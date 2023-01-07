package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.HomeBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.CommonService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends ActionSupport implements ModelDriven<HomeBean>{
	private static final long serialVersionUID = 1L;
	final static org.slf4j.Logger logger = LogUtil.getLogger(HomeAction.class);
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	String userId = session.get("UserId")==null?"":session.get("UserId").toString();
	HomeBean bean=new HomeBean();
	private static final String DROPDOWNAJAX="dropdownajax";
	CommonService service = new CommonService();
	private InputStream inputStream;
	public HomeBean getModel() {
		return bean;
	}
	/*public List<Map<String, Object>> getProductList() {
		return service.getProductList((String) session.get("BRANCH_CODE"));
	}*/
	public String home() {
		return "home";
	}
	public String welcome() {
		//return "welcome";
		bean.setVersion(new DropDownControllor().getVersionDetails());
		session.put("menuList",service.getMenuDropDownList(branchCode,userId));
		return "dashBoard";
	}
	public String department(){
		session.put("mfrid", bean.getProductId());
		bean.setDepartmentList(service.getDepartmentList(bean.getProductId(),session.get("BRANCH_CODE").toString()));
		if(bean.getDepartmentList().size()>0){
			request.setAttribute("departmentList", bean.getDepartmentList());
			bean.setHomeType("department");
		}
		else{
			session.put("DepartmentId", "0");
			bean.setProcessList(service.getProcessList(session.get("mfrid").toString(),"0",session.get("BRANCH_CODE").toString()));
			request.setAttribute("processMap", bean.getProcessList());
			bean.setHomeType("process");
		}

		return "welcome";
	}
	public String getProcess() {
		try{
			session.put("DepartmentId",bean.getDepartmentId());
			bean.setProcessList(service.getProcessList(session.get("mfrid").toString(),bean.getDepartmentId(),session.get("BRANCH_CODE").toString()));
			request.setAttribute("processList", bean.getProcessList());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		bean.setHomeType("process");
		return "welcome";
	}
	public String getLaunch() {
		try{
			//bean.setProcessId("2");
			session.put("processId", bean.getProcessId());
			List<Map<String,Object>> menuList =service.getFinalMenuList(session);
			session.put("menuList", menuList);
			/*MenuVB menuVB=(MenuVB) list.get(0);
			loginform.setMenuId(menuVB.getId());*/
			if("5".equalsIgnoreCase((String)session.get("mfrid"))){
				if("25".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","PT");
				}
				else if("26".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","RT");
				}
			}
			session.put("mfrid",service.getOldProductId(session));
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return "firstPage";
	}
	public static void dockbar(HttpServletRequest request,Map<String,Object> session){
		LinkedHashMap lmap=(LinkedHashMap)session.get("navList");
		LinkedHashMap temp=new LinkedHashMap();
		boolean op=false;
		String s=request.getParameter("navName");
		Set set=lmap.keySet();
		String temppid="";
		for(Object o:set){
			logger.info("Dock Bar Items====>"+o.toString());
			if(op){
				logger.info("Deleted ::::"+o);
			}
			else if(o.toString().equalsIgnoreCase(s)){
				temp.put(o, (Map)lmap.get(o));
				Map map=(Map)lmap.get(o);
				session.put("mfrid",temppid );
				op=true;
			}
			else{
				temp.put(o, (Map)lmap.get(o));
				Map map=(Map)lmap.get(o);
				for(Object o1:map.keySet()){
					temppid=(String)map.get(o1);
				}
			}
		}
		lmap=temp;
		session.put("navList", lmap);
	}

	public String departmentMenuAjax() {
		//if("endorsment".equalsIgnoreCase(bean.getMenumode())){
		if(StringUtils.isNotBlank(bean.getProposalNo())){
			if("endorsment".equalsIgnoreCase(bean.getMenumode())){
			new DropDownControllor().riskDetailsEndorsement(bean.getProposalNo(),"N","");
			}
			new DropDownControllor().updateEditMode(bean.getProposalNo(),"N","");
			String proposal = new DropDownControllor().getBaseProposal(bean.getProposalNo());
			new DropDownControllor().updateEditMode(proposal,"N","");
			new DropDownControllor().updateSubEditMode(proposal,"N","");
			new DropDownControllor().updateSubEditMode(bean.getProposalNo(),"N","");
		}
		session.put("productName",bean.getProductName());
		bean.setDropDown("departmentMenu");
		bean.setDepartmentList(service.getDepartmentList(bean.getProductId(),branchCode));
		if(bean.getDepartmentList().size()<=0) {
			bean.setDepartmentId("0");
			bean.setDepartmentName("");
			processMenuAjax();
		}
		return DROPDOWNAJAX;
	}

	public String processMenuAjax() {
		session.put("departmentName",bean.getDepartmentName());
		bean.setDropDown("processMenu");
		bean.setProcessList(service.getProcessList(bean.getProductId(),bean.getDepartmentId(),branchCode));
		return DROPDOWNAJAX;
	}

	public String finalMenuAjax() {
		session.put("processName",bean.getProcessName());
		bean.setDropDown("finalMenu");
		bean.setFinalMenuList(service.getFinalMenuList(userId,bean.getProcessId()));
		return DROPDOWNAJAX;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public void insertSessionTracking(){
		new DropDownControllor().insertSessionTrackingdetails(session,request.getSession().getId());
	}
	public void TimeOut(){
		 //HttpSession session1 = request.getSession(true) ;
	        //session1.setMaxInactiveInterval(1800) ;
	}
	public String territoryAjax(){
		if("country".equalsIgnoreCase(bean.getDropDown())){
			bean.setTerritoryCountryList(new DropDownControllor().setTerritoryCountryList(branchCode,bean.getTerritory(),bean.getCountryIncludedList(),bean.getCountryMode()));
			if("edit".equalsIgnoreCase(bean.getCountryMode())){
				bean.setExcludedCountryList(new DropDownControllor().getExcludeCountry(branchCode,bean.getTerritory(),bean.getCountryExcludedList(),bean.getCountryMode()));
			}
		}
		return "territoryAjax";
	}
}
