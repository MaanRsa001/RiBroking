package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;

public class ApiForAuthendication extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> AuthenticationList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Authendication.AuthenticationList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CheckItem", bean.getCheckItem());
			hp.put("UploadStatus", bean.getUploadStatus());
			
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
	public void AuthenticationChanges(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Authendication.AuthenticationChanges");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendmentDate", beanObj.getAmendmentDate());
			hp.put("ApproveStatus", beanObj.getApproveStatus());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("CheckItem", beanObj.getCheckItem());
			hp.put("ClaimPayNo", beanObj.getClaimPaymentNo());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("ExchRate", beanObj.getExchRate());
			
			if(beanObj.getCLCsettlementRatelist()!=null) {
				for(int i=0;i<beanObj.getCLCsettlementRatelist().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("getCLCsettlementRatelist", beanObj.getCLCsettlementRatelist().get(i));
					abj.put("Chkbox", beanObj.getChkbox().get(i));
					abj.put("CreditAmountCLClist", beanObj.getCreditAmountCLClist().get(i));
					abj.put("CreditAmountCLDlist", beanObj.getCreditAmountCLDlist().get(i));
					abj.put("MainCLCsettlementRatelist", beanObj.getMainCLCsettlementRatelist());
					abj.put("MainclaimPaymentNos", beanObj.getMainclaimPaymentNos());
					abj.put("MaincreditAmountCLClist", beanObj.getMaincreditAmountCLClist());
					abj.put("MaincreditAmountCLDlist", beanObj.getMaincreditAmountCLDlist());
					array.add(abj);
				}
			}
			
			hp.put("GetCashLossCreditReq1", array);
			
			hp.put("InstlmentNo", beanObj.getInstlmentNo());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("Mode", beanObj.getMode());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("RequestNo", beanObj.getRequestNo());
			hp.put("RiCession", beanObj.getRi_cession());
			hp.put("Transaction", beanObj.getTransaction());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			hp.put("UploadStatus", beanObj.getUploadStatus());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPremiumDetails(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Authendication.getPremiumDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("TableType", bean.getTableType());
			hp.put("TransactionNo", bean.getTransactionNo());
			
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
