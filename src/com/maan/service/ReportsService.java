package com.maan.service;

import java.util.List;
import java.util.Map;

import com.maan.bean.ReportsBean;
import com.maan.dao.ReportsDAO;
import com.maan.dao.impl.ReportsDAOImpl;

public class ReportsService {
	ReportsDAO dao= new ReportsDAOImpl();
	public List<Map<String,Object>> getMoveMentInit(ReportsBean bean){
		return dao.getMoveMentInit(bean);	 
	}

	public List<Map<String,Object>> getClaimJournelInit(ReportsBean bean) {
		return dao.getClaimJournelInit(bean);	 
	}
	
	public List<Map<String,Object>> getJournalListCrnt(ReportsBean bean) {
		return dao.getJournalListCrnt(bean);	 
	}

	public List<Map<String,Object>> getClaimMoveMentInit(ReportsBean bean) {
		return dao.getClaimMoveMentInit(bean);	 
	}

	public List<Map<String,Object>> getProductDropDown(String branchCode,String typeId) {
		return dao.getProductDropDown(branchCode,typeId);
	}

	public String getCedingCompany(ReportsBean bean) {
		return dao.getCedingCompany(bean);
	}

	public String getReportName(ReportsBean bean){
		return dao.getReportName(bean);
	}

	public List<Map<String,Object>> getPendingOffersList(ReportsBean bean){
		return dao.getPendingOffersList(bean);
	}

	public List<Map<String,Object>> getPolicyRegisterList(ReportsBean bean){
		return dao.getPolicyRegisterList(bean);
	}

	public List<Map<String,Object>> getPremiumRegisterList(ReportsBean bean){
		return dao.getPremiumRegisterList(bean);
	} 

	public List<Map<String,Object>> getClaimRegisterList(ReportsBean bean){
		return dao.getClaimRegisterList(bean);
	}

	public List<Map<String,Object>> getRenewalDueList(ReportsBean bean){
		return  dao.getRenewalDueList(bean);
	}

	public List<Map<String,Object>> getRetroQuarterlyReport(ReportsBean bean){
		return dao.getRetroQuarterlyReport(bean);	 
	}

	public List<Map<String,Object>> getInwardRetroMappingReport(ReportsBean bean){
		return dao.getInwardRetroMappingReport(bean);	 
	}
	public List<Map<String,Object>> getRetroInwardMappingReport(ReportsBean bean){
		return dao.getRetroInwardMappingReport(bean);	 
	}

	public List<Map<String,Object>> getTransactionMasterReport(ReportsBean bean){
		return dao.getTransactionMasterReport(bean);	 
	}

	public List<Map<String,Object>> getDebtorsAgeingReport(ReportsBean bean){
		return dao.getDebtorsAgeingReport(bean);	 
	}

	public List<Map<String,Object>> getMoveMntSummary(ReportsBean bean){
		return dao.getMoveMntSummary(bean);
	}

	public List<Map<String,Object>> getCompanyInfoList(ReportsBean bean){
		return dao.getCompanyInfoList(bean);
	}

	public List<Map<String,Object>> getColumnInfoList(ReportsBean bean) {	
		return dao.getColumnInfoList(bean);
	}
	
	public List<Map<String,Object>> getMoveMentPage(ReportsBean bean) {
		return dao.getMoveMentPage(bean);	 
	}
	
	public List<ReportsBean> getViewAll(ReportsBean bean){
		return dao.getViewAll(bean);	 
	}
	
	public List<Map<String,Object>> getClaimMoveMentPage(ReportsBean bean){
		return dao.getClaimMoveMentPage(bean);
	}
	
	public List<Map<String,Object>> getJournelPage(ReportsBean bean){
		return dao.getJournelPage(bean);	 
	}
	public String insertCLMovement(ReportsBean bean){
		return dao.insertCLMovement(bean);
	}
	
	public String insertMovement(ReportsBean bean){
		return dao.insertMovement(bean);
	}
	
	public int getCountMovementRecord(String branchCode,String accQtr,String accYear) {
		return dao.getCountMovementRecord(branchCode,accQtr,accYear);
	}
	
	public List<ReportsBean> getViewJurnelAll(ReportsBean bean){
		return dao.getViewJurnelAll(bean);	 
	}
	
	public String journelInsertMovement(ReportsBean reportsBean){
		return dao.journelInsertMovement(reportsBean);
	}
	
	public List<Map<String,Object>> getJournelMoveMentInit(ReportsBean reportsBean){
		return dao.getJournelMoveMentInit(reportsBean);	 
	}

	public List<Map<String, Object>> getPipelineMovementJvDetails(
			ReportsBean bean) {		
		return dao.getPipelineMovementJvDetails(bean);
	}

	public List<Map<String, Object>> getBookedPremiumDetails(ReportsBean bean) {
		return dao.getBookedPremiumDetails(bean);
	}
	
	public List<Map<String, Object>> getBookedUprDetails(ReportsBean bean) {
		return dao.getBookedUprDetails(bean);
	}
	
	public List<Map<String, Object>> getPipelinedWrtnDetails(ReportsBean bean) {
		return dao.getPipelinedWrtnDetails(bean);
	}

	public String callProBookedPremium(ReportsBean bean) {
		return dao.callProBookedPremium(bean);
	}

	public String callProBookedUpr(ReportsBean bean) {
		return dao.callProBookedUpr(bean);
	}

	public String callPipelineMoveJv(ReportsBean bean) {
		return dao.callPipelineMoveJv(bean);
	}

	public String callProPipelineWritten(ReportsBean bean) {
		return dao.callProPipelineWritten(bean);
	}

	public List<Map<String, Object>> getBookedUprInit(ReportsBean bean) {		
		return dao.getBookedUprInit(bean);
	}

	public List<Map<String, Object>> getBookedPremiumInit(ReportsBean bean) {
		return dao.getBookedPremiumInit(bean);
	}

	public List<Map<String, Object>> getPipelineWrittenInit(ReportsBean bean) {
		return dao.getPipelineWrittenInit(bean);
	}

	public String checkProBookedUpr(ReportsBean bean) {
		return dao.checkProBookedUpr(bean);		
	}

	public String checkProPipelineWrtn(ReportsBean bean) {
		return dao.checkProPipelineWrtn(bean);		
	}

	public String checkProBookedPremium(ReportsBean bean) {
		return dao.checkProBookedPremium(bean);	
		
	}
	public List<Map<String, Object>> getallocationReportList(ReportsBean bean){
		return dao.getallocationReportList(bean);	 
	}
	
	public List<Map<String, Object>> getClaimPaidRegisterList(ReportsBean bean){
		return dao.getClaimPaidRegisterList(bean);	 
	}

	public List<Map<String, Object>> getPayRecRegisterList(ReportsBean bean) {
		return dao.getPayRecRegisterList(bean);
	}

	public List<Map<String, Object>> getRetroRegisterList(ReportsBean bean) {
		return dao.getRetroRegisterList( bean);
	}

	public List<Map<String, Object>> getTreatyWithdrawRegisterList(ReportsBean bean) {
		return dao.getTreatyWithdrawRegisterList(bean);
	}

	public List<Map<String, Object>> getJVReports(ReportsBean bean) {
		return dao.getJVReports(bean);
	}

	public List<Map<String, Object>> getInstallmentDueReport(ReportsBean bean) {
		return dao.getInstallmentDueReport(bean);
	}

	public List<Map<String, Object>> getTrailBalanceReport(ReportsBean bean) {
		return dao.getTrailBalanceReport(bean);
	}

	public List<Map<String, Object>> getPortfolioOutPendingReport(ReportsBean bean) {
		return dao.getPortfolioOutPendingReport(bean);
	}

	public List<Map<String, Object>> getPptySOAPendingReport(ReportsBean bean) {
		return dao.getPptySOAPendingReport(bean);
	}

	public List<Map<String, Object>> getUWStatisticsReport(ReportsBean bean) {
		return dao.getUWStatisticsReport(bean);
	}

	public List<Map<String, Object>> getIEReport(ReportsBean bean) {
		return dao.getIEReport(bean);
	}

	public List<Map<String, Object>> getPptyOSLRClaimReport(ReportsBean bean) {
		return dao.getPptyOSLRClaimReport(bean);
	}
	public String getStartDateStatus(ReportsBean bean) {
		return dao.getStartDateStatus(bean);
	}

	public String getEndDateStatus(ReportsBean bean) {
		return dao.getEndDateStatus(bean);
	}
}
