package com.maan.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.action.admin.AdminAction;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.PortfolioBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.AuthendicationDAO;

public class AuthendicationDAOImp extends MyJdbcTemplate implements AuthendicationDAO {
	
	private static final Logger LOGGER = LogUtil.getLogger(AuthendicationDAOImp.class);
	public List<FaculPremiumBean> AuthenticationList(FaculPremiumBean bean) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<FaculPremiumBean> finalizeBean = new ArrayList<FaculPremiumBean>();
		try{
			String query = "";
			Object args[] = new Object[2];
			if("Multiple".equalsIgnoreCase(bean.getUploadStatus()) &&  bean.getCheckItem()!=null){
				String val = bean.getCheckItem().replace(",", "','");
				query = "SELECT TRANSACTION_NO,PREMIUM_CLASS,REQUEST_NO,LOGIN_ID,ACCOUNT_PERIOD_QTR,INSTALMENT_NUMBER,RECEIPT_NO,PREMIUM_SUBCLASS, REVERSEL_STATUS,REVERSE_TRANSACTION_NO,PRODUCT_ID,CONTRACT_NO,case TRA.PRODUCT_ID when 2 then (SELECT DISTINCT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=49 AND TYPE=TRA.ACCOUNT_PERIOD_QTR and STATUS='Y') || ' ' || TRA.ACCOUNT_PERIOD_YEAR else TRA.ACCOUNT_PERIOD_QTR || ' ' || ACCOUNT_PERIOD_YEAR  end ACC_PER FROM RSK_PREMIUM_DETAILS_TEMP TRA WHERE BRANCH_CODE=?  AND REQUEST_NO IN('"+val+"')";
				args = new Object[1];
				args[0] = bean.getBranchCode();
			}else{
			query = getQuery("GET_AUTHENTICATION_LIST");
			args = new Object[2];
			args[0] = bean.getBranchCode();
			args[1] = "P";
			}
			list = this.mytemplate.queryForList(query,args);
			if(list.size()>0)
			 {
				 for(int i=0;i<list.size();i++){
					 Map<String,Object>	 editPremium=list.get(i);
					 FaculPremiumBean beanObj = new FaculPremiumBean();								
					 beanObj.setTransaction(editPremium.get("TRANS_DATE")==null?"":editPremium.get("TRANS_DATE").toString()); 
					if("EP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
						beanObj.setAccount_Period("Endorsement Premium");
			    	}
					else if("RTP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
						beanObj.setAccount_Period("Return Premium");
			    	}
					else if("RVP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
						beanObj.setAccount_Period("Reversal Premium");
			    	}
					else if("RP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
						beanObj.setAccount_Period("Reinstatement Premium");
			    	}
					else if("AP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
						beanObj.setAccount_Period("Adjustment Premium");
			    	}
					else
			    	{
						beanObj.setAccount_Period((editPremium.get("ACC_PER")==null?"":editPremium.get("ACC_PER").toString()));
			    	}						
					beanObj.setProductId(editPremium.get("PRODUCT_ID")==null?"":editPremium.get("PRODUCT_ID").toString());
					beanObj.setContNo(editPremium.get("CONTRACT_NO")==null?"":editPremium.get("CONTRACT_NO").toString());
					beanObj.setRequestNo(editPremium.get("REQUEST_NO")==null?"":editPremium.get("REQUEST_NO").toString());
					beanObj.setTransactionNo(editPremium.get("TRANSACTION_NO")==null?"":editPremium.get("TRANSACTION_NO").toString());
					beanObj.setLoginId(editPremium.get("LOGIN_ID")==null?"":editPremium.get("LOGIN_ID").toString());
					beanObj.setDepartmentId(editPremium.get("PREMIUM_CLASS")==null?"":editPremium.get("PREMIUM_CLASS").toString());
					beanObj.setLayerNo(editPremium.get("LAYER_NO")==null?"":editPremium.get("LAYER_NO").toString());
			        finalizeBean.add(beanObj);   
				}
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalizeBean;
	}
	public String AuthenticationChanges(FaculPremiumBean beanObj) {
		String query = getQuery("FAC_TEMP_STATUS_UPDATE");
		if("multiple".equalsIgnoreCase(beanObj.getUploadStatus())){
			String val[] = beanObj.getCheckItem().split(",");
			for(int i=0;i<val.length;i++){
				beanObj.setRequestNo(val[i]);
				getPremiumDetails(beanObj);
				if("A".equalsIgnoreCase(beanObj.getApproveStatus())){
					
					beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
				}
				String args[]  = new String[5];
		 		args[0] = beanObj.getApproveStatus();
		 		args[1] = beanObj.getLoginId();
		 		args[2] = beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
		 		args[3]= beanObj.getRequestNo();
		 		args[4]= beanObj.getBranchCode();
		 		this.mytemplate.update(query,args);
		 		updateTransactionnDetails(beanObj);
		 		if("A".equalsIgnoreCase(beanObj.getApproveStatus())){
		 			updateTempToMain(beanObj);
					}
		 		 	
			}
		}else{
			getPremiumDetails(beanObj);
			if("A".equalsIgnoreCase(beanObj.getApproveStatus())){
				beanObj.setTransactionNo(new DropDownControllor().getSequence("Premium",beanObj.getProductId(),beanObj.getDepartmentId(), beanObj.getBranchCode(),"",beanObj.getTransaction()));
			}
			String args[]  = new String[5];
	 		args[0] = beanObj.getApproveStatus();
	 		args[1] = beanObj.getLoginId();
	 		args[2] = beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo();
	 		args[3]= beanObj.getRequestNo();
	 		args[4]= beanObj.getBranchCode();
	 		this.mytemplate.update(query,args);
	 		updateTransactionnDetails(beanObj);
		 		if("A".equalsIgnoreCase(beanObj.getApproveStatus())){
		 			updateTempToMain(beanObj);	
					}
		 		
		}
		return null;
	}
	private void updateTransactionnDetails(FaculPremiumBean beanObj) {
		try{
			String sql = getQuery("UPDATE_TRANSACTION_NO_STATUS");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo(),beanObj.getApproveStatus(),beanObj.getContNo(),beanObj.getRequestNo()});
	 	
		 	
		 	sql = getQuery("UPDATE_CASHLOSS_STATUS");
		 	LOGGER.info("Update Query==>"+sql);
		 	this.mytemplate.update(sql, new Object[]{beanObj.getTransactionNo()==null?"":beanObj.getTransactionNo(),beanObj.getApproveStatus(),beanObj.getContNo(),beanObj.getRequestNo()});
		 	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	private void updateTempToMain(FaculPremiumBean beanObj) {
		try{
			String query="";
	 		
	 		String args[] = new String[3];
	 		/*args[0] = beanObj.getTransactionNo();
	 		args[1]= beanObj.getRequestNo();
	 		args[2]= beanObj.getBranchCode();
	 		this.mytemplate.update(query,args);
	 		beanObj.setTransactionNo(args[0]);
	 		String transNo=args[0];*/
	 		
	 		
	 		query =getQuery("FAC_PREMIUM_TEMP_TO_MAIN");
	 		args = new String[2];
	 		args[0] = beanObj.getRequestNo();
	 		args[1] = beanObj.getBranchCode();
	 		
	 		this.mytemplate.update(query,args);
	 		
	 		if(!"2".equalsIgnoreCase(beanObj.getProductId())){
	 		query=getQuery("UPDATE_MND_INSTALLMENT");
			args = new String[4];
			args[0]=beanObj.getTransactionNo();
			args[1]=beanObj.getContNo();
			args[2]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
			args[3]=beanObj.getInstlmentNo();
			LOGGER.info("Insert Query==>"+query);
			LOGGER.info("Args==>"+StringUtils.join(args, ","));
			this.mytemplate.update(query,args);
	 		}
	 		if("2".equalsIgnoreCase(beanObj.getProductId())){
	 			String sql=getQuery("UPDATE_PREMIUM_RESERVE");
			 	LOGGER.info("Update Query==>"+sql);
			 	this.mytemplate.update(sql, new Object[]{beanObj.getContNo(),beanObj.getRequestNo(),"A",beanObj.getContNo(),beanObj.getTransactionNo()});
			 	
			 	
			 	 sql=getQuery("UPDATE_LOSS_RESERVE");
			 	LOGGER.info("Update Query==>"+sql);
			 	this.mytemplate.update(sql, new Object[]{beanObj.getContNo(),beanObj.getRequestNo(),"A",beanObj.getContNo(),beanObj.getTransactionNo()});
			 	ProportionalPremiumDaoImpl imp = new ProportionalPremiumDaoImpl();

			 	
			 	 List<FaculPremiumBean>cashLossList=imp.getCassLossCredit(beanObj,"");
			 	 for(int i=0;i<cashLossList.size();i++){
			 		FaculPremiumBean form= cashLossList.get(0);
			 		 sql=getQuery(DBConstants.UPDATE_CLAIM_PAYMENT);
		             LOGGER.info("Update Query==>"+sql);
		             this.mytemplate.update(sql, new Object[]{form.getContNo(),beanObj.getBranchCode(),beanObj.getRequestNo(),"A",form.getClaimNumber(),form.getClaimPaymentNo(),form.getContNo(),form.getClaimNumber(),form.getClaimPaymentNo()});
			 	 }
	 		}
			//if(be
			query=getQuery(DBConstants.PREMIUM_SP_RETROSPLIT);
			LOGGER.info("SP Name==>"+query);
			args = new String[16];
			args[0]=beanObj.getContNo();
			args[1]=StringUtils.isEmpty(beanObj.getLayerno())?"0":beanObj.getLayerno();
			args[2]=beanObj.getProductId();
			args[3]=beanObj.getTransactionNo();
			args[4]=beanObj.getTransaction();
			args[5]=beanObj.getCurrencyId();
			args[6]=beanObj.getExchRate();
			args[7]=beanObj.getBranchCode();
			args[8]="P";
			args[9]=beanObj.getAmendmentDate()==null?"":beanObj.getAmendmentDate();
			args[10]="";
			args[11]="";
			args[12]="";
			args[13]="";
			args[14]="";
			args[15] =beanObj.getRi_cession();
		
			int spresult=this.mytemplate.update(query,args);
		LOGGER.info("SP Result==>"+spresult);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void getPremiumDetails(FaculPremiumBean bean) {
		try{
			String query="";
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			String[] args = new String[1];
			query=getQuery("PREMIUM_SELECT_FACPREMIUMEDIT_TEMP_AUTH");
			args[0] = bean.getRequestNo();
			LOGGER.info("Query=>"+query.toString());
			 list=this.mytemplate.queryForList(query, args);
			 if(list.size()>0)
			 {
				 for(int i=0;i<list.size();i++){
					 Map<String,Object>	 editPremium=list.get(i);
													
					bean.setTransaction(editPremium.get("TRANS_DATE")==null?"":editPremium.get("TRANS_DATE").toString()); 
					if("EP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else if("RTP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else if("RVP".equalsIgnoreCase(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString()))
			    	{
			    		bean.setAccount_Period(editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString());
			    	}
					else
			    	{
			    		bean.setAccount_Period((editPremium.get("INSTALMENT_NUMBER")==null?"":editPremium.get("INSTALMENT_NUMBER").toString())+"_"+(editPremium.get("ACCOUNT_PERIOD_QTR")==null?"":editPremium.get("ACCOUNT_PERIOD_QTR").toString()));
			    	}
						bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						if((editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString())==""){
							bean.setExchRate( new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),bean.getCountryId(),bean.getBranchCode()));
						}
						else{
						bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString()));
						}
						bean.setPremiumQuotaShare(DropDownControllor.formatter(editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":editPremium.get("PREMIUM_QUOTASHARE_OC").toString()));
						bean.setCommission_view(DropDownControllor.formatterpercentage(editPremium.get("COMMISSION") ==null?"":editPremium.get("COMMISSION").toString()));
						bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":editPremium.get("COMMISSION_QUOTASHARE_OC").toString());
						bean.setBrokerage_view(editPremium.get("BROKERAGE")==null?"":editPremium.get("BROKERAGE").toString());
						bean.setBrokerage(editPremium.get("BROKERAGE_AMT_OC")==null?"":editPremium.get("BROKERAGE_AMT_OC").toString());
						bean.setTax_view(editPremium.get("TAX")==null?"":editPremium.get("TAX").toString());
						bean.setTax(editPremium.get("TAX_AMT_OC")==null?"":editPremium.get("TAX_AMT_OC").toString());
		     			bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
						bean.setReceipt_no(editPremium.get("RECEIPT_NO")==null?"":editPremium.get("RECEIPT_NO").toString());		
						bean.setInception_Date(editPremium.get("ENTRY_DATE")==null?"":editPremium.get("ENTRY_DATE").toString());
						bean.setEnteringMode(editPremium.get("ENTERING_MODE")==null?"":editPremium.get("ENTERING_MODE").toString().trim());
						bean.setOtherCost(editPremium.get("OTHER_COST_OC")==null?"":editPremium.get("OTHER_COST_OC").toString());
						bean.setCedentRef(editPremium.get("CEDANT_REFERENCE")==null?"":editPremium.get("CEDANT_REFERENCE").toString());
						bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
						bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":editPremium.get("NETDUE_OC").toString());
						bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_OC")==null?"0":editPremium.get("WITH_HOLDING_TAX_OC").toString()));
						bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.get("TDS_OC")==null?"0":editPremium.get("TDS_OC").toString()));
			            bean.setServiceTax(DropDownControllor.formatter(editPremium.get("ST_OC")==null?"0":editPremium.get("ST_OC").toString()));
			            bean.setBonus(DropDownControllor.formatter(editPremium.get("BONUS_OC")==null?"0":editPremium.get("BONUS_OC").toString()));
						//bean.setSettlement_status(editPremium.getString("SETTLEMENT_STATUS"));								
						bean.setTotalCredit(editPremium.get("TOTAL_CR_OC")==null?"":editPremium.get("TOTAL_CR_OC").toString());						
						bean.setTotalDebit(editPremium.get("TOTAL_DR_OC")==null?"":editPremium.get("TOTAL_DR_OC").toString());
						bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());
						bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":editPremium.get("RI_CESSION").toString());
						bean.setStatementDate(editPremium.get("STATEMENT_DATE")==null?"":editPremium.get("STATEMENT_DATE").toString());
						bean.setChooseTransaction(editPremium.get("REVERSEL_STATUS")==null?"":editPremium.get("REVERSEL_STATUS").toString() );
			            bean.setTransDropDownVal(editPremium.get("REVERSE_TRANSACTION_NO")==null?"":editPremium.get("REVERSE_TRANSACTION_NO").toString() );
			            bean.setProductId(editPremium.get("PRODUCT_ID")==null?"":editPremium.get("PRODUCT_ID").toString());
			            bean.setDepartmentId(editPremium.get("PREMIUM_CLASS")==null?"":editPremium.get("PREMIUM_CLASS").toString());
			            bean.setContNo(editPremium.get("CONTRACT_NO")==null?"":editPremium.get("CONTRACT_NO").toString());
				}
			 }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
