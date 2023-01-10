package com.maan.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.maan.bean.RiskDetailsBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.XolDAO;

public class XolDAOImpl extends MyJdbcTemplate implements XolDAO {

	Logger logger = LogUtil.getLogger(this.getClass());

	public boolean firstInsert(final RiskDetailsBean beanObj, final String pid,	boolean saveFlag, final boolean amendId)  {
		boolean savFlg = false, ChkSavFlg=false;
		if("Renewal".equalsIgnoreCase(beanObj.getRenewalEditMode())){
		ChkSavFlg = true;
		}
		try {
			String sql = "";
			Object[] args=null;
			if (saveFlag) {
				if (ChkSavFlg){
					String maxAmendID= getMaxAmednId(beanObj.getProposal_no());
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,maxAmendID);
					sql ="UPDATE ttrn_risk_details SET RSK_LAYER_NO=? WHERE RSK_PROPOSAL_NUMBER=? AND RSK_ENDORSEMENT_NO=?";
					Object argus[] =new Object[3];
					argus[1] = beanObj.getProposal_no();
					argus[2]=maxAmendID;
					argus[0] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
					this.mytemplate.update(sql,argus);
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
					sql = sql + " AND RSK_LAYER_NO = ? ";
					logger.info("Query=>"+sql);
					int updateCount = this.mytemplate.update(sql, args);
					logger.info("Result=>"+updateCount);
					args[1]=(Integer.parseInt(maxAmendID)+1)+"";
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
			} 
			else {
				String maxAmendID = getMaxAmednId(beanObj.getProposal_no());
				if (maxAmendID.equalsIgnoreCase(beanObj.getAmendId()) ) {
					args = getFirstPageInsertAruguments(beanObj, pid, amendId);
					sql = getQuery(DBConstants.RISK_INSERT_ISAMENDIDPROTREATY);
					logger.info("insert qry=>" + sql);
					int res=this.mytemplate.update(sql,args);
					logger.info("Result=>" + res);
				}
				else{
					sql ="UPDATE ttrn_risk_details SET RSK_LAYER_NO=? WHERE RSK_PROPOSAL_NUMBER=? AND RSK_ENDORSEMENT_NO=?";
					Object argus[] =new Object[3];
					argus[1] = beanObj.getProposal_no();
					argus[2]=maxAmendID;
					argus[0] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
					this.mytemplate.update(sql,argus);
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,maxAmendID);
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
					sql = sql + " AND RSK_LAYER_NO = ? ";
					logger.info("Query=>"+sql);
					int updateCount = this.mytemplate.update(sql, args);
					logger.info("Result=>"+updateCount);
					args[1]=(Integer.parseInt(maxAmendID)+1)+"";
				}
			}
			insertClassLimit(beanObj);
			InsertRemarkDetails(beanObj);
			UpadateUWShare(beanObj);
			savFlg = insertRiskProposal(beanObj,saveFlag,pid,ChkSavFlg,amendId,(String)args[1]);
			//savFlg = true;
		} catch (Exception e) {
			saveFlag = false;
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}
	private void UpadateUWShare(RiskDetailsBean beanObj) {
		String query="";
		try {
			query=getQuery("UPDATE_UW_SHARE");
			this.mytemplate.update(query,new Object[]{beanObj.getShareSigned(),beanObj.getProposal_no(),beanObj.getProposal_no()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public boolean checkProductMatch(final String pid, final String proposal,final boolean contarctMode){

		boolean saveFlag = false;
		try{
			String result = "",selectQry = "";
			if (contarctMode) {
				selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKPROIDBYCONTNO);
			} else {
				selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKPROIDBYPRONO);
			}
			logger.info("selectQry " + selectQry);
			logger.info("Args[0]=>" + proposal);
			result = this.mytemplate.queryForObject(selectQry,new String[]{proposal},String.class).toString();
			logger.info("Result=>" + result);
			if (pid.equalsIgnoreCase(result)) {
				saveFlag = true;
			} else {
				saveFlag = false;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}

		return saveFlag;
	}


	public String getRetroContractDetails(RiskDetailsBean beanObj)  {
		String  Cedingco="";
		String query="";
		try{
			List<Map<String, Object>> list =null;
			/*if(StringUtils.isNotEmpty(beanObj.getUwYear())&&"Dup".equalsIgnoreCase(beanObj.getMode())){
				query = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate()});
				beanObj.setRetroContractList(list);
			}
			else {*/
			//query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
			query = getQuery("FAC_SELECT_RETROCONTDET_23");
			logger.info("Select Query=>"+query);
			logger.info("Product Code=>"+beanObj.getProduct_id());
			//logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
			logger.info("UW Year=>"+beanObj.getUwYear());
			logger.info("Inception Date=>"+beanObj.getIncepDate());
			logger.info("Branch Code=>"+beanObj.getBranchCode());
			list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),beanObj.getUwYear(),beanObj.getIncepDate()});
			//list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate()});
			beanObj.setRetroContractList(list);
		//	}
			if(list!=null && list.size()>0){
				logger.info("List<Map<String, Object>> Size=>"+list.size());
				Map<String, Object> resMap;
				for(int i=0;i<list.size();i++) 
				{
					resMap = (Map<String, Object>)list.get(i); 
					if(i==(list.size()-1))
					{
						Cedingco+=resMap.get("CONTDET1").toString()+"~"+resMap.get("CONTDET2").toString();
					}else
					{
						Cedingco+=resMap.get("CONTDET1").toString()+"~"+resMap.get("CONTDET2").toString()+"~";	
					}
				}
			}
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return Cedingco;	
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
						//comm.add(resMap.get("COMISSION").toString());
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
				//beanObj.setCommission(comm);
			}else{
				int NoRetroCess=Integer.parseInt(beanObj.getNoRetroCess()==null?"0":beanObj.getNoRetroCess());
				List<String> ceding = new ArrayList<String>();
				List<String> rBroker = new ArrayList<String>();
				List<String> pStatus = new ArrayList<String>();
				List<String> sWritt = new ArrayList<String>();
				List<String> sSigned = new ArrayList<String>();
				List<String> comm = new ArrayList<String>();
				for(int i=0;i<NoRetroCess;i++){
					ceding.add(beanObj.getCedingCompany().get(i));
					rBroker.add(beanObj.getRetroBroker().get(i));
					pStatus.add(beanObj.getProposalStatus().get(i));
					sWritt.add(beanObj.getShareAccepted().get(i));
					sSigned.add(beanObj.getShareSigned().get(i));
					//comm.add(beanObj.getCommission().get(i));
				}
				beanObj.setCedingCompany(ceding);
				beanObj.setRetroBroker(rBroker);
				beanObj.setProposalStatus(pStatus);
				beanObj.setShareAccepted(sWritt);
				beanObj.setShareSigned(sSigned);
				//beanObj.setCommission(comm);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	public void showRetroCess1(RiskDetailsBean beanObj,int mode) {
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
						//comm.add(resMap.get("COMISSION").toString());
					}
				}else{
					ceding.add(beanObj.getCedingId());
					rBroker.add(beanObj.getBrokerId());
					pStatus.add(beanObj.getProStatus());
					sWritt.add(beanObj.getShareWritt().toString());
					sSigned.add(beanObj.getSharSign().toString());
				}
				beanObj.setCedingCompany(ceding);
				beanObj.setRetroBroker(rBroker);
				beanObj.setProposalStatus(pStatus);
				beanObj.setShareAccepted(sWritt);
				beanObj.setShareSigned(sSigned);
				beanObj.setCommission(comm);
			}else{
				int NoRetroCess=Integer.parseInt(beanObj.getNoRetroCess()==null?"0":beanObj.getNoRetroCess());
				List<String> ceding = new ArrayList<String>();
				List<String> rBroker = new ArrayList<String>();
				List<String> pStatus = new ArrayList<String>();
				List<String> sWritt = new ArrayList<String>();
				List<String> sSigned = new ArrayList<String>();
				List<String> comm = new ArrayList<String>();
				for(int i=0;i<NoRetroCess;i++){
					ceding.add(beanObj.getCedingCompany().get(i));
					rBroker.add(beanObj.getRetroBroker().get(i));
					pStatus.add(beanObj.getProposalStatus().get(i));
					sWritt.add(beanObj.getShareAccepted().get(i));
					sSigned.add(beanObj.getShareSigned().get(i));
					//comm.add(beanObj.getCommission().get(i));
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
			e.printStackTrace();
		}
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
				if(resMap!=null)
				{
					formobj.setBrokerage(resMap.get("RSK_BROKERAGE")==null?"":resMap.get("RSK_BROKERAGE").toString());
					formobj.setTax(resMap.get("RSK_TAX")==null?"":resMap.get("RSK_TAX").toString());
				}
			}
		}catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return false;
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
			if(resMap!=null)
			{
				formobj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
				//formobj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
				formobj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME")==null?"":resMap.get("TMAS_SPFC_NAME").toString()); 
				formobj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				formobj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				formobj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
				formobj.setUnderwriter(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
				formobj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME")==null?"":resMap.get("TMAS_POL_BRANCH_NAME").toString()); 
				formobj.setDepartClass(resMap.get("TMAS_DEPARTMENT_NAME")==null?"":resMap.get("TMAS_DEPARTMENT_NAME").toString());
				formobj.setEndttypename(resMap.get("DETAIL_NAME")==null?"":resMap.get("DETAIL_NAME").toString());
				formobj.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)", new Object[]{proposal}, String.class));

			}
			/*if(StringUtils.isNotBlank(formobj.getNo_Insurer()) && Integer.parseInt(formobj.getNo_Insurer())>0 && (formobj.getRetroFinalList()==null || formobj.getRetroFinalList().size()==0)){
				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<Integer.parseInt(formobj.getNo_Insurer());i++){
					retroFinalList.add(getRetroContractDetailsList(formobj,2));
				}
				formobj.setRetroFinalList(retroFinalList);
			}*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
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
			e.printStackTrace();
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
				beanObj.setNoRetroCess(resMap.get("RETRO_CESSIONARIES")==null?"0":resMap.get("RETRO_CESSIONARIES").toString());

				if (resMap.get("RSK_BASIS") != null && !"0".equals(resMap.get("RSK_BASIS"))) {
					beanObj.setBasis(this.mytemplate.queryForObject(getQuery(DBConstants.RISK_SELECT_GETDTLNAME),new String[]{resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString()},String.class).toString());
				}
				beanObj.setPnoc(resMap.get("RSK_PERIOD_OF_NOTICE")==null?"":resMap.get("RSK_PERIOD_OF_NOTICE").toString());
				beanObj.setRiskCovered(resMap.get("RSK_RISK_COVERED")==null?"":resMap.get("RSK_RISK_COVERED").toString());
				beanObj.setTerritoryscope(resMap.get("RSK_TERRITORY_SCOPE")==null?"":resMap.get("RSK_TERRITORY_SCOPE").toString());
				beanObj.setTerritory(resMap.get("TERRITORY_DESC")==null?"":resMap.get("TERRITORY_DESC").toString());
				beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString());
				beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
				String qry = "SELECT   RTRIM(XMLAGG(XMLELEMENT(E,TERRITORY_NAME,',')).EXTRACT('//text()'),',')  FROM   TMAS_TERRITORY  WHERE  TERRITORY_ID in("+beanObj.getTerritory()+") and BRANCH_CODE="+beanObj.getBranchCode();
				Object arg[]=new Object[2];
				if(StringUtils.isNotBlank(beanObj.getTerritory())){
				arg[0] = beanObj.getTerritory();
				arg[1] = beanObj.getBranchCode();
				beanObj.setTerritoryName(this.mytemplate.queryForObject(qry,String.class));
				}
				beanObj.setCountryIncludedList(resMap.get("COUNTRIES_INCLUDE")==null?"":resMap.get("COUNTRIES_INCLUDE").toString());
				if(StringUtils.isNotBlank(beanObj.getCountryIncludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+beanObj.getCountryIncludedList()+") and BRANCH_CODE="+beanObj.getBranchCode();
				arg[0] = beanObj.getCountryIncludedList();
				beanObj.setCountryIncludedName(this.mytemplate.queryForObject(qry,String.class));
				beanObj.setCountryIncludedName(beanObj.getCountryIncludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
				beanObj.setCountryExcludedList(resMap.get("COUNTRIES_EXCLUDE")==null?"":resMap.get("COUNTRIES_EXCLUDE").toString());
				if(StringUtils.isNotBlank(beanObj.getCountryExcludedList())){
				qry ="SELECT   RTRIM(XMLAGG(XMLELEMENT(E,COUNTRY_NAME,',')).EXTRACT('//text()'),',')  FROM   country_master  WHERE  COUNTRY_ID in("+beanObj.getCountryExcludedList()+") and BRANCH_CODE="+beanObj.getBranchCode();
				arg[0] = beanObj.getCountryExcludedList();
				beanObj.setCountryExcludedName(this.mytemplate.queryForObject(qry,String.class));
				beanObj.setCountryExcludedName(beanObj.getCountryExcludedName().replaceAll("&amp;", "&").replaceAll("&apos;", "'"));
				}
				
				/*beanObj.setBusinessType(resMap.get("RSK_BUSINESS_TYPE")==null?"":resMap.get("RSK_BUSINESS_TYPE").toString());
				if("6".equalsIgnoreCase(beanObj.getBusinessType())){
					beanObj.setBusinessType("Umbrella XL");
				}
				else if("7".equalsIgnoreCase(beanObj.getBusinessType())){
					beanObj.setBusinessType("Others");
				}
				else{*/
				beanObj.setBusinessType(resMap.get("RSK_BUSINESS_TYPE_Con")==null?"":resMap.get("RSK_BUSINESS_TYPE_Con").toString());
				//}
				beanObj.setInwardType(resMap.get("INWARD_BUS_TYPE")==null?"":resMap.get("INWARD_BUS_TYPE").toString());
				beanObj.setExchangeType(resMap.get("RSK_EXCHANGE_TYPE")==null?"":resMap.get("RSK_EXCHANGE_TYPE").toString());
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED")==null?"":resMap.get("RSK_PERILS_COVERED").toString());
				if("0".equalsIgnoreCase(beanObj.getPerilCovered())){
					beanObj.setPerilCovered("None");
				}else{
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED_Con")==null?"":resMap.get("RSK_PERILS_COVERED_Con").toString());
				}
				beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED")==null?"":resMap.get("RSK_LOC_ISSUED").toString());
				beanObj.setUmbrellaXL(resMap.get("RSK_UMBRELLA_XL")==null?"":resMap.get("RSK_UMBRELLA_XL").toString());
				beanObj.setLocBankName(resMap.get("RSK_LOC_BNK_NAME")==null?"":resMap.get("RSK_LOC_BNK_NAME").toString());
				beanObj.setLocCreditPrd(resMap.get("RSK_LOC_CRDT_PRD")==null?"":resMap.get("RSK_LOC_CRDT_PRD").toString());
				beanObj.setLocCreditAmt(resMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(resMap.get("RSK_LOC_CRDT_AMT").toString()));
				beanObj.setLocBeneficerName(resMap.get("RSK_LOC_BENFCRE_NAME")==null?"":resMap.get("RSK_LOC_BENFCRE_NAME").toString());
				
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
				beanObj.setLimitOrigCur(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OC")==null?"":secViewDataMap.get("RSK_LIMIT_OC").toString()));
				beanObj.setLimitOrig_CurDc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_DC")==null?"":secViewDataMap.get("RSK_LIMIT_DC").toString()));
				beanObj.setEpi_origCur(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OFFER_OC")==null?"":secViewDataMap.get("RSK_EPI_OFFER_OC").toString()));
				beanObj.setEpi_origCur_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OFFER_DC")==null?"":secViewDataMap.get("RSK_EPI_OFFER_DC").toString()));
				beanObj.setOurEstimate(secViewDataMap.get("RSK_EPI_ESTIMATE")==null?"":secViewDataMap.get("RSK_EPI_ESTIMATE").toString());
				beanObj.setXlPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_XLPREM_OC")==null?"":secViewDataMap.get("RSK_XLPREM_OC").toString()));
				beanObj.setXlPremium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_XLPREM_DC")==null?"":secViewDataMap.get("RSK_XLPREM_DC").toString()));
				beanObj.setDeduc_hunPercent(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUC_OC")==null?"":secViewDataMap.get("RSK_DEDUC_OC").toString()));
				beanObj.setEpiEstmate(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_EST_OC")==null?"":secViewDataMap.get("RSK_EPI_EST_OC").toString()));
				beanObj.setEpiEstmate_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_EST_DC")==null?"":secViewDataMap.get("RSK_EPI_EST_DC").toString()));
				beanObj.setXlCost(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_OC")==null?"":secViewDataMap.get("RSK_XLCOST_OC").toString()));
				beanObj.setXlCost_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_XLCOST_DC")==null?"":secViewDataMap.get("RSK_XLCOST_DC").toString()));
				beanObj.setCedReten(DropDownControllor.formatter(secViewDataMap.get("RSK_CEDANT_RETENTION")==null?"":secViewDataMap.get("RSK_CEDANT_RETENTION").toString()));
				beanObj.setShareWritt(DropDownControllor.formatterpercentage(secViewDataMap.get("RSK_SHARE_WRITTEN")==null?"":secViewDataMap.get("RSK_SHARE_WRITTEN").toString()));
				beanObj.setSharSign(DropDownControllor.formatterpercentage(secViewDataMap.get("RSK_SHARE_SIGNED")==null?"":secViewDataMap.get("RSK_SHARE_SIGNED").toString()));
				beanObj.setM_dPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_OC")==null?"":secViewDataMap.get("RSK_MD_PREM_OC").toString()));
				beanObj.setMd_premium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_MD_PREM_DC")==null?"":secViewDataMap.get("RSK_MD_PREM_DC").toString()));
				beanObj.setAdjRate(DropDownControllor.formattereight(secViewDataMap.get("RSK_ADJRATE")==null?"":secViewDataMap.get("RSK_ADJRATE").toString()));
				beanObj.setPortfoloCovered(secViewDataMap.get("RSK_PF_COVERED")==null?"":secViewDataMap.get("RSK_PF_COVERED").toString());
				beanObj.setSubPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_SUBJ_PREMIUM_OC")==null?"":secViewDataMap.get("RSK_SUBJ_PREMIUM_OC").toString()));
				beanObj.setSubPremiumOSOC(getShareVal(beanObj.getSubPremium().replaceAll(",", ""),beanObj.getSharSign(),"share"));
				beanObj.setDeduc_hunPercentOSOC(getShareVal(beanObj.getDeduc_hunPercent().replaceAll(",", ""),beanObj.getSharSign(),"share"));
				beanObj.setDeduc_hunPercentOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getDeduc_hunPercentOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
				beanObj.setSubPremiumOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getSubPremiumOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
				beanObj.setSubPremium_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_SUBJ_PREMIUM_DC")==null?"":secViewDataMap.get("RSK_SUBJ_PREMIUM_DC").toString()));
				beanObj.setMaxLimit_Product(DropDownControllor.formatter(secViewDataMap.get("RSK_MAX_LMT_COVER")==null?"":secViewDataMap.get("RSK_MAX_LMT_COVER").toString()));
				beanObj.setDeduc_hunPercent_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUC_DC")==null?"":secViewDataMap.get("RSK_DEDUC_DC").toString()));
				if("3".equalsIgnoreCase(beanObj.getProduct_id())){
					beanObj.setLimitOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OS_OC")==null?"":secViewDataMap.get("RSK_LIMIT_OS_OC").toString()));
					beanObj.setLimitOurShare_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OS_DC")==null?"":secViewDataMap.get("RSK_LIMIT_OS_DC").toString()));
				}else{
					beanObj.setLimitOurShare(getShareVal(beanObj.getLimitOrigCur().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitOurShare_Dc(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitOurShare().replaceAll(",",""),beanObj.getExchRate()).toString()));
				}
				if("3".equalsIgnoreCase(beanObj.getProduct_id())){
					beanObj.setEpiAsPerOffer(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOF_OC")==null?"":secViewDataMap.get("RSK_EPI_OSOF_OC").toString()));
					beanObj.setEpiAsPerOffer_Dc(DropDownControllor.formatter(secViewDataMap.get("RSK_EPI_OSOF_DC")==null?"":secViewDataMap.get("RSK_EPI_OSOF_DC").toString()));
				}else{
					beanObj.setEpiAsPerOffer(getShareVal(beanObj.getEpiEstmate().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setEpiAsPerOffer_Dc(DropDownControllor.formatter(getDesginationCountry(beanObj.getEpiAsPerOffer().replaceAll(",",""),beanObj.getExchRate()).toString()));
				}
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
				beanObj.setOurAssessment(secViewDataMap.get("OURASSESSMENT")==null?"0":secViewDataMap.get("OURASSESSMENT").toString());
				beanObj.setEgnpiOffer(DropDownControllor.formatter(secViewDataMap.get("EGPNI_AS_OFFER")==null?"":secViewDataMap.get("EGPNI_AS_OFFER").toString()));
				beanObj.setEgnpiOfferOSOC(getShareVal(beanObj.getEgnpiOffer().replaceAll(",", ""),beanObj.getSharSign(),"share"));
				beanObj.setEgnpiOfferOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getEgnpiOfferOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
				beanObj.setEgnpiOfferDC(DropDownControllor.formatter(secViewDataMap.get("EGPNI_AS_OFFER_DC")==null?"":secViewDataMap.get("EGPNI_AS_OFFER_DC").toString()));
				beanObj.setPml(secViewDataMap.get("RSK_PML")==null ? "" : secViewDataMap.get("RSK_PML").toString());
				beanObj.setPmlPercent(secViewDataMap.get("RSK_PML_PERCENT")==null ? "" : secViewDataMap.get("RSK_PML_PERCENT").toString());
				beanObj.setPremiumbasis(secViewDataMap.get("RSK_PREMIUM_BASIS_Con")==null ? "" : secViewDataMap.get("RSK_PREMIUM_BASIS_Con").toString());
				beanObj.setMinimumRate(secViewDataMap.get("RSK_MINIMUM_RATE")==null ? "" :DropDownControllor.formatter(secViewDataMap.get("RSK_MINIMUM_RATE").toString()));
				beanObj.setMaximumRate(secViewDataMap.get("RSK_MAXIIMUM_RATE")==null ? "" : DropDownControllor.formatter(secViewDataMap.get("RSK_MAXIIMUM_RATE").toString()));
				beanObj.setBurningCostLF(secViewDataMap.get("RSK_BURNING_COST_LF")==null ? "" : DropDownControllor.formatter(secViewDataMap.get("RSK_BURNING_COST_LF").toString()));
				beanObj.setPaymentDuedays(secViewDataMap.get("RSK_PAYMENT_DUE_DAYS")==null ? "" : secViewDataMap.get("RSK_PAYMENT_DUE_DAYS").toString());
				beanObj.setMinPremium(DropDownControllor.formatter(secViewDataMap.get("RSK_MINIMUM_PREMIUM_OC")==null?"":secViewDataMap.get("RSK_MINIMUM_PREMIUM_OC").toString()));
				beanObj.setMinPremiumDC(DropDownControllor.formatter(secViewDataMap.get("RSK_MINIMUM_PREMIUM_DC")==null?"":secViewDataMap.get("RSK_MINIMUM_PREMIUM_DC").toString()));
				beanObj.setMinPremiumOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_MINIMUM_PREMIUM_OS_OC")==null?"":secViewDataMap.get("RSK_MINIMUM_PREMIUM_OS_OC").toString()));
				beanObj.setMinPremiumOurShareDC(DropDownControllor.formatter(secViewDataMap.get("RSK_MINIMUM_PREMIUM_OS_DC")==null?"":secViewDataMap.get("RSK_MINIMUM_PREMIUM_OS_DC").toString()));
				beanObj.setCoverLimitXL(DropDownControllor.formatter(secViewDataMap.get("RSK_COVER_LIMIT_UXL_OC")==null?"":secViewDataMap.get("RSK_COVER_LIMIT_UXL_OC").toString()));
				beanObj.setCoverLimitXLDC(DropDownControllor.formatter(secViewDataMap.get("RSK_COVER_LIMIT_UXL_DC")==null?"":secViewDataMap.get("RSK_COVER_LIMIT_UXL_DC").toString()));
				beanObj.setCoverLimitXLOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_COVER_LIMIT_UXL_OS_OC")==null?"":secViewDataMap.get("RSK_COVER_LIMIT_UXL_OS_OC").toString()));
				beanObj.setCoverLimitXLOurShareDC(DropDownControllor.formatter(secViewDataMap.get("RSK_COVER_LIMIT_UXL_OS_DC")==null?"":secViewDataMap.get("RSK_COVER_LIMIT_UXL_OS_DC").toString()));
				beanObj.setDeductLimitXL(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUCTABLE_UXL_OC")==null?"":secViewDataMap.get("RSK_DEDUCTABLE_UXL_OC").toString()));
				beanObj.setDeductLimitXLDC(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUCTABLE_UXL_DC")==null?"":secViewDataMap.get("RSK_DEDUCTABLE_UXL_DC").toString()));
				beanObj.setDeductLimitXLOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUCTABLE_UXL_OS_OC")==null?"":secViewDataMap.get("RSK_DEDUCTABLE_UXL_OS_OC").toString()));
				beanObj.setDeductLimitXLOurShareDC(DropDownControllor.formatter(secViewDataMap.get("RSK_DEDUCTABLE_UXL_OS_DC")==null?"":secViewDataMap.get("RSK_DEDUCTABLE_UXL_OS_DC").toString()));
			}

			args = new String[3];
			args[0] = beanObj.getProposal_no();
			args[1] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
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
				List<String> paymentdays = new ArrayList<String>();
				for (number = 0; number < instalmentList.size(); number++) {
					Map<String, Object> insMap = (Map<String, Object>)instalmentList.get(number);
					//beanObj.getRequest().setAttribute("instalmentDate" + number, insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					//beanObj.getRequest().setAttribute("installmentPremium" + number, DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
					dateList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
					premiumList.add(DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
					paymentdays.add((insMap.get("PAYEMENT_DUE_DAY")==null?"":insMap.get("PAYEMENT_DUE_DAY").toString()));
				}
				beanObj.setInstalmentDateList(dateList);
				beanObj.setInstallmentPremium(premiumList);
				beanObj.setPaymentDueDays(paymentdays);
				//beanObj.getRequest().setAttribute("K", number);
			}else{
				List<String> paymentdays = new ArrayList<String>();
				for (int k = 0; k <Integer.parseInt(beanObj.getNo_Insurer()); k++) {
					paymentdays.add(beanObj.getPaymentDuedays());
				}
				beanObj.setPaymentDueDays(paymentdays);
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
					beanObj.setTax(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_TAX")==null?"":thirdViewDataMap.get("RSK_TAX").toString()));
					if (thirdViewDataMap.get("RSK_PROFIT_COMM") != null) {
						if (thirdViewDataMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("1")) {
							beanObj.setProfit_Comm("YES");
						} else {
							beanObj.setProfit_Comm("NO");
						}
					}
					beanObj.setPremiumQuotaShare(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE")==null?"":thirdViewDataMap.get("RSK_PREMIUM_QUOTA_SHARE").toString()));
					beanObj.setPremiumSurplus(DropDownControllor.formatter(thirdViewDataMap.get("RSK_PREMIUM_SURPULS")==null?"":thirdViewDataMap.get("RSK_PREMIUM_SURPULS").toString()));
					beanObj.setAcquisition_Cost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_ACQUISTION_COST_OC")==null?"":thirdViewDataMap.get("RSK_ACQUISTION_COST_OC").toString()));
					beanObj.setAcquisition_CostOSOC(getShareVal(beanObj.getAcquisition_Cost().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setAcquisition_CostOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getAcquisition_CostOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setAcquisition_Cost_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_ACQUISTION_COST_DC")==null?"":thirdViewDataMap.get("RSK_ACQUISTION_COST_DC").toString()));
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
					beanObj.setAnualAggregateLiabilityOSOC(getShareVal(beanObj.getAnualAggregateLiability().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setAnualAggregateLiabilityOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getAnualAggregateLiabilityOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setAnualAggregateLiability_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC").toString()));
					beanObj.setAnualAggregateDeduct(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_DEDUCT_OC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_DEDUCT_OC").toString()));
					beanObj.setAnualAggregateDeductOSOC(getShareVal(beanObj.getAnualAggregateDeduct().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setAnualAggregateDeductOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getAnualAggregateDeductOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setAnualAggregateDeduct_DC(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_DEDUCT_DC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_DEDUCT_DC").toString()));
					beanObj.setOcc_limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_OC")==null?"":thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_OC").toString()));
					beanObj.setOcc_limitOSOC(getShareVal(beanObj.getOcc_limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setOcc_limitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getOcc_limitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setOcc_limitDc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_DC")==null?"":thirdViewDataMap.get("RSK_OCCURRENT_LIMIT_DC").toString()));
					beanObj.setReinstNo(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_NO")==null?"":thirdViewDataMap.get("RSK_REINSTATE_NO").toString()));
					beanObj.setReinstAditionalPremium_percent_Oc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString()));
					beanObj.setReinstAditionalPremium_percent_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC").toString()));
					beanObj.setLeader_Underwriter(thirdViewDataMap.get("RSK_LEAD_UWID")==null?"":thirdViewDataMap.get("RSK_LEAD_UWID").toString());
					if(!"64".equalsIgnoreCase(beanObj.getLeader_Underwriter())){
					beanObj.setLeader_Underwriter(thirdViewDataMap.get("RSK_LEAD_UW")==null?"":thirdViewDataMap.get("RSK_LEAD_UW").toString());
					}else if("64".equalsIgnoreCase(beanObj.getLeader_Underwriter())){
						beanObj.setLeader_Underwriter("ITI Reinsurance Ltd");	
					}
					beanObj.setLeader_Underwriter_country(thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
					beanObj.setLeader_Underwriter_share(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_LEAD_UW_SHARE")==null?"":thirdViewDataMap.get("RSK_LEAD_UW_SHARE").toString()));
					beanObj.setAccounts(thirdViewDataMap.get("RSK_ACCOUNTS")==null?"":thirdViewDataMap.get("RSK_ACCOUNTS").toString());
					beanObj.setExclusion(thirdViewDataMap.get("RSK_EXCLUSION")==null?"":thirdViewDataMap.get("RSK_EXCLUSION").toString());
					beanObj.setRemarks(thirdViewDataMap.get("RSK_REMARKS")==null?"":thirdViewDataMap.get("RSK_REMARKS").toString());
					beanObj.setUnderwriter_Recommendations(thirdViewDataMap.get("RSK_UW_RECOMM")==null?"":thirdViewDataMap.get("RSK_UW_RECOMM").toString());
					beanObj.setGms_Approval(thirdViewDataMap.get("RSK_GM_APPROVAL")==null?"":thirdViewDataMap.get("RSK_GM_APPROVAL").toString());
					beanObj.setDecision(thirdViewDataMap.get("RSK_DECISION")==null?"":thirdViewDataMap.get("RSK_DECISION").toString()); 
					beanObj.setOthercost(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_OTHER_COST")==null?"":thirdViewDataMap.get("RSK_OTHER_COST").toString()));
					beanObj.setReinstAdditionalPremium(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setCommissionQ_SAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_QS_AMT")==null?"":thirdViewDataMap.get("COMM_QS_AMT").toString()));
					beanObj.setCommission_surpAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_SURPLUS_AMT")==null?"":thirdViewDataMap.get("COMM_SURPLUS_AMT").toString()));
					beanObj.setAcqCostPer(DropDownControllor.formatter(thirdViewDataMap.get("OTHER_ACQ_COST_AMT")==null?"":thirdViewDataMap.get("OTHER_ACQ_COST_AMT").toString()));
					beanObj.setProfit_commission(thirdViewDataMap.get("RSK_SHARE_PROFIT_COMMISSION")==null?"":thirdViewDataMap.get("RSK_SHARE_PROFIT_COMMISSION").toString());
					beanObj.setReinstAditionalPremium_percent(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setBrokerage((thirdViewDataMap.get("RSK_BROKERAGE")==null?"":thirdViewDataMap.get("RSK_BROKERAGE").toString()));
					beanObj.setLimitPerVesselOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_OC")==null?"":thirdViewDataMap.get("LIMIT_PER_VESSEL_OC").toString()));
					beanObj.setLimitPerVesselDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_VESSEL_DC").toString()));
					beanObj.setLimitPerVesselOSOC(getShareVal(beanObj.getLimitPerVesselOC().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitPerVesselOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitPerVesselOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setLimitPerLocationOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_OC")==null?"":thirdViewDataMap.get("LIMIT_PER_LOCATION_OC").toString()));
					beanObj.setLimitPerLocationDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_LOCATION_DC").toString()));
					beanObj.setLimitPerLocationOSOC(getShareVal(beanObj.getLimitPerLocationOC().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitPerLocationOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitPerLocationOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setEndorsementDate(thirdViewDataMap.get("ENDT_DATE")==null?"":thirdViewDataMap.get("ENDT_DATE").toString());
					beanObj.setReInstatementPremium(thirdViewDataMap.get("RSK_REINSTATEMENT_PREMIUM")==null?"":thirdViewDataMap.get("RSK_REINSTATEMENT_PREMIUM").toString());
					beanObj.setCrestaStatus(thirdViewDataMap.get("RSK_CREASTA_STATUS")==null?"":thirdViewDataMap.get("RSK_CREASTA_STATUS").toString());
					beanObj.setAcqBonus(thirdViewDataMap.get("RSK_BONUS_ID")==null?"":thirdViewDataMap.get("RSK_BONUS_ID").toString());
					beanObj.setAcqBonusPercentage(thirdViewDataMap.get("RSK_NOCLAIMBONUS_PRCENT")==null?"":DropDownControllor.formattereight(thirdViewDataMap.get("RSK_NOCLAIMBONUS_PRCENT").toString()));
					if("LCB".equalsIgnoreCase(beanObj.getAcqBonus())){
						beanObj.setAcqBonusName("Low Claim Bonus");
					}
					else if("NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
						beanObj.setAcqBonusName("No Claim Bonus");
					}
				}
			}
			String qry =getQuery("GET_POSITION_MASTER_CON_MAP");
			args = new String[2];
			args[0] = beanObj.getProposal_no();
			args[1] = beanObj.getAmendId();
			beanObj.setContractListVal(this.mytemplate.queryForObject(qry, args,String.class));
			
			if(StringUtils.isNotBlank(beanObj.getContractListVal()) && !"None".equalsIgnoreCase(beanObj.getContractListVal())){
			qry = getQuery("GET_MAPPING_PROPOSAL_NO");
			args = new String[4];
			args[0] = beanObj.getContractListVal();
			args[1] = beanObj.getLayerNo();
			args[2] = beanObj.getDepartId();
			args[3] = beanObj.getUwYear();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = this.mytemplate.queryForList(qry, args);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> map =list.get(i);
					beanObj.setMappingProposal(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
					beanObj.setMapingAmendId(map.get("AMEND_ID")==null?"":map.get("AMEND_ID").toString());
				}
			}
			}
			
			
			showRetroContracts(beanObj,pid,true);
			getClassLimitDetails(beanObj);
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
			if(res!=null && res.size()>0) {
				resMap = (Map<String, Object>)res.get(0);
				if(resMap!=null) {
					beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
					//beanObj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
					beanObj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME")==null?"":resMap.get("TMAS_SPFC_NAME").toString()); 
					beanObj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
					beanObj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
					beanObj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
					beanObj.setUnderwriter(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
					beanObj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME")==null?"":resMap.get("TMAS_POL_BRANCH_NAME").toString());
					beanObj.setDepartClass(resMap.get("TMAS_DEPARTMENT_NAME")==null?"":resMap.get("TMAS_DEPARTMENT_NAME").toString());
				}
			}
			/*if(StringUtils.isNotBlank(beanObj.getNo_Insurer()) && Integer.parseInt(beanObj.getNo_Insurer())>0){
				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<Integer.parseInt(beanObj.getNo_Insurer());i++){
					retroFinalList.add(new ArrayList<Map<String,Object>>());
				}
				beanObj.setRetroFinalList(retroFinalList);
			}*/
			if(StringUtils.isNotBlank(beanObj.getM_d_InstalmentNumber()) && Integer.parseInt(beanObj.getM_d_InstalmentNumber())>0){
				List<String> instalList=new ArrayList<String>();
				for(int i=0;i<Integer.parseInt(beanObj.getM_d_InstalmentNumber());i++){
					instalList.add(String.valueOf(i));
				}
				beanObj.setInstalList(instalList);
			}
			GetRemarksDetails(beanObj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return list;
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
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
					logger.info("updateQry=>" + updateQry);
					if (this.mytemplate.update(updateQry, obj) > 0) {
						/*if(StringUtils.isNotBlank(beanObj.getContNo())) {
							beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
						}
						else if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
						}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
							beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
						}*/
					}
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					
					obj = updateHomePositionMasterAruguments(beanObj, pid,"0");
					updateQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
					logger.info("updateQry=>" + updateQry);
					if (this.mytemplate.update(updateQry, obj) > 0) {
						if(StringUtils.isNotBlank(beanObj.getContNo())) {
							beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
						}
						else if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
							beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
						}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
						}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
							beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no() +" and Layer No : "+beanObj.getLayerNo());
						}
					}
				} else {
					obj = getFirstPageSecondTableAruguments(beanObj, pid, amednIdvalue, amendId);
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO35RSKPROPOSAL);
					logger.info("Insert Qry=>" + insertQry);

					int res=this.mytemplate.update(insertQry, obj);
					logger.info("Result=>"+res);
					
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					
					String renewalStatus = getRenewalStatus(beanObj);
					obj = insertHomePositionMasterAruguments(beanObj, pid,amednIdvalue, amendId,renewalStatus);
					insertQry = getQuery(DBConstants.RISK_INSERT_POSITIONMASTER);
					logger.info("Insert Qry=>" + insertQry);
					res=this.mytemplate.update(insertQry, obj);
					logger.info("Result=>"+res);

					if (beanObj.getProStatus().equalsIgnoreCase("A") || beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
						beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
					}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
						beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No : "+beanObj.getLayerNo());
					}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no() +" and Layer No : "+beanObj.getLayerNo());
					}
				}
			} else {
				if (!ChekmodeFlag) {
					if (maxAmendId.equalsIgnoreCase(beanObj.getAmendId())){
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO35RSKPROPOSAL);
						logger.info("Insert Qry=>" + insertQry);
						obj = getFirstPageSecondTableInsertAruguments(beanObj,pid, amednIdvalue, amendId);
					}else{
						insertQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
						logger.info("Update Qry=>" + insertQry);
						obj = getProposalSaveEditModeQuery(beanObj, pid,maxAmendId);
					}
					if (this.mytemplate.update(insertQry, obj) > 0) {
						//InsertFlag = true;
					}
					
					updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
					
					if(maxAmendId.equalsIgnoreCase(beanObj.getAmendId())){
						String renewalStatus = getRenewalStatus(beanObj);
						obj = insertHomePositionMasterAruguments(beanObj, pid,amednIdvalue, amendId,renewalStatus);
						insertQry = getQuery(DBConstants.RISK_INSERT_POSITIONMASTER);
						logger.info("insertQry=>" + insertQry);
					}else{
						insertQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
						logger.info("updateQry=>" + insertQry);
						obj = updateHomePositionMasterAruguments(beanObj, pid,maxAmendId);
					}
					int insertCount = this.mytemplate.update(insertQry, obj);
					logger.info("Result=>" + insertCount);
					if (insertCount > 0){
						//InsertFlag = true;
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
					//this.showSecondpageEditItems(beanObj, pid, proposalno);
				}
			}
			InsertFlag = true;
		} 
		catch (Exception e) {
			InsertFlag = false;
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return InsertFlag;
	}

	public boolean saveSecondMode(RiskDetailsBean beanObj, String productId)  {
		try{
			int ChkSecPagMod = checkSecondPageMode(beanObj, productId);
			int ContractEditMode = contractEditMode(beanObj, productId);
			String updateQry = "",insertQry = "";
			Object[] obj=null,obj1=null;
			//if (ContractEditMode == 1) {
			if (ChkSecPagMod == 2) {
				obj = saveUpdateRiskDetailsSecondForm(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
				logger.info("Query=>" + updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>" + res);
				String GetProposalStatus = null;
				String query=getQuery(DBConstants.RISK_SELECT_MAXRSKSTATUS);
				logger.info("Query=>" + query);
				logger.info("Args[0]=>" + obj[6]);
				GetProposalStatus = this.mytemplate.queryForObject(query,new Object[]{obj[6]},String.class).toString();
				logger.info("Result=>" + GetProposalStatus);
				beanObj.setProStatus(GetProposalStatus);
				if(StringUtils.isNotBlank(beanObj.getContNo())) {
					beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
				}
				else if ("A".equalsIgnoreCase(GetProposalStatus)||"P".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ obj[6]  + " and Layer No : "	+ beanObj.getLayerNo());
				}else if ("N".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ obj[6]  + " and Layer No : "	+ beanObj.getLayerNo());
				}
				if(productId.equalsIgnoreCase("3"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO3SECCOMM);
				else if(productId.equalsIgnoreCase("5")) 
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO5SECCOMM);
				obj1 = savemodeUpdateRiskDetailsSecondFormSecondTable(beanObj, productId);
				logger.info("updateQry " + updateQry);
				res=this.mytemplate.update(updateQry, obj1);
				logger.info("Result=>" + res);
			}else {
				obj = secondPageFirstTableSaveAruguments(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
				logger.info("updateQry" + updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>" + res);
				if(productId.equalsIgnoreCase("3"))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO3SECCOMM);
				else if(productId.equalsIgnoreCase("5"))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO5SECCOMM);
				logger.info("insertQry " + insertQry);
				obj1 = secondPageCommissionSaveAruguments(beanObj,productId);
				res=this.mytemplate.update(insertQry, obj1);
				logger.info("Result=>"+res);
				Object[] args = new Object[3];
				args[0] = beanObj.getProposal_no();
				args[1] = beanObj.getProposal_no();
				args[2] = beanObj.getProposal_no();
				String query=getQuery(DBConstants.RISK_SELECT_CHECHPROPOSALSTATUS);
				logger.info("Query=>"+query);
				logger.info("Args[1]..[3]=>"+beanObj.getProposal_no());
				Map<String, Object> resMap = this.mytemplate.queryForMap(query,args);
				logger.info("Map Size=>"+resMap.size());
				for (int i = 0; i < resMap.size(); i++) {
					beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
					beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString());
					beanObj.setContNo(resMap.get("RSK_CONTRACT_NO")==null?"":resMap.get("RSK_CONTRACT_NO").toString());
				}
				final String HomePosition = getproposalStatus(beanObj.getProposal_no());
				beanObj.setProStatus(HomePosition);
				if (HomePosition.equalsIgnoreCase("P")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no() + " and Layer No : "	+ beanObj.getLayerNo());

				} else if (HomePosition.equalsIgnoreCase("A")) {						
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no() + " and Layer No : "	+ beanObj.getLayerNo());

				} else if (HomePosition.equalsIgnoreCase("R")) {
					beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no() + " and Layer No : "	+ beanObj.getLayerNo());
				}else if (HomePosition.equalsIgnoreCase("N")) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no() + " and Layer No : "	+ beanObj.getLayerNo());
				} else if (HomePosition.equalsIgnoreCase("0")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no() + " and Layer No : " + beanObj.getLayerNo());
				}
			}
			instalMentPremium(beanObj);
			//}
			/*instalMentPremium(beanObj);
			if("3".equalsIgnoreCase(productId)){
				//insertRetroContracts(beanObj,productId);
			}
			if  ("5".equals(productId)){
				insertRetroCess(beanObj);
			}
			reInstatementMainInsert(beanObj);
			insertCrestaMaintable(beanObj);
			insertBonusDetails(beanObj);
			InsertRemarkDetails(beanObj);*/
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
	}

	

	public Object[] secondPageCommissionSaveAruguments(
			final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("3") || productId.equalsIgnoreCase("5")) {
			//,RSK_REMARKS,RSK_UW_RECOMM,RSK_GM_APPROVAL, RSK_DECISION,RSK_ENTRY_DATE,RSK_END_DATE,RSK_STATUS, RSK_OTHER_COST,RSK_REINSTATE_ADDL_PREM_PCT,RSK_BURNING_COST_PCT,RSK_AGGREGATE_deduct_OC,RSK_AGGREGATE_deduct_DC,RSK_REINSTATEMENT_PREMIUM,RSK_CREASTA_STATUS,RSK_LEAD_UNDERWRITER_COUNTRY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?,?,?,?,?)

			obj = new Object[38];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0"
					: beanObj.getBrokerage();
			logger.info("Enter Into beanObj.getExchRate()" + obj[3]);
			obj[4] = StringUtils.isEmpty(beanObj.getTax()) ? "0" : beanObj.getTax();
			obj[5] = StringUtils.isEmpty(beanObj.getShare_Profit_Commission()) ? "0": beanObj.getShare_Profit_Commission();
			obj[6] = "0";
			obj[7] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0": beanObj.getAcquisition_Cost();
			obj[8] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
			obj[9] = StringUtils.isEmpty(beanObj.getAnualAggregateLiability()) ? "0": beanObj.getAnualAggregateLiability();
			obj[10] = StringUtils.isEmpty(beanObj.getAnualAggregateLiability())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAnualAggregateLiability(), beanObj.getExchRate());
			obj[11] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[12] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium()) ? "0": beanObj.getReinstAdditionalPremium();
			obj[13] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getReinstAdditionalPremium(), beanObj.getExchRate());
			obj[14] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0": beanObj.getLeader_Underwriter();
			obj[15] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0": beanObj.getLeader_Underwriter_share();
			obj[16] = StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj.getAccounts();
			obj[17] = StringUtils.isEmpty(beanObj.getExclusion()) ? "": beanObj.getExclusion();
			obj[18] = StringUtils.isEmpty(beanObj.getRemarks()) ? "" : beanObj.getRemarks();
			obj[19] = StringUtils.isEmpty(beanObj .getUnderwriter_Recommendations()) ? "" : beanObj .getUnderwriter_Recommendations();
			obj[20] = StringUtils.isEmpty(beanObj.getGms_Approval()) ? "" : beanObj.getGms_Approval();
			obj[21] = "";
			obj[22] = "";
			//obj[23] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0": beanObj.getProfit_commission();
			obj[23] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0": beanObj.getOthercost();
			obj[24] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" : beanObj.getReinstAditionalPremium_percent();
			obj[25] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0" : beanObj.getBurningCost();
			obj[26] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct()) ? "0" : beanObj.getAnualAggregateDeduct();
			obj[27] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAnualAggregateDeduct(), beanObj.getExchRate());
			obj[28] = StringUtils.isEmpty(beanObj.getOcc_limit()) ? "0" : beanObj.getOcc_limit().replaceAll(",", "");
			obj[29] = StringUtils.isEmpty(beanObj.getOcc_limit())
			|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getOcc_limit().replaceAll(",", ""), beanObj.getExchRate());
			
			obj[30] = beanObj.getReInstatementPremium();
			obj[31] = beanObj.getCrestaStatus();
			obj[32] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "0": beanObj.getLeader_Underwriter_country();
			obj[33] = beanObj.getLoginId();
			obj[34] = beanObj.getBranchCode();
			obj[35] =StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			if(StringUtils.isNotBlank(beanObj.getAcqBonus())&&"NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
				obj[36]=StringUtils.isEmpty(beanObj.getAcqBonusPercentage())? "": beanObj.getAcqBonusPercentage();
				}
				else{
					obj[36]="";
				}
			obj[37]=StringUtils.isEmpty(beanObj.getAcqBonus())? "": beanObj.getAcqBonus();

			
		}/*else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[10];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[4] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" : beanObj.getReinstAditionalPremium_percent();
			obj[5] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0" : beanObj.getBurningCost(); 
			obj[6] = StringUtils.isEmpty(beanObj.getRemarks()) ? "" : beanObj.getRemarks();
			obj[7] = beanObj.getLoginId();
			obj[8] = beanObj.getBranchCode();
			obj[9] = beanObj.getBranchCode();
		}*/
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] secondPageFirstTableSaveAruguments(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		obj = new Object[9];
		obj[0] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[1] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[2] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[3] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[4] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
		obj[5] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
		obj[6] = beanObj.getProposal_no();
		obj[7] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		obj[8] = endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] savemodeUpdateRiskDetailsSecondFormSecondTable(
			final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=new Object[0];
		if (productId.equalsIgnoreCase("3") || productId.equalsIgnoreCase("5")) {
			obj = new Object[35];
			obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0"
					: beanObj.getBrokerage();
			obj[1] = beanObj.getTax();
			obj[2] = beanObj.getShare_Profit_Commission();
			obj[3] = beanObj.getAcquisition_Cost();
			obj[4] = getDesginationCountry(beanObj.getAcquisition_Cost(),
					beanObj.getExchRate());
			obj[5] = beanObj.getAnualAggregateLiability();
			obj[6] = getDesginationCountry(beanObj.getAnualAggregateLiability(),
					beanObj.getExchRate());
			obj[7] =StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[8] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium()) ? "0" :beanObj.getReinstAdditionalPremium();
			obj[9] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getReinstAdditionalPremium(), beanObj.getExchRate());
			obj[10] =StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0" :beanObj.getLeader_Underwriter();
			obj[11] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0" :beanObj.getLeader_Underwriter_share();
			//obj[12] = beanObj.getAccounts();
			obj[12] =StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj
					.getAccounts();
			obj[13] = beanObj.getExclusion();
			obj[14] = beanObj.getRemarks();
			obj[15] = beanObj.getUnderwriter_Recommendations();
			obj[16] = beanObj.getGms_Approval();
			obj[17] = beanObj.getOthercost();
			obj[18] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			obj[19] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0"
					: beanObj.getBurningCost();
			obj[20] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct()) ? "0" : beanObj.getAnualAggregateDeduct();
			obj[21] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct())
			|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getAnualAggregateDeduct(), beanObj.getExchRate());
			obj[22] = StringUtils.isEmpty(beanObj.getOcc_limit()) ? "0" : beanObj.getOcc_limit();
			obj[23] = StringUtils.isEmpty(beanObj.getOcc_limit())
			|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getOcc_limit(), beanObj.getExchRate());
			
			obj[24] = beanObj.getReInstatementPremium();
			obj[25] = beanObj.getCrestaStatus();
			obj[26] =StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "0" :beanObj.getLeader_Underwriter_country();
			obj[27] = beanObj.getLoginId();
			obj[28] = beanObj.getBranchCode();
			obj[29] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			if(StringUtils.isNotBlank(beanObj.getAcqBonus())&&"NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
				obj[30]=StringUtils.isEmpty(beanObj.getAcqBonusPercentage())? "": beanObj.getAcqBonusPercentage();
				}
				else{
					obj[30]="";
				}
			obj[31]=StringUtils.isEmpty(beanObj.getAcqBonus())? "": beanObj.getAcqBonus();
			obj[33] = beanObj.getProposal_no();
			obj[32] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[34] = beanObj.getAmendId();
		}/*else if (productId.equalsIgnoreCase("5")) {
			obj = new Object[10];
			obj[0] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0" : beanObj.getReinstNo();
			obj[1] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" : beanObj.getReinstAditionalPremium_percent();
			obj[2] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0" : beanObj.getBurningCost();
			obj[3] = beanObj.getRemarks();
			obj[4] = beanObj.getLoginId();
			obj[5] = beanObj.getBranchCode();
			obj[6] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			obj[7] = beanObj.getProposal_no();
			obj[8] = beanObj.getLayerNo();
			obj[9]=beanObj.getAmendId();
		}*/
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	private boolean showSecondpageEditItems(RiskDetailsBean beanObj,final String pid, final String proposalNo){
		boolean savFlg = false;
		try{
			String selectQry="";
			//int out=0;
			Object[] args = new Object[3];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = proposalNo;
			if ("3".equalsIgnoreCase(pid)||"5".equalsIgnoreCase(pid)) {
				String sql ="UPDATE ttrn_risk_commission SET RSK_LAYER_NO=? WHERE RSK_PROPOSAL_NUMBER=? AND RSK_ENDORSEMENT_NO=?";
				Object argus[] =new Object[3];
				argus[1] = proposalNo;
				argus[2]=getMaxAmednId(proposalNo);
				argus[0] = StringUtils.isBlank(beanObj.getLayerLayerNo())?beanObj.getLayerNo():beanObj.getLayerLayerNo();
				this.mytemplate.update(sql,argus);
				args = new String[4];
				args[0] = proposalNo;
				args[1] = proposalNo;
				args[2] = proposalNo;
				args[3] = StringUtils.isBlank(beanObj.getLayerLayerNo())?beanObj.getLayerNo():beanObj.getLayerLayerNo();
				selectQry = getQuery(DBConstants.RISK_SELECT_GETEDITMODESECPAGEPRO3DATA);
				logger.info("selectQry " + selectQry);
				logger.info("Args[0]..[2]=>" + proposalNo);
				logger.info("Args[3]=>" +  beanObj.getLayerNo());
				List<Map<String, Object>> resList = this.mytemplate.queryForList(selectQry, args);
				logger.info("List<Map<String, Object>> Size=>" + resList.size());
				Map<String, Object> resMap = null;
				if(resList!=null && resList.size()>0) {
					resMap = (Map<String, Object>)resList.get(0);
					if(resMap!=null && resMap.size()>0) {
						//for (int i = 0; i < resMap.size(); i++) {
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
							if(StringUtils.isBlank(beanObj.getAnualAggregateLiability())  ) {
								if (resMap.get("RSK_AGGREGATE_LIAB_OC") != null) {
									beanObj.setAnualAggregateLiability(resMap.get("RSK_AGGREGATE_LIAB_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_AGGREGATE_LIAB_OC").toString());
								}
							}
							if (resMap.get("RSK_REINSTATE_NO") != null) {
								beanObj.setReinstNo(resMap.get("RSK_REINSTATE_NO").toString().equalsIgnoreCase("0") ? "" : resMap.get("RSK_REINSTATE_NO").toString());
							}
							if (resMap.get("RSK_REINSTATE_ADDL_PREM_OC") != null) {
								beanObj.setReinstAdditionalPremium(resMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString());
							}
							if (resMap.get("RSK_LEAD_UW") != null) {
								beanObj.setLeader_Underwriter(resMap.get("RSK_LEAD_UW")==null ? "0" : resMap.get("RSK_LEAD_UW").toString());
							}
							if (resMap.get("RSK_LEAD_UW_SHARE") != null) {
								beanObj.setLeader_Underwriter_share(resMap.get("RSK_LEAD_UW_SHARE")==null ? "0" : resMap.get("RSK_LEAD_UW_SHARE").toString());
							}
							if (resMap.get("RSK_ACCOUNTS") != null) {
								beanObj.setAccounts(resMap.get("RSK_ACCOUNTS").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACCOUNTS").toString());
							}
							if (resMap.get("RSK_EXCLUSION") != null) {
								beanObj.setExclusion(resMap.get("RSK_EXCLUSION").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EXCLUSION").toString());
							}
							beanObj.setReInstatementPremium(resMap.get("RSK_REINSTATEMENT_PREMIUM")==null?"":resMap.get("RSK_REINSTATEMENT_PREMIUM").toString());
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
						//}
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
						if (resMap.get("RSK_AGGREGATE_DEDUCT_OC") != null) {
							beanObj.setAnualAggregateDeduct(resMap.get("RSK_AGGREGATE_DEDUCT_OC").toString()
									.equalsIgnoreCase("0") ? "0" : resMap.get("RSK_AGGREGATE_DEDUCT_OC").toString());
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
						beanObj.setLeader_Underwriter_country(resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
						beanObj.setCrestaStatus(resMap.get("RSK_CREASTA_STATUS")==null?"":resMap.get("RSK_CREASTA_STATUS").toString());
						if (resMap.get("RSK_LEAD_UW_SHARE") != null) {
							beanObj.setLeader_Underwriter_share(resMap.get("RSK_LEAD_UW_SHARE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LEAD_UW_SHARE").toString());
						}
						if (resMap.get("RSK_ACCOUNTS") != null) {
							beanObj.setAccounts(resMap.get("RSK_ACCOUNTS").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACCOUNTS").toString());
						}
						if (resMap.get("RSK_EXCLUSION") != null) {
							beanObj.setExclusion(resMap.get("RSK_EXCLUSION").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EXCLUSION").toString());
						}
						beanObj.setReInstatementPremium(resMap.get("RSK_REINSTATEMENT_PREMIUM")==null?"":resMap.get("RSK_REINSTATEMENT_PREMIUM").toString());
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
						if (resMap.get("RSK_BONUS_ID") != null) {
							beanObj.setAcqBonus(resMap.get("RSK_BONUS_ID").toString().equalsIgnoreCase("") ? "" : resMap.get("RSK_BONUS_ID").toString());
						}
						if (resMap.get("RSK_NOCLAIMBONUS_PRCENT") != null) {
							beanObj.setAcqBonusPercentage(resMap.get("RSK_NOCLAIMBONUS_PRCENT").toString().equalsIgnoreCase("") ? "0" : DropDownControllor.formattereight(resMap.get("RSK_NOCLAIMBONUS_PRCENT").toString()));
						}
						if (resMap.get("RSK_OCCURRENT_LIMIT_OC") != null) {
							beanObj.setOcc_limit(resMap.get("RSK_OCCURRENT_LIMIT_OC").toString().equalsIgnoreCase("") ? "" : resMap.get("RSK_OCCURRENT_LIMIT_OC").toString());
						}
						
						savFlg = true;
					}
				}
				String query=getQuery("GET_INSTALMENT_COUNT");
				int count=this.mytemplate.queryForInt(query,new Object[]{proposalNo,beanObj.getLayerLayerNo()});
				logger.info("selectQry " + query);
				logger.info("result " + count);
				
				if(beanObj.getM_d_InstalmentNumber().equalsIgnoreCase(Integer.toString(count))){
					args = new String[4];
					args[0] = proposalNo;
					args[1] = beanObj.getLayerLayerNo();
					args[2] = proposalNo;
					args[3] = beanObj.getLayerLayerNo();
				}else{
				args = new String[4];
				args[0] = beanObj.getProposal_no();
				args[1] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				args[2] = beanObj.getProposal_no();
				args[3] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				}
				selectQry = getQuery(DBConstants.RISK_SELECT_GETINSTALMENTDATA);
				logger.info("selectQry " + selectQry);
				logger.info("Args " + StringUtils.join(args,","));
				List<Map<String, Object>> instalmentList = this.mytemplate.queryForList(selectQry,args);
				logger.info("Result Size=>" +instalmentList.size());
				if (instalmentList != null && instalmentList.size()>0) {
					List<String> finalList = new ArrayList<String>();
					List<String> paymentdays = new ArrayList<String>();
					for (int k = 0; k < instalmentList.size(); k++) {
						Map<String, Object> insMap = (Map<String, Object>)instalmentList.get(k);
						//beanObj.getRequest().setAttribute("instalmentDate" + k,insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
						//beanObj.setInstalmentDate(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
						finalList.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
						paymentdays.add((insMap.get("PAYEMENT_DUE_DAY")==null?"":insMap.get("PAYEMENT_DUE_DAY").toString()));
					}
					beanObj.setInstalmentDateList(finalList);
					beanObj.setPaymentDueDays(paymentdays);
					beanObj.setInstalList(finalList);
				}else{
					List<String> paymentdays = new ArrayList<String>();
					if(StringUtils.isNotBlank(beanObj.getM_d_InstalmentNumber())) {
					for (int k = 0; k <Integer.parseInt(beanObj.getM_d_InstalmentNumber()); k++) {
						paymentdays.add(beanObj.getPaymentDuedays());
					}
					}
					beanObj.setPaymentDueDays(paymentdays);
				}
			}
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}

	public Object[] saveUpdateRiskDetailsSecondForm(
			final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		obj = new Object[9];
		obj[0] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[1] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[2] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[3] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[4] = StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0": beanObj.getMd_premium_our_service();
		obj[5] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
		obj[6] = beanObj.getProposal_no();
		obj[7] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		obj[8] = endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public boolean getLayerDuplicationCheck(RiskDetailsBean  formObj) {
		boolean result=false;
		String query="";
		List<Map<String, Object>> list=null;
		try{
			if (StringUtils.isNotBlank(formObj.getProposal_no()) && StringUtils.isNotBlank(formObj.getLayerNo()) /* && "Yes".equalsIgnoreCase(formObj.getLayerMode()) */){
				query= getQuery(DBConstants.RISK_SELECT_LAYERDUPCHECKBYPRONO);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getSectionNo());
				logger.info("Args[1]=>"+formObj.getProposal_no());
				list=this.mytemplate.queryForList(query,new Object[]{formObj.getLayerNo(),formObj.getProposal_no(),StringUtils.isBlank(formObj.getBaseLayer())?formObj.getProposalNo():formObj.getBaseLayer()});
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
			}else if (StringUtils.isNotBlank(formObj.getBaseLayer()) && StringUtils.isNotBlank(formObj.getLayerNo()) /* && "Yes".equalsIgnoreCase(formObj.getLayerMode()) */){
				query= getQuery(DBConstants.RISK_SELECT_LAYERDUPCHECKBYBASELAYER);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getLayerNo());
				logger.info("Args[1]=>"+formObj.getBaseLayer());
				list=this.mytemplate.queryForList(query,new Object[]{formObj.getLayerNo(),formObj.getBaseLayer()});
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
			}else if (StringUtils.isNotBlank(formObj.getProposalNo()) && StringUtils.isNotBlank(formObj.getLayerNo())){
				query= getQuery(DBConstants.RISK_SELECT_LAYERDUPCHECKBYBASELAYER);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+formObj.getLayerNo());
				logger.info("Args[1]=>"+formObj.getProposalNo());
				list=this.mytemplate.queryForList(query,new Object[]{formObj.getLayerNo(),formObj.getProposalNo()});
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
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveRiskDeatilsSecondForm(RiskDetailsBean beanObj,final String productId) {
		try {
			String updateQry = "",insertQry = "",selectQry="",endom="";
			Object[] obj=null,obj1=null;
			Object[] args=null;
			int out=0;
			int chkSecPageMode = checkSecondPageMode(beanObj, productId);
			int ContractEditMode = contractEditMode(beanObj, productId);
			//if(!"Renewal".equalsIgnoreCase(beanObj.getProposalReference())&& !"Layer".equalsIgnoreCase(beanObj.getProposalReference())){
			if (ContractEditMode == 1) {
				if (chkSecPageMode == 1) {
					obj = secondPageFirstTableAruguments(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
					logger.info("updateQry=>"+ updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" + out);
					if(productId.equalsIgnoreCase("3"))
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO3SECCOMM);
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
						if (beanObj.getProStatus().matches("A")	&& !beanObj.getSharSign().matches("0")) {
							String maxContarctNo = null;
							logger.info("Contract No=>"+ beanObj.getContractno() + "Layer No=>"+ beanObj.getLay());
							String prodid=productId;
							if (beanObj.getLay().equalsIgnoreCase("layer")) {
								logger.info("Mode Layer");
								maxContarctNo = beanObj.getContractno();
							} else {
								logger.info("Mode Not a Layer");
								if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
									maxContarctNo=beanObj.getRenewal_contract_no();
								}else{
									//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
										maxContarctNo=new DropDownControllor().getSequence("Contract","3".equalsIgnoreCase(prodid)?prodid:"6",beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
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
							if("3".equalsIgnoreCase(beanObj.getProduct_id()) || "5".equalsIgnoreCase(beanObj.getProduct_id())){
							updateQry = getQuery("CLASS_LIMIT_UPDATE_CONTNO");
							logger.info("updateQry " + updateQry);
							logger.info("Args[0]=>" + maxContarctNo);
							logger.info("Args[1]=>" +  beanObj.getProposal_no());
							out=this.mytemplate.update(updateQry,args);
							logger.info("Result=>" +  out);
							}
							if("5".equalsIgnoreCase(beanObj.getProduct_id())){
								String retroUpdate = getQuery("GET_RETRO_CON_UPDATE");
								args = new String[3];
								args[0] = maxContarctNo;
								args[1] = beanObj.getProposal_no();
								args[2] = beanObj.getLayerNo();
								this.mytemplate.update(retroUpdate,args);
								}
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
							beanObj.setContarctno(maxContarctNo);
							logger.info("Args[3]=>" +  beanObj.getProposal_no());
							out=this.mytemplate.update(updateQry,args);
							logger.info("Result=>" +  out);
							logger.info(beanObj.getRenewal_contract_no() + beanObj.getRenewalFlag());
							if(StringUtils.isBlank(beanObj.getRenewal_contract_no())||"0".equals(beanObj.getRenewal_contract_no())||"NEWCONTNO".equals(beanObj.getRenewalFlag())){
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
									beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No. :"+beanObj.getLayerNo());
								}	if (((String) args[1]).equalsIgnoreCase("N")) {
									beanObj.setContractGendration("Your Proposal is saved in  Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No. :"+beanObj.getLayerNo());

								}  else if (((String) args[1]).equalsIgnoreCase("A")) {


									beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+" and Layer No : "+beanObj.getLayerNo()+".");

								} else if (((String) args[1]).equalsIgnoreCase("R")) {
									beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
								}
							}
							args[3] = beanObj.getProposal_no();
							updateQry=getQuery(DBConstants.RISK_UPDATE_HOMECONTNO);
							logger.info("updateQry " + updateQry);
							int k=0;
							for(Object str:args)
								logger.info("Args["+k+++"]" +str);	
							out=this.mytemplate.update(getQuery(DBConstants.RISK_UPDATE_HOMECONTNO),args);
							logger.info("Result=>" +  out);
						}
					}
				}
			
				else if (chkSecPageMode == 2 ) {
					obj =updateRiskDetailsSecondForm(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35RSKPROPOSAL);
					logger.info("Update Query=>" + updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" +  out);
					obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId);
					if(productId.equalsIgnoreCase("3"))
						updateQry = getQuery(DBConstants.RISK_UPDATE_PRO3SECCOMM);
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

						String maxContarctNo=""; 
						if (beanObj.getLay().equalsIgnoreCase("layer")) {
							logger.info("Mode Layer");
							maxContarctNo = beanObj.getContractno();
						} else {
						if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
							maxContarctNo=beanObj.getRenewal_contract_no(); 
						}else{
							//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
								maxContarctNo=new DropDownControllor().getSequence("Contract","3".equalsIgnoreCase(prodid)?prodid:"6",beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
							/*}else
							maxContarctNo=new DropDownControllor().getPolicyNo("2",prodid,beanObj.getBranchCode());*/
						}
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
						if("3".equalsIgnoreCase(beanObj.getProduct_id()) || "5".equalsIgnoreCase(beanObj.getProduct_id())){
						updateQry = getQuery("CLASS_LIMIT_UPDATE_CONTNO");
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + maxContarctNo);
						logger.info("Args[1]=>" +  beanObj.getProposal_no());
						out=this.mytemplate.update(updateQry,arg);
						logger.info("Result=>" +  out);
						}
						if("5".equalsIgnoreCase(beanObj.getProduct_id())){
						String retroUpdate = getQuery("GET_RETRO_CON_UPDATE");
						args = new String[3];
						args[0] = maxContarctNo;
						args[1] = beanObj.getProposal_no();
						args[2] = beanObj.getLayerNo();
						this.mytemplate.update(retroUpdate,args);
						}
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

							beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+maxContarctNo+" and Layer No.: "+beanObj.getLayerNo()+".");
						}else{

							beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+",New Contract No : "+maxContarctNo+" and Layer No.: "+beanObj.getLayerNo()+".");

						}
						updateQry =getQuery(DBConstants.RISK_UPDATE_MNDINSTALLMENTS);
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + maxContarctNo);
						logger.info("Args[1]=>" + beanObj.getProposal_no());
						out=this.mytemplate.update(updateQry,new Object[]{maxContarctNo,beanObj.getProposal_no()});
						logger.info("Result=>" +out);
						beanObj.setContNo(maxContarctNo);
						beanObj.setContarctno(maxContarctNo);
					} else if (beanObj.getProStatus().matches("P")) {

						beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");

					}else if (beanObj.getProStatus().matches("N")) {

						beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
					}
					else if (beanObj.getProStatus().matches("R")) {
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
					}
				}
			}
			else if (ContractEditMode == 2 ) {
				endom=getQuery(DBConstants.RISK_SELECT_ENDO);
				logger.info("Query=>"+endom);
				logger.info("Args[0]=>"+beanObj.getProposal_no());
				String endtNo=(String)this.mytemplate.queryForObject(endom, new Object[]{beanObj.getProposal_no()}, String.class);
				logger.info("Result=>"+endtNo);
				obj = updateContractRiskDetailsSecondForm(beanObj, productId,endtNo);
				logger.info("Update Select Query========>"+endom);
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35CONTSECPAGE);
				logger.info("updateQry " + updateQry);
				out=this.mytemplate.update(updateQry, obj);
				logger.info("Result=>" +out);
				beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+" and Layer No : "+beanObj.getLayerNo()+".");

				/*if(productId.equalsIgnoreCase("3"))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO3SECCOMM);
				else if("5".equalsIgnoreCase(productId))
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO5SECCOMM);
				logger.info("InsertQry=>" + insertQry);
				obj1 = secondContarctPageCommissionAruguments(beanObj,productId);
				logger.info("Result=>" +  out);
				out=this.mytemplate.update(insertQry, obj1);*/

				if(productId.equalsIgnoreCase("3"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO3SECCOMM);
				else if(productId.equalsIgnoreCase("5"))
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO5SECCOMM);
				logger.info("updateQry " + updateQry);
				obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId);
				out=this.mytemplate.update(updateQry, obj1);

				logger.info("Result=>" +out);
				beanObj.setProStatus("A");
			}
			if("5".equalsIgnoreCase(productId)){
				insertRetroCess(beanObj);
			}
			instalMentPremium(beanObj);
			if("3".equalsIgnoreCase(productId)){
				//insertRetroContracts(beanObj,productId);
			}
			reInstatementMainInsert(beanObj);
			insertCrestaMaintable(beanObj);
			insertBonusDetails(beanObj);
			InsertRemarkDetails(beanObj);
			DropDownControllor dropDownController = new DropDownControllor();
			dropDownController.updatepositionMasterEndtStatus(beanObj.getProposal_no(),productId,beanObj.getEndorsementDate(),"");
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
	}

	private void insertCrestaMaintable(RiskDetailsBean bean) {
		Object obj[] =null;
		String query ="";
		try {
			int count=getCrestaCount(bean);
			if(count<=0){
				    query=getQuery("MOVE_TO_CRESTA_MAIN_TABLE");
				    obj = new Object[12];
					obj[0]=bean.getContractno();
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
					obj[11]="";
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
	
	public int getCrestaCount(RiskDetailsBean bean) {
		int count=0;
		try {
			String query=getQuery("GET_CRESTA_DETAIL_COUNT");
			Object[] obj=new Object[3];
			obj[0]=bean.getProposal_no();
			obj[1]=bean.getAmendId();
			obj[2]=bean.getBranchCode();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			count=this.mytemplate.queryForInt(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


	public void insertRetroCess(RiskDetailsBean beanObj) {
		try{
			int NoRetroCess=Integer.parseInt(beanObj.getNoRetroCess()==null?"0":beanObj.getNoRetroCess());
			Object[] obj=null;
			String sql="";
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			//To delete the existing retrocess for a proposal no with amend id.
			this.mytemplate.update("delete from TTRN_RETRO_CESSIONARY where PROPOSAL_NO=? and amend_id=?" , new Object[]{beanObj.getProposal_no(),endtNo});
			for(int i=0;i<NoRetroCess;i++){
				//boolean mode = new DropDownControllor().getMode(beanObj.getProposal_no(),i,endtNo,3);
				sql = getQuery(DBConstants.RISK_INSERT_RETROCESSDTLS);
				obj=getRetroCessArgs(beanObj,i,endtNo,true);
				logger.info("Query=>" + sql);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				logger.info("Result=>" +this.mytemplate.update(sql, obj));
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	public Object[] getRetroCessArgs(RiskDetailsBean beanObj,int i,String endtNo,boolean mode) {
		Object[] obj = null;
		try{
			if(mode){	
				obj = new Object[13];
				obj[0]=i;
				obj[1]=beanObj.getProposal_no();
				obj[2]=StringUtils.isBlank(beanObj.getContNo())?"0":beanObj.getContNo();
				obj[3]=StringUtils.isEmpty(beanObj.getCedingCompany().get(i))?"0":beanObj.getCedingCompany().get(i);
				obj[4]=StringUtils.isEmpty(beanObj.getRetroBroker().get(i))?"0":beanObj.getRetroBroker().get(i);
				obj[5]=StringUtils.isEmpty(beanObj.getShareAccepted().get(i))?"0":beanObj.getShareAccepted().get(i);
				obj[6]=StringUtils.isEmpty(beanObj.getShareSigned().get(i))?"0":beanObj.getShareSigned().get(i);
				obj[7]="";
				//obj[7]=StringUtils.isEmpty(beanObj.getCommission().get(i))?"0":beanObj.getCommission().get(i);
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
				obj[5]="";
				//obj[5]=StringUtils.isEmpty(beanObj.getCommission().get(i))?"0":beanObj.getCommission().get(i);
				obj[6]=StringUtils.isEmpty(beanObj.getProposalStatus().get(i))?"0":beanObj.getProposalStatus().get(i).equalsIgnoreCase("0")?"P":beanObj.getProposalStatus().get(i);
				obj[7]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				obj[8]=i;
				obj[9]=beanObj.getProposal_no();
				obj[10]=endtNo;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return obj;
	}

	public boolean instalMentPremium(final RiskDetailsBean beanObj) {
		try{
			
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());

			String query = "delete from TTRN_MND_INSTALLMENTS where PROPOSAL_NO=? and ENDORSEMENT_NO=?";
			Object[] args = new Object[2];
			args[0] = beanObj.getProposal_no();
			args[1] = endtNo;
			this.mytemplate.update(query,args);
			
			final String InstallmentPeriod = (StringUtils.isBlank(beanObj.getM_d_InstalmentNumber())||"0".equals(beanObj.getM_d_InstalmentNumber()))?"1":beanObj.getM_d_InstalmentNumber();
			int number=Integer.parseInt(InstallmentPeriod);
			String insertQry = getQuery(DBConstants.RISK_INSERT_INSTALPREM);
			
			Object[] obj = new Object[11];
			for (int i = 0; i < number; i++) {
				obj = new Object[11];
				obj[0] = String.valueOf(i + 1);
				obj[1] = beanObj.getProposal_no();
				obj[2] = StringUtils.isEmpty(beanObj.getContNo()) ? "0"	: beanObj.getContNo();
				obj[3] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				obj[4] = endtNo;
				obj[5] = StringUtils.isEmpty(beanObj.getInstalmentDateList().get(i)) ? "" : beanObj.getInstalmentDateList().get(i);
				obj[6] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i)) ? "" : beanObj.getInstallmentPremium().get(i).replaceAll(",", "");
				obj[7] = StringUtils.isEmpty(beanObj.getInstallmentPremium().get(i))|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getInstallmentPremium().get(i).replaceAll(",", ""), beanObj.getExchRate());
				obj[8] =(beanObj.getPaymentDueDays()==null)?"":StringUtils.isEmpty(beanObj.getPaymentDueDays().get(i)) ? "" : beanObj.getPaymentDueDays().get(i);
				obj[9] = beanObj.getLoginId();
				obj[10] = beanObj.getBranchCode();
				logger.info("Query=>"+insertQry);
				logger.info("Args[]"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(insertQry,obj);
				logger.info("Result=>"+res);
			}
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
	}

	public void insertRetroContracts(RiskDetailsBean beanObj,String productId) {
		try{
			final int LoopCount = Integer.parseInt(beanObj.getNo_Insurer());
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			Object[] obj = new Object[12];
			String query=getQuery(DBConstants.FAC_INSERT_INSDETAILS);
			String updQry=getQuery(DBConstants.FAC_UPD_INSDETAILS);
			String qry=getQuery(DBConstants.FAC_UPDATE_INSDETAILS);
			String deleteQuery = getQuery("RETRO_DELETE_QUERY");
			Object args[] = new Object[2];
			args[0] = beanObj.getProposal_no();
			args[1] = endtNo;
			int j=2;
			if(LoopCount==0){
				beanObj.setRetentionPercentage("100");
			}
			this.mytemplate.update(deleteQuery,args);
			for (int i = 0; i < LoopCount; i++) {
				boolean mode = new DropDownControllor().getMode(beanObj.getProposal_no(),j,endtNo,2);
				if(mode){
					if(Integer.parseInt(endtNo)>0){
						logger.info("Query=>"+query);
						logger.info("Arg[0]=>"+beanObj.getProposal_no());
						logger.info("Arg[1]=>"+(i+1));
						int res=this.mytemplate.update(qry,new Object[]{beanObj.getProposal_no(),(i+1)});
						logger.info("Update Result=>"+res);
					}
					obj = new Object[12];
					obj[0] = j;
					obj[1] = beanObj.getProposal_no();
					obj[2] = StringUtils.isEmpty(beanObj.getRetroCeding().get(i)) ? "0" :beanObj.getRetroCeding().get(i);
					obj[3] = endtNo;
					obj[4] = "C";
					obj[5] = StringUtils.isEmpty(beanObj.getPercentRetro().get(i))? "0" :beanObj.getPercentRetro().get(i);
					obj[6] = "Y";
					obj[7] = StringUtils.isEmpty(beanObj.getRetroYear().get(i))? "0" :beanObj.getRetroYear().get(i);
					obj[8] = StringUtils.isEmpty(beanObj.getRetroType())? "" :beanObj.getRetroType();
					obj[9] = beanObj.getDepartmentId();
					obj[10] = beanObj.getLoginId();
					obj[11] = beanObj.getBranchCode();
					logger.info("Insert Query=>"+query);
					logger.info("Args=>" +StringUtils.join(obj,","));
					int res=this.mytemplate.update(query, obj);
					logger.info("Result=>"+res);
					j++;
					
				}else
				{
					obj = new Object[11];
					obj[0] = StringUtils.isEmpty(beanObj.getRetroCeding().get(i)) ? "0" :beanObj.getRetroCeding().get(i);
					obj[1] = "C";
					obj[2] = StringUtils.isEmpty(beanObj.getPercentRetro().get(i))? "0" :beanObj.getPercentRetro().get(i);
					obj[3] = "Y";
					obj[4] = StringUtils.isEmpty(beanObj.getRetroYear().get(i))? "0" :beanObj.getRetroYear().get(i);
					obj[5] = StringUtils.isEmpty(beanObj.getRetroType())? "" :beanObj.getRetroType();
					obj[6] = beanObj.getLoginId();
					obj[7] = beanObj.getBranchCode();
					obj[8] = beanObj.getProposal_no();
					obj[9] = i+1;
					obj[10] = endtNo;
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
				obj = new Object[12];
				obj[0] = 0;
				obj[1] = beanObj.getProposal_no();
				obj[2] = "";
				obj[3] = endtNo;
				obj[4] = "R";
				obj[5] = StringUtils.isEmpty(beanObj.getRetentionPercentage())? "0" :beanObj.getRetentionPercentage();
				obj[6] = "Y";
				obj[7] = "";
				obj[8] = "";
				obj[9] = beanObj.getDepartmentId();
				obj[10] = beanObj.getLoginId();
				obj[11] = beanObj.getBranchCode();
				logger.info("Insert Query==>"+query);
				logger.info("Args=>" +StringUtils.join(obj,","));
				int res=this.mytemplate.update(query, obj);
				logger.info("Result=>"+res);
				obj = new Object[12];
				obj[0] = 1;
				obj[1] = beanObj.getProposal_no();
				obj[2] =  StringUtils.isEmpty(beanObj.getRetroDupContract()) ? "0" :beanObj.getRetroDupContract();
				obj[3] = endtNo;
				obj[4] = "C";
				obj[5] = StringUtils.isEmpty(beanObj.getRetentionPercentage())? "0" :beanObj.getRetentionPercentage();
				obj[6] = "Y";
				obj[7] = StringUtils.isEmpty(beanObj.getRetroDupYerar())? "0" :beanObj.getRetroDupYerar();
				obj[8] = StringUtils.isEmpty(beanObj.getRetroType())? "" :beanObj.getRetroType();
				obj[9] = beanObj.getDepartmentId();
				obj[10] = beanObj.getLoginId();
				obj[11] = beanObj.getBranchCode();
				logger.info("Insert Query==>"+query);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				res=this.mytemplate.update(query, obj);
				logger.info("Result=>"+res);
			}else
			{
				obj = new Object[11];
				obj[0] = "";
				obj[1] = "R";
				obj[2] = StringUtils.isEmpty(beanObj.getRetentionPercentage())? "0" :beanObj.getRetentionPercentage();
				obj[3] = "Y";
				obj[4] = "";
				obj[5] = "";
				obj[6] = beanObj.getLoginId();
				obj[7] = beanObj.getBranchCode();
				obj[8] = beanObj.getProposal_no();
				obj[9] = "0";
				obj[10] = endtNo;
				logger.info("Insert Query==>"+updQry);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(updQry, obj);
				logger.info("Result=>"+res);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}

	public boolean updateProportionalTreatyDao(final RiskDetailsBean beanObj, final String pid)
	{
		boolean savFlg = false;
		try {
			String updateQry = "";
			String sql ="UPDATE ttrn_risk_details SET RSK_LAYER_NO=? WHERE RSK_PROPOSAL_NUMBER=? AND RSK_ENDORSEMENT_NO=?";
			Object argus[] =new Object[3];
			argus[1] = beanObj.getProposal_no();
			argus[2]=getMaxAmednId(beanObj.getProposal_no());
			argus[0] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			this.mytemplate.update(sql,argus);
			Object[] args = getFirstPageSaveModeAruguments(beanObj, pid,getMaxAmednId(beanObj.getProposal_no()));
			updateQry = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
			updateQry = updateQry + " AND RSK_LAYER_NO = ? ";
			logger.info("Query=>"+updateQry);
			int updateCount = this.mytemplate.update(updateQry, args);
			logger.info("Result=>"+updateCount);
			String proposalno="";
			if (StringUtils.isNotEmpty(beanObj.getLayerProposalNo())) {
				proposalno = beanObj.getLayerProposalNo();
			} else {
				proposalno = beanObj.getProposal_no();
			}
			if (updateCount > 0) {
				beanObj.setProduct_id(pid);
				this.insertClassLimit(beanObj);
				InsertRemarkDetails(beanObj);
				GetRemarksDetails(beanObj);
				updateOfferNo(beanObj, pid);
				this.updateRiskProposal(beanObj, pid);
				//this.showSecondpageEditItems(beanObj, pid, proposalno);
				savFlg = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return savFlg;
	}
	private void updateOfferNo(RiskDetailsBean beanObj,String pid) {
		try {
			String offerNo="";
			
			if(StringUtils.isBlank(beanObj.getOfferNo())) {
				offerNo=new DropDownControllor().getSequence("Offer",pid,"0", beanObj.getBranchCode(),"","");
				beanObj.setOfferNo(offerNo);
				String query="UPDATE POSITION_MASTER SET OFFER_NO=? WHERE PROPOSAL_NO=?";
				this.mytemplate.update(query,new Object[] {beanObj.getOfferNo(),beanObj.getProposal_no()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			updateQry = getQuery(DBConstants.RISK_UPDATE_PRO35FIRPAGERSKPRO);
			logger.info("updateQry " + updateQry);
			res=this.mytemplate.update(updateQry, obj);
			logger.info("Result=>" + res);
			if (res> 0) {
				saveFlag = true;
			}
			updateFirstPageFields(beanObj, getMaxAmednIdPro(beanObj.getProposal_no()));
			//if(StringUtils.isBlank(beanObj.getBaseLayer())) {
			obj = updateHomePostion(beanObj, pid,true);
			updateQry = getQuery(DBConstants.RISK_UPDATE_POSITIONMASTER);
			logger.info("updateQry " + updateQry);
			res=this.mytemplate.update(updateQry, obj);
			logger.info("Result=>" + res);
			if (res > 0) {
				saveFlag = true;
			}
			//}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return saveFlag;
	}
	public Object[] updateHomePostion(final RiskDetailsBean beanObj,final String pid, final boolean bool) {

		Object[] obj = new Object[23];
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
		if(beanObj.getContractno()==null || beanObj.getContractno().equalsIgnoreCase(""))
		{
			/*if(beanObj.getProStatus().equalsIgnoreCase("P") || beanObj.getProStatus().equalsIgnoreCase("A"))
			{
				obj[11] ="P"; 
			}*/
			if(beanObj.getProStatus().equalsIgnoreCase("P"))
			{
				obj[11] ="P"; 
			}
			else if(beanObj.getProStatus().equalsIgnoreCase("A"))
			{
				obj[11] ="P";
			}
			else if(beanObj.getProStatus().equalsIgnoreCase("R"))
			{
				obj[11] ="R";
			}else if(beanObj.getProStatus().equalsIgnoreCase("N"))
			{
				obj[11] ="N";
			}
		}
		else
		{
			obj[11] =beanObj.getProStatus().trim();
		}
		obj[12] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		obj[13] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[14] = beanObj.getLoginId();
		obj[15] = "";
		obj[16] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[17] = beanObj.getBouquetModeYN();
		obj[18] = beanObj.getBouquetNo();
		obj[19] = beanObj.getUwYearTo();
		obj[20] = "";
		obj[21] = beanObj.getProposal_no();
		obj[22] = beanObj.getAmendId();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public void showRetroContracts(RiskDetailsBean beanObj,String productId,boolean view)  {
		try{
			Object[] args=null;
			String query="";
			beanObj.setProduct_id(productId);
			int noofInsurar=0;
			//beanObj.getRequest().setAttribute("NoInsurar",beanObj.getNo_Insurer());
			if(StringUtils.isNotBlank(beanObj.getNo_Insurer())){
				 noofInsurar = Integer.parseInt(beanObj.getNo_Insurer());
				 noofInsurar = noofInsurar+1;
			}
			if(view){
				args=new Object[3];
				args[0]=beanObj.getAmendId();
				args[1]=beanObj.getProposal_no();
				args[2]=Integer.toString(noofInsurar);
				query=getQuery(DBConstants.FAC_SELECT_VIEWINSDETAILS);
			}else{
				query=getQuery(DBConstants.FAC_SELECT_INSDETAILS);
				args=new String[2];
				args[0]=beanObj.getProposal_no();
				args[1]=Integer.toString(noofInsurar);
			}
			logger.info("Query=>" + query);
			logger.info("Args[]=>"+StringUtils.join(args,","));
			List<Map<String, Object>> insDetailsList=this.mytemplate.queryForList(query,args);
			logger.info("Result Size=>"+insDetailsList.size());
			List<String> retroList=new ArrayList<String>();
			List<String> retroPercentage=new ArrayList<String>();
			List<String> UWYear=new ArrayList<String>();
			List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
			//List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
			if(insDetailsList!=null&&insDetailsList.size()>0){
				
				List<String> cedingCompany=new ArrayList<String>();
				for(int j=0;j<insDetailsList.size();j++){
					Map<String, Object> insDetailsMap=(Map<String, Object>)insDetailsList.get(j);
					if("R".equalsIgnoreCase((String)insDetailsMap.get("TYPE"))){
						beanObj.setRetentionPercentage(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
					}else{
						//beanObj.getRequest().setAttribute("cedingCompany"+(j-1),insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
						/*beanObj.getRequest().setAttribute("RetroPercentage"+(j-1),insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
		 				beanObj.getRequest().setAttribute("uwYear"+(j-1),insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
						 */
						/*UWYear.add(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
						cedingCompany.add(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
						retroPercentage.add(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());*/
						//beanObj.getRequest().setAttribute("retroType"+(j-1),insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString());
						RiskDetailsBean bean=new RiskDetailsBean();
						logger.info("ProductCode=>"+productId);
						bean.setBranchCode(beanObj.getBranchCode());
						bean.setIncepDate(beanObj.getIncepDate());
						if("2".equals(productId)){
							bean.setProduct_id("4");	
							bean.setRetroType("TR");
						}else if("3".equals(productId)){
							bean.setProduct_id("4");	
							bean.setRetroType("TR");	
						}
						if(j==1){
							if(beanObj.getUwYear().equalsIgnoreCase(insDetailsMap.get("UW_YEAR").toString())){
								beanObj.setRetroDupYerar(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
								}else{
									beanObj.setRetroDupYerar(beanObj.getUwYear());
								}
							beanObj.setRetroDupContract(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
						}
						else if(j>1){
							UWYear.add(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
							cedingCompany.add(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
							retroPercentage.add(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
							retroFinalList.add(getRetroContractDetailsList(bean,2,insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString()));
							retroList.add(String.valueOf(j));
						}
						
						//beanObj.getRequest().setAttribute("UWYearList"+(j-1),getRetroContractDetailsList(bean,1));
						beanObj.setRetroUwyear(getRetroContractDetailsList(bean,1,""));
						bean.setUwYear(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
						//beanObj.getRequest().setAttribute("RetroContracts"+(j-1),getRetroContractDetailsList(bean,2));
						//retroDupList.add(getRetroContractDetailsList(bean,3));
					}
				}
				beanObj.setPercentRetro(retroPercentage);
				beanObj.setRetroCeding(cedingCompany);
				beanObj.setRetroYear(UWYear);
				if(UWYear.size()==0){
					beanObj.setRetroUwyear(getRetroContractDetailsList(beanObj,1,""));
				}
				if(retroFinalList.size()!=Integer.parseInt(beanObj.getNo_Insurer())){
					//if(retroFinalList.size()!=Integer.parseInt(beanObj.getNo_Insurer()) && retroFinalList.size()==0){
					for(int i=0;i<Integer.parseInt(beanObj.getNo_Insurer())-retroList.size();i++){
						retroFinalList.add(new ArrayList<Map<String,Object>>());
					}
				}
				/*if(retroDupList.size()<=0){
					retroDupList.add(new ArrayList<Map<String,Object>>());
				}*/
				beanObj.setRetroFinalList(retroFinalList);
				//beanObj.setRetroDupList(retroDupList);
			}else{
				for(int i=0;i<Integer.parseInt(StringUtils.isBlank(beanObj.getNo_Insurer())?"0":beanObj.getNo_Insurer());i++){
					RiskDetailsBean bean=new RiskDetailsBean();
					logger.info("ProductCode=>"+productId);
					if("2".equals(productId)){
						bean.setProduct_id("4");	
						bean.setRetroType("TR");
						bean.setUwYear(beanObj.getUwYear());
						bean.setIncepDate(beanObj.getIncepDate());
						bean.setBranchCode(beanObj.getBranchCode());
					}else if("3".equals(productId)){
						bean.setProduct_id("4");	
						bean.setRetroType("TR");
						bean.setUwYear(beanObj.getUwYear());
						bean.setIncepDate(beanObj.getIncepDate());
						bean.setBranchCode(beanObj.getBranchCode());
					}
					if(StringUtils.isNotBlank(beanObj.getNo_Insurer())){
						for (int z=0;z<Integer.parseInt(beanObj.getNo_Insurer());z++){
							/*if("1".equalsIgnoreCase(beanObj.getNo_Insurer())){
								retroPercentage.add("100");
								UWYear.add(beanObj.getUwYear());
								retroDupList.add(getRetroContractDetailsList(bean,3));
							}
							else if(0==z){
								UWYear.add(beanObj.getUwYear());
								retroPercentage.add("");
								retroDupList.add(getRetroContractDetailsList(beanObj,3));
							}else{
								retroDupList.add(new ArrayList<Map<String,Object>>());
							}*/
							beanObj.setRetroDupYerar(beanObj.getUwYear());
							List<Map<String,Object>> list = getRetroContractDetailsList(beanObj,3,beanObj.getUwYear());
							for (int j=0;j<list.size();j++){
								Map<String,Object> map = list.get(j);
								beanObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
							}
						}
							
					}
					beanObj.setPercentRetro(retroPercentage);
					beanObj.setRetroYear(UWYear);
					bean.setBranchCode(beanObj.getBranchCode());
					bean.setIncepDate(beanObj.getIncepDate());
					retroFinalList.add(new ArrayList<Map<String,Object>>());
					//retroDupList.add(new ArrayList<Map<String,Object>>());
					beanObj.setRetroUwyear(getRetroContractDetailsList(bean,1,""));
				}
				
				beanObj.setRetroFinalList(retroFinalList);
				//beanObj.setRetroDupList(retroDupList);
			}
			if(beanObj.getNo_Insurer()!=null&&Integer.parseInt(beanObj.getNo_Insurer())==0){
				beanObj.setRetroDupYerar(beanObj.getUwYear());
				List<Map<String,Object>> list = getRetroContractDetailsList(beanObj,3,beanObj.getUwYear());
				for (int j=0;j<list.size();j++){
					Map<String,Object> map = list.get(j);
					beanObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	}
	public Object[] updateRiskProposalArgs(final RiskDetailsBean beanObj,final String pid,String endNo) {
		Object[] obj=null;
		obj = new Object[36];
		obj[0] = beanObj.getLimitOrigCur().replaceAll(",", "");
		obj[1] = getDesginationCountry(beanObj.getLimitOrigCur().replaceAll(",", ""), beanObj.getExchRate());
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
		obj[19] = StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[23] =StringUtils.isEmpty(beanObj.getMd_premium_our_service()) ? "0"	: beanObj.getMd_premium_our_service();
		obj[24] = StringUtils.isEmpty(beanObj.getMd_premium_our_service())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getMd_premium_our_service(), beanObj.getExchRate());
		obj[25] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
		obj[26] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
		obj[27] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
		obj[28] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		obj[29] =StringUtils.isEmpty(beanObj.getEgnpiOffer()) ? "0"	: beanObj.getEgnpiOffer();
		obj[31] = StringUtils.isEmpty(beanObj.getEgnpiOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEgnpiOffer(), beanObj.getExchRate());
		obj[30] =StringUtils.isEmpty(beanObj.getOurAssessment()) ? "0"	: beanObj.getOurAssessment();
		obj[32] = beanObj.getLoginId();
		obj[33] = beanObj.getBranchCode();
		obj[34] = beanObj.getProposal_no();
		obj[35]=endNo;
		
		layerNoUpdate(beanObj,endNo);
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	private void layerNoUpdate(RiskDetailsBean beanObj, String endNo) {
		try{
			String query = getQuery("RISK_LAYER_UPDATE");
			Object args[] =  new Object[3];
			args[0]= StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			args[1] = beanObj.getProposal_no();
			args[2]=endNo;
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			
		}
	}



	public String[] getFirstPageSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		args = new String[55];
		args[0] = beanObj.getDepartId();
		args[1] = beanObj.getProfit_Center();
		args[2] = beanObj.getSubProfit_center();
		args[3] = beanObj.getPolBr();
		args[4] = beanObj.getCedingCo();
		args[5] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		args[6] = beanObj.getTreatyName_type();
		args[7] = beanObj.getMonth();
		args[8] = beanObj.getUwYear();
		args[9] = beanObj.getUnderwriter();
		args[10] = beanObj.getIncepDate();
		args[11] = beanObj.getExpDate();
		args[12] = beanObj.getAccDate();
		args[13] = beanObj.getOrginalCurrency();
		args[14] = beanObj.getExchRate();
		args[15] = beanObj.getBasis();
		args[16] = "";
		args[17] = "";
		args[18] = beanObj.getTerritoryscope();
		args[19] = StringUtils.isBlank(beanObj.getTerritory())?"":beanObj.getTerritory();
		args[20] = beanObj.getProStatus();
		args[21] = "";
		args[22] = "0";
		args[23] = "0";
		args[24] = "0";
		
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0": beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0": beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "": beanObj.getInsuredName();
		args[29] = StringUtils.isEmpty(beanObj.getInwardType()) ? "": beanObj.getInwardType();
		args[30] = StringUtils.isEmpty(beanObj.getTreatyType()) ? "": beanObj.getTreatyType();
		
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		
		args[36]=beanObj.getLoginId();
		args[37]=beanObj.getBranchCode();
		args[38] = StringUtils.isEmpty(beanObj.getCountryIncludedList()) ? ""	:beanObj.getCountryIncludedList();
		args[39] = StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	:beanObj.getCountryExcludedList();
		args[40] = "";
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] ="0";
		args[43] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[44] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[45] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[46] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[47] = "";
		args[48]="";
		args[49]=StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		args[50] = StringUtils.isEmpty(beanObj.getAccountingPeriodNotes())?"":beanObj.getAccountingPeriodNotes();
		args[51] =StringUtils.isEmpty(beanObj.getStatementConfirm())?"":beanObj.getStatementConfirm();
		args[52] = beanObj.getProposal_no();
		//args[30] = beanObj.getProposal_no();
		args[53]=endNo;
		args[54] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	public Object[] secondContarctPageCommissionAruguments(
			final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=new Object[0];
		if (productId.equalsIgnoreCase("3")) {
			obj = new Object[32];
			obj[0] = beanObj.getProposal_no();
			obj[1] = (getMaxAmednId(beanObj.getProposal_no()))+"";
			obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0"
					: beanObj.getBrokerage();
			obj[4] = beanObj.getTax();
			obj[5] = beanObj.getShare_Profit_Commission();
			obj[6] = "0";
			obj[7] = beanObj.getAcquisition_Cost();
			obj[8] = getDesginationCountry(beanObj.getAcquisition_Cost(),
					beanObj.getExchRate());
			obj[9] = beanObj.getAnualAggregateLiability();
			obj[10] = getDesginationCountry(beanObj
					.getAnualAggregateLiability(), beanObj.getExchRate());
			obj[11] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[12] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium()) ? "0" : beanObj.getReinstAdditionalPremium();
			obj[13] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getReinstAdditionalPremium(), beanObj.getExchRate());
			obj[14] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0" : beanObj.getLeader_Underwriter();
			obj[15] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0" : beanObj.getLeader_Underwriter_share();
			obj[16] = beanObj.getAccounts();
			obj[17] = beanObj.getExclusion();
			obj[18] = beanObj.getRemarks();
			obj[19] = beanObj.getUnderwriter_Recommendations();
			obj[20] = beanObj.getGms_Approval();
			obj[21] = "";
			obj[22] = "";
			obj[23] = "0";
			obj[24] = beanObj.getOthercost();
			obj[25] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			//obj[26] = beanObj.getBurningCost();
			obj[26] = "";
			obj[27] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct()) ? "0" : beanObj.getAnualAggregateDeduct();
			obj[28] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct())
			|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"
					: getDesginationCountry(beanObj.getAnualAggregateDeduct(), beanObj.getExchRate());
			obj[29] = beanObj.getReInstatementPremium();
			obj[30] = beanObj.getCrestaStatus();
			obj[31] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "0" : beanObj.getLeader_Underwriter_country();
		}else if("5".equalsIgnoreCase(productId)){
			obj = new Object[7];
			obj[0] = beanObj.getProposal_no();
			obj[1] = (getMaxAmednId(beanObj.getProposal_no()))+"";
			obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[4] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			//obj[5] = beanObj.getBurningCost();
			obj[5] = "";
			obj[6] = beanObj.getRemarks();
		}
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] updateContractRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId,final String endNo) {
		Object[] obj=null;
		obj = new Object[9];
		obj[0] = beanObj.getLimitOurShare();
		obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[2] = beanObj.getEpiAsPerOffer();
		obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[4] = beanObj.getMd_premium_our_service();
		obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),	beanObj.getExchRate());
		obj[6] = endNo;
		obj[7] = beanObj.getProposal_no();
		obj[8] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
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
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	public Object[] updateRiskDetailsSecondFormSecondTable(final RiskDetailsBean beanObj, final String productId) {

		Object[] obj=null;
		if (productId.equalsIgnoreCase("3") || productId.equalsIgnoreCase("5")) {
			obj = new Object[35];
			obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
			obj[1] = beanObj.getTax();
			obj[2] = beanObj.getShare_Profit_Commission();
			obj[3] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0": beanObj.getAcquisition_Cost().replaceAll(",", "");
			obj[4] = getDesginationCountry(beanObj.getAcquisition_Cost().replaceAll(",", ""),beanObj.getExchRate());
			obj[5] = beanObj.getAnualAggregateLiability().replaceAll(",", "");
			obj[6] = getDesginationCountry(beanObj.getAnualAggregateLiability().replaceAll(",", ""),beanObj.getExchRate());
			obj[7] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[8] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium()) ? "0" :beanObj.getReinstAdditionalPremium();
			obj[9] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getReinstAdditionalPremium(), beanObj.getExchRate());
			obj[10] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0" : beanObj.getLeader_Underwriter();
			obj[11] =StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0" : beanObj.getLeader_Underwriter_share();
			//obj[12] = beanObj.getAccounts();
			obj[12] =StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj.getAccounts();
			obj[13] = beanObj.getExclusion();
			obj[14] = beanObj.getRemarks();
			obj[15] = beanObj.getUnderwriter_Recommendations();
			obj[16] = beanObj.getGms_Approval();
			obj[17] = beanObj.getOthercost();
			obj[18] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			obj[19] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0": beanObj.getBurningCost();
			obj[20] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct()) ? "0" : beanObj.getAnualAggregateDeduct();
			obj[21] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAnualAggregateDeduct(), beanObj.getExchRate());
			obj[22] = StringUtils.isEmpty(beanObj.getOcc_limit()) ? "0" : beanObj.getOcc_limit().replaceAll(",", "");
			obj[23] = StringUtils.isEmpty(beanObj.getOcc_limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getOcc_limit().replaceAll(",", ""), beanObj.getExchRate());
			obj[24] = beanObj.getReInstatementPremium();
			obj[25] = beanObj.getCrestaStatus();
			obj[26] =StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "0" : beanObj.getLeader_Underwriter_country();
			obj[27] = beanObj.getLoginId();
			obj[28] = beanObj.getBranchCode();
			obj[29] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			if(StringUtils.isNotBlank(beanObj.getAcqBonus())&&"NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
				obj[30]=StringUtils.isEmpty(beanObj.getAcqBonusPercentage())? "": beanObj.getAcqBonusPercentage();
				}
				else{
					obj[30]="";
				}
			obj[31]=StringUtils.isEmpty(beanObj.getAcqBonus())? "": beanObj.getAcqBonus();
			obj[33] = beanObj.getProposal_no();
			obj[32] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[34] = beanObj.getAmendId();
		}/*else if (productId.equalsIgnoreCase("5")) {
			obj = new Object[10];
			obj[0] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0" : beanObj.getReinstNo();
			obj[1] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0": beanObj.getReinstAditionalPremium_percent();
			obj[2] = StringUtils.isEmpty(beanObj.getBurningCost()) ? "0": beanObj.getBurningCost();
			obj[3] = beanObj.getRemarks();
			obj[4] = beanObj.getLoginId();
			obj[5] = beanObj.getBranchCode();
			obj[6] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			obj[7] = beanObj.getProposal_no();
			obj[8] = beanObj.getLayerNo();
			obj[9]= beanObj.getAmendId();
		}*/
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] updateRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		obj = new Object[9];
		obj[0] = beanObj.getLimitOurShare();
		obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[2] = beanObj.getEpiAsPerOffer();
		obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[4] = beanObj.getMd_premium_our_service();
		obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),beanObj.getExchRate());
		obj[6] = beanObj.getProposal_no();
		obj[7] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		obj[8] = endNo;
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
			e.printStackTrace();
		}
		return result;
	}

	public Object[] secondPageCommissionAruguments(final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("3") || "5".equalsIgnoreCase(productId)) {
			obj = new Object[38];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0"
					: beanObj.getBrokerage();
			obj[4] = beanObj.getTax();
			obj[5] = beanObj.getShare_Profit_Commission();
			obj[6] = "0";
			obj[7] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())?"0":beanObj.getAcquisition_Cost().replaceAll(",", "");
			obj[8] = getDesginationCountry(StringUtils.isEmpty(beanObj.getAcquisition_Cost())?"0":beanObj.getAcquisition_Cost().replaceAll(",", ""),beanObj.getExchRate());
			obj[9] = beanObj.getAnualAggregateLiability().replaceAll(",", "");
			obj[10] = getDesginationCountry(beanObj.getAnualAggregateLiability().replaceAll(",", ""), beanObj.getExchRate());
			obj[11] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[12] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium()) ? "0" :beanObj.getReinstAdditionalPremium().replaceAll(",", "");
			obj[13] = StringUtils.isEmpty(beanObj.getReinstAdditionalPremium())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getReinstAdditionalPremium().replaceAll(",", ""), beanObj.getExchRate());
			obj[14] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "0" : beanObj.getLeader_Underwriter();
			obj[15] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0" : beanObj.getLeader_Underwriter_share();
			obj[16] = StringUtils.isEmpty(beanObj.getAccounts()) ? "" : beanObj.getAccounts();
			obj[17] = beanObj.getExclusion();
			obj[18] = beanObj.getRemarks();
			obj[19] = beanObj.getUnderwriter_Recommendations();
			obj[20] = beanObj.getGms_Approval();
			obj[21] = "";
			obj[22] = "";
			//obj[23] = "0";
			obj[23] = beanObj.getOthercost();
			obj[24] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			obj[25] = "";
			obj[26] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct()) ? "0" : beanObj.getAnualAggregateDeduct();
			obj[27] = StringUtils.isEmpty(beanObj.getAnualAggregateDeduct())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getAnualAggregateDeduct(), beanObj.getExchRate());
			obj[28] = StringUtils.isEmpty(beanObj.getOcc_limit()) ? "0" : beanObj.getOcc_limit().replaceAll(",", "");
			obj[29] = StringUtils.isEmpty(beanObj.getOcc_limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getOcc_limit().replaceAll(",", ""), beanObj.getExchRate());
			obj[30] = beanObj.getReInstatementPremium();
			obj[31] = beanObj.getCrestaStatus();
			obj[32] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country()) ? "0" : beanObj.getLeader_Underwriter_country();
			obj[33] = beanObj.getLoginId();
			obj[34] = beanObj.getBranchCode();
			obj[35] =StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			if(StringUtils.isNotBlank(beanObj.getAcqBonus())&&"NCB".equalsIgnoreCase(beanObj.getAcqBonus())){
				obj[36]=StringUtils.isEmpty(beanObj.getAcqBonusPercentage())? "": beanObj.getAcqBonusPercentage();
				}
				else{
					obj[36]="";
				}
			obj[37]=StringUtils.isEmpty(beanObj.getAcqBonus())? "": beanObj.getAcqBonus();

		}/*else if ("5".equalsIgnoreCase(productId)) {
			obj = new Object[9];
			obj[0] =  beanObj.getProposal_no();
			obj[1] = "0";
			obj[2] = beanObj.getLayerNo();
			obj[3] = StringUtils.isEmpty(beanObj.getReinstNo()) ? "0": beanObj.getReinstNo();
			obj[4] = StringUtils.isEmpty(beanObj.getReinstAditionalPremium_percent()) ? "0" :beanObj.getReinstAditionalPremium_percent();
			obj[5] = "";
			obj[6] = beanObj.getRemarks();
			obj[7] = beanObj.getLoginId();
			obj[8] = beanObj.getBranchCode();
			obj = new Object[7];
			
			obj[0] = beanObj.getLayerNo();
			obj[1] = beanObj.getReinstNo();
			obj[2] = beanObj.getReinstAditionalPremium_percent();
			obj[3] = beanObj.getBurningCost();
			obj[4] = beanObj.getRemarks();
			obj[5] = beanObj.getProposal_no();
			obj[6] = "0";
		}*/
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public int contractEditMode(final RiskDetailsBean beanObj,final String productId) {
		int mode=0;
		final String query = getQuery(DBConstants.RISK_SELECT_GETRSKCONTRACTNO);
		logger.info("Query=>" + query);
		//int res = this.mytemplate.queryForInt(query,new Object[]{beanObj.getProposal_no()});
		String string = (String) this.mytemplate.queryForObject(query,new Object[] {beanObj.getProposal_no()},String.class);
		logger.info("Result Count=>" + string);
		if ("0".equalsIgnoreCase(string)) {
			mode = 1;
		} else {
			mode = 2;
		}
		logger.info("Mode 1 Means Insert");
		logger.info("Mode 2 Means Update");
		return mode;
	}

	public int checkSecondPageMode(final RiskDetailsBean beanObj,final String productId) {
		int mode=0;
		try{
			final String query;
			if("5".equalsIgnoreCase(productId)){
				query = getQuery(DBConstants.RISK_SELECT_GETRETROCESSCOUNT);
			}else{
				query = getQuery(DBConstants.RISK_SELECT_GETRISKCOMMCOUNT);
			}
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
			e.printStackTrace();
		}
		logger.info("Mode Of Insertion=>" + mode);
		logger.info("If 1 means Insert");
		logger.info("if 2  means Update");
		return mode;
	}

	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean beanObj,int flag, String UWYear){
		String query="";
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		try{
			if(flag==1){
				if ("4".equals(beanObj.getProduct_id())){
					query = getQuery(DBConstants.FAC_SELECT_UWYEAR);
				}else{
					query = getQuery(DBConstants.RISK_SELECT_UWYEAR);
				}
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),beanObj.getIncepDate(),beanObj.getBranchCode(),beanObj.getIncepDate()});
			}else if(StringUtils.isNotEmpty(UWYear)&&flag==2){
				if(StringUtils.isNotBlank(beanObj.getRetroType()) && "TR".equals(beanObj.getRetroType()) && "4".equals(beanObj.getProduct_id())){
					query = getQuery("fac.select.retroContDetTR");
					logger.info("Select Query=>"+query);
					logger.info("Product Code=>"+beanObj.getProduct_id());
					logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
					logger.info("UW Year=>"+beanObj.getUwYear());
					logger.info("Inception Date=>"+beanObj.getIncepDate());
					logger.info("Branch Code=>"+beanObj.getBranchCode());
					list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),UWYear,beanObj.getIncepDate(),beanObj.getBranchCode(),UWYear,beanObj.getIncepDate()});
				}else{
				query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),UWYear,beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),UWYear,beanObj.getIncepDate()});
				}
			}
			else if(StringUtils.isNotEmpty(beanObj.getUwYear())&&flag==3){
				query = getQuery("FAC_SELECT_RETRO_DUP_CONTRACT");
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {"4","TR",beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),"TR",beanObj.getUwYear(),beanObj.getIncepDate()});
			}
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;	
	}

	public Object[] secondPageFirstTableAruguments(
			final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		obj = new Object[9];
		obj[0] = beanObj.getLimitOurShare();
		obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[2] = beanObj.getEpiAsPerOffer();
		obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[4] = beanObj.getMd_premium_our_service();
		logger.info("ENter Into FInal Statge"+ beanObj.getMd_premium_our_service());
		obj[5] = getDesginationCountry(beanObj.getMd_premium_our_service(),	beanObj.getExchRate());
		obj[6] = beanObj.getProposal_no();
		obj[7] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		obj[8] = endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}


	public Object[] getFirstPageSecondTableInsertAruguments(final RiskDetailsBean beanObj, final String pid,final Object args2, final boolean amendId) {
		Object[] obj=null;
		obj = new Object[37];
		if (amendId) {
			obj[1] = args2;
		} else {	
			obj[1] = "0";
		}
		obj[0] = beanObj.getProposal_no();
		obj[3] = beanObj.getLimitOrigCur().replaceAll(",", "");
		obj[4] = getDesginationCountry(beanObj.getLimitOrigCur().replaceAll(",", ""), beanObj.getExchRate());
		obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
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
		obj[32] = StringUtils.isEmpty(beanObj.getEgnpiOffer()) ? "": beanObj.getEgnpiOffer();
		obj[34] = StringUtils.isEmpty(beanObj.getEgnpiOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpiOffer(), beanObj.getExchRate());
		obj[33] = StringUtils.isEmpty(beanObj.getOurAssessment()) ? "": beanObj.getOurAssessment();
		obj[35] = beanObj.getLoginId();
		obj[36] = beanObj.getBranchCode();
		
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] insertHomePositionMasterAruguments(final RiskDetailsBean beanObj, final String pid, final Object args2, final boolean amendId,String renewalStatus) {
		String offerNo="",bouquetno="";
		if(StringUtils.isBlank(beanObj.getBouquetNo()) && "Y".equals(beanObj.getBouquetModeYN())) {
			bouquetno=new DropDownControllor().getSequence("Bouquet","9","0", beanObj.getBranchCode(),"","");
			beanObj.setBouquetNo(bouquetno);
		}
		if(StringUtils.isBlank(beanObj.getOfferNo())) {
			offerNo=new DropDownControllor().getSequence("Offer",pid,"0", beanObj.getBranchCode(),"","");
			beanObj.setOfferNo(offerNo);
		}
		Object[] obj = new Object[31];
		if (amendId) {
			obj[1] = beanObj.getContNo();
			obj[2] = args2;
			obj[16] = beanObj.getBaseLayer();
		} else {
			obj[1] = "0";
			obj[2] = "0";
			obj[16] = StringUtils.isBlank(beanObj.getBaseLayer())?beanObj.getLayerProposalNo():beanObj.getBaseLayer();
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
			obj[18] = StringUtils.isEmpty(beanObj.getLayerProposalNo()) ? "1" : "0";
		}
		obj[15] = StringUtils.isNotBlank(beanObj.getBaseLoginID())?beanObj.getBaseLoginID():beanObj.getLoginId();
		obj[17] = StringUtils.isEmpty(beanObj.getLayerProposalNo()) ?StringUtils.isEmpty(beanObj.getRenewal_contract_no()) ? "" : beanObj.getRenewal_contract_no():"";
		obj[19] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		obj[20] = beanObj.getBranchCode();
		obj[21] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[22] = beanObj.getLoginId();
		obj[23] = "N";
		obj[24] = "";
		obj[25] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[26] = beanObj.getBouquetModeYN();
		obj[27] = beanObj.getBouquetNo();
		obj[28] = beanObj.getUwYearTo();
		obj[29] = beanObj.getSectionNo();
		obj[30] = beanObj.getOfferNo();
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

	public Object[] getFirstPageSecondTableAruguments(final RiskDetailsBean beanObj, final String pid, final Object args2, final boolean amendId) {
		Object[] obj=null;
		obj = new Object[37];
		if (amendId) {
			obj[0] = beanObj.getProposal_no();
			obj[1] = args2;
		} else {
			obj[0] = beanObj.getProposal_no();
			obj[1] = "0";
		}
		obj[3] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur().replaceAll(",", "");
		obj[4] = StringUtils.isEmpty(beanObj.getLimitOrigCur())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOrigCur().replaceAll(",", ""), beanObj.getExchRate());
		obj[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
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
		obj[32] = StringUtils.isEmpty(beanObj.getEgnpiOffer()) ? "": beanObj.getEgnpiOffer();
		obj[34] = StringUtils.isEmpty(beanObj.getEgnpiOffer()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpiOffer(), beanObj.getExchRate());
		obj[33] = StringUtils.isEmpty(beanObj.getOurAssessment()) ? "": beanObj.getOurAssessment();
		obj[35] =beanObj.getLoginId();
		obj[36] = beanObj.getBranchCode();
		
		
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] updateHomePositionMasterAruguments(final RiskDetailsBean beanObj, final String pid, final String maxAmdId) {
		Object[] obj = new Object[22];
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
		if(beanObj.getContNo()!=null&&!"0".equals(beanObj.getContNo())&&!"".equals(beanObj.getContNo()))
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
		obj[12] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		obj[13] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[14] = beanObj.getLoginId();
		obj[15] = "";
		obj[16] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[17] = beanObj.getBouquetModeYN();
		obj[18] = beanObj.getBouquetNo();
		obj[19] = beanObj.getUwYearTo();
		obj[20] = beanObj.getProposal_no();
		obj[21] = maxAmdId;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public String getMaxProposanlno(String pid,String branchCode, String deptId) {
		String result="";
		try{
			//String query;
			//if("06".equalsIgnoreCase(branchCode)){
				result=new DropDownControllor().getSequence("Proposal","3".equalsIgnoreCase(pid)?pid:"6","", branchCode,"","");
			/*}else
			result=new DropDownControllor().getPolicyNo("1",pid,branchCode);*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
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
	private String getMaxAmednIdPro(final String proposalNo) {
		String result ="";
		try{
			String query=getQuery("GET_MAX_AMEND_RISK_PROPOSAL");
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+proposalNo);
			result = this.mytemplate.queryForObject(query, new String[]{proposalNo}, String.class).toString();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return result;
	}

	public int getEditMode(String proposalNo){
		int mode=0;
		try {
			//String selectQry = getQuery(DBConstants.RISK_SELECT_GETRSKCONTRACTNO);
			String selectQry = getQuery("RISK_SELECT_GETRSKCONTRACTNO");
			logger.info("select Query==>" + selectQry);
			logger.info("Args[0]=>"+proposalNo);
			String string = (String) this.mytemplate.queryForObject(selectQry,new Object[] {proposalNo},String.class);
			//mode = this.mytemplate.queryForInt(selectQry,new Object[] {proposalNo});
			logger.info("Result=>"+mode);
			if ("0".equalsIgnoreCase(string)) {
				mode = 1;
			} else {
				mode = 2;
			}
		}catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return mode;
	}


	public Object getDesginationCountry(final String limitOrigCur, final String ExchangeRate) {
		String output="0.0";
		try{
			double origCountryVal=0.0;
			if(limitOrigCur!=null){
				if (!("".equalsIgnoreCase(limitOrigCur)) && Double.parseDouble(limitOrigCur) != 0) {
					origCountryVal = Double.parseDouble(limitOrigCur) / Double.parseDouble(ExchangeRate);
					//final DecimalFormat myFormatter = new DecimalFormat("#0.00");
					//final double dround = Math.round(origCountryVal * 100.0) / 100.0;
					output = DropDownControllor.formatter(Double.toString(origCountryVal).toString()).replaceAll(",", "");
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return output;
	}

	public boolean riskDetailsEditMode(final RiskDetailsBean beanObj,final boolean ContractMode){
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
			List<Map<String, Object>> res = this.mytemplate.queryForList(GetRiskDetailsEditQueryProduct3(ContractMode),args);
			logger.info("List<Map<String, Object>> Size=>"+res.size());
			Map<String, Object> resMap = null;
			if(res!=null && res.size()>0)
				resMap = (Map<String, Object>)res.get(0);
			if (resMap!=null) {
				beanObj.setContractListVal(resMap.get("DATA_MAP_CONT_NO")==null?"":resMap.get("DATA_MAP_CONT_NO").toString());
				beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
				//beanObj.setBaseLayer(resMap.get("BASE_LAYER")==null?"":resMap.get("BASE_LAYER").toString());
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
				beanObj.setUwYearTo(resMap.get("UW_YEAR_TO")==null?"":resMap.get("UW_YEAR_TO").toString());
				beanObj.setBouquetModeYN(resMap.get("Bouquet_Mode_YN")==null?"N":resMap.get("Bouquet_Mode_YN").toString());
				beanObj.setBouquetNo(resMap.get("Bouquet_No")==null?"":resMap.get("Bouquet_No").toString());
				beanObj.setOfferNo(resMap.get("OFFER_NO")==null?"":resMap.get("OFFER_NO").toString());
				beanObj.setUnderwriter(resMap.get("RSK_UNDERWRITTER")==null?"":resMap.get("RSK_UNDERWRITTER").toString());
				if(!"Layer".equalsIgnoreCase(beanObj.getProposalReference())){
					if(StringUtils.isBlank(beanObj.getBaseLayer()))
				beanObj.setBaseLayer(resMap.get("BASE_LAYER")==null?"":resMap.get("BASE_LAYER").toString());
				}
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
					beanObj.setLimitOrigCur(resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_LIMIT_OC").toString());
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
					beanObj.setAdjRate(resMap.get("RSK_ADJRATE").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_ADJRATE").toString());
				}
				if (resMap.get("RSK_PF_COVERED") != null) {
					beanObj.setPortfoloCovered(resMap.get("RSK_PF_COVERED").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_PF_COVERED").toString());
				}
				beanObj.setM_d_InstalmentNumber(resMap.get("MND_INSTALLMENTS")==null?"":resMap.get("MND_INSTALLMENTS").toString());
				if("5".equalsIgnoreCase(beanObj.getProduct_id())){
					beanObj.setNoRetroCess(resMap.get("RETRO_CESSIONARIES").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RETRO_CESSIONARIES").toString());
				}
				//if("3".equals(beanObj.getProduct_id())){
					beanObj.setSpRetro(resMap.get("RSK_SP_RETRO")==null ? "0" : resMap.get("RSK_SP_RETRO").toString());
					beanObj.setNo_Insurer(resMap.get("RSK_NO_OF_INSURERS")==null ? "" : resMap.get("RSK_NO_OF_INSURERS").toString());
					beanObj.setLimitPerVesselOC((resMap.get("LIMIT_PER_VESSEL_OC")==null||"0".equals(resMap.get("LIMIT_PER_VESSEL_OC"))?"":resMap.get("LIMIT_PER_VESSEL_OC").toString()));
					beanObj.setLimitPerLocationOC((resMap.get("LIMIT_PER_LOCATION_OC")==null||"0".equals(resMap.get("LIMIT_PER_LOCATION_OC"))?"":resMap.get("LIMIT_PER_LOCATION_OC").toString()));
					beanObj.setEgnpiOffer((resMap.get("EGPNI_AS_OFFER")==null||"0".equals(resMap.get("EGPNI_AS_OFFER"))?"":resMap.get("EGPNI_AS_OFFER").toString()));
					beanObj.setEgnpiOfferDC((resMap.get("EGPNI_AS_OFFER_DC")==null||"0".equals(resMap.get("EGPNI_AS_OFFER_DC"))?"":resMap.get("EGPNI_AS_OFFER_DC").toString()));
					beanObj.setOurAssessment((resMap.get("OURASSESSMENT")==null||"0".equals(resMap.get("OURASSESSMENT"))?"":resMap.get("OURASSESSMENT").toString()));
					
					beanObj.setInwardType(resMap.get("INWARD_BUS_TYPE")==null ? "" : resMap.get("INWARD_BUS_TYPE").toString());
					beanObj.setBusinessType(resMap.get("RSK_BUSINESS_TYPE")==null ? "" : resMap.get("RSK_BUSINESS_TYPE").toString());
					beanObj.setExchangeType(resMap.get("RSK_EXCHANGE_TYPE")==null ? "" : resMap.get("RSK_EXCHANGE_TYPE").toString());
					beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED")==null ? "" : resMap.get("RSK_PERILS_COVERED").toString());
					beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED")==null ? "" : resMap.get("RSK_LOC_ISSUED").toString());
					beanObj.setUmbrellaXL(resMap.get("RSK_UMBRELLA_XL")==null ? "" : resMap.get("RSK_UMBRELLA_XL").toString());
					beanObj.setEvent_limit((resMap.get("RSK_EVENT_LIMIT_OC")==null||"0".equals(resMap.get("RSK_EVENT_LIMIT_OC"))?"":resMap.get("RSK_EVENT_LIMIT_OC").toString()));
					beanObj.setCoverLimitXL((resMap.get("RSK_COVER_LIMIT_UXL_OC")==null||"0".equals(resMap.get("RSK_COVER_LIMIT_UXL_OC"))?"":resMap.get("RSK_COVER_LIMIT_UXL_OC").toString()));
					beanObj.setDeductLimitXL((resMap.get("RSK_DEDUCTABLE_UXL_OC")==null||"0".equals(resMap.get("RSK_DEDUCTABLE_UXL_OC"))?"":resMap.get("RSK_DEDUCTABLE_UXL_OC").toString()));
					beanObj.setPml(resMap.get("RSK_PML")==null ? "" : resMap.get("RSK_PML").toString());
					beanObj.setPmlPercent(resMap.get("RSK_PML_PERCENT")==null ? "" : resMap.get("RSK_PML_PERCENT").toString());
					beanObj.setEgnpipml((resMap.get("RSK_EGNPI_PML_OC")==null||"0".equals(resMap.get("RSK_EGNPI_PML_OC"))?"":resMap.get("RSK_EGNPI_PML_OC").toString()));
					beanObj.setPremiumbasis(resMap.get("RSK_PREMIUM_BASIS")==null ? "" : resMap.get("RSK_PREMIUM_BASIS").toString());
					beanObj.setMinimumRate(resMap.get("RSK_MINIMUM_RATE")==null ? "" : resMap.get("RSK_MINIMUM_RATE").toString());
					beanObj.setMaximumRate(resMap.get("RSK_MAXIIMUM_RATE")==null ? "" : resMap.get("RSK_MAXIIMUM_RATE").toString());
					beanObj.setBurningCostLF(resMap.get("RSK_BURNING_COST_LF")==null ? "" : resMap.get("RSK_BURNING_COST_LF").toString());
					beanObj.setMinPremium((resMap.get("RSK_MINIMUM_PREMIUM_OC")==null ||"0".equals(resMap.get("RSK_MINIMUM_PREMIUM_OC"))?"":resMap.get("RSK_MINIMUM_PREMIUM_OC").toString()));
					beanObj.setPaymentDuedays(resMap.get("RSK_PAYMENT_DUE_DAYS")==null ? "" : resMap.get("RSK_PAYMENT_DUE_DAYS").toString());
					beanObj.setCountryIncludedList(resMap.get("COUNTRIES_INCLUDE")==null ? "" : resMap.get("COUNTRIES_INCLUDE").toString());
					beanObj.setCountryExcludedList(resMap.get("COUNTRIES_EXCLUDE")==null ? "" : resMap.get("COUNTRIES_EXCLUDE").toString());
					beanObj.setLocBankName(resMap.get("RSK_LOC_BNK_NAME")==null?"":resMap.get("RSK_LOC_BNK_NAME").toString());
					beanObj.setLocCreditPrd(resMap.get("RSK_LOC_CRDT_PRD")==null?"":resMap.get("RSK_LOC_CRDT_PRD").toString());
					beanObj.setLocCreditAmt(resMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(resMap.get("RSK_LOC_CRDT_AMT").toString()));
					beanObj.setLocBeneficerName(resMap.get("RSK_LOC_BENFCRE_NAME")==null?"":resMap.get("RSK_LOC_BENFCRE_NAME").toString());
					
				//}
				beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
				beanObj.setRenewal_contract_no(resMap.get("OLD_CONTRACTNO")==null?"":resMap.get("OLD_CONTRACTNO").toString());
				beanObj.setBaseLoginID(resMap.get("LOGIN_ID")==null?"":resMap.get("LOGIN_ID").toString());
				beanObj.setPaymentPartner(resMap.get("PAYMENT_PARTNER")==null?"":resMap.get("PAYMENT_PARTNER").toString());
				beanObj.setRateOnLine(resMap.get("RATE_ON_LINE")==null?"":resMap.get("RATE_ON_LINE").toString());
				beanObj.setRiskdetailYN(resMap.get("RISK_DET_YN")==null?"N":resMap.get("RISK_DET_YN").toString());
				beanObj.setBrokerdetYN(resMap.get("BROKER_DET_YN")==null?"N":resMap.get("BROKER_DET_YN").toString());
				beanObj.setPremiumdetailYN(resMap.get("PREMIUM_DET_YN")==null?"N":resMap.get("PREMIUM_DET_YN").toString());
				beanObj.setInstallYN(resMap.get("INTALL_DET_YN")==null?"N":resMap.get("INTALL_DET_YN").toString());
				beanObj.setAcqdetailYN(resMap.get("ACQCOST_DET_YN")==null?"N":resMap.get("ACQCOST_DET_YN").toString());
				beanObj.setReinstdetailYN(resMap.get("REINST_DET_YN")==null?"N":resMap.get("REINST_DET_YN").toString());
				
				
				saveFlag = true;
			}

			if ("3".equalsIgnoreCase(beanObj.getProduct_id())){
				if(StringUtils.isNotBlank(beanObj.getContNo())&&!"0".equals(beanObj.getContNo())){
					beanObj.setPrclFlag(new DropDownControllor().getPLCLCountStatus(beanObj.getContNo(), beanObj.getLayerNo()));
				}else{
					beanObj.setPrclFlag(false);
				}
			}
			beanObj.setAmendId(new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no()));
			getClassLimitDetails(beanObj);
			GetRemarksDetails(beanObj);
			String proposalno="";
			if (StringUtils.isNotEmpty(beanObj.getLayerProposalNo())) {
				proposalno = beanObj.getLayerProposalNo();
			} else {
				proposalno = beanObj.getProposal_no();
			}
			this.showSecondpageEditItems(beanObj, beanObj.getProduct_id(), proposalno);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return saveFlag;
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

	public Object[] getProposalSaveEditModeQuery(final RiskDetailsBean beanObj, final String pid,String endNo) {
		Object[] obj=null;
		obj = new Object[36];
		logger.info("Exchange Rate" + beanObj.getExchRate());
		obj[0] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur().replaceAll(",", "");
		obj[1] = StringUtils.isEmpty(beanObj.getLimitOrigCur())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitOrigCur().replaceAll(",", ""), beanObj.getExchRate());
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
		//obj[26] = args;
		obj[35]=endNo==null?"0":endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public String[] getFirstPageEditSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		args = new String[55];
		args[0] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[1] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0" : beanObj.getProfit_Center();
		args[2] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0" : beanObj.getSubProfit_center();
		args[3] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[4] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[5] = StringUtils.isEmpty(beanObj.getBroker()) ? "63" : beanObj.getBroker();
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
		args[20] = StringUtils.isEmpty(beanObj.getProStatus()) ? ""	: beanObj.getProStatus();
		args[21] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
		args[22] = "0";
		args[23] = "0";
		args[24] = "0";
		
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0"	: beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0" : beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "" : beanObj.getInsuredName();
		args[29]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[30]=StringUtils.isEmpty(beanObj.getTreatyType()) ? ""	: beanObj.getTreatyType();
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		
		args[36] = beanObj.getLoginId();
		args[37] = beanObj.getBranchCode();
		args[38] = StringUtils.isEmpty(beanObj.getCountryIncludedList()) ? ""	:beanObj.getCountryIncludedList();
		args[39] = StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	:beanObj.getCountryExcludedList();
		args[40] = "";
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] ="0";
		args[43] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[44] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[45] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[46] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[47] = "";
		args[48]="";
		args[49]=StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		args[50] = beanObj.getAccountingPeriodNotes();
		args[51] =beanObj.getStatementConfirm();
		args[52] = beanObj.getProposal_no();
		//args[30] = beanObj.getProposal_no();
		args[53]=endNo;
		args[54] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	public String[] getFirstPageInsertAruguments(final RiskDetailsBean beanObj, final String pid, final boolean amendId) {
		String[] args= new String[59];
		if (amendId) {
			args[0] = beanObj.getProposal_no();
			args[1] =(Integer.parseInt(getMaxAmednId(beanObj.getProposal_no()))+1)+"";
			args[26] = beanObj.getContNo();
		}else{
			args[26] = "";
			if(StringUtils.isBlank(beanObj.getProposal_no())){
			beanObj.setProposal_no(getMaxProposanlno(pid,beanObj.getBranchCode(),beanObj.getDepartmentId()));
			}
			args[0] = beanObj.getProposal_no();
			args[1] = "0";
		}
		args[2] = StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
		args[27] = "";
		args[28] = "0";
		args[29] = "0";
		args[30] = "0";
		args[3] = pid;
		args[4] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[5] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0" : beanObj.getProfit_Center();
		args[6] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0" : beanObj.getSubProfit_center();
		args[7] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[8] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[9] = StringUtils.isEmpty(beanObj.getBroker()) ? "63" : beanObj.getBroker();
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
		args[37]=StringUtils.isEmpty(beanObj.getTreatyType()) ? ""	: beanObj.getTreatyType();
		args[38]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[39]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[40]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[41]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[42]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[43] = beanObj.getLoginId();
		args[44] = beanObj.getBranchCode();
		args[45] = StringUtils.isEmpty(beanObj.getCountryIncludedList()) ? ""	:beanObj.getCountryIncludedList();
		args[46] = StringUtils.isEmpty(beanObj.getCountryExcludedList()) ? ""	:beanObj.getCountryExcludedList();
		args[47] ="";
		args[48] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[49] = "0";
		args[50] =StringUtils.isEmpty(beanObj.getLocBankName()) ? ""	:beanObj.getLocBankName();
		args[51] =StringUtils.isEmpty(beanObj.getLocCreditPrd()) ? ""	:beanObj.getLocCreditPrd();
		args[52] =StringUtils.isEmpty(beanObj.getLocCreditAmt()) ? ""	:beanObj.getLocCreditAmt().replaceAll(",", "");
		args[53] =StringUtils.isEmpty(beanObj.getLocBeneficerName()) ? ""	:beanObj.getLocBeneficerName();
		args[54]="";
		args[55]="";
		args[56]=StringUtils.isEmpty(beanObj.getRetentionYN()) ? ""	:beanObj.getRetentionYN();
		args[57] = beanObj.getAccountingPeriodNotes();
		args[58] =beanObj.getStatementConfirm();
		logger.info("Args[]=>" +StringUtils.join(args,","));
		return args;	
	}

	public String getShortname(RiskDetailsBean bean) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] { bean.getBranchCode()}, String.class);
		return Short;
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public boolean updateFirstPageFields(final RiskDetailsBean beanObj,String endNo){
		boolean updateStatus = true;
		int res=0;
		String query=getQuery("UPDATE_RISK_PROPOSAL_DETAILS");
		Object[] obj= new Object[55];
		try {
			obj[0] = StringUtils.isEmpty(beanObj.getEvent_limit()) ? "": beanObj.getEvent_limit();
			obj[1] = StringUtils.isEmpty(beanObj.getEvent_limit())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEvent_limit(), beanObj.getExchRate());
			obj[2] = StringUtils.isEmpty(beanObj.getEventLimitOurShare()) ? "0" : beanObj.getEventLimitOurShare();
			obj[3] = StringUtils.isEmpty(beanObj.getEventLimitOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEventLimitOurShare(), beanObj.getExchRate());
			
			obj[4] = StringUtils.isEmpty(beanObj.getCoverLimitXL()) ? "": beanObj.getCoverLimitXL();
			obj[5] = StringUtils.isEmpty(beanObj.getCoverLimitXL())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getCoverLimitXL(), beanObj.getExchRate());
			obj[6] = StringUtils.isEmpty(beanObj.getCoverLimitXLOurShare()) ? "0" : beanObj.getCoverLimitXLOurShare();
			obj[7] = StringUtils.isEmpty(beanObj.getCoverLimitXLOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getCoverLimitXLOurShare(), beanObj.getExchRate());
			
			obj[8] = StringUtils.isEmpty(beanObj.getDeductLimitXL()) ? "": beanObj.getDeductLimitXL();
			obj[9] = StringUtils.isEmpty(beanObj.getDeductLimitXL())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getDeductLimitXL(), beanObj.getExchRate());
			obj[10] = StringUtils.isEmpty(beanObj.getDeductLimitXLOurShare()) ? "0" : beanObj.getDeductLimitXLOurShare();
			obj[11] = StringUtils.isEmpty(beanObj.getDeductLimitXLOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getDeductLimitXLOurShare(), beanObj.getExchRate());
			
			obj[12] = StringUtils.isEmpty(beanObj.getPml()) ? "" : beanObj.getPml();
			obj[13] = StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0" : beanObj.getPmlPercent();
			
			obj[14] = StringUtils.isEmpty(beanObj.getEgnpipml()) ? "": beanObj.getEgnpipml();
			obj[15] = StringUtils.isEmpty(beanObj.getEgnpipml())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEgnpipml(), beanObj.getExchRate());
			obj[16] = StringUtils.isEmpty(beanObj.getEgnpipmlOurShare()) ? "0" : beanObj.getEgnpipmlOurShare();
			obj[17] = StringUtils.isEmpty(beanObj.getEgnpipmlOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getEgnpipmlOurShare(), beanObj.getExchRate());
			
			obj[18] = StringUtils.isEmpty(beanObj.getPremiumbasis()) ? "" : beanObj.getPremiumbasis();
			obj[19] = StringUtils.isEmpty(beanObj.getMinimumRate()) ? "0" : beanObj.getMinimumRate();
			obj[20] = StringUtils.isEmpty(beanObj.getMaximumRate()) ? "0" : beanObj.getMaximumRate();
			obj[21] = StringUtils.isEmpty(beanObj.getBurningCostLF()) ? "0" : beanObj.getBurningCostLF();
			
			obj[22] = StringUtils.isEmpty(beanObj.getMinPremium()) ? "": beanObj.getMinPremium();
			obj[23] = StringUtils.isEmpty(beanObj.getMinPremium())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getMinPremium(), beanObj.getExchRate());
			obj[24] = StringUtils.isEmpty(beanObj.getMinPremiumOurShare()) ? "0" : beanObj.getMinPremiumOurShare();
			obj[25] = StringUtils.isEmpty(beanObj.getMinPremiumOurShare()) || StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : getDesginationCountry(beanObj.getMinPremiumOurShare(), beanObj.getExchRate());
			obj[26] = StringUtils.isEmpty(beanObj.getPaymentDuedays()) ? "0" : beanObj.getPaymentDuedays();
			
			obj[27] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
			obj[28] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
			obj[29] =StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS()) ? "0": beanObj.getLimitOrigCurPmlOS();
			obj[30] = StringUtils.isEmpty(beanObj.getLimitOrigCurPmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPmlOS(), beanObj.getExchRate());
			obj[31] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml()) ? "0": beanObj.getTreatyLimitsurplusOCPml();
			obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOCPml(), beanObj.getExchRate());
			obj[33] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS()) ? "0": beanObj.getTreatyLimitsurplusOCPmlOS();
			obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOCPmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOCPmlOS(), beanObj.getExchRate());
			obj[35] =StringUtils.isEmpty(beanObj.getEpipml()) ? "0": beanObj.getEpipml();
			obj[36] = StringUtils.isEmpty(beanObj.getEpipml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpipml(), beanObj.getExchRate());
			obj[37] =StringUtils.isEmpty(beanObj.getEpipmlOS()) ? "0": beanObj.getEpipmlOS();
			obj[38] = StringUtils.isEmpty(beanObj.getEpipmlOS())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpipmlOS(), beanObj.getExchRate());
			obj[39]=beanObj.getRiskdetailYN();
			obj[40]=beanObj.getBrokerdetYN();
			obj[41]=beanObj.getCoverdetYN();
			obj[42]=beanObj.getPremiumdetailYN();
			obj[43]=beanObj.getAcqdetailYN();
			obj[44]=beanObj.getCommissiondetailYN();
			obj[45]=beanObj.getDepositdetailYN();
			obj[46]=beanObj.getLossdetailYN();
			obj[47]=beanObj.getDocdetailYN();
			obj[48] = beanObj.getPaymentPartner();
			obj[49] = beanObj.getInstallYN();
			obj[50] = beanObj.getReinstdetailYN();
			obj[51] = beanObj.getRateOnLine();
			obj[52] =StringUtils.isEmpty(beanObj.getQuotesharePercent()) ? "0": beanObj.getQuotesharePercent();
			obj[53] = beanObj.getProposal_no();
			obj[54]=endNo;
			logger.info("Args[]=>" + StringUtils.join(obj,","));
			logger.info("updateQry " + query);
			res=this.mytemplate.update(query, obj);
			logger.info("Result=>" + res);
			if (res> 0) {
				updateStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
		
	}
	private void insertClassLimit(RiskDetailsBean beanObj) {
		try {
			if("3".equalsIgnoreCase(beanObj.getProduct_id()) || "5".equalsIgnoreCase(beanObj.getProduct_id())){
			String  deleteQuery=getQuery("DELETE_CLASS_LIMIT");
			Object[] dobj= new Object[1];
			dobj[0]=beanObj.getProposal_no();
			//dobj[1]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_CLASS_LIMIT");
			if(!"5".equalsIgnoreCase(beanObj.getBusinessType())){
			for(int i=0;i<beanObj.getCoverLimitOC().size();i++){
				Object[] obj= new Object[15];
				obj[0]=beanObj.getProposal_no();
				obj[1]=(getMaxAmednId(beanObj.getProposal_no()))+"";
				obj[2]=beanObj.getContNo();
				obj[3]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				obj[4]=beanObj.getProduct_id();
				obj[5]=beanObj.getCoverdepartId().get(i);
				obj[6]=beanObj.getCoverLimitOC().get(i).replace(",", "");
				obj[7]="";
				obj[8]=beanObj.getDeductableLimitOC().get(i).replace(",", "");
				obj[9]="";
				obj[10]=beanObj.getBranchCode();
				obj[11]=i+1;
				obj[12]=beanObj.getEgnpiAsPerOff().get(i).replace(",", "");
				obj[13]=beanObj.getGnpiAsPO()==null?"0":beanObj.getGnpiAsPO().get(i).replace(",", "");
				obj[14]=beanObj.getNetMaxRetentPer().get(i).replace(",", "");
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
				String val = beanObj.getCoverLimitOC().get(i).replace(",", "");
			}
			}
			else{
				for(int i=0;i<beanObj.getCoverLimitAmount().size();i++){
					Object[] obj= new Object[14];
					obj[0]=beanObj.getProposal_no();
					obj[1]=(getMaxAmednId(beanObj.getProposal_no()))+"";
					obj[2]=beanObj.getContNo();
					obj[3]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
					obj[4]=beanObj.getProduct_id();
					obj[5]=beanObj.getCoverdepartIdS().get(i);
					obj[6]=beanObj.getCoverLimitAmount().get(i).replace(",", "");
					obj[7]=beanObj.getCoverLimitPercent().get(i).replace(",", "");
					obj[8]=beanObj.getDeductableLimitAmount().get(i).replace(",", "");
					obj[9]=beanObj.getDeductableLimitPercent().get(i).replace(",", "");
					obj[10]=beanObj.getBranchCode();
					obj[11]=i+1;
					obj[12]=beanObj.getEgnpiAsPerOffSlide().get(i).replace(",", "");
					obj[13]=beanObj.getGnpiAsPOSlide().get(i).replace(",", "");
					obj[14]="";
					logger.info("Args[]=>" + StringUtils.join(obj,","));
					this.mytemplate.update(query, obj);
				
		}
			}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void getClassLimitDetails(RiskDetailsBean beanObj) {
		try {
			List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
			String query=getQuery("GET_CLASS_LIMIT_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=beanObj.getProposal_no();
			obj[1]=StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				//List<String> coversubdepartId = new ArrayList<String>();
				List<String> coverLimitAmount = new ArrayList<String>();
				List<String> coverLimitPercent = new ArrayList<String>();
				List<String> deductableLimitAmount = new ArrayList<String>();
				List<String> deductableLimitPercent = new ArrayList<String>();
				List<String> netMaxRetentPer = new ArrayList<String>();
				
				//SList<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
				List<String> egnpi = new ArrayList<String>();
				List<String> gnpi = new ArrayList<String>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					coverdepartId.add(insMap.get("RSK_COVER_CLASS")==null?"":insMap.get("RSK_COVER_CLASS").toString());
					//coversubdepartId.add(insMap.get("RSK_COVER_SUB_CLASS")==null?"":insMap.get("RSK_COVER_SUB_CLASS").toString());
					coverLimitAmount.add(insMap.get("RSK_COVER_LIMT")==null?"0.00":DropDownControllor.formatter(insMap.get("RSK_COVER_LIMT").toString()));
					coverLimitPercent.add(insMap.get("RSK_COVER_LIMT_PERCENTAGE")==null?"0.00":insMap.get("RSK_COVER_LIMT_PERCENTAGE").toString());
					deductableLimitAmount.add(insMap.get("RSK_DEDUCTABLE_LIMT")==null?"0.00":DropDownControllor.formatter(insMap.get("RSK_DEDUCTABLE_LIMT").toString()));
					deductableLimitPercent.add(insMap.get("RSK_DEDUCTABLE_PERCENTAGE")==null?"0.00":insMap.get("RSK_DEDUCTABLE_PERCENTAGE").toString());
					egnpi.add(insMap.get("RSK_EGNPI_AS_OFF")==null?"0.00":DropDownControllor.formatter(insMap.get("RSK_EGNPI_AS_OFF").toString()));
					gnpi.add(insMap.get("RSK_GNPI_AS_OFF")==null?"0.00":DropDownControllor.formatter(insMap.get("RSK_GNPI_AS_OFF").toString()));
					//ScoversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(insMap.get("RSK_COVER_CLASS")==null?"":insMap.get("RSK_COVER_CLASS").toString(),beanObj.getBranchCode(),beanObj.getProduct_id()));
					netMaxRetentPer.add(insMap.get("RSK_NET_MAX_RETENT_PERCENT")==null?"0.00":DropDownControllor.formatter(insMap.get("RSK_NET_MAX_RETENT_PERCENT").toString()));
					
				}
				
				
				if(!beanObj.getBusinessType().equalsIgnoreCase("5") && !beanObj.getBusinessType().equalsIgnoreCase("Stop Loss")){
					beanObj.setCoverdepartId(coverdepartId);
					beanObj.setCoverdepartIdRe(coverdepartId);
					//SbeanObj.setCoversubdepartId(coversubdepartId);
					 if(StringUtils.isNotBlank(beanObj.getReinstatementOption()) && "U".equalsIgnoreCase(beanObj.getReinstatementOption())){
						 beanObj.setHcoverLimitOC(coverLimitAmount);
						 beanObj.setHcoverLimitOCRe(coverLimitAmount);
					 }else{
						 beanObj.setCoverLimitOC(coverLimitAmount);
						 beanObj.setCoverLimitOCRe(coverLimitAmount);
						 
						 beanObj.setHcoverLimitOC(coverLimitAmount);
						 beanObj.setHcoverLimitOCRe(coverLimitAmount);
						 
					 }
					beanObj.setDeductableLimitOC(deductableLimitAmount);
					beanObj.setEgnpiAsPerOff(egnpi);
					beanObj.setGnpiAsPO(gnpi);
					beanObj.setNetMaxRetentPer(netMaxRetentPer);
					//SbeanObj.setCoversubDepartList(coversubdeptList);
					beanObj.setLoopcount(Integer.toString(result.size()));
				}else{
					beanObj.setCoverdepartIdS(coverdepartId);
					//SbeanObj.setCoversubdepartId(coversubdepartId);
					beanObj.setCoverLimitAmount(coverLimitAmount);
					beanObj.setDeductableLimitAmount(deductableLimitAmount);
					beanObj.setCoverLimitPercent(coverLimitPercent);
					beanObj.setDeductableLimitPercent(deductableLimitPercent);
					beanObj.setEgnpiAsPerOffSlide(egnpi);
					beanObj.setGnpiAsPOSlide(gnpi);
					beanObj.setNetMaxRetentPer(netMaxRetentPer);
					//SbeanObj.setCoversubDepartList(coversubdeptList);
					
					beanObj.setCount(Integer.toString(result.size()));
				}
				
				if("copy".equals(beanObj.getFlag())) {
					coverLimitAmount = new ArrayList<String>();
					deductableLimitAmount = new ArrayList<String>();
					String sql=getQuery("GET_MAX_LAYER_NO");
					String proposalno=StringUtils.isBlank(beanObj.getBaseLayer())?beanObj.getProposal_no():beanObj.getBaseLayer();
					String layermax=this.mytemplate.queryForObject(sql, new Object[] {proposalno},String.class);
					if(Integer.parseInt(beanObj.getLayerNo())>=Integer.parseInt(layermax)) {
						for(int j=0;j<result.size();j++) {
							Map<String, Object> insMap = (Map<String, Object>)result.get(j);
							coverLimitAmount.add("");
							String coverAmount=insMap.get("RSK_COVER_LIMT")==null?"0.00":insMap.get("RSK_COVER_LIMT").toString();
							String coverAmount1=insMap.get("RSK_DEDUCTABLE_LIMT")==null?"0.00":insMap.get("RSK_DEDUCTABLE_LIMT").toString();
							String deducttotal=String.valueOf(Double.parseDouble(coverAmount1)+Double.parseDouble(coverAmount));
							deductableLimitAmount.add(DropDownControllor.formatter(deducttotal));
						}
					}else {
						for(int j=0;j<result.size();j++) {
							coverLimitAmount.add("");
							deductableLimitAmount.add("");
						}
					}
					beanObj.setCoverLimitOC(coverLimitAmount);
					beanObj.setDeductableLimitOC(deductableLimitAmount);
				}
				
				beanObj.setCoverList(result);
				
				
			}else{
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				 doubleMap.put("one",new Double(1.0));
				 result.add(doubleMap);
				 beanObj.setCoverList(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<Map<String, Object>> getReInstatementDetailsList(RiskDetailsBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String query="";
		 List<String>  sno = new ArrayList<String>();
         List<String> type = new ArrayList<String>();
         List<String> amount = new ArrayList<String>();
         List<String> minamount = new ArrayList<String>();
         List<String> minTime = new ArrayList<String>();
         List<String> coverdepartId = new ArrayList<String>();
         List<String> annualAgri = new ArrayList<String>();
         String proposalNo = bean.getProposal_no();
         Object args[]=null;
         int count =0;
		try{
				args = new Object[2];
				args[0] = proposalNo;
				args[1] = bean.getBranchCode();
					query =getQuery("REINSTATEMENT_MAIN_SELECT_A");
					result = this.mytemplate.queryForList(query,args);
					if(CollectionUtils.isEmpty(result)) {
						args[0] = bean.getReferenceNo();
						query =getQuery("REINSTATEMENT_MAIN_SELECT_A_REFERENCE");
						result = this.mytemplate.queryForList(query,args);
					}
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               sno.add(tempMap.get("REINST_NO")==null?"":tempMap.get("REINST_NO").toString());
		               type.add(tempMap.get("REINST_TYPE")==null?"":tempMap.get("REINST_TYPE").toString());
		               amount.add(tempMap.get("AMOUNT_PERCENT")==null?"":tempMap.get("AMOUNT_PERCENT").toString());
		               minamount.add(tempMap.get("MIN_AMOUNT_PERCENT")==null?"":tempMap.get("MIN_AMOUNT_PERCENT").toString());
		               minTime.add(tempMap.get("MIN_TIME_PERCENT")==null?"":tempMap.get("MIN_TIME_PERCENT").toString());
		               bean.setReinstatementOption(tempMap.get("REINSTATEMENT")==null?"":tempMap.get("REINSTATEMENT").toString());
				}
	               bean.setReinstatementNo(sno);
	               bean.setReinstatementTypeId(type);
	               bean.setReinstatementAmount(amount);
	               bean.setReinstatementMinAmount(minamount);
	               bean.setReinstatementMinTime(minTime); 
	               
	              /* query =getQuery("REINSTATEMENT_MAIN_SELECT_B");
	               list = this.mytemplate.queryForList(query,args);
	               if(CollectionUtils.isEmpty(result)) {
	            		args[0] = bean.getReferenceNo();
						query =getQuery("REINSTATEMENT_MAIN_SELECT_B_REFERENCE");
						result = this.mytemplate.queryForList(query,args);
					}
	               for(int i=0;i<list.size();i++){
		               Map<String,Object> tempMap = list.get(i);
		               coverdepartId.add(tempMap.get("DEPARTMENT_CLASS")==null?"":tempMap.get("DEPARTMENT_CLASS").toString());
		               annualAgri.add(tempMap.get("ANNUAL_AGGRE_LAIBLE")==null?"":DropDownControllor.formatter(tempMap.get("ANNUAL_AGGRE_LAIBLE").toString()));
	               }
	               if(list!=null && list.size()>0){
	            	   bean.setCoverdepartIdRe(coverdepartId); 
	            	   bean.setCoverLimitOCRe(annualAgri);
	            	   bean.setCoverListRe(list);
	               }if(!"U".equalsIgnoreCase(bean.getReinstatementOption())){
	               bean.setBusinessType("1");
	               getClassLimitDetails(bean);
	               if(result!=null && result.size()>0){
	            	   double coverLimit=0.00;
	            	   List<String> coverLimitAmount = new ArrayList<String>();
	            	   List<String> LimitAmount = new ArrayList<String>();
	            	   if(bean.getCoverList()!=null && bean.getCoverList().size()>0){
	            		   for(int i=0;i<bean.getCoverList().size();i++){
	            			   LimitAmount.add(bean.getCoverLimitOC().get(i));
	            			   if(StringUtils.isNotBlank(bean.getReinstatementOption()) && "S".equalsIgnoreCase(bean.getReinstatementOption())){
		            			   coverLimit= Double.parseDouble(bean.getCoverLimitOC().get(i).replace(",", "")) *(double)(result.size()+1);
		            			   coverLimitAmount.add(Double.toString(coverLimit));
	            			   }else{
	            				   coverLimit= Double.parseDouble(bean.getCoverLimitOC().get(i).replace(",", "")) *1;
		            			   coverLimitAmount.add(Double.toString(coverLimit));
	            			   }
	            	  
	            	   }
	            		  // if(StringUtils.isNotBlank(bean.getReinstatementOption()) && "S".equalsIgnoreCase(bean.getReinstatementOption())){
	            		   bean.setCoverLimitOC(coverLimitAmount);
	            		   bean.setHcoverLimitOC(LimitAmount);
	            		  /* }
	            		   else{
	            			   bean.setCoverLimitOC(new ArrayList<String>());
	            			   bean.setHcoverLimitOC(new ArrayList<String>());
	            		   }
	            	   }
	               }
	              }else{
	            	  bean.setBusinessType("1");
	            	  getClassLimitDetails(bean);
	              }*/
		}
	
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	

	public int getReInstatementCount(RiskDetailsBean bean, String pageFor) {
		String query ="";
		Object args[]=null;
		int result=0;
		try{
			/*query =getQuery("REINSTATEMENT_COUNT_ARCH");
			args = new Object[2];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			result = this.mytemplate.queryForInt(query,args);
			if(result<=0){*/
				if(StringUtils.isBlank(bean.getAmendId())){
					bean.setAmendId("0");
				}
				query =getQuery("REINSTATEMENT_COUNT_MAIN");
				args = new Object[3];
				args[0] = bean.getProposal_no();
				args[1] = bean.getBranchCode();
				args[2] = bean.getAmendId();
				result = this.mytemplate.queryForInt(query,args);
				if(result==0) {
					query =getQuery("REINSTATEMENT_COUNT_MAIN_REFERENCE");
					args[0] = bean.getReferenceNo();
					result = this.mytemplate.queryForInt(query,args);
				}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String insertReinstatementDetails(RiskDetailsBean bean) {
		if(StringUtils.isBlank(bean.getAmendId())){
			bean.setAmendId("0");
		}
		MoveReinstatementMain(bean);
	    /*deleteArchTable(bean);
	    String query =getQuery("INSERT_REINSTATEMENT_RAW");
	    Object args[] = new Object[13];
			for(int i=0;i<bean.getReinstatementNo().size();i++){
			args[0] = bean.getReinstatementNo().get(i);
			args[1] = bean.getReinstatementTypeId().get(i);
			args[2] = bean.getReinstatementAmount().get(i);
			args[3] = bean.getReinstatementMinAmount().get(i);
			args[4] = bean.getReinstatementMinTime().get(i);
			args[5] = bean.getProposal_no();
			args[6] = bean.getBranchCode();
			args[7] = bean.getAmendId();
			args[8] = bean.getDepartmentId();
			args[9] = bean.getContractNo();
			args[10] = bean.getProduct_id();
			args[11] = StringUtils.isEmpty(bean.getLayerNo()) ? ""	: bean.getLayerNo();
			args[12] =bean.getReinstatementOption();
			this.mytemplate.update(query,args);
			}
			//updateTotalReinstatement(bean);*/
			return null;
		
	}

	private void updateTotalReinstatement(RiskDetailsBean bean) {
		try{
			if(bean.getReinstatementNo()!=null){
			String query=getQuery("UPDATE_REINSTATEMENT_TOTAL_NO");
			Object args[]=new Object[2];
			args[1] =bean.getProposal_no();
			args[0] = bean.getReinstatementNo().size();
			this.mytemplate.update(query,args);	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void deleteArchTable(RiskDetailsBean bean) {
		try{
			String query=getQuery("QUERY_ARCH_TABLE");
			Object args[] = new Object[2];
			args[0] =bean.getProposal_no();
			args[1] = bean.getBranchCode();
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void reInstatementMainInsert(RiskDetailsBean beanObj) {
		if("Y".equalsIgnoreCase(beanObj.getReInstatementPremium())){
				//getReInstatementDetailsList(beanObj);
				//MoveReinstatementMain(beanObj);
				updateTotalReinstatement(beanObj);
			}
			else{
				deleteMainTable(beanObj);
				MoveReinstatementEmptyData(beanObj);
			}
		//deleteArchTable(beanObj);
		updateContractNumber(beanObj);
	}

	private void updateContractNumber(RiskDetailsBean bean) {
		try{
			String query =getQuery("RE_INSTATEMENT_UPDATE");
			Object args[]= new Object[5];
			args[0] = bean.getContNo();
			args[1] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			args[2] = bean.getProposal_no();
			args[3] = bean.getBranchCode();
			args[4] = bean.getAmendId();
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}



	public void MoveReinstatementEmptyData(RiskDetailsBean bean) {
		try{
		if(StringUtils.isBlank(bean.getAmendId())){
			bean.setAmendId("0");
		}
		 if(StringUtils.isBlank(bean.getProposal_no()) && StringUtils.isBlank(bean.getReferenceNo())) {
	        	String referenceNo="";
	        	String query=getQuery("GET_REFERENCE_NO_SEQ");
	        	referenceNo=this.mytemplate.queryForObject(query, String.class);
	        	bean.setReferenceNo(referenceNo);
	        }
		 String query =getQuery("INSERT_REINSTATEMENT_MAIN");
	    Object args[] = new Object[15];
			args[0] = "";
			args[1] = "";
			args[2] = "";
			args[3] = "";
			args[4] = "";
			args[5] = bean.getProposal_no();
			args[6] = bean.getBranchCode();
			args[7] = bean.getAmendId();
			args[8] = bean.getDepartmentId();
			args[9] = bean.getContractNo();
			args[10] = bean.getProduct_id();
			args[11] = StringUtils.isEmpty(bean.getLayerNo()) ? ""	: bean.getLayerNo();
			args[12] ="";
			args[13] = "A";
			args[14]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
			getClassLimitDetails(bean);
			query =getQuery("INSERT_REINSTATEMENT_MAIN_B");
			Object args1[] = new Object[11];
			for(int i=0;i<bean.getCoverdepartId().size();i++){
				args1[0] = bean.getCoverdepartId().get(i);
				args1[1] = bean.getCoverLimitOC().get(i).replaceAll(",", "");
				args1[2] = bean.getProposal_no();
				args1[3] = bean.getBranchCode();
				args1[4] = bean.getAmendId();
				args1[5] = bean.getDepartmentId();
				args1[6] ="";
				args1[7] = bean.getProduct_id();
				args1[8] = StringUtils.isEmpty(bean.getLayerNo()) ? "0"	: bean.getLayerNo();
				args1[9] = "B";
				args[10]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(args1, ","));
				this.mytemplate.update(query,args1);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
			
	}

	private void MoveReinstatementMain(RiskDetailsBean bean) {
		try{
		if(StringUtils.isBlank(bean.getAmendId())){
			bean.setAmendId("0");
		}
	    deleteMainTable(bean);
	    if(StringUtils.isBlank(bean.getProposal_no()) && StringUtils.isBlank(bean.getReferenceNo())) {
        	String referenceNo="";
        	String query=getQuery("GET_REFERENCE_NO_SEQ");
        	referenceNo=this.mytemplate.queryForObject(query, String.class);
        	bean.setReferenceNo(referenceNo);
        }
	    String query =getQuery("INSERT_REINSTATEMENT_MAIN");
	    Object args[] = new Object[15];
			for(int i=0;i<bean.getReinstatementNo().size();i++){
			args[0] = bean.getReinstatementNo().get(i);
			args[1] = bean.getReinstatementTypeId().get(i);
			args[2] = bean.getReinstatementAmount().get(i);
			args[3] = StringUtils.isEmpty(bean.getReinstatementMinAmount().get(i))?"":bean.getReinstatementMinAmount().get(i);
			args[4] = StringUtils.isEmpty(bean.getReinstatementMinTime().get(i))?"":bean.getReinstatementMinTime().get(i);
			args[5] = bean.getProposal_no();
			args[6] = bean.getBranchCode();
			args[7] = bean.getAmendId();
			args[8] = bean.getDepartmentId();
			args[9] ="";
			args[10] = bean.getProduct_id();
			args[11] = StringUtils.isEmpty(bean.getLayerNo()) ? "0"	: bean.getLayerNo();
			args[12] = bean.getReinstatementOption();
			args[13] = "A";
			args[14]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
			}
			/*query =getQuery("INSERT_REINSTATEMENT_MAIN_B");
			 Object args1[] = new Object[11];
			for(int i=0;i<bean.getCoverdepartId().size();i++){
				args1[0] = bean.getCoverdepartId().get(i);
				args1[1] = bean.getCoverLimitOC().get(i).replaceAll(",", "");
				args1[2] = bean.getProposal_no();
				args1[3] = bean.getBranchCode();
				args1[4] = bean.getAmendId();
				args1[5] = bean.getDepartmentId();
				args1[6] ="";
				args1[7] = bean.getProduct_id();
				args1[8] = StringUtils.isEmpty(bean.getLayerNo()) ? "0"	: bean.getLayerNo();
				args1[9] = "B";
				args[10]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(args1));
				this.mytemplate.update(query,args1);
			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void deleteMainTable(RiskDetailsBean bean) {
		String query1="";
		Object arg[]=null;
		try{
			if("".equalsIgnoreCase(bean.getAmendId())){
				query1 =getQuery("REINSTATEMENT_MAIN_DELETE");
				 arg = new Object[2];
				 arg[0] = bean.getProposal_no();
				 arg[1] = bean.getBranchCode();
			}else if(StringUtils.isBlank(bean.getProposal_no())) {
				query1 =getQuery("REINSTATEMENT_MAIN_DELETE_REF");
				 arg = new Object[2];
				 arg[0] = bean.getReferenceNo();
				 arg[1] = bean.getBranchCode();
			}
			else{
			 query1 =getQuery("REINSTATEMENT_MAIN_DELETE2");
			 arg = new Object[3];
			 arg[0] = bean.getProposal_no();
			 arg[1] = bean.getAmendId();
			 arg[2] = bean.getBranchCode();
			}
			this.mytemplate.update(query1,arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	public String getSumOfCover(RiskDetailsBean bean,String pid) {
		String result="";
		try{
			String query=getQuery("SUM_COVER_LIMT");
			Object args[]=new Object[7];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			args[2] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			args[3] = pid;
			args[4] = bean.getBranchCode();
			args[5] = bean.getProposal_no();
			//args[6] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			args[6] = pid;
			logger.info("query"+query);
			result=(String) this.mytemplate.queryForObject(query,args,String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
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
	public int getBonusListCount(RiskDetailsBean bean) {
		String query ="";
		Object args[]=null;
		int result=0;
		try{
		
			if(StringUtils.isBlank(bean.getAmendId())){
				bean.setAmendId("0");
			}
			query =getQuery("BONUS_COUNT_MAIN");
			args = new Object[5];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			args[2] = bean.getAcqBonus();
			args[3] = bean.getAmendId();
			args[4] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			result = this.mytemplate.queryForInt(query,args);
			if(result==0) {
				query =getQuery("BONUS_COUNT_MAIN_REFERENCE");
				args[0] = bean.getReferenceNo();
				result = this.mytemplate.queryForInt(query,args);
			}
		}
		catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
		}
	return result;
}



	public String LowClaimBonusInser(RiskDetailsBean bean) {
		try{
			if(StringUtils.isBlank(bean.getEndorsmentno())){
				String query=getQuery("GET_AMEND_ID");
				Object args[]=new Object[1];
	           args[0] =bean.getProposal_no();
	           bean.setEndorsmentno(this.mytemplate.queryForObject(query,args, String.class))	;
	           if(StringUtils.isBlank(bean.getEndorsmentno())) {
	        	   bean.setEndorsmentno("0");
	           }
			}
	        deleteMaintable(bean);
	        if(StringUtils.isBlank(bean.getProposal_no()) && StringUtils.isBlank(bean.getReferenceNo())) {
	        	String referenceNo="";
	        	String query=getQuery("GET_REFERENCE_NO_SEQ");
	        	referenceNo=this.mytemplate.queryForObject(query, String.class);
	        	bean.setReferenceNo(referenceNo);
	        }
	        String query =getQuery("BONUS_MAIN_INSERT");
			Object args[]=new Object[15];
			for(int i=0;i<bean.getBonusFrom().size();i++){
				if(StringUtils.isNotBlank(bean.getBonusFrom().get(i)) && StringUtils.isNotBlank(bean.getBonusTo().get(i)) &&StringUtils.isNotBlank(bean.getBonusLowClaimBonus().get(i)) ){
			           args[0] =bean.getProposal_no();
			           args[1] = bean.getContractNo();
			           args[2] = bean.getProduct_id();
			           args[3] = bean.getBonusTypeId();
			           args[4] = bean.getBonusFrom().get(i).replace(",", "");
			           args[5] = bean.getBonusTo().get(i).replace(",", "");
			           args[6] =bean.getBonusLowClaimBonus().get(i).replace(",", "");
			           args[7] = bean.getLoginId();
			           args[8] = bean.getBranchCode();
			           args[9] = bean.getBonusSNo().get(i).replace(",", "");
			           args[10] =bean.getAcqBonus();
			           args[11] = bean.getEndorsmentno();
			           args[12] =bean.getDepartmentId();
			           args[13] =StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			           args[14]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
			           logger.info("Query=>"+query);
			           logger.info("Args=>"+StringUtils.join(args, ","));
					   this.mytemplate.update(query,args);
					}
				
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



	


	private void deleteMaintable(RiskDetailsBean bean) {
		String query1="";
		Object arg[]=null;
		String acqBonus =bean.getAcqBonus();
		String type="";
		try{
			query1=getQuery("BONUS_PREVIOUS_TYPE_CHECK");
			 arg = new Object[3];
			 arg[0] = bean.getProposal_no();
			 arg[1] = bean.getBranchCode();
			 arg[2] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			 type = (String) this.mytemplate.queryForObject(query1, arg,String.class);
			 if(!type.equalsIgnoreCase(acqBonus)){
				 acqBonus=type;
			 }
			if("".equalsIgnoreCase(bean.getEndorsmentno())){
				query1 =getQuery("BONUS_MAIN_DELETE");
				 arg = new Object[4];
				 arg[0] = bean.getProposal_no();
				 arg[1] = bean.getBranchCode();
				 arg[2] = acqBonus;
				 arg[3] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			}
			else{
			 query1 =getQuery("BONUS_MAIN_DELETE2");
			 arg = new Object[5];
			 arg[0] = bean.getProposal_no();
			 arg[1] = bean.getEndorsmentno();
			 arg[2] = bean.getBranchCode();
			 arg[3] = acqBonus;
			 arg[4] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
			}
			this.mytemplate.update(query1,arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}



	public List<Map<String, Object>> getLowClaimBonusList(RiskDetailsBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		String query="";
		 List<String> bonusSno = new ArrayList<String>();
         List<String> bonusFrom = new ArrayList<String>();
         List<String> bonusTo = new ArrayList<String>();
         String proposalNo = bean.getProposal_no();
         List<String> bonusLowClaimBonus = new ArrayList<String>();
         Object args[]=null;
         int count =0;
		try{
				args = new Object[4];
				args[0] = proposalNo;
				args[1] = bean.getBranchCode();
				args[2] = bean.getAcqBonus();
				args[3] = "LR";
					query =getQuery("BONUS_MAIN_SELECT");
					result = this.mytemplate.queryForList(query,args);
					if(CollectionUtils.isEmpty(result)) {
						args[0] = bean.getReferenceNo();
						query =getQuery("BONUS_MAIN_SELECT_REFERENCE");
						result = this.mytemplate.queryForList(query,args);
					}
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
		return result;
	}
	private void insertBonusDetails(RiskDetailsBean beanObj) {
		if(!"LCB".equalsIgnoreCase(beanObj.getAcqBonus())){
				insetNOClaimBonusMainTable(beanObj);
			}
				String query = getQuery("UPDATE_CONTRACT_DETAILS");
				Object args[]=new Object[6];
				args[1] =beanObj.getProposal_no();
				args[0] = beanObj.getContNo();
				args[2] = beanObj.getBranchCode();
				args[3] = beanObj.getAcqBonus();
				args[4] =  beanObj.getAmendId();
				args[5] =  StringUtils.isEmpty(beanObj.getLayerNo())?"0":beanObj.getLayerNo();
				this.mytemplate.update(query,args);
	}
	
	public void insetNOClaimBonusMainTable(RiskDetailsBean bean) {
		try{
			if(StringUtils.isBlank(bean.getEndorsmentno())){
				String query=getQuery("GET_AMEND_ID");
					Object args[]=new Object[1];
		           args[0] =bean.getProposal_no();
		           bean.setEndorsmentno(this.mytemplate.queryForObject(query,args, String.class))	;
			}
			deleteMaintable(bean);
			if(StringUtils.isBlank(bean.getProposal_no()) && StringUtils.isBlank(bean.getReferenceNo())) {
	        	String referenceNo="";
	        	String query=getQuery("GET_REFERENCE_NO_SEQ");
	        	referenceNo=this.mytemplate.queryForObject(query, String.class);
	        	bean.setReferenceNo(referenceNo);
	        }
			String query =getQuery("BONUS_MAIN_INSERT");
			Object args[]=new Object[15];
		           args[0] =bean.getProposal_no();
		           args[1] = bean.getContractno();
		           args[2] = bean.getProduct_id();
		           args[3] = "";
		           args[4] = "";
		           args[5] = "";
		           args[6] ="";
		           args[7] = bean.getLoginId();
		           args[8] = bean.getBranchCode();
		           args[9] = "1";
		           args[10] =bean.getAcqBonus();
		           args[11] = bean.getAmendId();
		           args[12] = bean.getDepartmentId();
		           args[13] = StringUtils.isEmpty(bean.getLayerNo())?"0":bean.getLayerNo();
		           args[14] = StringUtils.isEmpty(bean.getReferenceNo())?"0":bean.getReferenceNo();
		           logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
	}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	public void insertIEModule(RiskDetailsBean bean) {
		try{
			String tranId="";
			String amendId="";
			if(StringUtils.isBlank(bean.getTransactionNo())){
			String sql="SELECT IE_RETRO_XOL_06.NEXTVAL FROM DUAL";
			tranId=this.mytemplate.queryForObject(sql,String.class);
			bean.setTransactionNo(tranId);
			amendId="0";
			}else{
				tranId=bean.getTransactionNo();
				String sql="SELECT MAX(AMEND_ID)+1  FROM TTRN_IE_MODULE WHERE PROPOSAL_NO=? AND TRANSACTION_NO=? AND BRANCH_CODE=?";
				amendId=this.mytemplate.queryForObject(sql,new Object[]{bean.getProposalNo(),bean.getTransactionNo(),bean.getBranchCode()},String.class);
			}
			String query =getQuery("INCLUSION_EXCLUSION_INSERT");
			
			
			if(StringUtils.isNotBlank(bean.getIncludedList())){
				String[]value=bean.getIncludedList().split(",");
				for(int i=0;i<value.length;i++){
					Object args[]=new Object[11];
					args[0] =bean.getProposalNo();
					args[1] =bean.getContarctno();
					args[2] =bean.getLayerNo();
					args[3] = tranId;
					String[]value1=value[i].split("~");
					args[4] = value1[0];
					args[5] = value1[1];
					args[6] = "I";
					args[7] = amendId;
					args[8] = bean.getBranchCode();
					args[9] = bean.getEffectiveDate();
					args[10] = bean.getLoginId();
					logger.info("Query=>"+query);
					logger.info("Args=>"+StringUtils.join(args, ","));
					this.mytemplate.update(query,args);
				}
			}
			if(StringUtils.isNotBlank(bean.getExcludedList())){
				String[]value=bean.getExcludedList().split(",");
				for(int i=0;i<value.length;i++){
					Object args[]=new Object[11];
					args[0] =bean.getProposalNo();
					args[1] =bean.getContarctno();
					args[2] =bean.getLayerNo();
					args[3] = tranId;
					String[]value1=value[i].split("~");
					args[4] = value1[0];
					args[5] = value1[1];
					args[6] = "E";
					args[7] = amendId;
					args[8] = bean.getBranchCode();
					args[9] = bean.getEffectiveDate();
					args[10] = bean.getLoginId();
					logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
				}
			}
			if(StringUtils.isNotBlank(bean.getExcludeProposalNo())){
				String[] value=bean.getExcludeProposalNo().split(",");
				for(int i=0;i<value.length;i++){
					Object args[]=new Object[11];
					args[0] =bean.getProposalNo();
					args[1] =bean.getContarctno();
					args[2] =bean.getLayerNo();
					args[3] = tranId;
					args[4] = "7";
					args[5] = value[i];
					args[6] = "E";
					args[7] = amendId;
					args[8] = bean.getBranchCode();
					args[9] = bean.getEffectiveDate();
					args[10] = bean.getLoginId();
					logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
				}
			}
		          
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public List<Map<String, Object>> getInclusionExList(RiskDetailsBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_INCLUSIONEX_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{bean.getProposalNo(), bean.getBranchCode(),bean.getProposalNo()});
			logger.info("Query=>"+query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}



	public void BaseLayerStatus(RiskDetailsBean beanObj, String pid) {
		try {
			String query=getQuery("GET_BASE_LAYER_DETAILS");
			logger.info("selectQry " + query);
			logger.info("Args[0]" + beanObj.getProposal_no());
			List<Map<String, Object>> res1 = this.mytemplate.queryForList(query,new Object[]{pid,beanObj.getBranchCode(),beanObj.getProposal_no()});
			logger.info("Result Size=>" +  res1.size());
			Map<String, Object> resMap1 = null;
			if(res1!=null && res1.size()>0)
				resMap1 = (Map<String, Object>)res1.get(0);
			if (resMap1 != null) {
					beanObj.setBaseLayerYN(resMap1.get("BASE_LAYER")==null?"":resMap1.get("BASE_LAYER").toString());
			}
			if(StringUtils.isNotBlank(beanObj.getBaseLayerYN())){
				query=getQuery("GET_BASE_LAYER_DETAILS");
				logger.info("selectQry " + query);
				logger.info("Args[0]" + beanObj.getProposal_no());
				res1 = this.mytemplate.queryForList(query,new Object[]{pid,beanObj.getBranchCode(),beanObj.getBaseLayerYN()});
				logger.info("Result Size=>" +  res1.size());
				resMap1 = null;
				if(res1!=null && res1.size()>0)
					resMap1 = (Map<String, Object>)res1.get(0);
				if (resMap1 != null) {
						beanObj.setBaseContractNo(resMap1.get("CONTRACT_NO")==null?"":resMap1.get("CONTRACT_NO").toString());
						beanObj.setBaseContractNoStatus(resMap1.get("CONTRACT_STATUS")==null?"":resMap1.get("CONTRACT_STATUS").toString());
						
				}
				if(("0".equalsIgnoreCase(beanObj.getBaseContractNo()) || StringUtils.isBlank(beanObj.getBaseContractNo())) && "P".equalsIgnoreCase(beanObj.getBaseContractNoStatus().trim())){
					beanObj.setProStatus("P");
					beanObj.setProdisableStatus("Y");
				}
				
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	public boolean GetShareValidation(RiskDetailsBean bean) {
		boolean result=false;
		try {
			String query=getQuery("GET_SIGN_SHARE_PRODUCT23");
			String out=this.mytemplate.queryForObject(query, new Object[]{bean.getProposal_no()},String.class);
			if(Double.parseDouble(out)+Double.parseDouble(bean.getLeader_Underwriter_share())>100){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private void InsertRemarkDetails(RiskDetailsBean beanObj) {
		try {
			String  deleteQuery=getQuery("DELETE_REMARKS_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposal_no();
			dobj[1]=beanObj.getLayerNo();
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_REMARKS_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_RISK_REMARKS WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposal_no()},String.class);
			for(int i=0;i<beanObj.getRemarkSNo().size();i++){
				Object[] obj= new Object[12];
				obj[0]=beanObj.getProposal_no();
				obj[1]=beanObj.getContractno();
				obj[2]=beanObj.getLayerNo();
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
			obj[1]=beanObj.getLayerNo();
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
	@Override
	public List<Map<String, Object>> getLayerInfo(RiskDetailsBean bean) {
		List<Map<String, Object>>result=new ArrayList<Map<String, Object>>();
		String query="";
		Object[] obj=null;
		try {
			if(StringUtils.isBlank(bean.getContNo())) {
				query=getQuery("GET_LAYER_INFO");
				obj= new Object[2];
				if("Y".equals(bean.getContractMode())) {
					obj[0]=bean.getBaseLayer();
					obj[1]=bean.getBaseLayer();
				}else {
					obj[0]=bean.getProposal_no();
					obj[1]=bean.getProposal_no();
				}
			}else {
				query=getQuery("GET_LAYER_CONTRACT_INFO");
				obj= new Object[2];
				if("Y".equals(bean.getContractMode())) {
					obj[0]=bean.getBaseLayer();
					obj[1]=bean.getBaseLayer();
				}else {
					obj[0]=bean.getProposal_no();
					obj[1]=bean.getProposal_no();
				}
			}
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void CancelProposal(RiskDetailsBean bean, String newProposal) {
		String query="";
		Object args[] = new Object[1];
		try{
			if(!"Layer".equalsIgnoreCase(bean.getProposalReference())){
			query=getQuery("CANCEL_OLD_PROPOSAL");
			args[0] = bean.getProposal_no();
			logger.info("Old Proposal[]=>"+StringUtils.join(args,","));
			logger.info("Sql=>"+query);
			this.mytemplate.update(query,args);
			}
			query=getQuery("CANCEL_PROPOSAL");
			args[0] = newProposal;
			logger.info("New Proposal[]=>"+StringUtils.join(args,","));
			logger.info("Sql=>"+query);
			this.mytemplate.update(query,args);
			
			
		}
		catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
			e.printStackTrace();
		}
	}
}