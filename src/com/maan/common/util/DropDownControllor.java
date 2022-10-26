package com.maan.common.util;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import com.maan.bean.ClaimBean;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.bean.TreasuryBean;
import com.maan.bean.admin.CedingMasterBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class DropDownControllor extends MyJdbcTemplate {
	Logger logger = LogUtil.getLogger(DropDownControllor.class);
	public List<Map<String, Object>> getCountryDropDown(String branchCode) {

		List<Map<String, Object>> countryList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETCOUNTRYLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			countryList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");		
		}
		return countryList;
	}

	public  List<Map<String, Object>> getProfitCentreDropDown(String branchCode) {
		List<Map<String, Object>> profitCenterList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETPROFITCENTERLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			profitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return profitCenterList;
	}

	public List<Map<String,Object>> getSubProfitCentreDropDown(String deptid,String branchCode,String productCode){
		List<Map<String,Object>> subProfitCenterList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETSUBPROFITCENTERLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + productCode);
			logger.info("Args[2]==>" + deptid);
			logger.info("Args[3]==> " + "Y");
			subProfitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,deptid,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return subProfitCenterList;
	}	 

	public List<Map<String,Object>> getProductDropDown(String branchCode){

		List<Map<String,Object>> productList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETPRODUCTLIST);
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			productList=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}

		return productList;
	}
	public List<Map<String,Object>> getUnderWritterDropDown(String branchCode, String attachedUW) {

		List<Map<String,Object>> uwList=new ArrayList<Map<String,Object>>();
		try{
			if(StringUtils.isNotBlank(attachedUW) && !"ALL".equalsIgnoreCase(attachedUW)) {
				String query=getQuery("GET_UNDERWRITER_ATTACHED");
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + "Y");
				logger.info("Args[2]==> " + attachedUW);
				uwList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",attachedUW});
			}else {
				String query=getQuery(DBConstants.COMMON_SELECT_GETUWList);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + "Y");
				uwList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return uwList;
	}
	public List<Map<String,Object>> getClaimDepartmentDropDown(String branchCode,String productId,String status, ClaimBean bean){
		List<Map<String,Object>> department=new ArrayList<Map<String,Object>>();
		try{
			String count="";
			if("2".equals(productId)){
				count=getCombinedClass(branchCode,productId,bean.getDepartmentId());
			}if(StringUtils.isNotBlank(count) && "2".equals(productId)){

				String query=getQuery("common.department.combined.premiumclaim");
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productId);
				logger.info("Args[2]==> " + count);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,count});	
			}else{
			
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST_PRECLAIM);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productId);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,status,bean.getContarctno(),bean.getLayerNo()});	
			if(department.size()==0){
				 query = getQuery("DEPARTMENT_VALUE");
				 department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,status,bean.getContarctno(),bean.getLayerNo()});	
			}
			}
			}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return department;
	}
	public List<Map<String,Object>> getDepartmentDropDown(String branchCode,String productCode,String status, String contNo, String endt, String proposalNo,String mode, String baseLayer){

		List<Map<String,Object>> department=new ArrayList<Map<String,Object>>();
		try{
			if(productCode.equalsIgnoreCase("")){
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST_PREMIUM);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,status});	
			}//&& !"Renewal".equalsIgnoreCase(proposalReference)
			else if(!StringUtils.isBlank(baseLayer) ) {
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status});	
				
			/*	String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST3);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				logger.info("Args[3]==> " + proposalNo);
				logger.info("Base Layer==> " + baseLayer);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status,baseLayer,baseLayer,proposalNo});*/
			}
			else if(StringUtils.isBlank(proposalNo) && StringUtils.isBlank(contNo)){
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status});	
			}
			else if(StringUtils.isBlank(contNo) && !StringUtils.isBlank(proposalNo)){
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status});	
				/*String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST2);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				logger.info("Args[3]==> " + proposalNo);
				logger.info("Base Layer==> " + baseLayer);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status,proposalNo,baseLayer,baseLayer});*/
				}
			else if(!StringUtils.isBlank(contNo) && StringUtils.isBlank(proposalNo)){
			String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST1);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + productCode);
			logger.info("Args[2]==> " + status);
			logger.info("Args[3]==> " + contNo);
			department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status,contNo,contNo});
			}
			else if(!StringUtils.isBlank(contNo)){
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST1);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productCode);
				logger.info("Args[2]==> " + status);
				logger.info("Args[3]==> " + contNo);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status,contNo,contNo});
				}
			}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return department;

	}

	public List<Map<String,Object>> getPolicyBranchDropDown(String branchCode) {

		List<Map<String,Object>> policyBranch=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_POLICYBRANCHLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			policyBranch=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return policyBranch;
	}


	public List<Map<String,Object>> getPersonalInfoDropDown(String branchCode,String customerType, String pid) {

		List<Map<String,Object>> personalInfo=new ArrayList<Map<String,Object>>();
		try{
			Object arg[]=null;
			String query=getQuery(DBConstants.COMMON_SELECT_PERSONALINFOLIST);
			if(customerType.equals("L") && !pid.equalsIgnoreCase("4") &&  !pid.equalsIgnoreCase("5")){
				query+=getQuery("GET_ITI_BROKER");
				arg=new Object[7];
				arg[0]=branchCode;
				arg[1]=customerType;
				arg[2]="Y";
				arg[3]=branchCode;
				arg[4]="C";
				arg[5]="Y";
				arg[6]="64";
			}
			else if(customerType.equals("C") && pid.equalsIgnoreCase("4") ){
				query+=getQuery("GET_ITI_BROKER");
				arg=new Object[7];
				arg[0]=branchCode;
				arg[1]=customerType;
				arg[2]="Y";
				arg[3]=branchCode;
				arg[4]="C";
				arg[5]="Y";
				arg[6]="64";
			}
			else{
				arg=new Object[3];
				arg[0]=branchCode;
				arg[1]=customerType;
				arg[2]="Y";
			}
			query+="ORDER BY NAME";
			logger.info("Select Query==> " + query);
			logger.info("Args==>" + StringUtils.join(arg,","));
			personalInfo=this.mytemplate.queryForList(query,arg);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return personalInfo;
	}
	//DEPARTMENT REPORT....
	public List<Map<String,Object>> getDeptName(String branchCode) {

		List<Map<String,Object>> personalInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_DEPTNAME);
			logger.info("Select Query==> " + query);
			logger.info("Branch Code==>Args[0]==> " + branchCode);
			// logger.info("Product Code==>Args[1]=>"+productid);
			//logger.info("Args[2]=>"+"H");
			personalInfo=this.mytemplate.queryForList(query,new Object[]{branchCode});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return personalInfo;
	}

	public  List<Map<String,Object>> getConstantDropDown(String categoryId) {

		List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
		String query ="";
		try{
			if("30".equalsIgnoreCase(categoryId) || "48".equalsIgnoreCase(categoryId)){
			query=getQuery("COM_SELECT_PERILS");
			}
			else{
			 query=getQuery(DBConstants.COMMON_SELECT_GETCONSTDET);
			 
			}
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + categoryId);
			logger.info("Args[1]==> " + "Y");
			constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return constantList;
	}

	public List<Map<String,Object>> getCurrencyMasterDropDown(String branchCode,String countryId) {
		logger.info("DropDownControllor getCurrencyMasterDropDown() || Enter  branchCode=>"+branchCode+"countryId=>"+countryId);
		List<Map<String,Object>> personalInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETCURRENCYLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + countryId);
			logger.info("Args[2]==> " + "Y");
			personalInfo=this.mytemplate.queryForList(query,new Object[]{branchCode,countryId,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return personalInfo;
	}

	public List<Map<String,Object>> getTerritoryDropDown(String branchCode) {

		List<Map<String,Object>> territorty=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETTERRITORYLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			territorty=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}"); 
		}
		return territorty;
	}

	public String GetExchangeRate(final String location,final String date,final String countryid,final String branchCode) {
		String exRate="";
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_STARTDTOFMONTH);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + date);
			String startDtOfMonth=(String)this.mytemplate.queryForObject(query,new Object[]{date},String.class);
			logger.info("Result==> " + startDtOfMonth);
			if(!"01/".equalsIgnoreCase(startDtOfMonth))
			{
				query=getQuery(DBConstants.COMMON_SELECT_EXRATE);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + location);
				logger.info("Args[1]==> " + countryid);
				logger.info("Args[2]==> " + startDtOfMonth);
				logger.info("Args[3]==> " + branchCode);
				List<Map<String,Object>> list=this.mytemplate.queryForList(query,new Object[] {location,countryid,startDtOfMonth,branchCode,startDtOfMonth});
				logger.info("List Size==> " + list.size());
				if(list!=null&&list.size()>0)
				{
					Map<String,Object> map=(Map<String,Object>)list.get(0);
					exRate=map.get("EXCHANGE_RATE")==null?"":map.get("EXCHANGE_RATE").toString();
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	 
		}

		return exRate;
	}

	public static String formatter(final String value)
	{
		String output="0.00";
		if(StringUtils.isNotBlank(value))
		{
			double doublevalue=Double.parseDouble(value);
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0.00");
			output =myFormatter.format(doublevalue);
		}
		return output;
	}
	public static String formatterpercentage(final String value)
	{
		String output="0.00";
		if(StringUtils.isNotBlank(value))
		{
			double doublevalue=Double.parseDouble(value);
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0.0000");
			output =myFormatter.format(doublevalue);
		}
		return output;
	}
	public static String formattereight(final String value)
	{
		String output="0.00";
		if(StringUtils.isNotBlank(value))
		{
			double doublevalue=Double.parseDouble(value);
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0.00000000");
			output =myFormatter.format(doublevalue);
		}
		return output;
	}
	public static String exchRateFormat(final String value){
		String output="0.00";
		if(StringUtils.isNotBlank(value))
		{
			System.out.println(value);
			double doublevalue=Double.parseDouble(value);
			DecimalFormat myFormatter = new DecimalFormat("#####.##########");
			output = myFormatter.format(doublevalue);
		}
		return output;
	}
	public static String GetDesginationCountry(final String limitOrigCur,final String ExchangeRate) {
		String valu="0";
		if (StringUtils.isNotBlank(limitOrigCur)&& Double.parseDouble(limitOrigCur) != 0) {
			double originalCountry = Double.parseDouble(limitOrigCur)/ Double.parseDouble(ExchangeRate);
			DecimalFormat myFormatter = new DecimalFormat("###0.00");
			final double dround = Math.round(originalCountry * 100.0) / 100.0;
			valu = myFormatter.format(dround);
		}
		return valu;
	}
	public static String GetTwoDecimalFormate(final double ans) {
		String valu="0";
			DecimalFormat myFormatter = new DecimalFormat("###0.00");
			final double dround = Math.round(ans * 100.0) / 100.0;
			valu = myFormatter.format(dround);
		return valu;
	}
	
	public static String GetACC(final double dround) {
		String valu="0";
			DecimalFormat myFormatter = new DecimalFormat("####.####");
			valu = myFormatter.format(dround);
		return valu;
	}

	public int getMaxID(final String tableName,final String fieldName)
	{
		String query;
		int maxId;
		query = "SELECT NVL(MAX(" + fieldName + "),0)+1 FROM " + tableName +" ";
		logger.info("Query==> " + query);
		maxId=this.mytemplate.queryForInt(query);
		logger.info("Max Id ==> " + maxId);
		return maxId;
	}
	public Date stringToDate(final String dateStr)  {

		Date result=new Date();
		DateFormat formatter;
		if (dateStr.length() == 0) {
			result = new Date();
		} else {
			try {
				formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
				result = (Date) formatter.parse(dateStr);
			} catch (Exception e) {
				logger.debug("Exception @ {" + e + "}");
			}
		}
		return result;
	}

	public String stringArrayToString(final String[] value,
			final String delimiter)  {
		final StringBuffer result=new StringBuffer();
		if (value == null) {
			result.append("");
		} else {
			result.append(value[0]);
			for (int i = 1; i < value.length; i++) {
				result.append(delimiter + value[i]);
			}
		}
		return result.toString();
	}
	public String[] stringToStringArray(final String value,
			final String delimiter)  {
		String[] result;
		if (value == null) {
			result = new String[0];
		} else {
			result = value.split(delimiter);
		}
		return result;
	}

	public List<Map<String,Object>> getRiskGradeDropDown(String branchCode)   {

		List<Map<String,Object>> riskGrade=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETRISKGRADELIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			riskGrade=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return riskGrade; 
	}

	public List<Map<String,Object>> getCategoryZoneDropDown(String branchCode)   {

		List<Map<String,Object>> categoryZone=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETCATEGORYZONELIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			categoryZone=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return categoryZone;
	}
	public List<Map<String,Object>> getBankMasterDropDown(String branchCode)   {

		List<Map<String,Object>> bankMaster=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_BANKMASTERLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			bankMaster=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return bankMaster;
	}

	public String getDepartmentName(String branchCode, String productCode,String deptCode)
	{

		String deptName="";
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETDEPTNAME);
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + productCode);
			logger.info("Args[2]==> " + deptCode);
			deptName=(String)this.mytemplate.queryForObject(query,new Object[]{branchCode,productCode,deptCode},String.class);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return deptName;
	}
	public String getCompanyName(String branchCode, String custId,String custType) 
	{

		String compName="";
		try{
			if(custId!=null && !"".equals(custId)&& !"0".equals(custId)){
				String query=getQuery(DBConstants.COMMON_SELECT_GETCOMPNAME);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + custId);
				logger.info("Args[2]==> " + custType);
				compName=(String)this.mytemplate.queryForObject(query,new Object[]{branchCode,custId,custType},String.class);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return compName;
	}

	public synchronized String getPolicyNo(String seqID,String productID,String branchCode){ 

		String policyNo="";
		try{
			/*For Oracle DataBase Policy Number generated based on Sequence by sathish on 09Mar13 || Start
			 */  
			String query=getQuery(DBConstants.COMMON_SELECT_SEQNAME);
			logger.info("Query==> " + query);
			logger.info("Sequenct ID==> " + seqID + ", Product ID==> " + productID + ", Branch Code==> " + branchCode + ", Status==>Y");
			String seqName=(String)this.mytemplate.queryForObject(query,new Object[]{seqID,productID,branchCode,"Y"},String.class);
			logger.info("Result==> " + seqName);
			query=getQuery(DBConstants.COMMON_SELECT_POLICYNO1)+" "+seqName+getQuery(DBConstants.COMMON_SELECT_POLICYNO2);
			logger.info("Query==> " + query);
			policyNo=(String)this.mytemplate.queryForObject(query,String.class);
			logger.info("Result==> " + policyNo);
			/*For Oracle DataBase Policy Number generated based on Sequence by sathish on 09Mar13 || End
			For MySql DataBase Policy Number generated based on SequenceMaster maintain the maxVal of SeqNo by sathish on 09Mar13 || Start
			 */
			/*String query=getQuery(DBConstants.COMMON_SELECT_POLICYNO);
			logger.info("Query=>"+query);
			logger.info("Sequenct ID=>"+seqID+" Product ID=>"+productID+" Branch Code=>"+branchCode+"Status=>Y");
			policyNo=(String)this.mytemplate.queryForObject(query,new Object[]{seqID,productID,branchCode,"Y"},String.class);
			logger.info("Result=>"+policyNo);
			query=getQuery(DBConstants.COMMON_UPDATE_POLICYNO);
			logger.info("Query=>"+query);
			logger.info("Policy No=>"+policyNo+"Sequenct ID=>"+seqID+" Product ID=>"+productID+" Branch Code=>"+branchCode+"Status=>Y");
			int res=this.mytemplate.update(query,new Object[]{policyNo,seqID,productID,branchCode,"Y"});
			logger.info("Result=>"+res);*/
			//For MySql DataBase Policy Number generated based on SequenceMaster maintain the maxVal of SeqNo by sathish on 09Mar13 || End
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return policyNo;
	}
	public synchronized String getSequence(String type,String productID,String departmentId,String branchCode, String proposalNo,String date){ 

		String seqName="";
		try{
			
			//String query=getQuery(DBConstants.COMMON_SELECT_SEQNAME);
			//String query="select fn_get_seqno('Proposal',1,2,'06') from dual";
			String query="select Fn_get_SeqNo(?,?,?,?,?,?) from dual";
			logger.info("Query==> " + query);
			logger.info("Type==> " + type + ", Product ID==> " + productID + ", dept ID==> " + departmentId + ", Branch Code==> " + branchCode);
			seqName=(String)this.mytemplate.queryForObject(query,new Object[]{type,productID,departmentId,branchCode,proposalNo,date},String.class);
			logger.info("Result==> " + seqName);
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return seqName;
	}
	public String getSysDate()
	{

		String sysDate="";
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETSYSDATE);
			logger.info("Select Query ==> " + query);
			sysDate=(String)this.mytemplate.queryForObject(query,String.class);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return sysDate;
	}
	public String getUWLimmit(String loginId,String processId,String productId,String deptId)
	{
		String uwLimit=null;
		try{
			logger.info("Select Query==> " + getQuery(DBConstants.COMMON_SELECT_GETUWLIMIT));
			logger.info("Arg[0]==> " + loginId);
			logger.info("Arg[1]==> " + processId);
			logger.info("Arg[2]==> " + productId);
			logger.info("Arg[3]==> " + deptId);
			logger.info("Arg[4]==> " + "1");
			List<Map<String,Object>> uwList=null;
			uwList=this.mytemplate.queryForList(getQuery(DBConstants.COMMON_SELECT_GETUWLIMIT),new Object[]{loginId,processId,productId,deptId,"1"});
			if(uwList!=null && uwList.size()>0)
			{
				Map<String,Object> uwMap=(Map<String,Object>)uwList.get(0);
				uwLimit=uwMap.get("UWLIMIT")==null?"0":uwMap.get("UWLIMIT").toString();
			}else
				uwLimit ="0";
			logger.info("UW Limit==> " + uwLimit);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return uwLimit;
	}
	public  List<Map<String,Object>> getLoginIdDropDown(String branchCode,String userType)  {
		List<Map<String,Object>> loginIdList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_LOGINIDLIST);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + userType);
			logger.info("Args[2]==> " + "1");
			loginIdList=this.mytemplate.queryForList(query,new Object[]{branchCode,userType,"1"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return loginIdList;
	}
	public List<Map<String,Object>> getModeOfTransportDropDown(String branchCode, String deptId) {

		List<Map<String,Object>> countryList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_MODEOFTRANSPORT);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			countryList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",deptId});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return countryList;
	}
	public boolean getPLCLCountStatus(String contractNo,String layerNo) {
		boolean  status=false;
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETPRCLCOUNT);
			logger.info("Select Query==> " + query);
			logger.info("Args[0][2]==> " + contractNo);
			logger.info("Args[1][3]==> " + layerNo);
			int plclCount=this.mytemplate.queryForInt(query,new Object[]{contractNo,layerNo,contractNo,layerNo});
			logger.info("Result==> " + plclCount);
			if(plclCount>0)
				status=true;
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return status;
	}
	public String getRenewalCopyQuote(final String type,final String productId,final String branchCode,final String proposalNo){

		String newProposalNo="";
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			//String spName= getQuery(DBConstants.FAC_SP_RENEWALCOPYQUOTE);
			//logger.info("SP Name==> " + spName);
			//logger.info("Product Id==> " + productId);
			//logger.info("Branch Code==> " + branchCode);
			//logger.info("Proposal No==> " + proposalNo);
			//int cstmt=this.mytemplate.queryForInt(spName,new Object[]{productId,branchCode,poposalNo});
			con = this.mytemplate.getDataSource().getConnection(); 	
			//cstmt = con.prepareCall(spName);
			//cstmt = con.prepareCall("{CALL RENEWAL_COPYQUOTE(?,?,?,?)}");
			cstmt = con.prepareCall("{CALL COPYQUOTE(?,?,?,?,?)}");
			if("Renewal".equalsIgnoreCase(type)){
			cstmt.setString(1, "Renewal");
			}
			else{
				cstmt.setString(1, type);	
			}
			cstmt.setString(2, type);
			cstmt.setString(3, productId);
			cstmt.setString(4, branchCode);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.setString(5, proposalNo);
			boolean count = cstmt.execute();
			newProposalNo = cstmt.getString(5);
			logger.info("result==> " + newProposalNo);
			
		}
		catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					logger.debug("Exception @ {" + e + "}");
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.debug("Exception @ {" + e + "}");
				}
			}
		}

		return 	newProposalNo;
	}
	public String decimalFormat(final double number, final int nos) {

		String noFmt="0";
		try{
			String digit = "0.00";
			noFmt= String.valueOf(number);
			switch (nos) {
			case 0:
				digit = "###,###";
				break;
			case 1:
				digit = "##,##0.0";
				break;
			case 2:
				digit = "##,##0.00";
				break;
			case 3:
				digit = "##,##0.000";
				break;
			case 4:
				digit = "##,##0.0000";
				break;
			case 5:
				digit = "##,##0.00000";
				break;
			case 6:
				digit = "##,##0.000000";
				break;
			default:
				digit = "0.00";
			}
			java.text.NumberFormat fmtNo;
			fmtNo = new java.text.DecimalFormat(digit);
			noFmt = fmtNo.format(number);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}

		return noFmt;
	}
	public boolean getMode(final String proposalNo,final int instNo,final String endtNo,final int mode) {

		Object[] args=new Object[3];
		boolean flag=false;
		try {
			args[0] = proposalNo;
			args[1] = instNo+"";
			args[2] = endtNo;
			String query="";
			if(mode==1)
				query=getQuery(DBConstants.RISK_SELECT_GETINSTNO);
			else if(mode==2)
				query=getQuery(DBConstants.RISK_SELECT_GETRETROCNT);
			else if(mode==3)
				query=getQuery(DBConstants.RISK_SELECT_RETROCESSCNT);
			logger.info("Query==> " + query);
			logger.info("Args[]==> " + StringUtils.join(args,","));
			int count=this.mytemplate.queryForInt(query,args);
			logger.info("Result==> " + count);
			if (count==0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}

		return flag;
	}

	public String getRiskComMaxAmendId(final String proposalNo) {

		String result="";
		try{
			String selectQry = getQuery(DBConstants.RISK_SELECT_RISKCOMMAXAMENDID); 
			logger.info("Select Qry==> " + selectQry);
			logger.info("Args[0]==> " + proposalNo);
			result = this.mytemplate.queryForObject(selectQry,new String[]{proposalNo},String.class).toString();
		}catch(Exception e)
		{
			logger.debug("Exception @ {" + e + "}");
		}

		return result;
	}
	public Object[] getIncObjectArray(Object[] srcObj,Object[] descObj){
		final Object[] tempObj = new Object[srcObj.length];
		System.arraycopy(srcObj, 0, tempObj, 0, srcObj.length);
		srcObj=new Object[tempObj.length+descObj.length];
		System.arraycopy(tempObj, 0, srcObj, 0, tempObj.length);
		System.arraycopy(descObj, 0, srcObj, tempObj.length, descObj.length);
		return srcObj;
	}
	public boolean getInstallments(FaculitivesBean formObj) {
		boolean status=false;
		try{
		String[] args = new String[4];
		args[0] = formObj.getProposalNo();
		args[1] = "0";
		args[2] = formObj.getProposalNo();
		args[3] = "0";
		String query = getQuery(DBConstants.RISK_SELECT_GETINSTALMENTDATA);
		logger.info("selectQry " + query);
		logger.info("Args[0]..[2]=>" +  formObj.getProposalNo());
		logger.info("Args[1]..[3]=>0");
		List instalmentList = this.mytemplate.queryForList(query,args);
		logger.info("Result Size=>" +instalmentList.size());
		if (instalmentList != null) {
			List<String> instDate=new ArrayList<String>();
			List<String> instPremium=new ArrayList<String>();
			List<String> transactionList=new ArrayList<String>();
			List<String> pay=new ArrayList<String>();
			for (int k = 0; k < instalmentList.size(); k++) {
				Map insMap = (Map)instalmentList.get(k);
				instDate.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
				instPremium.add(insMap.get("MND_PREMIUM_OC")==null?"":DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC").toString()));
				transactionList.add(insMap.get("TRANSACTION_NO")==null?"":insMap.get("TRANSACTION_NO").toString());
				pay.add(insMap.get("PAYEMENT_DUE_DAY")==null?"":insMap.get("PAYEMENT_DUE_DAY").toString());
				status=true;

			}
			formObj.setTransactionList(transactionList);
			formObj.setInstalmentDateList(instDate);
			formObj.setInstallmentPremium(instPremium);
			formObj.setPaymentDueDays(pay);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("DropDownControllor getInstallments Facultative || Exit Result=>"+status);
		return status;
	}
	public boolean getInstallments(RiskDetailsBean bean) {
		boolean status=false;
		try{
		Object[] args = new String[4];
		args[0] = bean.getProposal_no();
		args[1] = bean.getLayerNo();
		args[2] = bean.getProposal_no();
		args[3] = bean.getLayerNo();
		String query = getQuery(DBConstants.RISK_SELECT_GETINSTALMENTDATA);
		logger.info("selectQry " + query);
		logger.info("Args[0]..[2]=>" +  bean.getProposal_no());
		logger.info("Args[1]..[3]=>"+ bean.getLayerNo());
		List<Map<String,Object>> instalmentList = this.mytemplate.queryForList(query,args);
		logger.info("Result Size=>" +instalmentList.size());
		if (instalmentList != null && instalmentList.size()>0) {
		List<String> instDate=new ArrayList<String>();
		List<String> instPremium=new ArrayList<String>();
		List<String> transactionList=new ArrayList<String>();
		double totalInstPremium=0.0;
		for (int k = 0; k < instalmentList.size(); k++) {
		Map insMap = (Map)instalmentList.get(k);
		instDate.add(insMap.get("INSTALLMENT_DATE")==null?"":insMap.get("INSTALLMENT_DATE").toString());
		instPremium.add(DropDownControllor.formatter(insMap.get("MND_PREMIUM_OC")==null?"":insMap.get("MND_PREMIUM_OC").toString()));
		transactionList.add(insMap.get("TRANSACTION_NO")==null?"":insMap.get("TRANSACTION_NO").toString());
		totalInstPremium+=Double.parseDouble(instPremium.get(k).replaceAll(",", ""));
		status=true;

		}
		if(Double.parseDouble(bean.getMd_premium_our_service())==totalInstPremium || null == bean.getInstallmentPremium()){
			bean.setTransactionList(transactionList);
			bean.setInstalmentDateList(instDate);
			bean.setInstallmentPremium(instPremium);
		}
		}
		} catch (Exception e) {
		logger.debug("Exception @ { " + e + " } ");
		}
		return status;
		}
	public void riskDetailsEndorsement(String proposalNo, String endtStatus) {
		try {
			//String query1 = getQuery(DBConstants.CONTRACT_STATUS_COUNT);
			//String query1 ="Select Count(CONTRACT_STATUS) from position_master pm where proposal_no="+proposalNo+" and CONTRACT_STATUS='P' and pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)";
			//int result1 = this.mytemplate.queryForInt(query1);
			//if(result1==0){
			//String query = "call Endt_copyquote(?,?)";
			String query = "call COPYQUOTE (?,?,?,?,?)";
			logger.info("Endorsement==>"+query);
			logger.info("Args[0]==>" +proposalNo );
			logger.info("Args[1]==>"+endtStatus);
			
			int result = this.mytemplate.update(query,new Object[]{"Endt",endtStatus==null?"":endtStatus,"","",proposalNo});
			System.out.println(result);
			//}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
	}
	
	public void updatepositionMasterEndtStatus(String proposalNo, String productId, String endtDate, String ceaseStatus) {
		//String query = "update position_master pm set endt_status='Y',ENDORSEMENT_DATE=to_date(?,'DD/MM/YYYY') where proposal_no=? and pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)";
		String query = "update position_master pm set endt_status='Y',CONTRACT_STATUS=case when PROPOSAL_STATUS='A' then 'A' else  CONTRACT_STATUS end ,ENDORSEMENT_DATE=to_date(?,'DD/MM/YYYY'),CEASE_STATUS=? where proposal_no=? and pm.amend_id=(Select Max(Amend_id) From Position_master p where p.proposal_no=pm.proposal_no)";
		logger.info("Select Query ==> " + query);
		logger.info("Args[0]==> " + endtDate);
		logger.info("Args[1]==> " + proposalNo);
		this.mytemplate.update(query,new Object[]{endtDate,ceaseStatus,proposalNo});
	}

	public String getDisableStatus(String contractNo, String layerNo) {
		String status="Y";
		if(StringUtils.isBlank(contractNo)){
			contractNo="";
		}
		try {
			String query = getQuery("GET_DISABLE_STATUS");
			//status= (String)this.mytemplate.queryForObject(query,new Object[]{contractNo,layerNo,contractNo,layerNo},String.class);
			status= (String)this.mytemplate.queryForObject(query,new Object[]{contractNo,layerNo},String.class);
			System.out.println(status);
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return status;
	}

	public String getAcceptanceDate(String proposalNo) {

		String result="";
		try{
			String query=getQuery("GET_ACCEPTANCE_DATE");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " + proposalNo);
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{proposalNo},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
	}

	public String getAmend(TreasuryBean bean) {

		String result="";
		try{
			String query=getQuery("GET_AMENDMENT_DATE");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " + bean.getSerial_no());
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{bean.getSerial_no()},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
	}

	public String getTrans(TreasuryBean bean) {
		String result="";
		try{
			String query=getQuery("GET_TRANSACTION_DATE");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " + bean.getPolicyno());
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{bean.getPolicyno()},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
	}
	
	public List<FaculitivesBean> getOpenPeriod(final FaculitivesBean bean) {
			List<FaculitivesBean> finalList = new ArrayList<FaculitivesBean>();
			String openPeriodDate="";
			String query=getQuery("GET_OPEN_PERIOD_DATE");
			Object[] args = new String[1];
			args[0]=bean.getBranchCode();
			//args[1]=bean.getBranchCode();
			logger.info("Select Query ==> " + query);
			List<Map<String,Object>> list=this.mytemplate.queryForList(query,args);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				bean.setOpstartDate(tempMap.get("start_date")==null?"":tempMap.get("start_date").toString());
				bean.setOpendDate(tempMap.get("end_date")==null?"":tempMap.get("end_date").toString());
				openPeriodDate=openPeriodDate+bean.getOpstartDate()+" to "+bean.getOpendDate()+" ,";
				finalList.add(bean);
			}
			if(StringUtils.isNotBlank(openPeriodDate))
			openPeriodDate = openPeriodDate.substring(0, openPeriodDate.length() - 1);
			bean.setOpenPeriodDate(openPeriodDate);
			return finalList;
}

	public String getpreamendDate(String contractNo) {
		String result="";
		try{
			String query=getQuery("GET_PRE_AMEND_DATE");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " +contractNo);
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{contractNo},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
		/*
		
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_PRE_AMEND_DATE"), new Object[] {contractNo}, String.class);
		return Short;*/
	}

	public String getpremiumPreamendDate(FaculPremiumBean bean) {
		String result="";
		try{
			String query=getQuery("GET_PREMIUM_AMEND_DATE");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " +bean.getContNo());
			logger.info("Args[0]==> " +bean.getTransactionNo());
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo(),bean.getTransactionNo()},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
	}

	public int Validatethree(String branchCode,
			String accountDate) {
		int result=0;
		
		try{
			String query=getQuery("GET_OPEN_PERIOD_DATE");
			String OpstartDate="";
			String OpendDate="";
			Object[] args = new String[1];
			args[0]=branchCode;
			//args[1]=bean.getBranchCode();
			logger.info("Select Query ==> " + query);
			List<Map<String,Object>> list=this.mytemplate.queryForList(query,args);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				OpstartDate=tempMap.get("start_date")==null?"":tempMap.get("start_date").toString();
				OpendDate=tempMap.get("end_date")==null?"":tempMap.get("end_date").toString();
				String query1=getQuery("GET_OPEN_PERIOD_VALIDATE");
				args = new String[3];
				args[0]=accountDate;
				args[1]=OpstartDate;
				args[2]=OpendDate;
				//logger.info("Select Query ==> " + query1);
				//logger.info("Args==> " + StringUtils.join(args,','));
				 result=this.mytemplate.queryForInt(query1,args);
				 if(result>0)
					 break;
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return result;
	}

	public String getAllocationDisableStatus(String contractNo, String layerNo) {
		String status="Y";
		if(StringUtils.isBlank(contractNo)){
			contractNo="";
		}
		try {
			String query = getQuery("GET_ALLOCATION_DISABLE_STATUS");
			status= (String)this.mytemplate.queryForObject(query,new Object[]{contractNo,layerNo},String.class);
			System.out.println(status);
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return status;
	}

	public void UpdateRenewalStatus(String proposalNo) {
		try{
			String query1 = "SELECT OLD_CONTRACTNO FROM POSITION_MASTER PM WHERE Proposal_No = ? And Amend_Id = (Select   Max (Amend_Id) From   Position_Master Where   Position_Master.Proposal_No =PM.PROPOSAL_NO)";
			String conNo=this.mytemplate.queryForObject(query1,new Object[]{proposalNo},String.class);
			String query = "Update   Position_Master pm Set   Renewal_Status = '1' Where  CONTRACT_NO = ? And BASE_LAYER IS NULL AND Amend_Id = (Select   Max (Amend_Id) From   Position_Master Where   Position_Master.Proposal_No =PM.PROPOSAL_NO)";
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " + conNo);
			this.mytemplate.update(query,new Object[]{conNo});
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
	}

	public String getUnderWriterLimmit(String uwName, String processId,String productId,String deptId){
		String uwLimit=null;
		try{
			logger.info("Select Query==> " + getQuery(DBConstants.COMMON_SELECT_GETUWLIMIT1));
			logger.info("Arg[0]==> " + uwName);
			logger.info("Arg[1]==> " + productId);
			logger.info("Arg[2]==> " + deptId);
			List<Map<String,Object>> uwList=null;
			uwList=this.mytemplate.queryForList(getQuery(DBConstants.COMMON_SELECT_GETUWLIMIT1),new Object[]{uwName,productId,deptId});
			if(uwList!=null && uwList.size()>0)
			{
				Map<String,Object> uwMap=(Map<String,Object>)uwList.get(0);
				uwLimit=uwMap.get("UWLIMIT")==null?"0":decimalFormat(Double.parseDouble(uwMap.get("UWLIMIT").toString()),0);
			}else
				uwLimit ="0";
			logger.info("UW Limit==> " + uwLimit);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return uwLimit;
	}
	public String getDisableStatus1(String contractNo, String layerNo) {
		String status="Y";
		if(StringUtils.isBlank(contractNo)){
			contractNo="";
		}if(StringUtils.isBlank(layerNo)){
			layerNo="";
		}
		try {
			String query = getQuery("GET_DISABLE_STATUS1");
			status= (String)this.mytemplate.queryForObject(query,new Object[]{contractNo,layerNo,contractNo,layerNo},String.class);
			System.out.println(status);
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return status;
	}
	public List<Map<String, Object>> getCurrencyMasterDropDown2(String branchCode, String countryId, String currencyId) {
		logger.info("DropDownControllor getCurrencyMasterDropDown() || Enter  branchCode=>"+branchCode+"countryId=>"+countryId);
		List<Map<String,Object>> personalInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery(DBConstants.COMMON_SELECT_GETCURRENCYLIST_BANK);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + countryId);
			logger.info("Args[2]==> " + "Y");
			logger.info("Args[3]==> " + currencyId);
			//personalInfo=this.mytemplate.queryForList(query,new Object[]{branchCode,countryId,"Y",currencyId});
			personalInfo=this.mytemplate.queryForList(query,new Object[]{branchCode,countryId,"Y",currencyId});

		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return personalInfo;
	}

	public String getCeaseaccountStatus(RiskDetailsBean bean) {
		logger.info("DropDownControllor getCeaseStatus() || Enter )");
		String ceaseStatus="";
		int count=0;
		try{
			String query="select NETDUE_OC,ALLOCATED_TILL_DATE ,NETDUE_OC- ALLOCATED_TILL_DATE from rsk_premium_details where CONTRACT_NO=? union all select PAID_AMOUNT_OC,ALLOCATED_TILL_DATE, PAID_AMOUNT_OC-ALLOCATED_TILL_DATE from ttrn_claim_payment where CONTRACT_NO=?";
		List<Map<String,Object>>list=this.mytemplate.queryForList(query,new Object[]{bean.getContractNo(),bean.getContractNo()});
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				if(!((map.get("NETDUE_OC")==null?"":map.get("NETDUE_OC").toString()).equalsIgnoreCase((map.get("ALLOCATED_TILL_DATE")==null?"":map.get("ALLOCATED_TILL_DATE").toString())))){
					count=count+1;
				}
				
			}
		}
		if(count>0){
		ceaseStatus="N";
		}

		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return ceaseStatus;
	}
	public int getCliampaymnetCount(String claimNo, String contNo) {
		int result=0;
		
		try{
		String query1="select count(*) from TTRN_CLAIM_PAYMENT where claim_no=? and CONTRACT_NO =?  ";
		Object[] args = new String[2];
		args[0]=claimNo;
		args[1]=contNo;
		//args[2]=opendDate;
		//logger.info("Select Query ==> " + query1);
		//logger.info("Args==> " + StringUtils.join(args,','));
		 result=this.mytemplate.queryForInt(query1,args);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return result;
	}
	public void copyDatatoDeleteTable(FaculPremiumBean bean) {
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
				    logger.info(data+" ,"+header+" ,"+Tables[p]+" ,"+bean.getUserName());
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
	public void copyClaimDetailDatatoDeleteTable(ClaimBean bean) {
		String data="";
		String header="";
		String qry="";
		String[] Tables=new String[2];
		Tables[0]="TTRN_CLAIM_DETAILS";
		Tables[1]="TTRN_CLAIM_UPDATION";
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
			ResultSet rs = stmt.executeQuery( "select * from  "+Tables[p]+" where CLAIM_NO='"+bean.getClaim_No()+"'");
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
				    logger.info(data+" ,"+header+" ,"+Tables[p]+" ,"+bean.getUserName());
					this.mytemplate.update(qry);
					data="";
					}
				}
			}
			
			qry="delete  from "+Tables[p]+" where CLAIM_NO='"+bean.getClaim_No()+"'";
			this.mytemplate.update(qry);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getCrestaIDList(String branchCode, String crestaValue) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_CRESTAID_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{branchCode,StringUtils.isBlank(crestaValue)?"":crestaValue});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Map<String, Object>> getCrestaNameList(String branchCode, String crestaValue) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_CRESTA_NAME_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{branchCode,StringUtils.isBlank(crestaValue)?"":crestaValue});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Map<String, Object>> getCurrencyShortList(String branchCode,String countryId) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_CURRENCY_SHORT_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{branchCode,countryId,"Y"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Map<String, Object>> getBonusList() {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_BONUS_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{"23","Y"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public void updateSubClass(String proposalNo, String type) {
		try {
			String query = "call UPDATE_SUB(?,?)";
			logger.info("Endorsement==>"+query);
			logger.info("Args[0]==>" +proposalNo );
			
			int result = this.mytemplate.update(query,new Object[]{proposalNo,type});
			System.out.println(result);
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
			e.printStackTrace();
		}
	}

	public String getCrestaName(String branchCode, String crestaValue) {
		String crestaName="";
		
		try {
			String query=getQuery("GET_CRESTA_NAME_LIST");
		List<Map<String,Object>>result=this.mytemplate.queryForList(query,new Object[]{branchCode,crestaValue});
		if(result!=null && result.size()>0){
			Map<String,Object> res=result.get(0);
			crestaName=(res.get("CRESTA_NAME")==null?"":res.get("CRESTA_NAME").toString());
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return crestaName;
	}

	public List<Map<String, Object>> getTypeList(String type,String branchCode) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_COMMISSION_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{type,"Y"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}

	public List<Map<String, Object>> getReinstatementOptionList(RiskDetailsBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_REINSTATEMENT_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{"33","Y"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Map<String, Object>> getReinstatementTypeList(RiskDetailsBean bean) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_REINSTATEMENT_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{"32","Y"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public List<Map<String, Object>> getUnderwriterCountryList(RiskDetailsBean bean, String branchCode) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		try {
			String query=getQuery("GET_UNDERWRITER_COUNTRY_LIST");
			result=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",bean.getLeader_Underwriter()});
			for(int i=0;i<result.size();i++){
	               Map<String,Object> tempMap = result.get(i);
	               bean.setLeader_Underwriter_country(tempMap.get("COUNTRY_NAME")==null?"":tempMap.get("COUNTRY_NAME").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String getUnderwriterCountryName(RiskDetailsBean bean,String branchCode) {
		String result="";
		try {
			String query=getQuery("GET_UNDERWRITER_COUNTRY_NAME");
			result=(String) this.mytemplate.queryForObject(query,new Object[]{branchCode,"Y",bean.getLeader_Underwriter_country()},String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static Set<String> findDuplicates(List<String> listContainingDuplicates) {
		 
		final Set<String> setToReturn = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();
 
		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
}

	public List<Map<String, Object>> getPreDepartmentDropDown( String branchCode, String productId, String status, FaculPremiumBean bean) {
		List<Map<String,Object>> department=new ArrayList<Map<String,Object>>();
		try{
			String count="";
			if("2".equals(productId)){
				count=getCombinedClass(branchCode,productId,bean.getDepartmentId());
			}if(StringUtils.isNotBlank(count) && "2".equals(productId)){

				String query=getQuery("common.department.combined.premiumclaim");
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productId);
				logger.info("Args[2]==> " + count);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,count});	
			}else{
			
				String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST_PRECLAIM);
				logger.info("Select Query==> " + query);
				logger.info("Args[0]==> " + branchCode);
				logger.info("Args[1]==> " + productId);
				logger.info("Args[2]==> " + status);
				department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,status,bean.getContNo(),bean.getLayerno()});	
			if(department.size()==0){
				 query = getQuery("DEPARTMENT_VALUE");
				 department=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,status,bean.getContNo(),bean.getLayerno()});	
			}
			}
			}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return department;
	}

	public List<Map<String, Object>> productIdList(String branchCode) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		logger.info("Enter ino product List");
		try{
		String query=getQuery("GET_PREM_PRODUC_ID_LIST");
		Object[] args = new String[1];
		args[0]=branchCode;
		
		logger.info("Select Query ==> " + query);
		list=this.mytemplate.queryForList(query,args);
		
		}
		catch(Exception e){
			logger.debug("Exception "+e);
		}
		logger.info("Exit ino product List");
		return list;
	}
	public int getSumOfInstallmentBooked(String contractNo,String layerNo) {
		int amount=0;
		try {
			Object obj[]=new Object[2];
			obj[0]=contractNo;
			obj[1]=layerNo;
			String query=getQuery("GET_SUM_INSTALLMENT_BOOKED");
			logger.info("Query=>"+query);
	           logger.info("Args=>"+StringUtils.join(obj, ","));
			amount=this.mytemplate.queryForInt(query, obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

	public int getCountOfInstallmentBooked(String contractNo,String layerNo) {
		int count=0;
		try {
			Object obj[]=new Object[2];
			obj[0]=contractNo;
			obj[1]=layerNo;
			String query=getQuery("GET_COUNT_INSTALLMENT_BOOKED");
			logger.info("Query=>"+query);
	           logger.info("Args=>"+StringUtils.join(obj, ","));
	           count=this.mytemplate.queryForInt(query, obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void UpdateInstallmentTransaction(String proposalNo) {
		try {
			String query=getQuery("UPDATE_INSTALLMENT_TRANSACTION");
			logger.info("Query=>"+query);
	         this.mytemplate.update(query,new Object[]{proposalNo});

		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public List<Map<String, Object>> getTerritoryRegionList(String branchCode) {
		logger.info("DropDown getTerritoryRegionList || Enter");
		List<Map<String,Object>> List=null;
		try{
			String query="SELECT TERRITORY_ID,TERRITORY_NAME FROM TMAS_TERRITORY WHERE BRANCH_CODE=? ORDER BY CASE WHEN TERRITORY_NAME like 'India%' THEN 1 ELSE  2 END, TERRITORY_NAME ";
			Object[] args=new String[1];
			args[0]=branchCode;
			logger.info("Select Query====>"+query);
			logger.info("Args==> " + StringUtils.join(args,","));
			List=mytemplate.queryForList(query, args);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("DropDown getTerritoryRegionList || Exit");
		return List;
	}
	public List<Map<String, Object>> setTerritoryCountryList(String branchCode,String territoryId,String countryExclude, String type) {
		logger.info("DropDown setTerritoryCountryList || Enter");
		List<Map<String,Object>> List=null;
		territoryId = territoryId.replaceAll(",", "','");
		countryExclude =StringUtils.isNotBlank(countryExclude)? countryExclude.replaceAll(",", "','"):"";
		try{
			String query= LocalizedTextUtil.findDefaultText("GET_TERRITORY_COUNTRY_LIST", Locale.ENGLISH, new String[]{branchCode,territoryId});
			if("edit".equalsIgnoreCase(type)){
				query=query+"AND TC.COUNTRY_ID  in ('"+countryExclude+"')";
			}
			
			logger.info("Select Query====>"+query);
			List=mytemplate.queryForList(query);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("DropDown setTerritoryCountryList || Exit");
		return List;
	}
	public List<Map<String, Object>> getExcludeCountry(String branchCode,String territoryId, String countryExcluded, String countryMode) {
		logger.info("DropDown getExcludeCountry || Enter");
		List<Map<String,Object>> List=null;
		territoryId = territoryId.replaceAll(",", "','");
		countryExcluded =StringUtils.isNotBlank(countryExcluded)? countryExcluded.replaceAll(",", "','"):"";
		try{
			String query= LocalizedTextUtil.findDefaultText("GET_TERRITORY_COUNTRY_LIST", Locale.ENGLISH, new String[]{branchCode,territoryId});
			if("edit".equalsIgnoreCase(countryMode)){
				query=query+"AND TC.COUNTRY_ID  in ('"+countryExcluded+"')";
			}
			
			logger.info("Select Query====>"+query);
			List=mytemplate.queryForList(query);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("DropDown getExcludeCountry || Exit");
		return List;
	}

	public String getCopyQuote(String type, String productId,String branchCode, String proposalNo) {
		String newProposalNo="";
		Connection con = null;
		CallableStatement cstmt = null;
		try{
		con = this.mytemplate.getDataSource().getConnection();
		//cstmt = con.prepareCall(spName);
		cstmt = con.prepareCall("{CALL copyquote(?,?,?,?,?)}");
		cstmt.setString(1, type);
		cstmt.setString(2, "");
		cstmt.setString(3, productId);
		cstmt.setString(4, branchCode);
		cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
		cstmt.setString(5, proposalNo);
		boolean count = cstmt.execute();
		newProposalNo = cstmt.getString(5);
		logger.info("result==> " + newProposalNo);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("DropDown getExcludeCountry || Exit");
		return newProposalNo;
	}

	public List<Map<String, Object>> getDocType(String branchCode,String productId, String moduleType) {
		List<Map<String,Object>> docList= null;
		try{
			String query=getQuery(DBConstants.UPLOAD_GETDOCTYPELIST);
			logger.info("Select Query=>"+query);
			logger.info("Args[0]=>"+branchCode);
			logger.info("Args[1]=>"+productId);
			logger.info("Args[2]=>"+moduleType);
			logger.info("Args[3]=>"+"Y");
			if(moduleType==null){
				moduleType="";	
			}
			docList=this.mytemplate.queryForList(query,new Object[]{branchCode,productId,moduleType,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
		}
		return docList;
	}

	
public String getVersionDetails(){
	String result="";
	try{
			String query=getQuery("GET_VERSION_CONTROL_DETAILS");
			result =this.mytemplate.queryForObject(query,String.class);
		}
		catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
		}
		return result;

}

public String getpreReopendDate(String contractNo, String claimNo, String type) {
	String result="";
	try{
		String query="";
		if("Reopen".equalsIgnoreCase(type)){
			query=getQuery("GET_PRE_REOPEN_DATE");
		}else if("RetroReopen".equalsIgnoreCase(type)){
			query=getQuery("GET_PRE_REOPEN_DATE_RETRO");
		}else if("RetroReputed".equalsIgnoreCase(type)){
			query=getQuery("GET_PRE_REOPEN_DATE_RETRO");
		}
		else {
			query=getQuery("GET_PRE_REPUTED_DATE");
		}logger.info("Select Query ==> " + query);
		logger.info("Args[0]==> " +contractNo);
		logger.info("Args[1]==> " +claimNo);
		
		result=(String)this.mytemplate.queryForObject(query,new Object[]{contractNo,claimNo},String.class);
		System.out.println(result);
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return result;
}

public List<Map<String, Object>> getProductieModuleDropDown(String branchCode,String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String,Object>> productList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETIEPRODUCTLIST);
		if(mode.equals("edit")){
		query=query+" AND TMAS_PRODUCT_ID IN(Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='1' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";
		query=query+" order by TMAS_PRODUCT_ID";
		logger.info("Select Query==> " + query);
		productList=this.mytemplate.queryForList(query, new Object[]{branchCode,proposalNo,transactionNo,type,transactionNo});
		}else{
		query=query+" order by TMAS_PRODUCT_ID";
		logger.info("Select Query==> " + query);
		logger.info("Arg[0]==> " + branchCode);
		productList=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
	}
	catch (Exception e) {
		logger.debug("Exception @ {" + e + "}");
	}

	return productList;
}

public List<Map<String, Object>> getSubClassDropDown(String branchCode, String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String,Object>> subClassList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETSUBPROFITCENTERLISTIE);
		if(mode.equals("edit")){
		query=query+"AND TMAS_SPFC_ID IN(Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='5' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";

		query=query+" order by TMAS_PRODUCT_ID,TMAS_DEPARTMENT_ID";
		logger.info("Select Query==> " + query);
		subClassList=this.mytemplate.queryForList(query, new Object[]{branchCode,proposalNo,transactionNo,type,transactionNo});
		}else{
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			subClassList=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
		
	}
	catch (Exception e) {
		logger.debug("Exception @ {" + e + "}");
	}

	return subClassList;
}



public List<Map<String, Object>> getInwardBusinessTypeDropDown(String categoryId, String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETCONSTDETIE);
		if(mode.equals("edit")){
		query=query+"AND TYPE IN(Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='2' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";
		query=query+" ORDER BY DETAIL_NAME";
		logger.info("Select Query==> " + query);
		constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y",proposalNo,transactionNo,type,transactionNo});
		}else{
		logger.info("Select Query==> " + query);
		logger.info("Args[0]==> " + categoryId);
		logger.info("Args[1]==> " + "Y");
		query=query+" ORDER BY DETAIL_NAME";
		constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
		}
		
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return constantList;
}

public List<Map<String, Object>> getTreatyTypeDropDown(String categoryId, String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETCONSTDETIE1);
		if(mode.equals("edit")){
			query=query+"AND TYPE IN(Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='6' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";
			query=query+" ORDER BY DETAIL_NAME";
			logger.info("Select Query==> " + query);
			constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y",proposalNo,transactionNo,type,transactionNo});
			}else{
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + categoryId);
			logger.info("Args[1]==> " + "Y");
			query=query+" ORDER BY DETAIL_NAME";
			constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
			}
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return constantList;
}

public List<Map<String, Object>> getProfitCentreieModuleDropDown( String branchCode, String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String, Object>> profitCenterList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETPROFITCENTERLISTIE);
		if(mode.equals("edit")){
		query=query+" AND TMAS_PFC_ID IN  (Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='3' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";
		query=query+" ORDER BY TMAS_PFC_NAME ASC";
		logger.info("Select Query==> " + query);
		profitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",proposalNo,transactionNo,type,transactionNo});	
		}else{
			query=query+" ORDER BY TMAS_PFC_NAME ASC";
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			profitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});	
		}
		
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return profitCenterList;
}

public List<Map<String, Object>> getDepartmentieModuleDropDown(String branchCode, String proposalNo,String transactionNo,String type,String mode) {
	List<Map<String, Object>> departmentList=new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_GETDEPRTIELISTIE);
		if(mode.equals("edit")){
		query=query+" AND TMAS_DEPARTMENT_ID IN (Select ITEM_TYPE from TTRN_IE_MODULE where PROPOSAL_NO=? and TRANSACTION_NO=? and ITEM_NO='4' and ITEM_INCLUSION_EXCLUSION=?  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM TTRN_IE_MODULE TI WHERE TI.PROPOSAL_NO=PROPOSAL_NO AND TI.TRANSACTION_NO = ? AND TI.ITEM_NO = ITEM_NO AND TI.ITEM_INCLUSION_EXCLUSION = ITEM_INCLUSION_EXCLUSION ))";
		query=query+" ORDER BY   TMAS_DEPARTMENT_NAME ASC";
		logger.info("Select Query==> " + query);
		departmentList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",proposalNo,transactionNo,type,transactionNo});
		}else{
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			departmentList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return departmentList;
}
public List<Map<String, Object>> getDepartmentRenewalStatisticsDropDown(String branchCode,String productCode, String uwfrom,String uwto,String contractNo) {
	List<Map<String, Object>> departmentList=new ArrayList<Map<String,Object>>();
	try{
		String query="SELECT DISTINCT TMAS_DEPARTMENT_ID ,TMAS_DEPARTMENT_NAME FROM TMAS_DEPARTMENT_MASTER WHERE BRANCH_CODE=? AND TMAS_PRODUCT_ID=? AND TMAS_STATUS=?";
		query=query+" AND TMAS_DEPARTMENT_ID IN (SELECT DISTINCT RSK_DEPTID FROM  TTRN_RISK_DETAILS TRD WHERE RSK_UWYEAR BETWEEN ? AND ? AND SUBSTR(RSK_CONTRACT_NO, 1, LENGTH(RSK_CONTRACT_NO) - 1)=SUBSTR(?, 1, LENGTH(?) - 1)  AND RSK_ENDORSEMENT_NO=(SELECT MAX(RSK_ENDORSEMENT_NO) FROM TTRN_RISK_DETAILS WHERE RSK_PROPOSAL_NUMBER=TRD.RSK_PROPOSAL_NUMBER AND BRANCH_CODE= TRD.BRANCH_CODE))";
		query=query+" ORDER BY   TMAS_DEPARTMENT_NAME ASC";
		logger.info("Select Query==> " + query);
		logger.info("Args[0]==> " + branchCode);
		logger.info("Args[1]==> " + productCode);
		logger.info("Args[2]==> " + "Y");
		logger.info("Args[3]==> " + uwfrom);
		logger.info("Args[4]==> " + uwto);
		logger.info("Args[5]==> " + contractNo);
		logger.info("Args[6]==> " + contractNo);
		departmentList=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,"Y",uwfrom,uwto,contractNo,contractNo});
		
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");	
	}

	return departmentList;
}

public List<Map<String, Object>> RenewalDropDown(String branchCode, String productCode,String status) {
	 List<Map<String, Object>> department = new ArrayList<Map<String,Object>>();
	try{
		String query=getQuery(DBConstants.COMMON_SELECT_DEPARTMENTLIST);
		logger.info("Select Query==> " + query);
		logger.info("Args[0]==> " + branchCode);
		logger.info("Args[1]==> " + productCode);
		logger.info("Args[2]==> " + status);
		department=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,status});	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return department;
}


public List<Map<String, Object>> getbankCurrencyList(CedingMasterBean bean) {
	 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	try{
		try{
			String query=getQuery("GET_BANK_CURRENCY_LIST");
			Object args[]= new Object[2];
			args[0]="Y";
			args[1]="06";
			list=this.mytemplate.queryForList(query,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<Map<String, Object>> getbroGroupList(CedingMasterBean bean) {
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


	public List<Map<String, Object>> getSubProfitCentreMultiDropDown(String deptid,String branchCode,String productCode,String subProfitId) {
	List<Map<String,Object>> subProfitCenterList=new ArrayList<Map<String,Object>>();
	try{
		String query="SELECT DISTINCT TMAS_SPFC_ID ,TMAS_SPFC_NAME  FROM  TMAS_SPFC_MASTER WHERE  BRANCH_CODE=? AND TMAS_PRODUCT_ID=? and TMAS_DEPARTMENT_ID=? AND TMAS_STATUS=?";
		if(subProfitId!=null && !subProfitId.equalsIgnoreCase("ALL")){
		query=query+" and TMAS_SPFC_ID in(select * from table(SPLIT_TEXT_FN(replace(?,' ', ''))))";
		query=query+" ORDER BY TMAS_SPFC_NAME ASC";
		subProfitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,deptid,"Y",subProfitId});
		}else{
			query=query+" ORDER BY TMAS_SPFC_NAME ASC";
			subProfitCenterList=this.mytemplate.queryForList(query,new Object[]{branchCode,productCode,deptid,"Y"});
		}
		logger.info("Select Query==> " + query);
		logger.info("Args[0]==> " + branchCode);
		logger.info("Args[1]==> " + productCode);
		logger.info("Args[2]==>" + deptid);
		
		logger.info("Args[3]==> " + "Y");
		logger.info("Args[4]==> " + subProfitId);
	}catch(Exception e){
		logger.debug("Exception @ {" + e + "}");
	}

	return subProfitCenterList;
}

	public int DuplicateCountCheck(String uwYear, String branchCode,String pid, String type, String ProposalNo) {
		int count=0;
		try{
			String query=getQuery("DUPLICATE_COUNT_CHECK");
			Object args[]=new Object[4];
			args[0]=uwYear;
			args[1]=branchCode;
			args[2]=pid;
			args[3]=type;
			if(StringUtils.isNotBlank(ProposalNo)){
				query +="  and PROPOSAL_NO !="+ProposalNo;
			}
			count =this.mytemplate.queryForInt(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	public List<Map<String, Object>> getConstantDropDownET(String categoryId, String contractNo) {
		List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		String query ="";
		String transNo ="";
		String contNo ="";
		try{
			 query=getQuery(DBConstants.COMMON_SELECT_GETCONSTDET);
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + categoryId);
			logger.info("Args[1]==> " + "Y");
			constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
			String qry="SELECT  COUNT(TRANSACTION_NO) TRANSACTION_NO,COUNT(CONTRACT_NO) CONTRACT_NO FROM TTRN_MND_INSTALLMENTS T WHERE  CONTRACT_NO=? AND ENDORSEMENT_NO=(SELECT MAX(ENDORSEMENT_NO) FROM TTRN_MND_INSTALLMENTS WHERE CONTRACT_NO=T.CONTRACT_NO and PROPOSAL_NO = T.PROPOSAL_NO)";
			Object args[]=new Object[1];
			args[0] = contractNo;
			list = this.mytemplate.queryForList(qry,new Object[]{contractNo});
			if (list.size()>0) {			
				for (int i = 0; i < list.size(); i++) {				
					Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
					transNo = tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString();
					contNo =  tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString();
				}
			}
			if(transNo.equalsIgnoreCase(contNo) && contractNo !=null && contractNo!=""){
				map.put("DETAIL_NAME","GNPI");
				map.put("TYPE","GNPI");
				constantList.add(map);
			}
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return constantList;
	}

	public List<Map<String, Object>> getGNPIList(String contNo, String layerno,String type, String productId, String mode) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		String query ="";
		int count =0;
		try{
			
			if("3".equalsIgnoreCase(productId)){
				String qry = getQuery("PREMIUM_COUNT");
				 count = this.mytemplate.queryForInt(qry,new Object[]{contNo,layerno});
				if(count <= 0 || "edit".equalsIgnoreCase(mode)){
				query=getQuery("GET_GNPI_LIST");
			}
			else{
				query=getQuery("GET_GNPI_LIST_CON");	
			}
			}
			else{
				String qry = getQuery("PREMIUM_COUNT_RETRO");
				count = this.mytemplate.queryForInt(qry,new Object[]{contNo,layerno});
				if(count <= 0 || "edit".equalsIgnoreCase(mode)){
					query=getQuery("GET_GNPI_LIST");
				}
				else{
					query=getQuery("GET_GNPI_LIST_CON_RETRO");	
				}
			}
			result=this.mytemplate.queryForList(query,new Object[]{contNo,layerno,type});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void updateEditMode(String proposalNo, String val,String updateProposalNo) {
		try{
			String query=getQuery("POS_MAS_ED_MODE_UPDT");
			Object args[] = new Object[2];
			if(!"N".equalsIgnoreCase(val)){
				args[0] = val +"-"+ updateProposalNo;
				}
				else{
					args[0] = val;	
				}
			args[1] = proposalNo;
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public String EditModeStatus(String proposalNo, String layerNo) {
		String result="";
		try{
			String query=getQuery("POS_MAS_ED_MODE_SELECT");
			Object args[] = new Object[1];
			//args[1] = layerNo;
			args[0] = proposalNo;
			result = this.mytemplate.queryForObject(query,args,String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void updateSubEditMode(String proposalNo, String val, String updateProposalNo) {
		try{
			String query=getQuery("UPDATE_SUB_ENDT_STATUS");
			Object args[] = new Object[2];
			if(!"N".equalsIgnoreCase(val)){
			args[0] = val +"-"+ updateProposalNo;
			}
			else{
				args[0] = val;	
			}
			args[1] = proposalNo;
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getBaseProposal(String proposalNo) {
		String result="";
		try{
			String query=getQuery("GET_BASE_PROPOSAL");
			Object args[] = new Object[1];
			args[0] = proposalNo;
			result = this.mytemplate.queryForObject(query,args,String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void updateRenewalEditMode(String proposalNo, String val,String updateProposalNo) {
		try{
			String query=getQuery("GET_BASE_PROPOSAL_NO");
			Object args[] = new Object[1];
			args[0] = proposalNo;
			String proposal  = this.mytemplate.queryForObject(query,args,String.class);
			if(!"0".equalsIgnoreCase(proposal)){
			updateSubEditMode(proposal,val,updateProposalNo);
			updateEditMode(proposal,val,updateProposalNo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Map<String, Object>> getConstantDropDownBusinessType(String categoryId, String pid, String deptId) {
		String query="";
		List<Map<String,Object>> constantList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
		query=getQuery(DBConstants.COMMON_SELECT_GETCONSTDET);
		logger.info("Select Query==> " + query);
		logger.info("Args[0]==> " + categoryId);
		logger.info("Args[1]==> " + "Y");
		if(!"16".equalsIgnoreCase(deptId)){
			query+=" and CATEGORY_DETAIL_ID !='7'";
		}else{
			query+=" and  CATEGORY_DETAIL_ID !='2'";
		}
		query +=" ORDER BY TYPE";
		constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
	}
		catch(Exception e){
			e.printStackTrace();
		}
		return constantList;
}

	public List<Map<String, Object>> getYearListValue(String inceptionDate) {
		List<Map<String, Object>> yearsList = new  ArrayList<Map<String, Object>>();
		if(StringUtils.isNotBlank(inceptionDate)){
			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 String format = "dd/MM/yyyy" ;
		     SimpleDateFormat sdf = new SimpleDateFormat(format) ;
		      Date dateAsObj = null;
			try {
					dateAsObj = sdf.parse(inceptionDate);
			} catch (ParseException e) {
					e.printStackTrace();
			}
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(dateAsObj);
		       // cal.add(Calendar.MONTH, nbMonths);
          int year =cal.get(Calendar.YEAR);
          int month =cal.get(Calendar.MONTH);
          int day =cal.get(Calendar.DATE);
		        //Date dateAsObjAfterAMonth = cal.getTime() ;
		   Map<String, Object> year1 = new HashMap<String, Object>();
		   Map<String, Object> year2 = new HashMap<String, Object>();
		   year1.put("YEAR", year);
		   yearsList.add(year1);
		  if(month ==11){
			  if(day>=25){
				  cal.add(Calendar.YEAR, 1);
				  year =cal.get(Calendar.YEAR);
				  year2.put("YEAR", year);
				  yearsList.add(year2);
			  }
		  }
		  else if(month==0){
			  if(7 >=day){
				 cal.add(Calendar.YEAR,-1);
				  year =cal.get(Calendar.YEAR);
				  year2.put("YEAR", year);
				  yearsList.add(year2);
			  }
		  }
		  
		}
		return yearsList;
	}

	public String getMenuId(String processId) {
		String res ="";
		try{
			String query=getQuery("GET_MENU_ID");
			Object args[] = new Object[2];
			args[0] =processId;
			args[1] ="1";
			res =  this.mytemplate.queryForObject(query,args,String.class);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return res;
	}

	
	public String getContractLayerNo(String contNo, String layerNo,String branchCode, String productId, String proposalNo) {
		String res =" ";
		try{
			if("3".equalsIgnoreCase(productId) || "5".equalsIgnoreCase(productId)){
			String query=getQuery("GET_CON_DEPT_ID_LAYER");
			Object args[] = new Object[3];
			args[0] =contNo;
			args[1] = layerNo;
			args[2] =branchCode;
			res =  this.mytemplate.queryForObject(query,args,String.class);
			}
			else if("2".equalsIgnoreCase(productId)){
				String query=getQuery("GET_CON_DEPT_ID");
				Object args[] = new Object[3];
				args[0] =contNo;
				args[1] = layerNo;
				args[2] =branchCode;
				//args[3] = proposalNo;
				res =  this.mytemplate.queryForObject(query,args,String.class);
			}
			else if("1".equalsIgnoreCase(productId)){
				String query=getQuery("GET_CON_DETAILS");
				Object args[] = new Object[2];
				args[0] =contNo;
				args[1] =branchCode;
				res =  this.mytemplate.queryForObject(query,args,String.class);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public HashMap<String, String> getProposalStatus(String excludeProposalNo) {
		HashMap<String, String> res = new HashMap<String,String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			excludeProposalNo = excludeProposalNo.trim();
			excludeProposalNo = excludeProposalNo.replaceAll(",", "','");
			final String query = LocalizedTextUtil.findDefaultText("GET_PROPO_STATUS", Locale.ENGLISH, new String[]{excludeProposalNo});
			list = this.mytemplate.queryForList(query);
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				res.put(map.get("PROPOSAL_NO").toString(),map.get("CONTRACT_STATUS").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public String getExcludeProposalNo(String branchCode, String proposalNo,String transactionNo, String string, String string2) {
		String res="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_EXCLUDE_PROPOSAL");
			Object args[] = new Object[3];
			args[0] =proposalNo;
			args[1] = transactionNo;
			args[2] = branchCode;
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
			int j= list.size()-1;
			for(int i=0;i<list.size();i++){
				Map<String,Object> tempMap = list.get(i);
				if(i==j){
					res += tempMap.get("ITEM_TYPE")==null?"":tempMap.get("ITEM_TYPE").toString();
				}else{
					res += tempMap.get("ITEM_TYPE")==null?"":tempMap.get("ITEM_TYPE").toString()+",";
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String,Object>> getContractValidation(String productId, String cedingCompany,String inceptionDate, String expiryDate, String year,String originalCurrency, String departmentId, String type,String sumInsured, String ContNo, String profitCenter,String surplus, String coverPer, String dedPer, String layerNo, String branchCode) {
		String query="";
		List<Map<String,Object>> ContractList=new ArrayList<Map<String,Object>>();
		Object args[] = null;
		try{
			if(StringUtils.isBlank(sumInsured)){
				sumInsured="0";
			}
			if(StringUtils.isBlank(surplus)){
				surplus="0";
			}
			if(StringUtils.isBlank(coverPer)){
				coverPer="0";
			}
			if(StringUtils.isBlank(dedPer)){
				dedPer="0";
			}
			if(!cedingCompany.matches("^[0-9]+$")){
				cedingCompany="";
			}
			if("1".equalsIgnoreCase(productId)){
				query=getQuery("FAC_CONTRACT_LIST");
				 args = new Object[10];
				args[0] = cedingCompany;
				args[1] = inceptionDate;
				args[2] = expiryDate;
				args[3] = year;
				args[4] = originalCurrency;
				
				args[5] = departmentId;
				args[6] = type;
				args[7] = sumInsured;
				args[8] = profitCenter;
				args[9] = branchCode;
				if(ContNo!="0"){
					query+="  and RD.RSK_CONTRACT_NO!="+ContNo;
				}
			}
			else if("2".equalsIgnoreCase(productId)){
				query=getQuery("PTTY_CONTRACT_LIST");
				args = new Object[9];
				args[0] = cedingCompany;
				args[1] = inceptionDate;
				args[2] = expiryDate;
				args[3] = year;
				args[4] = originalCurrency;
				args[5] = departmentId;
				args[6] = type;
				args[7] = profitCenter;
				args[8] = branchCode;
				if("1".equalsIgnoreCase(type) ||"4".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type) ){
					query+="  and RP.RSK_LIMIT_OC="+sumInsured;
				}
				else if("2".equalsIgnoreCase(type)){
					query+="  and RP.RSK_TREATY_SURP_LIMIT_OC="+sumInsured;
				}
				else if("3".equalsIgnoreCase(type)){
					query+="  and RP.RSK_LIMIT_OC="+sumInsured+" and RP.RSK_TREATY_SURP_LIMIT_OC = "+surplus;
				}
				if(ContNo!="0"){
					query+="  and RD.RSK_CONTRACT_NO!="+ContNo;
				}
			}
			else if("3".equalsIgnoreCase(productId)){
				if(ContNo!="0"){
					query=getQuery("NPTTY_CONTRACT_LIST_CON");
					args = new Object[11];
					args[0] = cedingCompany;
					args[1] = inceptionDate;
					args[2] = expiryDate;
					args[3] = year;
					args[4] = originalCurrency;
					args[5] = departmentId;
					args[6] = type;
					args[7] = profitCenter;
					args[8] = layerNo;
					args[9] = ContNo;
					args[10] = branchCode;
				}
				else{
					query=getQuery("NPTTY_CONTRACT_LIST");
					args = new Object[10];
					args[0] = cedingCompany;
					args[1] = inceptionDate;
					args[2] = expiryDate;
					args[3] = year;
					args[4] = originalCurrency;
					args[5] = departmentId;
					args[6] = type;
					args[7] = profitCenter;
					args[8] = layerNo;
					args[9] = branchCode;
				}
				
				if("1".equalsIgnoreCase(type) ||"2".equalsIgnoreCase(type)||"3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type) ){
					query+="  and  E1.cvr_limit="+sumInsured+" AND E1.DEDU="+surplus;
				}
				else if("5".equalsIgnoreCase(type)){
					query+="  and  E1.cvr_limit="+sumInsured+" AND E1.DEDU="+surplus+" AND E1.COVER_PER = "+coverPer+" AND E1.DED_PER = "+dedPer ;
				}
			}
			logger.info("Query=>"+query);
			logger.info("Contract Mapping Args=>"+StringUtils.join(args, ","));
			ContractList =this.mytemplate.queryForList(query,args);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return ContractList;
	}

	public String getAnnualAgregateVal(String proposalNo,String policyContractNo, String layerNo, String branchCode,String departmentId) {
		String res="";
		try{
			String query=getQuery("GET_ANNUAL_AGR_VAL");
			Object args[]=new Object[5];
			args[0] = proposalNo;
			args[1] = policyContractNo;
			args[2] = layerNo;
			args[3] = branchCode;
			args[4] = departmentId;
			logger.info("Query=>"+query);
			logger.info("Args=>"+StringUtils.join(args, ","));
			res = this.mytemplate.queryForObject(query,args,String.class);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getDepartIdlist(String policyContractNo,String branchCode, String produciId, String deptId, String layerNo) {
		String query="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
		query=getQuery("GET_CLAIM_COVER_DEPARTMENT_ID");
		logger.info("Select Query==> " + query);
		Object args[] = new Object[5];
		 args[0] = policyContractNo;
		 args[1] = layerNo;
		 args[2] = produciId;
		 args[3] = produciId;
		 args[4] = branchCode;
		list=this.mytemplate.queryForList(query,args);
	}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getStatictisDepartmentDropDown(String branchCode, String productId, String string,String contractNo) {
		String query="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_STATISTIC_DEPARTMENT");
			Object args[]=new Object[4];
			args[0] = productId;
			args[1] ="Y";
			args[2] = branchCode;
			args[3] =contractNo;
			list = this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getPopUpDeptName(String branchCode,String deptId, String productId) {
		String query="";
		String res="";
		try{
			query=getQuery("GET_DEPT_NAME");
			Object args[]=new Object[3];
			args[0] =deptId;
			args[1]=productId;
			args[2] =branchCode;
			res=this.mytemplate.queryForObject(query,args,String.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public List<Map<String, Object>> getButtonSelectionList(String branchCode,String manufactureID, Object menuRights) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			map.put("TYPE","D");
			map.put("DETAIL_NAME","Document");
			list.add(map);
			map=new HashMap<String,Object>();
			map.put("TYPE","H");
			map.put("DETAIL_NAME","History");
			list.add(map);
			map=new HashMap<String,Object>();
			if( menuRights.toString().contains("EN")){
				map.put("TYPE","E");
				map.put("DETAIL_NAME","Edit");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("V")){
				map.put("TYPE","V");
				map.put("DETAIL_NAME","View");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("P")){
				map.put("TYPE","P");
				map.put("DETAIL_NAME","Premium");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("C")){
				map.put("TYPE","C");
				map.put("DETAIL_NAME","Claim");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("ST")){
				map.put("TYPE","S");
				map.put("DETAIL_NAME","Statistics");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("CS")){
				map.put("TYPE","CS");
				map.put("DETAIL_NAME","Classes");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			if( menuRights.toString().contains("R")){
				map.put("TYPE","R");
				map.put("DETAIL_NAME","Renewal");
				list.add(map);
				map=new HashMap<String,Object>();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getCountryDate(String branchCode, String DEC_FILE) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_CRESTA_DATE");
			Object args[]= new Object[2];
			args[0] =branchCode;
			args[1] =branchCode;
			list = this.mytemplate.queryForList(query,args);
			XSSFWorkbook workbook = new XSSFWorkbook();
		    XSSFSheet sheet = workbook.createSheet("Master");
		    
		    Map<String, Object[]> data = new TreeMap<String, Object[]>();
		    data.put("1", new Object[] {"Territory Code", "Cresta ID", "Cresta Name"});
		    int j=2;
		    for(int i=0;i<list.size();i++){
		    	Map<String,Object> map =list.get(i);
		    	data.put(Integer.toString(j), new Object[]{map.get("COUNTRY_NAME")==null?"":map.get("COUNTRY_NAME"),map.get("CRESTA_ID")==null?"":map.get("CRESTA_ID"),map.get("CRESTA_NAME")==null?"":map.get("CRESTA_NAME")});
		    	j++;
		    }
		    Set<String> keyset = data.keySet();
		    int rownum = 0;
		    for (String key : keyset)
		    {
		        XSSFRow row = sheet.createRow(rownum++);
		        Object [] objArr = data.get(key);
		        int cellnum = 0;
		        for (Object obj : objArr)
		        {
		           XSSFCell cell = row.createCell(cellnum++);
		           if(obj instanceof String)
		                cell.setCellValue((String)obj);
		            else if(obj instanceof Integer)
		                cell.setCellValue((Integer)obj);
		        }
		    }
		    XSSFSheet sheet1 = workbook.createSheet("Import");
		    Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
		    data1.put("1", new Object[] {"Territory Code", "Cresta ID", "Currency ","Accumulation Date","Accumulation of Risk"});
		    Set<String> keyset1 = data1.keySet();
		    int rownum1 = 0;
		    for (String key : keyset1)
		    {
		        XSSFRow row = sheet1.createRow(rownum1++);
		        Object [] objArr = data1.get(key);
		        int cellnum = 0;
		        for (Object obj : objArr)
		        {
		           XSSFCell cell = row.createCell(cellnum++);
		           if(obj instanceof String)
		                cell.setCellValue((String)obj);
		            else if(obj instanceof Integer)
		                cell.setCellValue((Integer)obj);
		        }
		    }
		    try
		    {
		        FileOutputStream out = new FileOutputStream(new File(DEC_FILE));
		        for (int k = 0; k<  5; k++) {
		        	sheet1.autoSizeColumn(k);
                }
		        for (int l = 0; l < 3; l++) {
		        	sheet.autoSizeColumn(l);
                }
		        workbook.write(out);
		        out.close();
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public List<Map<String, Object>> getSectionList(String contNo, String departmentId, String branchCode) {
		String query="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_SECTION_LIST");
			Object args[]=new Object[3];
			args[0] = contNo;
			args[1] =departmentId;
			args[2] = branchCode;
			list = this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public  boolean GetShareEqualValidation(String productId,String leaderUnderwriterShare, String proposalNo ) {
		boolean result=false;
		String query="";
		try {
			if("1".equals(productId)){
			 query=getQuery("GET_SIGN_SHARE_EQUAL_PRODUCT1");
			}else{
				query=getQuery("GET_SIGN_SHARE_EQUAL_PRODUCT23");
			}
			int count=this.mytemplate.queryForInt(query, new Object[]{leaderUnderwriterShare,proposalNo});
			if(count==0){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getConstantDropDownBasis(String categoryId,String pid, String deptId) {
		List<Map<String,Object>> constantList=new ArrayList<Map<String,Object>>();
		String query ="";
		try{
			query=getQuery("COMMON_SELECT_GETCONSTDET_BASIC");
			if(!"16".equalsIgnoreCase(deptId)){
				 query+=" and CATEGORY_DETAIL_ID!='3'";
			}
			query +="  ORDER BY TYPE";
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + categoryId);
			logger.info("Args[1]==> " + "Y");
			constantList=this.mytemplate.queryForList(query,new Object[]{categoryId,"Y"});
			
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return constantList;
	}

	public String getCoverLimitAmount(String proposalNo, String departmentId, String productId) {
		String coverLimitAmount="0";
		logger.info("Enter Into getCoverLimitAmount()");
		try{
			if("1".equalsIgnoreCase(productId)){
				String query=getQuery("GET_CLAIM_COVERLIMIT_FAC");
				logger.info("Query==>"+query);
				logger.info("obj==>"+proposalNo);
				coverLimitAmount=(String)this.mytemplate.queryForObject(query, new Object[]{proposalNo},String.class);
			}else{
				String query=getQuery("GET_CLAIM_COVERLIMIT");
				logger.info("Query==>"+query);
				logger.info("obj==>"+proposalNo,departmentId);
				coverLimitAmount=(String)this.mytemplate.queryForObject(query, new Object[]{proposalNo,departmentId},String.class);
			}
		}catch(Exception e){
			logger.debug("Exception "+e);
		}
		logger.info("Exit from GetPreviousPremium()"+coverLimitAmount);
		return coverLimitAmount;
	}

	public String getCurrenCyId(String claimNo, String contractNo,String layerNo,String productId) {
		String currency="";
		logger.info("Enter Into getCoverLimitAmount()");
		try{
			String query="";
			if("4".equalsIgnoreCase(productId)||"5".equalsIgnoreCase(productId)){
				query=getQuery("GET_CLAIM_CURRENCY_RETRO");
			}else{
			 query=getQuery("GET_CLAIM_CURRENCY");
			}
			logger.info("Query==>"+query);
			currency=(String)this.mytemplate.queryForObject(query, new Object[]{claimNo,contractNo,layerNo},String.class);
		}catch(Exception e){
			logger.debug("Exception "+e);
		}
		logger.info("Exit from GetPreviousPremium()"+currency);
		return currency;
	}

	public String getInceptionDateVal(String proposalNo, String contractNo) {
		String date="";
		try{
			String query = "SELECT to_char(M.INCEPTION_DATE,'dd/mm/yyyy') INCEPTION_DATE FROM POSITION_MASTER M WHERE M.CONTRACT_NO =? AND M.PROPOSAL_NO=?  AND M.AMEND_ID=(SELECT MAX(AMEND_ID) FROM POSITION_MASTER P WHERE P.PROPOSAL_NO=M.PROPOSAL_NO)";
			Object args[] = new Object[2];
			args[0] = contractNo;
			args[1] =proposalNo;
			List<Map<String,Object>>list=this.mytemplate.queryForList(query,args);
			if(list!=null &&list.size()>0){
				Map<String,Object>map=list.get(0);
				date=map.get("INCEPTION_DATE")==null?"":map.get("INCEPTION_DATE").toString();
			}
		}
		catch(Exception e){
			logger.debug("Exception "+e);
		}
		return date;
	}

		

	public void getSOATableInsert(String proposalNo, String contractno,String branchCode) {
		Connection con = null;
		CallableStatement cstmt = null;
		String error="";
		try {
			String query = "DELETE FROM TTRN_SOA_DUE WHERE PROPOSAL_NO=? AND CONTRACT_NO=? AND BRANCH_CODE=?";
			Object args[] = new Object[3];
			args[0]=proposalNo;
			args[1] =contractno;
			args[2] =branchCode;
			this.mytemplate.update(query,args);
			con = this.mytemplate.getDataSource().getConnection(); 	
			cstmt = con.prepareCall("{CALL PRC_SOA_PENDING_DUE(?,?,?,?)}");
			cstmt.setString(1,branchCode.trim() );	
			cstmt.setString(2, proposalNo.trim());
			cstmt.setString(3, contractno.trim());
			cstmt.registerOutParameter(4,java.sql.Types.VARCHAR );
			cstmt.setString(4, error);
			boolean count = cstmt.execute();
			logger.info("result==> " + error);
			
		}
		catch(Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					logger.debug("Exception @ {" + e + "}");
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.debug("Exception @ {" + e + "}");
				}
			}
		}
	}

	public String getStatementDate(String accountPeriodDate, String proposalNo,String contractno, String branchCode, String type, String deptId) {
		String result ="N";
		String query ="";
		try{
			Object args[]=new Object[3];
			if("date".equalsIgnoreCase(type)){
				args[0] = proposalNo;
				args[1] =contractno;
				args[2] = accountPeriodDate;
				query = "select nvl((SELECT to_char(t1.STATEMENT_DATE,'dd/mm/yyyy') FROM TTRN_SOA_DUE t1 WHERE t1.PROPOSAL_NO=?  AND t1.CONTRACT_NO=? AND decode(periodicity,'Yearly',add_months(t1.STATEMENT_DATE,12),'HalfYearly',add_months(t1.STATEMENT_DATE,6),'Quarterly',add_months(t1.STATEMENT_DATE,3))=to_date(?,'dd/mm/yyyy')),0) from dual";
			}else if("LC".equalsIgnoreCase(type) ){
				args[0] = proposalNo;
				args[1] =contractno;
				args[2] = "LPC";
				query ="SELECT nvl(SUB_SEC_CAL,0) FROM TTRN_BONUS B WHERE PROPOSAL_NO=? AND CONTRACT_NO =? AND TYPE =? AND B.ENDORSEMENT_NO=(SELECT MAX(ENDORSEMENT_NO) FROM TTRN_BONUS T WHERE  T.PROPOSAL_NO=B.PROPOSAL_NO AND T.CONTRACT_NO =B.CONTRACT_NO)";
			}else if("SC".equalsIgnoreCase(type)){
				args[0] = proposalNo;
				args[1] =contractno;
				args[2] = "SSC";
				query ="SELECT nvl(SUB_SEC_CAL,0) FROM TTRN_BONUS B WHERE PROPOSAL_NO=? AND CONTRACT_NO =? AND TYPE =? AND B.ENDORSEMENT_NO=(SELECT MAX(ENDORSEMENT_NO) FROM TTRN_BONUS T WHERE  T.PROPOSAL_NO=B.PROPOSAL_NO AND T.CONTRACT_NO =B.CONTRACT_NO)";
			}else if("PC".equalsIgnoreCase(type)){
				args=new Object[1];
				args[0] = proposalNo;
				query ="SELECT nvl(RSK_PRO_SUB_SEQ_CAL,0) FROM TTRN_RISK_COMMISSION B WHERE RSK_PROPOSAL_NUMBER=?  AND B.RSK_ENDORSEMENT_NO=(SELECT MAX(RSK_ENDORSEMENT_NO) FROM TTRN_RISK_COMMISSION T WHERE  T.RSK_PROPOSAL_NUMBER=B.RSK_PROPOSAL_NUMBER)";
			}
			else if("compare".equalsIgnoreCase(type)){
				//query="SELECT COUNT(*) FROM RSK_PREMIUM_DETAILS WHERE BRANCH_CODE=? AND ACCOUNTING_PERIOD_DATE =TO_DATE(?,'DD/MM/YYYY')  AND CONTRACT_NO=? AND  SUB_CLASS=? and ACCOUNT_PERIOD_QTR!='5'";
				query="SELECT COUNT(*) FROM RSK_PREMIUM_DETAILS_TEMP WHERE BRANCH_CODE=? AND ACCOUNTING_PERIOD_DATE =TO_DATE(?,'DD/MM/YYYY')  AND CONTRACT_NO=? AND  SUB_CLASS=? and ACCOUNT_PERIOD_QTR!='5' AND TRANS_STATUS !='R'";
				}
			 if(!"compare".equalsIgnoreCase(type)){
				 result =this.mytemplate.queryForObject(query, args,String.class);
			 }else{
				int count=this.mytemplate.queryForInt(query,new Object[]{branchCode,accountPeriodDate,contractno,deptId});
				result = Integer.toString(count);
				 }
			 logger.info("result==> " + result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public synchronized String getSequencePTRT(String type,String productID,String departmentId,String branchCode, String proposalNo, String trDate){ 

			String seqName="";
			try{
				String query="select Fn_get_SeqNo(?,?,?,?,?,?) from dual";
				
				logger.info("Type==> " + type + ", Product ID==> " + productID + ", dept ID==> " + departmentId + ", Branch Code==> " + branchCode+ ", Trans Date==> " + trDate);
				seqName=(String)this.mytemplate.queryForObject(query,new Object[]{type,productID,departmentId,branchCode,proposalNo,trDate},String.class);
				logger.info("Result==> " + seqName);
				
			}catch(Exception e){
				logger.debug("Exception @ {" + e + "}");
			}

			return seqName;
		}

	public List<Map<String, Object>> getLedgerDropDown(String branchCode) {
		logger.info("DropDownControllor getLedgerDropDown() || Enter  branchCode=>"+branchCode);
		List<Map<String,Object>> ledgerInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_LEDGER_LIST");
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[2]==> " + "Y");
			ledgerInfo=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return ledgerInfo;
	}

	public List<Map<String, Object>> getLedgerDeptDropDown(String branchCode) {
		logger.info("DropDownControllor getLedgerDeptDropDown() || Enter  branchCode=>"+branchCode);
		List<Map<String,Object>> ledgerInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_LEDGER_DEPT_LIST");
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[2]==> " + "Y");
			ledgerInfo=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return ledgerInfo;
	}

	public  String getTransactionNo(String startDate, String endDate,String branchCode) {
		String transactionNo="";
		logger.info("Enter Into getTransactionNo()");
		try{
			String query=getQuery("GET_JV_TRANID");
			logger.info("Query==>"+query);
			logger.info("Args[0]==> " + startDate);
			logger.info("Args[1]==> " + endDate);
			logger.info("Args[2]==> " + branchCode);
			transactionNo=(String)this.mytemplate.queryForObject(query, new Object[]{startDate,endDate,branchCode},String.class);
		}catch(Exception e){
			logger.debug("Exception "+e);
		}
		logger.info("Exit from getTransactionNo()"+transactionNo);
		return transactionNo;
	}

	public List<Map<String, Object>> getCoverDEpartmentDropDown(String branchCode, String pid, String departId) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String query="";
			try{
				if("17".equalsIgnoreCase(departId) || "18".equalsIgnoreCase(departId) ||"19".equalsIgnoreCase(departId) ){
					query=getQuery("GET_COVER_DEPT_LIST");
					list=this.mytemplate.queryForList(query,new Object[]{departId,branchCode,pid});
				}else{
					 //query=getQuery(DBConstants.COMMON_SELECT_GETDEPRTIELISTIE);
					 list=getDepartmentDropDown(branchCode,pid,"Y","","","","","");
				}
				logger.info("Select Query==> " + query);
				
			}catch(Exception e){
				logger.debug("Exception @ {" + e + "}");
				e.printStackTrace();
			}
			return list;
	}
	public String getCombinedClass(String branchCode,String productId,String departId){
		
		String count="";
		try {
			String query="SELECT CORE_COMPANY_CODE FROM TMAS_DEPARTMENT_MASTER WHERE BRANCH_CODE =? AND TMAS_PRODUCT_ID=? AND  TMAS_STATUS='Y' AND CORE_COMPANY_CODE IS NOT NULL AND TMAS_DEPARTMENT_ID IN(?) ";
			logger.info("Select Query==> " + query);
			List<Map<String,Object>> list=this.mytemplate.queryForList(query,  new Object[]{branchCode,productId,departId});
			if(list!=null && list.size()>0){
			Map<String,Object>map=list.get(0);
			count=map.get("CORE_COMPANY_CODE")==null?"":map.get("CORE_COMPANY_CODE").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Map<String,Object>> getRetroContractDropDown(String branchCode, String uwYear) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_RETERO_CONTRACT");
			list=this.mytemplate.queryForList(query,  new Object[]{uwYear,branchCode});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getpremiumPreamendDateRetro(FaculPremiumBean bean) {
		String result="";
		try{
			String query=getQuery("GET_PREMIUM_AMEND_DATE_RETRO");
			logger.info("Select Query ==> " + query);
			logger.info("Args[0]==> " +bean.getContNo());
			logger.info("Args[0]==> " +bean.getTransactionNo());
			
			result=(String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo(),bean.getTransactionNo()},String.class);
			System.out.println(result);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return result;
	}

	public String getProposalNo(String productId,String contractNo, String departId,String layerNo) {
			
		String query="";
		String proposal_No="";
		Object[] obj=null;
		if(StringUtils.isNotBlank(productId) &&("1".equals(productId) || "4".equals(productId)) ){
			query=getQuery("GET_FAC_PROPOSAL_NO");
			obj=new Object[1];
			obj[0]=contractNo;
			//proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		}else if("2".equals(productId)){
			query=getQuery("GET_PRO_PROPOSAL_NO");
			obj=new Object[2];
			obj[0]=contractNo;
			obj[1]=departId;
			//proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		}
		else if("3".equals(productId) || "5".equalsIgnoreCase(productId)){
			query=getQuery("GET_XOL_PROPOSAL_NO");
			obj=new Object[2];
			obj[0]=contractNo;
			obj[1]=layerNo;
			//proposal_No=this.mytemplate.queryForObject(query, obj , String.class);
		}
		List<Map<String,Object>>list=this.mytemplate.queryForList(query,obj);
		if(list!=null &&list.size()>0){
			Map<String,Object>map=list.get(0);
			proposal_No=map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString();
		}

		return proposal_No;
	}

	public int EditModeStatusCount(String contNo, String branchCode) {
		String query="";
		Object[] obj=null;
		int count=0;
		try{
			query=getQuery("GET_RETRO_EDIT_COUNT");
			obj=new Object[2];
			obj[0]=contNo;
			obj[1]=branchCode;
			count=this.mytemplate.queryForInt(query,obj);
		}catch(Exception e){
				logger.debug("Exception @ {" + e + "}");	
			}
		return count;
	}

	public String getSourceCode(String branchCode) {
		String query="";
		String code="";
		try{
			query = getQuery("GET_SOURCE_CODE");
			code = this.mytemplate.queryForObject(query, new Object[]{branchCode},String.class);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return code;
	}

	public String getCashLossCount(String contNo, String departmentId) {
		String query="";
		String code="";
		try{
			query = getQuery("GET_CASHLOSS_COUNT");
			code = this.mytemplate.queryForObject(query, new Object[]{contNo,departmentId},String.class);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return code;
	}

	public String getSectionName(String contNo, String departmentId,String branchCode) {
		String query="";
		String code="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_SECTION_LIST");
			Object args[]=new Object[3];
			args[0] = contNo;
			args[1] =departmentId;
			args[2] = branchCode;
			list = this.mytemplate.queryForList(query,args);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				//code=map.get("SECTION_NAME")==null?"":map.get("SECTION_NAME").toString();
				code=map.get("SECTION_NO")==null?"":map.get("SECTION_NO").toString();
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return code;
		
	}

	public List<Map<String, Object>> getProductDropDownSOAList(String branchCode) {

		List<Map<String,Object>> productList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("SOA_PRODUCT_LIST");
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			productList=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}

		return productList;
	}

	public List<Map<String,Object>> getTransactionList(String branchCode, String proposalNo, String date, String productId) {
		List<Map<String,Object>> transList=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			if(!"5".equalsIgnoreCase(productId)){
				query=getQuery("TRANSACTION_NO_LIST");
			}else{
				query=getQuery("TRANSACTION_NO_LIST_RETRO");
			}
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			transList=this.mytemplate.queryForList(query, new Object[]{branchCode,proposalNo,date});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}

		return transList;
	}

	public List<Map<String, Object>> getCompanyInfo(String branchCode) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			query=getQuery("GET_COMPANY_INFO");
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			list=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return list;
	}

	public List<Map<String, Object>> getYearToListValue(String inceptionDate, String expiryDate) {
		List<Map<String, Object>> yearsList = new  ArrayList<Map<String, Object>>();
		if(StringUtils.isNotBlank(inceptionDate)){
			 String format = "dd/MM/yyyy" ;
		     SimpleDateFormat sdf = new SimpleDateFormat(format) ;
		      Date dateAsObj = null;
		      Date dateAsObj1 = null;
			try {
					dateAsObj = sdf.parse(inceptionDate);
					dateAsObj1 = sdf.parse(expiryDate);
			} catch (ParseException e) {
					e.printStackTrace();
			}
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(dateAsObj);
		    int year =cal.get(Calendar.YEAR);
		    Calendar cal1 = Calendar.getInstance();
		    cal1.setTime(dateAsObj1);
		    int year1 =cal1.get(Calendar.YEAR);
         
		   for(int j=year;j<=year1;j++){
				Map<String,Object> yearl=new HashMap<String, Object>();
				yearl.put("YEAR", j);
				yearsList.add(yearl);
			}
		  
		}
		return yearsList;
	}

	public void updateBaseLayer(String proposalNo, String proposal_no) {
		try{
			String query=getQuery("UPDATE_BASE_LAYER");
			Object args[] = new Object[2];
			args[0] = proposalNo;
			args[1] = proposal_no;
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getPaymentPartnerlist(String branchCode, String cedingId,String brokerId) {
		List<Map<String,Object>> personalInfo=new ArrayList<Map<String,Object>>();
		try{
			Object arg[]=null;
			if(StringUtils.isNotBlank(cedingId) && StringUtils.isNotBlank(brokerId)) {
			String query=getQuery("GET_PAYMENT_PARTNER_LIST");
			if(!"63".equals(brokerId)){
				query+=getQuery("GET_PAYMENT_PARTNER_BR_LIST");
				arg=new Object[4];
				arg[0]=branchCode;
				arg[1]=cedingId;
				arg[2]=branchCode;
				arg[3]=brokerId;
			}else{
				arg=new Object[2];
				arg[0]=branchCode;
				arg[1]=cedingId;
			}
			query+="ORDER BY NAME";
			logger.info("Select Query==> " + query);
			logger.info("Args==>" + StringUtils.join(arg,","));
			personalInfo=this.mytemplate.queryForList(query,arg);
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return personalInfo;
	}

	public void updateProposalno(RiskDetailsBean bean) {
		try{
			String query=getQuery("UPDATE_PROPOSAL_SCALE");
			Object args[] = new Object[2];
			args[0] = bean.getProposal_no();
			args[1] = bean.getReferenceNo();
			this.mytemplate.update(query,args);
			query=getQuery("UPDATE_PROPOSAL_REINS");
		
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getBouquetList(String branchCode) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			String query="";
			query=getQuery("GET_BOUQUET_LIST");
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			list=this.mytemplate.queryForList(query, new Object[]{branchCode});
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return list;
	}

	public List<Map<String, Object>> getBouquetExistingList(String branchCode, String bouquetNo,String bouquetYN) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			if(StringUtils.isNotBlank(bouquetNo) && "Y".equals(bouquetYN)) {
			String query="";
			query=getQuery("GET_EXISTING_BOUQUET");
			logger.info("Select Query==> " + query);
			logger.info("Arg[0]==> " + branchCode);
			list=this.mytemplate.queryForList(query, new Object[]{branchCode,bouquetNo});
			}
		}
		catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
		}
		return list;
	}
	public  List<Map<String, Object>> getStatusDropDown(String branchCode) {
		List<Map<String, Object>> statusList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_STATUS_DROP_DOWN");
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			statusList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y"});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}

		return statusList;
	}
	public  List<Map<String, Object>> getSubStatusDropDown(String branchCode,String statusCode) {
		List<Map<String, Object>> statusList=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_SUBSTATUS_DROP_DOWN");
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + branchCode);
			logger.info("Args[1]==> " + "Y");
			statusList=this.mytemplate.queryForList(query,new Object[]{branchCode,"Y",statusCode});
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");	
		}
		return statusList;
	}

	public void updateBqEditMode(String proposalNo, String val, String updateProposalNo) {
		try{
			String query=getQuery("POS_MAS_BQ_MODE_UPDT");
			Object args[] = new Object[2];
			if(!"N".equalsIgnoreCase(val)){
				args[0] = val +"-"+ updateProposalNo;
				}
				else{
					args[0] = val;	
				}
			args[1] = proposalNo;
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}