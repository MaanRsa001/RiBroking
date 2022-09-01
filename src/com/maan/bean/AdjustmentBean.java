package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdjustmentBean {
	private List<Map<String,Object>> brokerList=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> ceddingCompanyList=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> currencyList=new ArrayList<Map<String,Object>>();
	private List<AdjustmentBean> adjustmentList;
	private String loginId;
	private String transactionNo;
	private String transactionDate;
	private String contractNo;
	private String layerNo;
	private String brokerName;
	private String cedingName;
	private String currency;
	private String pendingAmount;
	private String adjustAmount;
	private String adjustType;
	private String remarks;
	private String adjustmentDate;
	private String brokerId;
	private String cedingId;
	private String transactionType;
	private String currencyId;
	private String branchCode;
	private String type;
	private String productName;
	private List<String> transactionNos;
	private List<String> adjustmentAmounts;
	private List<String> chkbox;
	private List<String> adjustmentType;
	private String serialNo;
	private String allocateddate;
	private String payamount;
	private String status;
	private String currencyName;
	private String test;
	private String amountType;
	private String amount;
	private List<AdjustmentBean> allocatedList;
	private List<AdjustmentBean> allocatedAdjustmentList;
	private String searchBy;
	private String searchValue;
	private String unUtilizedAmt;
	private String check;
	private List<String> transactionDates;
	List<String> transDate=new ArrayList<String>();
	private String receiptNo;
	private String reverseDate;
	private String opStartDate;
	private String opEndDate;
	private String proposlaNo;
	private String subClass;
	
	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOpStartDate() {
		return opStartDate;
	}

	public void setOpStartDate(String opStartDate) {
		this.opStartDate = opStartDate;
	}

	public String getOpEndDate() {
		return opEndDate;
	}

	public void setOpEndDate(String opEndDate) {
		this.opEndDate = opEndDate;
	}

	public String getReverseDate() {
		return reverseDate;
	}

	public void setReverseDate(String reverseDate) {
		this.reverseDate = reverseDate;
	}

	public String getUnUtilizedAmt() {
		return unUtilizedAmt;
	}

	public void setUnUtilizedAmt(String unUtilizedAmt) {
		this.unUtilizedAmt = unUtilizedAmt;
	}

	public String getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(String adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getLayerNo() {
		return layerNo;
	}

	public void setLayerNo(String layerNo) {
		this.layerNo = layerNo;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getCedingName() {
		return cedingName;
	}

	public void setCedingName(String cedingName) {
		this.cedingName = cedingName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(String adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public List<AdjustmentBean> getAdjustmentList() {
		return adjustmentList;
	}

	public void setAdjustmentList(List<AdjustmentBean> adjustmentList) {
		this.adjustmentList = adjustmentList;
	}


	private String mode;
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public List<Map<String, Object>> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Map<String, Object>> currencyList) {
		this.currencyList = currencyList;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getCedingId() {
		return cedingId;
	}

	public void setCedingId(String cedingId) {
		this.cedingId = cedingId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getTransactionNos() {
		return transactionNos;
	}

	public void setTransactionNos(List<String> transactionNos) {
		this.transactionNos = transactionNos;
	}

	public List<String> getAdjustmentAmounts() {
		return adjustmentAmounts;
	}

	public void setAdjustmentAmounts(List<String> adjustmentAmounts) {
		this.adjustmentAmounts = adjustmentAmounts;
	}

	public List<String> getChkbox() {
		return chkbox;
	}

	public void setChkbox(List<String> chkbox) {
		this.chkbox = chkbox;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<String> getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(List<String> adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAllocateddate() {
		return allocateddate;
	}

	public void setAllocateddate(String allocateddate) {
		this.allocateddate = allocateddate;
	}

	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public List<AdjustmentBean> getAllocatedList() {
		return allocatedList;
	}

	public void setAllocatedList(List<AdjustmentBean> allocatedList) {
		this.allocatedList = allocatedList;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<AdjustmentBean> getAllocatedAdjustmentList() {
		return allocatedAdjustmentList;
	}

	public void setAllocatedAdjustmentList(
			List<AdjustmentBean> allocatedAdjustmentList) {
		this.allocatedAdjustmentList = allocatedAdjustmentList;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public List<String> getTransactionDates() {
		return transactionDates;
	}

	public void setTransactionDates(List<String> transactionDates) {
		this.transactionDates = transactionDates;
	}

	public List<String> getTransDate() {
		return transDate;
	}

	public void setTransDate(List<String> transDate) {
		this.transDate = transDate;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getProposlaNo() {
		return proposlaNo;
	}

	public void setProposlaNo(String proposlaNo) {
		this.proposlaNo = proposlaNo;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	
}
