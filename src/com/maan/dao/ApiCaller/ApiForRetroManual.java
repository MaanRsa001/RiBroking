package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;

public class ApiForRetroManual extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public void InsertPremium(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.RetroManual.InsertPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendmentDate", bean.getAmendmentDate());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("Brokerage", bean.getBrokerage());
			hp.put("BusinessType", bean.getBusinessType());
			hp.put("CashLossCredit", bean.getCashLoss_Credit());
			hp.put("CashLossPaid", bean.getCash_LossPaid());
			hp.put("Claimspaid", bean.getClaims_paid());
			hp.put("CliamPortfolioin", bean.getCliamPortfolioin());
			hp.put("Cliamportfolioout", bean.getCliam_portfolio_out());
			hp.put("CommissionQuotaShare", bean.getCommissionQuotaShare());
			hp.put("CommissionSurplus", bean.getCommissionSurplus());
			hp.put("ContNo", bean.getContNo());
			hp.put("Currency", bean.getCurrency());
			hp.put("ExchRate", bean.getExchRate());
			hp.put("InceptionDate", bean.getInception_Date());
			hp.put("Interest", bean.getInterest());
			hp.put("Layerno", bean.getLayerno());
			hp.put("LoginId", bean.getLoginId());
			hp.put("LossParticipation", bean.getLossParticipation());
			hp.put("LossReserveReleased", bean.getLossReserveReleased());
			hp.put("LossReserveRetained", bean.getLoss_ReserveRetained());
			hp.put("MaxDate", bean.getMaxDate());
			hp.put("Mode", bean.getMode());
			hp.put("OsClaimsLossUpdateOC", bean.getOsClaimsLossUpdateOC());
			hp.put("OtherCost", bean.getOtherCost());
			hp.put("Overrider", bean.getOverrider());
			hp.put("Predepartment", bean.getPredepartment());
			hp.put("PremiumQuotaShare", bean.getPremiumQuotaShare());
			hp.put("PremiumReserveQuotaShare", bean.getPremiumReserve_QuotaShare());
			hp.put("PremiumReserveReleased", bean.getPremium_Reserve_Released());
			hp.put("PremiumSurplus", bean.getPremiumSurplus());
			hp.put("PremiumportifolioIn", bean.getPremiumportifolioIn());
			hp.put("Premiumportifolioout", bean.getPremiumportifolioout());
			hp.put("ProductId", bean.getProductId());
			hp.put("ProfitCommission", bean.getProfit_Commission());
			hp.put("Reference", bean.getReference());
			hp.put("Remarks", bean.getRemarks());
			hp.put("ServiceTax", bean.getServiceTax());
			hp.put("Settlementstatus", bean.getSettlement_status());
			hp.put("SlideScaleCom", bean.getSlideScaleCom());
//			hp.put("SourceId", bean.getSourceId());
			hp.put("SubProfitId", bean.getSubProfitId());
			hp.put("Tax", bean.getTax());
			hp.put("TaxDedectSource", bean.getTaxDedectSource());
			hp.put("TotalCredit", bean.getTotalCredit());
			hp.put("TotalDebit", bean.getTotalDebit());
			hp.put("Transaction", bean.getTransaction());
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("TreatyName", bean.getTreatyName());
			hp.put("UwYear", bean.getUwYear());
			hp.put("WithHoldingTaxOC", bean.getWithHoldingTaxOC());
			hp.put("XlCost", bean.getXl_Cost());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
//				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRetroManualAdjlist(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.RetroManual.getRetroManualAdjlist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
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
	public List<Map<String, Object>> PremiumEdit(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.RetroManual.PremiumEdit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContractNo());
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("countyId", bean.getCountryId());
			hp.put("BranchCode", bean.getBranchCode());
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
	public List<Map<String, Object>> GetPremiumDetails(FaculPremiumBean bean, String transactionNo, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.RetroManual.GetPremiumDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContractNo());
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("BranchCode", bean.getBranchCode());
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
	public List<Map<String, Object>> getRetroDetails(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.RetroManual.getRetroDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
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

}
