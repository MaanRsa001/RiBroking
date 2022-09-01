package com.maan.dao.impl;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;


public class FacultiviesImpl  extends MyJdbcTemplate{
	Logger logger = LogUtil.getLogger(this.getClass());
	public boolean firstPageInsert(FaculitivesBean beanObj,final boolean flag,final boolean contract){
		logger.info("firstPageInsert() || Enter");
		boolean saveFlag=false;
		try{
			String query=getQuery(DBConstants.FAC_SP_FACULTATIVEPAGE1);
			logger.info("SP==>"+query);
			Object[] args=firstPageInsertAruguments(beanObj,flag,contract);
			int result=this.mytemplate.update(query,args);
			updatependingFields(beanObj);
			if(result==1)
				saveFlag=true;
			else
				saveFlag=false;
			logger.info("SP Result==>"+saveFlag);
			beanObj.setProposalNo(args[0].toString());
			if(StringUtils.isNotBlank(beanObj.getContractNo())) {
				beanObj.setBackmode("Con");
				beanObj.setStatus("Your Proposal is saved in Endorsement with Proposal No : "+beanObj.getProposalNo() +".");
			} else if("0".equalsIgnoreCase(beanObj.getProStatus()) ||"P".equalsIgnoreCase(beanObj.getProStatus()) || "A".equalsIgnoreCase(beanObj.getProStatus())){
				beanObj.setBackmode("Pro");
				beanObj.setStatus("Your Proposal is saved in Pending Stage with Proposal No : "+beanObj.getProposalNo()+".");
			}else if("N".equalsIgnoreCase(beanObj.getProStatus())){
				beanObj.setBackmode("NTU");
				beanObj.setStatus("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+beanObj.getProposalNo()+".");
			}else if("R".equalsIgnoreCase(beanObj.getProStatus())){
				beanObj.setBackmode("Reje");
				beanObj.setStatus("Your Proposal is saved in Rejected Stage with Proposal No : "+beanObj.getProposalNo() +".");
			} 
			if(saveFlag){
				InsertRemarkDetails(beanObj);
				UpadateUWShare(beanObj);
				if(StringUtils.isNotBlank(beanObj.getType())&&"1".equalsIgnoreCase(beanObj.getType()) ){
				InsertCoverDeductableDetails(beanObj);
				}else{
					InsertXolCoverDeductableDetails(beanObj);
				}
				
			}
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("firstPageInsert() || Exit");
		return true;
	}
	
	private void UpadateUWShare(FaculitivesBean beanObj) {
		String query="";
		try {
			query=getQuery("UPDATE_UW_SHARE");
			this.mytemplate.update(query,new Object[]{beanObj.getShSd(),beanObj.getProposalNo(),beanObj.getProposalNo()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void InsertRemarkDetails(FaculitivesBean beanObj) {
		logger.info("InsertRemarkDetails() || Enter");
		try {
			String  deleteQuery=getQuery("DELETE_REMARKS_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposalNo();
			dobj[1]="0";
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_REMARKS_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_RISK_REMARKS WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposalNo()},String.class);
			for(int i=0;i<beanObj.getRemarkSNo().size();i++){
				Object[] obj= new Object[12];
				obj[0]=beanObj.getProposalNo();
				obj[1]=beanObj.getContractNo();
				obj[2]="0";
				obj[3]=beanObj.getDepartmentId();
				obj[4]=beanObj.getProductId();
				obj[5]=amendId;
				obj[6]=i+1;
				obj[7]=beanObj.getDescription().get(i);
				obj[8]=beanObj.getRemark1().get(i);
				obj[9]=beanObj.getRemark2().get(i);
				obj[10]=beanObj.getLoginid();
				obj[11]=beanObj.getBranchCode();
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("InsertRemarkDetails() || Exit");
		
	}
	private void InsertCoverDeductableDetails(FaculitivesBean beanObj){
		logger.info("InsertCoverDeductableDetails() || Enter");
		try {
			String  deleteQuery=getQuery("DELETE_COVERDEDUCTABLE_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposalNo();
			dobj[1]="0";
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_COVERDEDUCTABLE_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_FAC_SI WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposalNo()},String.class);
			if(StringUtils.isNotBlank(beanObj.getType()) && "1".equalsIgnoreCase(beanObj.getType())){
			for(int i=0;i<beanObj.getCoverSNo().size();i++){
				Object[] obj= new Object[22];
				obj[0]=beanObj.getProposalNo();
				obj[1]=beanObj.getContractNo();
				obj[2]="0";
				obj[3]=beanObj.getDepartmentId();
				obj[4]=beanObj.getProductId();
				obj[5]=amendId;
				obj[6]=i+1;
				obj[7]=beanObj.getCoverdepartId().get(i);
				obj[8]=beanObj.getCoversubdepartId().get(i);
				obj[9]=beanObj.getCoverTypeId().get(i);
				obj[10]=beanObj.getCoverLimitOC().get(i).replace(",", "");
				obj[11]=beanObj.getDeductableLimitOC().get(i).replace(",", "");
				obj[12]=beanObj.getCoverageDays().get(i).replace(",", "");
				obj[13]=beanObj.getDeductableDays().get(i);
				obj[14]=beanObj.getPremiumRateList().get(i);
				obj[15]=beanObj.getEgnpiAsPerOff().get(i).replace(",", "");
				obj[16]=beanObj.getCoverRemark().get(i);
				obj[17]=beanObj.getLoginid();
				obj[18]=beanObj.getBranchCode();
				obj[19]=StringUtils.isEmpty(beanObj.getPmlPerList().get(i))? "" :beanObj.getPmlPerList().get(i).replace(",", "");
				obj[20]=StringUtils.isEmpty(beanObj.getPmlHundredPer().get(i))? "" :beanObj.getPmlHundredPer().get(i).replace(",", "");
				obj[21]=beanObj.getType();
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("InsertCoverDeductableDetails() || Exit");
	}
	private void InsertXolCoverDeductableDetails(FaculitivesBean beanObj) {
		logger.info("InsertXolCoverDeductableDetails() || Enter");
		try {
			String  deleteQuery=getQuery("DELETE_COVERDEDUCTABLE_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposalNo();
			dobj[1]="0";
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_XOLCOVERDEDUCTABLE_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_FAC_SI WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposalNo()},String.class);
			if(StringUtils.isNotBlank(beanObj.getType()) ){//&& "2".equalsIgnoreCase(beanObj.getType())
			for(int i=0;i<beanObj.getXolcoverSNo().size();i++){
				Object[] obj= new Object[16];
				obj[0]=beanObj.getProposalNo();
				obj[1]=beanObj.getContractNo();
				obj[2]="0";
				obj[3]=beanObj.getDepartmentId();
				obj[4]=beanObj.getProductId();
				obj[5]=amendId;
				obj[6]=i+1;
				obj[7]=beanObj.getXolcoverdepartId().get(i);
				obj[8]=beanObj.getXolcoversubdepartId().get(i);
				obj[9]=beanObj.getXolcoverLimitOC().get(i).replace(",", "");
				obj[10]=beanObj.getXoldeductableLimitOC().get(i).replace(",", "");
				obj[11]=beanObj.getXolpremiumRateList().get(i);
				obj[12]=beanObj.getXolgwpiOC().get(i).replace(",", "");
				obj[13]=beanObj.getLoginid();
				obj[14]=beanObj.getBranchCode();
				obj[15]=beanObj.getType();
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("InsertXolCoverDeductableDetails() || Exit");
		
	}
	private void updatependingFields(FaculitivesBean beanObj) {
		logger.info("updatependingFields() || Enter");
		String query="";
		Object args[]=null;
		try{
			 query=getQuery("UPDATE_RISK_DETAILS");
			 args=new Object[13];
			 args[0] = StringUtils.isEmpty(beanObj.getInwardType())? "" :beanObj.getInwardType();
			 args[1] = StringUtils.isEmpty(beanObj.getReceiptofPayment())? "" :beanObj.getReceiptofPayment();
			 args[2] = StringUtils.isEmpty(beanObj.getLocIssued())? "" :beanObj.getLocIssued();
			 args[3] = StringUtils.isEmpty(beanObj.getLatitude())? "" :beanObj.getLatitude();
			 args[4] = StringUtils.isEmpty(beanObj.getLongitude())? "" :beanObj.getLongitude();
			 args[5] = StringUtils.isEmpty(beanObj.getVessaletonnage())? "" :beanObj.getVessaletonnage();
			 args[6] = StringUtils.isEmpty(beanObj.getEndorsmenttype())? "" :beanObj.getEndorsmenttype();
			 args[7] = StringUtils.isEmpty(beanObj.getLocBankName())? "" :beanObj.getLocBankName();
			 args[8] = StringUtils.isEmpty(beanObj.getLocCreditPrd())? "" :beanObj.getLocCreditPrd();
			 args[9] = StringUtils.isEmpty(beanObj.getLocCreditAmt())? "" :beanObj.getLocCreditAmt().replaceAll(",", "");
			 args[10] = StringUtils.isEmpty(beanObj.getLocBeneficerName())? "" :beanObj.getLocBeneficerName();
			 args[11] = beanObj.getProposalNo();
			 args[12] = StringUtils.isEmpty(beanObj.getEndorsmentno())?"0":beanObj.getEndorsmentno();
			 this.mytemplate.update(query,args);
			 query =getQuery("UPDATE_FAC_RISK_PROPOSAL");
			 args = new Object[30];
			 args[0] =StringUtils.isEmpty(beanObj.getDeductibleFacXolPml())? "" :beanObj.getDeductibleFacXolPml();
			 args[1] = StringUtils.isEmpty(beanObj.getDeductibleFacXolPml())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getDeductibleFacXolPml(), beanObj.getUsCurrencyRate());
			 args[2] =StringUtils.isEmpty(beanObj.getDeductibleFacXolPmlOurShare())? "" :beanObj.getDeductibleFacXolPmlOurShare();
			 args[3] = StringUtils.isEmpty(beanObj.getDeductibleFacXolPmlOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getDeductibleFacXolPmlOurShare(), beanObj.getUsCurrencyRate());
			 args[4] =StringUtils.isEmpty(beanObj.getGwpiPml())? "" :beanObj.getGwpiPml();
			 args[5] = StringUtils.isEmpty(beanObj.getGwpiPml())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getGwpiPml(), beanObj.getUsCurrencyRate());
			 args[6] =StringUtils.isEmpty(beanObj.getGwpiPmlOurShare())? "" :beanObj.getGwpiPmlOurShare();
			 args[7] = StringUtils.isEmpty(beanObj.getGwpiPmlOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getGwpiPmlOurShare(), beanObj.getUsCurrencyRate());
			 args[8] =StringUtils.isEmpty(beanObj.getPslOC())? "0" :beanObj.getPslOC();
			 args[9] = StringUtils.isEmpty(beanObj.getPslOC())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPslOC(), beanObj.getUsCurrencyRate());
			 args[10] =StringUtils.isEmpty(beanObj.getPslOurShare())? "0" :beanObj.getPslOurShare();
			 args[11] = StringUtils.isEmpty(beanObj.getPslOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPslOurShare(), beanObj.getUsCurrencyRate());
			 args[12] =StringUtils.isEmpty(beanObj.getPllOC())? "0" :beanObj.getPllOC();
			 args[13] = StringUtils.isEmpty(beanObj.getPllOC())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPllOC(), beanObj.getUsCurrencyRate());
			 args[14] =StringUtils.isEmpty(beanObj.getPllOurShare())? "0" :beanObj.getPllOurShare();
			 args[15] = StringUtils.isEmpty(beanObj.getPllOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPllOurShare(), beanObj.getUsCurrencyRate());
			 args[16] =StringUtils.isEmpty(beanObj.getPblOC())? "0" :beanObj.getPblOC();
			 args[17] = StringUtils.isEmpty(beanObj.getPblOC())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPblOC(), beanObj.getUsCurrencyRate());
			 args[18] =StringUtils.isEmpty(beanObj.getPblOurShare())? "0" :beanObj.getPblOurShare();
			 args[19] = StringUtils.isEmpty(beanObj.getPblOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPblOurShare(), beanObj.getUsCurrencyRate());
			 args[20] =StringUtils.isEmpty(beanObj.getPmll())? "" :beanObj.getPmll();
			 args[21] = StringUtils.isEmpty(beanObj.getPmll())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPmll(), beanObj.getUsCurrencyRate());
			 args[22] =StringUtils.isEmpty(beanObj.getPmlOCOurShare())? "" :beanObj.getPmlOCOurShare();
			 args[23] = StringUtils.isEmpty(beanObj.getPmlOCOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getPmlOCOurShare(), beanObj.getUsCurrencyRate());
			 args[24] =StringUtils.isEmpty(beanObj.getSumInsuredPml())? "" :beanObj.getSumInsuredPml();
			 args[25] = StringUtils.isEmpty(beanObj.getSumInsuredPml())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getSumInsuredPml(), beanObj.getUsCurrencyRate());
			 args[26] =StringUtils.isEmpty(beanObj.getSumInsuredPmlOurShare())? "" :beanObj.getSumInsuredPmlOurShare();
			 args[27] = StringUtils.isEmpty(beanObj.getSumInsuredPmlOurShare())|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": getosvalue(beanObj.getSumInsuredPmlOurShare(), beanObj.getUsCurrencyRate());
			 args[28] = beanObj.getProposalNo();
			 args[29] = StringUtils.isEmpty(beanObj.getEndorsmentno())?"0":beanObj.getEndorsmentno();
			 this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("updatependingFields() || Exit");
	}

	public boolean secondPageInsert(FaculitivesBean beanObj,boolean flag){
		logger.info("secondPageInsert() || Enter");
		boolean saveFlag=false;
		try{
			final Object[] args=secondPageInsert(beanObj);
			String query=getQuery(DBConstants.FAC_SP_FACULTATIVEPAGE2);
			logger.info("SP Name==>"+query);
			int result=this.mytemplate.update(query,args);
			updateSecondPage(beanObj);
			if(result==1)
				saveFlag=true;
			else
				saveFlag=false;
			logger.info("SP Result==>"+saveFlag);
			if("".equalsIgnoreCase(beanObj.getContractNo())||"0".equalsIgnoreCase(beanObj.getContractNo())){
				Object[] conargs=new Object[3];
				conargs[0]=beanObj.getProposalNo();
				conargs[1]=beanObj.getProposalNo();
				conargs[2]=beanObj.getProposalNo();
				query=getQuery(DBConstants.FAC_SELECT_CONTGEN);
				logger.info("Select Query==>"+query);
				logger.info("Arg[0],Arg[1],Arg[2]====>"+beanObj.getProposalNo());
				Map<String, Object> contractMap=this.mytemplate.queryForMap(query, conargs);
				logger.info("Map Size=>"+contractMap.size());
				if(contractMap!=null&&contractMap.size()>0){	
					beanObj.setProStatus(contractMap.get("RSK_STATUS")==null?"":contractMap.get("RSK_STATUS").toString());
					beanObj.setContractNo(contractMap.get("RSK_CONTRACT_NO")==null?"":contractMap.get("RSK_CONTRACT_NO").toString());
					//if(saveFlag && flag){
					if(flag){
						if("A".equalsIgnoreCase(beanObj.getProStatus()) && ! "0".equalsIgnoreCase(beanObj.getShSd())){
							if(beanObj.getContractNo()==null||"0".equalsIgnoreCase(beanObj.getContractNo())||"".equalsIgnoreCase(beanObj.getContractNo())){
								if(!"".equals(beanObj.getRenewal_Contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
									beanObj.setContractNo(beanObj.getRenewal_Contract_no());
								}else {
									/* query=getQuery(DBConstants.COMMON_SELECT_MAXCONTNO);
									 logger.info("Select Query==>"+query);
									 logger.info("Arg[0]==>"+beanObj.getProductId());
									 logger.info("Arg[1]==>"+beanObj.getBranchCode());
									 beanObj.setContractNo((String)this.mytemplate.queryForObject(query,new Object[]{beanObj.getProductId(),beanObj.getBranchCode()},String.class));
									 logger.info("Result=>"+beanObj.getContractNo());
									 query=getQuery(DBConstants.COMMON_UPDATE_CONTNO);
									 logger.info("Update Query==>"+query);
									 logger.info("Arg[0]==>"+beanObj.getContractNo());
									 logger.info("Arg[1]==>"+beanObj.getProductId());
									 logger.info("Arg[2]==>"+beanObj.getBranchCode());
									 int res=this.mytemplate.update(query,new Object[]{beanObj.getContractNo(),beanObj.getProductId(),beanObj.getBranchCode()});
									 logger.info("Result=>"+res);*/
									//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
										beanObj.setContractNo(new DropDownControllor().getSequence("Contract",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposalNo(),beanObj.getYear()));
									
										/*}else
									beanObj.setContractNo(new DropDownControllor().getPolicyNo("2",beanObj.getProductId(),beanObj.getBranchCode()));*/
										
								}
								query=getQuery(DBConstants.COMMON_UPDATE_RISKDETCONTNO);
								logger.info("Update Query==>"+query);
								logger.info("Arg[0]==>"+beanObj.getContractNo());
								logger.info("Arg[1]==>"+beanObj.getProposalNo());
								int res=this.mytemplate.update(query,new Object[]{beanObj.getContractNo(),beanObj.getProposalNo()});
								logger.info("Result=>"+res);
								query=getQuery(DBConstants.COMMON_UPDATE_POSMASCONTNO);
								logger.info("Update Query==>"+query);
								logger.info("Arg[0]==>"+beanObj.getContractNo());
								logger.info("Arg[1]==>"+beanObj.getProposalNo());
								res=this.mytemplate.update(query,new Object[]{beanObj.getContractNo(),beanObj.getProposalNo()});
								logger.info("Result=>"+res);
								if(!"".equals(beanObj.getRenewal_Contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
									beanObj.setStatus("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposalNo() +", Old Contract No: "+beanObj.getContractNo()+" and New Contract No : "+beanObj.getContractNo()+".");
								}else{
									beanObj.setStatus("Your Proposal is converted to Contract with Proposal No : "+beanObj.getProposalNo() +" and Contract No : "+beanObj.getContractNo()+".");
								}
								beanObj.setBackmode("Con");
							}
							else{
								beanObj.setStatus("Your Contract is updated with Proposal No : "+beanObj.getProposalNo()+" and Contract No : "+beanObj.getContractNo()+".");
								beanObj.setBackmode("Con");
							}
						}else if("0".equalsIgnoreCase(beanObj.getProStatus())||"P".equalsIgnoreCase(beanObj.getProStatus())||"A".equalsIgnoreCase(beanObj.getProStatus())){
							beanObj.setStatus("Your Proposal is saved in Pending Stage with Proposal No : "+beanObj.getProposalNo()+".");
							beanObj.setBackmode("Pro");
						}else if("N".equalsIgnoreCase(beanObj.getProStatus())){
							beanObj.setStatus("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+beanObj.getProposalNo()+".");
							beanObj.setBackmode("NTU");
						}	
					}else{
						if("0".equalsIgnoreCase(beanObj.getProStatus())||"P".equalsIgnoreCase(beanObj.getProStatus())||"A".equalsIgnoreCase(beanObj.getProStatus())){
							beanObj.setStatus("Your Proposal is saved in Pending Stage with Proposal No : "+beanObj.getProposalNo()+".");
							beanObj.setBackmode("Pro");
						}else if("N".equalsIgnoreCase(beanObj.getProStatus())){
							beanObj.setStatus("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+beanObj.getProposalNo()+".");
							beanObj.setBackmode("NTU");
						}
					}
				}else {
					beanObj.setStatus("Your Proposal is saved in Pending Stage with Proposal No : ==>"+beanObj.getProposalNo());
					beanObj.setBackmode("Pro");
				}		
			}else{
				beanObj.setStatus("Your Contract is updated with Proposal No : "+beanObj.getProposalNo()+" and Contract No : "+beanObj.getContractNo()+".");
				beanObj.setBackmode("Con");
			}
			if("Y".equalsIgnoreCase(beanObj.getLossRecord())){
				inserLossRecord(beanObj);
			}
			instalMentPremium(beanObj);
			//if(Integer.parseInt(beanObj.getNo_Insurer())>0){
			insertInsurarerTableInsert(beanObj);
			//}
			insertBonusDetails(beanObj);
			insertCrestaMaintable(beanObj);
			InsertRemarkDetails(beanObj);
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		//return saveFlag;
		logger.info("secondPageInsert() || Exit");
		return true;
	}

	

	private void inserLossRecord(FaculitivesBean beanObj) {
		logger.info("inserLossRecord() || Enter");
		String query="";
		Object args[];
		try{
			query =getQuery("LOSS_DELETE");
			args= new Object[2];
			args[0] = beanObj.getProposalNo();
			args[1] = "0";
			this.mytemplate.update(query,args);
			query = "SELECT MAX(AMEND_ID) FROM POSITION_MASTER WHERE PROPOSAL_NO=?";
			args = new String[1];
			args[0] = beanObj.getProposalNo();
			String endtNo = (String) this.mytemplate.queryForObject(query, args, String.class);
			query = getQuery("INSET_LOSS_REC");
			args = new String[18];
			for(int i=0;i<beanObj.getLossYear().size();i++){
				if(StringUtils.isNotBlank(beanObj.getLossYear().get(i))){
					args[0] = beanObj.getProposalNo();
					args[1] =StringUtils.isBlank(beanObj.getContractNo())?"":beanObj.getContractNo();
					args[2] =endtNo;
					args[3] =beanObj.getProductId();
					args[4] =beanObj.getBranchCode();
					args[5] ="0";
					args[6] =StringUtils.isBlank(beanObj.getLossYear().get(i))?"0":beanObj.getLossYear().get(i);
					args[7] =StringUtils.isBlank(beanObj.getLossNo().get(i))?"0":beanObj.getLossNo().get(i);
					args[8] =StringUtils.isBlank(beanObj.getLossinsuredName().get(i))?"0":beanObj.getLossinsuredName().get(i);
					args[9] =StringUtils.isBlank(beanObj.getLossInceptionDate().get(i))?"":beanObj.getLossInceptionDate().get(i);
					args[10] =StringUtils.isBlank(beanObj.getLossExpiryDate().get(i))?"":beanObj.getLossExpiryDate().get(i);
					args[11] =StringUtils.isBlank(beanObj.getLossDateOfLoss().get(i))?"0":beanObj.getLossDateOfLoss().get(i);
					args[12] =StringUtils.isBlank(beanObj.getLossCauseOfLoss().get(i))?"0":beanObj.getLossCauseOfLoss().get(i).replaceAll(",", "");
					args[13] =StringUtils.isBlank(beanObj.getLossInsuredClaim().get(i))?"0":beanObj.getLossInsuredClaim().get(i).replaceAll(",", "");
					args[14] =StringUtils.isBlank(beanObj.getLossPremium().get(i))?"0":beanObj.getLossPremium().get(i).replaceAll(",", "");
					args[15] =StringUtils.isBlank(beanObj.getLossRatio().get(i))?"0":beanObj.getLossRatio().get(i).replaceAll(",", "");
					args[16] =StringUtils.isBlank(beanObj.getLossLeader().get(i))?"0":beanObj.getLossLeader().get(i);
					args[17] =StringUtils.isBlank(beanObj.getLossITIReShare().get(i))?"0":beanObj.getLossITIReShare().get(i).replaceAll(",", "");
					this.mytemplate.update(query,args);
				}
			}
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("inserLossRecord() || Exit");
	}

	private void updateSecondPage(FaculitivesBean beanObj) {
		logger.info("updateSecondPage() || Enter");
		String query="";
		try{
			query = getQuery("RISK_COM_UPDATE");
			Object args[] = new Object[8];
			args[0] = beanObj.getLeader_Underwriter();
			args[1] = beanObj.getLeader_Underwriter_share();
			args[2] = beanObj.getLeader_Underwriter_country();
			args[3] = beanObj.getExclusion();
			args[4] = beanObj.getCrestaStatus();
			args[5] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			args[6] = beanObj.getProposalNo();
			args[7] = StringUtils.isEmpty(beanObj.getEndorsmentno())?"0":beanObj.getEndorsmentno();;
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("updateSecondPage() || Exit");
	}

	private void insertInsurarerTableInsert(final FaculitivesBean beanObj)  {	
		logger.info("insertInsurarerTableInsert() || Enter");
		try{
			final int LoopCount = Integer.parseInt(beanObj.getNo_Insurer());
			//String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposalNo());
			Object[] obj = new Object[12];
			String query = "SELECT MAX(AMEND_ID) FROM POSITION_MASTER WHERE PROPOSAL_NO=?";
			Object[] args = new String[1];
			args[0] = beanObj.getProposalNo();
			String endtNo = (String) this.mytemplate.queryForObject(query, args, String.class);
			
			query = "delete from TTRN_INSURER_DETAILS where PROPOSAL_NO=? and ENDORSEMENT_NO=?";
			args = new Object[2];
			args[0] = beanObj.getProposalNo();
			args[1] = endtNo;
			this.mytemplate.update(query,args);
			
			if(LoopCount==0){
				beanObj.setRetper("100");
			}
			query=getQuery(DBConstants.FAC_INSERT_INSDETAILS);
			obj[0] = 0;
			obj[1] = beanObj.getProposalNo();
			obj[2] = "";
			obj[3] = endtNo;
			obj[4] = "R";
			obj[5] = StringUtils.isEmpty(beanObj.getRetper())? "0" :beanObj.getRetper();
			obj[6] = "Y";
			obj[7] = "";
			obj[8] = "";
			obj[9] = "";
			obj[10] = beanObj.getLoginid();
			obj[11] = beanObj.getBranchCode();
			logger.info("Insert Query==>"+query);
			logger.info("Args[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(query, obj);
			logger.info("Result=>"+res);
			
			obj = new Object[12];
			obj[0] = 1;
			obj[1] = beanObj.getProposalNo();
			obj[2] = StringUtils.isEmpty(beanObj.getRetroDupContract()) ? "0" :beanObj.getRetroDupContract();
			obj[3] = endtNo;
			obj[4] = "C";
			obj[5] = StringUtils.isEmpty(beanObj.getRetper())? "0" :beanObj.getRetper();
			obj[6] = "Y";
			obj[7] = StringUtils.isEmpty(beanObj.getRetroDupYerar())? "0" :beanObj.getRetroDupYerar();
			obj[8] ="TR";
			obj[9] = "";
			obj[10] = beanObj.getLoginid();
			obj[11] = beanObj.getBranchCode();
			logger.info("Insert Query==>"+query);
			logger.info("Args[]=>"+StringUtils.join(obj,","));
			res=this.mytemplate.update(query, obj);
			logger.info("Result=>"+res);
			int j=2;
			for (int i = 0; i < LoopCount; i++) {
				obj = new Object[12];
				obj[0] = j;
				obj[1] = beanObj.getProposalNo();
				obj[2] = StringUtils.isEmpty(beanObj.getCedingCompanyValList().get(i)) ? "0" :beanObj.getCedingCompanyValList().get(i);
				obj[3] = endtNo;
				obj[4] = "C";
				obj[5] = StringUtils.isEmpty(beanObj.getRetroPercentage().get(i))? "0" :beanObj.getRetroPercentage().get(i);
				obj[6] = "Y";
				obj[7] = StringUtils.isEmpty(beanObj.getUwYearValList().get(i))? "0" :beanObj.getUwYearValList().get(i);
				obj[8] = StringUtils.isEmpty(beanObj.getRetroTypeValList().get(i))? "" :beanObj.getRetroTypeValList().get(i);
				obj[9] = "";
				obj[10] = beanObj.getLoginid();
				obj[11] = beanObj.getBranchCode();
				logger.info("Insert Query==>"+query);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(query, obj);
				logger.info("Result=>"+res);
				if(i>0){
				if(StringUtils.isNotBlank(beanObj.getContractNo()) && !"0".equals(beanObj.getContractNo())&&"SR".equalsIgnoreCase(beanObj.getRetroTypeValList().get(i))){
					String updateQry=getQuery(DBConstants.FAC_UPDATE_FAC_CONTRACT_NO);
					logger.info("Update Query=>"+updateQry);
					logger.info("Args[0]=>"+beanObj.getContractNo());
					logger.info("Args[1]=>"+beanObj.getCedingCompanyValList().get(i));
					int result=this.mytemplate.update(updateQry, new Object[]{beanObj.getContractNo(),beanObj.getCedingCompanyValList().get(i)});
					logger.info("Update Result=>"+result);
				}
				}
				j++;
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}	
		logger.info("insertInsurarerTableInsert() || Exit");
	}
	private void getInsurarerDetails(final FaculitivesBean formObj,boolean view) {
		logger.info("getInsurarerDetails() || Enter");
		int noofInsurar =0;
	
		try{
			List<String> retroTypeValList = new ArrayList<String>();
			List<String> uwYearValList = new ArrayList<String>();
			List<String> cedingCompanyValList = new ArrayList<String>();
			List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			List<String> retroPercentage = new ArrayList<String>();
			List<List<Map<String,Object>>> uwlList = new ArrayList<List<Map<String,Object>>>();
			List<List<Map<String,Object>>> retrolList = new ArrayList<List<Map<String,Object>>>();
			List<Map<String,Object>> retroCedList = new ArrayList<Map<String,Object>>();
			if(StringUtils.isNotBlank(formObj.getNo_Insurer())){
				 noofInsurar = Integer.parseInt(formObj.getNo_Insurer());
				 noofInsurar = noofInsurar+1;
			}
			Object[] insargs=null;
			String query="";
			if(view){
				insargs=new Object[3];
				insargs[0]=formObj.getAmendId();
				insargs[1]=formObj.getProposalNo();
				insargs[2]=noofInsurar;
				query=getQuery(DBConstants.FAC_SELECT_VIEWINSDETAILS);
			}else{
				query=getQuery(DBConstants.FAC_SELECT_INSDETAILS);
				insargs=new Object[2];
				insargs[0]=formObj.getProposalNo();
				insargs[1]=noofInsurar;
			}
			logger.info("Query=>"+query);
			logger.info("Args[]=>"+StringUtils.join(insargs,","));
			//formObj.getRequest().setAttribute("NoInsurar",formObj.getNo_Insurer());
			List<Map<String,Object>> insDetailsList=this.mytemplate.queryForList(query,insargs);
			logger.info("List Size==>"+insDetailsList.size());
			if (insDetailsList!=null&&insDetailsList.size()>0){
				for(int j=0;j<insDetailsList.size();j++){
					Map<String,Object> insDetailsMap=(Map<String,Object>)insDetailsList.get(j);
					if("R".equalsIgnoreCase(insDetailsMap.get("TYPE").toString())){
						formObj.setRetper(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
						if(StringUtils.isNotBlank(formObj.getNo_Insurer()) && formObj.getRetper()=="0") {
							for(int z=0; z<Integer.valueOf(formObj.getNo_Insurer()) ; z++) {
								retroTypeValList.add("");
								uwYearValList.add("");
								cedingCompanyValList.add("");
								retroPercentage.add("");
								
								uwlList.add(new ArrayList<Map<String,Object>>());
								retrolList.add(new ArrayList<Map<String,Object>>());
							}
					}
					}
					else{
						if(j<=noofInsurar){
							if(1==j){
								String uwYear ="";
								query = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
								if(formObj.getYear().equalsIgnoreCase(insDetailsMap.get("UW_YEAR").toString())){
								formObj.setRetroDupYerar(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
								  uwYear = insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString();
								}else{
									formObj.setRetroDupYerar(formObj.getYear());
								  uwYear = formObj.getYear();
								}
								formObj.setRetroDupType(insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString());
								String retroType = insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString();
								
								List<Map<String,Object>> list =  this.mytemplate.queryForList(query, new Object[] {"4",(StringUtils.isBlank(retroType)?"":retroType),uwYear,formObj.getInceptionDate(),formObj.getBranchCode(),(StringUtils.isBlank(retroType)?"":retroType),uwYear,formObj.getInceptionDate()});
								for(int k=0;k<list.size();k++){
									Map<String,Object> map=list.get(k);
									formObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
								}
								}
							else {
						retroTypeValList.add(insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString());
						
						uwYearValList.add(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
						
						cedingCompanyValList.add(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
						
						retroPercentage.add(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
						
						query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
						
						List<Map<String,Object>> uwList = this.mytemplate.queryForList(query, new Object[] {"4",formObj.getInceptionDate(),formObj.getBranchCode(),formObj.getInceptionDate()});
						uwlList.add(uwList);
						
						String retroType = insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString();
						
						String uwYear = insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString();
						
						if(StringUtils.isNotBlank(retroType) && "TR".equals(retroType)){
							query = getQuery("fac.select.retroContDetTR");
							retroCedList = this.mytemplate.queryForList(query, new Object[] {"4",uwYear,formObj.getInceptionDate(),formObj.getBranchCode(),uwYear,formObj.getInceptionDate()});
						}else{
							query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
							retroCedList = this.mytemplate.queryForList(query, new Object[] {"4",(StringUtils.isBlank(retroType)?"":retroType),uwYear,formObj.getInceptionDate(),formObj.getBranchCode(),(StringUtils.isBlank(retroType)?"":retroType),uwYear,formObj.getInceptionDate()});
						}
						}
						
						
						if(1==j){
							retroDupList.add(retroCedList);
							//retrolList.add(new ArrayList<Map<String,Object>>());
						}
						else{
							retrolList.add(retroCedList);
						}
					}
					}
				}
				if(StringUtils.isNotBlank(formObj.getNo_Insurer())) {
					int num = noofInsurar - (insDetailsList.size()-1);
					for(int z=0; z<num ; z++) {
						uwYearValList.add("");
						cedingCompanyValList.add("");
						retroPercentage.add("");
						
						uwlList.add(new ArrayList<Map<String,Object>>());
						retrolList.add(new ArrayList<Map<String,Object>>());
						retroDupList.add(new ArrayList<Map<String,Object>>());
					}
				}
				//uwYearValList.add("");
				formObj.setUwlList(uwlList);
				formObj.setRetrolList(retrolList);
				formObj.setRetroDupList(retroDupList);
				formObj.setRetroTypeValList(retroTypeValList);
				formObj.setUwYearValList(uwYearValList);
				formObj.setCedingCompanyValList(cedingCompanyValList);
				formObj.setRetroPercentage(retroPercentage);
			}
			else if(StringUtils.isNotBlank(formObj.getNo_Insurer())) {
				for(int z=0; z<Integer.valueOf(formObj.getNo_Insurer())+1 ; z++) {
					retroTypeValList.add("");
					if(0==z){
						query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
						List<Map<String,Object>> uwList = this.mytemplate.queryForList(query, new Object[] {"4",formObj.getInceptionDate(),formObj.getBranchCode(),formObj.getInceptionDate()});
						uwlList.add(uwList);
						/*if("1".equalsIgnoreCase(formObj.getNo_Insurer())){
						retroPercentage.add("100");
						}
						else{
						retroPercentage.add("");
						}*/
						//uwYearValList.add(formObj.getYear());
						formObj.setRetroDupYerar(formObj.getYear());
						formObj.setRetroDupType("TR");
						String query1 = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
						List<Map<String,Object>> list =  this.mytemplate.queryForList(query1, new Object[] {"4","TR",formObj.getYear(),formObj.getInceptionDate(),formObj.getBranchCode(),"TR",formObj.getYear(),formObj.getInceptionDate()});
						for(int k=0;k<list.size();k++){
							Map<String,Object> map=list.get(k);
							formObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
						}
						//formObj.setRetroDupContract(this.mytemplate.queryForObject(query1, new Object[] {"4","TR",formObj.getYear(),formObj.getInceptionDate(),formObj.getBranchCode(),"TR",formObj.getYear(),formObj.getInceptionDate()},String.class));
						formObj.setRetroDupMode("Duplicate");
						
						//List<Map<String,Object>> retroCedList = this.mytemplate.queryForString(query1, new Object[] {"4","TR",formObj.getYear(),formObj.getInceptionDate(),formObj.getBranchCode(),"TR",formObj.getYear(),formObj.getInceptionDate()});
						//retroDupList.add(retroCedList);
					}
					else{
						uwlList.add(new ArrayList<Map<String,Object>>());
						retroPercentage.add("");
						uwYearValList.add("");
						//retroDupList.add(new ArrayList<Map<String,Object>>());
					}
					
					cedingCompanyValList.add("");
					retrolList.add(new ArrayList<Map<String,Object>>());
					
				}
				
				formObj.setUwlList(uwlList);
				formObj.setRetrolList(retrolList);
				//formObj.setRetroDupList(retroDupList);
				formObj.setRetroTypeValList(retroTypeValList);
				formObj.setUwYearValList(uwYearValList);
				formObj.setCedingCompanyValList(cedingCompanyValList);
				formObj.setRetroPercentage(retroPercentage);
				
			}
			if(formObj.getNo_Insurer()!=null&&Integer.parseInt(formObj.getNo_Insurer())==0){
				formObj.setRetroDupYerar(formObj.getYear());
				formObj.setRetroDupType("TR");
				String query1 = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
				List<Map<String,Object>> list =  this.mytemplate.queryForList(query1, new Object[] {"4","TR",formObj.getYear(),formObj.getInceptionDate(),formObj.getBranchCode(),"TR",formObj.getYear(),formObj.getInceptionDate()});
				for(int k=0;k<list.size();k++){
					Map<String,Object> map=list.get(k);
					formObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				}
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}	
		logger.info("getInsurarerDetails() || Exit");
	}
	public boolean showFirstPageItems(final FaculitivesBean formObj)  {	
		logger.info("showFirstPageItems() || Enter");
		try	{
			Object[] args=new Object[3];
			args[0]=formObj.getProposalNo();
			args[1]=formObj.getProposalNo();
			args[2]=formObj.getProposalNo();
			String query=getQuery(DBConstants.FAC_SELECT_FIRST_PAGE_DET);
			logger.info("Select Query ===>"+query);
			logger.info("Arg[0],Arg[1],Arg[2]====>"+formObj.getProposalNo());
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				formObj.setProposalNo(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
				formObj.setContractNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
				formObj.setEndorsmentno(tempMap.get("RSK_ENDORSEMENT_NO")==null?"":tempMap.get("RSK_ENDORSEMENT_NO").toString());
				formObj.setProductId(tempMap.get("RSK_PRODUCTID")==null?"":tempMap.get("RSK_PRODUCTID").toString());
				formObj.setDepartmentId(tempMap.get("RSK_DEPTID")==null?"":tempMap.get("RSK_DEPTID").toString());
				formObj.setProfitCenterCode(tempMap.get("RSK_PFCID")==null?"":tempMap.get("RSK_PFCID").toString());
				formObj.setSubProfitCenter(tempMap.get("RSK_SPFCID")==null?"":tempMap.get("RSK_SPFCID").toString());
				formObj.setCedingCompany(tempMap.get("RSK_CEDINGID")==null?"":tempMap.get("RSK_CEDINGID").toString());
				formObj.setBroker(tempMap.get("RSK_BROKERID")==null?"":tempMap.get("RSK_BROKERID").toString());
				formObj.setMonth(tempMap.get("MONTH")==null?"":tempMap.get("MONTH").toString());
				formObj.setYear(tempMap.get("RSK_UWYEAR")==null?"":tempMap.get("RSK_UWYEAR").toString());
				formObj.setUnderwriter(tempMap.get("RSK_UNDERWRITTER")==null?"":tempMap.get("RSK_UNDERWRITTER").toString());
				//formObj.setInceptionDate(tempMap.get("INS_DATE")==null?"":dateFormat(tempMap.get("INS_DATE").toString()));
				//formObj.setExpiryDate(tempMap.get("EXP_DATE")==null?"":dateFormat(tempMap.get("EXP_DATE").toString()));
				//formObj.setAccountDate(tempMap.get("AC_DATE")==null?"":dateFormat(tempMap.get("AC_DATE").toString()));
				formObj.setInceptionDate(tempMap.get("INS_DATE")==null?"":(tempMap.get("INS_DATE").toString()));
				formObj.setExpiryDate(tempMap.get("EXP_DATE")==null?"":(tempMap.get("EXP_DATE").toString()));
				formObj.setAccountDate(tempMap.get("AC_DATE")==null?"":(tempMap.get("AC_DATE").toString()));
				formObj.setOriginalCurrency(tempMap.get("RSK_ORIGINAL_CURR")==null?"":tempMap.get("RSK_ORIGINAL_CURR").toString());
				formObj.setUsCurrencyRate(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("RSK_EXCHANGE_RATE")==null?"":tempMap.get("RSK_EXCHANGE_RATE").toString()))?"":DropDownControllor.exchRateFormat(tempMap.get("RSK_EXCHANGE_RATE")==null?"":tempMap.get("RSK_EXCHANGE_RATE").toString()));
				formObj.setTerritory(tempMap.get("RSK_TERRITORY")==null?"":tempMap.get("RSK_TERRITORY").toString());
				formObj.setInsuredName(tempMap.get("RSK_INSURED_NAME")==null?"":tempMap.get("RSK_INSURED_NAME").toString());
				formObj.setLocation(tempMap.get("RSK_LOCATION")==null?"":tempMap.get("RSK_LOCATION").toString());
				formObj.setCity(tempMap.get("RSK_CITY")==null?"":tempMap.get("RSK_CITY").toString());
				formObj.setNr(StringUtils.isBlank(formObj.getContarctno())?"":tempMap.get("NR")==null?"":tempMap.get("NR").toString());
				//formObj.setCedantsRet(StringUtils.isBlank(formObj.getContarctno())?"":tempMap.get("RSK_CEDANT_RETENTION")==null?"":tempMap.get("RSK_CEDANT_RETENTION").toString());
				//formObj.setMaxiumlimit(StringUtils.isBlank(formObj.getContarctno())?"":tempMap.get("MAXIMUM_LIMIT_OC")==null?"":tempMap.get("MAXIMUM_LIMIT_OC").toString());
				//formObj.setDeductible(StringUtils.isBlank(formObj.getContarctno())?"":tempMap.get("DEDUCTIBLE_OC")==null?"":tempMap.get("DEDUCTIBLE_OC").toString());
				formObj.setCedantsRet(tempMap.get("RSK_CEDANT_RETENTION")==null?"":tempMap.get("RSK_CEDANT_RETENTION").toString());
				formObj.setMaxiumlimit(tempMap.get("MAXIMUM_LIMIT_OC")==null?"":tempMap.get("MAXIMUM_LIMIT_OC").toString());
				formObj.setDeductible(tempMap.get("DEDUCTIBLE_OC")==null?"":tempMap.get("DEDUCTIBLE_OC").toString());
				//formObj.setSpRetro(StringUtils.isBlank(tempMap.get("SP_RETRO").toString())?"N":tempMap.get("SP_RETRO").toString());
				formObj.setSpRetro(StringUtils.isBlank(tempMap.get("SP_RETRO")==null?"":tempMap.get("SP_RETRO").toString())?"N":tempMap.get("SP_RETRO").toString());
				formObj.setPml(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("PML")==null?"":tempMap.get("PML").toString()))?"":tempMap.get("PML").toString());
				formObj.setSipml(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("SI_PML_OC")==null?"":tempMap.get("SI_PML_OC").toString()))?"SI":tempMap.get("SI_PML_OC").toString());
				formObj.setCu(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("CU")==null?"":tempMap.get("CU").toString()))?"":tempMap.get("CU").toString());
				formObj.setCuRsn(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("CU_RSN")==null?"":tempMap.get("CU_RSN").toString()))?"":tempMap.get("CU_RSN").toString());
				formObj.setSumInsured(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("SUM_INSURED_OC")==null?"":tempMap.get("SUM_INSURED_OC").toString()))?"":tempMap.get("SUM_INSURED_OC").toString());
				formObj.setGwpi(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("GWPI_OC")==null?"":tempMap.get("GWPI_OC").toString()))?"":tempMap.get("GWPI_OC").toString());
				formObj.setPmll(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("PML_OC")==null?"":tempMap.get("PML_OC").toString()))?"":tempMap.get("PML_OC").toString());
				formObj.setTpl(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("TPL_OC")==null?"":tempMap.get("TPL_OC").toString()))?"":DropDownControllor.formatter(tempMap.get("TPL_OC").toString()));
				formObj.setShWt(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("SHARE_WRITTEN")==null?"":tempMap.get("SHARE_WRITTEN").toString()))?"":tempMap.get("SHARE_WRITTEN").toString());
				formObj.setShSd(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(tempMap.get("SHARE_SIGNED")==null?"":tempMap.get("SHARE_SIGNED").toString())||"0".equalsIgnoreCase(tempMap.get("SHARE_SIGNED")==null?"":tempMap.get("SHARE_SIGNED").toString()))?"":tempMap.get("SHARE_SIGNED").toString());
				formObj.setProStatus(tempMap.get("RSK_STATUS")==null?"":tempMap.get("RSK_STATUS").toString());
				formObj.setNo_Insurer((StringUtils.isBlank(tempMap.get("SP_RETRO")==null?"":tempMap.get("SP_RETRO").toString())||"N".equalsIgnoreCase(tempMap.get("SP_RETRO").toString()))?"0":tempMap.get("NO_OF_INSURERS")==null?"":tempMap.get("NO_OF_INSURERS").toString());
				formObj.setPremiumrate(StringUtils.isBlank(formObj.getContarctno())&&(StringUtils.isBlank(formObj.getNo_Insurer()))?"":tempMap.get("RSK_PREMIUM_RATE")==null?"":tempMap.get("RSK_PREMIUM_RATE").toString());
				formObj.setPolicyBranch(tempMap.get("RSK_POLBRANCH")==null?"":tempMap.get("RSK_POLBRANCH").toString());
				formObj.setCedRetenType(StringUtils.isBlank(tempMap.get("RSK_CEDRET_TYPE").toString())?"A":tempMap.get("RSK_CEDRET_TYPE").toString());
				formObj.setRenewal_Contract_no(tempMap.get("OLD_CONTRACTNO")==null?"":tempMap.get("OLD_CONTRACTNO").toString());
				formObj.setRenewalStatus(tempMap.get("RENEWAL_STATUS")==null?"1":tempMap.get("RENEWAL_STATUS").toString());
				formObj.setBaseLoginID(tempMap.get("LOGIN_ID")==null?"":tempMap.get("LOGIN_ID").toString());
				formObj.setDeductibleFacXol(tempMap.get("DEDUCTIBLE_FACXOL_OC")==null?"":tempMap.get("DEDUCTIBLE_FACXOL_OC").toString());
				formObj.setXolOC(tempMap.get("XOL_OC")==null?"":tempMap.get("XOL_OC").toString());
				String mdinst=tempMap.get("MND_INSTALLMENTS")==null?"":tempMap.get("MND_INSTALLMENTS").toString();
				formObj.setNoOfInst(StringUtils.isBlank(mdinst)||"0".equals(mdinst)?"1":mdinst);
				formObj.setModeOfTransport(tempMap.get("MODE_TRANSPORT_ID")==null?"":tempMap.get("MODE_TRANSPORT_ID").toString());
				formObj.setVesselName(tempMap.get("VESSEL_NAME")==null?"":tempMap.get("VESSEL_NAME").toString());
				formObj.setVesselAge(tempMap.get("VESSEL_AGE")==null?"":tempMap.get("VESSEL_AGE").toString());
				//formObj.setLimitPerVesselOC(StringUtils.isBlank(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_VESSEL_OC").toString()))?"":tempMap.get("LIMIT_PER_VESSEL_OC").toString());
				//formObj.setLimitPerLocationOC(StringUtils.isBlank(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_LOCATION_OC").toString()))?"":tempMap.get("LIMIT_PER_LOCATION_OC").toString());

				String lpvOC = tempMap.get("LIMIT_PER_VESSEL_OC")==null?"":tempMap.get("LIMIT_PER_VESSEL_OC").toString();
				String lplOC = tempMap.get("LIMIT_PER_LOCATION_OC")==null?"":tempMap.get("LIMIT_PER_LOCATION_OC").toString();

				formObj.setLimitPerVesselOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lpvOC)||"0".equals(lpvOC))?"":lpvOC));
				formObj.setLimitPerLocationOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lplOC)||"0".equals(lplOC))?"":lplOC));

				formObj.setType(tempMap.get("TYPE")==null?"":tempMap.get("TYPE").toString());
				formObj.setInterest(tempMap.get("RSK_INTEREST")==null?"":tempMap.get("RSK_INTEREST").toString());
				formObj.setCountryIncludedList(tempMap.get("COUNTRIES_INCLUDE")==null?"":tempMap.get("COUNTRIES_INCLUDE").toString());
				formObj.setCountryExcludedList(tempMap.get("COUNTRIES_EXCLUDE")==null?"":tempMap.get("COUNTRIES_EXCLUDE").toString());
				formObj.setContractListVal(tempMap.get("DATA_MAP_CONT_NO")==null?"":tempMap.get("DATA_MAP_CONT_NO").toString());
				formObj.setDeductibleFacXolPml(tempMap.get("COVER_LIMIT_PML_OC")==null?"":tempMap.get("COVER_LIMIT_PML_OC").toString());
				formObj.setGwpiPml(tempMap.get("GWPI_PML_OC")==null?"":tempMap.get("GWPI_PML_OC").toString());
				formObj.setPslOC(tempMap.get("PSL_OC")==null?"":tempMap.get("PSL_OC").toString());
				formObj.setPllOC(tempMap.get("PLL_OC")==null?"":tempMap.get("PLL_OC").toString());
				formObj.setPblOC(tempMap.get("PBL_OC")==null?"":tempMap.get("PBL_OC").toString());
				formObj.setSumInsuredPml(tempMap.get("SUM_INS_PML_OC")==null?"":tempMap.get("SUM_INS_PML_OC").toString());
				formObj.setInwardType(tempMap.get("INWARD_BUS_TYPE")==null?"":tempMap.get("INWARD_BUS_TYPE").toString());
				formObj.setReceiptofPayment(tempMap.get("RSK_RECEIPT_PAYEMENT")==null?"":tempMap.get("RSK_RECEIPT_PAYEMENT").toString());
				formObj.setLocIssued(tempMap.get("RSK_LOC_ISSUED")==null?"":tempMap.get("RSK_LOC_ISSUED").toString());
				formObj.setLatitude(tempMap.get("RSK_LATITUDE")==null?"":tempMap.get("RSK_LATITUDE").toString());
				formObj.setLongitude(tempMap.get("RSK_LONGITUDE")==null?"":tempMap.get("RSK_LONGITUDE").toString());
				formObj.setVessaletonnage(tempMap.get("RSK_VESSAL_TONNAGE")==null?"":tempMap.get("RSK_VESSAL_TONNAGE").toString());
				formObj.setEndorsmenttype(tempMap.get("RS_ENDORSEMENT_TYPE")==null?"":tempMap.get("RS_ENDORSEMENT_TYPE").toString());
				formObj.setLocBankName(tempMap.get("RSK_LOC_BNK_NAME")==null?"":tempMap.get("RSK_LOC_BNK_NAME").toString());
				formObj.setLocCreditPrd(tempMap.get("RSK_LOC_CRDT_PRD")==null?"":tempMap.get("RSK_LOC_CRDT_PRD").toString());
				formObj.setLocCreditAmt(tempMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(tempMap.get("RSK_LOC_CRDT_AMT").toString()));
				formObj.setLocBeneficerName(tempMap.get("RSK_LOC_BENFCRE_NAME")==null?"":tempMap.get("RSK_LOC_BENFCRE_NAME").toString());
				formObj.setXollayerNo(tempMap.get("Xol_LAYER_NO")==null?"":tempMap.get("Xol_LAYER_NO").toString());
				
			}
			
			if(StringUtils.isNotBlank(formObj.getContractNo())&&!"0".equals(formObj.getContractNo())){
				formObj.setPrclFlag(new DropDownControllor().getPLCLCountStatus(formObj.getContractNo(), "0"));
			}else{
				formObj.setPrclFlag(false);
			}
			formObj.setAmendId(new DropDownControllor().getRiskComMaxAmendId(formObj.getProposal_no()));
			GetRemarksDetails(formObj);
			GetCoverDeductableDetails(formObj);
			GetXolCoverDeductableDetails(formObj);
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}logger.info("showFirstPageItems() || Exit");
		return true;
	}

	private void GetRemarksDetails(FaculitivesBean formObj) {
		logger.info("GetRemarksDetails() || Enter");
		try {
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_REMARKS_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=formObj.getProposalNo();
			obj[1]="0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> description = new ArrayList<String>();
				List<String> remark1 = new ArrayList<String>();
				List<String> remark2 = new ArrayList<String>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					description.add(insMap.get("RSK_DESCRIPTION")==null?"Remarks":insMap.get("RSK_DESCRIPTION").toString());
					remark1.add(insMap.get("RSK_REMARK1")==null?" ":insMap.get("RSK_REMARK1").toString());
					remark2.add(insMap.get("RSK_REMARK2")==null?"":insMap.get("RSK_REMARK2").toString());

				}
				formObj.setDescription(description);
				formObj.setRemark1(remark1);
				formObj.setRemark2(remark2);
				formObj.setRemarkCount(Integer.toString(result.size()));
				
				formObj.setRemarkList(result);
				
			}else{
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				 formObj.setRemarkList(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("GetRemarksDetails() || Enter");
		
	}
	private void GetCoverDeductableDetails(FaculitivesBean formObj) {
		logger.info("GetCoverDeductableDetails() || Enter");
		try {
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_COVER_DEDUCTABLE_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=formObj.getProposalNo();
			obj[1]="0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				List<String> coversubdepartId = new ArrayList<String>();
				List<String> coverTypeId = new ArrayList<String>();
				List<String> coverLimitAmount = new ArrayList<String>();
				List<String> deductableLimitAmount = new ArrayList<String>();
				List<String> coverageDays = new ArrayList<String>();
				List<String> deductableDays = new ArrayList<String>();
				List<String> premiumRateList = new ArrayList<String>();
				List<String> pmlPer = new ArrayList<String>();
				List<String> pmlhunPer = new ArrayList<String>();
				List<String> gwpi = new ArrayList<String>();
				List<String> coverRemark = new ArrayList<String>();
				List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					coverdepartId.add(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString());
					coversubdepartId.add(insMap.get("RSK_SUBCLASS")==null?"":insMap.get("RSK_SUBCLASS").toString());
					coverTypeId.add(insMap.get("RSK_TYPE")==null?"":insMap.get("RSK_TYPE").toString());
					coverLimitAmount.add(insMap.get("RSK_COVERLIMIT_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_COVERLIMIT_OC").toString()));
					deductableLimitAmount.add(insMap.get("RSK_DEDUCTABLELIMIT_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_DEDUCTABLELIMIT_OC").toString()));
					coverageDays.add(insMap.get("RSK_COVERAGE_DAYS")==null?"":insMap.get("RSK_COVERAGE_DAYS").toString());
					deductableDays.add(insMap.get("RSK_DEDUCTABLE_DAYS")==null?"":insMap.get("RSK_DEDUCTABLE_DAYS").toString());
					premiumRateList.add(insMap.get("RSK_PREMIUM_RATE")==null?"":DropDownControllor.formatter(insMap.get("RSK_PREMIUM_RATE").toString()));
					gwpi.add(insMap.get("RSK_GWPI_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_GWPI_OC").toString()));
					coverRemark.add(insMap.get("RSK_COVER_REMARKS")==null?"":insMap.get("RSK_COVER_REMARKS").toString());
					pmlPer.add(DropDownControllor.formatter(insMap.get("PML_PERCENTAGE")==null?"0":insMap.get("PML_PERCENTAGE").toString()));
					pmlhunPer.add(DropDownControllor.formatter(insMap.get("PML_HUN_PER_OC")==null?"0":insMap.get("PML_HUN_PER_OC").toString()));
					coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString(),formObj.getBranchCode(),formObj.getProductId()));
				
				}
				formObj.setCoverdepartId(coverdepartId);
				formObj.setCoversubdepartId(coversubdepartId);
				formObj.setCoverTypeId(coverTypeId);
				formObj.setCoverLimitOC(coverLimitAmount);
				formObj.setDeductableLimitOC(deductableLimitAmount);
				formObj.setCoverageDays(coverageDays);
				formObj.setDeductableDays(deductableDays);
				formObj.setPremiumRateList(premiumRateList);
				formObj.setEgnpiAsPerOff(gwpi);
				formObj.setCoversubDepartList(coversubdeptList);
				formObj.setLoopcount(Integer.toString(result.size()));
				formObj.setCoverRemark(coverRemark);
				formObj.setCoverdeductableList(result);
				formObj.setPmlPerList(pmlPer);
				formObj.setPmlHundredPer(pmlhunPer);
				
			}else{
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				formObj.setCoverdeductableList(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("GetCoverDeductableDetails() || Exit");
		
	}
	private void GetXolCoverDeductableDetails(FaculitivesBean formObj) {
		logger.info("GetXolCoverDeductableDetails() || Enter");
		try {
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_XOLCOVER_DEDUCTABLE_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=formObj.getProposalNo();
			obj[1]="0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				List<String> coversubdepartId = new ArrayList<String>();
				List<String> coverLimitAmount = new ArrayList<String>();
				List<String> deductableLimitAmount = new ArrayList<String>();
				List<String> premiumRateList = new ArrayList<String>();
				List<String> gwpi = new ArrayList<String>();
				List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					coverdepartId.add(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString());
					coversubdepartId.add(insMap.get("RSK_SUBCLASS")==null?"":insMap.get("RSK_SUBCLASS").toString());
					coverLimitAmount.add(insMap.get("RSK_COVERLIMIT_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_COVERLIMIT_OC").toString()));
					deductableLimitAmount.add(insMap.get("RSK_DEDUCTABLELIMIT_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_DEDUCTABLELIMIT_OC").toString()));
					premiumRateList.add(insMap.get("RSK_PREMIUM_RATE")==null?"":DropDownControllor.formatter(insMap.get("RSK_PREMIUM_RATE").toString()));
					gwpi.add(insMap.get("RSK_GWPI_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_GWPI_OC").toString()));
					coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString(),formObj.getBranchCode(),"1"));
				
				}
				formObj.setXolcoverdepartId(coverdepartId);
				formObj.setXolcoversubdepartId(coversubdepartId);
				formObj.setXolcoverLimitOC(coverLimitAmount);
				formObj.setXoldeductableLimitOC(deductableLimitAmount);
				formObj.setXolpremiumRateList(premiumRateList);
				formObj.setXolgwpiOC(gwpi);
				formObj.setCoversubDepartList(coversubdeptList);
				formObj.setXolLoopcount(Integer.toString(result.size()));
				formObj.setXolCoverdeductableList(result);
				
			}else{
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				formObj.setXolCoverdeductableList(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("GetXolCoverDeductableDetails() || Exit");
		
	}
	public boolean secondPageShowItems(final FaculitivesBean formObj)  {
		logger.info("secondPageShowItems() || Enter");
		try{
			Object[] args=new Object[4];
			args[1]=formObj.getProposalNo();
			args[2]=formObj.getProposalNo();
			args[3]=formObj.getProposalNo();
			args[0]=formObj.getBranchCode();
			String query=getQuery(DBConstants.FAC_SELECT_SECOND_PAGE_DET);
			logger.info("Select Query ===>"+query);
			logger.info("Arg[0],Arg[1],Arg[2]====>"+formObj.getProposalNo());
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				formObj.setRiskGrade(tempMap.get("RISK_GRADE")==null?"":tempMap.get("RISK_GRADE").toString());
				//formObj.setSumusd(DropDownControllor.formatter(tempMap.get("SUM_INSURED_DC")==null?"":tempMap.get("SUM_INSURED_DC").toString()));
				formObj.setOccCode(tempMap.get("OCCUPATION_CODE")==null?"":tempMap.get("OCCUPATION_CODE").toString());
				formObj.setRiskDetail(tempMap.get("RISK_DETAILS")==null?"":tempMap.get("RISK_DETAILS").toString());
				formObj.setFireProt(StringUtils.isBlank(tempMap.get("FIRE_PORT")==null?"":tempMap.get("FIRE_PORT").toString())?"N":tempMap.get("FIRE_PORT").toString());
				formObj.setScope(tempMap.get("SCOPE")==null?"":tempMap.get("SCOPE").toString());
				formObj.setMbind(StringUtils.isBlank(tempMap.get("MB_IND")==null?"":tempMap.get("MB_IND").toString())?"N":tempMap.get("MB_IND").toString());
				formObj.setCategoryZone(tempMap.get("CATEGORY_ZONE")==null?"":tempMap.get("CATEGORY_ZONE").toString());
				formObj.setEqwsInd(tempMap.get("EARTHQUAKE_WS_IND")==null?"":tempMap.get("EARTHQUAKE_WS_IND").toString());
				formObj.setWsThreat(tempMap.get("WS_THREAT_IND")==null?"":tempMap.get("WS_THREAT_IND").toString());
				formObj.setEqThreat(tempMap.get("EQ_THREAT")==null?"":tempMap.get("EQ_THREAT").toString());
				formObj.setCommn(StringUtils.isBlank(formObj.getContractNo()==null?"":formObj.getContractNo().toString())&&(StringUtils.isBlank(tempMap.get("RSK_COMM")==null?"":tempMap.get("RSK_COMM").toString()))?"":tempMap.get("RSK_COMM").toString());
				formObj.setBrokerage(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("RSK_BROKERAGE")==null?"":tempMap.get("RSK_BROKERAGE").toString()))?"":tempMap.get("RSK_BROKERAGE").toString());
				formObj.setAcqBonus(tempMap.get("RSK_BONUS_ID")==null?"":tempMap.get("RSK_BONUS_ID").toString());
				formObj.setAcqBonusPercentage(DropDownControllor.exchRateFormat(tempMap.get("RSK_NOCLAIMBONUS_PRCENT")==null?"":tempMap.get("RSK_NOCLAIMBONUS_PRCENT").toString()));
				formObj.setTax(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("RSK_TAX")==null?"":tempMap.get("RSK_TAX").toString()))?"":tempMap.get("RSK_TAX").toString());
				formObj.setLossRecord(StringUtils.isBlank(tempMap.get("RSK_LOSS_RECORD")==null?"":tempMap.get("RSK_LOSS_RECORD").toString())?"N":tempMap.get("RSK_LOSS_RECORD").toString());
				formObj.setDgmsApproval(tempMap.get("RSK_DGM_APPROVAL")==null?"":tempMap.get("RSK_DGM_APPROVAL").toString());
				formObj.setUnderwriterCode(tempMap.get("RSK_UNDERWRITTER_CODE")==null?"":tempMap.get("RSK_UNDERWRITTER_CODE").toString());
				formObj.setUwRecommendation(tempMap.get("RSK_UW_RECOMMENDATION")==null?"":tempMap.get("RSK_UW_RECOMMENDATION").toString());
				formObj.setRemarks(tempMap.get("RSK_REMARKS")==null?"":tempMap.get("RSK_REMARKS").toString());
				formObj.setOthAccep(tempMap.get("RSK_OTH_ACCEP")==null?"":tempMap.get("RSK_OTH_ACCEP").toString());
				formObj.setReftoHO(StringUtils.isBlank(tempMap.get("RSK_REF_TO_HO")==null?"":tempMap.get("RSK_REF_TO_HO").toString())?"N":tempMap.get("RSK_REF_TO_HO").toString());
				formObj.setAcqCost(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("RSK_ACQUISTION_COST_OC")==null?"":tempMap.get("RSK_ACQUISTION_COST_OC").toString()))?"":tempMap.get("RSK_ACQUISTION_COST_OC").toString());
				formObj.setAccusd(tempMap.get("RSK_ACQUISTION_COST_DC")==null?"":tempMap.get("RSK_ACQUISTION_COST_DC").toString());
				//formObj.setCu(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("CU")==null?"":tempMap.get("CU").toString()))?"":tempMap.get("CU").toString());
				//formObj.setCu(tempMap.get("CU")==null?"":tempMap.get("CU").toString());
				formObj.setCuRsn(tempMap.get("CU_RSN")==null?"":tempMap.get("CU_RSN").toString());
				formObj.setOthercost(tempMap.get("RSK_OTHER_COST")==null?"":tempMap.get("RSK_OTHER_COST").toString());
				//formObj.setOthercost(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("RSK_OTHER_COST")==null?"":tempMap.get("RSK_OTHER_COST").toString()))?"":tempMap.get("RSK_OTHER_COST").toString());
				//formObj.setAcqCostPer((Double.parseDouble(StringUtils.isBlank(formObj.getCommn())?"0":formObj.getCommn())+Double.parseDouble(StringUtils.isBlank(formObj.getBrokerage())?"0":formObj.getBrokerage())+Double.parseDouble(StringUtils.isBlank(formObj.getTax())?"0":formObj.getTax())+Double.parseDouble(StringUtils.isBlank(formObj.getOthercost())?"0":formObj.getOthercost()))+"");
				formObj.setAcqCostPer(DropDownControllor.GetACC(Double.parseDouble(StringUtils.isBlank(formObj.getCommn())?"0":formObj.getCommn())+Double.parseDouble(StringUtils.isBlank(formObj.getBrokerage())?"0":formObj.getBrokerage())+Double.parseDouble(StringUtils.isBlank(formObj.getTax())?"0":formObj.getTax())+Double.parseDouble(StringUtils.isBlank(formObj.getOthercost())?"0":formObj.getOthercost()))+"");
				String mLop = tempMap.get("M_LOP")==null?"":tempMap.get("M_LOP").toString();
				formObj.setMlopYN(StringUtils.isBlank(mLop)?"N":mLop);
				String aLop = tempMap.get("A_LOP")==null?"":tempMap.get("A_LOP").toString();
				formObj.setMlopYN(StringUtils.isBlank(aLop)?"N":aLop);

				formObj.setAlopYN(tempMap.get("A_LOP")==null?"N":tempMap.get("A_LOP").toString());
				//formObj.setNoOfInst(StringUtils.isBlank(tempMap.get("MND_INSTALLMENTS")==null?"":tempMap.get("MND_INSTALLMENTS").toString())?"0":tempMap.get("MND_INSTALLMENTS").toString());
				
				formObj.setLeader_Underwriter_country(tempMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":tempMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
				formObj.setLeader_Underwriter(tempMap.get("RSK_LEAD_UW")==null ? "0" : tempMap.get("RSK_LEAD_UW").toString());
				formObj.setLeader_Underwriter_share(tempMap.get("RSK_LEAD_UW_SHARE")==null ? "0" : tempMap.get("RSK_LEAD_UW_SHARE").toString());
				formObj.setExclusion(tempMap.get("RSK_EXCLUSION")==null?"":tempMap.get("RSK_EXCLUSION").toString());
				formObj.setCrestaStatus(tempMap.get("RSK_CREASTA_STATUS")==null?"":tempMap.get("RSK_CREASTA_STATUS").toString());
				formObj.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)", new Object[]{formObj.getProposalNo()}, String.class));
			}
			args = new String[4];
			args[0] = formObj.getProposalNo();
			args[1] = "0";
			args[2] = formObj.getProposalNo();
			args[3] = "0";
			query = getQuery(DBConstants.RISK_SELECT_GETINSTALMENTDATA);
			logger.info("selectQry " + query);
			logger.info("Args[0]..[2]=>" +  formObj.getProposalNo());
			logger.info("Args[1]..[3]=>0");
			List<Map<String,Object>> instalmentList = this.mytemplate.queryForList(query,args);
			logger.info("Result Size=>" +instalmentList.size());
			if (instalmentList != null) {
				List<String> finalList = new ArrayList<String>();
				List<String> paymentdays = new ArrayList<String>();
				List<String> transactionList=new ArrayList<String>();
				for (int k = 0; k < instalmentList.size(); k++) {
					Map<String,Object> insMap = (Map<String,Object>)instalmentList.get(k);
					finalList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					paymentdays.add((insMap.get("PAYEMENT_DUE_DAY")==null?"":insMap.get("PAYEMENT_DUE_DAY").toString()));
					//formObj.getRequest().setAttribute("instalmentDate" + k,insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					transactionList.add(insMap.get("TRANSACTION_NO")==null?"":insMap.get("TRANSACTION_NO").toString());
				}
				
				for (int k = 0; k < Integer.parseInt(formObj.getNoOfInst()); k++) {
					//if(StringUtils.isBlank(paymentdays.get(k))){
						paymentdays.add(formObj.getReceiptofPayment());
					//}
				}
				formObj.setInstalmentDateList(finalList);
				formObj.setPaymentDueDays(paymentdays);
				formObj.setTransactionList(transactionList);
				
			}else{
				List<String> paymentdays = new ArrayList<String>();
				for (int k = 0; k < Integer.parseInt(formObj.getNoOfInst()); k++) {
						paymentdays.add(formObj.getReceiptofPayment());
				}
				formObj.setPaymentDueDays(paymentdays);
			}
			//if(StringUtils.isNotBlank(formObj.getNo_Insurer()) && Integer.parseInt(formObj.getNo_Insurer())>0 ) {
			getInsurarerDetails(formObj,false);
			//}
			GetRemarksDetails(formObj);
			
			if("Y".equalsIgnoreCase(formObj.getLossRecord())){
				getLossDEtails(formObj);
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("secondPageShowItems() || Exit");
		return false;
	}

	private void getLossDEtails(FaculitivesBean bean) {
		logger.info("getLossDEtails() || Enter");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> year = new ArrayList<String>();
		List<String> no = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> ins = new ArrayList<String>();
		List<String> exp = new ArrayList<String>();
		List<String> lossdate = new ArrayList<String>();
		List<String> cause = new ArrayList<String>();
		List<String> claim = new ArrayList<String>();
		List<String> premium = new ArrayList<String>();
		List<String> ratio = new ArrayList<String>();
		List<String> leader = new ArrayList<String>();
		List<String> share = new ArrayList<String>();
		try{
			String query=getQuery("GET_LOSS_DETIALS");
			Object args[] = new Object[2];
			args[0] =bean.getProposalNo();
			args[1] ="0";
			list = this.mytemplate.queryForList(query,args);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> insMap = list.get(i);
					year.add((insMap.get("YEAR")==null?"":insMap.get("YEAR").toString()));
					no.add((insMap.get("LOSS_NO")==null?"":insMap.get("LOSS_NO").toString()));
					name.add((insMap.get("INSURED_NAME")==null?"":insMap.get("INSURED_NAME").toString()));
					ins.add((insMap.get("INCEPTION_DATE")==null?"":insMap.get("INCEPTION_DATE").toString()));
					exp.add((insMap.get("EXPIRYDATE")==null?"":insMap.get("EXPIRYDATE").toString()));
					lossdate.add((insMap.get("DATE_OF_LOSS")==null?"":insMap.get("DATE_OF_LOSS").toString()));
					cause.add((insMap.get("CAUSE_OF_LOSS")==null?"":insMap.get("CAUSE_OF_LOSS").toString()));
					claim.add((insMap.get("INSURED_CLAIM")==null?"":DropDownControllor.formatter(insMap.get("INSURED_CLAIM").toString())));
					premium.add((insMap.get("PREMIUM")==null?"":DropDownControllor.formatterpercentage(insMap.get("PREMIUM").toString())));
					ratio.add((insMap.get("LOSS_RATIO")==null?"":DropDownControllor.formatter(insMap.get("LOSS_RATIO").toString())));
					leader.add((insMap.get("LEADER")==null?"":insMap.get("LEADER").toString()));
					share.add((insMap.get("ITI_RE_SHARE")==null?"":DropDownControllor.formatter(insMap.get("ITI_RE_SHARE").toString())));
				}
			}
			bean.setLossRecordList(list);
			if(bean.getLossRecordList().size()<=0){
			for(int i=0;i<5;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				}
			}
			bean.setLossRecordList(list);
			bean.setLossYear(year);
			bean.setLossNo(no);
			bean.setLossinsuredName(name);
			bean.setLossInceptionDate(ins);
			bean.setLossExpiryDate(exp);
			bean.setLossDateOfLoss(lossdate);
			bean.setLossCauseOfLoss(cause);
			bean.setLossInsuredClaim(claim);
			bean.setLossPremium(premium);
			bean.setLossRatio(ratio);
			bean.setLossLeader(leader);
			bean.setLossITIReShare(share);
			
			bean.setLossCount(Integer.toString(year.size()));
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getLossDEtails() || Exit");
	}

	public boolean viewMode(final FaculitivesBean formObj)  {	
		logger.info("viewMode() || Enter");
		try
		{
			Object[] args=new Object[13];
			args[0]=formObj.getBranchCode();
			args[1]=formObj.getBranchCode();
			args[2]=formObj.getBranchCode();
			args[3]=formObj.getBranchCode();
			args[4]=formObj.getBranchCode();
			args[5]=formObj.getBranchCode();
			args[6]=formObj.getBranchCode();
			args[7]=formObj.getBranchCode();
			args[8]=formObj.getBranchCode();
			args[9]=formObj.getBranchCode();
			//args[10]=formObj.getBranchCode();
			args[10]=formObj.getProposalNo();
			//args[12]=formObj.getProposalNo();
			args[11]=formObj.getAmendId();
			args[12]=formObj.getAmendId();
			String query=getQuery(DBConstants.FAC_SELECT_VIEW_FIRST_PAGE);
			logger.info("Query=>"+query);
			logger.info("Arg[}=>"+StringUtils.join(args,","));

			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);

				formObj.setProposalNo(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
				formObj.setContractNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
				formObj.setProductId(tempMap.get("TMAS_PRODUCT_NAME")==null?"":tempMap.get("TMAS_PRODUCT_NAME").toString());
				formObj.setDepartmentId(tempMap.get("TMAS_DEPARTMENT_ID")==null?"":tempMap.get("TMAS_DEPARTMENT_ID").toString());
				formObj.setDepartmentName(tempMap.get("TMAS_DEPARTMENT_NAME")==null?"":tempMap.get("TMAS_DEPARTMENT_NAME").toString());
				formObj.setProfitCenterCode(tempMap.get("TMAS_PFC_NAME")==null?"":tempMap.get("TMAS_PFC_NAME").toString());
				formObj.setSubProfitCenter(tempMap.get("RSK_SPFCID")==null?"":tempMap.get("RSK_SPFCID").toString());
				if(!"ALL".equalsIgnoreCase(formObj.getSubProfitCenter())){
					formObj.setSubProfitCenter(tempMap.get("TMAS_SPFC_NAME")==null?"":tempMap.get("TMAS_SPFC_NAME").toString().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));	
				}
				formObj.setCedingCompany(tempMap.get("COMAPNY")==null?"":tempMap.get("COMAPNY").toString());
				formObj.setBroker(tempMap.get("BROKER")==null?"":tempMap.get("BROKER").toString());
				formObj.setMonth(tempMap.get("MONTH")==null?"":tempMap.get("MONTH").toString());
				formObj.setYear(tempMap.get("RSK_UWYEAR")==null?"":tempMap.get("RSK_UWYEAR").toString());
				formObj.setUnderwriter(tempMap.get("UNDERWRITTER")==null?"":tempMap.get("UNDERWRITTER").toString());
				formObj.setInceptionDate(tempMap.get("INSDATE")==null?"":tempMap.get("INSDATE").toString());
				formObj.setExpiryDate(tempMap.get("EXPDATE")==null?"":tempMap.get("EXPDATE").toString());
				formObj.setAccountDate(tempMap.get("ACDATE")==null?"":tempMap.get("ACDATE").toString());
				formObj.setOriginalCurrency(tempMap.get("SHORT_NAME")==null?"":tempMap.get("SHORT_NAME").toString());
				formObj.setUsCurrencyRate(tempMap.get("RSK_EXCHANGE_RATE")==null?"":tempMap.get("RSK_EXCHANGE_RATE").toString());
				formObj.setTerritoryName(tempMap.get("TERRITORY_DESC")==null?"":tempMap.get("TERRITORY_DESC").toString());
				formObj.setInsuredName(tempMap.get("RSK_INSURED_NAME")==null?"":tempMap.get("RSK_INSURED_NAME").toString());
				formObj.setLocation(tempMap.get("RSK_LOCATION")==null?"":tempMap.get("RSK_LOCATION").toString());
				formObj.setCity(tempMap.get("RSK_CITY")==null?"":tempMap.get("RSK_CITY").toString());
				formObj.setNr(tempMap.get("NR")==null?"":tempMap.get("NR").toString());
				formObj.setCedantsRet(DropDownControllor.formatter(tempMap.get("RSK_CEDANT_RETENTION")==null?"":tempMap.get("RSK_CEDANT_RETENTION").toString()));
				formObj.setMaxiumlimit(DropDownControllor.formatter(tempMap.get("MAXIMUM_LIMIT_OC")==null?"":tempMap.get("MAXIMUM_LIMIT_OC").toString()));
				formObj.setDeductible(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_OC")==null?"":tempMap.get("DEDUCTIBLE_OC").toString()));
				formObj.setDeductibleDC(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_DC")==null?"":tempMap.get("DEDUCTIBLE_DC").toString()));
				formObj.setDeductibleFacXol(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_FACXOL_OC")==null?"":tempMap.get("DEDUCTIBLE_FACXOL_OC").toString()));
				formObj.setDeductibleFacXolDC(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_FACXOL_DC")==null?"":tempMap.get("DEDUCTIBLE_FACXOL_DC").toString()));
				formObj.setSpRetro(tempMap.get("SP_RETRO")==null?"":tempMap.get("SP_RETRO").toString());
				//formObj.setPml(DropDownControllor.formatter(tempMap.get("PML")==null?"":tempMap.get("PML").toString()));
				formObj.setPml(tempMap.get("PML")==null?"":tempMap.get("PML").toString());
				formObj.setSipml(tempMap.get("SI_PML_OC")==null?"":tempMap.get("SI_PML_OC").toString());
				formObj.setCu(tempMap.get("CU")==null?"":DropDownControllor.exchRateFormat(tempMap.get("CU").toString()));
				formObj.setCuRsn(tempMap.get("CU_RSN")==null?"":tempMap.get("CU_RSN").toString());
				formObj.setSumInsured(DropDownControllor.formatter(tempMap.get("SUM_INSURED_OC")==null?"":tempMap.get("SUM_INSURED_OC").toString()));
				formObj.setGwpi(DropDownControllor.formatter(tempMap.get("GWPI_OC")==null?"":tempMap.get("GWPI_OC").toString()));
				formObj.setPmll(DropDownControllor.formatter(tempMap.get("PML_100_OC")==null?"":tempMap.get("PML_100_OC").toString()));
				formObj.setTpl(DropDownControllor.formatter(tempMap.get("TPL_OC")==null?"":tempMap.get("TPL_OC").toString()));
				//formObj.setShWt(DropDownControllor.formatter(tempMap.get("SHARE_WRITTEN")==null?"":tempMap.get("SHARE_WRITTEN").toString()));
				//formObj.setShSd(DropDownControllor.formatter(tempMap.get("SHARE_SIGNED")==null?"":tempMap.get("SHARE_SIGNED").toString()));
				formObj.setShWt(tempMap.get("SHARE_WRITTEN")==null?"":tempMap.get("SHARE_WRITTEN").toString());
				formObj.setShSd(tempMap.get("SHARE_SIGNED")==null?"":tempMap.get("SHARE_SIGNED").toString());
				if (StringUtils.isNotBlank(tempMap.get("RSK_PROPOSAL_TYPE").toString())) {
					if ("P".equalsIgnoreCase(tempMap.get("RSK_PROPOSAL_TYPE").toString())||"0".equalsIgnoreCase(tempMap.get("RSK_PROPOSAL_TYPE").toString())) {
						formObj.setStatus("Pending");
					}else if ("N".equalsIgnoreCase(tempMap.get("RSK_PROPOSAL_TYPE").toString())) {
						formObj.setStatus("Not Taken Up");
					} else if ("A".equalsIgnoreCase(tempMap.get("RSK_PROPOSAL_TYPE").toString())) {
						formObj.setStatus("Accepted");
					} else if ("R".equalsIgnoreCase(tempMap.get("RSK_PROPOSAL_TYPE").toString())) {
						formObj.setStatus("Rejected");
					} 
				}
				formObj.setInterest(tempMap.get("RSK_INTEREST")==null?"":tempMap.get("RSK_INTEREST").toString());
				formObj.setSumusd(DropDownControllor.formatter(tempMap.get("SUM_INSURED_DC")==null?"":tempMap.get("SUM_INSURED_DC").toString()));
				formObj.setGwpiUsd(DropDownControllor.formatter(tempMap.get("GWPI_DC")==null?"":tempMap.get("GWPI_DC").toString()));
				formObj.setPmlusd(DropDownControllor.formatter(tempMap.get("PML_100_DC")==null?"":tempMap.get("PML_100_DC").toString()));
				formObj.setTplusd(DropDownControllor.formatter(tempMap.get("TPL_DC")==null?"":tempMap.get("TPL_DC").toString()));
				formObj.setSumOrginalUsd(DropDownControllor.formatter(tempMap.get("SUM_INSURED_OUR_SHARE_DC")==null?"":tempMap.get("SUM_INSURED_OUR_SHARE_DC").toString()));
				formObj.setGwpiOurShareusd(DropDownControllor.formatter(tempMap.get("GWPI_OUR_SHARE_DC")==null?"":tempMap.get("GWPI_OUR_SHARE_DC").toString()));
				formObj.setPmlOurShareusd(DropDownControllor.formatter(tempMap.get("PML_OS_DC")==null?"":tempMap.get("PML_OS_DC").toString()));
				formObj.setTplOurshareusd(DropDownControllor.formatter(tempMap.get("TPL_OUR_SHARE_DC")==null?"":tempMap.get("TPL_OUR_SHARE_DC").toString()));
				formObj.setDeductibleOurShareusd(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_OURSHARE_DC")==null?"":tempMap.get("DEDUCTIBLE_OURSHARE_DC").toString()));
				formObj.setCoverlimitOurShareusd(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_FACXOL_OURSHARE_DC")==null?"":tempMap.get("DEDUCTIBLE_FACXOL_OURSHARE_DC").toString()));
				formObj.setNo_Insurer(tempMap.get("NO_OF_INSURERS")==null?"":tempMap.get("NO_OF_INSURERS").toString());
				formObj.setPolicyBranch(tempMap.get("TMAS_POL_BRANCH_NAME")==null?"":tempMap.get("TMAS_POL_BRANCH_NAME").toString());
				formObj.setScope(tempMap.get("SCOPE")==null?"":tempMap.get("SCOPE").toString());
				formObj.setSumInsuredOurShare(DropDownControllor.formatter(tempMap.get("SUM_INSURED_OUR_SHARE_OC")==null?"":tempMap.get("SUM_INSURED_OUR_SHARE_OC").toString()));
				formObj.setGwpiOurShare(DropDownControllor.formatter(tempMap.get("GWPI_OUR_SHARE_OC")==null?"":tempMap.get("GWPI_OUR_SHARE_OC").toString()));
				formObj.setPmlOCOurShare(DropDownControllor.formatter(tempMap.get("PML_OS_OC")==null?"":tempMap.get("PML_OS_OC").toString()));
				formObj.setTplOurShare(DropDownControllor.formatter(tempMap.get("TPL_OUR_SHARE_OC")==null?"":tempMap.get("TPL_OUR_SHARE_OC").toString()));
				formObj.setDeductibleOurShare(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_OURSHARE_OC")==null?"":tempMap.get("DEDUCTIBLE_OURSHARE_OC").toString()));
				formObj.setCoverlimitOurShare(DropDownControllor.formatter(tempMap.get("DEDUCTIBLE_FACXOL_OURSHARE_OC")==null?"":tempMap.get("DEDUCTIBLE_FACXOL_OURSHARE_OC").toString()));
				//formObj.setPremiumrate(DropDownControllor.formatter(tempMap.get("RSK_PREMIUM_RATE")==null?"":tempMap.get("RSK_PREMIUM_RATE").toString()));
				formObj.setPremiumrate(tempMap.get("RSK_PREMIUM_RATE")==null?"":tempMap.get("RSK_PREMIUM_RATE").toString());
				formObj.setCedRetenType(tempMap.get("RSK_CEDRET_TYPE")==null?"":tempMap.get("RSK_CEDRET_TYPE").toString());
				formObj.setModeOfTransport(tempMap.get("TRANSPORT_DESCRIPTION")==null?"":tempMap.get("TRANSPORT_DESCRIPTION").toString());
				formObj.setVesselName(tempMap.get("VESSEL_NAME")==null?"":tempMap.get("VESSEL_NAME").toString());
				//formObj.setVesselAge(StringUtils.isBlank(tempMap.get("VESSEL_AGE")==null?"":tempMap.get("VESSEL_AGE").toString())||"0".equals(tempMap.get("VESSEL_AGE")==null?"":tempMap.get("VESSEL_AGE").toString())?"":tempMap.get("VESSEL_AGE").toString());
				formObj.setVesselAge(tempMap.get("VESSEL_AGE")==null?"":tempMap.get("VESSEL_AGE").toString());
				/*formObj.setLimitPerVesselOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_VESSEL_OC").toString())||"0".equals(tempMap.get("LIMIT_PER_VESSEL_OC").toString()))?"":tempMap.get("LIMIT_PER_VESSEL_OC").toString()));
				formObj.setLimitPerVesselDC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_VESSEL_DC")==null?"0":tempMap.get("LIMIT_PER_VESSEL_DC").toString())||"0".equals(tempMap.get("LIMIT_PER_VESSEL_DC")==null?"0":tempMap.get("LIMIT_PER_VESSEL_DC").toString()))?"":tempMap.get("LIMIT_PER_VESSEL_DC").toString()));
				formObj.setLimitPerLocationOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_LOCATION_OC").toString())||"0".equals(tempMap.get("LIMIT_PER_LOCATION_OC").toString()))?"":tempMap.get("LIMIT_PER_LOCATION_OC").toString()));
				formObj.setLimitPerLocationDC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(tempMap.get("LIMIT_PER_LOCATION_DC")==null?"0":tempMap.get("LIMIT_PER_LOCATION_DC").toString())||"0".equals(tempMap.get("LIMIT_PER_LOCATION_DC")==null?"0":tempMap.get("LIMIT_PER_LOCATION_DC").toString()))?"":tempMap.get("LIMIT_PER_LOCATION_DC").toString()));*/
				formObj.setXolOC(DropDownControllor.formatter(tempMap.get("XOL_OC")==null?"":tempMap.get("XOL_OC").toString()));
				formObj.setXolDC(DropDownControllor.formatter(tempMap.get("XOL_DC")==null?"":tempMap.get("XOL_DC").toString()));
				formObj.setXolOSOC(DropDownControllor.formatter(tempMap.get("XOL_OURSHARE_OC")==null?"":tempMap.get("XOL_OURSHARE_OC").toString()));
				formObj.setXolOSDC(DropDownControllor.formatter(tempMap.get("XOL_OURSHARE_DC")==null?"":tempMap.get("XOL_OURSHARE_DC").toString()));
				formObj.setNoOfInst(StringUtils.isBlank(tempMap.get("MND_INSTALLMENTS")==null?"":tempMap.get("MND_INSTALLMENTS").toString())?"0":tempMap.get("MND_INSTALLMENTS").toString());
				formObj.setType(tempMap.get("TYPE_NAME")==null?"":tempMap.get("TYPE_NAME").toString());

				String lpvOC = tempMap.get("LIMIT_PER_VESSEL_OC")==null?"":tempMap.get("LIMIT_PER_VESSEL_OC").toString();
				String lpvDC = tempMap.get("LIMIT_PER_VESSEL_DC")==null?"":tempMap.get("LIMIT_PER_VESSEL_DC").toString();
				String lplOC = tempMap.get("LIMIT_PER_LOCATION_OC")==null?"":tempMap.get("LIMIT_PER_LOCATION_OC").toString();
				String lplDC = tempMap.get("LIMIT_PER_LOCATION_DC")==null?"":tempMap.get("LIMIT_PER_LOCATION_DC").toString();

				formObj.setLimitPerVesselOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lpvOC)||"0".equals(lpvOC))?"":lpvOC));
				formObj.setLimitPerVesselDC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lpvDC)||"0".equals(lpvDC))?"":lpvDC));
				formObj.setLimitPerLocationOC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lplOC)||"0".equals(lplOC))?"":lplOC));
				formObj.setLimitPerLocationDC(DropDownControllor.formatter(StringUtils.isBlank(formObj.getContractNo())&&(StringUtils.isBlank(lplDC)||"0".equals(lplDC))?"":lplDC));
				formObj.setInwardType(tempMap.get("INWARD_BUS_TYPE")==null?"":tempMap.get("INWARD_BUS_TYPE").toString());
				formObj.setLocIssued(tempMap.get("RSK_LOC_ISSUED")==null?"":tempMap.get("RSK_LOC_ISSUED").toString());
				formObj.setLatitude(tempMap.get("RSK_LATITUDE")==null?"":tempMap.get("RSK_LATITUDE").toString());
				formObj.setLongitude(tempMap.get("RSK_LONGITUDE")==null?"":tempMap.get("RSK_LONGITUDE").toString());
				formObj.setVessaletonnage(tempMap.get("RSK_VESSAL_TONNAGE")==null?"":tempMap.get("RSK_VESSAL_TONNAGE").toString());
				formObj.setPslOC(tempMap.get("PSL_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PSL_OC").toString()));
				formObj.setPslusd(tempMap.get("PSL_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PSL_DC").toString()));
				formObj.setPslOurShare(tempMap.get("PSL_OS_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PSL_OS_OC").toString()));
				formObj.setPslOurShareusd(tempMap.get("PSL_OS_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PSL_OS_DC").toString()));
				formObj.setPllOC(tempMap.get("PLL_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PLL_OC").toString()));
				formObj.setPllusd(tempMap.get("PLL_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PLL_DC").toString()));
				formObj.setPllOurShare(tempMap.get("PLL_OS_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PLL_OS_OC").toString()));
				formObj.setPllOurShareusd(tempMap.get("PLL_OS_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PLL_OS_DC").toString()));
				formObj.setPblOC(tempMap.get("PBL_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PBL_OC").toString()));
				formObj.setPblusd(tempMap.get("PBL_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PBL_DC").toString()));
				formObj.setPblOurShare(tempMap.get("PBL_OS_OC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PBL_OS_OC").toString()));
				formObj.setPblOurShareusd(tempMap.get("PBL_OS_DC")==null?"0.00":DropDownControllor.formatter(tempMap.get("PBL_OS_DC").toString()));
				formObj.setReceiptofPayment(tempMap.get("RSK_RECEIPT_PAYEMENT")==null?"":tempMap.get("RSK_RECEIPT_PAYEMENT").toString());
				formObj.setLocBankName(tempMap.get("RSK_LOC_BNK_NAME")==null?"":tempMap.get("RSK_LOC_BNK_NAME").toString());
				formObj.setLocCreditPrd(tempMap.get("RSK_LOC_CRDT_PRD")==null?"":tempMap.get("RSK_LOC_CRDT_PRD").toString());
				formObj.setLocCreditAmt(tempMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(tempMap.get("RSK_LOC_CRDT_AMT").toString()));
				formObj.setLocBeneficerName(tempMap.get("RSK_LOC_BENFCRE_NAME")==null?"":tempMap.get("RSK_LOC_BENFCRE_NAME").toString());
				formObj.setTerritory(tempMap.get("RSK_TERRITORY")==null?"":tempMap.get("RSK_TERRITORY").toString());
				formObj.setEndorsmenttype(tempMap.get("RS_ENDORSEMENT_TYPE")==null?"":tempMap.get("RS_ENDORSEMENT_TYPE").toString());
				formObj.setXollayerNo(tempMap.get("Xol_LAYER_NO")==null?"":tempMap.get("Xol_LAYER_NO").toString());
				String qry = "SELECT   RTRIM(XMLAGG(XMLELEMENT(E,TERRITORY_NAME,',')).EXTRACT('//text()'),',')  FROM   TMAS_TERRITORY  WHERE  TERRITORY_ID in("+formObj.getTerritory()+") and BRANCH_CODE="+formObj.getBranchCode();
				Object arg[]=new Object[2];
				if(StringUtils.isNotBlank(formObj.getTerritory())){
				arg[0] = formObj.getTerritory();
				arg[1] = formObj.getBranchCode();
				formObj.setTerritoryName(this.mytemplate.queryForObject(qry,String.class));
				}
				formObj.setCountryIncludedList(tempMap.get("COUNTRIES_INCLUDE")==null?"":tempMap.get("COUNTRIES_INCLUDE").toString());
				if(StringUtils.isNotBlank(formObj.getCountryIncludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+formObj.getCountryIncludedList()+") and BRANCH_CODE="+formObj.getBranchCode();
				arg[0] = formObj.getCountryIncludedList();
				formObj.setCountryIncludedName(this.mytemplate.queryForObject(qry,String.class));
				formObj.setCountryIncludedName(formObj.getCountryIncludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
				formObj.setCountryExcludedList(tempMap.get("COUNTRIES_EXCLUDE")==null?"":tempMap.get("COUNTRIES_EXCLUDE").toString());
				if(StringUtils.isNotBlank(formObj.getCountryExcludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+formObj.getCountryExcludedList()+") and BRANCH_CODE="+formObj.getBranchCode();
				arg[0] = formObj.getCountryExcludedList();
				formObj.setCountryExcludedName(this.mytemplate.queryForObject(qry,String.class));
				formObj.setCountryExcludedName(formObj.getCountryExcludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}

			}
			args=new String[4];
			args[2]=formObj.getProposalNo();
			args[3]=formObj.getAmendId();
			//args[2]=formObj.getAmendId();
			args[0]=formObj.getBranchCode();
			args[1]=formObj.getBranchCode();
			query=getQuery(DBConstants.FAC_SELECT_VIEW_SECOND_PAGE);
			logger.info("Query=>"+query);
			logger.info("Arg[]=>"+StringUtils.join(args,","));

			List<Map<String,Object>> list1 = this.mytemplate.queryForList(query,args);
			if(list1!=null && list1.size()>0){
				Map<String,Object> tempMap = list1.get(0);
				formObj.setCu(tempMap.get("CU")==null?"":DropDownControllor.exchRateFormat(tempMap.get("CU").toString()));
				formObj.setRiskGrade(tempMap.get("GRADE_DESC")==null?"":tempMap.get("GRADE_DESC").toString());
				formObj.setOccCode(tempMap.get("OCCUPATION_CODE")==null?"":tempMap.get("OCCUPATION_CODE").toString());
				formObj.setRiskDetail(tempMap.get("RISK_DETAILS")==null?"":tempMap.get("RISK_DETAILS").toString());
				formObj.setFireProt(StringUtils.isBlank(tempMap.get("FIRE_PORT")==null?"":tempMap.get("FIRE_PORT").toString())?"N":tempMap.get("FIRE_PORT").toString());
				formObj.setScope(tempMap.get("SCOPE")==null?"":tempMap.get("SCOPE").toString());
				formObj.setMbind(StringUtils.isBlank(tempMap.get("MB_IND")==null?"":tempMap.get("MB_IND").toString())?"N":tempMap.get("MB_IND").toString());
				formObj.setCategoryZone(tempMap.get("ZONE_DESC")==null?"":tempMap.get("ZONE_DESC").toString());
				formObj.setEqwsInd(tempMap.get("EARTHQUAKE_WS_IND_CON")==null?"":tempMap.get("EARTHQUAKE_WS_IND_CON").toString());
				formObj.setWsThreat(tempMap.get("WS_THREAT_IND")==null?"":tempMap.get("WS_THREAT_IND").toString());
				formObj.setEqThreat(tempMap.get("EQ_THREAT")==null?"":tempMap.get("EQ_THREAT").toString());
				//formObj.setCommn(DropDownControllor.formatter(tempMap.get("RSK_COMM")==null?"":tempMap.get("RSK_COMM").toString()));
				//formObj.setBrokerage(DropDownControllor.formatter(tempMap.get("RSK_BROKERAGE")==null?"":tempMap.get("RSK_BROKERAGE").toString()));
				//formObj.setTax(DropDownControllor.formatter(tempMap.get("RSK_TAX")==null?"":tempMap.get("RSK_TAX").toString()));
				formObj.setAcqBonus(tempMap.get("RSK_BONUS_ID")==null?"":tempMap.get("RSK_BONUS_ID").toString());
				formObj.setAcqBonusPercentage(DropDownControllor.exchRateFormat(tempMap.get("RSK_NOCLAIMBONUS_PRCENT")==null?"":tempMap.get("RSK_NOCLAIMBONUS_PRCENT").toString()));
				formObj.setCommn(tempMap.get("RSK_COMM")==null?"":tempMap.get("RSK_COMM").toString());
				formObj.setBrokerage(tempMap.get("RSK_BROKERAGE")==null?"":tempMap.get("RSK_BROKERAGE").toString());
				formObj.setTax(tempMap.get("RSK_TAX")==null?"":tempMap.get("RSK_TAX").toString());
				formObj.setLossRecord(StringUtils.isBlank(tempMap.get("RSK_LOSS_RECORD")==null?"":tempMap.get("RSK_LOSS_RECORD").toString())?"N":tempMap.get("RSK_LOSS_RECORD").toString());
				formObj.setDgmsApproval(tempMap.get("RSK_DGM_APPROVAL")==null?"":tempMap.get("RSK_DGM_APPROVAL").toString());
				formObj.setUnderwriterCode(tempMap.get("RSK_UNDERWRITTER_CODE")==null?"":tempMap.get("RSK_UNDERWRITTER_CODE").toString());
				formObj.setUwRecommendation(tempMap.get("RSK_UW_RECOMMENDATION")==null?"":tempMap.get("RSK_UW_RECOMMENDATION").toString());
				formObj.setRemarks(tempMap.get("RSK_REMARKS")==null?"":tempMap.get("RSK_REMARKS").toString());
				formObj.setOthAccep(tempMap.get("RSK_OTH_ACCEP")==null?"":tempMap.get("RSK_OTH_ACCEP").toString());
				formObj.setReftoHO(StringUtils.isBlank(tempMap.get("RSK_REF_TO_HO")==null?"":tempMap.get("RSK_REF_TO_HO").toString())?"N":tempMap.get("RSK_REF_TO_HO").toString());
				formObj.setCuRsn(tempMap.get("CU_RSN")==null?"":tempMap.get("CU_RSN").toString());
				formObj.setAcqCost(DropDownControllor.formatter(tempMap.get("RSK_ACQUISTION_COST_OC")==null?"":tempMap.get("RSK_ACQUISTION_COST_OC").toString()));
				formObj.setAccusd(DropDownControllor.formatter(tempMap.get("RSK_ACQUISTION_COST_DC")==null?"":tempMap.get("RSK_ACQUISTION_COST_DC").toString()));
				//formObj.setOthercost(DropDownControllor.formatter(tempMap.get("RSK_OTHER_COST")==null?"":tempMap.get("RSK_OTHER_COST").toString()));
				//formObj.setAcqCostPer(DropDownControllor.formatter(tempMap.get("ACC_PERCENTAGE")==null?"":tempMap.get("ACC_PERCENTAGE").toString()));
				formObj.setOthercost(tempMap.get("RSK_OTHER_COST")==null?"":tempMap.get("RSK_OTHER_COST").toString());
				formObj.setAcqCostPer(tempMap.get("ACC_PERCENTAGE")==null?"":tempMap.get("ACC_PERCENTAGE").toString());
				formObj.setMlopYN(StringUtils.isBlank(tempMap.get("M_LOP")==null?"":tempMap.get("M_LOP").toString())?"N":tempMap.get("M_LOP").toString());
				formObj.setAlopYN(StringUtils.isBlank(tempMap.get("A_LOP")==null?"":tempMap.get("A_LOP").toString())?"N":tempMap.get("A_LOP").toString());
				formObj.setEndorsementDate(tempMap.get("ENDORSEMENT_DATE")==null?"":tempMap.get("ENDORSEMENT_DATE").toString());
				formObj.setLeader_Underwriter_country(tempMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":tempMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
				formObj.setLeader_Underwriter(tempMap.get("RSK_LEAD_UW")==null ? "" : tempMap.get("RSK_LEAD_UW").toString());
				formObj.setLeader_Underwriter_share(tempMap.get("RSK_LEAD_UW_SHARE")==null ? "" : tempMap.get("RSK_LEAD_UW_SHARE").toString());
				formObj.setCrestaStatus(tempMap.get("RSK_CREASTA_STATUS")==null?"":tempMap.get("RSK_CREASTA_STATUS").toString());
				formObj.setExclusion(tempMap.get("RSK_EXCLUSION")==null?"":tempMap.get("RSK_EXCLUSION").toString());
				
				//formObj.setContractListVal(tempMap.get("DATA_MAP_CONT_NO")==null?"":tempMap.get("DATA_MAP_CONT_NO").toString());
			}
			if("LCB".equalsIgnoreCase(formObj.getAcqBonus())){
				formObj.setAcqBonusName("Low Claim Bonus");
			}
			else if("NCB".equalsIgnoreCase(formObj.getAcqBonus())){
				formObj.setAcqBonusName("No Claim Bonus");
			}

			if(StringUtils.isNotBlank(formObj.getNoOfInst()) && Integer.parseInt(formObj.getNoOfInst())>0){
				List<String> instalList=new ArrayList<String>();
				for(int i=0;i<Integer.parseInt(formObj.getNoOfInst());i++){
					instalList.add(String.valueOf(i));
				}
				formObj.setInstalList(instalList);
			}
			args = new String[3];
			args[0] = formObj.getProposalNo();
			args[1] = "0";
			args[2] = formObj.getAmendId();
			query = getQuery(DBConstants.RISK_SELECT_VIEWINSTALMENTDATA);
			logger.info("Select Query=>" + query);
			logger.info("Args[]=>" +StringUtils.join(args,","));
			List<Map<String,Object>> instalmentList = this.mytemplate.queryForList(query,args);
			logger.info("List Size=>" +instalmentList.size());
			if (instalmentList != null) {
				List<String> finalList = new ArrayList<String>();
				List<String> preList = new ArrayList<String>();
				List<String> paymentdays = new ArrayList<String>();
				for (int number = 0; number < instalmentList.size(); number++) {
					Map<String,Object> insMap = (Map<String,Object>)instalmentList.get(number);
					finalList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					preList.add(insMap.get("MND_PREMIUM_OC")==null?"":DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC").toString()));
					paymentdays.add((insMap.get("PAYEMENT_DUE_DAY")==null?"":insMap.get("PAYEMENT_DUE_DAY").toString()));

					//formObj.getRequest().setAttribute("instalmentDate" + number, insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					//formObj.getRequest().setAttribute("installmentPremium" + number, DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
				}
				formObj.setInstalmentDateList(finalList);
				formObj.setInstallmentPremium(preList);
				formObj.setPaymentDueDays(paymentdays);
			}else{
				List<String> paymentdays = new ArrayList<String>();
				for (int k = 0; k < Integer.parseInt(formObj.getNoOfInst()); k++) {
						paymentdays.add(formObj.getReceiptofPayment());
				}
				formObj.setPaymentDueDays(paymentdays);
			}
			String qry =getQuery("GET_POSITION_MASTER_CON_MAP");
			args = new String[2];
			args[0] = formObj.getProposalNo();
			args[1] = formObj.getAmendId();
			formObj.setContractListVal(this.mytemplate.queryForObject(qry, args,String.class));
			
			if(StringUtils.isNotBlank(formObj.getContractListVal()) && !"None".equalsIgnoreCase(formObj.getContractListVal())) {
				String qrey = getQuery("GET_MAPPING_PROPOSAL_NO");
				args = new String[4];
				args[0] = formObj.getContractListVal();
				args[1] = "0";
				args[2] = formObj.getDepartmentId();
				args[3] = formObj.getYear();
				List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
				list2 = this.mytemplate.queryForList(qrey, args);
				if(list2.size()>0){
					for(int i=0;i<list2.size();i++){
						Map<String,Object> map =list2.get(i);
						formObj.setMappingProposal(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
						formObj.setMapingAmendId(map.get("AMEND_ID")==null?"":map.get("AMEND_ID").toString());
					}
				}
			}
			//if(StringUtils.isNotBlank(formObj.getNo_Insurer()) && Integer.parseInt(formObj.getNo_Insurer())>0 ) {
			getInsurarerDetails(formObj,true);
			//}
			GetRemarksDetails(formObj);
			GetCoverDeductableDetails(formObj);
			GetXolCoverDeductableDetails(formObj);
			if("Y".equalsIgnoreCase(formObj.getLossRecord())){
				getLossDEtails(formObj);
			}
			
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		} 	
		logger.info("viewMode() || Exit");
		return true;
	}

	public boolean showSecondPagedata(final FaculitivesBean formObj,FaculitivesBean beanObj) {
		logger.info("showSecondPagedata() || Enter");
		try{
			List<String> days=new ArrayList<String>();
			Object[] args=new String[4];
			args[0]=beanObj.getProposalNo();
			args[1]=beanObj.getProductId();
			args[2]=beanObj.getBranchCode();
			args[3]=beanObj.getProposalNo();
			String query=getQuery(DBConstants.FAC_SELECT_SHOW_SECOND_DATA);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+beanObj.getProposalNo());
			logger.info("Args[1]=>"+beanObj.getProductId());
			logger.info("Args[2]=>"+beanObj.getBranchCode());
			logger.info("Args[3]=>"+beanObj.getProposalNo());
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0) {
				Map<String,Object> tempMap = list.get(0);
				formObj.setProposalNo(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
				//formObj.setProfitCenterCode(tempMap.get("TMAS_PFC_NAME")==null?"":tempMap.get("TMAS_PFC_NAME").toString());
				formObj.setSubProfitCenter(tempMap.get("TMAS_SPFC_NAME")==null?"":tempMap.get("TMAS_SPFC_NAME").toString()); 
				formObj.setCedingCompany(tempMap.get("COMPANY")==null?"":tempMap.get("COMPANY").toString());
				formObj.setBroker(tempMap.get("BROKER")==null?"":tempMap.get("BROKER").toString());
				formObj.setUnderwriter(tempMap.get("RSK_UWYEAR")==null?"":tempMap.get("RSK_UWYEAR").toString());
				formObj.setInsuredName(tempMap.get("RSK_INSURED_NAME")==null?"":tempMap.get("RSK_INSURED_NAME").toString());
				formObj.setDepartClass(tempMap.get("TMAS_DEPARTMENT_NAME")==null?"":tempMap.get("TMAS_DEPARTMENT_NAME").toString());
				formObj.setEndttypename(tempMap.get("DETAIL_NAME")==null?"":tempMap.get("DETAIL_NAME").toString());
			}
			if(StringUtils.isNotBlank(formObj.getNoOfInst()) && Integer.parseInt(formObj.getNoOfInst())>0){
				List<String> instalList=new ArrayList<String>();
				for(int i=0;i<Integer.parseInt(formObj.getNoOfInst());i++){
					instalList.add(String.valueOf(i));
				}
				beanObj.setInstalList(instalList);
			}
			if(beanObj.getInstalList().size()>0){
				for(int i=0;i<beanObj.getInstalList().size();i++){
				if(null == beanObj.getPaymentDueDays() || beanObj.getPaymentDueDays().size()<=i){
					days.add(beanObj.getReceiptofPayment());
				}
				else{
					//if(StringUtils.isNotBlank(str))
					days.add(beanObj.getPaymentDueDays().get(i));
				}
				}
				beanObj.setPaymentDueDays(days);
			}else{
				List<String> paymentdays = new ArrayList<String>();
				for (int k = 0; k < Integer.parseInt(formObj.getNoOfInst()); k++) {
						paymentdays.add(formObj.getReceiptofPayment());
				}
				formObj.setPaymentDueDays(paymentdays);
			}
			if(StringUtils.isNotBlank(formObj.getNo_Insurer()) && Integer.parseInt(formObj.getNo_Insurer())>0 && (formObj.getRetrolList()==null || formObj.getRetrolList().size()==0)){
				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
				for(int i=1;i<Integer.parseInt(formObj.getNo_Insurer());i++){
					retroFinalList.add(getRetroContractDetailsList(formObj,2));
				}
				retroDupList.add(getRetroContractDetailsList(formObj, 3));
				beanObj.setRetroDupList(retroDupList);
				beanObj.setRetrolList(retroFinalList);
				logger.debug("size" + retroFinalList.size() + "}");
				
				/*List<String> retroFinalList=new ArrayList<String>();
				for(int i=0;i<Integer.parseInt(formObj.getNo_Insurer());i++){
					retroFinalList.add(String.valueOf(i));
				}
				beanObj.setUwYearValList(retroFinalList);
				logger.debug("size" + retroFinalList.size() + "}");*/
			}
			//GetRemarksDetails(beanObj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("showSecondPagedata() || Exit");
		return true;
	}
	public List<Map<String, Object>> getRetroContractDetailsList(FaculitivesBean beanObj,int flag){	
		logger.info("getRetroContractDetails() || Enter");
		String query="";
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		try{
			if(StringUtils.isEmpty(beanObj.getYear())&&flag==1){
				query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),beanObj.getInceptionDate(),beanObj.getBranchCode(),beanObj.getInceptionDate()});	
			}else if(StringUtils.isNotEmpty(beanObj.getYear())&&flag==2){
				if(StringUtils.isNotBlank(beanObj.getRetroType()) && "TR".equals(beanObj.getRetroType()) && "4".equals(beanObj.getProductId())){
					query = getQuery("fac.select.retroContDetTR");
					logger.info("Select Query=>"+query);
					logger.info("Product Code=>"+beanObj.getProductId());
					logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
					logger.info("UW Year=>"+beanObj.getYear());
					logger.info("Inception Date=>"+beanObj.getInceptionDate());
					logger.info("Branch Code=>"+beanObj.getBranchCode());
					list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),beanObj.getYear(),beanObj.getInceptionDate()});
					beanObj.setCedingCompanyList(list);	
				}else{
				query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getYear());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate()});
				}
			}
			else if(StringUtils.isNotEmpty(beanObj.getYear())&&flag==3){
				query = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getYear());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),"TR",beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),"TR",beanObj.getYear(),beanObj.getInceptionDate()});
			}
			
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getRetroContractDetails() || Exit");
		return list;	
	}
	public String getRetroContractDetails(FaculitivesBean beanObj) {
		logger.info("getRetroContractDetails() || Enter");
		String  Cedingco="";
		String query="";
		try{
			List<Map<String,Object>> list =null;
			//if(StringUtils.isBlank(beanObj.getYear())) {
			if("uwYear".equalsIgnoreCase(beanObj.getDropDown())) {
				query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),beanObj.getInceptionDate(),beanObj.getBranchCode(),beanObj.getInceptionDate()});
				beanObj.setUwYearList(list);
			}
			else if(StringUtils.isNotEmpty(beanObj.getYear())&&"Duplicate".equalsIgnoreCase(beanObj.getRetroDupMode())){
				query = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getYear());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate()});
				beanObj.setCedingCompanyList(list);
			}
			else {
				if(StringUtils.isNotBlank(beanObj.getRetroType()) && "TR".equals(beanObj.getRetroType()) && "4".equals(beanObj.getProductId())){
					query = getQuery("fac.select.retroContDetTR");
					logger.info("Select Query=>"+query);
					logger.info("Product Code=>"+beanObj.getProductId());
					logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
					logger.info("UW Year=>"+beanObj.getYear());
					logger.info("Inception Date=>"+beanObj.getInceptionDate());
					logger.info("Branch Code=>"+beanObj.getBranchCode());
					list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),beanObj.getYear(),beanObj.getInceptionDate()});
					beanObj.setCedingCompanyList(list);	
				}else{
				query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProductId());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getYear());
				logger.info("Inception Date=>"+beanObj.getInceptionDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProductId(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getYear(),beanObj.getInceptionDate()});
				beanObj.setCedingCompanyList(list);
				}
			}
			if(list!=null && list.size()>0){
				logger.info("List Size=>"+list.size());
				Map<String,Object> resMap;
				for(int i=0;i<list.size();i++){
					resMap = (Map<String,Object>)list.get(i); 
					if(i==(list.size()-1)){
						Cedingco+=resMap.get("CONTDET1").toString()+"~"+resMap.get("CONTDET2").toString();
					}else{
						Cedingco+=resMap.get("CONTDET1").toString()+"~"+resMap.get("CONTDET2").toString()+"~";	
					}
				}
			}
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getRetroContractDetails() || Exit");
		return Cedingco;	
	}
	
	public List<Map<String, Object>> getValidation(FaculitivesBean formObj,int mode)  {
		logger.info("getValidation() || Enter");
		String query="";
		List<Map<String,Object>> list=null;
		try{
			if(mode==1){
				query = getQuery(DBConstants.FAC_SELECT_RENEWALVALIDATION);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getInceptionDate());
				logger.info("Args[1]=>"+formObj.getRenewal_Contract_no());
				list = this.mytemplate.queryForList(query, new Object[] {formObj.getInceptionDate(),formObj.getRenewal_Contract_no()});
				/*if(list!=null&&list.size()>0)
			{
				Map map=(Map)list.get(0);
				if(mode==1)
				{
					result=(String)map.get("UW_YEAR");
				}else if(mode==2)
				{
					result=(String)map.get("EXPIRY_DATE");					
				}
			}*/
			}else{
				query = getQuery(DBConstants.FAC_SELECT_SPRETROVALIDATION);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getContractNo());
				list = this.mytemplate.queryForList(query, new Object[] {formObj.getContractNo()});
			}
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getValidation() || Exit");
		return list;
	}
	public boolean instalMentPremium(final FaculitivesBean beanObj) {
		logger.info("instalMentPremium() || Enter");
		try{
			String query = "SELECT MAX(AMEND_ID) FROM POSITION_MASTER WHERE PROPOSAL_NO=?";
			Object[] args = new String[1];
			args[0] = beanObj.getProposalNo();
			String endtNo = (String) this.mytemplate.queryForObject(query, args, String.class);

			query = "delete from TTRN_MND_INSTALLMENTS where PROPOSAL_NO=? and ENDORSEMENT_NO=?";
			args = new Object[2];
			args[0] = beanObj.getProposalNo();
			args[1] = endtNo;
			this.mytemplate.update(query,args);
			String insertQry = getQuery(DBConstants.RISK_INSERT_INSTALPREM);
			for (int i = 0; i < Integer.parseInt(beanObj.getNoOfInst()); i++) {
				Object[] obj = new Object[11];
				int res=0;
				obj[0] = i + 1;
				obj[1] = beanObj.getProposalNo();
				obj[2] = StringUtils.isEmpty(beanObj.getContractNo()) ? "0"	: beanObj.getContractNo();
				obj[3] = "0";
				obj[4] = endtNo;
				obj[5] = StringUtils.isEmpty(beanObj.getInstalmentDateList().get(i)) ? "" : beanObj.getInstalmentDateList().get(i);
				obj[6] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i)) ? "" : beanObj.getInstallmentPremium().get(i).replaceAll(",", "");
				obj[7] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i))|| StringUtils.isEmpty(beanObj.getUsCurrencyRate()) ? "0": (Double.parseDouble(beanObj.getInstallmentPremium().get(i).replaceAll(",", ""))/Double.parseDouble(beanObj.getUsCurrencyRate()));
				obj[8] =(beanObj.getPaymentDueDays()==null)?"":StringUtils.isEmpty(beanObj.getPaymentDueDays().get(i)) ? "" : beanObj.getPaymentDueDays().get(i);
				obj[9] = beanObj.getLoginid();
				obj[10] = beanObj.getBranchCode();
				logger.info("Query=>"+insertQry);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(insertQry,obj);
				logger.info("Result=>"+res);
			}
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("instalMentPremium() || Exit");
		return true;
	}
	public Object[] firstPageInsertAruguments(FaculitivesBean beanObj,final boolean flag,final boolean contract) throws ParseException {
		logger.info("firstPageInsertAruguments() || Enter");
		final Validation val=new Validation();
		Object[] args=new Object[64];
		if(beanObj.getProposalNo().equalsIgnoreCase("")){
			//args[0]=getMaxProposanlno(beanObj.getProductId(),beanObj.getBranchCode());
			//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
				args[0]=new DropDownControllor().getSequence("Proposal",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposalNo(),"");
			/*}else
			args[0]=new DropDownControllor().getPolicyNo("1",beanObj.getProductId(),beanObj.getBranchCode());*/
		}
		else
			args[0]=beanObj.getProposalNo();	
		beanObj.setProposalNo(args[0].toString());
		args[1]="0";
		args[2]=beanObj.getContractNo()==null?"":beanObj.getContractNo();
		args[3]=StringUtils.isEmpty(beanObj.getUsCurrencyRate())? "0": beanObj.getUsCurrencyRate();
		args[4]=StringUtils.isEmpty(beanObj.getProfitCenterCode())? "0": beanObj.getProfitCenterCode();
		args[5]=StringUtils.isEmpty(beanObj.getSubProfitCenter())? "0": beanObj.getSubProfitCenter();
		args[6]=StringUtils.isEmpty(beanObj.getMonth())? "": val.GetProcedureDate(beanObj.getMonth());
		args[7]=StringUtils.isEmpty(beanObj.getUnderwriter())? "": beanObj.getUnderwriter();
		args[8]=StringUtils.isEmpty(beanObj.getFacultativeDepartment())? "0": beanObj.getFacultativeDepartment();
		args[9]=StringUtils.isEmpty(beanObj.getCedingCompany())? "0":beanObj.getCedingCompany();
		args[10]=StringUtils.isEmpty(beanObj.getBroker())? "0":beanObj.getBroker();
		args[11]=StringUtils.isEmpty(beanObj.getInceptionDate())? "": val.GetProcedureDate(beanObj.getInceptionDate());
		args[12]=StringUtils.isEmpty(beanObj.getExpiryDate())? "":val.GetProcedureDate(beanObj.getExpiryDate());
		args[13]=StringUtils.isEmpty(beanObj.getAccountDate())?"":val.GetProcedureDate(beanObj.getAccountDate());
		args[14]=StringUtils.isEmpty(beanObj.getOriginalCurrency())? "0":beanObj.getOriginalCurrency();
		args[15]=StringUtils.isEmpty(beanObj.getTerritory())? "0":beanObj.getTerritory();
		args[16]=StringUtils.isEmpty(beanObj.getInsuredName())? "":beanObj.getInsuredName();
		args[17]=StringUtils.isEmpty(beanObj.getLocation())? "":beanObj.getLocation();
		args[18]=StringUtils.isEmpty(beanObj.getCity())? "":beanObj.getCity();
		args[19]=StringUtils.isEmpty(beanObj.getCedantsRet())? "0":beanObj.getCedantsRet();
		args[20]=StringUtils.isEmpty(beanObj.getNr())? "0":beanObj.getNr();
		args[21]=StringUtils.isEmpty(beanObj.getMaxiumlimit())? "0":beanObj.getMaxiumlimit();
		args[22]=StringUtils.isEmpty(beanObj.getDeductible())? "0":beanObj.getDeductible();
		args[23]=StringUtils.isEmpty(beanObj.getInterest())? "":beanObj.getInterest();
		args[24]=StringUtils.isEmpty(beanObj.getSpRetro())? "0":beanObj.getSpRetro();
		args[25]=StringUtils.isEmpty(beanObj.getPml())? "0":beanObj.getPml();
		args[26]=StringUtils.isEmpty(beanObj.getSipml())? "0":beanObj.getSipml();
		args[27]=StringUtils.isEmpty(beanObj.getSumInsured())? "0":beanObj.getSumInsured();
		args[28]=StringUtils.isEmpty(beanObj.getGwpi())? "0": beanObj.getGwpi();
		args[29]=StringUtils.isEmpty(beanObj.getPmll())? "0":beanObj.getPmll();
		args[30]=StringUtils.isEmpty(beanObj.getTpl())? "0":beanObj.getTpl();
		args[31]=StringUtils.isEmpty(beanObj.getShWt())? "0": beanObj.getShWt();
		if(!beanObj.getProStatus().equalsIgnoreCase("A")){
			args[32]="0";
		}
		else if(beanObj.getProStatus().equalsIgnoreCase("A")){
			args[32]=StringUtils.isEmpty(beanObj.getShSd())? "0": beanObj.getShSd();
		}
		args[33]=StringUtils.isEmpty(beanObj.getProductId()) ? "0":   beanObj.getProductId();
		args[34]=StringUtils.isEmpty(beanObj.getYear())? "0":   beanObj.getYear();
		if(flag){
			args[35]="P";
			args[36]="P";
		}
		else {
			if(beanObj.getProStatus().equalsIgnoreCase("0") ||  beanObj.getProStatus().equalsIgnoreCase("P") ){
				args[35]="P";
				args[36]="P";
			}else{
				args[35]=beanObj.getProStatus();
				args[36]=beanObj.getProStatus();
			}	
		}
		args[37]=StringUtils.isNotBlank(beanObj.getBaseLoginID())?beanObj.getBaseLoginID():beanObj.getLoginid();
		args[38]=StringUtils.isEmpty(beanObj.getNo_Insurer())?"0":beanObj.getNo_Insurer();
		args[39]=StringUtils.isEmpty(beanObj.getRenewal_Contract_no())?"":beanObj.getRenewal_Contract_no();
		//args[40]=getRenewalNo(beanObj);
		args[40]=beanObj.getRenewalStatus();
		args[41]=StringUtils.isEmpty(beanObj.getPremiumrate())?"0":beanObj.getPremiumrate();
		args[42]=beanObj.getBranchCode();
		args[43]=beanObj.getPolicyBranch();
		args[44]=StringUtils.isEmpty(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
		args[45]=StringUtils.isEmpty(beanObj.getSumInsuredOurShare())?"0": beanObj.getSumInsuredOurShare();
		args[46]=StringUtils.isEmpty(beanObj.getGwpiOurShare())? "0": beanObj.getGwpiOurShare();
		args[47]=StringUtils.isEmpty(beanObj.getPmlOurShare())? "0": beanObj.getPmlOurShare();
		args[48]=StringUtils.isEmpty(beanObj.getTplOurShare())? "0": beanObj.getTplOurShare();
		args[49]=beanObj.getLoginid();
		args[50]=StringUtils.isEmpty(beanObj.getDeductibleFacXol())?"0": beanObj.getDeductibleFacXol();
		args[51]=StringUtils.isEmpty(beanObj.getXolOC())?"0": beanObj.getXolOC();
		args[52]=StringUtils.isEmpty(beanObj.getXolOSOC())?"0": beanObj.getXolOSOC();
		args[53]=StringUtils.isEmpty(beanObj.getNoOfInst())?"0": beanObj.getNoOfInst();
		args[54]=StringUtils.isEmpty(beanObj.getModeOfTransport())?"0": beanObj.getModeOfTransport();
		args[55]=StringUtils.isEmpty(beanObj.getVesselName())?"": beanObj.getVesselName();
		args[56]=StringUtils.isEmpty(beanObj.getVesselAge())?"0": beanObj.getVesselAge();
		args[57]=StringUtils.isEmpty(beanObj.getLimitPerVesselOC())?"0": beanObj.getLimitPerVesselOC();
		args[58]=StringUtils.isEmpty(beanObj.getLimitPerLocationOC())?"0": beanObj.getLimitPerLocationOC();
		args[59]=StringUtils.isEmpty(beanObj.getType())?"": beanObj.getType();
		args[60]=StringUtils.isEmpty(beanObj.getCountryIncludedList())?"": beanObj.getCountryIncludedList();
		args[61]=StringUtils.isEmpty(beanObj.getCountryExcludedList())?"": beanObj.getCountryExcludedList();
		args[62]=StringUtils.isEmpty(beanObj.getContractListVal())?"": beanObj.getContractListVal();
		args[63]=StringUtils.isEmpty(beanObj.getXollayerNo())?"": beanObj.getXollayerNo();
		
		logger.info("Args[]=>"+StringUtils.join(args,","));	
		logger.info("firstPageInsertAruguments() || Exit");
		return args;
	}

	/*private String getMaxProposanlno(final String pid,final String branchCode) {
  			LogManager.info("FaculitivesBean getMaxProposanlno() || Enter");
			String query=getQuery(DBConstants.FAC_SELECT_MAXPRONO);
			LogManager.info("Query=>"+query);
			LogManager.info("Args[0]=>"+pid);
			LogManager.info("Args[1]=>"+branchCode);
			String result=this.mytemplate.queryForObject(query,new Object[]{pid,branchCode},String.class).toString();
			LogManager.info("Result=>"+result);
			LogManager.info("FaculitivesBean getMaxProposanlno() || Exit");
			return result;
	}*/

	public Object[] secondPageInsert(final FaculitivesBean beanObj) {
		logger.info("secondPageInsert() || Enter");
		Object[] args=new Object[34];
		args[0]=beanObj.getProposalNo()==null?"":beanObj.getProposalNo();
		args[1]=beanObj.getContractNo()==null?"":beanObj.getContractNo();
		args[2]=StringUtils.isEmpty(beanObj.getSumInsuredOurShare())? "0": beanObj.getSumInsuredOurShare();
		args[3]=StringUtils.isEmpty(beanObj.getGwpiOurShare())? "0": beanObj.getGwpiOurShare();
		args[4]=StringUtils.isEmpty(beanObj.getPmlOurShare())? "0": beanObj.getPmlOurShare();
		args[5]=StringUtils.isEmpty(beanObj.getTplOurShare())? "0": beanObj.getTplOurShare();
		args[6]=StringUtils.isEmpty(beanObj.getRiskGrade())? "": beanObj.getRiskGrade();
		args[7]=StringUtils.isEmpty(beanObj.getOccCode())? "": beanObj.getOccCode();
		args[8]=StringUtils.isEmpty(beanObj.getRiskDetail())? "": beanObj.getRiskDetail();
		args[9]=StringUtils.isEmpty(beanObj.getFireProt())? "": beanObj.getFireProt();
		args[10]=StringUtils.isEmpty(beanObj.getScope())? "": beanObj.getScope();
		args[11]=StringUtils.isEmpty(beanObj.getMbind())? "": beanObj.getMbind();
		args[12]=StringUtils.isEmpty(beanObj.getCategoryZone())? "": beanObj.getCategoryZone();
		args[13]=StringUtils.isEmpty(beanObj.getEqwsInd())? "0": beanObj.getEqwsInd();
		args[14]=StringUtils.isEmpty(beanObj.getWsThreat())? "0": beanObj.getWsThreat();
		args[15]=StringUtils.isEmpty(beanObj.getEqThreat())? "0": beanObj.getEqThreat();
		args[16]=StringUtils.isEmpty(beanObj.getCommn())? "0": beanObj.getCommn();
		args[17]=StringUtils.isEmpty(beanObj.getBrokerage())? "0": beanObj.getBrokerage();
		args[18]=StringUtils.isEmpty(beanObj.getTax())? "0": beanObj.getTax();
		args[19]=StringUtils.isEmpty(beanObj.getLossRecord())? "": beanObj.getLossRecord();
		args[20]=StringUtils.isEmpty(beanObj.getDgmsApproval())? "": beanObj.getDgmsApproval();
		args[21]=StringUtils.isEmpty(beanObj.getUnderwriterCode())? "": beanObj.getUnderwriterCode();
		args[22]=StringUtils.isEmpty(beanObj.getUwRecommendation())? "": beanObj.getUwRecommendation();
		args[23]=StringUtils.isEmpty(beanObj.getRemarks())? "": beanObj.getRemarks();
		args[24]=StringUtils.isEmpty(beanObj.getOthAccep())? "": beanObj.getOthAccep();
		args[25]=StringUtils.isEmpty(beanObj.getReftoHO())? "0": beanObj.getReftoHO();
		args[26]=StringUtils.isEmpty(beanObj.getAcqCost()) ? "0": beanObj.getAcqCost();
		args[27]=StringUtils.isEmpty(beanObj.getCu())? "0": beanObj.getCu();
		args[28]=StringUtils.isEmpty(beanObj.getCuRsn())? "": beanObj.getCuRsn();
		args[29]=StringUtils.isEmpty(beanObj.getOthercost())? "": beanObj.getOthercost();
		args[30]=StringUtils.isEmpty(beanObj.getMlopYN())? "": beanObj.getMlopYN();
		args[31]=StringUtils.isEmpty(beanObj.getAlopYN())? "": beanObj.getAlopYN();
		if("NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
		args[32]=StringUtils.isEmpty(beanObj.getAcqBonusPercentage())? "": beanObj.getAcqBonusPercentage();
		}
		else{
		args[32]="";
		}
		args[33]=StringUtils.isEmpty(beanObj.getAcqBonus())? "": beanObj.getAcqBonus();
		logger.info("Args[]=>"+StringUtils.join(args,","));
		logger.info("secondPageInsert() || Exit");
		return args;
	}

	private String dateFormat(String name){
		if(StringUtils.isNotBlank(name)) {
			String arr[]=name.split("/");
			return arr[1]+"/"+arr[0]+"/"+arr[2];
		}
		else {
			return name;
		}
	}

	public String getShortname(FaculitivesBean bean) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] { bean.getBranchCode()}, String.class);
		return Short;
	}

	public List<Map<String, Object>> getLowClaimBonusList(FaculitivesBean bean) {
		logger.info("getLowClaimBonusList() || Enter");
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		String query="";
		 List<String> bonusSno = new ArrayList<String>();
         List<String> bonusFrom = new ArrayList<String>();
         List<String> bonusTo = new ArrayList<String>();
         String proposalNo = bean.getProposalNo();
         List<String> bonusLowClaimBonus = new ArrayList<String>();
         Object args[]=null;
         int count =0;
		try{
				args = new Object[3];
				args[0] = proposalNo;
				args[1] = bean.getBranchCode();
				args[2] = bean.getAcqBonus();
					query =getQuery("BONUS_MAIN_SELECT");
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(args, ","));
					result = this.mytemplate.queryForList(query,args);
					
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               bean.setBonusTypeId(tempMap.get("LCB_TYPE")==null?"":tempMap.get("LCB_TYPE").toString());
		               bonusSno.add(tempMap.get("LCB_ID")==null?"":tempMap.get("LCB_ID").toString());
		               bonusFrom.add(tempMap.get("LCB_FROM")==null?"":DropDownControllor.formatter(tempMap.get("LCB_FROM").toString()));
		               bonusTo.add(tempMap.get("LCB_TO")==null?"":DropDownControllor.formatter(tempMap.get("LCB_TO").toString()));
		               bonusLowClaimBonus.add(tempMap.get("LCB_PERCENTAGE")==null?"":DropDownControllor.formatter(tempMap.get("LCB_PERCENTAGE").toString()));
		           }
	               bean.setBonusSNo(bonusSno);
	               bean.setBonusFrom(bonusFrom);
	               bean.setBonusTo(bonusTo);
	               bean.setBonusLowClaimBonus(bonusLowClaimBonus);
	               if("RD".equalsIgnoreCase(bean.getFlag()) && count<=0){
	               LowClaimBonusInser(bean);
	               }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getLowClaimBonusList() || Exit");
		return result;
	}

	

	public String LowClaimBonusInser(FaculitivesBean bean) {
		logger.info("LowClaimBonusInser() || Enter");
		try{
			MoveBonus(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("LowClaimBonusInser() || Exit");
		return null;
	}

	public void MoveDataToBonus(FaculitivesBean bean) {
		/*try{
			if(!"N".equalsIgnoreCase(bean.getEndorsementStatus())){
				getLowClaimBonusList(bean);
				MoveBonus(bean);
			}
				BonusDeleteRaw(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}

	private void MoveBonus(FaculitivesBean bean) {
		logger.info("MoveBonus() || Enter");
		try{
			if(StringUtils.isBlank(bean.getEndorsmentno())){
				bean.setEndorsmentno("0");
			}
	        deleteMaintable(bean);
	        String query =getQuery("BONUS_MAIN_INSERT");
			Object args[]=new Object[14];
			for(int i=0;i<bean.getBonusFrom().size();i++){
				if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) &&StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i)) ){
			           args[0] =bean.getProposalNo();
			           args[1] = bean.getContractNo();
			           args[2] = bean.getProductId();
			           args[3] = bean.getBonusTypeId();
			           args[4] = bean.getBonusFrom().get(i).replace(",", "");
			           args[5] = bean.getBonusTo().get(i).replace(",", "");
			           args[6] =bean.getBonusLowClaimBonus().get(i).replace(",", "");
			           args[7] = bean.getLoginid();
			           args[8] = bean.getBranchCode();
			           args[9] = bean.getBonusSNo().get(i).replace(",", "");
			           args[10] =bean.getAcqBonus();
			           args[11] = bean.getEndorsmentno();
			           args[12] =bean.getDepartmentId();
			           args[13] ="0";
			           logger.info("Query=>"+query);
			           logger.info("Args=>"+StringUtils.join(args, ","));
					   this.mytemplate.update(query,args);
					}
				
		}
	
	}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("MoveBonus() || Exit");
	}

	public void deleteMaintable(FaculitivesBean bean) {
		logger.info("deleteMaintable() || Enter");
		String query1="";
		Object arg[]=null;
		String acqBonus =bean.getAcqBonus();
		String type="";
		try{
			query1=getQuery("BONUS_PREVIOUS_TYPE_CHECK");
			 arg = new Object[3];
			 arg[0] = bean.getProposalNo();
			 arg[1] = bean.getBranchCode();
			 arg[2] = "0";
			 type = (String) this.mytemplate.queryForObject(query1, arg,String.class);
			 if(!type.equalsIgnoreCase(acqBonus)){
				 acqBonus=type;
			 }
			if("".equalsIgnoreCase(bean.getEndorsmentno())){
				query1 =getQuery("BONUS_MAIN_DELETE");
				 arg = new Object[4];
				 arg[0] = bean.getProposalNo();
				 arg[1] = bean.getBranchCode();
				 arg[2] = acqBonus;
				 arg[3] = "0";
			}
			else{
			 query1 =getQuery("BONUS_MAIN_DELETE2");
			 arg = new Object[5];
			 arg[0] = bean.getProposalNo();
			 arg[1] = bean.getEndorsmentno();
			 arg[2] = bean.getBranchCode();
			 arg[3] = acqBonus;
			 arg[4] = "0";
			}
			logger.info("Query=>"+query1);
			logger.info("Args=>"+StringUtils.join(arg, ","));
			this.mytemplate.update(query1,arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("deleteMaintable() || Exit");
	}

	public void BonusDeleteRaw(FaculitivesBean bean) {
		/*String query1="";
		Object arg[]=null;
		try{
			 query1 =getQuery("BONUS_ARCH_DELETE");
			 arg = new Object[3];
			 arg[0] = bean.getProposalNo();
			 arg[1] = bean.getBranchCode();
			 arg[2] =bean.getAcqBonus();
			this.mytemplate.update(query1,arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}

	public int getBonusListCount(FaculitivesBean bean) {
		logger.info("getBonusListCount() || Enter");
		String query ="";
		Object args[]=null;
		int result=0;
		try{
			if(StringUtils.isBlank(bean.getEndorsmentno())){
				bean.setEndorsmentno("0");
			}
			query =getQuery("BONUS_COUNT_MAIN");
			args = new Object[5];
			args[0] = bean.getProposalNo();
			args[1] = bean.getBranchCode();
			args[2] = bean.getAcqBonus();
			args[3] = bean.getEndorsmentno();
			args[4] ="0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			result = this.mytemplate.queryForInt(query,args);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	logger.info("getBonusListCount() || Exit");
		return result;
	}

	public void insetNOClaimBonusMainTable(FaculitivesBean bean) {
		logger.info("insetNOClaimBonusMainTable() || Enter");
		try{
			if(StringUtils.isBlank(bean.getEndorsmentno())){
				bean.setEndorsmentno("0");
			}
			deleteMaintable(bean);
			//BonusDeleteRaw(bean);
			String query =getQuery("BONUS_MAIN_INSERT");
			Object args[]=new Object[14];
		           args[0] =bean.getProposalNo();
		           args[1] = bean.getContractNo();
		           args[2] = bean.getProductId();
		           args[3] = "";
		           args[4] = "";
		           args[5] = "";
		           args[6] ="";
		           args[7] = bean.getLoginid();
		           args[8] = bean.getBranchCode();
		           args[9] = "1";
		           args[10] =bean.getAcqBonus();
		           args[11] = bean.getEndorsmentno();
		           args[12] = bean.getDepartmentId();
		           args[13] = "0";
		           logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("insetNOClaimBonusMainTable() || Exit");
	}
	private void insertBonusDetails(FaculitivesBean beanObj) {
			logger.info("insertBonusDetails() || Enter");
			if(!"LCB".equalsIgnoreCase(beanObj.getAcqBonus())){
					insetNOClaimBonusMainTable(beanObj);
				}
			String query = getQuery("UPDATE_CONTRACT_DETAILS");
			Object args[]=new Object[6];
			args[1] =beanObj.getProposalNo();
			args[0] = beanObj.getContractNo();
			args[2] = beanObj.getBranchCode();
			args[3] = beanObj.getAcqBonus();
			args[4] =  beanObj.getEndorsmentno();
			args[5] =  "0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
			logger.info("insertBonusDetails() || Exit");
	}
	private void insertCrestaMaintable(FaculitivesBean bean) {
		logger.info("insertCrestaMaintable() || Enter");
		String query ="";
		Object obj[] =null;
		try {
			int count=getCrestaCount(bean);
			if(count<=0){
				    query=getQuery("MOVE_TO_CRESTA_MAIN_TABLE");
				    obj = new Object[12];
					obj[0]=bean.getContractNo();
					obj[1]=bean.getProposalNo();
					obj[2]=StringUtils.isEmpty(bean.getEndorsmentno())?"0":bean.getEndorsmentno();;
					obj[3]=bean.getDepartmentId();
					obj[4]="";
					obj[5]="";
					obj[6]="";
					obj[7]="";
					obj[8]="";
					obj[9]=bean.getBranchCode();
					obj[10]="";
					obj[11]= "";
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(obj, ","));
					this.mytemplate.update(query,obj);
			}
			 query = getQuery("CREATA_CONTRACT_UPDATE");
			 obj = new Object[4];
			 obj[0]=bean.getContractNo();
			 obj[1]=bean.getProposalNo();
			 obj[2]=StringUtils.isEmpty(bean.getEndorsmentno())?"0":bean.getEndorsmentno();;
			 obj[3]=bean.getBranchCode();
			 logger.info("Query=>"+query);
			 logger.info("Args=>"+StringUtils.join(obj, ","));
			 this.mytemplate.update(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("insertCrestaMaintable() || Exit");
		
	}
	public int getCrestaCount(FaculitivesBean bean) {
		logger.info("getCrestaCount() || Enter");
		int count=0;
		try {
			String query=getQuery("GET_CRESTA_DETAIL_COUNT");
			Object[] obj=new Object[3];
			obj[0]=bean.getProposalNo();
			obj[1]=StringUtils.isEmpty(bean.getEndorsmentno())?"0":bean.getEndorsmentno();;
			obj[2]=bean.getBranchCode();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			count=this.mytemplate.queryForInt(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getCrestaCount() || Exit");
		return count;
	}
	
	public Object getosvalue(final String limitOrigCur,final String ExchangeRate) {
		logger.info("getosvalue() || Enter");
		double output=0.0;
		try{
			double origCountryVal=0.0;
			if(limitOrigCur!=null){
				String val = limitOrigCur.replaceAll(",", "");
				if (!("".equalsIgnoreCase(val))&& Double.parseDouble(val) != 0) {
					origCountryVal = Double.parseDouble(val) / Double.parseDouble(ExchangeRate);
					final DecimalFormat myFormatter = new DecimalFormat("###.##");
					output = Double.parseDouble(myFormatter.format(origCountryVal));
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("getosvalue() || Exit");
		return output;
	}

	public String doUploadDocDetails(List<Object[]> list, Object[] args) {
		logger.info("doUploadDocDetails() || Enter");
		String result="";
		try{
			String sql=getQuery(DBConstants.UPLOAD_INSERTDOCDETAILS);
			logger.info("Sql=>"+sql);
			
			for(Object[] obj:list)
			{
				logger.info("Obj[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(sql,obj);
				logger.info("Result=>"+res);	
			}
			String query =getQuery("DOC_CON_UPDATE");
			this.mytemplate.update(query,args);
		}catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
			result = e.getMessage();
		}
		logger.info("doUploadDocDetails() || Exit");
		return result;
	}

	public boolean GetShareValidation(FaculitivesBean bean) {
		logger.info("GetShareValidation() || Enter");
		boolean result=false;
		try {
			String query=getQuery("GET_SIGN_SHARE_PRODUCT1");
			String out=this.mytemplate.queryForObject(query, new Object[]{bean.getProposalNo()},String.class);
			if(Double.parseDouble(out)+Double.parseDouble(bean.getLeader_Underwriter_share())>100){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("GetShareValidation() || Exit");
		return result;
	}
}
