<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript">
	 $(function() {
		$( "#transaction" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#amendmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#inception_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  $( "#statementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="" name="premium" theme="simple" action="insertPremiumXolPremium"	method="post" autocomplete="off">					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">
								<div class="panel-heading">
										Contract Details
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox">
													<s:property value="contNo"/>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="" />
												</div>
												<div class="tbox">
													<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
													<s:hidden name="amendId"/>
													<s:if test='"3".equals(productId)'>
														 <input type="button" class="btn btn-xs btn-info" value="View Stats & Calc " style="cursor: pointer;" onclick="funStatisticMode('<s:property value="proposal_No" />','<s:property value="contNo" />','<s:property value="departmentId" />','<s:property value="layerno" />')" />
													</s:if>
												</div>
											</div>
											
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
													<s:property value="layerno"/>
												</div>
											</div>
											
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.underwritingYear" />
												</div>
												<div class="tbox">
													<s:property value="uwYear"/>
												</div>
											</div>		
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.treatyName" />
												</div>
												<div class="tbox">
													<s:property value="treatyName_type"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.DepartmentName" />
												</div>
												<div class="tbox">
													<s:property value="department_Name"/>
												</div>
											</div>
											
											<%--<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.profitCenter" />
												</div>
												<div class="tbox">
													<s:property value="profit_Center"/>
												</div>
											</div>
											
											 <div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.subProfitCenter" />
												</div>
												<div class="tbox">
													<s:property value="subProfit_center"/>
												</div>
											</div>
																				
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.policyBranch" />
												</div>
												<div class="tbox">
													<s:property value="policyBranch"/>
												</div>
											</div>--%>
											
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:if test='"3".equals(productId)'>
														<s:text name="label.cedingCompany" />
													</s:if>
													<s:elseif test='"5".equals(productId)'>
														<s:text name="label.leadretro" />
													</s:elseif>
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:if test='"3".equals(productId)'>
														<s:text name="label.broker" />
													</s:if>
													<s:elseif test='"5".equals(productId)'>
														<s:text name="label.leadbroker" />
													</s:elseif>
													
												</div>
												<div class="tbox">
													<s:property value="broker"/>
												</div>
											</div>
											
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.inceptionDate" />
												</div>
												<div class="tbox">
													<s:property value="insDate"/>
													<s:hidden name="insDate" />
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.expiryDate" />
												</div>
												<div class="tbox">
													<s:property value="expDate"/>
													<s:hidden name="expDate" />
												</div>
											</div>
											
											<br class="clear"/>
										</div>
									</div>
									<br class="clear"/>									
									<div class="panel-body">
										<div class="boxcontent">
										
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.brokerageP" />
												</div>
												<div class="tbox">
													<s:property value="brokerage_view"/>
													<s:hidden name="brokerage_S" value="%{brokerage_view}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.taxP" />
												</div>
												<div class="tbox">
													<s:property value="tax_view"/>
													<s:hidden name="tax_S" value="%{tax_view}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.otherCostP" />
												</div>
												<div class="tbox">
													<s:property value="otherCostView"/>
													<s:hidden name="otherCost_S" value="%{otherCostView}" />
												</div>
											</div>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.EPIOSOC1" /> - <s:property value="currencyName"/>
												</div>
												<div class="tbox">
													<s:property value="ePI_our_share_view"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.MDPremiumOurShare" /> - <s:property value="currencyName"/>
												</div>
												<div class="tbox">
													<s:property value="md_premium_view"/>
												</div>
											</div>
											</s:if>
											<%-- <div class="textfield">
												<div class="text txtB">
													<s:text name="label.settlementStatus" />
												</div>
												<div class="tbox">
													Pending
													<s:hidden name="settlement_status" value="Pending"/>
												</div>
											</div>--%>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.areYouEntering" />
												</div>
												<div class="tbox">
													<s:radio list="#{'1':'100%'}" name="enteringMode" id="enteringMode"/>													
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.signed" />
												</div>
												<div class="tbox">
													<s:radio name="enteringMode" list="#{'2':''}" value="2" id="enteringMode"/>
													<s:property value="shareSigned"/>
													<s:text name="Premium.percentage"/>																							
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										Transaction Details
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
												<div class="text">
													<s:text name="label.departmentClass" />
												</div>
												<div class="tbox">													
													<s:select name="predepartment" id="predepartment" list="predepartmentList" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" />													
												</div>
											</div>
											<s:if test='"edit".equals(mode) && !"Temp".equals(tableType)'>
												<div class="textfield">
												<div class="text">
													<s:text name="label.amendmentDate" />
												</div>
												<div class="tbox">
													<s:textfield name="amendmentDate" id="amendmentDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />													
												</div>
											</div>
											</s:if>
											<s:else>
											<div class="textfield"></div>
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDate" />
												</div>
												<s:if test='"edit".equals(mode)'>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();GetStatementRecDate();" disabled="true" />												
												</div>
												</s:if>
												<s:else>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();GetStatementRecDate();getTransactionDropDown('','trans','');"  />												
												</div>
												</s:else>
											</div>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementReceivedDate" />
												</div>
												<div class="tbox">
													<s:textfield name="inception_Date" id="inception_Date"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetStatementDate();"/>											
												</div>
											</div>
											</s:if>
											<s:else>
											<s:hidden name="inception_Date" id="inception_Date"/>
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementDate" />
												</div>
												<div class="tbox">
													<s:textfield name="statementDate" id="statementDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this);"  />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.installmentDate" />
												</div>
												<div class="tbox">	
													<s:if test="'edit'!=mode">
														<s:select list="mdList" listKey="KEY1" listValue="VALUE" headerKey="" headerValue="---Select---" name="account_Period" id="account_Period" cssClass="inputBoxS" onchange="GetMdInstalment(this.value);getGnpi(this.value);getDateDisable(this.value);xolNetDue();getTrasView(this.value);getTransactionData();" />
													</s:if>
													<s:else>
														<s:textfield name="account_Period" id="account_Period" readonly="true" cssClass="inputBox" />
													</s:else>													
												</div>
											</div>
											<div class="textfield" id="transactionView" style="display:none">
												<div class="text">
													<s:text name="label.transactionChoose" />
												</div>
												<div class="tbox" id="trans">
													<s:radio list="#{'Yes':'Yes','No':'No'}" name="chooseTransaction" id="chooseTransaction" value="%{(chooseTransaction==null || chooseTransaction=='')?'Yes':chooseTransaction}" onchange="getTransactionDropDown(this.value,'trans');" disabled='mode.equals("edit")?true:false'/>												
													<s:hidden name="transDropDownVal" id="transDropDownVal"/>
												</div>
												
											</div>	
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedantBrokerDocReference" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="cedentRef" maxlength="100"/>												
												</div>
											</div>	
											</s:if>
											<s:else>
											<s:hidden name="cedentRef" id="cedentRef"/>
											</s:else>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
													<s:select name="currency" id="currId" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" cssStyle="width:30%;float:left;" headerValue="---Select---" disabled="true"/>												
													<span id="exchRate">
													<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:left;text-align:right;" />	
													</span>
													
												</div>
											</div>
											<div  id="gnpi" style="display:none">
												<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<div class="textfield">
													<div class="text">
														<s:text name="label.gnpi" />
													</div>
													<div class="tbox">
														<!--  <div class="inputAppend">
															<sj:datepicker name="amendmentDate" id="amendmentDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />
														</div>-->
															<s:select list="gNPIList" listKey="KEY1" listValue="VALUE" headerKey="" headerValue="---Select---" name="gnpiDate" cssClass="inputBoxS"  onchange="GetAdjustmentPremium();CalculationXOL();"/>
													</div>
												</div>
												</s:if>
											</div>
											<s:if test='"3".equals(productId)'>
											<div class="textfield">
												<div class="text">
													<s:text name="label.ricession" />
												</div>
												<div class="tbox">													
													<s:radio list="#{'Yes':'Yes','No':'No'}" name="ri_cession" id="ri_cession"  value="%{ri_cession==null || ri_cession==''?'Yes':ri_cession}" disabled="true"/>													
												</div>
											</div>
											</s:if>
											<s:else>
											<s:hidden name="ri_cession" id="ri_cession"/>
											</s:else>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="footable">
												<thead>
												<tr>
													<th width="25%"></th>
													<th width="25%"><s:text name="label.credit" /></th>
													<th width="25%"></th>
													<th width="25%"><s:text name="label.debit" /></th>													
												</tr>
												</thead>
												<tbody>												
												<tr>
													<td>
														<s:text name="label.MDPremium" />
													</td>
													<td>
														<div id="mdpremium" >
														<s:textfield name="md_premium" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);CalculationXOL(this.value);xolNetDue();" onblur="this.value=Comma(this.value);"  onclick="this.select();" cssClass="inputBox" cssStyle="text-align:right;"  value="%{md_premium==null?'0.00':md_premium}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
														</div>
													</td>
													<s:if test='"3".equals(productId)'>
													<td>
														<s:text name="label.brokerage" />
													</td>
													<td>
														<s:textfield name="brokerage" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();"   onblur="this.value=Comma(this.value);" onclick="this.select();" id="BrokerAgeXol" cssStyle="text-align:right;" cssClass="inputBox"  maxlength="26" value="%{brokerage==null?'0.00':brokerage}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													</s:if>
													<s:else>
													 <s:hidden name="brokerage" id="BrokerAgeXol" value="0"/>
													 <td>
														<s:text name="label.tax" />
													</td>
													<td>
														<s:textfield name="tax" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();"  onblur="this.value=Comma(this.value);" onclick="this.select();" cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{tax==null?'0.00':tax}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													</s:else>
												</tr>
												<tr>
													<td id="AP1">
														<s:text name="label.adjustmentPremium" />
													</td>
													<td id="adjPrem">
														<s:textfield name="adjustment_premium" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);CalculationXOL(this.value);xolNetDue();"  onblur="this.value=Comma(this.value);" onclick="this.select();" cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{adjustment_premium==null?'0.00':adjustment_premium}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													<s:if test='"3".equals(productId)'>
													<td>
														
														<s:text name="label.taxExpense" />
														
													</td>
													<td>
														<s:textfield name="tax" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();" onblur="this.value=Comma(this.value);" onclick="this.select();"  cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{tax==null?'0.00':tax}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													</s:if>
													<s:else>
													<s:if test='"5".equals(productId)'>
													<td>
														<s:text name="label.otherCost" />
													</td>
													<td>
														<s:textfield name="otherCost" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();"  onblur="this.value=Comma(this.value);" onclick="this.select();" cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{otherCost==null?'0.00':otherCost}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>	
													</s:if>	
													</s:else>
												</tr>
												<tr>
													<s:hidden name="adjustment_premium_temp" />
													<td>
														<s:text name="label.reinstatementPremium" />
													</td>
													<td>
														<s:textfield name="recuirement_premium" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);ReInstmentPremium(this.value);xolNetDue();"  onblur="this.value=Comma(this.value);" onclick="this.select();" cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{recuirement_premium==null?'0.00':recuirement_premium}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													<s:if test='"3".equals(productId)'>
													<td>
														<s:text name="label.WithHoldingTax" />
													</td>
													<td>
                                                        <s:textfield name="withHoldingTaxOC" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align:right;"  onclick="this.select();" cssClass="inputBox" maxlength="26" value="%{withHoldingTaxOC==null?'0.00':withHoldingTaxOC}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
                                                    </td>
													</s:if>
													<s:else>
													<s:hidden name="withHoldingTaxOC" value="0"/>
													</s:else>
													<s:if test='"5".equals(productId)'>
													<td colspan="2"></td>
													</s:if>		
                                                </tr>
                                                <s:if test='"3".equals(productId)'>
                                                <tr>
                                                	<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
													<td><s:text name="label.taxdedect" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="taxDedectSource" id="taxDedectSource" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();setCalculateVal();"  onblur="this.value=Comma(this.value);"  cssStyle="text-align: right;" onclick="this.select();" value="%{taxDedectSource==null?'0.00':taxDedectSource}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>														
													</td>
													</s:if>
													<s:else>
													<td colspan="2">
													<s:hidden name="taxDedectSource" id="taxDedectSource" value="0"/>
													</td>
													</s:else>
													
													<td> <s:text name="label.BonusVal" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="bonus" id="bonus" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{bonus==null?'0.00':bonus}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
														<input type="button"  size="2"  value="..." onclick="BonusPopup()" class="pullRight"/>
													</td>	
                                                </tr>
												<tr>
												<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
													<td><s:text name="label.gst" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="serviceTax" id="serviceTax" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();setCalculateVal();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{serviceTax==null?'0.00':serviceTax}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>														
													</td>
												</s:if>
												<s:else>
													<td colspan="2">
													<s:hidden name="serviceTax" id="serviceTax" value="0"/>
													</td>
												</s:else>
													<td>
														<s:text name="label.otherCost" />
													</td>
													<td>
														<s:textfield name="otherCost" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);xolNetDue();" onblur="this.value=Comma(this.value);" onclick="this.select();"   cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{otherCost==null?'0.00':otherCost}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>	
												</tr>
												</s:if>	
												<s:else>
													<s:hidden name="bonus" id="bonus" value="0"/>
												</s:else>
												<tr>
													<td>
														<s:text name="label.total" />
													</td>
													<td>
														<s:textfield name="totalCredit" cssClass="inputBox" readonly="true" onclick="allow2DigitDecValues(this);middleMinusRestriction(this);this.select();"  onkeyup="allow2DigitDecValues(this);" cssStyle="text-align: right;" value="%{totalCredit==null?'0.00':totalCredit}" />
													</td>
													<td>
														<s:text name="label.total" />
													</td>
													<td>
														<s:textfield name="totalDebit" cssClass="inputBox" cssStyle="text-align: right;" readonly="true" onclick="allow2DigitDecValues(this);middleMinusRestriction(this);this.select();"  onkeyup="allow2DigitDecValues(this);" value="%{totalDebit==null?'0.00':totalDebit}"/>
													</td>
												</tr>
												<tr>
													<td colspan="2">&nbsp;</td>
													<td>
														<s:text name="label.netDue" />
													</td>
													<td>
														<s:textfield name="netDue" cssClass="inputBox" cssStyle="text-align: right;"  readonly="true"  onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);" onclick="allow2DigitDecValues(this);this.select();" value="%{netDue==null?'0.00':netDue}"/>
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="label.remarks" />
													</td>
													<td colspan="3">
														<s:textarea rows="3" id="remarks" name="remarks" cssClass="inputBoxA" cssStyle="width: 100%;" />
														<br/>
														<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
													</td>
												</tr>												
												</tbody>
											</table>																					
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>							
						</div>
						
						<s:hidden name="receipt_no" />
													
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<s:if test='premiumDisplay.equals("premiumDisplay")'>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
								<s:if test='#session.MenuRights.indexOf("PST")!=-1 || #session.MenuRights.indexOf("AST")!=-1'>	
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate1('Submit')" />		&nbsp;&nbsp;&nbsp;						
								</s:if>
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
							</s:if>
							<s:else>
								<s:if test="size == 'list'">
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='back("<s:property value="premiumMasterMode"/>")' /> &nbsp;&nbsp;&nbsp;
								</s:if>								
								<s:if test='premiumDisplay.equals("authenticationDisplay")'>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='#session.MenuRights.indexOf("PST")!=-1 || #session.MenuRights.indexOf("AST")!=-1'>								
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate1('Submit');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>						
									<!--<s:submit name="submit" value="Submit" cssClass="btn btn-sm btn-success"/>-->
								<s:if test="size != 'list'">
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="cancel()" />	
								</s:if>											
							</s:else>
							</div>
						</div>		
						<s:hidden name="transactionNo" id="transactionNo"/>					
						<s:hidden name="proposalno"/>
						<s:hidden name="proposal_No" id="proposal_No"/>
						<s:hidden name="size"/>
						<s:hidden name="baseLayer"/>
						<s:hidden name="layerProposalNo"/>						
						<s:hidden name="contNo" id="contNo" />						
						<s:hidden name="mode" id="mode" />
						<s:hidden name="baseCurrencyId" />
						<s:hidden name="baseCurrencyName" />
						<s:hidden name="currencyId" />
						<s:hidden name="commission_view" />
						<s:hidden name="tax_view" />
						<s:hidden name="overRider_view" />
						<s:hidden name="brokerage_view" />
						<s:hidden name="shareSigned" />
						<s:hidden name="layerno" id="layerno"/>
						<s:hidden name="amendId" />
						<s:hidden name="commission" value="0" />
						<s:hidden name="layerNo" value="%{layerno}" />
						<s:hidden name="menuId" id="menuId"/>
						<s:hidden name="ePI_our_share_view"/>
						<s:hidden name="previousPremium" id="previousPremium"/>
						<s:hidden name="contractPremium" id="contractPremium"/>	
						<s:hidden name="departmentId"/>	
						<s:hidden name="conPremiumOC" id="conPremiumOC"/>
						<s:hidden name="conClaimPaidOC" id="conClaimPaidOC"/>
						<s:hidden name="conClaimOutStandingOC" id="conClaimOutStandingOC"/>
						<s:hidden name="conClaimRatioOC" id="conClaimRatioOC"/>
						<s:hidden name="conbonusOC" id="conbonusOC"/>
						<s:hidden name="conbonusPaidOC" id="conbonusPaidOC"/>
						<s:hidden name="conbonusAdjOC" id="conbonusAdjOC"/>	
						<s:hidden name="disableStatus" id="disableStatus"/>
						<s:hidden name="CalculateTax" id="CalculateTax"/>
						<s:hidden name="premiumDisplay" id="premiumDisplay"/>
						<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>
						<s:hidden name="multiuserError" id="multiuserError"/>
						<s:hidden name="tableType" id="tableType"/>	
						<s:hidden name="requestNo" id="requestNo"/>
						<s:hidden name="buttonStatus" id="buttonStatus"/>
						<s:hidden name="acceptenceDate" id="acceptenceDate"/>
					</div>
						<div id="premiumSubmit"></div>				
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
 function xolNetDue()
		{
		//var mode=document.getElementById("mode").value;
		//if(mode!="transEdit"){
            var whTaxI = form.find('input[name="withHoldingTaxOC"]');
            var whTax = whTaxI.val().replace(new RegExp(',', 'g'),'') || whTaxI.val("0").val();
				var insDate=document.premium.account_Period.value;
				if(insDate!="0")
				{
				var Premium;
				if(insDate=='RP')
				{
				Premium =document.premium.recuirement_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				}else if(insDate=='AP')
				{
					Premium =document.premium.adjustment_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				
				}else
				{
					Premium =document.premium.md_premium.value;
				if(Premium!=null && Premium!="")
					Premium=Premium.replace(new RegExp(',', 'g'),'');
				else
				 	Premium=0;
				}
				if( '3'=='<s:property value="productId"/>'){
				var Brokerage=document.premium.brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				
				
				 	 var taxDedectSource=document.premium.taxDedectSource.value;
				if(taxDedectSource!=null && taxDedectSource!="")
					taxDedectSource=taxDedectSource.replace(new RegExp(',', 'g'),'');
				else
				 	taxDedectSource=0;
				 	
				 var serviceTax=document.premium.serviceTax.value;
				if(serviceTax!=null && serviceTax!="")
					serviceTax=serviceTax.replace(new RegExp(',', 'g'),'');
				else
				 	serviceTax=0;
				 	
				 	}else{
				 	var Brokerage=0;
				 	
				 	var serviceTax=0;
				 	var taxDedectSource=0;
				 	
				 	}
				 	var Tax=document.premium.tax.value;
				if(Tax!=null && Tax!="")
					Tax=Tax.replace(new RegExp(',', 'g'),'');
				else
				 	Tax=0;
				 	
				var OtherCost=document.premium.otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;
				
				 	
				var bonus=document.premium.bonus.value;
				if(bonus!=null && bonus!="")
					bonus=bonus.replace(new RegExp(',', 'g'),'');
				else
				 	bonus=0;	
				 		
								
				var A=NetDue=parseFloat(Premium) + parseFloat(taxDedectSource)+ parseFloat(serviceTax);
				var B=parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost) + parseFloat(whTax)+ parseFloat(bonus);
				document.premium.totalCredit.value=Comma(A.toFixed(2));
				document.premium.totalDebit.value=Comma(B.toFixed(2)); 
				var NetDue = A-B;
//                    NetDue = parseFloat(Premium) - parseFloat(Brokerage) - parseFloat(Tax) - parseFloat(OtherCost);
				document.premium.netDue.value=Comma(NetDue.toFixed(2));
				}
				//}
		}	
function BonusPopup(){
    var contrctNo = document.getElementById("contNo").value;
    var transaction = document.getElementById("transaction").value;
    var currency = document.getElementById("currId").value;
    var exchRate = document.premium.exchRate.value;
	var transactionNo = document.getElementById("transactionNo").value;
	var layerno = document.getElementById("layerno").value;
	
	var conPremiumOC= document.getElementById("conPremiumOC").value;
	var conClaimPaidOC= document.getElementById("conClaimPaidOC").value;
	var conClaimOutStandingOC= document.getElementById("conClaimOutStandingOC").value;
	var conClaimRatioOC= document.getElementById("conClaimRatioOC").value;
	var conbonusOC= document.getElementById("conbonusOC").value;
	var conbonusPaidOC= document.getElementById("conbonusPaidOC").value;
	var conbonusAdjOC= document.getElementById("conbonusAdjOC").value;
	
	var exchangeVal =document.premium.enteringMode.value
	var md_premium=document.premium.md_premium.value;
	var adjustment_premium=document.premium.adjustment_premium.value;
	
	
	
    if(transaction ==null || transaction ==''){
        alert("Please Select Transaction Date ");
	}
	if(currency == null || currency ==''){
        alert("Please Select Currency");
	}
	//if(statementDate == null || statementDate ==''){
       // alert("Please Select Statement Date");
	//}
	if(transaction !=null && transaction !='' && currency != null && currency !='' &&(exchRate == null || exchRate =='')){
	    alert("Please Enter Exchange Rate");
	}
	if("1"==exchangeVal){
	alert("To automate Bonus Calculation, kindly key in the figures in Signed share instead of 100%");
	}	    
	if(transaction !=null && transaction !='' && currency != null && currency !='' && exchRate != null && exchRate!='' &&exchangeVal=="2"){
	    document.premium.transaction.disabled = true;
        document.premium.currency.disabled = true;
        document.getElementById("enteringMode1").disabled = true;
        document.getElementById("enteringMode2").disabled = true;
       // document.premium.statementDate.disabled = true;
    var URL ='${pageContext.request.contextPath}/bonusviewPopupFaculPremium.do?mode=bonus&contNo='+contrctNo+'&transaction='+transaction+'&layerno='+layerno+'&currencyId='+currency+'&exchRate='+exchRate+'&transactionNo='+transactionNo+'&conPremiumOC='+conPremiumOC+'&conClaimPaidOC='+conClaimPaidOC+'&conClaimOutStandingOC='+conClaimOutStandingOC+'&conClaimRatioOC='+conClaimRatioOC+'&conbonusOC='+conbonusOC+'&conbonusPaidOC='+conbonusPaidOC+'&conbonusAdjOC='+conbonusAdjOC+'&md_premium='+md_premium+'&adjustment_premium='+adjustment_premium+'&type=Y';;
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
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
}
}
    var form = $('form[name="premium"]');
		Commas();
		function insertupdate1(buttonStatus){
		destroyPopUps();
      	if( '3'=='<s:property value="productId"/>'){
      	document.getElementById("buttonStatus").value = buttonStatus;
		var contNo = document.getElementById("contNo").value;
		var accPer = document.getElementById("account_Period").value;
		id = "premiumSubmit";
		$.ajax({
		        type: "POST",
		        url:'${pageContext.request.contextPath}/getPreviousEntryDetFaculPremium.do?contNo='+contNo+'&dropDown=premiumSubmit&account_Period='+accPer,
		        //data:'src_type_id='+val,
		        success: function(data){
		            $('#' + id).html(data);
		           
		        }
		        });
		}else insertupdate(buttonStatus);
		}
		
		
		
		function insertupdate(buttonStatus)
		{ 
		twoDecimal();
		if( '3'=='<s:property value="productId"/>'){
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
		}
		var premiumA=document.getElementById("previousPremium").value;
		var contractprmium=document.getElementById("contractPremium").value;
		 var mdp=document.premium.md_premium.value;
		  var adp=document.premium.adjustment_premium.value;
		  var exchange=document.premium.exchRate.value;
		  mdp=mdp.replace(new RegExp(',','g'),'');
		  adp=adp.replace(new RegExp(',','g'),'');
		  //alert("1");
		  if(adp==null || adp==''){
		  adp=0;
		  }
		var premiumB=parseFloat(mdp)+parseFloat(adp);
		//alert(premiumB);
		var premiumC=parseFloat(premiumA)+parseFloat(premiumB);
		//alert(premiumC);
		var premiumD=parseFloat(premiumC)/exchange;
		var premiumE=parseFloat(contractprmium)/exchange;
		//alert(premiumD+"D");
		//alert(premiumE+"E");
		if(premiumD>premiumE){
		var test= confirm("Premium is greater that EGNPI as per contract.  Do you with to continue?");
		if(test){
			enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
			document.premium.action="${pageContext.request.contextPath}/insertPremiumXolPremium.do";
			document.premium.submit();
		}
		}else{
			enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
			document.premium.action="${pageContext.request.contextPath}/insertPremiumXolPremium.do";
			document.premium.submit();
		}
	 	
		}	 				
		function EditBack()
		{
		document.premium.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init";
		document.premium.submit();
		}
				
		function EditMode()
		{
		document.premium.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=PremiumSearch";
		}
		function cancel1()
		{
		destroyPopUps();
		document.premium.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.premium.submit();
		}

		function back(premiumMasterMode)
		{
		destroyPopUps();
		var contNo=document.getElementById("contNo").value;
		var layerno=document.getElementById("layerno").value;    
		//document.premium.action="${pageContext.request.contextPath}/premiumListXolPremium.do?mode=add&contNo="+contNo+"&layerno="+layerno;
		document.premium.action="${pageContext.request.contextPath}/premiumListXolPremium.do?mode=add&premiumMasterMode="+premiumMasterMode;
		document.premium.submit();	
		}
		 function cancel()
   		{
   			   destroyPopUps();
  			   var menuId=document.getElementById("menuId").value;  
  			   document.location='<%=request.getContextPath()%>/InitPortfolio.action?flag=C&menuId='+menuId;
 		}
 	
 		<s:if test='"<s:property value="mode"/>" !="edit"'>	
 		GetMdInstalment('<s:property value="account_Period"/>');
 		</s:if>
        function GetMdInstalment(value)
        {
	        if(value!=0)
	        {
		        var contract=document.premium.contNo.value;
		        var layerno=document.premium.layerno.value;    
		        var brokerage=document.premium.brokerage_S.value;
				var brokerpercentage=parseFloat(brokerage)/100;
				var tax_view=document.premium.tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var otherCost_view=document.premium.otherCost_S.value;
				var otherCost_percen=parseFloat(otherCost_view)/100;
		        if(value!="RP" && value!="AP" && value!="RTP" && value!="RVP")
		        {		        							        	
		        	var URL='<%=request.getContextPath()%>/instalmentPremiumXolPremium.action?contNo='+contract+'&layerno='+layerno+'&instalmentdate='+value+'&dropDown=mdpremium';
  	 				postRequest(URL,'mdpremium');   	 				
			       	 	document.premium.recuirement_premium.style.display='None';
						document.premium.md_premium.style.display='Inline';
						document.premium.adjustment_premium.style.display='None';
						document.premium.recuirement_premium.value='';
						document.premium.adjustment_premium.value='';
						document.premium.brokerage.value='';
						document.premium.tax.value='';
						document.premium.otherCost.value='';
						setTimeout(function() {	XolCal( document.premium.md_premium.value,brokerpercentage,tax_percen,otherCost_percen); xolNetDue(); }, 100);										
				}
				else if(value=='RP')
				{
				 	document.premium.recuirement_premium.style.display='Inline';
					document.premium.md_premium.style.display='None';
					document.premium.adjustment_premium.style.display='None';
					document.premium.md_premium.value='0.00';
					document.premium.recuirement_premium.value='0.00';
					document.premium.adjustment_premium.value='0.00';
					document.premium.brokerage.value='0.00';
					document.premium.tax.value='0.00';
					document.premium.otherCost.value='0.00';
				}
				else if(value=='AP')
				{
					document.premium.adjustment_premium.style.display='Inline';
					document.premium.recuirement_premium.style.display='none';
					document.premium.md_premium.style.display='none';
					//document.premium.adjustment_premium.value=document.premium.adjustment_premium_temp.value;
					document.premium.recuirement_premium.value='0.00';
					document.premium.md_premium.value='0.00';
					document.premium.brokerage.value='0.00';
					document.premium.tax.value='0.00';
					document.premium.otherCost.value='0.00';
					//document.premium.brokerage.value=(parseFloat(document.premium.adjustment_premium.value))*brokerpercentage;
					//document.premium.tax.value=(parseFloat(document.premium.adjustment_premium.value))*tax_percen;
					//document.premium.otherCost.value=(parseFloat(document.premium.adjustment_premium.value))*otherCost_percen;
					//document.premium.otherCost.value=Comma(c.toFixed(2));
				}
				else if(value=='RTP' || value=='RVP')
				{
				 	document.premium.recuirement_premium.style.display='None';
					document.premium.md_premium.style.display='Inline';
					document.premium.adjustment_premium.style.display='None';
					document.premium.md_premium.value='0.00';
					document.premium.recuirement_premium.value='0.00';
					document.premium.adjustment_premium.value='0.00';
					document.premium.brokerage.value='0.00';
					document.premium.tax.value='0.00';
					document.premium.otherCost.value='0.00';
				}
		    }
		    else
		    {
			    document.premium.md_premium.style.display='None';
			    document.premium.adjustment_premium.style.display='None';
			    document.premium.recuirement_premium.style.display='None';
			    document.premium.adjustment_premium.value='0.00';
				document.premium.brokerage.value='0.00';
				document.premium.tax.value='0.00';
				document.premium.otherCost.value='0.00';
		    }
	    
	    }
	    function XolCal(value,brokerpercentage,tax_percen,otherCost_percen)
	    {
	    //alert(XolCal);
	   	 	var a=0;
	   	 	value = document.premium.md_premium.value;
	    	var pid = '<s:property value="productId"/>';
	    	if('3'==pid){
	    	 a=(parseFloat(value))*brokerpercentage;
	    	}
	    	
			document.getElementById("BrokerAgeXol").value=Comma(a.toFixed(2));
			var b=parseFloat(value)*tax_percen;
			document.premium.tax.value=Comma(b.toFixed(2));
			var c=(parseFloat(value))*otherCost_percen;
			document.premium.otherCost.value=Comma(c.toFixed(2));
	    }
		function ReInstmentPremium(Amount)
		{
		 var calc = document.getElementById("CalculateTax").value ;
        var mode = document.getElementById("mode").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && "edit"!=mode){
	 	 	if(Amount!=null && Amount!="")
			{
		 	if(document.premium.adjustment_premium.style.display='None')
		 	{
				var brokerage=document.premium.brokerage_S.value;
				var brokerpercentage=parseFloat(brokerage)/100;
				var tax_view=document.premium.tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var pid = '<s:property value="productId"/>';
	    		var aval=0;
				Amount=Amount.replace(new RegExp(',','g'),'');
				if('3'==pid){
				document.getElementById("BrokerAgeXol").value=Comma(((parseFloat(Amount))*brokerpercentage).toFixed(2));
				}else{
				document.getElementById("BrokerAgeXol").value=Comma((aval.toFixed(2)));
				}
				document.premium.tax.value=Comma((parseFloat(Amount)*tax_percen).toFixed(2));
				var otherCost=document.premium.otherCost_S.value;
				var percentage3=parseFloat(otherCost)/100;
				var e=parseFloat(Amount)*percentage3;
				document.premium.otherCost.value=Comma(e.toFixed(2));
			} 
			}else
			{
			document.getElementById("BrokerAgeXol").value='';
			document.premium.tax.value='';
			document.premium.otherCost.value='';
			}
			}
			</s:if>
		}		
		function AdjustmentPremium(value)
		{
		if(value=='AP')
		{
		var f=document.premium.adjustment_premium.value.replace(new RegExp(',', 'g'),'');
		document.premium.adjustment_premium.value=Comma(parseFloat(f).toFixed(2));
		document.premium.adjustment_premium.style.display='inline'
		document.premium.recuirement_premium.style.display='None';
		document.premium.md_premium.style.display='none';
		}
		else if(value=='RP')
		{
		var f=document.premium.recuirement_premium.value.replace(new RegExp(',', 'g'),'');
		document.premium.recuirement_premium.value=Comma(parseFloat(f).toFixed(2));
		document.premium.recuirement_premium.style.display='Inline';
		document.premium.adjustment_premium.style.display='None';
		document.premium.md_premium.style.display='none';
		}
		else if(value==0)
		{
		document.premium.recuirement_premium.style.display='None';
		document.premium.adjustment_premium.style.display='None';
		document.premium.md_premium.style.display='none';
		}
		else 
		{
		document.premium.md_premium.style.display='inline';
		document.premium.recuirement_premium.style.display='None';
		document.premium.adjustment_premium.style.display='None'
		}
		}
		function CalculationXOL(value)
		{
		
		var calc = document.getElementById("CalculateTax").value ;
		var mode = document.getElementById("mode").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && "edit"!=mode){
		if(value!=null && value!="")
		{
		var b=0;
		value=value.replace(new RegExp(',','g'),'');
		var pid='<s:property value="productId"/>';
		var brokarage=document.premium.brokerage_S.value;
		brokarage=brokarage.replace(new RegExp(',','g'),'');
		var percentage1=parseFloat(brokarage)/100;
		if('3'==pid){
		 b=parseFloat(value)*percentage1;
		}
		document.premium.brokerage.value=Comma(b.toFixed(2));
		var tax=document.premium.tax_S.value;
		var percentage2=parseFloat(tax)/100;
		var c=parseFloat(value)*percentage2;
		document.premium.tax.value=Comma(c.toFixed(2));
		var otherCost=document.premium.otherCost_S.value;
		var percentage3=parseFloat(otherCost)/100;
		var e=parseFloat(value)*percentage3;
		document.premium.otherCost.value=Comma(e.toFixed(2));
	    }
	    else
	    {
    		document.premium.brokerage.value='';
    		document.premium.tax.value='';
    		document.premium.otherCost.value='';
	    }
		}
		</s:if>
		}
		
		function GetAdjustmentPremium(){
		 var contrctNo = document.getElementById("contNo").value;
		var layerno = document.getElementById("layerno").value;
		var predepartment = document.getElementById("predepartment").value;
		document.premium.currency.disabled = false;
		var currency = document.premium.currency.value;
		document.premium.currency.disabled = true;
		var gnpiDate=document.premium.gnpiDate.value;
		if(gnpiDate!=null && gnpiDate!=""){
			var URL='<%=request.getContextPath()%>/getAdjPremXolPremium.action?gnpiDate='+gnpiDate+'&contNo='+contrctNo+'&layerno='+layerno+'&predepartment='+predepartment+'&currency='+currency+'&dropDown=adjPrem';
	 				postRequest(URL,'adjPrem');					
			}else
			{
					document.premium.adjustment_premium.value='0.00';
			}
			 var brokerage=document.premium.brokerage_S.value;
				var brokerpercentage=parseFloat(brokerage)/100;
				var tax_view=document.premium.tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var otherCost_view=document.premium.otherCost_S.value;
				var otherCost_percen=parseFloat(otherCost_view)/100;
		setTimeout(function() {	XolCal(document.premium.adjustment_premium.value,brokerpercentage,tax_percen,otherCost_percen); xolNetDue(); }, 100);
		
		}
		function GetExchangeRate()
		{
				var month=document.premium.transaction.value;				
				var Currecny=document.getElementById("currId").value;										
			 	if(month!=null && month!="" && Currecny!="0" )
				{
					document.premium.currencyId.value=Currecny;
					var URL='<%=request.getContextPath()%>/getExcRateRiskDetails.action?incepDate='+month+'&orginalCurrency='+Currecny+'&dropDown=exchRate';
  	 				postRequest(URL,'exchRate');					
				}else
				{
						document.premium.exchRate.value='';
				}
		}
		function Commas()
		{
		 	document.premium.otherCost.value=Comma(document.premium.otherCost.value);
			
			document.premium.tax.value=Comma(document.premium.tax.value);
			document.premium.recuirement_premium.value=Comma(document.premium.recuirement_premium.value);
			document.premium.md_premium.value=Comma(document.premium.md_premium.value);
			document.premium.adjustment_premium.value=Comma(document.premium.adjustment_premium.value);
			<s:if test='"3".equals(productId)'>
			document.premium.brokerage.value=Comma(document.premium.brokerage.value);
			document.premium.taxDedectSource.value=Comma(document.premium.taxDedectSource.value);
			document.premium.serviceTax.value=Comma(document.premium.serviceTax.value);
			document.premium.bonus.value=Comma(document.premium.bonus.value);
			AdjustmentPremium(document.premium.account_Period.value);
			</s:if>
			
			xolNetDue();	   
				
		}
		function twoDecimal(){
		var e=document.premium.md_premium.value.replace(new RegExp(',', 'g'),'');
		document.premium.md_premium.value=Comma(parseFloat(e).toFixed(2));
	
		var a=document.premium.otherCost.value.replace(new RegExp(',', 'g'),'');
		document.premium.otherCost.value=Comma(parseFloat(a).toFixed(2));
		
		var b=document.premium.brokerage.value.replace(new RegExp(',', 'g'),'');
		document.premium.brokerage.value=Comma(parseFloat(b).toFixed(2));
		
		var c=document.premium.tax.value.replace(new RegExp(',', 'g'),'');
		document.premium.tax.value=Comma(parseFloat(c).toFixed(2));
		
		if( '3'=='<s:property value="productId"/>'){
		var g=document.premium.taxDedectSource.value.replace(new RegExp(',', 'g'),'');
		document.premium.taxDedectSource.value=Comma(parseFloat(g).toFixed(2));
		
		var h=document.premium.tax.value.replace(new RegExp(',', 'g'),'');
		document.premium.tax.value=Comma(parseFloat(c).toFixed(2));
		
		
		var i=document.premium.serviceTax.value.replace(new RegExp(',', 'g'),'');
		document.premium.serviceTax.value=Comma(parseFloat(i).toFixed(2));
		}
		AdjustmentPremium(document.premium.account_Period.value);
		
		
		}
			
		function getViewContractDetails(prodId,proposalNo,amendId)
	    {
	    	var URL ='';
	    	//if(prodId=='1'){
 				//URL ='<%=request.getContextPath()%>/FaculitivesDispatchAction.do?method=ViewMethod&proposalno='+proposalNo+'&flag=PR&amendId='+amendId;
 			//}else
 			//{
 				//URL ='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=ViewMethod&proposalno='+proposalNo+'&flag=PR&amendId='+amendId;
 			//}
 			if(prodId=='1'){
			URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
			}
			else if(prodId=='2') {
				URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
			}
			else if((prodId=='3')||(prodId=='5')) {
				URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
			}
			else {
				URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
			}
			var windowName = "Contract Details";
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
			mtbChildWin[mtbWinCound] = strOpen;
			mtbWinCound++;
			strOpen.focus();
			return false;
	}	
	function Comma(Num)
	{
	  Num += '';
      Num = Num.replace(/,/g, '');
      if(Num.length>20)
      {
     	 alert('Exceeding Your Limit');
     	 Num=Num.substring(0,20);
      }
      x = Num.split('.');
      x1 = x[0];
      x2 = x.length > 1 ? '.' + x[1] : '.00';
      var rgx = /(\d+)(\d{3})/;
      while (rgx.test(x1))
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
      return x1 + x2;
	}
	$(function() {
		    	
	    $('#remarks').keyup(function() {
	        update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    });	    
	    update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    
	});

	function update_chars_left(max_len, target_input, display_element) {
	   var text_len = target_input.value.length;
	   if (text_len >= max_len) {
	       target_input.value = target_input.value.substring(0, max_len); // truncate
	       display_element.html("0");
	   } else {
	       display_element.html(max_len - text_len);
	   }
	}
	$(function(){
        $(document).on('click','input[type=text]',function(){ this.select(); });
    });
    
    
getGnpi('<s:property value="account_Period"/>');
function getGnpi(val){
	 if ("AP"==val){
		 document.getElementById("gnpi").style.display="inline";
	 }
	 else{
	  document.getElementById("gnpi").style.display="none";
	 }
 }
 ('<s:property value="account_Period"/>')
 function getDateDisable(val){
 if ("AP"==val || "RP"==val || "RTP"==val || "RVP"==val ){
 	document.getElementById("predepartment").disabled=false;
 }
 else{
 	document.getElementById("predepartment").disabled=true;
 }
 }
 
 
  function funStatisticMode(proposalNo,ContractNo,departmentId,layerNo){
     // document.getElementById("proposal_No").value=proposalNo;
     // document.getElementById("layerNo").value=layerNo;
      URL="statisticsDownStatistics.action?contractNo="+ContractNo+"&departmentId="+departmentId+"&proposal_No="+proposalNo+"&layerNo="+layerNo+"&mode=popup";
      var windowName = "Statistics Details";
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
			mtbChildWin[mtbWinCound] = strOpen;
			mtbWinCound++;
			strOpen.focus();
			return false;
      }
      enterMode();
      function enterMode(){
      var val ='<s:property value="enteringMode"/>';
      if(val=='1'){
      document.getElementById("enteringMode1").checked=true;
      }else if(val=='2'){
       document.getElementById("enteringMode2").checked=true;
      }
      
      }
function GetStatementRecDate(){
var pid = '<s:property value="productId"/>';
 var month=document.premium.transaction.value;
	if('3'==pid){
	    if(month!=null && month!='' ){
	    document.premium.inception_Date.value=month
	    }
	    GetStatementDate();
    	}else if('5'==pid){
    	if(month!=null && month!='' ){
		    document.premium.statementDate.value=month
		   }
    	}
    
    }
function GetStatementDate(){
    var month=document.premium.inception_Date.value;
    if(month!=null && month!='' ){
    document.premium.statementDate.value=month
   }
   }
   
   function setCalculateVal(){
   document.getElementById("CalculateTax").value = "Completed";
   }
   editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}


function middleMinusRestriction(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
	    if(i!=0){
	        if(text[i]=='-') min++;
	        if(min<1) { 
	        	prevValue += text[i];
	        }
	        }else{
	        	prevValue += text[i];
	        }
        }
    txt.value= prevValue;
    return false;
}
getTrasView('<s:property value="account_Period"/>');
function getTrasView(val){
	if(val=="RVP"){
		document.getElementById("transactionView").style.display='inline';
		getTransactionDropDown('<s:property value="chooseTransaction"/>','trans');
	}else {
		document.getElementById("transDropDownVal").value="";
		document.getElementById("chooseTransactionYes").value="";
		document.getElementById("chooseTransactionNo").value="";
		document.getElementById("transactionView").style.display='none';
		
	}
}
<s:if test='"<s:property value="mode"/>" !="edit"'>	
getTransactionDropDown('<s:property value="chooseTransaction"/>','trans');
</s:if>
function getTransactionDropDown(val,id){
var productId = '<s:property value="productId"/>';
var transDropDownVal= document.getElementById("transDropDownVal").value;
var transactionNo = document.getElementById("transactionNo").value;
var val = document.premium.chooseTransaction.value;
	if(val==''){
	  val = document.premium.chooseTransaction.value;
	 }
	 if(val=="Yes"){
	 document.premium.mode.value="transEdit";
	 }else{
	 	if(transactionNo==""){
			document.premium.mode.value="add";
			}else{
			document.premium.mode.value="edit";
			}
	 }
		var proposal_No = document.getElementById("proposal_No").value;
		var transaction =  document.getElementById("transaction").value;
		var mode = document.premium.mode.value;
		if(val!=''){
		var URL='${pageContext.request.contextPath}/transactionListFaculPremium.action?proposal_No='+proposal_No+'&transaction='+transaction+'&dropDown='+id+'&transDropDownVal='+transDropDownVal+'&productId='+productId+'&chooseTransaction='+val+'&mode='+mode;
	  	postRequest(URL,id);
	  	}
}

function getTransactionData(){
var val=document.getElementById("transDropDownVal").value;
var account_Period = document.premium.account_Period.value;
var transactionNo = document.getElementById("transactionNo").value;
if(val!=''){
document.premium.mode.value="transEdit";
}else{
	if(transactionNo==""){
			document.premium.mode.value="add";
			}else{
			document.premium.mode.value="edit";
			}
}

if(account_Period==("RVP") || account_Period==("RP")||account_Period==("RTP") ||account_Period==("AP") ){
document.premium.action="${pageContext.request.contextPath}/editPremiumXolPremium.do";
document.premium.submit();
}
}

function AuthendicationBack(){
	document.premium.action="${pageContext.request.contextPath}/initAuthentication.do";
	document.premium.submit();
}




function insertPremiumDet(val){
	if("Y"==val){
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
	      if(document.getElementById("ri_cessionNo").checked){
	        	answer=confirm("You have chosen to not cede this transaction to retro contracts.  Do you wish to continue?");
	     	if(answer)
	     	insertupdate(status);
	     	else
	     	return false;
	     	}
	    	else insertupdate(status);
	}else{
		answer = confirm("Previous Installement has not been Entered,Do You Wish to continue?");
		if(answer){
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
	      if(document.getElementById("ri_cessionNo").checked){
	        	answer=confirm("You have chosen to not cede this transaction to retro contracts.  Do you wish to continue?");
	     	if(answer)
	     	insertupdate(status);
	     	else
	     	return false;
	     	}
	    	else insertupdate(status);
		
		}else
	     	return false;
	}
}

</script>		
</body>
</html>
