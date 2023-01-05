package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.RiskDetailsBean;

public class ApiForProportionality extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public boolean insertProportionalTreaty(RiskDetailsBean beanObj, String pid, boolean saveFlag, boolean amendId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		try {
			String link=getValueFromWebservice("maan.client.proportionality.firstpagesave"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProductId", pid);
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("SubProfitcenter", beanObj.getSubProfit_center());
			hp.put("PolicyBranch", beanObj.getPolBr());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("Broker", beanObj.getBroker());
			hp.put("TreatyNametype", beanObj.getTreatyName_type());
			hp.put("Month", beanObj.getMonth());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("InceptionDate",beanObj.getIncepDate());
			hp.put("ExpiryDate", beanObj.getExpDate());
			hp.put("AcceptanceDate", beanObj.getAccDate());
			hp.put("OrginalCurrency", beanObj.getOrginalCurrency());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Basis", beanObj.getBasis());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("Territoryscope", beanObj.getTerritoryscope());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("AccountingPeriod", beanObj.getAccountingPeriod());
			hp.put("ReceiptofStatements", beanObj.getReceiptofStatements());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("UmbrellaXL", beanObj.getUmbrellaXL());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("TreatynoofLine", beanObj.getTreatynoofLine());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("RunoffYear", beanObj.getRunoffYear());
			hp.put("LocBankName", beanObj.getLocBankName());
			hp.put("LocCreditPrd", beanObj.getLocCreditPrd());
			hp.put("LocCreditAmt", beanObj.getLocCreditAmt());
			hp.put("LocBeneficerName", beanObj.getLocBeneficerName());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RenewalcontractNo", beanObj.getRenewal_contract_no());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("FaclimitOrigCur", beanObj.getFaclimitOrigCur());
			hp.put("LimitOrigCur", beanObj.getLimitOrigCur());
			hp.put("EpiorigCur", beanObj.getEpi_origCur());
			hp.put("OurEstimate", beanObj.getOurEstimate());
			hp.put("XlCost", beanObj.getXlCost());
			hp.put("CedRetent", beanObj.getCedReten());
			hp.put("Epi", beanObj.getEpi());
			hp.put("ShareWritten", beanObj.getShareWritt());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("CedRetenType", beanObj.getCedRetenType());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("MaxLimitProduct", beanObj.getMaxLimit_Product());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			hp.put("LimitPerVesselOC", beanObj.getLimitPerVesselOC());
			hp.put("LimitPerLocationOC", beanObj.getLimitPerLocationOC());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyLimitsurplusOurShare", beanObj.getTreatyLimitsurplusOurShare());
			hp.put("LimitOrigCurPml", beanObj.getLimitOrigCurPml());
			hp.put("LimitOrigCurPmlOS", beanObj.getLimitOrigCurPmlOS());
			hp.put("TreatyLimitsurplusOCPml", beanObj.getTreatyLimitsurplusOCPml());
			hp.put("TreatyLimitsurplusOCPmlOS", beanObj.getTreatyLimitsurplusOCPmlOS());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("MinimumRate", beanObj.getMinimumRate());
			hp.put("MaximumRate", beanObj.getMaximumRate());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("MinPremium", beanObj.getMinPremium());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("LayerProposalNo", beanObj.getLayerProposalNo());
			hp.put("BaseLoginId", beanObj.getBaseLoginID());
			hp.put("FlagStatus", saveFlag);
			hp.put("AmendStatus", amendId);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					beanObj.setContractGendration(json.get("Result")==null?"":json.get("Result").toString());
					beanObj.setProposal_no(json.get("ProposalNo")==null?"":json.get("ProposalNo").toString());
					savFlg=true;
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}


	@SuppressWarnings("unchecked")
	public boolean InsertRemarkDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg = false;
		try {
			String link=getValueFromWebservice("maan.client.proportionality.insertremarks"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			if(beanObj.getRemarkSNo()!=null) {
				for(int i=0;i<beanObj.getRemarkSNo().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("RemarkSNo", beanObj.getRemarkSNo().get(i));
					abj.put("Description", beanObj.getDescription().get(i));
					abj.put("Remark1", beanObj.getRemark1().get(i));
					abj.put("Remark2", beanObj.getRemark2().get(i));
					array.add(abj);
			}
			}
			hp.put("RemarksList", array);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				if("Success".equals(json.get("Result"))) {
					savFlg=true;
				}else {
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}
	@SuppressWarnings("unchecked")
	public boolean insertCedentRetention(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg = false;
		try {
			String link=getValueFromWebservice("maan.client.proportionality.insertcedentretent"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("RetentionYN", beanObj.getRetentionYN());
		
			if(beanObj.getRetSNo()!=null) {
				for(int i=0;i<beanObj.getRetSNo().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("RetentSNo", beanObj.getRetSNo().get(i));
					abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
					abj.put("CoversubdepartId", beanObj.getCoversubdepartId().get(i));
					abj.put("RetBusinessType", beanObj.getRetBusinessType().get(i));
					abj.put("RetType", beanObj.getRetType().get(i));
					abj.put("RetBasis", beanObj.getRetBasis().get(i));
					abj.put("Firstretention", beanObj.getFirstretention().get(i));
					abj.put("Secondretention", beanObj.getSecondretention().get(i));
					abj.put("RetTreatyFST", beanObj.getRetTreatyFST().get(i));
					abj.put("RetTreatySST", beanObj.getRetTreatySST().get(i));
					abj.put("RetEventFST", beanObj.getRetEventFST().get(i));
					abj.put("RetEventSST", beanObj.getRetEventSST().get(i));
					array.add(abj);
			}
			}
			hp.put("CedentList", array);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				if("Success".equals(json.get("Result"))) {
					savFlg=true;
				}else {
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}


	@SuppressWarnings("unchecked")
	public String getShortname(String branchCode) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("BranchCode", branchCode);
			link=getActualLink(link,hp);
			
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
	public int getEditMode(String proposalNo) {
		String result="0";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("proposalNo", proposalNo);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}


	@SuppressWarnings("unchecked")
	public Map<String, Object> riskDetailsEditMode(RiskDetailsBean beanObj, boolean contractMode) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.riskDetailsEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractMode", contractMode);
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("ProposalNo", beanObj.getProposal_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GetRemarksDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.GetRemarksDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			
			link=getActualLink(link,hp);
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
	public List<Map<String, Object>> getGetRetDetails(String proposal_no,String branchCode,String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.getGetRetDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposal_no);
			hp.put("branchCode", branchCode);
			hp.put("productId", productId);
			link=getActualLink(link,hp);
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
	public Map<String, Object> BaseLayerStatus(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.treasury.BaseLayerStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", beanObj.getProposal_no());
			hp.put("branchCode", beanObj.getBranchCode());
			hp.put("productId", pid);
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	
	@SuppressWarnings("unchecked")
	public int CommissionTypeCount(RiskDetailsBean bean) {
		int count = 0;
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.CommissionTypeCount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("proposalNo", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CommissionType", bean.getCommissionType());
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				count = (int) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRetentionDetails(RiskDetailsBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getRetentionDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CedingCo", bean.getCedingCo());
			hp.put("UwYear", bean.getUwYear());
			hp.put("Productid", bean.getProduct_id());
			
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
	public boolean GetShareValidation(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		try {
			String link=getValueFromWebservice("maan.client.proportionality.GetShareValidation"); 
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposal_no());
			link=getActualLink(link,hp);
						
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				savFlg = (boolean) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}


	@SuppressWarnings("unchecked")
	public String CancelProposal(RiskDetailsBean bean, String newProposal) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.CancelProposal");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("NewProposal", newProposal);
			link=getActualLink(link,hp);
			
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
	public List<Map<String, Object>> getprofitCommissionEnable(RiskDetailsBean bean, String type) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getprofitCommissionEnable");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BaseLayer", bean.getBaseLayer());
			hp.put("Type", type);
			
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
	public String getprofitCommissionDelete(RiskDetailsBean bean) {
	
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getprofitCommissionDelete");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProfitSno", bean.getProfitSno());
			
			link=getActualLink(link,hp);
			
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
	public List<Map<String, Object>> getprofitCommissionEdit(RiskDetailsBean bean) {
	
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getprofitCommissionEdit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProfitSno", bean.getProfitSno());
			link=getActualLink(link,hp);
			
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
	public int getProfitListCount(RiskDetailsBean bean) {
		
		int result=0;
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getProfitListCount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ProposalNo", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("AmendId", bean.getAmendId());
			link=getActualLink(link,hp);
//No Link			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (int) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ProfitCommissionList(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.ProfitCommissionList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CommissionType", bean.getCommissionType());
			hp.put("ContractNo", bean.getContractNo());
			
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
	public String insertProfitCommission(RiskDetailsBean bean) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.insertProfitCommission");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			for(int i=0;i<bean.getCommissionSNo().size();i++){
				JSONObject abj = new JSONObject();
				abj.put("CommissionSNo", bean.getCommissionSNo().get(i));
				abj.put("CommissionFrom", bean.getCommissionFrom().get(i));
				abj.put("CommissionTo", bean.getCommissionTo().get(i));
				abj.put("CommissionPer", bean.getCommissionPer().get(i));
				array.add(abj);
			}
			hp.put("CommissionSNo", array);
			hp.put("CommissionFrom", array);
			hp.put("CommissionTo", array);
			hp.put("CommissionPer", array);
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("AmendId", bean.getAmendId());
			hp.put("Productid", bean.getProduct_id());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("CommissionType", bean.getCommissionType());
			hp.put("LoginId", bean.getLoginId());
			
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
	public String CheckCrestaValue(RiskDetailsBean bean, String val, String countryId) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.CheckCrestaValue");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Val", val);
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("CountryId", countryId);
			link=getActualLink(link,hp);	
//No Link						
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
	public int getBonusListCount(RiskDetailsBean bean, String type) {
		
		int result=0;
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getBonusListCount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("AmendId", bean.getAmendId());
			hp.put("LayerNo", StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo());
			hp.put("Type", type);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (int) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public String MoveBonus(RiskDetailsBean bean) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.MoveBonus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ProposalNo", bean.getProposal_no());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("Productid", bean.getProduct_id());
			hp.put("BonusTypeId", bean.getBonusTypeId());
			
			for(int i=0;i<bean.getScaleFrom().size();i++){
				JSONObject abj = new JSONObject();
				abj.put("ScaleFrom",  bean.getScaleFrom().get(i));
				abj.put("ScaleTo",  bean.getScaleTo().get(i));
				abj.put("ScaleLowClaimBonus",  bean.getScaleLowClaimBonus().get(i));
				abj.put("ScaleSNo",  bean.getScaleSNo().get(i));
				array.add(abj);
			}
			hp.put("ScaleFrom", array);
			hp.put("ScaleTo", array);
			hp.put("ScaleLowClaimBonus", array);
			hp.put("ScaleSNo", array);
			hp.put("LoginId", bean.getLoginId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("AmendId", bean.getAmendId());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("LayerNo", StringUtils.isEmpty(bean.getLayerNo()) ? "0" : bean.getLayerNo());
			hp.put("QuotaShare", bean.getQuotaShare());
			hp.put("Bonusremarks", bean.getBonusremarks());
			hp.put("Fistpc", bean.getFistpc());
			hp.put("ProfitMont", bean.getProfitMont());
			hp.put("Subpc", bean.getSubpc());
			hp.put("SubProfitMonth", bean.getSubProfitMonth());
			hp.put("SubSeqCalculation", bean.getSubSeqCalculation());
			
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
	public List<Map<String, Object>> getScaleCommissionList(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getScaleCommissionList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("PageFor", bean.getPageFor());
			link=getActualLink(link,hp);
			
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
	public int getCrestaCount(RiskDetailsBean bean) {
		int count = 0;
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getCrestaCount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				count = (int) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCrestaDetailList(RiskDetailsBean bean) {

		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getCrestaDetailList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			
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
	public String insertCrestaDetails(RiskDetailsBean bean) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.insertCrestaDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			
			for(int i=0;i<bean.getCrestaId().size();i++){
				JSONObject abj = new JSONObject();
				abj.put("CrestaId",  bean.getCrestaId().get(i));
				abj.put("CrestaName",  bean.getCrestaName().get(i));
				abj.put("CurrencyId",  bean.getCurrencyId().get(i));
				abj.put("AccRisk",  bean.getAccRisk().get(i));
				abj.put("AccumulationDate",  bean.getAccumulationDate().get(i));
				abj.put("TerritoryCode",  bean.getTerritoryCode().get(i));
				abj.put("ScaleSNo",  bean.getScaleSNo().get(i));
				array.add(abj);
			}
				hp.put("CrestaId", array);
				hp.put("CrestaName", array);
				hp.put("CurrencyId", array);
				hp.put("AccRisk", array);
				hp.put("AccumulationDate", array);
				hp.put("TerritoryCode", array);
				hp.put("ScaleSNo", array);
				
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
	public List<Map<String, Object>> getLayerDuplicationCheck(RiskDetailsBean formObj) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getLayerDuplicationCheck");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LayerNo", formObj.getLayerNo());
			hp.put("LayerProposalNo", formObj.getLayerProposalNo());
			link=getActualLink(link,hp);
//No Link			
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
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean beanObj, int flag, String uWYear) {

		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getRetroContractDetailsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("UWYear", uWYear);
//No Link			
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
	public List<Map<String, Object>> getRetroContractDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.getRetroContractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("UWYear", beanObj.getUwYear());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("BranchCode", beanObj.getBranchCode());
//No Link			
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
	public Map<String, Object> showRetroContracts(RiskDetailsBean beanObj, String productId, boolean view) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.showRetroContracts");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProductId", productId);
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("View", view);
			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> showSecondPageData1(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.showSecondPageData1");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposal", beanObj.getProposal_no());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ProductId", pid);
			
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
	public List<Map<String, Object>> showLayerBrokerage(String layerProposalNo, RiskDetailsBean formobj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.showLayerBrokerage");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LayerProposalNo", layerProposalNo);
			
			link=getActualLink(link,hp);
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
	public Map<String, Object> showSecondPageData(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.showSecondPageData");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("RetroType", beanObj.getRetroType());
			if(beanObj.getRetroFinalList()!=null) {
				for(int i=0;i<beanObj.getRetroFinalList().size();i++){
					JSONObject abj = new JSONObject();
//					abj.put("CONTDET1", beanObj.getCONTDET1());
//					abj.put("CONTDET2", beanObj.getCONTDET2());
					abj.put("RetroDupContract", beanObj.getRetroDupContract());
					abj.put("RetroDupYerar", beanObj.getRetroDupYerar());
					array.add(abj);
				}
			}
				hp.put("RetroFinalList", array);
				hp.put("UwYear", beanObj.getUwYear());
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}


	@SuppressWarnings("unchecked")
	public Map<String, Object> saveSecondMode(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.saveSecondMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("Accounts", beanObj.getAccounts());
			hp.put("AggregateLimit", beanObj.getAggregate_Limit());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("CashLossLimit", beanObj.getCash_Loss_Limit());
			hp.put("CommissionQS", beanObj.getCommissionQ_S());
			hp.put("CommissionQSAmt", beanObj.getCommissionQ_SAmt());
			hp.put("CommissionSubClass", beanObj.getCommissionSubClass());
			hp.put("CommissionType", beanObj.getCommissionType());
			hp.put("Commissionsurp", beanObj.getCommission_surp());
			hp.put("CommissionsurpAmt", beanObj.getCommission_surpAmt());
			hp.put("ContractNo", beanObj.getContractno());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("CrestacommissionSubClass", beanObj.getCrestacommissionSubClass());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("EpiOSViewOC", beanObj.getEpiOSViewOC());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("Fistpc", beanObj.getFistpc());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("Interest", beanObj.getInterest());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LeaderUnderwriter", beanObj.getLeader_Underwriter());
			hp.put("LeaderUnderwritercountry", beanObj.getLeader_Underwriter_country());
			hp.put("LeaderUnderwritershare", beanObj.getLeader_Underwriter_share());
			hp.put("LimitOrigCur", beanObj.getLimitOrigCur());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("LocRate", beanObj.getLocRate());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossAdvise", beanObj.getLoss_Advise());
			hp.put("LossCarried", beanObj.getLossCarried());
			hp.put("LossParticipants", beanObj.getLossParticipants());
			hp.put("LossPopUp", beanObj.getLossPopUp());
			hp.put("LosscommissionSubClass", beanObj.getLosscommissionSubClass());
			hp.put("Lossreserve", beanObj.getLoss_reserve());
			hp.put("Lossyear", beanObj.getLossyear());
			hp.put("ManagementExpenses", beanObj.getManagement_Expenses());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("OccurrentLimit", beanObj.getOccurrent_Limit());
			hp.put("Orginalacqcost", beanObj.getOrginalacqcost());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("OuracqCost", beanObj.getOuracqCost());
			hp.put("Ourassessmentorginalacqcost", beanObj.getOurassessmentorginalacqcost());
			hp.put("OverRidder", beanObj.getOverRidder());
			hp.put("PortfolioinoutLoss", beanObj.getPortfolio_inout_Loss());
			hp.put("PortfolioinoutPremium", beanObj.getPortfolio_inout_Premium());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserve", beanObj.getPremium_Reserve());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("PreviousendoDate", beanObj.getPreviousendoDate());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", productId);
			hp.put("ProfitCarried", beanObj.getProfitCarried());
			hp.put("ProfitCarriedForYear", beanObj.getProfitCarriedForYear());
			hp.put("ProfitCommission", beanObj.getProfitCommission());
			hp.put("ProfitCommissionPer", beanObj.getProfitCommissionPer());
			hp.put("ProfitMont", beanObj.getProfitMont());
			hp.put("ProfitPopUp", beanObj.getProfitPopUp());
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RetentionPercentage", beanObj.getRetentionPercentage());
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			if(beanObj.getRetroList()!=null) {
			for(int i=0;i<beanObj.getRetroList().size();i++){
				JSONObject abj = new JSONObject();
				abj.put("PercentRetro", beanObj.getPercentRetro().get(i));
				abj.put("RetroCeding", beanObj.getRetroCeding().get(i));
				abj.put("PercentRetro", beanObj.getPercentRetro().get(i));
				array.add(abj);
			}
			}
			hp.put("RetroList", array);
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("Setup", beanObj.getSetup());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("ShareValue", beanObj.getShareValue());
			hp.put("SlidePopUp", beanObj.getSlidePopUp());
			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("SlidecommissionSubClass", beanObj.getSlidecommissionSubClass());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SubProfitMonth", beanObj.getSubProfitMonth());
			hp.put("SubSeqCalculation", beanObj.getSubSeqCalculation());
			hp.put("Subpc", beanObj.getSubpc());
			hp.put("SuperProfitCommission", beanObj.getSuperProfitCommission());
			hp.put("Tax", beanObj.getTax());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("acquisitionCost", beanObj.getAcquisition_Cost());
			
			
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
	public String checkAvialability(RiskDetailsBean beanObj, String pid) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.checkAvialability");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Proposalno", beanObj.getProposal_no());
			hp.put("ProductId", pid);
//No Link			
			link=getActualLink(link,hp);
			
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
	public String checkProductMatch(String pid, String proposal, boolean contarctMode) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.checkProductMatch");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Proposalno", proposal);
			hp.put("ContarctMode", contarctMode);
			hp.put("ProductId", pid);
			link=getActualLink(link,hp);
			
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
	public boolean updateProportionalTreatyDao(RiskDetailsBean beanObj, String pid) {
		boolean savFlg = false;
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.updateProportionalTreatyDao");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("AcceptanceDate", beanObj.getAccDate());
			hp.put("AccountingPeriod", beanObj.getAccountingPeriod());
			hp.put("AmendId", beanObj.getAmendId());
//			hp.put("AmendStatus", beanObj.AmendStatus());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLoginId", beanObj.getBaseLoginID());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedRetenType", beanObj.getCedRetenType());
			hp.put("CedRetent", beanObj.getCedReten());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("Edit", beanObj.getEdit());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("EpiorigCur", beanObj.getEpi_origCur());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpiryDate", beanObj.getExpDate());
			hp.put("FaclimitOrigCur", beanObj.getFaclimitOrigCur());
//			hp.put("FlagStatus", beanObj.getFlagStatus());
			hp.put("InceptionDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("LayerProposalNo", beanObj.getLayerProposalNo());
			hp.put("LimitOrigCur", beanObj.getLimitOrigCur());
			hp.put("LimitOrigCurPml", beanObj.getLimitOrigCurPml());
			hp.put("LimitOrigCurPmlOS", beanObj.getLimitOrigCurPmlOS());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("LimitPerLocationOC", beanObj.getLimitPerLocationOC());
			hp.put("LimitPerVesselOC", beanObj.getLimitPerVesselOC());
			hp.put("LocBankName", beanObj.getLocBankName());
			hp.put("LocBeneficerName", beanObj.getLocBeneficerName());
			hp.put("LocCreditAmt", beanObj.getLocCreditAmt());
			hp.put("LocCreditPrd", beanObj.getLocCreditPrd());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MaxLimitProduct", beanObj.getMaxLimit_Product());
			hp.put("MaximumRate", beanObj.getMaximumRate());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("MinPremium", beanObj.getMinPremium());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("MinimumRate", beanObj.getMinimumRate());
			hp.put("Month", beanObj.getMonth());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("OrginalCurrency", beanObj.getOrginalCurrency());
			hp.put("OurEstimate", beanObj.getOurEstimate());
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("PolicyBranch", beanObj.getPolBr());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProcessId", beanObj.getProcessId());
			hp.put("ProductId", pid);
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("ReceiptofStatements", beanObj.getReceiptofStatements());
			hp.put("RenewalcontractNo", beanObj.getRenewal_contract_no());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("RunoffYear", beanObj.getRunoffYear());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareWritten", beanObj.getShareWritt());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubProfitcenter", beanObj.getSubProfit_center());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("Territoryscope", beanObj.getTerritoryscope());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyLimitsurplusOCPml", beanObj.getTreatyLimitsurplusOCPml());
			hp.put("TreatyLimitsurplusOCPmlOS", beanObj.getTreatyLimitsurplusOCPmlOS());
			hp.put("TreatyLimitsurplusOurShare", beanObj.getTreatyLimitsurplusOurShare());
			hp.put("TreatyNametype", beanObj.getTreatyName_type());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("TreatynoofLine", beanObj.getTreatynoofLine());
			hp.put("UmbrellaXL", beanObj.getUmbrellaXL());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("XlCost", beanObj.getXlCost());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					beanObj.setContractGendration(json.get("Result")==null?"":json.get("Result").toString());
					savFlg=true;
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> viewRiskDetails(RiskDetailsBean beanObj, String pid) {

		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.viewRiskDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("ProductId", beanObj.getProposal_no());
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("SubProfitCenter", beanObj.getSubProfit_center());
			
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
	public Map<String, Object> saveRiskDeatilsSecondForm(RiskDetailsBean beanObj, String productId) {

		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.saveRiskDeatilsSecondForm");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("Accounts", beanObj.getAccounts());
			hp.put("AcquisitionCost", beanObj.getAcquisition_Cost());
			hp.put("AggregateLimit", beanObj.getAggregate_Limit());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLayerYN", beanObj.getBaseLayerYN());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("CashLossLimit", beanObj.getCash_Loss_Limit());
			hp.put("CeaseStatus", beanObj.getCeaseStatus());
			hp.put("CommissionQS", beanObj.getCommissionQ_S());
			hp.put("CommissionQSAmt", beanObj.getCommissionQ_SAmt());
			hp.put("CommissionSubClass", beanObj.getCommissionSubClass());
			hp.put("CommissionType", beanObj.getCommissionType());
			hp.put("Commissionsurp", beanObj.getCommission_surp());
			hp.put("CommissionsurpAmt", beanObj.getCommission_surpAmt());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("ContractGendration", beanObj.getContractGendration());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("CrestacommissionSubClass", beanObj.getCrestacommissionSubClass());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndNo", beanObj.getEndorsmentno());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("EpiOSViewOC", beanObj.getEpiOSViewOC());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("Fistpc", beanObj.getFistpc());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("Interest", beanObj.getInterest());
			hp.put("Lay", beanObj.getLay());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("LeaderUnderwriter", beanObj.getLeader_Underwriter());
			hp.put("LeaderUnderwritercountry", beanObj.getLeader_Underwriter_country());
			hp.put("LeaderUnderwritershare", beanObj.getLeader_Underwriter_share());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("LocRate", beanObj.getLocRate());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossAdvise", beanObj.getLoss_Advise());
			hp.put("LossCarried", beanObj.getLossCarried());
			hp.put("LossParticipants", beanObj.getLossParticipants());
			hp.put("LossPopUp", beanObj.getLossPopUp());
			hp.put("LosscommissionSubClass", beanObj.getLosscommissionSubClass());
			hp.put("Lossreserve", beanObj.getLoss_reserve());
			hp.put("Lossyear", beanObj.getLossyear());
			hp.put("ManagementExpenses", beanObj.getManagement_Expenses());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("OccurrentLimit", beanObj.getOccurrent_Limit());
			hp.put("Orginalacqcost", beanObj.getOrginalacqcost());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("OuracqCost", beanObj.getOuracqCost());
			hp.put("Ourassessmentorginalacqcost", beanObj.getOurassessmentorginalacqcost());
			hp.put("OverRidder", beanObj.getOverRidder());
			hp.put("PortfolioinoutLoss", beanObj.getPortfolio_inout_Loss());
			hp.put("PortfolioinoutPremium", beanObj.getPortfolio_inout_Premium());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserve", beanObj.getPremium_Reserve());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", productId);
			hp.put("ProfitCarried", beanObj.getProfitCarried());
			hp.put("ProfitCarriedForYear", beanObj.getProfitCarriedForYear());
			hp.put("ProfitCommission", beanObj.getProfitCommission());
			hp.put("ProfitCommissionPer", beanObj.getProfitCommissionPer());
			hp.put("ProfitMont", beanObj.getProfitMont());
			hp.put("Proposalno", beanObj.getProposal_no());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetentionPercentage", beanObj.getRetentionPercentage());
			
			if(beanObj.getRetroList()!=null) {
				for(int i=0;i<beanObj.getRetroList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("PercentRetro",beanObj.getPercentRetro().get(i));
					abj.put("RetroCeding",beanObj.getRetroCeding().get(i));
					abj.put("RetroYear",beanObj.getRetroYear().get(i));
					array.add(abj);
					
				}
			}
			hp.put("RetroList", array);
			
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("Setup", beanObj.getSetup());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("ShareValue", beanObj.getShareValue());
			hp.put("SlidePopUp", beanObj.getSlidePopUp());
			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("SlidecommissionSubClass", beanObj.getSlidecommissionSubClass());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SubProfitMonth", beanObj.getSubProfitMonth());
			hp.put("SubSeqCalculation", beanObj.getSubSeqCalculation());
			hp.put("Subpc", beanObj.getSubpc());
			hp.put("SuperProfitCommission", beanObj.getSuperProfitCommission());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("Tax", beanObj.getTax());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			
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
	public Map<String, Object> showSecondpageEditItems(RiskDetailsBean beanObj, String pid, String proposalNo) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.proportionality.showSecondpageEditItems");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProposalNo", beanObj.getProposal_no());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

}
