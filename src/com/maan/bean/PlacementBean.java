package com.maan.bean;

import java.io.File;
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
	private String cedingCompanyName;
	private List<String>cedingCompanyNames=new ArrayList<String>();
	private String treatyName;
	private String inceptionDate;
	private String expiryDate;
	private String uwYear;
	private String uwYearTo;
	private String placementNo;
	private String statusNo;
	private String baseProposalNo;
	private String sectionNo;
	private String mode;
	private List<Map<String,Object>>reinsurerInfoList;
	private List<Map<String,Object>>exreinsurerInfoList;
	private List<Map<String,Object>>placementInfoList;
	private List<Map<String,Object>>placementeditInfo;
	private List<Map<String,Object>>placementviewInfo;
	private List<Map<String,Object>>plSummaryInfo;
	private String emailBy;
	private String updateDate;
	private String mailrecordNo;
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
	private List<String>psignedLine=new ArrayList<String>();
	private List<String>proposedSL=new ArrayList<String>();
	private List<String>reoffer=new ArrayList<String>();
	private List<String>tqrBrokerageAmt=new ArrayList<String>();
	private List<String>signedLineValidity=new ArrayList<String>();
	private List<String>signedLineRemarks=new ArrayList<String>();
	private List<String>emailStatus=new ArrayList<String>();
	private String reinsurerId;
	private String brokerId;
	private List<String> snos;
	private List<String> bouquetNos;
	private List<String> baseproposalNos;
	private List<String> proposalNos;
	private List<String> reinsurerIds;
	private List<String> brokerIds;
	private List<String> reinsurerNames;
	private List<String> brokerNames;
	private List<String> cedingCompanys;
	private String mailType;
	private String mailSubject;
	private String mailBody;
	private String mailTo;
	private String mailCC;
	private String mailRegards;
	private String maxSharePercent;
	private String maxShareWritten;
	private String maxShareSigned;
	private String userId;
	private String eproposalNo;
	private String placementMode;
	private String placementDisabled;
	private String mailRemarks;
	private String searchType;
	private String searchReinsurerId;
	private String searchStatus;
	private String searchBrokerId;
	private String notplacedProposal;
	private String placedProposal;
	private String productId;
	private String status;
	
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	private String filePath;
	private String fileName;
	//private String docType;
	//private String docId;
	private List<Integer> docuList=new ArrayList<Integer>();
	private List<String>docId;
	private List<String>docTypeId;
	private List<String>docDesc;
	private String corresId;
	private String sno;
	private List<String> deleteStatus;
	private List<String> changeStatus;
	private String offerNo;
	private String amendId;
	private String placementamendId;
	private String reinsurerName;
	private String brokerCompany;
	private String reinsurerType;
	private String companyNameSearch;
	private String brokerNameSearch;
	private String uwYearSearch;
	private String uwYearToSearch;
	private String incepDateSearch;
	private String expDateSearch;
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
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailCC() {
		return mailCC;
	}
	public void setMailCC(String mailCC) {
		this.mailCC = mailCC;
	}
	
	public String getMailRegards() {
		return mailRegards;
	}
	public void setMailRegards(String mailRegards) {
		this.mailRegards = mailRegards;
	}
	public String getMaxSharePercent() {
		return maxSharePercent;
	}
	public void setMaxSharePercent(String maxSharePercent) {
		this.maxSharePercent = maxSharePercent;
	}
	public String getMailrecordNo() {
		return mailrecordNo;
	}
	public void setMailrecordNo(String mailrecordNo) {
		this.mailrecordNo = mailrecordNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEproposalNo() {
		return eproposalNo;
	}
	public void setEproposalNo(String eproposalNo) {
		this.eproposalNo = eproposalNo;
	}
	public String getPlacementMode() {
		return placementMode;
	}
	public void setPlacementMode(String placementMode) {
		this.placementMode = placementMode;
	}
	public List<Map<String, Object>> getExreinsurerInfoList() {
		return exreinsurerInfoList;
	}
	public void setExreinsurerInfoList(List<Map<String, Object>> exreinsurerInfoList) {
		this.exreinsurerInfoList = exreinsurerInfoList;
	}
	public String getPlacementDisabled() {
		return placementDisabled;
	}
	public void setPlacementDisabled(String placementDisabled) {
		this.placementDisabled = placementDisabled;
	}
	public String getMailRemarks() {
		return mailRemarks;
	}
	public void setMailRemarks(String mailRemarks) {
		this.mailRemarks = mailRemarks;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchReinsurerId() {
		return searchReinsurerId;
	}
	public void setSearchReinsurerId(String searchReinsurerId) {
		this.searchReinsurerId = searchReinsurerId;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public String getNotplacedProposal() {
		return notplacedProposal;
	}
	public void setNotplacedProposal(String notplacedProposal) {
		this.notplacedProposal = notplacedProposal;
	}
	public String getPlacedProposal() {
		return placedProposal;
	}
	public void setPlacedProposal(String placedProposal) {
		this.placedProposal = placedProposal;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public List<String> getDocId() {
		return docId;
	}
	public void setDocId(List<String> docId) {
		this.docId = docId;
	}
	public List<String> getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(List<String> docTypeId) {
		this.docTypeId = docTypeId;
	}
	public List<String> getDocDesc() {
		return docDesc;
	}
	public void setDocDesc(List<String> docDesc) {
		this.docDesc = docDesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List<Integer> getDocuList() {
		return docuList;
	}
	public void setDocuList(List<Integer> docuList) {
		this.docuList = docuList;
	}
	public String getCorresId() {
		return corresId;
	}
	public void setCorresId(String corresId) {
		this.corresId = corresId;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public List<String> getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(List<String> deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public List<String> getSnos() {
		return snos;
	}
	public void setSnos(List<String> snos) {
		this.snos = snos;
	}
	public List<String> getBouquetNos() {
		return bouquetNos;
	}
	public void setBouquetNos(List<String> bouquetNos) {
		this.bouquetNos = bouquetNos;
	}
	public List<String> getBaseproposalNos() {
		return baseproposalNos;
	}
	public void setBaseproposalNos(List<String> baseproposalNos) {
		this.baseproposalNos = baseproposalNos;
	}
	public List<String> getReinsurerNames() {
		return reinsurerNames;
	}
	public void setReinsurerNames(List<String> reinsurerNames) {
		this.reinsurerNames = reinsurerNames;
	}
	public List<String> getBrokerNames() {
		return brokerNames;
	}
	public void setBrokerNames(List<String> brokerNames) {
		this.brokerNames = brokerNames;
	}
	public List<String> getCedingCompanys() {
		return cedingCompanys;
	}
	public void setCedingCompanys(List<String> cedingCompanys) {
		this.cedingCompanys = cedingCompanys;
	}
	public List<String> getPsignedLine() {
		return psignedLine;
	}
	public void setPsignedLine(List<String> psignedLine) {
		this.psignedLine = psignedLine;
	}
	public String getOfferNo() {
		return offerNo;
	}
	public void setOfferNo(String offerNo) {
		this.offerNo = offerNo;
	}
	public List<Map<String, Object>> getPlacementviewInfo() {
		return placementviewInfo;
	}
	public void setPlacementviewInfo(List<Map<String, Object>> placementviewInfo) {
		this.placementviewInfo = placementviewInfo;
	}
	public String getCedingCompanyName() {
		return cedingCompanyName;
	}
	public void setCedingCompanyName(String cedingCompanyName) {
		this.cedingCompanyName = cedingCompanyName;
	}
	public List<String> getCedingCompanyNames() {
		return cedingCompanyNames;
	}
	public void setCedingCompanyNames(List<String> cedingCompanyNames) {
		this.cedingCompanyNames = cedingCompanyNames;
	}
	public String getMaxShareWritten() {
		return maxShareWritten;
	}
	public void setMaxShareWritten(String maxShareWritten) {
		this.maxShareWritten = maxShareWritten;
	}
	public String getMaxShareSigned() {
		return maxShareSigned;
	}
	public void setMaxShareSigned(String maxShareSigned) {
		this.maxShareSigned = maxShareSigned;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getPlacementamendId() {
		return placementamendId;
	}
	public void setPlacementamendId(String placementamendId) {
		this.placementamendId = placementamendId;
	}
	public String getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}
	public List<String> getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(List<String> changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getSearchBrokerId() {
		return searchBrokerId;
	}
	public void setSearchBrokerId(String searchBrokerId) {
		this.searchBrokerId = searchBrokerId;
	}
	public String getReinsurerName() {
		return reinsurerName;
	}
	public void setReinsurerName(String reinsurerName) {
		this.reinsurerName = reinsurerName;
	}
	public String getBrokerCompany() {
		return brokerCompany;
	}
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}
	public String getReinsurerType() {
		return reinsurerType;
	}
	public void setReinsurerType(String reinsurerType) {
		this.reinsurerType = reinsurerType;
	}
	public List<Map<String, Object>> getPlSummaryInfo() {
		return plSummaryInfo;
	}
	public void setPlSummaryInfo(List<Map<String, Object>> plSummaryInfo) {
		this.plSummaryInfo = plSummaryInfo;
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
	public String getUwYearSearch() {
		return uwYearSearch;
	}
	public void setUwYearSearch(String uwYearSearch) {
		this.uwYearSearch = uwYearSearch;
	}
	public String getUwYearToSearch() {
		return uwYearToSearch;
	}
	public void setUwYearToSearch(String uwYearToSearch) {
		this.uwYearToSearch = uwYearToSearch;
	}
	public String getIncepDateSearch() {
		return incepDateSearch;
	}
	public void setIncepDateSearch(String incepDateSearch) {
		this.incepDateSearch = incepDateSearch;
	}
	public String getExpDateSearch() {
		return expDateSearch;
	}
	public void setExpDateSearch(String expDateSearch) {
		this.expDateSearch = expDateSearch;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
}
