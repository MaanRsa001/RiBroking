<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">			
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	<style type="text/css">
	.text {
		width: 40%;
	}
	.tbox {
		width: 60%;
	}
	.inputBox {
		width: 95%;
	}	
	</style>		
</head>
<body>
<s:form name="retroView" id="retroView" action="" method="post" theme="simple">
<s:set name="profitCommissionListVar" value="profitCommissionList"/>
<s:set name="typeYearListVar" value="typeYearList"/>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<div class="table2">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading" align="center">
										<s:text name="heading.retro" />
									</div>
									<div class="panel-body">
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ProposalNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposal_no" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ContNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="contNo" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.DepartmentID" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="departClass" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ProfitCentre" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="profit_Center" cssClass="inputBox"  />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Underwriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="underwriter" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfieldA25">
												<s:text name="RiskDetails.SubProfitCentre" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" readonly="true" name="subProfit_center" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;"/>
										</div>									
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PolicyBranch" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="polBr" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
                                                        <s:text name="label.dummy" />
												</div>
												<div class="tbox">
                                                         <s:radio list="#{'D':'Yes','N':'No'}" name="dummyCon" value="%{(dummyCon==null||dummyCon=='')?'N':dummyCon}"  disabled="true" />
												</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.leadRetrocessionaire" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedingCo" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.leadRetroBroker" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="broker" cssClass="inputBox" />
											</div>
										</div>
										
										<div class="textfield33">
												<div class="text">
													<s:text name="label.retroType" />
												</div>
												<div class="tbox">
													<s:select list="retroTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="retroType" id="retroType" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled="true"/>
												</div>
											</div>
											<div class="textfield33" id="insured" style="dislay:none">
												<div class="text">
													<s:text name="label.insuredName" />
												</div>
												<div class="tbox">
													<s:textfield name="insuredName" cssClass="inputBox"  disabled="true"/>
												</div>
											</div>
										<div class="textfield33" id="type" style="dislay:none">
											<div class="text">
												<s:text name="label.retroTreatyType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatyTypeName" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33" id="name" style="dislay:none">
											<div class="text">
												<s:text name="label.retroTreatyNameType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatyName_type" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.InceptionDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="incepDate" cssClass="inputBox" />
											</div>
										</div>												 
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ExpiryDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="expDate" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.U/WYear" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="uwYear" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AccountDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="accDate" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.OriginalCurrency" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="orginalCurrency" cssClass="inputBox" cssStyle="width:50%;float:left;" />
												<s:textfield readonly="true" name="exchRate" cssClass="inputBox" cssStyle="width:45%;float:left;text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.cessionRate"/>
											</div>
											<div class="tbox">
												<s:select list="cessionList" listKey="TYPE"
                                                              listValue="DETAIL_NAME" name="cessionExgRate" id="cessionExgRate"
                                                              cssClass="inputBoxS" headerKey="0"
                                                              headerValue="---Select---" onchange="getFixRate(this.value)"  cssStyle="float:left;" disabled="true"/>
													 <div id="fixed"><s:textfield name="fixedRate" id="fixedRate"  cssStyle="width:45%;float:left;text-align:right;" 
	                                                  cssClass="inputBox" disabled="true"/> </div>
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Retro-Cessionaries" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="noRetroCess" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.TerritoryScope" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="territoryscope" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.cleancutrunoff" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposalType" cssClass="inputBox" />
											</div>
										</div>
										<s:if test="proposalType !=null && proposalType !='' && proposalType !='Clean-Cut'">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.rate.year" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="runoffYear" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioCovered" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="riskCovered" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.LOCIssued" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="lOCIssued" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Yes" == lOCIssued'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.bankName" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locBankName" cssClass="inputBox" />								
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.creditPrd" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locCreditPrd" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.creditAmt" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locCreditAmt" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.benficerName" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locBeneficerName" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PNOC" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pnoc" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AccountingPeriod" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="accountingPeriod" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.StatementDue" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="receiptofStatements" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PaymentDue" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="receiptofPayment" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>	
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
										 <div class="textfieldA25">
												<s:text name="label.perilCovered" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="1" readonly="true" name="perilCovered" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;"/>
										</div>	
										</s:if>
										<div class="textfieldA25">
											Territory / Region
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="territoryName" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										
										<div class="textfieldA25">
												Countries Included 
											</div>
											<div class="textfieldA75">
												<s:textarea rows="3" name="countryIncludedName" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
											
											</div>
										<div class="textfieldA25">
												Countries  Excluded
											</div>
											<div class="textfieldA75">
													<s:textarea rows="3" name="countryExcludedName" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
											</div>
										
										<hr class="clear"/>		
										<div class="textfield33">
											<div class="text">
												<s:text name="label.ourRetentionType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedRetenType" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.ourRetention" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedReten" cssClass="inputBox" />
											</div>
										</div>
										<s:if test=' "TR".equals(retroType) &&("2".equals(treatyType) || "3".equals(treatyType))'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.noofline" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatynoofLine" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypml" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pml" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Yes"==pml'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypmlper" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pmlPercent" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>	
										</s:if>
										<hr class="clear"/>																				
										
										<table class="table table-bordered">
											<thead>
											<tr>
												<th width="16.66%"></th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/></th>
												<th width="16.66%"></th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/></th>
											</tr>
											</thead>
											<tbody>
											<s:if test="limitOrigCur!=null&& limitOrigCur!=''">
											<tr>
												<td>
													<s:text name="RiskDetails.RetroPolicyLimitOSview" />
												</td>
												
												<td >
													<s:textfield readonly="true" name="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													<s:textfield readonly="true" name="limitOrig_CurDc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td><s:text name="RiskDetails.RetroPolicyLimitOSview" /></td>
												<td><s:textfield readonly="true" name="limitOrigCurOSOC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
												<td><s:textfield readonly="true" name="limitOrigCurOSDC" cssClass="inputBox" cssStyle="text-align: right;" /></td>	
											</tr>
											</s:if>
											<s:if test="faclimitOrigCur!=null&& faclimitOrigCur!=''">
											<tr>
												<td>
													<s:text name="label.facTreatyLimitOCview"/>
												</td>
												
												<td >
													<s:textfield readonly="true" name="faclimitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													<s:textfield readonly="true" name="facLimitOrig_CurDc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td><s:text name="label.facTreatyLimitOCview"/></td>
												<td><s:textfield readonly="true" name="faclimitOrigCurOSOC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
												<td><s:textfield readonly="true" name="faclimitOrigCurOSDC" cssClass="inputBox" cssStyle="text-align: right;" /></td>	
											</tr>
											</s:if>
											<s:if test=' "TR".equals(retroType) &&("2".equals(treatyType) || "3".equals(treatyType))'>
											<s:if test="treatyLimitsurplusOC!=null&& treatyLimitsurplusOC!=''">
											<tr>
												<td>
													<s:text name="label.retroTreatyLimitSurplusOCview"/>
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td><s:text name="label.retroTreatyLimitSurplusOCview"/></td>
												<td><s:textfield readonly="true" name="treatyLimitsurplusOCOSOC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
												<td><s:textfield readonly="true" name="treatyLimitsurplusOCOSDC" cssClass="inputBox" cssStyle="text-align: right;" /></td>	
											</tr>
											</s:if>
											</s:if>
											<tr>
												<td>
													<s:text name="label.EPIOC"/>
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>	
												<td><s:text name="label.EPIOC"/></td>
												<td><s:textfield readonly="true" name="epiEstmateOSOC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
												<td><s:textfield readonly="true" name="epiEstmateOSDC" cssClass="inputBox" cssStyle="text-align: right;" /></td>											
											</tr>	
											<tr>
											<td>
													<s:text name="label.lossadvice" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_Advise" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_Advise_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.lossadvice" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_AdviseOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_AdviseOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<tr>
											<td>
													<s:text name="label.cashlosslimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_Limit_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.cashlosslimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.EventLimit" /></td>
												<td>
													<s:textfield readonly="true" name="event_limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
												
													<s:text name="RiskDetails.EventLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.AggregateLimit" /></td>
												<td>
													<s:textfield readonly="true" name="aggregate_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_Limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.AggregateLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.OccurrentLimit" /></td>
												<td>
													<s:textfield readonly="true" name="occurrent_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_Limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.OccurrentLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<s:if test=' "TR".equals(retroType) &&("1".equals(treatyType) || "3".equals(treatyType))'>
											<tr>
												<td>	
													<s:text name="RiskDetails.PremiumQuotaShare" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													
													<s:text name="RiskDetails.PremiumQuotaShare" />
													
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShareOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShareOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											</s:if>
											<s:if test=' "TR".equals(retroType) &&("2".equals(treatyType) || "3".equals(treatyType))'>
											<tr>
											<td>
													<s:text name="RiskDetails.PremiumSurplus" />
													
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplus" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplus_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													<s:text name="RiskDetails.PremiumSurplus" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplusOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplusOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											</s:if>
											
											</tbody>
										</table>
										<hr class="clear"/>
										<div class="panel panel-default">											
									<div class="panel-heading"> <s:text name="label.acquisitionCost" /> </div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.rate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locRate" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
										<div class="text">
											<s:text name="label.comType" />
										</div>
										<div class="tbox">
											<s:select list="CommissionList" listValue="DETAIL_NAME" listKey="TYPE" name="retroCommissionType" id="retroCommissionType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true"/>
										</div>
										</div>
										<s:if test='"1".equals(treatyType) || "3".equals(treatyType) '>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.commissionQSP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="commissionQ_S" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										
											<div class="textfield33">
												
													<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="commissionQ_SYN"
															 value="commissionQ_SYN==null?'Y':commissionQ_SYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
											</s:if>
											<s:if test='"2".equals(treatyType) || "3".equals(treatyType) '>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.commissionSurpP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="commission_surp" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										
											<div class="textfield33">
													<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="commission_surpYN"
															 value="commission_surpYN==null?'Y':commission_surpYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
											</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.overriderP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="overRidder" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="overRidderYN"
															 value="overRidderYN==null||overRidderYN==''?'Y':overRidderYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.brokerageP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="brokerage" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="brokerageYN"
															 value="brokerageYN==null||brokerageYN==''?'Y':brokerageYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.taxP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="tax" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="taxYN"
															 value="taxYN==null||taxYN==''?'Y':taxYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.otherCostP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="othercost" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="othercostYN"
															 value="othercostYN==null||othercostYN==''?'Y':othercostYN" disabled="true"
															 ></s:radio>
													</div>
											</div>	
										</div>
										</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.premiumReserveP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="premium_Reserve" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.lossReserveP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="loss_reserve" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.interestP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="interest" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
												<div class="text">
														<s:text name="label.ceedODI"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="ceedODIYN"
															 value="ceedODIYN==null || ceedODIYN==''?'Y':ceedODIYN" disabled="true"
															 ></s:radio>
													</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioinoutLossP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="portfolio_inout_Loss" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioinoutPremiumP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="portfolio_inout_Premium" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div><hr class="clear"/>	
										<div class="textfield33">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus" value="crestaStatus==null?'N':crestaStatus" onchange="toggleCrestapopUp(this.value);" disabled="true" ></s:radio>
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="cresta" style="display:none">
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="CrestapopUp('<s:property value="contNo"/>','<s:property value="proposal_no"/>','<s:property value="amendId"/>','<s:property value="orginalCurrency"/>')"><s:text name="label.viewDetails" /></a>
													
												</div>
											</div>
											<div class="textfield33">
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.scaleCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="slideScaleCommission" list="#{'Y':'Yes','N':'No'}"  value="slideScaleCommission==null?'N':slideScaleCommission"  disabled="true"/>													
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="ssc" style="display:none">
													<div class="text">
														<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="renewalProposalNo"/>')"><s:text name="label.viewDetails" /></a>
													</div>
												</div>	
											</div>
											<div class="textfield33">
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.lossporticipents" />
												</div>
												<div class="tbox">													
													<s:radio name="lossParticipants" list="#{'Y':'Yes','N':'No'}"  value="lossParticipants==null?'N':lossParticipants"  disabled="true"/>													
												</div>
											</div>
											<div class="textfield33">
												<div class="text"  id="lp" style="display:none">
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="renewalProposalNo"/>')"><s:text name="label.viewDetails" /></a>
												</div>
											</div>
											<div class="textfield33">
											</div>													
											<div class="textfield33">
												<div class="text">
													<s:text name="label.profitCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="share_Profit_Commission" list="#{'1':'Yes','2':'No'}"  value="share_Profit_Commission==null?'2':share_Profit_Commission" disabled="true" />													
												</div>
											</div>
											<div class="textfield33">
											</div>
											<hr class="clear"/>			
										<div class="boxcontent" id="pc" style="display:none">													
											<div class="textfield33">
												<div class="text">
													<s:text name="label.manexp" /> 
												</div>
												<div class="tbox">												
												<s:textfield name="managementExpenses" id="managementExpenses"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this)" readonly="true"/>	<br/>											</div>
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.comtype" /> 
												</div>
												<div class="tbox">												
													<s:select  list="commissionTypeList"  name="commissionType" id="commissionType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getCommissionField(this.value);getpopupenable(this.value)" disabled="true"/><br/>
												</div>
											</div>
											<div class="textfield33" id="supcom" style="display:none">
												<div class="text">
													<s:text name="label.supprocom" /> 
												</div>
												<div class="tbox">
													<div class="row">
														<div class="col-xs-6">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="superProfitCommission" id="superProfitCommission" value="superProfitCommission==null?'N':superProfitCommission"  onchange="getpopupenable(this.value)" disabled="true"></s:radio>
														</div>
														<div class="col-xs-6" id="compoptype" style="display:none;">
														<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
														</div>
													</div>
												</div>
											</div>	
											<div class="textfield33" id="comper" style="display:none">
												<div class="text">
													<s:text name="label.procomper" /> 
												</div>
												<div class="tbox">												
													<s:textfield name="profitCommissionPer" id="profitCommissionPer"  cssClass="inputBox" cssStyle="text-align: right;" readonly="true"/>		<br/>										
												</div>
											</div>
											<div class="textfield33" id="setup" style="display:none">
												<div class="text">
													<s:text name="label.setup" /> 
												</div>
												<div class="tbox">												
													<s:radio list="#{'Y':'Yes','N':'No'}" name="setup" value="setup==null?'N':setup"  disabled="true"></s:radio>
												</div>
											</div>
											<div>
											<div class="textfield33" >
												<div class="text">
													<s:text name="label.lossCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="lossCarried" id="lossCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getLossYear(this.value)" disabled="true"/><br/>
											</div>
											</div>
											<div class="textfield33" id="lossyear" style="display:none">
												<div class="text">
													<s:text name="label.lossperidyear" /> 
												</div>
												<div class="tbox">
													<s:textfield name="lossyear" id="lossyear"  cssClass="inputBox" cssStyle="text-align: right;" readonly="true" />		<br/>										
													</div>
											</div>
											</div>
											<div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.profitCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="profitCarried" id="profitCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getProfitYear(this.value)" disabled="true"/><br/>
											</div>
											</div>
											<div class="textfield33" id="profityear" style="display:none">
												<div class="text">
													<s:text name="label.profitCarFordyear" /> 
												</div>
												<div class="tbox">												
														<s:textfield name="profitCarriedForYear" id="profitCarriedForYear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
											</div>
											</div>
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.firstprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS" disabled="true" /><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.subprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  disabled="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.subseqcal" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null?'N':subSeqCalculation"  disabled="true"></s:radio><br/>	
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.notes" />
												</div>
												<div class="tbox">
														<s:textarea rows="1" name="profitCommission" id="profitCommission" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" disabled="true"/><br/>
												</div>
											</div>	
											</div>
										<hr class="clear"/>
										<s:if test='!"0".equals(noRetroCess)'>
										<table class="table table-bordered" width="100%">
											<thead>
											<tr>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.slNo" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="label.retroName" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.broker" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="label.shareWrittenP" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="label.proposalStatus" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="label.shareSignedP" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="label.overriderCommissionP" /> </th>
											</tr>
											</thead>
											<tbody>
											<s:iterator value="retroCessList" var="retroCess123" status="stat">
											<tr>
													<td style="text-align: center;">
														<s:property value='%{#stat.count}'/>
													</td>
													<td style="text-align: center;">
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCompany[%{#stat.count-1}]" id="cedingCompany[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
													</td>
													<td style="text-align: center;">
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="retroBroker[%{#stat.count-1}]" id="retroBroker[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
													</td>
													<td style="text-align: center;">
														<s:textfield name="shareAccepted[%{#stat.count-1}]" id="shareAccepted[%{#stat.count-1}]" onkeyup="checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
													<td style="text-align: center;">
														<s:select list="proposallist" listValue="DETAIL_NAME" listKey="TYPE" name="proposalStatus[%{#stat.count-1}]" id="proposalStatus[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
													</td>
													<td style="text-align: center;">
														<s:textfield name="shareSigned[%{#stat.count-1}]" id="shareSigned[%{#stat.count-1}]" onkeyup="checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
													<td style="text-align: center;">
														<s:textfield name="commission[%{#stat.count-1}]" id="commission[%{#stat.count-1}]" onkeyup="checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
											</tr>
											</s:iterator>
											</tbody>
										</table>
										</s:if>
										<hr class="clear"/>	
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.LeadUnderWriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter" cssClass="inputBox" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.leadUnderwritterCountry" />
											</div>
											<div class="tbox">
													<s:textfield  cssClass="inputBox"   name="leader_Underwriter_country" id="leader_Underwriter_country"   readonly="true"   />
											</div>
											</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.LeadUnderwriterShare" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<hr class="clear"/>	--%>
										<div class="panel panel-default">											
										<div class="panel-heading"> &nbsp;&nbsp;&nbsp;</div>
										<div class="panel-body">
											<div class="boxcontent">									
										<div class="textfieldA25" style="font-weight: normal;">
											<s:text name="RiskDetails.Exclusion" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="exclusion" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>	
										<div class="textfieldA25" style="font-weight: normal;">
											<s:text name="RiskDetails.UnderwritersRecom" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="underwriter_Recommendations" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										</div>
										</div>
										</div>
										<hr class="clear"/>	
										<div class="panel panel-default">											
										<div class="panel-heading"> <s:text name="All Remarks" />  </div>
										<div class="panel-body">
										<div class="boxcontent">									
										<table class="table table-bordered" width="100%">
											<thead>
												<tr>
													<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
													<th width="10%" style="text-align: center; vertical-align: middle;"> <s:text name="label.description" />  </th>
													<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark1" />  </th>
													<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark2" /> </th>
												</tr>
											</thead>
											<tbody>
											<s:iterator value="remarkList" var="list" status="stat">
												<tr>
														<td >
									 					<s:textfield name="remarkSNo[%{#stat.count-1}]" id="remarkSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
									 					</td>
														<td>
														<s:textfield name="description[%{#stat.count-1}]" id="description[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="width: 100%; height: 100%;" readonly="true"/>
														
														</td>
														<td>
														<s:textarea name="remark1[%{#stat.count-1}]" id="remark1[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; "  readonly="true"/>
														</td>
														<td>
														<s:textarea name="remark2[%{#stat.count-1}]" id="remark2[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " readonly="true"/>
														</td>
													</tr>
													</s:iterator>
												</tbody>
										</table>
										</div>
										</div>
										</div>
										<hr class="clear"/>										
										
										<div class="textfield">
											<div class="text">
												<s:text name="label.proposalStatus" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="status" cssClass="inputBox" />
											</div>
											<br class="clear"/>
										</div>
										<br class="clear"/>										
									</div>
								</div>
							</div>
						</div>
						
						<div class="tablerow">
							<div class="boxcontent" align="center">
								<s:if test='!"PR".equals(Flag)'>
									<input type="button" id="back" value="Back" class="btn btn-sm btn-danger" onclick="destroyPopUps();goBack('<s:property value="#session.mfrid"/>','<s:property value="#session.DepartmentId"/>','<s:property value="flag"/>')" /> &nbsp;&nbsp;&nbsp;
									<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
								</s:if>
								<s:else>
									<input type="button"  value="Close" class="btn btn-sm btn-danger" onclick="destroyPopUps();window.close()"/>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<s:hidden name="proposalNo" value="%{proposal_no}" />
</s:form>
<script>
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
	function goBack(productId, deptId, flag) {
		if (flag == 'P'||flag == '') {
			document.retroView.action ="commonListPortfolio?manufactureID="+productId+"&Department="+deptId;
		}else if(flag=='C') {
			document.retroView.action="InitPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}else if(flag=='H') {
			document.retroView.action="getHistoryPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}else if(flag=='R') {
			document.retroView.action="commonListPortfolio?manufactureID="+productId+"&flag=R";
		}else if(flag=='N') {
			document.retroView.action="commonListPortfolio?manufactureID="+productId+"&flag=N";
		}else if(flag=='RP') {
			document.retroView.action ="commonListPortfolio?manufactureID="+productId+"&flag=RP";
		}else if(flag=='RD') {
			document.retroView.action="InitPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}
		document.retroView.submit();
	}
	
	function getPrint() {
		$(".btn").hide();
		window.print();
		$(".btn").show(); 
	}
	function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	var type = document.getElementById("commissionType").value;
	var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&mode=view&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&renewalProposalNo="+renewalProposalNo+"&commissionType="+type;
		var windowName = "Details";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 800;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=no';
		var strOpen = window.open (URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;mtbWinCound++;
		strOpen.focus();
		return false;
		}
			funView('<s:property value="slideScaleCommission"/>');
	function  funView(id){
			if(id=="Y"){
	     		document.getElementById('ssc').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('ssc').style.display = 'none';
	       	}
	}
	funLPView('<s:property value="lossParticipants"/>');
	function  funLPView(id){
			if(id=="Y"){
	     		document.getElementById('lp').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('lp').style.display = 'none';
	       	}
	}
	funLProfitiew('<s:property value="share_Profit_Commission"/>');
	function  funLProfitiew(id){
			if(id=="1"){
	     		document.getElementById('pc').style.display = 'block';
	       	} 
	       	else if(id=="2"){
	       	 	document.getElementById('pc').style.display = 'none';
	       	}
	}
	getCommissionField('<s:property value="commissionType"/>');
	function  getCommissionField(id){
			if(id=="PR" || id=="LR"){
	     		document.getElementById('comper').style.display = 'none';
	     		document.getElementById('supcom').style.display = 'none';
	     		document.getElementById('setup').style.display = 'block';
	       	} 
	       	
	       	else if(id=="PC"){
	     		document.getElementById('comper').style.display = 'block';
	     		document.getElementById('supcom').style.display = 'block';
	     		document.getElementById('setup').style.display = 'none';	       	}
	}
	
	getLossYear('<s:property value="lossCarried"/>');
	function getLossYear(id){
			if(id!="TE"){
	     		document.getElementById('lossyear').style.display = 'block';
	       		} 
	       	else{
				document.getElementById('lossyear').style.display = 'none';
	     		  }
	}
	getProfitYear('<s:property value="profitCarried"/>');
	function getProfitYear(id){
			if(id!="TE"){
	     		document.getElementById('profityear').style.display = 'block';
	       		} 
	       	else{
				document.getElementById('profityear').style.display = 'none';
	     		  }
	}
	getpopupenable('<s:property value="commissionType"/>');
	function getpopupenable(id){
		var id = document.getElementById('commissionType').value;
		var slide ="";
		if(id=="PC"){
		 slide = document.retroView.superProfitCommission.value;
		}
		if(id=="PR"){
     		document.getElementById('compoptype').style.display = 'block';
       	} 
       	else if(id=="LR"){
			document.getElementById('compoptype').style.display = 'block';
     			       	}
       	else if(id=="PC" && slide =="Y"){
			document.getElementById('compoptype').style.display = 'block';
     			}
		else{
		document.getElementById('compoptype').style.display = 'none';
		}
	}
	
toggleCrestapopUp('<s:property value="crestaStatus"/>');
function toggleCrestapopUp(value){
if(value=='Y'){
document.getElementById('cresta').style.display='block';
}else if(value=='N'){
document.getElementById('cresta').style.display='none';
}

}
function CrestapopUp(contractNo,proposalno,amendId,cur){
	//var contractNo = document.getElementById("contNo").value;
	//var proposalno = document.getElementById("proposalNo").value;
	//var amendId = document.getElementById("amendId").value;
	//var cur = document.getElementById("orginalCurrency").value;
	//var layerProposalNo = document.getElementById("layerProposalNo").value;
	var layerProposalNo ="";
		var URL ='${pageContext.request.contextPath}/crestaPopUpRiskDetails.action?mode=view&contractNo='+contractNo+'&proposal_no='+proposalno+'&amendId='+amendId+'&orginalCurrency='+cur+'&layerProposalNo='+layerProposalNo;
		var windowName = "Details";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 800;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=no';
		var strOpen = window.open (URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;mtbWinCound++;
		strOpen.focus();
		return false;
}

getFixRate('<s:property value="cessionExgRate"/>');
function getFixRate(val){
	if ("F" ==val ){
		document.getElementById("fixed").style.display='inline';
		document.getElementById("cessionExgRate").style.width='50%';
		document.getElementById("cessionExgRate").style.float='left';
	}
	else{
		document.getElementById("fixed").style.display='none';
		document.getElementById("fixedRate").value='';
		document.getElementById("cessionExgRate").style.width='95%';
	} 
}
getFieldValue('<s:property value="retroType"/>');
function getFieldValue(val){
	if("SR"==val){
		document.getElementById("insured").style.display='inline';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='none';
		document.getElementById('treatyType').value='0';
	}
	else if("TR"==val){
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='inline';
		document.getElementById("name").style.display='inline';
	}
	else if("FO"==val || "TO"==val){
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='inline';
		document.getElementById('treatyType').value='0';
	}
	else{
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='none';
		document.getElementById('treatyType').value='0';
	}
}
</script>
</body>
</html>

