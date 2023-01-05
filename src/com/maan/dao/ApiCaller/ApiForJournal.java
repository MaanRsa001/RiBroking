package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.JournalBean;

public class ApiForJournal extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public int insertInActiveOpenPeriod(String startDate, String endDate, String branchCode, String status,
			String journalname, String loginId) {
		int result = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.insertInActiveOpenPeriod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("EndDate", endDate);
			hp.put("Journalname", journalname);
			hp.put("LoginId", loginId);
			hp.put("StartDate", startDate);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (Integer) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getjounalList(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getjounalList");
			authorization= getValueFromWebservice("marine.insurance.auth");
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
	public List<Map<String, Object>> getOpenPeriodList(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getOpenPeriodList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
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
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStartDateList(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getStartDateList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
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
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEndDateList(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getEndDateList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
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
	
	@SuppressWarnings("unchecked")
	public String getForExDiffName(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		try {
			String link=getValueFromWebservice("maan.Journal.getForExDiffName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
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
	public int insertActiveOpenPeriod(String startDate, String endDate, String branchCode, String status,
			String journalname, String loginId) {
		int result = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.insertActiveOpenPeriod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("EndDate", endDate);
			hp.put("Journalname", journalname);
			hp.put("LoginId", loginId);
			hp.put("StartDate", startDate);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (Integer) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getShortname(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		try {
			String link=getValueFromWebservice("maan.Journal.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
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
	public List<Map<String, Object>> getSpcCurrencyList(JournalBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getSpcCurrencyList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("EndDate", bean.getEndDate());
			hp.put("JournalID", bean.getJournalID());
			hp.put("StartDate", bean.getStartDate());
			hp.put("Status", bean.getStatus());
			
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
	public List<Map<String, Object>> getJournalViews(JournalBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getJournalViews");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("EndDate", bean.getEndDate());
			hp.put("JournalID", bean.getJournalID());
			hp.put("StartDate", bean.getStartDate());
			hp.put("Status", bean.getStatus());
			
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
	public void ActivateInActivateLoginUsers(JournalBean bean, String status) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.Journal.ActivateInActivateLoginUsers");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("LoginId", bean.getLoginId());
			hp.put("Status", status);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getCountOpenPeriod(JournalBean bean,String sno) {
		int result = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.getCountOpenPeriod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("SNo", sno);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (Integer) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserDetails(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.getUserDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("LoginId", bean.getLoginId());
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
	public int getQuaterEndValidation(String sampledate, String branchCode) {
		int result = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.getQuaterEndValidation");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Sampledate", sampledate);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (Integer) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public int insertRetroProcess(String startDate, String endDate, String type, String branchCode) {
		int result = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Journal.insertRetroProcess");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("EndDate", endDate);
			hp.put("Type", type);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				if("Success".equals(json.get("Message"))) {
					result = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLedgerEntryList(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getLedgerEntryList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("OpendDate", bean.getOpendDate());
			hp.put("OpstartDate", bean.getOpstartDate());
			
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
	public void insertManualJV(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.insertManualJV");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendmentDate", bean.getAmendmentDate());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("Currency", bean.getCurrency());
			
			hp.put("ExchRate", bean.getExchRate());
			hp.put("ExcreditDC", bean.getExcreditDC());
			hp.put("ExdebitDC", bean.getExdebitDC());
			hp.put("LedClass", bean.getLedClass());
			
			if(bean.getLedgerId()!=null) {
				for(int i=0;i<bean.getLedgerId().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CreditDC", bean.getCreditDC().get(i));
					abj.put("CreditOC", bean.getCreditOC().get(i));
					abj.put("DebitDC", bean.getDebitDC().get(i));
					abj.put("DebitOC", bean.getDebitOC().get(i));
					abj.put("LedgerId", bean.getLedgerId().get(i));
					array.add(abj);
				}
			}
			
			hp.put("LedgerIdReq", array);
			
			hp.put("LoginId", bean.getLoginId());
			hp.put("Mode", bean.getMode());
			hp.put("Narration", bean.getNarration());
			hp.put("ReversalDate", bean.getReversalDate());
			hp.put("Shortname", bean.getShortname());
			hp.put("TranId", bean.getTranId());
			hp.put("TransactionDate", bean.getTransactionDate());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getViewLedgerDetails(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getViewLedgerDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ReversalStatus", bean.getReversalStatus());
			hp.put("TransId", bean.getTranId());
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
	public List<Map<String, Object>> getEditLedgerDetails(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Journal.getEditLedgerDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("TransId", bean.getTranId());
			hp.put("Mode", bean.getMode());
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
	public String getStartDateStatus(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		try {
			String link=getValueFromWebservice("maan.Journal.getStartDateStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("StartDate", bean.getStartDate());
			
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
	public String getEndDateStatus(JournalBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		try {
			String link=getValueFromWebservice("maan.Journal.getEndDateStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("EndDate", bean.getEndDate());
			
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

}
