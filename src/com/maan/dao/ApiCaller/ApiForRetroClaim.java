package com.maan.dao.ApiCaller;

import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.ClaimBean;

public class ApiForRetroClaim extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getShortname(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.Retroclaim.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getProposalNo(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.Retroclaim.getProposalNo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("LayerNo", bean.getLayerNo());
			hp.put("ProductId", bean.getProductId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
