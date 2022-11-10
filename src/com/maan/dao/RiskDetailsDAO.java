package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.RiskDetailsBean;

public interface RiskDetailsDAO {
	
	 boolean saveRiskDeatilsSecondForm(RiskDetailsBean bean, String productId) ;
		boolean riskDetailsEditMode(RiskDetailsBean bean, boolean contarctmode) ;
		boolean updateProportionalTreatyDao(RiskDetailsBean bean, String pid) ;
	    boolean viewRiskDetails(RiskDetailsBean bean, String pid) ;
		String getNextContractNumber() ;
		boolean checkProductMatch(String pid,String conno, boolean contarctmode);
		boolean checkAvialability(RiskDetailsBean bean, String pid);
	    boolean saveSecondMode(RiskDetailsBean bean, String productId);
		void showRetroContracts(RiskDetailsBean bean,String productCode,boolean view) ; 
		boolean showSecondPageData(String proposal,RiskDetailsBean bean,String pid);
		boolean showSecondPageData1(String proposal,RiskDetailsBean bean,String pid) ;
		boolean showLayerBrokerage(String layerProposalNo,RiskDetailsBean bean);
		int getEditMode(String proposalNo);
		public String getRetroContractDetails(RiskDetailsBean bean);
		public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean bean,int flag, String uWYear);
		public List<Map<String, Object>> getValidation(RiskDetailsBean bean) ;
		public boolean getLayerDuplicationCheck(RiskDetailsBean bean) ;
		boolean firstInsert(RiskDetailsBean beanObj, String pid, boolean flag,boolean amednId);
		String getShortname(RiskDetailsBean bean);
		void insertCrestaDetails(RiskDetailsBean bean);
		List<Map<String, Object>> getCrestaDetailList(RiskDetailsBean bean);
		void BaseLayerStatus(RiskDetailsBean bean, String pid);
		int getCrestaCount(RiskDetailsBean bean);
		List<Map<String, Object>> getScaleCommissionList(RiskDetailsBean bean);
		String ScaleCommissionInsert(RiskDetailsBean bean);
		int getBonusListCount(RiskDetailsBean bean, String type);
		String CheckCrestaValue(RiskDetailsBean bean, String val, String id, String countryId);
		String insertProfitCommission(RiskDetailsBean bean);
		List<Map<String, Object>> ProfitCommissionList(RiskDetailsBean bean);
		int getProfitListCount(RiskDetailsBean bean);
		void getprofitCommissionEdit(RiskDetailsBean bean);
		void getprofitCommissionDelete(RiskDetailsBean bean);
		String getprofitCommissionEnable(RiskDetailsBean bean, String type);
		int CommissionTypeCount(RiskDetailsBean bean);
		String doUploadDocDetails(List<Object[]> list, Object[] args);
		void CancelProposal(RiskDetailsBean bean, String oldProNo);
		boolean GetShareValidation(RiskDetailsBean bean);
		void getRetentionDetails(RiskDetailsBean bean);
		void getcalculateSC(RiskDetailsBean bean);
		void getSectionEditMode(RiskDetailsBean bean);
		boolean getSectionDuplicationCheck(RiskDetailsBean bean);
}
