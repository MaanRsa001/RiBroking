package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.RiskDetailsBean;

public class ApiForRetro extends ApiConfig implements Callable<Object>{
	String authorization = "";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public void firstInsert(RiskDetailsBean beanObj, String pid, boolean saveFlag, boolean amendId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Retro.firstInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("AccountingPeriod", beanObj.getAccountingPeriod());
			hp.put("AdjRate", beanObj.getAdjRate());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLoginID", beanObj.getBaseLoginID());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedReten", beanObj.getCedReten());
			hp.put("CedRetenType", beanObj.getCedRetenType());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("CessionExgRate", beanObj.getCessionExgRate());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeduchunPercent", beanObj.getDeduc_hunPercent());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("DepartId", beanObj.getDepartId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DummyCon", beanObj.getDummyCon());
			hp.put("EgnpiOffer", beanObj.getEgnpiOffer());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("EndorsmentNo", beanObj.getEndorsmentno());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("EpiorigCur", beanObj.getEpi_origCur());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpDate", beanObj.getExpDate());
			hp.put("FaclimitOrigCur", beanObj.getFaclimitOrigCur());
			hp.put("FaclimitOrigCurPml", beanObj.getFaclimitOrigCurPml());
			hp.put("FixedRate", beanObj.getFixedRate());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerNo", beanObj.getLayerNo());
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
			hp.put("MdPremium", beanObj.getM_dPremium());
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("MinPremium", beanObj.getMinPremium());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("MinimumRate", beanObj.getMinimumRate());
			hp.put("Month", beanObj.getMonth());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("OrginalCurrency", beanObj.getOrginalCurrency());
			hp.put("OurAssessment", beanObj.getOurAssessment());
			hp.put("OurEstimate", beanObj.getOurEstimate());
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("PolBr", beanObj.getPolBr());
			hp.put("PortfoloCovered", beanObj.getPortfoloCovered());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", pid);
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("ReceiptofStatements", beanObj.getReceiptofStatements());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("RunoffYear", beanObj.getRunoffYear());
			hp.put("SaveFlag", saveFlag);
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareWritt", beanObj.getShareWritt());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubPremium", beanObj.getSubPremium());
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
			hp.put("XlPremium", beanObj.getXlPremium());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			
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
	public String getShortname(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.Retro.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
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
	public List<Map<String, Object>> showSecondpageEditItems(RiskDetailsBean beanObj, String pid, String proposalNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.showSecondpageEditItems");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", proposalNo);
			
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
	public void InsertRemarkDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.InsertRemarkDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractno());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
			if(beanObj.getRemarkList()!=null) {
				for(int i=0;i<beanObj.getRemarkList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("Description", beanObj.getDescription().get(i));
					abj.put("Remark1", beanObj.getRemark1().get(i));
					abj.put("Remark2", beanObj.getRemark2().get(i));
					abj.put("RemarkSNo", beanObj.getRemarkSNo().get(i));
					array.add(abj);
				}
			}
			
			hp.put("RemarksList", array);
			
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
	public boolean checkAvialability(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.Retro.checkAvialability");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ProductId", pid);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
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
	public List<Map<String, Object>> viewRiskDetails(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.viewRiskDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("SubProfitCenter", beanObj.getSubProfit_center());
			
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
	public List<Map<String, Object>> GetRemarksDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.GetRemarksDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposalNo());
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
	public List<Map<String, Object>> showRetroCess(RiskDetailsBean beanObj, int mode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.showRetroCess");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("Mode", mode);
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
			if(beanObj.getRetroCessList()!=null) {
				for(int i=0;i<beanObj.getRetroCessList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("BrokerId", beanObj.getBrokerId());
					abj.put("CedingId", beanObj.getCedingId());
					abj.put("Commission", beanObj.getCommission().get(i));
					abj.put("ProStatus", beanObj.getProStatus());
					abj.put("SharSign", beanObj.getSharSign());
					abj.put("ShareWritt", beanObj.getShareWritt());
					array.add(abj);
				}
			}
			
			hp.put("RetroCessListReq", array);
			
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
	public List<Map<String, Object>> showSecondPageData1(String proposal, RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.showSecondPageData1");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ProductId", pid);
			hp.put("Proposal", proposal);
			
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
	public List<Map<String, Object>> showSecondPageData(String proposal, RiskDetailsBean formobj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.showSecondPageData");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", formobj.getBranchCode());
			hp.put("ProductId", pid);
			hp.put("Proposal", proposal);
			hp.put("ReMode", formobj.getReMode());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formobj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public int getEditMode(String proposalNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		int result = 0;
		
		try {
			String link=getValueFromWebservice("maan.Retro.getEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposal", proposalNo);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				//formobj.setErrors((JSONArray) json.get("ErrorMessage"));
				result = (Integer) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> saveSecondMode(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.saveSecondMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Accounts", beanObj.getAccounts());
			hp.put("AcquisitionCost", beanObj.getAcquisition_Cost());
			hp.put("AggregateLimit", beanObj.getAggregate_Limit());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("BrokerageYN", beanObj.getBrokerageYN());
			hp.put("BurningCost", beanObj.getBurningCost());
			hp.put("CashLossLimit", beanObj.getCash_Loss_Limit());
			hp.put("CeedODIYN", beanObj.getCeedODIYN());
			hp.put("CommissionQS", beanObj.getCommissionQ_S());
			hp.put("CommissionQSAmt", beanObj.getCommissionQ_SAmt());
			hp.put("CommissionQSYN", beanObj.getCommissionQ_SYN());
			hp.put("CommissionSubClass", beanObj.getCommissionSubClass());
			hp.put("CommissionType", beanObj.getCommissionType());
			hp.put("Commissionsurp", beanObj.getCommission_surp());
			hp.put("CommissionsurpAmt", beanObj.getCommission_surpAmt());
			hp.put("CommissionsurpYN", beanObj.getCommission_surpYN());
			hp.put("ContractNo", beanObj.getContractno());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("CrestacommissionSubClass", beanObj.getCrestacommissionSubClass());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementNo", beanObj.getEndorsmentno());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("Fistpc", beanObj.getFistpc());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("Interest", beanObj.getInterest());
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
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			
			if(beanObj.getCedingCompany()!=null) {
				for(int i=0;i<beanObj.getCedingCompany().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CedingCompany", beanObj.getCedingCompany().get(i));
					abj.put("Commission", beanObj.getCommission().get(i));
					abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
					abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
					abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
					abj.put("ShareSigned", beanObj.getShareSigned().get(i));
					array.add(abj);
				}
			}
			
			hp.put("NoRetroCessReq", array);
			
			hp.put("OccurrentLimit", beanObj.getOccurrent_Limit());
			hp.put("Orginalacqcost", beanObj.getOrginalacqcost());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("OthercostYN", beanObj.getOthercostYN());
			hp.put("OuracqCost", beanObj.getOuracqCost());
			hp.put("Ourassessmentorginalacqcost", beanObj.getOurassessmentorginalacqcost());
			hp.put("OverRidder", beanObj.getOverRidder());
			hp.put("OverRidderYN", beanObj.getOverRidderYN());
			hp.put("PortfolioinoutLoss", beanObj.getPortfolio_inout_Loss());
			hp.put("PortfolioinoutPremium", beanObj.getPortfolio_inout_Premium());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserve", beanObj.getPremium_Reserve());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("ProductId", productId);
			hp.put("ProfitCarried", beanObj.getProfitCarried());
			hp.put("ProfitCarriedForYear", beanObj.getProfitCarriedForYear());
			hp.put("ProfitCommission", beanObj.getProfitCommission());
			hp.put("ProfitCommissionPer", beanObj.getProfitCommissionPer());
			hp.put("ProfitMont", beanObj.getProfitMont());
			hp.put("ProfitPopUp", beanObj.getProfitPopUp());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ReMode", beanObj.getReMode());
			hp.put("ReinstAditionalPremiumpercent", beanObj.getReinstAditionalPremium_percent());
			hp.put("ReinstNo", beanObj.getReinstNo());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetroCommissionType", beanObj.getRetroCommissionType());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("Setup", beanObj.getSetup());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("SlidePopUp", beanObj.getSlidePopUp());
			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("SlidecommissionSubClass", beanObj.getSlidecommissionSubClass());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SubProfitMonth", beanObj.getSubProfitMonth());
			hp.put("SubSeqCalculation", beanObj.getSubSeqCalculation());
			hp.put("Subpc", beanObj.getSubpc());
			hp.put("SuperProfitCommission", beanObj.getSuperProfitCommission());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxYN", beanObj.getTaxYN());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			
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
	public void insertRetroCess(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.insertRetroCess");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			
			if(beanObj.getCedingCompany()!=null) {
				for(int i=0;i<beanObj.getCedingCompany().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CedingCompany", beanObj.getCedingCompany().get(i));
					abj.put("Commission", beanObj.getCommission().get(i));
					abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
					abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
					abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
					abj.put("ShareSigned", beanObj.getShareSigned().get(i));
					array.add(abj);
				}
			}

			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
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
	public void insertCrestaMaintable(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Retro.insertCrestaMaintable");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("ProposalNo", bean.getProposalNo());
			
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
	public void insertBonusDetails(RiskDetailsBean beanObj, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Retro.insertBonusDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossParticipants", beanObj.getLossParticipants());
			hp.put("PageFor", beanObj.getPageFor());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("Type", type);
			
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
	public void insertProfitCommissionMain(RiskDetailsBean beanObj, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Retro.insertProfitCommissionMain");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("CommissionType", beanObj.getCommissionType());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			
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
	public boolean instalMentPremium(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.Retro.instalMentPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("ExchangeRate", beanObj.getExchRate());
			
			if(beanObj.getInstallmentPremium()!=null) {
				for(int i=0;i<beanObj.getInstallmentPremium().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("InstallmentPremium", beanObj.getInstallmentPremium().get(i));
					abj.put("InstalmentDateList", beanObj.getInstalmentDateList().get(i));
					abj.put("PaymentDueDays", beanObj.getPaymentDueDays().get(i));
					array.add(abj);
				}
			}
			hp.put("InstalmentperiodReq", array);
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
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
	public List<Map<String, Object>> saveRiskDeatilsSecondForm(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.saveRiskDeatilsSecondForm");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Accounts", beanObj.getAccounts());
			hp.put("AcquisitionCost", beanObj.getAcquisition_Cost());
			hp.put("AggregateLimit", beanObj.getAggregate_Limit());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("BrokerageYN", beanObj.getBrokerageYN());
			hp.put("BurningCost", beanObj.getBurningCost());
			hp.put("CashLossLimit", beanObj.getCash_Loss_Limit());
			hp.put("CeedODIYN", beanObj.getCeedODIYN());
			hp.put("CommissionQS", beanObj.getCommissionQ_S());
			hp.put("CommissionQSAmt", beanObj.getCommissionQ_SAmt());
			hp.put("CommissionQSYN", beanObj.getCommissionQ_SYN());
			hp.put("CommissionSubClass", beanObj.getCommissionSubClass());
			hp.put("CommissionType", beanObj.getCommissionType());
			hp.put("Commissionsurp", beanObj.getCommission_surp());
			hp.put("CommissionsurpAmt", beanObj.getCommission_surpAmt());
			hp.put("CommissionsurpYN", beanObj.getCommission_surpYN());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("CrestacommissionSubClass", beanObj.getCrestacommissionSubClass());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementNo", beanObj.getEndorsmentno());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("Fistpc", beanObj.getFistpc());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("Interest", beanObj.getInterest());
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
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			
			if(beanObj.getCedingCompany()!=null) {
				for(int i=0;i<beanObj.getCedingCompany().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CedingCompany", beanObj.getCedingCompany().get(i));
					abj.put("Commission", beanObj.getCommission().get(i));
					abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
					abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
					abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
					abj.put("ShareSigned", beanObj.getShareSigned().get(i));
					array.add(abj);
				}
			}
			hp.put("NoRetroCessReq", array);
			
			hp.put("OccurrentLimit", beanObj.getOccurrent_Limit());
			hp.put("Orginalacqcost", beanObj.getOrginalacqcost());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("OthercostYN", beanObj.getOthercostYN());
			hp.put("OuracqCost", beanObj.getOuracqCost());
			hp.put("Ourassessmentorginalacqcost", beanObj.getOurassessmentorginalacqcost());
			hp.put("OverRidder", beanObj.getOverRidder());
			hp.put("OverRidderYN", beanObj.getOverRidderYN());
			hp.put("PortfolioinoutLoss", beanObj.getPortfolio_inout_Loss());
			hp.put("PortfolioinoutPremium", beanObj.getPortfolio_inout_Premium());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserve", beanObj.getPremium_Reserve());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("ProductId", productId);
			hp.put("ProfitCarried", beanObj.getProfitCarried());
			hp.put("ProfitCarriedForYear", beanObj.getProfitCarriedForYear());
			hp.put("ProfitCommission", beanObj.getProfitCommission());
			hp.put("ProfitCommissionPer", beanObj.getProfitCommissionPer());
			hp.put("ProfitMont", beanObj.getProfitMont());
			hp.put("ProfitPopUp", beanObj.getProfitPopUp());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ReMode", beanObj.getReMode());
			hp.put("ReinstAditionalPremiumpercent", beanObj.getReinstAditionalPremium_percent());
			hp.put("ReinstNo", beanObj.getReinstNo());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetroCommissionType", beanObj.getRetroCommissionType());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("Setup", beanObj.getSetup());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("SlidePopUp", beanObj.getSlidePopUp());
			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("SlidecommissionSubClass", beanObj.getSlidecommissionSubClass());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SubProfitMonth", beanObj.getSubProfitMonth());
			hp.put("SubSeqCalculation", beanObj.getSubSeqCalculation());
			hp.put("Subpc", beanObj.getSubpc());
			hp.put("SuperProfitCommission", beanObj.getSuperProfitCommission());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxYN", beanObj.getTaxYN());
			hp.put("TreatyLimitsurplusOC", beanObj.getTreatyLimitsurplusOC());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			
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
	public List<Map<String, Object>> showLayerBrokerage(String layerProposalNo, RiskDetailsBean formobj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.showLayerBrokerage");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LayerProposalNo", layerProposalNo);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formobj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public boolean updateProportionalTreatyDao(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.Retro.updateProportionalTreatyDao");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("AccountingPeriod", beanObj.getAccountingPeriod());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("CessionExgRate", beanObj.getCessionExgRate());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpDate", beanObj.getExpDate());
			hp.put("FixedRate", beanObj.getFixedRate());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LocBankName", beanObj.getLocBankName());
			hp.put("LocBeneficerName", beanObj.getLocBeneficerName());
			hp.put("LocCreditAmt", beanObj.getLocCreditAmt());
			hp.put("LocCreditPrd", beanObj.getLocCreditPrd());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("Month", beanObj.getMonth());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("OrginalCurrency", beanObj.getOrginalCurrency());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("PolicyBranch", beanObj.getPolBr());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", pid);
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("ReceiptofStatements", beanObj.getReceiptofStatements());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("RunoffYear", beanObj.getRunoffYear());
			hp.put("SubProfitCenter", beanObj.getSubProfit_center());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("Territoryscope", beanObj.getTerritoryscope());
			hp.put("TreatyNameType", beanObj.getTreatyName_type());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("TreatynoofLine", beanObj.getTreatynoofLine());
			hp.put("UmbrellaXL", beanObj.getUmbrellaXL());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("UwYear", beanObj.getUwYear());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				savFlg = (Boolean) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}

	@SuppressWarnings("unchecked")
	public boolean updateRiskProposal(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.Retro.updateRiskProposal");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("AccountingPeriod", beanObj.getAccountingPeriod());
			hp.put("AdjRate", beanObj.getAdjRate());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLoginID", beanObj.getBaseLoginID());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedReten", beanObj.getCedReten());
			hp.put("CedRetenType", beanObj.getCedRetenType());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("CessionExgRate", beanObj.getCessionExgRate());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeduchunPercent", beanObj.getDeduc_hunPercent());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("DepartId", beanObj.getDepartId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DummyCon", beanObj.getDummyCon());
			hp.put("EgnpiOffer", beanObj.getEgnpiOffer());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("EndorsmentNo", beanObj.getEndorsmentno());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("EpiorigCur", beanObj.getEpi_origCur());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpDate", beanObj.getExpDate());
			hp.put("FaclimitOrigCur", beanObj.getFaclimitOrigCur());
			hp.put("FaclimitOrigCurPml", beanObj.getFaclimitOrigCurPml());
			hp.put("FixedRate", beanObj.getFixedRate());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerNo", beanObj.getLayerNo());
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
			hp.put("MdPremium", beanObj.getM_dPremium());
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("MinPremium", beanObj.getMinPremium());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("MinimumRate", beanObj.getMinimumRate());
			hp.put("Month", beanObj.getMonth());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			hp.put("OrginalCurrency", beanObj.getOrginalCurrency());
			hp.put("OurAssessment", beanObj.getOurAssessment());
			hp.put("OurEstimate", beanObj.getOurEstimate());
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("PolBr", beanObj.getPolBr());
			hp.put("PortfoloCovered", beanObj.getPortfoloCovered());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", pid);
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("ReceiptofStatements", beanObj.getReceiptofStatements());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("RunoffYear", beanObj.getRunoffYear());
//			hp.put("SaveFlag", beanObj.getsaveFlag());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareWritt", beanObj.getShareWritt());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubPremium", beanObj.getSubPremium());
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
			hp.put("XlPremium", beanObj.getXlPremium());
			hp.put("XlcostOurShare", beanObj.getXlcostOurShare());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				savFlg = (Boolean) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}

	@SuppressWarnings("unchecked")
	public String PreviouRetroTypeChect(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.Retro.PreviouRetroTypeChect");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("BranchCode", bean.getBranchCode());
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
	public List<Map<String, Object>> riskDetailsEditMode(RiskDetailsBean beanObj, boolean contractMode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.Retro.riskDetailsEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractMode", contractMode);
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ReMode", beanObj.getReMode());
			
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
	public String getEndDate(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.Retro.getEndDate");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
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

	@SuppressWarnings("unchecked")
	public void insertRetroDetails(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.Retro.insertRetroDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("EndDate", bean.getEndDate());
			hp.put("StartDate", bean.getStartDate());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
