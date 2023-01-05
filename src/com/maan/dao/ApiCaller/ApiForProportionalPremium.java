package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;

public class ApiForProportionalPremium extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPremiumedList(FaculPremiumBean beanObj, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.premium.PremiumedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("SectionNo", beanObj.getSectionNo());
			hp.put("OpendDate", beanObj.getOpendDate());
			hp.put("OpstartDate", beanObj.getOpstartDate());
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
	public Map<String, Object> getPreList(FaculPremiumBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONObject json,result = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.premium.PreList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONObject) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConstantPeriodDropDown(String CategoryId, String contractNo,
			String departmentId, String proposal_No) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.ConstantPeriod.DropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CategoryId", CategoryId);
			hp.put("ContractNo", contractNo);
			hp.put("SectionNo", departmentId);
			hp.put("ProposalNo", proposal_No);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String GetPreviousPremium(String ContNo) {

		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.Previous.Premium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ContractNo", ContNo);
			
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
	public String GetContractPremium(String contNo, String departmentId, String branchCode) {

		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.Contract.Premium");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", contNo);
			hp.put("SectionNo", departmentId);
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
	public List<Map<String, Object>> getClaimNosDropDown(String contNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.ClaimNos.DropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("contractNo", contNo);
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
	public Map<String, Object> contractDetails(FaculPremiumBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONObject json,result,result1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.premium.contractDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContNo", bean.getContNo());
			hp.put("SectionNo", bean.getSectionNo());
			hp.put("ProductId", bean.getProductId());
			hp.put("ProposalNo", bean.getProposal_No());
			hp.put("TransactionNo", bean.getTransactionNo());

			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (JSONObject)json.get("Result");
				result1 = (JSONObject)result.get("RiskDetails");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	@SuppressWarnings("unchecked")
	public int getCountCleanCUT(FaculPremiumBean bean) {
		
		int count = 0;
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.Count.CleanCUT");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", bean.getContNo());
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				count = (int) json.get("Result").hashCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSPRetroList(String contNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.SPRetroList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", contNo);
			
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
	public List<Map<String, Object>> getRetroContracts(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.RetroContracts");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", beanObj.getProposal_No());
			hp.put("noOfRetro", beanObj.getNoOfRetro());
			
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
	public String getDepartmentNo(FaculPremiumBean bean) {
		String result = "";
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try {
			String link=getValueFromWebservice("maan.Premium.DepartmentNo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("contractNo", bean.getContNo());
			
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
	public List<Map<String, Object>> getOSBList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.getOSBList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Transaction",bean.getTransaction());
			hp.put("ContractNo", bean.getContNo());
			hp.put("BranchCode", bean.getBranchCode());
			
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
	public void premiumInsertMethod(FaculPremiumBean beanObj, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		String result="";
		JSONArray array=new JSONArray();
		try {
		
				String[] prTransNo=beanObj.getPRTransNo().split(",");
				String[] prAmount=beanObj.getPRAmount().split(",");
				String[] preAmount=beanObj.getPREAmount().split(",");
				String[] preRate=beanObj.getPRERate().split(",");
				
				String[] prTransNo1=beanObj.getLRTransNo().split(",");
				String[] prAmount1=beanObj.getLRAmount().split(",");
				String[] preAmount1=beanObj.getLREAmount().split(",");
				String[] preRate1=beanObj.getLRERate().split(",");
					
			String link=getValueFromWebservice("maan.client.premiumInsertMethod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcceptenceDate", beanObj.getAcceptenceDate());
			hp.put("AccountPeriod", beanObj.getAccount_Period());
			hp.put("AccountPeriodDate", beanObj.getAccountPeriodDate());
			hp.put("AccountPeriodyear", beanObj.getAccount_Period_year());
			hp.put("AmendmentDate", beanObj.getAmendmentDate());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("Brokerageview", beanObj.getBrokerage_view());
			hp.put("ButtonStatus", beanObj.getButtonStatus());
			hp.put("CashLossCredit", beanObj.getCashLoss_Credit());
			hp.put("CashLossPaid", beanObj.getCash_LossPaid());
			hp.put("CedentRef", beanObj.getCedentRef());
			hp.put("Claimspaid", beanObj.getClaims_paid());
			hp.put("CliamPortfolioin", beanObj.getCliamPortfolioin());
			hp.put("Cliamportfolioout", beanObj.getCliam_portfolio_out());
			hp.put("CommissionQuotaShare", beanObj.getCommissionQuotaShare());
			hp.put("CommissionSurplus", beanObj.getCommissionSurplus());
			hp.put("Commissionview", beanObj.getCommission_view());
			hp.put("CommssionSurp", beanObj.getCommssion_Surp());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("CountryId", beanObj.getCountryId());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
			hp.put("Interest", beanObj.getInterest());
			hp.put("Layerno", beanObj.getLayerno());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossParticipation", beanObj.getLossParticipation());
			hp.put("LossReserveReleased", beanObj.getLossReserveReleased());
			hp.put("LossReserveRetained", beanObj.getLoss_ReserveRetained());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("Mode", beanObj.getMode());
			hp.put("OsClaimsLossUpdateOC", beanObj.getOsClaimsLossUpdateOC());
			hp.put("OsbYN", beanObj.getOsbYN());
			hp.put("OtherCost", beanObj.getOtherCost());
			hp.put("OverRiderview", beanObj.getOverRider_view());
			hp.put("Overrider", beanObj.getOverrider());
			hp.put("PRTransNo", beanObj.getPRTransNo());
			hp.put("Predepartment", beanObj.getPredepartment());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserveQuotaShare", beanObj.getPremiumReserve_QuotaShare());
			hp.put("PremiumReserveReleased", beanObj.getPremium_Reserve_Released());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("PremiumportifolioIn", beanObj.getPremiumportifolioIn());
			hp.put("Premiumportifolioout", beanObj.getPremiumportifolioout());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProfitCommission", beanObj.getProfit_Commission());
			hp.put("ProposalNo", beanObj.getProposal_No());
			hp.put("Receiptno", beanObj.getReceipt_no());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RequestNo", beanObj.getRequestNo());
			hp.put("RiCession", beanObj.getRi_cession());
			hp.put("SectionName", beanObj.getSectionName());
			hp.put("SectionType", beanObj.getSectionType());
			hp.put("VatPremium", beanObj.getVatPremium());
			hp.put("BrokerageVat", beanObj.getBrokerageVat());
			hp.put("DocumentType", beanObj.getDocumentType());
			hp.put("Settlementstatus", beanObj.getSettlement_status());
			hp.put("ShareSigned", beanObj.getShareSigned());
			hp.put("SlideScaleCom", beanObj.getSlideScaleCom());
			//hp.put("SourceId", beanObj.getSourceId());
			hp.put("StatementDate", beanObj.getStatementDate());
			hp.put("SubProfitId", beanObj.getSubProfitId());
			hp.put("TableType", beanObj.getTableType());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxDedectSource", beanObj.getTaxDedectSource());
			hp.put("Taxview", beanObj.getTax_view());
			hp.put("TotalCredit", beanObj.getTotalCredit());
			hp.put("TotalDebit", beanObj.getTotalDebit());
			hp.put("Transaction", beanObj.getTransaction());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			hp.put("Type", beanObj.getType());
			hp.put("WithHoldingTaxOC", beanObj.getWithHoldingTaxOC());
			hp.put("XlCost", beanObj.getXl_Cost());
			hp.put("M1OC", beanObj.getMipremium1());
			hp.put("M2OC", beanObj.getMipremium2());
			hp.put("M3OC", beanObj.getMipremium3()); 
			if(StringUtils.isNotBlank(beanObj.getPRTransNo())){
			for(int i=0;i<prTransNo.length;i++) {
				JSONObject abj = new JSONObject();
				abj.put("PRAmount", prAmount[i]);
				abj.put("PREAmount", preAmount[i]);
				abj.put("PRERate", preRate[i]);
				abj.put("PRTransNo", prTransNo[i]);
				array.add(abj);
			}
			}
			hp.put("InsertLossReserved", array);
			if(StringUtils.isNotBlank(beanObj.getPRTransNo())){
				for(int i=0;i<prTransNo.length;i++) {
					JSONObject abj = new JSONObject();
					abj.put("PRAmount", prAmount1[i]);
					abj.put("PREAmount", preAmount1[i]);
					abj.put("PRERate", preRate1[i]);
					abj.put("PRTransNo", prTransNo1[i]);
					array.add(abj);
				}
				}
				hp.put("InsertPremiumReserved", array);
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				if("Success".equals(json.get("Message"))) {
					beanObj.setRequestNo(json1.get("RequestNo")==null?"":json1.get("RequestNo").toString());
					beanObj.setTransactionNo(json1.get("TransactionNo")==null?"":json1.get("TransactionNo").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPremiumDetails(FaculPremiumBean bean,String TransactionNo,String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.premium.PremiumDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContNo());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("ProductId", bean.getProductId());
			hp.put("RequestNo", bean.getRequestNo());
			hp.put("TableType", bean.getTableType());
			hp.put("TransactionNo", TransactionNo);

			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray)json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBrokerAndCedingName(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.BrokerAndCedingName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("BranchCode", beanObj.getBranchCode());
			
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
	public List<Map<String, Object>> getAllocatedList(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.AllocatedList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", beanObj.getContNo());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			
			link=getActualLink(link,hp);
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject)json.get("Result"); 
				array = (JSONArray) json1.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.currencyList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			
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
	public List<Map<String, Object>> getPremiumReserved(FaculPremiumBean bean, String prTransNo, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.PremiumReserved");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContNo", bean.getContNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("Transaction", bean.getTransaction());
			hp.put("PRTransNo", prTransNo);
			hp.put("Chkbox", bean.getChkbox());
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
	public List<Object> getAllocatedTransList(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.Allocated.TransList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", bean.getProposal_No());
			
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
	public List<Map<String, Object>> getAllocatedCassLossCredit(FaculPremiumBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.Allocated.CassLossCredit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", bean.getProposal_No());
			hp.put("BranchCode", bean.getBranchCode());
			
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
	public List<Map<String, Object>> getCassLossCredit(FaculPremiumBean bean, String claimPayNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.CassLossCredit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			String [] value=bean.getMaincreditAmountCLClist().split(",");
			String [] value1=bean.getMaincreditAmountCLDlist().split(",");
			String [] value2=bean.getMainCLCsettlementRatelist().split(",");
			String [] value3=bean.getMainclaimPaymentNos().split(",");
			
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("Brokerage", bean.getBrokerage());
			hp.put("ClaimPayNo", bean.getClaimPaymentNo());
			hp.put("ContNo", bean.getContNo());
			hp.put("CurrencyId", bean.getCurrencyId());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("Mode", bean.getMode());
			hp.put("Tax", bean.getTax());
			
			if(StringUtils.isNotBlank(bean.getMainclaimPaymentNos())){
				for(int i=0;i<value3.length;i++) {
				JSONObject abj = new JSONObject();
//				abj.put("CLCsettlementRatelist", prAmount1[i]);
//				abj.put("Chkbox", preAmount1[i]);
//				abj.put("CreditAmountCLClist", preRate1[i]);
//				abj.put("CreditAmountCLDlist", prTransNo1[i]);
				abj.put("MainCLCsettlementRatelist", value2[i]);
				abj.put("MainclaimPaymentNos", value3[i]);
				abj.put("MaincreditAmountCLClist", value[i]);
				abj.put("MaincreditAmountCLDlist", value1[i]);
				array.add(abj);
			}
			}
			hp.put("GetCashLossCreditReq1", array);
			
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
	public Map<String, Object> premiumEdit(FaculPremiumBean bean, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.premiumEdit");
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
			
			String responseStr=callAPIPost(link, authorization , hp.toString().replaceAll("\"\"", "null"));
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
	public void premiumUpdateMethod(FaculPremiumBean beanObj) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONArray array=new JSONArray();
		try {
			String link=getValueFromWebservice("maan.Premium.premiumUpdateMethod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("AcceptenceDate", beanObj.getAcceptenceDate());
			hp.put("AccountPeriod", beanObj.getAccount_Period());
			hp.put("AccountPeriodDate", beanObj.getAccountPeriodDate());
			hp.put("AccountPeriodyear", beanObj.getAccount_Period_year());
			hp.put("AmendmentDate", beanObj.getAmendmentDate());
			hp.put("BranchCode", beanObj.getBranchCode());
			hp.put("Brokerage", beanObj.getBrokerage());
			hp.put("Brokerageview", beanObj.getBrokerage_view());
			hp.put("ButtonStatus", beanObj.getButtonStatus());
			hp.put("CashLossCredit", beanObj.getCashLoss_Credit());
			hp.put("CashLossPaid", beanObj.getCash_LossPaid());
			hp.put("CedentRef", beanObj.getCedentRef());
			hp.put("ClaimPayNo", beanObj.getClaimPaymentNo());
			hp.put("Claimspaid", beanObj.getClaims_paid());
			hp.put("CliamPortfolioin", beanObj.getCliamPortfolioin());
			hp.put("Cliamportfolioout", beanObj.getCliam_portfolio_out());
			hp.put("CommissionQuotaShare", beanObj.getCommissionQuotaShare());
			hp.put("CommissionSurplus", beanObj.getCommissionSurplus());
			hp.put("Commissionview", beanObj.getCommission_view());
			hp.put("CommssionSurp", beanObj.getCommssion_Surp());
			hp.put("ContNo", beanObj.getContNo());
			hp.put("CountryId", beanObj.getCountryId());
			hp.put("Currency", beanObj.getCurrency());
			hp.put("CurrencyId", beanObj.getCurrencyId());
			hp.put("DepartmentId", beanObj.getDepartmentId());
			hp.put("EnteringMode", beanObj.getEnteringMode());
			hp.put("ExchRate", beanObj.getExchRate());
			
			if(beanObj.getCLCsettlementRatelist()!=null) {
				for(int i=0;i<beanObj.getCLCsettlementRatelist().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("CLCsettlementRatelist", beanObj.getCLCsettlementRatelist().get(i));
					abj.put("Chkbox", beanObj.getChkbox().get(i));
					abj.put("CreditAmountCLClist", beanObj.getCreditAmountCLClist().get(i));
					abj.put("CreditAmountCLDlist", beanObj.getCreditAmountCLDlist().get(i));
					abj.put("MainCLCsettlementRatelist", beanObj.getMainCLCsettlementRatelist());
					abj.put("MainclaimPaymentNos", beanObj.getMainclaimPaymentNos());
					abj.put("MaincreditAmountCLClist", beanObj.getMaincreditAmountCLClist());
					array.add(abj);
				}
			}
			
			hp.put("GetCashLossCreditReq1", array);
			
			hp.put("InceptionDate", beanObj.getInception_Date());
			hp.put("InsDate", beanObj.getInsDate());
			
			if(beanObj.getPremiumReservedList()!=null) {
				for(int i=0;i<beanObj.getPremiumReservedList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("PRAmount", beanObj.getPRAmount());
					abj.put("PREAmount", beanObj.getPREAmount());
					abj.put("PRERate", beanObj.getPRERate());
					abj.put("PRTransNo", beanObj.getPRTransNo());
					array.add(abj);
				}
				hp.put("InsertPremiumReserved", array);
			}else {
				hp.put("PRAmount", beanObj.getPRAmount());
				hp.put("PREAmount", beanObj.getPREAmount());
				hp.put("PRERate", beanObj.getPRERate());
				hp.put("PRTransNo", beanObj.getPRTransNo());
			}
			
			hp.put("Interest", beanObj.getInterest());
			hp.put("Layerno", beanObj.getLayerno());
			hp.put("LoginId", beanObj.getLoginId());
			hp.put("LossParticipation", beanObj.getLossParticipation());
			hp.put("LossReserveReleased", beanObj.getLossReserveReleased());
			hp.put("LossReserveRetained", beanObj.getLoss_ReserveRetained());
			hp.put("MaxDate", beanObj.getMaxDate());
			hp.put("Mode", beanObj.getMode());
			hp.put("OsClaimsLossUpdateOC", beanObj.getOsClaimsLossUpdateOC());
			hp.put("OsbYN", beanObj.getOsbYN());
			hp.put("OtherCost", beanObj.getOtherCost());
			hp.put("OverRiderview", beanObj.getOverRider_view());
			hp.put("Overrider", beanObj.getOverrider());
			hp.put("PRTransNo", beanObj.getPRTransNo());
			hp.put("Predepartment", beanObj.getPredepartment());
			hp.put("PremiumQuotaShare", beanObj.getPremiumQuotaShare());
			hp.put("PremiumReserveQuotaShare", beanObj.getPremiumReserve_QuotaShare());
			hp.put("PremiumReserveReleased", beanObj.getPremium_Reserve_Released());
			hp.put("PremiumSurplus", beanObj.getPremiumSurplus());
			hp.put("PremiumportifolioIn", beanObj.getPremiumportifolioIn());
			hp.put("Premiumportifolioout", beanObj.getPremiumportifolioout());
			hp.put("ProductId", beanObj.getProductId());
			hp.put("ProfitCommission", beanObj.getProfit_Commission());
			hp.put("ProposalNo", beanObj.getProposal_No());
			hp.put("Receiptno", beanObj.getReceipt_no());
			hp.put("Remarks", beanObj.getRemarks());
			hp.put("RequestNo", beanObj.getRequestNo());
			hp.put("RiCession", beanObj.getRi_cession());
			hp.put("SectionName", beanObj.getSectionName());
			hp.put("SectionType", beanObj.getSectionType());
			hp.put("VatPremium", beanObj.getVatPremium());
			hp.put("BrokerageVat", beanObj.getBrokerageVat());
			hp.put("DocumentType", beanObj.getDocumentType());
			hp.put("Settlementstatus", beanObj.getSettlement_status());
			hp.put("ShareSigned", beanObj.getShareSigned());
			hp.put("SlideScaleCom", beanObj.getSlideScaleCom());
//			hp.put("SourceId", beanObj.getsourceId());
			hp.put("StatementDate", beanObj.getStatementDate());
			hp.put("SubProfitId", beanObj.getSubProfitId());
			hp.put("TableType", beanObj.getTableType());
			hp.put("Tax", beanObj.getTax());
			hp.put("TaxDedectSource", beanObj.getTaxDedectSource());
			hp.put("Taxview", beanObj.getTax_view());
			hp.put("TotalCredit", beanObj.getTotalCredit());
			hp.put("TotalDebit", beanObj.getTotalDebit());
			hp.put("Transaction", beanObj.getTransaction());
			hp.put("TransactionNo", beanObj.getTransactionNo());
			hp.put("Type", beanObj.getType());
			hp.put("WithHoldingTaxOC", beanObj.getWithHoldingTaxOC());
			hp.put("XlCost", beanObj.getXl_Cost());
			hp.put("M1OC", beanObj.getMipremium1());
			hp.put("M2OC", beanObj.getMipremium2());
			hp.put("M3OC", beanObj.getMipremium3()); 
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Result");
				if("Success".equals(json.get("Message"))) {
					beanObj.setRequestNo(json1.get("RequestNo")==null?"":json1.get("RequestNo").toString());
					beanObj.setTransactionNo(json1.get("TransactionNo")==null?"":json1.get("TransactionNo").toString());
				}else {
					beanObj.setErrors((JSONArray) json.get("ErrorMessage"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRipremiumList(FaculPremiumBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray json1 = null;
		try {
			String link=getValueFromWebservice("maan.client.proppremium.ripremiumlist");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("TransactionNo", bean.getTransactionNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ContractNo", bean.getContNo());
			hp.put("ProductId", bean.getProductId());
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
