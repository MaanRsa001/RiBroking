package com.maan.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FaculitivesBean  {

	private static final long serialVersionUID = 1L;
	private String branchCode;
	private String sumInsuredOurShare;
	private String gwpiOurShare;
	private String pmlOurShare;
	private String deductibleOurShare;
	private String coverlimitOurShare;
	private String deductibleOurShareusd;
	private String coverlimitOurShareusd;
	private String riskGrade;
	private String occCode;
	private String riskDetail;
	private String fireProt;
	private String scope;
	private String eqwsInd;
	private String categoryZone;
	private String eqThreat;
	private String wsThreat;
	private String commn;
	private String brokerage;
	private String tax;
	private String lossRecord;
	private String dgmsApproval;
	private String underwriterCode;
	private String uwRecommendation;
	private String remarks;
	private String othAccep;
	private String reftoHO;
	private String decision;
	private String mbind;
	private String mlopYN;
	private String alopYN;
	private String proStatus;
	private String acqCost;
	private String loginid;
	private String no_Insurer;
	private String edit;
	private String risklist;
	private String departmentName;
	private String acqCostPer;
	private String policyBranch;
	private String cedRetenType;
	private String siOSViewOC;
	private String siOSViewDC;
	private String pmlOSViewOC;
	private String pmlOSViewDC;
	private String gwpiOSViewOC;
	private String gwpiOSViewDC;
	private String tplOSViewOC;
	private String tplOSViewDC;
	private String deductibleOSOC;
	private String deductibleOSDC;
	private String coverlimitOSOC;
	private String coverlimitOSDC;
	private String retroType;
	private String processId;
	private String renewalFlag;
	private String renewalStatus;
	private String baseLoginID;
	private String modeOfTransport;
	private String vesselName;
	private String vesselAge;
	private String limitPerVesselOC;
	private String limitPerLocationOC;
	private String limitPerVesselDC;
	private String limitPerLocationDC;
	private boolean prclFlag;
	private String amendId;
	private String type;
	private String flag;
	private String mode;
	private String proposal_no;
	private String conmode;
	private String broker;
	private String insuredName;
	private String location;
	private String city;
	private String originalCurrency;
	private String usCurrencyRate;
	private String nr;
	private String profitCenterCode;
	private String subProfitCenter;
	private String inceptionDate;
	private String inceptionMonth;
	private String inceptionYear;
	private String year;
	private String expiryDate;
	private String expiryMonth;
	private String expiryYear;
	private String accountDate;
	private String accountMonth;
	private String accountYear;
	private String month;
	private String underwriter;
	private String cedantsRet;
	private String maxiumlimit;
	private String deductible;
	private String deductibleDC;
	private String deductibleFacXol;
	private String deductibleFacXolDC;
	private String interest;
	private String spRetro;
	private String pml;
	private String sipml;
	private String cu;
	private String cuRsn;
	private String sumInsured;
	private String gwpi;
	private String xolOC;
	private String xolDC;
	private String xolOSOC;
	private String xolOSDC;
	private String xolOSViewOC;
	private String xolOSViewDC;
	private String noOfInst;
	private String pmll;
	private String shWt;
	private String shSd;
	private String facultativeDepartment;
	private String tpl;
	private String othercost;
	private String premiumrate;
	private String premiurate;
	private String risk;
	private String countryID;
	private String proposalNo;
	private String backmode;
	private String contractGendration;
	private String status;
	private String broke;
	private String contractNo;
	private String tplOurShare;
	private String productId;
	private String departmentId;
	private String accusd;
	private String cedingCompany;
	private String territoryCode;
	private String territoryName;
	private String sumusd;
	private String tplusd;
	private String gwpiUsd;
	private String sumOrginalUsd;
	private String pmlOurShareusd;
	private String gwpiOurShareusd;
	private String pmlusd;
	private String tplOurshareusd;
	private String renewal_Contract_no;
	private String contarctno;
	private String dropDown;
	private String listName;
	private String endorsmentno;
	private String RetentionPercentage;
	private String endorsementStatus;
    private String renewalMode;
    private String shortname;
    public String endorsementDate;
    private String endtMode;
    private String previousendoDate;
    private String maxDate;
    private String opstartDate;
	private String opendDate;
	private String searchType;
	private String searchValue;
    private String reMode;
	private String UWName;
	private String menuStatus;
	private String renewalProposalNo;
	private String acqBonus;
	private String acqBonusPercentage;
	private String deleteId;
	private String previousSelectTerritory;
	private String territory;
	private String countryIncluded;
	private String countryExcluded;
	private String countryIncludedName;
	private String countryExcludedName;
	
	
	private String inwardType;
	private String latitude;
	private String longitude;
	private String locIssued;
	private String gwpiPml;
	private String receiptofPayment;
	private String pslOC;
	private String pllOC;
	private String pblOC;
	private String pslOurShare;
	private String pllOurShare;
	private String pblOurShare;
	private String gwpiPmlOurShare;
	private String siPmlOurShare;
	private String pmlOC;
	private String pmlOCOurShare;
	private String departClass;
	private String deductibleFacXolPml;
	private String deductibleFacXolPmlOurShare;
	private String exclusion;
	private String leader_Underwriter;
	private String leader_Underwriter_country;
	private String leader_Underwriter_share;
	private String vessaletonnage;
	private String sumInsuredPml;
	private String sumInsuredPmlOurShare;
	
	private String crestaStatus;
	private String retper;
	private String endorsmenttype;
	private String pslusd;
	private String pslOurShareusd;
	private String pllusd;
	private String pllOurShareusd;
	private String pblusd;
	private String pblOurShareusd;
	
	private List<Integer> docuList=new ArrayList<Integer>();
	private List<String> docId=new ArrayList<String>();
	private List<String> docDesc=new ArrayList<String>();
	private List<String> docTypeId=new ArrayList<String>();
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	private String filePath;
	private String nodoc;
	private String docStatus;
	private String endttypename;
	private String countryIncludedList;
	private String countryExcludedList;
	private String acqBonusName;
	private List<String> retroDupVal;
	private String proposalReference;;
	private String locBankName;
	private String locCreditPrd;
	private String locCreditAmt;
	private String locBeneficerName;
	private List<Map<String,Object>>remarkList;
	private String remarkCount;
	private List<String>remarkSNo;
	private List<String>description;
	private List<String>remark1;
	private List<String>remark2;
	private List<String> lossSNoS;
	private List<String> lossYear;
	private List<String> lossNo;
	private List<String> lossinsuredName;
	private List<String> lossInceptionDate;
	private List<String> lossExpiryDate;
	private List<String> lossDateOfLoss;
	private List<String> lossInsuredClaim;
	private List<String> lossPremium;
	private List<String> lossRatio;
	private List<String> lossLeader;
	private List<String> lossITIReShare;
	private List<String> lossCauseOfLoss;
	private List<Map<String,Object>> lossRecordList;
	private String lossCount;
	private String ceaseStatus;
	private List<Map<String,Object>> coverdeductableList;
	private List<Map<String,Object>> xolCoverdeductableList;
	
	private List<Map<String,Object>> departIdlist;
	private List<List<Map<String,Object>>> coversubDepartList=new ArrayList<List<Map<String,Object>>>();
	private List<List<Map<String,Object>>> coverxolsubDepartList=new ArrayList<List<Map<String,Object>>>();
	private List<Map<String,Object>> subProfit_centerlist;
	private List<Map<String,Object>> coverTypelist;	
	private String loopcount;
	private String xolLoopcount;
	
	private List<String> coverSNo;
	private List<String> coverdepartId;
	private List<String> coversubdepartId;
	private List<String> pmlPerList;
	private List<String> coverTypeId;
	private List<String> coverLimitOC;
	private List<String> pmlHundredPer;
	private List<String> deductableLimitOC;
	private List<String> coverageDays;
	private List<String> deductableDays;
	private List<String> premiumRateList;
	private List<String> egnpiAsPerOff;
	private List<String> coverRemark;
	private String coverdepart;
	private String id;
	private String totalCoverage;
	private String totalGWPI;
	private String multiuserMode;	
	private String shareValue;
	private String totalDeductible;
	
	private List<String> lossSum;
	private List<String> premSum;
	private List<String> ratioSum;
	private List<String> yearSum;
	private String openPeriodDate;
	private String totalCoverageVal;
	private String totalGWPIVal;
	private List<Map<String,Object>> contractTypelist;	
	private String ContractListVal;
	private List<String> xolcoverSNo;
	private List<String> xolcoverdepartId;
	private List<String> xolcoversubdepartId;
	private List<String> xolcoverLimitOC;
	private List<String> xoldeductableLimitOC;
	private List<String> xolpremiumRateList;
	private List<String> xolgwpiOC;
	
	private String mappingProposal;
	private String mapingAmendId;
	private String xollayerNo;
	
	private String xoltotalGWPI;
	
	
	private String bonusPopUp;
	private String crestaPopUp;
	
	
	public String getBonusPopUp() {
		return bonusPopUp;
	}
	public void setBonusPopUp(String bonusPopUp) {
		this.bonusPopUp = bonusPopUp;
	}
	public String getCrestaPopUp() {
		return crestaPopUp;
	}
	public void setCrestaPopUp(String crestaPopUp) {
		this.crestaPopUp = crestaPopUp;
	}
	public String getXoltotalGWPI() {
		return xoltotalGWPI;
	}
	public void setXoltotalGWPI(String xoltotalGWPI) {
		this.xoltotalGWPI = xoltotalGWPI;
	}
	public List<String> getPmlHundredPer() {
		return pmlHundredPer;
	}
	public void setPmlHundredPer(List<String> pmlHundredPer) {
		this.pmlHundredPer = pmlHundredPer;
	}
	public String getMappingProposal() {
		return mappingProposal;
	}
	public void setMappingProposal(String mappingProposal) {
		this.mappingProposal = mappingProposal;
	}
	public String getMapingAmendId() {
		return mapingAmendId;
	}
	public void setMapingAmendId(String mapingAmendId) {
		this.mapingAmendId = mapingAmendId;
	}
	public String getContractListVal() {
		return ContractListVal;
	}
	public void setContractListVal(String contractListVal) {
		ContractListVal = contractListVal;
	}
	public List<Map<String, Object>> getContractTypelist() {
		return contractTypelist;
	}
	public void setContractTypelist(List<Map<String, Object>> contractTypelist) {
		this.contractTypelist = contractTypelist;
	}
	public String getTotalGWPIVal() {
		return totalGWPIVal;
	}
	public void setTotalGWPIVal(String totalGWPIVal) {
		this.totalGWPIVal = totalGWPIVal;
	}
	public String getTotalCoverageVal() {
		return totalCoverageVal;
	}
	public void setTotalCoverageVal(String totalCoverageVal) {
		this.totalCoverageVal = totalCoverageVal;
	}
	public List<String> getPmlPerList() {
		return pmlPerList;
	}
	public void setPmlPerList(List<String> pmlPerList) {
		this.pmlPerList = pmlPerList;
	}
	public String getTotalDeductible() {
		return totalDeductible;
	}
	public void setTotalDeductible(String totalDeductible) {
		this.totalDeductible = totalDeductible;
	}
	public List<String> getYearSum() {
		return yearSum;
	}
	public void setYearSum(List<String> yearSum) {
		this.yearSum = yearSum;
	}
	public List<String> getLossSum() {
		return lossSum;
	}
	public void setLossSum(List<String> lossSum) {
		this.lossSum = lossSum;
	}
	public List<String> getPremSum() {
		return premSum;
	}
	public void setPremSum(List<String> premSum) {
		this.premSum = premSum;
	}
	public List<String> getRatioSum() {
		return ratioSum;
	}
	public void setRatioSum(List<String> ratioSum) {
		this.ratioSum = ratioSum;
	}
	public String getShareValue() {
		return shareValue;
	}
	public void setShareValue(String shareValue) {
		this.shareValue = shareValue;
	}
	public String getMultiuserMode() {
		return multiuserMode;
	}
	public void setMultiuserMode(String multiuserMode) {
		this.multiuserMode = multiuserMode;
	}
	public String getCeaseStatus() {
		return ceaseStatus;
	}
	public void setCeaseStatus(String ceaseStatus) {
		this.ceaseStatus = ceaseStatus;
	}
	public String getLossCount() {
		return lossCount;
	}
	public void setLossCount(String lossCount) {
		this.lossCount = lossCount;
	}
	public List<String> getLossCauseOfLoss() {
		return lossCauseOfLoss;
	}
	public void setLossCauseOfLoss(List<String> lossCauseOfLoss) {
		this.lossCauseOfLoss = lossCauseOfLoss;
	}
	public List<Map<String, Object>> getLossRecordList() {
		return lossRecordList;
	}
	public void setLossRecordList(List<Map<String, Object>> lossRecordList) {
		this.lossRecordList = lossRecordList;
	}
	public List<String> getLossSNoS() {
		return lossSNoS;
	}
	public void setLossSNoS(List<String> lossSNoS) {
		this.lossSNoS = lossSNoS;
	}
	public List<String> getLossYear() {
		return lossYear;
	}
	public void setLossYear(List<String> lossYear) {
		this.lossYear = lossYear;
	}
	public List<String> getLossNo() {
		return lossNo;
	}
	public void setLossNo(List<String> lossNo) {
		this.lossNo = lossNo;
	}
	public List<String> getLossinsuredName() {
		return lossinsuredName;
	}
	public void setLossinsuredName(List<String> lossinsuredName) {
		this.lossinsuredName = lossinsuredName;
	}
	public List<String> getLossInceptionDate() {
		return lossInceptionDate;
	}
	public void setLossInceptionDate(List<String> lossInceptionDate) {
		this.lossInceptionDate = lossInceptionDate;
	}
	public List<String> getLossExpiryDate() {
		return lossExpiryDate;
	}
	public void setLossExpiryDate(List<String> lossExpiryDate) {
		this.lossExpiryDate = lossExpiryDate;
	}
	public List<String> getLossDateOfLoss() {
		return lossDateOfLoss;
	}
	public void setLossDateOfLoss(List<String> lossDateOfLoss) {
		this.lossDateOfLoss = lossDateOfLoss;
	}
	public List<String> getLossInsuredClaim() {
		return lossInsuredClaim;
	}
	public void setLossInsuredClaim(List<String> lossInsuredClaim) {
		this.lossInsuredClaim = lossInsuredClaim;
	}
	public List<String> getLossPremium() {
		return lossPremium;
	}
	public void setLossPremium(List<String> lossPremium) {
		this.lossPremium = lossPremium;
	}
	public List<String> getLossRatio() {
		return lossRatio;
	}
	public void setLossRatio(List<String> lossRatio) {
		this.lossRatio = lossRatio;
	}
	public List<String> getLossLeader() {
		return lossLeader;
	}
	public void setLossLeader(List<String> lossLeader) {
		this.lossLeader = lossLeader;
	}
	public List<String> getLossITIReShare() {
		return lossITIReShare;
	}
	public void setLossITIReShare(List<String> lossITIReShare) {
		this.lossITIReShare = lossITIReShare;
	}
	public String getCountryIncludedName() {
		return countryIncludedName;
	}
	public void setCountryIncludedName(String countryIncludedName) {
		this.countryIncludedName = countryIncludedName;
	}
	public String getCountryExcludedName() {
		return countryExcludedName;
	}
	public void setCountryExcludedName(String countryExcludedName) {
		this.countryExcludedName = countryExcludedName;
	}
	public String getLocBankName() {
		return locBankName;
	}
	public void setLocBankName(String locBankName) {
		this.locBankName = locBankName;
	}
	public String getLocCreditPrd() {
		return locCreditPrd;
	}
	public void setLocCreditPrd(String locCreditPrd) {
		this.locCreditPrd = locCreditPrd;
	}
	public String getLocCreditAmt() {
		return locCreditAmt;
	}
	public void setLocCreditAmt(String locCreditAmt) {
		this.locCreditAmt = locCreditAmt;
	}
	public String getLocBeneficerName() {
		return locBeneficerName;
	}
	public void setLocBeneficerName(String locBeneficerName) {
		this.locBeneficerName = locBeneficerName;
	}
	public String getProposalReference() {
		return proposalReference;
	}
	public void setProposalReference(String proposalReference) {
		this.proposalReference = proposalReference;
	}
	public List<String> getRetroDupVal() {
		return retroDupVal;
	}
	public void setRetroDupVal(List<String> retroDupVal) {
		this.retroDupVal = retroDupVal;
	}
	public String getAcqBonusName() {
		return acqBonusName;
	}
	public void setAcqBonusName(String acqBonusName) {
		this.acqBonusName = acqBonusName;
	}
	public String getEndttypename() {
		return endttypename;
	}
	public void setEndttypename(String endttypename) {
		this.endttypename = endttypename;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getNodoc() {
		return nodoc;
	}
	public void setNodoc(String nodoc) {
		this.nodoc = nodoc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public List<Integer> getDocuList() {
		return docuList;
	}
	public void setDocuList(List<Integer> docuList) {
		this.docuList = docuList;
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
	public String getEndorsmenttype() {
		return endorsmenttype;
	}
	public void setEndorsmenttype(String endorsmenttype) {
		this.endorsmenttype = endorsmenttype;
	}
	public String getCrestaStatus() {
		return crestaStatus;
	}
	public void setCrestaStatus(String crestaStatus) {
		this.crestaStatus = crestaStatus;
	}
	public String getRetper() {
		return retper;
	}
	public void setRetper(String retper) {
		this.retper = retper;
	}
	public String getLocIssued() {
		return locIssued;
	}
	public void setLocIssued(String locIssued) {
		this.locIssued = locIssued;
	}
	public String getSumInsuredPmlOurShare() {
		return sumInsuredPmlOurShare;
	}
	public void setSumInsuredPmlOurShare(String sumInsuredPmlOurShare) {
		this.sumInsuredPmlOurShare = sumInsuredPmlOurShare;
	}
	public String getSumInsuredPml() {
		return sumInsuredPml;
	}
	public void setSumInsuredPml(String sumInsuredPml) {
		this.sumInsuredPml = sumInsuredPml;
	}
	public String getDeductibleFacXolPml() {
		return deductibleFacXolPml;
	}
	public void setDeductibleFacXolPml(String deductibleFacXolPml) {
		this.deductibleFacXolPml = deductibleFacXolPml;
	}
	public String getDeductibleFacXolPmlOurShare() {
		return deductibleFacXolPmlOurShare;
	}
	public void setDeductibleFacXolPmlOurShare(String deductibleFacXolPmlOurShare) {
		this.deductibleFacXolPmlOurShare = deductibleFacXolPmlOurShare;
	}
	public String getVessaletonnage() {
		return vessaletonnage;
	}
	public void setVessaletonnage(String vessaletonnage) {
		this.vessaletonnage = vessaletonnage;
	}
	public String getExclusion() {
		return exclusion;
	}
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}
	public String getLeader_Underwriter() {
		return leader_Underwriter;
	}
	public void setLeader_Underwriter(String leaderUnderwriter) {
		leader_Underwriter = leaderUnderwriter;
	}
	public String getLeader_Underwriter_country() {
		return leader_Underwriter_country;
	}
	public void setLeader_Underwriter_country(String leaderUnderwriterCountry) {
		leader_Underwriter_country = leaderUnderwriterCountry;
	}
	public String getLeader_Underwriter_share() {
		return leader_Underwriter_share;
	}
	public void setLeader_Underwriter_share(String leaderUnderwriterShare) {
		leader_Underwriter_share = leaderUnderwriterShare;
	}
	public String getDepartClass() {
		return departClass;
	}
	public void setDepartClass(String departClass) {
		this.departClass = departClass;
	}
	public String getPmlOC() {
		return pmlOC;
	}
	public void setPmlOC(String pmlOC) {
		this.pmlOC = pmlOC;
	}
	public String getPmlOCOurShare() {
		return pmlOCOurShare;
	}
	public void setPmlOCOurShare(String pmlOCOurShare) {
		this.pmlOCOurShare = pmlOCOurShare;
	}
	public String getPslOurShare() {
		return pslOurShare;
	}
	public void setPslOurShare(String pslOurShare) {
		this.pslOurShare = pslOurShare;
	}
	public String getPllOurShare() {
		return pllOurShare;
	}
	public void setPllOurShare(String pllOurShare) {
		this.pllOurShare = pllOurShare;
	}
	public String getPblOurShare() {
		return pblOurShare;
	}
	public void setPblOurShare(String pblOurShare) {
		this.pblOurShare = pblOurShare;
	}
	public String getGwpiPmlOurShare() {
		return gwpiPmlOurShare;
	}
	public void setGwpiPmlOurShare(String gwpiPmlOurShare) {
		this.gwpiPmlOurShare = gwpiPmlOurShare;
	}
	public String getSiPmlOurShare() {
		return siPmlOurShare;
	}
	public void setSiPmlOurShare(String siPmlOurShare) {
		this.siPmlOurShare = siPmlOurShare;
	}
	public String getInwardType() {
		return inwardType;
	}
	public void setInwardType(String inwardType) {
		this.inwardType = inwardType;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	public String getGwpiPml() {
		return gwpiPml;
	}
	public void setGwpiPml(String gwpiPml) {
		this.gwpiPml = gwpiPml;
	}
	public String getReceiptofPayment() {
		return receiptofPayment;
	}
	public void setReceiptofPayment(String receiptofPayment) {
		this.receiptofPayment = receiptofPayment;
	}
	public String getPslOC() {
		return pslOC;
	}
	public void setPslOC(String pslOC) {
		this.pslOC = pslOC;
	}
	public String getPllOC() {
		return pllOC;
	}
	public void setPllOC(String pllOC) {
		this.pllOC = pllOC;
	}
	public String getPblOC() {
		return pblOC;
	}
	public void setPblOC(String pblOC) {
		this.pblOC = pblOC;
	}
	public String getCountryIncluded() {
		return countryIncluded;
	}
	public void setCountryIncluded(String countryIncluded) {
		this.countryIncluded = countryIncluded;
	}
	public String getCountryExcluded() {
		return countryExcluded;
	}
	public void setCountryExcluded(String countryExcluded) {
		this.countryExcluded = countryExcluded;
	}
	public String getPreviousSelectTerritory() {
		return previousSelectTerritory;
	}
	public void setPreviousSelectTerritory(String previousSelectTerritory) {
		this.previousSelectTerritory = previousSelectTerritory;
	}
	
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}
	public String getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
	public String getRenewalProposalNo() {
		return renewalProposalNo;
	}
	public void setRenewalProposalNo(String renewalProposalNo) {
		this.renewalProposalNo = renewalProposalNo;
	}
	public String getAcqBonus() {
		return acqBonus;
	}
	public void setAcqBonus(String acqBonus) {
		this.acqBonus = acqBonus;
	}
	public String getAcqBonusPercentage() {
		return acqBonusPercentage;
	}
	public void setAcqBonusPercentage(String acqBonusPercentage) {
		this.acqBonusPercentage = acqBonusPercentage;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	public String getUWName() {
		return UWName;
	}
	public void setUWName(String uWName) {
		UWName = uWName;
	}
	public String getReMode() {
		return reMode;
	}
	public void setReMode(String reMode) {
		this.reMode = reMode;
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
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	public String getPreviousendoDate() {
		return previousendoDate;
	}
	public void setPreviousendoDate(String previousendoDate) {
		this.previousendoDate = previousendoDate;
	}
	public String getEndtMode() {
		return endtMode;
	}
	public void setEndtMode(String endtMode) {
		this.endtMode = endtMode;
	}
	public String getEndorsementDate() {
		return endorsementDate;
	}
	public void setEndorsementDate(String endorsementDate) {
		this.endorsementDate = endorsementDate;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	private List<Map<String,Object>> profit_Center;
	private List<Map<String,Object>> subProfit_center;
	private List<Map<String,Object>> UnderWritter;
	private List<Map<String,Object>> departId;
	private List<Map<String,Object>> polBr;
	private List<Map<String,Object>> brokers;
	private List<Map<String,Object>> proposal;
	private List<Map<String,Object>> EQWSIndDrop;
	private List<Map<String,Object>> TypeDropDown;
	private List<Map<String,Object>> orginalCurrency;
	private List<Map<String,Object>> Territorty;
	private List<Map<String,Object>> CeddingCompany;
	private List<Map<String,Object>> modeOfTransports;
	private List<Map<String,Object>> yearList;
	private List<Map<String,Object>> yearToList;
	private List<Map<String,Object>> categoryList;
	private List<Map<String,Object>> riskGradeList;
	private List<Map<String,Object>> risklist1;
	private List<Map<String,Object>> category;
	private List<Map<String,Object>> uwYearList;
	private List<Map<String,Object>> cedingCompanyList;
	private List<List<Map<String,Object>>> uwYearCList;
	private List<List<Map<String,Object>>> cedingCompanyCList;
	List<List<Map<String,Object>>> uwlList;
	List<List<Map<String,Object>>> retrolList;
	List<List<Map<String,Object>>> retroDupList;
	private List<Map<String,Object>> bonusList;
	private List<Map<String,Object>> lowClaimBonusList;	
	private List<String> instalList;
	private List<String> instalmentDateList;
	private List<String> installmentPremium;
	private List<String> retroTypeValList;
	private List<String> uwYearValList;
	private List<String> cedingCompanyValList;
	private List<String> retroPercentage;
	private List<String> transactionList;
	private List<String> paymentDueDays;
    private List<String> bonusFrom ;
    private List<String> bonusTo ;
    private List<String> bonusLowClaimBonus ;
    private List<String> bonusSNo;
    private String bonusTypeId;
	private String endIndex;
	private String startIndex;
	private String reference;
	private String multiuserError;
	private String renewalEditMode;
	private String retroDupType;
	private String retroDupContract;
	private String retroDupYerar;
	private String retroDupMode;
	
	
	
	public List<Map<String, Object>> getYearToList() {
		return yearToList;
	}
	public void setYearToList(List<Map<String, Object>> yearToList) {
		this.yearToList = yearToList;
	}
	public String getRetroDupMode() {
		return retroDupMode;
	}
	public void setRetroDupMode(String retroDupMode) {
		this.retroDupMode = retroDupMode;
	}
	public String getRetroDupType() {
		return retroDupType;
	}
	public void setRetroDupType(String retroDupType) {
		this.retroDupType = retroDupType;
	}
	public String getRetroDupContract() {
		return retroDupContract;
	}
	public void setRetroDupContract(String retroDupContract) {
		this.retroDupContract = retroDupContract;
	}
	public String getRetroDupYerar() {
		return retroDupYerar;
	}
	public void setRetroDupYerar(String retroDupYerar) {
		this.retroDupYerar = retroDupYerar;
	}
	public String getRenewalEditMode() {
		return renewalEditMode;
	}
	public void setRenewalEditMode(String renewalEditMode) {
		this.renewalEditMode = renewalEditMode;
	}
	public String getMultiuserError() {
		return multiuserError;
	}
	public void setMultiuserError(String multiuserError) {
		this.multiuserError = multiuserError;
	}
	public List<List<Map<String, Object>>> getRetroDupList() {
		return retroDupList;
	}
	public void setRetroDupList(List<List<Map<String, Object>>> retroDupList) {
		this.retroDupList = retroDupList;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public List<String> getPaymentDueDays() {
		return paymentDueDays;
	}
	public void setPaymentDueDays(List<String> paymentDueDays) {
		this.paymentDueDays = paymentDueDays;
	}
	public List<String> getBonusSNo() {
		return bonusSNo;
	}
	public void setBonusSNo(List<String> bonusSNo) {
		this.bonusSNo = bonusSNo;
	}
	public String getBonusTypeId() {
		return bonusTypeId;
	}
	public void setBonusTypeId(String bonusTypeId) {
		this.bonusTypeId = bonusTypeId;
	}
	public List<String> getBonusFrom() {
		return bonusFrom;
	}
	public void setBonusFrom(List<String> bonusFrom) {
		this.bonusFrom = bonusFrom;
	}
	public List<String> getBonusTo() {
		return bonusTo;
	}
	public void setBonusTo(List<String> bonusTo) {
		this.bonusTo = bonusTo;
	}
	public List<String> getBonusLowClaimBonus() {
		return bonusLowClaimBonus;
	}
	public void setBonusLowClaimBonus(List<String> bonusLowClaimBonus) {
		this.bonusLowClaimBonus = bonusLowClaimBonus;
	}
	public List<Map<String, Object>> getLowClaimBonusList() {
		return lowClaimBonusList;
	}
	public void setLowClaimBonusList(List<Map<String, Object>> lowClaimBonusList) {
		this.lowClaimBonusList = lowClaimBonusList;
	}
	public List<Map<String, Object>> getBonusList() {
		return bonusList;
	}
	public void setBonusList(List<Map<String, Object>> bonusList) {
		this.bonusList = bonusList;
	}
	public String getRenewalMode() {
		return renewalMode;
	}
	public void setRenewalMode(String renewalMode) {
		this.renewalMode = renewalMode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Map<String, Object>> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Map<String, Object>> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Map<String, Object>> getRiskGradeList() {
		return riskGradeList;
	}

	public void setRiskGradeList(List<Map<String, Object>> riskGradeList) {
		this.riskGradeList = riskGradeList;
	}

	public List<Map<String, Object>> getYearList() {
		return yearList;
	}

	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}

	public List<Map<String, Object>> getModeOfTransports() {
		return modeOfTransports;
	}

	public void setModeOfTransports(List<Map<String, Object>> modeOfTransports) {
		this.modeOfTransports = modeOfTransports;
	}

	public List<Map<String, Object>> getProfit_Center() {
		return profit_Center;
	}

	public void setProfit_Center(List<Map<String, Object>> profitCenter) {
		profit_Center = profitCenter;
	}

	public List<Map<String, Object>> getSubProfit_center() {
		return subProfit_center;
	}

	public void setSubProfit_center(List<Map<String, Object>> subProfitCenter) {
		subProfit_center = subProfitCenter;
	}

	public List<Map<String, Object>> getUnderWritter() {
		return UnderWritter;
	}

	public void setUnderWritter(List<Map<String, Object>> underWritter) {
		UnderWritter = underWritter;
	}

	public List<Map<String, Object>> getDepartId() {
		return departId;
	}

	public void setDepartId(List<Map<String, Object>> departId) {
		this.departId = departId;
	}

	public List<Map<String, Object>> getPolBr() {
		return polBr;
	}

	public void setPolBr(List<Map<String, Object>> polBr) {
		this.polBr = polBr;
	}

	public List<Map<String, Object>> getBrokers() {
		return brokers;
	}

	public void setBrokers(List<Map<String, Object>> brokers) {
		this.brokers = brokers;
	}

	public List<Map<String, Object>> getProposal() {
		return proposal;
	}

	public void setProposal(List<Map<String, Object>> proposal) {
		this.proposal = proposal;
	}

	public List<Map<String, Object>> getEQWSIndDrop() {
		return EQWSIndDrop;
	}

	public void setEQWSIndDrop(List<Map<String, Object>> eQWSIndDrop) {
		EQWSIndDrop = eQWSIndDrop;
	}

	public List<Map<String, Object>> getTypeDropDown() {
		return TypeDropDown;
	}

	public void setTypeDropDown(List<Map<String, Object>> typeDropDown) {
		TypeDropDown = typeDropDown;
	}

	public List<Map<String, Object>> getOrginalCurrency() {
		return orginalCurrency;
	}

	public void setOrginalCurrency(List<Map<String, Object>> orginalCurrency) {
		this.orginalCurrency = orginalCurrency;
	}

	public List<Map<String, Object>> getTerritorty() {
		return Territorty;
	}

	public void setTerritorty(List<Map<String, Object>> territorty) {
		Territorty = territorty;
	}

	public List<Map<String, Object>> getCeddingCompany() {
		return CeddingCompany;
	}

	public void setCeddingCompany(List<Map<String, Object>> ceddingCompany) {
		CeddingCompany = ceddingCompany;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmendId() {
		return amendId;
	}

	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public boolean isPrclFlag() {
		return prclFlag;
	}

	public void setPrclFlag(boolean prclFlag) {
		this.prclFlag = prclFlag;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getVesselAge() {
		return vesselAge;
	}

	public void setVesselAge(String vesselAge) {
		this.vesselAge = vesselAge;
	}

	public String getLimitPerVesselOC() {
		return limitPerVesselOC;
	}

	public void setLimitPerVesselOC(String limitPerVesselOC) {
		this.limitPerVesselOC = limitPerVesselOC;
	}

	public String getLimitPerLocationOC() {
		return limitPerLocationOC;
	}

	public void setLimitPerLocationOC(String limitPerLocationOC) {
		this.limitPerLocationOC = limitPerLocationOC;
	}

	public String getLimitPerVesselDC() {
		return limitPerVesselDC;
	}

	public void setLimitPerVesselDC(String limitPerVesselDC) {
		this.limitPerVesselDC = limitPerVesselDC;
	}

	public String getLimitPerLocationDC() {
		return limitPerLocationDC;
	}

	public void setLimitPerLocationDC(String limitPerLocationDC) {
		this.limitPerLocationDC = limitPerLocationDC;
	}

	public String getRenewalStatus() {
		return renewalStatus;
	}

	public void setRenewalStatus(String renewalStatus) {
		this.renewalStatus = renewalStatus;
	}

	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRetroType() {
		return retroType;
	}

	public void setRetroType(String retroType) {
		this.retroType = retroType;
	}

	public String getSiOSViewOC() {
		return siOSViewOC;
	}

	public void setSiOSViewOC(String siOSViewOC) {
		this.siOSViewOC = siOSViewOC;
	}

	public String getSiOSViewDC() {
		return siOSViewDC;
	}

	public void setSiOSViewDC(String siOSViewDC) {
		this.siOSViewDC = siOSViewDC;
	}

	public String getPmlOSViewOC() {
		return pmlOSViewOC;
	}

	public void setPmlOSViewOC(String pmlOSViewOC) {
		this.pmlOSViewOC = pmlOSViewOC;
	}

	public String getPmlOSViewDC() {
		return pmlOSViewDC;
	}

	public void setPmlOSViewDC(String pmlOSViewDC) {
		this.pmlOSViewDC = pmlOSViewDC;
	}

	public String getGwpiOSViewOC() {
		return gwpiOSViewOC;
	}

	public void setGwpiOSViewOC(String gwpiOSViewOC) {
		this.gwpiOSViewOC = gwpiOSViewOC;
	}

	public String getGwpiOSViewDC() {
		return gwpiOSViewDC;
	}

	public void setGwpiOSViewDC(String gwpiOSViewDC) {
		this.gwpiOSViewDC = gwpiOSViewDC;
	}

	public String getTplOSViewOC() {
		return tplOSViewOC;
	}

	public void setTplOSViewOC(String tplOSViewOC) {
		this.tplOSViewOC = tplOSViewOC;
	}

	public String getTplOSViewDC() {
		return tplOSViewDC;
	}

	public void setTplOSViewDC(String tplOSViewDC) {
		this.tplOSViewDC = tplOSViewDC;
	}

	public String getCedRetenType() {
		return cedRetenType;
	}

	public void setCedRetenType(String cedRetenType) {
		this.cedRetenType = cedRetenType;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode
	 *            the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getPolicyBranch() {
		return policyBranch;
	}

	public void setPolicyBranch(String policyBranch) {
		this.policyBranch = policyBranch;
	}

	public String getAcqCostPer() {
		return acqCostPer;
	}

	public void setAcqCostPer(String acqCostPer) {
		this.acqCostPer = acqCostPer;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getRisklist() {
		return risklist;
	}

	public void setRisklist(String risklist) {
		this.risklist = risklist;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getNo_Insurer() {
		return no_Insurer;
	}

	public void setNo_Insurer(String noInsurer) {
		no_Insurer = noInsurer;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getAcqCost() {
		return acqCost;
	}

	public void setAcqCost(String acqCost) {
		this.acqCost = acqCost;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getMbind() {
		return mbind;
	}

	public void setMbind(String mbind) {
		this.mbind = mbind;
	}

	public String getMlopYN() {
		return mlopYN;
	}

	public void setMlopYN(String mlopYN) {
		this.mlopYN = mlopYN;
	}

	public String getAlopYN() {
		return alopYN;
	}

	public void setAlopYN(String alopYN) {
		this.alopYN = alopYN;
	}

	// First Form End ...........

	public String getAccusd() {
		return accusd;
	}

	public void setAccusd(String accusd) {
		this.accusd = accusd;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	// Second Form bean

	/**
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
	 * @param territoryName
	 *            the territoryName to set
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	
	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getPremiurate() {
		return premiurate;
	}

	public void setPremiurate(String premiurate) {
		this.premiurate = premiurate;
	}

	public String getPremiumrate() {
		return premiumrate;
	}

	public void setPremiumrate(String premiumrate) {
		this.premiumrate = premiumrate;
	}

	public String getOthercost() {
		return othercost;
	}

	public void setOthercost(String othercost) {
		this.othercost = othercost;
	}

	public String getBroke() {
		return broke;
	}

	public void setBroke(String broke) {
		this.broke = broke;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackmode() {
		return backmode;
	}

	public void setBackmode(String backmode) {
		this.backmode = backmode;
	}

	public String getContractGendration() {
		return contractGendration;
	}

	public void setContractGendration(String contractGendration) {
		this.contractGendration = contractGendration;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCedingCompany() {
		return cedingCompany;
	}

	public void setCedingCompany(String cedingCompany) {
		this.cedingCompany = cedingCompany;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOriginalCurrency() {
		return originalCurrency;
	}

	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}

	public String getUsCurrencyRate() {
		return usCurrencyRate;
	}

	public void setUsCurrencyRate(String usCurrencyRate) {
		this.usCurrencyRate = usCurrencyRate;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getProfitCenterCode() {
		return profitCenterCode;
	}

	public void setProfitCenterCode(String profitCenterCode) {
		this.profitCenterCode = profitCenterCode;
	}

	public String getSubProfitCenter() {
		return subProfitCenter;
	}

	public void setSubProfitCenter(String subProfitCenter) {
		this.subProfitCenter = subProfitCenter;
	}

	public String getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public String getInceptionMonth() {
		return inceptionMonth;
	}

	public void setInceptionMonth(String inceptionMonth) {
		this.inceptionMonth = inceptionMonth;
	}

	public String getInceptionYear() {
		return inceptionYear;
	}

	public void setInceptionYear(String inceptionYear) {
		this.inceptionYear = inceptionYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getAccountMonth() {
		return accountMonth;
	}

	public void setAccountMonth(String accountMonth) {
		this.accountMonth = accountMonth;
	}

	public String getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(String accountYear) {
		this.accountYear = accountYear;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUnderwriter() {
		return underwriter;
	}

	public void setUnderwriter(String underwriter) {
		this.underwriter = underwriter;
	}

	public String getCedantsRet() {
		return cedantsRet;
	}

	public void setCedantsRet(String cedantsRet) {
		this.cedantsRet = cedantsRet;
	}

	public String getMaxiumlimit() {
		return maxiumlimit;
	}

	public void setMaxiumlimit(String maxiumlimit) {
		this.maxiumlimit = maxiumlimit;
	}

	public String getDeductible() {
		return deductible;
	}

	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}

	public String getDeductibleFacXol() {
		return deductibleFacXol;
	}

	public void setDeductibleFacXol(String deductibleFacXol) {
		this.deductibleFacXol = deductibleFacXol;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getSpRetro() {
		return spRetro;
	}

	public void setSpRetro(String spRetro) {
		this.spRetro = spRetro;
	}

	public String getPml() {
		return pml;
	}

	public void setPml(String pml) {
		this.pml = pml;
	}

	public String getSipml() {
		return sipml;
	}

	public void setSipml(String sipml) {
		this.sipml = sipml;
	}

	public String getCu() {
		return cu;
	}

	public void setCu(String cu) {
		this.cu = cu;
	}

	public String getCuRsn() {
		return cuRsn;
	}

	public void setCuRsn(String cuRsn) {
		this.cuRsn = cuRsn;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getGwpi() {
		return gwpi;
	}

	public void setGwpi(String gwpi) {
		this.gwpi = gwpi;
	}

	public String getXolOC() {
		return xolOC;
	}

	public void setXolOC(String xolOC) {
		this.xolOC = xolOC;
	}

	public String getXolDC() {
		return xolDC;
	}

	public void setXolDC(String xolDC) {
		this.xolDC = xolDC;
	}

	public String getXolOSOC() {
		return xolOSOC;
	}

	public void setXolOSOC(String xolOSOC) {
		this.xolOSOC = xolOSOC;
	}

	public String getXolOSDC() {
		return xolOSDC;
	}

	public void setXolOSDC(String xolOSDC) {
		this.xolOSDC = xolOSDC;
	}

	public String getXolOSViewOC() {
		return xolOSViewOC;
	}

	public void setXolOSViewOC(String xolOSViewOC) {
		this.xolOSViewOC = xolOSViewOC;
	}

	public String getXolOSViewDC() {
		return xolOSViewDC;
	}

	public void setXolOSViewDC(String xolOSViewDC) {
		this.xolOSViewDC = xolOSViewDC;
	}

	public String getNoOfInst() {
		return noOfInst;
	}

	public void setNoOfInst(String noOfInst) {
		this.noOfInst = noOfInst;
	}

	public String getPmll() {
		return pmll;
	}

	public void setPmll(String pmll) {
		this.pmll = pmll;
	}

	public String getShWt() {
		return shWt;
	}

	public void setShWt(String shWt) {
		this.shWt = shWt;
	}

	public String getShSd() {
		return shSd;
	}

	public void setShSd(String shSd) {
		this.shSd = shSd;
	}

	public String getFacultativeDepartment() {
		return facultativeDepartment;
	}

	public void setFacultativeDepartment(String facultativeDepartment) {
		this.facultativeDepartment = facultativeDepartment;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	public String getSumInsuredOurShare() {
		return sumInsuredOurShare;
	}

	public void setSumInsuredOurShare(String sumInsuredOurShare) {
		this.sumInsuredOurShare = sumInsuredOurShare;
	}

	public String getGwpiOurShare() {
		return gwpiOurShare;
	}

	public void setGwpiOurShare(String gwpiOurShare) {
		this.gwpiOurShare = gwpiOurShare;
	}

	public String getPmlOurShare() {
		return pmlOurShare;
	}

	public void setPmlOurShare(String pmlOurShare) {
		this.pmlOurShare = pmlOurShare;
	}

	public String getRiskGrade() {
		return riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}

	public String getOccCode() {
		return occCode;
	}

	public void setOccCode(String occCode) {
		this.occCode = occCode;
	}

	public String getRiskDetail() {
		return riskDetail;
	}

	public void setRiskDetail(String riskDetail) {
		this.riskDetail = riskDetail;
	}

	public String getFireProt() {
		return fireProt;
	}

	public void setFireProt(String fireProt) {
		this.fireProt = fireProt;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getEqwsInd() {
		return eqwsInd;
	}

	public void setEqwsInd(String eqwsInd) {
		this.eqwsInd = eqwsInd;
	}

	public String getCategoryZone() {
		return categoryZone;
	}

	public void setCategoryZone(String categoryZone) {
		this.categoryZone = categoryZone;
	}

	public String getEqThreat() {
		return eqThreat;
	}

	public void setEqThreat(String eqThreat) {
		this.eqThreat = eqThreat;
	}

	public String getWsThreat() {
		return wsThreat;
	}

	public void setWsThreat(String wsThreat) {
		this.wsThreat = wsThreat;
	}

	public String getCommn() {
		return commn;
	}

	public void setCommn(String commn) {
		this.commn = commn;
	}

	public String getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getLossRecord() {
		return lossRecord;
	}

	public void setLossRecord(String lossRecord) {
		this.lossRecord = lossRecord;
	}

	public String getDgmsApproval() {
		return dgmsApproval;
	}

	public void setDgmsApproval(String dgmsApproval) {
		this.dgmsApproval = dgmsApproval;
	}

	public String getUnderwriterCode() {
		return underwriterCode;
	}

	public void setUnderwriterCode(String underwriterCode) {
		this.underwriterCode = underwriterCode;
	}

	public String getUwRecommendation() {
		return uwRecommendation;
	}

	public void setUwRecommendation(String uwRecommendation) {
		this.uwRecommendation = uwRecommendation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOthAccep() {
		return othAccep;
	}

	public void setOthAccep(String othAccep) {
		this.othAccep = othAccep;
	}

	public String getReftoHO() {
		return reftoHO;
	}

	public void setReftoHO(String reftoHO) {
		this.reftoHO = reftoHO;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getTplOurShare() {
		return tplOurShare;
	}

	public void setTplOurShare(String tplOurShare) {
		this.tplOurShare = tplOurShare;
	}

	public String getConmode() {
		return conmode;
	}

	public void setConmode(String conmode) {
		this.conmode = conmode;
	}

	// private String renewal_ProposalNo;

	/*
	 * public String getRenewal_ProposalNo() { return renewal_ProposalNo; }
	 * 
	 * public void setRenewal_ProposalNo(String renewalProposalNo) {
	 * renewal_ProposalNo = renewalProposalNo; }
	 */

	public String getRenewal_Contract_no() {
		return renewal_Contract_no;
	}

	public void setRenewal_Contract_no(String renewalContractNo) {
		renewal_Contract_no = renewalContractNo;
	}

	public String getTplOurshareusd() {
		return tplOurshareusd;
	}

	public void setTplOurshareusd(String tplOurshareusd) {
		this.tplOurshareusd = tplOurshareusd;
	}

	public String getSumusd() {
		return sumusd;
	}

	public void setSumusd(String sumusd) {
		this.sumusd = sumusd;
	}

	public String getTplusd() {
		return tplusd;
	}

	public void setTplusd(String tplusd) {
		this.tplusd = tplusd;
	}

	public String getGwpiUsd() {
		return gwpiUsd;
	}

	public void setGwpiUsd(String gwpiUsd) {
		this.gwpiUsd = gwpiUsd;
	}

	public String getSumOrginalUsd() {
		return sumOrginalUsd;
	}

	public void setSumOrginalUsd(String sumOrginalUsd) {
		this.sumOrginalUsd = sumOrginalUsd;
	}

	public String getPmlOurShareusd() {
		return pmlOurShareusd;
	}

	public void setPmlOurShareusd(String pmlOurShareusd) {
		this.pmlOurShareusd = pmlOurShareusd;
	}

	public String getGwpiOurShareusd() {
		return gwpiOurShareusd;
	}

	public void setGwpiOurShareusd(String gwpiOurShareusd) {
		this.gwpiOurShareusd = gwpiOurShareusd;
	}

	public String getPmlusd() {
		return pmlusd;
	}

	public void setPmlusd(String pmlusd) {
		this.pmlusd = pmlusd;
	}

	public String getBaseLoginID() {
		return baseLoginID;
	}

	public void setBaseLoginID(String baseLoginID) {
		this.baseLoginID = baseLoginID;
	}

	public String getDeductibleDC() {
		return deductibleDC;
	}

	public void setDeductibleDC(String deductibleDC) {
		this.deductibleDC = deductibleDC;
	}

	public String getDeductibleFacXolDC() {
		return deductibleFacXolDC;
	}

	public void setDeductibleFacXolDC(String deductibleFacXolDC) {
		this.deductibleFacXolDC = deductibleFacXolDC;
	}
	public List<String> getInstalList() {
		return instalList;
	}
	public void setInstalList(List<String> instalList) {
		this.instalList = instalList;
	}
	public List<String> getInstalmentDateList() {
		return instalmentDateList;
	}
	public void setInstalmentDateList(List<String> instalmentDateList) {
		this.instalmentDateList = instalmentDateList;
	}
	public List<String> getInstallmentPremium() {
		return installmentPremium;
	}
	public void setInstallmentPremium(List<String> installmentPremium) {
		this.installmentPremium = installmentPremium;
	}
	public String getProposal_no() {
		return proposal_no;
	}
	public void setProposal_no(String proposalNo) {
		proposal_no = proposalNo;
	}
	public List<Map<String, Object>> getRisklist1() {
		return risklist1;
	}
	public void setRisklist1(List<Map<String, Object>> risklist1) {
		this.risklist1 = risklist1;
	}
	public List<Map<String, Object>> getCategory() {
		return category;
	}
	public void setCategory(List<Map<String, Object>> category) {
		this.category = category;
	}
	public String getContarctno() {
		return contarctno;
	}
	public void setContarctno(String contarctno) {
		this.contarctno = contarctno;
	}
	public List<String> getRetroTypeValList() {
		return retroTypeValList;
	}
	public void setRetroTypeValList(List<String> retroTypeValList) {
		this.retroTypeValList = retroTypeValList;
	}
	public List<String> getUwYearValList() {
		return uwYearValList;
	}
	public void setUwYearValList(List<String> uwYearValList) {
		this.uwYearValList = uwYearValList;
	}
	public List<String> getCedingCompanyValList() {
		return cedingCompanyValList;
	}
	public void setCedingCompanyValList(List<String> cedingCompanyValList) {
		this.cedingCompanyValList = cedingCompanyValList;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public List<Map<String, Object>> getUwYearList() {
		return uwYearList;
	}
	public void setUwYearList(List<Map<String, Object>> uwYearList) {
		this.uwYearList = uwYearList;
	}
	public List<Map<String, Object>> getCedingCompanyList() {
		return cedingCompanyList;
	}
	public void setCedingCompanyList(List<Map<String, Object>> cedingCompanyList) {
		this.cedingCompanyList = cedingCompanyList;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getEndorsmentno() {
		return endorsmentno;
	}
	public void setEndorsmentno(String endorsmentno) {
		this.endorsmentno = endorsmentno;
	}
	public String getRetentionPercentage() {
		return RetentionPercentage;
	}
	public void setRetentionPercentage(String retentionPercentage) {
		RetentionPercentage = retentionPercentage;
	}
	public List<String> getRetroPercentage() {
		return retroPercentage;
	}
	public void setRetroPercentage(List<String> retroPercentage) {
		this.retroPercentage = retroPercentage;
	}
	public List<List<Map<String, Object>>> getUwYearCList() {
		return uwYearCList==null?new ArrayList<List<Map<String,Object>>>():uwYearCList;
	}
	public void setUwYearCList(List<List<Map<String, Object>>> uwYearCList) {
		this.uwYearCList = uwYearCList;
	}
	public List<List<Map<String, Object>>> getCedingCompanyCList() {
		return cedingCompanyCList==null?new ArrayList<List<Map<String,Object>>>():cedingCompanyCList;
	}
	public void setCedingCompanyCList(
			List<List<Map<String, Object>>> cedingCompanyCList) {
		this.cedingCompanyCList = cedingCompanyCList;
	}
	public String getEndorsementStatus() {
		return endorsementStatus;
	}
	public void setEndorsementStatus(String endorsementStatus) {
		this.endorsementStatus = endorsementStatus;
	}
	public List<List<Map<String, Object>>> getUwlList() {
		return uwlList;
	}
	public void setUwlList(List<List<Map<String, Object>>> uwlList) {
		this.uwlList = uwlList;
	}
	public List<List<Map<String, Object>>> getRetrolList() {
		return retrolList;
	}
	public void setRetrolList(List<List<Map<String, Object>>> retrolList) {
		this.retrolList = retrolList;
	}
	public String getDeductibleOSOC() {
		return deductibleOSOC;
	}
	public void setDeductibleOSOC(String deductibleOSOC) {
		this.deductibleOSOC = deductibleOSOC;
	}
	public String getDeductibleOSDC() {
		return deductibleOSDC;
	}
	public void setDeductibleOSDC(String deductibleOSDC) {
		this.deductibleOSDC = deductibleOSDC;
	}
	public String getCoverlimitOSOC() {
		return coverlimitOSOC;
	}
	public void setCoverlimitOSOC(String coverlimitOSOC) {
		this.coverlimitOSOC = coverlimitOSOC;
	}
	public String getCoverlimitOSDC() {
		return coverlimitOSDC;
	}
	public void setCoverlimitOSDC(String coverlimitOSDC) {
		this.coverlimitOSDC = coverlimitOSDC;
	}
	public String getDeductibleOurShare() {
		return deductibleOurShare;
	}
	public void setDeductibleOurShare(String deductibleOurShare) {
		this.deductibleOurShare = deductibleOurShare;
	}
	public String getCoverlimitOurShare() {
		return coverlimitOurShare;
	}
	public void setCoverlimitOurShare(String coverlimitOurShare) {
		this.coverlimitOurShare = coverlimitOurShare;
	}
	public String getDeductibleOurShareusd() {
		return deductibleOurShareusd;
	}
	public void setDeductibleOurShareusd(String deductibleOurShareusd) {
		this.deductibleOurShareusd = deductibleOurShareusd;
	}
	public String getCoverlimitOurShareusd() {
		return coverlimitOurShareusd;
	}
	public void setCoverlimitOurShareusd(String coverlimitOurShareusd) {
		this.coverlimitOurShareusd = coverlimitOurShareusd;
	}
	public List<String> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<String> transactionList) {
		this.transactionList = transactionList;
	}
	public String getCountryIncludedList() {
		return countryIncludedList;
	}
	public void setCountryIncludedList(String countryIncludedList) {
		this.countryIncludedList = countryIncludedList;
	}
	public String getCountryExcludedList() {
		return countryExcludedList;
	}
	public void setCountryExcludedList(String countryExcludedList) {
		this.countryExcludedList = countryExcludedList;
	}
	public String getPslusd() {
		return pslusd;
	}
	public void setPslusd(String pslusd) {
		this.pslusd = pslusd;
	}
	public String getPslOurShareusd() {
		return pslOurShareusd;
	}
	public void setPslOurShareusd(String pslOurShareusd) {
		this.pslOurShareusd = pslOurShareusd;
	}
	public String getPllusd() {
		return pllusd;
	}
	public void setPllusd(String pllusd) {
		this.pllusd = pllusd;
	}
	public String getPllOurShareusd() {
		return pllOurShareusd;
	}
	public void setPllOurShareusd(String pllOurShareusd) {
		this.pllOurShareusd = pllOurShareusd;
	}
	public String getPblusd() {
		return pblusd;
	}
	public void setPblusd(String pblusd) {
		this.pblusd = pblusd;
	}
	public String getPblOurShareusd() {
		return pblOurShareusd;
	}
	public void setPblOurShareusd(String pblOurShareusd) {
		this.pblOurShareusd = pblOurShareusd;
	}
	public List<Map<String, Object>> getRemarkList() {
		return remarkList;
	}
	public void setRemarkList(List<Map<String, Object>> remarkList) {
		this.remarkList = remarkList;
	}
	public String getRemarkCount() {
		return remarkCount;
	}
	public void setRemarkCount(String remarkCount) {
		this.remarkCount = remarkCount;
	}
	public List<String> getRemarkSNo() {
		return remarkSNo;
	}
	public void setRemarkSNo(List<String> remarkSNo) {
		this.remarkSNo = remarkSNo;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public List<String> getRemark1() {
		return remark1;
	}
	public void setRemark1(List<String> remark1) {
		this.remark1 = remark1;
	}
	public List<String> getRemark2() {
		return remark2;
	}
	public void setRemark2(List<String> remark2) {
		this.remark2 = remark2;
	}
	public List<Map<String, Object>> getCoverdeductableList() {
		return coverdeductableList;
	}
	public void setCoverdeductableList(List<Map<String, Object>> coverdeductableList) {
		this.coverdeductableList = coverdeductableList;
	}
	public List<Map<String, Object>> getDepartIdlist() {
		return departIdlist;
	}
	public void setDepartIdlist(List<Map<String, Object>> departIdlist) {
		this.departIdlist = departIdlist;
	}
	public List<List<Map<String, Object>>> getCoversubDepartList() {
		return coversubDepartList;
	}
	public void setCoversubDepartList(
			List<List<Map<String, Object>>> coversubDepartList) {
		this.coversubDepartList = coversubDepartList;
	}
	public String getLoopcount() {
		return loopcount;
	}
	public void setLoopcount(String loopcount) {
		this.loopcount = loopcount;
	}
	public List<Map<String, Object>> getCoverTypelist() {
		return coverTypelist;
	}
	public void setCoverTypelist(List<Map<String, Object>> coverTypelist) {
		this.coverTypelist = coverTypelist;
	}
	public List<String> getCoverSNo() {
		return coverSNo;
	}
	public void setCoverSNo(List<String> coverSNo) {
		this.coverSNo = coverSNo;
	}
	public List<String> getCoverdepartId() {
		return coverdepartId;
	}
	public void setCoverdepartId(List<String> coverdepartId) {
		this.coverdepartId = coverdepartId;
	}
	public List<String> getCoversubdepartId() {
		return coversubdepartId;
	}
	public void setCoversubdepartId(List<String> coversubdepartId) {
		this.coversubdepartId = coversubdepartId;
	}
	public List<String> getCoverTypeId() {
		return coverTypeId;
	}
	public void setCoverTypeId(List<String> coverTypeId) {
		this.coverTypeId = coverTypeId;
	}
	public List<String> getCoverLimitOC() {
		return coverLimitOC;
	}
	public void setCoverLimitOC(List<String> coverLimitOC) {
		this.coverLimitOC = coverLimitOC;
	}
	public List<String> getDeductableLimitOC() {
		return deductableLimitOC;
	}
	public void setDeductableLimitOC(List<String> deductableLimitOC) {
		this.deductableLimitOC = deductableLimitOC;
	}
	public List<String> getCoverageDays() {
		return coverageDays;
	}
	public void setCoverageDays(List<String> coverageDays) {
		this.coverageDays = coverageDays;
	}
	public List<String> getDeductableDays() {
		return deductableDays;
	}
	public void setDeductableDays(List<String> deductableDays) {
		this.deductableDays = deductableDays;
	}
	public List<String> getPremiumRateList() {
		return premiumRateList;
	}
	public void setPremiumRateList(List<String> premiumRateList) {
		this.premiumRateList = premiumRateList;
	}
	public List<String> getEgnpiAsPerOff() {
		return egnpiAsPerOff;
	}
	public void setEgnpiAsPerOff(List<String> egnpiAsPerOff) {
		this.egnpiAsPerOff = egnpiAsPerOff;
	}
	public String getCoverdepart() {
		return coverdepart;
	}
	public void setCoverdepart(String coverdepart) {
		this.coverdepart = coverdepart;
	}
	public List<Map<String, Object>> getSubProfit_centerlist() {
		return subProfit_centerlist;
	}
	public void setSubProfit_centerlist(
			List<Map<String, Object>> subProfitCenterlist) {
		subProfit_centerlist = subProfitCenterlist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTotalCoverage() {
		return totalCoverage;
	}
	public void setTotalCoverage(String totalCoverage) {
		this.totalCoverage = totalCoverage;
	}
	public String getTotalGWPI() {
		return totalGWPI;
	}
	public void setTotalGWPI(String totalGWPI) {
		this.totalGWPI = totalGWPI;
	}
	public List<String> getCoverRemark() {
		return coverRemark;
	}
	public void setCoverRemark(List<String> coverRemark) {
		this.coverRemark = coverRemark;
	}
	public String getOpenPeriodDate() {
		return openPeriodDate;
	}
	public void setOpenPeriodDate(String openPeriodDate) {
		this.openPeriodDate = openPeriodDate;
	}
	public List<String> getXolcoverSNo() {
		return xolcoverSNo;
	}
	public void setXolcoverSNo(List<String> xolcoverSNo) {
		this.xolcoverSNo = xolcoverSNo;
	}
	public List<String> getXolcoverdepartId() {
		return xolcoverdepartId;
	}
	public void setXolcoverdepartId(List<String> xolcoverdepartId) {
		this.xolcoverdepartId = xolcoverdepartId;
	}
	public List<String> getXolcoversubdepartId() {
		return xolcoversubdepartId;
	}
	public void setXolcoversubdepartId(List<String> xolcoversubdepartId) {
		this.xolcoversubdepartId = xolcoversubdepartId;
	}
	public List<String> getXolcoverLimitOC() {
		return xolcoverLimitOC;
	}
	public void setXolcoverLimitOC(List<String> xolcoverLimitOC) {
		this.xolcoverLimitOC = xolcoverLimitOC;
	}
	public List<String> getXoldeductableLimitOC() {
		return xoldeductableLimitOC;
	}
	public void setXoldeductableLimitOC(List<String> xoldeductableLimitOC) {
		this.xoldeductableLimitOC = xoldeductableLimitOC;
	}
	public List<String> getXolpremiumRateList() {
		return xolpremiumRateList;
	}
	public void setXolpremiumRateList(List<String> xolpremiumRateList) {
		this.xolpremiumRateList = xolpremiumRateList;
	}
	public List<String> getXolgwpiOC() {
		return xolgwpiOC;
	}
	public void setXolgwpiOC(List<String> xolgwpiOC) {
		this.xolgwpiOC = xolgwpiOC;
	}
	public List<Map<String, Object>> getXolCoverdeductableList() {
		return xolCoverdeductableList;
	}
	public void setXolCoverdeductableList(
			List<Map<String, Object>> xolCoverdeductableList) {
		this.xolCoverdeductableList = xolCoverdeductableList;
	}
	public String getXolLoopcount() {
		return xolLoopcount;
	}
	public void setXolLoopcount(String xolLoopcount) {
		this.xolLoopcount = xolLoopcount;
	}
	public String getXollayerNo() {
		return xollayerNo;
	}
	public void setXollayerNo(String xollayerNo) {
		this.xollayerNo = xollayerNo;
	}
	public List<List<Map<String, Object>>> getCoverxolsubDepartList() {
		return coverxolsubDepartList;
	}
	public void setCoverxolsubDepartList(
			List<List<Map<String, Object>>> coverxolsubDepartList) {
		this.coverxolsubDepartList = coverxolsubDepartList;
	}
	

	
	
}
