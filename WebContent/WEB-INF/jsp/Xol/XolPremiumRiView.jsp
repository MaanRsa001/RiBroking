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
																	:&nbsp;&nbsp;&nbsp;<s:property value="Settlementstatus"/>
																</div>
															</div>
															</s:if>
															<br class="clear"/>
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
																<s:if test="!AmendmentDate.equals('')">
																	:&nbsp;&nbsp;&nbsp;<s:property value="AmendmentDate"/>
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
																:&nbsp;&nbsp;&nbsp;<s:property value="Predepartment"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="Premium.Transaction" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="Transaction"/>
															</div>
														</div>
														<s:if test='"3".equals(productId)'>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="Premium.StatementReceivedDate" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="InceptionDate"/>
															</div>
														</div>
														</s:if>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.statementDate" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="StatementDate"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="RiskDetails.InstallmentDate" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="AccountPeriod"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="label.documentType" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="DocumentType"/>
															</div>
														</div>
														<s:if test="'RVP'.equals(account_Period)">
															<div class="textfield" >
																<div class="text txtB">
																	<s:text name="label.transactionChoose" />
																</div>
																<div class="tbox">
																	:&nbsp;&nbsp;&nbsp;<s:property value="ChooseTransaction" />
																													
																	<s:if test="'Yes'.equals(ChooseTransaction)">
																		&nbsp;&nbsp;&nbsp;<s:property value="TransDropDownVal" />
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
																:&nbsp;&nbsp;&nbsp;<s:property value="CedentRef"/>
															</div>
														</div>
														</s:if>
														<div class="textfield">
															<div class="text txtB">
																<s:text name="Payment.Currency" />
															</div>
															<div class="tbox">
																:&nbsp;&nbsp;&nbsp;<s:property value="Currency"/> :&nbsp;<s:property value="ExchRate"/>
															</div>
														</div>
														<div  id="gnpi" style="display:none">
														<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
															<div class="textfield" >
																<div class="text txtB">
																	<s:text name="label.gnpi" />
																</div>
																<div class="tbox">
																		:&nbsp;&nbsp;&nbsp;<s:property value="GnpiDate"/>
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
																:&nbsp;&nbsp;&nbsp;<s:property value="Ricession"/>
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
																<s:property value="CurrencyName" />
															</td>
															<td></td>
															<td class="txtB" align="center">
																<s:text name="Premium.Base" />
															</td>
															<td class="txtB" align="center">
																<s:property value="CurrencyName" />
															</td>
														</tr>
														<tr>
															<td>
																<s:text name="RiskDetails.M&DPremium" />
															</td>
															<td align="right">
																<s:if test="!Mdpremium.equals('0.00')">
																	<s:property value="Mdpremium"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!Mdpremiumusd.equals('0.00')">
																	<s:property value="Mdpremiumusd"/>
																</s:if>
															</td>
															<s:if test='"3".equals(productId)'>
															<td>
																<s:text name="Premium.BroKerage" />
															</td>
															<td align="right">
																<s:if test="!Brokerage.equals('0.00')">		
																	<div style="align:right;"><s:property value="Brokerage"/></div>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!Brokerageusd.equals('0.00')">		
																	<div style="align:right;"><s:property value="Brokerageusd"/></div>
																</s:if>
															</td>
															</s:if>
															<s:else>
															<td>
																	<s:text name="label.tax" />
															</td>
															<td align="right">
																<s:if test="!Tax.equals('0.00')">						
																	<s:property value="Tax"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!Taxusd.equals('0.00')">				
																	<s:property value="Taxusd"/>
																</s:if>
															</td>
															</s:else>
														</tr>
														<tr>										
															<td>
																<s:text name="Premium.AdjustmentPremiun" />
															</td>
															<td align="right">										
																<s:if test="!Adjustmentpremium.equals('0.00')">						
																	<s:property value="Adjustmentpremium"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!Adjustmentpremiumusd.equals('0.00')">				
																	<s:property value="Adjustmentpremiumusd"/>
																</s:if>
															</td>
															<s:if test='"3".equals(productId)'>
															<td>
			                                                    
																	<s:text name="label.taxExpense" />
																	
															</td>
															<td align="right">
																<s:if test="!TaxOc.equals('0.00')">						
																	<s:property value="TaxOc"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!Taxusd.equals('0.00')">				
																	<s:property value="Taxusd"/>
																</s:if>
															</td>
															</s:if>
															<s:else>
															 <td>
																<s:text name="Premium.OtherCost" />
															</td>
															<td align="right">
																<s:if test="!OtherCost.equals('0.00')">
																	<s:property value="OtherCost"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!OtherCostUSD.equals('0.00')">
																<s:property value="OtherCostUSD"/>
																</s:if>
															</td>	
															
															</s:else>
														</tr>
														<tr>
															<td>
																<s:text name="Premium.ReinstatementPremium" />
															</td>
															<td align="right">
																<s:if test="!Recuirementpremium.equals('0.00')">
																<s:property value="Recuirementpremium"/>
																</s:if>
															</td>
															<td align="right">
															    <s:if test="!Recuirementpremiumusd.equals('0.00')">
																<s:property value="Recuirementpremiumusd" />
																</s:if>
															</td>
															<s:if test='"3".equals(productId)'>
															<td>
			                                                    <s:text name="label.WithHoldingTax" />
			                                                </td>
			                                                <td align="right">
			                                                    <s:if test="!WithHoldingTaxOC.equals('0.00')">
			                                                        <s:property value="WithHoldingTaxOC"/>
			                                                    </s:if>
			                                                </td>
			                                                <td align="right">
			                                                    <s:if test="!WithHoldingTaxDC.equals('0.00')">
			                                                        <s:property value="WithHoldingTaxDC"/>
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
																<s:if test="!TaxDedectSource.equals('0.00')">
																	<s:property value="TaxDedectSource"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!TaxDedectSourceDc.equals('0.00')">
																	<s:property value="TaxDedectSourceDc"/>
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
			                                                    <s:if test="!Bonus.equals('0.00')">
			                                                        <s:property value="Bonus"/>
			                                                    </s:if>
			                                                </td>
			                                                <td align="right">
			                                                    <s:if test="!BonusDc.equals('0.00')">
			                                                        <s:property value="BonusDc"/>
			                                                    </s:if>
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
																<s:property value=""/>
															</td>
			                                                <td>
																<s:text name="Premium.OtherCost" />
															</td>
															<td align="right">
																<s:if test="!OtherCost.equals('0.00')">
																	<s:property value="OtherCost"/>
																</s:if>
															</td>
															<td align="right">
																<s:if test="!OtherCostUSD.equals('0.00')">
																<s:property value="OtherCostUSD"/>
																</s:if>
															</td>	
														</tr>
														<tr>
															 <td>
			                                                    <s:text name="label.vat" />
			                                                </td>
			                                                <td align="right">
			                                                    <s:if test="!VatPremiumOc.equals('0.00')">
			                                                        <s:property value="VatPremiumOc"/>
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
														</s:if>	
														
														<tr>
															<td>
																<s:text name="premium.total" />
															</td>
															<td align="right">
																<s:property value="TotalCredit"/>
															</td>
															<td align="right">
																<s:property value="TotalCreditDC"/>
															</td>
															<td>
																<s:text name="premium.total" />
															</td>
															<td align="right">
																<s:property value="TotalDebit"/>
															</td>
															<td align="right">
																<s:property value="TotalDebitDC"/>
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
																<s:property value="NetDue"/>
															</td>
															<td align="right">
																<s:property value="Netdueusd"/>
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
															<s:property value="Remarks"/>
														</div>
														<br class="clear"/>
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
						<s:hidden name="transactionNo" id="transactionNo"/>
						<s:hidden name="layerNo"/>
						<s:hidden name="layerno"/>
						<s:hidden name="contNo" id="contNo"/>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<div class="tablerow" align="center">
			<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel()" /> &nbsp;&nbsp;&nbsp;
			<input type="button" value="Submit" id="print" class="btn btn-sm btn-success"	onClick="fnSubmit()"/>				
		</div>
</div>
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
	 document.xolpremium.action="${pageContext.request.contextPath}/updateRiStatusXolPremium.do";
	 document.xolpremium.submit();
 }
</script>
</body>
</html>
