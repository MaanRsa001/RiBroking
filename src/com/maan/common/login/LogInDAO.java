package com.maan.common.login;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.maan.bean.CommonBean;
import com.maan.common.util.Jcrypt;
import com.maan.common.util.LogUtil;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class LogInDAO extends MyJdbcTemplate {
	final org.slf4j.Logger logger = LogUtil.getLogger(LogInDAO.class);
	private String query="";
	
	public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	public String[] validateUser(final String userId, String pwd,final String display, final String appId,String pwdcount, final String contextPath){
		logger.info("Enter into validateUser()");
        String result[] = new String[4];
        int pwdcount1=Integer.parseInt(pwdcount);
        List list=null;
        List list1=null;
        try{
	        String epwd = Jcrypt.crypt(pwd.substring(0, 3), pwd);
	        logger.info("Encrypted Password =>"+epwd);
	        logger.info("userId =>"+userId);
	        logger.info("password =>"+pwd);
	        query=getQuery("GET_VALID_USER");
	        logger.info("query =>"+query);
	        list1=this.mytemplate.queryForList(query,new Object[]{userId, appId});
	        if(list1 == null || list1.size()<=0){
	        	result[0] = LocalizedTextUtil.findDefaultText("E113", Locale.ENGLISH);	//Invalid User
	        	result[2]="E113";
	        }
	        else{
	        	query=getQuery("GET_USER_INFO");
	        	logger.info("query =>"+query);
			    list=this.mytemplate.queryForList(query, new Object[]{userId, epwd, appId});
			    if(list==null || list.size()<=0){
			    	 list=this.mytemplate.queryForList(query, new Object[]{userId, pwd, appId});
			    }
			    if(list == null || list.size()<=0){
			    	 Map rs1 = (Map)list1.get(0);
			    	if(Integer.parseInt(rs1.get("PWD_COUNT").toString())==(pwdcount1-2)){
		    			result[0]= LocalizedTextUtil.findDefaultText("E114", Locale.ENGLISH);	//This User will be lock if one more invalid Login
		    			result[2]="E114";
		    		}else if(Integer.parseInt(rs1.get("PWD_COUNT").toString())==(pwdcount1-1)){
		    			result[0]= LocalizedTextUtil.findDefaultText("E117", Locale.ENGLISH, new String[]{Integer.toString(pwdcount1)});	//This User is locked due to invalid login of more than "+pwdcount1+" times
		    			result[2]="E117";
			    	}else{ 
			    		result[0] = LocalizedTextUtil.findDefaultText("E115", Locale.ENGLISH);	//Invalid User for this Application
			    		result[2]="E115";
			    	}updateCount(userId,pwdcount, appId, contextPath);
			     }else{
		            Map rs = (Map)list.get(0);
		            if(rs == null || "N".equals((String)rs.get("status"))){
		                result[0] = LocalizedTextUtil.findDefaultText("E116", Locale.ENGLISH);	//User not in Active
		                result[1]=(String)rs.get("status");
		                result[2]="E116";
		            } else if(Integer.parseInt(rs.get("PWD_COUNT").toString())>pwdcount1){
		    			result[0]= LocalizedTextUtil.findDefaultText("E117", Locale.ENGLISH, new String[]{Integer.toString(pwdcount1)}); 	//	This User is locked due to invalid login of more than "+pwdcount1+" times
		    			result[2]="E117";
		            } else if("T".equals((String)rs.get("status"))){
		    			result[0]="changepwd";
		    			result[2]="E119";
		    		}else if("R".equals((String)rs.get("status"))){
		    			result[0]="R";
		    			result[1]=getActiveLoginId((String)rs.get("BRANCH_CODE"));
		    		}
		            else{
		            	result[0] =null;
		                result[1]=(String)rs.get("status");
		                result[2]="E100";
		                pwdCountReset(userId, appId);
		            }
		        }
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return result;
	 }
	 
	 private String getActiveLoginId(String branchCode) {
		String result="";
		try{
			String query = "SELECT LOGIN_ID FROM LOGIN_MASTER WHERE STATUS='Y' AND BRANCH_CODE=?";
			logger.info("Select Query====>"+query);
			result=(String)this.mytemplate.queryForObject(query,new Object[] {branchCode},String.class);
		}catch (Exception e) {
           e.printStackTrace();
		}
		return result;
	}

	public boolean checkPasswordChange(final String userId,String userStatus,String expiTime,String expiDate, String appId){
		 logger.info("Enter into checkPasswordChange()");
		 logger.info("userId =>"+userId);
		 boolean result=false;
		 if("Y".equals(userStatus)){
			 int expiDate1=Integer.parseInt(expiDate);
			 query=getQuery("GET_VALID_PWD_DAY");
	         logger.info("query =>"+query);
			 int days=this.mytemplate.queryForInt(query, new Object[]{userId, appId});
			 logger.info("Password Changed Before =>"+days+" Days");
			 result=days<expiDate1;
		 }
		 if("T".equals(userStatus)){
			 int expiTime1=Integer.parseInt(expiTime);
			 query=getQuery("GET_VALID_PWD_TIME");
	         logger.info("query =>"+query);
			 int hours=this.mytemplate.queryForInt(query, new Object[]{userId, appId});
			 logger.info("Password Changed Before =>"+hours+" hours");
			 result=hours<expiTime1;
		 }
		 return result;
	 }
	 
	  /*public List <Object> getUserInfo(final AdminAction ac, String userID, String appId){
    	logger.info("Enter into getUserInfo()");
    	List userInfo=null;
    	try{
    		query=getQuery("GET_USER_INFO_CHANGE");
	         logger.info("query =>"+query);
	         userInfo=this.mytemplate.queryForList(query,new Object[]{userID, appId});
	         if(userInfo!=null&& userInfo.size()>0){
	        	 Map map=(Map)userInfo.get(0);
	        	 ac.setUsername(map.get("USERNAME").toString());
	        	 ac.setUserID(map.get("LOGIN_ID").toString());
	        	 ac.setUserType(map.get("USERTYPE").toString());
	        	 ac.setStatus(map.get("STATUS").toString());
	        	 ac.setMail(map.get("USER_MAIL").toString());
	        	 ac.setAppIds(map.get("APP_ID").toString());
	        	 ac.setStartdate(map.get("STARTDATE").toString());
	         }
    	}
    	catch(Exception e){
    		logger.error("Exception in getUserInfo()"+e);
    	}
    	logger.info("Exit from getUserInfo()");
    	return userInfo;
     }*/
	 
	 public List<Object>  getUserInfo(String userId, String appId){
		 logger.info("Enter into getUserInfo()");
		 List list=null;
    	try {
    		query=getQuery("GET_USER_BASIC_INFO");
	         logger.info("query =>"+query);
    		list=this.mytemplate.queryForList(query,new Object[]{userId,appId});
        }catch(Exception e){
        	logger.error("Exception in getUserInfo => {}"+e);
        }
        logger.info("UserInfo - Exit");
    	return list;
	 }
	  
	 public List<Object>  validateMailForgot(Object[] obj){
		 logger.info("Enter into validateMailForgot()");
		 List list=null;
    	try {
    		query=getQuery("GET_MAIL_FORGOT_VALID");
	         logger.info("query =>"+query);
    		list=this.mytemplate.queryForList(query,obj);
        }catch(Exception e){
        	logger.error("Exception in validateMailForgot=> {}"+e);
        }
        logger.info("validateMailForgot - Exit");
    	return list;
	 }
	 
	  
	 public String changePassword(final String userId, String newPassword, String appId) {
		 logger.info("Enter into changePassword()");
		 String result = null;
		 newPassword = Jcrypt.crypt(newPassword.substring(0, 3), newPassword);
		 logger.info("Encrypted New Password => "+newPassword);
		 logger.info("userId =>"+userId);
		 query=getQuery("GET_COUNT_PWD");
         logger.info("query =>"+query);
		 int count =this.mytemplate.queryForInt(query, new Object[]{userId,appId,newPassword});
		 logger.info("Password Available Count => "+count);
		 if(count==0)
		 {
			 logger.info("newpwd =>"+newPassword);
			 logger.info("userId =>"+userId); 
			 query=getQuery("UPD_USER_PWD");
	         logger.info("query =>"+query);
		     int affRow=this.mytemplate.update(query, new Object[]{newPassword,userId,appId});
		     logger.info("Affected Rows => "+affRow);
		 }else
		 {
			  result ="This new Password used in last 5. Please enter new Password";
		 }
		 return result;
	}
	
	 public String getPassword(final String userId, String appId) {
		 logger.info("Enter into getPassword()");
		 final String alphabet = "Aa2Bb@3Cc#4Dd$5Ee%6Ff7Gg&8Hh9Jj2Kk=3L4Mm/5Nn@6Pp7Qq#8Rr$9Ss%2Tt3Uu&4Vv5Ww+6Xx=7Yy8Zz\\9";
		 final int N = alphabet.length();
		 String temppwd="";
		 Random r = new Random();
		 for (int i = 0; i < 10; i++) {
			 temppwd+=alphabet.charAt(r.nextInt(N));
		 }
		 query=getQuery("UPD_USER_PWD_TEMP");
         logger.info("query =>"+query);
		 String password = Jcrypt.crypt(temppwd.substring(0, 3), temppwd);
		 logger.info("newpwd ==>"+password+":userId ==>"+userId+":Temppassword==>"+temppwd);
	     this.mytemplate.update(query, new Object[]{password,userId, appId});
		 return temppwd;
	}
	
	 public Map<String, String> getMailDetails(String appId){
	    	Map<String, String> details = new HashMap<String, String>();
	    	logger.info("Enter into getMailDetails()");
	    	try {
	    		 query=getQuery("GET_MAIL_DETAILS");
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
	 
	 public String getExpireTime(String expTime){
		 logger.info("Enter into getExpireTime()");
    	String expireTime=null;
    	try {
    		 query=getQuery("GET_TEMP_PWD_EXP");
	         logger.info("query =>"+query);
        	expireTime=this.mytemplate.queryForObject(query,new Object[]{expTime},String.class).toString();
        }catch(Exception e){
        	logger.error("Exception getExpireTime => {}"+e);
        }
        logger.info("getExpireTime() - Exit");
    	return expireTime;
	 }
	 
	 public void pwdCountReset(final String user, String appId) {
		 logger.info("Enter into pwdCountReset()");
		 try{
			 query=getQuery("UPD_PWD_CNT_RESET");
	         logger.info("query =>"+query);
			 this.mytemplate.update(query, new Object[]{user, appId});
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
	
	 public void updateCount(final String user,String pwdcount, String appId, String contextPath) {
		 logger.info("Enter into updateCount()");
		 int pwdcount1=Integer.parseInt(pwdcount);
		 int pwdCount=0;
		 try{
			 query=getQuery("UPD_PWD_CNT");
	         logger.info("query =>"+query);
			 this.mytemplate.update(query, new Object[]{user, appId});
			 query=getQuery("GET_PWD_CNT_INVALID");
	         logger.info("query =>"+query);
			 pwdCount=this.mytemplate.queryForInt(query, new Object[]{user, appId});
			 if(pwdCount==pwdcount1){
				 query=getQuery("UPD_PWD_STATUS");
		         logger.info("query =>"+query);
				 this.mytemplate.update(query, new Object[]{"L",user,appId});
				 final LogInService service= new LogInService();
    			String temp="locked";
    			service.sendUserPwd(user,temp, appId, contextPath);
			 }
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
	
	public List<Object>  getappList(){
		 logger.info("Enter into getappList()");
	    	List list=null;
	    	try{
	    		 query=getQuery("GET_APP_LIST");
		         logger.info("query =>"+query);
	    		list=this.mytemplate.queryForList(query);
	    	}
	    	catch(Exception e){
	    		logger.error("Exception in getappList=> {}"+e);
	    	}
	    	return list;
	 }
	 
	 public String getAppName(String appId){
		 logger.info("Enter into getAppName()");
		 String appName=null;
    	try{
    		 query=getQuery("GET_APP_INFO");
	         logger.info("query =>"+query);
    		appName=this.mytemplate.queryForObject(query,new Object[]{appId},String.class).toString();
    	}
    	catch(Exception e){
    		logger.error("Exception in getAppName=> {}"+e);
    	}
    	return appName;
    }
	
	public boolean insertSessionInfo(final String loginId, final String sessionId, final String ipAddress, String branchCode){
        logger.info("Enter==>insertSessionInfo()");
        int affRow=0;
        try{
        	query=getQuery("INS_SESSION_INFO");
        	affRow = this.mytemplate.update(query, new Object[]{loginId, sessionId, ipAddress,branchCode});
        	logger.info("Query: " + query);
            logger.info("args => " + loginId+"	~	"+sessionId+"	~	"+ipAddress);
        }catch(Exception e){
        	e.printStackTrace();
        }
        logger.info("Exit==>insertSessionInfo()	Result==>"+affRow);
        return affRow>0;
    }
	
	public Map<Object, Object> getUserDetail(String loginId){
		logger.info("Enter==>getUserDetail()");
		Map<Object, Object>map=new HashMap<Object, Object>();
		List list=null;
		try{
			query=getQuery("GET_USER_DETAILS");
			list=this.mytemplate.queryForList(query, new Object[]{loginId});
			if(list!=null && list.size()>0){
				map=(Map)list.get(0);
			}
			logger.info("Query==>"+query);
			logger.info("Params => " + loginId);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("Exit==>getUserDetail()		Result==>"+list.size());
		return map;
	}
	
	public boolean updateSessionInfo(final String userId, final String sessionId, String remarks){
		logger.info("Enter==>updateSessionInfo()");
        int affRow=0;
        try{
        	query=getQuery("UPD_SESSION_INFO");
        	affRow = this.mytemplate.update(query, new Object[]{remarks,userId, sessionId});
        	logger.info("Query: " + query);
            logger.info("args => " + userId+"	~	"+sessionId);
        }catch(Exception e){
        	e.printStackTrace();
        }
        logger.info("Exit==>updateSessionInfo()	Result==>"+affRow);
        return affRow>0;
    }

	public List<Object> getMenuList(String loginid) {
        /*logger.info("Get Menu List - Enter");
        List list = null;
        try {
            //menuIds = menuIds.replaceAll(",", "','");
            //final String query = LocalizedTextUtil.findDefaultText("GET_MENU_LIST", Locale.ENGLISH, new String[]{menuIds});
            String query ="SELECT MENU_NAME, MENU_URL , MENU_ID  FROM Menu_master WHERE Active='1' AND  MENU_ID IN ("+ menuIds +") ORDER BY order_no,menu_id";
            logger.info("Query: " + query);
            logger.info("Params => " + menuIds);
            list = this.mytemplate.queryForList(query);
            logger.info("Menu List Size=>"+list.size());
        } catch (Exception e) {
            logger.error("Exception in Getting Menu List => ", e);
        }
        logger.info("Get Menu List - Exit");
        return list;*/
		logger.info("Get Menu List - Enter");
        List list = null;
        try {
        	String sql=getQuery(DBConstants.USER_SELECT_FINALMENULIST);
			String[] args=new String[3];
			args[0]=loginid;
			args[1]="0";
			args[2]="0";
			logger.info("Select Query====>"+sql);
			int i=0;
			for(String s:args){
				logger.info("Arg["+i+"]====>"+s);
				i++;
			}
			list=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+list.size());
        } catch (Exception e) {
            logger.error("Exception in Getting Menu List => ", e);
        }
        logger.info("Get Menu List - Exit");
        return list;
    }
	
	public String getMenuIds(String loginId) {
		String menuIds = "";
		try {
			String query = getQuery("GET_MENU_IDS");
			menuIds = (String) this.mytemplate.queryForObject(query, new Object[]{loginId},String.class);
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return menuIds;
	}
	
	public List<Map<String,Object>> getProductList(final String branchCode) {
		List<Map<String,Object>> productList=null;
		try{
			Object[] args=new String[1];
			String query=getQuery(DBConstants.USER_SELECT_PRODUCTMAP);
			//String query = "SELECT TMAS_PRODUCT_ID, TMAS_PRODUCT_NAME,DEPT_STATUS,BRANCH_CODE FROM PRODUCT_MASTER WHERE BRANCH_CODE=? AND TMAS_STATUS='1' ORDER BY TMAS_PRODUCT_ID";
			args[0]=branchCode;
			logger.info("Select Query====>"+query);
			logger.info("Arg[0]====>"+args[0]);
			productList=this.mytemplate.queryForList(query,args);
		}catch (Exception e) {
           e.printStackTrace();
		}
		return productList;
	}
	
	public List<Map<String, Object>> getDepartmentList(String branchCode) {
		logger.info("CommonDAO getDepartmentList || Enter");
		List<Map<String,Object>> departmentList=null;
		try{
			//String query=getQuery(DBConstants.USER_SELECT_DEPARTMENTMAP);
			String query = "SELECT TMAS_PRODUCT_ID,TMAS_DEPARTMENT_ID,TMAS_DEPARTMENT_NAME FROM TMAS_DEPARTMENT_MASTER WHERE TMAS_STATUS='H' AND BRANCH_CODE=?";
			Object[] args=new String[1];
			args[0]=branchCode;
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
	
	public List<Map<String, Object>> getProcessList(String branchCode) {
		logger.info("CommonDAO getProcessList || Enter");
		List<Map<String,Object>> processList=null;
		try{
			//String sql=getQuery(DBConstants.USER_SELECT_PROCESSMAP);
			String sql = "SELECT PRODUCT_ID,DEPT_ID,PROCESS_ID,PROCESS_NAME FROM TMAS_HOMEPAGE_MASTER WHERE STATUS='Y' AND BRANCH_CODE=?";
			Object[] args=new String[1];
			//args[0]=productId;
			//args[1]=departmentId;
			args[0]=branchCode;
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
	
	public List<Map<String, Object>> getFinalMenuList(String menuIds) {
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			//menuIds = menuIds.replaceAll(",", "','");
			//String sql=getQuery(DBConstants.USER_SELECT_FINALMENULIST);
			String sql = "SELECT MM.PRODUCT_ID, MM.DEPT_ID, MM.PROCESS_ID, MM.MENU_ID, MM.MENU_NAME, MM.MENU_URL FROM  MENU_MASTER MM where MM.ACTIVE = '1' "
				+ " AND MENU_ID IN ("+ menuIds +") "
				+ " ORDER BY   MM.ORDER_NO";
			//Object[] args=new String[1];
			logger.info("Select Query====>"+sql);
			//logger.info("Args==> " + StringUtils.join(args,","));
			//result=this.mytemplate.queryForList(sql,args);
			result=this.mytemplate.queryForList(sql);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("CommonDAO getFinalMenuList || Exit");
		return result;
	}

	public String getCountryId(String branchCode) {
		String country_Id="";
		try{
			String query = "select COUNTRY_ID from tmas_branch_master where branch_code=?";
			logger.info("Select Query====>"+query);
			country_Id=(String)this.mytemplate.queryForObject(query,new Object[] {branchCode},String.class);
		}catch (Exception e) {
           e.printStackTrace();
		}
		return country_Id;
	}

	public int getValidLoginStatus(CommonBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		int count=0;
		try {
			String query=getQuery("GET_VALID_LOGIN_STATUS");
			list=this.mytemplate.queryForList(query,new Object[]{bean.getLoginId()});
			if(list!=null && list.size()>0){
				count=1;
			bean.setInvalidLoginList(list);
			}
			//count=this.mytemplate.queryForInt(query,new Object[]{bean.getLoginId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getValidUserCount(CommonBean bean) {
		int count=0;
		try {
			String query=getQuery("GET_VALID_USER_STATUS");
			
			count=this.mytemplate.queryForInt(query,new Object[]{bean.getLoginId(),bean.getSessionId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int datevalidation(String content, CommonBean bean) {
		int result = 0;
		try
		{
		String con[] = content.split(",");	
		bean.setExpiryDate(con[1]);
		bean.setExpiryDate07(con[2]);
		String qry = getQuery("GET_BRANCH_CODE");
		Object arg[] = new Object[1];
		arg[0] = bean.getLoginId();
		String branchCode= this.mytemplate.queryForObject(qry, arg,String.class);
		if(branchCode.equalsIgnoreCase(con[0])){
		String query=getQuery("GET_SYS_DATE_COMPARE");
		Object[] args=new Object[1];
		args[0]=con[1];
		result=this.mytemplate.queryForInt(query,args);
		}
		else if(branchCode.equalsIgnoreCase(con[2])){
		String query=getQuery("GET_SYS_DATE_COMPARE");
		Object[] args=new Object[1];
		args[0]=con[3];
		result=this.mytemplate.queryForInt(query,args);
		}
		else{
			result = 20;
		}
		}
		catch(Exception e){
		e.printStackTrace();
		}
		return result;
		}

	public List<Map<String, Object>> getUserLoginList(CommonBean bean,String branchCode) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("USER_LOGIN_LIST");
			list = this.mytemplate.queryForList(query,new Object[]{bean.getLoginId(),branchCode});
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateUserInvalidation(CommonBean bean, String branchCode) {
		boolean status= true;
		try{
			query=getQuery("UPDATE_USER_INVALIDATION");
			String[] checked=bean.getCheckedVal().split(",");
			for(int i=0;i<checked.length;i++){
			this.mytemplate.update(query,new Object[]{"I",bean.getLoginId(),branchCode,checked[i]});
			}
		}catch(Exception e){
			e.printStackTrace();
			status = false;
		}
		return status;
		
	}
	}
	 

