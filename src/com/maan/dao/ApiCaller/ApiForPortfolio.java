package com.maan.dao.ApiCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.PortfolioBean;

public class ApiForPortfolio extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPendingList(PortfolioBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.portfolio.getPendingList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AttachedUW", beanObj.getAttachedUW());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BrokerNameSearch", beanObj.getBrokerNameSearch());
			hp.put("BrokerNameSearch1", beanObj.getBrokerNameSearch1());
			hp.put("CompanyNameSearch", beanObj.getCompanyNameSearch());
			hp.put("CompanyNameSearch1", beanObj.getCompanyNameSearch1());
			hp.put("ContractNoSearch", beanObj.getContractNoSearch());
			hp.put("DepartmentNameSearch", beanObj.getDepartmentNameSearch());
			hp.put("DepartmentNameSearch1", beanObj.getDepartmentNameSearch1());
			hp.put("DeptId", beanObj.getDeptId());
			hp.put("Flag", beanObj.getFlag());
			hp.put("InsuredNameSearch", beanObj.getInsuredNameSearch());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNoSearch", beanObj.getProposalNoSearch());
			hp.put("SearchType", beanObj.getSearchType());
			hp.put("UnderwriterSearch", beanObj.getUnderwriterSearch());
			hp.put("UnderwriterSearch1", beanObj.getUnderwriterSearch1());
			hp.put("UwYearSearch", beanObj.getUwYearSearch());
			hp.put("UwYearSearch1", beanObj.getUwYearSearch1());
			hp.put("UwYearSearch2", beanObj.getUwYearSearch2());
			hp.put("UwYearSearch3", beanObj.getUwYearSearch3());			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				result = (JSONArray) json1.get("PendingList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRejectedList(PortfolioBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.portfolio.getRejectedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AttachedUW", beanObj.getAttachedUW());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BrokerNameSearch", beanObj.getBrokerNameSearch());
			hp.put("BrokerNameSearch1", beanObj.getBrokerNameSearch1());
			hp.put("CompanyNameSearch", beanObj.getCompanyNameSearch());
			hp.put("CompanyNameSearch1", beanObj.getCompanyNameSearch1());
			hp.put("ContractNoSearch", beanObj.getContractNoSearch());
			hp.put("DepartmentNameSearch", beanObj.getDepartmentNameSearch());
			hp.put("DepartmentNameSearch1", beanObj.getDepartmentNameSearch1());
			hp.put("DeptId", beanObj.getDeptId());
			hp.put("Flag", beanObj.getFlag());
			hp.put("InsuredNameSearch", beanObj.getInsuredNameSearch());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNoSearch", beanObj.getProposalNoSearch());
			hp.put("SearchType", beanObj.getSearchType());
			hp.put("UnderwriterSearch", beanObj.getUnderwriterSearch());
			hp.put("UnderwriterSearch1", beanObj.getUnderwriterSearch1());
			hp.put("UwYearSearch", beanObj.getUwYearSearch());
			hp.put("UwYearSearch1", beanObj.getUwYearSearch1());
			hp.put("UwYearSearch2", beanObj.getUwYearSearch2());
			hp.put("UwYearSearch3", beanObj.getUwYearSearch3());			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAutoPendingList(PortfolioBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.portfolio.getAutoPendingList");
			authorization= getValueFromWebservice("marine.insurance.auth");
		
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("DueDate", beanObj.getDueDate());
			hp.put("Flag", beanObj.getFlag());
			hp.put("ProductId", beanObj.getProductId());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractsList(PortfolioBean beanObj, Object menuRights) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		List<Integer>departId=new ArrayList<Integer>();
		try {
			String link=getValueFromWebservice("maan.portfolio.getContractsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
		
			hp.put("AttachedUW", beanObj.getAttachedUW());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BrokerNameSearch", beanObj.getBrokerNameSearch());
			hp.put("BrokerNameSearch1", beanObj.getBrokerNameSearch1());
			hp.put("CompanyNameSearch", beanObj.getCompanyNameSearch());
			hp.put("CompanyNameSearch1", beanObj.getCompanyNameSearch1());
			hp.put("ContractNoSearch", beanObj.getContractNoSearch());
			
			departId.add(0);
			hp.put("DepartmentId", departId);
			
			hp.put("DepartmentNameSearch", beanObj.getDepartmentNameSearch());
			hp.put("DepartmentNameSearch1", beanObj.getDepartmentNameSearch1());
			hp.put("DeptId", beanObj.getDeptId());
			hp.put("Flag", beanObj.getFlag());
			hp.put("InsuredNameSearch", beanObj.getInsuredNameSearch());
			
			JSONArray result1=new JSONArray();
			if(menuRights!=null) {
				result1.add(menuRights);
			}
			
			hp.put("MenuRights", result1);
			
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNoSearch", beanObj.getProposalNoSearch());
			hp.put("SearchType", beanObj.getSearchType());
			hp.put("UnderwriterSearch", beanObj.getUnderwriterSearch());
			hp.put("UnderwriterSearch1", beanObj.getUnderwriterSearch1());
			hp.put("UwYearSearch", beanObj.getUwYearSearch());
			hp.put("UwYearSearch1", beanObj.getUwYearSearch1());
			hp.put("UwYearSearch2", beanObj.getUwYearSearch2());
			hp.put("UwYearSearch3", beanObj.getUwYearSearch3());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if(beanObj.getErrors()==null) {
					json1 = (JSONObject) json.get("Result");
					result = (JSONArray) json1.get("GetContractsList");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getHistoryList(PortfolioBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.portfolio.getHistoryList");
			authorization= getValueFromWebservice("marine.insurance.auth");
		
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Flag", beanObj.getFlag());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void procAuto(PortfolioBean bean, String menuId, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.portfolio.procAuto");
			authorization= getValueFromWebservice("marine.insurance.auth");
		
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CountryId", countryId);
			hp.put("LoginId", bean.getLoginId());
			
			if(bean.getPortfolioList()!=null) {
				for(int i=0;i<bean.getPortfolioList().size();i++) {
					JSONObject abj = new JSONObject();
//					abj.put("Bonus", bean.getBonus());
					abj.put("Brokerage", bean.getBrokerage());
					abj.put("BrokerageOC", bean.getBrokerageOC());
					abj.put("Commission", bean.getCommission());
					abj.put("CommissionOC", bean.getCommissionOC());
					abj.put("ContractNo", bean.getContractNo());
					abj.put("CurrencyId", bean.getCurrencyId());
					abj.put("DepartmentId", bean.getDepartmentId());
					abj.put("ExchangeRate", bean.getExchangeRate());
					abj.put("InstallmentDate", bean.getInstallmentDate());
					abj.put("InstallmentNo", bean.getInstallmentNo());
					abj.put("LayerNo", bean.getLayerNo());
					abj.put("NetDueOC", bean.getNetDueOC());
					abj.put("OtherCostOC", bean.getOtherCostOC());
					abj.put("PremiumClass", bean.getPremiumClass());
					abj.put("PremiumOC", bean.getPremiumOC());
					abj.put("PremiumSubClass", bean.getPremiumSubClass());
					abj.put("ProposalNo", bean.getProposalNo());
					abj.put("ReceiveDate", bean.getReceiveDate());
					abj.put("Reference", bean.getReference());
					abj.put("Remarks", bean.getRemarks());
//					abj.put("ServiceTax", bean.getServiceTax());
					abj.put("Tax", bean.getTax());
//					abj.put("TaxDedectSource", bean.getTaxDedectSource());
					abj.put("TaxOC", bean.getTaxOC());
					abj.put("TransactionDate", bean.getTransactionDate());
//					abj.put("WithHoldingTaxOC", bean.getWithHoldingTaxOC());
					array.add(abj);
				}
			}
			hp.put("PortfolioListReq", array);
			
			hp.put("ProductId", bean.getProductId());
//			hp.put("SettlementStatus", bean.getSettlementStatus());
			hp.put("TransactionError", bean.getTransactionError());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
