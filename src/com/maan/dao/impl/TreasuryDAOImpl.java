package com.maan.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.PaymentBean;
import com.maan.bean.TreasuryBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.TreasuryDAO;

public class TreasuryDAOImpl extends MyJdbcTemplate implements TreasuryDAO {
	Logger logger = LogUtil.getLogger(getClass());
	private String query = "";
	private Object[] args = null;
	public List<TreasuryBean> getReceiptList(String transType,String branchCode,String type,TreasuryBean bean) {
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		try {
			query = getQuery(DBConstants.PAYMENT_SELECT_GETRETLISTS);
			args = new Object[7];
			args[0] = branchCode;
			args[1] = branchCode;
			args[2] = transType;
			args[3] = branchCode;
			/*if("reversal".equalsIgnoreCase(type)){
				args[4] = "R";
				args[8] = "R";
			}
			else {
				args[4] = "Y";
				args[8] = "Y";
			}*/
			args[4] = branchCode;
			args[5] = transType;
			args[6] = branchCode;
			if(StringUtils.isNotBlank(bean.getSearchType())){
				query+=" WHERE BRANCH_CODE="+branchCode;
            	if(StringUtils.isNotBlank(bean.getPaymentNoSearch())){
            		query +=" AND PAYMENT_RECEIPT_NO like '%"+bean.getPaymentNoSearch()+"%'";
            	}
            	if(StringUtils.isNotBlank(bean.getBrokerNameSearch())){
            		query +=" AND UPPER(BROKER) like UPPER('%"+bean.getBrokerNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getCompanyNameSearch())){
            		query +=" AND UPPER(COMPANY_NAME) like UPPER('%"+bean.getCompanyNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getRemarksSearch())){
            		query +=" AND UPPER(REMARKS) like UPPER('%"+bean.getRemarksSearch()+"%')";
            	}
            	
			}else{
				bean.setPaymentNoSearch("");
				bean.setBrokerNameSearch("");
				bean.setCompanyNameSearch("");
				bean.setRemarksSearch("");
			}
			query +=" ORDER BY  PAYMENT_RECEIPT_NO DESC";
			if(StringUtils.isBlank(bean.getFullSearch()) && StringUtils.isBlank(bean.getSearchType())){
				query ="select * from ( "+query+" )where  rownum<=100";
        	}
			/*if(bean.getSearchBy()!=null){
				if("1".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where PAYMENT_RECEIPT_NO like '"+bean.getSearchValue()+"'";
				}else if("2".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where BROKER like '%"+bean.getSearchValue()+"%'";
				}else if("3".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where COMPANY_NAME like '%"+bean.getSearchValue()+"%'";
				}
			}*/
			//query +=" ORDER BY  PAYMENT_RECEIPT_NO DESC";
			logger.info("Query=>" + query);
			logger.info("Args==> " + StringUtils.join(args,", "));
			int count = 0;
			List<Map<String,Object>> list = this.mytemplate.queryForList(query, args);
			for(int i = 0; i < list.size(); i++ ) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> resMap = list.get(i);
				tempBean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				tempBean.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				tempBean.setName(resMap.get("PAYMENT_RESPONSE")==null?"":resMap.get("PAYMENT_RESPONSE").toString());
				tempBean.setPayamount(resMap.get("PAID_AMT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMT").toString()));
				tempBean.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				tempBean.setSerial_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
				tempBean.setRemarks(resMap.get("REMARKS")==null?"":resMap.get("REMARKS").toString());
				tempBean.setTr_date(resMap.get("TRANS_DATE")==null?"":resMap.get("TRANS_DATE").toString()); 
				tempBean.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
				count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new Object[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"Y"});
				/*if(count>0) {
					tempBean.setEditShowStatus("No");
				}
				else {
					tempBean.setEditShowStatus("Yes");                	  
				}*/
				if(count!=0) {
					tempBean.setEditShowStatus("No");
				}
				else {
					tempBean.setEditShowStatus("Yes");                	  
				}
				count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new Object[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"R"});
				/*if(count>0)
				{
					tempBean.setReversedShowStatus("Yes");
				}else 
				{
					tempBean.setReversedShowStatus("No");                	  
				}*/
				if(count!=0)
				{
					tempBean.setReversedShowStatus("Yes");
					//tempBean.setEditShowStatus("No");
				}else 
				{
					tempBean.setReversedShowStatus("No");
					//tempBean.setEditShowStatus("Yes");
				}
				 if(StringUtils.isNotBlank(bean.getOpstartDate())&& StringUtils.isNotBlank(bean.getOpendDate()) && StringUtils.isNotBlank(tempBean.getTr_date())){
						if(new DropDownControllor().Validatethree(branchCode, tempBean.getTr_date())==0){
							tempBean.setRecpayOpenYN("Yes");
						}
					}
				 	String query1=getQuery(DBConstants.PAYMENT_AMOUNT_DETAILS);
					args = new Object[2];
					args[0] = branchCode;
					args[1] = tempBean.getPay_rec_no();
					List<Map<String,Object>> curList = this.mytemplate.queryForList(query1,args);
					double Total=0.0;
					for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						Total=Total+Double.parseDouble(inResMap.get("AMOUNT").toString());
					}for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						if(Total!=0) {
							tempBean.setAllocationStatus("Partial");
							tempBean.setType("PA");						}
						else if(Total==0) {
							tempBean.setAllocationStatus("Full");
							tempBean.setType("FA");							
						}
					}
					/*for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						if(Double.parseDouble(inResMap.get("AMOUNT").toString())!=0) {
							tempBean.setAllocationStatus("Partial");
							tempBean.setType("PA");
							
						}
						else if( Double.parseDouble(inResMap.get("AMOUNT").toString())==0) {
							tempBean.setAllocationStatus("Full");
							tempBean.setType("FA");
						}
					}*/
				finalList.add(tempBean);
			}
		} 
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}

	public void getReceiptEdit(TreasuryBean bean, String branchCode) {
		try {		
			query=getQuery(DBConstants.PAYMENT_SELECT_GETRETDTLS);
			args = new Object[2];
			args[0] = bean.getSerial_no();
			args[1] = branchCode;
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args,","));
			Map<String,Object> resMap = this.mytemplate.queryForMap(query,args);
			if(resMap.size()>0) {
				bean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				bean.setCedingCo(resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString());
				bean.setBroker(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				bean.setReceiptBankId(resMap.get("RECEIPT_BANK")==null?"":resMap.get("RECEIPT_BANK").toString()); 
				bean.setTr_date(resMap.get("TRANS_DATE")==null?"":resMap.get("TRANS_DATE").toString());
				bean.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
				bean.setCurrency(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
				bean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
				bean.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
				bean.setTransactionTypeTest(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
				bean.setBankCharges(resMap.get("BANK_CHARGES")==null?"":resMap.get("BANK_CHARGES").toString());
				bean.setNetAmt(resMap.get("NET_AMT")==null?"":resMap.get("NET_AMT").toString());
				bean.setAmend_date(resMap.get("AMENDMENT_DATE")==null?"":resMap.get("AMENDMENT_DATE").toString());
				bean.setRemarks(resMap.get("REMARKS")==null?"":resMap.get("REMARKS").toString());
				bean.setBankchargeDC(resMap.get("BankDC")==null?"":resMap.get("BankDC").toString());
				bean.setNetamtDC(resMap.get("NET_AMTDC")==null?"":resMap.get("NET_AMTDC").toString());
				bean.setWithHoldingTaxOC(resMap.get("WH_TAX")==null?"":resMap.get("WH_TAX").toString());
				bean.setPremiumLavy(resMap.get("PREMIUM_LAVY") == null ? "" : resMap.get("PREMIUM_LAVY").toString());
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
	}

	public void getReceiptGeneration(TreasuryBean bean, String branchCode) {
		try {
			if("63".equals(bean.getBroker())) {
				query=getQuery(DBConstants.PAYMENT_SELECT_GETDIRBRODTLS);
				args=new String[5];
				args[0]=branchCode;
				args[1]=branchCode;
				args[2]=branchCode;
				args[3]=bean.getSerial_no();
				args[4]=branchCode;
			}
			else {
				query=getQuery(DBConstants.PAYMENT_SELECT_GETBRODTLS);
				args=new String[4];
				args[0]=branchCode;
				args[1]=branchCode;
				args[2]=bean.getSerial_no();
				args[3]=branchCode;
			}
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			//for(int i=0;i<list.size();i++) {
			if(list.size()>0) {
				Map<String,Object> resMap = list.get(0);
				bean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				bean.setBrokername(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				
				bean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				
				bean.setPaymentamount(resMap.get("PAID_AMT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMT").toString()));
				bean.setTr_date(resMap.get("TRANSDATE")==null?"":resMap.get("TRANSDATE").toString());
				bean.setSerial_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				bean.setExchangerate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
				bean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":DropDownControllor.formatter(resMap.get("EXCHANGE_RATE").toString()));
				bean.setTotalexchaamount(resMap.get("TOT_EXC_AMT")==null?"":DropDownControllor.formatter(resMap.get("TOT_EXC_AMT").toString()));
				bean.setBroker(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				bean.setDiffAmount(resMap.get("DIFF_AMT")==null?"":DropDownControllor.formatter(resMap.get("DIFF_AMT").toString()));
				bean.setConvDiffAmount(resMap.get("CONVERTED_DIFF_AMT")==null?"":DropDownControllor.formatter(resMap.get("CONVERTED_DIFF_AMT").toString()));
				bean.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
				bean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
				bean.setPay_rec_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
				bean.setBankCharges(resMap.get("BANK_CHARGES")==null?"":DropDownControllor.formatter(resMap.get("BANK_CHARGES").toString()));
				bean.setNetAmt(resMap.get("NET_AMT")==null?"":DropDownControllor.formatter(resMap.get("NET_AMT").toString()));
				bean.setReceiptBankName(resMap.get("BANK_NAME")==null?"":resMap.get("BANK_NAME").toString());
                bean.setWithHoldingTaxOC(resMap.get("WH_TAX") == null ? "" : DropDownControllor.formatter(resMap.get("WH_TAX").toString()));
                bean.setRemarks(resMap.get("REMARKS")==null?"":resMap.get("REMARKS").toString());
                bean.setPremiumLavy(resMap.get("PREMIUM_LAVY") == null ? "" : DropDownControllor.formatter(resMap.get("PREMIUM_LAVY").toString()));
            }
			//}
		}	
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
	}

	public List<TreasuryBean> getReceiptAllocate(TreasuryBean bean, String branchCode) {
		final List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		try{
			query=getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMCHKYN);
			logger.info("Query==> "+ query);
			int res=this.mytemplate.update(query);
			logger.info("Result=>"+res);

			query=getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTCHKYN);
			logger.info("Query==> "+query);
			res=this.mytemplate.update(query);
			logger.info("Result==> "+res);

			query = getQuery(DBConstants.PAYMENT_SELECT_GETRETALLODTLS);
			args = new Object[4];
			args[0] = branchCode;
			args[1] = bean.getTransType();
			args[2] = branchCode;
			args[3] = bean.getTransType();
			
			if(bean.getPay_rec_no() !=null && bean.getAllocateType()!=null){
				bean.setSearchBy("1");
				bean.setSearchValue(bean.getPay_rec_no());
				//query +=" where PAYMENT_RECEIPT_NO like '"+bean.getPay_rec_no()+"'";
			}
			if(StringUtils.isNotBlank(bean.getSearchType())){
				query+=" WHERE BRANCH_CODE="+branchCode;
            	if(StringUtils.isNotBlank(bean.getPaymentNoSearch())){
            		query +=" AND PAYMENT_RECEIPT_NO like '%"+bean.getPaymentNoSearch()+"%'";
            	}
            	if(StringUtils.isNotBlank(bean.getBrokerNameSearch())){
            		query +=" AND UPPER(BROKER) like UPPER('%"+bean.getBrokerNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getCompanyNameSearch())){
            		query +=" AND UPPER(COMPANY_NAME) like UPPER('%"+bean.getCompanyNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getRemarksSearch())){
            		query +=" AND UPPER(REMARKS) like UPPER('%"+bean.getRemarksSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getCurrencySearch())){
            		query +=" AND UPPER(CURRENCY_NAME) like UPPER('%"+bean.getCurrencySearch()+"%')";
            	}
            	
			}else{
				bean.setPaymentNoSearch("");
				bean.setBrokerNameSearch("");
				bean.setCompanyNameSearch("");
				bean.setRemarksSearch("");
				bean.setCurrencySearch("");
			}
			query += " ORDER BY PAYMENT_RECEIPT_NO DESC";
			if(StringUtils.isBlank(bean.getFullSearch()) && StringUtils.isBlank(bean.getSearchType())){
				query ="select * from ( "+query+" )where  rownum<=100";
        	}
			/*if(bean.getSearchBy()!=null){
				if("1".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where PAYMENT_RECEIPT_NO like '"+bean.getSearchValue()+"'";
				}else if("2".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where BROKER like '%"+bean.getSearchValue()+"%'";
				}else if("3".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where COMPANY_NAME like '%"+bean.getSearchValue()+"%'";
				}
			}*/
			//query += " ORDER BY PAYMENT_RECEIPT_NO DESC";
			logger.info("Query==> "+query);
			logger.info("Args==> " + StringUtils.join(args,","));

			List<Map<String,Object>> dataList = this.mytemplate.queryForList(query,args);
			if(dataList.size()>0) {
				for(int i = 0; i < dataList.size(); i++) {
					TreasuryBean tempBean =new TreasuryBean();
					Map<String,Object> resMap = dataList.get(i);
					tempBean.setSerial_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
					tempBean.setPaymentamount(resMap.get("PAID_AMT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMT").toString()));
					tempBean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
					tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
					tempBean.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
					tempBean.setCedingid(resMap.get("CEDDINGID")==null?"":resMap.get("CEDDINGID").toString());
					tempBean.setBrokerid(resMap.get("BROKERID")==null?"":resMap.get("BROKERID").toString());
					tempBean.setRemarks(resMap.get("REMARKS")==null?"":resMap.get("REMARKS").toString());
					query = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETCURRDTLS);
					args = new Object[2];
					args[0] = branchCode;
					args[1] = resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString();

					logger.info("query==> " + query);
					logger.info("Args==> " + StringUtils.join(args,","));
					List<Map<String,Object>> curList = this.mytemplate.queryForList(query,args);
					List<Map<String,Object>> allocateCurrencyList = new ArrayList<Map<String,Object>>();
					/*for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						if("PA".equalsIgnoreCase(bean.getType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())!=0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("FA".equalsIgnoreCase(bean.getType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())==0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("PA".equalsIgnoreCase(bean.getAllocateType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())!=0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("FA".equalsIgnoreCase(bean.getAllocateType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())==0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}*/
					double Total=0.0;
					for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						Total=Total+Double.parseDouble(inResMap.get("AMOUNT").toString());
					}for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = curList.get(c);
						if("PA".equalsIgnoreCase(bean.getType())&& Total!=0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("FA".equalsIgnoreCase(bean.getType())&&Total==0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("PA".equalsIgnoreCase(bean.getAllocateType())&& Total!=0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
						else if("FA".equalsIgnoreCase(bean.getAllocateType())&& Total==0) {
							inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
							allocateCurrencyList.add(inResMap);
						}
					}
					tempBean.setAllocateCurrencyList(allocateCurrencyList);
					logger.info("Result=>"+tempBean.getAllocateCurrencyList());
					if(allocateCurrencyList.size()>0) {
						finalList.add(tempBean);
					}
				}
			}
		}
		catch(Exception e) {

		}
		return finalList;
	}

	public List<TreasuryBean> reverseView(final TreasuryBean bean,String branchCode)  {
		logger.info("PaymentDAOImpl reverseView() || Enter");
		List<TreasuryBean> resList = new ArrayList<TreasuryBean>();
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPALLTRANDTLS);
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+bean.getSerial_no());
			List<Map<String,Object>> list=this.mytemplate.queryForList(selectQry,new Object[]{bean.getSerial_no()});
			logger.info("Result List Size=>"+list.size());
			for(int i = 0; i < list.size(); i++) {
				Map<String,Object> resMap = (Map<String,Object>) list.get(i);
				final TreasuryBean tempBean=new TreasuryBean();
				tempBean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
				tempBean.setAllocateddate(resMap.get("ALLOCATION_DATE")==null?"":resMap.get("ALLOCATION_DATE").toString());
				tempBean.setReversalDate(resMap.get("REVERSAL_DATE1")==null?"":resMap.get("REVERSAL_DATE1").toString());
				tempBean.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
				tempBean.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
				tempBean.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
				tempBean.setReversedAmount(resMap.get("REVERSAL_AMOUNT")==null?"":DropDownControllor.formatter(resMap.get("REVERSAL_AMOUNT").toString()));
				tempBean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
				tempBean.setStatus(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString());
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+branchCode);
				logger.info("Args[1]=>"+(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()));
				tempBean.setCurrency(this.mytemplate.queryForObject(selectQry,new String[]{branchCode,(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString())},String.class).toString());
				logger.info("Result=>"+tempBean.getCurrency());
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOAMT);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+bean.getSerial_no());
				logger.info("Args[1]=>"+(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()));
				tempBean.setAllTillDate(this.mytemplate.queryForObject(selectQry, new String[]{bean.getSerial_no(),resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()}, String.class).toString());
				logger.info("Result=>"+tempBean.getAllTillDate());
				tempBean.setPay_rec_no(bean.getPay_rec_no());
				tempBean.setBroker(bean.getBroker());
				tempBean.setCedingCo(bean.getCedingCo());
				resList.add(tempBean);
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return resList;
	}

	/*public List<TreasuryBean> getReversalInfo(TreasuryBean bean,String branchCode) {
		logger.info("PaymentDAOImpl getReversalInfo || Enter ");
		List<TreasuryBean> finalList = new ArrayList<TreasuryBean>();
		try{
			String query=getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO);
			args=new Object[0];
			if(StringUtils.isNotBlank(bean.getPay_rec_no()))
			{
				args=new Object[9];
				args[7]=bean.getPay_rec_no();
				args[8]="R";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_PAYNO)+" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}else
			{
				args=new Object[8];
				args[7]="Y";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}

			//obj[0]="RT";//("PT".equalsIgnoreCase(payform.getAllocType())?"RT":"PT");
			args[0]=("PT".equalsIgnoreCase(bean.getTransType())?"RT":"PT");
			args[1]=bean.getPaymentamount();
			args[2]=bean.getCurrencyValue();
			//args[3]=bean.getExchangerate();
			args[3]=branchCode;
			args[4]=branchCode;
			args[5]=branchCode;
			args[6]=branchCode;
			logger.info("Query==>" + query);
			logger.info("Args==> " + StringUtils.join(args,","));

			List<Map<String,Object>> list=this.mytemplate.queryForList(query,args);
			for(int i = 0; i < list.size(); i++) {
				Map<String,Object> resMap = list.get(i);
				final TreasuryBean tempBean=new TreasuryBean();
				tempBean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				tempBean.setBrokername(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				tempBean.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				tempBean.setCurrencyName(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				tempBean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
				tempBean.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
				tempBean.setHideRowCnt(Integer.toString(list.size()));
				finalList.add(tempBean);
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}*/
	public List<TreasuryBean> getReversalInfo(TreasuryBean bean,String branchCode) {
		//logger.info("PaymentDAOImpl getReversalInfo || Enter ");
		List<TreasuryBean> finalList = new ArrayList<TreasuryBean>();
		try{
			String query=getQuery("PAYMENT_SELECT_GETREVERSALINFO_TREASURY");
			args=new Object[0];
			if(StringUtils.isNotBlank(bean.getPay_rec_no()))
			{
				query=getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO);
				args=new Object[9];
				args[7]=bean.getPay_rec_no();
				args[8]="R";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_PAYNO)+" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}else
			{
				args=new Object[12];
				args[7]=bean.getBroker();
				args[8]=bean.getCedingCo() == null || org.apache.commons.lang3.StringUtils.isBlank(bean.getCedingCo() ) ?"0":bean.getCedingCo();
				args[9]=bean.getCedingCo() == null || org.apache.commons.lang3.StringUtils.isBlank(bean.getCedingCo() )?"0":bean.getCedingCo();
				args[10]=bean.getCedingCo() == null || org.apache.commons.lang3.StringUtils.isBlank(bean.getCedingCo() )?"0":bean.getCedingCo();
				args[11]="Y";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}
			//obj[0]="RT";//("PT".equalsIgnoreCase(payform.getAllocType())?"RT":"PT");
			args[0]=("PT".equalsIgnoreCase(bean.getTransType())?"RT":"PT");
			args[1]=bean.getPaymentamount().replaceAll(",", "");
			if(!"VIEW".equalsIgnoreCase(bean.getFlag()) && StringUtils.isEmpty(bean.getCurrencyValue())){
				args[2]=bean.getCurrency();
			}
			else{
			args[2]=bean.getCurrencyValue();
			}
			//args[3]=bean.getExchangerate();
			args[3]=branchCode;
			args[4]=branchCode;
			args[5]=branchCode;
			args[6]=branchCode;
			
			logger.info("Query==>" + query);
			logger.info("Args==> " + StringUtils.join(args,","));
			
			List<Map<String,Object>> list=this.mytemplate.queryForList(query,args);
			if(list.size()>0) {
			for(int i = 0; i < list.size(); i++) {
				Map<String,Object> resMap = list.get(i);
				final TreasuryBean tempBean=new TreasuryBean();
				tempBean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				tempBean.setBrokername(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				tempBean.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				tempBean.setCurrencyName(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				tempBean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
				tempBean.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
				tempBean.setHideRowCnt(Integer.toString(list.size()));
				query = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETCURRDTLS);
				args = new Object[2];
				args[0] = branchCode;
				args[1] = resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString();
				logger.info("query==> " + query);
				logger.info("Args==> " + StringUtils.join(args,","));
				List<Map<String,Object>> curList = this.mytemplate.queryForList(query,args);
				List<Map<String,Object>> allocateCurrencyList = new ArrayList<Map<String,Object>>();
				for(int c = 0; c < curList.size(); c++) {
					Map<String,Object> inResMap = curList.get(c);
					if(Double.parseDouble(inResMap.get("AMOUNT").toString())!=0) {
						int count = 0;
						if(StringUtils.isBlank(bean.getPay_rec_no()) ){
						query = getQuery("GET_ALLOCATION_STATUS");
						 count=this.mytemplate.queryForInt(query,args);
						}
						if(count<=0){
						inResMap.put("CURRENCY_ID",inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString());
						allocateCurrencyList.add(inResMap);
						}
					}
				}
				tempBean.setAllocateCurrencyList(allocateCurrencyList);
				if(allocateCurrencyList.size()>0) {
					finalList.add(tempBean);
				}
			}
		}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
			e.printStackTrace();
		}
		return finalList;
	}

	public List<TreasuryBean> getReceiptViewList(TreasuryBean bean, String branchCode) {
		logger.info("PaymentDAOImpl getReceiptViewList() || Enter"); 
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		try
		{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETVIEWDTLS);
			args = new Object[2];
			args[0] = branchCode;
			args[1] = bean.getSerial_no();
			logger.info("Select Query=>"+selectQry);
			logger.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> resultList = this.mytemplate.queryForList(selectQry,args);
			for(int i=0;i<resultList.size();i++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> resMap = resultList.get(i);
				tempBean.setPay_res(resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString());
				tempBean.setSerial_no(resMap.get("RECEIPT_SL_NO")==null?"":resMap.get("RECEIPT_SL_NO").toString());
				tempBean.setPayamount(resMap.get("AMOUNT")==null?"":DropDownControllor.formatter(resMap.get("AMOUNT").toString()));
				tempBean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":DropDownControllor.formattereight(resMap.get("EXCHANGE_RATE").toString()));
				tempBean.setInceptiobdate(resMap.get("IDATE")==null?"":resMap.get("IDATE").toString());
				tempBean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				tempBean.setTotAmount(resMap.get("TOT_AMT")==null?"":DropDownControllor.formatter(resMap.get("TOT_AMT").toString()));
				tempBean.setSetExcRate(resMap.get("SETTLED_EXCRATE")==null?"":DropDownControllor.formattereight(resMap.get("SETTLED_EXCRATE").toString()));
				tempBean.setConRecCur(resMap.get("CONVERTED_RECCUR")==null?"":DropDownControllor.formatter(resMap.get("CONVERTED_RECCUR").toString()));
				tempBean.setHideRowCnt(Integer.toString(resultList.size()));
				finalList.add(tempBean);
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}

	public List<TreasuryBean> getAllocatedStatus(final TreasuryBean bean,String branchCode)  {
		List<TreasuryBean> finalList = new ArrayList<TreasuryBean>();
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETSTATUS);
			if(StringUtils.isNotBlank(bean.getAlloccurrencyid())){
				selectQry+=" AND A.CURRENCY_ID='"+bean.getAlloccurrencyid()+"'";
			}
			args = new Object[2];
			args[0] = branchCode;
			args[1] = bean.getPay_rec_no();
			logger.info("Query=>"+selectQry);
			logger.info(StringUtils.join(args,","));
			List<Map<String,Object>> list =this.mytemplate.queryForList(selectQry,args);
			for(int i=0;i<list.size();i++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> resMap = list.get(i);
				tempBean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				tempBean.setAllocated(resMap.get("ALLOCATED")==null?"":DropDownControllor.formatter(resMap.get("ALLOCATED").toString()));
				tempBean.setUtilized(resMap.get("UTILIZED")==null?"":DropDownControllor.formatter(resMap.get("UTILIZED").toString()));	
				tempBean.setNotUtilized(resMap.get("NOTUTILIZED")==null?"":DropDownControllor.formatter(resMap.get("NOTUTILIZED").toString()));	
				tempBean.setStatus(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString());
				tempBean.setPaymentDate(resMap.get("PAYMANT_DATE")==null?"":resMap.get("PAYMANT_DATE").toString());
				tempBean.setBank(resMap.get("Bank_name")==null?"":resMap.get("Bank_name").toString());
				tempBean.setCedingCompanyName(resMap.get("Ceding_company")==null?"":resMap.get("Ceding_company").toString());
				finalList.add(tempBean);
			}
			if("".equals(bean.getBrokername())){
				Object[] selectArgs = new Object[3]; 
				selectArgs[0] = branchCode;
				selectArgs[1] = bean.getBrokerid();
				selectArgs[2] = "B";
				selectQry = getQuery(DBConstants.COMMON_SELECT_GETCOMPNAME);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+branchCode);
				logger.info("Args[1]=>"+bean.getBrokerid());
				logger.info("Args[2]=>"+"B");
				bean.setBrokername(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}

	public List<TreasuryBean> allocateDetails(final TreasuryBean bean,String branchCode) {
		List<TreasuryBean> finalList = new ArrayList<TreasuryBean>();
		try{
			String selectQry = getQuery("TREASURY_PAYMENT_SELECT_GETALLOCATEDTLS");
			args = new Object[1];
			args[0] = bean.getPay_rec_no();
			logger.info("Query=>"+selectQry);

			List<Map<String,Object>> list=this.mytemplate.queryForList(selectQry,args);
			for(int i = 0; i < list.size(); i++) {
				Map<String,Object> resMap = list.get(i);
				final TreasuryBean tempBean=new TreasuryBean();
				tempBean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
				tempBean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
				tempBean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
				tempBean.setAdjustMentType(resMap.get("ADJUSTMENT_TYPE")==null?"":resMap.get("ADJUSTMENT_TYPE").toString());
				
				//payform.setAlloccurrencyid(rset.getString("CURRENCY_ID"));
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+branchCode);
				logger.info("Args[1]=>"+resMap.get("CURRENCY_ID"));
				tempBean.setCurrency(this.mytemplate.queryForObject(selectQry, new String[]{branchCode,resMap.get("CURRENCY_ID").toString()}, String.class).toString());
				logger.info("Result=>"+tempBean.getCurrency());
				logger.info("Transaction Type===>"+bean.getType());
				/*if("PT".equalsIgnoreCase(bean.getType())){
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTAMTDTLS);
				}else 
				{
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETAMTDTLS);
				}*/
				String qry=getQuery("GET_ALOCATION_TYPE");
				List<Map<String,Object>> typeList  = this.mytemplate.queryForList(qry,new Object[]{tempBean.getSerial_no()});
				for(int k=0;k<typeList.size();k++){
					tempBean.setRetroType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
				}
				
				if("RE".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString())){
					selectQry="SELECT NVL(SUM(PAID_AMOUNT),'0') FROM TTRN_ALLOCATED_TRANSACTION WHERE SNO = ? and SNO = ? AND TYPE = 'RE'";
				}
				else{
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETAMTDTLS);
				}
				Object[] selectArgs = new Object[2];
				selectArgs[0] = tempBean.getSerial_no();
				selectArgs[1] = tempBean.getSerial_no();
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+selectArgs[0]);
				logger.info("Args[1]=>"+selectArgs[1]);
				tempBean.setAllTillDate(DropDownControllor.formatter(this.mytemplate.queryForObject(selectQry, selectArgs, String.class).toString()));
				logger.info("Result=>"+tempBean.getAllTillDate());
				tempBean.setPay_rec_no(bean.getPay_rec_no());
				tempBean.setBroker(bean.getBroker());
				tempBean.setCedingCo(bean.getCedingCo());
				finalList.add(tempBean);
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}

	public Map<String,List<TreasuryBean>> allocateView(final TreasuryBean bean,String branchCode) {
		logger.info("PaymentDAOImpl allocateView() || Enter");
		Map<String,List<TreasuryBean>> viewMap=new HashMap<String,List<TreasuryBean>>();
		try{
			final List<TreasuryBean> alllist = new ArrayList<TreasuryBean>();
			final List<TreasuryBean> allocatedList = new ArrayList<TreasuryBean>();
			final List<TreasuryBean> revertedList = new ArrayList<TreasuryBean>();
			args = new Object[1];
			/*if("VIEW".equalsIgnoreCase(bean.getFlag())){
			args[0] = bean.getSerial_no();
			}
			else{
				args[0] = bean.getPay_rec_no();
			}
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSDTLS);
			if(!"".equals(bean.getSerial_no())&&!"VIEW".equalsIgnoreCase(bean.getFlag())) {
				selectQry = selectQry + " AND SNO='"+bean.getSerial_no()+"'";
			}*/
			args = new Object[1];
			if(!"".equals(bean.getPay_rec_no())) {
			args[0] = bean.getPay_rec_no();
			}else{
				args[0] = bean.getSerial_no();
			}
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSDTLS);
			if(!"".equals(bean.getSerial_no())&&!"VIEW".equalsIgnoreCase(bean.getFlag())){
				selectQry = selectQry + " AND SNO='"+bean.getSerial_no()+"'";
			}
			selectQry = selectQry + " ORDER BY SNO DESC";
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+bean.getPay_rec_no());
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			logger.info("Result Size=>"+list.size());
			//if(!"".equals(bean.getSerial_no()))
			if(!"".equals(bean.getSerial_no())&&!"VIEW".equalsIgnoreCase(bean.getFlag()))
			{	
				selectQry=getQuery(DBConstants.PAYMENT_SELECT_GETBROCEDINGIDS);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+bean.getPay_rec_no());
				logger.info("Args[1]=>"+branchCode);
				Map<String,Object> resMap = this.mytemplate.queryForMap(selectQry,new Object[]{bean.getPay_rec_no(),branchCode});
				logger.info("Map Size=>"+resMap.size());
				if(resMap.size()>0) {
					Object[] selectArgs = new Object[3]; 
					selectArgs[0] = branchCode;
					selectArgs[1] = resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString();
					selectArgs[2] = "B";
					selectQry = getQuery(DBConstants.COMMON_SELECT_GETCOMPNAME);
					logger.info("Query=>"+selectQry);
					logger.info("Args[0]=>"+branchCode);
					logger.info("Args[1]=>"+resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
					logger.info("Args[2]=>"+"B");
					bean.setBrokername((String)this.mytemplate.queryForObject(selectQry, selectArgs, String.class));
					logger.info("Result=>"+bean.getBrokername());
					if("63".equals(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString())) {
						selectArgs[0] = branchCode;
						selectArgs[1] = resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString();
						selectArgs[2] ="C";
						logger.info("Query=>"+selectQry);
						logger.info("Args[0]=>"+branchCode);
						logger.info("Args[1]=>"+ resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString());
						logger.info("Args[2]=>"+"C");
						bean.setCedingCo((String)this.mytemplate.queryForObject(selectQry, selectArgs, String.class));
						logger.info("Result=>"+bean.getCedingCo());
					}
				}
				Object[] selectArgs = new Object[2];
				if(!"".equals(bean.getPay_rec_no())) {
					selectArgs[0] = bean.getPay_rec_no();
					}else{
						selectArgs[0] = bean.getSerial_no();
					}
				selectArgs[1] = bean.getAlloccurrencyid();
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETDTLS);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]==>"+ bean.getPay_rec_no()+"\nArgs[1]==>"+bean.getAlloccurrencyid());
				bean.setAllTillDate((String)this.mytemplate.queryForObject(selectQry, selectArgs, String.class));
				logger.info("Result=>"+bean.getAllTillDate());
			}
			else {
				bean.setAllTillDate("");
			}

			if (list.size()>0) {
				TreasuryBean tempBean;
				for (int i = 0; i < list.size(); i++) {
					tempBean = new TreasuryBean();
					Map<String,Object> resMap = list.get(i);
					tempBean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
					tempBean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					tempBean.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
					tempBean.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
					tempBean.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
					tempBean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
					tempBean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					bean.setAlloccurrencyid(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					tempBean.setStatus(("R".equals(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())?"Reverted":"Allocated"));
					if("Reverted".equalsIgnoreCase(tempBean.getStatus())){
						tempBean.setPayamount(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());	
						tempBean.setAllocateddate(resMap.get("REVERSAL_DATE")==null?"":resMap.get("REVERSAL_DATE").toString());
						bean.setAllocateddate(resMap.get("REVERSAL_DATE")==null?"":resMap.get("REVERSAL_DATE").toString());
						tempBean.setCheckPC((resMap.get("REVERSAL_AMOUNT_SIGN")==null?"":resMap.get("REVERSAL_AMOUNT_SIGN").toString()));
					}
					else{
						tempBean.setPayamount(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());	
						tempBean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
						bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
						tempBean.setCheckPC((resMap.get("PAID_AMOUNT_SIGN")==null?"":resMap.get("PAID_AMOUNT_SIGN").toString()));
					}
					tempBean.setAdjustMentType((resMap.get("ADJUSTMENT_TYPE")==null?"":resMap.get("ADJUSTMENT_TYPE").toString()));
					
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
					Object[] selectArgs = new Object[2];
					selectArgs[0] = branchCode;
					selectArgs[1] = tempBean.getCurrencyValue();
					logger.info("Query=>"+selectQry);
					logger.info("Arg[0]=>"+branchCode);
					logger.info("Arg[1]=>"+ tempBean.getCurrencyValue());
					tempBean.setCurrencyName(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
					logger.info("Result=>"+tempBean.getCurrencyName());
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETEXCHRATE);
					selectArgs = new Object[2];
					if(!"".equals(bean.getPay_rec_no())) {
						selectArgs[0] = bean.getPay_rec_no();
						}else{
							selectArgs[0] = bean.getSerial_no();
						}
					selectArgs[1] = tempBean.getCurrencyValue();
					logger.info("Query=>"+selectQry);
					logger.info("Arg[0]=>"+selectArgs[0]);
					logger.info("Arg[1]=>"+selectArgs[1]);
					tempBean.setExchangerate(this.mytemplate.queryForObject(selectQry, selectArgs, String.class).toString());
					logger.info("Result=>"+tempBean.getExchangerate());
					alllist.add(tempBean);
					if("Reverted".equalsIgnoreCase(tempBean.getStatus()))
					{
						revertedList.add(tempBean);
					}else
					{
						allocatedList.add(tempBean);
					}
				}
			}
			viewMap.put("AllList", alllist);
			viewMap.put("AllocatedList", allocatedList);
			viewMap.put("RevertedList", revertedList);
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
			e.printStackTrace();
		}
		return viewMap;
	}

	public List<TreasuryBean> reverseInsert(TreasuryBean bean,String branchCode)  {
		logger.info("PaymentDAOImpl reverseInsert() || Enter");
		List<TreasuryBean> list=new ArrayList<TreasuryBean>();
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSDTLS);
			String updateQry;
			if(!"".equals(bean.getSerial_no()))
			{
				selectQry = selectQry + " AND SNO='"+bean.getSerial_no()+"'";
			}
			selectQry = selectQry + " ORDER BY SNO DESC";
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+bean.getPay_rec_no());
			List<Map<String,Object>> resList = this.mytemplate.queryForList(selectQry, new Object[]{bean.getPay_rec_no()});
			logger.info("Result List Size=>"+resList.size());
			double a=0.0,b=0.0,c=0.0;
			String curencyId="";
			if (resList.size()>0) {
				for (int i = 0; i < resList.size(); i++) {
					Map<String,Object> resMap = resList.get(i);
					curencyId=resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
					if("Y".equalsIgnoreCase(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())){
						TreasuryBean pay=new  TreasuryBean();
						pay.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
						bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
						selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
						Object[] selectArgs = new Object[2];
						selectArgs[0] = branchCode;
						selectArgs[1] = resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
						logger.info("Query=>"+selectQry);
						logger.info("Args[0]=>"+selectArgs[0]);
						logger.info("Args[1]=>"+selectArgs[1]);
						String result=this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString();
						logger.info("Result=>"+result);
						bean.setCurrency(result);
						pay.setCurrency(result);
						pay.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
						pay.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
						pay.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
						pay.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
						
						updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS);
						Object[]  updateArgs = new Object[8];
						updateArgs[0] = "R";
						updateArgs[1] = bean.getReversalDate();
						updateArgs[2] = "RE".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString())?resMap.get("RETRO_PAID_AMOUNT")==null?"":resMap.get("RETRO_PAID_AMOUNT").toString():resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
						updateArgs[3] = "0";
						updateArgs[4] = bean.getLogin_id();
						updateArgs[5] = branchCode;
						updateArgs[6] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
						updateArgs[7] = resMap.get("SNO")==null?"":resMap.get("SNO").toString();
						logger.info("Query=>"+updateQry);
						int k=0;
						for(Object obj:updateArgs){
							logger.info("Arg["+k+++"]==>"+obj);
						}
						int res=this.mytemplate.update(updateQry, updateArgs);
						logger.info("Result=>"+res);
						if("P".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
						{ 
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMDTLS);
							updateArgs = new Object[5];
							updateArgs[0] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
							updateArgs[1] = bean.getLogin_id();
							updateArgs[2] = branchCode;
							updateArgs[3] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							updateArgs[4] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+updateQry);
							k=0;
							for(Object obj:updateArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							res=this.mytemplate.update(updateQry, updateArgs);
							logger.info("Result=>"+res);
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRSKPREMDTLS);
							selectArgs = new Object[2];
							selectArgs[0] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							selectArgs[1] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+selectQry);
							k=0;
							for(Object obj:selectArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							pay.setNetdue((String)this.mytemplate.queryForObject(selectQry, selectArgs,String.class));
							logger.info("Result=>"+pay.getNetdue());
							a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
						}else if("C".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString()))
						{
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTDTLS);
							updateArgs = new Object[6];
							updateArgs[0] = "Pending";
							updateArgs[1] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
							updateArgs[2] = branchCode;
							updateArgs[3] = bean.getLogin_id();
							updateArgs[4] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							updateArgs[5] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+updateQry);
							k=0;
							for(Object obj:updateArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							res=this.mytemplate.update(updateQry, updateArgs);
							logger.info("Result=>"+res);
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETCLAIMPYMTDTLS);
							selectArgs = new Object[2];
							selectArgs[0] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							selectArgs[1] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+selectQry);
							k=0;
							for(Object obj:selectArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							pay.setPayamount((String)this.mytemplate.queryForObject(selectQry, selectArgs,String.class));
							logger.info("Result=>"+pay.getPayamount());
							b=b+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
						}else if("RE".equalsIgnoreCase(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString())){
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RETROPYMTDTLS);
							updateArgs = new Object[3];
							updateArgs[0] = resMap.get("RETRO_PAID_AMOUNT")==null?"":resMap.get("RETRO_PAID_AMOUNT").toString();
							updateArgs[1] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							updateArgs[2] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+updateQry);
							k=0;
							for(Object obj:updateArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							res=this.mytemplate.update(updateQry, updateArgs);
							logger.info("Result=>"+res);
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSOAPREMDTLS);
							selectArgs = new Object[2];
							selectArgs[0] = resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString();
							selectArgs[1] = resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString();
							logger.info("Query=>"+selectQry);
							k=0;
							for(Object obj:selectArgs){
								logger.info("Arg["+k+++"]==>"+obj);
							}
							String netresult=(String)this.mytemplate.queryForObject(selectQry, selectArgs,String.class);
							//double netresultsign=Math.signum(bean.getTransType().equals("PT")?Double.valueOf((-1)*Double.parseDouble(netresult)):Double.valueOf(Double.parseDouble(netresult)));
							double netresultsign=Math.signum(Double.valueOf(Double.parseDouble(netresult)));
							pay.setNetdue(Integer.toString(Math.abs(Integer.parseInt(netresult))));
							pay.setCheckPC("1.0".equals(String.valueOf(netresultsign))?"P":"C");
							logger.info("Result=>"+pay.getNetdue());
							//payform.getTransType().equals("PT")?Double.toString((-1)*Double.parseDouble(ary[i]+receivePayAmountMap.get(form.getTransactionno()))):Double.toString(Double.parseDouble(ary[i]+receivePayAmountMap.get(form.getTransactionno())));
							a=a+(bean.getTransType().equals("PT")?(-1)*Double.parseDouble(resMap.get("RETRO_PAID_AMOUNT")==null?"":resMap.get("RETRO_PAID_AMOUNT").toString()):Double.parseDouble(resMap.get("RETRO_PAID_AMOUNT")==null?"":resMap.get("RETRO_PAID_AMOUNT").toString()));
						}
						list.add(pay);
					}

				}
				if("RT".equalsIgnoreCase(bean.getTransType()))
				{
					c=a-b;
					//c=a+b;
				}
				else if("PT".equalsIgnoreCase(bean.getTransType()))
				{
					c=b-a;
					//c=b+a;
				}if("RE".equalsIgnoreCase(bean.getRetroType()))
				{
					c=a;
				}
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PYMTRETDTLS);
				Object[] updateArgs = new Object[5];
				updateArgs[0] = c;
				updateArgs[1] = bean.getLogin_id();
				updateArgs[2] = branchCode;
				updateArgs[3] = bean.getPay_rec_no();
				updateArgs[4] = curencyId;
				logger.info("Query=>"+updateQry);
				int k=0;
				for(Object obj:updateArgs){
					logger.info("Arg["+k+++"]==>"+obj);
				}
				int res=this.mytemplate.update(updateQry, updateArgs);
				logger.info("Result=>"+res);
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETDTLS);
				Object[] selectArgs = new Object[2];
				selectArgs[0] = bean.getPay_rec_no();
				selectArgs[1] = curencyId;
				logger.info("Query=>"+selectQry);
				k=0;
				for(Object obj:selectArgs){
					logger.info("Arg["+k+++"]==>"+obj);
				}
				bean.setAllTillDate((String)this.mytemplate.queryForObject(selectQry, selectArgs,String.class));
				logger.info("Result=>"+bean.getAllTillDate());
			}
		}
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return list;
	}

	public String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo) {
		String  currecyAmount="";
		try{
			String selectQry="";
			args=new Object[3];
			args[0]=branchCode;
			args[1]=serialNo;
			args[2]=currValu;
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_CURRECYAMT);
			logger.info("Select Query=>"+selectQry);
			logger.info("Args[0]=>"+args[0]);
			logger.info("Args[1]=>"+args[1]);
			logger.info("Args[2]=>"+args[2]);
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry, args);
			logger.info("List Size=>"+list.size());

			if(list!=null &&list.size()>0) {
				Map<String,Object> resMap = list.get(0);
				currecyAmount=resMap.get("AMOUNT").toString()+"$"+resMap.get("CURRENCY_NAME").toString();
			}
		}	
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return currecyAmount;	
	}

	public List<TreasuryBean> getTransContract(TreasuryBean bean, String branchCode,Map<String,String> receivePayAmountMap) {
		
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		
        try {
        	args=new String[13];
    		logger.info("Transaction Type=>"+bean.getTransType());
    		args[0]=branchCode;
    		args[1]=bean.getAlloccurrencyid();
    		args[2]=bean.getBrokerid();
    		if("63".equalsIgnoreCase(bean.getBrokerid()))
    			args[3]=bean.getCedingid();
    		else        	
    			args[3]=bean.getBrokerid();
    		//args[4]="%"+(bean.getSearchContractNo()).trim()+"%";
    		//args[5]="%"+bean.getSearchBusinessType()+"%";
    		args[4] = "%%";
    		args[5] ="%%";
    		args[6]=branchCode;
    		args[7]=bean.getAlloccurrencyid();
    		args[8]=bean.getBrokerid();
    		if("63".equalsIgnoreCase(bean.getBrokerid())) {
    			args[9]=bean.getCedingid();
    		}
    		else {        	
    			args[9]=bean.getBrokerid();
    		}
    		//args[10]="%"+(bean.getSearchContractNo()).trim()+"%";
    		//args[11]="%"+bean.getSearchBusinessType()+"%";
    		//args[12] = "%" + bean.getSearchType() + "%";
    		args[10]="%%";
    		args[11]="%%";
    		args[12] = "%%";
            String selectQry;
            selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTRANCONTDTLS);
            logger.info("Select Query=>" + selectQry);
            logger.info("Args==> " + StringUtils.join(args, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(selectQry, args);
            bean.setTotalreccount(String.valueOf(list.size()));

			//int val = (list.size()<Integer.valueOf(bean.getEndrownum())?list.size():Integer.valueOf(bean.getEndrownum())) - Integer.valueOf(bean.getStartrownum());
			List<String> receivePayAmounts = new ArrayList<String>();
			List<String> chkbox = new ArrayList<String>();
			List<String> previousValue = new ArrayList<String>();

			//for(int i=Integer.valueOf(bean.getStartrownum())-1,count=0 ; i<list.size() && i<Integer.valueOf(bean.getEndrownum()) ; i++,count++) {
			for(int i=0 ,count=0; i<list.size(); i++,count++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> tempMap = list.get(i);
				tempBean.setContractno(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
				tempBean.setMode(tempMap.get("LAYER_NO")==null?"":tempMap.get("LAYER_NO").toString());
				tempBean.setProductname(tempMap.get("PRODUCT_NAME")==null?"":tempMap.get("PRODUCT_NAME").toString());
				tempBean.setTransactionno(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
				tempBean.setInceptiobdate(tempMap.get("ADATE")==null?"":tempMap.get("ADATE").toString());
				tempBean.setNetdue(tempMap.get("NETDUE")==null?"":tempMap.get("NETDUE").toString());
				tempBean.setPayamount(tempMap.get("PAID_AMOUNT_OC")==null?"":tempMap.get("PAID_AMOUNT_OC").toString());
				tempBean.setAccPremium(tempMap.get("ACC_PREMIUM")==null?"":tempMap.get("ACC_PREMIUM").toString());
				tempBean.setAccClaim(tempMap.get("ACC_CLAIM")==null?"":tempMap.get("ACC_CLAIM").toString());
				tempBean.setCheckYN(tempMap.get("CHECKYN")==null?"":tempMap.get("CHECKYN").toString());
				tempBean.setCheckPC(tempMap.get("BUSINESS_TYPE")==null?"":tempMap.get("BUSINESS_TYPE").toString());
				tempBean.setCedingCompanyName(tempMap.get("CEDING_COMPANY_NAME")==null?"":tempMap.get("CEDING_COMPANY_NAME").toString());
				tempBean.setAllocType(bean.getAllocType());
				if(receivePayAmountMap.containsKey(tempBean.getTransactionno())) {
					//receivePayAmounts[count] = receivePayAmountMap.get(bean.getTransactionno());
					receivePayAmounts.add(receivePayAmountMap.get(tempBean.getTransactionno()));
					previousValue.add(receivePayAmountMap.get(tempBean.getTransactionno()));
					chkbox.add("true");
				}
				else {
					//receivePayAmounts[count] = "1";
					receivePayAmounts.add("");
					previousValue.add("");
					chkbox.add("false");
				}
				tempBean.setCount(String.valueOf(count));
				finalList.add(tempBean);
			}
			bean.setReceivePayAmounts(receivePayAmounts);
			bean.setPayAmounts(receivePayAmounts);
			bean.setChkbox(chkbox);
			bean.setPreviousValue(previousValue);
		}	
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return finalList;
	}

	public boolean insertReceiptNO(TreasuryBean bean,String branchCode) {
		logger.info("PaymentDAOImpl insertReceiptNO || Enter");
		boolean flag=false;
		String updateQry;
		try{
			if("".equalsIgnoreCase(bean.getFlag() ))
			{
				Object[] updateArgs = new Object[2];
				updateArgs[0] = bean.getProid();
				updateArgs[1] = bean.getProid();	

				Object[] selectArgs = new Object[2];
				selectArgs[0] = bean.getProid();
				selectArgs[1] = branchCode;

				String insertQry = getQuery(DBConstants.PAYMENT_INSERT_RECEIPT);
				logger.info("Query=>"+insertQry);
				Object[] obj=insertReceiptNoArg(bean,branchCode);
				int insertCount = this.mytemplate.update(insertQry,obj);
				logger.info("Result=>"+insertCount);
				if(insertCount > 0)
				{
					/*if("PT".equalsIgnoreCase(payform.getTransType()))
						{
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PAYMENTNO);
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETMAXPAYMENTNO);
						}
						else
						{
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RECEIPTNO);
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETMAXRECEIPTNO);
						}
						logger.info("Query=>"+updateQry);
						logger.info("Args[0]=>"+updateArgs[0]);
						logger.info("Args[1]=>"+updateArgs[1]);
						int res=this.mytemplate.update(updateQry, updateArgs);
						logger.info("Result=>"+res);
						logger.info("Query=>"+selectQry);
						logger.info("Args[0]=>"+selectArgs[0]);
						logger.info("Args[1]=>"+selectArgs[1]);
						String payRecNo=this.mytemplate.queryForInt(selectQry, selectArgs)+"";
						logger.info("Result=>"+payRecNo);*/
					bean.setSerial_no(obj[0].toString());
					if(insertCount>0)
						flag=true;
				}
				/*Connection con = null;
				CallableStatement cstmt = null;
				String spName= getQuery(DBConstants.PAYMENT_UPDATE_PROCEDURE);
				logger.info("SP Name==> " + spName);
				logger.info("Product Id==> "+bean.getSerial_no());
				logger.info("Branch Code==> "+branchCode );
				logger.info("Proposal No==> "+"Null" );
				//int cstmt=this.mytemplate.queryForInt(spName,new Object[]{productId,branchCode,poposalNo});
				con = this.mytemplate.getDataSource().getConnection();
				//cstmt = con.prepareCall(spName);
				cstmt = con.prepareCall("{CALL Payment_Arch(?,?,?,?)}");
				cstmt.setString(1, bean.getSerial_no() );
				cstmt.setString(2, branchCode);
				cstmt.setString(3,"null" );
				if("C".equals(bean.getCancelType())){
					cstmt.setString(3,"C" );
				}
				cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
				cstmt.setString(4,"insert" );
				cstmt.execute();
				String msg = cstmt.getString(4);
				if("SUCCESSFULL INSERTED".equals(msg)){
					flag = true;
				}*/
				flag = true;
			}
			else if("EDIT".equalsIgnoreCase(bean.getFlag()))
			{
				/*Connection con = null;
				CallableStatement cstmt = null;
				String spName= getQuery(DBConstants.PAYMENT_UPDATE_PROCEDURE);
				logger.info("SP Name==> " + spName);*/
				logger.info("Product Id==> "+bean.getSerial_no());
				logger.info("Branch Code==> "+branchCode );
				logger.info("Proposal No==> "+"Null" );
				//int cstmt=this.mytemplate.queryForInt(spName,new Object[]{productId,branchCode,poposalNo});
				/*con = this.mytemplate.getDataSource().getConnection();
				cstmt = con.prepareCall(spName);
				cstmt = con.prepareCall("{CALL Payment_Arch(?,?,?,?)}");
				cstmt.setString(1, bean.getSerial_no() );
				cstmt.setString(2, branchCode);
				cstmt.setString(3,"null" );
				if("C".equals(bean.getCancelType())){
					cstmt.setString(3,"C" );
				}
				cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
				cstmt.setString(4,"insert" );
				cstmt.execute();
				String msg = cstmt.getString(4);
				if("SUCCESSFULL INSERTED".equals(msg)){
					flag = true;
				}*/
				flag = true;
				if(!"C".equals(bean.getCancelType())){
					updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PYMTRETDATA);
					logger.info("Query=>"+updateQry);
					int updateCount = this.mytemplate.update(updateQry,insertReceiptNoArg(bean,branchCode));
					logger.info("Result=>"+updateCount);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
		}
		logger.info("PaymentDAOImpl insertReceiptNO || Exit Flag"+flag);
		return flag;
	}

	public Object[] insertReceiptNoArg(TreasuryBean payform,String branchCode)
	{
		logger.info("PaymentDAOImpl insertReceiptNoArg || Enter");
		String args[]=null;
		if("".equalsIgnoreCase(payform.getFlag()))
		{
			args =new String[20];
			//args[0]=maxNo(payform);
			//if("06".equalsIgnoreCase(branchCode)){
				args[0]=new DropDownControllor().getSequencePTRT("PT".equalsIgnoreCase(payform.getTransType()) ? "TreasuryrPR" : "TreasuryrRP","","", branchCode,"",payform.getTr_date());
			//}else
			//args[0]=new DropDownControllor().getPolicyNo("PT".equalsIgnoreCase(payform.getTransType()) ? "6" : "7", "0", branchCode);
			args[1]=payform.getBroker(); 
			args[2]=payform.getCedingCo()==null?"0":payform.getCedingCo().trim();
			args[3]=payform.getCurrency();
			args[4]=payform.getPaymentamount();
			args[5]=payform.getExrate();//payform.getName();
			args[6]=payform.getReceiptBankId();
			args[7]=payform.getProid();
			args[8]=payform.getDept_no();
			args[9]="Y";
			args[10]=payform.getTr_date();
			args[11]=payform.getTransType();
			args[12]=payform.getTransactionType();
			args[13]=branchCode;
			args[14]=payform.getBankCharges().replaceAll(",", "");
			args[15]=payform.getNetAmt().replaceAll(",", "");
			args[16]=payform.getRemarks();
			args[17]=payform.getWithHoldingTaxOC().replaceAll(",", "");
			args[18]=payform.getLogin_id();
			args[19]=payform.getPremiumLavy();
		}
		else if("EDIT".equalsIgnoreCase(payform.getFlag()))
		{
			/*args =new String[20];
			args[0]=payform.getSerial_no();
			args[1]=payform.getBroker(); 
			args[2]=payform.getCedingCo();
			args[3]=payform.getCurrency();
			args[4]=payform.getPaymentamount();
			args[5]=payform.getExrate();//payform.getName();
			args[6]=payform.getReceiptBankId();
			args[7]=payform.getProid();
			args[8]=payform.getDept_no();
			args[9]="Y";
			args[10]=payform.getTr_date();
			args[11]=payform.getTransType();
			args[12]=payform.getTransactionType();
			args[13]=payform.getBankCharges();
			args[14]=payform.getNetAmt();
			args[15]=payform.getAmend_date();
			args[16]=payform.getRemarks();
            args[17]=payform.getWithHoldingTaxOC();
			args[18]=payform.getSerial_no();
			args[19]=branchCode;*/
			args =new String[23];
			//args[0]=maxNo(payform);
			args[0]=payform.getSerial_no();
			args[1]=payform.getBroker(); 
			args[2]=payform.getCedingCo()==null?"0":payform.getCedingCo().trim();
			args[3]=payform.getCurrency();
			args[4]=payform.getPaymentamount();
			args[5]=payform.getExrate();//payform.getName();
			args[6]=payform.getReceiptBankId();
			args[7]=payform.getProid();
			args[8]=payform.getDept_no();
			args[9]="Y";
			args[10]=payform.getTr_date();
			args[11]=payform.getTransType();
			args[12]=payform.getTransactionType();
			args[13]=branchCode;
			args[14]=payform.getBankCharges().replaceAll(",", "");;
			args[15]=payform.getNetAmt().replaceAll(",", "");;
			args[16]=payform.getRemarks();
			args[17]=payform.getWithHoldingTaxOC().replaceAll(",", "");;
			args[18]=payform.getSerial_no();
			args[19]=branchCode;
			args[20]=payform.getAmend_date();
			args[21]=payform.getLogin_id();
			args[22]=payform.getPremiumLavy();
		}
		int j=0;
		for(String s:args)
			logger.info("args["+j+++"]"+s);
		final String[] copiedArray = new String[args.length];
		System.arraycopy(args, 0, copiedArray, 0, args.length);
		logger.info("PaymentDAOImpl insertReceiptNoArg || Exit");
		return copiedArray;
	}

	public void getSecondPageInfo(TreasuryBean bean, String branchCode) {
		logger.info("PaymentDAOImpl getSecondPageInfo() || Enter");
		try{
			args = new String[1];
			args[0] = bean.getSerial_no();
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSECONDPAGEDTLS);
			logger.info("Query=>"+selectQry);
			logger.info("Arg[0]=>"+args[0]);
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			logger.info("List Size=>"+list.size());
			double totAmt=0.0;
			double totConRecCur=0.0;

			List<String> cedingCompanyValList = new ArrayList<String>();
			List<String> exachangeValList = new ArrayList<String>();
			List<String> payamountValList = new ArrayList<String>();
			List<String> rowamountValList = new ArrayList<String>();
			List<String> recNoValList = new ArrayList<String>();
			List<String> setExcRateValList = new ArrayList<String>();
			List<String> conRecCurValList = new ArrayList<String>();

			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> resMap = list.get(i);
				/*bean.getRequest().setAttribute("cedingCompany"+(i+1),resMap.get("CURRENCY_ID").toString());
				bean.getRequest().setAttribute("exachange"+(i+1),resMap.get("EXCHANGE_RATE").toString());
				bean.getRequest().setAttribute("payamount"+(i+1),resMap.get("AMOUNT").toString());
				bean.getRequest().setAttribute("rowamount"+(i+1),resMap.get("TOT_AMT").toString());
				totAmt=totAmt+Double.parseDouble(resMap.get("TOT_AMT").toString());
				bean.getRequest().setAttribute("recNo"+(i+1),resMap.get("RECEIPT_NO").toString());//for flag from execute update or insert
				bean.getRequest().setAttribute("setExcRate"+(i+1),resMap.get("SETTLED_EXCRATE").toString());
				bean.getRequest().setAttribute("conRecCur"+(i+1),resMap.get("CONVERTED_RECCUR").toString());
				totConRecCur=totConRecCur+Double.parseDouble(resMap.get("CONVERTED_RECCUR").toString());*/
				////request.setAttribute("rowamount"+(i+1),request.getParameter("rowamount"+i));

				cedingCompanyValList.add(resMap.get("CURRENCY_ID").toString());
				exachangeValList.add(resMap.get("EXCHANGE_RATE")==null?"0":DropDownControllor.formattereight(resMap.get("EXCHANGE_RATE").toString()));
				payamountValList.add(resMap.get("AMOUNT")==null?"0":DropDownControllor.formatter(resMap.get("AMOUNT").toString()));
				rowamountValList.add(resMap.get("TOT_AMT")==null?"0":DropDownControllor.formatter(resMap.get("TOT_AMT").toString()));
				recNoValList.add(resMap.get("RECEIPT_NO").toString());//for flag from execute update or insert
				setExcRateValList.add(resMap.get("SETTLED_EXCRATE")==null?"0":DropDownControllor.formattereight(resMap.get("SETTLED_EXCRATE").toString()));
				conRecCurValList.add(resMap.get("CONVERTED_RECCUR")==null?"0":DropDownControllor.formatter(resMap.get("CONVERTED_RECCUR").toString()));
				
				totAmt=totAmt+Double.parseDouble(resMap.get("TOT_AMT").toString());
				totConRecCur=totConRecCur+Double.parseDouble(resMap.get("CONVERTED_RECCUR").toString());
			}
			bean.setCedingCompanyValList(cedingCompanyValList);
			bean.setExachangeValList(exachangeValList);
			bean.setPayamountValList(payamountValList);
			bean.setRowamountValList(rowamountValList);
			bean.setRecNoValList(recNoValList);
			bean.setSetExcRateValList(setExcRateValList);
			bean.setConRecCurValList(conRecCurValList);
			bean.setTottotal(String.valueOf(totAmt));
			//bean.getRequest().setAttribute("totConRecCur",totConRecCur);
			bean.setTotalConRecCur(String.valueOf(totConRecCur));
			selectQry=getQuery(DBConstants.PAYMENT_SELECT_GETDIFFAMT);
			logger.info("Select Query=>"+selectQry);
			logger.info("Args[0]=>"+bean.getSerial_no());
			logger.info("Args[1]=>"+branchCode);
			String diffAmt=this.mytemplate.queryForObject(selectQry, new String[] {bean.getSerial_no(),branchCode}, String.class).toString();
			logger.info("Result=>"+diffAmt);
			if(diffAmt!=null && diffAmt.length()>0) {
				double Amt=Double.parseDouble(diffAmt);
				if(Amt<0) {
					Amt=Amt*(-1);
					totAmt=totAmt-Amt;
					bean.setDifftype("M");
				} else {
					bean.setDifftype("B");
					totAmt=totAmt+Amt;
				}
				//bean.getRequest().setAttribute("txtDiffAmt",Amt);
				bean.setTxtDiffAmt(String.valueOf(Amt));
			} else {
				bean.setDifftype("N");
			}
			//bean.getRequest().setAttribute("txtTotalAmt",totAmt);
			bean.setTxtTotalAmt(String.valueOf(totAmt));
			if(list.size()!=0) {
				//bean.getRequest().setAttribute("HidRowCnt",list.size());
				bean.setHideRowCnt(String.valueOf(list.size()));
				List<String> tempList=new ArrayList<String>();
				if(list.size()>0) {
					for(int i=0;i<list.size();i++){
						tempList.add(String.valueOf(i));
					}
				}
				bean.setPaymentList(tempList);
			}
			else {
				bean.setHideRowCnt("3");
				List<String> tempList=new ArrayList<String>();
				for(int i=0;i<3;i++){
					tempList.add(String.valueOf(i));
				}
				bean.setPaymentList(tempList);
			}
			
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
	}
	
	public void generationInsert(TreasuryBean bean, String branchCode) {
		try{
			String updateQry;
			if(!"Treasury".equals(bean.getTransactionType())){
				//Object[] obj=new Object[10];
				int LoopCount=Integer.parseInt(bean.getHideRowCnt());
				logger.info("LoopCount=>"+LoopCount);
				for(int i=0;i<LoopCount;i++) {	
					//String curencyId=StringUtils.isEmpty(bean.getRequest().getParameter("cedingCompany"+i)) ? "0" :bean.getRequest().getParameter("cedingCompany"+i);
					//String recNo=StringUtils.isEmpty(bean.getRequest().getParameter("recNo"+i)) ? "" :bean.getRequest().getParameter("recNo"+i);
					String curencyId=StringUtils.isEmpty(bean.getCedingCompanyValList().get(i)) ? "0" :bean.getCedingCompanyValList().get(i);
					String recNo=StringUtils.isEmpty(bean.getRecNoValList().get(i)) ? "" :bean.getRecNoValList().get(i);
					logger.info("RecNo=>"+recNo);
					if(!"0".equalsIgnoreCase(curencyId)){
					if(recNo.length()>0) {
						Object[] obj=new Object[14];
						obj[0]=curencyId;
						obj[1]=StringUtils.isEmpty(bean.getPayamountValList().get(i)) ? "0" :bean.getPayamountValList().get(i).trim().replaceAll(",", "");
						obj[2]=StringUtils.isEmpty(bean.getExachangeValList().get(i)) ? "0" :bean.getExachangeValList().get(i).replaceAll(",", "");
						obj[3]=bean.getTr_date(); 
						obj[4]="Y";
						obj[5]=StringUtils.isEmpty(bean.getRowamountValList().get(i)) ? "0" :bean.getRowamountValList().get(i).replaceAll(",", "");
						obj[6]=StringUtils.isEmpty(bean.getSetExcRateValList().get(i)) ? "0" :bean.getSetExcRateValList().get(i).replaceAll(",", "");
						obj[7]=StringUtils.isEmpty(bean.getConRecCurValList().get(i)) ? "0" :bean.getConRecCurValList().get(i).replaceAll(",", "");
						obj[8]=recNo;
						obj[9]=bean.getSerial_no();
						obj[10]=recNo;
						obj[11]=bean.getSerial_no();
						obj[12]=bean.getLogin_id();
						obj[13]=branchCode;
						int j=0;
						for(Object o:obj)
							logger.info("Obj["+j+++"]=>"+o);
						int res=this.mytemplate.update(generationInsert(2),obj);
						logger.info("Result=>"+res);
					}
					else {
						Object[] obj=new Object[13];
						obj[0]=maxVal();
						obj[1]=bean.getSerial_no();  
						obj[2]=curencyId;
						obj[3]=StringUtils.isEmpty(bean.getPayamountValList().get(i)) ? "0" :bean.getPayamountValList().get(i).trim().replaceAll(",", "");
						obj[4]=StringUtils.isEmpty(bean.getExachangeValList().get(i)) ? "0" :bean.getExachangeValList().get(i).replaceAll(",", "");
						obj[5]=bean.getTr_date();
						obj[6]="Y";
						obj[7]=StringUtils.isEmpty(bean.getRowamountValList().get(i)) ? "0" :bean.getRowamountValList().get(i).replaceAll(",", "");
						obj[8]=StringUtils.isEmpty(bean.getSetExcRateValList().get(i)) ? "0":bean.getSetExcRateValList().get(i).replaceAll(",", "");
						obj[9]=StringUtils.isEmpty(bean.getConRecCurValList().get(i)) ? "0" :bean.getConRecCurValList().get(i).replaceAll(",", "");
						obj[10]=bean.getSerial_no();  
						obj[11]=bean.getLogin_id();
						obj[12]=branchCode;
						int j=0;
						for(Object o:obj)
							logger.info("Obj["+j+++"]=>"+o);
						int res=this.mytemplate.update(generationInsert(1),obj);
						logger.info("Result=>"+res);
					}
				}
				}
				//double diffAmt=Double.parseDouble(StringUtils.isEmpty(bean.getTxtDiffAmt()) ? "0" :bean.getTxtDiffAmt());
				double diffAmt=Double.parseDouble(StringUtils.isEmpty(bean.getTxtDiffPer()) ? "0" :bean.getTxtDiffPer().replaceAll(",", ""));
				double diffAmt1=Double.parseDouble(StringUtils.isEmpty(bean.getConvertedDiffAmount()) ? "0" :bean.getConvertedDiffAmount().replaceAll(",", ""));
				//double diffAmt1=Double.parseDouble(StringUtils.isEmpty(bean.getTotalConRecCur()) ? "0" :bean.getTotalConRecCur());
				/*if("M".equalsIgnoreCase(bean.getDifftype())){
					diffAmt=diffAmt*(-1);
				}*/
				Object[] obj = new Object[6];
				obj[0] = diffAmt;
				obj[1]=diffAmt1;
				obj[2]=bean.getLogin_id();
				obj[3]=branchCode;
				obj[4] = bean.getSerial_no();
				obj[5] = branchCode;
				logger.info("args[0]=>"+obj[0]);
				logger.info("args[1]=>"+obj[1]);
				logger.info("args[2]=>"+obj[2]);
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_DIFFAMT);
				logger.info("Query=>"+updateQry);	
				int res=this.mytemplate.update(updateQry, obj);
				logger.info("Result=>"+res);	
			
			}else
			{
				Object[] obj=new Object[8];
				obj[0]="R";
				obj[1]="RT";
				obj[2]=bean.getPay_rec_no();
				obj[3]=bean.getLogin_id();
				obj[4]=bean.getLogin_id();
				obj[5]=branchCode;
				obj[6]=bean.getSerial_no();
				obj[7]=branchCode;
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_REVERSALPAYMENT);
				int j=0;
				for(Object o:obj)
					logger.info("Obj["+j+++"]=>"+o);
				logger.info("Query=>"+updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>"+res);
				obj[1]="PT";
				obj[2]=bean.getSerial_no();
				obj[6]=bean.getPay_rec_no();
				j=0;
				for(Object o:obj)
					logger.info("Obj["+j+++"]=>"+o);
				logger.info("Result=>"+updateQry);
				res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>"+res);
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_REVERSALPAYMENTDETAILS);
				logger.info("Query=>"+updateQry);
				logger.info("Args[0]=>"+bean.getSerial_no());
				res=this.mytemplate.update(updateQry,new Object[]{bean.getLogin_id(),branchCode,bean.getSerial_no()});
				logger.info("Result=>"+res);
				logger.info("Args[0]=>"+bean.getPay_rec_no());
				res=this.mytemplate.update(updateQry,new Object[]{bean.getLogin_id(),branchCode,bean.getPay_rec_no()});
				logger.info("Result=>"+res);
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}
	}
	
	public String generationInsert(int flag) {
		StringBuffer query=new StringBuffer();
		if(flag==1) {
			query.append(getQuery(DBConstants.PAYMENT_INSERT_PAYRETDTLS));
		}
		else if(flag==2) {
			query.append(getQuery(DBConstants.PAYMENT_UPDATE_PAYRETDTLS));
		}
		logger.info("Query=>"+query.toString());	
		return query.toString();
	}
	
	private String maxVal() {
		String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETNEXTRETDTLSNO);
		logger.info("Query=>"+selectQry);
		String result = this.mytemplate.queryForInt(selectQry) + "";
		logger.info("Recipt Serial No=>"+result);
		return result;
	}
	
	public List<TreasuryBean> getAllTransContract(TreasuryBean payform, String branchCode) {
		List<TreasuryBean> payList = new ArrayList<TreasuryBean>();
		try {
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTRANCONTDTLS);
			logger.info("Select Query=>"+selectQry);
			
			args=new String[13];
			logger.info("Transaction Type=>"+payform.getTransType());
			args[0]=branchCode;
			args[1]=payform.getAlloccurrencyid();
			args[2]=payform.getBrokerid();
			if("63".equalsIgnoreCase(payform.getBrokerid()))
				args[3]=payform.getCedingid();
			else        	
				args[3]=payform.getBrokerid();
			args[4]="%%";
			args[5]="%%";
			args[6]=branchCode;
			args[7]=payform.getAlloccurrencyid();
			args[8]=payform.getBrokerid();
			if("63".equalsIgnoreCase(payform.getBrokerid())) {
				args[9]=payform.getCedingid();
			}
			else {        	
				args[9]=payform.getBrokerid();
			}
			args[10]="%%";
			args[11]="%%";
			args[12]="%%";
			
			
			logger.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			for(int i=0 ; i<list.size(); i++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> tempMap = list.get(i);
				tempBean.setContractno(tempMap.get("CONTRACT_NO")==null?"":tempMap.get("CONTRACT_NO").toString());
				tempBean.setMode(tempMap.get("LAYER_NO")==null?"":tempMap.get("LAYER_NO").toString());
				tempBean.setProductname(tempMap.get("PRODUCT_NAME")==null?"":tempMap.get("PRODUCT_NAME").toString());
				tempBean.setTransactionno(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
				tempBean.setInceptiobdate(tempMap.get("ADATE")==null?"":tempMap.get("ADATE").toString());
				tempBean.setNetdue(tempMap.get("NETDUE")==null?"":tempMap.get("NETDUE").toString());
				tempBean.setPayamount(tempMap.get("PAID_AMOUNT_OC")==null?"":tempMap.get("PAID_AMOUNT_OC").toString());
				tempBean.setAccPremium(tempMap.get("ACC_PREMIUM")==null?"":tempMap.get("ACC_PREMIUM").toString());
				tempBean.setAccClaim(tempMap.get("ACC_CLAIM")==null?"":tempMap.get("ACC_CLAIM").toString());
				tempBean.setCheckYN(tempMap.get("CHECKYN")==null?"":tempMap.get("CHECKYN").toString());
				tempBean.setCheckPC(tempMap.get("BUSINESS_TYPE")==null?"":tempMap.get("BUSINESS_TYPE").toString());
				tempBean.setSubClass(tempMap.get("DEPT_ID")==null?"":tempMap.get("DEPT_ID").toString());
				tempBean.setProposalNo(tempMap.get("PROPOSAL_NO")==null?"":tempMap.get("PROPOSAL_NO").toString());
				payList.add(tempBean);
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return payList;
	}
	public List<TreasuryBean> getAllRetroTransContract(TreasuryBean payform, String branchCode) {
		List<TreasuryBean> payList = new ArrayList<TreasuryBean>();
		try {
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTRANCONTDTLS_RETRO);
			logger.info("Select Query=>"+selectQry);
			
			args=new String[4];
			logger.info("Transaction Type=>"+payform.getTransType());
			args[0]=branchCode;
			args[1]=payform.getAlloccurrencyid();
			args[2]=payform.getBrokerid();
			if("63".equalsIgnoreCase(payform.getBrokerid()))
				args[3]=payform.getCedingid();
			else        	
				args[3]=payform.getBrokerid();
			//args[4]="%"+(bean.getSearchContractNo()).trim()+"%";
			//args[5]="%"+bean.getSearchBusinessType()+"%";
			
			
			logger.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(selectQry,args);
			for(int i=0 ; i<list.size(); i++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> tempMap = list.get(i);
				tempBean.setContractno(tempMap.get("RETRO_CONTRACT_NUMBER")==null?"":tempMap.get("RETRO_CONTRACT_NUMBER").toString());
				tempBean.setTransactionno(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
				tempBean.setInceptiobdate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
				tempBean.setNetdue(tempMap.get("NETDUE")==null?"":tempMap.get("NETDUE").toString());
				tempBean.setCheckPC(tempMap.get("sign")==null?"":tempMap.get("sign").toString());
				tempBean.setCedingCompanyName(tempMap.get("CEDING_COMPANY_NAME")==null?"":tempMap.get("CEDING_COMPANY_NAME").toString());
				payList.add(tempBean);
			}
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return payList;
	}
	public void getallocateTransaction(TreasuryBean payform, String branchCode, Map<String,String> receivePayAmountMap) 
	{
		logger.info("PaymentDAOImpl getallocateTransaction() || Enter");
		try{
			//List<TreasuryBean> payList=getTransContract(payform,branchCode,receivePayAmountMap);
			
			List<TreasuryBean> payList = getAllTransContract(payform, branchCode);
			String serialNo;
			String updateQry;
			Double a=0.0,b=0.0,c=0.0;
			//if("06".equalsIgnoreCase(branchCode)){
			serialNo=new DropDownControllor().getSequencePTRT("TreasuryARP","","", branchCode,"",payform.getAccountDate());
				//serialNo=new DropDownControllor().getSequence("TreasuryARP","","", branchCode,"");
			/*}else{
			updateQry=getQuery(DBConstants.PAYMENT_SELECT_GETNEXTSNO);
			logger.info("Query=>"+updateQry);
			 serialNo =(String) this.mytemplate.queryForObject(updateQry,String.class);
			logger.info("Result=>"+serialNo);
			}*/
			payform.setSerial_no(serialNo);
			
			for(int i=0;i<payList.size();i++) {
				
				TreasuryBean form= payList.get(i);
				if(receivePayAmountMap.containsKey(form.getTransactionno())) {	 
					 args=new String[17];
					 args[0]=serialNo;	
					 args[1]=form.getContractno();
					 args[2]=StringUtils.isBlank(form.getMode())?"0":form.getMode();
					 //args[2]="0";//Layer No if need than we want to change.
					 args[3]=form.getProductname();
					 args[4]=form.getTransactionno();
					 args[5]=payform.getAccountDate();
						if("P".equalsIgnoreCase(form.getCheckPC())){
						 	//args[6]=form.getAccPremium();
							args[6]= receivePayAmountMap.get(form.getTransactionno());
						 	args[7]="P";
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMALLODTLS);
						 	Object[] updateArgs = new Object[5];
							//updateArgs[0] = form.getAccPremium();
						 	updateArgs[0] = receivePayAmountMap.get(form.getTransactionno());
						 	updateArgs[1] = payform.getLogin_id();
						 	updateArgs[2] = branchCode;
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							//logger.info("Args[0]=>"+form.getAccPremium()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							logger.info("Args[0]=>"+receivePayAmountMap.get(form.getTransactionno())+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
						 	int res=this.mytemplate.update(updateQry,updateArgs);
						 	logger.info("Udate Result=>"+res);
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PREMSETSTAUS);
						 	updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = payform.getLogin_id();
						 	updateArgs[2] = branchCode;
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+updateArgs[0]+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
						 	res=this.mytemplate.update(updateQry,updateArgs);
						 	logger.info("Udate Result=>"+res);
							//a=a+Double.parseDouble(form.getAccPremium());
						 	a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
						}
						else if("C".equalsIgnoreCase(form.getCheckPC())) {
							//args[6]=form.getAccClaim();
							args[6] = receivePayAmountMap.get(form.getTransactionno());
						 	args[7]="C";
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTALLODTLS);
							Object[] updateArgs = new Object[5];
							//updateArgs[0] = form.getAccClaim();
							updateArgs[0] = receivePayAmountMap.get(form.getTransactionno());
							updateArgs[1] = branchCode;
						 	updateArgs[2] = payform.getLogin_id();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							//logger.info("Args[0]=>"+form.getAccClaim()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							logger.info("Args[0]=>"+receivePayAmountMap.get(form.getTransactionno())+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							int res=this.mytemplate.update(updateQry,updateArgs);
							logger.info("Udate Result=>"+res);
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMSETSTAUS);
							updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = branchCode;
						 	updateArgs[2] = payform.getLogin_id();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							res=this.mytemplate.update(updateQry,updateArgs);
							logger.info("Udate Result=>"+res);
							//b=b+Double.parseDouble(form.getAccClaim());
							b = b + Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
						}
					args[8]="Y";
					args[9]="0";
					args[10]=payform.getPolicyno();//Receipt No
					payform.setPay_rec_no(payform.getPolicyno());
					args[11]=payform.getAlloccurrencyid();//Currency ID
					args[12]=StringUtils.isBlank(payform.getHideprocessType())?"I":"O";
					args[13]=form.getSubClass();
					args[14]=payform.getLogin_id();
					args[15]=branchCode;
					args[16]=form.getProposalNo();
					updateQry=getQuery(DBConstants.PAYMENT_INSERT_ALLOTRAN);
					logger.info("Query=>"+updateQry);
					int in=0;
					/*for(String s:args){
						logger.info("Arg["+in+"]==>"+s);
					}*/
					logger.info("Args==> " + StringUtils.join(args,","));
					int res=this.mytemplate.update(updateQry,args);
					logger.info("Result=>"+res);
				}
			}
			
			/*for(int i=0;i<payList.size();i++) {
				TreasuryBean form= payList.get(i);
				if("Y".equalsIgnoreCase(form.getCheckYN()))
				{	 
					 String[] args=new String[12];
					 args[0]=serialNo;	
					 args[1]=form.getContractno();
					 args[2]="0";//Layer No if need than we want to change.
					 args[3]=form.getProductname();
					 args[4]=form.getTransactionno();
					 args[5]=payform.getAccountDate();
						if("P".equalsIgnoreCase(form.getCheckPC())){
						 	args[6]=form.getAccPremium();
						 	args[7]="P";
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMALLODTLS);
						 	Object[] updateArgs = new Object[3];
							updateArgs[0] = form.getAccPremium();
							updateArgs[1] = form.getContractno();
							updateArgs[2] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+form.getAccPremium()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
						 	int res=this.mytemplate.update(updateQry,updateArgs);
						 	logger.info("Udate Result=>"+res);
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PREMSETSTAUS);
						 	updateArgs = new Object[3];
							updateArgs[0] = "Allocated";
							updateArgs[1] = form.getContractno();
							updateArgs[2] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+updateArgs[0]+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
						 	res=this.mytemplate.update(updateQry,updateArgs);
						 	logger.info("Udate Result=>"+res);
							a=a+Double.parseDouble(form.getAccPremium());
						}else if("C".equalsIgnoreCase(form.getCheckPC()))
						{
							args[6]=form.getAccClaim();
						 	args[7]="C";
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTALLODTLS);
							Object[] updateArgs = new Object[3];
							updateArgs[0] = form.getAccClaim();
							updateArgs[1] = form.getContractno();
							updateArgs[2] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+form.getAccClaim()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							int res=this.mytemplate.update(updateQry,updateArgs);
							logger.info("Udate Result=>"+res);
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMSETSTAUS);
							updateArgs = new Object[3];
							updateArgs[0] = "Allocated";
							updateArgs[1] = form.getContractno();
							updateArgs[2] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							res=this.mytemplate.update(updateQry,updateArgs);
							logger.info("Udate Result=>"+res);
							b=b+Double.parseDouble(form.getAccClaim());
						}
					args[8]="Y";
					args[9]="0";
					args[10]=payform.getPolicyno();//Receipt No
					payform.setPay_rec_no(payform.getPolicyno());
					args[11]=payform.getAlloccurrencyid();//Currency ID
					updateQry=getQuery(DBConstants.PAYMENT_INSERT_ALLOTRAN);
					logger.info("Query=>"+updateQry);
					int in=0;
					for(String s:args){
						logger.info("Arg["+in+"]==>"+s);
					}
					int res=this.mytemplate.update(updateQry,args);
					logger.info("Result=>"+res);
				}
			}*/
			if("RT".equalsIgnoreCase(payform.getTransType()))
			{
				c=a-b;
				//c=a+b;
			}
			else if("PT".equalsIgnoreCase(payform.getTransType()))
			{
				c=b-a;
				//c=b+a;
			}
			Object[] updateArgs = new Object[5];
			updateArgs[0] = c;
			updateArgs[1] = payform.getLogin_id();
			updateArgs[2] = branchCode;
			updateArgs[3] = payform.getPolicyno();
			updateArgs[4] = payform.getAlloccurrencyid();
			updateQry=getQuery(DBConstants.PAYMENT_UPDATE_ALLOTRANDTLS);
			logger.info("Update Query=>"+updateQry);
			logger.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+updateArgs[1]+"\nArgs[2]=>"+updateArgs[2]);
			int res=this.mytemplate.update(updateQry,updateArgs);
			logger.info("Result=>"+res);
			updateQry=getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMCHKYN);
			logger.info("Update Query=>"+updateQry);
			res=this.mytemplate.update(updateQry);
			logger.info("Result=>"+res);
			logger.info("Update Query=>"+updateQry);
			res=this.mytemplate.update(updateQry);
			logger.info("Result=>"+res);
			
	}
  	catch(Exception exe) {
  		exe.printStackTrace();
	}
  	logger.info("PaymentDAOImpl getallocateTransaction() || Exit");
	}

	public List<TreasuryBean> getReceiptReversalList(String transType,
			String branchCode, String type, TreasuryBean bean) {
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		try {
			query = getQuery("getReversaltLists");
			args = new Object[9];
			args[0] = branchCode;
			args[1] = branchCode;
			args[2] = transType;
			args[3] = branchCode;
			if("reversal".equalsIgnoreCase(type)){
				args[4] = "R";
				args[8] = "R";
			}
			/*else {
				args[4] = "Y";
				args[8] = "Y";
			}*/
			args[5] = branchCode;
			args[6] = transType;
			args[7] = branchCode;
			if(StringUtils.isNotBlank(bean.getSearchType())){
				query+=" WHERE BRANCH_CODE="+branchCode;
            	if(StringUtils.isNotBlank(bean.getPaymentNoSearch())){
            		query +=" AND PAYMENT_RECEIPT_NO like '%"+bean.getPaymentNoSearch()+"%'";
            	}
            	if(StringUtils.isNotBlank(bean.getBrokerNameSearch())){
            		query +=" AND UPPER(BROKER) like UPPER('%"+bean.getBrokerNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getCompanyNameSearch())){
            		query +=" AND UPPER(COMPANY_NAME) like UPPER('%"+bean.getCompanyNameSearch()+"%')";
            	}
            	if(StringUtils.isNotBlank(bean.getRemarksSearch())){
            		query +=" AND UPPER(REMARKS) like UPPER('%"+bean.getRemarksSearch()+"%')";
            	}
            	
			}else{
				bean.setPaymentNoSearch("");
				bean.setBrokerNameSearch("");
				bean.setCompanyNameSearch("");
				bean.setRemarksSearch("");
			}
			
			query +=" ORDER BY  PAYMENT_RECEIPT_NO DESC";
			if(StringUtils.isBlank(bean.getFullSearch()) && StringUtils.isBlank(bean.getSearchType())){
				query ="select * from ( "+query+" )where  rownum<=100";
        	}
			/*if(bean.getSearchBy()!=null){
				if("1".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where PAYMENT_RECEIPT_NO like '"+bean.getSearchValue()+"'";
				}else if("2".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where BROKER like '"+bean.getSearchValue()+"'";
				}else if("3".equalsIgnoreCase(bean.getSearchBy())){
					query +=" where COMPANY_NAME like '"+bean.getSearchValue()+"'";
				}
			}*/
			//query +=" ORDER BY  PAYMENT_RECEIPT_NO DESC";
			logger.info("Query=>" + query);
			logger.info("Args==> " + StringUtils.join(args,", "));

			int count = 0;
			List<Map<String,Object>> list = this.mytemplate.queryForList(query, args);
			for(int i = 0; i < list.size(); i++ ) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> resMap = list.get(i);
				tempBean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				tempBean.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				tempBean.setName(resMap.get("PAYMENT_RESPONSE")==null?"":resMap.get("PAYMENT_RESPONSE").toString());
				tempBean.setPayamount(resMap.get("PAID_AMT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMT").toString()));
				tempBean.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				tempBean.setSerial_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
				count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new Object[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"Y"});
				if(count>0) {
					tempBean.setEditShowStatus("No");
				}
				else {
					tempBean.setEditShowStatus("Yes");                	  
				}
				count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new Object[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"R"});
				if(count>0)
				{
					tempBean.setReversedShowStatus("Yes");
				}else 
				{
					tempBean.setReversedShowStatus("No");                	  
				}
				tempBean.setType(bean.getType());
				finalList.add(tempBean);
			}
		} 
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}
	public List<Map<String, Object>> getDirectCeding(String brokerId,String branchId){
        logger.info("TreasuryDAOImpl getDirectCeding || Enter");
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        try{
            String sql=getQuery(DBConstants.DIRECT_CEDING);
            Object[] args = new String[]{branchId};
            logger.info("Select Query====>"+sql);
            logger.info("Args==> " + StringUtils.join(args,","));
            result=this.mytemplate.queryForList(sql,args);
            logger.info("Result Size====>"+result.size());
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        logger.info("TreasuryDAOImpl getFinalMenuList || Exit");
        return result;
    }
	public String getShortname(String branchCode) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] {branchCode}, String.class);
		return Short;
	}

	public List<Map<String, Object>> getTreasuryJournalView(TreasuryBean bean) {
		logger.info("TreasuryDAOImpl getTreasuryJournalView || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			Object[] args=new String[2];
			if(bean.getType().equalsIgnoreCase("")){
			args[0]=bean.getSerial_no();
			args[1]=bean.getSerial_no();
			}
			else{
				args[0]=bean.getAllocationNo();
				args[1]=bean.getAllocationNo();
			}
			String sql=getQuery(DBConstants.TREASURY_JOURNAL_VIEW);
			logger.info("Select Query====>"+sql);
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("TreasuryDAOImpl getTreasuryJournalView || Exit");
		return result;
	}

	public List<TreasuryBean> getReceiptTreasuryGeneration(TreasuryBean bean,
			String branchCode) {
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		try {
			if("63".equals(bean.getBroker())) {
				query=getQuery(DBConstants.PAYMENT_SELECT_GETDIRBRODTLS);
				args=new String[5];
				args[0]=branchCode;
				args[1]=branchCode;
				args[2]=branchCode;
				args[3]=bean.getPay_rec_no();
				args[4]=branchCode;
			}
			else {
				query=getQuery(DBConstants.PAYMENT_SELECT_GETBRODTLS);
				args=new String[4];
				args[0]=branchCode;
				args[1]=branchCode;
				args[2]=bean.getPay_rec_no();
				args[3]=branchCode;
			}
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,args);
			//for(int i=0;i<list.size();i++) {
			if(list.size()>0) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> resMap = list.get(0);
				tempBean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
				tempBean.setBrokername(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
				tempBean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
				tempBean.setPaymentamount(resMap.get("PAID_AMT")==null?"":DropDownControllor.formatter(resMap.get("PAID_AMT").toString()));
				tempBean.setTr_date(resMap.get("TRANSDATE")==null?"":resMap.get("TRANSDATE").toString());
				tempBean.setSerial_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
				tempBean.setExchangerate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
				tempBean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":DropDownControllor.formatter(resMap.get("EXCHANGE_RATE").toString()));
				tempBean.setTotalexchaamount(resMap.get("TOT_EXC_AMT")==null?"":DropDownControllor.formatter(resMap.get("TOT_EXC_AMT").toString()));
				tempBean.setBroker(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				tempBean.setDiffAmount(resMap.get("DIFF_AMT")==null?"":DropDownControllor.formatter(resMap.get("DIFF_AMT").toString()));
				tempBean.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
				tempBean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
				tempBean.setPay_rec_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
				tempBean.setBankCharges(resMap.get("BANK_CHARGES")==null?"":DropDownControllor.formatter(resMap.get("BANK_CHARGES").toString()));
				tempBean.setNetAmt(resMap.get("NET_AMT")==null?"":DropDownControllor.formatter(resMap.get("NET_AMT").toString()));
				tempBean.setReceiptBankName(resMap.get("BANK_NAME")==null?"":resMap.get("BANK_NAME").toString());
                tempBean.setWithHoldingTaxOC(resMap.get("WH_TAX") == null ? "" : DropDownControllor.formatter(resMap.get("WH_TAX").toString()));
                tempBean.setPremiumLavy(resMap.get("PREMIUM_LAVY") == null ? "" : DropDownControllor.formatter(resMap.get("PREMIUM_LAVY").toString()));
                finalList.add(tempBean);
            }
			//}
		}	
		catch(Exception e) {
			logger.debug("Exception @ { " + e + " } ");
		}
		return finalList;
	}

	public List<TreasuryBean> getRetroTransContract(TreasuryBean bean,String branchCode, Map<String, String> receivePayAmountMap) {
		List<TreasuryBean> finalList=new ArrayList<TreasuryBean>();
		args=new String[4];
		logger.info("Transaction Type=>"+bean.getTransType());
		args[0]=branchCode;
		args[1]=bean.getAlloccurrencyid();
		args[2]=bean.getBrokerid();
		if("63".equalsIgnoreCase(bean.getBrokerid()))
			args[3]=bean.getCedingid();
		else        	
			args[3]=bean.getBrokerid();
		//args[4]="%"+(bean.getSearchContractNo()).trim()+"%";
		//args[5]="%"+bean.getSearchBusinessType()+"%";
		
        try {
            String selectQry;
            selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTRANCONTDTLS_RETRO);
            logger.info("Select Query=>" + selectQry);
            logger.info("Args==> " + StringUtils.join(args, ","));
            List<Map<String, Object>> list = this.mytemplate.queryForList(selectQry, args);
            bean.setTotalreccount(String.valueOf(list.size()));

			//int val = (list.size()<Integer.valueOf(bean.getEndrownum())?list.size():Integer.valueOf(bean.getEndrownum())) - Integer.valueOf(bean.getStartrownum());
			List<String> receivePayAmounts = new ArrayList<String>();
			List<String> chkbox = new ArrayList<String>();
			List<String> previousValue = new ArrayList<String>();

			for(int i=Integer.valueOf(bean.getStartrownum())-1,count=0 ; i<list.size() && i<Integer.valueOf(bean.getEndrownum()) ; i++,count++) {
				TreasuryBean tempBean=new TreasuryBean();
				Map<String,Object> tempMap = list.get(i);
				tempBean.setContractno(tempMap.get("RETRO_CONTRACT_NUMBER")==null?"":tempMap.get("RETRO_CONTRACT_NUMBER").toString());
				tempBean.setTransactionno(tempMap.get("TRANSACTION_NO")==null?"":tempMap.get("TRANSACTION_NO").toString());
				tempBean.setInceptiobdate(tempMap.get("TRANSACTION_DATE")==null?"":tempMap.get("TRANSACTION_DATE").toString());
				tempBean.setNetdue(tempMap.get("NETDUE")==null?"":tempMap.get("NETDUE").toString());
				tempBean.setCheckPC(tempMap.get("sign")==null?"":tempMap.get("sign").toString());
				tempBean.setCedingCompanyName(tempMap.get("CEDING_COMPANY_NAME")==null?"":tempMap.get("CEDING_COMPANY_NAME").toString());
				tempBean.setAllocType(bean.getAllocType());
				if(receivePayAmountMap.containsKey(tempBean.getTransactionno())) {
					//receivePayAmounts[count] = receivePayAmountMap.get(bean.getTransactionno());
					receivePayAmounts.add(Integer.toString(Math.abs(Integer.parseInt(receivePayAmountMap.get(tempBean.getTransactionno())))));
					//receivePayAmounts.add(Math.abs(Integer.parseInt((receivePayAmountMap.get(tempBean.getTransactionno()))));
					previousValue.add(receivePayAmountMap.get(tempBean.getTransactionno()));
					chkbox.add("true");
				}
				else {
					//receivePayAmounts[count] = "1";
					receivePayAmounts.add("");
					previousValue.add("");
					chkbox.add("false");
				}
				tempBean.setCount(String.valueOf(count));
				finalList.add(tempBean);
			}
			bean.setReceivePayAmounts(receivePayAmounts);
			bean.setChkbox(chkbox);
			bean.setPreviousValue(previousValue);
		}	
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return finalList;
	}

	public void getRetroallocateTransaction(TreasuryBean payform,String branchCode, Map<String, String> receivePayAmountMap) {
		logger.info("PaymentDAOImpl getRetroallocateTransaction() || Enter");
		try{
			
			List<TreasuryBean> payList = getAllRetroTransContract(payform, branchCode);
			
			Double a=0.0,b=0.0,c=0.0;
			String updateQry;
			String serialNo;
			//if("06".equalsIgnoreCase(branchCode)){
				serialNo=new DropDownControllor().getSequence("TreasuryARP","","", branchCode,"",payform.getAccountDate());
			/*}else{
			updateQry=getQuery(DBConstants.PAYMENT_SELECT_GETNEXTSNO);
			logger.info("Query=>"+updateQry);
			serialNo =(String) this.mytemplate.queryForObject(updateQry,String.class);
			logger.info("Result=>"+serialNo);
			}*/
			payform.setSerial_no(serialNo);
			
			for(int i=0;i<payList.size();i++) {
				//String[] ary = payform.getSign().split(",");
				TreasuryBean form= payList.get(i);
				if(receivePayAmountMap.containsKey(form.getTransactionno())) {	 
					 args=new String[12];
					 args[0]=serialNo;	
					 args[1]=form.getContractno();
					 //args[2]=form.getMode();
					 args[2]=StringUtils.isBlank(form.getMode())?"0":form.getMode();//Layer No if need than we want to change.
					 args[3]=form.getProductname();
					 args[4]=form.getTransactionno();
					 args[5]=payform.getAccountDate();
					
					args[6]= payform.getTransType().equals("PT")?Double.toString((-1)*Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))):Double.toString(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno())));
				 	args[7]="RE";
				 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_TTRNRETRODTS);
				 	Object[] updateArgs = new Object[3];
				 	updateArgs[0] = payform.getTransType().equals("PT")?Double.toString((-1)*Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()))):Double.toString(Double.parseDouble(receivePayAmountMap.get(form.getTransactionno())));
					updateArgs[1] = form.getContractno();
					updateArgs[2] = form.getTransactionno();
					logger.info("Update Query=>"+updateQry);
					logger.info("Args[0]=>"+updateArgs[0]+"\nArgs[1]=>"+updateArgs[1]+"\nArgs[2]=>"+updateArgs[2]);
				 	int res=this.mytemplate.update(updateQry,updateArgs);
				 	logger.info("Udate Result=>"+res);
				 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_TTRNRETROSTATUS);
				 	updateArgs = new Object[3];
					updateArgs[0] = "Allocated";
					updateArgs[1] = form.getContractno();
					updateArgs[2] = form.getTransactionno();
					logger.info("Update Query=>"+updateQry);
					logger.info("Args[0]=>"+updateArgs[0]+"\nArgs[1]=>"+updateArgs[1]+"\nArgs[2]=>"+updateArgs[2]);
				 	res=this.mytemplate.update(updateQry,updateArgs);
				 	logger.info("Udate Result=>"+res);
				 	//a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
				 	a=a+Double.parseDouble(receivePayAmountMap.get(form.getTransactionno()));
					args[8]="Y";
					args[9]="0";
					args[10]=payform.getPolicyno();//Receipt No
					payform.setPay_rec_no(payform.getPolicyno());
					args[11]=payform.getAlloccurrencyid();//Currency ID
					updateQry=getQuery(DBConstants.PAYMENT_INSERT_ALLOTRAN);
					logger.info("Query=>"+updateQry);
					int in=0;
					logger.info("Args==> " + StringUtils.join(args,","));
					int res1=this.mytemplate.update(updateQry,args);
					logger.info("Result=>"+res1);
				}
			}
			/*if("RT".equalsIgnoreCase(payform.getTransType()))
			{
				c=a-b;
				//c=a+b;
			}
			else if("PT".equalsIgnoreCase(payform.getTransType()))
			{
				c=b-a;
				//c=b+a;
			}*/
			Object[] updateArgs = new Object[5];
			updateArgs[0] = a;
			updateArgs[1] = payform.getLogin_id();
			updateArgs[2] = branchCode;
			updateArgs[3] = payform.getPolicyno();
			updateArgs[4] = payform.getAlloccurrencyid();
			updateQry=getQuery(DBConstants.PAYMENT_UPDATE_ALLOTRANDTLS);
			logger.info("Update Query=>"+updateQry);
			logger.info("Args[0]=>"+updateArgs[0]+" Args[1]=>"+updateArgs[1]+"\nArgs[2]=>"+updateArgs[2]);
			int res=this.mytemplate.update(updateQry,updateArgs);
			logger.info("Result=>"+res);
			
	}
  	catch(Exception exe) {
  		exe.printStackTrace();
	}
  	logger.info("PaymentDAOImpl getallocateTransaction() || Exit");
	}

}
