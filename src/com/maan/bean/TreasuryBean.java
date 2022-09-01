package com.maan.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class TreasuryBean {
	private String name;
	private String cedingCo;
	private String broker;
	private String trans_month;
	private String trans_year;
	private String currency;
	private String exrate;
	private String amount_credit;
	private String amount_debit;
	private String tr_date;
	private String remarks;
	private String pay_res;
	private String type;
	private String proid;
	private String mode;
	private String pay_rec_no;
	private String flag;
	private String dept_no;
	private String serial_no;
	private String login_id;
	private String cedingid;
	private String brokerid;
	// private String branchCode;
	private String difftype;
	private String policyno = "";
	private String allocateddate = "";
	private String payamount;
	private String contractno;
	private String layerno;
	private String productname;
	private String inceptiobdate;
	private String expirtdate;
	private String netdue;
	private String paymentamount;
	private String viewreport;
	private String transactionno;
	private String exchangerate = "";
	private String totalexchaamount = "";
	private String selcurrency;
	private String currencyValue = "";
	private String currencyName = "";
	private String alloccurrencyid = "";
	private String brokername = "";
	private String accountDate = "";
	private String accPremium = "";
	private String editShowStatus;
	private String reversalDate;
	private String reversedShowStatus;
	private String reversedAmount;
	private String searchContractNo;
	private String searchType;
	private String searchBusinessType;
	private String allocated;
	private String utilized;
	private String notUtilized;
	private String status;
	private String accClaim = "";
	private String checkPC = "";
	private String checkYN = "";
	private String accAmount = "";
	private String receiptBankId;
	private String receiptBankName;
	private String diffAmount;
	private String totAmount;
	private String allTillDate;
	private String setExcRate;
	private String conRecCur;
	private String allocType;
	private String baseCurrencyAmount;
	private String transactionType;
	private String bankCharges;
	private String netAmt;
	private String bank;
	private String unAllocatedCash;
	private String totalDR;
	private String totalCR;
	private String partToShow;
	private String selects;
	private String hidSelCurrencey;
	private String unUtilizedAmt;
	private String count;
	private String hideRowCnt;
	private String txtTotalAmt;
	private String txtDiffAmt;
	private String totalConRecCur;
	private String hidTotalAmt;
	private String receiptamt;
	private String incepDate;
	private String orginalCurrency;
	private String dropDown;
	private String amend_date;
	private String withHoldingTaxOC;
	private String withHoldingTaxDC;
	private String maxDate;
	private String preamendDate;
	private String shortname;
	private String bankchargeDC;
	private String netamtDC;
	private String allocationNo;
	private String recpayOpenYN;
	private String opstartDate;
	private String opendDate;
	private String adjustMentType;
	private String allocateType;
	private String transactionTypeTest;
	private String currencyId;
	private String menuStatus;
	private String processType;
	private String hideprocessType;
	private String retroType;
	private String sign;
	private String subClass;
	private String selectTransactionNo;
	private String selreceivePayAmount;
	private String proposalNo;
	private String paymentNoSearch;
	private String brokerNameSearch;
	private String companyNameSearch;
	private String remarksSearch;
	private String fullSearch;
	private String currencySearch;
	private String premiumLavy;
	
	
	public String getPremiumLavy() {
		return premiumLavy;
	}
	public void setPremiumLavy(String premiumLavy) {
		this.premiumLavy = premiumLavy;
	}
	public String getSelectTransactionNo() {
		return selectTransactionNo;
	}
	public void setSelectTransactionNo(String selectTransactionNo) {
		this.selectTransactionNo = selectTransactionNo;
	}
	public String getSelreceivePayAmount() {
		return selreceivePayAmount;
	}
	public void setSelreceivePayAmount(String selreceivePayAmount) {
		this.selreceivePayAmount = selreceivePayAmount;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRetroType() {
		return retroType;
	}
	public void setRetroType(String retroType) {
		this.retroType = retroType;
	}
	public String getHideprocessType() {
		return hideprocessType;
	}
	public void setHideprocessType(String hideprocessType) {
		this.hideprocessType = hideprocessType;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	
	private List<Map<String,Object>> bankCurrencyList;

	
	
	
	public List<Map<String, Object>> getBankCurrencyList() {
		return bankCurrencyList;
	}

	public void setBankCurrencyList(List<Map<String, Object>> bankCurrencyList) {
		this.bankCurrencyList = bankCurrencyList;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getTransactionTypeTest() {
		return transactionTypeTest;
	}

	public void setTransactionTypeTest(String transactionTypeTest) {
		this.transactionTypeTest = transactionTypeTest;
	}

	public String getAllocateType() {
		return allocateType;
	}

	public void setAllocateType(String allocateType) {
		this.allocateType = allocateType;
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

	public String getRecpayOpenYN() {
		return recpayOpenYN;
	}

	public void setRecpayOpenYN(String recpayOpenYN) {
		this.recpayOpenYN = recpayOpenYN;
	}

	public String getAllocationNo() {
		return allocationNo;
	}

	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}

	public String getNetamtDC() {
		return netamtDC;
	}

	public void setNetamtDC(String netamtDC) {
		this.netamtDC = netamtDC;
	}

	public String getBankchargeDC() {
		return bankchargeDC;
	}

	public void setBankchargeDC(String bankchargeDC) {
		this.bankchargeDC = bankchargeDC;
	}

	private String tottotal;

	public String getTottotal() {
		return tottotal;
	}

	public void setTottotal(String tottotal) {
		this.tottotal = tottotal;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getPreamendDate() {
		return preamendDate;
	}

	public void setPreamendDate(String preamendDate) {
		this.preamendDate = preamendDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	private List<String> cedingCompanyValList;
	private List<String> exachangeValList;
	private List<String> payamountValList;
	private List<String> rowamountValList;
	private List<String> recNoValList;
	private List<String> setExcRateValList;
	private List<String> conRecCurValList;
	private List<String> paymentList;
	private List<String> recNoList;
	private List<String> previousValue;

	private List<TreasuryBean> detailsList;
	private List<TreasuryBean> allocatedStatusList;
	private List<TreasuryBean> allocateDetailsList;
	private List<TreasuryBean> allocateViewList;
	private List<TreasuryBean> allocatedList;
	private List<TreasuryBean> revertedList;
	private List<TreasuryBean> transactionContractList;
	private List<TreasuryBean> ReversalInfo;
	private List<Map<String, Object>> allocateCurrencyList;
	private List<Map<String, Object>> CeddingCompanyList;
	private List<Map<String, Object>> brokerList;
	private List<Map<String, Object>> bankMasterList;
	private List<Map<String, Object>> transactionTypeList;
	private List<Map<String, Object>> orginalCurrencyList;
	private List<Map<String, Object>> treasuryJournalView;
	private List<TreasuryBean> reversedetailsList;
	private List<TreasuryBean> tresauryRecPaytailsList;

	public List<TreasuryBean> getTresauryRecPaytailsList() {
		return tresauryRecPaytailsList;
	}

	public void setTresauryRecPaytailsList(
			List<TreasuryBean> tresauryRecPaytailsList) {
		this.tresauryRecPaytailsList = tresauryRecPaytailsList;
	}

	public List<TreasuryBean> getReversedetailsList() {
		return reversedetailsList;
	}

	public void setReversedetailsList(List<TreasuryBean> reversedetailsList) {
		this.reversedetailsList = reversedetailsList;
	}

	public List<Map<String, Object>> getTreasuryJournalView() {
		return treasuryJournalView;
	}

	public void setTreasuryJournalView(
			List<Map<String, Object>> treasuryJournalView) {
		this.treasuryJournalView = treasuryJournalView;
	}

	// Pagination
	private String pagecount;
	private String checks;
	private String previouspageno;
	private String expg;
	private String oneTime = "1";
	private String pageLength;
	private String totalreccount;
	private String paginationsize = "10";
	private String lpage;
	private String cpage;
	private String totalpages;
	private String currentpageno;
	private String startrownum = "1";
	private String endrownum = "10";
	private String currentcheckeditems;
	private String checkedcount;
	private String[] select;
	private String cedingCompanyName;

	private List<String> transactionNos;
	private List<String> receivePayAmounts;
	private List<String> chkbox;
	//private List<String> sign;
	private List<String> payAmounts;
	
	
	private String searchValue;
	private String searchBy;
	private String paymentDate;
	private String allocationStatus;
	private String receiptcheck;
	private String cancelType;
	private String difftype1;
	private String convertedDiffAmount;
	private String txtDiffPer;
	private String convDiffAmount;
	
	
	
	public List<String> getPayAmounts() {
		return payAmounts;
	}
	public void setPayAmounts(List<String> payAmounts) {
		this.payAmounts = payAmounts;
	}
	/*public List<String> getSign() {
		return sign;
	}
	public void setSign(List<String> sign) {
		this.sign = sign;
	}*/
	public String getConvDiffAmount() {
		return convDiffAmount;
	}

	public void setConvDiffAmount(String convDiffAmount) {
		this.convDiffAmount = convDiffAmount;
	}

	public String getTxtDiffPer() {
		return txtDiffPer;
	}

	public void setTxtDiffPer(String txtDiffPer) {
		this.txtDiffPer = txtDiffPer;
	}

	public String getConvertedDiffAmount() {
		return convertedDiffAmount;
	}

	public void setConvertedDiffAmount(String convertedDiffAmount) {
		this.convertedDiffAmount = convertedDiffAmount;
	}

	public String getDifftype1() {
		return difftype1;
	}

	public void setDifftype1(String difftype1) {
		this.difftype1 = difftype1;
	}

	public String getReceiptcheck() {
		return receiptcheck;
	}

	public void setReceiptcheck(String receiptcheck) {
		this.receiptcheck = receiptcheck;
	}

	public String getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
	}

	public String getTotalCR() {
		return totalCR;
	}

	public void setTotalCR(String totalCR) {
		this.totalCR = totalCR;
	}

	public String getTotalDR() {
		return totalDR;
	}

	public void setTotalDR(String totalDR) {
		this.totalDR = totalDR;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getUnAllocatedCash() {
		return unAllocatedCash;
	}

	public void setUnAllocatedCash(String unAllocatedCash) {
		this.unAllocatedCash = unAllocatedCash;
	}

	/**
	 * @return the netAmt
	 */
	public String getNetAmt() {
		return netAmt;
	}

	/**
	 * @param netAmt
	 *            the netAmt to set
	 */
	public void setNetAmt(String netAmt) {
		this.netAmt = netAmt;
	}

	/**
	 * @return the bankCharges
	 */
	public String getBankCharges() {
		return bankCharges;
	}

	/**
	 * @param bankCharges
	 *            the bankCharges to set
	 */
	public void setBankCharges(String bankCharges) {
		this.bankCharges = bankCharges;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getBaseCurrencyAmount() {
		return baseCurrencyAmount;
	}

	public void setBaseCurrencyAmount(String baseCurrencyAmount) {
		this.baseCurrencyAmount = baseCurrencyAmount;
	}

	public String getAllocType() {
		return allocType;
	}

	public void setAllocType(String allocType) {
		this.allocType = allocType;
	}

	public String getAllocated() {
		return allocated;
	}

	public void setAllocated(String allocated) {
		this.allocated = allocated;
	}

	public String getUtilized() {
		return utilized;
	}

	public void setUtilized(String utilized) {
		this.utilized = utilized;
	}

	public String getNotUtilized() {
		return notUtilized;
	}

	public void setNotUtilized(String notUtilized) {
		this.notUtilized = notUtilized;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchContractNo() {
		return searchContractNo;
	}

	public void setSearchContractNo(String searchContractNo) {
		this.searchContractNo = searchContractNo;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchBusinessType() {
		return searchBusinessType;
	}

	public void setSearchBusinessType(String searchBusinessType) {
		this.searchBusinessType = searchBusinessType;
	}

	public String getReversedAmount() {
		return reversedAmount;
	}

	public void setReversedAmount(String reversedAmount) {
		this.reversedAmount = reversedAmount;
	}

	public String getReversedShowStatus() {
		return reversedShowStatus;
	}

	public void setReversedShowStatus(String reversedShowStatus) {
		this.reversedShowStatus = reversedShowStatus;
	}

	public void setReversalDate(String reversalDate) {
		this.reversalDate = reversalDate;
	}

	public String getReversalDate() {
		return reversalDate;
	}

	public String getEditShowStatus() {
		return editShowStatus;
	}

	public void setEditShowStatus(String editShowStatus) {
		this.editShowStatus = editShowStatus;
	}

	public String getReceiptBankId() {
		return receiptBankId;
	}

	public void setReceiptBankId(String receiptBankId) {
		this.receiptBankId = receiptBankId;
	}

	public String getReceiptBankName() {
		return receiptBankName;
	}

	public void setReceiptBankName(String receiptBankName) {
		this.receiptBankName = receiptBankName;
	}

	public String getSetExcRate() {
		return setExcRate;
	}

	public void setSetExcRate(String setExcRate) {
		this.setExcRate = setExcRate;
	}

	public String getConRecCur() {
		return conRecCur;
	}

	public void setConRecCur(String conRecCur) {
		this.conRecCur = conRecCur;
	}

	public String getAllTillDate() {
		return allTillDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	private String transType;

	public void setAllTillDate(String allTillDate) {
		this.allTillDate = allTillDate;
	}

	public String getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(String totAmount) {
		this.totAmount = totAmount;
	}

	public String getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(String diffAmount) {
		this.diffAmount = diffAmount;
	}

	public String getAccAmount() {
		return accAmount;
	}

	public void setAccAmount(String accAmount) {
		this.accAmount = accAmount;
	}

	public String getCheckYN() {
		return checkYN;
	}

	public void setCheckYN(String checkYN) {
		this.checkYN = checkYN;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public String getCheckPC() {
		return checkPC;
	}

	public void setCheckPC(String checkPC) {
		this.checkPC = checkPC;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getPolicyno() {
		return policyno;
	}

	public String getAccPremium() {
		return accPremium;
	}

	public void setAccPremium(String accPremium) {
		this.accPremium = accPremium;
	}

	public String getAccClaim() {
		return accClaim;
	}

	public void setAccClaim(String accClaim) {
		this.accClaim = accClaim;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public String getAllocateddate() {
		return allocateddate;
	}

	public void setAllocateddate(String allocateddate) {
		this.allocateddate = allocateddate;
	}

	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public String getDifftype() {
		return difftype;
	}

	public void setDifftype(String difftype) {
		this.difftype = difftype;
	}

	public String getAlloccurrencyid() {
		return alloccurrencyid;
	}

	public void setAlloccurrencyid(String alloccurrencyid) {
		this.alloccurrencyid = alloccurrencyid;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getSelcurrency() {
		return selcurrency;
	}

	public void setSelcurrency(String selcurrency) {
		this.selcurrency = selcurrency;
	}

	public String getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(String exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getTotalexchaamount() {
		return totalexchaamount;
	}

	public void setTotalexchaamount(String totalexchaamount) {
		this.totalexchaamount = totalexchaamount;
	}

	/*
	 * public String getBranchCode() { return branchCode; } public void
	 * setBranchCode(String branchCode) { this.branchCode = branchCode; }
	 */
	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getTransactionno() {
		return transactionno;
	}

	public void setTransactionno(String transactionno) {
		this.transactionno = transactionno;
	}

	public String getViewreport() {
		return viewreport;
	}

	public void setViewreport(String viewreport) {
		this.viewreport = viewreport;
	}

	public String getNetdue() {
		return netdue;
	}

	public void setNetdue(String netdue) {
		this.netdue = netdue;
	}

	public String getPaymentamount() {
		return paymentamount;
	}

	public void setPaymentamount(String paymentamount) {
		this.paymentamount = paymentamount;
	}

	public String getExpirtdate() {
		return expirtdate;
	}

	public void setExpirtdate(String expirtdate) {
		this.expirtdate = expirtdate;
	}

	public String getInceptiobdate() {
		return inceptiobdate;
	}

	public void setInceptiobdate(String inceptiobdate) {
		this.inceptiobdate = inceptiobdate;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getLayerno() {
		return layerno;
	}

	public void setLayerno(String layerno) {
		this.layerno = layerno;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getBrokerid() {
		return brokerid;
	}

	public void setBrokerid(String brokerid) {
		this.brokerid = brokerid;
	}

	public String getCedingid() {
		return cedingid;
	}

	public void setCedingid(String cedingid) {
		this.cedingid = cedingid;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String loginId) {
		login_id = loginId;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serialNo) {
		serial_no = serialNo;
	}

	public String getPay_res() {
		return pay_res;
	}

	public void setPay_res(String payRes) {
		pay_res = payRes;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String deptNo) {
		dept_no = deptNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPay_rec_no() {
		return pay_rec_no;
	}

	public void setPay_rec_no(String payRecNo) {
		pay_rec_no = payRecNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExrate() {
		return exrate;
	}

	public void setExrate(String exrate) {
		this.exrate = exrate;
	}

	public String getAmount_credit() {
		return amount_credit;
	}

	public void setAmount_credit(String amountCredit) {
		amount_credit = amountCredit;
	}

	public String getAmount_debit() {
		return amount_debit;
	}

	public void setAmount_debit(String amountDebit) {
		amount_debit = amountDebit;
	}

	public String getTr_date() {
		return tr_date;
	}

	public void setTr_date(String trDate) {
		tr_date = trDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCedingCo() {
		return cedingCo;
	}

	public void setCedingCo(String cedingCo) {
		this.cedingCo = cedingCo;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getTrans_month() {
		return trans_month;
	}

	public void setTrans_month(String transMonth) {
		trans_month = transMonth;
	}

	public String getTrans_year() {
		return trans_year;
	}

	public void setTrans_year(String transYear) {
		trans_year = transYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreasuryBean> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<TreasuryBean> detailsList) {
		this.detailsList = detailsList;
	}

	public List<Map<String, Object>> getAllocateCurrencyList() {
		return allocateCurrencyList;
	}

	public void setAllocateCurrencyList(
			List<Map<String, Object>> allocateCurrencyList) {
		this.allocateCurrencyList = allocateCurrencyList;
	}

	public String getPartToShow() {
		return partToShow;
	}

	public void setPartToShow(String partToShow) {
		this.partToShow = partToShow;
	}

	public List<Map<String, Object>> getCeddingCompanyList() {
		return CeddingCompanyList;
	}

	public void setCeddingCompanyList(
			List<Map<String, Object>> ceddingCompanyList) {
		CeddingCompanyList = ceddingCompanyList;
	}

	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}

	public List<Map<String, Object>> getBankMasterList() {
		return bankMasterList;
	}

	public void setBankMasterList(List<Map<String, Object>> bankMasterList) {
		this.bankMasterList = bankMasterList;
	}

	public List<Map<String, Object>> getTransactionTypeList() {
		return transactionTypeList;
	}

	public void setTransactionTypeList(
			List<Map<String, Object>> transactionTypeList) {
		this.transactionTypeList = transactionTypeList;
	}

	public List<Map<String, Object>> getOrginalCurrencyList() {
		return orginalCurrencyList;
	}

	public void setOrginalCurrencyList(
			List<Map<String, Object>> orginalCurrencyList) {
		this.orginalCurrencyList = orginalCurrencyList;
	}

	public List<TreasuryBean> getAllocatedStatusList() {
		return allocatedStatusList;
	}

	public void setAllocatedStatusList(List<TreasuryBean> allocatedStatusList) {
		this.allocatedStatusList = allocatedStatusList;
	}

	public List<TreasuryBean> getAllocateDetailsList() {
		return allocateDetailsList;
	}

	public void setAllocateDetailsList(List<TreasuryBean> allocateDetailsList) {
		this.allocateDetailsList = allocateDetailsList;
	}

	public List<TreasuryBean> getAllocateViewList() {
		return allocateViewList;
	}

	public void setAllocateViewList(List<TreasuryBean> allocateViewList) {
		this.allocateViewList = allocateViewList;
	}

	public List<TreasuryBean> getAllocatedList() {
		return allocatedList;
	}

	public void setAllocatedList(List<TreasuryBean> allocatedList) {
		this.allocatedList = allocatedList;
	}

	public List<TreasuryBean> getRevertedList() {
		return revertedList;
	}

	public void setRevertedList(List<TreasuryBean> revertedList) {
		this.revertedList = revertedList;
	}

	public String getSelects() {
		return selects;
	}

	public void setSelects(String selects) {
		this.selects = selects;
	}

	public String getHidSelCurrencey() {
		return hidSelCurrencey;
	}

	public void setHidSelCurrencey(String hidSelCurrencey) {
		this.hidSelCurrencey = hidSelCurrencey;
	}

	public List<TreasuryBean> getTransactionContractList() {
		return transactionContractList;
	}

	public void setTransactionContractList(
			List<TreasuryBean> transactionContractList) {
		this.transactionContractList = transactionContractList;
	}

	public String getUnUtilizedAmt() {
		return unUtilizedAmt;
	}

	public void setUnUtilizedAmt(String unUtilizedAmt) {
		this.unUtilizedAmt = unUtilizedAmt;
	}

	public String getPagecount() {
		return StringUtils.isBlank(pagecount) ? "0" : pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public String getChecks() {
		return checks;
	}

	public void setChecks(String checks) {
		this.checks = checks;
	}

	public String getPreviouspageno() {
		return StringUtils.isBlank(previouspageno) ? "1" : previouspageno;
	}

	public void setPreviouspageno(String previouspageno) {
		this.previouspageno = previouspageno;
	}

	public String getExpg() {
		return expg;
	}

	public void setExpg(String expg) {
		this.expg = expg;
	}

	public String getOneTime() {
		return oneTime;
	}

	public void setOneTime(String oneTime) {
		this.oneTime = oneTime;
	}

	public String getPageLength() {
		return StringUtils.isBlank(pageLength) ? "0" : pageLength;
	}

	public void setPageLength(String pageLength) {
		this.pageLength = pageLength;
	}

	public String getTotalreccount() {
		return StringUtils.isBlank(totalreccount) ? "0" : totalreccount;
	}

	public void setTotalreccount(String totalreccount) {
		this.totalreccount = totalreccount;
	}

	public String getPaginationsize() {
		return StringUtils.isBlank(paginationsize) ? "0" : paginationsize;
	}

	public void setPaginationsize(String paginationsize) {
		this.paginationsize = paginationsize;
	}

	public String getLpage() {
		return lpage;
	}

	public void setLpage(String lpage) {
		this.lpage = lpage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(String totalpages) {
		this.totalpages = totalpages;
	}

	public String getCurrentpageno() {
		return StringUtils.isBlank(currentpageno) ? "1" : currentpageno;
	}

	public void setCurrentpageno(String currentpageno) {
		this.currentpageno = currentpageno;
	}

	public String getStartrownum() {
		return StringUtils.isBlank(startrownum) ? "1" : startrownum;
	}

	public void setStartrownum(String startrownum) {
		this.startrownum = startrownum;
	}

	public String getEndrownum() {
		return endrownum;
	}

	public void setEndrownum(String endrownum) {
		this.endrownum = endrownum;
	}

	public String getCurrentcheckeditems() {
		return currentcheckeditems;
	}

	public void setCurrentcheckeditems(String currentcheckeditems) {
		this.currentcheckeditems = currentcheckeditems;
	}

	public String getCheckedcount() {
		return checkedcount;
	}

	public void setCheckedcount(String checkedcount) {
		this.checkedcount = checkedcount;
	}

	public String[] getSelect() {
		return select;
	}

	public void setSelect(String[] select) {
		this.select = select;
	}

	public List<String> getTransactionNos() {
		return transactionNos;
	}

	public void setTransactionNos(List<String> transactionNos) {
		this.transactionNos = transactionNos;
	}

	public List<String> getReceivePayAmounts() {
		return receivePayAmounts;
	}

	public void setReceivePayAmounts(List<String> receivePayAmounts) {
		this.receivePayAmounts = receivePayAmounts;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<String> getChkbox() {
		return chkbox;
	}

	public void setChkbox(List<String> chkbox) {
		this.chkbox = chkbox;
	}

	public String getHideRowCnt() {
		return hideRowCnt;
	}

	public void setHideRowCnt(String hideRowCnt) {
		this.hideRowCnt = hideRowCnt;
	}

	public String getTxtTotalAmt() {
		return txtTotalAmt;
	}

	public void setTxtTotalAmt(String txtTotalAmt) {
		this.txtTotalAmt = txtTotalAmt;
	}

	public String getTxtDiffAmt() {
		return txtDiffAmt;
	}

	public void setTxtDiffAmt(String txtDiffAmt) {
		this.txtDiffAmt = txtDiffAmt;
	}

	public String getTotalConRecCur() {
		return totalConRecCur;
	}

	public void setTotalConRecCur(String totalConRecCur) {
		this.totalConRecCur = totalConRecCur;
	}

	public List<String> getCedingCompanyValList() {
		return cedingCompanyValList;
	}

	public void setCedingCompanyValList(List<String> cedingCompanyValList) {
		this.cedingCompanyValList = cedingCompanyValList;
	}

	public List<String> getExachangeValList() {
		return exachangeValList;
	}

	public void setExachangeValList(List<String> exachangeValList) {
		this.exachangeValList = exachangeValList;
	}

	public List<String> getPayamountValList() {
		return payamountValList;
	}

	public void setPayamountValList(List<String> payamountValList) {
		this.payamountValList = payamountValList;
	}

	public List<String> getRowamountValList() {
		return rowamountValList;
	}

	public void setRowamountValList(List<String> rowamountValList) {
		this.rowamountValList = rowamountValList;
	}

	public List<String> getRecNoValList() {
		return recNoValList;
	}

	public void setRecNoValList(List<String> recNoValList) {
		this.recNoValList = recNoValList;
	}

	public List<String> getSetExcRateValList() {
		return setExcRateValList;
	}

	public void setSetExcRateValList(List<String> setExcRateValList) {
		this.setExcRateValList = setExcRateValList;
	}

	public List<String> getConRecCurValList() {
		return conRecCurValList;
	}

	public void setConRecCurValList(List<String> conRecCurValList) {
		this.conRecCurValList = conRecCurValList;
	}

	public List<String> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<String> paymentList) {
		this.paymentList = paymentList;
	}

	public List<String> getRecNoList() {
		return recNoList;
	}

	public void setRecNoList(List<String> recNoList) {
		this.recNoList = recNoList;
	}

	public String getHidTotalAmt() {
		return hidTotalAmt;
	}

	public void setHidTotalAmt(String hidTotalAmt) {
		this.hidTotalAmt = hidTotalAmt;
	}

	public String getReceiptamt() {
		return receiptamt;
	}

	public void setReceiptamt(String receiptamt) {
		this.receiptamt = receiptamt;
	}

	public String getIncepDate() {
		return incepDate;
	}

	public void setIncepDate(String incepDate) {
		this.incepDate = incepDate;
	}

	public String getOrginalCurrency() {
		return orginalCurrency;
	}

	public void setOrginalCurrency(String orginalCurrency) {
		this.orginalCurrency = orginalCurrency;
	}

	public String getDropDown() {
		return dropDown;
	}

	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}

	public List<String> getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(List<String> previousValue) {
		this.previousValue = previousValue;
	}

	public String getAmend_date() {
		return amend_date;
	}

	public void setAmend_date(String amendDate) {
		amend_date = amendDate;
	}

	public List<TreasuryBean> getReversalInfo() {
		return ReversalInfo;
	}

	public void setReversalInfo(List<TreasuryBean> reversalInfo) {
		ReversalInfo = reversalInfo;
	}

	public void setCedingCompanyName(String cedingCompanyName) {
		this.cedingCompanyName = cedingCompanyName;
	}

	public String getCedingCompanyName() {
		return cedingCompanyName;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setCancelType(String cancelType) {
		this.cancelType = cancelType;
	}

	public String getCancelType() {
		return cancelType;
	}

	public String getWithHoldingTaxOC() {
		return withHoldingTaxOC;
	}

	public void setWithHoldingTaxOC(String withHoldingTaxOC) {
		this.withHoldingTaxOC = withHoldingTaxOC;
	}

	public String getWithHoldingTaxDC() {
		return withHoldingTaxDC;
	}

	public void setWithHoldingTaxDC(String withHoldingTaxDC) {
		this.withHoldingTaxDC = withHoldingTaxDC;
	}

	public String getAdjustMentType() {
		return adjustMentType;
	}

	public void setAdjustMentType(String adjustMentType) {
		this.adjustMentType = adjustMentType;
	}
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPaymentNoSearch() {
		return paymentNoSearch;
	}
	public void setPaymentNoSearch(String paymentNoSearch) {
		this.paymentNoSearch = paymentNoSearch;
	}
	public String getBrokerNameSearch() {
		return brokerNameSearch;
	}
	public void setBrokerNameSearch(String brokerNameSearch) {
		this.brokerNameSearch = brokerNameSearch;
	}
	public String getCompanyNameSearch() {
		return companyNameSearch;
	}
	public void setCompanyNameSearch(String companyNameSearch) {
		this.companyNameSearch = companyNameSearch;
	}
	public String getRemarksSearch() {
		return remarksSearch;
	}
	public void setRemarksSearch(String remarksSearch) {
		this.remarksSearch = remarksSearch;
	}
	public String getFullSearch() {
		return fullSearch;
	}
	public void setFullSearch(String fullSearch) {
		this.fullSearch = fullSearch;
	}
	public String getCurrencySearch() {
		return currencySearch;
	}
	public void setCurrencySearch(String currencySearch) {
		this.currencySearch = currencySearch;
	}

	
}
