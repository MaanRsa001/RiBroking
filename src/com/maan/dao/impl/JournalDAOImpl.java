package com.maan.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.maan.bean.JournalBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.JournalDAO;

public class JournalDAOImpl extends MyJdbcTemplate implements JournalDAO {
	final org.slf4j.Logger logger = LogUtil.getLogger(JournalDAOImpl.class);
	public List<List<Map<String, Object>>> getJournalViews(JournalBean bean,String branchCode) {
		logger.info("JournalDAOImpl getJournalViews || Enter");
		List<List<Map<String, Object>>> finalList=new ArrayList<List<Map<String, Object>>>();
		try{
			String sql="";
			String sampledate[]=bean.getOpenperiod().split("-");
			String startDate=sampledate[0];
			String endDate=sampledate[1];
			String status=sampledate[2];
			if(status.equalsIgnoreCase("Y")){
				sql=getQuery(DBConstants.PIPE_LINE_JOURNALS);
			}
			else{
				sql=getQuery(DBConstants.PIPE_LINE_JOURNALS1);
			}
			Object[] args=new String[3];
			logger.info("Select Query====>"+sql);
			for(int i=0; i<bean.getSpcCurrencyList().size() ; i++) {
				Map<String,Object> map = bean.getSpcCurrencyList().get(i);
				String spc=map.get("SPC")==null?"":map.get("SPC").toString();
				String cur=map.get("CURRENCY")==null?"":map.get("CURRENCY").toString();
				String year=map.get("UWY")==null?"":map.get("UWY").toString();
				String productId=map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString();
				args = new String[]{spc,cur,year,bean.getJournalID(),startDate,endDate,productId,branchCode,spc,cur,year,bean.getJournalID(),startDate,endDate,productId,branchCode};
				List<Map<String, Object>> result=this.mytemplate.queryForList(sql,args);
				finalList.add(result);
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("JournalDAOImpl getJournalViews || Exit");
		return finalList;
	}

	public List<Map<String, Object>> getOpenPeriodList(String branchCode) {
		logger.info("JournalDAOImpl getOpenPeriodList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			String sql=getQuery(DBConstants.OPEN_PERIOD_LIST);
			Object[] args=new String[1];
			args[0]=branchCode;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("JournalDAOImpl getOpenPeriodList || Exit");
		return result;
	}

	public List<Map<String, Object>> getSpcCurrencyList(JournalBean bean,String branchCode) {
		logger.info("JournalDAOImpl getSpcCurrencyList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			Object[] args=new String[4];
			String sql="";
			String sampledate[]=bean.getOpenperiod().split("-");
			String startDate=sampledate[0];
			String endDate=sampledate[1];
			String status=sampledate[2];
			args[0]=bean.getJournalID();
			args[1]=startDate;
			args[2]=endDate;
			args[3]=branchCode;
			if(status.equalsIgnoreCase("Y")){
			sql=getQuery(DBConstants.SPE_CURRENCT_LIST);
			}
			else{
				sql=getQuery(DBConstants.SPE_CURRENCT_LIST1);
			}
			logger.info("Select Query====>"+sql);
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("JournalDAOImpl getSpcCurrencyList || Exit");
		return result;
	}

	public int insertActiveOpenPeriod(String startDate, String endDate,String branchCode, String status, String journalname,String loginId) {
		logger.info("JournalDAOImpl insertActiveOpenPeriod || Enter");
		int result=1;
		try {
			Object[] args=new String[5];
			args[0]=startDate;
			args[1]=endDate;
			args[2]=branchCode;
			args[3]="~"+journalname+"~";
			args[4]=loginId;
			String sql=getQuery(DBConstants.ACTIVE_OPEN_PERIOD_PROC);
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
		} catch (Exception e) {
			result=0;
			e.printStackTrace();
		}
		logger.info("JournalDAOImpl insertActiveOpenPeriod || End");
		return result;
	}

	public int insertInActiveOpenPeriod(String startDate, String endDate,
			String branchCode, String status, String journalname,String loginId) {
		logger.info("JournalDAOImpl insertInActiveOpenPeriod || Enter");
		int result=1;
		try {
			Object[] args=new String[5];
			args[0]=startDate;
			args[1]=endDate;
			args[2]=branchCode;
			args[3]="~"+journalname+"~";
			args[4]=loginId;
			String sql=getQuery(DBConstants.INACTIVE_OPEN_PERIOD_PROC);
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
			
				/*args=new String[3];
				//args[0]=startDate;
				args[0]=endDate;
				args[1]=endDate;
				args[2]=branchCode;
				sql=getQuery(DBConstants.INSERT_OPEN_PERIOD);
				logger.info("Select Query====>"+sql);
				logger.info("Args==> " + StringUtils.join(args,","));
				this.mytemplate.update(sql,args);*/
				args=new String[3];
				args[0]=startDate;
				args[1]=endDate;
				args[2]=branchCode;
				sql=getQuery(DBConstants.UPDATE_OPEN_PERIOD);
				logger.info("Select Query====>"+sql);
				logger.info("Args==> " + StringUtils.join(args,","));
				this.mytemplate.update(sql,args);
				
			
			
		} catch (Exception e) {
			result=0;
			e.printStackTrace();
		}
		logger.info("JournalDAOImpl insertInActiveOpenPeriod || End");
		return result;
	}

	@SuppressWarnings("unchecked")
	public int getQuaterEndValidation(String sampledate,String branchCode) {
		int count=0;
		String qType,startDate,endDate;
		try {
			String query=getQuery("GET_REPORTING_PERIOD");
			logger.info("Select Query====>"+query);
			logger.info("Args==>1  "+sampledate+"Args==>2  "+sampledate+"Args==>3  "+branchCode+"Args==>4  "+sampledate );
			List list=this.mytemplate.queryForList(query,new Object[]{sampledate,sampledate,branchCode,sampledate});
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object>map=(Map<String, Object>) list.get(i);
					qType=map.get("QTYPE")==null?"":map.get("QTYPE").toString();
					startDate=map.get("STARTDATE")==null?"":map.get("STARTDATE").toString();
					endDate=map.get("ENDDATE")==null?"":map.get("ENDDATE").toString();
					
					query=getQuery(DBConstants.GET_COUNT_PRCL);
					logger.info("Select Query====>"+query);
					logger.info("Args==>1  "+startDate+"Args==>2  "+endDate+"Args==>3  "+branchCode+"Args==>4  "+qType );
					count=this.mytemplate.queryForInt(query, new Object []{startDate,endDate,branchCode,qType});
					if(count>0)
						break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int insertRetroProcess(String startDate, String endDate, String type, String branchCode) {
		logger.info("Enter into insertRetroProcess ");
		int result=1;
		try {
			Object obj[]=new Object[4];
			obj[0]=(String)this.mytemplate.queryForObject(getQuery("GET_END_DATE_YEAR"), new Object[] {endDate,endDate,endDate,endDate}, String.class);;
			obj[1]=endDate;
			obj[2]=branchCode;
			obj[3]=type;
			String query=getQuery("GET_RETRO_PROCESSING");
			logger.info("Query==>"+query);
			logger.info("obj==>"+StringUtils.join(obj,","));
			this.mytemplate.update(query,obj);
		} catch (DataAccessException e) {
			result=0;
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("Exist into insertRetroProcess ");
		return result;
	}

	public String getShortname(String branchCode) {
		String Short="";
		Short=(String)this.mytemplate.queryForObject(getQuery("GET_SHORT_NAME"), new Object[] { branchCode}, String.class);
		return Short;
	}

	public boolean getUserDetails(JournalBean bean) {
		//JournalBean bean=new JournalBean();
		boolean result=false;
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
		return result;
	}

	public void ActivateInActivateLoginUsers(JournalBean bean, String status) {
	try {
		String query=getQuery("UPDATE_USER_STATUS");
		this.mytemplate.update(query, new Object[]{status,bean.getLoginId(),bean.getBranchCode()});
	} catch (Exception e) {
		e.printStackTrace();
	}		
	}

	public boolean getCountOpenPeriod(JournalBean bean) {
		boolean result=false;
		String sampledate[]=bean.getOpenperiod().split("-");
		String sno=sampledate[3];
		String query=getQuery("GET_COUNT_OPEN_PERIOD");
		int count=this.mytemplate.queryForInt(query,new Object[]{sno,bean.getBranchCode()});
		if(count>0)
			result=true;
		return result;
	}

	public void insertManualJV(JournalBean bean) {
		logger.info("JournalDAOImpl insertManualJV || Enter");
		try {String sql="";
			String transactionNo="";
			String revesalNo="";
			String amendId="";
			String value="";
			if(StringUtils.isBlank(bean.getTranId()) && "new".equals(bean.getMode())){
			transactionNo=new DropDownControllor().getSequencePTRT("ManualJournal" ,"","", bean.getBranchCode(),"",bean.getTransactionDate());
			bean.setTranId(transactionNo);
			}else if("reverse".equals(bean.getMode())){
				revesalNo=new DropDownControllor().getSequencePTRT("ManualJournal" ,"","", bean.getBranchCode(),"",bean.getReversalDate());
			}
			sql="SELECT NVL(MAX(AMEND_ID)+1,0) FROM  TTRN_MANUAL_JV WHERE BRANCH_CODE=? AND TRANSACTION_NO=?";
			amendId=this.mytemplate.queryForObject(sql, new Object[]{bean.getBranchCode(),bean.getTranId()},String.class);
			if(bean.getLedgerId()!=null && bean.getLedgerId().size()>0){
			for(int i=0;i<bean.getLedgerId().size();i++){
			if(StringUtils.isNotBlank(bean.getLedgerId().get(i))){
			Object[] args=new String[18];
			//args[0]=bean.getTranId();
			args[1]=bean.getLedClass();
			args[2]=bean.getCurrency();
			args[3]=bean.getExchRate();
			args[4]=bean.getLedgerId().get(i);
			if("edit".equals(bean.getMode()) || "new".equals(bean.getMode())){
				args[0]=bean.getTranId();
				args[5]=bean.getDebitOC().get(i).replaceAll(",", "");
				args[6]=bean.getCreditOC().get(i).replaceAll(",", "");
				args[7]=bean.getDebitDC().get(i).replaceAll(",", "");
				args[8]=bean.getCreditDC().get(i).replaceAll(",", "");
				args[9]=bean.getTransactionDate();
				args[16]="C";
				args[17]=revesalNo;
			}else{
				args[0]=revesalNo;
				args[5]=bean.getCreditOC().get(i).replaceAll(",", "");
				args[6]=bean.getDebitOC().get(i).replaceAll(",", "");
				args[7]=bean.getCreditDC().get(i).replaceAll(",", "");
				args[8]=bean.getDebitDC().get(i).replaceAll(",", "");
				args[9]=bean.getReversalDate();
				args[16]="R";
				args[17]=bean.getTranId();
			}
			args[10]=bean.getNarration();
			args[11]=amendId;
			args[12]=bean.getAmendmentDate();
			args[13]=bean.getBranchCode();
			args[14]=bean.getLoginId();
			args[15]=String.valueOf(i+1);
			value=String.valueOf(i+2);
			sql=getQuery("INSERT_MANUAL_JV");
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
			
			}
			}
			}
			Object[] args=new String[18];
			args[1]=bean.getLedClass();
			args[2]=bean.getCurrency();
			args[3]=bean.getExchRate();
			args[4]="169";
			if("edit".equals(bean.getMode()) || "new".equals(bean.getMode())){
				args[0]=bean.getTranId();
				args[5]="";
				args[6]="";
				args[7]=bean.getExdebitDC().replaceAll(",", "");
				args[8]=bean.getExcreditDC().replaceAll(",", "");
				args[9]=bean.getTransactionDate();
				args[16]="C";
				args[17]=revesalNo;
			}else{
				args[0]=revesalNo;
				args[5]="";
				args[6]="";
				args[7]=bean.getExcreditDC().replaceAll(",", "");
				args[8]=bean.getExdebitDC().replaceAll(",", "");
				args[9]=bean.getReversalDate();
				args[16]="R";
				args[17]=bean.getTranId();
			}
			args[10]=bean.getNarration();
			args[11]=amendId;
			args[12]=bean.getAmendmentDate();
			args[13]=bean.getBranchCode();
			args[14]=bean.getLoginId();
			args[15]=value;
			sql=getQuery("INSERT_MANUAL_JV");
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
			sql=getQuery("UPADATE_REVERSAL_JV");
			logger.info("Select Query====>"+sql);
			this.mytemplate.update(sql,new Object[]{revesalNo,bean.getTranId(),bean.getBranchCode()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("JournalDAOImpl insertInActiveOpenPeriod || End");
		
		
	}

	public List<JournalBean> getLedgerEntryList(JournalBean bean) {
		List<JournalBean> finalList = new ArrayList<JournalBean>();
		logger.info(" getLedgerEntryList() || Enter  ");
		List<Map<String,Object>> ledgerInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_LEDGER_ENTRY_LIST");
			logger.info("Select Query==> " + query);
			logger.info("Args[0]==> " + bean.getBranchCode());
			ledgerInfo=this.mytemplate.queryForList(query,new Object[]{bean.getBranchCode()});
			if(ledgerInfo!=null && ledgerInfo.size()>0){
				for(int i=0;i<ledgerInfo.size();i++){
					Map<String,Object> insMap=ledgerInfo.get(i);
					JournalBean tempBean=new JournalBean();
					tempBean.setTransactionDate((insMap.get("ENRTY_DATE")==null?"":insMap.get("ENRTY_DATE").toString()));
					tempBean.setCurrency((insMap.get("CURRENCY_NAME")==null?"":insMap.get("CURRENCY_NAME").toString()));
					tempBean.setTranId((insMap.get("TRANSACTION_NO")==null?"":insMap.get("TRANSACTION_NO").toString()));
					tempBean.setReversalNo(insMap.get("REVERAL_NO")==null?"":insMap.get("REVERAL_NO").toString());
					if((StringUtils.isNotBlank(bean.getOpstartDate()))&& (StringUtils.isNotBlank(bean.getOpendDate()))){
						if(new DropDownControllor().Validatethree(bean.getBranchCode(), tempBean.getTransactionDate())==0){
							tempBean.setTransOpenperiodStatus("N");
						}else
						{
							tempBean.setTransOpenperiodStatus("Y");
						}
					}if(StringUtils.isBlank(tempBean.getReversalNo())){
						tempBean.setReversalStatus("Y");
					}else{
						tempBean.setReversalStatus("N");
					}
					finalList.add(tempBean);
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return finalList;
	}

	public void getEditLedgerDetails(JournalBean bean) {
		logger.info("getEditLedgerDetails() || Enter");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> ledgerId = new ArrayList<String>();
		List<String> debitOC = new ArrayList<String>();
		List<String> creditOC = new ArrayList<String>();
		List<String> debitDC = new ArrayList<String>();
		List<String> creditDC = new ArrayList<String>();
		double totalcreditOC=0.00;
		double totaldebitOC=0.00;
		double totalcreditDC=0.00;
		double totaldebitDC=0.00;
		double exchangediff=0.00;
		
		try{
			String query=getQuery("GET_LEDGER_EDIT_LIST");
			Object args[] = new Object[2];
			args[0] =bean.getTranId();
			args[1] =bean.getBranchCode();
			logger.info("Select Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> insMap = list.get(i);
					ledgerId.add((insMap.get("LEDGER")==null?"":insMap.get("LEDGER").toString()));
					debitOC.add(insMap.get("DEBIT_OC")==null?"":DropDownControllor.formatter(insMap.get("DEBIT_OC").toString()));
					creditOC.add(insMap.get("CREDIT_OC")==null?"":DropDownControllor.formatter(insMap.get("CREDIT_OC").toString()));
					debitDC.add(insMap.get("DEBIT_DC")==null?"":DropDownControllor.formatter(insMap.get("DEBIT_DC").toString()));
					creditDC.add(insMap.get("CREDIT_DC")==null?"":DropDownControllor.formatter(insMap.get("CREDIT_DC").toString()));
					bean.setTransactionDate((insMap.get("ENRTY_DATE")==null?"":insMap.get("ENRTY_DATE").toString()));
					bean.setCurrency((insMap.get("CURRENCY")==null?"":insMap.get("CURRENCY").toString()));
					bean.setExchRate((insMap.get("EXCHANGE_RATE")==null?"":insMap.get("EXCHANGE_RATE").toString()));
					bean.setLedClass((insMap.get("CLASS")==null?"":insMap.get("CLASS").toString()));
					bean.setNarration((insMap.get("NARRATION")==null?"":insMap.get("NARRATION").toString()));
					bean.setAmendmentDate((insMap.get("AMENDMENT_DATE")==null?"":insMap.get("AMENDMENT_DATE").toString()));
					totaldebitOC+=Double.parseDouble((insMap.get("DEBIT_OC")==null?"0":insMap.get("DEBIT_OC").toString()));
					totalcreditOC+=Double.parseDouble((insMap.get("CREDIT_OC")==null?"0":insMap.get("CREDIT_OC").toString()));
					totaldebitDC+=Double.parseDouble((insMap.get("DEBIT_DC")==null?"0":insMap.get("DEBIT_DC").toString()));
					totalcreditDC+=Double.parseDouble((insMap.get("CREDIT_DC")==null?"0":insMap.get("CREDIT_DC").toString()));
					exchangediff=totaldebitDC-totalcreditDC;
				}
			}
			bean.setLedgerdetailList(list);
			bean.setLedgerId(ledgerId);
			if("edit".equals(bean.getMode())){
				bean.setDebitOC(debitOC);
				bean.setCreditOC(creditOC);
				bean.setDebitDC(debitDC);
				bean.setCreditDC(creditDC);
				bean.setTotaldebitOC(DropDownControllor.formatter(Double.toString(totaldebitOC)));
				bean.setTotalcreditOC(DropDownControllor.formatter(Double.toString(totalcreditOC)));
				bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totaldebitDC)));
				bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totalcreditDC)));
				if(exchangediff<0){
					bean.setExdebitDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totaldebitDC+Math.abs(exchangediff))));
				}else if (exchangediff>0){
					bean.setExcreditDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totalcreditDC+Math.abs(exchangediff))));
				}
			}else{
				bean.setDebitOC(creditOC);
				bean.setCreditOC(debitOC);
				bean.setDebitDC(creditDC);
				bean.setCreditDC(debitDC);
				bean.setTotaldebitOC(DropDownControllor.formatter(Double.toString(totalcreditOC)));
				bean.setTotalcreditOC(DropDownControllor.formatter(Double.toString(totaldebitOC)));
				bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totalcreditDC)));
				bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totaldebitDC)));
				//bean.setTranId("");
				bean.setNarration("Being reversal of journal no "+bean.getTranId()+" dated "+bean.getTransactionDate());
				if(exchangediff<0){
					bean.setExcreditDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totaldebitDC+Math.abs(exchangediff))));
				}else if (exchangediff>0){
					bean.setExdebitDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totalcreditDC+Math.abs(exchangediff))));
					
				}
			}
			bean.setLoopcount(Integer.toString(list.size()));
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getEditLedgerDetails() || Exit");
		
	}

	public List<Map<String, Object>> getjounalList(JournalBean bean) {
		logger.info(" getjounalList() || Enter  ");
		List<Map<String,Object>> journalInfo=new ArrayList<Map<String,Object>>();
		try{
			String query=getQuery("GET_JOURNAL_LIST");
			logger.info("Select Query==> " + query);
			journalInfo=this.mytemplate.queryForList(query);
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
		}
		return journalInfo;
	}

	public void getViewLedgerDetails(JournalBean bean) {
		logger.info("getViewLedgerDetails() || Enter");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> ledgerId = new ArrayList<String>();
		List<String> debitOC = new ArrayList<String>();
		List<String> creditOC = new ArrayList<String>();
		List<String> debitDC = new ArrayList<String>();
		List<String> creditDC = new ArrayList<String>();
		double totalcreditOC=0.00;
		double totaldebitOC=0.00;
		double totalcreditDC=0.00;
		double totaldebitDC=0.00;
		double exchangediff=0.00;
		
		try{
			String query=getQuery("GET_LEDGER_EDIT_LIST");
			Object args[] = new Object[2];
			args[0] =bean.getTranId();
			args[1] =bean.getBranchCode();
			logger.info("Select Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args, ","));
			list = this.mytemplate.queryForList(query,args);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> insMap = list.get(i);
					ledgerId.add((insMap.get("LEDGER")==null?"":insMap.get("LEDGER").toString()));
					debitOC.add(insMap.get("DEBIT_OC")==null?"":DropDownControllor.formatter(insMap.get("DEBIT_OC").toString()));
					creditOC.add(insMap.get("CREDIT_OC")==null?"":DropDownControllor.formatter(insMap.get("CREDIT_OC").toString()));
					debitDC.add(insMap.get("DEBIT_DC")==null?"":DropDownControllor.formatter(insMap.get("DEBIT_DC").toString()));
					creditDC.add(insMap.get("CREDIT_DC")==null?"":DropDownControllor.formatter(insMap.get("CREDIT_DC").toString()));
					bean.setTransactionDate((insMap.get("ENRTY_DATE")==null?"":insMap.get("ENRTY_DATE").toString()));
					bean.setCurrency((insMap.get("CURRENCY")==null?"":insMap.get("CURRENCY").toString()));
					bean.setExchRate((insMap.get("EXCHANGE_RATE")==null?"":insMap.get("EXCHANGE_RATE").toString()));
					bean.setLedClass((insMap.get("CLASS")==null?"":insMap.get("CLASS").toString()));
					bean.setNarration((insMap.get("NARRATION")==null?"":insMap.get("NARRATION").toString()));
					bean.setAmendmentDate((insMap.get("AMENDMENT_DATE")==null?"":insMap.get("AMENDMENT_DATE").toString()));
					bean.setReversalStatus((insMap.get("STATUS")==null?"":insMap.get("STATUS").toString()));
					totaldebitOC+=Double.parseDouble((insMap.get("DEBIT_OC")==null?"0":insMap.get("DEBIT_OC").toString()));
					totalcreditOC+=Double.parseDouble((insMap.get("CREDIT_OC")==null?"0":insMap.get("CREDIT_OC").toString()));
					totaldebitDC+=Double.parseDouble((insMap.get("DEBIT_DC")==null?"0":insMap.get("DEBIT_DC").toString()));
					totalcreditDC+=Double.parseDouble((insMap.get("CREDIT_DC")==null?"0":insMap.get("CREDIT_DC").toString()));
					exchangediff=totaldebitDC-totalcreditDC;
				}
			}
			bean.setLedgerdetailList(list);
			bean.setLedgerId(ledgerId);
			if("C".equals(bean.getReversalStatus())){
				bean.setDebitOC(debitOC);
				bean.setCreditOC(creditOC);
				bean.setDebitDC(debitDC);
				bean.setCreditDC(creditDC);
				bean.setTotaldebitOC(DropDownControllor.formatter(Double.toString(totaldebitOC)));
				bean.setTotalcreditOC(DropDownControllor.formatter(Double.toString(totalcreditOC)));
				bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totaldebitDC)));
				bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totalcreditDC)));
				if(exchangediff<0){
					bean.setExdebitDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totaldebitDC+Math.abs(exchangediff))));
				}else if (exchangediff>0){
					bean.setExcreditDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totalcreditDC+Math.abs(exchangediff))));
				}
				
			}else{
				bean.setDebitOC(creditOC);
				bean.setCreditOC(debitOC);
				bean.setDebitDC(creditDC);
				bean.setCreditDC(debitDC);
				bean.setTotaldebitOC(DropDownControllor.formatter(Double.toString(totalcreditOC)));
				bean.setTotalcreditOC(DropDownControllor.formatter(Double.toString(totaldebitOC)));
				bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totalcreditDC)));
				bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totaldebitDC)));
				if(exchangediff<0){
					bean.setExcreditDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(totaldebitDC+Math.abs(exchangediff))));
				}else if (exchangediff>0){
					bean.setExdebitDC(DropDownControllor.formatter(Double.toString(Math.abs(exchangediff))));
					bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(totalcreditDC+Math.abs(exchangediff))));
					
				}
			}
			bean.setLoopcount(Integer.toString(list.size()));
			
		}
		catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		logger.info("getViewLedgerDetails() || Exit");
		
	}

	public String getForExDiffName(String branchCode) {
		String diffName="";
		try {
			diffName=(String)this.mytemplate.queryForObject(getQuery("GET_FOR_EX_DIFF_NAME"), new Object[] { branchCode}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffName;
	}
	public List<Map<String, Object>> getStartDateList(String branchCode) {
		logger.info("JournalDAOImpl getStartDateList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			String sql=getQuery("GET_OPEN_PERIOD_STARTDATE");
			Object[] args=new String[1];
			args[0]=branchCode;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("JournalDAOImpl getStartDateList || Exit");
		return result;
	}
	public List<Map<String, Object>> getEndDateList(String branchCode) {
		logger.info("JournalDAOImpl getEndDateList || Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			String sql=getQuery("GET_OPEN_PERIOD_ENDDATE");
			Object[] args=new String[1];
			args[0]=branchCode;
			logger.info("Select Query====>"+sql);
			logger.info("Args==> " + StringUtils.join(args,","));
			result=this.mytemplate.queryForList(sql,args);
			logger.info("Result Size====>"+result.size());
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		logger.info("JournalDAOImpl getEndDateList || Exit");
		return result;
	}
	public String getStartDateStatus(JournalBean bean) {
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
	public String getEndDateStatus(JournalBean bean) {
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
