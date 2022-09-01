package com.maan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.action.admin.AdminAction;
import com.maan.bean.AdjustmentBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.AdjustmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdjustmentAction extends ActionSupport implements ModelDriven<AdjustmentBean>{
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId")==null?"":session.get("countryId").toString();
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private static final Logger LOGGER = LogUtil.getLogger(AdminAction.class);
	AdjustmentService service=new AdjustmentService();
	AdjustmentBean bean=new AdjustmentBean();
	FaculitivesBean bean1=new FaculitivesBean();
	
	//public static Map<String,List<AdjustmentBean>> receiveAdjustAmountMap = new HashMap<String, List<AdjustmentBean>>();
	Map<String,String> receiveAdjustAmountMap = new HashMap<String, String>();
	public String adjustmentHome(){
		bean.setMode("init");
		bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
		bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
		bean.setCurrencyList(new DropDownControllor().getCurrencyMasterDropDown(branchCode,countryId));
		return "adjustmentHome";
	}
	public String init() {
		try {
			bean.setBranchCode(branchCode);
			bean.setMode("adjustList");
			//new DropDownControllor().getOpenPeriod(bean1);
		
			bean.setAllocatedAdjustmentList(service.getAdjustmentDoneList(bean));
				
		}
		catch(Exception e) {
			//logger.debug("Exception @ { " + e + " } ");
		}
		return "adjustmentList";
	}
	public String adjustmentView(){
		String result="";
		bean.setBranchCode(branchCode);
		if("".equalsIgnoreCase(bean.getCedingName())){
			bean.setCedingName("N/A");
		}
		Map<String,List<AdjustmentBean>> adjustmentview=service.adjustmentView(bean);
		bean.setAllocatedList(adjustmentview.get("AllocatedList"));
		//bean.setMode("view");
		if(bean.getMode().equals("view")  || bean.getMode().equals("Reverse")){
			result="adjustmentList";
		}else{
			result="adjustmentPrint";
		}
		return result;
	}
	public String adjustmentList(){
		bean.setBranchCode(branchCode);
		bean.setMode("list");
		validateAdjustList();
		if (getActionErrors().isEmpty()) {
			String[] tokens = bean.getTransactionNo().split(",");
			System.out.println(tokens.length);
		if(bean.getTransactionType().equals("PC")){
			bean.setAdjustmentList(service.getAdjustmentList(bean,receiveAdjustAmountMap));
			
			if(bean.getAdjustmentList().size()!=tokens.length && bean.getTest().equals("S")){
				addActionError(getText("error.search.count"));
			}
			
		}else{
			bean.setAdjustmentList(service.getAdjustmentPayRecList(bean,receiveAdjustAmountMap));
			if(bean.getAdjustmentList().size()!=tokens.length  && bean.getTest().equals("S")){
				addActionError(getText("error.search.count"));
			}
		}
		}else{
			LOGGER.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				LOGGER.info(error.next());
			}
			LOGGER.info("##########Validation Message End###########");
			adjustmentHome();
		}
		return "adjustmentHome";
	}
	public String saveadjustment(){
		final Validation val=new Validation();
		try {
			bean.setBranchCode(branchCode);
			bean.setLoginId(userId);
			List<String> transactionNos = bean.getTransactionNos();
			List<String> adjustmentAmounts = bean.getAdjustmentAmounts();
			List<String> adjustmentType = bean.getAdjustmentType();
			List<String> transactionDates = bean.getTransactionDates();
			List<String> chkbox = bean.getChkbox();
			List<String> transDate=new ArrayList<String>();
			//Map<String,String> receiveAdjustAmountMap = new HashMap<String, String>();
			if(transactionNos!=null && transactionNos.size()>0) {
				for(int i=0 ; i<transactionNos.size() ; i++) {
					if("true".equalsIgnoreCase(chkbox.get(i))) {
						if(receiveAdjustAmountMap.containsKey(transactionNos.get(i))) {
							receiveAdjustAmountMap.remove(transactionNos.get(i));
							receiveAdjustAmountMap.put(transactionNos.get(i), adjustmentAmounts.get(i)+"~"+adjustmentType.get(i)+"~"+chkbox.get(i)+"~"+transactionDates.get(i));
							transDate.add(transactionDates.get(i));
						}
						else {
							receiveAdjustAmountMap.put(transactionNos.get(i), adjustmentAmounts.get(i)+"~"+adjustmentType.get(i)+"~"+chkbox.get(i)+"~"+transactionDates.get(i));
							transDate.add(transactionDates.get(i));
						}
					}
					else if(receiveAdjustAmountMap!=null && receiveAdjustAmountMap.containsKey(transactionNos.get(i))) {
						receiveAdjustAmountMap.remove(transactionNos.get(i));
					}
				}
			}
			bean.setTransDate(transDate);
			validteAdjustment();
			if (getActionErrors().isEmpty()) {
				if(bean.getTransactionType().equals("PC")){
					service.AddAdjustment(bean,branchCode,receiveAdjustAmountMap);
				}else{
					service.AddAdjustmentPayRec(bean,branchCode,receiveAdjustAmountMap);
				}
				Map<String,List<AdjustmentBean>> adjustmentview=service.adjustmentView(bean);
				bean.setAllocatedList(adjustmentview.get("AllocatedList"));
				bean.setMode("view");
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				if(bean.getTransactionType().equals("PC")){
					bean.setAdjustmentList(service.getAdjustmentList(bean,receiveAdjustAmountMap));
				}else{
					bean.setAdjustmentList(service.getAdjustmentPayRecList(bean,receiveAdjustAmountMap));
				}
				bean.setMode("list");
			}
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return "adjustmentHome";
	}
public String reverseInsert(){
	bean.setBranchCode(branchCode);
	bean.setLoginId(userId);
	if("".equalsIgnoreCase(bean.getCedingName())){
		bean.setCedingName("N/A");
	}
	validateReverseInsert();
	if (getActionErrors().isEmpty()) {
		service.insertReverse(bean);
		Map<String,List<AdjustmentBean>> adjustmentview=service.adjustmentView(bean);
		bean.setAllocatedList(adjustmentview.get("AllocatedList"));
	}
	else{
		LOGGER.info("##########Validation Message Start###########");
		Iterator<String> error = getActionErrors().iterator();
		while(error.hasNext()){
			LOGGER.info(error.next());
		}
		LOGGER.info("##########Validation Message End###########");
		bean.setMode("Reverse");
		Map<String,List<AdjustmentBean>> adjustmentview=service.adjustmentView(bean);
		bean.setAllocatedList(adjustmentview.get("AllocatedList"));
		if(StringUtils.isNotBlank(bean.getReverseDate())){
			bean.setReverseDate(dateFormat(bean.getReverseDate()));
		}
	}
	return "adjustmentList";
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	private void validateReverseInsert() {
		final Validation validation = new Validation();
		service.allocatedDate(bean);
		if(StringUtils.isBlank(bean.getReverseDate())) {
			this.addActionError(getText("errors.payment.reverseDateReq"));

		}
		else if(validation.checkDate(bean.getReverseDate()).equalsIgnoreCase("INVALID")) {
			this.addActionError(getText("errors.payment.reverseDateInv"));
		}
		
		else if(validation.ValidateTwoDates(bean.getAllocateddate(),bean.getReverseDate()).equalsIgnoreCase("INVALID")) {
			addActionError(getText("errors.payment.revDateGretAlloDate"));
		}
		bean.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if(!validation.isNull(bean.getOpStartDate()).equalsIgnoreCase("")&& !validation.isNull(bean.getOpEndDate()).equalsIgnoreCase("") && !validation.isNull(bean.getReverseDate()).equalsIgnoreCase("") ){
			if(new DropDownControllor().Validatethree(branchCode,bean.getReverseDate())==0){
				addActionError(getText("errors.open.period.date.reversal",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
	}
	public void validateAdjustList(){
		if(bean.getTest().equals("ALL")){
		if(bean.getBrokerId().equals("-1")){
			addActionError(getText("error.blank.brokerId"));
		}else if(bean.getBrokerId().equals("63")){
			if(bean.getCedingId().equals("-1")){
				addActionError(getText("error.blank.cedingId"));
			}
		}
		if(bean.getCurrencyId().equals("-1")){
			addActionError(getText("error.blank.currencyId"));
		}
		if(!bean.getAmountType().equals("-1")){
		if(StringUtils.isBlank(bean.getAmount())){
			addActionError(getText("error.blank.amount"));
		}
		}
		}else{
			if(StringUtils.isBlank(bean.getTransactionNo())){
				addActionError(getText("error.blank.transactionNo"));
			}
		}
		if(bean.getTransactionType().equals("-1")){
			addActionError(getText("error.blank.transactionType"));
		}
		if(bean.getAdjustType().equals("-1")){
			addActionError(getText("error.blank.adjustType"));
		}
	}
	public void validteAdjustment(){
		boolean check=true;
		final Validation val=new Validation();
		Double a=0.0,b=0.0,c=0.0;
		List<AdjustmentBean> payList=new ArrayList<AdjustmentBean>();
		//List<String> transDate=new ArrayList<String>();
		bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if(StringUtils.isBlank(bean.getAdjustmentDate())){
			addActionError(getText("error.blank.adjustmentDate"));
		}
		else if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAdjustmentDate()).equalsIgnoreCase("")){
			if(new DropDownControllor().Validatethree(branchCode, bean.getAdjustmentDate())==0){
				addActionError(getText("errors.open.period.date.adjustmentDate",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
		String maxDate=service.GetMaxDate(bean);
		if(null!=maxDate){
			if(val.ValidateTwoDates(maxDate, bean.getAdjustmentDate()).equalsIgnoreCase("Invalid")){
				addActionError(getText("errors.adjustmentDate.greater.maxDate",new String[] {maxDate}));
			}
		}
		List<String> transactionNos = bean.getTransactionNos();
		List<String> adjustmentAmounts = bean.getAdjustmentAmounts();
		List<String> chkbox = bean.getChkbox();
		Map<String,String> receiveAdjustAmountMap1 = new HashMap<String, String>();
		if(transactionNos!=null && transactionNos.size()>0) {
			for(int i=0 ; i<transactionNos.size() ; i++) {
				if("true".equalsIgnoreCase(chkbox.get(i))) {
					if(receiveAdjustAmountMap1.containsKey(transactionNos.get(i))) {
						receiveAdjustAmountMap1.remove(transactionNos.get(i));
						receiveAdjustAmountMap1.put(transactionNos.get(i), adjustmentAmounts.get(i).replace(",", ""));
					}
					else {
						receiveAdjustAmountMap1.put(transactionNos.get(i), adjustmentAmounts.get(i).replace(",", ""));						
					}
				}
				else if(receiveAdjustAmountMap1!=null && receiveAdjustAmountMap1.containsKey(transactionNos.get(i))) {
					receiveAdjustAmountMap1.remove(transactionNos.get(i));
				}
			}
		}
		if(bean.getTransactionType().equals("PC")){
			payList=service.getAdjustmentList(bean,receiveAdjustAmountMap);
		}else{
			payList=service.getAdjustmentPayRecList(bean,receiveAdjustAmountMap);
		}
		for(AdjustmentBean Obj : payList)
		{	
			AdjustmentBean form = Obj;
			if(receiveAdjustAmountMap1.containsKey(form.getTransactionNo())) {
				check=false;
					if(receiveAdjustAmountMap1.get(form.getTransactionNo())==null || "".equalsIgnoreCase(receiveAdjustAmountMap1.get(form.getTransactionNo())))
					 {			 
						 this.addActionError(getText("error.adjustAmountReq",new String[]{form.getTransactionNo()}));
					 }else if(val.numbervalid((receiveAdjustAmountMap1.get(form.getTransactionNo()))).equalsIgnoreCase("INVALID"))
					{
						 this.addActionError(getText("error.adjustAmountInv",new String[]{form.getTransactionNo()}));
					}else
					{
						String sign1;
						String sign2;
						if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))<0)
							sign1="-";
						else
							sign1="+";
						if(Double.parseDouble(form.getPendingAmount().replace(",", ""))<0)
							sign2="-";
						else
							sign2="+";
						 if(!sign1.equals(sign2))
						 {
							 this.addActionError(getText("error.sign.AdjustAmount",new String[] {form.getTransactionNo()}));
						 }else if("-".equals(sign2)&&"-".equals(sign1))
							{
								if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))*(-1)>Double.parseDouble(form.getPendingAmount().replace(",", ""))*(-1))
								{
									this.addActionError(getText("error.adjustAmountGreater",new String[] {form.getTransactionNo()}));
								}else{
									a=a+Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()));
								}
							}else if("+".equals(sign2)&&"+".equals(sign1))
							{
								if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))>Double.parseDouble(form.getPendingAmount().replace(",", "")))
								{
									this.addActionError(getText("error.adjustAmountGreater",new String[] {form.getTransactionNo()}));
								}else{
									a=a+Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()));
								}
							}
					}
					
			}else
			{
						if(receiveAdjustAmountMap1.get(form.getTransactionNo())!=null && !"".equalsIgnoreCase(receiveAdjustAmountMap1.get(form.getTransactionNo())))
						{
							this.addActionError(getText("error.check",new String[] {form.getTransactionNo()}));
							if(val.numbervalid((receiveAdjustAmountMap1.get(form.getTransactionNo()))).equalsIgnoreCase("INVALID"))
							{
								 this.addActionError(getText("error.adjustAmountInv",new String[]{form.getTransactionNo()}));
							}else 
							{
								String sign1;
								String sign2;
								if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))<0)
									sign1="-";
								else
									sign1="+";
								if(Double.parseDouble(form.getPendingAmount().replace(",", ""))<0)
									sign2="-";
								else
									sign2="+";
								 if(!sign1.equals(sign2))
								{
									 //this.addActionError(getText("error.premiumAmount",new String[] {form.getTransactionNo()}));
								}else if("-".equals(sign2)&&"-".equals(sign1))
								{
									if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))*(-1)>Double.parseDouble(form.getPendingAmount().replace(",", ""))*(-1))
									{
										this.addActionError(getText("error.adjustAmountGreater",new String[] {form.getTransactionNo()}));
									}
								}else if("+".equals(sign2)&&"+".equals(sign1))
								{
									if(Double.parseDouble(receiveAdjustAmountMap1.get(form.getTransactionNo()))>Double.parseDouble(form.getPendingAmount().replace(",", "")))
									{
										this.addActionError(getText("error.adjustAmountGreater",new String[] {form.getTransactionNo()}));
									}
								}
								 
							}
						}
			}
		}
	}
	
	public AdjustmentBean getModel() {
		return bean;
	}
}
