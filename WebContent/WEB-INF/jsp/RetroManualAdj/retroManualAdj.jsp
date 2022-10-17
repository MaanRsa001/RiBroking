<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
					<s:form name="retroManualAdj" id="retroManualAdj" action="" theme="simple">				
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										Transaction Details
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='transactionNo!=null&&transactionNo!=""'>
											<div class="textfield">
												<div class="text ">
													<s:text name="Transaction No " />
												</div>
												<div class="tbox">
													<s:property value="transactionNo" />
												</div>
											</div>
												<div class="textfield">
												<div class="text">
													<s:text name="label.amendmentDate" />
												</div>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<sj:datepicker name="amendmentDate" id="amendmentDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />
													</div>	-->
													<s:textfield name="amendmentDate" id="amendmentDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" />												
												</div>
											</div>
											
											</s:if>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.Class" />
												</div>
												<div class="tbox">
													<%--<s:textfield name="departmentName" cssClass="inputBox" readonly="true"/>--%>
													<s:select name="predepartment" id="predepartment" list="DepartmentList" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  onchange="getAjax(this.value,'presubclass','');"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.subClass" />
												</div>
												<div class="tbox" id="presubclass">
													<s:select list="subProfit_centerlist" name="subProfitId" id="subProfitId" cssClass="inputBoxS" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" />
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDate" />
												</div>
												<s:if test='transactionNo!=null&&transactionNo!=""'>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" disabled="true" />												
												</div>
												</s:if>
												<s:else>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();" disabled="%{disableStatus}"/>													
												</div>
												</s:else>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwyear" />
												</div>
												<div class="tbox">													
														<s:select list="yearList" headerKey="0" headerValue="YEAR" name="uwYear" id="uwYear"  cssClass="inputBoxS" onchange="getContractList(this.value,'contractList','')"/>											
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.retroContract" />
												</div>
												<div class="tbox" id="contractList">													
													<s:select list="retroContractList" name="contNo" id="contNo"  listKey="RSK_CONTRACT_NO" listValue="RSK_CONTRACT_NO" headerKey="" cssClass="inputBoxS"  headerValue="---Select---"  onchange="getRetroDet(this.value,'retroDetails')"/>
													
												</div>
											</div>
											<div id="retroDetails">
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadretrocessionary" />
												</div>
												<div class="tbox">
													<s:textfield name="leadRetro" id="leadRetro"  cssClass="inputBox"    readonly="true"/>																						
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadbroker" />
												</div>
												<div class="tbox">
													<s:textfield name="leadBroker" id="leadBroker"  cssClass="inputBox"   readonly="true"/>																						
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.treatyName" />
												</div>
												<div class="tbox">
													<s:textfield name="treatyName" id="treatyName"  cssClass="inputBox"   readonly="true"/>																						
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
													<s:select name="currency" id="currId" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" cssStyle="width:30%;float:left;" headerValue="---Select---" onchange="GetExchangeRate()" disabled="%{disableStatus}"/>											
													<span id="exchRate">
													<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:left;text-align:right;" />
													</span>
												</div>
											</div>
											<s:hidden name="commssion_S" value="%{commission_view}" />	
											<s:hidden name="brokerage_S" value="%{brokerage_view}" />	
											<s:hidden name="tax_view" />	
											<s:hidden name="otherCost_S" value="%{otherCostView}" />	
											<s:hidden name="overRider_S" value="%{overRider_view}" />
											<s:hidden name="premiumReserve" id="premiumReserve"/>
											</div>
											
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.referenceDetails" />
												</div>
												<div class="tbox">													
														<s:textfield name="reference" id="reference"  cssClass="inputBox"  />											
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="Business Type" />
												</div>
												<div class="tbox">													
														<s:select name="businessType" id="businessType" list="#{'Inward':'Inward','Outward':'Outward'}"  headerKey="" cssClass="inputBoxS" headerValue="---Select---"  disabled="%{disableStatus}"/>											
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
											<table width="100%" class="table table-bordered">
												<tr >
												<td width="50%" >
													<table width="100%" class="table table-bordered">
														<thead>
														<tr>
															<th width="50%"></th>
															<th width="50%"><s:text name="label.credit" /></th>

														</tr>
														</thead>
														<tbody>
														
															<tr>
																<td>
																	<s:text name="label.premium" />
																</td>
																<td>
																	<s:textfield name="premiumQuotaShare" cssClass="inputBox" onblur="allow2DigitDecValues(this);;proposalNetDue();this.value=Comma(this.value);" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);"  onclick="this.select();"  maxlength="30" value="%{premiumQuotaShare==null?'0.00':premiumQuotaShare}" />
																</td>
															</tr>
														
														<tr>
															<td >
																<s:text name="label.premiumPortfolioIn" />
															</td>
															<td>
																<s:textfield name="premiumportifolioIn" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{premiumportifolioIn==null?'0.00':premiumportifolioIn}"/>
															</td>

														</tr>
														<tr> 
															<td>
																<s:text name="label.claimPortfolioIn" />
															</td>
															<td>
																<s:textfield name="cliamPortfolioin" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{cliamPortfolioin==null?'0.00':cliamPortfolioin}"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.premiumReserveReleased" />
															</td>
															<td>
																<s:textfield name="premium_Reserve_Released" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{premium_Reserve_Released==null?'0.00':premium_Reserve_Released}" />
																
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.lossReserveReleased" />
															</td>
															<td>
																<s:textfield name="lossReserveReleased" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{lossReserveReleased==null?'0.00':lossReserveReleased}"  />
																
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.interest" />
															</td>
															<td>
																<s:textfield name="interest" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{interest==null?'0.00':interest}"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.cashLossCredit" />
															</td>
															<td >
																<s:textfield name="cashLoss_Credit" cssClass="inputBox"  onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{cashLoss_Credit==null?'0.00':cashLoss_Credit}" />
																
															</td>

														</tr>
														<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
														<tr>
															<td><s:text name="label.taxdedect" /> </td>
															<td>
																<s:textfield cssClass="inputBox" name="taxDedectSource" id="taxDedectSource" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);" cssStyle="text-align: right;" onclick="this.select();" value="%{taxDedectSource==null?'0.00':taxDedectSource}" maxlength="26"/>
															</td>

														</tr>
												
														<tr>
															<td><s:text name="label.gst" /> </td>
															<td>
																<s:textfield cssClass="inputBox" name="serviceTax" id="serviceTax" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);" onclick="this.select();" value="%{serviceTax==null?'0.00':serviceTax}" maxlength="26"/>
															</td>

														</tr>
														</s:if>
														
														<tr>
															<td><s:text name="label.lossPor" /> </td>
															<td>
																<s:textfield cssClass="inputBox" name="lossParticipation" id="lossParticipation" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  cssStyle="text-align: right;" onclick="this.select();" onkeyup="allow2DigitDecValues(this);"  value="%{lossParticipation==null?'0.00':lossParticipation}" maxlength="26"/>
																<%--<span id="lossPat"><input type="button"  size="2"  value="..." onclick="SlideCommission('loss')" class="pullRight"/></span>		--%>												
															</td>
														</tr>
														<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
														<tr>
															<td colspan="2">&nbsp; <s:hidden name="taxDedectSource" id="taxDedectSource"/></td>

														</tr>
														<tr>
															<td colspan="2">&nbsp;<s:hidden name="serviceTax" id="serviceTax"/></td>

														</tr>
														</s:if>
														<tr>
															<td colspan="2">&nbsp;</td>

														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>

														</tr>
														<tr>
																<td colspan="2">&nbsp;</td>

														</tr>
														
														<tr>
															<td colspan="2">&nbsp;</td>

														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.total" />
															</td>
															<td>
																<s:textfield name="totalCredit" cssClass="inputBox" readonly="true"   onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{totalCredit==null?'0.00':totalCredit}"/>
															</td>

														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>

														</tr>
														</tbody>
													</table>

												</td>
												<td width="50%" >
												<table width="100%" class="table table-bordered">
													<thead>
													<tr>
														<th width="50%"></th>
														<th width="50%"><s:text name="label.debit" /></th>
													</tr>
													</thead>
													<tbody>
														<tr>
															<td>
																	<s:text name="label.commission" />
															</td>
															<td>
																<s:textfield name="commissionQuotaShare" cssClass="inputBox" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);" onclick="this.select();" maxlength="30" value="%{commissionQuotaShare==null?'0.00':commissionQuotaShare}"/>
															</td>
														</tr>
													
													<tr >

														<td >
															<s:text name="label.slideScaleComm" />
														</td>
														<td  >
															<s:textfield name="slideScaleCom" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);checkDecimals15(this);proposalNetDue();"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{slideScaleCom==null?'0.00':slideScaleCom}"/>
															<%-- <input type="button"  size="2"  value="..." onclick="SlideCommission('slide')" class="pullRight"/>--%>
														</td>
													</tr>
													
													<tr>

														<td>
															<s:text name="label.brokerage" />
														</td>
														<td>
															<s:textfield name="brokerage" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{brokerage==null?'0.00':brokerage}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.taxExpense" />
														</td>
														<td>
															<s:textfield name="tax" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{tax==null?'0.00':tax}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.WithHoldingTax" />
														</td>
														<td>
															<s:textfield name="withHoldingTaxOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{withHoldingTaxOC==null?'0.00':withHoldingTaxOC}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.iwOverrider" />
														</td>
														<td>
															<s:textfield name="overrider" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{overrider==null?'0.00':overrider}"/>
														</td>


													</tr>
													<tr>

														<td>
															<s:text name="label.otherCost" />
														</td>
														<td>
															<s:textfield name="otherCost" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{otherCost==null?'0.00':otherCost}"/>
														</td>


													</tr>
													
													<s:hidden name="xl_Cost" id="xl_Cost" value="0"/>

													<tr>

														<td>
															<s:text name="label.premiumPortfolioOut" />
														</td>
														<td>
															<s:textfield name="premiumportifolioout" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{premiumportifolioout==null?'0.00':premiumportifolioout}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.claimPortfolioOut" />
														</td>
														<td>
															<s:textfield name="cliam_portfolio_out" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{cliam_portfolio_out==null?'0.00':cliam_portfolio_out}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.premiumReserveRetained" />
														</td>
														<td>
															<s:textfield name="premiumReserve_QuotaShare" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);;"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);" maxlength="30" value="%{premiumReserve_QuotaShare==null?'0.00':premiumReserve_QuotaShare}" />
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.lossReserveRetained" />
														</td>
														<td>
															<s:textfield name="loss_ReserveRetained" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);" maxlength="30" value="%{loss_ReserveRetained==null?'0.00':loss_ReserveRetained}"/>
														</td>
													</tr>
														<tr>

															<td>
																<s:text name="label.profitCommission" />
															</td>
															<td>
																<s:textfield name="profit_Commission" id="profit_Commission" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);proposalNetDue();"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{profit_Commission==null?'0.00':profit_Commission}"/>
																<%--<span id="ProfitComm"><input type="button"  size="2"  value="..." onclick="SlideCommission('profit')" class="pullRight"/></span>--%>
															</td>
														</tr>
													
													<tr>

														<td>
															<s:text name="label.cashLossPaid" />
														</td>
														<td>
															<s:textfield name="cash_LossPaid" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);proposalNetDue();"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{cash_LossPaid==null?'0.00':cash_LossPaid}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.claimsPaid" />
														</td>
														<td>
															<s:textfield name="claims_paid" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);proposalNetDue();"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{claims_paid==null?'0.00':claims_paid}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.total" />
														</td>
														<td>
															<s:textfield name="totalDebit" cssClass="inputBox" cssStyle="text-align: right;" readonly="true"  onclick="this.select();" maxlength="30" value="%{totalDebit==null?'0.00':totalDebit}"/>
														</td>
													</tr>
													<tr>

														<td>
															<s:text name="label.netDue" />
														</td>
														<td>
															<s:textfield name="netDue" readonly="true" cssClass="inputBox" cssStyle="text-align: right;"  onclick="this.select();" maxlength="30" value="%{netDue==null?'0.00':netDue}"/>
														</td>
													</tr>


													</tbody>
												</table>
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
												<tr>
													<td>
																<s:text name="label.outStandingClaimsLoss" /><span id="accountPrd" style="display:none"> </span>
															</td>
															<td>
																<s:textfield name="osClaimsLossUpdateOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{osClaimsLossUpdateOC==null?'0.00':osClaimsLossUpdateOC}" />
													</td>
												</tr>
											</table>
											<br class="clear"/>
											<div id="obsList"></div>
											
											
										</div>
									</div>
								</div>
							</div>							
						</div>
						<%-- <div class="tablerow">
							<div class="boxcontent">
							<div class="textfield">
							<div class="text">
								<s:label key="label.cashlossdetails" />
							</div>
							</div>
								<div class="panel panel-primary">			
									<div class="panel-body">
										<display:table name="CliamList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />										
											<display:column sortable="true" style="text-align:center;" title="Claim No" property="claim_No" />
											<display:column sortable="true" style="text-align:center;" title="Date Of Loss" property="date_of_Loss" />
											<display:column sortable="true" style="text-align:center;" title="Claim Registered Date" property="created_Date" />
											<display:column sortable="true" style="text-align:center;" title="Status Of Claim" property="status_of_claim" />
											<s:if test='#session.MenuRights.indexOf("CV")!=-1'>
											<display:column sortable="true" style="text-align:center;" title=" View ">
												<a title="View" style="cursor: pointer;" onclick="ViewMode('${record.policy_Contract_No}','${record.claim_No }')">View</a>
											</display:column>
											</s:if>
											</display:table>										
									</div>
								</div>
							</div>
						</div>--%>
						<s:hidden name="receipt_no" />
													
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							    <input type="button" value="Submit" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate('new')" />
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
						
							</div>
						</div>
						
					</div>
					<s:hidden name="CalculateTax" id="CalculateTax"/>	
					<s:hidden name="premiumReserveUser" id="premiumReserveUser"/>
					<s:hidden name="preVal" id="preVal"/>
					<s:hidden name="transactionNo" id="transactionNo"/>
					<s:hidden name="PRTransNo" id="PRTransNo"/>
					<s:hidden name="PRAmount" id="PRAmount"/>
					<s:hidden name="PREAmount" id="PREAmount"/>
					<s:hidden name="PRERate" id="PRERate"/>
					<s:hidden name="LRTransNo" id="LRTransNo"/>
					<s:hidden name="LRAmount" id="LRAmount"/>
					<s:hidden name="LREAmount" id="LREAmount"/>
					<s:hidden name="LRERate" id="LRERate"/>
					<s:hidden name="multiuserError" id="multiuserError"/>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function cancel1(){
	document.retroManualAdj.action='${pageContext.request.contextPath}/retroPremListRetroAdj.action';
	document.retroManualAdj.submit();
}
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
        var form = $('form[name="premium"]');
		

		function insertupdate(mode)
		{
		//twoDecimal();
		document.retroManualAdj.action='${pageContext.request.contextPath}/insertPremiumRetroAdj.action?productId=<s:property value="productId"/>&mode='+mode;
		document.retroManualAdj.submit();
		}
		<%--var premiumA=document.getElementById("previousPremium").value;
		var contractprmium=document.getElementById("contractPremium").value;
		 var PQS=document.retroManualAdj.premiumShare.value;
		  var exchange=document.retroManualAdj.exchRate.value;
		  PQS=PQS.replace(new RegExp(',','g'),'');
		 
		var premiumB=parseFloat(PQS);
		var premiumC=parseFloat(premiumA)+parseFloat(premiumB);
		var premiumD=parseFloat(premiumC)/exchange;
		var premiumE=parseFloat(contractprmium)/exchange;
		var accperiod = document.getElementById("accountPeriodDate").value;
		if(accperiod==''){
		alert("Pleasr Select Accounting Period");
		}else{
		if(premiumD>premiumE){
		var test= confirm("Premium is greater that EPI as per contract.  Do you wish to continue?");
	
		if(test){
		enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
		document.retroManualAdj.action='${pageContext.request.contextPath}/validationAlertProportionPremium.do?productId=<s:property value="productId"/>';
		document.retroManualAdj.submit();
		}
		}else{
		}
		}	
		--%>
		
	 	
		 var val = '<s:property value="alertShow"/>';
		 if("Y"==val){
		 premiumSubmit();
		 }	
		 
		 function premiumSubmit(){
		 var allalert='<s:property value="allAlert"/>';
		 var pcalert='<s:property value="pcAlert"/>';
		 var scalert='<s:property value="scAlert"/>';
		 var lcalert='<s:property value="lcAlert"/>';
		 if(allalert=="Y"){
		  answer = confirm("The Statement for the previous period is not received.  Do you wish to continue?");
		 }else if(pcalert=="Y"){
		  answer = confirm("Profit Commission Adjustment for this contract is to be calculated upon account statement submission.  You have not keyed in value in Profit Commission.  Do you wish to continue?");
		 }else if(scalert=="Y"){
		  answer = confirm("Sliding Scale Commission Adjustment for this contract is to be calculated upon account statement submission.  You have not keyed in value in Sliding Scale Commission.  Do you wish to continue?");
		 }else if(lcalert=="Y"){
		  answer = confirm("Loss Participation Adjustment for this contract is to be calculated upon account statement submission.  You have not keyed in value in Loss Participation.  Do you wish to continue?");
		 }
		 if(answer){
		 	premiumInsert();
			}
		 }	
		 
		 function premiumInsert(){
		 	enableForm(document.forms['premium'],false,'transaction,currency,enteringMode');
		 	document.retroManualAdj.action='${pageContext.request.contextPath}/insertPremiumProportionPremium.do?productId=<s:property value="productId"/>';
			document.retroManualAdj.submit();
		 }
		 
		function EditBack()
		{
		document.retroManualAdj.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init";
		document.retroManualAdj.submit();
		}
		function ViewMode1(policy_Contract_No,claim_No) { 
		var proNo = document.getElementById("proposal_No").value;
		var layer = document.getElementById("layerNo").value;			
		document.forms[0].action="${pageContext.request.contextPath}/claimViewClaim.do?mode=Bmode&claim_No="+claim_No+"&contarctno="+policy_Contract_No+"&proposal_No="+proNo+"&layerNo="+layer+"&premiumclaimMode=Yes";
		document.forms[0].submit();
	}
	function GetSection()
		{
			 	var contractNo=document.retroManualAdj.contNo.value;				
				var deptId=document.retroManualAdj.departmentId.value;
				var section=document.retroManualAdj.sectionName.value;
				var sectionType=document.retroManualAdj.sectionType.value;	
				if(section==null || section==''){
				alert("Please Enter Section Name");
				}else{							
			 	if(contractNo!=null && contractNo!="" && deptId!=null && deptId!="" &&section!=null && section!="" && sectionType!=null && sectionType!="")
				{
				document.retroManualAdj.sectionType.value='2';
					var URL='<%=request.getContextPath()%>/GetSectionProportionPremium.action?contNo='+contractNo+'&departmentId='+deptId+'&sectionName='+section+'&sectionType='+sectionType+'&dropDown=section';
  	 				postRequest(URL,'section');					
				}
				}
		}
	function ViewMode(policy_Contract_No,claim_No) {
		var URL ='';
		var proNo = document.getElementById("proposal_No").value;
		var layer = document.getElementById("layerNo").value;
		
			URL ='${pageContext.request.contextPath}/claimViewClaim.do?mode=Bmode&claim_No='+claim_No+'&contarctno='+policy_Contract_No+'&proposal_No='+proNo+'&layerNo='+layer+'&premiumclaimMode=Yes';
		
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
	function CashLossCredit(){
	var contrctNo = document.getElementById("contNo").value;
	var enteringMode=document.retroManualAdj.enteringMode.value;
	var val = document.getElementById("claimPaymentNo").value;
	var val1 = document.getElementById("creditAmountCLC").value;
	var val2 = document.getElementById("creditAmountCLD").value;
	var val3 = document.getElementById("CLCsettlementRate").value;
	
		//var currencyid = document.getElementById("baseCurrencyId").value;
		var currencyid = document.getElementById("currId").value;
		if(enteringMode==1){
	 	alert("This facility is not available when 100% figures are being keyed in");
		}
		if(enteringMode!=1){
		document.getElementById("_enteringMode1").disabled = true;
        document.getElementById("_enteringMode2").disabled = true;
		var URL ='${pageContext.request.contextPath}/cashLossCreditProportionPremium.do?contNo='+contrctNo+'&currencyId='+currencyid+'&mainclaimPaymentNos='+val+'&maincreditAmountCLClist='+val1+'&maincreditAmountCLDlist='+val2+'&mainCLCsettlementRatelist='+val3;
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

function SlideCommission(mode){
    var contrctNo = document.getElementById("contNo").value;
    var transaction = document.getElementById("transaction").value;
    var currency = document.getElementById("currId").value;
    var exchRate = document.retroManualAdj.exchRate.value;
    var slideScenario = document.getElementById("slideScenario").value;
	var transactionNo = document.getElementById("transactionNo").value;
	var statementDate = document.getElementById("statementDate").value;
	
	var conPremiumOC= document.getElementById("conPremiumOC").value;
	var conClaimPaidOC= document.getElementById("conClaimPaidOC").value;
	var conClaimOutStandingOC= document.getElementById("conClaimOutStandingOC").value;
	var conClaimRatioOC= document.getElementById("conClaimRatioOC").value;
	var conSlideScaleCommOC= document.getElementById("conSlideScaleCommOC").value;
	var conCommPaidOC= document.getElementById("conCommPaidOC").value;
	var conSlideScaleAdjOC= document.getElementById("conSlideScaleAdjOC").value;
	var proposal_No = document.getElementById("proposal_No").value;
	
    if(transaction ==null || transaction ==''){
        alert("Please Select Transaction Date ");
	}
	if(currency == null || currency ==''){
        alert("Please Select Currency");
	}
	if(statementDate == null || statementDate ==''){
        alert("Please Select Statement Date");
	}
	if(transaction !=null && transaction !='' && currency != null && currency !='' &&(exchRate == null || exchRate =='')){
	    alert("Please Enter Exchange Rate");
	}
	
	if(transaction !=null && transaction !='' && currency != null && currency !='' && exchRate != null && exchRate!='' && statementDate != null && statementDate !=''){
	    document.retroManualAdj.transaction.disabled = true;
        document.retroManualAdj.currency.disabled = true;
        document.retroManualAdj.statementDate.disabled = true;
        
    var URL ='${pageContext.request.contextPath}/slidCommissionProportionPremium.do?mode='+mode+'&contNo='+contrctNo+'&proposal_No='+proposal_No+'&transaction='+transaction+'&currencyId='+currency+'&exchRate='+exchRate+'&slideScenario='+slideScenario+'&transactionNo='+transactionNo+'&statementDate='+statementDate+'&conPremiumOC='+conPremiumOC+'&conClaimPaidOC='+conClaimPaidOC+'&conClaimOutStandingOC='+conClaimOutStandingOC+'&conClaimRatioOC='+conClaimRatioOC+'&conSlideScaleCommOC='+conSlideScaleCommOC+'&conCommPaidOC='+conCommPaidOC+'&conSlideScaleAdjOC='+conSlideScaleAdjOC;
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
function premuimReserved(value){
	var contrctNo = document.getElementById("contNo").value;
	var enteringMode=document.retroManualAdj.enteringMode.value;
	if(value=='PRR'){
	var val = document.getElementById("PRTransNo").value;
	var val1 = document.getElementById("PRAmount").value;
	var val2 = document.getElementById("PREAmount").value;
	var val3 = document.getElementById("PRERate").value;
	}else if(value=='LRR'){
	var val = document.getElementById("LRTransNo").value;
	var val1 = document.getElementById("LRAmount").value;
	var val2 = document.getElementById("LREAmount").value;
	var val3 = document.getElementById("LRERate").value;
	
	}
		//var currencyid = document.getElementById("baseCurrencyId").value;
		var currencyid = document.getElementById("currId").value;
		var transDate=document.getElementById("transaction").value;
		if(transDate ==null || transDate ==''){
        alert("Please Select Transaction Date ");
		}
		if(currencyid == null || currencyid ==''){
        alert("Please Select Currency");
		}if(enteringMode==1){
	 	alert("This facility is not available when 100% figures are being keyed in");
		}
		if(transDate !=null && transDate !='' && currencyid != null && currencyid !='' && enteringMode!=1){
		document.retroManualAdj.transaction.disabled = true;
        document.retroManualAdj.currency.disabled = true;
        
        document.getElementById("_enteringMode1").disabled = true;
        document.getElementById("_enteringMode2").disabled = true;
		var URL ='${pageContext.request.contextPath}/PremiumReservedProportionPremium.do?contNo='+contrctNo+'&currencyId='+currencyid+'&transaction='+transDate+'&mainclaimPaymentNos='+val+'&maincreditAmountCLClist='+val1+'&maincreditAmountCLDlist='+val2+'&mainCLCsettlementRatelist='+val3+'&type='+value;
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
		strOpen.focus();
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		return false;
		}
}
		function EditMode()
		{
		document.retroManualAdj.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=PremiumSearch";
		}

		function back(premiumMasterMode)
		{
		destroyPopUps();
		var contNo=document.getElementById("contNo").value;   
		document.forms[0].action="${pageContext.request.contextPath}/premiumListProportionPremium.do?mode=add&contNo="+contNo+"&premiumMasterMode="+premiumMasterMode;
		document.forms[0].submit();	
		}
		 function cancel()
   		{
   			   destroyPopUps();
  			   var menuId=document.getElementById("menuId").value;  
  			   document.location='<%=request.getContextPath()%>/InitPortfolio.action?flag=C&menuId='+menuId;
 		}
		
		function Calculation(value)
		{
		var calc = document.getElementById("CalculateTax").value ;
		var transactionNo = document.getElementById("transactionNo").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && transactionNo==""){
		var PremiumQshare=document.retroManualAdj.premiumQuotaShare.value;
		PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		var commssion=document.retroManualAdj.commssion_S.value;
		var percentage=parseFloat(commssion)/100;
		var b=value.replace(new RegExp(',', 'g'),'');
		var a=parseFloat(b)*percentage;
	 	if(value!="")
		{
		document.retroManualAdj.commissionQuotaShare.value=Comma(a.toFixed(2));
		}
		else
		{
		document.retroManualAdj.commissionQuotaShare.value='';
		}
		var brokerage=document.retroManualAdj.brokerage_S.value;
		var brokerpercentage=parseFloat(brokerage)/100;
	   
			if(PremiumQshare!=""  )
			{
				var b=parseFloat(PremiumQshare);
				var c=b*brokerpercentage;
				document.retroManualAdj.brokerage.value=Comma(c.toFixed(2));
				var tax_view=document.retroManualAdj.tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var d=b*tax_percen;
				document.retroManualAdj.tax.value=Comma(d.toFixed(2));
				var otherCost=document.retroManualAdj.otherCost_S.value;
				var percentage3=parseFloat(otherCost)/100;
				var e=b*percentage3;
				document.retroManualAdj.otherCost.value=Comma(e.toFixed(2));
				var overrider=document.retroManualAdj.overRider_S.value;
				var percentage4=parseFloat(overrider)/100;
				var f=b*percentage4;
				document.retroManualAdj.overrider.value=Comma(f.toFixed(2));
				
			}
				else if(PremiumQshare!="")
				{
					var b=parseFloat(PremiumQshare);
					var c=b*brokerpercentage;
					document.retroManualAdj.brokerage.value=Comma(c.toFixed(2));
					var tax_view=document.retroManualAdj.tax_view.value;
					var tax_percen=parseFloat(tax_view)/100;
					var d=b*tax_percen;
					document.retroManualAdj.tax.value=Comma(d.toFixed(2));
					var otherCost=document.retroManualAdj.otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var e=b*percentage3;
					document.retroManualAdj.otherCost.value=Comma(e.toFixed(2));
					var overrider=document.retroManualAdj.overRider_S.value;
					var percentage4=parseFloat(overrider)/100;
					var f=b*percentage4;
					document.retroManualAdj.overrider.value=Comma(f.toFixed(2));
				}
					else if(PremiumQshare== "" )
					{
						document.retroManualAdj.commissionQuotaShare.value=''
					    document.retroManualAdj.brokerage.value='';
						document.retroManualAdj.tax.value='';
						document.retroManualAdj.otherCost.value='';
						document.retroManualAdj.overrider.value='';
					} 
						    }
		</s:if>
		}
		function Caculate(value)
		{
		var calc = document.getElementById("CalculateTax").value ;
		var transactionNo = document.getElementById("transactionNo").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && transactionNo==""){
			if(value!=null && value!="")
			{
					var PremiumQshare=document.retroManualAdj.premiumQuotaShare.value;
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
					var commssion=document.retroManualAdj.commssion_S.value;
					var percentage=parseFloat(commssion)/100;
					var a=parseFloat(PremiumQshare)*percentage;
					document.retroManualAdj.commissionQuotaShare.value=Comma(a.toFixed(2));
					var brokarage=document.retroManualAdj.brokerage_S.value;
					var percentage1=parseFloat(brokarage)/100;
					var b=parseFloat(PremiumQshare)*percentage1;
					document.retroManualAdj.brokerage.value=Comma(b.toFixed(2));
					var tax=document.retroManualAdj.tax_S.value;
					var percentage2=parseFloat(tax)/100;
					var c=parseFloat(PremiumQshare)*percentage2;
					document.retroManualAdj.tax.value=Comma(c.toFixed(2));
					var otherCost=document.retroManualAdj.otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var d=parseFloat(PremiumQshare)*percentage3;
					document.retroManualAdj.otherCost.value=Comma(d.toFixed(2));
		    }
		    else
		    {
				    document.retroManualAdj.commissionQuotaShare.value='';
				    document.retroManualAdj.brokerage.value='';
				    document.retroManualAdj.tax.value='';
				    document.retroManualAdj.otherCost.value='';
			}
			document.getElementById("CalculateTax").value = "Completed";
		}
		</s:if>
		}
		function calculationSurplus(value)
		{
		var calc = document.getElementById("CalculateTax").value ;
		var transactionNo = document.getElementById("transactionNo").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && transactionNo==""){
			
			
			var brokerage=document.retroManualAdj.brokerage_S.value;
			var brokerpercentage=parseFloat(brokerage)/100;
			var PremiumQshare=document.retroManualAdj.premiumQuotaShare.value;
			PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		if(PremiumQshare!=""  )
		{
			var b=parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.retroManualAdj.brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.retroManualAdj.tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.retroManualAdj.tax.value=Comma(d.toFixed(2));
			var otherCost=document.retroManualAdj.otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.retroManualAdj.otherCost.value=Comma(e.toFixed(2));
			var overrider=document.retroManualAdj.overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.retroManualAdj.overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare!="")
		{
			var b=parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.retroManualAdj.brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.retroManualAdj.tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.retroManualAdj.tax.value=Comma(d.toFixed(2));
			var otherCost=document.retroManualAdj.otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.retroManualAdj.otherCost.value=Comma(e.toFixed(2));
			var overrider=document.retroManualAdj.overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.retroManualAdj.overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare== "" )
		{
		document.retroManualAdj.tax.value='';
		document.retroManualAdj.brokerage.value='';
		document.retroManualAdj.otherCost.value='';
		document.retroManualAdj.overrider.value='';
		}	
		}
		</s:if>
	 }		
	
   
		function GetExchangeRate()
		{
			 	var month=document.retroManualAdj.transaction.value;				
				var Currecny=document.getElementById("currId").value;								
			 	if(month!=null && month!="" && Currecny!="0" )
				{
					//document.retroManualAdj.currencyId.value=Currecny;
					var URL='<%=request.getContextPath()%>/ajaxValueRetroAdj.action?transaction='+month+'&currId='+Currecny+'&dropDown=exchRate';
  	 				postRequest(URL,'exchRate');					
				}else
				{
						document.retroManualAdj.exchRate.value='';
				}
		}
			
		function twoDecimal(){

		var a=document.retroManualAdj.cashLoss_Credit.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.cashLoss_Credit.value=Comma(parseFloat(a).toFixed(2));
		
		var b=document.retroManualAdj.cliam_portfolio_out.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.cliam_portfolio_out.value=Comma(parseFloat(b).toFixed(2));
		
		var c=document.retroManualAdj.retroManualAdjportifolioout.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.premiumportifolioout.value=Comma(parseFloat(c).toFixed(2));
		
		var d=document.retroManualAdj.premiumReserve_QuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.premiumReserve_QuotaShare.value=Comma(parseFloat(d).toFixed(2));
		
		var e=document.retroManualAdj.profit_Commission.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.profit_Commission.value=Comma(parseFloat(e).toFixed(2));
		
		var f=document.retroManualAdj.cash_LossPaid.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.cash_LossPaid.value=Comma(parseFloat(f).toFixed(2));
		
		var g=document.retroManualAdj.claims_paid.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.claims_paid.value=Comma(parseFloat(g).toFixed(2));
		
		
		var j=document.retroManualAdj.premiumportifolioIn.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.premiumportifolioIn.value=Comma(parseFloat(j).toFixed(2));
		
		var k=document.retroManualAdj.otherCost.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.otherCost.value=Comma(parseFloat(k).toFixed(2));
		
		var l=document.retroManualAdj.lossReserveReleased.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.lossReserveReleased.value=Comma(parseFloat(l).toFixed(2));
		
		var m=document.retroManualAdj.xl_Cost.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.xl_Cost.value=Comma(parseFloat(m).toFixed(2));
		
		var n=document.retroManualAdj.brokerage.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.brokerage.value=Comma(parseFloat(n).toFixed(2));
		
		var o=document.retroManualAdj.retroManualAdj_Reserve_Released.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.premium_Reserve_Released.value=Comma(parseFloat(o).toFixed(2));
		
		var p=document.retroManualAdj.tax.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.tax.value=Comma(parseFloat(p).toFixed(2));
		
		var q=document.retroManualAdj.cliamPortfolioin.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.cliamPortfolioin.value=Comma(parseFloat(q).toFixed(2));
		
		var r=document.retroManualAdj.loss_ReserveRetained.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.loss_ReserveRetained.value=Comma(parseFloat(r).toFixed(2));
		
		var s=document.retroManualAdj.commissionQuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.commissionQuotaShare.value=Comma(parseFloat(s).toFixed(2));

		var t=document.retroManualAdj.premiumQuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.premiumQuotaShare.value=Comma(parseFloat(t).toFixed(2));
		
		var w=document.retroManualAdj.netDue.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.netDue.value=Comma(parseFloat(w).toFixed(2));
		
		var u=document.retroManualAdj.interest.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.interest.value=Comma(parseFloat(u).toFixed(2));
		
		var v=document.retroManualAdj.withHoldingTaxOC.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.withHoldingTaxOC.value=Comma(parseFloat(v).toFixed(2));
		
		var x=document.retroManualAdj.overrider.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.overrider.value=Comma(parseFloat(x).toFixed(2));
		
		var y=document.retroManualAdj.taxDedectSource.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.taxDedectSource.value=Comma(parseFloat(y).toFixed(2));
		
		var z=document.retroManualAdj.serviceTax.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.serviceTax.value=Comma(parseFloat(z).toFixed(2));
		
		var aa=document.retroManualAdj.lossParticipation.value.replace(new RegExp(',', 'g'),'');
		document.retroManualAdj.lossParticipation.value=Comma(parseFloat(aa).toFixed(2));
		
		 proposalNetDue();
			    //document.premium.cliam_portfolio_out.value=Comma(document.premium.cliam_portfolio_out.value);
			    //document.premium.premiumportifolioout.value=Comma(document.premium.premiumportifolioout.value);
			  //d  document.premium.premiumReserve_QuotaShare.value=Comma(document.premium.premiumReserve_QuotaShare.value);
			  //e  document.premium.profit_Commission.value=Comma(document.premium.profit_Commission.value);
			    
			 //f   document.premium.cash_LossPaid.value=Comma(document.premium.cash_LossPaid.value);
			//g    document.premium.claims_paid.value=Comma(document.premium.claims_paid.value);
			 //h   document.premium.premiumSurplus.value=Comma(document.premium.premiumSurplus.value);
			  //i  document.premium.commissionSurplus.value=Comma(document.premium.commissionSurplus.value);
			  //j  document.premium.premiumportifolioIn.value=Comma(document.premium.premiumportifolioIn.value);
			    
			   //k document.premium.otherCost.value=Comma(document.premium.otherCost.value);
			   //l document.premium.lossReserveReleased.value=Comma(document.premium.lossReserveReleased.value);
			   //m document.premium.xl_Cost.value=Comma(document.premium.xl_Cost.value);
			   //n document.premium.brokerage.value=Comma(document.premium.brokerage.value);
			   //o document.premium.premium_Reserve_Released.value=Comma(document.premium.premium_Reserve_Released.value);

			   //p document.premium.tax.value=Comma(document.premium.tax.value);
			   //q document.premium.cliamPortfolioin.value=Comma(document.premium.cliamPortfolioin.value);
			   //r document.premium.loss_ReserveRetained.value=Comma(document.premium.loss_ReserveRetained.value);
			  //s  document.premium.commissionQuotaShare.value=Comma(document.premium.commissionQuotaShare.value);
			   //t document.premium.premiumQuotaShare.value=Comma(document.premium.premiumQuotaShare.value);
			  //w  document.premium.netDue.value=Comma(document.premium.netDue.value);
			    proposalNetDue();
		}
		proposalNetDue();
		function proposalNetDue()
		{
           var withHoldingTaxI = document.retroManualAdj.withHoldingTaxOC.value;
            var wHTax = withHoldingTaxI.replace(new RegExp(',', 'g'),'');
				var PremiumQshare=document.retroManualAdj.premiumQuotaShare.value;
				if(PremiumQshare!=null && PremiumQshare!="")
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
				else 
					PremiumQshare=0;
				var PremiumportifolioIn=document.retroManualAdj.premiumportifolioIn.value;
				if(PremiumportifolioIn!=null && PremiumportifolioIn!="")
					PremiumportifolioIn=PremiumportifolioIn.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumportifolioIn=0;
				
				var CliamPortfolioin=document.retroManualAdj.cliamPortfolioin.value;
				if(CliamPortfolioin!=null && CliamPortfolioin!="")
					CliamPortfolioin=CliamPortfolioin.replace(new RegExp(',', 'g'),'');
				else
				 	CliamPortfolioin=0;
				var Premium_Reserve_Released=document.retroManualAdj.premium_Reserve_Released.value;
				if(Premium_Reserve_Released!=null && Premium_Reserve_Released!="")
					Premium_Reserve_Released=Premium_Reserve_Released.replace(new RegExp(',', 'g'),'');
				else
				 	Premium_Reserve_Released=0;
				var LossReserveReleased=document.retroManualAdj.lossReserveReleased.value;
				if(LossReserveReleased!=null && LossReserveReleased!="")
					LossReserveReleased=LossReserveReleased.replace(new RegExp(',', 'g'),'');
				else
				 	LossReserveReleased=0;
				var Interest=document.retroManualAdj.interest.value;
				if(Interest!=null && Interest!="")
					Interest=Interest.replace(new RegExp(',', 'g'),'');
				else
				 	Interest=0; 	
				var CashLoss_Credit=document.retroManualAdj.cashLoss_Credit.value;
				if(CashLoss_Credit!=null && CashLoss_Credit!="")
					CashLoss_Credit=CashLoss_Credit.replace(new RegExp(',', 'g'),'');
				else
				 	CashLoss_Credit=0;
			
				
				var CommissionQshare=document.retroManualAdj.commissionQuotaShare.value;
				if(CommissionQshare!=null && CommissionQshare!="")
					CommissionQshare=CommissionQshare.replace(new RegExp(',', 'g'),'');
				else
					CommissionQshare=0;
				
				
				var Brokerage=document.retroManualAdj.brokerage.value;
				if(Brokerage!=null && Brokerage!="")
					Brokerage=Brokerage.replace(new RegExp(',', 'g'),'');
				else
				 	Brokerage=0;
				var Tax=document.retroManualAdj.tax.value;
				if(Tax!=null && Tax!="")
					Tax=Tax.replace(new RegExp(',', 'g'),'');
				else
				 	Tax=0;
				var overrider=document.retroManualAdj.overrider.value;
				if(overrider!=null && overrider!="")
					overrider=overrider.replace(new RegExp(',', 'g'),'');
				else
				 	overrider=0; 	
				
				var OtherCost=document.retroManualAdj.otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;
				
				var Xl_Cost=document.retroManualAdj.xl_Cost.value;
				if(Xl_Cost!=null && Xl_Cost!="")
					Xl_Cost=Xl_Cost.replace(new RegExp(',', 'g'),'');
				else
				 	Xl_Cost=0;
				var Premiumportifolioout=document.retroManualAdj.premiumportifolioout.value;
				if(Premiumportifolioout!=null && Premiumportifolioout!="")
					Premiumportifolioout=Premiumportifolioout.replace(new RegExp(',', 'g'),'');
				else
				 	Premiumportifolioout=0;
				var Cliam_portfolio_out=document.retroManualAdj.cliam_portfolio_out.value;
				if(Cliam_portfolio_out!=null && Cliam_portfolio_out!="")
					Cliam_portfolio_out=Cliam_portfolio_out.replace(new RegExp(',', 'g'),'');
				else
				 	Cliam_portfolio_out=0;
				
				var PremiumReserve_QuotaShare=document.retroManualAdj.premiumReserve_QuotaShare.value;
				if(PremiumReserve_QuotaShare!=null && PremiumReserve_QuotaShare!="")
					PremiumReserve_QuotaShare=PremiumReserve_QuotaShare.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumReserve_QuotaShare=0;
				var Loss_ReserveRetained=document.retroManualAdj.loss_ReserveRetained.value;
				if(Loss_ReserveRetained!=null && Loss_ReserveRetained!="")
					Loss_ReserveRetained=Loss_ReserveRetained.replace(new RegExp(',', 'g'),'');
				else
				 	Loss_ReserveRetained=0;
				var Profit_Commission=document.retroManualAdj.profit_Commission.value;
				if(Profit_Commission!=null && Profit_Commission!="")
					Profit_Commission=Profit_Commission.replace(new RegExp(',', 'g'),'');
				else
				 	Profit_Commission=0;
				
				var Cash_LossPaid=document.retroManualAdj.cash_LossPaid.value;
				if(Cash_LossPaid!=null && Cash_LossPaid!="")
					Cash_LossPaid=Cash_LossPaid.replace(new RegExp(',', 'g'),'');
				else
				 	Cash_LossPaid=0;
				var Claims_paid=document.retroManualAdj.claims_paid.value;
				if(Claims_paid!=null && Claims_paid!="")
					Claims_paid=Claims_paid.replace(new RegExp(',', 'g'),'');
				else
				 	Claims_paid=0;
				 	
				var taxDedectSource=document.retroManualAdj.taxDedectSource.value;
				if(taxDedectSource!=null && taxDedectSource!="")
					taxDedectSource=taxDedectSource.replace(new RegExp(',', 'g'),'');
				else
				 	taxDedectSource=0;
				 	
				var serviceTax=document.retroManualAdj.serviceTax.value;
				if(serviceTax!=null && serviceTax!="")
					serviceTax=serviceTax.replace(new RegExp(',', 'g'),'');
				else
				 	serviceTax=0;
				 	
				 var lossParticipation=document.retroManualAdj.lossParticipation.value;
				if(lossParticipation!=null && lossParticipation!="")
					lossParticipation=lossParticipation.replace(new RegExp(',', 'g'),'');
				else
				 	lossParticipation=0;
				 	
				 	
				//var per = document.retroManualAdj.account_Period.value;
				var slide = 0;

				//if("8"==per) {
                     slide = document.retroManualAdj.slideScaleCom.value;
                    if (slide != null && slide != "") {
                        slide = slide.replace(new RegExp(',', 'g'), '');
                    }else{
                        slide=0;
					}

                //}
				var A=parseFloat(PremiumQshare)+parseFloat(PremiumportifolioIn)+parseFloat(CliamPortfolioin)+parseFloat(Premium_Reserve_Released)+parseFloat(LossReserveReleased)+parseFloat(Interest)+parseFloat(CashLoss_Credit)+ parseFloat(taxDedectSource)+ parseFloat(serviceTax) + parseFloat(lossParticipation);
				var B=parseFloat(CommissionQshare)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(overrider)+parseFloat(OtherCost)+parseFloat(Xl_Cost)+parseFloat(Premiumportifolioout)+parseFloat(Cliam_portfolio_out)+parseFloat(PremiumReserve_QuotaShare)+parseFloat(Loss_ReserveRetained)+parseFloat(Profit_Commission)+parseFloat(Cash_LossPaid)+parseFloat(Claims_paid) + parseFloat(wHTax)+ parseFloat(slide);
				document.retroManualAdj.totalCredit.value=Comma(A.toFixed(2));
				document.retroManualAdj.totalDebit.value=Comma(B.toFixed(2));
				var NetDue = A-B;
//                NetDue = (parseFloat(PremiumQshare) + parseFloat(PremiumSurplus) + parseFloat(PremiumportifolioIn) + parseFloat(CliamPortfolioin) + parseFloat(Premium_Reserve_Released) + parseFloat(LossReserveReleased) + parseFloat(Interest) + parseFloat(CashLoss_Credit)) - (parseFloat(CommissionQshare) + parseFloat(CommissionSurplus) + parseFloat(Brokerage) + parseFloat(Tax) + parseFloat(overrider) + parseFloat(OtherCost) + parseFloat(Xl_Cost) + parseFloat(Premiumportifolioout) + parseFloat(Cliam_portfolio_out) + parseFloat(PremiumReserve_QuotaShare) + parseFloat(Loss_ReserveRetained) + parseFloat(Profit_Commission) + parseFloat(Cash_LossPaid) + parseFloat(Claims_paid));
				document.retroManualAdj.netDue.value=Comma(NetDue.toFixed(2));
		}
		function chkPremiumQS(){
		if(document.retroManualAdj.retroManualAdjQuotaShare.value==null || document.retroManualAdj.premiumQuotaShare.value==""){
			document.retroManualAdj.commissionQuotaShare.value="";
		}
		}
		
		
	function toggleCashLossCredit(val)
	{
		if(val!=null && val!="")
			val=val.replace(new RegExp(',', 'g'),'');
		else
		 	val=0;
		if(val>0){
//			document.getElementById('CashLossCreditOC').style.display='block';
            $("#CashLossCreditOC").show();
		}else
		{
//			document.getElementById('CashLossCreditOC').style.display='none';
            $("#CashLossCreditOC").hide();
		}
	
	}
	$(document).ready(function() {     
    $('#subProfitId').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
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
      Num = Num.trim().replace(/,/g, '');
      if(Num.length>20)
      {
     	 alert('Exceeding Your Limit');
     	 Num=Num.substring(0,20);
      }else if(Num.length==0){
          Num = '0';
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

    function getLableValue(){
    	//var accTypeval =document.retroManualAdj.account_Period.value ;
    	//var accYear = document.retroManualAdj.account_Period_year.value;
    	var mode = document.retroManualAdj.mode.value;
    	if(accTypeval!=''&&accYear!=''&&accTypeval != '5' && accTypeval != '6' && accYear!=0 ){
	    	if(accTypeval == '1'){
	    		document.getElementById('accountPrd').textContent ="31/03/"+accYear;
	    	}
	    	else if(accTypeval == '2'){
				document.getElementById('accountPrd').textContent ="30/06/"+accYear;
			}
			else if(accTypeval == '3'){
				document.getElementById('accountPrd').textContent ="30/09/"+accYear;
			}
			else if(accTypeval == '4'){
				document.getElementById('accountPrd').textContent ="31/12/"+accYear;
			}
		}
		else if(accTypeval == '5'){
			document.getElementById('accountPrd').textContent =document.retroManualAdj.transaction.value;
			if("edit"!=mode){
  			document.retroManualAdj.osClaimsLossUpdateOC.value = document.retroManualAdj.cliamPortfolioin.value ;
  			}
  			}
		else if(accTypeval == '6'){
			document.getElementById('accountPrd').textContent =document.retroManualAdj.statementDate.value;
		}
		else{
		document.getElementById('accountPrd').textContent ='';
		}
    }
    
   


function funStatisticMode(proposalNo,ContractNo,departmentId,layerNo){
      document.getElementById("proposal_No").value=proposalNo;
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
			mtbChildWin[mtbWinCound] = strOpen;
			mtbWinCound++;
			strOpen.focus();
			return false;
      }
      function ViewMode(contractno,transactionno,layerNo,dept){
     
      URL="premiumViewProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&departmentId="+dept+"&mode=popup";
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
      function getAccDate(){
      var period =document.getElementById("account_Period").value;
      var year = document.getElementById("account_Period_year").value;
      var inception = document.getElementById("insDate").value;
      var date1=new Date("DD/MM/YYYY");
      var date ="";
      if(period!=''&&year!='0'){
      if("5"==(period)){
					date=document.retroManualAdj.transaction.value;
				}
				else if("6"==(period) || "7"==(period) ||"8"==(period) ||"9"==(period) ){
					date=document.retroManualAdj.statementDate.value;
				}
				else if("51"==(period) || "52"==(period) ||"53"==(period) || "54"==(period) ||"55"==(period) || "56"==(period) ||  "57"==(period)){
					var dt = new Date(reformatDate(inception));
					
					 var year1 =0;
					//c.setTime(df.parse(inception));
					year1=dt.getFullYear() ;
					var num = parseInt(year) - year1;
					if("51"==(period)){
							 months=(3*1) + (12*num);
						}  
					if("52"==(period)){
						 months=(3*2) + (12*num);
					}
					if( "53"==(period)){
						 months=(3*3) + (12*num);
					}	
					if("54"==(period)){
							 months=(3*4) + (12*num);
						}  
					if("55"==(period)){
						 months=(6*1) + (12*num);
					}
					if( "56"==(period)){
						 months=(6*2) + (12*num);
					}
					if( "57"==(period)){
						 months=(12*1) + (12*num);
					}
				        dt.setMonth(dt.getMonth() + months);
				        dt.setDate(dt.getDate()-1);
						var day = dt.getDate();
						if(parseInt(day)<10) {
							day="0"+day;
						}
						var month= dt.getMonth()+1;
						if(parseInt(month)<10) {
							month="0"+month;
						}
						var year = dt.getFullYear();
				       date = day + "/" + month + "/" + year;
				}
				document.getElementById("accountPeriodDate").value= date;
      }
      }
function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
    return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}
var premiumReserve_QuotaShare = document.retroManualAdj.premiumReserve_QuotaShare.value;
if(parseFloat(premiumReserve_QuotaShare)==0){
//getpremiumDeposit('N');
}
function getpremiumDeposit(status){
var calc = document.getElementById("CalculateTax").value ;
		var transactionNo = document.getElementById("transactionNo").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && transactionNo==""){
	var resStatus = document.retroManualAdj.premiumReserveUser.value;
	if('Y'!=resStatus && 'Y'!=status){
	var pemiumReserve = document.retroManualAdj.premiumReserve.value;
	var PremiumQshare=document.retroManualAdj.premiumQuotaShare.value;
	if(PremiumQshare!=null && PremiumQshare!="")
		PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
	else 
		PremiumQshare=0;
	
	 	var val = parseFloat(PremiumQshare);
	 	if(parseFloat(val)>0){
	 	var val1 = (val* parseFloat(pemiumReserve)/100 );
	 	var result = Comma(val1.toFixed(2));
	 	document.retroManualAdj.premiumReserve_QuotaShare.value =result;
	 	}
	 	}
	 	if('Y'==status){
	 	document.retroManualAdj.premiumReserveUser.value = status;
	 	document.getElementById("CalculateTax").value = "Completed";
	 	}
	 	}
	 	
	 	</s:if>
}
//getAjax('<s:property value="predepartment"/>','presubclass','<s:property value="subProfitId"/>')
function getAjax(value,id,subProfitId) {
        $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueRetroAdj.action?predepartment='+value+'&dropDown=presubclass&subProfitId='+subProfitId,
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#subProfitId').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
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
        }
        });
    }
    
    
function removeElementsWithValue(arr, val) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === val) {
            arr.splice(i, 1);
        }
    }
    return arr;
}
    getContractList('<s:property value="uwYear"/>','contractList','<s:property value="contNo"/>');
    function getContractList(uwYear,dropDown,contNo){
    		var URL='<%=request.getContextPath()%>/ajaxValueRetroAdj.action?uwYear='+uwYear+'&dropDown='+dropDown+'&contNo='+contNo;
  	 		postRequest(URL,dropDown);	
    }
     getRetroDet('<s:property value="contNo"/>','retroDetails');
    function getRetroDet(retroContract,dropDown){
    var month=document.retroManualAdj.transaction.value;	
    		var URL='<%=request.getContextPath()%>/ajaxValueRetroAdj.action?contNo='+retroContract+'&dropDown='+dropDown+'&transaction='+month;
  	 		postRequest(URL,dropDown);
  	 		
    }
    function getViewContractDetails(prodId,proposalNo,amendId,branchCode)
	    {
			URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId+'&branchCode='+branchCode;
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
	 editMode();
	function editMode(){
	var val =document.getElementById("multiuserError").value;
		if('error'==val){
		document.getElementById("multiuserError").value ='';
		alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
		}
	}
</script>		
  </body>
</html>
