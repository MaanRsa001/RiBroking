package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.RiskDetailsBean;

public interface XolDAO {
	boolean firstInsert(RiskDetailsBean beanObj, String pid, boolean flag,boolean amednId);
	public boolean getLayerDuplicationCheck(RiskDetailsBean bean);
	boolean showSecondPageData1(String proposal,RiskDetailsBean bean,String pid);
	public List<Map<String, Object>> getValidation(RiskDetailsBean bean);
	int getEditMode(String proposalNo);
	boolean riskDetailsEditMode(RiskDetailsBean bean, boolean contarctmode) ;
	boolean saveRiskDeatilsSecondForm(RiskDetailsBean bean, String productId) ;
	public String getRetroContractDetails(RiskDetailsBean bean);
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean bean,int flag, String uWYear);
	boolean showSecondPageData(String proposal,RiskDetailsBean bean,String pid);
	boolean showLayerBrokerage(String layerProposalNo,RiskDetailsBean bean);
	boolean updateProportionalTreatyDao(RiskDetailsBean bean, String pid) ;
	void showRetroContracts(RiskDetailsBean bean,String productCode,boolean view) ;
	boolean saveSecondMode(RiskDetailsBean bean, String productId);
	void showRetroCess(RiskDetailsBean bean, int mode);
	boolean checkProductMatch(String pid,String conno, boolean contarctmode);
	boolean checkAvialability(RiskDetailsBean bean, String pid);
	boolean viewRiskDetails(RiskDetailsBean bean, String pid);
	void showRetroCess1(RiskDetailsBean bean, int mode);
	String getShortname(RiskDetailsBean bean);
	List<Map<String, Object>> getReInstatementDetailsList(RiskDetailsBean bean);
	String insertReinstatementDetails(RiskDetailsBean bean);
	int getReInstatementCount(RiskDetailsBean bean, String type);
	int getCrestaCount(RiskDetailsBean bean);
	void MoveReinstatementEmptyData(RiskDetailsBean bean);
	String getSumOfCover(RiskDetailsBean bean, String pid);
	String doUploadDocDetails(List<Object[]> list, Object[] args);
	int getBonusListCount(RiskDetailsBean bean);
	String LowClaimBonusInser(RiskDetailsBean bean);
	List<Map<String, Object>> getLowClaimBonusList(RiskDetailsBean bean);
	void insertIEModule(RiskDetailsBean bean);
	List<Map<String, Object>> getInclusionExList(RiskDetailsBean bean);
	void BaseLayerStatus(RiskDetailsBean bean, String pid);
	boolean GetShareValidation(RiskDetailsBean bean);
}
