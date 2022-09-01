package com.maan.bean;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PaymentBean {
	private static final long serialVersionUID = 1L;
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
	private String branchCode;
	private String difftype;
	private String policyno="";
	private String allocateddate="";
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
	private String exchangerate="";
	private String totalexchaamount="";
	private LinkedHashMap allocateCurrency=null;
	private String selcurrency;
	private String currencyValue="";	
	private String currencyName="";
	private String alloccurrencyid="";
	private String brokername="";
	private String accountDate="";
	private String accPremium="";
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
	private String accClaim="";
	private String checkPC="";
	private String checkYN="";
	private String accAmount="";
	private String receiptBankId;
	private String receiptBankName;
	private String diffAmount;
	private String totAmount;
	private String allTillDate;
	private String setExcRate;
	private String conRecCur;
	private String allocType;
	private HttpServletRequest request;
	private String baseCurrencyAmount;
	private String transactionType;
	private String bankCharges;
	private String netAmt;
	private String bank;
	private String unAllocatedCash;
	private String totalDR;
	private String totalCR;	
	private String withHoldingTaxOC;
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
	 * @param netAmt the netAmt to set
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
	 * @param bankCharges the bankCharges to set
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
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	public LinkedHashMap getAllocateCurrency() {
		return allocateCurrency;
	}
	public void setAllocateCurrency(LinkedHashMap allocateCurrency) {
		this.allocateCurrency = allocateCurrency;
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
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
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

    public String getWithHoldingTaxOC() {
        return withHoldingTaxOC;
    }

    public void setWithHoldingTaxOC(String withHoldingTaxOC) {
        this.withHoldingTaxOC = withHoldingTaxOC;
    }
}
