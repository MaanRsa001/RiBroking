package com.maan.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.PremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.PremiumDAO;

public class PremiumDaoImpl extends MyJdbcTemplate implements PremiumDAO{
	
	private static final Logger LOGGER = LogUtil.getLogger(ClaimDAOImpl.class);

	public List<PremiumBean> PremiumList(String branchCode,PremiumBean bean,String tableType) {
		String query="";
		Object arg[];
		List<Map<String, Object>> allocists=new ArrayList<Map<String, Object>>();
		List<PremiumBean> finalList = new ArrayList<PremiumBean>();
		LOGGER.info("Enter into PremiumList");
		try{
			if("Main".equalsIgnoreCase(tableType)){
			if(bean.getType().equalsIgnoreCase("premium")){
					query=getQuery(DBConstants.FULL_PREMIUM_LIST);
					arg=new Object[1];
					arg[0]=branchCode;
					//arg[1]=branchCode;
			}
			else{
					query=getQuery(DBConstants.PREMIUM_LIST);
					arg=new Object[1];
					arg[0]=branchCode;
			}
			}else{
				query=getQuery("PREMIUM_TEMP_LIST");
				arg=new Object[1];
				arg[0]=branchCode;
			}
			if("Main".equalsIgnoreCase(tableType)){
			if("S".equalsIgnoreCase(bean.getSearchType())){
				if(StringUtils.isNotBlank(bean.getCompanyNameSearch())){
					query +=" AND UPPER(CUSTOMER_NAME) LIKE UPPER('%"+bean.getCompanyNameSearch()+"%')";
				}
				if(StringUtils.isNotBlank(bean.getBrokerNameSearch())){
					query +=" AND UPPER(BROKER_NAME) LIKE UPPER('%"+bean.getBrokerNameSearch()+"%')";
				}
				if(StringUtils.isNotBlank(bean.getContractNoSearch())){
					query +=" AND RSK_CONTRACT_NO LIKE ('%"+bean.getContractNoSearch()+"%')";
				}
				if(StringUtils.isNotBlank(bean.getTransactionNoSearch())){
					query +=" AND TRANSACTION_NO LIKE ('%"+bean.getTransactionNoSearch()+"%')";
				}
				if(StringUtils.isNotBlank(bean.getTransactionDateSearch())){
					query +=" AND TRANSACTION_DATE LIKE ('%"+bean.getTransactionDateSearch()+"%')";
				}
				
				}else{
					bean.setCompanyNameSearch("");
					bean.setBrokerNameSearch("");
					bean.setTransactionNoSearch("");
					bean.setTransactionDateSearch("");
					bean.setContractNoSearch("");
				}	
			}else  if("Temp".equalsIgnoreCase(tableType)){
				if("ST".equalsIgnoreCase(bean.getSearchType())){
					if(StringUtils.isNotBlank(bean.getCompanyNameSearchTemp())){
						query +=" AND UPPER(CUSTOMER_NAME) LIKE UPPER('%"+bean.getCompanyNameSearchTemp()+"%')";
					}
					if(StringUtils.isNotBlank(bean.getBrokerNameSearchTemp())){
						query +=" AND UPPER(BROKER_NAME) LIKE UPPER('%"+bean.getBrokerNameSearchTemp()+"%')";
					}
					if(StringUtils.isNotBlank(bean.getContractNoSearchTemp())){
						query +=" AND RSK_CONTRACT_NO LIKE ('%"+bean.getContractNoSearchTemp()+"%')";
					}
					if(StringUtils.isNotBlank(bean.getTransactionNoSearchTemp())){
						query +=" AND REQUEST_NO LIKE ('%"+bean.getTransactionNoSearchTemp()+"%')";
					}
					if(StringUtils.isNotBlank(bean.getTransactionDateSearchTemp())){
						query +=" AND TRANSACTION_DATE LIKE ('%"+bean.getTransactionDateSearchTemp()+"%')";
					}
					
					}else{
						bean.setCompanyNameSearchTemp("");
						bean.setBrokerNameSearchTemp("");
						bean.setTransactionNoSearchTemp("");
						bean.setTransactionDateSearchTemp("");
						bean.setContractNoSearchTemp("");
					}
			}
		LOGGER.info("Select Query====>"+query);
		allocists=this.mytemplate.queryForList(query,arg);
		for(int i=0 ; i<allocists.size() ; i++) {
			Map<String,Object> tempMap = (Map<String,Object>) allocists.get(i);
			PremiumBean tempBean=new PremiumBean();
			tempBean.setProposal_No(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
			tempBean.setContractNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
			tempBean.setCeding_Company_Name(tempMap.get("Company_Name")==null?"":tempMap.get("Company_Name").toString());
			tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
			tempBean.setLayerno(tempMap.get("RSK_LAYER_NO")==null?"":tempMap.get("RSK_LAYER_NO").toString());
			tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
			tempBean.setTransactionType(tempMap.get("RSK_PRODUCTID")==null?"":tempMap.get("RSK_PRODUCTID").toString());
			tempBean.setTransDropDownVal(tempMap.get("REVERSE_TRANSACTION_NO")==null?"":tempMap.get("REVERSE_TRANSACTION_NO").toString());
			tempBean.setDept(tempMap.get("RSK_DEPTID")==null?"":tempMap.get("RSK_DEPTID").toString());
			tempBean.setRequestNo(tempMap.get("REQUEST_NO")==null?"":tempMap.get("REQUEST_NO").toString());
			if(tempBean.getTransactionType().equalsIgnoreCase("1")){
				tempBean.setTransactionTypeName("Facultative");
			}
			else if(tempBean.getTransactionType().equalsIgnoreCase("2")){
				tempBean.setTransactionTypeName("Proportional Treaty");
			}
			else if(tempBean.getTransactionType().equalsIgnoreCase("3")){
				tempBean.setTransactionTypeName("Non-Proportional Treaty");
			}
			else if(tempBean.getTransactionType().equalsIgnoreCase("5")){
				tempBean.setTransactionTypeName("Retro - Xol");
			}
			tempBean.setInsDate(tempMap.get("INS_DATE")==null?"":tempMap.get("INS_DATE").toString());
			tempBean.setExpDate(tempMap.get("EXP_DATE")==null?"":tempMap.get("EXP_DATE").toString());
			//tempBean.setAccount_Period(tempMap.get("ACC_PER")==null?"":tempMap.get("ACC_PER").toString());
			//if(Double.parseDouble(tempMap.get("ALLOC_AMT").toString())!=0)
			//	tempBean.setEndtYN("Yes");
			//else
			//	tempBean.setEndtYN("No");
			//tempBean.setProductId("2");
			tempBean.setInception_Date(tempMap.get("INS_DATE")==null?"":tempMap.get("INS_DATE").toString());
			//tempBean.setMovementYN(tempMap.get("MOVEMENT_YN")==null?"":tempMap.get("MOVEMENT_YN").toString());
			//tempBean.setSettlement_Status(tempMap.get("SETTLEMENT_STATUS")==null?"":tempMap.get("SETTLEMENT_STATUS").toString());
			tempBean.setTransactionDate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
			int count= new DropDownControllor().Validatethree(bean.getBranchCode(), tempBean.getTransactionDate());
			if("Main".equalsIgnoreCase(tableType)){
					if((StringUtils.isNotBlank(bean.getOpstartDate()))&& (StringUtils.isNotBlank(bean.getOpendDate()))){
						if(count ==0){
							tempBean.setTransOpenperiodStatus("N");
						}else
						{
							tempBean.setTransOpenperiodStatus("Y");
						}
						}
						tempBean.setAllocatedYN((String)this.mytemplate.queryForObject("select Decode (count(*),0,'Y','N') allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=? and TYPE='P' and STATUS='Y'",new Object[]{tempBean.getContractNo(),tempBean.getTransactionNo(),tempBean.getLayerno()},String.class));
						Object args2[]=new String[1];
						args2[0]=tempBean.getTransactionNo();
						query=getQuery(DBConstants.ALLOCATION_STATUS_COMPARITION);
						int allocationstatus=this.mytemplate.queryForInt(query,args2);
						query=getQuery(DBConstants.RETRO_PRCL_STATUS_COMPARITION);
						int retroPrclStatus=this.mytemplate.queryForInt(query,args2);
						int retroPrclStatus1=0;
						if(retroPrclStatus!=0){
						query=getQuery(DBConstants.RETRO_PRCL_STATUS_COMPARITION1);
						retroPrclStatus1=this.mytemplate.queryForInt(query,args2);
						}
						
						if(count!=0 && allocationstatus ==0 &&  retroPrclStatus1 ==0 ){
							tempBean.setDeleteStatus("Y");
						}
						if(StringUtils.isNotBlank(tempBean.getProposal_No()) ){
							tempBean.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where PROPOSAL_NO=? and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.PROPOSAL_NO=pm.PROPOSAL_NO )", new Object[]{tempBean.getProposal_No()}, String.class));
					}
			}
			finalList.add(tempBean);
		}
		
		}catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>");
		return finalList;
	}

	public String getOpenPeriod(PremiumBean bean) {
		String query=getQuery("GET_OPEN_PERIOD_DATE");
		Object[] args = new String[1];
		args[0]=bean.getBranchCode();
		LOGGER.info("Select Query ==> " + query);
		List<Map<String,Object>> list=this.mytemplate.queryForList(query,args);
		for(int i=0 ; i<list.size() ; i++) {
			Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
			bean.setOpstartDate(tempMap.get("start_date")==null?"":tempMap.get("start_date").toString());
			bean.setOpendDate(tempMap.get("end_date")==null?"":tempMap.get("end_date").toString());
		}
		return null;
	}

	public List<Map<String, Object>> productIdList(PremiumBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		LOGGER.info("Enter ino product List");
		try{
		String query=getQuery("GET_PREM_PRODUC_ID_LIST");
		Object[] args = new String[1];
		args[0]=bean.getBranchCode();
		
		LOGGER.info("Select Query ==> " + query);
		list=this.mytemplate.queryForList(query,args);
		
		}
		catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("Exit ino product List");
		return list;
	}

	public List<PremiumBean> contractidetifierlist(PremiumBean bean) {
		String query="";
		List<Map<String, Object>> allocists=new ArrayList<Map<String, Object>>();
		List<PremiumBean> finalList = new ArrayList<PremiumBean>();
		LOGGER.info("Enter into PremiumList");
		try{
			
		query=getQuery(DBConstants.CONTRACT_IDENTIFIER_LIST);
		LOGGER.info("Select Query====>"+query);
		Object arg[]=new Object[8];
		arg[0]=bean.getTransactionType();
		arg[1]=bean.getTransactionType();
		arg[2]=bean.getBranchCode();
		arg[3]=bean.getBranchCode();
		arg[4]=bean.getBranchCode();
		arg[5]=bean.getBranchCode();
		arg[6]=bean.getTransactionType();
		arg[7]=bean.getBranchCode();
		if(!"N".equalsIgnoreCase(bean.getCeding_Company_Name())&&!"".equalsIgnoreCase(bean.getCeding_Company_Name())){
			query +="AND D.RSK_CEDINGID = "+bean.getCeding_Company_Name()+"";
		}
		if(!"N".equalsIgnoreCase(bean.getBroker())&&!"".equalsIgnoreCase(bean.getBroker())){
			query +=" AND D.RSK_BROKERID ="+bean.getBroker()+"";
		}
		if(!"N".equalsIgnoreCase(bean.getUnderwritingYear())&&!"".equalsIgnoreCase(bean.getUnderwritingYear())){
			query +="AND D.RSK_UWYEAR ="+bean.getUnderwritingYear()+"";
		}
		if(!"N".equalsIgnoreCase(bean.getDept())&&!"".equalsIgnoreCase(bean.getDept())){
			query +="AND D.RSK_DEPTID="+bean.getDept()+"";
		}
		query +="ORDER BY A.CONTRACT_NO DESC";
		allocists=this.mytemplate.queryForList(query,arg);
		for(int i=0 ; i<allocists.size() ; i++) {
			Map<String,Object> tempMap = (Map<String,Object>) allocists.get(i);
			PremiumBean tempBean=new PremiumBean();
			tempBean.setProposal_No(tempMap.get("PROPOSAL_NO")==null?"":tempMap.get("PROPOSAL_NO").toString());
			tempBean.setContractNo(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
			tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
			tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
			tempBean.setLayerno(tempMap.get("LAYER_NO")==null?"":tempMap.get("LAYER_NO").toString());
			tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
			tempBean.setTransactionType(bean.getTransactionType());
			tempBean.setDept(tempMap.get("TMAS_DEPARTMENT_NAME")==null?"":tempMap.get("TMAS_DEPARTMENT_NAME").toString());
			tempBean.setExpDate(tempMap.get("EXPIRY_DATE")==null?"":tempMap.get("EXPIRY_DATE").toString());
			tempBean.setInception_Date(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
			tempBean.setTransactionDate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
			tempBean.setUnderwritingYear(tempMap.get("UW_YEAR")==null?"":tempMap.get("UW_YEAR").toString());
			tempBean.setUnderwriter(tempMap.get("UNDERWRITTER")==null?"":tempMap.get("UNDERWRITTER").toString());
			tempBean.setOld_Contract(tempMap.get("OLD_CONTRACTNO")==null?"":tempMap.get("OLD_CONTRACTNO").toString());
			tempBean.setDepartmentId(tempMap.get("DEPT_ID")==null?"":tempMap.get("DEPT_ID").toString());

			finalList.add(tempBean);
		}
		
		}catch(Exception e){
			LOGGER.debug("Exception "+e);
		}
		LOGGER.info("CliamBusinessImpl getClaimDates() || Exit Department Date=>");
		return finalList;
	}
	
	public void copyDatatoDeleteTable(PremiumBean bean) {
		String data="";
		String header="";
		
		String qry="";
		String[] Tables=new String[3];
		Tables[0]="RSK_PREMIUM_DETAILS";
		Tables[1]="RSK_PREMIUM_DETAILS_ARCHIVE";
		Tables[2]="TTRN_RETRO_PRCL";
		String[][] dataset=null;
		try {
			qry="select nvl(max(deleted_id),0)+1 from ttrn_deleted_transaction";
			String deletedId=(String) this.mytemplate.queryForObject(qry,String.class);
			for(int p=0;p<Tables.length;p++){
			qry="SELECT count(column_name) FROM USER_TAB_COLUMNS WHERE table_name = '"+Tables[p]+"'";
			int ColumnCount=this.mytemplate.queryForInt(qry);
			//ArrayList <String[]> result = new ArrayList<String[]>();
			Connection conn = this.mytemplate.getDataSource().getConnection();
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( "select * from  "+Tables[p]+" where transaction_no='"+bean.getTransactionNo()+"'");
			int columnCount = rs.getMetaData().getColumnCount();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next())
			{
				ArrayList <String[]> result = new ArrayList<String[]>();
				header="";
				
			    String[] row = new String[columnCount];
			    for (int i=0; i <columnCount ; i++){
			   
			    	header+=rsmd.getColumnName(i+1)+"|";
			    	
			       row[i] = rs.getString(i + 1);
			    }
			    result.add(row);
			    if (result.size()>0) {			
					for (int j=0;j<result.size();j++){
						dataset=result.toArray(new String[0][j]);
						 for(int l=0;l<ColumnCount;l++){
							 data+=dataset[0][l]+"|";
					 }
				    qry="insert into ttrn_deleted_transaction(Deleted_id,Deleted_data,DEL_TABLE,DEL_TAB_HEADER,DEL_BY,DEL_DATE)values('"+deletedId+"','"+data+"','"+Tables[p]+"','"+header+"','"+bean.getUserName()+"',to_date(sysdate,'dd/mm/yyyy'))";
				    LOGGER.info(data+" ,"+header+" ,"+Tables[p]+" ,"+bean.getUserName());
					this.mytemplate.update(qry);
					data="";
					}
				}
			}
			
			qry="delete  from "+Tables[p]+" where transaction_no='"+bean.getTransactionNo()+"'";
			this.mytemplate.update(qry);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}