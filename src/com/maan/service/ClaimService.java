package com.maan.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.maan.bean.ClaimBean;
import com.maan.common.util.Validation;
import com.maan.dao.ClaimDAO;
import com.maan.dao.impl.ClaimDAOImpl;

public class ClaimService {
	
	private static final ClaimDAO DAO=new ClaimDAOImpl();

	public boolean  InitCliam(final ClaimBean beanObj,final int icliam){
		boolean returnFlag=false;
		
			if(icliam==1)
			{
				returnFlag=DAO.contractDetails(beanObj,icliam);
			}
			
			if(icliam==2)
			{
				returnFlag=DAO.insertCliamDetails(beanObj,icliam);
			}
			
			if(icliam==3)
			{
				returnFlag=DAO.insertCliamDetails(beanObj,icliam);
			}
			
			if(icliam==4)
			{
				returnFlag=DAO.contractDetails(beanObj,icliam);
			}
			
			 if(icliam==5)
			{
				returnFlag=DAO.contractDetails(beanObj,icliam);
			} 
			
			if(icliam==6)
			{
				returnFlag=DAO.contractDetails(beanObj,icliam);
			}
			
			if(icliam==7)
			{
				returnFlag=DAO.contractDetails(beanObj,icliam);
			}
			
			if(icliam==8)
			{
				returnFlag=DAO.insertCliamDetails(beanObj,icliam);
			}
			
			if(icliam==9)
			{
				returnFlag=DAO.insertCliamDetails(beanObj,icliam);
			}
			
			if(icliam==10)
			{
				returnFlag=DAO.contractDetails(beanObj,10);
			}

			if(icliam==11)
			{
				returnFlag=DAO.contractDetails(beanObj,10);
			}
			if(icliam==12)
			{
				returnFlag=DAO.insertCliamDetails(beanObj,icliam);
			}
			
		return returnFlag;
	}
	public List CliamList(ClaimBean beanObj,final int cliam){
		List list=null;	
		list=DAO.cliamTableList(beanObj,cliam);
	 	return list;
	}
	public boolean BusinessValidation(ClaimBean formObj, int mode){
		boolean businesFlag=false;
		double Amount=0;
		double TotalPayment=0;
		if(mode==1)
		{
			Amount=DAO.businessValidaion(formObj,mode);
			TotalPayment=DAO.businessValidaion(formObj, 3);
			if(Amount  < (Double.parseDouble(formObj.getPaid_Amount_Orig_curr())+TotalPayment))
			{
				businesFlag=true;
			}
		}else if(mode==2)
		{
			Amount=DAO.businessValidaion(formObj,mode);
			if(Amount  > Double.parseDouble(formObj.getUpdate_Rivised_original_Cur()))
			{
				businesFlag=true;
			}
		}else if(mode==3)
		{
			Validation val=new Validation();
			if(Validation.ValidateTwo(DAO.getClaimDates(formObj,3),formObj.getDate_of_Loss()).equalsIgnoreCase("Invalid"))
			{
				businesFlag=true;
			}
		}else if(mode==4)
		{
			double lossEstRev=DAO.businessValidaion(formObj,mode);
			double paidSum=DAO.businessValidaion(formObj,5);			
			if(lossEstRev<(Double.parseDouble(StringUtils.isBlank(formObj.getPaid_Amount_Orig_curr())?"0":formObj.getPaid_Amount_Orig_curr())+paidSum)){
				businesFlag=true;
			}
		}else if(mode==6){
			String status=DAO.getClaimStatus(formObj);		
			if("Closed".equalsIgnoreCase(status)){
				businesFlag=true;
			}
		}else if(mode==7)
		{
			Amount=DAO.businessValidaion(formObj,mode);
			if(Double.parseDouble(formObj.getPaid_claim_os())>Amount)
			{
				businesFlag=true;
			}
		}else if(mode==8)
		{
			Amount=DAO.businessValidaion(formObj,mode);
			if(Double.parseDouble(formObj.getSurveyor_fee_os())>Amount)
			{
				businesFlag=true;
			}
		}else if(mode==9)
		{
			Amount=DAO.businessValidaion(formObj,mode);
			if(Double.parseDouble(formObj.getOther_prof_fee_os())>Amount)
			{
				businesFlag=true;
			}
		}
		else if(mode==10){
			String status=DAO.getClaimStatus(formObj);		
			if("Repudiate".equalsIgnoreCase(status)){
				businesFlag=true;
			}
		}
		return businesFlag;
	}
	public String getClaimDate(ClaimBean formObj,int mode){
	 	String result=DAO.getClaimDates(formObj,mode);
		return result;
	}
	public String getShortname(ClaimBean bean) {
		return DAO.getShortname( bean);
	}
	public String getCurrencyID(String claimNo, String contractNo,String branchCode) {
		return DAO.getCurrencyID(claimNo,contractNo,branchCode);
	}
	public List<Map<String,Object>> allocationList(ClaimBean bean) {
		return DAO.allocationList(bean);
	}
	public List allocList(ClaimBean bean) {
		return DAO.allocList(bean);
	}
	public String getUnderwritter(ClaimBean bean) {
		return DAO.getUnderwritter(bean);
	}
	public List<ClaimBean> claimlist(ClaimBean bean) {
		return DAO.claimlist(bean);
	}
	public List<Map<String, Object>> productIdList(ClaimBean bean) {
		return DAO.productIdList(bean);
	}
	public List<ClaimBean> contractidetifierlist(ClaimBean bean) {
		return DAO.contractidetifierlist(bean);
	}
	public List<ClaimBean> claimpaymentlist(ClaimBean bean) {
		return  DAO.claimpaymentlist(bean);
	}
	public String cedentNumberCheck(ClaimBean bean) {
		return DAO.cedentNumberCheck(bean);
	}
	
	public int ClaimpaymentCount(ClaimBean bean) {
		return DAO.claimpaymentCount(bean);
	}
	public double getClaimsAmount(ClaimBean formObj, int mode){
		return DAO.businessValidaion(formObj,mode);
	}
	public List<Map<String, Object>> XlPremiumList(ClaimBean bean, String countryId) {
		return DAO.XlPremiumList(bean,countryId);
	}
	public  List<Map<String, Object>> ClaimPaidList(ClaimBean bean, String countryId) {
		return DAO.ClaimPaidList(bean,countryId);
	}
	public List<Map<String, Object>> getReInstatementTypeList(ClaimBean bean) {
		return DAO.getReInstatementTypeList(bean);
	}
	public String getReInsValue(ClaimBean bean) {
		return DAO.getReInsValue(bean);
		
	}
	public String getProposalNo(ClaimBean bean) {
		return DAO.getProposalNo(bean);
	}
	public void claimPaymentEdit(ClaimBean bean) {
		 DAO.claimPaymentEdit(bean);
		
	}
	public String getContractNo(ClaimBean bean) {
		return DAO.getContractNO(bean);
		
	}
	public int claimMasterValidation(ClaimBean bean, String type) {
		return DAO.claimMasterValidation(bean,type);		
	}
	public List<Map<String, Object>> CedentNoList(ClaimBean bean) {
		return DAO.claimNoList(bean);
	}
	public List<Map<String,Object>> getPaymentNoList(ClaimBean bean) {
		return DAO.getPaymentNoList(bean);
	}
	public List<Map<String, Object>> getClaimAuthView(ClaimBean bean) {
		return DAO.getClaimAuthView(bean);
	}
	public String getDuplicateCedentClaim(ClaimBean bean) {
		return DAO.getDuplicateCedentClaim(bean);
	}
	
}
