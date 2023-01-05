<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${pageContext.request.contextPath}/css/autoProcess.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
    <!-- <link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    <link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/> -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
    <script src="tableToExcel.js"></script>
    <script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
    <style>
        .button {background-color: #4CAF50; /* Green */}

        .button2 {background-color: #008CBA;} /* Blue */
        .button3 {background-color: #f44336;} /* Red */
        .button4 {background-color: #e7e7e7; color: black;} /* Gray */
        .button5 {background-color: #555555;} /* Black */
    </style>
		<script type="text/javascript">
	 $(function() {
		$( "#dueDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	</script>
		<script language="JavaScript" type="text/javascript">
		
		function cancel()
		{
		var contNo=document.getElementById("contNo").value;   
		document.propremium.action="${pageContext.request.contextPath}/premiumListProportionPremium.do?mode=add";
		document.propremium.submit();
		}	
		function cancel1()
		{
		document.propremium.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.propremium.submit();
		}
		 	
		function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}
		function bankChange(val,searchby){
				var URL="${pageContext.request.contextPath}/ajexlistProportionPremium.do?reqform=branchAddress&bancAccountNo="+val;
				postRequest(URL,searchby);	
		}
		function currencyChange(val){
			if(val==1){
				document.getElementById("currValUGX").style.display='block';
				document.getElementById("currValUSD").style.display='none';
				document.getElementById("currValUGX1").style.display='block';
				document.getElementById("currValUSD1").style.display='none';
				document.getElementById("AccNo").style.display='block';
				document.getElementById("AccNo1").style.display='none';
			}else if(val==2){
				document.getElementById("currValUSD").style.display='block';
				document.getElementById("currValUGX").style.display='none';
				document.getElementById("currValUSD1").style.display='block';
				document.getElementById("currValUGX1").style.display='none';
				document.getElementById("AccNo1").style.display='block';
				document.getElementById("AccNo").style.display='none';
			}
		
		}
		function currencycheck(val,currencyId){
		var URL="${pageContext.request.contextPath}/ajexlistProportionPremium.do?reqform=accountno&currencyId="+val;
		postRequest(URL,currencyId);
		}
		function getAddress(val){
			document.getElementById("addr999").innerHTML=document.getElementById("Address_"+val).value;
			document.getElementById("bro999").innerHTML=document.getElementById("broker_"+val).value;
		}
	</script>
		<style type="text/css">
@media print {
	.no-print,.no-print * {
		display: none !important;
	}
	.ui-datepicker-trigger {
		display: none;
	}
}

.footable>tbody>tr>td {
	padding: 1px;
}

@page {
	size: legal; /* auto is the current printer page size */
	margin: 0cm;
}

@media print {
	html,body {
		height: 99%;
	}
}
}
</style>
	</head>
	<body>
		<div class="table0" style="width: 100%; margin: 0 auto;">
				<div class="tablerow">
					<div class="table1"
						style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
						<div class="tablerow">
							<div style="padding: 10px; background: #F8F8F8">
								<s:form name="propremium" action="" method="post" theme="simple">
									<div class="panel panel-primary">
										<div class="panel-heading" align="center">
											<s:text name="label.protreatyPremium" />
										</div>
										<s:if test="ripremiumList.size()>0">	
											<ul class="nav nav-tabs" style="color:blue">
												<s:iterator value="ripremiumList" var="rilist" status="stat">
												<s:if test='0==(#stat.count-1)'>
													<li id="riback<s:property value='#rilist.ReinsurerId'/>" style="background-color:gold"><a data-toggle="tab" href="#menu<s:property value='#rilist.ReinsurerId'/>" onclick="getRiDetail('<s:property value='#rilist.ReinsurerId'/>')"><s:property value="#rilist.ReInsurerName"/></a></li>
												</s:if>
												<s:else>
													<li id="riback<s:property value='#rilist.ReinsurerId'/>" style="background-color:cyan"><a data-toggle="tab" href="#menu<s:property value='#rilist.ReinsurerId'/>" onclick="getRiDetail('<s:property value='#rilist.ReinsurerId'/>')"><s:property value="#rilist.ReInsurerName"/></a></li>
												</s:else>
												</s:iterator>
											</ul>
										</s:if>
										<div class="panel-body">
											<div class="tab-content">
											<s:iterator value="ripremiumList" var="rilist" status="stat">
												<s:if test='0==(#stat.count-1)'>
													<div id="menu<s:property value='#rilist.ReinsurerId'/>" class="tab-pane fade in active">
												</s:if>
												<s:else>
													<div id="menu<s:property value='#rilist.ReinsurerId'/>" class="tab-pane fade">
												</s:else>
												 <div class="panel panel-primary">
														<div class="panel-heading">
															<s:property value="#rilist.ReInsurerName"/>
														</div>
															<div class="panel-body">
																<div class="table2">
																	<div class="tablerow">
																		<div class="boxcontent">
																			<div class="panel panel-primary">
																			<div class="panel-heading">
																				Contract Details
																			</div>
																				<div class="panel-body">
																					<div class="boxcontent">
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.ContractNo" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="contNo" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.UnderwritingYear" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="uwYear" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Claim.TreatyName" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="treatyName_type" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="label.Class" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;<s:property value="departmentName" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.Sub-Profitcenter" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="subProfit_center" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Heding.CedingCompany" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="cedingCo" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="RiskDetails.Broker" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="broker" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.InceptionDate" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="insDate" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.ExipryDate" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="expDate" />
																							</div>
																						</div>
																						<div class="textfield" style="display:table;">
																							<div class="text txtB">
																								<s:text name="Premium.SettlementStatus" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:property value="settlement_status" />
																							</div>
																						</div>
																						<br class="clear" />
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="tablerow">
																		<div class="panel panel-primary">
																			<div class="panel-heading">
																				Transaction Details
																			</div>
																			<div class="panel-body">
																				<div class="boxcontent">
																				<s:if test='"RI02".equals(#session.Source_Code)'>
																				<div class="textfield">
																						<div class="text txtB">
																							<s:text name="Section Name" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="SectionName" />
																						</div>
																					</div>
																					<div class="textfield">
																						
																					</div>
																					</s:if>
																						<div class="textfield">
																							<div class="text txtB">
																								<s:text name="Premium.TransactionNo" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;<s:property value="TransactionNo"/>
																							</div>
																						</div>
																						<div class="textfield">
																							<div class="text txtB">
																								<s:text name="RiskDetails.AmendmentDate" />
																							</div>
																							<div class="tbox">
																								:&nbsp;&nbsp;&nbsp;
																								<s:if test="!AmendmentDate.equals('')">
																								<s:property value="AmendmentDate" />
																								</s:if>
																								<s:else>
																								NA
																								</s:else>
																							</div>
																						</div>
																					<div class="textfield" style="display:table;">
																						<div class="text txtB">
																							<s:text name="label.Class" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;<s:property value="PremiumClass"/>
																						</div>
																					</div>
																					<div class="textfield" style="display:table;">
																						<div class="text txtB">
																							<s:text name="Premium.Sub-Profitcenter" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;<s:property value="PremiumSubClass"/>
																						</div>
																					</div>
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="Premium.Transaction" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="Transaction" />
																						</div>
																					</div>
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="Premium.StatementReceivedDate" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="inception_Date" />
																						</div>
																					</div>
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="label.statementDate" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="StatementDate" />
																						</div>
																					</div>
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="label.accountPeriod" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;<s:property value="AccountPeriod"/> &nbsp;&nbsp;&nbsp; <s:property value="AccountPeriodYear"/> 
																						</div>
																					</div>
																					
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="label.accountPeriodDate" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;<s:property value="AccDate"/>
																						</div>
																					</div>
																					
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="premium.cedantRef" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="CedentRef" />
																						</div>
																					</div>
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="Payment.Currency" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="Currency" /> :&nbsp;<s:property value="ExchRate" />
																						</div>
																					</div>
																					
																					<div class="textfield">
																						<div class="text txtB">
																							<s:text name="label.ricession" />
																						</div>
																						<div class="tbox">
																							:&nbsp;&nbsp;&nbsp;
																							<s:property value="RiCession" />
																						</div>
																					</div>
																			
																					<br class="clear" />
																				</div>
																			</div>
																		</div>
																	</div>
					
																	<div class="tablerow">
																		<div class="panel panel-primary">
																			<div class="panel-body">
																				<table width="100%" class="table table-bordered">
																					<thead>
																						<tr>
																							<th colspan="3">
																								<s:text name="label.credit" />
																							</th>
																							<th colspan="3">
																								<s:text name="label.debit" />
																							</th>
																						</tr>
																					</thead>
																					<tbody>
																						<tr>
																							<td width="16.66%"></td>
																							<td width="16.66%" class="txtB" align="center">
																								<s:text name="Premium.Base" />
																							</td>
																							<td width="16.66%" class="txtB" align="center">
																								<s:property value="currencyName" />
																							</td>
																							<td></td>
																							<td width="16.66%" class="txtB" align="center">
																								<s:text name="Premium.Base" />
																							</td>
																							<td width="16.66%" class="txtB" align="center">
																								<s:property value="currencyName" />
																							</td>
																						</tr>
																						<tr>
																							<td>

																							<s:text name="label.premium" />
																			
																							</td>
																							<td align="right">
																								<s:if test="!PremiumQuotaShare.equals('0.00')">
																									<s:property value="PremiumQuotaShare" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!PremiumQuotaShareUsd.equals('0.00')">
																									<s:property value="PremiumQuotaShareUsd" />
																								</s:if>
																							</td>
																							<td>
																								<s:text name="label.commission" />
																							</td>
																							<td align="right">
																								<s:if test="!CommissionQuotaShare.equals('0.00')">
																									<s:property value="CommissionQuotaShare" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!CommsissionQuotaShareUsd.equals('0.00')">
																									<s:property value="CommsissionQuotaShareUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.PremiumPortfolioIn" />
																							</td>
																							<td align="right">
																								<s:if test="!PremiumportifolioIn.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="PremiumportifolioIn" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!PremiumPortfolioInUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="PremiumPortfolioInUsd" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="label.slideScaleComm" />
																							</td>
																							<td align="right">
																								<s:if test="!SlideScaleCom.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="SlideScaleCom" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!SlideScaleComDC.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="SlideScaleComDC" />
																									</div>
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.ClaimPortfolioIn" />
																							</td>
																							<td align="right">
																								<s:if test="!CliamPortfolioin.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="CliamPortfolioin" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!CliamPortfolioUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="CliamPortfolioUsd" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="Premium.BroKerage" />
																							</td>
																							<td align="right">
																								<s:if test="!Brokerage.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="Brokerage" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!BrokerageUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="BrokerageUsd" />
																									</div>
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.reservereleased" />
																							</td>
																							<td align="right">
																								<s:if test="!PremiumReserveReleased.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="PremiumReserveReleased" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if
																									test="!PremiumReserveReleasedUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="PremiumReserveReleasedUsd" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="label.taxExpense" />
																							</td>
																							<td align="right">
																								<s:if test="!Tax.equals('0.00')">
																									<s:property value="Tax" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!TaxUsd.equals('0.00')">
																									<s:property value="TaxUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.LossReserveReleased" />
																							</td>
																							<td align="right">
																								<s:if test="!LossReserveReleased.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="LossReserveReleased" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!LossReserveReleasedUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="LossReserveReleasedUsd" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="label.WithHoldingTax" />
																							</td>
																							<td align="right">
																								<s:if test="!WithHoldingTaxOC.equals('0.00')">
																									<s:property value="WithHoldingTaxOC" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!WithHoldingTaxDC.equals('0.00')">
																									<s:property value="WithHoldingTaxDC" />
																								</s:if>
																							</td>
					
					
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.Interest" />
																							</td>
																							<td align="right">
																								<s:if test="!Interest.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="Interest" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!InterestDC.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="InterestDC" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="RiskDetails.OverriderAmt" />
																							</td>
																							<td align="right">
																								<s:if test="!Overrider.equals('0.00')">
																									<s:property value="Overrider" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!OverriderUSD.equals('0.00')">
																									<s:property value="OverriderUSD" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="Premium.CashLossCredit" />
																							</td>
																							<td align="right">
																								<s:if test="!CashLossCredit.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="CashLossCredit" />
																									</div>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!CashLossCreditUsd.equals('0.00')">
																									<div style="align: right;">
																										<s:property value="CashLossCreditUsd" />
																									</div>
																								</s:if>
																							</td>
																							<td>
																								<s:text name="Premium.OtherCost" />
																							</td>
																							<td align="right">
																								<s:if test="!OtherCost.equals('0.00')">
																									<s:property value="OtherCost" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!OtherCostUSD.equals('0.00')">
																									<s:property value="OtherCostUSD" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<%--<td>
																								<s:text name="label.taxdedect" />
																							</td>
																							<td align="right">
																								<s:if test="!taxDedectSource.equals('0.00')">
																									<s:property value="taxDedectSource" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!taxDedectSourceDc.equals('0.00')">
																									<s:property value="taxDedectSourceDc" />
																								</s:if>
																							</td>
																							 <td>
																								<s:text name="Premium.xlcost" />
																							</td>
																							<td align="right">
																								<s:if test="!xl_Cost.equals('0.00')">
																									<s:property value="xl_Cost" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!xl_cost_usd.equals('0.00')">
																									<s:property value="xl_cost_usd" />
																								</s:if>
																							</td>--%>
																							<td>
																								<s:text name="label.lossPor" />
																							</td>
																							<td align="right">
																								<s:if test="!LossParticipation.equals('0.00')">
																									<s:property value="LossParticipation" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!IossParticipationDC.equals('0.00')">
																									<s:property value="LossParticipationDC" />
																								</s:if>
																							</td>
																							<td>
																								<s:text name="Premium.PremiumPortfolioOut" />
																							</td>
																							<td align="right">
																								<s:if test="!Premiumportifolioout.equals('0.00')">
																									<s:property value="Ppremiumportifolioout" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!PremiumPortfolioOutUsd.equals('0.00')">
																									<s:property value="PremiumPortfolioOutUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																						<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																							<td>
																							<s:text name="label.taxdedect" />
																							</td>
																							<td align="right">
																								<s:if test="!TaxDedectSource.equals('0.00')">
																									<s:property value="TaxDedectSource" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!TaxDedectSourceDc.equals('0.00')">
																									<s:property value="TaxDedectSourceDc" />
																								</s:if>
																							</td>
																							 
																						</s:if>
																						<s:else>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							</s:else>
																							<td>
																								<s:text name="Premium.claimPortfolioOut" />
																							</td>
																							<td align="right">
																								<s:if test="!CliamPortfolioOut.equals('0.00')">
																									<s:property value="CliamPortfolioOut" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!CliamPortfolioOutUsd.equals('0.00')">
																									<s:property value="CliamPortfolioOutUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<%--<td>
																									<s:text name="label.gst" />
																								</td>
																								<td align="right">
																									<s:if test="!serviceTax.equals('0.00')">
																										<s:property value="serviceTax" />
																									</s:if>
																								</td>
																								<td align="right">
																									<s:if test="!serviceTaxDc.equals('0.00')">
																										<s:property value="serviceTaxDc" />
																									</s:if>
																								</td>
																								 <s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																							</s:if>
																							<s:else>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							</s:else> --%>
																							<td>
																								<s:text name="Premium.PremiumReserveQuotaShare" />
																							</td>
																							<td align="right">
																								<s:if test="!PremiumReserveQuotaShare.equals('0.00')">
																									<s:property value="PremiumReserveQuotaShare" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if
																									test="!PremiumQuotaShareUsd.equals('0.00')">
																									<s:property value="PremiumQuotaShareUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td>
																								<s:text name="Premium.LossReserveRetained" />
																							</td>
																							<td align="right">
																								<s:if test="!LossReserveRetained.equals('0.00')">
																									<s:property value="LossReserveRetained" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!LossReserveRetainedUsd.equals('0.00')">
																									<s:property value="LossReserveRetainedUsd" />
																								</s:if>
																							</td>
																						</tr>
					
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td>
																								<s:text name="Premium.ProfitCommission" />
																							</td>
																							<td align="right">
																								<s:if test="!ProfitCommission.equals('0.00')">
																									<s:property value="ProfitCommission" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!ProfitCommissionUsd.equals('0.00')">
																									<s:property value="ProfitCommissionUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td>
																								<s:text name="Premium.CashLossPaid" />
																							</td>
																							<td align="right">
																								<s:if test="!CashLossPaid.equals('0.00')">
																									<s:property value="CashLossPaid" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!CashLossPaidUsd.equals('0.00')">
																									<s:property value="CashLossPaidUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td>
																								<s:text name="Premium.ClaimsPaid" />
																							</td>
																							<td align="right">
																								<s:if test="!Claimspaid.equals('0.00')">
																									<s:property value="Claimspaid" />
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!ClamsPaidUsd.equals('0.00')">
																									<s:property value="ClamsPaidUsd" />
																								</s:if>
																							</td>
																						</tr>
																						<tr>
																							 <td>
											                                                    <s:text name="label.vat" />
											                                                </td>
											                                                <td align="right">
											                                                    <s:if test="!VatPremium.equals('0.00')">
											                                                        <s:property value="VatPremium"/>
											                                                    </s:if>
											                                                </td>
											                                                <td align="right">
											                                                    <s:if test="!VatPremiumDc.equals('0.00')">
											                                                        <s:property value="VatPremiumDc"/>
											                                                    </s:if>
											                                                </td>
											                                                <td>
																								<s:text name="label.brokerageVat" />
																							</td>
																							<td align="right">
																								<s:if test="!BrokerageVatOc.equals('0.00')">
																									<s:property value="BrokerageVatOc"/>
																								</s:if>
																							</td>
																							<td align="right">
																								<s:if test="!BrokerageVatDc.equals('0.00')">
																								<s:property value="BrokerageVatDc"/>
																								</s:if>
																							</td>	
																						</tr>
																						<tr>
																							<td>
																								<s:text name="premium.total" />
																							</td>
																							<td align="right">
																								<s:property value="TotalCredit" />
																							</td>
																							<td align="right">
																								<s:property value="TotalCreditDC" />
																							</td>
																							<td>
																								<s:text name="premium.total" />
																							</td>
																							<td align="right">
																								<s:property value="TotalDebit" />
																							</td>
																							<td align="right">
																								<s:property value="TotalDebitDC" />
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td align="right">
																								<s:property value="" />
																							</td>
																							<td>
																								<s:text name="Premium.NetDue" />
																							</td>
																							<td align="right">
																								<s:property value="NetDue" />
																							</td>
																							<td align="right">
																								<s:property value="NetDueUsd" />
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<s:text name="premium.remarks" />
																							</td>
																							<td colspan="5">
																								<s:property value="Remarks" />
																							</td>
																						</tr>
																						<tr><td colspan="3">
																						<table class="table table-bordered" >
																						<tr >
																						<td>
																						<s:text name="Do you want to record Outstanding Loss Update?" />
																						
																						</td>
																						<td>
																						<s:radio list="#{'Yes':'Yes','No':'No'}" name="OsbYN" id="OsbYN" value="%{OsbYN==null?'Yes':OsbYN}" onchange="getoutStanding();" disabled="true"/>
																						
																						</td>
																						<td>
																						</td>
																						</tr>
																							<tr>
																							<td>
																								<s:text name="premium.OSClaimsLossUpdate" />  <span id="accountPrd" style="display:none"> </span>
																								
																							</td>
																							<td align="right">
																								<s:property value="OsClaimsLossUpdateOC" />
																							</td>
																							<td align="right">
																								<s:property value="OsClaimsLossUpdateDC" />
																							</td>
																							</tr>
																							</table>
																							</td>
																							<td colspan="3">
																								<table width="100%" class="table table-bordered">
																									<tbody>
																										<s:iterator id="OSClaim" value="osClaimLoss">
																											<tr>
																												<td width="50%">
																													<s:property value="Key" />
																												</td>
																												<td width="50%">
																													<s:property value="value" />
																												</td>
																											</tr>
																										</s:iterator>
																									</tbody>
																								</table>
																							</td>
																						</tr>
																					</tbody>
																					<s:if test="!cashLoss_Credi.equals('0.00')">
																						<s:if test="claimNos.size() > 0">
																							<div id="CashLossCreditOC">
																								<thead>
																									<tr>
																										<th>
																											<s:text name="label.sNo" />
																										</th>
																										<th colspan="2">
																											<s:text name="label.claimNo" />
																										</th>
																										<th>
																											<s:text name="label.currency" />
																										</th>
																										<th colspan="2">
																											<s:text name="label.cashLossCredit" />
																										</th>
																									</tr>
																								</thead>
																								<tbody>
																									<s:iterator value="claimNos" var="claimList"
																										status="stat">
																										<tr>
																											<td>
																												<s:text name="%{#stat.count}"></s:text>
																											</td>
																											<td align="center" colspan="2">
																												<s:property value="claimNo[#stat.count-1]" />
																												<s:hidden name="claimNo[%{#stat.count-1}]"
																													id="claimNo[%{#stat.count-1}]" />
																											</td>
																											<td align="center">
																												<s:property value="currency" />
																											</td>
																											<td align="right">
																												<s:property
																													value="cashLossCreditOC[#stat.count-1]" />
																											</td>
																											<td align="right">
																												<s:property
																													value="cashLossCreditDC[#stat.count-1]" />
																											</td>
																										</tr>
																									</s:iterator>
																								</tbody>
																							</div>
																						</s:if>
																					</s:if>
																				</table>
																			</div>
																		</div>
																	</div>
																	
																	<div class="tablerow">
																	
																		<div class="boxcontent">
																			<div class="panel panel-primary">
																			<div class="panel-heading">
																				Allocation Details
																			</div>
																				<div class="panel-body">
																					<display:table name="allocatedList" pagesize="10"
																						requestURI="" class="table table-bordered" uid="row" id="record2">
																						<display:setProperty name="basic.msg.empty_list"
																							value="None" />
																						<display:setProperty
																							name="paging.banner.one_item_found" value="" />
																						<display:setProperty
																							name="paging.banner.one_items_found" value="" />
																						<display:setProperty
																							name="paging.banner.all_items_found" value="" />
																						<display:setProperty
																							name="paging.banner.some_items_found" value="" />
																						<display:setProperty name="paging.banner.placement"
																							value="bottom" />
																						<display:setProperty name="paging.banner.onepage"
																							value="" />
																						<display:column style="text-align:center;"
																							title="Allocation No." property="serial_no" />
																						<display:column style="text-align:center;"
																							title="Allocation Date" property="allocateddate" />
																						<display:column style="text-align:center;"
																							title="Business Type" property="productname" />
																						<display:column style="text-align:center;" title="Type">								
															PREMIUM														
															</display:column>
																						<display:column style="text-align:center;" title="Sign">
																							<s:if test='"RT".equals(allocateType)'>							
															+
															</s:if>
																							<s:if test='"PT".equals(allocateType)'>							
															-
															</s:if>
																						</display:column>
																						<display:column style="text-align:center;"
																							title="Amount" property="payamount" />
																						<display:column style="text-align:center;"
																							title="Status" property="status" />
																						<display:column style="text-align:center;"
																							title="Receipt/Payment No" property="pay_rec_no" />
																						<display:column style="text-align:center;"
																							title="Settlement Type" property="settlementType" />
																					</display:table>
																					<s:if
																						test='(!"".equals(totalAmount) || null != totalAmount)&&allocatedList.size()>0'>
																						<div class="boxcontent" align="right">
																							<div class="textfield33"></div>
																							<div class="textfield33"></div>
																							&nbsp;&nbsp;&nbsp;
																							<div class="textfield33">
																								<div class="text txtB">
																									<s:text name="Total Allocated Amount" />
																								</div>
																								<div class="tbox">
																									<s:textfield name="totalAmount" id="totalAmount"
																										cssStyle="text-align:right;" readonly="true"
																										cssClass="inputBox" />
																								</div>
																							</div>
																							<br class="clear" />
																						</div>
																					</s:if>
																				</div>
																			</div>
																		</div>
																	</div>
					
																	
					
																</div>
															</div>
										</div>
										</div>
										</s:iterator>
										</div>
										
												<s:hidden name="currencyId" />
													<s:hidden name="commission_view" />
													<s:hidden name="tax_view" />
													<s:hidden name="brokerage_view" />
													<s:hidden name="shareSigned" />
													<s:hidden name="layerNo" />
													<s:hidden name="contNo" id="contNo" />
													<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>
													<s:hidden name="departmentId" id="departmentId"/>
													<s:hidden name="sectionNo" id="sectionNo"/>
								 					<s:hidden name="proposal_No" id="proposal_No"/>
								 					<s:hidden name="transactionNo" id="transactionNo"/>
									</div>
								</s:form>
							</div>
						</div>
						<div class="tablerow" align="center">
							<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel()" /> &nbsp;&nbsp;&nbsp;
							<input type="button" value="Submit" id="print" class="btn btn-sm btn-success"	onClick="fnSubmit()"/>
						</div>
					</div>
						
				</div>
			

		</div>
<script language="JavaScript" type="text/javascript">
getoutStanding();
  function getoutStanding(){
  	val = '<s:property value="osbYN"/>';
   if(val=='Yes'){
   	//document.premium.osClaimsLossUpdateOC.disabled = false;
   	document.getElementById("accountPrd").style.display = 'block';
   	getLableValue();
   	
   }else{
   // document.premium.osClaimsLossUpdateOC.disabled = true;
    document.getElementById("accountPrd").style.display = 'none';
    //document.premium.osClaimsLossUpdateOC.value = '';
   }
  }
  
getLableValue();
		function getLableValue(){
    	var accTypeval ='<s:property value="account_Period"/>' ;
    	var accYear = '<s:property value="account_Period_year"/>';
    	if(accTypeval!=''&&accYear!=''&&accTypeval != 'PF IN' && accTypeval != 'PF OUT' && accYear!=0 ){
	    	if(accTypeval == '31 March'){
	    		document.getElementById('accountPrd').textContent ="31/03/"+accYear;
	    	}
	    	else if(accTypeval == '30 June'){
				document.getElementById('accountPrd').textContent ="30/06/"+accYear;
			}
			else if(accTypeval == '30 September'){
				document.getElementById('accountPrd').textContent ="30/09/"+accYear;
			}
			else if(accTypeval == '31 December'){
				document.getElementById('accountPrd').textContent ="31/12/"+accYear;
			}
		}
		else if(accTypeval == 'PF IN'){
			document.getElementById('accountPrd').textContent ='<s:property value="transaction"/>';
  			//document.premium.osClaimsLossUpdateOC.value ='<s:property value="cliamPortfolioin"/>'; 
  			}
		else if(accTypeval == 'PF OUT'){
			document.getElementById('accountPrd').textContent ='<s:property value="statementDate"/>';
		}
		else{
		document.getElementById('accountPrd').textContent ='';
		}
    }
  function AuthendicationBack(){
	document.propremium.action="${pageContext.request.contextPath}/initAuthentication.do";
	document.propremium.submit();
}
  function getRiDetail(id){
		 <s:iterator value="ripremiumList" var="rilist" status="stat"> 
		 var val='<s:property value="%{#rilist.ReinsurerId}"/>';
		 if(val==id){
			 document.getElementById("menu"+val).className = "tab-pane fade in active";
			 document.getElementById("riback"+val).style.backgroundColor = "gold";
		 }else{
			 document.getElementById("menu"+val).className = "tab-pane fade";
			 document.getElementById("riback"+val).style.backgroundColor = "cyan";
		 }
		 
		 </s:iterator>
	 }
  function fnSubmit(){
		 document.propremium.action="${pageContext.request.contextPath}/updateRiStatusProportionPremium.do";
		 document.propremium.submit();
	 }
  </script>
	</body>
</html>

