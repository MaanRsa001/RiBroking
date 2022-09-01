package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.MenuBeanNew;

public interface MenuDAO  {
	 List getMenu(final String loginID, final String make);
	 List getAdminMenu(final String loginID,String make) ;
	 String manuName(final String mfrID,final String branchCode);

	Map getDepartmentList(String manufactureID,String branchCode);

	String getSubMenuRights(String userID, String menuId, String productId, String departmentId);
	String getPendingMenuId(String manufactureID,String menuName);
	List getRigthsOfProcess(String menuid,String processid,String loginid)  ;
	
	public Map getDepartmentListNew(String product_id,String branchCode)  ;
	public Map getProcessList(String product_id,String dept_id,String branchCode) ;
	public List getFinalMenuList(MenuBeanNew beanObject)  ;
	public String getOldProductId(MenuBeanNew beanObject)  ;
}
