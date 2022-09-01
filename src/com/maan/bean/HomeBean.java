package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeBean {
	private String homeType;
    private String productId;
    private String departmentId;
    private String processId;
    private String dropDown;
    private String productName;
    private String departmentName;
    private String processName;
    private String journalID;
    private String downloadType;
    private String journalname;
    private String fileName;
    private String menuName;
    private String proposalNo;
    private String menumode;
    private String territoryId;
    private String countryIncluded;
    private String countryExcluded;
    private String countryIncludedList;
    private String countryExcludedList;
    private String contractNo;
    private String layerNo;
    private String amendId;
    private String territory;
    private String countryMode;
	private List<Map<String,Object>> territoryCountryList=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> excludedCountryList=new ArrayList<Map<String,Object>>();
	private String previousSelectTerritory;
	private String expiryDate;
	private String version;
	private String expiryCount;
	private String disableStatus1;
	private String baseLayer;


	
	
	public String getDisableStatus1() {
		return disableStatus1;
	}

	public void setDisableStatus1(String disableStatus1) {
		this.disableStatus1 = disableStatus1;
	}

	public String getBaseLayer() {
		return baseLayer;
	}

	public void setBaseLayer(String baseLayer) {
		this.baseLayer = baseLayer;
	}

	public String getVersion() {
		return version;
	}

	public String getExpiryCount() {
		return expiryCount;
	}

	public void setExpiryCount(String expiryCount) {
		this.expiryCount = expiryCount;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getJournalname() {
		return journalname;
	}

	public void setJournalname(String journalname) {
		this.journalname = journalname;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	public String getJournalID() {
		return journalID;
	}

	public void setJournalID(String journalID) {
		this.journalID = journalID;
	}

	private List<Map<String,Object>> processList;
    private List<Map<String,Object>> departmentList;
    private List<Map<String,Object>> finalMenuList;
    private List<Map<String,Object>> openperiodList;
    private List<List<Map<String, Object>>> journalViewList;
    private List<Map<String,Object>> spcCurrencyList;
    private List<Map<String,Object>> menuList;
    public List<Map<String, Object>> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Map<String, Object>> menuList) {
		this.menuList = menuList;
	}

	public List<Map<String, Object>> getSpcCurrencyList() {
		return spcCurrencyList;
	}

	public void setSpcCurrencyList(List<Map<String, Object>> spcCurrencyList) {
		this.spcCurrencyList = spcCurrencyList;
	}
	public List<List<Map<String, Object>>> getJournalViewList() {
		return journalViewList;
	}

	public void setJournalViewList(List<List<Map<String, Object>>> journalViewList) {
		this.journalViewList = journalViewList;
	}

	public List<Map<String, Object>> getOpenperiodList() {
		return openperiodList;
	}

	public void setOpenperiodList(List<Map<String, Object>> openperiodList) {
		this.openperiodList = openperiodList;
	}

	public List<Map<String, Object>> getProcessList() {
		return processList;
	}

	public void setProcessList(List<Map<String, Object>> processList) {
		this.processList = processList;
	}

	public List<Map<String, Object>> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Map<String, Object>> departmentList) {
		this.departmentList = departmentList;
	}
	
    public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getDropDown() {
		return dropDown;
	}

	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}

	public List<Map<String, Object>> getFinalMenuList() {
		return finalMenuList;
	}

	public void setFinalMenuList(List<Map<String, Object>> finalMenuList) {
		this.finalMenuList = finalMenuList;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getMenumode() {
		return menumode;
	}

	public void setMenumode(String menumode) {
		this.menumode = menumode;
	}

	public String getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
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

	public String getAmendId() {
		return amendId;
	}

	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getCountryMode() {
		return countryMode;
	}

	public void setCountryMode(String countryMode) {
		this.countryMode = countryMode;
	}

	public List<Map<String, Object>> getTerritoryCountryList() {
		return territoryCountryList;
	}

	public void setTerritoryCountryList(
			List<Map<String, Object>> territoryCountryList) {
		this.territoryCountryList = territoryCountryList;
	}

	public List<Map<String, Object>> getExcludedCountryList() {
		return excludedCountryList;
	}

	public void setExcludedCountryList(List<Map<String, Object>> excludedCountryList) {
		this.excludedCountryList = excludedCountryList;
	}

	public String getPreviousSelectTerritory() {
		return previousSelectTerritory;
	}

	public void setPreviousSelectTerritory(String previousSelectTerritory) {
		this.previousSelectTerritory = previousSelectTerritory;
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

	

}
