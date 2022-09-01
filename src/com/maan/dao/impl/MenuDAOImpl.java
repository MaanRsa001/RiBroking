package com.maan.dao.impl;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.maan.bean.MenuBean;
import com.maan.bean.MenuBeanNew;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.LogUtil;
import com.maan.dao.MenuDAO;

public class MenuDAOImpl extends MyJdbcTemplate implements MenuDAO {
	final org.slf4j.Logger logger = LogUtil.getLogger(MenuDAOImpl.class);
	
	public List getMenu(final String loginID, final String make)
			 {
        
		logger.info("getMenu() method Implementation ");
		List list = getAllMenu(loginID, "0", make);
		if (list == null) {
			list = new ArrayList();
		}
		final MenuBean menuVB = new MenuBean();
		menuVB.setContent("Logout");
		menuVB.setUrl("login.do?method=logout");
		menuVB.setList(null);
		list.add(menuVB);
		return list;
	}

	public List getAdminMenu(final String loginID,String make)  {

	    logger.info("getMenu() method Implementation ");
	    
	    String query;
	    String[] args=new String[1];
		List resultList = new ArrayList();
		MenuBean menuvb;
		
		query = getQuery(DBConstants.MENUDAO_SELECT_ADMINUSERMENUS);
		args[0]=loginID;
		logger.info("Select Query====>"+query);
		
		int j=0;
		for(String s:args){
			logger.info("Arg["+j+"]====>"+s);
			j++;
		}
		final List list = this.mytemplate.queryForList(query,args);
		menuvb = new MenuBean();
		for(Object o:list){
			final Map tempMap = (Map) o;
			menuvb = new MenuBean();
			menuvb.setContent((String) tempMap.get("MENU_NAME"));
			menuvb.setUrl((String) tempMap.get("MENU_URL"));
			resultList.add(menuvb);
			//logger.info(menuVB.getContent());
		}
		
		
		MenuBean menu = new MenuBean();
		menu.setContent("Logout");
		menu.setUrl("login.do?method=logout");
		//menuVB.setList(null);
		resultList.add(menu);
		
			
		return resultList;
		
	}

	public List getAllMenu(final String loginID, final String idVal,
			final String make)  {

		String query;
		final List resultList = new ArrayList();
		/*query = "select a.MENU_ID,a.MENU_NAME,a.MENU_URL from TMAS_MENU_MASTER a, TMAS_LOGIN_DETAILS b "
				+ "where a.MASTER_MENU_ID = ? and b.MENU_IDS like ('%,'||a.MENU_ID||',%') and b.LOGIN_ID=? "
				+ "and b.MFR_ID=? and b.active='1' order by a.order_No";
				*/
		query = getQuery(DBConstants.MENUDAO_SELECT_TMASMENUTMASLOGIN);
		logger.info("====>"+query);
		logger.info("Select Query====>"+query);
		//logger.info("Arg[0]====>"+idVal);
		logger.info("Arg[0]====>"+loginID);
		logger.info("Arg[1]====>"+make);
		//final List list = this.mytemplate.queryForList(query, new Object[] { idVal,loginID, make });
		final List list = this.mytemplate.queryForList(query, new Object[] { loginID, make });
		
		if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
				final Map tempMap = (Map) list.get(i);
				final MenuBean menuVB = new MenuBean();
				final String subID = String.valueOf(tempMap.get("MENU_ID"));
				menuVB.setId( String.valueOf(tempMap.get("MENU_ID")));
				menuVB.setContent((String) tempMap.get("MENU_NAME"));
				menuVB.setUrl((String) tempMap.get("MENU_URL"));
				//menuVB.setList(getAllMenu(loginID, subID, make));
				resultList.add(menuVB);
			}}  
	
		return resultList;
		
	}

	public List getAdminAllMenu(final String loginID, final String idVal)
			 {

		String query;
		  List resultList = new ArrayList();MenuBean menuVB;
		/*query = "select a.MENU_ID,a.MENU_NAME,a.MENU_URL from TMAS_MENU_MASTER a, TMAS_LOGIN_DETAILS b "
				+ "where a.MASTER_MENU_ID = ? and b.MENU_IDS like ('%,'||a.MENU_ID||',%') and b.LOGIN_ID=? "
				+ "and b.active='1' order by a.order_No";
				*/
		  query = getQuery(DBConstants.MENUDAO_SELECT_TMASMENUTMASLOGIN2);
		logger.info("hai"+idVal+"0sdsn"+loginID);
		logger.info("Select Query====>"+getQuery(DBConstants.MENUDAO_SELECT_TMASMENUTMASLOGIN2));
		logger.info("Arg[0]====>"+idVal);
		logger.info("Arg[1]====>"+loginID);
		final List list = this.mytemplate.queryForList(query, new Object[] { idVal,loginID });
		menuVB = new MenuBean();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				final Map tempMap = (Map) list.get(i);
				menuVB = new MenuBean();
				final String subID = String.valueOf(tempMap.get("MENU_ID")) ;
				menuVB.setContent((String) tempMap.get("MENU_NAME"));
				menuVB.setUrl((String) tempMap.get("MENU_URL"));
				menuVB.setList(getAdminAllMenu(loginID, subID));
				resultList.add(menuVB);
			}
		
		}	return resultList;
	}


	private boolean printValue(final List list)
	{
		MenuBean menuVB=null;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				menuVB = (MenuBean) list.get(i);
				logger.info(menuVB.getContent() + "\t" + menuVB.getUrl());
				printValue(menuVB.getList());
			}
			
		}return false;

	}
	
	public String manuName(final String mfrID,final String branchCode)
	{
		//final String manuQuery = "select MFR_NAME from TMAS_MANUFACTURE_MASTER where MFR_ID="+mfrID;
		final String manuQuery = getQuery(DBConstants.MENUDAO_SELECT_MENUQUERY);
		//final Map manuMap = this.mytemplate.queryForMap(manuQuery);
		logger.info("Select Query====>"+getQuery(DBConstants.MENUDAO_SELECT_MENUQUERY));
		logger.info("Arg[0]====>"+mfrID);
		logger.info("Arg[1]====>"+branchCode);
		final Map manuMap = this.mytemplate.queryForMap(manuQuery,new String[]{mfrID,branchCode});
		String manuName = manuMap.get("TMAS_PRODUCT_NAME")==null?"":manuMap.get("TMAS_PRODUCT_NAME").toString();
		return manuName;
	}

	public Map getDepartmentList(String manufactureID,String branchCode) {
	 
			logger.info("MenuDAOImpl getDepartmentList() || Enter ");

			final SortedMap<Integer,String> map = new TreeMap();
			//String query="  SELECT TMAS_DEPARTMENT_ID,TMAS_DEPARTMENT_NAME FROM TMAS_DEPARTMENT_MASTER WHERE TMAS_PRODUCT_ID=? AND BRANCH_CODE=? AND TMAS_STATUS='Y' ORDER BY TMAS_DEPARTMENT_ID ";
			String query=getQuery(DBConstants.MENUDAO_SELECT_DEPTIDDEPTNAME);
			logger.info("query"+query);
			logger.info("Select Query====>"+getQuery(DBConstants.MENUDAO_SELECT_DEPTIDDEPTNAME));
			logger.info("Arg[0]====>"+manufactureID);
			logger.info("Arg[1]====>"+branchCode);
			final List list = this.mytemplate.queryForList(query,new Object[] {manufactureID,branchCode});
			for (int i = 0; i < list.size(); i++) {
				final Map tempMap = (Map) list.get(i);
				map.put(Integer.parseInt(tempMap.get("TMAS_DEPARTMENT_ID").toString()), tempMap.get("TMAS_DEPARTMENT_NAME").toString());
			}
			logger.info("MenuDAOImpl getDepartmentList() || Enter ");
			return map;
	}

	public String getSubMenuRights(String userID, String menuId,String productId, String departmentId){
		logger.info("MenuDAOImpl getSubMenuRights() ||Enter");
	    String userRights="";
	    logger.info("userID==>"+userID+" menuId==>"+menuId+" departmentId==>"+departmentId);
		String query=getQuery(DBConstants.MENUDAO_SELECT_USERRIGHTS);
    	logger.info("Select Query====>"+query);
    	logger.info("Arg[0]====>"+menuId);
    	logger.info("Arg[1]====>"+userID);
    	logger.info("Arg[2]====>"+productId);
    	List pendingList=this.mytemplate.queryForList(query,new String[]{menuId,userID,productId});
		if(pendingList!=null && pendingList.size()>0)
		{
			Map userRightsMap=(Map)pendingList.get(0);
			userRights=userRightsMap.get("SUB_MENU_RIGHTS")==null?"":userRightsMap.get("SUB_MENU_RIGHTS").toString();
		}
		logger.info("MenuDAOImpl getSubMenuRights() ||Exit UserRights=>"+userRights);
		return userRights;
	}

	public String getPendingMenuId(String manufactureID,String menuName)  {
		// TODO Auto-generated method stub
		logger.info("MenuDAOImpl getPendingMenuId() ||Enter");
		String pendingMenuId="";
		Object[] args=new Object[4];
		args[0]=manufactureID;
		args[1]=menuName;
		args[2]="User";
		args[3]="1";
		String query=getQuery(DBConstants.MENUDAO_SELECT_GET_PENDINGOFF_MENUID);
		logger.info("Query=>"+query);
		int i=0;
		for(Object arg:args)
		logger.info("Args["+i+++"]=>"+arg);
		List pendingList=this.mytemplate.queryForList(query,args);
		if(pendingList!=null && pendingList.size()>0)
		{
			Map pendingMap=(Map)pendingList.get(0);
			pendingMenuId=pendingMap.get("MENU_ID")==null?"":pendingMap.get("MENU_ID").toString();
		}
		logger.info("MenuDAOImpl getPendingMenuId() ||Exit Result=>"+pendingMenuId);
		return pendingMenuId;
	}
	
	public List getRigthsOfProcess(String menuid,String processid,String loginid) {
		logger.info("MenuDAOImpl getRigthsOfProcess || Enter");
		List list=null;
		try{
			if(menuid!=null && !"null".equals(menuid.trim()) && !"".equals(menuid.trim())){
				String sql=getQuery(DBConstants.MENUDAO_SELECT_GETRIGTHSOFPROCESSID);
				String[] args=new String[5];
				args[0]=menuid;
				args[1]=processid;
				args[2]=menuid;
				args[3]=processid;
				args[4]=loginid;
				logger.info("Select Query====>"+sql);
				int i=0;
				for(String s:args){
					logger.info("Arg["+i+"]====>"+s);
					i++;
				}
				list=this.mytemplate.queryForList(sql, args);
				logger.info("Result Size ====>"+list.size());
			}
		}
		catch (Exception exe)
		{
		}
		logger.info("MenuDAOImpl getRigthsOfProcess || Exit");
		return list;
	}
	public Map getDepartmentListNew(String product_id,String branchCode) {
		logger.info("MenuDAOImpl getDepartmentListNew || Enter");
		Map map=new TreeMap();
		try{
			String query=getQuery(DBConstants.USER_SELECT_DEPARTMENTMAP);
			String[] args=new String[2];
			args[0]=product_id;
			args[1]=branchCode;
			logger.info("Select Query====>"+query);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			List list=this.mytemplate.queryForList(query, args);
			for(Object o:list){
				Map m=(Map)o;
				map.put(m.get("TMAS_DEPARTMENT_ID"),m.get("TMAS_DEPARTMENT_NAME"));
			}
			logger.info("Result Size====>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDAOImpl getDepartmentListNew || Exit");
		return map;
	}
	public Map getProcessList(String product_id,String dept_id,String branchCode){
		logger.info("MenuDAOImpl getProcessList || Enter");
		Map map=new TreeMap();
		try{
			String sql=getQuery(DBConstants.USER_SELECT_PROCESSMAP);
			String[] args=new String[3];
			args[0]=product_id;
			args[1]=dept_id;
			args[2]=branchCode;
			logger.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			List list=this.mytemplate.queryForList(sql, args);
			Map m;
			for(Object o:list){
				m=(Map)o;
				map.put(m.get("PROCESS_ID"), m.get("PROCESS_NAME"));
			}
			logger.info("Result Size====>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDAOImpl getProcessList || Exit");
		return map;
	}
	public List getFinalMenuList(MenuBeanNew beanObject) {
		logger.info("MenuDAOImpl getFinalMenuList || Enter");
		List result=new ArrayList();
		try{
			String sql=getQuery(DBConstants.USER_SELECT_FINALMENULIST);
			String[] args=new String[3];
			args[0]=beanObject.getLoginId();
			args[1]=beanObject.getProcessId();
			args[2]=beanObject.getProcessId();
			/*String[] args=new String[9];
			args[0]=beanObject.getProductId();
			args[1]=beanObject.getDeptId();
			args[2]=beanObject.getProcessId();
			args[3]=beanObject.getBranchCode();
			args[4]="Y";
			args[5]=beanObject.getLoginId();
			args[6]=beanObject.getProductId();
			args[7]="1";
			args[8]="1";*/
			logger.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			List list=this.mytemplate.queryForList(sql,args);
			Map m;
			for(Object o:list){
				m=(Map)o;
				MenuBean menuVB=new MenuBean();
				menuVB.setContent((String)m.get("MENU_NAME"));
				menuVB.setUrl((String)m.get("MENU_URL"));
				menuVB.setId((m.get("MENU_ID")).toString());
				result.add(menuVB);
			}
			MenuBean menuVB=new MenuBean();
			menuVB.setContent("Logout");
			menuVB.setUrl("login.do?method=logout");
			result.add(menuVB);
			logger.info("Result Size====>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDAOImpl getFinalMenuList || Exit");
		return result;
	}
	public String getOldProductId(MenuBeanNew beanObject) {
		logger.info("MenuDAOImpl getOldProductId || Enter");
		String result="";
		try{
			String sql=getQuery(DBConstants.USER_SELECT_GETOLDPRODUCTID);
			String[] args=new String[5];
			args[0]=beanObject.getProductId();
			args[1]=beanObject.getDeptId();
			args[2]=beanObject.getProcessId();
			args[3]=beanObject.getBranchCode();
			args[4]="Y";
			logger.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			result=(String) this.mytemplate.queryForObject(sql, args, String.class);
			logger.info("Result Value====>"+result);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("MenuDAOImpl getOldProductId || Exit");
		return result;
	}

	public List getFinalMenuList1(com.maan.bean.MenuBeanNew beanObject) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOldProductId1(com.maan.bean.MenuBeanNew beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
}

