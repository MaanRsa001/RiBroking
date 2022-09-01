package com.maan.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.maan.bean.ReportsBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.DatabaseDAO;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.ReportsDAO;

public class ReportsDAOImpl extends MyJdbcTemplate implements ReportsDAO{
	Logger logger = LogUtil.getLogger(this.getClass());

	public List<Map<String,Object>> getMoveMentInit(ReportsBean bean){

		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[2];
			String query="";
			args[0]=bean.getBranchCode();
			args[1]=bean.getBranchCode();
			query=getQuery(DBConstants.REPORT_SELECT_MOVEMENT_INIT);
			logger.info("Query=>"+query);
			logger.info("args[0][1]=>"+bean.getBranchCode());
			list = this.mytemplate.queryForList(query,args);
			/*if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setSNo(tempMap.get("MOVMENT_TRANID")==null?"":tempMap.get("MOVMENT_TRANID").toString());
				bean.setAccount_Period(tempMap.get("DETAIL_NAME")==null?"":tempMap.get("DETAIL_NAME").toString());
				bean.setAccper(tempMap.get("ACCOUNT_PERIOD_QTR")==null?"":tempMap.get("ACCOUNT_PERIOD_QTR").toString());
				bean.setAccountDate(tempMap.get("ACCOUNT_PERIOD")==null?"":tempMap.get("ACCOUNT_PERIOD").toString());
				bean.setChangedYN(tempMap.get("CHANGEDYN")==null?"":tempMap.get("CHANGEDYN").toString());
				bean.setMovementType(tempMap.get("REPORT_TYPE")==null?"":tempMap.get("REPORT_TYPE").toString().equalsIgnoreCase("F")?"Final":"Intreim");

			}*/
			/*list=this.mytemplate.query(query,args,new RowMapper() {
				public Object mapRow(ResultSet move, int rowNum) throws SQLException {
					ReportsBean repBean=new ReportsBean();	
					repBean.setSNo(move.getString("MOVMENT_TRANID"));
					repBean.setAccount_Period(move.getString("DETAIL_NAME"));
					repBean.setAccper(move.getString("ACCOUNT_PERIOD_QTR"));
					repBean.setAccountDate(move.getString("ACCOUNT_PERIOD"));
					repBean.setChangedYN(move.getString("CHANGEDYN"));
					repBean.setMovementType(move.getString("REPORT_TYPE").equalsIgnoreCase("F")?"Final":"Intreim");
					return repBean;
				}
			});*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}

		return list;
	}

	public List<Map<String,Object>> getClaimJournelInit(ReportsBean bean) {

		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[1];
			String query="";
			args[0]=bean.getBranchCode();
			query=getQuery(DBConstants.REPORT_SELECT_CLJOURNEL_INIT);
			logger.info("Query=>"+query);
			logger.info("args[0][1]=>"+bean.getBranchCode());
			list = this.mytemplate.queryForList(query,args);
			/*if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setSNo(tempMap.get("MOVMENT_TRANID")==null?"":tempMap.get("MOVMENT_TRANID").toString());
				bean.setAccount_Period(tempMap.get("DETAIL_NAME")==null?"":tempMap.get("DETAIL_NAME").toString());
				bean.setAccper(tempMap.get("ACCOUNT_PERIOD_QTR")==null?"":tempMap.get("ACCOUNT_PERIOD_QTR").toString());
				bean.setAccountDate(tempMap.get("ACCOUNT_PERIOD")==null?"":tempMap.get("ACCOUNT_PERIOD").toString());
				bean.setChangedYN(tempMap.get("CHANGEDYN")==null?"":tempMap.get("CHANGEDYN").toString());
				bean.setMovementType(tempMap.get("REPORT_TYPE")==null?"Interim":tempMap.get("REPORT_TYPE").toString().equalsIgnoreCase("F")?"Final":"Intreim");

			}*/
			/*list=this.mytemplate.query(query,args,new RowMapper() {
				public Object mapRow(ResultSet move, int rowNum) throws SQLException {
					ReportsBean repBean=new ReportsBean();	
					repBean.setSNo(move.getString("MOVMENT_TRANID"));
					repBean.setAccount_Period(move.getString("DETAIL_NAME"));
					repBean.setAccper(move.getString("ACCOUNT_PERIOD_QTR"));
					repBean.setAccountDate(move.getString("ACCOUNT_PERIOD"));
					repBean.setMovementType(move.getString("REPORT_TYPE")==null?"Intreim":move.getString("REPORT_TYPE").equalsIgnoreCase("F")?"Final":"Intreim");
					return repBean;
				}
			});*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> getJournalListCrnt(ReportsBean bean) {

		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			Object[] args = null;
			if ("15".equalsIgnoreCase(bean.getTypeId())) {
				args=new Object[6];			
				args[0]=bean.getBranchCode();
				args[1]=bean.getUwYear();
				args[2]=bean.getMovementId();
				args[3]=bean.getAccountDate();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				query=getQuery("BOOKED_UPR_CRNT");
			}
			else if ("16".equalsIgnoreCase(bean.getTypeId())) {
				args=new Object[6];			
				args[0]=bean.getBranchCode();
				args[1]=bean.getUwYear();
				args[2]=bean.getMovementId();
				args[3]=bean.getAccountDate();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				query=getQuery("BOOKED_PREMIUM_CRNT");
			}				
			else if ("17".equalsIgnoreCase(bean.getTypeId())) {
				args=new Object[6];			
				args[0]=bean.getBranchCode();
				args[1]=bean.getUwYear();
				args[2]=bean.getMovementId();
				args[3]=bean.getAccountDate();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				query=getQuery("BOOKED_UPR_CRNT");
			}				
			else if ("18".equalsIgnoreCase(bean.getTypeId())) {
				args=new Object[6];			
				args[0]=bean.getBranchCode();				
				args[1]=bean.getUwYear();
				args[2]=bean.getMovementId();
				args[3]=bean.getAccountDate();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				query=getQuery("PIPELINE_WRITTEN_CRNT");
			}
			logger.info("Query=>"+query);
			logger.info("args[0][1] [2]=>"+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query,args);			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String,Object>> getClaimMoveMentInit(ReportsBean bean){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[1];
			String query="";
			args[0]=bean.getBranchCode();
			query=getQuery(DBConstants.REPORT_SELECT_CLMOVEMENT_INIT);
			logger.info("Query=>"+query);
			logger.info("args[0][1]=>"+bean.getBranchCode());
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setSNo(tempMap.get("MOVEMENT_ID")==null?"":tempMap.get("MOVEMENT_ID").toString());
				bean.setAccountDate(tempMap.get("MOVEMENT_DT")==null?"":tempMap.get("MOVEMENT_DT").toString());
				bean.setMovementType(tempMap.get("REPORT_TYPE")==null?"Interim":tempMap.get("REPORT_TYPE").toString().equalsIgnoreCase("F")?"Final":"Intreim");
			}
			/*list=this.mytemplate.query(query,args,new RowMapper() {
				public Object mapRow(ResultSet move, int rowNum) throws SQLException {
					ReportsBean repBean=new ReportsBean();	
					repBean.setSNo(move.getString("MOVEMENT_ID"));
					repBean.setAccountDate(move.getString("MOVEMENT_DT"));
					repBean.setMovementType(move.getString("REPORT_TYPE").equalsIgnoreCase("F")?"Final":"Intreim");
					return repBean;
				}
			});*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();			
		}
		return list;
	}

	public List<Map<String,Object>> getProductDropDown(String branchCode,String typeId) {
		List<Map<String,Object>> productList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.REPORT_SELECT_GETREPORTPRODUCTLIST);
			logger.info("Select Query=>"+query);
			logger.info("Arg[0]=>"+typeId);
			logger.info("Arg[1]=>"+branchCode);
			productList=this.mytemplate.queryForList(query, new Object[]{typeId,branchCode});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("ReportsDAOImpl getProductDropDown() || Exit List size=>"+productList.size());
		return productList;
	}

	public String getCedingCompany(ReportsBean bean)  {
		String  cedingco="";
		String query="";
		try{
			List<Map<String,Object>> list =null;
			query = getQuery(DBConstants.REPORT_SELECT_GETCEDINGCOMPANYLIST);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+bean.getBranchCode());
			logger.info("Args[1]=>"+"C");
			logger.info("Args[2]=>"+"Y");
			//logger.info("Args[3]=>"+bean.getBrokerId());
			logger.info("Args[3]=>"+bean.getBranchCode());
			logger.info("Args[4]=>"+bean.getProductId());
			//list = this.mytemplate.queryForList(query, new Object[] {bean.getBranchCode(),"C","Y",bean.getBrokerId(),bean.getProductId()});
			list = this.mytemplate.queryForList(query, new Object[] {bean.getBranchCode(),"C","Y",bean.getBranchCode(),bean.getProductId()});
			bean.setCeddingCompanyList(list);
			if(list!=null && list.size()>0){
				logger.info("List Size=>"+list.size());
				Map<String,Object> resMap;
				for(int i=0;i<list.size();i++){
					resMap = (Map<String,Object>)list.get(i); 
					if(i==(list.size()-1)){
						cedingco+=resMap.get("CUSTOMER_ID").toString()+"~"+resMap.get("NAME").toString();
					}else{
						cedingco+=resMap.get("CUSTOMER_ID").toString()+"~"+resMap.get("NAME").toString()+"~";	
					}
				}
			}
		} 
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return cedingco;	
	}

	public String getReportName(ReportsBean bean) {
		String reportName=null;
		String query=null;
		try{
			if("19".equals(bean.getTypeId()) || "21".equals(bean.getTypeId())|| "11".equals(bean.getTypeId()) || "54".equals(bean.getTypeId())){
				query = "SELECT TYPE_NAME FROM XL_TYPE_MASTER WHERE TYPE_ID=? AND STATUS=?";
				logger.info("Select Query=>"+query);
				logger.info("Args[1]=>"+bean.getTypeId());
				logger.info("Args[2]=>"+"Y");
				reportName = (String)this.mytemplate.queryForObject(query, new String[] {bean.getTypeId(),"Y"},String.class);
			}else{
				query = getQuery(DBConstants.REPORT_SELECT_GETREPORTNAME);
				logger.info("Select Query=>"+query);
				logger.info("Args[0]=>"+bean.getProductId());
				logger.info("Args[1]=>"+bean.getTypeId());
				logger.info("Args[2]=>"+"Y");
				reportName = (String)this.mytemplate.queryForObject(query, new String[] {bean.getProductId(),bean.getTypeId(),"Y"},String.class);	
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return reportName;
	}
	public List<Map<String,Object>> getPendingOffersList(final ReportsBean bean) {
		String query="";
		Object[] obj= new Object[0];
		if("1".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLIST);
			obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACORDER);
		}else if("2".equalsIgnoreCase(bean.getProductId()) || "3".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_XOLORTREATYGETQUOTEREGISTERLIST);
			obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACORDER);
		}
		else if("4".equalsIgnoreCase(bean.getProductId()) || "5".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_DEPTNAMERETRORETROXOL);
			obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getProductId();
			obj[3]=bean.getDept();
			obj[4]=bean.getStart_date();
			obj[5]=bean.getEnd_date();
			logger.info("DeptName==>"+bean.getDept());
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACORDER);
		}else{
			query=getQuery(DBConstants.REPORTDAO_SELECT_XOLORTREATYGETQUOTEREGISTERLIST);
			obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACORDER);
		}
		logger.info("Query=>"+query);
		int j=0;
		for(Object o:obj){
			logger.info("Arg["+j+"]=>"+o);
			j++;
		}
		final List<Map<String,Object>> list=this.mytemplate.queryForList(query,obj);
		return list;
	}

	private Object[] getIncObjectArray(Object[] srcObj,Object[] descObj){
		final Object[] tempObj = new Object[srcObj.length];
		System.arraycopy(srcObj, 0, tempObj, 0, srcObj.length);
		srcObj=new Object[tempObj.length+descObj.length];
		System.arraycopy(tempObj, 0, srcObj, 0, tempObj.length);
		System.arraycopy(descObj, 0, srcObj, tempObj.length, descObj.length);
		return srcObj;
	}
	public List<Map<String,Object>> getPolicyRegisterList(final ReportsBean bean) {
		ResultSet resultSet=null;
		String query="";
		 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		 Object[] obj= new Object[0];
		 
		if("1".equalsIgnoreCase(bean.getProductId())){
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		// get refcursor and convert it to ResultSet
		
		try {
			String runSP = "{ call PRC_FAC_POL_REG(?,?,?,?,?,?,?,?,?,?) }";
			CallableStatement cs = connection.prepareCall(runSP);
			cs.setString(1, bean.getStart_date());
			cs.setString(2, bean.getEnd_date());
			cs.setString(3, bean.getBranchCode());
			cs.setString(4, bean.getLoginId());
			cs.setString(5, bean.getBrokerId());
			cs.setString(6, bean.getCedingId());
			cs.setString(7, bean.getUwYear());
			cs.setString(8, "10");
			cs.setString(9, "1");

			cs.registerOutParameter(10, OracleTypes.CURSOR);

			// run SP
			cs.execute();

			resultSet = (ResultSet) cs.getObject(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return resultSetToArrayList(resultSet);
		}else if("2".equalsIgnoreCase(bean.getProductId()) || "3".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_POLICYREGPROPOTIONALTREATY);
			obj=new Object[4];
			obj[0]=bean.getProductId();
			obj[1]=bean.getStart_date();
			obj[2]=bean.getEnd_date();
			obj[3]=bean.getBranchCode();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACORDERBY);
		}else if("4".equalsIgnoreCase(bean.getProductId())||"5".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_RETROPOLICYREPORT);
			obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getDept();
			obj[3]=bean.getStart_date();
			obj[4]=bean.getEnd_date();
			obj[5]=bean.getBranchCode();
			logger.info("Dept Name==>"+bean.getDept());
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACORDERBY);
		}
		logger.info("Query=>"+query);
		int i=0;
		for(Object ob:obj){
			logger.info("Args["+i+"]"+ob);
			i++;
		}
		
		 try {
			list=this.mytemplate.queryForList(query,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List resultSetToArrayList(ResultSet rs) {
		ArrayList list = new ArrayList();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			
			while (rs.next()){
				HashMap row = new HashMap(columns);
				for(int i=1; i<=columns; ++i){
					row.put(md.getColumnName(i),rs.getObject(i));
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	/*public List<Map<String,Object>> getPolicyRegisterList(final ReportsBean bean) {
		String query="";
		 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
		Object[] obj= new Object[0];
		if("1".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLTATIVE);
			obj=new Object[3];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACORDERBY);
			query=getQuery("GET_FAC_POLICY_REGISTER");
			
			obj= new Object[7];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getLoginId();
			obj[4] =bean.getBrokerId();
			obj[5] =bean.getCedingId();
			obj[6] = bean.getUwYear();
		}else if("2".equalsIgnoreCase(bean.getProductId()) || "3".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_POLICYREGPROPOTIONALTREATY);
			obj=new Object[4];
			obj[0]=bean.getProductId();
			obj[1]=bean.getStart_date();
			obj[2]=bean.getEnd_date();
			obj[3]=bean.getBranchCode();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACORDERBY);
		}else if("4".equalsIgnoreCase(bean.getProductId())||"5".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_RETROPOLICYREPORT);
			obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getDept();
			obj[3]=bean.getStart_date();
			obj[4]=bean.getEnd_date();
			obj[5]=bean.getBranchCode();
			logger.info("Dept Name==>"+bean.getDept());
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACORDERBY);
		}
		logger.info("Query=>"+query);
		int i=0;
		for(Object ob:obj){
			logger.info("Args["+i+"]"+ob);
			i++;
		}
		 list=this.mytemplate.queryForList(query,obj);
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		return list;
	}*/

	public List<Map<String,Object>> getPremiumRegisterList(final ReportsBean bean) {
		Object[] obj= new Object[0];
		String query="";
		if("1".equalsIgnoreCase(bean.getProductId())){
			obj= new Object[7];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getLoginId();
			obj[4] =bean.getBrokerId();
			obj[5] =bean.getCedingId();
			obj[6] = bean.getUwYear();
			query+=getQuery(DBConstants.REPORT_SELECT_PREMIUMREPORT);
		}else if("2".equalsIgnoreCase(bean.getProductId())){
			obj= new Object[7];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getLoginId();
			obj[4] =bean.getBrokerId();
			obj[5] =bean.getCedingId();
			obj[6] = bean.getUwYear();
			//query+=getQuery(DBConstants.REPORT_SELECT_TREATYPREMIUMREPORT);
			query+=getQuery("REPORT_SELECT_PTTYPREMIUMREPORT");
		}else if("3".equalsIgnoreCase(bean.getProductId())){
			obj= new Object[7];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getLoginId();
			obj[4] =bean.getBrokerId();
			obj[5] =bean.getCedingId();
			obj[6] = bean.getUwYear();
			query+=getQuery("REPORT_SELECT_NPTTYPREMIUMREPORT");
		}
		else if("4".equalsIgnoreCase(bean.getProductId()) || "5".equalsIgnoreCase(bean.getProductId())){
			obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getDept();
			obj[3]=bean.getStart_date();
			obj[4]=bean.getEnd_date();
			obj[5]=bean.getBranchCode();
			query+=getQuery(DBConstants.REPORT_SELECT_RETROPERMIUMCLAIM);
		}
		if("4".equalsIgnoreCase(bean.getProductId()) || "5".equalsIgnoreCase(bean.getProductId())){
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);	
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACUWYEAR);
			}
		}
		query+=" "+getQuery(DBConstants.REPORT_SELECT_PREMIUMREPORTORDERBY);
		logger.info("Query=>"+query);
		int i=0;
		for(Object ob:obj){
			logger.info("Args["+i+"]"+ob);
			i++;
		}
		final List<Map<String,Object>> list=this.mytemplate.queryForList(query,obj);
		return list;
	}

	public List<Map<String,Object>> getClaimRegisterList(final ReportsBean bean) {
		 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
		Object[] obj= new Object[0];
		String query="";
		/*if("1".equalsIgnoreCase(bean.getProductId())){
			obj= new Object[19];
			obj[0]=bean.getEnd_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getEnd_date();
			obj[3]=bean.getEnd_date();
			obj[4]=bean.getEnd_date();
			obj[5]=bean.getEnd_date();
			obj[6]=bean.getEnd_date();
			obj[7]=bean.getEnd_date();
			obj[8]=bean.getStart_date();
			obj[9]=bean.getEnd_date();
			obj[10]=bean.getStart_date();
			obj[11]=bean.getEnd_date();
			obj[12]=bean.getStart_date();
			obj[13]=bean.getEnd_date();
			obj[14]=bean.getProductId();
			obj[15]=bean.getStart_date();
			obj[16]=bean.getEnd_date();
			obj[17]=bean.getCountryId();
			obj[18]=bean.getBranchCode();
			query=getQuery(DBConstants.REPORT_SELECT_CLAIMREPORT);
		}else if("2".equalsIgnoreCase(bean.getProductId())){
			obj=new Object[27];
			obj[0]=bean.getEnd_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			obj[4]=bean.getStart_date();
			obj[5]=bean.getEnd_date();
			obj[6]=bean.getEnd_date();
			obj[7]=bean.getEnd_date();
			obj[8]=bean.getStart_date();
			obj[9]=bean.getEnd_date();
			obj[10]=bean.getStart_date();
			obj[11]=bean.getEnd_date();
			obj[12]=bean.getEnd_date();
			obj[13]=bean.getEnd_date();
			obj[14]=bean.getEnd_date();
			obj[15]=bean.getEnd_date();
			obj[16]=bean.getStart_date();
			obj[17]=bean.getEnd_date();
			obj[18]=bean.getStart_date();
			obj[19]=bean.getEnd_date();
			obj[20]=bean.getStart_date();
			obj[21]=bean.getEnd_date();
			obj[22]=bean.getProductId();
			obj[23]=bean.getBranchCode();
			obj[24]=bean.getStart_date();
			obj[25]=bean.getEnd_date();
			obj[26]=bean.getCountryId();
			query=getQuery(DBConstants.REPORT_SELECT_CLAIMREPORTPRMXOL);
		}
		else if("3".equalsIgnoreCase(bean.getProductId())){
			obj=new Object[27];
			obj[0]=bean.getEnd_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			obj[4]=bean.getStart_date();
			obj[5]=bean.getEnd_date();
			obj[6]=bean.getEnd_date();
			obj[7]=bean.getEnd_date();
			obj[8]=bean.getStart_date();
			obj[9]=bean.getEnd_date();
			obj[10]=bean.getStart_date();
			obj[11]=bean.getEnd_date();
			obj[12]=bean.getEnd_date();
			obj[13]=bean.getEnd_date();
			obj[14]=bean.getEnd_date();
			obj[15]=bean.getEnd_date();
			obj[16]=bean.getStart_date();
			obj[17]=bean.getEnd_date();
			obj[18]=bean.getStart_date();
			obj[19]=bean.getEnd_date();
			obj[20]=bean.getStart_date();
			obj[21]=bean.getEnd_date();
			obj[22]=bean.getProductId();
			obj[23]=bean.getBranchCode();
			obj[24]=bean.getStart_date();
			obj[25]=bean.getEnd_date();
			obj[26]=bean.getCountryId();
			query=getQuery(DBConstants.REPORT_SELECT_CLAIMREPORTPRMXOL1);
		}*/
		query  = getQuery("GET_CLAIM_REG_REPORT");
		obj= new Object[7];
		obj[0] = bean.getProductId();
		obj[1]=bean.getEnd_date();
		obj[2]=bean.getBranchCode();
		obj[3]=bean.getLoginId();
		obj[4] =bean.getBrokerId();
		obj[5] =bean.getCedingId();
		obj[6] = bean.getUwYear();
		/*if(!"-1".equals(bean.getLoginId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGLOGINID);	
		}
		if(!"-1".equals(bean.getBrokerId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGBROKERID);		
		}
		if(!"-1".equals(bean.getCedingId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGCEDINGID);
		}
		if(!"-1".equals(bean.getUwYear())){
			obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
			//query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACUWYEAR);
			query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMUWYEAR);
		}*/
		//query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGORDERBY);
		logger.info("Query=>"+query);
		int i=0;
		for(Object ob:obj){
			logger.info("Args["+i+"]"+ob);
			i++;
		}
		 list=this.mytemplate.queryForList(query, obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String,Object>> getRenewalDueList(final ReportsBean bean) {
		String query="";
		Object[] obj= new Object[0];
		if("1".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_RENEWALDUEFAC);
			obj=new Object[6];
			obj[0]=bean.getProductId();
			//obj[1]=bean.getProductId();
			//obj[2]=bean.getBranchCode();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getBranchCode();
			obj[3]=bean.getBranchCode();
			obj[4]=bean.getStart_date();
			obj[5]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_RENEWALORDER);
		}else if("2".equalsIgnoreCase(bean.getProductId()) || "3".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_XOLRENEWALDUELIST);
			obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
		}else if("4".equalsIgnoreCase(bean.getProductId()) || "5".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORTDAO_SELECT_RETRORETROXOL);
			obj=new Object[6];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getBranchCode();
			obj[2]=bean.getProductId();
			obj[3]=bean.getDept();
			obj[4]=bean.getStart_date();
			obj[5]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
		}else{
			query=getQuery(DBConstants.REPORTDAO_SELECT_XOLRENEWALDUELIST);
			obj=new Object[4];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			obj[2]=bean.getStart_date();
			obj[3]=bean.getEnd_date();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_REPORTUWYEAR);	
			}
		}
		logger.info("Query=>"+query);
		int j=0;
		for(Object o:obj){
			logger.info("Arg["+j+"]=>"+o);
			j++;
		}
		logger.info("Select Query====>"+query);
		final List<Map<String,Object>> list=this.mytemplate.queryForList(query, obj);
		return list;
	}

	public List<Map<String,Object>> getRetroQuarterlyReport(final ReportsBean bean) {
		String query="";
		Object[] obj= new Object[0];
		query=getQuery(DBConstants.REPORT_SELECT_RETROQUARRDS);
		obj=new Object[6];
		obj[0]=bean.getBranchCode();
		obj[1]=bean.getProductId();
		obj[2]=bean.getDept();
		obj[3]=bean.getStart_date();
		obj[4]=bean.getEnd_date();
		obj[5]=bean.getBranchCode();
		if(!"-1".equals(bean.getLoginId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
		}
		if(!"-1".equals(bean.getBrokerId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
		}
		if(!"-1".equals(bean.getCedingId())){
			obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
			query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);	
		}
		logger.info("Query=>"+query);
		int j=0;
		for(Object o:obj){
			logger.info("Arg["+j+"]=>"+o);
			j++;
		}
		List<Map<String,Object>> list=this.mytemplate.queryForList(query,obj);
		return list;
	}

	public List<Map<String,Object>> getInwardRetroMappingReport(ReportsBean bean){
		String query="";
		Object[] obj= new Object[0];
		if("1".equalsIgnoreCase(bean.getProductId())){
			query=getQuery(DBConstants.REPORT_SELECT_GETFACRETROLIST);
			obj=new Object[3];
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACUWYEAR);		
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_GETFACRETROORDERBY);
		}else {
			query=getQuery(DBConstants.REPORT_SELECT_GETTREATYXOLRETROLIST);
			obj=new Object[4];
			obj[0]=bean.getProductId();
			obj[1]=bean.getStart_date();
			obj[2]=bean.getEnd_date();
			obj[3]=bean.getBranchCode();
			if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACCEDINGID);

			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_POLICYREGFACUWYEAR);		
			}
			query+=" "+getQuery(DBConstants.REPORT_SELECT_GETFACRETROORDERBY);
		}
		logger.info("Query=>"+query);
		int j=0;
		for(Object o:obj){
			logger.info("Arg["+j+"]=>"+o);
			j++;
		}
		final List<Map<String,Object>> list=this.mytemplate.queryForList(query,obj);
		return list;
	}

	public List<Map<String,Object>> getRetroInwardMappingReport(ReportsBean bean){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[3];
			String query = "";
			obj[0] = bean.getStart_date();
			obj[1] = bean.getEnd_date();
			obj[2] = bean.getBranchCode();
			
			query = getQuery("GET_RETRO_RDS_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String,Object>> getTransactionMasterReport(ReportsBean bean){
		String query="";
		Object[] obj= new Object[0];
		query=getQuery(DBConstants.REPORT_SELECT_GETTRANSMASTERLIST);
		obj=new Object[8];
		
		obj[0]=bean.getStart_date();
		obj[1]=bean.getEnd_date();
		obj[2]=bean.getBranchCode();
		obj[3]=bean.getDocType().replaceAll("\\s+","");
		obj[4]=bean.getBrokerId();
		obj[5]=bean.getCedingId();
		obj[6]=bean.getUwYear();
		obj[7]=bean.getContractNo();
		
		/*obj[3]=bean.getDocType().replaceAll("\\s+","");
		obj[4]=bean.getDocType().replaceAll("\\s+","");
		obj[5]=bean.getDocType().replaceAll("\\s+","");
		obj[6]=bean.getDocType().replaceAll("\\s+","");
		obj[7]=bean.getDocType().replaceAll("\\s+","");
		obj[8]=bean.getUwYear();
		obj[9]=bean.getUwYear();
		obj[10]=bean.getUwYear();
		obj[11]=bean.getContractNo();
		obj[12]=bean.getContractNo();
		obj[13]=bean.getContractNo();
		obj[14]=bean.getContractNo();
		obj[15]=bean.getContractNo();
		obj[16]=bean.getCedingId();
		obj[17]=bean.getCedingId();
		obj[18]=bean.getCedingId();
		obj[19]=bean.getBrokerId();
		obj[20]=bean.getBrokerId();
		obj[21]=bean.getBrokerId();*/
		
		//query+=" "+getQuery(DBConstants.REPORT_SELECT_PREMIUMREPORTORDERBY);
		logger.info("Query=>"+query);
		int j=0;
		for(Object o:obj){
			logger.info("Arg["+j+"]=>"+o);
			j++;
		}
		final List<Map<String,Object>> list=this.mytemplate.queryForList(query,obj);
		return list;
	}

	public List<Map<String, Object>> getDebtorsAgeingReport(ReportsBean bean) {
		String query = "";
		Object[] obj = new Object[19];
		List<Map<String, Object>> list=null;
		try {
			query = getQuery(DBConstants.REPORT_SELECT_GETAGEINGLIST);
			if("1".equalsIgnoreCase(bean.getType())){
				query+=" ORDER BY CURRENCY, BROKER_CODE,COMPANY_CODE,TRANSACTION_DATE,TRANSACTION_NO";
			}else if("2".equalsIgnoreCase(bean.getType())){
				query+=" ORDER BY UPPER(CLIENT_ID), CURRENCY";
			}
			obj[0] = bean.getType();
			obj[1] = bean.getStart_date();
			obj[2] = bean.getDebFrom();
			obj[3] = bean.getDebTo();
			obj[4] = bean.getDebFrom1();
			obj[5] = bean.getDebTo1();
			obj[6] = bean.getDebFrom2();
			obj[7] = bean.getDebTo2();
			obj[8] = bean.getDebFrom3();
			obj[9] = bean.getDebTo3();
			obj[10] = bean.getDebFrom4();
			obj[11] = bean.getDebTo4();
			obj[12] = bean.getDebFrom5();
			obj[13] = bean.getDebTo5();
			obj[14] = bean.getBranchCode();
			obj[15] = bean.getProductId();
			obj[16] = bean.getBrokerId();
			obj[17] = bean.getCedingId();
			obj[18] = bean.getDocType();
			logger.info("Query=>" + query);
			logger.info("Arg[]=>" + StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public List<Map<String,Object>> getMoveMntSummary(ReportsBean bean){
		String query="";
		List<Map<String,Object>> list=null; 
		Object[] obj= new Object[3];
		try{
			query=getQuery(DBConstants.REPORT_SELECT_GETMOVSYMMARY);
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getStart_date();
			obj[2]=bean.getEnd_date();
			logger.info("Query=>"+query);
			logger.info("Arg[]=>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(query,obj);
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String,Object>> getCompanyInfoList(ReportsBean bean){
		List<Map<String,Object>> companyInfo=null;
		try{
			String query = getQuery(DBConstants.REPORT_SELECT_GETCOMPANYINFOLIST);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+"RI01");
			logger.info("Args[1]=>"+"Y");
			companyInfo = this.mytemplate.queryForList(query, new Object[] {"RI01","Y"});	
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}		
		return companyInfo;
	}

	public List<Map<String,Object>> getColumnInfoList(ReportsBean bean){		
		List<Map<String,Object>> columnInfo=null;
		try{
			String query = getQuery(DBConstants.REPORT_SELECT_GETCOLUMNINFOLIST);
			logger.info("Select Query=>"+query);
			Object obj[]=new Object[5];
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getProductId();
			if("1".equals(bean.getProductId()))
				obj[2]=(StringUtils.isBlank(bean.getDept())||"-1".equals(bean.getDept())?"2":bean.getDept());
			else
				obj[2]=(StringUtils.isBlank(bean.getDept())||"-1".equals(bean.getDept())?"0":bean.getDept());
			if("26".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="9";	
			}
			else{
			obj[3]=bean.getTypeId();
			}
			obj[4]="Y";
			logger.info("Args[]=>"+StringUtils.join(obj,","));
			columnInfo = this.mytemplate.queryForList(query, obj);
			/*if("19".equals(bean.getTypeId())){
				query = "SELECT EXCEL_HEADER_NAME,FIELD_NAME,DATA_TYPE,SUMATIONYN,FORMULA FROM XL_DOWNLOAD_CONFIG_MASTER WHERE  TYPE_ID=? AND STATUS='Y' ORDER BY SNO";
				Object obj1[]=new Object[1];
				obj1[0]=bean.getTypeId();
				columnInfo = this.mytemplate.queryForList(query, obj1);
			}*/
			if("N".equals(bean.getShowAllFields()) && "9".equals(bean.getTypeId())){
				query = "SELECT EXCEL_HEADER_NAME,FIELD_NAME,DATA_TYPE,SUMATIONYN,FORMULA FROM XL_DOWNLOAD_CONFIG_MASTER WHERE  TYPE_ID=20 AND STATUS='Y' ORDER BY SNO";
				logger.info("Select Query=>"+query);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				columnInfo = this.mytemplate.queryForList(query);
			}else if("N".equals(bean.getShowAllFields()) && "21".equals(bean.getTypeId())){
				query = "SELECT EXCEL_HEADER_NAME,FIELD_NAME,DATA_TYPE,SUMATIONYN,FORMULA FROM XL_DOWNLOAD_CONFIG_MASTER WHERE  TYPE_ID=22 AND STATUS='Y' ORDER BY SNO";
				logger.info("Select Query=>"+query);
				logger.info("Args[]=>"+StringUtils.join(obj,","));
				columnInfo = this.mytemplate.queryForList(query);
			}else if("36".equals(bean.getTypeId()) || "11".equals(bean.getTypeId()) || "54".equals(bean.getTypeId())){
				query = "SELECT EXCEL_HEADER_NAME,FIELD_NAME,DATA_TYPE,SUMATIONYN,FORMULA FROM XL_DOWNLOAD_CONFIG_MASTER WHERE  TYPE_ID=? AND STATUS='Y' ORDER BY SNO";
				logger.info("Select Query=>"+query);
				Object obj1[]=new Object[1];
				obj1[0]=bean.getTypeId();
				logger.info("Args[]=>"+StringUtils.join(obj1,","));
				columnInfo = this.mytemplate.queryForList(query, obj1);
			}
				

		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return columnInfo;
	}

	public List<Map<String,Object>> getMoveMentPage(final ReportsBean bean){
		Object[] args;
		String query="";
		args=new Object[5];
		args[0]=bean.getAccper();
		args[1]=bean.getAccountDate();
		args[2]=bean.getBranchCode();
		args[3]=bean.getMovementType();
		args[4]=bean.getMovId();
		if("MovDisPage".equalsIgnoreCase(bean.getDisplay())){
			query=getQuery(DBConstants.REPORT_SELECT_DISCREPANCIES);
		}else{
			query=getQuery(DBConstants.REPORT_SELECT_MOVEMENT);
		} 
		logger.info("Query=>"+query);
		logger.info("args[0]=>"+args[0]);
		logger.info("args[1]=>"+args[1]);
		logger.info("args[2]=>"+args[2]);
		logger.info("args[3]=>"+args[3]);
		logger.info("args[4]=>"+args[4]);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			if("MovDisPage".equalsIgnoreCase(bean.getDisplay())){
				bean.setAccper(tempMap.get("ACCOUNT_QTR")==null?"":tempMap.get("ACCOUNT_QTR").toString());
				bean.setAccountDate(tempMap.get("ACCOUNT_YEAR")==null?"":tempMap.get("ACCOUNT_YEAR").toString());
				bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
				bean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
				bean.setCurrencyname(tempMap.get("CURRENCY")==null?"":tempMap.get("CURRENCY").toString());
				bean.setPreviousPremium(tempMap.get("PREV_AMT_DC")==null?"":tempMap.get("PREV_AMT_DC").toString());
				bean.setCurrentPremium(tempMap.get("AMOUNT_DC")==null?"":tempMap.get("AMOUNT_DC").toString());
				bean.setDiffPremium(tempMap.get("PRM_DIFF")==null?"":tempMap.get("PRM_DIFF").toString());
				bean.setCurrentGrossPre((tempMap.get("GROSS_PREMIUM_DC"))==null?"":tempMap.get("GROSS_PREMIUM_DC").toString());
				bean.setPreGrossPre((tempMap.get("GROSS_PREMIUM_DC_PREV"))==null?"":tempMap.get("GROSS_PREMIUM_DC_PREV").toString());
				bean.setDiffGrossPre((tempMap.get("GROSS_PREMIUM_DC_DIFF"))==null?"":tempMap.get("GROSS_PREMIUM_DC_DIFF").toString());
				bean.setCurrentGrossAcq((tempMap.get("GROSS_ACQUISTION_COST_DC"))==null?"":tempMap.get("GROSS_ACQUISTION_COST_DC").toString());
				bean.setPreviousGrossAcq((tempMap.get("GROSS_ACQUISTION_COST_DC_PREV"))==null?"":tempMap.get("GROSS_ACQUISTION_COST_DC_PREV").toString());
				bean.setDiffGrossAcq((tempMap.get("GROSS_ACQUISTION_COST_DC_DIFF"))==null?"":tempMap.get("GROSS_ACQUISTION_COST_DC_DIFF").toString());
				bean.setCurrentPremiumDepositRet(((tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC")))==null?"":tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC").toString());
				bean.setPreviousPremiumDepositRet(((tempMap.get("PREM_DEP_RETAINED_DC_PREV")))==null?"":tempMap.get("PREM_DEP_RETAINED_DC_PREV").toString());
				bean.setDiffPremiumDepositRet(((tempMap.get("PREM_DEP_RETAINED_DC_DIFF")))==null?"":tempMap.get("PREM_DEP_RETAINED_DC_DIFF").toString());
				bean.setCurrentPremiumDepositRel(((tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC")))==null?"":tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC").toString());
				bean.setPreviousPremiumDepositRel(((tempMap.get("PREM_DEP_RELEASED_DC_PREV")))==null?"":tempMap.get("PREM_DEP_RELEASED_DC_PREV").toString());
				bean.setDiffPremiumDepositRel(((tempMap.get("PREM_DEP_RELEASED_DC_DIFF")))==null?"":tempMap.get("PREM_DEP_RELEASED_DC_DIFF").toString());
				bean.setCurrentClaimDepositRet(((tempMap.get("CLAIM_DEPOSIT_RETAINED_DC")))==null?"":tempMap.get("CLAIM_DEPOSIT_RETAINED_DC").toString());
				bean.setPreviousClaimDepositRet(((tempMap.get("CLAIM_DEPOSIT_RETAINED_DC_PREV")))==null?"":tempMap.get("CLAIM_DEPOSIT_RETAINED_DC_PREV").toString());
				bean.setDiffClaimDepositRet(((tempMap.get("CLAIM_DEPOSIT_RETAINED_DC_DIFF")))==null?"":tempMap.get("CLAIM_DEPOSIT_RETAINED_DC_DIFF").toString());
				bean.setCurrentClaimDepositRel(((tempMap.get("CLAIM_DEPOSIT_RELEASED_DC")))==null?"":tempMap.get("CLAIM_DEPOSIT_RELEASED_DC").toString());
				bean.setPreviousClaimDepositRel(((tempMap.get("CLAIM_DEPOSIT_RELEASED_DC_PREV")))==null?"":tempMap.get("CLAIM_DEPOSIT_RELEASED_DC_PREV").toString());
				bean.setDiffClaimDepositRel(((tempMap.get("CLAIM_DEPOSIT_RELEASED_DC_DIFF")))==null?"":tempMap.get("CLAIM_DEPOSIT_RELEASED_DC_DIFF").toString());
				bean.setCurrentInterestDeposit(((tempMap.get("INTEREST_ON_DEPOSIT_DC")))==null?"":tempMap.get("INTEREST_ON_DEPOSIT_DC").toString());
				bean.setPreviousInterestDeposit(((tempMap.get("INTEREST_ON_DEPOSIT_DC_PREV")))==null?"":tempMap.get("INTEREST_ON_DEPOSIT_DC_PREV").toString());
				bean.setDiffInterestDeposit(((tempMap.get("INTEREST_ON_DEPOSIT_DC_DIFF")))==null?"":tempMap.get("INTEREST_ON_DEPOSIT_DC_DIFF").toString());
				bean.setCurrentGrossClaimPaid(((tempMap.get("GROSS_CLAIMS_PAID_DC")))==null?"":tempMap.get("GROSS_CLAIMS_PAID_DC").toString());
				bean.setPreviousGrossClaimPaid(((tempMap.get("GROSS_CLAIMS_PAID_DC_PREV")))==null?"":tempMap.get("GROSS_CLAIMS_PAID_DC_PREV").toString());
				bean.setDiffGrossClaimPaid(((tempMap.get("GROSS_CLAIMS_PAID_DC_DIFF")))==null?"":tempMap.get("GROSS_CLAIMS_PAID_DC_DIFF").toString());
			}else{
				bean.setSNo(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
				bean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
				bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
				bean.setSumofPrm(DropDownControllor.formatter(tempMap.get("SUMOFTOTPR")==null?"0":tempMap.get("SUMOFTOTPR").toString()));
				bean.setSumofPrmDC(DropDownControllor.formatter(tempMap.get("SUMOFTOTPR_DC")==null?"0":tempMap.get("SUMOFTOTPR_DC").toString()));
			} 

		}


		/*final List list=this.mytemplate.query(query,args,new RowMapper() {
			public Object mapRow(ResultSet move, int rowNum) throws SQLException {
				if("MovDisPage".equalsIgnoreCase(bean.getDisplay()))
				{
					repBean.setAccper(move.getString("ACCOUNT_QTR"));
					repBean.setAccountDate(move.getString("ACCOUNT_YEAR"));
					repBean.setUwYear(move.getString("UW_YEAR"));
					repBean.setSpc(move.getString("SPC_NAME"));
					repBean.setCurrencyname(move.getString("CURRENCY"));
					repBean.setPreviousPremium(move.getString("PREV_AMT_DC"));
					repBean.setCurrentPremium(move.getString("AMOUNT_DC"));
					repBean.setDiffPremium(move.getString("PRM_DIFF"));
					repBean.setCurrentGrossPre((move.getString("GROSS_PREMIUM_DC")));
					repBean.setPreGrossPre((move.getString("GROSS_PREMIUM_DC_PREV")));
					repBean.setDiffGrossPre((move.getString("GROSS_PREMIUM_DC_DIFF")));
					repBean.setCurrentGrossAcq((move.getString("GROSS_ACQUISTION_COST_DC")));
					repBean.setPreviousGrossAcq((move.getString("GROSS_ACQUISTION_COST_DC_PREV")));
					repBean.setDiffGrossAcq((move.getString("GROSS_ACQUISTION_COST_DC_DIFF")));
					repBean.setCurrentPremiumDepositRet(((move.getString("PREMIUM_DEPOSIT_RETAINED_DC"))));
					repBean.setPreviousPremiumDepositRet(((move.getString("PREM_DEP_RETAINED_DC_PREV"))));
					repBean.setDiffPremiumDepositRet(((move.getString("PREM_DEP_RETAINED_DC_DIFF"))));
					repBean.setCurrentPremiumDepositRel(((move.getString("PREMIUM_DEPOSIT_RELEASED_DC"))));
					repBean.setPreviousPremiumDepositRel(((move.getString("PREM_DEP_RELEASED_DC_PREV"))));
					repBean.setDiffPremiumDepositRel(((move.getString("PREM_DEP_RELEASED_DC_DIFF"))));
					repBean.setCurrentClaimDepositRet(((move.getString("CLAIM_DEPOSIT_RETAINED_DC"))));
					repBean.setPreviousClaimDepositRet(((move.getString("CLAIM_DEPOSIT_RETAINED_DC_PREV"))));
					repBean.setDiffClaimDepositRet(((move.getString("CLAIM_DEPOSIT_RETAINED_DC_DIFF"))));
					repBean.setCurrentClaimDepositRel(((move.getString("CLAIM_DEPOSIT_RELEASED_DC"))));
					repBean.setPreviousClaimDepositRel(((move.getString("CLAIM_DEPOSIT_RELEASED_DC_PREV"))));
					repBean.setDiffClaimDepositRel(((move.getString("CLAIM_DEPOSIT_RELEASED_DC_DIFF"))));
					repBean.setCurrentInterestDeposit(((move.getString("INTEREST_ON_DEPOSIT_DC"))));
					repBean.setPreviousInterestDeposit(((move.getString("INTEREST_ON_DEPOSIT_DC_PREV"))));
					repBean.setDiffInterestDeposit(((move.getString("INTEREST_ON_DEPOSIT_DC_DIFF"))));
					repBean.setCurrentGrossClaimPaid(((move.getString("GROSS_CLAIMS_PAID_DC"))));
					repBean.setPreviousGrossClaimPaid(((move.getString("GROSS_CLAIMS_PAID_DC_PREV"))));
					repBean.setDiffGrossClaimPaid(((move.getString("GROSS_CLAIMS_PAID_DC_DIFF"))));
				}else{
					repBean.setSNo(move.getString("SNO"));
					repBean.setUwYear(move.getString("UW_YEAR"));
					repBean.setSpc(move.getString("SPC_NAME"));
					repBean.setCurrencyname(move.getString("CURRENCY_NAME"));
					repBean.setSumofPrm(DropDownControllor.formatter(move.getString("SUMOFTOTPR")==null?"0":move.getString("SUMOFTOTPR")));
					repBean.setSumofPrmDC(DropDownControllor.formatter(move.getString("SUMOFTOTPR_DC")==null?"0":move.getString("SUMOFTOTPR_DC")));
				} 
				return repBean;
			}
		});*/
		return list;
	}

	public List<ReportsBean> getViewAll(ReportsBean bean) {
		String query="";
		List<ReportsBean> finalList=new ArrayList<ReportsBean>();	
		try{
			Object args[]=null;
			if("viewJurnalAll".equalsIgnoreCase(bean.getDisplay())){
				args=new Object[4];
				args[0]=bean.getAccper();
				args[1]=bean.getAccountDate();
				args[2]=bean.getBranchCode();
				args[3]=bean.getMovId();
				query=getQuery(DBConstants.REPORT_SELECT_VIEWJURNAL1);
				logger.info("Query=>"+query);
				logger.info("args[0]=>"+args[0]);
				logger.info("args[1]=>"+args[1]);
				logger.info("args[2]=>"+args[2]);
				logger.info("args[3]=>"+args[3]);
			}else{
				args=new Object[5];
				args[0]=bean.getAccper();
				args[1]=bean.getAccountDate();
				args[2]=bean.getBranchCode();
				args[3]=bean.getSNo();
				args[4]=bean.getMovId();
				query=getQuery(DBConstants.REPORT_SELECT_VIEWJURNAL);
				logger.info("Query=>"+query);
				logger.info("args[]=>"+bean.getAccper()+","+bean.getAccountDate()+","+bean.getBranchCode()+","+bean.getSNo());
			}
			List<Map<String,Object>>list = this.mytemplate.queryForList(query,args);
			//if(list!=null && list.size()>0){
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				double gpOC,gpDC,gAOC,gADC,pdRelOC,pdRelDC,pdRetOC,pdRetDC,cdRetOC,cdRetDC,cdRelOC,cdRelDC,intOC,intDC,gcpOC,gcpDC,blctlOC=0,blctlDC=0,totalCROC=0,totalCRDC=0,totalDROC=0,totalDRDC=0;
				ReportsBean tempBean = new ReportsBean();
				tempBean.setAccount_Period(bean.getAccount_Period());
				tempBean.setAccountDate(bean.getAccountDate());
				tempBean.setSNo(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				tempBean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
				tempBean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
				tempBean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());

				tempBean.setGrossPremiumOC(DropDownControllor.formatter(tempMap.get("GROSS_PREMIUM_OC")==null?"":tempMap.get("GROSS_PREMIUM_OC").toString())); //Gross Premium oc
				tempBean.setGrossPremiumDC(DropDownControllor.formatter(tempMap.get("GROSS_PREMIUM_DC")==null?"":tempMap.get("GROSS_PREMIUM_DC").toString())); //Gross Premium dc

				tempBean.setGrossAcqCostOC(DropDownControllor.formatter(tempMap.get("GROSS_ACQUISTION_COST_OC")==null?"":tempMap.get("GROSS_ACQUISTION_COST_OC").toString())); //
				tempBean.setGrossAcqCostDC(DropDownControllor.formatter(tempMap.get("GROSS_ACQUISTION_COST_DC")==null?"":tempMap.get("GROSS_ACQUISTION_COST_DC").toString())); //

				tempBean.setPremiumDepositRetainedDC(DropDownControllor.formatter(tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC")==null?"":tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC").toString())); //Premium Deposit Retained dc
				tempBean.setPremiumDepositRetainedOC(DropDownControllor.formatter(tempMap.get("PREMIUM_DEPOSIT_RETAINED_OC")==null?"":tempMap.get("PREMIUM_DEPOSIT_RETAINED_OC").toString())); //Premium Deposit Retained oc

				tempBean.setPremiumDepositReleasedOC(DropDownControllor.formatter(tempMap.get("PREMIUM_DEPOSIT_RELEASED_OC")==null?"":tempMap.get("PREMIUM_DEPOSIT_RELEASED_OC").toString())); //Premium Deposit Released oc
				tempBean.setPremiumDepositReleasedDC(DropDownControllor.formatter(tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC")==null?"":tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC").toString())); //Premium Deposit Released dc

				tempBean.setClaimDepositRetainedOC(DropDownControllor.formatter(tempMap.get("CLAIM_DEPOSIT_RETAINED_OC")==null?"":tempMap.get("CLAIM_DEPOSIT_RETAINED_OC").toString()));  //Claim Deposit Retained oc
				tempBean.setClaimDepositRetainedDC(DropDownControllor.formatter(tempMap.get("CLAIM_DEPOSIT_RETAINED_DC")==null?"":tempMap.get("CLAIM_DEPOSIT_RETAINED_DC").toString())); //Claim Deposit Retained dc

				tempBean.setClaimDepositReleasedOC(DropDownControllor.formatter(tempMap.get("CLAIM_DEPOSIT_RELEASED_OC")==null?"":tempMap.get("CLAIM_DEPOSIT_RELEASED_OC").toString()));  //Claim Deposit Released oc
				tempBean.setClaimDepositReleasedDC(DropDownControllor.formatter(tempMap.get("CLAIM_DEPOSIT_RELEASED_DC")==null?"":tempMap.get("CLAIM_DEPOSIT_RELEASED_DC").toString())); //Claim Deposit Released dc

				tempBean.setInterestOnDepositOC(DropDownControllor.formatter(tempMap.get("INTEREST_ON_DEPOSIT_OC")==null?"":tempMap.get("INTEREST_ON_DEPOSIT_OC").toString())); //Interest On Deposit oc
				tempBean.setInterestOnDepositDC(DropDownControllor.formatter(tempMap.get("INTEREST_ON_DEPOSIT_DC")==null?"":tempMap.get("INTEREST_ON_DEPOSIT_DC").toString())); //Interest On Deposit dc

				tempBean.setGrossClaimPaidOC(DropDownControllor.formatter(tempMap.get("GROSS_CLAIMS_PAID_OC")==null?"":tempMap.get("GROSS_CLAIMS_PAID_OC").toString())); //Gross Claims Paid oc
				tempBean.setGrossClaimPaidDC(DropDownControllor.formatter(tempMap.get("GROSS_CLAIMS_PAID_DC")==null?"":tempMap.get("GROSS_CLAIMS_PAID_DC").toString())); //Gross Claims Paid dc

				tempBean.setGpOC(tempBean.getGrossPremiumOC().replaceAll(",", ""));
				tempBean.setgAOC(tempBean.getGrossAcqCostOC().replaceAll(",", ""));
				tempBean.setPdRetOC(tempBean.getPremiumDepositRetainedOC().replaceAll(",", ""));
				tempBean.setPdRelOC(tempBean.getPremiumDepositReleasedOC().replaceAll(",", ""));
				tempBean.setCdRelOC(tempBean.getClaimDepositReleasedOC().replaceAll(",", ""));
				tempBean.setCdRetOC(tempBean.getClaimDepositRetainedOC().replaceAll(",", ""));
				tempBean.setIntOC(tempBean.getInterestOnDepositOC().replaceAll(",", ""));
				tempBean.setGcpOC(tempBean.getGrossClaimPaidOC().replaceAll(",", ""));

				gpOC=Double.valueOf(tempBean.getGrossPremiumOC().replaceAll(",", ""));
				gpDC=Double.valueOf(tempBean.getGrossPremiumDC().replaceAll(",", ""));

				gAOC=Double.valueOf(tempBean.getGrossAcqCostOC().replaceAll(",", ""));
				gADC=Double.valueOf(tempBean.getGrossAcqCostDC().replaceAll(",", ""));

				pdRetOC=Double.valueOf(tempBean.getPremiumDepositRetainedOC().replaceAll(",", ""));
				pdRetDC=Double.valueOf(tempBean.getPremiumDepositRetainedDC().replaceAll(",", ""));

				pdRelOC=Double.valueOf(tempBean.getPremiumDepositReleasedOC().replaceAll(",", ""));
				pdRelDC=Double.valueOf(tempBean.getPremiumDepositReleasedDC().replaceAll(",", ""));

				cdRelOC=Double.valueOf(tempBean.getClaimDepositReleasedOC().replaceAll(",", ""));
				cdRelDC=Double.valueOf(tempBean.getClaimDepositReleasedDC().replaceAll(",", ""));

				cdRetOC=Double.valueOf(tempBean.getClaimDepositRetainedOC().replaceAll(",", ""));
				cdRetDC=Double.valueOf(tempBean.getClaimDepositRetainedDC().replaceAll(",", ""));

				intOC=Double.valueOf(tempBean.getInterestOnDepositOC().replaceAll(",", ""));
				intDC=Double.valueOf(tempBean.getInterestOnDepositDC().replaceAll(",", ""));

				gcpOC=Double.valueOf(tempBean.getGrossClaimPaidOC().replaceAll(",", ""));
				gcpDC=Double.valueOf(tempBean.getGrossClaimPaidDC().replaceAll(",", ""));

				//Credit Total
				if(gpOC>=0){
					totalCROC=totalCROC+gpOC;
					totalCRDC=totalCRDC+gpDC;
				}else{
					totalDROC=totalDROC+Math.abs(gpOC);
					totalDRDC=totalDRDC+Math.abs(gpDC);
				}
				if(pdRelOC>=0){
					totalCROC=totalCROC+pdRelOC;
					totalCRDC=totalCRDC+pdRelDC;
				}else{
					totalDROC=totalDROC+Math.abs(pdRelOC);
					totalDRDC=totalDRDC+Math.abs(pdRelDC);
				}
				if(cdRelOC>=0){
					totalCROC=totalCROC+cdRelOC;
					totalCRDC=totalCRDC+cdRelDC;
				}else{
					totalDROC=totalDROC+Math.abs(cdRelOC);
					totalDRDC=totalDRDC+Math.abs(cdRelDC);
				}
				if(intOC>=0){
					totalCROC=totalCROC+intOC;
					totalCRDC=totalCRDC+intDC;
				}else{
					totalDROC=totalDROC+Math.abs(intOC);
					totalDRDC=totalDRDC+Math.abs(intDC);
				}
				if(gcpOC>=0){
					totalCROC=totalCROC+gcpOC;
					totalCRDC=totalCRDC+gcpDC;
				}else{
					totalDROC=totalDROC+Math.abs(gcpOC);
					totalDRDC=totalDRDC+Math.abs(gcpDC);
				}

				//Debit Total

				if(gAOC>=0){
					totalDROC=totalDROC+gAOC;
					totalDRDC=totalDRDC+gADC;
				}else{
					totalCROC=totalCROC+Math.abs(gAOC);
					totalCRDC=totalCRDC+Math.abs(gADC);
				}
				if(pdRetOC>=0){
					totalDROC=totalDROC+pdRetOC;
					totalDRDC=totalDRDC+pdRetDC;
				}else{
					totalCROC=totalCROC+Math.abs(pdRetOC);
					totalCRDC=totalCRDC+Math.abs(pdRetDC);
				}
				if(cdRetOC>=0){
					totalDROC=totalDROC+cdRetOC;
					totalDRDC=totalDRDC+cdRetDC;
				}else{
					totalCROC=totalCROC+Math.abs(cdRetOC);
					totalCRDC=totalCRDC+Math.abs(cdRetDC);
				}
				blctlOC=totalCROC-totalDROC;
				blctlDC=totalCRDC-totalDRDC;
				if(blctlOC>0){
					totalDROC=totalDROC+blctlOC;
					totalDRDC=totalDRDC+blctlDC;
				}else{
					totalCROC=totalCROC+Math.abs(blctlOC);
					totalCRDC=totalCRDC+Math.abs(blctlDC);	
				}
				tempBean.setBrokerLedCtlOC(DropDownControllor.formatter(String.valueOf(blctlOC)));
				tempBean.setBrokerLedCtlDC(DropDownControllor.formatter(String.valueOf(blctlDC)));
				tempBean.setBlOC(String.valueOf(blctlOC));
				tempBean.setTotalCROC(DropDownControllor.formatter(String.valueOf(totalCROC)));
				tempBean.setTotalDROC(DropDownControllor.formatter(String.valueOf(totalDROC)));
				tempBean.setTotalCRDC(DropDownControllor.formatter(String.valueOf(totalCRDC)));
				tempBean.setTotalDRDC(DropDownControllor.formatter(String.valueOf(totalDRDC)));

				logger.info("Total CR Original currency is "+totalCROC);
				logger.info("Total Dr Original currency is "+totalDROC);
				logger.info("Total CR Destination currency is "+totalCRDC);
				logger.info("Total DR Destination currency is "+totalDRDC);
				logger.info("Broker ledg ctl for OC is "+blctlOC);
				logger.info("Broker ledg ctl for DC is "+blctlDC);

				finalList.add(tempBean);
			}

		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return finalList;
	}

	public List<Map<String,Object>> getClaimMoveMentPage(final ReportsBean bean){
		Object[] args;
		String query="";
		args=new Object[4];
		args[0]=bean.getMovId();
		args[1]=bean.getBranchCode();
		args[2]=bean.getMovId();
		args[3]=bean.getBranchCode();
		query=getQuery(DBConstants.REPORT_SELECT_CLMOVEMENT_DTLS);
		logger.info("Query=>"+query);
		logger.info("args[]=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setUwYear(tempMap.get("UWYEAR")==null?"":tempMap.get("UWYEAR").toString());
			bean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
			bean.setContractNo(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
			bean.setProductName(tempMap.get("PRODUCT")==null?"":tempMap.get("PRODUCT").toString());
			bean.setTranNo(tempMap.get("TRAN_NO")==null?"":tempMap.get("TRAN_NO").toString());
			bean.setType(tempMap.get("TYPE")==null?"":tempMap.get("TYPE").toString());
			bean.setOsLossupdateOC(tempMap.get("OUTSTANDING_AMOUNT_OC")==null?"":tempMap.get("OUTSTANDING_AMOUNT_OC").toString());
			bean.setOsLossupdateDC(tempMap.get("OUTSTANDING_AMOUNT_DC")==null?"":tempMap.get("OUTSTANDING_AMOUNT_DC").toString());
		}

		/*final List<Map<String,Object>> list=this.mytemplate.query(query,args,new RowMapper() {
				public Object mapRow(ResultSet move, int rowNum) throws SQLException {
					        ReportsActionForm repBean=new ReportsActionForm();	
				        	repBean.setUwYear(move.getString("UWYEAR"));
				    		repBean.setSpc(move.getString("SPC_NAME"));
				    		repBean.setCurrencyname(move.getString("CURRENCY_NAME"));
				    		repBean.setContractNo(move.getString("CONTRACT_NO"));
				    		repBean.setProductName(move.getString("PRODUCT"));
				    		repBean.setTranNo(move.getString("TRAN_NO"));
				    		repBean.setType(move.getString("TYPE"));
				    		repBean.setOsLossupdateOC(move.getString("OUTSTANDING_AMOUNT_OC"));
				    		repBean.setOsLossupdateDC(move.getString("OUTSTANDING_AMOUNT_DC"));
				            return repBean;
			    }
			    });*/
		return list;
	}

	public List<Map<String,Object>> getJournelPage(final ReportsBean bean){
		Object[] args;
		String query="";
		args=new Object[6];
		args[0]=bean.getBranchCode();
		args[1]=bean.getBranchCode();
		args[2]=bean.getMovId();
		args[3]=bean.getBranchCode();
		args[4]=bean.getBranchCode();
		args[5]=bean.getBranchCode();

		query=getQuery(DBConstants.REPORT_SELECT_JOURNEL);

		logger.info("Query=>"+query);
		logger.info("args[0]=>"+args[0]);
		logger.info("args[2]=>"+args[2]);

		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setSNo(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
			bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			bean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
			bean.setSumofPrm(DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_OC")==null?"0":tempMap.get("LOSS_ESTIMATE_REVISED_OC").toString()));
			bean.setSumofPrmDC(DropDownControllor.formatter(tempMap.get("LOSS_ESTIMATE_REVISED_DC")==null?"0":tempMap.get("LOSS_ESTIMATE_REVISED_DC").toString()));
		}
		/*final List list=this.mytemplate.query(query,args,new RowMapper() {
			public Object mapRow(ResultSet move, int rowNum) throws SQLException {
				        ReportsActionForm repBean=new ReportsActionForm();	
				       		repBean.setSNo(move.getString("SNO")==null?"":move.getString("SNO"));
				       		repBean.setUwYear(move.getString("UW_YEAR")==null?"":move.getString("UW_YEAR"));
				    		repBean.setSpc(move.getString("SPC_NAME")==null?"":move.getString("SPC_NAME"));
				    		repBean.setCurrencyname(move.getString("CURRENCY_NAME")==null?"":move.getString("CURRENCY_NAME"));
				            repBean.setSumofPrm(DropDownControllor.formatter(move.getString("LOSS_ESTIMATE_REVISED_OC")==null?"0":move.getString("LOSS_ESTIMATE_REVISED_OC")));
				            repBean.setSumofPrmDC(DropDownControllor.formatter(move.getString("LOSS_ESTIMATE_REVISED_DC")==null?"0":move.getString("LOSS_ESTIMATE_REVISED_DC")));
			            return repBean;
		    }
		    });*/
		return list;
	}

	public String insertCLMovement(ReportsBean bean){
		try{
			String query=getQuery(DBConstants.REPORT_SP_CLMOVEMENTREP);
			Object[] args=new Object[] {bean.getEnd_date(),bean.getBranchCode(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getEnd_date()+","+bean.getBranchCode()+","+bean.getMovementType());
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String insertMovement(ReportsBean bean){
		try{
			String query=getQuery(DBConstants.REPORT_SP_MOVEMENTREP);
			Object[] args=new Object[] {bean.getAccper(),bean.getAccountDate(),bean.getBranchCode(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getAccper()+","+bean.getAccountDate()+","+bean.getBranchCode()+","+bean.getMovementType());
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public int getCountMovementRecord(String branchCode, String accQtr,	String accYear) {
		int count=0;
		try{
			String query=getQuery(DBConstants.REPORT_GET_MOVEMENTCNT);
			Object[] args=new Object[] {branchCode,accQtr,accYear};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+branchCode+","+accQtr+","+accYear);
			count=this.mytemplate.queryForInt(query,args);
			logger.info("Result=>"+count);

		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return count;
	}

	public List<ReportsBean> getViewJurnelAll(ReportsBean bean) {
		String query="";
		List<ReportsBean> finalList=new ArrayList<ReportsBean>();	
		try{
			Object args[]=null;
			if("viewJurnalAll".equalsIgnoreCase(bean.getDisplay())){
				args=new Object[6];
				args[0]=bean.getBranchCode();
				args[1]=bean.getBranchCode();
				args[2]=bean.getMovId();
				args[3]=bean.getBranchCode();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				query=getQuery(DBConstants.REPORT_SELECT_VIEWJURNALALL1);
				logger.info("Query=>"+query);
				logger.info("args[0]=>"+args[0]);
				logger.info("args[2]=>"+args[2]);
			}else{
				args=new Object[7];
				args[0]=bean.getBranchCode();
				args[1]=bean.getBranchCode();
				args[2]=bean.getMovId();
				args[3]=bean.getBranchCode();
				args[4]=bean.getBranchCode();
				args[5]=bean.getBranchCode();
				args[6]=bean.getSNo();
				query=getQuery(DBConstants.REPORT_SELECT_VIEWJURNALALL);
				logger.info("Query=>"+query);
				logger.info("args[0]=>"+args[0]);
				logger.info("args[2]=>"+args[2]);
				logger.info("args[6]=>"+args[6]);
			}

			List<Map<String,Object>>list = this.mytemplate.queryForList(query,args);
			//if(list!=null && list.size()>0){
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				double gpOC,gpDC,gAOC,gADC,pdRelOC,pdRelDC,pdRetOC,pdRetDC,cdRetOC,cdRetDC,cdRelOC,cdRelDC,blctlOC=0,blctlDC=0,totalCROC=0,totalCRDC=0,totalDROC=0,totalDRDC=0;
				ReportsBean tempBean = new ReportsBean();
				tempBean.setAccount_Period(bean.getAccount_Period());
				tempBean.setAccountDate(bean.getAccountDate());
				tempBean.setSNo(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				tempBean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
				tempBean.setSpc(tempMap.get("SPC_NAME")==null?"":tempMap.get("SPC_NAME").toString());
				tempBean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());

				tempBean.setOsLossMovement(DropDownControllor.formatter(tempMap.get("OS_LOSS_MOVEMENT_PL")==null?"":tempMap.get("OS_LOSS_MOVEMENT_PL").toString())); 
				tempBean.setOsLossMovementUSD(DropDownControllor.formatter(tempMap.get("OS_LOSS_MOVEMENT_PL_USD")==null?"":tempMap.get("OS_LOSS_MOVEMENT_PL_USD").toString())); 

				tempBean.setBsMovement(DropDownControllor.formatter(tempMap.get("BS_MOVEMENT")==null?"":tempMap.get("BS_MOVEMENT").toString())); 
				tempBean.setBsMovementUSD(DropDownControllor.formatter(tempMap.get("BS_MOVEMENT_USD")==null?"":tempMap.get("BS_MOVEMENT_USD").toString())); 

				tempBean.setJournelClaimPaid(DropDownControllor.formatter(tempMap.get("CLAIMS_PAID")==null?"":tempMap.get("CLAIMS_PAID").toString())); 
				tempBean.setJournelClaimPaidUSD(DropDownControllor.formatter(tempMap.get("CLAIMS_PAID_USD")==null?"":tempMap.get("CLAIMS_PAID_USD").toString()));

				tempBean.setDebtorsCreditors(DropDownControllor.formatter(tempMap.get("DEBTORS_CREDIT_CONTROL_AC")==null?"":tempMap.get("DEBTORS_CREDIT_CONTROL_AC").toString()));
				tempBean.setDebtorsCreditorsUSD(DropDownControllor.formatter(tempMap.get("DEBTORS_CREDIT_CONTROL_AC_USD")==null?"":tempMap.get("DEBTORS_CREDIT_CONTROL_AC_USD").toString()));

				tempBean.setOsLossMovementBs(DropDownControllor.formatter(tempMap.get("OS_LOSS_MOVEMENT_BS")==null?"":tempMap.get("OS_LOSS_MOVEMENT_BS").toString()));
				tempBean.setOsLossMovementBsUSD(DropDownControllor.formatter(tempMap.get("OS_LOSS_MOVEMENT_BS_USD")==null?"":tempMap.get("OS_LOSS_MOVEMENT_BS_USD").toString()));

				tempBean.setOsLossPl(DropDownControllor.formatter(tempMap.get("OS_LOSS_PL_CR")==null?"":tempMap.get("OS_LOSS_PL_CR").toString()));
				tempBean.setOsLossPlUSD(DropDownControllor.formatter(tempMap.get("OS_LOSS_PL_CR_USD")==null?"":tempMap.get("OS_LOSS_PL_CR_USD").toString()));

				tempBean.setOsLM(tempBean.getOsLossMovement().replaceAll(",", ""));
				tempBean.setBsM(tempBean.getBsMovement().replaceAll(",", ""));
				tempBean.setjCP(tempBean.getJournelClaimPaid().replaceAll(",", ""));
				tempBean.setdC(tempBean.getDebtorsCreditors().replaceAll(",", ""));
				tempBean.setOsLMB(tempBean.getOsLossMovementBs().replaceAll(",", ""));
				tempBean.setOsLP(tempBean.getOsLossPl().replaceAll(",", ""));


				gpOC=Double.valueOf(tempBean.getOsLossMovement().replaceAll(",", ""));
				gpDC=Double.valueOf(tempBean.getOsLossMovementUSD().replaceAll(",", ""));

				gAOC=Double.valueOf(tempBean.getBsMovement().replaceAll(",", ""));
				gADC=Double.valueOf(tempBean.getBsMovementUSD().replaceAll(",", ""));

				pdRetOC=Double.valueOf(tempBean.getJournelClaimPaid().replaceAll(",", ""));
				pdRetDC=Double.valueOf(tempBean.getJournelClaimPaidUSD().replaceAll(",", ""));

				pdRelOC=Double.valueOf(tempBean.getDebtorsCreditors().replaceAll(",", ""));
				pdRelDC=Double.valueOf(tempBean.getDebtorsCreditorsUSD().replaceAll(",", ""));

				cdRelOC=Double.valueOf(tempBean.getOsLossMovementBs().replaceAll(",", ""));
				cdRelDC=Double.valueOf(tempBean.getOsLossMovementBsUSD().replaceAll(",", ""));

				cdRetOC=Double.valueOf(tempBean.getOsLossPl().replaceAll(",", ""));
				cdRetDC=Double.valueOf(tempBean.getOsLossPlUSD().replaceAll(",", ""));



				//Credit Total
				if(gpOC>=0){
					totalCROC=totalCROC+gpOC;
					totalCRDC=totalCRDC+gpDC;
				}else{
					totalDROC=totalDROC+Math.abs(gpOC);
					totalDRDC=totalDRDC+Math.abs(gpDC);
				}
				if(pdRelOC>=0){
					totalCROC=totalCROC+pdRelOC;
					totalCRDC=totalCRDC+pdRelDC;
				}else{
					totalDROC=totalDROC+Math.abs(pdRelOC);
					totalDRDC=totalDRDC+Math.abs(pdRelDC);
				}
				if(cdRelOC>=0){
					totalCROC=totalCROC+cdRelOC;
					totalCRDC=totalCRDC+cdRelDC;
				}else{
					totalDROC=totalDROC+Math.abs(cdRelOC);
					totalDRDC=totalDRDC+Math.abs(cdRelDC);
				}


				//Debit Total

				if(gAOC>=0){
					totalDROC=totalDROC+gAOC;
					totalDRDC=totalDRDC+gADC;
				}else{
					totalCROC=totalCROC+Math.abs(gAOC);
					totalCRDC=totalCRDC+Math.abs(gADC);
				}
				if(pdRetOC>=0){
					totalDROC=totalDROC+pdRetOC;
					totalDRDC=totalDRDC+pdRetDC;
				}else{
					totalCROC=totalCROC+Math.abs(pdRetOC);
					totalCRDC=totalCRDC+Math.abs(pdRetDC);
				}
				if(cdRetOC>=0){
					totalDROC=totalDROC+cdRetOC;
					totalDRDC=totalDRDC+cdRetDC;
				}else{
					totalCROC=totalCROC+Math.abs(cdRetOC);
					totalCRDC=totalCRDC+Math.abs(cdRetDC);
				}
				blctlOC=totalCROC-totalDROC;
				blctlDC=totalCRDC-totalDRDC;
				if(blctlOC>0){
					totalDROC=totalDROC+blctlOC;
					totalDRDC=totalDRDC+blctlDC;
				}else{
					totalCROC=totalCROC+Math.abs(blctlOC);
					totalCRDC=totalCRDC+Math.abs(blctlDC);	
				}
				tempBean.setBrokerLedCtlOC(DropDownControllor.formatter(String.valueOf(blctlOC)));
				tempBean.setBrokerLedCtlDC(DropDownControllor.formatter(String.valueOf(blctlDC)));

				tempBean.setTotalCROC(DropDownControllor.formatter(String.valueOf(totalCROC)));
				tempBean.setTotalDROC(DropDownControllor.formatter(String.valueOf(totalDROC)));
				tempBean.setTotalCRDC(DropDownControllor.formatter(String.valueOf(totalCRDC)));
				tempBean.setTotalDRDC(DropDownControllor.formatter(String.valueOf(totalDRDC)));

				logger.info("Total CR Original currency is "+totalCROC);
				logger.info("Total Dr Original currency is "+totalDROC);
				logger.info("Total CR Destination currency is "+totalCRDC);
				logger.info("Total DR Destination currency is "+totalDRDC);
				logger.info("Broker ledg ctl for OC is "+blctlOC);
				logger.info("Broker ledg ctl for DC is "+blctlDC);

				finalList.add(tempBean);
			}
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return finalList;
	}
	
	public String journelInsertMovement(ReportsBean bean){
		try{
			bean.setMovementType(StringUtils.isBlank(bean.getMovementType())?"I":bean.getMovementType());
			String query=getQuery(DBConstants.JOURNELREPORT_SP_MOVEMENTREP);
			Object[] args=new Object[] {bean.getAccper(),bean.getAccountDate(),bean.getBranchCode(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getAccper()+","+bean.getAccountDate()+","+bean.getBranchCode()+","+bean.getMovementType()+","+"CL");
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}
	
	public List<Map<String,Object>> getJournelMoveMentInit(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[3];
			String query="";
			args[0]=bean.getBranchCode();
			args[1]=bean.getBranchCode();
			args[2]=bean.getBranchCode();
			query=getQuery(DBConstants.JOURNELREPORT_SELECT_MOVEMENT_INIT);
			logger.info("Query=>"+query);
			logger.info("args[0][1]=>"+bean.getBranchCode());
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setSNo(tempMap.get("MOVMENT_TRANID")==null?"":tempMap.get("MOVMENT_TRANID").toString());
				bean.setAccount_Period(tempMap.get("DETAIL_NAME")==null?"":tempMap.get("DETAIL_NAME").toString());
				bean.setAccper(tempMap.get("ACCOUNT_PERIOD_QTR")==null?"":tempMap.get("ACCOUNT_PERIOD_QTR").toString());
				bean.setAccountDate(tempMap.get("ACCOUNT_PERIOD_DATE")==null?"":tempMap.get("ACCOUNT_PERIOD_DATE").toString());
				bean.setMovementType(tempMap.get("REPORT_TYPE")==null?"Interim":tempMap.get("REPORT_TYPE").toString().equalsIgnoreCase("F")?"Final":"Intreim");
			}
			
			/*list=this.mytemplate.query(query,args,new RowMapper() {
				public Object mapRow(ResultSet move, int rowNum) throws SQLException {
					bean repBean=new bean();	
					repBean.setSNo(move.getString("MOVMENT_TRANID")==null?"":move.getString("MOVMENT_TRANID"));
					repBean.setAccount_Period(move.getString("DETAIL_NAME")==null?"":move.getString("DETAIL_NAME"));
					repBean.setAccper(move.getString("ACCOUNT_PERIOD_QTR")==null?"":move.getString("ACCOUNT_PERIOD_QTR"));
					repBean.setAccountDate(move.getString("ACCOUNT_PERIOD_DATE")==null?"":move.getString("ACCOUNT_PERIOD_DATE"));
					repBean.setMovementType(move.getString("REPORT_TYPE")==null?"Intreim":move.getString("REPORT_TYPE").equalsIgnoreCase("F")?"Final":"Intreim");
					return repBean;
				}
			});*/
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getPipelineMovementJvDetails(
			ReportsBean bean) {
		Object[] args;
		String query="";
		args=new Object[8];
		args[0]=bean.getMovementId();
		args[1]=bean.getBranchCode();
		args[2]=bean.getProductId();
		args[3]=bean.getSpc();
		args[4]=bean.getCurrencyId();
		args[5]=bean.getUwYear();
		args[6]=bean.getBranchCode();
		args[7]=bean.getBranchCode();
		query=getQuery("PINELINE_MOVEMENT_JV_CRNT_DETAILS");
		logger.info("Query=>"+query);
		logger.info("args[]=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setAccountDate(tempMap.get("REPORT_DATE")==null?"":tempMap.get("REPORT_DATE").toString());
			bean.setSpcName(tempMap.get("TMAS_SPFC_NAME")==null?"":tempMap.get("TMAS_SPFC_NAME").toString());
			bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
			bean.setProductName(tempMap.get("TMAS_PRODUCT_NAME")==null?"":tempMap.get("TMAS_PRODUCT_NAME").toString());
			bean.setStart_date(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
			bean.setEnd_date(tempMap.get("EXPIRY_DATE")==null?"":tempMap.get("EXPIRY_DATE").toString());

			
			bean.setInwrdPiplinUnearnPremiumD(tempMap.get("INWARDPIPEUNEARN_DEBIT")==null?"":tempMap.get("INWARDPIPEUNEARN_DEBIT").toString());
			bean.setInwrdPiplinDefAcqCostBsD(tempMap.get("INWARDPIPEUPRB_S_DEBIT")==null?"":tempMap.get("INWARDPIPEUPRB_S_DEBIT").toString());
			
			bean.setInwrdPiplinDefAcqCostC(tempMap.get("INWARDPIPEUNEARNACQCOST_CREDIT")==null?"":tempMap.get("INWARDPIPEUNEARNACQCOST_CREDIT").toString());
			bean.setInwrdPiplinUprBsC(tempMap.get("INWARDPIPEUPRB_S_CREDIT")==null?"":tempMap.get("INWARDPIPEUPRB_S_CREDIT").toString());
			
			
			bean.setDebitTotal(Double.parseDouble(bean.getInwrdPiplinDefAcqCostBsD()) + 
					Double.parseDouble(bean.getInwrdPiplinUnearnPremiumD()));
			bean.setCreditTotal(Double.parseDouble(bean.getInwrdPiplinDefAcqCostC()) + 
					Double.parseDouble(bean.getInwrdPiplinUprBsC()));
		}
		return list;
	}

	public List<Map<String, Object>> getBookedPremiumDetails(ReportsBean bean) {
		Object[] args;
		String query="";
		args=new Object[7];
		args[0]=bean.getMovementId();
		args[1]=bean.getProductId();
		args[2]=bean.getSpc();
		args[3]=bean.getCurrencyId();
		args[4]=bean.getUwYear();
		args[5]=bean.getBranchCode();
		args[6]=bean.getBranchCode();
		query=getQuery("BOOKED_PREMIUM_DETAILS");
		logger.info("Query=>"+query);
		logger.info("args[]=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setAccountDate(tempMap.get("REPORT_DATE")==null?"0":tempMap.get("REPORT_DATE").toString());
			bean.setSpcName(tempMap.get("TMAS_SPFC_NAME")==null?"0":tempMap.get("TMAS_SPFC_NAME").toString());
			bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"0":tempMap.get("CURRENCY_NAME").toString());
			bean.setProductName(tempMap.get("TMAS_PRODUCT_NAME")==null?"":tempMap.get("TMAS_PRODUCT_NAME").toString());
			bean.setStart_date(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
			bean.setEnd_date(tempMap.get("EXPIRY_DATE")==null?"":tempMap.get("EXPIRY_DATE").toString());
			
			bean.setGrsPremiumC(tempMap.get("GWPI_DC")==null?"":tempMap.get("GWPI_DC").toString());
			
			bean.setGrsPiplinPremiumD(tempMap.get("PREMIUM_DC")==null?"0":tempMap.get("PREMIUM_DC").toString());
			
			bean.setGrsAcqCostD(tempMap.get("GROSS_ACQUISTION_COST_DC")==null?"0":tempMap.get("GROSS_ACQUISTION_COST_DC").toString());
			
			bean.setGrsPiplinAcqCostC(tempMap.get("GROSS_PIPELINE_ACQUTION_COST")==null?"0":tempMap.get("GROSS_PIPELINE_ACQUTION_COST").toString());
			
			bean.setPreResRetainD(tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC")==null?"0":tempMap.get("PREMIUM_DEPOSIT_RETAINED_DC").toString());
		
			bean.setPreResReleaseC(tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC")==null?"0":tempMap.get("PREMIUM_DEPOSIT_RELEASED_DC").toString());
			
			bean.setLossResRetainD(tempMap.get("CLAIM_DEPOSIT_RETAINED_DC")==null?"0":tempMap.get("CLAIM_DEPOSIT_RETAINED_DC").toString());
			
			bean.setLossResReleaseC(tempMap.get("CLAIM_DEPOSIT_RELEASED_DC")==null?"0":tempMap.get("CLAIM_DEPOSIT_RELEASED_DC").toString());
			
			bean.setBkdPreInterestC(tempMap.get("INTEREST_ON_DEPOSIT_DC")==null?"0":tempMap.get("INTEREST_ON_DEPOSIT_DC").toString());
			
			bean.setGrsClaimsPaidD(tempMap.get("GROSS_CLAIMS_PAID_DC")==null?"0":tempMap.get("GROSS_CLAIMS_PAID_DC").toString());
			
			//bean.setBusiPartInwrdNetAcD(tempMap.get("PIPELINED_NET_AC")==null?"0":tempMap.get("PIPELINED_NET_AC").toString());
			
			bean.setPiplinNetAcC(tempMap.get("PIPELINED_NET_AC")==null?"0":tempMap.get("PIPELINED_NET_AC").toString());
			
			bean.setCreditTotal(Double.parseDouble(bean.getGrsPremiumC()) + Double.parseDouble(bean.getGrsPiplinAcqCostC()) +
					Double.parseDouble(bean.getPreResReleaseC()) + Double.parseDouble(bean.getLossResReleaseC()) + 
					Double.parseDouble(bean.getBkdPreInterestC())+ Double.parseDouble(bean.getPiplinNetAcC()));
			
			Double businessPratInward =  (Double.parseDouble(bean.getGrsPremiumC()) + Double.parseDouble(bean.getGrsPiplinAcqCostC()) +
											Double.parseDouble(bean.getPreResReleaseC()) + Double.parseDouble(bean.getLossResReleaseC()) + 
											Double.parseDouble(bean.getBkdPreInterestC())+ Double.parseDouble(bean.getPiplinNetAcC()))
											- 
										(Double.parseDouble(bean.getGrsPiplinPremiumD()) + Double.parseDouble(bean.getGrsAcqCostD()) +
												Double.parseDouble(bean.getPreResRetainD()) + Double.parseDouble(bean.getLossResRetainD()) + 
												Double.parseDouble(bean.getGrsClaimsPaidD()));
			
			bean.setBusiPartInwrdNetAcD( businessPratInward.toString());
			
			bean.setDebitTotal(Double.parseDouble(bean.getGrsPiplinPremiumD()) + Double.parseDouble(bean.getGrsAcqCostD()) +
					Double.parseDouble(bean.getPreResRetainD()) + Double.parseDouble(bean.getLossResRetainD()) + 
					Double.parseDouble(bean.getGrsClaimsPaidD()) + Double.parseDouble(bean.getBusiPartInwrdNetAcD()));
			
			
			
			
		} 
		return list;
	}

	public List<Map<String, Object>> getBookedUprDetails(ReportsBean bean) {
		Object[] args;
		String query="";
		args=new Object[8];
		args[0]=bean.getMovementId();
		args[1]=bean.getBranchCode();
		args[2]=bean.getProductId();
		args[3]=bean.getSpc();
		args[4]=bean.getCurrencyId();
		args[5]=bean.getUwYear();
		args[6]=bean.getBranchCode();
		args[7]=bean.getBranchCode();
		query=getQuery("BOOKED_UPR_DETAILS");
		logger.info("Query=>"+query);
		logger.info("args[]=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setAccountDate(tempMap.get("REPORT_DATE")==null?"":tempMap.get("REPORT_DATE").toString());
			bean.setSpcName(tempMap.get("TMAS_SPFC_NAME")==null?"":tempMap.get("TMAS_SPFC_NAME").toString());
			bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
			bean.setProductName(tempMap.get("TMAS_PRODUCT_NAME")==null?"":tempMap.get("TMAS_PRODUCT_NAME").toString());
			bean.setStart_date(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
			bean.setEnd_date(tempMap.get("EXPIRY_DATE")==null?"":tempMap.get("EXPIRY_DATE").toString());

			
			bean.setBkdUprD(tempMap.get("BOOKED_UPR_DEBIT")==null?"":tempMap.get("BOOKED_UPR_DEBIT").toString());
			bean.setInwrdBkdDefAcqCostBsD(tempMap.get("INWARD_BOOK_DACQ_BS_DEBIT")==null?"":tempMap.get("INWARD_BOOK_DACQ_BS_DEBIT").toString());
			bean.setPiplinAcqCostD(tempMap.get("PIPELINED_ACQ_COST")==null?"":tempMap.get("PIPELINED_ACQ_COST").toString());
			bean.setInwrdPiplinUprBSD(tempMap.get("INWARD_PIPELINE_UPR_BS_DEBIT")==null?"":tempMap.get("INWARD_PIPELINE_UPR_BS_DEBIT").toString());
			
			bean.setBkdDefAcqCostC(tempMap.get("BOOK_DEFFE_ACQUI_COST_CREDIT")==null?"":tempMap.get("BOOK_DEFFE_ACQUI_COST_CREDIT").toString());
			bean.setInwrdBkdUprBsC(tempMap.get("INWARD_BOOKED_UPR_BS_CREDIT")==null?"":tempMap.get("INWARD_BOOKED_UPR_BS_CREDIT").toString());			
			bean.setPiplinUprC(tempMap.get("PIPELINED_UPR_CREDIT")==null?"":tempMap.get("PIPELINED_UPR_CREDIT").toString());			
			bean.setInwrdPiplinDefAcqCostBsC(tempMap.get("INWARD_PIPE_DACQ_COST_CREDIT")==null?"":tempMap.get("INWARD_PIPE_DACQ_COST_CREDIT").toString());
			
			bean.setDebitTotal(Double.parseDouble(bean.getBkdUprD()) + Double.parseDouble(bean.getInwrdBkdDefAcqCostBsD()) + 
					Double.parseDouble(bean.getPiplinAcqCostD()) + Double.parseDouble(bean.getInwrdPiplinUprBSD()));
			
			bean.setCreditTotal(Double.parseDouble(bean.getBkdDefAcqCostC()) + Double.parseDouble(bean.getInwrdBkdUprBsC()) +
					Double.parseDouble(bean.getPiplinUprC()) + Double.parseDouble(bean.getInwrdPiplinDefAcqCostBsC()));
		}
		return list;		
	}
		
	public List<Map<String, Object>> getPipelinedWrtnDetails(ReportsBean bean) {
		Object[] args;
		String query="";
		args=new Object[7];
		args[0]=bean.getMovementId();
		args[1]=bean.getBranchCode();
		args[2]=bean.getBranchCode();
		args[3]=bean.getProductId();
		args[4]=bean.getCurrencyId();
		args[5]=bean.getUwYear();
		args[6]=bean.getSpc();		
		query=getQuery("PIPELINE_WRITTEN_DETAILS");
		logger.info("Query=>"+query);
		logger.info("args[]=>"+StringUtils.join(args,","));
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		list = this.mytemplate.queryForList(query,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setAccountDate(tempMap.get("REPORT_DATE")==null?"":tempMap.get("REPORT_DATE").toString());
			bean.setSpcName(tempMap.get("TMAS_SPFC_NAME")==null?"":tempMap.get("TMAS_SPFC_NAME").toString());
			bean.setUwYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			bean.setCurrencyname(tempMap.get("CURRENCY_NAME")==null?"":tempMap.get("CURRENCY_NAME").toString());
			bean.setProductName(tempMap.get("TMAS_PRODUCT_NAME")==null?"":tempMap.get("TMAS_PRODUCT_NAME").toString());
			
			bean.setPiplinPremiumC(tempMap.get("PIPELINED_GWPI_DC")==null?"":tempMap.get("PIPELINED_GWPI_DC").toString());
			bean.setPiplinAcqCostD(tempMap.get("PIPELINED_ACQUISTION_DC")==null?"":tempMap.get("PIPELINED_ACQUISTION_DC").toString());
			bean.setPiplinNetAcD(tempMap.get("PIPELINED_NETPREMIUM")==null?"":tempMap.get("PIPELINED_NETPREMIUM").toString());
			
			bean.setDebitTotal(Double.parseDouble(bean.getPiplinAcqCostD()) + Double.parseDouble(bean.getPiplinNetAcD()));
			
			bean.setCreditTotal(Double.parseDouble(bean.getPiplinPremiumC()));
			
			
		}
		return list;
	}

	public String callProBookedPremium(ReportsBean bean) {
		try {
			String query=getQuery("JOURNAL_REPORT_PROCEDURE");
			/*Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getType(),bean.getMovementType(),bean.getProduct()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+bean.getProduct());
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);*/
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+bean.getProduct());

			SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withProcedureName(query);
			Map<String,Object> inputValues=new HashMap<String, Object>();
			inputValues.put("pvBranch", bean.getBranchCode());
			inputValues.put("PvReport", bean.getReportDate());
			inputValues.put("Pvtype", bean.getType());
			inputValues.put("Pvtrimtype", bean.getMovementType());
			inputValues.put("Pvproduct", bean.getProduct());			
			Map<String,Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
			bean.setResult(outputValues.get("PVERROR")==null?"":outputValues.get("PVERROR").toString()) ;
			logger.info("Output =>"+bean.getResult());
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String callProBookedUpr(ReportsBean bean) {
		try {
			String query=getQuery("JOURNAL_REPORT_PROCEDURE");
			/*Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getType(),bean.getMovementType(),""};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+"");
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);*/
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+bean.getProduct());

			SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withProcedureName(query);
			Map<String,Object> inputValues=new HashMap<String, Object>();
			inputValues.put("pvBranch", bean.getBranchCode());
			inputValues.put("PvReport", bean.getReportDate());
			inputValues.put("Pvtype", bean.getType());
			inputValues.put("Pvtrimtype", bean.getMovementType());
			inputValues.put("Pvproduct", bean.getProduct());			
			Map<String,Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
			bean.setResult(outputValues.get("PVERROR")==null?"":outputValues.get("PVERROR").toString()) ;
			logger.info("Output =>"+bean.getResult());
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String callPipelineMoveJv(ReportsBean bean) {
		try {
			String query=getQuery("JOURNAL_REPORT_PROCEDURE");
			/*Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getType(),bean.getMovementType(),""};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+"");
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);*/
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+"");

			SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withProcedureName(query);
			Map<String,Object> inputValues=new HashMap<String, Object>();
			inputValues.put("pvBranch", bean.getBranchCode());
			inputValues.put("PvReport", bean.getReportDate());
			inputValues.put("Pvtype", bean.getType());
			inputValues.put("Pvtrimtype", bean.getMovementType());
			inputValues.put("Pvproduct", bean.getProduct());			
			Map<String,Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
			bean.setResult(outputValues.get("PVERROR")==null?"":outputValues.get("PVERROR").toString()) ;
			logger.info("Output =>"+bean.getResult());
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String callProPipelineWritten(ReportsBean bean) {
		try {
			String query=getQuery("JOURNAL_REPORT_PIPELINE_WRITTEN");
			/*Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getType(),bean.getMovementType(),bean.getProduct()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+bean.getProduct());
			int result=this.mytemplate.update(query,args);
			logger.info("Result=>"+result);*/
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getType()+","+bean.getMovementType()+","+bean.getProduct());

			SimpleJdbcCall procedure=new SimpleJdbcCall(this.mytemplate.getDataSource()).withProcedureName(query);
			Map<String,Object> inputValues=new HashMap<String, Object>();
			inputValues.put("pvBranch", bean.getBranchCode());
			inputValues.put("PvReport", bean.getReportDate());
			inputValues.put("Pvtype", bean.getType());
			inputValues.put("Pvtrimtype", bean.getMovementType());
			inputValues.put("Pvproduct", bean.getProduct());			
			Map<String,Object> outputValues = procedure.execute(new MapSqlParameterSource(inputValues)); //Executes the procedure.
			bean.setResult(outputValues.get("PVERROR")==null?"":outputValues.get("PVERROR").toString()) ;
			logger.info("Output =>"+bean.getResult());
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public List<Map<String, Object>> getBookedUprInit(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			Object[] args = null;
			args=new Object[1];			
			args[0]=bean.getBranchCode();			
			query=getQuery("BOOKED_UPR_INIT");
			logger.info("Query=>"+query);
			logger.info("args[0][1] [2]=>"+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getBookedPremiumInit(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			Object[] args = null;
			args=new Object[1];			
			args[0]=bean.getBranchCode();			
			query=getQuery("BOOKED_PREMIUM_INIT");
			logger.info("Query=>"+query);
			logger.info("args[0][1] [2]=>"+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getPipelineWrittenInit(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			Object[] args = null;
			args=new Object[1];			
			args[0]=bean.getBranchCode();			
			query=getQuery("PIPELIEN_WRITTEN_INIT");
			logger.info("Query=>"+query);
			logger.info("args[0][1] [2]=>"+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public String checkProBookedUpr(ReportsBean bean) {
		try {
			String query=getQuery("BOOKED_UPR_REPORT_CHECK");
			Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getMovementType());			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setCount(tempMap.get("COUNTS")==null?"":tempMap.get("COUNTS").toString());
			}
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String checkProPipelineWrtn(ReportsBean bean) {
		try {
			String query=getQuery("PIPELIEN_WRITTEN_CHECK");
			Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getMovementType());			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setCount(tempMap.get("COUNTS")==null?"":tempMap.get("COUNTS").toString());
			}
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}

	public String checkProBookedPremium(ReportsBean bean) {
		try {
			String query=getQuery("BOOKED_PREMIUM_CHECK");
			Object[] args=new Object[] {bean.getBranchCode(),bean.getReportDate(),bean.getMovementType()};
			logger.info("Query=>"+query);
			logger.info("args[]=>"+bean.getBranchCode()+","+bean.getReportDate()+","+bean.getMovementType());			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				Map<String,Object> tempMap = list.get(0);
				bean.setCount(tempMap.get("COUNTS")==null?"":tempMap.get("COUNTS").toString());
			}
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "";
	}
	public List<Map<String, Object>> getallocationReportList(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[7];
			String query="";
			args[0]=bean.getStart_date();
			args[1]=bean.getEnd_date();
			args[2]=bean.getSettlementType();
			args[3]=bean.getBrokerId();
			args[4]=bean.getCedingId();
			args[5]=bean.getAllocateStatus();
			args[6]=bean.getBranchCode();
			if("-1".equals(bean.getBrokerId())){
				args[3]="ALL";
			}
			if("-1".equals(bean.getCedingId())){
				args[4]="ALL";
			}
			
			query=getQuery(DBConstants.ALLOCATION_REPORT_LIST);
			logger.info("Query=>"+query);
			logger.info("args=>"+StringUtils.join(args,","));
			list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, Object>> getPayRecRegisterList(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] args=new Object[8];
			String query="";
			args[0]=bean.getBranchCode();
			args[1]=bean.getStart_date();
			args[2]=bean.getEnd_date();
			if("-1".equals(bean.getCedingId())){
				args[3]="ALL";
			}
			if("-1".equals(bean.getBrokerId())){
				args[4]="ALL";
			}
			args[5]=bean.getPayrecType();
			args[6]=bean.getTransactionType();
			args[7]=bean.getShowAllFields();
			
			/*args[0]=bean.getBranchCode();
			args[1]=bean.getStart_date();
			args[2]=bean.getEnd_date();
			args[3]=bean.getCedingId();
			args[4]=bean.getCedingId();
			args[5]=bean.getCedingId();
			args[6]=bean.getBrokerId();
			args[7]=bean.getBrokerId();
			args[8]=bean.getBrokerId();
			args[9]=bean.getPayrecType();
			args[10]=bean.getPayrecType();
			args[11]=bean.getPayrecType();
			
			args[12]=bean.getTransactionType();
			args[13]=bean.getTransactionType();
			args[14]=bean.getTransactionType();
			
			if("-1".equals(bean.getCedingId())){
				args[3]="ALL";
				args[4]="ALL";
				args[5]="ALL";
			}
			if("-1".equals(bean.getBrokerId())){
				args[6]="ALL";
				args[7]="ALL";
				args[8]="ALL";
			}
			*/
			query=getQuery(DBConstants.PAYREC_REGISTER_REPORT_LIST);
			logger.info("Query=>"+query);
			logger.info(StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, Object>> getClaimPaidRegisterList(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] obj=new Object[8];
			String query="";
			obj[0]=bean.getProductId();
			obj[1]=bean.getBranchCode();
			if("-1".equals(bean.getLoginId())){
				obj[2]="ALL";
			}else{
				obj[2]=	bean.getLoginId();
			}
			if("-1".equals(bean.getBrokerId())){
				obj[3]="ALL";
			}else{
				obj[3]=	bean.getBrokerId();
			}
			if("-1".equals(bean.getCedingId())){
				obj[4]="ALL";
			}else{
				obj[4]=	bean.getCedingId();
			}
			if("-1".equals(bean.getUwYear())){
				obj[5]="ALL";
			}else{
				obj[5]=	bean.getUwYear();
			}
			obj[6]=bean.getStart_date();
			obj[7]=bean.getEnd_date();
			
			/*if(!"-1".equals(bean.getLoginId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getLoginId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGLOGINID);	
			}
			if(!"-1".equals(bean.getBrokerId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getBrokerId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGBROKERID);		
			}
			if(!"-1".equals(bean.getCedingId())){
				obj=getIncObjectArray(obj,new Object[]{bean.getCedingId()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMREGCEDINGID);	
			}
			if(!"-1".equals(bean.getUwYear())){
				obj=getIncObjectArray(obj,new Object[]{bean.getUwYear()});
				query+=" "+getQuery(DBConstants.REPORT_SELECT_CLAIMUWYEAR);
			}*/
			query=getQuery(DBConstants.CLAIM_PAID_REPORT_LIST);
			logger.info("Query=>"+query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query,obj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getRetroRegisterList(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			Object[] obj=new Object[12];
			String query="";
			obj[0]=bean.getStart_date();
			obj[1]=bean.getEnd_date();
			obj[2]=bean.getBranchCode();
			if("-1".equals(bean.getBrokerId())){
				obj[3]="ALL";
				obj[4]="ALL";
				obj[5]="ALL";
			}else{
				obj[3]=	bean.getBrokerId();
				obj[4]=	bean.getBrokerId();
				obj[5]=	bean.getBrokerId();
			}
			if("-1".equals(bean.getCedingId())){
				obj[6]="ALL";
				obj[7]="ALL";
				obj[8]="ALL";
			}else{
				obj[6]=	bean.getCedingId();
				obj[7]=	bean.getCedingId();
				obj[8]=	bean.getCedingId();
			}
			if("-1".equals(bean.getUwYear())){
				obj[9]="ALL";
				obj[10]="ALL";
				obj[11]="ALL";
			}else{
				obj[9]=	bean.getUwYear();
				obj[10]=bean.getUwYear();
				obj[11]=bean.getUwYear();
			}
			query=getQuery(DBConstants.RETRO_REPORT_LIST);
			logger.info("Query=>"+query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query,obj);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getJVReports(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String query = "";
			//String sampledate[]=bean.getOpenperiod().split("-");
			//String startDate=sampledate[0];
			//String endDate=sampledate[1];
			
			
			
			
			Object[] obj = new Object[5];
			
			
			obj[0]=bean.getBranchCode();
			obj[1]=bean.getStartDate();
			obj[2]=bean.getEndDate();
			obj[3]=bean.getJournalType();
			obj[4]=bean.getStatus();
			query = getQuery("GET_JV_REPORT");
			/*if("27".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="PREMIUM_JV";
			}else if("28".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="CLAIM_JV";
			}else if("29".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="TREASURY_JV1";
			}else if("30".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="TREASURY_JV2";
			}else if("46".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="PIPELINE_JV";
			}else if("47".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="OUTSTANDING_JV";
			}else if("48".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="PIPELINE_UPR_JV";
			}else if("49".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="BOOKED_UPR_JV";
			}else if("50".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="ADJUSTMENT_JV";
			}else if("51".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="RETRO_PR_JV";
			}else if("52".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="RETRO_XL_JV";
			}else if("53".equalsIgnoreCase(bean.getTypeId())){
				obj[3]="MANUAL_JV";
			}*/
			
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getTreatyWithdrawRegisterList(
			ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[6];
			String query = "";
			if ("-1".equals(bean.getCedingId())) {
				obj[0] = "ALL";
			} else {
				obj[0] = bean.getCedingId();
			}
			if ("-1".equals(bean.getBrokerId())) {
				obj[1] = "ALL";
			} else {
				obj[1] = bean.getBrokerId();
			}
			if ("-1".equals(bean.getUwYear())) {
				obj[2] = "ALL";
			} else {
				obj[2] = bean.getUwYear();
			}
			if ("-1".equals(bean.getTreatyMainClass())) {
				obj[3] = "ALL";
			} else {
				obj[3] = bean.getTreatyMainClass();
			}
			if ("-1".equals(bean.getTreatyType())) {
				obj[4] = "ALL";
			} else {
				obj[5] = bean.getTreatyType();
			}
			obj[5] = bean.getEnd_date();
			query = getQuery(DBConstants.TREATY_WITHDRAW_REPORT_LIST);
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getInstallmentDueReport(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[6];
			String query = "";
			obj[0] = bean.getEnd_date();
			obj[1] = bean.getBranchCode();
			obj[2] = bean.getProductId();
			obj[3] = bean.getCedingId();
			obj[4] = bean.getBrokerId();
			obj[5] = bean.getAllocationYN();
			
			query = getQuery("GET_INSTALLMENT_DUE_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getTrailBalanceReport(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getStart_date();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("GET_TRAIL_BALANCE_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getPortfolioOutPendingReport(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getStart_date();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("PORTFOLIO_OUT_PENDING_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getPptySOAPendingReport(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getStart_date();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("GET_PPTY_SOA_PENDING_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getUWStatisticsReport(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String query ="";
		Object args[]=null;
		try {
			query=getQuery("GET_RENEWAL_STATISTICS_REPORT");

			args=new Object[7];
			args[0] = bean.getPeriodFrom();
			args[1] = bean.getPeriodTo();
			args[2] = bean.getUwFrom();
			args[3] = bean.getUwTo();
			args[4] = bean.getContractNo();
			args[5] = bean.getBranchCode();
			args[6] = bean.getType();
			logger.info("Query==>"+query);
			logger.info("Args==>"+StringUtils.join(args,","));
			list=this.mytemplate.queryForList(query,args);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getIEReport(ReportsBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String query ="";
		Object args[]=null;
		try {
			query=getQuery("GET_UW_YEAR");
			args = new Object[3];
			args[0]=bean.getProposalNo();
			args[1] = bean.getContractNo();
			args[2] = bean.getLayerNo();
			String uwYear = this.mytemplate.queryForObject(query,args,String.class);
			query=getQuery("GET_IE_REPORT");
			args=new Object[6];
			args[0] = bean.getStart_date();
			args[1] = bean.getEnd_date();
			args[2] = bean.getContractNo();
			args[3] = bean.getLayerNo();
			args[4] = bean.getBranchCode();
			args[5] = uwYear;
			logger.info("Query==>"+query);
			logger.info("Args==>"+StringUtils.join(args,","));
			list=this.mytemplate.queryForList(query,args);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getPptyOSLRClaimReport(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getStart_date();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("GET_PPTY_OSLR_CLAIM_REPORT");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return list;
	}
	public String getStartDateStatus(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String status="";
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getStartDate();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("GET_OP_START_DATE_STATUS");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
			if(list!=null && list.size()>0){
				Map<String,Object>map=list.get(0);
				status=map.get("STATUS")==null?"":map.get("STATUS").toString();
			}
			
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return status;
	}
	public String getEndDateStatus(ReportsBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String status="";
		try {
			Object[] obj = new Object[2];
			String query = "";
			obj[0] = bean.getEndDate();
			obj[1] = bean.getBranchCode();
			
			query = getQuery("GET_OP_END_DATE_STATUS");
			logger.info("Query=>" + query);
			logger.info(StringUtils.join(obj, ","));
			list = this.mytemplate.queryForList(query, obj);
			if(list!=null && list.size()>0){
				Map<String,Object>map=list.get(0);
				status=map.get("STATUS")==null?"":map.get("STATUS").toString();
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return status;
	}
	
	
}