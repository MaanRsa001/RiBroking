package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiForCommon extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMenuDropDownList(String branchCode, String userId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Home.getMenuDropDownList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("UserId", userId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDepartmentList(String productId, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Home.getDepartmentList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ProductId", productId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
//				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProcessList(String productId, String departmentId, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Home.getProcessList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ProductId", productId);
			hp.put("DepartmentId", departmentId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
//				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getOldProductId(Map<String, Object> session) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.Home.getOldProductId");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", session.get("BRANCH_CODE"));
			hp.put("DepartmentId", session.get("DepartmentId"));
			hp.put("ProcessId", session.get("processId"));
			hp.put("ProductId", session.get("mfrid"));
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
//				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFinalMenuList(String userId, String processId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Home.getFinalMenuList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("UserId", userId);
			hp.put("ProcessId", processId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
//				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

}
