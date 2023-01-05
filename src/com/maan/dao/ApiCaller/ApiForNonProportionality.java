package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.RiskDetailsBean;

public class ApiForNonProportionality extends ApiConfig implements Callable<Object>{
	String authorization="";

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GetShareValidation(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.GetShareValidation");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
//No Link			
			link=getActualLink(link, hp);
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
	public List<Map<String, Object>> BaseLayerStatus(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.BaseLayerStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", beanObj.getProposal_no());
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
	public List<Map<String, Object>> getInclusionExList(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getInclusionExList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposal_no());
			hp.put("BranchCode", bean.getBranchCode());
	
			link=getActualLink(link, hp);
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
	public List<Map<String, Object>> insertIEModule(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertIEModule");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposalNo());
			hp.put("Contarctno", bean.getContarctno());
			hp.put("EffectiveDate", bean.getEffectiveDate());
			hp.put("ExcludeProposalNo", bean.getExcludeProposalNo());
			hp.put("ExcludedList", bean.getExcludedList());
			hp.put("IncludedList", bean.getIncludedList());
			hp.put("LayerNo", bean.getLayerNo());
			hp.put("LoginId", bean.getLoginId());
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("TransactionNo", bean.getTransactionNo());
	
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
	public List<Map<String, Object>> getLowClaimBonusList(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getLowClaimBonusList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Proposalno", bean.getProposalNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("AcqBonus", bean.getAcqBonus());
	
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
	public String getShortname(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			link=getActualLink(link,hp);
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> showSecondPageData1(String proposal, RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.showSecondPageData1");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("PercentRetro", beanObj.getPercentRetro());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", proposal);
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			hp.put("RetroFinalList", beanObj.getRetroFinalList());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RetroYear", beanObj.getRetroYear());
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
	public List<Map<String, Object>> GetRemarksDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.GetRemarksDetails");
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
	public String getSumOfCover(RiskDetailsBean bean, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getSumOfCover");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposal_no());
			hp.put("Branchcode", bean.getBranchCode());
			hp.put("ProductId", pid);
			hp.put("LayerNo", bean.getLayerNo());
			
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
	public List<Map<String, Object>> showLayerBrokerage(String layerProposalNo, RiskDetailsBean formobj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.showLayerBrokerage");
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
	public String getRetroContractDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getRetroContractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("UwYear", beanObj.getUwYear());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String checkAvialability(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.checkAvialability");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ProductId", pid);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean updateProportionalTreaty(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.updateProportionalTreatyDao");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("AdjRate", beanObj.getAdjRate());
			hp.put("AmendId", beanObj.getAmendId());
//			hp.put("AmendStatus", beanObj.getAmendStatus());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLoginId", beanObj.getBaseLoginID());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedReten", beanObj.getCedReten());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			
			if(!"5".equalsIgnoreCase(beanObj.getBusinessType())){
				if(beanObj.getCoverLimitOC()!=null) {
					for(int i=0;i<beanObj.getCoverLimitOC().size();i++){
						JSONObject abj = new JSONObject();
						abj.put("CoverLimitOC", beanObj.getCoverLimitOC().get(i));
						abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
						abj.put("DeductableLimitOC", beanObj.getDeductableLimitOC().get(i));
						abj.put("EgnpiAsPerOff", beanObj.getEgnpiAsPerOff().get(i));
						abj.put("GnpiAsPO", beanObj.getGnpiAsPO().get(i));
						array.add(abj);
					}
				}
				hp.put("CoverLimitOCReq", array);
				}else {
					if(beanObj.getCoverLimitAmount()!=null) {
						for(int i=0;i<beanObj.getCoverLimitAmount().size();i++){
							JSONObject abj = new JSONObject();
							abj.put("CoverLimitAmount", beanObj.getCoverLimitAmount().get(i));
							abj.put("CoverLimitPercent", beanObj.getCoverLimitPercent().get(i));
							abj.put("CoverdepartIdS", beanObj.getCoverdepartIdS()==null?"":beanObj.getCoverdepartIdS().get(i));
							abj.put("DeductableLimitAmount", beanObj.getDeductableLimitAmount().get(i));
							abj.put("DeductableLimitPercent", beanObj.getDeductableLimitPercent().get(i));
							abj.put("EgnpiAsPerOffSlide", beanObj.getEgnpiAsPerOffSlide().get(i));
							abj.put("GnpiAsPOSlide", beanObj.getGnpiAsPOSlide().get(i));
							array.add(abj);
						}
					}
					hp.put("CoverLimitAmountreq", array);
				}
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeduchunPercent", beanObj.getDeduc_hunPercent());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("Edit", beanObj.getEdit());
			hp.put("EgnpiOffer", beanObj.getEgnpiOffer());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("EndNo", beanObj.getEndorsmentno());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpDate", beanObj.getExpDate());
//			hp.put("FlagStatus", beanObj.getFlagStatus());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerMode", beanObj.getLayerMode());
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
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("PolicyBranch", beanObj.getPolBr());
			hp.put("PortfoloCovered", beanObj.getPortfoloCovered());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProcessId", beanObj.getProcessId());
			hp.put("ProductId", pid);
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("RenewalcontractNo", beanObj.getRenewal_contract_no());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareWritten", beanObj.getShareWritt());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubPremium", beanObj.getSubPremium());
			hp.put("SubProfitCenter", beanObj.getSubProfit_center());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("Territoryscope", beanObj.getTerritoryscope());
			hp.put("TreatyLimitsurplusOCPml", beanObj.getTreatyLimitsurplusOCPml());
			hp.put("TreatyLimitsurplusOCPmlOS", beanObj.getTreatyLimitsurplusOCPmlOS());
			hp.put("TreatyNameType", beanObj.getTreatyName_type());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("UmbrellaXL", beanObj.getUmbrellaXL());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("XlPremium", beanObj.getXlPremium());
			
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
	public boolean insertClassLimit(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertClassLimit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("ContNo", beanObj.getContNo());
			
			
			if(!"5".equalsIgnoreCase(beanObj.getBusinessType())){
			if(beanObj.getCoverLimitOC()!=null) {
				for(int i=0;i<beanObj.getCoverLimitOC().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("CoverLimitOC", beanObj.getCoverLimitOC().get(i));
					abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
					abj.put("DeductableLimitOC", beanObj.getDeductableLimitOC().get(i));
					abj.put("EgnpiAsPerOff", beanObj.getEgnpiAsPerOff().get(i));
					abj.put("GnpiAsPO", beanObj.getGnpiAsPO().get(i));
					array.add(abj);
				}
			}
			}else {
				if(beanObj.getCoverLimitAmount()!=null) {
					for(int i=0;i<beanObj.getCoverLimitAmount().size();i++){
						JSONObject abj = new JSONObject();
						abj.put("CoverLimitAmount", beanObj.getCoverLimitAmount().get(i));
						abj.put("CoverLimitPercent", beanObj.getCoverLimitPercent().get(i));
						abj.put("CoverdepartIdS", beanObj.getCoverdepartIdS()==null?"":beanObj.getCoverdepartIdS().get(i));
						abj.put("DeductableLimitAmount", beanObj.getDeductableLimitAmount().get(i));
						abj.put("DeductableLimitPercent", beanObj.getDeductableLimitPercent().get(i));
						abj.put("EgnpiAsPerOffSlide", beanObj.getEgnpiAsPerOffSlide().get(i));
						abj.put("GnpiAsPOSlide", beanObj.getGnpiAsPOSlide().get(i));
						array.add(abj);
					}
				}
			}
			hp.put("CoverList", array);
			hp.put("CoverLimitAmount", array);
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("Proposalno", beanObj.getProposal_no());
		
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
	public Map<String, Object> viewRiskDetails(RiskDetailsBean beanObj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.viewRiskDetails");
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
				json1 = (JSONObject) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> riskDetailsEditMode(RiskDetailsBean beanObj, boolean contractMode) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.riskDetailsEditMode");
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
	public Map<String, Object> showSecondPageData(String proposal, RiskDetailsBean formobj, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.showSecondPageData");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", formobj.getBranchCode());
			hp.put("IncepDate", formobj.getIncepDate());
			hp.put("NoInsurer", formobj.getNo_Insurer());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", proposal);
			
			if(formobj.getRetroFinalList()!=null) {
			for(int i=0;i<formobj.getRetroFinalList().size();i++){
			JSONObject abj = new JSONObject();
//			abj.put("CONTDET1", formobj.getCONTDET1().get(i));
//			abj.put("CONTDET2", formobj.getCONTDET2().get(i));
			abj.put("RetroDupContract", formobj.getRetroDupContract());
			abj.put("RetroDupYerar", formobj.getRetroDupYerar());
			array.add(abj);
			}
			}
			hp.put("RetroFinalList", array);
			
			hp.put("RetroType", formobj.getRetroType());
			hp.put("UwYear", formobj.getUwYear());
			
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
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean beanObj, int flag, String uWYear) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getRetroContractDetailsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Flag", flag);
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("UwYear", beanObj.getUwYear());
			
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
	public Map<String, Object> saveSecondMode(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.saveSecondPage");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("Accounts", beanObj.getAccounts());
			hp.put("AcqBonus", beanObj.getAcqBonus());
			hp.put("AcqBonusPercentage", beanObj.getAcqBonusPercentage());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("AnualAggregateDeduct", beanObj.getAnualAggregateDeduct());
			hp.put("AnualAggregateLiability", beanObj.getAnualAggregateLiability());
			hp.put("BonusPopUp", beanObj.getBonusPopUp());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("BurningCost", beanObj.getBurningCost());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("ExpiryDate", beanObj.getExpDate());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("IncepDate", beanObj.getIncepDate());
			
			if(beanObj.getInstalmentDateList()!=null) {
				for(int i=0;i<beanObj.getInstalmentDateList().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("InstallmentPremium", beanObj.getInstallmentPremium().get(i));
					abj.put("InstalmentDateList", beanObj.getInstalmentDateList().get(i));
					abj.put("PaymentDueDays", beanObj.getPaymentDueDays().get(i));
					array.add(abj);
				}
			}
			hp.put("Instalmentperiod", array);
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LeaderUnderwriter", beanObj.getLeader_Underwriter());
			hp.put("LeaderUnderwritercountry", beanObj.getLeader_Underwriter_country());
			hp.put("LeaderUnderwritershare", beanObj.getLeader_Underwriter_share());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			
			if(beanObj.getCedingCompany()!=null) {
				for(int i=0;i<beanObj.getCedingCompany().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("CedingCompany", beanObj.getCedingCompany().get(i));
					abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
					abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
					abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
					abj.put("ShareSigned", beanObj.getShareSigned().get(i));
					array.add(abj);
				}
			}
			hp.put("NoRetroCessReq", array);
			
			hp.put("OccurrentLimit", beanObj.getOcc_limit());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("PreviousendoDate", beanObj.getPreviousendoDate());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", productId);
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ReInstatementPremium", beanObj.getReInstatementPremium());
			hp.put("ReinsPopUp", beanObj.getReinsPopUp());
			hp.put("ReinstAdditionalPremium", beanObj.getReinstAdditionalPremium());
			hp.put("ReinstAditionalPremiumpercent", beanObj.getReinstAditionalPremium_percent());
			hp.put("ReinstNo", beanObj.getReinstNo());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RetentionPercentage", beanObj.getRetentionPercentage());
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			
			if(beanObj.getRetroList()!=null) {
				for(int i=0;i<beanObj.getRetroList().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("PercentRetro", beanObj.getPercentRetro().get(i));
					abj.put("RetroCeding", beanObj.getRetroCeding().get(i));
					abj.put("RetroYear", beanObj.getRetroYear().get(i));
					array.add(abj);
				}
			}
			
			hp.put("RetroList", array);
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("Tax", beanObj.getTax());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("acquisitionCost", beanObj.getAcquisition_Cost());
			
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
	public boolean firstInsert(RiskDetailsBean beanObj, String pid, boolean saveFlag,boolean amendId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg= false;
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.firstInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate", beanObj.getAccDate());
			hp.put("AdjRate", beanObj.getAdjRate());
			hp.put("AmendStatus", amendId);
			hp.put("AmendId", StringUtils.isBlank(beanObj.getAmendId())?"0":beanObj.getAmendId());
			hp.put("BaseLayer", beanObj.getBaseLayer());
			hp.put("BaseLoginID", beanObj.getBaseLoginID());
			hp.put("Basis", beanObj.getBasis());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("BurningCostLF", beanObj.getBurningCostLF());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("CedingCo", beanObj.getCedingCo());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("ContractGendration", beanObj.getContractGendration());
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			hp.put("CoverLimitXL", beanObj.getCoverLimitXL());
			hp.put("CoverLimitXLOurShare", beanObj.getCoverLimitXLOurShare());
			hp.put("DeduchunPercent", beanObj.getDeduc_hunPercent());
			hp.put("DeductLimitXL", beanObj.getDeductLimitXL());
			hp.put("DeductLimitXLOurShare", beanObj.getDeductLimitXLOurShare());
			hp.put("DepartId", beanObj.getDepartId());
			hp.put("EgnpiOffer", beanObj.getEgnpiOffer());
			hp.put("Egnpipml", beanObj.getEgnpipml());
			hp.put("EgnpipmlOurShare", beanObj.getEgnpipmlOurShare());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("Epi", beanObj.getEpi());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("EpiAsPerShare", beanObj.getEpiAsPerShare());
			hp.put("Epipml", beanObj.getEpipml());
			hp.put("EpipmlOS", beanObj.getEpipmlOS());
			hp.put("EventLimitOurShare", beanObj.getEventLimitOurShare());
			hp.put("Eventlimit", beanObj.getEvent_limit());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("ExchangeType", beanObj.getExchangeType());
			hp.put("ExpDate", beanObj.getExpDate());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("LOCIssued", beanObj.getLOCIssued());
			hp.put("LayerLayerNo", beanObj.getLayerLayerNo());
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
			hp.put("PaymentDuedays", beanObj.getPaymentDuedays());
			hp.put("PerilCovered", beanObj.getPerilCovered());
			hp.put("Pid", pid);
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlPercent", beanObj.getPmlPercent());
			hp.put("Pnoc", beanObj.getPnoc());
			hp.put("PolBr", beanObj.getPolBr());
			hp.put("PortfoloCovered", beanObj.getPortfoloCovered());
			hp.put("Premiumbasis", beanObj.getPremiumbasis());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProfitCenter", beanObj.getProfit_Center());
			hp.put("ProposalType", beanObj.getProposalType());
			hp.put("Proposalno", beanObj.getProposal_no());
			hp.put("RenewalEditMode", beanObj.getRenewalEditMode());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetentionYN", beanObj.getRetentionYN());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("RiskCovered", beanObj.getRiskCovered());
			hp.put("SaveFlag", saveFlag);
			hp.put("SharSign", beanObj.getSharSign());
			hp.put("ShareWritt", beanObj.getShareWritt());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubPremium", beanObj.getSubPremium());
			hp.put("SubProfitcenter", beanObj.getSubProfit_center());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("Territoryscope", beanObj.getTerritoryscope());
			hp.put("TreatyLimitsurplusOCPml", beanObj.getTreatyLimitsurplusOCPml());
			hp.put("TreatyLimitsurplusOCPmlOS", beanObj.getTreatyLimitsurplusOCPmlOS());
			hp.put("TreatyNametype", beanObj.getTreatyName_type());
			hp.put("TreatyType", beanObj.getTreatyType());
			hp.put("UmbrellaXL", beanObj.getUmbrellaXL());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("XlPremium", beanObj.getXlPremium());

			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					beanObj.setContractGendration(json.get("Result")==null?"":json.get("Result").toString());
					beanObj.setProposal_no(json.get("Proposalno")==null?"":json.get("Proposalno").toString());
					savFlg = true;
				}
		}} catch (Exception e) {
			e.printStackTrace();
		}
		return savFlg;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getValidation(RiskDetailsBean formObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getValidation");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("IncepDate", formObj.getIncepDate());
			hp.put("Renewalcontractno", formObj.getRenewal_contract_no());
			
			link=getActualLink(link, hp);
			
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
	public Map<String, Object> showRetroContracts(RiskDetailsBean beanObj, String productId, boolean view) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.showRetroContracts");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProductId", productId);
			hp.put("ProposalNo", beanObj.getProposal_no());
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
	public Map<String, Object> saveRiskDeatilsSecondForm(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.saveRiskDeatilsSecondForm");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccDate",beanObj.getAccDate());
			hp.put("Accounts", beanObj.getAccounts());
			
			hp.put("AcqBonus", beanObj.getAcqBonus());
			hp.put("AcqBonusPercentage", beanObj.getAcqBonusPercentage());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("AnualAggregateDeduct", beanObj.getAnualAggregateDeduct());
			hp.put("AnualAggregateLiability", beanObj.getAnualAggregateLiability());
			hp.put("BonusPopUp", beanObj.getBonusPopUp());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("BurningCost", beanObj.getBurningCost());
			hp.put("BusinessType", beanObj.getBusinessType());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("CrestaPopUp", beanObj.getCrestaPopUp());
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("EndorsementDate", beanObj.getEndorsementDate());
			hp.put("EndorsementStatus", beanObj.getEndorsementStatus());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("EpiAsPerOffer", beanObj.getEpiAsPerOffer());
			hp.put("ExchangeRate", beanObj.getExchRate());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("ExpiryDate", beanObj.getExpDate());
			hp.put("GmsApproval", beanObj.getGms_Approval());
			hp.put("IncepDate", beanObj.getIncepDate());
			
			if(beanObj.getInstalList()!=null) {
				for(int i=0;i<beanObj.getInstalList().size();i++){
					JSONObject abj = new JSONObject();
					abj.put("InstallmentPremium", beanObj.getInstallmentPremium().get(i));
					abj.put("InstalmentDateList", beanObj.getInstalmentDateList().get(i));
					abj.put("PaymentDueDays", beanObj.getPaymentDueDays().get(i));
					array.add(abj);
				}
			}
			
			hp.put("InstalList", array);
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LeaderUnderwriter", beanObj.getLeader_Underwriter());
			hp.put("LeaderUnderwritercountry", beanObj.getLeader_Underwriter_country());
			hp.put("LeaderUnderwritershare", beanObj.getLeader_Underwriter_share());
			hp.put("LimitOurShare", beanObj.getLimitOurShare());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MaxDate", beanObj.getMaxDate());
			
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("Mdpremiumourservice", beanObj.getMd_premium_our_service());
			hp.put("MinPremiumOurShare", beanObj.getMinPremiumOurShare());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoRetroCess", beanObj.getNoRetroCess());
			
			if(beanObj.getCeddingCompanylist()!=null) {
				for(int i=0;i<beanObj.getCeddingCompanylist().size();i++) {
					JSONObject abj = new JSONObject();
				abj.put("CedingCompany", beanObj.getCeddingCompanylist().get(i));
				abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
				abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
				abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
				abj.put("ShareSigned", beanObj.getShareSigned().get(i));
				array.add(abj);
				}
			}
			
			hp.put("CeddingCompanylist", array);
			
			hp.put("OccurrentLimit", beanObj.getOccurrent_Limit());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("PreviousendoDate", beanObj.getPreviousendoDate());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", productId);
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ReInstatementPremium", beanObj.getReInstatementPremium());
			hp.put("ReinsPopUp", beanObj.getReinsPopUp());
			hp.put("ReinstAdditionalPremium", beanObj.getReinstAdditionalPremium());
			hp.put("ReinstAditionalPremiumpercent", beanObj.getReinstAditionalPremium_percent());
			hp.put("ReinstNo", beanObj.getReinstNo());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("Renewalcontractno", beanObj.getRenewal_contract_no());
			hp.put("RetentionPercentage", beanObj.getRetentionPercentage());
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			
			if(beanObj.getRetroList()!=null) {
				for(int i=0;i<beanObj.getRetroList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("PercentRetro", beanObj.getPercentRetro().get(i));
					abj.put("RetroCeding", beanObj.getRetroCeding().get(i));
					abj.put("RetroYear", beanObj.getRetroYear().get(i));
					array.add(abj);
				}
			}
			
			hp.put("RetroList", array);
			
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("ShareProfitCommission", beanObj.getShare_Profit_Commission());
			hp.put("SourceId", beanObj.getSourceId());
			hp.put("Tax", beanObj.getTax());
			hp.put("UnderwriterRecommendations", beanObj.getUnderwriter_Recommendations());
			hp.put("UwYear", beanObj.getUwYear());
			hp.put("acquisitionCost", beanObj.getAcquisition_Cost());
			
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
	public boolean InsertRemarkDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.InsertRemarkDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("Productid", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposal_no());
			
			if(beanObj.getRemarkSNo()!=null) {
				for(int i=0;i<beanObj.getRemarkSNo().size();i++) {
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
	public List<Map<String, Object>> getReInstatementDetailsList(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getReInstatementDetailsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo ", bean.getProposal_no());
			hp.put("BranchCode ", bean.getBranchCode());
			
			link=getActualLink(link, hp);
			
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
	public void MoveReinstatementMain(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.MoveReinstatementMain");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			
			if(bean.getCoverdepartId()!=null) {
			for(int i=0;i<bean.getCoverdepartId().size();i++) {
				JSONObject abj = new JSONObject();
				abj.put("CoverLimitOC", bean.getCoverLimitOC().get(i));
				abj.put("CoverdepartId", bean.getCoverdepartId().get(i));	
				array.add(abj);
			}
		}
			hp.put("CoverdepartIdList", array);
			
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("LayerNo", bean.getLayerNo());
			hp.put("ProductId", bean.getProduct_id());
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("BranchCode", bean.getBranchCode());
			
			if(bean.getReinstatementNo()!=null) {
				for(int i=0;i<bean.getReinstatementNo().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("ReinstatementAmount", bean.getReinstatementAmount().get(i));
					abj.put("ReinstatementMinAmount", bean.getReinstatementMinAmount().get(i));
					abj.put("ReinstatementMinTime", bean.getReinstatementMinTime().get(i));
					abj.put("ReinstatementNo", bean.getReinstatementNo().get(i));
					abj.put("ReinstatementTypeId", bean.getReinstatementTypeId().get(i));
					array.add(abj);
				}
			}
			hp.put("ReinstatementNo", array);
			
			hp.put("ReinstatementOption", bean.getReinstatementOption());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteMainTable(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.deleteMainTable");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposal_no());
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode ", bean.getBranchCode());
			
			link=getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String LowClaimBonusInser(RiskDetailsBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		String result = "";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.LowClaimBonusInser");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcqBonus", bean.getAcqBonus());
			
			if(bean.getBonusFrom()!=null) {
				for(int i=0;i<bean.getBonusFrom().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("BonusFrom",bean.getBonusFrom().get(i));
					abj.put("BonusLowClaimBonus",bean.getBonusLowClaimBonus().get(i));
					abj.put("BonusSNo",bean.getBonusSNo().get(i));
					abj.put("BonusTo",bean.getBonusTo().get(i));
					array.add(abj);
				}
			}
			hp.put("BonusReq", array);
			hp.put("BonusTypeId", bean.getBonusTypeId());
			hp.put("BranchCode ", bean.getBranchCode());
			
			hp.put("Broker ", bean.getBroker());
			hp.put("CedingCo ", bean.getCedingCo());
			hp.put("ContractNo ", bean.getContractNo());
			hp.put("DepartmentId ", bean.getDepartId());
			hp.put("EndorsmentNo ", bean.getEndorsmentno());
			hp.put("LayerNo ", bean.getLayerNo());
			hp.put("LoginId ", bean.getLoginId());
			hp.put("Month ", bean.getMonth());
			hp.put("PolicyBranch ", bean.getPolBr());
			hp.put("ProductId ", bean.getProduct_id());
			hp.put("ProposalNo ", bean.getProposalNo());
			hp.put("TreatyNameType ", bean.getTreatyName_type());
			hp.put("UwYear ", bean.getUwYear());
			
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
	public Map<String, Object> showSecondpageEditItems(RiskDetailsBean beanObj, String pid, String proposalNo) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.showSecondpageEditItems");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getIncepDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("ProductId", pid);
			hp.put("ProposalNo", proposalNo);
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			
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
	public List<Map<String, Object>> getClassLimitDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getClassLimitDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("ProposalNo", beanObj.getProposal_no());
			
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
	public void insertRetroCess(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertRetroCess");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode ", beanObj.getBranchCode());
			hp.put("ContNo ", beanObj.getContNo());
			
			if(beanObj.getCedingCompany()!=null) {
				for(int i=0;i<beanObj.getCedingCompany().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CedingCompany", beanObj.getCedingCompany().get(i));
					abj.put("ProposalStatus", beanObj.getProposalStatus().get(i));
					abj.put("RetroBroker", beanObj.getRetroBroker().get(i));
					abj.put("ShareAccepted", beanObj.getShareAccepted().get(i));
					abj.put("ShareSigned", beanObj.getShareSigned().get(i));
					array.add(abj);
				}
			}
			hp.put("InsertRetroCessReq1", array);
			
			hp.put("LayerNo ", beanObj.getLayerNo());
			hp.put("LoginId ", beanObj.getLoginId());
			hp.put("NoRetroCess ", beanObj.getNoRetroCess());
			hp.put("ProposalNo ", beanObj.getProposalNo());			
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> instalMentPremium(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.instalMentPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("ExchangeRate", beanObj.getExchRate());
			
			if(beanObj.getInstalmentDateList()!=null) {
				for(int i=0;i<beanObj.getInstalmentDateList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("InstallmentPremium", beanObj.getInstallmentPremium().get(i));
					abj.put("InstalmentDateList", beanObj.getInstalmentDateList().get(i));
					abj.put("PaymentDueDays", beanObj.getPaymentDueDays().get(i));
					array.add(abj);
				}
			}
			hp.put("Instalmentperiod", array);
			
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("MdInstalmentNumber", beanObj.getM_d_InstalmentNumber());
			hp.put("ProposalNo", beanObj.getProposal_no());
			
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
	public void insertRetroContracts(RiskDetailsBean beanObj, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertRetroContracts");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("RetentionPercentage", beanObj.getRetentionPercentage());
			
			if(beanObj.getPercentRetro()!=null) {
				for(int i=0;i<beanObj.getPercentRetro().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("PercentRetro", beanObj.getPercentRetro().get(i));
					abj.put("RetroCeding", beanObj.getRetroCeding().get(i));
					abj.put("RetroYear", beanObj.getRetroYear().get(i));
					array.add(abj);
				}
			}
			hp.put("RetroDetail", array);
			
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			hp.put("RetroDupYerar", beanObj.getRetroDupYerar());
			hp.put("RetroType", beanObj.getRetroType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void reInstatementMainInsert(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.reInstatementMainInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContNo());
			
			if("Y".equalsIgnoreCase(beanObj.getReInstatementPremium())){
			if(beanObj.getCoverLimitOC()!=null) {
				for(int i=0;i<beanObj.getCoverLimitOC().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CoverLimitOC", beanObj.getCoverLimitOC().get(i));
					abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
					array.add(abj);
				}
			}
		}
			hp.put("CoverdepartIdList", array);
			
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposal_no());
			hp.put("ReInstatementPremium", beanObj.getReInstatementPremium());
			
			if(beanObj.getReinstatementNo()!=null) {
				for(int i=0;i<beanObj.getReinstatementNo().size();i++) {
					JSONObject abj = new JSONObject();
				abj.put("ReinstatementNo", beanObj.getReinstatementNo().get(0));
				array.add(abj);
				}
			}
			
			hp.put("ReinstatementNo", array);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
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
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertCrestaMaintable");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", bean.getAmendId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContNo());
			hp.put("DepartmentId", bean.getDepartId());
			hp.put("ProposalNo", bean.getProposalNo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void insertBonusDetails(RiskDetailsBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.nonprop.insertBonusDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcqBonus", beanObj.getAcqBonus());
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("DepartmentId", beanObj.getDepartId());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("ProductId", beanObj.getProduct_id());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
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
