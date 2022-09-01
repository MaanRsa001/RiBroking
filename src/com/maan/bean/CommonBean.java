package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonBean {
	
	private String loginId;
	private String pwd;
	private String utype;
	private String userId;
	private String userPwd;
	private String status;
	private String newpwd;
	private String repwd;
	private String mailId;
	private String pwdMsg;
	private String productId;
	private String proposalNo;
	private String menumode;
	private String menumodeStatus;
	private String validLogin;
	private String sessionId;
	private String expiryDate;
	private String expiryCount;
	private String expiryDate07;
	public List<Map<String,Object>>invalidLoginList=new ArrayList<Map<String,Object>>();
	public List<Map<String,Object>>userLoginList;
	private String checkedVal;
	
	
	
	
	public String getCheckedVal() {
		return checkedVal;
	}
	public void setCheckedVal(String checkedVal) {
		this.checkedVal = checkedVal;
	}
	public List<Map<String, Object>> getUserLoginList() {
		return userLoginList;
	}
	public void setUserLoginList(List<Map<String, Object>> userLoginList) {
		this.userLoginList = userLoginList;
	}
	public String getExpiryDate07() {
		return expiryDate07;
	}
	public void setExpiryDate07(String expiryDate07) {
		this.expiryDate07 = expiryDate07;
	}
	public String getExpiryCount() {
		return expiryCount;
	}
	public void setExpiryCount(String expiryCount) {
		this.expiryCount = expiryCount;
	}
	
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPwdMsg() {
		return pwdMsg;
	}
	public void setPwdMsg(String pwdMsg) {
		this.pwdMsg = pwdMsg;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductId() {
		return productId;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getMenumode() {
		return menumode;
	}
	public void setMenumode(String menumode) {
		this.menumode = menumode;
	}
	public String getMenumodeStatus() {
		return menumodeStatus;
	}
	public void setMenumodeStatus(String menumodeStatus) {
		this.menumodeStatus = menumodeStatus;
	}
	public String getValidLogin() {
		return validLogin;
	}
	public void setValidLogin(String validLogin) {
		this.validLogin = validLogin;
	}
	public List<Map<String, Object>> getInvalidLoginList() {
		return invalidLoginList;
	}
	public void setInvalidLoginList(List<Map<String, Object>> invalidLoginList) {
		this.invalidLoginList = invalidLoginList;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


}
