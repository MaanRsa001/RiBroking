package com.maan.action;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.action.admin.AdminAction;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.bean.PremiumBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.FaculPremiumService;
import com.maan.service.ProportionalPremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class FaculPremiumAction extends ActionSupport implements ModelDriven<FaculPremiumBean>
	{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogUtil.getLogger(AdminAction.class);
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	FaculPremiumBean bean=new FaculPremiumBean();
	FaculitivesBean bean1=new FaculitivesBean();
	private transient final FaculPremiumService SERVICE=new FaculPremiumService();
	private transient final ProportionalPremiumService SERVICE1=new ProportionalPremiumService();
	DropDownControllor dropDownController=new DropDownControllor();
	private String productId=(String) session.get("mfrid")==null?"":(String) session.get("mfrid");
	private String userType=(String) session.get("userType")==null?"":(String) session.get("userType");
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String countryId=(String) session.get("countryId")==null?"":session.get("countryId").toString();
	private String branchCode=(String) session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String userName=(String) session.get("UserName")==null?"":(String) session.get("UserName");
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	public FaculPremiumBean getModel() {
		return bean;
	}	
	private List<FaculPremiumBean> preList;	
	private List<FaculPremiumBean> allocatedList;
	private List<FaculPremiumBean> brokerAndCedingName;
	private String dropDown;
	private String menuId;
	private InputStream inputStream;
	
	public List<FaculPremiumBean> getAllocatedList() {
		return allocatedList;
	}
	public void setAllocatedList(List<FaculPremiumBean> allocatedList) {
		this.allocatedList = allocatedList;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}

	public List<FaculPremiumBean> getPreList() {
		return preList;
	}
	public void setPreList(List<FaculPremiumBean> preList) {
		this.preList = preList;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@SuppressWarnings("unchecked")
	public String premiumList()
		{
		String forward="premiumList";
		String editMode="";
		if(StringUtils.isNotBlank(bean.getDepartmentId())){
			session.put("DepartmentId",bean.getDepartmentId());
		}else{
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
		}
			bean.setBranchCode(branchCode);	
			bean1.setBranchCode(branchCode);
		if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
			bean.setProductId(productId);	
		}else{
			session.put("mfrid",bean.getProductId());
			productId = bean.getProductId();
		}
		bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
		if(StringUtils.isNotBlank(bean.getProposal_No())){
		 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
		}
		if(!"N".equalsIgnoreCase(editMode) && (null==bean.getMultiuserError() || StringUtils.isBlank(bean.getMultiuserError()) )){
			bean.setMultiuserError("error");
			if("premiumDisplay".equalsIgnoreCase(bean.getPremiumDisplay())){
				bean.setType("premium");
				forward = "masterList";
			}else{
			bean.setFlag("C");
			forward = "portfoliList";
			}
		}else{
		
			new DropDownControllor().getOpenPeriod(bean1);
			bean.setOpstartDate(bean1.getOpstartDate());
			bean.setOpendDate(bean1.getOpendDate());
			preList=SERVICE.getPremiumedList(bean); 	
			bean.setPreTempList(SERVICE.getPremiumTempList(bean));
	        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
	        if(bean.getMode()!=null && bean.getMode().equalsIgnoreCase("premiumMaster")){
	        	bean.setPremiumMasterMode(bean.getPremiumDisplay());
	        }
	        if(preList.isEmpty() && bean.getPreTempList().isEmpty() && userRights.indexOf("PN")!=-1)
	        {	
	        	 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
	        	 bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
	        	 //bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString()));
				  bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
	        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
	 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
	        	 SERVICE.ContractDetails(bean,countryId); 
	        	 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));		 	    
		 	     bean.setCurrency(bean.getBaseCurrencyId());
		         bean.setEnteringMode("2");
		         bean.setMenuStatus("N");
		         bean.setMode("add");
		         forward = "editPremium";
	        }
	       /* else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
	        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
	        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
	 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
	        	 SERVICE.ContractDetails(bean,countryId);
				 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
	        	 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));		 	    
		 	     bean.setCurrency(bean.getBaseCurrencyId());
		         bean.setEnteringMode("2");
		         bean.setMenuStatus("N");
		         bean.setMode("add");
	        	 return "editPremium";	
	        }*/
	        else
	        {	        	
		        SERVICE.GetPreList(bean);
		        bean.setSize("list");
		        
		       
	        }	
		 	
		}
		return forward;
		} 
	public String editPremium()
	{		
		String forward="editPremium";
		String editMode ="";
		if(StringUtils.isNotBlank(bean.getDepartmentId())){
			session.put("DepartmentId",bean.getDepartmentId());
		}else{
			bean.setDepartmentId(SERVICE.getDepartmentId(bean));
			session.put("DepartmentId",bean.getDepartmentId());
		}
		bean.setPredepartment(bean.getDepartmentId());
		
		if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
			bean.setProductId(productId);	
		}else{
			session.put("mfrid",bean.getProductId());
			productId = bean.getProductId();
		}
		bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
		if(StringUtils.isNotBlank(bean.getProposal_No())){
		 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
		}
		if(!"N".equalsIgnoreCase(editMode)){
			bean.setMultiuserError("error");
			if("premiumDisplay".equalsIgnoreCase(bean.getPremiumDisplay())){
				bean.setType("premium");
				forward = "masterList";
			}else{
			premiumList();
			forward = "premiumList";
			}
		}else{
			//validatepremium();
		 	setSessionValuNull();
	    	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));	
	    	bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
	    	if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}
	    	bean.setBranchCode(branchCode);
	        bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
	        bean.setContractPremium(SERVICE.GetContractPremium(bean));
	        bean.setContractDeptId(dropDownController.getContractLayerNo(bean.getContNo(),bean.getDepartmentId(),branchCode,"1",bean.getProposal_No()));
	        if(StringUtils.isBlank(bean.getContractDeptId())){
	        	bean.setBranchCode(branchCode);
	    		bean.setProductIdList(dropDownController.productIdList(branchCode));
	    		bean.setFlag("new");
	    		addActionError("Contract is not available");
	    		bean.setPredepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"2","Y","","","","",""));
	    		forward= "premiummaster";
	        }
	        else{
	        SERVICE.ContractDetails(bean,countryId);
			bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,(String)session.get("mfrid"),bean.getConsubProfitId()));
	        bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));
	        bean.setMenuStatus("N");
	        if("edit".equals(bean.getMode()) || "transEdit".equalsIgnoreCase(bean.getMode()))
	        {
			SERVICE.PremiumEdit(bean,countryId);	
			if( "transEdit".equalsIgnoreCase(bean.getMode())){
				bean.setTransList(dropDownController.getTransactionList(bean.getBranchCode(),bean.getProposal_No(),bean.getTransaction(),bean.getProductId()));
			}
	        }
	        else
	        {
	        	  bean.setCurrency(bean.getBaseCurrencyId());
	        }
	        }
		}
		bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
	        return forward;
			
	}
	@SuppressWarnings("static-access")
	public String insertPremium()
	{			
				String editMode="";
				
				bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}else{
					session.put("mfrid",bean.getProductId());
					productId = bean.getProductId();
				}
				if(StringUtils.isBlank(bean.getDepartmentId())){
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				}
				else{
					session.put("DepartmentId", bean.getDepartmentId());
				}
				bean.setBranchCode(branchCode);
		        bean1.setBranchCode(branchCode);
				bean.setUserType(userType);
				bean.setLoginId(userId);
				if(StringUtils.isBlank(bean.getProposal_No())){
					bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
				}
				if(StringUtils.isNotBlank(bean.getProposal_No())){
					 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
				}
				bean.setPreamendmentDate(new DropDownControllor().getpremiumPreamendDate(bean));
				new DropDownControllor().getOpenPeriod(bean1);
				if("N".equalsIgnoreCase(editMode)){
					validateFaculPremium();
				}
	    		 if (!hasActionErrors() && "N".equalsIgnoreCase(editMode)) 
				 {
	    			 GetBonusDeatail();
	    			 if("EP".equalsIgnoreCase(bean.getAccount_Period()) || "RVP".equalsIgnoreCase(bean.getAccount_Period())||"RTP".equalsIgnoreCase(bean.getAccount_Period()))
			        	{
			        		bean.setInstlmentNo(bean.getAccount_Period());	        		
			        	}else if(!bean.getAccount_Period().equalsIgnoreCase(""))// && StringUtils.isBlank(bean.getTransactionNo()))
			        	{      
			        		final String[] InstalmentNo=bean.getAccount_Period().split("_");
	    			       	bean.setInstlmentNo(InstalmentNo[0]);
	    			       	bean.setInstalmentdate(InstalmentNo[1]);
			        	}
			    		if("add".equalsIgnoreCase(bean.getMode()) || "transEdit".equalsIgnoreCase(bean.getMode())) {
						     SERVICE.InsertPremium(bean);
			    		 }
			    		 else if("edit".equalsIgnoreCase(bean.getMode()))
			    		 {
				    		 SERVICE.UpdatePremium(bean);
			    		 }
			    		 SERVICE.ContractDetails(bean,countryId);
						  bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
						  SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
						 setSessionValuNull();
						return "PremiumSucuss";
				 }
				 else
				 {	 
					 LOGGER.info("##########Validation Message Start###########");
						Iterator<String> error = getActionErrors().iterator();
						while(error.hasNext()){
							LOGGER.info(error.next());
						}
						LOGGER.info("##########Validation Message End###########");
					 if(!"N".equalsIgnoreCase(editMode)){
							bean.setMultiuserError("error");
					 }
					 SERVICE.ContractDetails(bean,countryId);
					  bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,(String)session.get("mfrid"),bean.getConsubProfitId()));
					 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));			 
					 bean.setStatus(bean.getStatus());				
				 }    
		return "editPremium";
	}
	public String instalmentPremium()
	{
	bean.setPremiumQuotaShare(SERVICE.GetInstalmentAmount(bean.getContNo(),bean.getInstalmentdate()));
	return "dropdownajax";				
	}
	public String premiumView()
	{
		if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
			bean.setProductId(productId);	
		}
		bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
	        bean.setBranchCode(branchCode);			
			SERVICE.ContractDetails(bean,countryId);
			SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
			brokerAndCedingName = SERVICE.getBrokerAndCedingName(bean);
			allocatedList=SERVICE.getAllocatedList(bean); 
			bean.setCurrencyList(SERVICE.currencyList(bean));
		return "PremiumSucuss";
	}
	
	public String deletePremium(){
		{
			bean.setUserName(userName);
			new DropDownControllor().copyDatatoDeleteTable(bean);
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}
				bean.setBranchCode(branchCode);	
				bean1.setBranchCode(branchCode);
				new DropDownControllor().getOpenPeriod(bean1);
				bean.setOpstartDate(bean1.getOpstartDate());
				bean.setOpendDate(bean1.getOpendDate());
				preList=SERVICE.getPremiumedList(bean); 
				bean.setPreTempList(SERVICE.getPremiumTempList(bean));
		        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
		        if(preList.isEmpty()&& bean.getPreTempList().isEmpty() && userRights.indexOf("PN")!=-1)
		        {	
		        	 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
		 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
		        	 SERVICE.ContractDetails(bean,countryId); 
					 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
		        	 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));		 	    
			 	     bean.setCurrency(bean.getBaseCurrencyId());
			         bean.setEnteringMode("2");
			         bean.setMenuStatus("N");
			         bean.setMode("add");
			         bean.setTransactionNo("");
		        	 return "editPremium";
		        }
		        /*else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
		        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
		 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
		        	 SERVICE.ContractDetails(bean,countryId); 
					 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
		        	 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0"));		 	    
			 	     bean.setCurrency(bean.getBaseCurrencyId());
			         bean.setEnteringMode("2");
			         bean.setMenuStatus("N");
			         bean.setMode("add");
			         bean.setTransactionNo("");
		        	 return "editPremium";	
		        }*/
		        else
		        {	        	
			        SERVICE.GetPreList(bean);
			        bean.setSize("list");
			        
			       
		        }
			 	return "premiumList";
			} 
	}
	
	private void validateFaculPremium()
	{
		final Validation val=new Validation();
		boolean flag=false;
		boolean cashLossCrFlag=false;
		List retroList=SERVICE.getSPRetroList(bean.getContNo(), bean.getProductId(), bean.getLayerno());
			 if(retroList!=null&&retroList.size()>0)
			 {
				 Map map=(Map) retroList.get(0);				 
				 bean.setProductId(bean.getProductId());
				 bean.setSpRetro(map.get("SP_RETRO")==null?"N":map.get("SP_RETRO").toString());
				 bean.setNoOfRetro(map.get("NO_OF_INSURERS")==null?"0":map.get("NO_OF_INSURERS").toString());
				 bean.setProposal_No(map.get("RSK_PROPOSAL_NUMBER")==null?"0":map.get("RSK_PROPOSAL_NUMBER").toString());
				 if("Y".equalsIgnoreCase(bean.getSpRetro()))
				 {
					 List insList=SERVICE.getRetroContracts(bean);
					 if(insList!=null && insList.size()>0){
						 for(int i=0;i<insList.size();i++){
							 Map insMap=(Map)insList.get(i);
							 if("C".equals(insMap.get("TYPE")))//"C" Retro Contract
							 {
								 bean.setRetroContractNo(insMap.get("CONTRACT_NO").toString());
								 String string= SERVICE.getSumOfShareSign(bean);
								 if(Double.parseDouble(string)!=100){
									 addActionError(getText("errors.retroNotCompleted",new String[] {bean.getRetroContractNo()}));
								 }
							 }							
						 }
					 }
				 }
			 }
			 //bean.setOtherCost((bean.getOtherCost()).replaceAll(",",""));
			 if(!(bean.getPremiumQuotaShare()).equalsIgnoreCase(""))
					    {
					   	  bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",",""));
					   	  if(val.numbervalid(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID"))
					   	  {
					   		addActionError(getText("Errors.PremiumQuota.Invalid"));
					   	  }else
					   	  {
					   		  double gwpi=Double.parseDouble(bean.getGwpiOS());
					   		  double sumPaidPre=Double.parseDouble(bean.getSum_of_paid_premium());
					   		  double premium=Double.parseDouble(bean.getPremiumQuotaShare());					   		 
					   		  if("1".equals(bean.getEnteringMode()))
					   		  {
					   			premium=(premium*Double.parseDouble(bean.getShareSigned())/100.0);
					   		  }
					   		if(!"edit".equalsIgnoreCase(bean.getMode())){
					   		  DecimalFormat formatter = new  DecimalFormat("#.##");
					   		  double preBal=Double.parseDouble(formatter.format((gwpi-sumPaidPre)));                             
					   		  if(preBal<premium)
					   		  {
					   			//addActionError(getText("errors.PremiumGreterGwpi.Invalid"));
					   		  }
					   		}
					   	  }
					    }	
			 if("RI02".equalsIgnoreCase(sourceId)){
			 if(StringUtils.isBlank(bean.getTaxDedectSource())){
				 addActionError(getText("taxdedct.source.invalid"));
			 }
			 else{
				 bean.setTaxDedectSource(bean.getTaxDedectSource().replaceAll(",", ""));
				 if(val.numbervalid(bean.getTaxDedectSource()).equalsIgnoreCase("INVALID"))
				 {
					 addActionError("error.taxdedect.number");
				 }
			 }
			 }
					 if(val.isNull(bean.getEnteringMode()).equalsIgnoreCase(""))
					 {
					 flag=true;
					 addActionError(getText("errors.mode.reqired")); 
					 }	 
				 	 if(val.isNull(bean.getCurrency()).equalsIgnoreCase("0"))
					 {						 
				 		addActionError(getText("errors.currency.requireds"));							
					 }
					 if(val.isNull(bean.getExchRate()).equalsIgnoreCase(""))
					 {
						 addActionError(getText("errors.exchRate.required"));
					 }
					if(StringUtils.isBlank(bean.getRi_cession())){
						addActionError(getText("errors.ricession.required"));
					}
					boolean dateflag=true; 
					boolean statDate=true;
					if(val.isNull(bean.getInception_Date()).equalsIgnoreCase(""))
					{
						addActionError(getText("errors.statRecDate.required"));
							dateflag=false;
							statDate=false;
					}
					else if(val.checkDate(bean.getInception_Date()).equalsIgnoreCase("INVALID"))
					{
						addActionError(getText("errors.statRecDate.invalid"));
							dateflag=false;
							statDate=false;
					}else if(Validation.ValidateTwo(bean.getStatementDate(),bean.getInception_Date()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.premium.statRecDate"));
						
					}
					if(val.isNull(bean.getStatementDate()).equalsIgnoreCase(""))
					{
						addActionError(getText("errors.statDate.required"));
							dateflag=false;
							statDate=false;
					}
					else if(val.checkDate(bean.getStatementDate()).equalsIgnoreCase("INVALID"))
					{
						addActionError(getText("errors.statDate.invalid"));
							dateflag=false;
							statDate=false;
					}else if(Validation.ValidateTwo(bean.getInsDate(),bean.getStatementDate()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.premium.statDate"));
						
					}
					if("edit".equalsIgnoreCase(bean.getMode()) && !"Temp".equalsIgnoreCase(bean.getTableType())){
						bean.setMaxDate(Validation.getMaxDateValidate(bean.getTransaction(), bean.getPreamendmentDate()));
					if(val.isNull(bean.getAmendmentDate()).equalsIgnoreCase(""))
					{
						addActionError(getText("errors.amendmentDate.required"));
							dateflag=false;
							statDate=false;
					}
					else if(val.checkDate(bean.getAmendmentDate()).equalsIgnoreCase("INVALID"))
					{
						addActionError(getText("errors.amendmentDate.invalid"));
							dateflag=false;
							statDate=false;
					}else if(Validation.ValidateTwo(bean.getMaxDate(),bean.getAmendmentDate()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.premium.amendDate"));
						
					}
					if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAmendmentDate()).equalsIgnoreCase("")){
						if(new DropDownControllor().Validatethree(branchCode, bean.getAmendmentDate())==0){
							addActionError(getText("errors.open.period.date.trans.amend",new String[] {bean1.getOpenPeriodDate()}));
						}
					}
					}
					if(val.isNull(bean.getTransaction()).equalsIgnoreCase(""))
					{
						addActionError(getText("errors.transaction.required"));			
					}
					else if(val.checkDate(bean.getTransaction()).equalsIgnoreCase("INVALID"))
					{
						addActionError(getText("errors.transaction.invalid"));

					}else if(dateflag && Validation.ValidateTwo(bean.getInception_Date(),bean.getTransaction()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.premium.transaction"));
					}else if(dateflag && Validation.ValidateTwo(bean.getAcceptenceDate(),bean.getTransaction()).equalsIgnoreCase("invalid"))
					{
						addActionError(getText("errors.premium.acDate",new String[]{bean.getAcceptenceDate()}));
					}
					else if(!"superuser".equalsIgnoreCase(bean.getUserType()))
					{
						String morRepMaxDate=SERVICE.getMovementReportMaxDate(bean.getBranchCode());
						if(StringUtils.isBlank(bean.getTransactionNo())&&StringUtils.isNotBlank(morRepMaxDate)&&Validation.ValidateTwo(morRepMaxDate,bean.getTransaction()).equalsIgnoreCase("invalid")){
							addActionError(getText("errors.tranDate.invalid"));
						}
					}
					if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getTransaction()).equalsIgnoreCase("") && !"edit".equalsIgnoreCase(bean.getMode())){
						if(new DropDownControllor().Validatethree(branchCode, bean.getTransaction())==0){
							addActionError(getText("errors.open.period.date.trans",new String[] {bean1.getOpenPeriodDate()}));
						}
						}
					if(!val.isNull(bean.getReceipt_no()).equalsIgnoreCase(""))
						 {
							 flag=true;						   
							 if(val.numbervalid(bean.getReceipt_no()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.receipt_no.Check"));			 
							 }
									
						 }	
						 if(val.isSelect(bean.getAccount_Period()).equalsIgnoreCase(""))
						 {
							 flag=true;
							 addActionError(getText("errors.account_Period.required"));
						 }else //if(StringUtils.isBlank(bean.getTransactionNo()))
						 {
							 String[] ins=bean.getAccount_Period().split("_");
							 if((!"EP".equalsIgnoreCase(ins[0]) && !"RTP".equalsIgnoreCase(ins[0]) && !"RVP".equalsIgnoreCase(ins[0]))){
							/* List list=SERVICE.getMandDInstallments(bean.getContNo(),bean.getLayerno());
							 int num=Integer.parseInt(ins[0]);
							 if(num!=1)
							 {
								 if(list!=null &&list.size()>0)
								 {
									 int j=1;
									 while(j<num){
										 boolean inst=true;
										 for(int i=0;i<list.size();i++)
										 {
											 Map map=(Map)list.get(i);
											 if(Integer.parseInt((String)(map.get("INSTALMENT_NUMBER")==null?"0":map.get("INSTALMENT_NUMBER")))==j)
											 {
												 inst=false;
												 break;
											 }
										 }
										 if(inst)
										 {
											 addActionError(getText("errors.MandD.invalid",new String[]{Integer.toString(j)}));
										 }
										 j++;
									 }
								 }else
								 {
									 for(int i=1;i<num;i++){
										 addActionError(getText("errors.MandD.invalid",new String[]{Integer.toString(i)}));
									 }
								 }
							 }*/
							/* if(!bean.getCurrency().equals(bean.getBaseCurrencyId())){
								 addActionError(getText("errors.MandD.currencyInvalid");
							 }*/
							 if(!"2".equals(bean.getEnteringMode())){
								 addActionError(getText("errors.MandD.enteringModeInvalid",new String[]{bean.getShareSigned()}));
							 }
						  }
						  else
						  {
							  if(Double.parseDouble(bean.getPremiumQuotaShare())==0|| bean.getPremiumQuotaShare().equalsIgnoreCase(""))
								 {
									 addActionError(getText("Errors.PremiumQuota.Zero"));
								 }
						  }
						 } 
						
						 if(!val.isNull(bean.getCommissionQuotaShare()).equalsIgnoreCase(""))
						 {
							 flag=true;						
							 bean.setCommissionQuotaShare((bean.getCommissionQuotaShare()).replaceAll(",",""));
							 if(val.numbervalid(bean.getCommissionQuotaShare()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.commission.Check")); 
							 } 
						 }						  
						 
						 if(!val.isNull(bean.getTax()).equalsIgnoreCase(""))
						 {
							 flag=true;						
							 bean.setTax((bean.getTax()).replaceAll(",",""));
							 if(val.numbervalid(bean.getTax()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.tax.check"));
							 }
						 }	
						 if(StringUtils.isBlank(bean.getWithHoldingTaxOC())){
								addActionError(getText("with.tax.empty")); 
							 }
							 else{
								 bean.setWithHoldingTaxOC(bean.getWithHoldingTaxOC().replaceAll(",", ""));
								 if(val.numbervalid(bean.getWithHoldingTaxOC()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError("error.withtax.number");
								 }	
							 }
						 if(StringUtils.isBlank(bean.getBonus())){
								addActionError(getText("with.bonus.empty")); 
							 }
							 else{
								 bean.setBonus(bean.getBonus().replaceAll(",", ""));
								 if(val.numbervalid(bean.getBonus()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError("error.bonus.number");
								 }	
							 }
						 if("RI02".equalsIgnoreCase(sourceId)){
							 if(StringUtils.isBlank(bean.getServiceTax())){
								addActionError(getText("servicetax.empty")); 
							 }
							 else{
								 bean.setServiceTax(bean.getServiceTax().replaceAll(",", ""));
								 if(val.numbervalid(bean.getServiceTax()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError(getText("error.servicetax.number"));
								 }	
							 }
						 }
						 if(!val.isNull(bean.getBrokerage()).equalsIgnoreCase(""))
						 {
							 flag=true;							
							 bean.setBrokerage((bean.getBrokerage()).replaceAll(",",""));
							 if(val.numbervalid(bean.getBrokerage()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.brokerage.check"));
							 }
						 }						 
						 if(val.isNull(bean.getBrokerage()).equalsIgnoreCase(""))
						 {
							 flag=true;
						 }										
					bean.setOtherCost((bean.getOtherCost()).replaceAll(",",""));
					bean.setTotalCredit((bean.getTotalCredit()).replaceAll(",",""));
					bean.setTotalDebit((bean.getTotalDebit()).replaceAll(",",""));
					if(!flag)
					{
						addActionError(getText("errors.currency.select"));	 
					}	
					if("transEdit".equalsIgnoreCase(bean.getMode())){
						if(StringUtils.isBlank(bean.getTransDropDownVal()) && "Yes".equalsIgnoreCase(bean.getChooseTransaction())){
							addActionError(getText("resersel.trans"));
						}
					}
		 		}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
		}
	public void setBrokerAndCedingName(List<FaculPremiumBean> brokerAndCedingName) {
		this.brokerAndCedingName = brokerAndCedingName;
	}
	public List<FaculPremiumBean> getBrokerAndCedingName() {
		return brokerAndCedingName;
	}
	public String bonusviewPopup(){
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		SERVICE.PremiumContractDetails(bean, countryId);
		if(StringUtils.isBlank(bean.getCurrencyName())) {
			bean.setCurrencyName(SERVICE1.getCurrencyShortName(bean));
		}
		if(StringUtils.isBlank(bean.getSubProfit_center()) && !"ALL".equalsIgnoreCase(bean.getDepartmentId())){
			bean.setSubProfit_center(new DropDownControllor().getPopUpDeptName(branchCode, bean.getDepartmentId(),productId));
		}
		if(StringUtils.isBlank(bean.getTransactionNo()) || "bonusRage".equalsIgnoreCase(bean.getMode()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())) {
		bean.setBonusCommission(SERVICE.bonusdetails(bean, countryId));
		}
		if (StringUtils.isNotBlank(bean.getMode()) && "bonus".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isNotBlank(bean.getTransactionNo()) && !"recal".equalsIgnoreCase(bean.getFlag())) {
				if(bean.getPremiumOC() ==null ||  StringUtils.isBlank(bean.getPremiumOC().get(0))){
					bean.setBonusCommission(SERVICE.bonusdetails(bean, countryId));
				}
			}
		}
		//GetBonusDeatail();
		if(bean.getPremiumOC() ==null || StringUtils.isBlank(bean.getPremiumOC().get(0)) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
		SERVICE.GetFieldValues(bean);
		}
		if(StringUtils.isBlank(bean.getTransactionNo()) || "bonusRage".equalsIgnoreCase(bean.getMode()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
		ConvertedCurrencyValue();
		}
		else{
			String bonusExchangeRate="";
			for(int i=0;i<bean.getExchRatePrem().size();i++) {
				 String ExchangeRate = bean.getExchRatePrem().get(i);
				String shortName = bean.getCurrencyShortName().get(i);
				bonusExchangeRate += shortName+"~"+ExchangeRate+",";
				
			}
			bean.setBonusExchangeRate(bonusExchangeRate);
		}
		session.put("PreCurrencylist", bean.getPreCurrencylist());
		return "bonusviewPopup";
	}
	public void ConvertedCurrencyValue() {
		String ExchangeRate;
		String premium;
		String claim;
		String claimout;
		String shortName;
		String bonusExchangeRate="";
		double premiumOC=0.00;
		double claimOC=0.00;
		double claimoutOC=0.00;
		double claimRatioOC=0.00;
		double bonusOC=0.00;
		double paidOC=0.00;
		double claimRatio=0.0;
		String bonus="0.00";
		String paiddate="0.00";
		double bonusAdj=0.00;
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
	for(int i=0;i<bean.getExchRatePrem().size();i++) {
	 	ExchangeRate = bean.getExchRatePrem().get(i);
		shortName = bean.getCurrencyShortName().get(i);
		ExchangeRate = ExchangeRate.replaceAll(",","");
	
			premium = bean.getPremiumOC().get(i);
			premium = premium.replaceAll(",","");
			claim = bean.getClaimPaidOC().get(i);
			claim =claim.replaceAll(",","");
			paiddate = bean.getBonusPaidOCTillDate().get(i);
			paiddate =paiddate.replaceAll(",","");
			claimout = bean.getClaimOutStandingOC().get(i);
			claimout = claimout.replaceAll(",","");

			premium = Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate));
			claim = Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate));
			claimout = Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate));
			paiddate =  Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate));

			premiumOC +=Double.parseDouble(premium);
			claimOC +=Double.parseDouble(claim);
			claimoutOC +=Double.parseDouble( claimout);
			paidOC +=Double.parseDouble( paiddate);
			bonusExchangeRate +=shortName+"~"+ExchangeRate+",";
			if(Double.parseDouble(premium)>0) {
				claimRatio = (Double.parseDouble(claim) + Double.parseDouble( claimout)) / Double.parseDouble(premium) * 100;
				claimRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatio));
				claimRatioOC += claimRatio;
				String rate = SERVICE.getBonusValue(bean, claimRatio);
				if (Double.parseDouble(rate)>0) {
					bonus = Double.toString((Double.parseDouble(premium) * Double.parseDouble(rate)) / 100);
				}
					bonusOC += Double.parseDouble(bonus);
					bonusAdj+=Double.parseDouble(bonus) - Double.parseDouble( paiddate);
			}
	}
	
		bean.setConPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
		bean.setConClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
		bean.setConClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
		bean.setConbonusPaidOC(DropDownControllor.formatter(Double.toString(paidOC)));
		bean.setConClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
		bean.setConbonusOC(DropDownControllor.formatter(Double.toString(bonusOC)));
		bean.setConbonusAdjOC(DropDownControllor.formatter(Double.toString(bonusAdj)));
		bean.setBonusExchangeRate(bonusExchangeRate);
		if(StringUtils.isBlank(bean.getFlag())){
	    	bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
			bean.setManualconClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
			bean.setManualconClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
			bean.setManualconClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
	    }
		if("recal".equalsIgnoreCase(bean.getFlag()) && "bonusRage".equalsIgnoreCase(bean.getMode())){
			getManualBonusValues();
		}
	}
	private void getManualBonusValues() {
		String premium;
		String unearned;
		String claim;
		String claimout;
		String ExchangeRate;
		double premiumOC=0.00;
		double unearnedOC=0.00;
		double claimOC=0.00;
		double claimoutOC=0.00;
		double claimRatioOC=0.00;
		
		double lossOC=0.00;
		double paidOC=0.00;
		String claimRatio="";
		
		String loss="0.00";
		String paiddate="0.00";
		String manualPaid="0";
		String lossAdj="0.00";
		double claimRatioOCManual=0;
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		List<String> bonusComm =new ArrayList<String>();
		List<String> commpaid = new ArrayList<String>();
		List<String> bonusAdj = new ArrayList<String>();
		List<String> claimRatioManualList = new ArrayList<String>();
		String bonusOC="";
		
		try{
			for(int i=0;i<bean.getExchRatePrem().size();i++) {
				ExchangeRate = bean.getExchRatePrem().get(i);
				ExchangeRate = ExchangeRate.replaceAll(",","");
			premium = bean.getManualPremiumOC().get(i).replaceAll(",","");
			claim = bean.getManualclaimPaidOC().get(i).replaceAll(",","");
			if(StringUtils.isBlank(bean.getManualbonusPaidOCTillDate().get(i))){
			paiddate = StringUtils.isBlank(bean.getBonusPaidOCTillDate().get(i))?"0":bean.getBonusPaidOCTillDate().get(i).replaceAll(",","");
			}else{
				paiddate = StringUtils.isBlank(bean.getManualbonusPaidOCTillDate().get(i))?"0":bean.getManualbonusPaidOCTillDate().get(i).replaceAll(",","");
			}
			paiddate =paiddate.replaceAll(",","");
			manualPaid = paiddate.replaceAll(",","");
			claimout = bean.getManualclaimOutStandingOC().get(i).replaceAll(",","");
			//unearned = bean.getManualunearnpremiumOC().get(i).replaceAll(",","");
			
			bonusOC = StringUtils.isBlank(bean.getManualbonusOC().get(i))?"":bean.getManualbonusOC().get(i).replaceAll(",","");
			
			
			
			
			//premium = Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate));
			//unearned = Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate));
			//claim = Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate));
			//claimout = Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate));
			//paiddate =  Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate));
			
			//unearnedOC +=Double.parseDouble(unearned);
			premiumOC +=Double.parseDouble(Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate)));
			claimOC +=Double.parseDouble(Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate)));
			claimoutOC +=Double.parseDouble(Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate)));
			paidOC +=Double.parseDouble(Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate)));
			
			
			String claimRatioManual="0";
			String lossManual="0";
			
			double lossOCManual =0;
			String lossAdjManual="0";
				
			
			if(Double.parseDouble(premium)>0) {
				if(Double.parseDouble(premium)>=0){
					LOGGER.info("JSP Calculation Manual Claims Ratio "+bean.getManualclaimRatioOC().get(i).replaceAll(",","") + " in Row number " +i);
					String test = Double.toString(((Double.parseDouble(claim)+ Double.parseDouble(claimout)) / (Double.parseDouble(premium))) * 100);
;					test = DropDownControllor.formatter(test).replaceAll(",","");
					LOGGER.info("Java Calculation Manual Claims Ratio "+test+ " in Row number " +i);
					if(!test.equalsIgnoreCase(bean.getManualclaimRatioOC().get(i).replaceAll(",",""))){
						addActionError(getText("error.calcul.mistake"));
						LOGGER.info("Calculation Failed. Please retry. If problem persists, please contact support.");
					}else{
						bean.getManualclaimRatioOC().set(i,test);
					}
				}
				claimRatioManual=bean.getManualclaimRatioOC().get(i).replaceAll(",","");
				//claimRatioOCManual = Double.parseDouble(claimRatioManual);
				if("0.00".equalsIgnoreCase(bonusOC) ||StringUtils.isBlank(bonusOC)){
				String rate1 = SERVICE.getBonusValue(bean, Double.parseDouble(claimRatioManual));
				if (Double.parseDouble(rate1)>0) {
					lossManual = Double.toString((Double.parseDouble(premium) * Double.parseDouble(rate1)) / 100);
				}
				}else{
					lossManual =bonusOC;
				}
			}
			claimRatioOCManual=+Double.parseDouble(claimRatioManual);
			lossOCManual = Double.parseDouble(lossManual);
			lossAdjManual = Double.toString(lossOCManual - Double.parseDouble(manualPaid));
			commpaid.add(DropDownControllor.formatter(manualPaid));
			claimRatioManualList.add(DropDownControllor.formatter(claimRatioManual));
			bonusComm.add(DropDownControllor.formatter(Double.toString(lossOCManual)));
			bonusAdj.add(DropDownControllor.formatter(lossAdjManual));
			}
			if(premiumOC>0) {
				//if((premiumOC)>=0){
				//claimRatio = Double.toString((((claimOC) + (claimoutOC)) / (premiumOC-unearnedOC)) * 100);
				//}
				claimRatioOC += claimRatioOCManual;
				String rate = SERVICE.getBonusValue(bean, claimRatioOC);
				if (Double.parseDouble(rate)>0) {
					loss = Double.toString((premiumOC * Double.parseDouble(rate)) / 100);
				}
			}
			lossOC += Double.parseDouble(loss);
			lossAdj = Double.toString(lossOC - paidOC);
			//bean.setManualconClaimRatioOC(claimRatioManualList);
			//bean.setManualclaimPaidOC(claimOC);
			bean.setManualbonusOC(bonusComm);
			bean.setManualbonusAdjOC(bonusAdj);
			bean.setManualbonusPaidOCTillDate(commpaid);
			bean.setManualconbonusAdjOC(DropDownControllor.formatter(Double.toString(paidOC)));
			bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
			bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
			bean.setManualconClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
			bean.setManualconClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
			bean.setManualconClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
			bean.setManualconbonusOC(DropDownControllor.formatter(Double.toString(lossOC)));
			bean.setManualconbonusAdjOC(DropDownControllor.formatter(lossAdj));
			bean.setManualconbonusPaidOC(DropDownControllor.formatter(Double.toString(paidOC)));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String getBonusView(){
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setBonusCommission(SERVICE.bonusdetails(bean, countryId));
		SERVICE.PremiumContractDetails(bean, countryId);
		SERVICE.GetFieldValues(bean);
		SERVICE.addFieldValue(bean);
		return "dropdownajax";
	}
	public String insertBonus(){
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setLoginId(userId);
		String forward="streamResult";
		//SERVICE.insertSlideCommission(bean);
		String val="";
		String value="";
		
			if(StringUtils.isNotBlank(bean.getManualconbonusAdjOC())){
				 val=StringUtils.isEmpty(bean.getManualconbonusAdjOC()) ? "0.00" :bean.getManualconbonusAdjOC();
			}
			else{
				for(int i=0;i<bean.getManualbonusAdjOC().size();i++){
					val +=bean.getManualbonusAdjOC().get(i);
				}
			}
			if("1".equalsIgnoreCase(productId))
			value="<script type='text/javascript'>window.opener.premium.bonus.value='"+ val +"';window.opener.premium.disableStatus.value=true;window.opener.premium.conPremiumOC.value='"+ (StringUtils.isBlank(bean.getConPremiumOC())?"":bean.getConPremiumOC()) +"'; window.opener.premium.conClaimPaidOC.value='"+(StringUtils.isBlank(bean.getConClaimPaidOC())?"":bean.getConClaimPaidOC())+"'; window.opener.premium.conClaimOutStandingOC.value='"+(StringUtils.isBlank(bean.getConClaimOutStandingOC())?"":bean.getConClaimOutStandingOC())+"'; window.opener.premium.conClaimRatioOC.value='"+(StringUtils.isBlank(bean.getConClaimRatioOC())?"":bean.getConClaimRatioOC())  +"'; window.opener.premium.conbonusOC.value='"+(StringUtils.isBlank(bean.getConbonusOC())?"":bean.getConbonusOC()) +"'; window.opener.premium.conbonusPaidOC.value='"+(StringUtils.isBlank(bean.getConbonusPaidOC())?"":bean.getConbonusPaidOC()) +"';	 window.opener.premium.conbonusAdjOC.value='"+(StringUtils.isBlank(bean.getConbonusAdjOC())?"":bean.getConbonusAdjOC())+"';window.opener.facNetDue();window.close();</script>";
			if("3".equalsIgnoreCase(productId))
				value="<script type='text/javascript'>window.opener.premium.bonus.value='"+ val +"';window.opener.premium.disableStatus.value=true;window.opener.premium.conPremiumOC.value='"+ (StringUtils.isBlank(bean.getConPremiumOC())?"":bean.getConPremiumOC()) +"'; window.opener.premium.conClaimPaidOC.value='"+(StringUtils.isBlank(bean.getConClaimPaidOC())?"":bean.getConClaimPaidOC())+"'; window.opener.premium.conClaimOutStandingOC.value='"+(StringUtils.isBlank(bean.getConClaimOutStandingOC())?"":bean.getConClaimOutStandingOC())+"'; window.opener.premium.conClaimRatioOC.value='"+(StringUtils.isBlank(bean.getConClaimRatioOC())?"":bean.getConClaimRatioOC())  +"'; window.opener.premium.conbonusOC.value='"+(StringUtils.isBlank(bean.getConbonusOC())?"":bean.getConbonusOC()) +"'; window.opener.premium.conbonusPaidOC.value='"+(StringUtils.isBlank(bean.getConbonusPaidOC())?"":bean.getConbonusPaidOC()) +"';	 window.opener.premium.conbonusAdjOC.value='"+(StringUtils.isBlank(bean.getConbonusAdjOC())?"":bean.getConbonusAdjOC())+"';window.opener.xolNetDue();window.close();</script>";
			session.put("PremiumOc", bean.getPremiumOC());
			session.put("ClaimPaidOc", bean.getClaimPaidOC());
			session.put("ClaimOutStandingOC",  bean.getClaimOutStandingOC());
			session.put("ClaimRatioOC", bean.getClaimRatioOC());
			session.put("bonusOC", bean.getBonusOC());
			session.put("BonusPaidOCTillDate", bean.getBonusPaidOCTillDate());
			session.put("BonusAdjOC", bean.getBonusAdjOC());
			session.put("ExchRatePrem", bean.getExchRatePrem());
			
		
		byte[] byteArray = value.getBytes();
		inputStream = new ByteArrayInputStream(byteArray);
		return forward;
	}
	private void GetBonusDeatail() {
		 if(session.get("PremiumOc")!=null){
		 	bean.setPremiumOC((List<String>) session.get("PremiumOc"));
	 			bean.setClaimPaidOC((List<String>) session.get("ClaimPaidOc"));
	 			bean.setClaimOutStandingOC((List<String>) session.get("ClaimOutStandingOC"));
	 			bean.setClaimRatioOC((List<String>) session.get("ClaimRatioOC"));
	 			bean.setBonusOC((List<String>) session.get("bonusOC"));
	 			bean.setBonusPaidOCTillDate((List<String>) session.get("BonusPaidOCTillDate"));
	 			bean.setBonusAdjOC((List<String>) session.get("BonusAdjOC"));
	 			if(!"bonusRage".equalsIgnoreCase(bean.getMode())){
	 			bean.setExchRatePrem((List<String>) session.get("ExchRatePrem"));
	 			}
	 			bean.setPreCurrencylist((List<String>) session.get("PreCurrencylist"));
		 }
		
	}
	private void setSessionValuNull() {
		try{
			session.put("PremiumOc", null);
			session.put("ClaimPaidOc", null);
			session.put("ClaimOutStandingOC",  null);
			session.put("ClaimRatioOC",  null);
			session.put("bonusOC",  null);
			session.put("BonusPaidOCTillDate",  null);
			session.put("BonusAdjOC",  null);
			session.put("ExchRatePrem",  null);
			session.put("PreCurrencylist",  null);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
	}
	
	public String transactionList(){
		bean.setBranchCode(branchCode);
		bean.setTransList(dropDownController.getTransactionList(bean.getBranchCode(),bean.getProposal_No(),bean.getTransaction(),bean.getProductId()));
	return "dropdownajax";
	}
	
	public String getPreviousEntryDet(){
		bean.setPopupShowStatus("Y");
		if(StringUtils.isNotBlank(bean.getAccount_Period())){
			 String[] ins=bean.getAccount_Period().split("_");
			 if((!"EP".equalsIgnoreCase(ins[0]) && !"RTP".equalsIgnoreCase(ins[0]) && !"RVP".equalsIgnoreCase(ins[0])) && !"AP".equalsIgnoreCase(ins[0]) && !"RP".equalsIgnoreCase(ins[0])){
			 List list=SERVICE.getMandDInstallments(bean.getContNo(),bean.getLayerno());
			 int num=Integer.parseInt(ins[0]);
			 if(num!=1)
			 {
				 if(list!=null &&list.size()>0)
				 {
					 int j=1;
					 while(j<num){
						 boolean inst=true;
						 for(int i=0;i<list.size();i++)
						 {
							 Map map=(Map)list.get(i);
							 if(Integer.parseInt((String)(map.get("INSTALMENT_NUMBER")==null?"0":map.get("INSTALMENT_NUMBER")))==j)
							 {
								 inst=false;
								 break;
							 }
						 }
						 if(inst)
						 {
							bean.setPopupShowStatus("N");
						 }
						 j++;
					 }
				 }else
				 {
					 for(int i=1;i<num;i++){
						 bean.setPopupShowStatus("N");
					 }
				 }
			 }
			 }
		}
		return "dropdownajax";
	}
	
	}
