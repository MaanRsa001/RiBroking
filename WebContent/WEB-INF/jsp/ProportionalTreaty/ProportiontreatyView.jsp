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
			<s:if test='!"DN".equals(type)'>
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
																	<%-- <div class="textfield" style="display:table;">
																		<div class="text txtB">
																			<s:text name="Premium.ProfitCenter" />
																		</div>
																		<div class="tbox">
																			:&nbsp;&nbsp;&nbsp;
																			<s:property value="profit_Center" />
																		</div>
																	</div>--%>
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
																	<%-- <div class="textfield" style="display:table;">
																		<div class="text txtB">
																			<s:text name="RiskDetails.PolicyBranch" />
																		</div>
																		<div class="tbox">
																			:&nbsp;&nbsp;&nbsp;
																			<s:property value="policyBranch" />
																		</div>
																	</div>--%>
																	
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
												<%-- <div class="tablerow">
													<div class="panel panel-primary">
														<div class="panel-body">
															<div class="boxcontent">
																<table width="90%" class="table table-bordered">
																	<tr>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.CommisQ/S" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="commission_view" />
																		</td>
																		<s:hidden name="commssion_S"
																			value="%{commission_view}" />
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.CommisSurp" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="commissionSurb_view" />
																			<s:hidden name="commssion_Surp"
																				value="%{commission_view}" />
																		</td>
																	</tr>
																	<tr>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.Overrider" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="overRider_view" />
																		</td>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.BroKerage" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="brokerage_view" />
																			<s:hidden name="brokerage_S"
																				value="%{brokerage_view}" />
																		</td>
																	</tr>
																	<tr>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.Tax" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="tax_view" />
																			<s:hidden name="tax_S" value="%{tax_view}" />
																		</td>
																		<td width="25%" class="txtB">
																			<s:text name="Premium.OtherCostPercent" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="otherCostView" />
																		</td>
																	</tr>
																	<tr>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.PremiumReserve" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="premium_Reserve_view" />
																		</td>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.LossReserve" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="loss_reserve_view" />
																		</td>
																	</tr>
																	<%-- <tr>
																		<td width="25%" class="txtB">
																			<s:text name="RiskDetails.XLCostOurShareOriginal" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="xl_cost_view" />
																		</td>
																		<td width="25%" class="txtB">
																			<s:text name="Premium.Premium(Q/S)" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="premiumQuota_view" />
																		</td>
																	</tr>--%
																	<tr>
																		<%-- <td width="25%" class="txtB">
																			<s:text name="RiskDetails.PremiumSurplus" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="premiumsurp_view" />
																		</td>--%
																		<td class="txtB">
																			<s:text name="label.epi.ourassessment.ourshare" />
																		</td>
																		<td align="right">
																			<s:property value="epioc"/>
																		</td>
																		<td width="25%" class="txtB">
																			<s:text name="Premium.SettlementStatus" />
																		</td>
																		<td width="25%" align="right">
																			<s:property value="settlement_status" />
																		</td>
																	</tr>
																</table>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>--%>

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
																		<s:property value="sectionName" />
																	</div>
																</div>
																<div class="textfield">
																	
																</div>
																</s:if>
																<div class="textfield">
																	<s:if test='""==transactionNo || null == transactionNo'>
																			<div class="text txtB">
																				<s:text name="Premium.reqNo" />
																			</div>
																			<div class="tbox">
																				:&nbsp;&nbsp;&nbsp;<s:property value="requestNo"/>
																			</div>
																		</s:if>
																		<s:else>
																			<div class="text txtB">
																				<s:text name="Premium.TransactionNo" />
																			</div>
																			<div class="tbox">
																				:&nbsp;&nbsp;&nbsp;<s:property value="transactionNo"/>
																			</div>
																		</s:else>
																</div>
																
																	<div class="textfield">
																		<div class="text txtB">
																			<s:text name="RiskDetails.AmendmentDate" />
																		</div>
																		<div class="tbox">
																			:&nbsp;&nbsp;&nbsp;
																			<s:if test="!amendmentDate.equals('')">
																			<s:property value="amendmentDate" />
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
																		:&nbsp;&nbsp;&nbsp;<s:property value="premiumClass"/>
																	</div>
																</div>
																<div class="textfield" style="display:table;">
																	<div class="text txtB">
																		<s:text name="Premium.Sub-Profitcenter" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;<s:property value="premiumSubClass"/>
																	</div>
																</div>
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="Premium.Transaction" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;
																		<s:property value="transaction" />
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
																		<s:property value="statementDate" />
																	</div>
																</div>
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="label.accountPeriod" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;<s:property value="account_Period"/> &nbsp;&nbsp;&nbsp; <s:property value="account_Period_year"/> 
																	</div>
																</div>
																
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="label.accountPeriodDate" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;<s:property value="accDate"/>
																	</div>
																</div>
																
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="premium.cedantRef" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;
																		<s:property value="cedentRef" />
																	</div>
																</div>
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="Payment.Currency" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;
																		<s:property value="currency" /> :&nbsp;<s:property value="exchRate" />
																	</div>
																</div>
																
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="label.ricession" />
																	</div>
																	<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;
																		<s:property value="ri_cession" />
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
																	<s:if test='"1"==treatyType ||"3"==treatyType || "4"==treatyType ||"5"==treatyType'>
																	<tr>
																		<td>
																			<s:if test='"1"==treatyType ||"3"==treatyType'>
																				<s:text name="label.premiumQuotaShare" />
																			</s:if>
																			<s:if test='"4"==treatyType ||"5"==treatyType'>
																				<s:text name="label.premium" />
																			</s:if>
														
																		</td>
																		<td align="right">
																			<s:if test="!premiumQuotaShare.equals('0')">
																				<s:property value="premiumQuotaShare" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!premiumQuotaShare_usd.equals('0')">
																				<s:property value="premiumQuotaShare_usd" />
																			</s:if>
																		</td>
																		<td>
																		<s:if test='"1"==treatyType ||"3"==treatyType'>
																			<s:text name="label.CommissionQuotaShare" />
																		</s:if>
																		<s:if test='"4"==treatyType ||"5"==treatyType'>
																			<s:text name="label.commission" />
																		</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!commissionQuotaShare.equals('0')">
																				<s:property value="commissionQuotaShare" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!commsissionQuotaShare_usd.equals('0')">
																				<s:property value="commsissionQuotaShare_usd" />
																			</s:if>
																		</td>
																	</tr>
																	</s:if>
																	<s:if test='"2"==treatyType ||"3"==treatyType'>
																	<tr>
																		<td>
																			<s:text name="RiskDetails.PremiumSurplus" />
																		</td>
																		<td align="right">
																			<s:if test="!premiumSurplus.equals('0')">
																				<s:property value="premiumSurplus" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!premium_surplus_usd.equals('0')">
																				<s:property value="premium_surplus_usd" />
																			</s:if>
																		</td>
																		<td>
																			<s:text name="RiskDetails.CommissionSurplus" />
																		</td>
																		<td align="right">
																			<s:if test="!commissionSurplus.equals('0')">
																				<s:property value="commissionSurplus" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!comission_surplus_usd.equals('0')">
																				<s:property value="comission_surplus_usd" />
																			</s:if>
																		</td>
																	</tr>
																	</s:if>
																	<tr>
																		<td>
																			<s:text name="Premium.PremiumPortfolioIn" />
																		</td>
																		<td align="right">
																			<s:if test="!premiumportifolioIn.equals('0')">
																				<div style="align: right;">
																					<s:property value="premiumportifolioIn" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!premium_portfolio_in_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="premium_portfolio_in_usd" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="label.slideScaleComm" />
																		</td>
																		<td align="right">
																			<s:if test="!slideScaleCom.equals('0')">
																				<div style="align: right;">
																					<s:property value="slideScaleCom" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!slideScaleComDC.equals('0')">
																				<div style="align: right;">
																					<s:property value="slideScaleComDC" />
																				</div>
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="Premium.ClaimPortfolioIn" />
																		</td>
																		<td align="right">
																			<s:if test="!cliamPortfolioin.equals('0')">
																				<div style="align: right;">
																					<s:property value="cliamPortfolioin" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!cliam_portfolio_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="cliam_portfolio_usd" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="Premium.BroKerage" />
																		</td>
																		<td align="right">
																			<s:if test="!brokerage.equals('0')">
																				<div style="align: right;">
																					<s:property value="brokerage" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!brokerage_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="brokerage_usd" />
																				</div>
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="Premium.reservereleased" />
																		</td>
																		<td align="right">
																			<s:if test="!premium_Reserve_Released.equals('0')">
																				<div style="align: right;">
																					<s:property value="premium_Reserve_Released" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if
																				test="!premium_Reserve_Released_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="premium_Reserve_Released_usd" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="label.taxExpense" />
																		</td>
																		<td align="right">
																			<s:if test="!tax.equals('0')">
																				<s:property value="tax" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!tax_usd.equals('0')">
																				<s:property value="tax_usd" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="Premium.LossReserveReleased" />
																		</td>
																		<td align="right">
																			<s:if test="!lossReserveReleased.equals('0')">
																				<div style="align: right;">
																					<s:property value="lossReserveReleased" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!loss_Reserve_released_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="loss_Reserve_released_usd" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="label.WithHoldingTax" />
																		</td>
																		<td align="right">
																			<s:if test="!withHoldingTaxOC.equals('0')">
																				<s:property value="withHoldingTaxOC" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!withHoldingTaxDC.equals('0')">
																				<s:property value="withHoldingTaxDC" />
																			</s:if>
																		</td>


																	</tr>
																	<tr>
																		<td>
																			<s:text name="Premium.Interest" />
																		</td>
																		<td align="right">
																			<s:if test="!interest.equals('0')">
																				<div style="align: right;">
																					<s:property value="interest" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!interestDC.equals('0')">
																				<div style="align: right;">
																					<s:property value="interestDC" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="RiskDetails.OverriderAmt" />
																		</td>
																		<td align="right">
																			<s:if test="!overrider.equals('0')">
																				<s:property value="overrider" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!overriderUSD.equals('0')">
																				<s:property value="overriderUSD" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="Premium.CashLossCredit" />
																		</td>
																		<td align="right">
																			<s:if test="!cashLoss_Credit.equals('0')">
																				<div style="align: right;">
																					<s:property value="cashLoss_Credit" />
																				</div>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!cash_loss_credit_usd.equals('0')">
																				<div style="align: right;">
																					<s:property value="cash_loss_credit_usd" />
																				</div>
																			</s:if>
																		</td>
																		<td>
																			<s:text name="Premium.OtherCost" />
																		</td>
																		<td align="right">
																			<s:if test="!otherCost.equals('0')">
																				<s:property value="otherCost" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!otherCostUSD.equals('0')">
																				<s:property value="otherCostUSD" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<%--<td>
																			<s:text name="label.taxdedect" />
																		</td>
																		<td align="right">
																			<s:if test="!taxDedectSource.equals('0')">
																				<s:property value="taxDedectSource" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!taxDedectSourceDc.equals('0')">
																				<s:property value="taxDedectSourceDc" />
																			</s:if>
																		</td>
																		 <td>
																			<s:text name="Premium.xlcost" />
																		</td>
																		<td align="right">
																			<s:if test="!xl_Cost.equals('0')">
																				<s:property value="xl_Cost" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!xl_cost_usd.equals('0')">
																				<s:property value="xl_cost_usd" />
																			</s:if>
																		</td>--%>
																		<td>
																			<s:text name="label.lossPor" />
																		</td>
																		<td align="right">
																			<s:if test="!lossParticipation.equals('0')">
																				<s:property value="lossParticipation" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!lossParticipationDC.equals('0')">
																				<s:property value="lossParticipationDC" />
																			</s:if>
																		</td>
																		<td>
																			<s:text name="Premium.PremiumPortfolioOut" />
																		</td>
																		<td align="right">
																			<s:if test="!premiumportifolioout.equals('0')">
																				<s:property value="premiumportifolioout" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!premium_PortfolioOut_usd.equals('0')">
																				<s:property value="premium_PortfolioOut_usd" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																	<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																		<td>
																		<s:text name="label.taxdedect" />
																		</td>
																		<td align="right">
																			<s:if test="!taxDedectSource.equals('0')">
																				<s:property value="taxDedectSource" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!taxDedectSourceDc.equals('0')">
																				<s:property value="taxDedectSourceDc" />
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
																			<s:if test="!cliam_portfolio_out.equals('0')">
																				<s:property value="cliam_portfolio_out" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!cliam_portfolio_out_usd.equals('0')">
																				<s:property value="cliam_portfolio_out_usd" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																		<td>
																				<s:text name="label.gst" />
																			</td>
																			<td align="right">
																				<s:if test="!serviceTax.equals('0')">
																					<s:property value="serviceTax" />
																				</s:if>
																			</td>
																			<td align="right">
																				<s:if test="!serviceTaxDc.equals('0')">
																					<s:property value="serviceTaxDc" />
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
																			<s:text name="Premium.PremiumReserveQuotaShare" />
																		</td>
																		<td align="right">
																			<s:if test="!premiumReserve_QuotaShare.equals('0')">
																				<s:property value="premiumReserve_QuotaShare" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if
																				test="!premium_reserve_quota_share_usd.equals('0')">
																				<s:property value="premium_reserve_quota_share_usd" />
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
																			<s:if test="!loss_ReserveRetained.equals('0')">
																				<s:property value="loss_ReserveRetained" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!loss_reserve_retained_usd.equals('0')">
																				<s:property value="loss_reserve_retained_usd" />
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
																			<s:if test="!profit_Commission.equals('0')">
																				<s:property value="profit_Commission" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!profit_commission_usd.equals('0')">
																				<s:property value="profit_commission_usd" />
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
																			<s:if test="!cash_LossPaid.equals('0')">
																				<s:property value="cash_LossPaid" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!cash_loss_paid_usd.equals('0')">
																				<s:property value="cash_loss_paid_usd" />
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
																			<s:if test="!claims_paid.equals('0')">
																				<s:property value="claims_paid" />
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!clams_paid_usd.equals('0')">
																				<s:property value="clams_paid_usd" />
																			</s:if>
																		</td>
																	</tr>
																	<tr>
																		 <td>
						                                                    <s:text name="label.vat" />
						                                                </td>
						                                                <td align="right">
						                                                    <s:if test="!vatPremium.equals('0.00')">
						                                                        <s:property value="vatPremium"/>
						                                                    </s:if>
						                                                </td>
						                                                <td align="right">
						                                                    <s:if test="!vatPremiumDc.equals('0.00')">
						                                                        <s:property value="vatPremiumDc"/>
						                                                    </s:if>
						                                                </td>
						                                                <td>
																			<s:text name="label.brokerageVat" />
																		</td>
																		<td align="right">
																			<s:if test="!brokerageVat.equals('0.00')">
																				<s:property value="brokerageVat"/>
																			</s:if>
																		</td>
																		<td align="right">
																			<s:if test="!brokerageVatDc.equals('0.00')">
																			<s:property value="brokerageVatDc"/>
																			</s:if>
																		</td>	
																	</tr>
																	<tr>
																		<td>
																			<s:text name="premium.total" />
																		</td>
																		<td align="right">
																			<s:property value="totalCredit" />
																		</td>
																		<td align="right">
																			<s:property value="totalCreditDC" />
																		</td>
																		<td>
																			<s:text name="premium.total" />
																		</td>
																		<td align="right">
																			<s:property value="totalDebit" />
																		</td>
																		<td align="right">
																			<s:property value="totalDebitDC" />
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
																			<s:property value="netDue" />
																		</td>
																		<td align="right">
																			<s:property value="net_due_usd" />
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="premium.remarks" />
																		</td>
																		<td colspan="5">
																			<s:property value="remarks" />
																		</td>
																	</tr>
																	<tr><td colspan="3">
																	<table class="table table-bordered" >
																	<tr >
																	<td>
																	<s:text name="Do you want to record Outstanding Loss Update?" />
																	
																	</td>
																	<td>
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="osbYN" id="osbYN" value="%{osbYN==null?'Yes':osbYN}" onchange="getoutStanding();" disabled="true"/>
																	
																	</td>
																	<td>
																	</td>
																	</tr>
																		<tr>
																		<td>
																			<s:text name="premium.OSClaimsLossUpdate" />  <span id="accountPrd" style="display:none"> </span>
																			
																		</td>
																		<td align="right">
																			<s:property value="osClaimsLossUpdateOC" />
																		</td>
																		<td align="right">
																			<s:property value="osClaimsLossUpdateDC" />
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
																<s:if test="!cashLoss_Credi.equals('0')">
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

												<div class="tablerow" align="center">
													<s:if test='premiumDisplay.equals("premiumDisplay") && (premiumMasterMode==null||!premiumMasterMode.equals("premiumDisplay"))'>
														<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
													</s:if>
													<s:elseif test='premiumDisplay.equals("authenticationDisplay")'>
														<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' />
													</s:elseif>
													<s:elseif test='mode.equals("popup")'>
													<input type="button" value="Close" class="btn btn-sm btn-danger" onclick="window.close()" />
													</s:elseif>
													<s:else>
														<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel()" /> &nbsp;&nbsp;&nbsp;
													</s:else>
													<input type="button" value="Print" id="print"
														class="btn btn-sm btn-primary" onClick="printpage()" />
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
												</div>

											</div>
										</div>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="tablerow">
					<div class="table1"
						style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
						<div class="tablerow">
							<div style="padding: 10px; background: #F8F8F8">
								<s:form name="propremium" method="post" theme="simple">
									<div class="panel panel-primary">
										<div class="panel-heading" align="center">
											<div class="pullLeft">
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<img alt="<s:property value='#session.COMPANY_NAME'/>"	src="<%=request.getContextPath()%>/images/<s:property value='#session.HEADER_LOGO'/>" width="230" height="71">
											</s:if>
											<s:else>
												<img alt="<s:property value='#session.COMPANY_NAME'/>"	src="<%=request.getContextPath()%>/images/<s:property value='#session.HEADER_LOGO'/>" width="230" height="71">
											</s:else>
											</div>
											<s:if test="creditsign<0">
												<s:text name="label.CreditNote" />
											</s:if>
											<s:else>
												<s:text name="label.DebitNote" />
											</s:else>
											<br class="clear" />
										</div>
										<div class="panel-body" style="padding-bottom: 2px;">
											<div class="table2">
												<div class="tablerow">
													<s:if test='"DIRECT ".equals(broker)'>
														<div class="">
															<div class="text txtB" style="width: 25%">
																<s:text name="dn.ClientName" />
															</div>
															<div class="tbox" style="width: 75%">
																:&nbsp;&nbsp;&nbsp;
																<s:property value="cedingCo" />
															</div>

														</div>
													</s:if>
													<s:else>
														<div class="">
															<div class="text txtB" style="width: 25%">
																<s:text name="dn.ClientName" />
															</div>
															<div class="tbox" style="width: 75%">
																:&nbsp;&nbsp;&nbsp;
																<s:select list="brokerAndCedingName"
																	listKey="CUSTOMER_ID" listValue="BROKER"
																	headerValue="---Select---" headerKey="" name="broker"
																	cssClass="no-print" onchange="getAddress(this.value);" />
																<span id="bro999"> <s:property value="broker" />
																</span>
															</div>
														</div>
													</s:else>
													<br />
													<div class="">
														<div class="text txtB" style="width: 25%">
															<s:text name="dn.ClientAddress" />
														</div>
														<div class="tbox" style="width: 75%" id="addressChange">
															:&nbsp;&nbsp;&nbsp;
															<s:iterator value="brokerAndCedingName"
																var="commodityVar" status="stat">
																<s:hidden name="Address[%{#stat.count-1}]"
																	id="Address_%{#commodityVar.CUSTOMER_ID}"
																	value="%{#commodityVar.Address}" />
																<s:hidden name="broker[%{#stat.count-1}]"
																	id="broker_%{#commodityVar.CUSTOMER_ID}"
																	value="%{#commodityVar.BROKER}" />
															</s:iterator>
															<span id="addr999"> <s:property value="Address" />
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<br>
										<table width="100%" class="table table-bordered">
											<tbody>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.TransactionNo" />
													</td>
													<td align="right" width="25%">
														<s:property value="transactionNo" />
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.Transaction" />
													</td>
													<td align="right" width="25%">
														<s:property value="transaction" />
													</td>

												</tr>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.ContractNo" />
													</td>
													<td align="right" width="25%">
														<s:property value="contNo" />
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.UnderwritingYear" />
													</td>
													<td align="right" width="25%">
														<s:property value="uwYear" />
													</td>

												</tr>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.SubClass" />
													</td>
													<td align="right" width="25%">
														<s:property value="subProfit_center" />
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.ShareSigned" />
													</td>
													<td align="right" width="25%">
														<s:property value="shareSigned" />
													</td>
												</tr>

												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Claim.TreatyName" />
													</td>
													<td align="right" width="25%">
														<s:property value="treatyName_type" />
													</td>
													<td>
														<s:text name="" />
													</td>
													<td align="right" width="25%">
														<s:property value="" />
													</td>
												</tr>

												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Heding.CedingCompany" />
													</td>
													<td align="right" width="25%">
														<s:property value="cedingCo" />
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="RiskDetails.Broker" />
													</td>
													<td align="right" width="25%">
														<s:property value="broker" />
													</td>
												</tr>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.InceptionDate" />
													</td>
													<td align="right" width="25%">
														<s:property value="insDate" />
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.ExipryDate" />
													</td>
													<td align="right">
														<s:property value="expDate" />
													</td>
												</tr>

											</tbody>
										</table>
										<br>
										<table width="100%" class="table table-bordered">
											<tbody>

												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="dn.yourRef" />
													</td>
													<td width="25%" align="right">
														<s:property value="cedentRef" />
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.StatementReceivedDate" />
													</td>
													<td width="25%" align="right">
														<s:property value="inception_Date" />
													</td>
												</tr>

												<tr>
													<td style="font-weight: bold">
														<s:text name="Payment.Currency" />
													</td>
													<td align="right">
														<s:property value="currency" />
													</td>
													<td style="font-weight: bold">
														<s:text name="dn.accPeriod" />
													</td>
													<td align="right">
														<s:property value="account_Period" />
														&nbsp;
														<s:property value="Account_Period_year" />
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="dn.ourTin" />
													</td>
													<td align="right">
														1001115829
													</td>
													<td>
														<s:text name="" />
													</td>
													<td align="right">
														<s:property value="" />
													</td>
												</tr>
											</tbody>
										</table>
										<br>
										<table width="100%" class="table table-bordered">
											<thead>
												<s:if test='!"DN".equals(type)'>
													<tr>
														<th colspan="2">
															<s:text name="label.credit" />
														</th>
														<th colspan="2">
															<s:text name="label.debit" />
														</th>
													</tr>
												</s:if>
												<s:else>
													<tr>
														<th colspan="2">
															<s:text name="label.debit" />
														</th>
														<th colspan="2">
															<s:text name="label.credit" />
														</th>
													</tr>
												</s:else>
											</thead>
											<tbody>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.PremiumQuotaShareTTY" />
													</td>
													<td align="right" width="25%">
														<s:if test="!premiumQuotaShare.equals('0')">
															<s:property value="premiumQuotaShare" />
														</s:if>
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.CommissionQS" />
													</td>
													<td align="right" width="25%">
														<s:if test="!commissionQuotaShare.equals('0')">
															<s:property value="commissionQuotaShare" />
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.PremiumSurplus" />
													</td>
													<td align="right">
														<s:if test="!premiumSurplus.equals('0')">
															<s:property value="premiumSurplus" />
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.CommissionSurplus" />
													</td>
													<td align="right">
														<s:if test="!commissionSurplus.equals('0')">
															<s:property value="commissionSurplus" />
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.PremiumPortfolioIn" />
													</td>
													<td align="right">
														<s:if test="!premiumportifolioIn.equals('0')">
															<div style="align: right;">
																<s:property value="premiumportifolioIn" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.BroKerage" />
													</td>
													<td align="right">
														<s:if test="!brokerage.equals('0')">
															<div style="align: right;">
																<s:property value="brokerage" />
															</div>
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.ClaimPortfolioIn" />
													</td>
													<td align="right">
														<s:if test="!cliamPortfolioin.equals('0')">
															<div style="align: right;">
																<s:property value="cliamPortfolioin" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.Tax" />
													</td>
													<td align="right">
														<s:if test="!tax.equals('0')">
															<s:property value="tax" />
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.reservereleased" />
													</td>
													<td align="right">
														<s:if test="!premium_Reserve_Released.equals('0')">
															<div style="align: right;">
																<s:property value="premium_Reserve_Released" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="label.WithHoldingTax" />
													</td>
													<td align="right">
														<s:if test="!withHoldingTaxOC.equals('0')">
															<s:property value="withHoldingTaxOC" />
														</s:if>
													</td>

												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.LossReserveReleased" />
													</td>
													<td align="right">
														<s:if test="!lossReserveReleased.equals('0')">
															<div style="align: right;">
																<s:property value="lossReserveReleased" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.OverriderAmt" />
													</td>
													<td align="right">
														<s:if test="!overrider.equals('0')">
															<s:property value="overrider" />
														</s:if>
													</td>

												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.Interest" />
													</td>
													<td align="right">
														<s:if test="!interest.equals('0')">
															<div style="align: right;">
																<s:property value="interest" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.OtherCost" />
													</td>
													<td align="right">
														<s:if test="!otherCost.equals('0')">
															<s:property value="otherCost" />
														</s:if>
													</td>

												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.CashLossCredit" />
													</td>
													<td align="right">
														<s:if test="!cashLoss_Credit.equals('0')">
															<div style="align: right;">
																<s:property value="cashLoss_Credit" />
															</div>
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.xlcost" />
													</td>
													<td align="right">
														<s:if test="!xl_Cost.equals('0')">
															<s:property value="xl_Cost" />
														</s:if>
													</td>

												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="label.taxdedect" />
													</td>
													<td align="right">
														<s:if test="!taxDedectSource.equals('0')">
															<s:property value="taxDedectSource" />
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.PremiumPortfolioOut" />
													</td>
													<td align="right">
														<s:if test="!premiumportifolioout.equals('0')">
															<s:property value="premiumportifolioout" />
														</s:if>
													</td>

												</tr>
												<tr>

													<td style="font-weight: bold">
														<s:text name="label.sertax" />
													</td>
													<td align="right">
														<s:if test="!serviceTax.equals('0')">
															<s:property value="serviceTax" />
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.claimPortfolioOut" />
													</td>
													<td align="right">
														<s:if test="!cliam_portfolio_out.equals('0')">
															<s:property value="cliam_portfolio_out" />
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
													<td style="font-weight: bold">
														<s:text name="Premium.PremiumReserveQuotaShare" />
													</td>
													<td align="right">
														<s:if test="!premiumReserve_QuotaShare.equals('0')">
															<s:property value="premiumReserve_QuotaShare" />
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
													<td style="font-weight: bold">
														<s:text name="Premium.LossReserveRetained" />
													</td>
													<td align="right">
														<s:if test="!loss_ReserveRetained.equals('0')">
															<s:property value="loss_ReserveRetained" />
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
													<td style="font-weight: bold">
														<s:text name="Premium.ProfitCommission" />
													</td>
													<td align="right">
														<s:if test="!profit_Commission.equals('0')">
															<s:property value="profit_Commission" />
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
													<td style="font-weight: bold">
														<s:text name="Premium.CashLossPaid" />
													</td>
													<td align="right">
														<s:if test="!cash_LossPaid.equals('0')">
															<s:property value="cash_LossPaid" />
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
													<td style="font-weight: bold">
														<s:text name="Premium.ClaimsPaid" />
													</td>
													<td align="right">
														<s:if test="!claims_paid.equals('0')">
															<s:property value="claims_paid" />
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="premium.total" />
													</td>
													<td align="right">
														<s:property value="totalCredit" />
													</td>
													<td style="font-weight: bold">
														<s:text name="premium.total" />
													</td>
													<td align="right">
														<s:if test="totalDebit<0">
														(<s:property value="totalDebit*(-1)" />)
													</s:if>
														<s:else>
															<s:property value="totalDebit" />
														</s:else>
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="" />
													</td>
													<td align="right">
														<s:property value="" />
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.NetDue" />
													</td>
													<td align="right">
														<s:property value="netDue" />
													</td>
												</tr>
												<s:if test='"DN".equals(type)'>
													<tr>
														<td style="font-weight: bold">
															<s:if test="creditsign<0">
																<s:text name="Premium.BalDueTo" />
															</s:if>
															<s:else>
																<s:text name="Premium.BalDueFrom" />
															</s:else>
														</td>
														<td colspan="3" align="right">
															<s:if test="creditsign<0">
																<!--<s:property value="creditsign*(-1)" />-->
																<s:property
																	value="getText('{0,number,#,##0.00}',{creditsign*(-1)})" />
															</s:if>
															<s:else>
																<s:property value="netDue" />
															</s:else>
														</td>
													</tr>
												</s:if>
										</table>
										<br>
										<div class="textfield">
											<div class="text">
												<s:text name="Payment Due date " />
											</div>
											<div class="tbox">
												<!--  <div class="inputAppend">
										 <sj:datepicker name="dueDate" id="dueDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent; no-print" onkeyup="validateSpecialChars(this)" />
										 
										</div>-->
												<s:textfield name="dueDate" id="dueDate" cssClass="inputBox"
													onkeyup="validateSpecialChars(this)" />
												<!--<s:property value="dueDate"/>
										-->
											</div>
										</div>
										<br>
										<br>
										<br class="clear" />
										<div class="boxcontent">

											<span id="currValUGX1" style="display: none;">All
												payments to be made to our UGX Accounts as follows:</span>
											<span id="currValUSD1" style="display: none;">All
												payments to be made to our USD Accounts as follows:</span>

										</div>
										<br>
										<br>
										<div class="boxcontent">
											<div class="textfieldA25 no-print">
												<s:text name="dn.currency" />
											</div>
											<div class="textfieldA75" id="currencyId">
												<!--<s:select list="#{'1':'UGX','2':'USD'}"
													headerValue="---Select---" headerKey=""
													onchange="currencyChange(this.value);" cssClass="no-print" />-->
												<!--<s:select list="#{'1':'UGX','2':'USD'}"
													headerValue="---Select---" headerKey=""
													onchange='currencycheck(this.value,"AccountCodeDiv");' cssClass="no-print" />-->
												<s:select list="currencyList" headerValue="---Select---"
													headerKey="" listKey="Code" listValue="CodeDescription"
													onchange='currencycheck(this.value,"AccountCodeDiv");'
													cssClass="no-print" />
												<!--<span id="currValUGX" style="display: none;"> UGX </span>
													<span id="currValUSD" style="display: none;"> USD </span>-->
												<div>
												</div>

											</div>
											<br class="clear" />
										</div>
										<div id="AccountCodeDiv">
											<div class="boxcontent">
												<s:if test='accountList!=null'>
													<div class="textfieldA25">
														<s:text name="Currency Name" />
													</div>
													<div class="textfieldA75">
														<s:property value="currencyName" />
													</div>
													<div class="textfieldA25 no-print">
														<s:text name="dn.AccNo" />
													</div>
													<div class="textfieldA75">
														<s:select list="accountList" name="accountId"
															id="accountId" cssClass="no-print" listKey="BANK_AC_NO"
															listValue="BANK_AC_NO" headerKey=""
															headerValue="--Select--"
															onchange='bankChange(this.value,"BranchAddressDiv");' />
													</div>
												</s:if>
											</div>

										</div>
										<div id="BranchAddressDiv">
											<s:if test='bankAddress!=null'>
												<div class="boxcontent">
													<div class="textfieldA25 ">
														<s:text name="Account Number" />
													</div>
													<div class="textfieldA75 ">
														<s:property value="bancAccountNo" />
													</div>
													<div class="textfieldA25">
														<s:text name="dn.address" />
													</div>
													<div class="textfieldA75">
														<s:property value="bankAddress" />
													</div>
												</div>
											</s:if>
										</div>

										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="dn.AccName" />
											</div>
											<div class="textfieldA75">
												<s:property value='#session.COMPANY_NAME'/>
											</div>
											<br class="clear" />
										</div>
										<div class="boxcontent">
											<!--  <div class="textfieldA25">
												<s:text name="dn.address" />
											</div>
											<div class="textfieldA75">
												<div id="bankchange" style="display: none;">
													Bank of Baroda
													<br>
													Kampala Branch
													<br>
													P.O. Box 7197
													<br>
													Kampala
												</div>

												<div id="bankchange1" style="display: none;">
													Crane Bank
													<br>
													Kampala Branch
													<br>
													P.O. Box 7197
													<br>
													Kampala
												</div>-->
										</div>
										<br class="clear" />
										<div class="boxcontent">
											<!-- 	<div class="textfieldA25">
													<s:text name="dn.AccNo" />
												</div>
												<div class="textfieldA75" id="AccNo" style="display: none;">
												
													<s:select
														list="#{'1':'95010200002317','3':'145020133300','4':'95010200002565'}"
														headerValue="---Select---" headerKey=""
														onchange="bankChange(this.value);" cssClass="no-print" />
													<span id="bankId1" style="display: none;">
														95010200002317 </span>
													<!--<span id="bankId2" style="display: none;">
														95010200002332 </span>
													-->
											<!--<span id="bankId3" style="display: none;">
														145020133300 </span>
														<span id="bankId4" style="display: none;">
														95010200002565 </span>
														</div>
													<div class="textfieldA75" id="AccNo1" style="display: none;">
														<s:select
														list="#{'2':'95010200002332','5':'95010200002564'}"
														headerValue="---Select---" headerKey=""
														onchange="bankChange(this.value);" cssClass="no-print" />
													<!--<span id="bankId1" style="display: none;">
														95010200002317 </span>
													<span id="bankId3" style="display: none;">
														145020133300 </span>
														-->
											<!--<span id="bankId2" style="display: none;">
														95010200002332 </span>
														<span id="bankId5" style="display: none;">
														95010200002564 </span>
												</div>
												<br class="clear" />
											</div>
											<br> -->
										</div>
										<br class="clear" />
										<s:if test='"DN".equals(type)'>
												Please quote our transaction number above against payment.
											</s:if>
										<br>
										<br>
										<table width="100%">
											<tbody>
												<tr>
													<td>
														<s:text name="Prepared by:" />
														<br>
														<br>
														<br>
														<s:text name="Technical Assistant/Officer" />
													</td>
													<td>
														<s:text name="" />
													</td>
													<td align="right">
														<s:text name="Reviewed by:" />
														<br>
														<br>
														<br>
														<s:text name="Head, Technical Operations" />
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<br>
														<s:property value='#session.COMPANY_NAME'/> |  <s:property value='#session.ADDRESS1'/>, <s:property value='#session.COUNTRY'/>  | <s:property value='#session.EMAIL'/> |Website:<s:property value='#session.WEB_SITE'/>
													</td>
												</tr>
											</tbody>
										</table>

										<br>
										<div class="tablerow" align="center">
											<s:if test='premiumDisplay.equals("premiumDisplay") && (premiumMasterMode==null||!premiumMasterMode.equals("premiumDisplay"))'>
												<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancelTACL()" /> &nbsp;&nbsp;&nbsp;
											</s:if>
											<s:elseif test='premiumDisplay.equals("authenticationDisplay")'>
												<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' />
											</s:elseif>
											<s:else>
												<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel()" />
											&nbsp;&nbsp;&nbsp;
										</s:else>
											<input type="button" value="Print" id="print" class="btn btn-sm btn-primary" onClick="printpage()" />
												
											<s:hidden name="currencyId" />
											<s:hidden name="commission_view" />
											<s:hidden name="tax_view" />
											<s:hidden name="brokerage_view" />
											<s:hidden name="shareSigned" />
											<s:hidden name="layerNo" />
											<s:hidden name="contNo" id="contNo" />
											<s:hidden name="proposal_No" id="proposal_No"/>
											<s:hidden name="departmentId" id="departmentId"/>
											<s:hidden name="sectionNo" id="sectionNo"/>
										</div>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
			</s:else>

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
  </script>
	</body>
</html>

