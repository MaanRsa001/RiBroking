package com.maan.dao.impl;

import com.maan.bean.RiskDetailsBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.RetroDAO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetroDAOImpl extends MyJdbcTemplate implements RetroDAO{
	Logger logger = LogUtil.getLogger(this.getClass());
	public boolean firstInsert(final RiskDetailsBean beanObj, final String pid,	boolean saveFlag, final boolean amendId)  {
		boolean savFlg = false,ChkSavFlg = false;
		try {
			String sql = "";
			Object[] args=null;
			if (saveFlag) {
				ChkSavFlg = checkEditSaveModeMethod(beanObj);
				if (ChkSavFlg){
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,getMaxAmednId(beanObj.getProposal_no()));
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
					if("5".equalsIgnoreCase(pid)){
						sql = sql + " AND RSK_LAYER_NO = ? ";
					}
					logger.info("Query=>"+sql);
					int updateCount = this.mytemplate.update(sql, args);
					logger.info("Result=>"+updateCount);
					args[1]=(Integer.parseInt((String)args[30])+1)+"";
					if (updateCount > 0) {
						savFlg = true;
					} else {
						return false;
					}
				} else {
					args = getFirstPageInsertAruguments(beanObj, pid, amendId);
					sql = getQuery(DBConstants.RISK_INSERT_ISAMENDIDPROTREATY);
					logger.info("insert qry=>" + sql);
					int res=this.mytemplate.update(sql,args);
					logger.info("Result=>" + res);
					beanObj.setContractGendration("Your Proposal Number :"+ beanObj.getProposal_no());
				}
			} else {
				String maxAmendID = getMaxAmednId(beanObj.getProposal_no());
				if(maxAmendID.equalsIgnoreCase(beanObj.getAmendId())){
					args = getFirstPageInsertAruguments(beanObj, pid, amendId);
					sql = getQuery(DBConstants.RISK_INSERT_ISAMENDIDPROTREATY);
					logger.info("insert qry=>" + sql);
					int res=this.mytemplate.update(sql,args);
					logger.info("Result=>" + res);
				}else{
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,maxAmendID);
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
					if("3".equalsIgnoreCase(pid)||"5".equalsIgnoreCase(pid)){
						sql = sql + " AND RSK_LAYER_NO = ? ";
					}
					logger.info("Query=>"+sql);
					int updateCount = this.mytemplate.update(sql, args);
					logger.info("Result=>"+updateCount);
					args[1]=(Integer.parseInt((String)args[30])+1)+"";
				}
			}
			savFlg = insertRiskProposal(beanObj,saveFlag,pid,ChkSavFlg,amendId,(String)args[1]);
			InsertRemarkDetails(beanObj);
			savFlg = true;
		} catch (Exception e) {
			saveFlag = false;
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}

	private boolean checkEditSaveModeMethod(final RiskDetailsBean beanObj) {
		boolean editSaveMode = false;
		try {
			Object[] args = new Object[1];
			args[0] = beanObj.getProposal_no();
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKPRONO);
			logger.info("Query=>"+selectQry);
			List<Map<String, Object>> list = this.mytemplate.queryForList(selectQry, args);
			logger.info("Result Size=>"+list.size());
			if (list.size() == 0) {
				editSaveMode = false;
			} else {
				editSaveMode = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return editSaveMode;
	}

	private String getMaxAmednId(final String proposalNo) {
		String result ="";
		try{
			String query=getQuery(DBConstants.RISK_SELECT_GETMAXENDORSENO);
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+proposalNo);
			result = this.mytemplate.queryForObject(query, new String[]{proposalNo}, String.class).toString();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	public String[] getFirstPageEditSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		args = new String[52];
		args[0] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[1] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0" : beanObj.getProfit_Center();
		args[2] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0" : beanObj.getSubProfit_center();
		args[3] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[4] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[5] = StringUtils.isEmpty(beanObj.getBroker()) ? "0" : beanObj.getBroker();
		args[6] = StringUtils.isEmpty(beanObj.getTreatyName_type()) ? "" : beanObj.getTreatyName_type();
		args[7] = StringUtils.isEmpty(beanObj.getMonth()) ? "" : beanObj.getMonth();
		args[8] = StringUtils.isEmpty(beanObj.getUwYear()) ? "0" : beanObj.getUwYear();
		args[9] = StringUtils.isEmpty(beanObj.getUnderwriter()) ? "" : beanObj.getUnderwriter();
		args[10] = StringUtils.isEmpty(beanObj.getIncepDate()) ? "" : beanObj.getIncepDate();
		args[11] = StringUtils.isEmpty(beanObj.getExpDate()) ? "" : beanObj.getExpDate();
		args[12] = StringUtils.isEmpty(beanObj.getAccDate()) ? "" : beanObj.getAccDate();
		args[13] = StringUtils.isEmpty(beanObj.getOrginalCurrency()) ? "0" : beanObj.getOrginalCurrency();
		args[14] = StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : beanObj.getExchRate();
		args[15] = StringUtils.isEmpty(beanObj.getBasis()) ? "0" : beanObj.getBasis();
		args[16] = StringUtils.isEmpty(beanObj.getPnoc()) ? "" : beanObj.getPnoc();
		args[17] = StringUtils.isEmpty(beanObj.getRiskCovered()) ? "" : beanObj.getRiskCovered();
		args[18] = StringUtils.isEmpty(beanObj.getTerritoryscope()) ? "" : beanObj.getTerritoryscope();
		args[19] = StringUtils.isEmpty(beanObj.getTerritory()) ? "" : beanObj.getTerritory();
		if ("4".equalsIgnoreCase(pid)) {
			args[20] = StringUtils.isEmpty(beanObj.getProStatus()) ? "" : beanObj.getProStatus();
			args[21] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
			args[22] = StringUtils.isEmpty(beanObj.getAccountingPeriod()) ? "0"	: beanObj.getAccountingPeriod();
			args[23] = StringUtils.isEmpty(beanObj.getReceiptofStatements()) ? "0" : beanObj.getReceiptofStatements();
			args[24] = StringUtils.isEmpty(beanObj.getReceiptofPayment()) ? "0"	: beanObj.getReceiptofPayment();
		} else if ("5".equalsIgnoreCase(pid)) {
			args[20] = StringUtils.isEmpty(beanObj.getProStatus()) ? ""	: beanObj.getProStatus();
			args[21] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
			args[22] = "0";
			args[23] = "0";
			args[24] = "0";
			args[43] = beanObj.getLayerNo();
		}
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0"	: beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0" : beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "" : beanObj.getInsuredName();
		args[29]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[30]=StringUtils.isEmpty(beanObj.getTreatyType()) ? "0"	: beanObj.getTreatyType();
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[36] = beanObj.getLoginId();
		args[37] = beanObj.getBranchCode();
		args[38] =StringUtils.isEmpty(beanObj.getCountryIncludedList()) ? ""	:beanObj.getCountryIncludedList();
		args[39] =StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	: beanObj.getCountryExcludedList();
		args[40] =StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] =StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
		args[43] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[44] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[45] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[46] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[47] =StringUtils.isEmpty(beanObj.getCessionExgRate()) ? ""	:beanObj.getCessionExgRate();
		args[48] =StringUtils.isEmpty(beanObj.getFixedRate()) ? ""	:beanObj.getFixedRate();
		args[49] =StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		args[50] = beanObj.getProposal_no();
		args[51]=endNo;
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	public String[] getFirstPageInsertAruguments(final RiskDetailsBean beanObj, final String pid, final boolean amendId) {
		String[] args= new String[57];
		if (amendId) {
			args[0] = beanObj.getProposal_no();
			args[1] =(Integer.parseInt(getMaxAmednId(beanObj.getProposal_no()))+1)+"";
			args[26] = beanObj.getContNo();
		}else{
			args[26] = "";
			beanObj.setProposal_no(getMaxProposanlno(pid,beanObj.getBranchCode(),beanObj.getRetroType(),beanObj.getDepartmentId()));
			args[0] = beanObj.getProposal_no();
			args[1] = "0";
		}
		if (pid.equalsIgnoreCase("4")) {
			args[2] = "0";
			args[27] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
			args[28] = StringUtils.isEmpty(beanObj.getAccountingPeriod()) ? "0" : beanObj.getAccountingPeriod();
			args[29] = StringUtils.isEmpty(beanObj.getReceiptofStatements()) ? "0" : beanObj.getReceiptofStatements();
			args[30] = StringUtils.isEmpty(beanObj.getReceiptofPayment()) ? "0"	: beanObj.getReceiptofPayment();
		}else if ("5".equalsIgnoreCase(pid)) {
			args[2] = beanObj.getLayerNo();
			args[27] = "";
			args[28] = "0";
			args[29] = "0";
			args[30] = "0";
		}
		args[3] = pid;
		args[4] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[5] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0" : beanObj.getProfit_Center();
		args[6] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0" : beanObj.getSubProfit_center();
		args[7] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[8] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[9] = StringUtils.isEmpty(beanObj.getBroker()) ? "0" : beanObj.getBroker();
		args[10] = StringUtils.isEmpty(beanObj.getTreatyName_type()) ? "" : beanObj.getTreatyName_type();
		args[11] = StringUtils.isEmpty(beanObj.getMonth()) ? "" : beanObj.getMonth();
		args[12] = StringUtils.isEmpty(beanObj.getUwYear()) ? "0" : beanObj.getUwYear();
		args[13] = StringUtils.isEmpty(beanObj.getUnderwriter()) ? "" : beanObj.getUnderwriter();
		args[14] = StringUtils.isEmpty(beanObj.getIncepDate()) ? "" : beanObj.getIncepDate();
		args[15] = StringUtils.isEmpty(beanObj.getExpDate()) ? "" : beanObj.getExpDate();
		args[16] = StringUtils.isEmpty(beanObj.getAccDate()) ? "" : beanObj.getAccDate();
		args[17] = StringUtils.isEmpty(beanObj.getOrginalCurrency()) ? "0" : beanObj.getOrginalCurrency();
		args[18] = StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : beanObj.getExchRate();
		args[19] = StringUtils.isEmpty(beanObj.getBasis()) ? "0" : beanObj.getBasis();
		args[20] = StringUtils.isEmpty(beanObj.getPnoc()) ? "" : beanObj.getPnoc();
		args[21] = StringUtils.isEmpty(beanObj.getRiskCovered()) ? "0"	: beanObj.getRiskCovered();
		args[22] = StringUtils.isEmpty(beanObj.getTerritoryscope()) ? "" : beanObj.getTerritoryscope();
		args[23] = StringUtils.isBlank(beanObj.getTerritory())?"" :beanObj.getTerritory();
		args[24] = StringUtils.isEmpty(beanObj.getProStatus()) ? "0" : beanObj.getProStatus();
		args[25] = "";
		args[31] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0": beanObj.getM_d_InstalmentNumber();
		args[32] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0"	: beanObj.getNoRetroCess();
		args[33] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0" : beanObj.getRetroType();
		args[34] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "" : beanObj.getInsuredName();
		args[35] = StringUtils.isEmpty(beanObj.getRenewal_contract_no()) ? "": beanObj.getRenewal_contract_no();
		args[36]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[37]=StringUtils.isEmpty(beanObj.getTreatyType()) ? "0"	: beanObj.getTreatyType();
		args[38]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[39]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[40]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[41]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[42]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[43] = beanObj.getLoginId();
		args[44] = beanObj.getBranchCode();
		args[45] =StringUtils.isEmpty(beanObj.getCountryIncludedList()) ? ""	:beanObj.getCountryIncludedList();
		args[46] = StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	: beanObj.getCountryExcludedList();
		args[47] =StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[48] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[49] =StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
		args[50] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[51] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[52] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[53] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[54] =StringUtils.isEmpty(beanObj.getCessionExgRate()) ? ""	:beanObj.getCessionExgRate();
		args[55] =StringUtils.isEmpty(beanObj.getFixedRate()) ? ""	:beanObj.getFixedRate();
		args[56]=StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		//args[56] =StringUtils.isEmpty(beanObj.getDummyCon()) ? ""	:beanObj.getDummyCon();
		logger.info("Args[]=>" +StringUtils.join(args,","));
		return args;
	}

	public String getMaxProposanlno(String pid,String branchCode,String retroType, String deptId) {
		String result="";
		try{
			//if("06".equalsIgnoreCase(branchCode)){
			result = new DropDownControllor().getSequence("Proposal", "SR".equalsIgnoreCase(retroType) ? "5" : "4", deptId, branchCode,"","");
			/*}else
			result=new DropDownControllor().getPolicyNo("1",pid,branchCode);*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	private boolean insertRiskProposal(final RiskDetailsBean beanObj,final boolean saveFlag, final String pid,final boolean ChekmodeFlag,boolean amendId,final String amednIdvalue) {
		boolean InsertFlag = false;
		try {
			String updateQry = "",insertQry = "",endom;
			Object[] obj=null;
			String maxAmendId="0";
			if(!"0".endsWith(amednIdvalue))
				maxAmendId=(Integer.parseInt(amednIdvalue)-1)+"";
			if (saveFlag) {
				if (ChekmodeFlag) {
					endom=getQuery(DBConstants.RISK_SELECT_MAXENDOM);
					logger.info("Query=>"+endom);
					logger.info("Args[0]=>"+beanObj.getProposal_no());
					String endtNo=(String)this.mytemplate.queryForObject(endom, new Object[]{beanObj.getProposal_no()},String.class);
					logger.info("Result=>"+endtNo);
					obj = getProposalSaveEditModeQuery(beanObj, pid,endtNo);
					if(pid.equalsIgnoreCase("4"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
					else if(pid.equalsIgnoreCase("5"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
					logger.info("updateQry=>" + updateQry);
					if (this.mytemplate.update(updateQry, obj) > 0) {
						if(StringUtils.isNotBlank(beanObj.getContNo())) {
							beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
						}
						else if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
							beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
						}
					}
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					obj = updateHomePositionMasterAruguments(beanObj, pid,"0");
					updateQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
					logger.info("updateQry=>" + updateQry);
					if (this.mytemplate.update(updateQry, obj) > 0) {
						if (pid.equalsIgnoreCase("4")) {
							if(StringUtils.isNotBlank(beanObj.getContNo())) {
								beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
							}
							else if (beanObj.getProStatus().equalsIgnoreCase("A")|| beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
								beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
							}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
								beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
							}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
								beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
							}
						}else if (pid.equalsIgnoreCase("5")) {
							if(StringUtils.isNotBlank(beanObj.getContNo())) {
								beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
							}
							if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
								beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
							}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
								beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
							}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
								beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no() +" and Layer No : "+beanObj.getLayerNo());
							}
						}
					}
				} else {
					obj = getFirstPageSecondTableAruguments(beanObj, pid, amednIdvalue, amendId);
					if(pid.equalsIgnoreCase("4"))
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO24RSKPROPOSAL);
					else if(pid.equalsIgnoreCase("5"))
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO35RSKPROPOSAL);
					logger.info("Insert Qry=>" + insertQry);
					int res=this.mytemplate.update(insertQry, obj);
					logger.info("Result=>"+res);
					String renewalStatus = getRenewalStatus(beanObj);
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					obj = insertHomePositionMasterAruguments(beanObj, pid,amednIdvalue, amendId,renewalStatus);
					insertQry = getQuery(DBConstants.RISK_INSERT_POSITIONMASTER);
					logger.info("Insert Qry=>" + insertQry);
					res=this.mytemplate.update(insertQry, obj);
					logger.info("Result=>"+res);
					if (pid.equalsIgnoreCase("4")) {
						if (beanObj.getProStatus().equalsIgnoreCase("A")|| beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
							beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
						}
					}else if (pid.equalsIgnoreCase("5")) {
						if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
						}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
						}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
							beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no() +" and Layer No : "+beanObj.getLayerNo());
						}
					}
				}
			} else {
				if (!ChekmodeFlag) {
					if (maxAmendId.equalsIgnoreCase(beanObj.getAmendId())){
						if(pid.equalsIgnoreCase("4"))
							insertQry = getQuery(DBConstants.RISK_INSERT_PRO24RSKPROPOSAL);
						else if(pid.equalsIgnoreCase("5"))
							insertQry = getQuery(DBConstants.RISK_INSERT_PRO35RSKPROPOSAL);
						logger.info("Insert Qry=>" + insertQry);
						obj = getFirstPageSecondTableInsertAruguments(beanObj,pid, amednIdvalue, amendId);
					}else{
						if(pid.equalsIgnoreCase("4"))
							insertQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
						else if(pid.equalsIgnoreCase("5"))
							insertQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
						logger.info("Update Qry=>" + insertQry);
						obj = getProposalSaveEditModeQuery(beanObj, pid,maxAmendId);
					}
					if (this.mytemplate.update(insertQry, obj) > 0) {
						InsertFlag = true;
					}
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					if(maxAmendId.equalsIgnoreCase(beanObj.getAmendId())){
						String renewalStatus = getRenewalStatus(beanObj);
						insertQry = getQuery(DBConstants.RISK_INSERT_POSITIONMASTER);
						logger.info("insertQry=>" + insertQry);
						obj = insertHomePositionMasterAruguments(beanObj, pid,amednIdvalue, amendId,renewalStatus);

					}else{
						insertQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
						logger.info("updateQry=>" + insertQry);
						obj = updateHomePositionMasterAruguments(beanObj, pid,maxAmendId);
					}
					int insertCount = this.mytemplate.update(insertQry, obj);
					logger.info("Result=>" + insertCount);
					if (insertCount > 0){
						InsertFlag = true;
					}
					if (beanObj.getProStatus().equalsIgnoreCase("R")) {
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
					}
					String proposalno="";
					if (StringUtils.isNotEmpty(beanObj.getLayerProposalNo())) {
						proposalno = beanObj.getLayerProposalNo();
					} else {
						proposalno = beanObj.getProposal_no();
					}
					this.showSecondpageEditItems(beanObj, pid, proposalno);
				}
			}
		} catch (Exception e) {
			InsertFlag = false;
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();

		}
		return InsertFlag;
	}
	public Object[] getProposalSaveEditModeQuery(final RiskDetailsBean beanObj, final String pid,String endNo) {
		Object[] obj=null;
		if (pid.equalsIgnoreCase("4")) {
			obj = new Object[51];
			if("TR".equalsIgnoreCase(beanObj.getRetroType())){
			obj[0] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
			obj[1] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
			}
			else{
				obj[0] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
				obj[1] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
				}
			obj[2] = StringUtils.isEmpty(beanObj.getEpi_origCur()) ? "0" : beanObj.getEpi_origCur();
			obj[3] = StringUtils.isEmpty(beanObj.getEpi_origCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
			obj[4] = StringUtils.isEmpty(beanObj.getOurEstimate()) ? "0" : beanObj.getOurEstimate();
			obj[7] = StringUtils.isEmpty(beanObj.getXlCost()) ? "0" : beanObj.getXlCost();
			obj[8] = StringUtils.isEmpty(beanObj.getXlCost()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
			obj[9] = StringUtils.isEmpty(beanObj.getCedReten()) ? "0" : beanObj.getCedReten();
			obj[5] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi();
			obj[6] = StringUtils.isEmpty(beanObj.getEpi()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[10] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0" : beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[11] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[11] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0"	: beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[11] = "0";
			} else {
				obj[11] = "0";
			}
			obj[12] =StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
			obj[13] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[14] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[15] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[16] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0" : beanObj.getLimitOurShare();
			obj[17] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[18] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0" : beanObj.getEpiAsPerOffer();
			obj[19] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[20] =StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0" : beanObj.getEpiAsPerShare();
			obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
			obj[22] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0"	: beanObj.getXlcostOurShare();
			obj[23] = StringUtils.isEmpty(beanObj.getXlcostOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
			obj[24] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[25] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[26] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[27] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[28] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
			obj[29] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
			obj[30] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
			obj[31] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
			if("TR".equalsIgnoreCase(beanObj.getRetroType())){
			obj[32] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
			obj[33] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
			}
			else{
				obj[32] =StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml()) ? "0": beanObj.getFaclimitOrigCurPml();
				obj[33] = StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCurPml(), beanObj.getExchRate());
				}
			obj[34] =StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) ? "0": beanObj.getLimitOrigCurPmlOS();
			obj[35] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPmlOS(), beanObj.getExchRate());
			obj[36] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) ? "0": beanObj.getTreatyLimitsurplusOCPml();
			obj[37] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOCPml(), beanObj.getExchRate());
			obj[38] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) ? "0": beanObj.getTreatyLimitsurplusOCPmlOS();
			obj[39] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOCPmlOS(), beanObj.getExchRate());
			obj[40] =StringUtils.isEmpty(beanObj.getEpipml()) ? "0": beanObj.getEpipml();
			obj[41] = StringUtils.isEmpty(beanObj.getEpipml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpipml(), beanObj.getExchRate());
			obj[42] =StringUtils.isEmpty(beanObj.getEpipmlOS()) ? "0": beanObj.getEpipmlOS();
			obj[43] = StringUtils.isEmpty(beanObj.getEpipmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpipmlOS(), beanObj.getExchRate());
			obj[44]=beanObj.getDepartId();
			obj[45]=beanObj.getLoginId();
			obj[46]=beanObj.getBranchCode();
			obj[47] = StringUtils.isEmpty(beanObj.getPml()) ? "" : beanObj.getPml();
			obj[48] = StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0.00" : beanObj.getPmlPercent();
			obj[49] = beanObj.getProposal_no();
			obj[50] = endNo;
		}
		if ("5".equalsIgnoreCase(pid)) {
			obj = new Object[35];
			logger.info("Exchange Rate" + beanObj.getExchRate());
			obj[0] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
			obj[1] = StringUtils.isEmpty(beanObj.getLimitOrigCur())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
			obj[2] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi();
			obj[3] = StringUtils.isEmpty(beanObj.getEpi()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[4] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0" : beanObj.getShareWritt();
			obj[5] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0" : beanObj.getSharSign();
			obj[6] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[7] = StringUtils.isEmpty(beanObj.getSubPremium()) ? "0"	: beanObj.getSubPremium();
			obj[8] = StringUtils.isEmpty(beanObj.getSubPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getSubPremium(), beanObj.getExchRate());
			obj[9] = StringUtils.isEmpty(beanObj.getXlPremium()) ? "0"	: beanObj.getXlPremium();
			obj[10] = StringUtils.isEmpty(beanObj.getXlPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlPremium(), beanObj.getExchRate());
			obj[11] = StringUtils.isEmpty(beanObj.getPortfoloCovered()) ? "0" : beanObj.getPortfoloCovered();
			obj[12] = StringUtils.isEmpty(beanObj.getDeduc_hunPercent()) ? "0" : beanObj.getDeduc_hunPercent();
			obj[13] = StringUtils.isEmpty(beanObj.getDeduc_hunPercent()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getDeduc_hunPercent(),beanObj.getExchRate());
			obj[14] = StringUtils.isEmpty(beanObj.getM_dPremium()) ? "0" : beanObj.getM_dPremium();
			obj[15] = StringUtils.isEmpty(beanObj.getM_dPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getM_dPremium(), beanObj.getExchRate());
			obj[16] = StringUtils.isEmpty(beanObj.getAdjRate()) ? "0" : beanObj.getAdjRate();
			obj[17] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[18] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[19] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0" : beanObj.getLimitOurShare();
			obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[21] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0"	: beanObj.getEpiAsPerOffer();
			obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[23] =StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
			obj[24] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[25] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[26] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[27] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0" : beanObj.getLimitPerLocationOC();
			obj[28] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[29] =StringUtils.isEmpty(beanObj.getEgnpiOffer()) ? "0"	: beanObj.getEgnpiOffer();
			obj[31] = StringUtils.isEmpty(beanObj.getEgnpiOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpiOffer(), beanObj.getExchRate());
			obj[30] =StringUtils.isEmpty(beanObj.getOurAssessment()) ? "0"	: beanObj.getOurAssessment();
			obj[32] = beanObj.getLoginId();
			obj[33] = beanObj.getBranchCode();
			obj[34] = beanObj.getProposal_no();
			obj[35]=endNo==null?"0":endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object getDesginationCountry(final String limitOrigCur, final String ExchangeRate) {
		double output=0.0;
		try{
			double origCountryVal=0.0;
			if(limitOrigCur!=null){
				if (!("".equalsIgnoreCase(limitOrigCur)) && Double.parseDouble(limitOrigCur) != 0) {
					origCountryVal = Double.parseDouble(limitOrigCur) / Double.parseDouble(ExchangeRate);
					final DecimalFormat myFormatter = new DecimalFormat("###.##");
					output = Double.parseDouble(myFormatter.format(origCountryVal));
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return output;
	}

	public Object[] updateHomePositionMasterAruguments(final RiskDetailsBean beanObj, final String pid, final String maxAmdId) {
		Object[] obj = new Object[19];
		obj[0] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
		obj[1] = "";
		obj[2] = pid;
		obj[3] = beanObj.getDepartId();
		obj[4] = beanObj.getCedingCo();
		obj[5] = beanObj.getUwYear();
		obj[6] = beanObj.getMonth();
		obj[7] = beanObj.getAccDate();
		obj[8] = beanObj.getIncepDate();
		obj[9] = beanObj.getExpDate();
		obj[10] = beanObj.getProStatus();
		if (beanObj.getContNo() != null && !"0".equals(beanObj.getContNo().replaceAll(",", "")) && !"".equals(beanObj.getContNo().replaceAll(",", "")))
			obj[11] = "A";
		else if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")) {
			obj[11] = "P";
		} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
			obj[11] = "R";
		} else if("N".equalsIgnoreCase(beanObj.getProStatus())) {
			obj[11] = "N";
		}else{
			obj[11] = "P";
		}
		obj[12] = beanObj.getBroker();
		obj[13] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[14] = beanObj.getLoginId();
		obj[15] =StringUtils.isBlank(beanObj.getDummyCon())?"":beanObj.getDummyCon(); 
		obj[16] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[17] = beanObj.getProposal_no();
		obj[18] = maxAmdId;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] getFirstPageSecondTableAruguments(final RiskDetailsBean beanObj, final String pid, final Object args2, final boolean amendId) {
		Object[] obj=null;
		if (pid.equalsIgnoreCase("2")||pid.equalsIgnoreCase("4")) {
			obj = new Object[38];
		}else if ("3".equalsIgnoreCase(pid)||"5".equalsIgnoreCase(pid)){
			obj = new Object[32];
		}
		if (amendId) {
			obj[0] = beanObj.getProposal_no();
			obj[1] = args2;
		} else {
			obj[0] = beanObj.getProposal_no();
			obj[1] = "0";
		}
		if("TR".equalsIgnoreCase(beanObj.getRetroType())){
			obj[3] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
			obj[4] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
			}
			else{
				obj[3] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
				obj[4] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
				}
		if (pid.equalsIgnoreCase("4")) {
			obj[2] = "0";
			obj[5] = StringUtils.isEmpty(beanObj.getEpi_origCur()) ? "0" : beanObj.getEpi_origCur();
			obj[6] = StringUtils.isEmpty(beanObj.getEpi_origCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
			obj[7] = StringUtils.isEmpty(beanObj.getOurEstimate()) ? "0" : beanObj.getOurEstimate();
			obj[10] = StringUtils.isEmpty(beanObj.getXlCost()) ? "0" : beanObj.getXlCost();
			obj[11] = StringUtils.isEmpty(beanObj.getXlCost()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
			obj[12] = StringUtils.isEmpty(beanObj.getCedReten()) ? "0" : beanObj.getCedReten();
			obj[8] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi();
			obj[9] = StringUtils.isEmpty(beanObj.getEpi()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[13] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0" : beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[14] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[14] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0" : beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[14] = "0";
			} else {
				obj[14] = "0";
			}
			obj[15] = StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
			obj[16] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[17] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[19] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0" : beanObj.getLimitOurShare();
			obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0" : beanObj.getEpiAsPerOffer();
			obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[23] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0" : beanObj.getEpiAsPerShare();
			obj[24] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
			obj[25] = StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0" : beanObj.getXlcostOurShare();
			obj[26] = StringUtils.isEmpty(beanObj.getXlcostOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
			obj[27] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[28] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[29] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[30] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[31] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
			obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
			obj[33] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
			obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
			obj[35]="";//sub class
			obj[36]=beanObj.getLoginId();
			obj[37]=beanObj.getBranchCode();
		}

		if ("5".equalsIgnoreCase(pid)) {
			obj[2] = beanObj.getLayerNo();
			obj[5] = StringUtils.isEmpty(beanObj.getXlPremium()) ? "0" : beanObj.getXlPremium();
			obj[6] = StringUtils.isEmpty(beanObj.getXlPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlPremium(), beanObj.getExchRate());
			obj[7] = StringUtils.isEmpty(beanObj.getDeduc_hunPercent()) ? "0" : beanObj.getDeduc_hunPercent();
			obj[10] = StringUtils.isEmpty(beanObj.getM_dPremium()) ? "0" : beanObj.getM_dPremium();
			obj[11] = StringUtils.isEmpty(beanObj.getM_dPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getM_dPremium(), beanObj.getExchRate());
			obj[14] = StringUtils.isEmpty(beanObj.getAdjRate()) ? "0" : beanObj.getAdjRate();
			obj[15] = StringUtils.isEmpty(beanObj.getPortfoloCovered()) ? "0" : beanObj.getPortfoloCovered();
			obj[16] = StringUtils.isEmpty(beanObj.getSubPremium()) ? "0" : beanObj.getSubPremium();
			obj[17] = StringUtils.isEmpty(beanObj.getSubPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getSubPremium(), beanObj.getExchRate());
			obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[19] = StringUtils.isEmpty(beanObj.getDeduc_hunPercent()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getDeduc_hunPercent(), beanObj.getExchRate());
			obj[20] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[21] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[8] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi();
			obj[9] = StringUtils.isEmpty(beanObj.getEpi()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[12] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0" : beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[13] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[13] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0" : beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[13] = "0";
			} else {
				obj[13] = "0";
			}
			obj[22] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0" : beanObj.getLimitOurShare();
			obj[23] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[24] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0" : beanObj.getEpiAsPerShare();
			obj[25] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
			obj[26] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0" : beanObj.getMd_premium_our_service();
			obj[27] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[28] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[29] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[30] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[31] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	private String getRenewalStatus(final RiskDetailsBean beanObj) {
		String result="";
		try{
			if(StringUtils.isNotBlank(beanObj.getContNo())){
				String query=getQuery(DBConstants.RISK_SELECT_GETRENEWALSTATUS);
				logger.info("Query=>"+query);
				logger.info("args[0]=>"+beanObj.getProposal_no());
				result =(String)this.mytemplate.queryForObject(query,new String[]{beanObj.getProposal_no()},String.class);
				logger.info("Result=>"+result);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	public Object[] insertHomePositionMasterAruguments(final RiskDetailsBean beanObj, final String pid,	final Object args2, final boolean amendId,String renewalStatus) {
		Object[] obj = new Object[26];
		if (amendId) {
			obj[1] = beanObj.getContNo();
			obj[2] = args2;
			obj[16] = beanObj.getBaseLayer();
		} else {

			obj[1] = "0";
			obj[2] = "0";
			obj[16] = beanObj.getLayerProposalNo();
		}
		obj[0] = beanObj.getProposal_no();
		obj[3] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
		obj[4] = "";
		obj[5] = pid;
		obj[6] = beanObj.getDepartId();
		obj[7] = beanObj.getCedingCo();
		obj[8] = beanObj.getUwYear();
		obj[9] = beanObj.getMonth();
		obj[10] = beanObj.getAccDate();
		obj[11] = beanObj.getIncepDate();
		obj[12] = beanObj.getExpDate();
		obj[13] = beanObj.getProStatus();
		if (amendId) {
			obj[14] = "A";
			obj[18] = renewalStatus;
		} else {
			obj[14] = "P";
			obj[18] = "1";
		}
		obj[15] = StringUtils.isNotBlank(beanObj.getBaseLoginID())?beanObj.getBaseLoginID():beanObj.getLoginId();
		//obj[16] = beanObj.getLayerProposalNo();
		obj[17] = StringUtils.isEmpty(beanObj.getRenewal_contract_no()) ? "": beanObj.getRenewal_contract_no();
		obj[19] = beanObj.getBroker();
		obj[20] = beanObj.getBranchCode();
		obj[21] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[22] = beanObj.getLoginId();
		obj[23] = "";
		obj[24] =StringUtils.isBlank(beanObj.getDummyCon())?"":beanObj.getDummyCon(); 
		obj[25] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] getFirstPageSecondTableInsertAruguments(final RiskDetailsBean beanObj, final String pid,final Object args2, final boolean amendId) {
		Object[] obj=null;

		if (pid.equalsIgnoreCase("4")) {
			obj = new Object[38];
		} else if ("5".equalsIgnoreCase(pid)) {
			obj = new Object[32];
		}
		if (amendId) {
			obj[1] = args2;
		} else {
			obj[1] = "0";
		}
		obj[0] = beanObj.getProposal_no();
		if("TR".equalsIgnoreCase(beanObj.getRetroType())){
			obj[3] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
			obj[4] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
			}
			else{
				obj[3] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
				obj[4] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
				}
		if (pid.equalsIgnoreCase("4")) {
			obj[2] = "0";
			obj[5] = beanObj.getEpi_origCur();
			obj[6] = getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
			obj[7] =StringUtils.isBlank(beanObj.getOurEstimate())?"0": beanObj.getOurEstimate();
			obj[10] = beanObj.getXlCost();
			obj[11] = getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getEpi())?"0": beanObj.getEpi();
			obj[9] = getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[12] = StringUtils.isBlank(beanObj.getCedReten())?"0":beanObj.getCedReten();
			obj[13] = beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[14] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[14] = beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[14] = "0";
			} else {
				obj[14] = "0";
			}
			obj[15] = StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
			obj[16] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[17] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[19] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0"	: beanObj.getLimitOurShare();
			obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0" : beanObj.getEpiAsPerOffer();
			obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[23] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0"	: beanObj.getEpiAsPerShare();
			obj[24] = StringUtils.isEmpty(beanObj.getEpiAsPerShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
			obj[25] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0"	: beanObj.getXlcostOurShare();
			obj[26] = StringUtils.isEmpty(beanObj.getXlcostOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
			obj[27] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[28] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[29] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[30] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[31] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
			obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
			obj[33] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
			obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
			obj[35]="";
			obj[36]=beanObj.getLoginId();
			obj[37]=beanObj.getBranchCode();
		}
		if ("5".equalsIgnoreCase(pid)) {
			obj[2] = beanObj.getLayerNo();
			obj[5] = beanObj.getXlPremium();
			obj[6] = getDesginationCountry(beanObj.getXlPremium(), beanObj.getExchRate());
			obj[7] = beanObj.getDeduc_hunPercent();
			obj[10] = beanObj.getM_dPremium();
			obj[11] = getDesginationCountry(beanObj.getM_dPremium(), beanObj.getExchRate());
			obj[14] = beanObj.getAdjRate();
			obj[15] = beanObj.getPortfoloCovered();
			obj[16] = beanObj.getSubPremium();
			obj[17] = getDesginationCountry(beanObj.getSubPremium(), beanObj.getExchRate());
			obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[19] = getDesginationCountry(beanObj.getDeduc_hunPercent(), beanObj.getExchRate());
			obj[20] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[21] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[8] = beanObj.getEpi();
			obj[9] = getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[12] = beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[13] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[13] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0" : beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[13] = "0";
			} else {
				obj[13] = "0";
			}
			obj[22] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0"	: beanObj.getLimitOurShare();
			obj[23] = StringUtils.isEmpty(beanObj.getLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[24] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0"	: beanObj.getEpiAsPerOffer();
			obj[25] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[26] =StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
			obj[27] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[28] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0" : beanObj.getLimitPerVesselOC();
			obj[29] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[30] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[31] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	private boolean showSecondpageEditItems(RiskDetailsBean beanObj,final String pid, final String proposalNo){
		boolean savFlg = false;
		try{
			String selectQry="";
			Object[] args = new Object[3];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = proposalNo;
			if (pid.equalsIgnoreCase("4")) {
				selectQry = getQuery(DBConstants.RISK_SELECT_GETEDITMODESECPAGEDATA);
				logger.info("Select Query=>" + selectQry);
				logger.info("Args[0]..[2]=>" + proposalNo);
				List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
				logger.info("List<Map<String, Object>> Size=>" + res.size());
				Map<String, Object> resMap = null;
				if(res!=null && res.size()>0)
					resMap = (Map<String, Object>)res.get(0);
				if (resMap != null) {
					if (resMap.get("RSK_LIMIT_OS_OC") != null) {
						beanObj.setLimitOurShare(resMap.get("RSK_LIMIT_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_OC").toString());
						beanObj.setLimitOSViewOC(DropDownControllor.formatter(resMap.get("RSK_LIMIT_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_OC").toString()));
					}
					if (resMap.get("RSK_EPI_OSOF_OC") != null) {
						beanObj.setEpiAsPerOffer(resMap.get("RSK_EPI_OSOF_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_OC").toString());
						beanObj.setEpiOSViewOC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOF_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_OC").toString()));
					}
					if (resMap.get("RSK_EPI_OSOE_OC") != null) {
						beanObj.setEpiAsPerShare(resMap.get("RSK_EPI_OSOE_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOE_OC").toString());
						beanObj.setEpiOSOEViewOC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOE_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOE_OC").toString()));
					}
					if (resMap.get("RSK_XLCOST_OS_OC") != null) {
						beanObj.setXlcostOurShare(resMap.get("RSK_XLCOST_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_XLCOST_OS_OC").toString());
						beanObj.setXlCostViewOC(DropDownControllor.formatter(resMap.get("RSK_XLCOST_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_XLCOST_OS_OC").toString()));
					}
					if (resMap.get("RSK_LIMIT_OS_DC") != null) {
						beanObj.setLimitOSViewDC(DropDownControllor.formatter(resMap.get("RSK_LIMIT_OS_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_DC").toString()));
					}
					if (resMap.get("RSK_EPI_OSOF_DC") != null) {
						beanObj.setEpiOSViewDC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOF_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_DC").toString()));
					}
					if (resMap.get("RSK_EPI_OSOE_DC") != null) {
						beanObj.setEpiOSOEViewDC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOE_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOE_DC").toString()));
					}
					if (resMap.get("RSK_XLCOST_OS_DC") != null) {
						beanObj.setXlCostViewDC(DropDownControllor.formatter(resMap.get("RSK_XLCOST_OS_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_XLCOST_OS_DC").toString()));
					}
					if (resMap.get("RSK_COMM_QUOTASHARE") != null) {
						beanObj.setCommissionQ_S(resMap.get("RSK_COMM_QUOTASHARE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_COMM_QUOTASHARE").toString());
					}
					if (resMap.get("RSK_COMM_SURPLUS") != null) {
						beanObj.setCommission_surp(resMap.get("RSK_COMM_SURPLUS").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_COMM_SURPLUS").toString());
					}
					if (resMap.get("RSK_OVERRIDER_PERC") != null) {
						beanObj.setOverRidder(resMap.get("RSK_OVERRIDER_PERC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_OVERRIDER_PERC").toString());
					}
					if (resMap.get("RSK_BROKERAGE") != null) {
						beanObj.setBrokerage(resMap.get("RSK_BROKERAGE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_BROKERAGE").toString());
					}
					if (resMap.get("RSK_TAX") != null) {
						beanObj.setTax(resMap.get("RSK_TAX").toString().equalsIgnoreCase("0") ? "0"	: resMap.get("RSK_TAX").toString());
					}
					if (resMap.get("RSK_ACQUISTION_COST_OC") != null) {
						beanObj.setAcquisition_Cost(resMap.get("RSK_ACQUISTION_COST_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACQUISTION_COST_OC").toString());
					}
					if (resMap.get("RSK_PROFIT_COMM") != null) {
						beanObj.setShare_Profit_Commission(resMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PROFIT_COMM").toString());
					}
					if (resMap.get("RSK_MANAGEMENT_EXPENSES") != null) {
						beanObj.setManagement_Expenses(resMap.get("RSK_MANAGEMENT_EXPENSES").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_MANAGEMENT_EXPENSES").toString());
					}
					if (resMap.get("RSK_LOSS_CARRYFORWARD") != null) {
						beanObj.setLossC_F(resMap.get("RSK_LOSS_CARRYFORWARD").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LOSS_CARRYFORWARD").toString());
					}
					if (resMap.get("RSK_PREMIUM_RESERVE") != null) {
						beanObj.setPremium_Reserve(resMap.get("RSK_PREMIUM_RESERVE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PREMIUM_RESERVE").toString());
					}
					if (resMap.get("RSK_LOSS_RESERVE") != null) {
						beanObj.setLoss_reserve(resMap.get("RSK_LOSS_RESERVE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LOSS_RESERVE").toString());
					}
					if (resMap.get("RSK_INTEREST") != null) {
						beanObj.setInterest(resMap.get("RSK_INTEREST").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_INTEREST").toString());
					}
					if (resMap.get("RSK_CASHLOSS_LMT_OC") != null) {
						beanObj.setCash_Loss_Limit(resMap.get("RSK_CASHLOSS_LMT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_CASHLOSS_LMT_OC").toString());
					}
					if (resMap.get("RSK_PF_INOUT_PREM") != null) {
						beanObj.setPortfolio_inout_Premium(resMap.get("RSK_PF_INOUT_PREM").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PF_INOUT_PREM").toString());
					}
					if (resMap.get("RSK_PF_INOUT_LOSS") != null) {
						beanObj.setPortfolio_inout_Loss(resMap.get("RSK_PF_INOUT_LOSS").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PF_INOUT_LOSS").toString());
					}
					if (resMap.get("RSK_LOSSADVICE") != null) {
						beanObj.setLoss_Advise(resMap.get("RSK_LOSSADVICE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LOSSADVICE").toString());
					}
					if (resMap.get("RSK_LEAD_UW") != null) {
						beanObj.setLeader_Underwriter(resMap.get("RSK_LEAD_UW").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LEAD_UW").toString());
					}
					if (resMap.get("RSK_LEAD_UW_SHARE") != null) {
						beanObj.setLeader_Underwriter_share(resMap.get("RSK_LEAD_UW_SHARE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LEAD_UW_SHARE").toString());
					}
					beanObj.setAccounts(resMap.get("RSK_ACCOUNTS")==null?"":resMap.get("RSK_ACCOUNTS").toString());
					beanObj.setCrestaStatus(resMap.get("RSK_CREASTA_STATUS") == null ? "" : resMap.get("RSK_CREASTA_STATUS").toString());
					beanObj.setEvent_limit(resMap.get("RSK_EVENT_LIMIT_OC") == null ? "0" : resMap.get("RSK_EVENT_LIMIT_OC").toString());
					beanObj.setAggregate_Limit(resMap.get("RSK_AGGREGATE_LIMIT_OC") == null ? "0" : resMap.get("RSK_AGGREGATE_LIMIT_OC").toString());
					beanObj.setOccurrent_Limit(resMap.get("RSK_OCCURRENT_LIMIT_OC") == null ? "0" : resMap.get("RSK_OCCURRENT_LIMIT_OC").toString());
					beanObj.setExclusion(resMap.get("RSK_EXCLUSION")==null?"":resMap.get("RSK_EXCLUSION").toString());
					beanObj.setRemarks(resMap.get("RSK_REMARKS")==null?"":resMap.get("RSK_REMARKS").toString());
					beanObj.setUnderwriter_Recommendations(resMap.get("RSK_UW_RECOMM")==null?"":resMap.get("RSK_UW_RECOMM").toString());
					beanObj.setGms_Approval(resMap.get("RSK_GM_APPROVAL")==null?"":resMap.get("RSK_GM_APPROVAL").toString());
					//beanObj.setProfit_commission(resMap.get("RSK_SHARE_PROFIT_COMMISSION")==null?"":resMap.get("RSK_SHARE_PROFIT_COMMISSION").toString());
					beanObj.setSlideScaleCommission(resMap.get("RSK_SLADSCALE_COMM") == null ? "" : resMap.get("RSK_SLADSCALE_COMM").toString());
					beanObj.setLossParticipants(resMap.get("RSK_LOSS_PART_CARRIDOR") == null ? "" : resMap.get("RSK_LOSS_PART_CARRIDOR").toString());
					beanObj.setCommissionSubClass(resMap.get("RSK_COMBIN_SUB_CLASS") == null ? "" : resMap.get("RSK_COMBIN_SUB_CLASS").toString());
					beanObj.setLeader_Underwriter_country(resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY") == null ? "" : resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
					beanObj.setOrginalacqcost(resMap.get("RSK_INCLUDE_ACQ_COST") == null ? "" : resMap.get("RSK_INCLUDE_ACQ_COST").toString());
					beanObj.setOuracqCost(resMap.get("RSK_OUR_ASS_ACQ_COST") == null ? "" : resMap.get("RSK_OUR_ASS_ACQ_COST").toString());
					beanObj.setOurassessmentorginalacqcost(resMap.get("RSK_OUR_ACQ_OUR_SHARE_OC") == null ? "" : resMap.get("RSK_OUR_ACQ_OUR_SHARE_OC").toString());
					beanObj.setProfitCommission(resMap.get("RSK_PRO_NOTES") == null ? "" : resMap.get("RSK_PRO_NOTES").toString());
					beanObj.setLosscommissionSubClass(resMap.get("RSK_LOSS_COMBIN_SUB_CLASS") == null ? "" : resMap.get("RSK_LOSS_COMBIN_SUB_CLASS").toString());
					beanObj.setSlidecommissionSubClass(resMap.get("RSK_SLIDE_COMBIN_SUB_CLASS") == null ? "" : resMap.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString());
					beanObj.setCrestacommissionSubClass(resMap.get("RSK_CRESTA_COMBIN_SUB_CLASS") == null ? "" : resMap.get("RSK_CRESTA_COMBIN_SUB_CLASS").toString());
					beanObj.setManagementExpenses(resMap.get("RSK_PRO_MANAGEMENT_EXP") == null ? "" : resMap.get("RSK_PRO_MANAGEMENT_EXP").toString());
					beanObj.setCommissionType(resMap.get("RSK_PRO_COMM_TYPE") == null ? "" : resMap.get("RSK_PRO_COMM_TYPE").toString());
					beanObj.setProfitCommissionPer(resMap.get("RSK_PRO_COMM_PER") == null ? "" : resMap.get("RSK_PRO_COMM_PER").toString());
					beanObj.setSetup(resMap.get("RSK_PRO_SET_UP") == null ? "" : resMap.get("RSK_PRO_SET_UP").toString());
					beanObj.setSuperProfitCommission(resMap.get("RSK_PRO_SUP_PRO_COM") == null ? "" : resMap.get("RSK_PRO_SUP_PRO_COM").toString());
					beanObj.setLossCarried(resMap.get("RSK_PRO_LOSS_CARY_TYPE") == null ? "" : resMap.get("RSK_PRO_LOSS_CARY_TYPE").toString());
					beanObj.setLossyear(resMap.get("RSK_PRO_LOSS_CARY_YEAR") == null ? "" : resMap.get("RSK_PRO_LOSS_CARY_YEAR").toString());
					beanObj.setProfitCarried(resMap.get("RSK_PRO_PROFIT_CARY_TYPE") == null ? "" : resMap.get("RSK_PRO_PROFIT_CARY_TYPE").toString());
					beanObj.setProfitCarriedForYear(resMap.get("RSK_PRO_PROFIT_CARY_YEAR") == null ? "" : resMap.get("RSK_PRO_PROFIT_CARY_YEAR").toString());
					beanObj.setFistpc(resMap.get("RSK_PRO_FIRST_PFO_COM") == null ? "" : resMap.get("RSK_PRO_FIRST_PFO_COM").toString());
					beanObj.setProfitMont(resMap.get("RSK_PRO_FIRST_PFO_COM_PRD") == null ? "" : resMap.get("RSK_PRO_FIRST_PFO_COM_PRD").toString());
					beanObj.setSubProfitMonth(resMap.get("RSK_PRO_SUB_PFO_COM_PRD") == null ? "" : resMap.get("RSK_PRO_SUB_PFO_COM_PRD").toString());
					beanObj.setSubpc(resMap.get("RSK_PRO_SUB_PFO_COM") == null ? "" : resMap.get("RSK_PRO_SUB_PFO_COM").toString());
					beanObj.setSubSeqCalculation(resMap.get("RSK_PRO_SUB_SEQ_CAL") == null ? "" : resMap.get("RSK_PRO_SUB_SEQ_CAL").toString());
					beanObj.setCommissionQ_SYN(resMap.get("RSK_COMMISSION_QS_YN") == null ? "" : resMap.get("RSK_COMMISSION_QS_YN").toString());
					beanObj.setCommission_surpYN(resMap.get("RSK_COMMISSION_SUR_YN") == null ? "" : resMap.get("RSK_COMMISSION_SUR_YN").toString());
					beanObj.setOverRidderYN(resMap.get("RSK_OVERRIDE_YN") == null ? "" : resMap.get("RSK_OVERRIDE_YN").toString());
					beanObj.setBrokerageYN(resMap.get("RSK_BROKARAGE_YN") == null ? "" : resMap.get("RSK_BROKARAGE_YN").toString());
					beanObj.setTaxYN(resMap.get("RSK_TAX_YN") == null ? "" : resMap.get("RSK_TAX_YN").toString());
					beanObj.setOthercostYN(resMap.get("RSK_OTHER_COST_YN") == null ? "" : resMap.get("RSK_OTHER_COST_YN").toString());
					beanObj.setCeedODIYN(resMap.get("RSK_CEED_ODI_YN") == null ? "" : resMap.get("RSK_CEED_ODI_YN").toString());
					beanObj.setLocRate(resMap.get("RSK_RATE")==null?"":resMap.get("RSK_RATE").toString());
					beanObj.setRetroCommissionType(resMap.get("RSK_COMMISSION_TYPE")==null?"":resMap.get("RSK_COMMISSION_TYPE").toString());
					if (resMap.get("RSK_OTHER_COST") != null) {
						beanObj.setOthercost(resMap.get("RSK_OTHER_COST").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_OTHER_COST").toString());
					}else{
						beanObj.setOthercost("0");
					}
					if ("2".equalsIgnoreCase(pid))
						beanObj.setAcqCostPer((Double.parseDouble(beanObj.getCommissionQ_S())+Double.parseDouble(beanObj.getCommission_surp())+Double.parseDouble(beanObj.getOverRidder())+Double.parseDouble(beanObj.getBrokerage())+Double.parseDouble(beanObj.getTax())+Double.parseDouble(beanObj.getOthercost()))+"");
					savFlg = true;
				}

				args = new String[1];
				args[0] = beanObj.getProposal_no();
				selectQry = getQuery(DBConstants.RISK_SELECT_GETQUOTASHARE);
				logger.info("Select Query" + selectQry);
				logger.info("args[0]=>"+ args[0]);
				List<Map<String, Object>> res1 = this.mytemplate.queryForList(selectQry,args);
				logger.info("List<Map<String, Object>> Size=>" + res1.size());
				Map<String, Object> res1Map = null;
				if(res1!=null && res1.size()>0)
					res1Map = (Map<String, Object>)res1.get(0);
				if(res1Map!=null){
					beanObj.setPremiumQuotaShare(res1Map.get("RSK_PREMIUM_QUOTA_SHARE")==null?"":res1Map.get("RSK_PREMIUM_QUOTA_SHARE").toString());
					beanObj.setPremiumSurplus(res1Map.get("RSK_PREMIUM_SURPULS")==null?"":res1Map.get("RSK_PREMIUM_SURPULS").toString());
				}
			}
			if ("5".equalsIgnoreCase(pid)) {
				args = new String[4];
				args[0] = proposalNo;
				args[1] = proposalNo;
				args[2] = proposalNo;
				args[3] = beanObj.getLayerNo();
				selectQry = getQuery(DBConstants.RISK_SELECT_GETEDITMODESECPAGEPRO3DATA);
				logger.info("selectQry " + selectQry);
				logger.info("Args[0]..[2]=>" + proposalNo);
				logger.info("Args[3]=>" +  beanObj.getLayerNo());
				List<Map<String, Object>> resList = this.mytemplate.queryForList(selectQry, args);
				logger.info("List<Map<String, Object>> Size=>" + resList.size());
				Map<String, Object> resMap = null;
				if(resList!=null && resList.size()>0)
					resMap = (Map<String, Object>)resList.get(0);
				if(resMap!=null && resMap.size()>0){
					for (int i = 0; i < resMap.size(); i++) {
						if (resMap.get("RSK_LIMIT_OS_OC") != null) {
							beanObj.setLimitOurShare(resMap.get("RSK_LIMIT_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_OC").toString());
							beanObj.setLimitOSViewOC(DropDownControllor.formatter(resMap.get("RSK_LIMIT_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_OC").toString()));
						}
						if (resMap.get("RSK_LIMIT_OS_DC") != null) {
							beanObj.setLimitOSViewDC(DropDownControllor.formatter(resMap.get("RSK_LIMIT_OS_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OS_DC").toString()));
						}
						if (resMap.get("RSK_EPI_OSOF_OC") != null) {
							beanObj.setEpiAsPerOffer(resMap.get("RSK_EPI_OSOF_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_OC").toString());
							beanObj.setEpiOSViewOC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOF_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_OC").toString()));
						}
						if (resMap.get("RSK_EPI_OSOF_DC") != null) {
							beanObj.setEpiOSViewDC(DropDownControllor.formatter(resMap.get("RSK_EPI_OSOF_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OSOF_DC").toString()));
						}
						if (resMap.get("RSK_BROKERAGE") != null) {
							beanObj.setBrokerage(resMap.get("RSK_BROKERAGE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_BROKERAGE").toString());
						}
						if (resMap.get("RSK_TAX") != null) {
							beanObj.setTax(resMap.get("RSK_TAX").toString().equalsIgnoreCase("0") ? "0"	: resMap.get("RSK_TAX").toString());
						}
						if (resMap.get("RSK_ACQUISTION_COST_OC") != null) {
							beanObj.setAcquisition_Cost(resMap.get("RSK_ACQUISTION_COST_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACQUISTION_COST_OC").toString());
						}
						if (resMap.get("RSK_PROFIT_COMM") != null) {
							beanObj.setShare_Profit_Commission(resMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PROFIT_COMM").toString());
						}
						if (resMap.get("RSK_MD_PREM_OS_OC") != null) {
							beanObj.setMd_premium_our_service(resMap.get("RSK_MD_PREM_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_MD_PREM_OS_OC").toString());
							beanObj.setMandDpreViewOC(DropDownControllor.formatter(resMap.get("RSK_MD_PREM_OS_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_MD_PREM_OS_OC").toString()));
						}
						if (resMap.get("RSK_MD_PREM_OS_DC") != null) {
							beanObj.setMandDpreViewDC(DropDownControllor.formatter(resMap.get("RSK_MD_PREM_OS_DC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_MD_PREM_OS_DC").toString()));
						}
						if (resMap.get("RSK_AGGREGATE_LIAB_OC") != null) {
							beanObj.setAnualAggregateLiability(resMap.get("RSK_AGGREGATE_LIAB_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_AGGREGATE_LIAB_OC").toString());
						}
						if (resMap.get("RSK_REINSTATE_NO") != null) {
							beanObj.setReinstNo(resMap.get("RSK_REINSTATE_NO").toString().equalsIgnoreCase("0") ? "" : resMap.get("RSK_REINSTATE_NO").toString());
						}
						if (resMap.get("RSK_REINSTATE_ADDL_PREM_OC") != null) {
							beanObj.setReinstAdditionalPremium(resMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString());
						}
						if (resMap.get("RSK_LEAD_UW") != null) {
							beanObj.setLeader_Underwriter(resMap.get("RSK_LEAD_UW").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LEAD_UW").toString());
						}
						if (resMap.get("RSK_LEAD_UW_SHARE") != null) {
							beanObj.setLeader_Underwriter_share(resMap.get("RSK_LEAD_UW_SHARE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LEAD_UW_SHARE").toString());
						}
						if (resMap.get("RSK_ACCOUNTS") != null) {
							beanObj.setAccounts(resMap.get("RSK_ACCOUNTS").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACCOUNTS").toString());
						}
						if (resMap.get("RSK_EXCLUSION") != null) {
							beanObj.setExclusion(resMap.get("RSK_EXCLUSION").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EXCLUSION").toString());
						}
						beanObj.setRemarks(resMap.get("RSK_REMARKS")==null?"":resMap.get("RSK_REMARKS").toString());
						beanObj.setUnderwriter_Recommendations(resMap.get("RSK_UW_RECOMM")==null?"":resMap.get("RSK_UW_RECOMM").toString());
						beanObj.setGms_Approval(resMap.get("RSK_GM_APPROVAL")==null?"":resMap.get("RSK_GM_APPROVAL").toString());
						if (resMap.get("RSK_OTHER_COST") != null) {
							beanObj.setOthercost(resMap.get("RSK_OTHER_COST").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_OTHER_COST").toString());
						}
						if (resMap.get("RSK_REINSTATE_ADDL_PREM_PCT") != null) {
							beanObj.setReinstAditionalPremium_percent(resMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString());
						}
						if (resMap.get("RSK_BURNING_COST_PCT") != null) {
							beanObj.setBurningCost(resMap.get("RSK_BURNING_COST_PCT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_BURNING_COST_PCT").toString());
						}
						savFlg = true;
					}
				}
				args = new String[4];
				args[0] = beanObj.getProposal_no();
				args[1] = beanObj.getLayerNo();
				args[2] = beanObj.getProposal_no();
				args[3] = beanObj.getLayerNo();
				selectQry = getQuery(DBConstants.RISK_SELECT_GETINSTALMENTDATA);
				logger.info("selectQry " + selectQry);
				logger.info("Args[0]..[2]=>" +  beanObj.getProposal_no());
				logger.info("Args[1]..[3]=>" + beanObj.getLayerNo());
				List<Map<String, Object>> instalmentList = this.mytemplate.queryForList(selectQry,args);
				logger.info("Result Size=>" +instalmentList.size());
				if (instalmentList != null) {
					List<String> finalList = new ArrayList<String>();
					for (int k = 0; k < instalmentList.size(); k++) {
						Map<String, Object> insMap = (Map<String, Object>)instalmentList.get(k);
						//beanObj.getRequest().setAttribute("instalmentDate" + k,insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
						//beanObj.setInstalmentDate(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
						finalList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					}
					beanObj.setInstalmentDateList(finalList);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean beanObj,int flag){
		String query="";
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		try{
			if(flag==1){
				query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),beanObj.getIncepDate(),beanObj.getBranchCode(),beanObj.getIncepDate()});
			}else if(StringUtils.isNotEmpty(beanObj.getUwYear())&&flag==2){
				query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate()});
			}
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return list;
	}

	public boolean getLayerDuplicationCheck(RiskDetailsBean  formObj) {
		boolean result=false;
		try{
			if(StringUtils.isNotBlank(formObj.getLayerProposalNo())&&StringUtils.isNotBlank(formObj.getLayerNo())){
				String query= getQuery(DBConstants.RISK_SELECT_LAYERDUPCHECKBYBASELAYER);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getLayerNo());
				logger.info("Args[1]=>"+formObj.getLayerProposalNo());
				List<Map<String, Object>> list=this.mytemplate.queryForList(query,new Object[]{formObj.getLayerNo(),formObj.getLayerProposalNo()});
				logger.info("Result=>"+list.size());
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						Map<String, Object> map=(Map<String, Object>)list.get(i);
						String res=map.get("LAYER_NO")==null?"":map.get("LAYER_NO").toString();
						if(res.equalsIgnoreCase(formObj.getLayerNo())){
							result=true;
						}
					}
				}
				query= getQuery(DBConstants.RISK_SELECT_LAYERDUPCHECKBYPRONO);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getLayerNo());
				logger.info("Args[1]=>"+formObj.getLayerProposalNo());
				list=this.mytemplate.queryForList(query,new Object[]{formObj.getLayerNo(),formObj.getLayerProposalNo()});
				logger.info("Result=>"+list.size());
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						Map<String, Object> map=(Map<String, Object>)list.get(i);
						String res=map.get("LAYER_NO")==null?"":map.get("LAYER_NO").toString();
						if(res.equalsIgnoreCase(formObj.getLayerNo())){
							result=true;
						}
					}
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return result;
	}

	public boolean showSecondPageData1(String proposal,RiskDetailsBean beanObj,String pid) {
		try{
			Object[] args=new Object[7];
			args[0]=proposal;
			args[1]=beanObj.getBranchCode();
			args[2]=beanObj.getBranchCode();
			args[3]=pid;
			args[4]=beanObj.getBranchCode();
			args[5]=beanObj.getBranchCode();
			args[6]=beanObj.getBranchCode();
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETSECPAGEDATA);
			logger.info("selectQry " + selectQry);
			logger.info("Proposal No=>" + proposal);
			logger.info("Product Id=>" + pid);
			logger.info("Branch Code=>" + beanObj.getBranchCode());
			List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
			logger.info("Result Size=>" + res.size());
			Map<String, Object> resMap = null;
			if(res!=null && res.size()>0)
				resMap = (Map<String, Object>)res.get(0);
			if(resMap!=null){
				beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
				//beanObj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
				beanObj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME") == null ? "" : resMap.get("TMAS_SPFC_NAME").toString());
				beanObj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				beanObj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				beanObj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
				beanObj.setUnderwriter(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
				beanObj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME")==null?"":resMap.get("TMAS_POL_BRANCH_NAME").toString());
			}
			GetRemarksDetails(beanObj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		return true;
	}

	public List<Map<String, Object>> getValidation(RiskDetailsBean  formObj)  {
		String query="";
		List<Map<String, Object>> list=null;
		try{
			query = getQuery(DBConstants.FAC_SELECT_RENEWALVALIDATION);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+formObj.getIncepDate());
			logger.info("Args[1]=>"+formObj.getRenewal_contract_no());
			list = this.mytemplate.queryForList(query, new Object[] {formObj.getIncepDate(),formObj.getRenewal_contract_no()});
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return list;
	}

	public int getEditMode(String proposalNo){
		int mode=0;
		try {
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKCONTRACTNO);
			logger.info("select Query==>" + selectQry);
			logger.info("Args[0]=>"+proposalNo);
			String string = (String)this.mytemplate.queryForObject(selectQry,new Object[] {proposalNo},String.class);
			//mode = this.mytemplate.queryForInt(selectQry,new Object[] {proposalNo});
			logger.info("Result=>"+mode);
			if ("0".equalsIgnoreCase(string)) {
				mode = 1;
			} else {
				mode = 2;
			}
		}catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return mode;
	}

	public boolean riskDetailsEditMode(final RiskDetailsBean beanObj,final boolean ContractMode)  {
		boolean saveFlag = false;
		try {
			Object[] args = new Object[3];
			if (ContractMode) {
				args[0] = beanObj.getContNo();
				args[1] = beanObj.getContNo();
				args[2] = beanObj.getContNo();
			} else {
				args[0] = beanObj.getProposal_no();
				args[1] = beanObj.getProposal_no();
				args[2] = beanObj.getProposal_no();
			}
			logger.info("Args[0]..[2]"+args[0]);
			if (beanObj.getProduct_id().equalsIgnoreCase("4")) {
				List<Map<String, Object>> res = this.mytemplate.queryForList(GetRiskDetailsEditQuery(ContractMode),args);
				logger.info("List<Map<String, Object>> Size=>"+res.size());
				Map<String, Object> resMap = null;
				if(res!=null && res.size()>0)
					resMap = (Map<String, Object>)res.get(0);
				if (resMap!=null) {
					beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
					beanObj.setEndorsmentno(resMap.get("RSK_ENDORSEMENT_NO")==null?"":resMap.get("RSK_ENDORSEMENT_NO").toString());
					if(!"Renewal".equalsIgnoreCase(beanObj.getReMode())) {
					beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
					}
					beanObj.setLayerNo(resMap.get("RSK_LAYER_NO")==null?"":resMap.get("RSK_LAYER_NO").toString());
					beanObj.setProduct_id(resMap.get("RSK_PRODUCTID")==null?"":resMap.get("RSK_PRODUCTID").toString());
					beanObj.setDepartId(resMap.get("RSK_DEPTID")==null?"":resMap.get("RSK_DEPTID").toString());
					beanObj.setProfit_Center(resMap.get("RSK_PFCID")==null?"":resMap.get("RSK_PFCID").toString());
					beanObj.setSubProfit_center(resMap.get("RSK_SPFCID")==null?"":resMap.get("RSK_SPFCID").toString());
					beanObj.setPolBr(resMap.get("RSK_POLBRANCH")==null?"":resMap.get("RSK_POLBRANCH").toString());
					beanObj.setCedingCo(resMap.get("RSK_CEDINGID")==null?"":resMap.get("RSK_CEDINGID").toString());
					beanObj.setBroker(resMap.get("RSK_BROKERID")==null?"":resMap.get("RSK_BROKERID").toString());
					beanObj.setTreatyName_type(resMap.get("RSK_TREATYID")==null?"":resMap.get("RSK_TREATYID").toString());
					beanObj.setMonth(resMap.get("RSK_MONTH")==null?"":resMap.get("RSK_MONTH").toString());
					beanObj.setUwYear(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
					beanObj.setUnderwriter(resMap.get("RSK_UNDERWRITTER")==null?"":resMap.get("RSK_UNDERWRITTER").toString());
					beanObj.setIncepDate(resMap.get("RSK_INCEPTION_DATE")==null?"":(resMap.get("RSK_INCEPTION_DATE").toString()));
					beanObj.setExpDate(resMap.get("RSK_EXPIRY_DATE")==null?"":(resMap.get("RSK_EXPIRY_DATE").toString()));
					beanObj.setAccDate(resMap.get("RSK_ACCOUNT_DATE")==null?"":(resMap.get("RSK_ACCOUNT_DATE").toString()));
					beanObj.setOrginalCurrency(resMap.get("RSK_ORIGINAL_CURR")==null?"":resMap.get("RSK_ORIGINAL_CURR").toString());
					beanObj.setExchRate(resMap.get("RSK_EXCHANGE_RATE")==null?"":resMap.get("RSK_EXCHANGE_RATE").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_EXCHANGE_RATE")==null?"":resMap.get("RSK_EXCHANGE_RATE").toString());
					beanObj.setBasis(resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString());
					beanObj.setPnoc(resMap.get("RSK_PERIOD_OF_NOTICE")==null?"":resMap.get("RSK_PERIOD_OF_NOTICE").toString());
					beanObj.setRiskCovered(resMap.get("RSK_RISK_COVERED")==null?"":resMap.get("RSK_RISK_COVERED").toString());
					beanObj.setTerritoryscope(resMap.get("RSK_TERRITORY_SCOPE")==null?"":resMap.get("RSK_TERRITORY_SCOPE").toString());
					beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString()); //24
					beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
					beanObj.setRetroType(resMap.get("RSK_RETRO_TYPE")==null?"0":resMap.get("RSK_RETRO_TYPE").toString());
					if("TR".equalsIgnoreCase(beanObj.getRetroType())){
						beanObj.setLimitOrigCur(resMap.get("RSK_LIMIT_OC")==null?"":resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OC").toString()==null?"":resMap.get("RSK_LIMIT_OC").toString());
					}
					else{
						beanObj.setFaclimitOrigCur(resMap.get("RSK_LIMIT_OC")==null?"":resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OC").toString()==null?"":resMap.get("RSK_LIMIT_OC").toString());
					}
					beanObj.setEpi_origCur(resMap.get("RSK_EPI_OFFER_OC")==null?"":resMap.get("RSK_EPI_OFFER_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OFFER_OC").toString()==null?"":resMap.get("RSK_EPI_OFFER_OC").toString());
					beanObj.setXlCost(resMap.get("RSK_XLCOST_OC")==null?"":resMap.get("RSK_XLCOST_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_XLCOST_OC").toString()==null?"":resMap.get("RSK_XLCOST_OC").toString());
					beanObj.setShareWritt(resMap.get("RSK_SHARE_WRITTEN")==null?"":resMap.get("RSK_SHARE_WRITTEN").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_SHARE_WRITTEN").toString()==null?"":resMap.get("RSK_SHARE_WRITTEN").toString());
					beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString().equalsIgnoreCase("0") ? "": resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString());
					beanObj.setProposalType(resMap.get("RSK_PROPOSAL_TYPE")==null?"":resMap.get("RSK_PROPOSAL_TYPE").toString());
					beanObj.setAccountingPeriod(resMap.get("RSK_ACCOUNTING_PERIOD")==null?"":resMap.get("RSK_ACCOUNTING_PERIOD").toString());
					beanObj.setReceiptofStatements(resMap.get("RSK_RECEIPT_STATEMENT")==null?"":resMap.get("RSK_RECEIPT_STATEMENT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_RECEIPT_STATEMENT")==null?"":resMap.get("RSK_RECEIPT_STATEMENT").toString());
					beanObj.setReceiptofPayment(resMap.get("RSK_RECEIPT_PAYEMENT")==null?"":resMap.get("RSK_RECEIPT_PAYEMENT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_RECEIPT_PAYEMENT").toString()==null?"":resMap.get("RSK_RECEIPT_PAYEMENT").toString());
					beanObj.setNoRetroCess(resMap.get("RETRO_CESSIONARIES")==null?"":resMap.get("RETRO_CESSIONARIES").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RETRO_CESSIONARIES").toString()==null?"":resMap.get("RETRO_CESSIONARIES").toString());
					beanObj.setInsuredName(resMap.get("RSK_INSURED_NAME")==null?"":resMap.get("RSK_INSURED_NAME").toString());
					beanObj.setMaxLimit_Product(resMap.get("RSK_MAX_LMT_COVER")==null?"":resMap.get("RSK_MAX_LMT_COVER").toString()==null ? "0" : resMap.get("RSK_MAX_LMT_COVER").toString()==null?"":resMap.get("RSK_MAX_LMT_COVER").toString());
					beanObj.setRenewal_contract_no(resMap.get("OLD_CONTRACTNO")==null?"":resMap.get("OLD_CONTRACTNO").toString());
					beanObj.setBaseLoginID(resMap.get("LOGIN_ID")==null?"":resMap.get("LOGIN_ID").toString());
					beanObj.setCedRetenType(resMap.get("RSK_CEDRET_TYPE") == null ? "" : resMap.get("RSK_CEDRET_TYPE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_CEDRET_TYPE") == null ? "" : resMap.get("RSK_CEDRET_TYPE").toString());
					beanObj.setCedReten(resMap.get("RSK_CEDANT_RETENTION") == null ? "" : resMap.get("RSK_CEDANT_RETENTION").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_CEDANT_RETENTION").toString() == null ? "" : resMap.get("RSK_CEDANT_RETENTION").toString());
					beanObj.setPml(resMap.get("RSK_PML") == null ? "" : resMap.get("RSK_PML").toString());
					beanObj.setPmlPercent(resMap.get("RSK_PML_PERCENT") == null ? "" : resMap.get("RSK_PML_PERCENT").toString());
					beanObj.setTreatyType(resMap.get("TREATYTYPE") == null ? "" : resMap.get("TREATYTYPE").toString());
					beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED") == null ? "" : resMap.get("RSK_LOC_ISSUED").toString());
					beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED") == null ? "" : resMap.get("RSK_PERILS_COVERED").toString());
					beanObj.setCountryIncludedList(resMap.get("COUNTRIES_INCLUDE") == null ? "" : resMap.get("COUNTRIES_INCLUDE").toString());
					beanObj.setCountryExcludedList(resMap.get("COUNTRIES_EXCLUDE") == null ? "" : resMap.get("COUNTRIES_EXCLUDE").toString());
					beanObj.setTreatynoofLine(resMap.get("RSK_NO_OF_LINE") == null ? "0" : resMap.get("RSK_NO_OF_LINE").toString());
					if("TR".equalsIgnoreCase(beanObj.getRetroType())){
					beanObj.setLimitOrigCurPml(resMap.get("RSK_TRTY_LMT_PML_OC") == null ? "0" : resMap.get("RSK_TRTY_LMT_PML_OC").toString());
					}
					else{
						beanObj.setFaclimitOrigCurPml(resMap.get("RSK_TRTY_LMT_PML_OC") == null ? "0" : resMap.get("RSK_TRTY_LMT_PML_OC").toString());
					}
					beanObj.setTreatyLimitsurplusOCPml(resMap.get("RSK_TRTY_LMT_SUR_PML_OC") == null ? "" : resMap.get("RSK_TRTY_LMT_SUR_PML_OC").toString());
					beanObj.setEpipml(resMap.get("RSK_TRTY_LMT_OURASS_PML_OC") == null ? "" : resMap.get("RSK_TRTY_LMT_OURASS_PML_OC").toString());
					beanObj.setEpi(resMap.get("RSK_TRTY_LMT_OURASS_PML_OC") == null ? "" : resMap.get("RSK_TRTY_LMT_OURASS_PML_OC").toString());
					beanObj.setEpi(resMap.get("RSK_EPI_EST_OC") == null ? "" : resMap.get("RSK_EPI_EST_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_EST_OC").toString() == null ? "" : resMap.get("RSK_EPI_EST_OC").toString());
					beanObj.setTreatyLimitsurplusOC(resMap.get("RSK_TREATY_SURP_LIMIT_OC") == null ? "" : resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString() == null ? "" : resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString());
					//beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
					beanObj.setRunoffYear(resMap.get("RSK_RUN_OFF_YEAR")==null?"":resMap.get("RSK_RUN_OFF_YEAR").toString());
					beanObj.setLocBankName(resMap.get("RSK_LOC_BNK_NAME")==null?"":resMap.get("RSK_LOC_BNK_NAME").toString());
					beanObj.setLocCreditPrd(resMap.get("RSK_LOC_CRDT_PRD")==null?"":resMap.get("RSK_LOC_CRDT_PRD").toString());
					beanObj.setLocCreditAmt(resMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(resMap.get("RSK_LOC_CRDT_AMT").toString()));
					beanObj.setLocBeneficerName(resMap.get("RSK_LOC_BENFCRE_NAME")==null?"":resMap.get("RSK_LOC_BENFCRE_NAME").toString());
					beanObj.setCessionExgRate(resMap.get("RSK_CESSION_EXG_RATE")==null?"":resMap.get("RSK_CESSION_EXG_RATE").toString());
					beanObj.setFixedRate(resMap.get("RSK_FIXED_RATE")==null?"":resMap.get("RSK_FIXED_RATE").toString());
					beanObj.setDummyCon(resMap.get("RSK_DUMMY_CONTRACT")==null?"":resMap.get("RSK_DUMMY_CONTRACT").toString());
					saveFlag = true;
				}
			}
			if ("5".equalsIgnoreCase(beanObj.getProduct_id())) {
				List<Map<String, Object>> res = this.mytemplate.queryForList(GetRiskDetailsEditQueryProduct3(ContractMode),args);
				logger.info("List<Map<String, Object>> Size=>"+res.size());
				Map<String, Object> resMap = null;
				if(res!=null && res.size()>0)
					resMap = (Map<String, Object>)res.get(0);
				if (resMap!=null) {
					beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
					beanObj.setEndorsmentno(resMap.get("RSK_ENDORSEMENT_NO")==null?"":resMap.get("RSK_ENDORSEMENT_NO").toString());
					beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
					beanObj.setLayerNo(resMap.get("RSK_LAYER_NO")==null?"":resMap.get("RSK_LAYER_NO").toString());
					beanObj.setProduct_id(resMap.get("RSK_PRODUCTID")==null?"":resMap.get("RSK_PRODUCTID").toString());
					beanObj.setDepartId(resMap.get("RSK_DEPTID")==null?"":resMap.get("RSK_DEPTID").toString());
					beanObj.setProfit_Center(resMap.get("RSK_PFCID")==null?"":resMap.get("RSK_PFCID").toString());
					beanObj.setSubProfit_center(resMap.get("RSK_SPFCID")==null?"":resMap.get("RSK_SPFCID").toString());
					beanObj.setPolBr(resMap.get("RSK_POLBRANCH")==null?"":resMap.get("RSK_POLBRANCH").toString());
					beanObj.setCedingCo(resMap.get("RSK_CEDINGID")==null?"":resMap.get("RSK_CEDINGID").toString());
					beanObj.setBroker(resMap.get("RSK_BROKERID")==null?"":resMap.get("RSK_BROKERID").toString());
					beanObj.setTreatyName_type(resMap.get("RSK_TREATYID")==null?"":resMap.get("RSK_TREATYID").toString());
					beanObj.setMonth(resMap.get("RSK_MONTH")==null?"":resMap.get("RSK_MONTH").toString());
					beanObj.setUwYear(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
					beanObj.setUnderwriter(resMap.get("RSK_UNDERWRITTER")==null?"":resMap.get("RSK_UNDERWRITTER").toString());
					beanObj.setIncepDate(resMap.get("RSK_INCEPTION_DATE")==null?"":resMap.get("RSK_INCEPTION_DATE").toString());
					beanObj.setExpDate(resMap.get("RSK_EXPIRY_DATE")==null?"":resMap.get("RSK_EXPIRY_DATE").toString());
					beanObj.setAccDate(resMap.get("RSK_ACCOUNT_DATE")==null?"":resMap.get("RSK_ACCOUNT_DATE").toString());
					beanObj.setOrginalCurrency(resMap.get("RSK_ORIGINAL_CURR")==null?"":resMap.get("RSK_ORIGINAL_CURR").toString());
					if (resMap.get("RSK_EXCHANGE_RATE") != null) {
						beanObj.setExchRate(resMap.get("RSK_EXCHANGE_RATE").toString().equalsIgnoreCase("0") ? "0":resMap.get("RSK_EXCHANGE_RATE").toString());
					}
					if (resMap.get("RSK_BASIS") != null) {
						beanObj.setBasis(resMap.get("RSK_BASIS").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_BASIS").toString());
					}
					beanObj.setTerritoryscope(resMap.get("RSK_TERRITORY_SCOPE")==null?"":resMap.get("RSK_TERRITORY_SCOPE").toString());
					beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString());
					beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
					if (resMap.get("RSK_LIMIT_OC") != null) {
						if("TR".equalsIgnoreCase(beanObj.getRetroType())){
							beanObj.setLimitOrigCur(resMap.get("RSK_LIMIT_OC")==null?"":resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OC").toString()==null?"":resMap.get("RSK_LIMIT_OC").toString());
						}
						else{
							beanObj.setFaclimitOrigCur(resMap.get("RSK_LIMIT_OC")==null?"":resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OC").toString()==null?"":resMap.get("RSK_LIMIT_OC").toString());
						}
						
					}
					if (resMap.get("RSK_EPI_EST_OC") != null) {
						beanObj.setEpi(resMap.get("RSK_EPI_EST_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_EPI_EST_OC").toString());
					}
					if (resMap.get("RSK_SHARE_WRITTEN") != null) {
						beanObj.setShareWritt(resMap.get("RSK_SHARE_WRITTEN").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_SHARE_WRITTEN").toString());
					}
					if (resMap.get("RSK_SHARE_SIGNED") != null) {
						beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED").toString().equalsIgnoreCase("0") ? "" :resMap.get("RSK_SHARE_SIGNED").toString());
					}
					if (resMap.get("RSK_MAX_LMT_COVER") != null) {
						beanObj.setMaxLimit_Product(resMap.get("RSK_MAX_LMT_COVER").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_MAX_LMT_COVER").toString());
					}
					if (resMap.get("RSK_SUBJ_PREMIUM_OC") != null) {
						beanObj.setSubPremium(resMap.get("RSK_SUBJ_PREMIUM_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_SUBJ_PREMIUM_OC").toString());
					}
					if (resMap.get("RSK_XLPREM_OC") != null) {
						beanObj.setXlPremium(resMap.get("RSK_XLPREM_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_XLPREM_OC").toString());
					}
					if (resMap.get("RSK_DEDUC_OC") != null) {
						beanObj.setDeduc_hunPercent(resMap.get("RSK_DEDUC_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_DEDUC_OC").toString());
					}
					if (resMap.get("RSK_MD_PREM_OC") != null) {
						beanObj.setM_dPremium(resMap.get("RSK_MD_PREM_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_MD_PREM_OC").toString());
					}
					if (resMap.get("RSK_ADJRATE") != null) {
						beanObj.setAdjRate(resMap.get("RSK_ADJRATE").toString().equalsIgnoreCase("0") ? "0"	: resMap.get("RSK_ADJRATE").toString());
					}
					if (resMap.get("RSK_PF_COVERED") != null) {
						beanObj.setPortfoloCovered(resMap.get("RSK_PF_COVERED").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_PF_COVERED").toString());
					}
					beanObj.setM_d_InstalmentNumber(resMap.get("MND_INSTALLMENTS")==null?"":resMap.get("MND_INSTALLMENTS").toString());
					if("5".equalsIgnoreCase(beanObj.getProduct_id())){
						beanObj.setNoRetroCess(resMap.get("RETRO_CESSIONARIES").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RETRO_CESSIONARIES").toString());
					}
					
					//beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
					beanObj.setRenewal_contract_no(resMap.get("OLD_CONTRACTNO")==null?"":resMap.get("OLD_CONTRACTNO").toString());
					beanObj.setBaseLoginID(resMap.get("LOGIN_ID")==null?"":resMap.get("LOGIN_ID").toString());
					saveFlag = true;
				}
			}
			GetRemarksDetails(beanObj);
			beanObj.setAmendId(new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no()));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return saveFlag;
	}
	public String GetRiskDetailsEditQuery(boolean contractMode) {
		String query = "";
		query = getQuery(DBConstants.RISK_SELECT_GETEDITMODEDATA);
		if(contractMode){
			query = " "+query +" "+ getQuery(DBConstants.RISK_SELECT_GETEDITMODECONTCOND);
		}else{
			query = " "+query +" "+ getQuery(DBConstants.RISK_SELECT_GETEDITMODEPROCOND);
		}
		logger.info("Query=>" + query);
		return query.toString();
	}
	public boolean saveSecondMode(RiskDetailsBean beanObj, String productId)  {
		try{
			int ChkSecPagMod = checkSecondPageMode(beanObj, productId);
			int ContractEditMode = contractEditMode(beanObj, productId);
			String updateQry = "",insertQry = "";
			Object[] obj=null,obj1=null;
			//if (ContractEditMode == 1) {
			if (ChkSecPagMod == 2) {
				obj = saveUpdateRiskDetailsSecondForm(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
				logger.info("Query=>" + updateQry);
				int res = this.mytemplate.update(updateQry, obj);
				logger.info("Result=>" + res);
				String GetProposalStatus = null;
				String query = getQuery(DBConstants.RISK_SELECT_MAXRSKSTATUS);
				logger.info("Query=>" + query);
				logger.info("Args[0]=>" + obj[16]);
				GetProposalStatus = this.mytemplate.queryForObject(query, new Object[]{obj[16]}, String.class).toString();
				logger.info("Result=>" + GetProposalStatus);
				beanObj.setProStatus(GetProposalStatus);
				if (StringUtils.isNotBlank(beanObj.getContNo())) {
					beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : " + beanObj.getProposal_no());
				} else if ("A".equalsIgnoreCase(GetProposalStatus) || "P".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : " + obj[16]);
				} else if ("N".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : " + obj[16]);
				}
				obj1 = savemodeUpdateRiskDetailsSecondFormSecondTable(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO4SECCOMM);
				res = this.mytemplate.update(updateQry, obj1);
				logger.info("Result=>" + res);
			} else {
				obj = secondPageFirstTableSaveAruguments(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
				logger.info("updateQry" + updateQry);
				int res = this.mytemplate.update(updateQry, obj);
				logger.info("Result=>" + res);
				if (productId.equalsIgnoreCase("4"))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO4SECCOMM);
				else if (productId.equalsIgnoreCase("5"))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO5SECCOMM);
				logger.info("insertQry " + insertQry);
				obj1 = secondPageCommissionSaveAruguments(beanObj, productId);
				res = this.mytemplate.update(insertQry, obj1);
				logger.info("Result=>" + res);
				Object[] args = new Object[3];
				args[0] = beanObj.getProposal_no();
				args[1] = beanObj.getProposal_no();
				args[2] = beanObj.getProposal_no();
				String query = getQuery(DBConstants.RISK_SELECT_CHECHPROPOSALSTATUS);
				logger.info("Query=>" + query);
				logger.info("Args[1]..[3]=>" + beanObj.getProposal_no());
				Map<String, Object> resMap = this.mytemplate.queryForMap(query, args);
				logger.info("Map Size=>" + resMap.size());
				for (int i = 0; i < resMap.size(); i++) {
					beanObj.setProStatus(resMap.get("RSK_STATUS") == null ? "" : resMap.get("RSK_STATUS").toString());
					beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED") == null ? "" : resMap.get("RSK_SHARE_SIGNED").toString());
					beanObj.setContNo(resMap.get("RSK_CONTRACT_NO") == null ? "" : resMap.get("RSK_CONTRACT_NO").toString());
				}
				final String HomePosition = getproposalStatus(beanObj.getProposal_no());
				beanObj.setProStatus(HomePosition);
				if (HomePosition.equalsIgnoreCase("P")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : " + beanObj.getProposal_no());
				} else if (HomePosition.equalsIgnoreCase("A")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : " + beanObj.getProposal_no());
				} else if (HomePosition.equalsIgnoreCase("R")) {
					beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : " + beanObj.getProposal_no());
				} else if (HomePosition.equalsIgnoreCase("N")) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : " + beanObj.getProposal_no());
				} else if (HomePosition.equalsIgnoreCase("0")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : " + beanObj.getProposal_no());
				}
			}
			//}
			insertRetroCess(beanObj);
			InsertRemarkDetails(beanObj);
			insertCrestaMaintable(beanObj);
			beanObj.setProduct_id(productId);
			insertBonusDetails(beanObj,"scale");
			insertBonusDetails(beanObj,"lossparticipate");
			insertProfitCommissionMain(beanObj,"main");
			if ("5".equalsIgnoreCase(productId)) {
				instalMentPremium(beanObj);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("Exception @ {" + e + "}");
		}
		return true;
	}

	private void insertProfitCommissionMain(RiskDetailsBean beanObj,String type) {
		if(!"1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
				mainDelete(beanObj);
				profitMainEmptyInsert(beanObj);
			}
		profitUpdate(beanObj);
	}


	private void profitMainEmptyInsert(RiskDetailsBean bean) {
		try{
		
			String query = getQuery("COMMISSION_INSERT");
			Object args[]=new Object[12];
				args[0]="";
				args[1]="";
				args[2]="";
				args[3]="";
				args[4]=bean.getProposal_no();
				args[5]=bean.getContractNo();
				args[6]=bean.getAmendId();
				args[7]=bean.getProduct_id();
				args[8]=bean.getBranchCode();
				args[9]=bean.getDepartmentId();
				args[10]=bean.getCommissionType();
				args[11]=bean.getLoginId();
				this.mytemplate.update(query,args);
			
		
		}
			catch(Exception e){
				e.printStackTrace();
			}
	}


	private void mainDelete(RiskDetailsBean bean) {
		try{
		String query=getQuery("COMMIOSSION_DELETE");
		Object args[] = null;
		if(StringUtils.isNotBlank(bean.getContractNo())){
			 query +=" and CONTRACT_NO=?";
			 args=new Object[4];
			 args[0]=bean.getProposal_no();
			 args[1]=bean.getBranchCode();
			 args[2]=bean.getAmendId();
			 args[3]=bean.getContractNo();
		}
		else{
			args=new Object[3];
			 args[0]=bean.getProposal_no();
			 args[1]=bean.getBranchCode();
			 args[2]=bean.getAmendId();
		}
		this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void profitUpdate(RiskDetailsBean bean) {
		try{
			String query =getQuery("COMMISSION_STATUS_UPDATE");
			Object args[] = new Object[5];
			args[0] = bean.getContractNo();
			args[1] = bean.getShare_Profit_Commission();
			args[2] = bean.getProposal_no();
			args[3] = bean.getBranchCode();
			args[4] = bean.getAmendId();
			this.mytemplate.update(query,args);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public int checkSecondPageMode(final RiskDetailsBean beanObj,final String productId) {
		int mode=0;
		try{
			final String query;
			query = getQuery(DBConstants.RISK_SELECT_GETRETROCESSCOUNT);
			logger.info("Query=>" + query);
			logger.info("Args[0]=>" + beanObj.getProposal_no());
			int selectCount = this.mytemplate.queryForInt(query,new Object[]{beanObj.getProposal_no()});
			logger.info("Result=>" + selectCount);
			if (selectCount == 0) {
				mode = 1;
			} else if (selectCount != 0) {
				mode = 2;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		logger.info("Mode Of Insertion=>" + mode);
		logger.info("If 1 means Insert");
		logger.info("if 2  means Update");
		return mode;
	}

	public int contractEditMode(final RiskDetailsBean beanObj,final String productId) {
		int mode=0;
		final String query = getQuery(DBConstants.RISK_SELECT_GETRSKCONTRACTNO);
		logger.info("Query=>" + query);
		/*int res = this.mytemplate.queryForInt(query,new Object[]{beanObj.getProposal_no()});
		logger.info("Result Count=>" + res);
		if (res==0) {
			mode = 1;
		} else if (res>0) {
			mode = 2;
		}*/
		String string = (String)this.mytemplate.queryForObject(query,new Object[] {beanObj.getProposal_no()},String.class);
		logger.info("Result=>"+mode);
		if ("0".equalsIgnoreCase(string)) {
			mode = 1;
		} else {
			mode = 2;
		}
		logger.info("Mode 1 Means Insert");
		logger.info("Mode 2 Means Update");
		return mode;
	}
	public Object[] saveUpdateRiskDetailsSecondForm(
			final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		if ("4".equalsIgnoreCase(productId)) {
			obj = new Object[18];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
					.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
					.getExchRate());
			obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
			obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
					.getExchRate());
			obj[6] = beanObj.getXlcostOurShare();
			obj[7] = getDesginationCountry(beanObj.getXlcostOurShare(), beanObj
					.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getPremiumQuotaShare())?"0":beanObj.getPremiumQuotaShare();
			obj[9] = StringUtils.isBlank(beanObj.getPremiumSurplus())?"0":beanObj.getPremiumSurplus();
			obj[10]= StringUtils.isEmpty(beanObj.getPremiumQuotaShare())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumQuotaShare(), beanObj
					.getExchRate());
			obj[11]= StringUtils.isEmpty(beanObj.getPremiumSurplus())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumSurplus(), beanObj
					.getExchRate());
			obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt();
			obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt();
			obj[14]= StringUtils.isEmpty(beanObj.getCommissionQ_SAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommissionQ_SAmt(), beanObj
					.getExchRate());
			obj[15]= StringUtils.isEmpty(beanObj.getCommission_surpAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommission_surpAmt(), beanObj
					.getExchRate());
			obj[16] = beanObj.getProposal_no();
			obj[17] = endNo;
		} else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
			obj[1] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[2] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
			obj[3] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[4] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0": beanObj.getMd_premium_our_service();
			obj[5] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[6] = beanObj.getProposal_no();
			obj[7] = beanObj.getLayerNo();
			obj[8] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] savemodeUpdateRiskDetailsSecondFormSecondTable(
			final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=new Object[0];
		if (productId.equalsIgnoreCase("4")) {
			obj = new Object[71];
			obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0" : beanObj.getBrokerage();
			obj[1] = beanObj.getTax();
			obj[2] = beanObj.getShare_Profit_Commission();
			obj[3] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0" : beanObj.getAcquisition_Cost();
			obj[4] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getAcquisition_Cost(), beanObj.getExchRate());
			obj[5] = StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0" : beanObj.getCommissionQ_S();
			obj[6] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0" : beanObj.getCommission_surp();
			obj[7] = beanObj.getOverRidder();
			//obj[8] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
			//obj[9] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();
			obj[8] = beanObj.getPremium_Reserve();
			obj[9] = beanObj.getLoss_reserve();
			obj[10] = beanObj.getInterest();
			obj[11] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0" : beanObj.getPortfolio_inout_Premium();
			obj[12] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0" : beanObj.getPortfolio_inout_Loss();
			obj[13] = beanObj.getLoss_Advise();
			obj[14] = beanObj.getCash_Loss_Limit();
			obj[15] = getDesginationCountry(beanObj.getCash_Loss_Limit(), beanObj.getExchRate());
			/*obj[16] = beanObj.getLeader_Underwriter();
			obj[17] = beanObj.getLeader_Underwriter_share();*/
			
			obj[18] = beanObj.getAccounts();
			obj[19] = beanObj.getExclusion();
			obj[20] = beanObj.getRemarks();
			obj[21] = beanObj.getUnderwriter_Recommendations();
			obj[22] = beanObj.getGms_Approval();
			//obj[25] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0"	: beanObj.getProfit_commission();
			obj[23] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0" : beanObj.getOthercost();
			obj[24] = beanObj.getCrestaStatus();
			obj[25] = beanObj.getEvent_limit();
			obj[26] = getDesginationCountry(beanObj.getEvent_limit(), beanObj.getExchRate());
			obj[27] = beanObj.getAggregate_Limit();
			obj[28] = getDesginationCountry(beanObj.getAggregate_Limit(), beanObj.getExchRate());
			obj[29] = beanObj.getOccurrent_Limit();
			obj[30] = getDesginationCountry(beanObj.getOccurrent_Limit(), beanObj.getExchRate());
			obj[31] = beanObj.getSlideScaleCommission();
			obj[32] = beanObj.getLossParticipants();
			obj[33] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "" : beanObj.getCommissionSubClass();
			obj[34] = beanObj.getDepartmentId();
			obj[35] = beanObj.getLoginId();
			obj[36] = beanObj.getBranchCode();
			obj[37] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "" : beanObj.getLeader_Underwriter_country();
			obj[38] = StringUtils.isEmpty(beanObj.getOrginalacqcost()) ? "" : beanObj.getOrginalacqcost();
			obj[39] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost()) ? "" : beanObj.getOurassessmentorginalacqcost();
			obj[40] = StringUtils.isEmpty(beanObj.getOuracqCost()) ? "" : beanObj.getOuracqCost();
			obj[41] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass()) ? "" : beanObj.getLosscommissionSubClass();
			obj[42] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass()) ? "" : beanObj.getSlidecommissionSubClass();
			obj[43] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass()) ? "" : beanObj.getCrestacommissionSubClass();
			if ("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())) {
				obj[44] = StringUtils.isEmpty(beanObj.getManagementExpenses()) ? "" : beanObj.getManagementExpenses();
				obj[45] = StringUtils.isEmpty(beanObj.getCommissionType()) ? "" : beanObj.getCommissionType();
				obj[46] = StringUtils.isEmpty(beanObj.getProfitCommissionPer()) ? "" : beanObj.getProfitCommissionPer();
				obj[47] = StringUtils.isEmpty(beanObj.getSetup()) ? "" : beanObj.getSetup();
				obj[48] = StringUtils.isEmpty(beanObj.getSuperProfitCommission()) ? "" : beanObj.getSuperProfitCommission();
				obj[49] = StringUtils.isEmpty(beanObj.getLossCarried()) ? "" : beanObj.getLossCarried();
				obj[50] = StringUtils.isEmpty(beanObj.getLossyear()) ? "" : beanObj.getLossyear();
				obj[51] = StringUtils.isEmpty(beanObj.getProfitCarried()) ? "" : beanObj.getProfitCarried();
				obj[52] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear()) ? "" : beanObj.getProfitCarriedForYear();
				obj[53] = StringUtils.isEmpty(beanObj.getFistpc()) ? "" : beanObj.getFistpc();
				obj[54] = StringUtils.isEmpty(beanObj.getProfitMont()) ? "" : beanObj.getProfitMont();
				obj[55] = StringUtils.isEmpty(beanObj.getSubProfitMonth()) ? "" : beanObj.getSubProfitMonth();
				obj[56] = StringUtils.isEmpty(beanObj.getSubpc()) ? "" : beanObj.getSubpc();
				obj[57] = StringUtils.isEmpty(beanObj.getSubSeqCalculation()) ? "" : beanObj.getSubSeqCalculation();
				obj[58] = StringUtils.isEmpty(beanObj.getProfitCommission()) ? "" : beanObj.getProfitCommission();
			} else {
				obj[44] = "";
				obj[45] = "";
				obj[46] = "";
				obj[47] = "";
				obj[48] = "";
				obj[49] = "";
				obj[50] = "";
				obj[51] = "";
				obj[52] = "";
				obj[53] = "";
				obj[54] = "";
				obj[55] = "";
				obj[56] = "";
				obj[57] = "";
				obj[58] = "";
			}
			obj[59] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			obj[60] =StringUtils.isEmpty(beanObj.getCommissionQ_SYN())?"":beanObj.getCommissionQ_SYN();
			obj[61] =StringUtils.isEmpty(beanObj.getCommission_surpYN())?"":beanObj.getCommission_surpYN();
			obj[62] =StringUtils.isEmpty(beanObj.getOverRidderYN())?"":beanObj.getOverRidderYN();
			obj[63] =StringUtils.isEmpty(beanObj.getBrokerageYN())?"":beanObj.getBrokerageYN();
			obj[64] =StringUtils.isEmpty(beanObj.getTaxYN())?"":beanObj.getTaxYN();
			obj[65] =StringUtils.isEmpty(beanObj.getOthercostYN())?"":beanObj.getOthercostYN();
			obj[66] =StringUtils.isEmpty(beanObj.getCeedODIYN())?"":beanObj.getCeedODIYN();
			obj[67] =StringUtils.isEmpty(beanObj.getLocRate())?"":beanObj.getLocRate();
			obj[68] =StringUtils.isEmpty(beanObj.getRetroCommissionType())?"":beanObj.getRetroCommissionType();
			obj[69] = beanObj.getProposal_no();
			obj[70] = endNo;
		}else if (productId.equalsIgnoreCase("5")) {
			obj = new Object[7];
			obj[0] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0" : beanObj.getReinstNo();
			obj[1] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" : beanObj.getReinstAditionalPremium_percent();
			obj[2] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0" : beanObj.getBurningCost();
			obj[3] = beanObj.getRemarks();
			obj[4] = beanObj.getProposal_no();
			obj[5] = beanObj.getLayerNo();
			obj[6] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageFirstTableSaveAruguments(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		if ("4".equalsIgnoreCase(productId)) {
			obj = new Object[18];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
					.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
					.getExchRate());
			obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
			obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
					.getExchRate());
			obj[6] = beanObj.getXlcostOurShare();
			obj[7] = getDesginationCountry(beanObj.getXlcostOurShare(), beanObj
					.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getPremiumQuotaShare())?"0":beanObj.getPremiumQuotaShare();
			obj[9] = StringUtils.isBlank(beanObj.getPremiumSurplus())?"0":beanObj.getPremiumSurplus();
			obj[10]= StringUtils.isEmpty(beanObj.getPremiumQuotaShare())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumQuotaShare(), beanObj
					.getExchRate());
			obj[11]= StringUtils.isEmpty(beanObj.getPremiumSurplus())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumSurplus(), beanObj
					.getExchRate());
			obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt();
			obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt();
			obj[14]= StringUtils.isEmpty(beanObj.getCommissionQ_SAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommissionQ_SAmt(), beanObj
					.getExchRate());
			obj[15]= StringUtils.isEmpty(beanObj.getCommission_surpAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommission_surpAmt(), beanObj
					.getExchRate());
			obj[16] = beanObj.getProposal_no();
			obj[17] = endNo;
		}
		if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
			obj[1] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[2] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
			obj[3] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[4] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
			obj[5] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[6] = beanObj.getProposal_no();
			obj[7] = beanObj.getLayerNo();
			obj[8] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageCommissionSaveAruguments(
			final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		if ("4".equalsIgnoreCase(productId)) {
			obj = new Object[75];
			obj[0] = beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = "0";
			obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
			logger.info("Enter Into beanObj.getExchRate()" + obj[3]);
			obj[4] = StringUtils.isEmpty(beanObj.getTax()) ? "0" : beanObj.getTax();
			obj[5] = StringUtils.isEmpty(beanObj.getShare_Profit_Commission()) ? "0": beanObj.getShare_Profit_Commission();
			obj[6] = "0";
			obj[7] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0": beanObj.getAcquisition_Cost();
			obj[8] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
			obj[9] = StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0": beanObj.getCommissionQ_S();
			obj[10] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0": beanObj.getCommission_surp();
			obj[11] = StringUtils.isEmpty(beanObj.getOverRidder()) ? "0": beanObj.getOverRidder();
			//obj[12] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
			//obj[13] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();

			obj[12] = StringUtils.isEmpty(beanObj.getPremium_Reserve()) ? "0": beanObj.getPremium_Reserve();
			obj[13] = StringUtils.isEmpty(beanObj.getLoss_reserve()) ? "0": beanObj.getLoss_reserve();
			obj[14] = StringUtils.isEmpty(beanObj.getInterest()) ? "0": beanObj.getInterest();
			obj[15] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
			obj[16] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
			obj[17] = StringUtils.isEmpty(beanObj.getLoss_Advise()) ? "0": beanObj.getLoss_Advise();
			obj[18] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": beanObj.getCash_Loss_Limit();
			obj[19] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
			obj[20] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0": beanObj.getLeader_Underwriter();
			obj[21] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0": beanObj.getLeader_Underwriter_share();
			obj[22] = StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj.getAccounts();
			obj[23] = StringUtils.isEmpty(beanObj.getExclusion()) ? "": beanObj.getExclusion();
			obj[24] = StringUtils.isEmpty(beanObj.getRemarks()) ? "" : beanObj.getRemarks();
			obj[25] = StringUtils.isEmpty(beanObj.getUnderwriter_Recommendations()) ? "" : beanObj.getUnderwriter_Recommendations();
			obj[26] = StringUtils.isEmpty(beanObj.getGms_Approval()) ? "": beanObj.getGms_Approval();
			obj[27] = "";
			obj[28] = "";
			//obj[31] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0": beanObj.getProfit_commission();
			obj[29] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0": beanObj.getOthercost();
			obj[30] = beanObj.getCrestaStatus();
			obj[31] = beanObj.getEvent_limit();
			obj[32] =getDesginationCountry(beanObj.getEvent_limit(),beanObj.getExchRate());
			obj[33] = beanObj.getAggregate_Limit();
			obj[34] =getDesginationCountry(beanObj.getAggregate_Limit(),beanObj.getExchRate());
			obj[35] = beanObj.getOccurrent_Limit();
			obj[36] =getDesginationCountry(beanObj.getOccurrent_Limit(),beanObj.getExchRate());
			obj[37] = beanObj.getSlideScaleCommission();
			obj[38] = beanObj.getLossParticipants();
			obj[39] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "": beanObj.getCommissionSubClass();
			obj[40]=beanObj.getDepartmentId();
			obj[41] = beanObj.getLoginId();
			obj[42] = beanObj.getBranchCode();
			obj[43] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country())?"":beanObj.getLeader_Underwriter_country();
			obj[44] =StringUtils.isEmpty(beanObj.getOrginalacqcost())?"":beanObj.getOrginalacqcost();
			obj[45] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost())?"":beanObj.getOurassessmentorginalacqcost();
			obj[46] = StringUtils.isEmpty(beanObj.getOuracqCost())?"":beanObj.getOuracqCost();

			obj[47] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass())?"":beanObj.getLosscommissionSubClass();
			obj[48] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass())?"":beanObj.getSlidecommissionSubClass();
			obj[49] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass())?"":beanObj.getCrestacommissionSubClass();
			if("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
				obj[50] = StringUtils.isEmpty(beanObj.getManagementExpenses())?"":beanObj.getManagementExpenses();
				obj[51] = StringUtils.isEmpty(beanObj.getCommissionType())?"":beanObj.getCommissionType();
				obj[52] = StringUtils.isEmpty(beanObj.getProfitCommissionPer())?"":beanObj.getProfitCommissionPer();
				obj[53] = StringUtils.isEmpty(beanObj.getSetup())?"":beanObj.getSetup();
				obj[54] = StringUtils.isEmpty(beanObj.getSuperProfitCommission())?"":beanObj.getSuperProfitCommission();
				obj[55] = StringUtils.isEmpty(beanObj.getLossCarried())?"":beanObj.getLossCarried();
				obj[56] = StringUtils.isEmpty(beanObj.getLossyear())?"":beanObj.getLossyear();
				obj[57] = StringUtils.isEmpty(beanObj.getProfitCarried())?"":beanObj.getProfitCarried();
				obj[58] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear())?"":beanObj.getProfitCarriedForYear();
				obj[59] = StringUtils.isEmpty(beanObj.getFistpc())?"":beanObj.getFistpc();
				obj[60] = StringUtils.isEmpty(beanObj.getProfitMont())?"":beanObj.getProfitMont();
				obj[61] = StringUtils.isEmpty(beanObj.getSubProfitMonth())?"":beanObj.getSubProfitMonth();
				obj[62] = StringUtils.isEmpty(beanObj.getSubpc())?"":beanObj.getSubpc();
				obj[63] = StringUtils.isEmpty(beanObj.getSubSeqCalculation())?"":beanObj.getSubSeqCalculation();
				obj[64] = StringUtils.isEmpty(beanObj.getProfitCommission())?"":beanObj.getProfitCommission();
			}
			else{
				obj[50] = "";
				obj[51] = "";
				obj[52] = "";
				obj[53] = "";
				obj[54] = "";
				obj[55] = "";
				obj[56] = "";
				obj[57] = "";
				obj[58] = "";
				obj[59] = "";
				obj[60] = "";
				obj[61] = "";
				obj[62] = "";
				obj[63] = "";
				obj[64] = "";
			}
			obj[65] =StringUtils.isEmpty(beanObj.getDocStatus())?"":beanObj.getDocStatus();
			obj[66] =StringUtils.isEmpty(beanObj.getCommissionQ_SYN())?"":beanObj.getCommissionQ_SYN();
			obj[67] =StringUtils.isEmpty(beanObj.getCommission_surpYN())?"":beanObj.getCommission_surpYN();
			obj[68] =StringUtils.isEmpty(beanObj.getOverRidderYN())?"":beanObj.getOverRidderYN();
			obj[69] =StringUtils.isEmpty(beanObj.getBrokerageYN())?"":beanObj.getBrokerageYN();
			obj[70] =StringUtils.isEmpty(beanObj.getTaxYN())?"":beanObj.getTaxYN();
			obj[71] =StringUtils.isEmpty(beanObj.getOthercostYN())?"":beanObj.getOthercostYN();
			obj[72] =StringUtils.isEmpty(beanObj.getCeedODIYN())?"":beanObj.getCeedODIYN();
			obj[73] =StringUtils.isEmpty(beanObj.getLocRate())?"":beanObj.getLocRate();
			obj[74] =StringUtils.isEmpty(beanObj.getRetroCommissionType())?"":beanObj.getRetroCommissionType();
		}else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[7];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[4] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" : beanObj.getReinstAditionalPremium_percent();
			obj[5] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0" : beanObj.getBurningCost();
			obj[6] = StringUtils.isEmpty(beanObj.getRemarks()) ? "" : beanObj.getRemarks();
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	private String getproposalStatus(final String proposalNo) {
		String result="";
		try{
			String query=getQuery(DBConstants.RISK_SELECT_GETRSKSTATUS);
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+proposalNo);
			result = this.mytemplate.queryForObject(query,new String[] {proposalNo},String.class).toString();
			logger.info("Result=>"+result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return result;
	}

	public void insertRetroContracts(RiskDetailsBean beanObj,String productId) {
		try{
			final int LoopCount = Integer.parseInt(beanObj.getNo_Insurer());
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			Object[] obj = new Object[9];
			String query=getQuery(DBConstants.FAC_INSERT_INSDETAILS);
			String updQry=getQuery(DBConstants.FAC_UPD_INSDETAILS);
			String qry=getQuery(DBConstants.FAC_UPDATE_INSDETAILS);
			for (int i = 0; i < LoopCount; i++) {
				boolean mode = new DropDownControllor().getMode(beanObj.getProposal_no(),i+1,endtNo,2);
				if(mode){
					if(Integer.parseInt(endtNo)>0){
						logger.info("Query=>"+query);
						logger.info("Arg[0]=>"+beanObj.getProposal_no());
						logger.info("Arg[1]=>"+(i+1));
						int res=this.mytemplate.update(qry,new Object[]{beanObj.getProposal_no(),(i+1)});
						logger.info("Update Result=>"+res);
					}
					obj[0] = i+1;
					obj[1] = beanObj.getProposal_no();
					obj[2] = StringUtils.isEmpty(beanObj.getRetroCeding().get(i)) ? "0" :beanObj.getRetroCeding().get(i);
					obj[3] = endtNo;
					obj[4] = "C";
					obj[5] = StringUtils.isEmpty(beanObj.getPercentRetro().get(i))? "0" :beanObj.getPercentRetro().get(i);
					obj[6] = "Y";
					obj[7] = StringUtils.isEmpty(beanObj.getRetroYear().get(i))? "0" :beanObj.getRetroYear().get(i);
					obj[8] = StringUtils.isEmpty(beanObj.getRetroType())? "" :beanObj.getRetroType();
					logger.info("Insert Query=>"+query);
					logger.info("Args=>" +StringUtils.join(obj,","));
					int res=this.mytemplate.update(query, obj);
					logger.info("Result=>"+res);
				}else{
					obj[0] = StringUtils.isEmpty(beanObj.getRetroCeding().get(i)) ? "0" :beanObj.getRetroCeding().get(i);
					obj[1] = "C";
					obj[2] = StringUtils.isEmpty(beanObj.getPercentRetro().get(i))? "0" :beanObj.getPercentRetro().get(i);
					obj[3] = "Y";
					obj[4] = StringUtils.isEmpty(beanObj.getRetroYear().get(i))? "0" :beanObj.getRetroYear().get(i);
					obj[5] = StringUtils.isEmpty(beanObj.getRetroType())? "" :beanObj.getRetroType();
					obj[6] = beanObj.getProposal_no();
					obj[7] = i+1;
					obj[8] = endtNo;
					logger.info("Insert Query==>"+updQry);
					logger.info("Args[]=>"+StringUtils.join(obj,","));
					int res=this.mytemplate.update(updQry, obj);
					logger.info("Result=>"+res);
				}
			}
			boolean mode = new DropDownControllor().getMode(beanObj.getProposal_no(),0,endtNo,2);
			if(mode){
				if(Integer.parseInt(endtNo)>0){
					logger.info("Query=>"+qry);
					logger.info("Arg[0]=>"+beanObj.getProposal_no());
					logger.info("Arg[1]=>"+0);
					int res=this.mytemplate.update(qry,new Object[]{beanObj.getProposal_no(),"0"});
					logger.info("Update Result=>"+res);
				}
				obj[0] = 0;
				obj[1] = beanObj.getProposal_no();
				obj[2] = "";
				obj[3] = endtNo;
				obj[4] = "R";
				obj[5] = StringUtils.isEmpty(beanObj.getRetentionPercentage())? "0" :beanObj.getRetentionPercentage();
				obj[6] = "Y";
				obj[7] = "";
				obj[8] = "";
				logger.info("Insert Query==>"+query);
				logger.info("Args=>" +StringUtils.join(obj,","));
				int res=this.mytemplate.update(query, obj);
				logger.info("Result=>"+res);
			}else{
				obj[0] = "";
				obj[1] = "R";
				obj[2] = StringUtils.isEmpty(beanObj.getRetentionPercentage())? "0" :beanObj.getRetentionPercentage();
				obj[3] = "Y";
				obj[4] = "";
				obj[5] = "";
				obj[6] = beanObj.getProposal_no();
				obj[7] = "0";
				obj[8] = endtNo;
				logger.info("Insert Query==>"+updQry);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(updQry, obj);
				logger.info("Result=>"+res);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
	}

	public boolean showSecondPageData(String proposal,RiskDetailsBean  formobj,String pid)  {
		try{
			String selectQry = "";
			Object[] args=new Object[7];
			args[0]=proposal;
			args[1]=formobj.getBranchCode();
			args[2]=formobj.getBranchCode();
			args[3]=pid;
			args[4]=formobj.getBranchCode();
			args[5]=formobj.getBranchCode();
			args[6]=formobj.getBranchCode();
			selectQry = getQuery(DBConstants.RISK_SELECT_GETSECPAGEDATA);
			logger.info("selectQry " + selectQry);
			logger.info("Proposal No===>"+args[0]);
			logger.info("Branch Code=>"+args[1]);
			logger.info("product Id=>"+args[3]);
			List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
			logger.info("Result Size=>" + res.size());
			Map<String, Object> resMap = null;
			if(res!=null && res.size()>0)
				resMap = (Map<String, Object>)res.get(0);
			if(resMap!=null){
				formobj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
				if( !"Renewal".equalsIgnoreCase(formobj.getReMode())){
				formobj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
				}
				//formobj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
				formobj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME") == null ? "" : resMap.get("TMAS_SPFC_NAME").toString());
				formobj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				formobj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				formobj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
				formobj.setUnderwriter(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
				formobj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME") == null ? "" : resMap.get("TMAS_POL_BRANCH_NAME").toString());
				formobj.setEndttypename(resMap.get("DETAIL_NAME")==null?"":resMap.get("DETAIL_NAME").toString());
			}
			selectQry = getQuery(DBConstants.RISK_SELECT_GETCOMMDET);
			logger.info("selectQry " + selectQry);
			logger.info("Args[0]=>"+proposal);
			Map<String, Object> commMap=null;
			List<Map<String, Object>> commList=this.mytemplate.queryForList(selectQry,new Object[]{proposal});
			logger.info("Result Size=>" +commList.size());
			if(commList!=null && commList.size()>0){
				commMap=(Map<String, Object>)commList.get(0);
				formobj.setReinstNo(commMap.get("RSK_REINSTATE_NO")==null?"":commMap.get("RSK_REINSTATE_NO").toString());
				formobj.setReinstAditionalPremium_percent(commMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":commMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString());
				formobj.setBurningCost(commMap.get("RSK_BURNING_COST_PCT")==null?"":commMap.get("RSK_BURNING_COST_PCT").toString());
				formobj.setRemarks(commMap.get("RSK_REMARKS")==null?"":commMap.get("RSK_REMARKS").toString());
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return true;
	}
	public void insertRetroCess(RiskDetailsBean beanObj) {
		try{
			int NoRetroCess=Integer.parseInt(beanObj.getNoRetroCess()==null?"0":beanObj.getNoRetroCess());
			Object[] obj=null;
			String sql="";
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			//To delete the existing retrocess for a proposal no with amend id.
			this.mytemplate.update("delete from TTRN_RETRO_CESSIONARY where PROPOSAL_NO=? and amend_id=?", new Object[]{beanObj.getProposal_no(), endtNo});
			for(int i=0;i<NoRetroCess;i++){
				sql = getQuery(DBConstants.RISK_INSERT_RETROCESSDTLS);
				obj=getRetroCessArgs(beanObj,i,endtNo,true);
				logger.info("Query=>" + sql);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				logger.info("Result=>" +this.mytemplate.update(sql, obj));
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
	}
	public Object[] getRetroCessArgs(RiskDetailsBean beanObj,int i,String endtNo,boolean mode) {
		Object[] obj = null;
		try{
			if (mode) {
				obj = new Object[13];
				obj[0]=i;
				obj[1]=beanObj.getProposal_no();
				obj[2]=StringUtils.isBlank(beanObj.getContNo())?"0":beanObj.getContNo();
				obj[3]=StringUtils.isEmpty(beanObj.getCedingCompany().get(i))?"0":beanObj.getCedingCompany().get(i);
				obj[4]=StringUtils.isEmpty(beanObj.getRetroBroker().get(i))?"0":beanObj.getRetroBroker().get(i);
				obj[5]=StringUtils.isEmpty(beanObj.getShareAccepted().get(i))?"0":beanObj.getShareAccepted().get(i);
				obj[6]=StringUtils.isEmpty(beanObj.getShareSigned().get(i))?"0":beanObj.getShareSigned().get(i);
				obj[7]=StringUtils.isEmpty(beanObj.getCommission().get(i))?"0":beanObj.getCommission().get(i);
				obj[8]=StringUtils.isEmpty(beanObj.getProposalStatus().get(i))?"0":beanObj.getProposalStatus().get(i).equalsIgnoreCase("0")?"P":beanObj.getProposalStatus().get(i);
				obj[9]=endtNo;
				obj[10]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				obj[11]=beanObj.getLoginId();
				obj[12]=beanObj.getBranchCode();
			}else{
				obj = new Object[11];
				obj[0]=StringUtils.isBlank(beanObj.getContNo())?"0":beanObj.getContNo();
				obj[1]=StringUtils.isEmpty(beanObj.getCedingCompany().get(i))?"0":beanObj.getCedingCompany().get(i);
				obj[2]=StringUtils.isEmpty(beanObj.getRetroBroker().get(i))?"0":beanObj.getRetroBroker().get(i);
				obj[3]=StringUtils.isEmpty(beanObj.getShareAccepted().get(i))?"0":beanObj.getShareAccepted().get(i);
				obj[4]=StringUtils.isEmpty(beanObj.getShareSigned().get(i))?"0":beanObj.getShareSigned().get(i);
				obj[5]=StringUtils.isEmpty(beanObj.getCommission().get(i))?"0":beanObj.getCommission().get(i);
				obj[6]=StringUtils.isEmpty(beanObj.getProposalStatus().get(i))?"0":beanObj.getProposalStatus().get(i).equalsIgnoreCase("0")?"P":beanObj.getProposalStatus().get(i);
				obj[7]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				obj[8]=i;
				obj[9]=beanObj.getProposal_no();
				obj[10]=endtNo;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return obj;
	}
	public boolean instalMentPremium(final RiskDetailsBean beanObj) {
		try{
			final String InstallmentPeriod = beanObj.getM_d_InstalmentNumber();
			String updateQry ="", insertQry = "";
			Object[] obj=null;
			int res=0;
			insertQry = getQuery(DBConstants.RISK_INSERT_INSTALPREM);
			updateQry = getQuery(DBConstants.RISK_UPDATE_MNDINSTALLMENTSDATA);
			logger.info("ContractNo=>"+beanObj.getContNo());
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			int number=Integer.parseInt(InstallmentPeriod);
			obj = new Object[8];
			for (int i = 0; i < number; i++) {
				boolean modeOfInsertion = new  DropDownControllor().getMode(beanObj.getProposal_no(),i+1,endtNo,1);
				if (modeOfInsertion) {
					obj[0] = i + 1;
					obj[1] = beanObj.getProposal_no();
					obj[2] = StringUtils.isEmpty(beanObj.getContNo()) ? "0"	: beanObj.getContNo();
					obj[3] = beanObj.getLayerNo();
					obj[4] = endtNo;
					obj[5] = StringUtils.isEmpty(beanObj.getInstalmentDateList().get(i)) ? "" : beanObj.getInstalmentDateList().get(i);
					obj[6] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i)) ? "" : beanObj.getInstallmentPremium();
					obj[7] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i))|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getInstallmentPremium().get(i), beanObj.getExchRate());
					logger.info("Query=>"+insertQry);
					logger.info("Args[]"+StringUtils.join(obj,","));
					res=this.mytemplate.update(insertQry,obj);
					logger.info("Result=>"+res);
				}else {
					obj = new String[8];
					obj[0] = StringUtils.isEmpty(beanObj.getContNo()) ? "0": beanObj.getContNo();
					obj[1] = beanObj.getLayerNo();
					obj[2] = StringUtils.isEmpty(beanObj.getInstalmentDateList().get(i)) ? "" : beanObj.getInstalmentDateList().get(i);
					obj[3] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i)) ? "" : beanObj.getInstallmentPremium();
					obj[4] = String.valueOf((StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i))|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getInstallmentPremium().get(i), beanObj.getExchRate())));
					obj[5] = String.valueOf(i + 1);
					obj[6] = beanObj.getProposal_no();
					obj[7] = endtNo;
					logger.info("Query=>"+updateQry);
					logger.info("Args[]=>"+StringUtils.join(obj,","));
					res=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>"+res);
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return true;
	}

	public boolean saveRiskDeatilsSecondForm(RiskDetailsBean beanObj,final String productId) {
		try {
			String updateQry = "",insertQry = "",selectQry="",endom="";
			String flag="";
			Object[] obj=null,obj1=null;
			Object[] args=null;
			int out=0;
			int chkSecPageMode = checkSecondPageMode(beanObj, productId);
			int ContractEditMode = contractEditMode(beanObj, productId);
			if (ContractEditMode == 1) {
				if (chkSecPageMode == 1) {
					obj = secondPageFirstTableAruguments(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					if(productId.equalsIgnoreCase("2") || productId.equalsIgnoreCase("4"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
					else if(productId.equalsIgnoreCase("3") || productId.equalsIgnoreCase("5"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
					logger.info("updateQry=>"+ updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" + out);
					if("4".equalsIgnoreCase(productId))
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO4SECCOMM);
					else if("5".equalsIgnoreCase(productId))
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO5SECCOMM);
					logger.info("insertQry=>" + insertQry);
					obj = secondPageCommissionAruguments(beanObj,productId);
					out=this.mytemplate.update(insertQry, obj);
					logger.info("Result=>" + out);
					args = new Object[3];
					args[0] = beanObj.getProposal_no();
					args[1] = beanObj.getProposal_no();
					args[2] = beanObj.getProposal_no();
					selectQry = getQuery(DBConstants.RISK_SELECT_CHECHPROPOSALSTATUS);
					logger.info("Query=>" + selectQry);
					logger.info("Args[0]..[1]=>" +  beanObj.getProposal_no());
					List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
					logger.info("List<Map<String, Object>> Size=>" + res.size());
					Map<String, Object> resMap = null;
					if(res!=null && res.size()>0)
						resMap = (Map<String, Object>)res.get(0);
					if(resMap!=null){
						beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
						beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString());
						beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
						if (beanObj.getProStatus().matches("A")&& !beanObj.getSharSign().matches("0")) {
							String maxContarctNo = null;
							logger.info("Contract No=>"+ beanObj.getContractno() + "Layer No=>"+ beanObj.getLay());
							String prodid=productId;
							if (beanObj.getLay().equalsIgnoreCase("layer")) {
								logger.info("Mode Layer");
								maxContarctNo = beanObj.getContractno();
							} else {
								logger.info("Mode Not a Layer");
								String query=getQuery(DBConstants.RISK_SELECT_POLICYNOPRODCODE);
								String catagoryId="";
								if("SR".equalsIgnoreCase(beanObj.getRetroType())){
									catagoryId="9";//Fac Spc Retro
								}else{
									catagoryId="10";//Treaty Retro
								}
								logger.info("Query=>" + query);
								logger.info("Args[0]=>" + catagoryId);
								prodid=(String)this.mytemplate.queryForObject(query,new Object[]{catagoryId},String.class);
								logger.info("Result=>" + prodid);
								if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
									maxContarctNo=beanObj.getRenewal_contract_no();
								}else{
									//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
									maxContarctNo = new DropDownControllor().getSequence("Contract", "SR".equalsIgnoreCase(beanObj.getRetroType()) ? "5" : "4", beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
									/*}else
									maxContarctNo=new DropDownControllor().getPolicyNo("2",prodid,beanObj.getBranchCode());*/
									logger.info("Result=>" + maxContarctNo);
								}
							}
							logger.info("Proposal Number=>"	+ beanObj.getProposal_no() + " Contract No=>" + maxContarctNo);
							args = new String[2];
							args[0] = maxContarctNo;
							args[1] = beanObj.getProposal_no();
							updateQry = getQuery(DBConstants.RISK_UPDATE_CONTNO);
							logger.info("updateQry " + updateQry);
							logger.info("Args[0]=>" + maxContarctNo);
							logger.info("Args[1]=>" +  beanObj.getProposal_no());
							out=this.mytemplate.update(updateQry,args);
							logger.info("Result=>" +  out);
							updateQry=getQuery(DBConstants.RISK_UPDATE_HOMECONTNO);
							args = new String[4];
							args[0] = maxContarctNo;
							beanObj.setContNo((String)args[0]);
							args[1] = "A";
							args[2] = "A";
							args[3] = beanObj.getProposal_no();
							logger.info("updateQry " + updateQry);
							logger.info("Args[0]=>" + maxContarctNo);
							logger.info("Args[1]..[2]=>" + "A");
							logger.info("Args[3]=>" +  beanObj.getProposal_no());
							out=this.mytemplate.update(updateQry,args);
							logger.info("Result=>" +  out);
							if("".equals(beanObj.getRenewal_contract_no())||"0".equals(beanObj.getRenewal_contract_no())||"NEWCONTNO".equals(beanObj.getRenewalFlag())){
								beanObj.setContractGendration("Your Proposal is converted to Contract with Proposal No : "+beanObj.getProposal_no() +" and Contract No : "+maxContarctNo+".");
							}else{
								beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+" and New Contract No : "+maxContarctNo+".");
							}
						} else {
							args = new String[4];
							args[0] = beanObj.getContNo();
							args[1] = getproposalStatus(beanObj.getProposal_no());
							args[2] = args[1];
							if (args != null) {
								if (((String) args[1]).equalsIgnoreCase("P")) {
									if (productId.equalsIgnoreCase("4")) {
										beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
									}
									if ("5".equalsIgnoreCase(productId)) {
										beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No. :"+beanObj.getLayerNo());
									}
								}	if (((String) args[1]).equalsIgnoreCase("N")) {
									if (productId.equalsIgnoreCase("2")||productId.equalsIgnoreCase("4")) {
										beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
									}
									if ("5".equalsIgnoreCase(productId)) {
										beanObj.setContractGendration("Your Proposal is saved in  Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No. :"+beanObj.getLayerNo());
									}
								}  else if (((String) args[1]).equalsIgnoreCase("A")) {
									if ("4".equalsIgnoreCase(productId)) {
										beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+" and Contract No : "+beanObj.getContNo()+".");
									}
									if ("5".equalsIgnoreCase(productId)) {
										beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+" and Layer No : "+beanObj.getLayerNo()+".");
									}
								} else if (((String) args[1]).equalsIgnoreCase("R")) {
									beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
								}
							}
							args[3] = beanObj.getProposal_no();
							updateQry=getQuery(DBConstants.RISK_UPDATE_HOMECONTNO);
							logger.info("updateQry " + updateQry);
							int k=0;
							for(Object str:args)
								logger.info("Args[" + k++ + "]" + str);
							out=this.mytemplate.update(getQuery(DBConstants.RISK_UPDATE_HOMECONTNO),args);
							logger.info("Result=>" +  out);
						}
					}
				}else if (chkSecPageMode == 2) {
					obj =updateRiskDetailsSecondForm(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					if(productId.equalsIgnoreCase("4"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
					else if(productId.equalsIgnoreCase("5"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
					logger.info("Update Query=>" + updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" +  out);
					obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					if(productId.equalsIgnoreCase("4"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO4SECCOMM);
					else if(productId.equalsIgnoreCase("5"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO5SECCOMM);
					logger.info("updateQry " + updateQry);
					out=this.mytemplate.update(updateQry, obj1);
					logger.info("Result=>" +  out);
					args = new String[3];
					args[0] = beanObj.getProposal_no();
					args[1] = beanObj.getProposal_no();
					args[2] = beanObj.getProposal_no();
					selectQry=getQuery(DBConstants.RISK_SELECT_CHECHPROPOSALSTATUS);
					logger.info("selectQry " + selectQry);
					logger.info("Args[0]..[2] " + beanObj.getProposal_no());
					List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
					logger.info("Result Size=>" +  res.size());
					Map<String, Object> resMap = null;
					if(res!=null && res.size()>0)
						resMap = (Map<String, Object>)res.get(0);
					if (resMap != null) {
						for (int i = 0; i < resMap.size(); i++) {
							beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
							beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString());
							beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
						}
					}

					if (beanObj.getProStatus().matches("A")	&& !beanObj.getSharSign().matches("0")) {
						String prodid=productId;
						String query=getQuery(DBConstants.RISK_SELECT_POLICYNOPRODCODE);
						String catagoryId="";
						if("SR".equalsIgnoreCase(beanObj.getRetroType())){
							catagoryId = "9";
						}else{
							catagoryId="10";
						}
						logger.info("selectQry " + query);
						logger.info("Args[0]=>" + catagoryId);
						prodid=(String)this.mytemplate.queryForObject(query,new Object[]{catagoryId},String.class);
						logger.info("Result=>" + prodid);
						String maxContarctNo = "";
						if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
							maxContarctNo = beanObj.getRenewal_contract_no();
						}else{
							//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
							maxContarctNo = new DropDownControllor().getSequence("Contract", "SR".equalsIgnoreCase(beanObj.getRetroType()) ? "5" : "4", beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
							/*}else
							maxContarctNo=new DropDownControllor().getPolicyNo("2",prodid,beanObj.getBranchCode());*/
						}
						logger.info(" Proposal Number" + beanObj.getProposal_no() + " Contract No" + maxContarctNo);
						Object[] arg = new Object[2];
						arg[0] = maxContarctNo;
						arg[1] = beanObj.getProposal_no();
						updateQry = getQuery(DBConstants.RISK_UPDATE_CONTNO);
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + maxContarctNo);
						logger.info("Args[1]=>" + beanObj.getProposal_no());
						out=this.mytemplate.update(updateQry,arg);
						logger.info("Result=>" +out);
						args = new String[4];
						args[0] = maxContarctNo;
						args[1] = getMaxproposalStatus(beanObj.getProposal_no());
						args[2] = args[1];
						args[3] = beanObj.getProposal_no();
						updateQry =getQuery(DBConstants.RISK_UPDATE_HOMECONTNO);
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + args[0]);
						logger.info("Args[1]=>" + args[1]);
						logger.info("Args[2]=>" + args[2]);
						logger.info("Args[3]=>" + args[3]);
						out=this.mytemplate.update(getQuery(DBConstants.RISK_UPDATE_HOMECONTNO),args);
						logger.info("Result=>" +out);
						if("".equals(beanObj.getRenewal_contract_no())||"0".equals(beanObj.getRenewal_contract_no())||"NEWCONTNO".equals(beanObj.getRenewalFlag())){
							if (productId.equalsIgnoreCase("4")) {
								beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+" and Contract No : "+maxContarctNo+".");
							}
							if ("5".equalsIgnoreCase(productId)) {
								beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+maxContarctNo+" and Layer No.: "+beanObj.getLayerNo()+".");
							}
						}else	{
							if (productId.equalsIgnoreCase("4")) {
								beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+" and New Contract No : "+maxContarctNo+".");
							}
							if ("5".equalsIgnoreCase(productId)) {
								beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+",New Contract No : "+maxContarctNo+" and Layer No.: "+beanObj.getLayerNo()+".");
							}
						}
						updateQry =getQuery(DBConstants.RISK_UPDATE_MNDINSTALLMENTS);
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + maxContarctNo);
						logger.info("Args[1]=>" + beanObj.getProposal_no());
						out=this.mytemplate.update(updateQry,new Object[]{maxContarctNo,beanObj.getProposal_no()});
						logger.info("Result=>" +out);
						beanObj.setContNo(maxContarctNo);
					} else if (beanObj.getProStatus().matches("P")) {
						if (productId.equalsIgnoreCase("4")) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
						}
						if ("5".equalsIgnoreCase(productId)) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
						}
					}else if (beanObj.getProStatus().matches("N")) {
						if (productId.equalsIgnoreCase("4")) {
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
						}
						if ("5".equalsIgnoreCase(productId)) {
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
						}
					}
					else if (beanObj.getProStatus().matches("R")) {
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
					}
				}
			}
			else if (ContractEditMode == 2) {
				endom=getQuery(DBConstants.RISK_SELECT_ENDO);
				logger.info("Query=>"+endom);
				if(StringUtils.isBlank(beanObj.getContNo())&&"Renewal".equals(beanObj.getReMode())){
					beanObj.setContNo(new DropDownControllor().getSequence("Contract", "SR".equalsIgnoreCase(beanObj.getRetroType()) ? "5" : "4", beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear()));
					logger.info(" Proposal Number" + beanObj.getProposal_no() + " Contract No" + beanObj.getContNo());
					Object[] arg = new Object[2];
					arg[0] = beanObj.getContNo();
					arg[1] = beanObj.getProposal_no();
					updateQry = getQuery(DBConstants.RISK_UPDATE_CONTNO);
					logger.info("updateQry " + updateQry);
					logger.info("Args[0]=>" + beanObj.getContNo());
					logger.info("Args[1]=>" + beanObj.getProposal_no());
					out=this.mytemplate.update(updateQry,arg);
					logger.info("Result=>" +out);
					
					args = new String[4];
					args[0] = beanObj.getContNo();
					args[1] = getMaxproposalStatus(beanObj.getProposal_no());
					args[2] = args[1];
					args[3] = beanObj.getProposal_no();
					updateQry =getQuery(DBConstants.RISK_UPDATE_HOMECONTNO);
					logger.info("updateQry " + updateQry);
					logger.info("Args[0]=>" + args[0]);
					logger.info("Args[1]=>" + args[1]);
					logger.info("Args[2]=>" + args[2]);
					logger.info("Args[3]=>" + args[3]);
					out=this.mytemplate.update(getQuery(DBConstants.RISK_UPDATE_HOMECONTNO),args);
				}
				logger.info("Args[0]=>"+beanObj.getProposal_no());
				String endtNo=(String)this.mytemplate.queryForObject(endom, new Object[]{beanObj.getProposal_no()}, String.class);
				logger.info("Result=>"+endtNo);
				obj = updateContractRiskDetailsSecondForm(beanObj, productId,endtNo);
				logger.info("Update Select Query========>"+endom);
				if(productId.equalsIgnoreCase("4"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24CONTSECPAGE);
				else if(productId.equalsIgnoreCase("5"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35CONTSECPAGE);
				logger.info("updateQry " + updateQry);
				out=this.mytemplate.update(updateQry, obj);
				logger.info("Result=>" +out);
				if (productId.equalsIgnoreCase("4")) {
					beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+".");
				} else if ("5".equalsIgnoreCase(productId)) {
					beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+" and Layer No : "+beanObj.getLayerNo()+".");
				}
				/*if("4".equalsIgnoreCase(productId))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO4SECCOMM);
				else if("5".equalsIgnoreCase(productId))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO5SECCOMM);
				logger.info("InsertQry=>" + insertQry);
				obj1 = secondContarctPageCommissionAruguments(beanObj,productId);
				out = this.mytemplate.update(insertQry, obj1);*/
				if(productId.equalsIgnoreCase("4"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO4SECCOMM);
				else if(productId.equalsIgnoreCase("5"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO5SECCOMM);
				obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
				out = this.mytemplate.update(updateQry, obj1);
				logger.info("Result=>" +out);
				beanObj.setProStatus("A");
			}
			if("4".equalsIgnoreCase(productId)){
				insertRetroCess(beanObj);
			}
			if("5".equalsIgnoreCase(productId)) {
				instalMentPremium(beanObj);
			}
			insertCrestaMaintable(beanObj);
			beanObj.setProduct_id(productId);
			insertBonusDetails(beanObj,"scale");
			insertBonusDetails(beanObj,"lossparticipate");
			insertProfitCommissionMain(beanObj,"main");
			InsertRemarkDetails(beanObj);
			DropDownControllor dropDownControllor = new DropDownControllor();
			dropDownControllor.updatepositionMasterEndtStatus(beanObj.getProposal_no(),productId,beanObj.getEndorsementDate(),"");
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
	}
	
	private void insertCrestaMaintable(RiskDetailsBean bean) {
		String query ="";
		Object obj[] =null;
		try {
			if(StringUtils.isBlank(bean.getAmendId())){
				bean.setAmendId("0");
			}
			int count=getCrestaCount(bean);
			if(count<=0){
				    query=getQuery("MOVE_TO_CRESTA_MAIN_TABLE");
				    obj = new Object[12];
					obj[0]=bean.getContractNo();
					obj[1]=bean.getProposal_no();
					obj[2]=bean.getAmendId();
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
			 obj[0]=bean.getContNo();
			 obj[1]=bean.getProposal_no();
			 obj[2]=bean.getAmendId();
			 obj[3]=bean.getBranchCode();
			 this.mytemplate.update(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void insertBonusDetails(RiskDetailsBean beanObj,String type) {
		if(StringUtils.isBlank(beanObj.getContractNo()) && StringUtils.isNotBlank(beanObj.getContNo()) ){
			beanObj.setContractNo(beanObj.getContNo());
		}
					beanObj.setPageFor(type);
					
					if("scale".equalsIgnoreCase(beanObj.getPageFor()) && "Y".equalsIgnoreCase(beanObj.getSlideScaleCommission()) ){
						updateContractDetails(beanObj,"scale");
					}
					else if("Y".equalsIgnoreCase(beanObj.getLossParticipants()) && "lossparticipate".equalsIgnoreCase(beanObj.getPageFor())){
						updateContractDetails(beanObj,"lossparticipate");
					}
					else{
						moveBonusEmptyData(beanObj);
					}
		}

	private void moveBonusEmptyData(RiskDetailsBean bean) {
		try{
			if(StringUtils.isBlank(bean.getAmendId())){

				bean.setAmendId("0");
			}
			deleteMaintable(bean);
			String query =getQuery("BONUS_MAIN_INSERT_PTTY");
			Object args[]=new Object[21];
					args[0] =bean.getProposal_no();
					args[1] = bean.getContractNo();
					args[2] = bean.getProduct_id();
		           args[3] = "";
		           args[4] = "";
		           args[5] = "";
		           args[6] ="";
		           args[7] = bean.getLoginId();
		           args[8] = bean.getBranchCode();
		           args[9] = "";
		           if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   args[10] ="SSC";
		           }
		           else{
		        	   args[10]="LPC";
		           }
		           args[11] = bean.getAmendId();
		           args[12] = bean.getDepartmentId();
		           args[13] = StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();
		           args[14] ="";
		           args[15] ="";
		           args[16] ="";
		           args[17] ="";
		           args[18] ="";
		           args[19] ="";
		           args[20] ="";
		           logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
	}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void deleteMaintable(RiskDetailsBean bean) {
		String query1="";
		Object arg[]=null;
		try{
			if("".equalsIgnoreCase(bean.getEndorsmentno())){
				query1 =getQuery("BONUS_MAIN_DELETE");
				 arg = new Object[4];
				 arg[0] = bean.getProposal_no();
				 arg[1] = bean.getBranchCode();
				 if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   arg[2] ="SSC";
		           }
		           else{
		        	   arg[2]="LPC";
		           }
				  arg[3]=StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();;
			}
			else{
			 query1 =getQuery("BONUS_MAIN_DELETE2");
			 arg = new Object[5];
			 arg[0] = bean.getProposal_no();
			 arg[1] = bean.getAmendId();
			 arg[2] = bean.getBranchCode();
			 if("scale".equalsIgnoreCase(bean.getPageFor())){
	        	   arg[3] ="SSC";
	           }
	           else{
	        	   arg[3]="LPC";
	           }
			  arg[4]=StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();;
			}
			this.mytemplate.update(query1,arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		private void updateContractDetails(RiskDetailsBean bean, String string) {
			try{
				String query=getQuery("CONTRACT_UPDATE");
				Object args[] = new Object[3];
				args[1] =bean.getProposal_no();
			    args[0] = bean.getContractNo();
	           args[2] = bean.getBranchCode();
	           this.mytemplate.update(query,args);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	public Object[] secondPageFirstTableAruguments(
			final RiskDetailsBean beanObj, final String productId , final String endNo) {
		Object[] obj=null;
		if ("4".equalsIgnoreCase(productId)) {
			obj = new Object[18];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
					.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
					.getExchRate());
			obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
			obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
					.getExchRate());
			obj[6] = beanObj.getXlcostOurShare();
			obj[7] = getDesginationCountry(beanObj.getXlcostOurShare(), beanObj
					.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getPremiumQuotaShare())?"0":beanObj.getPremiumQuotaShare();
			obj[9] = StringUtils.isBlank(beanObj.getPremiumSurplus())?"0":beanObj.getPremiumSurplus();
			obj[10]= StringUtils.isEmpty(beanObj.getPremiumQuotaShare())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumQuotaShare(), beanObj
					.getExchRate());
			obj[11]= StringUtils.isEmpty(beanObj.getPremiumSurplus())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumSurplus(), beanObj
					.getExchRate());
			obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt();
			obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt();
			obj[14]= StringUtils.isEmpty(beanObj.getCommissionQ_SAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommissionQ_SAmt(), beanObj
					.getExchRate());
			obj[15]= StringUtils.isEmpty(beanObj.getCommission_surpAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommission_surpAmt(), beanObj
					.getExchRate());
			obj[16] = beanObj.getProposal_no();
			obj[17] = endNo;
		}
		if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[4] = beanObj.getMd_premium_our_service();
			logger.info("ENter Into FInal Statge"+ beanObj.getMd_premium_our_service());
			obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),	beanObj.getExchRate());
			obj[6] = beanObj.getProposal_no();
			obj[7] = beanObj.getLayerNo();
			obj[8] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] secondPageCommissionAruguments(final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		//if("insert".equalsIgnoreCase(flag)){
		if ("4".equalsIgnoreCase(productId)) {
			obj = new Object[75];
			obj[0] = beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = "0";
			obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
			logger.info("Enter Into beanObj.getExchRate()" + obj[3]);
			obj[4] = StringUtils.isEmpty(beanObj.getTax()) ? "0" : beanObj.getTax();
			obj[5] = StringUtils.isEmpty(beanObj.getShare_Profit_Commission()) ? "0": beanObj.getShare_Profit_Commission();
			obj[6] = "0";
			obj[7] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0": beanObj.getAcquisition_Cost();
			obj[8] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
			obj[9] = StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0": beanObj.getCommissionQ_S();
			obj[10] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0": beanObj.getCommission_surp();
			obj[11] = StringUtils.isEmpty(beanObj.getOverRidder()) ? "0": beanObj.getOverRidder();
			//obj[12] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
			//obj[13] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();

			obj[12] = StringUtils.isEmpty(beanObj.getPremium_Reserve()) ? "0": beanObj.getPremium_Reserve();
			obj[13] = StringUtils.isEmpty(beanObj.getLoss_reserve()) ? "0": beanObj.getLoss_reserve();
			obj[14] = StringUtils.isEmpty(beanObj.getInterest()) ? "0": beanObj.getInterest();
			obj[15] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
			obj[16] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
			obj[17] = StringUtils.isEmpty(beanObj.getLoss_Advise()) ? "0": beanObj.getLoss_Advise();
			obj[18] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": beanObj.getCash_Loss_Limit();
			obj[19] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
			obj[20] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0": beanObj.getLeader_Underwriter();
			obj[21] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0": beanObj.getLeader_Underwriter_share();
			obj[22] = StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj.getAccounts();
			obj[23] = StringUtils.isEmpty(beanObj.getExclusion()) ? "": beanObj.getExclusion();
			obj[24] = StringUtils.isEmpty(beanObj.getRemarks()) ? "" : beanObj.getRemarks();
			obj[25] = StringUtils.isEmpty(beanObj.getUnderwriter_Recommendations()) ? "" : beanObj.getUnderwriter_Recommendations();
			obj[26] = StringUtils.isEmpty(beanObj.getGms_Approval()) ? "": beanObj.getGms_Approval();
			obj[27] = "";
			obj[28] = "";
			//obj[31] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0": beanObj.getProfit_commission();
			obj[29] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0": beanObj.getOthercost();
			obj[30] = beanObj.getCrestaStatus();
			obj[31] = beanObj.getEvent_limit();
			obj[32] =getDesginationCountry(beanObj.getEvent_limit(),beanObj.getExchRate());
			obj[33] = beanObj.getAggregate_Limit();
			obj[34] =getDesginationCountry(beanObj.getAggregate_Limit(),beanObj.getExchRate());
			obj[35] = beanObj.getOccurrent_Limit();
			obj[36] =getDesginationCountry(beanObj.getOccurrent_Limit(),beanObj.getExchRate());
			obj[37] = beanObj.getSlideScaleCommission();
			obj[38] = beanObj.getLossParticipants();
			obj[39] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "": beanObj.getCommissionSubClass();
			obj[40]=beanObj.getDepartmentId();
			obj[41] = beanObj.getLoginId();
			obj[42] = beanObj.getBranchCode();
			obj[43] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country())?"":beanObj.getLeader_Underwriter_country();
			obj[44] =StringUtils.isEmpty(beanObj.getOrginalacqcost())?"":beanObj.getOrginalacqcost();
			obj[45] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost())?"":beanObj.getOurassessmentorginalacqcost();
			obj[46] = StringUtils.isEmpty(beanObj.getOuracqCost())?"":beanObj.getOuracqCost();

			obj[47] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass())?"":beanObj.getLosscommissionSubClass();
			obj[48] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass())?"":beanObj.getSlidecommissionSubClass();
			obj[49] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass())?"":beanObj.getCrestacommissionSubClass();
			if("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
				obj[50] = StringUtils.isEmpty(beanObj.getManagementExpenses())?"":beanObj.getManagementExpenses();
				obj[51] = StringUtils.isEmpty(beanObj.getCommissionType())?"":beanObj.getCommissionType();
				obj[52] = StringUtils.isEmpty(beanObj.getProfitCommissionPer())?"":beanObj.getProfitCommissionPer();
				obj[53] = StringUtils.isEmpty(beanObj.getSetup())?"":beanObj.getSetup();
				obj[54] = StringUtils.isEmpty(beanObj.getSuperProfitCommission())?"":beanObj.getSuperProfitCommission();
				obj[55] = StringUtils.isEmpty(beanObj.getLossCarried())?"":beanObj.getLossCarried();
				obj[56] = StringUtils.isEmpty(beanObj.getLossyear())?"":beanObj.getLossyear();
				obj[57] = StringUtils.isEmpty(beanObj.getProfitCarried())?"":beanObj.getProfitCarried();
				obj[58] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear())?"":beanObj.getProfitCarriedForYear();
				obj[59] = StringUtils.isEmpty(beanObj.getFistpc())?"":beanObj.getFistpc();
				obj[60] = StringUtils.isEmpty(beanObj.getProfitMont())?"":beanObj.getProfitMont();
				obj[61] = StringUtils.isEmpty(beanObj.getSubProfitMonth())?"":beanObj.getSubProfitMonth();
				obj[62] = StringUtils.isEmpty(beanObj.getSubpc())?"":beanObj.getSubpc();
				obj[63] = StringUtils.isEmpty(beanObj.getSubSeqCalculation())?"":beanObj.getSubSeqCalculation();
				obj[64] = StringUtils.isEmpty(beanObj.getProfitCommission())?"":beanObj.getProfitCommission();
			}
			else{
				obj[50] = "";
				obj[51] = "";
				obj[52] = "";
				obj[53] = "";
				obj[54] = "";
				obj[55] = "";
				obj[56] = "";
				obj[57] = "";
				obj[58] = "";
				obj[59] = "";
				obj[60] = "";
				obj[61] = "";
				obj[62] = "";
				obj[63] = "";
				obj[64] = "";
			}
			obj[65] =StringUtils.isEmpty(beanObj.getDocStatus())?"":beanObj.getDocStatus();
			obj[66] =StringUtils.isEmpty(beanObj.getCommissionQ_SYN())?"":beanObj.getCommissionQ_SYN();
			obj[67] =StringUtils.isEmpty(beanObj.getCommission_surpYN())?"":beanObj.getCommission_surpYN();
			obj[68] =StringUtils.isEmpty(beanObj.getOverRidderYN())?"":beanObj.getOverRidderYN();
			obj[69] =StringUtils.isEmpty(beanObj.getBrokerageYN())?"":beanObj.getBrokerageYN();
			obj[70] =StringUtils.isEmpty(beanObj.getTaxYN())?"":beanObj.getTaxYN();
			obj[71] =StringUtils.isEmpty(beanObj.getOthercostYN())?"":beanObj.getOthercostYN();
			obj[72] =StringUtils.isEmpty(beanObj.getCeedODIYN())?"":beanObj.getCeedODIYN();
			obj[73] =StringUtils.isEmpty(beanObj.getLocRate())?"":beanObj.getLocRate();
			obj[74] =StringUtils.isEmpty(beanObj.getRetroCommissionType())?"":beanObj.getRetroCommissionType();
		}else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[7];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = beanObj.getLayerNo();
			obj[3] = beanObj.getReinstNo();
			obj[4] = beanObj.getReinstAditionalPremium_percent();
			obj[5] = beanObj.getBurningCost();
			obj[6] = beanObj.getRemarks();
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] updateRiskDetailsSecondFormSecondTable(final RiskDetailsBean beanObj, final String productId,final String endNo) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("4")) {
			obj = new Object[71];
			obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0" : beanObj.getBrokerage();
			obj[1] = beanObj.getTax();
			obj[2] = beanObj.getShare_Profit_Commission();
			obj[3] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0" : beanObj.getAcquisition_Cost();
			obj[4] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getAcquisition_Cost(), beanObj.getExchRate());
			obj[5] = StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0" : beanObj.getCommissionQ_S();
			obj[6] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0" : beanObj.getCommission_surp();
			obj[7] = beanObj.getOverRidder();
			//obj[8] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
			//obj[9] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();
			obj[8] = beanObj.getPremium_Reserve();
			obj[9] = beanObj.getLoss_reserve();
			obj[10] = beanObj.getInterest();
			obj[11] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0" : beanObj.getPortfolio_inout_Premium();
			obj[12] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0" : beanObj.getPortfolio_inout_Loss();
			obj[13] = beanObj.getLoss_Advise();
			obj[14] = beanObj.getCash_Loss_Limit();
			obj[15] = getDesginationCountry(beanObj.getCash_Loss_Limit(), beanObj.getExchRate());
			obj[16] =StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "" :beanObj.getLeader_Underwriter();
			obj[17] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "" : beanObj.getLeader_Underwriter_share();
			obj[18] = beanObj.getAccounts();
			obj[19] = beanObj.getExclusion();
			obj[20] = beanObj.getRemarks();
			obj[21] = beanObj.getUnderwriter_Recommendations();
			obj[22] = beanObj.getGms_Approval();
			//obj[25] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0"	: beanObj.getProfit_commission();
			obj[23] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0" : beanObj.getOthercost();
			obj[24] = beanObj.getCrestaStatus();
			obj[25] = beanObj.getEvent_limit();
			obj[26] = getDesginationCountry(beanObj.getEvent_limit(), beanObj.getExchRate());
			obj[27] = beanObj.getAggregate_Limit();
			obj[28] = getDesginationCountry(beanObj.getAggregate_Limit(), beanObj.getExchRate());
			obj[29] = beanObj.getOccurrent_Limit();
			obj[30] = getDesginationCountry(beanObj.getOccurrent_Limit(), beanObj.getExchRate());
			obj[31] = beanObj.getSlideScaleCommission();
			obj[32] = beanObj.getLossParticipants();
			obj[33] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "" : beanObj.getCommissionSubClass();
			obj[34] = beanObj.getDepartmentId();
			obj[35] = beanObj.getLoginId();
			obj[36] = beanObj.getBranchCode();
			obj[37] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "" : beanObj.getLeader_Underwriter_country();
			obj[38] = StringUtils.isEmpty(beanObj.getOrginalacqcost()) ? "" : beanObj.getOrginalacqcost();
			obj[39] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost()) ? "" : beanObj.getOurassessmentorginalacqcost();
			obj[40] = StringUtils.isEmpty(beanObj.getOuracqCost()) ? "" : beanObj.getOuracqCost();
			obj[41] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass()) ? "" : beanObj.getLosscommissionSubClass();
			obj[42] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass()) ? "" : beanObj.getSlidecommissionSubClass();
			obj[43] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass()) ? "" : beanObj.getCrestacommissionSubClass();
			if ("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())) {
				obj[44] = StringUtils.isEmpty(beanObj.getManagementExpenses()) ? "" : beanObj.getManagementExpenses();
				obj[45] = StringUtils.isEmpty(beanObj.getCommissionType()) ? "" : beanObj.getCommissionType();
				obj[46] = StringUtils.isEmpty(beanObj.getProfitCommissionPer()) ? "" : beanObj.getProfitCommissionPer();
				obj[47] = StringUtils.isEmpty(beanObj.getSetup()) ? "" : beanObj.getSetup();
				obj[48] = StringUtils.isEmpty(beanObj.getSuperProfitCommission()) ? "" : beanObj.getSuperProfitCommission();
				obj[49] = StringUtils.isEmpty(beanObj.getLossCarried()) ? "" : beanObj.getLossCarried();
				obj[50] = StringUtils.isEmpty(beanObj.getLossyear()) ? "" : beanObj.getLossyear();
				obj[51] = StringUtils.isEmpty(beanObj.getProfitCarried()) ? "" : beanObj.getProfitCarried();
				obj[52] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear()) ? "" : beanObj.getProfitCarriedForYear();
				obj[53] = StringUtils.isEmpty(beanObj.getFistpc()) ? "" : beanObj.getFistpc();
				obj[54] = StringUtils.isEmpty(beanObj.getProfitMont()) ? "" : beanObj.getProfitMont();
				obj[55] = StringUtils.isEmpty(beanObj.getSubProfitMonth()) ? "" : beanObj.getSubProfitMonth();
				obj[56] = StringUtils.isEmpty(beanObj.getSubpc()) ? "" : beanObj.getSubpc();
				obj[57] = StringUtils.isEmpty(beanObj.getSubSeqCalculation()) ? "" : beanObj.getSubSeqCalculation();
				obj[58] = StringUtils.isEmpty(beanObj.getProfitCommission()) ? "" : beanObj.getProfitCommission();
			} else {
				obj[44] = "";
				obj[45] = "";
				obj[46] = "";
				obj[47] = "";
				obj[48] = "";
				obj[49] = "";
				obj[50] = "";
				obj[51] = "";
				obj[52] = "";
				obj[53] = "";
				obj[54] = "";
				obj[55] = "";
				obj[56] = "";
				obj[57] = "";
				obj[58] = "";
			}
			obj[59] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			obj[60] =StringUtils.isEmpty(beanObj.getCommissionQ_SYN())?"":beanObj.getCommissionQ_SYN();
			obj[61] =StringUtils.isEmpty(beanObj.getCommission_surpYN())?"":beanObj.getCommission_surpYN();
			obj[62] =StringUtils.isEmpty(beanObj.getOverRidderYN())?"":beanObj.getOverRidderYN();
			obj[63] =StringUtils.isEmpty(beanObj.getBrokerageYN())?"":beanObj.getBrokerageYN();
			obj[64] =StringUtils.isEmpty(beanObj.getTaxYN())?"":beanObj.getTaxYN();
			obj[65] =StringUtils.isEmpty(beanObj.getOthercostYN())?"":beanObj.getOthercostYN();
			obj[66] =StringUtils.isEmpty(beanObj.getCeedODIYN())?"":beanObj.getCeedODIYN();
			obj[67] =StringUtils.isEmpty(beanObj.getLocRate())?"":beanObj.getLocRate();
			obj[68] =StringUtils.isEmpty(beanObj.getRetroCommissionType())?"":beanObj.getRetroCommissionType();
			obj[69] = beanObj.getProposal_no();
			obj[70] = endNo;
		}else if (productId.equalsIgnoreCase("5")) {
			obj = new Object[7];
			obj[0] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0" : beanObj.getReinstNo();
			obj[1] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0": beanObj.getReinstAditionalPremium_percent();
			obj[2] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0": beanObj.getBurningCost();
			obj[3] = beanObj.getRemarks();
			obj[4] = beanObj.getProposal_no();
			obj[5] = beanObj.getLayerNo();
			obj[6] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] updateRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("4")) {
			obj = new Object[18];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
					.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
					.getExchRate());
			obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
			obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
					.getExchRate());
			obj[6] = beanObj.getXlcostOurShare();
			obj[7] = getDesginationCountry(beanObj.getXlcostOurShare(), beanObj
					.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getPremiumQuotaShare())?"0":beanObj.getPremiumQuotaShare();
			obj[9] = StringUtils.isBlank(beanObj.getPremiumSurplus())?"0":beanObj.getPremiumSurplus();
			obj[10]= StringUtils.isEmpty(beanObj.getPremiumQuotaShare())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumQuotaShare(), beanObj
					.getExchRate());
			obj[11]= StringUtils.isEmpty(beanObj.getPremiumSurplus())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumSurplus(), beanObj
					.getExchRate());
			obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt();
			obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt();
			obj[14]= StringUtils.isEmpty(beanObj.getCommissionQ_SAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommissionQ_SAmt(), beanObj
					.getExchRate());
			obj[15]= StringUtils.isEmpty(beanObj.getCommission_surpAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommission_surpAmt(), beanObj
					.getExchRate());
			obj[16] = beanObj.getProposal_no();
			obj[17] = endNo;
		} else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[4] = beanObj.getMd_premium_our_service();
			obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),beanObj.getExchRate());
			obj[6] = beanObj.getProposal_no();
			obj[7] = beanObj.getLayerNo();
			obj[8] = endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	private String getMaxproposalStatus(String proposalNo) {
		String result="";
		try{
			String query=getQuery(DBConstants.RISK_SELECT_MAXRSKSTATUS);
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+proposalNo);
			result =this.mytemplate.queryForObject(query,new String[]{proposalNo},String.class).toString();
		}catch(Exception e)
		{
			logger.debug("Exception @ {" + e + "}");
		}
		return result;
	}

	public Object[] updateContractRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId,final String endNo) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("4")) {
			obj = new Object[18];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
					.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
					.getExchRate());
			obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
			obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
					.getExchRate());
			obj[6] = beanObj.getXlcostOurShare();
			obj[7] = getDesginationCountry(beanObj.getXlcostOurShare(), beanObj
					.getExchRate());
			obj[8] = StringUtils.isBlank(beanObj.getPremiumQuotaShare())?"0":beanObj.getPremiumQuotaShare();
			obj[9] = StringUtils.isBlank(beanObj.getPremiumSurplus())?"0":beanObj.getPremiumSurplus();
			obj[10]= StringUtils.isEmpty(beanObj.getPremiumQuotaShare())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumQuotaShare(), beanObj
					.getExchRate());
			obj[11]= StringUtils.isEmpty(beanObj.getPremiumSurplus())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getPremiumSurplus(), beanObj
					.getExchRate());
			obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt();
			obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt();
			obj[14]= StringUtils.isEmpty(beanObj.getCommissionQ_SAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommissionQ_SAmt(), beanObj
					.getExchRate());
			obj[15]= StringUtils.isEmpty(beanObj.getCommission_surpAmt())
					|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getCommission_surpAmt(), beanObj
					.getExchRate());
			obj[16]=endNo;
			obj[17] = beanObj.getProposal_no();
		} else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] = beanObj.getLimitOurShare();
			obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[2] = beanObj.getEpiAsPerOffer();
			obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[4] = beanObj.getMd_premium_our_service();
			obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),	beanObj.getExchRate());
			obj[6] = endNo;
			obj[7] = beanObj.getProposal_no();
			obj[8] = beanObj.getLayerNo();
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] secondContarctPageCommissionAruguments(
			final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=new Object[0];
		if("4".equalsIgnoreCase(productId)){
			obj = new Object[4];
			obj[0] = beanObj.getProposal_no();
			obj[1] = (getMaxAmednId(beanObj.getProposal_no()))+"";
			obj[2] = beanObj.getLayerNo();
			obj[3] = beanObj.getRemarks();
		}else if("5".equalsIgnoreCase(productId)){
			obj = new Object[7];
			obj[0] = beanObj.getProposal_no();
			obj[1] = (getMaxAmednId(beanObj.getProposal_no()))+"";
			obj[2] = beanObj.getLayerNo();
			obj[3] = beanObj.getReinstNo();
			obj[4] = beanObj.getReinstAditionalPremium_percent();
			obj[5] = beanObj.getBurningCost();
			obj[6] = beanObj.getRemarks();
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public String getRetroContractDetails(RiskDetailsBean beanObj)  {
		String  Cedingco="";
		String query="";
		try{
			List<Map<String, Object>> list =null;
			query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
			logger.info("Select Query=>"+query);
			logger.info("Product Code=>"+beanObj.getProduct_id());
			logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
			logger.info("UW Year=>"+beanObj.getUwYear());
			logger.info("Inception Date=>"+beanObj.getIncepDate());
			logger.info("Branch Code=>"+beanObj.getBranchCode());
			list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate()});
			beanObj.setRetroContractList(list);
			if(list!=null && list.size()>0){
				logger.info("List<Map<String, Object>> Size=>"+list.size());
				Map<String, Object> resMap;
				for(int i=0;i<list.size();i++){
					resMap = (Map<String, Object>) list.get(i);
					if(i==(list.size()-1)){
						Cedingco+=resMap.get("CONTDET1").toString()+"~"+resMap.get("CONTDET2").toString();
					}else{
						Cedingco += resMap.get("CONTDET1").toString() + "~" + resMap.get("CONTDET2").toString() + "~";
					}
				}
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return Cedingco;
	}

	public boolean showLayerBrokerage(String layerProposalNo,RiskDetailsBean  formobj) {
		try {
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETBROKERAGE);
			Object[] args=new Object[2];
			args[0]=layerProposalNo;
			args[1]=layerProposalNo;
			logger.info("Select Query=>" + selectQry);
			logger.info("layerProposalNo Args[0]=>"+layerProposalNo);
			logger.info("layerProposalNo Args[1]=>"+layerProposalNo);
			if(StringUtils.isNotBlank(layerProposalNo)){
				List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
				Map<String, Object> resMap = null;
				if(res!=null && res.size()>0)
					resMap = (Map<String, Object>)res.get(0);
				if(resMap!=null){
					formobj.setBrokerage(resMap.get("RSK_BROKERAGE")==null?"":resMap.get("RSK_BROKERAGE").toString());
					formobj.setTax(resMap.get("RSK_TAX")==null?"":resMap.get("RSK_TAX").toString());
				}
			}
		}catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return false;
	}

	public boolean updateProportionalTreatyDao(final RiskDetailsBean beanObj,final String pid){
		boolean savFlg = false;
		try {
			String updateQry = "";
			Object[] args = getFirstPageSaveModeAruguments(beanObj, pid,getMaxAmednId(beanObj.getProposal_no()));
			updateQry = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
			if("5".equalsIgnoreCase(pid)){
				updateQry = updateQry + " AND RSK_LAYER_NO = ? ";
			}
			logger.info("Query=>"+updateQry);
			int updateCount = this.mytemplate.update(updateQry, args);
			logger.info("Result=>"+updateCount);
			if (updateCount > 0) {
				this.updateRiskProposal(beanObj, pid);
				InsertRemarkDetails(beanObj);
				GetRemarksDetails(beanObj);
				this.showSecondpageEditItems(beanObj, pid, beanObj.getProposal_no());
				savFlg = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}

	public String[] getFirstPageSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		if (pid.equalsIgnoreCase("4")) {
			args = new String[52];
		} else if ("5".equalsIgnoreCase(pid)) {
			args = new String[53];
		}
		args[0] = beanObj.getDepartId();
		args[1] = beanObj.getProfit_Center();
		args[2] = beanObj.getSubProfit_center();
		args[3] = beanObj.getPolBr();
		args[4] = beanObj.getCedingCo();
		args[5] = beanObj.getBroker();
		args[6] = beanObj.getTreatyName_type();
		args[7] = beanObj.getMonth();
		args[8] = beanObj.getUwYear();
		args[9] = beanObj.getUnderwriter();
		args[10] = beanObj.getIncepDate();
		args[11] = beanObj.getExpDate();
		args[12] = beanObj.getAccDate();
		args[13] = beanObj.getOrginalCurrency();
		args[14] = beanObj.getExchRate();
		if (pid.equalsIgnoreCase("4")) {
			args[15] = "0";
			args[16] = beanObj.getPnoc();
			args[17] = beanObj.getRiskCovered();
		} else if ("5".equalsIgnoreCase(pid)) {
			args[15] = beanObj.getBasis();
			args[16] = "";
			args[17] = "";
		}
		args[18] = beanObj.getTerritoryscope();
		args[19] = StringUtils.isBlank(beanObj.getTerritory())?"":beanObj.getTerritory();
		args[20] = beanObj.getProStatus();
		if (pid.equalsIgnoreCase("4")) {
			args[21] = beanObj.getProposalType();
			args[22] = beanObj.getAccountingPeriod();
			args[23] = beanObj.getReceiptofStatements();
			args[24] = beanObj.getReceiptofPayment();
			
		} else if ("5".equalsIgnoreCase(pid)) {
			args[21] = "";
			args[22] = "0";
			args[23] = "0";
			args[24] = "0";
			args[52] = beanObj.getLayerNo();
		}
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0": beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0" : beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "" : beanObj.getInsuredName();
		args[29]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[30]=StringUtils.isEmpty(beanObj.getTreatyType()) ? "0"	: beanObj.getTreatyType();
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[36] = beanObj.getLoginId();
		args[37] = beanObj.getBranchCode();
		args[38] =StringUtils.isEmpty( beanObj.getCountryIncludedList()) ? ""	: beanObj.getCountryIncludedList();
		args[39] =StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	: beanObj.getCountryExcludedList();
		args[40] =StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] = StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
		args[43] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[44] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[45] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[46] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[47] =StringUtils.isEmpty(beanObj.getCessionExgRate()) ? ""	:beanObj.getCessionExgRate();
		args[48] =StringUtils.isEmpty(beanObj.getFixedRate()) ? ""	:beanObj.getFixedRate();
		//args[49] =StringUtils.isEmpty(beanObj.getDummyCon()) ? ""	:beanObj.getDummyCon();
		args[49]=StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		args[50] = beanObj.getProposal_no();
		args[51]=endNo;
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	private boolean updateRiskProposal(final RiskDetailsBean beanObj,final String pid) {
		boolean saveFlag = false;
		try {
			String updateQry = "",endom="";
			int res=0;
			Object[] obj=null;
			endom=getQuery(DBConstants.RISK_SELECT_MAXENDOM);
			logger.info("Query=>"+endom);
			logger.info("Args[0]=>"+beanObj.getProposal_no());
			String endtNo=(String)this.mytemplate.queryForObject(endom, new Object[]{beanObj.getProposal_no()},String.class);
			logger.info("Result=>"+endtNo);
			obj = updateRiskProposalArgs(beanObj, pid,endtNo);
			if(pid.equalsIgnoreCase("4"))
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
			else if(pid.equalsIgnoreCase("5"))
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
			logger.info("updateQry " + updateQry);
			res=this.mytemplate.update(updateQry, obj);
			logger.info("Result=>" + res);
			if (res> 0) {
				saveFlag = true;
			}
			updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
			obj = updateHomePostion(beanObj, pid,true);
			updateQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
			logger.info("updateQry " + updateQry);
			res=this.mytemplate.update(updateQry, obj);
			logger.info("Result=>" + res);
			if (res > 0) {
				saveFlag = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return saveFlag;
	}

	public Object[] updateRiskProposalArgs(final RiskDetailsBean beanObj,final String pid,String endNo) {
		Object[] obj=null;
		if (pid.equalsIgnoreCase("4")) {
			obj = new Object[51];
			if("TR".equalsIgnoreCase(beanObj.getRetroType())){
				obj[0] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
				obj[1] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
				}
				else{
					obj[0] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
					obj[1] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
					}
			obj[2] = beanObj.getEpi_origCur();
			obj[3] = getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
			obj[4] = StringUtils.isBlank(beanObj.getOurEstimate())?"0":beanObj.getOurEstimate();
			obj[5] = StringUtils.isBlank(beanObj.getEpi())?"0":beanObj.getEpi();
			obj[6] = StringUtils.isEmpty(beanObj.getEpi()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[7] = beanObj.getXlCost();
			obj[8] = StringUtils.isEmpty(beanObj.getXlCost()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
			obj[9] =StringUtils.isEmpty(beanObj.getCedReten())?"":beanObj.getCedReten();
			obj[10] = beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[11] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[11] = beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[11] = "0";
			} else {
				obj[11] = "0";
			}
			obj[12] = StringUtils.isEmpty(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
			obj[13] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[14] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[15] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
			obj[16] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
			obj[17] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[18] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
			obj[19] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[20] =StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0": beanObj.getEpiAsPerShare();
			obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
			obj[22] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0"	: beanObj.getXlcostOurShare();
			obj[23] = StringUtils.isEmpty(beanObj.getXlcostOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
			obj[24] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
			obj[25] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[26] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[27] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[28] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
			obj[29] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
			obj[30] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
			obj[31] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
			if("TR".equalsIgnoreCase(beanObj.getRetroType())){
				obj[32] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
				obj[33] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
				}
				else{
					obj[32] =StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml()) ? "0": beanObj.getFaclimitOrigCurPml();
					obj[33] = StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCurPml(), beanObj.getExchRate());
					}
			obj[34] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) ? "0" : beanObj.getLimitOrigCurPmlOS();
			obj[35] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCurPmlOS(), beanObj.getExchRate());
			obj[36] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) ? "0" : beanObj.getTreatyLimitsurplusOCPml();
			obj[37] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getTreatyLimitsurplusOCPml(), beanObj.getExchRate());
			obj[38] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) ? "0" : beanObj.getTreatyLimitsurplusOCPmlOS();
			obj[39] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getTreatyLimitsurplusOCPmlOS(), beanObj.getExchRate());
			obj[40] = StringUtils.isEmpty(beanObj.getEpipml()) ? "0" : beanObj.getEpipml();
			obj[41] = StringUtils.isEmpty(beanObj.getEpipml()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpipml(), beanObj.getExchRate());
			obj[42] = StringUtils.isEmpty(beanObj.getEpipmlOS()) ? "0" : beanObj.getEpipmlOS();
			obj[43] = StringUtils.isEmpty(beanObj.getEpipmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpipmlOS(), beanObj.getExchRate());
			obj[44] = beanObj.getDepartId();
			obj[45] = beanObj.getLoginId();
			obj[46] = beanObj.getBranchCode();
			obj[47] = beanObj.getProposal_no();
			obj[47] = StringUtils.isEmpty(beanObj.getPml()) ? "" : beanObj.getPml();
			obj[48] = StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0.00" : beanObj.getPmlPercent();
			obj[49] = beanObj.getProposal_no();
			obj[50] = endNo;
			logger.info("Args[]=>" + StringUtils.join(obj, ","));
			return obj;
		}
		if ("5".equalsIgnoreCase(pid)) {
			obj = new Object[31];
			obj[0] = beanObj.getLimitOrigCur();
			obj[1] = getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
			obj[2] = beanObj.getEpi();
			obj[3] = getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
			obj[4] = beanObj.getShareWritt();
			if (beanObj.getProStatus().equalsIgnoreCase("P")) {
				obj[5] = "0";
			} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
				obj[5] = beanObj.getSharSign();
			} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
				obj[5] = "0";
			} else {
				obj[5] = "0";
			}
			obj[6] = StringUtils.isBlank(beanObj.getMaxLimit_Product())?"":beanObj.getMaxLimit_Product();
			obj[7] = beanObj.getSubPremium();
			obj[8] = getDesginationCountry(beanObj.getSubPremium(), beanObj.getExchRate());
			obj[9] = beanObj.getXlPremium();
			obj[10] = getDesginationCountry(beanObj.getXlPremium(), beanObj.getExchRate());
			obj[11] = beanObj.getPortfoloCovered();
			obj[12] = beanObj.getDeduc_hunPercent();
			obj[13] = getDesginationCountry(beanObj.getDeduc_hunPercent(),beanObj.getExchRate());
			obj[14] = beanObj.getM_dPremium();
			obj[15] = getDesginationCountry(beanObj.getM_dPremium(), beanObj.getExchRate());
			obj[16] = beanObj.getAdjRate();
			obj[17] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
			obj[18] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
			obj[19] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
			obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
			obj[21] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
			obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
			obj[23] =StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
			obj[24] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
			obj[25] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
			obj[26] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
			obj[27] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
			obj[28] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
			obj[29] = beanObj.getProposal_no();
			obj[30]=endNo;
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] updateHomePostion(final RiskDetailsBean beanObj,final String pid, final boolean bool) {
		Object[] obj = new Object[19];
		obj[0] = StringUtils.isEmpty(beanObj.getLayerNo()) ? "0" : beanObj.getLayerNo();
		obj[1] = "";
		obj[2] = pid;
		obj[3] = beanObj.getDepartId();
		obj[4] = beanObj.getCedingCo();
		obj[5] = beanObj.getUwYear();
		obj[6] = beanObj.getMonth();
		obj[7] = beanObj.getAccDate();
		obj[8] = beanObj.getIncepDate();
		obj[9] = beanObj.getExpDate();
		obj[10] = beanObj.getProStatus().trim();
		if(beanObj.getContractno()==null || beanObj.getContractno().equalsIgnoreCase("") ){
			if(beanObj.getProStatus().equalsIgnoreCase("P") || beanObj.getProStatus().equalsIgnoreCase("A")){
				obj[11] = "P";
			}
			else if(beanObj.getProStatus().equalsIgnoreCase("R")){
				obj[11] ="R";
			}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
				obj[11] ="N";
			}
		}
		else{
			obj[11] =beanObj.getProStatus().trim();
		}
		obj[12] = beanObj.getBroker();
		obj[13] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[14] = beanObj.getLoginId();
		obj[15] = StringUtils.isBlank(beanObj.getDummyCon())?"":beanObj.getDummyCon();
		obj[16] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[17] = beanObj.getProposal_no();
		obj[18] = beanObj.getEndorsmentno();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public void showRetroCess(RiskDetailsBean beanObj,int mode) {
		try{
			if(mode==1||mode==3){
				Object[] selectArgs = null;
				String selectQry = "";
				if(mode==1){
					selectArgs = new Object[2];
					selectArgs[0] = beanObj.getProposal_no();
					selectArgs[1] = (Integer.parseInt(beanObj.getNoRetroCess())-1);
					selectQry = getQuery(DBConstants.RISK_SELECT_GETRETROCESSDTLS);
				}else if(mode==3){
					selectArgs = new Object[3];
					selectArgs[0] = beanObj.getAmendId();
					selectArgs[1] = beanObj.getProposal_no();
					selectArgs[2] = (Integer.parseInt(beanObj.getNoRetroCess())-1);
					selectQry = getQuery(DBConstants.RISK_SELECT_VIEWRETROCESSDTLS);
				}
				logger.info("Select Query=>"+selectQry);
				logger.info("args[0]"+StringUtils.join(selectArgs,","));
				logger.info("NoRetroCess=>"+ beanObj.getNoRetroCess());
				List<Map<String, Object>> resultList = this.mytemplate.queryForList(selectQry, selectArgs);
				logger.info("List<Map<String, Object>> Size=>"+resultList.size());
				List<String> ceding = new ArrayList<String>();
				List<String> rBroker = new ArrayList<String>();
				List<String> pStatus = new ArrayList<String>();
				List<String> sWritt = new ArrayList<String>();
				List<String> sSigned = new ArrayList<String>();
				List<String> comm = new ArrayList<String>();
				if(resultList.size()!=0){
					for(int i = 0; i < resultList.size(); i++) {
						Map<String, Object> resMap = (Map<String, Object>)resultList.get(i);
						ceding.add(resMap.get("CEDING_COMPANY_ID").toString());
						rBroker.add(resMap.get("BROKER_ID").toString());
						pStatus.add(resMap.get("PROPOSAL_STATUS").toString());
						sWritt.add(resMap.get("SHARE_ACCEPTED").toString());
						sSigned.add(resMap.get("SHARE_SIGNED").toString());
						comm.add(resMap.get("COMISSION").toString());
					}
				}else{
					ceding.add(beanObj.getCedingId());
					rBroker.add(beanObj.getBrokerId());
					pStatus.add(beanObj.getProStatus());
					sWritt.add("");
					sSigned.add("");
				}
				beanObj.setCedingCompany(ceding);
				beanObj.setRetroBroker(rBroker);
				beanObj.setProposalStatus(pStatus);
				beanObj.setShareAccepted(sWritt);
				beanObj.setShareSigned(sSigned);
				beanObj.setCommission(comm);
			}else{
				int NoRetroCess=Integer.parseInt(beanObj.getNoRetroCess()==null?"0":beanObj.getNoRetroCess());
				List<String> retroCessList1=new ArrayList<String>();
				List<String> ceding = new ArrayList<String>();
				List<String> rBroker = new ArrayList<String>();
				List<String> pStatus = new ArrayList<String>();
				List<String> sWritt = new ArrayList<String>();
				List<String> sSigned = new ArrayList<String>();
				List<String> comm = new ArrayList<String>();
				for(int i=0;i<NoRetroCess;i++){
					retroCessList1.add(String.valueOf(i));
					ceding.add(beanObj.getCedingCompany().get(i));
					rBroker.add(beanObj.getRetroBroker().get(i));
					pStatus.add(beanObj.getProposalStatus().get(i));
					sWritt.add(beanObj.getShareAccepted().get(i));
					sSigned.add(beanObj.getShareSigned().get(i));
					comm.add(beanObj.getCommission().get(i));
				}
				beanObj.setCedingCompany(ceding);
				beanObj.setRetroBroker(rBroker);
				beanObj.setProposalStatus(pStatus);
				beanObj.setShareAccepted(sWritt);
				beanObj.setShareSigned(sSigned);
				beanObj.setCommission(comm);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
	}
	public String GetRiskDetailsEditQueryProduct3(boolean contractMode) {
		String selectQry = getQuery(DBConstants.RISK_SELECT_GETEDITMODEDATAPRO3);
		if(contractMode)
			selectQry = " "+selectQry +" "+ getQuery(DBConstants.RISK_SELECT_GETEDITMODEDATAPRO3CONTCOND);
		else
			selectQry =" "+ selectQry + " "+getQuery(DBConstants.RISK_SELECT_GETEDITMODEDATAPRO3PROCOND);
		logger.info("selectQry=>" + selectQry);
		return selectQry;
	}

	public boolean checkAvialability(final RiskDetailsBean beanObj,final String pid)  {
		boolean saveFlag = false;
		try{
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKPROIDBYPRONO);
			logger.info("selectQry " + selectQry);
			logger.info("Args[0]=>" + beanObj.getProposal_no());
			String result = this.mytemplate.queryForObject(selectQry,new String[]{beanObj.getProposal_no()},String.class).toString();
			logger.info("Result " + result);
			if (result.equals(pid)) {
				saveFlag = true;
			} else {
				saveFlag = false;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		return saveFlag;
	}

	public boolean viewRiskDetails(RiskDetailsBean beanObj, String pid)  {
		try {
			Object[] args=new Object[10];
			args[0] = beanObj.getBranchCode();
			args[1] = beanObj.getBranchCode();
			args[2] = beanObj.getBranchCode();
			args[3] = beanObj.getBranchCode();
			args[4] = beanObj.getBranchCode();
			args[5] = beanObj.getBranchCode();
			args[6] = beanObj.getBranchCode();
			args[7] = beanObj.getBranchCode();
			//args[8] = beanObj.getBranchCode();
			args[8] = beanObj.getProposal_no();
			args[9] = beanObj.getAmendId();
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETCOMMONDATA);
			logger.info("Query=>"+selectQry);
			logger.info("Args[]=>" +StringUtils.join(args,",") );
			List<Map<String, Object>> res = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" + res.size());
			Map<String, Object> resMap = null;
			if(res!=null && res.size()>0)
				resMap = (Map<String, Object>)res.get(0);
			if (resMap != null) {
				beanObj.setDepartId(resMap.get("RSK_DEPTID")==null?"":resMap.get("RSK_DEPTID").toString());
				beanObj.setDepartClass(resMap.get("TMAS_DEPARTMENT_NAME")==null?"":resMap.get("TMAS_DEPARTMENT_NAME").toString());
				beanObj.setDepartId(resMap.get("RSK_DEPTID")==null?"":resMap.get("RSK_DEPTID").toString());
				beanObj.setProposalType(resMap.get("PROPOSAL_TYPE")==null?"":resMap.get("PROPOSAL_TYPE").toString());
				beanObj.setAccountingPeriod(resMap.get("ACCOUNTING_PERIOD")==null?"":resMap.get("ACCOUNTING_PERIOD").toString());
				beanObj.setReceiptofPayment(resMap.get("RSK_RECEIPT_PAYEMENT")==null?"":resMap.get("RSK_RECEIPT_PAYEMENT").toString());
				beanObj.setReceiptofStatements(resMap.get("RSK_RECEIPT_STATEMENT")==null?"":resMap.get("RSK_RECEIPT_STATEMENT").toString());
				beanObj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
				beanObj.setSubProfit_center(resMap.get("RSK_SPFCID")==null?"":resMap.get("RSK_SPFCID").toString());
				if(!"ALL".equalsIgnoreCase(beanObj.getSubProfit_center())){
				beanObj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME")==null?"":resMap.get("TMAS_SPFC_NAME").toString().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
				beanObj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				beanObj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				beanObj.setTreatyName_type(resMap.get("RSK_TREATYID")==null?"":resMap.get("RSK_TREATYID").toString());
				beanObj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
				beanObj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME")==null?"":resMap.get("TMAS_POL_BRANCH_NAME").toString());
				beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
				beanObj.setUwYear(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
				beanObj.setUnderwriter(resMap.get("UNDERWRITTER")==null?"":resMap.get("UNDERWRITTER").toString());
				beanObj.setIncepDate(resMap.get("RSK_INCEPTION_DATE")==null?"":resMap.get("RSK_INCEPTION_DATE").toString());
				beanObj.setExpDate(resMap.get("RSK_EXPIRY_DATE")==null?"":resMap.get("RSK_EXPIRY_DATE").toString());
				beanObj.setAccDate(resMap.get("RSK_ACCOUNT_DATE")==null?"":resMap.get("RSK_ACCOUNT_DATE").toString());
				beanObj.setOrginalCurrency(resMap.get("SHORT_NAME")==null?"":resMap.get("SHORT_NAME").toString());
				beanObj.setExchRate(resMap.get("RSK_EXCHANGE_RATE")==null?"":resMap.get("RSK_EXCHANGE_RATE").toString());
				beanObj.setM_d_InstalmentNumber(resMap.get("MND_INSTALLMENTS")==null?"0":resMap.get("MND_INSTALLMENTS").toString());
				beanObj.setRetroType(resMap.get("RSK_RETRO_TYPE")==null?"0":resMap.get("RSK_RETRO_TYPE").toString());
				beanObj.setRetroTypeName(resMap.get("RSK_RETRO_TYPE")==null?"0":resMap.get("RSK_RETRO_TYPE").toString());
				beanObj.setNoRetroCess(resMap.get("RETRO_CESSIONARIES")==null?"0":resMap.get("RETRO_CESSIONARIES").toString());
				beanObj.setCessionExgRate(resMap.get("RSK_CESSION_EXG_RATE")==null?"":resMap.get("RSK_CESSION_EXG_RATE").toString());
				beanObj.setFixedRate(resMap.get("RSK_FIXED_RATE")==null?"":resMap.get("RSK_FIXED_RATE").toString());
				beanObj.setDummyCon(resMap.get("RSK_DUMMY_CONTRACT")==null?"":resMap.get("RSK_DUMMY_CONTRACT").toString());
				if (resMap.get("RSK_BASIS") != null && !"0".equals(resMap.get("RSK_BASIS"))) {
					beanObj.setBasis(this.mytemplate.queryForObject(getQuery(DBConstants.RISK_SELECT_GETDTLNAME),new String[]{resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString()},String.class).toString());
				}
				beanObj.setPnoc(resMap.get("RSK_PERIOD_OF_NOTICE")==null?"":resMap.get("RSK_PERIOD_OF_NOTICE").toString());
				beanObj.setRiskCovered(resMap.get("RSK_RISK_COVERED")==null?"":resMap.get("RSK_RISK_COVERED").toString());
				beanObj.setTerritoryscope(resMap.get("RSK_TERRITORY_SCOPE")==null?"":resMap.get("RSK_TERRITORY_SCOPE").toString());
				beanObj.setTerritory(resMap.get("TERRITORY_DESC")==null?"":resMap.get("TERRITORY_DESC").toString());
				beanObj.setTreatyTypeName(resMap.get("TREATYTYPE") == null ? "" : resMap.get("TREATYTYPE").toString());
				beanObj.setTreatyType(resMap.get("TREATYTYPEID") == null ? "" : resMap.get("TREATYTYPEID").toString());
				beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED") == null ? "" : resMap.get("RSK_LOC_ISSUED").toString());
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED") == null ? "" : resMap.get("RSK_PERILS_COVERED").toString());
				if("0".equalsIgnoreCase(beanObj.getPerilCovered())){
					beanObj.setPerilCovered("None");
				}else{
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED_Con")==null ? "" : resMap.get("RSK_PERILS_COVERED_Con").toString());
				}
				beanObj.setTreatynoofLine(resMap.get("RSK_NO_OF_LINE") == null ? "0" : resMap.get("RSK_NO_OF_LINE").toString());
				
				if (resMap.get("RSK_STATUS") != null) {
					if (resMap.get("RSK_STATUS").toString().equalsIgnoreCase("P")||"0".equalsIgnoreCase(resMap.get("RSK_STATUS").toString())) {
						beanObj.setStatus("Pending");
					}else if (resMap.get("RSK_STATUS").toString().equalsIgnoreCase("N")) {
						beanObj.setStatus("Not Taken Up");
					} else if (resMap.get("RSK_STATUS").toString().equalsIgnoreCase("A")) {
						beanObj.setStatus("Accepted");
					} else if (resMap.get("RSK_STATUS").toString().equalsIgnoreCase("R")) {
						beanObj.setStatus("Rejected");
					} else {
						beanObj.setStatus("Pending");
					}
				}
				beanObj.setLayerNo(resMap.get("RSK_LAYER_NO")==null?"":resMap.get("RSK_LAYER_NO").toString());
				beanObj.setRunoffYear(resMap.get("RSK_RUN_OFF_YEAR")==null?"":resMap.get("RSK_RUN_OFF_YEAR").toString());
				beanObj.setLocBankName(resMap.get("RSK_LOC_BNK_NAME")==null?"":resMap.get("RSK_LOC_BNK_NAME").toString());
				beanObj.setLocCreditPrd(resMap.get("RSK_LOC_CRDT_PRD")==null?"":resMap.get("RSK_LOC_CRDT_PRD").toString());
				beanObj.setLocCreditAmt(resMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(resMap.get("RSK_LOC_CRDT_AMT").toString()));
				beanObj.setLocBeneficerName(resMap.get("RSK_LOC_BENFCRE_NAME")==null?"":resMap.get("RSK_LOC_BENFCRE_NAME").toString());
				beanObj.setInsuredName(resMap.get("RSK_INSURED_NAME")==null?"":resMap.get("RSK_INSURED_NAME").toString());
				beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString());
				String qry = "SELECT   RTRIM(XMLAGG(XMLELEMENT(E,TERRITORY_NAME,',')).EXTRACT('//text()'),',')  FROM   TMAS_TERRITORY  WHERE  TERRITORY_ID in("+beanObj.getTerritory()+") and BRANCH_CODE="+beanObj.getBranchCode();
				if(StringUtils.isNotBlank(beanObj.getTerritory())){
				beanObj.setTerritoryName(this.mytemplate.queryForObject(qry,String.class));
				}
				beanObj.setCountryIncludedList(resMap.get("COUNTRIES_INCLUDE")==null?"":resMap.get("COUNTRIES_INCLUDE").toString());
				if(StringUtils.isNotBlank(beanObj.getCountryIncludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+beanObj.getCountryIncludedList()+") and BRANCH_CODE="+beanObj.getBranchCode();
				beanObj.setCountryIncludedName(this.mytemplate.queryForObject(qry,String.class));
				beanObj.setCountryIncludedName(beanObj.getCountryIncludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
				beanObj.setCountryExcludedList(resMap.get("COUNTRIES_EXCLUDE")==null?"":resMap.get("COUNTRIES_EXCLUDE").toString());
				if(StringUtils.isNotBlank(beanObj.getCountryExcludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+beanObj.getCountryExcludedList()+") and BRANCH_CODE="+beanObj.getBranchCode();
				beanObj.setCountryExcludedName(this.mytemplate.queryForObject(qry,String.class));
				beanObj.setCountryExcludedName(beanObj.getCountryExcludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
			}
			args = new String[2];
			args[0] = beanObj.getProposal_no();
			args[1] = beanObj.getAmendId();
			selectQry = getQuery(DBConstants.RISK_SELECT_GETSECONDVIEWDATA);
			logger.info("selectQry " + selectQry);
			logger.info("Args[]=>" +StringUtils.join(args,",") );
			List<Map<String, Object>> res1 = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" + res1.size());
			Map<String, Object> secViewDataMap = null;
			if(res1!=null && res1.size()>0)
				secViewDataMap = (Map<String, Object>)res1.get(0);
			if (secViewDataMap != null) {
				beanObj.setSharSign(DropDownControllor.formatter(secViewDataMap.get("RSK_SHARE_SIGNED")==null?"":secViewDataMap.get("RSK_SHARE_SIGNED").toString()));
				if("TR".equalsIgnoreCase(beanObj.getRetroType())){
					beanObj.setLimitOrigCur(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OC")==null?"":secViewDataMap.get("RSK_LIMIT_OC").toString()));
					beanObj.setLimitOrig_CurDc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_DC")==null?"":secViewDataMap.get("RSK_LIMIT_DC").toString()));
					beanObj.setLimitOrigCurOSOC(getShareVal(beanObj.getLimitOrigCur().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitOrigCurOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitOrigCurOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));

				}
				else{
					beanObj.setFaclimitOrigCur(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OC")==null?"":secViewDataMap.get("RSK_LIMIT_OC").toString()));
					beanObj.setFacLimitOrig_CurDc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_DC")==null?"":secViewDataMap.get("RSK_LIMIT_DC").toString()));
					beanObj.setFaclimitOrigCurOSOC(getShareVal(beanObj.getFaclimitOrigCur().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setFaclimitOrigCurOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getFaclimitOrigCurOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
				}
				
				beanObj.setEpi_origCur(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OFFER_OC")==null?"":secViewDataMap.get("RSK_EPI_OFFER_OC").toString()));
				beanObj.setEpi_origCur_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OFFER_DC")==null?"":secViewDataMap.get("RSK_EPI_OFFER_DC").toString()));
				beanObj.setOurEstimate(secViewDataMap.get("RSK_EPI_ESTIMATE")==null?"":secViewDataMap.get("RSK_EPI_ESTIMATE").toString());
				beanObj.setXlPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_XLPREM_OC")==null?"":secViewDataMap.get("RSK_XLPREM_OC").toString()));
				beanObj.setXlPremium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_XLPREM_DC")==null?"":secViewDataMap.get("RSK_XLPREM_DC").toString()));
				beanObj.setDeduc_hunPercent(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUC_OC")==null?"":secViewDataMap.get("RSK_DEDUC_OC").toString()));
				beanObj.setEpiEstmate(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_EST_OC")==null?"":secViewDataMap.get("RSK_EPI_EST_OC").toString()));
				beanObj.setEpiEstmate_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_EST_DC")==null?"":secViewDataMap.get("RSK_EPI_EST_DC").toString()));
				beanObj.setEpiEstmateOSOC(getShareVal(beanObj.getEpiEstmate().replaceAll(",", ""),beanObj.getSharSign(),"share"));
				beanObj.setEpiEstmateOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getEpiEstmateOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
				beanObj.setXlCost(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_OC")==null?"":secViewDataMap.get("RSK_XLCOST_OC").toString()));
				beanObj.setXlCost_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_DC")==null?"":secViewDataMap.get("RSK_XLCOST_DC").toString()));
				beanObj.setCedReten(DropDownControllor.formatter(secViewDataMap.get("RSK_CEDANT_RETENTION")==null?"":secViewDataMap.get("RSK_CEDANT_RETENTION").toString()));
				beanObj.setShareWritt(DropDownControllor.formatter(secViewDataMap.get("RSK_SHARE_WRITTEN")==null?"":secViewDataMap.get("RSK_SHARE_WRITTEN").toString()));
				beanObj.setM_dPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_OC")==null?"":secViewDataMap.get("RSK_MD_PREM_OC").toString()));
				beanObj.setMd_premium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_DC")==null?"":secViewDataMap.get("RSK_MD_PREM_DC").toString()));
				beanObj.setAdjRate(DropDownControllor.formatter(secViewDataMap.get("RSK_ADJRATE")==null?"":secViewDataMap.get("RSK_ADJRATE").toString()));
				beanObj.setPortfoloCovered(secViewDataMap.get("RSK_PF_COVERED")==null?"":secViewDataMap.get("RSK_PF_COVERED").toString());
				beanObj.setSubPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_SUBJ_PREMIUM_OC")==null?"":secViewDataMap.get("RSK_SUBJ_PREMIUM_OC").toString()));
				beanObj.setSubPremium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_SUBJ_PREMIUM_DC")==null?"":secViewDataMap.get("RSK_SUBJ_PREMIUM_DC").toString()));
				beanObj.setMaxLimit_Product(DropDownControllor.formatter(secViewDataMap.get("RSK_MAX_LMT_COVER")==null?"":secViewDataMap.get("RSK_MAX_LMT_COVER").toString()));
				beanObj.setDeduc_hunPercent_Dc(secViewDataMap.get("RSK_DEDUC_DC")==null?"":secViewDataMap.get("RSK_DEDUC_DC").toString());
				beanObj.setLimitOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OS_OC")==null?"":secViewDataMap.get("RSK_LIMIT_OS_OC").toString()));
				beanObj.setLimitOurShare_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OS_DC")==null?"":secViewDataMap.get("RSK_LIMIT_OS_DC").toString()));
				beanObj.setEpiAsPerOffer(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOF_OC")==null?"":secViewDataMap.get("RSK_EPI_OSOF_OC").toString()));
				beanObj.setEpiAsPerOffer_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOF_DC")==null?"":secViewDataMap.get("RSK_EPI_OSOF_DC").toString()));
				beanObj.setEpiOurShareEs(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOE_OC")==null?"":secViewDataMap.get("RSK_EPI_OSOE_OC").toString()));
				beanObj.setEpiOurShareEs_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOE_DC")==null?"":secViewDataMap.get("RSK_EPI_OSOE_DC").toString()));
				beanObj.setXlcostOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_OS_OC")==null?"":secViewDataMap.get("RSK_XLCOST_OS_OC").toString()));
				beanObj.setXlcostOurShare_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_OS_DC")==null?"":secViewDataMap.get("RSK_XLCOST_OS_DC").toString()));
				beanObj.setMd_premium_our_service(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_OS_OC")==null?"":secViewDataMap.get("RSK_MD_PREM_OS_OC").toString()));
				beanObj.setMd_premium_our_service_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_OS_DC")==null?"":secViewDataMap.get("RSK_MD_PREM_OS_DC").toString()));
				beanObj.setSpRetro(secViewDataMap.get("RSK_SP_RETRO")==null?"0":secViewDataMap.get("RSK_SP_RETRO").toString());
				beanObj.setNo_Insurer(secViewDataMap.get("RSK_NO_OF_INSURERS")==null?"0":secViewDataMap.get("RSK_NO_OF_INSURERS").toString());
				beanObj.setMaxLimit_Product(DropDownControllor.formatter(secViewDataMap.get("RSK_MAX_LMT_COVER")==null?"0":secViewDataMap.get("RSK_MAX_LMT_COVER").toString()));
				beanObj.setCedRetenType(secViewDataMap.get("CEDRET_TYPE")==null?"0":secViewDataMap.get("CEDRET_TYPE").toString());
				beanObj.setPml(secViewDataMap.get("RSK_PML") == null ? "" : secViewDataMap.get("RSK_PML").toString());
				beanObj.setPmlPercent(secViewDataMap.get("RSK_PML_PERCENT") == null ? "" : secViewDataMap.get("RSK_PML_PERCENT").toString());
				if("TR".equalsIgnoreCase(beanObj.getRetroType())){
					beanObj.setLimitOrigCurPml(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_OC") == null ? "0" : secViewDataMap.get("RSK_TRTY_LMT_PML_OC").toString()));
					beanObj.setLimitOrigCurPmlDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_DC") == null ? "0" : secViewDataMap.get("RSK_TRTY_LMT_PML_DC").toString()));
					}
					else{
						beanObj.setFaclimitOrigCurPml(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_OC") == null ? "0" : secViewDataMap.get("RSK_TRTY_LMT_PML_OC").toString()));
						beanObj.setFaclimitOrigCurPmlDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_DC") == null ? "0" : secViewDataMap.get("RSK_TRTY_LMT_PML_DC").toString()));
					}
					beanObj.setTreatyLimitsurplusOCPml(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OC") == null ? "" : secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OC").toString()));
					beanObj.setTreatyLimitsurplusOCPmlDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_DC") == null ? "" : secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_DC").toString()));
					beanObj.setTreatyLimitsurplusOC(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC") == null ? "" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC").toString() == null ? "" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC").toString()));
					beanObj.setTreatyLimitsurplusDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC") == null ? "" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC").toString().equalsIgnoreCase("0") ? "0" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC").toString() == null ? "" : secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC").toString()));
					beanObj.setTreatyLimitsurplusOCOSOC(getShareVal(beanObj.getTreatyLimitsurplusOC().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setTreatyLimitsurplusOCOSDC(getDesginationCountry(beanObj.getTreatyLimitsurplusOCOSOC().replaceAll(",",""),beanObj.getExchRate()).toString());
			}

			args = new String[3];
			args[0] = beanObj.getProposal_no();
			args[1] = beanObj.getLayerNo();
			args[2] = beanObj.getAmendId();
			int number = 0;
			selectQry = getQuery(DBConstants.RISK_SELECT_VIEWINSTALMENTDATA);
			logger.info("Select Query=>" + selectQry);
			logger.info("Args[]=>" +StringUtils.join(args,","));
			List<Map<String, Object>> instalmentList = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" +instalmentList.size());
			if (instalmentList != null) {
				List<String> dateList = new ArrayList<String>();
				List<String> premiumList = new ArrayList<String>();
				for (number = 0; number < instalmentList.size(); number++) {
					Map<String, Object> insMap = (Map<String, Object>)instalmentList.get(number);
					//beanObj.getRequest().setAttribute("instalmentDate" + number, insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					//beanObj.getRequest().setAttribute("installmentPremium" + number, DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
					dateList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					premiumList.add(DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
				}
				beanObj.setInstalmentDateList(dateList);
				beanObj.setInstallmentPremium(premiumList);
				//beanObj.getRequest().setAttribute("K", number);
			}
			args = new String[5];
			args[0] = beanObj.getBranchCode();
			args[1] = beanObj.getProposal_no();
			args[2] = beanObj.getAmendId();
			args[3] = beanObj.getProposal_no();
			args[4] = beanObj.getAmendId();
			selectQry = getQuery(DBConstants.RISK_SELECT_GETTHIRDPAGEDATA);
			logger.info("Select Query=>" + selectQry);
			logger.info("Args[]=>" +StringUtils.join(args,",") );
			List<Map<String, Object>> res3 = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" +res3.size());
			Map<String, Object> thirdViewDataMap = null;
			if(res3!=null && res3.size()>0)
				thirdViewDataMap = (Map<String, Object>)res3.get(0);
			if (thirdViewDataMap != null) {
				for (int k = 0; k < thirdViewDataMap.size(); k++) {
					beanObj.setBrokerage(thirdViewDataMap.get("RSK_BROKERAGE")==null?"":thirdViewDataMap.get("RSK_BROKERAGE").toString());
					beanObj.setTax(DropDownControllor.formatter(thirdViewDataMap.get("RSK_TAX")==null?"":thirdViewDataMap.get("RSK_TAX").toString()));
					if (thirdViewDataMap.get("RSK_PROFIT_COMM") != null) {
						if (thirdViewDataMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("1")) {
							beanObj.setProfit_Comm("YES");
						} else {
							beanObj.setProfit_Comm("NO");
						}
					}
					beanObj.setPremiumQuotaShare(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE")==null?"":thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE").toString()));
					beanObj.setPremiumSurplus(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_SURPULS")==null?"":thirdViewDataMap.get("RSK_PREMIUM_SURPULS").toString()));
					beanObj.setPremiumQuotaShare_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE_DC")==null?"":thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE_DC").toString()));
					beanObj.setPremiumSurplus_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_SURPLUS_DC")==null?"":thirdViewDataMap.get("RSK_PREMIUM_SURPLUS_DC").toString()));
					beanObj.setPremiumQuotaShareOSOC(getShareVal(beanObj.getPremiumQuotaShare().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setPremiumSurplusOSOC(getShareVal(beanObj.getPremiumSurplus().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setPremiumQuotaShareOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getPremiumQuotaShareOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setPremiumSurplusOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getPremiumSurplusOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					
					beanObj.setAcquisition_Cost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_ACQUISTION_COST_OC")==null?"":thirdViewDataMap.get("RSK_ACQUISTION_COST_OC").toString()));
					beanObj.setAcquisition_Cost_Dc(thirdViewDataMap.get("RSK_ACQUISTION_COST_DC")==null?"":thirdViewDataMap.get("RSK_ACQUISTION_COST_DC").toString());
					beanObj.setCommissionQ_S(DropDownControllor.formatter(thirdViewDataMap.get("RSK_COMM_QUOTASHARE")==null?"":thirdViewDataMap.get("RSK_COMM_QUOTASHARE").toString()));
					beanObj.setCommission_surp(DropDownControllor.formatter(thirdViewDataMap.get("RSK_COMM_SURPLUS")==null?"":thirdViewDataMap.get("RSK_COMM_SURPLUS").toString()));
					beanObj.setOverRidder(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OVERRIDER_PERC")==null?"":thirdViewDataMap.get("RSK_OVERRIDER_PERC").toString()));
					beanObj.setManagement_Expenses(thirdViewDataMap.get("RSK_MANAGEMENT_EXPENSES")==null?"":thirdViewDataMap.get("RSK_MANAGEMENT_EXPENSES").toString());
					beanObj.setLossC_F(thirdViewDataMap.get("RSK_LOSS_CARRYFORWARD")==null?"":thirdViewDataMap.get("RSK_LOSS_CARRYFORWARD").toString());
					beanObj.setPremium_Reserve(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_RESERVE")==null?"":thirdViewDataMap.get("RSK_PREMIUM_RESERVE").toString()));
					beanObj.setLoss_reserve(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSS_RESERVE")==null?"":thirdViewDataMap.get("RSK_LOSS_RESERVE").toString()));
					beanObj.setInterest(DropDownControllor.formatter(thirdViewDataMap.get("RSK_INTEREST")==null?"":thirdViewDataMap.get("RSK_INTEREST").toString()));
					beanObj.setPortfolio_inout_Premium(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PF_INOUT_PREM")==null?"":thirdViewDataMap.get("RSK_PF_INOUT_PREM").toString()));
					beanObj.setPortfolio_inout_Loss(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PF_INOUT_LOSS")==null?"":thirdViewDataMap.get("RSK_PF_INOUT_LOSS").toString()));
					beanObj.setLoss_Advise(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSSADVICE")==null?"":thirdViewDataMap.get("RSK_LOSSADVICE").toString()));
					beanObj.setCash_Loss_Limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC").toString()));
					beanObj.setCash_Loss_Limit_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC").toString()));
					beanObj.setAnualAggregateLiability(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIAB_OC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_LIAB_OC").toString()));
					beanObj.setAnualAggregateLiability_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC").toString()));
					beanObj.setReinstNo(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_NO")==null?"":thirdViewDataMap.get("RSK_REINSTATE_NO").toString()));
					beanObj.setReinstAditionalPremium_percent(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString()));
					beanObj.setReinstAditionalPremium_percent_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC").toString()));
					beanObj.setLeader_Underwriter(thirdViewDataMap.get("RSK_LEAD_UW")==null?"":thirdViewDataMap.get("RSK_LEAD_UW").toString());
					beanObj.setLeader_Underwriter_share(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LEAD_UW_SHARE")==null?"":thirdViewDataMap.get("RSK_LEAD_UW_SHARE").toString()));
					beanObj.setAccounts(thirdViewDataMap.get("RSK_ACCOUNTS")==null?"":thirdViewDataMap.get("RSK_ACCOUNTS").toString());
					beanObj.setExclusion(thirdViewDataMap.get("RSK_EXCLUSION")==null?"":thirdViewDataMap.get("RSK_EXCLUSION").toString());
					beanObj.setRemarks(thirdViewDataMap.get("RSK_REMARKS")==null?"":thirdViewDataMap.get("RSK_REMARKS").toString());
					beanObj.setUnderwriter_Recommendations(thirdViewDataMap.get("RSK_UW_RECOMM")==null?"":thirdViewDataMap.get("RSK_UW_RECOMM").toString());
					beanObj.setGms_Approval(thirdViewDataMap.get("RSK_GM_APPROVAL")==null?"":thirdViewDataMap.get("RSK_GM_APPROVAL").toString());
					beanObj.setDecision(thirdViewDataMap.get("RSK_DECISION") == null ? "" : thirdViewDataMap.get("RSK_DECISION").toString());
					beanObj.setOthercost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OTHER_COST")==null?"":thirdViewDataMap.get("RSK_OTHER_COST").toString()));
					beanObj.setReinstAdditionalPremium(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setCommissionQ_SAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_QS_AMT")==null?"":thirdViewDataMap.get("COMM_QS_AMT").toString()));
					beanObj.setCommission_surpAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_SURPLUS_AMT")==null?"":thirdViewDataMap.get("COMM_SURPLUS_AMT").toString()));
					beanObj.setAcqCostPer(DropDownControllor.formatter(thirdViewDataMap.get("OTHER_ACQ_COST_AMT")==null?"":thirdViewDataMap.get("OTHER_ACQ_COST_AMT").toString()));
					beanObj.setReinstAditionalPremium_percent_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setBrokerage((thirdViewDataMap.get("RSK_BROKERAGE")==null?"":thirdViewDataMap.get("RSK_BROKERAGE").toString()));
					beanObj.setLimitPerVesselOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_OC")==null?"":thirdViewDataMap.get("LIMIT_PER_VESSEL_OC").toString()));
					beanObj.setLimitPerVesselDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_VESSEL_DC").toString()));
					beanObj.setLimitPerLocationOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_OC")==null?"":thirdViewDataMap.get("LIMIT_PER_LOCATION_OC").toString()));
					beanObj.setLimitPerLocationDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_LOCATION_DC").toString()));
					beanObj.setProfitCommission(thirdViewDataMap.get("RSK_PRO_NOTES")==null?"":thirdViewDataMap.get("RSK_PRO_NOTES").toString());
					beanObj.setManagementExpenses(thirdViewDataMap.get("RSK_PRO_MANAGEMENT_EXP")==null?"":thirdViewDataMap.get("RSK_PRO_MANAGEMENT_EXP").toString());
					beanObj.setCommissionType(thirdViewDataMap.get("RSK_PRO_COMM_TYPE")==null?"":thirdViewDataMap.get("RSK_PRO_COMM_TYPE").toString());
					beanObj.setProfitCommissionPer(thirdViewDataMap.get("RSK_PRO_COMM_PER")==null?"":thirdViewDataMap.get("RSK_PRO_COMM_PER").toString());
					beanObj.setSetup(thirdViewDataMap.get("RSK_PRO_SET_UP")==null?"":thirdViewDataMap.get("RSK_PRO_SET_UP").toString());
					beanObj.setSuperProfitCommission(thirdViewDataMap.get("RSK_PRO_SUP_PRO_COM")==null?"":thirdViewDataMap.get("RSK_PRO_SUP_PRO_COM").toString());
					beanObj.setLossCarried(thirdViewDataMap.get("RSK_PRO_LOSS_CARY_TYPE")==null?"":thirdViewDataMap.get("RSK_PRO_LOSS_CARY_TYPE").toString());
					beanObj.setLossyear(thirdViewDataMap.get("RSK_PRO_LOSS_CARY_YEAR")==null?"":thirdViewDataMap.get("RSK_PRO_LOSS_CARY_YEAR").toString());
					beanObj.setProfitCarried(thirdViewDataMap.get("RSK_PRO_PROFIT_CARY_TYPE")==null?"":thirdViewDataMap.get("RSK_PRO_PROFIT_CARY_TYPE").toString());
					beanObj.setProfitCarriedForYear(thirdViewDataMap.get("RSK_PRO_PROFIT_CARY_YEAR")==null?"":thirdViewDataMap.get("RSK_PRO_PROFIT_CARY_YEAR").toString());
					beanObj.setFistpc(thirdViewDataMap.get("RSK_PRO_FIRST_PFO_COM")==null?"":thirdViewDataMap.get("RSK_PRO_FIRST_PFO_COM").toString());
					beanObj.setProfitMont(thirdViewDataMap.get("RSK_PRO_FIRST_PFO_COM_PRD")==null?"":thirdViewDataMap.get("RSK_PRO_FIRST_PFO_COM_PRD").toString());
					beanObj.setSubProfitMonth(thirdViewDataMap.get("RSK_PRO_SUB_PFO_COM_PRD")==null?"":thirdViewDataMap.get("RSK_PRO_SUB_PFO_COM_PRD").toString());
					beanObj.setSubpc(thirdViewDataMap.get("RSK_PRO_SUB_PFO_COM")==null?"":thirdViewDataMap.get("RSK_PRO_SUB_PFO_COM").toString());
					beanObj.setSubSeqCalculation(thirdViewDataMap.get("RSK_PRO_SUB_SEQ_CAL")==null?"":thirdViewDataMap.get("RSK_PRO_SUB_SEQ_CAL").toString());
					beanObj.setCrestaStatus(thirdViewDataMap.get("RSK_CREASTA_STATUS")==null?"":thirdViewDataMap.get("RSK_CREASTA_STATUS").toString());
					beanObj.setSlideScaleCommission(thirdViewDataMap.get("RSK_SLADSCALE_COMM")==null?"":thirdViewDataMap.get("RSK_SLADSCALE_COMM").toString());
					beanObj.setLossParticipants(thirdViewDataMap.get("RSK_LOSS_PART_CARRIDOR")==null?"":thirdViewDataMap.get("RSK_LOSS_PART_CARRIDOR").toString());
					beanObj.setShare_Profit_Commission(thirdViewDataMap.get("RSK_PROFIT_COMM")==null?"":thirdViewDataMap.get("RSK_PROFIT_COMM").toString());
					beanObj.setLocRate(thirdViewDataMap.get("RSK_RATE")==null?"":thirdViewDataMap.get("RSK_RATE").toString());
					beanObj.setRetroCommissionType(thirdViewDataMap.get("RSK_COMMISSION_TYPE")==null?"":thirdViewDataMap.get("RSK_COMMISSION_TYPE").toString());
					beanObj.setLeader_Underwriter_country(thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
					beanObj.setLoss_Advise(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSSADVICE")==null?"":thirdViewDataMap.get("RSK_LOSSADVICE").toString()));
					beanObj.setLoss_Advise_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSSADVICE_DC")==null?"":thirdViewDataMap.get("RSK_LOSSADVICE_DC").toString()));
					beanObj.setLoss_AdviseOSOC(getShareVal(beanObj.getLoss_Advise().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLoss_AdviseOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLoss_AdviseOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setCash_Loss_Limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC").toString()));
					beanObj.setCash_Loss_Limit_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC").toString()));
					beanObj.setCash_Loss_LimitOSOC(getShareVal(beanObj.getCash_Loss_Limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setCash_Loss_LimitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getCash_Loss_LimitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setEvent_limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_EVENT_LIMIT_OC")==null?"0":thirdViewDataMap.get("RSK_EVENT_LIMIT_OC").toString()));
					beanObj.setEvent_limit_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_EVENT_LIMIT_DC")==null?"0":thirdViewDataMap.get("RSK_EVENT_LIMIT_DC").toString()));
					beanObj.setEvent_limitOSOC(getShareVal(beanObj.getEvent_limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setEvent_limitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getEvent_limitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setAggregate_Limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIMIT_OC")==null?"0":thirdViewDataMap.get("RSK_AGGREGATE_LIMIT_OC").toString()));
					beanObj.setAggregate_Limit_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIMIT_DC")==null?"0":thirdViewDataMap.get("RSK_AGGREGATE_LIMIT_DC").toString()));
					beanObj.setAggregate_LimitOSOC(getShareVal(beanObj.getAggregate_Limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setAggregate_LimitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getAggregate_LimitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setOccurrent_Limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_OC")==null?"0":thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_OC").toString()));
					beanObj.setOccurrent_Limit_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_DC")==null?"0":thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_DC").toString()));
					beanObj.setOccurrent_LimitOSOC(getShareVal(beanObj.getOccurrent_Limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setOccurrent_LimitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getOccurrent_LimitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setCommissionQ_SYN(thirdViewDataMap.get("RSK_COMMISSION_QS_YN") == null ? "" : thirdViewDataMap.get("RSK_COMMISSION_QS_YN").toString());
					beanObj.setCommission_surpYN(thirdViewDataMap.get("RSK_COMMISSION_SUR_YN") == null ? "" : thirdViewDataMap.get("RSK_COMMISSION_SUR_YN").toString());
					beanObj.setOverRidderYN(thirdViewDataMap.get("RSK_OVERRIDE_YN") == null ? "" : thirdViewDataMap.get("RSK_OVERRIDE_YN").toString());
					beanObj.setBrokerageYN(thirdViewDataMap.get("RSK_BROKARAGE_YN") == null ? "" : thirdViewDataMap.get("RSK_BROKARAGE_YN").toString());
					beanObj.setTaxYN(thirdViewDataMap.get("RSK_TAX_YN") == null ? "" : thirdViewDataMap.get("RSK_TAX_YN").toString());
					beanObj.setOthercostYN(thirdViewDataMap.get("RSK_OTHER_COST_YN") == null ? "" : thirdViewDataMap.get("RSK_OTHER_COST_YN").toString());
					beanObj.setCeedODIYN(thirdViewDataMap.get("RSK_CEED_ODI_YN") == null ? "" : thirdViewDataMap.get("RSK_CEED_ODI_YN").toString());
					
					if("NR".equalsIgnoreCase(beanObj.getLocRate())){
						beanObj.setLocRate("On Net Rate");
					}
					else if("GR".equalsIgnoreCase(beanObj.getLocRate())){
						beanObj.setLocRate("On Gross Rate");
					}
				}
			}
			showRetroContracts(beanObj,pid,true);
			GetRemarksDetails(beanObj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();

		}
		return true;
	}
	private String getShareVal(String lossAdvise, String signedShare, String type) {
		String res="";
		try{
			if(StringUtils.isBlank(lossAdvise)){
				lossAdvise="0";
			}
			if(StringUtils.isBlank(signedShare)){
				signedShare="0";
			}
			if("share".equalsIgnoreCase(type)){
			res=Double.toString((Double.parseDouble(lossAdvise)*Double.parseDouble(signedShare))/100);
			}
			res=DropDownControllor.formatter(res);
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return res;
	}
	public void showRetroContracts(RiskDetailsBean beanObj,String productId,boolean view)  {/*
		try{
	 		Object[] args=null;
		 	String query="";
			beanObj.getRequest().setAttribute("NoInsurar",beanObj.getNo_Insurer());
		 	if(view){
		 		args=new Object[3];
		 		args[0]=beanObj.getAmendId();
		 		args[1]=beanObj.getProposal_no();
		 		args[2]=beanObj.getNo_Insurer();
		 		query=getQuery(DBConstants.FAC_SELECT_VIEWINSDETAILS);
		 	}else{
		 		query=getQuery(DBConstants.FAC_SELECT_INSDETAILS);
		 		args=new String[2];
		 		args[0]=beanObj.getProposal_no();
		 		args[1]=beanObj.getNo_Insurer();
		 	}
			logger.info("Query=>" + query);
			logger.info("Args[]=>"+StringUtils.join(args,","));
	 		List<Map<String, Object>> insDetailsList=this.mytemplate.queryForList(query,args);
	 		logger.info("Result Size=>"+insDetailsList.size());
	 		if(insDetailsList!=null&&insDetailsList.size()>0){
		 		for(int j=0;j<insDetailsList.size();j++)
		 		{
		 			Map<String, Object> insDetailsMap=(Map<String, Object>)insDetailsList.get(j);
		 			if("R".equalsIgnoreCase((String)insDetailsMap.get("TYPE")))
		 			{
		 				beanObj.getRequest().setAttribute("RetentionPercentage",insDetailsMap.get("RETRO_PER").toString());
		 			}else
		 			{
		 				beanObj.getRequest().setAttribute("cedingCompany"+(j-1),insDetailsMap.get("CONTRACTNO").toString());
		 				beanObj.getRequest().setAttribute("RetroPercentage"+(j-1),insDetailsMap.get("RETRO_PER").toString());
		 				beanObj.getRequest().setAttribute("uwYear"+(j-1),insDetailsMap.get("UW_YEAR").toString());
		 				beanObj.getRequest().setAttribute("retroType"+(j-1),insDetailsMap.get("RETRO_TYPE").toString());
			 		    RiskDetailsBean bean=new RiskDetailsBean();
			 		    logger.info("ProductCode=>"+productId);
			 		    if("2".equals(productId)){
			 		    	bean.setProduct_id("4");
			 		    	bean.setRetroType("TR");
			 		    }else if("3".equals(productId)){
			 		    	bean.setProduct_id("5");
			 		    }
			 		    bean.setBranchCode(beanObj.getBranchCode());
			 		    bean.setIncepDate(beanObj.getIncepDate());
			 		    beanObj.getRequest().setAttribute("UWYearList"+(j-1),getRetroContractDetailsList(bean,1));
		 		    	bean.setUwYear(insDetailsMap.get("UW_YEAR").toString());
		 		    	beanObj.getRequest().setAttribute("RetroContracts"+(j-1),getRetroContractDetailsList(bean,2));
		 			}
		 		}
	 		}else
	 		{
	 			for(int i=0;i<Integer.parseInt(StringUtils.isBlank(beanObj.getNo_Insurer())?"0":beanObj.getNo_Insurer());i++)
	 			{
	 				  RiskDetailsBean bean=new RiskDetailsBean();
	 		 		    logger.info("ProductCode=>"+productId);
	 		 		    if("2".equals(productId)){
	 		 		    	bean.setProduct_id("4");
	 		 		    	bean.setRetroType("TR");
	 		 		    }else if("3".equals(productId)){
	 		 		    	bean.setProduct_id("5");
	 		 		    }
	 		 		    bean.setBranchCode(beanObj.getBranchCode());
	 		 		    bean.setIncepDate(beanObj.getIncepDate());
	 		 		    beanObj.getRequest().setAttribute("UWYearList"+i,getRetroContractDetailsList(bean,1));
	 			}
	 		}
		}catch(Exception e)
		{
			logger.debug("Exception @ {" + e + "}");
		}
	 */}

	public String getShortname(RiskDetailsBean bean) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] { bean.getBranchCode()}, String.class);
		return Short;
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String getEndDate(RiskDetailsBean bean) {
		String result="";
		try {
			result=(String)this.mytemplate.queryForObject(getQuery("GET_END_DATE"), new Object[] { bean.getEndDate()}, String.class);
		} catch (DataAccessException e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return result;
	}
	public void insertRetroDetails(RiskDetailsBean bean) {
		logger.info("Enter into insertRetroDetails ");
		try {
			Object obj[]=new Object[4];
			obj[0]=bean.getStartDate();
			obj[1]=bean.getEndDate();
			obj[2]=bean.getBranchCode();
			obj[3]="I";
			String query=getQuery("GET_RETRO_PROCESSING");
			logger.info("Query==>"+query);
			logger.info("obj==>"+StringUtils.join(obj,","));
			int result=this.mytemplate.update(query,obj);
		} catch (DataAccessException e) {
			logger.debug("Exception @ {" + e + "}");
		}
		logger.info("Exist into insertRetroDetails ");

	}


	public boolean updateFirstPageFields(final RiskDetailsBean beanObj, String endNo) {
		boolean updateStatus = true;
		int res = 0;
		String query = getQuery("UPDATE_RISK_PROPOSAL_DETAILS");
		Object[] obj = new Object[41];
		try {
			obj[0] = StringUtils.isEmpty(beanObj.getEvent_limit()) ? "" : beanObj.getEvent_limit();
			obj[1] = StringUtils.isEmpty(beanObj.getEvent_limit()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEvent_limit(), beanObj.getExchRate());
			obj[2] = StringUtils.isEmpty(beanObj.getEventLimitOurShare()) ? "0" : beanObj.getEventLimitOurShare();
			obj[3] = StringUtils.isEmpty(beanObj.getEventLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEventLimitOurShare(), beanObj.getExchRate());

			obj[4] = StringUtils.isEmpty(beanObj.getCoverLimitXL()) ? "" : beanObj.getCoverLimitXL();
			obj[5] = StringUtils.isEmpty(beanObj.getCoverLimitXL()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getCoverLimitXL(), beanObj.getExchRate());
			obj[6] = StringUtils.isEmpty(beanObj.getCoverLimitXLOurShare()) ? "0" : beanObj.getCoverLimitXLOurShare();
			obj[7] = StringUtils.isEmpty(beanObj.getCoverLimitXLOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getCoverLimitXLOurShare(), beanObj.getExchRate());

			obj[8] = StringUtils.isEmpty(beanObj.getDeductLimitXL()) ? "" : beanObj.getDeductLimitXL();
			obj[9] = StringUtils.isEmpty(beanObj.getDeductLimitXL()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getDeductLimitXL(), beanObj.getExchRate());
			obj[10] = StringUtils.isEmpty(beanObj.getDeductLimitXLOurShare()) ? "0" : beanObj.getDeductLimitXLOurShare();
			obj[11] = StringUtils.isEmpty(beanObj.getDeductLimitXLOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getDeductLimitXLOurShare(), beanObj.getExchRate());

			obj[12] = StringUtils.isEmpty(beanObj.getPml()) ? "" : beanObj.getPml();
			if ("Y".equalsIgnoreCase(beanObj.getPml())) {
				obj[13] = StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0" : beanObj.getPmlPercent();

				obj[14] = StringUtils.isEmpty(beanObj.getEgnpipml()) ? "" : beanObj.getEgnpipml();
				obj[15] = StringUtils.isEmpty(beanObj.getEgnpipml()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpipml(), beanObj.getExchRate());
				obj[16] = StringUtils.isEmpty(beanObj.getEgnpipmlOurShare()) ? "0" : beanObj.getEgnpipmlOurShare();
			} else {
				obj[13] = "";
				obj[14] = "";
				obj[15] = "";
				obj[16] = "";
			}
			obj[17] = StringUtils.isEmpty(beanObj.getEgnpipmlOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpipmlOurShare(), beanObj.getExchRate());

			obj[18] = StringUtils.isEmpty(beanObj.getPremiumbasis()) ? "" : beanObj.getPremiumbasis();
			obj[19] = StringUtils.isEmpty(beanObj.getMinimumRate()) ? "0" : beanObj.getMinimumRate();
			obj[20] = StringUtils.isEmpty(beanObj.getMaximumRate()) ? "0" : beanObj.getMaximumRate();
			obj[21] = StringUtils.isEmpty(beanObj.getBurningCostLF()) ? "0" : beanObj.getBurningCostLF();

			obj[22] = StringUtils.isEmpty(beanObj.getMinPremium()) ? "" : beanObj.getMinPremium();
			obj[23] = StringUtils.isEmpty(beanObj.getMinPremium()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMinPremium(), beanObj.getExchRate());
			obj[24] = StringUtils.isEmpty(beanObj.getMinPremiumOurShare()) ? "0" : beanObj.getMinPremiumOurShare();
			obj[25] = StringUtils.isEmpty(beanObj.getMinPremiumOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMinPremiumOurShare(), beanObj.getExchRate());
			obj[26] = StringUtils.isEmpty(beanObj.getPaymentDuedays()) ? "0" : beanObj.getPaymentDuedays();

			if("TR".equalsIgnoreCase(beanObj.getRetroType())){
				obj[27] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
				obj[28] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
				}
				else{
					obj[27] =StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml()) ? "0": beanObj.getFaclimitOrigCurPml();
					obj[28] = StringUtils.isEmpty(beanObj.getFaclimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCurPml(), beanObj.getExchRate());
					}
			obj[29] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) ? "0" : beanObj.getLimitOrigCurPmlOS();
			obj[30] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getLimitOrigCurPmlOS(), beanObj.getExchRate());
			obj[31] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) ? "0" : beanObj.getTreatyLimitsurplusOCPml();
			obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getTreatyLimitsurplusOCPml(), beanObj.getExchRate());
			obj[33] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) ? "0" : beanObj.getTreatyLimitsurplusOCPmlOS();
			obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getTreatyLimitsurplusOCPmlOS(), beanObj.getExchRate());
			obj[35] = StringUtils.isEmpty(beanObj.getEpipml()) ? "0" : beanObj.getEpipml();
			obj[36] = StringUtils.isEmpty(beanObj.getEpipml()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpipml(), beanObj.getExchRate());
			obj[37] = StringUtils.isEmpty(beanObj.getEpipmlOS()) ? "0" : beanObj.getEpipmlOS();
			obj[38] = StringUtils.isEmpty(beanObj.getEpipmlOS()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEpipmlOS(), beanObj.getExchRate());

			obj[39] = beanObj.getProposal_no();
			obj[40] = endNo;
			logger.info("Args[]=>" + StringUtils.join(obj, ","));
			logger.info("updateQry " + query);
			res = this.mytemplate.update(query, obj);
			logger.info("Result=>" + res);
			if (res > 0) {
				updateStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;

	}

	private String getMaxAmednIdPro(final String proposalNo) {
		String result = "";
		try {
			String query = getQuery("GET_MAX_AMEND_RISK_PROPOSAL");
			logger.info("Query=>" + query);
			logger.info("Args[0]=>" + proposalNo);
			result = this.mytemplate.queryForObject(query, new String[]{proposalNo}, String.class).toString();
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	public int getCrestaCount(RiskDetailsBean bean) {
		int count = 0;
		try {
			String query = getQuery("GET_CRESTA_DETAIL_COUNT");
			Object[] obj = new Object[3];
			obj[0] = bean.getProposal_no();
			obj[1] = bean.getAmendId();
			obj[2] = bean.getBranchCode();
			logger.info("Query=>" + query);
			logger.info("Args=>" + StringUtils.join(obj, ","));
			count = this.mytemplate.queryForInt(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getBonusListCount(RiskDetailsBean bean, String type) {
		String query = "";
		Object args[] = null;
		int result = 0;
		try {
			query = getQuery("BONUS_COUNT_MAIN");
			args = new Object[5];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			if ("scale".equalsIgnoreCase(type)) {
				args[2] = "SSC";
			} else {
				args[2] = "LPC";
			}
			args[3] = bean.getAmendId();
			args[4] = StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();;
			result = this.mytemplate.queryForInt(query, args);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int CommissionTypeCount(RiskDetailsBean bean) {
		int count = 0;
		try {
			String query = getQuery("COMMISSION_TYPE_COUNT");
			Object args[] = new Object[3];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			args[2] = bean.getCommissionType();
			count = this.mytemplate.queryForInt(query, args);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return count;
	}
	public String doUploadDocDetails(List<Object[]> list, Object[] args) {
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
		return result;
	}

	public String PreviouRetroTypeChect(RiskDetailsBean bean) {
		String result="";
		try{
			String query = getQuery("GET_RETRO_TYPE");
			Object args[]=new Object[2];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			result = this.mytemplate.queryForObject(query,args,String.class);
		}
		catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
			e.printStackTrace();
		}
		return result;
	}
	private void InsertRemarkDetails(RiskDetailsBean beanObj) {
		try {
			String  deleteQuery=getQuery("DELETE_REMARKS_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposal_no();
			dobj[1]="0";
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_REMARKS_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_RISK_REMARKS WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposal_no()},String.class);
			for(int i=0;i<beanObj.getRemarkSNo().size();i++){
				Object[] obj= new Object[12];
				obj[0]=beanObj.getProposal_no();
				obj[1]=beanObj.getContractNo();
				obj[2]="0";
				obj[3]=beanObj.getDepartmentId();
				obj[4]=beanObj.getProduct_id();
				obj[5]=amendId;
				obj[6]=i+1;
				obj[7]=beanObj.getDescription().get(i);
				obj[8]=beanObj.getRemark1().get(i);
				obj[9]=beanObj.getRemark2().get(i);
				obj[10]=beanObj.getLoginId();
				obj[11]=beanObj.getBranchCode();
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void GetRemarksDetails(RiskDetailsBean beanObj) {
		try {
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_REMARKS_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=beanObj.getProposal_no();
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
				beanObj.setDescription(description);
				beanObj.setRemark1(remark1);
				beanObj.setRemark2(remark2);
				beanObj.setRemarkCount(Integer.toString(result.size()));
				
				beanObj.setRemarkList(result);
				
			}else{
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				 beanObj.setRemarkList(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}
