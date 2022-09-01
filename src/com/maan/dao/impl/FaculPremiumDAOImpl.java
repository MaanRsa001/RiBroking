package com.maan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.FaculPremiumDAO;

public class FaculPremiumDAOImpl extends MyJdbcTemplate implements FaculPremiumDAO {
	
	private static final Logger LOGGER = LogUtil.getLogger(FaculPremiumDAOImpl.class);
	
	public boolean premiumInsertMethod(final FaculPremiumBean beanObj){
		LOGGER.info("PremiumDAOImpl premiumInsertMethod || Enter");
		boolean saveFlag = false;
		try {
				String query="";
				int result;
				String[] args = insertArguments(beanObj);
			 	String netDueOc="0";
			 	String transNo="";
			 	query=getQuery(DBConstants.PREMIUM_INSERT_FACPREMIUM);
			 	netDueOc=args[17];
			 	//transNo=args[2];			 	
			 	LOGGER.info("Insert Query==>"+query);
			 	result=this.mytemplate.update(query, args);
			 	LOGGER.info("Insert Result==>"+result);	
			 	if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
			 		beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
					query = getQuery("FAC_TEMP_STATUS_UPDATE");
					args = new String[5];
			 		args[0] = "A";
			 		args[1] = beanObj.getLoginId();
			 		args[2] =beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
			 		args[3]= beanObj.getRequestNo() ;
			 		args[4]= beanObj.getBranchCode() ;
			 		this.mytemplate.update(query,args);
			 		getTempToMainMove(beanObj,netDueOc);
			 	if (result==1) {
					if(!("EP".equalsIgnoreCase(beanObj.getInstlmentNo()) || "RTP".equalsIgnoreCase(beanObj.getInstlmentNo()) || "RVP".equalsIgnoreCase(beanObj.getInstlmentNo()))){
					query=getQuery("UPDATE_MND_INSTALLMENT");
					args = new String[4];
					args[0]=beanObj.getTransactionNo();
					args[1]=beanObj.getContNo();
					args[2]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
					args[3]=beanObj.getInstlmentNo();
					LOGGER.info("Insert Query==>"+query);
					LOGGER.info("Args==>"+StringUtils.join(args, ","));
					this.mytemplate.update(query,args);
					}
				}
				//insertBonus(beanObj);
				if("transEdit".equalsIgnoreCase(beanObj.getMode())){
					LOGGER.info("Reversel Transaction Enter Transaction No" +beanObj.getTransactionNo()+"Reverser Transaction No"+beanObj.getTransDropDownVal()+"Proposal No"+beanObj.getProposal_No());
					query=getQuery("UPDATE_REV_TRANSACTION_NO");
					String  query1=getQuery("UPDATE_REV_TRANSACTION_NO_TEMP");
					args=new String[2];
					args[0] = beanObj.getTransactionNo();
					args[1] = beanObj.getTransDropDownVal();
					this.mytemplate.update(query,args);
					this.mytemplate.update(query1,args);
					args[1] = beanObj.getTransactionNo();
					args[0] = beanObj.getTransDropDownVal();
					this.mytemplate.update(query,args);
					this.mytemplate.update(query1,args);
					query=getQuery("UPDATE_MND_INSTAL");
					args=new String[3];
					args[0] = "";
					args[1] = beanObj.getProposal_No();
					args[2] = beanObj.getTransDropDownVal();
					this.mytemplate.update(query,args);
				}
		}
		}
		catch (Exception exe) {
			LOGGER.debug(""+exe);			
		}
		LOGGER.info("PremiumDAOImpl premiumInsertMethod || Enter");
		return saveFlag;
	}

@SuppressWarnings("unchecked")
public boolean contractDetails(final FaculPremiumBean bean,final String countryId){
	LOGGER.info("PremiumDAOImpl contractDetails || Enter");
	 String query="";
	 String[] args=null;
	 boolean saveFlag=false;
	 try {
			query=getQuery(DBConstants.PREMIUM_SELECT_FACCONTDET);
			args =new String[7];
			args[0] = bean.getContNo();
			args[1] = bean.getBranchCode();
			args[2] = bean.getBranchCode();
			args[3] = bean.getProductId();
			args[4] = bean.getBranchCode();
			args[5] = bean.getBranchCode();
			args[6] = bean.getContNo();			
			LOGGER.info("Select Query=>"+query);
			for(int i=0;i<args.length;i++)
			LOGGER.info("Args["+i+"]=>"+args[i]);
			List list = this.mytemplate.query(query, args,new RowMapper() {				
					public Object mapRow(ResultSet contDet, int rowNum) throws SQLException {
					bean.setContNo(contDet.getString("RSK_CONTRACT_NO"));
					bean.setAmendId(contDet.getString("RSK_ENDORSEMENT_NO"));
					bean.setProfit_Center(contDet.getString("TMAS_PFC_NAME"));
					bean.setSubProfit_center(contDet.getString("RSK_SPFCID"));
					if(!"ALL".equalsIgnoreCase(bean.getSubProfit_center())){
					bean.setSubProfit_center(contDet.getString("TMAS_SPFC_NAME"));
					}
					bean.setCedingCo(contDet.getString("COMPANY"));
					bean.setBroker(contDet.getString("BROKER"));
					bean.setTreatyName_type(contDet.getString("RSK_TREATYID"));					
					bean.setProposal_No(contDet.getString("RSK_PROPOSAL_NUMBER"));
					bean.setUwYear(contDet.getString("RSK_UWYEAR"));
					bean.setLayerno(contDet.getString("RSK_LAYER_NO"));
					bean.setInsDate(contDet.getString("INS_DATE"));
					bean.setExpDate(contDet.getString("EXP_DATE"));
					bean.setMonth(contDet.getString("MONTH"));
					bean.setBaseCurrencyId(contDet.getString("RSK_ORIGINAL_CURR"));
					bean.setBaseCurrencyName(contDet.getString("SHORT_NAME"));
					bean.setInsured_name(contDet.getString("RSK_INSURED_NAME"));
					bean.setAddress(contDet.getString("Address"));
					bean.setTdsRate(contDet.getString("Special_Rate"));
					bean.setDepartmentId(contDet.getString("RSK_DEPTID"));
					bean.setPredepartment(contDet.getString("RSK_DEPTID"));
					bean.setConsubProfitId(contDet.getString("RSK_SPFCID"));
					bean.setSubProfitId("ALL");
					bean.setAcceptenceDate(contDet.getString("RSK_ACCOUNT_DATE"));
					return bean;
				}
			});
			if(list!=null && list.size()>0)
				saveFlag = true;
				args=new String[2];
				args[0] = bean.getProposal_No();
				args[1] = bean.getProposal_No();
				query=getQuery(DBConstants.PREMIUM_SELECT_COMMISSIONDETAILS);
				LOGGER.info("Select Query=>"+query);
				this.mytemplate.query(query, args,new RowMapper() {
					public Object mapRow(ResultSet commission, int rowNum) throws SQLException {
						bean.setCommission_view(commission.getString("RSK_COMM"));
						bean.setCommissionSurb_view(commission.getString("RSK_COMM_SURPLUS"));
						bean.setOverRider_view(commission.getString("RSK_OVERRIDER_PERC"));
						bean.setBrokerage_view(commission.getString("RSK_BROKERAGE"));
						bean.setTax_view(commission.getString("RSK_TAX"));
						bean.setOtherCostView(commission.getString("RSK_OTHER_COST"));
						return null;
					}
				});
				args[0] = bean.getProposal_No();
				args[1] = bean.getProposal_No();
				query=getQuery(DBConstants.PREMIUM_SELECT_FACPROPOSALDETAILS);				
				this.mytemplate.query(query, args,new RowMapper() {
					public Object mapRow(ResultSet proposalDetails, int rowNum) throws SQLException {
						bean.setShareSigned(proposalDetails.getString("SHARE_SIGNED"));
						//bean.setGwpiOS(proposalDetails.getString("GWPI_OUR_SHARE_OC"));
						bean.setGwpiOS(proposalDetails.getString("GWPI_OC"));
					    bean.setRdsExchageRate(proposalDetails.getString("RSK_EXCHANGE_RATE"));
						return null;
					}
				});				
					query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
					LOGGER.info("Select Query==>"+query);
					LOGGER.info("Arg[0]====>"+bean.getContNo());
					bean.setSum_of_paid_premium((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
					
					LOGGER.info("Result===>"+bean.getSum_of_paid_premium());					
					double sumofPaidPre=Double.parseDouble(bean.getSum_of_paid_premium());
					double sumpaidpremium =(sumofPaidPre/Double.parseDouble(bean.getRdsExchageRate()));
					bean.setSum_of_paid_premium(Double.toString(sumpaidpremium));
					sumofPaidPre=Double.parseDouble(bean.getSum_of_paid_premium());
					double gwpiOSFor=Double.parseDouble(bean.getGwpiOS());
					double preBooked=gwpiOSFor-sumofPaidPre;
					bean.setEpibalance(String.valueOf(preBooked));
					query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
				   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));
					
	} catch (Exception exe) {
		LOGGER.debug("contractDetails"+exe);		
	}
	LOGGER.info("PremiumDAOImpl contractDetails || Exit");
	return saveFlag;
}


@SuppressWarnings("unchecked")
public boolean getPremiumDetails(final FaculPremiumBean bean,final String TransactionNo,final String countryId)  {
	LOGGER.info("PremiumDAOImpl getPremiumDetails || Enter");
	String query="";
	try{
	   	
			String[] args=new String[4];
			args[0] = bean.getProductId();
			args[1] = bean.getProductId();
		   	args[2]=bean.getContNo();
		   	if("Temp".equalsIgnoreCase(bean.getTableType()) || "".equalsIgnoreCase(TransactionNo)){
		   		args[3] = bean.getRequestNo();
		   		query=getQuery("PREMIUM_SELECT_FACPREMIVIEW_TEMP");
		   	}else{
		   		args[3]=TransactionNo;
		   		query=getQuery(DBConstants.PREMIUM_SELECT_FACPREMIVIEW);
		   	}
	   		LOGGER.info("Query=>"+query);
		   	this.mytemplate.query(query, args,new RowMapper() {
				public Object mapRow(ResultSet facView, int rowNum) throws SQLException {
					
	   			bean.setTransaction(facView.getString("TRANS_DATE"));			
	   			bean.setAccount_Period(facView.getString("INSTALMENT_NUMBER")+(StringUtils.isBlank(facView.getString("ACCOUNT_PERIOD_QTR"))?"":("_"+facView.getString("ACCOUNT_PERIOD_QTR"))));
				bean.setCurrencyId(facView.getString("CURRENCY_ID"));
				bean.setExchRate(DropDownControllor.exchRateFormat(facView.getString("EXCHANGE_RATE")));
				bean.setPremiumQuotaShare(DropDownControllor.formatter(facView.getString("PREMIUM_QUOTASHARE_OC")));
				bean.setCommission_view(facView.getString("COMMISSION"));
				bean.setCommissionQuotaShare(DropDownControllor.formatter(facView.getString("COMMISSION_QUOTASHARE_OC")));
				bean.setBrokerage_view(facView.getString("BROKERAGE"));
				bean.setBrokerage(DropDownControllor.formatter(facView.getString("BROKERAGE_AMT_OC")));
				bean.setTax_view(facView.getString("TAX"));
				bean.setTax(DropDownControllor.formatter(facView.getString("TAX_AMT_OC")));
     			bean.setStatus(facView.getString("STATUS"));
				bean.setReceipt_no(facView.getString("RECEIPT_NO"));
				bean.setInception_Date(facView.getString("ENTRY_DATE"));
				bean.setNetDue(DropDownControllor.formatter(facView.getString("NETDUE_OC")));
				bean.setTransactionNo(facView.getString("TRANSACTION_NO"));
				bean.setOtherCost(DropDownControllor.formatter(facView.getString("OTHER_COST_OC")));
				bean.setPremiumQuotaShare_usd(DropDownControllor.formatter(facView.getString("PREMIUM_QUOTASHARE_DC")));
				bean.setCommsissionQuotaShare_usd(DropDownControllor.formatter(facView.getString("COMMISSION_QUOTASHARE_DC")));
				bean.setBrokerage_usd(DropDownControllor.formatter(facView.getString("BROKERAGE_AMT_DC")));
				bean.setTax_usd(DropDownControllor.formatter(facView.getString("TAX_AMT_DC")));
				bean.setNet_due_usd(DropDownControllor.formatter(facView.getString("NETDUE_DC")));
				bean.setOtherCostUSD(DropDownControllor.formatter(facView.getString("OTHER_COST_DC")));
				bean.setCedentRef(facView.getString("CEDANT_REFERENCE"));
				bean.setRemarks(facView.getString("REMARKS"));
				bean.setTotalCredit(DropDownControllor.formatter(facView.getString("TOTAL_CR_OC")));
				bean.setTotalCreditDC(DropDownControllor.formatter(facView.getString("TOTAL_CR_DC")));
				bean.setTotalDebit(DropDownControllor.formatter(facView.getString("TOTAL_DR_OC")));
				bean.setTotalDebitDC(DropDownControllor.formatter(facView.getString("TOTAL_DR_DC")));
				bean.setSettlement_status(facView.getString("SETTLEMENT_STATUS"));
				bean.setAmendmentDate(facView.getString("AMENDMENT_DATE"));
				bean.setWithHoldingTaxOC(DropDownControllor.formatter(facView.getString("WITH_HOLDING_TAX_OC")));
                bean.setWithHoldingTaxDC(DropDownControllor.formatter(facView.getString("WITH_HOLDING_TAX_DC")));
                bean.setDueDate(facView.getString("due_date"));
                bean.setCreditsign(facView.getString("NETDUE_OC"));
                bean.setRi_cession(facView.getString("RI_CESSION"));
                bean.setTaxDedectSource(DropDownControllor.formatter(facView.getString("TDS_OC")));
                bean.setTaxDedectSourceDc(DropDownControllor.formatter(facView.getString("TDS_DC")));
                bean.setServiceTax(DropDownControllor.formatter(facView.getString("ST_OC")));
                bean.setServiceTaxDc(DropDownControllor.formatter(facView.getString("ST_DC")));
                bean.setBonus(DropDownControllor.formatter(facView.getString("BONUS_OC")));
                bean.setBonusDc(DropDownControllor.formatter(facView.getString("BONUS_DC")));
                bean.setPremiumClass(facView.getString("TMAS_DEPARTMENT_NAME"));
                bean.setPremiumSubClass(facView.getString("PREMIUM_SUBCLASS"));
                bean.setChooseTransaction(facView.getString("REVERSEL_STATUS"));
                bean.setTransDropDownVal(facView.getString("REVERSE_TRANSACTION_NO"));
                if(!"ALL".equalsIgnoreCase(bean.getPremiumSubClass())){
                	bean.setPremiumSubClass(facView.getString("PREMIUM_SUBCLASS_VAL"));
                }
                bean.setStatementDate(facView.getString("STATEMENT_DATE"));
				return null;
				}
			});
		 	query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
			LOGGER.info("Select Query==>"+query);
			LOGGER.info("Arg[0]====>"+bean.getContNo());
			bean.setSum_of_paid_premium((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
			double sumofPaidPre=Double.parseDouble(bean.getSum_of_paid_premium());
			double sumpaidpremium =(sumofPaidPre/Double.parseDouble(bean.getRdsExchageRate()));
			bean.setSum_of_paid_premium(Double.toString(sumpaidpremium));
			sumofPaidPre=Double.parseDouble(bean.getSum_of_paid_premium());
			double gwpiOSFor=Double.parseDouble(bean.getGwpiOS());
			double preBooked=gwpiOSFor-sumofPaidPre;
			bean.setEpibalance(String.valueOf(preBooked));
			LOGGER.info("Result===>"+bean.getSum_of_paid_premium());
	   
	   	if(StringUtils.isNotBlank(bean.getCurrencyId())){
			query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY);
			LOGGER.info("Select Query==>"+query);
			LOGGER.info("Arg[0]====>"+bean.getCurrencyId());
			LOGGER.info("Arg[1]====>"+bean.getBranchCode());
			bean.setCurrency((String)this.mytemplate.queryForObject(query,new Object[]{bean.getCurrencyId(),bean.getBranchCode()},String.class));
			LOGGER.info("Result==>"+bean.getCurrency());
	   	}
	   	query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
	   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));
	   	query=getQuery("GETSETTLEMET_STATUS");
		List<Map<String,Object>> premlist = new ArrayList<Map<String,Object>>();
		premlist = this.mytemplate.queryForList(query,new Object[]{bean.getContNo()});
		if(premlist.size()>0){
			for(int i=0;i<premlist.size();i++){
				Map<String,Object> map = premlist.get(i);
					String allocate = map.get("ALLOCATED_TILL_DATE")==null?"0":map.get("ALLOCATED_TILL_DATE").toString();
					String net = map.get("NETDUE_OC").toString();
					if("0".equalsIgnoreCase(allocate)){
						bean.setSettlement_status("Pending");
					}else if(Double.parseDouble(allocate) == Double.parseDouble(net)){
						bean.setSettlement_status("Allocated");
					}else{
						bean.setSettlement_status("Partially Allocated");
					}
			}
		}
		LOGGER.info("PremiumDAOImpl getPremiumDetails || Exit");
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);	
		e.printStackTrace();
	}
	return false;
}

public List<FaculPremiumBean> getPremiumedList(final FaculPremiumBean beanObj)
{
	List<FaculPremiumBean> finalList = new ArrayList<FaculPremiumBean>();
	LOGGER.info("PremiumDAOImpl getPremiumedList || Enter");
	String query="";
    Object[] args=null;
      	args=new String[3];
    	args[0]=beanObj.getContNo();
    	args[1]=beanObj.getBranchCode();
    	args[2]=beanObj.getContNo();
    	
	query=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST1);
	
	query+=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST2);	

	query+=getQuery(DBConstants.PREMIUM_SELECT_PREMIUMEDLIST3);
	LOGGER.info("Query=>"+query);
	LOGGER.info("Args=>"+StringUtils.join(args,","));
	List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);
	for(int i=0 ; i<list.size() ; i++) {
		Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
		FaculPremiumBean tempBean = new FaculPremiumBean();
		tempBean.setProposal_No(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
		tempBean.setContNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
		tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
		tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
		tempBean.setLayerno(tempMap.get("RSK_LAYER_NO")==null?"":tempMap.get("RSK_LAYER_NO").toString());
		tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
		tempBean.setAccount_Period(tempMap.get("INS_DETAIL")==null?"":tempMap.get("INS_DETAIL").toString());
		tempBean.setTransDropDownVal(tempMap.get("REVERSE_TRANSACTION_NO")==null?"":tempMap.get("REVERSE_TRANSACTION_NO").toString());
		if(i==0)
			tempBean.setEndtYN("No");
		else
			tempBean.setEndtYN("Yes");
		if(Double.parseDouble(tempMap.get("ALLOC_AMT").toString())!=0)
			tempBean.setEndtYN("Yes");	
		tempBean.setInception_Date(tempMap.get("INS_DATE")==null?"":tempMap.get("INS_DATE").toString());
		tempBean.setStatementDate(tempMap.get("STATEMENT_DATE")==null?"":tempMap.get("STATEMENT_DATE").toString());
		tempBean.setMovementYN(tempMap.get("MOVEMENT_YN")==null?"":tempMap.get("MOVEMENT_YN").toString());
		//tempBean.setSettlement_Status(tempMap.get("SETTLEMENT_STATUS")==null?"":tempMap.get("SETTLEMENT_STATUS").toString());
		tempBean.setTransDate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
		if((StringUtils.isNotBlank(beanObj.getOpstartDate()))&& (StringUtils.isNotBlank(beanObj.getOpendDate()))){
			if(new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate())==0){
				tempBean.setTransOpenperiodStatus("N");
			}else
			{
				tempBean.setTransOpenperiodStatus("Y");
			}
			}
		tempBean.setProductId("1");
		tempBean.setAllocatedYN((String)this.mytemplate.queryForObject("select Decode (count(*),0,'Y','N') allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=? and TYPE='P' and STATUS='Y'",new Object[]{tempBean.getContNo(),tempBean.getTransactionNo(),tempBean.getLayerno()},String.class));
		int count=new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate());
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
		
		if(count!=0 && allocationstatus ==0 && retroPrclStatus1 ==0 ){
			tempBean.setDeleteStatus("Y");
		}
		finalList.add(tempBean);
		
	}
	LOGGER.info("PremiumDAOImpl getPremiumedList || Exit List Size==>"+list.size());
	return finalList;
}

@SuppressWarnings("unchecked")
public boolean premiumEdit(final FaculPremiumBean bean,final String countryId) {		
	 LOGGER.info("PremiumDAOImpl premiumEdit || Enter");
	 String query="";
	 boolean saveFlag=false;
	 List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	 String[] args = new String[2];
	if( "transEdit".equalsIgnoreCase(bean.getMode())){
		
		args[0] = bean.getContNo();
		args[1] = bean.getTransDropDownVal();
		query=getQuery(DBConstants.PREMIUM_SELECT_FACPREMIUMEDIT);			
		LOGGER.info("Query=>"+query.toString());
		 list=this.mytemplate.queryForList(query, args);
		 if(list.size()>0)
		 {
			 for(int i=0;i<list.size();i++){
				 Map<String,Object>	 editPremium=list.get(i);
												
				//bean.setTransaction(editPremium.get("TRANS_DATE")==null?"":editPremium.get("TRANS_DATE").toString()); 
					bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
					bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
					if((editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString())==""){
						bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
					}
					else{
					bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString()));
					}
					bean.setPremiumQuotaShare((editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUM_QUOTASHARE_OC").toString()));
					bean.setPremiumQuotaShare(getMultipleVal(bean.getPremiumQuotaShare()));
					bean.setCommission_view((editPremium.get("COMMISSION") ==null?"":editPremium.get("COMMISSION").toString()));
					//bean.setCommission_view(DropDownControllor.formatterpercentage(getMultipleVal(bean.getCommission_view())));
					bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":editPremium.get("COMMISSION_QUOTASHARE_OC").toString());
					bean.setCommissionQuotaShare((getMultipleVal(bean.getCommissionQuotaShare())));
					bean.setBrokerage_view(editPremium.get("BROKERAGE")==null?"":editPremium.get("BROKERAGE").toString());
					//bean.setBrokerage_view((getMultipleVal(bean.getBrokerage_view())));
					bean.setBrokerage(editPremium.get("BROKERAGE_AMT_OC")==null?"":editPremium.get("BROKERAGE_AMT_OC").toString());
					bean.setBrokerage((getMultipleVal(bean.getBrokerage())));
					bean.setTax_view(editPremium.get("TAX")==null?"":editPremium.get("TAX").toString());
					//bean.setTax_view((getMultipleVal(bean.getTax_view())));
					bean.setTax(editPremium.get("TAX_AMT_OC")==null?"":editPremium.get("TAX_AMT_OC").toString());
					bean.setTax((getMultipleVal(bean.getTax())));
	     			bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
					bean.setReceipt_no(editPremium.get("RECEIPT_NO")==null?"":editPremium.get("RECEIPT_NO").toString());		
					//bean.setInception_Date(editPremium.get("ENTRY_DATE")==null?"":editPremium.get("ENTRY_DATE").toString());
					bean.setEnteringMode(editPremium.get("ENTERING_MODE")==null?"":editPremium.get("ENTERING_MODE").toString().trim());
					bean.setOtherCost(editPremium.get("OTHER_COST_OC")==null?"":editPremium.get("OTHER_COST_OC").toString());
					bean.setOtherCost((getMultipleVal(bean.getOtherCost())));
					bean.setCedentRef(editPremium.get("CEDANT_REFERENCE")==null?"":editPremium.get("CEDANT_REFERENCE").toString());
					bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
					bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
					bean.setNetDue((getMultipleVal(bean.getNetDue())));
					bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_OC")==null?"0":editPremium.get("WITH_HOLDING_TAX_OC").toString()));
					bean.setWithHoldingTaxOC((getMultipleVal(bean.getWithHoldingTaxOC())));
					bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.get("TDS_OC")==null?"0":editPremium.get("TDS_OC").toString()));
					bean.setTaxDedectSource((getMultipleVal(bean.getTaxDedectSource())));
					bean.setServiceTax((editPremium.get("ST_OC")==null?"0":editPremium.get("ST_OC").toString()));
					bean.setServiceTax(getMultipleVal(bean.getServiceTax()));
					bean.setBonus((editPremium.get("BONUS_OC")==null?"0":editPremium.get("BONUS_OC").toString()));
					bean.setBonus(getMultipleVal(bean.getBonus()));
		            //bean.setSettlement_status(editPremium.getString("SETTLEMENT_STATUS"));								
					bean.setTotalCredit(editPremium.get("TOTAL_CR_OC")==null?"":editPremium.get("TOTAL_CR_OC").toString());						
					bean.setTotalCredit((getMultipleVal(bean.getTotalCredit())));
					bean.setTotalDebit(editPremium.get("TOTAL_DR_OC")==null?"":editPremium.get("TOTAL_DR_OC").toString());
					bean.setTotalDebit((getMultipleVal(bean.getTotalDebit())));
					
					//bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());
					//bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":editPremium.get("RI_CESSION").toString());
					//bean.setStatementDate(editPremium.get("STATEMENT_DATE")==null?"":editPremium.get("STATEMENT_DATE").toString());
			}
		 }
		}else{
			args[0] = bean.getContNo();
			
			if("Temp".equalsIgnoreCase(bean.getTableType())){
				query=getQuery("PREMIUM_SELECT_FACPREMIUMEDIT_TEMP");
				args[1] = bean.getRequestNo();
			}else{
				query=getQuery(DBConstants.PREMIUM_SELECT_FACPREMIUMEDIT);	
				args[1] = bean.getTransactionNo();
			}
			LOGGER.info("Query=>"+query.toString());
			 list=this.mytemplate.queryForList(query, args);
			 if(list.size()>0)
			 {
				 for(int i=0;i<list.size();i++){
					 Map<String,Object>	 editPremium=list.get(i);
													
					bean.setTransaction(editPremium.get("TRANS_DATE")==null?"":editPremium.get("TRANS_DATE").toString()); 
					if("EP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else if("RTP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else if("RVP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else
			    	{
			    		bean.setAccount_Period((editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString())+"_"+(editPremium.get("ACCOUNT_PERIOD_QTR")==null?"":editPremium.get("ACCOUNT_PERIOD_QTR").toString()));
			    	}
						bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						if((editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString())==""){
							bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
						}
						else{
						bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString()));
						}
						bean.setPremiumQuotaShare(DropDownControllor.formatter(editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUM_QUOTASHARE_OC").toString()));
						bean.setCommission_view(DropDownControllor.formatterpercentage(editPremium.get("COMMISSION") ==null?"":editPremium.get("COMMISSION").toString()));
						bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":editPremium.get("COMMISSION_QUOTASHARE_OC").toString());
						bean.setBrokerage_view(editPremium.get("BROKERAGE")==null?"":editPremium.get("BROKERAGE").toString());
						bean.setBrokerage(editPremium.get("BROKERAGE_AMT_OC")==null?"":editPremium.get("BROKERAGE_AMT_OC").toString());
						bean.setTax_view(editPremium.get("TAX")==null?"":editPremium.get("TAX").toString());
						bean.setTax(editPremium.get("TAX_AMT_OC")==null?"":editPremium.get("TAX_AMT_OC").toString());
		     			bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
						bean.setReceipt_no(editPremium.get("RECEIPT_NO")==null?"":editPremium.get("RECEIPT_NO").toString());		
						bean.setInception_Date(editPremium.get("ENTRY_DATE")==null?"":editPremium.get("ENTRY_DATE").toString());
						bean.setEnteringMode(editPremium.get("ENTERING_MODE")==null?"":editPremium.get("ENTERING_MODE").toString().trim());
						bean.setOtherCost(editPremium.get("OTHER_COST_OC")==null?"":editPremium.get("OTHER_COST_OC").toString());
						bean.setCedentRef(editPremium.get("CEDANT_REFERENCE")==null?"":editPremium.get("CEDANT_REFERENCE").toString());
						bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
						bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
						bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_OC")==null?"0":editPremium.get("WITH_HOLDING_TAX_OC").toString()));
						bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.get("TDS_OC")==null?"0":editPremium.get("TDS_OC").toString()));
			            bean.setServiceTax(DropDownControllor.formatter(editPremium.get("ST_OC")==null?"0":editPremium.get("ST_OC").toString()));
			            bean.setBonus(DropDownControllor.formatter(editPremium.get("BONUS_OC")==null?"0":editPremium.get("BONUS_OC").toString()));
						//bean.setSettlement_status(editPremium.getString("SETTLEMENT_STATUS"));								
						bean.setTotalCredit(editPremium.get("TOTAL_CR_OC")==null?"":editPremium.get("TOTAL_CR_OC").toString());						
						bean.setTotalDebit(editPremium.get("TOTAL_DR_OC")==null?"":editPremium.get("TOTAL_DR_OC").toString());
						bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());
						bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":editPremium.get("RI_CESSION").toString());
						bean.setStatementDate(editPremium.get("STATEMENT_DATE")==null?"":editPremium.get("STATEMENT_DATE").toString());
						bean.setChooseTransaction(editPremium.get("REVERSEL_STATUS")==null?"":editPremium.get("REVERSEL_STATUS").toString() );
			            bean.setTransDropDownVal(editPremium.get("REVERSE_TRANSACTION_NO")==null?"":editPremium.get("REVERSE_TRANSACTION_NO").toString() );
				}
			 }
			/*args[0] = bean.getContNo();
			args[1] = bean.getTransactionNo();
			query=getQuery(DBConstants.PREMIUM_SELECT_FACPREMIUMEDIT);			
			LOGGER.info("Query=>"+query.toString());
			 list=this.mytemplate.query(query, args,new RowMapper() {
				public Object mapRow(ResultSet editPremium, int rowNum) throws SQLException {											
					bean.setTransaction(editPremium.getString("TRANS_DATE")); 
					
						bean.setCurrencyId(editPremium.getString("CURRENCY_ID"));
						bean.setCurrency(editPremium.getString("CURRENCY_ID"));
						if(null==editPremium.getString("EXCHANGE_RATE")){
							bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
						}
						else{
						bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.getString("EXCHANGE_RATE")));
						}
						bean.setPremiumQuotaShare(DropDownControllor.formatter(editPremium.getString("PREMIUM_QUOTASHARE_OC")));
						bean.setCommission_view(DropDownControllor.formatterpercentage(editPremium.getString("COMMISSION")));
						bean.setCommissionQuotaShare(editPremium.getString("COMMISSION_QUOTASHARE_OC"));
						bean.setBrokerage_view(editPremium.getString("BROKERAGE"));
						bean.setBrokerage(editPremium.getString("BROKERAGE_AMT_OC"));
						bean.setTax_view(editPremium.getString("TAX"));
						bean.setTax(editPremium.getString("TAX_AMT_OC"));
		     			bean.setStatus(editPremium.getString("STATUS"));
						bean.setReceipt_no(editPremium.getString("RECEIPT_NO"));		
						bean.setInception_Date(editPremium.getString("ENTRY_DATE"));
						bean.setEnteringMode(editPremium.getString("ENTERING_MODE").trim());
						bean.setOtherCost(editPremium.getString("OTHER_COST_OC"));
						bean.setCedentRef(editPremium.getString("CEDANT_REFERENCE"));
						bean.setRemarks(editPremium.getString("REMARKS"));
						bean.setNetDue(editPremium.getString("NETDUE_OC"));
						bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.getString("WITH_HOLDING_TAX_OC")));
						bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.getString("TDS_OC")));
			            bean.setServiceTax(DropDownControllor.formatter(editPremium.getString("ST_OC")));
			            bean.setBonus(DropDownControllor.formatter(editPremium.getString("BONUS_OC")));
						//bean.setSettlement_status(editPremium.getString("SETTLEMENT_STATUS"));								
						bean.setTotalCredit(editPremium.getString("TOTAL_CR_OC"));						
						bean.setTotalDebit(editPremium.getString("TOTAL_DR_OC"));
						bean.setAmendmentDate(editPremium.getString("AMENDMENT_DATE"));
						bean.setRi_cession(editPremium.getString("RI_CESSION"));
						bean.setStatementDate(editPremium.getString("STATEMENT_DATE"));
					return bean;
				}
			});*/
	}
			if(list!=null && list.size()>0)
				saveFlag = true;
			if("1".equalsIgnoreCase(bean.getProductId()))
			{
			   	query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
			   	if( "transEdit".equalsIgnoreCase(bean.getMode())){
			   		query +=" and TRANSACTION_NO !="+bean.getTransDropDownVal();
			   	}
				LOGGER.info("Select Query==>"+query);
				LOGGER.info("Arg[0]====>"+bean.getContNo());
				double premium=Double.parseDouble(StringUtils.isBlank(bean.getPremiumQuotaShare())?"0":bean.getPremiumQuotaShare().replaceAll(",", ""));
				double sum=Double.parseDouble((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
			/*	if(premium<0)
					premium=premium*(-1);*/
				sum =sum-premium;
				double sumpaidpremium =(sum/Double.parseDouble(bean.getRdsExchageRate()));
				bean.setSum_of_paid_premium(Double.toString(sumpaidpremium));
				sum=Double.parseDouble(bean.getSum_of_paid_premium());
				double gwpiOSFor=Double.parseDouble(bean.getGwpiOS());
				double preBooked=gwpiOSFor-sum;
				bean.setEpibalance(String.valueOf(preBooked));
				
			LOGGER.info("PremiumDAOImpl premiumEdit || Exit");	
	}
		return saveFlag;
		}

private String getMultipleVal(String premiumQuotaShare) {
	String res="";double val =0;
	try{
		if(premiumQuotaShare==""){
			premiumQuotaShare="0";
		}
		//if(Double.parseDouble(premiumQuotaShare.replaceAll(",", ""))>0){
		 val = Double.parseDouble(premiumQuotaShare.replaceAll(",", ""))*-1;
		//}
		 if(val==-0){
			 val = 0; 
		 }
		res = DropDownControllor.formatter(Double.toString(val));
	}catch(Exception e){
		e.printStackTrace();
	}
	return res;
}

@SuppressWarnings("unchecked")
public boolean getPreList(final FaculPremiumBean bean) {
	LOGGER.info("PremiumDAOImpl getPreList || Enter");
	String query="";
	boolean saveFlag=false;
	String[] args = null;
	args = new String[2];
	args[0] = bean.getContNo();
	args[1]=bean.getDepartmentId();
	//args[1] = bean.getLayerno();
	query=getQuery(DBConstants.PREMIUM_SELECT_FACTREATYPRELIST);	
	LOGGER.info("Select Query=>"+query);
	LOGGER.info("Args=>"+StringUtils.join(args,","));
	List list=this.mytemplate.query(query, args,new RowMapper() {
		public Object mapRow(ResultSet preList, int rowNum) throws SQLException {
			bean.setContNo(preList.getString("CONTRACT_NO"));
			bean.setDepartment_Name(preList.getString("TMAS_DEPARTMENT_NAME"));
			bean.setUwYear(preList.getString("UW_YEAR"));
			bean.setCeding_Company_Name(preList.getString("COMPANY_NAME"));
			bean.setLayerno(preList.getString("LAYER_NO"));
			bean.setBrokername(preList.getString("Broker_name"));
			return bean;
		}
	});
	if(list!=null && list.size()>0)
		saveFlag = true;
	LOGGER.info("PremiumDAOImpl getPreList || Exit");
	if(StringUtils.isNotBlank(bean.getContNo())){
		bean.setCeaseStatus((String)this.mytemplate.queryForObject("select CEASE_STATUS from position_master pm where CONTRACT_NO=?  and  pm.amend_id=(Select Max(Amend_id) From Position_master p where p.CONTRACT_NO=pm.CONTRACT_NO )", new Object[]{bean.getContNo()}, String.class));
	}
	return saveFlag;
}

public boolean premiumUpdateMethod(final FaculPremiumBean beanObj){	LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Enter");
	String query="";
	boolean saveFlag = false;
	try {
		String[] args = updateAruguments(beanObj);
		String netDueOc="0";
		if("Temp".equalsIgnoreCase(beanObj.getTableType())){
			query=getQuery("PREMIUM_UPDATE_FACUPATEPRE_TEMP");
		}else{
			query=getQuery(DBConstants.PREMIUM_UPDATE_FACUPATEPRE);
		}
		netDueOc=args[14];	
		LOGGER.info("Update Query=>"+query);
		LOGGER.info("Update Arg=>"+ StringUtils.join(args,","));
		int update=this.mytemplate.update(query,args);
		if("Submit".equalsIgnoreCase(beanObj.getButtonStatus()) && "Temp".equalsIgnoreCase(beanObj.getTableType())){
			beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
			query = getQuery("FAC_TEMP_STATUS_UPDATE");
			args = new String[5];
	 		args[0] = "A";
	 		args[1] = beanObj.getLoginId();
	 		args[2] =beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
	 		args[3]= beanObj.getRequestNo() ;
	 		args[4]= beanObj.getBranchCode() ;
	 		this.mytemplate.update(query,args);
			getTempToMainMove(beanObj,netDueOc);
			LOGGER.info("SP Name=>"+getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE));
			LOGGER.info("Args[]==>"+beanObj.getContNo()+","+(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno())+","+beanObj.getTransactionNo()+","+beanObj.getCurrencyId()+","+beanObj.getExchRate()+","+netDueOc+","+beanObj.getDepartmentId()+","+beanObj.getProductId());
			int spresult=this.mytemplate.update(getQuery(DBConstants.PREMIUM_DETAIL_ARCHIVE),new String[]{beanObj.getContNo(),(StringUtils.isBlank(beanObj.getLayerno())?"0":beanObj.getLayerno()),beanObj.getTransactionNo(),beanObj.getCurrencyId(),beanObj.getExchRate(),netDueOc,beanObj.getDepartmentId(),beanObj.getProductId()});
			LOGGER.info("SP Result==>"+spresult);
		}
		/*e}
		else{
			 query=getQuery("PRCL_DELETE");
			Object arg[]=new Object[3];
			arg[0]=beanObj.getContNo();
			arg[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
			arg[2]=beanObj.getProductId();
			this.mytemplate.update(query,arg);
		}*/
		saveFlag=true;
		//insertBonus(beanObj);
	} catch (Exception exe) {
		saveFlag=false;
		LOGGER.debug("Exception "+exe);	
		exe.printStackTrace();
	}
	LOGGER.info("PremiumDAOImpl premiumUpdateMethod || Exit");
	return saveFlag;
}

private void getTempToMainMove(FaculPremiumBean beanObj, String netDueOc) {
	try{
		String query="";
		String[] args = null;
		if(!"Main".equalsIgnoreCase(beanObj.getTableType())){
			query =getQuery("FAC_PREMIUM_TEMP_TO_MAIN");
	 		args = new String[2];
	 		args[0] = beanObj.getRequestNo();
	 		args[1] = beanObj.getBranchCode();
	 		this.mytemplate.update(query,args);
	 		/*query=getQuery("FAC_TRANS_UPDATE");
	 		args = new String[3];
	 		args[0] = beanObj.getTransactionNo();
	 		//args[1] = beanObj.getLoginId();
	 		args[1]= beanObj.getRequestNo() ;
	 		args[2]= beanObj.getBranchCode() ;
	 		this.mytemplate.update(query,args);*/
	 		//beanObj.setTransactionNo(args[0]);
	 		//transNo=args[0];
		}
	
	//if(beanObj.getRi_cession().equalsIgnoreCase("Yes")){
	query=getQuery(DBConstants.PREMIUM_SP_RETROSPLIT);
	LOGGER.info("SP Name==>"+query);
	args = new String[16];
	args[0]=beanObj.getContNo();
	args[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
	args[2]=beanObj.getProductId();
	args[3]=beanObj.getTransactionNo();
	args[4]=beanObj.getTransaction();
	args[5]=beanObj.getCurrencyId();
	args[6]=beanObj.getExchRate();
	args[7]=netDueOc;
	args[7]=beanObj.getBranchCode();
	args[8]="P";
	args[9]=beanObj.getAmendmentDate()==null?"":beanObj.getAmendmentDate();
	args[10]="";
	args[11]="";
	args[12]="";
	args[13]="";
	args[14]="";
	args[15]= beanObj.getRi_cession();
	for(int i=0;i<args.length;i++)
	LOGGER.info("Args["+i+"]==>"+args[i]);
	int spresult=this.mytemplate.update(query,args);		
	beanObj.setStatus("Premium Saved Sucussfully , And Your Transaction Id :"+ beanObj.getTransactionNo());
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

public List<Map<String,Object>> mdInstallmentDates(final String contNo,final String layerNo) {
	LOGGER.info("PremiumDAOImpl mdInstallmentDates || Enter");
	String query="";
	 List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    try{
	    Object[] args=new Object[6];
	    args[0]=contNo;
	    args[1]=layerNo;
	    args[2]=contNo;
	    args[3]=layerNo;
	    args[4] = contNo;
	    args[5]=layerNo;
	   // query=getQuery(DBConstants.PREMIUM_SELECT_MDINSTALMENTLIST);
	    query=getQuery("PREMIUM_MND_INS_LIST");
	    LOGGER.info("Select mdInstallmentDates Query=>"+query);
	    list=this.mytemplate.queryForList(query, args);		 
	    Map<String,Object> tempMap1 = new HashMap<String, Object>();
	    tempMap1.put("KEY1","EP");	    
	    tempMap1.put("VALUE","Endorsement Premium");
	    list.add(tempMap1);	
	    Map<String,Object> tempMap2 = new HashMap<String, Object>();
	    tempMap2.put("KEY1","RTP");	    
	    tempMap2.put("VALUE","Return Premium");
	    list.add(tempMap2);
	    Map<String,Object> tempMap3 = new HashMap<String, Object>();
	    tempMap3.put("KEY1","RVP");	    
	    tempMap3.put("VALUE","Reversal Premium");
	    list.add(tempMap3);
		}catch (Exception exe) {
			LOGGER.debug("Exception "+exe);			
	}
    LOGGER.info("PremiumDAOImpl mdInstallmentDates || Exit");
	   return list;
}

private String[] insertArguments(final FaculPremiumBean beanObj)
{
	LOGGER.info("PremiumDAOImpl insertArguments || Enter");
	String[] args=null;
	args=new String[53];
	args[0]=beanObj.getReceipt_no();
    args[1]=beanObj.getContNo();
  //args[2]=maxTransationNo(beanObj.getProductId(),beanObj.getBranchCode());
   // if("06".equalsIgnoreCase(beanObj.getBranchCode())){
	//	args[2]=new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction());
	/*}else
	args[2]=new DropDownControllor().getPolicyNo("3", "0", beanObj.getBranchCode());*/
	//args[3]=beanObj.getTransaction()+"-"+beanObj.getTransaction_year();
    args[2] = getRequestNo(beanObj);
	args[3]=beanObj.getTransaction();
	args[4]=StringUtils.isEmpty(beanObj.getInstalmentdate())?"":beanObj.getInstalmentdate();
	args[5]=StringUtils.isEmpty(beanObj.getInstlmentNo())?"":beanObj.getInstlmentNo();
	args[6]=beanObj.getCurrencyId();
	args[7]=beanObj.getExchRate();
	args[8]=getModeOfTransaction(beanObj.getPremiumQuotaShare(), beanObj);
	args[21]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
	args[9]=beanObj.getCommission_view();
	args[10]=getModeOfTransaction(beanObj.getCommissionQuotaShare(), beanObj);
	args[22]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
	args[11]=beanObj.getBrokerage_view();
	args[12]=getModeOfTransaction(beanObj.getBrokerage(), beanObj);
	args[23]=DropDownControllor.GetDesginationCountry(args[12], beanObj.getExchRate());
	args[13]=beanObj.getTax_view();
	args[14]=getModeOfTransaction(beanObj.getTax(), beanObj);
	args[24]=DropDownControllor.GetDesginationCountry(args[14], beanObj.getExchRate());
    args[33]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(), beanObj);
    args[34]=DropDownControllor.GetDesginationCountry(args[33], beanObj.getExchRate());
    args[35]=beanObj.getRi_cession();
    args[36]=beanObj.getLoginId();
    args[37]=beanObj.getBranchCode();
    args[38]=beanObj.getDepartmentId();
    args[39] = getModeOfTransaction(beanObj.getTaxDedectSource(), beanObj);
    args[40] = DropDownControllor.GetDesginationCountry(args[39],beanObj.getExchRate());
    args[41] = getModeOfTransaction(beanObj.getServiceTax(), beanObj);
    args[42] = DropDownControllor.GetDesginationCountry(args[41],beanObj.getExchRate());
    args[43] = getModeOfTransaction(beanObj.getBonus(), beanObj);
    args[44] = DropDownControllor.GetDesginationCountry(args[43],beanObj.getExchRate());
    args[45]=beanObj.getPredepartment();
    args[46]=beanObj.getSubProfitId().replace(" ", "");
		LOGGER.info("Premium==>"+beanObj.getPremiumQuotaShare()+"\nCommission==>"+beanObj.getCommissionQuotaShare()+
				"\nBrokerate==>"+beanObj.getBrokerage()+"\nTax===>"+beanObj.getTax());
		
		if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare()))
		{
			LOGGER.info("Afters");
			double premiums=Double.parseDouble(beanObj.getPremiumQuotaShare());
			if(StringUtils.isEmpty(beanObj.getCommissionQuotaShare()))
			{
				final double commission=premiums*(Double.parseDouble(beanObj.getCommission_view())/100);
				args[10]=getModeOfTransaction(commission+" ",beanObj); 
				args[22]=DropDownControllor.GetDesginationCountry(args[10],beanObj.getExchRate());
				
			}
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				final double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[12]=getModeOfTransaction(brokerage+" ",beanObj);
				args[23]=DropDownControllor.GetDesginationCountry(args[12],beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
				
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				final double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
				args[14]=getModeOfTransaction(tax+" ",beanObj);
				args[24]=DropDownControllor.GetDesginationCountry(args[14],beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			
		}
		//Added by sathish for java script failure cases-End
		args[15]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[16]="Y";
		//args[18]=beanObj.getEnteringMode();
		args[18]="2";
		args[19]=beanObj.getSettlement_status();
		args[20]=getModeOfTransaction(beanObj.getOtherCost().replaceAll(",",""),beanObj);
		args[26]=DropDownControllor.GetDesginationCountry(args[20],beanObj.getExchRate());
		args[17]=getNetDueFac(args,1);
		args[25]=DropDownControllor.GetDesginationCountry(args[17],beanObj.getExchRate());
		args[27]=beanObj.getCedentRef();
		args[28]=beanObj.getRemarks();
		args[29]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
		args[30]=DropDownControllor.GetDesginationCountry(args[29],beanObj.getExchRate());
		args[31]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
		args[32]=DropDownControllor.GetDesginationCountry(args[31],beanObj.getExchRate());
		args[47]=StringUtils.isEmpty(beanObj.getStatementDate()) ?"" :beanObj.getStatementDate();
		args[48]=beanObj.getProposal_No();
		args[49]=beanObj.getProductId();
		args[50]= beanObj.getChooseTransaction()==null?"":beanObj.getChooseTransaction();
		if("submit".equalsIgnoreCase(beanObj.getButtonStatus())){
			args[51] = "A";
		}else{
			args[51] = "P";
		}
		args[52] = beanObj.getMode();
		beanObj.setRequestNo(args[2]);
		//beanObj.setTransactionNo(args[2]);			 
		for(int i=0;i<args.length;i++)
		{
			LOGGER.info("Args["+i+"]=>"+args[i]);
		}	
	final String[] copiedArray = new String[args.length];
	System.arraycopy(args, 0, copiedArray, 0, args.length);
	LOGGER.info("PremiumDAOImpl maxTransationNo || Exit");
	return copiedArray;
}
private String getRequestNo(FaculPremiumBean beanObj) {
	String reqNo = "";
	try{
		String query=getQuery("GET_SEQ_NAME");
		String name=this.mytemplate.queryForObject(query, new String[]{beanObj.getBranchCode()},String.class);
		query="SELECT LPAD("+name+".nextval,6,0) FROM DUAL";
		reqNo = this.mytemplate.queryForObject(query,String.class);
		reqNo ="92"+reqNo;
	}catch(Exception e){
		e.printStackTrace();
	}
	return reqNo;
}

/*private  String maxTransationNo(final String productId,final String branchCode) {
	LOGGER.info("PremiumDAOImpl maxTransationNo || Enter");
	String query="";
	LOGGER.info("BranchCode=>"+branchCode);
	query = getQuery(DBConstants.PREMIUM_SELECT_MAXTRANSATION);
	final String TransactionId=(String)this.mytemplate.queryForObject(query,new Object[]{"11",branchCode},String.class);
	LOGGER.info("Transaction Id=>"+TransactionId);
	LOGGER.info("PremiumDAOImpl maxTransationNo || Exit");
	return TransactionId;
}*/
private static String getModeOfTransaction(final String Value,final FaculPremiumBean beanObj) {
LOGGER.info("PremiumDAOImpl getModeOfTransaction || Enter");
LOGGER.info("Value=>"+Value);
LOGGER.info("Entering Mode=>"+beanObj.getEnteringMode());
LOGGER.info("ShareSigned=>"+beanObj.getShareSigned());
String result="0";
double shareSigned=0.0;
DecimalFormat twoDForm = new DecimalFormat("#.##");
if(beanObj.getEnteringMode()!=null)
{
	if("1".equalsIgnoreCase(beanObj.getEnteringMode()))
	{
		shareSigned=Double.parseDouble(beanObj.getShareSigned());
	}
	else if("2".equalsIgnoreCase(beanObj.getEnteringMode()))
	{
		shareSigned=100;
	}
	LOGGER.info("Value==>"+Value);
	if(!"".equalsIgnoreCase(Value))
	{
			double finalValue=Double.parseDouble(Value) *shareSigned/100;
			LOGGER.info("Final Value==>"+finalValue);
			result=String.valueOf(Double.valueOf(twoDForm.format(finalValue)));
	} 
}
LOGGER.info("PremiumDAOImpl getModeOfTransaction || Exit");
return result;
}
private static String getNetDueFac(final String[] args,final int flag) {
	LOGGER.info("PremiumDAOImpl getNetDueFac || Enter");
	Double Net = 0.0;
	if (flag == 1) {
		if (StringUtils.isNotEmpty(args[8]))
		{
			Net += Double.parseDouble(args[8]);
		}
		if (StringUtils.isNotEmpty(args[39]))
		{
			Net += Double.parseDouble(args[39]);
		}
		if (StringUtils.isNotEmpty(args[41]))
		{
			Net += Double.parseDouble(args[41]);
		}
		if (StringUtils.isNotEmpty(args[10]))
		{
			Net -= Double.parseDouble(args[10]);
		}
		if (StringUtils.isNotEmpty(args[12]))
		{
			Net -= Double.parseDouble(args[12]);
		}
		if (StringUtils.isNotEmpty(args[14]))
		{
			Net -= Double.parseDouble(args[14]);
		}
		if (StringUtils.isNotEmpty(args[20]))
		{
			Net -= Double.parseDouble(args[20]);
		}
		if (StringUtils.isNotEmpty(args[33]))
		{
			Net -= Double.parseDouble(args[33]);
		}
		if (StringUtils.isNotEmpty(args[43]))
		{
			Net -= Double.parseDouble(args[43]);
		}
	} else if (flag == 2) {
		if (StringUtils.isNotEmpty(args[5]))
		{
			Net += Double.parseDouble(args[5]);
		}
		if (StringUtils.isNotEmpty(args[36]))
		{
			Net += Double.parseDouble(args[36]);
		}
		if (StringUtils.isNotEmpty(args[38]))
		{
			Net += Double.parseDouble(args[38]);
		}
		if (StringUtils.isNotEmpty(args[7]))
		{
			Net -= Double.parseDouble(args[7]);
		}
		if (StringUtils.isNotEmpty(args[9]))
		{
			Net -= Double.parseDouble(args[9]);
		}
		if (StringUtils.isNotEmpty(args[11]))
		{
			Net -= Double.parseDouble(args[11]);
		}
		if (StringUtils.isNotEmpty(args[17]))
		{
			Net -= Double.parseDouble(args[17]);
		}
		if (StringUtils.isNotEmpty(args[31]))
        {
            Net -= Double.parseDouble(args[31]);
        }
		if (StringUtils.isNotEmpty(args[40]))
        {
            Net -= Double.parseDouble(args[40]);
        }
	}
	LOGGER.info("PremiumDAOImpl getNetDueFac || Exit");
	return String.valueOf(Net);
}
private static String getNetDueAmount(final String[] args,final String CliamPaid) {
	LOGGER.info("PremiumDAOImpl getNetDueAmount || Enter");
	double Abt=0;
	double Bbt=0;
	if(StringUtils.isNotEmpty(args[12]))
	{
	Abt+=Double.parseDouble(args[12]);
	}
	if(StringUtils.isNotEmpty(args[14]))
	{
	Abt+=Double.parseDouble(args[14]);
	}
	if(StringUtils.isNotEmpty(args[16]))
	{
	Abt+=Double.parseDouble(args[16]);
	}
	if(StringUtils.isNotEmpty(args[17]))
	{
	Abt+=Double.parseDouble(args[17]);
	}
	if(StringUtils.isNotEmpty(args[19]))
	{
	Abt+=Double.parseDouble(args[19]);
	}
	if(StringUtils.isNotEmpty(args[21]))
	{
	Abt+=Double.parseDouble(args[21]);
	}
	if(StringUtils.isNotEmpty(args[32]))
	{
		Abt+=Double.parseDouble(args[32]);
	}
	if(StringUtils.isNotEmpty(args[63]))
	{
		Abt+=Double.parseDouble(args[63]);
	}
	if(StringUtils.isNotEmpty(args[13]))
	{
		Bbt+=Double.parseDouble(args[13]);
	}
	if(StringUtils.isNotEmpty(args[15]))
	{
		Bbt+=Double.parseDouble(args[15]);
	}
	if(StringUtils.isNotEmpty(args[8]))
	{
		Bbt+=Double.parseDouble(args[8]);
	}
	if(StringUtils.isNotEmpty(args[10]))
	{
		Bbt+=Double.parseDouble(args[10]);
	}
	if(StringUtils.isNotEmpty(args[18]))
	{
		Bbt+=Double.parseDouble(args[18]);
	}
	if(StringUtils.isNotEmpty(args[20]))
	{
		Bbt+=Double.parseDouble(args[20]);
	}
	if(StringUtils.isNotEmpty(args[22]))
	{
		Bbt+=Double.parseDouble(args[22]);
	}
	if(StringUtils.isNotEmpty(args[23]))
	{
		Bbt+=Double.parseDouble(args[23]);
	}
	if(StringUtils.isNotEmpty(args[24]))
	{
		Bbt+=Double.parseDouble(args[24]);
	}
	if(StringUtils.isNotEmpty(CliamPaid))
	{
		Bbt+=Double.parseDouble(CliamPaid);
	}
	if(StringUtils.isNotEmpty(args[31]))
	{
		Bbt+=Double.parseDouble(args[31]);
	}
	if(StringUtils.isNotEmpty(args[30]))
	{
		Bbt+=Double.parseDouble(args[30]);
	}
	if(StringUtils.isNotEmpty(args[34]))
	{
		Bbt+=Double.parseDouble(args[34]);
	}
	if(StringUtils.isNotEmpty(args[67]))
	{
		Bbt+=Double.parseDouble(args[67]);
	}
	LOGGER.info("A==>"+Abt);
	LOGGER.info("B==>"+Bbt);
    final double cbt=Abt-Bbt;
    LOGGER.info("Net Due==>"+cbt);
    LOGGER.info("PremiumDAOImpl getNetDueAmount || Exit");
	return String.valueOf(cbt);
}
private static String getNetDueXol(final String[] args) {
	LOGGER.info("PremiumDAOImpl getNetDueXol || Enter");
	final double Ant=StringUtils.isEmpty(args[13]) ? 0 :Double.parseDouble(args[13]) ;
	final double Bnt=StringUtils.isEmpty(args[14]) ? 0 :Double.parseDouble(args[14]) ;
	final double Cnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
	final double Dnt=StringUtils.isEmpty(args[8]) ? 0 :Double.parseDouble(args[8]) ;
	final double Ent=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
	final double Fnt=StringUtils.isEmpty(args[12]) ? 0 :Double.parseDouble(args[12]) ;
	final double Hnt=StringUtils.isEmpty(args[23]) ? 0 :Double.parseDouble(args[23]) ;
	LOGGER.info("A==>"+Ant);
	LOGGER.info("B==>"+Bnt);
    final double cnt=(Ant+Bnt+Cnt)-(Dnt+Ent+Fnt+Hnt);
    LOGGER.info("Net Due==>"+cnt);
    LOGGER.info("PremiumDAOImpl getNetDueXol || Exit");
	return String.valueOf(cnt);
}
public static String[] updateAruguments(final FaculPremiumBean beanObj) {
	LOGGER.info("PremiumDAOImpl updateAruguments || Enter");
	String[] args=null;
	args=new String[48];
	args[0]=StringUtils.isEmpty(beanObj.getInstalmentdate())?"":beanObj.getInstalmentdate();
	args[1]=StringUtils.isEmpty(beanObj.getInstlmentNo())?"":beanObj.getInstlmentNo();
	//args[2]=beanObj.getTransaction()+"-"+beanObj.getTransaction_year();		 
	args[2]=beanObj.getTransaction();
	args[3]=beanObj.getCurrencyId();
	args[4]=beanObj.getExchRate();
	args[5]=getModeOfTransaction(beanObj.getPremiumQuotaShare(), beanObj);
	args[18]=DropDownControllor.GetDesginationCountry(args[5], beanObj.getExchRate());
	args[6]=beanObj.getCommission_view();
	args[7]=getModeOfTransaction(beanObj.getCommissionQuotaShare(), beanObj);
	args[19]=DropDownControllor.GetDesginationCountry(args[7], beanObj.getExchRate());
	args[8]=beanObj.getBrokerage_view();
	args[9]=getModeOfTransaction(beanObj.getBrokerage(), beanObj);
	args[20]=DropDownControllor.GetDesginationCountry(args[9], beanObj.getExchRate());
	args[10]=beanObj.getTax_view();
	args[11]=getModeOfTransaction(beanObj.getTax(), beanObj);
	args[21]=DropDownControllor.GetDesginationCountry(args[11], beanObj.getExchRate());
    args[31]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(), beanObj);
    args[32]=DropDownControllor.GetDesginationCountry(args[31], beanObj.getExchRate());
    args[33]=beanObj.getRi_cession();
    args[34]=beanObj.getLoginId();
    args[35]=beanObj.getBranchCode();
    args[36] = getModeOfTransaction(beanObj.getTaxDedectSource(), beanObj);
    args[37] = DropDownControllor.GetDesginationCountry(args[36],beanObj.getExchRate());
    args[38] = getModeOfTransaction(beanObj.getServiceTax(), beanObj);
    args[39] = DropDownControllor.GetDesginationCountry(args[38],beanObj.getExchRate());
    args[40] = getModeOfTransaction(beanObj.getBonus(), beanObj);
    args[41] = DropDownControllor.GetDesginationCountry(args[40],beanObj.getExchRate());
    args[42]=beanObj.getPredepartment();
    args[43]=beanObj.getSubProfitId();
    args[44]=beanObj.getStatementDate();
    args[45] = beanObj.getMode();
    args[46]=beanObj.getContNo();
    if(StringUtils.isBlank(beanObj.getTransactionNo())){
    	args[47]=beanObj.getRequestNo();
    }else{
    	args[47]=beanObj.getTransactionNo();
    
    }
    
		//Added by sathish for java script failure cases-Start
		LOGGER.info("Before");
		LOGGER.info("Premium==>"+beanObj.getPremiumQuotaShare());
		LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
		LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
		LOGGER.info("Tax===>"+beanObj.getTax());
		if(!StringUtils.isEmpty(beanObj.getPremiumQuotaShare()))
		{
			LOGGER.info("After");
			double premiums=Double.parseDouble(beanObj.getPremiumQuotaShare());
			if(StringUtils.isEmpty(beanObj.getCommissionQuotaShare()))
			{
				final double commission=premiums*(Double.parseDouble(beanObj.getCommission_view())/100);
				LOGGER.info("Commission===>"+commission);
				args[7]=getModeOfTransaction(commission+" ",beanObj); 
				args[19]=DropDownControllor.GetDesginationCountry(args[7],beanObj.getExchRate());
			}
			if(StringUtils.isEmpty(beanObj.getBrokerage()))
			{
				final double brokerage=premiums*(Double.parseDouble(beanObj.getBrokerage_view())/100);
				args[9]=getModeOfTransaction(brokerage+" ",beanObj);
				args[20]=DropDownControllor.GetDesginationCountry(args[9],beanObj.getExchRate());
				LOGGER.info("Brokerate===>"+brokerage);
				
			}
			if(StringUtils.isEmpty(beanObj.getTax()))
			{
				final double tax=premiums*(Double.parseDouble(beanObj.getTax_view())/100);
				args[11]=getModeOfTransaction(tax+" ",beanObj);
				args[21]=DropDownControllor.GetDesginationCountry(args[11],beanObj.getExchRate());
				LOGGER.info("Tax===>"+tax);
			}
			
		}
		//Added by sathish for java script failure cases-End
		args[12]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
		args[13]="Y";
		//args[15]=beanObj.getEnteringMode();
		args[15]="2";
		args[16]=beanObj.getReceipt_no();
		args[17]=getModeOfTransaction(beanObj.getOtherCost(),beanObj);
		args[23]=DropDownControllor.GetDesginationCountry(args[17],beanObj.getExchRate());
		args[14]=getNetDueFac(args,2);
		args[22]=DropDownControllor.GetDesginationCountry(args[14],beanObj.getExchRate());
		args[24]=beanObj.getCedentRef();
		args[25]=beanObj.getRemarks();
		args[26]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
		args[27]=DropDownControllor.GetDesginationCountry(args[26],beanObj.getExchRate());
		args[28]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
		args[29]=DropDownControllor.GetDesginationCountry(args[28],beanObj.getExchRate());
		args[30]=beanObj.getAmendmentDate();
//		args[31]=beanObj.getContNo();
//		args[32]=beanObj.getTransactionNo();
		
		for(int i=0;i<args.length;i++)
		{
			LOGGER.info("Args["+i+"]=>"+args[i]);
		}		
	final String[] copiedArray = new String[args.length];
	System.arraycopy(args, 0, copiedArray, 0, args.length);
	LOGGER.info("PremiumDAOImpl updateAruguments || Exit");
	return copiedArray;
}
private static String updateNetDue(final String[] args,final String claimpaid) {
	LOGGER.info("PremiumDAOImpl updateNetDue || Enter");
	double Aut=0;
	double But=0;
	if(StringUtils.isNotEmpty(args[10]))
	{
	Aut+=Double.parseDouble(args[10]);
	}
	if(StringUtils.isNotEmpty(args[12]))
	{
	Aut+=Double.parseDouble(args[12]);
	}
	if(StringUtils.isNotEmpty(args[14]))
	{
	Aut+=Double.parseDouble(args[14]);
	}
	if(StringUtils.isNotEmpty(args[15]))
	{
	Aut+=Double.parseDouble(args[15]);
	}
	if(StringUtils.isNotEmpty(args[17]))
	{
	Aut+=Double.parseDouble(args[17]);
	}
	if(StringUtils.isNotEmpty(args[19]))
	{
	Aut+=Double.parseDouble(args[19]);
	}
	if(StringUtils.isNotEmpty(args[29]))
	{
		Aut+=Double.parseDouble(args[29]);
	}
	if(StringUtils.isNotEmpty(args[59]))
	{
		Aut+=Double.parseDouble(args[59]);
	}
	if(StringUtils.isNotEmpty(args[11]))
	{
		But+=Double.parseDouble(args[11]);
	}
	if(StringUtils.isNotEmpty(args[13]))
	{
		But+=Double.parseDouble(args[13]);
	}
	if(StringUtils.isNotEmpty(args[6]))
	{
		But+=Double.parseDouble(args[6]);
	}
	if(StringUtils.isNotEmpty(args[8]))
	{
		But+=Double.parseDouble(args[8]);
	}
	if(StringUtils.isNotEmpty(args[16]))
	{
		But+=Double.parseDouble(args[16]);
	}
	if(StringUtils.isNotEmpty(args[18]))
	{
		But+=Double.parseDouble(args[18]);
	}
	if(StringUtils.isNotEmpty(args[20]))
	{
		But+=Double.parseDouble(args[20]);
	}
	if(StringUtils.isNotEmpty(args[21]))
	{
		But+=Double.parseDouble(args[21]);
	}
	if(StringUtils.isNotEmpty(args[22]))
	{
		But+=Double.parseDouble(args[22]);
	}
	if(StringUtils.isNotEmpty(claimpaid))
	{
		But+=Double.parseDouble(claimpaid);
	}
	if(StringUtils.isNotEmpty(args[31]))
	{
		But+=Double.parseDouble(args[31]);
	}
	if(StringUtils.isNotEmpty(args[27]))
	{
		But+=Double.parseDouble(args[27]);
	}
	if(StringUtils.isNotEmpty(args[28]))
	{
		But+=Double.parseDouble(args[28]);
	}
	if(StringUtils.isNotEmpty(args[63]))
	{
		But+=Double.parseDouble(args[63]);
	}
	LOGGER.info("A==>"+Aut);
	LOGGER.info("B==>"+But);
    double cut=Aut-But;
    LOGGER.info("Net Due==>"+cut);
    LOGGER.info("PremiumDAOImpl updateNetDue || Exit");
	return String.valueOf(cut);
}
private static String getNetDueXolUpdate(final String[] args) {
	LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Enter");
	double Ant=StringUtils.isEmpty(args[9]) ? 0 :Double.parseDouble(args[9]) ;
	double Bnt=StringUtils.isEmpty(args[10]) ? 0 :Double.parseDouble(args[10]) ;
	double Cnt=StringUtils.isEmpty(args[11]) ? 0 :Double.parseDouble(args[11]) ;
	double Dnt=StringUtils.isEmpty(args[4]) ? 0 :Double.parseDouble(args[4]) ;
	double Ent=StringUtils.isEmpty(args[6]) ? 0 :Double.parseDouble(args[6]) ;
	double Fnt=StringUtils.isEmpty(args[15]) ? 0 :Double.parseDouble(args[15]) ;
	LOGGER.info("A==>"+Ant);
	LOGGER.info("B==>"+Bnt);
	double c=(Ant+Bnt+Cnt)-(Dnt+Ent+Fnt);
    LOGGER.info("Net Due==>"+c);
    LOGGER.info("PremiumDAOImpl getNetDueXolUpdate || Exit");
	return String.valueOf(c);
}

public List getMandDInstallments(String contNo, String layerNo)
		{
	LOGGER.info("PremiumDAOImpl getMandDInstallments || Enter");
	List list=null;
	try{
		String query=this.getQuery(DBConstants.PREMIUM_SELECT_MDINSTALLMENTS);
		 LOGGER.info("Select mdInstallmentDates Query=>"+query);
		 LOGGER.info("Contract No=>"+contNo);
		 LOGGER.info("Layer No=>"+layerNo);
		 list=this.mytemplate.queryForList(query, new Object[]{contNo,layerNo});
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);		
	}
	LOGGER.info("PremiumDAOImpl getMandDInstallments || Exit List Size"+list.size());
	return list;
}

public String GetInstalmentAmount(String contractNo, String getAmount)
		{
	LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Enter");
	String  string=null;
	try{
		String query=this.getQuery(DBConstants.PREMIUM_SELECT_MNDPREMIUMOC);
		 final String[] Instalmentno=getAmount.split("_");
		 LOGGER.info("Select mdInstallmentDates Query=>"+query);
		 LOGGER.info("Contract No=>"+contractNo);
		 LOGGER.info("Inst No=>"+Instalmentno[0]);
		 string=(String)this.mytemplate.queryForObject(query, new Object[]{contractNo,0,Instalmentno[0]},String.class);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);		
	}
	LOGGER.info("PremiumDAOImpl GetInstalmentAmount || Exit Ins Amt=>"+string);
	return string;
}

public String getRPPremiumOC(String contractNo, String layerNo)
		{
	LOGGER.info("PremiumDAOImpl getRPPremiumOC || Enter");
	String  string=null;
	try{
		String query=this.getQuery(DBConstants.PREMIUM_SELECT_RPPREMIUMOC);
		 LOGGER.info("Select RP Premium Query=>"+query);
		 LOGGER.info("Contract No=>"+contractNo);
		 LOGGER.info("Layer No=>"+layerNo);
		 string=(String)this.mytemplate.queryForObject(query, new Object[]{contractNo,layerNo},String.class);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);		
	}
	LOGGER.info("PremiumDAOImpl getRPPremiumOC || Exit RP Amt=>"+string);
	return string;
}

public List getSPRetroList(String contNo,String productId, String layerNo)
		{
	LOGGER.info("PremiumDAOImpl getSPRetroList || Enter");
	List list=null;
	try{
		String query="";
		Object args[]=null;
		if("1".equals(productId)){
			args=new Object[1];
			args[0]=contNo;
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETFACSPRETRO);
		}else if("2".equals(productId))
		{
			args=new Object[1];
			args[0]=contNo;
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETTREATYSPRETRO);
		}else if("3".equals(productId)){
			args=new Object[2];
			args[0]=contNo;
			args[1]=layerNo;
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETXOLSPRETRO);	
		}
		 LOGGER.info("Select SpRetro Query=>"+query);
		 LOGGER.info("Product Code No=>"+productId);
		 LOGGER.info("Contract No=>"+contNo);
		 LOGGER.info("Layer No=>"+layerNo);
		 list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);		
	}
	LOGGER.info("PremiumDAOImpl getSPRetroList || Exit List Size"+list.size());
	return list;
}

public List getRetroContracts(FaculPremiumBean beanObj) {
	// TODO Auto-generated method stub
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Enter");
	List list=null;
	try{
		String query="";
		Object args[]=null;
		args=new Object[2];
		args[0]=beanObj.getProposal_No();
		args[1]=beanObj.getNoOfRetro();
		query=this.getQuery(DBConstants.PREMIUM_SELECT_INSDETAILS);
		LOGGER.info("Select SpRetro Query=>"+query);
		LOGGER.info("Proposal No=>"+beanObj.getProposal_No());
		LOGGER.info("No of Retro=>"+beanObj.getNoOfRetro());
		list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);	
	}
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Exit List Size"+list.size());
	return list;
}

public String getSumOfShareSign(FaculPremiumBean beanObj)
		{
	LOGGER.info("PremiumDAOImpl getSumOfShareSign || Enter");
	String query="";
	String  string="0";
	String noOfRetroCess="";
	Object args[]=null;
	try{		
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETNORETROCESS);
			LOGGER.info("Query=>"+query);
			args=new Object[1];
			args[0]=beanObj.getRetroContractNo();
			LOGGER.info("Args[0]==>"+args[0]);
			noOfRetroCess=(String)this.mytemplate.queryForObject(query, args,String.class);
			LOGGER.info("Result=>"+noOfRetroCess);
			query=this.getQuery(DBConstants.PREMIUM_SELECT_GETSUMOFSHARESIGN);
			LOGGER.info("Query=>"+query);
			args=new Object[2];
			args[0]=beanObj.getRetroContractNo();
			args[1]=Integer.parseInt(noOfRetroCess)-1;
			LOGGER.info("Args[0]==>"+args[0]+"\nArgs[1]==>"+args[1]);
			string=(String)this.mytemplate.queryForObject(query, args,String.class);
			LOGGER.info("Result=>"+string);
		
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);		
	}
	LOGGER.info("PremiumDAOImpl getSumOfShareSign || Exit Sum of Retro SS Amt=>"+string);
	return string;
}

public List<Map<String, Object>> getClaimNosDropDown(String contNo)
		{
		LOGGER.info("PremiumDAOImpl getClaimNosDropDown() || Enter Contract No=>"+contNo);
	 	List<Map<String, Object>> claimNos=new ArrayList<Map<String,Object>>();
		try{
		 	String query=getQuery(DBConstants.PREMIUM_SELECT_GETCLAIMNODROPDOWN);
		    LOGGER.info("Select Query=>"+query);
		    LOGGER.info("Args[0]=>"+contNo);
		    claimNos=this.mytemplate.queryForList(query,new Object[]{contNo});
		}catch(Exception e){
			LOGGER.debug("Exception "+e);			
		}
	    LOGGER.info("PremiumDAOImpl getClaimNosDropDown() || Exit List size=>"+claimNos.size());
	    return claimNos;
}

public boolean getCashLossUpdateValidation(String contractNo,
		String transactionNo, String claimNo,String cashLOossUpdateOc,String excRate) {
	LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Enter Contract No=>"+contractNo+"transactionNo=>"+transactionNo+"claimNo=>"+claimNo+"cashLOossUpdateOc=>"+cashLOossUpdateOc+"excRate=>"+excRate);
	boolean cashlossFlag=false;
	transactionNo=StringUtils.isBlank(transactionNo)?"9999999999":transactionNo;
 	List<Map<String,String>> claimNos=new ArrayList<Map<String,String>>();
	try{
	 	String query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDCLAIM);
	    LOGGER.info("Select Query=>"+query);
	    LOGGER.info("Args[0]=>"+contractNo+"Args[1]=>"+claimNo);
	    double sumClaimsPaid=(Double)this.mytemplate.queryForObject(query, new String[]{contractNo,claimNo},Double.class);
	    LOGGER.info("Select Result=>"+sumClaimsPaid);
	    query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFCASHLOSSUPDATE);
	    LOGGER.info("Select Query=>"+query);
	    LOGGER.info("Args[0]=>"+contractNo+"Args[1]=>"+transactionNo+"Args[2]=>"+claimNo+"Args[3]=>"+transactionNo);
	    double sumLossUpdatesPaid=(Double)this.mytemplate.queryForObject(query, new String[]{contractNo,transactionNo,claimNo,transactionNo},Double.class);
	    double totalLossUpdates=sumLossUpdatesPaid+(Double.parseDouble(cashLOossUpdateOc)/Double.parseDouble(excRate));
	    LOGGER.info("Select Result=>"+totalLossUpdates);
	    if(totalLossUpdates>sumClaimsPaid)
	    	cashlossFlag=true;
	}catch(Exception e){
		LOGGER.debug("Exception "+e);		
	}
    LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Exit cashlossFlag=>"+cashlossFlag);
    return cashlossFlag;
}

public String getMovementReportMaxDate(String branchCode) {
	LOGGER.info("PremiumDAOImpl getCashLossUpdateValidation() || Enter");
	String maxDate = "";
	LOGGER.info("PremiumDAOImpl getMovementReportMaxDate || Enter");
	String query = this.getQuery(DBConstants.PREMIUM_MOV_REP_MAX_DATE);
	LOGGER.info("Select Query=>"+query);
	LOGGER.info("Obj[]=>"+branchCode);
	try {
		maxDate = (String) this.mytemplate.queryForObject(query,new Object[]{branchCode},
				String.class);
	} catch (Exception e) {
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("PremiumDAOImpl getMovementReportMaxDate() || Exit maxDate"+maxDate);
	return maxDate;
}
public List<FaculPremiumBean> getAllocatedList(final FaculPremiumBean beanObj)
{
	double a=0;
	LOGGER.info("FaculPremiumDAOImpl allocateView() || Enter");
	List<FaculPremiumBean> allocateList = new ArrayList<FaculPremiumBean>();
	try{		
		String[] args = new String[4];
		args[0] = beanObj.getContNo();
		args[1] = beanObj.getTransactionNo();
		args[2] = beanObj.getContNo();
		args[3] = beanObj.getTransactionNo();
		String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSACTION);		
		selectQry = selectQry + " ORDER BY SNO DESC";
		LOGGER.info("Query=>"+selectQry);
		List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
		LOGGER.info("Result Size=>"+list.size());	
		if (list.size()>0) {			
			for (int i = 0; i < list.size(); i++) {				
				Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
				FaculPremiumBean tempBean = new FaculPremiumBean();
				tempBean.setSerial_no(tempMap.get("SNO")==null?"":tempMap.get("SNO").toString());
				tempBean.setAllocateddate(tempMap.get("INCEPTION_DATE")==null?"":tempMap.get("INCEPTION_DATE").toString());
				//tempBean.setTransactionno(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
				//tempBean.setContractno(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
				tempBean.setProductname(tempMap.get("PRODUCT_NAME")==null?"":tempMap.get("PRODUCT_NAME").toString());
				tempBean.setType(tempMap.get("TYPE")==null?"":tempMap.get("TYPE").toString());
				tempBean.setPayamount(tempMap.get("PAID_AMOUNT")==null?"":tempMap.get("PAID_AMOUNT").toString());
				tempBean.setCurrencyValue(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
				tempBean.setAlloccurrencyid(tempMap.get("CURRENCY_ID")==null?"":tempMap.get("CURRENCY_ID").toString());
				tempBean.setStatus(("R".equals(tempMap.get("STATUS")==null?"":tempMap.get("STATUS").toString())?"Reverted":"Allocated"));	
				//tempBean.setStatus(tempMap.get("ADJUSTMENT_TYPE")==null?"":tempMap.get("ADJUSTMENT_TYPE").toString());
				tempBean.setPay_rec_no(tempMap.get("RECEIPT_NO")==null?"":tempMap.get("RECEIPT_NO").toString());
				tempBean.setSettlementType(tempMap.get("TRANS_TYPE")==null?"":tempMap.get("TRANS_TYPE").toString());
				if(tempBean.getSettlementType().equalsIgnoreCase("PAYMENT") || tempBean.getSettlementType().equalsIgnoreCase("RECEIPT")){
				beanObj.setAllocateType(tempMap.get("ALLOCATE_TYPE")==null?"":tempMap.get("ALLOCATE_TYPE").toString());
				//beanObj.setAllocateType((String)this.mytemplate.queryForObject("SELECT TRANS_TYPE FROM TTRN_PAYMENT_RECEIPT WHERE PAYMENT_RECEIPT_NO=?",new Object[]{tempBean.getPay_rec_no()},String.class));
				}
				allocateList.add(tempBean);	
				a=a+Double.parseDouble(tempMap.get("PAID_AMOUNT")==null?"":tempMap.get("PAID_AMOUNT").toString());
			}
		}
		if(a>0){
		beanObj.setTotalAmount(DropDownControllor.formatter(Double.toString(a)));
		}
		else{
			beanObj.setTotalAmount("");
		}
	
	}
  	catch(Exception exe)
	{
		LOGGER.debug("Exception"+exe);
			}
  	LOGGER.info("FaculPremiumDAOImpl allocateView() || Exit Map");
	return allocateList;

}	public List getBrokerAndCedingName(FaculPremiumBean beanObj) {
	// TODO Auto-generated method stub
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Enter");
	List list=null;
	try{
		String query="";
		Object args[]=null;
		args=new Object[4];
		args[0]=beanObj.getContNo();
		args[1]=beanObj.getBranchCode();
		args[2]=beanObj.getContNo();
		args[3]=beanObj.getBranchCode();
		query=this.getQuery(DBConstants.BROKER_AND_CEDING_NAME);
		LOGGER.info("Select BrokerAndCedingName Query=>"+query);
		LOGGER.info("Contract No=>"+beanObj.getProposal_No());
		list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);	
	}
	LOGGER.info("PremiumDAOImpl getRetroContracts() || Exit List Size"+list.size());
	return list;
}

public String GetPreviousPremium(FaculPremiumBean bean) {
	String premium="";
	LOGGER.info("Enter Into GetPreviousPremium()");
	try{
		String query=" select nvl(sum(PREMIUM_QUOTASHARE_OC),0) from rsk_premium_details where CONTRACT_NO= ?";
		LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+bean.getContNo());
		premium=(String) this.mytemplate.queryForObject(query, new Object[]{bean.getContNo()},String.class);
	}catch(Exception e){
		LOGGER.debug("Exception "+e);
	}
	LOGGER.info("Exit from GetPreviousPremium()"+premium);
	return premium;
}

public String GetContractPremium(FaculPremiumBean bean) {
	String premium="";
	LOGGER.info("Enter Into GetContractPremium()");
	try{
		String query="select nvl(GWPI_OUR_SHARE_OC,0) from  TTRN_FAC_RISK_PROPOSAL TFRP where RSK_PROPOSAL_NUMBER=(select PROPOSAL_NO from position_master PM where CONTRACT_NO=? and amend_id=(select max(amend_id) from position_master where CONTRACT_NO=pm.CONTRACT_NO and branch_code=?)) and RSK_ENDORSEMENT_NO=(select max(RSK_ENDORSEMENT_NO) from TTRN_FAC_RISK_PROPOSAL where RSK_PROPOSAL_NUMBER=TFRP.RSK_PROPOSAL_NUMBER)";
		LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+bean.getContNo(),bean.getBranchCode());
		premium=(String) this.mytemplate.queryForObject(query, new Object[]{bean.getContNo(),bean.getBranchCode()},String.class);
	}catch(Exception e){
		LOGGER.debug("Exception "+e);
		e.printStackTrace();
	}
	LOGGER.info("Exit from GetContractPremium()"+premium);
	return premium;
}
public List<Map<String, Object>> currencyList(FaculPremiumBean bean) {
	LOGGER.info("PremiumDAOImpl currencyList() || Enter");
	List<Map<String,Object>> list=null;
	try{
		String query="";
		Object args[]=null;
		args=new Object[2];
		args[0]=bean.getBranchCode();
		args[1]=bean.getBranchCode();
		query=this.getQuery(DBConstants.CURRENCY_LIST);
		list=this.mytemplate.queryForList(query, args);
	}catch(Exception e)
	{
		LOGGER.debug("Exception "+e);	
	}
	LOGGER.info("PremiumDAOImpl currencyList() || Exit List Size"+list.size());
	return list;
}

public List<Map<String, Object>> productIdList(FaculPremiumBean bean) {
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	LOGGER.info("Enter ino product List");
	try{
	String query=getQuery("GET_PRODUC_ID_LIST");
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

public void PremiumContractDetails(FaculPremiumBean bean, String countryId) {
	 String query="";
     Object args[] = null;
     List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
     try {
		 query = getQuery("FAC_PREMIUM_CONTRACT_DETAILS");
		 args = new Object[6];
		 args[0] = bean.getContNo();
		 args[1] = bean.getLayerno();
		 args[2] = bean.getBranchCode();
		 args[3] = bean.getBranchCode();
		 args[4] = bean.getContNo();
		 args[5] = bean.getLayerno();
		 LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+StringUtils.join(args, ","));
		 res = this.mytemplate.queryForList(query, args);
		 if (res.size() > 0) {
			 for (int i = 0; i < res.size(); i++) {
				 Map<String, Object> map = res.get(i);
				    bean.setContNo(map.get("RSK_CONTRACT_NO")== null ? "" : map.get("RSK_CONTRACT_NO").toString());
				    bean.setAmendId(map.get("RSK_ENDORSEMENT_NO")== null ? "" : map.get("RSK_ENDORSEMENT_NO").toString());
				    bean.setSubProfit_center(map.get("TMAS_DEPARTMENT_NAME")== null ? "" : map.get("TMAS_DEPARTMENT_NAME").toString());
				    bean.setCedingCo(map.get("COMPANY")== null ? "" : map.get("COMPANY").toString());
				    bean.setTreatyName_type(map.get("RSK_TREATYID")== null ? "" : map.get("RSK_TREATYID").toString());
				    bean.setProposal_No(map.get("RSK_PROPOSAL_NUMBER")== null ? "" : map.get("RSK_PROPOSAL_NUMBER").toString());
				    bean.setUwYear(map.get("RSK_UWYEAR")== null ? "" : map.get("RSK_UWYEAR").toString());
				    bean.setLayerno(map.get("RSK_LAYER_NO")== null ? "" : map.get("RSK_LAYER_NO").toString());
				    bean.setInsDate(map.get("INS_DATE")== null ? "" : map.get("INS_DATE").toString());
				    bean.setExpDate(map.get("EXP_DATE")== null ? "" : map.get("EXP_DATE").toString());
				    bean.setBroker(map.get("BROKER")== null ? "" : map.get("BROKER").toString());
				    bean.setDepartmentId(map.get("RSK_DEPTID")== null ? "" : map.get("RSK_DEPTID").toString());
				    bean.setInsured_name(map.get("RSK_INSURED_NAME")== null ? "" : map.get("RSK_INSURED_NAME").toString());
				    
				   // bean.setAcqBonusName(map.get("BONUS_TYPE")== null ? "" : map.get("BONUS_TYPE").toString());
				    bean.setBonusId(map.get("BONUS_TYPE")== null ? "" : map.get("BONUS_TYPE").toString());
				    if("LCB".equalsIgnoreCase(bean.getBonusId())){
				    	bean.setAcqBonusName("Low Claim Bonus");
					}
					else if("NCB".equalsIgnoreCase(bean.getBonusId())){
						bean.setAcqBonusName("No Claim Bonus");
					}
			 }
		 }
	 }
	 catch(Exception e)   {
     	e.printStackTrace();
     	LOGGER.debug	("Exception @ {" + e + "}");
     }

}

public List<Map<String, Object>> bonusdetails(FaculPremiumBean bean, String countryId) {
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
    String cur = "";
    List<String> exhRate = new ArrayList<String>();
    List<String> total = new ArrayList<String>();
    List<String> curId = new ArrayList<String>();
		List<String> curName = new ArrayList<String>();
    //String output="0.00";
    String currency ="";
        try {
			Object args[] = null;
			String query = "";
				query = getQuery("GET_BONUS_CURRENY_DET");
				args = new Object[9];
				args[0] = bean.getContNo();
				args[1] = bean.getLayerno();
				args[2] = bean.getTransaction();
				args[3] = bean.getBranchCode();
				args[4] = bean.getContNo();
				args[5] = bean.getLayerno();
				args[6] = bean.getTransaction();
				args[7] = bean.getBranchCode();
				args[8] = bean.getBranchCode();
				list = this.mytemplate.queryForList(query, args);
			
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					currency = map.get("SHORT_NAME").toString() == null ? "" : map.get("SHORT_NAME").toString();
					cur = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					if (StringUtils.isNotBlank(bean.getMode()) && "bonus".equalsIgnoreCase(bean.getMode())) {
						String rate = new DropDownControllor().GetExchangeRate(cur, bean.getTransaction(), countryId, bean.getBranchCode());
						String gridexchange= new DropDownControllor().GetExchangeRate(bean.getCurrencyId(), bean.getTransaction(), countryId, bean.getBranchCode());
						double exchange = Double.parseDouble(gridexchange) / Double.parseDouble(rate);
						exhRate.add(DropDownControllor.formattereight(Double.toString(exchange)));
					}
					curId.add(cur);
					curName.add(currency);
				}
			}else if(list.size()==0 && (StringUtils.isNotBlank(bean.getType())&&bean.getType().equals("Y"))){
				curId.add(bean.getCurrencyId());
				query=getQuery("GET_CURRENCY_NAME");
				String currenyName= this.mytemplate.queryForObject(query, new Object[]{bean.getBranchCode(),bean.getCurrencyId()},String.class);
				curName.add(currenyName);
				exhRate.add(DropDownControllor.formattereight(bean.getExchRate()));
				
				 Map<String,Object> doubleMap = new HashMap<String,Object>();
				doubleMap.put("SHORT_NAME",currenyName);
				list1.add(doubleMap);
			}
			if (StringUtils.isNotBlank(bean.getMode()) && ("bonus".equalsIgnoreCase(bean.getMode()) ||  "bonusView".equalsIgnoreCase(bean.getMode()))  && bean.getPremiumOC() == null) {
				bean.setExchRatePrem(exhRate);
			if (list.size() == 1) {
				if (bean.getCurrencyName().equalsIgnoreCase(currency.trim())) {
					bean.setGridShow("N");
				} else {
					bean.setGridShow("Y");
				}
			} else if (list.size() > 1) {
				bean.setGridShow("Y");
			}else if(list1.size() == 1){
				list=list1;
				bean.setGridShow("N");
			}
		}else if(list.size()==0){
			list=list1;
		}
			bean.setCurrencyShortName(curName);
			bean.setPreCurrencylist(curId);

        }catch(Exception e){
            LOGGER.debug("Exception @ {" + e + "}");
            e.printStackTrace();
        }

        return list;
}

public void GetFieldValues(FaculPremiumBean bean) {
	Object args[]=null;
	Object prargs[]=null;
	Object orargs[]=null;
	List<Map<String,Object>> comList = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> premList = new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> OSList = new ArrayList<Map<String,Object>>();
	List<String> claimout =new ArrayList<String>();
	List<String> claimRatio =new ArrayList<String>();
	List<String> total = new ArrayList<String>();
	List<String> pretotal = new ArrayList<String>();
	List<String> paiddate = new ArrayList<String>();
	List<String> bonusOC = new ArrayList<String>();
	List<String> bonusAdj = new ArrayList<String>();
	List<String> claimamt =new ArrayList<String>();
	List<String> premiumamt =new ArrayList<String>();
	List<Map<String,Object>>finalTest=new ArrayList<Map<String,Object>>();
	double val =0;
	double curval =0;
	double bonus =0;
	double paid =0;
	double premiumPaid=0;
	double commission=0;
	String paidamount = "";
	String premAmt ="";
	String curId=""   ;
	String precurId="";  
	String query ="";
	String prquery ="";
	String quotaShare = "";
	String mdpremium ="";
	String adjustmentPremium = "";
	String portfolioin ="";
	String portfolioout = "";
	String comqs = "";
	String comsu = "";
	String claimoutSt = "";
	//String claimoutSt1 = "";
	String bonusres="";
	String OSQuery="";
	
	DecimalFormat myFormatter = new DecimalFormat("#####.##");
	try{
		if (bean.getPreCurrencylist().size() > 0) {
			 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
			 	total.add("0.00") ;
				 paiddate.add("0.00");
			 	 pretotal.add("0.00");
				 claimout.add("0.00");
				 claimamt.add("0.00");
				 premiumamt.add("0.00");
			 }
		}
			query = getQuery("GET_BONUS_PAID_AMOUT");
			args=new Object[5];
			args[0] = bean.getContNo();
			args[1] = bean.getLayerno();
			args[2] = bean.getBranchCode();
			args[3] = bean.getTransaction();
			args[4] = bean.getProductId();

			prquery = getQuery("GET_BONUS_PREMIUM_DETAILS");
			prargs=new Object[7];
			//prargs[0] = bean.getTransaction();
			//prargs[1] = bean.getContNo();
			//prargs[2] = bean.getBranchCode();
			prargs[0] = bean.getContNo();
			prargs[1] = bean.getLayerno();
			prargs[2] = bean.getBranchCode();
			prargs[3] = bean.getContNo();
			prargs[4] = bean.getLayerno();
			prargs[5] = bean.getTransaction();
			prargs[6] = bean.getProductId();
			
			OSQuery = getQuery("GET_BONUS_RESERVED_CLAIM_PAID");
			orargs=new Object[6];
			orargs[0] = bean.getContNo();
			orargs[1] = bean.getLayerno();
			orargs[2] = bean.getTransaction();
			orargs[3] = bean.getContNo();
			orargs[4] = bean.getLayerno();
			orargs[5] = bean.getTransaction();
			if(StringUtils.isNotBlank(bean.getTransactionNo())){
			prquery="SELECT * FROM ("+prquery+")where TRANSACTION_NO not in('"+bean.getTransactionNo()+"')" ;
			}
		comList = this.mytemplate.queryForList(query,args);
		premList = this.mytemplate.queryForList(prquery,prargs);
		OSList = this.mytemplate.queryForList(OSQuery,orargs);
		
		curval+=Double.parseDouble(StringUtils.isBlank(bean.getPremiumQuotaShare())?"0":bean.getPremiumQuotaShare().replace(",", ""))+
		Double.parseDouble(StringUtils.isBlank(bean.getMd_premium())?"0":bean.getMd_premium().replace(",", ""))+
		Double.parseDouble(StringUtils.isBlank(bean.getAdjustment_premium())?"0":bean.getAdjustment_premium().replace(",", ""));
		if(premList.size()>0){
			for (int i = 0; i < premList.size(); i++) {
					Map<String, Object> map = premList.get(i);
					finalTest.add(premList.get(i));
					quotaShare = map.get("PREMIUM_QUOTASHARE_OC") == null ? "0.00" : map.get("PREMIUM_QUOTASHARE_OC").toString();
					mdpremium = map.get("M_DPREMIUM_OC") == null ? "0.00" : map.get("M_DPREMIUM_OC").toString();
					adjustmentPremium = map.get("ADJUSTMENT_PREMIUM_OC") == null ? "0.00" : map.get("ADJUSTMENT_PREMIUM_OC").toString();
					//premAmt = map.get("CLAIM_PAID_AMOUNT")==null?"0.00":map.get("CLAIM_PAID_AMOUNT").toString();
					comqs=map.get("BONUS_OC")==null?"0.00":map.get("BONUS_OC").toString();
					quotaShare = quotaShare.replaceAll(",", "");
					portfolioin = portfolioin.replaceAll(",", "");
					portfolioout = portfolioout.replaceAll(",", "");
					comqs = comqs.replaceAll(",", "");
					comsu = comsu.replaceAll(",", "");
					//claimoutSt1 = claimoutSt1.replaceAll(",", "");
					//premAmt = premAmt.replaceAll(",", "");
					curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					if (precurId!=null && precurId.equals(curId)) {
						val += Double.parseDouble(quotaShare) + Double.parseDouble(mdpremium) + Double.parseDouble(adjustmentPremium) ;
						bonus += Double.parseDouble(comqs);
					}
					else{
						val=0;
						bonus=0;
						val += Double.parseDouble(quotaShare) + Double.parseDouble(mdpremium) + Double.parseDouble(adjustmentPremium) ;
						bonus += Double.parseDouble(comqs);
					}
					if (bean.getPreCurrencylist().size() > 0) {
					 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
						 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
							if (precurId.equals(bean.getPreCurrencylist().get(j))) {
								
								paiddate.set(j,DropDownControllor.formatter(Double.toString(bonus)));
								 pretotal.set(j,DropDownControllor.formatter(Double.toString(val+curval)));
								 //total.set(j,DropDownControllor.formatter(Double.toString(premAmt)));
								// claimout.set(j,DropDownControllor.formatter(claimoutSt1));
								// premiumamt.set(j,DropDownControllor.formatter(premAmt));
							}
						}
					}
				}
			}else{
				if ( bean.getPreCurrencylist().size() > 0) {
					for (int j = 0; j <  bean.getPreCurrencylist().size(); j++) {
						 pretotal.set(j,DropDownControllor.formatter(Double.toString(val+curval)));
					}
				}
			}
		if(OSList.size()>0){
			for (int i = 0; i < OSList.size(); i++) {
					Map<String, Object> map = OSList.get(i);
					claimoutSt = map.get("OSLR").toString() == null ? "0.00" : map.get("OSLR").toString();
					curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
					claimoutSt = claimoutSt.replaceAll(",", "");
					if (precurId!=null && precurId.equals(curId)) {
							commission += Double.parseDouble(claimoutSt) ;
					}
					else{
						commission=0;
						commission += Double.parseDouble(claimoutSt);
					}
					if (bean.getPreCurrencylist().size() > 0) {
						 for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
							 	precurId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
								if (precurId.equals(bean.getPreCurrencylist().get(j))) {
									claimout.set(j,DropDownControllor.formatter(Double.toString(commission)));
								}
							}
						}
			}
		}
		if(comList.size()>0) {
			for (int i = 0; i < comList.size(); i++) {
				
				Map<String, Object> map = comList.get(i);
				finalTest.add(comList.get(i));
				curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
				
				if (precurId!=null && precurId.equals(curId)) {
					paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
					paid += Double.parseDouble(paidamount.replaceAll(",",""));
				}else{
					paid =0;
					paidamount = map.get("PAID_AMOUNT_OC").toString() == null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
					paid += Double.parseDouble(paidamount.replaceAll(",",""));
				}
				precurId = curId;
				if (bean.getPreCurrencylist().size() > 0) {
					for (int j = 0; j < bean.getPreCurrencylist().size(); j++) {
						curId = map.get("CURRENCY_ID").toString() == null ? "" : map.get("CURRENCY_ID").toString();
						if (curId.equals(bean.getPreCurrencylist().get(j))) {
							paid = paid+Double.parseDouble(premiumamt.get(j).replaceAll(",",""));
							total.set(j,DropDownControllor.formatter(Double.toString(paid)));
						}
					}
				}
			}
		}
		else{
			if ( bean.getPreCurrencylist().size() > 0) {
				for (int j = 0; j <  bean.getPreCurrencylist().size(); j++) {
					total.set(j,DropDownControllor.formatter(premiumamt.get(j).replaceAll(",","")));
				}
			}
		}
		//if(bean.getPremiumOC()==null) {
			for (int i = 0; i < bean.getPreCurrencylist().size(); i++) {
				double ans = 0.00;
				double slideresult = 0.00;
				String prtotal = pretotal.get(i).replaceAll(",", "");
				String claimou = claimout.get(i).replaceAll(",", "");
				String tot = total.get(i).replaceAll(",", "");
				if (Double.parseDouble(prtotal) > 0) {
					ans = (Double.parseDouble(tot) + Double.parseDouble(claimou)) / Double.parseDouble(prtotal) * 100;
				}
				claimRatio.add(DropDownControllor.formatter(Double.toString(ans)));
			//if(ans>0) {
				bonusres = getBonusValue(bean, ans);
				bonusres = bonusres.replaceAll(",", "");
				if (Double.parseDouble(prtotal) > 0) {
					slideresult = Double.parseDouble(prtotal) * Double.parseDouble(bonusres) / 100;
				}
			//}
				bonusOC.add(DropDownControllor.formatter(Double.toString(slideresult)));
				String sliderest = bonusOC.get(i).replaceAll(",", "");
				String paiddat = paiddate.get(i).replaceAll(",", "");
				double slideans = Double.parseDouble(sliderest) - Double.parseDouble(paiddat);
				bonusAdj.add(DropDownControllor.formatter(Double.toString(slideans)));
			}
			
			bean.setPremiumOC(pretotal);
			bean.setClaimPaidOC(total);
			bean.setClaimRatioOC(claimRatio);
			bean.setClaimOutStandingOC(claimout);
			bean.setBonusOC(bonusOC);
			bean.setBonusPaidOCTillDate(paiddate);
			bean.setBonusAdjOC(bonusAdj);
		//}
		    bean.setPremiumFinallist2(finalTest);
		    if(StringUtils.isBlank(bean.getFlag())){
		    	bean.setManualPremiumOC(pretotal);
				bean.setManualclaimPaidOC(total);
				bean.setManualclaimRatioOC(claimRatio);
				bean.setManualclaimOutStandingOC(claimout);
		    }

	}catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
}

public String getBonusValue(FaculPremiumBean bean, double ans) {
	String slideres ="0.00";
	try{
		if(StringUtils.isNotBlank(bean.getBonusId()) && "LCB".equals(bean.getBonusId())){
		String slidequery = getQuery("GET_SILDE_VALUE_DEPT") ;
		Object slideArgs[] = new Object[7];
		slideArgs[0] = bean.getContNo();
		slideArgs[1] = bean.getProductId();
		slideArgs[2] = bean.getBranchCode();
		slideArgs[3] = ans;
		slideArgs[4] = bean.getBonusId();
		slideArgs[5] = bean.getDepartmentId();
		slideArgs[6] = bean.getProposal_No();
		 slideres = this.mytemplate.queryForObject(slidequery, slideArgs, String.class);
		}else if(StringUtils.isNotBlank(bean.getBonusId()) && "NCB".equals(bean.getBonusId()) && ans==0.00){
			String slidequery = getQuery("GET_BONUS_VALUE") ;
			Object slideArgs[] = new Object[2];
			slideArgs[0] = bean.getContNo();
			slideArgs[1] = bean.getLayerno();
			
			 slideres = this.mytemplate.queryForObject(slidequery, slideArgs, String.class);
		}
	}
	catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
	return slideres;
}

public void addFieldValue(FaculPremiumBean bean) {
	String name="";
	String premium="";
	String cliam="";
	String cliamAMT="";
	String paid ="";
	String acqCost="";
	String claimout="";
	String bonusTill="";
	String total="";
	String proComm="";
	String val1[];
	boolean flag=true;
	String[] val=bean.getBonusExchangeRate().split(",");
	List<Map<String,Object>> test= new ArrayList<Map<String,Object>>();
try{
		Map<String,Object> list = new HashMap<String,Object>();
			if (bean.getPremiumFinallist2().size() > 0) {
				for (int i = 0; i < bean.getPremiumFinallist2().size(); i++) {
					Map<String, Object> map = bean.getPremiumFinallist2().get(i);
					flag = true;
					name = map.get("SHORT_NAME") == null ? "" : map.get("SHORT_NAME").toString();
					premium = map.get("PremiumOC")== null ? "0.00" : map.get("PremiumOC").toString();
					cliam = map.get("PAID_AMOUNT_OC")== null ? "0.00" : map.get("PAID_AMOUNT_OC").toString();
					claimout = map.get("OSLR") == null ? "0.00" : map.get("OSLR").toString();
					bonusTill = map.get("BONUS_OC") == null ? "0.00" : map.get("BONUS_OC").toString();
					
					total = DropDownControllor.formatter(cliam);
					if (bean.getCurrencyShortName().size() > 0) {
						for (int j = 0; j < bean.getCurrencyShortName().size(); j++) {
							if(name.equalsIgnoreCase(bean.getCurrencyShortName().get(j))){
								//bean.getExchRatePrem().get(i);
								val1 = val[j].split("~");
								if(val1[0].equalsIgnoreCase(name) && flag){
									map.put("EXCHG_RATE",DropDownControllor.formattereight(val1[1]));
									premium =DropDownControllor.formatter(Double.toString(Double.parseDouble(premium) * Double.parseDouble(val1[1])));
									cliam =DropDownControllor.formatter(Double.toString(Double.parseDouble(cliam) * Double.parseDouble(val1[1])));
									claimout =DropDownControllor.formatter(Double.toString(Double.parseDouble(claimout) * Double.parseDouble(val1[1])));
									bonusTill=DropDownControllor.formatter(Double.toString(Double.parseDouble(bonusTill) * Double.parseDouble(val1[1])));
									map.put("PREMIUM_DC",premium);
									map.put("CLAIM_DC",cliam);
									map.put("CLAIMOUT_DC",claimout);
									map.put("BONUS_DC",bonusTill);
									map.put("CLAIM_PREMIUM_VAL",total);
									flag = false;
								}

							}
						}
						}
					test.add(map);
		}
	}
	bean.setPremiumFinallist2(test);
}
catch(Exception e){
	e.printStackTrace();
	}
	
}
public void insertBonus(FaculPremiumBean bean) {
	try{
		String qry=getQuery("BONUS_MAX_AMEND_ID");
		Object arg[]=new Object[6];
		arg[0] = bean.getTransactionNo();
		arg[1] = bean.getTransaction();
		arg[2] = bean.getBranchCode();
		arg[3] = bean.getProductId();
		arg[4] = bean.getContNo();
		arg[5] = bean.getLayerno();
		String amendId = this.mytemplate.queryForObject(qry,arg,String.class);
		
		if(bean.getPremiumOC()!=null){
			String query=getQuery("BONUS_CAL_INSERT");
			Object args[]=new Object[17];
		for(int i=0;i<bean.getExchRatePrem().size();i++){
			args[0]=bean.getContNo();
			args[1]=bean.getLayerno();
			args[2] = bean.getProductId();
			args[3]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
			args[4]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
			args[5]=bean.getPremiumOC().get(i).replaceAll(",", "");
			args[6]=bean.getClaimPaidOC().get(i).replaceAll(",", "");
			args[7]=bean.getClaimOutStandingOC().get(i).replaceAll(",", "");
			args[8]=bean.getClaimRatioOC().get(i).replaceAll(",", "");
			args[9]=bean.getBonusOC().get(i).replaceAll(",", "");
			args[10]=bean.getBonusPaidOCTillDate().get(i).replaceAll(",", "");
			args[11]=bean.getBonusAdjOC().get(i).replaceAll(",", "");
			args[12] = bean.getPreCurrencylist().get(i).replaceAll(",","");
			args[13]=bean.getExchRatePrem().get(i).replaceAll(",", "");
			args[14] = amendId;
			args[15] = bean.getLoginId();
			args[16]=bean.getBranchCode();
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
		}
		if(bean.getConPremiumOC()!=null&&StringUtils.isNotBlank(bean.getConPremiumOC())){
			args[0]=bean.getContNo();
			args[1]=bean.getLayerno();
			args[2] = bean.getProductId();
			args[3]=StringUtils.isEmpty(bean.getTransactionNo()) ? "" : bean.getTransactionNo();
			args[4]=StringUtils.isEmpty(bean.getTransaction()) ? "" : bean.getTransaction();
			args[5]=bean.getConPremiumOC().replaceAll(",", "");
			args[6]=bean.getConClaimPaidOC().replaceAll(",", "");
			args[7]=bean.getConClaimOutStandingOC().replaceAll(",", "");
			args[8]=bean.getConClaimRatioOC().replaceAll(",", "");
			args[9]=bean.getConbonusOC().replaceAll(",", "");
			args[10]=bean.getConbonusPaidOC().replaceAll(",", "");
			args[11]=bean.getConbonusAdjOC().replaceAll(",", "");
			args[12] ="";
			args[13]="";
			args[14] = amendId;
			args[15] = bean.getLoginId();
			args[16]=bean.getBranchCode();
			LOGGER.info("Query==>"+query);
			LOGGER.info("obj==>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
		}
		}
		
	}catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}

}

public void PremBonusEditFieldValue(FaculPremiumBean bean, String type) {
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> slide=new ArrayList<Map<String,Object>>();
	String exchangeRate = "0.00";
	List<String> preOc=new ArrayList<String>();
	List<String> claimpaidOc=new ArrayList<String>();
	List<String> claimratioOc=new ArrayList<String>();
	List<String> claimoutOc=new ArrayList<String>();
	List<String> bonusOc=new ArrayList<String>();
	List<String> bonusadjOc=new ArrayList<String>();
	List<String> compaidOc=new ArrayList<String>();
	List<String> ExOc=new ArrayList<String>();
	
	List<String> currId=new ArrayList<String>();
	List<String> currName=new ArrayList<String>();
	List<String> exhRate=new ArrayList<String>();
	try {
		String query = getQuery("SELECT_BONUS_FIELD_VALUES");
		Object args[] = new Object[6];
		args[0] = bean.getTransactionNo();
		args[1] = bean.getTransaction();
		args[2] = bean.getBranchCode();
		args[3] = bean.getProductId();
		args[4] = bean.getContNo();
		args[5] = bean.getLayerno();
		LOGGER.info("Query==>"+query);
		LOGGER.info("obj==>"+StringUtils.join(args, ","));
		
		list = this.mytemplate.queryForList(query, args);
		if(list!=null && list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			exchangeRate = map.get("EXCHANGE_RATE")==null ? "0.00" : map.get("EXCHANGE_RATE").toString();
			currId.add(map.get("CURRENCY_ID") == null ? "0.00" : map.get("CURRENCY_ID").toString());
			currName.add(map.get("SHORT_NAME") == null ? "0.00" : map.get("SHORT_NAME").toString());
			if(exchangeRate!="0.00"){
			ExOc.add(exchangeRate);
			slide.add(list.get(i));
			}
			if("bonus".equalsIgnoreCase(type)){
			if(exchangeRate=="0.00"){
				bean.setGridShow("Y");
				bean.setConPremiumOC(map.get("PREMIUM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREMIUM_OC").toString()));
				bean.setConClaimPaidOC(  map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
				bean.setConClaimRatioOC(map.get("CLAIM_RATIO_OC")== null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
				bean.setConClaimOutStandingOC(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
				bean.setConbonusOC(map.get("BONUS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_OC").toString()));
				bean.setConbonusAdjOC(map.get("BONUS_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_ADJ_OC").toString()));
				bean.setConbonusPaidOC(map.get("BONUS_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_PAID_TIL_DATE_OC").toString()));
			}else{
			preOc.add(map.get("PREMIUM_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("PREMIUM_OC").toString()));
			claimpaidOc.add(map.get("CLAIM_PAID_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_PAID_OC").toString()));
			claimratioOc.add(map.get("CLAIM_RATIO_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_RATIO_OC").toString()));
			claimoutOc.add(map.get("CLAIM_OS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("CLAIM_OS_OC").toString()));
			bonusOc.add(map.get("BONUS_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_OC").toString()));
			bonusadjOc.add(map.get("BONUS_ADJ_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_ADJ_OC").toString()));
			compaidOc.add(map.get("BONUS_PAID_TIL_DATE_OC") == null ? "0.00" : DropDownControllor.formatter(map.get("BONUS_PAID_TIL_DATE_OC").toString()));
			
			}
			}
			
		}
		bean.setClaimRatioOC(claimratioOc);
		bean.setPremiumOC(preOc);
		bean.setClaimPaidOC(claimpaidOc);
		bean.setBonusOC(bonusOc);
		bean.setBonusAdjOC(bonusadjOc);
		bean.setClaimOutStandingOC(claimoutOc);
		bean.setBonusPaidOCTillDate(compaidOc);
		bean.setPortfolioPremium(preOc);
		bean.setBonusCommission(slide);
		}
		bean.setCurrencyShortName(currName);
		bean.setExchRatePrem(ExOc);
		bean.setPreCurrencylist(currId);
	}
	catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
	
}

public String getDepartmentId(FaculPremiumBean bean) {
	String deptId = "";
	try{
		String query = "SELECT DEPT_ID,PROPOSAL_NO FROM POSITION_MASTER M WHERE M.CONTRACT_NO =? AND M.PRODUCT_ID=?  AND M.AMEND_ID=(SELECT MAX(AMEND_ID) FROM POSITION_MASTER P WHERE P.PROPOSAL_NO=M.PROPOSAL_NO)";
		Object args[] = new Object[2];
		args[0] = bean.getContNo();
		args[1] = bean.getProductId();
		List<Map<String,Object>>list=this.mytemplate.queryForList(query,args);
		if(list!=null &&list.size()>0){
			Map<String,Object>map=list.get(0);
			deptId=map.get("DEPT_ID")==null?"":map.get("DEPT_ID").toString();
			bean.setProposal_No(map.get("PROPOSAL_NO")==null?"":map.get("PROPOSAL_NO").toString());
		}
	}catch(Exception e){
		LOGGER.debug("Exception @ {" + e + "}");
		e.printStackTrace();
	}
	return deptId;
}

public List<FaculPremiumBean> getPremiumTempList(FaculPremiumBean beanObj) {
	List<FaculPremiumBean> finalList = new ArrayList<FaculPremiumBean>();
	LOGGER.info("PremiumDAOImpl getPremiumedList || Enter");
	String query="";
    Object[] args=null;
      	args=new String[3];
    	args[0]=beanObj.getContNo();
    	args[1]=beanObj.getBranchCode();
    	args[2]=beanObj.getContNo();
    	
	query=getQuery("FAC_PREMIUM_SELECT_PREMIUMEDLIST_TEMP");
	
	LOGGER.info("Query=>"+query);
	LOGGER.info("Args=>"+StringUtils.join(args,","));
	List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);
	for(int i=0 ; i<list.size() ; i++) {
		Map<String,Object> tempMap = (Map<String,Object>) list.get(i);
		FaculPremiumBean tempBean = new FaculPremiumBean();
		tempBean.setProposal_No(tempMap.get("RSK_PROPOSAL_NUMBER")==null?"":tempMap.get("RSK_PROPOSAL_NUMBER").toString());
		tempBean.setContNo(tempMap.get("RSK_CONTRACT_NO")==null?"":tempMap.get("RSK_CONTRACT_NO").toString());
		tempBean.setCeding_Company_Name(tempMap.get("COMPANY_NAME")==null?"":tempMap.get("COMPANY_NAME").toString());
		tempBean.setBroker(tempMap.get("BROKER_NAME")==null?"":tempMap.get("BROKER_NAME").toString());
		tempBean.setLayerno(tempMap.get("RSK_LAYER_NO")==null?"":tempMap.get("RSK_LAYER_NO").toString());
		//tempBean.setTransactionNo(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
		tempBean.setRequestNo(tempMap.get("REQUEST_NO")==null?"":tempMap.get("REQUEST_NO").toString());
		tempBean.setAccount_Period(tempMap.get("INS_DETAIL")==null?"":tempMap.get("INS_DETAIL").toString());
		tempBean.setTransDropDownVal(tempMap.get("REVERSE_TRANSACTION_NO")==null?"":tempMap.get("REVERSE_TRANSACTION_NO").toString());
		if(i==0)
			tempBean.setEndtYN("No");
		else
			tempBean.setEndtYN("Yes");
		if(Double.parseDouble(tempMap.get("ALLOC_AMT").toString())!=0)
			tempBean.setEndtYN("Yes");	
		tempBean.setInception_Date(tempMap.get("INS_DATE")==null?"":tempMap.get("INS_DATE").toString());
		tempBean.setStatementDate(tempMap.get("STATEMENT_DATE")==null?"":tempMap.get("STATEMENT_DATE").toString());
		tempBean.setMovementYN(tempMap.get("MOVEMENT_YN")==null?"":tempMap.get("MOVEMENT_YN").toString());
		//tempBean.setSettlement_Status(tempMap.get("SETTLEMENT_STATUS")==null?"":tempMap.get("SETTLEMENT_STATUS").toString());
		tempBean.setTransDate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
		if((StringUtils.isNotBlank(beanObj.getOpstartDate()))&& (StringUtils.isNotBlank(beanObj.getOpendDate()))){
			if(new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate())==0){
				tempBean.setTransOpenperiodStatus("N");
			}else
			{
				tempBean.setTransOpenperiodStatus("Y");
			}
			}
		tempBean.setProductId("1");
		/*tempBean.setAllocatedYN((String)this.mytemplate.queryForObject("select Decode (count(*),0,'Y','N') allocatedYN from TTRN_ALLOCATED_TRANSACTION where CONTRACT_NO =? and TRANSACTION_NO=? and LAYER_NO=? and TYPE='P' and STATUS='Y'",new Object[]{tempBean.getContNo(),tempBean.getTransactionNo(),tempBean.getLayerno()},String.class));
		int count=new DropDownControllor().Validatethree(beanObj.getBranchCode(), tempBean.getTransDate());
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
		
		if(count!=0 && allocationstatus ==0 && retroPrclStatus1 ==0 ){
			tempBean.setDeleteStatus("Y");
		}*/
		finalList.add(tempBean);
		
	}
	LOGGER.info("PremiumDAOImpl getPremiumedList || Exit List Size==>"+list.size());
	return finalList;
}

}
