package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.ReportsBean;

public class ApiForReports extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPendingOffersList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getPendingOffersList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("Dept", bean.getDept());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getMoveMentInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getMoveMentInit");
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
	public List<Map<String, Object>> getClaimMoveMentInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getClaimMoveMentInit");
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
	public List<Map<String, Object>> getClaimJournelInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getClaimJournelInit");
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
	public List<Map<String, Object>> getBookedUprInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getBookedUprInit");
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
	public List<Map<String, Object>> getBookedPremiumInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getBookedPremiumInit");
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
	public List<Map<String, Object>> getPipelineWrittenInit(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getPipelineWrittenInit");
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
	public List<Map<String, Object>> getProductDropDown(String branchCode, String typeId) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getProductDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("TypeId", typeId);
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
	public List<Map<String, Object>> getCedingCompany(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getCedingCompany");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProductId", bean.getProductId());
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
	public String getReportName(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		String result = "";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getReportName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("TypeId", bean.getTypeId());
			hp.put("ProductId", bean.getProductId());
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
	public List<Map<String, Object>> getRetroQuarterlyReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getRetroQuarterlyReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("Dept", bean.getDept());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getInwardRetroMappingReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
	
		try {
			String link=getValueFromWebservice("maan.Reports.getInwardRetroMappingReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getMoveMntSummary(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getMoveMntSummary");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("EndDate", bean.getEndDate());
			hp.put("StartDate", bean.getStartDate());
			
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
	public List<Map<String, Object>> getPolicyRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getPolicyRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("Dept", bean.getDept());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getPremiumRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getPremiumRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("Dept", bean.getDept());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getClaimRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getClaimRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getRenewalDueList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getRenewalDueList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("Dept", bean.getDept());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getRetroInwardMappingReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getRetroInwardMappingReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("EndDate", bean.getEndDate());
			hp.put("StartDate", bean.getStartDate());
			
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
	public List<Map<String, Object>> getTransactionMasterReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getTransactionMasterReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("DocType", bean.getDocType());
			hp.put("EndDate", bean.getEndDate());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getDebtorsAgeingReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getDebtorsAgeingReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("DebFrom", bean.getDebFrom());
			hp.put("DebFrom1", bean.getDebFrom1());
			hp.put("DebFrom2", bean.getDebFrom2());
			hp.put("DebFrom3", bean.getDebFrom3());
			hp.put("DebFrom4", bean.getDebFrom4());
			hp.put("DebFrom5", bean.getDebFrom5());
			hp.put("DebTo", bean.getDebTo());
			hp.put("DebTo1", bean.getDebTo1());
			hp.put("DebTo2", bean.getDebTo2());
			hp.put("DebTo3", bean.getDebTo3());
			hp.put("DebTo4", bean.getDebTo4());
			hp.put("DebTo5", bean.getDebTo5());
			hp.put("DocType", bean.getDocType());
			hp.put("EndDate", bean.getEndDate());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("Type", bean.getType());
			
			
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
	public List<Map<String, Object>> getallocationReportList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getallocationReportList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AllocateStatus", bean.getAllocateStatus());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("SettlementType", bean.getSettlementType());
			hp.put("StartDate", bean.getStartDate());
			
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
	public List<Map<String, Object>> getPayRecRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getPayRecRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("PayrecType", bean.getPayrecType());
			hp.put("ShowAllFields", bean.getShowAllFields());
			hp.put("StartDate", bean.getStartDate());
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
	public List<Map<String, Object>> getClaimPaidRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getClaimPaidRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProductId", bean.getProductId());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getRetroRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getRetroRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("StartDate", bean.getStartDate());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getTreatyWithdrawRegisterList(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getTreatyWithdrawRegisterList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("TreatyMainClass", bean.getTreatyMainClass());
			hp.put("TreatyType", bean.getTreatyType());
			hp.put("UwYear", bean.getUwYear());
			
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
	public List<Map<String, Object>> getJVReports(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getJVReports");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("EndDate", bean.getEndDate());
			hp.put("JournalType", bean.getJournalType());
			hp.put("StartDate", bean.getStartDate());
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
	public List<Map<String, Object>> getInstallmentDueReport(ReportsBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Reports.getInstallmentDueReport");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AllocationYN", bean.getAllocationYN());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BrokerId", bean.getBrokerId());
			hp.put("CedingId", bean.getCedingId());
			hp.put("EndDate", bean.getEndDate());
			hp.put("ProductId", bean.getProductId());
			
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
