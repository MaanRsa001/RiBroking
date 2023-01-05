package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;

public class ApiForFaculPremium extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPremiumTempList(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getPremiumTempList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
			link = getActualLink(link, hp);
			
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
	public List<Map<String, Object>> getPremiumedList(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getPremiumedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("OpendDate", beanObj.getOpendDate());
			hp.put("OpstartDate", beanObj.getOpstartDate());
			
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
	public List<Map<String, Object>> getPreList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();	
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getPreList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("DeptId", bean.getDepartmentId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json1.get("Result");
				if("Success".equals(json.get("Message"))) {
					bean.setCeaseStatus(json1.get("CeaseStatus")==null?"":json1.get("CeaseStatus").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String GetPreviousPremium(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.GetPreviousPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
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
	public String GetContractPremium(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.GetContractPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
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
	public Map<String, Object> contractDetails(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.contractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProductId", bean.getProductId());
			link = getActualLink(link, hp);
			
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
	public List<Map<String, Object>> PremiumContractDetails(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.PremiumContractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("LayerNo", bean.getLayerNo());
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
	public List<Map<String, Object>> mdInstallmentDates(String contNo, String layerNo) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.mdInstallmentDates");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contNo);
			hp.put("LayerNo", layerNo);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				//bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getDepartmentId(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getDepartmentId");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))){
					bean.setDepartmentId(json1.get("DepartmentId")==null?"":json1.get("DepartmentId").toString());
					bean.setProposal_No(json1.get("ProposalNo")==null?"":json1.get("ProposalNo").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean.getDepartmentId();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> premiumEdit(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json,json1,json2 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.premiumEdit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("CountryId", countryId);
			hp.put("Mode", bean.getMode());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("Transaction", bean.getTransaction());
			hp.put("TableType", bean.getTableType());
			hp.put("TransDropDownVal", bean.getTransDropDownVal());
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("RdsExchageRate", bean.getRdsExchageRate());
			hp.put("GwpiOS", bean.getGwpiOS());
			hp.put("PremiumQuotaShare", bean.getPremiumQuotaShare());
			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				json2 = (JSONObject) json1.get("PremiumEdit");
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json1.get("EditPremium");
				if("Success".equals(json.get("Message"))) {
					bean.setSum_of_paid_premium(json2.get("Sumofpaidpremium")==null?"":json2.get("Sumofpaidpremium").toString());
					bean.setEpibalance(json2.get("Epibalance")==null?"":json2.get("Epibalance").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public boolean premiumInsertMethod(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.premiumInsertMethod");
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
			hp.put("CommissionQuotaShare", beanObj.getCommissionQuotaShare());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EPIourshareview", beanObj.getEPI_our_share_view());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("GnpiDate", beanObj.getGnpiDate());
			hp.put("GwpiOS", beanObj.getGwpiOS());
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
			hp.put("Instalmentdate", beanObj.getInstalmentdate());
			hp.put("InstlmentNo", beanObj.getInstlmentNo());
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
			hp.put("ServiceTax", beanObj.getServiceTax());
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
				json1 = (JSONObject) json.get("Result");
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					beanObj.setRequestNo(json1.get("RequestNo")==null?"":json1.get("RequestNo").toString());
					beanObj.setTransactionNo(json1.get("TransactionNo")==null?"":json1.get("TransactionNo").toString());
					savFlg = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}

	@SuppressWarnings("unchecked")
	public boolean premiumUpdateMethod(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.premiumUpdateMethod");
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
			hp.put("CommissionQuotaShare", beanObj.getCommissionQuotaShare());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EPIourshareview", beanObj.getEPI_our_share_view());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("GnpiDate", beanObj.getGnpiDate());
			hp.put("GwpiOS", beanObj.getGwpiOS());
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
			hp.put("Instalmentdate", beanObj.getInstalmentdate());
			hp.put("InstlmentNo", beanObj.getInstlmentNo());
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
			hp.put("ServiceTax", beanObj.getServiceTax());
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
				if("Success".equals(json.get("Message"))) {
					savFlg = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPremiumDetails(FaculPremiumBean bean, String transactionNo, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getPremiumDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("TableType", bean.getTableType());
			hp.put("TransactionNo", transactionNo);
			hp.put("RdsExchageRate", bean.getRdsExchageRate());
			hp.put("GwpiOS", bean.getGwpiOS());
			
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
	public String GetInstalmentAmount(String contractNo, String getAmount) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.GetInstalmentAmount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contractNo);
			hp.put("Instalmentno", getAmount);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
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
	public Map<String, Object> getAllocatedList(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getAllocatedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", beanObj.getContNo());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			link = getActualLink(link, hp);
			
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
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.currencyList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
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
	public List<Map<String, Object>> GetFieldValues(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.GetFieldValues");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Adjustmentpremium", bean.getAdjustment_premium());
			hp.put("BonusId", bean.getBonusId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("Flag", bean.getFlag());
			hp.put("Layerno", bean.getLayerno());
			hp.put("Mdpremium", bean.getMd_premium());
			
			if(bean.getPreCurrencylist()!=null) {
				for(int i=0;i<bean.getPreCurrencylist().size();i++) {
				JSONObject abj = new JSONObject();
				abj.get(bean.getPreCurrencylist().get(i));
				array.add(abj);
			  }
			}
			hp.put("PreCurrencylist", array);
			
			hp.put("PremiumQuotaShare", bean.getPremiumQuotaShare());
			hp.put("ProductId", bean.getProductId());
			hp.put("ProposalNo", bean.getProposal_No());
			hp.put("Transaction", bean.getTransaction());
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

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> bonusdetails(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.bonusdetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("CountryId", countryId);
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("CurrencyName", bean.getCurrencyName());
			hp.put("ExchRate", bean.getExchRate());
			hp.put("Layerno", bean.getLayerno());
			hp.put("Mode", bean.getMode());
			hp.put("PremiumOC", bean.getPremiumOC());
			hp.put("Transaction", bean.getTransaction());
			hp.put("Type", bean.getType());
			
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
	public void addFieldValue(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.addFieldValue");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BonusExchangeRate", bean.getBonusExchangeRate());
			
			if(bean.getCurrencyShortName()!=null) {
				for(int i=0;i<bean.getCurrencyShortName().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CurrencyShortName", bean.getCurrencyShortName().get(i));
					array.add(abj);
				}
			}
			hp.put("CurrencyShortName", array);
			
			if(bean.getPremiumFinallist2()!=null) {
				for(int i=0;i<bean.getPremiumFinallist2().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("additionalProp1", bean.getPremiumFinallist2());
					abj.put("additionalProp2", bean.getPremiumFinallist2());
					abj.put("additionalProp3", bean.getPremiumFinallist2());
					array.add(abj);
				}
			}
			hp.put("PremiumFinallist2", array);
			
			hp.put("Transaction", bean.getTransaction());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMandDInstallments(String contNo, String layerNo) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getMandDInstallments");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contNo);
			hp.put("LayerNo", layerNo);
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
	public List<Map<String, Object>> getBrokerAndCedingName(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.faculpremium.getBrokerAndCedingName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
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
