package com.maan.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.maan.bean.ClaimBean;
import com.maan.bean.FaculPremiumBean;
import com.maan.bean.FaculitivesBean;
import com.maan.bean.JournalBean;
import com.maan.bean.RiskDetailsBean;
import com.maan.bean.TreasuryBean;
import com.maan.common.util.DropDownControllor;
import com.maan.common.util.LogUtil;

public class CommonCalculation {
	Logger logger = LogUtil.getLogger(getClass());
	public String calculateTreasuryPayment(TreasuryBean bean, String type, int i) {
		String result ="";
		double amt = 0;
		try{
			if("netAmt".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Net Amount "+bean.getNetAmt());
				String premiumRate=StringUtils.isBlank(bean.getPaymentamount())?"0":bean.getPaymentamount().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getBankCharges())?"0":bean.getBankCharges().replaceAll(",", "");
				String coverlimit1=StringUtils.isBlank(bean.getWithHoldingTaxOC())?"0":bean.getWithHoldingTaxOC().replaceAll(",", "");
				String pemiumLavy=StringUtils.isBlank(bean.getPremiumLavy())?"0":bean.getPremiumLavy().replaceAll(",", "");
				if("PT".equalsIgnoreCase(bean.getTransType())){
					amt = (Double.parseDouble(premiumRate) +  Double.parseDouble(coverlimit)) - Double.parseDouble(coverlimit1)- Double.parseDouble(pemiumLavy);
				}else{
					amt = (Double.parseDouble(premiumRate) -  Double.parseDouble(coverlimit)) - Double.parseDouble(coverlimit1)- Double.parseDouble(pemiumLavy);
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Net Amount "+result);
			}
			else if("baseCur".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Base Currency Amount "+bean.getBaseCurrencyAmount());
				String premiumRate=StringUtils.isBlank(bean.getPaymentamount())?"0":bean.getPaymentamount().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getExrate())?"0":bean.getExrate().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Base Currency Amount "+result);
			}else if("ConRec".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Converted Paid Amount "+bean.getConRecCurValList().get(i) + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getPayamountValList().get(i))?"0":bean.getPayamountValList().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getSetExcRateValList().get(i))?"0":bean.getSetExcRateValList().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Converted Paid Amount "+result + " in Row number" +i);
			}else if("totalAmt".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total Amount "+bean.getRowamountValList().get(i) + "in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getPayamountValList().get(i))?"0":bean.getPayamountValList().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getExachangeValList().get(i))?"0":bean.getExachangeValList().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total Amount "+result + " in Row number" +i);
			}else if("setExchangeRate".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Client Exchange Rate "+bean.getSetExcRateValList().get(i) + "in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getPayamountValList().get(i))?"0":bean.getPayamountValList().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getConRecCurValList().get(i))?"0":bean.getConRecCurValList().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formattereight(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Client Exchange Rate "+result + " in Row number " +i);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculateClaimRegistration(ClaimBean bean, String type) {
		String result ="";
		double amt = 0;
		try{
			if("Surveyor".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Surveyor / Adjustor Fee - Our Share- OC "+bean.getSurveyorAdjesterOurShareOC() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getSurveyorAdjesterPerOC())?"0":bean.getSurveyorAdjesterPerOC().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Surveyor / Adjustor Fee - Our Share- OC "+result );
			}
			else if ("professional".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Other Professional Fee - Our Share- OC "+bean.getProfessionalOurShareOc() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getOtherProfessionalPerOc())?"0":bean.getOtherProfessionalPerOc().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Other Professional Fee - Our Share- OC "+result );
			}else if ("Gross".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Gross Loss to the Contract - Our Share - OC "+bean.getLoss_Estimate_Our_share_Orig_Curr() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getLoss_Estimate_Orig_Curr())?"0":bean.getLoss_Estimate_Orig_Curr().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Gross Loss to the Contract - Our Share - OC "+result );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculateClaimPayment(ClaimBean bean, String type) {
		String result ="";
		double amt = 0;
		try{
			if("Total".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total Claims Paid - Our Share - OC "+bean.getPaid_Amount_Orig_curr() );
				String premiumRate=StringUtils.isBlank(bean.getPaid_claim_os())?"0":bean.getPaid_claim_os().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getSurveyor_fee_os())?"0":bean.getSurveyor_fee_os().replaceAll(",", "");
				String premiumRate1=StringUtils.isBlank(bean.getOther_prof_fee_os())?"0":bean.getOther_prof_fee_os().replaceAll(",", "");
				
				amt = (Double.parseDouble(premiumRate) + Double.parseDouble(coverlimit) + Double.parseDouble(premiumRate1));
				if("3".equalsIgnoreCase(bean.getProductId())){
					String coverlimit1=StringUtils.isBlank(bean.getReinstPremiumOCOS())?"0":bean.getReinstPremiumOCOS().replaceAll(",", "");
					amt =  amt - Double.parseDouble(coverlimit1);
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total Claims Paid - Our Share - OC "+result );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculateClaimReversal(ClaimBean bean, String type) {
		String result ="";
		double amt = 0;
		try{
			if("Surveyor".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Surveyor / Adjustor Fee - Our Share- OC "+bean.getUpdatesurveyorAdjesterOurShareOC() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getUpdatesurveyorAdjesterPerOC())?"0":bean.getUpdatesurveyorAdjesterPerOC().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Surveyor / Adjustor Fee - Our Share- OC "+result );
			}
			else if ("Professional".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Other Professional Fee - Our Share- OC "+bean.getUpdateprofessionalOurShareOc() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getUpdateotherProfessionalPerOc())?"0":bean.getUpdateotherProfessionalPerOc().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Other Professional Fee - Our Share- OC "+result );
			}else if ("OutRes".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Outstanding Reserve Amount - Our Share - OC "+bean.getUpdate_Rivised_original_Cur() );
				String premiumRate=StringUtils.isBlank(bean.getSigned_Share())?"0":bean.getSigned_Share().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getUpdate_Rivised_percentage())?"0":bean.getUpdate_Rivised_percentage().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Outstanding Reserve Amount - Our Share - OC "+result );
			}
			if("Total".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total Claims Paid - Our Share - OC "+bean.getTotalReserveOSOC() );
				String premiumRate=StringUtils.isBlank(bean.getUpdatesurveyorAdjesterOurShareOC())?"0":bean.getUpdatesurveyorAdjesterOurShareOC().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getUpdateprofessionalOurShareOc())?"0":bean.getUpdateprofessionalOurShareOc().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) + Double.parseDouble(bean.getUpdate_Rivised_original_Cur().replaceAll(",", "")) + Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total Claims Paid - Our Share - OC "+result );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculateFacultative(FaculitivesBean bean, String type, int i) {
		String result ="";
		double amt = 0;
		try{
			if("Egnpi".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Fac GWPI – OC – 100%  "+bean.getEgnpiAsPerOff().get(i) + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getPremiumRateList().get(i))?"0":bean.getPremiumRateList().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
				amt = ((Double.parseDouble(premiumRate) /100)* Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Fac GWPI – OC – 100%  "+result + " in Row number " +i);
			}
			else if("Pmlper".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation PML %  "+bean.getPmlPerList().get(i) + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getPmlHundredPer().get(i))?"0":bean.getPmlHundredPer().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
				amt = ((Double.parseDouble(premiumRate) *100)/ Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation PML %  "+result + " in Row number " +i);
			}
			else if("Gwpi".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Fac XOL GWPI – OC – 100%  "+bean.getXolgwpiOC().get(i) + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getXolpremiumRateList().get(i))?"0":bean.getXolpremiumRateList().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getXolcoverLimitOC().get(i))?"0":bean.getXolcoverLimitOC().get(i).replaceAll(",", "");
				amt = ((Double.parseDouble(premiumRate) /100)* Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Fac XOL GWPI – OC – 100%  "+result + " in Row number " +i);
			}
			else if("TotGwpi".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total GWPI  "+bean.getTotalGWPI() );
				if(bean.getEgnpiAsPerOff()!=null){
					for(int j=0;j<bean.getEgnpiAsPerOff().size();j++){
						String premiumRate=StringUtils.isBlank(bean.getEgnpiAsPerOff().get(i))?"0":bean.getEgnpiAsPerOff().get(i).replaceAll(",", "");
						amt = amt+Double.parseDouble(premiumRate);
					}
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total GWPI  "+result );
			}
			else if("TotCoverage".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total Coverage  "+bean.getTotalCoverage() );
				if(bean.getCoverLimitOC()!=null){
					for(int j=0;j<bean.getCoverLimitOC().size();j++){
						String premiumRate=StringUtils.isBlank(bean.getCoverLimitOC().get(i))?"0":bean.getCoverLimitOC().get(i).replaceAll(",", "");
						amt = amt+Double.parseDouble(premiumRate);
					}
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total Coverage  "+result );
			}
			else if("TotDetuctible".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Total Deductible  "+bean.getTotalDeductible() );
				if(bean.getDeductableLimitOC()!=null){
					for(int j=0;j<bean.getDeductableLimitOC().size();j++){
						String premiumRate=StringUtils.isBlank(bean.getDeductableLimitOC().get(i))?"0":bean.getDeductableLimitOC().get(i).replaceAll(",", "");
						amt = amt+Double.parseDouble(premiumRate);
					}
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Total Deductible  "+result );
			}else if("AcqPer".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Acq-Cost % "+bean.getAcqCostPer());
				double a=Double.parseDouble(bean.getCommn().equalsIgnoreCase("")?"0":bean.getCommn().replaceAll(",", ""));
				double b=Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""));
				double c=Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", ""));
				double d=Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", ""));
				amt = a+b+c+d;
				result =  DropDownControllor.formatterpercentage(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Acq-Cost %  "+result );
			}else if("AcqCost".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Acq-Cost Amount "+bean.getAcqCost());
				double a=Double.parseDouble(bean.getCommn().equalsIgnoreCase("")?"0":bean.getCommn().replaceAll(",", ""));
				double b=Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""));
				double c=Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", ""));
				double d=Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", ""));
				amt = ((a+b+c+d)*Double.parseDouble(bean.getGwpiOurShare().replaceAll(",", "")))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Acq-Cost Amount  "+result );
			}
			else if("CapacityUti".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Capacity Utilization %  "+bean.getCu());
				String premiumRate=StringUtils.isBlank(bean.getSumusd())?"0":bean.getSumusd().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getMaxiumlimit())?"0":bean.getMaxiumlimit().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatterpercentage(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Capacity Utilization %  "+result);
			}else if("LossRatio".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Loss Ratio %  "+bean.getLossRatio().get(i) + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getLossInsuredClaim().get(i))?"0":bean.getLossInsuredClaim().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getLossPremium().get(i))?"0":bean.getLossPremium().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) / Double.parseDouble(coverlimit))*100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Loss Ratio %  "+result + " in Row number " +i);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculatePTTY(RiskDetailsBean bean, String type, int i) {
		String result ="";
		double amt = 0;
		try{
			if("EPI".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation EPI (Our Assessment) - 100% - OC   "+bean.getEpi() );
				String premiumRate=StringUtils.isBlank(bean.getEpi_origCur())?"0":bean.getEpi_origCur().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getOurEstimate())?"0":bean.getOurEstimate().replaceAll(",", "");
				if(!"0".equals(premiumRate) && !"0".equals(coverlimit))
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation EPI (Our Assessment) - 100% - OC   "+result );
			}
			else if("OurEstmt".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Our Assessment   "+bean.getOurEstimate() );
				String premiumRate=StringUtils.isBlank(bean.getEpi())?"0":bean.getEpi().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getEpi_origCur())?"0":bean.getEpi_origCur().replaceAll(",", "");
				if(!"0".equals(premiumRate) && !"0".equals(coverlimit))
				amt = (Double.parseDouble(premiumRate) * 100 / Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Our Assessment   "+result );
			}else if("OurAcuCost".equalsIgnoreCase(type)){
				String premiumRate=StringUtils.isBlank(bean.getEpiAsPerOffer())?"0":bean.getEpiAsPerOffer().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getOurassessmentorginalacqcost())?"0":bean.getOurassessmentorginalacqcost().replaceAll(",", "");
				logger.info("Jsp Calculation Our Acq Cost   "+bean.getOuracqCost() );
				if(!"0".equals(premiumRate) && !"0".equals(coverlimit))
				amt = (Double.parseDouble(premiumRate) *  Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Our Acq Cost   "+result );
			}else if("AcqCost".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Our Acq Cost   "+bean.getAcquisition_Cost() );
				double a=0,b=0,c=0;
				if("3".equalsIgnoreCase(bean.getTreatyType())){
					a = (((Double.parseDouble(StringUtils.isBlank(bean.getPremiumQuotaShare())?"0":bean.getPremiumQuotaShare().replaceAll(",", "")) *Double.parseDouble(bean.getShareValue().replaceAll(",", "")))/100)*Double.parseDouble(bean.getCommissionQ_S().equalsIgnoreCase("")?"0":bean.getCommissionQ_S().replaceAll(",", "")))/100 ;
					b = (((Double.parseDouble(StringUtils.isBlank(bean.getPremiumSurplus())?"0":bean.getPremiumSurplus().replaceAll(",", "")) *Double.parseDouble(bean.getShareValue().replaceAll(",", "")))/100)*Double.parseDouble(bean.getCommission_surp().equalsIgnoreCase("")?"0":bean.getCommission_surp().replaceAll(",", "")))/100 ;
					c = (Double.parseDouble(StringUtils.isBlank(bean.getEpiOSViewOC())?"0":bean.getEpiOSViewOC().replaceAll(",", "")) *(Double.parseDouble(bean.getOverRidder().equalsIgnoreCase("")?"0":bean.getOverRidder().replaceAll(",", "")) +Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", "")) +Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", "")) + Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""))))/100;
				}else if("1".equalsIgnoreCase(bean.getTreatyType())||"5".equalsIgnoreCase(bean.getTreatyType())||"4".equalsIgnoreCase(bean.getTreatyType())){
					c= (Double.parseDouble(StringUtils.isBlank(bean.getEpiOSViewOC())?"0":bean.getEpiOSViewOC().replaceAll(",", "")) *( Double.parseDouble(bean.getCommissionQ_S().equalsIgnoreCase("")?"0":bean.getCommissionQ_S().replaceAll(",", ""))+Double.parseDouble(bean.getOverRidder().equalsIgnoreCase("")?"0":bean.getOverRidder().replaceAll(",", "")) +Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", "")) +Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", "")) + Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""))))/100;
				}else if("2".equalsIgnoreCase(bean.getTreatyType())){
					c= (Double.parseDouble(StringUtils.isBlank(bean.getEpiOSViewOC())?"0":bean.getEpiOSViewOC().replaceAll(",", "")) *( Double.parseDouble(bean.getCommission_surp().equalsIgnoreCase("")?"0":bean.getCommission_surp().replaceAll(",", ""))+Double.parseDouble(bean.getOverRidder().equalsIgnoreCase("")?"0":bean.getOverRidder().replaceAll(",", "")) +Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", "")) +Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", "")) + Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""))))/100;
				}
				amt = Double.parseDouble((bean.getOuracqCost()==null||bean.getOuracqCost().equalsIgnoreCase(""))?"0":bean.getOuracqCost().replaceAll(",", "")) + a+b+c;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Our Acq Cost   "+result );
			}else if("Surplus".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Premium Surplus   "+bean.getPremiumSurplus() );
				String premiumRate=StringUtils.isBlank(bean.getEpiOSViewOC())?"0":bean.getEpiOSViewOC().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getPremiumQuotaShare())?"0":bean.getPremiumQuotaShare().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) -  Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Premium Surplus    "+result );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}

		return result;
	}
	public String calculateXOL(RiskDetailsBean bean, String type, int i, String sourceId) {
		String result ="";
		double amt = 0;
		try{
			if("EPI".equalsIgnoreCase(type)){
				double a=0;
				logger.info("Jsp Calculation XL Premium - 100% - OC   "+bean.getEpi() );
				if("1".equalsIgnoreCase(bean.getPremiumbasis())){
				a =  Double.parseDouble((bean.getAdjRate()==null||bean.getAdjRate().equalsIgnoreCase(""))?"0":bean.getAdjRate().replaceAll(",", ""));	
				}else if("2".equalsIgnoreCase(bean.getPremiumbasis())){
				a =  Double.parseDouble((bean.getMinimumRate()==null||bean.getMinimumRate().equalsIgnoreCase(""))?"0":bean.getMinimumRate().replaceAll(",", ""));	
				}
				String premiumRate=StringUtils.isBlank(bean.getSubPremium())?"0":bean.getSubPremium().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * a)/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation XL Premium - 100% - OC   "+result );
			}
			else if("EGNPI".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation EGNPI (Our Assessment) - 100% - OC   "+bean.getSubPremium() );
				String premiumRate=StringUtils.isBlank(bean.getEgnpiOffer())?"0":bean.getEgnpiOffer().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getOurAssessment())?"0":bean.getOurAssessment().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation EGNPI (Our Assessment) - 100% - OC   "+result );
			}else if("OverAss".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Our Assessment   "+bean.getOurAssessment() );
				String premiumRate=StringUtils.isBlank(bean.getSubPremium())?"0":bean.getSubPremium().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getEgnpiOffer())?"0":bean.getEgnpiOffer().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) * 100)/Double.parseDouble(coverlimit);
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Our Assessment   "+result );
			}else if("MinPremium".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Minimum Premium - 100% - OC   "+bean.getMinPremium() );
				String premiumRate=StringUtils.isBlank(bean.getSubPremium())?"0":bean.getSubPremium().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getMinimumRate())?"0":bean.getMinimumRate().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) *Double.parseDouble(coverlimit))/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Minimum Premium - 100% - OC   "+result );
			}else if("EGNPIAsper".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation EGNPI (As per Offer) - 100% - OC   "+bean.getEgnpiOffer() );
				if("5".equalsIgnoreCase(bean.getBusinessType())){
					if(bean.getEgnpiAsPerOffSlide()!=null){
						for(int j=0;j<bean.getEgnpiAsPerOffSlide().size();j++){
							amt = amt+Double.parseDouble(bean.getEgnpiAsPerOffSlide().get(i)==null||bean.getEgnpiAsPerOffSlide().get(i).equalsIgnoreCase("")?"0":bean.getEgnpiAsPerOffSlide().get(i).replaceAll(",", ""));
						}
					}
				}else{
					if(bean.getEgnpiAsPerOffSlide()!=null){
						for(int j=0;j<bean.getEgnpiAsPerOff().size();j++){
							amt = amt+Double.parseDouble(bean.getEgnpiAsPerOff().get(i)==null||bean.getEgnpiAsPerOff().get(i).equalsIgnoreCase("")?"0":bean.getEgnpiAsPerOff().get(i).replaceAll(",", ""));
						}
					}
				}
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation EGNPI (As per Offer) - 100% - OC  "+result );
			}
			else if("AcqCost".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Acq-Cost Amount   "+bean.getAcquisition_Cost() );
				double a = 0;
				if("RI01".equalsIgnoreCase(sourceId)){
					a = Double.parseDouble((bean.getMinPremiumOurShare ()==null||bean.getMinPremiumOurShare().equalsIgnoreCase(""))?"0":bean.getMinPremiumOurShare().replaceAll(",", ""));
				}else{
					a = Double.parseDouble((bean.getEpiAsPerOffer()==null||bean.getEpiAsPerOffer().equalsIgnoreCase(""))?"0":bean.getEpiAsPerOffer().replaceAll(",", ""));
				}
				double b=Double.parseDouble(bean.getOthercost().equalsIgnoreCase("")?"0":bean.getOthercost().replaceAll(",", ""));
				double c=Double.parseDouble(bean.getBrokerage().equalsIgnoreCase("")?"0":bean.getBrokerage().replaceAll(",", ""));
				double d=Double.parseDouble(bean.getTax().equalsIgnoreCase("")?"0":bean.getTax().replaceAll(",", ""));
				amt = ((b+c+d)*a)/100;
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Acq-Cost Amount   "+result );
			}
			else if("PremiumSur".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Premium Surplus - 100% - OC  "+bean.getPremiumSurplus() );
				String premiumRate=StringUtils.isBlank(bean.getEpi())?"0":bean.getEpi().replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getPremiumQuotaShare())?"0":bean.getPremiumQuotaShare().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate) - Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Premium Surplus - 100% - OC   "+result );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	
	public String calculateClaimReInsPopUp(ClaimBean bean, String type,String posi) {
		String result ="";
		double amt = 0;
		try{
			if("Booked".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Booked Premium  "+bean.getClaimBookedPremium().get(Integer.parseInt(posi)));
				String premiumRate=StringUtils.isBlank(bean.getClaimPremOC().get(Integer.parseInt(posi)))?"0":bean.getClaimPremOC().get(Integer.parseInt(posi)).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getClaimExcRate().get(Integer.parseInt(posi)))?"0":bean.getClaimExcRate().get(Integer.parseInt(posi)).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)*Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Booked Premium   "+result );
			}else if("Exchange".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Exchange Rate  "+bean.getClaimExcRate().get(Integer.parseInt(posi)));
				String premiumRate=StringUtils.isBlank(bean.getClaimBookedPremium().get(Integer.parseInt(posi)))?"0":bean.getClaimBookedPremium().get(Integer.parseInt(posi)).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getClaimPremOC().get(Integer.parseInt(posi)))?"0":bean.getClaimPremOC().get(Integer.parseInt(posi)).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Exchange Rate   "+result );
			}else if("ClaimPaid".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Claim Paid – TC "+bean.getClaimPaidPremium().get(Integer.parseInt(posi)).replaceAll(",",""));
				String premiumRate=StringUtils.isBlank(bean.getClaimPaidOC().get(Integer.parseInt(posi)))?"0":bean.getClaimPaidOC().get(Integer.parseInt(posi)).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getClaimEcxhangeRate().get(Integer.parseInt(posi)))?"0":bean.getClaimEcxhangeRate().get(Integer.parseInt(posi)).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)*Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Claim Paid – TC   "+result );
			}
			else if("ClaimExchan".equalsIgnoreCase(type)){
					logger.info("Jsp Calculation Claim Paid – TC "+bean.getClaimEcxhangeRate().get(Integer.parseInt(posi)).replaceAll(",",""));
					String premiumRate=StringUtils.isBlank(bean.getClaimPaidPremium().get(Integer.parseInt(posi)))?"0":bean.getClaimPaidPremium().get(Integer.parseInt(posi)).replaceAll(",", "");
					String coverlimit=StringUtils.isBlank(bean.getClaimPaidOC().get(Integer.parseInt(posi)))?"0":bean.getClaimPaidOC().get(Integer.parseInt(posi)).replaceAll(",", "");
					amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
					result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
					logger.info("Java Calculation Claim Paid – TC   "+result );
				}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
		}
	public String calculatePTTYPopUp(FaculPremiumBean bean, String type, int i) {
		String result ="";
		double amt = 0;
		try{
			if("PremiRes".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Rl. Amount  "+bean.getCreditAmountCLClist().get(i).replaceAll(",", "") + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getCreditAmountCLDlist().get(i))?"0":bean.getCreditAmountCLDlist().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getCLCsettlementRatelist().get(i))?"0":bean.getCLCsettlementRatelist().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Rl. Amount   "+result + " in Row number " +i);
			}else if("CashLoss".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Rl. Amount  "+bean.getCreditAmountCLDlist ().get(i).replaceAll(",", "") + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getCreditAmountCLClist().get(i))?"0":bean.getCreditAmountCLClist().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getCLCsettlementRatelist().get(i))?"0":bean.getCLCsettlementRatelist().get(i).replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Rl. Amount   "+result + " in Row number " +i);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	public String calculateManualJournal(JournalBean bean, String type, int i) {
		String result ="";
		double amt = 0;
		try{
			if("CreditDC".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Credit DC  "+bean.getCreditDC().get(i).replaceAll(",", "") + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getCreditOC().get(i))?"0":bean.getCreditOC().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getExchRate())?"0":bean.getExchRate().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Credit DC   "+result + " in Row number " +i);
			}else if("DebitDC".equalsIgnoreCase(type)){
				logger.info("Jsp Calculation Debit DC  "+bean.getDebitDC().get(i).replaceAll(",", "") + " in Row number " +i);
				String premiumRate=StringUtils.isBlank(bean.getDebitOC().get(i))?"0":bean.getDebitOC().get(i).replaceAll(",", "");
				String coverlimit=StringUtils.isBlank(bean.getExchRate())?"0":bean.getExchRate().replaceAll(",", "");
				amt = (Double.parseDouble(premiumRate)/Double.parseDouble(coverlimit));
				result =  DropDownControllor.formatter(Double.toString(amt)).replaceAll(",", "");
				logger.info("Java Calculation Debit DC   "+result + " in Row number " +i);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(""+e);
		}
		return result;
	}
	
}
