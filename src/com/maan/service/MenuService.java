package com.maan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.maan.bean.MenuBeanNew;
import com.maan.dao.CommonDAO;
import com.maan.dao.MenuDAO;
import com.maan.dao.impl.MenuDAOImpl;

public class MenuService  {

	final MenuDAO menuDAO = (MenuDAO)new MenuDAOImpl();
	
	public List getMenu(final String loginID, final String make)
			 {
		List result;
		result = menuDAO.getMenu(loginID, make);
		return result;
	}
	
	public List getAdminMenu(final String loginID,String make) {

		List result;
		result = menuDAO.getAdminMenu(loginID, make);
		return result;
	}
	
	public String getManuName(final String mfrId,final String branchCode)   {

		String result;
		
		result = menuDAO.manuName(mfrId,branchCode);
		return result;
	}

	public Map getDepartmentList(String manufactureID,String branchCode) {
		
	  Map list;
	 
	  list=menuDAO.getDepartmentList(manufactureID,branchCode);
	  return list;
	 
	}

	public String getSubMenuRights(String userID, String menuId, String productId, String departmentId) {
		  String menuRights;
		  menuRights=menuDAO.getSubMenuRights(userID,menuId,productId,departmentId);
		  return menuRights;
	}
	public String getPendingMenuId(final String mfrId,String menuName)  {
		String result;
		result = menuDAO.getPendingMenuId(mfrId,menuName);
		return result;
	}
	
	public String getRigthsOfProcess(String menuid,String processid,String loginid){
		List list=new ArrayList();
		String result="";
		try{
			List l = menuDAO.getRigthsOfProcess(menuid,processid,loginid);
			if(l!=null &&l.size()>0){
				for(Object o:l){
					/*Map m=(Map)o;
					list.add(m.get("SUB_MENU_CODE"));*/
					Map m=(Map)o;
					result=result+","+m.get("SUB_MENU_CODE");
				}
			}
			if("".equalsIgnoreCase(result)){
				result=",,";
			}
			else{
				result=result+",";
			}
		}
		catch (Exception exe)
		{
			 exe.printStackTrace();
		}
		return result;
	}
	
	public Map getDepartmentListNew(String product_id,String branchCode) {
		Map map=null;
		try{
			map=menuDAO.getDepartmentListNew(product_id,branchCode);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return map;
	}
	public Map getProcessList(String product_id,String dept_id,String branchCode){
		Map map=null;
		try{
			map=menuDAO.getProcessList(product_id,dept_id,branchCode);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return map;
	}
	public List getFinalMenuList(Map<String,Object> session){
		List list=null;
		try{
			list=new CommonDAO().getFinalMenuList(session);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return list;
	}
	public String getOldProductId(Map<String,Object> session) {
		String result="";
		try{
			result=new CommonDAO().getOldProductId(session);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return result;
	}
}
