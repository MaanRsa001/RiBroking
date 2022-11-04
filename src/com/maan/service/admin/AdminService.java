package com.maan.service.admin;

import com.maan.bean.admin.AdminBean;
import com.maan.dao.admin.AdminDAO;
import com.maan.dao.impl.admin.AdminDAOImpl;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class AdminService {
	AdminDAO dao=new AdminDAOImpl();
	public List<AdminBean> getProfitCenterList(AdminBean bean) {
		return dao.getProfitCenterList(bean);
	}
	public int getInsertProfitCenter(AdminBean bean) {
		return dao.getInsertProfitCenter(bean);
	}
	public int getUpdateProfitCenter(AdminBean bean) {
		return dao.getUpdateProfitCenter(bean);
	}
	public void getEditProfitCenter(AdminBean bean) {
		dao.getEditProfitCenter(bean);
	}
	public List<AdminBean> getUserListMaster(AdminBean bean) {
		return dao.getUserListMaster(bean);
	}
	public void insertNewUser(AdminBean bean) {
		dao.insertNewUser(bean);
	}
	public void getEditUserList(AdminBean bean) {
		dao.getEditUserList(bean);

	}
	public void updateUserList(AdminBean bean) {
		dao.updateUserList(bean);

	}
	public void getChangePassword(AdminBean bean) {
		dao.getChangePassword(bean);

	}
	public Map menuAllocationInfo(AdminBean bean) {
		/*Change Details: removal UW Limit & admin user rights panel into single panel for sub-menu & button*/
		Map<Object, Map<Object, Map<String,Object>>> mainMap=new LinkedHashMap<Object, Map<Object, Map<String,Object>>>();
		try{
			List list=dao.menuAllocation(bean);
			Map m;
			Map menuMap=new LinkedHashMap();
			Map<String,Object> prosMap=new LinkedHashMap<String,Object>();
			Map<Object, Map<String,Object>> deptMap=new LinkedHashMap<Object, Map<String,Object>>();
			ArrayList processList = new ArrayList();
			/*Map<String,String> uwlimit=new LinkedHashMap<String,String>();*/
			boolean flag=dao.checkDeptAllocated(bean);
			if(flag){
				bean.setLsatus("true");
			}
			else{
				bean.setLsatus("false");
			}
			String[] temp=null;
			ArrayList<String> tempnew =new ArrayList<String>();
			int i=0;
			String prevoiusProd="";
			String prevoiusDept="";

			boolean dispValue=false;

			for(Object o:list){
				m=(Map)o;

				/*if("Y".equalsIgnoreCase((String)m.get("UW_LIMIT_YN"))){
					String value;

					try{
						value=((BigDecimal)m.get("UNDERWRITTIMG_LIMIT")).toString();

					}
					catch(Exception e){
						value="0";
					}

					uwlimit.put(m.get("PRODUCT_ID").toString()+","+(m.get("PROCESS_ID")).toString(),value);
				}*/

				if(null==mainMap.get(m.get("TMAS_PRODUCT_NAME"))){

					if(!flag){
						if(dispValue){
							tempnew.add(prevoiusProd+","+prevoiusDept);
							//temp[i]=prevoiusProd+","+prevoiusDept;
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";

						}
					}
					else{
						tempnew.add(m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString());
						//temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						i++;
					}

					prosMap=new LinkedHashMap<String, Object>();
					bean.setProcessId(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					prosMap = new LinkedHashMap<String, Object>();
					processList.add(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					prosMap.put(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString() + "_" + m.get("PROCESS_NAME"), menuAllocationNew(bean));
					ArrayList a = new ArrayList();
					a = bean.getTempSelectedMainMenu();
					if (bean.getSelectedMainMenu() == null) {
						bean.setSelectedMainMenu(a);
					} else {
						a.addAll(bean.getSelectedMainMenu());
						bean.setSelectedMainMenu(a);
					}

					deptMap=new LinkedHashMap<Object, Map<String,Object>>();
					deptMap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

					mainMap.put(m.get("TMAS_PRODUCT_NAME"),deptMap );

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}
				}
				else if (null == ((Map) mainMap.get(m.get("TMAS_PRODUCT_NAME")))
						.get(m.get("TMAS_DEPARTMENT_NAME"))) {

					if(!flag){
						if(dispValue){
							tempnew.add(prevoiusProd+","+prevoiusDept);
							//temp[i]=prevoiusProd+","+prevoiusDept;
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";
						}
							/*temp[i]=m.get("TMAS_PRODUCT_NAME").toString().replaceAll(" ", "")+","+m.get("TMAS_DEPARTMENT_NAME").toString().replaceAll(" ", "");
							i++;*/
					}
					else{
						tempnew.add(m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString());
						//temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						i++;
					}
					Map dmap = (Map) mainMap
							.get(m.get("TMAS_PRODUCT_NAME"));
					bean.setProcessId(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					prosMap = new LinkedHashMap();
					processList.add(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					prosMap.put(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString() + "_" + m.get("PROCESS_NAME"), menuAllocationNew(bean));
					ArrayList a = new ArrayList();
					a = bean.getTempSelectedMainMenu();
					if (bean.getSelectedMainMenu() == null) {
						bean.setSelectedMainMenu(a);
					} else {
						a.addAll(bean.getSelectedMainMenu());
						bean.setSelectedMainMenu(a);
					}
					dmap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

					mainMap.put((String)m.get("TMAS_PRODUCT_NAME"), dmap);

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}

				} else{
					Map procMap=(Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"));
					Map dmap=(Map)procMap.get(m.get("TMAS_DEPARTMENT_NAME"));
					bean.setProcessId(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					processList.add(m.get("PRODUCT_ID").toString() + "," + (m.get("PROCESS_ID")).toString());
					ArrayList a = new ArrayList();
					dmap.put(m.get("PRODUCT_ID").toString() + "," + m.get("PROCESS_ID") + "_" + m.get("PROCESS_NAME"), menuAllocationNew(bean));

					a = bean.getTempSelectedMainMenu();
					if (bean.getSelectedMainMenu() == null) {
						bean.setSelectedMainMenu(a);
					} else {
						a.addAll(bean.getSelectedMainMenu());
						bean.setSelectedMainMenu(a);
					}

					procMap.put(m.get("TMAS_DEPARTMENT_NAME"), dmap);
					mainMap.put((String)m.get("TMAS_PRODUCT_NAME"), procMap);
					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}
				}
			}
			if(!flag){
				if(dispValue){
					temp =new String[tempnew.size()+1];
					tempnew.add(temp[i]=prevoiusProd+","+prevoiusDept);
					//temp[i]=prevoiusProd+","+prevoiusDept;
					i++;
					dispValue=false;
					prevoiusProd="";
					prevoiusDept="";

				}
			}
			/*bean.setUwlimitMap(uwlimit);*/
			bean.setSelectedProcess(processList);
			bean.setSelectedDepartment(tempnew);

		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return mainMap;
	}
	public List<AdminBean> getExchangeListMaster(AdminBean bean) {
		return dao.getExchangeListMaster(bean);
	}
	public List<AdminBean> GetExchangeCurrency(AdminBean bean) {
		return dao.GetExchangeCurrency(bean);
	}
	public int insertExchangeRate(AdminBean bean) {
		return dao.insertExchangeRate(bean);
	}
	public void getEditExchangeMaster(AdminBean bean) {
		dao.getEditExchangeMaster(bean);
	}
	public int updateExchangeRate(AdminBean bean) {
		return dao.updateExchangeRate(bean);
	}
	public String GetSysDate(AdminBean bean) {
		return dao.GetSysDate(bean);
	}
	public String GetInceptionDate(AdminBean bean) {
		return dao.GetInceptionDate(bean);
	}
	public int getExcCoreAppCode(AdminBean bean) {
		return dao.getExcCoreAppCode(bean);
	}
	public int getExchangeCurrency(AdminBean bean) {
		return dao.getExchangeCurrency(bean);
	}
	public List<AdminBean> getProductList(AdminBean bean) {
		return dao.getProductList(bean);
	}
	public List<AdminBean> getDepartmentList(AdminBean bean) {
		return dao.getDepartmentList(bean);
	}
	public List<AdminBean> getProcessList(AdminBean bean) {
		return dao.getProcessList(bean);
	}
	public List<AdminBean> getMenuList(AdminBean bean) {
		return dao.getMenuList(bean);
	}
	public String getMenuId(AdminBean bean) {
		return dao.getMenuId( bean);
	}
	public int insertMenu(AdminBean bean) {
		return dao.insertMenu(bean);
	}
	public void getEditMenuMaster(AdminBean bean) {
		dao.getEditMenuMaster(bean);
	}
	public int updateMenu(AdminBean bean) {
		return dao.updateMenu(bean);
	}
	public int getMenuNameExist(AdminBean bean) {
		return dao.getMenuNameExist(bean);
	}
	public List<AdminBean> getDepartmentMasterList(AdminBean bean){
		return dao.getDepartmentMasterList(bean);
	}
	public int getInsertDepartment(AdminBean bean){
		return dao.getInsertDepartment(bean);
	}
	public int getUpdateDepartment(AdminBean bean){
		return dao.getUpdateDepartment(bean);
	}
	public void getEditDepartment(AdminBean bean){
		dao.getEditDepartment(bean);
	}
	public int getSContCoreCompanyCode(AdminBean bean) {
		return dao.getSContCoreCompanyCode(bean);
	}
	public int getcontDapartmentName(AdminBean bean) {
		return dao.getcontDepartmentName(bean);
	}

	public List<AdminBean> getProductMasterList(AdminBean bean) {
		return dao.getProductMasterList(bean);
	}
	public int getInsertProductMastert(AdminBean bean) {
		return dao.getInsertProductMaster(bean);
	}
	public int getUpdateProductMastert(AdminBean bean) {
		return dao.getUpdateProductMaster(bean);
	}
	public void getEditProductMastert(AdminBean bean) {
		dao.getEditProductMaster(bean);
	}
	public int getcontProductName(AdminBean bean) {
		return dao.getcontProductName(bean);
	}
	public int getcontCoreCompanyCode(AdminBean bean) {
		return dao.getcontCoreCompanyCode(bean);
	}
	public List<AdminBean> getterritoryList(AdminBean bean) {
		return dao.getterritoryList(bean);	}
	public int getUpdateterritoryMaster(AdminBean bean) {
		return dao.getUpdateterritoryMaster(bean);

	}
	public void getEditTerritoryMaster(AdminBean bean) {
		dao.getEditTerritoryMaster(bean);
	}
	public int getInsertTerritoryMaster(AdminBean bean) {
		return dao.getInsertTerritoryMaster(bean);
	}
	public int getcountCoreCompanyCode(AdminBean bean) {

		return dao.getcountCoreCompanyCode(bean);
	}
	public int getcontTerritoryDesc(AdminBean bean) {
		return dao.getcontTerritoryDesc(bean);
	}
	public List<AdminBean> getCategoryMasterList(AdminBean bean) {
		return dao.getCategoryMasterList(bean);
	}
	public void getEditCategoryZoneMaster(AdminBean bean) {
		dao.getEditCategoryZoneMaster(bean);
	}
	public int getUpdateCategoryZoneMaster(AdminBean bean) {
		return dao.getUpdateCategoryZoneMaster(bean);
	}
	public int getInsertCategoryMaster(AdminBean bean) {
		return dao.getInsertCategoryMaster(bean);
	}
	public int getcontCategoryName(AdminBean bean) {
		return dao.getcontCategoryName(bean);
	}
	public int getcountCoreAppCode(AdminBean bean) {
		return dao.getcountCoreAppCode(bean);
	}
	public List<AdminBean> getCurrencyMasterList(AdminBean bean) {
		return dao.getCurrencyMasterList(bean);
	}
	public void getEditCurrencyMaster(AdminBean bean) {
		dao.getEditCurrencyMaster(bean);
	}
	public int getUpdateCurrencyMaster(AdminBean bean) {
		return dao.getUpdateCurrencyMaster(bean);
	}
	public int getInsertCurrencyMaster(AdminBean bean) {
		return dao.getInsertCurrencyMaster(bean);
	}
	public List<AdminBean> getProcessMasterList(AdminBean bean) {
		return dao.getProcessMasterList(bean);
	}
	public void getEditprocessMaster(AdminBean bean) {
		dao.getEditprocessMaster(bean);

	}
	public int getUpdateprocessMaster(AdminBean bean) {
		return dao.getUpdateprocessMaster(bean);

	}
	public List<AdminBean> getCommonDepartmentList(AdminBean bean){
		return dao. getCommonDepartmentList(bean);

	}
	public int getInsertprocessMaster(AdminBean bean) {
		return dao.getInsertprocessMaster(bean);

	}
	public int getProcessName(AdminBean bean) {
		return dao.getProcessName(bean);
	}

	public int getcombination(AdminBean bean) {

		return dao.getcombination(bean);
	}
	public int getCoreAppCode(AdminBean bean) {
		return dao.getCoreAppCode(bean);
	}
	public int getCurrencyName(AdminBean bean) {

		return dao.getCurrencyName(bean);
	}
	public int getcountProfitCenter(AdminBean bean) {
		return dao.getcountProfitCenter(bean);
	}
	public int getprofitcontCoreCompanyCode(AdminBean bean) {
		return dao.getprofitcontCoreCompanyCode(bean);
	}
	public List<AdminBean> getSubprofitCenterList(AdminBean bean){

		return dao.getSubprofitCenterList(bean);

	}
	public int getInsertSubprofitCenter(AdminBean bean) {

		return dao.getInsertSubprofitCenter(bean);
	}
	public int getUpdateSubprofitCenter(AdminBean bean) {
		return 	dao.getUpdateSubprofitCenter(bean);

	}
	public void getEditSubprofitCenter(AdminBean bean) {
		dao.getEditSubprofitCenter(bean);
	}
	public int getContSubProfitCenter_Id(AdminBean bean){
		return dao.getContSubProfitCenter_Id(bean);
	}
	public int getContSubProfitCenter(AdminBean bean){
		return dao.getContSubProfitCenter(bean);
	}

	public List<AdminBean> getPolicyBranchList(AdminBean bean) {
		return dao.getPolicyBranchList(bean);
	}
	public int getInsertPolicyBranch(AdminBean bean) {
		return 	dao.getInsertPolicyBranch(bean);

	}
	public int getUpdatePolicyBranch(AdminBean bean) {

		return dao.getUpdatePolicyBranch(bean);
	}
	public void getEditPolicyBranch(AdminBean bean) {
		dao.getEditPolicyBranch(bean);
	}
	public int getContpolicyCoreCompanyCode(AdminBean bean){
		return dao.getContpolicyCoreCompanyCode(bean);
	}
	public int getContPolicyBranch(AdminBean bean){
		return dao.getContPolicyBranch(bean);
	}
	public List<AdminBean> getUnderWriterList(AdminBean bean) {
		return dao.getUnderWriterList(bean);

	}
	public int getInsertunderwritertMaster(AdminBean bean){
		return 	dao.getInsertunderwritertMaster(bean);

	}
	public int getUpdateunderwritertMaster(AdminBean bean){
		return dao.getUpdateunderwritertMaster(bean);

	}
	public void getEditunderwritertMaster(AdminBean bean){
		dao.getEditunderwritertMaster(bean);
	}
	public int getContunderwriterCoreCompanyCode(AdminBean bean){
		return dao.getContunderwriterCoreCompanyCode(bean);
	}
	public int getContUnderWriterId(AdminBean bean){
		return dao.getContUnderWriterId(bean);
	}
	public List<AdminBean> getRiskGradeList(AdminBean bean) {
		return dao.getRiskGradeList(bean);

	}
	public void getEditriskgradeMaster(AdminBean bean){
		dao.getEditriskgradeMaster(bean);
	}
	public int getUpdateriskgradeMaster(AdminBean bean){
		return 	dao.getUpdateriskgradeMaster(bean);

	}
	public int getInsertriskgradeMaster(AdminBean bean){
		return 	dao.getInsertriskgradeMaster(bean);

	}/*
	public int getContRiskGradeId(AdminBean bean){
		return dao.getContRiskGradeId(bean);
	}*/
	public int getContRiskGradeCoreCompanyCode(AdminBean bean){
		return dao.getContRiskGradeCoreCompanyCode(bean);
	}
	public List<AdminBean>getCountryList(AdminBean bean){
		return dao.getCountryList(bean);
	}
	public void getEditInsertCountryMaster(AdminBean bean){
		dao.getEditCountryMaster(bean);
	}
	public int getUpdateCountryMaster(AdminBean bean){
		return dao.getUpdateCountryMaster(bean);
	}
	public int getInsertCountryMaster(AdminBean bean){

		return dao.getInsertCountryMaster(bean);
	}
	public int getContcountryMasterCoreCompanyCode(AdminBean bean){
		return dao.getContcountryMasterCoreCompanyCode(bean);
	}
	public int getContcountryName(AdminBean bean){
		return dao.getContcountryName(bean);
	}
	public int getaddprocessMaster(AdminBean bean){
		return dao.getaddprocessMaster(bean);
	}
	public int getupdateprocessMaster(AdminBean bean){
		return dao.getupdateprocessMaster(bean);
	}
	public int getContProcessName(AdminBean bean){
		return dao.getContProcessName(bean);
	}
	public List<AdminBean> getBranchList(AdminBean bean){
		return dao.getBranchList(bean);
	}
	public List<AdminBean>getCountryIdList(AdminBean bean){
		return dao.getCountryIdList(bean);
	}
	public List<AdminBean>getDcurrencyList(AdminBean bean){
		return dao.getDcurrencyList(bean);
	}
	public List<AdminBean>getBaseCurrencyList(AdminBean bean){
		return dao.getBaseCurrencyList(bean);
	}
	public List<AdminBean>getCedingList(AdminBean bean){
		return dao.getCedingList(bean);
	}
	public int getinsertcedingMaster(AdminBean bean){
		return dao.getinsertcedingMaster(bean);
	}
	public void geteditcedingMaster(AdminBean bean){
		dao.geteditcedingMaster(bean);
	}
	public int getupdatecedingMaster(AdminBean bean){
		return dao.getupdatecedingMaster(bean);
	}
	public int getContcedingMasterCoreCompanyCode(AdminBean bean){
		return dao.getContcedingMasterCoreCompanyCode(bean);
	}
	public int getinsertbranchMaster(AdminBean bean){
		return dao.getinsertbranchMaster(bean);
	}
	public void getEditbranchMaster(AdminBean bean){
		dao.getEditbranchMaster(bean);
	}
	public String getbranchCode1(AdminBean bean){
		return dao.getbranchCode1(bean);
	}
	public int getupdatebranchMaster(AdminBean bean){
		return dao.getupdatebranchMaster(bean);
	}
	public int getContbranchMasterCoreCompanyCode(AdminBean bean){
		return dao.getContbranchMasterCoreCompanyCode(bean);
	}
	public List<AdminBean>getOpenList(AdminBean bean){
		return dao.getOpenList(bean);
	}
	public int getinsertopenperiodMaster(AdminBean bean){
		return dao.getinsertopenperiodMaster(bean);
	}
	public void getEditopenperiodMaster(AdminBean bean){
		dao.getEditopenperiodMaster(bean);
	}
	public int getupdateopenperiodMaster(AdminBean bean){
		return dao.getupdateopenperiodMaster(bean);
	}
	public List<AdminBean>getSubMenuList(AdminBean bean){
		return dao.getSubMenuList(bean);
	}
	public int getinsertsubmenuMaster(AdminBean bean){
		return dao.getinsertsubmenuMaster(bean);
	}
	public void getEditsubmenuMaster(AdminBean bean){
		dao.getEditsubmenuMaster(bean);
	}
	public int getupdatsubmenuMaster(AdminBean bean){
		return dao.getupdatsubmenuMaster(bean);
	}
	public void getinsertClientMaster(AdminBean bean,String branchCode){
		dao.getinsertClientMaster(bean, branchCode);
	}
	public Map<String, String> AdminMenuAllocation(AdminBean bean) {
		Map result=new LinkedHashMap();
		ArrayList tempnew =new ArrayList();
		List menus=new ArrayList();

		try {
			List list=dao.AdminMenuAllcationInfo(bean);
			String menuids=dao.getAdminMenuids(bean);
			boolean flag=false;
			if("".equalsIgnoreCase(menuids)){
				flag=true;
			}
			else{
				String[] str=menuids.substring(1, menuids.length()).split(",");
				for(String s:str){
					menus.add(s);
				}
			}
			int i=0;
			for(Object o:list){
				Map m=(Map)o;
				result.put(((BigDecimal)m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
				if(flag){
					tempnew.add(((BigDecimal)m.get("MENU_ID")).toString());
					i++;
				}
				else{
					if(menus.contains(((BigDecimal)m.get("MENU_ID")).toString())){
						tempnew.add(((BigDecimal)m.get("MENU_ID")).toString());
						i++;
					}
				}
			}

			bean.setSelectedMenus(tempnew);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public Map<String, Object> AdminSubMenuAllocation(AdminBean bean) {
		Map mainMap=new LinkedHashMap();
		try{
			List list=dao.AdminSubMenuAllcationInfo(bean);
			Map m;
			Map menuMap=new LinkedHashMap();
			Map prosMap=new LinkedHashMap();
			Map deptMap=new LinkedHashMap();
			Map uwlimitMap=new LinkedHashMap();
			boolean flag=dao.getAdminSubMenuids(bean);
			if(flag){
				bean.setLsatus("true");
			}
			else{
				bean.setLsatus("false");
			}
			String[] temp=new String[20];
			ArrayList<String> tempnew =new ArrayList<String>();
			int i=0;
			String prevoiusProd="";
			String prevoiusDept="";
			boolean dispValue=false;
			for(Object o:list){
				m=(Map)o;
				if(null==mainMap.get(m.get("TMAS_PRODUCT_NAME"))){
					if(!flag){
						if(dispValue){
							//temp[i]=prevoiusProd+","+prevoiusDept;
							tempnew.add(prevoiusProd+","+prevoiusDept);
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";
						}

					}
					else{
						//temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						tempnew.add(m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString());
						i++;
					}

					uwlimitMap.put((String)m.get("TMAS_PRODUCT_NAME")+m.get("TMAS_DEPARTMENT_NAME")+m.get("PROCESS_NAME"),m.get("UNDERWRITTIMG_LIMIT"));

					menuMap=new LinkedHashMap();
					//menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+(m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					prosMap=new LinkedHashMap();
					prosMap.put(m.get("PROCESS_NAME"),menuMap);

					deptMap=new LinkedHashMap();
					deptMap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

					mainMap.put(m.get("TMAS_PRODUCT_NAME"),deptMap );

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}
				}
				else if (null == ((Map) mainMap.get(m.get("TMAS_PRODUCT_NAME")))
						.get(m.get("TMAS_DEPARTMENT_NAME"))) {

					uwlimitMap.put((String)m.get("TMAS_PRODUCT_NAME")+m.get("TMAS_DEPARTMENT_NAME")+m.get("PROCESS_NAME"),m.get("UNDERWRITTIMG_LIMIT"));
					if(!flag){
						if(dispValue){
							//temp[i]=prevoiusProd+","+prevoiusDept;
							tempnew.add(prevoiusProd+","+prevoiusDept);
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";
						}
					}
					else{
						//temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						tempnew.add(m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString());
						i++;
					}

					Map dmap =(Map) mainMap.get(m.get("TMAS_PRODUCT_NAME"));

					menuMap=new LinkedHashMap();
					//menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+(m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					prosMap=new LinkedHashMap();
					prosMap.put(m.get("PROCESS_NAME"),menuMap);

					dmap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

					mainMap.put((String)m.get("TMAS_PRODUCT_NAME"), dmap);

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}

				} else if(null==((Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME")))
						.get(m.get("TMAS_DEPARTMENT_NAME"))).get(m.get("PROCESS_NAME"))){

					uwlimitMap.put((String)m.get("TMAS_PRODUCT_NAME")+m.get("TMAS_DEPARTMENT_NAME")+m.get("PROCESS_NAME"),m.get("UNDERWRITTIMG_LIMIT"));
					Map dmap=(Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"));
					Map pmap=(Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"));

					menuMap=new LinkedHashMap();
					//menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					menuMap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+(m.get("MENU_ID")).toString(),m.get("MENU_NAME"));
					pmap.put(m.get("PROCESS_NAME"), menuMap);
					dmap.put(m.get("TMAS_DEPARTMENT_NAME"),pmap);

					mainMap.put(m.get("TMAS_PRODUCT_NAME"), dmap);

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}

				}
				else{

					Map dmap=(Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"));
					Map pmap=(Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"));
					Map mmap=(Map)((Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"))).get(m.get("PROCESS_NAME"));

					//mmap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
					mmap.put(m.get("PRODUCT_ID")+","+m.get("PROCESS_ID")+","+(m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
					pmap.put(m.get("PROCESS_NAME"),mmap);
					dmap.put(m.get("TMAS_DEPARTMENT_NAME"),pmap);

					mainMap.put(m.get("TMAS_PRODUCT_NAME"), dmap);

					if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}
				}
			}
			if(!flag){
				if(dispValue){
					//temp[i]=prevoiusProd+","+prevoiusDept;
					tempnew.add(prevoiusProd+","+prevoiusDept);
					i++;
					dispValue=false;
					prevoiusProd="";
					prevoiusDept="";

				}
			}
			//bean.setSelectedDepartments(temp);
			bean.setSelectedDepartment(tempnew);
			bean.setUwlimitMap(uwlimitMap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return mainMap;
	}
	public Map<Object,Object> SubmenuAllocationInfo(AdminBean bean) {
		Map<Object,Object> result=new LinkedHashMap<Object,Object>();
		try{
			int i=0;
			List list=dao.submenuAllocation(bean);
			String[] temp=new String[30];
			ArrayList<String> tempnew =new ArrayList<String>();
			for(Object o:list){
				Map m=(Map)o;
				result.put(bean.getProcessId() + "," + m.get("SUB_MENU_CODE"), m.get("SUB_MENU_NAME"));
				if ("Y".equalsIgnoreCase((String) m.get("STATUS"))) {
					//temp[i]=m.get("SUB_MENU_CODE").toString();
					tempnew.add(bean.getProcessId() + "," + m.get("SUB_MENU_CODE").toString());
					i++;
				}
			}
			bean.setTempSelectedSubMenuButton(tempnew);
		} catch (Exception exception) {

		}

		return result;
	}
	public int getPolicyCoreCompanyCode(AdminBean bean) {
		return dao.getPolicyCoreCompanyCode(bean);
	}
	public int getCountRiskGradeName(AdminBean bean) {
		return dao.getCountRiskGradeName(bean);
	}
	public int getCountProfitId(AdminBean bean) {
		return dao.getCountProfitId(bean);
	}
	public int getCountSubMenuCode(AdminBean bean) {
		return dao.getCountSubMenuCode(bean);
	}
	public int getCountSubMenuName(AdminBean bean) {
		return dao.getCountSubMenuName(bean);
	}
	public int getContCoreCompanyCode(AdminBean bean) {
		return dao.getContCoreCompanyCode(bean);
	}
	public boolean menuGlobalSubmit(AdminBean bean) {
		boolean flag=false;
		try{
			boolean firstUserOrNot=dao.checkDeptMenuAllocated(bean);
			if(firstUserOrNot){
				boolean temp=dao.MenuGlobalSubmit(bean);

				if(temp){
					//flag=BusinessImpl.updateUWLimit(beanObj);
					flag=updateMenuGlobalSubmit(bean);
				}
				else{
					flag=false;
				}
			}
			else{
				flag=updateMenuGlobalSubmit(bean);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return flag;
	}
	private boolean updateSubMenuGlobalSubmit(AdminBean bean) {
		boolean flag=true;

		ArrayList<String> selectedDept=bean.getSelectedDepartment();
		//String[] selectedDept=bean.getSelectedDepartments();
		if(selectedDept.contains("false")){
			selectedDept=new ArrayList<String>();
		}
		Map<String,Set<String>> previousDept=new LinkedHashMap<String,Set<String>>();

		Set<String> dept=new HashSet<String>();
		List list=dao.menuAllocation(bean);
		Map<String,List<String>> uwlMap=new LinkedHashMap<String,List<String>>();
		for(Object o:list){
			Map m=(Map)o;
			if(null==uwlMap.get(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"))){
				List l=new ArrayList();
				l.add((m.get("PROCESS_ID")).toString());
				uwlMap.put(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"),l);
			}
			else{
				List l=uwlMap.get(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"));
				l.add((m.get("PROCESS_ID")).toString());
				uwlMap.put(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"),l);
			}

			if ("Y".equalsIgnoreCase((String) m.get("ALLOCATED_YN"))) {

				if (null == previousDept.get((String) m.get("TMAS_PRODUCT_NAME"))) {
					Set<String> deptset = new HashSet<String>();
					deptset.add((String) m.get("TMAS_DEPARTMENT_NAME"));
					previousDept.put((String) m.get("TMAS_PRODUCT_NAME"),deptset);
				} else {
					dept = previousDept.get((String) m.get("TMAS_PRODUCT_NAME"));
					dept.add((String) m.get("TMAS_DEPARTMENT_NAME"));
					previousDept.put((String) m.get("TMAS_PRODUCT_NAME"),dept);
				}
			}
		}
		List<String> plist=new ArrayList<String>();
		for(String s:previousDept.keySet()){
			Set<String> set=previousDept.get(s);
			for(String o:set){
				plist.add(s+","+o);
			}
		}

		List<String> nlist=new ArrayList<String>();
		for(String o:selectedDept){
			nlist.add(o);
		}

		List<String> commonlist=new ArrayList<String>();


		for(String o:nlist){
			if(plist.contains(o)){
				commonlist.add(o);
			}
		}

		plist.removeAll(commonlist);
		nlist.removeAll(commonlist);

		for(String s:commonlist){
			boolean udata=dao.updateSubMenuGlobalSubmit(bean, "1", s);

			if(false==udata){
				flag=false;
			}
		}
		for(String s:nlist){
			boolean udata=dao.updateSubMenuGlobalSubmit(bean, "1", s);

			if(false==udata){
				flag=false;
			}
		}
		for(String s:plist){
			boolean udata=dao.updateSubMenuGlobalSubmit(bean, "0", s);

			if(false==udata){
				flag=false;
			}
		}
		return flag;
	}
	public Map allocationInfo(AdminBean bean) {

		Map result=new LinkedHashMap();

		Map mainMap=new LinkedHashMap();

		List list=dao.allocationInfo(bean);
		Map m;

		Map menuMap=new LinkedHashMap();
		Map prosMap=new LinkedHashMap();
		Map deptMap=new LinkedHashMap();


		for(Object o:list){
			m=(Map)o;

			if(null==mainMap.get(m.get("TMAS_PRODUCT_NAME"))){

					/*if(!flag){
						if(dispValue){
							temp[i]=prevoiusProd+","+prevoiusDept;
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";
						}

					}
					else{
						temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						i++;
					}*/

				menuMap=new LinkedHashMap();
				menuMap.put(m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));

				prosMap=new LinkedHashMap();
				prosMap.put(m.get("PROCESS_NAME"),menuMap);

				deptMap=new LinkedHashMap();
				deptMap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

				mainMap.put(m.get("TMAS_PRODUCT_NAME"),deptMap );

					/*if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}*/
			}
			else if (null == ((Map) mainMap.get(m.get("TMAS_PRODUCT_NAME")))
					.get(m.get("TMAS_DEPARTMENT_NAME"))) {

					/*if(!flag){
						if(dispValue){
							temp[i]=prevoiusProd+","+prevoiusDept;
							i++;
							dispValue=false;
							prevoiusProd="";
							prevoiusDept="";
						}
					}
					else{
						temp[i]=m.get("TMAS_PRODUCT_NAME").toString()+","+m.get("TMAS_DEPARTMENT_NAME").toString();
						i++;
					}*/

				Map dmap =(Map) mainMap.get(m.get("TMAS_PRODUCT_NAME"));

				menuMap=new LinkedHashMap();
				menuMap.put(m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));

				prosMap=new LinkedHashMap();
				prosMap.put(m.get("PROCESS_NAME"),menuMap);

				dmap.put(m.get("TMAS_DEPARTMENT_NAME"),prosMap);

				mainMap.put((String)m.get("TMAS_PRODUCT_NAME"), dmap);

					/*if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}*/

			} else if(null==((Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME")))
					.get(m.get("TMAS_DEPARTMENT_NAME"))).get(m.get("PROCESS_NAME"))){

				Map dmap=(Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"));
				Map pmap=(Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"));

				menuMap=new LinkedHashMap();
				menuMap.put(m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(),m.get("MENU_NAME"));

				pmap.put(m.get("PROCESS_NAME"), menuMap);
				dmap.put(m.get("TMAS_DEPARTMENT_NAME"),pmap);

				mainMap.put(m.get("TMAS_PRODUCT_NAME"), dmap);

					/*if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}*/

			}
			else{

				Map dmap=(Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"));
				Map pmap=(Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"));
				Map mmap=(Map)((Map)((Map)mainMap.get(m.get("TMAS_PRODUCT_NAME"))).get(m.get("TMAS_DEPARTMENT_NAME"))).get(m.get("PROCESS_NAME"));

				mmap.put(m.get("PROCESS_ID")+","+((BigDecimal)m.get("MENU_ID")).toString(), m.get("MENU_NAME"));

				pmap.put(m.get("PROCESS_NAME"),mmap);
				dmap.put(m.get("TMAS_DEPARTMENT_NAME"),pmap);

				mainMap.put(m.get("TMAS_PRODUCT_NAME"), dmap);

					/*if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
						dispValue=true;
						prevoiusProd=m.get("TMAS_PRODUCT_NAME").toString();
						prevoiusDept=m.get("TMAS_DEPARTMENT_NAME").toString();
					}*/

			}

			/*for(Object o:mainMap.keySet()){
				LogManager.info(""+o.toString());
				for(Object o1:((Map)mainMap.get(o)).keySet()){
					LogManager.info("     "+o1.toString());
					Map ma=(Map)((Map)mainMap.get(o)).get(o1);
					for(Object o2:ma.keySet()){
						LogManager.info("           "+o2.toString());
						Map mm=(Map)((Map)(((Map)mainMap.get(o)).get(o1))).get(o2);
						for(Object st:mm.keySet()){
							LogManager.info("                "+st+"=="+mm.get(st));
						}
					}
				}
			}*/
			result=mainMap;
		}


		return result;

	}
	public boolean SaveManu(AdminBean bean) {
		boolean flag=false;
		ArrayList<String> temp=bean.getSelectedMenus();
		String menuids="";
		for(String o:temp){
			menuids=menuids+","+o;
		}
		if("".equalsIgnoreCase(menuids)){
			menuids=",,";
		}
		else{
			menuids=menuids+",";
		}
		flag=dao.SaveManu(bean,menuids);

		return flag;

	}
	public Map allocationList(AdminBean bean) {

		Map result=new LinkedHashMap();
		List list=dao.allocationList(bean);
		Map m;
		String[] temp=new String[40];
		ArrayList<String>tempnew=new ArrayList<String>();
		int i=0;
		for(Object o:list){
			m=(Map)o;
			if("Y".equalsIgnoreCase((String)m.get("ALLOCATED_YN"))){
				tempnew.add((String)m.get("SUB_MENU_CODE"));
				//temp[i]=(String)m.get("SUB_MENU_CODE");
				i++;
			}
			result.put(m.get("SUB_MENU_CODE"), m.get("SUB_MENU_NAME"));
		}
			/*for(Object o:result.keySet()){
				LogManager.info(o+"---"+result.get(o));
			}*/
		//bean.setSelectedSubMenus(temp);
		bean.setSelectedSubMenu(tempnew);
		return result;
	}
	public boolean subMenuSave(AdminBean bean) {
		boolean flag=false;
		ArrayList<String> temp=bean.getSelectedSubMenu();
		if(temp==null){
			temp=new ArrayList<String>();
		}
		String submenuids="";
		for(String o:temp){
			submenuids=submenuids+","+o;
		}
		if("".equalsIgnoreCase(submenuids)){
			submenuids=",,";
		}
		else{
			submenuids=submenuids+",";
		}
		flag=dao.subMenuSave(bean,submenuids);
		return flag;

	}
	public Map menuAllocation(AdminBean bean) {
		Map result=new LinkedHashMap();
		String[] temp=new String[30];
		ArrayList tempnew =new ArrayList();
		Date d=new Date();

		List list=dao.menubuttonAllocation(bean);
		boolean flag=dao.menuCheck(bean);
		int i=0;
		for(Object o:list){
			Map m=(Map)o;
			//result.put(((BigDecimal)m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
			result.put((m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
			if(flag){
				if("Y".equalsIgnoreCase((String)m.get("STATUS"))){
					//temp[i]=((BigDecimal)m.get("MENU_ID")).toString();
					tempnew.add(((BigDecimal)m.get("MENU_ID")).toString());
					//temp[i]=(m.get("MENU_ID")).toString();
					i++;
				}
			}
			else{
				//temp[i]=((BigDecimal)m.get("MENU_ID")).toString();
				tempnew.add(((BigDecimal)m.get("MENU_ID")).toString());
				//temp[i]=(m.get("MENU_ID")).toString();
				i++;
			}
		}
		bean.setSelectedMenus(tempnew);
		String path=dao.menuAllocationPath(bean);
		bean.setMenuPath(path);
		return result;
	}
	public boolean subMenuGlobalSubmit(AdminBean bean) {
		boolean flag=false;

		boolean firstUserOrNot=dao.checkDeptsubMenuAllocated(bean);
		if(firstUserOrNot){
			boolean temp=dao.subMenuGlobalSubmit(bean);

			if(temp){
				flag=updateSubMenuGlobalSubmit(bean);
			}
			else{
				flag=false;
			}
		}
		else{
			flag=updateSubMenuGlobalSubmit(bean);
		}
		return flag;
	}
	private boolean updateMenuGlobalSubmit(AdminBean bean) {
		boolean flag=true;

		//String[] selectedDept=bean.getSelectedDepartments();
		ArrayList<String> selectedDept=bean.getSelectedDepartment();
		Map<String,Set<String>> previousDept=new LinkedHashMap<String,Set<String>>();

		Set<String> dept=new HashSet<String>();
		List list=dao.getPrevoiusValues(bean);
		Map<String,List<String>> uwlMap=new LinkedHashMap<String,List<String>>();
		for(Object o:list){
			Map m=(Map)o;
			if(null==uwlMap.get(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"))){
				List l=new ArrayList();
				l.add((m.get("PROCESS_ID")).toString());
				uwlMap.put(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"),l);
			}
			else{
				List l=uwlMap.get(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"));
				l.add((m.get("PROCESS_ID")).toString());
				uwlMap.put(m.get("TMAS_PRODUCT_NAME")+","+m.get("TMAS_DEPARTMENT_NAME"),l);
			}

			if ("Y".equalsIgnoreCase((String) m.get("ALLOCATED_YN"))) {

				if (null == previousDept.get((String) m.get("TMAS_PRODUCT_NAME"))) {
					Set<String> deptset = new HashSet<String>();
					deptset.add((String) m.get("TMAS_DEPARTMENT_NAME"));
					previousDept.put((String) m.get("TMAS_PRODUCT_NAME"),deptset);
				} else {
					dept = previousDept.get((String) m.get("TMAS_PRODUCT_NAME"));
					dept.add((String) m.get("TMAS_DEPARTMENT_NAME"));
					previousDept.put((String) m.get("TMAS_PRODUCT_NAME"),dept);
				}
			}
		}
		List<String> plist=new ArrayList<String>();
		for(String s:previousDept.keySet()){
			Set<String> set=previousDept.get(s);
			for(String o:set){
				plist.add(s+","+o);
			}
		}
		List<String> nlist=new ArrayList<String>();
		for(String o:selectedDept){
			nlist.add(o);
		}
		List<String> commonlist=new ArrayList<String>();


		for(String o:nlist){
			if(plist.contains(o)){
				commonlist.add(o);
			}
		}

		plist.removeAll(commonlist);
		nlist.removeAll(commonlist);

		Map selecteduwMap=bean.getUwlimitMap();
		Map nuwlimit=new HashMap();
		for(Object o:selecteduwMap.keySet()){
			String[] st=((String)o).split(",");
			nuwlimit.put(st[1],selecteduwMap.get(o));
		}
		for(String s:commonlist){
			boolean udata=dao.update(bean, "1", s);
			boolean ulimit=true;
			List l=uwlMap.get(s);
			List addList=new ArrayList();

			for(Object o:nuwlimit.keySet()){
				if(l.contains(o)){
					addList.add(o);
				}
			}
			for(Object str:addList){
				ulimit=false;
				ulimit=dao.updateUWLimit(bean,(String)nuwlimit.get(str),(String)str);
			}
			if((false==udata) || (false==ulimit)){
				flag=false;
			}
		}
		for(String s:nlist){
			boolean udata=dao.update(bean, "1", s);
			boolean ulimit=true;

			List l=uwlMap.get(s);
			List addList=new ArrayList();

			for(Object o:nuwlimit.keySet()){
				if(l.contains(o)){
					addList.add(o);
				}
			}
			for(Object str:addList){
				ulimit=false;
				ulimit=dao.updateUWLimit(bean,(String)nuwlimit.get(str),(String)str);
			}
			if((false==udata) || (false==ulimit)){
				flag=false;
			}
		}
		for(String s:plist){
			boolean udata=dao.update(bean, "0", s);
			boolean ulimit=true;

			List l=uwlMap.get(s);
			List addList=new ArrayList();

			for(Object o:nuwlimit.keySet()){
				if(l.contains(o)){
					addList.add(o);
				}
			}
			for(Object str:addList){
				ulimit=false;
				ulimit=dao.updateUWLimit(bean,"0",(String)str);
			}
			if((false==udata) || (false==ulimit)){
				flag=false;
			}
		}


		return flag;
	}
	public boolean saveAdminMenus(AdminBean bean) {
		boolean flag=false;
		ArrayList tempnew =new ArrayList();
		tempnew=bean.getSelectedMenus();
		String menus="";
		for(Object s:tempnew){
			menus=menus+","+s;
		}
		if(menus.length()==0){
			menus=",,";
		}
		else{
		}
		flag=dao.saveAdminMenus(bean,menus);

		return flag;
	}
	public boolean subMenuAllocationSave(AdminBean bean) {
		boolean flag=false;
		ArrayList tempnew =new ArrayList();
		tempnew=bean.getSelectedSubMenu();
		if(tempnew==null){
			tempnew=new ArrayList();
		}
		String str="";
		for(Object s:tempnew){
			str=str+s+",";
		}
		if(!("".equalsIgnoreCase(str))){
			str=(String) str.subSequence(0, str.length()-1);
		}
		bean.setMenuidList(str);
		flag=dao.subMenuAllocationSave(bean);

		return flag;
	}
	public List<AdminBean> getUWLimitMasterList(AdminBean bean) {
		return dao.getUWLimitMasterList(bean);
	}
	public int getInsertUWLimitMaster(AdminBean bean) {
		return dao.getInsertUWLimitMaster(bean);
	}
	public void getEditUWLimitMaster(AdminBean bean) {
		dao.getEditUWLimitMaster(bean);

	}
	public int getUpdateUWLimitMaster(AdminBean bean) {
		return dao.getUpdateUWLimitMaster(bean);
	}
	public int getUWLimitcombination(AdminBean bean) {
		return dao.getUWLimitcombination(bean);
	}
	public List<Map<String, Object>> getDropDownTerritoryList(AdminBean bean, String branchCode) {
		return dao.getDropDownTerritoryList(bean,branchCode);
	}
	public List<Map<String, Object>> getCrestaMasterList(AdminBean bean,String branchCode) {
		return dao.getCrestaMasterList(bean,branchCode);
	}
	public void EditCresta(AdminBean bean, String branchCode) {
		dao.EditCresta(bean,branchCode);
	}
	public void CrestaInsertUpdate(AdminBean bean, String branchCode) {
		dao.CrestaInsertUpdate(bean,branchCode);
	}
	public List<Map<String, Object>> getRateMasterList(AdminBean bean,String branchCode) {
		return dao.getRateMasterList(bean,branchCode);
	}
	public List<Map<String, Object>> getDropDownTypeList(AdminBean bean) {
		return dao.getDropDownTypeList(bean);
	}

	public void RateInsertUpdate(AdminBean bean, String branchCode) {
		dao.RateInsertUpdate(bean,branchCode);
	}
	public void RateMasterDelete(AdminBean bean, String branchCode) {
		dao.RateMasterDelete(bean,branchCode);
	}
	public String dateComparition(AdminBean bean, String branchCode) {
		return dao.dateComparition(bean,branchCode);
	}
	public List<Map<String, Object>> getEditAddRateMaster(AdminBean bean,String branchCode) {
		return dao.getEditAddRateMaster(bean,branchCode);
	}
	public List<Map<String, Object>> getDropDownResidentalList(AdminBean bean) {
		return dao.getDropDownResidentalList(bean);
	}

	public List<Map<String, Object>> getbankCurrencyList(AdminBean bean) {
		return dao.getbankCurrencyList(bean);
	}
	public List<Map<String, Object>> gettdsTypeList(AdminBean bean, String string) {
		return dao.gettdsTypeList(bean,string);
	}
	public List<Map<String, Object>> getclientPersonalList(AdminBean bean, String branchCode) {
		return dao.getclientPersonalList(bean,branchCode);
	}
	public void getEditClientMaster(AdminBean bean, String branchCode) {
		dao.getEditClientMaster(bean,branchCode);

	}

	public void getupdateClientMaster(AdminBean bean, String branchCode) {
		dao.getupdateClientMaster(bean,branchCode);

	}
	public List<Map<String, Object>> getbroGroupList(AdminBean bean) {
		return dao.getbroGroupList(bean);
	}
	public String doUpload(AdminBean bean, String branchCode, String userId,List<File> upload, List<String> uploadFileName) {
		String result="";
		try{
			List<Object[]> list = new ArrayList<Object[]>();
			Object args[]=new Object[5];
			String filePath=bean.getFilePath();

			filePath +="PER"+"/"+bean.getCustomerId()+"/";

			File tmpFile = new File(filePath);
			if(!tmpFile.exists()){
				tmpFile.mkdir();
			}

			List<String> docId= bean.getDocId();
			List<File> doc = upload;
			List<String> docName = uploadFileName;
			List<String> docDesc= bean.getDocDesc();
			for(int i=(Integer.parseInt(bean.getStartIndex()));i<Integer.parseInt(bean.getEndIndex());i++)
			{
				final String orgFileName=docName.get(i);
				Calendar cal = Calendar.getInstance();
				String time = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"
						+cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
				String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
				String fileName = (bean.getDocId().get(i))+"_"+orgFileName.substring(0, orgFileName.lastIndexOf("."))+"_"+time;
				fileName = fileName + ext;
				final File copyFile = new File(filePath+fileName);
				FileUtils.copyFile(doc.get(i), copyFile);

				final Object[] obj = new Object[14];
				obj[0] = docId.get(i);
				obj[1] = "";
				obj[2] = docDesc.get(i);
				obj[3] = orgFileName;
				obj[4] = fileName;
				obj[5] = filePath;
				obj[6] = "PER";
				obj[7] = "";
				obj[8] = "";
				obj[9] = "";
				obj[10] = "";
				obj[11] = bean.getCustomerId();
				obj[12] = branchCode;
				obj[13] = userId;
				list.add(obj);

			}
			if(list.size()>0){
				result = dao.doUploadDocDetails(list);
			}
		}
		catch(Exception exception) {
			result = "Error while saving the Files => " + exception.getMessage();
		}
		return result;
	}
	public List<Map<String, Object>> getDocList(AdminBean bean,String branchCode) {

		return dao.getDocList(bean,branchCode);
	}
	public String doDeleteDocDetails(AdminBean bean, String loginId,String branchCode) {

		return dao.doDeleteDocDetails(bean,loginId,branchCode);
	}

	public Map menuAllocationNew(AdminBean bean) {
		Map result = new LinkedHashMap();
		String[] temp = new String[30];
		ArrayList tempnew = new ArrayList();
		Date d = new Date();

		List list = dao.menubuttonAllocation(bean);
		boolean flag = dao.menuCheck(bean);
		int i = 0;
		for (Object o : list) {
			Map m = (Map) o;
			//result.put(((BigDecimal)m.get("MENU_ID")).toString(), m.get("MENU_NAME"));
			String processIdTemp0 = bean.getProcessId() + "," + m.get("MENU_ID").toString();
			String processIdTemp1 = bean.getProcessId();

			bean.setProcessId(processIdTemp0);

			result.put(processIdTemp1 + "," + (m.get("MENU_ID")).toString() + "_" + m.get("MENU_NAME"), SubmenuAllocationInfo(bean));
			ArrayList a = new ArrayList();
			a = bean.getTempSelectedSubMenuButton();
			if (bean.getSelectedSubMenuButton() == null) {
				bean.setSelectedSubMenuButton(a);
			} else {
				a.addAll(bean.getSelectedSubMenuButton());
				bean.setSelectedSubMenuButton(a);
			}
			bean.setProcessId(processIdTemp1);

			if (flag) {
				if ("Y".equalsIgnoreCase((String) m.get("STATUS"))) {
					//temp[i]=((BigDecimal)m.get("MENU_ID")).toString();
					tempnew.add(bean.getProcessId() + "," + ((BigDecimal) m.get("MENU_ID")).toString());
					//temp[i]=(m.get("MENU_ID")).toString();
					i++;
				}
			}
		}
		bean.setTempSelectedMainMenu(tempnew);
		String path = dao.menuAllocationPath(bean);
		bean.setMenuPath(path);
		return result;
	}

	public int countComma(String inputString) {
		int count = 0;

		for (int x = 0; x < inputString.length(); x++) {
			if (inputString.charAt(x) == ',') {
				count++;
			}
		}
		return count;
	}

	public boolean insertMainMenu(AdminBean bean, ArrayList<String> addMainMenuList) {
		boolean result = true;
		Map addMap = new HashMap();
		for (String s : addMainMenuList) {
			String str[] = s.split(",");
			String list;
			if (addMap.containsKey(str[0] + "," + str[1])) {
				list = (String) addMap.get(str[0] + "," + str[1]);
				list += "," + str[2];
				addMap.put(str[0] + "," + str[1], list);
			} else {
				list = new String();
				list += str[2];
				addMap.put(str[0] + "," + str[1], list);
			}
		}
		result = dao.insertMainMenu(bean, addMap);
		return result;
	}

	public boolean insertSubMenu(AdminBean bean, ArrayList<String> addSubMenuList) {
		boolean addResult = true;
		Map addMap = new HashMap();
		for (String s : addSubMenuList) {
			String str[] = s.split(",");
			String list;
			if (addMap.containsKey(str[0] + "," + str[1] + "," + str[2])) {
				list = (String) addMap.get(str[0] + "," + str[1] + "," + str[2]);
				list += "," + str[3];
				addMap.put(str[0] + "," + str[1] + "," + str[2], list);
			} else {
				list = new String();
				list += str[3];
				addMap.put(str[0] + "," + str[1] + "," + str[2], list);
			}
		}
		addResult = dao.insertSubMenu(bean, addMap);
		return addResult;
	}

	public boolean removeMainMenuAllocationForUser(AdminBean bean) {
		return dao.removeMainMenuAllocationForUser(bean);
	}

	public boolean removeSubMenuAllocationForUser(AdminBean bean) {
		return dao.removeSubMenuAllocationForUser(bean);
	}
	public List<Map<String, Object>> getAttachUnderWriterList(String branchCode) {
		return dao.getAttachUnderWriterList(branchCode);
	}
	public int getLoginCount(AdminBean bean, String branchCode) {
		return dao.getLoginCount( bean,branchCode);
	}
}
	
	


