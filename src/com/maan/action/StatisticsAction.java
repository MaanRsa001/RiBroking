package com.maan.action;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.ClaimBean;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.common.util.DropDownControllor;
import com.maan.service.ClaimService;
import com.maan.service.ProportionalPremiumService;
import com.maan.service.StatisticsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticsAction extends ActionSupport implements ModelDriven<FaculPremiumBean>
{
	private static final long serialVersionUID = 1L;
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	DropDownControllor dropDownController=new DropDownControllor();
	FaculPremiumBean bean=new FaculPremiumBean();
	StatisticsService service= new StatisticsService();
	
	private String productId=session.get("mfrid") == null ? "" :(String) session.get("mfrid");
	private String userId=session.get("UserId") == null ? "" :(String) session.get("UserId");
	private String userType=session.get("userType") == null ? "" :(String) session.get("userType");
	private String userName=session.get("UserName") == null ? "" :(String) session.get("UserName");
	//private String processId=session.get("processId").toString();
	private String countryId=session.get("countryId") == null ? "" :session.get("countryId").toString();
	private String branchCode=session.get("BRANCH_CODE") == null ? "" :session.get("BRANCH_CODE").toString();
	
	private InputStream inputStream;
	public FaculPremiumBean getModel() {
		return bean;
	}	
	public String statisticsDown(){
		String forward="statistics";
		bean.setProductId(session.get("mfrid").toString());
		bean.setBranchCode(branchCode);
		bean.setUwYearList(getYearList());
		bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		bean.setDepartIdlist(dropDownController.getStatictisDepartmentDropDown(branchCode,productId,"Y",bean.getContractNo()));
		bean.setRenewalDepartIdlist(dropDownController.getDepartmentRenewalStatisticsDropDown(branchCode,productId,bean.getUwFrom(),bean.getUwTo(),bean.getContractNo()));
		
		service.SlideScenario(bean);
		if("popup".equalsIgnoreCase(bean.getMode())){
			forward="statisticsView";
		}
		return forward;
		
	}
	private List<Map<String,Object>> getYearList(){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		Calendar cal=Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
	      Date dateAsObj = null;
		try {
			if(StringUtils.isBlank(bean.getInception_Date())){
				bean.setInception_Date(dropDownController.getInceptionDateVal(bean.getProposal_No(),bean.getContractNo()));
			}
				dateAsObj = sdf.parse(bean.getInception_Date());
		} catch (ParseException e) {
				e.printStackTrace();
		}
	    cal.setTime(dateAsObj);
		for(int j=(cal.get(Calendar.YEAR));j>=(cal.get(Calendar.YEAR)-4);j--){
			Map<String,Object> year=new HashMap<String, Object>();
			year.put("YEAR", j);
			yearsList.add(year);
		}
		return yearsList;
	}
	public static void main(String[] args){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		Calendar cal=Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
	      Date dateAsObj = null;
		try {
				dateAsObj = sdf.parse("29/02/2012");
		} catch (ParseException e) {
				e.printStackTrace();
		}
	    cal.setTime(dateAsObj);
	    Calendar cal1=Calendar.getInstance(); 
		
	      Date dateAsObj1 = null;
		try {
				dateAsObj1 = sdf.parse("30/06/2017");
		} catch (ParseException e) {
				e.printStackTrace();
		}
	    cal1.setTime(dateAsObj1);
	    for(int i=(cal.get(Calendar.YEAR));i<(cal1.get(Calendar.YEAR));i++){
	    	cal.add(Calendar.YEAR, 1); 
	    	Map<String,Object> year=new HashMap<String, Object>();
	    	String formatted = sdf.format(cal.getTime());
	    	System.out.println(formatted);
			//year.put("YEAR", cal.getd);
			//yearsList.add(year);
	    }
		/*Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
		String formatted = sdf.format(cal.getTime());
    	System.out.println(formatted);*/
	}
	public String ajaxValue(){	
		bean.setRenewalDepartIdlist(dropDownController.getDepartmentRenewalStatisticsDropDown(branchCode,productId,bean.getUwFrom(),bean.getUwTo(),bean.getContractNo()));
		return "dropdownajax";
	}
	public List<Map<String,Object>> getRowHeaderlist(){
    	return dropDownController.getConstantDropDown("48");
    }
	public List<Map<String,Object>> getColumnHeaderlist(){
    	return service.getColumnHeaderlist(bean);
    }
	public String renewalDetails(){
		bean.setMode("renewal");
		bean.setBranchCode(branchCode);
		Map<String,Object> doubleMap = new HashMap<String,Object>();
		 List<Map<String,Object>> remarkList=new ArrayList<Map<String,Object>>();
		 doubleMap.put("one",new Double(1.0));
		 remarkList.add(doubleMap);
		bean.setPremiumList(service.getRenewalStatisticsList(bean,"PREMIUM"));
		bean.setClaimList(service.getRenewalStatisticsList(bean,"CLAIM"));
		bean.setAcqcostList(service.getRenewalStatisticsList(bean,"ACQCOST"));
		bean.setClaimoutStandList(service.getRenewalStatisticsList(bean,"CLAIMOUTSTANDING"));
		bean.setTreatyResultList(service.getRenewalCalStatisticsList(bean,"treaty"));
		bean.setClaimRatioList(service.getRenewalCalStatisticsList(bean,"calimratio"));
		bean.setCombinedRatioList(service.getRenewalCalStatisticsList(bean,"combined"));
		
		return "statistics";
		
	}
}
