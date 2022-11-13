package com.maan.bean;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 *
 */
public class PortfolioBean 
{
	private String proposalNo;
	private String ceding_Company_Name;
	private String department_Name;
	private String quote_Gendrated_date;
	private String inception_Date;
	private String expiry_Date;
	private String cedding_company_id;
	private String proposalId;
    private String mode;
    private String baseLayer;
    private String renewal_Status;
    private String old_Contract;
    private String renewalPeriod;
    private String underwritter;
    private String uwMonth;
    private String flag;
    private String brokerName;
    private String searchType;
    private String searchValue;
    private String branchCode;
    private String loginId;
    private String userType;
    private String productId;
    private String deptId;
    private String reqFrom;
    private String display;
    private String amendId;
    private String menuId;
    private String tilte;
    private String title;
    private String departmentId;
    private String contractno1;
    private String lay1;
    private String endorsementStatus;
    private String subClass;
    private String insuredName;
    private String shareSign;
    private String currencyShort;
    private RiskDetailsBean riskDetailsBean;
    private FaculPremiumBean faculPremiumBean;
    private String exchangeRate;
    private String transactionDate;
    private String dueDate;
    private String installmentDate;
    private String receiveDate;
    private String currencyId;
    private String transactionNo;
    private String contractNo;
    private String brokerId;
    private String premiumOC;
    private String commission;
    private String brokerage;
    private String tax;
    private String otherCost;
    private String openPeriodEndDate;
    private String openPeriodStartDate;
    private boolean isValid;
    private String installmentNo;
    private String remarks;
    private String brokerageOC;
    private String taxOC;
    private String otherCostOC;
    private String reference;
    private String commissionOC;
    private String treatyName;
    private String netDueOC;
    private String autoopenDate;
    private String endtMode;
    private String proposal_no;
    private String offerNo;
    private String proposalNo1;
    private String pageNumber;
    private String ceaseStatus;
    private String type;
    private String endorsmentTypeName;
    private String oldStatus;
    private String proposalStatus;
    private String contractStatus;
    private String disableStatus;
    private String renewaldisable;
    private String classdisable;
    private String layerdisable;
    private String gnpiFlag;
    
    private int premiumcount;
    private int claimCount;
    private String premiumStatus;
    private String claimStatus;
    private String editMode;
    private String multiuserError;
    private String backMode;
  
    private String  productName;
    private String  loginName;
    private String  cedingId;
    private String  cedingCoName;
    private String sysDate 	;	
    private String excelFileName;
    private String premiumClass;
	private String premiumSubClass;
	private String combinedClassCount;
	
	private List<List<Map<String,Object>>> buttonList;
	private List<Map<String,Object>> buttonSelectionList;
	
	private String offerNoSearch;
	private String proposalNoSearch;
	private String companyNameSearch;
	private String brokerNameSearch;
	private String departmentNameSearch;
	private String insuredNameSearch;
	private String uwYearSearch;
	private String underwriterSearch;
	private String uwYearSearch1;
	private String underwriterSearch1;
	private String contractNoSearch;
	private String companyNameSearch1;
	private String brokerNameSearch1;
	private String uwYearSearch2;
	private String uwYearSearch3;
	private String departmentNameSearch1;
	private String transactionError;
	private String attachedUW;
	private String acceptenceDate;
	private String bouquetNoSearch;
	private String subclassSearch;
	private String bouquetNo;
	
	public String getAttachedUW() {
		return attachedUW;
	}
	public void setAttachedUW(String attachedUW) {
		this.attachedUW = attachedUW;
	}
	public String getAcceptenceDate() {
		return acceptenceDate;
	}
	public void setAcceptenceDate(String acceptenceDate) {
		this.acceptenceDate = acceptenceDate;
	}
	public String getTransactionError() {
		return transactionError;
	}
	public void setTransactionError(String transactionError) {
		this.transactionError = transactionError;
	}
	public String getDepartmentNameSearch1() {
		return departmentNameSearch1;
	}
	public void setDepartmentNameSearch1(String departmentNameSearch1) {
		this.departmentNameSearch1 = departmentNameSearch1;
	}
	public String getProposalNoSearch() {
		return proposalNoSearch;
	}
	public void setProposalNoSearch(String proposalNoSearch) {
		this.proposalNoSearch = proposalNoSearch;
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
	public String getDepartmentNameSearch() {
		return departmentNameSearch;
	}
	public void setDepartmentNameSearch(String departmentNameSearch) {
		this.departmentNameSearch = departmentNameSearch;
	}
	public String getInsuredNameSearch() {
		return insuredNameSearch;
	}
	public void setInsuredNameSearch(String insuredNameSearch) {
		this.insuredNameSearch = insuredNameSearch;
	}
	public String getUwYearSearch() {
		return uwYearSearch;
	}
	public void setUwYearSearch(String uwYearSearch) {
		this.uwYearSearch = uwYearSearch;
	}
	public String getUnderwriterSearch() {
		return underwriterSearch;
	}
	public void setUnderwriterSearch(String underwriterSearch) {
		this.underwriterSearch = underwriterSearch;
	}
	public String getUwYearSearch1() {
		return uwYearSearch1;
	}
	public void setUwYearSearch1(String uwYearSearch1) {
		this.uwYearSearch1 = uwYearSearch1;
	}
	public String getUnderwriterSearch1() {
		return underwriterSearch1;
	}
	public void setUnderwriterSearch1(String underwriterSearch1) {
		this.underwriterSearch1 = underwriterSearch1;
	}
	public String getContractNoSearch() {
		return contractNoSearch;
	}
	public void setContractNoSearch(String contractNoSearch) {
		this.contractNoSearch = contractNoSearch;
	}
	public String getCompanyNameSearch1() {
		return companyNameSearch1;
	}
	public void setCompanyNameSearch1(String companyNameSearch1) {
		this.companyNameSearch1 = companyNameSearch1;
	}
	public String getBrokerNameSearch1() {
		return brokerNameSearch1;
	}
	public void setBrokerNameSearch1(String brokerNameSearch1) {
		this.brokerNameSearch1 = brokerNameSearch1;
	}
	public String getUwYearSearch2() {
		return uwYearSearch2;
	}
	public void setUwYearSearch2(String uwYearSearch2) {
		this.uwYearSearch2 = uwYearSearch2;
	}
	public String getUwYearSearch3() {
		return uwYearSearch3;
	}
	public void setUwYearSearch3(String uwYearSearch3) {
		this.uwYearSearch3 = uwYearSearch3;
	}
	public List<Map<String, Object>> getButtonSelectionList() {
		return buttonSelectionList;
	}
	public void setButtonSelectionList(List<Map<String, Object>> buttonSelectionList) {
		this.buttonSelectionList = buttonSelectionList;
	}
	public List<List<Map<String, Object>>> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<List<Map<String, Object>>> buttonList) {
		this.buttonList = buttonList;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCedingId() {
		return cedingId;
	}
	public void setCedingId(String cedingId) {
		this.cedingId = cedingId;
	}
	public String getCedingCoName() {
		return cedingCoName;
	}
	public void setCedingCoName(String cedingCoName) {
		this.cedingCoName = cedingCoName;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getBackMode() {
		return backMode;
	}
	public void setBackMode(String backMode) {
		this.backMode = backMode;
	}
	public String getMultiuserError() {
		return multiuserError;
	}
	public void setMultiuserError(String multiuserError) {
		this.multiuserError = multiuserError;
	}
	public String getEditMode() {
		return editMode;
	}
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}
	public String getPremiumStatus() {
		return premiumStatus;
	}
	public void setPremiumStatus(String premiumStatus) {
		this.premiumStatus = premiumStatus;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public int getPremiumcount() {
		return premiumcount;
	}
	public void setPremiumcount(int premiumcount) {
		this.premiumcount = premiumcount;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
	public String getGnpiFlag() {
		return gnpiFlag;
	}
	public void setGnpiFlag(String gnpiFlag) {
		this.gnpiFlag = gnpiFlag;
	}
	public String getRenewaldisable() {
		return renewaldisable;
	}
	public void setRenewaldisable(String renewaldisable) {
		this.renewaldisable = renewaldisable;
	}
	public String getClassdisable() {
		return classdisable;
	}
	public void setClassdisable(String classdisable) {
		this.classdisable = classdisable;
	}
	public String getLayerdisable() {
		return layerdisable;
	}
	public void setLayerdisable(String layerdisable) {
		this.layerdisable = layerdisable;
	}
	public String getDisableStatus() {
		return disableStatus;
	}
	public void setDisableStatus(String disableStatus) {
		this.disableStatus = disableStatus;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	public String getEndorsmentTypeName() {
	return endorsmentTypeName;
}
public void setEndorsmentTypeName(String endorsmentTypeName) {
	this.endorsmentTypeName = endorsmentTypeName;
}
	private List<Map<String,Object>> menuList;
    
    public List<Map<String, Object>> getMenuList() {
	return menuList;
    }
	public void setMenuList(List<Map<String, Object>> menuList) {
		this.menuList = menuList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		public String getCeaseStatus() {
		return ceaseStatus;
	}
	public void setCeaseStatus(String ceaseStatus) {
		this.ceaseStatus = ceaseStatus;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getProposalNo1() {
		return proposalNo1;
	}
	public void setProposalNo1(String proposalNo1) {
		this.proposalNo1 = proposalNo1;
	}
	public String getProposal_no() {
		return proposal_no;
	}
	public void setProposal_no(String proposalNo) {
		proposal_no = proposalNo;
	}
	public String getEndtMode() {
		return endtMode;
	}
	public void setEndtMode(String endtMode) {
		this.endtMode = endtMode;
	}
	public String getAutoopenDate() {
		return autoopenDate;
	}
	public void setAutoopenDate(String autoopenDate) {
		this.autoopenDate = autoopenDate;
	}
	public String getEndorsementStatus() {
		return endorsementStatus;
	}
	public void setEndorsementStatus(String endorsementStatus) {
		this.endorsementStatus = endorsementStatus;
	}
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	private List<PortfolioBean> portfolioList;
    public List<PortfolioBean> getPortfolioList() {
		return portfolioList;
	}
	public void setPortfolioList(List<PortfolioBean> portfolioList) {
		this.portfolioList = portfolioList;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
    
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
    public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUwMonth() {
		return uwMonth;
	}
	public void setUwMonth(String uwMonth) {
		this.uwMonth = uwMonth;
	}
	public String getUwYear() {
		return uwYear;
	}
	public void setUwYear(String uwYear) {
		this.uwYear = uwYear;
	}
	private String uwYear;
	public String getUnderwritter() {
		return underwritter;
	}
	public void setUnderwritter(String underwritter) {
		this.underwritter = underwritter;
	}
	public String getRenewalPeriod() {
		return renewalPeriod;
	}
	public void setRenewalPeriod(String renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}
	public String getRenewal_Status() {
		return renewal_Status;
	}
	public void setRenewal_Status(String renewalStatus) {
		renewal_Status = renewalStatus;
	}
	public String getOld_Contract() {
		return old_Contract;
	}
	public void setOld_Contract(String oldContract) {
		old_Contract = oldContract;
	}
	public String getLayerNo() {
		return layerNo;
	}
	public void setLayerNo(String layerNo) {
		this.layerNo = layerNo;
	}
	private String layerNo;
	
 
	public String getBaseLayer() {
		return baseLayer;
	}
	public void setBaseLayer(String baseLayer) {
		this.baseLayer = baseLayer;
	}
	public String getMode() {
	return mode;
}
	public void setMode(String mode) {
	this.mode = mode;
}
	public String getProposalId() {
		return proposalId;
	}
	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}
	public String getCedding_company_id() {
		return cedding_company_id;
	}
	public void setCedding_company_id(String ceddingCompanyId) {
		cedding_company_id = ceddingCompanyId;
	}
	public String getDepartment_Name() {
		return department_Name;
	}
	public void setDepartment_Name(String departmentName) {
		department_Name = departmentName;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getCeding_Company_Name() {
		return ceding_Company_Name;
	}
	public void setCeding_Company_Name(String cedingCompanyName) {
		ceding_Company_Name = cedingCompanyName;
	}
	
	public String getQuote_Gendrated_date() {
		return quote_Gendrated_date;
	}
	public void setQuote_Gendrated_date(String quoteGendratedDate) {
		quote_Gendrated_date = quoteGendratedDate;
	}
	public String getInception_Date() {
		return inception_Date;
	}
	public void setInception_Date(String inceptionDate) {
		inception_Date = inceptionDate;
	}
	public String getExpiry_Date() {
		return expiry_Date;
	}
	public void setExpiry_Date(String expiryDate) {
		expiry_Date = expiryDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getContractno1() {
		return contractno1;
	}
	public void setContractno1(String contractno1) {
		this.contractno1 = contractno1;
	}
	public String getLay1() {
		return lay1;
	}
	public void setLay1(String lay1) {
		this.lay1 = lay1;
	}

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public RiskDetailsBean getRiskDetailsBean() {
        return riskDetailsBean;
    }

    public void setRiskDetailsBean(RiskDetailsBean riskDetailsBean) {
        this.riskDetailsBean = riskDetailsBean;
    }

    public FaculPremiumBean getFaculPremiumBean() {
        return faculPremiumBean;
    }

    public void setFaculPremiumBean(FaculPremiumBean facultPremiumBean) {
        this.faculPremiumBean = facultPremiumBean;
    }

    public String getShareSign() {
        return shareSign;
    }

    public void setShareSign(String shareSign) {
        this.shareSign = shareSign;
    }

    public String getCurrencyShort() {
        return currencyShort;
    }

    public void setCurrencyShort(String currencyShort) {
        this.currencyShort = currencyShort;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setInstallmentDate(String installmentDate) {
        this.installmentDate = installmentDate;
    }

    public String getInstallmentDate() {
        return installmentDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setPremiumOC(String premiumOC) {
        this.premiumOC = premiumOC;
    }

    public String getPremiumOC() {
        return premiumOC;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getCommission() {
        return commission;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTax() {
        return tax;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOpenPeriodEndDate(String openPeriodEndDate) {
        this.openPeriodEndDate = openPeriodEndDate;
    }

    public String getOpenPeriodEndDate() {
        return openPeriodEndDate;
    }

    public void setOpenPeriodStartDate(String openPeriodStartDate) {
        this.openPeriodStartDate = openPeriodStartDate;
    }

    public String getOpenPeriodStartDate() {
        return openPeriodStartDate;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setInstallmentNo(String installmentNo) {
        this.installmentNo = installmentNo;
    }

    public String getInstallmentNo() {
        return installmentNo;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getBrokerageOC() {
        return brokerageOC;
    }

    public void setBrokerageOC(String brokerageOC) {
        this.brokerageOC = brokerageOC;
    }

    public String getTaxOC() {
        return taxOC;
    }

    public void setTaxOC(String taxOC) {
        this.taxOC = taxOC;
    }

    public String getOtherCostOC() {
        return otherCostOC;
    }

    public void setOtherCostOC(String otherCostOC) {
        this.otherCostOC = otherCostOC;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCommissionOC() {
        return commissionOC;
    }

    public void setCommissionOC(String commissionOC) {
        this.commissionOC = commissionOC;
    }

    public void setTreatyName(String treatyName) {
        this.treatyName = treatyName;
    }

    public String getTreatyName() {
        return treatyName;
    }

    public void setNetDueOC(String netDueOC) {
        this.netDueOC = netDueOC;
    }

    public String getNetDueOC() {
        return netDueOC;
    }
	public String getPremiumClass() {
		return premiumClass;
	}
	public void setPremiumClass(String premiumClass) {
		this.premiumClass = premiumClass;
	}
	public String getPremiumSubClass() {
		return premiumSubClass;
	}
	public void setPremiumSubClass(String premiumSubClass) {
		this.premiumSubClass = premiumSubClass;
	}
	public String getCombinedClassCount() {
		return combinedClassCount;
	}
	public void setCombinedClassCount(String combinedClassCount) {
		this.combinedClassCount = combinedClassCount;
	}
	public String getBouquetNoSearch() {
		return bouquetNoSearch;
	}
	public void setBouquetNoSearch(String bouquetNoSearch) {
		this.bouquetNoSearch = bouquetNoSearch;
	}
	public String getSubclassSearch() {
		return subclassSearch;
	}
	public void setSubclassSearch(String subclassSearch) {
		this.subclassSearch = subclassSearch;
	}
	public String getBouquetNo() {
		return bouquetNo;
	}
	public void setBouquetNo(String bouquetNo) {
		this.bouquetNo = bouquetNo;
	}
	public String getOfferNo() {
		return offerNo;
	}
	public void setOfferNo(String offerNo) {
		this.offerNo = offerNo;
	}
	public String getOfferNoSearch() {
		return offerNoSearch;
	}
	public void setOfferNoSearch(String offerNoSearch) {
		this.offerNoSearch = offerNoSearch;
	}
    
}
