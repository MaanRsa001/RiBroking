package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.HomeBean;
import com.maan.dao.CommonDAO;


public class CommonService {
    CommonDAO dao=new CommonDAO();
    
	public List<Map<String,Object>> getProductList(final String branchCode){
		return dao.getProductList(branchCode);
	}
	public List<Map<String, Object>> getDepartmentList(String productId,
			String branchCode) {
		return dao.getDepartmentList(productId,branchCode);
	}
	public List<Map<String, Object>> getProcessList(String productId,String status,String branchCode){
			return dao.getProcessList(productId,status,branchCode);
	}
	public List<Map<String, Object>> getFinalMenuList(Map<String, Object> session) {
		   return dao.getFinalMenuList(session);
	}
	public String getOldProductId(Map<String, Object> session) {
		return dao.getOldProductId(session);
	}
	public List<Map<String, Object>> getFinalMenuList(String userId,String processId) {
		   return dao.getFinalMenuList(userId,processId);
	}
	public List<Map<String, Object>> getMenuDropDownList(String branchCode, String userId) {
		return dao.getMenuDropDownList(branchCode,userId);
	}
	public void insertSessionTrackingdetails(Map<String, Object> session, String sessionId) {
		dao.insertSessionTrackingdetails(session,sessionId);
	}
	

}
