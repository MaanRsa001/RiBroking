package com.maan.common.db;


public interface DBConstants {

	public static final String DB_QUERY_PROPERTY_FILE = "com.maan.struts.DbQuery";

	//Common Query's
	public static final String COMMON_SELECT_GETDEPTNAME="common.select.getDeptName";
	public static final String COMMON_SELECT_GETCOMPNAME="common.select.getCompName";
	//public static final String COMMON_SELECT_MAXCONTNO="common.select.maxContNo";
	//public static final String COMMON_UPDATE_CONTNO="common.update.contNo";
	public static final String COMMON_UPDATE_RISKDETCONTNO="common.update.riskDetContNo";
	public static final String COMMON_UPDATE_POSMASCONTNO="common.update.posMasDetContNo";
	public static final String COMMON_SELECT_GETCONSTDET="common.select.getConstDet";
	public static final String COMMON_SELECT_GETCONSTDETIE="common.select.getConstDetie";
	public static final String COMMON_SELECT_GETCONSTDETIE1="common.select.getConstDetie1";
	public static final String COMMON_SELECT_GETCOUNTRYLIST="common.select.getCountryList";
	public static final String COMMON_SELECT_GETPROFITCENTERLIST="common.select.getProfitCenterList";
	public static final String COMMON_SELECT_GETPROFITCENTERLISTIE="common.select.getProfitCenterListIE";
	public static final String COMMON_SELECT_GETSUBPROFITCENTERLIST="common.select.getSubProfitCenterList";
	public static final String COMMON_SELECT_GETSUBPROFITCENTERLISTIE="common.select.getSubProfitCenterListIE";
	public static final String COMMON_SELECT_GETUWList="common.select.getUWList";
	public static final String COMMON_SELECT_DEPARTMENTLIST="common.select.getDepartmentList";
	public static final String COMMON_SELECT_DEPARTMENTLIST2="common.select.getDepartmentList2";
	public static final String COMMON_SELECT_DEPARTMENTLIST3 = "common.select.getDepartmentList3";
	public static final String COMMON_SELECT_DEPARTMENTLIST1="common.select.getDepartmentList1";
	public static final String COMMON_SELECT_DEPARTMENTLIST_PRECLAIM="common.select.getDepartmentList.preClaim";
	public static final String COMMON_SELECT_POLICYBRANCHLIST="common.select.getPolicyBranchList";
	public static final String COMMON_SELECT_PERSONALINFOLIST="common.select.getPersonalInfoList";
	public static final String COMMON_SELECT_DEPTNAME="common.select.deptName";
	public static final String COMMON_SELECT_GETCURRENCYLIST="common.select.getCurrencyList";
	public static final String COMMON_SELECT_GETTERRITORYLIST="common.select.getTerritoryList";
	public static final String COMMON_SELECT_GETRISKGRADELIST="common.select.getRiskGradeList";
	public static final String COMMON_SELECT_GETCATEGORYZONELIST="common.select.getCategoryZoneList";
	public static final String COMMON_SELECT_BANKMASTERLIST="common.select.getBankMasterList";
	public static final String COMMON_SELECT_GETPRODUCTLIST="common.select.getProductList";
	public static final String COMMON_SELECT_GETSYSDATE="common.select.getsysDate";
	public static final String COMMON_SELECT_GETUWLIMIT="common.select.getUWLimit";
	public static final String COMMON_SELECT_LOGINIDLIST="common.select.getLoginIdList";
	public static final String COMMON_SELECT_MODEOFTRANSPORT="common.select.modeOfTransport";
	public static final String COMMON_SELECT_STARTDTOFMONTH="common.select.getStartDTOfMonth";
	public static final String COMMON_SELECT_EXRATE="common.select.getExRate";
	public static final String COMMON_SELECT_SEQNAME="common.select.getSeqName";
	public static final String COMMON_SELECT_POLICYNO1="common.select.getPolicyNo1";
	public static final String COMMON_SELECT_POLICYNO2="common.select.getPolicyNo2";
	public static final String COMMON_SELECT_POLICYNO="common.select.policyNo";
	public static final String COMMON_UPDATE_POLICYNO="common.update.policyNo";
	public static final String COMMON_SELECT_GETPRCLCOUNT="common.select.getPRCLCount";
	public static final String COMMON_SELECT_GETUWLIMIT1="common.select.getUWLimit1";
	public static final String COMMON_SELECT_GETIEPRODUCTLIST="common.select.getPRoductListIE";
	public static final String COMMON_SELECT_GETDEPRTIELISTIE="common.select.getDepartListIE";
	
	//FACULTATIVE Query's
	public static final String FAC_SP_FACULTATIVEPAGE1="fac.sp.facultativepage1";
	//public static final String FAC_SELECT_RENEWALSTATUS="fac.select.renewalStatus";
	//public static final String FAC_SELECT_MAXPRONO="fac.select.maxProNo";
	//public static final String FAC_SELECT_GETDISTRICTS="fac.select.getDistricts";
	public static final String FAC_SP_FACULTATIVEPAGE2="fac.sp.facultativepage2";
	public static final String FAC_SELECT_CONTGEN="fac.select.contGen";
	public static final String FAC_INSERT_INSDETAILS="fac.insert.insDetails";
	public static final String FAC_UPD_INSDETAILS="fac.upddate.updDetails";
	public static final String FAC_UPDATE_INSDETAILS="fac.update.insDetails";
	public static final String FAC_SELECT_FIRST_PAGE_DET="fac.select.firstPageDet";
	public static final String FAC_SELECT_SECOND_PAGE_DET="fac.select.secondPageDet";
	public static final String FAC_SELECT_VIEW_FIRST_PAGE="fac.select.viewfirstPage";
	public static final String FAC_SELECT_VIEW_SECOND_PAGE="fac.select.viewsecondPage";
	public static final String FAC_SELECT_INSDETAILS="fac.select.insDetails";
	public static final String FAC_SELECT_VIEWINSDETAILS="fac.select.viewInsDetails";
	public static final String FAC_SELECT_SHOW_SECOND_DATA="fac.select.showSecondData";
	public static final String FAC_SELECT_UWYEAR="fac.select.uwYear";
	public static final String FAC_SELECT_RETROCONTDET="fac.select.retroContDet";
	public static final String FAC_UPDATE_FAC_CONTRACT_NO="fac.update.fac.contractNo";
	public static final String FAC_SP_RENEWALCOPYQUOTE="fac.sp.renewalCopyQuote";
	public static final String FAC_SP_EXCHAGERETROPREMIUM="fac.sp.exchageRetroPremium";
	public static final String FAC_SELECT_RENEWALVALIDATION="fac.select.getRenewalValidation";
	public static final String PRO_SELECT_RENEWALVALIDATION="pro.select.getRenewalValidation";

	public static final String FAC_SELECT_SPRETROVALIDATION="fac.select.getSPRetroValidation";

	//Premium Query's
	public static final String PREMIUM_SELECT_SUMOFPAIDPREMIUM="premium.select.sumOfPaidPremium";
	public static final String PREMIUM_SELECT_CURRENCY="premium.select.currency";
	public static final String PREMIUM_SELECT_CURRENCY_SHORT="premium.select.currency.short";
	public static final String PREMIUM_SELECT_CURRENCY_NAME="premium.select.currecy.name";
	public static final String PREMIUM_INSERT_FACPREMIUM="premium.insert.facpremium";
	public static final String PREMIUM_INSERT_TREATYPREMIUM="premium.insert.treatypremium";
	public static final String PREMIUM_INSERT_XOLPREMIUM="premium.insert.xolpremium";
	public static final String PREMIUM_INSERT_RETROXOLPREMIUM="premium.insert.retoxolpremium";
	public static final String PREMIUM_SELECT_FACCONTDET="premium.select.facContDet";
	public static final String PREMIUM_SELECT_TREATYCONTDET1="premium.select.treatyContDet1";
	public static final String PREMIUM_SELECT_TREATYCONTDET2="premium.select.treatyContDet2";
	public static final String PREMIUM_SELECT_TREATYCONTDET3="premium.select.treatyContDet3";
	public static final String PREMIUM_SELECT_XOLLAYERNO1="premium.select.xolLayerNo1";
	public static final String PREMIUM_SELECT_XOLLAYERNO2="premium.select.xolLayerNo2";
	public static final String PREMIUM_SELECT_COMMISSIONDETAILS="premium.select.commissionDetails";
	public static final String PREMIUM_SELECT_FACPROPOSALDETAILS="premium.select.facProposalDetails";
	public static final String PREMIUM_SELECT_TREATYPROPOSALDETAILS="premium.select.treatyProposalDetails";
	public static final String PREMIUM_SELECT_TREATYXOLPROPOSALDETAILS="premium.select.treatyXOLfacProposalDetails";
	public static final String PREMIUM_SELECT_CURRENCYSEL="premium.select.currencySel";
	//public static final String PREMIUM_UPDATE_UPDATETRAN="premium.update.updateTran";
	public static final String PREMIUM_UPDATE_FACUPATEPRE="premium.update.facUpdatePre";
	public static final String PREMIUM_UPDATE_TREATYUPDATEPRE="premium.update.treatyUpdatePre";
	public static final String PREMIUM_UPDATE_XOLUPDATEPRE="premium.update.xolUpdatePre";
	public static final String PREMIUM_UPDATE_RETROXOLUPDATEPRE="premium.update.retroxolUpdatePre";
	public static final String PREMIUM_SELECT_FACPREMIVIEW="premium.select.facPremiumView";
	public static final String PREMIUM_SELECT_TREATYPREMIUMVIEW="premium.select.treatyPremiumView";
	public static final String PREMIUM_SELECT_XOLPREMIUMVIEW="premium.select.XolPremiumView";
	public static final String PREMIUM_SELECT_RETROXOLPREMIUMVIEW="premium.select.retroXolPremiumView";
	public static final String PREMIUM_SELECT_PREMIUMEDLIST1="premium.select.PremiumedList1";
	public static final String PREMIUM_SELECT_RETROPREMIUMEDLIST1="premium.select.retroPremiumedList1";
	public static final String PREMIUM_SELECT_PREMIUMEDLIST2="premium.select.PremiumedList2";
	public static final String PREMIUM_SELECT_XOLLAYER1="premium.select.xolLayer1";
	public static final String PREMIUM_SELECT_XOLLAYER2="premium.select.xolLayer2";
	public static final String PREMIUM_SELECT_XOLLAYER3="premium.select.xolLayer3";
	public static final String PREMIUM_SELECT_PREMIUMEDLIST3="premium.select.PremiumedList3";
	public static final String PREMIUM_SELECT_FACPREMIUMEDIT="premium.select.facPremiumEdit";
	public static final String PREMIUM_SELECT_TREETYXOLPREMIUMEDIT="premium.select.treetyXOLPremiumEdit";
	public static final String PREMIUM_SELECT_RETROTREETYXOLPREMIUMEDIT="premium.select.retrotreetyXOLPremiumEdit";
	public static final String PREMIUM_SELECT_XOLPRELIST="premium.select.xolPreList";
	public static final String PREMIUM_SELECT_FACTREATYPRELIST="premium.select.facTreatyPreList";
	public static final String PREMIUM_SELECT_MDINSTALMENTLIST="premium.select.mdInstalmentList";
	public static final String PREMIUM_SELECT_INSTALMENTAMOUNTQUERY="premium.select.InstalmentAmountQuery";
	//public static final String PREMIUM_SELECT_MAXTRANSATION="premium.select.maxTransationNo";
	public static final String PREMIUM_SELECT_MDINSTALLMENTS="premium.select.mdInstallments";
	public static final String PREMIUM_SELECT_MNDPREMIUMOC="premium.select.mndPremiumOC";
	public static final String PREMIUM_SELECT_RPPREMIUMOC="premium.select.RPPremiumOC";
	public static final String PREMIUM_SELECT_GETFACSPRETRO="premium.select.getFacSPRetro";
	public static final String PREMIUM_SELECT_GETTREATYSPRETRO="premium.select.getTreatySPRetro";
	public static final String PREMIUM_SELECT_GETXOLSPRETRO="premium.select.geXOLSPRetro";
	public static final String PREMIUM_SELECT_INSDETAILS="premium.select.insDetails";
	public static final String PREMIUM_SELECT_GETNORETROCESS="premium.select.getNoRetroCess";
	public static final String PREMIUM_SELECT_GETXOLNORETROCESS="premium.select.getXOLNoRetroCess";
	public static final String PREMIUM_SELECT_GETSUMOFSHARESIGN="premium.select.getSumOfShareSign";
	public static final String PREMIUM_SELECT_GETXOLSUMOFSHARESIGN="premium.select.getXOLSumOfShareSign";
	public static final String PREMIUM_DETAIL_ARCHIVE="premium.detail.archive";
	public static final String PREMIUM_SP_RETROSPLIT="premium.sp.retroSplit";
	public static final String PREMIUM_SELECT_GETOSCLAIMLOSSUPDATE="premium.select.getOsClaimLossUpdate";
	public static final String PREMIUM_SELECT_GETOSCLAIMLOSSUPDATENEW="premium.select.getOsClaimLossUpdateNew";
	public static final String PREMIUM_SELECT_GETOSCLAIMLOSSUPDATEEDIT="premium.select.getOsClaimLossUpdateEdit";
	public static final String PREMIUM_SELECT_GETCLAIMNODROPDOWN="premium.select.getClaimNoDropdown";
	public static final String PREMIUM_INSERT_CASHLOSSCREDITUPDATE="premium.insert.cashLossCreditUpdate";
	public static final String PREMIUM_SELECT_CASHLOSSCREDITUPDATE="premium.select.cashLossCreditUpdate";
	public static final String PREMIUM_SELECT_SUMOFPAIDCLAIM="premium.select.sumOfPaidClaim";
	public static final String PREMIUM_SELECT_SUMOFCASHLOSSUPDATE="premium.select.sumOfCashLossUpdate";
	public static final String PREMIUM_MOV_REP_MAX_DATE="premium.mov.rep.max.date";

	//Login
	public static final String LOGIN_SELECT_MAKEAUTHENDICATION="login.select.makeAuthendication";
	public static final String LOGIN_SELECT_INSERTSESSIONINFO="login.select.insertSessionInfo";
	public static final String LOGIN_INSERT_SESSIONMASS="login.insert.sessionMas";
	public static final String LOGIN_UPDATE_UPDATESESSIONINFO="login.update.updateSessionInfo";

	//UserManipulation
	public static final String USER_GETHOMEPAGEMAP="user.getHomePageMap";
	public static final String USER_PRODUCTMAP1="user.getProductMap1";
	public static final String USER_PRODUCTMAP2="user.getProductMap2";
	public static final String USER_PRODUCTMAP3="user.getProductMap3";
	public static final String USER_GETHOMEPAGEPRODIDS="user.getHomePageProdIDs";

	//Policy
	public static final String POLICY_SELECT_BRANCHINSERT="policy.select.branchInsert";
	public static final String POLICY_SELECT_BRANCHINSERT1="policy.select.branchInsert1";
	public static final String POLICY_SELECT_MODE="policy.select.mode";
	public static final String POLICY_INSERT_GETINSERTQUERY="policy.insert.getInsertQuery";
	public static final String POLICY_SELECT_MAXPID="policy.select.maxPid";
	public static final String POLICY_SELECT_GETEDITPOLICYBRANCHQUERY="policy.select.getEditPolicyBranchQuery";
	public static final String POLICY_SELECT_CHECKCOUNT="policy.select.checkCount";
	public static final String POLICY_UPDATE_GETUPDATEPOLICY="policy.update.getUpdatePolicy";
	public static final String POLICY_SELECT_CORECHECKINSERT="policy.select.coreCheckInsert";
	public static final String POLICY_SELECT_CORECHECKUPDATE="policy.select.coreCheckUpdate";
	public static final String POLICY_INSERT_VALIDATE="policy.insert.validate";
	public static final String POLICY_EDIT_VALIDATE="policy.edit.validate";
	//Clime Query's
	public static final String CLAIM_SELECT_SUMPAIDAMT="claim.select.sumPaidAmt";
	public static final String CLAIM_SELECT_REVSUMPAIDAMT="claim.select.revSumPaidAmt";
	public static final String CLAIM_SELECT_SUMPAIDAMT1="claim.select.sumPaidAmt1";
	public static final String CLAIM_SELECT_REVSUMPAIDAMT1="claim.select.revSumPaidAmt1";
	public static final String CLAIM_SELECT_LOSSESTIMATEREVISEDOC="claim.select.lossEstimateRevisedOc";
	public static final String CLAIM_SELECT_FACGETCLIAMQUERY="claim.select.facGetCliamQuery";
	public static final String CLAIM_SELECT_XOLORTREATYGETCLIMEQUERY="claim.select.xolOrTeatyGetClimeQuery";
	public static final String CLAIM_SELECT_CLAIMEDIT="claim.select.claimEdit";
	public static final String CLAIM_SELECT_GETCLAIMRESERVELIST="claim.select.getClaimReserveList";
	public static final String CLAIM_SELECT_GETCLAIMUPDATELIST="claim.select.getClaimUpdateList";
	public static final String CLAIM_SELECT_GETCLAIMREVIEWQUERY="claim.select.getClaimReviewQuery";
	//public static final String CLAIM_UPDATE_CLAIMNO="claim.update.claimNo";
	//public static final String CLAIM_SELECT_CLAIMNO="claim.select.claimNo";
	//public static final String CLAIM_UPDATE_CLAIMPAYMENTNO="claim.update.claimPaymentNO";
	//public static final String CLAIM_SELECT_CLAIMEPAYMENTNO="claim.select.climePaymentNo";
	public static final String CLAIM_INSERT_PAYMENT="claim.insert.payment";
	public static final String CLAIM_INSERT_GETRESERVEQUERY="claim.insert.getReserveQuery";
	public static final String CLAIM_UPDATE_TOTALAMTPAIDTILLDATE="claim.update.totalAmtPaidTillDate";
	public static final String CLAIM_UPDATE_EXCHANGE_RATE="claim.update.exchange_rate";
	public static final String CLAIM_INSERT_GETUPDATIONQUERY="claim.insert.getUpdationQuery";
	//public static final String CLAIM_UPDATE_TTRNCLAIMDETAILS="claim.update.ttrnClaimDetails";
	public static final String Claim_SP_RETROSPLIT="claim.retro.split";
	public static final String ClaimUpdation_SP_RETROSPLIT="claim.update.retro.split";
	public static final String CLAIM_UPDATE_TTRNCLAIMDETAILSRDANDRDB="claim.update.ttrnClaimDetailsRDandrdb";
	public static final String CLAIM_SELECT_MAXSNO="claim.select.maxSno";
	public static final String CLAIM_INSERT_TTRNCLAIMREVIEW="claim.insert.ttrnClaimReview";
	public static final String CLAIM_SELECT_PAYMENTREQNO="claim.select.paymentReqNo";
	public static final String CLAIM_SELECT_MAXSNODTB1="claim.select.maxSnoDTB1";
	public static final String CLAIM_SELECT_MAXSNODTB2="claim.select.maxSnoDTB2";
	public static final String CLAIM_SELECT_LOSSESTIMATEOSOC="claim.select.lossEstimateOsOc";
	public static final String CLAIM_SELECT_SUMPAIDAMTOC="claim.select.sumPaidAmtOc";
	public static final String CLIAM_SELECT_TOTALAMTPAIDTILLDATE="claim.select.totalAmtPaidTillDate";
	//public static final String CLAIM_SELECT_LOSSESTIMATE="claim.select.lossEstimate";
	public static final String CLAIM_SELECT_CLAIMTABLELIST="claim.select.claimTableList";
	public static final String CLAIM_SELECT_GETCLAIMUPDATELIST2="claim.select.getClaimUpdateList";
	public static final String CLIME_SELECT_GETCLAIMRESERVELIST="clime.select.getClaimRESERVEList";
	public static final String CLAIM_SELECT_GETCLAIMLIST="claim.select.getClaimList";
	public static final String CLAIM_SELECT_GETCLAIMRESERVELIST2="claim.select.getClaimReserveList";
	public static final String CLAIM_INSERT_CLIAMDETAILS="claim.insert.cliamDetails";
	public static final String CLAIM_SELECT_GETCLAIMPAYMENTLIST="claim.select.getClaimPaymentList";
	public static final String CLAIM_SELECT_GETCLAIMREVIEWLIST="claim.select.getClaimReviewList";
	public static final String CLAIM_SELECT_LOSSESTREV="claim.select.lossEstRev";
	//public static final String CLAIM_INSERT_GETUPDATIONQUERY="claim.insert.getUpdationQuery";
	public static final String CLAIM_UPDATE_CLIAMDETAILSUPDATE="claim.update.cliamDetailsUpdate";
	public static final String CLAIM_SELECT_MAXRESVID="claim.select.maxResvId";
	public static final String CLAIM_SELECT_EXCRATE="claim.select.excRate";
	public static final String CLAIM_UPDATE_CLOSECLAIM="claim.update.closeClaim";
	public static final String CLAIM_SELECT_CLAIMSTATUS="claim.select.claimstatus";
	public static final String CLAIM_SELECT_GETINSDATE="claim.select.getInsDate";
	public static final String CLAIM_SELECT_GETLOSSDATE="claim.select.getLossDate";
	public static final String CLAIM_SELECT_GETCONTINSDATE="claim.select.getContInsDate";
	public static final String CLAIM_SELECT_GETLASTPAYMENTDT="claim.select.getLastPayDt";

	//SubProfitCenter Query's
	public static final String SUBPROFIT_SELECT_TMASSPFCNAME="subprofit.select.tmasSpfcName";
	public static final String SUBPROFIT_SELECT_TMASSPFCNAMEID="subprofit.select.tmasSpfcNameId";
	public static final String SUBPROFIT_SELECT_GETEDITQUERY="subprofit.select.GetEditQuery";
	public static final String SUBPROFIT_INSERT_INSERTQUERY="subprofit.insert.insertQuery";
	public static final String SUBPROFIT_UPDATE_GETUPDATEDEPARTMENT="subprofit.update.GetUpdateDepartment";
	public static final String SUBPROFIT_SELECT_GETLISTSUBPROFITCENTER="subprofit.select.GetListSubProfitCenter";
	public static final String SUBPROFIT_SELECT_GETSEARCHLISTSUBPROFITCENTER="subprofit.select.GetSearchListSubProfitCenter";
	public static final String SUBPROFIT_SELECT_TMASSPFCALLID="subprofit.select.tmasSpfcAllId";
	public static final String SUBPROFIT_SELECT_CORECHECKUPDATE="subprofit.select.coreCheckUpdate";
	public static final String SUBPROFIT_SELECT_CORECHECKINSERT="subprofit.select.coreCheckInsert";
	public static final String SUBPROFIT_INSERT_VALIDATIONQUERY="subprofit.insert.validationquery";
	public static final String SUBPROFIT_INSERT_VALIDATIONQUERY1="subprofit.insert.validationquery1";
	public static final String SUBPROFIT_EDIT_VALIDATIONQUERY="subprofit.edit.validationquery";

	//ProfitCenter Query's
	public static final String PROFIT_SELECT_TMASPFCNAME="profit.select.tmasPfcName";
	public static final String PROFIT_SELECT_TMASPFCNAMEUPD="profit.select.tmasPfcNameupd";
	public static final String PROFIT_INSERT_INSERTQUERY="profit.insert.insertQuery";
	public static final String PROFIT_SELECT_TMASPFCNAMECHECK="profit.select.tmasPfcNameCheck";
	public static final String PROFIT_SELECT_TMASPFCNAMEID="profit.select.tmasPfcNameId";
	public static final String PROFIT_SELECT_GETEDITPROFITCENTER="profit.select.getEditProfitCenter";
	public static final String PROFIT_UPDATE_UPDATEQUERY="profit.update.updateQuery";
	public static final String PROFIT_SELECT_GETLISTPROFITCENTER="profit.select.getListProfitCenter";

	//CeddinCompanyBusiness Query's
	public static final String CEDDINGCOMPANY_SELECT_GETVIEWEXIST="ceddingcompany.select.getViewExist";

	//CeddingCompanyDao Query's
	public static final String CEDDINGCOMPANYDAO_INSERT_INSERTCEDDINGQUERY="ceddingcompanydao.insert.insertCeddingQuery";
	public static final String CEDDINGCOMPANYDAO_UPDATE_GETSEQUENCEUPDATE="ceddingcompanydao.update.getSequenceUpdate";
	public static final String CEDDINGCOMPANYDAO_SELECT_GETSEARCHQUERYTRUE="ceddingcompanydao.select.getSearchQueryTrue";
	public static final String CEDDINGCOMPANYDAO_SELECT_GETSEARCHQUERYFALSE="ceddingcompanydao.select.getSearchQueryFalse";
	public static final String CEDDINGCOMPANYDAO_UPDATE_UPDATEQUERY="ceddingcompanydao.update.updateQuery";
	public static final String CEDDINGCOMPANYDAO_SELECT_GETVIEWEXIST="ceddingcompanydao.select.getViewExist";

	//CeddingCompanyAction Query's
	//public static final String CEDDINGCOMPANYACTION_SELECT_CUSTOMERID="ceddingcompanyaction.select.customerId";
	//CeddingCompanyArgument Query's
	public static final String CEDDINGCOMPANYARGUMENT_SELECT_CUSTOMERID="ceddingcompanyargument.select.customerId";

	//Reports Query's
	public static final String REPORTDAO_SELECT_FACGETQUOTEREGISTERLIST="reportdao.select.facgetQuoteRegisterList";
	public static final String REPORTDAO_SELECT_FACORDER="reportdao.select.facOrder";
	public static final String REPORTDAO_SELECT_FACGETQUOTEREGISTERLOGINID="reportdao.select.facgetQuoteRegisterLoginId";
	public static final String REPORTDAO_SELECT_FACGETQUOTEREGISTERCEDINGID="reportdao.select.facgetQuoteRegisterCedingId";
	public static final String REPORTDAO_SELECT_FACGETQUOTEREGISTERBROKERID="reportdao.select.facgetQuoteRegisterBrokerId";
	public static final String REPORTDAO_SELECT_XOLORTREATYGETQUOTEREGISTERLIST="reportdao.select.xolOrTreatygetQuoteRegisterList";
	public static final String REPORTDAO_SELECT_DEPTNAMERETRORETROXOL="reportdao.select.deptNameRetroRetroxol";
	//public static final String REPORT_RECORD="report.select.premium";
	//public static final String REPORT_SELECT_CLAIM="report.select.claim";
	//public static final String REPORT_SELECT_REGXOL="report.select.regXOL";
	//public static final String REPORT_SELECT_REGPROPOTIONALTREATY="report.select.regPropotionalTreaty";
	//public static final String REPORT_SELECT_FACLTATIVE="report.select.facltative";
	//public static final String REPORT_SELECT_RETROPOLICY="report.select.retroPolicy";
	//public static final String REPORT_SELECT_RETROQUARFAC="report.select.retroQuarFac";
	//public static final String REPORT_SELECT_RETROQUARTREATY="report.select.retroQuarTreaty";
	public static final String REPORT_SELECT_PREMIUMREPORT="report.select.premiumReport";
	public static final String REPORT_SELECT_TREATYPREMIUMREPORT="report.select.treayPremiumReport";
	public static final String REPORT_SELECT_PREMIUMREPORTORDERBY="report.select.premiumReportOrderBy";
	public static final String REPORT_SELECT_CLAIMREPORT="report.select.claimReport";
	public static final String REPORT_SELECT_CLAIMREGLOGINID="report.select.claimRegLoginId";
	public static final String REPORT_SELECT_CLAIMREGCEDINGID="report.select.claimRegCedingId";
	public static final String REPORT_SELECT_CLAIMREGBROKERID="report.select.claimRegBrokerId";
	public static final String REPORT_SELECT_CLAIMREGORDERBY="report.select.claimRegOrderBy";
	public static final String REPORT_SELECT_POLICYREGFACLTATIVE="report.select.PolicyRegfacltative";
	public static final String REPORT_SELECT_POLICYREGFACLOGINID="report.select.policyRegFacLoginId";
	public static final String REPORT_SELECT_POLICYREGFACBROKERID="report.select.policyRegFacBrokerId";
	public static final String REPORT_SELECT_POLICYREGFACCEDINGID="report.select.policyRegFacCedingId";
	public static final String REPORT_SELECT_POLICYREGFACUWYEAR="report.select.policyRegFacUWYear";
	public static final String REPORT_SELECT_CLAIMUWYEAR="report.select.claimuwyear";
	public static final String REPORT_SELECT_POLICYREGFACORDERBY="report.select.policyRegFacOrderBy";
	public static final String REPORT_SELECT_POLICYREGPROPOTIONALTREATY="report.select.policyRegPropotionalTreaty";
	public static final String REPORT_SELECT_POLICYREGXOL="report.select.policyRegXOL";
	public static final String REPORT_SELECT_RETROPOLICYREPORT="report.select.retroPolicyReport";
	public static final String REPORT_SELECT_RETROQUARRDS="report.select.retroQuarRDS";
	public static final String REPORTDAO_SELECT_POLICYREGISTERLIST="reportdao.select.policyRegisterList";
	public static final String REPORTDAO_SELECT_XOLPOLICYREGISTERLIST="reportdao.select.xolPolicyRegisterList";
	public static final String REPORTDAO_SELECT_RENEWALDUEFAC="reportdao.select.renewalDueFac";
	public static final String REPORT_SELECT_RENEWALORDER="reportdao.select.renewalOrder";
	public static final String REPORTDAO_SELECT_RENEWALDUEFACORDERBY="reportdao.select.renewalDueFacOrderBy";
	public static final String REPORTDAO_SELECT_XOLRENEWALDUELIST="reportda.select.xolRenewalDueList";
	public static final String REPORTDAO_SELECT_RETRORETROXOL="reportda.select.retroRetroxol";
	public static final String REPORT_SELECT_GETCEDINGCOMPANYLIST="report.select.getCedingCompanyList";
	public static final String REPORT_SELECT_GETCOLUMNINFOLIST="report.select.getColumnInfoList";
	public static final String REPORT_SELECT_GETCOMPANYINFOLIST="report.select.getCompanyInfoList";
	public static final String REPORT_SELECT_GETREPORTNAME="report.select.getReportName";
	public static final String REPORT_SELECT_GETFACRETROLIST="reportdao.select.getFacRetroList";
	public static final String REPORT_SELECT_GETTREATYXOLRETROLIST="reportdao.select.getTreatyXOLRetroList";
	public static final String REPORT_SELECT_GETFACRETROORDERBY="reportdao.select.getFacRetroOrderBy";
	public static final String REPORT_SELECT_GETRETROFACLIST="reportdao.select.getRetroFacList";
	public static final String REPORT_SELECT_GETRETROTREATYXOLLIST="reportdao.select.getRetroTreatyXOLList";
	public static final String REPORT_SELECT_GETRETROFACORDERBY="reportdao.select.getRetroFacOrderBy";
	public static final String REPORT_SELECT_GETTRANSMASTERLIST="reportdao.select.getTransMasterList";
	public static final String REPORT_SELECT_GETREPORTPRODUCTLIST="reportdao.select.getReportProductList";
	public static final String REPORT_SELECT_CLAIMREPORTPRMXOL="report.select.claimReportPRMXOL";
	public static final String REPORT_SELECT_CLAIMREPORTPRMXOL1="report.select.claimReportPRMXOL1";
	public static final String REPORT_SELECT_RETROPERMIUMCLAIM="report.select.retroPermiumClaim";
	public static final String REPORT_SELECT_REPORTUWYEAR="report.select.reportUWYear";
	public static final String REPORT_SELECT_MOVEMENT_INIT="report.select.moveMentInit";
	public static final String REPORT_SELECT_MOVEMENT="report.select.moveMent";
	public static final String REPORT_SELECT_VIEWJURNAL="report.select.viewJurnal";
	public static final String REPORT_SELECT_VIEWJURNAL1="report.select.viewJurnal1";
	public static final String REPORT_SP_MOVEMENTREP="report.sp.movementRep";
	public static final String REPORT_GET_MOVEMENTCNT="report.get.movementCnt";
	public static final String REPORT_SELECT_DISCREPANCIES="report.select.discrepancies";
	public static final String REPORT_SELECT_GETAGEINGLIST="reportdao.select.getAgeingList";
	public static final String REPORT_SELECT_GETMOVSYMMARY = "reportdao.select.getMovMntSummaryList";
	public static final String REPORT_SELECT_CLJOURNEL_INIT="report.select.clJournalInit";
	//Claim Movement Report
	public static final String REPORT_SELECT_CLMOVEMENT_INIT="report.select.clMoveMentInit";
	public static final String REPORT_SELECT_CLMOVEMENT_DTLS="report.select.clMoveMentDtls";
	public static final String REPORT_SP_CLMOVEMENTREP="report.sp.clMovementRep";
	//CountryBussinessImpl Query's
	public static final String COUNTRYBUSSINESS_SELECT_COUNTRYNAME="countrybussiness.select.countryName";
	public static final String COUNTRYBUSSINESS_INSERT_GETINSERTCOUNTRYMASTER="countrybussiness.insert.getInsertCountryMaster";
	public static final String COUNTRYBUSSINESS_UPDATE_GETUPDATEQUERY="countrybussiness.update.getUpdateQuery";
	public static final String COUNTRYBUSSINESS_SELECT_COUNTRYNAMESNO="countrybussiness.select.countryNameSno";
	public static final String COUNTRYBUSSINESS_SELECT_GETLISTCOUNTRYMASTER="countrybussiness.select.getListCountryMaster";
	public static final String COUNTRYBUSSINESS_SELECT_GETEDITQUERY="countrybussiness.select.getEditQuery";
	public static final String COUNTRYBUSSINESS_SELECT_CHECKAVAILABILITY="countrybussiness.select.checkAvailability";

	//DepartmentBusinessImpl Query's
	public static final String DEPARTMENTBUSINESS_SELECT_TMASDEPTNAME="departmentbusiness.select.tmasDeptName";
	public static final String DEPARTMENTBUSINESS_INSERT_GETINSERTQUERY="departmentbusiness.insert.getInsertQuery";
	public static final String DEPARTMENTBUSINESS_UPDATE_TMASDEPTNAME="departmentbusiness.update.tmasDeptName";
	public static final String DEPARTMENTBUSINESS_SELECT_TMASDEPTNAME2="departmentbusiness.select.tmasDeptName2";
	public static final String DEPARTMENTBUSINESS_SELECT_DEPTIDPID="departmentbusiness.select.deptIdPid";
	public static final String DEPARTMENTBUSINESS_SELECT_GETEDITDEPARTMENTQUERY="departmentbusiness.select.getEditDepartmentQuery";
	public static final String DEPARTMENTBUSINESS_SELECT_TMASDEPTID="departmentbusiness.select.tmasDeptId";
	public static final String DEPARTMENTBUSINESS_SELECT_DEPTIDCOUNT="departmentbusiness.select.deptIdCount";
	public static final String DEPARTMENTBUSINESS_SELECT_MAXDID="departmentbusiness.select.maxDid";
	public static final String DEPARTMENTBUSINESS_SELECT_CORECHECKINSERT="departmentbusiness.select.coreCheckInsert";
	public static final String DEPARTMENTBUSINESS_SELECT_CORECHECKUPDATE="departmentbusiness.select.coreCheckUpdate";
	public static final String DEPARTMENTBUSINESS_INSERT_VALIDATION="departmentbusiness.insert.validation";
	public static final String DEPARTMENTBUSINESS_EDIT_VALIDATION="departmentbusiness.edit.validation";

	//MenuDaoImpl Query's
	public static final String MENUDAO_SELECT_USERRIGHTS="menudao.select.userRights";
	public static final String MENUDAO_SELECT_TMASMENUTMASLOGIN="menudao.select.tmasMenuTmasLogin";
	public static final String MENUDAO_SELECT_TMASMENUTMASLOGIN2="menudao.select.tmasMenuTmasLogin2";
	public static final String MENUDAO_SELECT_MENUQUERY="menudao.select.menuQuery";
	public static final String MENUDAO_SELECT_DEPTIDDEPTNAME="menudao.select.deptIdDeptName";
	public static final String MENUDAO_SELECT_GET_PENDINGOFF_MENUID="menudao.select.getPendingOffMenuId";
	public static final String MENUDAO_SELECT_GETRIGTHSOFPROCESSID="menudao.select.getRigthsOfProcessId";
	public static final String MENUDAO_SELECT_ADMINUSERMENUS="menudao.select.adminuserMenus";

	//SubmenuBusinessImpl Query's
	public static final String SUBMENUBUSINESS_SELECT_TMASDETAILS="submenubusiness.select.tmasDetails";

	//ProfitCenterDaoImpl Query's
	public static final String PROFIT_SELECT_CORECHECKINSERT="profit.select.coreCheckInsert";
	public static final String PROFIT_SELECT_CORECHECKUPDATE="profit.select.coreCheckUpdate";
	public static final String PROFIT_SELECT_PROFITCHECKINSERT="profit.select.profitnamecheckinsert";
	public static final String PROFIT_SELECT_PROFITCHECKUPDATE="profit.select.profitnamecheckupdate";
	public static final String PROFIT_SELECT_PROFITIDCHECK="profit.select.profitidcheck";


	//PolicyBusinessImpl Query's
	public static final String POLICYBUSINES_SELECT_GETLISTPOLICYBRANCH="policybusines.select.getListPolicyBranch";


	//By Najeeb
	//TreatyMaster Query's
	public static final String TREATY_SELECT_LISTQUERY="treaty.select.listQuery";
	public static final String TREATY_SELECT_CHECKAVAIL="treaty.select.checkAvail";
	public static final String TREATY_SELECT_MAXID="treaty.select.maxId";
	public static final String TREATY_INSERT_INSERTDATA="treaty.insert.insertData";
	public static final String TREATY_SELECT_EDITDATA="treaty.select.editData";
	public static final String TREATY_UPDATE_UPDATEDATA="treaty.update.updateData";

	//ProductMaster Query's
	public static final String PRODUCT_SELECT_LISTQUERY="product.select.listQuery";
	public static final String PRODUCT_SELECT_CHECKAVAIL="product.select.checkAvail";
	public static final String PRODUCT_SELECT_MAXID="product.select.maxId";
	public static final String PRODUCT_INSERT_INSERTQUERY="product.insert.insertQuery";
	public static final String PRODUCT_SELECT_EDITQUERY="product.select.editQuery";
	public static final String PRODUCT_UPDATE_UPDATEQUERY="product.update.updateQuery";
	public static final String PRODUCT_SELECT_CORECHECKINSERT="product.select.coreCheckInsert";
	public static final String PRODUCT_SELECT_CORECHECKUPDATE="product.select.coreCheckUpdate";

	public static final String PRODUCT_SELECT_PRODUCTCHECKINSERT="product.select.productCheckInsert";
	public static final String PRODUCT_SELECT_PRODUCTCKUPDATE="product.select.productCheckUpdate";

	//UnderWriterMaster Query's
	public static final String UWR_SELECT_LISTQUERY="uwr.select.listQuery";
	public static final String UWR_SELECT_CHECKAVAIL="uwr.select.checkAvail";
	public static final String UWR_INSERT_INSERTQUERY="uwr.insert.insertQuery";
	public static final String UWR_SELECT_EDITQUERY="uwr.select.editQuery";
	public static final String UWR_UPDATE_UPDATEQUERY="uwr.update.updateQuery";
	public static final String UWR_SELECT_CORECHECKINSERT="uwr.select.coreCheckInsert";
	public static final String UWR_SELECT_CORECHECKUPDATE="uwr.select.coreCheckUpdate";


	//TerritoryMaster Query's
	public static final String TERRITORY_SELECT_LISTQUERY="territory.select.listQuery";
	public static final String TERRITORY_SELECT_CHECKAVAIL="territory.select.checkAvail";
	public static final String TERRITORY_SELECT_MAXID="territory.select.maxId";
	public static final String TERRITORY_INSERT_INSERTQUERY="territory.insert.insertQuery";
	public static final String TERRETORY_SELECT_EDITQUERY="terretory.select.editQuery";
	public static final String TERRITORY_UPDATE_UPDATEQUERY="territory.update.updateQuery";
	public static final String TERRETORY_SELECT_CORECHECKINSERT="terretory.select.coreCheckInsert";
	public static final String TERRETORY_SELECT_CORECHECKUPDATE="terretory.select.coreCheckUpdate";
	public static final String TERRITORY_SELECT_TERRITORYNAMECHECKINSERT="terretory.select.territorynameCheckInsert";
	public static final String TERRITORY_SELECT_TERRITORYNAMECHECKUPDATE="terretory.select.territorynamecoreCheckUpdate";
	//RiskGradeMaster Query's
	public static final String RISKGRADE_SELECT_LISTQUERY="riskgrade.select.listQuery";
	public static final String RISKGRADE_SELECT_CHECKAVAIL="riskgrade.select.checkAvail";
	public static final String RISKGRADE_SELECT_MAXID="riskgrade.select.maxId";
	public static final String RISKGRADE_SELECT_INSERTQUERY="riskgrade.select.insertQuery";
	public static final String RISKGRADE_SELECT_EDITQUERY="riskgrade.select.editQuery";
	public static final String RISKGRADE_UPDATE_UPDATEQUERY="riskgrade.update.updateQuery";
	public static final String RISKGRADE_SELECT_UPDATECHECKAVAIL="riskgrade.select.updateCheckAvail";
	public static final String RISKGRADE_SELECT_CORECHECKINSERT="riskgrade.select.coreCheckInsert";
	public static final String RISKGRADE_SELECT_CORECHECKUPDATE="riskgrade.select.coreCheckUpdate";

	//CategoryMaster Query's
	public static final String CATEGORY_SELECT_LISTQUERY="category.select.listQuery";
	public static final String CATEGORY_SELECT_CHECKAVAIL="category.select.checkAvail";
	public static final String CATEGORY_SELECT_MAXID="category.select.maxId";
	public static final String CATEGORY_INSERT_INSERTQUERY="category.insert.insertQuery";
	public static final String CATEGORY_SELECT_EDITQUERY="category.select.editQuery";
	public static final String CATEGORY_UPDATE_UPDATEQUERY="category.update.updateQuery";
	public static final String CATEGORY_SELECT_UPDATECHECKAVAIL="category.select.updateCheckAvail";
	public static final String CATEGORY_SELECT_CORECHECKINSERT="category.select.coreCheckInsert";
	public static final String CATEGORY_SELECT_CORECHECKUPDATE="category.select.coreCheckUpdate";
	public static final String CATEGORY_SELECT_CATEGORYNAMECHECKINSERT="country.select.categoryNameCheckInsert";
	public static final String CATEGORY_SELECT_CATEGORYNAMECHECKUPDATE="country.select.categoryNameCheckUpdate";
	//CountryMaster Query's
	public static final String COUNRY_SELECT_LISTQUERY="counry.select.listQuery";
	public static final String COUNTRY_SELECT_MAXSNO="country.select.maxSno";
	public static final String COUNTRY_SELECT_MAXCID="country.select.maxCid";
	public static final String COUNTRY_INSERT_INSERTQUERY="country.insert.insertQuery";
	public static final String COUNTRY_SELECT_EDITQUERY="country.select.editQuery";
	public static final String COUNTRY_SELECT_UPDATECHECKAVAIL="country.select.updateCheckAvail";
	public static final String COUNTRY_SELECT_MAXAMENDID="country.select.maxAmendId";
	public static final String COUNTRY_SELECT_CORECHECKINSERT="country.select.coreCheckInsert";
	public static final String COUNTRY_SELECT_CORECHECKUPDATE="country.select.coreCheckUpdate";
	public static final String COUNTRY_UPDATE_QUERY="country.update.query";
	//CurrencyMaster Query's
	public static final String CURRENCY_SELECT_LISTQUERY="currency.select.listQuery";
	public static final String CURRENCY_SELECT_CHECKAVAIL="currency.select.checkAvail";
	public static final String CURRENCY_SELECT_MAXSNO="currency.select.maxsno";
	public static final String CURRENCY_SELECT_MAXCURRENCYID="currency.select.maxCurrencyId";
	public static final String CURRENCY_INSERT_INSERTQUERY="currency.insert.insertQuery";
	public static final String CURRENCY_SELECT_EDITQUERY="currency.select.editQuery";
	public static final String CURRENCY_SELECT_UPDATECHECKAVAIL="currency.select.updateCheckAvail";
	public static final String CURRENCY_SELECT_MAXAMENDID="currency.select.maxAmendId";
	public static final String CURRENCY_SELECT_COUNTRYID="currency.select.countryId";
	public static final String CURRENCY_SELECT_INCEPTIONDATE="currency.select.inceptionDate";
	public static final String CURRENCY_UPDATE_EXPIRYDATE="currency.update.expiryDate";
	public static final String CURRENCY_SELECT_CORECHECKINSERT="currency.select.coreCheckInsert";
	public static final String CURRENCY_SELECT_CORECHECKUPDATE="currency.select.coreCheckUpdate";
	public static final String CURRENCY_SELECT_UPDATEQUERY="currency.update.updatequery";
	public static final String CURRENCY_SELECT_CURRENCYINSERTNAMECHECK="currency.select.currencyNameCheckInsert";
	public static final String CURRENCY_SELECT_CURRENCYUPDATENAMECHECK="currency.select.currencyNameCheckUpdate";
	//ExchangeMaster Query's
	public static final String EXCHANGE_SELECT_LISTQUERY="exchange.select.listQuery";
	public static final String EXCHANGE_SELECT_CHECKAVAIL="exchange.select.checkAvail";
	public static final String EXCHANGE_SELECT_MAXSNO="exchange.select.maxsno";
	public static final String EXCHANGE_SELECT_MAXEXCHANGEID="exchange.select.maxExchangeId";
	public static final String EXCHANGE_INSERT_INSERTQUERY="exchange.insert.insertQuery";
	public static final String EXCHANGE_UPDATE_UPDATEQUERY="exchange.update.updateQuery";
	public static final String EXCHANGE_SELECT_EDITQUERY="exchange.select.editQuery";
	public static final String EXCHANGE_SELECT_UPDATECHECKAVAIL="exchange.select.updateCheckAvail";
	public static final String EXCHANGE_SELECT_MAXAMENDID="exchange.select.maxAmendId";
	public static final String EXCHANGE_SELECT_INCEPTIONDATE="exchange.select.inceptionDate";
	public static final String EXCHANGE_UPDATE_EXPIRYDATE="exchange.update.expiryDate";
	public static final String EXCHANGE_SELECT_CORECHECKINSERT="exchange.select.coreCheckInsert";
	public static final String EXCHANGE_SELECT_CORECHECKUPDATE="exchange.select.coreCheckUpdate";

	//CedingMaster Query's
	public static final String CEDING_SELECT_LISTQUERY="ceding.select.listQuery";
	public static final String CEDING_SELECT_CHECKAVAIL="ceding.select.checkAvail";
	public static final String CEDING_SELECT_MAXID="ceding.select.maxId";
	public static final String CEDING_SELECT_COUNTRYID="ceding.select.countryId";
	public static final String CEDING_INSERT_INSERTQUERY="ceding.insert.insertQuery";
	public static final String CEDING_SELECT_EDITQUERY="ceding.select.editQuery";
	public static final String CEDING_SELECT_UPDATECHECKAVAIL="ceding.select.updateCheckAvail";
	public static final String CEDING_SELECT_MAXAMENDID="ceding.select.maxAmendId";
	public static final String CEDING_SELECT_INCEPTIONDATE="ceding.select.inceptiondate";
	public static final String CEDING_UPDATE_EXPIRYDATE="ceding.update.expiryDate";
	public static final String CEDING_SELECT_CORECHECKINSERT="ceding.select.coreCheckInsert";
	public static final String CEDING_SELECT_CORECHECKUPDATE="ceding.select.coreCheckUpdate";
	public static final String CEDING_UPDATE_QUERY="ceding.update.query";
	//RiskDetails Query's
	public static final String RISK_UPDATE_MNDINSTALLMENTS="risk.update.mndInstallments";
	//public static final String RISK_UPDATE_CONTRACTNO="risk.update.contractNo";
	//public static final String RISK_UPDATE_CONTRACTNOBYPROID="risk.update.contractNoByProId";
	public static final String RISK_UPDATE_RSKDTLS="risk.update.rskDtls";
	public static final String RISK_UPDATE_RSKDTLSFAC="risk.update.rskDtlsFac";
	public static final String RISK_UPDATE_HOMECONTNO="risk.update.homeContNo";
	public static final String RISK_UPDATE_PRO24RSKPROPOSAL = "risk.update.pro24RskProposal";
	public static final String RISK_UPDATE_PRO35RSKPROPOSAL = "risk.update.pro35RskProposal";
	public static final String RISK_UPDATE_PRO24FIRPAGERSKPRO = "risk.update.pro24FirPageRskPro";
	public static final String RISK_SELECT_MAXENDOM="risk.select.maxEndom";
	public static final String RISK_UPDATE_PRO35FIRPAGERSKPRO = "risk.update.pro35FirPageRskPro";
	public static final String RISK_UPDATE_PRO2SECCOMM = "risk.update.pro2SecComm";
	public static final String RISK_UPDATE_PRO3SECCOMM = "risk.update.pro3SecComm";
	public static final String RISK_UPDATE_PRO4SECCOMM = "risk.update.pro4SecComm";
	public static final String RISK_UPDATE_PRO5SECCOMM = "risk.update.pro5SecComm";
	//public static final String RISK_UPDATE_PROPOSALNO = "risk.update.proposalNo";
	public static final String RISK_SELECT_POLICYNOPRODCODE="risk.select.policyNoprodCode";
	public static final String RISK_UPDATE_CONTNO = "risk.update.contNo";
	public static final String RISK_UPDATE_POSITIONMASTER = "risk.update.positionMaster";
	public static final String RISK_UPDATE_MNDINSTALLMENTSDATA = "risk.update.mndInstallmentsData";
	public static final String RISK_UPDATE_PRO24CONTSECPAGE = "risk.update.pro24ContSecPage";
	public static final String RISK_UPDATE_PRO35CONTSECPAGE = "risk.update.pro35ContSecPage";
	public static final String RISK_SELECT_ENDO="risk.select.endo";

	public static final String RISK_INSERT_RETROCESSDTLS = "risk.insert.retroCessDtls";
	public static final String RISK_UPDATE_RETROCESSDTLS ="risk.update.retroCessDtls";
	public static final String RISK_INSERT_PRO24RSKPROPOSAL = "risk.insert.pro24RskProposal";
	public static final String RISK_INSERT_PRO35RSKPROPOSAL = "risk.insert.pro35RskProposal";
	public static final String RISK_INSERT_PRO2SECCOMM = "risk.insert.pro2SecComm";
	public static final String RISK_INSERT_PRO3SECCOMM = "risk.insert.pro3SecComm";
	public static final String RISK_INSERT_PRO4SECCOMM = "risk.insert.pro4SecComm";
	public static final String RISK_INSERT_PRO5SECCOMM = "risk.insert.pro5SecComm";
	public static final String RISK_INSERT_ISAMENDIDPROTREATY = "risk.insert.isAmendIDProTreaty";
	public static final String RISK_INSERT_ISAMENDIDPROFAC="risk.insert.isAmendIDProFac";
	public static final String RISK_INSERT_ISNOTAMENDIDPROFAC="risk.insert.isNotAmendIDProFac";
	public static final String RISK_INSERT_INSTALPREM = "risk.insert.instalPrem";
	public static final String RISK_INSERT_POSITIONMASTER = "risk.insert.positionMaster";

	public static final String RISK_SELECT_MAXRSKSTATUS="risk.select.maxRskStatus";
	//public static final String RISK_SELECT_MAXCONTRACTNO="risk.select.maxContractNo";
	//public static final String RISK_SELECT_MAXCONTRACTNOBYPROID="risk.select.maxContractNoByProId";
	public static final String RISK_SELECT_GETDTLNAME="risk.select.getDtlName";
	public static final String RISK_SELECT_GETSECONDVIEWDATA="risk.select.getSecondViewData";
	public static final String RISK_SELECT_GETRENEWALSTATUS="risk.select.getRenewalStatus";
	public static final String RISK_SELECT_GETRSKPRONO = "risk.select.getRskProNO";
	public static final String RISK_SELECT_GETINSTNO = "risk.select.getInstNo";
	public static final String RISK_SELECT_GETRETROCNT = "risk.select.getGetRetroCnt";
	public static final String RISK_SELECT_RETROCESSCNT="risk.select.getRetroCessCnt";
	public static final String RISK_SELECT_GETMAXRSKPRONO = "risk.select.getMaxRskProNO";
	public static final String RISK_SELECT_GETNEXTRSKPRONO = "risk.select.getNextRskProNO";
	public static final String RISK_SELECT_GETRETROCESSDTLS = "risk.select.getRetroCessDtls";
	public static final String RISK_SELECT_VIEWRETROCESSDTLS = "risk.select.viewRetroCessDtls";
	public static final String RISK_SELECT_GETMAXENDORSENO = "risk.select.getMaxEndorseNo";
	//public static final String RISK_SELECT_GETMAXPRONO = "risk.select.getMaxProNo";
	public static final String RISK_SELECT_GETRSKSTATUS = "risk.select.getRskStatus";
	public static final String RISK_SELECT_GETEDITMODEDATA = "risk.select.getEditModeData";
	public static final String RISK_SELECT_GETEDITMODECONTCOND = "risk.select.getEditModeContCond";
	public static final String RISK_SELECT_GETEDITMODEPROCOND = "risk.select.getEditModeProCond";
	public static final String RISK_SELECT_GETEDITMODEDATAPRO3 = "risk.select.getEditModeDataPro3";
	public static final String RISK_SELECT_GETEDITMODEDATAPRO3CONTCOND = "risk.select.getEditModeDataPro3ContCond";
	public static final String RISK_SELECT_GETEDITMODEDATAPRO3PROCOND = "risk.select.getEditModeDataPro3ProCond";
	public static final String RISK_SELECT_GETQUOTASHARE = "risk.select.getQuotaShare";
	public static final String RISK_SELECT_GETEDITMODESECPAGEDATA = "risk.select.getEditModeSecPageData";
	public static final String RISK_SELECT_GETEDITMODESECPAGEPRO3DATA = "risk.select.getEditModeSecPagePro3Data";
	public static final String RISK_SELECT_GETINSTALMENTDATA = "risk.select.getInstalmentData";
	public static final String RISK_SELECT_VIEWINSTALMENTDATA= "risk.select.viewInstalmentData";
	public static final String RISK_SELECT_GETTHIRDPAGEDATA = "risk.select.getThirdPageData";
	public static final String RISK_SELECT_CHECHPROPOSALSTATUS = "risk.select.chechProposalStatus";
	public static final String RISK_SELECT_GETCOMMONDATA = "risk.select.getCommonData";
	public static final String RISK_SELECT_RISKCOMMAXAMENDID = "risk.select.getRiskComMaxAmendId";
	public static final String RISK_SELECT_GETNEXTAMENDID = "risk.select.getNextAmendID";
	public static final String RISK_SELECT_GETRETROCESSCOUNT = "risk.select.getRetroCessCount";
	public static final String RISK_SELECT_GETRISKCOMMCOUNT = "risk.select.getRiskCommCount";
	public static final String RISK_SELECT_GETRSKCONTRACTNO = "risk.select.getRskContractNo";
	public static final String RISK_SELECT_GETRSKPROIDBYCONTNO = "risk.select.getRskProIdByContNo";
	public static final String RISK_SELECT_GETRSKPROIDBYPRONO = "risk.select.getRskProIdByProNo";
	public static final String RISK_SELECT_GETRSKPRONOOFRSKCOMM = "risk.select.getRskProNoOfRskComm";
	public static final String RISK_SELECT_GETPRO1SHARESIGN = "risk.select.getPro1ShareSign";
	public static final String RISK_SELECT_GETPRO2SHARESIGN = "risk.select.getPro2ShareSign";
	public static final String RISK_SELECT_GETPRO3SHARESIGN = "risk.select.getPro3ShareSign";
	public static final String RISK_SELECT_GETSECPAGEDATA = "risk.select.getSecPageData";
	public static final String RISK_SELECT_GETBROKERAGE = "risk.select.getBrokerage";
	public static final String RISK_SELECT_GETCOMMDET="risk.select.getCommDet";
	public static final String RISK_SELECT_UWYEAR="risk.select.uwYear";
	public static final String RISK_SELECT_LAYERDUPCHECKBYBASELAYER="risk.select.getLayerDupcheckByBaseLayer";
	public static final String RISK_SELECT_LAYERDUPCHECKBYPRONO="risk.select.getLayerDupcheckByProNo";

	//Payment Query's
	//public static final String PAYMENT_UPDATE_PAYMENTNO = "payment.update.paymentNo";
	//public static final String PAYMENT_UPDATE_RECEIPTNO = "payment.update.receiptNo";
	public static final String PAYMENT_UPDATE_DIFFAMT = "payment.update.diffAmt";
	public static final String PAYMENT_UPDATE_PAYRETDTLS = "payment.update.payRetDtls";
	public static final String PAYMENT_UPDATE_ALLOCATEDDTLS = "payment.update.allocatedDtls";
	public static final String PAYMENT_UPDATE_RSKPREMDTLS = "payment.update.rskPremDtls";
	public static final String PAYMENT_UPDATE_CLAIMPYMTDTLS = "payment.update.claimPymtDtls";
	public static final String PAYMENT_UPDATE_PYMTRETDTLS = "payment.update.pymtRetDtls";
	public static final String PAYMENT_UPDATE_CLAIMPYMTDTLS1="payment.update.claimPymtDtls1";
	public static final String PAYMENT_UPDATE_PYMTRETDTLS1="payment.update.pymtRetDtls1";
	public static final String PAYMENT_UPDATE_PYMTRETDATA = "payment.update.pymtRetData";
	public static final String PAYMENT_UPDATE_PROCEDURE  = "payment.update.procedure";
	public static final String PAYMENT_UPDATE_RSKPREMCHKYN = "payment.update.rskPremChkyn";
	public static final String PAYMENT_UPDATE_CLAIMPYMTCHKYN = "payment.update.claimPymtChkyn";
	public static final String PAYMENT_UPDATE_ALLOTRANDTLS = "payment.update.AlloTranDtls";
	public static final String PAYMENT_UPDATE_RSKPREMALLODTLS = "payment.update.rskPremAlloDtls";
	public static final String PAYMENT_UPDATE_PREMSETSTAUS = "payment.update.preSetStatus";
	public static final String PAYMENT_UPDATE_CLAIMPYMTALLODTLS = "payment.update.claimPymtAlloDtls";
	public static final String PAYMENT_UPDATE_CLAIMSETSTAUS = "payment.update.claimSetStatus";
	public static final String PAYMENT_UPDATE_RSKPREMDTLS1="payment.update.rskPremDtls1";
	public static final String PAYMENT_UPDATE_RSKPREM = "payment.update.rskPrem";
	public static final String PAYMENT_UPDATE_CHECKYNRSKPREM = "payment.update.checkynRskPrem";
	public static final String PAYMENT_UPDATE_CLAIMPYMT = "payment.update.ClaimPymt";
	public static final String PAYMENT_UPDATE_CHECKYNCLAIMPYMT = "payment.update.checkynClaimPymt";
	public static final String PAYMENT_INSERT_RECEIPT = "payment.insert.receipt";
	public static final String PAYMENT_INSERT_ALLOTRAN = "payment.insert.alloTran";
	//public static final String PAYMENT_SELECT_GETMAXPAYMENTNO = "payment.select.getMaxPaymentNo";
	//public static final String PAYMENT_SELECT_GETMAXRECEIPTNO = "payment.select.getMaxReceiptNo";
	//public static final String PAYMENT_SELECT_GETNEXTPAYMENTNO = "payment.select.getNextPaymentNo";
	//public static final String PAYMENT_SELECT_GETNEXTRECEIPTNO = "payment.select.getNextReceiptNo";
	public static final String PAYMENT_SELECT_GETNEXTRETDTLSNO = "payment.select.getNextRetDtlsNo";
	public static final String PAYMENT_SELECT_GETDIRBRODTLS = "payment.select.getDirBroDtls";
	public static final String PAYMENT_SELECT_GETTRANCONTDTLS_RETRO = "payment.select.getDirBroDtls.retro";
	public static final String PAYMENT_SELECT_GETBRODTLS = "payment.select.getBroDtls";
	public static final String PAYMENT_SELECT_GETSELCURRENCY = "payment.select.getSelCurrency";
	public static final String PAYMENT_SELECT_GETALLOCATEDTLS = "payment.select.getAllocateDtls";
	public static final String PAYMENT_SELECT_GETRSKPREMDTLS = "payment.select.getRskPremDtls";
	public static final String PAYMENT_SELECT_GETCLAIMPYMTDTLS = "payment.select.getClaimPymtDtls";
	public static final String PAYMENT_SELECT_GETPYMTRETDTLS = "payment.select.getPymtRetDtls";
	public static final String PAYMENT_SELECT_GETPALLTRANDTLS = "payment.select.getAllTranDtls";
	public static final String PAYMENT_SELECT_GETPYMTRETSTATUS = "payment.select.getPymtRetStatus";
	public static final String PAYMENT_SELECT_GETCEDINGCOMPDTLS = "payment.select.getCedingCompDtls";
	public static final String PAYMENT_SELECT_GETPYMTAMTDTLS = "payment.select.getPymtAmtDtls";
	public static final String PAYMENT_SELECT_GETRETAMTDTLS = "payment.select.getRetAmtDtls";
	public static final String PAYMENT_SELECT_GETALLOTRANSDTLS = "payment.select.getAlloTransDtls";
	public static final String PAYMENT_SELECT_GETALLOTRANSACTION = "payment.select.getAlloTransaction";
	public static final String PAYMENT_SELECT_GETEXCHRATE = "payment.select.getExchRate";
	public static final String PAYMENT_SELECT_GETSECONDPAGEDTLS = "payment.select.getSecondPageDtls";
	public static final String PAYMENT_SELECT_GETDIFFAMT = "payment.select.getDiffAmt";
	public static final String PAYMENT_SELECT_GETALLOAMT = "payment.select.getAlloAmt";
	public static final String PAYMENT_SELECT_GETNEXTSNO = "payment.select.getNextSNO";
	public static final String PAYMENT_SELECT_GETALLOTRANDTLS = "payment.select.getAlloTranDtls";
	public static final String PAYMENT_SELECT_GETRETALLODTLS = "payment.select.getRetAlloDtls";
	public static final String PAYMENT_SELECT_GETPYMTRETCURRDTLS = "payment.select.getPymtRetCurrDtls";
	public static final String PAYMENT_SELECT_GETRETCONTDTLS = "payment.select.getRetContDtls";
	public static final String PAYMENT_SELECT_GETTRANCONTDTLS = "payment.select.getTranContDtls";
	public static final String PAYMENT_SELECT_GETTRANCONTDTLS_DIRECT = "payment.select.getTranContDtlsDirect";
	public static final String PAYMENT_SELECT_GETRETLISTS = "payment.select.getRetLists";
	public static final String PAYMENT_SELECT_GETTOTCOUNT = "payment.select.getTotCount";
	public static final String PAYMENT_SELECT_GETRSKPREMCHECKYN = "payment.select.getRskPremCheckyn";
	public static final String PAYMENT_SELECT_GETCLAIMPYMTCHECKYN = "payment.select.getClaimPymtCheckyn";
	public static final String PAYMENT_SELECT_GETTOTAMT = "payment.select.getTotAmt";
	public static final String PAYMENT_SELECT_GETBROCEDINGIDS = "payment.select.getBroCedingIds";
	public static final String PAYMENT_SELECT_GETRETDTLS = "payment.select.getRetDtls";
	public static final String PAYMENT_SELECT_GETRETVIEWDTLS = "payment.select.getRetViewDtls";
	public static final String PAYMENT_SELECT_GETREVERSALINFO = "payment.select.getReversalInfo";
	public static final String PAYMENT_SELECT_GETREVERSALINFO_PAYNO ="payment.select.getReversalInfoPayNo";
	public static final String PAYMENT_SELECT_GETREVERSALINFO_STATUS="payment.select.getReversalInfoStatus";
	public static final String PAYMENT_UPDATE_REVERSALPAYMENT = "payment.update.reversalPayment";
	public static final String PAYMENT_UPDATE_REVERSALPAYMENTDETAILS ="payment.update.reversalPaymentDetails";
	public static final String PAYMENT_SELECT_CURRECYAMT ="payment.select.currencyAmt";
	public static final String PAYMENT_AMOUNT_DETAILS="payment_amount_details";
	//Upload for Document Management
	public static final String UPLOAD_GETDOCLIST="upload.getDocList";
	public static final String UPLOAD_CONTNO="upload.contNo";
	public static final String UPLOAD_TRANNO="upload.tranNo";
	public static final String UPLOAD_GETDOCTYPELIST="upload.getDocTypeList";
	public static final String UPLOAD_INSERTDOCDETAILS="upload.insertDocDetails";
	public static final String UPLOAD_UPDATEDOCDETAILS = "upload.updateDocDetails";
	public static final String UPLOAD_UPDATECONTLAYERNO = "upload.updateContLayerNo";
	public static final String UPLOAD_UPDATETRANNO = "upload.updateTranNo";
	public static final String UPLOAD_SWAPDOCID = "upload.swapDocId";

	//UserMaster
	public static final String USER_SELECT_LISTQUERY="user.select.listQuery";
	public static final String USER_SELECT_RIGHTSLIST="user.select.rightsList";
	//public static final String USER_SELECT_SELECTEDSUBMENUS="user.select.selectedSubMenus";
	public static final String USER_UPDATE_SUBMENURIGHTS="user.update.subMenuRights";
	public static final String USER_SELECT_CHECKFORINSERT="user.select.checkforinsert";
	public static final String USER_INSERT_EMPTYUSERRIGHTS="user.insert.emptyUserRights";
	public static final String USER_SELECT_MAINMENULIST="user.select.MainMenuList";
	public static final String USER_SELECT_SELECTEDMAINMENU="user.select.selectedMainMenu";
	public static final String USER_SELECT_CHKUPDATEMAINMENULIST="user.select.chkUpdateMainMenuList";
	public static final String USER_INSERT_UPDATEMAINMENU="user.insert.updateMainMenu";
	public static final String USER_UPDATE_UPDATEMAINMENU2="user.update.updateMainMenu2";
	public static final String USER_INSERT_INSERTMAINMENU="user.insert.insertMainMenu";
	public static final String USER_DELETE_DELETEMAINMENU="user.delete.deleteMainMenu";
	public static final String USER_INSERT_INSERTSUBMENU="user.insert.insertSubMenuButtons";
	public static final String USER_DELETE_DELETESUBMENU="user.delete.deleteSubMenuButtons";
	public static final String USER_SELECT_DISPALYUSER="user.select.dispalyUser";
	public static final String USER_UPDATE_UPDATEUSER="user.update.updateUser";
	public static final String USER_SELECT_LOGINAVAIL="user.select.loginAvail";
	public static final String USER_INSERT_INSERTUSER="user.insert.insertUser";
	public static final String USER_SELECT_LOGIN="user.select.login";
	public static final String USER_UPDATE_PASSWORDUPDATE="user.update.passwordUpdate";
	public static final String USERLIST_SELECT_LOGINMASTER1="user.update.loginMaster1";
	public static final String USERLIST_SELECT_LOGINMASTER2="user.update.loginMaster2";
	public static final String USERLIST_SELECT_LOGINDTSID="user.select.loginDtsId";
	public static final String USERLIST_SELECT_LOGINDTS="user.select.loginDts";
	public static final String USERLIST_SELECT_MENULIST="userlist.select.menuList";
	public static final String USER_SELECT_LOGINMRFCHECK="user.select.loginMrfCheck";
	public static final String USER_SELECT_PREVIOUSSUBMENUS="user.select.previousSubMenus";
	public static final String USER_INSERT_LIMITS="user.insert.limits";
	public static final String USER_SELECT_CHECKUPDATELIMIT="user.select.checkUpdateLimit";
	public static final String USER_UPDATE_LIMITUPDATE="user.update.limitUpdate";
	public static final String USER_DELETE_LIMITDELETE="user.delete.limitDelete";
	public static final String USER_SELECT_SBCODE="user.select.sbcode";
	public static final String USER_SELECT_PREVIOUSSM="user.select.previousSM";
	public static final String USER_SELECT_UWRLIMIT="user.select.uwrLimit";
	public static final String USER_INSERT_SINGLELIMIT="user.insert.singleLimit";
	public static final String USER_SELECT_SINLELIMITCHECK="user.select.sinleLimitCheck";
	public static final String USER_UPDATE_SINGLELIMIT="user.update.singleLimit";
	public static final String USER_SELECT_SINGLELIMIT="user.select.singleLimit";
	public static final String USER_SELECT_MENUALLOCATIONLIST="user.select.menuAllocationList";
	public static final String USER_SELECT_MENUALLOCATIONINFO="user.select.menuAllocationInfo";
	public static final String USER_SELECT_CHECKMENUALLOCATED="user.select.checkMenuAllocated";
	public static final String USER_SELECT_GETALLOCATEDMENUS="user.select.getAllocatedMenus";
	public static final String USER_UPDATE_UPDATEMENUIDS="user.update.updateMenuids";
	public static final String USER_INSERT_INSERTMENUIDS="user.insert.insertMenuids";
	public static final String USER_SELECT_SUBMENUALLOCATIONINFO="user.select.submenuAllocationInfo";
	public static final String USER_SELECT_MENUALLOCATIONPATH="user.select.menuAllocationPath";
	public static final String USER_SELECT_GETALLOCATEDSUBMENUS="user.select.getAllocatedSubMenus";
	public static final String USER_SELECT_CHECKSUBMENUALLOCATION="user.select.checkSubMenuAllocation";
	public static final String USER_INSERT_INSERTSUBMENUIDS="user.insert.insertsubMenuids";
	public static final String USER_UPDATE_UPDATESUBMNEUIDS="user.update.updateSubMneuids";
	public static final String USER_SELECT_CHECKDEPTALLOCATED="user.select.checkDeptAllocated";
	public static final String USER_SELECT_CHECKDEPTALLOCATEDSUBMENU="user.select.checkDeptAllocatedSubMenu";
	public static final String USER_INSERT_INSERTMENUGLOBALSUBMIT="user.insert.insertMenuGlobalSubmit";
	public static final String USER_UPDATE_UWLIMIT="user.update.uwlimit";
	public static final String USER_UPDATE_MENUGLOBALSUBMIT="user.update.menuGlobalSubmit";
	public static final String USER_SELECT_PREVIOUSDEPTDETAILS="user.select.previousDeptDetails";
	public static final String USER_INSERT_SUBMENUGLOBALSUBMIT="user.insert.subMenuGlobalSubmit";
	public static final String USER_UPDATE_UPDATESUBMENUGLOBALSUBMIT="user.update.updateSubMenuGlobalSubmit";
	public static final String USER_SELECT_ADMINMENULIST="user.select.adminMenuList";
	public static final String USER_SELECT_ADMINMENUIDS="user.select.adminmenuids";
	public static final String USER_INSERT_ADMINMENUS="user.insert.adminmenus";
	public static final String USER_UPDATE_ADMINMENUS="user.update.adminmenus";

	//MenuMaster Query's
	/*public static final String MENUMASTER_INSERT_MENUINSERT="menumaster.insert.menuInsert";
	public static final String MENUMASTER_SELECT_UPDATECHECK="menumaster.select.updateCheck";
	public static final String MENUMASTER_SELECT_SELECTMENU="menumaster.select.selectMenu";*/
	public static final String MENUMASTER_SELECT_MAXMENUID="menumaster.select.maxMenuId";
	public static final String MENUMASTER_SELECT_CHECKMENUINSERT="menumaster.select.checkMenuInsert";
	public static final String MENUMASTER_INSERT_INSERTMENU="menumaster.insert.insertMenu";
	public static final String MENUMASTER_SELECT_MENULIST="menumaster.select.menuList";
	public static final String MENUMASTER_SELECT_EDITMENU="menumaster.select.editMenu";
	public static final String MENUMASTER_UPDATE_UPDATEMENU="menumaster.update.updateMenu";
	public static final String MENUMASTER_SELECT_UPDATECHECK="menumaster.select.updateCheck";
	public static final String MENUMASTER_SELECT_DEPTDROPDOWN="menumaster.select.deptDropDown";
	public static final String MENUMASTER_SELECT_PROCESSDROPDOWN="menumaster.select.processDropDown";
	public static final String MENUMASTER_SELECT_GETPRODUCTANDDEPTID="menumaster.select.getproductAndDeptid";

	//BranchMaster Query's
	public static final String BRANCH_SELECT_LISTQUERY="branch.select.listQuery";
	public static final String BRANCH_SELECT_CURRENCYLIST="branch.select.currencyList";
	public static final String BRANCH_SELECT_COUNTRYLIST="branch.select.countryList";
	public static final String BRANCH_SELECT_NEXTBRANCHCODE="branch.select.nextBranchCode";
	public static final String BRANCH_SELECT_CHECKINSERT="branch.select.checkInsert";
	public static final String BRANCH_INSERT_BRANCHINSERT="branch.insert.branchInsert";
	public static final String BRANCH_SELECT_BRANCHSELECT="branch.select.branchSelect";
	public static final String BRANCH_SELECT_CHECKUPDATE="branch.select.checkUpdate";
	public static final String BRANCH_UPDATE_BRANCHUPDATE="branch.update.branchUpdate";
	public static final String BRANCH_SELECT_CHECKCOREAPPCODE="branch.select.checkCoreAppCode";
	public static final String BRANCH_SELECT_CHECKCOREAPPCODEUPDATE="branch.select.checkCoreAppCodeUpdate";

	//SubMenuMaster Query's
	public static final String SUBMENU_SELECT_SUBMENULIST="submenu.select.subMenuList";
	public static final String SUBMENU_SELECT_SUBMENUSELECT="submenu.select.subMenuSelect";
	public static final String SUBMENU_SELECT_INSERTCHECK="submenu.select.insertCheck";
	public static final String SUBMENU_INSERT_SUBMENUINSERT="submenu.insert.submenuInsert";
	public static final String SUBMENU_SELECT_INSERTCHECK2="submenu.select.insertCheck2";
	public static final String SUBMENU_SELECT_UPDATECHECK="submenu.select.updateCheck";
	public static final String SUBMENU_SELECT_UPDATECHECK2="submenu.select.updateCheck2";
	public static final String SUBMENU_UPDATE_UPDATESUBMENU="submenu.update.updateSubMenu";
	public static final String SUBMENU_SELECT_MENULIST="submenu.select.menuList";
	public static final String SUBMENU_SELECT_SUBMENULISTSELECT="submenu.select.subMenuListSelect";
	public static final String SUBMENU_SELECT_SELECTEDSUBMENUS="submenu.select.SelectedSubMenus";
	public static final String SUBMENU_INSERT_SUBMENUCONFIG="submenu.insert.subMenuConfig";
	public static final String SUBMENU_DELETE_SUBMENUCONFIG="submenu.delete.subMenuConfig";
	public static final String SUBMENU_SELECT_SUBMENUINFO="submenu.select.subMenuInfo";
	public static final String SUBMENU_SELECT_SUBMENUALLOCATIONLIST="submenu.select.subMenuAllocationList";
	public static final String SUBMENU_UPDATE_UPDATEMENU="submenu.update.updateMenu";
	//public static final String SUBMENU_SELECT_CHECKSUBMENUALLOCATED="submenu.select.checksubmenuallocated";

	//Home Page List Query's
	public static final String PORTFOLIO_SELECT_CONTRACTLIST1="portfolio.select.contractList1";
	public static final String PORTFOLIO_SELECT_CONTRACTLIST2="portfolio.select.contractList2";
	public static final String PORTFOLIO_SELECT_CONTRACTLIST3="portfolio.select.contractList3";
	public static final String PORTFOLIO_SELECT_CONTRACTLIST3HISTORY="portfolio.select.contractList3History";
	public static final String PORTFOLIO_SELECT_CONTRACTLIST4="portfolio.select.contractList4";
	public static final String PORTFOLIO_SELECT_CONTRACTLISTLAYERNO1="portfolio.select.contractListLayerNo1";
	public static final String PORTFOLIO_SELECT_CONTRACTLISTLAYERNO2="portfolio.select.contractListLayerNo2";
	public static final String PORTFOLIO_SELECT_CONTRACTLISTLAYERNO3="portfolio.select.contractListLayerNo3";
	public static final String PORTFOLIO_SELECT_CONTRACTLISTDEPTID="portfolio.select.contractListDeptID";
	public static final String PORTFOLIO_SELECT_LOGINID="portfolio.select.loginid";
	public static final String PORTFOLIO_SELECT_CONNO="portfolio.select.conno";
	public static final String PORTFOLIO_SELECT_PERNO="portfolio.select.perno";
	public static final String PORTFOLIO_SELECT_COMNAME="portfolio.select.comname";
	public static final String PORTFOLIO_SELECT_BRONAME="portfolio.select.broname";
	public static final String PORTFOLIO_SELECT_UYEAR="portfolio.select.uyear";
	public static final String PORTFOLIO_SELECT_ORDERBYCONNO="portfolio.select.orderbyconno";
	public static final String PORTFOLIO_SELECT_ORDERBYCONNOAMENDID="portfolio.select.orderbyConNoAmendId";
	public static final String PORTFOLIO_SELECT_ORDERBYPRONO="portfolio.select.orderbyprono";
	public static final String PORTFOLIO_SELECT_NTU="portfolio.select.ntu";
	public static final String PORTFOLIO_SELECT_FLAGN="portfolio.select.flagn";
	public static final String PORTFOLIO_SELECT_FLAGNULL="portfolio.select.flagnull";
	public static final String PORTFOLIO_SELECT_NTUDEPT="portfolio.select.ntudept";
	public static final String PORTFOLIO_SELECT_NTUEND="portfolio.select.ntuend";
	public static final String PORTFOLIO_SELECT_REJECTED="portfolio.select.rejected";
	public static final String PORTFOLIO_SELECT_REJECTEDDEPT="portfolio.select.rejecteddept";
	public static final String PORTFOLIO_SELECT_RENEWALDUE="portfolio.select.renewalDue";
	public static final String PORTFOLIO_SELECT_CLAIMYN="portfolio.select.claimYN";
	public static final String PORTFOLIO_SELECT_PENDING="portfolio.select.pending";
	public static final String PORTFOLIO_SELECT_RENEWALPENDING="portfolio.select.renewalPending";
	public static final String PORTFOLIO_SELECT_AUTOPENDING="portfolio.select.autoPending";
	public static final String PORTFOLIO_SELECT_AUTOPENDING1="portfolio.select.autoPending1";


	//HomeMaster Query's
	//public static final String HOMEMASTER_SELECT_HOMELIST="homemaster.select.homeList";


	//ProcessMaster Query's
	public static final String PROCESSMASTER_SELECT_PROCESSLIST="processmaster.select.processList";
	public static final String PROCESSMASTER_SELECT_INSERTCHECK="processmaster.select.insertCheck";
	public static final String PROCESSMASTER_INSERT_INSERTPROCESS="processmaster.insert.insertProcess";
	public static final String PROCESSMASTER_SELECT_PROCESSDETAILS="processmaster.select.processdetails";
	public static final String PROCESSMASTER_SELECT_UPDATECHECK="processmaster.select.updateCheck";
	public static final String PROCESSMASTER_UPDATE_PROCESSDETAILS="processmaster.update.processdetails";
	//public static final String PROCESSMASTER_SELECT_MENULIST="processmaster.select.menuList";
	//public static final String PROCESSMASTER_SELECT_SUBMENULIST="processmaster.select.submenuList";
	public static final String PROCESSMASTER_SELECT_PROCESSNAMECHECK="processmaster.select.processNameinsertCheck";
	public static final String PROCESSMASTER_SELECT_UPDATEPROCESSNAMECHECK="processmaster.select.processNameupdateCheck";

	//First Step
	public static final String USER_SELECT_PRODUCTMAP="user.select.productMap";
	public static final String USER_SELECT_DEPARTMENTMAP="user.select.departmentMap";
	public static final String USER_SELECT_PROCESSMAP="user.select.processMap";
	public static final String USER_SELECT_FINALMENULIST="user.select.finalMenuList";
	public static final String USER_SELECT_GETOLDPRODUCTID="user.select.getOldProductId";


	//Reports

	public static final String REPORT_SELECT_JOURNEL="report.select.journel";
	public static final String REPORT_SELECT_VIEWJURNALALL1="report.select.viewJurnalall1";
	public static final String REPORT_SELECT_VIEWJURNALALL="report.select.viewJurnalall";
	public static final String JOURNELREPORT_SP_MOVEMENTREP="journelreport.sp.clMovementRep";
	public static final String JOURNELREPORT_SELECT_MOVEMENT_INIT="journelreport.select.moveMentInit";

	public static final String DIRECT_CEDING="ceding.select.direct";
	//Allocation Report
	public static final String ALLOCATION_REPORT_LIST = "allocation.report.list";
	public static final String BROKER_AND_CEDING_NAME = "broker.ceding.name";
	public static final String OPEN_PERIOD_LIST="open.period.journal";
	public static final String PIPE_LINE_JOURNALS="pipe.line.jounal.view";
	public static final String SPE_CURRENCT_LIST="spc.currency.list";
	public static final String TREASURY_JOURNAL_VIEW="treasury.journal.view";
	public static final String PAYREC_REGISTER_REPORT_LIST="payrec.register.list";
	public static final String ACTIVE_OPEN_PERIOD_PROC="active.open.period.proc";
	public static final String INACTIVE_OPEN_PERIOD_PROC="inactive.open.period.proc";
	public static final String INSERT_OPEN_PERIOD="insert.open.period";
	public static final String UPDATE_OPEN_PERIOD="update.open.period.status";
	public static final String  SPE_CURRENCT_LIST1="spc.currency.list1";
	public static final String PIPE_LINE_JOURNALS1="pipe.line.jounal.view1";


	//Masters

	public static final String OPEN_PERIOD_SELECT_LISTQUERY="open.period.list";
	public static final String OPEN_PERIOD_INSERT_INSERTDATA="open.period.insert";
	public static final String OPEN_PERIOD_SELECT_EDITDATA="open.period.edit";
	public static final String OPEN_PERIOD_UPDATE_UPDATEDATA="open.period.update";
	public static final String OPEN_PERIOD_STATUS="open.period.staus";
	public static final String OPEN_PERIOD_STATUS1="open.period.staus1";

	public static final String CLAIM_PAID_REPORT_LIST="claim.paid.report";
	public static final String ADJUSTMENT_LIST="adjutment.list";
	public static final String ADJUSTMENT_INSERT="adjustment.insert";
	public static final String ADJUSTMENT_PAY_REC_LIST="adjustment.payrec.list";
	public static final String ADJUSTMENT_GETALLOTRANSDTLS="adjustment.view.list";
	public static final String ADJUSTMENT_TRANSACTIONCP="adjustment.list.ind.transactionCP";
	public static final String ADJUSTMENT_TRANSACTIONRP="adjustment.list.ind.transactionRP";
	public static final String ADJUSTMENT_GETALLOTRANSDTLS_LIST="adjustment.list.allocated";
	public static final String ADJUSTMENT_LIST_MAXDATE="adjutment.list.maxdate";
	public static final String ADJUSMENT_OPEN_PERION_DATE="adjusment.open.period.date" ;
	public static final String ACCOUNT_LIST="account.list";
	public static final String CURRENCY_LIST="currency.list";
	public static final String BANK_ADDRESS_LIST="bankaddress.list";
	public static final String UPLOAD_GETMODULEDOCLIST="moduledoc.list";
	public static final String UPLOAD_GETMODULETYPELIST="upload_module_list";
	public static final String CLAIM_ALLOCATION_LIST="claim_allocation_list";
	public static final String ADJUSMENT_ALLOCATION_PERION_DATE="allocation.date";
	public static final String ADJUSTMENT_GETREVERSE_TRANSDTLS="adjusment.reverse.trans.list";
	public static final String PAYMENT_UPDATE_ALLOCATEDDTLS1="payment.update.allocatedtls1";
	public static final String UWLIMIT_LIST="uwlimit.list";
	public static final String INSERT_UW_LIMIT="uwlimit.insert";
	public static final String UPDATE_UW_LIMIT="uwlimit.update";
	public static final String GET_UW_LIMIT="edit.uwlimit";
	public static final String UWLIMIT_COMBINATION="uwlimit.combination";
	public static final String PAYMENT_UPDATE_ALLOCATEDDTLS2="payment.update.allocation2";
	public static final String RETRO_REPORT_LIST="retro.report.list";
	public static final String TREATY_WITHDRAW_REPORT_LIST="treaty.withdraw.report.list";
	public static final String PREMIUM_LIST="premium.list";
	public static final String FULL_PREMIUM_LIST="full.premium.list";
	public static final String PAYMENT_INSERT_PAYRETDTLS = "payment.insert.payRetDtls";
	public static final String COMMON_SELECT_GETCURRENCYLIST_BANK="common.select.getcurrency.bank";
	public static final String COMMON_SELECT_DEPARTMENTLIST_PREMIUM="common.select.departlist.premium";
	public static final String CONTRACT_IDENTIFIER_LIST="contract.identifier.list";

	public static final String INSERT_CASS_LOSSCREDIT="insert.cash.loss.credit";
	public static final String UPDATE_CLAIM_PAYMENT="update.claim.payment";
	public static final String CLAIM_SELECT_CLAIMTABLELIST_CLAIMMASTER="claim.select.claim.claimmaster";
	public static final String PARTIAL_CLAIM_SELECT_GETCLAIMPAYMENTMASTERLIST="partial.claim.select.getpaymentlist";
	public static final String CLAIM_SELECT_GETCLAIMPAYMENTMASTERLIST="claim.select.getpaymentlist";
	public static final String CONTRACT_IDENTIFIER_PAYMENTLIST="contract.identifier.payment.list";
	public static final String PARTIAL_CLAIM_SELECT_GETCLAIMMASTERLIST="partial.claim.select.claim.claimmaster";
	public static final String PAYMENT_UPDATE_TTRNRETRODTS="payment.update.retro.details";
	public static final String PAYMENT_UPDATE_TTRNRETROSTATUS="payment.update.retro.status";
	public static final String PAYMENT_UPDATE_RETROPYMTDTLS="payment.update.retro.payment";
	public static final String PAYMENT_SELECT_GETSOAPREMDTLS="payment.get.retro.payment";
	public static final String ALLOCATION_STATUS_COMPARITION="allocation.status";
	public static final String RETRO_PRCL_STATUS_COMPARITION="retro.status";
	public static final String GET_COUNT_PRCL="get.count.prcl.processid";
	public static final String RETRO_PRCL_STATUS_COMPARITION1="retro.status1";
	public static final String CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE = "claim.loss.estimate.paid.difference";
	public static final String CLAIM_SAF_OS_SUM_DIFFERENCE = "claim.saf.os.sum.difference";
	public static final String CLAIM_OTHER_FEE_OS_SUM_DIFFERENCE = "claim.other.fee.os.sum.difference";
	
	//RETRO CLAIM
	public static final String CLAIM_SELECT_SUMPAIDAMT_RETRO="claim.select.sumPaidAmt.retro";
	public static final String CLAIM_SELECT_REVSUMPAIDAMT_RETRO="claim.select.revSumPaidAmt.retro";
	public static final String CLAIM_SELECT_CLAIMEDIT_RETRO="claim.select.claimEdit.retro";
	public static final String CLAIM_SELECT_GETCLAIMRESERVELIST_RETRO="claim.select.getClaimReserveList.Retro";
	public static final String CLAIM_SELECT_LOSSESTIMATEREVISEDOC_RETRO="claim.select.lossEstimateRevisedOc.Retro";
	public static final String CLAIM_SELECT_CLAIMTABLELIST_RETRO="claim.select.claimTableList.Retro";
	public static final String CLAIM_SELECT_GETCLAIMUPDATELIST_RETRO="claim.select.getClaimUpdateList.Retro";
	public static final String CLAIM_SELECT_GETCLAIMREVIEWQUERY_RETRO="claim.select.getClaimReviewQuery.Retro";
	public static final String CLIME_SELECT_GETCLAIMRESERVELIST_RETRO="clime.select.getClaimRESERVEList.Retro";
	public static final String CLAIM_SELECT_GETCLAIMLIST_RETRO="claim.select.getClaimList.Retro";
	public static final String CLAIM_SELECT_GETCLAIMPAYMENTLIST_RETRO="claim.select.getClaimPaymentList.Retro";
	public static final String CLAIM_INSERT_CLIAMDETAILS_RETRO="claim.insert.cliamDetails.Retro";
	public static final String CLAIM_INSERT_GETUPDATIONQUERY_RETRO="claim.insert.getUpdationQuery.Retro";
	public static final String CLAIM_UPDATE_CLIAMDETAILSUPDATE_RETRO="claim.update.cliamDetailsUpdate.Retro";
	public static final String CLAIM_INSERT_PAYMENT_RETRO="claim.insert.payment.Retro";
	public static final String CLAIM_SELECT_MAXRESVID_RETRO="claim.select.maxResvId.Retro";
	public static final String CLAIM_UPDATE_TOTALAMTPAIDTILLDATE_RETRO="claim.update.totalAmtPaidTillDate.Retro";
	public static final String CLAIM_UPDATE_CLOSECLAIM_RETRO="claim.update.closeClaim.Retro";
	public static final String CLAIM_UPDATE_TTRNCLAIMDETAILSRDANDRDB_RETRO="claim.update.ttrnClaimDetailsRDandrdb.Retro";
	public static final String CLAIM_SELECT_PAYMENTREQNO_RETRO="claim.select.paymentReqNo.Retro";
	public static final String CLAIM_SELECT_LOSSESTIMATEOSOC_RETRO="claim.select.lossEstimateOsOc.Retro";
	public static final String CLAIM_SELECT_SUMPAIDAMTOC_RETRO="claim.select.sumPaidAmtOc.Retro";
	public static final String CLIAM_SELECT_TOTALAMTPAIDTILLDATE_RETRO="claim.select.totalAmtPaidTillDate.Retro";
	public static final String CLAIM_SELECT_LOSSESTREV_RETRO="claim.select.lossEstRev.Retro";
	public static final String CLAIM_SAF_OS_SUM_DIFFERENCE_RETRO = "claim.saf.os.sum.difference.Retro";
	public static final String CLAIM_OTHER_FEE_OS_SUM_DIFFERENCE_RETRO = "claim.other.fee.os.sum.difference.Retro";
	public static final String CLAIM_SELECT_EXCRATE_RETRO="claim.select.excRate.Retro";
	public static final String CLAIM_SELECT_CLAIMSTATUS_RETRO="claim.select.claimstatus.Retro";
	public static final String CLAIM_SELECT_GETINSDATE_RETRO="claim.select.getInsDate.Retro";
	public static final String CLAIM_SELECT_GETLOSSDATE_RETRO="claim.select.getLossDate.Retro";
	public static final String CLAIM_SELECT_GETLASTPAYMENTDT_RETRO="claim.select.getLastPayDt.Retro";
	public static final String CLAIM_LOSS_ESTIMATE_PAID_DIFFERENCE_RETRO = "claim.loss.estimate.paid.difference.Retro";
}