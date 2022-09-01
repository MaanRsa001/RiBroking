package com.maan.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import com.maan.bean.RiskDetailsBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;


public class RdsCalculation {
	final static Logger logger = LogUtil.getLogger(RdsCalculation.class);
	final static DecimalFormat twoDigit = new DecimalFormat("###0.00");
 	public static boolean SecondPageCaculation(final RiskDetailsBean formobj,final String Pid)
	{ 
		if (StringUtils.isEmpty(formobj.getSharSign()))
		{
			if (StringUtils.isNotEmpty(formobj.getLimitOrigCur())
					&& StringUtils.isNotEmpty(formobj.getShareWritt())){
				formobj.setLimitOrigCur(formobj.getLimitOrigCur().replaceAll(",", ""));
				double dvalue=(Double.parseDouble(formobj.getLimitOrigCur())*(Double.parseDouble(formobj.getShareWritt())/100.0));
				logger.info("Treaty- Limit Orig Curr-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setLimitOurShare(valu);
				formobj.setLimitOSViewOC(DropDownControllor.formatter(valu));
				formobj.setLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getLimitOurShare(),formobj.getExchRate())));
		
			}
			if (StringUtils.isNotEmpty(formobj.getFaclimitOrigCur())
					&& StringUtils.isNotEmpty(formobj.getShareWritt())){
				formobj.setFaclimitOrigCur(formobj.getFaclimitOrigCur().replaceAll(",", ""));
				double dvalue=(Double.parseDouble(formobj.getFaclimitOrigCur())*(Double.parseDouble(formobj.getShareWritt())/100.0));
				logger.info("Treaty- Limit Orig Curr-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setLimitOurShare(valu);
				formobj.setLimitOSViewOC(DropDownControllor.formatter(valu));
				formobj.setLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getLimitOurShare(),formobj.getExchRate())));
		
			}
			if (StringUtils.isNotEmpty(formobj.getTreatyLimitsurplusOC())
					&& StringUtils.isNotEmpty(formobj.getShareWritt())){
				formobj.setTreatyLimitsurplusOC(formobj.getTreatyLimitsurplusOC().replaceAll(",", ""));
				double dvalue=(Double.parseDouble(formobj.getTreatyLimitsurplusOC())*(Double.parseDouble(formobj.getShareWritt())/100.0));
				logger.info("Treaty- Limit sulp-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setTreatyLimitsurplusOurShare(valu);
				formobj.setTreatyLimitsurplusOSViewOC(DropDownControllor.formatter(valu));
				formobj.setTreatyLimitsurplusOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getTreatyLimitsurplusOurShare(),formobj.getExchRate())));
		
			}
		
			if(Pid.equalsIgnoreCase("2")||Pid.equalsIgnoreCase("4"))
			{
				if (StringUtils.isNotEmpty(formobj.getEpi_origCur())&& StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{			
					formobj.setEpi_origCur(formobj.getEpi_origCur().replaceAll(",", ""));
					final double dvalue=(Double.parseDouble(formobj.getEpi_origCur()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					final double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    final String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEpiAsPerOffer(valu);
					formobj.setEpiOSOEViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSOEViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerOffer(),formobj.getExchRate())));
				
				}
				if (StringUtils.isNotEmpty(formobj.getEpi())&& StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{			
					formobj.setEpi(formobj.getEpi().replaceAll(",", ""));
					final double dvalue=(Double.parseDouble(formobj.getEpi()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					final double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    final String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEpiAsPerShare(valu);
					formobj.setEpiOSViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerShare(),formobj.getExchRate())));
				
				}
				
				if(Pid.equalsIgnoreCase("2"))
				{
				
					//if (StringUtils.isNotEmpty(formobj.getEpi())&& StringUtils.isNotEmpty(formobj.getShareWritt())) 
					/*if (StringUtils.isNotEmpty(formobj.getEpipml())&& StringUtils.isNotEmpty(formobj.getShareWritt())) 
					{
						final double dvalue=(Double.parseDouble(formobj.getEpipml()) * 
								(Double.parseDouble(formobj.getShareWritt())) / 100);
						final double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
						formobj.setEpiAsPerShare(valu);
						formobj.setEpiOSOEViewOC(DropDownControllor.formatter(valu));
						formobj.setEpiOSOEViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerShare(),formobj.getExchRate())));
				
					}*/
					
					if(StringUtils.isNotEmpty(formobj.getLimitOrigCurPml()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
					{
						formobj.setLimitOrigCurPml(formobj.getLimitOrigCurPml().replaceAll(",", ""));
						double dvalue=(Double.parseDouble(formobj.getLimitOrigCurPml()) * 
								(Double.parseDouble(formobj.getShareWritt())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setLimitOrigCurPmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}
					if(StringUtils.isNotEmpty(formobj.getTreatyLimitsurplusOCPml()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
					{
						formobj.setTreatyLimitsurplusOCPml(formobj.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
						double dvalue=(Double.parseDouble(formobj.getTreatyLimitsurplusOCPml()) * 
								(Double.parseDouble(formobj.getShareWritt())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setTreatyLimitsurplusOCPmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}
					/*if(StringUtils.isNotEmpty(formobj.getEpipml()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
					{
						double dvalue=(Double.parseDouble(formobj.getEpipml()) * 
								(Double.parseDouble(formobj.getShareWritt())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setEpipmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}*/
					
				}
				if (StringUtils.isNotEmpty(formobj.getXlCost())&& StringUtils.isNotEmpty(formobj.getShareWritt()) ) {//&& StringUtils.isNotEmpty(formobj.getEpi())
					formobj.setXlCost(formobj.getXlCost().replaceAll(",", ""));
					double dddd=(Double.parseDouble(formobj.getXlCost()) * Double.parseDouble(formobj.getShareWritt()) / 100);
					double dround=Math.round(dddd*100.0)/100.0;
					logger.info("value=>"+dround);
	    		    String valu=twoDigit.format(dround);
	    		    logger.info("Formated Value=>" + valu);
				    formobj.setXlcostOurShare(valu);
				    formobj.setXlCostViewOC(DropDownControllor.formatter(valu));
					formobj.setXlCostViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
				
				}
			}
			 
			else if(Pid.equalsIgnoreCase("3")||"5".equalsIgnoreCase(Pid))
			{
				if(StringUtils.isNotEmpty(formobj.getEpi()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setEpi(formobj.getEpi().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getEpi()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEpiAsPerOffer(valu);
					formobj.setEpiOSViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerOffer(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getEvent_limit()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setEvent_limit(formobj.getEvent_limit().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getEvent_limit()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEventLimitOurShare(valu);
					//formobj.setEventLimitOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getCoverLimitXL()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setCoverLimitXL(formobj.getCoverLimitXL().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getCoverLimitXL()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setCoverLimitXLOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getDeductLimitXL()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setDeductLimitXL(formobj.getDeductLimitXL().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getDeductLimitXL()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setDeductLimitXLOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				/*if(StringUtils.isNotEmpty(formobj.getEgnpipml()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					double dvalue=(Double.parseDouble(formobj.getEgnpipml()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEgnpipmlOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}*/
				if(StringUtils.isNotEmpty(formobj.getMinPremium()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setMinPremium(formobj.getMinPremium().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getMinPremium()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setMinPremiumOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getM_dPremium()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setM_dPremium(formobj.getM_dPremium().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getM_dPremium()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
	    		    String valu=twoDigit.format(dround);
	    		    logger.info("Formated Value=>" + valu);
					formobj.setMd_premium_our_service(valu);
					formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
					formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					
				}
				if(StringUtils.isNotEmpty(formobj.getMinPremium()) && StringUtils.isNotEmpty(formobj.getShareWritt())) 
				{
					formobj.setMinPremium(formobj.getMinPremium().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getMinPremium()) * 
							(Double.parseDouble(formobj.getShareWritt())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setMinPremiumOurShare(valu);
					formobj.setMinPremiumOSOC(DropDownControllor.formatter(valu));
					formobj.setMinPremiumOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getMinPremiumOurShare(),formobj.getExchRate())));
				
				}
				int nos=0;
				double total=0;
				if(formobj.getM_d_InstalmentNumber()!=null)
				{
					nos=Integer.parseInt(formobj.getM_d_InstalmentNumber());
				}
				if(StringUtils.isNotEmpty(formobj.getMd_premium_our_service()) && nos!=0)
				{
					DropDownControllor controller=new DropDownControllor();
					boolean status=controller.getInstallments(formobj);
					if(!status){
						total=Double.parseDouble(formobj.getMd_premium_our_service())/nos;
						final double dround = Math.round(total * 100.0) / 100.0;
						logger.info("Rounded Value=>" + dround);
						final String valu = twoDigit.format(dround);
						List<String> InstallmentPremium = formobj.getInstallmentPremium()==null?new ArrayList<String>():formobj.getInstallmentPremium();
						/*for(int i=0;i<nos;i++)
						{
							if(i>=InstallmentPremium.size()) {
								InstallmentPremium.add(valu);
							}
							else {
								InstallmentPremium.set(i,valu);
							}
							
							logger.info("Installment Premium=>["+i+"]==> " + valu);
						}*/
						double Sum=0.0;
						for(int i=0;i<nos-1;i++){
							InstallmentPremium.add(valu);
							 Sum=Sum+Double.parseDouble(valu);
							 logger.info("Formated Value1=>" + Sum);
						}
						Double Gwpi=Double.parseDouble(formobj.getMd_premium_our_service());
						String retotal =twoDigit.format(Gwpi-Sum);
						 logger.info("Formated retotal=>" + retotal);
						 InstallmentPremium.add(retotal);
						formobj.setInstallmentPremium(InstallmentPremium);
					}
				}
				if(StringUtils.isNotBlank(formobj.getPaymentDuedays())){
					List<String> days =formobj.getPaymentDueDays()==null?new ArrayList<String>():formobj.getPaymentDueDays();
					for(int i=0;i<nos-1;i++){
						days.add(formobj.getPaymentDuedays());
					}
					formobj.setPaymentDueDays(days);
				}
			}
			if(StringUtils.isNotBlank(formobj.getShareWritt())){
			formobj.setShareValue(formobj.getShareWritt().replaceAll(",", ""));
			}
		}
		else {
			if (StringUtils.isNotEmpty(formobj.getLimitOrigCur())
					&& StringUtils.isNotEmpty(formobj.getSharSign())){
				logger.info("LimitOrigCur =>"+formobj.getLimitOrigCur()+" Share Singed=>"+formobj.getSharSign());
				formobj.setLimitOrigCur(formobj.getLimitOrigCur().replaceAll(",", ""));
				logger.info("LimitOrigCur =>"+formobj.getLimitOrigCur()+" Share Singed=>"+formobj.getSharSign());
				double dvalue=(Double.parseDouble(formobj.getLimitOrigCur())*(Double.parseDouble(formobj.getSharSign())/100.0));
				logger.info("Treaty-Orig Curr-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setLimitOurShare(valu);
				formobj.setLimitOSViewOC(DropDownControllor.formatter(valu));
				formobj.setLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getLimitOurShare(),formobj.getExchRate())));
				
			}
			if (StringUtils.isNotEmpty(formobj.getFaclimitOrigCur())&& StringUtils.isNotEmpty(formobj.getSharSign())){
				logger.info("LimitOrigCur =>"+formobj.getFaclimitOrigCur()+" Share Singed=>"+formobj.getSharSign());
				formobj.setFaclimitOrigCur(formobj.getFaclimitOrigCur().replaceAll(",", ""));
				logger.info("LimitOrigCur =>"+formobj.getLimitOrigCur()+" Share Singed=>"+formobj.getSharSign());
				double dvalue=(Double.parseDouble(formobj.getFaclimitOrigCur())*(Double.parseDouble(formobj.getSharSign())/100.0));
				logger.info("Treaty-Orig Curr-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setLimitOurShare(valu);
				formobj.setLimitOSViewOC(DropDownControllor.formatter(valu));
				formobj.setLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getLimitOurShare(),formobj.getExchRate())));
			}
			if (StringUtils.isNotEmpty(formobj.getTreatyLimitsurplusOC())
					&& StringUtils.isNotEmpty(formobj.getSharSign())){
				formobj.setTreatyLimitsurplusOC(formobj.getTreatyLimitsurplusOC().replaceAll(",", ""));
				double dvalue=(Double.parseDouble(formobj.getTreatyLimitsurplusOC())*(Double.parseDouble(formobj.getSharSign())/100.0));
				logger.info("Treaty- Limit sulp-Our Share=>" + dvalue);
				final double dround=Math.round(dvalue*100.0)/100.0;
			    final String valu=twoDigit.format(dround);
			    logger.info("Formated Value=>" + valu);
				formobj.setTreatyLimitsurplusOurShare(valu);
				formobj.setTreatyLimitsurplusOSViewOC(DropDownControllor.formatter(valu));
				formobj.setTreatyLimitsurplusOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getTreatyLimitsurplusOurShare(),formobj.getExchRate())));
		
			}
			if(Pid.equalsIgnoreCase("2")||Pid.equalsIgnoreCase("4"))
			
			{
			
				if(Pid.equalsIgnoreCase("2"))
				{
					
					/*if (StringUtils.isNotEmpty(formobj.getEpipml())
							&& StringUtils
									.isNotEmpty(formobj.getSharSign())) {
						logger.info("EPI =>"+formobj.getEpi()+" Share Singed=>"+formobj.getSharSign());
						double dvalue=(Double.parseDouble(formobj.getEpipml()) * 
								(Double.parseDouble(formobj.getSharSign())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
	     			    String valu=twoDigit.format(dround);
	     			    logger.info("Formated Value=>" + valu);
					    formobj.setEpiAsPerShare(valu);
						formobj.setEpiOSOEViewOC(DropDownControllor.formatter(valu));
						formobj.setEpiOSOEViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerShare(),formobj.getExchRate())));
					}*/
					
					if(StringUtils.isNotEmpty(formobj.getLimitOrigCurPml()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
					{
						formobj.setLimitOrigCurPml(formobj.getLimitOrigCurPml().replaceAll(",", ""));
						double dvalue=(Double.parseDouble(formobj.getLimitOrigCurPml()) * 
								(Double.parseDouble(formobj.getSharSign())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setLimitOrigCurPmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}
					if(StringUtils.isNotEmpty(formobj.getTreatyLimitsurplusOCPml()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
					{
						formobj.setTreatyLimitsurplusOCPml(formobj.getTreatyLimitsurplusOCPml().replaceAll(",", ""));
						double dvalue=(Double.parseDouble(formobj.getTreatyLimitsurplusOCPml()) * 
								(Double.parseDouble(formobj.getSharSign())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setTreatyLimitsurplusOCPmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}
					/*if(StringUtils.isNotEmpty(formobj.getEpipml()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
					{
						double dvalue=(Double.parseDouble(formobj.getEpipml()) * 
								(Double.parseDouble(formobj.getSharSign())) / 100);
						double dround=Math.round(dvalue*100.0)/100.0;
						logger.info("value=>"+dround);
						String valu=twoDigit.format(dround);
						logger.info("Formated Value=>" + valu);
					    formobj.setEpipmlOS(valu);
					    //formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
						//formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					  
					}*/
					
					
					
				}
				if (StringUtils.isNotEmpty(formobj.getEpi_origCur())&& StringUtils.isNotEmpty(formobj.getSharSign())) {
					formobj.setEpi_origCur(formobj.getEpi_origCur().replaceAll(",", ""));
					logger.info("EPI OC=>"+formobj.getEpi_origCur()+" Share Singed=>"+formobj.getSharSign());
					double dvalue=(Double.parseDouble(formobj.getEpi_origCur()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEpiAsPerOffer(valu);
					formobj.setEpiOSOEViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSOEViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerOffer(),formobj.getExchRate())));
				}
				if (StringUtils.isNotEmpty(formobj.getEpi())&& StringUtils.isNotEmpty(formobj.getSharSign())) {
					formobj.setEpi(formobj.getEpi().replaceAll(",", ""));
					logger.info("EPI OC=>"+formobj.getEpi()+" Share Singed=>"+formobj.getSharSign());
					double dvalue=(Double.parseDouble(formobj.getEpi()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEpiAsPerShare(valu);
					formobj.setEpiOSViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerShare(),formobj.getExchRate())));
				}
				
				if (StringUtils.isNotEmpty(formobj.getXlCost())
						&& StringUtils
								.isNotEmpty(formobj.getSharSign())) {
					logger.info("XLCost=>"+formobj.getXlCost()+" Share Singed=>"+formobj.getSharSign());
					formobj.setXlCost(formobj.getXlCost().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getXlCost()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setXlcostOurShare(valu);
					formobj.setXlCostViewOC(DropDownControllor.formatter(valu));
					formobj.setXlCostViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
					
					
				}
			}
			else if("3".equalsIgnoreCase(Pid)||"5".equalsIgnoreCase(Pid))
			{
				
				if(StringUtils.isNotEmpty(formobj.getEpi()) && StringUtils.isNotEmpty(formobj.getSharSign()))
				{
					formobj.setEpi(formobj.getEpi().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getEpi()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
     			    String valu=twoDigit.format(dround);
     			    logger.info("Formated Value=>" + valu);
				    formobj.setEpiAsPerOffer(valu);
				    formobj.setEpiOSViewOC(DropDownControllor.formatter(valu));
					formobj.setEpiOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEpiAsPerOffer(),formobj.getExchRate())));
				  
				}
				if(StringUtils.isNotEmpty(formobj.getEvent_limit()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					formobj.setEvent_limit(formobj.getEvent_limit().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getEvent_limit()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEventLimitOurShare(valu);
					//formobj.setEventLimitOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getCoverLimitXL()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					formobj.setCoverLimitXL(formobj.getCoverLimitXL().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getCoverLimitXL()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setCoverLimitXLOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				if(StringUtils.isNotEmpty(formobj.getDeductLimitXL()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					formobj.setDeductLimitXL(formobj.getDeductLimitXL().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getDeductLimitXL()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setDeductLimitXLOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}
				/*if(StringUtils.isNotEmpty(formobj.getEgnpipml()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					double dvalue=(Double.parseDouble(formobj.getEgnpipml()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setEgnpipmlOurShare(valu);
					//formobj.setCoverLimitXLOSViewOC(DropDownControllor.formatter(valu));
					//formobj.setEventLimitOSViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getEventLimitOurShare(),formobj.getExchRate())));
				
				}*/
				if(StringUtils.isNotEmpty(formobj.getMinPremium()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					formobj.setMinPremium(formobj.getMinPremium().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getMinPremium()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
				    String valu=twoDigit.format(dround);
				    logger.info("Formated Value=>" + valu);
					formobj.setMinPremiumOurShare(valu);
					formobj.setMinPremiumOSOC(DropDownControllor.formatter(valu));
					formobj.setMinPremiumOSDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(formobj.getMinPremiumOurShare(),formobj.getExchRate())));
				
				}
				
				if(StringUtils.isNotEmpty(formobj.getM_dPremium()) && StringUtils.isNotEmpty(formobj.getSharSign())) 
				{
					logger.info("MD Premium=>"+formobj.getM_dPremium()+" Share Singed=>"+formobj.getSharSign());
					formobj.setM_dPremium(formobj.getM_dPremium().replaceAll(",", ""));
					double dvalue=(Double.parseDouble(formobj.getM_dPremium()) * 
							(Double.parseDouble(formobj.getSharSign())) / 100);
					double dround=Math.round(dvalue*100.0)/100.0;
					logger.info("value=>"+dround);
					String valu=twoDigit.format(dround);
					logger.info("Formated Value=>" + valu);
				    formobj.setMd_premium_our_service(valu);
				    formobj.setMandDpreViewOC(DropDownControllor.formatter(valu));
					formobj.setMandDpreViewDC(DropDownControllor.formatter(DropDownControllor.GetDesginationCountry(valu,formobj.getExchRate())));
				  
				}
				
				
				
			int nos1=0;
			double total=0;
			if(formobj.getM_d_InstalmentNumber()!=null && !"".equals(formobj.getM_d_InstalmentNumber()))
			{
				nos1=Integer.parseInt(formobj.getM_d_InstalmentNumber());
			}
			if(StringUtils.isNotEmpty(formobj.getM_dPremium()))
			{
				DropDownControllor controller=new DropDownControllor();
				boolean status=controller.getInstallments(formobj);
				if(!status){
					formobj.setMd_premium_our_service(formobj.getMd_premium_our_service().replaceAll(",", ""));
					total=Double.parseDouble(formobj.getMd_premium_our_service())/nos1;
					final double dround = Math.round(total * 100.0) / 100.0;
					logger.info("Rounded Value=>" + dround);
					final String valu = twoDigit.format(dround);
					List<String> InstallmentPremium = formobj.getInstallmentPremium()==null?new ArrayList<String>():formobj.getInstallmentPremium();
					/*for(int i=0;i<nos1;i++)
					{
						if(i>=InstallmentPremium.size()) {
							InstallmentPremium.add(valu);
						}
						else {
							InstallmentPremium.set(i,valu);
						}
						
						logger.info("Installment Premium=>["+i+"]==> " + valu);
					}*/
					double Sum=0.0;
					for(int i=0;i<nos1-1;i++){
						InstallmentPremium.add(valu);
						 Sum=Sum+Double.parseDouble(valu);
						 logger.info("Formated Value1=>" + Sum);
					}
					Double Gwpi=Double.parseDouble(formobj.getMd_premium_our_service());
					String retotal =twoDigit.format(Gwpi-Sum);
					 logger.info("Formated retotal=>" + retotal);
					 InstallmentPremium.add(retotal);
					formobj.setInstallmentPremium(InstallmentPremium);
				}
				int nos2=Integer.parseInt(formobj.getM_d_InstalmentNumber());
				if(StringUtils.isNotBlank(formobj.getPaymentDuedays())){
					List<String> days =formobj.getPaymentDueDays()==null?new ArrayList<String>():formobj.getPaymentDueDays();
					for(int i=0;i<nos2;i++){
						days.add(formobj.getPaymentDuedays());
					}
					formobj.setPaymentDueDays(days);
				}
			}
		}
			if(StringUtils.isNotBlank(formobj.getSharSign())){
			formobj.setShareValue(formobj.getSharSign().replaceAll(",", ""));
			}
		}
		return false;
	}


}
