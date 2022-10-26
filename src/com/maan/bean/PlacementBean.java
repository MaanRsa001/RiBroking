package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlacementBean {

	private String branchCode;
	private String proposalNo;
	private String contractNo;
	private String layerNo;
	private String bouquetModeYN;
	private String bouquetNo;
	private String deleteId;
	private String dropDown;
	private List<String>reinsSNo;
	private List<String>reinsureName;
	private List<String>placingBroker;
	private List<String>shareOffer;
	private List<String>mailStatus;
	private String cedingCompany;
	private String treatyName;
	private String inceptionDate;
	private String expiryDate;
	private String uwYear;
	private String uwYearTo;
	private String placementNo;
	private String baseProposalNo;
	private String sectionNo;
	private String mode;
	private List<Map<String,Object>>reinsurerInfoList;
	private List<Map<String,Object>>placementInfoList;
	private List<Map<String,Object>>placementeditInfo;
	private String emailBy;
	private String updateDate;
	
	private String currentStatus;
	private String newStatus;
	private String cedentCorrespondent;
	private String reinsurerCorrespondent;
	private String tqrCorrespondent;
	private List<String>shareOffered=new ArrayList<String>();
	private List<String>writtenLine=new ArrayList<String>();
	private List<String>brokerage=new ArrayList<String>();
	private List<String>writtenvaliditydate=new ArrayList<String>();
	private List<String>writtenvalidityRemarks=new ArrayList<String>();
	private List<String>proposedWL=new ArrayList<String>();
	private List<String>signedLine=new ArrayList<String>();
	private List<String>proposedSL=new ArrayList<String>();
	private List<String>reoffer=new ArrayList<String>();
	private List<String>tqrBrokerageAmt=new ArrayList<String>();
	private List<String>signedLineValidity=new ArrayList<String>();
	private List<String>signedLineRemarks=new ArrayList<String>();
	private List<String>emailStatus=new ArrayList<String>();
	private String reinsurerId;
	private String brokerId;
	private List<String> proposalNos;
	private List<String> reinsurerIds;
	private List<String> brokerIds;
	
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
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
	public String getBouquetModeYN() {
		return bouquetModeYN;
	}
	public void setBouquetModeYN(String bouquetModeYN) {
		this.bouquetModeYN = bouquetModeYN;
	}
	public String getBouquetNo() {
		return bouquetNo;
	}
	public void setBouquetNo(String bouquetNo) {
		this.bouquetNo = bouquetNo;
	}
	public String getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public List<String> getReinsSNo() {
		return reinsSNo;
	}
	public void setReinsSNo(List<String> reinsSNo) {
		this.reinsSNo = reinsSNo;
	}
	public List<String> getReinsureName() {
		return reinsureName;
	}
	public void setReinsureName(List<String> reinsureName) {
		this.reinsureName = reinsureName;
	}
	public List<String> getPlacingBroker() {
		return placingBroker;
	}
	public void setPlacingBroker(List<String> placingBroker) {
		this.placingBroker = placingBroker;
	}
	public List<String> getShareOffer() {
		return shareOffer;
	}
	public void setShareOffer(List<String> shareOffer) {
		this.shareOffer = shareOffer;
	}
	public List<String> getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(List<String> mailStatus) {
		this.mailStatus = mailStatus;
	}
	public String getCedingCompany() {
		return cedingCompany;
	}
	public void setCedingCompany(String cedingCompany) {
		this.cedingCompany = cedingCompany;
	}
	public String getTreatyName() {
		return treatyName;
	}
	public void setTreatyName(String treatyName) {
		this.treatyName = treatyName;
	}
	public String getInceptionDate() {
		return inceptionDate;
	}
	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getUwYear() {
		return uwYear;
	}
	public void setUwYear(String uwYear) {
		this.uwYear = uwYear;
	}
	public String getUwYearTo() {
		return uwYearTo;
	}
	public void setUwYearTo(String uwYearTo) {
		this.uwYearTo = uwYearTo;
	}
	public String getPlacementNo() {
		return placementNo;
	}
	public void setPlacementNo(String placementNo) {
		this.placementNo = placementNo;
	}
	public String getBaseProposalNo() {
		return baseProposalNo;
	}
	public void setBaseProposalNo(String baseProposalNo) {
		this.baseProposalNo = baseProposalNo;
	}
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<Map<String, Object>> getReinsurerInfoList() {
		return reinsurerInfoList;
	}
	public void setReinsurerInfoList(List<Map<String, Object>> reinsurerInfoList) {
		this.reinsurerInfoList = reinsurerInfoList;
	}
	public List<Map<String, Object>> getPlacementInfoList() {
		return placementInfoList;
	}
	public void setPlacementInfoList(List<Map<String, Object>> placementInfoList) {
		this.placementInfoList = placementInfoList;
	}
	public List<Map<String, Object>> getPlacementeditInfo() {
		return placementeditInfo;
	}
	public void setPlacementeditInfo(List<Map<String, Object>> placementeditInfo) {
		this.placementeditInfo = placementeditInfo;
	}
	public String getEmailBy() {
		return emailBy;
	}
	public void setEmailBy(String emailBy) {
		this.emailBy = emailBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public String getCedentCorrespondent() {
		return cedentCorrespondent;
	}
	public void setCedentCorrespondent(String cedentCorrespondent) {
		this.cedentCorrespondent = cedentCorrespondent;
	}
	public String getReinsurerCorrespondent() {
		return reinsurerCorrespondent;
	}
	public void setReinsurerCorrespondent(String reinsurerCorrespondent) {
		this.reinsurerCorrespondent = reinsurerCorrespondent;
	}
	public String getTqrCorrespondent() {
		return tqrCorrespondent;
	}
	public void setTqrCorrespondent(String tqrCorrespondent) {
		this.tqrCorrespondent = tqrCorrespondent;
	}
	public List<String> getShareOffered() {
		return shareOffered;
	}
	public void setShareOffered(List<String> shareOffered) {
		this.shareOffered = shareOffered;
	}
	public List<String> getWrittenLine() {
		return writtenLine;
	}
	public void setWrittenLine(List<String> writtenLine) {
		this.writtenLine = writtenLine;
	}
	public List<String> getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(List<String> brokerage) {
		this.brokerage = brokerage;
	}
	public List<String> getWrittenvaliditydate() {
		return writtenvaliditydate;
	}
	public void setWrittenvaliditydate(List<String> writtenvaliditydate) {
		this.writtenvaliditydate = writtenvaliditydate;
	}
	public List<String> getWrittenvalidityRemarks() {
		return writtenvalidityRemarks;
	}
	public void setWrittenvalidityRemarks(List<String> writtenvalidityRemarks) {
		this.writtenvalidityRemarks = writtenvalidityRemarks;
	}
	public List<String> getProposedWL() {
		return proposedWL;
	}
	public void setProposedWL(List<String> proposedWL) {
		this.proposedWL = proposedWL;
	}
	public List<String> getSignedLine() {
		return signedLine;
	}
	public void setSignedLine(List<String> signedLine) {
		this.signedLine = signedLine;
	}
	public List<String> getProposedSL() {
		return proposedSL;
	}
	public void setProposedSL(List<String> proposedSL) {
		this.proposedSL = proposedSL;
	}
	public List<String> getReoffer() {
		return reoffer;
	}
	public void setReoffer(List<String> reoffer) {
		this.reoffer = reoffer;
	}
	public List<String> getTqrBrokerageAmt() {
		return tqrBrokerageAmt;
	}
	public void setTqrBrokerageAmt(List<String> tqrBrokerageAmt) {
		this.tqrBrokerageAmt = tqrBrokerageAmt;
	}
	public List<String> getSignedLineValidity() {
		return signedLineValidity;
	}
	public void setSignedLineValidity(List<String> signedLineValidity) {
		this.signedLineValidity = signedLineValidity;
	}
	public List<String> getSignedLineRemarks() {
		return signedLineRemarks;
	}
	public void setSignedLineRemarks(List<String> signedLineRemarks) {
		this.signedLineRemarks = signedLineRemarks;
	}
	public List<String> getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(List<String> emailStatus) {
		this.emailStatus = emailStatus;
	}
	public String getReinsurerId() {
		return reinsurerId;
	}
	public void setReinsurerId(String reinsurerId) {
		this.reinsurerId = reinsurerId;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public List<String> getProposalNos() {
		return proposalNos;
	}
	public void setProposalNos(List<String> proposalNos) {
		this.proposalNos = proposalNos;
	}
	public List<String> getReinsurerIds() {
		return reinsurerIds;
	}
	public void setReinsurerIds(List<String> reinsurerIds) {
		this.reinsurerIds = reinsurerIds;
	}
	public List<String> getBrokerIds() {
		return brokerIds;
	}
	public void setBrokerIds(List<String> brokerIds) {
		this.brokerIds = brokerIds;
	}
	
	
	
	
	
}
