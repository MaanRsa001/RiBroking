package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.RiskDetailsBean;

public interface RetroDAO {

	boolean firstInsert(RiskDetailsBean beanObj, String pid, boolean flag,boolean amednId);
	public boolean getLayerDuplicationCheck(RiskDetailsBean bean);
	boolean showSecondPageData1(String proposal,RiskDetailsBean bean,String pid) ;
	public List<Map<String, Object>> getValidation(RiskDetailsBean bean) ;
	int getEditMode(String proposalNo);
	boolean riskDetailsEditMode(RiskDetailsBean bean, boolean contarctmode) ;
	boolean saveSecondMode(RiskDetailsBean bean, String productId);
	boolean showSecondPageData(String proposal,RiskDetailsBean bean,String pid);
	public List<Map<String, Object>> getRetroContractDetailsList(RiskDetailsBean bean,int flag);
	boolean saveRiskDeatilsSecondForm(RiskDetailsBean bean, String productId) ;
	public String getRetroContractDetails(RiskDetailsBean bean);
	boolean showLayerBrokerage(String layerProposalNo,RiskDetailsBean bean);
	boolean updateProportionalTreatyDao(RiskDetailsBean bean, String pid) ;
	void showRetroCess(RiskDetailsBean bean, int mode);
	boolean checkAvialability(RiskDetailsBean bean, String pid);
	boolean viewRiskDetails(RiskDetailsBean bean, String pid) ;
	String getShortname(RiskDetailsBean bean);
	void insertRetroDetails(RiskDetailsBean bean);
	String getEndDate(RiskDetailsBean bean);
	int getCrestaCount(RiskDetailsBean bean);
	int getBonusListCount(RiskDetailsBean bean, String type);
    int CommissionTypeCount(RiskDetailsBean bean);
    String doUploadDocDetails(List<Object[]> list, Object[] args);
	String PreviouRetroTypeChect(RiskDetailsBean bean);
}
