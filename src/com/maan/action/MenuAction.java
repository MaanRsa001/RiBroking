
package com.maan.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.MenuBean;
import com.maan.bean.PortfolioBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.service.MenuService;
import com.maan.service.PaymentBusinessService;
import com.maan.service.PortfolioService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class MenuAction extends ActionSupport implements ModelDriven<MenuBean> {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogUtil.getLogger(PortfolioAction.class);
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	MenuService service = new MenuService();
	MenuBean bean=new MenuBean();
	private String userId=session.get("UserId")==null?"":(String) session.get("UserId");
	//	private String processId=session.get("processId").toString();
	private String userType=session.get("UserType")==null?"":session.get("UserType").toString();
	//	private String departmentId=session.get("DepartmentId").toString();
	private String branchCode=session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	//	private static String department="Department";
	//	private static final String MFRID="mfrid";
	private String manufactureID=session.get("mfrid")==null?"":session.get("mfrid").toString();
	public MenuBean getModel() {
		return bean;
	}
	private  static String ForwardName="";
	
	public String adminMenu() {
		final MenuService menuCB = new MenuService();

		List menu;
		final HttpSession session = request.getSession(false);
		try {
			logger.info("Menu Controller menu method() - Enter");
			bean.setManufactureID("1");
			menu = menuCB.getAdminMenu(userId, manufactureID);
			/*for(Object o:menu){
				MenuVB menucb=(MenuVB)o;
				logger.info(menucb.getContent());
			}*/
			session.setAttribute("menu", menu);
			//session.setAttribute("mfrid",(manufactureID==null?(String)session.getAttribute("mfrid"):manufactureID) );
			session.setAttribute("mfrid","0");
			session.setAttribute("mfrName", menuCB.getManuName(manufactureID,session.getAttribute("BRANCH_CODE").toString()));

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			logger.debug("Login Controller method() - Exit");
		}
		return "adminMenu";
	}
	public String  userRights(){
		logger.info("MenuDispathAction UserRights() || Enter");
		try {	

			session.put("menuId",bean.getMenuId());
			if(session.containsKey("processId")) {
				MenuService menuCb=new MenuService();
				String list=menuCb.getRigthsOfProcess(bean.getMenuId(),session.get("processId").toString(),userId);
				session.put("MenuRights", list);
				logger.info("New Sub Mneu Rights====>"+list);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("MenuDispathAction UserRights() || Exit");
		return "redirect";
	}


	public String process() {
		logger.info("MenuDispatchAction process || Enter");
		MenuService businesslogic=new MenuService();
		try{

			dockbar(request,session);

			String product_id=request.getParameter("manufactureID");
			session.put("mfrid", product_id);
			Map map=businesslogic.getDepartmentListNew(product_id,session.get("BRANCH_CODE").toString());
			if(map.size()>0){
				request.setAttribute("departmentMap", map);
				ForwardName="departmentPage";
			}
			else{
				session.put("DepartmentId", "0");
				map=businesslogic.getProcessList(session.get("mfrid").toString(),"0",session.get("BRANCH_CODE").toString());
				request.setAttribute("processMap", map);
				ForwardName="processPage";
			}

		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDispatchAction process || Exit");
		return ForwardName;
	}
	public String process2(){
		logger.info("MenuDispatchAction process2 || Enter");
		List menu;
		MenuService businesslogic=new MenuService();


		dockbar(request,session);

		try{
			String dept_Id=request.getParameter("deptId");
			session.put("DepartmentId", dept_Id);
			Map map=businesslogic.getProcessList(session.get("mfrid").toString(),dept_Id,session.get("BRANCH_CODE").toString());
			request.setAttribute("processMap", map);
			ForwardName="processPage";
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDispatchAction process2 || Exit");
		return ForwardName;
	}
	public String menuNew() {
		logger.info("MenuDispatchAction menu || Enter");
		MenuService businesslogic=new MenuService();
		try{

			dockbar(request,session);

			String process_id=request.getParameter("processId");

			session.put("processId", process_id);
			List list=businesslogic.getFinalMenuList(session);
			session.put("menu", list);
			if("5".equalsIgnoreCase((String)session.get("mfrid"))){
				if("25".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","PT");
				}
				else if("26".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","RT");
				}
			}
			session.put("mfrid",businesslogic.getOldProductId(session));

			ForwardName="firstNew";
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDispatchAction menu || Exit");
		return ForwardName;
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


	public String dnavigation() {
		try {
			List<Map<String,String>> navList = (List<Map<String, String>>) session.get("navList");
			if("add".equalsIgnoreCase(bean.getPositionType())) {
				if(navList.size() < Integer.valueOf(bean.getListPosition())) {
					Map<String, String> navMap = new HashMap<String, String>();
					navMap.put("LIST_KEY",bean.getMenuName());
					navMap.put("LIST_VALUE",bean.getMenuUrl());
					navList.add(navMap);
					session.put("navList", navList);
				}
			}
			else if("delete".equalsIgnoreCase(bean.getPositionType())) {
				while(navList.size() > Integer.valueOf(bean.getListPosition())) {
					int index = Integer.valueOf(bean.getListPosition());
					navList.remove(index);
					session.put("navList", navList);
				}
			}

		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return "redirect";
	}

	public String dockNavigation() {
		try {
			if("endorsment".equalsIgnoreCase(bean.getMenumodeStatus()) && StringUtils.isBlank(bean.getMenuId()) && "welcomeHome.action".equalsIgnoreCase(bean.getMenuUrl())){
				new DropDownControllor().riskDetailsEndorsement(bean.getProposalNo(),"N","");
				new DropDownControllor().updateEditMode(bean.getProposalNo(),"N","");
					String proposal = new DropDownControllor().getBaseProposal(bean.getProposalNo());
					new DropDownControllor().updateEditMode(proposal,"N","");
					new DropDownControllor().updateSubEditMode(proposal,"N","");
					new DropDownControllor().updateSubEditMode(bean.getProposalNo(),"N","");
			}
			
			session.put("mfrid", bean.getProductId());
			session.put("ProductName", bean.getProductName());

			session.put("DepartmentId",bean.getDepartmentId());
			session.put("DepartmentName",bean.getDepartmentName());

			session.put("processId", bean.getProcessId());
			session.put("ProcessName", bean.getProcessName());
			session.put("menuName", bean.getMenuName());
			
			if("5".equalsIgnoreCase((String)session.get("mfrid"))){
				if("25".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","PT");
				}
				else if("26".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","RT");
				}
			}
			session.put("mfrid",service.getOldProductId(session));
			
			session.put("menuId",bean.getMenuId());
			if(session.containsKey("processId")) {
				MenuService menuCb=new MenuService();
				String list=menuCb.getRigthsOfProcess(bean.getMenuId(),session.get("processId").toString(),userId);
				session.put("MenuRights", list);
				logger.info("New Sub Mneu Rights====>"+list);
			}
			new DropDownControllor().insertSessionTrackingdetails(session,request.getSession().getId());
		}
		catch(Exception e) {
			logger.debug("Exception @ dockmenu { " + e + " } ");
		}
		return "redirect";
	}
	public String menuNavigation() {
		try {
			String text []=bean.getMenuName().split("~");
			bean.setProductId(text[0]);
			bean.setDepartmentId(text[1]);
			bean.setProcessId(text[2]);
			bean.setMenuUrl(text[4]);
			bean.setProductName(text[5]);
			bean.setDepartmentName(text[6]);
			bean.setProcessName(text[7]);
			bean.setMenuId(text[8]);
			session.put("mfrid", bean.getProductId());
			session.put("ProductName", bean.getProductName());

			session.put("DepartmentId",bean.getDepartmentId());
			session.put("DepartmentName",bean.getDepartmentName());

			session.put("processId", bean.getProcessId());
			session.put("ProcessName", bean.getProcessName());
			
			if("5".equalsIgnoreCase((String)session.get("mfrid"))){
				if("25".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","PT");
				}
				else if("26".equalsIgnoreCase((String)session.get("processId"))){
					session.put("AllocateType","RT");
				}
			}
			session.put("mfrid",service.getOldProductId(session));
			
			session.put("menuId",bean.getMenuId());
			if(session.containsKey("processId")) {
				MenuService menuCb=new MenuService();
				String list=menuCb.getRigthsOfProcess(bean.getMenuId(),session.get("processId").toString(),userId);
				session.put("MenuRights", list);
				logger.info("New Sub Mneu Rights====>"+list);
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ dockmenu { " + e + " } ");
		}
		return "redirect";
	}

}