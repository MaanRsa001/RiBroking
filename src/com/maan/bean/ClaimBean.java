package com.maan.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClaimBean {
	
	private String policy_Contract_No;
	private String ceding_Company_Code;
	private String ceding_company_Name;
	private String broker_code;
	private String broker_Name;
	private String original_Insured;
	private String from;
	private String to;
	private String treaty_Name;
	private String nature_of_Coverage;
	private String retention;
	private String limit_Orig_Curr;
	private String limit_Our_share_USD;
	private String signed_Share;
	private String currecny;
	private String remarks;
	private String advice_uw_email;
	private String advice_management_email;
	private String sumOfPaidAmountOC;
	private String risk_Code;
	private String accumulation_Code;
	private String event_Code;
	private String branchCode;
	private String claimPaymentNo;
	private String SNo;
	private String insuredName;
	private String adviceTreasuryEmail;
	private String revSumOfPaidAmt;
	private String reverseClaimYN;
	private String closeClaimYN;
	private String sumInsOSOC;
	private String sumInsOSDC;
	private String cashLossOSOC;
	private String cashLossOSDC;
	private String osAmt;
	private String processId;
	private String loginId;
	private String amendId;
	private String contarctno;
	private String productId;
	private String deptId;
	private String claim_No;
	private String status_of_claim;
	private String date_of_Loss;
	private String report_Date;
	private String loss_Details;
	private String cause_of_Loss;
	private String location;
	private String loss_Estimate_Orig_Curr;
	private String loss_Estimate_Our_share_Orig_Curr;
	private String exc_Rate;
	private String loss_Estimate_Our_Share_USD;
	private String advice_UW;
	private String advice_Mangement;
	private String ri_Recovery;
	private String ri_Recovery_Amount_USD;
	private String recovery_from;
	private String created_by;
	private String created_Date;
	private String modified_by;
	private String modified_Date;
	private String updated_by;
	private String updated_Date;
	private String layerNo;
	private String payment_Request_No;
	private String paid_Amount_Orig_curr;
	private String paid_Amount_USD;
	private String loss_Estimate_Revised_Orig_Curr;
	private String paid_claim_os;
	private String surveyor_fee_os;
	private String other_prof_fee_os;
	private String total_claims_paid_os;
	private String loss_Estimate_Revised_USD;
	private String claim_Note_recommendations;
	private String payment_Reference;
	private String advice_Treasury;
	private String date;
	private String update_Reference;
	private List<Map<String,Object>> orginalCurrency;
	private String BusinessMode;	
	private String path;
	private String review_Date;
	private String review_Done_By;
	private String userID;
	private String MsgFlag;	
	private String cliamMode;
	private String mode;
	private String claimNo;
	private String contractNo;
	private String sample;
	private String update_Rivised_original_Cur;
	private String proposal_No;
	private String cliam_update_Date;
	public String editMode;	
	public String currencyName;	
	public String flag;
	private String shortname;
	private String reinstType;
	private String reinstPremiumOCOS;
	private String reinstPremiumDCOS;
	private String netclaimAmountOCOS;
	private String netclaimAmountDCOS;
	private String settlementStatus;
	private String transactionType;
	private String transactionNumber;
	private String premiumclaimMode;
	private String paymentType;
	private String productName;
	private String sign;
	private String receiptNo;
	private String type;
	private String totalAmount;
	private String inception_Date;
	private List<ClaimBean> Claimlist;
	private String expiryDate;
	private String underwritingYear;
	private String transactionDate;
	private String underwriter;
	private String old_Contract;
	private String claimDisplay;
	private String display;
	private String premiumDisplay;
	private String userName;
	private String opstartDate;
	private String opendDate;
	private String deleteStatus;
	private List<Map<String,Object>> yearList;
	private List<Map<String,Object>> productIdList;
	private List<ClaimBean> premiumList;
	private List<Map<String,Object>> brokerList;
	private List<Map<String,Object>> cedingCompanyList;
	private List<Map<String,Object>> underWriterlist;
	private List<Map<String,Object>> departIdlist;
	private List<ClaimBean> contractIdentifierList;
	private List<Map<String,Object>> predepartIdlist =new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> subProfit_centerlist;
	private List<Map<String,Object>> departmentClassList;
	private String departmentClass;
	
	private String menuStatus;
	private String departmentId;
	private String departmentName;
	private String shareSigned;
	private String uwYear;
	
	private String recordFees;
	private String surveyorAdjesterPerOC;
	private String surveyorAdjesterOurShareOC;
	private String otherProfessionalPerOc;
	private String professionalOurShareOc;
	private String recordIbnr;
	private String ibnrPerOc;
	private String ibnrOurShareOc;
	private String cedentClaimNo;
	private String update_Rivised_percentage;
	private String updaterecordFees;
	private String updatesurveyorAdjesterPerOC;
	private String updatesurveyorAdjesterOurShareOC;
	private String updateotherProfessionalPerOc;
	private String updateprofessionalOurShareOc;
	private String updaterecordIbnr;
	private String updateibnrPerOc;
	private String updateibnrOurShareOc;
	private String oldClaimNumber;
	private String reopen_Date;
	private String proposalType;
	private String basis;
	private String lero2a;
	private String lero2b;
	private String lero2c;
	private String saf3a;
	private String saf3b;
	private String saf3c;
	private String ofos4a;
	private String ofos4b;
	private String ofos4c;
	private String totala;
	private String totalb;
	private String totalc;
	private String reInspremiumOS;
	private String cibnr100Oc;
	private String totalReserveOSOC;
	private String totalReserveOSDC;
	private String grossLossFGU;
	private String preReopenDate;
	private String totalORpaidAmount;
	private String totalSApaidAmount;
	private String totalOPpaidAmount;
	private String totalTORpaidAmount;
	private String subProfitId;
	private String reservePositionDate;
	private String reserveCount;
	private String claim_closed_Date;
	private String consubProfitId;
	private String rdsCurrency;
	private String CeaseStatus;
	private String subProfitCenter;
	private String claimdepartId;
	
	private List<Map<String,Object>> xlPremiumList;
	private List<Map<String,Object>> claimPaidList;
	private List<Map<String,Object>> reinstatementList;
	private List<Map<String,Object>> reinsNumList;
	private List<String> fielddisableClaimsPaid;
	private List<String>  claimBookedPremium;
	private List<String>  claimExcRate;
	private List<String>  claimPremOC;
	private List<String>  claimPremDC;
	private List<Map<String,Object>> cedantNoList;
	
	private String companyNameSearch;
	private String brokerNameSearch;
	private String contractNoSearch;
	private String claimNoSearch;
	private String dateOfLossSearch;
	private String claimStatusSearch;
	private String paymentDateSearch;
	private String paymentNoSearch;
	private String searchType;
	
	private String acceptenceDate;
	
	private String selectedRows;
	private List<Map<String,Object>> paymentNoList;
	private List<Map<String,Object>> multiPaymentNoList;
	private List<Map<String,Object>> claimView;
	private String paymentNo;
	
	
	public String getSelectedRows() {
		return selectedRows;
	}
	public void setSelectedRows(String selectedRows) {
		this.selectedRows = selectedRows;
	}
	public String getAcceptenceDate() {
		return acceptenceDate;
	}
	public void setAcceptenceDate(String acceptenceDate) {
		this.acceptenceDate = acceptenceDate;
	}
	public String getPaymentDateSearch() {
		return paymentDateSearch;
	}
	public void setPaymentDateSearch(String paymentDateSearch) {
		this.paymentDateSearch = paymentDateSearch;
	}
	public String getPaymentNoSearch() {
		return paymentNoSearch;
	}
	public void setPaymentNoSearch(String paymentNoSearch) {
		this.paymentNoSearch = paymentNoSearch;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getCompanyNameSearch() {
		return companyNameSearch;
	}
	public void setCompanyNameSearch(String companyNameSearch) {
		this.companyNameSearch = companyNameSearch;
	}
	public String getBrokerNameSearch() {
		return brokerNameSearch;
	}
	public void setBrokerNameSearch(String brokerNameSearch) {
		this.brokerNameSearch = brokerNameSearch;
	}
	public String getContractNoSearch() {
		return contractNoSearch;
	}
	public void setContractNoSearch(String contractNoSearch) {
		this.contractNoSearch = contractNoSearch;
	}
	public String getClaimNoSearch() {
		return claimNoSearch;
	}
	public void setClaimNoSearch(String claimNoSearch) {
		this.claimNoSearch = claimNoSearch;
	}
	public String getDateOfLossSearch() {
		return dateOfLossSearch;
	}
	public void setDateOfLossSearch(String dateOfLossSearch) {
		this.dateOfLossSearch = dateOfLossSearch;
	}
	public String getClaimStatusSearch() {
		return claimStatusSearch;
	}
	public void setClaimStatusSearch(String claimStatusSearch) {
		this.claimStatusSearch = claimStatusSearch;
	}
	public List<Map<String, Object>> getCedantNoList() {
		return cedantNoList;
	}
	public void setCedantNoList(List<Map<String, Object>> cedantNoList) {
		this.cedantNoList = cedantNoList;
	}
	public List<String> getFielddisableClaimsPaid() {
		return fielddisableClaimsPaid;
	}
	public void setFielddisableClaimsPaid(List<String> fielddisableClaimsPaid) {
		this.fielddisableClaimsPaid = fielddisableClaimsPaid;
	}
	private List<String>  claimPaidOC;
	private List<String>  claimPaidDC;
	private List<String>  claimEcxhangeRate;
	private List<String>  claimPaidPremium;
	private List<String>  reinsNum;
	private List<String>  baseExchRate;
	private List<String>  claimBaseExchRate;
	private List<String>  paidSno;
	private List<String>  claimNoList;
	private List<String>  claimPaymentNoList;
	private List<String>  departmentNameList;
	private List<String>  inceptionDateList;
	private List<String>  currencyNameList;
	private List<String>  rdsCurrencyNameList;
	private List<String>  paymentCoverNum;
	private List<String>  paymentCoverPlus;
	private String reinsCalVal;
	private String disabledFields;
	private String totalBookedPremium;
	private String coverLimitAmount;
	
	private String allocatedYN;
	private String transOpenperiodStatus;
	private String paymentFlag;
	
	private List<String> fielddisableXlPrm;
	
	private String claimMasterMode;
	private String reinstatementPremium;
	private String multiuserError;
	private String dropDown;
	private String rdsCurrencyId;
	
	private String claimCount;
	private String reputed_Date;
	
	
	
	
	public String getReputed_Date() {
		return reputed_Date;
	}
	public void setReputed_Date(String reputedDate) {
		reputed_Date = reputedDate;
	}
	public String getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(String claimCount) {
		this.claimCount = claimCount;
	}
	public String getRdsCurrencyId() {
		return rdsCurrencyId;
	}
	public void setRdsCurrencyId(String rdsCurrencyId) {
		this.rdsCurrencyId = rdsCurrencyId;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public String getMultiuserError() {
		return multiuserError;
	}
	public void setMultiuserError(String multiuserError) {
		this.multiuserError = multiuserError;
	}
	public String getReinstatementPremium() {
		return reinstatementPremium;
	}
	public void setReinstatementPremium(String reinstatementPremium) {
		this.reinstatementPremium = reinstatementPremium;
	}
	public String getClaimMasterMode() {
		return claimMasterMode;
	}
	public void setClaimMasterMode(String claimMasterMode) {
		this.claimMasterMode = claimMasterMode;
	}
	public List<String> getFielddisableXlPrm() {
		return fielddisableXlPrm;
	}
	public void setFielddisableXlPrm(List<String> fielddisableXlPrm) {
		this.fielddisableXlPrm = fielddisableXlPrm;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public String getAllocatedYN() {
		return allocatedYN;
	}
	public void setAllocatedYN(String allocatedYN) {
		this.allocatedYN = allocatedYN;
	}
	public String getTransOpenperiodStatus() {
		return transOpenperiodStatus;
	}
	public void setTransOpenperiodStatus(String transOpenperiodStatus) {
		this.transOpenperiodStatus = transOpenperiodStatus;
	}
	public List<Map<String, Object>> getReinsNumList() {
		return reinsNumList;
	}
	public void setReinsNumList(List<Map<String, Object>> reinsNumList) {
		this.reinsNumList = reinsNumList;
	}
	public String getTotalBookedPremium() {
		return totalBookedPremium;
	}
	public void setTotalBookedPremium(String totalBookedPremium) {
		this.totalBookedPremium = totalBookedPremium;
	}
	
	public String getDepartmentClass() {
		return departmentClass;
	}
	public void setDepartmentClass(String departmentClass) {
		this.departmentClass = departmentClass;
	}
	public List<Map<String, Object>> getDepartmentClassList() {
		return departmentClassList;
	}
	public void setDepartmentClassList(List<Map<String, Object>> departmentClassList) {
		this.departmentClassList = departmentClassList;
	}
	public String getDisabledFields() {
		return disabledFields;
	}
	public void setDisabledFields(String disabledFields) {
		this.disabledFields = disabledFields;
	}
	public String getReinsCalVal() {
		return reinsCalVal;
	}
	public void setReinsCalVal(String reinsCalVal) {
		this.reinsCalVal = reinsCalVal;
	}
	public List<String> getPaymentCoverNum() {
		return paymentCoverNum;
	}
	public void setPaymentCoverNum(List<String> paymentCoverNum) {
		this.paymentCoverNum = paymentCoverNum;
	}
	public List<String> getPaymentCoverPlus() {
		return paymentCoverPlus;
	}
	public void setPaymentCoverPlus(List<String> paymentCoverPlus) {
		this.paymentCoverPlus = paymentCoverPlus;
	}
	public List<Map<String, Object>> getReinstatementList() {
		return reinstatementList;
	}
	public void setReinstatementList(List<Map<String, Object>> reinstatementList) {
		this.reinstatementList = reinstatementList;
	}
	public List<String> getClaimNoList() {
		return claimNoList;
	}
	public void setClaimNoList(List<String> claimNoList) {
		this.claimNoList = claimNoList;
	}
	public List<String> getClaimPaymentNoList() {
		return claimPaymentNoList;
	}
	public void setClaimPaymentNoList(List<String> claimPaymentNoList) {
		this.claimPaymentNoList = claimPaymentNoList;
	}
	public List<String> getDepartmentNameList() {
		return departmentNameList;
	}
	public void setDepartmentNameList(List<String> departmentNameList) {
		this.departmentNameList = departmentNameList;
	}
	public List<String> getInceptionDateList() {
		return inceptionDateList;
	}
	public void setInceptionDateList(List<String> inceptionDateList) {
		this.inceptionDateList = inceptionDateList;
	}
	public List<String> getCurrencyNameList() {
		return currencyNameList;
	}
	public void setCurrencyNameList(List<String> currencyNameList) {
		this.currencyNameList = currencyNameList;
	}
	public List<String> getRdsCurrencyNameList() {
		return rdsCurrencyNameList;
	}
	public void setRdsCurrencyNameList(List<String> rdsCurrencyNameList) {
		this.rdsCurrencyNameList = rdsCurrencyNameList;
	}
	public List<String> getPaidSno() {
		return paidSno;
	}
	public void setPaidSno(List<String> paidSno) {
		this.paidSno = paidSno;
	}
	public List<Map<String, Object>> getClaimPaidList() {
		return claimPaidList;
	}
	public void setClaimPaidList(List<Map<String, Object>> claimPaidList) {
		this.claimPaidList = claimPaidList;
	}
	public List<String> getClaimEcxhangeRate() {
		return claimEcxhangeRate;
	}
	public void setClaimEcxhangeRate(List<String> claimEcxhangeRate) {
		this.claimEcxhangeRate = claimEcxhangeRate;
	}
	public List<String> getBaseExchRate() {
		return baseExchRate;
	}
	public void setBaseExchRate(List<String> baseExchRate) {
		this.baseExchRate = baseExchRate;
	}
	public List<String> getClaimBaseExchRate() {
		return claimBaseExchRate;
	}
	public void setClaimBaseExchRate(List<String> claimBaseExchRate) {
		this.claimBaseExchRate = claimBaseExchRate;
	}
	public List<String> getReinsNum() {
		return reinsNum;
	}
	public void setReinsNum(List<String> reinsNum) {
		this.reinsNum = reinsNum;
	}
	public String getRdsCurrency() {
		return rdsCurrency;
	}
	public void setRdsCurrency(String rdsCurrency) {
		this.rdsCurrency = rdsCurrency;
	}
	
	public List<String> getClaimPaidOC() {
		return claimPaidOC;
	}
	public void setClaimPaidOC(List<String> claimPaidOC) {
		this.claimPaidOC = claimPaidOC;
	}
	public List<String> getClaimPaidDC() {
		return claimPaidDC;
	}
	public void setClaimPaidDC(List<String> claimPaidDC) {
		this.claimPaidDC = claimPaidDC;
	}
	
	public List<String> getClaimPaidPremium() {
		return claimPaidPremium;
	}
	public void setClaimPaidPremium(List<String> claimPaidPremium) {
		this.claimPaidPremium = claimPaidPremium;
	}
	public List<String> getClaimBookedPremium() {
		return claimBookedPremium;
	}
	public void setClaimBookedPremium(List<String> claimBookedPremium) {
		this.claimBookedPremium = claimBookedPremium;
	}
	public List<String> getClaimExcRate() {
		return claimExcRate;
	}
	public void setClaimExcRate(List<String> claimExcRate) {
		this.claimExcRate = claimExcRate;
	}
	public List<String> getClaimPremOC() {
		return claimPremOC;
	}
	public void setClaimPremOC(List<String> claimPremOC) {
		this.claimPremOC = claimPremOC;
	}
	public List<String> getClaimPremDC() {
		return claimPremDC;
	}
	public void setClaimPremDC(List<String> claimPremDC) {
		this.claimPremDC = claimPremDC;
	}
	public List<Map<String, Object>> getXlPremiumList() {
		return xlPremiumList;
	}
	public void setXlPremiumList(List<Map<String, Object>> xlPremiumList) {
		this.xlPremiumList = xlPremiumList;
	}
	public String getUwYear() {
		return uwYear;
	}
	public void setUwYear(String uwYear) {
		this.uwYear = uwYear;
	}
	public String getSubProfitCenter() {
		return subProfitCenter;
	}
	public void setSubProfitCenter(String subProfitCenter) {
		this.subProfitCenter = subProfitCenter;
	}
	public String getCeaseStatus() {
		return CeaseStatus;
	}
	public void setCeaseStatus(String ceaseStatus) {
		CeaseStatus = ceaseStatus;
	}
	public String getOldClaimNumber() {
		return oldClaimNumber;
	}
	public void setOldClaimNumber(String oldClaimNumber) {
		this.oldClaimNumber = oldClaimNumber;
	}
	public String getUpdaterecordFees() {
		return updaterecordFees;
	}
	public void setUpdaterecordFees(String updaterecordFees) {
		this.updaterecordFees = updaterecordFees;
	}
	public String getUpdatesurveyorAdjesterPerOC() {
		return updatesurveyorAdjesterPerOC;
	}
	public void setUpdatesurveyorAdjesterPerOC(String updatesurveyorAdjesterPerOC) {
		this.updatesurveyorAdjesterPerOC = updatesurveyorAdjesterPerOC;
	}
	public String getUpdatesurveyorAdjesterOurShareOC() {
		return updatesurveyorAdjesterOurShareOC;
	}
	public void setUpdatesurveyorAdjesterOurShareOC(
			String updatesurveyorAdjesterOurShareOC) {
		this.updatesurveyorAdjesterOurShareOC = updatesurveyorAdjesterOurShareOC;
	}
	public String getUpdateotherProfessionalPerOc() {
		return updateotherProfessionalPerOc;
	}
	public void setUpdateotherProfessionalPerOc(String updateotherProfessionalPerOc) {
		this.updateotherProfessionalPerOc = updateotherProfessionalPerOc;
	}
	public String getUpdateprofessionalOurShareOc() {
		return updateprofessionalOurShareOc;
	}
	public void setUpdateprofessionalOurShareOc(String updateprofessionalOurShareOc) {
		this.updateprofessionalOurShareOc = updateprofessionalOurShareOc;
	}
	public String getUpdaterecordIbnr() {
		return updaterecordIbnr;
	}
	public void setUpdaterecordIbnr(String updaterecordIbnr) {
		this.updaterecordIbnr = updaterecordIbnr;
	}
	public String getUpdateibnrPerOc() {
		return updateibnrPerOc;
	}
	public void setUpdateibnrPerOc(String updateibnrPerOc) {
		this.updateibnrPerOc = updateibnrPerOc;
	}
	public String getUpdateibnrOurShareOc() {
		return updateibnrOurShareOc;
	}
	public void setUpdateibnrOurShareOc(String updateibnrOurShareOc) {
		this.updateibnrOurShareOc = updateibnrOurShareOc;
	}
	public String getUpdate_Rivised_percentage() {
		return update_Rivised_percentage;
	}
	public void setUpdate_Rivised_percentage(String updateRivisedPercentage) {
		update_Rivised_percentage = updateRivisedPercentage;
	}
	public String getRecordFees() {
		return recordFees;
	}
	public void setRecordFees(String recordFees) {
		this.recordFees = recordFees;
	}
	public String getSurveyorAdjesterPerOC() {
		return surveyorAdjesterPerOC;
	}
	public void setSurveyorAdjesterPerOC(String surveyorAdjesterPerOC) {
		this.surveyorAdjesterPerOC = surveyorAdjesterPerOC;
	}
	public String getSurveyorAdjesterOurShareOC() {
		return surveyorAdjesterOurShareOC;
	}
	public void setSurveyorAdjesterOurShareOC(String surveyorAdjesterOurShareOC) {
		this.surveyorAdjesterOurShareOC = surveyorAdjesterOurShareOC;
	}
	public String getOtherProfessionalPerOc() {
		return otherProfessionalPerOc;
	}
	public void setOtherProfessionalPerOc(String otherProfessionalPerOc) {
		this.otherProfessionalPerOc = otherProfessionalPerOc;
	}
	public String getProfessionalOurShareOc() {
		return professionalOurShareOc;
	}
	public void setProfessionalOurShareOc(String professionalOurShareOc) {
		this.professionalOurShareOc = professionalOurShareOc;
	}
	public String getRecordIbnr() {
		return recordIbnr;
	}
	public void setRecordIbnr(String recordIbnr) {
		this.recordIbnr = recordIbnr;
	}
	public String getIbnrPerOc() {
		return ibnrPerOc;
	}
	public void setIbnrPerOc(String ibnrPerOc) {
		this.ibnrPerOc = ibnrPerOc;
	}
	public String getIbnrOurShareOc() {
		return ibnrOurShareOc;
	}
	public void setIbnrOurShareOc(String ibnrOurShareOc) {
		this.ibnrOurShareOc = ibnrOurShareOc;
	}
	public String getCedentClaimNo() {
		return cedentClaimNo;
	}
	public void setCedentClaimNo(String cedentClaimNo) {
		this.cedentClaimNo = cedentClaimNo;
	}
	public String getShareSigned() {
		return shareSigned;
	}
	public void setShareSigned(String shareSigned) {
		this.shareSigned = shareSigned;
	}
	public String getMenuStatus() {
			return menuStatus;
		}
		public void setMenuStatus(String menuStatus) {
			this.menuStatus = menuStatus;
		}
	
	public String getPremiumDisplay() {
		return premiumDisplay;
	}
	public void setPremiumDisplay(String premiumDisplay) {
		this.premiumDisplay = premiumDisplay;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getClaimDisplay() {
		return claimDisplay;
	}
	public void setClaimDisplay(String claimDisplay) {
		this.claimDisplay = claimDisplay;
	}
	public String getUnderwritingYear() {
		return underwritingYear;
	}
	public void setUnderwritingYear(String underwritingYear) {
		this.underwritingYear = underwritingYear;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getUnderwriter() {
		return underwriter;
	}
	public void setUnderwriter(String underwriter) {
		this.underwriter = underwriter;
	}
	public String getOld_Contract() {
		return old_Contract;
	}
	public void setOld_Contract(String oldContract) {
		old_Contract = oldContract;
	}
	public List<Map<String, Object>> getYearList() {
		return yearList;
	}
	public void setYearList(List<Map<String, Object>> yearList) {
		this.yearList = yearList;
	}
	public List<Map<String, Object>> getProductIdList() {
		return productIdList;
	}
	public void setProductIdList(List<Map<String, Object>> productIdList) {
		this.productIdList = productIdList;
	}
	public List<ClaimBean> getPremiumList() {
		return premiumList;
	}
	public void setPremiumList(List<ClaimBean> premiumList) {
		this.premiumList = premiumList;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Map<String, Object>> getCedingCompanyList() {
		return cedingCompanyList;
	}
	public void setCedingCompanyList(List<Map<String, Object>> cedingCompanyList) {
		this.cedingCompanyList = cedingCompanyList;
	}
	public List<Map<String, Object>> getUnderWriterlist() {
		return underWriterlist;
	}
	public void setUnderWriterlist(List<Map<String, Object>> underWriterlist) {
		this.underWriterlist = underWriterlist;
	}
	public List<Map<String, Object>> getDepartIdlist() {
		return departIdlist;
	}
	public void setDepartIdlist(List<Map<String, Object>> departIdlist) {
		this.departIdlist = departIdlist;
	}
	public List<ClaimBean> getContractIdentifierList() {
		return contractIdentifierList;
	}
	public void setContractIdentifierList(List<ClaimBean> contractIdentifierList) {
		this.contractIdentifierList = contractIdentifierList;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public List<ClaimBean> getClaimlist() {
		return Claimlist;
	}
	public void setClaimlist(List<ClaimBean> claimlist) {
		Claimlist = claimlist;
	}
	public String getInception_Date() {
		return inception_Date;
	}
	public void setInception_Date(String inceptionDate) {
		inception_Date = inceptionDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPremiumclaimMode() {
		return premiumclaimMode;
	}
	public void setPremiumclaimMode(String premiumclaimMode) {
		this.premiumclaimMode = premiumclaimMode;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getSettlementStatus() {
		return settlementStatus;
	}
	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}
	public String getReinstType() {
		return reinstType;
	}
	public void setReinstType(String reinstType) {
		this.reinstType = reinstType;
	}
	public String getReinstPremiumOCOS() {
		return reinstPremiumOCOS;
	}
	public void setReinstPremiumOCOS(String reinstPremiumOCOS) {
		this.reinstPremiumOCOS = reinstPremiumOCOS;
	}
	public String getReinstPremiumDCOS() {
		return reinstPremiumDCOS;
	}
	public void setReinstPremiumDCOS(String reinstPremiumDCOS) {
		this.reinstPremiumDCOS = reinstPremiumDCOS;
	}
	public String getNetclaimAmountOCOS() {
		return netclaimAmountOCOS;
	}
	public void setNetclaimAmountOCOS(String netclaimAmountOCOS) {
		this.netclaimAmountOCOS = netclaimAmountOCOS;
	}
	public String getNetclaimAmountDCOS() {
		return netclaimAmountDCOS;
	}
	public void setNetclaimAmountDCOS(String netclaimAmountDCOS) {
		this.netclaimAmountDCOS = netclaimAmountDCOS;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getProposal_No() {
		return proposal_No;
	}
	public void setProposal_No(String proposalNo) {
		proposal_No = proposalNo;
	}
	public String getCliam_update_Date() {
		return cliam_update_Date;
	}
	public void setCliam_update_Date(String cliamUpdateDate) {
		cliam_update_Date = cliamUpdateDate;
	}
	public String getEditMode() {
		return editMode;
	}
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}
	public String getUpdate_Rivised_original_Cur() {
		return update_Rivised_original_Cur;
	}
	public void setUpdate_Rivised_original_Cur(String updateRivisedOriginalCur) {
		update_Rivised_original_Cur = updateRivisedOriginalCur;
	}
	public String getSample() {
		return sample;
	}
	public void setSample(String sample) {
		this.sample = sample;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCliamMode() {
		return cliamMode;
	}
	public void setCliamMode(String cliamMode) {
		this.cliamMode = cliamMode;
	}
	public String getMsgFlag() {
		return MsgFlag;
	}
	public void setMsgFlag(String msgFlag) {
		MsgFlag = msgFlag;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getReview_Date() {
		return review_Date;
	}
	public void setReview_Date(String reviewDate) {
		review_Date = reviewDate;
	}
	public String getReview_Done_By() {
		return review_Done_By;
	}
	public void setReview_Done_By(String reviewDoneBy) {
		review_Done_By = reviewDoneBy;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBusinessMode() {
		return BusinessMode;
	}
	public void setBusinessMode(String businessMode) {
		BusinessMode = businessMode;
	}
	public List<Map<String, Object>> getOrginalCurrency() {
		return orginalCurrency;
	}
	public void setOrginalCurrency(List<Map<String, Object>> orginalCurrency) {
		this.orginalCurrency = orginalCurrency;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getClaim_No() {
		return claim_No;
	}
	public void setClaim_No(String claimNo) {
		claim_No = claimNo;
	}
	public String getStatus_of_claim() {
		return status_of_claim;
	}
	public void setStatus_of_claim(String statusOfClaim) {
		status_of_claim = statusOfClaim;
	}
	public String getDate_of_Loss() {
		return date_of_Loss;
	}
	public void setDate_of_Loss(String dateOfLoss) {
		date_of_Loss = dateOfLoss;
	}
	public String getReport_Date() {
		return report_Date;
	}
	public void setReport_Date(String reportDate) {
		report_Date = reportDate;
	}
	public String getLoss_Details() {
		return loss_Details;
	}
	public void setLoss_Details(String lossDetails) {
		loss_Details = lossDetails;
	}
	public String getCause_of_Loss() {
		return cause_of_Loss;
	}
	public void setCause_of_Loss(String causeOfLoss) {
		cause_of_Loss = causeOfLoss;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLoss_Estimate_Orig_Curr() {
		return loss_Estimate_Orig_Curr;
	}
	public void setLoss_Estimate_Orig_Curr(String lossEstimateOrigCurr) {
		loss_Estimate_Orig_Curr = lossEstimateOrigCurr;
	}
	public String getLoss_Estimate_Our_share_Orig_Curr() {
		return loss_Estimate_Our_share_Orig_Curr;
	}
	public void setLoss_Estimate_Our_share_Orig_Curr(
			String lossEstimateOurShareOrigCurr) {
		loss_Estimate_Our_share_Orig_Curr = lossEstimateOurShareOrigCurr;
	}
	public String getExc_Rate() {
		return exc_Rate;
	}
	public void setExc_Rate(String excRate) {
		exc_Rate = excRate;
	}
	public String getLoss_Estimate_Our_Share_USD() {
		return loss_Estimate_Our_Share_USD;
	}
	public void setLoss_Estimate_Our_Share_USD(String lossEstimateOurShareUSD) {
		loss_Estimate_Our_Share_USD = lossEstimateOurShareUSD;
	}
	public String getAdvice_UW() {
		return advice_UW;
	}
	public void setAdvice_UW(String adviceUW) {
		advice_UW = adviceUW;
	}
	public String getAdvice_Mangement() {
		return advice_Mangement;
	}
	public void setAdvice_Mangement(String adviceMangement) {
		advice_Mangement = adviceMangement;
	}
	public String getRi_Recovery() {
		return ri_Recovery;
	}
	public void setRi_Recovery(String riRecovery) {
		ri_Recovery = riRecovery;
	}
	public String getRi_Recovery_Amount_USD() {
		return ri_Recovery_Amount_USD;
	}
	public void setRi_Recovery_Amount_USD(String riRecoveryAmountUSD) {
		ri_Recovery_Amount_USD = riRecoveryAmountUSD;
	}
	public String getRecovery_from() {
		return recovery_from;
	}
	public void setRecovery_from(String recoveryFrom) {
		recovery_from = recoveryFrom;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String createdBy) {
		created_by = createdBy;
	}
	public String getCreated_Date() {
		return created_Date;
	}
	public void setCreated_Date(String createdDate) {
		created_Date = createdDate;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modifiedBy) {
		modified_by = modifiedBy;
	}
	public String getModified_Date() {
		return modified_Date;
	}
	public void setModified_Date(String modifiedDate) {
		modified_Date = modifiedDate;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updatedBy) {
		updated_by = updatedBy;
	}
	public String getUpdated_Date() {
		return updated_Date;
	}
	public void setUpdated_Date(String updatedDate) {
		updated_Date = updatedDate;
	}
	public String getLayerNo() {
		return layerNo;
	}
	public void setLayerNo(String layerNo) {
		this.layerNo = layerNo;
	}
	public String getPayment_Request_No() {
		return payment_Request_No;
	}
	public void setPayment_Request_No(String paymentRequestNo) {
		payment_Request_No = paymentRequestNo;
	}
	public String getPaid_Amount_Orig_curr() {
		return paid_Amount_Orig_curr;
	}
	public void setPaid_Amount_Orig_curr(String paidAmountOrigCurr) {
		paid_Amount_Orig_curr = paidAmountOrigCurr;
	}
	public String getPaid_Amount_USD() {
		return paid_Amount_USD;
	}
	public void setPaid_Amount_USD(String paidAmountUSD) {
		paid_Amount_USD = paidAmountUSD;
	}
	public String getLoss_Estimate_Revised_Orig_Curr() {
		return loss_Estimate_Revised_Orig_Curr;
	}
	public void setLoss_Estimate_Revised_Orig_Curr(
			String lossEstimateRevisedOrigCurr) {
		loss_Estimate_Revised_Orig_Curr = lossEstimateRevisedOrigCurr;
	}
	public String getLoss_Estimate_Revised_USD() {
		return loss_Estimate_Revised_USD;
	}
	public void setLoss_Estimate_Revised_USD(String lossEstimateRevisedUSD) {
		loss_Estimate_Revised_USD = lossEstimateRevisedUSD;
	}
	public String getClaim_Note_recommendations() {
		return claim_Note_recommendations;
	}
	public void setClaim_Note_recommendations(String claimNoteRecommendations) {
		claim_Note_recommendations = claimNoteRecommendations;
	}
	public String getPayment_Reference() {
		return payment_Reference;
	}
	public void setPayment_Reference(String paymentReference) {
		payment_Reference = paymentReference;
	}
	public String getAdvice_Treasury() {
		return advice_Treasury;
	}
	public void setAdvice_Treasury(String adviceTreasury) {
		advice_Treasury = adviceTreasury;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUpdate_Reference() {
		return update_Reference;
	}
	public void setUpdate_Reference(String updateReference) {
		update_Reference = updateReference;
	}
	public String getContarctno() {
		return contarctno;
	}
	public void setContarctno(String contarctno) {
		this.contarctno = contarctno;
	}
	public String getPolicy_Contract_No() {
		return policy_Contract_No;
	}
	public void setPolicy_Contract_No(String policyContractNo) {
		policy_Contract_No = policyContractNo;
	}
	public String getCeding_Company_Code() {
		return ceding_Company_Code;
	}
	public void setCeding_Company_Code(String cedingCompanyCode) {
		ceding_Company_Code = cedingCompanyCode;
	}
	public String getCeding_company_Name() {
		return ceding_company_Name;
	}
	public void setCeding_company_Name(String cedingCompanyName) {
		ceding_company_Name = cedingCompanyName;
	}
	public String getBroker_code() {
		return broker_code;
	}
	public void setBroker_code(String brokerCode) {
		broker_code = brokerCode;
	}
	public String getBroker_Name() {
		return broker_Name;
	}
	public void setBroker_Name(String brokerName) {
		broker_Name = brokerName;
	}
	public String getOriginal_Insured() {
		return original_Insured;
	}
	public void setOriginal_Insured(String originalInsured) {
		original_Insured = originalInsured;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTreaty_Name() {
		return treaty_Name;
	}
	public void setTreaty_Name(String treatyName) {
		treaty_Name = treatyName;
	}
	public String getNature_of_Coverage() {
		return nature_of_Coverage;
	}
	public void setNature_of_Coverage(String natureOfCoverage) {
		nature_of_Coverage = natureOfCoverage;
	}
	public String getRetention() {
		return retention;
	}
	public void setRetention(String retention) {
		this.retention = retention;
	}
	public String getLimit_Orig_Curr() {
		return limit_Orig_Curr;
	}
	public void setLimit_Orig_Curr(String limitOrigCurr) {
		limit_Orig_Curr = limitOrigCurr;
	}
	public String getLimit_Our_share_USD() {
		return limit_Our_share_USD;
	}
	public void setLimit_Our_share_USD(String limitOurShareUSD) {
		limit_Our_share_USD = limitOurShareUSD;
	}
	public String getSigned_Share() {
		return signed_Share;
	}
	public void setSigned_Share(String signedShare) {
		signed_Share = signedShare;
	}
	public String getCurrecny() {
		return currecny;
	}
	public void setCurrecny(String currecny) {
		this.currecny = currecny;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAdvice_uw_email() {
		return advice_uw_email;
	}
	public void setAdvice_uw_email(String adviceUwEmail) {
		advice_uw_email = adviceUwEmail;
	}
	public String getAdvice_management_email() {
		return advice_management_email;
	}
	public void setAdvice_management_email(String adviceManagementEmail) {
		advice_management_email = adviceManagementEmail;
	}
	public String getSumOfPaidAmountOC() {
		return sumOfPaidAmountOC;
	}
	public void setSumOfPaidAmountOC(String sumOfPaidAmountOC) {
		this.sumOfPaidAmountOC = sumOfPaidAmountOC;
	}
	public String getRisk_Code() {
		return risk_Code;
	}
	public void setRisk_Code(String riskCode) {
		risk_Code = riskCode;
	}
	public String getAccumulation_Code() {
		return accumulation_Code;
	}
	public void setAccumulation_Code(String accumulationCode) {
		accumulation_Code = accumulationCode;
	}
	public String getEvent_Code() {
		return event_Code;
	}
	public void setEvent_Code(String eventCode) {
		event_Code = eventCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getClaimPaymentNo() {
		return claimPaymentNo;
	}
	public void setClaimPaymentNo(String claimPaymentNo) {
		this.claimPaymentNo = claimPaymentNo;
	}
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String sNo) {
		SNo = sNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getAdviceTreasuryEmail() {
		return adviceTreasuryEmail;
	}
	public void setAdviceTreasuryEmail(String adviceTreasuryEmail) {
		this.adviceTreasuryEmail = adviceTreasuryEmail;
	}
	public String getRevSumOfPaidAmt() {
		return revSumOfPaidAmt;
	}
	public void setRevSumOfPaidAmt(String revSumOfPaidAmt) {
		this.revSumOfPaidAmt = revSumOfPaidAmt;
	}
	public String getReverseClaimYN() {
		return reverseClaimYN;
	}
	public void setReverseClaimYN(String reverseClaimYN) {
		this.reverseClaimYN = reverseClaimYN;
	}
	public String getCloseClaimYN() {
		return closeClaimYN;
	}
	public void setCloseClaimYN(String closeClaimYN) {
		this.closeClaimYN = closeClaimYN;
	}
	public String getSumInsOSOC() {
		return sumInsOSOC;
	}
	public void setSumInsOSOC(String sumInsOSOC) {
		this.sumInsOSOC = sumInsOSOC;
	}
	public String getSumInsOSDC() {
		return sumInsOSDC;
	}
	public void setSumInsOSDC(String sumInsOSDC) {
		this.sumInsOSDC = sumInsOSDC;
	}
	public String getCashLossOSOC() {
		return cashLossOSOC;
	}
	public void setCashLossOSOC(String cashLossOSOC) {
		this.cashLossOSOC = cashLossOSOC;
	}
	public String getCashLossOSDC() {
		return cashLossOSDC;
	}
	public void setCashLossOSDC(String cashLossOSDC) {
		this.cashLossOSDC = cashLossOSDC;
	}
	public String getOsAmt() {
		return osAmt;
	}
	public void setOsAmt(String osAmt) {
		this.osAmt = osAmt;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOpstartDate() {
		return opstartDate;
	}
	public void setOpstartDate(String opstartDate) {
		this.opstartDate = opstartDate;
	}
	public String getOpendDate() {
		return opendDate;
	}
	public void setOpendDate(String opendDate) {
		this.opendDate = opendDate;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Map<String, Object>> getPredepartIdlist() {
		return predepartIdlist;
	}
	public void setPredepartIdlist(List<Map<String, Object>> predepartIdlist) {
		this.predepartIdlist = predepartIdlist;
	}
	/**
	 * @return the paid_claim_os
	 */
	public String getPaid_claim_os() {
		return paid_claim_os;
	}
	/**
	 * @param paidClaimOs the paid_claim_os to set
	 */
	public void setPaid_claim_os(String paidClaimOs) {
		paid_claim_os = paidClaimOs;
	}
	/**
	 * @return the surveyor_fee_os
	 */
	public String getSurveyor_fee_os() {
		return surveyor_fee_os;
	}
	/**
	 * @param surveyorFeeOs the surveyor_fee_os to set
	 */
	public void setSurveyor_fee_os(String surveyorFeeOs) {
		surveyor_fee_os = surveyorFeeOs;
	}
	/**
	 * @return the other_prof_fee_os
	 */
	public String getOther_prof_fee_os() {
		return other_prof_fee_os;
	}
	/**
	 * @param otherProfFeeOs the other_prof_fee_os to set
	 */
	public void setOther_prof_fee_os(String otherProfFeeOs) {
		other_prof_fee_os = otherProfFeeOs;
	}
	/**
	 * @return the total_claims_paid_os
	 */
	public String getTotal_claims_paid_os() {
		return total_claims_paid_os;
	}
	/**
	 * @param totalClaimsPaidOs the total_claims_paid_os to set
	 */
	public void setTotal_claims_paid_os(String totalClaimsPaidOs) {
		total_claims_paid_os = totalClaimsPaidOs;
	}
	public void setReopen_Date(String reopen_Date) {
		this.reopen_Date = reopen_Date;
	}
	public String getReopen_Date() {
		return reopen_Date;
	}
	public void setProposalType(String proposalType) {
		this.proposalType = proposalType;
	}
	public String getProposalType() {
		return proposalType;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
	public String getBasis() {
		return basis;
	}
	public String getLero2a() {
		return lero2a;
	}
	public void setLero2a(String lero2a) {
		this.lero2a = lero2a;
	}
	public String getLero2b() {
		return lero2b;
	}
	public void setLero2b(String lero2b) {
		this.lero2b = lero2b;
	}
	public String getLero2c() {
		return lero2c;
	}
	public void setLero2c(String lero2c) {
		this.lero2c = lero2c;
	}
	public String getSaf3a() {
		return saf3a;
	}
	public void setSaf3a(String saf3a) {
		this.saf3a = saf3a;
	}
	public String getSaf3b() {
		return saf3b;
	}
	public void setSaf3b(String saf3b) {
		this.saf3b = saf3b;
	}
	public String getSaf3c() {
		return saf3c;
	}
	public void setSaf3c(String saf3c) {
		this.saf3c = saf3c;
	}
	public String getOfos4a() {
		return ofos4a;
	}
	public void setOfos4a(String ofos4a) {
		this.ofos4a = ofos4a;
	}
	public String getOfos4b() {
		return ofos4b;
	}
	public void setOfos4b(String ofos4b) {
		this.ofos4b = ofos4b;
	}
	public String getOfos4c() {
		return ofos4c;
	}
	public void setOfos4c(String ofos4c) {
		this.ofos4c = ofos4c;
	}
	public String getTotala() {
		return totala;
	}
	public void setTotala(String totala) {
		this.totala = totala;
	}
	public String getTotalb() {
		return totalb;
	}
	public void setTotalb(String totalb) {
		this.totalb = totalb;
	}
	public String getTotalc() {
		return totalc;
	}
	public void setTotalc(String totalc) {
		this.totalc = totalc;
	}
	public String getReInspremiumOS() {
		return reInspremiumOS;
	}
	public void setReInspremiumOS(String reInspremiumOS) {
		this.reInspremiumOS = reInspremiumOS;
	}

	public void setCibnr100Oc(String cibnr100Oc) {
		this.cibnr100Oc = cibnr100Oc;
	}
	public String getCibnr100Oc() {
		return cibnr100Oc;
	}
	public String getTotalReserveOSOC() {
		return totalReserveOSOC;
	}
	public void setTotalReserveOSOC(String totalReserveOSOC) {
		this.totalReserveOSOC = totalReserveOSOC;
	}
	public String getTotalReserveOSDC() {
		return totalReserveOSDC;
	}
	public void setTotalReserveOSDC(String totalReserveOSDC) {
		this.totalReserveOSDC = totalReserveOSDC;
	}
	public String getGrossLossFGU() {
		return grossLossFGU;
	}
	public void setGrossLossFGU(String grossLossFGU) {
		this.grossLossFGU = grossLossFGU;
	}
	public String getPreReopenDate() {
		return preReopenDate;
	}
	public void setPreReopenDate(String preReopenDate) {
		this.preReopenDate = preReopenDate;
	}
	public String getTotalORpaidAmount() {
		return totalORpaidAmount;
	}
	public void setTotalORpaidAmount(String totalORpaidAmount) {
		this.totalORpaidAmount = totalORpaidAmount;
	}
	public String getTotalSApaidAmount() {
		return totalSApaidAmount;
	}
	public void setTotalSApaidAmount(String totalSApaidAmount) {
		this.totalSApaidAmount = totalSApaidAmount;
	}
	public String getTotalOPpaidAmount() {
		return totalOPpaidAmount;
	}
	public void setTotalOPpaidAmount(String totalOPpaidAmount) {
		this.totalOPpaidAmount = totalOPpaidAmount;
	}
	public String getTotalTORpaidAmount() {
		return totalTORpaidAmount;
	}
	public void setTotalTORpaidAmount(String totalTORpaidAmount) {
		this.totalTORpaidAmount = totalTORpaidAmount;
	}
	
	public List<Map<String, Object>> getSubProfit_centerlist() {
		return subProfit_centerlist;
	}
	public void setSubProfit_centerlist(
			List<Map<String, Object>> subProfitCenterlist) {
		subProfit_centerlist = subProfitCenterlist;
	}
	public String getSubProfitId() {
		return subProfitId;
	}
	public void setSubProfitId(String subProfitId) {
		this.subProfitId = subProfitId;
	}
	public String getReservePositionDate() {
		return reservePositionDate;
	}
	public void setReservePositionDate(String reservePositionDate) {
		this.reservePositionDate = reservePositionDate;
	}
	public String getReserveCount() {
		return reserveCount;
	}
	public void setReserveCount(String reserveCount) {
		this.reserveCount = reserveCount;
	}
	public String getClaim_closed_Date() {
		return claim_closed_Date;
	}
	public void setClaim_closed_Date(String claimClosedDate) {
		claim_closed_Date = claimClosedDate;
	}
	public String getConsubProfitId() {
		return consubProfitId;
	}
	public void setConsubProfitId(String consubProfitId) {
		this.consubProfitId = consubProfitId;
	}
	public String getCoverLimitAmount() {
		return coverLimitAmount;
	}
	public void setCoverLimitAmount(String coverLimitAmount) {
		this.coverLimitAmount = coverLimitAmount;
	}
	public String getClaimdepartId() {
		return claimdepartId;
	}
	public void setClaimdepartId(String claimdepartId) {
		this.claimdepartId = claimdepartId;
	}
	public List<Map<String, Object>> getPaymentNoList() {
		return paymentNoList;
	}
	public void setPaymentNoList(List<Map<String, Object>> paymentNoList) {
		this.paymentNoList = paymentNoList;
	}
	public List<Map<String, Object>> getClaimView() {
		return claimView;
	}
	public void setClaimView(List<Map<String, Object>> claimView) {
		this.claimView = claimView;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public void setMultiPaymentNoList(List<Map<String,Object>> multiPaymentNoList) {
		this.multiPaymentNoList = multiPaymentNoList;
	}
	public List<Map<String,Object>> getMultiPaymentNoList() {
		return multiPaymentNoList;
	}

	

}
