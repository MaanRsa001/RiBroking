package com.maan.dao;

import java.util.List;
import java.util.Map;

import com.maan.bean.ClaimBean;

public interface ClaimDAO {	
	public boolean contractDetails(final ClaimBean beanObj,final int icliam);
	public boolean insertCliamDetails(final ClaimBean beanObj,final int icliam);	
	public List cliamTableList(ClaimBean beanObj,final int cliam);	
	public double businessValidaion(ClaimBean formObj, int mode);
	public String getClaimStatus(ClaimBean formObj);		
	public String getClaimDates(ClaimBean formObj,int mode);
	public String getShortname(ClaimBean bean);
	public String getCurrencyID(String claimNo, String contractNo,String branchCode);
	public List<Map<String,Object>> allocationList(ClaimBean bean);
	public List allocList(ClaimBean bean);
	public String getUnderwritter(ClaimBean bean);
	public List<ClaimBean> claimlist(ClaimBean bean);
	public List<Map<String, Object>> productIdList(ClaimBean bean);
	public List<ClaimBean> contractidetifierlist(ClaimBean bean);
	public List<ClaimBean> claimpaymentlist(ClaimBean bean);
	public String cedentNumberCheck(ClaimBean bean);
	public int claimpaymentCount(ClaimBean bean);
	public List<Map<String, Object>> XlPremiumList(ClaimBean bean, String countryId);
	public  List<Map<String, Object>> ClaimPaidList(ClaimBean bean, String countryId);
	public List<Map<String, Object>> getReInstatementTypeList(ClaimBean bean);
	public String getReInsValue(ClaimBean bean);
	public String getProposalNo(ClaimBean bean);
	public void claimPaymentEdit(ClaimBean bean);
	public String getContractNO(ClaimBean bean);
	public int claimMasterValidation(ClaimBean bean, String type);
	public List<Map<String, Object>> claimNoList(ClaimBean bean);
	public List<Map<String,Object>> getPaymentNoList(ClaimBean bean);
	public List<Map<String, Object>> getClaimAuthView(ClaimBean bean);
	public String getDuplicateCedentClaim(ClaimBean bean);
}
