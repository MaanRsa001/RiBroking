package com.maan.bean;

import java.util.List;
import java.util.Map;

public class ReportsBean {

	private String acqcost;
	private String claimPaid;
	private String premiumReserve;
	private String claimReserve;
	private String claimOutstandingPL;
	private String claimOutstandingBS;
	private int credit;
	private int debit;
	private String interest;
	private double creditLoss;
	private double percentDebit;
	private double percentCredit;
	private double creditTotal;
	private double debitTotal;
	private String percentDebit1;
	private String percentCredit1;
	private String debitTotal1;
	private String creditTotal1;
	private double premiumview;
	private String dept;
	private String movementType;
	private String typeId;
	private String productId;
	private String display;
	private String branchCode;
	private String SNo;
	private String account_Period;
	private String accper;
	private String accountDate;
	private String reportDate;
	private String changedYN;
	private String brokerId;
	private String dropDown;
	private String reportName;
	private String reportType;
	private String countryId;
	private String startDateLabelName;
	private String endDateLabelName;
	private String productName;
	private String loginName;
	private String brokerName;
	private String cedingId;
	private String cedingCoName;
	private String sysDate;
	private String start_date;
	private String end_date;
	private String loginId;
	private String uwYear;
	private String headMes;
	private String dateMes;
	private String proMes;
	private String movId;
	private String spc;
	private String spcName;
	private String currencyname;
	private String previousPremium;
	private String currentPremium;
	private String diffPremium;
	private String currentGrossPre;
	private String preGrossPre;
	private String diffGrossPre;
	private String currentGrossAcq;
	private String previousGrossAcq;
	private String diffGrossAcq;
	private String currentPremiumDepositRet;
	private String previousPremiumDepositRet;
	private String diffPremiumDepositRet;
	private String currentPremiumDepositRel;
	private String previousPremiumDepositRel;
	private String diffPremiumDepositRel;
	private String currentClaimDepositRet;
	private String previousClaimDepositRet;
	private String diffClaimDepositRet;
	private String currentClaimDepositRel;
	private String previousClaimDepositRel;
	private String diffClaimDepositRel;
	private String currentInterestDeposit;
	private String previousInterestDeposit;
	private String diffInterestDeposit;
	private String currentGrossClaimPaid;
	private String previousGrossClaimPaid;
	private String diffGrossClaimPaid;
	private String sumofPrm;
	private String sumofPrmDC;
	private String grossPremiumOC;
	private String grossPremiumDC;
	private String grossAcqCostOC;
	private String grossAcqCostDC;
	private String premiumDepositRetainedDC;
	private String premiumDepositRetainedOC;
	private String premiumDepositReleasedOC;
	private String premiumDepositReleasedDC;
	private String claimDepositRetainedOC;
	private String claimDepositRetainedDC;
	private String claimDepositReleasedOC;
	private String claimDepositReleasedDC;
	private String interestOnDepositOC;
	private String interestOnDepositDC;
	private String grossClaimPaidOC;
	private String grossClaimPaidDC;
	private String brokerLedCtlOC;
	private String brokerLedCtlDC;
	private String totalCROC;
	private String totalDROC;
	private String totalCRDC;
	private String totalDRDC;
	private String gpOC;
	private String gAOC;
	private String pdRetOC;
	private String pdRelOC;
	private String cdRelOC;
	private String cdRetOC;
	private String intOC;
	private String gcpOC;
	private String blOC;
	private String contractNo;
	private String tranNo;
	private String type;
	private String osLossupdateOC;
	private String osLossupdateDC;
	private String osLossMovement;
	private String osLossMovementUSD;
	private String bsMovement;
	private String bsMovementUSD;
	private String journelClaimPaid;
	private String journelClaimPaidUSD;
	private String debtorsCreditors;
	private String debtorsCreditorsUSD;
	private String osLossMovementBs;
	private String osLossMovementBsUSD;
	private String osLossPl;
	private String osLossPlUSD;
	private String osLM;
	private String bsM;
	private String jCP;
	private String dC;
	private String osLMB;
	private String osLP;
	private String excelFileName;
	private String product;
	
	private String bkdUprD;
	private String bkdDefAcqCostC;
	private String inwrdBkdUprBsC;
	private String inwrdBkdDefAcqCostBsD;
	private String piplinUprC;
	private String piplinAcqCostD;
	private String inwrdPiplinUprBSD;
	private String inwrdPiplinDefAcqCostBsC;
	
	private String grsPremiumC;
	private String grsPiplinPremiumD;
	private String grsAcqCostD;
	private String grsPiplinAcqCostC;
	private String preResRetainD;
	private String preResReleaseC;
	private String lossResRetainD;
	private String lossResReleaseC;
	private String bkdPreInterestC;
	private String grsClaimsPaidD;
	private String busiPartInwrdNetAcD;
	private String piplinNetAcC;
	
	private String piplinPremiumC;
	private String piplinNetAcD;
	private String inwrdPiplinUnearnPremiumD;
	private String inwrdPiplinDefAcqCostC;
	private String inwrdPiplinUprBsC;
	private String inwrdPiplinDefAcqCostBsD;
	
	private String currencyId;
	private String movementId;
	private String count;
	private String result;
	
	private String startDate;
	private String endDate;
	private String settlementType;
	private String broker;
	private String cedingCompany;
	private String status;
	private String allocateStatus;
	private String allocationNo;
	private String allocationDate;
	private String paymentReceiptNo;
	private String receiptPayAmtOC;
	private String receiptPayAmtUGX;
	private String allocateAmtOC;
	private String allocateAmtUGX;
	private String docType;
	private String test;
	private String showAllFields;
	private String payrecType;
	private String transactionType;
	private String fileName;
	private List<Map<String,Object>>proposaltypelist;
	private List<Map<String,Object>>departIdlist;
	private String treatyMainClass;
	private String treatyType;
	private String openperiod;
	private String debFrom;
	private String debTo;
	private String debFrom1;
	private String debTo1;
	private String debFrom2;
	private String debTo2;
	private String debFrom3;
	private String debTo3;
	private String debFrom4;
	private String debTo4;
	private String debFrom5;
	private String debTo5;
	private String periodFrom;
	private String periodTo;
	private String uwFrom;
	private String uwTo;
	private String layerNo;
	private String proposalNo;
	private String imagePath;
	private String receiptNo;
	
	private String settledItems;
	private String unAllocatedCash;
	private String saperatePRCLYN;
	private String journalType;
	private String allocationYN;
	
	
	public String getAllocationYN() {
		return allocationYN;
	}
	public void setAllocationYN(String allocationYN) {
		this.allocationYN = allocationYN;
	}
	public String getSettledItems() {
		return settledItems;
	}
	public void setSettledItems(String settledItems) {
		this.settledItems = settledItems;
	}
	public String getUnAllocatedCash() {
		return unAllocatedCash;
	}
	public void setUnAllocatedCash(String unAllocatedCash) {
		this.unAllocatedCash = unAllocatedCash;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public List<Map<String, Object>> getDepartIdlist() {
		return departIdlist;
	}
	public void setDepartIdlist(List<Map<String, Object>> departIdlist) {
		this.departIdlist = departIdlist;
	}
	public List<Map<String, Object>> getProposaltypelist() {
		return proposaltypelist;
	}
	public void setProposaltypelist(List<Map<String, Object>> proposaltypelist) {
		this.proposaltypelist = proposaltypelist;
	}
	
	private String mode;
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPayrecType() {
		return payrecType;
	}
	public void setPayrecType(String payrecType) {
		this.payrecType = payrecType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	private List<Map<String,Object>> productList;
	private List<Map<String,Object>> userList;
	private List<Map<String,Object>> brokerList;
	private List<Map<String,Object>> ceddingCompanyList;
	private List<Map<String,Object>> reportList;
	private List<Map<String,Object>> deptName;
	private List<Map<String,Object>> yearList;
	private List<Map<String,Object>> reportsList;
	private List<Map<String,Object>> premiumaccperiod;
	private Map<String, Object> headerInfo;
	private List<Map<String,Object>> columnInfo;
	private List<ReportsBean> viewJurnalAll;
	private List<Map<String,Object>> AllocationReportList;
	
	
	public String getAcqcost() {
		return acqcost;
	}
	public void setAcqcost(String acqcost) {
		this.acqcost = acqcost;
	}
	public String getClaimPaid() {
		return claimPaid;
	}
	public void setClaimPaid(String claimPaid) {
		this.claimPaid = claimPaid;
	}
	public String getPremiumReserve() {
		return premiumReserve;
	}
	public void setPremiumReserve(String premiumReserve) {
		this.premiumReserve = premiumReserve;
	}
	public String getClaimReserve() {
		return claimReserve;
	}
	public void setClaimReserve(String claimReserve) {
		this.claimReserve = claimReserve;
	}
	public String getClaimOutstandingPL() {
		return claimOutstandingPL;
	}
	public void setClaimOutstandingPL(String claimOutstandingPL) {
		this.claimOutstandingPL = claimOutstandingPL;
	}
	public String getClaimOutstandingBS() {
		return claimOutstandingBS;
	}
	public void setClaimOutstandingBS(String claimOutstandingBS) {
		this.claimOutstandingBS = claimOutstandingBS;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public double getCreditLoss() {
		return creditLoss;
	}
	public void setCreditLoss(double creditLoss) {
		this.creditLoss = creditLoss;
	}
	public double getPercentDebit() {
		return percentDebit;
	}
	public void setPercentDebit(double percentDebit) {
		this.percentDebit = percentDebit;
	}
	public double getPercentCredit() {
		return percentCredit;
	}
	public void setPercentCredit(double percentCredit) {
		this.percentCredit = percentCredit;
	}
	public double getCreditTotal() {
		return creditTotal;
	}
	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}
	public double getDebitTotal() {
		return debitTotal;
	}
	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}
	public String getPercentDebit1() {
		return percentDebit1;
	}
	public void setPercentDebit1(String percentDebit1) {
		this.percentDebit1 = percentDebit1;
	}
	public String getPercentCredit1() {
		return percentCredit1;
	}
	public void setPercentCredit1(String percentCredit1) {
		this.percentCredit1 = percentCredit1;
	}
	public String getDebitTotal1() {
		return debitTotal1;
	}
	public void setDebitTotal1(String debitTotal1) {
		this.debitTotal1 = debitTotal1;
	}
	public String getCreditTotal1() {
		return creditTotal1;
	}
	public void setCreditTotal1(String creditTotal1) {
		this.creditTotal1 = creditTotal1;
	}
	public double getPremiumview() {
		return premiumview;
	}
	public void setPremiumview(double premiumview) {
		this.premiumview = premiumview;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public List<Map<String, Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
	public List<Map<String, Object>> getUserList() {
		return userList;
	}
	public void setUserList(List<Map<String, Object>> userList) {
		this.userList = userList;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Map<String, Object>> getCeddingCompanyList() {
		return ceddingCompanyList;
	}
	public void setCeddingCompanyList(List<Map<String, Object>> ceddingCompanyList) {
		this.ceddingCompanyList = ceddingCompanyList;
	}
	public List<Map<String, Object>> getReportList() {
		return reportList;
	}
	public void setReportList(List<Map<String, Object>> reportList) {
		this.reportList = reportList;
	}
	public void setDeptName(List<Map<String, Object>> deptName) {
		this.deptName = deptName;
	}
	public List<Map<String, Object>> getDeptName() {
		return deptName;
	}
	public String getAccper() {
		return accper;
	}
	public void setAccper(String accper) {
		this.accper = accper;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	public String getChangedYN() {
		return changedYN;
	}
	public void setChangedYN(String changedYN) {
		this.changedYN = changedYN;
	}
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String sNo) {
		SNo = sNo;
	}
	public String getAccount_Period() {
		return account_Period;
	}
	public void setAccount_Period(String accountPeriod) {
		account_Period = accountPeriod;
	}
	public List<Map<String, Object>> getYearList() {
		return yearList;
	}
	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStartDateLabelName() {
		return startDateLabelName;
	}
	public void setStartDateLabelName(String startDateLabelName) {
		this.startDateLabelName = startDateLabelName;
	}
	public String getEndDateLabelName() {
		return endDateLabelName;
	}
	public void setEndDateLabelName(String endDateLabelName) {
		this.endDateLabelName = endDateLabelName;
	}
	public List<Map<String, Object>> getReportsList() {
		return reportsList;
	}
	public void setReportsList(List<Map<String, Object>> reportsList) {
		this.reportsList = reportsList;
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
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
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
	public Map<String, Object> getHeaderInfo() {
		return headerInfo;
	}
	public void setHeaderInfo(Map<String, Object> headerInfo2) {
		this.headerInfo = headerInfo2;
	}
	public List<Map<String, Object>> getColumnInfo() {
		return columnInfo;
	}
	public void setColumnInfo(List<Map<String, Object>> columnInfo) {
		this.columnInfo = columnInfo;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String endDate) {
		end_date = endDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String startDate) {
		start_date = startDate;
	}
	public String getUwYear() {
		return uwYear;
	}
	public void setUwYear(String uwYear) {
		this.uwYear = uwYear;
	}
	public String getHeadMes() {
		return headMes;
	}
	public void setHeadMes(String headMes) {
		this.headMes = headMes;
	}
	public String getDateMes() {
		return dateMes;
	}
	public void setDateMes(String dateMes) {
		this.dateMes = dateMes;
	}
	public String getProMes() {
		return proMes;
	}
	public void setProMes(String proMes) {
		this.proMes = proMes;
	}
	public String getMovId() {
		return movId;
	}
	public void setMovId(String movId) {
		this.movId = movId;
	}
	public String getSpc() {
		return spc;
	}
	public void setSpc(String spc) {
		this.spc = spc;
	}
	public String getCurrencyname() {
		return currencyname;
	}
	public void setCurrencyname(String currencyname) {
		this.currencyname = currencyname;
	}
	public String getPreviousPremium() {
		return previousPremium;
	}
	public void setPreviousPremium(String previousPremium) {
		this.previousPremium = previousPremium;
	}
	public String getCurrentPremium() {
		return currentPremium;
	}
	public void setCurrentPremium(String currentPremium) {
		this.currentPremium = currentPremium;
	}
	public String getDiffPremium() {
		return diffPremium;
	}
	public void setDiffPremium(String diffPremium) {
		this.diffPremium = diffPremium;
	}
	public String getPreGrossPre() {
		return preGrossPre;
	}
	public void setPreGrossPre(String preGrossPre) {
		this.preGrossPre = preGrossPre;
	}
	public String getDiffGrossPre() {
		return diffGrossPre;
	}
	public void setDiffGrossPre(String diffGrossPre) {
		this.diffGrossPre = diffGrossPre;
	}
	public String getCurrentGrossAcq() {
		return currentGrossAcq;
	}
	public void setCurrentGrossAcq(String currentGrossAcq) {
		this.currentGrossAcq = currentGrossAcq;
	}
	public String getPreviousGrossAcq() {
		return previousGrossAcq;
	}
	public void setPreviousGrossAcq(String previousGrossAcq) {
		this.previousGrossAcq = previousGrossAcq;
	}
	public String getDiffGrossAcq() {
		return diffGrossAcq;
	}
	public void setDiffGrossAcq(String diffGrossAcq) {
		this.diffGrossAcq = diffGrossAcq;
	}
	public String getCurrentPremiumDepositRet() {
		return currentPremiumDepositRet;
	}
	public void setCurrentPremiumDepositRet(String currentPremiumDepositRet) {
		this.currentPremiumDepositRet = currentPremiumDepositRet;
	}
	public String getPreviousPremiumDepositRet() {
		return previousPremiumDepositRet;
	}
	public void setPreviousPremiumDepositRet(String previousPremiumDepositRet) {
		this.previousPremiumDepositRet = previousPremiumDepositRet;
	}
	public String getDiffPremiumDepositRet() {
		return diffPremiumDepositRet;
	}
	public void setDiffPremiumDepositRet(String diffPremiumDepositRet) {
		this.diffPremiumDepositRet = diffPremiumDepositRet;
	}
	public String getCurrentPremiumDepositRel() {
		return currentPremiumDepositRel;
	}
	public void setCurrentPremiumDepositRel(String currentPremiumDepositRel) {
		this.currentPremiumDepositRel = currentPremiumDepositRel;
	}
	public String getPreviousPremiumDepositRel() {
		return previousPremiumDepositRel;
	}
	public void setPreviousPremiumDepositRel(String previousPremiumDepositRel) {
		this.previousPremiumDepositRel = previousPremiumDepositRel;
	}
	public String getDiffPremiumDepositRel() {
		return diffPremiumDepositRel;
	}
	public void setDiffPremiumDepositRel(String diffPremiumDepositRel) {
		this.diffPremiumDepositRel = diffPremiumDepositRel;
	}
	public String getCurrentClaimDepositRet() {
		return currentClaimDepositRet;
	}
	public void setCurrentClaimDepositRet(String currentClaimDepositRet) {
		this.currentClaimDepositRet = currentClaimDepositRet;
	}
	public String getPreviousClaimDepositRet() {
		return previousClaimDepositRet;
	}
	public void setPreviousClaimDepositRet(String previousClaimDepositRet) {
		this.previousClaimDepositRet = previousClaimDepositRet;
	}
	public String getDiffClaimDepositRet() {
		return diffClaimDepositRet;
	}
	public void setDiffClaimDepositRet(String diffClaimDepositRet) {
		this.diffClaimDepositRet = diffClaimDepositRet;
	}
	public String getCurrentClaimDepositRel() {
		return currentClaimDepositRel;
	}
	public void setCurrentClaimDepositRel(String currentClaimDepositRel) {
		this.currentClaimDepositRel = currentClaimDepositRel;
	}
	public String getPreviousClaimDepositRel() {
		return previousClaimDepositRel;
	}
	public void setPreviousClaimDepositRel(String previousClaimDepositRel) {
		this.previousClaimDepositRel = previousClaimDepositRel;
	}
	public String getDiffClaimDepositRel() {
		return diffClaimDepositRel;
	}
	public void setDiffClaimDepositRel(String diffClaimDepositRel) {
		this.diffClaimDepositRel = diffClaimDepositRel;
	}
	public String getCurrentInterestDeposit() {
		return currentInterestDeposit;
	}
	public void setCurrentInterestDeposit(String currentInterestDeposit) {
		this.currentInterestDeposit = currentInterestDeposit;
	}
	public String getPreviousInterestDeposit() {
		return previousInterestDeposit;
	}
	public void setPreviousInterestDeposit(String previousInterestDeposit) {
		this.previousInterestDeposit = previousInterestDeposit;
	}
	public String getDiffInterestDeposit() {
		return diffInterestDeposit;
	}
	public void setDiffInterestDeposit(String diffInterestDeposit) {
		this.diffInterestDeposit = diffInterestDeposit;
	}
	public String getCurrentGrossClaimPaid() {
		return currentGrossClaimPaid;
	}
	public void setCurrentGrossClaimPaid(String currentGrossClaimPaid) {
		this.currentGrossClaimPaid = currentGrossClaimPaid;
	}
	public String getPreviousGrossClaimPaid() {
		return previousGrossClaimPaid;
	}
	public void setPreviousGrossClaimPaid(String previousGrossClaimPaid) {
		this.previousGrossClaimPaid = previousGrossClaimPaid;
	}
	public String getDiffGrossClaimPaid() {
		return diffGrossClaimPaid;
	}
	public void setDiffGrossClaimPaid(String diffGrossClaimPaid) {
		this.diffGrossClaimPaid = diffGrossClaimPaid;
	}
	public String getCurrentGrossPre() {
		return currentGrossPre;
	}
	public void setCurrentGrossPre(String currentGrossPre) {
		this.currentGrossPre = currentGrossPre;
	}
	public String getSumofPrm() {
		return sumofPrm;
	}
	public void setSumofPrm(String sumofPrm) {
		this.sumofPrm = sumofPrm;
	}
	public String getSumofPrmDC() {
		return sumofPrmDC;
	}
	public void setSumofPrmDC(String sumofPrmDC) {
		this.sumofPrmDC = sumofPrmDC;
	}
	public List<ReportsBean> getViewJurnalAll() {
		return viewJurnalAll;
	}
	public void setViewJurnalAll(List<ReportsBean> viewJurnalAll) {
		this.viewJurnalAll = viewJurnalAll;
	}
	public String getGrossPremiumOC() {
		return grossPremiumOC;
	}
	public void setGrossPremiumOC(String grossPremiumOC) {
		this.grossPremiumOC = grossPremiumOC;
	}
	public String getGrossPremiumDC() {
		return grossPremiumDC;
	}
	public void setGrossPremiumDC(String grossPremiumDC) {
		this.grossPremiumDC = grossPremiumDC;
	}
	public String getGrossAcqCostOC() {
		return grossAcqCostOC;
	}
	public void setGrossAcqCostOC(String grossAcqCostOC) {
		this.grossAcqCostOC = grossAcqCostOC;
	}
	public String getGrossAcqCostDC() {
		return grossAcqCostDC;
	}
	public void setGrossAcqCostDC(String grossAcqCostDC) {
		this.grossAcqCostDC = grossAcqCostDC;
	}
	public String getPremiumDepositRetainedDC() {
		return premiumDepositRetainedDC;
	}
	public void setPremiumDepositRetainedDC(String premiumDepositRetainedDC) {
		this.premiumDepositRetainedDC = premiumDepositRetainedDC;
	}
	public String getPremiumDepositRetainedOC() {
		return premiumDepositRetainedOC;
	}
	public void setPremiumDepositRetainedOC(String premiumDepositRetainedOC) {
		this.premiumDepositRetainedOC = premiumDepositRetainedOC;
	}
	public String getPremiumDepositReleasedOC() {
		return premiumDepositReleasedOC;
	}
	public void setPremiumDepositReleasedOC(String premiumDepositReleasedOC) {
		this.premiumDepositReleasedOC = premiumDepositReleasedOC;
	}
	public String getPremiumDepositReleasedDC() {
		return premiumDepositReleasedDC;
	}
	public void setPremiumDepositReleasedDC(String premiumDepositReleasedDC) {
		this.premiumDepositReleasedDC = premiumDepositReleasedDC;
	}
	public String getClaimDepositRetainedOC() {
		return claimDepositRetainedOC;
	}
	public void setClaimDepositRetainedOC(String claimDepositRetainedOC) {
		this.claimDepositRetainedOC = claimDepositRetainedOC;
	}
	public String getClaimDepositRetainedDC() {
		return claimDepositRetainedDC;
	}
	public void setClaimDepositRetainedDC(String claimDepositRetainedDC) {
		this.claimDepositRetainedDC = claimDepositRetainedDC;
	}
	public String getClaimDepositReleasedOC() {
		return claimDepositReleasedOC;
	}
	public void setClaimDepositReleasedOC(String claimDepositReleasedOC) {
		this.claimDepositReleasedOC = claimDepositReleasedOC;
	}
	public String getClaimDepositReleasedDC() {
		return claimDepositReleasedDC;
	}
	public void setClaimDepositReleasedDC(String claimDepositReleasedDC) {
		this.claimDepositReleasedDC = claimDepositReleasedDC;
	}
	public String getInterestOnDepositOC() {
		return interestOnDepositOC;
	}
	public void setInterestOnDepositOC(String interestOnDepositOC) {
		this.interestOnDepositOC = interestOnDepositOC;
	}
	public String getInterestOnDepositDC() {
		return interestOnDepositDC;
	}
	public void setInterestOnDepositDC(String interestOnDepositDC) {
		this.interestOnDepositDC = interestOnDepositDC;
	}
	public String getGrossClaimPaidOC() {
		return grossClaimPaidOC;
	}
	public void setGrossClaimPaidOC(String grossClaimPaidOC) {
		this.grossClaimPaidOC = grossClaimPaidOC;
	}
	public String getGrossClaimPaidDC() {
		return grossClaimPaidDC;
	}
	public void setGrossClaimPaidDC(String grossClaimPaidDC) {
		this.grossClaimPaidDC = grossClaimPaidDC;
	}
	public String getBrokerLedCtlOC() {
		return brokerLedCtlOC;
	}
	public void setBrokerLedCtlOC(String brokerLedCtlOC) {
		this.brokerLedCtlOC = brokerLedCtlOC;
	}
	public String getBrokerLedCtlDC() {
		return brokerLedCtlDC;
	}
	public void setBrokerLedCtlDC(String brokerLedCtlDC) {
		this.brokerLedCtlDC = brokerLedCtlDC;
	}
	public String getTotalCROC() {
		return totalCROC;
	}
	public void setTotalCROC(String totalCROC) {
		this.totalCROC = totalCROC;
	}
	public String getTotalDROC() {
		return totalDROC;
	}
	public void setTotalDROC(String totalDROC) {
		this.totalDROC = totalDROC;
	}
	public String getTotalCRDC() {
		return totalCRDC;
	}
	public void setTotalCRDC(String totalCRDC) {
		this.totalCRDC = totalCRDC;
	}
	public String getTotalDRDC() {
		return totalDRDC;
	}
	public void setTotalDRDC(String totalDRDC) {
		this.totalDRDC = totalDRDC;
	}
	public String getGpOC() {
		return gpOC;
	}
	public void setGpOC(String gpOC) {
		this.gpOC = gpOC;
	}
	public String getgAOC() {
		return gAOC;
	}
	public void setgAOC(String gAOC) {
		this.gAOC = gAOC;
	}
	public String getPdRetOC() {
		return pdRetOC;
	}
	public void setPdRetOC(String pdRetOC) {
		this.pdRetOC = pdRetOC;
	}
	public String getPdRelOC() {
		return pdRelOC;
	}
	public void setPdRelOC(String pdRelOC) {
		this.pdRelOC = pdRelOC;
	}
	public String getCdRelOC() {
		return cdRelOC;
	}
	public void setCdRelOC(String cdRelOC) {
		this.cdRelOC = cdRelOC;
	}
	public String getCdRetOC() {
		return cdRetOC;
	}
	public void setCdRetOC(String cdRetOC) {
		this.cdRetOC = cdRetOC;
	}
	public String getIntOC() {
		return intOC;
	}
	public void setIntOC(String intOC) {
		this.intOC = intOC;
	}
	public String getGcpOC() {
		return gcpOC;
	}
	public void setGcpOC(String gcpOC) {
		this.gcpOC = gcpOC;
	}
	public String getBlOC() {
		return blOC;
	}
	public void setBlOC(String blOC) {
		this.blOC = blOC;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOsLossupdateOC() {
		return osLossupdateOC;
	}
	public void setOsLossupdateOC(String osLossupdateOC) {
		this.osLossupdateOC = osLossupdateOC;
	}
	public String getOsLossupdateDC() {
		return osLossupdateDC;
	}
	public void setOsLossupdateDC(String osLossupdateDC) {
		this.osLossupdateDC = osLossupdateDC;
	}
	public List<Map<String, Object>> getPremiumaccperiod() {
		return premiumaccperiod;
	}
	public void setPremiumaccperiod(List<Map<String, Object>> premiumaccperiod) {
		this.premiumaccperiod = premiumaccperiod;
	}
	public String getOsLossMovement() {
		return osLossMovement;
	}
	public void setOsLossMovement(String osLossMovement) {
		this.osLossMovement = osLossMovement;
	}
	public String getOsLossMovementUSD() {
		return osLossMovementUSD;
	}
	public void setOsLossMovementUSD(String osLossMovementUSD) {
		this.osLossMovementUSD = osLossMovementUSD;
	}
	public String getBsMovement() {
		return bsMovement;
	}
	public void setBsMovement(String bsMovement) {
		this.bsMovement = bsMovement;
	}
	public String getBsMovementUSD() {
		return bsMovementUSD;
	}
	public void setBsMovementUSD(String bsMovementUSD) {
		this.bsMovementUSD = bsMovementUSD;
	}
	public String getJournelClaimPaid() {
		return journelClaimPaid;
	}
	public void setJournelClaimPaid(String journelClaimPaid) {
		this.journelClaimPaid = journelClaimPaid;
	}
	public String getJournelClaimPaidUSD() {
		return journelClaimPaidUSD;
	}
	public void setJournelClaimPaidUSD(String journelClaimPaidUSD) {
		this.journelClaimPaidUSD = journelClaimPaidUSD;
	}
	public String getDebtorsCreditors() {
		return debtorsCreditors;
	}
	public void setDebtorsCreditors(String debtorsCreditors) {
		this.debtorsCreditors = debtorsCreditors;
	}
	public String getDebtorsCreditorsUSD() {
		return debtorsCreditorsUSD;
	}
	public void setDebtorsCreditorsUSD(String debtorsCreditorsUSD) {
		this.debtorsCreditorsUSD = debtorsCreditorsUSD;
	}
	public String getOsLossMovementBs() {
		return osLossMovementBs;
	}
	public void setOsLossMovementBs(String osLossMovementBs) {
		this.osLossMovementBs = osLossMovementBs;
	}
	public String getOsLossMovementBsUSD() {
		return osLossMovementBsUSD;
	}
	public void setOsLossMovementBsUSD(String osLossMovementBsUSD) {
		this.osLossMovementBsUSD = osLossMovementBsUSD;
	}
	public String getOsLossPl() {
		return osLossPl;
	}
	public void setOsLossPl(String osLossPl) {
		this.osLossPl = osLossPl;
	}
	public String getOsLossPlUSD() {
		return osLossPlUSD;
	}
	public void setOsLossPlUSD(String osLossPlUSD) {
		this.osLossPlUSD = osLossPlUSD;
	}
	public String getOsLM() {
		return osLM;
	}
	public void setOsLM(String osLM) {
		this.osLM = osLM;
	}
	public String getBsM() {
		return bsM;
	}
	public void setBsM(String bsM) {
		this.bsM = bsM;
	}
	public String getjCP() {
		return jCP;
	}
	public void setjCP(String jCP) {
		this.jCP = jCP;
	}
	public String getdC() {
		return dC;
	}
	public void setdC(String dC) {
		this.dC = dC;
	}
	public String getOsLMB() {
		return osLMB;
	}
	public void setOsLMB(String osLMB) {
		this.osLMB = osLMB;
	}
	public String getOsLP() {
		return osLP;
	}
	public void setOsLP(String osLP) {
		this.osLP = osLP;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getBkdUprD() {
		return bkdUprD;
	}
	public void setBkdUprD(String bkdUprD) {
		this.bkdUprD = bkdUprD;
	}
	public String getBkdDefAcqCostC() {
		return bkdDefAcqCostC;
	}
	public void setBkdDefAcqCostC(String bkdDefAcqCostC) {
		this.bkdDefAcqCostC = bkdDefAcqCostC;
	}
	public String getInwrdBkdUprBsC() {
		return inwrdBkdUprBsC;
	}
	public void setInwrdBkdUprBsC(String inwrdBkdUprBsC) {
		this.inwrdBkdUprBsC = inwrdBkdUprBsC;
	}
	public String getInwrdBkdDefAcqCostBsD() {
		return inwrdBkdDefAcqCostBsD;
	}
	public void setInwrdBkdDefAcqCostBsD(String inwrdBkdDefAcqCostBsD) {
		this.inwrdBkdDefAcqCostBsD = inwrdBkdDefAcqCostBsD;
	}
	public String getPiplinUprC() {
		return piplinUprC;
	}
	public void setPiplinUprC(String piplinUprC) {
		this.piplinUprC = piplinUprC;
	}
	public String getPiplinAcqCostD() {
		return piplinAcqCostD;
	}
	public void setPiplinAcqCostD(String piplinAcqCostD) {
		this.piplinAcqCostD = piplinAcqCostD;
	}
	public String getInwrdPiplinUprBSD() {
		return inwrdPiplinUprBSD;
	}
	public void setInwrdPiplinUprBSD(String inwrdPiplinUprBSD) {
		this.inwrdPiplinUprBSD = inwrdPiplinUprBSD;
	}
	public String getInwrdPiplinDefAcqCostBsC() {
		return inwrdPiplinDefAcqCostBsC;
	}
	public void setInwrdPiplinDefAcqCostBsC(String inwrdPiplinDefAcqCostBsC) {
		this.inwrdPiplinDefAcqCostBsC = inwrdPiplinDefAcqCostBsC;
	}
	public String getGrsPremiumC() {
		return grsPremiumC;
	}
	public void setGrsPremiumC(String grsPremiumC) {
		this.grsPremiumC = grsPremiumC;
	}
	public String getGrsPiplinPremiumD() {
		return grsPiplinPremiumD;
	}
	public void setGrsPiplinPremiumD(String grsPiplinPremiumD) {
		this.grsPiplinPremiumD = grsPiplinPremiumD;
	}
	public String getGrsAcqCostD() {
		return grsAcqCostD;
	}
	public void setGrsAcqCostD(String grsAcqCostD) {
		this.grsAcqCostD = grsAcqCostD;
	}
	public String getGrsPiplinAcqCostC() {
		return grsPiplinAcqCostC;
	}
	public void setGrsPiplinAcqCostC(String grsPiplinAcqCostC) {
		this.grsPiplinAcqCostC = grsPiplinAcqCostC;
	}
	public String getPreResRetainD() {
		return preResRetainD;
	}
	public void setPreResRetainD(String preResRetainD) {
		this.preResRetainD = preResRetainD;
	}
	public String getPreResReleaseC() {
		return preResReleaseC;
	}
	public void setPreResReleaseC(String preResReleaseC) {
		this.preResReleaseC = preResReleaseC;
	}
	public String getLossResRetainD() {
		return lossResRetainD;
	}
	public void setLossResRetainD(String lossResRetainD) {
		this.lossResRetainD = lossResRetainD;
	}
	public String getLossResReleaseC() {
		return lossResReleaseC;
	}
	public void setLossResReleaseC(String lossResReleaseC) {
		this.lossResReleaseC = lossResReleaseC;
	}
	public String getBkdPreInterestC() {
		return bkdPreInterestC;
	}
	public void setBkdPreInterestC(String bkdPreInterestC) {
		this.bkdPreInterestC = bkdPreInterestC;
	}
	public String getGrsClaimsPaidD() {
		return grsClaimsPaidD;
	}
	public void setGrsClaimsPaidD(String grsClaimsPaidD) {
		this.grsClaimsPaidD = grsClaimsPaidD;
	}
	public String getBusiPartInwrdNetAcD() {
		return busiPartInwrdNetAcD;
	}
	public void setBusiPartInwrdNetAcD(String busiPartInwrdNetAcD) {
		this.busiPartInwrdNetAcD = busiPartInwrdNetAcD;
	}
	public String getPiplinNetAcC() {
		return piplinNetAcC;
	}
	public void setPiplinNetAcC(String piplinNetAcC) {
		this.piplinNetAcC = piplinNetAcC;
	}
	public String getPiplinPremiumC() {
		return piplinPremiumC;
	}
	public void setPiplinPremiumC(String piplinPremiumC) {
		this.piplinPremiumC = piplinPremiumC;
	}
	public String getPiplinNetAcD() {
		return piplinNetAcD;
	}
	public void setPiplinNetAcD(String piplinNetAcD) {
		this.piplinNetAcD = piplinNetAcD;
	}
	public String getInwrdPiplinUnearnPremiumD() {
		return inwrdPiplinUnearnPremiumD;
	}
	public void setInwrdPiplinUnearnPremiumD(String inwrdPiplinUnearnPremiumD) {
		this.inwrdPiplinUnearnPremiumD = inwrdPiplinUnearnPremiumD;
	}
	public String getInwrdPiplinDefAcqCostC() {
		return inwrdPiplinDefAcqCostC;
	}
	public void setInwrdPiplinDefAcqCostC(String inwrdPiplinDefAcqCostC) {
		this.inwrdPiplinDefAcqCostC = inwrdPiplinDefAcqCostC;
	}
	public String getInwrdPiplinUprBsC() {
		return inwrdPiplinUprBsC;
	}
	public void setInwrdPiplinUprBsC(String inwrdPiplinUprBsC) {
		this.inwrdPiplinUprBsC = inwrdPiplinUprBsC;
	}
	public String getInwrdPiplinDefAcqCostBsD() {
		return inwrdPiplinDefAcqCostBsD;
	}
	public void setInwrdPiplinDefAcqCostBsD(String inwrdPiplinDefAcqCostBsD) {
		this.inwrdPiplinDefAcqCostBsD = inwrdPiplinDefAcqCostBsD;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setSpcName(String spcName) {
		this.spcName = spcName;
	}
	public String getSpcName() {
		return spcName;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProduct() {
		return product;
	}
	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}
	public String getMovementId() {
		return movementId;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCount() {
		return count;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getBroker() {
		return broker;
	}
	public void setCedingCompany(String cedingCompany) {
		this.cedingCompany = cedingCompany;
	}
	public String getCedingCompany() {
		return cedingCompany;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setAllocateStatus(String allocateStatus) {
		this.allocateStatus = allocateStatus;
	}
	public String getAllocateStatus() {
		return allocateStatus;
	}
	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}
	public String getAllocationNo() {
		return allocationNo;
	}
	public void setAllocationDate(String allocationDate) {
		this.allocationDate = allocationDate;
	}
	public String getAllocationDate() {
		return allocationDate;
	}
	public void setPaymentReceiptNo(String paymentReceiptNo) {
		this.paymentReceiptNo = paymentReceiptNo;
	}
	public String getPaymentReceiptNo() {
		return paymentReceiptNo;
	}
	public void setReceiptPayAmtOC(String receiptPayAmtOC) {
		this.receiptPayAmtOC = receiptPayAmtOC;
	}
	public String getReceiptPayAmtOC() {
		return receiptPayAmtOC;
	}
	public void setReceiptPayAmtUGX(String receiptPayAmtUGX) {
		this.receiptPayAmtUGX = receiptPayAmtUGX;
	}
	public String getReceiptPayAmtUGX() {
		return receiptPayAmtUGX;
	}
	public void setAllocateAmtOC(String allocateAmtOC) {
		this.allocateAmtOC = allocateAmtOC;
	}
	public String getAllocateAmtOC() {
		return allocateAmtOC;
	}
	public void setAllocateAmtUGX(String allocateAmtUGX) {
		this.allocateAmtUGX = allocateAmtUGX;
	}
	public String getAllocateAmtUGX() {
		return allocateAmtUGX;
	}
	public void setAllocationReportList(List<Map<String,Object>> allocationReportList) {
		AllocationReportList = allocationReportList;
	}
	public List<Map<String,Object>> getAllocationReportList() {
		return AllocationReportList;
	}
	public void setShowAllFields(String showAllFields) {
		this.showAllFields = showAllFields;
	}
	public String getShowAllFields() {
		return showAllFields;
	}
	public String getTreatyMainClass() {
		return treatyMainClass;
	}
	public void setTreatyMainClass(String treatyMainClass) {
		this.treatyMainClass = treatyMainClass;
	}
	public String getTreatyType() {
		return treatyType;
	}
	public void setTreatyType(String treatyType) {
		this.treatyType = treatyType;
	}
	public String getOpenperiod() {
		return openperiod;
	}
	public void setOpenperiod(String openperiod) {
		this.openperiod = openperiod;
	}
	public String getDebFrom() {
		return debFrom;
	}
	public void setDebFrom(String debFrom) {
		this.debFrom = debFrom;
	}
	public String getDebTo() {
		return debTo;
	}
	public void setDebTo(String debTo) {
		this.debTo = debTo;
	}
	public String getDebFrom1() {
		return debFrom1;
	}
	public void setDebFrom1(String debFrom1) {
		this.debFrom1 = debFrom1;
	}
	public String getDebTo1() {
		return debTo1;
	}
	public void setDebTo1(String debTo1) {
		this.debTo1 = debTo1;
	}
	public String getDebFrom2() {
		return debFrom2;
	}
	public void setDebFrom2(String debFrom2) {
		this.debFrom2 = debFrom2;
	}
	public String getDebTo2() {
		return debTo2;
	}
	public void setDebTo2(String debTo2) {
		this.debTo2 = debTo2;
	}
	public String getDebFrom3() {
		return debFrom3;
	}
	public void setDebFrom3(String debFrom3) {
		this.debFrom3 = debFrom3;
	}
	public String getDebTo3() {
		return debTo3;
	}
	public void setDebTo3(String debTo3) {
		this.debTo3 = debTo3;
	}
	public String getDebFrom4() {
		return debFrom4;
	}
	public void setDebFrom4(String debFrom4) {
		this.debFrom4 = debFrom4;
	}
	public String getDebTo4() {
		return debTo4;
	}
	public void setDebTo4(String debTo4) {
		this.debTo4 = debTo4;
	}
	public String getDebFrom5() {
		return debFrom5;
	}
	public void setDebFrom5(String debFrom5) {
		this.debFrom5 = debFrom5;
	}
	public String getDebTo5() {
		return debTo5;
	}
	public void setDebTo5(String debTo5) {
		this.debTo5 = debTo5;
	}
	public String getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}
	public String getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}
	public String getUwFrom() {
		return uwFrom;
	}
	public void setUwFrom(String uwFrom) {
		this.uwFrom = uwFrom;
	}
	public String getUwTo() {
		return uwTo;
	}
	public void setUwTo(String uwTo) {
		this.uwTo = uwTo;
	}
	public String getLayerNo() {
		return layerNo;
	}
	public void setLayerNo(String layerNo) {
		this.layerNo = layerNo;
	}
	public String getSaperatePRCLYN() {
		return saperatePRCLYN;
	}
	public void setSaperatePRCLYN(String saperatePRCLYN) {
		this.saperatePRCLYN = saperatePRCLYN;
	}
	public String getJournalType() {
		return journalType;
	}
	public void setJournalType(String journalType) {
		this.journalType = journalType;
	}
		
}
