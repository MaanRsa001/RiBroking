package com.maan.calculation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.common.db.MyJdbcTemplate;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;


public class TdsCalculation extends MyJdbcTemplate {
	final static Logger logger = LogUtil.getLogger(TdsCalculation.class);
	final static DecimalFormat twoDigit = new DecimalFormat("###0.00");
 	public static boolean TdsfacPremiumCalculation(final String brokarge,final String tdsRate,final String tdsType,final String exchRate,final String Pid)
	{
 		
 		
 		double dvalue=(Double.parseDouble(brokarge)*(Double.parseDouble(tdsRate)/100.0));
		logger.info("Treaty- Limit Orig Curr-Our Share=>" + dvalue);
		final double dround=Math.round(dvalue*100.0)/100.0;
	    final String tdsAmount=twoDigit.format(dround);
	    logger.info("Formated Value=>" + tdsAmount);
		final String tdsDc=DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(tdsAmount,exchRate));
 		
		
		return false; 
		
	}
 	public  String serviceYTaxCal(String amount){
 		String servicetax="";
 		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
 		String query=getQuery("GET_SERVICE_TAX");
 		list=this.mytemplate.queryForList(query);
 		if(list!=null && list.size()>0){
 			Map<String, Object> resMap = null;
 			resMap = (Map<String, Object>)list.get(0);
 			servicetax=resMap.get("SERVICE_TAX")==null?"":resMap.get("SERVICE_TAX").toString();
 		}
 		double dvalue=(Double.parseDouble(servicetax)*(Double.parseDouble(amount)/100.0));
		logger.info("Service Tax out put=>" + dvalue);
		final double dround=Math.round(dvalue*100.0)/100.0;
	    final String serviceAmount=twoDigit.format(dround);
	    logger.info("Formated Value=>" + serviceAmount);
 		
 		
 		return serviceAmount;
 	}


}
