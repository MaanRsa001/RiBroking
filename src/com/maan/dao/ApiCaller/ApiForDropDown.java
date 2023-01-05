package com.maan.dao.ApiCaller;


import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;


public class ApiForDropDown extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public String EditModeStatus(String proposalNo) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.claim.editmode.status");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ProposalNo", proposalNo);
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
	public List<Map<String,Object>> getCurrencyMasterDropDown(String branchCode,String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.Currency.Master");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("branchCode", branchCode);
			hp.put("countryId", countryId);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
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
	public String getDepartmentName(String branchCode, String productCode, String deptCode) {
	
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.claim.Department.Name");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("BranchCode", branchCode);
			hp.put("ProductCode", productCode);
			hp.put("DeptCode", deptCode);
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
	public List<Map<String, Object>> getClaimDepartmentDropDown(String branchCode, String productId, String status,
			String contarctno, String layerNo,String departmentid) {
			JSONObject hp = new JSONObject();
			JSONObject json = null;
			JSONParser parser = new JSONParser();
			JSONArray list=new JSONArray();
			
			try {
				String link=getValueFromWebservice("maan.client.claim.Department.DropDown");
				authorization= getValueFromWebservice("marine.insurance.auth");
				
				hp.put("BranchCode", branchCode);
				hp.put("ContractNo", contarctno);
				hp.put("DepartmentId", departmentid);
				hp.put("LayerNo", StringUtils.isBlank(layerNo)?"0":layerNo);
				hp.put("ProductId", productId);
				hp.put("Status", status);
				
				String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
				if(responseStr!=null && responseStr.length()>0) {
					json = (JSONObject) parser.parse(responseStr);
					list = (JSONArray) json.get("Result");
			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSubProfitCentreMultiDropDown(String departmentId,String branchCode,String ProductCode, String subProfitId)
	{
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.CentreMulti.DropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("DepartmentId", departmentId);
			hp.put("ProductCode", ProductCode);
			hp.put("SubProfitId", subProfitId);
			
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
	public String getCurrenCyId(String claimNo, String contractNo, String layerNo, String productId) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.claim.currencyid");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ClaimNo", claimNo);
			hp.put("ContractNo", contractNo);
			hp.put("LayerNo", StringUtils.isBlank(layerNo)?"0":layerNo);
			hp.put("ProductId", productId);
			
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
	public String getProposalNo(String productId, String contNo, String departmentId, String layerNo) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.ProposalNo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProductId", productId);
			hp.put("ContractNo", contNo);
			hp.put("DepartId", departmentId);
			hp.put("LayerNo", StringUtils.isBlank(layerNo)?"0":layerNo);
			
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
	public List<Map<String, Object>> getOpenPeriod(FaculitivesBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.OpenPeriod");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", bean.getBranchCode());
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list =  (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSectionList(String contNo, String departmentId, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray result=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.SectionList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", contNo);
			hp.put("DepartId", departmentId);
			hp.put("BranchCode", branchCode);
			
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
	public String getContractLayerNo(String contNo, String branchCode, String layerNo,String productId) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.ContractLayerNo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", contNo);
			hp.put("BranchCode", branchCode);
			hp.put("LayerNo", layerNo);
			hp.put("ProductId", productId);
			
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
	public void updateSubClass(String proposalNo, String type) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.dropdown.updatesubclass");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposalNo);
			hp.put("type", type);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
public String getCashLossCount(String contNo, String departmentId) {
	
	String result="";
	JSONObject json = null;
	JSONParser parser = new JSONParser();
	JSONObject hp = new JSONObject();
	try {
		String link=getValueFromWebservice("maan.client.CashLossCount");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
		hp.put("ContractNo", contNo);
		hp.put("DepartmentId", departmentId);
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
	public void updateRenewalEditMode(String proposalNo, String status, String updateProposalNo) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.dropdown.updateRenewalEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposalNo);
			hp.put("status", status);
			hp.put("updateProposalNo", updateProposalNo);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void updateEditMode(String proposalNo, String status, String updateProposalNo) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.dropdown.updateEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposalNo);
			hp.put("status", status);
			hp.put("updateProposalNo", updateProposalNo);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void updateSubEditMode(String proposalNo, String status, String updateProposalNo) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.dropdown.updateSubEditMode");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposalNo);
			hp.put("status", status);
			hp.put("updateProposalNo", updateProposalNo);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}		

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPreDepartmentDropDown(String branchCode, String productId, String status,
			FaculPremiumBean bean) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.claim.PreDepartment.DropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ContractNo", bean.getContNo());
			hp.put("DepartmentId", bean.getDepartmentId());
			hp.put("LayerNo", StringUtils.isBlank(bean.getLayerno())?"0":bean.getLayerno());
			hp.put("ProductId", productId);
			hp.put("Status", status);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void riskDetailsEndorsement(String proposalNo, String endtStatus) {
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.dropdown.riskDetailsEndorsement");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("proposalNo", proposalNo);
			hp.put("endtStatus", endtStatus);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public int Validatethree(String branchCode, String accountDate) {
		String vali = "";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.Validatethree");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("AccountDate", accountDate);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				vali = responseStr ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(vali);
	}

	@SuppressWarnings("unchecked")
	public String getDisableStatus(String contractNo, String layerNo) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.DropDown.DisableStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ContractNo", contractNo);
			hp.put("LayerNo", layerNo);
			
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
	public String getDisableStatus1(String contractNo, String layerNo) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.DropDown.DisableStatus1");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ContractNo", contractNo);
			hp.put("LayerNo", layerNo);
			
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
	public List<Map<String, Object>> getConstantDropDown(String categoryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getConstantDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CategoryId", categoryId);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getCeaseaccountStatus(RiskDetailsBean bean) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.DropDown.CeaseaccountStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("ContractNo", bean.getContractNo());
			
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
	public List<Map<String, Object>> getCurrencyShortList(String branchCode, String countryId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCurrencyShortList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CountryId", countryId);
	
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountryDropDown(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.getCountryDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTypeList(String type, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getTypeList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Type", type);
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTerritoryRegionList(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getTerritoryRegionList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPersonalInfoDropDown(String branchCode, String customerType, String pid) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getPersonalInfoDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CustomerType", customerType);
			hp.put("Pid", StringUtils.isBlank(pid)?"0":pid);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getUnderWriterLimmit(String uwName, String processId, String ProductId, String deptId) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getUnderWriterLimit");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("UwName", uwName);
			hp.put("ProcessId", processId);
			hp.put("ProductId", ProductId);
			hp.put("DeptId", deptId);
			
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
	public List<Map<String, Object>> getDocType(String branchCode, String productId, String moduleType) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getDocType");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ProductId", productId);
			hp.put("ModuleType", moduleType);
			
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getYearListValue(String inceptionDate) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getYearListValue");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("InceptionDate", inceptionDate);
		
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSubProfitCentreDropDown(String deptid, String branchCode, String productCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			if(StringUtils.isNotBlank(deptid)) {
				String link=getValueFromWebservice("maan.client.DropDown.getSubProfitCentreDropDown");
				authorization= getValueFromWebservice("marine.insurance.auth");
				
				hp.put("Deptid", deptid);
				hp.put("BranchCode", branchCode);
				hp.put("ProductCode", productCode);
				
				link=getActualLink(link,hp);
				
				String responseStr=callAPIGet(link, authorization);
				if(responseStr!=null && responseStr.length()>0) {
					json = (JSONObject) parser.parse(responseStr);
					list = (JSONArray) json.get("Result");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String GetExchangeRate(String location, String date, String countryid, String branchCode) {
		String result="";
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.DropDown.GetExchangeRate");
			authorization= getValueFromWebservice("marine.insurance.auth");
			hp.put("Currency", location);
			hp.put("Date", date);
			hp.put("Countryid", countryid);
			hp.put("BranchCode", branchCode);
			
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
	public List<Map<String, Object>> getUnderwriterCountryList(RiskDetailsBean bean, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getUnderwriterCountryList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("LeaderUnderriter", bean.getLeader_Underwriter());
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProfitCentreDropDown(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getProfitCentreDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUnderWritterDropDown(String branchCode, String attachedUW) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getUnderWritterDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("AttachedUW", attachedUW);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> RenewalDropDown(String branchCode, String productCode, String status) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.RenewalDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("ProductCode", productCode);
			hp.put("Status", status);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDepartmentDropDown(String branchCode, String productCode, String status,
			String contNo, String endt, String proposalNo, String mode, String baseLayer) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getDepartmentDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BaseLayer", baseLayer);
			hp.put("BranchCode", branchCode);
			hp.put("ProductCode", productCode);
			hp.put("ContNo", contNo);
			hp.put("ProposalNo", proposalNo);
			hp.put("Mode", mode);
			hp.put("BaseLayer", baseLayer);
			hp.put("Status", status);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPolicyBranchDropDown(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getPolicyBranchDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTerritoryDropDown(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getTerritoryDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractValidation(String productId, String cedingCompany, String inceptionDate,String expiryDate, String year, String originalCurrency, String departmentId, String type,String sumInsured, String contNo, String profitCenter, String surplus, String coverPer, String dedPer,String layerNo, String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getContractValidation");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CedingCo", cedingCompany);
			hp.put("ContNo", contNo);
			hp.put("DepartId", departmentId);
			hp.put("ExpDate", expiryDate);
			hp.put("IncepDate", inceptionDate);
			hp.put("LayerNo", layerNo);
			hp.put("OrginalCurrency", originalCurrency);
			hp.put("ProductId", productId);
			hp.put("ProfitCenter", profitCenter);
			hp.put("TreatyType", type);
			hp.put("UwYear", year);
			hp.put("SumInsured", sumInsured);
			hp.put("Surplus", surplus);
			hp.put("CoverPer", coverPer);
			hp.put("DedPer", dedPer);
		
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCrestaIDList(String branchCode, String crestaValue) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCrestaIDList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CrestaValue", crestaValue);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCrestaNameList(String branchCode, String crestaValue) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCrestaNameList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CrestaValue", crestaValue);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getCrestaName(String branchCode, String crestaValue) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCrestaName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CrestaValue", crestaValue);
			
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
	public List<Map<String, Object>> getCountryDate(String branchCode, String dEC_FILE) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCountryDate");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("DEC_FILE", dEC_FILE);
			
//			link=getActualLink(link,hp);
//			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConstantDropDownBusinessType(String categoryId, String pid, String deptId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getConstantDropDownBusinessType");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CategoryId", categoryId);
			hp.put("ProductId", pid);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConstantDropDownBasis(String categoryId, String pid, String deptId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getConstantDropDownBasis");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CategoryId", categoryId);
			hp.put("ProductId", pid);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoverDEpartmentDropDown(String branchCode, String pid, String departId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getCoverDEpartmentDropDown");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Branchcode", branchCode);
			hp.put("ProductId", pid);
			hp.put("DepartId", departId);
			
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getAllocationDisableStatus(String contractNo, String layerNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String status="";
		
		try {
			String link=getValueFromWebservice("maan.facul.DropDown.getAllocationDisableStatus");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ContractNo", contractNo);
			hp.put("LayerNo", layerNo);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				status = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public String getCompanyName(String branchCode, String custId, String custType) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String status="";
		
		try {
			String link=getValueFromWebservice("maan.facul.DropDown.getCompanyName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BranchCode", branchCode);
			hp.put("CustId", custId);
			hp.put("CustType", custType);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				status = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public String getBaseProposal(String proposalNo) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String status="";
		
		try {
			String link=getValueFromWebservice("maan.facul.DropDown.getBaseProposal");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProposalNo", proposalNo);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				status = (String) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDeptName(String branchCode) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray list=new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.DropDown.getDeptName");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Branchcode", branchCode);
			link=getActualLink(link,hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				list = (JSONArray) json.get("Result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	}

