package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.ClaimBean;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.ClaimService;
import com.maan.service.CommonCalculation;
import com.maan.service.ProportionalPremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.DateFormat;
import java.text.ParseException;

public class ProportionalPremiumAction extends ActionSupport implements ModelDriven<FaculPremiumBean>
		{
		private static final long serialVersionUID = 1L;
		final static org.slf4j.Logger logger = LogUtil.getLogger(PortfolioAction.class);
		final HttpServletRequest request = ServletActionContext.getRequest();
		final HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		DropDownControllor dropDownController=new DropDownControllor();
		FaculPremiumBean bean=new FaculPremiumBean();
		FaculitivesBean bean1=new FaculitivesBean();
		ClaimBean bean2=new ClaimBean();
		ClaimService SERVICE1=new ClaimService();
		private transient final ProportionalPremiumService SERVICE=new ProportionalPremiumService();
		private String productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
		private String userId=session.get("UserId") == null ? "" :(String) session.get("UserId");
		private String userType=session.get("userType") == null ? "" :(String) session.get("userType");
		private String userName=session.get("UserName") == null ? "" :(String) session.get("UserName");
		//private String processId=session.get("processId").toString();
		private String countryId=session.get("countryId") == null ? "" :session.get("countryId").toString();
		private String branchCode=session.get("BRANCH_CODE") == null ? "" :session.get("BRANCH_CODE").toString();
		private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
		CommonCalculation calcu = new CommonCalculation();
		private InputStream inputStream;
		public FaculPremiumBean getModel() {
			return bean;
		}	
		private List<FaculPremiumBean> preList;
		private List<FaculPremiumBean> allocatedList;
		private List<FaculPremiumBean> brokerAndCedingName;
		private List<FaculPremiumBean> CliamList;
		public List<FaculPremiumBean> getCliamList() {
			return CliamList;
		}
		public void setCliamList(List<FaculPremiumBean> cliamList) {
			CliamList = cliamList;
		}
		private Map yearList;
		private String dropDown;
		private String menuId;

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
		public Map getYearList(){
			Map<String, String> treeMap =new TreeMap<String, String>();
	        final Calendar c = Calendar.getInstance();
			final Map uType=new HashMap();
			Calendar now = Calendar.getInstance();  
			 final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			 try {
	            c.setTime(df.parse(bean.getInsDate()));
	            // c.setTime(df.parse(bean.getOpstartDate()));
	            //int[] out = bean.getInsDate().split("/");
				// int year1=out[3];
				//String t=bean.getInsDate();
	            //c.setTime(df.parse("04/12/2015"));
	            //c.setTime(df.parse(t));
	            int year1=c.get(Calendar.YEAR);
	            // System.out.println("Month = " + (c.get(Calendar.MONTH)));
	            // System.out.println("Day = " + c.get(Calendar.DAY_OF_MONTH));
	            int year=now.get(Calendar.YEAR);
				 //String date = bean.getInsDate();
				 //java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);
				 // System.out.println(javaSqlDate);
				 //System.out.println(javaSqlDate.getYear());
				for(int i=year1;i<=year+1;i++)
				uType.put(i, i);
				treeMap = new TreeMap<String, String>(uType);	
	         } 
	         catch (ParseException e) {
	             e.printStackTrace();
	         }
	         return treeMap;
		}	
		public String premiumList()
			{
			String forward="premiumList";
			String editMode = "";
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}else{
				session.put("mfrid",bean.getProductId());
				productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
			}
			if(StringUtils.isNotBlank(bean.getDepartmentId())){
				session.put("DepartmentId",bean.getDepartmentId());
			}else{
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			}
			bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
			if(StringUtils.isNotBlank(bean.getProposal_No())){
			 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
			}
			if(!"N".equalsIgnoreCase(editMode)&& (null==bean.getMultiuserError() || StringUtils.isBlank(bean.getMultiuserError()) )){
						bean.setMultiuserError("error");
						if("premiumDisplay".equalsIgnoreCase(bean.getPremiumDisplay())){
							bean.setType("premium");
							forward = "masterList";
						}else{
						bean.setFlag("C");
						forward= "portfoliList";
						}
					}else{
			
				//bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				bean.setBranchCode(branchCode);
				bean1.setBranchCode(branchCode);
				new DropDownControllor().getOpenPeriod(bean1);
				bean.setOpstartDate(bean1.getOpstartDate());
				bean.setOpendDate(bean1.getOpendDate());
				preList=SERVICE.getPremiumedList(bean,"Main"); 	
				bean.setPreTempList(SERVICE.getPremiumedList(bean,"Temp"));
		        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
		        bean.setSectionList(dropDownController.getSectionList(bean.getContNo(),bean.getDepartmentId(),branchCode));
				if(bean.getSectionList()!=null && bean.getSectionList().size()>0){
					bean.setSectionType("2");
				}else{
					bean.setSectionType("1");
				}
				 if( bean.getMode() !=null&&bean.getMode().equalsIgnoreCase("premiumMaster")){
			        	bean.setPremiumMasterMode(bean.getPremiumDisplay());
			        }
		        if(preList.isEmpty() && bean.getPreTempList().isEmpty()  && userRights.indexOf("PN")!=-1)
		        {	
		        	bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo())); 
		        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));	
		        	bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), (String)session.get("DepartmentId")));
		        	bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
		        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
		        	SERVICE.ContractDetails(bean,countryId);
		        	 bean.setContractPremium(SERVICE.GetContractPremium(bean));
		        	bean.setSubProfitId("ALL");
		        	bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
		        	bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,(String)session.get("mfrid"),bean.getConsubProfitId()));
		        	bean.setCurrency(bean.getBaseCurrencyId());
		        	bean.setEnteringMode("2");
			        bean.setMode("add");
			        bean.setMenuStatus("N");
			        bean2.setPolicy_Contract_No(bean.getContNo());
				    bean2.setLayerNo(bean.getLayerno());
			        CliamList = SERVICE1.CliamList(bean2, 1);
			        if("RI01".equalsIgnoreCase(sourceId)){
			        bean.setSectionName(new DropDownControllor().getSectionName(bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode()));
			        }
			        forward =  "editPremium";
		        }
		       /* else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
		        	bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo())); 
		        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));		        	
		        	bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
		        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
		        	SERVICE.ContractDetails(bean,countryId);
		        	bean.setContractPremium(SERVICE.GetContractPremium(bean));
		        	bean.setSubProfitId("ALL");
		        	bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown((String)session.get("DepartmentId"),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
		        	bean.setCurrency(bean.getBaseCurrencyId());
		        	bean.setEnteringMode("2");
			        bean.setMode("add");
			        bean.setMenuStatus("N");
			        bean2.setPolicy_Contract_No(bean.getContNo());
				    bean2.setLayerNo(bean.getLayerno());
			        CliamList = SERVICE1.CliamList(bean2, 1);
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
			String editMode="";
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}else{
				session.put("mfrid",bean.getProductId());
				productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
			}
			if(StringUtils.isNotBlank(bean.getDepartmentId())){
				session.put("DepartmentId",bean.getDepartmentId());
			}
			bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
			if(StringUtils.isNotBlank(bean.getProposal_No())){
			 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
			}
			if(!"N".equalsIgnoreCase(editMode) ){
				bean.setMultiuserError("error");
				if("premiumDisplay".equalsIgnoreCase(bean.getPremiumDisplay())){
					bean.setType("premium");
					forward = "masterList";
				}else{
				premiumList();
				forward = "premiumList";
				}
			}else{
			//bean.setPredepartment(bean.getDepartmentId());
			
		    	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
	        	bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
				/*if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				else{
					session.put("mfrid",bean.getProductId());
				}*/
				bean.setSectionList(dropDownController.getSectionList(bean.getContNo(),bean.getDepartmentId(),branchCode));
				if(bean.getSectionList()!=null && bean.getSectionList().size()>0){
					bean.setSectionType("2");
				}else{
					bean.setSectionType("1");
				}
		        bean.setBranchCode(branchCode);
		        bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
		        bean.setContractPremium(SERVICE.GetContractPremium(bean));
		        bean.setContractDeptId(dropDownController.getContractLayerNo(bean.getContNo(),bean.getDepartmentId(),branchCode,(String) session.get("mfrid"),bean.getProposal_No()));
		        //if("0".equalsIgnoreCase(bean.getPreviousPremium()) || "".equalsIgnoreCase( bean.getContractPremium())){
		        if(StringUtils.isBlank(bean.getContractDeptId())){
		        	bean.setBranchCode(branchCode);
		    		bean.setProductIdList(dropDownController.productIdList(branchCode));
		    		bean.setFlag("new");
		    		addActionError("Contract is not available");
		    		bean.setPredepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"2","Y","","","","",""));
		    		forward= "premiummaster";
		        	
		        }
		        else{
		        List claimList = SERVICE.getClaimNosDropDown(bean.getContNo());
		        bean.setClaimNos(claimList);
		        List<String> claimno = new ArrayList<String>();
			    for(int k=0;k<claimList.size();k++){
					Map temp=(Map)claimList.get(k);
					claimno.add(temp.get("CLAIM_NO")==null?"":temp.get("CLAIM_NO").toString());
			    }
			    bean.setClaimNo(claimno);
			    bean2.setPolicy_Contract_No(bean.getContNo());
			    bean2.setLayerNo(bean.getLayerno()==null?"":bean.getLayerno());
			    if(StringUtils.isNotBlank(bean.getDepartmentId())){
			    	 bean2.setDepartmentId(bean.getDepartmentId());
			    }
			    bean.setMenuStatus("N");
			    SERVICE.ContractDetails(bean,countryId);
			    bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getDepartmentId()));
			    bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
			    
			    CliamList = SERVICE1.CliamList(bean2, 1);
			   
		        if("edit".equals(bean.getMode()))
		        {
				SERVICE.PremiumEdit(bean,countryId);	
		        }
		        else
		        { 
		        	  bean.setCurrency(bean.getBaseCurrencyId());
		        }
		       
		        }
		        if (StringUtils.isBlank(bean.getSubProfitId())){
		        //bean.setSubProfitId(bean.getConsubProfitId());
		        	bean.setSubProfitId("ALL");
		        }
		        bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
		        //setSessionValuNull();	
			}
				bean.setCleanCutCount(SERVICE.getCountCleanCUT(bean));
				bean.setCashlossCount(dropDownController.getCashLossCount(bean.getContNo(),bean.getDepartmentId()));
				 if("RI01".equalsIgnoreCase(sourceId)){
			        bean.setSectionName(new DropDownControllor().getSectionName(bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode()));
			        }
				return forward;
		}
		@SuppressWarnings("static-access")
		public String insertPremium()
		{		bean.setLoginId(userId);
				String editMode="";
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}else{
					session.put("mfrid",bean.getProductId());
					productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
				}
				 bean.setBranchCode(branchCode);
			     bean1.setBranchCode(branchCode);
				 bean.setUserType(userType);
				bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
				if(StringUtils.isNotBlank(bean.getProposal_No())){
				 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
				}
				bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			
		        bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo()));
		        if("edit".equalsIgnoreCase(bean.getMode())){
		        bean.setPreamendmentDate(new DropDownControllor().getpremiumPreamendDate(bean));
		        //if(StringUtils.isNotBlank(bean.getTransaction()) && StringUtils.isNotBlank(bean.getPreamendmentDate())){
				bean.setMaxDate(Validation.getMaxDateValidate(bean.getTransaction(), bean.getPreamendmentDate()));
		       // }
		        }
		        //getAccountperiodDate(bean);
				new DropDownControllor().getOpenPeriod(bean1);
				if("N".equalsIgnoreCase(editMode)){
					validateProportionPremium();
				}
	    		 if (!hasActionErrors() && "N".equalsIgnoreCase(editMode)) 
				 {
	    			/* if(session.get("PremiumOc")!=null){
	    				 getSessionValue("slide");
	    			 }
	    			 else if(session.get("portfolioPremium")!=null){
	    				 getSessionValue("profit");
	    			 } getLossDetails();*/
	    			 
	    			 	if("add".equalsIgnoreCase(bean.getMode())) {
						     SERVICE.InsertPremium(bean,countryId);
						     if("RI02".equalsIgnoreCase(sourceId)){
						    	 if("A".equalsIgnoreCase(bean.getCashlossType())) {
						    		 SERVICE.InsertCashLossCredit(bean);
						    	 }else if("R".equalsIgnoreCase(bean.getCashlossType())) {
						    		 SERVICE.InsertReverseCashLossCredit(bean);
						    	 }
						     }
			    		 }
			    		 else if("edit".equalsIgnoreCase(bean.getMode()))
			    		 {
				    		 SERVICE.UpdatePremium(bean);
			    		 }
			    		 SERVICE.ContractDetails(bean,countryId);
						 SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
						// setSessionValuNull();
						 SERVICE.cashLossmailTrigger(bean);
						return "PremiumSucuss";
				 }
				 else
				 {	
					 logger.info("##########Validation Message Start###########");
						Iterator<String> error = getActionErrors().iterator();
						while(error.hasNext()){
							logger.info(error.next());
						}
					 logger.info("##########Validation Message End###########");
    		         bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
					 SERVICE.ContractDetails(bean,countryId);
					 bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getDepartmentId()));
					 bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
					 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
					 bean.setStatus(bean.getStatus());				
				 }    		
	    		 if(!"N".equalsIgnoreCase(editMode)){
	    				bean.setMultiuserError("error");
	    		 }
			return "editPremium";
		}
		
		/*private void setSessionValuNull() {
			try{
				session.put("PremiumOc", null);
				 session.put("ClaimPaidOc", null);
				 session.put("ClaimOutStandingOC",  null);
				 session.put("ClaimRatioOC",null);
				 session.put("SlideScaleCommOC",null);
				 session.put("CommPaidOCTillDate", null);
				 session.put("SlideScaleAdjOC", null);
				 session.put("ExchRatePrem", null);
				 session.put("portfolioPremium", null);
				 session.put("accCostBrokerage", null);
				 session.put("managExp", null);
				 session.put("proClaimPaidOC", null);
				 session.put("proClaimOutStandingOC", null);
				 session.put("profitLoss", null);
				 session.put("otherAdj", null);
				 session.put("treatyAdj", null);
				 session.put("profitRatio", null);
				 session.put("lossRatio", null);
				 session.put("profitCommission",null);
				 session.put("superProfitComm", null);
				 session.put("payedTillDate", null);
				 session.put("profitCommAdj", null);
				 session.put("lossPremiumOc",null);
				 session.put("lossClaimPaidOc", null);
				 session.put("lossClaimOutStandingOC",  null);
				 session.put("lossClaimRatioOC", null);
				 session.put("ClaimPaidRatioOC", null);
				 session.put("LossPartOC", null);
				 session.put("LossPartRefTillDate",null);
				 session.put("LossPartRefAdjOC",null);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}*/
		public String instalmentPremium()
		{
		bean.setPremiumQuotaShare(SERVICE.GetInstalmentAmount(bean.getContNo(),bean.getInstalmentdate()));
		return "dropdownajax";				
		}
		public String ajexlist(){
			if(bean.getReqform().equalsIgnoreCase("branchAddress")){
				bean.setBranchCode(branchCode);
				SERVICE.bankAddress(bean);
			}
			else{
			bean.setBranchCode(branchCode);
			bean.setAccountList(SERVICE.getaccountList(bean));
			}
			return "dropdownajax";
		}
		public String premiumView()
		{
			String forward="PremiumSucuss";
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}
		        bean.setBranchCode(branchCode);	
				bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		        bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo()));
				SERVICE.ContractDetails(bean,countryId);
				bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getDepartmentId()));
				SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
				brokerAndCedingName = SERVICE.getBrokerAndCedingName(bean);
				allocatedList=SERVICE.getAllocatedList(bean); 
				bean.setCurrencyList(SERVICE.currencyList(bean));
				if("popup".equalsIgnoreCase(bean.getMode())){
					forward="propView";
				}
			return forward;	
		}
		private void validateProportionPremium()
		{
			final Validation val=new Validation();
			boolean flag=false;
			boolean cashLossCrFlag=false;
			if(StringUtils.isBlank(bean.getRi_cession())){
				bean.setRi_cession("Yes");
			}
			if(StringUtils.isBlank(bean.getRi_cession())){
				addActionError(getText("errors.ricession.required"));
			}
			else if("Yes".equalsIgnoreCase(bean.getRi_cession())){
			List retroList=SERVICE.getSPRetroList(bean.getContNo(), bean.getProductId(), bean.getLayerno());
				 if(retroList!=null&&retroList.size()>0)
				 {
					 Map map=(Map) retroList.get(0);				 
					 bean.setProductId(bean.getProductId());
					 bean.setSpRetro(map.get("RSK_SP_RETRO")==null?"N":map.get("RSK_SP_RETRO").toString());
					 bean.setNoOfRetro(map.get("RSK_NO_OF_INSURERS")==null?"0":map.get("RSK_NO_OF_INSURERS").toString());
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
										 //addActionError(getText("errors.retroNotCompleted"));
										 addActionError(getText("errors.retroNotCompleted",new String[] {bean.getRetroContractNo()}));
									 }
								 }							
							 }
						 }
					 }
				 }
			}
				 String deptId = SERVICE.getDepartmentNo(bean);
				 if(!deptId.equalsIgnoreCase(bean.getDepartmentId())){
					 //addActionError(getText("dept.mismatch"));
				 }
				 		 bean.setOtherCost((bean.getOtherCost()).replaceAll(",",""));
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
								//statDate=false;
						}
						else if(val.checkDate(bean.getInception_Date()).equalsIgnoreCase("INVALID"))
						{
							addActionError(getText("errors.statRecDate.invalid"));
								dateflag=false;
								//statDate=false;
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
							
						}else if(Validation.ValidateTwo(bean.getStatementDate(),bean.getInception_Date()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("errors.premium.statRecDate"));
							
						}
						if("edit".equalsIgnoreCase(bean.getMode()) && !"Temp".equalsIgnoreCase(bean.getTableType())){
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
						if(StringUtils.isBlank(bean.getSectionType())){
							addActionError(getText("error.enter.sectionType"));	
						}else if(!"2".equals(bean.getSectionType())){
							addActionError(getText("error.enter.sectionType.invalid"));	
						}
						if(StringUtils.isNotBlank(bean.getSectionName()) && "-1".equals(bean.getSectionName())){
							addActionError(getText("error.enter.sectionName"));	
						}
						if(StringUtils.isBlank(bean.getPredepartment())){
							addActionError(getText("error.departId.required"));
						}
						if(StringUtils.isBlank(bean.getSubProfitId())){
							addActionError(getText("error.subProfit_center.required"));
						}else{
							bean.setSubProfitId((bean.getSubProfitId()).replaceAll(" ", ""));
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
						/*else if(!"superuser".equalsIgnoreCase(bean.getUserType()))
						{
							String morRepMaxDate=SERVICE.getMovementReportMaxDate(bean.getBranchCode());
							if(StringUtils.isBlank(bean.getTransactionNo())&&StringUtils.isNotBlank(morRepMaxDate)&&Validation.ValidateTwo(morRepMaxDate,bean.getTransaction()).equalsIgnoreCase("invalid")){
								//addActionError(getText("errors.tranDate.invalid"));
							}
						}	*/				 
						if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getTransaction()).equalsIgnoreCase("") && !"edit".equalsIgnoreCase(bean.getMode())){
							if(new DropDownControllor().Validatethree(branchCode, bean.getTransaction())==0){
								addActionError(getText("errors.open.period.date.trans",new String[] {bean1.getOpenPeriodDate()}));
							}
							}
						  if("0".equals(bean.getAccount_Period_year())||"0".equals(bean.getAccount_Period()))
						 {
							  addActionError(getText("errors.account_Period_year.required"));
						 }else if(statDate)
						 {
							/* String date="";
							if("1".equals(bean.getAccount_Period())) 
							{
								date="31/03";
							}else if("2".equals(bean.getAccount_Period()))
							{
								date="30/06";
							}else if("3".equals(bean.getAccount_Period()))
							{
								date="30/09";
							}else if("4".equals(bean.getAccount_Period()))
							{
								date="31/12";
						    }*/
							 
							if(bean.getAccountPeriodDate().length()>0)
							{
								//date=date+"/"+bean.getAccount_Period_year();
								if(Validation.ValidateTwo(bean.getAccountPeriodDate(),bean.getStatementDate()).equalsIgnoreCase("invalid")){
									addActionError(getText("errors.premium.statRecDateGrAccPer"));
								}
							}
							
						 }
						 if(!val.isNull(bean.getReceipt_no()).equalsIgnoreCase(""))
						 {
							 flag=true;
						    //errors.add("receipt_no",new ActionError("errors.receipt_no.requireds"));
							 if(val.numbervalid(bean.getReceipt_no()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.receipt_no.Check"));			 
							 }
									
						 }			  
						 if(!val.isNull(bean.getSettlement_status()).equalsIgnoreCase(""))
						 {
							// flag=true;
						    //errors.add("settlement_status",new ActionError("errors.settlement_status.requireds"));
									
						 }			
						 if(!val.isNull(bean.getClaims_paid()).equalsIgnoreCase(""))
						 {
							 flag=true;
						    //errors.add("claims_paid",new ActionError("errors.claims_paid.requireds"));
							 bean.setClaims_paid((bean.getClaims_paid()).replaceAll(",",""));
							 if(val.numbervalid(bean.getClaims_paid().toString()).equalsIgnoreCase("INVALID"))
							 {
								 addActionError(getText("errors.claims_paid.Check"));			 
							 }
									
						 }
						 
					 if(val.isNull(bean.getPremiumQuotaShare()).equalsIgnoreCase("") && val.isNull(bean.getPremiumSurplus()).equalsIgnoreCase("") && val.isNull(bean.getPremiumportifolioIn()).equalsIgnoreCase("") && val.isNull(bean.getPremiumportifolioout()).equalsIgnoreCase("") && val.isNull(bean.getPremium_Reserve_Released()).equalsIgnoreCase("") && val.isNull(bean.getLossReserveReleased()).equalsIgnoreCase(""))	 
					 {
						 addActionError(getText("enter.anyone.value"));
					 }
					/* if(bean.getPremiumQuotaShare().equalsIgnoreCase("0") && bean.getPremiumSurplus().equalsIgnoreCase("0") && bean.getPremiumportifolioIn().equalsIgnoreCase("0") && bean.getPremiumportifolioout().equalsIgnoreCase("0") && bean.getPremium_Reserve_Released().equalsIgnoreCase("0") && bean.getLossReserveReleased().equalsIgnoreCase("0"))	 
					 {
						 addActionError(getText("enter.anyone.value"));
					 }*/
					 if(!val.isNull(bean.getPremiumQuotaShare()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("premium",new ActionError("errors.premium.required"));
						 bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremiumQuotaShare()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.premium.Error")); 
						 }
						
					 }	
				 	 if(!val.isNull(bean.getCommissionQuotaShare()).equalsIgnoreCase(""))
					 {
						 flag=true;
						// errors.add("commission",new ActionError("errors.commission.requireds"));
						 bean.setCommissionQuotaShare((bean.getCommissionQuotaShare()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCommissionQuotaShare()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.commission.Check"));
						 }
						  
					 }
				 	 if(!val.isNull(bean.getBrokerage()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("brokerage",new ActionError("errors.brokerage.requireds"));
						 bean.setBrokerage((bean.getBrokerage()).replaceAll(",",""));
						 if(val.numbervalid(bean.getBrokerage().toString()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.brokerage.check"));
						 }
					 }
				 if(!val.isNull(bean.getTax()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setTax((bean.getTax()).replaceAll(",",""));
						 if(val.numbervalid(bean.getTax().toString()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.tax.check"));
						 }
					 }
                if(!val.isNull(bean.getWithHoldingTaxOC()).equalsIgnoreCase(""))
                {
                    flag=true;
                    bean.setWithHoldingTaxOC((bean.getWithHoldingTaxOC()).replaceAll(",", ""));
                    if(val.numbervalid(bean.getWithHoldingTaxOC()).equalsIgnoreCase("INVALID"))
                    {
                        addActionError(getText("errors.whtax.check"));
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
				 if(!val.isNull(bean.getOverrider()).equalsIgnoreCase(""))
				 {
					 flag=true;
					 bean.setOverrider((bean.getOverrider()).replaceAll(",",""));
					 if(val.numbervalid(bean.getOverrider().toString()).equalsIgnoreCase("INVALID"))
					 {
						 addActionError(getText("errors.overrider.check"));
					 }
				 }
					 
				 if(!val.isNull(bean.getPremiumSurplus()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("premiumsurplus",new ActionError("errors.premiumsurplus.reqired"));
						 bean.setPremiumSurplus((bean.getPremiumSurplus()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremiumSurplus()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.premiumsurplus.Error")); 
						 }
					 }
					 		 
					 if(!val.isNull(bean.getCommissionSurplus()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("CommissionSurplus",new ActionError("errors.CommissionSurplus.reqired"));
						 bean.setCommissionSurplus((bean.getCommissionSurplus()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCommissionSurplus()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.CommissionSurplus.Error")); 
						 }
					 }
					 if(!val.isNull(bean.getPremiumportifolioIn()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setPremiumportifolioIn((bean.getPremiumportifolioIn()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremiumportifolioIn()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.PremiumPortfolioIn.Error")); 
						 }
						 //errors.add("PremiumPortfolioIn",new ActionError("errors.PremiumPortfolioIn.reqired")); 
					 }
					if(!val.isNull(bean.getSlideScaleCom()).equalsIgnoreCase(""))
					{
						flag=true;
						bean.setSlideScaleCom((bean.getSlideScaleCom()).replaceAll(",",""));
						if(val.numbervalid(bean.getSlideScaleCom()).equalsIgnoreCase("INVALID"))
						{
							addActionError(getText("errors.slidescale.Error"));
						}
						//errors.add("PremiumPortfolioIn",new ActionError("errors.PremiumPortfolioIn.reqired"));
					}
					 if(!val.isNull(bean.getCliamPortfolioin()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setCliamPortfolioin((bean.getCliamPortfolioin()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCliamPortfolioin()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.ClaimPortfolioIn.Error")); 
						 }
						// errors.add("ClaimPortfolioIn",new ActionError("errors.ClaimPortfolioIn.reqired")); 
					 }
				 	 if(!val.isNull(bean.getPremiumportifolioout()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("PremiumPortfolioOut",new ActionError("errors.PremiumPortfolioOut.reqired"));
						 bean.setPremiumportifolioout((bean.getPremiumportifolioout()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremiumportifolioout()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.PremiumPortfolioOut.Error")); 
						 }
					 }
				 	 if(!val.isNull(bean.getLossReserveReleased()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setLossReserveReleased((bean.getLossReserveReleased()).replaceAll(",",""));
						 if(val.numbervalid(bean.getLossReserveReleased()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.LossReserveReleased.Error")); 
						 }
						 
					 }
				 	 if(!val.isNull(bean.getInterest()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setInterest((bean.getInterest()).replaceAll(",",""));
						 if(val.numbervalid(bean.getInterest()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.Premium.intrest.invalid"));
						 }
				 	 }
				 	 if(!val.isNull(bean.getPremiumReserve_QuotaShare()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setPremiumReserve_QuotaShare((bean.getPremiumReserve_QuotaShare()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremiumReserve_QuotaShare()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.PremiumReserveQuotaShare1.Error"));
						 }else if(Double.parseDouble(bean.getPremiumReserve_QuotaShare())<0){
							 addActionError(getText("errors.PremiumReserveQuotaShare1.less"));
							 
						 }
				 	 }
					 if(!val.isNull(bean.getCashLoss_Credit()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 cashLossCrFlag=true;
						// errors.add("CashLossCredit",new ActionError("errors.CashLossCredit.reqired"));
						 bean.setCashLoss_Credit((bean.getCashLoss_Credit()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCashLoss_Credit()).equalsIgnoreCase("INVALID"))
						 {
							 cashLossCrFlag=false;
							 addActionError(getText("errors.CashLossCredit.Error")); 
						 }else if(Double.parseDouble(bean.getCashLoss_Credit())>0 && (bean.getClaimNos().size()==0)){
							 cashLossCrFlag=false;
							 addActionError(getText("errors.CashLossCredit.invalid"));
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
						 if(StringUtils.isBlank(bean.getLossParticipation())){
								addActionError(getText("lossParticipation.empty"));
							 }
							 else{
								 bean.setLossParticipation(bean.getLossParticipation().replaceAll(",", ""));
								 if(val.numbervalid(bean.getLossParticipation()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError(getText("error.lossParticipation.number"));
								 }
							 }
						 
					/* if(cashLossCrFlag&&Double.parseDouble(bean.getCashLoss_Credit())>0){
						 int rowcount=bean.getClaimNos().size();
						 double cashlossCount=0.0;
						 for(int i=0;i<rowcount;i++)
						 {
							 cashlossCount+=Double.parseDouble(StringUtils.isBlank(bean.getCashLossCreditOC().get(i))?"0":bean.getCashLossCreditOC().get(i).replaceAll(",",""));
						 }
						 if(Double.parseDouble(bean.getCashLoss_Credit())!=cashlossCount){
							 addActionError(getText("errors.SumOfCashLossCredit.invalid"));
						 }else{
							 for(int i=0;i<rowcount;i++)
							 {
								 if(SERVICE.getCashLossUpdateValidation(bean.getContNo(),bean.getTransactionNo(),bean.getClaimNo().get(i),(StringUtils.isBlank(bean.getCashLossCreditOC().get(i))?"0":bean.getCashLossCreditOC().get(i).replaceAll(",","")),bean.getExchRate()))
								 {
									 addActionError(getText("errors.CashLossCreditlse.SumOfclaimspaid",new String[]{String.valueOf(i+1)}));
								 }
							 }
						 }
					 }*/
					 
					 
				 	 if(!val.isNull(bean.getLoss_ReserveRetained()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 //errors.add("LossReserveRetained",new ActionError("errors.LossReserveRetained.reqired"));
						 bean.setLoss_ReserveRetained((bean.getLoss_ReserveRetained()).replaceAll(",",""));
						 if(val.numbervalid(bean.getLoss_ReserveRetained()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.LossReserveRetained.Error")); 
						 }else if(Double.parseDouble(bean.getLoss_ReserveRetained())<0){
							 addActionError(getText("errors.LossReserveRetained.Error.less"));
						 }
						 
					 }
					  
					 if(!val.isNull(bean.getProfit_Commission()).equalsIgnoreCase(""))
					 {
						 flag=true;
						// errors.add("ProfitCommission ",new ActionError("errors.ProfitCommission.reqired"));
						 bean.setProfit_Commission((bean.getProfit_Commission()).replaceAll(",",""));
						 if(val.numbervalid(bean.getProfit_Commission()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.ProfitCommission.Error")); 
						 }
					 }
				 if(!val.isNull(bean.getCash_LossPaid()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setCash_LossPaid((bean.getCash_LossPaid()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCash_LossPaid()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.CashLossPaid1.Error"));
						 }
						
					 }
				  /* if(val.isSelect(bean.getAccount_Period()).equalsIgnoreCase(""))
					 {
					
					 } 
					 else if(bean.getAccount_Period().equalsIgnoreCase("1") || bean.getAccount_Period().equalsIgnoreCase("2") || bean.getAccount_Period().equalsIgnoreCase("3") || bean.getAccount_Period().equalsIgnoreCase("4") )
					 {
						 bean.setPremiumQuotaShare((bean.getPremiumQuotaShare()).replaceAll(",",""));
						 if(val.isNull(bean.getPremiumQuotaShare()).equalsIgnoreCase("")  && val.isNull(bean.getPremiumSurplus()).equalsIgnoreCase(""))
							 {
							errors.add("Error",new ActionError("Anyone.Required.ForPremium"));	 
							 }
						 }
					 
					 else if(bean.getAccount_Period().equalsIgnoreCase("5") && val.isNull(bean.getPremiumportifolioIn()).equalsIgnoreCase(""))
					 {
						 errors.add("Error",new ActionError("Premium.Portfolio.In.required"));
					 }*/
					 
				   if(! val.isNull(bean.getXl_Cost()).equalsIgnoreCase(""))
					 {
						 bean.setXl_Cost((bean.getXl_Cost()).replaceAll(",",""));
						 if(val.numbervalid(bean.getXl_Cost()).equalsIgnoreCase("Invalid"))
						 {
							 addActionError(getText("Premium.Xlcost.Invalid"));
						 }
					 }
					 
				   if(! val.isNull(bean.getCliam_portfolio_out()).equalsIgnoreCase(""))
					 {
						 bean.setCliam_portfolio_out((bean.getCliam_portfolio_out()).replaceAll(",",""));
						 if(val.numbervalid(bean.getCliam_portfolio_out()).equalsIgnoreCase("Invalid"))
						 {
							 addActionError(getText("Premium.CliamPortpoliIn.Invalid")) ;
						 }
					 }
					 
					 if(! val.isNull(bean.getPremium_Reserve_Released()).equalsIgnoreCase(""))
					 {
						 bean.setPremium_Reserve_Released((bean.getPremium_Reserve_Released()).replaceAll(",",""));
						 if(val.numbervalid(bean.getPremium_Reserve_Released()).equalsIgnoreCase("Invalid"))
						 {
							 addActionError(getText("PremiumReserveRealsed.Required.error"));
						 }
					 }
					 if(!val.isNull(bean.getOtherCost()).equalsIgnoreCase(""))
					 {
						 flag=true;
						 bean.setOtherCost((bean.getOtherCost()).replaceAll(",",""));
						 if(val.numbervalid(bean.getOtherCost()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.othercost.second")); 
						 }
					 }
					 if(!val.isNull(bean.getOsClaimsLossUpdateOC()).equalsIgnoreCase(""))
					 {
						 flag=true;
						// errors.add("CashLossCredit",new ActionError("errors.CashLossCredit.reqired"));
						 bean.setOsClaimsLossUpdateOC((bean.getOsClaimsLossUpdateOC()).replaceAll(",",""));
						 if(val.numbervalid(bean.getOsClaimsLossUpdateOC()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.OSClaimLossUpdate.invalid")); 
						 }
					 }
					 if(StringUtils.isNotBlank(bean.getAccount_Period()) &&"51".equalsIgnoreCase(bean.getAccount_Period()) || "52".equalsIgnoreCase(bean.getAccount_Period()) ||"53".equalsIgnoreCase(bean.getAccount_Period()) || "54".equalsIgnoreCase(bean.getAccount_Period()) ||"55".equalsIgnoreCase(bean.getAccount_Period()) || "56".equalsIgnoreCase(bean.getAccount_Period()) ||  "57".equalsIgnoreCase(bean.getAccount_Period())){

						 if(SERVICE.getCountAccountPeriod(bean)==0){
							// addActionError(getText("errors.aacount.period.invalid"));  
						 }
					 }
					/* if(StringUtils.isNotBlank(bean.getAccount_Period()) && !"5".equals(bean.getAccount_Period())){
					 if(SERVICE.getCountCleanCUT(bean)==0){
						 addActionError(getText("errors.cleancutcount.invalid")); 
					 }
					 }*/
					 if(StringUtils.isNotBlank(bean.getAccount_Period()) && "6".equals(bean.getAccount_Period())){
						 SERVICE.getOSBList(bean);
						 if(Double.parseDouble(bean.getTotalOSB())>0){
							 addActionError(getText("errors.osbtotal.invalid"));  
						 }
					 }
					 
					 
						bean.setTotalCredit((bean.getTotalCredit()).replaceAll(",",""));
						bean.setTotalDebit((bean.getTotalDebit()).replaceAll(",",""));
						if(flag==false)
						{
							addActionError(getText("errors.currency.select"));	 
						}
						
						
			 		}
		public String cashLossCredit(){
			bean.setBranchCode(branchCode);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setCashlossCreditList(SERVICE.getCassLossCredit(bean,""));
			bean.setAllocatedTransList(SERVICE.getAllocatedTransList(bean));
			bean.setAllocatedcashLossList(SERVICE.getAllocatedCassLossCredit(bean));
			return "cashlossPOP";
		}
		public String getCredit(){
			bean.setBranchCode(branchCode);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setCashlossCreditList(SERVICE.getCassLossCredit(bean,""));
			bean.setAllocatedTransList(SERVICE.getAllocatedTransList(bean));
			bean.setAllocatedcashLossList(SERVICE.getAllocatedCassLossCredit(bean));
			return "cashlossPOP";
		}
		
		public String submitCredit(){
			String forward="streamResult";
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			Map<String,String> cashLossCreditMap = new HashMap<String, String>();
			try{
				List<String>claimPaymentNos=bean.getClaimPaymentNos();
				List<String>creditAmountCLC=bean.getCreditAmountCLClist();
				List<String>creditAmountCLD=bean.getCreditAmountCLDlist();
				List<String>cLCsettlementRate=bean.getCLCsettlementRatelist();
				List<String>payamountList = bean.getPayamountList();
				List<String> cashchkbox = bean.getChkbox();
				double credit=0.00;
				validateCashLossCredit();
				if(!hasActionErrors()){
				if(claimPaymentNos!=null && claimPaymentNos.size()>0) {
					for(int i=0 ; i<claimPaymentNos.size() ; i++) {
						if("true".equalsIgnoreCase(cashchkbox.get(i))) {
							if(cashLossCreditMap.containsKey(claimPaymentNos.get(i))) {
								cashLossCreditMap.remove(claimPaymentNos.get(i));
								cashLossCreditMap.put(claimPaymentNos.get(i), creditAmountCLC.get(i).replace(",", "")+"~"+creditAmountCLD.get(i).replace(",", "")+"~"+cLCsettlementRate.get(i).replace(",", "")+"~"+payamountList.get(i).replace(",", ""));
								credit=credit+Double.parseDouble(creditAmountCLC.get(i).replace(",", ""));
							}
							else {
								cashLossCreditMap.put(claimPaymentNos.get(i), creditAmountCLC.get(i).replace(",", "")+"~"+creditAmountCLD.get(i).replace(",", "")+"~"+cLCsettlementRate.get(i).replace(",", "")+"~"+payamountList.get(i).replace(",", ""));
								credit=credit+Double.parseDouble(creditAmountCLC.get(i).replace(",", ""));
								
							}
						}
						else if(cashLossCreditMap!=null && cashLossCreditMap.containsKey(claimPaymentNos.get(i))) {
							cashLossCreditMap.remove(claimPaymentNos.get(i));
						}
					}
				}
				String value1="";
				String value2="";
				String value3="";
				String value4="";
				String value5="";
				List<FaculPremiumBean>cashLossList=SERVICE.getCassLossCredit(bean,"");
				for(int i=0;i<cashLossList.size();i++) {
					
					FaculPremiumBean form= cashLossList.get(i);
					if(( cashLossCreditMap).containsKey(form.getClaimPaymentNo())) {
						String string3=cashLossCreditMap.get(form.getClaimPaymentNo());
						String[] cashloss=string3.split("~");
						value1=value1+form.getClaimPaymentNo()+",";
						value2=value2+cashloss[0]+",";
						value3=value3+cashloss[1]+",";
						value4=value4+cashloss[2]+",";
						value5 = value5+cashloss[3]+",";
					}else{
						value1=value1+",";
						value2=value2+",";
						value3=value3+",";
						value4=value4+",";
						value5=value5+",";
					}
				}
				//session.put("CassLossCredit",cashLossCreditMap );
				String creditVal =DropDownControllor.formatter(Double.toString(credit));
				String value="<script type='text/javascript'>window.opener.premium.cashLoss_Credit.value='"+creditVal+"';window.opener.premium.cashlossType.value='"+bean.getCashlossType()+"';window.opener.premium.claimPaymentNo.value='"+value1+"';window.opener.premium.creditAmountCLC.value='"+value2+"';window.opener.premium.creditAmountCLD.value='"+value3+"';window.opener.premium.CLCsettlementRate.value='"+value4+"';window.opener.premium.cLDAmount.value='"+value5+"';window.opener.premium.disableStatus.value=true;window.opener.proposalNetDue();window.close();</script>";
				byte[] byteArray = value.getBytes();
				inputStream=new ByteArrayInputStream(byteArray);
				}
				else{
					logger.info("##########Validation Message Start###########");
					Iterator<String> error = getActionErrors().iterator();
					while(error.hasNext()){
						logger.info(error.next());
					}
					logger.info("##########Validation Message End###########");
					bean.setMode("error");
					bean.setBranchCode(branchCode);
					bean.setCashlossCreditList(SERVICE.getCassLossCredit(bean,""));
					forward="cashlossPOP";
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return forward;
		}
		public String reverseCredit() {
			String forward="streamResult";
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			try {
				double value1=0.0;
				value1=SERVICE.getReverseCassLossCredit(bean);
				
				String creditVal ="-"+DropDownControllor.formatter(Double.toString(value1));
				String value="<script type='text/javascript'>window.opener.premium.cashLoss_Credit.value='"+creditVal+"';window.opener.premium.cashlosstranId.value='"+bean.getCashlosstranId()+"';window.opener.premium.cashlossType.value='"+bean.getCashlossType()+"';window.opener.premium.disableStatus.value=true;window.opener.proposalNetDue();window.close();</script>";
				byte[] byteArray = value.getBytes();
				inputStream=new ByteArrayInputStream(byteArray);
			
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return forward;
		}
		public String PremiumReserved(){
			bean.setBranchCode(branchCode);
			bean.setType("PRR");
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setPremiumReservedList(SERVICE.getPremiumReserved(bean,"",countryId));
			return "premiumResPOP";
		}
		
		public String getReserveCount(){
			bean.setBranchCode(branchCode);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			int count = SERVICE.getDepositReleaseCount(bean);
			if(count<=0){
				bean.setPopupShowStatus("Y");
			}else{
				bean.setPopupShowStatus("N");
			}
			return "dropdownajax";
		}
		
		public String submitPremiumReserved(){
			String forward="streamResult";
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			bean.setBranchCode(branchCode);
			Map<String,String> cashLossCreditMap = new HashMap<String, String>();
			try{
				List<String>claimPaymentNos=bean.getClaimPaymentNos();
				List<String>creditAmountCLC=bean.getCreditAmountCLClist();
				List<String>creditAmountCLD=bean.getCreditAmountCLDlist();
				List<String>cLCsettlementRate=bean.getCLCsettlementRatelist();
				List<String> cashchkbox = bean.getChkbox();
				double credit=0.00;
				validatePremimReserved();
				if(!hasActionErrors()){
				if(claimPaymentNos!=null && claimPaymentNos.size()>0) {
					for(int i=0 ; i<claimPaymentNos.size() ; i++) {
						if("true".equalsIgnoreCase(cashchkbox.get(i))) {
							if(cashLossCreditMap.containsKey(claimPaymentNos.get(i))) {
								cashLossCreditMap.remove(claimPaymentNos.get(i));
								cashLossCreditMap.put(claimPaymentNos.get(i), creditAmountCLC.get(i).replace(",", "")+"~"+creditAmountCLD.get(i).replace(",", "")+"~"+cLCsettlementRate.get(i).replace(",", ""));
								credit=credit+Double.parseDouble(creditAmountCLC.get(i).replace(",", ""));
							}
							else {
								cashLossCreditMap.put(claimPaymentNos.get(i), creditAmountCLC.get(i).replace(",", "")+"~"+creditAmountCLD.get(i).replace(",", "")+"~"+cLCsettlementRate.get(i).replace(",", ""));
								credit=credit+Double.parseDouble(creditAmountCLC.get(i).replace(",", ""));
							}
						}
						else if(cashLossCreditMap!=null && cashLossCreditMap.containsKey(claimPaymentNos.get(i))) {
							cashLossCreditMap.remove(claimPaymentNos.get(i));
						}
					}
				}
				String value1="";
				String value2="";
				String value3="";
				String value4="";
				List<FaculPremiumBean>premiumList=SERVICE.getPremiumReserved(bean,"",countryId);
				for(int i=0;i<premiumList.size();i++) {
					FaculPremiumBean form= premiumList.get(i);
					if(( cashLossCreditMap).containsKey(form.getTransactionNo())) {
						String string3=cashLossCreditMap.get(form.getTransactionNo());
						String[] cashloss=string3.split("~");
						value1=value1+form.getTransactionNo()+",";
						value2=value2+cashloss[0]+",";
						value3=value3+cashloss[1]+",";
						value4=value4+cashloss[2]+",";
					}else{
						value1=value1+",";
						value2=value2+",";
						value3=value3+",";
						value4=value4+",";
					}
				}
				String value="";
				String creditVal =DropDownControllor.formatter(Double.toString(credit));
				if("PRR".equals(bean.getType())){
				value="<script type='text/javascript'>window.opener.premium.premium_Reserve_Released.value='"+creditVal+"';window.opener.premium.PRTransNo.value='"+value1+"';window.opener.premium.PRAmount.value='"+value2+"';window.opener.premium.PREAmount.value='"+value3+"';window.opener.premium.PRERate.value='"+value4+"';window.opener.premium.disableStatus.value=true;window.opener.proposalNetDue();window.close();</script>";
				}else if("LRR".equals(bean.getType())){
					value="<script type='text/javascript'>window.opener.premium.lossReserveReleased.value='"+creditVal+"';window.opener.premium.LRTransNo.value='"+value1+"';window.opener.premium.LRAmount.value='"+value2+"';window.opener.premium.LREAmount.value='"+value3+"';window.opener.premium.LRERate.value='"+value4+"';window.opener.premium.disableStatus.value=true;window.opener.proposalNetDue();window.close();</script>";
				}
				byte[] byteArray = value.getBytes();
				inputStream=new ByteArrayInputStream(byteArray);
				}
				else{
					logger.info("##########Validation Message Start###########");
					Iterator<String> error = getActionErrors().iterator();
					while(error.hasNext()){
						logger.info(error.next());
					}
					logger.info("##########Validation Message End###########");
					bean.setMode("error");
					bean.setBranchCode(branchCode);
					bean.setPremiumReservedList(SERVICE.getPremiumReserved(bean,"",countryId));
					forward="premiumResPOP";
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return forward;
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
					preList=SERVICE.getPremiumedList(bean,"Main"); 	
					bean.setPreTempList(SERVICE.getPremiumedList(bean,"Temp"));
			        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
			        bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
			        if(preList.isEmpty() && bean.getPreTempList().isEmpty() && userRights.indexOf("PN")!=-1)
			        {	
			        	bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo())); 
			        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));		        	
			        	bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
			        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
			        	SERVICE.ContractDetails(bean,countryId);
			        	bean.setContractPremium(SERVICE.GetContractPremium(bean));
			        	bean.setCurrency(bean.getBaseCurrencyId());
			        	bean.setEnteringMode("2");
				        bean.setMode("add");
				        bean.setMenuStatus("N");
				        bean2.setPolicy_Contract_No(bean.getContNo());
					    bean2.setLayerNo(bean.getLayerno());
				        CliamList = SERVICE1.CliamList(bean2, 1);
			        	return "editPremium";
			        }
			       /* else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
			        	bean.setClaimNos(SERVICE.getClaimNosDropDown(bean.getContNo())); 
			        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));		        	
			        	bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
			        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
			        	SERVICE.ContractDetails(bean,countryId);
			        	 bean.setContractPremium(SERVICE.GetContractPremium(bean));
			        	bean.setCurrency(bean.getBaseCurrencyId());
			        	bean.setEnteringMode("2");
				        bean.setMode("add");
				        bean.setMenuStatus("N");
				        bean2.setPolicy_Contract_No(bean.getContNo());
					    bean2.setLayerNo(bean.getLayerno());
				        CliamList = SERVICE1.CliamList(bean2, 1);
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
		public void validateCashLossCredit(){
			boolean check=true;
			final Validation val=new Validation();
			List<String>claimPaymentNos=bean.getClaimPaymentNos();
			List<String>creditAmountCLC=bean.getCreditAmountCLClist();
			List<String>creditAmountCLD=bean.getCreditAmountCLDlist();
			List<String>cLCsettlementRate=bean.getCLCsettlementRatelist();
			List<String>payamountList=bean.getPayamountList();
			List<String> cashchkbox = bean.getChkbox();
			//bean.setChkbox("")
			if(claimPaymentNos!=null && claimPaymentNos.size()>0) {
				for(int i=0 ; i<claimPaymentNos.size() ; i++) {
					if("true".equalsIgnoreCase(cashchkbox.get(i))) {
						check=false;
						if(StringUtils.isBlank(creditAmountCLC.get(i).replace(",", ""))){
							addActionError(getText("error.creditamountclc.req",new String[] { String.valueOf(i + 1) }));
						}else if(val.numbervalid(creditAmountCLC.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.creditamountclc.invalid",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(creditAmountCLD.get(i).replace(",", ""))){
							addActionError(getText("error.creditamountcld.req",new String[] { String.valueOf(i + 1) }));
						}
						else if(val.numbervalid(creditAmountCLD.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.creditamountclc.invalid",new String[] { String.valueOf(i + 1) }));
						}else if(Double.parseDouble(creditAmountCLD.get(i).replace(",", ""))>Double.parseDouble(payamountList.get(i).replace(",", ""))){
							addActionError(getText("error.payamount.less.credit",new String[] { String.valueOf(i + 1) }));
						}else{
							String ans = calcu.calculatePTTYPopUp(bean,"CashLoss",i);
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getCreditAmountCLDlist().get(i).replace(",", ""))){
								this.addActionError(getText("error.calcul.mistake"));
								logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.getCreditAmountCLDlist().set(i,ans);
							}
						}
						if(StringUtils.isBlank(cLCsettlementRate.get(i).replace(",", ""))){
							addActionError(getText("error.cLCsettlementRate.req",new String[] { String.valueOf(i + 1) }));
						}else if(val.numbervalid(cLCsettlementRate.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.creditamountclc.invalid",new String[] { String.valueOf(i + 1) }));
						}
					}
					
					}if(check) {
						this.addActionError(getText("error.one.check1"));
					}
		}
		}
		public void validatePremimReserved(){
			boolean check=true;
			final Validation val=new Validation();
			List<String>claimPaymentNos=bean.getClaimPaymentNos();
			List<String>creditAmountCLC=bean.getCreditAmountCLClist();
			List<String>creditAmountCLD=bean.getCreditAmountCLDlist();
			List<String>cLCsettlementRate=bean.getCLCsettlementRatelist();
			List<String>payamountList=bean.getPayamountList();
			List<String>prAllocatedList=bean.getPrallocatedTillList();
			List<String> cashchkbox = bean.getChkbox();
			//bean.setChkbox("")
			if(claimPaymentNos!=null && claimPaymentNos.size()>0) {
				for(int i=0 ; i<claimPaymentNos.size() ; i++) {
					if("true".equalsIgnoreCase(cashchkbox.get(i))) {
						check=false;
						if(StringUtils.isBlank(bean.getCreditAmountCLClist().get(i).replace(",", ""))){
							addActionError(getText("error.pramount.req",new String[] { String.valueOf(i + 1) }));
						}else{
							String ans = calcu.calculatePTTYPopUp(bean,"PremiRes",i);
							if(Double.parseDouble(ans)!=Double.parseDouble(bean.getCreditAmountCLClist().get(i).replace(",", ""))){
								this.addActionError(getText("error.calcul.mistake"));
								logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.getCreditAmountCLClist().set(i,ans);
							}
							
							if(val.numbervalid(creditAmountCLC.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.pramount.invalid",new String[] { String.valueOf(i + 1) }));
						}
						}
						if(StringUtils.isBlank(creditAmountCLD.get(i).replace(",", ""))){
							addActionError(getText("error.preamount.req",new String[] { String.valueOf(i + 1) }));
						}
						else if(val.numbervalid(creditAmountCLD.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.preamount.invalid",new String[] { String.valueOf(i + 1) }));
						}else if(Double.parseDouble(creditAmountCLD.get(i).replace(",", ""))>Double.parseDouble(payamountList.get(i).replace(",", ""))){
							addActionError(getText("error.riamount.less.rt",new String[] { String.valueOf(i + 1) }));
						}else if(Double.parseDouble(creditAmountCLD.get(i).replace(",", ""))+Double.parseDouble(prAllocatedList.get(i).replace(",", ""))<0){
							addActionError(getText("error.riamount.less.rt",new String[] { String.valueOf(i + 1) }));
						}
						if(StringUtils.isBlank(cLCsettlementRate.get(i).replace(",", ""))){
							addActionError(getText("error.preRate.req",new String[] { String.valueOf(i + 1) }));
						}else if(val.numbervalid(cLCsettlementRate.get(i).replace(",", "")).equalsIgnoreCase("INVALID")){
							addActionError(getText("error.preRate.invalid",new String[] { String.valueOf(i + 1) }));
						}
					}
					
					}if(check) {
						this.addActionError(getText("error.one.check1"));
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
		public InputStream getInputStream() {
			return inputStream;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		
		public String slidCommission() {
			String forward="";
			bean.setBranchCode(branchCode);
			bean.setProductId(productId);
			//SERVICE.PremiumContractDetails(bean, countryId);
			if(StringUtils.isBlank(bean.getCurrencyName())) {
				bean.setCurrencyName(SERVICE.getCurrencyShortName(bean));
			}
			if(StringUtils.isBlank(bean.getSubProfit_center()) && !"ALL".equalsIgnoreCase(bean.getDepartmentId())){
				bean.setSubProfit_center(new DropDownControllor().getPopUpDeptName(branchCode, bean.getDepartmentId(),productId));
			}
			if(StringUtils.isBlank(bean.getTransactionNo()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag()) || "profitRage".equalsIgnoreCase(bean.getMode()) ) {
			bean.setSlideCommission(SERVICE.SlideCommission(bean, countryId));
			}
			if("profit".equalsIgnoreCase(bean.getMode()) || "profitRage".equalsIgnoreCase(bean.getMode())){
				forward ="profitCommission";
				/*if (StringUtils.isNotBlank(bean.getMode()) && "profit".equalsIgnoreCase(bean.getMode())){ As on 05/10/2017 mail table droped and modified pop up logig By Ramya A
					if(StringUtils.isNotBlank(bean.getTransactionNo()) && !"recal".equalsIgnoreCase(bean.getFlag())) {
						if(bean.getPremiumOC() ==null ||  StringUtils.isBlank(bean.getPremiumOC().get(0))){
							SERVICE.PremSlideEditFieldValue(bean,"profit");
						}
					}
				} getSessionValue("profit");*/
				
				
				
				if(bean.getPortfolioPremium() ==null || StringUtils.isBlank(bean.getPortfolioPremium().get(0)) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
					SERVICE.PremiumGetFieldValues(bean);
					}
				if(StringUtils.isBlank(bean.getTransactionNo()) || "profitRage".equalsIgnoreCase(bean.getMode()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
					ConvertedCurrencyValue();
				}
				else{
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
						String slideExchangeRate="";
						 String ExchangeRate = bean.getExchRatePrem().get(i);
						 
						String shortName = bean.getCurrencyShortName().get(i);
						slideExchangeRate += shortName+"~"+ExchangeRate+",";
						bean.setSlideExchangeRate(slideExchangeRate);
					}
				}
			}
			else if ("loss".equalsIgnoreCase(bean.getMode()) || "lossRage".equalsIgnoreCase(bean.getMode())){
				forward ="lossParticipation";
				/*	if (StringUtils.isNotBlank(bean.getMode()) && "loss".equalsIgnoreCase(bean.getMode())){
					if(StringUtils.isNotBlank(bean.getTransactionNo()) && !"recal".equalsIgnoreCase(bean.getFlag())) {
						if(bean.getLossPremiumOC() ==null ||  StringUtils.isBlank(bean.getLossPremiumOC().get(0))){
						SERVICE.PremSlideEditFieldValue(bean,"loss");
						}
					}
				}
				getLossDetails();*/
				if(bean.getLossPremiumOC() ==null || StringUtils.isBlank(bean.getLossPremiumOC().get(0)) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
					SERVICE.GetLossFieldValues(bean);
				}
				if(StringUtils.isBlank(bean.getTransactionNo()) || "lossRage".equalsIgnoreCase(bean.getMode()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
					ConvertedCurrencyValue();
				}
				else{
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
						String slideExchangeRate="";
						 String ExchangeRate = bean.getExchRatePrem().get(i);
						 
						String shortName = bean.getCurrencyShortName().get(i);
						slideExchangeRate += shortName+"~"+ExchangeRate+",";
						bean.setSlideExchangeRate(slideExchangeRate);
					}
				}
				forward ="lossParticipation";
			}
			else{
				/*if (StringUtils.isNotBlank(bean.getMode()) && "slide".equalsIgnoreCase(bean.getMode())){
					if(StringUtils.isNotBlank(bean.getTransactionNo()) && !"recal".equalsIgnoreCase(bean.getFlag())) {
						if(bean.getPremiumOC() ==null ||  StringUtils.isBlank(bean.getPremiumOC().get(0))){
						SERVICE.PremSlideEditFieldValue(bean,"slide");
						}
					}
				}
				getSessionValue("slide");*/
				
				if(bean.getPremiumOC() ==null || StringUtils.isBlank(bean.getPremiumOC().get(0)) ||StringUtils.isNotBlank(bean.getFlag()) ){
					SERVICE.GetFieldValues(bean);
				}
				if(StringUtils.isBlank(bean.getTransactionNo()) || "slideRage".equalsIgnoreCase(bean.getMode()) ||StringUtils.isNotBlank(bean.getFlag()) || "recal".equalsIgnoreCase(bean.getFlag())){
					ConvertedCurrencyValue();
				}
				else{
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
						String slideExchangeRate="";
						 String ExchangeRate = bean.getExchRatePrem().get(i);
						 
						String shortName = bean.getCurrencyShortName().get(i);
						slideExchangeRate += shortName+"~"+ExchangeRate+",";
						bean.setSlideExchangeRate(slideExchangeRate);
					}
				}
				forward ="slideCommission";
			}
			session.put("PreCurrencylist", bean.getPreCurrencylist());
			return forward;
		}
		
	/*	private void getSessionValue(String type) {
			if("profit".equalsIgnoreCase(type)){
				if(session.get("portfolioPremium")!=null){
					bean.setPortfolioPremium((List<String>) session.get("portfolioPremium"));
					bean.setAccCostBrokerage((List<String>) session.get("accCostBrokerage"));
					bean.setManagExp((List<String>) session.get("managExp"));
					bean.setProClaimPaidOC((List<String>) session.get("proClaimPaidOC"));
					bean.setProClaimOutStandingOC((List<String>) session.get("proClaimOutStandingOC"));
					bean.setProfitLoss((List<String>) session.get("profitLoss"));
					bean.setOtherAdj((List<String>) session.get("otherAdj"));
					bean.setTreatyAdj((List<String>) session.get("treatyAdj"));
					bean.setProfitRatio((List<String>) session.get("profitRatio"));
					bean.setLossRatio((List<String>) session.get("lossRatio"));
					bean.setProfitCommission((List<String>) session.get("profitCommission"));
					bean.setSuperProfitComm((List<String>) session.get("superProfitComm"));
					bean.setPayedTillDate((List<String>) session.get("payedTillDate"));
					bean.setProfitCommAdj((List<String>) session.get("profitCommAdj"));
					bean.setExchRatePrem((List<String>) session.get("ExchRatePrem"));
					bean.setPreCurrencylist((List<String>) session.get("PreCurrencylist"));
				}
			}
			else if("slide".equalsIgnoreCase(type)){
				if(session.get("PremiumOc")!=null){
					bean.setPremiumOC((List<String>) session.get("PremiumOc"));
					bean.setClaimPaidOC((List<String>) session.get("ClaimPaidOc"));
					bean.setClaimOutStandingOC((List<String>) session.get("ClaimOutStandingOC"));
					bean.setClaimRatioOC((List<String>) session.get("ClaimRatioOC"));
					bean.setSlideScaleCommOC((List<String>) session.get("SlideScaleCommOC"));
					bean.setCommPaidOCTillDate((List<String>) session.get("CommPaidOCTillDate"));
					bean.setSlideScaleAdjOC((List<String>) session.get("SlideScaleAdjOC"));
					bean.setExchRatePrem((List<String>) session.get("ExchRatePrem"));
				}
			}
		}*/
		
		public String calculationProfitComm(){
			bean.setBranchCode(branchCode);
			bean.setProductId(productId);
			//SERVICE.PremiumContractDetails(bean, countryId);
			if(StringUtils.isBlank(bean.getCurrencyName())) {
				bean.setCurrencyName(SERVICE.getCurrencyShortName(bean));
			}
			if(StringUtils.isBlank(bean.getSubProfit_center()) && !"ALL".equalsIgnoreCase(bean.getDepartmentId())){
				bean.setSubProfit_center(new DropDownControllor().getPopUpDeptName(branchCode, bean.getDepartmentId(),productId));
			}
			bean.setSlideCommission(SERVICE.SlideCommission(bean, countryId));
			getCalculationValue();
			ConvertedCurrencyValue();
			return "profitCommission";
		}

		private void getCalculationValue() {
			try{
				List<String> treatyRes =new ArrayList<String>();
				List<String> manExp = new ArrayList<String>();
				List<String> profitComm = new ArrayList<String>();
				List<String> profitRatio = new ArrayList<String>();
				List<String> lossRatio =new ArrayList<String>();
				List<String> suprProfit =new ArrayList<String>();
				List<String> profitComAdj =new ArrayList<String>();
				double managExp=0;
				double trtyRes=0;
				double profitRto =0;
				double LossRto =0;
				double proftCom=0;
				double superProfitComm =0;
				double profitCommAdj =0;
				for(int i=0;i<bean.getExchRatePrem().size();i++) {
					managExp =(Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")) * Double.parseDouble(bean.getManagementExp().replaceAll(",","")))/100;
					trtyRes = Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")) -Double.parseDouble(bean.getAccCostBrokerage().get(i).replaceAll(",",""))-Double.parseDouble(bean.getProClaimPaidOC().get(i).replaceAll(",",""))-Double.parseDouble(bean.getProClaimOutStandingOC().get(i).replaceAll(",",""))-managExp+Double.parseDouble(bean.getProfitLoss().get(i).replaceAll(",",""))+Double.parseDouble(bean.getOtherAdj().get(i).replaceAll(",",""));
					
					if("PR".equalsIgnoreCase(bean.getProfitCommType()) ){
						profitRto = (trtyRes/Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")))*100;
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType())){
						LossRto=((Double.parseDouble(bean.getProClaimPaidOC().get(i).replaceAll(",",""))+Double.parseDouble(bean.getProClaimOutStandingOC().get(i).replaceAll(",","")))/Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")))*100;
					}
					if(trtyRes>0){
					 if("PC".equalsIgnoreCase(bean.getProfitCommType())){
						 proftCom = (trtyRes * Double.parseDouble(bean.getProfitComPer().replaceAll(",","")))/100;
					 }
					 else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
						proftCom = trtyRes * profitRto /100 ;
					}
					else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
						proftCom = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(profitRto),bean.getPortfolioPremium().get(i).replaceAll(",","")));
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
						proftCom = trtyRes * LossRto /100 ;
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
						proftCom = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(profitRto),bean.getPortfolioPremium().get(i).replaceAll(",","")));
					}
					}
					if("Y".equalsIgnoreCase(bean.getSuperProfitCom())){
						String ratio = (Double.toString((trtyRes/Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")))*100));
						 superProfitComm = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),ratio,bean.getPortfolioPremium().get(i).replaceAll(",","")));
					 }
					profitCommAdj = (proftCom+superProfitComm-Double.parseDouble(bean.getPayedTillDate().get(i).replaceAll(",","")));
					
					manExp.add(DropDownControllor.formatter(Double.toString(managExp)));
					treatyRes.add(DropDownControllor.formatter(Double.toString(trtyRes)));
					profitRatio.add(DropDownControllor.formatter(Double.toString(profitRto)));
					lossRatio.add(DropDownControllor.formatter(Double.toString(LossRto)));
					profitComm.add(DropDownControllor.formatter(Double.toString(proftCom)));
					suprProfit.add(DropDownControllor.formatter(Double.toString(superProfitComm)));
					profitComAdj.add(DropDownControllor.formatter(Double.toString(profitCommAdj)));
				}
				bean.setManagExp(manExp);
				bean.setTreatyAdj(treatyRes);
				bean.setProfitRatio(profitRatio);
				bean.setLossRatio(lossRatio);
				bean.setProfitCommission(profitComm);
				bean.setSuperProfitComm(suprProfit);
				bean.setProfitCommAdj(profitComAdj);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
			public void ConvertedCurrencyValue() {
				String ExchangeRate;
				String premium;
				String unearned;
				String claim;
				String claimout;
				String shortName;
				String slideExchangeRate="";
				double premiumOC=0.00;
				double unearnedOC=0.00;
				double claimOC=0.00;
				double claimoutOC=0.00;
				double claimRatioOC=0.00;
				double losspremiumOC=0.00;
				double lossclaimOC=0.00;
				double lossclaimoutOC=0.00;
				double losspaidOC=0.00;
				double claimPaidRatioOC=0.00;
				
				double slideOC=0.00;
				double adjoutOC=0.00;
				double paidOC=0.00;
				double claimRatio=0.00;
				double claimPaidRatio=0.00;
				
				String slide="0.00";
				String paiddate="0.00";
				String slideAdj="0.00";
				bean.setBranchCode(branchCode);
				bean.setProductId(productId);
				double conPortfolioPremium=0.00;
				double conAccCostBrokerage=0.00;
				double conProClaimPaidOC=0.00;
				double conProClaimOutStandingOC=0.00;
				double conManagExp=0.00;
				double conProfitLoss=0.00;
				double conOtherAdj=0.00;
				double conTreatyAdj=0.00;
				double conProfitRatio=0.00;
				double conLossRatio=0.00;
				String conProfitCommission="0";
				String conSuperProfitComm="0";
				double conPayedTillDate=0.00;
				String conProfitCommAdj="0";
				
			for(int i=0;i<bean.getExchRatePrem().size();i++) {
			 	ExchangeRate = bean.getExchRatePrem().get(i);
				shortName = bean.getCurrencyShortName().get(i);
				ExchangeRate = ExchangeRate.replaceAll(",","");
				if("profitRage".equalsIgnoreCase(bean.getMode()) || "profit".equalsIgnoreCase(bean.getMode())){
					 conPortfolioPremium+= (Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conAccCostBrokerage+= (Double.parseDouble(bean.getAccCostBrokerage().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProClaimPaidOC+= (Double.parseDouble(bean.getProClaimPaidOC().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProClaimOutStandingOC+= (Double.parseDouble(bean.getProClaimOutStandingOC().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conManagExp+= (Double.parseDouble(bean.getManagExp().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProfitLoss+= (Double.parseDouble(bean.getProfitLoss().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conOtherAdj+=(Double.parseDouble(bean.getOtherAdj().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conTreatyAdj+= (Double.parseDouble(bean.getTreatyAdj().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conPayedTillDate+= (Double.parseDouble(bean.getPayedTillDate().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 slideExchangeRate +=shortName+"~"+ExchangeRate+",";
				}
				else if("lossRage".equalsIgnoreCase(bean.getMode()) || "loss".equalsIgnoreCase(bean.getMode())){
					premium = Double.toString(Double.parseDouble(bean.getLossPremiumOC().get(i).replaceAll(",",""))  * Double.parseDouble(ExchangeRate));
					claim = Double.toString(Double.parseDouble(bean.getLossClaimPaidOC().get(i).replaceAll(",",""))  * Double.parseDouble(ExchangeRate));
					claimout = Double.toString(Double.parseDouble(bean.getLossClaimOutStandingOC().get(i).replaceAll(",",""))  * Double.parseDouble(ExchangeRate));
					paiddate =  Double.toString(Double.parseDouble(bean.getLossPartRefTillDate().get(i).replaceAll(",",""))  * Double.parseDouble(ExchangeRate));
					unearned =  Double.toString(Double.parseDouble(bean.getUnearnpremiumOC().get(i).replaceAll(",",""))  * Double.parseDouble(ExchangeRate));
					losspremiumOC +=Double.parseDouble(premium);
					lossclaimOC +=Double.parseDouble(claim);
					lossclaimoutOC +=Double.parseDouble( claimout);
					losspaidOC +=Double.parseDouble( paiddate);
					unearnedOC +=Double.parseDouble(unearned);
					slideExchangeRate +=shortName+"~"+ExchangeRate+",";
					if(Double.parseDouble(premium)>0) {
						claimRatio = ((Double.parseDouble(claimout) + Double.parseDouble(claim)) / (Double.parseDouble(premium)-unearnedOC)) * 100;
						claimPaidRatio = ((Double.parseDouble(claim)) / (Double.parseDouble(premium)-unearnedOC) )* 100;
						claimRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatio));
						claimPaidRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimPaidRatio));
						claimPaidRatioOC += claimPaidRatio;
						claimRatioOC += claimRatio;
						String rate = SERVICE.getLossValue(bean, claimPaidRatio,premium);
						String rate1 = SERVICE.getLossValue(bean, claimRatio,premium);
						/*if (Double.parseDouble(rate)>0) {
							slide = Double.toString((Double.parseDouble(premium) * Double.parseDouble(rate)) / 100);
						}*/
						double adjtooutloss = Double.parseDouble(rate1) -Double.parseDouble(rate) ;
						adjoutOC+=adjtooutloss;
							slideOC += Double.parseDouble(slide);
							slideAdj = Double.toString(slideOC - paidOC);
					}
					
					
				}
				else{
					premium = bean.getPremiumOC().get(i);
					premium = premium.replaceAll(",","");
					claim = bean.getClaimPaidOC().get(i);
					claim =claim.replaceAll(",","");
					paiddate = bean.getCommPaidOCTillDate().get(i);
					paiddate =paiddate.replaceAll(",","");
					claimout = bean.getClaimOutStandingOC().get(i);
					claimout = claimout.replaceAll(",","");
					unearned = bean.getUnearnpremiumOC().get(i);
					unearned = unearned.replaceAll(",","");
					
					
					premium = Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate));
					unearned = Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate));
					claim = Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate));
					claimout = Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate));
					paiddate =  Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate));
					
					
					unearnedOC +=Double.parseDouble(unearned);
					premiumOC +=Double.parseDouble(premium);
					claimOC +=Double.parseDouble(claim);
					claimoutOC +=Double.parseDouble( claimout);
					paidOC +=Double.parseDouble( paiddate);
					slideExchangeRate +=shortName+"~"+ExchangeRate+",";
					
				}
			}
			if("slideRage".equalsIgnoreCase(bean.getMode()) || "slide".equalsIgnoreCase(bean.getMode())){
				if(premiumOC>0) {
					if((premiumOC)>=0){
					claimRatio = (((claimOC) + (claimoutOC)) / (premiumOC-unearnedOC)) * 100;
					claimRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatio));
					}
					claimRatioOC += claimRatio;
					String rate = SERVICE.getSlideValue(bean, claimRatio);
					if (Double.parseDouble(rate)>0) {
						slide = Double.toString((premiumOC * Double.parseDouble(rate)) / 100);
					}
				}
				slideOC += Double.parseDouble(slide);
				slideAdj = Double.toString(slideOC - paidOC);
			}
		if ("profitRage".equalsIgnoreCase(bean.getMode()) || "profit".equalsIgnoreCase(bean.getMode())){
			if(conTreatyAdj>0){
				if("PR".equalsIgnoreCase(bean.getProfitCommType()) ){
					 conProfitRatio = (conTreatyAdj/conPortfolioPremium)*100;
					 conProfitRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatio));
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType())){
						conLossRatio=((conProClaimPaidOC+conProClaimOutStandingOC)/conPortfolioPremium)*100;
						conLossRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(conLossRatio));
					}
					 if("PC".equalsIgnoreCase(bean.getProfitCommType())){
						 conProfitCommission =Double.toString( (conTreatyAdj * Double.parseDouble(bean.getProfitComPer().replaceAll(",","")))/100);
					 }
					 else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
						 conProfitCommission = Double.toString((conTreatyAdj * conProfitRatio) /100 );
					}
					else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
						conProfitCommission = SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(conProfitRatio),Double.toString(conPortfolioPremium));
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
						conProfitCommission = Double.toString((conTreatyAdj *conLossRatio) /100) ;
					}
					else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
						conProfitCommission  = SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(conLossRatio),Double.toString(conPortfolioPremium));
					}
			}
					if("Y".equalsIgnoreCase(bean.getSuperProfitCom())){
						String ratio="0";
						if(conTreatyAdj>0 && conPortfolioPremium>0)
						ratio = Double.toString((conTreatyAdj/conPortfolioPremium)*100);
						conSuperProfitComm = SERVICE.getPremiumValue(bean,bean.getProfitCommType(),ratio,Double.toString(conPortfolioPremium));
					 }
				 conProfitCommAdj=Double.toString(Double.parseDouble(conProfitCommission)+Double.parseDouble(conSuperProfitComm)-conPayedTillDate);
			}
				bean.setConlossPremiumOC(DropDownControllor.formatter(Double.toString(losspremiumOC)));
				bean.setConlossClaimPaidOC(DropDownControllor.formatter(Double.toString(lossclaimOC)));
				bean.setConlossClaimOutStandingOC(DropDownControllor.formatter(Double.toString(lossclaimoutOC)));
				bean.setConlossClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
				bean.setConlossOC(DropDownControllor.formatter(Double.toString(slideOC)));
				bean.setConlossAdjOC(DropDownControllor.formatter(slideAdj));
				bean.setConlossTillOC(DropDownControllor.formatter(Double.toString(losspaidOC)));
				bean.setConClaimPaidRatioOC(DropDownControllor.formatter(Double.toString(claimPaidRatioOC)));	
				bean.setAdjToOutLoss(DropDownControllor.formatter(Double.toString(adjoutOC)));
				bean.setConPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
				bean.setConunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
				bean.setConClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
				bean.setConClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
				bean.setConCommPaidOC(DropDownControllor.formatter(Double.toString(paidOC)));
				//bean.setManualconCommPaidOC(DropDownControllor.formatter(Double.toString(paidOC)));
				bean.setConClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
				bean.setConSlideScaleCommOC(DropDownControllor.formatter(Double.toString(slideOC)));
				bean.setConSlideScaleAdjOC(DropDownControllor.formatter(slideAdj));
				bean.setConPortfolioPremium(DropDownControllor.formatter(Double.toString(conPortfolioPremium)));
				bean.setConAccCostBrokerage(DropDownControllor.formatter(Double.toString(conAccCostBrokerage)));
				bean.setConProClaimPaidOC(DropDownControllor.formatter(Double.toString(conProClaimPaidOC)));
				bean.setConManagExp(DropDownControllor.formatter(Double.toString(conManagExp)));
				bean.setConProClaimOutStandingOC(DropDownControllor.formatter(Double.toString(conProClaimOutStandingOC)));
				bean.setConProfitLoss(DropDownControllor.formatter(Double.toString(conProfitLoss)));
				bean.setConLossRatio(DropDownControllor.formatter(Double.toString(conLossRatio)));
				bean.setConProfitCommission(DropDownControllor.formatter(conProfitCommission));
				bean.setConSuperProfitComm(DropDownControllor.formatter(conSuperProfitComm));
				bean.setConPayedTillDate(DropDownControllor.formatter(Double.toString(conPayedTillDate)));
				bean.setConProfitCommAdj(DropDownControllor.formatter(conProfitCommAdj));
				bean.setConProfitRatio(DropDownControllor.formatter(Double.toString(conProfitRatio)));
				bean.setSlideExchangeRate(slideExchangeRate);
				bean.setConOtherAdj(DropDownControllor.formatter(Double.toString(conOtherAdj)));
				bean.setConTreatyAdj(DropDownControllor.formatter(Double.toString(conTreatyAdj)));
				bean.setConunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
				
				if(StringUtils.isBlank(bean.getFlag()) && StringUtils.isNotBlank(bean.getMode()) &&"loss".equalsIgnoreCase(bean.getMode())){
					bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(losspremiumOC)));
					bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
					bean.setManualconlossClaimPaidOC(DropDownControllor.formatter(Double.toString(lossclaimOC)));
					bean.setManualconlossClaimOutStandingOC(DropDownControllor.formatter(Double.toString(lossclaimoutOC)));
					bean.setManualconlossClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
					bean.setManualconClaimPaidRatioOC(DropDownControllor.formatter(Double.toString(claimPaidRatioOC)));
				}if(StringUtils.isBlank(bean.getFlag()) && StringUtils.isNotBlank(bean.getMode()) &&"slide".equalsIgnoreCase(bean.getMode())){
					bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
					bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
					bean.setManualconClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
					bean.setManualconClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
					bean.setManualconClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
				}
				 if(StringUtils.isBlank(bean.getFlag()) && StringUtils.isNotBlank(bean.getMode()) &&"profit".equalsIgnoreCase(bean.getMode())){
						bean.setManualconPortfolioPremium(DropDownControllor.formatter(Double.toString(conPortfolioPremium)));
						bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
						bean.setManualconAccCostBrokerage(DropDownControllor.formatter(Double.toString(conAccCostBrokerage)));
						bean.setManualconTreatyAdj(DropDownControllor.formatter(Double.toString(conTreatyAdj)));
						bean.setManualconProClaimPaidOC(DropDownControllor.formatter(Double.toString(conProClaimPaidOC)));
						bean.setManualconProClaimOutStandingOC(DropDownControllor.formatter(Double.toString(conProClaimOutStandingOC)));
						bean.setManualconManagExp(DropDownControllor.formatter(Double.toString(conManagExp)));
						bean.setManualconProfitRatio(DropDownControllor.formatter(Double.toString(conProfitRatio)));
						bean.setManualconOtherAdj(DropDownControllor.formatter(Double.toString(conOtherAdj)));
						bean.setManualconProfitRatio(DropDownControllor.formatter(Double.toString(conProfitRatio)));
						bean.setManualconLossRatio(DropDownControllor.formatter(Double.toString(conLossRatio)));
						}
				if("recal".equalsIgnoreCase(bean.getFlag()) && "slideRage".equalsIgnoreCase(bean.getMode())){
					getManualValues();
				}else if("recal".equalsIgnoreCase(bean.getFlag()) && "lossRage".equalsIgnoreCase(bean.getMode())){
					getManualLPCValues();
				}
				else if("Calculation".equalsIgnoreCase(bean.getFlag()) && "profitRage".equalsIgnoreCase(bean.getMode())){
					getManualProfitValues();
				}
				
			}
		
			private void getManualValues() {
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
				double claimRatioOCManual=0;
				double slideOC=0.00;
				double paidOC=0.00;
				double claimRatio=0.00;
				
				String slide="0.00";
				String paiddate="0.00";
				String manualPaid="0";
				String slideAdj="0.00";
				bean.setBranchCode(branchCode);
				bean.setProductId(productId);
				List<String> slideCom =new ArrayList<String>();
				List<String> commpaid = new ArrayList<String>();
				List<String> slideAdjment = new ArrayList<String>();
				List<String> claimRatioManualList = new ArrayList<String>();
				String slidCom="";
				double slodeComsion=0;
				
				try{
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
						ExchangeRate = bean.getExchRatePrem().get(i);
						ExchangeRate = ExchangeRate.replaceAll(",","");
					premium = bean.getManualPremiumOC().get(i);
					premium = premium.replaceAll(",","");
					claim = bean.getManualclaimPaidOC().get(i);
					claim =claim.replaceAll(",","");
					if(StringUtils.isBlank(bean.getManualcommPaidOCTillDate().get(i))){
					paiddate = StringUtils.isBlank(bean.getCommPaidOCTillDate().get(i))?"0":bean.getCommPaidOCTillDate().get(i).replaceAll(",","");
					}else{
						paiddate = StringUtils.isBlank(bean.getManualcommPaidOCTillDate().get(i))?"0":bean.getManualcommPaidOCTillDate().get(i).replaceAll(",","");
					}
					paiddate =paiddate.replaceAll(",","");
					manualPaid = paiddate.replaceAll(",","");
					claimout = bean.getManualclaimOutStandingOC().get(i);
					claimout = claimout.replaceAll(",","");
					unearned = bean.getManualunearnpremiumOC().get(i);
					unearned = unearned.replaceAll(",","");
					
					slidCom = StringUtils.isBlank(bean.getManualslideScaleCommOC().get(i))?"":bean.getManualslideScaleCommOC().get(i).replaceAll(",","");
					slidCom =slidCom.replaceAll(",","");
					
					
					
					
					//premium = Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate));
					//unearned = Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate));
					//claim = Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate));
					//claimout = Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate));
					//paiddate =  Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate));
					
					unearnedOC +=Double.parseDouble(Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate)));
					premiumOC +=Double.parseDouble(Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate)));
					claimOC +=Double.parseDouble(Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate)));
					claimoutOC +=Double.parseDouble( Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate)));
					paidOC +=Double.parseDouble( Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate)));
					
					
					String claimRatioManual="0";
					String slideManual="0";
					
					double slideOCManual =0;
					String slideAdjManual="0";
					if(Double.parseDouble(premium)>0) {
						if(Double.parseDouble(premium)>=0){
							claimRatioOCManual = (((Double.parseDouble(claim)+ Double.parseDouble(claimout)) / (Double.parseDouble(premium)-Double.parseDouble(unearned))) * 100);
							claimRatioOCManual=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatioOCManual));
							logger.info("JSP Calculation Slide Manual Claims Ratio "+bean.getManualclaimRatioOC().get(i).replaceAll(",","") + " in Row number " +i);
							logger.info("Java Calculation Slide Manual Claims Ratio "+claimRatioOCManual+ " in Row number " +i);
							if(!Double.toString(claimRatioOCManual).equalsIgnoreCase(bean.getManualclaimRatioOC().get(i).replaceAll(",",""))){
								addActionError(getText("error.calcul.mistake"));
								logger.info("Calculation Failed. Please retry. If problem persists, please contact support.");
							}else{
								bean.getManualclaimRatioOC().set(i,DropDownControllor.formatter(Double.toString(claimRatioOCManual)));
							}
						}
						//claimRatioOCManual = Double.parseDouble(bean.getManualclaimRatioOC().get(i).replaceAll(",",""));
						if("0.00".equalsIgnoreCase(slidCom) ||StringUtils.isBlank(slidCom)){
						String rate1 = SERVICE.getSlideValue(bean, claimRatioOCManual);
						if (Double.parseDouble(rate1)>0) {
							slideManual = Double.toString((Double.parseDouble(premium) * Double.parseDouble(rate1)) / 100);
						}
						}else{
							slideManual =slidCom;
						}
					}
					
					slideOCManual = Double.parseDouble(slideManual);
					slideAdjManual = Double.toString(slideOCManual - Double.parseDouble(manualPaid));
					commpaid.add(DropDownControllor.formatter(manualPaid));
					claimRatioManualList.add(DropDownControllor.formatter(claimRatioManual));
					slideCom.add(DropDownControllor.formatter(Double.toString(slideOCManual)));
					slideAdjment.add(DropDownControllor.formatter(slideAdjManual));
					}
					if(premiumOC>0) {
						if((premiumOC)>=0){
						claimRatio = (((claimOC) + (claimoutOC)) / (premiumOC-unearnedOC)) * 100;
						claimRatio=Double.parseDouble(dropDownController.GetTwoDecimalFormate(claimRatio));
						
						}
						claimRatioOC += (claimRatio);
						String rate = SERVICE.getSlideValue(bean, claimRatio);
						if (Double.parseDouble(rate)>0) {
							
							slide = Double.toString((premiumOC * Double.parseDouble(rate)) / 100);
						}
					}
					
					slideOC += Double.parseDouble(slide);
					slideAdj = Double.toString(slideOC - paidOC);
					//bean.setManualconClaimRatioOC(claimRatioManualList);
					//bean.setManualclaimPaidOC(claimOC);
					bean.setManualslideScaleCommOC(slideCom);
					bean.setManualslideScaleAdjOC(slideAdjment);
					bean.setManualcommPaidOCTillDate(commpaid);
					bean.setManualconCommPaidOC(DropDownControllor.formatter(Double.toString(paidOC)));
					bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
					bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
					bean.setManualconClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
					bean.setManualconClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
					bean.setManualconClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
					bean.setManualconSlideScaleCommOC(DropDownControllor.formatter(Double.toString(slideOC)));
					bean.setManualconSlideScaleAdjOC(DropDownControllor.formatter(slideAdj));
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			private void getManualLPCValues() {
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
				double claimRatio=0.00;
				
				String loss="0.00";
				String paiddate="0.00";
				String manualPaid="0";
				String lossAdj="0.00";
				double manualadjoutOC=0;
				bean.setBranchCode(branchCode);
				bean.setProductId(productId);
				List<String> lossCom =new ArrayList<String>();
				List<String> commpaid = new ArrayList<String>();
				List<String> lossAdjment = new ArrayList<String>();
				List<String> claimRatioManualList = new ArrayList<String>();
				String losspartOC="";
				double slodeComsion=0;
				
				try{
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
						ExchangeRate = bean.getExchRatePrem().get(i);
						ExchangeRate = ExchangeRate.replaceAll(",","");
					premium = bean.getManualPremiumOC().get(i).replaceAll(",","");
					claim = bean.getManuallossClaimPaidOC().get(i).replaceAll(",","");
					if(StringUtils.isBlank(bean.getManuallossPartRefTillDate().get(i))){
					paiddate = StringUtils.isBlank(bean.getLossPartRefTillDate().get(i))?"0":bean.getLossPartRefTillDate().get(i).replaceAll(",","");
					}else{
						paiddate = StringUtils.isBlank(bean.getManuallossPartRefTillDate().get(i))?"0":bean.getManuallossPartRefTillDate().get(i).replaceAll(",","");
					}
					paiddate =paiddate.replaceAll(",","");
					manualPaid = paiddate.replaceAll(",","");
					claimout = bean.getManuallossClaimOutStandingOC().get(i).replaceAll(",","");
					unearned = bean.getManualunearnpremiumOC().get(i).replaceAll(",","");
					
					losspartOC = StringUtils.isBlank(bean.getManuallossPartOC().get(i))?"":bean.getManuallossPartOC().get(i).replaceAll(",","");
					
					
					
					
					//premium = Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate));
					//unearned = Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate));
					//claim = Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate));
					//claimout = Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate));
					//paiddate =  Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate));
					
					unearnedOC +=Double.parseDouble(Double.toString(Double.parseDouble(unearned)  * Double.parseDouble(ExchangeRate)));
					premiumOC +=Double.parseDouble(Double.toString(Double.parseDouble(premium)  * Double.parseDouble(ExchangeRate)));
					claimOC +=Double.parseDouble(Double.toString(Double.parseDouble(claim)  * Double.parseDouble(ExchangeRate)));
					claimoutOC +=Double.parseDouble( Double.toString(Double.parseDouble(claimout)  * Double.parseDouble(ExchangeRate)));
					paidOC +=Double.parseDouble( Double.toString(Double.parseDouble(paiddate)  * Double.parseDouble(ExchangeRate)));
					
					
					String claimRatioManual="0";
					String claimRatioManual1="0";
					String lossManual="0";
					double claimRatioOCManual=0;
					double lossOCManual =0;
					
					String lossAdjManual="0";
					if(Double.parseDouble(premium)>0) {
						if(Double.parseDouble(premium)>=0){
							try{
								String ans = Double.toString(((Double.parseDouble(claim)+ Double.parseDouble(claimout)) / (Double.parseDouble(premium)-Double.parseDouble(unearned))) * 100);
								logger.info("JSP Calculation Loss Manual Claims Ratio "+bean.getManuallossClaimRatioOC().get(i).replaceAll(",","") + " in Row number " +i);
								ans = DropDownControllor.formatter(ans).replaceAll(",","");
								logger.info("Java Calculation Loss Manual Claims Ratio "+ans+ " in Row number " +i);
								if(!ans.equalsIgnoreCase(bean.getManuallossClaimRatioOC().get(i).replaceAll(",",""))){
									addActionError(getText("error.calcul.mistake"));
									logger.info("Calculation Failed. Please retry. If problem persists, please contact support.");
								}else{
									bean.getManuallossClaimRatioOC().set(i,DropDownControllor.formatter(ans));
								}
								ans = Double.toString(((Double.parseDouble(claim)) / (Double.parseDouble(premium)-Double.parseDouble(unearned))) * 100);
								logger.info("JSP Calculation Loss Manual Claims Paid Ratio "+bean.getManuallossclaimPaidRatioOC().get(i).replaceAll(",","") + " in Row number " +i);
								ans = DropDownControllor.formatter(ans).replaceAll(",","");
								logger.info("Java Calculation Loss ManualClaims Paid Ratio "+ans+ " in Row number " +i);
								if(!ans.equalsIgnoreCase(bean.getManuallossclaimPaidRatioOC().get(i).replaceAll(",",""))){
									addActionError(getText("error.calcul.mistake"));
									logger.info("Calculation Failed. Please retry. If problem persists, please contact support.");
								}else{
									bean.getManuallossclaimPaidRatioOC().set(i,DropDownControllor.formatter(ans));
								}
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
						claimRatioManual= bean.getManuallossClaimRatioOC().get(i).replaceAll(",","");
						claimRatioManual1=bean.getManuallossclaimPaidRatioOC().get(i).replaceAll(",","");
						//claimRatioOCManual = Double.parseDouble(claimRatioManual);
						if("0.00".equalsIgnoreCase(losspartOC) ||StringUtils.isBlank(losspartOC)){
						String rate1 = SERVICE.getLossValue(bean, Double.parseDouble(claimRatioManual), premium);
						String rate2 = SERVICE.getLossValue(bean, Double.parseDouble(claimRatioManual1),premium);
						double adjtooutloss = Double.parseDouble(rate1) -Double.parseDouble(rate2) ;
						manualadjoutOC+=adjtooutloss;
						if (Double.parseDouble(rate1)>0) {
							lossManual =rate2 ;
						}
						}else{
							lossManual =losspartOC;
						}
					}
					claimRatio=+Double.parseDouble(claimRatioManual);
					lossOCManual = Double.parseDouble(lossManual);
					lossAdjManual = Double.toString(lossOCManual - Double.parseDouble(manualPaid));
					commpaid.add(DropDownControllor.formatter(manualPaid));
					claimRatioManualList.add(DropDownControllor.formatter(claimRatioManual));
					lossCom.add(DropDownControllor.formatter(Double.toString(lossOCManual)));
					lossAdjment.add(DropDownControllor.formatter(lossAdjManual));
					}
					if(premiumOC>0) {
						//if((premiumOC)>=0){
						//claimRatio = Double.toString((((claimOC) + (claimoutOC)) / (premiumOC-unearnedOC)) * 100);
						//}
						claimRatioOC += claimRatio;
						String rate = SERVICE.getLossValue(bean, claimRatio,Double.toString(premiumOC));
						if (Double.parseDouble(rate)>0) {
							loss = Double.toString((premiumOC * Double.parseDouble(rate)) / 100);
						}
					}
					lossOC += Double.parseDouble(loss);
					lossAdj = Double.toString(lossOC - paidOC);
					//bean.setManualconClaimRatioOC(claimRatioManualList);
					//bean.setManualclaimPaidOC(claimOC);
					bean.setManuallossPartOC(lossCom);
					bean.setManuallossPartRefAdjOC(lossAdjment);
					bean.setManuallossPartRefTillDate(commpaid);
					bean.setManualconlossAdjOC(DropDownControllor.formatter(Double.toString(paidOC)));
					bean.setManualconPremiumOC(DropDownControllor.formatter(Double.toString(premiumOC)));
					bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(unearnedOC)));
					bean.setManualconlossClaimPaidOC(DropDownControllor.formatter(Double.toString(claimOC)));
					bean.setManualconlossClaimOutStandingOC(DropDownControllor.formatter(Double.toString(claimoutOC)));
					bean.setManualconlossClaimRatioOC(DropDownControllor.formatter(Double.toString(claimRatioOC)));
					bean.setManualconlossOC(DropDownControllor.formatter(Double.toString(lossOC)));
					bean.setManualconlossTillOC(DropDownControllor.formatter(Double.toString(paidOC)));
					bean.setManualconlossAdjOC(DropDownControllor.formatter(lossAdj));
					bean.setManualadjoutOC(DropDownControllor.formatter(Double.toString(manualadjoutOC)));
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			private void getManualProfitValues() {
				String ExchangeRate;
				String shortName;
				double managExp=0;
				double trtyRes=0;
				double profitRto =0;
				double LossRto =0;
				double proftCom=0;
				double payTillDate=0;
				String paiddate="0.00";
				String manualPaid="0";
				double superProfitComm =0;
				double profitCommAdj =0;
				String slideExchangeRate="";
				double conPortfolioPremium=0.00;
				double conunearned=0.00;
				double conAccCostBrokerage=0.00;
				double conProClaimPaidOC=0.00;
				double conProClaimOutStandingOC=0.00;
				double conManagExp=0.00;
				double conProfitLoss=0.00;
				double conOtherAdj=0.00;
				double conTreatyAdj=0.00;
				String conProfitRatio="0";
				String conLossRatio="0";
				double conProfitCommission=0.00;
				double conSuperProfitComm=0.00;
				double conPayedTillDate=0.00;
				double conProfitCommAdj=0.00;
				List<String> manualtrety =new ArrayList<String>();
				List<String> manualProfitCom =new ArrayList<String>();
				List<String> manuallsuperProfitComm =new ArrayList<String>();
				List<String> manualprofitCommAdj =new ArrayList<String>();
				List<String> manualpaidDate =new ArrayList<String>();
				
				try {
					for(int i=0;i<bean.getExchRatePrem().size();i++) {
					 	ExchangeRate = bean.getExchRatePrem().get(i);
						shortName = bean.getCurrencyShortName().get(i);
						ExchangeRate = ExchangeRate.replaceAll(",","");
						if(StringUtils.isBlank(bean.getManualpayedTillDate().get(i))){
							paiddate = StringUtils.isBlank(bean.getPayedTillDate().get(i))?"0":bean.getPayedTillDate().get(i).replaceAll(",","");
							}else{
								paiddate = StringUtils.isBlank(bean.getManualpayedTillDate().get(i))?"0":bean.getManualpayedTillDate().get(i).replaceAll(",","");
							}
						managExp =(Double.parseDouble(bean.getManualportfolioPremium().get(i).replaceAll(",","")) * Double.parseDouble(bean.getManagementExp().replaceAll(",","")))/100;
						trtyRes = Double.parseDouble(bean.getManualportfolioPremium().get(i).replaceAll(",",""))-Double.parseDouble(bean.getManualunearnpremiumOC().get(i).replaceAll(",","")) -Double.parseDouble(bean.getManualaccCostBrokerage().get(i).replaceAll(",",""))-Double.parseDouble(bean.getManualproClaimPaidOC().get(i).replaceAll(",",""))-Double.parseDouble(bean.getManualproClaimOutStandingOC().get(i).replaceAll(",",""))-managExp+Double.parseDouble(bean.getManualprofitLoss().get(i).replaceAll(",",""))+Double.parseDouble(bean.getManualotherAdj().get(i).replaceAll(",",""));
						
						if("PR".equalsIgnoreCase(bean.getProfitCommType()) ){
							profitRto = (trtyRes/Double.parseDouble(bean.getManualportfolioPremium().get(i).replaceAll(",","")))*100;
						}
						else if("LR".equalsIgnoreCase(bean.getProfitCommType())){
							LossRto=((Double.parseDouble(bean.getManualproClaimPaidOC().get(i).replaceAll(",",""))+Double.parseDouble(bean.getManualproClaimOutStandingOC().get(i).replaceAll(",","")))/Double.parseDouble(bean.getManualportfolioPremium().get(i).replaceAll(",","")))*100;
						}
						if(trtyRes>0){
						 if("PC".equalsIgnoreCase(bean.getProfitCommType())){
							 proftCom = (trtyRes * Double.parseDouble(bean.getProfitComPer().replaceAll(",","")))/100;
						 }
						 else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
							proftCom = trtyRes * profitRto /100 ;
						}
						else if("PR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
							proftCom = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(profitRto),bean.getManualportfolioPremium().get(i).replaceAll(",","")));
						}
						else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"N".equalsIgnoreCase(bean.getStepUp())){
							proftCom = trtyRes * LossRto /100 ;
						}
						else if("LR".equalsIgnoreCase(bean.getProfitCommType()) &&"Y".equalsIgnoreCase(bean.getStepUp())){
							proftCom = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),Double.toString(profitRto),bean.getManualportfolioPremium().get(i).replaceAll(",","")));
						}
						}
						if("Y".equalsIgnoreCase(bean.getSuperProfitCom())){
							String ratio = (Double.toString((trtyRes/Double.parseDouble(bean.getPortfolioPremium().get(i).replaceAll(",","")))*100));
							 superProfitComm = Double.parseDouble(SERVICE.getPremiumValue(bean,bean.getProfitCommType(),ratio,bean.getManualportfolioPremium().get(i).replaceAll(",","")));
						 }
						payTillDate=Double.parseDouble(StringUtils.isBlank(bean.getManualpayedTillDate().get(i))?"0":bean.getManualpayedTillDate().get(i).replaceAll(",",""));
						
						
						profitCommAdj = (proftCom+superProfitComm-payTillDate);
						manualtrety.add(DropDownControllor.formatter(Double.toString(trtyRes)));
						manualProfitCom.add(DropDownControllor.formatter(Double.toString(proftCom)));
						manuallsuperProfitComm.add(DropDownControllor.formatter(Double.toString(superProfitComm)));
						manualprofitCommAdj.add(DropDownControllor.formatter(Double.toString(profitCommAdj)));
						manualpaidDate.add(DropDownControllor.formatter(paiddate));
						
					 conPortfolioPremium+= (Double.parseDouble(bean.getManualportfolioPremium().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conunearned += (Double.parseDouble(bean.getManualunearnpremiumOC().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate)); 
					 conAccCostBrokerage+= (Double.parseDouble(bean.getManualaccCostBrokerage().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProClaimPaidOC+= (Double.parseDouble(bean.getManualproClaimPaidOC().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProClaimOutStandingOC+= (Double.parseDouble(bean.getManualproClaimOutStandingOC().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conManagExp+= (Double.parseDouble(bean.getManualmanagExp().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conProfitLoss+= (Double.parseDouble(bean.getManualprofitLoss().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conOtherAdj+=(Double.parseDouble(bean.getManualotherAdj().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conTreatyAdj+= (Double.parseDouble(bean.getManualtreatyAdj().get(i).replaceAll(",", ""))* Double.parseDouble(ExchangeRate));
					 conPayedTillDate+= (payTillDate* Double.parseDouble(ExchangeRate));
					 slideExchangeRate +=shortName+"~"+ExchangeRate+",";
					 conProfitCommission+=(proftCom* Double.parseDouble(ExchangeRate));
						conSuperProfitComm+=(superProfitComm* Double.parseDouble(ExchangeRate));
						conPayedTillDate+=(Double.parseDouble(paiddate)* Double.parseDouble(ExchangeRate));
						conProfitCommAdj+=(profitCommAdj* Double.parseDouble(ExchangeRate));
					}
					 bean.setManualtreatyAdj(manualtrety);
					 bean.setManualprofitCommission(manualProfitCom);
					 bean.setManualsuperProfitComm(manuallsuperProfitComm);
					 bean.setManualprofitCommAdj(manualprofitCommAdj);
					 bean.setManualpayedTillDate(manualpaidDate);
					 
					 bean.setManualconPortfolioPremium(DropDownControllor.formatter(Double.toString(conPortfolioPremium)));
					 bean.setManualconunearnpremiumOC(DropDownControllor.formatter(Double.toString(conunearned)));
					 bean.setManualconAccCostBrokerage(DropDownControllor.formatter(Double.toString(conAccCostBrokerage)));
					 bean.setManualconProClaimPaidOC(DropDownControllor.formatter(Double.toString(conProClaimPaidOC)));
					 bean.setManualconProClaimOutStandingOC(DropDownControllor.formatter(Double.toString(conProClaimOutStandingOC)));
					 bean.setManualconManagExp(DropDownControllor.formatter(Double.toString(conManagExp)));
					 bean.setManualconProfitLoss(DropDownControllor.formatter(Double.toString(conProfitLoss)));
					 bean.setManualconOtherAdj(DropDownControllor.formatter(Double.toString(conOtherAdj)));
					 bean.setManualconTreatyAdj(DropDownControllor.formatter(Double.toString(conTreatyAdj)));
					 bean.setManualconPayedTillDate(DropDownControllor.formatter(Double.toString(conPayedTillDate)));
					 bean.setManualconProfitCommission(DropDownControllor.formatter(Double.toString(conProfitCommission)));
					 bean.setManualconSuperProfitComm(DropDownControllor.formatter(Double.toString(conSuperProfitComm)));
					 bean.setManualconPayedTillDate(DropDownControllor.formatter(Double.toString(conPayedTillDate)));
					 bean.setManualconProfitCommAdj(DropDownControllor.formatter(Double.toString(conProfitCommAdj)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			/*public String insertSlide(){  As on mail 05/10 dropped table and modified pop up logic By Ramya
			bean.setBranchCode(branchCode);
			bean.setProductId(productId);
			bean.setLoginId(userId);
			String forward="streamResult";
			//SERVICE.insertSlideCommission(bean);
			String val="";
			String value="";
			
			if("slideSubmit".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isNotBlank(bean.getConSlideScaleAdjOC())){
					 val=StringUtils.isEmpty(bean.getConSlideScaleAdjOC()) ? "0.00" :bean.getConSlideScaleAdjOC();
				}
				else{
					for(int i=0;i<bean.getSlideScaleAdjOC().size();i++){
						val +=bean.getSlideScaleAdjOC().get(i);
					}
				}
				value="<script type='text/javascript'>window.opener.premium.slideScaleCom.value='"+ val +"';window.opener.premium.disableStatus.value=true;window.opener.premium.conPremiumOC.value='"+ bean.getConPremiumOC() +"'; window.opener.premium.conClaimPaidOC.value='"+ bean.getConClaimPaidOC() +"'; window.opener.premium.conClaimOutStandingOC.value='"+ bean.getConClaimOutStandingOC() +"'; window.opener.premium.conClaimRatioOC.value='"+ bean.getConClaimRatioOC() +"'; window.opener.premium.conSlideScaleCommOC.value='"+bean.getConSlideScaleCommOC() +"'; window.opener.premium.conCommPaidOC.value='"+ bean.getConCommPaidOC() +"';	 window.opener.premium.conSlideScaleAdjOC.value='"+ bean.getConSlideScaleAdjOC()+"';window.close();</script>";
				session.put("PremiumOc", bean.getPremiumOC());
				session.put("ClaimPaidOc", bean.getClaimPaidOC());
				session.put("ClaimOutStandingOC",  bean.getClaimOutStandingOC());
				session.put("ClaimRatioOC", bean.getClaimRatioOC());
				session.put("SlideScaleCommOC", bean.getSlideScaleCommOC());
				session.put("CommPaidOCTillDate", bean.getCommPaidOCTillDate());
				session.put("SlideScaleAdjOC", bean.getSlideScaleAdjOC());
				session.put("ExchRatePrem", bean.getExchRatePrem());
			}else if("lossSubmit".equalsIgnoreCase(bean.getMode())){
				if(StringUtils.isNotBlank(bean.getConlossAdjOC())){
					 val=StringUtils.isEmpty(bean.getConlossAdjOC()) ? "0.00" :bean.getConlossAdjOC();
				}
				else{
					for(int i=0;i<bean.getLossPartRefAdjOC().size();i++){
						val +=bean.getLossPartRefAdjOC().get(i);
					}
				}
				value="<script type='text/javascript'>window.opener.premium.lossParticipation.value='"+ val +"';window.opener.premium.disableStatus.value=true;window.opener.premium.conlossPremiumOC.value='"+ bean.getConlossPremiumOC() +"'; window.opener.premium.conlossClaimPaidOC.value='"+ bean.getConlossClaimPaidOC() +"'; window.opener.premium.conlossClaimOutStandingOC.value='"+ bean.getConlossClaimOutStandingOC() +"'; window.opener.premium.conlossClaimRatioOC.value='"+ bean.getConlossClaimRatioOC() +"'; window.opener.premium.conClaimPaidRatioOC.value='"+ bean.getConClaimPaidRatioOC() +"'; window.opener.premium.conlossOC.value='"+bean.getConlossOC() +"'; window.opener.premium.conlossTillOC.value='"+ bean.getConlossTillOC() +"';	 window.opener.premium.conlossAdjOC.value='"+ bean.getConlossAdjOC()+"';	 window.opener.premium.adjToOutLoss.value='"+ bean.getAdjToOutLoss()+"';window.close();</script>";
				session.put("lossPremiumOc", bean.getLossPremiumOC());
				session.put("lossClaimPaidOc", bean.getLossClaimPaidOC());
				session.put("lossClaimOutStandingOC",  bean.getLossClaimOutStandingOC());
				session.put("lossClaimRatioOC", bean.getLossClaimRatioOC());
				session.put("ClaimPaidRatioOC", bean.getClaimPaidRatioOC());
				session.put("LossPartOC", bean.getLossPartOC());
				session.put("LossPartRefTillDate", bean.getLossPartRefTillDate());
				session.put("LossPartRefAdjOC", bean.getLossPartRefAdjOC());
				session.put("ExchRatePrem", bean.getExchRatePrem());
			}
			else{
				if(StringUtils.isNotBlank(bean.getConProfitCommAdj())){
					 val=StringUtils.isEmpty(bean.getConProfitCommAdj()) ? "0.00" :bean.getConProfitCommAdj();
				}
				else{
					for(int i=0;i<bean.getProfitCommAdj().size();i++){
						val +=bean.getProfitCommAdj().get(i);
					}
				}
				value="<script type='text/javascript'>window.opener.premium.profit_Commission.value='"+ val +"';window.opener.premium.disableStatus.value=true; window.opener.premium.conPortfolioPremium.value='"+ bean.getConPortfolioPremium() +"'; window.opener.premium.conAccCostBrokerage.value='"+ bean.getConAccCostBrokerage() +"';  window.opener.premium.conProClaimPaidOC.value='"+ bean.getConProClaimPaidOC() +"'; window.opener.premium.conProClaimOutStandingOC.value='"+ bean.getConProClaimOutStandingOC() +"';  window.opener.premium.conManagExp.value='"+bean.getConManagExp() +"'; window.opener.premium.conProfitLoss.value='"+ bean.getConProfitLoss() +"';	  window.opener.premium.conOtherAdj.value='"+ bean.getConOtherAdj()+"';window.opener.premium.conTreatyAdj.value='"+ bean.getConTreatyAdj()+"';  window.opener.premium.conProfitRatio.value='"+ bean.getConProfitRatio()+"';window.opener.premium.conLossRatio.value='"+ bean.getConLossRatio()+"';  window.opener.premium.conProfitCommission.value='"+ bean.getConProfitCommission()+"';window.opener.premium.conSuperProfitComm.value='"+ bean.getConSuperProfitComm()+"';  window.opener.premium.conPayedTillDate.value='"+ bean.getConPayedTillDate()+"';window.opener.premium.conProfitCommAdj.value='"+ bean.getConProfitCommAdj()+"';  window.close();</script>";
				session.put("portfolioPremium", bean.getPortfolioPremium());
				session.put("accCostBrokerage", bean.getAccCostBrokerage());
				session.put("managExp", bean.getManagExp());
				session.put("proClaimPaidOC", bean.getProClaimPaidOC());
				session.put("proClaimOutStandingOC", bean.getProClaimOutStandingOC());
				session.put("profitLoss", bean.getProfitLoss());
				session.put("otherAdj", bean.getOtherAdj());
				session.put("treatyAdj", bean.getTreatyAdj());
				session.put("profitRatio", bean.getProfitRatio());
				session.put("lossRatio", bean.getLossRatio());
				session.put("profitCommission", bean.getProfitCommission());
				session.put("superProfitComm", bean.getSuperProfitComm());
				session.put("payedTillDate", bean.getPayedTillDate());
				session.put("profitCommAdj", bean.getProfitCommAdj());
				session.put("ExchRatePrem", bean.getExchRatePrem());
			}
			byte[] byteArray = value.getBytes();
			inputStream = new ByteArrayInputStream(byteArray);
			return forward;
		}*/

		public String getSlideView(){
			bean.setBranchCode(branchCode);
			bean.setProductId(productId);
			bean.setSlideCommission(SERVICE.SlideCommission(bean, countryId));
			//SERVICE.PremiumContractDetails(bean, countryId);
			if("profitView".equalsIgnoreCase(bean.getMode())){
				SERVICE.PremiumGetFieldValues(bean);
			}else if("lossView".equalsIgnoreCase(bean.getMode())){
				SERVICE.GetLossFieldValues(bean);
			}
			else{
				SERVICE.GetFieldValues(bean);
			}
			SERVICE.addFieldValue(bean);
			return "dropdownajax";
		}
		public void getAccountperiodDate(FaculPremiumBean bean){
			if(StringUtils.isNotBlank(bean.getAccount_Period()) && StringUtils.isNotBlank(bean.getAccount_Period_year())){
				String date="";
				int months= 0;
				/*if("1".equalsIgnoreCase(bean.getAccount_Period())){
					date="31/03/"+bean.getAccount_Period_year();
				}else if("2".equalsIgnoreCase(bean.getAccount_Period())){
					date="30/06/"+bean.getAccount_Period_year();
				}
				else if("3".equalsIgnoreCase(bean.getAccount_Period())){
					date="30/09/"+bean.getAccount_Period_year();
				}
				else if("4".equalsIgnoreCase(bean.getAccount_Period())){
					date="31/12/"+bean.getAccount_Period_year();
				}*/
				if("5".equalsIgnoreCase(bean.getAccount_Period())){
					date=bean.getTransaction();
				}
				else if("6".equalsIgnoreCase(bean.getAccount_Period()) || "7".equalsIgnoreCase(bean.getAccount_Period())||"8".equalsIgnoreCase(bean.getAccount_Period())||"9".equalsIgnoreCase(bean.getAccount_Period())){
					date=bean.getStatementDate();
				}
				else if("51".equalsIgnoreCase(bean.getAccount_Period()) || "52".equalsIgnoreCase(bean.getAccount_Period()) ||"53".equalsIgnoreCase(bean.getAccount_Period()) || "54".equalsIgnoreCase(bean.getAccount_Period()) ||"55".equalsIgnoreCase(bean.getAccount_Period()) || "56".equalsIgnoreCase(bean.getAccount_Period()) ||  "57".equalsIgnoreCase(bean.getAccount_Period())){
					final Calendar c = Calendar.getInstance();
					final Map uType=new HashMap();
					Calendar now = Calendar.getInstance();  
					 final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					 int year1 =0;
			            try {
							c.setTime(df.parse(bean.getInsDate()));
							 year1=c.get(Calendar.YEAR);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						int num = Integer.parseInt(bean.getAccount_Period_year()) - year1;
					if("51".equalsIgnoreCase(bean.getAccount_Period())){
							 months=(3*1) + (12*num);
						}  
					if("52".equalsIgnoreCase(bean.getAccount_Period())){
						 months=(3*2) + (12*num);
					}
					if( "53".equalsIgnoreCase(bean.getAccount_Period())){
						 months=(3*3) + (12*num);
					}	
					if("54".equalsIgnoreCase(bean.getAccount_Period())){
							 months=(3*4) + (12*num);
						}  
					if("55".equalsIgnoreCase(bean.getAccount_Period())){
						 months=(6*1) + (12*num);
					}
					if( "56".equalsIgnoreCase(bean.getAccount_Period())){
						 months=(6*2) + (12*num);
					}
					if( "57".equalsIgnoreCase(bean.getAccount_Period())){
						 months=(12*1) + (12*num);
					}
					try {
						c.setTime(df.parse(bean.getInsDate()));
				        c.add(Calendar.MONTH, months);
				        c.add(Calendar.DATE, -1);
				        Date date1 =  c.getTime();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				        date = sdf.format(date1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				
				bean.setAccountPeriodDate(date);
			}
			
		}
		public String OBSAjaxList(){
			bean.setBranchCode(branchCode);
			bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			//getAccountperiodDate(bean);
			 bean.setOSBList(SERVICE.getOSBList(bean));
			return "dropdownajax";
		}
		public String GetSection(){
			bean.setLoginId(userId);
			bean.setBranchCode(branchCode);
			validateSection();
			if(!hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getSectionType()) && "1".equals(bean.getSectionType()) && StringUtils.isNotBlank(bean.getSectionName())){
				SERVICE.insertSection(bean);
			}
			bean.setSectionList(dropDownController.getSectionList(bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode()));
			}
			else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setSectionList(dropDownController.getSectionList(bean.getContNo(),bean.getDepartmentId(),bean.getBranchCode()));
				bean.setDropDown("error");
			}
			return "dropdownajax";
		}
		/*public void getLossDetails(){
		if(session.get("lossPremiumOc")!=null){
			bean.setLossPremiumOC((List<String>) session.get("lossPremiumOc"));
			bean.setLossClaimPaidOC((List<String>) session.get("lossClaimPaidOc"));
			bean.setLossClaimOutStandingOC((List<String>) session.get("lossClaimOutStandingOC"));
			bean.setLossClaimRatioOC((List<String>) session.get("lossClaimRatioOC"));
			bean.setClaimPaidRatioOC((List<String>) session.get("ClaimPaidRatioOC"));
			bean.setLossPartOC((List<String>) session.get("LossPartOC"));
			bean.setLossPartRefTillDate((List<String>) session.get("LossPartRefTillDate"));
			bean.setLossPartRefAdjOC((List<String>) session.get("LossPartRefAdjOC"));
			bean.setExchRatePrem((List<String>) session.get("ExchRatePrem"));
			bean.setPreCurrencylist((List<String>) session.get("PreCurrencylist"));
			
		}
		}*/
		private void validateSection() {
		if(StringUtils.isNotBlank(bean.getSectionType()) && "1".equals(bean.getSectionType())){
			if(SERVICE.getSectionCount(bean)>0){
				addActionError("This section already exists.  Please choose from the existing list.");
			}
		}
			
		}
		
		public String validationAlert(){
			String forward="editPremium";
			String editMode="";
			if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(productId);	
			}else{
				session.put("mfrid",bean.getProductId());
				productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
			} 
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
			if(StringUtils.isNotBlank(bean.getProposal_No())){
			 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
			}
			if("N".equalsIgnoreCase(editMode) ){
				validationpopUps();
			}else{
    				bean.setMultiuserError("error");
			}
			
			 bean.setPremiumaccperiod(SERVICE.getConstantPeriodDropDown("49",bean.getContNo(),bean));
			 SERVICE.ContractDetails(bean,countryId);
			 bean.setDepartmentName(new DropDownControllor().getDepartmentName((String)session.get("BRANCH_CODE"), (String)session.get("mfrid"), bean.getDepartmentId()));
			 bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
			 bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,session.get("mfrid").toString(),bean.getConsubProfitId()));
			 bean.setStatus(bean.getStatus());
				bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				if((StringUtils.isBlank(bean.getAlertShow())||!"Y".equalsIgnoreCase(bean.getAlertShow())) &&"N".equalsIgnoreCase(editMode)){
					insertPremium();
					if (!hasActionErrors()) 
					 {
					 forward =  "PremiumSucuss";
					 }else{
						 logger.info("##########Validation Message Start###########");
							Iterator<String> error = getActionErrors().iterator();
							while(error.hasNext()){
								logger.info(error.next());
							}
						 logger.info("##########Validation Message End###########");
					 }
				}
			 return forward;
		}
		private void validationpopUps() {
			bean.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getAccountPeriodDate())){
				bean.setAlertShow("");
				bean.setAllAlert("");
				bean.setPcAlert("");
				bean.setScAlert("");
				bean.setLcAlert("");
				if("51".equalsIgnoreCase(bean.getAccount_Period()) || "52".equalsIgnoreCase(bean.getAccount_Period()) ||"53".equalsIgnoreCase(bean.getAccount_Period()) || "54".equalsIgnoreCase(bean.getAccount_Period()) ||"55".equalsIgnoreCase(bean.getAccount_Period()) || "56".equalsIgnoreCase(bean.getAccount_Period()) ||  "57".equalsIgnoreCase(bean.getAccount_Period())){
					String val = dropDownController.getStatementDate(bean.getAccountPeriodDate(),bean.getProposal_No(),bean.getContNo(),bean.getBranchCode(),"date",bean.getDepartmentId());
					if( StringUtils.isBlank(val) || val.equalsIgnoreCase("N")){
					bean.setAlertShow("Y");
					bean.setAllAlert("Y");
					}else if(!val.equalsIgnoreCase("0")){
						val = dropDownController.getStatementDate(val,bean.getProposal_No(),bean.getContNo(),bean.getBranchCode(),"compare",bean.getDepartmentId());
						if(val.equalsIgnoreCase("0")){
							bean.setAlertShow("Y");
							bean.setAllAlert("Y");
						}
					}
				}
				else if("7".equalsIgnoreCase(bean.getAccount_Period())){
					String val = dropDownController.getStatementDate(bean.getAccountPeriodDate(),bean.getProposal_No(),bean.getContNo(),bean.getBranchCode(),"PC",bean.getDepartmentId());
					if(val!="" && val.equalsIgnoreCase("Y") && Double.parseDouble(bean.getProfit_Commission().replaceAll(",", ""))<=0){
						bean.setAlertShow("Y");
						bean.setPcAlert("Y");
					}
				}else if("8".equalsIgnoreCase(bean.getAccount_Period())){
					String val = dropDownController.getStatementDate(bean.getAccountPeriodDate(),bean.getProposal_No(),bean.getContNo(),bean.getBranchCode(),"SC",bean.getDepartmentId());
					if(val!="" && val.equalsIgnoreCase("Y") && Double.parseDouble(bean.getSlideScaleCom().replaceAll(",", ""))<=0){
						bean.setAlertShow("Y");
						bean.setScAlert("Y");
					}
				}else if("9".equalsIgnoreCase(bean.getAccount_Period())){
					String val = dropDownController.getStatementDate(bean.getAccountPeriodDate(),bean.getProposal_No(),bean.getContNo(),bean.getBranchCode(),"LC",bean.getDepartmentId());
					if(val!="" && val.equalsIgnoreCase("Y") && Double.parseDouble(bean.getLossParticipation().replaceAll(",", ""))<=0){
						bean.setAlertShow("Y");
						bean.setLcAlert("Y");
					}
				}
			}
		}
		}



