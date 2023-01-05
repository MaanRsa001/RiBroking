package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.CollectionUtils;

import com.maan.bean.FaculPremiumBean;

public class ApiForXolPremium extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getPremiumedList(FaculPremiumBean beanObj, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getPremiumedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("LayerNo", beanObj.getLayerno());
			hp.put("OpendDate", beanObj.getOpendDate());
			hp.put("OpstartDate", beanObj.getOpstartDate());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("Type", type);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPreList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getPreList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("LayerNo", bean.getLayerno());
			
			link=getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> getDepartmentId(FaculPremiumBean bean) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getDepartmentId");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
			
			link=getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public String GetPreviousPremium(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.xolprem.GetPreviousPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContNo());
			
			link=getActualLink(link, hp);
			
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
	public String GetContractPremium(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.xolprem.GetContractPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContNo());
			hp.put("LayerNo", bean.getLayerno());
			
			link=getActualLink(link, hp);
			
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
	public Map<String, Object> contractDetails(FaculPremiumBean bean, String countryId) {
		JSONObject json,josn1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.contractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("Layerno", bean.getLayerno());
			hp.put("ProductId", bean.getProductId());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				josn1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return josn1;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mdInstallmentDates(String contNo, String layerNo, String sourceId,
			String productId) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.mdInstallmentDates");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contNo);
			hp.put("Layerno", layerNo);
			hp.put("SourceId", sourceId);
			hp.put("ProductId", productId);
			
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
	public Map<String,Object> premiumInsertMethod(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json,json1 = new JSONObject();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.xolprem.premiumInsertMethod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcceptenceDate", beanObj.getAcceptenceDate());
			hp.put("AccountPeriod", beanObj.getAccount_Period());
			hp.put("AccountPeriodyear", beanObj.getAccount_Period_year());
			hp.put("Adjustmentpremium", beanObj.getAdjustment_premium());
			hp.put("Adjustmentpremiumtemp", beanObj.getAdjustment_premium_temp());
			hp.put("AmendmentDate", beanObj.getAmendmentDate());
			hp.put("BaseCurrencyId", beanObj.getBaseCurrencyId());
			hp.put("BaseCurrencyName", beanObj.getBaseCurrencyName());
			hp.put("Bonus", beanObj.getBonus());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("Brokerageview", beanObj.getBrokerage_view());
			hp.put("ButtonStatus", beanObj.getButtonStatus());
			hp.put("CedentRef", beanObj.getCedentRef());
			hp.put("ChooseTransaction", beanObj.getChooseTransaction());
			hp.put("Commission", beanObj.getCommission());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EPIourshareview", beanObj.getEPI_our_share_view());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("GnpiDate", beanObj.getGnpiDate());
			hp.put("GwpiOS", beanObj.getGwpiOS());
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
			//hp.put("Instalmentdate", beanObj.getInstalmentdate());
			//hp.put("InstlmentNo", beanObj.getInstlmentNo());
			hp.put("Layerno", beanObj.getLayerno());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("Mdpremium", beanObj.getMd_premium());
			hp.put("Mode", beanObj.getMode());
			hp.put("NoOfRetro", beanObj.getNoOfRetro());
			hp.put("OtherCost", beanObj.getOtherCost());
			hp.put("PreamendmentDate", beanObj.getPreamendmentDate());
			hp.put("Predepartment", beanObj.getPredepartment());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposal_No());
			hp.put("Receiptno", beanObj.getReceipt_no());
			hp.put("Recuirementpremium", beanObj.getRecuirement_premium());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RequestNo", beanObj.getRequestNo());
			hp.put("RetroContractNo", beanObj.getRetroContractNo());
			hp.put("Ricession", beanObj.getRi_cession());
			hp.put("VatPremium", beanObj.getVatPremium());
			hp.put("BrokerageVat", beanObj.getBrokerageVat());
			hp.put("DocumentType", beanObj.getDocumentType());
			hp.put("Settlementstatus", beanObj.getSettlement_status());
			hp.put("ShareSigned", beanObj.getShareSigned());
//			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("StatementDate", beanObj.getStatementDate());
			hp.put("SubProfitId", beanObj.getSubProfitId());
			hp.put("Sumofpaidpremium", beanObj.getSum_of_paid_premium());
			hp.put("TableType", beanObj.getTableType());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxDedectSource", beanObj.getTaxDedectSource());
			hp.put("Taxview", beanObj.getTax_view());
			hp.put("TotalCredit", beanObj.getTotalCredit());
			hp.put("TotalDebit", beanObj.getTotalDebit());
			hp.put("TransDropDownVal", beanObj.getTransDropDownVal());
			hp.put("Transaction", beanObj.getTransaction());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			hp.put("UserType", beanObj.getUserType());
			hp.put("WithHoldingTaxOC", beanObj.getWithHoldingTaxOC());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				 json = (JSONObject) parser.parse(responseStr);
				 beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				 json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPremiumDetails(FaculPremiumBean bean, String transactionNo, String countryId) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getPremiumDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("TableType", bean.getTableType());
			hp.put("TransactionNo", transactionNo);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> premiumEdit(FaculPremiumBean bean, String countryId) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.premiumEdit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("CountryId", countryId);
			hp.put("Mode", bean.getMode());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("TableType", bean.getTableType());
			hp.put("TransDropDownVal", bean.getTransDropDownVal());
			hp.put("TransactionNo", bean.getTransactionNo());			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				if(CollectionUtils.isEmpty(bean.getErrors())) {
					json1 = (JSONObject) json.get("Result");
					array = (JSONArray) json1.get("PremiumEditRes1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> premiumUpdateMethod(FaculPremiumBean beanObj) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.premiumUpdateMethod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcceptenceDate", beanObj.getAcceptenceDate());
			hp.put("AccountPeriod", beanObj.getAccount_Period());
			hp.put("AccountPeriodyear", beanObj.getAccount_Period_year());
			hp.put("Adjustmentpremium", beanObj.getAdjustment_premium());
			hp.put("Adjustmentpremiumtemp", beanObj.getAdjustment_premium_temp());
			hp.put("AmendmentDate", beanObj.getAmendmentDate());
			hp.put("BaseCurrencyId", beanObj.getBaseCurrencyId());
			hp.put("BaseCurrencyName", beanObj.getBaseCurrencyName());
			hp.put("Bonus", beanObj.getBonus());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("Brokerageview", beanObj.getBrokerage_view());
			hp.put("ButtonStatus", beanObj.getButtonStatus());
			hp.put("CedentRef", beanObj.getCedentRef());
			hp.put("ChooseTransaction", beanObj.getChooseTransaction());
			hp.put("Commission", beanObj.getCommission());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EPIourshareview", beanObj.getEPI_our_share_view());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("GnpiDate", beanObj.getGnpiDate());
			hp.put("GwpiOS", beanObj.getGwpiOS());
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
//			hp.put("Instalmentdate", beanObj.getInstalmentdate());
//			hp.put("InstlmentNo", beanObj.getInstlmentNo());
			hp.put("Layerno", beanObj.getLayerno());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("Mdpremium", beanObj.getMd_premium());
			hp.put("Mode", beanObj.getMode());
			hp.put("NoOfRetro", beanObj.getNoOfRetro());
			hp.put("OtherCost", beanObj.getOtherCost());
			hp.put("PreamendmentDate", beanObj.getPreamendmentDate());
			hp.put("Predepartment", beanObj.getPredepartment());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposal_No());
			hp.put("Receiptno", beanObj.getReceipt_no());
			hp.put("Recuirementpremium", beanObj.getRecuirement_premium());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RequestNo", beanObj.getRequestNo());
			hp.put("RetroContractNo", beanObj.getRetroContractNo());
			hp.put("Ricession", beanObj.getRi_cession());
			hp.put("VatPremium", beanObj.getVatPremium());
			hp.put("BrokerageVat", beanObj.getBrokerageVat());
			hp.put("DocumentType", beanObj.getDocumentType());
			hp.put("Settlementstatus", beanObj.getSettlement_status());
			hp.put("ShareSigned", beanObj.getShareSigned());
			//hp.put("SourceId", beanObj.getSourceId());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("StatementDate", beanObj.getStatementDate());
			hp.put("SubProfitId", beanObj.getSubProfitId());
			hp.put("Sumofpaidpremium", beanObj.getSum_of_paid_premium());
			hp.put("TableType", beanObj.getTableType());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxDedectSource", beanObj.getTaxDedectSource());
			hp.put("Taxview", beanObj.getTax_view());
			hp.put("TotalCredit", beanObj.getTotalCredit());
			hp.put("TotalDebit", beanObj.getTotalDebit());
			hp.put("TransDropDownVal", beanObj.getTransDropDownVal());
			hp.put("Transaction", beanObj.getTransaction());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			hp.put("UserType", beanObj.getUserType());
			hp.put("WithHoldingTaxOC", beanObj.getWithHoldingTaxOC());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public String GetInstalmentAmount(String contractNo, String layerNo, String getAmount) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.xolprem.GetInstalmentAmount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contractNo);
			hp.put("LayerNo", layerNo);
			hp.put("Instalmentno", getAmount);
			
			//link=getActualLink(link, hp);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBrokerAndCedingName(FaculPremiumBean beanObj) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getBrokerAndCedingName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			
			link=getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.currencyList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			
			link=getActualLink(link, hp);
			
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
	public Map<String,Object> getAllocatedList(FaculPremiumBean beanObj) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getAllocatedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", beanObj.getContNo());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			
			link=getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public String GetAdjPremium(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.xolprem.GetAdjPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("Currency", bean.getCurrency());
			hp.put("GnpiDate", bean.getGnpiDate());
			hp.put("Layerno", bean.getLayerno());
			hp.put("Predepartment", bean.getPredepartment());			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
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
	public Map<String, Object> getVatInfo(FaculPremiumBean bean) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.xolprem.getvatInfo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("PremiumAmount", bean.getPremiumAmount());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProposalNo", bean.getProposal_No());
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray json1 = null;
		try {
			String link=getValueFromWebservice("maan.client.xolprem.ripremiumlist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContNo());
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public void updateRiStatus(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray json1 = null;
		try {
			String link=getValueFromWebservice("maan.client.proppremium.updateristatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContNo());
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
