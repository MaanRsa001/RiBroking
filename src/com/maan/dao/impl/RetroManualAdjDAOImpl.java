package com.maan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.jdbc.core.RowMapper;


import com.maan.bean.FaculPremiumBean;
import com.maan.common.db.DBConstants;
import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;
import com.maan.dao.RetroManualAdjDAO;
import com.sun.org.apache.xpath.internal.operations.Number;

public class RetroManualAdjDAOImpl extends MyJdbcTemplate implements RetroManualAdjDAO{
	String query="";
	private static final Logger LOGGER = LogUtil.getLogger(ProportionalPremiumDaoImpl.class);	
	public List<Map<String, Object>> getRetroManualAdjlist(FaculPremiumBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_RETRO_MANUAL_ADJ_LIST");
			Object args[] = new Object[2];
			args[0] = bean.getBranchCode();
			args[1] = bean.getProductId();
			list=this.mytemplate.queryForList(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	public void getRetroDetails(final FaculPremiumBean bean) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			query=getQuery("GET_RETRO_MANUAL_ADJ");
			list=this.mytemplate.queryForList(query,new Object[]{bean.getContNo(),bean.getBranchCode()});
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = list.get(i);
				if(map.get("RSK_INSURED_NAME")!=null &&map.get("RSK_INSURED_NAME")!=""){
					bean.setTreatyName(map.get("RSK_INSURED_NAME")==null?"":map.get("RSK_INSURED_NAME").toString());	
				}else{
					bean.setTreatyName(map.get("RSK_TREATYID")==null?"":map.get("RSK_TREATYID").toString());
				}
				bean.setLeadBroker(map.get("Broker_name")==null?"":map.get("Broker_name").toString());
				bean.setLeadRetro(map.get("Customer_name")==null?"":map.get("Customer_name").toString());
				bean.setProposal_No(map.get("RSK_PROPOSAL_NUMBER")==null?"":map.get("RSK_PROPOSAL_NUMBER").toString());
				bean.setAmendId(map.get("RSK_ENDORSEMENT_NO")==null?"":map.get("RSK_ENDORSEMENT_NO").toString());
				bean.setCurrency(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
				bean.setCurrId(map.get("RSK_ORIGINAL_CURR")==null?"":map.get("RSK_ORIGINAL_CURR").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void InsertPremium(FaculPremiumBean bean) {
	
			try{
				
				/*String query =getQuery("INSERT_RETRO_MANUAL_ADJ");
				Object args[] = new Object[90];
				args[0]   = bean.getPredepartment();
				args[1]   = bean.getSubProfitId();
				args[2]   = bean.getTransaction();
				args[3]   = bean.getUwYear();
				args[4]   = bean.getReference();
				args[5]   = bean.getLeadRetro();
				args[6]   = bean.getLeadBroker();
				args[7]   = bean.getCurrency();
				args[8]   = bean.getExchRate();
				args[9]   = bean.getPremiumQuotaShare().replaceAll(",", "");
				args[10]  = bean.getPremiumQuotaShare().replaceAll(",", "");
				args[11]  = bean.getPremiumQuotaShare().replaceAll(",", "");
				args[12]  = bean.getCommissionQuotaShare().replaceAll(",", "");
				args[13]  = bean.getCommissionQuotaShare().replaceAll(",", "");
				args[14]  = bean.getCommissionQuotaShare().replaceAll(",", "");
				args[15]  = bean.getPremiumportifolioIn().replaceAll(",", "");
				args[16]  = bean.getPremiumportifolioIn().replaceAll(",", "");
				args[17]  = bean.getPremiumportifolioIn().replaceAll(",", "");
				args[18]  = bean.getSlideScaleCom().replaceAll(",", "");
				args[19]  = bean.getSlideScaleCom().replaceAll(",", "");
				args[20]  = bean.getSlideScaleCom().replaceAll(",", "");
				args[21]  = bean.getCliamPortfolioin().replaceAll(",", "");
				args[22]  = bean.getCliamPortfolioin().replaceAll(",", "");
				args[23]  = bean.getCliamPortfolioin().replaceAll(",", "");
				args[24]  = bean.getBrokerage().replaceAll(",", "");
				args[25]  = bean.getBrokerage().replaceAll(",", "");
				args[26]  = bean.getBrokerage().replaceAll(",", "");
				args[27]  = bean.getPremium_Reserve_Released().replaceAll(",", "");
				args[28]  = bean.getPremium_Reserve_Released().replaceAll(",", "");
				args[29]  = bean.getPremium_Reserve_Released().replaceAll(",", "");
				args[30]  = bean.getLossReserveReleased().replaceAll(",", "");
				args[31]  = bean.getLossReserveReleased().replaceAll(",", "");
				args[32]  = bean.getLossReserveReleased().replaceAll(",", "");
				args[33]  = bean.getTax().replaceAll(",", "");
				args[34]  = bean.getTax().replaceAll(",", "");
				args[35]  = bean.getTax().replaceAll(",", "");
				args[36]  = bean.getWithHoldingTaxOC().replaceAll(",", "");
				args[37]  = bean.getWithHoldingTaxOC().replaceAll(",", "");
				args[38]  = bean.getWithHoldingTaxOC().replaceAll(",", "");
				args[39]  = bean.getOverrider().replaceAll(",", "");
				args[40]  = bean.getOverrider().replaceAll(",", "");
				args[41]  = bean.getOverrider().replaceAll(",", "");
				args[42]  = bean.getInterest().replaceAll(",", "");
				args[43]  = bean.getInterest().replaceAll(",", "");
				args[44]  = bean.getInterest().replaceAll(",", "");
				args[45]  = bean.getOtherCost().replaceAll(",", "");
				args[46]  = bean.getOtherCost().replaceAll(",", "");
				args[47]  = bean.getOtherCost().replaceAll(",", "");
				args[48]  = bean.getCashLoss_Credit().replaceAll(",", "");
				args[49]  = bean.getCashLoss_Credit().replaceAll(",", "");
				args[50]  = bean.getCashLoss_Credit().replaceAll(",", "");
				args[51]  = bean.getPremiumportifolioout().replaceAll(",", "");
				args[52]  = bean.getPremiumportifolioout().replaceAll(",", "");
				args[53]  = bean.getPremiumportifolioout().replaceAll(",", "");
				args[54]  = bean.getTaxDedectSource().replaceAll(",", "");
				args[55]  = bean.getTaxDedectSource().replaceAll(",", "");
				args[56]  = bean.getTaxDedectSource().replaceAll(",", "");
				args[57]  = bean.getCliam_portfolio_out().replaceAll(",", "");
				args[58]  = bean.getCliam_portfolio_out().replaceAll(",", "");
				args[59]  = bean.getCliam_portfolio_out().replaceAll(",", "");
				args[60]  = bean.getPremiumReserve_QuotaShare().replaceAll(",", "");
				args[61]  = bean.getPremiumReserve_QuotaShare().replaceAll(",", "");
				args[62]  = bean.getPremiumReserve_QuotaShare().replaceAll(",", "");
				args[63]  = bean.getServiceTax().replaceAll(",", "");
				args[64]  = bean.getServiceTax().replaceAll(",", "");
				args[65]  = bean.getServiceTax().replaceAll(",", "");
				args[66]  = bean.getLoss_ReserveRetained().replaceAll(",", "");
				args[67]  = bean.getLoss_ReserveRetained().replaceAll(",", "");
				args[68]  = bean.getLoss_ReserveRetained().replaceAll(",", "");
				args[69]  = bean.getLossParticipation().replaceAll(",", "");
				args[70]  = bean.getLossParticipation().replaceAll(",", "");
				args[71]  = bean.getLossParticipation().replaceAll(",", "");
				args[72]  = bean.getProfit_Commission().replaceAll(",", "");
				args[73]  = bean.getProfit_Commission().replaceAll(",", "");
				args[74]  = bean.getProfit_Commission().replaceAll(",", "");
				args[75]  = bean.getCash_LossPaid().replaceAll(",", "");
				args[76]  = bean.getCash_LossPaid().replaceAll(",", "");
				args[77]  = bean.getCash_LossPaid().replaceAll(",", "");
				args[78]  = bean.getClaims_paid()==null?"0":bean.getClaims_paid().replaceAll(",", "");
				args[79]  = bean.getClaims_paid()==null?"0":bean.getClaims_paid().replaceAll(",", "");
				args[80]  = bean.getClaims_paid()==null?"0":bean.getClaims_paid().replaceAll(",", "");
				args[81]  = bean.getTreatyName();
				args[82]  = bean.getBranchCode();
				args[83]  = bean.getOsClaimsLossUpdateOC().replaceAll(",", "");
				args[84]  = bean.getOsClaimsLossUpdateOC().replaceAll(",", "");
				args[85]  = bean.getOsClaimsLossUpdateOC().replaceAll(",", "");
				args[86]  = bean.getRemarks();
				args[87]  = "4";
				args[88]  = bean.getContNo();
				args[89]="0001";//new DropDownControllor().getSequence("Premium",bean.getProductId(),bean.getPredepartment(), bean.getBranchCode(),"");*/
				String[] args =new String[0];
				String transNo="";
				args = insertArguments(bean);
			 	String netDueOc="0";
			 	query=getQuery(DBConstants.PREMIUM_INSERT_TREATYPREMIUM);
				netDueOc=args[33];
		 		transNo=args[1];
				//}
			 	LOGGER.info("Insert Query==>"+query);
			 	int result=this.mytemplate.update(query, args);
			 	LOGGER.info("Insert Result==>"+result);
				if (result==1) {
					query=getQuery(DBConstants.PREMIUM_SP_RETROSPLIT);
					LOGGER.info("SP Name==>"+query);
					args = new String[16];
					args[0]=bean.getContNo();
					args[1]=StringUtils.isEmpty(bean.getLayerno())?"0":bean.getLayerno();
					args[2]=bean.getProductId();
					args[3]=transNo;
					args[4]=bean.getTransaction();
					args[5]=bean.getCurrency();
					args[6]=bean.getExchRate();
					args[7]=bean.getBranchCode();
					args[8]="P";
					args[9]=bean.getAmendmentDate()==null?"":bean.getAmendmentDate();
					args[10] = bean.getReference();
					args[11] = bean.getTreatyName();
					args[12] = bean.getRemarks();
					args[13] = bean.getUwYear();
					args[14] = bean.getSubProfitId();
					args[15] ="Yes";
					for(int i=0;i<args.length;i++)
					LOGGER.info("Args["+i+"]==>"+args[i]);
					int spresult=this.mytemplate.update(query,args);
					LOGGER.info("SP Result==>"+spresult);
					boolean saveFlag = true;
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			 
		
	}
	
	private static String getModeOfTransaction(final String Value,final FaculPremiumBean beanObj) {
	/*LOGGER.info("PremiumDAOImpl getModeOfTransaction || Enter");
	LOGGER.info("Value=>"+Value);
	LOGGER.info("Entering Mode=>"+beanObj.getEnteringMode());
	LOGGER.info("ShareSigned=>"+beanObj.getShareSigned());
	String result="0";
	double shareSigned=0.0;
	DecimalFormat twoDForm = new DecimalFormat("#.##");
	if(beanObj.getEnteringMode()!=null)
	{
		if("1".equalsIgnoreCase(beanObj.getEnteringMode()))
		{
			shareSigned=Double.parseDouble(beanObj.getShareSigned());
		}
		else if("2".equalsIgnoreCase(beanObj.getEnteringMode()))
		{
			shareSigned=100;
		}
		//shareSigned=100;
		LOGGER.info("Value==>"+Value);
		if(!"".equalsIgnoreCase(Value))
		{
				double finalValue=Double.parseDouble(Value) *shareSigned/100;
				LOGGER.info("Final Value==>"+finalValue);
				result=String.valueOf(Double.valueOf(twoDForm.format(finalValue)));
		}*/
		
		String result=Value;
	
	LOGGER.info("PremiumDAOImpl getModeOfTransaction || Exit");
	return result;
	}
	private String[] insertArguments(FaculPremiumBean beanObj)
	{
		LOGGER.info("PremiumDAOImpl insertArguments || Enter");
		String[] args=null;
			args=new String[93];
		    args[0]=beanObj.getContNo();
			
		    if(StringUtils.isBlank(beanObj.getTransactionNo())){
				args[1]=new DropDownControllor().getSequencePTRT("RetroJournal" ,"","", beanObj.getBranchCode(),"",beanObj.getTransaction());
		    }else{
		    	args[1]=beanObj.getTransactionNo();
		    }
			
			args[2]=beanObj.getTransaction();
			args[3]="";
			args[4]="";
			args[5]=beanObj.getCurrency();
			args[6]=beanObj.getExchRate();
			args[7]="";
			args[8]=getModeOfTransaction(beanObj.getBrokerage(),beanObj);
			args[35]=DropDownControllor.GetDesginationCountry(args[8], beanObj.getExchRate());
			args[9]="";
			args[10]=getModeOfTransaction(beanObj.getTax(),beanObj);
			args[36]=DropDownControllor.GetDesginationCountry(args[10], beanObj.getExchRate());
			args[67]=getModeOfTransaction(beanObj.getOverrider(),beanObj);
			args[68]=DropDownControllor.GetDesginationCountry(args[67],beanObj.getExchRate());
			args[69]="";
	        args[70]=getModeOfTransaction(beanObj.getWithHoldingTaxOC(),beanObj);
	        args[71]=DropDownControllor.GetDesginationCountry(args[70], beanObj.getExchRate());
			args[11]=StringUtils.isEmpty(beanObj.getInception_Date()) ?"" :beanObj.getInception_Date();
			args[12]=getModeOfTransaction(beanObj.getPremiumQuotaShare(),beanObj);
			args[37]=DropDownControllor.GetDesginationCountry(args[12], beanObj.getExchRate());
			args[13]=getModeOfTransaction(beanObj.getCommissionQuotaShare(),beanObj);
			args[38]=DropDownControllor.GetDesginationCountry(args[13], beanObj.getExchRate());
			args[14]="";
			args[39]="";
			args[15]="";
			args[40]="";
			args[16]=getModeOfTransaction(beanObj.getPremiumportifolioIn(),beanObj);
			args[41]=DropDownControllor.GetDesginationCountry(args[16], beanObj.getExchRate());
			args[72]="Yes";
			//args[73]=userId;
			args[73]= beanObj.getLoginId();
			args[74]=beanObj.getBranchCode();
			args[75]=beanObj.getPredepartment();
			args[76] = getModeOfTransaction(beanObj.getTaxDedectSource(),beanObj);
			args[77] = DropDownControllor.GetDesginationCountry(args[76], beanObj.getExchRate());
			args[78] = getModeOfTransaction(beanObj.getServiceTax(),beanObj);
			args[79] = DropDownControllor.GetDesginationCountry(args[78], beanObj.getExchRate());
			args[80] = getModeOfTransaction(beanObj.getSlideScaleCom(),beanObj);
			args[81] = DropDownControllor.GetDesginationCountry(args[80], beanObj.getExchRate());
			args[82] = beanObj.getPredepartment();
			args[83] = beanObj.getSubProfitId().replace(" ", "");
			args[84] = "";
			args[85] = "";
			args[86] = "";
			args[87] = getModeOfTransaction(beanObj.getLossParticipation(),beanObj);
			args[88] = DropDownControllor.GetDesginationCountry(args[87], beanObj.getExchRate());
			args[89] = "";
			args[90] = "";
			args[91] = beanObj.getProductId();
			args[92] = beanObj.getBusinessType();
			//Added by sathish for java script failure cases-Start
			LOGGER.info("Before");
			LOGGER.info("Premium==>"+beanObj.getPremiumQuotaShare());
			LOGGER.info("Commission==>"+beanObj.getCommissionQuotaShare());
			LOGGER.info("Brokerate==>"+beanObj.getBrokerage());
			LOGGER.info("Tax===>"+beanObj.getTax());
			LOGGER.info("Overrider===>"+beanObj.getOverrider());
			LOGGER.info("PremiumSurplus===>"+beanObj.getPremiumSurplus());
			LOGGER.info("comsurp===>"+beanObj.getCommissionSurplus());
			args[17]=getModeOfTransaction(beanObj.getCliamPortfolioin(),beanObj);
			args[42]=DropDownControllor.GetDesginationCountry(args[17], beanObj.getExchRate());
			args[18]=getModeOfTransaction(beanObj.getPremiumportifolioout(),beanObj);
			args[43]=DropDownControllor.GetDesginationCountry(args[18], beanObj.getExchRate());
			args[19]=getModeOfTransaction(beanObj.getLossReserveReleased(),beanObj);
			args[44]=DropDownControllor.GetDesginationCountry(args[19], beanObj.getExchRate());
			args[20]=getModeOfTransaction(beanObj.getPremiumReserve_QuotaShare(),beanObj);
			args[45]=DropDownControllor.GetDesginationCountry(args[20], beanObj.getExchRate());
			args[21]=getModeOfTransaction(beanObj.getCashLoss_Credit(),beanObj);
			args[46]=DropDownControllor.GetDesginationCountry(args[21], beanObj.getExchRate());
			args[22]=getModeOfTransaction(beanObj.getLoss_ReserveRetained(),beanObj);
			args[47]=DropDownControllor.GetDesginationCountry(args[22], beanObj.getExchRate());
			args[23]=getModeOfTransaction(StringUtils.isBlank(beanObj.getProfit_Commission()) ? "0" : beanObj.getProfit_Commission(),beanObj);
			args[48]=DropDownControllor.GetDesginationCountry(args[23], beanObj.getExchRate());
			args[24]=getModeOfTransaction(beanObj.getCash_LossPaid(),beanObj);
			args[49]=DropDownControllor.GetDesginationCountry(args[24], beanObj.getExchRate());
			args[25]="Y";
			args[26]="2";
			args[27]="";
			args[28]=getModeOfTransaction(beanObj.getClaims_paid(),beanObj);
			args[50]=DropDownControllor.GetDesginationCountry(args[28], beanObj.getExchRate());
			args[29]=beanObj.getSettlement_status();
			args[30]=getModeOfTransaction(beanObj.getXl_Cost(),beanObj);
			args[51]=DropDownControllor.GetDesginationCountry(args[30], beanObj.getExchRate());
			args[31]=getModeOfTransaction(beanObj.getCliam_portfolio_out(),beanObj);
			args[52]=DropDownControllor.GetDesginationCountry(args[31], beanObj.getExchRate());
			args[32]=getModeOfTransaction(beanObj.getPremium_Reserve_Released(),beanObj);
			args[53]=DropDownControllor.GetDesginationCountry(args[32], beanObj.getExchRate());
			args[34]=getModeOfTransaction(beanObj.getOtherCost(),beanObj);
			args[55]=DropDownControllor.GetDesginationCountry(args[34], beanObj.getExchRate());
			/*args[33]=getNetDueAmount(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
			args[54]=DropDownControllor.GetDesginationCountry(args[33], beanObj.getExchRate());*/
			args[56]="";
			args[57]="";
			args[58]=beanObj.getRemarks();
			args[59]=getModeOfTransaction(beanObj.getTotalCredit(),beanObj);
			args[60]=DropDownControllor.GetDesginationCountry(args[59],beanObj.getExchRate());
			args[61]=getModeOfTransaction(beanObj.getTotalDebit(),beanObj);
			args[62]=DropDownControllor.GetDesginationCountry(args[61],beanObj.getExchRate());
			args[63]=getModeOfTransaction(beanObj.getInterest(),beanObj);
			args[64]=DropDownControllor.GetDesginationCountry(args[63],beanObj.getExchRate());
			args[33]=getNetDueAmount(args,getModeOfTransaction(beanObj.getClaims_paid(),beanObj));
			args[54]=DropDownControllor.GetDesginationCountry(args[33], beanObj.getExchRate());
			args[65]=StringUtils.isEmpty(beanObj.getOsClaimsLossUpdateOC())?"0":getModeOfTransaction(beanObj.getOsClaimsLossUpdateOC(),beanObj);
			args[66]=DropDownControllor.GetDesginationCountry(args[65], beanObj.getExchRate());
			
			beanObj.setTransactionNo(args[1]);

		for(int i=0;i>args.length;i++)
		{
			LOGGER.info("Args=>"+args[i]);
		}
		final String[] copiedArray = new String[args.length];
		System.arraycopy(args, 0, copiedArray, 0, args.length);
		LOGGER.info("PremiumDAOImpl maxTransationNo || Exit");
		return copiedArray;
	}

	private static String getNetDueAmount(final String[] args,final String CliamPaid) {
		LOGGER.info("PremiumDAOImpl getNetDueAmount || Enter");
		double Abt=0;
		double Bbt=0;
		if(StringUtils.isNotEmpty(args[12]))
		{
		Abt+=Double.parseDouble(args[12]);
		}
		if(StringUtils.isNotEmpty(args[14]))
		{
		Abt+=Double.parseDouble(args[14]);
		}
		if(StringUtils.isNotEmpty(args[16]))
		{
		Abt+=Double.parseDouble(args[16]);
		}
		if(StringUtils.isNotEmpty(args[17]))
		{
		Abt+=Double.parseDouble(args[17]);
		}
		if(StringUtils.isNotEmpty(args[19]))
		{
		Abt+=Double.parseDouble(args[19]);
		}
		if(StringUtils.isNotEmpty(args[21]))
		{
		Abt+=Double.parseDouble(args[21]);
		}
		if(StringUtils.isNotEmpty(args[32]))
		{
			Abt+=Double.parseDouble(args[32]);
		}
		if(StringUtils.isNotEmpty(args[63]))
		{
			Abt+=Double.parseDouble(args[63]);
		}
		if(StringUtils.isNotEmpty(args[76]))
		{
		Abt+=Double.parseDouble(args[76]);
		}
		if(StringUtils.isNotEmpty(args[78]))
		{
		Abt+=Double.parseDouble(args[78]);
		}
		
		if(StringUtils.isNotEmpty(args[87]))
		{
		Abt+=Double.parseDouble(args[87]);
		}
		if(StringUtils.isNotEmpty(args[13]))
		{
			Bbt+=Double.parseDouble(args[13]);
		}
		if(StringUtils.isNotEmpty(args[15]))
		{
			Bbt+=Double.parseDouble(args[15]);
		}
		if(StringUtils.isNotEmpty(args[8]))
		{
			Bbt+=Double.parseDouble(args[8]);
		}
		if(StringUtils.isNotEmpty(args[10]))
		{
			Bbt+=Double.parseDouble(args[10]);
		}
		if(StringUtils.isNotEmpty(args[18]))
		{
			Bbt+=Double.parseDouble(args[18]);
		}
		if(StringUtils.isNotEmpty(args[20]))
		{
			Bbt+=Double.parseDouble(args[20]);
		}
		if(StringUtils.isNotEmpty(args[22]))
		{
			Bbt+=Double.parseDouble(args[22]);
		}
		if(StringUtils.isNotEmpty(args[23]))
		{
			Bbt+=Double.parseDouble(args[23]);
		}
		if(StringUtils.isNotEmpty(args[24]))
		{
			Bbt+=Double.parseDouble(args[24]);
		}
		if(StringUtils.isNotEmpty(CliamPaid))
		{
			Bbt+=Double.parseDouble(CliamPaid);
		}
		if(StringUtils.isNotEmpty(args[31]))
		{
			Bbt+=Double.parseDouble(args[31]);
		}
		if(StringUtils.isNotEmpty(args[30]))
		{
			Bbt+=Double.parseDouble(args[30]);
		}
		if(StringUtils.isNotEmpty(args[34]))
		{
			Bbt+=Double.parseDouble(args[34]);
		}
		if(StringUtils.isNotEmpty(args[67]))
		{
			Bbt+=Double.parseDouble(args[67]);
		}
		if(StringUtils.isNotEmpty(args[70]))
		{
			Bbt+=Double.parseDouble(args[70]);
		}
		if(StringUtils.isNotEmpty(args[80]))
		{
			Bbt+=Double.parseDouble(args[80]);
		}
		LOGGER.info("A==>"+Abt);
		LOGGER.info("B==>"+Bbt);
	    final double cbt=Abt-Bbt;
	    LOGGER.info("Net Due==>"+cbt);
	    LOGGER.info("PremiumDAOImpl getNetDueAmount || Exit");
		return String.valueOf(cbt);
	}
	public boolean GetPremiumDetails(FaculPremiumBean bean,String transactionNo, String countryId) {
			
		LOGGER.info("PremiumDAOImpl getPremiumDetails || Enter");
		String query="";
		try{
	   	  	String[] args=new String[2];
		   	args[0]=bean.getContNo();
		   	args[1]=transactionNo;
		   	query=getQuery("GET_RETRO_PREMIUM");
		   	LOGGER.info("Query=>"+query);
	   		List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);
			for(int i=0;i<list.size();i++){
				Map<String,Object> treatyView=list.get(i);
					bean.setContNo(treatyView.get("CONTRACT_NO")==null?"":treatyView.get("CONTRACT_NO").toString());
					bean.setTransactionNo(treatyView.get("TRANSACTION_NO")==null?"":treatyView.get("TRANSACTION_NO").toString());
					bean.setTransaction(treatyView.get("TRANSACTION_MONTH_YEAR")==null?"":treatyView.get("TRANSACTION_MONTH_YEAR").toString());
					bean.setBrokerage(DropDownControllor.formatter(treatyView.get("BROKERAGE_AMT_OC")==null?"0":treatyView.get("BROKERAGE_AMT_OC").toString()));
					bean.setTax(DropDownControllor.formatter(treatyView.get("TAX_AMT_OC")==null?"0":treatyView.get("TAX_AMT_OC").toString()));
					bean.setPremiumQuotaShare(DropDownControllor.formatter(treatyView.get("PREMIUM_QUOTASHARE_OC")==null?"0":treatyView.get("PREMIUM_QUOTASHARE_OC").toString()));
					bean.setCommissionQuotaShare(DropDownControllor.formatter(treatyView.get("COMMISSION_QUOTASHARE_OC")==null?"0":treatyView.get("COMMISSION_QUOTASHARE_OC").toString()));
					bean.setPremiumSurplus(DropDownControllor.formatter(treatyView.get("PREMIUM_SURPLUS_OC")==null?"0":treatyView.get("PREMIUM_SURPLUS_OC").toString()));
					bean.setCommissionSurplus(DropDownControllor.formatter(treatyView.get("COMMISSION_SURPLUS_OC")==null?"0":treatyView.get("COMMISSION_SURPLUS_OC").toString()));
					bean.setPremiumportifolioIn(DropDownControllor.formatter(treatyView.get("PREMIUM_PORTFOLIOIN_OC")==null?"0":treatyView.get("PREMIUM_PORTFOLIOIN_OC").toString()));
					bean.setCliamPortfolioin(DropDownControllor.formatter(treatyView.get("CLAIM_PORTFOLIOIN_OC")==null?"0":treatyView.get("CLAIM_PORTFOLIOIN_OC").toString()));
					bean.setPremiumportifolioout(DropDownControllor.formatter(treatyView.get("PREMIUM_PORTFOLIOOUT_OC")==null?"0":treatyView.get("PREMIUM_PORTFOLIOOUT_OC").toString()));
					bean.setLossReserveReleased(DropDownControllor.formatter(treatyView.get("LOSS_RESERVE_RELEASED_OC")==null?"0":treatyView.get("LOSS_RESERVE_RELEASED_OC").toString()));
					bean.setPremiumReserve_QuotaShare(DropDownControllor.formatter(treatyView.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"0":treatyView.get("PREMIUMRESERVE_QUOTASHARE_OC").toString()));
					bean.setCashLoss_Credit(DropDownControllor.formatter(treatyView.get("CASH_LOSS_CREDIT_OC")==null?"0":treatyView.get("CASH_LOSS_CREDIT_OC").toString()));
					bean.setLoss_ReserveRetained(DropDownControllor.formatter(treatyView.get("LOSS_RESERVERETAINED_OC")==null?"0":treatyView.get("LOSS_RESERVERETAINED_OC").toString()));
					bean.setProfit_Commission(DropDownControllor.formatter(treatyView.get("PROFIT_COMMISSION_OC")==null?"0":treatyView.get("PROFIT_COMMISSION_OC").toString()));
					bean.setCash_LossPaid(DropDownControllor.formatter(treatyView.get("CASH_LOSSPAID_OC")==null?"0":treatyView.get("CASH_LOSSPAID_OC").toString()));
					
					//bean.setReceipt_no(treatyView.get("RECEIPT_NO")==null?"":treatyView.get("").toString());
					//bean.setSettlement_status(treatyView.get("SETTLEMENT_STATUS"));
					bean.setClaims_paid(DropDownControllor.formatter(treatyView.get("CLAIMS_PAID_OC")==null?"0":treatyView.get("CLAIMS_PAID_OC").toString()));
					bean.setInception_Date(treatyView.get("ENTRY_DATE")==null?"":treatyView.get("ENTRY_DATE").toString());
					bean.setXl_Cost(DropDownControllor.formatter(treatyView.get("XL_COST_OC")==null?"0":treatyView.get("XL_COST_OC").toString()));
					bean.setCliam_portfolio_out(DropDownControllor.formatter(treatyView.get("CLAIM_PORTFOLIO_OUT_OC")==null?"0":treatyView.get("CLAIM_PORTFOLIO_OUT_OC").toString()));
					bean.setPremium_Reserve_Released(DropDownControllor.formatter(treatyView.get("PREMIUM_RESERVE_REALSED_OC")==null?"0":treatyView.get("PREMIUM_RESERVE_REALSED_OC").toString()));
					bean.setAccount_Period(treatyView.get("ACCOUNT_PERIOD_QTR")==null?"":treatyView.get("ACCOUNT_PERIOD_QTR").toString());
					bean.setAccount_Period_year(treatyView.get("ACCOUNT_PERIOD_YEAR")==null?"":treatyView.get("ACCOUNT_PERIOD_YEAR").toString());
					bean.setCurrencyId(treatyView.get("CURRENCY_ID")==null?"":treatyView.get("CURRENCY_ID").toString());
					bean.setOtherCost(DropDownControllor.formatter(treatyView.get("OTHER_COST_OC")==null?"0":treatyView.get("OTHER_COST_OC").toString()));
					bean.setBrokerage_usd(DropDownControllor.formatter(treatyView.get("BROKERAGE_AMT_DC")==null?"0":treatyView.get("BROKERAGE_AMT_DC").toString()));
					bean.setTax_usd(DropDownControllor.formatter(treatyView.get("TAX_AMT_DC")==null?"0":treatyView.get("TAX_AMT_DC").toString()));
					bean.setPremiumQuotaShare_usd(DropDownControllor.formatter(treatyView.get("PREMIUM_QUOTASHARE_DC")==null?"0":treatyView.get("PREMIUM_QUOTASHARE_DC").toString()));
					bean.setCommsissionQuotaShare_usd(DropDownControllor.formatter(treatyView.get("COMMISSION_QUOTASHARE_DC")==null?"0":treatyView.get("COMMISSION_QUOTASHARE_DC").toString()));
					bean.setPremium_surplus_usd(DropDownControllor.formatter(treatyView.get("PREMIUM_SURPLUS_DC")==null?"0":treatyView.get("PREMIUM_SURPLUS_DC").toString()));
					bean.setComission_surplus_usd(DropDownControllor.formatter(treatyView.get("COMMISSION_SURPLUS_DC")==null?"0":treatyView.get("COMMISSION_SURPLUS_DC").toString()));
					bean.setPremium_portfolio_in_usd(DropDownControllor.formatter(treatyView.get("PREMIUM_PORTFOLIOIN_DC")==null?"0":treatyView.get("PREMIUM_PORTFOLIOIN_DC").toString()));
					bean.setCliam_portfolio_usd(DropDownControllor.formatter(treatyView.get("CLAIM_PORTFOLIOIN_DC")==null?"0":treatyView.get("CLAIM_PORTFOLIOIN_DC").toString()));
					bean.setPremium_PortfolioOut_usd(DropDownControllor.formatter(treatyView.get("PREMIUM_PORTFOLIOOUT_DC")==null?"0":treatyView.get("PREMIUM_PORTFOLIOOUT_DC").toString()));
					bean.setLoss_Reserve_released_usd(DropDownControllor.formatter(treatyView.get("LOSS_RESERVE_RELEASED_DC")==null?"0":treatyView.get("LOSS_RESERVE_RELEASED_DC").toString()));
					bean.setPremium_reserve_quota_share_usd(DropDownControllor.formatter(treatyView.get("PREMIUMRESERVE_QUOTASHARE_DC")==null?"0":treatyView.get("PREMIUMRESERVE_QUOTASHARE_DC").toString()));
					bean.setCash_loss_credit_usd(DropDownControllor.formatter(treatyView.get("CASH_LOSS_CREDIT_DC")==null?"0":treatyView.get("CASH_LOSS_CREDIT_DC").toString()));
					bean.setLoss_reserve_retained_usd(DropDownControllor.formatter(treatyView.get("LOSS_RESERVERETAINED_DC")==null?"0":treatyView.get("LOSS_RESERVERETAINED_DC").toString()));
					bean.setProfit_commission_usd(DropDownControllor.formatter(treatyView.get("PROFIT_COMMISSION_DC")==null?"0":treatyView.get("PROFIT_COMMISSION_DC").toString()));
					bean.setCash_loss_paid_usd(DropDownControllor.formatter(treatyView.get("CASH_LOSSPAID_DC")==null?"0":treatyView.get("CASH_LOSSPAID_DC").toString()));
					bean.setClams_paid_usd(DropDownControllor.formatter(treatyView.get("CLAIMS_PAID_DC")==null?"0":treatyView.get("CLAIMS_PAID_DC").toString()));
					bean.setXl_cost_usd(DropDownControllor.formatter(treatyView.get("XL_COST_DC")==null?"0":treatyView.get("XL_COST_DC").toString()));
					bean.setCliam_portfolio_out_usd(DropDownControllor.formatter(treatyView.get("CLAIM_PORTFOLIO_OUT_DC")==null?"0":treatyView.get("CLAIM_PORTFOLIO_OUT_DC").toString()));
					bean.setPremium_Reserve_Released_usd(DropDownControllor.formatter(treatyView.get("PREMIUM_RESERVE_REALSED_DC")==null?"0":treatyView.get("PREMIUM_RESERVE_REALSED_DC").toString()));
					//bean.setNet_due_usd(DropDownControllor.formatter(treatyView.get("NETDUE_DC")==null?"":treatyView.get("NETDUE_DC").toString()));
					bean.setOtherCostUSD(DropDownControllor.formatter(treatyView.get("OTHER_COST_DC")==null?"0":treatyView.get("OTHER_COST_DC").toString()));
					bean.setRemarks(treatyView.get("REMARKS")==null?"":treatyView.get("REMARKS").toString());
					bean.setInterest(DropDownControllor.formatter(treatyView.get("INTEREST_OC")==null?"0":treatyView.get("INTEREST_OC").toString()));
					bean.setInterestDC(DropDownControllor.formatter(treatyView.get("INTEREST_DC")==null?"0":treatyView.get("INTEREST_DC").toString()));
					bean.setOsClaimsLossUpdateOC(DropDownControllor.formatter(treatyView.get("OSCLAIM_LOSSUPDATE_OC")==null?"0":treatyView.get("OSCLAIM_LOSSUPDATE_OC").toString()));
					bean.setOsClaimsLossUpdateDC(DropDownControllor.formatter(treatyView.get("OSCLAIM_LOSSUPDATE_DC")==null?"0":treatyView.get("OSCLAIM_LOSSUPDATE_DC").toString()));
					bean.setOverrider(DropDownControllor.formatter(treatyView.get("OVERRIDER_AMT_OC")==null?"0":treatyView.get("OVERRIDER_AMT_OC").toString()));
					bean.setOverriderUSD(DropDownControllor.formatter(treatyView.get("OVERRIDER_AMT_DC")==null?"0":treatyView.get("OVERRIDER_AMT_DC").toString()));
					bean.setAmendmentDate(treatyView.get("AMENDMENT_DATE")==null?"":treatyView.get("AMENDMENT_DATE").toString());
	                bean.setWithHoldingTaxOC(DropDownControllor.formatter(treatyView.get("WITH_HOLDING_TAX_OC")==null?"0":treatyView.get("WITH_HOLDING_TAX_OC").toString()));
	                bean.setWithHoldingTaxDC(DropDownControllor.formatter(treatyView.get("WITH_HOLDING_TAX_DC")==null?"0":treatyView.get("WITH_HOLDING_TAX_DC").toString()));
	                bean.setDueDate(treatyView.get("due_date")==null?"":treatyView.get("due_date").toString());
	                //bean.setCreditsign(treatyView.get("NETDUE_OC")==null?"":treatyView.get("NETDUE_OC").toString());
	                //bean.setRi_cession(treatyView.get("RI_CESSION")==null?"":treatyView.get("RI_CESSION").toString());
	                bean.setTaxDedectSource(DropDownControllor.formatter(treatyView.get("TDS_OC")==null?"0":treatyView.get("TDS_OC").toString()));
					bean.setTaxDedectSourceDc(DropDownControllor.formatter(treatyView.get("TDS_DC")==null?"0":treatyView.get("TDS_DC").toString()));
					bean.setServiceTax(DropDownControllor.formatter(treatyView.get("ST_OC")==null?"0":treatyView.get("ST_OC").toString()));
					bean.setServiceTaxDc(DropDownControllor.formatter(treatyView.get("ST_DC")==null?"0":treatyView.get("ST_DC").toString()));
					bean.setLossParticipation(DropDownControllor.formatter(treatyView.get("LPC_OC")==null?"0":treatyView.get("LPC_OC").toString()));
					bean.setLossParticipationDC(DropDownControllor.formatter(treatyView.get("LPC_DC")==null?"0":treatyView.get("LPC_DC").toString()));
					bean.setSlideScaleCom(DropDownControllor.formatter(treatyView.get("SC_COMM_OC")==null?"0":treatyView.get("SC_COMM_OC").toString()));
					bean.setSlideScaleComDC(DropDownControllor.formatter(treatyView.get("SC_COMM_DC")==null?"0":treatyView.get("SC_COMM_DC").toString()));
					bean.setExchRate(DropDownControllor.exchRateFormat(treatyView.get("EXCHANGE_RATE")==null?"0":treatyView.get("EXCHANGE_RATE").toString()));
					bean.setTotalCredit(DropDownControllor.formatter(treatyView.get("TOTAL_OC")==null?"0":treatyView.get("TOTAL_OC").toString()));
					bean.setTotalCreditDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(bean.getTotalCredit().replaceAll(",", ""),bean.getExchRate())));
					bean.setTotalDebit(DropDownControllor.formatter(treatyView.get("TOTAL_DC")==null?"0":treatyView.get("TOTAL_DC").toString()));
					bean.setTotalDebitDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(bean.getTotalDebit().replaceAll(",", ""),bean.getExchRate())));
					String valu =Double.toString(Double.parseDouble(bean.getTotalCredit().replaceAll(",", ""))-Double.parseDouble(bean.getTotalDebit().replaceAll(",", "")));
					bean.setNetDue(DropDownControllor.formatter(valu));
					bean.setNet_due_usd(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(bean.getNetDue().replaceAll(",", ""),bean.getExchRate())));
					bean.setStatementDate(treatyView.get("STATEMENT_DATE")==null?"":treatyView.get("STATEMENT_DATE").toString());
					bean.setPremiumClass(treatyView.get("TMAS_DEPARTMENT_NAME")==null?"":treatyView.get("TMAS_DEPARTMENT_NAME").toString());
		            bean.setPremiumSubClass(treatyView.get("SPC")==null?"":treatyView.get("SPC").toString());
	                if(!"ALL".equalsIgnoreCase(bean.getPremiumSubClass().toString())){
	                	bean.setPremiumSubClass(treatyView.get("PREMIUM_SUBCLASS_NAME")==null?"":treatyView.get("PREMIUM_SUBCLASS_NAME").toString());
	                }
	                bean.setReference(treatyView.get("REFERENCE")==null?"":treatyView.get("REFERENCE").toString());
		        bean.setUwYear(treatyView.get("UWY")==null?"":treatyView.get("UWY").toString()) ;
		        bean.setBusinessType(treatyView.get("BUSINESS_TYPE")==null?"":treatyView.get("BUSINESS_TYPE").toString());
			}
			 	query=getQuery(DBConstants.PREMIUM_SELECT_SUMOFPAIDPREMIUM);
				bean.setSum_of_paid_premium((String)this.mytemplate.queryForObject(query,new Object[]{bean.getContNo()},String.class));
		   	if(StringUtils.isNotBlank(bean.getCurrencyId())){
				query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY);
			
				bean.setCurrency((String)this.mytemplate.queryForObject(query,new Object[]{bean.getCurrencyId(),bean.getBranchCode()},String.class));
		   	}
		   	query=getQuery(DBConstants.PREMIUM_SELECT_CURRENCY_NAME);
		   	bean.setCurrencyName((String)this.mytemplate.queryForObject(query,new Object[]{bean.getBranchCode()},String.class));

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean PremiumEdit(FaculPremiumBean bean, String countryId) {
		LOGGER.info("PremiumDAOImpl premiumEdit || Enter");
		 String query="";
		 boolean saveFlag=false;
				String[] args = new String[2];
				args[0] = bean.getContNo();
				args[1] = bean.getTransactionNo();
				query=getQuery("GET_RETRO_PREMIUM");
				LOGGER.info("Query=>"+query.toString());
				List<Map<String,Object>> list=this.mytemplate.queryForList(query, args);

			for(int i=0;i<list.size();i++){
				Map<String,Object> editPremium=list.get(i);
						bean.setTransaction(editPremium.get("TRANSACTION_MONTH_YEAR")==null?"":editPremium.get("TRANSACTION_MONTH_YEAR").toString());
						bean.setCurrencyId(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						bean.setCurrency(editPremium.get("CURRENCY_ID")==null?"":editPremium.get("CURRENCY_ID").toString());
						if(null==editPremium.get("EXCHANGE_RATE")){
							bean.setExchRate(new DropDownControllor().GetExchangeRate(bean.getCurrencyId(),bean.getTransaction(),countryId,bean.getBranchCode()));
						}
						else{
						bean.setExchRate(DropDownControllor.exchRateFormat(editPremium.get("EXCHANGE_RATE")==null?"":editPremium.get("EXCHANGE_RATE").toString()));
						}
						bean.setBrokerage(editPremium.get("BROKERAGE_AMT_OC")==null?"":DropDownControllor.formatter(editPremium.get("BROKERAGE_AMT_OC").toString()));
						bean.setTax(editPremium.get("TAX_AMT_OC")==null?"":DropDownControllor.formatter(editPremium.get("TAX_AMT_OC").toString()));
						bean.setPremiumQuotaShare(editPremium.get("PREMIUM_QUOTASHARE_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUM_QUOTASHARE_OC").toString()));
						bean.setCommissionQuotaShare(editPremium.get("COMMISSION_QUOTASHARE_OC")==null?"":DropDownControllor.formatter(editPremium.get("COMMISSION_QUOTASHARE_OC").toString()));
						bean.setPremiumSurplus(editPremium.get("PREMIUM_SURPLUS_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUM_SURPLUS_OC").toString()));
						bean.setCommissionSurplus(editPremium.get("COMMISSION_SURPLUS_OC")==null?"":DropDownControllor.formatter(editPremium.get("COMMISSION_SURPLUS_OC").toString()));
						bean.setPremiumportifolioIn(editPremium.get("PREMIUM_PORTFOLIOIN_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUM_PORTFOLIOIN_OC").toString()));
						bean.setCliamPortfolioin(editPremium.get("CLAIM_PORTFOLIOIN_OC")==null?"":DropDownControllor.formatter(editPremium.get("CLAIM_PORTFOLIOIN_OC").toString()));
						bean.setPremiumportifolioout(editPremium.get("PREMIUM_PORTFOLIOOUT_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUM_PORTFOLIOOUT_OC").toString()));
						bean.setLossReserveReleased(editPremium.get("LOSS_RESERVE_RELEASED_OC")==null?"":DropDownControllor.formatter(editPremium.get("LOSS_RESERVE_RELEASED_OC").toString()));
						bean.setPremiumReserve_QuotaShare(editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUMRESERVE_QUOTASHARE_OC").toString()));
						bean.setCashLoss_Credit(editPremium.get("CASH_LOSS_CREDIT_OC")==null?"":DropDownControllor.formatter(editPremium.get("CASH_LOSS_CREDIT_OC").toString()));
						bean.setLoss_ReserveRetained(editPremium.get("LOSS_RESERVERETAINED_OC")==null?"":DropDownControllor.formatter(editPremium.get("LOSS_RESERVERETAINED_OC").toString()));
						bean.setProfit_Commission(editPremium.get("PROFIT_COMMISSION_OC")==null?"":DropDownControllor.formatter(editPremium.get("PROFIT_COMMISSION_OC").toString()));
						bean.setCash_LossPaid(editPremium.get("CASH_LOSSPAID_OC")==null?"":DropDownControllor.formatter(editPremium.get("CASH_LOSSPAID_OC").toString()));
						bean.setStatus(editPremium.get("STATUS")==null?"":editPremium.get("STATUS").toString());
						bean.setNetDue(DropDownControllor.formatter(editPremium.get("NET_DUE_OC")==null?"0":editPremium.get("NET_DUE_OC").toString()));
						bean.setNet_due_usd(DropDownControllor.formatter(editPremium.get("NET_DUE_DC")==null?"0":editPremium.get("NET_DUE_DC").toString()));
						///bean.setReceipt_no(editPremium.get("RECEIPT_NO").toString());
						bean.setClaims_paid(editPremium.get("CLAIMS_PAID_OC")==null?"":DropDownControllor.formatter(editPremium.get("CLAIMS_PAID_OC").toString()));
					    bean.setMd_premium(editPremium.get("M_DPREMIUM_OC")==null?"":DropDownControllor.formatter(editPremium.get("M_DPREMIUM_OC").toString()));
					    bean.setAdjustment_premium(editPremium.get("ADJUSTMENT_PREMIUM_OC")==null?"":DropDownControllor.formatter(editPremium.get("ADJUSTMENT_PREMIUM_OC").toString()));
					    bean.setRecuirement_premium(editPremium.get("REC_PREMIUM_OC")==null?"":DropDownControllor.formatter(editPremium.get("REC_PREMIUM_OC").toString()));
					    bean.setCommission(editPremium.get("COMMISSION")==null?"":DropDownControllor.formatter(editPremium.get("COMMISSION").toString()));
					    ///bean.setInstlmentNo(editPremium.get("INSTALMENT_NUMBER").toString());
					    bean.setXl_Cost(editPremium.get("XL_COST_OC")==null?"":DropDownControllor.formatter(editPremium.get("XL_COST_OC").toString()));
					    bean.setCliam_portfolio_out(editPremium.get("CLAIM_PORTFOLIO_OUT_OC")==null?"":DropDownControllor.formatter(editPremium.get("CLAIM_PORTFOLIO_OUT_OC").toString()));
					    bean.setPremium_Reserve_Released(editPremium.get("PREMIUM_RESERVE_REALSED_OC")==null?"":DropDownControllor.formatter(editPremium.get("PREMIUM_RESERVE_REALSED_OC").toString()));
					    bean.setOtherCost(editPremium.get("OTHER_COST_OC")==null?"":DropDownControllor.formatter(editPremium.get("OTHER_COST_OC").toString()));
						bean.setRemarks(editPremium.get("REMARKS")==null?"":editPremium.get("REMARKS").toString());
						bean.setNetDue(editPremium.get("NETDUE_OC")==null?"":DropDownControllor.formatter(editPremium.get("NETDUE_OC").toString()));
						bean.setInterest(DropDownControllor.formatter(editPremium.get("INTEREST_OC")==null?"0":editPremium.get("INTEREST_OC").toString()));
						bean.setOsClaimsLossUpdateOC(DropDownControllor.formatter(editPremium.get("OSCLAIM_LOSSUPDATE_OC")==null?"0":editPremium.get("OSCLAIM_LOSSUPDATE_OC").toString()));
						bean.setOverrider(editPremium.get("OVERRIDER_AMT_OC")==null?"":DropDownControllor.formatter(editPremium.get("OVERRIDER_AMT_OC").toString()));
						bean.setOverriderUSD(editPremium.get("OVERRIDER_AMT_DC")==null?"":DropDownControllor.formatter(editPremium.get("OVERRIDER_AMT_DC").toString()));
						bean.setAmendmentDate(editPremium.get("AMENDMENT_DATE")==null?"":editPremium.get("AMENDMENT_DATE").toString());
	                    bean.setWithHoldingTaxOC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_OC")==null?"":(editPremium.get("WITH_HOLDING_TAX_OC").toString())));
	                    bean.setWithHoldingTaxDC(DropDownControllor.formatter(editPremium.get("WITH_HOLDING_TAX_DC")==null?"":(editPremium.get("WITH_HOLDING_TAX_DC").toString())));
	                    bean.setRi_cession(editPremium.get("RI_CESSION")==null?"":DropDownControllor.formatter(editPremium.get("RI_CESSION").toString()));
	                    bean.setTaxDedectSource(DropDownControllor.formatter(editPremium.get("TDS_OC")==null?"0":editPremium.get("TDS_OC").toString()));
	    				bean.setTaxDedectSourceDc(DropDownControllor.formatter(editPremium.get("TDS_DC")==null?"0":editPremium.get("TDS_DC").toString()));
	    				bean.setServiceTax(DropDownControllor.formatter(editPremium.get("ST_OC")==null?"0":editPremium.get("ST_OC").toString()));
	    				bean.setServiceTaxDc(DropDownControllor.formatter(editPremium.get("ST_DC")==null?"0":editPremium.get("ST_DC").toString()));
	    				bean.setLossParticipation(DropDownControllor.formatter(editPremium.get("LPC_OC")==null?"0":editPremium.get("LPC_OC").toString()));
	    				bean.setLossParticipationDC(DropDownControllor.formatter(editPremium.get("LPC_DC")==null?"0":editPremium.get("LPC_DC").toString()));
						bean.setSlideScaleCom(DropDownControllor.formatter(editPremium.get("SC_COMM_OC")==null?"0":editPremium.get("SC_COMM_OC").toString()));
						bean.setSlideScaleComDC(DropDownControllor.formatter(editPremium.get("SC_COMM_DC")==null?"0":editPremium.get("SC_COMM_DC").toString()));
						bean.setSubProfitId(editPremium.get("SPC")==null?"":editPremium.get("SPC").toString());
						bean.setPrAllocatedAmount(editPremium.get("PRD_ALLOCATED_TILL_DATE")==null?"":DropDownControllor.formatter(editPremium.get("PRD_ALLOCATED_TILL_DATE").toString()));
						bean.setLrAllocatedAmount(editPremium.get("LRD_ALLOCATED_TILL_DATE")==null?"":DropDownControllor.formatter(editPremium.get("LRD_ALLOCATED_TILL_DATE").toString()));
						bean.setPredepartment(editPremium.get("PREMIUM_CLASS")==null?"":editPremium.get("PREMIUM_CLASS").toString()) ;
						bean.setUwYear(editPremium.get("UWY")==null?"":editPremium.get("UWY").toString()) ;
						 bean.setReference(editPremium.get("REFERENCE")==null?"":editPremium.get("REFERENCE").toString());
						 bean.setBusinessType(editPremium.get("BUSINESS_TYPE")==null?"":editPremium.get("BUSINESS_TYPE").toString());
					}
			LOGGER.info("PremiumDAOImpl premiumEdit || Exit");
				return saveFlag;
	}
	
	
	
}
