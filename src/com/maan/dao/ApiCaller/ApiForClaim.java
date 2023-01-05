package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.ClaimBean;

public class ApiForClaim extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractDetails(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.contranct.info");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProductId", bean.getProductId());
			hp.put("ProposalNo", bean.getProposal_No());
			
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
	public List<Map<String,Object>> getClaimList(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.list");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("LayerNo", StringUtils.isBlank(bean.getLayerNo())?"0":bean.getLayerNo());
			hp.put("PolicyContractNo", bean.getPolicy_Contract_No());
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
	public String getShortName(String BranchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject json1 = null;
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.Short.list");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("branchCode", BranchCode);
			link=getActualLink(link,hp);
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Result");
				//result=json1.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void insertCliamDetails(ClaimBean beanObj) {
			JSONObject hp = new JSONObject();
			JSONObject json = null;
			JSONParser parser = new JSONParser();
			JSONArray array=new JSONArray();
			String result="";
			
			try {
				String link=getValueFromWebservice("maan.client.claim.Details");
				authorization= getValueFromWebservice("marine.insurance.auth");
				
				hp.put("AcceptenceDate", beanObj.getAcceptenceDate());
				hp.put("AccumulationCode", beanObj.getAccumulation_Code());
				hp.put("AdviceManagementEmail", beanObj.getAdvice_management_email());
				hp.put("AdviceMangement", beanObj.getAdvice_Mangement());
				hp.put("AdviceUW", beanObj.getAdvice_UW());
				hp.put("AdviceUwEmail", beanObj.getAdvice_uw_email());
				hp.put("Basis", beanObj.getBasis());
				hp.put("BranchCode", beanObj.getBranchCode());
				hp.put("CauseofLoss", beanObj.getCause_of_Loss());
				hp.put("CedentClaimNo", beanObj.getCedentClaimNo());
				hp.put("ClaimNo", beanObj.getClaim_No());
				hp.put("ClaimdepartId", beanObj.getClaimdepartId());
				hp.put("CreatedBy", beanObj.getCreated_by());
				hp.put("CreatedDate", beanObj.getCreated_Date());
				hp.put("Currecny", beanObj.getCurrecny());
				hp.put("DateofLoss", beanObj.getDate_of_Loss());
				hp.put("DepartmentClass", beanObj.getDepartmentClass());
				hp.put("DepartmentId", beanObj.getDepartmentId());
				hp.put("EventCode", beanObj.getEvent_Code());
				hp.put("ExcRate", beanObj.getExc_Rate());
				hp.put("From", beanObj.getFrom());
				hp.put("GrossLossFGU", beanObj.getGrossLossFGU());
				hp.put("IbnrOurShareOc", beanObj.getIbnrOurShareOc());
				hp.put("IbnrPerOc", beanObj.getIbnrPerOc());
				hp.put("InsuredName", beanObj.getInsuredName());
				hp.put("LayerNo", beanObj.getLayerNo());
				hp.put("Location", beanObj.getLocation());
				hp.put("LoginId", beanObj.getLoginId());
				hp.put("LossDetails", beanObj.getLoss_Details());
				hp.put("LossEstimateOrigCurr", beanObj.getLoss_Estimate_Orig_Curr());
				hp.put("LossEstimateOurShareOrigCurr", beanObj.getLoss_Estimate_Our_share_Orig_Curr());
				hp.put("OpendDate", beanObj.getOpendDate());
				hp.put("OpstartDate", beanObj.getOpstartDate());
				hp.put("OsAmt", beanObj.getOsAmt());
				hp.put("OtherProfessionalPerOc", beanObj.getOtherProfessionalPerOc());
				hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
				hp.put("PreReOpenDate", beanObj.getPreReopenDate());
				hp.put("ProductId", beanObj.getProductId());
				hp.put("ProfessionalOurShareOc", beanObj.getProfessionalOurShareOc());
				hp.put("ProposalNo", beanObj.getProposal_No());
				hp.put("ReOpenDate", beanObj.getPreReopenDate());
				hp.put("RecordFees", beanObj.getRecordFees());
				hp.put("RecordIbnr", beanObj.getRecordIbnr());
				hp.put("RecoveryFrom", beanObj.getRecovery_from());
				hp.put("Remarks", beanObj.getRemarks());
				hp.put("ReportDate", beanObj.getReport_Date());
				hp.put("ReputedDate", beanObj.getReputed_Date());
				hp.put("ReservePositionDate", beanObj.getReservePositionDate());
				hp.put("RiRecovery", beanObj.getRi_Recovery());
				hp.put("RiskCode", beanObj.getRisk_Code());
				hp.put("SignedShare", beanObj.getSigned_Share());
				hp.put("StatusofClaim", beanObj.getStatus_of_claim());
				hp.put("SubProfitId", beanObj.getSubProfitId());
				hp.put("SumInsOSDC", beanObj.getSumInsOSDC());
				hp.put("SurveyorAdjesterOurShareOC", beanObj.getSurveyorAdjesterOurShareOC());
				hp.put("SurveyorAdjesterPerOC", beanObj.getSurveyorAdjesterPerOC());
				hp.put("To", beanObj.getTo());
				hp.put("TotalReserveOSOC", beanObj.getTotalReserveOSOC());
				
				String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
				if(responseStr!=null && responseStr.length()>0) {
					json = (JSONObject) parser.parse(responseStr);
					result =  (String) json.get("Success");
					beanObj.setClaim_No((String) json.get("Result"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClaimListmodetwo(String branchCode, String claimNo, String contractNo) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.modetwo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ClaimNo", claimNo);
			hp.put("ContractNo", contractNo);
			
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
	public List<Map<String, Object>> getClaimListmodefour(String branchCode, String claim_No, String policy_Contract_No,
			String claimPaymentNo) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.modefour");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ClaimNo", claim_No);
			hp.put("ContractNo", policy_Contract_No);
			hp.put("ClaimPaymentNo", claimPaymentNo);
			
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
	public List<Map<String, Object>> getContractDetailsmodefour(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.contranct.modefour");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
			
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
	public List<Map<String, Object>> getClaimListmodethree(ClaimBean beanObj) {
	JSONObject hp = new JSONObject();
	JSONObject json = null;
	JSONParser parser = new JSONParser();
	JSONArray array=new JSONArray();
	
	try {
		String link=getValueFromWebservice("maan.client.claim.Cliam.modethree");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
		hp.put("BranchCode", beanObj.getBranchCode());
		hp.put("ClaimNo", beanObj.getClaim_No());
		hp.put("ContractNo", beanObj.getPolicy_Contract_No());
		
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
	public List<Map<String, Object>> getcontractDetailsModeTen(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Cliam.modeTen");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
			
			
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
	public List<Map<String, Object>> getContractDetailsmodefive(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Cliam.modefive");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
			
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
	public List<Map<String, Object>> allocationList(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claimview.allocationList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContractNo());
			hp.put("TransactionNumber", bean.getTransactionNumber());
			
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
	public List<Map<String, Object>> allocList(ClaimBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claimview.allocList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContractNo());
			hp.put("TransactionNo", bean.getTransactionNumber());
			
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
	public List<Map<String, Object>> getClaimListmodeseven(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONObject json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claimList.modeseven");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("ContractNo", beanObj.getContractNo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				array=(JSONArray) json1.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public void insertCliamDetailsmode3(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Details.modethree");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AdviceTreasury", beanObj.getAdvice_Treasury());
			hp.put("AdviceTreasuryEmail", beanObj.getAdviceTreasuryEmail());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("BusinessMode", beanObj.getBusinessMode());
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("ClaimNoteRecommendations", beanObj.getClaim_Note_recommendations());
			hp.put("ClaimPaymentNo", beanObj.getClaimPaymentNo());
			hp.put("Currecny", beanObj.getCurrecny());
			hp.put("Date", beanObj.getDate());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("ExcRate", beanObj.getExc_Rate());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossEstimateRevisedOrigCurr", beanObj.getLoss_Estimate_Revised_Orig_Curr());
			hp.put("Otherproffeeos", beanObj.getOther_prof_fee_os());
			hp.put("PaidAmountOrigcurr", beanObj.getPaid_Amount_Orig_curr());
			hp.put("PaidClaimOs", beanObj.getPaid_claim_os());
			hp.put("PaymentFlag", beanObj.getPaymentFlag());
			hp.put("PaymentReference", beanObj.getPayment_Reference());
			hp.put("PaymentRequestNo", beanObj.getPayment_Request_No());
			hp.put("PaymentType", beanObj.getPaymentType());
			hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ReinstPremiumOCOS", beanObj.getReinstPremiumOCOS());
			hp.put("ReinstType", beanObj.getReinstType());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RiRecovery", beanObj.getRi_Recovery());
			hp.put("Surveyorfeeos", beanObj.getSurveyor_fee_os());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void insertCliamDetailsmode12(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Details.Mode12");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Date", beanObj.getDate());
			hp.put("ExcRate", beanObj.getExc_Rate());
			hp.put("UpdateReference", beanObj.getUpdate_Reference());
			hp.put("CliamupdateDate", beanObj.getCliam_update_Date());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("UpdatesurveyorAdjesterPerOC", beanObj.getUpdatesurveyorAdjesterPerOC());
			hp.put("UpdateotherProfessionalPerOc", beanObj.getUpdateotherProfessionalPerOc());
			hp.put("UpdateibnrPerOc", beanObj.getUpdateibnrPerOc());
			hp.put("UpdatesurveyorAdjesterOurShareOC", beanObj.getUpdatesurveyorAdjesterOurShareOC());
			hp.put("UpdateprofessionalOurShareOc", beanObj.getUpdateprofessionalOurShareOc());
			hp.put("UpdateibnrOurShareOc", beanObj.getUpdateibnrOurShareOc());
			hp.put("LossEstimateOrigCurr", beanObj.getLoss_Estimate_Orig_Curr());
			hp.put("UpdaterecordFees", beanObj.getUpdaterecordFees());
			hp.put("UpdaterecordIbnr", beanObj.getUpdaterecordIbnr());
			hp.put("TotalReserveOSOC", beanObj.getTotalReserveOSOC());
			hp.put("ReservePositionDate", beanObj.getReservePositionDate());
			hp.put("CloseClaimYN", beanObj.getCloseClaimYN());
			hp.put("UpdateRivisedpercentage", beanObj.getUpdate_Rivised_percentage());
			hp.put("DateofLoss", beanObj.getDate_of_Loss());
			hp.put("SignedShare", beanObj.getSigned_Share());
			hp.put("BusinessMode", beanObj.getBusinessMode	());
			hp.put("PaymentType", beanObj.getPaymentType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void insertCliamDetailsmode8(ClaimBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Details.Mode8");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("UpdateReference", beanObj.getUpdate_Reference());
			hp.put("UpdaterecordFees", beanObj.getUpdaterecordFees());
			hp.put("UpdatesurveyorAdjesterOurShareOC", beanObj.getUpdatesurveyorAdjesterOurShareOC());
			hp.put("UpdatesurveyorAdjesterPerOC", beanObj.getUpdatesurveyorAdjesterPerOC());
			hp.put("UpdateotherProfessionalPerOc", beanObj.getUpdateotherProfessionalPerOc());
			hp.put("UpdateprofessionalOurShareOc", beanObj.getUpdateprofessionalOurShareOc());
			hp.put("UpdateibnrOurShareOc", beanObj.getUpdateibnrOurShareOc());
			hp.put("UpdateibnrPerOc", beanObj.getUpdateibnrPerOc());
			hp.put("UpdaterecordIbnr", beanObj.getUpdaterecordIbnr());
			hp.put("ReservePositionDate", beanObj.getReservePositionDate());
			hp.put("ClaimclosedDate", beanObj.getClaim_closed_Date());
			hp.put("CliamupdateDate", beanObj.getCliam_update_Date());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("TotalReserveOSOC", beanObj.getTotalReserveOSOC());
			hp.put("LossEstimateOrigCurr", beanObj.getLoss_Estimate_Orig_Curr());
			hp.put("UpdateRivisedoriginalCur", beanObj.getUpdate_Rivised_original_Cur());
			hp.put("ReverseClaimYN", beanObj.getReverseClaimYN());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ClaimNo", beanObj.getClaim_No());
			hp.put("ExcRate", beanObj.getExc_Rate());
			hp.put("LayerNo", StringUtils.isBlank(beanObj.getLayerNo())?"0":beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("PolicyContractNo", beanObj.getPolicy_Contract_No());
			hp.put("RiRecovery", beanObj.getRi_Recovery());
			hp.put("CloseClaimYN", beanObj.getCloseClaimYN());
			hp.put("UpdateRivisedpercentage", beanObj.getUpdate_Rivised_percentage());
			hp.put("DateofLoss", beanObj.getDate_of_Loss());
			hp.put("SignedShare", beanObj.getSigned_Share());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result =  (String) json.get("Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
		
		
