package com.maan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.maan.bean.PaymentBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.PaymentDAO;

public class PaymentDAOImpl extends MyJdbcTemplate implements PaymentDAO
{
	final static org.slf4j.Logger logger = LogUtil.getLogger(PaymentDAOImpl.class);
	public boolean insertReceiptNO(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl insertReceiptNO || Enter");
		boolean flag=false;
		String updateQry;
		try{
				if("".equalsIgnoreCase(payform.getFlag()))
				{
					Object[] updateArgs = new Object[2];
					updateArgs[0] = payform.getProid();
					updateArgs[1] = payform.getProid();	
					
					Object[] selectArgs = new Object[2];
					selectArgs[0] = payform.getProid();
					selectArgs[1] = payform.getBranchCode();
					
					String insertQry = getQuery(DBConstants.PAYMENT_INSERT_RECEIPT);
					logger.info("Query=>"+insertQry);
					String[] obj=insertReceiptNoArg(payform);
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
						payform.setSerial_no(obj[0]);
						if(insertCount>0)
							flag=true;
					}
				}
				else if("EDIT".equalsIgnoreCase(payform.getFlag()))
				{
					updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PYMTRETDATA);
					logger.info("Query=>"+updateQry);
					int updateCount = this.mytemplate.update(updateQry,insertReceiptNoArg(payform));
					logger.info("Result=>"+updateCount);
					if(updateCount>0)
					{
						flag=true;
					}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("PaymentDAOImpl insertReceiptNO || Exit Flag"+flag);
		return flag;
	}
	
	public String[] insertReceiptNoArg(PaymentBean payform)
	{
		logger.info("PaymentDAOImpl insertReceiptNoArg || Enter");
		String args[]=null;
	    if("".equalsIgnoreCase(payform.getFlag()))
		{
			args =new String[16];
			//args[0]=maxNo(payform);
			//if("06".equalsIgnoreCase(payform.getBranchCode())){
				args[0]=new DropDownControllor().getSequence("PT".equalsIgnoreCase(payform.getTransType()) ? "TreasuryrPR" : "TreasuryrRP","","", payform.getBranchCode(),"",payform.getTr_date());
			//}else
			//args[0]=new DropDownControllor().getPolicyNo("PT".equalsIgnoreCase(payform.getTransType())?"6":"7", "0",payform.getBranchCode());
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
			args[13]=payform.getBranchCode();
			args[14]=payform.getBankCharges();
			args[15]=payform.getNetAmt();
		}
		else if("EDIT".equalsIgnoreCase(payform.getFlag()))
		{
			args =new String[17];
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
			args[15]=payform.getSerial_no();
			args[16]=payform.getBranchCode();
		}
	    int j=0;
		for(String s:args)
			logger.info("args["+j+++"]"+s);
		final String[] copiedArray = new String[args.length];
		System.arraycopy(args, 0, copiedArray, 0, args.length);
		logger.info("PaymentDAOImpl insertReceiptNoArg || Exit");
		return copiedArray;
	}
	
	/*public String maxNo(PaymentActionForm payform)
	{
		logger.info("PaymentDAOImpl maxNo || Enter");
		String result="";
		try{
			String selectQry="";
			Object[] selectArgs = new Object[2];
			selectArgs[0] = payform.getProid();
			selectArgs[1] = payform.getBranchCode();
			logger.info("args[0]=>"+selectArgs[0]);	
			logger.info("args[1]=>"+selectArgs[1]);	
			if("PT".equalsIgnoreCase((payform.getTransType())))
			{
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETNEXTPAYMENTNO);
			}
			else
			{
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETNEXTRECEIPTNO);
			}
			logger.info("Query=>"+selectQry);	
			result = this.mytemplate.queryForInt(selectQry, selectArgs) + "";
		}catch(Exception e){
			logger.debug(e);
		}
		logger.info("PaymentDAOImpl maxNo || Exit Result=>"+result);
		return result;
	}*/
	
	public void generationInsert(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl generationInsert || Enter");
		try{
			String updateQry;
			if(!"Treasury".equals(payform.getTransactionType()))
			{
				Object[] obj=new Object[10];
				int LoopCount=Integer.parseInt(payform.getRequest().getParameter("HidRowCnt"));
				logger.info("LoopCount=>"+LoopCount);
				for(int i=1;i<=LoopCount;i++)
				{	String curencyId=StringUtils.isEmpty(payform.getRequest().getParameter("cedingCompany"+i)) ? "0" :payform.getRequest().getParameter("cedingCompany"+i);
					String recNo=StringUtils.isEmpty(payform.getRequest().getParameter("recNo"+i)) ? "" :payform.getRequest().getParameter("recNo"+i);
					logger.info("RecNo=>"+recNo);
					if(recNo.length()>0)
					{
						obj[0]=curencyId;
						obj[1]=StringUtils.isEmpty(payform.getRequest().getParameter("payamount"+i)) ? "0" :payform.getRequest().getParameter("payamount"+i).trim();
						obj[2]=StringUtils.isEmpty(payform.getRequest().getParameter("exachange"+i)) ? "0" :payform.getRequest().getParameter("exachange"+i);
						obj[3]=payform.getTr_date();
						obj[4]="Y";
						obj[5]=StringUtils.isEmpty(payform.getRequest().getParameter("rowamount"+i)) ? "0" :payform.getRequest().getParameter("rowamount"+i);
						obj[6]=StringUtils.isEmpty(payform.getRequest().getParameter("setExcRate"+i)) ? "0" :payform.getRequest().getParameter("setExcRate"+i);
						obj[7]=StringUtils.isEmpty(payform.getRequest().getParameter("conRecCur"+i)) ? "0" :payform.getRequest().getParameter("conRecCur"+i);
						obj[8]=recNo;
						obj[9]=payform.getSerial_no();
						int j=0;
						for(Object o:obj)
							logger.info("Obj["+j+++"]=>"+o);
						int res=this.mytemplate.update(generationInsert(2),obj);
						logger.info("Result=>"+res);
					}else{
						obj[0]=maxVal();
						obj[1]=payform.getSerial_no();  
						obj[2]=curencyId;
						obj[3]=StringUtils.isEmpty(payform.getRequest().getParameter("payamount"+i)) ? "0" :payform.getRequest().getParameter("payamount"+i).trim();
						obj[4]=StringUtils.isEmpty(payform.getRequest().getParameter("exachange"+i)) ? "0" :payform.getRequest().getParameter("exachange"+i);
						obj[5]=payform.getTr_date();
						obj[6]="Y";
						obj[7]=StringUtils.isEmpty(payform.getRequest().getParameter("rowamount"+i)) ? "0" :payform.getRequest().getParameter("rowamount"+i);
						obj[8]=StringUtils.isEmpty(payform.getRequest().getParameter("setExcRate"+i)) ? "0":payform.getRequest().getParameter("setExcRate"+i);
						obj[9]=StringUtils.isEmpty(payform.getRequest().getParameter("conRecCur"+i)) ? "0" :payform.getRequest().getParameter("conRecCur"+i);
						int j=0;
						for(Object o:obj)
							logger.info("Obj["+j+++"]=>"+o);
						int res=this.mytemplate.update(generationInsert(1),obj);
						logger.info("Result=>"+res);
					}
				}
				double diffAmt=Double.parseDouble(StringUtils.isEmpty(payform.getRequest().getParameter("txtDiffAmt")) ? "0" :payform.getRequest().getParameter("txtDiffAmt"));
				if("M".equalsIgnoreCase(payform.getDifftype())){
					diffAmt=diffAmt*(-1);
				}
				obj = new Object[3];
				obj[0] = diffAmt;
				obj[1] = payform.getSerial_no();
				obj[2] = payform.getBranchCode();
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
				obj[2]=payform.getPay_rec_no();
				obj[3]=payform.getLogin_id();
				obj[4]=payform.getLogin_id();
				obj[5]=payform.getBranchCode();
				obj[6]=payform.getSerial_no();
				obj[7]=payform.getBranchCode();
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_REVERSALPAYMENT);
				int j=0;
				for(Object o:obj)
					logger.info("Obj["+j+++"]=>"+o);
				logger.info("Query=>"+updateQry);
				int res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>"+res);
				obj[1]="PT";
				obj[2]=payform.getSerial_no();
				obj[6]=payform.getPay_rec_no();
				j=0;
				for(Object o:obj)
					logger.info("Obj["+j+++"]=>"+o);
				logger.info("Result=>"+updateQry);
				res=this.mytemplate.update(updateQry,obj);
				logger.info("Result=>"+res);
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_REVERSALPAYMENTDETAILS);
				logger.info("Query=>"+updateQry);
				logger.info("Args[0]=>"+payform.getSerial_no());
				res=this.mytemplate.update(updateQry,new Object[]{payform.getSerial_no()});
				logger.info("Result=>"+res);
				logger.info("Args[0]=>"+payform.getPay_rec_no());
				res=this.mytemplate.update(updateQry,new Object[]{payform.getPay_rec_no()});
				logger.info("Result=>"+res);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.info("PaymentDAOImpl generationInsert || Exit");
	}
	
	public String generationInsert(int flag) 
	{
		StringBuffer query=new StringBuffer();
		if(flag==1)
		{
			query.append(getQuery(DBConstants.PAYMENT_INSERT_PAYRETDTLS));
		}else if(flag==2)
		{
			query.append(getQuery(DBConstants.PAYMENT_UPDATE_PAYRETDTLS));
		}
		logger.info("Query=>"+query.toString());	
		return query.toString();
	}
	
	private String maxVal() 
	{
		String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETNEXTRETDTLSNO);
		logger.info("Query=>"+selectQry);
		String result = this.mytemplate.queryForInt(selectQry) + "";
		logger.info("Recipt Serial No=>"+result);
		return result;
	}
	
	public void getReceiptGeneration(PaymentBean payform)
	{
		logger.info("PaymentDAOImpl getReceiptGeneration() || Enter");
		try{
			String args[]=null;
			String query="";
			if("63".equals(payform.getBroker()))
			{
			 query=getQuery(DBConstants.PAYMENT_SELECT_GETDIRBRODTLS);
			 args=new String[5];
			 args[0]=payform.getBranchCode();
			 args[1]=payform.getBranchCode();
			 args[2]=payform.getBranchCode();
			 args[3]=payform.getSerial_no();
			 args[4]=payform.getBranchCode();
			}
			else
			{
				query=getQuery(DBConstants.PAYMENT_SELECT_GETBRODTLS);
				args=new String[4];
				args[0]=payform.getBranchCode();
				args[1]=payform.getBranchCode();
				args[2]=payform.getSerial_no();
				args[3]=payform.getBranchCode();
			}
			logger.info("Select Query=>"+query);
			int j=0;
			for(String  o:args)
				logger.info("Obj["+j+++"]=>"+o);
			List list = this.mytemplate.queryForList(query,args);
			logger.info("List Size=>"+list.size());
			payform.setCurrencyValue(payform.getCurrency());
			if(list.size()>0)
			{
				for(int i=0;i<list.size();i++)
				{
					Map resMap = (Map)list.get(i);
					payform.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
					payform.setBrokername(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
					payform.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
					payform.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
					payform.setTr_date(resMap.get("TRANSDATE")==null?"":resMap.get("TRANSDATE").toString());
					payform.setSerial_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
					payform.setExchangerate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
					payform.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
					payform.setTotalexchaamount(resMap.get("TOT_EXC_AMT")==null?"":resMap.get("TOT_EXC_AMT").toString());
					payform.setBroker(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
					payform.setDiffAmount(resMap.get("DIFF_AMT")==null?"":resMap.get("DIFF_AMT").toString());
					payform.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
					payform.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					payform.setPay_rec_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
					payform.setBankCharges(resMap.get("BANK_CHARGES")==null?"":resMap.get("BANK_CHARGES").toString());
	                payform.setNetAmt(resMap.get("NET_AMT")==null?"":resMap.get("NET_AMT").toString());
	                payform.setReceiptBankName(resMap.get("BANK_NAME")==null?"":resMap.get("BANK_NAME").toString());
				}
			}
		 }	
	    catch(Exception exe)
		{
			exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getReceiptGeneration() || Exit");
	}

	public String getCedingCo(final String broker) {
		logger.info("PaymentDAOImpl getCedingCo() || Enter ");
		String  Cedingco="";
		try{
			String selectQry="";
			String[] args=new String[1];
			args[0]=broker;
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETCEDINGCOMPDTLS);
			logger.info("Select Query=>"+selectQry);
			logger.info("args[0]=>"+args[0]);
			List list = this.mytemplate.queryForList(selectQry, args);
			logger.info("List Size=>"+list.size());
			Map resMap;
			for(int i=0;i<list.size();i++) 
			{
				resMap = (Map)list.get(i); 
				if(i==(list.size()-1))
				{
					Cedingco+=resMap.get("RSK_CEDINGID").toString()+"~"+resMap.get("COMPANY_NAME").toString();
				}else
				{
					Cedingco+=resMap.get("RSK_CEDINGID").toString()+"~"+resMap.get("COMPANY_NAME").toString()+"~";	
				}
			}
		}	
	    catch(Exception exe)
		{
			exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getCedingCo() || Exit Cedingco=>"+Cedingco);
		return Cedingco;	
	}
	
	public void getAllocateView(PaymentBean payform,String contract, String trancsaction,String ceding, String brokerid) 
	{
		try{
			String[] args=new String[4];
			args[0]=brokerid;
			args[1]=ceding;
			args[2]=contract;
			args[3]=trancsaction;
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANDTLS);
			for(int i=0;i<args.length;i++)
			{
				logger.info("Args["+i+"]=>"+args[i]);
			}
			logger.info("selectQry=>"+selectQry);
			Map resMap = this.mytemplate.queryForMap(selectQry,args);
			logger.info("Result=>"+resMap.size());
			if(resMap!=null)
			{
				payform.setCedingid(resMap.get("CEDING_COMPANY_ID")==null?"":resMap.get("CEDING_COMPANY_ID").toString());
				payform.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
				payform.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
				payform.setLayerno(resMap.get("LAYER_NO")==null?"":resMap.get("LAYER_NO").toString());
				payform.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
				payform.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
				payform.setInceptiobdate(resMap.get("ENTRY_DATE_TIME")==null?"":resMap.get("ENTRY_DATE_TIME").toString());
				payform.setNetdue(resMap.get("NETDUE")==null?"":resMap.get("NETDUE").toString());
				payform.setPaymentamount(resMap.get("PAID_AMOUNT_OC ")==null?"":resMap.get("PAID_AMOUNT_OC ").toString());
			}
		}catch(Exception exe){
			exe.printStackTrace();
		}
	}
	public List getReceiptAllocate(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl getReceiptAllocate || Enter");
		final List list=new ArrayList();
		try{
			String query=getQuery(DBConstants.PAYMENT_UPDATE_RSKPREMCHKYN);
			logger.info("Query=>"+query);
			int res=this.mytemplate.update(query);
			logger.info("Result=>"+res);
			query=getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTCHKYN);
			logger.info("Query=>"+query);
			res=this.mytemplate.update(query);
			logger.info("Result=>"+res);
			query = getQuery(DBConstants.PAYMENT_SELECT_GETRETALLODTLS);
			logger.info("Query=>"+query);
			logger.info("Args[0]=>"+payform.getBranchCode());
			logger.info("Args[1]=>"+payform.getTransType());
			logger.info("Args[2]=>"+payform.getBranchCode());
			logger.info("Args[3]=>"+payform.getTransType());
			List dataList = this.mytemplate.queryForList(query, new String[]{payform.getBranchCode(),payform.getTransType(),payform.getBranchCode(),payform.getTransType()});
			logger.info("List Size=>"+dataList.size());
			if(dataList.size()>0) {
				for(int i = 0; i < dataList.size(); i++) {
					PaymentBean bean =new PaymentBean();
					LinkedHashMap allocationCurrency = new LinkedHashMap();
					Map resMap = (Map)dataList.get(i);
					bean.setSerial_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
					bean.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
					bean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
					bean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
					bean.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
					bean.setCedingid(resMap.get("CEDDINGID")==null?"":resMap.get("CEDDINGID").toString());
					bean.setBrokerid(resMap.get("BROKERID")==null?"":resMap.get("BROKERID").toString());
					query = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETCURRDTLS);
					Object[] selectArgs = new Object[2];
					selectArgs[0] = payform.getBranchCode();
					selectArgs[1] = resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString();
					logger.info("query " + query);
					logger.info("arg[0] " + payform.getBranchCode());
					logger.info("arg[1] " + resMap.get("PAYMENT_RECEIPT_NO"));
					List curList = this.mytemplate.queryForList(query, selectArgs);
					logger.info("List Size=>"+curList.size());
					for(int c = 0; c < curList.size(); c++) {
						Map<String,Object> inResMap = (Map)curList.get(c);
						logger.info("map size " + inResMap.size());
						if("PA".equalsIgnoreCase(payform.getAllocType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())!=0)
						{
								allocationCurrency.put(inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString(),inResMap.get("CURRENCY_NAME")==null?"":inResMap.get("CURRENCY_NAME").toString());
							
						}else if("FA".equalsIgnoreCase(payform.getAllocType())&& Double.parseDouble(inResMap.get("AMOUNT").toString())==0)
						{
								allocationCurrency.put(inResMap.get("CURRENCY_ID").toString()+"$"+resMap.get("PAYMENT_RECEIPT_NO").toString(),inResMap.get("CURRENCY_NAME").toString());
						}
						bean.setAllocateCurrency(allocationCurrency);
					}
					if(allocationCurrency.size()>0)
						list.add(bean);
				}
			}
		}catch(Exception exe){
			exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getReceiptAllocate || Exit"+list.size());
		return list;
	}
	
	public List getReceiptContract(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl getReceiptContract || Enter");
		List list=new ArrayList();
		String[] args=new String[2];
		args[0]=payform.getBrokerid();
		args[1]=payform.getCedingid();
		try
		{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETCONTDTLS);
			logger.info("Query=>" + selectQry);
			logger.info("Args[0]=>" + payform.getBrokerid());
			logger.info("Args[1]=>" + payform.getCedingid());
		 	list = this.mytemplate.query(selectQry,args, new RowMapper() {
		 		public Object mapRow(ResultSet rSet, int idVal) throws SQLException {
		 			PaymentBean bean =new PaymentBean();
		 			bean.setContractno(rSet.getString("CONTRACT_NO")==null?"":rSet.getString("CONTRACT_NO"));
					bean.setCedingCo(rSet.getString("COMPANY_NAME")==null?"":rSet.getString("COMPANY_NAME"));
					bean.setInceptiobdate(rSet.getString("INCEPTION_DATE")==null?"":rSet.getString("INCEPTION_DATE"));
					bean.setExpirtdate(rSet.getString("EXPIRY_DATE")==null?"":rSet.getString("EXPIRY_DATE"));
					bean.setProductname(rSet.getString("TMAS_PRODUCT_NAME")==null?"":rSet.getString("TMAS_PRODUCT_NAME"));
		 			return bean;
		 		}
		 	});		 	
		}catch(Exception exe){
			exe.printStackTrace();
		}
		
		logger.info("PaymentDAOImpl getReceiptContract || Exit"+list.size());
		return list;
	}
	public List getTransContract(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl getTransContract() || Enter");
		List list=new ArrayList();
	    String[] args=new String[13];
	    logger.info("Transaction Type=>"+payform.getTransType());
	    args[0]=payform.getBranchCode();
        args[1]=payform.getAlloccurrencyid();
        args[2]=payform.getBrokerid();
        if("63".equalsIgnoreCase(payform.getBrokerid()))
        	args[3]=payform.getCedingid();
        else        	
        	args[3]=payform.getBrokerid();
        args[4]="%"+(payform.getSearchContractNo()).trim()+"%";
        args[5]="%"+payform.getSearchBusinessType()+"%";
        args[6]=payform.getBranchCode();
        args[7]=payform.getAlloccurrencyid();
        args[8]=payform.getBrokerid();
        if("63".equalsIgnoreCase(payform.getBrokerid()))
        	args[9]=payform.getCedingid();
        else        	
        	args[9]=payform.getBrokerid();
        args[10]="%"+(payform.getSearchContractNo()).trim()+"%";
        args[11]="%"+payform.getSearchBusinessType()+"%";
        args[12]="%"+payform.getSearchType()+"%";
	    try
	    {
	    	String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTRANCONTDTLS);
		 	logger.info("Select Query=>"+selectQry);
		 	int i=0;
		 	for(String s:args)
		 	{
		 		logger.info("Args["+i+"]=>"+s);
		 		i++;
		 	}
	    	list = this.mytemplate.query(selectQry, args, new RowMapper() {
	    		public Object mapRow(ResultSet rSet, int idVal) throws SQLException {
	    			PaymentBean bean=new PaymentBean();
	                  bean.setContractno(rSet.getString("CONTRACT_NO")==null?"":rSet.getString("CONTRACT_NO").toString());
	                  bean.setMode(rSet.getString("LAYER_NO")==null?"":rSet.getString("LAYER_NO").toString());
	                  bean.setProductname(rSet.getString("PRODUCT_NAME")==null?"":rSet.getString("PRODUCT_NAME").toString());
	                  bean.setTransactionno(rSet.getString("TRANSACTION_NO")==null?"":rSet.getString("TRANSACTION_NO").toString());
	                  bean.setInceptiobdate(rSet.getString("ADATE")==null?"":rSet.getString("ADATE").toString());
	                  bean.setNetdue(rSet.getString("NETDUE")==null?"":rSet.getString("NETDUE").toString());
	                  bean.setPayamount(rSet.getString("PAID_AMOUNT_OC")==null?"":rSet.getString("PAID_AMOUNT_OC").toString());
	                  bean.setAccPremium(rSet.getString("ACC_PREMIUM")==null?"":rSet.getString("ACC_PREMIUM").toString());
	                  bean.setAccClaim(rSet.getString("ACC_CLAIM")==null?"":rSet.getString("ACC_CLAIM").toString());
	                  bean.setCheckYN(rSet.getString("CHECKYN")==null?"":rSet.getString("CHECKYN").toString());
	                  bean.setCheckPC(rSet.getString("BUSINESS_TYPE")==null?"":rSet.getString("BUSINESS_TYPE").toString());
	                  return bean;
	    		}
	    	});
	    }	
	    catch(Exception exe)
		{
	    	exe.printStackTrace();
		}
	    logger.info("PaymentDAOImpl getTransContract() || Exit List Size=>"+list.size());
		return list;
	}
	public List getReceiptList(String transType,String branchCode,String type) 
	{
		logger.info("PaymentDAOImpl getReceiptList() || Enter");
		List list=new ArrayList();
		try
		{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETLISTS);
			logger.info("Select Query=>"+selectQry);
			Object[] selectArgs = new Object[9];
			selectArgs[0] = branchCode;
			selectArgs[1] = branchCode;
			selectArgs[2] = transType;
			selectArgs[3] = branchCode;
			if("reversal".equalsIgnoreCase(type)){
				selectArgs[4] = "R";
				selectArgs[8] = "R";
			}else
			{
				selectArgs[4] = "Y";
				selectArgs[8] = "Y";
			}
			selectArgs[5] = branchCode;
			selectArgs[6] = transType;
			selectArgs[7] = branchCode;
			int j=0;
		   for(Object o:selectArgs)
			   logger.info("Args["+j+++"]=>"+o);
			Map resMap;
			int count = 0;
			List dataList = this.mytemplate.queryForList(selectQry, selectArgs);
			for(int i = 0; i < dataList.size(); i++ ) {
				PaymentBean bean=new PaymentBean();
				resMap = (Map) dataList.get(i);
				bean.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
		        bean.setCedingCo(resMap.get("COMPANY_NAME")==null?"":resMap.get("COMPANY_NAME").toString());
		        bean.setBroker(resMap.get("BROKER")==null?"":resMap.get("BROKER").toString());
		        bean.setName(resMap.get("PAYMENT_RESPONSE")==null?"":resMap.get("PAYMENT_RESPONSE").toString());
		        bean.setPayamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
		        bean.setBrokerid(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
		        bean.setSerial_no(resMap.get("REVERSALTRANSNO")==null?"":resMap.get("REVERSALTRANSNO").toString());
		        count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new String[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"Y"});
		        if(count>0)
		        {
		      	  bean.setEditShowStatus("No");
		        }else 
		        {
		      	  bean.setEditShowStatus("Yes");                	  
		        }
		        count = this.mytemplate.queryForInt(getQuery(DBConstants.PAYMENT_SELECT_GETTOTCOUNT),new String[]{resMap.get("PAYMENT_RECEIPT_NO").toString(),"R"});
		        if(count>0)
		        {
		      	  bean.setReversedShowStatus("Yes");
		        }else 
		        {
		      	  bean.setReversedShowStatus("No");                	  
		        }
		        list.add(bean);
			}
		} 
	 	catch(Exception exe)
		{
	 		exe.printStackTrace();
		}
	 	logger.info("PaymentDAOImpl getReceiptList() || Exit List Size=>"+list.size());
		return list;
	}
	
	public void getReceiptEdit(PaymentBean payform)
	{
		logger.info("PaymentDAOImpl getReceiptEdit() || Enter");
	  	try
		{		String query=getQuery(DBConstants.PAYMENT_SELECT_GETRETDTLS);
	  			logger.info("Query=>"+query);
	  			logger.info("Args[0]=>"+payform.getSerial_no());
	  			logger.info("Args[1]=>"+payform.getBranchCode());
	  			Map resMap = this.mytemplate.queryForMap(query,new Object[]{payform.getSerial_no(),payform.getBranchCode()});
	  			logger.info("Map Size=>"+resMap.size());
			 	if(resMap.size()>0)
		   		{
			 	  payform.setPay_rec_no(resMap.get("PAYMENT_RECEIPT_NO")==null?"":resMap.get("PAYMENT_RECEIPT_NO").toString());
			 	  payform.setCedingCo(resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString());
			 	  payform.setBroker(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
                  payform.setReceiptBankId(resMap.get("RECEIPT_BANK")==null?"":resMap.get("RECEIPT_BANK").toString()); 
                  payform.setTr_date(resMap.get("TRANS_DATE")==null?"":resMap.get("TRANS_DATE").toString());
                  payform.setPaymentamount(resMap.get("PAID_AMT")==null?"":resMap.get("PAID_AMT").toString());
                  payform.setCurrency(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
                  payform.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
                  payform.setTransactionType(resMap.get("TRANSCATIONTYPE")==null?"":resMap.get("TRANSCATIONTYPE").toString());
                  payform.setBankCharges(resMap.get("BANK_CHARGES")==null?"":resMap.get("BANK_CHARGES").toString());
                  payform.setNetAmt(resMap.get("NET_AMT")==null?"":resMap.get("NET_AMT").toString());
               }
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getReceiptEdit() || Exit"); 
	}
	public List getReceiptViewList(PaymentBean payform)
	{
		logger.info("PaymentDAOImpl getReceiptViewList() || Enter"); 
        List list=new ArrayList();
 		try
		{
 			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETVIEWDTLS);
 			logger.info("Select Query=>"+selectQry);
 			logger.info("Args[0]=>"+payform.getBranchCode());
 			logger.info("Args[1]=>"+payform.getSerial_no());
 			List resultList = this.mytemplate.queryForList(selectQry, new String[] {payform.getBranchCode(),payform.getSerial_no()});
 			logger.info("Result Size=>"+resultList.size());
 			for(int i=0;i<resultList.size();i++)
	   		{
		 		PaymentBean bean=new PaymentBean();
		 		Map resMap = (Map) resultList.get(i);
		 		bean.setPay_res(resMap.get("RECEIPT_NO")==null?"":resMap.get("RECEIPT_NO").toString());
		 		bean.setSerial_no(resMap.get("RECEIPT_SL_NO")==null?"":resMap.get("RECEIPT_SL_NO").toString());
		 		bean.setPayamount(resMap.get("AMOUNT")==null?"":resMap.get("AMOUNT").toString());
		 		bean.setExrate(resMap.get("EXCHANGE_RATE")==null?"":resMap.get("EXCHANGE_RATE").toString());
		 		bean.setInceptiobdate(resMap.get("IDATE")==null?"":resMap.get("IDATE").toString());
		 		bean.setCurrency(resMap.get("CURRENCY_NAME")==null?"":resMap.get("CURRENCY_NAME").toString());
		 		bean.setTotAmount(resMap.get("TOT_AMT")==null?"":resMap.get("TOT_AMT").toString());
		 		bean.setSetExcRate(resMap.get("SETTLED_EXCRATE")==null?"":resMap.get("SETTLED_EXCRATE").toString());
		 		bean.setConRecCur(resMap.get("CONVERTED_RECCUR")==null?"":resMap.get("CONVERTED_RECCUR").toString());
		 	    list.add(bean);
	   		}
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl getReceiptViewList() || Exit List size=>"+list.size());
		return list;
	}
	public void getallocateTransaction(PaymentBean payform) 
	{
		logger.info("PaymentDAOImpl getallocateTransaction() || Enter");
		try{
			List payList=getTransContract(payform);
			Double a=0.0,b=0.0,c=0.0;
			String updateQry;
			updateQry=getQuery(DBConstants.PAYMENT_SELECT_GETNEXTSNO);
			logger.info("Query=>"+updateQry);
			String serialNo =(String) this.mytemplate.queryForObject(updateQry,String.class);
			logger.info("Result=>"+serialNo);
			payform.setSerial_no(serialNo);
			for(int i=0;i<payList.size();i++)
			{
				PaymentBean form=(PaymentBean)(payList.get(i));
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
						 	Object[] updateArgs = new Object[5];
							updateArgs[0] = form.getAccPremium();
							updateArgs[1] = form.getLogin_id();
							updateArgs[2] = form.getBranchCode();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+form.getAccPremium()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
						 	int res=this.mytemplate.update(updateQry,updateArgs);
						 	logger.info("Udate Result=>"+res);
						 	updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PREMSETSTAUS);
						 	updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = form.getLogin_id();
							updateArgs[2] = form.getBranchCode();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
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
							Object[] updateArgs = new Object[5];
							updateArgs[0] = form.getAccClaim();
							updateArgs[1] = form.getLogin_id();
							updateArgs[2] = form.getBranchCode();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
							logger.info("Update Query=>"+updateQry);
							logger.info("Args[0]=>"+form.getAccClaim()+"\nArgs[1]=>"+form.getContractno()+"\nArgs[2]=>"+form.getTransactionno());
							int res=this.mytemplate.update(updateQry,updateArgs);
							logger.info("Udate Result=>"+res);
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMSETSTAUS);
							updateArgs = new Object[5];
							updateArgs[0] = "Allocated";
							updateArgs[1] = form.getLogin_id();
							updateArgs[2] = form.getBranchCode();
							updateArgs[3] = form.getContractno();
							updateArgs[4] = form.getTransactionno();
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
			}
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
			updateArgs[1]=payform.getLogin_id();
			updateArgs[2] = payform.getBranchCode();
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
			updateQry=getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTCHKYN);
			logger.info("Update Query=>"+updateQry);
			res=this.mytemplate.update(updateQry);
			logger.info("Result=>"+res);
			
	}
  	catch(Exception exe)
	{
  		exe.printStackTrace();
	}
  	logger.info("PaymentDAOImpl getallocateTransaction() || Exit");
	}
	
	public String ajaxCheck(String checkPC) {
		String selectQry="";
		if("P".equalsIgnoreCase(checkPC))
		{
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRSKPREMCHECKYN);
		}else if("C".equalsIgnoreCase(checkPC))
		{
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETCLAIMPYMTCHECKYN);
		}
		return selectQry;
	}
	
	public String ajaxCheckUpdate(String checkPC,String mode) {
		String updateQry = "";
		if("P".equalsIgnoreCase(checkPC))
		{
			if("CH".equalsIgnoreCase(mode))
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CHECKYNRSKPREM);
			else
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_RSKPREM);
		}else if("C".equalsIgnoreCase(checkPC))
		{
			if("CH".equalsIgnoreCase(mode))
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CHECKYNCLAIMPYMT);
			else
				updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMT);
		}
		logger.info("Query=>"+updateQry);
		return updateQry;
	}
	
	public String ajaxCheck(String contNo,String transNo, String checkPC,String amount,String mode,String payRecNo,String cedId,String brokId,String curId,String type,String branchCode)  {
		logger.info("PaymentDAOImpl ajaxCheck() || Enter");
		String totAmt="";
		try{
			String result=null;
			String selectQry=null;
			if(!"S".equalsIgnoreCase(checkPC)){
				selectQry=ajaxCheck(checkPC);
				logger.info("Query=>"+selectQry);
				logger.info("Arg[0]=>"+contNo);
				logger.info("Arg[1]=>"+transNo);
				result=(String)this.mytemplate.queryForObject(selectQry,new String[]{contNo,transNo},String.class);
				logger.info("Result=>"+result);
			}
			
			String args[]=new String[0];
			if("CH".equalsIgnoreCase(mode))
			{
				args=new String[4];
				if(result==null || "N".equals(result))
				{
					args[0]="Y";
					args[1]=amount;
				}else
				{
					args[0]="";
					args[1]="";
				}
				args[2]=contNo;
				args[3]=transNo;
				int i=0;
				for(String s:args){
					logger.info("Arg["+i+++"]==>"+s);
				}
				int res=this.mytemplate.update(ajaxCheckUpdate(checkPC,mode), args);
				logger.info("Result=>"+res);
			}else if("IN".equalsIgnoreCase(mode))	
			{
				args=new String[3];
					args[0]=amount;
					args[1]=contNo;
					args[2]=transNo;
					int i=0;
					for(String s:args){
						logger.info("Arg["+i+++"]==>"+s);
					}
					int res=this.mytemplate.update(ajaxCheckUpdate(checkPC,mode), args);
					logger.info("Result=>"+res);
			}
			String id="";
			int sign=1;
			if("RT".equalsIgnoreCase(type))
				sign*=(-1);
			if("63".equals(brokId))
			{
				id=cedId;
			}else
			{
				id=brokId;
			}
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETTOTAMT);
			Object[] selectArgs = new Object[11];
			selectArgs[0] = branchCode;
			selectArgs[1] = payRecNo;
			selectArgs[2] = curId;
			selectArgs[3] = curId;
			selectArgs[4] = brokId;
			selectArgs[5] = id;
			selectArgs[6] = sign;
			selectArgs[7] = curId;
			selectArgs[8] = brokId;
			selectArgs[9] = id;
			selectArgs[10] = sign;
			logger.info("Query=>"+selectQry);
			int in=0;
			for(Object s:selectArgs){
				logger.info("Arg["+in+"]=>"+s);
				in++;
			}
			Object temp = this.mytemplate.queryForObject(selectQry, selectArgs, String.class);
			logger.info("Result=>"+temp);
			totAmt=temp==null?"":temp.toString();
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl ajaxCheck() || Exit Result=>"+totAmt);
		return totAmt;
	}
	public Map allocateView(final PaymentBean payform){
		logger.info("PaymentDAOImpl allocateView() || Enter");
		Map viewMap=new HashMap();
		try{
			final ArrayList alllist = new ArrayList();
			final ArrayList allocatedList = new ArrayList();
			final ArrayList revertedList = new ArrayList();
			String[] args = new String[1];
			args[0] = payform.getPay_rec_no();
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSDTLS);
			if(!"".equals(payform.getSerial_no()))
			{
				selectQry = selectQry + " AND SNO='"+payform.getSerial_no()+"'";
			}
			selectQry = selectQry + " ORDER BY SNO DESC";
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+payform.getPay_rec_no());
			List list = this.mytemplate.queryForList(selectQry,args);
			logger.info("Result Size=>"+list.size());
			if(!"".equals(payform.getSerial_no()))
			{	
				selectQry=getQuery(DBConstants.PAYMENT_SELECT_GETBROCEDINGIDS);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+payform.getPay_rec_no());
				logger.info("Args[1]=>"+payform.getBranchCode());
				Map resMap = this.mytemplate.queryForMap(selectQry, new String[] {payform.getPay_rec_no(),payform.getBranchCode()});
				logger.info("Map Size=>"+resMap.size());
				if(resMap.size()>0)
				{
					Object[] selectArgs = new Object[3]; 
					selectArgs[0] = payform.getBranchCode();
					selectArgs[1] = resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString();
					selectArgs[2] = "B";
					selectQry = getQuery(DBConstants.COMMON_SELECT_GETCOMPNAME);
					logger.info("Query=>"+selectQry);
					logger.info("Args[0]=>"+payform.getBranchCode());
					logger.info("Args[1]=>"+resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString());
					logger.info("Args[2]=>"+"B");
					payform.setBrokername(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
					logger.info("Result=>"+payform.getBrokername());
					if("63".equals(resMap.get("BROKER_ID")==null?"":resMap.get("BROKER_ID").toString()))
					{
						selectArgs[0] = payform.getBranchCode();
						selectArgs[1] = resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString();
						selectArgs[2] ="C";
						logger.info("Query=>"+selectQry);
						logger.info("Args[0]=>"+payform.getBranchCode());
						logger.info("Args[1]=>"+ resMap.get("CEDING_ID")==null?"":resMap.get("CEDING_ID").toString());
						logger.info("Args[2]=>"+"C");
						payform.setCedingCo(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
						logger.info("Result=>"+payform.getCedingCo());
					}
				}
				Object[] selectArgs = new Object[2];
				selectArgs[0] = payform.getPay_rec_no();
				selectArgs[1] = payform.getAlloccurrencyid();
				selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETDTLS);
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]==>"+ payform.getPay_rec_no()+"\nArgs[1]==>"+payform.getAlloccurrencyid());
				payform.setAllTillDate(this.mytemplate.queryForObject(selectQry, selectArgs,String.class).toString());
				logger.info("Result=>"+payform.getAllTillDate());
			}else
			{
				payform.setAllTillDate("");
			}
			
			if (list.size()>0) {
				PaymentBean bean;
				for (int i = 0; i < list.size(); i++) {
					bean = new PaymentBean();
					Map resMap = (Map) list.get(i);
					bean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
					bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					payform.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
					bean.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
					bean.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
					bean.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
					bean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
					bean.setPayamount(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
					bean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					payform.setAlloccurrencyid(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
					bean.setStatus(("R".equals(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())?"Reverted":"Allocated"));
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
					Object[] selectArgs = new Object[2];
					selectArgs[0] = payform.getBranchCode();
					selectArgs[1] = bean.getCurrencyValue();
					logger.info("Query=>"+selectQry);
					logger.info("Arg[0]=>"+payform.getBranchCode());
					logger.info("Arg[1]=>"+ bean.getCurrencyValue());
					bean.setCurrencyName(this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString());
					logger.info("Result=>"+bean.getCurrencyName());
					selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETEXCHRATE);
					selectArgs = new Object[2];
					selectArgs[0] = payform.getPay_rec_no();
					selectArgs[1] = bean.getCurrencyValue();
					logger.info("Query=>"+selectQry);
					logger.info("Arg[0]=>"+selectArgs[0]);
					logger.info("Arg[1]=>"+selectArgs[1]);
					bean.setExchangerate(this.mytemplate.queryForObject(selectQry, selectArgs, String.class).toString());
					logger.info("Result=>"+bean.getExchangerate());
					alllist.add(bean);
					if("Reverted".equalsIgnoreCase(bean.getStatus()))
					{
						revertedList.add(bean);
					}else
					{
						allocatedList.add(bean);
					}
				}
			}
			viewMap.put("AllList", alllist);
			logger.info("All List Size=>"+alllist.size());
			viewMap.put("AllocatedList", allocatedList);
			logger.info("Allocated List Size=>"+allocatedList.size());
			viewMap.put("RevertedList", revertedList);
			logger.info("Reverted List Size=>"+revertedList.size());
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl allocateView() || Exit Map Size=>"+viewMap.size());
		return viewMap;
	}
	public void getSecondPageInfo(PaymentBean payform) {
		logger.info("PaymentDAOImpl getSecondPageInfo() || Enter");
		try{
			String[] args = new String[1];
			args[0] = payform.getSerial_no();
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSECONDPAGEDTLS);
			logger.info("Query=>"+selectQry);
			logger.info("Arg[0]=>"+args[0]);
			List list = this.mytemplate.queryForList(selectQry,args);
			logger.info("List Size=>"+list.size());
			double totAmt=0.0;
			double totConRecCur=0.0;
			for (int i = 0; i < list.size(); i++) {
				Map resMap = (Map)list.get(i);
				payform.getRequest().setAttribute("cedingCompany"+(i+1),resMap.get("CURRENCY_ID").toString());
				payform.getRequest().setAttribute("exachange"+(i+1),resMap.get("EXCHANGE_RATE").toString());
				payform.getRequest().setAttribute("payamount"+(i+1),resMap.get("AMOUNT").toString());
				payform.getRequest().setAttribute("rowamount"+(i+1),resMap.get("TOT_AMT").toString());
				totAmt=totAmt+Double.parseDouble(resMap.get("TOT_AMT").toString());
				payform.getRequest().setAttribute("recNo"+(i+1),resMap.get("RECEIPT_NO").toString());//for flag from execute update or insert
				payform.getRequest().setAttribute("setExcRate"+(i+1),resMap.get("SETTLED_EXCRATE").toString());
				payform.getRequest().setAttribute("conRecCur"+(i+1),resMap.get("CONVERTED_RECCUR").toString());
				totConRecCur=totConRecCur+Double.parseDouble(resMap.get("CONVERTED_RECCUR").toString());
				//request.setAttribute("rowamount"+(i+1),request.getParameter("rowamount"+i));
			}
			payform.getRequest().setAttribute("totConRecCur",totConRecCur);
			selectQry=getQuery(DBConstants.PAYMENT_SELECT_GETDIFFAMT);
			logger.info("Select Query=>"+selectQry);
			logger.info("Args[0]=>"+payform.getSerial_no());
			logger.info("Args[1]=>"+payform.getBranchCode());
			String diffAmt=this.mytemplate.queryForObject(selectQry, new String[] {payform.getSerial_no(),payform.getBranchCode()}, String.class).toString();
			logger.info("Result=>"+diffAmt);
			if(diffAmt!=null && diffAmt.length()>0){
				double Amt=Double.parseDouble(diffAmt);
				if(Amt<0)
				{
					Amt=Amt*(-1);
					totAmt=totAmt-Amt;
					payform.setDifftype("M");
				}else
				{
					payform.setDifftype("B");
					totAmt=totAmt+Amt;
				}
				payform.getRequest().setAttribute("txtDiffAmt",Amt);
			}else
			{
				payform.setDifftype("N");
			}
			payform.getRequest().setAttribute("txtTotalAmt",totAmt);
			if(list.size()!=0)
			{
				payform.getRequest().setAttribute("HidRowCnt",list.size());
			}
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl getSecondPageInfo() || Exit");
	}
	public List allocateDetails(final PaymentBean payform)  {
		logger.info("PaymentDAOImpl allocateDetails() || Enter");
		List resList = new ArrayList();
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOCATEDTLS);
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+payform.getPay_rec_no());
			List list=this.mytemplate.queryForList(selectQry,new Object[]{payform.getPay_rec_no()});
			logger.info("List Size=>"+list.size());
			for(int i = 0; i < list.size(); i++) {
				Map resMap = (Map)list.get(i);
		    	 final PaymentBean bean=new PaymentBean();
		    	 bean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
		    	 bean.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
		    	 bean.setCurrencyValue(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString());
		    	// payform.setAlloccurrencyid(rset.getString("CURRENCY_ID"));
		    	 selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
		    	 logger.info("Query=>"+selectQry);
    			 logger.info("Args[0]=>"+payform.getBranchCode());
				 logger.info("Args[1]=>"+resMap.get("CURRENCY_ID"));
		    	 bean.setCurrency(this.mytemplate.queryForObject(selectQry, new String[]{payform.getBranchCode(),resMap.get("CURRENCY_ID").toString()}, String.class).toString());
		    	 logger.info("Result=>"+bean.getCurrency());
		    	 logger.info("Transaction Type===>"+payform.getType());
		    	 if("PT".equalsIgnoreCase(payform.getType())){
		    		 selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTAMTDTLS);
		    	 }else
		    	 {
		    		 selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETRETAMTDTLS);
		    	 }
		    	 Object[] selectArgs = new Object[2];
	    		 selectArgs[0] = bean.getSerial_no();
	    		 selectArgs[1] = bean.getSerial_no();
	    		 logger.info("Query=>"+selectQry);
	    		 logger.info("Args[0]=>"+selectArgs[0]);
	    		 logger.info("Args[1]=>"+selectArgs[1]);
	    		 bean.setAllTillDate(this.mytemplate.queryForObject(selectQry, selectArgs, String.class).toString());
	    		 logger.info("Result=>"+bean.getAllTillDate());
		    	 bean.setPay_rec_no(payform.getPay_rec_no());
		    	 bean.setBroker(payform.getBroker());
		    	 bean.setCedingCo(payform.getCedingCo());
		    	 resList.add(bean);
			}
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl allocateDetails() || Exit Size=>"+resList.size());
		return resList;
	}
	public List reverseInsert(PaymentBean payform)  {
		logger.info("PaymentDAOImpl reverseInsert() || Enter");
		List list=new ArrayList();
		try{
				String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOTRANSDTLS);
				String updateQry;
				if(!"".equals(payform.getSerial_no()))
				{
					selectQry = selectQry + " AND SNO='"+payform.getSerial_no()+"'";
				}
				selectQry = selectQry + " ORDER BY SNO DESC";
				logger.info("Query=>"+selectQry);
				logger.info("Args[0]=>"+payform.getPay_rec_no());
				List resList = this.mytemplate.queryForList(selectQry, new String[]{payform.getPay_rec_no()});
				logger.info("Result List Size=>"+resList.size());
				double a=0.0,b=0.0,c=0.0;
				String curencyId="";
				if (resList.size()>0) {
					for (int i = 0; i < resList.size(); i++) {
						Map resMap = (Map)resList.get(i);
						curencyId=resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
						if("Y".equalsIgnoreCase(resMap.get("STATUS")==null?"":resMap.get("STATUS").toString())){
							PaymentBean pay=new  PaymentBean();
							pay.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
							payform.setAllocateddate(resMap.get("INCEPTION_DATE")==null?"":resMap.get("INCEPTION_DATE").toString());
							selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
							Object[] selectArgs = new Object[2];
							selectArgs[0] = payform.getBranchCode();
							selectArgs[1] = resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString();
							logger.info("Query=>"+selectQry);
							logger.info("Args[0]=>"+selectArgs[0]);
							logger.info("Args[1]=>"+selectArgs[1]);
							String result=this.mytemplate.queryForObject(selectQry,selectArgs,String.class).toString();
							logger.info("Result=>"+result);
							payform.setCurrency(result);
							pay.setCurrency(result);
							pay.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
							pay.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
							pay.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
							pay.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
							updateQry = getQuery(DBConstants.PAYMENT_UPDATE_ALLOCATEDDTLS);
							Object[]  updateArgs = new Object[8];
							updateArgs[0] = "R";
							updateArgs[1] = payform.getReversalDate();
							updateArgs[2] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
							updateArgs[3] = "0";
							updateArgs[4] = payform.getLogin_id();
							updateArgs[5] = payform.getBranchCode();
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
								updateArgs[1] =  payform.getBranchCode();
								updateArgs[2] = payform.getLogin_id();
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
								pay.setNetdue(this.mytemplate.queryForInt(selectQry, selectArgs)+"");
								logger.info("Result=>"+pay.getNetdue());
								a=a+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
							}else
							{
								updateArgs = new Object[6];
								
								updateQry = getQuery(DBConstants.PAYMENT_UPDATE_CLAIMPYMTDTLS);
								updateArgs[0] = "Pending";
								updateArgs[1] = resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString();
								updateArgs[2] =  payform.getBranchCode();
								updateArgs[3] = payform.getLogin_id();
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
								pay.setPayamount(this.mytemplate.queryForInt(selectQry, selectArgs)+"");
								logger.info("Result=>"+pay.getPayamount());
								b=b+Double.parseDouble(resMap.get("PAID_AMOUNT")==null?"":resMap.get("PAID_AMOUNT").toString());
							}
							list.add(pay);
						}
						
					}
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
					updateQry = getQuery(DBConstants.PAYMENT_UPDATE_PYMTRETDTLS);
					Object[] updateArgs = new Object[5];
					updateArgs[0] = c;
					updateArgs[1] = payform.getLogin_id();
					updateArgs[2] = payform.getBranchCode();
					updateArgs[3] = payform.getPay_rec_no();
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
					selectArgs[0] = payform.getPay_rec_no();
					selectArgs[1] = curencyId;
					logger.info("Query=>"+selectQry);
					k=0;
					for(Object obj:selectArgs){
						logger.info("Arg["+k+++"]==>"+obj);
					}
					payform.setAllTillDate(this.mytemplate.queryForInt(selectQry,selectArgs)+"");
					logger.info("Result=>"+payform.getAllTillDate());
				}
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
	  	logger.info("PaymentDAOImpl reverseInsert() || Exit Size=>"+list.size());
		return list;
	}
	
	public List reverseView(final PaymentBean payform)  {
		logger.info("PaymentDAOImpl reverseView() || Enter");
		List resList = new ArrayList();
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPALLTRANDTLS);
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+payform.getSerial_no());
			List list=this.mytemplate.queryForList(selectQry,new Object[]{payform.getSerial_no()});
			logger.info("Result List Size=>"+list.size());
			for(int i = 0; i < list.size(); i++) {
				Map resMap = (Map) list.get(i);
				final PaymentBean bean=new PaymentBean();
		    	 bean.setSerial_no(resMap.get("SNO")==null?"":resMap.get("SNO").toString());
		    	 bean.setAllocateddate(resMap.get("ALLOCATION_DATE")==null?"":resMap.get("ALLOCATION_DATE").toString());
		    	 bean.setReversalDate(resMap.get("REVERSAL_DATE1")==null?"":resMap.get("REVERSAL_DATE1").toString());
		    	 bean.setContractno(resMap.get("CONTRACT_NO")==null?"":resMap.get("CONTRACT_NO").toString());
		    	 bean.setTransactionno(resMap.get("TRANSACTION_NO")==null?"":resMap.get("TRANSACTION_NO").toString());
		    	 bean.setProductname(resMap.get("PRODUCT_NAME")==null?"":resMap.get("PRODUCT_NAME").toString());
		    	 bean.setReversedAmount(resMap.get("REVERSAL_AMOUNT")==null?"":resMap.get("REVERSAL_AMOUNT").toString());
		    	 bean.setType(resMap.get("TYPE")==null?"":resMap.get("TYPE").toString());
		    	 selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETSELCURRENCY);
		    	 logger.info("Query=>"+selectQry);
		    	 logger.info("Args[0]=>"+payform.getBranchCode());
		    	 logger.info("Args[1]=>"+(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()));
		    	 bean.setCurrency(this.mytemplate.queryForObject(selectQry,new String[]{payform.getBranchCode(),(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString())},String.class).toString());
		    	 logger.info("Result=>"+bean.getCurrency());
		    	 selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETALLOAMT);
		    	 logger.info("Query=>"+selectQry);
		    	 logger.info("Args[0]=>"+payform.getSerial_no());
		    	 logger.info("Args[1]=>"+(resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()));
		    	 bean.setAllTillDate(this.mytemplate.queryForObject(selectQry, new String[]{payform.getSerial_no(),resMap.get("CURRENCY_ID")==null?"":resMap.get("CURRENCY_ID").toString()}, String.class).toString());
		    	 logger.info("Result=>"+bean.getAllTillDate());
		    	 bean.setPay_rec_no(payform.getPay_rec_no());
		    	 bean.setBroker(payform.getBroker());
		    	 bean.setCedingCo(payform.getCedingCo());
		    	 resList.add(bean);
			}
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl reverseView() || Exit List Size=>"+resList.size());
		return resList;
	}
	public List getAllocatedStatus(final PaymentBean payform)  {
		logger.info("PaymentDAOImpl getAllocatedStatus || Enter Rec/Pay No=>"+payform.getPay_rec_no());
		List list=null;
		try{
			String selectQry = getQuery(DBConstants.PAYMENT_SELECT_GETPYMTRETSTATUS);
			if(!"".equals(payform.getAlloccurrencyid())){
				selectQry+=" AND A.CURRENCY_ID='"+payform.getAlloccurrencyid()+"'";
			}
			logger.info("Query=>"+selectQry);
			logger.info("Args[0]=>"+payform.getBranchCode());
			logger.info("Args[1]=>"+payform.getPay_rec_no());
			list=this.mytemplate.query(selectQry,new Object[]{payform.getBranchCode(),payform.getPay_rec_no()}, new RowMapper(){
			    public Object mapRow(ResultSet rset, final int arg)throws SQLException 
			    {
			    	 final PaymentBean bean=new PaymentBean();				    	 
			    	 bean.setCurrency(rset.getString("CURRENCY_NAME")==null?"":rset.getString("CURRENCY_NAME"));
			    	 bean.setAllocated(rset.getString("ALLOCATED")==null?"":rset.getString("ALLOCATED"));
			    	 bean.setUtilized(rset.getString("UTILIZED")==null?"":rset.getString("UTILIZED"));	
			    	 bean.setNotUtilized(rset.getString("NOTUTILIZED")==null?"":rset.getString("NOTUTILIZED"));	
			    	 bean.setStatus(rset.getString("STATUS")==null?"":rset.getString("STATUS"));				    	 				    
			       	 return bean;
			    }
			    });
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getAllocatedStatus || Exit List Size=>"+list.size());
		return list;
	}

	public List getReversalInfo(PaymentBean payform)   {
		logger.info("PaymentDAOImpl getReversalInfo || Enter ");
		List list=null;
		try{
			String query=getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO);
			Object[] obj=new Object[0];
			if(StringUtils.isNotBlank(payform.getPay_rec_no()))
			{
				obj=new Object[10];
				obj[8]=payform.getPay_rec_no();
				obj[9]="R";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_PAYNO)+" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}else
			{
				obj=new Object[9];
				obj[8]="Y";
				query+=" "+getQuery(DBConstants.PAYMENT_SELECT_GETREVERSALINFO_STATUS);
			}
			logger.info("Query=>"+query);
			//obj[0]="RT";//("PT".equalsIgnoreCase(payform.getAllocType())?"RT":"PT");
			obj[0]=("PT".equalsIgnoreCase(payform.getTransType())?"RT":"PT");
			obj[1]=payform.getPaymentamount();
			obj[2]=payform.getCurrencyValue();
			obj[3]=payform.getExrate();
			obj[4]=payform.getBranchCode();
			obj[5]=payform.getBranchCode();
			obj[6]=payform.getBranchCode();
			obj[7]=payform.getBranchCode();
			int i=0;
			for(Object o:obj){
				logger.info("obj["+i+"]=>"+o);
				i++;
			}
			list=this.mytemplate.query(query,obj, new RowMapper(){
			    public Object mapRow(ResultSet rset, final int arg)throws SQLException 
			    {
			    	 final PaymentBean bean=new PaymentBean();		
			    	 bean.setPay_rec_no(rset.getString("PAYMENT_RECEIPT_NO")==null?"":rset.getString("PAYMENT_RECEIPT_NO"));
			    	 bean.setBrokername(rset.getString("BROKER")==null?"":rset.getString("BROKER"));
			    	 bean.setBrokerid(rset.getString("BROKER_ID")==null?"":rset.getString("BROKER_ID"));
			    	 bean.setCedingCo(rset.getString("COMPANY_NAME")==null?"":rset.getString("COMPANY_NAME"));
			    	 bean.setCurrencyName(rset.getString("CURRENCY_NAME")==null?"":rset.getString("CURRENCY_NAME"));
			    	 bean.setExrate(rset.getString("EXCHANGE_RATE")==null?"":rset.getString("EXCHANGE_RATE"));
			    	 bean.setPaymentamount(rset.getString("PAID_AMT")==null?"":rset.getString("PAID_AMT"));
			       	 return bean;
			    }
			    });
		}
	  	catch(Exception exe)
		{
	  		exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getReversalInfo || Exit List Size=>"+list.size());
		return list;
	}

	public String getCurrecyAmount(final String branchCode,final String currValu,final String serialNo)
	{
		logger.info("PaymentDAOImpl getCurrecyAmount() || Enter ");
		String  currecyAmount="";
		try{
			String selectQry="";
			String[] args=new String[3];
			args[0]=branchCode;
			args[1]=serialNo;
			args[2]=currValu;
			selectQry = getQuery(DBConstants.PAYMENT_SELECT_CURRECYAMT);
			logger.info("Select Query=>"+selectQry);
			logger.info("Args[0]=>"+args[0]);
			logger.info("Args[1]=>"+args[1]);
			logger.info("Args[2]=>"+args[2]);
			List list = this.mytemplate.queryForList(selectQry, args);
			logger.info("List Size=>"+list.size());
			
			if(list!=null &&list.size()>0) 
			{
				Map resMap = (Map)list.get(0);
				currecyAmount=resMap.get("AMOUNT").toString()+"$"+resMap.get("CURRENCY_NAME").toString();
			}
		}	
	    catch(Exception exe)
		{
	    	exe.printStackTrace();
		}
		logger.info("PaymentDAOImpl getCurrecyAmount() || Exit CurrecyAmount=>"+currecyAmount);
		return currecyAmount;	
	}
}
	