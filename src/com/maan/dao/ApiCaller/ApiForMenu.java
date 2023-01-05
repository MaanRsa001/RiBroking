package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiForMenu extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdminMenu(String loginID, String make) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Menu.getAdminMenu");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LoginID", loginID);
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
	public String manuName(String mfrID, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String name = "";
		
		try {
			String link=getValueFromWebservice("maan.Menu.manuName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("MfrID", mfrID);
			hp.put("BranchCode", branchCode);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				name = (String) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRigthsOfProcess(String menuid, String processid, String loginid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Menu.getRigthsOfProcess");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LoginID", loginid);
			hp.put("Menuid", menuid);
			hp.put("Processid", processid);
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
	public List<Map<String, Object>> getDepartmentListNew(String product_id, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Menu.getDepartmentListNew");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProductId", product_id);
			hp.put("BranchCode", branchCode);
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

}
