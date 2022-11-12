package com.maan.common.login;
/**
 * @author Aswin
 *
 * Common Login Template
 */
import com.maan.bean.CommonBean;
import com.maan.common.util.LogUtil;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

public class LogInService {
	
	LogInDAO dao=new LogInDAO();
    final Logger logger = LogUtil.getLogger(LogInService.class);
    String SMTP_AUTH_USER; 
	String SMTP_AUTH_PWD;

	public String getClientIpAddr(HttpServletRequest request) {
		return dao.getClientIpAddr(request);
	}
	
	public boolean insertSessionInfo(final String loginId, final String sessionId, final String ipAddress, String branchCode){
		return dao.insertSessionInfo(loginId, sessionId, ipAddress,branchCode);
	}
	
	public boolean updateSessionInfo(final String userId, final String sessionId, String remarks){
		return dao.updateSessionInfo(userId, sessionId,remarks);
	}
	
	public String[] validateUser(final String userId, final String password,final String display, final String appId,String pwdcount, String contextPath){
        return dao.validateUser(userId, password,display,appId,pwdcount,contextPath);
    }
	public boolean checkPasswordChange(final String userId, String userStatus,String expiTime,String expiDate, String appId){
        return dao.checkPasswordChange(userId, userStatus,expiTime,expiDate,appId);
    }
	
	/*public List <Object>  getUserInfo(final AdminAction ac, final String userId, String appId){
        return dao.getUserInfo(ac, userId, appId);
    }*/
	
	public List <Object>  getUserInfo(final String userId, String appId){
        return dao.getUserInfo(userId, appId);
    }
	
	public String changePassword(String userId, String newPassword, String appId) {
        return dao.changePassword(userId, newPassword, appId);
	}
	
	public List<Object>  validateMailForgot(Object[] obj) {
        return dao.validateMailForgot(obj);
	}
	
	public String getAppName(String appId) {
        return dao.getAppName(appId);
	}
	
	public List<Object>  getappList() {
        return dao.getappList();
	}
	
	public Map<String, String> getMailDetails(String appId){
	    return dao.getMailDetails(appId);
	}
	public List<Object> getMenuList(String menuIds){
        return dao.getMenuList(menuIds);
    }
	public String sendUserPwd(final String user, String temp, String appId, String contextPath){
	 	List<Object>  list=null;
	 	String mail="";
		String expireTime="";
		String result="";
		try{
			Map<String, String> mapt=dao.getMailDetails(appId);
			list=dao.getUserInfo(user,appId);
			Map rs = (Map)list.get(0);
			mail=(String)rs.get("USER_MAIL");
			expireTime=dao.getExpireTime((String)mapt.get("EXP_TIME"));
			if(mail==null){
				result = "User doesn't Exists";	//E111=
			}else{
				String password =dao.getPassword(user,appId);			
				Map<String, String> details=new HashMap<String, String>();
				if(details != null){
					details.put("MAIL_TO", mail);
					details.put("MAIL_CC", (String)mapt.get("MAIL_CC"));
					details.put("USER", user);
					details.put("PASSWORD", password);
					details.put("SMTP_HOST", (String)mapt.get("SMTP_HOST"));
					details.put("SMTP_USER", (String)mapt.get("SMTP_USER"));
					details.put("SMTP_PWD", (String)mapt.get("SMTP_PWD"));
					details.put("MAIL_FROM", (String)mapt.get("SMTP_ADDRESS"));
					details.put("SMTP_SHORT_ADDRESS", (String)mapt.get("SMTP_SHORT_ADDRESS"));
					details.put("LOGO_PATH", contextPath + (String)mapt.get("LOGO_PATH"));
					details.put("expireTime", expireTime);
					details.put("SMTP_PORT", mapt.get("SMTP_PORT"));
			}
			result = sendPasswordMail(details,temp);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return result;
	 }
	
    public String sendPasswordMail(Map<String, String> details, String temp){
    	String result = null;
    	try{
	    	if(details !=null && !details.isEmpty()){
	    		String SMTP_HOST_NAME = details.get("SMTP_HOST");
	    		String user = details.get("SMTP_USER");
	    		String pwd = details.get("SMTP_PWD");
	    		String port=details.get("SMTP_PORT");
	    		String SMTP_MAIL_FROM = details.get("MAIL_FROM");
	    		String SMTP_SHORT_ADDRESS = details.get("SMTP_SHORT_ADDRESS");
	    		String subject = "GRIPS - Re Insurance -Password Reset";
	    		String toAddress = details.get("MAIL_TO");
	    		String ccAddress = details.get("MAIL_CC");
	    		String imagePath = details.get("LOGO_PATH");
	    		if(toAddress!=null && !"".equals(toAddress)){
		    		String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
		    		String[] ccAddresses = new String[0];
		    		if(ccAddress!=null && !"".equals(ccAddress)){
		    			ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
		    		}
		    		StringBuffer msg = new StringBuffer();
		        	msg.append("<br /><table width='100%' align='center'><tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append("Dear "+(String)details.get("USER")+",</td></tr><tr><td>&nbsp;</td></tr>");
		        	if("locked".equalsIgnoreCase(temp)){
		        		msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
			        	msg.append("Your UserId is locked due to invalid login of 3 times.</td></tr><tr><td>&nbsp;</td></tr>");
		        	}
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append(LocalizedTextUtil.findDefaultText("mail.forgot.pass.success", Locale.ENGLISH, new String[]{})+"</td></tr><tr><td>&nbsp;</td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append("(This Password will be expired at "+(String)details.get("expireTime")+")</td></tr><tr><td>&nbsp;</td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'><b>");
		        	msg.append("LoginID: "+(String)details.get("USER")+"</b></td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'><b>");
		        	msg.append("Password: "+(String)details.get("PASSWORD")+"</b></td></tr><tr><td>&nbsp;</td></tr></table>");
		        	msg.append(getMailSignature(imagePath));
		        	StringBuffer mailDatas = new StringBuffer();
		    		mailDatas.append("<html><head></head>");
		    		mailDatas.append("<body><table style='min-height: 200px;' align='left'>");
		    		mailDatas.append("<tr height='20'><td>&nbsp;</td></tr><tr><td align='left'>");
		    		mailDatas.append(msg);
		    		mailDatas.append("</td></tr><tr height='20'><td>&nbsp;</td></tr>");
		    		mailDatas.append("<tr><td><p>");
		    		mailDatas.append("</table></body></html>");
		    		String message = mailDatas.toString();
		    		sendResponseMail(SMTP_HOST_NAME, user, pwd, SMTP_MAIL_FROM, subject, message, toAddresses, ccAddresses, SMTP_SHORT_ADDRESS,port);
	    		}
	    	}
    	}catch(Exception e){
    		System.out.println("Exception Send mail => {}"+e);
    		result = e.getMessage();
    	}
    	return result;
    }
    
    public String getMailSignature(String imagePath){
    	StringBuffer msg = new StringBuffer();
    	msg.append("<br /><table width='100%'><tr><td><span style='font-size: 14px;'>");
    	msg.append("Best Regards,<br /><br />GRIPS Team");
    	msg.append("</td></tr><tr><td>");
    	//msg.append("<img src=\"C:\\Documents and Settings\\Administrator\\Desktop\\HEAD1.GIF\" />");
    	//msg.append("<img src=\"C:\\Documents and Settings\\Administrator\\Desktop\\HEAD2.GIF\" />");
    	msg.append("<img src=\""+ imagePath +"\" />");
    	msg.append("</td></tr>");
    	msg.append("</table>");
    	return msg.toString();
    }
    
    public String sendResponseMail(final String SMTP_HOST_NAME, final String user,  final String pwd, final String SMTP_MAIL_FROM, final String subject,
    		final String message, final String[] toAddress, final String[] ccAddress, final String SMTP_SHORT_ADDRESS,final String SMTP_PORT){
    	 logger.info("Enter sendResponseMail");
    	 String status="";
    	SMTP_AUTH_USER = user;
    	SMTP_AUTH_PWD = pwd;
    	try{
	    	Properties props = new Properties();
	    	props.setProperty("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", SMTP_PORT);
			props.put("mail.smtp.starttls.enable", "true");
			 props.put("mail.debug", "true");
			 props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = null; 
			if(SMTP_AUTH_PWD != null && !"".equals(SMTP_AUTH_PWD.trim())){
				props.put("mail.smtp.auth", "true");
				Authenticator auth = new SMTPAuthenticator();
				session = Session.getInstance(props, auth);
			}else{
				props.put("mail.smtp.auth", "false");
				session = Session.getInstance(props);
			}
			session.setDebug(false);
			Message msg1 = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(SMTP_MAIL_FROM, SMTP_SHORT_ADDRESS);
			msg1.setFrom(addressFrom);
			if(toAddress != null && toAddress.length>0){
				InternetAddress[] addressTo = new InternetAddress[toAddress.length];			
				for (int i = 0; i < toAddress.length; i++){
					addressTo[i] = new InternetAddress(toAddress[i]);
					msg1.addRecipient(Message.RecipientType.TO, addressTo[i]);
				}
			}
			if(ccAddress != null && ccAddress.length>0){
				InternetAddress[] addressToCC = new InternetAddress[ccAddress.length];			
				for(int i=0;i<ccAddress.length;i++){
					addressToCC[i] = new InternetAddress(ccAddress[i]);
					msg1.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				}
			}
			msg1.setSubject(subject);
			msg1.setContent(message, "text/html");
			System.out.println(msg1);
			logger.info("Mail msg"+msg1);
			Transport.send(msg1);
			status="Success";
		}catch(Exception e){
			System.out.println(e);
			status="Failed";
		}
    	logger.info("Exit sendResponseMail");
    	return status;
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
    
    public String getMenuIds(String loginId) {
    	return dao.getMenuIds(loginId);
    }
    public List<Map<String,Object>> getProductList(final String branchCode){
		return dao.getProductList(branchCode);
	}
    public List<Map<String, Object>> getDepartmentList(String branchCode) {
		return dao.getDepartmentList(branchCode);
	}
    public List<Map<String, Object>> getProcessList(String branchCode){
		return dao.getProcessList(branchCode);
    }
    public List<Map<String, Object>> getFinalMenuList(String menuIds) {
		   return dao.getFinalMenuList(menuIds);
	}

	public String getCountryId(String branchCode) {
		
		return dao.getCountryId(branchCode);
	}

	public int getValidLoginStatus(CommonBean bean) {
		return dao.getValidLoginStatus(bean);
	}

	public int getValidUserCount(CommonBean bean) {
		return dao.getValidUserCount(bean);
	}

	public int datevalidation(String content, CommonBean bean) {
		return dao.datevalidation(content,bean);
	}

	public List<Map<String, Object>> getUserLoginList(CommonBean bean, String branchCode) {
		return dao.getUserLoginList(bean,branchCode);
	}

	public boolean updateUserInvalidation(CommonBean bean, String branchCode) {
		return dao.updateUserInvalidation(bean,branchCode);
		
	}
}