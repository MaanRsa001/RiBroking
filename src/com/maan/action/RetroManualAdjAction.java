package com.maan.action;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.ProportionalPremiumService;
import com.maan.service.RetroManualAdjService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RetroManualAdjAction extends ActionSupport implements ModelDriven<FaculPremiumBean>{
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String countryId=(String) session.get("countryId")==null?"":session.get("countryId").toString();
	private String branchCode=(String) session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	FaculPremiumBean bean =new FaculPremiumBean();
	FaculitivesBean bean1 =new FaculitivesBean();
	private transient final RetroManualAdjService service=new RetroManualAdjService();
	private transient final ProportionalPremiumService SERVICE=new ProportionalPremiumService();
	Logger logger = LogUtil.getLogger(this.getClass());
	//ProportionalPremiumService SERVICE=new ProportionalPremiumService();
	DropDownControllor dropDownController=new DropDownControllor();
	private Map yearList;
	public Map getYearList(){
		Map<String, String> treeMap =new TreeMap<String, String>();
        final Calendar c = Calendar.getInstance();
		final Map uType=new HashMap();
		Calendar now = Calendar.getInstance();  
		 final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		 try {
            c.setTime(df.parse("01/01/2011"));
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
	public String retroPremList(){
		bean.setBranchCode(branchCode);
		bean1.setBranchCode(branchCode);
		bean.setProductId("4");
		new DropDownControllor().getOpenPeriod(bean1);
		bean.setRetroManualAdjList(service.getRetroManualAdjlist(bean));
		return "retroPremList";
	}
	
	public String retroInit(){
		String forward="retroManualAdj";
		bean.setBranchCode(branchCode);
		String editMode ="N";
		int count=dropDownController.EditModeStatusCount(bean.getContNo(),bean.getBranchCode());
		if(StringUtils.isNotBlank(bean.getProposal_No())){
		 editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
		}
	if("N".equalsIgnoreCase(editMode) && count==0){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.PremiumEdit(bean,countryId);	
		}else if("view".equalsIgnoreCase(bean.getMode())){
			service.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
			forward = "retroManualAdjView";
		}
		bean.setDepartmentList(dropDownController.getDepartmentDropDown(branchCode,"4","Y","","","","",""));
		bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getPredepartment()==null?"":bean.getPredepartment(),branchCode,"4"));
		bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		bean.setRetroContractList(new DropDownControllor().getRetroContractDropDown(branchCode, bean.getUwYear()));
		service.getRetroDetails(bean);
	}
	else{
		bean.setMultiuserError("error");
		retroPremList();
		forward = "retroPremList";
	}
		return forward;
	}
	public String ajaxValue(){
		Validation val = new Validation();
		bean.setBranchCode(branchCode);
		if("presubclass".equals(bean.getDropDown())){
			bean.setSubProfit_centerlist(dropDownController.getSubProfitCentreDropDown(bean.getPredepartment(),branchCode,"4"));
		}
		else if("exchRate".equals(bean.getDropDown())){
			bean.setBranchCode(branchCode);
			if(StringUtils.isNotBlank(bean.getTransaction()) && !val.checkDate(bean.getTransaction()).equalsIgnoreCase("INVALID")){
			bean.setExchRate(dropDownController.GetExchangeRate(bean.getCurrId(),bean.getTransaction(),countryId,bean.getBranchCode()));
			}
		}else if("contractList".equals(bean.getDropDown())){
			bean.setRetroContractList(new DropDownControllor().getRetroContractDropDown(branchCode, bean.getUwYear()));
			
		}else if("retroDetails".equals(bean.getDropDown())){
			service.getRetroDetails(bean);
			bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			if(StringUtils.isNotBlank(bean.getTransaction()) && !val.checkDate(bean.getTransaction()).equalsIgnoreCase("INVALID")){
				bean.setExchRate(dropDownController.GetExchangeRate(bean.getCurrId(),bean.getTransaction(),countryId,bean.getBranchCode()));
			}
		}
		return "dropdownajax";
	}
	
	public String insertPremium()
	{
		bean.setLoginId(userId);
			bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			//bean.setDepartmentId((String) session.get("DepartmentId")==null?"0":(String) session.get("DepartmentId"));
	        bean.setBranchCode(branchCode);
	        bean1.setBranchCode(branchCode);
	        bean.setProductId("4");
	        if(StringUtils.isNotBlank(bean.getTransactionNo())){
	        bean.setPreamendmentDate(new DropDownControllor().getpremiumPreamendDateRetro(bean));
	        if(StringUtils.isNotBlank(bean.getTransaction()) && StringUtils.isNotBlank(bean.getPreamendmentDate())){
			bean.setMaxDate(Validation.getMaxDateValidate(bean.getTransaction(), bean.getPreamendmentDate()));
	        }
	        }
	        
	        bean.setProposal_No(dropDownController.getProposalNo("4",bean.getContNo(),bean.getDepartmentId(),bean.getLayerNo()));
	        String editMode = dropDownController.EditModeStatus(bean.getProposal_No(),"0");
	        int count=dropDownController.EditModeStatusCount(bean.getContNo(),bean.getBranchCode());
			new DropDownControllor().getOpenPeriod(bean1);
			if("N".equalsIgnoreCase(editMode) && count==0){
			validateProportionPremium();
			}
    		 if (!hasActionErrors() && "N".equalsIgnoreCase(editMode) &&  count==0) 
			 {
    			
    			 	if("new".equalsIgnoreCase(bean.getMode())) {
					     service.InsertPremium(bean);
					     service.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
					     //SERVICE.InsertCashLossCredit(bean);
		    		 }
		    		 else if("edit".equalsIgnoreCase(bean.getMode()))
		    		 {
			    		// SERVICE.UpdatePremium(bean);
		    			 service.GetPremiumDetails(bean,bean.getTransactionNo(),countryId);
		    		 }
    			 	bean.setDepartmentList(dropDownController.getDepartmentDropDown(branchCode,"4","Y","","","","",""));
					bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,"4",bean.getSubProfitId()));
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					bean.setRetroContractList(new DropDownControllor().getRetroContractDropDown(branchCode, bean.getUwYear()));
					return "retroManualAdjView";
			 }
			 else
			 {	
				 logger.info("##########Validation Message Start###########");
					Iterator<String> error = getActionErrors().iterator();
					while(error.hasNext()){
						logger.info(error.next());
					}
				 logger.info("##########Validation Message End###########");
				 if(!"N".equalsIgnoreCase(editMode) || count!=0){
					 bean.setMultiuserError("error");
				 }
				 	bean.setDepartmentList(dropDownController.getDepartmentDropDown(branchCode,"4","Y","","","","",""));
					bean.setSubProfit_centerlist(new DropDownControllor().getSubProfitCentreMultiDropDown(bean.getPredepartment(),branchCode,"4",bean.getSubProfitId()));
					bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
					bean.setRetroContractList(new DropDownControllor().getRetroContractDropDown(branchCode, bean.getUwYear()));
					return "retroManualAdj";			
			 }    		
			}
	
	
	private void validateProportionPremium() {
		final Validation val=new Validation();
		boolean flag=false;
		boolean cashLossCrFlag=false;
			/* String deptId = SERVICE.getDepartmentNo(bean);
			 if(!deptId.equalsIgnoreCase(bean.getDepartmentId())){
				 //addActionError(getText("dept.mismatch"));
			 }*/
			 		 bean.setOtherCost((bean.getOtherCost()).replaceAll(",",""));
			 		  
				 	 if(val.isNull(bean.getCurrency()).equalsIgnoreCase("0"))
					 {						 
				 		addActionError(getText("errors.currency.requireds"));							
					 }
					 if(val.isNull(bean.getExchRate()).equalsIgnoreCase(""))
					 {
						 addActionError(getText("errors.exchRate.required"));
					 }
					boolean dateflag=true; 
					boolean statDate=true;
					
					
				
					if(StringUtils.isNotBlank(bean.getTransactionNo())){
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
					}
						if(StringUtils.isNotBlank(bean.getMaxDate()) && !(null ==bean.getMaxDate())){
						 if(Validation.ValidateTwo(bean.getMaxDate(),bean.getAmendmentDate()).equalsIgnoreCase("invalid"))
						{
							addActionError(getText("errors.premium.amendDate"));
							
						}
							}
					if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAmendmentDate()).equalsIgnoreCase("")){
						if(new DropDownControllor().Validatethree(branchCode, bean.getAmendmentDate())==0){
							addActionError(getText("errors.open.period.date.trans.amend",new String[] {bean1.getOpenPeriodDate()}));
						}
					}
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
					}if(StringUtils.isBlank(bean.getBusinessType())){
						addActionError(getText("Please Enter Business Type"));
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
			 	 
				
				 if(StringUtils.isBlank(bean.getCashLoss_Credit())){
					 addActionError(getText("errors.CashLossCredit.reqired"));
				 }else {
					 bean.setCashLoss_Credit(bean.getCashLoss_Credit().replaceAll(",", ""));
					 if(val.numbervalid(bean.getCashLoss_Credit()).equalsIgnoreCase("INVALID"))
						 {
							 addActionError(getText("errors.CashLossCredit.Error")); 
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
				 
				 
					bean.setTotalCredit((bean.getTotalCredit()).replaceAll(",",""));
					bean.setTotalDebit((bean.getTotalDebit()).replaceAll(",",""));
					if(flag==false)
					{
						addActionError(getText("errors.currency.select"));	 
					}	
		
	}
	
	public FaculPremiumBean getModel() {
		return bean;
	}
}
