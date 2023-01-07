package com.maan.common.login;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import com.maan.bean.CommonBean;
import com.maan.common.util.CSVConverter;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class LogInAction extends ActionSupport  implements SessionAware, ServletRequestAware, ModelDriven<CommonBean>{
	final Logger logger=LogUtil.getLogger(LogInAction.class);
	private static final long serialVersionUID = 10001L;
	private LogInService service=new LogInService();
	private CommonBean bean=new CommonBean();
	private Map<String, Object> session = null;
	private HttpServletRequest request = null;
	private String appId="51";
	private FileEncryption enc=new FileEncryption();
	final HttpServletRequest request1 = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	ServletContext context = request1.getSession().getServletContext();
	private  String DEC_FILE=context.getRealPath("/documents/SEC/dec.txt");
	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
	public void setServletRequest(HttpServletRequest hsr) {
		this.request = hsr;
	}
	public String homepage(){
		return "homepage";
	}
	public String page(){
		return "page";
	}

	public String submit() throws FileNotFoundException {

		String result="page";
		logger.info("Enter==>submit()");
		validatelogin();
		Map <String, String>rs1=service.getMailDetails(appId);
		String pwdlen[]=rs1.get("PWD_LEN").split("-");
		session.put("expiTime", rs1.get("EXP_TIME"));
		session.put("expiDate", rs1.get("EXP_DATE"));
		session.put("pwdCount", rs1.get("PWD_CNT"));
		session.put("pwdLenMin", pwdlen[0]);
		session.put("pwdLenMax", pwdlen[1]);
		session.put("appID", appId);
		if (getActionErrors().size() <= 0) {
			String[] statuses=service.validateUser(bean.getLoginId(), bean.getPwd(),"",(String)session.get("appID"),(String) session.get("pwdCount"), request.getRealPath("/"));//===>check method through UserId, Password
			String status1=statuses[0];
			if(status1==null){

				String userStatus=statuses[1];
				boolean pass=service.checkPasswordChange(bean.getLoginId(), userStatus,(String) session.get("expiTime"),(String) session.get("expiDate"),(String) session.get("appID"));
				if(pass){
					try {
						enc.main(null);
						
						File file = new File(DEC_FILE);
						String contents = new Scanner(file).useDelimiter("\\Z").next();
						int res = service.datevalidation(contents,bean);
						if(res<0){
							addActionError(getText("error.date.expired"));
						}
						else{
							bean.setExpiryCount(Integer.toString(res));
						}
					    //file.delete();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					if(!hasActionErrors()){
						if(service.getValidLoginStatus(bean)>0){
							result="invalidate";
						}
						else{
							List userDetails=service.getUserInfo(bean.getLoginId(), (String)session.get("appID"));
							if (userDetails == null || userDetails.size()<=0)
								addActionError(getText("E110"));
							else{
								if("changepwd".equalsIgnoreCase(bean.getStatus())){
									bean.setStatus("changepwd");
									result = "changePWD";
								}else{
									//service.insertSessionInfo(bean.getLoginId(), request.getSession(false).getId(), service.getClientIpAddr(request));
									if (userDetails != null && userDetails.size()>0){
										
										Map<String, String> navMap = new HashMap<String, String>();
										navMap.put("LIST_KEY","HOME");
										navMap.put("LIST_VALUE","welcomeHome.action");
										
										List<Map<String,String>> navList = new ArrayList<Map<String,String>>();
										navList.add(navMap);
										session.put("navList", navList);
										Map rs=(Map)userDetails.get(0);
										String branchCode = rs.get("BRANCH_CODE").toString();
										service.insertSessionInfo(bean.getLoginId(), request.getSession(false).getId(), service.getClientIpAddr(request),branchCode);
										session.put("UserId", rs.get("login_id"));
										session.put("UserName", rs.get("username"));
										session.put("UserType", rs.get("USERTYPE"));
										session.put("appId", rs.get("appId"));
										session.put("startDate", rs.get("STARTDATE"));
										session.put("endDate", rs.get("ENDDATE"));
										session.put("BRANCH_CODE",rs.get("BRANCH_CODE"));
										session.put("ATTACHED_UW",rs.get("ATTACHED_UW"));
										
										session.put("ProductList",service.getProductList(branchCode));
										session.put("countryId",service.getCountryId(branchCode));
										List<Map<String,Object>>companyInfo=new DropDownControllor().getCompanyInfo(branchCode);
										if(companyInfo!=null && companyInfo.size()>0) {
											Map<String,Object>map=companyInfo.get(0);
											session.put("SOURCE_CODE", map.get("COMPANY_CODE"));
											session.put("COMPANY_NAME", map.get("COMPANY_NAME"));
											session.put("EMAIL", map.get("EMAIL"));
											session.put("ADDRESS1", map.get("ADDRESS1"));
											session.put("COUNTRY", map.get("COUNTRY"));
											session.put("WEB_SITE", map.get("WEB_SITE"));
											session.put("MOBILE", map.get("MOBILE"));
											session.put("HEADER_LOGO", map.get("HEADER_LOGO"));
											session.put("MAIN_LOGO", map.get("MAIN_LOGO"));
										}
										if("admin".equalsIgnoreCase((String)rs.get("USERTYPE"))){
											session.put("MenuList",service.getMenuList((String)rs.get("login_id")));
											result="adminHome";
										}else{
											result="home";
										}
									}
								}
							}
						}
					}
					else{
						result = INPUT;
					}
				}else{
					bean.setStatus("changepwd");
					result = "changePWD";
				}
			}
			else if("changepwd".equalsIgnoreCase(status1)){
				bean.setStatus("changepwd");
				result = "changePWD";
			}
			else if("changePwd".equalsIgnoreCase(bean.getStatus())){
				addActionError(status1);
				result = "changePWD";
			}
			else if("R".equalsIgnoreCase(status1)){
				addActionError(getText("E128", new String[]{statuses[1]}));
				result = INPUT;
			}
			else{
				addActionError(status1);
				result = INPUT;
			}

			// }
		}
		else if("changePwd".equalsIgnoreCase(bean.getStatus())){
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			result = "changePWD";
		}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			result = INPUT;
		}
		if("changePWD".equals(result)){
			bean.setPwdMsg(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
		}
		logger.info("Login Submit Method - Exit");
		System.gc();
		return result;

	}

	public String pwdChange() {
		logger.info("Enter==>pwdChange()");
		String status1;
		bean.setStatus("changepwd");
		String returnResult = "changePWD";
		if (bean.getNewpwd() == null || "".equals(bean.getNewpwd().trim())) {
			addActionError(getText("E101"));
		}if (bean.getRepwd() == null || "".equals(bean.getRepwd().trim())) {
			addActionError(getText("E102"));	//Please Enter Confirm Password
		}else if (!bean.getRepwd().equals(bean.getRepwd())) {
			addActionError(getText("E103"));	//Both Passwords are Different
		}else if(!validPassword((bean.getRepwd()))){
			addActionError(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{(String)session.get("pwdLenMin"), (String)session.get("pwdLenMax")}));	//New Password Should contain one Uppercase, one lowercase, one number & one special character(@#$%)
		}
		if (getActionErrors().size() <= 0) {
			status1=service.changePassword(bean.getLoginId(),bean.getRepwd(), appId);
			if(status1==null){
				bean.setStatus("success");
			}
			else{
				addActionError(status1);
			}
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}
		logger.info("Exit==>pwdChange()");
		return returnResult;

	}
	public String pwdForgot(){
		logger.info("Enter==>pwdForgot()");
		if (bean.getLoginId() == null || "".equals(bean.getLoginId().trim())) {
			addActionError(getText("E105"));	//Please Enter UserName
		}if (bean.getMailId() == null || "".equals(bean.getMailId().trim())) {
			addActionError(getText("E106"));	//Please Enter Email Id
		} if (StringUtils.isEmpty(bean.getMailId()) || !validateEmail((bean.getMailId()))) {
			addActionError(getText("E108"));	//Please Enter valid Email Id
		}if (getActionErrors().size() <= 0) {
			Object obj[]={bean.getLoginId(),bean.getMailId(), "51"};
			List list=service.validateMailForgot(obj);
			if(list!=null && list.size()>0){
				String temp="tempPwd";
				String status1=service.sendUserPwd(bean.getLoginId(),temp, appId, request.getRealPath("/"));
				if (status1 == null) {
					bean.setStatus("success");
				}
				if(status1!=null){
					addActionError(status1);
				}
			}
			else if(list==null || list.size()==0){
				addActionError(getText("E109"));	//UserName and Email Id not match for this application
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
			}
			logger.info("Exit==>pwdForgot()");
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}
		return "forgotPWD";
	}

	public void validate(String userId, String pwd,String status) {
		if (userId == null || "".equals(userId.trim()))//Please Enter UserName
			addActionError( LocalizedTextUtil.findDefaultText("E105", Locale.ENGLISH));	//Please Enter UserName
		if (pwd == null || "".equals(pwd.trim())) {	//Please Enter Password
			addActionError( LocalizedTextUtil.findDefaultText("E111", Locale.ENGLISH)+"~");	//Please Enter Password
		}
	}

	public String option(){
		String returnResult=INPUT;
		if("changePwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="changePWD";
		}else if("forgotPwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="forgotPWD";
		}
		Map rs1=service.getMailDetails(appId);
		String pwdlen[]=rs1.get("PWD_LEN").toString().split("-");
		bean.setPwdMsg(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
		return returnResult;
	}

	public String out(){
		if("endorsment".equalsIgnoreCase(bean.getMenumodeStatus())){
			new DropDownControllor().riskDetailsEndorsement(bean.getProposalNo(),"N","");
		}
		logger.info("Enter==> Logout()");
		bean.setLoginId((String) session.get("UserId"));
		service.updateSessionInfo(bean.getLoginId(), request.getSession().getId(),"Log Out");
		((SessionMap<String, Object>) this.session).invalidate();
		logger.info("Exit==> Logout()");
		System.gc();
		return "page";
	}

	public CommonBean getModel() {
		return bean;
	}

	public boolean validPassword(String newpassword){
		Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{7,20})");
		Matcher matcher = pattern.matcher(newpassword);
		return matcher.matches();
	}
	public boolean validateEmail(String email){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9_\\+-]+(\\.[A-Za-z0-9_\\+-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.([A-Za-z]{2,4})$");
		boolean stat = true;
		if(email.contains(",")){
			String[] emails = email.split(",");
			for(String ids:emails){
				Matcher matcher = pattern.matcher(ids);
				if(!matcher.matches()){
					stat = false;
					break;
				}
			}
		}else{
			Matcher matcher = pattern.matcher(email);
			stat = matcher.matches();
		}
		return stat;
	}
	public void validatelogin(){
		if(StringUtils.isEmpty(bean.getLoginId())){
			addActionError(getText("loginid.empty"));
		}if(StringUtils.isEmpty(bean.getPwd())){
			addActionError(getText("pwd.empty"));
		}

	}
	public String invalidate(){
		logger.info("Enter==> invalidate()");
		service.updateSessionInfo(bean.getLoginId(), bean.getSessionId(),"I");
		bean.setStatus("success");
		logger.info("Exit==> invalidate()");
		return "invalidate";
	}

	public void Check(){
		bean.setLoginId((String) session.get("UserId"));
		bean.setSessionId( request.getSession(false).getId());
		if(service.getValidUserCount(bean)==0){
			((SessionMap<String, Object>) this.session).invalidate();
			logger.info("invalidate()");
		}
	}
	
	public String userInvalidate(){
		bean.setLoginId((String) session.get("UserId"));
		if(StringUtils.isBlank(bean.getCheckedVal())){
		bean.setUserLoginList(service.getUserLoginList(bean,session.get("BRANCH_CODE").toString()));
		}else{
			boolean status = service.updateUserInvalidation(bean,session.get("BRANCH_CODE").toString());
			if(status){
			bean.setStatus("success");
			}else{
				addActionError("Intrepted during login invalidation");
				bean.setUserLoginList(service.getUserLoginList(bean,session.get("BRANCH_CODE").toString()));
			}
		}
		return "userInvalidate";
	}

}
