package com.maan.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.RiskDetailsDAO;

public class RiskDetailsDAOImpl extends MyJdbcTemplate implements RiskDetailsDAO {
	final Logger logger = LogUtil.getLogger(getClass());
	
	
	public boolean showSecondPageData(String proposal,RiskDetailsBean  formobj,String pid)  {
		logger.info("showSecondPageData() || Enter");
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
				//formobj.setProfit_Center(resMap.get("TMAS_PFC_NAME")==null?"":resMap.get("TMAS_PFC_NAME").toString());
				formobj.setSubProfit_center(resMap.get("TMAS_SPFC_NAME")==null?"":resMap.get("TMAS_SPFC_NAME").toString()); 
				formobj.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				formobj.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				formobj.setMonth(resMap.get("MONTH")==null?"":resMap.get("MONTH").toString());
				formobj.setUnderwriter(resMap.get("RSK_UWYEAR")==null?"":resMap.get("RSK_UWYEAR").toString());
				formobj.setPolBr(resMap.get("TMAS_POL_BRANCH_NAME")==null?"":resMap.get("TMAS_POL_BRANCH_NAME").toString());
				formobj.setDepartClass(resMap.get("TMAS_DEPARTMENT_NAME")==null?"":resMap.get("TMAS_DEPARTMENT_NAME").toString());
				formobj.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)", new Object[]{proposal}, String.class));
				formobj.setEndttypename(resMap.get("DETAIL_NAME")==null?"":resMap.get("DETAIL_NAME").toString());
			}
			if(StringUtils.isNotBlank(formobj.getNo_Insurer()) && Integer.parseInt(formobj.getNo_Insurer())>0 && (formobj.getRetroFinalList()==null || formobj.getRetroFinalList().size()==0)){
				if(StringUtils.isNotBlank(formobj.getNo_Insurer()) && Integer.parseInt(formobj.getNo_Insurer())>0){
					List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
					//List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
					List<String> retroPercentage=new ArrayList<String>();
					List<String> UWYear=new ArrayList<String>();
					for(int i=0;i<Integer.parseInt(formobj.getNo_Insurer());i++){
						retroFinalList.add(getRetroContractDetailsList(formobj,2,""));
						//retroDupList.add(getRetroContractDetailsList(beanObj,3));
					}
					if(StringUtils.isNotBlank(formobj.getNo_Insurer())){
						for (int z=0;z<Integer.parseInt(formobj.getNo_Insurer());z++){
							/*if("1".equalsIgnoreCase(formobj.getNo_Insurer())){
								retroPercentage.add("100");
								UWYear.add(formobj.getUwYear());
								retroDupList.add(getRetroContractDetailsList(formobj,3));
							}
							else if(0==z){
								UWYear.add(formobj.getUwYear());
								retroPercentage.add("");
								retroDupList.add(getRetroContractDetailsList(formobj,3));
							}else{
								retroDupList.add(new ArrayList<Map<String,Object>>());
							}*/
							formobj.setRetroDupYerar(formobj.getUwYear());
							List<Map<String,Object>> list = getRetroContractDetailsList(formobj,3,"");
							for (int i=0;i<list.size();i++){
								Map<String,Object> map = list.get(i);
								formobj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
							}
						}
					}
					
					formobj.setPercentRetro(retroPercentage);
					formobj.setRetroYear(UWYear);
					formobj.setRetroFinalList(retroFinalList);
					//formobj.setRetroDupList(retroDupList);
				}
				if(formobj.getNo_Insurer()!=null && Integer.parseInt(formobj.getNo_Insurer())==0){
					formobj.setRetroDupYerar(formobj.getUwYear());
					List<Map<String,Object>> list = getRetroContractDetailsList(formobj,3,"");
					for (int i=0;i<list.size();i++){
						Map<String,Object> map = list.get(i);
						formobj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
					}
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("showSecondPageData() || Exit");
		return true;
	}

	public boolean showSecondPageData1(String proposal,RiskDetailsBean beanObj,String pid) {
		logger.info("showSecondPageData1() || Enter");
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
				if(resMap!=null){
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
			if(StringUtils.isNotBlank(beanObj.getNo_Insurer()) && Integer.parseInt(beanObj.getNo_Insurer())>0 && (beanObj.getRetroFinalList()==null || beanObj.getRetroFinalList().size()==0)){

				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
				List<String> retroPercentage=new ArrayList<String>();
				List<String> UWYear=new ArrayList<String>();
				for(int i=0;i<Integer.parseInt(beanObj.getNo_Insurer());i++){
					retroFinalList.add(getRetroContractDetailsList(beanObj,2,""));
					//retroDupList.add(getRetroContractDetailsList(beanObj,3));
				}
				beanObj.setPercentRetro(retroPercentage);
				beanObj.setRetroYear(UWYear);
				beanObj.setRetroFinalList(retroFinalList);
				//beanObj.setRetroDupList(retroDupList);
			}
			if(StringUtils.isNotBlank(beanObj.getNo_Insurer())){
				for (int z=0;z<Integer.parseInt(beanObj.getNo_Insurer());z++){
					/*if("1".equalsIgnoreCase(beanObj.getNo_Insurer())){
						retroPercentage.add("100");
						UWYear.add(beanObj.getUwYear());
						retroDupList.add(getRetroContractDetailsList(beanObj,3));
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
					for (int i=0;i<list.size();i++){
						Map<String,Object> map = list.get(i);
						beanObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
					}
				}
			}
			if(beanObj.getNo_Insurer()!=null &&Integer.parseInt(beanObj.getNo_Insurer())==0){
				beanObj.setRetroDupYerar(beanObj.getUwYear());
				List<Map<String,Object>> list = getRetroContractDetailsList(beanObj,3,"");
				for (int i=0;i<list.size();i++){
					Map<String,Object> map = list.get(i);
					beanObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				}
			}
			GetRemarksDetails(beanObj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("showSecondPageData1() || Exit");
		return true;
	}

	public boolean showLayerBrokerage(String layerProposalNo,RiskDetailsBean  formobj) {
		logger.info("showLayerBrokerage() || Enter");
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
		logger.info("showLayerBrokerage() || Exit");
		return false;
	}

	public int getEditMode(String proposalNo){
		logger.info("getEditMode() || Enter");
		int mode=0;
		try {
			String selectQry = getQuery("RISK_SELECT_GETRSKCONTRACTNO");
			logger.info("select Query==>" + selectQry);
			logger.info("Args[0]=>"+proposalNo);
			String string = (String) this.mytemplate.queryForObject(selectQry,new Object[] {proposalNo},String.class);
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
		logger.info("getEditMode() || Exit");
		return mode;
	}

	private boolean checkEditSaveModeMethod(final RiskDetailsBean beanObj) {
		logger.info("checkEditSaveModeMethod() || Enter");
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

		}
		logger.info("checkEditSaveModeMethod() || Exit");
		return editSaveMode;
	}

	public boolean firstInsert(final RiskDetailsBean beanObj, final String pid, boolean saveFlag, final boolean amendId)  {
		logger.info("firstInsert() || Enter");
		boolean savFlg = false,ChkSavFlg = false;
		try {
			String sql = "";
			Object[] args=null;
			if (saveFlag) {
				ChkSavFlg = checkEditSaveModeMethod(beanObj);
				if (ChkSavFlg){
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,getMaxAmednId(beanObj.getProposal_no()));
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
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
				}
				else {
					args = getFirstPageEditSaveModeAruguments(beanObj, pid,maxAmendID);
					sql = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
					logger.info("Query=>"+sql);
					int updateCount = this.mytemplate.update(sql, args);
					logger.info("Result=>"+updateCount);
					args[1]=(Integer.parseInt((String)args[30])+1)+"";
				}
			}
			savFlg = insertRiskProposal(beanObj,saveFlag,pid,ChkSavFlg,amendId,(String)args[1]);
			
			InsertRemarkDetails(beanObj);
			insertCedentRetention(beanObj,pid);
			//savFlg = true;
		} catch (Exception e) {
			saveFlag = false;
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("firstInsert() || Exit");
		return savFlg;
	}

	public String getMaxProposanlno(String pid,String branchCode, String deptId) {
		logger.info("getMaxProposanlno() || Enter");
		String result="";
		try{
			//if("06".equalsIgnoreCase(branchCode)){
				result=new DropDownControllor().getSequence("Proposal",pid,deptId, branchCode,"","");
			/*}else
			result=new DropDownControllor().getPolicyNo("1",pid,branchCode);*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("getMaxProposanlno() || Exit");
		return result;
	}

	private String getRenewalStatus(final RiskDetailsBean beanObj) {
		logger.info("getRenewalStatus() || Enter");
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

		}
		logger.info("getRenewalStatus() || Exit");
		return result;
	}

	private String getMaxAmednId(final String proposalNo) {
		logger.info("getMaxAmednId() || Enter");
		String result ="";
		try{
			String query=getQuery(DBConstants.RISK_SELECT_GETMAXENDORSENO);
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+proposalNo);
			result = this.mytemplate.queryForObject(query, new String[]{proposalNo}, String.class).toString();
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		logger.info("getMaxAmednId() || Exit");
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
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
					logger.info("updateQry=>" + updateQry);
					if (this.mytemplate.update(updateQry, obj) > 0) {
						if (beanObj.getProStatus().equalsIgnoreCase("A")|| beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
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
					}
				} else {
					obj = getFirstPageSecondTableAruguments(beanObj, pid, amednIdvalue, amendId);
					insertQry = getQuery(DBConstants.RISK_INSERT_PRO24RSKPROPOSAL);
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
					if (beanObj.getProStatus().equalsIgnoreCase("A")|| beanObj.getProStatus().equalsIgnoreCase("P")||"0".equalsIgnoreCase(beanObj.getProStatus())) {
						beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
					}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
						beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
					}else if(beanObj.getProStatus().equalsIgnoreCase("R")){
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
					}
				}
			} else {
				if (!ChekmodeFlag) {
					if(maxAmendId.equalsIgnoreCase(beanObj.getAmendId())){
						insertQry = getQuery(DBConstants.RISK_INSERT_PRO24RSKPROPOSAL);
						logger.info("Insert Qry=>" + insertQry);
						obj = getFirstPageSecondTableInsertAruguments(beanObj,pid, amednIdvalue, amendId);
					}else{
						insertQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
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
			
		} catch (Exception e) {
			InsertFlag = false;
			logger.debug("Exception @ {" + e + "}");

		}
		return InsertFlag;
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
			if("Y".equalsIgnoreCase(beanObj.getPml())){
			obj[13] = StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0" : beanObj.getPmlPercent();
			
			obj[14] = StringUtils.isEmpty(beanObj.getEgnpipml()) ? "": beanObj.getEgnpipml();
			obj[15] = StringUtils.isEmpty(beanObj.getEgnpipml())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getEgnpipml(), beanObj.getExchRate());
			obj[16] = StringUtils.isEmpty(beanObj.getEgnpipmlOurShare()) ? "0" : beanObj.getEgnpipmlOurShare();
			}
			else{
				obj[13]="";
				obj[14]="";
				obj[15]="";
				obj[16]="";
			}
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
			List<Map<String, Object>> res = this.mytemplate.queryForList(GetRiskDetailsEditQuery(ContractMode),args);
			logger.info("List<Map<String, Object>> Size=>"+res.size());
			Map<String, Object> resMap = null;
			if(res!=null && res.size()>0)
				resMap = (Map<String, Object>)res.get(0);
			if (resMap!=null) {
				beanObj.setContractListVal(resMap.get("DATA_MAP_CONT_NO")==null?"":resMap.get("DATA_MAP_CONT_NO").toString());
				beanObj.setProposal_no(resMap.get("RSK_PROPOSAL_NUMBER")==null?"":resMap.get("RSK_PROPOSAL_NUMBER").toString());
				beanObj.setBaseLayer(resMap.get("BASE_LAYER")==null?"":resMap.get("BASE_LAYER").toString());
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
				beanObj.setBouquetModeYN(resMap.get("Bouquet_Mode_YN")==null?"":resMap.get("Bouquet_Mode_YN").toString());
				beanObj.setBouquetNo(resMap.get("Bouquet_No")==null?"":resMap.get("Bouquet_No").toString());
				beanObj.setUnderwriter(resMap.get("RSK_UNDERWRITTER")==null?"":resMap.get("RSK_UNDERWRITTER").toString());
				beanObj.setIncepDate(resMap.get("RSK_INCEPTION_DATE")==null?"":resMap.get("RSK_INCEPTION_DATE").toString());
				beanObj.setExpDate(resMap.get("RSK_EXPIRY_DATE")==null?"":resMap.get("RSK_EXPIRY_DATE").toString());
				beanObj.setAccDate(resMap.get("RSK_ACCOUNT_DATE")==null?"":resMap.get("RSK_ACCOUNT_DATE").toString());
				beanObj.setOrginalCurrency(resMap.get("RSK_ORIGINAL_CURR")==null?"":resMap.get("RSK_ORIGINAL_CURR").toString());
				beanObj.setExchRate(resMap.get("RSK_EXCHANGE_RATE")==null?"":resMap.get("RSK_EXCHANGE_RATE").toString().equalsIgnoreCase("0") ? "0"	: resMap.get("RSK_EXCHANGE_RATE")==null?"":resMap.get("RSK_EXCHANGE_RATE").toString());
				beanObj.setBasis(resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_BASIS")==null?"":resMap.get("RSK_BASIS").toString());
				beanObj.setPnoc(resMap.get("RSK_PERIOD_OF_NOTICE")==null?"":resMap.get("RSK_PERIOD_OF_NOTICE").toString());
				beanObj.setRiskCovered(resMap.get("RSK_RISK_COVERED")==null?"":resMap.get("RSK_RISK_COVERED").toString());
				beanObj.setTerritoryscope(resMap.get("RSK_TERRITORY_SCOPE")==null?"":resMap.get("RSK_TERRITORY_SCOPE").toString());
				beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString()); //24
				beanObj.setProStatus(resMap.get("RSK_STATUS")==null?"":resMap.get("RSK_STATUS").toString());
				
				beanObj.setEpi_origCur(resMap.get("RSK_EPI_OFFER_OC")==null?"":resMap.get("RSK_EPI_OFFER_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_OFFER_OC").toString()==null?"":resMap.get("RSK_EPI_OFFER_OC").toString());
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED")==null ? "" : resMap.get("RSK_PERILS_COVERED").toString());
				if(beanObj.getProduct_id().equalsIgnoreCase("2")){
					beanObj.setOurEstimate(resMap.get("RSK_EPI_ESTIMATE")==null?"":resMap.get("RSK_EPI_ESTIMATE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_EPI_ESTIMATE").toString()==null?"":resMap.get("RSK_EPI_ESTIMATE").toString());
				}
				if(beanObj.getProduct_id().equalsIgnoreCase("2")){
					beanObj.setEpi(resMap.get("RSK_EPI_EST_OC")==null?"":resMap.get("RSK_EPI_EST_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_EPI_EST_OC").toString()==null?"":resMap.get("RSK_EPI_EST_OC").toString());
				}
				beanObj.setXlCost(resMap.get("RSK_XLCOST_OC")==null?"":resMap.get("RSK_XLCOST_OC").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_XLCOST_OC").toString()==null?"":resMap.get("RSK_XLCOST_OC").toString());
				if(beanObj.getProduct_id().equalsIgnoreCase("2")){
					beanObj.setCedReten(resMap.get("RSK_CEDANT_RETENTION")==null?"":resMap.get("RSK_CEDANT_RETENTION").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_CEDANT_RETENTION").toString()==null?"":resMap.get("RSK_CEDANT_RETENTION").toString());
				}
				beanObj.setShareWritt(resMap.get("RSK_SHARE_WRITTEN")==null?"":resMap.get("RSK_SHARE_WRITTEN").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_SHARE_WRITTEN").toString()==null?"":resMap.get("RSK_SHARE_WRITTEN").toString());
				beanObj.setSharSign(resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString().equalsIgnoreCase("0") ? "": resMap.get("RSK_SHARE_SIGNED")==null?"":resMap.get("RSK_SHARE_SIGNED").toString());
				beanObj.setProposalType(resMap.get("RSK_PROPOSAL_TYPE")==null?"":resMap.get("RSK_PROPOSAL_TYPE").toString());
				beanObj.setAccountingPeriod(resMap.get("RSK_ACCOUNTING_PERIOD")==null?"":resMap.get("RSK_ACCOUNTING_PERIOD").toString());
				beanObj.setReceiptofStatements(resMap.get("RSK_RECEIPT_STATEMENT")==null?"":resMap.get("RSK_RECEIPT_STATEMENT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_RECEIPT_STATEMENT")==null?"":resMap.get("RSK_RECEIPT_STATEMENT").toString());
				beanObj.setReceiptofPayment(resMap.get("RSK_RECEIPT_PAYEMENT")==null?"":resMap.get("RSK_RECEIPT_PAYEMENT").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_RECEIPT_PAYEMENT").toString()==null?"":resMap.get("RSK_RECEIPT_PAYEMENT").toString());
				if("2".equalsIgnoreCase(beanObj.getProduct_id())){
					beanObj.setCedRetenType(resMap.get("RSK_CEDRET_TYPE")==null?"":resMap.get("RSK_CEDRET_TYPE").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_CEDRET_TYPE")==null?"":resMap.get("RSK_CEDRET_TYPE").toString());
					beanObj.setSpRetro(resMap.get("RSK_SP_RETRO")==null?"":resMap.get("RSK_SP_RETRO").toString()==null ? "0" : resMap.get("RSK_SP_RETRO").toString()==null?"":resMap.get("RSK_SP_RETRO").toString());
					beanObj.setNo_Insurer(resMap.get("RSK_NO_OF_INSURERS")==null?"":resMap.get("RSK_NO_OF_INSURERS").toString());
					beanObj.setLimitPerVesselOC(resMap.get("LIMIT_PER_VESSEL_OC")==null||"0".equals(resMap.get("LIMIT_PER_clasimVESSEL_OC"))?"":resMap.get("LIMIT_PER_VESSEL_OC").toString());
					beanObj.setLimitPerLocationOC((resMap.get("LIMIT_PER_LOCATION_OC")==null||"0".equals(resMap.get("LIMIT_PER_LOCATION_OC"))?"":resMap.get("LIMIT_PER_LOCATION_OC").toString()));
					beanObj.setCountryIncludedList(resMap.get("COUNTRIES_INCLUDE")==null?"":resMap.get("COUNTRIES_INCLUDE").toString());
					beanObj.setCountryExcludedList(resMap.get("COUNTRIES_EXCLUDE")==null?"":resMap.get("COUNTRIES_EXCLUDE").toString());
					beanObj.setTreatynoofLine(resMap.get("RSK_NO_OF_LINE")==null?"0":resMap.get("RSK_NO_OF_LINE").toString());
					beanObj.setLimitOrigCurPml(resMap.get("RSK_TRTY_LMT_PML_OC")==null?"0":resMap.get("RSK_TRTY_LMT_PML_OC").toString());
					beanObj.setTreatyLimitsurplusOCPml(resMap.get("RSK_TRTY_LMT_SUR_PML_OC")==null?"":resMap.get("RSK_TRTY_LMT_SUR_PML_OC").toString());
					beanObj.setEpipml(resMap.get("RSK_TRTY_LMT_OURASS_PML_OC")==null?"":resMap.get("RSK_TRTY_LMT_OURASS_PML_OC").toString());
				}
				
				beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
				beanObj.setPml(resMap.get("RSK_PML")==null ? "" : resMap.get("RSK_PML").toString());
				beanObj.setPmlPercent(resMap.get("RSK_PML_PERCENT")==null ? "" : resMap.get("RSK_PML_PERCENT").toString());
				beanObj.setMaxLimit_Product(resMap.get("RSK_MAX_LMT_COVER")==null?"":resMap.get("RSK_MAX_LMT_COVER").toString()==null ? "0" : resMap.get("RSK_MAX_LMT_COVER").toString()==null?"":resMap.get("RSK_MAX_LMT_COVER").toString());
				beanObj.setRenewal_contract_no(resMap.get("OLD_CONTRACTNO")==null?"":resMap.get("OLD_CONTRACTNO").toString());
				beanObj.setBaseLoginID(resMap.get("LOGIN_ID")==null?"":resMap.get("LOGIN_ID").toString());
				beanObj.setTreatyLimitsurplusOC(resMap.get("RSK_TREATY_SURP_LIMIT_OC")==null?"":resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString()==null?"":resMap.get("RSK_TREATY_SURP_LIMIT_OC").toString());
				beanObj.setInwardType(resMap.get("INWARD_BUS_TYPE")==null?"":resMap.get("INWARD_BUS_TYPE").toString());
				beanObj.setTreatyType(resMap.get("TREATYTYPE")==null?"":resMap.get("TREATYTYPE").toString());
				beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED")==null?"":resMap.get("RSK_LOC_ISSUED").toString());
				beanObj.setRunoffYear(resMap.get("RSK_RUN_OFF_YEAR")==null?"":resMap.get("RSK_RUN_OFF_YEAR").toString());
				beanObj.setLocBankName(resMap.get("RSK_LOC_BNK_NAME")==null?"":resMap.get("RSK_LOC_BNK_NAME").toString());
				beanObj.setLocCreditPrd(resMap.get("RSK_LOC_CRDT_PRD")==null?"":resMap.get("RSK_LOC_CRDT_PRD").toString());
				beanObj.setLocCreditAmt(resMap.get("RSK_LOC_CRDT_AMT")==null?"":DropDownControllor.formatter(resMap.get("RSK_LOC_CRDT_AMT").toString()));
				beanObj.setLocBeneficerName(resMap.get("RSK_LOC_BENFCRE_NAME")==null?"":resMap.get("RSK_LOC_BENFCRE_NAME").toString());
				beanObj.setRetentionYN(resMap.get("RETENTIONYN")==null?"":resMap.get("RETENTIONYN").toString());
				if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
					beanObj.setFaclimitOrigCur(DropDownControllor.formatter(resMap.get("RSK_LIMIT_OC")==null?"0":resMap.get("RSK_LIMIT_OC").toString()));
				}
				else{
				beanObj.setLimitOrigCur(resMap.get("RSK_LIMIT_OC")==null?"0":resMap.get("RSK_LIMIT_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LIMIT_OC").toString()==null?"":resMap.get("RSK_LIMIT_OC").toString());
				}
				saveFlag = true;
			}
			if(StringUtils.isNotBlank(beanObj.getContNo())&&!"0".equals(beanObj.getContNo())){
				beanObj.setPrclFlag(new DropDownControllor().getPLCLCountStatus(beanObj.getContNo(), "0"));
			}else{
				beanObj.setPrclFlag(false);
			}
			beanObj.setRiskdetailYN(resMap.get("RISK_DET_YN")==null?"":resMap.get("RISK_DET_YN").toString());
			beanObj.setBrokerdetYN(resMap.get("BROKER_DET_YN")==null?"":resMap.get("BROKER_DET_YN").toString());
			beanObj.setCoverdetYN(resMap.get("COVER_DET_YN")==null?"":resMap.get("COVER_DET_YN").toString());
			beanObj.setPremiumdetailYN(resMap.get("PREMIUM_DET_YN")==null?"":resMap.get("PREMIUM_DET_YN").toString());
			beanObj.setAcqdetailYN(resMap.get("ACQCOST_DET_YN")==null?"":resMap.get("ACQCOST_DET_YN").toString());
			beanObj.setCommissiondetailYN(resMap.get("COMM_DET_YN")==null?"":resMap.get("COMM_DET_YN").toString());
			beanObj.setDepositdetailYN(resMap.get("DEPOSIT_DET_YN")==null?"":resMap.get("DEPOSIT_DET_YN").toString());
			beanObj.setLossdetailYN(resMap.get("LOSS_DET_YN")==null?"":resMap.get("LOSS_DET_YN").toString());
			beanObj.setDocdetailYN(resMap.get("DOC_DET_YN")==null?"":resMap.get("DOC_DET_YN").toString());
			beanObj.setPaymentPartner(resMap.get("PAYMENT_PARTNER")==null?"":resMap.get("PAYMENT_PARTNER").toString());
			beanObj.setSectionNo(resMap.get("SECTION_NO")==null?"":resMap.get("SECTION_NO").toString());
			beanObj.setQuotesharePercent(resMap.get("QUOTESHARE_PERCENT")==null?"":resMap.get("QUOTESHARE_PERCENT").toString());
			beanObj.setAccountingPeriodNotes(resMap.get("RSK_ACCOUNT_PERIOD_NOTICE")==null?"":resMap.get("RSK_ACCOUNT_PERIOD_NOTICE").toString());
			beanObj.setStatementConfirm(resMap.get("RSK_STATEMENT_CONFIRM")==null?"":resMap.get("RSK_STATEMENT_CONFIRM").toString());
			GetRemarksDetails(beanObj);
			getGetRetDetails(beanObj);
			beanObj.setAmendId(new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no()));
			String proposalno="";
			if (StringUtils.isNotEmpty(beanObj.getLayerProposalNo())) {
				proposalno = beanObj.getLayerProposalNo();
			} else {
				proposalno = beanObj.getProposal_no();
			}
			this.showSecondpageEditItems(beanObj, beanObj.getProduct_id(), proposalno);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

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

	public boolean updateProportionalTreatyDao(final RiskDetailsBean beanObj,final String pid){
		boolean savFlg = false;
		try {
			String updateQry = "";
			Object[] args = getFirstPageSaveModeAruguments(beanObj, pid,getMaxAmednId(beanObj.getProposal_no()));
			updateQry = getQuery(DBConstants.RISK_UPDATE_RSKDTLS);
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
				this.updateRiskProposal(beanObj, pid);
				InsertRemarkDetails(beanObj);
				insertCedentRetention(beanObj,pid);
				UpadateUWShare(beanObj);
				//this.showSecondpageEditItems(beanObj, pid, proposalno);
				GetRemarksDetails(beanObj);
				//new DropDownControllor().getSOATableInsert(beanObj.getProposal_no(), beanObj.getContractno(),beanObj.getBranchCode());
				savFlg = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return savFlg;
	}

	private void UpadateUWShare(RiskDetailsBean beanObj) {
		String query="";
		try {
			query=getQuery("UPDATE_UW_SHARE");
			this.mytemplate.update(query,new Object[]{beanObj.getSharSign(),beanObj.getProposal_no(),beanObj.getProposal_no()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public String GetRiskDetailsEditQuery(boolean contractMode) {

		String query = "";
		query = getQuery(DBConstants.RISK_SELECT_GETEDITMODEDATA);
		if(contractMode){
			query = " "+query +" "+ getQuery(DBConstants.RISK_SELECT_GETEDITMODECONTCOND);
		}
		else {
			query = " "+query +" "+ getQuery(DBConstants.RISK_SELECT_GETEDITMODEPROCOND);
		}
		logger.info("Query=>" + query);
		return query.toString();
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
			updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24FIRPAGERSKPRO);
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
			//logger.info("Result=>" + res);
			if (res > 0) {
				saveFlag = true;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return saveFlag;
	}

	public String getNextContractNumber()  {
		String result="";
		try{
			String selectQry = getQuery(DBConstants.RISK_SELECT_GETNEXTRSKPRONO);
			logger.info("selectQry " + selectQry);
			result = this.mytemplate.queryForObject(selectQry,String.class).toString();
			logger.info("Result=>" + result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		return result;
	}

	public boolean checkProductMatch(final String pid, final String proposal,final boolean contarctMode) {
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

		}
		return saveFlag;
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

	private boolean showSecondpageEditItems(RiskDetailsBean beanObj,final String pid, final String proposalNo){
		boolean savFlg = false;
		try{
			String selectQry="";
			Object[] args = new Object[3];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = proposalNo;
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
					beanObj.setTax(resMap.get("RSK_TAX").toString().equalsIgnoreCase("0") ? "0": resMap.get("RSK_TAX").toString());
				}
				if (resMap.get("RSK_ACQUISTION_COST_OC") != null) {
					beanObj.setAcquisition_Cost(resMap.get("RSK_ACQUISTION_COST_OC").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_ACQUISTION_COST_OC").toString());
				}
				if (resMap.get("RSK_PROFIT_COMM") != null) {
					beanObj.setShare_Profit_Commission(resMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_PROFIT_COMM").toString());
				}
				/*if (resMap.get("RSK_MANAGEMENT_EXPENSES") != null) {
					beanObj.setManagement_Expenses(resMap.get("RSK_MANAGEMENT_EXPENSES").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_MANAGEMENT_EXPENSES").toString());
				}
				if (resMap.get("RSK_LOSS_CARRYFORWARD") != null) {
					beanObj.setLossC_F(resMap.get("RSK_LOSS_CARRYFORWARD").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_LOSS_CARRYFORWARD").toString());
				}*/
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
				beanObj.setCrestaStatus(resMap.get("RSK_CREASTA_STATUS")==null?"":resMap.get("RSK_CREASTA_STATUS").toString());
				beanObj.setEvent_limit(resMap.get("RSK_EVENT_LIMIT_OC")==null?"0":resMap.get("RSK_EVENT_LIMIT_OC").toString());
				beanObj.setAggregate_Limit(resMap.get("RSK_AGGREGATE_LIMIT_OC")==null?"0":resMap.get("RSK_AGGREGATE_LIMIT_OC").toString());
				beanObj.setOccurrent_Limit(resMap.get("RSK_OCCURRENT_LIMIT_OC")==null?"0":resMap.get("RSK_OCCURRENT_LIMIT_OC").toString());
				beanObj.setExclusion(resMap.get("RSK_EXCLUSION")==null?"":resMap.get("RSK_EXCLUSION").toString());
				beanObj.setRemarks(resMap.get("RSK_REMARKS")==null?"":resMap.get("RSK_REMARKS").toString());
				beanObj.setUnderwriter_Recommendations(resMap.get("RSK_UW_RECOMM")==null?"":resMap.get("RSK_UW_RECOMM").toString());
				beanObj.setGms_Approval(resMap.get("RSK_GM_APPROVAL")==null?"":resMap.get("RSK_GM_APPROVAL").toString());
				//beanObj.setProfit_commission(resMap.get("RSK_SHARE_PROFIT_COMMISSION")==null?"":resMap.get("RSK_SHARE_PROFIT_COMMISSION").toString());
				beanObj.setSlideScaleCommission(resMap.get("RSK_SLADSCALE_COMM")==null?"":resMap.get("RSK_SLADSCALE_COMM").toString());
				beanObj.setLossParticipants(resMap.get("RSK_LOSS_PART_CARRIDOR")==null?"":resMap.get("RSK_LOSS_PART_CARRIDOR").toString());
				beanObj.setCommissionSubClass(resMap.get("RSK_COMBIN_SUB_CLASS")==null?"":resMap.get("RSK_COMBIN_SUB_CLASS").toString());
				beanObj.setLeader_Underwriter_country(resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":resMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
				beanObj.setOrginalacqcost(resMap.get("RSK_INCLUDE_ACQ_COST")==null?"":resMap.get("RSK_INCLUDE_ACQ_COST").toString());
				beanObj.setOurassessmentorginalacqcost(resMap.get("RSK_OUR_ASS_ACQ_COST")==null?"":resMap.get("RSK_OUR_ASS_ACQ_COST").toString());
				beanObj.setOuracqCost(resMap.get("RSK_OUR_ACQ_OUR_SHARE_OC")==null?"":resMap.get("RSK_OUR_ACQ_OUR_SHARE_OC").toString());
				beanObj.setProfitCommission(resMap.get("RSK_PRO_NOTES")==null?"":resMap.get("RSK_PRO_NOTES").toString());
				beanObj.setLosscommissionSubClass(resMap.get("RSK_LOSS_COMBIN_SUB_CLASS")==null?"":resMap.get("RSK_LOSS_COMBIN_SUB_CLASS").toString());
				beanObj.setSlidecommissionSubClass(resMap.get("RSK_SLIDE_COMBIN_SUB_CLASS")==null?"":resMap.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString());
				beanObj.setCrestacommissionSubClass(resMap.get("RSK_CRESTA_COMBIN_SUB_CLASS")==null?"":resMap.get("RSK_CRESTA_COMBIN_SUB_CLASS").toString());
				beanObj.setManagementExpenses(resMap.get("RSK_PRO_MANAGEMENT_EXP")==null?"":resMap.get("RSK_PRO_MANAGEMENT_EXP").toString());
				beanObj.setCommissionType(resMap.get("RSK_PRO_COMM_TYPE")==null?"":resMap.get("RSK_PRO_COMM_TYPE").toString());
				beanObj.setProfitCommissionPer(resMap.get("RSK_PRO_COMM_PER")==null?"":DropDownControllor.formatterpercentage(resMap.get("RSK_PRO_COMM_PER").toString()));
				beanObj.setSetup(resMap.get("RSK_PRO_SET_UP")==null?"":resMap.get("RSK_PRO_SET_UP").toString());
				beanObj.setSuperProfitCommission(resMap.get("RSK_PRO_SUP_PRO_COM")==null?"":resMap.get("RSK_PRO_SUP_PRO_COM").toString());
				beanObj.setLossCarried(resMap.get("RSK_PRO_LOSS_CARY_TYPE")==null?"":resMap.get("RSK_PRO_LOSS_CARY_TYPE").toString());
				beanObj.setLossyear(resMap.get("RSK_PRO_LOSS_CARY_YEAR")==null?"":resMap.get("RSK_PRO_LOSS_CARY_YEAR").toString());
				beanObj.setProfitCarried(resMap.get("RSK_PRO_PROFIT_CARY_TYPE")==null?"":resMap.get("RSK_PRO_PROFIT_CARY_TYPE").toString());
				beanObj.setProfitCarriedForYear(resMap.get("RSK_PRO_PROFIT_CARY_YEAR")==null?"":resMap.get("RSK_PRO_PROFIT_CARY_YEAR").toString());
				beanObj.setFistpc(resMap.get("RSK_PRO_FIRST_PFO_COM")==null?"":resMap.get("RSK_PRO_FIRST_PFO_COM").toString());
				beanObj.setProfitMont(resMap.get("RSK_PRO_FIRST_PFO_COM_PRD")==null?"":resMap.get("RSK_PRO_FIRST_PFO_COM_PRD").toString());
				beanObj.setSubProfitMonth(resMap.get("RSK_PRO_SUB_PFO_COM_PRD")==null?"":resMap.get("RSK_PRO_SUB_PFO_COM_PRD").toString());
				beanObj.setSubpc(resMap.get("RSK_PRO_SUB_PFO_COM")==null?"":resMap.get("RSK_PRO_SUB_PFO_COM").toString());
				beanObj.setSubSeqCalculation(resMap.get("RSK_PRO_SUB_SEQ_CAL")==null?"":resMap.get("RSK_PRO_SUB_SEQ_CAL").toString());
				beanObj.setLocRate(resMap.get("RSK_RATE")==null?"":resMap.get("RSK_RATE").toString());
				beanObj.setPremiumResType(resMap.get("RSK_PREMIUM_RES_TYPE")==null?"":resMap.get("RSK_PREMIUM_RES_TYPE").toString());
				beanObj.setPortfolioType(resMap.get("RSK_PORTFOLIO_TYPE")==null?"":resMap.get("RSK_PORTFOLIO_TYPE").toString());
				beanObj.setPcfpcType(resMap.get("FPC_TYPE")==null?"":resMap.get("FPC_TYPE").toString());
				beanObj.setPcfixedDate(resMap.get("FPC_FIXED_DATE")==null?"":resMap.get("FPC_FIXED_DATE").toString());
				if (resMap.get("RSK_OTHER_COST") != null) {
					beanObj.setOthercost(resMap.get("RSK_OTHER_COST").toString().equalsIgnoreCase("0") ? "0" : resMap.get("RSK_OTHER_COST").toString());
				}else{
					beanObj.setOthercost("0");
				}
				beanObj.setAcqCostPer((Double.parseDouble(beanObj.getCommissionQ_S())+Double.parseDouble(beanObj.getCommission_surp())+Double.parseDouble(beanObj.getOverRidder())+Double.parseDouble(beanObj.getBrokerage())+Double.parseDouble(beanObj.getTax())+Double.parseDouble(beanObj.getOthercost()))+"");
				savFlg = true;
			}
			args = new String[1];
			//args[0] = beanObj.getProposal_no();
			args[0] =proposalNo;
			selectQry = getQuery(DBConstants.RISK_SELECT_GETQUOTASHARE);
			logger.info("Select Query" + selectQry);
			logger.info("args[0]=>"+ args[0]);
			List<Map<String, Object>> res1 = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" + res1.size());
			Map<String, Object> res1Map = null;
			if(res1!=null && res1.size()>0) {
				res1Map = (Map<String, Object>)res1.get(0);
				if(res1Map!=null) {
					beanObj.setPremiumQuotaShare(res1Map.get("RSK_PREMIUM_QUOTA_SHARE")==null?"":res1Map.get("RSK_PREMIUM_QUOTA_SHARE").toString());
					beanObj.setPremiumSurplus(res1Map.get("RSK_PREMIUM_SURPULS")==null?"":res1Map.get("RSK_PREMIUM_SURPULS").toString());
				}
			}
			
			selectQry = getQuery("RISK_SELECT_COMM_GETQUOTASHARE");
			logger.info("Select Query" + selectQry);
			logger.info("args[0]=>"+ args[0]);
			res1 = this.mytemplate.queryForList(selectQry,args);
			logger.info("List<Map<String, Object>> Size=>" + res1.size());
			if(res1!=null && res1.size()>0) {
				res1Map = (Map<String, Object>)res1.get(0);
				if(res1Map!=null) {
					beanObj.setCommissionQ_SAmt(res1Map.get("COMM_QS_AMT")==null?"":res1Map.get("COMM_QS_AMT").toString());
					beanObj.setCommission_surpAmt(res1Map.get("COMM_SURPLUS_AMT")==null?"":res1Map.get("COMM_SURPLUS_AMT").toString());
				}
			}
			for(int i=0;i<Integer.parseInt(beanObj.getNo_Insurer());i++){
				beanObj.setProduct_id("4");	
				beanObj.setRetroType("TR");
				beanObj.setBranchCode(beanObj.getBranchCode());
				beanObj.setIncepDate(beanObj.getIncepDate());
				beanObj.setRetroUwyear(getRetroContractDetailsList(beanObj,1,""));
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

		}
		return savFlg;
	}

	public int checkSecondPageMode(final RiskDetailsBean beanObj,final String productId) {
		int mode=0;
		try{
			final String query;
			query = getQuery(DBConstants.RISK_SELECT_GETRISKCOMMCOUNT);
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

	public boolean saveRiskDeatilsSecondForm(RiskDetailsBean beanObj,final String productId) {
		try {
			String updateQry = "",insertQry = "",selectQry="",endom="";
			Object[] obj=null,obj1=null;
			Object[] args=null;
			int out=0;
			int chkSecPageMode = checkSecondPageMode(beanObj, productId); //commission table count 0 mode=1 else 2
			int ContractEditMode = contractEditMode(beanObj, productId); // get contract no from risk details if empty mode=1 else 2
			if (ContractEditMode == 1) {
				if (chkSecPageMode == 1) {

					obj = secondPageFirstTableAruguments(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
					logger.info("updateQry=>"+ updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" + out);

					insertQry = getQuery(DBConstants.RISK_INSERT_PRO2SECCOMM);
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
							
							String query=getQuery("GET_BASE_LAYER_DETAILS");
							logger.info("selectQry " + selectQry);
							logger.info("Args[0]" + beanObj.getProposal_no());
							List<Map<String, Object>> res1 = this.mytemplate.queryForList(query,new Object[]{productId,beanObj.getBranchCode(),beanObj.getProposal_no()});
							logger.info("Result Size=>" +  res1.size());
							Map<String, Object> resMap1 = null;
							if(res1!=null && res1.size()>0)
								resMap1 = (Map<String, Object>)res1.get(0);
							if (resMap1 != null) {
									beanObj.setBaseLayerYN(resMap1.get("BASE_LAYER")==null?"":resMap1.get("BASE_LAYER").toString());
							}
							
							logger.info("Contract No=>"+ beanObj.getContractno() + "Layer No=>"+ beanObj.getLay());
							String prodid=productId;
							if ("layer".equalsIgnoreCase(beanObj.getLay())) {
								logger.info("Mode Layer");
								maxContarctNo = beanObj.getContractno();
							}
							else if(StringUtils.isNotBlank(beanObj.getBaseLayerYN())){
								query=getQuery("GET_BASE_LAYER_DETAILS");
								logger.info("selectQry " + query);
								logger.info("Args[0]" + beanObj.getProposal_no());
								res1 = this.mytemplate.queryForList(query,new Object[]{productId,beanObj.getBranchCode(),beanObj.getBaseLayerYN()});
								logger.info("Result Size=>" +  res1.size());
								resMap1 = null;
								if(res1!=null && res1.size()>0)
									resMap1 = (Map<String, Object>)res1.get(0);
								if (resMap1 != null) {
										beanObj.setContNo(resMap1.get("CONTRACT_NO")==null?"":resMap1.get("CONTRACT_NO").toString());
								}
								maxContarctNo=beanObj.getContNo();
							}
							
							else {
								logger.info("Mode Not a Layer");
								if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
									maxContarctNo=beanObj.getRenewal_contract_no();
								}else{
									//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
										maxContarctNo=new DropDownControllor().getSequence("Contract",prodid,beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
									/*}else
									maxContarctNo=new DropDownControllor().getPolicyNo("2",prodid,beanObj.getBranchCode());*/
									if("RI01".equalsIgnoreCase(beanObj.getSourceId())){
										insertSectionValue(beanObj,maxContarctNo);
									}
									logger.info("Result=>" + maxContarctNo);
								}
							}
							logger.info("Proposal Number=>"	+ beanObj.getProposal_no() + " Contract No=>"+ maxContarctNo);
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
							beanObj.setContNo(maxContarctNo);
							if("".equals(beanObj.getRenewal_contract_no())||"0".equals(beanObj.getRenewal_contract_no())||"NEWCONTNO".equals(beanObj.getRenewalFlag())){
								beanObj.setContractGendration("Your Proposal is converted to Contract with Proposal No : "+beanObj.getProposal_no() +" and Contract No : "+maxContarctNo+".");
							}else{
								beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+" and New Contract No : "+maxContarctNo+".");
							}
						} else {
							args = new String[4];
							args[0] = beanObj.getContNo();
							args[1] = getproposalStatus(beanObj
									.getProposal_no());
							args[2] = args[1];
							if (args != null) {

								if (((String) args[1]).equalsIgnoreCase("P")) {
									beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
								}	if (((String) args[1]).equalsIgnoreCase("N")) {
									beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
								}  else if (((String) args[1]).equalsIgnoreCase("A")) {
									beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+" and Contract No : "+beanObj.getContNo()+".");
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

				else if (chkSecPageMode == 2) {
					obj =updateRiskDetailsSecondForm(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
					logger.info("Update Query=>" + updateQry);
					out=this.mytemplate.update(updateQry,obj);
					logger.info("Result=>" +  out);
					obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
					updateQry = getQuery(DBConstants.RISK_UPDATE_PRO2SECCOMM);
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
						String query=getQuery("GET_BASE_LAYER_DETAILS");
						logger.info("selectQry " + selectQry);
						logger.info("Args[0]" + beanObj.getProposal_no());
						List<Map<String, Object>> res1 = this.mytemplate.queryForList(query,new Object[]{productId,beanObj.getBranchCode(),beanObj.getProposal_no()});
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
							res1 = this.mytemplate.queryForList(query,new Object[]{productId,beanObj.getBranchCode(),beanObj.getBaseLayerYN()});
							logger.info("Result Size=>" +  res1.size());
							resMap1 = null;
							if(res1!=null && res1.size()>0)
								resMap1 = (Map<String, Object>)res1.get(0);
							if (resMap1 != null) {
									beanObj.setContNo(resMap1.get("CONTRACT_NO")==null?"":resMap1.get("CONTRACT_NO").toString());
							}
							maxContarctNo=beanObj.getContNo();
							if("RI01".equalsIgnoreCase(beanObj.getSourceId())){
								insertSectionValue(beanObj,maxContarctNo); 
							}
						}else{
						if(!"".equals(beanObj.getRenewal_contract_no())&&!"0".equals(beanObj.getRenewal_contract_no())&&"OLDCONTNO".equals(beanObj.getRenewalFlag())){
							maxContarctNo=beanObj.getRenewal_contract_no(); 
							
						}else{
							//if("06".equalsIgnoreCase(beanObj.getBranchCode())){
								maxContarctNo=new DropDownControllor().getSequence("Contract",prodid,beanObj.getDepartmentId(), beanObj.getBranchCode(),beanObj.getProposal_no(),beanObj.getUwYear());
							/*}else
							maxContarctNo=new DropDownControllor().getPolicyNo("2",prodid,beanObj.getBranchCode());*/
						}
						if("RI01".equalsIgnoreCase(beanObj.getSourceId())){
							insertSectionValue(beanObj,maxContarctNo);
						}
						}
						//if(StringUtils.isNotBlank(maxContarctNo) && !"0".equalsIgnoreCase(maxContarctNo)){
						logger.info(" Proposal Number"+ beanObj.getProposal_no() + " Contract No"+ maxContarctNo);
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
						if(StringUtils.isNotBlank(beanObj.getBaseLayer())){
							beanObj.setContractGendration("A new proposal "+beanObj.getProposal_no()+" has been created under contract number "+beanObj.getContNo()+".");
						}
						else if("".equals(beanObj.getRenewal_contract_no())||"0".equals(beanObj.getRenewal_contract_no())||"NEWCONTNO".equals(beanObj.getRenewalFlag())){
							beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+" and Contract No : "+maxContarctNo+".");
						}else{
							beanObj.setContractGendration("Your Proposal is Renewaled with Proposal No : "+beanObj.getProposal_no() +", Old Contract No:"+maxContarctNo+" and New Contract No : "+maxContarctNo+".");
						}
						updateQry =getQuery(DBConstants.RISK_UPDATE_MNDINSTALLMENTS);
						logger.info("updateQry " + updateQry);
						logger.info("Args[0]=>" + maxContarctNo);
						logger.info("Args[1]=>" + beanObj.getProposal_no());
						out=this.mytemplate.update(updateQry,new Object[]{maxContarctNo,beanObj.getProposal_no()});
						logger.info("Result=>" +out);
						beanObj.setContNo(maxContarctNo);
					//}else{
						//beanObj.setContractGendration("Your  Proposal No : "+ beanObj.getProposal_no()+"not converting into contract because base proposal of this proposal "+beanObj.getBaseLayerYN()+"is not converted as contract");
					//}
					} else if (beanObj.getProStatus().matches("P")) {
						beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "+ beanObj.getProposal_no());
					}else if (beanObj.getProStatus().matches("N")) {
						if (productId.equalsIgnoreCase("2")||productId.equalsIgnoreCase("4")) {
							beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no());
						}
					}else if (beanObj.getProStatus().matches("R")) {
						beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no()+" and Layer No.:"+beanObj.getLayerNo()+".");
					}

				}
			}else if (ContractEditMode == 2) {

				endom=getQuery(DBConstants.RISK_SELECT_ENDO);
				logger.info("Query=>"+endom);
				logger.info("Args[0]=>"+beanObj.getProposal_no());
				String endtNo=(String)this.mytemplate.queryForObject(endom, new Object[]{beanObj.getProposal_no()}, String.class);
				logger.info("Result=>"+endtNo);

				obj = updateContractRiskDetailsSecondForm(beanObj, productId,endtNo);
				logger.info("Update Select Query========>"+endom);
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24CONTSECPAGE);
				logger.info("updateQry " + updateQry);
				out=this.mytemplate.update(updateQry, obj);
				logger.info("Result=>" +out);
					beanObj.setContractGendration("Your Contract is updated with Proposal No : "+beanObj.getProposal_no()+", Contract No : "+beanObj.getContNo()+".");
				/*insertQry = getQuery(DBConstants.RISK_INSERT_PRO2SECCOMM);
				logger.info("InsertQry=>" + insertQry);
				obj1 = secondContarctPageCommissionAruguments(beanObj,productId);
				out=this.mytemplate.update(insertQry, obj1);*/

				obj1 = updateRiskDetailsSecondFormSecondTable(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO2SECCOMM);
				logger.info("updateQry " + updateQry);
				out=this.mytemplate.update(updateQry, obj1);
				logger.info("Result=>" +  out);

				logger.info("Result=>" +out);
				beanObj.setProStatus("A");
			}
			insertRetroContracts(beanObj,productId);
			insertCrestaMaintable(beanObj);
			beanObj.setProduct_id(productId);
			insertBonusDetails(beanObj,"scale");
			insertBonusDetails(beanObj,"lossparticipate");
			insertProfitCommissionMain(beanObj,"main");
			InsertRemarkDetails(beanObj);
			updateRetentionContractNo(beanObj);
			DropDownControllor dropDownController = new DropDownControllor();
			dropDownController.updatepositionMasterEndtStatus(beanObj.getProposal_no(),productId,beanObj.getEndorsementDate(),beanObj.getCeaseStatus());
			if(StringUtils.isNotBlank(beanObj.getContNo())){
			new DropDownControllor().getSOATableInsert(beanObj.getProposal_no(), beanObj.getContNo(),beanObj.getBranchCode());
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return true;
	}

private void insertSectionValue(RiskDetailsBean bean, String maxContarctNo) {
		try{
			String query=getQuery("INSERT_SECTION_DETAILS");
			Object args[]= new Object[6];
			args[0] ="1";
			args[1] = maxContarctNo;
			args[2] = bean.getDepartmentId();
			args[3] = "Main Section";
			args[4] = bean.getBranchCode();
			args[5] = bean.getLoginId();
			logger.info("Query"+query);
			logger.info("Args"+args);
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

public void updateRetentionContractNo(RiskDetailsBean bean){
	try{
		String query=getQuery("GET_COUNT_RETENTION");
		Object args[] = new Object[2];
		args[0] = bean.getProposal_no();
		args[1] = bean.getProduct_id();
		int count = this.mytemplate.queryForInt(query,args);
		if(count>=1){
			query=getQuery("UPDATE_RETEN_CONTNO");
			 	args = new Object[4];
			 	args[0] = bean.getContNo();
			 	args[1] = bean.getDepartmentId();
				args[2] = bean.getProposal_no();
				args[3] = bean.getProduct_id();
				this.mytemplate.update(query,args);
		}
	}catch (Exception e) {
		logger.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
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

		}
		return result;
	}

	public boolean saveSecondMode(RiskDetailsBean beanObj, String productId)  {
		try{
			int ChkSecPagMod = checkSecondPageMode(beanObj, productId);
			//int ContractEditMode = contractEditMode(beanObj, productId);
			String updateQry = "",insertQry = "";
			Object[] obj=null,obj1=null;
			//if (ContractEditMode == 1) {
			if (ChkSecPagMod == 2) {
				obj = saveUpdateRiskDetailsSecondForm(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
				logger.info("Query=>" + updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>" + res);
				String GetProposalStatus = null;
				String query=getQuery(DBConstants.RISK_SELECT_MAXRSKSTATUS);
				logger.info("Query=>" + query);
				logger.info("Args[0]=>" + obj[16]);
				GetProposalStatus = this.mytemplate.queryForObject(query,new Object[]{obj[16]},String.class).toString();
				logger.info("Result=>" + GetProposalStatus);
				beanObj.setProStatus(GetProposalStatus);
				if(StringUtils.isNotBlank(beanObj.getContNo())) {
					beanObj.setContractGendration("Your Proposal is saved in Endorsement with Proposal No : "+ beanObj.getProposal_no());
				}
				else if ("A".equalsIgnoreCase(GetProposalStatus)||"P".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ obj[16]);
				}else if ("N".equalsIgnoreCase(GetProposalStatus)) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ obj[16]);
				}
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO2SECCOMM);
				obj1 = savemodeUpdateRiskDetailsSecondFormSecondTable(beanObj, productId, getMaxAmednId(beanObj.getProposal_no()));
				res=this.mytemplate.update(updateQry, obj1);
				logger.info("Result=>" + res);
			}else {
				obj = secondPageFirstTableSaveAruguments(beanObj, productId,getMaxAmednId(beanObj.getProposal_no()));
				updateQry = getQuery(DBConstants.RISK_UPDATE_PRO24RSKPROPOSAL);
				logger.info("updateQry" + updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>" + res);
				insertQry = getQuery(DBConstants.RISK_INSERT_PRO2SECCOMM);
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
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no());
				} else if (HomePosition.equalsIgnoreCase("A")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no() );
				} else if (HomePosition.equalsIgnoreCase("R")) {
					beanObj.setContractGendration("Your Proposal is saved in Rejected Stage with Proposal No : "+ beanObj.getProposal_no());
				}else if (HomePosition.equalsIgnoreCase("N")) {
					beanObj.setContractGendration("Your Proposal is saved in Not Taken Up Stage with Proposal No : "+ beanObj.getProposal_no() );
				} else if (HomePosition.equalsIgnoreCase("0")) {
					beanObj.setContractGendration("Your Proposal is saved in Pending Stage with Proposal No : "	+ beanObj.getProposal_no() );
				}
			}
			//}
			//insertRetroContracts(beanObj,productId);
			//insertCrestaMaintable(beanObj);
			beanObj.setProduct_id(productId);
			//insertBonusDetails(beanObj,"scale");
			//insertBonusDetails(beanObj,"lossparticipate");
			//insertProfitCommissionMain(beanObj,"main");
			//InsertRemarkDetails(beanObj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return true;
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
			String selectQry = getQuery("RISK_SELECT_GETCOMMONDATA_PTTY");
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
				beanObj.setInwardType(resMap.get("INWARD_BUS_TYPE")==null?"":resMap.get("INWARD_BUS_TYPE").toString());
				beanObj.setTreatyType(resMap.get("TREATYTYPE")==null?"":resMap.get("TREATYTYPE").toString());
				beanObj.setTreatyName(resMap.get("TREATYTYPE_NAME")==null?"":resMap.get("TREATYTYPE_NAME").toString());
				beanObj.setLOCIssued(resMap.get("RSK_LOC_ISSUED")==null?"":resMap.get("RSK_LOC_ISSUED").toString());
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED")==null ? "" : resMap.get("RSK_PERILS_COVERED").toString());
				if("0".equalsIgnoreCase(beanObj.getPerilCovered())){
					beanObj.setPerilCovered("None");
				}else{
				beanObj.setPerilCovered(resMap.get("RSK_PERILS_COVERED_Con")==null ? "" : resMap.get("RSK_PERILS_COVERED_Con").toString());
				}
				beanObj.setTreatynoofLine(resMap.get("RSK_NO_OF_LINE")==null?"0":resMap.get("RSK_NO_OF_LINE").toString());
				beanObj.setRetentionYN(resMap.get("RETENTIONYN")==null?"":resMap.get("RETENTIONYN").toString());
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
				beanObj.setTerritory(resMap.get("RSK_TERRITORY")==null?"":resMap.get("RSK_TERRITORY").toString());
				beanObj.setEndorsmenttype(resMap.get("RS_ENDORSEMENT_TYPE")==null?"":resMap.get("RS_ENDORSEMENT_TYPE").toString());
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
				if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
					beanObj.setFaclimitOrigCur(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OC")==null?"0":secViewDataMap.get("RSK_LIMIT_OC").toString()));
					beanObj.setFacLimitOrig_CurDc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_DC")==null?"":secViewDataMap.get("RSK_LIMIT_DC").toString()));
					beanObj.setLimitOrigCur("0.00");
					beanObj.setLimitOrig_CurDc("0.00");
				}
				else{
					beanObj.setFaclimitOrigCur("0.00");
					beanObj.setFacLimitOrig_CurDc("0.00");
					beanObj.setLimitOrigCur(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_OC")==null?"0":secViewDataMap.get("RSK_LIMIT_OC").toString()));
					beanObj.setLimitOrig_CurDc(DropDownControllor.formatter(secViewDataMap.get("RSK_LIMIT_DC")==null?"":secViewDataMap.get("RSK_LIMIT_DC").toString()));
				}
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
				//beanObj.setShareWritt(DropDownControllor.formatter(secViewDataMap.get("RSK_SHARE_WRITTEN")==null?"":secViewDataMap.get("RSK_SHARE_WRITTEN").toString()));
				//beanObj.setSharSign(DropDownControllor.formatter(secViewDataMap.get("RSK_SHARE_SIGNED")==null?"":secViewDataMap.get("RSK_SHARE_SIGNED").toString()));
				beanObj.setShareWritt(DropDownControllor.formatterpercentage(secViewDataMap.get("RSK_SHARE_WRITTEN")==null?"":secViewDataMap.get("RSK_SHARE_WRITTEN").toString()));
				beanObj.setSharSign(DropDownControllor.formatterpercentage(secViewDataMap.get("RSK_SHARE_SIGNED")==null?"":secViewDataMap.get("RSK_SHARE_SIGNED").toString()));
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
				beanObj.setTreatyLimitsurplusOC(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC")==null?"":secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OC").toString()));
				beanObj.setTreatyLimitsurplusDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC")==null?"":secViewDataMap.get("RSK_TREATY_SURP_LIMIT_DC").toString()));
				beanObj.setTreatyLimitsurplusOurShare(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OS_OC")==null?"":secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OS_OC").toString()));
				beanObj.setTreatyLimitsurplusOurShareDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OS_DC")==null?"":secViewDataMap.get("RSK_TREATY_SURP_LIMIT_OS_DC").toString()));
				beanObj.setPml(secViewDataMap.get("RSK_PML")==null ? "" : secViewDataMap.get("RSK_PML").toString());
				beanObj.setPmlPercent(secViewDataMap.get("RSK_PML_PERCENT")==null ? "" : secViewDataMap.get("RSK_PML_PERCENT").toString());
				beanObj.setLimitOrigCurPml(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_OC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_PML_OC").toString()));
				beanObj.setLimitOrigCurPmlDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_DC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_PML_DC").toString()));
				beanObj.setLimitOrigCurPmlOS(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_OS_OC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_PML_OS_OC").toString()));
				beanObj.setLimitOrigCurPmlOSDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_PML_OS_DC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_PML_OS_DC").toString()));
				beanObj.setTreatyLimitsurplusOCPml(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OC").toString()));
				beanObj.setTreatyLimitsurplusOCPmlDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_DC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_DC").toString()));
				beanObj.setTreatyLimitsurplusOCPmlOS(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OS_OC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OS_OC").toString()));
				beanObj.setTreatyLimitsurplusOCPmlOSDC(DropDownControllor.formatter(secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OS_DC")==null?"":secViewDataMap.get("RSK_TRTY_LMT_SUR_PML_OS_DC").toString()));
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
					beanObj.setShare_Profit_Commission(thirdViewDataMap.get("RSK_PROFIT_COMM")==null?"":thirdViewDataMap.get("RSK_PROFIT_COMM").toString());
					/*if (thirdViewDataMap.get("RSK_PROFIT_COMM") != null) {
						if (thirdViewDataMap.get("RSK_PROFIT_COMM").toString().equalsIgnoreCase("1")) {
							beanObj.setProfit_Comm("YES");
						} else {
							beanObj.setProfit_Comm("NO");
						}
					}*/
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
					beanObj.setAcquisition_CostOSOC(getShareVal(beanObj.getAcquisition_Cost().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setAcquisition_CostOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getAcquisition_CostOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setCommissionQ_S(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_COMM_QUOTASHARE")==null?"":thirdViewDataMap.get("RSK_COMM_QUOTASHARE").toString()));
					beanObj.setCommission_surp(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_COMM_SURPLUS")==null?"":thirdViewDataMap.get("RSK_COMM_SURPLUS").toString()));
					beanObj.setOverRidder(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_OVERRIDER_PERC")==null?"":thirdViewDataMap.get("RSK_OVERRIDER_PERC").toString()));
					//beanObj.setManagement_Expenses(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_MANAGEMENT_EXPENSES")==null?"":thirdViewDataMap.get("RSK_MANAGEMENT_EXPENSES").toString()));
					//beanObj.setLossC_F(thirdViewDataMap.get("RSK_LOSS_CARRYFORWARD")==null?"":thirdViewDataMap.get("RSK_LOSS_CARRYFORWARD").toString());
					beanObj.setPremium_Reserve(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_PREMIUM_RESERVE")==null?"":thirdViewDataMap.get("RSK_PREMIUM_RESERVE").toString()));
					beanObj.setLoss_reserve(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_LOSS_RESERVE")==null?"":thirdViewDataMap.get("RSK_LOSS_RESERVE").toString()));
					beanObj.setInterest(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_INTEREST")==null?"":thirdViewDataMap.get("RSK_INTEREST").toString()));
					beanObj.setPortfolio_inout_Premium(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_PF_INOUT_PREM")==null?"":thirdViewDataMap.get("RSK_PF_INOUT_PREM").toString()));
					beanObj.setPortfolio_inout_Loss(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_PF_INOUT_LOSS")==null?"":thirdViewDataMap.get("RSK_PF_INOUT_LOSS").toString()));
					beanObj.setLoss_Advise(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSSADVICE")==null?"":thirdViewDataMap.get("RSK_LOSSADVICE").toString()));
					beanObj.setLoss_Advise_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_LOSSADVICE_DC")==null?"":thirdViewDataMap.get("RSK_LOSSADVICE_DC").toString()));
					beanObj.setLoss_AdviseOSOC(getShareVal(beanObj.getLoss_Advise().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLoss_AdviseOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLoss_AdviseOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setCash_Loss_Limit(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_OC").toString()));
					beanObj.setCash_Loss_Limit_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC")==null?"":thirdViewDataMap.get("RSK_CASHLOSS_LMT_DC").toString()));
					beanObj.setCash_Loss_LimitOSOC(getShareVal(beanObj.getCash_Loss_Limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setCash_Loss_LimitOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getCash_Loss_LimitOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setAnualAggregateLiability(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIAB_OC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_LIAB_OC").toString()));
					beanObj.setAnualAggregateLiability_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC")==null?"":thirdViewDataMap.get("RSK_AGGREGATE_LIAB_DC").toString()));
					//beanObj.setCash_Loss_LimitOSOC(getShareVal(beanObj.getCash_Loss_Limit().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					//beanObj.setCash_Loss_LimitOSDC(getShareVal(beanObj.getCash_Loss_LimitOSOC().replaceAll(",",""),beanObj.getExchRate(),"exch"));
					beanObj.setReinstNo(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_NO")==null?"":thirdViewDataMap.get("RSK_REINSTATE_NO").toString()));
					beanObj.setReinstAditionalPremium_percent(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_OC").toString()));
					beanObj.setReinstAditionalPremium_percent_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_DC").toString()));
					beanObj.setLeader_Underwriter(thirdViewDataMap.get("RSK_LEAD_UWID")==null?"":thirdViewDataMap.get("RSK_LEAD_UWID").toString());
					if(!"64".equalsIgnoreCase(beanObj.getLeader_Underwriter())){
					beanObj.setLeader_Underwriter(thirdViewDataMap.get("RSK_LEAD_UW")==null?"":thirdViewDataMap.get("RSK_LEAD_UW").toString());
					}else if("64".equalsIgnoreCase(beanObj.getLeader_Underwriter())){
						//beanObj.setLeader_Underwriter("ITI Reinsurance Ltd");
						beanObj.setLeader_Underwriter(getUGUWName(beanObj.getBranchCode(),beanObj.getLeader_Underwriter()));
					}
					beanObj.setLeader_Underwriter_share(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_LEAD_UW_SHARE")==null?"":thirdViewDataMap.get("RSK_LEAD_UW_SHARE").toString()));
					beanObj.setAccounts(thirdViewDataMap.get("RSK_ACCOUNTS")==null?"":thirdViewDataMap.get("RSK_ACCOUNTS").toString());
					beanObj.setExclusion(thirdViewDataMap.get("RSK_EXCLUSION")==null?"":thirdViewDataMap.get("RSK_EXCLUSION").toString());
					beanObj.setRemarks(thirdViewDataMap.get("RSK_REMARKS")==null?"":thirdViewDataMap.get("RSK_REMARKS").toString());
					beanObj.setUnderwriter_Recommendations(thirdViewDataMap.get("RSK_UW_RECOMM")==null?"":thirdViewDataMap.get("RSK_UW_RECOMM").toString());
					beanObj.setGms_Approval(thirdViewDataMap.get("RSK_GM_APPROVAL")==null?"":thirdViewDataMap.get("RSK_GM_APPROVAL").toString());
					beanObj.setDecision(thirdViewDataMap.get("RSK_DECISION")==null?"":thirdViewDataMap.get("RSK_DECISION").toString()); 
					beanObj.setOthercost(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_OTHER_COST")==null?"":thirdViewDataMap.get("RSK_OTHER_COST").toString()));
					beanObj.setReinstAdditionalPremium(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setCommissionQ_SAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_QS_AMT")==null?"":thirdViewDataMap.get("COMM_QS_AMT").toString()));
					beanObj.setCommission_surpAmt(DropDownControllor.formatter(thirdViewDataMap.get("COMM_SURPLUS_AMT")==null?"":thirdViewDataMap.get("COMM_SURPLUS_AMT").toString()));
					beanObj.setCommissionQ_SAmt_DC(DropDownControllor.formatter(thirdViewDataMap.get("COMM_QS_AMT_DC")==null?"":thirdViewDataMap.get("COMM_QS_AMT_DC").toString()));
					beanObj.setCommission_surpAmt_DC(DropDownControllor.formatter(thirdViewDataMap.get("COMM_SURPLUS_AMT_DC")==null?"":thirdViewDataMap.get("COMM_SURPLUS_AMT_DC").toString()));
					//beanObj.setAcqCostPer(DropDownControllor.formatter(thirdViewDataMap.get("OTHER_ACQ_COST_AMT")==null?"":thirdViewDataMap.get("OTHER_ACQ_COST_AMT").toString()));
					//beanObj.setAcqCostPerDC(DropDownControllor.formatter(thirdViewDataMap.get("OTHER_ACQ_COST_AMT_UGX")==null?"":thirdViewDataMap.get("OTHER_ACQ_COST_AMT_UGX").toString()));
					beanObj.setAcqCostPer(DropDownControllor.formatter((Double.parseDouble(beanObj.getCommissionQ_S().replaceAll(",",""))+Double.parseDouble(beanObj.getCommission_surp().replaceAll(",",""))+Double.parseDouble(beanObj.getOverRidder().replaceAll(",",""))+Double.parseDouble(beanObj.getBrokerage().replaceAll(",",""))+Double.parseDouble(beanObj.getTax().replaceAll(",",""))+Double.parseDouble(beanObj.getOthercost().replaceAll(",","")))+""));
					beanObj.setAcqCostPerDC(DropDownControllor.formatter(getDesginationCountry(StringUtils.isBlank(beanObj.getAcqCostPer())?"0":beanObj.getAcqCostPer().replaceAll(",", ""),beanObj.getExchRate()).toString()));
					//beanObj.setProfit_commission(thirdViewDataMap.get("RSK_SHARE_PROFIT_COMMISSION")==null?"":thirdViewDataMap.get("RSK_SHARE_PROFIT_COMMISSION").toString());
					beanObj.setReinstAditionalPremium_percent_Dc(DropDownControllor.formatter(thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT")==null?"":thirdViewDataMap.get("RSK_REINSTATE_ADDL_PREM_PCT").toString()));
					beanObj.setBurningCost(DropDownControllor.formatter(thirdViewDataMap.get("RSK_BURNING_COST_PCT")==null?"":thirdViewDataMap.get("RSK_BURNING_COST_PCT").toString()));
					beanObj.setBrokerage(DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_BROKERAGE")==null?"":thirdViewDataMap.get("RSK_BROKERAGE").toString()));
					beanObj.setLimitPerVesselOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_OC")==null?"0":thirdViewDataMap.get("LIMIT_PER_VESSEL_OC").toString()));
					beanObj.setLimitPerVesselDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_VESSEL_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_VESSEL_DC").toString()));
					beanObj.setLimitPerVesselOSOC(getShareVal(beanObj.getLimitPerVesselOC().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitPerVesselOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitPerVesselOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					
					beanObj.setLimitPerLocationOC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_OC")==null?"0":thirdViewDataMap.get("LIMIT_PER_LOCATION_OC").toString()));
					beanObj.setLimitPerLocationDC(DropDownControllor.formatter(thirdViewDataMap.get("LIMIT_PER_LOCATION_DC")==null?"":thirdViewDataMap.get("LIMIT_PER_LOCATION_DC").toString()));
					beanObj.setLimitPerLocationOSOC(getShareVal(beanObj.getLimitPerLocationOC().replaceAll(",", ""),beanObj.getSharSign(),"share"));
					beanObj.setLimitPerLocationOSDC(DropDownControllor.formatter(getDesginationCountry(beanObj.getLimitPerLocationOSOC().replaceAll(",",""),beanObj.getExchRate()).toString()));
					beanObj.setEndorsementDate(thirdViewDataMap.get("ENDT_DATE")==null?"":thirdViewDataMap.get("ENDT_DATE").toString());
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
					
					beanObj.setSlideScaleCommission(thirdViewDataMap.get("RSK_SLADSCALE_COMM")==null?"":thirdViewDataMap.get("RSK_SLADSCALE_COMM").toString());
					beanObj.setLossParticipants(thirdViewDataMap.get("RSK_LOSS_PART_CARRIDOR")==null?"":thirdViewDataMap.get("RSK_LOSS_PART_CARRIDOR").toString());
					beanObj.setCommissionSubClass(thirdViewDataMap.get("RSK_COMBIN_SUB_CLASS")==null?"":thirdViewDataMap.get("RSK_COMBIN_SUB_CLASS").toString());
					beanObj.setLeader_Underwriter_country(thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY")==null?"":thirdViewDataMap.get("RSK_LEAD_UNDERWRITER_COUNTRY").toString());
					beanObj.setOrginalacqcost(thirdViewDataMap.get("RSK_INCLUDE_ACQ_COST")==null?"":thirdViewDataMap.get("RSK_INCLUDE_ACQ_COST").toString());
					beanObj.setOuracqCost(thirdViewDataMap.get("RSK_OUR_ACQ_OUR_SHARE_OC")==null?"":thirdViewDataMap.get("RSK_OUR_ACQ_OUR_SHARE_OC").toString());
					beanObj.setOurassessmentorginalacqcost(thirdViewDataMap.get("RSK_OUR_ASS_ACQ_COST")==null?"":thirdViewDataMap.get("RSK_OUR_ASS_ACQ_COST").toString());
					beanObj.setProfitCommission(thirdViewDataMap.get("RSK_PRO_NOTES")==null?"":thirdViewDataMap.get("RSK_PRO_NOTES").toString());
					beanObj.setLosscommissionSubClass(thirdViewDataMap.get("RSK_LOSS_COMBIN_SUB_CLASS")==null?"":thirdViewDataMap.get("RSK_LOSS_COMBIN_SUB_CLASS").toString());
					beanObj.setSlidecommissionSubClass(thirdViewDataMap.get("RSK_SLIDE_COMBIN_SUB_CLASS")==null?"":thirdViewDataMap.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString());
					beanObj.setCrestacommissionSubClass(thirdViewDataMap.get("RSK_CRESTA_COMBIN_SUB_CLASS")==null?"":thirdViewDataMap.get("RSK_CRESTA_COMBIN_SUB_CLASS").toString());
					beanObj.setManagementExpenses(thirdViewDataMap.get("RSK_PRO_MANAGEMENT_EXP")==null?"":thirdViewDataMap.get("RSK_PRO_MANAGEMENT_EXP").toString());
					beanObj.setCommissionType(thirdViewDataMap.get("RSK_PRO_COMM_TYPE")==null?"":thirdViewDataMap.get("RSK_PRO_COMM_TYPE").toString());
					beanObj.setProfitCommissionPer(thirdViewDataMap.get("RSK_PRO_COMM_PER")==null?"":DropDownControllor.formatterpercentage(thirdViewDataMap.get("RSK_PRO_COMM_PER").toString()));
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
					beanObj.setLocRate(thirdViewDataMap.get("RSK_RATE")==null?"":thirdViewDataMap.get("RSK_RATE").toString());
					
					
					if("NR".equalsIgnoreCase(beanObj.getLocRate())){
						beanObj.setLocRate("On Net Rate");
					}
					else if("GR".equalsIgnoreCase(beanObj.getLocRate())){
						beanObj.setLocRate("On Gross Rate");
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
			
			String query=getQuery("GET_BASE_LAYER_DETAILS");
			logger.info("selectQry " + selectQry);
			logger.info("Args[0]" + beanObj.getProposal_no());
			List<Map<String, Object>> list1 = this.mytemplate.queryForList(query,new Object[]{beanObj.getProduct_id(),beanObj.getBranchCode(),beanObj.getProposal_no()});
			logger.info("Result Size=>" +  list1.size());
			Map<String, Object> resMap1 = null;
			if(list1!=null && list1.size()>0)
				resMap1 = (Map<String, Object>)list1.get(0);
			if (resMap1 != null) {
					beanObj.setBaseLayer(resMap1.get("BASE_LAYER")==null?"":resMap1.get("BASE_LAYER").toString());
			}
			
			showRetroContracts(beanObj,pid,true);
			GetRemarksDetails(beanObj);
			getGetRetDetails(beanObj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");

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

	public void showRetroContracts(RiskDetailsBean beanObj,String productId,boolean view)  {
		try{
			RiskDetailsBean bean=new RiskDetailsBean();
			Object[] args=null;
			String query="";
			int noofInsurar = 0;
			if(StringUtils.isNotBlank(beanObj.getNo_Insurer())){
				 noofInsurar = Integer.parseInt(beanObj.getNo_Insurer());
				 noofInsurar = noofInsurar+1;
			}
			//beanObj.getRequest().setAttribute("NoInsurar",beanObj.getNo_Insurer());
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
			if(insDetailsList!=null&&insDetailsList.size()>0){
				List<String> retroList=new ArrayList<String>();
				List<String> retroPercentage=new ArrayList<String>();
				List<String> UWYear=new ArrayList<String>();
				//List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				List<String> cedingCompany=new ArrayList<String>();
				for(int j=0;j<insDetailsList.size();j++){
					
					Map<String, Object> insDetailsMap=(Map<String, Object>)insDetailsList.get(j);
					if("R".equalsIgnoreCase((String)insDetailsMap.get("TYPE")))	{
						beanObj.setRetentionPercentage(insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
					}else{
						//beanObj.getRequest().setAttribute("cedingCompany"+(j-1),insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
						/*beanObj.getRequest().setAttribute("RetroPercentage"+(j-1),insDetailsMap.get("RETRO_PER")==null?"":insDetailsMap.get("RETRO_PER").toString());
		 				beanObj.getRequest().setAttribute("uwYear"+(j-1),insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
						 */
						if(j==1){
							if(beanObj.getUwYear().equalsIgnoreCase(insDetailsMap.get("UW_YEAR").toString())){
								beanObj.setRetroDupYerar(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
								}else{
									beanObj.setRetroDupYerar(beanObj.getUwYear());
								}
							if(insDetailsMap.get("CONTRACTNO")!=null){
							beanObj.setRetroDupContract(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
							}
						}
						else if(j>1){
							UWYear.add(insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString());
							cedingCompany.add(insDetailsMap.get("CONTRACTNO")==null?"":insDetailsMap.get("CONTRACTNO").toString());
							retroPercentage.add(insDetailsMap.get("RETRO_PER")==null?"0":insDetailsMap.get("RETRO_PER").toString());
							retroFinalList.add(getRetroContractDetailsList(bean,2,insDetailsMap.get("UW_YEAR")==null?"":insDetailsMap.get("UW_YEAR").toString()));
							retroList.add(String.valueOf(j));
						}
						//beanObj.getRequest().setAttribute("retroType"+(j-1),insDetailsMap.get("RETRO_TYPE")==null?"":insDetailsMap.get("RETRO_TYPE").toString());

						
						logger.info("ProductCode=>"+productId);
						if("2".equals(productId)){
							bean.setProduct_id("4");	
							bean.setRetroType("TR");
						}else if("3".equals(productId)){
							bean.setProduct_id("4");	
							bean.setRetroType("TR");	
						}
						
						bean.setBranchCode(beanObj.getBranchCode());
						bean.setIncepDate(beanObj.getIncepDate());
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
				/*	if(retroDupList.size()<=0){
					retroDupList.add(new ArrayList<Map<String,Object>>());
				}*/
				beanObj.setRetroFinalList(retroFinalList);
				//beanObj.setRetroDupList(retroDupList);
			}else{
				List<List<Map<String,Object>>> retroFinalList=new ArrayList<List<Map<String,Object>>>();
				//List<List<Map<String,Object>>> retroDupList=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<Integer.parseInt(StringUtils.isBlank(beanObj.getNo_Insurer())?"0":beanObj.getNo_Insurer());i++){
					//RiskDetailsBean bean=new RiskDetailsBean();
					logger.info("ProductCode=>"+productId);
					if("2".equals(productId)){
						bean.setProduct_id("4");	
						bean.setRetroType("TR");
					}else if("3".equals(productId)){
						bean.setProduct_id("4");	
						bean.setRetroType("TR");
					}
					bean.setBranchCode(beanObj.getBranchCode());
					bean.setIncepDate(beanObj.getIncepDate());
					retroFinalList.add(new ArrayList<Map<String,Object>>());
					//retroDupList.add(new ArrayList<Map<String,Object>>());
					beanObj.setRetroUwyear(getRetroContractDetailsList(bean,1,""));
				}
			}
			if(beanObj.getNo_Insurer()!=null &&Integer.parseInt(beanObj.getNo_Insurer())==0){
				beanObj.setRetroDupYerar(beanObj.getUwYear());
				List<Map<String,Object>> list = getRetroContractDetailsList(beanObj,3,"");
				for (int i=0;i<list.size();i++){
					Map<String,Object> map = list.get(i);
					beanObj.setRetroDupContract(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString());
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}


	}
	public void insertRetroCess(RiskDetailsBean beanObj) {}

	public void insertRetroContracts(RiskDetailsBean beanObj,String productId) {
		try{
			final int LoopCount = Integer.parseInt(beanObj.getNo_Insurer());
			Object[] obj = new Object[12];
			String endtNo=new DropDownControllor().getRiskComMaxAmendId(beanObj.getProposal_no());
			//To delete the existing retrocess for a proposal no with amend id.
			this.mytemplate.update("delete from TTRN_INSURER_DETAILS where PROPOSAL_NO=? and ENDORSEMENT_NO=?" , new Object[]{beanObj.getProposal_no(),endtNo});
			String query=getQuery(DBConstants.FAC_INSERT_INSDETAILS);
			String qry=getQuery(DBConstants.FAC_UPDATE_INSDETAILS);
			if(LoopCount==0){
				beanObj.setRetentionPercentage("100");
			}
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
			int j=2;
			for(int i=0;i<LoopCount;i++){
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
				logger.info("Query=>" + query);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				logger.info("Result=>" +this.mytemplate.update(query, obj));
				j++;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
	}
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean beanObj,int flag, String UWYear){
		String query="";
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		try{
			if(flag==1)	{
				if("4".equals(beanObj.getProduct_id())){
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
				query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),UWYear,beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),UWYear,beanObj.getIncepDate()});
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
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		return list;	
	}
	public String getRetroContractDetails(RiskDetailsBean beanObj)  {
		String  Cedingco="";
		String query="";
		try{
			List<Map<String, Object>> list =null;
			 if(StringUtils.isNotEmpty(beanObj.getUwYear())&&"Dup".equalsIgnoreCase(beanObj.getMode())){
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
			 	else {
					//query = getQuery(DBConstants.FAC_SELECT_RETROCONTDET);	
				query = getQuery("FAC_SELECT_RETROCONTDET_23");
				logger.info("Select Query=>"+query);
				logger.info("Product Code=>"+beanObj.getProduct_id());
				//logger.info("Retro Type=>"+(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()));
				logger.info("UW Year=>"+beanObj.getUwYear());
				logger.info("Inception Date=>"+beanObj.getIncepDate());
				logger.info("Branch Code=>"+beanObj.getBranchCode());
				//list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),(StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType()),beanObj.getUwYear(),beanObj.getIncepDate()});
				list = this.mytemplate.queryForList(query, new Object[] {beanObj.getProduct_id(),beanObj.getUwYear(),beanObj.getIncepDate(),beanObj.getBranchCode(),beanObj.getUwYear(),beanObj.getIncepDate()});
				beanObj.setRetroContractList(list);
				}
			if(list!=null && list.size()>0){
				logger.info("List<Map<String, Object>> Size=>"+list.size());
				Map<String, Object> resMap;
				for(int i=0;i<list.size();i++){
					resMap = (Map<String, Object>)list.get(i); 
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

		}
		return Cedingco;	
	}
	public List<Map<String, Object>> getValidation(RiskDetailsBean  formObj)  {
		String query="";
		List<Map<String, Object>> list=null;
		try{
			//query = getQuery(DBConstants.FAC_SELECT_RENEWALVALIDATION);
			query = getQuery(DBConstants.PRO_SELECT_RENEWALVALIDATION);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+formObj.getIncepDate());
			logger.info("Args[1]=>"+formObj.getRenewal_contract_no());
			logger.info("Args[2]=>"+formObj.getDepartId());
			list = this.mytemplate.queryForList(query, new Object[] {formObj.getIncepDate(),formObj.getRenewal_contract_no(),formObj.getDepartId()});
		} 
		catch(Exception e){
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
				if(list!=null && list.size()>0)	{
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

	public Object[] updateHomePostion(final RiskDetailsBean beanObj,final String pid, final boolean bool) {

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
		obj[10] = beanObj.getProStatus().trim();
		if(beanObj.getContractno()==null || beanObj.getContractno().equalsIgnoreCase("") ){
			/*if(beanObj.getProStatus().equalsIgnoreCase("P") || beanObj.getProStatus().equalsIgnoreCase("A")){
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
			else if(beanObj.getProStatus().equalsIgnoreCase("R")){
				obj[11] ="R";
			}else if(beanObj.getProStatus().equalsIgnoreCase("N")){
				obj[11] ="N";
			}
		}
		else{
			obj[11] =beanObj.getProStatus().trim();
		}
		obj[12] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		obj[13] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[14] = beanObj.getLoginId();
		obj[15]="";
		
		obj[16] =  StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[17] = beanObj.getBouquetModeYN();
		obj[18] = beanObj.getBouquetNo();
		obj[19] = beanObj.getUwYearTo();
		obj[20] = beanObj.getProposal_no();
		obj[21] = beanObj.getAmendId();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	
	//hidden method
	public Object[] secondContarctPageCommissionAruguments(final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=new Object[0];
		obj = new Object[33];
		obj[0] = beanObj.getProposal_no();
		obj[1] = (getMaxAmednId(beanObj.getProposal_no()))+"";
		obj[2] = "0";
		obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
		obj[4] = beanObj.getTax();
		obj[5] = beanObj.getShare_Profit_Commission();
		obj[6] = "0";
		obj[7] = beanObj.getAcquisition_Cost();
		obj[8] = getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
		obj[9] = beanObj.getCommissionQ_S();
		obj[10] = beanObj.getCommission_surp();
		obj[11] = beanObj.getOverRidder();
		//obj[12] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
		//obj[13] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();
		obj[14] = beanObj.getPremium_Reserve();
		obj[15] = beanObj.getLoss_reserve();
		obj[16] = beanObj.getInterest();
		obj[17] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
		obj[18] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
		obj[19] = beanObj.getLoss_Advise();
		obj[20] = beanObj.getCash_Loss_Limit();
		obj[21] = getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
		obj[22] = beanObj.getLeader_Underwriter();
		obj[23] = beanObj.getLeader_Underwriter_share();
		obj[24] = beanObj.getAccounts();
		obj[25] = beanObj.getExclusion();
		obj[26] = StringUtils.isEmpty(beanObj.getRemarks())?"":beanObj.getRemarks();
		obj[27] = beanObj.getUnderwriter_Recommendations();
		obj[28] = beanObj.getGms_Approval();
		obj[29] = "";
		obj[30] = "";
		//obj[31] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0"	: beanObj.getProfit_commission();
		obj[32] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0"	: beanObj.getOthercost();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] updateContractRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId,final String endNo) {
		Object[] obj=null;
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
		obj[6] = "";
		obj[7] = "";
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
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageCommissionSaveAruguments(final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		obj = new Object[71];
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
		obj[25] = StringUtils.isEmpty(beanObj.getUnderwriter_Recommendations()) ? "" : beanObj.getRemarks();
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
		obj[66] =StringUtils.isEmpty(beanObj.getLocRate())? "" :beanObj.getLocRate();
		obj[67] =StringUtils.isEmpty(beanObj.getPremiumResType())? "" :beanObj.getPremiumResType();
		obj[68] = StringUtils.isEmpty(beanObj.getPcfpcType())?"":beanObj.getPcfpcType();
		obj[69] = StringUtils.isEmpty(beanObj.getPcfixedDate())?"":beanObj.getPcfixedDate();
		obj[70] = StringUtils.isEmpty(beanObj.getPortfolioType())?"":beanObj.getPortfolioType();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageFirstTableSaveAruguments(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
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
		obj[6] = "";
		obj[7] = "";
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
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageCommissionAruguments(final RiskDetailsBean beanObj, final String productId) {
		Object[] obj=null;
		obj = new Object[71];
		obj[0] = beanObj.getProposal_no();
		obj[1] = "0";
		obj[2] = beanObj.getLayerNo();
		obj[3] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
		obj[4] = beanObj.getTax();
		obj[5] = beanObj.getShare_Profit_Commission().replaceAll(",", "");
		obj[6] = "0";
		obj[7] = beanObj.getAcquisition_Cost().replaceAll(",", "");
		obj[8] = getDesginationCountry(beanObj.getAcquisition_Cost().replaceAll(",",""),beanObj.getExchRate());
		obj[9] = StringUtils.isBlank(beanObj.getCommissionQ_S())?"0":beanObj.getCommissionQ_S().replaceAll(",", "");
		obj[10] = StringUtils.isBlank(beanObj.getCommission_surp())?"0":beanObj.getCommission_surp().replaceAll(",", "");
		obj[11] = beanObj.getOverRidder().replaceAll(",", "");
		//obj[12] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
		//obj[13] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();
		obj[12] = beanObj.getPremium_Reserve();
		obj[13] = beanObj.getLoss_reserve();
		obj[14] = beanObj.getInterest();
		obj[15] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
		obj[16] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
		obj[17] = beanObj.getLoss_Advise().replaceAll(",", "");
		obj[18] = beanObj.getCash_Loss_Limit().replaceAll(",", "");
		obj[19] = getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
		obj[20] = beanObj.getLeader_Underwriter();
		obj[21] = beanObj.getLeader_Underwriter_share();
		obj[22] = beanObj.getAccounts();
		obj[23] = beanObj.getExclusion();
		obj[24] =StringUtils.isEmpty(beanObj.getRemarks())?"":beanObj.getRemarks();
		obj[25] = beanObj.getUnderwriter_Recommendations();
		obj[26] = beanObj.getGms_Approval();
		obj[27] = "";
		obj[28] = "";
		//obj[31] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0": beanObj.getProfit_commission().replaceAll(",", "");
		obj[29] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0"	: beanObj.getOthercost().replaceAll(",", "");
		obj[30] = beanObj.getCrestaStatus();
		obj[31] = beanObj.getEvent_limit();
		obj[32] =getDesginationCountry(beanObj.getEvent_limit(),beanObj.getExchRate());
		obj[33] = beanObj.getAggregate_Limit();
		obj[34] =getDesginationCountry(beanObj.getAggregate_Limit(),beanObj.getExchRate());
		obj[35] = beanObj.getOccurrent_Limit();
		obj[36] =getDesginationCountry(beanObj.getOccurrent_Limit(),beanObj.getExchRate());
		obj[37] = beanObj.getSlideScaleCommission();
		obj[38] = beanObj.getLossParticipants();
		obj[39] =StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "": beanObj.getCommissionSubClass();
		obj[40]=beanObj.getDepartmentId();
		obj[41] = beanObj.getLoginId();
		obj[42] = beanObj.getBranchCode();
		obj[43] = beanObj.getLeader_Underwriter_country();
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
		obj[66] =StringUtils.isEmpty(beanObj.getLocRate())? "" :beanObj.getLocRate();
		obj[67] =StringUtils.isEmpty(beanObj.getPremiumResType())? "" :beanObj.getPremiumResType();
		obj[68] = StringUtils.isEmpty(beanObj.getPcfpcType())?"":beanObj.getPcfpcType();
		obj[69] = StringUtils.isEmpty(beanObj.getPcfixedDate())?"":beanObj.getPcfixedDate();
		obj[70] = StringUtils.isEmpty(beanObj.getPortfolioType())?"":beanObj.getPortfolioType();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] secondPageFirstTableAruguments(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		obj = new Object[18];
		obj[0] =  StringUtils.isBlank(beanObj.getLimitOurShare())?"":beanObj.getLimitOurShare().replaceAll(",", "");
		obj[1] = getDesginationCountry(beanObj.getLimitOurShare(), beanObj
				.getExchRate());
		obj[2] = StringUtils.isBlank(beanObj.getEpiAsPerOffer())?"0":beanObj.getEpiAsPerOffer().replaceAll(",", "");
		obj[3] = getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj
				.getExchRate());
		obj[4] =StringUtils.isBlank(beanObj.getEpiAsPerShare())?"0": beanObj.getEpiAsPerShare();
		obj[5] = getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj
				.getExchRate());
		obj[6] = "";
		obj[7] = "";
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
		obj[12]=StringUtils.isBlank(beanObj.getCommissionQ_SAmt())?"0":beanObj.getCommissionQ_SAmt().replaceAll(",", "");
		obj[13]=StringUtils.isBlank(beanObj.getCommission_surpAmt())?"0":beanObj.getCommission_surpAmt().replaceAll(",", "");
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
		else if (beanObj.getProStatus().equalsIgnoreCase("A")|| beanObj.getProStatus().equalsIgnoreCase("P")) {
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
	public Object[] savemodeUpdateRiskDetailsSecondFormSecondTable(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=new Object[0];
		obj = new Object[67];
		obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
		obj[1] = StringUtils.isEmpty(beanObj.getTax()) ? "0" : beanObj.getTax();
		obj[2] = StringUtils.isEmpty(beanObj.getShare_Profit_Commission()) ? "0": beanObj.getShare_Profit_Commission();
		obj[3] = StringUtils.isEmpty(beanObj.getAcquisition_Cost()) ? "0": beanObj.getAcquisition_Cost();
		obj[4] = StringUtils.isEmpty(beanObj.getAcquisition_Cost())	|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getAcquisition_Cost(),beanObj.getExchRate());
		obj[5] = StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0": beanObj.getCommissionQ_S();
		obj[6] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0": beanObj.getCommission_surp();
		obj[7] = StringUtils.isEmpty(beanObj.getOverRidder()) ? "0"	: beanObj.getOverRidder();
		//obj[8] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
		//obj[9] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();		
		obj[8] = StringUtils.isEmpty(beanObj.getPremium_Reserve()) ? "0": beanObj.getPremium_Reserve();
		obj[9] = StringUtils.isEmpty(beanObj.getLoss_reserve()) ? "0": beanObj.getLoss_reserve();
		obj[10] = StringUtils.isEmpty(beanObj.getInterest()) ? "0": beanObj.getInterest();
		obj[11] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
		obj[12] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
		obj[13] = StringUtils.isEmpty(beanObj.getLoss_Advise()) ? "0": beanObj.getLoss_Advise();
		obj[14] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit()) ? "0": beanObj.getCash_Loss_Limit();
		obj[15] = StringUtils.isEmpty(beanObj.getCash_Loss_Limit())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
		obj[16] = StringUtils.isEmpty(beanObj.getLeader_Underwriter()) ? "": beanObj.getLeader_Underwriter();
		obj[17] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_share()) ? "0": beanObj.getLeader_Underwriter_share();
		obj[18] = beanObj.getAccounts();
		obj[19] = beanObj.getExclusion();
		obj[20] = StringUtils.isEmpty(beanObj.getRemarks())?"":beanObj.getRemarks();
		obj[21] = beanObj.getUnderwriter_Recommendations();
		obj[22] = beanObj.getGms_Approval();
		//obj[25] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0": beanObj.getProfit_commission();
		obj[23] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0": beanObj.getOthercost();
		obj[24] = beanObj.getCrestaStatus();
		obj[25] = beanObj.getEvent_limit();
		obj[26] =getDesginationCountry(beanObj.getEvent_limit(),beanObj.getExchRate());
		obj[27] = beanObj.getAggregate_Limit();
		obj[28] =getDesginationCountry(beanObj.getAggregate_Limit(),beanObj.getExchRate());
		obj[29] = beanObj.getOccurrent_Limit();
		obj[30] =getDesginationCountry(beanObj.getOccurrent_Limit(),beanObj.getExchRate());
		obj[31] = beanObj.getSlideScaleCommission();
		obj[32] = beanObj.getLossParticipants();
		obj[33] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "": beanObj.getCommissionSubClass();
		obj[34] = beanObj.getDepartmentId();
		obj[35] = beanObj.getLoginId();
		obj[36] = beanObj.getBranchCode();
		obj[37] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country())?"":beanObj.getLeader_Underwriter_country();
		obj[38] =StringUtils.isEmpty(beanObj.getOrginalacqcost())?"":beanObj.getOrginalacqcost();
		obj[39] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost())?"":beanObj.getOurassessmentorginalacqcost();
		obj[40] = StringUtils.isEmpty(beanObj.getOuracqCost())?"":beanObj.getOuracqCost();
		obj[41] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass())?"":beanObj.getLosscommissionSubClass();
		obj[42] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass())?"":beanObj.getSlidecommissionSubClass();
		obj[43] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass())?"":beanObj.getCrestacommissionSubClass();
		if("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
			obj[44] = StringUtils.isEmpty(beanObj.getManagementExpenses())?"":beanObj.getManagementExpenses();
			obj[45] = StringUtils.isEmpty(beanObj.getCommissionType())?"":beanObj.getCommissionType();
			obj[46] = StringUtils.isEmpty(beanObj.getProfitCommissionPer())?"":beanObj.getProfitCommissionPer();
			obj[47] = StringUtils.isEmpty(beanObj.getSetup())?"":beanObj.getSetup();
			obj[48] = StringUtils.isEmpty(beanObj.getSuperProfitCommission())?"":beanObj.getSuperProfitCommission();
			obj[49] = StringUtils.isEmpty(beanObj.getLossCarried())?"":beanObj.getLossCarried();
			obj[50] = StringUtils.isEmpty(beanObj.getLossyear())?"":beanObj.getLossyear();
			obj[51] = StringUtils.isEmpty(beanObj.getProfitCarried())?"":beanObj.getProfitCarried();
			obj[52] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear())?"":beanObj.getProfitCarriedForYear();
			obj[53] = StringUtils.isEmpty(beanObj.getFistpc())?"":beanObj.getFistpc();
			obj[54] = StringUtils.isEmpty(beanObj.getProfitMont())?"":beanObj.getProfitMont();
			obj[55] = StringUtils.isEmpty(beanObj.getSubProfitMonth())?"":beanObj.getSubProfitMonth();
			obj[56] = StringUtils.isEmpty(beanObj.getSubpc())?"":beanObj.getSubpc();
			obj[57] = StringUtils.isEmpty(beanObj.getSubSeqCalculation())?"":beanObj.getSubSeqCalculation();
			obj[58] = StringUtils.isEmpty(beanObj.getProfitCommission())?"":beanObj.getProfitCommission();
			}
			else{
				obj[44] = "";
				obj[45] =  "";
				obj[46] =  "";
				obj[47] =  "";
				obj[48] =  "";
				obj[49] =  "";
				obj[50] =  "";
				obj[51] =  "";
				obj[52] =  "";
				obj[53] =  "";
				obj[54] =  "";
				obj[55] =  "";
				obj[56] =  "";
				obj[57] =  "";
				obj[58] =  "";
			}
		obj[59] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
		obj[60] = StringUtils.isEmpty(beanObj.getLocRate())? "" :beanObj.getLocRate();
		obj[61] =StringUtils.isEmpty(beanObj.getPremiumResType())? "" :beanObj.getPremiumResType();
		obj[62] = StringUtils.isEmpty(beanObj.getPcfpcType())?"":beanObj.getPcfpcType();
		obj[63] = StringUtils.isEmpty(beanObj.getPcfixedDate())?"":beanObj.getPcfixedDate();
		obj[64] = StringUtils.isEmpty(beanObj.getPortfolioType())?"":beanObj.getPortfolioType();
		obj[65] = beanObj.getProposal_no();
		obj[66] = endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] saveUpdateRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
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
		obj[6] ="";
		obj[7] = "";
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
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] updateRiskDetailsSecondFormSecondTable(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
		if (productId.equalsIgnoreCase("2")) {
			obj = new Object[67];
			obj[0] = StringUtils.isEmpty(beanObj.getBrokerage()) ? "0": beanObj.getBrokerage();
			obj[1] = beanObj.getTax();
			obj[2] = beanObj.getShare_Profit_Commission();
			obj[3] = beanObj.getAcquisition_Cost();
			obj[4] = getDesginationCountry(beanObj.getAcquisition_Cost().replaceAll(",",""),beanObj.getExchRate());
			obj[5] =StringUtils.isEmpty(beanObj.getCommissionQ_S()) ? "0": beanObj.getCommissionQ_S();
			obj[6] = StringUtils.isEmpty(beanObj.getCommission_surp()) ? "0":beanObj.getCommission_surp();
			obj[7] = StringUtils.isEmpty(beanObj.getOverRidder())?"0":beanObj.getOverRidder();
			//obj[8] = StringUtils.isEmpty(beanObj.getManagement_Expenses()) ? "0": beanObj.getManagement_Expenses();
			//obj[9] = StringUtils.isEmpty(beanObj.getLossC_F()) ? "0" : beanObj.getLossC_F();
			obj[8] = beanObj.getPremium_Reserve();
			obj[9] = beanObj.getLoss_reserve();
			obj[10] = beanObj.getInterest();
			obj[11] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Premium()) ? "0": beanObj.getPortfolio_inout_Premium();
			obj[12] = StringUtils.isEmpty(beanObj.getPortfolio_inout_Loss()) ? "0": beanObj.getPortfolio_inout_Loss();
			obj[13] = beanObj.getLoss_Advise();
			obj[14] = beanObj.getCash_Loss_Limit();
			obj[15] = getDesginationCountry(beanObj.getCash_Loss_Limit(),beanObj.getExchRate());
			obj[16] = beanObj.getLeader_Underwriter();
			obj[17] = beanObj.getLeader_Underwriter_share();
			obj[18] = beanObj.getAccounts();
			obj[19] = beanObj.getExclusion();
			obj[20] = StringUtils.isEmpty(beanObj.getRemarks())?"":beanObj.getRemarks();
			obj[21] = beanObj.getUnderwriter_Recommendations();
			obj[22] = beanObj.getGms_Approval();
			//obj[25] = StringUtils.isEmpty(beanObj.getProfit_commission()) ? "0"	: beanObj.getProfit_commission();
			obj[23] = StringUtils.isEmpty(beanObj.getOthercost()) ? "0"	: beanObj.getOthercost();
			obj[24] =beanObj.getCrestaStatus();
			obj[25] = beanObj.getEvent_limit();
			obj[26] =getDesginationCountry(beanObj.getEvent_limit(),beanObj.getExchRate());
			obj[27] = beanObj.getAggregate_Limit();
			obj[28] =getDesginationCountry(beanObj.getAggregate_Limit(),beanObj.getExchRate());
			obj[29] = beanObj.getOccurrent_Limit();
			obj[30] =getDesginationCountry(beanObj.getOccurrent_Limit(),beanObj.getExchRate());
			obj[31] = beanObj.getSlideScaleCommission();
			obj[32] = beanObj.getLossParticipants();
			obj[33] = StringUtils.isEmpty(beanObj.getCommissionSubClass()) ? "": beanObj.getCommissionSubClass();
			obj[34] =  beanObj.getDepartmentId();
			obj[35] = beanObj.getLoginId();
			obj[36] = beanObj.getBranchCode();
			obj[37] = StringUtils.isEmpty(beanObj.getLeader_Underwriter_country())?"":beanObj.getLeader_Underwriter_country();
			obj[38] =StringUtils.isEmpty(beanObj.getOrginalacqcost())?"":beanObj.getOrginalacqcost();
			obj[39] = StringUtils.isEmpty(beanObj.getOurassessmentorginalacqcost())?"":beanObj.getOurassessmentorginalacqcost();
			obj[40] = StringUtils.isEmpty(beanObj.getOuracqCost())?"":beanObj.getOuracqCost();
			obj[41] = StringUtils.isEmpty(beanObj.getLosscommissionSubClass())?"":beanObj.getLosscommissionSubClass();
			obj[42] = StringUtils.isEmpty(beanObj.getSlidecommissionSubClass())?"":beanObj.getSlidecommissionSubClass();
			obj[43] = StringUtils.isEmpty(beanObj.getCrestacommissionSubClass())?"":beanObj.getCrestacommissionSubClass();
			if("1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
			obj[44] = StringUtils.isEmpty(beanObj.getManagementExpenses())?"":beanObj.getManagementExpenses();
			obj[45] = StringUtils.isEmpty(beanObj.getCommissionType())?"":beanObj.getCommissionType();
			obj[46] = StringUtils.isEmpty(beanObj.getProfitCommissionPer())?"":beanObj.getProfitCommissionPer();
			obj[47] = StringUtils.isEmpty(beanObj.getSetup())?"":beanObj.getSetup();
			obj[48] = StringUtils.isEmpty(beanObj.getSuperProfitCommission())?"":beanObj.getSuperProfitCommission();
			obj[49] = StringUtils.isEmpty(beanObj.getLossCarried())?"":beanObj.getLossCarried();
			obj[50] = StringUtils.isEmpty(beanObj.getLossyear())?"":beanObj.getLossyear();
			obj[51] = StringUtils.isEmpty(beanObj.getProfitCarried())?"":beanObj.getProfitCarried();
			obj[52] = StringUtils.isEmpty(beanObj.getProfitCarriedForYear())?"":beanObj.getProfitCarriedForYear();
			obj[53] = StringUtils.isEmpty(beanObj.getFistpc())?"":beanObj.getFistpc();
			obj[54] = StringUtils.isEmpty(beanObj.getProfitMont())?"":beanObj.getProfitMont();
			obj[55] = StringUtils.isEmpty(beanObj.getSubProfitMonth())?"":beanObj.getSubProfitMonth();
			obj[56] = StringUtils.isEmpty(beanObj.getSubpc())?"":beanObj.getSubpc();
			obj[57] = StringUtils.isEmpty(beanObj.getSubSeqCalculation())?"":beanObj.getSubSeqCalculation();
			obj[58] = StringUtils.isEmpty(beanObj.getProfitCommission())?"":beanObj.getProfitCommission();
			
			
			}
			else{
				obj[44] = "";
				obj[45] =  "";
				obj[46] =  "";
				obj[47] =  "";
				obj[48] =  "";
				obj[49] =  "";
				obj[50] =  "";
				obj[51] =  "";
				obj[52] =  "";
				obj[53] =  "";
				obj[54] =  "";
				obj[55] =  "";
				obj[56] =  "";
				obj[57] =  "";
				obj[58] =  "";
			}
			obj[59] = StringUtils.isEmpty(beanObj.getDocStatus())? "" :beanObj.getDocStatus();
			obj[60] = StringUtils.isEmpty(beanObj.getLocRate())? "" :beanObj.getLocRate();
			obj[61] =StringUtils.isEmpty(beanObj.getPremiumResType())? "" :beanObj.getPremiumResType();
			obj[62] = StringUtils.isEmpty(beanObj.getPcfpcType())?"":beanObj.getPcfpcType();
			obj[63] = StringUtils.isEmpty(beanObj.getPcfixedDate())?"":beanObj.getPcfixedDate();
			obj[64] = StringUtils.isEmpty(beanObj.getPortfolioType())?"":beanObj.getPortfolioType();
			obj[65] = beanObj.getProposal_no();
			obj[66] = endNo;
		} 
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public String[] getFirstPageSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		args = new String[54];
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
		args[15] = "0";
		args[16] = beanObj.getPnoc();
		args[17] = beanObj.getRiskCovered();
		args[18] = beanObj.getTerritoryscope();
		args[19] = StringUtils.isBlank(beanObj.getTerritory())?"":beanObj.getTerritory();
		args[20] = beanObj.getProStatus();
		args[21] = beanObj.getProposalType();
		args[22] = beanObj.getAccountingPeriod();
		args[23] = beanObj.getReceiptofStatements();
		args[24] = beanObj.getReceiptofPayment();
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0": beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0": beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "": beanObj.getInsuredName();
		args[29]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[30]=StringUtils.isEmpty(beanObj.getTreatyType()) ? "0"	: beanObj.getTreatyType();
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[36] = beanObj.getLoginId();
		args[37] = beanObj.getBranchCode();
		args[38] = beanObj.getCountryIncludedList();
		args[39] = beanObj.getCountryExcludedList();
		args[40] = StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] =StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
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
		args[53]=endNo;
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	public Object[] updateRiskProposalArgs(final RiskDetailsBean beanObj,final String pid,String endNo) {
		Object[] obj=null;
		obj = new Object[51];
		if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
			obj[0] = beanObj.getFaclimitOrigCur();
			obj[1] = getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
		
		}
		else{
			obj[0] = beanObj.getLimitOrigCur();
			obj[1] = getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
		}
		obj[2] = beanObj.getEpi_origCur();
		obj[3] = getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
		obj[4] = StringUtils.isBlank(beanObj.getOurEstimate())?"0":beanObj.getOurEstimate();
		obj[5] = StringUtils.isBlank(beanObj.getEpi())?"0":beanObj.getEpi();
		obj[6] = getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
		obj[7] = StringUtils.isBlank(beanObj.getXlCost())?"":beanObj.getXlCost();
		obj[8] = getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
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
		obj[22] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0": beanObj.getXlcostOurShare();
		obj[23] = StringUtils.isEmpty(beanObj.getXlcostOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
		obj[24] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
		obj[25] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
		obj[26] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0"	: beanObj.getLimitPerLocationOC();
		obj[27] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		obj[28] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
		obj[29] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
		obj[30] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
		obj[31] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
		obj[32] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
		obj[33] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
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
		obj[47]=StringUtils.isEmpty(beanObj.getPml()) ? "": beanObj.getPml();
		obj[48]=StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0.00": beanObj.getPmlPercent();
		
		obj[49] = beanObj.getProposal_no();
		obj[50]=endNo;
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public Object[] updateRiskDetailsSecondForm(final RiskDetailsBean beanObj, final String productId, final String endNo) {
		Object[] obj=null;
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
		obj[6] = "";
		obj[7] = "";
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
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}
	public String[] getFirstPageInsertAruguments(final RiskDetailsBean beanObj, final String pid,final boolean amendId) {
		String[] args= new String[59];
		if (amendId) {
			args[0] = beanObj.getProposal_no();
			args[1] =(Integer.parseInt(getMaxAmednId(beanObj.getProposal_no()))+1)+"";
			args[26] = beanObj.getContNo();
		}else{
			args[26] = "";
			beanObj.setProposal_no(getMaxProposanlno(pid,beanObj.getBranchCode(),beanObj.getDepartmentId()));
			args[0] = beanObj.getProposal_no();
			args[1] = "0";
		}
		args[2] = "0";
		args[27] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
		args[28] = StringUtils.isEmpty(beanObj.getAccountingPeriod()) ? "0"	: beanObj.getAccountingPeriod();
		args[29] = StringUtils.isEmpty(beanObj.getReceiptofStatements()) ? "0": beanObj.getReceiptofStatements();
		args[30] = StringUtils.isEmpty(beanObj.getReceiptofPayment()) ? "0": beanObj.getReceiptofPayment();
		args[3] = pid;
		args[4] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[5] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0": beanObj.getProfit_Center();
		args[6] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0": beanObj.getSubProfit_center();
		args[7] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[8] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[9] = StringUtils.isEmpty(beanObj.getBroker()) ? "63" : beanObj.getBroker();
		args[10] = StringUtils.isEmpty(beanObj.getTreatyName_type()) ? "": beanObj.getTreatyName_type();
		args[11] = StringUtils.isEmpty(beanObj.getMonth()) ? "" : beanObj.getMonth();
		args[12] = StringUtils.isEmpty(beanObj.getUwYear()) ? "0" : beanObj.getUwYear();
		args[13] = StringUtils.isEmpty(beanObj.getUnderwriter()) ? "" : beanObj.getUnderwriter();
		args[14] = StringUtils.isEmpty(beanObj.getIncepDate()) ? "" : beanObj.getIncepDate();
		args[15] = StringUtils.isEmpty(beanObj.getExpDate()) ? "" : beanObj.getExpDate();
		args[16] = StringUtils.isEmpty(beanObj.getAccDate()) ? "" : beanObj.getAccDate();
		args[17] = StringUtils.isEmpty(beanObj.getOrginalCurrency()) ? "0": beanObj.getOrginalCurrency();
		args[18] = StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : beanObj.getExchRate();
		args[19] = StringUtils.isEmpty(beanObj.getBasis()) ? "0" : beanObj.getBasis();
		args[20] = StringUtils.isEmpty(beanObj.getPnoc()) ? "" : beanObj.getPnoc();
		args[21] = StringUtils.isEmpty(beanObj.getRiskCovered()) ? "0": beanObj.getRiskCovered();
		args[22] = StringUtils.isEmpty(beanObj.getTerritoryscope()) ? "": beanObj.getTerritoryscope();
		args[23] = StringUtils.isBlank(beanObj.getTerritory())?"":beanObj.getTerritory();
		args[24] = StringUtils.isEmpty(beanObj.getProStatus()) ? "0" : beanObj.getProStatus();
		args[25] = "";
		args[31] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0": beanObj.getM_d_InstalmentNumber();
		args[32] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0": beanObj.getNoRetroCess();
		args[33] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0": beanObj.getRetroType();
		args[34] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "": beanObj.getInsuredName();
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
		args[45] = beanObj.getCountryIncludedList();
		args[46] = beanObj.getCountryExcludedList();
		args[47] =StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[48] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[49] =StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
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

	public String[] getFirstPageEditSaveModeAruguments(final RiskDetailsBean beanObj, final String pid,String endNo) {
		String[] args=null;
		args = new String[54];
		args[0] = StringUtils.isEmpty(beanObj.getDepartId()) ? "0" : beanObj.getDepartId();
		args[1] = StringUtils.isEmpty(beanObj.getProfit_Center()) ? "0"	: beanObj.getProfit_Center();
		args[2] = StringUtils.isEmpty(beanObj.getSubProfit_center()) ? "0": beanObj.getSubProfit_center();
		args[3] = StringUtils.isEmpty(beanObj.getPolBr()) ? "0" : beanObj.getPolBr();
		args[4] = StringUtils.isEmpty(beanObj.getCedingCo()) ? "0" : beanObj.getCedingCo();
		args[5] = StringUtils.isEmpty(beanObj.getBroker()) ? "63" : beanObj.getBroker();
		args[6] = StringUtils.isEmpty(beanObj.getTreatyName_type()) ? "": beanObj.getTreatyName_type();
		args[7] = StringUtils.isEmpty(beanObj.getMonth()) ? "" : beanObj.getMonth();
		args[8] = StringUtils.isEmpty(beanObj.getUwYear()) ? "0" : beanObj.getUwYear();
		args[9] = StringUtils.isEmpty(beanObj.getUnderwriter()) ? "" : beanObj.getUnderwriter();
		args[10] = StringUtils.isEmpty(beanObj.getIncepDate()) ? "" : beanObj.getIncepDate();
		args[11] = StringUtils.isEmpty(beanObj.getExpDate()) ? "" : beanObj.getExpDate();
		args[12] = StringUtils.isEmpty(beanObj.getAccDate()) ? "" : beanObj.getAccDate();
		args[13] = StringUtils.isEmpty(beanObj.getOrginalCurrency()) ? "0": beanObj.getOrginalCurrency();
		args[14] = StringUtils.isEmpty(beanObj.getExchRate()) ? "0" : beanObj.getExchRate();
		args[15] = StringUtils.isEmpty(beanObj.getBasis()) ? "0" : beanObj.getBasis();
		args[16] = StringUtils.isEmpty(beanObj.getPnoc()) ? "" : beanObj.getPnoc();
		args[17] = StringUtils.isEmpty(beanObj.getRiskCovered()) ? "" : beanObj.getRiskCovered();
		args[18] = StringUtils.isEmpty(beanObj.getTerritoryscope()) ? "": beanObj.getTerritoryscope();
		args[19] = StringUtils.isEmpty(beanObj.getTerritory()) ? "" : beanObj.getTerritory();
		args[20] = StringUtils.isEmpty(beanObj.getProStatus()) ? ""	: beanObj.getProStatus();
		args[21] = StringUtils.isEmpty(beanObj.getProposalType()) ? "0"	: beanObj.getProposalType();
		args[22] = StringUtils.isEmpty(beanObj.getAccountingPeriod()) ? "0"	: beanObj.getAccountingPeriod();
		args[23] = StringUtils.isEmpty(beanObj.getReceiptofStatements()) ? "0": beanObj.getReceiptofStatements();
		args[24] = StringUtils.isEmpty(beanObj.getReceiptofPayment()) ? "0"	: beanObj.getReceiptofPayment();
		args[25] = StringUtils.isEmpty(beanObj.getM_d_InstalmentNumber()) ? "0"	: beanObj.getM_d_InstalmentNumber();
		args[26] = StringUtils.isEmpty(beanObj.getNoRetroCess()) ? "0": beanObj.getNoRetroCess();
		args[27] = StringUtils.isEmpty(beanObj.getRetroType()) ? "0": beanObj.getRetroType();
		args[28] = StringUtils.isEmpty(beanObj.getInsuredName()) ? "": beanObj.getInsuredName();
		args[29]=StringUtils.isEmpty(beanObj.getInwardType()) ? "0"	: beanObj.getInwardType();
		args[30]=StringUtils.isEmpty(beanObj.getTreatyType()) ? "0"	: beanObj.getTreatyType();
		args[31]=StringUtils.isEmpty(beanObj.getBusinessType()) ? ""	: beanObj.getBusinessType();
		args[32]=StringUtils.isEmpty(beanObj.getExchangeType()) ? ""	: beanObj.getExchangeType();
		args[33]=StringUtils.isEmpty(beanObj.getPerilCovered()) ? ""	: beanObj.getPerilCovered();
		args[34]=StringUtils.isEmpty(beanObj.getLOCIssued()) ? ""	: beanObj.getLOCIssued();
		args[35]=StringUtils.isEmpty(beanObj.getUmbrellaXL()) ? ""	: beanObj.getUmbrellaXL();
		args[36] = beanObj.getLoginId();
		args[37] = beanObj.getBranchCode();
		args[38] = beanObj.getCountryIncludedList();
		args[39] = beanObj.getCountryExcludedList();
		args[40] =StringUtils.isEmpty(beanObj.getTreatynoofLine()) ? ""	:beanObj.getTreatynoofLine().replaceAll(",", "");
		args[41] =StringUtils.isEmpty(beanObj.getEndorsmenttype()) ? ""	:beanObj.getEndorsmenttype();
		args[42] =StringUtils.isEmpty(beanObj.getRunoffYear()) ? "0"	:beanObj.getRunoffYear();
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
		args[53]=endNo;
		logger.info("Args[]=>" + StringUtils.join(args,","));
		return args;
	}

	public Object[] getFirstPageSecondTableAruguments(final RiskDetailsBean beanObj, final String pid,final Object args2, final boolean amendId) {
		Object[] obj=null;
		obj = new Object[38];
		if (amendId) {
			obj[0] = beanObj.getProposal_no();
			obj[1] = args2;
		} else {
			obj[0] = beanObj.getProposal_no();
			obj[1] = "0";
		}
		if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
			obj[3] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
			obj[4] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
		}
		else{
		obj[3] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0" : beanObj.getLimitOrigCur();
		obj[4] = StringUtils.isEmpty(beanObj.getLimitOrigCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
		}
		obj[2] = "0";
		obj[5] = StringUtils.isEmpty(beanObj.getEpi_origCur()) ? "0": beanObj.getEpi_origCur();
		obj[6] = StringUtils.isEmpty(beanObj.getEpi_origCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
		obj[7] = StringUtils.isEmpty(beanObj.getOurEstimate()) ? "0": beanObj.getOurEstimate();
		obj[10] = StringUtils.isEmpty(beanObj.getXlCost()) ? "" : beanObj.getXlCost();
		obj[11] = StringUtils.isEmpty(beanObj.getXlCost())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
		obj[12] = StringUtils.isEmpty(beanObj.getCedReten()) ? "0": beanObj.getCedReten();
		obj[8] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi();
		obj[9] = StringUtils.isEmpty(beanObj.getEpi())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
		obj[13] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0": beanObj.getShareWritt();
		if (beanObj.getProStatus().equalsIgnoreCase("P")) {
			obj[14] = "0";
		} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
			obj[14] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0": beanObj.getSharSign();
		} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
			obj[14] = "0";
		} else {
			obj[14] = "0";
		}
		obj[15] = StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
		obj[16] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
		obj[17] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
		obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
		obj[19] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[21] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[23] =StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0": beanObj.getEpiAsPerShare();
		obj[24] = StringUtils.isEmpty(beanObj.getEpiAsPerShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
		obj[25] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0"	: beanObj.getXlcostOurShare();
		obj[26] = StringUtils.isEmpty(beanObj.getXlcostOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
		obj[27] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
		obj[28] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
		obj[29] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0": beanObj.getLimitPerLocationOC();
		obj[30] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		obj[31] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
		obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
		obj[33] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
		obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
		obj[35]=beanObj.getDepartId();
		obj[36]=beanObj.getLoginId();
		obj[37]=beanObj.getBranchCode();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] getFirstPageSecondTableInsertAruguments(final RiskDetailsBean beanObj, final String pid,final Object args2, final boolean amendId) {
		Object[] obj=null;
		obj = new Object[38];
		if (amendId) {
			obj[1] = args2;
		} else {
			obj[1] = "0";
		}
		obj[0] = beanObj.getProposal_no();
		if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
			obj[3] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
			obj[4] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
		}
		else{
		obj[3] = beanObj.getLimitOrigCur();
		obj[4] = getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
		}
		obj[2] = "0";
		obj[5] = beanObj.getEpi_origCur();
		obj[6] = getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
		obj[7] =StringUtils.isBlank(beanObj.getOurEstimate())?"0": beanObj.getOurEstimate();
		obj[10] = StringUtils.isBlank(beanObj.getXlCost())?"":beanObj.getXlCost();
		obj[11] = getDesginationCountry(StringUtils.isBlank(beanObj.getXlCost())?"0":beanObj.getXlCost(), beanObj.getExchRate());
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
		obj[15]=StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
		obj[16] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
		obj[17] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
		obj[18] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
		obj[19] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[20] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[21] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[22] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[23] =StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0": beanObj.getEpiAsPerShare();
		obj[24] = StringUtils.isEmpty(beanObj.getEpiAsPerShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
		obj[25] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0": beanObj.getXlcostOurShare();
		obj[26] = StringUtils.isEmpty(beanObj.getXlcostOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
		obj[27] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
		obj[28] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
		obj[29] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0": beanObj.getLimitPerLocationOC();
		obj[30] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		obj[31] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
		obj[32] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
		obj[33] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
		obj[34] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
		obj[35] =beanObj.getDepartId();
		obj[36]=beanObj.getLoginId();
		obj[37]=beanObj.getBranchCode();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] getProposalSaveEditModeQuery(final RiskDetailsBean beanObj, final String pid,String endNo) {
		Object[] obj=null;
		obj = new Object[52];
		if( beanObj.getTreatyType().equalsIgnoreCase("4") ||  beanObj.getTreatyType().equalsIgnoreCase("5") ){
			obj[0] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur()) ? "0" : beanObj.getFaclimitOrigCur();
			obj[1] = StringUtils.isEmpty(beanObj.getFaclimitOrigCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getFaclimitOrigCur(), beanObj.getExchRate());
		}
		else{
			obj[0] = StringUtils.isEmpty(beanObj.getLimitOrigCur()) ? "0": beanObj.getLimitOrigCur();
			obj[1] = StringUtils.isEmpty(beanObj.getLimitOrigCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCur(), beanObj.getExchRate());
		}
		obj[2] = StringUtils.isEmpty(beanObj.getEpi_origCur()) ? "0": beanObj.getEpi_origCur();
		obj[3] = StringUtils.isEmpty(beanObj.getEpi_origCur())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpi_origCur(), beanObj.getExchRate());
		obj[4] = StringUtils.isEmpty(beanObj.getOurEstimate()) ? "0": beanObj.getOurEstimate();
		obj[7] = StringUtils.isEmpty(beanObj.getXlCost()) ? "0" : beanObj.getXlCost();
		obj[8] = StringUtils.isEmpty(beanObj.getXlCost())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlCost(), beanObj.getExchRate());
		obj[9] = StringUtils.isEmpty(beanObj.getCedReten()) ? "0" : beanObj.getCedReten();
		obj[5] = StringUtils.isEmpty(beanObj.getEpi()) ? "0" : beanObj.getEpi().replaceAll(",", "");
		obj[6] = StringUtils.isEmpty(beanObj.getEpi())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpi(), beanObj.getExchRate());
		obj[10] = StringUtils.isEmpty(beanObj.getShareWritt()) ? "0": beanObj.getShareWritt();
		if (beanObj.getProStatus().equalsIgnoreCase("P")) {
			obj[11] = "0";
		} else if (beanObj.getProStatus().equalsIgnoreCase("A")) {
			obj[11] = StringUtils.isEmpty(beanObj.getSharSign()) ? "0": beanObj.getSharSign();
		} else if (beanObj.getProStatus().equalsIgnoreCase("R")) {
			obj[11] = "0";
		} else {
			obj[11] = "0";
		}
		obj[12] =StringUtils.isBlank(beanObj.getCedRetenType())?"":beanObj.getCedRetenType();
		obj[13] = StringUtils.isEmpty(beanObj.getSpRetro()) ? "" : beanObj.getSpRetro();
		obj[14] = StringUtils.isEmpty(beanObj.getNo_Insurer()) ? "0" : beanObj.getNo_Insurer();
		obj[15] = StringUtils.isEmpty(beanObj.getMaxLimit_Product()) ? "0" : beanObj.getMaxLimit_Product();
		obj[16] =StringUtils.isEmpty(beanObj.getLimitOurShare()) ? "0": beanObj.getLimitOurShare();
		obj[17] = StringUtils.isEmpty(beanObj.getLimitOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOurShare(), beanObj.getExchRate());
		obj[18] =StringUtils.isEmpty(beanObj.getEpiAsPerOffer()) ? "0": beanObj.getEpiAsPerOffer();
		obj[19] = StringUtils.isEmpty(beanObj.getEpiAsPerOffer())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerOffer(), beanObj.getExchRate());
		obj[20] =StringUtils.isEmpty(beanObj.getEpiAsPerShare()) ? "0": beanObj.getEpiAsPerShare();
		obj[21] = StringUtils.isEmpty(beanObj.getEpiAsPerShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getEpiAsPerShare(), beanObj.getExchRate());
		obj[22] =StringUtils.isEmpty(beanObj.getXlcostOurShare()) ? "0": beanObj.getXlcostOurShare();
		obj[23] = StringUtils.isEmpty(beanObj.getXlcostOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getXlcostOurShare(), beanObj.getExchRate());
		obj[24] =StringUtils.isEmpty(beanObj.getLimitPerVesselOC()) ? "0": beanObj.getLimitPerVesselOC();
		obj[25] = StringUtils.isEmpty(beanObj.getLimitPerVesselOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0"	: getDesginationCountry(beanObj.getLimitPerVesselOC(), beanObj.getExchRate());
		obj[26] =StringUtils.isEmpty(beanObj.getLimitPerLocationOC()) ? "0": beanObj.getLimitPerLocationOC();
		obj[27] = StringUtils.isEmpty(beanObj.getLimitPerLocationOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitPerLocationOC(), beanObj.getExchRate());
		obj[28] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC()) ? "0": beanObj.getTreatyLimitsurplusOC();
		obj[29] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOC())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOC(), beanObj.getExchRate());
		obj[30] =StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare()) ? "0": beanObj.getTreatyLimitsurplusOurShare();
		obj[31] = StringUtils.isEmpty(beanObj.getTreatyLimitsurplusOurShare())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getTreatyLimitsurplusOurShare(), beanObj.getExchRate());
		obj[32] =StringUtils.isEmpty(beanObj.getLimitOrigCurPml()) ? "0": beanObj.getLimitOrigCurPml();
		obj[33] = StringUtils.isEmpty(beanObj.getLimitOrigCurPml())|| StringUtils.isEmpty(beanObj.getExchRate()) ? "0": getDesginationCountry(beanObj.getLimitOrigCurPml(), beanObj.getExchRate());
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
		obj[47]=StringUtils.isEmpty(beanObj.getPml()) ? "": beanObj.getPml();
		obj[48]=StringUtils.isEmpty(beanObj.getPmlPercent()) ? "0.00": beanObj.getPmlPercent();
		obj[49] = beanObj.getProposal_no();
		obj[50]=endNo;
		
		
		
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object[] insertHomePositionMasterAruguments(final RiskDetailsBean beanObj, final String pid,	final Object args2, final boolean amendId,String renewalStatus) {
		String sectionNo="",bouquetno="";
		if("2".equals(beanObj.getProduct_id())) {
			//if(StringUtils.isBlank(beanObj.getSectionNo())) {
				String query=getQuery("GET_MAX_SECTION_NO_DET");
				sectionNo=this.mytemplate.queryForObject(query, String.class, new Object[] {beanObj.getProposalNo()});
				beanObj.setSectionNo(sectionNo);
			//}
		}
		if(StringUtils.isBlank(beanObj.getBouquetNo()) && "Y".equals(beanObj.getBouquetModeYN())) {
			String query=getQuery("GET_BOUQUET_NO_SEQ");
			bouquetno=this.mytemplate.queryForObject(query, String.class);
			beanObj.setBouquetNo(bouquetno);
		}
		Object[] obj = new Object[30];
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
			//obj[18] = "1";
			obj[18] = StringUtils.isEmpty(beanObj.getLayerProposalNo()) ? "1" : "0";
		}
		obj[15] = StringUtils.isNotBlank(beanObj.getBaseLoginID())?beanObj.getBaseLoginID():beanObj.getLoginId();
		//obj[17] = StringUtils.isEmpty(beanObj.getRenewal_contract_no()) ? "": beanObj.getRenewal_contract_no();
		obj[17] = StringUtils.isEmpty(beanObj.getLayerProposalNo()) ?StringUtils.isEmpty(beanObj.getRenewal_contract_no()) ? "" : beanObj.getRenewal_contract_no():"";
		obj[19] = StringUtils.isBlank(beanObj.getBroker())?"63":beanObj.getBroker();
		obj[20] = beanObj.getBranchCode();
		obj[21] = StringUtils.isBlank(beanObj.getRetroType())?"":beanObj.getRetroType();
		obj[22] = beanObj.getLoginId();
		obj[23] = "";
		obj[24] = "";
		obj[25] = StringUtils.isEmpty(beanObj.getContractListVal())?"":beanObj.getContractListVal();
		obj[26] = beanObj.getBouquetModeYN();
		obj[27] = beanObj.getBouquetNo();
		obj[28] = beanObj.getUwYearTo();
		obj[29] = beanObj.getSectionNo();
		logger.info("Args[]=>" + StringUtils.join(obj,","));
		return obj;
	}

	public Object getDesginationCountry(final String limitOrigCur,final String ExchangeRate) {
		String output="0.0";
		try{
			double origCountryVal=0.0;
			if(limitOrigCur!=null){
				String val = limitOrigCur.replaceAll(",", "");
				if (!("".equalsIgnoreCase(val))&& Double.parseDouble(val) != 0) {
					origCountryVal = Double.parseDouble(val) / Double.parseDouble(ExchangeRate);
					//final DecimalFormat myFormatter = new DecimalFormat("###.##");
					//final double dround = Math.round(origCountryVal * 100.0) / 100.0;
					//output = Double.parseDouble(myFormatter.format(dround));
					output = DropDownControllor.formatter(Double.toString(origCountryVal).toString()).replaceAll(",", "");
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");

		}
		return output;
	}

	public String getShortname(RiskDetailsBean bean) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] { bean.getBranchCode()}, String.class);
		return Short;
	}

	public void insertCrestaDetails(RiskDetailsBean bean) {
		try {
			if(StringUtils.isBlank(bean.getAmendId())){
				bean.setAmendId("0");
			}
			String query="";
			Object[] obj=null;
				query=getQuery("DELETE_CRESTA_MAIN_DETAILS");
				 obj = new Object[3];
				obj[0]=bean.getProposal_no();
				obj[1]=bean.getAmendId();
				obj[2]=bean.getBranchCode();
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(obj, ","));
				this.mytemplate.update(query,obj);
			query=getQuery("MOVE_TO_CRESTA_MAIN_TABLE");
			obj = new Object[12];
			for(int i=0;i<bean.getCrestaId().size();i++){
				if(StringUtils.isNotBlank(bean.getTerritoryCode().get(i))){
				obj[0]=bean.getContractNo();
				obj[1]=bean.getProposal_no();
				obj[2]=bean.getAmendId();
				obj[3]=bean.getDepartmentId();
				obj[4]=bean.getCrestaId().get(i);
				obj[5]=bean.getCrestaName().get(i);
				obj[6]=bean.getCurrencyId().get(i);
				obj[7]=bean.getAccRisk().get(i);
				obj[8]=bean.getAccumulationDate().get(i);
				obj[9]=bean.getBranchCode();
				obj[10]=bean.getTerritoryCode().get(i);
				obj[11]= bean.getScaleSNo().get(i);
				logger.info("Query=>"+query);
				logger.info("Args=>"+StringUtils.join(obj, ","));
				this.mytemplate.update(query,obj);
			}
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Map<String, Object>> getCrestaDetailList(RiskDetailsBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		Object[] obj = null;
		String query ="";
		try {
			
			query=getQuery("GET_CRESTA_DETAIL_LIST");
			obj=new Object[3];
			if(StringUtils.isBlank(bean.getAmendId())){
				bean.setAmendId("0");
			}
			obj[0]=bean.getProposal_no();
			obj[1]=bean.getAmendId();
			obj[2]=bean.getBranchCode();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> territoryId = new ArrayList<String>();
				List<String> crestaId = new ArrayList<String>();
				List<String> crestaName = new ArrayList<String>();
				List<String> currencyId = new ArrayList<String>();
				List<String> accRisk = new ArrayList<String>();
				List<String> accDate = new ArrayList<String>();
				List<String> scaleSNo = new ArrayList<String>();
				List<List<Map<String,Object>>> crestaIDList=new ArrayList<List<Map<String,Object>>>();
				List<List<Map<String,Object>>> crestaNameList=new ArrayList<List<Map<String,Object>>>();
				List<List<Map<String,Object>>> crestaIDList1=new ArrayList<List<Map<String,Object>>>();
				List<String>cur=new ArrayList<String>();
				
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					territoryId.add(insMap.get("TERITORY_CODE")==null?"":insMap.get("TERITORY_CODE").toString());
					crestaId.add(insMap.get("CRESTA_ID")==null?"":insMap.get("CRESTA_ID").toString());
					crestaName.add(insMap.get("CRESTA_NAME")==null?"":insMap.get("CRESTA_NAME").toString());
					currencyId.add(insMap.get("CURRENCY")==null?"":insMap.get("CURRENCY").toString());
					accRisk.add(insMap.get("ACC_RISK")==null?"":insMap.get("ACC_RISK").toString());
					accDate.add(insMap.get("ACCUM_DATE")==null?"":insMap.get("ACCUM_DATE").toString());
					scaleSNo.add(insMap.get("SNO")==null?"":insMap.get("SNO").toString());
					//bean.setAccumDate(insMap.get("ACCUM_DATE")==null?"":insMap.get("ACCUM_DATE").toString());
					crestaIDList.add(new DropDownControllor().getCrestaIDList(bean.getBranchCode(),insMap.get("TERITORY_CODE")==null?"":insMap.get("TERITORY_CODE").toString()));
					crestaNameList.add(new DropDownControllor().getCrestaNameList(bean.getBranchCode(),insMap.get("CRESTA_ID")==null?"":insMap.get("CRESTA_ID").toString()));
					crestaIDList1.add(new ArrayList<Map<String,Object>>());
				}
				bean.setTerritoryCode(territoryId);
				bean.setCrestaId(crestaId);
				bean.setCrestaName(crestaName);
				bean.setCurrencyId(currencyId);
				bean.setAccRisk(accRisk);
				bean.setAccumulationDate(accDate);
				bean.setCrestaIDList(crestaIDList);
				bean.setCrestaNameList(crestaNameList);
				bean.setCrestaList1(crestaIDList1);
				bean.setScaleSNo(scaleSNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
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

	public List<Map<String, Object>> getScaleCommissionList(RiskDetailsBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		String query="";
		 List<String> bonusSno = new ArrayList<String>();
         List<String> bonusFrom = new ArrayList<String>();
         List<String> bonusTo = new ArrayList<String>();
         String proposalNo = bean.getProposal_no();
         List<String> bonusLowClaimBonus = new ArrayList<String>();
         List<String> scalemaxpartpercent = new ArrayList<String>();
         Object args[]=null;
         int count =0;
		try{
				 if("scale".equalsIgnoreCase(bean.getPageFor())){
					 args = new Object[4];
						args[0] = proposalNo;
						args[1] = bean.getBranchCode();
						args[2] ="SSC";
						args[3] ="SSC2";
					query =getQuery("BONUS_MAIN_SELECT");
					result = this.mytemplate.queryForList(query,args);
					if(CollectionUtils.isEmpty(result)) {
						args[0] = bean.getReferenceNo();
						query =getQuery("BONUS_MAIN_SELECT_REFERENCE");
						result = this.mytemplate.queryForList(query,args);
					}
				 }else {
					 args = new Object[3];
						args[0] = proposalNo;
						args[1] = bean.getBranchCode();
						args[2] ="LPC";
					
					 query =getQuery("BONUS_MAIN_SELECT_LPC");
						result = this.mytemplate.queryForList(query,args);
						if(CollectionUtils.isEmpty(result)) {
							args[0] = bean.getReferenceNo();
							query =getQuery("BONUS_MAIN_SELECT_REFERENCE_LPC");
							result = this.mytemplate.queryForList(query,args);
						}
				 }
					//}
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               bonusSno.add(tempMap.get("LCB_ID")==null?"":tempMap.get("LCB_ID").toString());
		               bonusFrom.add(tempMap.get("LCB_FROM")==null?"":DropDownControllor.formattereight(tempMap.get("LCB_FROM").toString()));
		               bonusTo.add(tempMap.get("LCB_TO")==null?"":DropDownControllor.formattereight(tempMap.get("LCB_TO").toString()));
		               bonusLowClaimBonus.add(tempMap.get("LCB_PERCENTAGE")==null?"":DropDownControllor.formatter(tempMap.get("LCB_PERCENTAGE").toString()));
		               scalemaxpartpercent.add(tempMap.get("SCALE_MAX_PART_PERCENT")==null?"":DropDownControllor.formatter(tempMap.get("SCALE_MAX_PART_PERCENT").toString()));
		               if(!"scale".equalsIgnoreCase(bean.getPageFor())){
		               bean.setBonusTypeId(tempMap.get("LCB_TYPE")==null?"":tempMap.get("LCB_TYPE").toString());
		               }
		               bean.setQuotaShare(tempMap.get("QUOTA_SHARE")==null?"":tempMap.get("QUOTA_SHARE").toString());
		               bean.setBonusremarks(tempMap.get("REMARKS")==null?"":tempMap.get("REMARKS").toString());
		               bean.setScfistpc(tempMap.get("FIRST_PROFIT_COMM")==null?"":tempMap.get("FIRST_PROFIT_COMM").toString());
		               bean.setScprofitMont(tempMap.get("FPC_DURATION_TYPE")==null?"":tempMap.get("FPC_DURATION_TYPE").toString());
		               bean.setScsubpc(tempMap.get("SUB__PROFIT_COMM")==null?"":tempMap.get("SUB__PROFIT_COMM").toString());
		               bean.setScsubProfitMonth(tempMap.get("SPC_DURATION_TYPE")==null?"":tempMap.get("SPC_DURATION_TYPE").toString());
		               bean.setScsubSeqCalculation(tempMap.get("SUB_SEC_CAL")==null?"":tempMap.get("SUB_SEC_CAL").toString());
		               bean.setFpcType(tempMap.get("FPC_TYPE")==null?"":tempMap.get("FPC_TYPE").toString());
		               bean.setFpcfixedDate(tempMap.get("FPC_FIXED_DATE")==null?"":tempMap.get("FPC_FIXED_DATE").toString());
				}
	               bean.setScaleSNo(bonusSno);
	               bean.setScaleFrom(bonusFrom);
	               bean.setScaleTo(bonusTo);
	               bean.setScaleLowClaimBonus(bonusLowClaimBonus);
	               bean.setScalemaxpartpercent(scalemaxpartpercent);
	           GetSlidingScaleMethodInfo(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private void GetSlidingScaleMethodInfo(RiskDetailsBean bean) {
		 Object args[]=null;
			List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
			String query="";
		try {
			args = new Object[4];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			args[2] ="SSC";
			args[3] ="SSC1";
			
				query =getQuery("SELECT_SLIDING_SCALE_METHOD_INFO");
				result = this.mytemplate.queryForList(query,args);
				if(CollectionUtils.isEmpty(result)) {
					args[0] = bean.getReferenceNo();
					query =getQuery("SELECT_SLIDING_SCALE_METHOD_INFO_REF");
					result = this.mytemplate.queryForList(query,args);
				}
				//}
			for(int i=0;i<result.size();i++){
			       Map<String,Object> tempMap = result.get(i);

			       bean.setProvisionCom(tempMap.get("PROVISIONAL_COMMISIION")==null?"":tempMap.get("PROVISIONAL_COMMISIION").toString());
	               bean.setScalementhod(tempMap.get("SC_METHOD_TYPE")==null?"":tempMap.get("SC_METHOD_TYPE").toString());
	               bean.setScaleminRatio(tempMap.get("SC_MIN_LOSS_RATIO")==null?"":tempMap.get("SC_MIN_LOSS_RATIO").toString());
	               bean.setScalemaxRatio(tempMap.get("SC_MAX_LOSS_RATIO")==null?"":tempMap.get("SC_MAX_LOSS_RATIO").toString());
	               bean.setScalecombine(tempMap.get("SC_COMBINE_LOSS_RATIO")==null?"":tempMap.get("SC_COMBINE_LOSS_RATIO").toString());
	               bean.setScalebanding(tempMap.get("SC_BANDING_STEP")==null?"":tempMap.get("SC_BANDING_STEP").toString());
	               bean.setScaledigit(tempMap.get("SC_NO_OF_DIGIT")==null?"":tempMap.get("SC_NO_OF_DIGIT").toString());
	               bean.setScalelossratioFrom(tempMap.get("LCB_FROM")==null?"":tempMap.get("LCB_FROM").toString());
	               bean.setScalelossratioTo(tempMap.get("LCB_TO")==null?"":tempMap.get("LCB_TO").toString());
	               bean.setScaledeltalossratio(tempMap.get("DELTA_LOSS_RATIO")==null?"":tempMap.get("DELTA_LOSS_RATIO").toString());
	               bean.setScaledeltacommission(tempMap.get("LCB_PERCENTAGE")==null?"":tempMap.get("LCB_PERCENTAGE").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ScaleCommissionInsert(RiskDetailsBean bean) {
		MoveBonus(bean);
		return null;
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

	private void moveBonusEmptyData(RiskDetailsBean bean) {
		try{
			if(StringUtils.isBlank(bean.getAmendId())){

				bean.setAmendId("0");
			}
			deleteMaintable(bean);
			String query =getQuery("BONUS_MAIN_INSERT_PTTY");
			Object args[]=new Object[22];
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
		           args[21]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
		           logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
	}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void MoveBonus(RiskDetailsBean bean) {
		if(StringUtils.isBlank(bean.getAmendId())){
			bean.setAmendId("0");
		}
        deleteMaintable(bean);
        if(StringUtils.isBlank(bean.getProposal_no()) && StringUtils.isBlank(bean.getReferenceNo())) {
        	String referenceNo="";
        	String query=getQuery("GET_REFERENCE_NO_SEQ");
        	referenceNo=this.mytemplate.queryForObject(query, String.class);
        	bean.setReferenceNo(referenceNo);
        }
       
        String query =getQuery("BONUS_MAIN_INSERT_PTTY");
		Object args[]=new Object[25];
		for(int i=0;i<bean.getScaleFrom().size();i++){
			if("MB".equals(bean.getScalementhod()) || (StringUtils.isNotBlank(bean.getScaleFrom().get(i)) && StringUtils.isNotBlank(bean.getScaleTo().get(i)) &&StringUtils.isNotBlank(bean.getScaleLowClaimBonus().get(i)) )){
		           args[0] =bean.getProposal_no();
		           args[1] = bean.getContractNo();
		           args[2] = bean.getProduct_id();
		           if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   args[3] = "SSC2";
		           }
		           else{
		        	   args[3] = bean.getBonusTypeId();  
		           }
		           if(!"MB".equals(bean.getScalementhod())) {
		           args[9] = bean.getScaleSNo().get(i).replace(",", "");
		           args[4] = bean.getScaleFrom().get(i).replace(",", "");
		           args[5] = bean.getScaleTo().get(i).replace(",", "");
		           args[6] =bean.getScaleLowClaimBonus().get(i).replace(",", "");
		           }else {
		        	   args[9] = "";
			           args[4] = "";
			           args[5] = "";
			           args[6] ="";
		           }
		        	   
		           args[7] = bean.getLoginId();
		           args[8] = bean.getBranchCode();
		         
		           if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   args[10] ="SSC";
		           }
		           else{
		        	   args[10]="LPC";
		           }
		           args[11] = bean.getAmendId();
		           args[12] = bean.getDepartmentId();
		           args[13] = StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();
		           if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   args[14] =bean.getQuotaShare();
		           }
		           else{
		        	   args[14]="";
		           }
		           args[15] =StringUtils.isEmpty(bean.getBonusremarks()) ? "" :bean.getBonusremarks();
		           args[16] =StringUtils.isEmpty(bean.getScfistpc()) ? "" :bean.getScfistpc();
		           args[17] =StringUtils.isEmpty(bean.getScprofitMont()) ? "" :bean.getScprofitMont();
		           args[18] =StringUtils.isEmpty(bean.getScsubpc()) ? "" :bean.getScsubpc();
		           args[19] =StringUtils.isEmpty(bean.getScsubProfitMonth()) ? "" :bean.getScsubProfitMonth();
		           args[20] =StringUtils.isEmpty(bean.getScsubSeqCalculation()) ? "" :bean.getScsubSeqCalculation();
		           args[21]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
		           if("scale".equalsIgnoreCase(bean.getPageFor())){
		        	   args[22]="";
		           }
		           else{
		        	   args[22] =bean.getScalemaxpartpercent().get(i); 
		           }
		           args[23] =bean.getFpcType();
		           args[24] =bean.getFpcfixedDate();
		           logger.info("Query=>"+query);
		           logger.info("Args=>"+StringUtils.join(args, ","));
				   this.mytemplate.update(query,args);
				   if("MB".equals(bean.getScalementhod())) {
					   break;
				   }
				}
	
        }
		 if("scale".equalsIgnoreCase(bean.getPageFor())){
			  InsertSlidingScaleMentodInfo(bean);
		  }
		
	}
	
	
	private void InsertSlidingScaleMentodInfo(RiskDetailsBean bean) {
		try {
			 String query =getQuery("INSERT_SC_METHOD_INFO");
			 Object args[]=new Object[23];
			   args[0] =bean.getProposal_no();
	           args[1] = bean.getContractNo();
	           args[2] = bean.getProduct_id();
	           args[3] = "SSC1";  
	           args[4] = bean.getProvisionCom();
	           args[5] = bean.getScalementhod();
	           args[6] = bean.getScaleminRatio();
	           args[7] = bean.getScalemaxRatio();
	           args[8] = bean.getScalecombine();
	           args[9] = bean.getScalebanding();
	           args[10] = bean.getScaledigit();
	           args[11] = bean.getLoginId();
	           args[12] = bean.getBranchCode();
	           args[13] ="SSC";
	           args[14] = bean.getAmendId();
	           args[15] = bean.getDepartmentId();
	           args[16] = StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();
	           args[17]="";
	           args[18]=StringUtils.isBlank(bean.getReferenceNo())?"":bean.getReferenceNo();
	           if("MB".equals(bean.getScalementhod())) {
	        	   args[19]=bean.getScalelossratioFrom();
	        	   args[20]=bean.getScalelossratioTo();
	        	   args[21]=bean.getScaledeltalossratio();
	        	   args[22]=bean.getScaledeltacommission();
	        	   
	           }else {
	        	   args[19]="";
	        	   args[20]="";
	        	   args[21]="";
	        	   args[22]="";
	           }
	           logger.info("Query=>"+query);
	           logger.info("Args=>"+StringUtils.join(args, ","));
			   this.mytemplate.update(query,args);
			 
		}catch (Exception e) {
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
			}else if(StringUtils.isBlank(bean.getProposal_no())) {
				query1 =getQuery("BONUS_MAIN_DELETE3");
				 arg = new Object[4];
				 arg[0] = bean.getReferenceNo();
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

	public int getBonusListCount(RiskDetailsBean bean, String type) {
		String query ="";
		Object args[]=null;
		int result=0;
		try{
			query =getQuery("BONUS_COUNT_MAIN");
			args = new Object[5];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			 if("scale".equalsIgnoreCase(type)){
		           args[2] ="SSC";
		           }
		      else{
		        	   args[2]="LPC";
		         }
			args[3] = bean.getAmendId();
			args[4] = StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo();;
			result = this.mytemplate.queryForInt(query,args);
		//}
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return result;
	}

	public String CheckCrestaValue(RiskDetailsBean bean, String val, String id, String countryId) {
		String query1="";
		Object arg[]=null;
		String status="";
		try{
			if("id".equalsIgnoreCase(id)){
			 query1 =getQuery("CRESTA_ID_CHECK");
			 arg = new Object[2];
			 arg[0] =val;
			 arg[1] = bean.getBranchCode();
			 status = (String) this.mytemplate.queryForObject(query1,arg,String.class);
			}
			else if("name".equalsIgnoreCase(id)){
				 query1 =getQuery("CRESTA_NAME_CHECK");
				 arg = new Object[2];
				 arg[0] =val;
				 arg[1] = bean.getBranchCode();
				 status = (String) this.mytemplate.queryForObject(query1,arg,String.class);
				}
			else if("currency".equalsIgnoreCase(id)){
				 query1 =getQuery("CRESTA_CURRENCY_CHECK");
				 arg = new Object[3];
				 arg[0] =val.toUpperCase();
				 arg[1] =bean.getBranchCode();
				 arg[2] =countryId;
				 status = (String) this.mytemplate.queryForObject(query1,arg,String.class);
				}
				else if("teritoryid".equalsIgnoreCase(id)){
					 query1 =getQuery("CRESTA_TERRITORY_CHECK");
					 arg = new Object[2];
					 arg[0] =val;
					 arg[1] =bean.getBranchCode();
					 status = (String) this.mytemplate.queryForObject(query1,arg,String.class);
					}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public String insertProfitCommission(RiskDetailsBean bean) {
		String sno=bean.getProfitSno();
		String query="";
		Object args[];
		try{
			mainDelete(bean);
			
			query = getQuery("COMMISSION_INSERT");
			args=new Object[12];
			for(int i=0;i<bean.getCommissionSNo().size();i++){
				if(StringUtils.isNotBlank(bean.getCommissionFrom().get(i))&&StringUtils.isNotBlank(bean.getCommissionTo().get(i))&&StringUtils.isNotBlank(bean.getCommissionPer().get(i))){
				args[0]=bean.getCommissionSNo().get(i);
				args[1]=bean.getCommissionFrom().get(i);
				args[2]=bean.getCommissionTo().get(i);
				args[3]=bean.getCommissionPer().get(i);
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
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
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

	
	public List<Map<String, Object>> ProfitCommissionList(RiskDetailsBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		String query="";
		 List<String> profitSno = new ArrayList<String>();
         List<String> from = new ArrayList<String>();
         List<String> to = new ArrayList<String>();
         List<String> com = new ArrayList<String>();
         Object args[]=null;
         try{
        	  query =getQuery("COMMISSION_TYPE_LIST");
        	  	if(StringUtils.isNotBlank(bean.getContractNo())){
		        	  	query+=" AND CONTRACT_NO=?";
		        	  	args = new Object[4];
		        	  
		    			args[0] = bean.getProposal_no();
		 				args[1] = bean.getBranchCode();
		 				args[2] = bean.getCommissionType();
		 				args[3] = bean.getContractNo();
        	  	}
        	  	else{
		        	  args = new Object[3];
		  				args[0] = bean.getProposal_no();
						args[1] = bean.getBranchCode();
						args[2] = bean.getCommissionType();
        	  	}
        	  	query+=" order by SNO";
				result = this.mytemplate.queryForList(query,args);
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               profitSno.add(tempMap.get("S_NO")==null?"":tempMap.get("S_NO").toString());
		               from.add(tempMap.get("COMM_FROM")==null?"":DropDownControllor.formatter(tempMap.get("COMM_FROM").toString()));
		               to.add(tempMap.get("COMM_TO")==null?"":DropDownControllor.formatter(tempMap.get("COMM_TO").toString()));
		               com.add(tempMap.get("PROFIT_COMM")==null?"":DropDownControllor.formatter(tempMap.get("PROFIT_COMM").toString()));
				}
				bean.setCommissionTo(to);
				bean.setCommissionFrom(from);
				bean.setCommissionSNo(profitSno);
				bean.setCommissionPer(com);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int getProfitListCount(RiskDetailsBean bean) {
		String query ="";
		Object args[]=null;
		int result=0;
		try{
			query =getQuery("PROFIT_COUNT_ARCH");
			args = new Object[2];
			args[0] = bean.getProposal_no();
			args[1] = bean.getBranchCode();
			result = this.mytemplate.queryForInt(query,args);
			if(result<=0){
				if(StringUtils.isBlank(bean.getAmendId())){
					bean.setAmendId("0");
				}
				query =getQuery("PROFIT_COUNT_MAIN");
				args = new Object[3];
				args[0] = bean.getProposal_no();
				args[1] = bean.getBranchCode();
				args[2] = bean.getAmendId();
				result = this.mytemplate.queryForInt(query,args);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private void insertProfitCommissionMain(RiskDetailsBean beanObj,String type) {
		if(!"1".equalsIgnoreCase(beanObj.getShare_Profit_Commission())){
				mainDelete(beanObj);
				profitMainEmptyInsert(beanObj);
			}
		profitUpdate(beanObj);
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

	
	public void getprofitCommissionDelete(RiskDetailsBean bean) {
		try{
				String query=getQuery("PROFIT_COMMISSION_DELETE");
				Object args[] = new Object[3];
				args[0] = bean.getProposal_no();
				args[1] = bean.getBranchCode();
				args[2] = bean.getProfitSno();
				this.mytemplate.update(query,args);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getprofitCommissionEdit(RiskDetailsBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("PROFIT_COMMISSION_EDIT");
			Object args[] = new Object[3];
				args[0] = bean.getProposal_no();
				args[1] = bean.getBranchCode();
				args[2] = bean.getProfitSno();
				result =this.mytemplate.queryForList(query,args);
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               bean.setProfitSno(tempMap.get("S_NO")==null?"":tempMap.get("S_NO").toString());
		               bean.setManagementExpenses(tempMap.get("MANAGEMENT_EXPENSES")==null?"":tempMap.get("MANAGEMENT_EXPENSES").toString());
		               bean.setLossCF(tempMap.get("LOSS")==null?"":tempMap.get("LOSS").toString());
		               bean.setFistpc(tempMap.get("FIRST_PROFIT_COM_AFTER")==null?"":tempMap.get("FIRST_PROFIT_COM_AFTER").toString());
		               bean.setProfitMont(tempMap.get("PROFIT_MONTHS")==null?"":tempMap.get("PROFIT_MONTHS").toString());
		               bean.setProfitQuarters(tempMap.get("PROFIT_QUARTERS")==null?"":tempMap.get("PROFIT_QUARTERS").toString());
		               bean.setProfitYear(tempMap.get("PROFIT_YEAR")==null?"":tempMap.get("PROFIT_YEAR").toString());
		               bean.setProfitCommission(tempMap.get("PROFIT_COMMISSION")==null?"":tempMap.get("PROFIT_COMMISSION").toString());
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getprofitCommissionEnable(RiskDetailsBean bean, String type) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		String profitComm="";
		String subclass="";
		String status="Y";
		try{
			if(StringUtils.isBlank(bean.getBaseLayer())){
				String qry = getQuery("GET_BASE_PROPOSAL_NO");
				Object args[]=new Object[2];
				args[0] = bean.getProposal_no();
				args[1] = bean.getBranchCode();
				String res = this.mytemplate.queryForObject(qry, args,String.class); 
				bean.setBaseLayer(res);
			}
			if("profit".equalsIgnoreCase(type)){
			String query=getQuery("PROFIT_COMMISSION_ENABLE");
			Object arg[]=new Object[2];
			arg[0] =bean.getBaseLayer();
			arg[1] = bean.getBaseLayer();
			result =this.mytemplate.queryForList(query,arg);
			for(int i=0;i<result.size();i++){
	               Map<String,Object> tempMap = result.get(i);
	               profitComm=(tempMap.get("RSK_PROFIT_COMM")==null?"":tempMap.get("RSK_PROFIT_COMM").toString());
	               subclass=(tempMap.get("RSK_COMBIN_SUB_CLASS")==null?"":tempMap.get("RSK_COMBIN_SUB_CLASS").toString());
			}
			}
			if("loss".equalsIgnoreCase(type)){
				String query=getQuery("PROFIT_COMMISSION_ENABLE_LOSS");
				Object arg[]=new Object[2];
				arg[0] =bean.getBaseLayer();
				arg[1] = bean.getBaseLayer();
				result =this.mytemplate.queryForList(query,arg);
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               profitComm=(tempMap.get("RSK_LOSS_PART_CARRIDOR")==null?"":tempMap.get("RSK_LOSS_PART_CARRIDOR").toString());
		               subclass=(tempMap.get("RSK_LOSS_COMBIN_SUB_CLASS")==null?"":tempMap.get("RSK_LOSS_COMBIN_SUB_CLASS").toString());
				}
				
				}
			if("cresta".equalsIgnoreCase(type)){
				String query=getQuery("PROFIT_COMMISSION_ENABLE_CRESTA");
				Object arg[]=new Object[2];
				arg[0] =bean.getBaseLayer();
				arg[1] = bean.getBaseLayer();
				result =this.mytemplate.queryForList(query,arg);
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               profitComm=(tempMap.get("RSK_CREASTA_STATUS")==null?"":tempMap.get("RSK_CREASTA_STATUS").toString());
		               subclass=(tempMap.get("RSK_CRESTA_COMBIN_SUB_CLASS")==null?"":tempMap.get("RSK_CRESTA_COMBIN_SUB_CLASS").toString());
				}
				
				}
			if("slide".equalsIgnoreCase(type)){
				String query=getQuery("PROFIT_COMMISSION_ENABLE_SLIDE");
				Object arg[]=new Object[2];
				arg[0] =bean.getBaseLayer();
				arg[1] = bean.getBaseLayer();
				result =this.mytemplate.queryForList(query,arg);
				for(int i=0;i<result.size();i++){
		               Map<String,Object> tempMap = result.get(i);
		               profitComm=(tempMap.get("RSK_SLADSCALE_COMM")==null?"":tempMap.get("RSK_SLADSCALE_COMM").toString());
		               subclass=(tempMap.get("RSK_SLIDE_COMBIN_SUB_CLASS")==null?"":tempMap.get("RSK_SLIDE_COMBIN_SUB_CLASS").toString());
				}
				
				}
			if("2".equalsIgnoreCase(subclass) || result.size()<=0){
				status="N";	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
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

	public int CommissionTypeCount(RiskDetailsBean bean) {
		int count=0;
		try{
			String query =getQuery("COMMISSION_TYPE_COUNT");
			Object args[]=new Object[3];
			args[0]=bean.getProposal_no();
			args[1]=bean.getBranchCode();
			args[2]= bean.getCommissionType();
			count = this.mytemplate.queryForInt(query,args);
		}
		catch(Exception e){
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
				logger.info("Query=>"+query);
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

	public void insertCedentRetention(RiskDetailsBean beanObj, String pid) {
		try {
			String  deleteQuery=getQuery("DELETE_RET_DETAILS");
			Object[] dobj= new Object[2];
			dobj[0]=beanObj.getProposal_no();
			dobj[1]="0";
			logger.info("Query=>"+deleteQuery);
			logger.info("Args=>"+StringUtils.join(dobj, ","));
			this.mytemplate.update(deleteQuery, dobj);
			String query=getQuery("INSERT_RET_DETAILS");
			String amendId=this.mytemplate.queryForObject("(SELECT NVL(MAX(AMEND_ID)+1,0) FROM TTRN_CEDENT_RET WHERE PROPOSAL_NO=?)", new Object[]{beanObj.getProposal_no()},String.class);
			if(StringUtils.isNotBlank(beanObj.getRetentionYN()) && "Y".equalsIgnoreCase(beanObj.getRetentionYN())){
			for(int i=0;i<beanObj.getRetSNo().size();i++){
				Object[] obj= new Object[20];
				obj[0]=beanObj.getProposal_no();
				obj[1]=beanObj.getContNo();
				obj[2]="0";
				obj[3]=beanObj.getDepartId();
				obj[4]=pid;
				obj[5]=amendId;
				obj[6]=i+1;
				obj[7]=beanObj.getCoverdepartId().get(i);
				obj[8]=beanObj.getCoversubdepartId().get(i);
				obj[9]=beanObj.getRetBusinessType().get(i);
				obj[10]=beanObj.getRetType().get(i);
				obj[11]=beanObj.getRetBasis().get(i);
				obj[12]=beanObj.getFirstretention().get(i).replace(",", "");
				obj[13]=beanObj.getSecondretention().get(i).replace(",", "");
				obj[14]=beanObj.getRetTreatyFST().get(i).replace(",", "");
				obj[15]=beanObj.getRetTreatySST().get(i).replace(",", "");
				obj[16]=beanObj.getRetEventFST().get(i).replace(",", "");
				obj[17]=beanObj.getRetEventSST().get(i).replace(",", "");
				obj[18]=beanObj.getLoginId();
				obj[19]=beanObj.getBranchCode();
				logger.info("Query=>"+query);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				this.mytemplate.update(query, obj);
				
				
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void getGetRetDetails(RiskDetailsBean bean) {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		try {
			
			String query=getQuery("GET_RET_DETAILS");
			Object[] obj= new Object[2];
			obj[0]=bean.getProposal_no();
			obj[1]="0";
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				List<String> coversubdepartId = new ArrayList<String>();
				List<String> retType = new ArrayList<String>();
				List<String> retBasis = new ArrayList<String>();
				List<String> retBusinessType= new ArrayList<String>();
				List<String> firstretention = new ArrayList<String>();
				List<String> secondretention = new ArrayList<String>();
				List<String> retTreatyFST = new ArrayList<String>();
				List<String> retTreatySST = new ArrayList<String>();
				List<String> retEventFST = new ArrayList<String>();
				List<String> retEventSST = new ArrayList<String>();
				List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					coverdepartId.add(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString());
					coversubdepartId.add(insMap.get("RSK_SUBCLASS")==null?"":insMap.get("RSK_SUBCLASS").toString());
					retBusinessType.add(insMap.get("RSK_TYPE")==null?"":insMap.get("RSK_TYPE").toString());
					retType.add(insMap.get("RSK_RETTYPE")==null?"":insMap.get("RSK_RETTYPE").toString());
					retBasis.add(insMap.get("RSK_BASISTYPE")==null?"":insMap.get("RSK_BASISTYPE").toString());
					firstretention.add(insMap.get("RSK_FIRST_RET_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_FIRST_RET_OC").toString()));
					secondretention.add(insMap.get("RSK_SECOND_RET_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_SECOND_RET_OC").toString()));
					retTreatyFST.add(insMap.get("RSK_RET_TL_FST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_TL_FST_OC").toString()));
					retTreatySST.add(insMap.get("RSK_RET_TL_SST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_TL_SST_OC").toString()));
					retEventFST.add(insMap.get("RSK_RET_EL_FST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_EL_FST_OC").toString()));
					retEventSST.add(insMap.get("RSK_RET_EL_SST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_EL_SST_OC").toString()));
					coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString(),bean.getBranchCode(),bean.getProduct_id()));
				}
				bean.setCoverdepartId(coverdepartId);
				bean.setCoversubdepartId(coversubdepartId);
				bean.setRetType(retType);
				bean.setRetBasis(retBasis);
				bean.setRetBusinessType(retBusinessType);
				bean.setFirstretention(firstretention);
				bean.setSecondretention(secondretention);
				bean.setRetTreatyFST(retTreatyFST);
				bean.setRetTreatySST(retTreatySST);
				bean.setRetEventFST(retEventFST);
				bean.setRetEventSST(retEventSST);
				bean.setCoversubDepartList(coversubdeptList);
				 bean.setRetList(result);
				bean.setLoopcount(Integer.toString(result.size()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void getRetentionDetails(RiskDetailsBean bean) {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		try {
			
			String query=getQuery("GET_RET_BASE_DETAILS");
			Object[] obj= new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCedingCo();
			obj[2]=bean.getUwYear();
			obj[3]=bean.getProduct_id();
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(obj, ","));
			
			result=this.mytemplate.queryForList(query,obj);	
			if(result!=null && result.size()>0){
				List<String> coverdepartId = new ArrayList<String>();
				List<String> coversubdepartId = new ArrayList<String>();
				List<String> retType = new ArrayList<String>();
				List<String> retBasis = new ArrayList<String>();
				List<String> retBusinessType= new ArrayList<String>();
				List<String> firstretention = new ArrayList<String>();
				List<String> secondretention = new ArrayList<String>();
				List<String> retTreatyFST = new ArrayList<String>();
				List<String> retTreatySST = new ArrayList<String>();
				List<String> retEventFST = new ArrayList<String>();
				List<String> retEventSST = new ArrayList<String>();
				List<List<Map<String,Object>>> coversubdeptList=new ArrayList<List<Map<String,Object>>>();
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> insMap = (Map<String, Object>)result.get(i);
					coverdepartId.add(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString());
					coversubdepartId.add(insMap.get("RSK_SUBCLASS")==null?"":insMap.get("RSK_SUBCLASS").toString());
					retBusinessType.add(insMap.get("RSK_TYPE")==null?"":insMap.get("RSK_TYPE").toString());
					retType.add(insMap.get("RSK_RETTYPE")==null?"":insMap.get("RSK_RETTYPE").toString());
					retBasis.add(insMap.get("RSK_BASISTYPE")==null?"":insMap.get("RSK_BASISTYPE").toString());
					firstretention.add(insMap.get("RSK_FIRST_RET_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_FIRST_RET_OC").toString()));
					secondretention.add(insMap.get("RSK_SECOND_RET_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_SECOND_RET_OC").toString()));
					retTreatyFST.add(insMap.get("RSK_RET_TL_FST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_TL_FST_OC").toString()));
					retTreatySST.add(insMap.get("RSK_RET_TL_SST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_TL_SST_OC").toString()));
					retEventFST.add(insMap.get("RSK_RET_EL_FST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_EL_FST_OC").toString()));
					retEventSST.add(insMap.get("RSK_RET_EL_SST_OC")==null?"":DropDownControllor.formatter(insMap.get("RSK_RET_EL_SST_OC").toString()));
					coversubdeptList.add(new DropDownControllor().getSubProfitCentreDropDown(insMap.get("RSK_CLASS")==null?"":insMap.get("RSK_CLASS").toString(),bean.getBranchCode(),bean.getProduct_id()));
				}
				bean.setCoverdepartId(coverdepartId);
				bean.setCoversubdepartId(coversubdepartId);
				bean.setRetType(retType);
				bean.setRetBasis(retBasis);
				bean.setRetBusinessType(retBusinessType);
				bean.setFirstretention(firstretention);
				bean.setSecondretention(secondretention);
				bean.setRetTreatyFST(retTreatyFST);
				bean.setRetTreatySST(retTreatySST);
				bean.setRetEventFST(retEventFST);
				bean.setRetEventSST(retEventSST);
				bean.setCoversubDepartList(coversubdeptList);
				 bean.setRetList(result);
				bean.setLoopcount(Integer.toString(result.size()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public String getUGUWName(String branchCode,String ugcode) {
		String query="";
		String code="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_UG_UNDERWRITER");
			Object args[]=new Object[2];
			args[0] = branchCode;
			args[1] = ugcode;
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				code=map.get("RSK_LEAD_UW_UG")==null?"":map.get("RSK_LEAD_UW_UG").toString();
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return code;
		
	}

	@Override
	public void getcalculateSC(RiskDetailsBean bean) {
        List<String> bonusFrom = new ArrayList<String>();
        List<String> bonusTo = new ArrayList<String>();
        List<String> bonusLowClaimBonus = new ArrayList<String>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			int j=0;
			double bandToprevious=0;
			String bandToNext="";
			//bean.setScaleminRatio("40");
			//bean.setScalemaxRatio("60");
			//bean.setScalebanding("5");
			//bean.setScalecombine("61");
			//bean.setScaledigit("4");
				String bandFrom="0",bandTo="",slidingScaleCom="";
				int a=Integer.parseInt(bean.getScaleminRatio());int b=Integer.parseInt(bean.getScalemaxRatio());int c=Integer.parseInt(bean.getScalebanding()),d=Integer.parseInt(bean.getScaledigit()),e=Integer.parseInt(bean.getScalecombine()),f=999;
				bonusFrom.add(bandFrom);
				bonusTo.add(bean.getScaleminRatio());
				bonusLowClaimBonus.add(String.valueOf((e-(a+0))));
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				j++;
			for(int i=a;i<=b;i+=c) {
				if(j==1) {
					bandToprevious=a;
					bandToNext=String.valueOf(((i+c)));	
					
				}else {
					bandToprevious=Double.parseDouble(bandTo);
					bandToNext=String.valueOf(((Double.parseDouble(bandTo)+c)));	
				}
				
				if(bandTo.equals(DropDownControllor.formattereight(String.valueOf(f)))) {
					bandFrom="";
				}else {
					bandFrom=String.valueOf((bandToprevious)+(1/Math.pow(10, d)));
					System.out.println(j+"A==>"+DropDownControllor.formattereight(bandFrom));
				}
				
				
				if(((Double.parseDouble(bandToNext)))>b) {
					bandTo=DropDownControllor.formattereight(String.valueOf(f));
					
				}else {
					bandTo=DropDownControllor.formattereight(bandToNext);
				}
				System.out.println(j+"B==>"+bandTo);
				if(e<(i+c)) {
					slidingScaleCom=String.valueOf((e-b));
				}else {
					slidingScaleCom=String.valueOf((e-((i+c))));
				}
				System.out.println(j+"C==>"+slidingScaleCom);
				bonusFrom.add(bandFrom);
				bonusTo.add(bandTo);
				bonusLowClaimBonus.add(slidingScaleCom);
				string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
				j++;
			}
			
			bean.setScaleFrom(bonusFrom);
			bean.setScaleTo(bonusTo);
			bean.setScaleLowClaimBonus(bonusLowClaimBonus);
			bean.setScaleCommissionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
