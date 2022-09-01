<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<style type="text/css">
		.btn-group {
		width: 100%;
		}
		.btn-group .btn {
		width: 90%;
		padding: 3px 12px;
		}
	</style>
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
					<s:form id="" name="premium" theme="simple" action="insertPremiumFaculPremium"	method="post" autocomplete="off">					
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
												<s:if test='!"7".equals(<s:property value="%{#session.mfrid}" />)'>
													<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
													<s:hidden name="amendId"/>
												</s:if>
												<s:else>
												<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="productId" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
												<s:hidden name="amendId"/>
												</s:else>
												<input type="button" class="btn btn-xs btn-info" value="View Stats & Calc " style="cursor: pointer;" onclick="funStatisticMode('<s:property value="proposal_No" />','<s:property value="contNo" />','<s:property value="departmentId" />','<s:property value="layerNo" />')" />
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
													<s:text name="label.insuredName" />
												</div>
												<div class="tbox">
													<s:property value="insured_name"/>
												</div>
											</div>	
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.Class" />
												</div>
												<div class="tbox">
													<s:property value="departmentName"/>
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
											<%-- <div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.profitCenter" />
												</div>
												<div class="tbox">
													<s:property value="profit_Center"/>
												</div>
											</div>	--%>					
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.cedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.broker" />
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
													<s:text name="label.commissionP" />
												</div>
												<div class="tbox">
													<s:property value="commission_view"/>
												</div>
											</div>											
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
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.gwpiOurShare" /> - <s:property value="currencyName"/>
												</div>
												<div class="tbox">
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(gwpiOS)})}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB"> 
													<s:text name="label.gwpiBooked" /> - <s:property value="currencyName"/> 
												</div>
												<div class="tbox">
													<s:set id="prebal" value="sum_of_paid_premium" />
													<s:hidden name="sum_of_paid_premium" />
													<s:set id="pregwpi" value="gwpiOS" />
													<s:hidden name="gwpiOS" />
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(sum_of_paid_premium)})}"/>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.gwpiBalance" /> - <s:property value="currencyName"/>
												</div>
												<div class="tbox">
													<s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(Epibalance)})}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
												</div>
												<div class="tbox">
													<s:hidden name="settlement_status" id="settlement_status"/>
												</div>
											</div>
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
													<s:radio list="#{'1':'100%'}" name="enteringMode"  id="enteringMode"/>												
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
										<s:if test='"edit".equals(mode) && !"Temp".equals(tableType)'>
												<div class="textfield">
												<div class="text">
													<s:text name="label.amendmentDate" />
												</div>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<sj:datepicker name="amendmentDate" id="amendmentDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />
													</div>	-->
													<s:textfield name="amendmentDate" id="amendmentDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />													
												</div>
											</div>
											<div class="textfield"></div>
											</s:if>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.departmentClass" />
												</div>
												<div class="tbox">
													<s:textfield name="departmentName" cssClass="inputBox" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.subClass" />
												</div>
												<div class="tbox">
													<s:select list="subProfit_centerlist" name="subProfitId" id="subProfitId" cssClass="inputBoxS" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" />
													<s:hidden name="subProfitIdbase" id="subProfitIdbase"></s:hidden>
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDate" />
												</div>
												<s:if test='"edit".equals(mode)'>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<!--<sj:datepicker name="transaction" id="transaction" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" disabled="true"/>--
														<sj:textfield name="transaction" id="transaction" disabled="true" cssStyle="width: 100%; border:transparent;" />	
													</div>-->
													
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"   disabled="true" />														
												</div>
												</s:if>
												<s:else>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<sj:datepicker name="transaction" id="transaction" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();"/>
													</div>-->
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();GetStatementRecDate();getTransactionDropDown('','trans','');"/>														
												</div>
												</s:else>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementReceivedDate" />
												</div>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<sj:datepicker name="inception_Date" id="inception_Date" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>	-->
												<s:textfield name="inception_Date" id="inception_Date"  cssClass="inputBox"  onkeyup="validateSpecialChars(this);" onchange="GetStatementDate();" />												
												</div>
											</div>
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
													<%--<s:if test="((transactionNo == null || ''.equals(transactionNo)) ||(requestNo == null || ''.equals(requestNo)))">--%>
													<s:if test='mode!="edit"'>
														<s:select list="mdList" listKey="KEY1" listValue="VALUE" headerKey="" headerValue="---Select---" name="account_Period" id="account_Period" cssClass="inputBoxS" onchange="GetFacInstalment(this.value);getTrasView(this.value);getTransactionData();" />
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
													<s:radio list="#{'Yes':'Yes','No':'No'}" name="chooseTransaction" id="chooseTransaction" value="%{(chooseTransaction==null || chooseTransaction=='')?'Yes':chooseTransaction}" onchange="getTransactionDropDown(this.value,'trans');" disabled="(mode=='edit')?true:false"/>												
													<s:hidden name="transDropDownVal" id="transDropDownVal"/>
												</div>
												
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedantBrokerDocReference" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="cedentRef" />												
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
													<s:select name="currency" id="currId" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" cssStyle="width:30%;float:left;" headerValue="---Select---" disabled="true"/>												
													<%--<s:hidden name="currency" id="currId"></s:hidden>--%>
													<span id="exchRate">
													<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:left;text-align:right;" />	
													</span>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.ricession" />
												</div>
												<div class="tbox">													
													<s:radio list="#{'Yes':'Yes','No':'No'}" name="ri_cession" id="ri_cession" value="%{ri_cession==null || ri_cession==''?'Yes':ri_cession}" disabled="true"/>													
												</div>
											</div>						
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
													<td><s:text name="label.premium" /> </td>
													<td>
														<s:if test="account_Period == 'EP'">	
														<div id="Premium">													
														<s:textfield cssClass="inputBox" name="premiumQuotaShare" id="premiumQuotaShare" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);Caculate(this.value,'');facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{premiumQuotaShare==null?'0.00':premiumQuotaShare}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
														</div>
														</s:if>
														<s:else>
														<div id="Premium">
														<s:textfield cssClass="inputBox" name="premiumQuotaShare" id="premiumQuotaShare" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);Caculate(this.value,'');facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{premiumQuotaShare==null?'0.00':premiumQuotaShare}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
														</div>
														</s:else>
													</td>
													<td> <s:text name="label.commission" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="commissionQuotaShare" id="commissionQuotaShare" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{commissionQuotaShare==null?'0.00':commissionQuotaShare}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>														
													</td>
												</tr>
												<tr>
													<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
													 <td><s:text name="label.taxdedect" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="taxDedectSource" id="taxDedectSource" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);Caculate(this.value,'tax');facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{taxDedectSource==null?'0.00':taxDedectSource}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>														
													</td>
													</s:if>
													<s:else>
													<td colspan="2">
													<s:hidden name="taxDedectSource" id="taxDedectSource" value="0"/> 
													</td>
													</s:else>
													<td> <s:text name="label.brokerage" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="brokerage" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{brokerage==null?'0.00':brokerage}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
												</tr>
												<tr>
													<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
													 <td><s:text name="label.gst" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="serviceTax" id="serviceTax" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();Caculate(this.value,'service');" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{serviceTax==null?'0.00':serviceTax}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
													</s:if>
													<s:else>
													<td colspan="2"> </td>
													<s:hidden name="serviceTax" id="serviceTax" value="0"/>
													</s:else>
                                                    <td> <s:text name="label.taxExpense" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="tax" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{tax==null?'0.00':tax}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
												</tr>
												<tr>
                                                    <td colspan="2"> </td>
                                                    <td> <s:text name="label.WithHoldingTax" /> </td>
                                                    <td>
                                                        <s:textfield cssClass="inputBox" name="withHoldingTaxOC" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{withHoldingTaxOC==null?'0.00':withHoldingTaxOC}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2"> </td>
                                                    <td> <s:text name="label.BonusVal" /> </td>
                                                    <td>
                                                        <s:textfield cssClass="inputBox" name="bonus" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{bonus==null?'0.00':bonus}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
                                                        <input type="button"  size="2"  value="..." onclick="BonusPopup()" class="pullRight" />
                                                    </td>
                                                </tr>
												
												<tr>
													<td colspan="2"> </td>
													<td> <s:text name="label.otherCost" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="otherCost" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);facNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{otherCost==null?'0.00':otherCost}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
													</td>
												</tr>
												<tr>
													<td> <s:text name="label.total" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="totalCredit" readonly="true"  cssStyle="text-align: right;" onclick="this.select();" value="%{totalCredit==null?'0.00':totalCredit}"/>
													</td>
													<td> <s:text name="label.total" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="totalDebit" readonly="true"  cssStyle="text-align: right;" onclick="this.select();" value="%{totalDebit==null?'0.00':totalDebit}"/>
													</td>
												</tr>
												<tr>
													<td colspan="2"> </td>
													<td> <s:text name="label.netDue" /> </td>
													<td>
														<s:textfield cssClass="inputBox" name="netDue" readonly="true"  cssStyle="text-align: right;" onclick="this.select();" value="%{netDue==null?'0.0':netDue}"/>
													</td>
												</tr>
												<tr>
													<td> <s:text name="label.remarks" /> </td>
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
							<s:if test='#session.MenuRights.indexOf("PST")!=-1'>	
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate1('Submit')" />
								&nbsp;&nbsp;&nbsp;
							</s:if>
							<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
							</s:if>
								
							</s:if>
							<s:else>
								<s:if test="size == 'list'">
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='back("<s:property value="premiumMasterMode"/>","<s:property value="proposal_No" />")' />
								</s:if>	
								<s:if test='premiumDisplay.equals("authenticationDisplay")'>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' />
								</s:if>
								<s:if test='#session.MenuRights.indexOf("PST")!=-1 || #session.MenuRights.indexOf("AST")!=-1'>								
									&nbsp;&nbsp;&nbsp;
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate1('Submit');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
							<!-- 	<s:submit name="submit" value="Submit" cssClass="btn btn-sm btn-success" /> --> 
								<s:if test="size != 'list' && premiumDisplay!=('authenticationDisplay')">
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="cancel()" />		
								</s:if>										
							</s:else>
							</div>
						</div>	
						<s:hidden name="transactionNo" id="transactionNo"/>					
						<s:hidden name="proposalno" value="%{proposal_no}" />
						<s:hidden name="proposal_No" id="proposal_No" />
						<s:hidden name="baseLayer"/>
						<s:hidden name="layerProposalNo"/>											
						<s:hidden name="contNo" id="contNo"/>						
						<s:hidden name="mode" id="mode" />
						<s:hidden name="baseCurrencyId" />
						<s:hidden name="baseCurrencyName" />
						<s:hidden name="currencyName" />
						<s:hidden name="size" />
						
						<s:hidden name="currencyId" />
						<s:hidden name="commission_view" />
						<s:hidden name="tax_view" />
						<s:hidden name="overRider_view" />
						<s:hidden name="brokerage_view" />
						<s:hidden name="shareSigned" />
						<s:hidden name="layerno" id="layerno"/>
						<s:hidden name="amendId" />
						<s:hidden name="commission" value="0" />
						<s:hidden name="commssion_S" value="%{commission_view}"/>
						<s:hidden name="layerNo" id="layerNo" />
						<s:hidden name="menuId" id="menuId"/>
						<s:hidden name="previousPremium" id="previousPremium"/>
						<s:hidden name="contractPremium" id="contractPremium"/>	
						<s:hidden name="premiumDisplay" id="premiumDisplay"/>	
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
						<s:hidden name="predepartment" id="predepartment"/>	
						<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>	
						<s:hidden name="multiuserError" id="multiuserError"/>	
						<s:hidden name="tableType" id="tableType"/>	
						<s:hidden name="requestNo" id="requestNo"/>
						<s:hidden name="buttonStatus" id="buttonStatus"/>
						<s:hidden name="acceptenceDate" id="acceptenceDate"/>
						<s:hidden name="preVal" id="preVal"/>
					</div>	
					<div id="premiumSubmit">
					</div>									
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
function BonusPopup(){
    var contrctNo = document.getElementById("contNo").value;
    var transaction = document.getElementById("transaction").value;
    var currency = document.getElementById("currId").value;
    var exchRate = document.premium.exchRate.value;
	var transactionNo = document.getElementById("transactionNo").value;
	
	var conPremiumOC= document.getElementById("conPremiumOC").value;
	var conClaimPaidOC= document.getElementById("conClaimPaidOC").value;
	var conClaimOutStandingOC= document.getElementById("conClaimOutStandingOC").value;
	var conClaimRatioOC= document.getElementById("conClaimRatioOC").value;
	var conbonusOC= document.getElementById("conbonusOC").value;
	var conbonusPaidOC= document.getElementById("conbonusPaidOC").value;
	var conbonusAdjOC= document.getElementById("conbonusAdjOC").value;
	var exchangeVal =document.premium.enteringMode.value
	var premiumQuotaShare=document.getElementById("premiumQuotaShare").value
	
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
    var URL ='${pageContext.request.contextPath}/bonusviewPopupFaculPremium.do?mode=bonus&contNo='+contrctNo+'&transaction='+transaction+'&currencyId='+currency+'&exchRate='+exchRate+'&transactionNo='+transactionNo+'&conPremiumOC='+conPremiumOC+'&conClaimPaidOC='+conClaimPaidOC+'&conClaimOutStandingOC='+conClaimOutStandingOC+'&conClaimRatioOC='+conClaimRatioOC+'&conbonusOC='+conbonusOC+'&conbonusPaidOC='+conbonusPaidOC+'&conbonusAdjOC='+conbonusAdjOC+'&premiumQuotaShare='+premiumQuotaShare+'&type=Y';
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
function insertupdate1(status){
		destroyPopUps();
		document.getElementById("buttonStatus").value = status;
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
	}
		function insertupdate(status)
		{ 
		twoDecimal();
		//var status= document.getElementById("buttonStatus").value;
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
		var premiumA=document.getElementById("previousPremium").value;
		var contractprmium=document.getElementById("contractPremium").value;
		 var PQS=document.premium.premiumQuotaShare.value;
		  var exchange=document.premium.exchRate.value;
		  PQS=PQS.replace(new RegExp(',','g'),'');
		  //alert(contractprmium);
		var premiumB=parseFloat(PQS)
		//alert(premiumA);
		//alert(premiumB);
		var premiumC=parseFloat(premiumA)+parseFloat(premiumB);
		//alert(premiumC);
		var premiumD=parseFloat(premiumC)/exchange;
		var premiumE=parseFloat(contractprmium)/exchange;
		document.premium.currency.value = document.getElementById("currId").value;
		//alert(document.premium.currency.value);
		//alert(premiumD+"D");
		//alert(premiumE+"E");
		//document.getElementById("buttonStatus").value = status;
		if(premiumD>premiumE){
		var test= confirm("Premium is greater that GWPI as per contract.  Do you wish to continue?");
		if(test){
			enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
	 	var mode = document.getElementById("mode").value;
	 	var contNo = document.getElementById("contNo").value;
	 	var layer = document.getElementById("layerno").value;
	 	 
		document.premium.action='${pageContext.request.contextPath}//insertPremiumFaculPremium.do?productId=<s:property value="productId"/>';
		document.premium.submit();
		}
		}else{
			enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
	 	var mode = document.getElementById("mode").value;
	 	var contNo = document.getElementById("contNo").value;
	 	var layer = document.getElementById("layerno").value;
		document.premium.action='${pageContext.request.contextPath}/insertPremiumFaculPremium.do?productId=<s:property value="productId"/>';
		document.premium.submit();
		}
		}
		function cancel1()
		{
		destroyPopUps();
		document.forms[0].action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.forms[0].submit();
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

		function back(premiumMasterMode,proposal_No)
		{
		destroyPopUps();
		var contNo=document.getElementById("contNo").value;   
		document.getElementById("mode").value = "add";
		
		document.forms[0].action="${pageContext.request.contextPath}/premiumListFaculPremium.do?contNo="+contNo+"&premiumMasterMode="+premiumMasterMode+"&proposal_No="+proposal_No;
		document.forms[0].submit();	
		}
		 function cancel()
   		{
   			   destroyPopUps();
  			   var menuId=document.getElementById("menuId").value;  
  			   document.location='<%=request.getContextPath()%>/InitPortfolio.action?flag=C&menuId='+menuId;
 		}
 		/*function getInsert()
   		{
  			  // var menuId=document.getElementById("menuId").value;  
  			   enableForm(document.forms['premium'],false,'transaction');
  			   document.premium.action="${pageContext.request.contextPath}/insertPremiumFaculPremium.do;
  			   document.premium.submit();	
 		}*/		
		function Caculate(value,val)
		{
		var calc = document.getElementById("CalculateTax").value ;
       var mode = document.getElementById("mode").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && "edit"!=mode){
			if(value!=null && value!="")
			{
					var PremiumQshare=document.premium.premiumQuotaShare.value;					
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');					
					var commssion=document.premium.commssion_S.value;
					
					var percentage=parseFloat(commssion)/100;
					var a=parseFloat(PremiumQshare)*percentage;
					document.premium.commissionQuotaShare.value=Comma(a.toFixed(2));
					var brokarage=document.premium.brokerage_S.value;
					var percentage1=parseFloat(brokarage)/100;
					var b=parseFloat(PremiumQshare)*percentage1;
					document.premium.brokerage.value=Comma(b.toFixed(2));
					var tax=document.premium.tax_S.value;
					var percentage2=parseFloat(tax)/100;
					var c=parseFloat(PremiumQshare)*percentage2;
					document.premium.tax.value=Comma(c.toFixed(2));
					var otherCost=document.premium.otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var d=parseFloat(PremiumQshare)*percentage3;
					document.premium.otherCost.value=Comma(d.toFixed(2));
					//document.premium.premiumQuotaShare.value=Comma(parseFloat(PremiumQshare).toFixed(2));
					document.premium.premiumQuotaShare.value=PremiumQshare;
		    }
		    else
		    {
				    document.premium.commissionQuotaShare.value='0.00';
				    document.premium.brokerage.value='0.00';
				    document.premium.tax.value='0.00';
				    document.premium.otherCost.value='0.00';
			}
			}
			</s:if>
			if('tax'==val || 'service'==val){
			 document.getElementById("CalculateTax").value="Completed" ;
			}
			
		}       
	     function GetFacInstalment(value)
        {
	        if(value!=0 && value!='EP' && value!='RTP' && value!='RVP')
	        {
//	        	    document.premium.premiumQuotaShare.setAttribute('readonly', 'readonly');
					var contract=document.premium.contNo.value;			
		        	var URL='<%=request.getContextPath()%>/instalmentPremiumFaculPremium.action?contNo='+contract+'&instalmentdate='+value+'&dropDown=Premium';
  	 				postRequest(URL,'Premium'); 	 				
					setTimeout(function() {	Caculate(document.premium.premiumQuotaShare.value,''); facNetDue(); }, 800);
																			
			}else
			{
        	    document.premium.premiumQuotaShare.removeAttribute('readonly');       	   
				document.premium.premiumQuotaShare.value="0.00";
				Caculate(document.premium.premiumQuotaShare.value,'');
				facNetDue();
			}						
	    }		
		function GetExchangeRate()
		{
				var month=document.premium.transaction.value;
				//var year=document.premium.transaction_year.value;
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
function GetStatementRecDate(){
    var month=document.premium.transaction.value;
    if(month!=null && month!='' ){
    document.premium.inception_Date.value=month
    }
    GetStatementDate();
    
    }
   function GetStatementDate(){
    var month=document.premium.inception_Date.value;
    if(month!=null && month!='' ){
    document.premium.statementDate.value=month
   }
   }
			function Commas()
			{		//parseFloat(PremiumQshare).toFixed(2)
		  		//document.premium.premiumQuotaShare.value=Comma(document.premium.premiumQuotaShare.value);
		  		document.premium.commissionQuotaShare.value=Comma(document.premium.commissionQuotaShare.value);
		  		//alert(Comma(parseFloat(document.premium.commissionQuotaShare.value).toFixed(2))+"");
		  	  	document.premium.otherCost.value=Comma(document.premium.otherCost.value);
		  	  	document.premium.serviceTax.value=Comma(document.premium.serviceTax.value);
		  	  	document.premium.taxDedectSource.value=Comma(document.premium.taxDedectSource.value);
			    document.premium.brokerage.value=Comma(document.premium.brokerage.value);
				document.premium.tax.value=Comma(document.premium.tax.value);
				document.premium.netDue.value=Comma(document.premium.netDue.value);
				document.premium.withHoldingTaxOC.value=Comma(document.premium.withHoldingTaxOC.value || "0.00");
				document.premium.bonus.value=Comma(document.premium.bonus.value);
				facNetDue()          
		}
		function twoDecimal(){
		
		var a=document.premium.commissionQuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.premium.commissionQuotaShare.value=Comma(parseFloat(a).toFixed(2));
		
		var b=document.premium.otherCost.value.replace(new RegExp(',', 'g'),'');
		document.premium.otherCost.value=Comma(parseFloat(b).toFixed(2));
		
		var c=document.premium.brokerage.value.replace(new RegExp(',', 'g'),'');
		document.premium.brokerage.value=Comma(parseFloat(c).toFixed(2));
		
		var d=document.premium.tax.value.replace(new RegExp(',', 'g'),'');
		document.premium.tax.value=Comma(parseFloat(d).toFixed(2));
		
		var e=document.premium.withHoldingTaxOC.value.replace(new RegExp(',', 'g'),'');
		document.premium.withHoldingTaxOC.value=Comma(parseFloat(e).toFixed(2));
		
		var f=document.premium.taxDedectSource.value.replace(new RegExp(',', 'g'),'');
		document.premium.taxDedectSource.value=Comma(parseFloat(f).toFixed(2));
		
		var g=document.premium.serviceTax.value.replace(new RegExp(',', 'g'),'');
		document.premium.serviceTax.value=Comma(parseFloat(g).toFixed(2));
		
		var h=document.premium.bonus.value.replace(new RegExp(',', 'g'),'');
		document.premium.bonus.value=Comma(parseFloat(h).toFixed(2));
		facNetDue();
		}
		function facNetDue()
		{
//          var iTotalCredit = form.find('name["totalCredit"]');
//          var iTotalDebit = form.find('name["totalDebit"]');
//          var iNetDue = form.find('name["netDue"]');
            var withHoldTax = form.find('input[name="withHoldingTaxOC"]').val() || form.find('input[name="withHoldingTaxOC"]').val("0").val();
            withHoldTax = withHoldTax.replace(new RegExp(',', 'g'),'');
				
		
				var PremiumQshare=document.premium.premiumQuotaShare.value;
				if(PremiumQshare!=null && PremiumQshare!="")
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
				else 
					PremiumQshare=0;
				var CommissionQshare=document.premium.commissionQuotaShare.value;
				if(CommissionQshare!=null && CommissionQshare!="")
					CommissionQshare=CommissionQshare.replace(new RegExp(',', 'g'),'');
				else
					CommissionQshare=0;
				var Brokerage=document.premium.brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				
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
				 	var bonus=document.premium.bonus.value;
				if(bonus!=null && bonus!="")
					bonus=bonus.replace(new RegExp(',', 'g'),'');
				else
				 	bonus=0;
				var A=parseFloat(PremiumQshare)+parseFloat(taxDedectSource)+parseFloat(serviceTax);
				var B=parseFloat(CommissionQshare)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost) + parseFloat(withHoldTax) + parseFloat(bonus);
				document.premium.totalCredit.value=Comma(A.toFixed(2));
				document.premium.totalDebit.value=Comma(B.toFixed(2));
				var NetDue=A-B;
				document.premium.netDue.value=Comma(NetDue.toFixed(2));
		}		
		
		function getViewContractDetails(prodId,proposalNo,amendId) {
		var URL ='';
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
			strOpen.focus();
			return false;
	}
	$(document).ready(function() {     
    $('#subProfitId').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 3,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
        	var val = $('#subProfitId').val();
	          var val1 =document.getElementById("preVal").value;
	          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
	          $("#subProfitId").multiselect('clearSelection');
	          val = removeElementsWithValue(val, 'ALL');
	          $("#subProfitId").val(val);
	           $("#subProfitId").multiselect("refresh");
	           document.getElementById("preVal").value = '';
	          }
	          else if (val !=null && val[0]=='ALL' ) {
	          	$("#subProfitId").multiselect('clearSelection');
	          	$("#subProfitId").val('ALL');
	          	 $("#subProfitId").multiselect("refresh");
	          	 document.getElementById("preVal").value = 'ALL';
	          }
	          else if(val== null || val[0]==''){
	          $("#subProfitId").multiselect('clearSelection');
	          $("#subProfitId").multiselect("refresh");
	          document.getElementById("preVal").value = '';
	          }
      	}                     
    }); 
    
     <s:if test='subProfitId!=null && !"".equals(subProfitId)'>	
 		var uwgrade='<s:property value="subProfitId"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#subProfitId").val(dataArray);
		 $("#subProfitId").multiselect("refresh");
	</s:if>    
           
});
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
(function(){
        $(document).on('click','input[type=text]',function(){ this.select(); });
    });
    
    
function funStatisticMode(proposalNo,ContractNo,departmentId,layerNo){
      //document.getElementById("proposal_No").value=proposalNo;
      document.getElementById("layerNo").value=layerNo;
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
 editMode();
	function editMode(){
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
		var URL='${pageContext.request.contextPath}/transactionListFaculPremium.action?proposal_No='+proposal_No+'&transaction='+transaction+'&dropDown='+id+'&transDropDownVal='+transDropDownVal+'&chooseTransaction='+val+'&mode='+mode;
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
if(account_Period==("RTP") || account_Period==("RVP")||account_Period==("EP")){
	document.premium.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do";
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
