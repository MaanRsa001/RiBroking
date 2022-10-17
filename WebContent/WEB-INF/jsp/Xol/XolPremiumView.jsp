<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 
<!DOCTYPE html>
<html>
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
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
		  
		//document.forms[0].action="${pageContext.request.contextPath}/premiumListXolPremium.do?mode=add&contNo="+contNo;
		document.xolpremium.action="${pageContext.request.contextPath}/premiumListXolPremium.do?mode=add";
		document.xolpremium.submit();
		}	 	
		function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}
		function cancel1()
		{
		document.xolpremium.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.xolpremium.submit();
		}
		function bankChange(val,searchby){
				var URL="${pageContext.request.contextPath}/ajexlistProportionPremium.do?reqform=branchAddress&bancAccountNo="+val;
				postRequest(URL,searchby);	
		}
		function bankChange1(val){
			//if(val==1){
				//document.getElementById("bankchange1").style.display='none';
				//document.getElementById("bankchange").style.display='block';
				//document.getElementById("bankId1").style.display='block';
				//document.getElementById("bankId2").style.display='none';
				//document.getElementById("bankId3").style.display='none';
			//}else if(val==2){
				//document.getElementById("bankchange1").style.display='none';
				//document.getElementById("bankchange").style.display='block';
				//document.getElementById("bankId2").style.display='block';
				//document.getElementById("bankId1").style.display='none';
				//document.getElementById("bankId3").style.display='none';
			//}
			//else if(val==3){
				//document.getElementById("bankchange").style.display='none';
			//	document.getElementById("bankchange1").style.display='block';
			//	document.getElementById("bankId3").style.display='block';
				//document.getElementById("bankId1").style.display='none';
				//document.getElementById("bankId2").style.display='none';
			//}
			if(val==1){
				document.getElementById("bankchange1").style.display='none';
				document.getElementById("bankchange").style.display='block';
				document.getElementById("bankId1").style.display='block';
				document.getElementById("bankId2").style.display='none';
				document.getElementById("bankId3").style.display='none';
				document.getElementById("bankId4").style.display='none';
				document.getElementById("bankId5").style.display='none';
			}else if(val==2){
				document.getElementById("bankchange1").style.display='none';
				document.getElementById("bankchange").style.display='block';
				document.getElementById("bankId2").style.display='block';
				document.getElementById("bankId1").style.display='none';
				document.getElementById("bankId3").style.display='none';
				document.getElementById("bankId4").style.display='none';
				document.getElementById("bankId5").style.display='none';
			}
			else if(val==3){
				document.getElementById("bankchange").style.display='none';
				document.getElementById("bankchange1").style.display='block';
				document.getElementById("bankId3").style.display='block';
				document.getElementById("bankId1").style.display='none';
				document.getElementById("bankId2").style.display='none';
				document.getElementById("bankId4").style.display='none';
				document.getElementById("bankId5").style.display='none';
			}
			else if(val==4){
				document.getElementById("bankchange").style.display='none';
				document.getElementById("bankchange1").style.display='block';
				document.getElementById("bankId4").style.display='block';
				document.getElementById("bankId1").style.display='none';
				document.getElementById("bankId2").style.display='none';
				document.getElementById("bankId3").style.display='none';
				document.getElementById("bankId5").style.display='none';
			}
			else if(val==5){
				document.getElementById("bankchange").style.display='none';
				document.getElementById("bankchange1").style.display='block';
				document.getElementById("bankId5").style.display='block';
				document.getElementById("bankId1").style.display='none';
				document.getElementById("bankId2").style.display='none';
				document.getElementById("bankId4").style.display='none';
				document.getElementById("bankId3").style.display='none';
			}
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
		function getAddress(val){
			document.getElementById("addr999").innerHTML=document.getElementById("Address_"+val).value;
			//document.getElementById("bro999").style.display='block';
			document.getElementById("bro999").innerHTML=document.getElementById("broker_"+val).value;
		}
		function currencycheck(val,currencyId){
		var URL="${pageContext.request.contextPath}/ajexlistProportionPremium.do?reqform=accountno&currencyId="+val;
		postRequest(URL,currencyId);
		} 
function AuthendicationBack(){
	document.xolpremium.action="${pageContext.request.contextPath}/initAuthentication.do";
	document.xolpremium.submit();
}
	</script>
	<style type="text/css">
	 @media print
			{    
			    .no-print, .no-print *
			    {
			        display: none !important;
			    }
			    .ui-datepicker-trigger { display:none; }
			}
	@page 
        {
            size: legal;   /* auto is the current printer page size */
            margin:0cm;
        }
        @media print { html, body { height: 99%; } }
	</style>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<s:if test='!"DN".equals(type)'>
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
				<s:form name="xolpremium" method="post" theme="simple">
				<div class="panel panel-primary">
					<div class="panel-heading" align="center">
						<s:if test='"3".equals(productId)'>
							<s:text name="label.xolPremium" />
						</s:if>
						<s:if test='"5".equals(productId)'>
							<s:text name="label.retroxolPremium" />
						</s:if>
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
													<div class="text txtB" >
														<s:text name="Premium.ContractNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="contNo"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="RiskDetails.LayerNo" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="layerno"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.UnderwritingYear" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="uwYear"/>
													</div>
												</div>
												<%--<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.ProfitCenter" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="profit_Center"/>
													</div>
												</div>										
												 <div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="" />
													</div>
													<div class="tbox">
														&nbsp;&nbsp;&nbsp;<s:property value=""/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.Sub-Profitcenter" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="subProfit_center"/>
													</div>
												</div>
												
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="RiskDetails.PolicyBranch" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="policyBranch"/>
													</div>
												</div>--%>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Claim.TreatyName" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="treatyName_type"/>
													</div>
												</div>
												<div class="textfield"  style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.DepartmentName" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="department_Name"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
													<s:if test='"3".equals(productId)'>
														<s:text name="Heding.CedingCompany" />
													</s:if>
													<s:if test='"5".equals(productId)'>
														<s:text name="label.leadretro" />
													</s:if>
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="cedingCo"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
													<s:if test='"3".equals(productId)'>
														<s:text name="RiskDetails.Broker" />
													</s:if>
													<s:elseif test='"5".equals(productId)'>
														<s:text name="label.leadbroker" />
													</s:elseif>
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="broker"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.InceptionDate" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="insDate"/>
													</div>
												</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.ExipryDate" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="expDate"/>
													</div>
												</div>
												<s:if test='"3".equals(productId)'>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
														<s:text name="Premium.SettlementStatus" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="settlement_status"/>
													</div>
												</div>
												</s:if>
												<br class="clear"/>
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
													<s:hidden name="brokerage_S" value="%{brokerage_view}"/>
													<td width="25%" class="txtB">
														<s:text name="RiskDetails.BroKerage" />
													</td>
													<td width="25%" align="right">
														<s:property value="brokerage_view"/>
													</td>										
													<td width="25%" class="txtB">
														<s:text name="RiskDetails.Tax" />
													</td>
													<td width="25%" align="right">
														<s:property value="tax_view"/>
													</td>
													<s:hidden name="tax_S" value="%{tax_view}"/>										
												</tr>
												<tr>
													<td width="25%" class="txtB">
														<s:text name="Premium.OtherCostPercent" />
													</td>
													<td width="25%" align="right">
														<s:property value="otherCostView"/>
													</td>
													<td width="25%" class="txtB">
														<s:text name="Premium.EPIOurShare1" />
													</td>
													<td width="25%" align="right">												
														<s:property value="EPI_our_share_view"/>
													</td>											
												</tr>
												<tr>
													<td width="25%" class="txtB">
														<s:text name="Premium.M&DPremium" />
													</td>
													<td width="25%" align="right">
														<s:property value="md_premium_view"/>
													</td>
													<td width="25%" class="txtB">
														<s:text name="Premium.SettlementStatus" />
													</td>
													<td width="25%" align="right">
														<s:property value="settlement_status"/>
													</td>
												</tr>
											</table>									
										</div>
										<br class="clear"/>
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
													<s:if test="!amendmentDate.equals('')">
														:&nbsp;&nbsp;&nbsp;<s:property value="amendmentDate"/>
													</s:if>
													<s:else>
														:&nbsp;&nbsp;&nbsp;NA
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.departmentClass" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="predepartment"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.Transaction" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="transaction"/>
												</div>
											</div>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.StatementReceivedDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="inception_Date"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.statementDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="statementDate"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="RiskDetails.InstallmentDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="account_Period"/>
												</div>
											</div>
											<s:if test="'RVP'.equals(account_Period)">
												<div class="textfield" >
													<div class="text txtB">
														<s:text name="label.transactionChoose" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="chooseTransaction" />
																										
														<s:if test="'Yes'.equals(chooseTransaction)">
															&nbsp;&nbsp;&nbsp;<s:property value="transDropDownVal" />
														</s:if>
													</div>
													
												</div>	
											</s:if>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="premium.cedantRef" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="cedentRef"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Payment.Currency" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="currency"/> :&nbsp;<s:property value="exchRate"/>
												</div>
											</div>
											<div  id="gnpi" style="display:none">
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<div class="textfield" >
													<div class="text txtB">
														<s:text name="label.gnpi" />
													</div>
													<div class="tbox">
															:&nbsp;&nbsp;&nbsp;<s:property value="gnpiDate"/>
													</div>
												</div>
											</s:if>
											</div>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.ricession" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="ri_cession"/>
												</div>
											</div>
											</s:if>
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
												<td></td>
												<td class="txtB" align="center">
													<s:text name="Premium.Base" />
												</td>
												<td class="txtB" align="center">
													<s:property value="currencyName" />
												</td>
												<td></td>
												<td class="txtB" align="center">
													<s:text name="Premium.Base" />
												</td>
												<td class="txtB" align="center">
													<s:property value="currencyName" />
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="RiskDetails.M&DPremium" />
												</td>
												<td align="right">
													<s:if test="!md_premium.equals('0')">
														<s:property value="md_premium"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!md_premium_usd.equals('0')">
														<s:property value="md_premium_usd"/>
													</s:if>
												</td>
												<s:if test='"3".equals(productId)'>
												<td>
													<s:text name="Premium.BroKerage" />
												</td>
												<td align="right">
													<s:if test="!brokerage.equals('0')">		
														<div style="align:right;"><s:property value="brokerage"/></div>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!brokerage_usd.equals('0')">		
														<div style="align:right;"><s:property value="brokerage_usd"/></div>
													</s:if>
												</td>
												</s:if>
												<s:else>
												<td>
														<s:text name="label.tax" />
												</td>
												<td align="right">
													<s:if test="!tax.equals('0')">						
														<s:property value="tax"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!tax_usd.equals('0')">				
														<s:property value="tax_usd"/>
													</s:if>
												</td>
												</s:else>
											</tr>
											<tr>										
												<td>
													<s:text name="Premium.AdjustmentPremiun" />
												</td>
												<td align="right">										
													<s:if test="!adjustment_premium.equals('0')">						
														<s:property value="adjustment_premium"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!adjustment_premium_usd.equals('0')">				
														<s:property value="adjustment_premium_usd"/>
													</s:if>
												</td>
												<s:if test='"3".equals(productId)'>
												<td>
                                                    
														<s:text name="label.taxExpense" />
														
												</td>
												<td align="right">
													<s:if test="!tax.equals('0')">						
														<s:property value="tax"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!tax_usd.equals('0')">				
														<s:property value="tax_usd"/>
													</s:if>
												</td>
												</s:if>
												<s:else>
												 <td>
													<s:text name="Premium.OtherCost" />
												</td>
												<td align="right">
													<s:if test="!otherCost.equals('0')">
														<s:property value="otherCost"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!otherCostUSD.equals('0')">
													<s:property value="otherCostUSD"/>
													</s:if>
												</td>	
												
												</s:else>
											</tr>
											<tr>
												<td>
													<s:text name="Premium.ReinstatementPremium" />
												</td>
												<td align="right">
													<s:if test="!recuirement_premium.equals('0')">
													<s:property value="recuirement_premium"/>
													</s:if>
												</td>
												<td align="right">
												    <s:if test="!recuirement_premium_usd.equals('0')">
													<s:property value="recuirement_premium_usd" />
													</s:if>
												</td>
												<s:if test='"3".equals(productId)'>
												<td>
                                                    <s:text name="label.WithHoldingTax" />
                                                </td>
                                                <td align="right">
                                                    <s:if test="!withHoldingTaxOC.equals('0')">
                                                        <s:property value="withHoldingTaxOC"/>
                                                    </s:if>
                                                </td>
                                                <td align="right">
                                                    <s:if test="!withHoldingTaxDC.equals('0')">
                                                        <s:property value="withHoldingTaxDC"/>
                                                    </s:if>
                                                </td>
                                                </s:if>
                                                <s:if test='"5".equals(productId)'>
                                               <td colspan="3"></td>
												</s:if>			
                                            </tr>
                                            <s:if test='"3".equals(productId)'>
                                            <tr>
                                            <s:if test='"RI02".equals(#session.SOURCE_CODE)'>
                                           		<td>
													<s:text name="label.taxdedect" />
												</td>
												<td align="right">
													<s:if test="!taxDedectSource.equals('0')">
														<s:property value="taxDedectSource"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!taxDedectSourceDc.equals('0')">
														<s:property value="taxDedectSourceDc"/>
													</s:if>
												</td> 
												</s:if>
												<s:else>
												<td colspan="3"></td>
												</s:else>
												<td>
                                                    <s:text name="label.BonusVal" />
                                                </td>
                                                <td align="right">
                                                    <s:if test="!bonus.equals('0')">
                                                        <s:property value="bonus"/>
                                                    </s:if>
                                                </td>
                                                <td align="right">
                                                    <s:if test="!bonusDc.equals('0')">
                                                        <s:property value="bonusDc"/>
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
                                                        <s:property value="serviceTax"/>
                                                    </s:if>
                                                </td>
                                                <td align="right">
                                                    <s:if test="!serviceTaxDc.equals('0')">
                                                        <s:property value="serviceTaxDc"/>
                                                    </s:if>
                                                </td>
                                                </s:if><s:else>
                                                <td colspan="3"></td>
                                                </s:else>
                                                <td>
													<s:text name="Premium.OtherCost" />
												</td>
												<td align="right">
													<s:if test="!otherCost.equals('0')">
														<s:property value="otherCost"/>
													</s:if>
												</td>
												<td align="right">
													<s:if test="!otherCostUSD.equals('0')">
													<s:property value="otherCostUSD"/>
													</s:if>
												</td>	
											</tr>
											</s:if>	
											
											<tr>
												<td>
													<s:text name="premium.total" />
												</td>
												<td align="right">
													<s:property value="totalCredit"/>
												</td>
												<td align="right">
													<s:property value="totalCreditDC"/>
												</td>
												<td>
													<s:text name="premium.total" />
												</td>
												<td align="right">
													<s:property value="totalDebit"/>
												</td>
												<td align="right">
													<s:property value="totalDebitDC"/>
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="" />
												</td>
												<td align="right">
													<s:property value=""/>
												</td>
												<td align="right">
													<s:property value="" />
												</td>
												<td>
													<s:text name="Premium.NetDue" />
												</td>
												<td align="right">
													<s:property value="netDue"/>
												</td>
												<td align="right">
													<s:property value="net_due_usd"/>
												</td>
											</tr>									
											</tbody>
										</table>
									</div>
								</div>
							</div>
							
							<div class="tablerow">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="premium.remarks" />
											</div>
											<div class="textfieldA75">
												<s:property value="remarks"/>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							 <s:if test='"3".equals(productId)'>
							<div class="tablerow">
									<div class="boxcontent">
										<div class="panel panel-primary">		
											<div class="panel-heading">
												Allocation Details
											</div>
										<div class="panel-body">
										<display:table name="allocatedList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record2">
										<display:setProperty name="basic.msg.empty_list" value="None"/>
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found"	value="" />
										<display:setProperty name="paging.banner.placement" value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column  style="text-align:center;" title="Allocation No." property="serial_no" />
										<display:column  style="text-align:center;" title="Allocation Date" property="allocateddate" />
										<display:column  style="text-align:center;" title="Business Type" property="productname" />								
										<display:column  style="text-align:center;" title="Type">								
										PREMIUM														
										</display:column>
										<display:column  style="text-align:center;" title="Sign">														
										<s:if test='"RT".equals(allocateType)'>							
										+
										</s:if>								
										<s:if test='"PT".equals(allocateType)'>							
										-
										</s:if>															
										</display:column>		
										<display:column  style="text-align:center;" title="Amount" property="payamount"/>								
										<display:column  style="text-align:center;" title="Status" property="status" />
										<display:column  style="text-align:center;" title="Receipt/Payment No" property="pay_rec_no" />
										<display:column  style="text-align:center;" title="Settlement Type" property="settlementType" />																
										</display:table>	
										<s:if test='(!"".equals(totalAmount) || null != totalAmount)&&allocatedList.size()>0'>
										<br class="clear" />
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
							</s:if>			
							<div class="tablerow" align="center">
							<s:if test='premiumDisplay.equals("premiumDisplay") && (premiumMasterMode==null||!premiumMasterMode.equals("premiumDisplay"))'>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
							</s:if>
							<s:elseif test='premiumDisplay.equals("authenticationDisplay")'>
								<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' />
							</s:elseif>
							<s:else>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel()" /> &nbsp;&nbsp;&nbsp;
								</s:else>
								<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>				
								<s:hidden name="currencyId" />
								<s:hidden name="commission_view" />
								<s:hidden name="tax_view" />
								<s:hidden name="brokerage_view" />
								<s:hidden name="shareSigned" />
								<s:hidden name="layerNo"/>
								<s:hidden name="layerno"/>
								<s:hidden name="contNo" id="contNo"/>
								<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>
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
								<s:form name="xolpremium" method="post" theme="simple">
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
											</s:if><s:else>
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
															<div class="tbox" style="width: 75%" >
																:&nbsp;&nbsp;&nbsp;
																<s:select list="brokerAndCedingName"
																	listKey="CUSTOMER_ID" listValue="BROKER" 
																	headerValue="---Select---" headerKey="" name="broker" cssClass="no-print"
																	onchange="getAddress(this.value);" />
																	<span id="bro999" >
																<s:property value="broker"  />
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
															<s:iterator value="brokerAndCedingName" var="commodityVar" status="stat">
															<s:hidden name="Address[%{#stat.count-1}]" id="Address_%{#commodityVar.CUSTOMER_ID}" value="%{#commodityVar.Address}" />
															<s:hidden name="broker[%{#stat.count-1}]" id="broker_%{#commodityVar.CUSTOMER_ID}" value="%{#commodityVar.BROKER}" />
															</s:iterator>
															<span id="addr999">
																<s:property value="Address"  />
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<br>
										<br>
										<table width="100%" class="table table-bordered" border="5">
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
													<td align="right" width="25%" >
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
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.UnderwritingYear" />
													</td>
													<td align="right" width="25%">
														<s:property value="uwYear" />
													</td>

												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.SubClass" />
													</td>
													<td align="right">
														<s:property value="subProfit_center" />
													</td>
													<td style="font-weight: bold">
														<s:text name="Premium.ShareSigned" />
													</td>
													<td align="right">
														<s:property value="shareSigned" />
													</td>
												</tr>

												<tr>
													<td style="font-weight: bold">
														<s:text name="Claim.TreatyName" />
													</td>
													<td align="right">
														<s:property value="treatyName_type" />
													</td>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.LayerNo" />
													</td>
													<td align="right">
														<s:property value="layerno" />
													</td>
												</tr>

												<tr>
													<td style="font-weight: bold">
														<s:text name="Heding.CedingCompany" />
													</td>
													<td align="right">
														<s:property value="cedingCo" />
													</td>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.Broker" />
													</td>
													<td align="right">
														<s:property value="broker" />
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.InceptionDate" />
													</td>
													<td align="right">
														<s:property value="insDate" />
													</td>
													<td style="font-weight: bold">
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
													<td  width="25%" align="right">
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
														<s:text name="RiskDetails.InstallmentDate" />
													</td>
													<td align="right">
														<s:property value="account_Period" />
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
												<s:if test='!"DN".equals(type)'>
													<tr>
														<td></td>
														<td class="txtB" align="center">
															<s:text name="Premium.Base" />
														</td>

														<td class="txtB" align="center">
															<s:property value="currencyName" />
														</td>
														<td></td>
														<td class="txtB" align="center">
															<s:text name="Premium.Base" />
														</td>
														<td class="txtB" align="center">
															<s:property value="currencyName" />
														</td>
													</tr>
												</s:if>
												<tr>
													<td width="25%" style="font-weight: bold">
														<s:text name="RiskDetails.M&DPremium" />
													</td>
													<td align="right" width="25%">
														<s:if test="!md_premium.equals('0')">
															<s:property value="md_premium" />
														</s:if>
													</td>
													<td width="25%" style="font-weight: bold">
														<s:text name="Premium.BroKerage" />
													</td>
													<td align="right" width="25%">
														<s:if test="!brokerage.equals('0')">
															<div style="align: right;">
																<s:property value="brokerage" />
															</div>
														</s:if>
													</td>
												</tr>
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.AdjustmentPremiun" />
													</td>
													<td align="right">
														<s:if test="!adjustment_premium.equals('0')">
															<s:property value="adjustment_premium" />
														</s:if>
													</td>
													<td style="font-weight: bold">
														<s:text name="RiskDetails.Tax" />
													</td>
													<td align="right">
														<s:if test="!tax.equals('0')">
															<s:property value="tax" />
														</s:if>
													</td>
												</tr>
												
												<tr>
													<td style="font-weight: bold">
														<s:text name="Premium.ReinstatementPremium" />
													</td>
													<td align="right">
														<s:if test="!recuirement_premium.equals('0')">
															<s:property value="recuirement_premium" />
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
														<s:text name="label.taxdedect" />
													</td>
													<td align="right">
														<s:if test="!taxDedectSource.equals('0')">
															<s:property value="taxDedectSource"/>
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
	                                                    <s:text name="label.sertax" />
	                                                </td>
	                                                <td align="right">
	                                                    <s:if test="!serviceTax.equals('0')">
	                                                        <s:property value="serviceTax"/>
	                                                    </s:if>
	                                                </td>
												<td></td>
												<td></td>
												
												
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
																</s:if><s:else>
																<s:text name="Premium.BalDueFrom" />
																</s:else>
														</td>
														<td colspan="3" align="right">
															<s:if test="creditsign<0">
																<!--<s:property value="creditsign*(-1)" />-->
																<s:property value="getText('{0,number,#,##0.00}',{creditsign*(-1)})"/>
															</s:if><s:else>
																<s:property value="netDue" />
															</s:else>
														</td>
													</tr>
												</s:if>
											</tbody>
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
										<s:textfield name="dueDate" id="dueDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" />
										<!--<s:property value="dueDate"/>
										--></div>
										</div>
										<br>
										<br>
										<br class="clear" />
										<div class="boxcontent" >
										
										<span id="currValUGX1" style="display: none;">All payments to be made to our UGX Accounts as follows:</span>
										<span id="currValUSD1" style="display: none;">All payments to be made to our USD Accounts as follows:</span>
										
										</div>
										<br>
										<br>
										<div class="boxcontent">
											<div class="textfieldA25 no-print">
												<s:text name="dn.currency" />
											</div>
											<div class="textfieldA75" id="currencyId">
													<s:select list="currencyList"
													headerValue="---Select---" headerKey="" listKey="CURRENCY_ID" listValue="SHORT_NAME"
													onchange='currencycheck(this.value,"AccountCodeDiv");' cssClass="no-print" />
													<div >
													</div>													
												
											</div>											
											<br class="clear" />
										</div>
											
										<div id = "AccountCodeDiv">
											<div class="boxcontent">
												<s:if test='accountList!=null'>
												<div class="textfieldA25">
															<s:text name="Currency Name" />
														</div>
														<div class="textfieldA75">
															<s:property value="currencyName"/>
														</div>
													<div class="textfieldA25 no-print">
														<s:text name="dn.AccNo" />
													</div>
													<div class="textfieldA75">
														<s:select list="accountList" name="accountId" id="accountId" cssClass="no-print" listKey="BANK_AC_NO" listValue="BANK_AC_NO"  headerKey="" headerValue="--Select--" onchange='bankChange(this.value,"BranchAddressDiv");'/>
													</div>
												</s:if>
											</div>
											
										</div>
										<div id = "BranchAddressDiv">
											<s:if test='bankAddress!=null'>
												<div class="boxcontent">
													<div class="textfieldA25">
															<s:text name="Account Number" />
														</div>
														<div class="textfieldA75">
															<s:property value="bancAccountNo"/>
														</div>
														<div class="textfieldA25">
															<s:text name="dn.address" />
														</div>
														<div class="textfieldA75">
															<s:property value="bankAddress"/>
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
										<!--  <div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="dn.currency" />
											</div>
											<div class="textfieldA75" id="currencyId">
												<s:select list="#{'1':'UGX','2':'USD'}"
													headerValue="---Select---" headerKey=""
													onchange="currencyChange(this.value);" cssClass="no-print" />
												<span id="currValUGX" style="display: none;"> UGX </span>
												<span id="currValUSD" style="display: none;"> USD </span>
											</div>
											<br class="clear" />
										</div>
										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="dn.AccName" />
											</div>
											<div class="textfieldA75">
												Uganda Reinsurance Company Limited
											</div>
											<br class="clear" />
										</div>-->
										<!-- <div class="boxcontent">
											<div class="textfieldA25">
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
												</div>
											</div>
											<br class="clear" />
											<div class="boxcontent">
												<div class="textfieldA25">
													<s:text name="dn.AccNo" />
												</div>
												<div class="textfieldA75" id="AccNo" style="display: none;">
													<s:select
														list="#{'1':'95010200002317','3':'145020133300'}"
														headerValue="---Select---" headerKey=""
														onchange="bankChange(this.value);" cssClass="no-print" />
													<span id="bankId1" style="display: none;">
														95010200002317 </span>
													<!--<span id="bankId2" style="display: none;">
														95010200002332 </span>
													<span id="bankId3" style="display: none;">
														145020133300 </span>
														</div>
													<div class="textfieldA75" id="AccNo1" style="display: none;">
														<s:select
														list="#{'2':'95010200002332'}"
														headerValue="---Select---" headerKey=""
														onchange="bankChange(this.value);" cssClass="no-print" />
													<!--<span id="bankId1" style="display: none;">
														95010200002317 </span>
													<span id="bankId2" style="display: none;">
														95010200002332 </span>
													<!--<span id="bankId3" style="display: none;">
														145020133300 </span>
														
												</div>
												<br class="clear" />
											</div>
											<br> 
										</div>-->
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
										<s:if test='premiumDisplay.equals("premiumDisplay")'>
										<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
										</s:if>
										<s:else>
											<input type="button" value="Back" id="back"
												class="btn btn-sm btn-danger" onclick="cancel()"/>
												</s:else>
											&nbsp;&nbsp;&nbsp;
											<input type="button" value="Print" id="print"
												class="btn btn-sm btn-primary" onClick="printpage()" />
											<s:hidden name="currencyId" />
											<s:hidden name="commission_view" />
											<s:hidden name="tax_view" />
											<s:hidden name="brokerage_view" />
											<s:hidden name="shareSigned" />
											<s:hidden name="layerNo" />
											<s:hidden name="layerno" />
											<s:hidden name="contNo" id="contNo" />
											<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>
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
getGnpi('<s:property value="account_Period"/>');
 function getGnpi(val){
 
	 if ("AP"==val){
		 document.getElementById("gnpi").style.display="inline";
	 }
	 else{
	  document.getElementById("gnpi").style.display="none";
	 }
	 }
</script>
</body>
</html>
