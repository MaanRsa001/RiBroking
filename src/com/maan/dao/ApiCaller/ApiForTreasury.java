package com.maan.dao.ApiCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.ClaimBean;
import com.maan.bean.TreasuryBean;

public class ApiForTreasury extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReceiptList(String transType, String branchCode, String type,TreasuryBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptlist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("TransType", transType);
			hp.put("PaymentNoSearch", bean.getPaymentNoSearch());
			hp.put("BrokerNameSearch", bean.getBrokerNameSearch());
			hp.put("CompanyNameSearch", bean.getCompanyNameSearch());
			hp.put("RemarksSearch", bean.getRemarksSearch());
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
	public List<Map<String, Object>> getReceiptReversalList(String transType, String branchCode, String type,TreasuryBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptreversallist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("TransType", transType);
			hp.put("PaymentNoSearch", bean.getPaymentNoSearch());
			hp.put("BrokerNameSearch", bean.getBrokerNameSearch());
			hp.put("CompanyNameSearch", bean.getCompanyNameSearch());
			hp.put("RemarksSearch", bean.getRemarksSearch());
			hp.put("SearchType", type);
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				array = (JSONArray) parser.parse(responseStr);
				//array = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReceiptAllocate(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptallocatelist"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("TransType", bean.getTransType());
			hp.put("AllocateType", bean.getAllocateType());
			hp.put("PaymentNoSearch", bean.getPaymentNoSearch());
			hp.put("BrokerNameSearch", bean.getBrokerNameSearch());
			hp.put("CompanyNameSearch", bean.getCompanyNameSearch());
			hp.put("RemarksSearch", bean.getRemarksSearch());
			hp.put("CurrencySearch", bean.getCurrencySearch());
			hp.put("Type", bean.getType());
			hp.put("SearchType", bean.getSearchType());
			hp.put("Payamount", bean.getPayamount());
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				array = (JSONArray) parser.parse(responseStr);
				//array = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public boolean insertReceiptNO(TreasuryBean bean, String branchCode) {
		boolean result=false;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptsave"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("Broker", bean.getBroker());
			hp.put("CedingCompany", bean.getCedingCo());
			hp.put("Currency", bean.getCurrency());
			hp.put("PaymentAmount", bean.getPaymentamount());
			hp.put("ExchangeRate", bean.getExrate());
			hp.put("ReceiptBankId", bean.getReceiptBankId());
			hp.put("ProductId", bean.getProid());
			hp.put("DepartmentId", bean.getDept_no());
			hp.put("TransactionDate", bean.getTr_date());
			hp.put("BaseCurrencyAmount", bean.getBaseCurrencyAmount());
			hp.put("TransType", bean.getTransType());
			hp.put("TransactionType", bean.getTransactionType());
			hp.put("BranchCode", branchCode);
			hp.put("BankCharges",bean.getBankCharges().replaceAll(",", ""));
			hp.put("NetAmount", bean.getNetAmt().replaceAll(",", ""));
			hp.put("Remarks", bean.getRemarks());
			hp.put("WithHoldingTaxOC", bean.getWithHoldingTaxOC().replaceAll(",", ""));
			hp.put("AmendDate", bean.getAmend_date());
			hp.put("LoginId", bean.getLogin_id());
			hp.put("TxtDiffPer", bean.getTxtDiffPer());
			hp.put("ConvertedDiffAmount", bean.getConvertedDiffAmount());
			hp.put("RevPayReceiptNo", bean.getPay_rec_no());
			hp.put("PremiumLavy", bean.getPremiumLavy());
			int LoopCount=Integer.parseInt(bean.getHideRowCnt());
			if(bean.getCedingCompanyValList()!=null) {
				for(int i=0;i<LoopCount;i++) {	
					JSONObject abj = new JSONObject();
					abj.put("CurrencyValList", bean.getCedingCompanyValList().get(i));
					abj.put("PayamountValList", bean.getPayamountValList().get(i));
					abj.put("ExachangeValList", bean.getExachangeValList().get(i));
					abj.put("RowamountValList", bean.getRowamountValList().get(i));
					abj.put("SetExcRateValList", bean.getSetExcRateValList().get(i));
					abj.put("ConRecCurValList", bean.getConRecCurValList().get(i));
					abj.put("RecNoValList", bean.getRecNoValList().get(i));
					array.add(abj);
			}
			}
			hp.put("PaymentReceiptList", array);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					bean.setSerial_no(json.get("Result")==null?"":json.get("Result").toString());
					result=true;
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> receiptdeteails(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptdetail"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("Broker", bean.getBroker());
			hp.put("CedingCompany", bean.getCedingCo());
			hp.put("Currency", bean.getCurrency());
			hp.put("PaymentAmount", bean.getPaymentamount());
			hp.put("ExchangeRate", bean.getExrate());
			hp.put("ReceiptBankId", bean.getReceiptBankId());
			hp.put("ProductId", bean.getProid());
			hp.put("DepartmentId", bean.getDept_no());
			hp.put("TransactionDate", bean.getTr_date());
			
			hp.put("TransType", bean.getTransType());
			hp.put("TransactionType", bean.getTransactionType());
			hp.put("BranchCode", branchCode);
			hp.put("BankCharges",bean.getBankCharges().replaceAll(",", ""));
			hp.put("NetAmount", bean.getNetAmt().replaceAll(",", ""));
			hp.put("Remarks", bean.getRemarks());
			hp.put("WithHoldingTaxOC", bean.getWithHoldingTaxOC().replaceAll(",", ""));
			hp.put("AmendDate", bean.getAmend_date());
			hp.put("LoginId", bean.getLogin_id());
			hp.put("TxtDiffPer", bean.getTxtDiffPer());
			hp.put("ConvertedDiffAmount", bean.getConvertedDiffAmount());
			hp.put("RevPayReceiptNo", bean.getPay_rec_no());
			hp.put("PremiumLavy", bean.getPremiumLavy());
			hp.put("BaseCurrencyAmount", bean.getBaseCurrencyAmount());
			hp.put("PaymentReceiptList", array);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				if(bean.getErrors()==null)
				result=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getReceiptEdit(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptedit"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("paymentReceiptNo", bean.getSerial_no());
			hp.put("branchCode", branchCode);
			link=getActualLink(link,hp);
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				//array = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getReceiptGeneration(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.client.treasury.recieptview"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Broker", bean.getBroker());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("BranchCode", branchCode);
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<Map<String,Object>>> allocateView(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.client.treasury.allocateview"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("PayRecNo", bean.getPay_rec_no());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("BranchCode", branchCode);
			hp.put("Flag", bean.getFlag());
			hp.put("AlloccurrencyId", bean.getAlloccurrencyid());
			hp.put("CurrecncyValue", bean.getCurrencyValue());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllocatedStatus(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getAllocatedStatus"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AlloccurrencyId", bean.getAlloccurrencyid());
			hp.put("BranchCode", branchCode);
			hp.put("BrokerId", bean.getBrokerid());
			hp.put("BrokerName", bean.getBrokername());
			hp.put("PayRecNo", bean.getPay_rec_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				array=(JSONArray) parser.parse(responseStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReversalInfo(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getReversalInfo"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Broker", bean.getBroker());
			hp.put("CedingCo", bean.getCedingCo());
			hp.put("Currency", bean.getCurrency());
			hp.put("CurrencyValue", bean.getCurrencyValue());
			hp.put("Flag", bean.getFlag());
			hp.put("PayRecNo", bean.getPay_rec_no());
			hp.put("PaymentAmount", bean.getPaymentamount());
			hp.put("TransType", bean.getTransType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allocateDetails(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.allocateDetails"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Broker", bean.getBrokername());
			hp.put("CedingCo", bean.getCedingCompanyName());
			hp.put("PayRecNo", bean.getPay_rec_no());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("Type", bean.getType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				array = (JSONArray) parser.parse(responseStr);
				//array=(JSONArray) json.get("Result"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> reverseView(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.reverseView"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("SerialNo", bean.getSerial_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTransContract(TreasuryBean bean, String branchCode,
			Map<String, String> receivePayAmountMap) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getTransContract"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccountDate", bean.getAccountDate());
			hp.put("AllocType", bean.getAllocType());
			hp.put("AlloccurrencyId", bean.getAlloccurrencyid());
			hp.put("AmendDate", bean.getAmend_date());
			hp.put("BranchCode", branchCode);
			hp.put("BrokerId", bean.getBrokerid());
			hp.put("CedingId", bean.getCedingid());
			hp.put("ChkBox", bean.getChkbox());
			hp.put("CurrencyValue", bean.getCurrencyValue());
			
			if(bean.getReceivePayAmounts()!=null) {
				for(int i=0;i<bean.getReceivePayAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("ReceivePayAmounts", bean.getReceivePayAmounts().get(i));
					abj.put("TransactionNo", bean.getTransactionNos().get(i));
					abj.put("PreviousValue", bean.getPreviousValue().get(i));
					array.add(abj);
				}
			}
			
			hp.put("TransContractListReq", array);
			
			hp.put("HideprocessType", bean.getHideprocessType());
			hp.put("Loginid", bean.getLogin_id());
			hp.put("PayAmounts", bean.getPayAmounts());
			hp.put("Payrecno", bean.getPay_rec_no());
			hp.put("Policyno", bean.getPolicyno());
			//hp.put("PreviousValue", bean.getPreviousValue());
			hp.put("Serialno", bean.getSerial_no());
			hp.put("TotalRecCount", bean.getTotalreccount());
			hp.put("TransType", bean.getTransType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				array = (JSONArray) parser.parse(responseStr);
				//array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDirectCeding(String brokerId, String branchId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getDirectCeding"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchId", branchId);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				array = (JSONArray) parser.parse(responseStr);
				//array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getShortname(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.treasury.getShortname"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result=(String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTreasuryJournalView(TreasuryBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getTreasuryJournalView"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AllocationNo", bean.getAllocationNo());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("Type", bean.getType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public void getRetroallocateTransaction(TreasuryBean bean, String branchCode,
			Map<String, String> receivePayAmountMap) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getRetroallocateTransaction"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccountDate", bean.getAccountDate());
			hp.put("AllOccurencyId", bean.getAlloccurrencyid());
			hp.put("AmendDate", bean.getAmend_date());
			
			hp.put("BranchCode", branchCode);
			hp.put("BrokerId", bean.getBrokerid());
			hp.put("CedingId", bean.getCedingid());
			hp.put("ContractNo", bean.getContractno());
			
			if(bean.getReceivePayAmounts()!=null) {
				for(int i=0;i<bean.getReceivePayAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("ReceivePayAmounts", bean.getReceivePayAmounts().get(i));
					abj.put("TransactionNo", bean.getTransactionno());
					array.add(abj);
				}
			}
			
			hp.put("GetTransContractListReq", array);
			
			hp.put("HideprocessType", bean.getHideprocessType());
			hp.put("LoginId", bean.getLogin_id());
			hp.put("PayRecNo", bean.getPay_rec_no());
			hp.put("PolicyNo", bean.getPolicyno());
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("SubClass", bean.getSubClass());
			hp.put("TransType", bean.getTransType());
//			hp.put("alOccurencyId", bean.getalOccurencyId());
			hp.put("productName", bean.getProductname());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRetroTransContract(TreasuryBean bean, String branchCode,
			Map<String, String> receivePayAmountMap) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getRetroTransContract"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AllocType", bean.getAllocType());
			hp.put("Alloccurrencyid", bean.getAlloccurrencyid());
			hp.put("BranchCode", branchCode);
			hp.put("Brokerid", bean.getBrokerid());
			hp.put("Cedingid", bean.getCedingid());
			
			if(bean.getReceivePayAmounts()!=null) {
				for(int i=0;i<bean.getReceivePayAmounts().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("ReceivePayAmounts", bean.getReceivePayAmounts().get(i));
					abj.put("TransactionNo", bean.getTransactionno());
					array.add(abj);
				}
			}
			hp.put("GetTransContractListReq", array);
			
			hp.put("TransType", bean.getTransType());

			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReceiptTreasuryGeneration(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getReceiptTreasuryGeneration"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Broker", bean.getBroker());
			hp.put("Payrecno", bean.getPay_rec_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReceiptViewList(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getReceiptViewList"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Serialno", bean.getSerial_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getCurrecyAmount(String branchCode, String currValu, String serialNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.treasury.getCurrecyAmount"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CurrValu", currValu);
			hp.put("SerialNo", serialNo);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result=(String) json.get("CurrecyAmount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSecondPageInfo(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.getSecondPageInfo"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("Serialno", bean.getSerial_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> reverseInsert(TreasuryBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.treasury.reverseInsert"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AllTillDate", bean.getAllTillDate());
			hp.put("AllocatedDate", bean.getAllocateddate());
			hp.put("BranchCode", branchCode);
			hp.put("ContractNo", bean.getContractno());
			hp.put("LoginId", bean.getLogin_id());
			hp.put("PaidAmount", bean.getPayamount());
			hp.put("PayRecNo", bean.getPay_rec_no());
//			hp.put("RetroPaidAmount", bean.getRetroPaidAmount());
			hp.put("RetroType", bean.getRetroType());
			hp.put("ReversalDate", bean.getReversalDate());
			hp.put("SerialNo", bean.getSerial_no());
			hp.put("TransType", bean.getTransType());
			hp.put("TransactionNo", bean.getTransactionno());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				if(bean.getErrors()==null)
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public void getallocateTransaction(TreasuryBean payform, String branchCode,
			Map<String, String> receivePayAmountMap) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.treasury.getallocateTransaction"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("TransType", payform.getTransType());
			hp.put("AlloccurrencyId", payform.getAlloccurrencyid());
			hp.put("BrokerId", payform.getBrokerid());
			hp.put("CedingId", payform.getCedingid());
			hp.put("AllocType", payform.getAllocType());
			hp.put("TotalRecCount", payform.getTotalreccount());
			hp.put("PayAmounts", payform.getPayAmounts());
			hp.put("ChkBox", payform.getChkbox());
			hp.put("BranchCode", branchCode);
			hp.put("AccountDate", payform.getAccountDate());
			hp.put("Serialno", payform.getSerial_no());
			hp.put("Loginid", payform.getLogin_id());
			hp.put("Policyno", payform.getPolicyno());
			hp.put("Payrecno", payform.getPay_rec_no());
			hp.put("HideprocessType", payform.getHideprocessType());
			
			if(payform.getTransactionContractList()!=null) {
				for(int i=0;i<payform.getTransactionContractList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("TransactionNo", payform.getTransactionno());
					abj.put("ReceivePayAmounts", payform.getReceivePayAmounts().get(i));
					array.add(abj);
				}
			}
				hp.put("GetTransContractListReq", array);
				
			hp.put("AmendDate", payform.getAmend_date());
			hp.put("CurrencyValue", payform.getCurrencyValue());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				payform.setErrors((JSONArray) json.get("ErrorMessage"));
				array=(JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
