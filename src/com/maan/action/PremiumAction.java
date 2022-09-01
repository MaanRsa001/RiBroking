package com.maan.action;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;


import com.maan.bean.FaculitivesBean;
import com.maan.bean.PremiumBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.PremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PremiumAction extends ActionSupport implements ModelDriven<PremiumBean>{
	Map<String,Object> session = ActionContext.getContext().getSession();
	final static org.slf4j.Logger logger = LogUtil.getLogger(PortfolioAction.class);
	private String branchCode=session.get("BRANCH_CODE") == null ? "" : session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId") == null ? "" : session.get("countryId").toString();
	DropDownControllor dropDownController=new DropDownControllor();
	private String userName=session.get("UserName") == null ? "" : (String) session.get("UserName");
	PremiumService service=new PremiumService();
	PremiumBean bean=new PremiumBean();
	
	FaculitivesBean bean1=new FaculitivesBean();

	
	public String premiumList() {
		bean.setBranchCode(branchCode);
		service.getOpenPeriod(bean);
		bean.setPremiumList(service.PremiumList(branchCode,bean,"Main"));
		bean.setPremiumTempList(service.PremiumList(branchCode,bean,"Temp"));
		return "premiummaster";
	}

	public String newPremium(){
		bean.setBranchCode(branchCode);
		bean.setProductIdList(service.productIdList(bean));
		bean.setPredepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"2","Y","","","","",""));
		return "premiummaster";
	}
	public String contractSearch(){
		bean.setBranchCode(branchCode);
		bean.setYearList(getYearList());
		bean.setDepartIdlist(dropDownController.getDepartmentDropDown(branchCode,"","Y","","","","",""));
		bean.setBrokerList(dropDownController.getPersonalInfoDropDown(branchCode,"B",""));
		bean.setCedingCompanyList(dropDownController.getPersonalInfoDropDown(branchCode,"C",""));
		bean.setProductIdList(service.productIdList(bean));
		return "premiummaster";	
	}
	public String contractIdetifier(){
		bean.setBranchCode(branchCode);
		contractIdentifierValidation();
		if (!hasActionErrors()) {
		bean.setContractIdentifierList(service.contractidetifierlist(bean));
			}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			bean.setFlag("search");
		}
		contractSearch();
		return "premiummaster";	
		
	}
	private void contractIdentifierValidation() {
		if("N".equalsIgnoreCase(bean.getTransactionType())|| "".equalsIgnoreCase(bean.getTransactionType())){
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
	public String deletePremium(){
		{
			bean.setType("");
			bean.setUserName(userName);
			service.copyDatatoDeleteTable(bean);
			bean.setBranchCode(branchCode);
			service.getOpenPeriod(bean);
			bean.setPremiumList(service.PremiumList(branchCode,bean,"Main"));
			return "premiummaster";
		}
		}
	
	public PremiumBean getModel() {
		return bean;
	}
	
	}