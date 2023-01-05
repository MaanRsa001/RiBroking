package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;

public class ApiForStatistics extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> SlideScenario(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Statistics.SlideScenario");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("DepartmentId",bean.getDepartmentId());
			hp.put("LayerNo", bean.getLayerNo());
			hp.put("ProductId", bean.getProductId());
			hp.put("ProposalNo", bean.getProposal_No());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCurrencyName(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Statistics.getCurrencyName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo",bean.getProposal_No());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("ProductId", bean.getProductId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getColumnHeaderlist(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Statistics.getColumnHeaderlist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("PeriodFrom",bean.getPeriodFrom());
			hp.put("PeriodTo", bean.getPeriodTo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRenewalStatisticsList(FaculPremiumBean bean, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Statistics.getRenewalStatisticsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode",bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("PeriodFrom", bean.getPeriodFrom());
			hp.put("PeriodTo", bean.getPeriodTo());
			hp.put("Type", type);
			hp.put("UwFrom", bean.getUwFrom());
			hp.put("UwTo", bean.getUwTo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRenewalCalStatisticsList(FaculPremiumBean bean, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Statistics.getRenewalCalStatisticsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode",bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("PeriodFrom", bean.getPeriodFrom());
			hp.put("PeriodTo", bean.getPeriodTo());
			hp.put("Type", type);
			hp.put("UwFrom", bean.getUwFrom());
			hp.put("UwTo", bean.getUwTo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
		

}
