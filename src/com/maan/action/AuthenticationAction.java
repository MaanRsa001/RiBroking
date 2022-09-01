package com.maan.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.FaculPremiumBean;
import com.maan.service.AuthenticationService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AuthenticationAction extends ActionSupport implements ModelDriven<FaculPremiumBean> {
	
	FaculPremiumBean bean = new FaculPremiumBean();
	AuthenticationService service= new AuthenticationService();
	 
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	
	Map<String,Object> session = ActionContext.getContext().getSession();
	private String productId=(String) session.get("mfrid")==null?"":(String) session.get("mfrid");
	private String userType=(String) session.get("userType")==null?"":(String) session.get("userType");
	private String userId=(String) session.get("UserId")==null?"":(String) session.get("UserId");
	private String countryId=(String) session.get("countryId")==null?"":session.get("countryId").toString();
	private String branchCode=(String) session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	private String userName=(String) session.get("UserName")==null?"":(String) session.get("UserName");
	private String sourceId = session.get("SOURCE_CODE") ==null ?"":session.get("SOURCE_CODE").toString();
	
	public String init(){
		bean.setBranchCode(branchCode);
		bean.setAuthenticationList(service.AuthenticationList(bean));
		return "authentication";
	}
	
	public String approvalChange(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		bean.setCountryId(countryId);
		validationApproval();
		if(!hasActionErrors()){
		String res = service.AuthenticationChanges(bean);
		if("multiple".equalsIgnoreCase(bean.getUploadStatus())){
			if("A".equalsIgnoreCase(bean.getApproveStatus())){
				addActionMessage("Your Transaction have been Approved");
			}else{
				addActionMessage("Your Transaction have been Rejected");
			}
			bean.setAuthenticationList(service.AuthenticationList(bean));
		}else{
			if("A".equalsIgnoreCase(bean.getApproveStatus())){
				bean.setSuccessMessage("Your Transaction have been Approved,Transaction No : "+bean.getTransactionNo());
			}else{
				bean.setSuccessMessage("Your Transaction have been Rejected,Request No : "+bean.getRequestNo());
			}
		}
		return "authenticationSuccess";
		}else{
			 init();
			return "authentication";
		}
	}
	
	private void validationApproval() {
		if("multiple".equalsIgnoreCase(bean.getUploadStatus()) &&(bean.getCheckItem()==null || "".equalsIgnoreCase(bean.getCheckItem()))){
			addActionError(getText("error.authe.checkItem"));
		}
	}

	public FaculPremiumBean getModel() {
		return bean;
	}

}
