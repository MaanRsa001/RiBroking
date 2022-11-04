package com.maan.dao.impl.admin;

import com.maan.bean.admin.AdminBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.Jcrypt;
import com.maan.common.util.LogUtil;
import com.maan.dao.admin.AdminDAO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAOImpl extends MyJdbcTemplate implements AdminDAO{
	private static final Logger LOGGER = LogUtil.getLogger(AdminDAOImpl.class);
	public List<AdminBean> getProfitCenterList(AdminBean bean) {
		List ProfitCenterList = null;
		String query = "";
		try
		{
			LOGGER.info("getProfitCenterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.PROFIT_SELECT_GETLISTPROFITCENTER);
			if ("PN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TMAS_PFC_NAME) LIKE UPPER(?)";
			}
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			ProfitCenterList = this.mytemplate.queryForList(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return ProfitCenterList;
	}
	public int getInsertProfitCenter(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertProfitCenter");
			Object[] obj=new Object[5];
			obj[0]=bean.getProfitCenterId();
			obj[1]=bean.getProfitCenter();
			obj[2]=bean.getActive();
			//obj[3]=bean.getCoreCompany();
			obj[3]="";
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.PROFIT_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getUpdateProfitCenter(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateProfitCenter-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getProfitCenter();
			obj[1]=bean.getActive();
			//obj[2]=bean.getCoreCompany();
			obj[2]="";
			obj[3]=bean.getProfitCenterId();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.PROFIT_UPDATE_UPDATEQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateProfitCenter-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditProfitCenter(AdminBean bean) {
		LOGGER.info("getEditProfitCenter || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getProfitCenterId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.PROFIT_SELECT_GETEDITPROFITCENTER);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setProfitCenter(map.get("TMAS_PFC_NAME")==null?"":map.get("TMAS_PFC_NAME").toString());
				bean.setActive(map.get("TMAS_STATUS")==null?"":map.get("TMAS_STATUS").toString());
				bean.setCoreCompany(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
				bean.setProfitCenterId(map.get("TMAS_PFC_ID")==null?"":map.get("TMAS_PFC_ID").toString());
			}
			LOGGER.info("getEditProfitCenter || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public List<AdminBean> getUserListMaster(AdminBean bean) {
		List UserListMaster = null;
		String query = "";
		try
		{
			LOGGER.info("getUserListMaster");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.USER_SELECT_LISTQUERY);
			if ("UN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(USERNAME) LIKE UPPER(?)";
			}
			query+="   ORDER BY USERNAME";

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			UserListMaster = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getUserListMaster || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return UserListMaster;
	}
	public void insertNewUser(AdminBean bean) {
		String query="";
		try{
			LOGGER.info("insertNewUser - Enter");
			Object obj[]=new Object[8];
			obj[0]=bean.getLoginId();
			obj[1]=Jcrypt.crypt(bean.getPassword().substring(0, 3), bean.getPassword());
			obj[2]=bean.getUserType();
			obj[3]=bean.getEmail();
			obj[4]=bean.getUserName();
			obj[5]=bean.getActive();
			obj[6]=bean.getBranchCode();
			obj[7]=bean.getAttachedUW().replaceAll(" ", "");
			query=getQuery(DBConstants.USER_INSERT_INSERTUSER);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			this.mytemplate.update(query,obj);
			LOGGER.info("insertNewUser || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}

	}
	public void getEditUserList(AdminBean bean) {
		LOGGER.info("getEditUserList || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getLoginId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.USER_SELECT_DISPALYUSER);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setLoginId(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				bean.setUserName(map.get("USERNAME")==null?"":map.get("USERNAME").toString());
				bean.setUserType(map.get("USERTYPE")==null?"":map.get("USERTYPE").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setEmail(map.get("USER_MAIL")==null?"":map.get("USER_MAIL").toString());
				bean.setAttachedUW(map.get("ATTACHED_UW")==null?"":map.get("ATTACHED_UW").toString());
			}
			LOGGER.info("getEditUserList || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public void updateUserList(AdminBean bean) {
		String query="";
		try{
			LOGGER.info("updateUserList - Enter");
			Object obj[]=new Object[7];
			obj[0]=bean.getUserName();
			obj[1]=bean.getUserType();
			obj[2]=bean.getActive();
			obj[3]=bean.getEmail();
			obj[4]=bean.getAttachedUW().replaceAll(" ", "");
			obj[5]=bean.getLoginId();
			obj[6]=bean.getBranchCode();
			query=getQuery(DBConstants.USER_UPDATE_UPDATEUSER);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			this.mytemplate.update(query,obj);
			LOGGER.info("updateUserList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}

	}
	public void getChangePassword(AdminBean bean) {
		String query="";
		try{
			LOGGER.info("getChangePassword - Enter");
			Object obj[]=new Object[3];
			obj[0]=Jcrypt.crypt(bean.getPassword().substring(0, 3), bean.getPassword());
			obj[1]=bean.getLoginId();
			obj[2]=bean.getBranchCode();
			query=getQuery(DBConstants.USER_UPDATE_PASSWORDUPDATE);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			this.mytemplate.update(query,obj);
			LOGGER.info("updateUserList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}

	}
	public List menuAllocation(AdminBean bean) {
		List menuAllocation = null;
		String query = "";
		try
		{
			LOGGER.info("menuAllocation");
			Object[] obj=new Object[4];
			obj[0]=bean.getLoginId();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getBranchCode();
			query=getQuery(DBConstants.USER_SELECT_MENUALLOCATIONINFO);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			menuAllocation = this.mytemplate.queryForList(query,obj);
			LOGGER.info("menuAllocation || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return menuAllocation;
	}
	public boolean checkDeptAllocated(AdminBean bean) {
		boolean flag=false;
		String query = "";
		LOGGER.info("menuAllocation");
		try {
			Object[] obj=new Object[1];
			obj[0]=bean.getLoginId();
			query=getQuery(DBConstants.USER_SELECT_CHECKDEPTALLOCATED);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			int count=mytemplate.queryForInt(query, obj);
			if(count==0){
				flag=true;
			}
			else{
				flag=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/*public List<AdminBean> getExchangeListMaster(AdminBean bean) {
		List exchangeList = null;
		String query = "";
		try
		{
			LOGGER.info("getExchangeListMaster || Enter");
			Object[] obj=new Object[2];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getBranchCode();
			query=getQuery(DBConstants.EXCHANGE_SELECT_LISTQUERY);
			if ("CN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(CM.CURRENCY_NAME) LIKE UPPER(?)";
			}
			query+="  order by EXCHANGE_ID";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			exchangeList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getExchangeListMaster || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return exchangeList;
	}*/
	public List<AdminBean> getExchangeListMaster(AdminBean bean) {
		List exchangeList = null;
		String query = "";
		try
		{
			LOGGER.info("getExchangeListMaster || Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			//obj[1]=bean.getBranchCode();
			query=getQuery(DBConstants.EXCHANGE_SELECT_LISTQUERY);
			if ("SN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " AND UPPER(EM.AMEND_ID) LIKE UPPER(?) ";
			}
			query+="  ORDER BY   AMEND_ID DESC";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			exchangeList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getExchangeListMaster || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return exchangeList;
	}
	public List<AdminBean> GetExchangeCurrency(AdminBean bean) {
		String query = "";
		List<AdminBean> exchangeCurrList=new ArrayList<AdminBean>();
		try
		{
			/*LOGGER.info("GetExchangeCurrency || Enter");
			Object[] obj=new Object[3];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCountryId();
			obj[2]="Y";
			query=getQuery(DBConstants.COMMON_SELECT_GETCURRENCYLIST);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			exchangeCurrList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("GetExchangeCurrency || Exit");*/
			if("insert".equalsIgnoreCase(bean.getMode())){
				Object[] obj=new Object[3];
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getCountryId();
				obj[2]="Y";
				query=getQuery(DBConstants.COMMON_SELECT_GETCURRENCYLIST);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);


				for(int i=0; i<list.size(); i++) {
					AdminBean tempBean=new AdminBean();
					Map<String,Object> tempMap = list.get(i);
					tempBean.setExchangeRateId(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
					tempBean.setCurrencyNameId(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
					if(bean.getExchageRate()==null){
						tempBean.setExchangeRate("");
					}else{
						tempBean.setExchangeRate(bean.getExchageRate().get(i)==null?"":bean.getExchageRate().get(i));
					}
					exchangeCurrList.add(tempBean);
				}
			}else if("Edit".equalsIgnoreCase(bean.getMode())|| "view".equalsIgnoreCase(bean.getMode())){
				Object[] obj=new Object[2];
				obj[0]=bean.getBranchCode();
				obj[1]=bean.getAmendid();
				query=getQuery(DBConstants.EXCHANGE_SELECT_EDITQUERY);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				List<Map<String, Object>> list = this.mytemplate.queryForList(query, obj);


				for(int i=0; i<list.size(); i++) {
					AdminBean tempBean=new AdminBean();
					Map<String,Object> tempMap = list.get(i);
					tempBean.setExchangeRateId(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
					tempBean.setCurrencyNameId(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
					if(bean.getExchageRate()==null){
						tempBean.setExchangeRate(tempMap.get("EXCHANGE_RATE")==null?"":tempMap.get("EXCHANGE_RATE").toString());
					}else{
						tempBean.setExchangeRate(bean.getExchageRate().get(i)==null?"":bean.getExchageRate().get(i));
					}
					bean.setAmendid(tempMap.get("AMEND_ID")==null?"":tempMap.get("AMEND_ID").toString());
					bean.setInceptionDate(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
					bean.setEntrydate(tempMap.get("ENTRY_DATE")==null?"":tempMap.get("ENTRY_DATE").toString());

					exchangeCurrList.add(tempBean);
				}
			}
		}

		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return exchangeCurrList;
	}
	public int insertExchangeRate(AdminBean bean) {
		String query="";
		int count=0;
		try{
			LOGGER.info("insertExchangeRate - Enter");
			String amend_id=(String) this.mytemplate.queryForObject("SELECT MAX(AMEND_ID)+1 FROM EXCHANGE_MASTER WHERE BRANCH_CODE=?",new Object[]{ bean.getBranchCode()},String.class);
			for(int i=0;i<bean.getExchageRate().size();i++){
				Object obj[]=new Object[9];
				obj[0]=bean.getExchageRateList().get(i);
				obj[1]=bean.getExchageRate().get(i);
				obj[2]=bean.getExchageRateList().get(i);
				obj[3]=amend_id;
				obj[4]=bean.getInceptionDate();
				obj[5]=bean.getInceptionDate();
				obj[6]=bean.getInceptionDate();
				obj[7]=bean.getCountryId();
				obj[8]=bean.getBranchCode();
				query=getQuery(DBConstants.EXCHANGE_INSERT_INSERTQUERY);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count=this.mytemplate.update(query,obj);
			}
			LOGGER.info("insertExchangeRate || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}
		return count;
	}
	public void getEditExchangeMaster(AdminBean bean) {
		LOGGER.info("getEditExchangeMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getExchangeId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.EXCHANGE_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setCurrencyId(map.get("CURRENCY_ID")==null?"":map.get("CURRENCY_ID").toString());
				bean.setExchangeRate(map.get("EXCHANGE_RATE")==null?"":map.get("EXCHANGE_RATE").toString());
				bean.setCoreAppCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				bean.setInceptionDate(map.get("INCEPTIONDATE")==null?"":map.get("INCEPTIONDATE").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
			LOGGER.info("getEditExchangeMaster || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int updateExchangeRate(AdminBean bean) {
		String query="";
		int count=0;
		try{
			LOGGER.info("updateExchangeRate - Enter");
			query=getQuery(DBConstants.EXCHANGE_UPDATE_EXPIRYDATE);
			Object arg[]=new Object[5];
			arg[0]=bean.getInceptionDate();
			arg[1]=bean.getExchangeId();
			arg[2]=bean.getExchangeId();
			arg[3]=bean.getBranchCode();
			arg[4]=bean.getBranchCode();
			this.mytemplate.update(query,arg);
			LOGGER.info("update Expire date Successfully ");
			Object obj[]=new Object[12];
			obj[0]=bean.getExchangeId();
			obj[1]=bean.getExchangeRate();
			obj[2]=bean.getCurrencyId();
			obj[3]=bean.getExchangeId();
			obj[4]=bean.getBranchCode();
			obj[5]=bean.getInceptionDate();
			obj[6]=bean.getInceptionDate();
			obj[7]=bean.getRemarks();
			obj[8]=bean.getActive();
			obj[9]=bean.getCoreAppCode();
			obj[10]=bean.getCountryId();
			obj[11]=bean.getBranchCode();

			query=getQuery(DBConstants.EXCHANGE_UPDATE_UPDATEQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("updateExchangeRate || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}
		return count;
	}
	public String GetSysDate(AdminBean bean) {
		String query="";
		LOGGER.info(" GetSysDate || Enter");
		String[] arg=new String[1];

		query=getQuery(DBConstants.COMMON_SELECT_STARTDTOFMONTH);
		arg[0]=bean.getInceptionDate();
		LOGGER.info("Select Query====>"+query);
		LOGGER.info("Arg[0]====>"+arg[0]);
		LOGGER.info(" GetSysDate || Exit");
		return ""+mytemplate.queryForObject(query,arg,String.class);
	}
	public String GetInceptionDate(AdminBean bean) {
		String query="";
		LOGGER.info(" GetInceptionDate || Enter");
		String[] arg=new String[1];
		//getting expiry date
		query=getQuery(DBConstants.EXCHANGE_SELECT_INCEPTIONDATE);
		//arg[0]=bean.getCurrencyId();
		arg[0]=bean.getBranchCode();
		LOGGER.info("Select Query====>"+query);
		LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
		LOGGER.info(" GetInceptionDate || Exit");
		return ""+mytemplate.queryForObject(query,arg,String.class);
	}
	public int getExcCoreAppCode(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getExcCoreAppCode || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[2];

			query=getQuery(DBConstants.EXCHANGE_SELECT_CORECHECKINSERT);
			arg[0]=bean.getCoreAppCode();
			arg[1]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[3];

			query=getQuery(DBConstants.EXCHANGE_SELECT_CORECHECKUPDATE);
			arg[0]=bean.getCoreAppCode();
			arg[1]=bean.getBranchCode();
			arg[1]=bean.getExchangeId();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}

		LOGGER.info(" GetInceptionDate || Exit");
		return count;
	}
	public int getExchangeCurrency(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getExchangeCurrency || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[2];

			query=getQuery(DBConstants.EXCHANGE_SELECT_CHECKAVAIL);
			arg[0]=bean.getCurrencyId();
			arg[1]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[3];

			query=getQuery(DBConstants.EXCHANGE_SELECT_UPDATECHECKAVAIL);
			arg[0]=bean.getExchangeId();
			arg[1]=bean.getCurrencyId();
			arg[2]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}
		LOGGER.info(" getExchangeCurrency || Exit");
		return count;
	}
	public List<AdminBean> getProductList(AdminBean bean) {
		List ProductList = null;
		String query = "";
		try
		{
			LOGGER.info("getProductList || Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.COMMON_SELECT_GETPRODUCTLIST);
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			ProductList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getProductList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return ProductList;
	}
	public List<AdminBean> getDepartmentList(AdminBean bean)
	{
		List DepartmentList = null;
		String query = "";
		try
		{
			LOGGER.info("getDepartmentList || Enter");
			Object[] obj=new Object[3];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]="H";

			query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			DepartmentList = this.mytemplate.queryForList(query,obj);
			if(DepartmentList.size()==0){
				Map m = new HashMap();
				m.put("TMAS_DEPARTMENT_ID", "0");
				m.put("TMAS_DEPARTMENT_NAME", "NONE");
				DepartmentList.add(m);
			}
			LOGGER.info("getDepartmentList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return DepartmentList;
	}
	public List<AdminBean> getProcessList(AdminBean bean) {
		List ProcessList = null;
		String query = "";
		try
		{
			LOGGER.info("getProcessList || Enter");
			Object[] obj=new Object[3];
			obj[0]=bean.getProductId();
			obj[1]=bean.getDepartment();
			obj[2]=bean.getBranchCode();

			query=getQuery(DBConstants.MENUMASTER_SELECT_PROCESSDROPDOWN);
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			ProcessList = this.mytemplate.queryForList(query,obj);
			if(ProcessList.size()==0){
				Map m = new HashMap();
				m.put("PROCESS_ID", "0");
				m.put("PROCESS_NAME", "NONE");
				ProcessList.add(m);
			}
			LOGGER.info("getProcessList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return ProcessList;
	}
	public List<AdminBean> getMenuList(AdminBean bean) {
		List MenuList = null;
		String query = "";
		try
		{
			LOGGER.info("getMenuList || Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getProcess();
			query=getQuery(DBConstants.MENUMASTER_SELECT_MENULIST);
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			MenuList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getMenuList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return MenuList;
	}
	public String getMenuId(AdminBean bean) {
		String result="";
		String query = "";
		try
		{
			LOGGER.info("getMenuId || Enter");
			query=getQuery(DBConstants.MENUMASTER_SELECT_MAXMENUID);
			LOGGER.info("Select Query====>"+query);
			result=""+(mytemplate.queryForInt(query));
			LOGGER.info("getMenuList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return result;
	}
	public int insertMenu(AdminBean bean) {
		String query="";
		int count=0;
		try{
			LOGGER.info("insertMenu - Enter");
			Object obj[]=new Object[12];
			obj[0]=bean.getMenuId();
			obj[1]=bean.getMenuName();
			obj[2]=bean.getMenuUrl();
			obj[3]=bean.getOrderby();
			obj[4]=bean.getActive();
			obj[5]=bean.getRemarks();
			obj[6]=bean.getInceptionDate();
			obj[7]=bean.getExpiryDate();
			obj[8]=bean.getUserType();
			obj[9]=bean.getProcess();
			obj[10]=bean.getProductId();
			obj[11]=bean.getDepartment();
			query=getQuery(DBConstants.MENUMASTER_INSERT_INSERTMENU);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("insertMenu || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}
		return count;
	}
	public void getEditMenuMaster(AdminBean bean) {
		LOGGER.info("getEditMenuMaster || Enter");
		try{
			Object obj[] = new Object[1];
			obj[0]=bean.getMenuId();
			String query=getQuery(DBConstants.MENUMASTER_SELECT_EDITMENU);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setMenuId(map.get("MENU_ID")==null?"":map.get("MENU_ID").toString());
				bean.setMenuName(map.get("MENU_NAME")==null?"":map.get("MENU_NAME").toString());
				bean.setMenuUrl(map.get("MENU_URL")==null?"":map.get("MENU_URL").toString());
				bean.setOrderby(map.get("ORDER_NO")==null?"":map.get("ORDER_NO").toString());
				bean.setInceptionDate(map.get("START_DATE")==null?"":map.get("START_DATE").toString());
				bean.setExpiryDate(map.get("END_DATE")==null?"":map.get("END_DATE").toString());
				bean.setUserType(map.get("TYPE")==null?"":map.get("TYPE").toString());
				bean.setProcess(map.get("PROCESS_ID")==null?"":map.get("PROCESS_ID").toString());
				bean.setProductId(map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString());
				bean.setDepartment(map.get("DEPT_ID")==null?"":map.get("DEPT_ID").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setActive(map.get("ACTIVE")==null?"":map.get("ACTIVE").toString());
			}
			LOGGER.info("getEditMenuMaster || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}

	}
	public int updateMenu(AdminBean bean) {
		String query="";
		int count=0;
		try{
			LOGGER.info("updateMenu - Enter");
			Object obj[]=new Object[12];

			obj[0]=bean.getMenuName();
			obj[1]=bean.getMenuUrl();
			obj[2]=bean.getOrderby();
			obj[3]=bean.getRemarks();
			obj[4]=bean.getInceptionDate();
			obj[5]=bean.getExpiryDate();
			obj[6]=bean.getUserType();
			obj[7]=bean.getActive();
			obj[8]=bean.getProcess();
			obj[9]=bean.getProductId();
			obj[10]=bean.getDepartment();
			obj[11]=bean.getMenuId();
			query=getQuery(DBConstants.MENUMASTER_UPDATE_UPDATEMENU);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("updateMenu || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}
		return count;
	}
	public int getMenuNameExist(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getMenuNameExist || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[3];

			query=getQuery(DBConstants.MENUMASTER_SELECT_CHECKMENUINSERT);
			arg[0]=bean.getMenuName();
			arg[1]=bean.getUserType();
			arg[2]=bean.getProcess();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[4];

			query=getQuery(DBConstants.MENUMASTER_SELECT_UPDATECHECK);
			arg[0]=bean.getMenuName();
			arg[1]=bean.getUserType();
			arg[2]=bean.getProcess();
			arg[3]=bean.getMenuId();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}

		LOGGER.info(" getMenuNameExist || Exit");
		return count;
	}
	public int getcountProfitCenter(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcountProfitCenter || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				{
					obj= new Object[2];
					obj[0]=bean.getProfitCenter();
					obj[1]=bean.getBranchCode();
					query=getQuery(DBConstants.PROFIT_SELECT_PROFITCHECKINSERT);
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
					count = mytemplate.queryForInt(query, obj);
				}}
			else{
				obj= new Object[3];
				obj[0] = bean.getProfitCenter();
				obj[1] = bean.getProfitCenterId();
				obj[2] = bean.getBranchCode();
				query=getQuery(DBConstants.PROFIT_SELECT_PROFITCHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcountProfitCenter || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getprofitcontCoreCompanyCode(AdminBean bean) {
		boolean flag=false;
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getprofitcontCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompany();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.PROFIT_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[3];
				obj[0]=bean.getCoreCompany();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getProfitCenterId();
				query=getQuery(DBConstants.PROFIT_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

			LOGGER.info("getprofitcontCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}

	public List<AdminBean> getDepartmentMasterList(AdminBean bean) {
		List DepartmentList = null;
		String query = "";
		try
		{
			LOGGER.info("getDepartmentList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.DEPARTMENTBUSINESS_SELECT_DEPTIDPID);
			if ("DN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TMAS_DEPARTMENT_NAME) LIKE UPPER(?)";
			}
			query+="  order by TMAS_PRODUCT_ID,TMAS_DEPARTMENT_NAME,TMAS_PRODUCT_ID";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			DepartmentList = this.mytemplate.queryForList(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return DepartmentList;
	}
	public int getInsertDepartment(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertDepartment");
			Object[] obj=new Object[7];
			obj[0]=bean.getProductId();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getProductId();
			obj[3]=bean.getDepartmentName();
			obj[4]=bean.getStatus();
			//obj[5]=bean.getCoreCompanyCode();
			obj[5]="";
			obj[6]=bean.getBranchCode();
			query=getQuery(DBConstants.DEPARTMENTBUSINESS_INSERT_GETINSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("getEditDepartment || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;}

	public int getUpdateDepartment(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateDepartment-Enter");
			Object[] obj=new Object[6];
			obj[0]=bean.getDepartmentName();
			obj[1]=bean.getStatus();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getDepartmentId();
			obj[4]=bean.getBranchCode();
			obj[5]=bean.getProductId();

			query=getQuery(DBConstants.DEPARTMENTBUSINESS_UPDATE_TMASDEPTNAME);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateDepartment-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditDepartment(AdminBean bean) {
		LOGGER.info("getEditDepartment || Enter");
		try{
			Object obj[] = new Object[3];
			obj[0]=bean.getDepartmentId();
			obj[1]=bean.getProductId();
			obj[2]=bean.getBranchCode();
			String query=getQuery(DBConstants.DEPARTMENTBUSINESS_SELECT_GETEDITDEPARTMENTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setDepartmentName(map.get("TMAS_DEPARTMENT_NAME")==null?"":map.get("TMAS_DEPARTMENT_NAME").toString());
				bean.setStatus(map.get("TMAS_STATUS")==null?"":map.get("TMAS_STATUS").toString());
				//bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
				bean.setDepartmentId(map.get("TMAS_DEPARTMENT_ID")==null?"":map.get("TMAS_DEPARTMENT_ID").toString());
				bean.setProductId(map.get("TMAS_PRODUCT_ID")==null?"":map.get("TMAS_PRODUCT_ID").toString());
			}
			LOGGER.info("getEditDepartment || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}}
	public int getContCoreCompanyCode(AdminBean bean) {
		boolean flag=false;
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.DEPARTMENTBUSINESS_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getDepartmentId();
				query=getQuery(DBConstants.DEPARTMENTBUSINESS_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

			LOGGER.info("getContCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getcontDepartmentName(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContDepartmentName || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				{
					obj= new Object[3];
					obj[0]=bean.getDepartmentName();
					obj[1]=bean.getProductId();
					obj[2]=bean.getBranchCode();
					query=getQuery(DBConstants.DEPARTMENTBUSINESS_INSERT_VALIDATION);
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
					count = mytemplate.queryForInt(query, obj);
				}}
			else{
				obj= new Object[4];
				obj[0] = bean.getDepartmentName();
				obj[1] = bean.getProductId();
				obj[2] = bean.getDepartmentId();
				obj[3] = bean.getBranchCode();
				query=getQuery(DBConstants.DEPARTMENTBUSINESS_EDIT_VALIDATION);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getDepartmentName || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public List<AdminBean> getProductMasterList(AdminBean bean) {
		List ProductMasterList = null;
		String query = "";
		try
		{
			LOGGER.info("getProductMasterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.PRODUCT_SELECT_LISTQUERY);
			if ("PN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TMAS_PRODUCT_NAME) LIKE UPPER(?)";
			}
			query+="   order by TMAS_PRODUCT_ID,TMAS_PRODUCT_NAME";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			ProductMasterList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getEditProductMasterList || Exit");

		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return ProductMasterList;
	}
	public void getEditProductMaster(AdminBean bean) {
		LOGGER.info("getEditProductMaster|| Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getProductId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.PRODUCT_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setStatus(map.get("TMAS_STATUS")==null?"":map.get("TMAS_STATUS").toString());
				bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
				bean.setProductName(map.get("TMAS_PRODUCT_NAME")==null?"":map.get("TMAS_PRODUCT_NAME").toString());
				bean.setProductId(map.get("TMAS_PRODUCT_ID")==null?"":map.get("TMAS_PRODUCT_ID").toString());
			}
			LOGGER.info("getEditProductMaster|| Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}}


	public int getInsertProductMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertProductMaster");
			Object[] obj=new Object[5];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductName();
			obj[2]=bean.getStatus();
			//obj[3]=bean.getCoreCompanyCode();
			obj[3]="";
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.PRODUCT_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("getEditProductMaster|| Exit");
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getUpdateProductMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateProductMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getProductName();
			obj[1]=bean.getStatus();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getProductId();
			obj[4]=bean.getBranchCode();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			query=getQuery(DBConstants.PRODUCT_UPDATE_UPDATEQUERY);
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateProductMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getcontProductName(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcontProductName || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				{
					obj= new Object[2];
					obj[0]=bean.getProductName();
					obj[1]=bean.getBranchCode();
					query=getQuery(DBConstants.PRODUCT_SELECT_PRODUCTCHECKINSERT);
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
					count = mytemplate.queryForInt(query, obj);
				}}
			else{
				obj= new Object[3];
				obj[0]=bean.getProductName();
				obj[1] = bean.getProductId();
				obj[2] = bean.getBranchCode();
				query=getQuery(DBConstants.PRODUCT_SELECT_PRODUCTCKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcontProductName || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}
	public int getcontCoreCompanyCode(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcontCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				{
					obj= new Object[2];
					obj[0]=bean.getCoreCompanyCode();
					obj[1]=bean.getBranchCode();
					query=getQuery(DBConstants.PRODUCT_SELECT_CORECHECKINSERT);
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
					count = mytemplate.queryForInt(query, obj);
				}}
			else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2] = bean.getProductId();
				query=getQuery(DBConstants.PRODUCT_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcontCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}
	public List<AdminBean> getterritoryList(AdminBean bean) {
		List territoryList = null;
		String query = "";
		LOGGER.info("getEditterritoryMaster|| Enter");
		try
		{
			LOGGER.info("getterritoryMasterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.TERRITORY_SELECT_LISTQUERY);
			if ("TN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TERRITORY_DESC) LIKE UPPER(?)";
			}
			query+="   order by TERRITORY_ID,TERRITORY_CODE,TERRITORY_DESC";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			territoryList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getEditterritoryMaster|| Exit");

		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return territoryList;
	}
	public int getUpdateterritoryMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateterritoryMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getTerritoryDesc();
			obj[1]=bean.getStatus();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getTerritoryCode();
			obj[4]=bean.getBranchCode();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			query=getQuery(DBConstants.TERRITORY_UPDATE_UPDATEQUERY);
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateterritoryMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}

	public int getInsertTerritoryMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertterritoryMaster");
			Object[] obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getTerritoryCode();
			obj[2]=bean.getTerritoryDesc();
			obj[3]=bean.getStatus();
			//obj[4]=bean.getCoreCompanyCode();
			obj[4]="";
			obj[5]=bean.getBranchCode();
			query=getQuery(DBConstants.TERRITORY_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;}
	public void getEditTerritoryMaster(AdminBean bean) {
		LOGGER.info("getEditTerritoryMaster|| Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getTerritoryCode();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.TERRETORY_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setTerritoryId(map.get("TERRITORY_ID")==null?"":map.get("TERRITORY_ID").toString());
				bean.setTerritoryCode(map.get("TERRITORY_CODE")==null?"":map.get("TERRITORY_CODE").toString());
				bean.setTerritoryDesc(map.get("TERRITORY_DESC")==null?"":map.get("TERRITORY_DESC").toString());
				bean.setStatus(map.get("TERRITORY_STATUS")==null?"":map.get("TERRITORY_STATUS").toString());
				//bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
			}
			LOGGER.info("getEditTerritoryMaster|| Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}}
	public int getcontTerritoryDesc(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcontTerritoryDesc || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getTerritoryDesc();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.TERRITORY_SELECT_TERRITORYNAMECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0] = bean.getTerritoryDesc();
				obj[1] = bean.getTerritoryId();
				obj[2] = bean.getBranchCode();
				query=getQuery(DBConstants.TERRITORY_SELECT_TERRITORYNAMECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcontTerritoryDesc || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getcountCoreCompanyCode(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcontCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.TERRETORY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2] = bean.getTerritoryCode();
				query=getQuery(DBConstants.TERRETORY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcontCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}
	public List<AdminBean> getCategoryMasterList(AdminBean bean) {
		List CategoryMasterList = null;
		String query = "";
		LOGGER.info("getCategoryMasterList|| Enter");
		try
		{
			LOGGER.info("getCategoryMasterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.CATEGORY_SELECT_LISTQUERY);
			if ("CN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(ZONE_DESC) LIKE UPPER(?)";
			}
			query+="   ORDER BY ZONE_ID,ZONE_DESC";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			CategoryMasterList= this.mytemplate.queryForList(query,obj);
			LOGGER.info("getEditterritoryMaster|| Exit");

		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return CategoryMasterList;
	}
	public void getEditCategoryZoneMaster(AdminBean bean) {
		LOGGER.info("getEditCategoryZoneMaster|| Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getCategoryId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.CATEGORY_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setCategoryId(map.get("ZONE_ID")==null?"":map.get("ZONE_ID").toString());
				bean.setCategoryName(map.get("ZONE_DESC")==null?"":map.get("ZONE_DESC").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCoreAppCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
			}
			LOGGER.info("getEditCategoryZoneMaster|| Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}}

	public int getInsertCategoryMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertCategoryMaster");
			Object[] obj=new Object[5];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCategoryName();
			obj[2]=bean.getStatus().trim();
			obj[3]=bean.getCoreAppCode();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.CATEGORY_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;}

	public int getUpdateCategoryZoneMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateterritoryMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getCategoryName();
			obj[1]=bean.getStatus();
			obj[2]=bean.getCoreAppCode();
			obj[3]=bean.getCategoryId();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.CATEGORY_UPDATE_UPDATEQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateterritoryMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getcontCategoryName(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcontCategoryName || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCategoryName();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.CATEGORY_SELECT_CATEGORYNAMECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0]=bean.getCategoryName();
				obj[1] = bean.getCategoryId();
				obj[2] = bean.getBranchCode();
				query=getQuery(DBConstants.CATEGORY_SELECT_CATEGORYNAMECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcontCategoryName || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}
	public int getcountCoreAppCode(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcountCoreAppCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){

				obj= new Object[2];
				obj[0]=bean.getCoreAppCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.CATEGORY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0]=bean.getCoreAppCode();
				obj[1]=bean.getBranchCode();
				obj[2] = bean.getCategoryId();
				query=getQuery(DBConstants.CATEGORY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcountCoreAppCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}
	public List<AdminBean> getCurrencyMasterList(AdminBean bean) {
		List currencyMasterList = null;
		String query = "";
		LOGGER.info("getCurrencyMasterList|| Enter");
		try
		{
			//LOGGER.info("getCurrencyMasterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.CURRENCY_SELECT_LISTQUERY);
			if ("CN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(CM.CURRENCY_NAME) LIKE UPPER(?)";
			}
			query+="  order by CURRENCY_NAME";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			currencyMasterList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getCurrencyMasterList|| Exit");

		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return currencyMasterList;
	}
	public void getEditCurrencyMaster(AdminBean bean) {
		LOGGER.info("getEditCurrencyMaster|| Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getCurrencyId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.CURRENCY_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setCurrencyId(map.get("CURRENCY_ID")==null?"":map.get("CURRENCY_ID").toString());
				bean.setCurrencyName(map.get("CURRENCY_NAME")==null?"":map.get("CURRENCY_NAME").toString());
				bean.setAmendid(map.get("AMEND_ID")==null?"":map.get("AMEND_ID").toString());
				bean.setInceptiondate(map.get("INCEPTIONDATE")==null?"":map.get("INCEPTIONDATE").toString());
				bean.setExperydate(map.get("EXPIRYDATE")==null?"":map.get("EXPIRYDATE").toString());
				bean.setEffectDate(map.get("EFFECTIVEDATE")==null?"":map.get("EFFECTIVEDATE").toString());
				bean.setEntrydate(map.get("ENTRYDATE")==null?"":map.get("ENTRYDATE").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCoreAppCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				bean.setCountryId(map.get("COUNTRY_ID")==null?"":map.get("COUNTRY_ID").toString());
				bean.setCurrencyShortName(map.get("SHORT_NAME")==null?"":map.get("SHORT_NAME").toString());
				bean.setBranchCode(map.get("BRANCH_CODE")==null?"":map.get("BRANCH_CODE").toString());
				bean.setSno(map.get("SNO")==null?"":map.get("SNO").toString());
			}
			LOGGER.info("getEditCurrencyMaster|| Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}}
	public int getInsertCurrencyMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertCategoryMaster");
			Object[] obj=new Object[11];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCurrencyName();
			obj[2]=bean.getEffectDate();
			obj[3]=bean.getEffectDate();
			obj[4]=bean.getRemarks();
			obj[5]=bean.getStatus();
			obj[6]=bean.getCoreAppCode();
			obj[7]=bean.getCountryId();
			obj[8]=bean.getCurrencyShortName();
			obj[9]=bean.getBranchCode();
			obj[10]=bean.getBranchCode();
			query=getQuery(DBConstants.CURRENCY_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getUpdateCurrencyMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateCurrencyMaster-Enter");
			Object[] obj1=new Object[5];
			obj1[0]=bean.getEffectDate();
			obj1[1]=bean.getCurrencyId();
			obj1[2]=bean.getCurrencyId();
			obj1[3]=bean.getBranchCode();
			obj1[4]=bean.getBranchCode();
			query=getQuery(DBConstants.CURRENCY_UPDATE_EXPIRYDATE);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj1,","));

			count=this.mytemplate.update(query,obj1);
			LOGGER.info("getUpdateInsertCurrencyMaster-Enter");
			Object[] obj=new Object[13];
			obj[0]=bean.getCurrencyId();
			obj[1]=bean.getCurrencyName();
			obj[2]=bean.getCurrencyId();
			obj[3]=bean.getBranchCode();
			obj[4]=bean.getEffectDate();
			obj[5]=bean.getEffectDate();
			obj[6]=bean.getRemarks();
			obj[7]=bean.getStatus();
			obj[8]=bean.getCoreAppCode();
			obj[9]=bean.getCountryId();
			obj[10]=bean.getCurrencyShortName();
			obj[11]=bean.getBranchCode();
			obj[12]=bean.getSno();
			query=getQuery(DBConstants.CURRENCY_SELECT_UPDATEQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateCurrencyMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getCurrencyName(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getCurrencyNameCheck || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCurrencyName();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.CURRENCY_SELECT_CURRENCYINSERTNAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0]=bean.getCurrencyName();
				obj[1]=bean.getCurrencyId();
				obj[2] = bean.getBranchCode();
				query=getQuery(DBConstants.CURRENCY_SELECT_CURRENCYUPDATENAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getCurrencyNameCheck || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}

	public int getCoreAppCode(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getcountCoreAppCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreAppCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.CURRENCY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else{
				obj= new Object[3];
				obj[0]=bean.getCoreAppCode();
				obj[1]=bean.getBranchCode();
				obj[2] = bean.getCurrencyId();
				query=getQuery(DBConstants.CURRENCY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getcountCoreAppCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;

	}

	public List<AdminBean> getProcessMasterList(AdminBean bean) {
		List processMasterList = null;
		String query = "";
		LOGGER.info("getProcessList|| Enter");
		try
		{
			LOGGER.info("getProcessMasterList");
			Object[] obj=new Object[3];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getBranchCode();
			query=getQuery(DBConstants.PROCESSMASTER_SELECT_PROCESSLIST);
			if ("PN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(PROCESS_NAME) LIKE UPPER(?)";
			}
			query+="   ORDER BY HM.PRODUCT_ID,HM.DEPT_ID,HM.PROCESS_ID";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			processMasterList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getProcessList|| Exit");

		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return processMasterList;}
	public void getEditprocessMaster(AdminBean bean) {
		LOGGER.info("getEditprocessMaster|| Enter");
		try{
			Object obj[] = new Object[1];
			obj[0]=bean.getProcessId();
			String query=getQuery(DBConstants.PROCESSMASTER_SELECT_PROCESSDETAILS);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setProductId(map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString());
				bean.setDepartmentId(map.get("DEPT_ID")==null?"":map.get("DEPT_ID").toString());
				bean.setProcessId(map.get("PROCESS_ID")==null?"":map.get("PROCESS_ID").toString());
				bean.setProcessName(map.get("PROCESS_NAME")==null?"":map.get("PROCESS_NAME").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setOrderno(map.get("ORDER_NO")==null?"":map.get("ORDER_NO").toString());
				bean.setUwlimit(map.get("UW_LIMIT_YN")==null?"":map.get("UW_LIMIT_YN").toString());

			}
			LOGGER.info("getEditprocessMaster|| Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getUpdateprocessMaster(AdminBean bean) {
		int count = 0;
		String query = "";
		try
		{
			LOGGER.info("getUpdateprocessMaster-Enter");
			Object[] obj=new Object[8];
			obj[0]=bean.getProductId();
			obj[1]=bean.getDepartmentId();
			obj[2]=bean.getProcessName();
			obj[3]=bean.getStatus();
			obj[4]=bean.getOrderno();
			obj[5]=bean.getUwlimit();
			obj[6]=bean.getProcessId();
			obj[7]=bean.getBranchCode();
			query=getQuery(DBConstants.PROCESSMASTER_UPDATE_PROCESSDETAILS);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateprocessMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public List<AdminBean> getCommonDepartmentList(AdminBean bean) {
		List DepartmentList = null;
		String query = "";
		try
		{
			LOGGER.info("getCommonDepartmentList");
			Object[] obj=new Object[3];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]="H";
			query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
			DepartmentList = this.mytemplate.queryForList(query,obj);
			if(DepartmentList.size()==0){
				Map m = new HashMap();
				m.put("TMAS_DEPARTMENT_ID", "0");
				m.put("TMAS_DEPARTMENT_NAME", "NONE");
				DepartmentList.add(m);
			}
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return DepartmentList;
	}
	public int getInsertprocessMaster(AdminBean bean) {
		String query="";
		int count=0;
		try{
			LOGGER.info("getInsertprocessMaster");
			Object[] obj=new Object[8];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getDepartmentId();
			obj[3]=bean.getProcessName();
			obj[4]=bean.getStatus();
			obj[5]=bean.getBranchCode();
			obj[6]=bean.getOrderno();
			obj[7]=bean.getUwlimit();
			query=getQuery(DBConstants.PROCESSMASTER_INSERT_INSERTPROCESS);
			LOGGER.info("query"+query);
			LOGGER.info("Arg"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("GetInsertprocessMaster-exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getProcessName(AdminBean bean) {
		boolean flag=false;
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getOrderno || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getProcessName();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_PROCESSNAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[3];
				obj[0]=bean.getProcessName();
				obj[1]=bean.getProductId();
				obj[2]=bean.getBranchCode();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_UPDATEPROCESSNAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

			LOGGER.info("getOrderno || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getcombination(AdminBean bean) {
		boolean flag=false;
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getOrderno || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[4];
				obj[0]=bean.getProductId();
				obj[1]=bean.getDepartmentId();
				obj[2]=bean.getProcessName();
				obj[3]=bean.getBranchCode();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_INSERTCHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[5];
				obj[0]=bean.getProcessName();
				obj[1]=bean.getProcessId();
				obj[2]=bean.getDepartmentId();
				obj[3]=bean.getProductId();
				obj[4]=bean.getBranchCode();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_UPDATECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

			LOGGER.info("getOrderno || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public List<AdminBean> getSubprofitCenterList(AdminBean bean) {
		List SubprofitCenterList = null;
		String query = "";
		try
		{
			LOGGER.info("getSubprofitCenterList || Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.SUBPROFIT_SELECT_GETLISTSUBPROFITCENTER);

			if ("SP".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TMAS_SPFC_NAME) LIKE UPPER(?)";
			}
			query +="order by TMAS_SPFC_ID,TMAS_SPFC_NAME";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			SubprofitCenterList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getSubprofitCenterList || Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return SubprofitCenterList;
	}
	public int getInsertSubprofitCenter(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertSubProfitCenter");
			Object[] obj=new Object[6];
			obj[0]=bean.getSubProfitCenter_Id();
			obj[1]=bean.getProductId();
			obj[2]=bean.getSubProfitCenter();
			obj[3]=bean.getActive();
			//obj[4]=bean.getCoreCompanyCode();
			obj[4]="";
			obj[5]=bean.getBranchCode();
			query=getQuery(DBConstants.SUBPROFIT_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getUpdateSubprofitCenter(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateSubprofitCenter-Enter");
			Object[] obj=new Object[6];
			obj[0]=bean.getSubProfitCenter();
			obj[1]=bean.getActive();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getSubProfitCenter_Id();
			obj[4]=bean.getProductId();
			obj[5]=bean.getBranchCode();
			query=getQuery(DBConstants.SUBPROFIT_UPDATE_GETUPDATEDEPARTMENT);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateProfitCenter-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditSubprofitCenter(AdminBean bean) {
		LOGGER.info("getEditProfitCenter || Enter");
		try{
			Object obj[] = new Object[3];
			obj[0]=bean.getSubProfitCenter_Id();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getProductId();
			String query=getQuery(DBConstants.SUBPROFIT_SELECT_GETEDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setSubProfitCenter(map.get("TMAS_SPFC_NAME")==null?"":map.get("TMAS_SPFC_NAME").toString());
				bean.setActive(map.get("TMAS_STATUS")==null?"":map.get("TMAS_STATUS").toString());
				//bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
				bean.setSubProfitCenter_Id(map.get("TMAS_SPFC_ID")==null?"":map.get("TMAS_SPFC_ID").toString());
				bean.setProductId(map.get("TMAS_PRODUCT_ID")==null?"":map.get("TMAS_PRODUCT_ID").toString());
			}
			LOGGER.info("getEditProfitCenter || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getSContCoreCompanyCode(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.SUBPROFIT_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.SUBPROFIT_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}

	public int getContSubProfitCenter_Id(AdminBean bean){
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContSubProfitCenter_Id || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[3];
				obj[0]=bean.getSubProfitCenter_Id();
				obj[1]=bean.getProductId();
				obj[2]=bean.getBranchCode();
				query=getQuery(DBConstants.SUBPROFIT_INSERT_VALIDATIONQUERY1);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public int getContSubProfitCenter(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContSubProfitCenter || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[3];
				obj[0]=bean.getSubProfitCenter();
				obj[1]=bean.getProductId();
				obj[2]=bean.getBranchCode();
				query=getQuery(DBConstants.SUBPROFIT_INSERT_VALIDATIONQUERY);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[4];
				obj[0]=bean.getSubProfitCenter();
				obj[1]=bean.getProductId();
				obj[2]=bean.getSubProfitCenter_Id();
				obj[3]=bean.getBranchCode();
				query=getQuery(DBConstants.SUBPROFIT_EDIT_VALIDATIONQUERY);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}

	public List<AdminBean> getPolicyBranchList(AdminBean bean) {
		List PolicyBranchList = null;
		String query = "";
		try
		{
			LOGGER.info("getPolicyBranchList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.POLICYBUSINES_SELECT_GETLISTPOLICYBRANCH);

			if ("PB".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(TMAS_POL_BRANCH_NAME) LIKE UPPER(?)";
			}
			query +="order by TMAS_POL_BRANCH_ID,TMAS_POL_BRANCH_NAME";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			PolicyBranchList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getPolicyBranchList || Exit");
		}

		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return PolicyBranchList;
	}
	public int getInsertPolicyBranch(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertPolicyBranch");
			Object[] obj=new Object[5];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getPolicybranchname();
			obj[2]=bean.getActive();
			//obj[3]=bean.getCoreCompanyCode();
			obj[3]="";
			obj[4]=bean.getBranchCode();

			query=getQuery(DBConstants.POLICY_INSERT_GETINSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;

	}
	public int getUpdatePolicyBranch(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdatePolicyBranch-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getPolicybranchname();
			obj[1]=bean.getActive();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getPolicybranchid();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.POLICY_UPDATE_GETUPDATEPOLICY);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateProfitCenter-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}

		return count;
	}
	public void getEditPolicyBranch(AdminBean bean) {
		LOGGER.info("getEditPolicyBranch || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getPolicybranchid();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.POLICY_SELECT_GETEDITPOLICYBRANCHQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setPolicybranchid(map.get("TMAS_POL_BRANCH_ID")==null?"":map.get("TMAS_POL_BRANCH_ID").toString());
				bean.setPolicybranchname(map.get("TMAS_POL_BRANCH_NAME")==null?"":map.get("TMAS_POL_BRANCH_NAME").toString());
				bean.setActive(map.get("TMAS_STATUS")==null?"":map.get("TMAS_STATUS").toString());
				bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
			}
			LOGGER.info("getEditPolicyBranch || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getContpolicyCoreCompanyCode(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getpolicyContCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.POLICY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getPolicybranchid();
				query=getQuery(DBConstants.POLICY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getpolicyContCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}
	public int getContPolicyBranch(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContPolicyBranch || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getPolicybranchname();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.POLICY_INSERT_VALIDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getPolicybranchname();
				obj[1]=bean.getPolicybranchid();
				obj[2]=bean.getBranchCode();
				query=getQuery(DBConstants.POLICY_EDIT_VALIDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContPolicyBranch || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}
	public List<AdminBean> getUnderWriterList(AdminBean bean){
		List underwritertMasterList=null;
		String query=" ";
		try{
			LOGGER.info("getUnderWriterList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.UWR_SELECT_LISTQUERY);
			if ("UN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(UNDERWRITTER) LIKE UPPER(?)";
			}
			query +="order by UWR_CODE,UNDERWRITTER";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			underwritertMasterList = this.mytemplate.queryForList(query,obj);
			LOGGER.info("getUnderWriterList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");

		}
		return underwritertMasterList;
	}

	public int getInsertunderwritertMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertPolicyBranch");
			Object[] obj=new Object[5];
			obj[0]=bean.getUnderWriterId();
			obj[1]=bean.getUnderWriterName();
			obj[2]=bean.getActive();
			//obj[3]=bean.getCoreCompanyCode();
			obj[3]="";
			obj[4]=bean.getBranchCode();

			query=getQuery(DBConstants.UWR_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getUpdateunderwritertMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateunderwritertMaster-Enter");
			Object[] obj=new Object[5];

			obj[0]=bean.getUnderWriterName();
			obj[1]=bean.getActive();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getUnderWriterId();
			obj[4]=bean.getBranchCode();

			query=getQuery(DBConstants.UWR_UPDATE_UPDATEQUERY);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateunderwritertMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditunderwritertMaster(AdminBean bean){
		LOGGER.info("getEditunderwritertMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getUnderWriterId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.UWR_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setUnderWriterName(map.get("UNDERWRITTER")==null?"":map.get("UNDERWRITTER").toString());
				bean.setActive(map.get("UWR_STATUS")==null?"":map.get("UWR_STATUS").toString());
				//bean.setCoreCompanyCode(map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString());
			}
			LOGGER.info("getEditunderwritertMaster || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

	public int getContunderwriterCoreCompanyCode(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContunderwriterCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.UWR_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getUnderWriterId();
				query=getQuery(DBConstants.UWR_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContunderwriterCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}

	public int getContUnderWriterId(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContUnderWriterId || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getUnderWriterId();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.UWR_SELECT_CHECKAVAIL);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getContUnderWriterId || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}
	public List<AdminBean>getRiskGradeList(AdminBean bean){
		List RiskGradeList=null;
		String query="";

		try{
			LOGGER.info("getRiskGradeList ||Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.RISKGRADE_SELECT_LISTQUERY);
			if ("RG".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(GRADE_DESC) LIKE UPPER(?)";
			}
			query +="order by GRADE_DESC";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			RiskGradeList=this.mytemplate.queryForList(query,obj);
			LOGGER.info("getRiskGradeList ||Exit");
		}catch(Exception e){
			LOGGER.info("Exception @ { " + e + " }");
		}
		return RiskGradeList;
	}
	public int getInsertriskgradeMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertriskgradeMaster");
			Object[] obj=new Object[5];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getRiskGradeName();
			obj[2]=bean.getActive();
			//obj[3]=bean.getCoreCompanyCode();
			obj[3]="";
			obj[4]=bean.getBranchCode();

			query=getQuery(DBConstants.RISKGRADE_SELECT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getUpdateriskgradeMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateriskgradeMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getRiskGradeName();
			obj[1]=bean.getActive();
			//obj[2]=bean.getCoreCompanyCode();
			obj[2]="";
			obj[3]=bean.getRiskGradeId();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.RISKGRADE_UPDATE_UPDATEQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateriskgradeMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditriskgradeMaster(AdminBean bean) {
		LOGGER.info("getEditriskgradeMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getRiskGradeId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.RISKGRADE_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setRiskGradeId(map.get("GRADE_ID")==null?"":map.get("GRADE_ID").toString());
				bean.setRiskGradeName(map.get("GRADE_DESC")==null?"":map.get("GRADE_DESC").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCoreCompanyCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
			}
			LOGGER.info("getEditriskgradeMaster || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getContRiskGradeCoreCompanyCode(AdminBean bean){
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContRiskGradeCoreCompanyCode || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.RISKGRADE_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getRiskGradeId();
				query=getQuery(DBConstants.RISKGRADE_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContRiskGradeCoreCompanyCode || Exit");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return count;
	}
	public List<AdminBean>getCountryList(AdminBean bean){
		List CountryList=null;
		String query="";
		try{
			LOGGER.info("getCountryList || Enter");
			Object obj[]=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.COUNRY_SELECT_LISTQUERY);

			if ("CN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(CM.COUNTRY_NAME) LIKE UPPER(?)";
			}
			query +="order by CM.SNO,CM.COUNTRY_NAME";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			CountryList=this.mytemplate.queryForList(query,obj);
			LOGGER.info("getCountryList || Exit");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return CountryList;
	}
	public int getInsertCountryMaster(AdminBean bean) {
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getInsertCountryMaster");
			Object obj[]=new Object[8];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getCountryName();
			obj[2]=bean.getCountryShortName();
			obj[3]=bean.getActive();
			//obj[4]=bean.getCoreCompanyCode();
			obj[4]="";
			obj[5]=bean.getEffectDate();
			obj[6]=bean.getRemarks();
			obj[7]=bean.getBranchCode();

			query=getQuery(DBConstants.COUNTRY_INSERT_INSERTQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getUpdateCountryMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getUpdateCountryMaster-Enter");
			Object[] obj=new Object[11];
			obj[0]=bean.getSno();
			obj[1]=bean.getCountryId();
			obj[2]=bean.getCountryName();
			obj[3]=bean.getCountryShortName();
			obj[4]=bean.getActive();
			//obj[5]=bean.getCoreCompanyCode();
			obj[5]="";
			obj[6]=bean.getCountryId();
			obj[7]=bean.getBranchCode();
			obj[8]=bean.getEffectDate();
			obj[9]=bean.getRemarks();
			obj[10]=bean.getBranchCode();
			query=getQuery(DBConstants.COUNTRY_UPDATE_QUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getUpdateCountryMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public void getEditCountryMaster(AdminBean bean) {
		LOGGER.info("getEditCountryMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getCountryId();
			obj[1]=bean.getBranchCode();
			String query=getQuery(DBConstants.COUNTRY_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setSno(map.get("SNO")==null?"":map.get("SNO").toString());
				bean.setCountryId(map.get("COUNTRY_ID")==null?"":map.get("COUNTRY_ID").toString());
				bean.setCountryName(map.get("COUNTRY_NAME")==null?"":map.get("COUNTRY_NAME").toString());
				bean.setCountryShortName(map.get("COUNTRY_SHORT_NAME")==null?"":map.get("COUNTRY_SHORT_NAME").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCoreCompanyCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				bean.setNationality(map.get("NATIONALITY_NAME")==null?"":map.get("NATIONALITY_NAME").toString());
				bean.setAmmentID(map.get("AMEND_ID")==null?"":map.get("AMEND_ID").toString());
				bean.setEffectDate(map.get("E_DATE")==null?"":map.get("E_DATE").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
			}
			LOGGER.info("getEditCountryMaster || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

	public int getContcountryMasterCoreCompanyCode(AdminBean bean){
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContcountryMasterCoreCompanyCode || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.COUNTRY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getCountryId();
				query=getQuery(DBConstants.COUNTRY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContcountryMasterCoreCompanyCode || Exit");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return count;
	}
	public int getContcountryName(AdminBean bean){
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContcountryName || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[3];
				obj[0]=bean.getCountryId();
				obj[1]=bean.getCountryName();
				obj[2]=bean.getBranchCode();
				query=getQuery(DBConstants.COUNTRY_SELECT_UPDATECHECKAVAIL);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			LOGGER.info("getContcountryName || Exit");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return count;
	}
	public int getaddprocessMaster(AdminBean bean){
		String query="";
		int count=0;
		try{
			LOGGER.info("getaddprocessMaster || Enter");
			Object obj[]=new Object[8];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getDepartment();
			obj[3]=bean.getProcessName();
			obj[4]=bean.getActive();
			obj[5]=bean.getBranchCode();
			obj[6]=bean.getOrderno();
			obj[7]=bean.getUwlimit();
			query=getQuery(DBConstants.PROCESSMASTER_INSERT_INSERTPROCESS);
			LOGGER.info("String Query=>"+query);
			LOGGER.info(StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("getaddprocessMaster||Exit");
		}catch(Exception e){
			LOGGER.info("Exception @"+e);
		}
		return count;
	}
	public int getupdateprocessMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getupdateprocessMaster-Enter");
			Object[] obj=new Object[8];
			obj[0]=bean.getProductId();
			obj[1]=bean.getDepartmentId();
			obj[2]=bean.getProcessName();
			obj[3]=bean.getActive();
			obj[4]=bean.getOrderno();
			obj[5]=bean.getUwlimit();
			obj[6]=bean.getProcessId();
			obj[7]=bean.getBranchCode();
			query=getQuery(DBConstants.PROCESSMASTER_UPDATE_PROCESSDETAILS);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getupdateprocessMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getContProcessName(AdminBean bean) {
		boolean flag=false;
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContProcessName || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getProcessName();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_PROCESSNAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}
			else if(bean.getMode().equalsIgnoreCase("update")){
				obj= new Object[3];
				obj[0]=bean.getProcessName();
				obj[1]=bean.getProductId();
				obj[2]=bean.getBranchCode();
				obj[3]=bean.getProcessId();
				query=getQuery(DBConstants.PROCESSMASTER_SELECT_UPDATEPROCESSNAMECHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}

			LOGGER.info("getOrderno || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return count;
	}
	public List<AdminBean> getBranchList(AdminBean bean){
		List BranchList=null;
		String query="";
		try{
			LOGGER.info("getBranchList ||Enter");
			Object obj[]=new Object[1];
			query=getQuery(DBConstants.BRANCH_SELECT_LISTQUERY);
			if ("BN".equalsIgnoreCase(bean.getSearchType())) {
				obj[0] =bean.getSearchValue();
				query += " where UPPER(BRANCH_NAME) LIKE '%' ||UPPER(?) ||'%'";
				query +=" ORDER BY BRANCH_NAME";
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				BranchList=this.mytemplate.queryForList(query,obj);
			}else{
				query +=" ORDER BY BRANCH_CODE,BRANCH_NAME";
				LOGGER.info("query =>" + query);
				BranchList=this.mytemplate.queryForList(query);
			}
			LOGGER.info("getProcessList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return BranchList;
	}
	public List<AdminBean>getCountryIdList(AdminBean bean){
		List CountryList1 = null;
		String query = "";
		try
		{
			LOGGER.info("getCountryIdList");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.BRANCH_SELECT_COUNTRYLIST);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			CountryList1 =this.mytemplate.queryForList(query,obj);
			LOGGER.info("getCountryIdList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return CountryList1;
	}
	public List<AdminBean> getDcurrencyList(AdminBean bean){
		List DcurrencyList=null;
		String query="";
		try
		{
			LOGGER.info("getDcurrencyList");
			Object[] obj=new Object[2];
			obj[0]=bean.getCountryId();
			obj[1]=bean.getBranchCode();
			query=getQuery(DBConstants.BRANCH_SELECT_CURRENCYLIST);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			DcurrencyList =this.mytemplate.queryForList(query,obj);
			LOGGER.info("getDcurrencyList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return DcurrencyList;
	}
	public List<AdminBean> getBaseCurrencyList(AdminBean bean){
		List BaseCurrencyList=null;
		String query="";
		try
		{
			LOGGER.info("BaseCurrencyList");
			Object[] obj=new Object[2];
			obj[0]=bean.getCountryId();
			obj[1]=bean.getBranchCode();
			query=getQuery(DBConstants.BRANCH_SELECT_CURRENCYLIST);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			BaseCurrencyList =this.mytemplate.queryForList(query,obj);
			LOGGER.info("BaseCurrencyList || Exit");
		}catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return BaseCurrencyList;
	}
	public String getbranchCode1(AdminBean bean){
		String query="";
		String result="";
		try{
			LOGGER.info("getbranchCode | Enter");
			query=getQuery(DBConstants.BRANCH_SELECT_NEXTBRANCHCODE);
			Map<String, Object> forMap = this.mytemplate.queryForMap(query);
			result=forMap.get("BRANCH_CODE")==null?"":forMap.get("BRANCH_CODE").toString();

		}catch(Exception e){
			LOGGER.debug("EXCEPTION @{"+e+"}");
		}
		return result;
	}
	public int getinsertbranchMaster(AdminBean bean){
		String query="";
		int count=0;
		try{
			LOGGER.info("getinsertbranchMaster || Enter");
			Object obj[]=new Object[7];
			obj[0]=bean.getBranchCode1();
			obj[1]=bean.getBranchName();
			obj[2]=bean.getBaseCurrencyId();
			obj[3]=bean.getDescCurrencyId();
			obj[4]=bean.getActive();
			obj[5]=bean.getCountryId();
			//obj[6]=bean.getCoreCompanyCode();
			obj[6]="";
			query=getQuery(DBConstants.BRANCH_INSERT_BRANCHINSERT);
			LOGGER.info("String Query=>"+query);
			LOGGER.info(StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("getinsertbranchMaster||Exit");

		}catch(Exception e){
			LOGGER.info("Exception @"+e);
		}
		return count;
	}
	public void getEditbranchMaster(AdminBean bean){
		LOGGER.info("getEditbranchMaster || Enter");
		try{
			Object obj[] = new Object[1];
			obj[0]=bean.getBranchCode1();
			String query=getQuery(DBConstants.BRANCH_SELECT_BRANCHSELECT);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setBranchCode1(map.get("BRANCH_CODE")==null?"":map.get("BRANCH_CODE").toString());
				bean.setBranchName(map.get("BRANCH_NAME")==null?"":map.get("BRANCH_NAME").toString());
				bean.setBaseCurrencyId(map.get("BASE_CURRENCY_ID")==null?"":map.get("BASE_CURRENCY_ID").toString());
				bean.setDescCurrencyId(map.get("DESC_CURRENCY_ID")==null?"":map.get("DESC_CURRENCY_ID").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCountry(map.get("COUNTRY_ID")==null?"":map.get("COUNTRY_ID").toString());
				bean.setCoreCompanyCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				LOGGER.info("getEditbranchMaster || Exit");
			}

		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getupdatebranchMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getupdateprocessMaster-Enter");
			Object[] obj=new Object[7];
			obj[0]=bean.getBranchName();
			obj[1]=bean.getBaseCurrencyId();
			obj[2]=bean.getDescCurrencyId();
			obj[3]=bean.getActive();
			obj[4]=bean.getCountryId();
			//obj[5]=bean.getCoreCompanyCode();
			obj[5]="";
			obj[6]=bean.getBranchCode1();
			query=getQuery(DBConstants.BRANCH_UPDATE_BRANCHUPDATE);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getupdateprocessMaster-Exit");
		}catch(Exception e){
			LOGGER.debug("ECXCEPTION"+e);
		}

		return count;
	}
	public int getContbranchMasterCoreCompanyCode(AdminBean bean){
		String query="";
		Object obj[];
		int count=0;

		LOGGER.info("getContbranchMasterCoreCompanyCode || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[1];
				obj[0]=bean.getCoreCompanyCode();

				query=getQuery(DBConstants.BRANCH_SELECT_CHECKCOREAPPCODE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();

				query=getQuery(DBConstants.BRANCH_SELECT_CHECKCOREAPPCODEUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContbranchMasterCoreCompanyCode || Exit");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return count;
	}

	public List<AdminBean>getCedingList(AdminBean bean){
		List CedingList=null;
		String query="";
		try{
			LOGGER.info("getCedingList || Enter");
			Object[] obj=new Object[2];
			obj[0]=bean.getCustomerType();
			obj[1]=bean.getBranchCode();
			query=getQuery(DBConstants.CEDING_SELECT_LISTQUERY);

			if ("CN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(COMPANY_NAME) LIKE UPPER(?)";
			}else if("BN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(FIRST_NAME) LIKE UPPER(?)";
			}

			if((bean.getCustomerType().equals("C"))||(bean.getCustomerType().equals("L"))){
				query += " order by COMPANY_NAME ";
			}else if(bean.getCustomerType().equals("B")){
				query += " order by FIRST_NAME ";
			}

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			CedingList=this.mytemplate.queryForList(query,obj);
			LOGGER.info("getCedingList || Exit");
		}catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return CedingList;
	}
	public int getinsertcedingMaster(AdminBean bean){
		String query="";
		int count=0;

		try{
			LOGGER.info("getinsertcedingMaster || Enter");
			Object obj[]=new Object[29];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getTitle();
			obj[2]=bean.getFirstName();
			obj[3]=bean.getTelephone();
			obj[4]=bean.getMobile();
			obj[5]=bean.getFaxNo();
			obj[6]=bean.getEmail();
			obj[7]=bean.getAddress1();
			obj[8]=bean.getAddress2();
			obj[9]=bean.getDesignation();
			obj[10]=bean.getCountry();
			obj[11]=bean.getInceptionDate();
			obj[12]=bean.getInceptionDate();
			obj[13]=bean.getRemarks();
			obj[14]=bean.getActive();
			obj[15]=bean.getLoginId();
			obj[16]=bean.getCompanyName();
			obj[17]=bean.getCustomerType();
			obj[18]=bean.getTelephone();
			obj[19]=bean.getCity();
			obj[20]=bean.getCompanyCode();
			obj[21]=bean.getBranchCode();
			obj[22]=bean.getZipcode();
			obj[23]=bean.getLastName();
			//obj[24]=bean.getCoreCompanyCode();
			obj[24]="";
			obj[25]=bean.getPanNo();
			obj[26]=bean.getIsIndividual();
			obj[27]=bean.getIsNonResident();
			obj[28]=bean.getSpecialRate();


			query=getQuery(DBConstants.CEDING_INSERT_INSERTQUERY);
			LOGGER.info("String Query=>"+query);
			LOGGER.info(StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			LOGGER.info("getinsertcedingMaster||Exit");

		}catch(Exception e){
			LOGGER.info("Exception @"+e);
		}
		return count;
	}
	public void geteditcedingMaster(AdminBean bean){
		LOGGER.info("geteditcedingMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getCustomerId();
			obj[1]=bean.getBranchCode();

			String query=getQuery(DBConstants.CEDING_SELECT_EDITQUERY);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
				bean.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
				//bean.setTitle(map.get("EFFECTIVEDATE")==null?"":map.get("EFFECTIVEDATE").toString());
				bean.setFirstName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				bean.setTelephone(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
				bean.setMobile(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				bean.setFaxNo(map.get("FAX")==null?"":map.get("FAX").toString());
				bean.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
				bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
				bean.setDesignation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
				bean.setCountry(map.get("COUNTRY")==null?"":map.get("COUNTRY").toString());
				bean.setInceptionDate(map.get("INCEPTIONDATE")==null?"":map.get("INCEPTIONDATE").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setCompanyName(map.get("COMPANY_NAME")==null?"":map.get("COMPANY_NAME").toString());
				bean.setTelephone(map.get("CED_COMPANY_PHONE")==null?"":map.get("CED_COMPANY_PHONE").toString());
				bean.setCity(map.get("CITY")==null?"":map.get("CITY").toString());
				bean.setCompanyCode(map.get("COMPANY_CODE")==null?"":map.get("COMPANY_CODE").toString());
				bean.setZipcode(map.get("POBOX")==null?"":map.get("POBOX").toString());
				bean.setCoreCompanyCode(map.get("APPLICATION_ID")==null?"":map.get("APPLICATION_ID").toString());
				bean.setLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
				bean.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
				bean.setPanNo(map.get("PAN_NUMBER")==null?"":map.get("PAN_NUMBER").toString());
				bean.setIsIndividual(map.get("INDIVIDUALYN")==null?"":map.get("INDIVIDUALYN").toString());
				bean.setIsNonResident(map.get("NON_RESIDENTYN")==null?"":map.get("NON_RESIDENTYN").toString());
				bean.setSpecialRate(map.get("SPECIAL_RATE")==null?"":map.get("SPECIAL_RATE").toString());
				LOGGER.info("geteditcedingMaster || Exit");
			}

		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public int getupdatecedingMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getupdatecedingMaster-Enter");
			Object obj1[]=new Object[5];
			obj1[0]=bean.getInceptionDate();
			obj1[1]=bean.getCustomerId();
			obj1[2]=bean.getCustomerId();
			obj1[3]=bean.getBranchCode();
			obj1[4]=bean.getBranchCode();
			query=getQuery(DBConstants.CEDING_UPDATE_EXPIRYDATE);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj1,","));
			int count1=this.mytemplate.update(query,obj1);
			LOGGER.info("Count==>"+count1);
			LOGGER.info("get upadation expirydate || Exit");

			Object obj[]=new Object[31];
			obj[0]=bean.getCustomerId();
			obj[1]=bean.getTitle();
			obj[2]=bean.getFirstName();
			obj[3]=bean.getTelephone();
			obj[4]=bean.getMobile();
			obj[5]=bean.getFaxNo();
			obj[6]=bean.getEmail();
			obj[7]=bean.getAddress1();
			obj[8]=bean.getAddress2();
			obj[9]=bean.getDesignation();
			obj[10]=bean.getCountry();
			obj[11]=bean.getCustomerId();
			obj[12]=bean.getBranchCode();
			obj[13]=bean.getInceptionDate();
			obj[14]=bean.getInceptionDate();
			obj[15]=bean.getRemarks();
			obj[16]=bean.getActive();
			obj[17]=bean.getLoginId();
			obj[18]=bean.getCompanyName();
			obj[19]=bean.getCustomerType();
			obj[20]=bean.getTelephone();
			obj[21]=bean.getCity();
			obj[22]=bean.getCompanyCode();
			obj[23]=bean.getBranchCode();
			obj[24]=bean.getZipcode();
			obj[25]=bean.getLastName();
			//obj[26]=bean.getCoreCompanyCode();
			obj[26]="";
			obj[27]=bean.getPanNo();
			obj[28]=bean.getIsIndividual();
			obj[29]=bean.getIsNonResident();
			obj[30]=bean.getSpecialRate();

			query=getQuery(DBConstants.CEDING_UPDATE_QUERY);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count = this.mytemplate.update(query, obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getupdatecedingMaster-Exit");
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	public int getContcedingMasterCoreCompanyCode(AdminBean bean)
	{
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getContcedingMasterCoreCompanyCode || Enter");
		try{
			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.CEDING_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getCustomerId();
				query=getQuery(DBConstants.CEDING_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getContcedingMasterCoreCompanyCode || Exit");

		}catch(Exception e2){
			e2.printStackTrace();
		}
		return count;
	}

	public List AdminSubMenuAllcationInfo(AdminBean bean) {

		LOGGER.info("UserBusinessImpl subMenuAllocationInfo || Enter");
		List list=null;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_SUBMENUALLOCATIONINFO);
			String[] args=new String[5];
			args[0]=bean.getLoginId();
			args[1]=bean.getLoginId();
			args[2]=bean.getBranchCode();
			args[3]=bean.getBranchCode();
			args[4]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}
			list=mytemplate.queryForList(sql, args);
			LOGGER.info("Result Size====>"+list.size());
		}
		catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return list;
	}
	public boolean getAdminSubMenuids(AdminBean bean) {
		/*LOGGER.info("UserBusinessImpl AdminManuAllocationInfo || Enter");
			String s="";
			try{
				String sql=getQuery(DBConstants.USER_SELECT_ADMINMENUIDS);
				String[] args=new String[1];
				args[0]=bean.getLoginId();
				LOGGER.info("Select Query====>"+sql);
				int i=0;
				for(String a:args){
					LOGGER.info("Arg["+i+"]====>"+a);
					i++;
				}
				s=(String)mytemplate.queryForObject(sql, args, String.class);
				LOGGER.info("Result Size==>"+sql);
			}
			catch(Exception e){
				LOGGER.debug("EXCEPTION @ {"+e+"}");

				//throw new MotorBaseException(exception,MotorExceptionConstants.OTHER_ERROR);
			}
			LOGGER.info("UserBusinessImpl AdminManuAllocationInfo || Exit");
			return s;*/
		LOGGER.info("UserBusinessImpl checkDeptSubMenuAllocated || Enter");
		boolean flag=false;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_CHECKDEPTALLOCATEDSUBMENU);
			String[] args=new String[1];

			args[0]=bean.getLoginId();

			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.queryForInt(sql, args);
			if(count==0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception e){
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}
		LOGGER.info("UserBusinessImpl checkDeptSubMenuAllocated || Exit");
		return flag;
	}


	public List AdminMenuAllcationInfo(AdminBean bean) {
		List AdminMenuList=null;
		String query="";
		try{
			LOGGER.info("AdminMenuAllcationInfo || Enter");
			query=getQuery(DBConstants.USER_SELECT_ADMINMENULIST);
			LOGGER.info("query =>" + query);
			AdminMenuList=this.mytemplate.queryForList(query);
			LOGGER.info("getCedingList || Exit");
		}catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return AdminMenuList;
	}
	public String getAdminMenuids(AdminBean bean) {
		String query="";
		String result="";
		try{
			LOGGER.info("AdminMenuAllcationInfo || Enter");
			Object obj[]= new Object[1];
			obj[0]=bean.getLoginId();
			query=getQuery(DBConstants.USER_SELECT_ADMINMENUIDS);
			LOGGER.info("query =>" + query);
			result=(String)mytemplate.queryForObject(query, obj, String.class);
			LOGGER.info("getCedingList || Exit");
		}catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return result;
	}
	public List<AdminBean>getOpenList(AdminBean bean){
		List openlist=null;
		String query="";
		try{
			LOGGER.info("getOpenList || Enter");
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			query=getQuery(DBConstants.OPEN_PERIOD_SELECT_LISTQUERY);

			if ("SD".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(START_DATE) LIKE UPPER(?)";
			}else if("ED".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(END_DATE) LIKE UPPER(?)";
			}
			query +="order by sno ASC";

			openlist=this.mytemplate.queryForList(query,obj);
			LOGGER.info("getOpenList || Exit"+query+"  "+openlist);
		}catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return openlist;
	}

	public int getinsertopenperiodMaster(AdminBean bean){
		String query="";
		int count=0;

		try{
			LOGGER.info("getinsertopenperiodMaster || Enter");
			Object obj[]=new Object[4];
			obj[0]=bean.getStartDate();
			obj[1]=bean.getEndDate();
			obj[2]=bean.getActive();
			obj[3]=bean.getBranchCode();

			query=getQuery(DBConstants.OPEN_PERIOD_INSERT_INSERTDATA);
			LOGGER.info("String Query=>"+query);
			LOGGER.info(StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			if(count==1 && bean.getActive().equals("Y")){
				query=getQuery(DBConstants.OPEN_PERIOD_STATUS1);
				this.mytemplate.update(query,new Object[]{bean.getBranchCode(),bean.getBranchCode()});
			}
			LOGGER.info("getinsertopenperiodMaster||Exit"+query);

		}catch(Exception e){
			LOGGER.info("Exception @"+e);
		}
		return count;
	}

	public void getEditopenperiodMaster(AdminBean bean)
	{
		LOGGER.info("getEditopenperiodMaster || Enter");
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getSno();
			obj[1]=bean.getBranchCode();

			String query=getQuery(DBConstants.OPEN_PERIOD_SELECT_EDITDATA);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setSno(map.get("sno")==null?"":map.get("sno").toString());
				bean.setStartDate(map.get("START_DATE")==null?"":map.get("START_DATE").toString());
				bean.setEndDate(map.get("END_DATE")==null?"":map.get("END_DATE").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());

				LOGGER.info("getEditopenperiodMaster || Exit" +list);

			}

		}catch(Exception exception){
			exception.printStackTrace();
		}


	}
	public int getupdateopenperiodMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getupdateopenperiodMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getStartDate();
			obj[1]=bean.getEndDate();
			obj[2]=bean.getActive();
			obj[3]=bean.getSno();
			obj[4]=bean.getBranchCode();
			query=getQuery(DBConstants.OPEN_PERIOD_UPDATE_UPDATEDATA);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count=this.mytemplate.update(query,obj);
			if(count==1 && bean.getActive().equals("Y")){
				query=getQuery(DBConstants.OPEN_PERIOD_STATUS);
				this.mytemplate.update(query,new Object[]{bean.getSno(),bean.getBranchCode()});
			}
			LOGGER.info("Count==>"+count);
			LOGGER.info("getupdateopenperiodMaster-Exit"+query);
		}catch(Exception e){
			LOGGER.debug("ECXCEPTION"+e);
		}

		return count;
	}

	public  List<AdminBean>getSubMenuList(AdminBean bean){
		List submenulist=null;
		String query="";
		try{
			LOGGER.info("getSubMenuList || Enter");
			Object[] obj=new Object[1];
			query=getQuery(DBConstants.SUBMENU_SELECT_SUBMENULIST);
			if ("SN".equalsIgnoreCase(bean.getSearchType())) {
				obj[0] =bean.getSearchValue();
				query += " where UPPER(SUB_MENU_NAME) LIKE '%' ||UPPER(?) ||'%'";
				query +=" ORDER BY SUB_MENU_NAME";
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				submenulist=this.mytemplate.queryForList(query,obj);
			}else{
				query +=" ORDER BY SUB_MENU_NAME";
				LOGGER.info("query =>" + query);
				submenulist=this.mytemplate.queryForList(query);
			}
			LOGGER.info("getSubMenuList || Exit"+query);
		}catch(Exception e)
		{
			LOGGER.debug("EXCEPTION @ {"+e+"}");
		}

		return submenulist;
	}

	public int getinsertsubmenuMaster(AdminBean bean){
		String query="";
		int count=0;

		try{
			LOGGER.info("getinsertopenperiodMaster || Enter");
			Object obj[]=new Object[4];
			obj[0]=bean.getSubMenuCode();
			obj[1]=bean.getSubMenuName();
			obj[2]=bean.getActive();
			obj[3]=bean.getRemarks();

			query=getQuery(DBConstants.SUBMENU_INSERT_SUBMENUINSERT);
			LOGGER.info("String Query=>"+query);
			LOGGER.info(StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);

			LOGGER.info("getinsertopenperiodMaster||Exit"+query);

		}catch(Exception e){
			LOGGER.info("Exception @"+e);
		}
		return count;
	}

	public void getEditsubmenuMaster(AdminBean bean){
		LOGGER.info("getEditsubmenuMaster || Enter");
		try{
			Object obj[] = new Object[1];
			obj[0]=bean.getSubMenuCode();
			String query=getQuery(DBConstants.SUBMENU_SELECT_SUBMENUSELECT);
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setSubmenuId(map.get("SUB_MENU_ID")==null?"":map.get("SUB_MENU_ID").toString());
				bean.setSubMenuCode(map.get("SUB_MENU_CODE")==null?"":map.get("SUB_MENU_CODE").toString());
				bean.setSubMenuName(map.get("SUB_MENU_NAME")==null?"":map.get("SUB_MENU_NAME").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());

				LOGGER.info("getEditsubmenuMaster || Exit" +list);

			}

		}catch(Exception exception){
			exception.printStackTrace();
		}


	}
	public int getupdatsubmenuMaster(AdminBean bean){
		String query = "";
		int count=0;
		try
		{
			LOGGER.info("getupdatsubmenuMaster-Enter");
			Object[] obj=new Object[5];
			obj[0]=bean.getSubMenuCode();
			obj[1]=bean.getSubMenuName();
			obj[2]=bean.getActive();
			obj[3]=bean.getRemarks();
			obj[4]=bean.getSubmenuId();

			query=getQuery(DBConstants.SUBMENU_UPDATE_UPDATESUBMENU);

			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));

			count=this.mytemplate.update(query,obj);
			LOGGER.info("Count==>"+count);
			LOGGER.info("getupdatsubmenuMaster-Exit"+query);
		}catch(Exception e){
			LOGGER.debug("ECXCEPTION"+e);
		}

		return count;
	}
	public int getPolicyCoreCompanyCode(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		LOGGER.info("getpolicyContCoreCompanyCode || Enter");
		try{

			if(bean.getMode().equalsIgnoreCase("insert")){
				obj= new Object[2];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.POLICY_SELECT_CORECHECKINSERT);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}else{
				obj= new Object[3];
				obj[0]=bean.getCoreCompanyCode();
				obj[1]=bean.getBranchCode();
				obj[2]=bean.getPolicybranchid();
				query=getQuery(DBConstants.POLICY_SELECT_CORECHECKUPDATE);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);
			}LOGGER.info("getpolicyContCoreCompanyCode || Exit");

		}catch(Exception exception){
			exception.printStackTrace();
		}

		return count;
	}
	public int getCountRiskGradeName(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getCountRiskGradeName || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[2];

			query=getQuery(DBConstants.RISKGRADE_SELECT_CHECKAVAIL);
			arg[0]=bean.getRiskGradeName();
			arg[1]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[3];

			query=getQuery(DBConstants.RISKGRADE_SELECT_UPDATECHECKAVAIL);
			arg[0]=bean.getRiskGradeId();
			arg[1]=bean.getRiskGradeName();
			arg[2]=bean.getBranchCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}

		LOGGER.info(" getCountRiskGradeName || Exit");
		return count;
	}
	public int getCountProfitId(AdminBean bean) {
		String query="";
		Object obj[];
		int count=0;
		if(bean.getMode().equals("insert")){
			LOGGER.info("getCountProfitId || Enter");
			try{
				obj= new Object[2];
				obj[0]=bean.getProfitCenterId();
				obj[1]=bean.getBranchCode();
				query=getQuery(DBConstants.PROFIT_SELECT_PROFITIDCHECK);
				LOGGER.info("query =>" + query);
				LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
				count = mytemplate.queryForInt(query, obj);

				LOGGER.info("getContbranchMasterCoreCompanyCode || Exit");
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return count;
	}
	public int getCountSubMenuCode(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getCountSubMenuCode || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[1];

			query=getQuery(DBConstants.SUBMENU_SELECT_INSERTCHECK);
			arg[0]=bean.getSubMenuCode();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[2];

			query=getQuery(DBConstants.SUBMENU_SELECT_UPDATECHECK);
			arg[0]=bean.getSubMenuCode();
			arg[1]=bean.getSubmenuId();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}

		LOGGER.info(" getCountSubMenuCode || Exit");
		return count;
	}
	public int getCountSubMenuName(AdminBean bean) {
		String query="";
		int count=0;
		LOGGER.info(" getCountSubMenuName || Enter");
		if(bean.getMode().equals("insert")){
			String[] arg=new String[1];

			query=getQuery(DBConstants.SUBMENU_SELECT_INSERTCHECK2);
			arg[0]=bean.getSubMenuName();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}else{
			String[] arg=new String[2];

			query=getQuery(DBConstants.SUBMENU_SELECT_UPDATECHECK2);
			arg[0]=bean.getSubMenuName();
			arg[1]=bean.getSubmenuId();
			LOGGER.info("Select Query====>"+query);
			LOGGER.info("Arg[]=>"+StringUtils.join(arg,","));
			count=mytemplate.queryForInt(query, arg);
		}

		LOGGER.info(" getCountSubMenuName || Exit");
		return count;
	}
	public boolean checkDeptMenuAllocated(AdminBean bean) {
		LOGGER.info("UserBusinessImpl checkDeptAllocated || Enter");
		boolean flag=false;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_CHECKDEPTALLOCATED);
			String[] args=new String[1];

			args[0]=bean.getLoginId();

			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.queryForInt(sql, args);
			if(count==0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){

			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl checkDeptAllocated || Exit");
		return flag;
	}

	public boolean subMenuGlobalSubmit(AdminBean bean) {
		boolean flag=false;
		LOGGER.info("UserBusinessImpl subMenuGlobalSubmit || Enter");
		try{
			String sql=getQuery(DBConstants.USER_INSERT_SUBMENUGLOBALSUBMIT);
			String[] args=new String[1];
			args[0]=bean.getLoginId();
			LOGGER.info("Insert Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.update(sql, args);
			if(count>0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl subMenuGlobalSubmit || Exit");
		return flag;
	}
	public boolean updateSubMenuGlobalSubmit(AdminBean bean, String status,String prod) {
		LOGGER.info("UserBusinessImpl updateSubMenuGlobalSubmit || Enter");
		boolean flag=true;
		try{
			String sql=getQuery(DBConstants.USER_UPDATE_UPDATESUBMENUGLOBALSUBMIT);
			String[] args=new String[7];
			//String[] st=processId.split(",");
			String[] s=prod.split(",");
			args[0]=status;
			args[1]=bean.getLoginId();
			args[2]=s[1];
			args[3]=bean.getBranchCode();
			args[4]=s[0];
			args[5]=bean.getBranchCode();
			args[6]=bean.getBranchCode();

			LOGGER.info("Update Query====>"+sql);
			int i=0;
			for(String a:args){
				LOGGER.info("Arg["+i+"]====>"+a);
				i++;
			}
			int count=mytemplate.update(sql, args);

			if(count<0){
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return flag;
	}
	public List allocationInfo(AdminBean bean) {
		LOGGER.info("SubMenuBusinessImpl allocationInfo || Enter");
		List list=new ArrayList();
		try{
			String sql=getQuery(DBConstants.SUBMENU_SELECT_SUBMENUINFO);
			String[] args=new String[3];
			args[0]=bean.getBranchCode();
			args[1]=bean.getBranchCode();
			args[2]=bean.getBranchCode();

			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}
			list=mytemplate.queryForList(sql, args);
			LOGGER.info("Result Size==>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("SubMenuBusinessImpl allocationList || Exit");
		return list;
	}
	public List submenuAllocation(AdminBean bean) {
		LOGGER.info("UserBusinessImpl submenuAllocation || Enter");
		List list=null;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_GETALLOCATEDSUBMENUS);
			String[] args=new String[5];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=st[1];
			args[1]=st[2];
			args[2]=st[1];
			args[3]=bean.getLoginId();
			args[4]=st[2];
			LOGGER.info("Select Query====>"+sql);

			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			bean.setProcessId(processId);
			bean.setSubmenuId(("SUB_MENU_ID")==null?"":("SUB_MENU_ID").toString());
			//PROCESS_ID = '3'
			// AND LOGIN_ID = 'test'
			//AND MENU_ID = '9'
			//String menuid=bean.setMenuid("menuid");
			//String loginId=bean.setLoginId("loginId");
			list=mytemplate.queryForList(sql, args);
			LOGGER.info("Result Size==>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl submenuAllocation || Exit");
		return list;
	}

	public boolean SaveManu(AdminBean bean, String menuids) {
		LOGGER.info("UserBusinessImpl SaveManu || Enter");
		boolean flag=false;
		try{
			boolean op=menuCheck(bean);
			String sql="";
			String[] args;
			if(op){
				sql=getQuery(DBConstants.USER_UPDATE_UPDATEMENUIDS);
				args=new String[4];
				String processId=bean.getProcessId();
				String[] st=processId.split(",");
				args[0]=menuids;
				args[1]=st[0];
				args[2]=st[1];
				args[3]=bean.getLoginId();

				LOGGER.info("Update Query====>"+sql);
			}
			else{
				sql=getQuery(DBConstants.USER_INSERT_INSERTMENUIDS);
				args=new String[4];
				String processId=bean.getProcessId();
				String[] st=processId.split(",");
				args[0]=bean.getLoginId();
				args[1]=st[1];
				args[2]=menuids;
				args[3]=st[0];
				LOGGER.info("Insert Query====>"+sql);
			}
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.update(sql, args);
			LOGGER.info("Effected Records==>"+count);
			if(count==0){
				flag=false;
			}
			else{
				flag=true;
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl SaveManu || Exit");
		return flag;
	}
	public List allocationList(AdminBean bean) {
		LOGGER.info("SubMenuBusinessImpl allocationList || Enter");
		List list=new ArrayList();
		try{
			String sql=getQuery(DBConstants.SUBMENU_SELECT_SUBMENUALLOCATIONLIST);
			String[] args=new String[2];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=st[0];
			args[1]=st[1];
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}
			list=mytemplate.queryForList(sql, args);
			LOGGER.info("Result Size==>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("SubMenuBusinessImpl allocationList || Exit");
		return list;
	}
	public boolean subMenuSave(AdminBean bean, String submenuids) {
		LOGGER.info("UserBusinessImpl subMenuSave || Enter");
		boolean result=false;
		try{
			boolean flag=checkSubMenu(bean);
			String sql;
			String[] args;
			if(flag){
				sql=getQuery(DBConstants.USER_UPDATE_UPDATESUBMNEUIDS);
				args=new String[4];
				String processId=bean.getProcessId();
				String[] st=processId.split(",");
				args[0]=submenuids;
				args[1]=st[1];
				args[2]=bean.getLoginId();
				args[3]=st[2];
				LOGGER.info("Update Query====>"+sql);
			}
			else{
				sql=getQuery(DBConstants.USER_INSERT_INSERTSUBMENUIDS);
				args=new String[4];
				String processId=bean.getProcessId();
				String[] st=processId.split(",");
				args[0]=st[1];
				args[1]=bean.getLoginId();
				args[2]=st[2];
				args[3]=submenuids;
				LOGGER.info("Insert Query====>"+sql);
			}
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}

			int count=mytemplate.update(sql,args);

			LOGGER.info("Result is==>"+count);
			if(count>0){
				result=true;
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl subMenuSave || Exit");
		return result;
	}
	public boolean checkSubMenu(AdminBean bean) {
		LOGGER.info("UserBusinessImpl checkSubMenu || Enter");
		boolean flag=false;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_CHECKSUBMENUALLOCATION);
			String processId=bean.getProcessId();
			String[] st=processId.split(",");

			String[] args=new String[3];
			args[0]=bean.getLoginId();
			args[1]=st[1];
			args[2]=st[2];

			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.queryForInt(sql, args);
			if(count>0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl checkSubMenu || Exit");
		return flag;
	}
	public String menuAllocationPath(AdminBean bean) {
		LOGGER.info("UserBusinessImpl menuAllocationPath || Enter");
		String result="";
		try{
			String sql=getQuery(DBConstants.USER_SELECT_MENUALLOCATIONPATH);
			String[] args=new String[4];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=bean.getBranchCode();
			args[1]=bean.getBranchCode();
			args[2]=bean.getBranchCode();
			args[3]=st[1];
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			result=mytemplate.queryForObject(sql, args, String.class).toString();
			LOGGER.info("Result is==>"+result);
		}
		catch(Exception exception){
			exception.printStackTrace();

		}
		LOGGER.info("UserBusinessImpl menuAllocationPath || Exit");
		return result;
	}
	public boolean menuCheck(AdminBean bean) {
		LOGGER.info("UserBusinessImpl menuCheck || Enter");
		boolean result=false;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_CHECKMENUALLOCATED);
			String[] args=new String[2];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=bean.getLoginId();
			args[1]=st[1];
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.queryForInt(sql, args);
			if(count>0){
				result=true;
			}
			else{
				result=false;
			}
			LOGGER.info("Result Is==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl menuCheck || Exit");
		return result;
	}
	public List menubuttonAllocation(AdminBean bean) {
		LOGGER.info("UserBusinessImpl menuAllocation || Enter");
		List list=null;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_GETALLOCATEDMENUS);
			String[] args=new String[4];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=st[1];
			args[1]=bean.getLoginId();
			args[2]=st[0];
			args[3]=st[1];
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			list=mytemplate.queryForList(sql, args);
			LOGGER.info("Result Size==>"+list.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl menuAllocation || Exit");
		return list;
	}
	public boolean checkDeptsubMenuAllocated(AdminBean bean) {
		LOGGER.info("UserBusinessImpl checkDeptSubMenuAllocated || Enter");
		boolean flag=false;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_CHECKDEPTALLOCATEDSUBMENU);
			String[] args=new String[1];
			args[0]=bean.getLoginId();
			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.queryForInt(sql, args);
			if(count==0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl checkDeptSubMenuAllocated || Exit");
		return flag;
	}
	public boolean saveAdminMenus(AdminBean bean, String menus) {
		LOGGER.info("UserBusinessImpl saveAdminMenus || Enter");
		boolean flag=false;
		try{
			String check=getAdminMenuids(bean);
			String sql;
			String[] args;
			if("".equalsIgnoreCase(check)){
				sql=getQuery(DBConstants.USER_INSERT_ADMINMENUS);
				args=new String[2];
				args[0]=bean.getLoginId();
				args[1]=menus;
				LOGGER.info("Insert Query====>"+sql);
			}
			else{
				sql=getQuery(DBConstants.USER_UPDATE_ADMINMENUS);
				args=new String[2];
				args[0]=menus;
				args[1]=bean.getLoginId();
				LOGGER.info("Update Query====>"+sql);
			}
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}

			int count=mytemplate.update(sql, args);
			if(count>0){
				flag=true;
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl saveAdminMenus || Exit");
		return flag;
	}
	public boolean MenuGlobalSubmit(AdminBean bean) {
		boolean flag=false;
		LOGGER.info("UserBusinessImpl menuGlobalSubmit || Enter");
		try{
			String sql=getQuery(DBConstants.USER_INSERT_INSERTMENUGLOBALSUBMIT);
			String[] args=new String[1];
			args[0]=bean.getLoginId();
			LOGGER.info("Insert Query====>"+sql);
			int i=0;
			for(String o:args){
				LOGGER.info("Arg["+i+"]====>"+o);
				i++;
			}
			int count=mytemplate.update(sql, args);
			if(count>0){
				flag=true;
			}
			else{
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl menuGlobalSubmit || Exit");
		return flag;
	}
	public List getPrevoiusValues(AdminBean bean) {
		LOGGER.info("UserBusinessImpl getPrevoiusValues || Enter");
		List list=new ArrayList();
		try{
			String sql=getQuery(DBConstants.USER_SELECT_MENUALLOCATIONINFO);
			String[] args=new String[4];
			args[0]=bean.getLoginId();
			args[1]=bean.getBranchCode();
			args[2]=bean.getBranchCode();
			args[3]=bean.getBranchCode();

			LOGGER.info("Select Query====>"+sql);
			int i=0;
			for(String a:args){
				LOGGER.info("Arg["+i+"]====>"+a);
				i++;
			}

			list=mytemplate.queryForList(sql, args);

			LOGGER.info("Result Size==>"+list.size());

		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl getPrevoiusValues || Exit");
		return list;
	}
	public boolean update(AdminBean bean, String status, String prod) {
		LOGGER.info("UserBusinessImpl update || Enter");
		boolean flag=true;
		try{
			String sql=getQuery(DBConstants.USER_UPDATE_MENUGLOBALSUBMIT);
			String[] args=new String[6];

			String[] st=prod.split(",");

			args[0]=status;
			args[1]=bean.getLoginId();
			args[2]=st[1];
			args[3]=bean.getBranchCode();
			args[4]=st[0];
			args[5]=bean.getBranchCode();

			LOGGER.info("Update Query====>"+sql);
			int i=0;
			for(String a:args){
				LOGGER.info("Arg["+i+"]====>"+a);
				i++;
			}
			int count=mytemplate.update(sql, args);

			if(count<0){
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl update || Exit");
		return flag;
	}
	public boolean updateUWLimit(AdminBean bean, String uwvalue,String processid) {
		LOGGER.info("UserBusinessImpl updateUWLimit || Enter");
		boolean flag=true;
		try{


			String sql=getQuery(DBConstants.USER_UPDATE_UWLIMIT);

			String[] args=new String[3];
			args[0]=uwvalue;
			args[1]=processid;
			args[2]=bean.getLoginId();

			LOGGER.info("Update Query====>"+sql);
			int i=0;
			for(String a:args){
				LOGGER.info("Arg["+i+"]====>"+a);
				i++;
			}
			int count=mytemplate.update(sql, args);
			if(!(count>0)){
				flag=false;
			}
			LOGGER.info("Result Size==>"+count);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("UserBusinessImpl updateUWLimit || Exit");
		return flag;
	}
	public boolean subMenuAllocationSave(AdminBean bean) {
		LOGGER.info("SubMenuBusinessImpl subMenuAllocationSave || Enter");
		boolean flag=false;
		try{
			/*String query=getQuery(DBConstants.SUBMENU_SELECT_CHECKSUBMENUALLOCATED);
			String[] arg=new String[2];
			arg[0]=beanObj.getProcess();
			arg[1]=beanObj.getMenuid();
			LogManager.info("Select Q");*/


			String sql=getQuery(DBConstants.SUBMENU_UPDATE_UPDATEMENU);
			String[] args=new String[3];
			String processId=bean.getProcessId();
			String[] st=processId.split(",");
			args[0]=bean.getMenuidList();
			args[1]=st[0];
			args[2]=st[1];

			LOGGER.info("Update Query====>"+sql);
			int i=0;
			for(String s:args){
				LOGGER.info("Arg["+i+"]====>"+s);
				i++;
			}
			int count=mytemplate.update(sql, args);
			LOGGER.info("Result Size==>"+count);
			if(count>0){
				flag=true;
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		LOGGER.info("SubMenuBusinessImpl subMenuAllocationSave || Exit");
		return flag;
	}
	public List<AdminBean> getUWLimitMasterList(AdminBean bean) {
		List UWLimitList=null;
		String query="";
		try{
			query=getQuery(DBConstants.UWLIMIT_LIST);
			Object[] obj=new Object[1];
			obj[0]=bean.getBranchCode();
			if ("UN".equalsIgnoreCase(bean.getSearchType())) {
				obj = new DropDownControllor().getIncObjectArray(obj, new Object[]{("%" + bean.getSearchValue() + "%")});
				query += " and UPPER(UNDERWRITTER) LIKE UPPER(?)";
			}
			query=query+" order by Product_id";
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			UWLimitList=this.mytemplate.queryForList(query,obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return UWLimitList;
	}
	public int getInsertUWLimitMaster(AdminBean bean) {
		int count=0;
		String query="";
		try{
			query=getQuery(DBConstants.INSERT_UW_LIMIT);
			Object [] obj=new Object[8];
			obj[0]=bean.getProductId();
			obj[1]=bean.getDepartmentId();
			obj[2]=bean.getUnderwritter();
			obj[3]=bean.getBranchCode();
			obj[4]=bean.getUnderwritter();
			obj[5]=bean.getUwlimit();
			obj[6]=bean.getBranchCode();
			obj[7]=bean.getStatus();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query, obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	public void getEditUWLimitMaster(AdminBean bean) {
		String query="";
		try{
			query=getQuery(DBConstants.GET_UW_LIMIT);
			Object []abj=new Object[3];
			abj[0]=bean.getProductId();
			abj[1]=bean.getDepartmentId();
			abj[2]=bean.getUnderwritter();
			List UWlist=this.mytemplate .queryForList(query, abj);
			if(UWlist!=null && UWlist.size()>0){

				Map uwlimit=(Map) UWlist.get(0);
				bean.setProductId(uwlimit.get("PRODUCT_ID")==null?"":uwlimit.get("PRODUCT_ID").toString());
				bean.setDepartmentId(uwlimit.get("DEPARTMENTID")==null?"":uwlimit.get("DEPARTMENTID").toString());
				bean.setUnderwritter(uwlimit.get("UNDERWRITERID")==null?"":uwlimit.get("UNDERWRITERID").toString());
				bean.setUwlimit(uwlimit.get("UNDERWRITTER_LIMIT")==null?"":uwlimit.get("UNDERWRITTER_LIMIT").toString());
				bean.setStatus(uwlimit.get("STATUS")==null?"":uwlimit.get("STATUS").toString());
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public int getUpdateUWLimitMaster(AdminBean bean) {
		int count=0;
		String query="";
		try{
			query=getQuery(DBConstants.UPDATE_UW_LIMIT);
			Object [] obj=new Object[6];
			obj[0]=bean.getUwlimit();
			obj[1]=bean.getStatus();
			obj[2]=bean.getProductId();
			obj[3]=bean.getDepartmentId();
			obj[4]=bean.getUnderwritter();
			obj[5]=bean.getBranchCode();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query, obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	public int getUWLimitcombination(AdminBean bean) {
		int count=0;
		String query="";
		try{
			query=getQuery(DBConstants.UWLIMIT_COMBINATION);
			Object[] abj=new Object[4];
			abj[0]=bean.getProductId();
			abj[1]=bean.getDepartmentId();
			abj[2]=bean.getUnderwritter();
			abj[3]=bean.getBranchCode();

			count=this.mytemplate.queryForInt(query,abj);

		}catch(Exception e){
			e.printStackTrace();
		}

		return count;
	}
	public List<Map<String, Object>> getDropDownTerritoryList(AdminBean bean,String branchCode) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_TERIRORY_DROP_DOWN");
			Object args[] = new Object[2];
			args[0] = "Y";
			args[1] = branchCode;
			result=this.mytemplate.queryForList(query,args);

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String, Object>> getCrestaMasterList(AdminBean bean,String branchCode) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_CRESTA_LIST");
			Object args[] = new Object[1];
			args[0] = branchCode;
			result=this.mytemplate.queryForList(query,args);

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void EditCresta(AdminBean bean, String branchCode) {
		try{
			Object obj[] = new Object[2];
			obj[1]=bean.getCrestaSNo();
			obj[0]=branchCode;
			String query=getQuery("GET_CRESTA_EDIT");
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setTerritoryId(map.get("TERITORY_CODE")==null?"":map.get("TERITORY_CODE").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setCrestaId(map.get("CRESTA_ID")==null?"":map.get("CRESTA_ID").toString());
				bean.setCrestaName(map.get("CRESTA_NAME")==null?"":map.get("CRESTA_NAME").toString());
			}
			LOGGER.info("EditCresta || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	public void CrestaInsertUpdate(AdminBean bean, String branchCode) {
		String query="";
		Object args[] =null;
		String status="success";
		try{
			if("insert".equalsIgnoreCase(bean.getMode())){
				query=getQuery("CRESTA_MAX_ID");
				args= new Object[1];
				args[0]=branchCode;
				String id=(String) this.mytemplate.queryForObject(query,args,String.class);
				query =getQuery("CRESTA_INSERT");
				args= new Object[8];
				args[0]= bean.getTerritoryId();
				args[1]=bean.getCrestaId();
				args[2]=bean.getCrestaName();
				args[3] = branchCode;
				args[4]= bean.getActive();
				args[5]= bean.getLoginId();
				args[6]= bean.getRemarks();
				args[7]=id;
				int cout=this.mytemplate.update(query,args);
			}
			else if("update".equalsIgnoreCase(bean.getMode())){
				query =getQuery("CRESTA_UPDATE");
				args= new Object[8];
				args[0]= bean.getTerritoryId();
				args[1]=bean.getCrestaId();
				args[2]=bean.getCrestaName();
				args[3] = branchCode;
				args[4]= bean.getActive();
				args[5]= bean.getLoginId();
				args[6]= bean.getRemarks();
				args[7]=bean.getCrestaSNo();
				int cout=this.mytemplate.update(query,args);
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
			status="Error";
		}
		bean.setCrestaStatus(status);
	}
	public List<Map<String, Object>> getRateMasterList(AdminBean bean,String branchCode) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_RATE_LIST");
			Object args[] = new Object[1];
			args[0] = branchCode;
			result=this.mytemplate.queryForList(query,args);

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String, Object>> getDropDownTypeList(AdminBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_DROP_DOWN_TYPE_LIST");
			Object args[] = new Object[2];
			args[0] = "Y";
			args[1] = "26";
			result=this.mytemplate.queryForList(query,args);

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void RateInsertUpdate(AdminBean bean, String branchCode) {
		String query="";
		Object args[] =null;
		String status="success";
		String id ="";
		String amendId="";
		try{
			query =getQuery("RATE_INSERT_MAX_AMEND_ID");
			args= new Object[1];
			args[0]=branchCode;
			amendId=(String) this.mytemplate.queryForObject(query,args,String.class);
			query =getQuery("RATE_INSERT");
			args= new Object[14];
			for(int i=0;i<bean.getSectionNo().size();i++){
				if(StringUtils.isNotBlank(bean.getSectionNo().get(i)) &&StringUtils.isNotBlank(bean.getAnualThresholdLimit().get(i)) && StringUtils.isNotBlank(bean.getTypeId().get(i)) &&StringUtils.isNotBlank(bean.getResidentalStatus().get(i)) && StringUtils.isNotBlank(bean.getRateOfTds().get(i)) &&StringUtils.isNotBlank(bean.getCessOne().get(i))&&StringUtils.isNotBlank(bean.getCessTwo().get(i)) && StringUtils.isNotBlank(bean.getCessThree().get(i))){
					args[0]=bean.getRateSno().get(i);
					args[1]=bean.getSectionNo().get(i).replace(",", "");
					args[2]=bean.getAnualThresholdLimit().get(i).replace(",", "");
					args[3] = bean.getTypeId().get(i);
					args[4]= bean.getResidentalStatus().get(i);
					args[5]= bean.getRateOfTds().get(i).replace(",", "");
					args[6]= bean.getCessOne().get(i).replace(",", "");
					args[7]=bean.getCessTwo().get(i).replace(",", "");
					args[8]= bean.getCessThree().get(i).replace(",", "");
					args[9]= bean.getTotalTds().get(i).replace(",", "");
					args[10]= bean.getEffectiveDate();
					args[11]=amendId;
					args[12]= bean.getLoginId();
					args[13]=branchCode;

					int cout=this.mytemplate.update(query,args);
				}
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
			status="Error";
		}
		bean.setCrestaStatus(status);
	}
	public void RateMasterDelete(AdminBean bean, String branchCode) {
		String status="success";
		try{
			String query=getQuery("DELETE_RATE_MASTER");
			Object args[] = new Object[2];
			args[0] = branchCode;
			args[1] = bean.getRateSno();
			this.mytemplate.update(query,args);

		}catch(Exception e){
			e.printStackTrace();
			status="error";
		}
		bean.setCrestaStatus(status);
	}
	public String dateComparition(AdminBean bean, String branchCode) {
		String status="success";
		try{
			String query=getQuery("DATE_RATE_MASTER");
			Object args[] = new Object[1];
			args[0] = branchCode;
			status =(String) this.mytemplate.queryForObject(query,args,String.class);

		}catch(Exception e){
			e.printStackTrace();
			status="error";
		}
		bean.setCrestaStatus(status);
		return status;
	}
	public List<Map<String, Object>> getEditAddRateMaster(AdminBean bean,
														  String branchCode) {
		List<String> rateSno=new ArrayList<String>();
		List<String> sectionNo=new ArrayList<String>();
		List<String> typeId=new ArrayList<String>();
		List<String> residentalStatus=new ArrayList<String>();
		List<String> rateOfTds=new ArrayList<String>();
		List<String> cessOne=new ArrayList<String>();
		List<String> cessTwo=new ArrayList<String>();
		List<String> cessThree=new ArrayList<String>();
		List<String> totalTds=new ArrayList<String>();
		String query="";
		Object obj[] =null;
		List<String> anualThresholdLimit=new ArrayList<String>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try{

			if( "view".equalsIgnoreCase(bean.getMode())){
				obj = new Object[2];
				obj[0]=bean.getTdsSno();
				obj[1]=branchCode;
				query=getQuery("GET_RATE_VIEW");
			}
			else{
				obj = new Object[1];
				obj[0]=branchCode;
				query=getQuery("GET_RATE_EDIT");
			}
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				for(int i=0;i<list.size();i++){
					Map map=(Map)list.get(i);
					rateSno.add(map.get("SNO")==null?"":map.get("SNO").toString());
					sectionNo.add(map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString());
					anualThresholdLimit.add(map.get("ANNUAL_THRESHOLD_LIMIT")==null?"":map.get("ANNUAL_THRESHOLD_LIMIT").toString());
					typeId.add(map.get("TYPE")==null?"":map.get("TYPE").toString());
					residentalStatus.add(map.get("RESIDENTIAL_STATUS")==null?"":map.get("RESIDENTIAL_STATUS").toString());
					rateOfTds.add(map.get("TDS_RATE")==null?"":map.get("TDS_RATE").toString());
					cessOne.add(map.get("CESS1")==null?"":map.get("CESS1").toString());
					cessTwo.add(map.get("CESS2")==null?"":map.get("CESS2").toString());
					cessThree.add(map.get("CESS3")==null?"":map.get("CESS3").toString());
					totalTds.add(map.get("TOTAL_TDS_RATE")==null?"":map.get("TOTAL_TDS_RATE").toString());
					bean.setEffectiveDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
					bean.setTdsSno(map.get("AMEND_ID")==null?"":map.get("AMEND_ID").toString());
					bean.setEntrydate(map.get("SYS_DATE")==null?"":map.get("SYS_DATE").toString());
				}
			}
			bean.setRateSno(rateSno);
			bean.setSectionNo(sectionNo);
			bean.setAnualThresholdLimit(anualThresholdLimit);
			bean.setTypeId(typeId);
			bean.setResidentalStatus(residentalStatus);
			bean.setRateOfTds(rateOfTds);
			bean.setCessOne(cessOne);
			bean.setCessTwo(cessTwo);
			bean.setCessThree(cessThree);
			bean.setTotalTds(totalTds);
			LOGGER.info("EditCresta || Exit");
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getDropDownResidentalList(AdminBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_DROP_DOWN_TYPE_LIST");
			Object args[] = new Object[2];
			args[0] = "Y";
			args[1] = "27";
			result=this.mytemplate.queryForList(query,args);

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String, Object>> getbankCurrencyList(AdminBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_BANK_CURRENCY_LIST");
			Object args[]= new Object[2];
			args[0]="Y";
			args[1]="06";
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	/*public List<Map<String, Object>> gettdsTypeList(AdminBean bean) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_BANK_TDSTYPE_LIST");
			Object args[]= new Object[2];
			args[0]="36";
			args[1]="Y";
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String, Object>> getclientList(AdminBean bean) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_CLIENT_TYPE_LIST");
			Object args[]=new Object[2];
			args[0]="38";
			args[1]="Y";
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}*/
	public List<Map<String, Object>> gettdsTypeList(AdminBean bean,
													String string) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_CLIENT_TYPE_LIST");
			Object args[]=new Object[2];
			args[0]=string;
			args[1]="Y";
			result=this.mytemplate.queryForList(query,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}
	public List<Map<String, Object>> getclientPersonalList(AdminBean bean,String branchCode) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_CLIENT_DETAILS");
			Object args[]=new Object[1];
			args[0]=branchCode;
			result=this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void getinsertClientMaster(AdminBean bean,String branchCode) {
		String query="";
		String result;
		Object args[] =null;
		try{
			String qry ="select max(customer_id)+1 from personal_info";
			String maxId = this.mytemplate.queryForObject(qry,String.class);
			query =getQuery("INSERT_CLIENT_DETAILS");
			args= new Object[38];
			args[0]=bean.getClientType();
			args[1]=bean.getFirstName();
			args[2]=bean.getInceptionDate();
			args[3]=bean.getAddress1();
			args[4]=bean.getAddress2();
			args[5]=bean.getCity();
			args[6]=bean.getCountry();
			args[7]=bean.getZipcode();
			args[8]=bean.getEmail();
			args[9]=bean.getTelephone();
			args[10]=bean.getMobile();
			args[11]=bean.getFaxNo();
			args[12]=bean.getPanNo();
			args[13]=bean.getIsIndividual();
			args[14]=bean.getIsNonResident();
			args[15]=bean.getSpecialRate();
			args[16]=bean.getActive();
			args[17]=bean.getClientRemarks();
			args[18]="";
			args[19]="";
			args[20]="";
			args[21]="";
			args[22]="";
			args[23]="";
			args[24]="";
			args[25]="";
			args[26]=StringUtils.isBlank(bean.getBroGroup())?"":bean.getBroGroup();
			args[27]="0";
			args[28]=maxId;
			args[29]=branchCode;
			args[30]=bean.getLoginId();
			args[31]=StringUtils.isBlank(bean.getEstablishmentYear())?"":bean.getEstablishmentYear();
			args[32]=StringUtils.isBlank(bean.getRegNo())?"":bean.getRegNo();
			args[33]=StringUtils.isBlank(bean.getCinNo())?"":bean.getCinNo();
			args[34]=StringUtils.isBlank(bean.getRating())?"":bean.getRating();
			args[35]=StringUtils.isBlank(bean.getRatingAgency())?"":bean.getRatingAgency();
			args[36]=StringUtils.isBlank(bean.getLastRating())?"":bean.getLastRating();
			args[37]=bean.getFirstName();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			bean.setBranchCode(branchCode);
			InsertPersonalContact(bean,"0");
			InsertPersonalBank(bean,"0");
			
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
	}
	private void InsertPersonalBank(AdminBean bean, String amendid) {
		try {
			Object args[] =null;
			if(bean.getBankSNo()!=null) {
				String query=getQuery("DELETE_PERSONAL_BANK_INFO");
				this.mytemplate.update(query,new Object[] {bean.getCustomerId(),amendid});
				query=getQuery("INSERT_PERSONAL_BANK_INFO");
				for(int i=0;i<bean.getBankSNo().size();i++) {
					if(StringUtils.isNotBlank(bean.getBankCurrency().get(i))) {
					args= new Object[11];
					args[0]=bean.getBankSNo().get(i);
					args[1]=bean.getBankCurrency().get(i);
					args[2]=bean.getBankaccountnumber().get(i);
					args[3]=bean.getBankname().get(i);
					args[4]=bean.getAccountname().get(i);
					args[5]=bean.getSwiftcode().get(i);
					args[6]=bean.getCorespondentbank().get(i);
					args[7]=bean.getBankRemarks().get(i);
					args[8]=bean.getCustomerId();
					args[9]=bean.getBranchCode();
					args[10]=amendid;
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(args,","));
					this.mytemplate.update(query,args);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void InsertPersonalContact(AdminBean bean, String amendid) {
		try {
			Object args[] =null;
			if(bean.getContactSNo()!=null) {
				String query=getQuery("DELETE_PERSONAL_CONTACT_INFO");
				this.mytemplate.update(query,new Object[] {bean.getCustomerId(),amendid});
				query=getQuery("INSERT_PERSONAL_CONTACT_INFO");
				for(int i=0;i<bean.getContactSNo().size();i++) {
					if(StringUtils.isNotBlank(bean.getDepartmentCD().get(i))) {
					args= new Object[9];
					args[0]=bean.getContactSNo().get(i);
					args[1]=bean.getDepartmentCD().get(i);
					args[2]=bean.getSubdepartmentCD().get(i);
					args[3]=bean.getEmailaddress().get(i);
					args[4]=bean.getTelephonenumber().get(i);
					args[5]=bean.getFaxnumber().get(i);
					args[6]=bean.getCustomerId();
					args[7]=bean.getBranchCode();
					args[8]=amendid;
					LOGGER.info("query =>" + query);
					LOGGER.info("Arg[]=>"+StringUtils.join(args,","));
					this.mytemplate.update(query,args);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getEditClientMaster(AdminBean bean,String branchCode) {

		String query="";
		try{
			Object obj[] = new Object[2];
			obj[0]=bean.getCustomerId();
			obj[1]=branchCode;

			query=getQuery("EDIT_CLIENT_DETAILS");
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				bean.setClientType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
				bean.setFirstName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				bean.setBroGroup(map.get("BROKER_GROUP")==null?"":map.get("BROKER_GROUP").toString());
				bean.setDesignation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
				bean.setInceptionDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				bean.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
				bean.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
				bean.setCity(map.get("CITY")==null?"":map.get("CITY").toString());
				bean.setCountry(map.get("COUNTRY")==null?"":map.get("COUNTRY").toString());
				bean.setZipcode(map.get("POBOX")==null?"":map.get("POBOX").toString());
				bean.setTelephone(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
				bean.setMobile(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				bean.setFaxNo(map.get("FAX")==null?"":map.get("FAX").toString());
				bean.setPanNo(map.get("PAN_NUMBER")==null?"":map.get("PAN_NUMBER").toString());
				bean.setIsIndividual(map.get("INDIVIDUALYN")==null?"":map.get("INDIVIDUALYN").toString());
				bean.setIsNonResident(map.get("NON_RESIDENTYN")==null?"":map.get("NON_RESIDENTYN").toString());
				bean.setSpecialRate(map.get("SPECIAL_RATE")==null?"":map.get("SPECIAL_RATE").toString());
				bean.setActive(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setClientRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setSnumber(map.get("CON_SERIAL_NO")==null?"":map.get("CON_SERIAL_NO").toString());
				bean.setBroGroup(map.get("BROKER_GROUP")==null?"":map.get("BROKER_GROUP").toString());
				bean.setEstablishmentYear(map.get("ESTB_YEAR")==null?"":map.get("ESTB_YEAR").toString());
				bean.setRegNo(map.get("REG_NO")==null?"":map.get("REG_NO").toString());
				bean.setCinNo(map.get("CIN_NO")==null?"":map.get("CIN_NO").toString());
				bean.setRating(map.get("RATING")==null?"":map.get("RATING").toString());
				bean.setRatingAgency(map.get("RATING_AGENCY")==null?"":map.get("RATING_AGENCY").toString());
				bean.setLastRating(map.get("LAST_RATING")==null?"":map.get("LAST_RATING").toString());
				
			}
			GetContactInfo(bean,branchCode);
			GetBankInfo(bean,branchCode);
			
		}catch(Exception exception){
			exception.printStackTrace();
		}

	}
	private void GetBankInfo(AdminBean bean, String branchCode) {
		try {
			String query=getQuery("GET_PERSONAL_BANK_INFO");
			Object obj[] = new Object[2];
			obj[0]=bean.getCustomerId();
			obj[1]=branchCode;
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0) {
				List<String> bankCurrency=new ArrayList<String>();
				List<String> bankaccountnumber=new ArrayList<String>();
				List<String> bankname=new ArrayList<String>();
				List<String> accountname=new ArrayList<String>();
				List<String> swiftcode=new ArrayList<String>();
				List<String> corespondentbank=new ArrayList<String>();
				List<String> bankRemarks=new ArrayList<String>();
				List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<list.size();i++) {
					Map<String,Object>map=(Map<String, Object>) list.get(i);
					bankCurrency.add(map.get("BANK_CURRENCY")==null?"":map.get("BANK_CURRENCY").toString());
					bankaccountnumber.add(map.get("BANK_ACC_NO")==null?"":map.get("BANK_ACC_NO").toString());
					bankname.add(map.get("BANK_NAME")==null?"":map.get("BANK_NAME").toString());
					accountname.add(map.get("BANK_ACC_NAME")==null?"":map.get("BANK_ACC_NAME").toString());
					swiftcode.add(map.get("SWIFT_CODE")==null?"":map.get("SWIFT_CODE").toString());
					corespondentbank.add(map.get("CORESPONDENT_BANK")==null?"":map.get("CORESPONDENT_BANK").toString());
					bankRemarks.add(map.get("BANK_REMARKS")==null?"":map.get("BANK_REMARKS").toString());
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					creList.add(doubleMap);
					currencyList1.add(creList);
				}
				bean.setBankCurrency(bankCurrency);
				bean.setBankaccountnumber(bankaccountnumber);
				bean.setBankname(bankname);
				bean.setAccountname(accountname);
				bean.setSwiftcode(swiftcode);
				bean.setCorespondentbank(corespondentbank);
				bean.setBankRemarks(bankRemarks);
				bean.setCurrencyList(currencyList1);
				bean.setContactList(currencyList1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void GetContactInfo(AdminBean bean,String branchCode) {
		try {
			String query=getQuery("GET_PERSONAL_CONTACT_INFO");
			Object obj[] = new Object[2];
			obj[0]=bean.getCustomerId();
			obj[1]=branchCode;
			List list=this.mytemplate.queryForList(query,obj);
			if(list!=null && list.size()>0) {
				List<String> departmentCD=new ArrayList<String>();
				List<String> subdepartmentCD=new ArrayList<String>();
				List<String> emailaddress=new ArrayList<String>();
				List<String> telephonenumber=new ArrayList<String>();
				List<String> faxnumber=new ArrayList<String>();
				List<List<Map<String,Object>>> currencyList1=new ArrayList<List<Map<String,Object>>>();
				for(int i=0;i<list.size();i++) {
					Map<String,Object>map=(Map<String, Object>) list.get(i);
					departmentCD.add(map.get("DEPARTMENT")==null?"":map.get("DEPARTMENT").toString());
					subdepartmentCD.add(map.get("SUB_DEPARTMENT")==null?"":map.get("SUB_DEPARTMENT").toString());
					emailaddress.add(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
					telephonenumber.add(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
					faxnumber.add(map.get("FAX_NUMBER")==null?"":map.get("FAX_NUMBER").toString());
					Map<String,Object> doubleMap = new HashMap<String,Object>();
					List<Map<String,Object>> creList=new ArrayList<Map<String,Object>>();
					doubleMap.put("one",new Double(1.0));
					creList.add(doubleMap);
					currencyList1.add(creList);
				}
				bean.setDepartmentCD(departmentCD);
				bean.setSubdepartmentCD(subdepartmentCD);
				bean.setEmailaddress(emailaddress);
				bean.setTelephonenumber(telephonenumber);
				bean.setFaxnumber(faxnumber);
				bean.setContactList(currencyList1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void getupdateClientMaster(AdminBean bean,String branchCode) {
		String query="";
		String result;
		Object args[] =null;
		try{
			query = "Update PERSONAL_INFO set EXPIRY_DATE=sysdate where customer_id=? and BRANCH_CODE=?";
			args= new Object[2];
			args[0]=bean.getCustomerId();
			args[1] = branchCode;
			this.mytemplate.update(query,args);
			query ="select max(AMEND_ID)+1 from personal_info where customer_id=?";
			args= new Object[1];
			args[0]=bean.getCustomerId();
			String maxId = this.mytemplate.queryForObject(query,args,String.class);
			query =getQuery("INSERT_CLIENT_DETAILS");
			args= new Object[38];
			args[0]=bean.getClientType();
			args[1]=bean.getFirstName();
			args[2]=bean.getInceptionDate();
			args[3]=bean.getAddress1();
			args[4]=bean.getAddress2();
			args[5]=bean.getCity();
			args[6]=bean.getCountry();
			args[7]=bean.getZipcode();
			args[8]=bean.getEmail();
			args[9]=bean.getTelephone();
			args[10]=bean.getMobile();
			args[11]=bean.getFaxNo();
			args[12]=bean.getPanNo();
			args[13]=bean.getIsIndividual();
			args[14]=bean.getIsNonResident();
			args[15]=bean.getSpecialRate();
			args[16]=bean.getActive();
			args[17]=bean.getClientRemarks();
			args[18]="";
			args[19]="";
			args[20]="";
			args[21]="";
			args[22]="";
			args[23]="";
			args[24]="";
			args[25]="";
			args[26]=StringUtils.isBlank(bean.getBroGroup())?"":bean.getBroGroup();
			args[27]=maxId;
			args[28]=bean.getCustomerId();
			args[29]=branchCode;
			args[30]=bean.getLoginId();
			args[31]=StringUtils.isBlank(bean.getEstablishmentYear())?"":bean.getEstablishmentYear();
			args[32]=StringUtils.isBlank(bean.getRegNo())?"":bean.getRegNo();
			args[33]=StringUtils.isBlank(bean.getCinNo())?"":bean.getCinNo();
			args[34]=StringUtils.isBlank(bean.getRating())?"":bean.getRating();
			args[35]=StringUtils.isBlank(bean.getRatingAgency())?"":bean.getRatingAgency();
			args[36]=StringUtils.isBlank(bean.getLastRating())?"":bean.getLastRating();
			args[37]=bean.getFirstName();
			LOGGER.info("query =>" + query);
			LOGGER.info("Arg[]=>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			bean.setBranchCode(branchCode);
			InsertPersonalContact(bean,maxId);
			InsertPersonalBank(bean,maxId);
		}

		catch(Exception exception){
			exception.printStackTrace(); 
		}

	}
	public List<Map<String, Object>> getbroGroupList(AdminBean bean) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_BROKERGROUP_DETAILS");
			result=this.mytemplate.queryForList(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String doUploadDocDetails(List<Object[]> list) {
		String result="";
		try{
			String sql=getQuery(DBConstants.UPLOAD_INSERTDOCDETAILS);
			LOGGER.info("Sql=>"+sql);

			for(Object[] obj:list)
			{
				LOGGER.info("Obj[]=>"+StringUtils.join(obj,","));
				int res=this.mytemplate.update(sql,obj);
				LOGGER.info("Result=>"+res);
			}

		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
			result = e.getMessage();
		}
		return result;
	}
	public List<Map<String, Object>> getDocList(AdminBean bean,String branchCode) {
		List<Map<String,Object>> list = null;
		try{
			Object[] obj=new Object[3];
			String sql=getQuery("GET_DOC_LIST");
			obj[0]=branchCode;
			obj[1]="Y";
			obj[2] =bean.getCustomerId();
			LOGGER.info("SQL=>"+sql);
			LOGGER.info("OBj=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(sql,obj);
		}
		catch(Exception exception) {
			LOGGER.debug("Exception @ { " + exception + " } ");
		}
		return list;
	}
	public String doDeleteDocDetails(AdminBean bean, String userId,String branchCode) {
		String result="";
		try{
			Object[] obj=new Object[6];
			String sql=this.getQuery("DELETE_DOC_LIST");
			obj[0]=userId;
			obj[1]=branchCode;
			obj[2]="PER";
			obj[3]=bean.getCustomerId();
			obj[4]=bean.getDocumentId();
			obj[5]=bean.getOurFileName();
			LOGGER.info("Sql=>"+sql);
			LOGGER.info("Obj[]=>"+StringUtils.join(obj,","));
			int res=this.mytemplate.update(sql,obj);
			LOGGER.info("Result=>"+res);
		}catch(Exception e){
			LOGGER.debug("Exception @ { " + e + " } ");
		}
		return result;
	}



	public boolean insertMainMenu(AdminBean bean, Map<String, String> map) {
		boolean result = true;
		if (removeMainMenuAllocationForUser(bean)) {
			for (Object object : map.keySet()) {
				try {
					Object[] obj = new Object[8];
					String str = (String) object;
					String arrayValue[] = str.split(",");
					String sql = getQuery(DBConstants.USER_INSERT_INSERTMAINMENU);
					obj[0] = bean.getLoginId();
					obj[1] = arrayValue[1];
					obj[2] = map.get(object);
					obj[3] = "";
					obj[4] = "1";
					obj[5] = arrayValue[0];
					obj[6] = arrayValue[1];
					obj[7] = arrayValue[0];
					LOGGER.info("Sql=>" + sql);
					LOGGER.info("Obj[]=>" + StringUtils.join(obj, ","));
					int res = this.mytemplate.update(sql, obj);
					LOGGER.info("Result=>" + res);
					if (res > 0) {
						result = true;
					} else {
						result = false;
					}
				} catch (Exception e) {
					LOGGER.debug("Exception @ { " + e + " } ");
					result = false;
				}
			}
		} else {
			result = false;
		}
		return result;
	}


	public boolean insertSubMenu(AdminBean bean, Map map) {
		boolean result = true;
		if (removeSubMenuAllocationForUser(bean)) {
			for (Object object : map.keySet()) {
				try {
					Object[] obj = new Object[6];
					String str = (String) object;
					String arrayValue[] = str.split(",");
					String sql = getQuery(DBConstants.USER_INSERT_INSERTSUBMENU);
					obj[0] = arrayValue[1];
					obj[1] = bean.getLoginId();
					obj[2] = arrayValue[2];
					obj[3] = map.get(object);
					obj[4] = "1";
					obj[5] = "";
					LOGGER.info("Sql=>" + sql);
					LOGGER.info("Obj[]=>" + StringUtils.join(obj, ","));
					int res = this.mytemplate.update(sql, obj);
					LOGGER.info("Result=>" + res);
					if (res > 0) {
						result = true;
					} else {
						result = false;
					}
				} catch (Exception e) {
					LOGGER.debug("Exception @ { " + e + " } ");
					result = false;
				}
			}
		} else {
			result = false;
		}
		return result;
	}


	public boolean removeMainMenuAllocationForUser(AdminBean bean) {
		boolean result = true;
		try {
			Object[] objDelete = new Object[1];
			String sqlDelete = getQuery(DBConstants.USER_DELETE_DELETEMAINMENU);
			objDelete[0] = bean.getLoginId();
			int deleteResult = this.mytemplate.update(sqlDelete, objDelete);
		} catch (Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
			result = false;
		}
		return result;
	}


	public boolean removeSubMenuAllocationForUser(AdminBean bean) {
		boolean result = true;
		try {
			Object[] objDelete = new Object[1];
			String sqlDelete = getQuery(DBConstants.USER_DELETE_DELETESUBMENU);
			objDelete[0] = bean.getLoginId();
			int deleteResult = this.mytemplate.update(sqlDelete, objDelete);
		} catch (Exception e) {
			LOGGER.debug("Exception @ { " + e + " } ");
			result = false;
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> getAttachUnderWriterList(String branchCode) {
		List<Map<String,Object>> list = null;
		try{
			Object[] obj=new Object[1];
			String sql=getQuery("GET_ATTACHED_UW_LIST");
			obj[0]=branchCode;
			LOGGER.info("SQL=>"+sql);
			LOGGER.info("OBj=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(sql,obj);
		}
		catch(Exception exception) {
			LOGGER.debug("Exception @ { " + exception + " } ");
		}
		return list;
	}
	@Override
	public int getLoginCount(AdminBean bean,String branchCode) {
		Object[] obj=new Object[2];
		String sql=getQuery("GET_LOGIN_COUNT");
		obj[0]=bean.getLoginId();
		obj[1]=branchCode;
		LOGGER.info("SQL=>"+sql);
		LOGGER.info("OBj=>"+StringUtils.join(obj,","));
		int count=this.mytemplate.queryForInt(sql,obj);
		return count;
	}

}

	


