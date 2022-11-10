package com.maan.action.admin;

import com.maan.bean.admin.AdminBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.admin.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdminAction extends ActionSupport implements ModelDriven<AdminBean> {
	private static final Logger LOGGER = LogUtil.getLogger(AdminAction.class);
	Map<String, Object> session = ActionContext.getContext().getSession();
	AdminBean bean=new AdminBean();
	AdminService service=new AdminService();
	Validation val=new Validation();
	private String branchCode=session.get("BRANCH_CODE").toString();
	//private String processId=session.get("PROCESS_ID").toString();
	//private String menuId=session.get("MENU_ID").toString();
	private String countryId=session.get("countryId").toString();
	private String userId=(String) session.get("UserId");
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	private String mode;
	private String optionMode;
	private Map submenuinfo;
	private Map menuinfo;

	public List<Map<String,Object>> getDropDownTerritoryList(){
		return service.getDropDownTerritoryList(bean,branchCode);
	}
	public List<Map<String,Object>> getDropDownResidentalList(){
		return service.getDropDownResidentalList(bean);
	}
	public List<Map<String,Object>> getDropDownTypeList(){
		return service.getDropDownTypeList(bean);
	}
	public List<Map<String,Object>> getCrestaMasterList(){
		return service.getCrestaMasterList(bean,branchCode);
	}
	public List<Map<String,Object>> getRateMasterList(){
		return service.getRateMasterList(bean,branchCode);
	}
	public List<Map<String,Object>> getAttachUnderWriterList(){
		return service.getAttachUnderWriterList(branchCode);
	}

	private Map info;

	public Map getInfo() {
		return info;
	}

	public Map setInfo(Map info) {
		return this.info = info;
	}

	public Map getMenuinfo() {
		return menuinfo;
	}

	public void setMenuinfo(Map menuinfo) {
		this.menuinfo = menuinfo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getOptionMode() {
		return optionMode;
	}

	public void setOptionMode(String optionMode) {
		this.optionMode = optionMode;
	}

	public String login() {
		return "login";
	}

	public String welcome() {
		return "welcome";
	}
	public String welcomeHome(){
		return INPUT;
	}
	public String listMenu() {
		mode = "menulist";
		return "editMenu";
	}

	public String editMenu() {
		mode = "editmenu";
		return "editMenu";
	}

	public String listUser() {
		mode = "userlist";
		return "editUser";
	}

	public String editUser() {
		mode = "edituser";
		return "editUser";
	}

	public String listBank() {
		mode = "banklist";
		return "editBank";
	}

	public String editBank() {
		mode = "editbank";
		return "editBank";
	}

	public String listBankAc() {
		mode = "bankaclist";
		return "editBankAc";
	}

	public String editBankAc() {
		mode = "editbankac";
		return "editBankAc";
	}

	public String listBankEr() {
		mode = "bankerlist";
		return "editBankEr";
	}

	public String editBankEr() {
		mode = "editbanker";
		return "editBankEr";
	}

	public String listReject() {
		mode = "rejectlist";
		return "editReject";
	}

	public String editReject() {
		mode = "editreject";
		return "editReject";
	}

	public String listBranch() {
		mode = "branchlist";
		return "editBranch";
	}

	public String editBranch() {
		mode = "editbranch";
		return "editBranch";
	}

	public String listTmasVal() {
		mode = "listtmasval";
		return "editTmasVal";
	}

	public String editTmasVal() {
		mode = "edittmasval";
		return "editTmasVal";
	}

	public String getMenuSelection() {
		return "menuPopup";
	}

	public AdminBean getModel() {
		return bean;
	}

	public String profitCenter(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListProfitCenter");
		bean.setProfitCenterList(service.getProfitCenterList(bean));
		return "profitCenter";
	}
	public String editProfitCenter(){
		bean.setPath("InsertProfitCenter");
		bean.setMode("insert");
		return "profitCenter";
	}
	public String insertProfitCenter(){
		LOGGER.info("Enter into insertProfitCenter method");
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertProfitCenter");
			service.getEditProfitCenter(bean);
		}
		else if("update".equals(bean.getMode())||"insert".equals(bean.getMode()) ){
			validateprofitcenter();

			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateProfitCenter(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertProfitCenter(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				profitCenter();
				bean.setPath("ListProfitCenter");
				return "profitCenter";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setPath("InsertProfitCenter");
		LOGGER.info("Exit into insertProfitCenter method");
		return "profitCenter";
	}
	public String userListMaster(){
		LOGGER.info("Enter into userListMaster method");
			if("search".equals(bean.getMode())){
				validatesearch();
			}
			if(hasActionErrors()){
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			bean.setBranchCode(branchCode);
			bean.setPath("ListUserMaster");
			bean.setUserListMaster(service.getUserListMaster(bean));
		LOGGER.info("Exit into userListMaster method");
		return "userListMaster";
	}
	public String addUserList(){
		bean.setUseroperation("new");
		bean.setPath("LoginDisp");
		return "userListMaster";
	}
	public String insertUserMaster(){
		LOGGER.info("Enter into insertUserMaster method");
		bean.setBranchCode(branchCode);
		if("LoginUpdate".equals(bean.getMode())){
			validateUser();
			if(!hasActionErrors()){
				if("new".equals(bean.getUseroperation())){
					service.insertNewUser(bean);
					bean.setPath("success");
				}else{
					service.updateUserList(bean);
					bean.setPath("success");
				}
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				bean.setPath("LoginDisp");
			}
		}else if("LoginEdit".equals(bean.getMode())){
			bean.setLoginId(bean.getLid());
			service.getEditUserList(bean);
			bean.setPath("LoginDisp");
		}
		else if("PasswordDisp".equals(bean.getMode())){
			bean.setPath("PasswordDisp");
		}
		else if("PasswordChange".equals(bean.getMode())){
			validatePassWord();
			if(!hasActionErrors()){
				service.getChangePassword(bean);
				bean.setPath("success");
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				bean.setPath("PasswordDisp");
			}
		}

		LOGGER.info("Exit into insertUserMaster method");
		return "userListMaster";
	}
	public String MenuAllocation(){
		String result="";
		LOGGER.info("Enter into MenuAllocation method");
		bean.setBranchCode(branchCode);
		//bean.setLoginId(userId);
		if(bean.getMode().equals("Menu")){
			if("Admin".equalsIgnoreCase(bean.getUserType())){
				bean.setPath("MenuInfo");
				bean.setMenuinfo(service.AdminMenuAllocation(bean));
			}else{
				bean.setPath("MenuInfo");
				bean.setMenuinfo(service.menuAllocationInfo(bean));
			}
			LOGGER.info("Exit into MenuAllocation method");
			result="userListMaster";
		}

		return result;

	}
	@SuppressWarnings("static-access")
	public String SubMenuAllocation(){
		LOGGER.info("Enter into SubMenuAllocation method");
		String result="";
		bean.setBranchCode(branchCode);
		if(bean.getMode().equals("SubMenu")){
			if("Admin".equalsIgnoreCase(bean.getUserType())){
				submenuinfo=bean.setSubmenuinfo(service.AdminSubMenuAllocation(bean));
				if(submenuinfo.size()>0){
					bean.setPath("SubMenuInfo");
				}
				else
				{
					bean.setPath("none");
				}
				result="userListMaster";
			}
			else{
				submenuinfo=bean.setSubmenuinfo(service.AdminSubMenuAllocation(bean));
				if(submenuinfo.size()>0){
					bean.setPath("SubMenuInfo");
				}
				else
				{
					bean.setPath("none");
				}
				result="userListMaster";
			}
		}
		else if(bean.getMode().equals("submenuscreen")){
			submenuinfo=bean.setSubmenuinfo(service.SubmenuAllocationInfo(bean));
			bean.setPath("submenu");
			//bean.setPath("menu");
			result="menuPopUp";
			/*if(submenuinfo.size()>0){
				bean.setPath("SubMenuInfo");
			}
			else
			{
				bean.setPath("none");
			}*/

		}
		else if(bean.getMode().equals("menuscreen")){
			bean.setPath("menu");
			//menuinfo=bean.setMenuinfo(service.menuAllocation(bean));
			bean.setMenuinfo(service.menuAllocation(bean));
			bean.setMenuPath(bean.getMenuPath(menuinfo));
			result="menuPopUp";
				/*if(submenuinfo.size()>0){
					bean.setPath("SubMenuInfo");
				}
				else
				{
					bean.setPath("none");
				}*/
		}
		else if(bean.getMode().equals("menusave")){
			bean.setPath("menu");
			boolean flag=service.SaveManu(bean);
			bean.setPath("menu");
			if(flag){
				bean.setPath("success");
			}
			else{
				bean.setPath("error");
			}result="menuPopUp";
		}
		else if(bean.getMode().equals("submenusave")){
			bean.setPath("menu");
			boolean flag=service.subMenuSave(bean);
			bean.setPath("submenu");
			if(flag){
				bean.setPath("success");
			}
			else{
				bean.setPath("error");
			}
			result="menuPopUp";
		}
		else if(bean.getMode().equals("submenumastersave")){
			bean.setPath("menu");
			boolean flag=service.subMenuAllocationSave(bean);
			bean.setPath("submenu");
			if(flag){
				bean.setPath("success");
			}
			else{
				bean.setPath("error");
			}
			result="menuPopUp";
		}
		LOGGER.info("Exit into SubMenuAllocation method");
		return result;
	}
	public String SubMenuAllocations(){
		LOGGER.info("Enter into SubMenuAllocations method");
		String result="";
		bean.setBranchCode(branchCode);
		if(bean.getMode().equals("info")){
			info=setInfo(service.allocationInfo(bean));
			bean.setPath("SubMenuInfo");
			result="submenuMaster";
		}
		if(bean.getMode().equals("submenuscreen")){
			//bean.setPath("submenu");
			bean.setPath("submenumaster");
			submenuinfo=bean.setSubmenuinfo(service.allocationList(bean));

			result="menuPopUp";
		}
		LOGGER.info("Exit into SubMenuAllocations method");
		return result;
	}

	public String globalSubmit(){
		LOGGER.info("Enter into globalSubmit method");
		String result1="";
		bean.setBranchCode(branchCode);
		if(bean.getMode().equals("menu")){
			HttpServletRequest request = ServletActionContext.getRequest();
			Enumeration e = request.getParameterNames();
			Map<String,String> map=new HashMap<String,String>();
			while(e.hasMoreElements()){
				String temp=(String)e.nextElement();
				String[] s=temp.split(",");
				System.out.println("parameters"+s.toString());
				int i=0;
				for(String st:s){
					if(i==1){
						String value;
						if(null==request.getParameter(temp)||"".equalsIgnoreCase(request.getParameter(temp))){
							value="0";
						} else {
							value=request.getParameter(temp);
							System.out.println("parameters"+value);
						}
						map.put(temp,value);
					}
					i++;
				}
			}
			bean.setUwlimitMap(map);
			bean.setBranchCode(branchCode);
			boolean result=service.menuGlobalSubmit(bean);
			if(result){
				bean.setPath("success");
			} else {
				bean.setPath("error");
			}
			return "userListMaster";
		}else if(bean.getMode().equals("SubMenu")){
			bean.setBranchCode(branchCode);
			boolean result=service.subMenuGlobalSubmit(bean);
			if(result){
				bean.setPath("success");
			} else {
				bean.setPath("error");
			}
		} else if (bean.getMode().equals("adminmenusave")) {
			bean.setBranchCode(branchCode);
			boolean result=service.saveAdminMenus(bean);
			if(result){
				bean.setPath("success");
			} else {
				bean.setPath("error");
			}
		}
		LOGGER.info("Exit into globalSubmit method");
		return "userListMaster";
	}

	public String globalSubmitNew() {
		LOGGER.info("Enter into globalSubmitNew method");
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean resultSubMenu = true;
		boolean resultDepartment = true;
		ArrayList<String> addList = new ArrayList<String>();
		if (request.getParameterMap().get("selectedMainMenu") != null) {
			Collections.addAll(addList, request.getParameterMap().get("selectedMainMenu")); 
			bean.setBranchCode(branchCode);
			resultDepartment = service.insertMainMenu(bean, addList);
		}else{
			bean.setBranchCode(branchCode);
			resultDepartment = service.removeMainMenuAllocationForUser(bean);
			resultSubMenu = service.removeSubMenuAllocationForUser(bean);
		}
		ArrayList<String> addSubMenuList = new ArrayList<String>();
		if (request.getParameterMap().get("selectedSubMenuButton") != null && request.getParameterMap().get("selectedMainMenu") != null) {
			Collections.addAll(addSubMenuList, request.getParameterMap().get("selectedSubMenuButton"));
			bean.setBranchCode(branchCode);
			resultSubMenu = service.insertSubMenu(bean, addSubMenuList);
		}
		else{
			bean.setBranchCode(branchCode);
			resultSubMenu = service.removeSubMenuAllocationForUser(bean);
		}
		if (resultDepartment && resultSubMenu) {
			bean.setPath("success");
		} else {
			bean.setPath("error");
		}
		LOGGER.info("Exit into globalSubmitNew method");
		return "userListMaster";
	}


	public void validateUser(){
		if(bean.getUseroperation().equalsIgnoreCase("new")){
			if(StringUtils.isBlank(bean.getLoginId())){
				addActionError(getText("error.userLoginId.required"));
			}else {
				int count=service.getLoginCount(bean,branchCode);
				if(count>0) {
					addActionError(getText("error.userLoginId.invalid"));
				}
			}
		}

		if(StringUtils.isBlank(bean.getUserName())){
			addActionError(getText("error.userName.required"));
		}


		if(StringUtils.isBlank(bean.getEmail())){
			addActionError(getText("error.isBlank.Email"));
		}else{
			if (!isValidEmailAddress(bean.getEmail())) {
				addActionError(getText("error.invalid.Email"));
			}
		}


		if(bean.getUserType().equalsIgnoreCase("0")){
			addActionError(getText("error.userType.required"));
		}
		if(bean.getUseroperation().equalsIgnoreCase("new")){
			int i=1;
			if(StringUtils.isBlank(bean.getPassword())){
				addActionError(getText("error.password.required"));
				i=0;
			}

			if(StringUtils.isBlank(bean.getRePassword())){
				addActionError(getText("error.repassword.required"));
			}
			else if(i==1){
				if(!bean.getPassword().equals(bean.getRePassword())){
					addActionError(getText("error.passqword.error"));
				}
			}
		}
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.userActive.required"));
		}
		if(StringUtils.isBlank(bean.getAttachedUW())) {
			addActionError(getText("error.useruw.required"));
		}
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}


	public void validatePassWord(){
		int i=1;
		if(StringUtils.isBlank(bean.getPassword())){
			addActionError(getText("error.password.required"));
			i=0;
		}
		if(StringUtils.isBlank(bean.getRePassword())){
			addActionError(getText("error.repassword.required"));
		}
		else if(i==1){
			if(!bean.getPassword().equals(bean.getRePassword())){
				addActionError(getText("error.passqword.error"));
			}
		}
	}

	public String exchangeMaster(){
		LOGGER.info("Enter into exchangeMaster method");
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListExchangeMaster");
		bean.setExchangeList(service.getExchangeListMaster(bean));
		LOGGER.info("Exit into exchangeMaster method");
		return "exchangeMaster";
	}

	public String addExchangeMaster(){
		LOGGER.info("Enter into addExchangeMaster method");
		bean.setBranchCode(branchCode);
		bean.setCountryId(countryId);
		bean.setPath("InsertExchangeMaster");
		bean.setMode("insert");
		bean.setExchangeCurrencyList(service.GetExchangeCurrency(bean));
		LOGGER.info("Exit into addExchangeMaster method");
		return "exchangeMaster";
	}
	public String insertExchangeMaster(){
		LOGGER.info("Enter into insertExchangeMaster method");
		bean.setBranchCode(branchCode);
		bean.setCountryId(countryId);
		if("Edit".equals(bean.getMode())){
			//service.getEditExchangeMaster(bean);exchageRate
			bean.setExchangeCurrencyList(service.GetExchangeCurrency(bean));
			bean.setMode("update");
			bean.setPath("InsertExchangeMaster");
		}else if("view".equals(bean.getMode())){
			//service.getEditExchangeMaster(bean);
			bean.setExchangeCurrencyList(service.GetExchangeCurrency(bean));
			bean.setMode("view");
			bean.setPath("InsertExchangeMaster");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			int count;
			validateExchange();
			if(!hasActionErrors()){
				if("insert".equals(bean.getMode())){
					count=service.insertExchangeRate(bean);
					if(count==0){
						addActionMessage(getText("insert.error"));
					}else{
						addActionMessage(getText("insert.success"));
					}
				}
				else if("update".equals(bean.getMode())){
					//count=service.updateExchangeRate(bean);
					count=service.insertExchangeRate(bean);
					if(count==0){
						addActionMessage(getText("update.error"));
					}else{
						addActionMessage(getText("update.success"));
					}
				}
				exchangeMaster();
				//bean.setPath("success");
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				if(StringUtils.isNotBlank(bean.getInceptionDate()))
					bean.setInceptionDate(dateFormat(bean.getInceptionDate()));
				bean.setMode("Edit");
				bean.setExchangeCurrencyList(service.GetExchangeCurrency(bean));
				bean.setMode("update");
				bean.setPath("InsertExchangeMaster");
			}
		}
		LOGGER.info("Exit into insertExchangeMaster method");
		return "exchangeMaster";
	}
	public void validateExchange(){
		String indate=service.GetInceptionDate(bean);
		if("insert".equalsIgnoreCase(bean.getMode())){
			if(StringUtils.isBlank(bean.getInceptionDate())){
				addActionError(getText("error.inceptionDate.required"));
			}else if(val.checkDate(bean.getInceptionDate()).equalsIgnoreCase("invalid")){
				addActionError(getText("error.inceptionDate.error"));
			}else if(val.ValidateTwoDates1(indate,bean.getInceptionDate()).equalsIgnoreCase("Invalid")){
				addActionError(getText("error.exchengeinceptionDateValue.error"));
			}
		}
		for(int i=0;i<bean.getCurrencyNameList().size();i++){
			if(StringUtils.isBlank(bean.getExchageRate().get(i))){
				addActionError(getText("error.exchangeRate.required",new String[]{bean.getCurrencyNameList().get(i)}));
			}
		}


		/*if(bean.getCurrencyId().equals("-1")){
			addActionError(getText("error.exchangeCurrencyId.required"));
		}
		if(StringUtils.isBlank(bean.getExchangeRate())){
			addActionError(getText("error.exchangeRate.required"));
		}
		if(StringUtils.isBlank(bean.getCoreAppCode())){
			addActionError(getText("error.coreappcode.required"));
		}else if(service.getExcCoreAppCode(bean)!=0){
			addActionError(getText("error.exchangeCoreExist.error"));
		}
		String currentDate=service.GetSysDate(bean);
		if(StringUtils.isBlank(bean.getInceptionDate())){
			addActionError(getText("error.inceptionDate.required"));
		}else if(val.checkDate(bean.getInceptionDate()).equalsIgnoreCase("invalid")){
			addActionError(getText("error.inceptionDate.error"));
		}else if(val.PresentDate(currentDate,bean.getInceptionDate()).equalsIgnoreCase("Invalid")){
			addActionError(getText("error.inceptionDate.error.valid"));
		}else if("update".equals(bean.getMode())){
			String indate=service.GetInceptionDate(bean);
			if(val.ValidateTwoDates(indate,bean.getInceptionDate()).equalsIgnoreCase("Invalid")){
				addActionError(getText("error.exchengeinceptionDateValue.error"));
			}
		}
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.status.required"));
		}
		if(service.getExchangeCurrency(bean)!=0){
			addActionError(getText("error.exchangeName.check"));
		}*/
	}
	public String menuMaster(){
		bean.setPath("Home");
		bean.setUserType("-1");
		return "menuMaster";
	}
	public String AddNew(){
		LOGGER.info("Enter into AddNew method");
		if(bean.getMode().equals("AddForm")){
			bean.setMenuId(service.getMenuId(bean));
			bean.setPath("DispForm");
			bean.setMode("insert");
		}
		LOGGER.info("Exit into AddNew method");
		return "menuMaster";
	}
	public String dropdownAjax(){
		List list=new ArrayList();
		bean.setBranchCode(branchCode);
		if("product".equals(bean.getReqform())){
			if("User".equals(bean.getUserType())){
				bean.setProductList(service.getProductList(bean));
			}else{

				Map m = new HashMap();
				m.put("TMAS_PRODUCT_ID", "0");
				m.put("TMAS_PRODUCT_NAME", "NONE");
				list.add(m);
				bean.setProductList(list);
			}
		}else if("department".equals(bean.getReqform())){
			if("User".equals(bean.getUserType())){
				bean.setDepartmentList(service.getDepartmentList(bean));
			}else{
				Map m = new HashMap();
				m.put("TMAS_DEPARTMENT_ID", "0");
				m.put("TMAS_DEPARTMENT_NAME", "NONE");
				list.add(m);
				bean.setDepartmentList(list);
			}
		}
		else if("process".equals(bean.getReqform())){
			if("User".equals(bean.getUserType())){
				bean.setProcessList(service.getProcessList(bean));
			}else{
				Map m = new HashMap();
				m.put("PROCESS_ID", "0");
				m.put("PROCESS_NAME", "NONE");
				list.add(m);
				bean.setProcessList(list);
			}
		}else if("menu".equals(bean.getReqform())){
			bean.setMenuList(service.getMenuList(bean));
		}
		return "adminAjax";

	}
	public String insertMenuMaster(){
		LOGGER.info("Enter into insertMenuMaster method");
		List list=new ArrayList();
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			validationmenuEdit();
			if(!hasActionErrors()){
				service.getEditMenuMaster(bean);
				if("User".equals(bean.getUserType())){
					bean.setProductList(service.getProductList(bean));
				}else{

					Map m = new HashMap();
					m.put("TMAS_PRODUCT_ID", "0");
					m.put("TMAS_PRODUCT_NAME", "NONE");
					list.add(m);
					bean.setProductList(list);
				}
				if("User".equals(bean.getUserType())){
					bean.setDepartmentList(service.getDepartmentList(bean));
				}else{
					Map m = new HashMap();
					m.put("TMAS_DEPARTMENT_ID", "0");
					m.put("TMAS_DEPARTMENT_NAME", "NONE");
					list.add(m);
					bean.setDepartmentList(list);
				}
				if("User".equals(bean.getUserType())){
					bean.setProcessList(service.getProcessList(bean));
				}else{
					Map m = new HashMap();
					m.put("PROCESS_ID", "0");
					m.put("PROCESS_NAME", "NONE");
					list.add(m);
					bean.setProcessList(list);
				}
				bean.setMenuList(service.getMenuList(bean));
				bean.setMode("update");
				bean.setPath("DispForm");
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				bean.setPath("Home");
			}

		}else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			int count;
			validateMenu();
			if(!hasActionErrors()){
				if("insert".equals(bean.getMode())){
					count=service.insertMenu(bean);
					if(count==0){
						addActionMessage(getText("insert.error"));
					}else{
						addActionMessage(getText("insert.success"));
					}
				}
				else if("update".equals(bean.getMode())){
					count=service.updateMenu(bean);
					if(count==0){
						addActionMessage(getText("update.error"));
					}else{
						addActionMessage(getText("update.success"));
					}
				}
				bean.setPath("success");
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				if(StringUtils.isNotBlank(bean.getInceptionDate()))
					bean.setInceptionDate(dateFormat(bean.getInceptionDate()));
				if(StringUtils.isNotBlank(bean.getInceptionDate()))
					bean.setExpiryDate(dateFormat(bean.getExpiryDate()));
				if("User".equals(bean.getUserType())){
					bean.setProductList(service.getProductList(bean));
				}else{
					Map m = new HashMap();
					m.put("TMAS_PRODUCT_ID", "0");
					m.put("TMAS_PRODUCT_NAME", "NONE");
					list.add(m);
					bean.setProductList(list);
				}
				if("0".equals(bean.getDepartment())){
					Map m = new HashMap();
					m.put("TMAS_DEPARTMENT_ID", "0");
					m.put("TMAS_DEPARTMENT_NAME", "NONE");
					list.add(m);
					bean.setDepartmentList(list);
				}else{
					bean.setDepartmentList(service.getDepartmentList(bean));
				}
				if("0".equals(bean.getProcess())){
					Map m = new HashMap();
					m.put("PROCESS_ID", "0");
					m.put("PROCESS_NAME", "NONE");
					list.add(m);
					bean.setProcessList(list);
				}else{
					bean.setProcessList(service.getProcessList(bean));
				}

				//bean.setMode("insert");
				bean.setPath("DispForm");
			}
		}
		LOGGER.info("Exit into insertMenuMaster method");
		return "menuMaster";
	}
	public void validateMenu(){
		if(StringUtils.isBlank(bean.getMenuName())){
			addActionError(getText("menumaster.menuName.required"));
		}
		if(StringUtils.isBlank(bean.getMenuUrl())){
			addActionError(getText("menumaster.url.required"));
		}
		if(bean.getUserType().equals("-1")){
			addActionError(getText("menumaster.type.required"));
		}
		if(bean.getProductId().equals("-1")){
			addActionError(getText("menumaster.product.required"));
		}
		if(bean.getDepartment().equals("-1")){
			addActionError(getText("menumaster.department.required"));
		}
		if(bean.getProcess().equals("-1")){
			addActionError(getText("menumaster.process.required"));
		}
		if(StringUtils.isBlank(bean.getOrderby())){
			addActionError(getText("menumaster.order.required"));
		}
		if(StringUtils.isBlank(bean.getInceptionDate())){
			addActionError(getText("menumaster.sdate.required"));
		}else if(val.checkDate(bean.getInceptionDate()).equalsIgnoreCase("invalid")){
			addActionError(getText("menumaster.sdateformate.error"));
		}else{
			Date date=new Date();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if(val.ValidateTwoDates(formatter.format(date),bean.getInceptionDate()).equalsIgnoreCase("Invalid")){
				addActionError(getText("menumaster.sdateGraterThanSys.error"));
			}
		}
		if(StringUtils.isBlank(bean.getExpiryDate())){
			addActionError(getText("menumaster.edater.required"));
		}else if(val.checkDate(bean.getExpiryDate()).equalsIgnoreCase("invalid")){
			addActionError(getText("menumaster.edateformate.error"));
		}else{
			if(val.ValidateTwoDates(bean.getInceptionDate(),bean.getExpiryDate()).equalsIgnoreCase("Invalid")){
				addActionError(getText("menumaster.edateInvalid.error"));
			}
		}
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("menumaster.status.required"));
		}
		if(service.getMenuNameExist(bean)!=0){
			addActionError(getText("menumaster.exists.error"));
		}
	}
	public void validationmenuEdit(){
		if(bean.getUserType().equals("-1")){
			addActionError(getText("menumaster.type.required"));
		}
		if(bean.getProductId().equals("-1")){
			addActionError(getText("menumaster.product.required"));
		}
		if(bean.getDepartment().equals("-1")){
			addActionError(getText("menumaster.department.required"));
		}
		if(bean.getProcess().equals("-1")){
			addActionError(getText("menumaster.process.required"));
		}
		if(bean.getMenuId().equals("-1")){
			addActionError(getText("menumaster.menuName.required"));
		}
	}
	public String department(){
		if("search".equals(bean.getMode())){
			validatesearch();}
		bean.setBranchCode(branchCode);
		bean.setPath("ListDepartmentMaster");
		bean.setDepartmentList(service.getDepartmentMasterList(bean));
		return "department";
	}
	public String editDepartment(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertDepartmentMaster");
		bean.setMode("insert");
		bean.setProductList(service.getProductList(bean));
		return "department";
	}
	public String insertDepartment(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertDepartment");
			service.getEditDepartment(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validatedepartment();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateDepartment(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertDepartment(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				department();
				return "department";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertDepartmentMaster");
		return "department";
	}
	public String productMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();}
		bean.setBranchCode(branchCode);
		bean.setPath("ListProductMaster");
		bean.setProductMasterList(service.getProductMasterList(bean));
		return "productMaster";
	}
	public String editProductMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertProductMaster");
		bean.setMode("insert");
		return "productMaster";
	}
	public String insertProductMaster(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertDepartment");
			service.getEditProductMastert(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateProduct();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateProductMastert(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertProductMastert(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				productMaster();
				return "productMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		//bean.setProductMasterList(service.getProductMastertList(bean));
		bean.setPath("InsertProductMaster");
		return "productMaster";
	}
	public String territoryMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();}
		bean.setBranchCode(branchCode);
		bean.setPath("ListTerritoryMaster");
		bean.setTerritoryList(service.getterritoryList(bean));
		return "territoryMaster";
	}
	public String editterritoryMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertTerritoryMaster");
		bean.setMode("insert");
		return "territoryMaster";
	}
	public String insertterritoryMaster(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertTerritoryMaster");
			service.getEditTerritoryMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateTerritoryMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateterritoryMaster(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertTerritoryMaster(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				territoryMaster();
				return "territoryMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			bean.setPath("InsertTerritoryMaster");
		}
		return "territoryMaster";
	}
	public String categoryZoneMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();}
		bean.setBranchCode(branchCode);
		bean.setPath("ListCategoryMaster");
		bean.setCategoryMasterList(service.getCategoryMasterList(bean));
		return "categoryMaster";
	}
	public String editcategoryZoneMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertCategoryMaster");
		bean.setMode("insert");
		return "categoryMaster";
	}
	public String insertcategoryZoneMaster(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertCategoryMaster");
			service.getEditCategoryZoneMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validatecategoryMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateCategoryZoneMaster(bean);
					if(count==0){
						this.addActionError("update.error");}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertCategoryMaster(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				categoryZoneMaster();
				return "categoryMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			bean.setPath("InsertCategoryMaster");
		}
		return "categoryMaster";
	}


	public String currencyMaster(){
		LOGGER.info("Enter into currencyMaster method");
		if("search".equals(bean.getMode())){
			validatesearch();}
		if(hasActionErrors()){
			LOGGER.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				LOGGER.info(error.next());
			}
			LOGGER.info("##########Validation Message End###########");
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListCurrencyMaster");
		bean.setCurrencyMasterList(service.getCurrencyMasterList(bean));
		LOGGER.info("Exit into currencyMaster method");
		return "currencyMaster";
	}
	public String editCurrencyMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertCurrencyMaster");
		bean.setMode("insert");
		return "currencyMaster";
	}
	public String insertCurrencyMaster(){
		bean.setBranchCode(branchCode);
		bean.setCountryId(countryId);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertCurrencyMaster");
			service.getEditCurrencyMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateCurrencyMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateCurrencyMaster(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertCurrencyMaster(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				currencyMaster();
				return "currencyMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			bean.setPath("InsertCurrencyMaster");
		}
		return "currencyMaster";
	}

	private void validatesearch() {
		if(StringUtils.isBlank(bean.getSearchType())){
			addActionError(getText("error.empty.searchType"));
		}
		else if(StringUtils.isBlank(bean.getSearchValue())){
			addActionError(getText("error.empty.searchvalue"));
		}
	}
	private void validateCurrencyMaster() {
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getCurrencyName())){
			addActionError(getText("error.empty.CurrencyName"));
		}
		else if(service.getCurrencyName(bean)!=0){
			addActionError(getText("error.insertvalidation.currencyname"));
		}
		if(StringUtils.isBlank(bean.getCurrencyShortName())){
			addActionError(getText("error.empty.CurrencyShortName"));
		}
		/*if(StringUtils.isBlank(bean.getCoreAppCode()))
		{
			addActionError(getText("error.empty.CoreAppCode"));
		}
		else if(val.numbervalid(bean.getCoreAppCode()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.getCoreAppCode1"));
		}	*/
		if(service.getCoreAppCode(bean)!=0){
			addActionError(getText("error.AlreadyExists.CoreAppCode1"));
		}
		if(StringUtils.isBlank(bean.getEffectDate())){
			addActionError(getText("error.empty.EffectDate"));
		}
		if(StringUtils.isBlank(bean.getStatus()))
		{
			addActionError(getText("error.empty.Status1"));
		}
		/* if(service.getcombination(bean)!=0){
		addActionError(getText("error.AlreadyExists.ProcessName"));
	}*/
	}
	public String underwriterLimitMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();}
		bean.setBranchCode(branchCode);
		bean.setPath("ListUWLimitMaster");
		bean.setUWLimitMasterList(service.getUWLimitMasterList(bean));
		return "uwLimitMaster";
	}
	public String editUWLImitMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertUWLimitMaster");
		bean.setMode("insert");
		bean.setProductList(service.getProductList(bean));
		bean.setUnderWritterList(new DropDownControllor().getUnderWritterDropDown(branchCode,""));
		return "uwLimitMaster";
	}
	public String insertUWLImitMaster(){
		bean.setBranchCode(branchCode);
		bean.setCountryId(countryId);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertUWLimitMaster");
			bean.setProductList(service.getProductList(bean));
			bean.setUnderWritterList(new DropDownControllor().getUnderWritterDropDown(branchCode,""));
			service.getEditUWLimitMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateUWLimitMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateUWLimitMaster(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertUWLimitMaster(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				underwriterLimitMaster();
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				bean.setPath("InsertUWLimitMaster");
				bean.setProductList(service.getProductList(bean));
				bean.setUnderWritterList(new DropDownControllor().getUnderWritterDropDown(branchCode,""));
			}
		}
		return "uwLimitMaster";
	}

	private void validateUWLimitMaster() {
		if(bean.getProductId().equals("-1")){
			addActionError(getText("uwlimit.product.required"));
		}
		if(bean.getDepartmentId().equals("-1")){
			addActionError(getText("uwlimit.department.required"));
		}
		if(bean.getUnderwritter().equals("-1")){
			addActionError(getText("uwlimit.underwritter.required"));
		}
		if(StringUtils.isBlank(bean.getUwlimit())){
			addActionError(getText("uwlimit.underwritterlimit.required"));
		}
		if(bean.getMode().equalsIgnoreCase("insert")){
			if(service.getUWLimitcombination(bean)!=0){
				addActionError(getText("error.AlreadyExists.combination"));
			}
		}
	}
	private void validatecategoryMaster() {
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getCategoryName())){
			addActionError(getText("error.empty.categoryname"));
		}

		else if(service.getcontCategoryName(bean)!=0){
			addActionError(getText("error.insertvalidation.categoryyname"));
		}
		/*if(StringUtils.isBlank(bean.getCoreAppCode())){
			addActionError(getText("error.empty.coreAppCode"));
		}
		else if(val.numbervalid(bean.getCoreAppCode()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.coreAppCode2"));
		}
		else if(service.getcountCoreAppCode(bean)!=0){
			addActionError(getText("error.empty.coreAppCode1"));
		}*/
		if(StringUtils.isBlank(bean.getStatus()))
		{
			addActionError(getText("error.empty.Status"));
		}
	}

	private void validateTerritoryMaster() {
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getTerritoryCode())){
			addActionError(getText("error.empty.TerritoryCode"));
		}
		if(StringUtils.isBlank(bean.getTerritoryDesc())){
			addActionError(getText("error.empty.territoryname"));
		}

		else if(service.getcontTerritoryDesc(bean)!=0){
			addActionError(getText("error.insertvalidation.territorryname"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.coreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.coreCompanyCode2"));
		}else if(service.getcountCoreCompanyCode(bean)!=0){
			addActionError(getText("error.empty.coreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getStatus()))
		{
			addActionError(getText("error.empty.Status"));
		}
	}


	public void validateprofitcenter(){
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getProfitCenterId())){
			addActionError(getText("error.empty.ProfitCenterId"));
		}else if(service.getCountProfitId(bean)!=0){
			addActionError(getText("error.count.ProfitCenterId"));
		}
		if(StringUtils.isBlank(bean.getProfitCenter())){
			addActionError(getText("error.empty.ProfitCenter"));
		}
		else if(service.getcountProfitCenter(bean)!=0){
			addActionError(getText("error.insertvalidation.ProfitCenter"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompany())){
			addActionError(getText("error.profitcentr.empty.coreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompany()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.profitcentr.DigitFormat.coreCompanyCode2"));
		}else if(service.getprofitcontCoreCompanyCode(bean)!=0){
			addActionError(getText("error.empty.coreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.profitcentr.empty.Active"));
		}
	}


	public void validateProduct(){
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getProductName())){
			addActionError(getText("error.empty.productName"));
		}
		else if(service.getcontProductName(bean)!=0){
			addActionError(getText("error.insertvalidation.productname"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.coreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.coreCompanyCode2"));
		}else if(service.getcontCoreCompanyCode(bean)!=0){
			addActionError(getText("error.empty.coreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getStatus())){
			addActionError(getText("error.empty.Active"));
		}
	}
	public void validatedepartment(){
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getDepartmentName())){
			addActionError(getText("error.empty.departmentname"));
		}
		else if(service.getcontDapartmentName(bean)!=0){
			addActionError(getText("error.insertvalidation.departmentname"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.coreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.coreCompanyCode2"));
		}else if(service.getContCoreCompanyCode(bean)!=0){
			addActionError(getText("error.empty.coreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getProductId())){
			addActionError(getText("error.empty.ProductId"));

		}
		if(StringUtils.isBlank(bean.getStatus()))
		{
			addActionError(getText("error.empty.Status"));
		}
	}
	public String subprofitCenter(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListSubProfitCenter");
		bean.setSubprofitCenterList(service.getSubprofitCenterList(bean));
		return "subprofitCenter";
	}
	public String editSubprofitCenter(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertSubProfitCenter");
		bean.setMode("insert");
		bean.setProductList(service.getProductList(bean));

		return "subprofitCenter";
	}public String insertsubprofitCenter()
	{
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertSubProfitCenter");
			service.getEditSubprofitCenter(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validatesubprofitCenter();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateSubprofitCenter(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){

					int count=service.getInsertSubprofitCenter(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}
					else{

						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				subprofitCenter();
				//bean.setPath("ListSubProfitCenter");
				return "subprofitCenter";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertSubProfitCenter");
		return "subprofitCenter";

	}

	public void validatesubprofitCenter(){
		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getProductId())){
			addActionError(getText("error.empty.productId"));
		}
		if(StringUtils.isBlank(bean.getSubProfitCenter_Id())){
			addActionError(getText("error.empty.subprofitcenterId"));
		}
		else if(service.getContSubProfitCenter_Id(bean)!=0){
			addActionError(getText("error.insert.subprofitcenterId"));
		}
		if(StringUtils.isBlank(bean.getSubProfitCenter())){
			addActionError(getText("error.empty.SubProfitCenterName"));
		}
		else if(service.getContSubProfitCenter(bean)!=0){
			addActionError(getText("error.insertvalidation.SubProfitCenterName"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.CoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.coreCompanyCode2"));
		}
		else if(service.getSContCoreCompanyCode(bean)!=0){
			addActionError(getText("error.insertvalidation.coreCompanyCode1"));
		}*/

		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.empty.Active"));
		}

	}
	public String policyBranch(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListPolicyBranch");
		bean.setPolicyBranchList(service.getPolicyBranchList(bean));
		return "policyBranch";
	}
	public String editpolicyBranch(){
		bean.setPath("InsertPolicyBranch");
		bean.setMode("insert");
		return "policyBranch";
	}
	public String insertpolicyBranch(){
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertPolicyBranch");
			service.getEditPolicyBranch(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validatepolicyBranch();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){

					int count=service.getUpdatePolicyBranch(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));
					}

				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertPolicyBranch(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));
					}

				}
				//bean.setPath("success");
				policyBranch();
				return "policyBranch";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setPath("InsertPolicyBranch");
		return "policyBranch";
	}
	public void validatepolicyBranch(){
		Validation val=new Validation();


		if(StringUtils.isBlank(bean.getPolicybranchname())){
			addActionError(getText("error.empty.PolicyBranchName"));
		}
		else if(service.getContPolicyBranch(bean)!=0){
			addActionError(getText("error.insertvalidation.PolicyBranch"));
		}

		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.policyCoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.policycoreCompanyCode2"));
		}
		else if(service.getPolicyCoreCompanyCode(bean)!=0){
			addActionError(getText("error.insertvalidation.policycoreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.empty.policyActive"));
		}
	}
	public String underwritertMaster(){

		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListUnderWriterMaster");
		bean.setUnderWriterList(service.getUnderWriterList(bean));
		return "underwritertMaster";
	}
	public String editunderwritertMaster(){
		bean.setPath("InsertUnderWriterMaster");
		bean.setMode("insert");
		return "underwritertMaster";
	}
	public String insertunderwritertMaster()
	{
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertUnderWriterMaster");
			service.getEditunderwritertMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validationunderwriterMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateunderwritertMaster(bean);
					if(count==0){

						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}

				}
				else if("insert".equals(bean.getMode())){

					int count=service.getInsertunderwritertMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));
					}

				}
				//bean.setPath("success");
				underwritertMaster();
				return "underwritertMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertUnderWriterMaster");
		return "underwritertMaster";

	}
	public void validationunderwriterMaster(){
		Validation val=new Validation();

		if(StringUtils.isBlank(bean.getUnderWriterId())){
			addActionError(getText("error.isBlank.underwriterId"));
		}
		else if(service.getContUnderWriterId(bean)!=0){
			addActionError(getText("error.insertvalidation.underwriterID"));
		}
		if(StringUtils.isBlank(bean.getUnderWriterName())){
			addActionError(getText("error.isBlank.underwriterName"));
		}
		/*(if(StringUtils.isBlank(bean.getCoreCompanyCode())){

			addActionError(getText("error.empty.underCoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.underCompanyCode2"));
		}
		else if(service.getContunderwriterCoreCompanyCode(bean)!=0){
			addActionError(getText("error.insertvalidation.underwritercoreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.empty.underActive"));
		}

	}
	public String riskGradeMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListTerritoryMaster");
		bean.setRiskGradeList(service.getRiskGradeList(bean));
		return "riskgradeMaster";
	}
	public String editriskgradeMaster(){
		bean.setPath("InsertTerritoryMaster12");
		bean.setMode("insert");
		return "riskgradeMaster";
	}
	public String insertriskgradeMaster(){
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertTerritoryMaster12");
			service.getEditriskgradeMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validationriskgradeMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){

					int count=service.getUpdateriskgradeMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));

					}else{
						this.addActionMessage(getText("update.success"));

					}

				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertriskgradeMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}

				}
				//bean.setPath("success");
				riskGradeMaster();
				return "riskgradeMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertTerritoryMaster12");
		return "riskgradeMaster";

	}
	public void validationriskgradeMaster(){
		Validation val=new Validation();

		if(StringUtils.isBlank(bean.getRiskGradeName())){
			addActionError(getText("error.isBlank.riskGradeName"));
		}
		else if(service.getCountRiskGradeName(bean)!=0){
			addActionError(getText("error.already.riskGradeName"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.riskGradeCoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.riskGradeCompanyCode2"));
		}
		else if(service.getContRiskGradeCoreCompanyCode(bean)!=0){
			addActionError(getText("error.insertvalidation.riskGradercoreCompanyCode1"));
		}*/
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.empty.riskGradeActive"));
		}

	}
	public String countryMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListCategoryMaster");
		bean.setCountryList(service.getCountryList(bean));

		return "countryMaster";
	}
	public String editcountryMaster(){
		bean.setPath("InsertCountryMaster");
		bean.setMode("insert");
		return "countryMaster";
	}
	public String insertcountryMaster(){
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertCountryMaster");
			service.getEditInsertCountryMaster(bean);
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validatecountryMaster();

			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){

					int count=service.getUpdateCountryMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}

				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertCountryMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}

				}
				//bean.setPath("success");
				countryMaster();
				return "countryMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertCountryMaster");
		return "countryMaster";

	}public void validatecountryMaster(){
		Validation val=new Validation();


		if(StringUtils.isBlank(bean.getCountryName())){
			addActionError(getText("error.isBlank.CountryName"));
		}
		else if(service.getContcountryName(bean)!=0){
			addActionError(getText("ContcontrymasterName"));
		}
		if(StringUtils.isBlank(bean.getCountryShortName())){
			addActionError(getText("error.isBlank.CountryShortName"));
		}
		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.countryMasterCoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.countryMasterCompanyCode2"));
		}
		else if(service.getContcountryMasterCoreCompanyCode(bean)!=0){
			addActionError(getText("ContcountryMasterCoreCompanyCode"));
		}*/

		/*if(bean.getEffectDate().equals("dd/mm/yyy"){
	 addActionError(getText("date validation"));
}*/

		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.empty.contryActive"));
		}
		if(StringUtils.isBlank(bean.getEffectDate())){
			addActionError(getText("error.empty.date"));
		}
	}
	public String processMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListProcessMaster");
		bean.setProcessList(service.getProcessMasterList(bean));

		return "processMaster";
	}
	public String editprocessMaster(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertPage");
		bean.setMode("insert");
		bean.setProductList(service.getProductList(bean));
		return "processMaster";
	}
	public String DepartmentdropDown(){
		bean.setBranchCode(branchCode);
		if(bean.getReqform().equals("departmentId")){
			bean.setDepartmentList(service.getDepartmentList(bean));
		}if(bean.getReqform().equals("departmentId1")){
			bean.setDepartmentList(service.getDepartmentList(bean));
		}
		return "adminAjax";
	}
	public String insertprocessMaster(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertPage");
			service.getEditprocessMaster(bean);
			bean.setProductList(service.getProductList(bean));
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateprocessMaster();
			bean.setProductList(service.getProductList(bean));
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getUpdateprocessMaster(bean);
					if(count==0){
						this.addActionError(getText("update.error"));}
					else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getInsertprocessMaster(bean);
					if(count==0){
						this.addActionError(getText("insert.error"));}
					else{
						this.addActionMessage(getText("insert.success"));
					}
				}
				//bean.setPath("success");
				processMaster();
				return "processMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			bean.setPath("InsertPage");
		}
		return "processMaster";
	}
	/*public String addprocessMaster(){


		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertPage");
			service.getEditprocessMaster(bean);
			bean.setProductList(service.getProductList(bean));
			bean.setMode("update");
		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			validateprocessMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getupdateprocessMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}

				}
				else if("insert".equals(bean.getMode())){
					int count=	service.getaddprocessMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}


				}
				bean.setPath("success");
				return "processMaster";
			}
		}
		bean.setProductList(service.getProductList(bean));
		bean.setPath("InsertPage");
		return "processMaster";

	}*/
	public void validateprocessMaster(){

		Validation val=new Validation();
		if(StringUtils.isBlank(bean.getProcessName())){
			addActionError(getText("error.empty.processname"));
		}
		/*else if(service.getProcessName(bean)!=0){
			addActionError(getText("error.insertvalidation.processname"));
		}*/
		if(bean.getProductId().equals("-1")){
			addActionError(getText("error.empty.ProductId1"));
		}
		if(bean.getDepartmentId().equals("-1")){
			addActionError(getText("error.empty.DepartmentId"));
		}
		if(StringUtils.isBlank(bean.getUwlimit()))
		{
			addActionError(getText("error.empty.Uwlimit"));
		}
		if(StringUtils.isBlank(bean.getOrderno())){
			addActionError(getText("error.empty.Orderno"));
		}
		else if(val.numbervalid(bean.getOrderno()).equalsIgnoreCase("INVALID"))
		{
			addActionError(getText("error.DigitFormat.Orderno1"));
		}
		if(StringUtils.isBlank(bean.getStatus()))
		{
			addActionError(getText("error.empty.Status"));
		}
		if(service.getcombination(bean)!=0){
			addActionError(getText("error.ExchangeName.check"));
		}
	}
	public String branchMaster(){
		bean.setCountryId(countryId);
		if("search".equals(bean.getMode())){
			validatesearch();
		}

		bean.setBranchCode(branchCode);
		bean.setPath("ListBranchMaster");
		bean.setBranchList(service.getBranchList(bean));
		return "branchMaster";
	}
	public String editbranchMaster(){

		bean.setCountryId(countryId);
		bean.setBranchCode(branchCode);
		bean.setPath("InsertBranchMaster");
		bean.setMode("insert");
		bean.setBranchCode1(service.getbranchCode1(bean));
		bean.setCountryIdList(service.getCountryIdList(bean));
		bean.setDcurrencyList(service.getDcurrencyList(bean));
		bean.setBaseCurrencyList(service.getBaseCurrencyList(bean));
		return "branchMaster";
	}
	public String getbranchCode(){
		bean.setPath("InsertBranchMaster");
		bean.setMode("insert");
		//service.getbranchCode(bean);
		return "branchMaster";
	}

	public String insertbranchMaster()
	{
		bean.setCountryId(countryId);
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertBranchMaster");
			service.getEditbranchMaster(bean);
			//bean.setProductList(service.getProductList(bean));
			bean.setMode("update");
		}

		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode()) ){
			ValidationbranchMaster();
			if (!hasActionErrors()) {
				if("update".equals(bean.getMode())){
					int count=service.getupdatebranchMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));
					}
				}
				else if("insert".equals(bean.getMode())){
					int count=	service.getinsertbranchMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}
				}
				//bean.setPath("success");
				branchMaster();
				return "branchMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setCountryIdList(service.getCountryIdList(bean));
		bean.setDcurrencyList(service.getDcurrencyList(bean));
		bean.setBaseCurrencyList(service.getBaseCurrencyList(bean));
		bean.setPath("InsertBranchMaster");
		return "branchMaster";

	}
	public void ValidationbranchMaster(){

		Validation val=new Validation();


		if(StringUtils.isBlank(bean.getBranchName())){
			addActionError(getText("error.empty.BranchName"));
		}

		/*if(StringUtils.isBlank(bean.getCoreCompanyCode())){
			addActionError(getText("error.empty.countryMasterCoreCompanyCode"));
		}
		else if(val.numbervalid(bean.getCoreCompanyCode()).equalsIgnoreCase("INVALID")){
			addActionError(getText("error.DiditFormat.countryMasterCompanyCode2"));
		}
		else if(service.getContbranchMasterCoreCompanyCode(bean)!=0){
			addActionError(getText("ContcountryMasterCoreCompanyCode"));
		}*/
		if("0".equals(bean.getBaseCurrencyId())){
			addActionError(getText("error.empty.BaseCurrencyId"));
		}
		if("0".equals(bean.getDescCurrencyId())){
			addActionError(getText("error.empty.getDescCurrencyId"));
		}
		if("0".equals(bean.getCountry())){
			addActionError(getText("error.enmpty.conry"));
		}

		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.isBlank.active"));
		}
	}
	public String cedingMaster(){
		if(StringUtils.isBlank(bean.getCustomerType())){
			bean.setCustomerType("C");
		}
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListCedingMaster");
		bean.setCedingList(service.getCedingList(bean));
		return "cedingMaster";
	}

	public String brokerMaster(){
		bean.setCustomerType("B");
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setPath("ListCedingMaster");
		bean.setCedingList(service.getCedingList(bean));
		return "cedingMaster";
	}

	public String editcedingMaster(){
		bean.setBranchCode(branchCode);
		bean.setMode("insert");
		bean.setPath("InsertCedingMaster");
		bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
		return "cedingMaster";
	}
	public String insertcedingMaster(){
		bean.setBranchCode(branchCode);
		bean.setCountryId(countryId);
		bean.setLoginId(userId);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertCedingMaster");
			service.geteditcedingMaster(bean);
			bean.setMode("update");

		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode())){

			validatecedingMaster();

			if(!hasActionErrors()){
				if("update".equals(bean.getMode())){
					int count=service.getupdatecedingMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getinsertcedingMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}
				}
				//bean.setPath("success");
				if(bean.getCustomerType().equalsIgnoreCase("B")){
					brokerMaster();
				}else if(bean.getCustomerType().equalsIgnoreCase("C")){
					cedingMaster();
				}
				else if(bean.getCustomerType().equalsIgnoreCase("L")){
					cedingMaster();
				}
				return "cedingMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
		bean.setPath("InsertCedingMaster");
		return "cedingMaster";

	}

	public void validatecedingMaster(){
		Validation val=new Validation();


		if("C".equals(bean.getCustomerType())||"B".equals(bean.getCustomerType())||"L".equals(bean.getCustomerType())){
			if("C".equals(bean.getCustomerType()))
			{
				if(StringUtils.isBlank(bean.getCompanyName())){
					addActionError(getText("error.isBlank.CompanyName"));
				}
				if(StringUtils.isBlank(bean.getFirstName())){
					addActionError(getText("error.isBlank.FirstName"));
				}else if(StringUtils.isNumeric(bean.getFirstName())){
					addActionError(getText("error.valid.FirstName"));
				}
			}
			if("L".equals(bean.getCustomerType()))
			{
				if(StringUtils.isBlank(bean.getCompanyName())){
					addActionError(getText("error.isBlank.LeadWriterName"));
				}
				if(StringUtils.isBlank(bean.getFirstName())){
					addActionError(getText("error.isBlank.FirstName"));
				}else if(StringUtils.isNumeric(bean.getFirstName())){
					addActionError(getText("error.valid.FirstName"));
				}
			}
			if("B".equals(bean.getCustomerType()))
			{
				if(bean.getTitle().equals("0")){
					addActionError(getText("error.isBlank.title"));
				}
				if(StringUtils.isBlank(bean.getFirstName())){
					addActionError(getText("error.isBlank.FirstName1"));
				}
				if(StringUtils.isBlank(bean.getDesignation())){
					addActionError(getText("error.isblank.designation"));
				}
			}
			if(StringUtils.isBlank(bean.getInceptionDate()))
			{
				addActionError(getText("error.isBlank.InceptionDate"));
			}
			if(StringUtils.isBlank(bean.getAddress1())){
				addActionError(getText("error.isBlank.Address1"));
			}
			if(StringUtils.isBlank(bean.getAddress2())){
				addActionError(getText("error.isBlank.Address2"));
			}
			if(StringUtils.isBlank(bean.getCity())){
				addActionError(getText("error.isBlank.City"));
			}else if(StringUtils.isNumeric(bean.getCity())){
				addActionError(getText("error.valid.City"));
			}
			if(StringUtils.isBlank(bean.getCountry())){
				addActionError(getText("error.isBlank.country"));
			}
			if(StringUtils.isBlank(bean.getZipcode())){
				addActionError(getText("error.isBlank.Zipcode"));
			}else if(!StringUtils.isNumeric(bean.getZipcode())){
				addActionError(getText("error.valid.Zipcode"));
			}
			if(StringUtils.isBlank(bean.getEmail()))
			{
				addActionError(getText("error.isBlank.Email"));
			}else
			{
				Validation validate=new Validation();
				String validate2 = validate.emailValidate(bean.getEmail());
				if(validate2.equalsIgnoreCase("Invalid"))
				{
					addActionError(getText("error.invalid.Email"));
				}
			}

			if(StringUtils.isBlank(bean.getTelephone())){
				addActionError(getText("error.isBlank.Telephone"));
			}else
			{Validation validate=new Validation();
				String validate1=validate.numbervalid(bean.getTelephone());
				if(validate1.equalsIgnoreCase("Invalid")){
					addActionError(getText("error.invalid.telephone"));
				}
			}
			if(StringUtils.isBlank(bean.getMobile())){
				addActionError(getText("error.isBlank.Mobile"));
			}
			else
			{Validation validate=new Validation();
				String validate1=validate.contactNumber(bean.getMobile());
				if(validate1.equalsIgnoreCase("Invalid")){
					addActionError(getText("error.invalid.Mobile"));
				}
			}
			if(StringUtils.isBlank(bean.getFaxNo())){
				addActionError(getText("error.isBlank.FaxNo"));
			}
			if("B".equals(bean.getCustomerType())){
			/*if(StringUtils.isBlank(bean.getPanNo())){
				addActionError(getText("error.isBlank.PanNo"));
			}*/
				if(StringUtils.isNotBlank(bean.getPanNo())){
					Validation validate=new Validation();
					String validate1=validate.contactNumber(bean.getPanNo());
					if(validate1.equalsIgnoreCase("Invalid")){
						addActionError(getText("error.valid.PanNo"));
					}
				}
				if(StringUtils.isBlank(bean.getIsIndividual())){
					addActionError(getText("error.isBlank.IsIndividual"));
				}
				if(StringUtils.isBlank(bean.getIsNonResident())){
					addActionError(getText("error.isBlank.IsNonResident"));
				}
				if(StringUtils.isBlank(bean.getSpecialRate())){
					addActionError(getText("error.isBlank.SpecialRate"));
				}

			}
			if(StringUtils.isBlank(bean.getActive())){
				addActionError(getText("error.isBlank.AActive"));
			}

		}
	}
	public String openperiodMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setMode(mode);
		bean.setPath("ListOpenMaster");
		bean.setOpenList(service.getOpenList(bean));
		return "openperiodMaster";
	}

	public String editopenperiodMaster(){
		bean.setBranchCode(branchCode);
		bean.setMode("insert");
		bean.setPath("InsertOpenMaster");

		return "openperiodMaster";

	}
	public String insertopenperiodMaster(){

		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertCedingMaster");
			service.getEditopenperiodMaster(bean);
			bean.setMode("update");
		}
		if("update".equals(bean.getMode()) || "insert".equals(bean.getMode())){

			validationopenperiodMaster();

			if(!hasActionErrors()){
				if("update".equals(bean.getMode())){
					int count=service.getupdateopenperiodMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getinsertopenperiodMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}
				}
				//bean.setPath("success");
				openperiodMaster();
				return "openperiodMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}

		bean.setPath("InsertOpenMaster");
		return "openperiodMaster";

	}
	public void validationopenperiodMaster(){
		if(StringUtils.isBlank(bean.getStartDate()))
		{
			addActionError(getText("error.isBlank.StartDate"));
		}else if(val.checkDate(bean.getStartDate()).equalsIgnoreCase("invalid")){
			addActionError(getText("error.invalid.StartDate"));
		}
		if(StringUtils.isBlank(bean.getEndDate()))
		{
			addActionError(getText("error.isBlank.EndDate"));
		}
		else if(val.checkDate(bean.getEndDate()).equalsIgnoreCase("invalid")){
			addActionError(getText("error.invalid.EndDate"));
		}
		if(StringUtils.isBlank(bean.getActive())){
			addActionError(getText("error.isBlank.AActive"));
		}
	}

	public String submenuMaster(){
		if("search".equals(bean.getMode())){
			validatesearch();
		}
		bean.setBranchCode(branchCode);
		bean.setMode(mode);
		bean.setPath("ListSubMenu");
		bean.setSubMenuList(service.getSubMenuList(bean));
		return "submenuMaster";
	}
	public String editsubmenuMaster(){
		bean.setBranchCode(branchCode);
		bean.setMode("insert");
		bean.setPath("DispForm");
		return "submenuMaster";

	}
	public String editallocate(){
		bean.setBranchCode(branchCode);
		bean.setMode("insert");
		bean.setPath("Allocate");
		bean.setProductList(service.getProductList(bean));
		return "submenuMaster";
	}
	public String detailsCresta(){
		if("edit".equalsIgnoreCase(bean.getMode())){
			service.EditCresta(bean,branchCode);
		}
		else if("insert".equalsIgnoreCase(bean.getMode()) || "update".equalsIgnoreCase(bean.getMode())){
			crestaValidation();
			if(!hasActionErrors()){
				bean.setLoginId(userId);
				service.CrestaInsertUpdate(bean,branchCode);
				if("success".equalsIgnoreCase(bean.getCrestaStatus())){
					if( "update".equalsIgnoreCase(bean.getMode())){
						this.addActionMessage(getText("crests.update.success"));
					}
					else if("insert".equalsIgnoreCase(bean.getMode()) ){
						this.addActionMessage(getText("crests.insert.success"));

					}
					bean.setMode("list");
				}
			}
			else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				if( "update".equalsIgnoreCase(bean.getMode())){
					bean.setMode("edit");
				}
				else if("insert".equalsIgnoreCase(bean.getMode()) ){
					bean.setMode("new");
				}
			}
		}
		return "crestaMaster";
	}

	public String removeRow(){
		List<String> sno=new ArrayList<String>();
		List<String> anul=new ArrayList<String>();
		List<String> type=new ArrayList<String>();
		List<String> residental=new ArrayList<String>();
		List<String> rate=new ArrayList<String>();
		List<String> cess1=new ArrayList<String>();
		List<String> cess2=new ArrayList<String>();
		List<String> cess3=new ArrayList<String>();
		List<String> total=new ArrayList<String>();
		bean.getRateSno().remove(bean.getDeleteId());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
		for(int i=0;i<bean.getRateSno().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
		}
		bean.setEditAddRateMaster(list);
		int j=1;
		for(int i=0;i<bean.getRateSno().size();i++){
			int id=Integer.parseInt(bean.getDeleteId());
			if(i<id){
				sno.add(bean.getSectionNo().get(i));
				anul.add(bean.getAnualThresholdLimit().get(i));
				type.add(bean.getTypeId().get(i));
				residental.add(bean.getResidentalStatus().get(i));
				rate.add(bean.getRateOfTds().get(i));
				cess1.add(bean.getCessOne().get(i));
				cess2.add(bean.getCessTwo().get(i));
				cess3.add(bean.getCessThree().get(i));
				total.add(bean.getTotalTds().get(i));
			}
			else{
				if(StringUtils.isNotBlank(bean.getSectionNo().get(j)))
				{
					sno.add(bean.getSectionNo().get(j));
				}
				if(StringUtils.isNotBlank(bean.getAnualThresholdLimit().get(j)))
				{
					anul.add(bean.getAnualThresholdLimit().get(j));
				}
				if(StringUtils.isNotBlank(bean.getTypeId().get(j)))
				{
					type.add(bean.getTypeId().get(j));
				}
				if(StringUtils.isNotBlank(bean.getResidentalStatus().get(j)))
				{
					residental.add(bean.getResidentalStatus().get(j));
				}
				if(StringUtils.isNotBlank(bean.getRateOfTds().get(j)))
				{
					rate.add(bean.getRateOfTds().get(j));
				}

				if(StringUtils.isNotBlank(bean.getCessOne().get(j)))
				{
					cess1.add(bean.getCessOne().get(j));
				}

				if(StringUtils.isNotBlank(bean.getCessTwo().get(j)))
				{
					cess2.add(bean.getCessTwo().get(j));
				}
				if(StringUtils.isNotBlank(bean.getCessThree().get(j)))
				{
					cess3.add(bean.getCessThree().get(j));
				}
				if(StringUtils.isNotBlank(bean.getTotalTds().get(j)))
				{
					total.add(bean.getTotalTds().get(j));
				}
			}
			j++;
		}
		bean.setSectionNo(sno);
		bean.setAnualThresholdLimit(anul);
		bean.setTypeId(type);
		bean.setResidentalStatus(residental);
		bean.setRateOfTds(rate);
		bean.setCessOne(cess1);
		bean.setCessTwo(cess2);
		bean.setCessThree(cess3);
		bean.setTotalTds(total);
		return "rateMaster";
	}

	public String detailsRateMaster(){
		/*if ("delete".equalsIgnoreCase(bean.getFlag())){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
			for(int i=0;i<bean.getRateSno().size();i++){
			Map<String,Object> string = new HashMap<String,Object>();
			string.put("1","1");
			list.add(string);
			}
			bean.setEditAddRateMaster(list);
			bean.getEditAddRateMaster().remove(bean.getDeleteId());
			bean.getRateSno().remove(bean.getDeleteId());
		}
		else{*/
		if("edit".equalsIgnoreCase(bean.getMode())||"view".equalsIgnoreCase(bean.getMode())){
			//service.EditRate(bean,branchCode);
			bean.setEditAddRateMaster(service.getEditAddRateMaster(bean,branchCode));
		}
		if("new".equalsIgnoreCase(bean.getMode())){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
			for(int i=0;i<5;i++){
				Map<String,Object> string = new HashMap<String,Object>();
				string.put("1","1");
				list.add(string);
			}
			bean.setEditAddRateMaster(list);
		}
		else if("insert".equalsIgnoreCase(bean.getMode()) || "update".equalsIgnoreCase(bean.getMode())){
			RateValidation();
			if(bean.getErrorList().size()<=0){
				bean.setLoginId(userId);
				service.RateInsertUpdate(bean,branchCode);
				if("success".equalsIgnoreCase(bean.getCrestaStatus())){
					if( "update".equalsIgnoreCase(bean.getMode())){
						this.addActionMessage(getText("rate.update.success"));
					}
					else if("insert".equalsIgnoreCase(bean.getMode()) ){
						this.addActionMessage(getText("rate.insert.success"));

					}
					bean.setMode("list");
				}
			}
			else{
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(5);
				for(int i=0;i<bean.getRateSno().size();i++){
					Map<String,Object> string = new HashMap<String,Object>();
					string.put("1","1");
					list.add(string);
				}
				bean.setEditAddRateMaster(list);
				if( "update".equalsIgnoreCase(bean.getMode())){
					bean.setMode("edit");
				}
				else if("insert".equalsIgnoreCase(bean.getMode()) ){
					bean.setMode("new");
				}
			}
		}
		else if("delete".equalsIgnoreCase(bean.getMode()) ){
			service.RateMasterDelete(bean,branchCode);
			bean.setMode("list");
			if("success".equalsIgnoreCase(bean.getCrestaStatus())){
				this.addActionMessage(getText("rate.deleted.success"));
			}
		}
		//}
		return "rateMaster";
	}

	private void RateValidation() {
		List<String> list=new ArrayList<String>();
		List<String> list1=new ArrayList<String>();
		for(int i=0;i<bean.getRateSno().size();i++){
			if(StringUtils.isBlank(bean.getSectionNo().get(i)))
			{
				list.add((getText("error.isBlank.secNo",new String[] { String.valueOf(i + 1) })));
			}
			if(StringUtils.isBlank(bean.getAnualThresholdLimit().get(i)))
			{
				list.add((getText("error.isBlank.anual",new String[] { String.valueOf(i + 1) })));
			}
			if(StringUtils.isBlank(bean.getTypeId().get(i)))
			{
				list.add(getText("error.isBlank.tye",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getResidentalStatus().get(i)))
			{
				list.add(getText("error.isBlank.residental",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getRateOfTds().get(i)))
			{
				list.add(getText("error.isBlank.rate",new String[] { String.valueOf(i + 1) }));
			}

			if(StringUtils.isBlank(bean.getCessOne().get(i)))
			{
				list.add(getText("error.isBlank.cess1",new String[] { String.valueOf(i + 1) }));
			}

			if(StringUtils.isBlank(bean.getCessTwo().get(i)))
			{
				list.add(getText("error.isBlank.cess2",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getCessThree().get(i)))
			{
				list.add(getText("error.isBlank.cess3",new String[] { String.valueOf(i + 1) }));
			}

			if(list.size()==8){
				list=new ArrayList<String>();
			}
			else{
				if(StringUtils.isNotBlank(bean.getRateOfTds().get(i))){
					double val =Double.parseDouble(bean.getRateOfTds().get(i).replace(",", ""));
					if(val>100){
						list.add(getText("range.rate",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isNotBlank(bean.getCessOne().get(i))){
					double val =Double.parseDouble(bean.getCessOne().get(i).replace(",", ""));
					if(val>100){
						list.add(getText("range.rate",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isNotBlank(bean.getCessTwo().get(i))){
					double val =Double.parseDouble(bean.getCessTwo().get(i).replace(",", ""));
					if(val>100){
						list.add(getText("range.rate",new String[] { String.valueOf(i + 1) }));
					}
				}
				if(StringUtils.isNotBlank(bean.getCessThree().get(i))){
					double val =Double.parseDouble(bean.getCessThree().get(i).replace(",", ""));
					if(val>100){
						addActionError(getText("range.rate",new String[] { String.valueOf(i + 1) }));
					}
				}
			}

			for(int k=0;k<list.size();k++){
				list1.add(list.get(k)+"\b");
			}
			list=new ArrayList<String>();

		}

		if(StringUtils.isBlank(bean.getEffectiveDate()))
		{
			list.add(getText("error.isBlank.effdate"));
		}
		else {
			String status = service.dateComparition(bean,branchCode);
			if(!"error".equalsIgnoreCase(status)){
				if(val.ValidateTwoDates(dateFormat(status),dateFormat(bean.getEffectiveDate())).equalsIgnoreCase("Invalid")){
					list.add(getText("error.isBlank.effdate.invalid"));
				}
			}
		}
		for(int k=0;k<list.size();k++){
			list1.add(list.get(k));
		}

		bean.setErrorList(list1);

	}
	private void crestaValidation() {
		if(StringUtils.isBlank(bean.getTerritoryId()))
		{
			addActionError(getText("error.isBlank.territoryId"));
		}
		if(StringUtils.isBlank(bean.getCrestaId()))
		{
			addActionError(getText("error.isBlank.crestaId"));
		}
		if(StringUtils.isBlank(bean.getCrestaName()))
		{
			addActionError(getText("error.isBlank.crestaName"));
		}
		if(StringUtils.isBlank(bean.getActive()))
		{
			addActionError(getText("error.isBlank.Active"));
		}
	}
	public String insertsubmenuMaster(){
		bean.setBranchCode(branchCode);

		if("Edit".equals(bean.getMode())){
			bean.setPath("DispForm");
			service.getEditsubmenuMaster(bean);
			bean.setMode("update");

		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode())){
			validatesubmenuMaster();

			if(!hasActionErrors()){
				if("update".equals(bean.getMode())){
					int count=service.getupdatsubmenuMaster(bean);
					if(count==0){
						this.addActionMessage(getText("update.error"));
					}else{
						this.addActionMessage(getText("update.success"));

					}
				}
				else if("insert".equals(bean.getMode())){
					int count=service.getinsertsubmenuMaster(bean);
					if(count==0){
						this.addActionMessage(getText("insert.error"));
					}else{
						this.addActionMessage(getText("insert.success"));

					}
				}
				//bean.setPath("Success");
				submenuMaster();
				return "submenuMaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
		}
		//bean.setProductList(service.getProductList(bean));

		bean.setPath("DispForm");
		return "submenuMaster";

	}
	public void validatesubmenuMaster(){
		if(StringUtils.isBlank(bean.getSubMenuName()))
		{
			addActionError(getText("error.isBlank.SubMenuName"));
		}else if(service.getCountSubMenuName(bean)!=0){
			addActionError(getText("error.valid.SubMenuName"));
		}
		if(StringUtils.isBlank(bean.getSubMenuCode()))
		{
			addActionError(getText("error.isBlank.SubMenuCode"));
		}else if(service.getCountSubMenuCode(bean)!=0){
			addActionError(getText("error.valid.SubMenuCode"));
		}
		if(StringUtils.isBlank(bean.getActive()))
		{
			addActionError(getText("error.isBlank.Active"));
		}

	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}

	public void setSubmenuinfo(Map submenuinfo) {
		this.submenuinfo = submenuinfo;
	}

	public Map getSubmenuinfo() {
		return submenuinfo;
	}
	public String clientMaster1(){
		bean.setBranchCode(branchCode);
		bean.setPath("InsertClientMaster");
		bean.setBankCurrencyList(service.getbankCurrencyList(bean));
		bean.setTdsTypeList(service.gettdsTypeList(bean,"36"));
		bean.setClientList(service.gettdsTypeList(bean,"38"));
		bean.setDepartList(service.gettdsTypeList(bean,"50"));
		bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
		bean.setBroGroupList(service.getbroGroupList(bean));
		SimpleDateFormat sformat=new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		bean.setInceptionDate(sformat.format(date));
		if(bean.getCurrencyList()==null){
			List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>(3);
			for(int i=0;i<3;i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
				doubleMap.put("one",new Double(1.0));
				creList.add(doubleMap);
				currencyList1.add(creList);
			}
			bean.setCurrencyList(currencyList1);
		}
		if(bean.getContactList()==null){
			List<List<Map<String,Object>>> contactList1=new ArrayList<List<Map<String,Object>>>(3);
			for(int i=0;i<3;i++){
				Map<String,Object> doubleMap = new HashMap<String,Object>();
				List<Map<String,Object>> cntList=new ArrayList<Map<String,Object>>();
				doubleMap.put("one",new Double(1.0));
				cntList.add(doubleMap);
				contactList1.add(cntList);
			}
			bean.setContactList(contactList1);
		}
		return "clientmaster";
	}
	public String clientMaster(){
		bean.setPath("List");
		bean.setClientPersonalList(service.getclientPersonalList(bean,branchCode));
		return "clientmaster";
	}



	public String insertClientMaster(){
		bean.setLoginId(userId);
		bean.setBranchCode(branchCode);
		if("Edit".equals(bean.getMode())){
			bean.setPath("InsertClientMaster");
			bean.setBankCurrencyList(service.getbankCurrencyList(bean));
			bean.setTdsTypeList(service.gettdsTypeList(bean,"36"));
			bean.setClientList(service.gettdsTypeList(bean,"38"));
			bean.setDepartList(service.gettdsTypeList(bean,"50"));
			bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
			bean.setBroGroupList(service.getbroGroupList(bean));
			if(bean.getCurrencyList()==null){
				List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>(3);
				for(int i=0;i<3;i++){
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					creList.add(doubleMap);
					currencyList1.add(creList);
				}
				bean.setCurrencyList(currencyList1);
			}
			if(bean.getContactList()==null){
				List<List<Map<String,Object>>> contactList1=new ArrayList<List<Map<String,Object>>>(3);
				for(int i=0;i<3;i++){
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> cntList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					cntList.add(doubleMap);
					contactList1.add(cntList);
				}
				bean.setContactList(contactList1);
			}
			service.getEditClientMaster(bean,branchCode);
			//bean.setMode("update");

		}
		else if("update".equals(bean.getMode()) || "insert".equals(bean.getMode())){
			validateClientMaster();
			if(!hasActionErrors() ){
				if("update".equals(bean.getMode())){
					service.getupdateClientMaster(bean,branchCode);
					this.addActionMessage(getText("client.update.success"));
				}
				else if("insert".equals(bean.getMode())){
					service.getinsertClientMaster(bean,branchCode);
					this.addActionMessage(getText("client.insert.success"));
				}
				clientMaster();
				return "clientmaster";
			}else{
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
			}
			}
		if(hasActionErrors()){
			bean.setPath("InsertClientMaster");
			bean.setBankCurrencyList(service.getbankCurrencyList(bean));
			bean.setTdsTypeList(service.gettdsTypeList(bean,"36"));
			bean.setClientList(service.gettdsTypeList(bean,"38"));
			bean.setDepartList(service.gettdsTypeList(bean,"50"));
			bean.setBrokercountryList(new DropDownControllor().getCountryDropDown(branchCode));
			bean.setBroGroupList(service.getbroGroupList(bean));
			if(bean.getCurrencyList()==null){
				List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>(3);
				for(int i=0;i<3;i++){
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					creList.add(doubleMap);
					currencyList1.add(creList);
				}
				bean.setCurrencyList(currencyList1);
			}
			if(bean.getContactList()==null){
				List<List<Map<String,Object>>> contactList1=new ArrayList<List<Map<String,Object>>>(3);
				for(int i=0;i<3;i++){
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> cntList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					cntList.add(doubleMap);
					contactList1.add(cntList);
				}
				bean.setContactList(contactList1);
			}
		}

		bean.setPath("InsertClientMaster");
		return "clientmaster";
	}
	private void validateClientMaster() {
		if(StringUtils.isBlank(bean.getClientType())){
			addActionError(getText("error.isBlank.clienttype"));
		}
		if(StringUtils.isBlank(bean.getInceptionDate())){
			addActionError(getText("error.isBlank.inceptiondate"));
		}if("C".equalsIgnoreCase(bean.getClientType())){
			if(StringUtils.isBlank(bean.getFirstName())){
				addActionError(getText("error.isBlank.cedingname"));
			}
		}else if("B".equalsIgnoreCase(bean.getClientType())){
			if(StringUtils.isBlank(bean.getFirstName())){
				addActionError(getText("error.isBlank.firstname"));
			}
		}else if("L".equalsIgnoreCase(bean.getClientType())){
			if(StringUtils.isBlank(bean.getFirstName())){
				addActionError(getText("error.isBlank.leadername"));
			}}
		if(StringUtils.isBlank(bean.getBroGroup())){
			//addActionError(getText("error.isBlank.broGroup"));
		}
		if(StringUtils.isBlank(bean.getAddress1())){
			addActionError(getText("error.isBlank.Address1"));
		}if(StringUtils.isBlank(bean.getAddress2())){
			//addActionError(getText("error.isBlank.Address2"));
		}if(StringUtils.isBlank(bean.getCity())){
			addActionError(getText("error.isBlank.City"));
		}if(StringUtils.isBlank(bean.getCountry())){
			addActionError(getText("error.isBlank.Country"));
		}if(StringUtils.isBlank(bean.getZipcode())){
			addActionError(getText("error.isBlank.Zipcode"));
		}if(StringUtils.isBlank(bean.getEmail())){
			//addActionError(getText("error.isBlank.Email"));
		}else {
			Validation validate=new Validation();
			String validate2 = validate.emailValidate(bean.getEmail());
			if(validate2.equalsIgnoreCase("Invalid")){
				addActionError(getText("error.invalid.Email"));
			}
		}if(StringUtils.isBlank(bean.getTelephone())){
			//addActionError(getText("error.isBlank.Telephone"));
		}if(StringUtils.isBlank(bean.getMobile())){
			//addActionError(getText("error.isBlank.Mobile"));
		}else if(bean.getMobile().length()<10){
			//addActionError(getText("error.mobilenumber"));
		}if(StringUtils.isBlank(bean.getFaxNo())){
			//addActionError(getText("error.isBlank.FaxNo"));
		}
		List<String> list=new ArrayList<String>();
		for(int i=0;i<bean.getBankSNo().size();i++){
			if(StringUtils.isBlank(bean.getBankCurrency().get(i))){
				list.add(getText("error.bankcurrency") +" in Bank Details row number  "+(i+1));
			}
			if(StringUtils.isBlank(bean.getBankaccountnumber().get(i))){
				list.add(getText("error.bankaccountnumber")+" in Bank Details row number "+(i+1));
			}
			if(StringUtils.isBlank(bean.getBankname().get(i))){
				list.add(getText("error.bankname")+" in Bank Details row number "+(i+1));
			}
			if(StringUtils.isBlank(bean.getAccountname().get(i))){
				list.add(getText("error.accountname")+" in Bank Details row number "+(i+1));
			}
			if(StringUtils.isBlank(bean.getSwiftcode().get(i))){
				list.add(getText("error.swiftcode")+" in Bank Details row number "+(i+1));
			
			}
			if(StringUtils.isBlank(bean.getBankRemarks().get(i))){
				//list.add(getText("error.bankremarks")+" in Bank Details row number "+(i+1));
			}
			//else if(StringUtils.isNotBlank(bean.getSwiftcode().get(i)) || StringUtils.isNotBlank(bean.getIfsccode().get(i))){
				if(list.size()==5){
					list=new ArrayList<String>();
				}
				else{
					if(bean.getBankCurrency().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getBankaccountnumber().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getBankname().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getAccountname().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getSwiftcode().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getCorespondentbank().get(i).contains(",")){
						list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
					if(bean.getBankRemarks().get(i).contains(",")){
						//list.add(getText("error.comma")+" in Bank Details row number "+(i+1));
					}
				//}
			}
			for(int k=0;k<list.size();k++){
				addActionError(list.get(k));
			}
			list=new ArrayList<String>();
			

		}

		List<String> cdList=new ArrayList<String>();
		List<String> cdList1=new ArrayList<String>();
		for(int i=0;i<bean.getContactSNo().size();i++){
			if(StringUtils.isBlank(bean.getDepartmentCD().get(i))){
				cdList.add(getText("error.Blank.department")+" in Contact Details row number "+(i+1));
			}
			if(StringUtils.isBlank(bean.getEmailaddress().get(i)) && StringUtils.isBlank(bean.getTelephonenumber().get(i)) && StringUtils.isBlank(bean.getFaxnumber().get(i))){
				cdList.add(getText("error.Blank.emailaddress")+" in Contact Details row number "+(i+1));
			}
			
			if(cdList.size()==2){
				cdList=new ArrayList<String>();
			}else{
				if(bean.getDepartmentCD().get(i).contains(",")){
					cdList.add(getText("error.comma")+" in Contact Details row number "+(i+1));
				}
				if(bean.getEmailaddress().get(i).contains(",")){
					cdList.add(getText("error.comma")+" in Contact Details row number "+(i+1));
				}else if(StringUtils.isNotBlank(bean.getEmailaddress().get(i))){
					Validation validate=new Validation();
					String validate2 = validate.emailValidate(bean.getEmailaddress().get(i));
					if(validate2.equalsIgnoreCase("Invalid")){
						cdList.add(getText("error.invalid.Email")+" in Contact Details row number "+(i+1));
					}
				}
				if(bean.getTelephonenumber().get(i).contains(",")){
					cdList.add(getText("error.comma")+" in Contact Details row number "+(i+1));
				}
				if(bean.getFaxnumber().get(i).contains(",")){
					cdList.add(getText("error.comma")+" in Contact Details row number "+(i+1));
				}

				for(int k=0;k<cdList.size();k++){
					addActionError(cdList.get(k));
				}
				cdList=new ArrayList<String>();
			}

		}
if(StringUtils.isNotBlank(bean.getCountry()) && "356".equalsIgnoreCase(bean.getCountry())) {
	if("RI02".equalsIgnoreCase(sourceId)){
	if (StringUtils.isBlank(bean.getPanNo())) {
		addActionError(getText("error.isBlank.brokerPanNo"));
	} else if (bean.getPanNo().length() < 10) {
		addActionError(getText("error.brokerpanlength"));
	}
	if (StringUtils.isBlank(bean.getIsIndividual())) {
		addActionError(getText("error.isBlank.IsIndividual"));
	}
	if (StringUtils.isBlank(bean.getIsNonResident())) {
		addActionError(getText("error.isBlank.IsNonResident"));
	}
	if (StringUtils.isBlank(bean.getSpecialRate())) {
		//addActionError(getText("error.isBlank.SpecialRate"));
	}
	}
	
	if (StringUtils.isBlank(bean.getActive())) {
		addActionError(getText("error.isBlank.Active"));
	}
	if (StringUtils.isBlank(bean.getClientRemarks())) {
		//addActionError(getText("error.isBlank.remarks"));
	}
	if (StringUtils.isBlank(bean.getRegNo())) {
		if("RI02".equalsIgnoreCase(sourceId)){
			addActionError(getText("error.regno"));
		}else{
			addActionError(getText("error.regno.ira"));
		}
		
	}
	if (StringUtils.isBlank(bean.getCinNo())) {
		addActionError(getText("error.cinno"));	
		}
}
	if(StringUtils.isNotBlank(bean.getRating())){
		if(StringUtils.isBlank(bean.getRatingAgency())){
			addActionError(getText("error.rate.agentcy"));	
		}
		if(StringUtils.isBlank(bean.getLastRating())){
			addActionError(getText("error.last.rating"));
		}
		if (!bean.getInceptionDate().equalsIgnoreCase("")&& !bean.getLastRating().equalsIgnoreCase("")) {
			if (Validation.ValidateTwo(bean.getLastRating(),bean.getInceptionDate()).equalsIgnoreCase("Invalid")) {
				addActionError(getText("error.lastrating.invalid"));
			}
		}
	}
}
	public String docUpload(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>() ;
		if("addnew".equalsIgnoreCase(bean.getMode())){
			bean.setEndIndex(String.valueOf(Integer.parseInt(bean.getEndIndex())+1));
			bean.setStartValue(String.valueOf(list.size()+1));
			List<Integer> docList=new ArrayList<Integer>();
			for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
				docList.add(i);
			}
			bean.setDocuList(docList);
		}
		else{
			if(!"newDoc".equalsIgnoreCase(bean.getMode())){
				bean.setDocList(service.getDocList(bean,branchCode));
			}
			else{
				bean.setFlag("new");
			}
			if( bean.getDocList().size()<=0){
				bean.setEndIndex("1");
				bean.setStartValue(String.valueOf(list.size()+1));
				bean.setStartIndex("0");
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
					docList.add(i);
				}
				bean.setDocuList(docList);
			}
			else{
				bean.setMode("list");
			}
		}

		return "admindocupload";
	}
	public String uploadDocument() {
		try {
			validateUploadDocument();
			if(!hasActionErrors()) {
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				int size=Integer.parseInt(bean.getEndIndex());
				bean.setEndIndex(size+"");
				bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
				String result=service.doUpload(bean,branchCode,userId,bean.getUpload(),bean.getUploadFileName());
				if(result.length()<=0&&"".equals(result)){
					addActionMessage("Success");
					bean.setMode("list");
					list=service.getDocList(bean,branchCode);
					bean.setDocList(list);
				}else{
					List<Integer> docList=new ArrayList<Integer>();
					for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
						docList.add(i);
					bean.setDocuList(docList);
					this.addActionError(result);

				}
			}else {
				LOGGER.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					LOGGER.info(error.next());
				}
				LOGGER.info("##########Validation Message End###########");
				List<Integer> docList=new ArrayList<Integer>();
				for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++)
					docList.add(i);
				bean.setDocuList(docList);

			}
		}
		catch (Exception exception) {
		}
		return "admindocupload";
	}
	private void validateUploadDocument() {
		int emptyCount=0;
		String emptyRows="";
		boolean empty=false;
		int size=Integer.parseInt(bean.getEndIndex());

		for(int i=(Integer.parseInt(bean.getStartIndex()));i<Integer.parseInt(bean.getEndIndex());i++) {
			if(bean.getUpload()==null){
				this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
			}else{

				if( bean.getUpload().size()>i){

					if(StringUtils.isBlank(bean.getDocDesc().get(i))&&( bean.getUpload().get(i).length() >0)){
						this.addActionError(getText("upload.docDec.required",new String[]{String.valueOf(i+1)}));
					}
					if(bean.getUpload().get(i).length() <= 0&&(StringUtils.isNotBlank(bean.getDocDesc().get(i)))){
						this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
					}
					if(StringUtils.isBlank(bean.getDocDesc().get(i))&&"0".equals( bean.getUpload().get(i).length() <= 0))
					{
						emptyCount++;
					}
					if(!empty&&StringUtils.isBlank(bean.getDocDesc().get(i))&& bean.getUpload().get(i).length() <= 0)
					{
						if(size==Integer.parseInt(bean.getEndIndex()))
							size=i;
						emptyRows+=(i+1)+",";

					}else if(emptyRows.length()>0)
					{
						empty=true;
					}
					if(empty) {
						this.addActionError(getText("upload.emptyRows.Required",new String[]{emptyRows.substring(0,emptyRows.lastIndexOf(","))}));
					}
					if((Integer.parseInt(bean.getEndIndex())-Integer.parseInt(bean.getStartIndex()))==emptyCount) {
						this.addActionError(getText("upload.oneRow.Required"));
					}
				}
				else{
					this.addActionError(getText("upload.file.required",new String[]{String.valueOf(i+1)}));
				}
			}
		}
	}

	public void downloadDocument() {
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			OutputStream oout = response.getOutputStream();
			response.setHeader("Content-disposition","attachment;filename="+bean.getOrgFileName());
			response.setContentType("application/binary");
			String filePath=ServletActionContext.getServletContext().getRealPath("/")+"documents/";
			filePath += "PER"+"/"+bean.getCustomerId()+"/";
			FileInputStream fis = new FileInputStream(filePath+bean.getOurFileName());
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = fis.read(buf)) != -1) {
				oout.write(buf, 0, bytesRead);
			}
			oout.close();
			fis.close();

		}
		catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
		}
	}
	public String previewDocument() {
		String forward = "pdfReport";
		try {
			bean.setFileName("documents/"+"PER"+"/"+bean.getCustomerId()+"/"+bean.getOurFileName());
			if(StringUtils.isNotBlank(bean.getFileName())) {
				String filePath=ServletActionContext.getServletContext().getRealPath(bean.getFileName());
				File file=new File(filePath);
				if(file.exists()){
					forward = "viewPDF";
				}
			}
		}
		catch(Exception exception) {
			LOGGER.debug(""+exception);
		}
		return forward;

	}
	public String deleteDocument() {
		try {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			String userId = session.get("UserId").toString();
			if(StringUtils.isNotBlank(bean.getProductId())&&bean.getProductId().equalsIgnoreCase("")){
				bean.setProductId(session.get("mfrid").toString());
			}
			bean.setFilePath(ServletActionContext.getServletContext().getRealPath("/")+"documents/");
			String result=service.doDeleteDocDetails(bean,userId,branchCode);
			if(result.length()<=0&&"".equals(result)) {
				if("document".equalsIgnoreCase(bean.getType())){
					list=service.getDocList(bean,branchCode);
				}
				else{
					list=service.getDocList(bean,branchCode);
				}
				this.addActionMessage(getText("msg.DeleteSucess"));
				if(list.size()>0) {
					bean.setDocList(list);
					bean.setMode("list");
				}
				else{
					bean.setMode("newDoc");
					bean.setEndIndex("1");
					bean.setStartValue(String.valueOf(list.size()+1));
					bean.setStartIndex("0");
					List<Integer> docList=new ArrayList<Integer>();
					for(int i=Integer.parseInt(bean.getStartIndex());i<Integer.parseInt(bean.getEndIndex());i++){
						docList.add(i);
					}
					bean.setDocuList(docList);
					bean.setFlag("client");
				}
			}
			else {
				this.addActionError(result);
			}
		}
		catch (Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return "admindocupload";
	}
	
	public String removeRowBank(){
		
		List<String> bankCurrency=new ArrayList<String>();
		List<String> bankaccountnumber=new ArrayList<String>();
		List<String> bankname=new ArrayList<String>();
		List<String> accountname=new ArrayList<String>();
		List<String> swiftcode=new ArrayList<String>();
		List<String> corespondentbank=new ArrayList<String>();
		List<String> bankRemarks=new ArrayList<String>();
		
		bean.getBankSNo().remove(bean.getDeleteId());
		
		
		List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>(5);
		for(int i=0;i<bean.getBankSNo().size();i++){
			Map<String,Object> doubleMap = new HashMap<String,Object>();
			List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
			doubleMap.put("one",new Double(1.0));
			creList.add(doubleMap);
			currencyList1.add(creList);
		}
		int j=1;
		
		for(int k=0;k<bean.getBankSNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				bankCurrency.add(bean.getBankCurrency().get(k));
				bankaccountnumber.add(bean.getBankaccountnumber().get(k));
				bankname.add(bean.getBankname().get(k));
				accountname.add(bean.getAccountname().get(k));
				swiftcode.add(bean.getSwiftcode().get(k));
				corespondentbank.add(bean.getCorespondentbank().get(k));
				bankRemarks.add(bean.getBankRemarks().get(k));
			}
			else{
			if(StringUtils.isNotBlank(bean.getBankCurrency().get(j))){
				bankCurrency.add(bean.getBankCurrency().get(j));
			}
			if(StringUtils.isNotBlank(bean.getBankaccountnumber().get(j))){
				bankaccountnumber.add(bean.getBankaccountnumber().get(j));
			}
			if(StringUtils.isNotBlank(bean.getBankname().get(j))){
				bankname.add(bean.getBankname().get(j));	
						}
			if(StringUtils.isNotBlank(bean.getAccountname().get(j))){
				accountname.add(bean.getAccountname().get(j));
			}
			if(StringUtils.isNotBlank(bean.getSwiftcode().get(j))){
				swiftcode.add(bean.getAccountname().get(j));
			}
			if(StringUtils.isNotBlank(bean.getCorespondentbank().get(j))){
				corespondentbank.add(bean.getAccountname().get(j));
			}
			if(StringUtils.isNotBlank(bean.getBankRemarks().get(j))){
				bankRemarks.add(bean.getBankRemarks().get(j));
			}
			
			}
			j++;
		}
		bean.setBankCurrency(bankCurrency);
		bean.setBankaccountnumber(bankaccountnumber);
		bean.setBankname(bankname);
		bean.setAccountname(accountname);
		bean.setSwiftcode(swiftcode);
		bean.setCorespondentbank(corespondentbank);
		bean.setBankRemarks(bankRemarks);
		bean.setCurrencyList(currencyList1);
		bean.setBankCurrencyList(service.getbankCurrencyList(bean));
		return "dropdownajax";
	}
public String removeRowcontact(){
		
		List<String> departmentCD=new ArrayList<String>();
		List<String> subdepartmentCD=new ArrayList<String>();
		List<String> emailaddress=new ArrayList<String>();
		List<String> telephonenumber=new ArrayList<String>();
		List<String> faxnumber=new ArrayList<String>();
		
		bean.getContactSNo().remove(bean.getDeleteId());
		
		
		List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>(5);
		for(int i=0;i<bean.getContactSNo().size();i++){
			Map<String,Object> doubleMap = new HashMap<String,Object>();
			List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
			doubleMap.put("one",new Double(1.0));
			creList.add(doubleMap);
			currencyList1.add(creList);
		}
		int j=1;
		
		for(int k=0;k<bean.getContactSNo().size();k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				departmentCD.add(bean.getDepartmentCD().get(k));
				subdepartmentCD.add(bean.getSubdepartmentCD().get(k));
				emailaddress.add(bean.getEmailaddress().get(k));
				telephonenumber.add(bean.getTelephonenumber().get(k));
				faxnumber.add(bean.getFaxnumber().get(k));
			}
			else{
			if(StringUtils.isNotBlank(bean.getDepartmentCD().get(j))){
				departmentCD.add(bean.getDepartmentCD().get(j));
			}
			if(StringUtils.isNotBlank(bean.getSubdepartmentCD().get(j))){
				subdepartmentCD.add(bean.getSubdepartmentCD().get(j));	
						}
			if(StringUtils.isNotBlank(bean.getEmailaddress().get(j))){
				emailaddress.add(bean.getEmailaddress().get(j));
			}
			if(StringUtils.isNotBlank(bean.getTelephonenumber().get(j))){
				telephonenumber.add(bean.getTelephonenumber().get(j));
			}
			if(StringUtils.isNotBlank(bean.getFaxnumber().get(j))){
				faxnumber.add(bean.getFaxnumber().get(j));
			}
			
			
			}
			j++;
		}
		bean.setDepartmentCD(departmentCD);
		bean.setSubdepartmentCD(subdepartmentCD);
		bean.setEmailaddress(emailaddress);
		bean.setTelephonenumber(telephonenumber);
		bean.setFaxnumber(faxnumber);
		bean.setContactList(currencyList1);
		bean.setDepartList(service.gettdsTypeList(bean,"50"));
		return "dropdownajax";
	}


}

