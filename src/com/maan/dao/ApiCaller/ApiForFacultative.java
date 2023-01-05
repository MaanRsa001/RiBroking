package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculitivesBean;

public class ApiForFacultative extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getShortname(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.facultative.getShortname");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				result =  (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> firstPageInsert(FaculitivesBean beanObj, boolean flag, boolean contract) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.facultative.firstPageInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AccountDate", beanObj.getAccountDate());
			hp.put("BaseLoginID", beanObj.getBaseLoginID());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Broker", beanObj.getBroker());
			hp.put("CedRetenType", beanObj.getCedRetenType());
			hp.put("CedantsRet", beanObj.getCedantsRet());
			hp.put("CedingCompany", beanObj.getCedingCompany());
			hp.put("City", beanObj.getCity());
			hp.put("Contract", contract);
			hp.put("ContractListVal", beanObj.getContractListVal());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("CountryExcludedList", beanObj.getCountryExcludedList());
			hp.put("CountryID", beanObj.getCountryID());
			hp.put("CountryIncludedList", beanObj.getCountryIncludedList());
			
			if(beanObj.getCoverLimitOC()!=null) {
				for(int i=0;i<beanObj.getCoverLimitOC().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CoverLimitOC", beanObj.getCoverLimitOC().get(i));
					abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
					abj.put("DeductableLimitOC", beanObj.getDeductableLimitOC().get(i));
					abj.put("EgnpiAsPerOff", beanObj.getEgnpiAsPerOff().get(i));
//					abj.put("GnpiAsPO", beanObj.getGnpiAsPO());	
					array.add(abj);
				}
			}
			
			hp.put("CoverLimitOC", array);
			
			hp.put("Deductible", beanObj.getDeductible());
			hp.put("DeductibleFacXol", beanObj.getDeductibleFacXol());
			hp.put("DeductibleFacXolPml", beanObj.getDeductibleFacXolPml());
			hp.put("DeductibleFacXolPmlOurShare", beanObj.getDeductibleFacXolPmlOurShare());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Edit", beanObj.getEdit());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("Endorsmenttype", beanObj.getEndorsmenttype());
			hp.put("ExpiryDate", beanObj.getExpiryDate());
			hp.put("FacultativeDepartment", beanObj.getFacultativeDepartment());
			hp.put("Flag", flag);
			hp.put("Gwpi", beanObj.getGwpi());
			hp.put("GwpiOurShare", beanObj.getGwpiOurShare());
			hp.put("GwpiPml", beanObj.getGwpiPml());
			hp.put("GwpiPmlOurShare", beanObj.getGwpiPmlOurShare());
			hp.put("InceptionDate", beanObj.getInceptionDate());
			hp.put("InsuredName", beanObj.getInsuredName());
			hp.put("Interest", beanObj.getInterest());
			hp.put("InwardType", beanObj.getInwardType());
			hp.put("Latitude", beanObj.getLatitude());
			hp.put("LimitPerLocationOC", beanObj.getLimitPerLocationOC());
			hp.put("LimitPerVesselOC", beanObj.getLimitPerVesselOC());
			hp.put("LocBankName", beanObj.getLocBankName());
			hp.put("LocBeneficerName", beanObj.getLocBeneficerName());
			hp.put("LocCreditAmt", beanObj.getLocCreditAmt());
			hp.put("LocCreditPrd", beanObj.getLocCreditPrd());
			hp.put("LocIssued", beanObj.getLocIssued());
			hp.put("Location", beanObj.getLocation());
			hp.put("Loginid", beanObj.getLoginid());
			hp.put("Longitude", beanObj.getLongitude());
			hp.put("Maxiumlimit", beanObj.getMaxiumlimit());
			hp.put("ModeOfTransport", beanObj.getModeOfTransport());
			hp.put("Month", beanObj.getMonth());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoOfInst", beanObj.getNoOfInst());
			hp.put("Nr", beanObj.getNr());
			hp.put("OriginalCurrency", beanObj.getOriginalCurrency());
			hp.put("PblOC", beanObj.getPblOC());
			hp.put("PblOurShare", beanObj.getPblOurShare());
			hp.put("PllOC", beanObj.getPllOC());
			hp.put("PllOurShare", beanObj.getPllOurShare());
			hp.put("Pml", beanObj.getPml());
			hp.put("PmlOCOurShare", beanObj.getPmlOCOurShare());
			hp.put("PmlOurShare", beanObj.getPmlOurShare());
			hp.put("Pmll", beanObj.getPmll());
			hp.put("PolicyBranch", beanObj.getPolicyBranch());
			hp.put("Premiumrate", beanObj.getPremiumrate());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProcessId", beanObj.getProcessId());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProfitCenterCode", beanObj.getProfitCenterCode());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("PslOC", beanObj.getPslOC());
			hp.put("PslOurShare", beanObj.getPslOurShare());
			hp.put("ReceiptofPayment", beanObj.getReceiptofPayment());
			hp.put("RenewalContractno", beanObj.getRenewal_Contract_no());
			hp.put("RenewalStatus", beanObj.getRenewalStatus());
			hp.put("ShSd", beanObj.getShSd());
			hp.put("ShWt", beanObj.getShWt());
			hp.put("Sipml", beanObj.getSipml());
//			hp.put("SourceId", beanObj.getsourceId());
			hp.put("SpRetro", beanObj.getSpRetro());
			hp.put("SubProfitCenter", beanObj.getSubProfitCenter());
			hp.put("SumInsured", beanObj.getSumInsured());
			hp.put("SumInsuredOurShare", beanObj.getSumInsuredOurShare());
			hp.put("SumInsuredPml", beanObj.getSumInsuredPml());
			hp.put("SumInsuredPmlOurShare", beanObj.getSumInsuredPmlOurShare());
			hp.put("Territory", beanObj.getTerritory());
			hp.put("TotalCoverage", beanObj.getTotalCoverage());
			hp.put("TotalDeductible", beanObj.getTotalDeductible());
			hp.put("TotalGWPI", beanObj.getTotalGWPI());
			hp.put("Tpl", beanObj.getTpl());
			hp.put("TplOurShare", beanObj.getTplOurShare());
			hp.put("Type", beanObj.getType());
			hp.put("Underwriter", beanObj.getUnderwriter());
			hp.put("UsCurrencyRate", beanObj.getUsCurrencyRate());
			hp.put("Vessaletonnage", beanObj.getVessaletonnage());
			hp.put("VesselAge", beanObj.getVesselAge());
			hp.put("VesselName", beanObj.getVesselName());
			hp.put("XolOC", beanObj.getXolOC());
			hp.put("XolOSOC", beanObj.getXolOSOC());
			hp.put("XollayerNo", beanObj.getXollayerNo());
			hp.put("XoltotalGWPI", beanObj.getXoltotalGWPI());
			hp.put("Year", beanObj.getYear());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 =  (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public void InsertRemarkDetails(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.facultative.InsertRemarkDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("DepartmentId", beanObj.getDepartmentId());
//			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginid());
			hp.put("Productid", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
			if(beanObj.getDescription()!=null) {
				for(int i=0;i<beanObj.getDescription().size();i++) {
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
	public void UpadateUWShare(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.UpadateUWShare");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Shsd", beanObj.getShSd());
			hp.put("ProposalNo", beanObj.getProposalNo());
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void InsertCoverDeductableDetails(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.InsertCoverDeductableDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			
			if(beanObj.getCoverSNo()!=null) {
				JSONObject abj = new JSONObject();
				for(int i=0;i<beanObj.getCoverSNo().size();i++) {
					abj.put("CoverLimitOC", beanObj.getCoverLimitOC().get(i));
					abj.put("CoverRemark", beanObj.getCoverRemark().get(i));
					abj.put("CoverTypeId", beanObj.getCoverTypeId().get(i));
					abj.put("CoverageDays", beanObj.getCoverageDays().get(i));
					abj.put("CoverdepartId", beanObj.getCoverdepartId().get(i));
					abj.put("CoversubdepartId", beanObj.getCoversubdepartId().get(i));
					abj.put("DeductableDays", beanObj.getDeductableDays().get(i));
					abj.put("DeductableLimitOC", beanObj.getDeductableLimitOC().get(i));
					abj.put("EgnpiAsPerOff", beanObj.getEgnpiAsPerOff().get(i));
					abj.put("PmlHundredPer", beanObj.getPmlHundredPer().get(i));
					abj.put("PmlPerList", beanObj.getPmlPerList().get(i));
					abj.put("PremiumRateList", beanObj.getPremiumRateList().get(i));
					array.add(abj);
				}
			}
			
			hp.put("CoverSNoReq", array);
			
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Loginid", beanObj.getLoginid());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("Type", beanObj.getType());
			
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
	public void InsertXolCoverDeductableDetails(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.InsertXolCoverDeductableDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Loginid", beanObj.getLoginid());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("Type", beanObj.getType());
			
			if(beanObj.getXolcoverLimitOC()!=null) {
				JSONObject abj = new JSONObject();
				for(int i=0;i<beanObj.getXolcoverLimitOC().size();i++) {
					abj.put("XolcoverLimitOC", beanObj.getXolcoverLimitOC().get(i));
					abj.put("XolcoverdepartId", beanObj.getXolcoverdepartId().get(i));
					abj.put("XolcoversubdepartId", beanObj.getXolcoversubdepartId().get(i));
					abj.put("XoldeductableLimitOC", beanObj.getXoldeductableLimitOC().get(i));
					abj.put("XolgwpiOC", beanObj.getXolgwpiOC().get(i));
					abj.put("XolpremiumRateList", beanObj.getXolpremiumRateList().get(i));
					array.add(abj);
				}
			}
			
			hp.put("XolcoverSNoReq", array);
			
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
	public List<Map<String, Object>> getLowClaimBonusList(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.getLowClaimBonusList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("acqBonus", bean.getAcqBonus());
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
	public Map<String, Object> showSecondPagedata(FaculitivesBean formObj, FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.showSecondPagedata");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getInceptionDate());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("NoOfInst", beanObj.getNoOfInst());
			
			if(beanObj.getPaymentDueDays()!=null) {
				for(int i=0;i<beanObj.getPaymentDueDays().size();i++) {
					array.add(beanObj.getPaymentDueDays().get(i));
				}
			}
			
			hp.put("PaymentDueDays", array);
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			
			JSONArray arrReceip = new JSONArray();
			if(beanObj.getReceiptofPayment()!=null) {
				arrReceip.add(beanObj.getReceiptofPayment());
			}
			
			hp.put("ReceiptofPayment", arrReceip);
			hp.put("RetroType", beanObj.getRetroType());
			
			JSONArray arrRetro = new JSONArray();
			if(beanObj.getRetrolList()!=null) {
				for(int i=0;i<beanObj.getRetrolList().size();i++) {
					arrRetro.add(beanObj.getRetrolList().get(i));
				}
			}
			
			hp.put("RetrolList", arrRetro);
			hp.put("Year", beanObj.getYear());
			
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
	public List<Map<String, Object>> getRetroContractDetailsList(FaculitivesBean beanObj, int flag) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.getRetroContractDetailsList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Flag", flag);
			hp.put("IncepDate", beanObj.getInceptionDate());
			hp.put("Productid", beanObj.getProductId());
			hp.put("RetroType", beanObj.getRetroType());
			hp.put("UwYear", beanObj.getUwYearList());
			hp.put("IncepDate", beanObj.getInceptionDate());
			
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
	public Map<String, Object> secondPageShowItems(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.secondPageShowItems");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", formObj.getBranchCode());
			hp.put("ProposalNo", formObj.getProposalNo());
			hp.put("ContractNo", formObj.getContractNo());
			hp.put("NoOfInst", formObj.getNoOfInst());
			hp.put("ReceiptofPayment", formObj.getReceiptofPayment());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				json1 = (JSONObject) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GetRemarksDetails(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.GetRemarksDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", formObj.getProposalNo());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getInsurarerDetails(FaculitivesBean formObj, boolean view) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.getInsurarerDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", formObj.getAmendId());
			hp.put("BranchCode", formObj.getBranchCode());
			hp.put("InceptionDate", formObj.getInceptionDate());
			hp.put("NoInsurer", formObj.getNo_Insurer());
			hp.put("ProposalNo", formObj.getProposalNo());
			hp.put("View", view);
			hp.put("Year", formObj.getYear());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLossDEtails(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.getLossDEtails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposalNo());
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
	public void MoveBonus(FaculitivesBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.MoveBonus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcqBonus", bean.getAcqBonus());
			
			if(bean.getBonusList()!=null) {
				for(int i=0;i<bean.getBonusList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("BonusFrom", bean.getBonusFrom().get(i));
					abj.put("BonusLowClaimBonus", bean.getBonusLowClaimBonus().get(i));
					abj.put("BonusSNo", bean.getBonusSNo().get(i));
					abj.put("BonusTo", bean.getBonusTo().get(i));
					array.add(abj);
				}
			}
			
			hp.put("BonusReq", array);
			
			hp.put("BonusTypeId", bean.getBonusTypeId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContractNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("Endorsmentno", bean.getEndorsmentno());
			hp.put("Loginid", bean.getLoginid());
			hp.put("ProductId", bean.getProductId());
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
	public void deleteMaintable(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.deleteMaintable");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcqBonus", bean.getAcqBonus());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("Endorsmentno", bean.getEndorsmentno());
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
	public Map<String, Object> secondPageInsert(FaculitivesBean beanObj, boolean flag) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.secondPageInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcqBonus", beanObj.getAcqBonus());
			hp.put("AcqBonusPercentage", beanObj.getAcqBonusPercentage());
			hp.put("AcqCost", beanObj.getAcqCost());
			hp.put("AlopYN", beanObj.getAlopYN());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("CategoryZone", beanObj.getCategoryZone());
			hp.put("Commn", beanObj.getCommn());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("Cu", beanObj.getCu());
			hp.put("CuRsn", beanObj.getCuRsn());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("DgmsApproval", beanObj.getDgmsApproval());
			hp.put("EqThreat", beanObj.getEqThreat());
			hp.put("EqwsInd", beanObj.getEqwsInd());
			hp.put("FireProt", beanObj.getFireProt());
			hp.put("Flag", flag);
			hp.put("GwpiOurShare", beanObj.getGwpiOurShare());
			hp.put("LossRecord", beanObj.getLossRecord());
			hp.put("Mbind", beanObj.getMbind());
			hp.put("MlopYN", beanObj.getMlopYN());
			hp.put("OccCode", beanObj.getOccCode());
			hp.put("OthAccep", beanObj.getOthAccep());
			hp.put("Othercost", beanObj.getOthercost());
			hp.put("PmlOurShare", beanObj.getPmlOurShare());
			hp.put("ProStatus", beanObj.getProStatus());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("ReftoHO", beanObj.getReftoHO());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RenewalContractno", beanObj.getRenewal_Contract_no());
			hp.put("RenewalFlag", beanObj.getRenewalFlag());
			hp.put("RiskDetail", beanObj.getRiskDetail());
			hp.put("RiskGrade", beanObj.getRiskGrade());
			hp.put("Scope", beanObj.getScope());
			hp.put("ShSd", beanObj.getShSd());
			hp.put("Tax", beanObj.getTax());
			hp.put("TplOurShare", beanObj.getTplOurShare());
			hp.put("UnderwriterCode", beanObj.getUnderwriterCode());
			hp.put("UwRecommendation", beanObj.getUwRecommendation());
			hp.put("WsThreat", beanObj.getWsThreat());
			hp.put("Year", beanObj.getYear());
			
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
	public void updateSecondPage(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.updateSecondPage");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CrestaStatus", beanObj.getCrestaStatus());
			hp.put("DocStatus", beanObj.getDocStatus());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
			hp.put("Exclusion", beanObj.getExclusion());
			hp.put("LeaderUnderwriter", beanObj.getLeader_Underwriter());
			hp.put("LeaderUnderwritercountry", beanObj.getLeader_Underwriter_country());
			hp.put("LeaderUnderwritershare", beanObj.getLeader_Underwriter_share());
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
	public void inserLossRecord(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.facultative.inserLossRecord");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			
			if(beanObj.getLossCauseOfLoss()!=null) {
				for(int i=0;i<beanObj.getLossCauseOfLoss().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("LossCauseOfLoss", beanObj.getLossCauseOfLoss().get(i));
					abj.put("LossDateOfLoss", beanObj.getLossDateOfLoss().get(i));
					abj.put("LossExpiryDate", beanObj.getLossExpiryDate().get(i));
					abj.put("LossITIReShare", beanObj.getLossITIReShare().get(i));
					abj.put("LossInceptionDate", beanObj.getLossInceptionDate().get(i));
					abj.put("LossInsuredClaim", beanObj.getLossInsuredClaim().get(i));
					abj.put("LossLeader", beanObj.getLossLeader().get(i));
					abj.put("LossNo", beanObj.getLossNo().get(i));
					abj.put("LossPremium", beanObj.getLossPremium().get(i));
					abj.put("LossRatio", beanObj.getLossRatio().get(i));
					abj.put("LossYear", beanObj.getLossYear().get(i));
					abj.put("LossinsuredName", beanObj.getLossinsuredName().get(i));
					array.add(abj);
				}
			}
			
			hp.put("LossDetails", array);
			
			hp.put("ProductId", beanObj.getProductId());
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
	public boolean instalMentPremium(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.facultative.instalMentPremium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("EndorsementNo", beanObj.getEndorsmentno());
			hp.put("UsCurrencyRate", beanObj.getUsCurrencyRate());
			
			if(beanObj.getInstalmentDateList()!=null) {
				for(int i=0;i<beanObj.getInstalmentDateList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("InstalmentDateList", beanObj.getInstalmentDateList().get(i));
					abj.put("InstallmentPremium", beanObj.getInstallmentPremium().get(i));
					abj.put("PaymentDueDays", beanObj.getPaymentDueDays().get(i));
					array.add(abj);
				}
			}
			
			hp.put("InstalmentDetails", array);
			
			hp.put("Loginid", beanObj.getLoginid());
			hp.put("NoOfInst", beanObj.getNoOfInst());
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
	public void insertInsurarerTableInsert(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.facultative.insertInsurarerTableInsert");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("Loginid", beanObj.getLoginid());
			hp.put("NoInsurer", beanObj.getNo_Insurer());
			hp.put("ProposalNo", beanObj.getProposalNo());
			hp.put("Retper", beanObj.getRetper());
			
			if(beanObj.getCedingCompanyValList()!=null) {
				for(int i=0;i<beanObj.getCedingCompanyValList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CedingCompanyValList", beanObj.getCedingCompanyValList().get(i));
					abj.put("RetroPercentage", beanObj.getRetroPercentage().get(i));
					abj.put("RetroTypeValList", beanObj.getRetroTypeValList().get(i));
					abj.put("UwYearValList", beanObj.getUwYearValList().get(i));
					array.add(abj);
					
				}
			}
			hp.put("RetroDetails", array);
			
			hp.put("RetroDupContract", beanObj.getRetroDupContract());
			hp.put("RetroDupYerar", beanObj.getRetroDupYerar());
			
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
	public void insertBonusDetails(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.insertBonusDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", beanObj.getAmendId());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContractNo", beanObj.getContractNo());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("Endorsmentno", beanObj.getEndorsmentno());
//			hp.put("LayerNo", beanObj.getLayerNo());
			hp.put("LoginId", beanObj.getLoginid());
//			hp.put("LossParticipants", beanObj.getLossParticipants());
//			hp.put("PageFor", beanObj.getPageFor());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProposalNo", beanObj.getProposalNo());
//			hp.put("SlideScaleCommission", beanObj.getSlideScaleCommission());
			hp.put("Type", beanObj.getType());
			
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
	public void insertCrestaMaintable(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.insertCrestaMaintable");
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
	public List<Map<String, Object>> viewMode(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.viewMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AmendId", formObj.getAmendId());
			hp.put("BranchCode", formObj.getBranchCode());
			hp.put("ProposalNo", formObj.getProposalNo());
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GetCoverDeductableDetails(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.GetCoverDeductableDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", formObj.getProposalNo());
			hp.put("BranchCode", formObj.getBranchCode());
			hp.put("ProductId", formObj.getProductId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GetXolCoverDeductableDetails(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.GetXolCoverDeductableDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", formObj.getProposalNo());
			hp.put("BranchCode", formObj.getBranchCode());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getRetroContractDetails(FaculitivesBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result ="";
		
		try {
			String link=getValueFromWebservice("maan.facultative.getRetroContractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("IncepDate", beanObj.getInceptionDate());
			hp.put("ProductId", beanObj.getProductId());
//			hp.put("UwYear", beanObj.getUwYear());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				result = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean GetShareValidation(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		boolean savFlg = false;
		
		try {
			String link=getValueFromWebservice("maan.facultative.GetShareValidation");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("LeaderUWShare", bean.getLeader_Underwriter_share());
			link = getActualLink(link, hp);
				
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
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
	public int getBonusListCount(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		int count = 0;
		
		try {
			String link=getValueFromWebservice("maan.facultative.getBonusListCount");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", bean.getProposalNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("AcqBonus", bean.getAcqBonus());
			hp.put("Endorsmentno", bean.getEndorsmentno());
			link = getActualLink(link, hp);
				
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("ErrorMessage"));
				count = (Integer)json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> showFirstPageItems(FaculitivesBean formObj) {
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.facultative.showFirstPageItems");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", formObj.getProposalNo());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				formObj.setErrors((JSONArray) json.get("ErrorMessage"));
				array = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
}
