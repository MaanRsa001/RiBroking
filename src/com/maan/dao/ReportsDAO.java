package com.maan.dao;

import java.util.List;
import java.util.Map;
import com.maan.bean.ReportsBean;

public interface ReportsDAO {

	List<Map<String,Object>> getMoveMentInit(ReportsBean bean);
	List<Map<String,Object>> getClaimJournelInit(ReportsBean bean);
	List<Map<String,Object>> getJournalListCrnt(ReportsBean bean);
	List<Map<String,Object>> getClaimMoveMentInit(ReportsBean bean);
	List<Map<String,Object>> getProductDropDown(String branchCode,String typeId);
	String getCedingCompany(ReportsBean bean);
	String getReportName(ReportsBean bean);
	List<Map<String,Object>> getPendingOffersList(ReportsBean bean);
	List<Map<String,Object>> getPolicyRegisterList(ReportsBean bean);
	List<Map<String,Object>> getPremiumRegisterList(ReportsBean bean);
	List<Map<String,Object>> getClaimRegisterList(ReportsBean bean);
	List<Map<String,Object>> getRenewalDueList(ReportsBean bean);
	List<Map<String,Object>> getRetroQuarterlyReport(ReportsBean bean);
	List<Map<String,Object>> getInwardRetroMappingReport(ReportsBean bean);
	List<Map<String,Object>> getRetroInwardMappingReport(ReportsBean bean);
	List<Map<String,Object>> getTransactionMasterReport(ReportsBean bean);
	List<Map<String,Object>> getDebtorsAgeingReport(ReportsBean bean);
	List<Map<String,Object>> getMoveMntSummary(ReportsBean bean);
	List<Map<String,Object>> getCompanyInfoList(ReportsBean bean);
	List<Map<String,Object>> getColumnInfoList(ReportsBean bean);
	List<Map<String,Object>> getMoveMentPage(ReportsBean bean);
	List<ReportsBean> getViewAll(ReportsBean bean);
	List<Map<String,Object>> getClaimMoveMentPage(ReportsBean bean);
	List<Map<String,Object>> getJournelPage(ReportsBean bean);
	String insertCLMovement(ReportsBean bean);
	String insertMovement(ReportsBean bean);
	int getCountMovementRecord(String branchCode, String accQtr, String accYear);
	List<ReportsBean> getViewJurnelAll(ReportsBean reportsBean);
	String journelInsertMovement(ReportsBean reportsBean);
	List<Map<String,Object>> getJournelMoveMentInit(ReportsBean reportsBean);
	List<Map<String, Object>> getPipelineMovementJvDetails(ReportsBean bean);
	List<Map<String, Object>> getBookedPremiumDetails(ReportsBean bean);
	List<Map<String, Object>> getBookedUprDetails(ReportsBean bean);	
	List<Map<String, Object>> getPipelinedWrtnDetails(ReportsBean bean);
	String callProBookedPremium(ReportsBean bean);
	String callProBookedUpr(ReportsBean bean);
	String callPipelineMoveJv(ReportsBean bean);
	String callProPipelineWritten(ReportsBean bean);
	List<Map<String, Object>> getBookedUprInit(ReportsBean bean);
	List<Map<String, Object>> getBookedPremiumInit(ReportsBean bean);
	List<Map<String, Object>> getPipelineWrittenInit(ReportsBean bean);
	String checkProBookedUpr(ReportsBean bean);
	String checkProPipelineWrtn(ReportsBean bean);
	String checkProBookedPremium(ReportsBean bean);
	List<Map<String, Object>> getallocationReportList(ReportsBean bean);
	
	List<Map<String, Object>> getClaimPaidRegisterList(ReportsBean bean);
	List<Map<String, Object>> getPayRecRegisterList(ReportsBean bean);
	List<Map<String, Object>> getRetroRegisterList(ReportsBean bean);
	List<Map<String, Object>> getTreatyWithdrawRegisterList(ReportsBean bean);
	List<Map<String, Object>> getJVReports(ReportsBean bean);
	List<Map<String, Object>> getInstallmentDueReport(ReportsBean bean);
	List<Map<String, Object>> getTrailBalanceReport(ReportsBean bean);
	List<Map<String, Object>> getPortfolioOutPendingReport(ReportsBean bean);
	List<Map<String, Object>> getPptySOAPendingReport(ReportsBean bean);
	List<Map<String, Object>> getUWStatisticsReport(ReportsBean bean);
	List<Map<String, Object>> getIEReport(ReportsBean bean);
	List<Map<String, Object>> getPptyOSLRClaimReport(ReportsBean bean);
	String getStartDateStatus(ReportsBean bean);
	String getEndDateStatus(ReportsBean bean);
	
}
