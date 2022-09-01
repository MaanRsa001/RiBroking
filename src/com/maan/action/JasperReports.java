package com.maan.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.maan.bean.JournalBean;
import com.maan.bean.ReportsBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.db.DatabaseDAO;
import com.maan.common.util.LogUtil;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.inject.Container;

public class JasperReports {
	private final Logger logger = LogUtil.getLogger(getClass());
	final HttpServletRequest request=ServletActionContext.getRequest();
	final HttpServletResponse response=ServletActionContext.getResponse();
	//final transient private String imageURL = request.getSession().getServletContext().getRealPath("/images/");
	Map<String, Object> session=ActionContext.getContext().getSession();

	public void getJournalReport(String startDate, String endDate, String branchCode, String status,String journalId,String processStatus, String downloadType,   OutputStream os) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/Journals_Report.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("JournalId", journalId);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("status", status);
			jasperParameter.put("processStatus", processStatus);
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, os);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
	}

	private void exporter(String downloadType, JasperPrint jasperPrint, OutputStream os) throws Exception {
		if("xls".equals(downloadType)) {
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
			exporter.exportReport();
		}			
		else if("pdf".equals(downloadType)) {
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);					
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);							
			exporter.exportReport();				
		}			
		else {
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);	

			exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true);
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
			exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.3F);
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
			exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,true);	
			exporter.exportReport();			
		}
	}

	public void getPayRecReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/PayRecRegister.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("broker", bean.getBrokerId().equals("-1")?"ALL":bean.getBrokerId());
			jasperParameter.put("ceding", bean.getCedingId().equals("-1")?"ALL":bean.getCedingId());
			jasperParameter.put("payrectype", bean.getPayrecType());
			jasperParameter.put("transactionType", bean.getTransactionType());
			jasperParameter.put("status", bean.getShowAllFields());
			jasperParameter.put("reportName", bean.getReportName());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getTransactionReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/TransactionReport.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("docType", bean.getDocType().replaceAll("\\s+",""));
			jasperParameter.put("uwYear", bean.getUwYear());
			jasperParameter.put("contractNo", bean.getContractNo());
			jasperParameter.put("cedingName", bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("showfieldYN", bean.getShowAllFields());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginName());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getClaimRegisterReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/ClaimRegister.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("countryId", bean.getCountryId());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("uwYear", bean.getUwYear());
			
			jasperParameter.put("cedingName", bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginId());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
	}

	public void getPremiumRegisterReport(ReportsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/PremiumRegisterReport.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("countryId", bean.getCountryId());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("uwYear", bean.getUwYear());
			
			jasperParameter.put("cedingName", bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginId());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getClaimPaidRegisterReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/ClaimPaidReport.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("countryId", bean.getCountryId());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("uwYear", bean.getUwYear().equals("-1")?"ALL":bean.getUwYear());
			
			jasperParameter.put("cedingName", bean.getCedingId().equals("-1")?"ALL":bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId().equals("-1")?"ALL":bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginId().equals("-1")?"ALL":bean.getLoginId());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getPolicyRegisterReport(ReportsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/PolicyRegisterReport.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("countryId", bean.getCountryId());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("uwYear", bean.getUwYear());
			
			jasperParameter.put("cedingName", bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginId());
			jasperParameter.put("deptId", bean.getDept());
			jasperParameter.put("reportName", bean.getReportName());
			jasperParameter.put("rowNum", "ALL");
			jasperParameter.put("pageNum", "ALL");
			System.out.println(jasperParameter);
			JRProperties.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql"
					,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");

			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			jasperReport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql"
					,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");		
	}
	public  void getJournalReport(JournalBean bean, String branchCode,
			String filePath) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			String sampledate[]=bean.getOpenperiod().split("-");
			String startDate=sampledate[0];
			String endDate=sampledate[1];
			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("journalId", bean.getJournalID());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("shortname", bean.getShortname());
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/journalReport.jasper");
			if(new File(jasperPath).exists()){
				jasperReport = (JasperReport)JRLoader.loadObject(jasperPath);
				logger.info("Jasper Patch "+jasperPath);
			}
			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("Compiled File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObject(jasperPath);
			}
			logger.info("Filling report started.........");
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
			logger.info("Filling report finished.........");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void getRetroReport(RiskDetailsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("startDate", bean.getStartDate());
			jasperParameter.put("endDate", bean.getEndDate());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("type", bean.getType());
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/RetrProcessReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
	}

	public void getRetroRegisterReport(ReportsBean bean, String branchCode, String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("countryId", bean.getCountryId());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("uwYear", bean.getUwYear());
			
			jasperParameter.put("cedingName", bean.getCedingId());
			jasperParameter.put("brokerName", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingCoName());
			jasperParameter.put("brokerId", bean.getBrokerName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			jasperParameter.put("loginId", bean.getLoginId());
			jasperParameter.put("deptId", bean.getDept());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/RetroCessionReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getTreatyWithdrawReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("cedingId", "-1".equalsIgnoreCase(bean.getCedingId())?"ALL":bean.getCedingId());
			jasperParameter.put("brokerId", "-1".equalsIgnoreCase(bean.getBrokerId())?"ALL":bean.getBrokerId());
			jasperParameter.put("uwYear", "-1".equalsIgnoreCase(bean.getUwYear())?"ALL":bean.getUwYear());
			jasperParameter.put("class", "-1".equalsIgnoreCase(bean.getTreatyMainClass())?"ALL":bean.getTreatyMainClass());
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("treatyType", "-1".equalsIgnoreCase(bean.getTreatyType())?"ALL":bean.getTreatyType());
			jasperParameter.put("withdrawDate", bean.getEnd_date());
			
			
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/TreatyWithDrawReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getDebtorsAgingReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("type", bean.getType());
			jasperParameter.put("param1", bean.getDebFrom());
			jasperParameter.put("param2", bean.getDebTo());
			jasperParameter.put("param3", bean.getDebFrom1());
			jasperParameter.put("param4", bean.getDebTo1());
			jasperParameter.put("param5", bean.getDebFrom2());
			jasperParameter.put("param6", bean.getDebTo2());
			jasperParameter.put("param7", bean.getDebFrom3());
			jasperParameter.put("param8", bean.getDebTo3());
			jasperParameter.put("param9", bean.getDebFrom4());
			jasperParameter.put("param10", bean.getDebTo4());
			jasperParameter.put("param11", bean.getDebFrom5());
			jasperParameter.put("param12", bean.getDebTo5());
			jasperParameter.put("branchCode", bean.getBranchCode());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("brokerId", bean.getBrokerId());
			jasperParameter.put("cedingId", bean.getCedingId());
			jasperParameter.put("docType", bean.getDocType());
			jasperParameter.put("reportDate", bean.getStart_date());
			jasperParameter.put("reportName", bean.getReportName());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/DrCrAgeingReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getsoaReport(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("imagePath", bean.getImagePath());
			jasperParameter.put("branchCode", branchCode);
			//jasperParameter.put("uwYear", bean.getUwYear());
			jasperParameter.put("cedingName", bean.getCedingCoName());
			jasperParameter.put("brokerName", bean.getBrokerName());
			jasperParameter.put("cedingId", bean.getCedingId());
			jasperParameter.put("brokerId", bean.getBrokerId());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			//jasperParameter.put("loginId", bean.getLoginId());
			//jasperParameter.put("deptId", bean.getDept());
			jasperParameter.put("reportName", bean.getReportName());
			jasperParameter.put("settledItems", bean.getSettledItems());
			jasperParameter.put("unAllocatedCash", bean.getUnAllocatedCash());
			jasperParameter.put("prclSeparate", bean.getSaperatePRCLYN());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/SOAReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	
		}catch (Exception e1) {
			e1.printStackTrace();
		} 
}
	public void getAllocationReport(ReportsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} 

		catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			String jasperPath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/AllocationReport.jasper");
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("settleType", bean.getSettlementType());
			jasperParameter.put("cedingName", "-1".equalsIgnoreCase(bean.getCedingId())?"ALL":bean.getCedingId());
			jasperParameter.put("brokerName", "-1".equalsIgnoreCase(bean.getBrokerId())?"ALL":bean.getBrokerId());
			jasperParameter.put("allocatedStatus", bean.getAllocateStatus());
			jasperParameter.put("branchCode", branchCode);
			
			System.out.println(jasperParameter);
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");	

		}
		catch(Exception exception){
			logger.debug("Exception @ { " + exception + " } ");
			exception.printStackTrace();
		}

		logger.info("Filling report finished.........");
		
	}

	public void getTransactionPDFReport(ReportsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", bean.getImagePath());
	
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("receiptNo", bean.getReceiptNo());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			if("PT".equalsIgnoreCase(bean.getTypeId())){
				jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/IssuePayment.jasper");
			}else{
				jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/IssueReceipt.jasper");
			}
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			//exporter(downloadType, jasperPrint, bos1);  
			JasperViewer jv = new JasperViewer( jasperPrint, false );
			jv.viewReport( jasperPrint, false );
			logger.info("Filling report Ended.........");	
		}catch (Exception e1) {
			e1.printStackTrace();
		} 
}

	public void getClaimOslrReport(ReportsBean bean, String branchCode,
			String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", bean.getImagePath());
			jasperParameter.put("reportName", bean.getReportName());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("reportDate", bean.getStart_date());
			
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/PttyClaimOslrReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");
		
		}catch (Exception e1) {
			e1.printStackTrace();
		} 
}
	public void getSOAReport(ReportsBean bean, String branchCode, String downloadType, ByteArrayOutputStream bos1){
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("startDate", bean.getStart_date());
			jasperParameter.put("endDate", bean.getEnd_date());
			jasperParameter.put("productId", bean.getProductId());
			jasperParameter.put("imagePath", bean.getImagePath());
			jasperParameter.put("branchCode", branchCode);
			//jasperParameter.put("uwYear", bean.getUwYear());
			jasperParameter.put("cedingName", bean.getCedingCoName());
			jasperParameter.put("brokerName", bean.getBrokerName());
			jasperParameter.put("cedingId", bean.getCedingId());
			jasperParameter.put("brokerId", bean.getBrokerId());
			jasperParameter.put("systemDate", bean.getSysDate());
			jasperParameter.put("productName", bean.getProductName());
			//jasperParameter.put("loginId", bean.getLoginId());
			//jasperParameter.put("deptId", bean.getDept());
			jasperParameter.put("reportName", bean.getReportName());
			jasperParameter.put("settledItems", bean.getSettledItems());
			jasperParameter.put("unAllocatedCash", bean.getUnAllocatedCash());
			jasperParameter.put("prclSeparate", bean.getSaperatePRCLYN());
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/SOAReportXls.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");
		
		}catch (Exception e1) {
			e1.printStackTrace();
		} 
		
	}
	public void getJournalViewDownload(ReportsBean bean, String branchCode,String downloadType, ByteArrayOutputStream bos1) {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			//final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			//jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			//String type="";
			/*if("27".equalsIgnoreCase(bean.getTypeId())){
				type="PREMIUM_JV";
			}else if("28".equalsIgnoreCase(bean.getTypeId())){
				type="CLAIM_JV";
			}else if("29".equalsIgnoreCase(bean.getTypeId())){
				type="TREASURY_JV1";
			}else if("30".equalsIgnoreCase(bean.getTypeId())){
				type="TREASURY_JV2";
			}else if("46".equalsIgnoreCase(bean.getTypeId())){
				type="PIPELINE_JV";
			}else if("47".equalsIgnoreCase(bean.getTypeId())){
				type="OUTSTANDING_JV";
			}else if("48".equalsIgnoreCase(bean.getTypeId())){
				type="PIPELINE_UPR_JV";
			}else if("49".equalsIgnoreCase(bean.getTypeId())){
				type="BOOKED_UPR_JV";
			}else if("50".equalsIgnoreCase(bean.getTypeId())){
				type="ADJUSTMENT_JV";
			}else if("51".equalsIgnoreCase(bean.getTypeId())){
				type="RETRO_PR_JV";
			}else if("52".equalsIgnoreCase(bean.getTypeId())){
				type="RETRO_XL_JV";
			}else if("53".equalsIgnoreCase(bean.getTypeId())){
				type="MANUAL_JV";
			}*/
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("startDate", bean.getStartDate());
			jasperParameter.put("endDate", bean.getEndDate());
			jasperParameter.put("status", bean.getStatus());
			jasperParameter.put("type",bean.getJournalType());
			jasperParameter.put("typeId", bean.getTypeId());
			jasperParameter.put("reportName", bean.getReportName());
			
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/JournalViewReport.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");
		
		}catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
	public void getInsallmentDueReport(ReportsBean bean, String branchCode, String downloadType, ByteArrayOutputStream bos1) throws SQLException {
		Connection connection=null;
		try {
			connection=DatabaseDAO.getInstance().getDBConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try{
			
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("pvdate", bean.getEnd_date());
			jasperParameter.put("Pvbranch", bean.getBranchCode());
			jasperParameter.put("pvProductId", bean.getProductId());
			jasperParameter.put("pvbroker", bean.getBrokerId());
			jasperParameter.put("pvceding",bean.getCedingId());
			jasperParameter.put("pvallocateYN", bean.getAllocationYN());
			
			System.out.println(jasperParameter);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			String jasperPath="";
			jasperPath= request.getSession().getServletContext().getRealPath("/"+"/Jasper/InstallmentDue.jasper");
			if(new File(jasperPath).exists())
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);

			else
			{
				String path=jasperPath.replace(".jasper", ".jrxml");
				String temp = JasperCompileManager.compileReportToFile(path);
				logger.info("File "+temp);
				jasperReport = (JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
			}

			logger.info("Filling report started.........");			 
			jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
			exporter(downloadType, jasperPrint, bos1);
			logger.info("Filling report Ended.........");
		
		}catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
