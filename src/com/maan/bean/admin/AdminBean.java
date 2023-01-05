package com.maan.bean.admin;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;


public class AdminBean {

	private String path;
	private String branchCode;
	private String mode;
	private String profitCenterId;
	private String profitCenter;
	private String coreCompany;
	private String active;
	private String useroperation;
	private String loginId;
	private String userName;
	private String userType;
	private String password;
	private String rePassword;
	private String lid;
	private String lsatus;
	private String countryId;
	private String currencyId;
	private String exchangeId;
	private String exchangeRate;
	private String coreAppCode;
	private String inceptionDate;
	private List<String> remarks;
	private String searchType;
	private String searchValue;
	private String commonValue;
	private String reqform;
	private String productId;
	private String department;
	private String process;
	private String menuId;
	private String menuName;
	private String menuUrl;
	private String orderby;
	private String expiryDate;
	private String departmentId;
	private String departmentName;
	private String pid;
	private String coreCompanyCode;
	private String status;
	private String searchby;
	private String ProductMaster;
	private String customer_names;
	private String productName;
	private String territoryMaster;
	private String territoryCode;
	private String territoryName;
	private String territoryId;
	private String territoryDesc;
	private String territoryStatus;
	private String categoryId;
	private String categoryName;
	private String sno;
	private String currencyName;
	private String effectDate;
	private String currencyShortName;
	private String processName;
	private String product;
	private String orderno;
	private String uwlimit;
	private String cid;
	private String amendid;
	private String inceptiondate;
	private String experydate;
	private String entrydate;
	private String countryid;
	private String processId;
	private String branchName;
	private String subprofitCenterId;
	private String subProfitCenter_Id;
	private String subProfitCenter;
	private String policybranchid;
	private String policybranchname;
	private String underWriterId;
	private String underWriterName;
	private String riskGradeName;
	private String riskGradeId;
	private String countryName;
	private String countryShortName;
	private String ammentID;
	private String baseCurrencyId;
	private String descCurrencyId;
	private String customerType;
	private String companyName;
	private String companyCode;
	private String firstName;
	private String title;
	private String lastName;
	private String designation;
	private String email;
	private String telephone;
	private String mobile;
	private String faxNo;
	private String address1;
	private String address2;
	private String city;
	private String zipcode;
	private String customerId;
	private String branch;
	private String country;
	private String branchCode1;
	private String nationality;
	//private String[] selectedMenus;
	private String userForm;
	private String startDate;
	private String endDate;
	private String subMenuName;
	private String subMenuCode;
	private String submenuId;
	private String menuid;
	private String menuPath;
	private String teritoryId;
	private String crestaId;
	private String crestaName;
	private String crestaSNo;
	private String crestaStatus;
	private List<String> sectionNo;
	private List<String> anualThresholdLimit;
	private List<String> typeId;
	private List<String> residentalStatus;
	private List<String> rateOfTds;
	private List<String> cessOne;
	private List<String> cessTwo;
	private List<String> cessThree;
	private List<String> totalTds;
	private String tdsSno;
	public List<String> rateSno;
	private String effectiveDate;
	public List<AdminBean> profitCenterList;
	public List<AdminBean> userListMaster;
	public List<AdminBean> exchangeList;
	public List<AdminBean> exchangeCurrencyList;
	public List<AdminBean> DepartmentList;
	public List<AdminBean> ProductList;
	public List<AdminBean> ProductMasterList;
	public List<AdminBean> TerritoryList;
	public List<AdminBean> CategoryMasterList;
	public List<AdminBean> CurrencyMasterList;
	public List<AdminBean> ProcessList;
	public List<AdminBean> CommonDepartmentList = new ArrayList();
	public List<AdminBean> productList = new ArrayList();
	public List<AdminBean> departmentList = new ArrayList();
	public List<AdminBean> processList = new ArrayList();
	public List<AdminBean> menuList = new ArrayList();
	public List<AdminBean> subprofitCenterList;
	public List<AdminBean> policyBranchList;
	public List<AdminBean> UnderWriterList;
	public List<AdminBean> RiskGradeList;
	public List<AdminBean> CountryList;
	public List<AdminBean> BranchList;
	public List<AdminBean> CedingList;
	public List<AdminBean> titleList;
	public List<AdminBean> countryIdList;
	public List<AdminBean> dcurrencyList;
	public List<AdminBean> baseCurrencyList;
	public ArrayList selectedMenus;
	public List<AdminBean> openList;
	public List<AdminBean> subMenuList;
	public List<AdminBean> uwLimitList;
	public String exchangeRateId;
	public String currencyNameId;
	public List<String> exchageRateList;
	public List<String> currencyNameList;
	public List<String> exchageRate;
	public List<AdminBean> UWLimitMasterList;
	private List<Map<String, Object>> UnderWritterList = new ArrayList();
	private List<Map<String, Object>> brokercountryList = new ArrayList();
	private List<Map<String, Object>> editAddRateMaster;
	public String underwritter;
	public String panNo;
	public String isIndividual;
	public String isNonResident;
	public String specialRate;
	private String deleteId;
	private String flag;
	private List<String> errorList;
	private List<List<Map<String, Object>>> currencyList;
	private List<List<Map<String, Object>>> contactList;
	private List<Map<String, Object>> bankCurrencyList;
	private Map<String, List<String>> error = new HashMap<String, List<String>>();
	private List<Map<String, Object>> tdsTypeList;
	private List<Map<String, Object>> clientList;
	private List<Map<String, Object>> clientPersonalList;
	private List<Map<String, Object>> departList;
	private List<String> bankSNo;
	private List<String> bankaccountnumber;
	private List<String> bankname;
	private List<String> accountname;
	private List<String> swiftcode;
	private List<String> ifsccode;
	private List<String> corespondentbank;
	private String snumber;
	private List<String> emailaddress;
	private List<String> telephonenumber;
	private List<String> faxnumber;
	private List<String> departmentCD;
	private List<String> subdepartmentCD;
	private List<String> contactSNo;
	
	private String Servicetax;
	private String PANnumber;
	private String type;
	private String clientType;
	private List<String> bankCurrency;
	private String clientRemarks;
	private List<String> bankRemarks;
	private List<Map<String, Object>> broGroupList;
	private String broGroup;

	private String startIndex;
	private String endIndex;
	private List<Integer> docuList = new ArrayList<Integer>();
	private String startValue;

	private List<String> docId = new ArrayList<String>();
	private List<String> docDesc = new ArrayList<String>();
	private List<Map<String, Object>> docList = new ArrayList<Map<String, Object>>();
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	private String documentId;
	private String ourFileName;
	private String orgFileName;
	private String filePath;
	private String fileName;
	private String establishmentYear;
	private String regNo;
	private String cinNo;
	private String rating;
	private String ratingAgency;
	private String lastRating;
	private String attachedUW;
	private String dropDown;
	private String vatRegYN;
	private String vatRegNo;
	public String getAttachedUW() {
		return attachedUW;
	}

	public void setAttachedUW(String attachedUW) {
		this.attachedUW = attachedUW;
	}

	public String getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(String establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRatingAgency() {
		return ratingAgency;
	}

	public void setRatingAgency(String ratingAgency) {
		this.ratingAgency = ratingAgency;
	}

	public String getLastRating() {
		return lastRating;
	}

	public void setLastRating(String lastRating) {
		this.lastRating = lastRating;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Map<String, Object>> getDocList() {
		return docList;
	}

	public void setDocList(List<Map<String, Object>> docList) {
		this.docList = docList;
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

	public List<String> getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(List<String> docDesc) {
		this.docDesc = docDesc;
	}

	public List<Integer> getDocuList() {
		return docuList;
	}

	public void setDocuList(List<Integer> docuList) {
		this.docuList = docuList;
	}

	public String getStartValue() {
		return startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
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

	public String getBroGroup() {
		return broGroup;
	}

	public void setBroGroup(String broGroup) {
		this.broGroup = broGroup;
	}

	public List<Map<String, Object>> getBroGroupList() {
		return broGroupList;
	}

	public void setBroGroupList(List<Map<String, Object>> broGroupList) {
		this.broGroupList = broGroupList;
	}

	public List<String> getDepartmentCD() {
		return departmentCD;
	}

	public void setDepartmentCD(List<String> departmentCD) {
		this.departmentCD = departmentCD;
	}


	public List<List<Map<String, Object>>> getContactList() {
		return contactList;
	}

	public void setContactList(List<List<Map<String, Object>>> contactList) {
		this.contactList = contactList;
	}

	public List<String> getBankRemarks() {
		return bankRemarks;
	}

	public void setBankRemarks(List<String> bankRemarks) {
		this.bankRemarks = bankRemarks;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public List<String> getBankCurrency() {
		return bankCurrency;
	}

	public void setBankCurrency(List<String> bankCurrency) {
		this.bankCurrency = bankCurrency;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(List<String> bankname2) {
		this.bankaccountnumber = bankname2;
	}

	public List<String> getBankname() {
		return bankname;
	}

	public void setBankname(List<String> bankname2) {
		this.bankname = bankname2;
	}

	public List<String> getAccountname() {
		return accountname;
	}

	public void setAccountname(List<String> bankname2) {
		this.accountname = bankname2;
	}

	public List<String> getSwiftcode() {
		return swiftcode;
	}

	public void setSwiftcode(List<String> swiftCode2) {
		this.swiftcode = swiftCode2;
	}

	public List<String> getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(List<String> ifscCode2) {
		this.ifsccode = ifscCode2;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public List<String> getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(List<String> emailaddress) {
		this.emailaddress = emailaddress;
	}

	public List<String> getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(List<String> telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public List<String> getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(List<String> faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getServicetax() {
		return Servicetax;
	}

	public void setServicetax(String servicetax) {
		Servicetax = servicetax;
	}

	public String getPANnumber() {
		return PANnumber;
	}

	public void setPANnumber(String pANnumber) {
		PANnumber = pANnumber;
	}

	public List<Map<String, Object>> getClientPersonalList() {
		return clientPersonalList;
	}

	public void setClientPersonalList(List<Map<String, Object>> clientPersonalList) {
		this.clientPersonalList = clientPersonalList;
	}

	public List<Map<String, Object>> getClientList() {
		return clientList;
	}

	public void setClientList(List<Map<String, Object>> clientList) {
		this.clientList = clientList;
	}

	public List<Map<String, Object>> getTdsTypeList() {
		return tdsTypeList;
	}

	public void setTdsTypeList(List<Map<String, Object>> tdsTypeList) {
		this.tdsTypeList = tdsTypeList;
	}

	public List<Map<String, Object>> getBankCurrencyList() {
		return bankCurrencyList;
	}

	public void setBankCurrencyList(List<Map<String, Object>> BankCurrencyList) {
		this.bankCurrencyList = BankCurrencyList;
	}

	public List<List<Map<String, Object>>> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<List<Map<String, Object>>> currencyList2) {
		this.currencyList = currencyList2;
	}

	public String getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Map<String, List<String>> getError() {
		return error;
	}

	public void setError(Map<String, List<String>> error) {
		this.error = error;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public String getTdsSno() {
		return tdsSno;
	}

	public void setTdsSno(String tdsSno) {
		this.tdsSno = tdsSno;
	}

	public List<Map<String, Object>> getEditAddRateMaster() {
		return editAddRateMaster;
	}

	public void setEditAddRateMaster(List<Map<String, Object>> editAddRateMaster) {
		this.editAddRateMaster = editAddRateMaster;
	}

	public List<String> getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(List<String> sectionNo) {
		this.sectionNo = sectionNo;
	}

	public List<String> getAnualThresholdLimit() {
		return anualThresholdLimit;
	}

	public void setAnualThresholdLimit(List<String> anualThresholdLimit) {
		this.anualThresholdLimit = anualThresholdLimit;
	}

	public List<String> getTypeId() {
		return typeId;
	}

	public void setTypeId(List<String> typeId) {
		this.typeId = typeId;
	}

	public List<String> getResidentalStatus() {
		return residentalStatus;
	}

	public void setResidentalStatus(List<String> residentalStatus) {
		this.residentalStatus = residentalStatus;
	}

	public List<String> getRateOfTds() {
		return rateOfTds;
	}

	public void setRateOfTds(List<String> rateOfTds) {
		this.rateOfTds = rateOfTds;
	}

	public List<String> getCessOne() {
		return cessOne;
	}

	public void setCessOne(List<String> cessOne) {
		this.cessOne = cessOne;
	}

	public List<String> getCessTwo() {
		return cessTwo;
	}

	public void setCessTwo(List<String> cessTwo) {
		this.cessTwo = cessTwo;
	}

	public List<String> getCessThree() {
		return cessThree;
	}

	public void setCessThree(List<String> cessThree) {
		this.cessThree = cessThree;
	}

	public List<String> getTotalTds() {
		return totalTds;
	}

	public void setTotalTds(List<String> totalTds) {
		this.totalTds = totalTds;
	}

	public List<String> getRateSno() {
		return rateSno;
	}

	public void setRateSno(List<String> rateSno) {
		this.rateSno = rateSno;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCrestaStatus() {
		return crestaStatus;
	}

	public void setCrestaStatus(String crestaStatus) {
		this.crestaStatus = crestaStatus;
	}

	public String getCrestaSNo() {
		return crestaSNo;
	}

	public void setCrestaSNo(String crestaSNo) {
		this.crestaSNo = crestaSNo;
	}

	public String getTeritoryId() {
		return teritoryId;
	}

	public void setTeritoryId(String teritoryId) {
		this.teritoryId = teritoryId;
	}

	public String getCrestaId() {
		return crestaId;
	}

	public void setCrestaId(String crestaId) {
		this.crestaId = crestaId;
	}

	public String getCrestaName() {
		return crestaName;
	}

	public void setCrestaName(String crestaName) {
		this.crestaName = crestaName;
	}

	public String getUnderwritter() {
		return underwritter;
	}

	public void setUnderwritter(String underwritter) {
		this.underwritter = underwritter;
	}

	public List<Map<String, Object>> getUnderWritterList() {
		return UnderWritterList;
	}

	public void setUnderWritterList(List<Map<String, Object>> underWritterList) {
		UnderWritterList = underWritterList;
	}

	public List<AdminBean> getUWLimitMasterList() {
		return UWLimitMasterList;
	}

	public void setUWLimitMasterList(List<AdminBean> uWLimitMasterList) {
		UWLimitMasterList = uWLimitMasterList;
	}

	public List<String> getExchageRateList() {
		return exchageRateList;
	}

	public void setExchageRateList(List<String> exchageRateList) {
		this.exchageRateList = exchageRateList;
	}

	public List<String> getCurrencyNameList() {
		return currencyNameList;
	}

	public void setCurrencyNameList(List<String> currencyNameList) {
		this.currencyNameList = currencyNameList;
	}

	public String getExchangeRateId() {
		return exchangeRateId;
	}

	public void setExchangeRateId(String exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	public String getCurrencyNameId() {
		return currencyNameId;
	}

	public void setCurrencyNameId(String currencyNameId) {
		this.currencyNameId = currencyNameId;
	}

	public List<String> getExchageRate() {
		return exchageRate;
	}

	public void setExchageRate(List<String> exchageRate) {
		this.exchageRate = exchageRate;
	}

	public String getMenuPath(Object object) {
		return menuPath;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getReqform() {
		return reqform;
	}

	public void setReqform(String reqform) {
		this.reqform = reqform;
	}

	public String getCommonValue() {
		return commonValue;
	}

	public void setCommonValue(String commonValue) {
		this.commonValue = commonValue;
	}


	//private Map<String,String> menuinfo;
	private Map menuinfo;
	private Map submenuinfo;

	/*private Map info;

    public Map getInfo() {
        return info;
    }

    public void setInfo(Map info) {
        this.info = info;
    }
*/
	public Map getMenuinfo() {
		return menuinfo;
	}

	public Map setMenuinfo(Map menuinfo) {
		return this.menuinfo = menuinfo;
	}

	public Map getSubmenuinfo() {
		return submenuinfo;
	}

	public Map setSubmenuinfo(Map submenuinfo) {
		return this.submenuinfo = submenuinfo;
	}

	public String getProfitCenterId() {
		return profitCenterId;
	}

	public void setProfitCenterId(String profitCenterId) {
		this.profitCenterId = profitCenterId;
	}

	public String getProfitCenter() {
		return profitCenter;
	}

	public void setProfitCenter(String profitCenter) {
		this.profitCenter = profitCenter;
	}

	public String getCoreCompany() {
		return coreCompany;
	}

	public void setCoreCompany(String coreCompany) {
		this.coreCompany = coreCompany;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getUseroperation() {
		return useroperation;
	}

	public void setUseroperation(String useroperation) {
		this.useroperation = useroperation;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getLsatus() {
		return lsatus;
	}

	public void setLsatus(String lsatus) {
		this.lsatus = lsatus;
	}


	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/*public Map<String, String> getMenuinfo() {
        return menuinfo;
	}

	public void setMenuinfo(Map<String, String> menuinfo) {
		this.menuinfo = menuinfo;
	}*/


	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCoreAppCode() {
		return coreAppCode;
	}

	public void setCoreAppCode(String coreAppCode) {
		this.coreAppCode = coreAppCode;
	}

	public String getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks1(List<String> string) {
		this.remarks = string;
	}


	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
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


	public List<AdminBean> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AdminBean> menuList) {
		this.menuList = menuList;
	}

	public List<AdminBean> getProcessList() {
		return processList;
	}

	public void setProcessList(List<AdminBean> processList) {
		this.processList = processList;
	}

	public List<AdminBean> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<AdminBean> departmentList) {
		this.departmentList = departmentList;
	}

	public List<AdminBean> getProductList() {
		return productList;
	}

	public void setProductList(List<AdminBean> productList) {
		this.productList = productList;
	}


	private Map<String, String> uwlimitMap;
	private ArrayList<String> selectedDepartment;
	private ArrayList<String> selectedProduct;
	private ArrayList<String> selectedProcess;
	private ArrayList<String> selectedMainMenu;
	private ArrayList<String> selectedSubMenuButton;
	private ArrayList<String> tempSelectedMainMenu;
	private ArrayList<String> tempSelectedSubMenuButton;

	public ArrayList<String> getTempSelectedMainMenu() {
		return tempSelectedMainMenu;
	}

	public void setTempSelectedMainMenu(ArrayList<String> tempSelectedMainMenu) {
		this.tempSelectedMainMenu = tempSelectedMainMenu;
	}

	public ArrayList<String> getTempSelectedSubMenuButton() {
		return tempSelectedSubMenuButton;
	}

	public void setTempSelectedSubMenuButton(ArrayList<String> tempSelectedSubMenuButton) {
		this.tempSelectedSubMenuButton = tempSelectedSubMenuButton;
	}

	public ArrayList<String> getSelectedMainMenu() {
		return selectedMainMenu;
	}

	public void setSelectedMainMenu(ArrayList<String> selectedMainMenu) {
		this.selectedMainMenu = selectedMainMenu;
	}

	public ArrayList<String> getSelectedSubMenuButton() {
		return selectedSubMenuButton;
	}

	public void setSelectedSubMenuButton(ArrayList<String> selectedSubMenuButton) {
		this.selectedSubMenuButton = selectedSubMenuButton;
	}

	public ArrayList<String> getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ArrayList<String> selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ArrayList<String> getSelectedProcess() {
		return selectedProcess;
	}

	public void setSelectedProcess(ArrayList<String> selectedProcess) {
		this.selectedProcess = selectedProcess;
	}

	private String[] selectedDepartments;

	public ArrayList<String> getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(ArrayList<String> selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public List<AdminBean> getProfitCenterList() {
		return profitCenterList;
	}

	public void setProfitCenterList(List<AdminBean> profitCenterList) {
		this.profitCenterList = profitCenterList;
	}

	public List<AdminBean> getUserListMaster() {
		return userListMaster;
	}

	public void setUserListMaster(List<AdminBean> userListMaster) {
		this.userListMaster = userListMaster;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getUwlimitMap() {
		return uwlimitMap;
	}

	public void setUwlimitMap(Map<String, String> uwlimitMap) {
		this.uwlimitMap = uwlimitMap;
	}

	public String[] getSelectedDepartments() {
		return selectedDepartments;
	}

	public void setSelectedDepartments(String[] selectedDepartments) {
		this.selectedDepartments = selectedDepartments;
	}

	public List<AdminBean> getExchangeList() {
		return exchangeList;
	}

	public void setExchangeList(List<AdminBean> exchangeList) {
		this.exchangeList = exchangeList;
	}

	public List<AdminBean> getExchangeCurrencyList() {
		return exchangeCurrencyList;
	}

	public void setExchangeCurrencyList(List<AdminBean> exchangeCurrencyList) {
		this.exchangeCurrencyList = exchangeCurrencyList;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCoreCompanyCode() {
		return coreCompanyCode;
	}

	public void setCoreCompanyCode(String coreCompanyCode) {
		this.coreCompanyCode = coreCompanyCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchby() {
		return searchby;
	}

	public void setSearchby(String searchby) {
		this.searchby = searchby;
	}

	public String getProductMaster() {
		return ProductMaster;
	}

	public void setProductMaster(String productMaster) {
		ProductMaster = productMaster;
	}

	public String getCustomer_names() {
		return customer_names;
	}

	public void setCustomer_names(String customerNames) {
		customer_names = customerNames;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTerritoryMaster() {
		return territoryMaster;
	}

	public void setTerritoryMaster(String territoryMaster) {
		this.territoryMaster = territoryMaster;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
	}

	public String getTerritoryDesc() {
		return territoryDesc;
	}

	public void setTerritoryDesc(String territoryDesc) {
		this.territoryDesc = territoryDesc;
	}

	public String getTerritoryStatus() {
		return territoryStatus;
	}

	public void setTerritoryStatus(String territoryStatus) {
		this.territoryStatus = territoryStatus;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getCurrencyShortName() {
		return currencyShortName;
	}

	public void setCurrencyShortName(String currencyShortName) {
		this.currencyShortName = currencyShortName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getUwlimit() {
		return uwlimit;
	}

	public void setUwlimit(String uwlimit) {
		this.uwlimit = uwlimit;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAmendid() {
		return amendid;
	}

	public void setAmendid(String amendid) {
		this.amendid = amendid;
	}

	public String getInceptiondate() {
		return inceptiondate;
	}

	public void setInceptiondate(String inceptiondate) {
		this.inceptiondate = inceptiondate;
	}

	public String getExperydate() {
		return experydate;
	}

	public void setExperydate(String experydate) {
		this.experydate = experydate;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public List<AdminBean> getProductMasterList() {
		return ProductMasterList;
	}

	public void setProductMasterList(List<AdminBean> productMasterList) {
		ProductMasterList = productMasterList;
	}

	public List<AdminBean> getTerritoryList() {
		return TerritoryList;
	}

	public void setTerritoryList(List<AdminBean> territoryList) {
		TerritoryList = territoryList;
	}

	public List<AdminBean> getCategoryMasterList() {
		return CategoryMasterList;
	}

	public void setCategoryMasterList(List<AdminBean> categoryMasterList) {
		CategoryMasterList = categoryMasterList;
	}

	public List<AdminBean> getCurrencyMasterList() {
		return CurrencyMasterList;
	}

	public void setCurrencyMasterList(List<AdminBean> currencyMasterList) {
		CurrencyMasterList = currencyMasterList;
	}

	public List<AdminBean> getCommonDepartmentList() {
		return CommonDepartmentList;
	}

	public void setCommonDepartmentList(List<AdminBean> commonDepartmentList) {
		CommonDepartmentList = commonDepartmentList;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSubprofitCenterId() {
		return subprofitCenterId;
	}

	public void setSubprofitCenterId(String subprofitCenterId) {
		this.subprofitCenterId = subprofitCenterId;
	}

	public String getSubProfitCenter_Id() {
		return subProfitCenter_Id;
	}

	public void setSubProfitCenter_Id(String subProfitCenterId) {
		subProfitCenter_Id = subProfitCenterId;
	}

	public String getSubProfitCenter() {
		return subProfitCenter;
	}

	public void setSubProfitCenter(String subProfitCenter) {
		this.subProfitCenter = subProfitCenter;
	}

	public String getPolicybranchid() {
		return policybranchid;
	}

	public void setPolicybranchid(String policybranchid) {
		this.policybranchid = policybranchid;
	}

	public String getPolicybranchname() {
		return policybranchname;
	}

	public void setPolicybranchname(String policybranchname) {
		this.policybranchname = policybranchname;
	}

	public String getUnderWriterId() {
		return underWriterId;
	}

	public void setUnderWriterId(String underWriterId) {
		this.underWriterId = underWriterId;
	}

	public String getUnderWriterName() {
		return underWriterName;
	}

	public void setUnderWriterName(String underWriterName) {
		this.underWriterName = underWriterName;
	}

	public String getRiskGradeName() {
		return riskGradeName;
	}

	public void setRiskGradeName(String riskGradeName) {
		this.riskGradeName = riskGradeName;
	}

	public String getRiskGradeId() {
		return riskGradeId;
	}

	public void setRiskGradeId(String riskGradeId) {
		this.riskGradeId = riskGradeId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryShortName() {
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}

	public String getAmmentID() {
		return ammentID;
	}

	public void setAmmentID(String ammentID) {
		this.ammentID = ammentID;
	}

	public String getBaseCurrencyId() {
		return baseCurrencyId;
	}

	public void setBaseCurrencyId(String baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}

	public String getDescCurrencyId() {
		return descCurrencyId;
	}

	public void setDescCurrencyId(String descCurrencyId) {
		this.descCurrencyId = descCurrencyId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBranchCode1() {
		return branchCode1;
	}

	public void setBranchCode1(String branchCode1) {
		this.branchCode1 = branchCode1;
	}

	public List<AdminBean> getSubprofitCenterList() {
		return subprofitCenterList;
	}

	public void setSubprofitCenterList(List<AdminBean> subprofitCenterList) {
		this.subprofitCenterList = subprofitCenterList;
	}

	public List<AdminBean> getPolicyBranchList() {
		return policyBranchList;
	}

	public void setPolicyBranchList(List<AdminBean> policyBranchList) {
		this.policyBranchList = policyBranchList;
	}

	public List<AdminBean> getUnderWriterList() {
		return UnderWriterList;
	}

	public void setUnderWriterList(List<AdminBean> underWriterList) {
		UnderWriterList = underWriterList;
	}

	public List<AdminBean> getRiskGradeList() {
		return RiskGradeList;
	}

	public void setRiskGradeList(List<AdminBean> riskGradeList) {
		RiskGradeList = riskGradeList;
	}

	public List<AdminBean> getCountryList() {
		return CountryList;
	}

	public void setCountryList(List<AdminBean> countryList) {
		CountryList = countryList;
	}

	public List<AdminBean> getBranchList() {
		return BranchList;
	}

	public void setBranchList(List<AdminBean> branchList) {
		BranchList = branchList;
	}

	public List<AdminBean> getCedingList() {
		return CedingList;
	}

	public void setCedingList(List<AdminBean> cedingList) {
		CedingList = cedingList;
	}

	public List<AdminBean> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<AdminBean> titleList) {
		this.titleList = titleList;
	}

	public List<AdminBean> getCountryIdList() {
		return countryIdList;
	}

	public void setCountryIdList(List<AdminBean> countryIdList) {
		this.countryIdList = countryIdList;
	}

	public List<AdminBean> getDcurrencyList() {
		return dcurrencyList;
	}

	public void setDcurrencyList(List<AdminBean> dcurrencyList) {
		this.dcurrencyList = dcurrencyList;
	}

	public List<AdminBean> getBaseCurrencyList() {
		return baseCurrencyList;
	}

	public void setBaseCurrencyList(List<AdminBean> baseCurrencyList) {
		this.baseCurrencyList = baseCurrencyList;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUserForm() {
		return userForm;
	}

	public void setUserForm(String userForm) {
		this.userForm = userForm;
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

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getSubMenuCode() {
		return subMenuCode;
	}

	public void setSubMenuCode(String subMenuCode) {
		this.subMenuCode = subMenuCode;
	}

	public String getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(String submenuId) {
		this.submenuId = submenuId;
	}

	public List<AdminBean> getOpenList() {
		return openList;
	}

	public void setOpenList(List<AdminBean> openList) {
		this.openList = openList;
	}

	public List<AdminBean> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<AdminBean> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String[] selectedMenu;

	public String[] getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String[] temp) {
		this.selectedMenu = temp;
	}


	public Map setInfo(Map allocationList) {
		return null;
	}

	public String getParameter(String string) {
		return null;
	}

	public StringUtils getProcessId(String string) {
		return null;
	}

	private String[] selectedSubMenus;
	private ArrayList selectedSubMenu;
	private Enumeration parameterNames;
	private String menuidList;
	private String Remarks1;
	private String Remarks;

	public Enumeration getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(Enumeration parameterNames) {
		this.parameterNames = parameterNames;
	}

	public void setSelectedMenus(ArrayList selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	public void setSelectedSubMenus(String[] selectedSubMenus) {
		this.selectedSubMenus = selectedSubMenus;
	}

	public String[] getSelectedSubMenus() {
		return selectedSubMenus;
	}

	public ArrayList getSelectedMenus() {
		// TODO Auto-generated method stub
		return selectedMenus;
	}

	public void setMenuidList(String menuidList) {
		this.menuidList = menuidList;
	}

	public String getMenuidList() {
		return menuidList;
	}

	public ArrayList getSelectedSubMenu() {
		return selectedSubMenu;
	}

	public void setSelectedSubMenu(ArrayList selectedSubMenu) {
		this.selectedSubMenu = selectedSubMenu;
	}

	public List<AdminBean> getUwLimitList() {
		return uwLimitList;
	}

	public void setUwLimitList(List<AdminBean> uwLimitList) {
		this.uwLimitList = uwLimitList;
	}

	public List<Map<String, Object>> getBrokercountryList() {
		return brokercountryList;
	}

	public void setBrokercountryList(List<Map<String, Object>> brokercountryList) {
		this.brokercountryList = brokercountryList;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getIsIndividual() {
		return isIndividual;
	}

	public void setIsIndividual(String isIndividual) {
		this.isIndividual = isIndividual;
	}

	public String getIsNonResident() {
		return isNonResident;
	}

	public void setIsNonResident(String isNonResident) {
		this.isNonResident = isNonResident;
	}

	public String getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(String specialRate) {
		this.specialRate = specialRate;
	}

	public void setRemarks1(String string) {
		this.Remarks1 = specialRate;
	}

	public String getRemarks1() {
		return Remarks1;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public void setRemarks(String Remarks) {
		this.Remarks = Remarks;

	}

	public List<Map<String, Object>> getDepartList() {
		return departList;
	}

	public void setDepartList(List<Map<String, Object>> departList) {
		this.departList = departList;
	}

	public List<String> getBankSNo() {
		return bankSNo;
	}

	public void setBankSNo(List<String> bankSNo) {
		this.bankSNo = bankSNo;
	}

	public List<String> getCorespondentbank() {
		return corespondentbank;
	}

	public void setCorespondentbank(List<String> corespondentbank) {
		this.corespondentbank = corespondentbank;
	}

	public List<String> getSubdepartmentCD() {
		return subdepartmentCD;
	}

	public void setSubdepartmentCD(List<String> subdepartmentCD) {
		this.subdepartmentCD = subdepartmentCD;
	}

	public List<String> getContactSNo() {
		return contactSNo;
	}

	public void setContactSNo(List<String> contactSNo) {
		this.contactSNo = contactSNo;
	}

	public String getDropDown() {
		return dropDown;
	}

	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}

	public String getVatRegYN() {
		return vatRegYN;
	}

	public void setVatRegYN(String vatRegYN) {
		this.vatRegYN = vatRegYN;
	}

	public String getVatRegNo() {
		return vatRegNo;
	}

	public void setVatRegNo(String vatRegNo) {
		this.vatRegNo = vatRegNo;
	}


}
