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
								<s:form name="retPrem" action="" method="post" theme="simple">
									<div class="panel panel-primary">
										<div class="panel-heading" align="center">
											<s:text name="label.protreatyPremium" />
										</div>
										<div class="panel-body">
											<div class="table2">
												
												<div class="tablerow">
													<div class="panel panel-primary">											
									<div class="panel-heading">
										Transaction Details
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Transaction No " />
												</div>
												<div class="tbox">
													<s:property value="transactionNo" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.AmendmentDate" />
												</div>
												<div class="tbox">
													
													<s:if test="!amendmentDate.equals('')">
													<s:property value="amendmentDate" />
													</s:if>
													<s:else>
													NA
													</s:else>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.Class" />
												</div>
												<div class="tbox">
													<s:property value="PremiumClass"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.subClass" />
												</div>
												<div class="tbox" >
												<s:property value="premiumSubClass"/>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.transactionDate" />
												</div>
												<div class="tbox">
												<s:property value="transaction"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.uwyear" />
												</div>
												<div class="tbox">
															<s:property value="uwYear"/>										
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB ">
													<s:text name="label.retroContractNo" />
												</div>
												<div class="tbox" id="contractList">
												<s:property value="contNo"/>														
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.leadretrocessionary" />
												</div>
												<div class="tbox">
												<s:property value="leadRetro"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.leadbroker" />
												</div>
												<div class="tbox">
												<s:property value="leadBroker"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.treatyName" />
												</div>
												<div class="tbox">
												<s:property value="treatyName"/>
												</div>
											</div>
											
											
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.referenceDetails" />
												</div>
												<div class="tbox">
												<s:property value="reference"/>													
												</div>
											</div>
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
												<s:property value="currency"/>
													<span >
													<s:property value="exchRate"/>
													</span>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Business Type" />
												</div>
												<div class="tbox">
												<s:property value="businessType"/>													
												</div>
											</div>
											<br class="clear"/>
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
																		
																			<s:text name="label.commission" />
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
																	</s:else>
																		<%-- <td>
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
																		<td colspan="1">
																			<s:text name="premium.remarks" />
																		</td>
																		<td colspan="5">
																			<s:property value="remarks" />
																		</td>
																	</tr>
																	<tr>
																		<td >
																			<s:text name="premium.OSClaimsLossUpdate" />  <span id="accountPrd" style="display:none"> </span>
																			
																		</td>
																		<td align="right" >
																			<s:property value="osClaimsLossUpdateOC" />
																		</td>
																		<td align="right">
																			<s:property value="osClaimsLossUpdateDC" />
																		</td>
																		<td align="right"></td>
																		<td align="right"></td>
																		<td align="right"></td>
																	</tr>
																		
															</table>
														</div>
													</div>
												</div>
												<div class="tablerow" align="center">
												
														<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
													<input type="button" value="Print" id="print"
														class="btn btn-sm btn-primary" onClick="printpage()" />
													
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
			
		</div>
<script language="JavaScript" type="text/javascript">
function cancel1(){
	document.retPrem.action='${pageContext.request.contextPath}/retroPremListRetroAdj.action';
	document.retPrem.submit();
}
  </script>
	</body>
</html>

