package com.maan.dao.impl.admin;

import java.util.ArrayList;
import java.util.Map;

import com.maan.bean.admin.CedingMasterBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.LogUtil;
import com.maan.dao.admin.CedingMasterDAO;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

public class CedingMasterDAOImpl extends MyJdbcTemplate implements CedingMasterDAO{

	Logger logger = LogUtil.getLogger(this.getClass());

	public List<Map<String,Object>> GetListCeding(CedingMasterBean bean){
		String sql=getQuery(DBConstants.CEDING_SELECT_LISTQUERY);
		Object[] args=new Object[2];
		args[0]=bean.getCustomerType();
		args[1]=bean.getBranchCode();
		logger.info("Query=>"+sql);
		logger.info("args[0]=> " + bean.getCustomerType());
		logger.info("args[1]=> " + bean.getBranchCode());
		
		List<Map<String,Object>> list = this.mytemplate.queryForList(sql,args);
		if(list!=null && list.size()>0){
			Map<String,Object> tempMap = list.get(0);
			bean.setCompanyName(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
			bean.setFirstName(tempMap.get("FIRST_NAME")==null?"":tempMap.get("FIRST_NAME").toString());
			bean.setTelephone(tempMap.get("TELEPHONE")==null?"":tempMap.get("TELEPHONE").toString());
			bean.setMobile(tempMap.get("MOBILE")==null?"":tempMap.get("MOBILE").toString());
			bean.setEmail(tempMap.get("EMAIL")==null?"":tempMap.get("EMAIL").toString());
			bean.setCustomerId(tempMap.get("CUSTOMER_ID")==null?"":tempMap.get("CUSTOMER_ID").toString());
			bean.setPanNo(tempMap.get("PAN_NUMBER")==null?"":tempMap.get("PAN_NUMBER").toString());
			bean.setIsIndividual(tempMap.get("INDIVIDUALYN")==null?"":tempMap.get("INDIVIDUALYN").toString());
			bean.setIsNonResident(tempMap.get("NON_RESIDENTYN")==null?"":tempMap.get("NON_RESIDENTYN").toString());
			bean.setSpecialRate(tempMap.get("SPECIAL_RATE")==null?"":tempMap.get("SPECIAL_RATE").toString());
		}
		/*final List<Map<String,Object>> list=this.mytemplate.query(sql,args,new RowMapper(){
			public Object mapRow(final ResultSet rset, final int arg)throws SQLException {
				CedingMasterBean bean=new CedingMasterBean();
				bean.setCompanyName(rset.getString("COMPANY_NAME"));
				bean.setFirstName(rset.getString("FIRST_NAME"));
				bean.setTelephone(rset.getString("TELEPHONE"));
				bean.setMobile(rset.getString("MOBILE"));
				bean.setEmail(rset.getString("EMAIL"));
				bean.setCustomerId(rset.getString("CUSTOMER_ID"));
				return bean;
			}
		});*/
		return list;
	}
	public boolean searchmethod(final CedingMasterBean bean,final String productId,final boolean viewFlag){
		boolean flag=false;
		try{     
			String sql=getQuery(DBConstants.CEDDINGCOMPANYDAO_SELECT_GETSEARCHQUERYTRUE);
			Object args[]=null;
			Map<String,Object> map=null;
			if(viewFlag){
			args=new Object[2];
			args[0]=bean.getCustomerId();
		    args[1]=bean.getBranchCode();
			}else{
				args=new String[1];
				args[0]="C";
			}
			if(!"-1".equalsIgnoreCase(bean.getCustomerId())){	
			if(viewFlag){
				logger.info("Select Query==>"+sql);
				logger.info("args[0]=> " + bean.getCustomerId());
				logger.info("args[1]=> " + bean.getBranchCode());
				//info=this.mytemplate.queryForMap(getQuery(DBConstants.CEDDINGCOMPANYDAO_SELECT_GETSEARCHQUERYTRUE), args);
				map=this.mytemplate.queryForMap(getQuery("EDIT_CLIENT_DETAILS_POP"), args);
			}
			if(!map.isEmpty()){
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
				List<String> email= new ArrayList<String>();
				List<String> department= new ArrayList<String>();
				List<String> fax= new ArrayList<String>();
				List<String> telephone= new ArrayList<String>();
				String contemail=map.get("EMAIL")==null?"":map.get("EMAIL").toString();
				String[] email1=contemail.split(",");
				if(email1.length>0){
				bean.setEmail(email1[0]==null?"":email1[0]);
				for(int i=1;i<email1.length;i++){
					email.add(email1[i]);
				}
				}
				bean.setEmailaddress(email);
				String contdept=map.get("CON_DEPARTMENT")==null?"":map.get("CON_DEPARTMENT").toString();
				String[] dept1=contdept.split(",");
				for(int i=0;i<dept1.length;i++){
					department.add(dept1[i]);
				
				}
				bean.setDepartmentCD(department);
				String contfax=map.get("FAX")==null?"":map.get("FAX").toString();
				String[] fax1=contfax.split(",");
				if(fax1.length>0){
				bean.setFaxNo(fax1[0]==null?"":fax1[0]);
				for(int i=1;i<fax1.length;i++){
					fax.add(fax1[i]);
				}
				}
				else{
					bean.setFaxNo("");
				}
				bean.setFaxnumber(fax);
				String conttelephone=map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString();
				String[] telph=conttelephone.split(",");
				if(fax1.length>0){
				bean.setTelephone(telph[0]==null?"":telph[0]);
				for(int i=1;i<telph.length;i++){
					telephone.add(telph[i]);
				}
				}
				else{
					bean.setTelephone("");
				}
				bean.setTelephonenumber(telephone);

				List<String> bankcurrency= new ArrayList<String>();
				List<String> bankAccno = new ArrayList<String>();
				List<String> bankname = new ArrayList<String>();
				List<String> accName = new ArrayList<String>();
				List<String> swiftCode = new ArrayList<String>();
				List<String> ifscCode= new ArrayList<String>();
				List<String> remarks= new ArrayList<String>();
				String bcurrency=map.get("BANK_CURRENCY")==null?"":map.get("BANK_CURRENCY").toString();
				String[] bankcurr=bcurrency.split(",");
				for(int i=0;i<bankcurr.length;i++){
					bankcurrency.add(bankcurr[i]);
				}
				bean.setBankCurrency(bankcurrency);
				String baccno=map.get("BANK_ACC_NO")==null?"":map.get("BANK_ACC_NO").toString();
				String[] bankaccnum=baccno.split(",");
				for(int i=0;i<bankaccnum.length;i++){
					bankAccno.add(bankaccnum[i]);
				}
				bean.setBankaccountnumber(bankAccno);
				String bname=map.get("BANK_NAME")==null?"":map.get("BANK_NAME").toString();
				String[] bkname=bname.split(",");
				for(int i=0;i<bkname.length;i++){
					bankname.add(bkname[i]);
				}
				bean.setBankname(bankname);
				String bankaccname=map.get("BANK_ACC_NAME")==null?"":map.get("BANK_ACC_NAME").toString();
				String[] bkaccname=bankaccname.split(",");
				for(int i=0;i<bkaccname.length;i++){
					accName.add(bkaccname[i]);
				}
				bean.setAccountname(accName);
				String scode=map.get("SWIFT_CODE")==null?"":map.get("SWIFT_CODE").toString();
				String[] swtcode=scode.split(",");
				for(int i=0;i<swtcode.length;i++){
					swiftCode.add(swtcode[i]);
				}
				bean.setSwiftcode(swiftCode);
				String ifcode=map.get("IFSC_CODE")==null?"":map.get("IFSC_CODE").toString();
				String[] ifscode=ifcode.split(",");
				for(int i=0;i<ifscode.length;i++){
					ifscCode.add(ifscode[i]);
				}
				bean.setIfsccode(ifscCode);
				String bremarks=map.get("BANK_REMARKS")==null?"":map.get("BANK_REMARKS").toString();
				String[] bkremarks=bremarks.split(",");
				for(int i=0;i<bkremarks.length;i++){
					remarks.add(bkremarks[i]);
				}
				bean.setBankRemarks(remarks);
				/*bean.setTitle(info.get("TITLE")==null?"":info.get("TITLE").toString());
				bean.setFirstName(info.get("FIRST_NAME")==null?"":info.get("FIRST_NAME").toString());
				bean.setTelephone(info.get("TELEPHONE")==null?"":info.get("TELEPHONE").toString());
				bean.setMobile(info.get("MOBILE")==null?"":info.get("MOBILE").toString());
				bean.setFaxNo(info.get("FAX")==null?"":info.get("FAX").toString());
				bean.setEmail(info.get("EMAIL")==null?"":info.get("EMAIL").toString());
				bean.setAddress1(info.get("ADDRESS1")==null?"":info.get("ADDRESS1").toString());
				bean.setAddress2(info.get("ADDRESS2")==null?"":info.get("ADDRESS2").toString());
				bean.setDesignation(info.get("OCCUPATION")==null?"":info.get("OCCUPATION").toString());
				bean.setZipcode(info.get("POBOX")==null?"":info.get("POBOX").toString());
				bean.setCountry(info.get("COUNTRY")==null?"":info.get("COUNTRY").toString());
				bean.setCompanyName(info.get("COMPANY_NAME")==null?"":info.get("COMPANY_NAME").toString());
				bean.setCompanyTelePhone(info.get("CED_COMPANY_PHONE")==null?"":info.get("CED_COMPANY_PHONE").toString());
				bean.setCity(info.get("CITY")==null?"":info.get("CITY").toString());
				bean.setCustomerId(info.get("CUSTOMER_ID")==null?"":info.get("CUSTOMER_ID").toString());
				bean.setCustomerType(info.get("CUSTOMER_TYPE")==null?"":info.get("CUSTOMER_TYPE").toString());
				bean.setPanNo(info.get("PAN_NUMBER")==null?"":info.get("PAN_NUMBER").toString());
				bean.setIsIndividual(info.get("INDIVIDUALYN")==null?"":info.get("INDIVIDUALYN").toString());
				bean.setIsNonResident(info.get("NON_RESIDENTYN")==null?"":info.get("NON_RESIDENTYN").toString());
				bean.setSpecialRate(info.get("SPECIAL_RATE")==null?"":info.get("SPECIAL_RATE").toString());*/
				
				flag=true;
				}
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
	 return flag;
	}
	public List<Map<String, Object>> getproposalList(CedingMasterBean bean) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			String query = getQuery("GET_PROPOSAL_CAUSE_LIST");
			list = this.mytemplate.queryForList(query,new String[]{bean.getBranchCode()});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public boolean getUserDetails(CedingMasterBean bean) {
		boolean result=false;
		try{
			String loginIdList="";
			String query=getQuery("GET_SESSION_USER_DETAILS");
			List<Map<String,Object>> list=this.mytemplate.queryForList(query, new Object[]{bean.getBranchCode(),bean.getLoginId()});
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> map = list.get(i);
					if(i==0){
						loginIdList+=map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString();
					}else{
					loginIdList+=map.get("LOGIN_ID")==null?"":","+map.get("LOGIN_ID").toString();
					}
				}
				bean.setLoginIdList(loginIdList);
				result=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void CancelProposal(CedingMasterBean bean, String proposalNo) {
		String query="";
		Object args[] = new Object[1];
		try{
			query=getQuery("CANCEL_PROPOSAL");
			args[0] = proposalNo;
			logger.info("New Proposal[]=>"+StringUtils.join(args,","));
			logger.info("Sql=>"+query);
			this.mytemplate.update(query,args);
			
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
