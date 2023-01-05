package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.AdjustmentBean;

public class ApiForAdjustment extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdjustmentDoneList(AdjustmentBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.getAdjustmentDoneList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("SearchBy", bean.getSearchBy());
			hp.put("SearchValue", bean.getSearchValue());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
	public List<Map<String, Object>> adjustmentView(AdjustmentBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.adjustmentView");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("Mode", bean.getMode());
			hp.put("SerialNo", bean.getSerialNo());
			hp.put("Status", bean.getStatus());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
	public List<Map<String, Object>> getAdjustmentList(AdjustmentBean bean,
			Map<String, String> receiveAdjustAmountMap) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.getAdjustmentList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AdjustType", bean.getAdjustType());
			hp.put("AdjustmentDate", bean.getAdjustmentDate());
			hp.put("Amount", bean.getAmount());
			hp.put("AmountType", bean.getAmountType());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("LoginId", bean.getLoginId());
			hp.put("Mode", bean.getMode());
			hp.put("Test", bean.getTest());
			hp.put("TransDate", bean.getTransDate());
			hp.put("TransactionNo", bean.getTransactionNo());
			
			if(bean.getAdjustmentAmounts()!=null) {
				for(int i=0;i<bean.getAdjustmentAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("AdjustAmount", bean.getAdjustmentAmounts().get(i));
					abj.put("AdjustType", bean.getAdjustmentType().get(i));
					abj.put("Check", bean.getCheck());
					abj.put("TransactionNo", bean.getTransactionNo());
					array.add(abj);
				}
			}
			hp.put("TransactionNoListReq", array);
			hp.put("TransactionType", bean.getTransactionType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
	public List<Map<String, Object>> getAdjustmentPayRecList(AdjustmentBean bean,
			Map<String, String> receiveAdjustAmountMap) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.getAdjustmentPayRecList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AdjustType", bean.getAdjustType());
			hp.put("AdjustmentDate", bean.getAdjustmentDate());
			hp.put("Amount", bean.getAmount());
			hp.put("AmountType", bean.getAmountType());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("LoginId", bean.getLoginId());
			hp.put("Mode", bean.getMode());
			hp.put("Test", bean.getTest());
			hp.put("TransDate", bean.getTransDate());
			hp.put("TransactionNo", bean.getTransactionNo());
			
			if(bean.getAdjustmentAmounts()!=null) {
				for(int i=0;i<bean.getAdjustmentAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("AdjustAmount", bean.getAdjustmentAmounts().get(i));
					abj.put("AdjustType", bean.getAdjustmentType().get(i));
					abj.put("Check", bean.getCheck());
					abj.put("TransactionNo", bean.getTransactionNo());
					array.add(abj);
				}
			}
			hp.put("TransactionNoListReq", array);
			hp.put("TransactionType", bean.getTransactionType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
	public void AddAdjustment(AdjustmentBean bean, String branchCode, Map<String, String> receiveAdjustAmountMap) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.AddAdjustment");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AdjustType", bean.getAdjustType());
			hp.put("AdjustmentDate", bean.getAdjustmentDate());
			hp.put("Amount", bean.getAmount());
			hp.put("AmountType", bean.getAmountType());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("LoginId", bean.getLoginId());
			hp.put("Mode", bean.getMode());
			hp.put("Test", bean.getTest());
			hp.put("TransDate", bean.getTransDate());
			hp.put("TransactionNo", bean.getTransactionNo());
			
			if(bean.getAdjustmentAmounts()!=null) {
				for(int i=0;i<bean.getAdjustmentAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("AdjustAmount", bean.getAdjustmentAmounts().get(i));
					abj.put("AdjustType", bean.getAdjustmentType().get(i));
					abj.put("Check", bean.getCheck());
					abj.put("TransactionNo", bean.getTransactionNo());
					array.add(abj);
				}
			}
			hp.put("TransactionNoListReq", array);
			hp.put("TransactionType", bean.getTransactionType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void AddAdjustmentPayRec(AdjustmentBean bean, String branchCode,
			Map<String, String> receiveAdjustAmountMap) {

		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.AddAdjustmentPayRec");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AdjustType", bean.getAdjustType());
			hp.put("AdjustmentDate", bean.getAdjustmentDate());
			hp.put("Amount", bean.getAmount());
			hp.put("AmountType", bean.getAmountType());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("LoginId", bean.getLoginId());
			hp.put("Mode", bean.getMode());
			hp.put("Test", bean.getTest());
			hp.put("TransDate", bean.getTransDate());
			hp.put("TransactionNo", bean.getTransactionNo());
			
			if(bean.getAdjustmentAmounts()!=null) {
				for(int i=0;i<bean.getAdjustmentAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("AdjustAmount", bean.getAdjustmentAmounts().get(i));
					abj.put("AdjustType", bean.getAdjustmentType().get(i));
					abj.put("Check", bean.getCheck());
					abj.put("TransactionNo", bean.getTransactionNo());
					array.add(abj);
				}
			}
			hp.put("TransactionNoListReq", array);
			hp.put("TransactionType", bean.getTransactionType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> insertReverse(AdjustmentBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.adjustment.insertReverse");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Allocateddate", bean.getAllocateddate());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ReverseDate", bean.getReverseDate());
			hp.put("SerialNo", bean.getSerialNo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
