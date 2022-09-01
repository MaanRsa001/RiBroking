package com.maan.bean;

import java.util.List;
import java.util.Map;

public class JournalBean {

 private String journalID;
 private String downloadType;
 private String journalname;
 private String fileName;
 private String openperiod;
 private String status;
 private String processStatus;
 private String parttoShow;
 private String shortname;
 private String loginIdList;
 private String branchCode;
 private String loginId;
 private String mode;
 private String transactionDate;
 private String reversalDate;
 private String ledClass;
 private String currency;
 private String exchRate;
 private String departId;
 private List<String> ledgerId;
 private List<String> debitOC;
 private List<String> creditOC;
 private List<String> debitDC;
 private List<String> creditDC;
 private String narration;
 private String amendmentDate;
 private String tranId;
 private List<Map<String,Object>> orginalCurrency;
 private List<Map<String,Object>> ledgerList;
 private List<Map<String,Object>> ledgerdetailList;
 private List<Map<String,Object>> ledgerdeptList;
 private List<JournalBean> ledgerEntryList;
 private String totaldebitOC;
 private String totalcreditOC;
 private String totaldebitDC;
 private String totalcreditDC;
 private String exdebitDC;
 private String excreditDC;
 private String loopcount;
 private String deleteId;
 private String opstartDate;
 private String opendDate;
 private String transOpenperiodStatus;
 private String reversalNo;
 private String reversalStatus;
 private String forexDiffName;
 private String startDate;
 private String endDate;
 private String typeId;
 private String reportType;
 private String journalType;
 public String getDeleteId() {
	return deleteId;
}
public void setDeleteId(String deleteId) {
	this.deleteId = deleteId;
}
public String getParttoShow() {
	return parttoShow;
}
public void setParttoShow(String parttoShow) {
	this.parttoShow = parttoShow;
}
public String getProcessStatus() {
	return processStatus;
}
public void setProcessStatus(String processStatus) {
	this.processStatus = processStatus;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getOpenperiod() {
	return openperiod;
}
public void setOpenperiod(String openperiod) {
	this.openperiod = openperiod;
}
 private List<List<Map<String, Object>>> journalViewList;
 private List<Map<String,Object>> spcCurrencyList;
 private List<Map<String,Object>> yearList;
public String getJournalID() {
	return journalID;
}
public void setJournalID(String journalID) {
	this.journalID = journalID;
}
public String getDownloadType() {
	return downloadType;
}
public void setDownloadType(String downloadType) {
	this.downloadType = downloadType;
}
public String getJournalname() {
	return journalname;
}
public void setJournalname(String journalname) {
	this.journalname = journalname;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}

public List<List<Map<String, Object>>> getJournalViewList() {
	return journalViewList;
}
public void setJournalViewList(List<List<Map<String, Object>>> journalViewList) {
	this.journalViewList = journalViewList;
}
public List<Map<String, Object>> getSpcCurrencyList() {
	return spcCurrencyList;
}
public void setSpcCurrencyList(List<Map<String, Object>> spcCurrencyList) {
	this.spcCurrencyList = spcCurrencyList;
}
public String getShortname() {
	return shortname;
}
public void setShortname(String shortname) {
	this.shortname = shortname;
}
public List<Map<String, Object>> getYearList() {
	return yearList;
}
public void setYearList(List<Map<String, Object>> yearList) {
	this.yearList = yearList;
}
public String getLoginIdList() {
	return loginIdList;
}
public void setLoginIdList(String loginIdList) {
	this.loginIdList = loginIdList;
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
public String getMode() {
	return mode;
}
public void setMode(String mode) {
	this.mode = mode;
}
public List<Map<String, Object>> getOrginalCurrency() {
	return orginalCurrency;
}
public void setOrginalCurrency(List<Map<String, Object>> orginalCurrency) {
	this.orginalCurrency = orginalCurrency;
}
public List<Map<String, Object>> getLedgerList() {
	return ledgerList;
}
public void setLedgerList(List<Map<String, Object>> ledgerList) {
	this.ledgerList = ledgerList;
}
public List<Map<String, Object>> getLedgerdetailList() {
	return ledgerdetailList;
}
public void setLedgerdetailList(List<Map<String, Object>> ledgerdetailList) {
	this.ledgerdetailList = ledgerdetailList;
}
public String getLoopcount() {
	return loopcount;
}
public void setLoopcount(String loopcount) {
	this.loopcount = loopcount;
}
public List<Map<String, Object>> getLedgerdeptList() {
	return ledgerdeptList;
}
public void setLedgerdeptList(List<Map<String, Object>> ledgerdeptList) {
	this.ledgerdeptList = ledgerdeptList;
}
public String getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(String transactionDate) {
	this.transactionDate = transactionDate;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public String getExchRate() {
	return exchRate;
}
public void setExchRate(String exchRate) {
	this.exchRate = exchRate;
}
public String getDepartId() {
	return departId;
}
public void setDepartId(String departId) {
	this.departId = departId;
}
public List<String> getLedgerId() {
	return ledgerId;
}
public void setLedgerId(List<String> ledgerId) {
	this.ledgerId = ledgerId;
}
public List<String> getDebitOC() {
	return debitOC;
}
public void setDebitOC(List<String> debitOC) {
	this.debitOC = debitOC;
}
public List<String> getCreditOC() {
	return creditOC;
}
public void setCreditOC(List<String> creditOC) {
	this.creditOC = creditOC;
}
public List<String> getDebitDC() {
	return debitDC;
}
public void setDebitDC(List<String> debitDC) {
	this.debitDC = debitDC;
}
public List<String> getCreditDC() {
	return creditDC;
}
public void setCreditDC(List<String> creditDC) {
	this.creditDC = creditDC;
}
public String getNarration() {
	return narration;
}
public void setNarration(String narration) {
	this.narration = narration;
}
public String getLedClass() {
	return ledClass;
}
public void setLedClass(String ledClass) {
	this.ledClass = ledClass;
}
public String getAmendmentDate() {
	return amendmentDate;
}
public void setAmendmentDate(String amendmentDate) {
	this.amendmentDate = amendmentDate;
}

public List<JournalBean> getLedgerEntryList() {
	return ledgerEntryList;
}
public void setLedgerEntryList(List<JournalBean> ledgerEntryList) {
	this.ledgerEntryList = ledgerEntryList;
}
public String getTranId() {
	return tranId;
}
public void setTranId(String tranId) {
	this.tranId = tranId;
}
public String getTotaldebitOC() {
	return totaldebitOC;
}
public void setTotaldebitOC(String totaldebitOC) {
	this.totaldebitOC = totaldebitOC;
}
public String getTotalcreditOC() {
	return totalcreditOC;
}
public void setTotalcreditOC(String totalcreditOC) {
	this.totalcreditOC = totalcreditOC;
}
public String getTotaldebitDC() {
	return totaldebitDC;
}
public void setTotaldebitDC(String totaldebitDC) {
	this.totaldebitDC = totaldebitDC;
}
public String getTotalcreditDC() {
	return totalcreditDC;
}
public void setTotalcreditDC(String totalcreditDC) {
	this.totalcreditDC = totalcreditDC;
}
public String getExdebitDC() {
	return exdebitDC;
}
public void setExdebitDC(String exdebitDC) {
	this.exdebitDC = exdebitDC;
}
public String getExcreditDC() {
	return excreditDC;
}
public void setExcreditDC(String excreditDC) {
	this.excreditDC = excreditDC;
}
public String getReversalDate() {
	return reversalDate;
}
public void setReversalDate(String reversalDate) {
	this.reversalDate = reversalDate;
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
public String getTransOpenperiodStatus() {
	return transOpenperiodStatus;
}
public void setTransOpenperiodStatus(String transOpenperiodStatus) {
	this.transOpenperiodStatus = transOpenperiodStatus;
}
public String getReversalNo() {
	return reversalNo;
}
public void setReversalNo(String reversalNo) {
	this.reversalNo = reversalNo;
}
public String getReversalStatus() {
	return reversalStatus;
}
public void setReversalStatus(String reversalStatus) {
	this.reversalStatus = reversalStatus;
}
public String getForexDiffName() {
	return forexDiffName;
}
public void setForexDiffName(String forexDiffName) {
	this.forexDiffName = forexDiffName;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getTypeId() {
	return typeId;
}
public void setTypeId(String typeId) {
	this.typeId = typeId;
}
public String getReportType() {
	return reportType;
}
public void setReportType(String reportType) {
	this.reportType = reportType;
}

public String getJournalType() {
	return journalType;
}
public void setJournalType(String journalType) {
	this.journalType = journalType;
}


 

}
