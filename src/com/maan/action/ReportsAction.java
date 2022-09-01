package com.maan.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.ReportsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.ExcelDownload;
import com.maan.common.util.LogUtil;
import com.maan.common.util.Validation;
import com.maan.service.ReportsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.maan.common.util.StringHelper;

public class ReportsAction extends ActionSupport implements ModelDriven<ReportsBean>{
	private static final long serialVersionUID = 1L;
	Logger logger = LogUtil.getLogger(this.getClass());
	Map<String,Object> session = ActionContext.getContext().getSession();
	final String pid = (String) session.get("mfrid");
	private static final String DROPDOWNAJAX="dropdownajax";
	private String branchCode=session.get("BRANCH_CODE").toString();
	private String countryId=session.get("countryId").toString();
	//private String userId=(String) session.get("UserId");
	final HttpServletRequest request1 = ServletActionContext.getRequest();
	final HttpServletResponse response = ServletActionContext.getResponse();
	ServletContext context = request1.getSession().getServletContext();
	ReportsBean bean= new ReportsBean();
	ReportsService service=new ReportsService();
	final Validation val=new Validation();
	private static boolean flag;

	private InputStream inputStream;

	public ReportsBean getModel() {
		return bean;
	}
	
	public String getInit(){
		String forward="Reports";
		try {
			if ("1".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.pendingOffers")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("RiskDetails.AccountDate"));
			}else if ("2".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.policyRegister")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("RiskDetails.AccountDate"));
			}else if("3".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.PremiumRegister")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Premium.Transaction"));
			}else if("4".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.claimRegister")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("claim.transactionDate"));
				bean.setStart_date("01/01/2013");
			}else if("5".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.renewalDue")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("RiskDetails.ExpiryDate"));
			}else if("6".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.PremiumRegister")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Premium.Transaction"));
			}else if("7".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.retroPolicyDetails")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("RiskDetails.AccountDate"));
			}else if("9".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.transaction")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Premium.Transaction"));
			}if("8".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.inward")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("RiskDetails.AccountDate"));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setYearList(getYearList());
				bean.setDisplay("retroRDSReport");
			}else if("9".equals(bean.getTypeId())){
				bean.setProductId(pid);
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setYearList(getYearList());
				bean.setDisplay("initTrans");				
			}else if("10".equals(bean.getTypeId())){
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getMoveMentInit(bean));
				bean.setProductId(pid);
				bean.setDisplay("initMovRep");
				bean.setMovementType("I");
				forward ="MovementReports";
			}else if("11".equals(bean.getTypeId()) ||"54".equals(bean.getTypeId())){
				bean.setProductList(new DropDownControllor().getProductDropDownSOAList(branchCode));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				//bean.setProductId(pid);
				bean.setDebFrom("0");
				bean.setDebTo("30");
				bean.setDebFrom1("31");
				bean.setDebTo1("90");
				bean.setDebFrom2("91");
				bean.setDebTo2("180");
				bean.setDebFrom3("181");
				bean.setDebTo3("365");
				bean.setDebFrom4("366");
				bean.setDebTo4("730");
				bean.setDebFrom5("731");
				
				bean.setDisplay("init");
				forward="DebtorsAgeing";
			}else if("12".equals(bean.getTypeId())){
				bean.setProductId(pid);
				bean.setDisplay("initMovementSummary");
				forward ="MovementReports";
			}else if("13".equals(bean.getTypeId())){
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getClaimMoveMentInit(bean));
				bean.setProductId(pid);
				bean.setDisplay("initCLMovRep");
				bean.setMovementType("I");
				forward ="MovementReports";
			}else if("14".equals(bean.getTypeId())){
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getClaimJournelInit(bean));
				bean.setProductId(pid);
				bean.setDisplay("clmJurnelRep");
				bean.setMovementType("I");
				forward ="MovementReports";
			}else if("15".equals(bean.getTypeId())){
				bean.setDisplay("init");
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getBookedUprInit(bean));
				forward ="journalReports";
			}else if("16".equals(bean.getTypeId())){
				bean.setDisplay("init");
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getBookedPremiumInit(bean));
				forward ="journalReports";
			}else if("17".equals(bean.getTypeId())){
				bean.setDisplay("init");
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getBookedUprInit(bean));
				forward ="journalReports";
			}else if("18".equals(bean.getTypeId())){
				bean.setDisplay("init");
				bean.setBranchCode(branchCode);
				bean.setReportList(service.getPipelineWrittenInit(bean));
				forward ="journalReports";
			}else if("19".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.allocationdetails")+" "+getText("Heading.Reports"));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDisplay("allocationReport");
			}else if("21".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.receiptpaymentdetails")+" "+getText("Heading.Reports"));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDisplay("receiptpaymentReport");
			}
			else if("23".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.claimpaiddetails")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("claim.transactionDate"));
				bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDeptName(new DropDownControllor().getDeptName(branchCode));
				bean.setYearList(getYearList());
				bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
				bean.setDisplay("init");
			}
			else if("24".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.retrodetails")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("claim.transactionDate"));
				//bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDeptName(new DropDownControllor().getDeptName(branchCode));
				
				bean.setYearList(getYearList());
				bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
				bean.setDisplay("retroReport");
			}
			else if("25".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.treatydetails")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("claim.transactionDate"));
				//bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDepartIdlist(new DropDownControllor().RenewalDropDown(branchCode,"2","Y"));
				bean.setProposaltypelist(new DropDownControllor().getConstantDropDown("4"));
				bean.setYearList(getYearList());
				bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
				bean.setDisplay("TRwithdrawReport");
			}
			else if("36".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.installment")+" "+getText("Heading.Reports"));
				bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
				bean.setDisplay("installmentdueReport");
			}
			else if("37".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.TB")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Trial Balance"));
				bean.setDisplay("TbReport");
			}else if("38".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.POP")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Portfolio Out Pending"));
				bean.setDisplay("TbReport");
			}else if("39".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.SOA")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Pending Statements"));
				bean.setDisplay("TbReport");
			}else if("45".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.OSLR")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("Claims Outstanding"));
				bean.setDisplay("TbReport");
			}
			else if("55".equals(bean.getTypeId())){
				bean.setHeadMes(getText("Heading.SOAReport")+" "+getText("Heading.Reports"));
				bean.setDateMes(getText("SOA Report"));
				bean.setDisplay("soaReport");
				bean.setProductList(new DropDownControllor().getProductDropDownSOAList(branchCode));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));//C=>Ceding Compacy
			}
			else{
				bean.setDisplay("init");
				bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
				bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
				bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
				bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));//C=>Ceding Compacy
				bean.setDeptName(new DropDownControllor().getDeptName(branchCode));
				bean.setYearList(getYearList());
				bean.setBranchCode(branchCode);
			}
		}catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();		
		}
		return forward;
	}


	private List<Map<String,Object>> getYearList(){
		List<Map<String,Object>> yearsList=new ArrayList<Map<String,Object>>();
		Calendar cal=Calendar.getInstance(); 
		for(int j=(cal.get(Calendar.YEAR))-4;j<=(cal.get(Calendar.YEAR))+1;j++){
			Map<String,Object> year=new HashMap<String, Object>();
			year.put("YEAR", j);
			yearsList.add(year);
		}
		return yearsList;
	}

	public String getCedingCompany(){
		try {
			bean.setBranchCode(branchCode);
			bean.setProductId(bean.getProductId()==null?"":bean.getProductId());
			bean.setBrokerId(bean.getBrokerId()==null?"":bean.getBrokerId());
			String cedingCo = service.getCedingCompany(bean);
			logger.info("CedingCo==> " + cedingCo);
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return DROPDOWNAJAX;
	}
	
	public void treansactionPdf(){
		
		JasperReports reports=new JasperReports();
		try {
			bean.setSysDate(new DropDownControllor().getSysDate());
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
			String date = sdf.format(cal.getTime());
			if("PT".equalsIgnoreCase(bean.getTypeId())){
				bean.setExcelFileName("PaymentDetails"+ date + ".pdf");
			}else{
				bean.setExcelFileName("ReceiptDetails"+ date + ".pdf");
			}
				bean.setImagePath(context.getRealPath("/images"));
				ByteArrayOutputStream bos1=new ByteArrayOutputStream();
				reports.getTransactionPDFReport(bean,branchCode,"pdf",bos1);
				//inputStream = new ByteArrayInputStream(bos1.toByteArray());
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		 //return "stream";
	}
	
	public String getRlist(){
		String forward = "Reports";
		bean.setDisplay("init");
		//bean.setLoginId(userId);
		try {
			if(!("27".equals(bean.getTypeId()))|| "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) ||"30".equals(bean.getTypeId()) || "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"55".equals(bean.getTypeId()) ||"56".equals(bean.getTypeId()) ||"57".equals(bean.getTypeId())){
			getActionErrorMethods();
			}
			/*if("27".equals(bean.getTypeId())|| "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) || "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"55".equals(bean.getTypeId())){
				JournalDownloadValidation();
			}*/
			if("8".equals(bean.getTypeId()) ||"12".equals(bean.getTypeId()) || "24".equals(bean.getTypeId()) || "25".equals(bean.getTypeId())|| "27".equals(bean.getTypeId())|| "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) ||"30".equals(bean.getTypeId()) ||"37".equals(bean.getTypeId()) ||"38".equals(bean.getTypeId())||"39".equals(bean.getTypeId()) ||"45".equals(bean.getTypeId()) || "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"55".equals(bean.getTypeId()) ||"56".equals(bean.getTypeId()) ||"57".equals(bean.getTypeId()))
				if(StringUtils.isBlank(bean.getProductId())){
				bean.setProductId(pid);
				}
			if(!hasActionErrors()){
				//String EXCEL_DOWNLOAD_PATH=context.getRealPath("/Downloads/XLS/");
				if("-1".equalsIgnoreCase(bean.getProductId())||StringUtils.isBlank(bean.getProductId())){
					bean.setProductName("All");
				}else{
					if("1".equalsIgnoreCase(bean.getProductId())){
						bean.setProductName("Facultative");
					}else if("2".equalsIgnoreCase(bean.getProductId())){
						bean.setProductName("Proportional Treaty");
					}else if("3".equalsIgnoreCase(bean.getProductId())){
						bean.setProductName("Non Proportional Treaty");
					}else if("4".equalsIgnoreCase(bean.getProductId())){
						bean.setProductName("Retro");
					}else if("5".equalsIgnoreCase(bean.getProductId())){
						bean.setProductName("Retro Non Proportional Treaty");
					}else if("6".equalsIgnoreCase(bean.getProductId())){
						if("9".equals(bean.getTypeId()))
							bean.setProductName("Transaction Reports");
						else
							bean.setProductName("RDS Policy Details Reports");
					}
				}
				if("-1".equalsIgnoreCase(bean.getLoginId())||StringUtils.isBlank(bean.getLoginId())){
					bean.setLoginName("All");
				}else{
					bean.setLoginName(bean.getLoginId());
				}
				if("-1".equalsIgnoreCase(bean.getBrokerId())||StringUtils.isBlank(bean.getBrokerId())){
					bean.setBrokerName("All");
				}else{
					bean.setBrokerName(new DropDownControllor().getCompanyName(branchCode, bean.getBrokerId(), "B"));
				}
				if("-1".equalsIgnoreCase(bean.getCedingId())||StringUtils.isBlank(bean.getCedingId())){
					bean.setCedingCoName("MULTIPLE");
				}else{
					bean.setCedingCoName(new DropDownControllor().getCompanyName(branchCode, bean.getCedingId(), "C"));
				}
				
				bean.setSysDate(new DropDownControllor().getSysDate());
				if("pdf".equalsIgnoreCase(bean.getReportType())){
					JasperReports reports=new JasperReports();
					try {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
						String date = sdf.format(cal.getTime());
							bean.setExcelFileName("SOAReport"+ date + ".pdf");
							bean.setImagePath(context.getRealPath("/images"));
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getsoaReport(bean,branchCode,"pdf",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
					}
					 catch (Exception e) {
							e.printStackTrace();
						}
					 forward = "stream";
				}else{
					if(!"55".equals(bean.getTypeId())){	
						bean.setReportName(service.getReportName(bean));
					}else{
						bean.setReportName("SOAReport");
					}
				String fileName=StringHelper.getFileNameFormat(bean.getReportName()+".xls", "");
				List<Map<String,Object>> result = null;
				if("1".equals(bean.getTypeId())){
					//PENDING OFFERS
					bean.setBranchCode(branchCode);
					result=service.getPendingOffersList(bean);
					bean.setStartDateLabelName("Acceptance Date Start From");
					bean.setEndDateLabelName("Acceptance Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("2".equals(bean.getTypeId())){
					//POLICY REGISTER
					bean.setBranchCode(branchCode);
					bean.setStartDateLabelName("Acceptance Date Start From");
					bean.setEndDateLabelName("Acceptance Date End To");
					if("HTML".equalsIgnoreCase(bean.getReportType())) {
						result = service.getPolicyRegisterList(bean);
						if (!"Excel".equalsIgnoreCase(bean.getReportType())) {
							bean.setReportsList(result);
						}
					}
				}else if("3".equals(bean.getTypeId())){
					//PREMIUM REGISTER
					bean.setBranchCode(branchCode);
					result=service.getPremiumRegisterList(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("4".equals(bean.getTypeId())){
					//CLAIM REGISTER
					bean.setBranchCode(branchCode);
					bean.setCountryId(countryId);
					result=service.getClaimRegisterList(bean);
					bean.setStartDateLabelName("Transaction Date Start");
					bean.setEndDateLabelName("Transaction Date End");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("5".equals(bean.getTypeId())){
					//RENEWAL DUE REPORT
					bean.setBranchCode(branchCode);
					result=service.getRenewalDueList(bean);
					bean.setStartDateLabelName("Expiry Date Start From");
					bean.setEndDateLabelName("Expiry Reg Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("6".equals(bean.getTypeId())){
					//RETRO QUARTERLY REPORT
					bean.setBranchCode(branchCode);
					result=service.getRetroQuarterlyReport(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("7".equals(bean.getTypeId())){
					//INWARD RETRO  REPORT
					bean.setBranchCode(branchCode);
					result=service.getInwardRetroMappingReport(bean);
					bean.setStartDateLabelName("Acceptance Date Start From");
					bean.setEndDateLabelName("Acceptance Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("8".equals(bean.getTypeId())){
					//RETRO  INWARD REPORT
					bean.setBranchCode(branchCode);
					result=service.getRetroInwardMappingReport(bean);
					bean.setStartDateLabelName("Acceptance Date Start From");
					bean.setEndDateLabelName("Acceptance Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("9".equals(bean.getTypeId())){
					//TRANSACTION REPORT
					bean.setBranchCode(branchCode);
					if(bean.getTest().equalsIgnoreCase("ALL")){
						bean.setContractNo("ALL");
					}
					result=service.getTransactionMasterReport(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("11".equals(bean.getTypeId()) || "54".equals(bean.getTypeId())){
					//DEBTORSAGEING REPORT
					bean.setBranchCode(branchCode);
					result=service.getDebtorsAgeingReport(bean);
					bean.setStartDateLabelName("Debtors Ageing Report Date as on");
					bean.setEndDateLabelName("");
					bean.setEnd_date("");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("12".equals(bean.getTypeId())){
					//DEBTORSAGEING REPORT
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					bean.setBranchCode(branchCode);
					result=service.getMoveMntSummary(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}else if("19".equals(bean.getTypeId())){
					//ALLOCATION REPORT
					bean.setBranchCode(branchCode);
					bean.setProductId(pid);
					result = service.getallocationReportList(bean);
					bean.setStartDateLabelName("Allocation Date Start From");
					bean.setEndDateLabelName("Allocation Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}else if("21".equals(bean.getTypeId())){
					bean.setBranchCode(branchCode);
					bean.setProductId(pid);
					result = service.getPayRecRegisterList(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}
				
				else if("23".equals(bean.getTypeId())){
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					bean.setBranchCode(branchCode);
					result = service.getClaimPaidRegisterList(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}
				else if("24".equals(bean.getTypeId())){
					bean.setBranchCode(branchCode);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					result = service.getRetroRegisterList(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}
				else if("25".equals(bean.getTypeId())){
					bean.setBranchCode(branchCode);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					result = service.getTreatyWithdrawRegisterList(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}else if("26".equals(bean.getTypeId())){
					//Statistic REPORT
					bean.setBranchCode(branchCode);
					result=service.getTransactionMasterReport(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}
				else if("27".equals(bean.getTypeId()) || "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) ||"30".equals(bean.getTypeId())|| "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"56".equals(bean.getTypeId()) ||"57".equals(bean.getTypeId())){
					bean.setBranchCode(branchCode);
					result = service.getJVReports(bean);
					bean.setStartDateLabelName("Transaction Date Start From");
					bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);	
					}
				}else if("36".equals(bean.getTypeId())){
					//Installment Due REPORT
					bean.setBranchCode(branchCode);
					result=service.getInstallmentDueReport(bean);
					bean.setStartDateLabelName("Installment Due As On Date");
					//bean.setEndDateLabelName("Transaction Date End To");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("37".equals(bean.getTypeId())){
					//Trial Balance REPORT
					bean.setBranchCode(branchCode);
					result=service.getTrailBalanceReport(bean);
					bean.setStartDateLabelName("Trial Balance as on Date");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("38".equals(bean.getTypeId())){
					//Portfolio Out Pending Report
					bean.setBranchCode(branchCode);
					result=service.getPortfolioOutPendingReport(bean);
					bean.setStartDateLabelName("Portfolio Out Pending as on Date");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("39".equals(bean.getTypeId())){
					//PTTY SOA Pending Report
					bean.setBranchCode(branchCode);
					result=service.getPptySOAPendingReport(bean);
					bean.setStartDateLabelName("Pending Statements as on Date");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("40".equals(bean.getTypeId()) ||"41".equals(bean.getTypeId())||"42".equals(bean.getTypeId())||"43".equals(bean.getTypeId())){
					//PTTY SOA Pending Report
					bean.setBranchCode(branchCode);
					result=service.getUWStatisticsReport(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("44".equals(bean.getTypeId())){
					//IE Report
					bean.setBranchCode(branchCode);
					result=service.getIEReport(bean);
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}else if("45".equals(bean.getTypeId())){
					//PTTY Claim OSLR
					bean.setBranchCode(branchCode);
					result=service.getPptyOSLRClaimReport(bean);
					bean.setStartDateLabelName("Claims Outstanding as on");
					if(!"Excel".equalsIgnoreCase(bean.getReportType())){
						bean.setReportsList(result);
					}
				}
				
				
				List<Map<String,Object>> companyInfo=null;
				companyInfo=service.getCompanyInfoList(bean);
				List<Map<String,Object>> columnInfo=null;
				columnInfo=service.getColumnInfoList(bean);
				Map<String,Object> headerInfo=new HashMap<String,Object>();
				headerInfo.put("CompanyInfo", companyInfo);
				
				headerInfo.put("SelectedInfo", bean);
				if("Excel".equalsIgnoreCase(bean.getReportType())){
					try {
						//ByteArrayOutputStream bos=new ByteArrayOutputStream();
						//ExcelDownload.writeExcel(headerInfo,columnInfo,result, bos,bean.getTypeId());
						//ByteArrayInputStream inputStream=new ByteArrayInputStream(bos.toByteArray());
						/*response.setContentType("application/vnd.ms-excel");    
						response.addHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");   
						ServletOutputStream out = response.getOutputStream();  
						out.write(bos.toByteArray());   
						out.flush(); 
						forward=null;*/
						ByteArrayOutputStream bos=new ByteArrayOutputStream();
						ExcelDownload.writeExcel(headerInfo,columnInfo,result, bos,bean.getTypeId());
						inputStream=new ByteArrayInputStream(bos.toByteArray());
						bean.setExcelFileName(fileName);
						forward = "stream";
					} catch (Exception e) {
						logger.debug("Exception @ {" + e + "}");
						e.printStackTrace();
					}
				}else if("xml".equalsIgnoreCase(bean.getReportType())){
					JasperReports reports=new JasperReports();
					try {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
						String date = sdf.format(cal.getTime());
						if("2".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getPolicyRegisterReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("3".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getPremiumRegisterReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("4".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getClaimRegisterReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}else if("9".equals(bean.getTypeId())){
							bean.setExcelFileName("TransactionMaster " + date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getTransactionReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("19".equals(bean.getTypeId())){
							bean.setExcelFileName("Allocation " + date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getAllocationReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("26".equals(bean.getTypeId())){
							bean.setExcelFileName("Statistics Report as " + date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getTransactionReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("23".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getClaimPaidRegisterReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("24".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							bean.setLoginId("-1");
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getRetroRegisterReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("25".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getTreatyWithdrawReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else if("11".equals(bean.getTypeId()) || "54".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getDebtorsAgingReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}else if("45".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							bean.setImagePath(context.getRealPath("/images"));
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getClaimOslrReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}else if("55".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							bean.setImagePath(context.getRealPath("/images"));
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getSOAReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}else if("27".equals(bean.getTypeId()) || "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) ||"30".equals(bean.getTypeId())|| "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"56".equals(bean.getTypeId()) ||"57".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getJournalViewDownload(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}else if("36".equals(bean.getTypeId())){
							bean.setExcelFileName(bean.getReportName()+ date + ".xls");
							bean.setImagePath(context.getRealPath("/images"));
							ByteArrayOutputStream bos1=new ByteArrayOutputStream();
							reports.getInsallmentDueReport(bean,branchCode,"xls",bos1);
							inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}
						else{
						bean.setExcelFileName(bean.getReportName() + date + ".xls");
						
						ByteArrayOutputStream bos1=new ByteArrayOutputStream();
						//reports.getPayRecReport(bean.getStart_date(),bean.getEnd_date(),branchCode,bean.getBrokerId(),bean.getCedingId(),bean.getPayrecType(),bean.getTransactionType(),bean.getShowAllFields(),"xls",bos1);
						reports.getPayRecReport(bean,branchCode,"xls",bos1);
						inputStream = new ByteArrayInputStream(bos1.toByteArray());
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					return "stream";
				} 
				else{
					bean.setHeaderInfo(headerInfo);
					bean.setColumnInfo(columnInfo);
				}
				bean.setDisplay("pendingOffer");
				}
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
			   logger.info("##########Validation Message End###########");
				getInit();
				if("11".equals(bean.getTypeId()))
					forward = "DebtorsAgeing";
				else if("27".equals(bean.getTypeId()) || "28".equals(bean.getTypeId()) || "29".equals(bean.getTypeId()) ||"30".equals(bean.getTypeId())|| "46".equals(bean.getTypeId()) ||"47".equals(bean.getTypeId()) ||"48".equals(bean.getTypeId()) ||"49".equals(bean.getTypeId()) ||"50".equals(bean.getTypeId()) ||"51".equals(bean.getTypeId()) ||"52".equals(bean.getTypeId()) ||"53".equals(bean.getTypeId())||"56".equals(bean.getTypeId()) ||"57".equals(bean.getTypeId())){
					
					//bean.setOpenperiodList(service.getOpenPeriodList(session.get("BRANCH_CODE").toString()));
					//bean.setStartDateList(service.getStartDateList(session.get("BRANCH_CODE").toString()));
					//bean.setEndDateList(service.getEndDateList(session.get("BRANCH_CODE").toString()));
					return "journal";
				}
				/*else if("12".equals(bean.getTypeId())) {
					bean.setDisplay("initMovementSummary");	
					forward = "MovementReports";
				}else if("19".equals(bean.getTypeId())){
					bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
					bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
					bean.setDisplay("allocationReport");
				}if("9".equals(bean.getTypeId())){
					bean.setProductId(pid);
					bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
					bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
					bean.setYearList(getYearList());
					bean.setDisplay("initTrans");	}
				else if("25".equals(bean.getTypeId())){
					bean.setHeadMes(getText("Heading.treatydetails")+" "+getText("Heading.Reports"));
					bean.setDateMes(getText("claim.transactionDate"));
					//bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
					bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
					bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));
					bean.setDepartIdlist(new DropDownControllor().RenewalDropDown(branchCode,"2","Y"));
					bean.setProposaltypelist(new DropDownControllor().getConstantDropDown("4"));
					bean.setYearList(getYearList());
					bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
					bean.setDisplay("TRwithdrawReport");
				}
				else{
					bean.setProductList(service.getProductDropDown(branchCode,bean.getTypeId()));
					bean.setUserList(new DropDownControllor().getLoginIdDropDown(branchCode, "user"));
					bean.setBrokerList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"B",""));//B=>Broker
					bean.setCeddingCompanyList(new DropDownControllor().getPersonalInfoDropDown(branchCode,"C",""));//C=>Ceding Compacy
					bean.setDeptName(new DropDownControllor().getDeptName(branchCode));
					bean.setYearList(getYearList());
					
				}*/
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return forward;
	}
	private void JournalDownloadValidation() {
		bean.setBranchCode(branchCode);
		String startDateStatus=service.getStartDateStatus(bean);
		String endDateStatus=service.getEndDateStatus(bean);
		if(!startDateStatus.equals(endDateStatus)){
			addActionError("Start Date and End Date both Must be Active or DeActive");
		}else{
			bean.setStatus(startDateStatus);
		}
		
	}
	public String viewMovDtls(){
		try{
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getMoveMentPage(bean));
			//reportsForm.setDisplay("MovRepPage");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String viewPipelineMoveJvCrnt() {
		try {
			bean.setBranchCode(branchCode);
			bean.setDisplay("crnt");
			bean.setReportList(service.getJournalListCrnt(bean));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String viewBookedUPRCrnt() {
		try {
			bean.setBranchCode(branchCode);			
			bean.setDisplay("crnt");
			bean.setReportList(service.getJournalListCrnt(bean));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String callProBookedPremium() {
		try {
			bean.setBranchCode(branchCode);
			logger.info("Movement type"+bean.getMovementType());
			validateProBookedPremium();
			if(!hasActionErrors()){				
				service.callProBookedPremium(bean);
				if (!"".equals(bean.getResult())) {
					addActionError(bean.getResult());
					bean.setDisplay("new");
					/*if(StringUtils.isNotBlank(bean.getReportDate()))
						bean.setReportDate(dateFormat(bean.getReportDate()));*/						
				} else {
					bean.setReportList(service.getBookedPremiumInit(bean));					
					bean.setDisplay("init");					
					addActionError("Inserted Successfully");
				}
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	private void validateProBookedPremium() {
		if(StringUtils.isBlank(bean.getReportDate()))
			addActionError("Please select Report Date");		
		if(StringUtils.isBlank(bean.getProduct()))
			addActionError("Please select Product");
		if(StringUtils.isBlank(bean.getMovementType()))
			addActionError("Please select Movement Type");
	}

	/*private void validateDate() {
		if(StringUtils.isBlank(bean.getStart_date()))
			addActionError("Please select Start Date");
		if(StringUtils.isBlank(bean.getEnd_date()))
			addActionError("Please select End Date");
		if(StringUtils.isNotBlank(bean.getStart_date()) && StringUtils.isNotBlank(bean.getEnd_date())){
			try{
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	            Date date1 = sdf.parse(bean.getStart_date());
	            Date date2 = sdf.parse(bean.getEnd_date());

	            System.out.println(sdf.format(date1));
	            System.out.println(sdf.format(date2));

	            if(date2.before(date1)) {
	            	addActionError("End Date is smaller than Start Date");
	            }

	            /*
	            if(date1.after(date2)){
	                System.out.println("Date1 is after Date2");
	            }

	            if(date1.before(date2)){
	                System.out.println("Date1 is before Date2");
	            }

	            if(date1.equals(date2)){
	                System.out.println("Date1 is equal Date2");
	            } */
	/*	}catch(Exception ex){
	            ex.printStackTrace();
	        }
		}
	}*/

	public String callProBookedUpr() {
		try {
			bean.setBranchCode(branchCode);
			System.out.println("Movement type"+bean.getMovementType());
			validateProBookedPremium();
			if(!hasActionErrors()){
				service.callProBookedUpr(bean);
				if (!"".equals(bean.getResult())) {
					addActionError(bean.getResult());
					bean.setDisplay("new");
					/*if(StringUtils.isNotBlank(bean.getReportDate()))
						bean.setReportDate(dateFormat(bean.getReportDate()));	*/					
				} else {
					bean.setReportList(service.getBookedUprInit(bean));					
					addActionError("Inserted Successfully");
					bean.setDisplay("init");						
				}												
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setDisplay("new");
			/*	if(StringUtils.isNotBlank(bean.getReportDate()))
					bean.setReportDate(dateFormat(bean.getReportDate()));*/
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	private void validateProBookedUpr() {		
		if(StringUtils.isBlank(bean.getReportDate()))
			addActionError("Please select Report Date");
		if(StringUtils.isBlank(bean.getMovementType()))
			addActionError("Please select Movement Type");
	}

	public String callProPipelineMoveJv() {
		try {
			bean.setBranchCode(branchCode);
			logger.info("Movement type"+bean.getMovementType());
			validateProBookedPremium();
			if(!hasActionErrors()){				
				service.callPipelineMoveJv(bean);
				if (!"".equals(bean.getResult())) {
					addActionError(bean.getResult());
					bean.setDisplay("new");
					/*if(StringUtils.isNotBlank(bean.getReportDate()))
						bean.setReportDate(dateFormat(bean.getReportDate()));*/
				} else {
					bean.setReportList(service.getBookedUprInit(bean));					
					addActionError("Inserted Successfully");
					bean.setDisplay("init");							
				}						
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setDisplay("new");
				/*if(StringUtils.isNotBlank(bean.getReportDate()))
					bean.setReportDate(dateFormat(bean.getReportDate()));*/	
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String callProPipelineWritten() {
		try {
			bean.setBranchCode(branchCode);
			System.out.println("Movement type"+bean.getMovementType());
			validateProBookedPremium();
			if(!hasActionErrors()){				
				service.callProPipelineWritten(bean);
				if (!"".equals(bean.getResult())) {
					addActionError(bean.getResult());
					bean.setDisplay("new");
					/*if(StringUtils.isNotBlank(bean.getReportDate()))
						bean.setReportDate(dateFormat(bean.getReportDate()));	*/					
				} else {
					bean.setReportList(service.getPipelineWrittenInit(bean));
					bean.setDisplay("init");
					addActionError("Inserted Successfully");
				}							
			} else {
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setDisplay("new");
				/*if(StringUtils.isNotBlank(bean.getReportDate()))
					bean.setReportDate(dateFormat(bean.getReportDate()));*/				
			}
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String viewBookedPremiumCrnt() {
		try {
			bean.setBranchCode(branchCode);
			bean.setDisplay("crnt");
			bean.setReportList(service.getJournalListCrnt(bean));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	} 

	public String viewPipelineWritPreCrnt() {		
		try {
			bean.setBranchCode(branchCode);			
			bean.setDisplay("crnt");
			bean.setReportList(service.getJournalListCrnt(bean));
		} catch (Exception e) {
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String getPipelineMovementJvDetails() {
		try {
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getPipelineMovementJvDetails(bean));
			bean.setDisplay("pipelineMovementJV");
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String getBookedPremiumDetails() {
		try {
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getBookedPremiumDetails(bean));
			bean.setDisplay("bookedPremium");
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String getBookedUprDetails() {
		try {
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getBookedUprDetails(bean));
			bean.setDisplay("bookedUpr");
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String getPipelinedWrtnDetails() {
		try {
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getPipelinedWrtnDetails(bean));
			bean.setDisplay("pipelineWritten");
		} catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String viewJurnal(){
		try{
			String sNo = bean.getSNo()==null?"0":bean.getSNo();
			/*double pre,inte,credit,acq,claim,prRe,claRe,debit,percentDebit,percentCredit,debitTotal;
			    String debitTotal1;	*/
			logger.info("SNO=>"+sNo);
			bean.setSNo(bean.getSNo()==null?"":bean.getSNo());
			bean.setDisplay("viewJurnal");
			bean.setBranchCode(branchCode);
			List<ReportsBean> list = service.getViewAll(bean);
			bean.setViewJurnalAll(list);
			bean.setDisplay("viewAllReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		//reportsForm.setDisplay("viewJurnal");
		return "MovementReports";
	}

	public String viewCLMovDtls(){
		try{
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getClaimMoveMentPage(bean));
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}
	public String viewJournelDtls(){
		try{
			bean.setBranchCode(branchCode);
			bean.setReportList(service.getJournelPage(bean));
			bean.setDisplay("MovjournelPage");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String newCLMovReport(){
		try{
			bean.setDisplay("newCLMovReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String insertCLMovement(){
		try{
			bean.setBranchCode(branchCode);
			getCLInsertMovement();
			if(!hasActionErrors()){
				service.insertCLMovement(bean);
				bean.setReportList(service.getClaimMoveMentInit(bean));
				bean.setDisplay("initCLMovRep");
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
			   logger.info("##########Validation Message End###########");
				/*if(StringUtils.isNotBlank(bean.getEnd_date()))
					bean.setEnd_date(dateFormat(bean.getEnd_date()));*/
				bean.setDisplay("newCLMovReport");
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String viewAll() {
		try{
			//reportsForm.setBranchCode(loginform.getBranchCode());
			bean.setBranchCode(branchCode);
			bean.setDisplay("viewJurnalAll");
			//BeanUtils.copyProperties(reportsBean, reportsForm);
			List<ReportsBean> list = service.getViewAll(bean);
			bean.setViewJurnalAll(list);
			//request.setAttribute("viewJurnalAll", list);
			bean.setDisplay("viewAllReport");
		}catch(Exception e){
			logger.debug("Exception @ { " + e + " } ");
		}
		return "MovementReports"; 
	}

	public String newMovReport(){
		try{
			bean.setBranchCode(branchCode);
			bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("12"));//premiumaccperiodDropDown
			bean.setYearList(getYearList());
			bean.setDisplay("newMovReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String  newJournal(){
		try{
			bean.setBranchCode(branchCode);
			//bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("12"));//premiumaccperiodDropDown
			//bean.setYearList(getYearList());
			bean.setDisplay("new");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "journalReports";
	}

	public String insertMovement(){
		try{
			bean.setBranchCode(branchCode);
			System.out.println("Movement type"+bean.getMovementType());
			getInsertMovement();
			if(!hasActionErrors()){
				service.insertMovement(bean);
				bean.setReportList(service.getMoveMentInit(bean));
				bean.setDisplay("initMovRep");
				addActionError(getText("error.movmentsucess"));
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
				logger.info("##########Validation Message End###########");
				bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("12"));//premiumaccperiodDropDown
				bean.setYearList(getYearList());
				bean.setDisplay("newMovReport");
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String viewJurnalReport(){
		try{
			String sNo =bean.getSNo()==null?"0":bean.getSNo();
			/*double pre,inte,credit,acq,claim,prRe,claRe,debit,percentDebit,percentCredit,debitTotal;
			    String debitTotal1;	*/
			logger.info("SNO=>"+sNo);
			bean.setSNo(bean.getSNo()==null?"0":bean.getSNo());
			bean.setDisplay("viewJurnal");
			bean.setBranchCode(branchCode);
			List<ReportsBean> list = service.getViewJurnelAll(bean);
			bean.setViewJurnalAll(list);
			bean.setDisplay("viewJurnelAllReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		//bean.setDisplay("viewJurnal");
		return "MovementReports";
	}

	public String viewAllJournel(){
		try{
			bean.setBranchCode(branchCode);
			bean.setDisplay("viewJurnalAll");
			List<ReportsBean> list = service.getViewJurnelAll(bean);
			bean.setViewJurnalAll(list);
			bean.setDisplay("viewJurnelAllReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String journelReport(){
		try{
			bean.setBranchCode(branchCode);
			bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("12"));//premiumaccperiodDropDown
			bean.setYearList(getYearList());
			bean.setDisplay("journelReport");
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public String insertJournelMovement(){
		try{
			bean.setBranchCode(branchCode);
			System.out.println("Movement type"+bean.getMovementType());
			getInsertMovement();
			if(!hasActionErrors()){
				service.journelInsertMovement(bean);
				bean.setReportList(service.getJournelMoveMentInit(bean));
				bean.setDisplay("clmJurnelRep");
				addActionError(getText("error.movmentsucess"));
			}else{
				logger.info("##########Validation Message Start###########");
				Iterator<String> error = getActionErrors().iterator();
				while(error.hasNext()){
					logger.info(error.next());
				}
			   logger.info("##########Validation Message End###########");
				bean.setPremiumaccperiod(new DropDownControllor().getConstantDropDown("12"));//premiumaccperiodDropDown
				bean.setYearList(getYearList());
				bean.setDisplay("journelReport");
			}
		}catch(Exception e){
			logger.debug("Exception @ {" + e + "}");
			e.printStackTrace();
		}
		return "MovementReports";
	}

	public void getActionErrorMethods() {

		boolean flag=false;
		try {
			if("1".equals(bean.getTypeId()) || "2".equals(bean.getTypeId())|| "3".equals(bean.getTypeId())|| "4".equals(bean.getTypeId())|| "5".equals(bean.getTypeId())|| "6".equals(bean.getTypeId())|| "7".equals(bean.getTypeId()) || "23".equals(bean.getTypeId()) ){
				if(Integer.parseInt(bean.getProductId())<0){
				addActionError(getText("errors.report.product"));
				}
			}
			if("1".equals(bean.getTypeId())|| "2".equals(bean.getTypeId())|| "3".equals(bean.getTypeId())|| "5".equals(bean.getTypeId())||"6".equals(bean.getTypeId())|| "7".equals(bean.getTypeId())|| "8".equals(bean.getTypeId())|| "9".equals(bean.getTypeId())|| "11".equals(bean.getTypeId())|| "19".equals(bean.getTypeId())|| "21".equals(bean.getTypeId())|| "23".equals(bean.getTypeId())|| "24".equals(bean.getTypeId()) || "37".equals(bean.getTypeId())|| "38".equals(bean.getTypeId())|| "39".equals(bean.getTypeId()) || "45".equals(bean.getTypeId())|| "46".equals(bean.getTypeId())){
				if(val.checkDate(bean.getStart_date()).equalsIgnoreCase("INVALID")) {
					if("45".equals(bean.getTypeId())){
						addActionError(getText("errors.report.start_date.claim"));
					}else if("38".equals(bean.getTypeId())){
						addActionError(getText("errors.report.start_date.portfolio"));
					}else if("37".equals(bean.getTypeId())){
						addActionError(getText("errors.report.start_date.trial"));
					}else if("39".equals(bean.getTypeId())){
						addActionError(getText("errors.report.start_date.ptty"));
					}else if("11".equals(bean.getTypeId())){
						addActionError(getText("errors.report.start_date.deptors"));
					}else{
					addActionError(getText("errors.report.start_date"));	
					}
					flag=true;
				}
			}
			if("1".equals(bean.getTypeId())|| "2".equals(bean.getTypeId())|| "3".equals(bean.getTypeId()) || "4".equals(bean.getTypeId())|| "5".equals(bean.getTypeId())||"6".equals(bean.getTypeId())|| "7".equals(bean.getTypeId())|| "8".equals(bean.getTypeId())|| "9".equals(bean.getTypeId())|| "19".equals(bean.getTypeId())|| "21".equals(bean.getTypeId())|| "23".equals(bean.getTypeId())|| "24".equals(bean.getTypeId()) || "25".equals(bean.getTypeId()) ||"36".equals(bean.getTypeId())|| "46".equals(bean.getTypeId())){
				if(val.checkDate(bean.getEnd_date()).equalsIgnoreCase("INVALID")) {
					if("25".equals(bean.getTypeId())){
						addActionError(getText("errors.report.end_date.portfolio"));
					}else if("36".equals(bean.getTypeId())){
						addActionError(getText("errors.report.end_date.as"));
					}
					else{
					addActionError(getText("errors.report.end_date"));
					}
					flag=true;
				}
			}
			
			if("9".equals(bean.getTypeId())){
				if(bean.getTest().equalsIgnoreCase("S")){
				if(StringUtils.isBlank(bean.getContractNo())){
					addActionError(getText("errors.report.contract"));
				}
				}
				if(StringUtils.isBlank(bean.getDocType())){
					addActionError(getText("errors.report.Doc.type"));
				}
			}if("46".equalsIgnoreCase(bean.getTypeId())){
				if(StringUtils.isBlank(bean.getBrokerId())){
					addActionError(getText("errors.report.broker"));
				}else if("63".equalsIgnoreCase(bean.getBrokerId())){
					if(StringUtils.isBlank(bean.getCedingId())){
						addActionError(getText("errors.report.cedent"));
					}
				}
			}
			if("1".equals(bean.getTypeId()) || "2".equals(bean.getTypeId()) || "5".equals(bean.getTypeId()) || "3".equals(bean.getTypeId())){
				if(bean.getProductId().equalsIgnoreCase("4") && "-1".equalsIgnoreCase(bean.getDept())){
				addActionError(getText("eror.dept.id.report"));
				}
			}
			if(!flag) {
				if(StringUtils.isNotBlank(bean.getStart_date()) && StringUtils.isNotBlank( bean.getEnd_date())){
				if(val.ValidateTwoDates(bean.getStart_date(), bean.getEnd_date()).equalsIgnoreCase("Invalid")) {
					addActionError(getText("errors.report.dates_invalied"));		 
				}
				}
			}if("55".equalsIgnoreCase(bean.getTypeId())){
				if(SOAReportValidation(bean)){
					addActionError(getText("errors.report.invalid.combination"));
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getCLInsertMovement(){		 
		if(val.checkDate(bean.getEnd_date()).equalsIgnoreCase("INVALID")){
			addActionError(getText("errors.report.movRepAsOn"));
		}
	}
	public void getInsertMovement(){
		if(Integer.parseInt(bean.getAccper())==0){
			flag=true;
			addActionError(getText("error.accountingQtr"));	
		}
		if(Integer.parseInt(bean.getAccountDate())==0){
			flag=true;
			addActionError(getText("error.accountingYr"));	
		}
		String date="";
		if("1".equals(bean.getAccper())) {
			date="31/03";
		}else if("2".equals(bean.getAccper())){
			date="30/06";
		}else if("3".equals(bean.getAccper())){
			date="30/09";
		}else if("4".equals(bean.getAccper())){
			date="31/12";
		}
		if(date.length()>0){
			date=date+"/"+bean.getAccountDate();
			if(Validation.ValidateTwo(new DropDownControllor().getSysDate(),date).equalsIgnoreCase("")){
				flag=true;
				addActionError(getText("errors.movmentDateInvalid"));
			}
		}
		if((!flag)&&"F".equalsIgnoreCase(bean.getMovementType())&&((service.getCountMovementRecord(bean.getBranchCode(),bean.getAccper(),bean.getAccountDate()))>0)){
			addActionError(getText("error.movmentExists"));
		}
	}

	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/*public String allocationReportDtls(){
		bean.setAllocationReportList(service.getallocationReportList(bean));
		bean.setDisplay("allocationReportList");
		return "allocationReports";
	}*/
	/*public boolean SOAReportValidation(ReportsBean bean){
	boolean result=true;
	if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//1
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//2
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//3
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//4
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//5
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//6
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//7
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//8
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//9
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) && "N".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//10
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//11
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) && "Y".equalsIgnoreCase(bean.getSaperatePRCLYN()) ){
		result=false;//12
	}
	return result;
}*/
public boolean SOAReportValidation(ReportsBean bean){
	boolean result=true;
	if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//1
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//2
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//3
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//4
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//5
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//6
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//7
	}else if("-1".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//8
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//9
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && !"-1".equalsIgnoreCase(bean.getProductId())  ){
		result=false;//10
	}else if("64".equalsIgnoreCase(bean.getBrokerId()) && !"-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//11
	}else if(!"-1".equalsIgnoreCase(bean.getBrokerId()) && "-1".equalsIgnoreCase(bean.getCedingId()) && "-1".equalsIgnoreCase(bean.getProductId()) ){
		result=false;//12
	}
	return result;
}

	
}

