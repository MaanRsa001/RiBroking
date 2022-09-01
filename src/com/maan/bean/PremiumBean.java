package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PremiumBean {
	private String opstartDate;
	private String opendDate;
	private String transactionDate;
	private String transOpenperiodStatus;
	private String allocatedYN;
	private String layerno;	
	private String contractNo;
	private String transactionNo;
	private String proposal_No;
	private String ceding_Company_Name;
	private String broker;
	private String inception_Date;
	private String settlement_Status;
	private String branchCode;
	private String transactionType;
	private String InsDate;
	private String flag;
	private String premiumDisplay;
	private String expDate;
	private String transactionTypeName;
	private String dept;
	private String underwritingYear;
	private List<Map<String,Object>> yearList;
	private String underwriter;
	private String old_Contract;
	private String productId;
	private String type;
	private String deleteStatus;
	private String userName;
	private String departmentId;
	private String multiuserError;
	
	private String companyNameSearch;
	private String brokerNameSearch;
	private String contractNoSearch;
	private String transactionNoSearch;
	private String transactionDateSearch;
	private String searchType;
	private String requestNo;
	
	private String companyNameSearchTemp;
	private String brokerNameSearchTemp;
	private String contractNoSearchTemp;
	private String transactionNoSearchTemp;
	private String transactionDateSearchTemp;
	
	
	
	
	
	public String getCompanyNameSearchTemp() {
		return companyNameSearchTemp;
	}
	public void setCompanyNameSearchTemp(String companyNameSearchTemp) {
		this.companyNameSearchTemp = companyNameSearchTemp;
	}
	public String getBrokerNameSearchTemp() {
		return brokerNameSearchTemp;
	}
	public void setBrokerNameSearchTemp(String brokerNameSearchTemp) {
		this.brokerNameSearchTemp = brokerNameSearchTemp;
	}
	public String getContractNoSearchTemp() {
		return contractNoSearchTemp;
	}
	public void setContractNoSearchTemp(String contractNoSearchTemp) {
		this.contractNoSearchTemp = contractNoSearchTemp;
	}
	public String getTransactionNoSearchTemp() {
		return transactionNoSearchTemp;
	}
	public void setTransactionNoSearchTemp(String transactionNoSearchTemp) {
		this.transactionNoSearchTemp = transactionNoSearchTemp;
	}
	public String getTransactionDateSearchTemp() {
		return transactionDateSearchTemp;
	}
	public void setTransactionDateSearchTemp(String transactionDateSearchTemp) {
		this.transactionDateSearchTemp = transactionDateSearchTemp;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getCompanyNameSearch() {
		return companyNameSearch;
	}
	public void setCompanyNameSearch(String companyNameSearch) {
		this.companyNameSearch = companyNameSearch;
	}
	public String getBrokerNameSearch() {
		return brokerNameSearch;
	}
	public void setBrokerNameSearch(String brokerNameSearch) {
		this.brokerNameSearch = brokerNameSearch;
	}
	public String getContractNoSearch() {
		return contractNoSearch;
	}
	public void setContractNoSearch(String contractNoSearch) {
		this.contractNoSearch = contractNoSearch;
	}
	public String getTransactionNoSearch() {
		return transactionNoSearch;
	}
	public void setTransactionNoSearch(String transactionNoSearch) {
		this.transactionNoSearch = transactionNoSearch;
	}
	public String getTransactionDateSearch() {
		return transactionDateSearch;
	}
	public void setTransactionDateSearch(String transactionDateSearch) {
		this.transactionDateSearch = transactionDateSearch;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getMultiuserError() {
		return multiuserError;
	}
	public void setMultiuserError(String multiuserError) {
		this.multiuserError = multiuserError;
	}
	private List<Map<String,Object>> productIdList =new ArrayList<Map<String,Object>>();
	private List<PremiumBean> premiumList=new ArrayList<PremiumBean>();
	private List<PremiumBean> premiumTempList=new ArrayList<PremiumBean>();
	private List<Map<String,Object>> brokerList =new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> cedingCompanyList =new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> underWriterlist =new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> departIdlist =new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> predepartIdlist =new ArrayList<Map<String,Object>>();
	private List<PremiumBean> contractIdentifierList;
	private String transDropDownVal;
	private String ceaseStatus;
	
	
	
	public List<PremiumBean> getPremiumTempList() {
		return premiumTempList;
	}
	public void setPremiumTempList(List<PremiumBean> premiumTempList) {
		this.premiumTempList = premiumTempList;
	}
	public String getCeaseStatus() {
		return ceaseStatus;
	}
	public void setCeaseStatus(String ceaseStatus) {
		this.ceaseStatus = ceaseStatus;
	}
	public String getTransDropDownVal() {
		return transDropDownVal;
	}
	public void setTransDropDownVal(String transDropDownVal) {
		this.transDropDownVal = transDropDownVal;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUnderwriter() {
		return underwriter;
	}
	public void setUnderwriter(String underwriter) {
		this.underwriter = underwriter;
	}
	public String getOld_Contract() {
		return old_Contract;
	}
	public void setOld_Contract(String oldContract) {
		old_Contract = oldContract;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getUnderwritingYear() {
		return underwritingYear;
	}
	public void setUnderwritingYear(String underwritingYear) {
		this.underwritingYear = underwritingYear;
	}
	
	public List<PremiumBean> getContractIdentifierList() {
		return contractIdentifierList;
	}
	public void setContractIdentifierList(List<PremiumBean> contractIdentifierList) {
		this.contractIdentifierList = contractIdentifierList;
	}
	public List<Map<String, Object>> getYearList() {
		return yearList;
	}
	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}
	public List<Map<String, Object>> getUnderWriterlist() {
		return underWriterlist;
	}
	public void setUnderWriterlist(List<Map<String, Object>> underWriterlist) {
		this.underWriterlist = underWriterlist;
	}
	public List<Map<String, Object>> getDepartIdlist() {
		return departIdlist;
	}
	public void setDepartIdlist(List<Map<String, Object>> departIdlist) {
		this.departIdlist = departIdlist;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Map<String, Object>> getCedingCompanyList() {
		return cedingCompanyList;
	}
	public void setCedingCompanyList(List<Map<String, Object>> cedingCompanyList) {
		this.cedingCompanyList = cedingCompanyList;
	}
	public String getTransactionTypeName() {
		return transactionTypeName;
	}
	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getPremiumDisplay() {
		return premiumDisplay;
	}
	public void setPremiumDisplay(String premiumDisplay) {
		this.premiumDisplay = premiumDisplay;
	}
	public List<Map<String, Object>> getProductIdList() {
		return productIdList;
	}
	public void setProductIdList(List<Map<String, Object>> productIdList) {
		this.productIdList = productIdList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getInsDate() {
		return InsDate;
	}
	public void setInsDate(String insDate) {
		InsDate = insDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getProposal_No() {
		return proposal_No;
	}
	public void setProposal_No(String proposalNo) {
		proposal_No = proposalNo;
	}
	public String getCeding_Company_Name() {
		return ceding_Company_Name;
	}
	public void setCeding_Company_Name(String cedingCompanyName) {
		ceding_Company_Name = cedingCompanyName;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getInception_Date() {
		return inception_Date;
	}
	public void setInception_Date(String inceptionDate) {
		inception_Date = inceptionDate;
	}
	public String getSettlement_Status() {
		return settlement_Status;
	}
	public void setSettlement_Status(String settlementStatus) {
		settlement_Status = settlementStatus;
	}
	public List<PremiumBean> getPremiumList() {
		return premiumList;
	}
	public void setPremiumList(List<PremiumBean> premiumList) {
		this.premiumList = premiumList;
	}
	public String getLayerno() {
		return layerno;
	}
	public void setLayerno(String layerno) {
		this.layerno = layerno;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTransOpenperiodStatus() {
		return transOpenperiodStatus;
	}
	public void setTransOpenperiodStatus(String transOpenperiodStatus) {
		this.transOpenperiodStatus = transOpenperiodStatus;
	}
	public String getAllocatedYN() {
		return allocatedYN;
	}
	public void setAllocatedYN(String allocatedYN) {
		this.allocatedYN = allocatedYN;
	}
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getOpstartDate() {
		return opstartDate;
	}

	public void setOpstartDate(String opstartDate) {
		this.opstartDate = opstartDate;
	}

	public String getOpendDate() {
		return opendDate;
	}

	public void setOpendDate(String opendDate) {
		this.opendDate = opendDate;
	}
	public List<Map<String, Object>> getPredepartIdlist() {
		return predepartIdlist;
	}
	public void setPredepartIdlist(List<Map<String, Object>> predepartIdlist) {
		this.predepartIdlist = predepartIdlist;
	}


}
