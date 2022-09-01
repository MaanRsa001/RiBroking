package com.maan.dao;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.bean.FaculPremiumBean;
import com.maan.bean.HomeBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.login.LogInService;
import com.maan.common.util.LogUtil;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class CommonDAO extends MyJdbcTemplate{
	final org.slf4j.Logger logger = LogUtil.getLogger(CommonDAO.class);
	public List<Map<String,Object>> getProductList(final String branchCode) {
		List<Map<String,Object>> productList=null;
		try{
			logger.info("UserManipulationDAOImpl getManufactureMapNew || Enter ");
			String[] args=new String[1];
			String query=getQuery(DBConstants.USER_SELECT_PRODUCTMAP);
			args[0]=branchCode;
			logger.info("Select Query====>"+query);
			logger.info("Arg[0]====>"+args[0]);
			productList=this.mytemplate.queryForList(query,args);
		}catch (Exception e) {
           e.printStackTrace();
		}
		return productList;
	}
	public List<Map<String, Object>> getDepartmentList(String productId,
			String branchCode) {
		logger.info("CommonDAO getDepartmentList || Enter");
		List<Map<String,Object>> departmentList=null;
		try{
			String query=getQuery(DBConstants.USER_SELECT_DEPARTMENTMAP);
			Object[] args=new Object[2];
			args[0]=productId;
			args[1]=branchCode;
			logger.info("Select Query====>"+query);
			logger.info("Args==> " + StringUtils.join(args,","));
			departmentList=mytemplate.queryForList(query, args);
			logger.info("Result Size====>"+departmentList.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return departmentList;
	}
	public List<Map<String, Object>> getProcessList(String productId,String departmentId, String branchCode) {
		logger.info("CommonDAO getProcessList || Enter");
		List<Map<String,Object>> processList=null;
		try{
			String sql=getQuery(DBConstants.USER_SELECT_PROCESSMAP);
			Object[] args=new String[3];
			args[0]=productId;
			args[1]=departmentId;
			args[2]=branchCode;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			processList=mytemplate.queryForList(sql, args);
			logger.info("Result Size====>"+processList.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("CommonDAO getProcessList || Exit");
		return processList;
	}
	public List<Map<String, Object>> getFinalMenuList(Map<String, Object> session) {
		logger.info("CommonDAO getFinalMenuList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			String sql=getQuery(DBConstants.USER_SELECT_FINALMENULIST);
			String[] args=new String[3];
			args[0]=(String)session.get("UserId");
			args[1]=(String)session.get("processId");
			args[2]=(String)session.get("processId");
			logger.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("CommonDAO getFinalMenuList || Exit");
		return result;
	}
	public String getOldProductId(Map<String, Object> session) {
		logger.info("CommonDAO getOldProductId || Enter");
		String result="";
		try{
			String sql=getQuery(DBConstants.USER_SELECT_GETOLDPRODUCTID);
			String[] args=new String[5];
			args[0]=(String)session.get("mfrid");
			args[1]=(String)session.get("DepartmentId");
			args[2]=(String)session.get("processId");
			args[3]=(String)session.get("BRANCH_CODE");
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
		logger.info("CommonDAO getOldProductId || Exit");
		return result;
	}
	
	public List<Map<String, Object>> getDepartmentListByProduct(String productId, String branchCode) {
		logger.info("CommonDAO getDepartmentList || Enter");
		List<Map<String,Object>> departmentList=null;
		try{
			String query="SELECT TMAS_PRODUCT_ID,TMAS_DEPARTMENT_ID,TMAS_DEPARTMENT_NAME FROM TMAS_DEPARTMENT_MASTER WHERE TMAS_STATUS='H' AND TMAS_PRODUCT_ID=? AND BRANCH_CODE=?";
			Object[] args=new String[2];
			args[0]=productId;
			args[1]=branchCode;
			logger.info("Select Query====>"+query);
			logger.info("Args==> " + StringUtils.join(args,","));
			departmentList=mytemplate.queryForList(query, args);
			logger.info("Result Size====>"+departmentList.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return departmentList;
	}
	
	public List<Map<String, Object>> getProcessListByDepartment(String productId,String departmentId,String branchCode) {
		return null;
	}
	
	public List<Map<String,Object>> getFinalListByProcess(String processId) {
		return null;
	}
	
	public List<Map<String, Object>> getFinalMenuList(String userId,String processId) {
		logger.info("CommonDAO getFinalMenuList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			String sql=getQuery(DBConstants.USER_SELECT_FINALMENULIST);
			Object[] args=new String[3];
			args[0]=userId;
			args[1]=processId;
			args[2]=processId;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("CommonDAO getFinalMenuList || Exit");
		return result;
	}
	public List<Map<String, Object>> getMenuDropDownList(String branchCode, String userId) {
		logger.info("CommonDAO getMenuDropDownList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			//String sql=getQuery(DBConstants.USER_SELECT_DROPMENULIST);
			String sql="SELECT PRODUCT_ID||'~'||DEPT_ID||'~'||PROCESS_ID||'~'||MENU_NAME ||'~'||MENU_URL||'~'||(SELECT TMAS_PRODUCT_NAME FROM TMAS_PRODUCT_MASTER WHERE TMAS_PRODUCT_ID=PRODUCT_ID AND BRANCH_CODE=? AND  TMAS_STATUS='1' )||'~'||(SELECT TMAS_DEPARTMENT_NAME FROM TMAS_DEPARTMENT_MASTER WHERE TMAS_PRODUCT_ID =PRODUCT_ID AND TMAS_DEPARTMENT_ID=DEPT_ID  AND BRANCH_CODE=? AND  TMAS_STATUS = 'H' ) ||'~'||( SELECT    PROCESS_NAME FROM   TMAS_HOMEPAGE_MASTER WHERE   STATUS = 'Y' AND PRODUCT_ID = MM.PRODUCT_ID AND DEPT_ID = MM.DEPT_ID AND PROCESS_ID=MM.PROCESS_ID AND BRANCH_CODE = ?)||'~'||MENU_ID MENUKEY,(SELECT TMAS_PRODUCT_NAME FROM TMAS_PRODUCT_MASTER WHERE TMAS_PRODUCT_ID=PRODUCT_ID AND BRANCH_CODE=? AND  TMAS_STATUS='1' )||'>'||(SELECT TMAS_DEPARTMENT_NAME||'>' FROM TMAS_DEPARTMENT_MASTER WHERE TMAS_PRODUCT_ID =PRODUCT_ID AND TMAS_DEPARTMENT_ID=DEPT_ID  AND BRANCH_CODE=? AND  TMAS_STATUS = 'H' ) ||( SELECT    PROCESS_NAME FROM   TMAS_HOMEPAGE_MASTER WHERE   STATUS = 'Y' AND PRODUCT_ID = MM.PRODUCT_ID AND DEPT_ID = MM.DEPT_ID AND PROCESS_ID=MM.PROCESS_ID AND BRANCH_CODE = ?)||'>'||MENU_NAME MENU_LIST FROM MENU_MASTER MM WHERE TYPE = 'User' AND ACTIVE='1' and menu_id in (SELECT   REGEXP_SUBSTR ( MENU_IDS,'[^,]+', 1, LEVEL) AS MENU_ID FROM   (SELECT   to_char(listagg(MENU_IDS||',')) MENU_IDS FROM   TMAS_LOGIN_DETAILS WHERE   LOGIN_ID = ?  AND ACTIVE = '1') CONNECT BY   LEVEL <= LENGTH (MENU_IDS)  - LENGTH (REPLACE (MENU_IDS, ',', '')) + 1) ORDER BY PRODUCT_ID,DEPT_ID,PROCESS_ID  ,ORDER_NO";
			Object[] args=new String[7];
			args[0]=branchCode;
			args[1]=branchCode;
			args[2]=branchCode;
			args[3]=branchCode;
			args[4]=branchCode;
			args[5]=branchCode;
			args[6]=userId;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("CommonDAO getMenuDropDownList || Exit");
		return result;
	}
	public void insertSessionTrackingdetails(Map<String, Object> session, String sessionId) {
		try {
			if((String)session.get("productName")!=null){
			Object obj[]=new Object[8];
			obj[0]=(String)session.get("BRANCH_CODE");
			obj[1]=(String)session.get("UserId");
			obj[2]=sessionId;
			obj[3]=(String)session.get("productName");
			obj[4]=(String)session.get("departmentName");
			obj[5]=(String)session.get("processName");
			obj[6]=(String)session.get("menuName");
			obj[7]=(String)session.get("BRANCH_CODE");
			String query=getQuery("INSERT_TRACKING_DETAILS");
			this.mytemplate.update(query,obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String sendCashLossMail(List<Map<String, Object>> list, FaculPremiumBean bean) {
		List<Map<String, Object>>listinfo=null;
		LogInService service=new LogInService();
		Map<String, String> mapt=getMailDetails("51");
		listinfo=getMailCCInfo("CASH_LOSS",bean.getBranchCode());
		Map<String, String> details=new HashMap<String, String>();
		if(mapt != null && listinfo!=null && listinfo.size()>0){
			Map<String,Object>map=listinfo.get(0);
			String hostName=(String)mapt.get("SMTP_HOST");
			String user = mapt.get("SMTP_USER");
    		String pwd = mapt.get("SMTP_PWD");
    		String port=mapt.get("SMTP_PORT");
    		String mailform = mapt.get("SMTP_ADDRESS");
    		String shortAddress = mapt.get("SMTP_SHORT_ADDRESS");
    		String subject = "Cash Loss Credit Mail";
    		String toAddress = (String) map.get("EMAIL_TO");
    		String ccAddress = (String) map.get("EMAIL_CC");
    		
			details.put("TRANSACTION_NO", bean.getTransactionNo());
			details.put("LOGIN_ID", bean.getLoginId());
			details.put("CONTRACT_NO", bean.getContNo());
			details.put("TRANSACTION_DATE", bean.getTransaction());
			details.put("BRANCH_CODE", bean.getBranchCode());
			details.put("CLC_CURRENCY", bean.getCurrencyId());
			if(toAddress!=null && !"".equals(toAddress)){
	    		String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
	    		String[] ccAddresses = new String[0];
	    		if(ccAddress!=null && !"".equals(ccAddress)){
	    			ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
	    		}
			
			String msg=frameTemplate(details,list);
			service.sendResponseMail(hostName, user, pwd, mailform, subject, msg, toAddresses, ccAddresses, shortAddress,port);
			}
	}
		return null;
	}

	private String frameTemplate(Map<String, String> details, List<Map<String, Object>> list) {
		DecimalFormat d = new DecimalFormat("#,###");
		String messageContent="";
		try {
			String query=getQuery("GET_CURRENCY_NAME");
			String branchcode=details.get("BRANCH_CODE")==null?"":details.get("BRANCH_CODE").toString();
			messageContent="Dear Management,<br/><br/><br/>\r\n" + 
			"\r\n" + 
			"A transaction "+details.get("TRANSACTION_NO")+" has been processed by "+details.get("LOGIN_ID")+" on "+details.get("TRANSACTION_DATE")+" under contract number "+details.get("CONTRACT_NO")+".<br/>\r\n" + 
			"Below were the list of pending allocations in Cash Loss Credit module on the transaction date for contract "+details.get("CONTRACT_NO")+" (including previous risk's pending credits).<br/>\r\n" + 
			"<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<body>\r\n" + 
					"    <div style=\"width: 80%;\">\r\n" + 
					"        <p style=\"color: #003286;font: small/ 1.5 Arial,Helvetica,sans-serif;font-size: 25px;font-weight: bold;text-decoration: underline;\">Below were the list of pending allocations in Cash Loss Credit module on the transaction date for contract "+details.get("CONTRACT_NO")+" (including previous risk's pending credits).</p>\r\n" + 
					"        <table style=\"width:100%;border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">\r\n" + 
					"            <tr>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">S No</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Contract No</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Claim No</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Claim Payment No</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Paid Date</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">CLD Currency</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">CLD Amount</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">CLC Currency</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Credit Amount in CLC Currency</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Credit Amount in CLD Currency</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">CLC Settlement rate</th>\r\n" + 
					"                <th style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;background-color: #003286;color: white;font-weight: bold;\">Status</th>\r\n" + 
					"            </tr>\r\n" ;
			
			
			for(int i=0;i<list.size();i++) {
				Map<String,Object>map=list.get(i);
				String cldcurrencyName=(String)this.mytemplate.queryForObject(query, new Object[]{branchcode,map.get("CLDCURRENCY_ID")==null?"":map.get("CLDCURRENCY_ID").toString()},String.class);
				String clccurrencyName=(String)this.mytemplate.queryForObject(query, new Object[]{branchcode,details.get("CLC_CURRENCY")==null?"":details.get("CLC_CURRENCY").toString()},String.class);
				String status="";
				Double cldamount=map.get("CLD_AMOUNT")==null?0d:Double.parseDouble(map.get("CLD_AMOUNT").toString());
				Double creditcldamount=map.get("CREDITAMOUNTCLD")==null?0d:Double.parseDouble(map.get("CREDITAMOUNTCLD").toString());
				if(cldamount==creditcldamount) {
					status="Fully Credited";
				}else if(cldamount<creditcldamount) {
					status="Partially Credit";
				}else if(creditcldamount<=0) {
					status="Unallocated";
				}
					messageContent+="<tr>\r\n"+
				"				 <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(i+1)+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CONTRACT_NO")==null?"":map.get("CONTRACT_NO").toString())+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CLAIM_NO")==null?"":map.get("CLAIM_NO").toString())+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CLAIMPAYMENT_NO")==null?"":map.get("CLAIMPAYMENT_NO").toString())+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CREDITDATE")==null?"":map.get("CREDITDATE").toString())+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(cldcurrencyName)+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CLD_AMOUNT")==null?"":d.format(Double.parseDouble(map.get("CLD_AMOUNT").toString())))+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(clccurrencyName)+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CREDITAMOUNTCLC")==null?"":d.format(Double.parseDouble(map.get("CREDITAMOUNTCLC").toString())))+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("CREDITAMOUNTCLD")==null?"":d.format(Double.parseDouble(map.get("CREDITAMOUNTCLD").toString())))+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(map.get("EXCHANGE_RATE")==null?"":map.get("EXCHANGE_RATE").toString())+"</td>\r\n" + 
				"                <td style=\"border: 1px solid #003286; font: small/ 1.5 Arial,Helvetica,sans-serif;\">"+(status)+"</td>\r\n" + 
				"</tr>\r\n";
			}
			messageContent+="        </table>\r\n" + 
				"    </div>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>";
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return messageContent.toString();
	}


	private List<Map<String, Object>> getMailCCInfo(String type, String branchCode) {
    	logger.info("Enter into getMailCCInfo()");
    	List<Map<String, Object>>list=null;
    	try {
    		String  query=getQuery("GET_MAILCC_INFO");
    		 logger.info("query =>"+query);
        	 list =this.mytemplate.queryForList(query,new Object[]{type,branchCode});
        	
        }catch(Exception e){
        	logger.error("Exception in getMailCCInfo => {}"+e);
        }
    	logger.info("getMailCCInfo - Exit");
    	return list;
	}


	public Map<String, String> getMailDetails(String appId){
    	Map<String, String> details = new HashMap<String, String>();
    	logger.info("Enter into getMailDetails()");
    	try {
    		String  query=getQuery("GET_MAIL_DETAILS");
    		 logger.info("query =>"+query);
        	List list =this.mytemplate.queryForList(query,new Object[]{appId});
        	if(list != null && list.size()>0){
	        	details = (Map)list.get(0);
        	}
        }catch(Exception e){
        	logger.error("Exception in Getting Mail Details => {}"+e);
        }
    	logger.info("Getting Mail Details - Exit");
    	return details;
  }
}
