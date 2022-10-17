package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.action.admin.AdminAction;
import com.maan.bean.ClaimBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.ClaimService;
import com.maan.service.CommonCalculation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClaimAction extends ActionSupport implements ModelDriven<ClaimBean>{
	final static DecimalFormat twoDigit = new DecimalFormat("###0.00");
	private static final long serialVersionUID = 1L;
	Map<String, Object> session = ActionContext.getContext().getSession();
	ClaimBean bean=new ClaimBean();
	FaculitivesBean bean1=new FaculitivesBean();
	private transient final ClaimService SERVICE=new ClaimService();
	private String productId=(String) session.get("mfrid")==null?"":(String) session.get("mfrid");
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String processId=(String) session.get("processId")==null?"":session.get("processId").toString();
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
	private String userName=session.get("UserName")==null?"":(String) session.get("UserName");
	private static final Logger LOGGER = LogUtil.getLogger(AdminAction.class);
	DropDownControllor dropDownController = new DropDownControllor();
	CommonCalculation calcu = new CommonCalculation();
	public ClaimBean getModel() {
		return bean;
	}
	private List<ClaimBean> RevisionList;	
	private List<ClaimBean> ClaimList;
	private List<ClaimBean> ClaimPayment;
	private List<ClaimBean> claimReview;
	private List<ClaimBean> CliamList;
	private List<ClaimBean> ReserveList;
	private List<ClaimBean> allocList;
	private String dropDown;	
	private String menuId;
	private String edit;
	
	private List<Map<String,Object>> allocationList;
	
	
	
	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public List<Map<String, Object>> getAllocationList() {
		return allocationList;
	}
	public void setAllocationList(List<Map<String, Object>> allocationList) {
		this.allocationList = allocationList;
	}
	
	
	public List<ClaimBean> getAllocList() {
		return allocList;
	}
	public void setAllocList(List<ClaimBean> allocList) {
		this.allocList = allocList;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public List<ClaimBean> getReserveList() {
		return ReserveList;
	}
	public void setReserveList(List<ClaimBean> reserveList) {
		ReserveList = reserveList;
	}	
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public List<ClaimBean> getCliamList() {
		return CliamList;
	}
	public void setCliamList(List<ClaimBean> cliamList) {
		CliamList = cliamList;
	}
	public List<ClaimBean> getClaimReview() {
		return claimReview;
	}
	public void setClaimReview(List<ClaimBean> claimReview) {
		this.claimReview = claimReview;
	}
	public List<ClaimBean> getClaimList() {
		return ClaimList;
	}
	public void setClaimList(List<ClaimBean> claimList) {
		ClaimList = claimList;
	}
	public List<ClaimBean> getClaimPayment() {
		return ClaimPayment;
	}
	public void setClaimPayment(List<ClaimBean> claimPayment) {
		ClaimPayment = claimPayment;
	}
	public List<ClaimBean> getRevisionList() {
		return RevisionList;
	}
	public void setRevisionList(List<ClaimBean> revisionList) {
		RevisionList = revisionList;
	}	
	public String claimInit()
		{	
		String forwards="";
		String editMode ="N";
		bean1.setBranchCode(branchCode);
		bean.setPolicy_Contract_No(bean.getContarctno());				
		if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
			bean.setProductId(productId);	
		}
		else{
			session.put("mfrid",bean.getProductId());
		}
		if (bean.getBusinessMode() != null && bean.getBusinessMode().equalsIgnoreCase("NewCliam") && StringUtils.isBlank(bean.getProposal_No())) {
			bean.setProposal_No(SERVICE.getProposalNo(bean));
		}else if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
			bean.setProposal_No(SERVICE.getProposalNo(bean));
		}
		session.put("claim_contract_no",bean.getPolicy_Contract_No());
		bean.setBranchCode(branchCode);	
		if(StringUtils.isNotBlank(bean.getProposal_No())){
		 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),bean.getLayerNo());
		}
		
			new DropDownControllor().getOpenPeriod(bean1);
			bean.setOpstartDate(bean1.getOpstartDate());
			bean.setOpendDate(bean1.getOpendDate());
				bean.setShortname(SERVICE.getShortname(bean));
				if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
					claimPaymentValidation();
				}
				
				 if(StringUtils.isNotBlank(bean.getDepartmentId())){
						session.put("DepartmentId",bean.getDepartmentId());
					}
				 if((StringUtils.isBlank(bean.getClaimMasterMode()))){
					bean.setDepartmentId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
				 }
				 SERVICE.InitCliam(bean,1);
				 if(((String)session.get("DepartmentId")).equals("0")){
					 session.put("DepartmentId",bean.getDepartmentId());
				 }
				 bean.setCoverLimitAmount(dropDownController.getCoverLimitAmount(bean.getProposal_No(),bean.getDepartmentId(),bean.getProductId()));
				//if((StringUtils.isBlank(bean.getInception_Date()))||(StringUtils.isBlank(bean.getAmendId()) || StringUtils.isBlank(bean.getCurrecny()) ||StringUtils.isBlank(bean.getCeding_Company_Code())) ){
				if(StringUtils.isBlank(bean.getAmendId()) && hasActionErrors()){
					claimnew();
					 if(!hasActionErrors()){
				    		addActionError("Contract details not available");
				    	}
					 	LOGGER.info("##########Validation Message Start###########");
						Iterator<String> error = getActionErrors().iterator();
						while(error.hasNext()){
							LOGGER.info(error.next());
						}
						LOGGER.info("##########Validation Message End###########");
					 if(StringUtils.isNotBlank(bean.getClaimMasterMode()) && "CP".equalsIgnoreCase(bean.getClaimMasterMode())){
							bean.setBusinessMode("new1");
						 forwards = "claimPayment";
					 }else{
						 bean.setBusinessMode("new");
						 forwards = "claimMaster";
					 }
				}
				else{
					if(!"N".equalsIgnoreCase(editMode)){
						bean.setMultiuserError("error");
						if(StringUtils.isNotBlank(bean.getClaimDisplay()) && "claimDisplaypayment".equalsIgnoreCase(bean.getClaimDisplay())){
									if("contractidentifier1".equalsIgnoreCase(bean.getFlag())){
										 idetifierList();
									 }else if(StringUtils.isNotBlank(bean.getClaimMasterMode()) && "CP".equalsIgnoreCase(bean.getClaimMasterMode())){
										 bean.setBusinessMode("new1");
										 claimnew();
									 }else{
									 	bean.setFlag("claim");
										claimPaymentList();
									 }
									forwards = "claimPayment";
								 }else if(StringUtils.isNotBlank(bean.getClaimDisplay()) && "claimDisplay".equalsIgnoreCase(bean.getClaimDisplay())){
									 if("NewCliam".equalsIgnoreCase(bean.getBusinessMode())){
										 if("contractidentifier".equalsIgnoreCase(bean.getFlag())){
											 idetifierList();
										 }else{
											 bean.setBusinessMode("new");
											 claimnew();
										 }
									 }else{
										 bean.setFlag("claim");
										 claimMasterList();
									 }
									 forwards = "claimMaster";
								 }else{
									if ( bean.getBusinessMode() != null  || edit!=null) {
										CliamList = SERVICE.CliamList(bean, 1);
										String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
										if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
											userRights = "CN,CV";
											session.put("MenuRights","CN,CV");
										}
										if (CliamList.isEmpty() && userRights.indexOf("CN")!=-1) {
											bean.setFlag("C");
											forwards ="portfoliList";
										}else{
											session.remove("IntialMode");
											forwards ="ClaimListAction";
										}
										}else{
											bean.setFlag("C");
											forwards ="portfoliList";
										}
									 }
							}
					else{
				if (bean.getBusinessMode() != null && bean.getBusinessMode().equalsIgnoreCase("NewCliam") ) {
					session.put("IntialMode","IntialMode");
					session.remove("Cliam_cliam_no");
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));	
					 //bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
					bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
					bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
					  if(StringUtils.isBlank(bean.getDepartmentId())){
							bean.setSubProfit_centerlist(new ArrayList<Map<String,Object>>());
						}else{
							bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
							//bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartmentId(),branchCode,productId));
						}
					//bean.setStatus_of_claim("Opened");
					bean.setStatus_of_claim("Open");
					bean.setAdvice_UW("No");
					bean.setAdvice_Mangement("No");
					bean.setRi_Recovery("Yes");
					bean.setPath("Amode");
					bean.setMenuStatus("N");
					forwards ="CliamInitAction";
					
				} else {
					if (StringUtils.isNotEmpty(bean.getClaim_No()) && StringUtils.isNotEmpty(edit)) {
						session.remove("IntialMode");						
						session.put("Cliam_cliam_no", bean.getClaim_No());
						bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
						 //bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
						bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
						bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
						RevisionList=SERVICE.CliamList(bean, 2);
						ClaimPayment=SERVICE.CliamList(bean, 4);
						SERVICE.InitCliam(bean, 4);
						bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
						//bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDeptId(),branchCode,productId));
						bean.setPath("Amode");
						bean.setMenuStatus("N");
						forwards = "CliamInitAction";
					} else {
						CliamList = SERVICE.CliamList(bean, 1);
						String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
						if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
							userRights = "CN,CV";
							session.put("MenuRights","CN,CV");
						}
						if (CliamList.isEmpty() && userRights.indexOf("CN")!=-1) {
							session.remove("Cliam_cliam_no");
							session.put("IntialMode","IntialMode");
							bean.setMenuStatus("N");
							forwards = "CliamInitAction";
						} else {
							session.remove("IntialMode");
							forwards ="ClaimListAction";
						}
					}
		 	  }
				if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
				{
					SERVICE.InitCliam(bean,10);
				} 
				if(bean.getPolicy_Contract_No()!=null &&"3".equalsIgnoreCase((String)session.get("mfrid")))
				{
					bean.setDepartmentClassList(new DropDownControllor().getDepartIdlist(bean.getPolicy_Contract_No(),branchCode,session.get("mfrid").toString(),(String)session.get("DepartmentId"),bean.getLayerNo()));
				}
				}
		}
			return forwards;
		}		
	    private void claimPaymentValidation() {
	    	if(StringUtils.isNotBlank(bean.getClaimMasterMode()) &&"CP".equalsIgnoreCase(bean.getClaimMasterMode())){
				if(StringUtils.isNotBlank(bean.getClaim_No())){
					int count =SERVICE.claimMasterValidation(bean,"claim");
					if(count==0){
						addActionError("Please Enter Correct Claim Number");
					}
				}
	    	}
		if(StringUtils.isNotBlank(bean.getContractNo())){
			int count =SERVICE.claimMasterValidation(bean,"contract");
			if(count==0){
				if(StringUtils.isNotBlank(bean.getClaimMasterMode()) &&"CD".equalsIgnoreCase(bean.getClaimMasterMode())){
					addActionError("Please Enter Correct Contract Number");
				}else{
				addActionError("No contract Number was found for this claim number");
				}
			}
		}
		if("2".equalsIgnoreCase(bean.getProductId()) && StringUtils.isNotBlank(bean.getDepartmentId()) && StringUtils.isNotBlank(bean.getContarctno())){
			int count =SERVICE.claimMasterValidation(bean,"class");
		if(count==0){
			addActionError("No Class was found for this Contract number");
		}
	    }
		if("3".equalsIgnoreCase(bean.getProductId()) && StringUtils.isNotBlank(bean.getLayerNo()) && StringUtils.isNotBlank(bean.getContarctno())){
			int count =SERVICE.claimMasterValidation(bean,"layer");
		if(count==0){
			addActionError("No Layer was found for this Contract number");
		}
	    }
		
	}
		@SuppressWarnings("unchecked")
		public String cliamPageModes()
		{
			String forward="CliamInitAction";
			String editMode ="";
			if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
    			bean.setContarctno(SERVICE.getContractNo(bean));
			}
	    	if (StringUtils.isBlank(bean.getProposal_No())) {
				bean.setProposal_No(SERVICE.getProposalNo(bean));
			}
	    	if(StringUtils.isNotBlank(bean.getDepartmentId())){
				session.put("DepartmentId",bean.getDepartmentId());
			}
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
			if(StringUtils.isNotBlank(bean.getProposal_No())){
			 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
			}
			//bean.setDeptId(bean.getDepartmentId());
	    	bean.setMenuStatus("N");
	    	if(StringUtils.isNotBlank(bean.getClaimDisplay())&&"claimDisplaypayment".equalsIgnoreCase(bean.getClaimDisplay())){	 
	    	if(StringUtils.isBlank( bean.getClaim_No())||bean.getClaim_No().equalsIgnoreCase("")){
					bean.setClaim_No(session.get("Cliam_cliam_no").toString());				}
			else{
					session.put("Cliam_cliam_no",bean.getClaim_No());
					session.remove("IntialMode");
				}
	    	}
	    	if(StringUtils.isBlank( bean.getPolicy_Contract_No())|| bean.getPolicy_Contract_No().equalsIgnoreCase("")){
	    		bean.setPolicy_Contract_No((String)session.get("claim_contract_no"));
	    	}
	    	else{
					session.put("claim_contract_no",bean.getPolicy_Contract_No());
				}
	    	  
	    	if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
			else{
					session.put("mfrid",bean.getProductId());
				}	
	    	 /* if(session.get("Cliam_cliam_no")!=null)
			  {
				  if(StringUtils.isBlank( bean.getClaim_No())||bean.getClaim_No().equalsIgnoreCase("")){
					  bean.setClaim_No(session.get("Cliam_cliam_no").toString());						}
					else{
						session.put("Cliam_cliam_no",bean.getClaim_No());
					}
			  }*/
	    	
			  bean.setBranchCode(branchCode);
			  bean.setShortname(SERVICE.getShortname(bean));
			  if(StringUtils.isNotBlank(bean.getClaimMasterMode())){
					claimPaymentValidation();
				}
			  if("N".equalsIgnoreCase(editMode)){
			  SERVICE.InitCliam(bean,1) ;
			  }
			  if((StringUtils.isBlank(bean.getAmendId()) && hasActionErrors())){
					claimnew();
					if(!hasActionErrors()){
						addActionError("Contract details not available");
					}
					LOGGER.info("##########Validation Message Start###########");
					Iterator<String> error = getActionErrors().iterator();
					while(error.hasNext()){
						LOGGER.info(error.next());
					}
					LOGGER.info("##########Validation Message End###########");
					 if(StringUtils.isNotBlank(bean.getClaimMasterMode()) && "CP".equalsIgnoreCase(bean.getClaimMasterMode())){
						 bean.setBusinessMode("new1");
						 forward = "claimPayment";
					 }else{
						 bean.setBusinessMode("new");
						 forward = "claimMaster";
					 }
				}
				else{
					if(!"N".equalsIgnoreCase(editMode) && StringUtils.isBlank(bean.getMultiuserError())){
						bean.setMultiuserError("error");
						if(StringUtils.isNotBlank(bean.getClaimDisplay()) && "claimDisplaypayment".equalsIgnoreCase(bean.getClaimDisplay())){
							if("contractidentifier1".equalsIgnoreCase(bean.getFlag())){
								 idetifierList();
							 }else if(StringUtils.isNotBlank(bean.getClaimMasterMode()) && "CP".equalsIgnoreCase(bean.getClaimMasterMode())){
								 bean.setBusinessMode("new1");
								 claimnew();
							 }
							 else{
									 bean.setFlag("claim");
										claimPaymentList();
							 }
								forward = "claimPayment";
						 }else if(StringUtils.isNotBlank(bean.getClaimDisplay()) && "claimDisplay".equalsIgnoreCase(bean.getClaimDisplay())){
							 if(bean.getBusinessMode().equalsIgnoreCase("Bmode") && null !=bean.getPaymentFlag() &&  ("new".equalsIgnoreCase(bean.getPaymentFlag()) ||"edit".equalsIgnoreCase(bean.getPaymentFlag())) ){
								 if(session.get("Cliam_cliam_no")!=null)
								   {		   
								   bean.setClaim_No(session.get("Cliam_cliam_no").toString());
								   ClaimList =SERVICE.CliamList(bean, 3);
								   ClaimPayment=SERVICE.CliamList(bean, 4);
								   RevisionList=SERVICE.CliamList(bean, 2);
								   SERVICE.InitCliam(bean,10);   
								   bean.setPaid_Amount_Orig_curr(bean.getPaid_Amount_Orig_curr());
								   bean.setLoss_Estimate_Revised_Orig_Curr(bean.getLoss_Estimate_Revised_Orig_Curr());
								   SERVICE.InitCliam(bean,5);
								   }
								   bean.setPaymentFlag("list");
								   bean.setPath("Bmode");
								   bean.setPayment_Request_No("");
								   bean.setPaid_Amount_Orig_curr("");
								   bean.setClaim_Note_recommendations("");
								   bean.setPayment_Reference("");
								   bean.setAdvice_Treasury("No");
								   bean.setDate("");
								   bean.setRemarks("");
								   bean.setPaymentType("Interim");
								   bean.setCurrecny(new DropDownControllor().getCurrenCyId(bean.getClaim_No(),bean.getContarctno(),bean.getLayerNo(),bean.getProductId()));
								   if("new".equalsIgnoreCase(bean.getPaymentFlag()) ){
									   bean.setDisabledFields("");
								   }
							 }
							 else if("NewCliam".equalsIgnoreCase(bean.getBusinessMode())){
								 if("contractidentifier".equalsIgnoreCase(bean.getFlag())){
									 idetifierList();
								 }else{
									 bean.setBusinessMode("new");
									 claimnew();
								 }
								 forward = "claimMaster";
							 }else{
								 bean.setFlag("claim");
								 claimMasterList();
								 forward = "claimMaster";
							 }
							 
						 }else{
							 if(bean.getBusinessMode().equalsIgnoreCase("Bmode") && null !=bean.getPaymentFlag() &&  ("new".equalsIgnoreCase(bean.getPaymentFlag()) ||"edit".equalsIgnoreCase(bean.getPaymentFlag())) ){
								 if(session.get("Cliam_cliam_no")!=null)
								   {		   
								   bean.setClaim_No(session.get("Cliam_cliam_no").toString());
								   ClaimList =SERVICE.CliamList(bean, 3);
								   ClaimPayment=SERVICE.CliamList(bean, 4);
								   RevisionList=SERVICE.CliamList(bean, 2);
								   SERVICE.InitCliam(bean,10);   
								   bean.setPaid_Amount_Orig_curr(bean.getPaid_Amount_Orig_curr());
								   bean.setLoss_Estimate_Revised_Orig_Curr(bean.getLoss_Estimate_Revised_Orig_Curr());
								   SERVICE.InitCliam(bean,5);
								   }bean.setDisabledFields("");
								   bean.setPaymentFlag("list");
								   bean.setPath("Bmode");
								   bean.setPayment_Request_No("");
								   bean.setPaid_Amount_Orig_curr("");
								   bean.setClaim_Note_recommendations("");
								   bean.setPayment_Reference("");
								   bean.setAdvice_Treasury("No");
								   bean.setDate("");
								   bean.setRemarks("");
								   bean.setPaymentType("Interim");
								   bean.setCurrecny(new DropDownControllor().getCurrenCyId(bean.getClaim_No(),bean.getContarctno(),bean.getLayerNo(),bean.getProductId()));
							 }else{
								bean.setFlag("C");
								forward ="portfoliList";	
							 }
							 }
					}else{
				  if(bean.getBusinessMode().equalsIgnoreCase("Amode"))
				  {				  
					  bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					  //bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
					  bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
					  bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
					  
					  if(session.get("Cliam_cliam_no")!=null)
					  {
						  if(StringUtils.isBlank( bean.getClaim_No())||bean.getClaim_No().equalsIgnoreCase("")){
							  bean.setClaim_No(session.get("Cliam_cliam_no").toString());						}
							else{
								session.put("Cliam_cliam_no",bean.getClaim_No());
							}
			 		  SERVICE.InitCliam(bean,4);
			 		  ClaimPayment=SERVICE.CliamList(bean, 4);
			 		  RevisionList=SERVICE.CliamList(bean, 2);
					  if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
						{SERVICE.InitCliam(bean,10);
							if("ClaimDone".equalsIgnoreCase(bean.getMsgFlag())){
								addActionMessage(getText("msg.claimUpdated"));
							}						
						}
					  }else
					  {
						  //bean.setStatus_of_claim("Opened");
						  bean.setStatus_of_claim("Open");
						  bean.setAdvice_Mangement("No");
						  bean.setAdvice_UW("No");
						  bean.setRi_Recovery("Yes");
						  //bean.setCreated_Date(dateFormat(new DropDownControllor().getSysDate()));
						  bean.setCreated_Date(new DropDownControllor().getSysDate());
					  }
					  if(bean.getPolicy_Contract_No()!=null &&"3".equalsIgnoreCase((String)session.get("mfrid")))
						{
							bean.setDepartmentClassList(new DropDownControllor().getDepartIdlist(bean.getPolicy_Contract_No(),branchCode,session.get("mfrid").toString(),(String)session.get("DepartmentId"),bean.getLayerNo()));
						}
					  if(StringUtils.isBlank(bean.getDepartmentId())){
							bean.setSubProfit_centerlist(new ArrayList<Map<String,Object>>());
						}else{
							bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
							//bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartmentId(),branchCode,productId));
						}
			 		  bean.setPath("Amode");
			 		
		 		  }
				  else if(bean.getBusinessMode().equalsIgnoreCase("Bmode"))
				  { 
					   if(session.get("Cliam_cliam_no")!=null)
					   {		   
					   bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					   if("list".equalsIgnoreCase(bean.getPaymentFlag())){
					   ClaimList =SERVICE.CliamList(bean, 3);
					   ClaimPayment=SERVICE.CliamList(bean, 4);
					   RevisionList=SERVICE.CliamList(bean, 2);
					   } 
					   SERVICE.InitCliam(bean,10);   
					   bean.setPaid_Amount_Orig_curr(bean.getPaid_Amount_Orig_curr());
					   bean.setLoss_Estimate_Revised_Orig_Curr(bean.getLoss_Estimate_Revised_Orig_Curr());
					   SERVICE.InitCliam(bean,5);
					   }
					   bean.setPath("Bmode");
					   bean.setPayment_Request_No("");
					   bean.setPaid_Amount_Orig_curr("");
					   bean.setClaim_Note_recommendations("");
					   bean.setPayment_Reference("");
					   bean.setAdvice_Treasury("No");
					   bean.setDate("");
					   bean.setRemarks("");
					   bean.setPaymentType("Interim");
					   bean.setCurrecny(new DropDownControllor().getCurrenCyId(bean.getClaim_No(),bean.getContarctno(),bean.getLayerNo(),bean.getProductId()));
					   if("edit".equalsIgnoreCase(bean.getPaymentFlag()) || "view".equalsIgnoreCase(bean.getPaymentFlag())){
							  SERVICE.claimPaymentEdit(bean); 
						 }else{
							 bean.setDisabledFields("");
						 }
				  }
				  else if(bean.getBusinessMode().equalsIgnoreCase("Cmode"))
				  {
					   if(session.get("Cliam_cliam_no")!=null)
					   {
						   bean.setClaim_No(session.get("Cliam_cliam_no").toString());
						   ClaimList =SERVICE.CliamList(bean, 3);
						   ClaimPayment=SERVICE.CliamList(bean, 4);
						   RevisionList=SERVICE.CliamList(bean, 2);
						   if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
							{
								SERVICE.InitCliam(bean,10);   
							}
					    }
					   bean.setRemarks("");
					   bean.setReverseClaimYN("Yes");
					   bean.setCloseClaimYN("Yes");
					   bean.setPath("Cmode");
					   if("PaymentDone".equalsIgnoreCase(bean.getMsgFlag())){
						   addActionMessage(getText("msg.paymentDone"));
					   }else if("ReserveDone".equalsIgnoreCase(bean.getMsgFlag()))
					   {
						   addActionMessage(getText("msg.reserveDone"));
					   }else if("ClaimClosed".equalsIgnoreCase(bean.getMsgFlag()))
					   {
						   addActionMessage(getText("msg.claimClosed"));
					   }				   
				  }
				  else if(bean.getBusinessMode().equalsIgnoreCase("Dmode"))
				  {
					  if(session.get("Cliam_cliam_no")!=null)
					  {
					  bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					  ClaimList = SERVICE.CliamList(bean, 3);
					  claimReview = SERVICE.CliamList(bean, 6);
					  SERVICE.InitCliam(bean,7);
					  if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
						{
							SERVICE.InitCliam(bean,10);
							if("ReviewDone".equalsIgnoreCase(bean.getMsgFlag())){
								addActionMessage(getText("msg.reviewDone"));
							}						
						}
					  }
					  bean.setPath("Dmode");
					  bean.setReview_Date("");
					  bean.setReview_Done_By("");
					  bean.setRemarks("");			  
				  }				
					}
				}
			return forward;			
		}		 
		@SuppressWarnings("unchecked")
		public String claimInsertAction()
		{	
			boolean flag=false;
			String forwards=null;
			/*if(StringUtils.isBlank(bean.getSubProfitId())){
				bean.setSubProfitId((String) session.get("subprofitID")==null?"0":(String) session.get("subprofitID"));
				session.remove("subprofitID");
			}*/
				String editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
				bean.setPolicy_Contract_No((String)session.get("claim_contract_no"));
				//bean.setProposal_No((String)session.get("claim_proposal_no"));
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				else{
					session.put("mfrid",bean.getProductId());
				}
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
				bean.setBranchCode(branchCode);
				bean1.setBranchCode(branchCode);
				bean.setLoginId(userId);
				bean1.setLoginid(userId);
				new DropDownControllor().getOpenPeriod(bean1);
				if(bean.getBusinessMode().equalsIgnoreCase("Amode"))
					 {
							if("N".equalsIgnoreCase(editMode)){
								   claimsInsertValidation();
							}
		 			       if(!hasActionErrors() && "N".equalsIgnoreCase(editMode))
						    {
		 			    	   flag=SERVICE.InitCliam(bean,2);
		 			    	   session.put("Cliam_cliam_no", bean.getClaim_No());
			 			       if(session.get("IntialMode")!=null){
			 			    	 session.remove("IntialMode");
			 			       }
		 			    	   bean.setBusinessMode("Amode");
		 			    	   bean.setMsgFlag("ClaimDone");
		 			    	   AfterInsert();
		 			    	   forwards="claimRedirect";
						 	}
						    else
						    {
						    	LOGGER.info("##########Validation Message Start###########");
								Iterator<String> error = getActionErrors().iterator();
								while(error.hasNext()){
									LOGGER.info(error.next());
								}
								LOGGER.info("##########Validation Message End###########");
							     if(bean.getBusinessMode().equalsIgnoreCase("Amode"))
							     {
									 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
									 //bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
									 bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
									 bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
									 if(StringUtils.isBlank(bean.getDepartmentId())){
											bean.setSubProfit_centerlist(new ArrayList<Map<String,Object>>());
										}else{
											bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
											//bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getDepartmentId(),branchCode,productId));
									 }
							    	 RevisionList=SERVICE.CliamList(bean, 2);
							    	 errorInsert();
							    	 if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
										{
								 	 		SERVICE.InitCliam(bean,10);
									 	}
							    	 if(bean.getPolicy_Contract_No()!=null &&"3".equalsIgnoreCase((String)session.get("mfrid")))
										{
											bean.setDepartmentClassList(new DropDownControllor().getDepartIdlist(bean.getPolicy_Contract_No(),branchCode,session.get("mfrid").toString(),(String)session.get("DepartmentId"),bean.getLayerNo()));
										}
							    	 forwards="CliamInitAction";
							     }
						    }	 			 	 
							 
					 }
				 else  if(bean.getBusinessMode().equalsIgnoreCase("Bmode"))
				 {	
					 bean.setDate_of_Loss(bean.getSample());
					 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					 bean.setLoginId(userId);
					 bean.setProcessId(processId);
					 bean.setDeptId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
					 if("N".equalsIgnoreCase(editMode)){
					    reserveValidation();
					 }
						if(!hasActionErrors() && "N".equalsIgnoreCase(editMode))
						{
							bean.setCurrecny(SERVICE.getCurrencyID(bean.getClaim_No(),bean.getContarctno(),branchCode));
							 getExcRateforClaim();
							bean.setClaim_No(session.get("Cliam_cliam_no").toString());
							flag=SERVICE.InitCliam(bean,3);
							if(bean.getPaymentType().equals("Final") && flag){
								SERVICE.InitCliam(bean,12);
							}
					 		if(flag)
					 		{
					 			//AfterInsert();		
					 			//bean.setBusinessMode("Amode");
			 			    	bean.setMsgFlag("ClaimDone");
					 			//forwards="claimRedirect";
					 			 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
								 ClaimList =SERVICE.CliamList(bean, 3);
								 RevisionList=SERVICE.CliamList(bean, 2);
								 ClaimPayment=SERVICE.CliamList(bean, 4);
								 errorInsert();
								 bean.setPaymentFlag("list");
								 forwards="CliamInitAction";
					 		}
					 		else
					 		{
								 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
								 ClaimList =SERVICE.CliamList(bean, 3);
								 ClaimPayment=SERVICE.CliamList(bean, 4);
								 addActionError(getText("errors.payment_Request_No.exits"));
								 errorInsert();
								 forwards="CliamInitAction";
					 		}
						}
						else
						{	
							LOGGER.info("##########Validation Message Start###########");
							Iterator<String> error = getActionErrors().iterator();
							while(error.hasNext()){
								LOGGER.info(error.next());
							}
							LOGGER.info("##########Validation Message End###########");
							bean.setClaim_No(session.get("Cliam_cliam_no").toString());
							 ClaimList =SERVICE.CliamList(bean, 3);
							 RevisionList=SERVICE.CliamList(bean, 2);
							 ClaimPayment=SERVICE.CliamList(bean, 4);
							 errorInsert();
							 if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
								{
						 	 		SERVICE.InitCliam(bean,10);
							 	}
							 if(StringUtils.isNotBlank(bean.getDate())) {
								 	//bean.setDate(dateFormat(bean.getDate()));
								 	bean.setDate(bean.getDate());
							 }
							 forwards="CliamInitAction";
				 		}
						 
				 }
				 
				 else if(bean.getBusinessMode().equalsIgnoreCase("Cmode"))
				 {
					 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					 bean.setDate_of_Loss(bean.getSample());
					 if("N".equalsIgnoreCase(editMode)){
					 updationValidation();	
					 }
					 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					 if(!hasActionErrors() && "N".equalsIgnoreCase(editMode))
						{					
						flag=SERVICE.InitCliam(bean,8);
						if("Yes".equalsIgnoreCase(bean.getReverseClaimYN())){
							bean.setMsgFlag("ReserveDone");
						}else{
							//if("0".equals((bean.getLoss_Estimate_Revised_Orig_Curr())))
							//bean.setMsgFlag("ClaimClosed");
							if(Double.parseDouble(StringUtils.isBlank(bean.getOsAmt())?"0":bean.getOsAmt())==0){
							 //addActionError(getText("msg.claimClosed"));
							bean.setMsgFlag("ClaimClosed");
						}
						}
						AfterInsert();
						bean.setBusinessMode("Cmode");
			 			forwards="claimRedirect";
						}
						else
						{
							LOGGER.info("##########Validation Message Start###########");
							Iterator<String> error = getActionErrors().iterator();
							while(error.hasNext()){
								LOGGER.info(error.next());
							}
							LOGGER.info("##########Validation Message End###########");
							bean.setClaim_No(session.get("Cliam_cliam_no").toString());
							RevisionList=SERVICE.CliamList(bean, 2);
						    ClaimList =SERVICE.CliamList(bean, 3);
						    ClaimPayment=SERVICE.CliamList(bean, 4);
						    errorInsert();
						 if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
							{
					 	 		SERVICE.InitCliam(bean,10);
						 	}
						/* if(StringUtils.isNotBlank(bean.getCliam_update_Date())) {
							 	bean.setCliam_update_Date(dateFormat(bean.getCliam_update_Date()));
						 }*/
						 forwards="CliamInitAction";
						}
				 }
				 else if(bean.getBusinessMode().equalsIgnoreCase("Dmode"))
				 {
					 bean.setDate_of_Loss(bean.getSample());
					 bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					 if("N".equalsIgnoreCase(editMode)){
					 reviewValidation();	
					 }
					 if(!hasActionErrors() && "N".equalsIgnoreCase(editMode))
						{
						 flag=SERVICE.InitCliam(bean,9);
						 bean.setMsgFlag("ReviewDone");
						 AfterInsert();
						 bean.setBusinessMode("Dmode");
				 		 forwards="claimRedirect";
						}
						else
						{
							LOGGER.info("##########Validation Message Start###########");
							Iterator<String> error = getActionErrors().iterator();
							while(error.hasNext()){
								LOGGER.info(error.next());
							}
							LOGGER.info("##########Validation Message End###########");
					     bean.setClaim_No(session.get("Cliam_cliam_no").toString());
					     ClaimList =SERVICE.CliamList(bean, 3);						 	
						 claimReview = SERVICE.CliamList(bean, 6);
						 errorInsert();
						 if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
							{
					 	 		SERVICE.InitCliam(bean,10);
						 	}
						 /*if(StringUtils.isNotBlank(bean.getReview_Date())) {
							 	bean.setReview_Date(dateFormat(bean.getReview_Date()));
						 }*/
						  forwards="CliamInitAction";
						}
				 }	
				if(!"N".equalsIgnoreCase(editMode)){
					bean.setMultiuserError("error");
				}
			return forwards;
		}			
	    private void errorInsert(){
	         bean.setBranchCode(branchCode);
	         SERVICE.InitCliam(bean,1);
	         bean.setShortname(SERVICE.getShortname(bean));
			 bean.setPath(bean.getBusinessMode());			 
		}
		private void AfterInsert(){
			bean.setPath(bean.getBusinessMode());
			session.put("modeofentry","EditEntry");
			bean.setCliamMode(session.get("modeofentry").toString());
			bean.setBranchCode(branchCode);
			bean.setShortname(SERVICE.getShortname(bean));
			SERVICE.InitCliam(bean,1);
			CliamList = SERVICE.CliamList(bean,1);
		}

		@SuppressWarnings("unchecked")
		public String claimView()
		{
				bean.setClaim_No(bean.getClaim_No());
				bean.setPolicy_Contract_No(bean.getContarctno());
				bean.setProcessId("2");
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				else{
					session.put("mfrid",bean.getProductId());
				}
				bean.setBranchCode(branchCode);	
				bean.setShortname(SERVICE.getShortname(bean));
				SERVICE.InitCliam(bean,1) ;
				if (bean.getMode().equalsIgnoreCase("Amode")) {
					ClaimPayment=SERVICE.CliamList(bean, 4);
					RevisionList=SERVICE.CliamList(bean, 2);						
					SERVICE.InitCliam(bean, 4);
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
					bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
					// bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
					  if(StringUtils.isBlank(bean.getDepartmentId())){
							bean.setSubProfit_centerlist(new ArrayList<Map<String,Object>>());
						}else{
							bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
							//bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDepartmentId(),branchCode,productId));
						}
					if(bean.getPolicy_Contract_No()!=null &&"3".equalsIgnoreCase((String)session.get("mfrid")))
						{
							bean.setDepartmentClassList(new DropDownControllor().getDepartIdlist(bean.getPolicy_Contract_No(),branchCode,session.get("mfrid").toString(),(String)session.get("DepartmentId"),bean.getLayerNo()));
					}
					bean.setCreated_Date(bean.getCreated_Date());
					bean.setReport_Date(bean.getReport_Date());
					bean.setDate_of_Loss(bean.getDate_of_Loss());
					bean.setPath("Amode");
				} else if (bean.getMode().equalsIgnoreCase("Bmode")) {
					  ClaimList =SERVICE.CliamList(bean, 3);
					  RevisionList=SERVICE.CliamList(bean, 2);
					  ClaimPayment=SERVICE.CliamList(bean,4);		          
					  bean.setPath("Bmode");
				} else if (bean.getMode().equalsIgnoreCase("Cmode")) {
					ClaimList =SERVICE.CliamList(bean, 3);
					RevisionList=SERVICE.CliamList(bean, 2);
					ClaimPayment=SERVICE.CliamList(bean,4);
					bean.setPath("Cmode");
				} else if (bean.getMode().equalsIgnoreCase("Dmode")) {
					ClaimList =SERVICE.CliamList(bean, 3);
					claimReview = SERVICE.CliamList(bean, 6);
					SERVICE.InitCliam(bean, 7);
					bean.setPath("Dmode");
			}			
			return  "CliamViewAction";			
		}
		@SuppressWarnings("unchecked")
		public String reserveList() 
		{
			if(bean.getMode().equalsIgnoreCase("payment")){
				allocationList=SERVICE.allocationList(bean);
				allocList=SERVICE.allocList(bean);
			}
			else{
			bean.setClaim_No(bean.getClaimNo());
			bean.setPolicy_Contract_No(bean.getContractNo());
			bean.setBranchCode(branchCode);
			bean.setProductId(productId);
			ReserveList=SERVICE.CliamList(bean, 7);
			}
			return "ReserveList";
		}
		/*public String getExcRate(){			
			bean.setExc_Rate(new DropDownControllor().GetExchangeRate(bean.getCurrecny(),bean.getDate_of_Loss(),countryId,branchCode));			
			return "dropdownajax";
		}*/
		public String getExcRate(){		
			if("paymentView".equalsIgnoreCase(bean.getDropDown())){
				 SERVICE.claimPaymentEdit(bean); 
			}else{
			bean.setExc_Rate(new DropDownControllor().GetExchangeRate(bean.getCurrecny(),bean.getCreated_Date(),countryId,branchCode));	
			}
			return "dropdownajax";
		}
		public String getExcRateforClaim(){			
			bean.setExc_Rate(new DropDownControllor().GetExchangeRate(bean.getCurrecny(),bean.getDate(),countryId,branchCode));			
			return "dropdownajax";
		}
		public String claimMasterList(){
			bean1.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean1);
			bean.setOpstartDate(bean1.getOpstartDate());
			bean.setOpendDate(bean1.getOpendDate());
			 bean.setBranchCode(branchCode);
			 
			 bean.setClaimlist(SERVICE.claimlist(bean));
			 bean.setClaimDisplay("claimDisplay");
			 return "claimMaster";
		}
		
		public String claimnew(){
			bean.setBranchCode(branchCode);
			bean.setProductIdList(SERVICE.productIdList(bean));
			bean.setPredepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"2","Y","","","","",""));
			if("new1".equalsIgnoreCase(bean.getBusinessMode())){
				return "claimPayment";	
			}
			else{
			return "claimMaster";
			}
		}
		public String contractSearch(){
			bean.setBranchCode(branchCode);
			bean.setYearList(getYearList());
			bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"","Y","","","","",""));
			bean.setBrokerList(dropDownController.getPersonalInfoDropDown(branchCode,"B",""));
			bean.setCedingCompanyList(dropDownController.getPersonalInfoDropDown(branchCode,"C",""));
			bean.setProductIdList(SERVICE.productIdList(bean));
			if("search1".equalsIgnoreCase(bean.getFlag())){
				return "claimPayment";
			}
			else{
			return "claimMaster";
			}
			
		}
		public String idetifierList(){
			bean.setBranchCode(branchCode);
			contractIdentifierValidation();
			String forward= "";
			if (!hasActionErrors()) {
					bean.setContractIdentifierList(SERVICE.contractidetifierlist(bean));
					if("contractidentifier1".equalsIgnoreCase(bean.getFlag())){
						forward = "claimPayment";
					}
					else{
						forward = "claimMaster";
					}
				}
			else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
					if("contractidentifier1".equalsIgnoreCase(bean.getFlag())){
						bean.setFlag("search1");
						forward = "claimPayment";
					}
					else{
						bean.setFlag("search");
						forward = "claimMaster";
					}
			}
			contractSearch();
			return forward;
			
		}
		public String claimPaymentList(){
			 bean.setBranchCode(branchCode);
			 bean.setClaimlist(SERVICE.claimpaymentlist(bean));
			 bean.setClaimDisplay("claimDisplaypayment");
			return "claimPayment";
		}
		private void contractIdentifierValidation() {
			if("N".equalsIgnoreCase(bean.getProductId())|| "".equalsIgnoreCase(bean.getProductId())){
				addActionError(getText("errors.transaction.reqired"));	
			}
		}
		private List<Map<String,Object>> getYearList(){
			List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
			Calendar cal=Calendar.getInstance(); 
			for(int j=(cal.get(Calendar.YEAR))-4;j<=(cal.get(Calendar.YEAR))+1;j++){
				Map<String,Object> year=new HashMap<String, Object>();
				year.put("YEAR", j);
				yearsList.add(year);
			}
			return yearsList;
		}

		
		public void claimsInsertValidation() {
			final Validation val = new Validation();
			
				if (val.isNull(bean.getStatus_of_claim()).equalsIgnoreCase("")) {
					addActionError(getText("errors.status_of_claim.required"));
				}else if("Closed".equals(bean.getStatus_of_claim()))
				{
					if(Double.parseDouble(StringUtils.isBlank(bean.getOsAmt())?"0":bean.getOsAmt())!=0){
						addActionError(getText("claim.close.osAmout"));
					}
					
				}
				if("3".equalsIgnoreCase(productId)){
					if(StringUtils.isBlank(bean.getDepartmentClass())){
						addActionError(getText("error.departmentclass"));
					}
				}
				if(!"2".equalsIgnoreCase(productId)){
					if (val.checkDate(bean.getDate_of_Loss()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.date_of_Loss.required"));
					}else if(val.belowCurrentDate(bean.getDate_of_Loss()).equalsIgnoreCase("INVALID"))
					{
						addActionError(getText("errors.date_of_Loss.CurrentDate"));				
					} 
					else if("3".equalsIgnoreCase(productId) && bean.getBasis()!=null && "2".equals(bean.getBasis())) {
						if(Validation.ValidateTwo(bean.getFrom(), bean.getDate_of_Loss()).equalsIgnoreCase("Invalid"))
						{ 
							addActionError(getText("errors.date_of_Loss.inception.Error"));
						} else if(Validation.ValidateTwo(bean.getDate_of_Loss(), bean.getTo()).equalsIgnoreCase("Invalid"))
						{ 
							addActionError(getText("errors.date_of_Loss.inception.Error"));
						}
					}
					else if("1".equalsIgnoreCase(productId)) {
						if(Validation.ValidateTwo(bean.getFrom(), bean.getDate_of_Loss()).equalsIgnoreCase("Invalid"))
						{ 
							addActionError(getText("errors.date_of_Loss.inception.Error.pid1"));
						} else if(Validation.ValidateTwo(bean.getDate_of_Loss(), bean.getTo()).equalsIgnoreCase("Invalid"))
						{ 
							addActionError(getText("errors.date_of_Loss.inception.Error.pid1"));
						}
					}					
				}
				if (val.checkDate(bean.getReport_Date()).equalsIgnoreCase("INVALID")) {

					addActionError(getText("errors.report_Date.required"));
				}
				else if(val.belowCurrentDate(bean.getReport_Date()).equalsIgnoreCase("INVALID"))
				{
					addActionError(getText("errors.report_Date.Error"));
				}
				if (val.checkDate(bean.getReservePositionDate()).equalsIgnoreCase("INVALID")) {

					addActionError(getText("errors.reservepositionDate.required"));
				}
				else if(val.belowCurrentDate(bean.getReservePositionDate()).equalsIgnoreCase("INVALID"))
				{
					addActionError(getText("errors.reservepositionDate.Invalid"));
				}
				if(Validation.ValidateTwo(bean.getDate_of_Loss(), bean.getReport_Date()).equalsIgnoreCase("Invalid"))
				{ 
					addActionError(getText("errors.report_Date1.Error"));
				}
				if(Validation.ValidateTwo(bean.getDate_of_Loss(), bean.getReservePositionDate()).equalsIgnoreCase("Invalid"))
				{ 
					addActionError(getText("errors.reservepositionDate.Error"));
				}
				if(Validation.ValidateTwo(bean.getReservePositionDate(), bean.getReport_Date()).equalsIgnoreCase("Invalid"))
				{ 
					addActionError(getText("errors.reservepositionDate.Error1"));
				}
				if (val.isNull(bean.getCreated_by()).equalsIgnoreCase("")) {
					//addActionError(getText("errors.created_by.required"));
				}
				else if(Validation.ValidateTwo(bean.getReport_Date(),bean.getCreated_Date()).equalsIgnoreCase("invalid"))
				{
					addActionError(getText("errors.created_Date.greater"));
				}
				else if(val.belowCurrentDate(bean.getCreated_Date()).equalsIgnoreCase("Invalid"))
				{
					addActionError(getText("errors.created_Date.invalid"));
				}
				if (val.checkDate(bean.getCreated_Date()).equalsIgnoreCase("INVALID"))
				{
					addActionError(getText("errors.created_Date.required"));
				}
				else if(Validation.ValidateTwo(bean.getDate_of_Loss(),bean.getCreated_Date()).equalsIgnoreCase("invalid"))
				{
					addActionError(getText("errors.created_Date.greater1"));
				}
				 if(Validation.ValidateTwo(bean.getReservePositionDate(), bean.getCreated_Date()).equalsIgnoreCase("Invalid"))
				{ 
					addActionError(getText("errors.reservepositionDate.Error2"));
				}
				 else if(Validation.ValidateTwo(bean.getAcceptenceDate(),bean.getCreated_Date()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.claim.acDate",new String[]{bean.getAcceptenceDate()}));
					}
				 if(!"Reopened".equalsIgnoreCase(bean.getStatus_of_claim()) && !"Repudiate".equalsIgnoreCase(bean.getStatus_of_claim())){
				if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getCreated_Date()).equalsIgnoreCase("")){
					if(new DropDownControllor().Validatethree(branchCode, bean.getCreated_Date())==0){
						addActionError(getText("errors.open.period.date.Claim.reg",new String[] {bean1.getOpenPeriodDate()}));
					}
				}
				 }
				if (val.isNull(bean.getLoss_Details()).equalsIgnoreCase("")) {
					addActionError(getText("errors.loss_Details.required"));
				}

				if (val.isNull(bean.getCause_of_Loss()).equalsIgnoreCase("")) {
					addActionError(getText("errors.cause_of_Loss.required"));
				}

				if (val.isNull(bean.getLocation()).equalsIgnoreCase("")) {
					addActionError(getText("errors.location.required"));
				}
				if(StringUtils.isBlank(bean.getCedentClaimNo())){
					addActionError(getText("error.cedent.claim.no"));
				}else {
					String claimno=SERVICE.getDuplicateCedentClaim(bean);
					if(StringUtils.isNotBlank(claimno)) {
						addActionError(getText("error.cedent.claim.no.duplicate",new String[]{claimno}));
					}
				}
				
				if(val.isSelect(bean.getCurrecny()).equalsIgnoreCase(""))
				{
					addActionError(getText("Error.Currency.required"));
				}
				if("2".equalsIgnoreCase(bean.getProductId())){
				if(StringUtils.isBlank(bean.getClaimdepartId())){
					addActionError(getText("error.departId.required"));
				}
				}if(!"3".equalsIgnoreCase(bean.getProductId()) ){
					if(StringUtils.isBlank(bean.getSubProfitId())){
						addActionError(getText("error.subProfit_center.required"));
					}else{
						bean.setSubProfitId((bean.getSubProfitId()).replaceAll(" ", ""));
					}
				}
				if (val.isNull(bean.getLoss_Estimate_Orig_Curr()).equalsIgnoreCase("")) {
					if( "1".equals(bean.getProductId())){
					addActionError(getText("errors.loss_Estimate100OC.required" ));
					}else if( "2".equals(bean.getProductId())){
					addActionError(getText("errors.loss_Estimate100OC.required.cash" ));
					}else {
					addActionError(getText("errors.loss_Estimate100OC.required.loss" ));
					}
					
				} else
				{
					bean.setLoss_Estimate_Orig_Curr((bean.getLoss_Estimate_Orig_Curr()).replaceAll(",",""));
					if (val.numbervalid(bean.getLoss_Estimate_Orig_Curr()).equalsIgnoreCase("INVALID")) 
					{
						if( "1".equals(bean.getProductId())){
						addActionError(getText("errors.loss_Estimate100OC_invalid"));
						}else if( "2".equals(bean.getProductId())){
							addActionError(getText("errors.loss_Estimate100OC_invalid.cash" ));
						}else {
						addActionError(getText("errors.loss_Estimate100OC_invalid.loss" ));
						}
						
					}
				}
				if(StringUtils.isNotBlank(bean.getGrossLossFGU())){
					bean.setGrossLossFGU((bean.getGrossLossFGU()).replaceAll(",",""));
					if(Double.parseDouble(bean.getLoss_Estimate_Orig_Curr())>Double.parseDouble(bean.getGrossLossFGU())){
						if( "1".equals(bean.getProductId())){
						//addActionError(getText("errors.loss_Estimate100OC_invalid.less"));
						}else if( "2".equals(bean.getProductId())){
							//addActionError(getText("errors.loss_Estimate100OC_invalid.less.cash" ));
						}else {
						//addActionError(getText("errors.loss_Estimate100OC_invalid.less.loss" ));
						}
						
					}
				}
				boolean flag=true;
				if(StringUtils.isBlank(bean.getInsuredName())) {
					addActionError(getText("error.claim.insuredname"));
				}
				
				
				if (val.isNull(bean.getLoss_Estimate_Our_share_Orig_Curr()).equalsIgnoreCase("")) {
					if( "1".equals(bean.getProductId())){
					addActionError(getText("errors.loss_Estimate_Orig_Curr.required"));
					}else if( "2".equals(bean.getProductId())){
						addActionError(getText("errors.loss_Estimate_Orig_Curr.required.cash" ));
					}else {
					addActionError(getText("errors.loss_Estimate_Orig_Curr.required.loss" ));
					}
					flag=false;
				} else
				{
					bean.setLoss_Estimate_Our_share_Orig_Curr((bean.getLoss_Estimate_Our_share_Orig_Curr()).replaceAll(",",""));
					if (val.numbervalid(bean.getLoss_Estimate_Our_share_Orig_Curr()).equalsIgnoreCase("INVALID")) 
					{
						if( "1".equals(bean.getProductId())){
						addActionError(getText("errors.loss_Estimate_Orig_Curr.invalid"));
						}else if( "2".equals(bean.getProductId())){
							addActionError(getText("errors.loss_Estimate_Orig_Curr.invalid.cash" ));
						}else {
						addActionError(getText("errors.loss_Estimate_Orig_Curr.invalid.loss" ));
						}
						flag=false;
					}
					else {
						String ans = calcu.calculateClaimRegistration(bean,"Gross");
						if(Double.parseDouble(ans)!=Double.parseDouble(bean.getLoss_Estimate_Our_share_Orig_Curr())){
							this.addActionError(getText("error.calcul.mistake"));
							LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						}else{
							bean.setLoss_Estimate_Our_share_Orig_Curr(ans);
						}
						
					}
				}
				if (val.isNull(bean.getExc_Rate()).equalsIgnoreCase("")) {
					addActionError(getText("errors.exc_Rate.required"));
					flag=false;
				}
				if(flag && "1".equals(bean.getProductId())&& ! val.isNull(bean.getSumInsOSDC()).equalsIgnoreCase(""))
				{
					bean.setSumInsOSDC((bean.getSumInsOSDC()).replaceAll(",",""));
					if((Double.parseDouble(bean.getLoss_Estimate_Our_share_Orig_Curr())/Double.parseDouble(bean.getExc_Rate()))>Double.parseDouble(bean.getSumInsOSDC()))
					{
						//addActionError(getText("errors.loss_Estimate_Orig_Curr.greaterSIOSDC"));
					}
				}
				if(StringUtils.isNotBlank(bean.getLoss_Estimate_Orig_Curr()) && StringUtils.isNotBlank(bean.getLoss_Estimate_Our_share_Orig_Curr())){
					bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
					double value = ((Double.parseDouble(bean.getLoss_Estimate_Orig_Curr())*Double.parseDouble(bean.getSigned_Share()))/100);
					final double dround=Math.round(value*100.0)/100.0;
					final String valu=twoDigit.format(dround);
					if(Double.parseDouble(valu)<Double.parseDouble(bean.getLoss_Estimate_Our_share_Orig_Curr())){
						if( "1".equals(bean.getProductId())){
						addActionError(getText("error.our.share.loss.value"));
						}else if( "2".equals(bean.getProductId())){
							addActionError(getText("error.our.share.loss.value.cash" ));
						}else {
						addActionError(getText("error.our.share.loss.value.loss" ));
						}
					}
				}
				/*else if(flag&& "2".equals(bean.getProductId())&& ! val.isNull(bean.getCashLossOSDC()).equalsIgnoreCase(""))
				{
					bean.setCashLossOSDC((bean.getCashLossOSDC()).replaceAll(",",""));
					if((Double.parseDouble(bean.getLoss_Estimate_Our_share_Orig_Curr())/Double.parseDouble(bean.getExc_Rate()))<Double.parseDouble(bean.getCashLossOSDC()))
					{
						addActionError(getText("errors.loss_Estimate_Orig_Curr.greater"));
					}					
				}*/

				/*if (val.isNull(bean.getAdvice_UW().trim()).equalsIgnoreCase("")) {

					addActionError(getText("errors.advice_UW.required"));
				}

				if (val.isNull(bean.getAdvice_Mangement().trim()).equalsIgnoreCase("")) {

					addActionError(getText("errors.advice_Mangement.required"));
				}*/
				if(StringUtils.isBlank(bean.getRecordFees())){
					addActionError(getText("error.record.fees"));
				}
				else{
					if("Yes".equalsIgnoreCase(bean.getRecordFees())){
						if(StringUtils.isBlank(bean.getSurveyorAdjesterPerOC())){
							addActionError(getText("surveyor.error.per"));
						}
						else{
							bean.setSurveyorAdjesterPerOC(bean.getSurveyorAdjesterPerOC().replaceAll(",", ""));
						}
						if(StringUtils.isBlank(bean.getSurveyorAdjesterOurShareOC())){
							addActionError(getText("surveyor.error.our.share.per"));
						}
						else{
							bean.setSurveyorAdjesterOurShareOC(bean.getSurveyorAdjesterOurShareOC().replaceAll(",", ""));
							String ans = calcu.calculateClaimRegistration(bean,"Surveyor");
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getSurveyorAdjesterOurShareOC())){
								this.addActionError(getText("error.calcul.mistake"));
								LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.setSurveyorAdjesterOurShareOC(ans);
							}
						}
						if(StringUtils.isNotBlank(bean.getSurveyorAdjesterPerOC()) && StringUtils.isNotBlank(bean.getSurveyorAdjesterOurShareOC())){
							bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
							double value = ((Double.parseDouble(bean.getSurveyorAdjesterPerOC())*Double.parseDouble(bean.getSigned_Share()))/100);
							final double dround=Math.round(value*100.0)/100.0;
							final String valu=twoDigit.format(dround);
							if(Double.parseDouble(valu)<Double.parseDouble(bean.getSurveyorAdjesterOurShareOC())){
								addActionError(getText("error.our.share.value"));
							}
						}
						if(StringUtils.isBlank(bean.getOtherProfessionalPerOc())){
							addActionError(getText("other.error.per"));
						}
						else{
							bean.setOtherProfessionalPerOc(bean.getOtherProfessionalPerOc().replaceAll(",", ""));
						}
						if(StringUtils.isBlank(bean.getProfessionalOurShareOc())){
							addActionError(getText("other.error.our.share.per"));
						}
						else{
							bean.setProfessionalOurShareOc(bean.getProfessionalOurShareOc().replaceAll(",", ""));
							bean.setSurveyorAdjesterOurShareOC(bean.getSurveyorAdjesterOurShareOC().replaceAll(",", ""));
							String ans =calcu.calculateClaimRegistration(bean,"professional");
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getProfessionalOurShareOc())){
								this.addActionError(getText("error.calcul.mistake"));
								LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.setProfessionalOurShareOc(ans);
							}
						}
						if(StringUtils.isNotBlank(bean.getOtherProfessionalPerOc()) && StringUtils.isNotBlank(bean.getProfessionalOurShareOc())){
							bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
							double value = ((Double.parseDouble(bean.getOtherProfessionalPerOc())*Double.parseDouble(bean.getSigned_Share()))/100);
							final double dround=Math.round(value*100.0)/100.0;
							final String valu=twoDigit.format(dround);
							if(Double.parseDouble(valu)<Double.parseDouble(bean.getProfessionalOurShareOc())){
								addActionError(getText("error.our.share.other.value"));
							}
						}
					}
				}
				if(StringUtils.isBlank(bean.getRecordIbnr())){
					addActionError(getText("error.record.ibnr"));
				}
				else{
					if("Yes".equalsIgnoreCase(bean.getRecordIbnr())){
						if(StringUtils.isBlank(bean.getIbnrPerOc())){
							addActionError(getText("ibnr.error.per"));
						}
						else{
							bean.setIbnrPerOc(bean.getIbnrPerOc().replaceAll(",", ""));
						}
						if(StringUtils.isBlank(bean.getIbnrOurShareOc())){
							addActionError(getText("ibnr.error.our.share.per"));
						}
						else{
							bean.setIbnrOurShareOc(bean.getIbnrOurShareOc().replaceAll(",", ""));
						}
						
					}
					if(StringUtils.isNotBlank(bean.getIbnrPerOc()) && StringUtils.isNotBlank(bean.getIbnrOurShareOc())){
						bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
						double value = ((Double.parseDouble(bean.getIbnrPerOc())*Double.parseDouble(bean.getSigned_Share()))/100);
						final double dround=Math.round(value*100.0)/100.0;
						final String valu=twoDigit.format(dround);
						if(Double.parseDouble(valu)<Double.parseDouble(bean.getIbnrOurShareOc())){
							addActionError(getText("error.our.share.ibnr.value"));
						}
				}
				}
				if (val.isNull(bean.getRi_Recovery()).equalsIgnoreCase("")) {

					addActionError(getText("errors.ri_Recovery.required"));
				}
				else if(bean.getRi_Recovery().equalsIgnoreCase("Yes") && val.isSelect(bean.getRecovery_from()).equalsIgnoreCase(""))
				{
					addActionError(getText("errors.recovery_from.required"));
				}


				if (!val.isNull(bean.getStatus_of_claim()).equalsIgnoreCase("") && bean.getStatus_of_claim().equalsIgnoreCase("Closed") && val.isNull(bean.getRemarks()).equalsIgnoreCase("")) 
				{
					addActionError(getText("errors.remarks.required"));				 
				}	
				bean.setLoss_Estimate_Our_share_Orig_Curr((bean.getLoss_Estimate_Our_share_Orig_Curr()).replaceAll(",",""));
				if("1".equalsIgnoreCase(productId)){
				if (SERVICE.BusinessValidation(bean, 3))
				  {
					addActionError(getText("DateInvalide.Compare.InceptionDate"));
				  }	
				}
				if("Reopened".equals(bean.getStatus_of_claim()))
				{
					bean.setPreReopenDate(new DropDownControllor().getpreReopendDate(bean.getContarctno(),bean.getClaim_No(),"Reopen"));
					if (val.checkDate(bean.getReopen_Date()).equalsIgnoreCase("INVALID")) 
					{
						addActionError(getText("errors.claim.reopendate.required"));
					}
					else{
						if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getReopen_Date()).equalsIgnoreCase("")){
						if(new DropDownControllor().Validatethree(branchCode, bean.getReopen_Date())==0){
							addActionError(getText("errors.open.period.date.Claim.reopen",new String[] {bean1.getOpenPeriodDate()}));
						}
					}
					 if(!val.isNull(bean.getReopen_Date()).equalsIgnoreCase("") && !val.isNull(bean.getCreated_Date()).equalsIgnoreCase(""))  {
						if(Validation.ValidateTwo(bean.getCreated_Date(),bean.getReopen_Date()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("error.claim.registration.reopen.gr"));
						}
					}
					 if(!val.isNull(bean.getReopen_Date()).equalsIgnoreCase("") && !val.isNull(bean.getCreated_Date()).equalsIgnoreCase(""))  {
						if(Validation.ValidateTwo(Validation.getMaxDateValidate(bean.getCreated_Date(), bean.getPreReopenDate()),bean.getReopen_Date()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("error.claim.registration.reopen.pr"));
						}
					}
				}
				}
				if("Repudiate".equals(bean.getStatus_of_claim()))
				{
					String preDate = new DropDownControllor().getpreReopendDate(bean.getContarctno(),bean.getClaim_No(),"Reputed");
					if (val.checkDate(bean.getReputed_Date()).equalsIgnoreCase("INVALID")) 
					{
						addActionError(getText("errors.claim.repudiatedate.required"));
					}
					else{
						if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getReputed_Date()).equalsIgnoreCase("")){
						if(new DropDownControllor().Validatethree(branchCode, bean.getReputed_Date())==0){
							addActionError(getText("errors.open.period.date.Claim.repudiate",new String[] {bean1.getOpenPeriodDate()}));
						}
					}
					if(!val.isNull(bean.getReputed_Date()).equalsIgnoreCase("") && !val.isNull(bean.getCreated_Date()).equalsIgnoreCase(""))  {
						if(Validation.ValidateTwo(bean.getCreated_Date(),bean.getReputed_Date()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("error.claim.registration.repudiate.gr"));
						}
					}
					 if(!val.isNull(bean.getReputed_Date()).equalsIgnoreCase("") && !val.isNull(bean.getCreated_Date()).equalsIgnoreCase(""))  {
						if(Validation.ValidateTwo(Validation.getMaxDateValidate(bean.getCreated_Date(), preDate),bean.getReputed_Date()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("error.claim.registration.repudi.pr"));
						}
					}
				}
				}
		}
		public void reserveValidation() {
			try {
				final Validation val = new Validation();			
				//int count=SERVICE.ClaimpaymentCount(bean);
				//if(count<=0){
				//	addActionError("contract.claim.mismatch");
				//}
				//else{
					if(SERVICE.BusinessValidation(bean,6)){
						addActionError(getText("error.claimClosed"));
					}
					else if(SERVICE.BusinessValidation(bean,10)){
						addActionError(getText("error.claimRepudiated"));
					}
					else{ 
						
					if (val.isNull(bean.getPaid_Amount_Orig_curr()).equalsIgnoreCase("")) {
						addActionError(getText("errors.paid_Amount_Orig_curr.required"));
					} else
					{
						bean.setPaid_Amount_Orig_curr((bean.getPaid_Amount_Orig_curr()).replaceAll(",",""));
						if (val.numbervalid(bean.getPaid_Amount_Orig_curr()).equalsIgnoreCase("INVALID")) 
						{
							addActionError(getText("errors.paid_Amount_Orig_curr.invalid"));
						}else{
							String ans =calcu.calculateClaimPayment(bean,"Total");
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getPaid_Amount_Orig_curr())){
								this.addActionError(getText("error.calcul.mistake"));
								LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.setPaid_Amount_Orig_curr(ans);
							}
						}
						
						/*else {
							bean.setUnderwriter(SERVICE.getUnderwritter(bean));
							String uwLimit=dropDownController.getUnderWriterLimmit(bean.getUnderwriter(),(String)session.get("processId"), productId, productId.equalsIgnoreCase("1")?(String)session.get("DepartmentId"):"0");
							uwLimit=uwLimit.replaceAll(",","");
							//String uwLimit=new DropDownControllor().getUWLimmit(bean.getLoginId(),bean.getProcessId(),bean.getProductId(), bean.getDeptId());
							if(Double.parseDouble(uwLimit)==0)
							{
								addActionError(getText("error.maxLimitProduct.config"));
							}else if(Double.parseDouble(bean.getPaid_Amount_Orig_curr())>Double.parseDouble(uwLimit))
							{
								addActionError(getText("error.maxLimitProduct.exceedLimit",new String[]{uwLimit}));
							}
						}*/
					}			 

					if (val.isNull(bean.getPayment_Reference()).equalsIgnoreCase("")) {
						addActionError(getText("errors.payment_Reference.required"));
					}
					if (val.isNull(bean.getPaid_claim_os()).equalsIgnoreCase("")) {
						addActionError(getText("errors.paid_claim_os.required"));
					} else {
						bean.setPaid_claim_os((bean.getPaid_claim_os()).replaceAll(",",""));
						if (val.numbervalid(bean.getPaid_claim_os()).equalsIgnoreCase("INVALID")) 
						{
							addActionError(getText("errors.paid_claim_os.invalid"));
						} else if(SERVICE.BusinessValidation(bean,7)){
							addActionError(getText("errors.paid_claim_osGrRsrvAmt"));
						}
					}
					if (val.isNull(bean.getSurveyor_fee_os()).equalsIgnoreCase("")) {
						addActionError(getText("errors.surveyor_fee_os.required"));
					}  else {
						bean.setSurveyor_fee_os((bean.getSurveyor_fee_os()).replaceAll(",",""));
						if (val.numbervalid(bean.getSurveyor_fee_os()).equalsIgnoreCase("INVALID")) 
						{
							addActionError(getText("errors.surveyor_fee_os.invalid"));
						} else if(SERVICE.BusinessValidation(bean,8)){
							addActionError(getText("errors.surveyor_fee_osGrRsrvAmt"));
						}
					}
					if (val.isNull(bean.getOther_prof_fee_os()).equalsIgnoreCase("")) {
						addActionError(getText("errors.other_prof_fee_os.required"));
					}  else {
						bean.setOther_prof_fee_os((bean.getOther_prof_fee_os()).replaceAll(",",""));
						if (val.numbervalid(bean.getOther_prof_fee_os()).equalsIgnoreCase("INVALID")) 
						{
							addActionError(getText("errors.other_prof_fee_os.invalid"));
						} else if(SERVICE.BusinessValidation(bean,9)){
							addActionError(getText("errors.other_prof_fee_osGrRsrvAmt"));
						}
					}
					if("3".equalsIgnoreCase(productId)){
					if (!val.isNull(bean.getPaid_claim_os()).equalsIgnoreCase("") && !val.isNull(bean.getSurveyor_fee_os()).equalsIgnoreCase("") && !val.isNull(bean.getOther_prof_fee_os()).equalsIgnoreCase("")) {
						double totalVal = Double.parseDouble(bean.getPaid_claim_os().replaceAll(",", "")) +Double.parseDouble(bean.getSurveyor_fee_os().replaceAll(",", "")) +Double.parseDouble(bean.getOther_prof_fee_os().replaceAll(",", ""));
						String annualVal =new DropDownControllor().getAnnualAgregateVal(bean.getProposal_No(),bean.getPolicy_Contract_No(),bean.getLayerNo(),bean.getBranchCode(),bean.getDepartmentId());
						if(totalVal>Double.parseDouble(annualVal)){
							//addActionError(getText("error.annual.agregate",new String[] {bean.getDepartmentName() }));
						}
					}
					}
					if (val.checkDate(bean.getDate()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.date.required"));
					}else if(val.belowCurrentDate(bean.getDate()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.created_Date.invalid5"));
					}
					else if(Validation.ValidateTwo(bean.getDate_of_Loss(),bean.getDate()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.created_Date.invalid1"));
					}else if(Validation.ValidateTwo(SERVICE.getClaimDate(bean,1),bean.getDate()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.payDtGrELastResDt"));
					}else if(Validation.ValidateTwo(SERVICE.getClaimDate(bean,4),bean.getDate()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.payDtGrELastPayDt"));
					}
					if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getDate()).equalsIgnoreCase("")){
						if(new DropDownControllor().Validatethree(branchCode, bean.getDate())==0){
							addActionError(getText("errors.open.period.date.Claim.pay",new String[] {bean1.getOpenPeriodDate()}));
						}
						}
					if(!"edit".equalsIgnoreCase(bean.getPaymentFlag())){
						if(SERVICE.BusinessValidation(bean,4)){
							addActionError(getText("errors.PaidAmtGrOSAmt"));
						}
					}
				}	
					if("3".equalsIgnoreCase(bean.getProductId())){
					bean.setReinstPremiumOCOS((bean.getReinstPremiumOCOS()).replaceAll(",",""));
					//bean.setNetclaimAmountOCOS((bean.getNetclaimAmountOCOS()).replaceAll(",",""));
					}
//}
			} catch (NumberFormatException e) {
				addActionError(getText("error.claim.payment.validation"));
				e.printStackTrace();
			}
				
		}
		public void updationValidation() {
			final Validation val = new Validation();
			if(SERVICE.BusinessValidation(bean,6)){
				addActionError(getText("error.claimClosed"));
				}else{	
						if("Yes".equalsIgnoreCase(bean.getReverseClaimYN())&&"Yes".equalsIgnoreCase(bean.getCloseClaimYN())){
							addActionError(getText("claim.reserveClaimloseYN"));
						}else if("Yes".equalsIgnoreCase(bean.getReverseClaimYN()))
						{
						if (val.isNull(bean.getUpdate_Rivised_original_Cur()).equalsIgnoreCase("")) {
							addActionError(getText("errors.loss_Estimate_Revised_Orig_Curr.required"));
						} 
						else
						{
							bean.setUpdate_Rivised_original_Cur((bean.getUpdate_Rivised_original_Cur()).replaceAll(",",""));
							if (val.isValidNo(bean.getUpdate_Rivised_original_Cur()).equalsIgnoreCase("INVALID")) {
								addActionError(getText("errors.loss_Estimate_Revised_Orig_Curr.invalid"));
							}else{
								String ans = calcu.calculateClaimReversal(bean,"OutRes");
								if(Double.parseDouble(ans)!=Double.parseDouble(bean.getUpdate_Rivised_original_Cur())){
									this.addActionError(getText("error.calcul.mistake"));
									LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
								}else{
									bean.setUpdate_Rivised_original_Cur(ans);
								}
							}
							
						}
						if(StringUtils.isBlank(bean.getUpdate_Rivised_percentage())){
							addActionError(getText("error.update.revised"));
						}
						if(StringUtils.isNotBlank(bean.getUpdate_Rivised_percentage()) && StringUtils.isNotBlank(bean.getUpdate_Rivised_original_Cur())){
							bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
							double value = ((Double.parseDouble(bean.getUpdate_Rivised_percentage())*Double.parseDouble(bean.getSigned_Share()))/100);
							final double dround=Math.round(value*100.0)/100.0;
							final String valu=twoDigit.format(dround);
							if(Double.parseDouble(valu)<Double.parseDouble(bean.getUpdate_Rivised_original_Cur())){
								addActionError(getText("error.our.share.update.revised.value"));
							}
						}
						if(StringUtils.isBlank(bean.getUpdaterecordFees())){
							addActionError(getText("error.record.fees"));
						}
						else{
							if("Yes".equalsIgnoreCase(bean.getUpdaterecordFees())){
								if(StringUtils.isBlank(bean.getUpdatesurveyorAdjesterPerOC())){
									addActionError(getText("surveyor.error.per"));
								}
								else{
									bean.setUpdatesurveyorAdjesterPerOC(bean.getUpdatesurveyorAdjesterPerOC().replaceAll(",", ""));
								}
								if(StringUtils.isBlank(bean.getUpdatesurveyorAdjesterOurShareOC())){
									addActionError(getText("surveyor.error.our.share.per"));
								}
								else{
									bean.setUpdatesurveyorAdjesterOurShareOC(bean.getUpdatesurveyorAdjesterOurShareOC().replaceAll(",", ""));
									String ans = calcu.calculateClaimReversal(bean,"Surveyor");
									if(Double.parseDouble(ans)!=Double.parseDouble(bean.getUpdatesurveyorAdjesterOurShareOC())){
										this.addActionError(getText("error.calcul.mistake"));
										LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
									}else{
										bean.setUpdatesurveyorAdjesterOurShareOC(ans);
									}
								}
								if(StringUtils.isNotBlank(bean.getUpdatesurveyorAdjesterOurShareOC()) && StringUtils.isNotBlank(bean.getUpdatesurveyorAdjesterPerOC())){
									bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
									double value = ((Double.parseDouble(bean.getUpdatesurveyorAdjesterPerOC())*Double.parseDouble(bean.getSigned_Share()))/100);
									final double dround=Math.round(value*100.0)/100.0;
									final String valu=twoDigit.format(dround);
									if(Double.parseDouble(valu)<Double.parseDouble(bean.getUpdatesurveyorAdjesterOurShareOC())){
										addActionError(getText("error.our.share.value"));
									}
								}
								
								if(StringUtils.isBlank(bean.getUpdateotherProfessionalPerOc())){
									addActionError(getText("other.error.per"));
								}
								else{
									bean.setUpdateotherProfessionalPerOc(bean.getUpdateotherProfessionalPerOc().replaceAll(",", ""));
								}
								if(StringUtils.isBlank(bean.getUpdateprofessionalOurShareOc())){
									addActionError(getText("other.error.our.share.per"));
								}
								else{
									bean.setUpdateprofessionalOurShareOc(bean.getUpdateprofessionalOurShareOc().replaceAll(",", ""));
									String ans = calcu.calculateClaimReversal(bean,"Professional");
									if(Double.parseDouble(ans)!=Double.parseDouble(bean.getUpdateprofessionalOurShareOc())){
										this.addActionError(getText("error.calcul.mistake"));
										LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
									}else{
										bean.setUpdateprofessionalOurShareOc(ans);
									}
								}
								if(StringUtils.isNotBlank(bean.getUpdateotherProfessionalPerOc()) && StringUtils.isNotBlank(bean.getUpdateprofessionalOurShareOc())){
									bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
									double value = ((Double.parseDouble(bean.getUpdateotherProfessionalPerOc())*Double.parseDouble(bean.getSigned_Share()))/100);
									final double dround=Math.round(value*100.0)/100.0;
									final String valu=twoDigit.format(dround);
									if(Double.parseDouble(valu)<Double.parseDouble(bean.getUpdateprofessionalOurShareOc())){
										addActionError(getText("error.our.share.other.value"));
									}
								}
						}
						}
						if(StringUtils.isBlank(bean.getUpdaterecordIbnr())){
							addActionError(getText("update.error.record.ibnr"));
						}
						else{
							if("Yes".equalsIgnoreCase(bean.getUpdaterecordIbnr())){
								if(StringUtils.isBlank(bean.getUpdateibnrPerOc())){
									addActionError(getText("ibnr.error.per"));
								}
								else{
									bean.setUpdateibnrPerOc(bean.getUpdateibnrPerOc().replaceAll(",", ""));
								}
								if(StringUtils.isBlank(bean.getUpdateibnrOurShareOc())){
									addActionError(getText("ibnr.error.our.share.per"));
								}
								else{
									bean.setUpdateibnrOurShareOc(bean.getUpdateibnrOurShareOc().replaceAll(",", ""));
								}
								if(StringUtils.isNotBlank(bean.getUpdateibnrOurShareOc()) && StringUtils.isNotBlank(bean.getUpdateibnrPerOc())){
								bean.setSigned_Share(bean.getSigned_Share().replaceAll(",", ""));
								double value = ((Double.parseDouble(bean.getUpdateibnrPerOc())*Double.parseDouble(bean.getSigned_Share()))/100);
									final double dround=Math.round(value*100.0)/100.0;
									final String valu=twoDigit.format(dround);
								if(Double.parseDouble(valu)<Double.parseDouble(bean.getUpdateibnrOurShareOc())){
									addActionError(getText("error.our.share.ibnr.value"));
								}
							}
						}
						}
						if(StringUtils.isBlank(bean.getTotalReserveOSOC())){
							addActionError(getText("errors.total_reserveosoc.required"));
						}
						else{
							bean.setTotalReserveOSOC(bean.getTotalReserveOSOC().replaceAll(",",""));
							String ans = calcu.calculateClaimReversal(bean,"Total");
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getTotalReserveOSOC().replaceAll(",",""))){
								this.addActionError(getText("error.calcul.mistake"));
								LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.setTotalReserveOSOC(ans);
							}
						}
						if (val.isNull(bean.getUpdate_Reference()).equalsIgnoreCase("")) {
							addActionError(getText("errors.update_Reference.required"));
						}
						
						if(val.isNull(bean.getCliam_update_Date()).equalsIgnoreCase(""))
						{
							addActionError(getText("Error.UpdateDate.Requireed"))	;
						}
						else if(val.checkDate(bean.getCliam_update_Date()).equalsIgnoreCase("INVALID"))
						{
							addActionError(getText("Error.UpdateDate.DateError"));
						}
						else if(val.ValidateTwoDates(SERVICE.getClaimDate(bean,1),bean.getCliam_update_Date()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("Error.UpdateDate.Patdate"));
						}else if(val.ValidateTwoDates(SERVICE.getClaimDate(bean,4),bean.getCliam_update_Date()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("Error.UpdateDtLastPayDt"));
						}
						else if(val.belowCurrentDate(bean.getCliam_update_Date()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("Error.UpdateDate.CurrentDate"));
						}
						else if(val.ValidateTwoDates(SERVICE.getClaimDate(bean,2),bean.getCliam_update_Date()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("Error.UpdateDate.LossDate"));
						}
						else if(Validation.ValidateTwo(bean.getDate_of_Loss(),bean.getCliam_update_Date()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("errors.created_Date.invalid2"));
						}
						if(val.isNull(bean.getReservePositionDate()).equalsIgnoreCase(""))
						{
							addActionError(getText("errors.reservepositionDate.required"))	;
						}
						else if(val.checkDate(bean.getReservePositionDate()).equalsIgnoreCase("INVALID"))
						{
							addActionError(getText("errors.reservepositionDate.Invalid"));
						}
						else if(val.ValidateTwoDates(SERVICE.getClaimDate(bean,5),bean.getReservePositionDate()).equalsIgnoreCase("Invalid"))
						{
							addActionError(getText("errors.reservepositionDate.Error3"));
						}
						if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getCliam_update_Date()).equalsIgnoreCase("")){
							if(new DropDownControllor().Validatethree(branchCode, bean.getCliam_update_Date())==0){
								addActionError(getText("errors.open.period.date.Claim.update",new String[] {bean1.getOpenPeriodDate()}));
							}
							}
					}else if("No".equals(bean.getReverseClaimYN())){
						if("None".equals(bean.getCloseClaimYN())||"No".equalsIgnoreCase(bean.getCloseClaimYN())){
							addActionError(getText("claim.reserveNo"));
						}
						if(val.checkDate(bean.getClaim_closed_Date()).equalsIgnoreCase("INVALID")){
							addActionError(getText("claim.closed.date"));
						}else{
							/*bean.setPolicy_Contract_No((String)session.get("claim_contract_no"));
							String lostreserveDate=new DropDownControllor().GetLostReserveUpdateDate(bean.getPolicy_Contract_No(),bean.getClaim_No(),bean.getBranchCode());*/
							if(val.ValidateTwoDates(SERVICE.getClaimDate(bean,5),bean.getClaim_closed_Date()).equalsIgnoreCase("Invalid"))
							{
								addActionError(getText("Error.lost.reserve.Date"));
							}
						}
						/*if("Yes".equalsIgnoreCase(bean.getCloseClaimYN())){
							if(Double.parseDouble(StringUtils.isBlank(bean.getOsAmt())?"0":bean.getOsAmt())!=0){
								addActionError(getText("claim.close.osAmout"));
							}
							if(SERVICE.getClaimsAmount(bean, 7)!=0.0) {
								addActionError(getText("claim.close.osClaimAmout"));
							}
							if(SERVICE.getClaimsAmount(bean, 8)!=0.0) {
								addActionError(getText("claim.close.safClaimAmout"));
							}
							if(SERVICE.getClaimsAmount(bean, 9)!=0.0) {
								addActionError(getText("claim.close.pfClaimAmout"));
							}
					}*/
				}
			 }
			}
		public void	reviewValidation() {
			final Validation val = new Validation();			
				if(SERVICE.BusinessValidation(bean,6)){
					addActionError(getText("error.claimClosed"));
				}else{
					if (val.checkDate(bean.getReview_Date()).equalsIgnoreCase("INVALID")) {
						addActionError(getText("errors.review_Date.required"));
					}else if(val.belowCurrentDate(bean.getReview_Date()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.review_Date.invalid"));
					}
					else if(Validation.ValidateTwo(bean.getDate_of_Loss(),bean.getReview_Date()).equalsIgnoreCase("Invalid"))
					{
						addActionError(getText("errors.created_Date.invalid3"));
					}
					if (val.isNull(bean.getReview_Done_By()).equalsIgnoreCase("")) {		
						addActionError(getText("errors.review_Done_By.required"));
					}
				}			
		}
		public String deleteclaimDetails(){
			String forwards="";
			bean1.setBranchCode(branchCode);
			new DropDownControllor().getOpenPeriod(bean1);
			bean.setOpstartDate(bean1.getOpstartDate());
			bean.setOpendDate(bean1.getOpendDate());
			bean.setUserName(userName);
			new DropDownControllor().copyClaimDetailDatatoDeleteTable(bean);
			
			if("claimDisplay".equalsIgnoreCase(bean.getClaimDisplay())){
				 bean.setBranchCode(branchCode);
				 bean.setClaimlist(SERVICE.claimlist(bean));
				 bean.setClaimDisplay("claimDisplay");
				 forwards= "claimMaster";
			}else{
			
			bean.setPolicy_Contract_No(bean.getContarctno());				
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}
			else{
				session.put("mfrid",bean.getProductId());
			}
			session.put("claim_contract_no",bean.getPolicy_Contract_No());
			//session.put("claim_proposal_no",bean.getProposal_No());
			bean.setBranchCode(branchCode);	
			bean.setShortname(SERVICE.getShortname(bean));
			SERVICE.InitCliam(bean,1);
					CliamList = SERVICE.CliamList(bean, 1);
					String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
					if (CliamList.isEmpty() && userRights.indexOf("CN")!=-1) {
						session.remove("Cliam_cliam_no");
						session.put("IntialMode","IntialMode");
						bean.setMenuStatus("N");
						bean.setClaim_No("");
						forwards = "CliamInitAction";
					} else {
						session.remove("IntialMode");
						forwards ="ClaimListAction";
					}	
			}
		return forwards;
			
		}
		private String dateFormat(String name){
			String arr[]=name.split("/");
			return arr[1]+"/"+arr[0]+"/"+arr[2];
			}
		
		public String cedentCheck(){
			bean.setMode("cedent");
			String forwards="CliamInitAction";
			bean.setBranchCode(branchCode);
			session.put("subprofitID",bean.getSubProfitId());
			bean.setOldClaimNumber(SERVICE.cedentNumberCheck(bean));
			//bean.setStatus_of_claim("Open");
			boolean flag=false;
				bean.setPolicy_Contract_No((String)session.get("claim_contract_no"));
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				else{
					session.put("mfrid",bean.getProductId());
				}
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
				bean.setBranchCode(branchCode);
				bean1.setBranchCode(branchCode);
				bean.setLoginId(userId);
				bean1.setLoginid(userId);
				new DropDownControllor().getOpenPeriod(bean1);
				if (bean.getBusinessMode() != null && bean.getBusinessMode().equalsIgnoreCase("NewCliam")) {
					session.put("IntialMode","IntialMode");
					session.remove("Cliam_cliam_no");
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					 //bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,productId,"Y","","","","",""));
					 bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
					 bean.setPredepartIdlist(new DropDownControllor().getClaimDepartmentDropDown(branchCode,productId,"Y",bean));
					 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getClaimdepartId(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));		
					 //bean.setSubProfitList(dropDownController.getSubProfitCentreDropDown(bean.getDeptId(),branchCode,productId));
					//bean.setStatus_of_claim("Opened");
					//bean.setStatus_of_claim("Open");
					 if(bean.getPolicy_Contract_No()!=null &&"3".equalsIgnoreCase((String)session.get("mfrid")))
						{
							bean.setDepartmentClassList(new DropDownControllor().getDepartIdlist(bean.getPolicy_Contract_No(),branchCode,session.get("mfrid").toString(),(String)session.get("DepartmentId"),bean.getLayerNo()));
						}
					bean.setAdvice_UW("No");
					bean.setAdvice_Mangement("No");
					bean.setRi_Recovery("Yes");
					bean.setPath("Amode");
					bean.setMenuStatus("N");
					forwards="CliamInitAction";
				}
				
				return forwards;
		}
		public String reserveCheck(){
			bean.setPolicy_Contract_No((String)session.get("claim_contract_no"));
			//bean.setProposal_No((String)session.get("claim_proposal_no"));
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}
			else{
				session.put("mfrid",bean.getProductId());
			}
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"":(String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			bean1.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean1.setLoginid(userId);
			bean.setClaim_No(session.get("Cliam_cliam_no").toString());
			RevisionList=SERVICE.CliamList(bean, 2);
		    ClaimList =SERVICE.CliamList(bean, 3);
		    ClaimPayment=SERVICE.CliamList(bean, 4);
		    errorInsert();
			 if(bean.getClaim_No()!=null && bean.getPolicy_Contract_No()!=null)
				{
		 	 		SERVICE.InitCliam(bean,10);
			 	}
			if("Yes".equalsIgnoreCase(bean.getCloseClaimYN()) && "No".equals(bean.getReverseClaimYN())){
				boolean status=false;
				if(Double.parseDouble(StringUtils.isBlank(bean.getOsAmt())?"0":bean.getOsAmt())!=0.0){
					bean.setReserveCount("1");
				}
				if(SERVICE.getClaimsAmount(bean, 7)!=0.0) {
					bean.setReserveCount("1");
					status=false;
				}else{
					status=true;
				}
				if(SERVICE.getClaimsAmount(bean, 8)!=0.0) {
					bean.setReserveCount("1");
					status=false;
				}else{
					status=true;
				}
				if(SERVICE.getClaimsAmount(bean, 9)!=0.0) {
					bean.setReserveCount("1");
					status=false;
				}else{
					status=true;
				}
				if(status){
					bean.setReserveCount("0");
				}
		}else{
			bean.setReserveCount("0");
		}
			
			return "CliamInitAction";
		}
		
		public String reInsPopUp(){
			bean.setBranchCode(branchCode);
			SERVICE.InitCliam(bean, 1);
			bean.setXlPremiumList(SERVICE.XlPremiumList(bean,countryId));
			bean.setClaimPaidList(SERVICE.ClaimPaidList(bean,countryId));
			bean.setShortname(SERVICE.getShortname(bean));
			return "reinsPopUp";
		}
		
		public String reInsCalculation(){
			bean.setBranchCode(branchCode);
			String [] arr= bean.getSelectedRows().split(",");
			double sum=0;
			for(int i=1;i<arr.length;i++){
				if(StringUtils.isNotBlank(bean.getClaimBookedPremium().get(Integer.parseInt(arr[i])))){
					String ans = calcu.calculateClaimReInsPopUp(bean,"Booked",arr[i]);
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getClaimBookedPremium().get(Integer.parseInt(arr[i])).replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake.cal"));
						LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
						sum = sum+Double.parseDouble(ans);
					}else{
						bean.getClaimBookedPremium().set(Integer.parseInt(arr[i]),ans);
						sum = sum+Double.parseDouble(ans);
					}
				}
				if(StringUtils.isNotBlank(bean.getClaimExcRate().get(Integer.parseInt(arr[i])))){
					String ans = calcu.calculateClaimReInsPopUp(bean,"Exchange",arr[i]);
					if(Double.parseDouble(ans)!=Double.parseDouble( DropDownControllor.formatter(bean.getClaimExcRate().get(Integer.parseInt(arr[i])).replaceAll(",","")).replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						//bean.getClaimExcRate().set(Integer.parseInt(arr[i]),ans);
					}
				}
			}
				if(StringUtils.isNotBlank(bean.getTotalBookedPremium())){
					String result =  DropDownControllor.formattereight(Double.toString(sum)).replaceAll(",", "");
					LOGGER.info("Jsp Calculation Exchange Rate  "+bean.getTotalBookedPremium());
					if(Double.parseDouble(result)!=Double.parseDouble(bean.getTotalBookedPremium().replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.setTotalBookedPremium(result);
					}
					LOGGER.info("Jsp Calculation Exchange Rate  "+result);
				}
			/*}*/
			for(int i=0;i<bean.getClaimPaidPremium().size();i++){
				if(StringUtils.isNotBlank(bean.getClaimPaidPremium().get(i))){
					String ans = calcu.calculateClaimReInsPopUp(bean,"ClaimPaid",Integer.toString(i));
					if(Double.parseDouble(ans)!=Double.parseDouble(bean.getClaimPaidPremium().get(i).replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getClaimPaidPremium().set(i,ans);
					}
				}
				if(StringUtils.isNotBlank(bean.getClaimEcxhangeRate().get(i))){
					String ans = calcu.calculateClaimReInsPopUp(bean,"ClaimExchan",Integer.toString(i));
					if(Double.parseDouble(ans)!=Double.parseDouble( DropDownControllor.formatter(bean.getClaimEcxhangeRate().get(i).replaceAll(",","")).replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						LOGGER.info("Insertion Failed. Please retry. If problem persists, please contact support.");
					}else{
						//bean.getClaimEcxhangeRate().set(i,ans);
					}
				}
			}
			if(!hasActionErrors()){
			bean.setReinstatementList(SERVICE.getReInstatementTypeList(bean));
			/*List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for(int i=0;i<bean.getPaidSno().size();i++){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("0","");
				list.add(map);
				
			}*/
			
			bean.setReinsCalVal(SERVICE.getReInsValue(bean));
			}
			return "dropdownajax";
		}
		public String submitReIns(){
			String forward="streamResult";
			String value="";
			if(bean.getReinsCalVal()==null ){
				bean.setReinsCalVal("0");
			}
			String val = DropDownControllor.formatter(Double.toString(Double.parseDouble(bean.getPaid_claim_os().replaceAll(",", ""))+Double.parseDouble(bean.getSurveyor_fee_os().replaceAll(",", ""))+Double.parseDouble(bean.getOther_prof_fee_os().replaceAll(",", ""))-Double.parseDouble(bean.getReinsCalVal().replaceAll(",", ""))));
			value="<script type='text/javascript'>window.opener.claims.reinstPremiumOCOS.value='"+bean.getReinsCalVal()+"'; window.opener.claims.paid_Amount_Orig_curr.value='"+ val +"';window.opener.claims.disabledFields.value='Y'; window.close();</script>" ; 
			byte[] byteArray = value.getBytes();
			inputStream = new ByteArrayInputStream(byteArray);
			return forward;
		}
		
		public String cedentDetails(){
			bean.setBranchCode(branchCode);
			bean.setCedantNoList(SERVICE.CedentNoList(bean));
			return "cedentDetails";
		}
		
		public String viewAuth(){
			bean.setBranchCode(branchCode);
			bean.setPaymentNoList(SERVICE.getPaymentNoList(bean));
			return "claimAuth";
		}
		
		public String viewAuthAjax(){
			bean.setBranchCode(branchCode);
			bean.setClaimView(SERVICE.getClaimAuthView(bean));
			if("detail".equalsIgnoreCase(bean.getMode())){
				return "ajaxClaimDetail";
			}else{
				return "ajaxClaimAuth";
			}
		}
		
}
