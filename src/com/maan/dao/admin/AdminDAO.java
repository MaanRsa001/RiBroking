package com.maan.dao.admin;

import java.util.List;
import java.util.Map;

import com.maan.bean.admin.AdminBean;

public interface AdminDAO {

	List<AdminBean> getProfitCenterList(AdminBean bean);

	int getInsertProfitCenter(AdminBean bean);

	int getUpdateProfitCenter(AdminBean bean);

	void getEditProfitCenter(AdminBean bean);

	List<AdminBean> getUserListMaster(AdminBean bean);

	void insertNewUser(AdminBean bean);

	void getEditUserList(AdminBean bean);

	void updateUserList(AdminBean bean);

	void getChangePassword(AdminBean bean);

	List menuAllocation(AdminBean bean);

	boolean checkDeptAllocated(AdminBean bean);

	List<AdminBean> getExchangeListMaster(AdminBean bean);

	List<AdminBean> GetExchangeCurrency(AdminBean bean);

	int insertExchangeRate(AdminBean bean);

	void getEditExchangeMaster(AdminBean bean);

	int updateExchangeRate(AdminBean bean);

	String GetSysDate(AdminBean bean);

	String GetInceptionDate(AdminBean bean);

	int getExcCoreAppCode(AdminBean bean);

	int getExchangeCurrency(AdminBean bean);

	List<AdminBean> getProductList(AdminBean bean);

	List<AdminBean> getDepartmentList(AdminBean bean);

	List<AdminBean> getProcessList(AdminBean bean);

	List<AdminBean> getMenuList(AdminBean bean);

	String getMenuId(AdminBean bean);

	int insertMenu(AdminBean bean);

	void getEditMenuMaster(AdminBean bean);

	int updateMenu(AdminBean bean);

	int getMenuNameExist(AdminBean bean);
	int getCoreAppCode(AdminBean bean);

	List<AdminBean> getDepartmentMasterList(AdminBean bean);

	int getInsertDepartment(AdminBean bean);

	int getUpdateDepartment(AdminBean bean);

	void getEditDepartment(AdminBean bean);

	int getSContCoreCompanyCode(AdminBean bean);

	int getcontDepartmentName(AdminBean bean);

	List<AdminBean> getProductMasterList(AdminBean bean);

	int getUpdateProductMaster(AdminBean bean);

	void getEditProductMaster(AdminBean bean);

	int getInsertProductMaster(AdminBean bean);

	int getcontProductName(AdminBean bean);

	int getcontCoreCompanyCode(AdminBean bean);

	List<AdminBean> getterritoryList(AdminBean bean);

	int getUpdateterritoryMaster(AdminBean bean);

	int getInsertTerritoryMaster(AdminBean bean);

	void getEditTerritoryMaster(AdminBean bean);

	int getcontTerritoryDesc(AdminBean bean);

	int getcountCoreCompanyCode(AdminBean bean);

	List<AdminBean> getCategoryMasterList(AdminBean bean);

	void getEditCategoryZoneMaster(AdminBean bean);

	int getUpdateCategoryZoneMaster(AdminBean bean);

	int getInsertCategoryMaster(AdminBean bean);

	int getcontCategoryName(AdminBean bean);

	int getcountCoreAppCode(AdminBean bean);

	List<AdminBean> getCurrencyMasterList(AdminBean bean);

	void getEditCurrencyMaster(AdminBean bean);

	int getUpdateCurrencyMaster(AdminBean bean);

	int getInsertCurrencyMaster(AdminBean bean);

	List<AdminBean> getProcessMasterList(AdminBean bean);

	void getEditprocessMaster(AdminBean bean);

	int getUpdateprocessMaster(AdminBean bean);

	List<AdminBean> getCommonDepartmentList(AdminBean bean);

	int getInsertprocessMaster(AdminBean bean);

	int getProcessName(AdminBean bean);

	int getcombination(AdminBean bean);

	int getCurrencyName(AdminBean bean);

	int getcountProfitCenter(AdminBean bean);

	int getprofitcontCoreCompanyCode(AdminBean bean);

	List<AdminBean> getSubprofitCenterList(AdminBean bean);

	int getInsertSubprofitCenter(AdminBean bean);

	int getUpdateSubprofitCenter(AdminBean bean);

	void getEditSubprofitCenter(AdminBean bean);

	int getContSubProfitCenter_Id(AdminBean bean);

	int getContSubProfitCenter(AdminBean bean);

	List<AdminBean> getPolicyBranchList(AdminBean bean);

	int getInsertPolicyBranch(AdminBean bean);

	int getUpdatePolicyBranch(AdminBean bean);

	void getEditPolicyBranch(AdminBean bean);

	int getContpolicyCoreCompanyCode(AdminBean bean);

	int getContPolicyBranch(AdminBean bean);

	List<AdminBean> getUnderWriterList(AdminBean bean);

	int getInsertunderwritertMaster(AdminBean bean);

	int getUpdateunderwritertMaster(AdminBean bean);

	void getEditunderwritertMaster(AdminBean bean);

	int getContunderwriterCoreCompanyCode(AdminBean bean);

	int getContUnderWriterId(AdminBean bean);

	List<AdminBean> getRiskGradeList(AdminBean bean);

	void getEditriskgradeMaster(AdminBean bean);

	int getUpdateriskgradeMaster(AdminBean bean);

	int getInsertriskgradeMaster(AdminBean bean);

	int getContRiskGradeCoreCompanyCode(AdminBean bean);

	List<AdminBean>getCountryList(AdminBean bean);

	void getEditCountryMaster(AdminBean bean);

	int getUpdateCountryMaster(AdminBean bean);

	int getInsertCountryMaster(AdminBean bean);

	int getContcountryMasterCoreCompanyCode(AdminBean bean);

	int getContcountryName(AdminBean bean);

	int getaddprocessMaster(AdminBean bean);

	int getupdateprocessMaster(AdminBean bean);

	int getContProcessName(AdminBean bean);

	List<AdminBean>getBranchList(AdminBean bean);

	int getinsertbranchMaster(AdminBean bean);

	List<AdminBean>getCountryIdList(AdminBean bean);

	List<AdminBean> getBaseCurrencyList(AdminBean bean);

	List<AdminBean>getCedingList(AdminBean bean);

	int getinsertcedingMaster(AdminBean bean);

	void geteditcedingMaster(AdminBean bean);

	int getupdatecedingMaster(AdminBean bean);

	int getContcedingMasterCoreCompanyCode(AdminBean bean);

	List<AdminBean> getDcurrencyList(AdminBean bean);

	void getEditbranchMaster(AdminBean bean);

	String getbranchCode1(AdminBean bean);

	int getupdatebranchMaster(AdminBean bean);

	int getContbranchMasterCoreCompanyCode(AdminBean bean);

	List AdminMenuAllcationInfo(AdminBean bean);

	List<AdminBean>getOpenList(AdminBean bean);
	List AdminSubMenuAllcationInfo(AdminBean bean);


	int getinsertopenperiodMaster(AdminBean bean);
	void getEditopenperiodMaster(AdminBean bean);
	int getupdateopenperiodMaster(AdminBean bean);
	List<AdminBean>getSubMenuList(AdminBean bean);
	int getinsertsubmenuMaster(AdminBean bean);
	void getEditsubmenuMaster(AdminBean bean);
	int getupdatsubmenuMaster(AdminBean bean);

	String getAdminMenuids(AdminBean bean);
	boolean getAdminSubMenuids(AdminBean bean);
	int getPolicyCoreCompanyCode(AdminBean bean);

	int getCountRiskGradeName(AdminBean bean);

	int getCountProfitId(AdminBean bean);

	int getCountSubMenuCode(AdminBean bean);

	int getCountSubMenuName(AdminBean bean);

	int getContCoreCompanyCode(AdminBean bean);

	boolean checkDeptMenuAllocated(AdminBean bean);

	boolean subMenuGlobalSubmit(AdminBean bean);

	boolean updateSubMenuGlobalSubmit(AdminBean bean, String string, String s);

	List allocationInfo(AdminBean bean);

	List submenuAllocation(AdminBean bean);

	boolean SaveManu(AdminBean bean, String menuids);

	List allocationList(AdminBean bean);

	boolean subMenuSave(AdminBean bean, String submenuids);

	boolean menuCheck(AdminBean bean);

	String menuAllocationPath(AdminBean bean);

	List menubuttonAllocation(AdminBean bean);

	boolean checkDeptsubMenuAllocated(AdminBean bean);

	boolean saveAdminMenus(AdminBean bean, String menus);

	boolean MenuGlobalSubmit(AdminBean bean);

	List getPrevoiusValues(AdminBean bean);

	boolean update(AdminBean bean, String string, String s);

	boolean updateUWLimit(AdminBean bean, String string, String str);

	boolean subMenuAllocationSave(AdminBean bean);

	List<AdminBean> getUWLimitMasterList(AdminBean bean);

	int getInsertUWLimitMaster(AdminBean bean);

	void getEditUWLimitMaster(AdminBean bean);

	int getUpdateUWLimitMaster(AdminBean bean);

	int getUWLimitcombination(AdminBean bean);

	List<Map<String, Object>> getDropDownTerritoryList(AdminBean bean, String branchCode);

	List<Map<String, Object>> getCrestaMasterList(AdminBean bean,String branchCode);

	void EditCresta(AdminBean bean, String branchCode);

	void CrestaInsertUpdate(AdminBean bean, String branchCode);

	List<Map<String, Object>> getRateMasterList(AdminBean bean,String branchCode);

	List<Map<String, Object>> getDropDownTypeList(AdminBean bean);

	void RateInsertUpdate(AdminBean bean, String branchCode);

	void RateMasterDelete(AdminBean bean, String branchCode);

	String dateComparition(AdminBean bean, String branchCode);

	List<Map<String, Object>> getEditAddRateMaster(AdminBean bean,String branchCode);

	List<Map<String, Object>> getDropDownResidentalList(AdminBean bean);

	List<Map<String, Object>> getbankCurrencyList(AdminBean bean);

	List<Map<String, Object>> gettdsTypeList(AdminBean bean, String string);

	List<Map<String, Object>> getclientPersonalList(AdminBean bean, String branchCode);

	void getinsertClientMaster(AdminBean bean,String branchCode);

	void getEditClientMaster(AdminBean bean, String branchCode);

	void getupdateClientMaster(AdminBean bean, String branchCode);

	List<Map<String, Object>> getbroGroupList(AdminBean bean);

	String doUploadDocDetails(List<Object[]> list);

	List<Map<String, Object>> getDocList(AdminBean bean, String branchCode);

	String doDeleteDocDetails(AdminBean bean, String loginId, String branchCode);

	boolean insertMainMenu(AdminBean bean, Map<String, String> map);

	boolean insertSubMenu(AdminBean bean, Map map);

	boolean removeMainMenuAllocationForUser(AdminBean bean);

	boolean removeSubMenuAllocationForUser(AdminBean bean);

	List<Map<String, Object>> getAttachUnderWriterList(String branchCode);
}
