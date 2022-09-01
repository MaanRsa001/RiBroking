package com.maan.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;	
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.XolPremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class XolPremiumAction extends ActionSupport implements ModelDriven<FaculPremiumBean>
			{
			private static final long serialVersionUID = 1L;
			Logger logger = LogUtil.getLogger(this.getClass());
			final HttpServletRequest request = ServletActionContext.getRequest();
			final HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			FaculPremiumBean bean=new FaculPremiumBean();
			FaculitivesBean bean1=new FaculitivesBean();
			private transient final XolPremiumService SERVICE=new XolPremiumService();
			private String productId=session.get("mfrid")==null?"":(String) session.get("mfrid");
			private String userType=session.get("userType")==null?"":(String) session.get("userType");
			private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
			private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
			private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
			DropDownControllor dropDownController=new DropDownControllor();
			private String userName=session.get("UserName")==null?"":(String) session.get("UserName");
			private String userId=session.get("UserId")==null?"":(String) session.get("UserId");
			public FaculPremiumBean getModel() {
				return bean;
			}	
			private List<FaculPremiumBean> preList;
			private List<FaculPremiumBean> allocatedList;
			private List<FaculPremiumBean> brokerAndCedingName;
			private String dropDown;
			private String menuId;		
			
			 public List<Map<String,Object>> getGNPIList(){
					return dropDownController.getGNPIList(bean.getContNo(),bean.getLayerno(),"GNPI",productId,bean.getMode());
				}
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
			public String premiumList()
				{
				String forward="premiumList";
				bean.setLoginId(userId);
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
				else{
					 session.put("mfrid",bean.getProductId());	
					 productId=session.get("mfrid")==null?"":(String) session.get("mfrid");
				}
				if(StringUtils.isNotBlank(bean.getDepartmentId())){
					session.put("DepartmentId",bean.getDepartmentId());
				}else{
					bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
				}
					bean.setBranchCode(branchCode);
					bean1.setBranchCode(branchCode);
					new DropDownControllor().getOpenPeriod(bean1);
					bean.setOpstartDate(bean1.getOpstartDate());
					bean.setOpendDate(bean1.getOpendDate());
					String editMode = "";
					bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerno()));
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
								forward= "portfoliList";
								}
							}else{
					preList=SERVICE.getPremiumedList(bean,"Main"); 
					if("3".equalsIgnoreCase(bean.getProductId())){
						bean.setPreTempList(SERVICE.getPremiumedList(bean,"Temp"));
					}
			        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
			        if(bean.getMode() !=null && bean.getMode().equalsIgnoreCase("premiumMaster")){
			        	bean.setPremiumMasterMode(bean.getPremiumDisplay());
			        }
			        if(preList.isEmpty() && bean.getPreTempList().isEmpty()  && userRights.indexOf("PN")!=-1)
			        {	
			        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));	
			        	bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
			        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
			 	        bean.setContractPremium(SERVICE.GetContractPremium(bean));
			        	SERVICE.ContractDetails(bean,countryId);
			        	bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),bean.getLayerno(),sourceId,bean.getProductId()));	
			        	bean.setCurrency(bean.getBaseCurrencyId());
			        	bean.setEnteringMode("2");
			        	bean.setMenuStatus("N");
				        bean.setMode("add");
				        bean.setPredepartment(bean.getDepartmentId());
				        forward="editPremium";
			        }
			       /* else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
			        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			        	bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
			        	bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
			 	        bean.setContractPremium(SERVICE.GetContractPremium(bean));
			        	SERVICE.ContractDetails(bean,countryId);
			        	bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),bean.getLayerno()));	
			        	bean.setCurrency(bean.getBaseCurrencyId());
			        	bean.setEnteringMode("2");
			        	bean.setMenuStatus("N");
				        bean.setMode("add");
				        bean.setPredepartment(bean.getDepartmentId());
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
				}
				else{
					 session.put("mfrid",bean.getProductId());	
					 productId=session.get("mfrid")==null?"":(String) session.get("mfrid");
				}
				
				bean.setLoginId(userId);
				if(StringUtils.isNotBlank(bean.getDepartmentId())){
					session.put("DepartmentId",bean.getDepartmentId());
				}else{
					bean.setDepartmentId(SERVICE.getDepartmentId(bean));
					session.put("DepartmentId",bean.getDepartmentId());
				}
					bean.setPredepartment(bean.getDepartmentId());
					bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerno()));
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
					setSessionValuNull();
			    	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			    	bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			    	bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
		        	bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("7"));
		        	/*if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
						bean.setProductId(productId);	
					}
		        	else{
						 session.put("mfrid",bean.getProductId());	
					}*/
			        bean.setBranchCode(branchCode);
			        bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
			        bean.setContractPremium(SERVICE.GetContractPremium(bean));
			        bean.setContractDeptId(dropDownController.getContractLayerNo(bean.getContNo(),bean.getLayerno(),branchCode,bean.getProductId(),bean.getProposal_No()));
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
				    bean.setMenuStatus("N");
				    bean.setPredepartment(bean.getDepartmentId());
				    bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),bean.getLayerno(),sourceId,bean.getProductId()));
			        if("edit".equals(bean.getMode())|| "transEdit".equalsIgnoreCase(bean.getMode()))
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
					return forward;
			}
			@SuppressWarnings("static-access")
			public String insertPremium()
			{		bean.setLoginId(userId);
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
						bean.setProductId(productId);	
					}
					else{
						 session.put("mfrid",bean.getProductId());	
						 productId=session.get("mfrid")==null?"":(String) session.get("mfrid");
					}
					bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
			        bean.setBranchCode(branchCode);
			        bean1.setBranchCode(branchCode);
					bean.setUserType(userType);	
					String editMode="";
					bean.setProposal_No(dropDownController.getProposalNo(productId,bean.getContNo(),bean.getDepartmentId(),bean.getLayerno()));
					if(StringUtils.isNotBlank(bean.getProposal_No())){
					 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
					}
					bean.setPreamendmentDate(new DropDownControllor().getpremiumPreamendDate(bean));
					//if(StringUtils.isNotBlank( bean.getPreamendmentDate())){
					bean.setMaxDate(Validation.getMaxDateValidate(bean.getTransaction(), bean.getPreamendmentDate()));
					//}
					new DropDownControllor().getOpenPeriod(bean1);
					if("N".equalsIgnoreCase(editMode)){
			        validateXolPremium();
					}
		    		 if (!hasActionErrors() && "N".equalsIgnoreCase(editMode)) 
					 {
		    			 GetBonusDeatail();
		    			 if("RP".equalsIgnoreCase(bean.getAccount_Period()) || "AP".equalsIgnoreCase(bean.getAccount_Period()) || "RTP".equalsIgnoreCase(bean.getAccount_Period()) || "RVP".equalsIgnoreCase(bean.getAccount_Period()))
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
							 SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
							 setSessionValuNull();
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
						 bean.setPredepartmentList(new DropDownControllor().getPreDepartmentDropDown(branchCode,productId,"Y",bean));
	    		         bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("7"));
						 SERVICE.ContractDetails(bean,countryId);
						 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),bean.getLayerno(),sourceId,bean.getProductId()));
						 bean.setStatus(bean.getStatus());				
					 }    		
		    		 if(!"N".equalsIgnoreCase(editMode)){
		    				bean.setMultiuserError("error");
		    		 }
				return "editPremium";
			}
			public String instalmentPremium()
			{
				bean.setLoginId(userId);
			bean.setMd_premium(SERVICE.GetInstalmentAmount(bean.getContNo(),bean.getLayerno(),bean.getInstalmentdate()));
			return "dropdownajax";				
			}
			public String premiumView()
			{
				if(StringUtils.isBlank(bean.getProductId())||bean.getProductId().equalsIgnoreCase("")){
					bean.setProductId(productId);	
				}
			        bean.setBranchCode(branchCode);	
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					SERVICE.ContractDetails(bean,countryId);
					SERVICE.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
					brokerAndCedingName = SERVICE.getBrokerAndCedingName(bean);
					if("3".equalsIgnoreCase(bean.getProductId())){
					allocatedList=SERVICE.getAllocatedList(bean);
					}
					bean.setCurrencyList(SERVICE.currencyList(bean));
				return "PremiumSucuss";
			}
			
			public String deletePremium(){
				{
					bean.setLoginId(userId);
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
						if("3".equalsIgnoreCase(bean.getProductId())){
							bean.setPreTempList(SERVICE.getPremiumedList(bean,"Temp"));
						}
				        String userRights=session.get("MenuRights")==null?"":session.get("MenuRights").toString();
				        if(preList.isEmpty() && bean.getPreTempList().isEmpty() && userRights.indexOf("PN")!=-1)
				        {	
				        	 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
				        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
				 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
				        	 SERVICE.ContractDetails(bean,countryId); 
				        	 bean.setMdList(SERVICE.MDinstallmentDates(bean.getContNo(),"0",sourceId,bean.getProductId()));		 	    
					 	     bean.setCurrency(bean.getBaseCurrencyId());
					         bean.setEnteringMode("2");
					         bean.setMenuStatus("N");
					         bean.setMode("add");
					         bean.setTransactionNo("");
				        	 return "editPremium";
				        }
				       /* else if(preList.isEmpty() && bean.getMode().equalsIgnoreCase("premiumMaster")){
				        	bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
				        	 bean.setPreviousPremium(SERVICE.GetPreviousPremium(bean));
				 	         bean.setContractPremium(SERVICE.GetContractPremium(bean));
				        	 SERVICE.ContractDetails(bean,countryId); 
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
			
			private void validateXolPremium()
			{
				final Validation val=new Validation();
				boolean flag=false;
				boolean cashLossCrFlag=false;
				List retroList=SERVICE.getSPRetroList(bean.getContNo(), bean.getProductId(), bean.getLayerno());
					 if(retroList!=null&&retroList.size()>0)
					 {
						 Map map=(Map) retroList.get(0);				 
						 bean.setProductId(bean.getProductId());						
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
							if("3".equalsIgnoreCase(bean.getProductId())){
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
							}/*else if(Validation.ValidateTwo(bean.getInsDate(),bean.getInception_Date()).equalsIgnoreCase("invalid"))
							{
								addActionError(getText("errors.premium.statRecDate"));
								
							}*/else if(Validation.ValidateTwo(bean.getStatementDate(),bean.getInception_Date()).equalsIgnoreCase("invalid"))
							{
								addActionError(getText("errors.premium.statRecDate"));
								
							}
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
							}else if("3".equalsIgnoreCase(bean.getProductId())&&dateflag && Validation.ValidateTwo(bean.getInception_Date(),bean.getTransaction()).equalsIgnoreCase("invalid"))
							{
								addActionError(getText("errors.premium.transaction"));
							}else if("5".equalsIgnoreCase(bean.getProductId())&&dateflag && Validation.ValidateTwo(bean.getStatementDate(),bean.getTransaction()).equalsIgnoreCase("invalid"))
							{
								addActionError(getText("errors.premium.transaction.statement"));
							}
							else if(dateflag && Validation.ValidateTwo(bean.getAcceptenceDate(),bean.getTransaction()).equalsIgnoreCase("invalid"))
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

							 if(val.isSelect(bean.getAccount_Period()).equalsIgnoreCase(""))
							 {
								 addActionError(getText("errors.account_Period.required"));
							 }else //if(StringUtils.isBlank(bean.getTransactionNo()))
							 {
								 String[] ins=bean.getAccount_Period().split("_");
								 if((!"AP".equalsIgnoreCase(ins[0]))&&(!"RP".equalsIgnoreCase(ins[0])) &&(!"RTP".equalsIgnoreCase(ins[0]))&& (!"RVP".equalsIgnoreCase(ins[0]))){
									 /*List list=SERVICE.getMandDInstallments(bean.getContNo(),bean.getLayerno(),bean.getProductId());
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
													 addActionError(getText("errors.MandD.invalid",new String[]{String.valueOf(j)}));
												 }
												 j++;
											 }
										 }else
										 {
											 for(int i=1;i<num;i++){
												 addActionError(getText("errors.MandD.invalid",new String[]{String.valueOf(i)}));
											 }
										 }
									 }*/
									 if(!bean.getCurrency().equals(bean.getBaseCurrencyId())){
										 addActionError(getText("errors.MandD.currencyInvalid",new String[]{bean.getBaseCurrencyName()}));
									 }
									 if(!"2".equals(bean.getEnteringMode())){
										 addActionError(getText("errors.MandD.enteringModeInvalid",new String[]{bean.getShareSigned()}));
									 }
									 }								 	
								 	else if("RP".equalsIgnoreCase(ins[0]))
									 {
								 		if(bean.getRecuirement_premium().equals("0")|| bean.getRecuirement_premium().equalsIgnoreCase(""))
										 {
											 addActionError(getText("errors.Recuirement_premium.Zero"));
										 }
								 		else if( ! val.isNull(bean.getRecuirement_premium()).equalsIgnoreCase("") )
										 {
											 flag=true;
											 bean.setRecuirement_premium((bean.getRecuirement_premium()).replaceAll(",",""));
											 if(val.numbervalid(bean.getRecuirement_premium()).equalsIgnoreCase("INVALID"))
											 {
												 addActionError(getText("errors.Recuirement_premium.check"));  
											 }else{
												 String rpval=SERVICE.getRPPremiumOC(bean.getContNo(),bean.getLayerno(),bean.getProductId());
												 if(Double.parseDouble(bean.getRecuirement_premium())<0){
												 if(Double.parseDouble(rpval)<(Double.parseDouble(bean.getRecuirement_premium())*(-1))){
													 addActionError(getText("errors.rppreimium.error"));
												 }
											 }
										 	 }
										 }
									 }
							 }
							if(StringUtils.isNotBlank(bean.getAccount_Period()) && "AP".equalsIgnoreCase(bean.getAccount_Period()) && "RI02".equalsIgnoreCase(sourceId))	{
								if(StringUtils.isBlank(bean.getGnpiDate())){
									addActionError(getText("error.gnpi.value"));
								}
							}
							 if("3".equalsIgnoreCase(bean.getProductId())){
							 if(!val.isNull(bean.getCommission()).equalsIgnoreCase(""))
							 {
								 flag=true;
							 	 if(val.numbervalid(bean.getCommission()).equalsIgnoreCase("INVALID"))
								 {
							 		addActionError(getText("errors.commission.Check"));
								 }				  
							 }		
							 
							 if(!val.isNull(bean.getBrokerage()).equalsIgnoreCase(""))
							 {
								 flag=true;
								 bean.setBrokerage((bean.getBrokerage()).replaceAll(",",""));
							 	 if(val.numbervalid(bean.getBrokerage().toString()).equalsIgnoreCase("INVALID"))
								 {
							 		addActionError(getText("errors.brokerage.check"));
								 }
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
							 if("3".equalsIgnoreCase(bean.getProductId())){
						        if (!val.isNull(bean.getWithHoldingTaxOC()).equalsIgnoreCase("")) {
														 flag=true;
						            bean.setWithHoldingTaxOC((bean.getWithHoldingTaxOC()).replaceAll(",", ""));
						            if (val.numbervalid(bean.getWithHoldingTaxOC()).equalsIgnoreCase("INVALID")) {
						                addActionError(getText("errors.whtax.check"));
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
							 }
						        if (!val.isNull(bean.getMd_premium()).equalsIgnoreCase("")) {
						            flag = true;
								 bean.setMd_premium((bean.getMd_premium()).replaceAll(",",""));
								 if(val.numbervalid(bean.getMd_premium()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError(getText("errors.mdpremium.check"));
								 }
							 }
						
							 if(! val.isNull(bean.getAdjustment_premium()).equalsIgnoreCase(""))
							 {
								 flag=true;
								 bean.setAdjustment_premium((bean.getAdjustment_premium()).replaceAll(",",""));
								 if(val.numbervalid(bean.getAdjustment_premium()).equalsIgnoreCase("INVALID"))
								 {
									 addActionError(getText("errors.adjustpremium.check"));
								 }else if(! val.isNull(bean.getAdjustment_premium_temp()).equalsIgnoreCase("")){
									 if(Double.parseDouble(bean.getAdjustment_premium())>Double.parseDouble(bean.getAdjustment_premium_temp()))
										 {
											 //addActionError(getText("errors.adjustpremium.invalied"));					 
										 }		
								 }
							}
							 if (StringUtils.isNotBlank(bean.getMd_premium()) && StringUtils.isNotBlank(bean.getEPI_our_share_view())) {
									bean.setMd_premium((bean.getMd_premium()).replaceAll(",", ""));
									bean.setAdjustment_premium((bean.getAdjustment_premium()).replaceAll(",", ""));
									bean.setEPI_our_share_view((bean.getEPI_our_share_view()).replaceAll(",", ""));
									if (!val.isValidNo(bean.getMd_premium().trim())	.equalsIgnoreCase("INVALID")&& !val.isValidNo(bean.getAdjustment_premium().trim()).equalsIgnoreCase("INVALID") && !val.isValidNo(bean.getEPI_our_share_view().trim()).equalsIgnoreCase("INVALID")) {
										if(StringUtils.isBlank(bean.getAdjustment_premium())){
											bean.setAdjustment_premium("0");
										}
							 final String Mdpremium = val.isNull(bean.getMd_premium());
							 final String AdjPremium = val.isNull(bean.getAdjustment_premium());
							 final Double sumMdadjust=Double.parseDouble(Mdpremium)+Double.parseDouble(AdjPremium);
							 
							 /*if(sumMdadjust>Double.parseDouble(bean.getEPI_our_share_view())){
								 addActionError(getText("errors.adjustpremium.epi.invalied"));	
							 }*/
									}
							 }
							bean.setTotalCredit((bean.getTotalCredit()).replaceAll(",",""));
							bean.setTotalDebit((bean.getTotalDebit()).replaceAll(",",""));
							if(flag==false)
							{
								addActionError(getText("errors.currency.select"));	 
							}	
							if("transEdit".equalsIgnoreCase(bean.getMode())){
								if(StringUtils.isBlank(bean.getTransDropDownVal()) && "Yes".equalsIgnoreCase(bean.getChooseTransaction())){
									addActionError(getText("resersel.trans"));
								}
							}
				 		}
			private String dateFormat(String name)
			{
				String arr[]=name.split("/");
				return arr[1]+"/"+arr[0]+"/"+arr[2];
			}
			public void setBrokerAndCedingName(List<FaculPremiumBean> brokerAndCedingName) {
				this.brokerAndCedingName = brokerAndCedingName;
			}
			public List<FaculPremiumBean> getBrokerAndCedingName() {
				return brokerAndCedingName;
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
			public String getAdjPrem(){
				bean.setBranchCode(branchCode);
				bean.setAdjustment_premium(SERVICE.GetAdjPremium(bean));
				return "dropdownajax";
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
}






