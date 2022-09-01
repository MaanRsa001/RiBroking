package com.maan.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadBean {
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	
	private String productId;
	private String display;
	private String moduleType;
	private String contarctno;
	private String layerNo;
	private String tranNo;
	private String brokerName;
	private String documentId;
	private String ourFileName;
	private String orgFileName;
	private String filePath;
	private String startIndex;
	private String endIndex;
	private String proposalno;
	private String startValue;
	private String companyName;
	private String type;
	private String allocType;
	private String proposal_No;
	private String mode;
	private String premiumDisplay;
	private String fileName;
	private List<Map<String,Object>> docList=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> docType=new ArrayList<Map<String,Object>>();
	private List<Integer> docuList=new ArrayList<Integer>();
	private List<String> docId=new ArrayList<String>();
	private List<String> docDesc=new ArrayList<String>();
	private List<String> docTypeId=new ArrayList<String>();
	private List<Map<String,Object>> moduleTypeList=new ArrayList<Map<String,Object>>();
	private String dropDown;
	private String claimDisplay;
	private String departmentId;
	
	private String deleteId;
	
	
	
	public String getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
	public String getClaimDisplay() {
		return claimDisplay;
	}
	public void setClaimDisplay(String claimDisplay) {
		this.claimDisplay = claimDisplay;
	}
	public String getPremiumDisplay() {
		return premiumDisplay;
	}
	public void setPremiumDisplay(String premiumDisplay) {
		this.premiumDisplay = premiumDisplay;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public List<Map<String, Object>> getModuleTypeList() {
		return moduleTypeList;
	}
	public void setModuleTypeList(List<Map<String, Object>> moduleTypeList) {
		this.moduleTypeList = moduleTypeList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
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
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getContarctno() {
		return contarctno;
	}
	public void setContarctno(String contarctno) {
		this.contarctno = contarctno;
	}
	public String getLayerNo() {
		return layerNo;
	}
	public void setLayerNo(String layerNo) {
		this.layerNo = layerNo;
	}
	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getOurFileName() {
		return ourFileName;
	}
	public void setOurFileName(String ourFileName) {
		this.ourFileName = ourFileName;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public String getProposalno() {
		return proposalno;
	}
	public void setProposalno(String proposalno) {
		this.proposalno = proposalno;
	}
	public String getStartValue() {
		return startValue;
	}
	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}
	
	public List<Map<String, Object>> getDocList() {
		return docList;
	}
	public void setDocList(List<Map<String, Object>> docList) {
		this.docList = docList;
	}
	public List<Map<String, Object>> getDocType() {
		return docType;
	}
	public void setDocType(List<Map<String, Object>> docType) {
		this.docType = docType;
	}
	public List<String> getDocId() {
		return docId;
	}
	public void setDocId(List<String> docId) {
		this.docId = docId;
	}
	public List<String> getDocDesc() {
		return docDesc;
	}
	public void setDocDesc(List<String> docDesc) {
		this.docDesc = docDesc;
	}
	public List<String> getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(List<String> docTypeId) {
		this.docTypeId = docTypeId;
	}
	public List<Integer> getDocuList() {
		return docuList;
	}
	public void setDocuList(List<Integer> docuList) {
		this.docuList = docuList;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAllocType() {
		return allocType;
	}
	public void setAllocType(String allocType) {
		this.allocType = allocType;
	}
	public String getProposal_No() {
		return proposal_No;
	}
	public void setProposal_No(String proposalNo) {
		proposal_No = proposalNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
		
	
}
