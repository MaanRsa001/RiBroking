package com.maan.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.bean.FaculitivesBean;
import com.maan.bean.JournalBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.CommonCalculation;
import com.maan.service.JournalService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class JournalAction extends ActionSupport implements ModelDriven<JournalBean>{
	private static final long serialVersionUID = 1L;
	final static org.slf4j.Logger logger = LogUtil.getLogger(JournalAction.class);
	final HttpServletRequest request = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = session.get("BRANCH_CODE")==null?"":session.get("BRANCH_CODE").toString();
	String userId = session.get("UserId")==null?"":session.get("UserId").toString();
	private String countryId=(String) session.get("countryId")==null?"":session.get("countryId").toString();
	JournalBean bean=new JournalBean();
	FaculitivesBean bean1=new FaculitivesBean();
	CommonCalculation calcu = new CommonCalculation();
	JournalService service=new JournalService();
	private InputStream inputStream;

	public JournalBean getModel() {
		return bean;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public List<Map<String,Object>> getJounalList(){
		return service.getjounalList(bean);
	}
	public List<Map<String,Object>> getOpenperiodList(){
		return service.getOpenPeriodList(session.get("BRANCH_CODE").toString());
	}
	public List<Map<String,Object>> getStartDateList(){
		return service.getStartDateList(session.get("BRANCH_CODE").toString());
	}
	public List<Map<String,Object>> getEndDateList(){
		return service.getEndDateList(session.get("BRANCH_CODE").toString());
	}
	public String getForexDiffName(){
		return service.getForExDiffName(branchCode);
	}
	public String journalHome(){
		
		if(StringUtils.isNotBlank(bean.getProcessStatus())){
			bean.setLoginId(userId);
			validatejournal();
			if (!hasActionErrors()) 
			{
				String sampledate[]=bean.getOpenperiod().split("-");
				String startDate=sampledate[0];
				String endDate=sampledate[1];
				String status=sampledate[2];
				if(status.equalsIgnoreCase("Y")){
					if(bean.getProcessStatus().equalsIgnoreCase("process")){
					
						int count=service.insertInActiveOpenPeriod(startDate,endDate,branchCode,status,bean.getJournalname(),userId);
						bean.setOpenperiod(sampledate[0]+"-"+sampledate[1]+"-N-"+sampledate[3]);
						bean.setProcessStatus("process");
						String transactionNo=new DropDownControllor().getTransactionNo(startDate,endDate,branchCode);
						if(count==1){
							addActionError(getText("process.completed.success",new String[]{transactionNo}));
						}else{
							addActionError(getText("process.completed.failed"));
						}
						
					}else{
						int count=service.insertActiveOpenPeriod(startDate,endDate,branchCode,status,bean.getJournalname(),userId);
						if(count==1){
							addActionError(getText("inprocess.completed.success"));
						}else{
							addActionError(getText("inprocess.completed.failed"));
						}
						
						bean.setProcessStatus("");
					}
				}	
			//bean.setParttoShow("processDownload");
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
			}
		}
		//bean.setOpenperiodList(service.getOpenPeriodList(session.get("BRANCH_CODE").toString()));
		//bean.setStartDateList(service.getStartDateList(session.get("BRANCH_CODE").toString()));
		//bean.setEndDateList(service.getEndDateList(session.get("BRANCH_CODE").toString()));
		return "journal";
	}
	public String getjournalView(){
		String forword="";
		bean.setShortname(service.getShortname(branchCode));
		validateviewjournal();
		if (!hasActionErrors()) {
		bean.setSpcCurrencyList(service.getSpcCurrencyList(bean,branchCode));
		bean.setJournalViewList(service.getJournalViews(bean,branchCode));
		if(bean.getJournalViewList().size()==0){
			addActionError(getText("errors.list.view"));
		}
		forword= "journalView";
		}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			//bean.setOpenperiodList(service.getOpenPeriodList(session.get("BRANCH_CODE").toString()));
			//bean.setStartDateList(service.getStartDateList(session.get("BRANCH_CODE").toString()));
			//bean.setEndDateList(service.getEndDateList(session.get("BRANCH_CODE").toString()));
			forword= "journal"; 
		}
		return forword;
	}
	/*public String getjournalPrint(){
		bean.setSpcCurrencyList(service.getSpcCurrencyList(bean,branchCode));
		bean.setJournalViewList(service.getJournalViews(bean,branchCode));
		return "journalPrint";
	}*/
	public String getjournalPrint(){
		JasperReports reports=new JasperReports();
		try {
			bean.setShortname(service.getShortname(branchCode));
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
			String date = sdf.format(cal.getTime());
			String filePath1 = "/documents/PDF/JournalViewReport"+date+".pdf";
			String filePath=this.request.getSession().getServletContext().getRealPath(filePath1);
				reports.getJournalReport(bean,branchCode,filePath);
				session.put("pdfFilePath",filePath1);
			}catch(Exception e){
				
			}
		return "redirectPDFReport";
	}
	public String pdf() {
		String forward = "pdfJournal";
		try {
			bean.setFileName((String)session.get("pdfFilePath"));
			session.remove("pdfFilePath");
			logger.info("PDF Report File Loc==> " + bean.getFileName());
			if(StringUtils.isNotBlank(bean.getFileName())) {
				String filePath = this.request.getSession().getServletContext().getRealPath(bean.getFileName());
				File file=new File(filePath);
				if(file.exists()){
					//inputStream=new FileInputStream(file);
					//forward = "stream";
					forward = "viewPDF";
				}
			}
		}
		catch(Exception exception) {
			logger.debug(""+exception);
		}
		return forward;
	}
	public String journalReport(){
		String forword="";
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		JasperReports reports=new JasperReports();
		validatejournal1();
		if (!hasActionErrors()) {
		String sampledate[]=bean.getOpenperiod().split("-");
		String startDate=sampledate[0];
		String endDate=sampledate[1];
		String status=sampledate[2];
		/*if(status.equalsIgnoreCase("Y")){
			//service.ActivateInActivateLoginUsers(bean,"R");
			service.insertActiveOpenPeriod(startDate,endDate,branchCode,status,bean.getJournalname(),userId);
			//service.ActivateInActivateLoginUsers(bean,"Y");
			if(bean.getProcessStatus().equalsIgnoreCase("process")){
				service.ActivateInActivateLoginUsers(bean,"R");
				String sampleendDate[]=sampledate[1].split("/");
				String sampleendDate1=sampleendDate[0]+"/"+sampleendDate[1];
				if("31/03".equals(sampleendDate1) ||"30/06".equals(sampleendDate1) ||"30/09".equals(sampleendDate1) ||"31/12".equals(sampleendDate1)){
					//service.insertRetroProcess(sampledate[1],branchCode, sampleendDate1, sampleendDate1);
					service.insertRetroProcess(sampledate[1],sampledate[1],"F",branchCode);
				}
				service.insertInActiveOpenPeriod(startDate,endDate,branchCode,status,bean.getJournalname().replaceAll(",", "~").replaceAll("\\s+",""));
				service.ActivateInActivateLoginUsers(bean,"Y");
				addActionError(getText("process.completed.success"));
			}
		}*/
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
			String date = sdf.format(cal.getTime());
			bean.setFileName("Journal " + date + ".xls");
			
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			reports.getJournalReport(startDate,endDate,branchCode,status,bean.getJournalname(),bean.getProcessStatus(),"xls",bos);
			inputStream = new ByteArrayInputStream(bos.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
		}
		forword= "stream";
		}
		else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			//bean.setOpenperiodList(service.getOpenPeriodList(session.get("BRANCH_CODE").toString()));
			//bean.setStartDateList(service.getStartDateList(session.get("BRANCH_CODE").toString()));
			//bean.setEndDateList(service.getEndDateList(session.get("BRANCH_CODE").toString()));
			forword= "journal"; 
		}
		return forword;
	}
	public void validatejournal(){
		bean.setBranchCode(branchCode);
		if(StringUtils.isBlank(bean.getOpenperiod())){
			addActionError(getText("errors.openperiod.date"));
		}
		if(StringUtils.isBlank(bean.getJournalname())){
			//addActionError(getText("errors.check.box"));
		}if(service.getCountOpenPeriod(bean)){
			addActionError(getText("errors.open.period.count"));
		}if(service.getUserDetails(bean)){
			addActionError(getText("error.retro.user.list",new String[]{bean.getLoginIdList()}));
		}
		
		if(StringUtils.isNotBlank(bean.getProcessStatus())&& bean.getProcessStatus().equals("process")){
		String sampledate[]=bean.getOpenperiod().split("-");
		String sampleendDate[]=sampledate[1].split("/");
		String sampleendDate1=sampleendDate[0]+"/"+sampleendDate[1];
		if("31/03".equals(sampleendDate1) ||"30/06".equals(sampleendDate1) ||"30/09".equals(sampleendDate1) ||"31/12".equals(sampleendDate1)){
			if(service.getQuaterEndValidation(sampledate[1],branchCode)!=0){
				addActionError(getText("error.quaterend.date"));
			}
		}
		}
	}
	public void validatejournal1(){
		if(StringUtils.isBlank(bean.getOpenperiod())){
			addActionError(getText("errors.openperiod.date"));
		}
		if(StringUtils.isBlank(bean.getJournalname())){
			//addActionError(getText("errors.check.box"));
		}if(!bean.getProcessStatus().equalsIgnoreCase("process")){
		if(service.getUserDetails(bean)){
			addActionError(getText("error.retro.user.list",new String[]{bean.getLoginIdList()}));
		}
		}if(StringUtils.isNotBlank(bean.getOpenperiod())){
		if(service.getCountOpenPeriod(bean)){
			addActionError(getText("errors.open.period.count"));
		}
		}
		
	}
	public void validateviewjournal(){
		if(StringUtils.isBlank(bean.getOpenperiod())){
			addActionError(getText("errors.openperiod.date"));
		}
		
	}
	public String retroInsert(){
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		validationretroInsert();
		if(!hasActionErrors()){
			String sampledate[]=bean.getOpenperiod().split("-");
			String startDate=sampledate[0];
			String endDate=sampledate[1];
			service.ActivateInActivateLoginUsers(bean,"R");	
		int count=service.insertRetroProcess(startDate,endDate,"I",branchCode);
		service.ActivateInActivateLoginUsers(bean,"Y");
		if(count==1){
			addActionError(getText("Retro Processing is successful"));
		}else{
			addActionError(getText("Retro Processing is Failed"));
		}
		
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
		}
		//bean.setOpenperiodList(service.getOpenPeriodList(session.get("BRANCH_CODE").toString()));
		//bean.setStartDateList(service.getStartDateList(session.get("BRANCH_CODE").toString()));
		//bean.setEndDateList(service.getEndDateList(session.get("BRANCH_CODE").toString()));
		return "journal";
	}
	public void validationretroInsert(){
		if(service.getUserDetails(bean)){
			addActionError(getText("error.retro.user.list",new String[]{bean.getLoginIdList()}));
		}
		/*bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		if(StringUtils.isBlank(bean.getRetro_period_Date())){
			addActionError(getText("error.retro.period.date"));
		}
		if(StringUtils.isBlank(bean.getRetro_period_Year())){
			addActionError(getText("error.retro.period.year"));
		}if(StringUtils.isNotBlank(bean.getRetro_period_Date()) && StringUtils.isNotBlank(bean.getRetro_period_Year())){
		if(dropDownControllor.Validatethree(bean1.getOpstartDate(),bean1.getOpendDate(), bean.getRetro_period_Date()+"/"+bean.getRetro_period_Year())==0){
			addActionError(getText("error.retro.open.period"));
		}
		}*/
}
	public String newJv(){
		bean.setMode("new");
		bean.setShortname(service.getShortname(branchCode));
		bean.setForexDiffName(service.getForExDiffName(branchCode));
		 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		 bean.setLedgerList(new DropDownControllor().getLedgerDropDown(branchCode));
		 bean.setLedgerdeptList(new DropDownControllor().getLedgerDeptDropDown(branchCode));
		 Map<String,Object> doubleMap = new HashMap<String,Object>();
		 List<Map<String,Object>> ledgerdetailList=new ArrayList<Map<String,Object>>();
		 for(int i=0;i<5;i++){
			 doubleMap.put("one",new Double(1.0));
			 ledgerdetailList.add(doubleMap);
			 bean.setLoopcount(Integer.toString(i+1));
		 }
		 bean.setLedgerdetailList(ledgerdetailList);
		return "manualJournal";
	}
	public String manualJV(){
		bean.setMode("list");
		bean.setBranchCode(branchCode);
		bean1.setBranchCode(branchCode);
		new DropDownControllor().getOpenPeriod(bean1);
		bean.setOpstartDate(bean1.getOpstartDate());
		bean.setOpendDate(bean1.getOpendDate());
		bean.setLedgerEntryList(service.getLedgerEntryList(bean));
		if(bean.getLedgerEntryList().size()==0){
			newJv();
		}
		
		return "manualJournal";
	}
	public String insertmanualJV(){
		bean1.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(branchCode));
		bean.setForexDiffName(service.getForExDiffName(branchCode));
		new DropDownControllor().getOpenPeriod(bean1);
		bean.setOpstartDate(bean1.getOpstartDate());
		bean.setOpendDate(bean1.getOpendDate());
		validationManual();
		bean.setBranchCode(branchCode);
		bean.setLoginId(userId);
		if(!hasActionErrors()){
		    service.insertManualJV(bean);
		    bean.setMode("list");
		    bean.setLedgerEntryList(service.getLedgerEntryList(bean));
		}else{
			logger.info("##########Validation Message Start###########");
			Iterator<String> error = getActionErrors().iterator();
			while(error.hasNext()){
				logger.info(error.next());
			}
			logger.info("##########Validation Message End###########");
			bean.setShortname(service.getShortname(branchCode));
			 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
			 bean.setLedgerList(new DropDownControllor().getLedgerDropDown(branchCode));
			 bean.setLedgerdeptList(new DropDownControllor().getLedgerDeptDropDown(branchCode));
			 Map<String,Object> doubleMap = new HashMap<String,Object>();
			 List<Map<String,Object>> ledgerdetailList=new ArrayList<Map<String,Object>>();
			 for(int i=0;i<bean.getLedgerId().size();i++){
				 doubleMap.put("one",new Double(1.0));
				 ledgerdetailList.add(doubleMap);
				 bean.setLoopcount(Integer.toString(i+1));
			 }
			 bean.setLedgerdetailList(ledgerdetailList);
		}
		
		
		return "manualJournal";
		
	}
	private void validationManual() {
		double totDebitOC=0;
		double totCreditOC=0;
		double totDebitDC=0;
		double totCreditDC=0;
		double exchangediff=0;
		final Validation val = new Validation();
		if(StringUtils.isNotBlank(bean.getTranId()) && "edit".equals(bean.getMode())){
			if(StringUtils.isBlank(bean.getAmendmentDate())){
				addActionError(getText("error.camend.date"));
			}else  if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getAmendmentDate()))) {
				addActionError(getText("error.amend.date.invalid"));
			}if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getAmendmentDate()).equalsIgnoreCase("")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getAmendmentDate())==0){
					addActionError(getText("errors.open.period.date.trans.amend",new String[] {bean1.getOpenPeriodDate()}));
				}
				}
		}
		if("edit".equals(bean.getMode()) || "new".equals(bean.getMode())){
		if(StringUtils.isBlank(bean.getTransactionDate())){
			addActionError(getText("error.transactio.date"));
		}else  if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getTransactionDate()))) {
			addActionError(getText("error.transactio.date.invalid"));
		}if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getTransactionDate()).equalsIgnoreCase("")){
			if(new DropDownControllor().Validatethree(branchCode, bean.getTransactionDate())==0){
				addActionError(getText("errors.open.period.date.trans",new String[] {bean1.getOpenPeriodDate()}));
			}
			}
		}else{
			if(StringUtils.isBlank(bean.getReversalDate())){
				addActionError(getText("error.reversal.date"));
			}else  if ("INVALID".equalsIgnoreCase(val.checkDate(bean.getReversalDate()))) {
				addActionError(getText("error.reversal.date.invalid"));
			}if(!val.isNull(bean1.getOpstartDate()).equalsIgnoreCase("")&& !val.isNull(bean1.getOpendDate()).equalsIgnoreCase("") && !val.isNull(bean.getReversalDate()).equalsIgnoreCase("")){
				if(new DropDownControllor().Validatethree(branchCode, bean.getReversalDate())==0){
					addActionError(getText("errors.open.period.date.reversal",new String[] {bean1.getOpenPeriodDate()}));
				}
				}
		}
		if(StringUtils.isBlank(bean.getCurrency())){
			addActionError(getText("error.currency"));
		}
		if(StringUtils.isBlank(bean.getExchRate())){
			addActionError(getText("error.exchange.rate"));
		}
		if(StringUtils.isBlank(bean.getLedClass())){
			addActionError(getText("error.class"));
		}
		for(int i=0;i<bean.getLedgerId().size();i++){
			if(i==0 || StringUtils.isNotBlank(bean.getLedgerId().get(i)) || StringUtils.isNotBlank(bean.getDebitOC().get(i)) || StringUtils.isNotBlank(bean.getCreditOC().get(i)) || StringUtils.isNotBlank(bean.getDebitDC().get(i)) || StringUtils.isNotBlank(bean.getCreditDC().get(i))){
			if(StringUtils.isBlank(bean.getLedgerId().get(i)) ){
				addActionError(getText("error.ledgerid",new String[] { String.valueOf(i + 1) }));
			}
			if(StringUtils.isBlank(bean.getDebitOC().get(i)) &&StringUtils.isBlank(bean.getCreditOC().get(i))  ){
				if(StringUtils.isBlank(bean.getDebitOC().get(i)) ){
					addActionError(getText("error.debitOC",new String[] {String.valueOf(i + 1) }));
				}if(StringUtils.isBlank(bean.getCreditOC().get(i)) ){
					addActionError(getText("error.creditOC",new String[] { String.valueOf(i + 1) }));
				}
			}
			if(StringUtils.isBlank(bean.getDebitDC().get(i)) &&StringUtils.isBlank(bean.getCreditDC().get(i))  ){
				if(StringUtils.isBlank(bean.getDebitDC().get(i)) ){
					addActionError(getText("error.debitDC",new String[] {bean.getShortname(), String.valueOf(i + 1) }));
				}if(StringUtils.isBlank(bean.getCreditDC().get(i)) ){
					addActionError(getText("error.creditDC",new String[] {bean.getShortname(), String.valueOf(i + 1) }));
				}
			} 
			if(StringUtils.isNotBlank(bean.getCreditDC().get(i)) ){
				String ans = calcu.calculateManualJournal(bean,"CreditDC",i);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getCreditDC().get(i).replace(",", ""))){
					this.addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.getCreditDC().set(i,ans);
				}
			}
			if(StringUtils.isNotBlank(bean.getDebitDC().get(i)) ){
				String ans = calcu.calculateManualJournal(bean,"DebitDC",i);
				if(Double.parseDouble(ans)!=Double.parseDouble(bean.getDebitDC().get(i).replace(",", ""))){
					this.addActionError(getText("error.calcul.mistake"));
					logger.info("Insertion Failed. Please retry. If problem persists, please contact support.");
				}else{
					bean.getDebitDC().set(i,ans);
				}
			}
			totDebitOC +=Double.parseDouble(StringUtils.isBlank(bean.getDebitOC().get(i))?"0":bean.getDebitOC().get(i).replaceAll(",", ""));
			totCreditOC +=Double.parseDouble(StringUtils.isBlank(bean.getCreditOC().get(i))?"0":bean.getCreditOC().get(i).replaceAll(",", ""));
			totDebitDC +=Double.parseDouble(StringUtils.isBlank(bean.getDebitDC().get(i))?"0":bean.getDebitDC().get(i).replaceAll(",", ""));
			totCreditDC +=Double.parseDouble(StringUtils.isBlank(bean.getCreditDC().get(i))?"0":bean.getCreditDC().get(i).replaceAll(",", ""));
		}
		}
		exchangediff+=totDebitDC-totCreditDC;
		if(exchangediff<0){
			totDebitDC=totDebitDC+Math.abs(exchangediff);
		}else if (exchangediff>0){
			totCreditDC=totCreditDC+Math.abs(exchangediff);
		}
		if(totDebitOC!=totCreditOC){
			addActionError(getText("error.totaloc"));
		}
		if(totDebitDC!=totCreditDC){
			addActionError(getText("error.totaldc",new String[] {bean.getShortname()}));
		}
		if(StringUtils.isBlank(bean.getNarration())){
			addActionError(getText("error.narration"));
		}
	}
	public String getEditLedgerDetails()
	{
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(branchCode));
		bean.setForexDiffName(service.getForExDiffName(branchCode));
		 bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		 bean.setLedgerList(new DropDownControllor().getLedgerDropDown(branchCode));
		 bean.setLedgerdeptList(new DropDownControllor().getLedgerDeptDropDown(branchCode));
		 if("view".equals(bean.getMode())){
			 service.getViewLedgerDetails(bean);
		 }else{
			 service.getEditLedgerDetails(bean);
		 }
		return "manualJournal";
	}
	
	public String deleteJv(){
		bean.setBranchCode(branchCode);
		bean.setShortname(service.getShortname(branchCode));
		bean.setForexDiffName(service.getForExDiffName(branchCode));
		bean.setOrginalCurrency(new DropDownControllor().getCurrencyMasterDropDown(branchCode, countryId));
		bean.setLedgerList(new DropDownControllor().getLedgerDropDown(branchCode));
		bean.setLedgerdeptList(new DropDownControllor().getLedgerDeptDropDown(branchCode));
		List<String> ledger=new ArrayList<String>();
		List<String> deboc=new ArrayList<String>();
		List<String> crcoc=new ArrayList<String>();
		List<String> debdc=new ArrayList<String>();
		List<String> crcdc=new ArrayList<String>();
		double detotalOc=0.00;
		double detotalDc=0.00;
		double crtotalOc = 0.00;
		double crtotalDc = 0.00;
		double exchangediff=0.00;
		int j=1;
		//bean.getLedgerId().remove(Integer.parseInt(bean.getDeleteId())-1);
		String [] a =bean.getLoopcount().split(",");
		int count = Integer.parseInt(bean.getLoopcount())-1;
		for(int k=0;k<count;k++){
			int value=Integer.parseInt(bean.getDeleteId());
			if(k<value){
				ledger.add(bean.getLedgerId().get(k));
				deboc.add(bean.getDebitOC().get(k));
				crcoc.add(bean.getCreditOC().get(k));
				debdc.add(bean.getDebitDC().get(k));
				crcdc.add(bean.getCreditDC().get(k));
				detotalOc +=Double.parseDouble(StringUtils.isBlank(bean.getDebitOC().get(k))?"0.00":bean.getDebitOC().get(k).replaceAll(",", ""));
				detotalDc+=Double.parseDouble(StringUtils.isBlank(bean.getDebitDC().get(k))?"0.00":bean.getDebitDC().get(k).replaceAll(",", ""));
				crtotalOc+=Double.parseDouble(StringUtils.isBlank(bean.getCreditOC().get(k))?"0.00":bean.getCreditOC().get(k).replaceAll(",", ""));
				crtotalDc+=Double.parseDouble(StringUtils.isBlank(bean.getCreditDC().get(k))?"0.00":bean.getCreditDC().get(k).replaceAll(",", ""));
			}
			else {
				ledger.add(bean.getLedgerId().get(j));
				deboc.add(bean.getDebitOC().get(j));	
				crcoc.add(bean.getCreditOC().get(j));
				debdc.add(bean.getDebitDC().get(j));	
				crcdc.add(bean.getCreditDC().get(j));
				detotalOc +=Double.parseDouble(StringUtils.isBlank(bean.getDebitOC().get(j))?"0.00":bean.getDebitOC().get(j).replaceAll(",", ""));
				detotalDc+=Double.parseDouble(StringUtils.isBlank(bean.getDebitDC().get(j))?"0.00":bean.getDebitDC().get(j).replaceAll(",", ""));
				crtotalOc+=Double.parseDouble(StringUtils.isBlank(bean.getCreditOC().get(j))?"0.00":bean.getCreditOC().get(j).replaceAll(",", ""));
				crtotalDc+=Double.parseDouble(StringUtils.isBlank(bean.getCreditDC().get(j))?"0.00":bean.getCreditDC().get(j).replaceAll(",", ""));
			}
			j++;
		}
		exchangediff+=detotalDc-crtotalDc;
		
		bean.setLedgerId(ledger);
		bean.setDebitOC(deboc);
		bean.setDebitDC(debdc);
		bean.setCreditOC(crcoc);
		bean.setCreditDC(crcdc);
		bean.setTotaldebitOC(DropDownControllor.formatter(Double.toString(detotalOc))) ;
		bean.setTotalcreditOC(DropDownControllor.formatter(Double.toString(crtotalOc)));
		bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(detotalDc)));
		bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(crtotalDc)));
		if(exchangediff<0){
			bean.setExdebitDC(Double.toString(Math.abs(exchangediff)));
			bean.setTotaldebitDC(DropDownControllor.formatter(Double.toString(detotalDc+Math.abs(exchangediff))));
		}else if (exchangediff>0){
			bean.setExcreditDC(Double.toString(Math.abs(exchangediff)));
			bean.setTotalcreditDC(DropDownControllor.formatter(Double.toString(crtotalDc+Math.abs(exchangediff))));
		}
		Map<String,Object> doubleMap = new HashMap<String,Object>();
		 List<Map<String,Object>> ledgerdetailList=new ArrayList<Map<String,Object>>();
		 for(int i=0;i<bean.getLedgerId().size();i++){
			 doubleMap.put("one",new Double(1.0));
			 ledgerdetailList.add(doubleMap);
			 bean.setLoopcount(Integer.toString(i+1));
		 }
		 bean.setLedgerdetailList(ledgerdetailList);
		
		 return "manualJournal";	
		
	
	}
	public String useManualpdf() {
		String forward = "pdfJournal";
		try {
			bean.setFileName("/documents/User Manual/User Manual.pdf");
			if(StringUtils.isNotBlank(bean.getFileName())) {
				String filePath = this.request.getSession().getServletContext().getRealPath(bean.getFileName());
				File file=new File(filePath);
				if(file.exists()){
					forward = "viewPDF";
				}
			}
		}
		catch(Exception exception) {
			logger.debug(""+exception);
		}
		return forward;
		
	}
	public String getjournalDownload(){
		String forward="";
		JournalDownloadValidation();
		if(!hasActionErrors()){
			forward="journalDownload";
		}else{
			forward="journal";
		}
		return forward;
	}
	private void JournalDownloadValidation() {
		bean.setBranchCode(branchCode);
		if(StringUtils.isBlank(bean.getStartDate())){
			addActionError("Please Enter Start Date");
		}
		if(StringUtils.isBlank(bean.getEndDate())){
			addActionError("Please Enter End Date");
		}
		if(StringUtils.isNotBlank(bean.getStartDate()) && StringUtils.isNotBlank(bean.getEndDate())){
			if (Validation.ValidateTwo(bean.getStartDate(),bean.getEndDate()).equalsIgnoreCase("Invalid")) {
				addActionError("End Date SHould be less than Start Date");
			}else{
				String startDateStatus=service.getStartDateStatus(bean);
				String endDateStatus=service.getEndDateStatus(bean);
					if(!startDateStatus.equals(endDateStatus)){
						addActionError("Start Date and End Date both Must be Active or DeActive");
					}else{
						bean.setStatus(startDateStatus);
					}
			}
		}
		
	}
}
