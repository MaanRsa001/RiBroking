<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
		$( "#accountPeriodDate" ).datepicker({
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
					<s:form id="" name="premium" theme="simple" action="insertPremiumProportionPremium" method="post" enctype="">					
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
												<s:if test='!"add".equals(mode)'>
													<s:if test='!"7".equals(<s:property value="%{#session.mfrid}" />)'>
													<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
													<s:hidden name="amendId"/>
												</s:if>
												<s:else>
												<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="productId" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
												<s:hidden name="amendId"/>
												</s:else>
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
													<s:text name="label.treatyName" />
												</div>
												<div class="tbox">
													<s:property value="treatyName_type"/>
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
											<%-- <div class="textfield">
												<div class="text txtB" style="display:table;">
													<s:text name="label.profitCenter" />
												</div>
												<div class="tbox">
													<s:property value="profit_Center"/>
												</div>
											</div>	--%>										
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
													<s:text name="label.policyBranch" />
												</div>
												<div class="tbox">
													<s:property value="policyBranch"/>
												</div>
											</div>--%>
											
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
													<s:hidden name="insDate" id="insDate"/>
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
											<table width="100%" class="table table-bordered">
												<tr>
												<s:if test='"1"==treatyType ||"3"==treatyType || "4"==treatyType ||"5"==treatyType'>
													<td width="25%" class="txtB">
														<s:if test='"1"==treatyType ||"3"==treatyType'>
															<s:text name="label.commissionQSPer" />
														</s:if>
														<s:if test='"4"==treatyType ||"5"==treatyType'>
															<s:text name="label.commissionPer" />
														</s:if>
													</td>
													<td width="25%" align="right">
														<s:property value="commission_view"/>
													</td>
													</s:if>
													<td width="25%" class="txtB">
														<s:if test='"2"==treatyType ||"3"==treatyType'>
															<s:text name="label.commissionSurpP" />
														</s:if>
													</td>
													<td width="25%" align="right">
														<s:if test='"2"==treatyType ||"3"==treatyType'>
															<s:property value="commissionSurb_view"/>
														</s:if>
														<s:hidden name="commssion_Surp" value="%{commissionSurb_view}" />
													</td>
													<s:if test='"2"==treatyType'>
													<td width="25%" class="txtB">
													</td><td width="25%" class="txtB">
													</td>
													</s:if>
												</tr>
												<tr>
													<td class="txtB">
														<s:text name="label.overriderP" />
													</td>
													<td align="right">
														<s:property value="overRider_view"/>
														<s:hidden name="overRider_S" value="%{overRider_view}" />
													</td>
													<td class="txtB">
														<s:text name="label.brokerageP" />
													</td>
													<td align="right">
														<s:property value="brokerage_view"/>
														<s:hidden name="brokerage_S" value="%{brokerage_view}" />
													</td>
												</tr>
												<tr>
													<td class="txtB">
														<s:text name="label.taxP" />
													</td>
													<td align="right">
														<s:property value="tax_view"/>
														<s:hidden name="tax_S" value="%{tax_view}" />
													</td>
													<td class="txtB">
														<s:text name="label.otherCostP" />
													</td>
													<td align="right">
														<s:property value="otherCostView"/>
														<s:hidden name="otherCost_S" value="%{otherCostView}" />
													</td>
												</tr>
												<tr>
													<td class="txtB">
														<s:text name="label.premiumReserveP" />
													</td>
													<td align="right">
														<s:property value="premium_Reserve_view"/>
													</td>
													<td class="txtB">
														<s:text name="label.lossReserveP" />
													</td>
													<td align="right">
														<s:property value="loss_reserve_view"/>
													</td>
												</tr>
												<%-- <tr>
													<td class="txtB">
														<s:text name="label.xlCostOurShareOC" />
													</td>
													<td align="right">
														<s:property value="xl_cost_view"/>
													</td>
													<td class="txtB">
														<s:text name="label.premiumQS" />
													</td>
													<td align="right">
														<s:property value="premiumQuota_view"/>
													</td>
												</tr>--%>
												<tr>
													<%-- <td class="txtB">
														<s:text name="label.premiumSurplus" />
													</td>
													<td align="right">
														<s:property value="premiumsurp_view"/>
													</td>--%>
													<td class="txtB">
														<s:text name="label.epi.ourassessment.ourshare" /> - <s:property value="currencyName"/>
													</td>
													<td align="right">
														<s:property value="epioc"/>
													</td>
													<td class="txtB">
														<s:if test='"2".equals(businessType)'>
															<s:text name="label.ourassessment.orginal" /> 
														</s:if>
													</td>
													<td align="right">
														<s:if test='"2".equals(businessType)'>
															<s:property value="ourAssessmentOfOrginal"/>
														</s:if>
														<s:hidden name="settlement_status" id="settlement_status"/>
													</td>
												</tr>
											</table>
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
													<s:radio list="#{'1':'100%'}" name="enteringMode" />													
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.signed" />
												</div>
												<div class="tbox">
													<s:radio name="enteringMode" list="#{'2':''}" value="enteringMode==null?2:enteringMode"/>
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
													<s:textfield name="amendmentDate" id="amendmentDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate();validatedate(this.id,this.value);" />												
												</div>
											</div>
											<div class="textfield">
											</div>
											</s:if>
											
											<div class="textfield">
												<div class="text">
													<s:text name="Section" />
												</div>
												<div class="tbox">
													<s:select name="sectionType" id="sectionType" list="#{'1':'Create New','2':'Choose from Existing'}"  headerKey="" cssClass="inputBoxS" headerValue="---Select---" onchange="GetSectionType()" disabled="%{disableStatus}"/>												
												</div>
											</div>
																					
											<div class="textfield">
												<div class="text">
													<s:text name="Section Name" />
												</div>
												<div class="tbox" id="section">
													<s:textfield cssClass="inputBox" name="sectionName" cssStyle="width: 85%; float:left;" />
													<span class="cr" onclick="GetSection();" style="cursor: pointer" title="Click here"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
												</div>
											</div>
											<%-- <s:if test="'RI02'.equals(#session.SOURCE_CODE)">
											</s:if>
											<s:else>
											<s:hidden name="sectionType" id="sectionType" value="2"/>
											<s:hidden name="sectionName" id="sectionName"/>
											</s:else> --%>
											<div class="textfield">
												<div class="text">
													<s:text name="label.Class" />
												</div>
												<div class="tbox">
													<%--<s:textfield name="departmentName" cssClass="inputBox" readonly="true"/>--%>
													<s:select name="predepartment" id="predepartment" list="predepartmentList" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  onchange="getAjax(this.value,'presubclass');"/>
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
												<s:if test='"edit".equals(mode)'>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="validatedate(this.id,this.value);GetExchangeRate();GetStatementRecDate();getoutStanding();getAccDate();" disabled="true" />												
												</div>
												</s:if>
												<s:else>
												<div class="tbox">
													<s:textfield name="transaction" id="transaction"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="validatedate(this.id,this.value);GetExchangeRate();GetStatementRecDate();getoutStanding();getAccDate();" disabled="%{disableStatus}"/>													
												</div>
												</s:else>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementReceivedDate" />
												</div>
												<div class="tbox">
													<s:textfield name="inception_Date" id="inception_Date"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="validatedate(this.id,this.value);GetStatementDate();getAccDate();"/>																						
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.statementDate" />
												</div>
												<s:if test='"edit".equals(mode)'>
												<div class="tbox">
													<s:textfield name="statementDate" id="statementDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this);getoutStanding();getAccDate();" onchange="validatedate(this.id,this.value);"  disabled="true" />												
												</div>
												</s:if>
												<s:else>
												<div class="tbox">
													<s:textfield name="statementDate" id="statementDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this);getoutStanding();getAccDate();" onchange="validatedate(this.id,this.value);" disabled="%{disableStatus}"/>													
												</div>
												</s:else>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountPeriod" />
												</div>
												<div class="tbox">													
													<s:select list="premiumaccperiod" listKey="PremiumType" listValue="DetailName" headerKey="0" headerValue="---Select---" name="account_Period" id="account_Period" onchange="GetOSBDisplay();GetOSBValue();getoutStanding();getAccDate();" cssClass="inputBoxS" cssStyle="width: 45%; float:left;" />
													<s:set name="insDate" id="date" />
													<s:select list="yearList" headerKey="0" headerValue="YEAR" name="account_Period_year" id="account_Period_year"  cssClass="inputBoxS" cssStyle="width: 45%; float:left;" onchange="getoutStanding();getAccDate();"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.documentType" />
												</div>
												<div class="tbox">	
													<s:select list="documentTypeList" listKey="TYPE" listValue="DETAIL_NAME" headerKey="" headerValue="---Select---" name="documentType" id="documentType" cssClass="inputBoxS" onchange="getTransactionData();getTrasView(this.value);" />
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
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountPeriodDate" />
												</div>
												<div class="tbox">													
														<s:textfield cssClass="inputBox" name="accountPeriodDate" id="accountPeriodDate"  maxlength="50" readonly="flase" onchange="validatedate(this.id,this.value);"/>												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedantBrokerDocReference" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="cedentRef" maxlength="50" />												
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
													<table width="100%" class="table table-bordered">
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
																	<s:text name="label.premium" />
																</td>
																<td>
																	<s:textfield name="premiumQuotaShare" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);Calculation(this.value);proposalNetDue();getpremiumDeposit('N');" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;"  onclick="this.select();getpremiumDeposit()"  maxlength="30" value="%{premiumQuotaShare==null?'0.00':premiumQuotaShare}" onchange="getVATInfo(this.value,'vattaxid');" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<s:hidden name="premiumSurplus" id="premiumSurplus" value="0.00"/>
																	<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" class="pullRight">
													 			     	<span class="glyphicon glyphicon-list"></span>
													 			    </button>
																</td>
																<td>
																	<s:text name="label.commission" />
																</td>
																<td>
																	<s:textfield name="commissionQuotaShare" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();chkPremiumQS();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;"  onclick="this.select();" maxlength="30" value="%{commissionQuotaShare==null?'0.00':commissionQuotaShare}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<s:hidden name="commissionSurplus" id="commissionSurplus" value="0.00"/>
																</td>
															</tr>
															<tr>
																<td >
																	<s:text name="label.premiumPortfolioIn" />
																</td>
																<td>
																	<s:textfield name="premiumportifolioIn" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{premiumportifolioIn==null?'0.00':premiumportifolioIn}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
																<td >
																	<s:text name="label.slideScaleComm" />
																</td>
																<td  >
																	<s:textfield name="slideScaleCom" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);checkDecimals15(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{slideScaleCom==null?'0.00':slideScaleCom}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<%-- <input type="button"  size="2"  value="..." onclick="SlideCommission('slide')" class="pullRight"/>--%>
																</td>
	
															</tr>
															<tr>
																<td>
																	<s:text name="label.claimPortfolioIn" />
																</td>
																<td>
																	<s:textfield name="cliamPortfolioin" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();GetOSBValue();" onblur="this.value=Comma(this.value);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{cliamPortfolioin==null?'0.00':cliamPortfolioin}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
																<td>
																	<s:text name="label.brokerage" />
																</td>
																<td>
																	<s:textfield name="brokerage" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{brokerage==null?'0.00':brokerage}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
	
															</tr>
															<tr>
																<td>
																	<s:text name="label.premiumReserveReleased" />
																</td>
																<td>
																	<s:textfield name="premium_Reserve_Released" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{premium_Reserve_Released==null?'0.00':premium_Reserve_Released}" readonly="true" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<s:if test='"edit".equals(mode)'>
																		<input type="button"  size="2"  value="..." onclick="premuimReserved('PRR');" class="pullRight" disabled="disabled"/>
																	</s:if>
																	<s:else>
																		<input type="button"  size="2"  value="..." onclick="premuimReserved('PRR');" class="pullRight"/>
																	</s:else>
																</td>
																<td>
																	<s:text name="label.taxExpense" />
																</td>
																<td>
																	<s:textfield name="tax" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{tax==null?'0.00':tax}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td>
																	<s:text name="label.lossReserveReleased" />
																</td>
																<td>
																	<s:textfield name="lossReserveReleased" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{lossReserveReleased==null?'0.00':lossReserveReleased}"  readonly="true" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<s:if test='"edit".equals(mode)'>
																		<input type="button"  size="2"  value="..." onclick="premuimReserved('LRR');" class="pullRight" disabled="disabled"/>
																	</s:if>
																	<s:else>
																		<input type="button"  size="2"  value="..." onclick="premuimReserved('LRR');" class="pullRight"/>
																	</s:else>
																</td>
																<td>
																	<s:text name="label.WithHoldingTax" />
																</td>
																<td>
																	<s:textfield name="withHoldingTaxOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{withHoldingTaxOC==null?'0.00':withHoldingTaxOC}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td>
																	<s:text name="label.interest" />
																</td>
																<td>
																	<s:textfield name="interest" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);"  cssStyle="text-align: right;" onclick="this.select();" maxlength="30" value="%{interest==null?'0.00':interest}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
																<td>
																	<s:text name="label.iwOverrider" />
																</td>
																<td>
																	<s:textfield name="overrider" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{overrider==null?'0.00':overrider}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td>
																	<s:text name="label.cashLossCredit" />
																</td>
																<td >
																	<s:textfield name="cashLoss_Credit" cssClass="inputBox"  onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);"  onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{cashLoss_Credit==null?'0.00':cashLoss_Credit}" readonly='"RI02".equals(#session.SOURCE_CODE)?true:flase' disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<s:if test='"RI02".equals(#session.SOURCE_CODE)'> 
																			<s:if test='"edit".equals(mode)'>
																				<input type="button"  size="2"  value="..." onclick="CashLossCredit()" class="pullRight" disabled="disabled"/>
																			</s:if>
																			<s:else>
																				<input type="button"  size="2"  value="..." onclick="CashLossCredit()" class="pullRight"/>
																			</s:else>
																	 </s:if> 
																</td>
																<td>
																	<s:text name="label.otherCost" />
																</td>
																<td>
																	<s:textfield name="otherCost" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{otherCost==null?'0.00':otherCost}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
															<tr>
																<td><s:text name="label.taxdedect" /> </td>
																<td>
																	<s:textfield cssClass="inputBox" name="taxDedectSource" id="taxDedectSource" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);Caculate(this.value);proposalNetDue();" onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{taxDedectSource==null?'0.00':taxDedectSource}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
																<%-- <td>
																	<s:text name="label.xlCost" />
																</td>
																<td>
																	<s:textfield name="xl_Cost" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);proposalNetDue();this.value=Comma(this.value);"  onclick="this.select();" onkeyup="allow2DigitDecValues(this);" maxlength="30" value="%{xl_Cost==null?'0.00':xl_Cost}"/>
																</td> --%>
															</tr>
															</s:if>
															<s:else>
																<s:hidden name="taxDedectSource" id="taxDedectSource" value="0"/>
																<s:hidden name="xl_Cost" id="xl_Cost" value="0"/>
															</s:else>
															<tr>
																<td><s:text name="label.lossPor" /> </td>
																<td>
																	<s:textfield cssClass="inputBox" name="lossParticipation" id="lossParticipation" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{lossParticipation==null?'0.00':lossParticipation}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<%--<span id="lossPat"><input type="button"  size="2"  value="..." onclick="SlideCommission('loss')" class="pullRight"/></span>		--%>												
																</td>
																<td>
																	<s:text name="label.premiumPortfolioOut" />
																</td>
																<td>
																	<s:textfield name="premiumportifolioout" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{premiumportifolioout==null?'0.00':premiumportifolioout}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.claimPortfolioOut" />
																</td>
																<td>
																	<s:textfield name="cliam_portfolio_out" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{cliam_portfolio_out==null?'0.00':cliam_portfolio_out}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.premiumReserveRetained" />
																</td>
																<td>
																	<s:textfield name="premiumReserve_QuotaShare" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();getpremiumDeposit('Y');"  onclick="this.select();" onblur="this.value=Comma(this.value);negative(this.id,this.value);" maxlength="30" value="%{premiumReserve_QuotaShare==null?'0.00':premiumReserve_QuotaShare}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.lossReserveRetained" />
																</td>
																<td>
																	<s:textfield name="loss_ReserveRetained" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);negative(this.id,this.value);" maxlength="30" value="%{loss_ReserveRetained==null?'0.00':loss_ReserveRetained}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.profitCommission" />
																</td>
																<td>
																	<s:textfield name="profit_Commission" id="profit_Commission" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{profit_Commission==null?'0.00':profit_Commission}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																	<%--<span id="ProfitComm"><input type="button"  size="2"  value="..." onclick="SlideCommission('profit')" class="pullRight"/></span>--%>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.cashLossPaid" />
																</td>
																<td>
																	<s:textfield name="cash_LossPaid" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{cash_LossPaid==null?'0.00':cash_LossPaid}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.claimsPaid" />
																</td>
																<td>
																	<s:textfield name="claims_paid" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();"  onclick="this.select();" onblur="this.value=Comma(this.value);" maxlength="30" value="%{claims_paid==null?'0.00':claims_paid}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr id="vattaxid">
																<td><s:text name="label.vat" /> </td>
																<td>
																	<s:textfield cssClass="inputBox" name="vatPremium" id="vatPremium" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();setCalculateVal();"  onblur="this.value=Comma(this.value);" cssStyle="text-align: right;" onclick="this.select();" value="%{vatPremium==null?'0.00':vatPremium}" maxlength="26" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>														
																</td>
																<td>
																	<s:text name="label.brokerageVat" />
																</td>
																<td>
																	<s:textfield name="brokerageVat" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);proposalNetDue();" onblur="this.value=Comma(this.value);" onclick="this.select();"   cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{brokerageVat==null?'0.00':brokerageVat}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>	
															</tr>
															<tr>
																<td>
																	<s:text name="label.total" />
																</td>
																<td>
																	<s:textfield name="totalCredit" cssClass="inputBox" readonly="true"   onclick="this.select();" cssStyle="text-align: right;" maxlength="30" value="%{totalCredit==null?'0.00':totalCredit}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
																<td>
																	<s:text name="label.total" />
																</td>
																<td>
																	<s:textfield name="totalDebit" cssClass="inputBox" cssStyle="text-align: right;" readonly="true"  onclick="this.select();" maxlength="30" value="%{totalDebit==null?'0.00':totalDebit}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
																</td>
															</tr>
															<tr>
																<td colspan="2">&nbsp;</td>
																<td>
																	<s:text name="label.netDue" />
																</td>
																<td>
																	<s:textfield name="netDue" cssClass="inputBox" cssStyle="text-align: right;"  readonly="true"  onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);" onclick="allow2DigitDecValues(this);this.select();" value="%{netDue==null?'0.00':netDue}" disabled="(transDropDownVal==null||transDropDownVal=='')?false:true"/>
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
																<td colspan="4">
																	<table class="table table-bordered" >
																	<tr >
																	<td>
																	<s:text name="Do you want to record Outstanding Loss Update?" />
																	
																	</td>
																	<td>
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="osbYN" id="osbYN" value="%{osbYN==null?'Yes':osbYN}" onchange="getoutStanding();"/>
																	
																	</td>
																	</tr>
																		<tr>
																			<td>
																				<s:text name="label.outStandingClaimsLossUpdate" /><span id="accountPrd" style="display:none"> </span>
																			</td>
																			<td>
																				<s:textfield name="osClaimsLossUpdateOC" cssClass="inputBox" cssStyle="text-align: right;" onblur="allow2DigitDecValues(this);middleMinusRestriction(this);this.value=Comma(this.value);" maxlength="30" value="%{osClaimsLossUpdateOC==null?'0.00':osClaimsLossUpdateOC}" />
																			</td>
																		</tr>
																	</table>
																</td>
																<td colspan="3">
																<table width="100%" class="table table-bordered">
																		<tbody>
																		<s:if test="osClaimLoss.size()>0">
																			<tr>
																			<td>
																				<s:text name="Previous Outstanding Loss position " />
																			</td>
																			<td>
																			<a title="View" style="cursor: pointer;" onclick="ViewOSB()">View</a>
																			</td>
																				
																			</tr>
																		</s:if>
																		</tbody>
																	</table>
																	<%-- <table width="100%" class="table table-bordered">
																		<tbody>
																		<s:iterator id="OSClaim" value="osClaimLoss">
																			<tr>
																				<td width="33.33%">
																					<s:property value="Key" /> <!-- name="OSClaim" property="key" -->
																				</td>
																				<td width="33.33%">
																					<s:property value="value" /> <!-- name="OSClaim" property="value" format="###,###,###,###.00" -->
																				</td>
																				<td width="33.33%">
																				<a title="View" style="cursor: pointer;" onclick="ViewOSB()">View</a>
																				</td>
																			</tr>
																		</s:iterator>
																		</tbody>
																	</table>--%>
																</td>
															</tr>
														</tbody>
													</table>
													
											<br class="clear"/>
											<div id="obsList"></div>
											
											
										</div>
									</div>
								</div>
							</div>							
						</div>
						<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">	
								<div class="panel-heading">	
										<s:label key="label.cashlossdetails" />
								</div>	
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
												<a title="View" style="cursor: pointer;" onclick="ViewModeClaim('${record.policy_Contract_No}','${record.claim_No }')">View</a>
											</display:column>
											</s:if>
											</display:table>										
									</div>
								</div>
							</div>
						</div>
					</s:if>
						<s:hidden name="receipt_no" />
													
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<s:if test='premiumDisplay.equals("premiumDisplay")'>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="cancel1()" /> &nbsp;&nbsp;&nbsp;
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="insertupdate1('Submit')" />&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
								
							</s:if>
							<s:else>
							
								<s:if test="size == 'list'">
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='back("<s:property value="premiumMasterMode"/>")' />&nbsp;&nbsp;&nbsp;	
								</s:if>		
								<s:if test='premiumDisplay.equals("authenticationDisplay")'>
									<input type="button" value="Back" class="btn btn-sm btn-danger" onclick='AuthendicationBack()' /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='#session.MenuRights.indexOf("PST")!=-1 || #session.MenuRights.indexOf("AST")!=-1'>								
									
									<input type="button" value="Next" class="btn btn-sm btn-success" onClick="disableForm(this.form,false,'');insertupdate1('Submit');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test='(#session.MenuRights.indexOf("PSV")!=-1 || #session.MenuRights.indexOf("ASE")!=-1)&& "Main"!=tableType'>								
									<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');insertupdate1('Save');"/>
									&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:if test="size != 'list'">					
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="cancel()" />	
								</s:if>									
							</s:else>
							</div>
						</div>
						<s:hidden name="transactionNo" id="transactionNo" value="%{transactionNo}"/>
						<s:hidden name="commssion_S" value="%{commission_view}" />						
						<s:hidden name="proposalno" value="%{proposal_no}" />
						<s:hidden name="proposal_No" id="proposal_No"/>
						<s:hidden name="baseLayer" value="%{baseLayer}" />
						<s:hidden name="layerProposalNo" value="%{layerProposalNo}" />
						<s:hidden name="contNo" id="contNo"/>						
						<s:hidden name="mode" id="mode" />
						<s:hidden name="baseCurrencyId" id="baseCurrencyId" />
						<s:hidden name="baseCurrencyName" />
						<s:hidden name="currencyId" />
						<s:hidden name="commission_view" />
						<s:hidden name="tax_view" />
						<s:hidden name="overRider_view" />
						<s:hidden name="brokerage_view" />
						<s:hidden name="shareSigned" />
						<s:hidden name="layerno" />
						<s:hidden name="amendId" />
						<s:hidden name="commission" value="0" />
						<s:hidden name="layerNo" value="%{layerno}" id="layerNo"/>
						<s:hidden name="menuId" id="menuId"/>
						<s:hidden name="previousPremium" id="previousPremium"/>
						<s:hidden name="contractPremium" id="contractPremium"/>	
						<s:hidden name="premiumDisplay"/>
						<s:hidden name="departmentId" id="departmentId"/>
						<s:hidden name="slideScenario" id="slideScenario"/>
						<s:hidden name="claimPaymentNo" id="claimPaymentNo"/>
						<s:hidden name="creditAmountCLC" id="creditAmountCLC"/>
						<s:hidden name="creditAmountCLD" id="creditAmountCLD"/>
						<s:hidden name="CLCsettlementRate" id="CLCsettlementRate"/>
						<s:hidden name="cLDAmount" id="cLDAmount"/>
						
						<s:hidden name="PRTransNo" id="PRTransNo"/>
						<s:hidden name="PRAmount" id="PRAmount"/>
						<s:hidden name="PREAmount" id="PREAmount"/>
						<s:hidden name="PRERate" id="PRERate"/>
						<s:hidden name="LRTransNo" id="LRTransNo"/>
						<s:hidden name="LRAmount" id="LRAmount"/>
						<s:hidden name="LREAmount" id="LREAmount"/>
						<s:hidden name="LRERate" id="LRERate"/>
						<s:hidden name="disableStatus" id="disableStatus"/>
						
						
						<s:hidden name="conPortfolioPremium" id="conPortfolioPremium"/>
						<s:hidden name="conAccCostBrokerage" id="conAccCostBrokerage"/>
						<s:hidden name="conProClaimPaidOC" id="conProClaimPaidOC"/>
						<s:hidden name="conProClaimOutStandingOC" id="conProClaimOutStandingOC"/>
						<s:hidden name="conManagExp" id="conManagExp"/>
						<s:hidden name="conProfitLoss" id="conProfitLoss"/>
						<s:hidden name="conOtherAdj" id="conOtherAdj"/>
						<s:hidden name="conTreatyAdj" id="conTreatyAdj"/>
						<s:hidden name="conProfitRatio" id="conProfitRatio"/>
						<s:hidden name="conLossRatio" id="conLossRatio"/>
						<s:hidden name="conProfitCommission" id="conProfitCommission"/>
						<s:hidden name="conSuperProfitComm" id="conSuperProfitComm"/>
						<s:hidden name="conPayedTillDate" id="conPayedTillDate"/>
						<s:hidden name="conProfitCommAdj" id="conProfitCommAdj"/>
						<s:hidden name="conPremiumOC" id="conPremiumOC"/>
						<s:hidden name="conClaimPaidOC" id="conClaimPaidOC"/>
						<s:hidden name="conClaimOutStandingOC" id="conClaimOutStandingOC"/>
						<s:hidden name="conClaimRatioOC" id="conClaimRatioOC"/>
						<s:hidden name="conSlideScaleCommOC" id="conSlideScaleCommOC"/>
						<s:hidden name="conCommPaidOC" id="conCommPaidOC"/>
						<s:hidden name="conSlideScaleAdjOC" id="conSlideScaleAdjOC"/>
						
						<s:hidden name="conlossPremiumOC" id="conlossPremiumOC"/>
						<s:hidden name="conlossClaimPaidOC" id="conlossClaimPaidOC"/>
						<s:hidden name="conlossClaimOutStandingOC" id="conlossClaimOutStandingOC"/>
						<s:hidden name="conlossClaimRatioOC" id="conlossClaimRatioOC"/>
						<s:hidden name="conClaimPaidRatioOC" id="conClaimPaidRatioOC"/>
						
						<s:hidden name="conlossOC" id="conlossOC"/>
						<s:hidden name="conlossTillOC" id="conlossTillOC"/>
						<s:hidden name="conlossAdjOC" id="conlossAdjOC"/>
						<s:hidden name="adjToOutLoss" id="adjToOutLoss"/>
						<s:hidden name="size" id="size"/>
						<s:hidden name="CalculateTax" id="CalculateTax"/>
					<%--	<s:hidden name="predepartment" id="predepartment"/>	 --%>
						<s:hidden name="premiumReserve" id="premiumReserve"/>	
						<s:hidden name="premiumReserveUser" id="premiumReserveUser"/>	
						<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>	
						<s:hidden name="preVal" id="preVal"/>
						<s:hidden name="multiuserError" id="multiuserError"/>
						<s:hidden name="cleanCutCount" id="cleanCutCount"/>
						<s:hidden name="cashlossCount" id="cashlossCount"/>
						<s:hidden name="tableType" id="tableType"/>	
						<s:hidden name="requestNo" id="requestNo"/>
						<s:hidden name="buttonStatus" id="buttonStatus"/>
						<s:hidden name="acceptenceDate" id="acceptenceDate"/>
						<s:hidden name="cashlosstranId" id="cashlosstranId"/>
						<s:hidden name="cashlossType" id="cashlossType"/>
						<s:hidden name="sectionNo" id="sectionNo"/>
					</div>
						<div id="countStatus">
						</div>	
						<div id="cashcountStatus">
						</div>
						<div id="companyModal" class="modal fade" role="dialog">
						  <div class="modal-dialog modal-lg">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						      </div>
						      <div class="modal-body" >
						        <div class="container-fluid">
						        	<div class="tablerow">
						        	<div class="boxcontent">
										<div class="textfield25">
											<div class="text">
												<s:text name="label.mipremium1" />
											</div>
											<div class="tbox">
												<s:textfield name="mipremium1" id="mipremium1" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="10" onkeyup="negative(this.id,this.value);allow2DigitDecValues(this);middleMinusRestriction(this);getMiPremium();"  onclick="this.select();" />													
											</div>
										</div>
										<div class="textfield25">
											<div class="text">
												<s:text name="label.mipremium2" />
											</div>
											<div class="tbox">
												<s:textfield name="mipremium2" id="mipremium2" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="10" onkeyup="negative(this.id,this.value);allow2DigitDecValues(this);middleMinusRestriction(this);getMiPremium();"/>													
											</div>
										</div>
										<div class="textfield25">
											<div class="text">
												<s:text name="label.mipremium3" />
											</div>
											<div class="tbox">
												<s:textfield name="mipremium3" id="mipremium3" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="10" onkeyup="negative(this.id,this.value);allow2DigitDecValues(this);middleMinusRestriction(this);getMiPremium();"/>													
											</div>
										</div>
										<div class="textfield25">
											<div class="text">
												<s:text name="label.mipremium" />
											</div>
											<div class="tbox">
												<s:textfield name="mipremium" id="mipremium" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" maxlength="10"/>													
											</div>
										</div>
									</div>
									</div>
									<br/>
									<br/>
									<br/>
									<div class="boxcontent" align="center">
										<input type="button" id="print" value="Sumbit" class="btn btn-sm btn-success" data-dismiss="modal" onclick="misumit();"/>
										<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
								   </div>
						        </div>
						      </div>
						    </div>
						  </div>
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
        var form = $('form[name="premium"]');
		Commas();
		function cancel1()
		{
		destroyPopUps();
		document.forms[0].action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      	document.forms[0].submit();
		}
		function insertupdate1(status){
		destroyPopUps();
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
		var count = document.getElementById("cleanCutCount").value;
		var acper = document.getElementById("account_Period").value;
		var mode = document.getElementById("mode").value;
      	if(document.getElementById("ri_cessionNo").checked){
	        	answer=confirm("You have chosen to not cede this transaction to retro contracts.  Do you wish to continue?");
		     	if(answer){
			     	if(acper!='0' && acper!='5' && count=='0' && mode!='edit'){
				     	answer = confirm("Portfolio Assumption has not been recorded for this Clean-Cut treaty.  Do you wish to continue with this transaction?");
				     	if(answer){
				     		insertupdate2(status);
				     	}
				     	else{
					     	return false;
					     	}
				     	}
     	}
     	else{
     		return false;
     	}
     	}
    	else {
    		if(acper !='0' && acper!='5' && count=='0'  && mode!='edit'){
		     	answer = confirm("Portfolio Assumption has not been recorded for this Clean-Cut treaty.  Do you wish to continue with this transaction?");
		     	if(answer){
		     		insertupdate2(status);
		     	}
		     	else{
			     	return false;
			     	}
		     	}
	     	else{
	     		insertupdate2(status);
	     	}
    		}
		}
		
		function insertupdate2(buttonStatus){
		var No = document.getElementById("claimPaymentNo").value;
		var CLC = document.getElementById("cLDAmount").value;
		var CLD = document.getElementById("creditAmountCLD").value;
		var lossCount = document.getElementById("cashlossCount").value;
		document.getElementById("buttonStatus").value=buttonStatus;
		var noSize=[];
		var clcVal =[];
		var cldVal=[];
		var status=false;
		if(No !=''){
		 noSize = No.split(",");
		noSize =  noSize.filter(function(e){return e}); 
		}
		if(lossCount !=0 && noSize.length != lossCount){
			answer = confirm("You are recording the technical statement without recording full credit entries for pending cash losses. Do you wish to continue? ");
			if(answer){
				if(CLC!='' && CLD !=''){
					 clcVal = CLC.split(",");
					 cldVal = CLD.split(",");
					 clcVal =  clcVal.filter(function(e){return e}); 
					 cldVal =  cldVal.filter(function(e){return e}); 
						 for (var i=0;i<clcVal.length;i++){
						 var clc1 = clcVal[i].replace(new RegExp(',','g'),'') ;
						 var cld1 =  cldVal[i].replace(new RegExp(',','g'),'');
						 alert(clc1!=cld1);
						 if(clc1!=cld1){
							 status = true;
							 break;
						 }
					}if(status){
						answer = confirm("You are recording the technical statement without recording full credit entries for pending cash losses. Do you wish to continue? ");
						if(answer){
							insertupdate(buttonStatus);
						}else{
							return false;
						}
					}else{
						insertupdate(buttonStatus);
						}
				}
				else{
					insertupdate(buttonStatus);
					}
			}else{
				return false;
			}
			}else{
				if(CLC!='' && CLD !=''){
					 clcVal = CLC.split(",");
					 cldVal = CLD.split(",");
					 clcVal =  clcVal.filter(function(e){return e}); 
					 cldVal =  cldVal.filter(function(e){return e}); 
						 for (var i=0;i<clcVal.length;i++){
						 var clc1 = clcVal[i].replace(new RegExp(',','g'),'') ;
						 var cld1 =  cldVal[i].replace(new RegExp(',','g'),'');
						 if(clc1!=cld1){
							 status = true;
							 break;
						 }
					}if(status){
						answer = confirm("You are recording the technical statement without recording full credit entries for pending cash losses. Do you wish to continue? ");
						if(answer){
							insertupdate(buttonStatus);
						}else{
							return false;
						}
					}else{
						insertupdate(buttonStatus);
						}
				}else{
					insertupdate(buttonStatus);
					}
			}
		}
		
		function insertupdate(status)
		{
		twoDecimal();
		document.getElementById("ri_cessionNo").disabled=false;
		document.getElementById("ri_cessionYes").disabled=false;
		var premiumA=document.getElementById("previousPremium").value;
		var contractprmium=document.getElementById("contractPremium").value;
		 var PQS=document.premium.premiumQuotaShare.value;
		  var PS=document.premium.premiumSurplus.value;
		  var exchange=document.premium.exchRate.value;
		  PQS=PQS.replace(new RegExp(',','g'),'');
		  PS=PS.replace(new RegExp(',','g'),'');
		  if(PS==null || PS==''){
		  PS=0;
		  }
		var premiumB=parseFloat(PQS)+parseFloat(PS);
		var premiumC=parseFloat(premiumA)+parseFloat(premiumB);
		var premiumD=parseFloat(premiumC)/exchange;
		var premiumE=parseFloat(contractprmium)/exchange;
		var accperiod = document.getElementById("accountPeriodDate").value;
		if(accperiod==''){
		alert("Pleasr Select Accounting Period");
		document.getElementById("ri_cessionNo").disabled=true;
		document.getElementById("ri_cessionYes").disabled=true;
		}else{
		if(premiumD>premiumE){
		var test= confirm("Premium is greater that EPI as per contract.  Do you wish to continue?");
	
		if(test){
		enableForm(document.forms['premium'],false,'transaction,statementDate,currency,sectionType,enteringMode');
		document.premium.action='${pageContext.request.contextPath}/validationAlertProportionPremium.do?productId=<s:property value="productId"/>';
		document.premium.submit();
		}
		}else{
		enableForm(document.forms['premium'],false,'transaction,statementDate,currency,sectionType,enteringMode');
		document.premium.action='${pageContext.request.contextPath}/validationAlertProportionPremium.do?productId=<s:property value="productId"/>';
		document.premium.submit();
		}
	 	}
		}	
		 var val = '<s:property value="alertShow"/>';
		 if("Y"==val){
		 premiumSubmit(status);
		 }	
		 
		 function premiumSubmit(status){
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
		 	premiumInsert(status);
			}
		 }	
		 
		 function premiumInsert(status){
			document.getElementById("ri_cessionNo").disabled=false;
			document.getElementById("ri_cessionYes").disabled=false;
		 	enableForm(document.forms['premium'],false,'transaction,statementDate,currency,sectionType,enteringMode');
		 	document.premium.action='${pageContext.request.contextPath}/insertPremiumProportionPremium.do?productId=<s:property value="productId"/>';
			document.premium.submit();
		 }
		 
		function EditBack()
		{
		document.premium.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init";
		document.premium.submit();
		}
		function ViewMode1(policy_Contract_No,claim_No) { 
		var proNo = document.getElementById("proposal_No").value;
		var layer = document.getElementById("layerNo").value;			
		document.forms[0].action="${pageContext.request.contextPath}/claimViewClaim.do?mode=Bmode&claim_No="+claim_No+"&contarctno="+policy_Contract_No+"&proposal_No="+proNo+"&layerNo="+layer+"&premiumclaimMode=Yes";
		document.forms[0].submit();
	}
	function GetSection()
		{
			 	var contractNo=document.premium.contNo.value;				
				var deptId=document.premium.departmentId.value;
				var section=document.premium.sectionName.value;
				var sectionType=document.premium.sectionType.value;	
				if(section==null || section==''){
				alert("Please Enter Section Name");
				}else{							
			 	if(contractNo!=null && contractNo!="" && deptId!=null && deptId!="" &&section!=null && section!="" && sectionType!=null && sectionType!="")
				{
				document.premium.sectionType.value='2';
					var URL='<%=request.getContextPath()%>/GetSectionProportionPremium.action?contNo='+contractNo+'&departmentId='+deptId+'&sectionName='+section+'&sectionType='+sectionType+'&dropDown=section';
  	 				postRequest(URL,'section');					
				}
				}
		}GetSectionType();
		function GetSectionType()
		{
				var sectionType=document.premium.sectionType.value;
				var contractNo=document.premium.contNo.value;				
				var deptId=document.premium.departmentId.value;
				var section=document.premium.sectionName.value;	
				if(	sectionType=='2'){						
			 	if(contractNo!=null && contractNo!="" && deptId!=null && deptId!="")
				{
				document.premium.sectionType.value='2';
					var URL='<%=request.getContextPath()%>/GetSectionProportionPremium.action?contNo='+contractNo+'&departmentId='+deptId+'&sectionName='+section+'&dropDown=section';
  	 				postRequest(URL,'section');					
				}
				}else if(sectionType=='1'){
				var URL='<%=request.getContextPath()%>/GetSectionProportionPremium.action?dropDown=section1';
  	 				postRequest(URL,'section');
				}
		}	
	function ViewModeClaim(policy_Contract_No,claim_No) {
		var URL ='';
		var proNo = document.getElementById("proposal_No").value;
		var layer = document.getElementById("layerNo").value;
		var departmentId =document.premium.departmentId.value;
			URL ='${pageContext.request.contextPath}/claimViewClaim.do?mode=Bmode&claim_No='+claim_No+'&contarctno='+policy_Contract_No+'&proposal_No='+proNo+'&layerNo='+layer+'&premiumclaimMode=Yes&departmentId='+departmentId;
		
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
	var proposal_No = document.getElementById("proposal_No").value;
	var enteringMode=document.premium.enteringMode.value;
	var val = document.getElementById("claimPaymentNo").value;
	var val1 = document.getElementById("creditAmountCLC").value;
	var val2 = document.getElementById("creditAmountCLD").value;
	var val3 = document.getElementById("CLCsettlementRate").value;
	
		//var currencyid = document.getElementById("baseCurrencyId").value;
		var currencyid = document.getElementById("currId").value;
		var exchangeRate =document.premium.exchRate.value;
		if(exchangeRate ==null || exchangeRate ==''){
	        alert("Please Enter Exchange Rate ");
		}
		if(enteringMode==1){
	 	alert("This facility is not available when 100% figures are being keyed in");
		}
		if(enteringMode!=1 && exchangeRate !=null && exchangeRate !=''){
		document.getElementById("_enteringMode1").disabled = true;
        document.getElementById("_enteringMode2").disabled = true;
		var URL ='${pageContext.request.contextPath}/cashLossCreditProportionPremium.do?contNo='+contrctNo+'&proposal_No='+proposal_No+'&currencyId='+currencyid+'&exchRate='+exchangeRate+'&mainclaimPaymentNos='+val+'&maincreditAmountCLClist='+val1+'&maincreditAmountCLDlist='+val2+'&mainCLCsettlementRatelist='+val3;
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
    var exchRate = document.premium.exchRate.value;
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
	    document.premium.transaction.disabled = true;
        document.premium.currency.disabled = true;
        document.premium.statementDate.disabled = true;
        
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
	var id="countStatus";
	var enteringMode=document.premium.enteringMode.value;
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
			document.premium.transaction.disabled = true;
	        document.premium.currency.disabled = true;
	    
	  	 	 $.ajax({
		        type: "POST",
		        url:'${pageContext.request.contextPath}/getReserveCountProportionPremium.do?contNo='+contrctNo+'&currencyId='+currencyid+'&transaction='+transDate+'&mainclaimPaymentNos='+val+'&maincreditAmountCLClist='+val1+'&maincreditAmountCLDlist='+val2+'&mainCLCsettlementRatelist='+val3+'&type='+value+'&dropDown=countStatus',
		        //data:'src_type_id='+val,
		        success: function(data){
		            $('#' + id).html(data);
		           
		        }
		        });
		}
}
		function EditMode()
		{
		document.premium.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=PremiumSearch";
		}

		function back(premiumMasterMode)
		{
		destroyPopUps();
		var contNo=document.getElementById("contNo").value;   
		var proposal_No = document.getElementById("proposal_No").value; 
		var sectionNo= document.getElementById("sectionNo").value; 
		document.forms[0].action="${pageContext.request.contextPath}/premiumListProportionPremium.do?mode=add&contNo="+contNo+"&premiumMasterMode="+premiumMasterMode+"&proposal_No="+proposal_No+"&sectionNo="+sectionNo;
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
		var mode = document.getElementById("mode").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && "edit"!=mode){
		var PremiumQshare=document.premium.premiumQuotaShare.value;
		PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		var commssion=document.premium.commssion_S.value;
		var percentage=parseFloat(commssion)/100;
		var b=value.replace(new RegExp(',', 'g'),'');
		var a=parseFloat(b)*percentage;
	 	if(value!="")
		{
		document.premium.commissionQuotaShare.value=Comma(a.toFixed(2));
		}
		else
		{
		document.premium.commissionQuotaShare.value='';
		}
		var brokerage=document.premium.brokerage_S.value;
		var brokerpercentage=parseFloat(brokerage)/100;
	    var premiumSurplus=document.premium.premiumSurplus.value;
	    premiumSurplus=premiumSurplus.replace(new RegExp(',', 'g'),'');
			if(PremiumQshare!="" && premiumSurplus!="" )
			{
				var b=parseFloat(premiumSurplus)+parseFloat(PremiumQshare);
				var c=b*brokerpercentage;
				document.premium.brokerage.value=Comma(c.toFixed(2));
				var tax_view=document.premium.tax_view.value;
				var tax_percen=parseFloat(tax_view)/100;
				var d=b*tax_percen;
				document.premium.tax.value=Comma(d.toFixed(2));
				var otherCost=document.premium.otherCost_S.value;
				var percentage3=parseFloat(otherCost)/100;
				var e=b*percentage3;
				document.premium.otherCost.value=Comma(e.toFixed(2));
				var overrider=document.premium.overRider_S.value;
				var percentage4=parseFloat(overrider)/100;
				var f=b*percentage4;
				document.premium.overrider.value=Comma(f.toFixed(2));
				
			}
				else if(PremiumQshare!="")
				{
					var b=parseFloat(PremiumQshare);
					var c=b*brokerpercentage;
					document.premium.brokerage.value=Comma(c.toFixed(2));
					var tax_view=document.premium.tax_view.value;
					var tax_percen=parseFloat(tax_view)/100;
					var d=b*tax_percen;
					document.premium.tax.value=Comma(d.toFixed(2));
					var otherCost=document.premium.otherCost_S.value;
					var percentage3=parseFloat(otherCost)/100;
					var e=b*percentage3;
					document.premium.otherCost.value=Comma(e.toFixed(2));
					var overrider=document.premium.overRider_S.value;
					var percentage4=parseFloat(overrider)/100;
					var f=b*percentage4;
					document.premium.overrider.value=Comma(f.toFixed(2));
				}
					else if(premiumSurplus!="")
					{
						var b=parseFloat(premiumSurplus);
						var c=b*brokerpercentage;
						document.premium.brokerage.value=c.toFixed(2);
						var tax_view=document.premium.tax_view.value;
						var tax_percen=parseFloat(tax_view)/100;
						var d=b*tax_percen;
						document.premium.tax.value=d.toFixed(2);
						var otherCost=document.premium.otherCost_S.value;
						var percentage3=parseFloat(otherCost)/100;
						var e=b*percentage3;
						document.premium.otherCost.value=Comma(e.toFixed(2));
						var overrider=document.premium.overRider_S.value;
						var percentage4=parseFloat(overrider)/100;
						var f=b*percentage4;
						document.premium.overrider.value=Comma(f.toFixed(2));
					}
					else if(PremiumQshare== "" && premiumSurplus=="")
					{
						document.premium.commissionQuotaShare.value=''
					    document.premium.brokerage.value='';
						document.premium.tax.value='';
						document.premium.otherCost.value='';
						document.premium.overrider.value='';
					} 
						    }
		</s:if>
		}
		function Caculate(value)
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
		    }
		    else
		    {
				    document.premium.commissionQuotaShare.value='';
				    document.premium.brokerage.value='';
				    document.premium.tax.value='';
				    document.premium.otherCost.value='';
			}
			document.getElementById("CalculateTax").value = "Completed";
		}
		</s:if>
		}
        function GetFacInstalment(value)
        {
	        if(value!=0 && value!='EP')
	        {
	        	    
					var contNo=document.premium.contNo.value;
		        	try {
		        	document.getElementById("enable2").checked=true;
                    } catch (e) {
                    }
			       	if (window.XMLHttpRequest)
				  	{
				   		xmlhttp=new XMLHttpRequest();
				    } 	
					else
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function()
					{
						if(xmlhttp.readyState==4 && xmlhttp.status==200)
						{
						 	document.premium.premiumQuotaShare.value=Comma(xmlhttp.responseText||0);
						 	Caculate(document.premium.premiumQuotaShare.value);
						 	facNetDue();
						}
					}
					xmlhttp.open("POST",'<%=request.getContextPath()%>/instalmentPremiumProportionPremium.do?method=GetInstalmentPremium&contNo='+contNo+'&value='+value,true);
					xmlhttp.send();
			}else
			{
        	    document.premium.premiumQuotaShare.removeAttribute('readonly');
				document.premium.premiumQuotaShare.value="";
				Caculate(document.premium.premiumQuotaShare.value);
				facNetDue();
			}
	    }
		function calculationSurplus(value)
		{
		var calc = document.getElementById("CalculateTax").value ;
		var mode = document.getElementById("mode").value ;
		<s:if test="!hasActionErrors()">
		
		if("Completed"!=calc && "edit"!=mode){
			var premiumSurplus=document.premium.premiumSurplus.value;
			premiumSurplus=premiumSurplus.replace(new RegExp(',','g'),'');
			var commssion=document.premium.commssion_Surp.value;
			var percentage=parseFloat(commssion)/100;
			var a=parseFloat(premiumSurplus)*percentage;
			if(value!="")
			{
			document.premium.commissionSurplus.value=Comma(a.toFixed(2));
			}
			else
			{
			document.premium.commissionSurplus.value='';
			}
			var brokerage=document.premium.brokerage_S.value;
			var brokerpercentage=parseFloat(brokerage)/100;
			var PremiumQshare=document.premium.premiumQuotaShare.value;
			PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
		if(PremiumQshare!="" && premiumSurplus!="" )
		{
			var b=parseFloat(premiumSurplus)+parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.premium.brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.premium.tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.premium.tax.value=Comma(d.toFixed(2));
			var otherCost=document.premium.otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.premium.otherCost.value=Comma(e.toFixed(2));
			var overrider=document.premium.overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.premium.overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare!="")
		{
			var b=parseFloat(PremiumQshare);
			var c=b*brokerpercentage;
			document.premium.brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.premium.tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.premium.tax.value=Comma(d.toFixed(2));
			var otherCost=document.premium.otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.premium.otherCost.value=Comma(e.toFixed(2));
			var overrider=document.premium.overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.premium.overrider.value=Comma(f.toFixed(2));
		}
		else if(premiumSurplus!="")
		{
			var b=parseFloat(premiumSurplus);
			var c=b*brokerpercentage;
			document.premium.brokerage.value=Comma(c.toFixed(2));
			var tax_view=document.premium.tax_view.value;
			var tax_percen=parseFloat(tax_view)/100;
			var d=b*tax_percen;
			document.premium.tax.value=d.toFixed(2);
			var otherCost=document.premium.otherCost_S.value;
			var percentage3=parseFloat(otherCost)/100;
			var e=b*percentage3;
			document.premium.otherCost.value=Comma(e.toFixed(2));
			var overrider=document.premium.overRider_S.value;
			var percentage4=parseFloat(overrider)/100;
			var f=b*percentage4;
			document.premium.overrider.value=Comma(f.toFixed(2));
		}
		else if(PremiumQshare== "" && premiumSurplus=="")
		{
		document.premium.tax.value='';
		document.premium.brokerage.value='';
		document.premium.commissionSurplus.value='';
		document.premium.otherCost.value='';
		document.premium.overrider.value='';
		}	
		}
		</s:if>
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
		function GetExchangeRate()
		{
			 	var month=document.premium.transaction.value;				
				var Currecny=document.getElementById("currId").value;								
				for(var i=0;;i++){
					if(document.getElementById("currencyIdDis"+i)!=null){
						document.getElementById("currencyIdDis"+i).value=Currecny;
						document.getElementById("currencyIds"+i).value=Currecny;
					}else
						break;
				}								
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
		       // <s:if test='%{ClaimNos.size()==0}'>			    
			   // document.premium.cashLoss_Credit.value='0';
			   // document.premium.cashLoss_Credit.setAttribute('readonly', 'readonly');
			   // </s:if>
			    toggleCashLossCredit(document.premium.cashLoss_Credit.value);
			    document.premium.cashLoss_Credit.value=Comma(document.premium.cashLoss_Credit.value);
			    document.premium.cliam_portfolio_out.value=Comma(document.premium.cliam_portfolio_out.value);
			    document.premium.premiumportifolioout.value=Comma(document.premium.premiumportifolioout.value);
			    document.premium.premiumReserve_QuotaShare.value=Comma(document.premium.premiumReserve_QuotaShare.value);
			    document.premium.profit_Commission.value=Comma(document.premium.profit_Commission.value);
			    
			    document.premium.cash_LossPaid.value=Comma(document.premium.cash_LossPaid.value);
			    document.premium.claims_paid.value=Comma(document.premium.claims_paid.value);
			    document.premium.premiumSurplus.value=Comma(document.premium.premiumSurplus.value);
			    document.premium.commissionSurplus.value=Comma(document.premium.commissionSurplus.value);
			    document.premium.premiumportifolioIn.value=Comma(document.premium.premiumportifolioIn.value);
			    
			    document.premium.otherCost.value=Comma(document.premium.otherCost.value);
			    document.premium.lossReserveReleased.value=Comma(document.premium.lossReserveReleased.value);
			    document.premium.xl_Cost.value=Comma(document.premium.xl_Cost.value);
			    document.premium.brokerage.value=Comma(document.premium.brokerage.value);
			    document.premium.premium_Reserve_Released.value=Comma(document.premium.premium_Reserve_Released.value);

			    document.premium.tax.value=Comma(document.premium.tax.value);
			    document.premium.cliamPortfolioin.value=Comma(document.premium.cliamPortfolioin.value);
			    document.premium.loss_ReserveRetained.value=Comma(document.premium.loss_ReserveRetained.value);
			    document.premium.commissionQuotaShare.value=Comma(document.premium.commissionQuotaShare.value);
			    document.premium.premiumQuotaShare.value=Comma(document.premium.premiumQuotaShare.value);
			    document.premium.netDue.value=Comma(document.premium.netDue.value);
			    document.premium.taxDedectSource.value=Comma(document.premium.taxDedectSource.value);
			    document.premium.vatPremium.value=Comma(document.premium.vatPremium.value);
			     document.premium.lossParticipation.value=Comma(document.premium.lossParticipation.value);
			    proposalNetDue();							
		}
		function twoDecimal(){

		var a=document.premium.cashLoss_Credit.value.replace(new RegExp(',', 'g'),'');
		document.premium.cashLoss_Credit.value=Comma(parseFloat(a).toFixed(2));
		
		var b=document.premium.cliam_portfolio_out.value.replace(new RegExp(',', 'g'),'');
		document.premium.cliam_portfolio_out.value=Comma(parseFloat(b).toFixed(2));
		
		var c=document.premium.premiumportifolioout.value.replace(new RegExp(',', 'g'),'');
		document.premium.premiumportifolioout.value=Comma(parseFloat(c).toFixed(2));
		
		var d=document.premium.premiumReserve_QuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.premium.premiumReserve_QuotaShare.value=Comma(parseFloat(d).toFixed(2));
		
		var e=document.premium.profit_Commission.value.replace(new RegExp(',', 'g'),'');
		document.premium.profit_Commission.value=Comma(parseFloat(e).toFixed(2));
		
		var f=document.premium.cash_LossPaid.value.replace(new RegExp(',', 'g'),'');
		document.premium.cash_LossPaid.value=Comma(parseFloat(f).toFixed(2));
		
		var g=document.premium.claims_paid.value.replace(new RegExp(',', 'g'),'');
		document.premium.claims_paid.value=Comma(parseFloat(g).toFixed(2));
		
		var h=document.premium.premiumSurplus.value.replace(new RegExp(',', 'g'),'');
		document.premium.premiumSurplus.value=Comma(parseFloat(h).toFixed(2));
		
		var i=document.premium.commissionSurplus.value.replace(new RegExp(',', 'g'),'');
		document.premium.commissionSurplus.value=Comma(parseFloat(i).toFixed(2));
		
		var j=document.premium.premiumportifolioIn.value.replace(new RegExp(',', 'g'),'');
		document.premium.premiumportifolioIn.value=Comma(parseFloat(j).toFixed(2));
		
		var k=document.premium.otherCost.value.replace(new RegExp(',', 'g'),'');
		document.premium.otherCost.value=Comma(parseFloat(k).toFixed(2));
		
		var l=document.premium.lossReserveReleased.value.replace(new RegExp(',', 'g'),'');
		document.premium.lossReserveReleased.value=Comma(parseFloat(l).toFixed(2));
		
		var m=document.premium.xl_Cost.value.replace(new RegExp(',', 'g'),'');
		document.premium.xl_Cost.value=Comma(parseFloat(m).toFixed(2));
		
		var n=document.premium.brokerage.value.replace(new RegExp(',', 'g'),'');
		document.premium.brokerage.value=Comma(parseFloat(n).toFixed(2));
		
		var o=document.premium.premium_Reserve_Released.value.replace(new RegExp(',', 'g'),'');
		document.premium.premium_Reserve_Released.value=Comma(parseFloat(o).toFixed(2));
		
		var p=document.premium.tax.value.replace(new RegExp(',', 'g'),'');
		document.premium.tax.value=Comma(parseFloat(p).toFixed(2));
		
		var q=document.premium.cliamPortfolioin.value.replace(new RegExp(',', 'g'),'');
		document.premium.cliamPortfolioin.value=Comma(parseFloat(q).toFixed(2));
		
		var r=document.premium.loss_ReserveRetained.value.replace(new RegExp(',', 'g'),'');
		document.premium.loss_ReserveRetained.value=Comma(parseFloat(r).toFixed(2));
		
		var s=document.premium.commissionQuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.premium.commissionQuotaShare.value=Comma(parseFloat(s).toFixed(2));

		var t=document.premium.premiumQuotaShare.value.replace(new RegExp(',', 'g'),'');
		document.premium.premiumQuotaShare.value=Comma(parseFloat(t).toFixed(2));
		
		var w=document.premium.netDue.value.replace(new RegExp(',', 'g'),'');
		document.premium.netDue.value=Comma(parseFloat(w).toFixed(2));
		
		var u=document.premium.interest.value.replace(new RegExp(',', 'g'),'');
		document.premium.interest.value=Comma(parseFloat(u).toFixed(2));
		
		var v=document.premium.withHoldingTaxOC.value.replace(new RegExp(',', 'g'),'');
		document.premium.withHoldingTaxOC.value=Comma(parseFloat(v).toFixed(2));
		
		var x=document.premium.overrider.value.replace(new RegExp(',', 'g'),'');
		document.premium.overrider.value=Comma(parseFloat(x).toFixed(2));
		
		var y=document.premium.taxDedectSource.value.replace(new RegExp(',', 'g'),'');
		document.premium.taxDedectSource.value=Comma(parseFloat(y).toFixed(2));
		
		var z=document.premium.vatPremium.value.replace(new RegExp(',', 'g'),'');
		document.premium.vatPremium.value=Comma(parseFloat(z).toFixed(2));
		
		var aa=document.premium.lossParticipation.value.replace(new RegExp(',', 'g'),'');
		document.premium.lossParticipation.value=Comma(parseFloat(aa).toFixed(2));
		
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
		function facNetDue()
		{
            var withHoldingTaxI = form.find('input[name="withHoldingTaxOC"]');
            var wHTax = withHoldingTaxI.val().replace(new RegExp(',', 'g'),'') || withHoldingTaxI.val("").val().length;
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
				var A=parseFloat(PremiumQshare);
				var B=parseFloat(CommissionQshare)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost)+ parseFloat(wHTax);
				document.premium.totalCredit.value=Comma(A.toFixed(2));
				document.premium.totalDebit.value=Comma(B.toFixed(2));
				var NetDue = A-B;
//            NetDue = parseFloat(PremiumQshare) - parseFloat(CommissionQshare) - parseFloat(Brokerage) - parseFloat(Tax) - parseFloat(OtherCost);
				document.premium.netDue.value=Comma(NetDue.toFixed(2));
		}
		function getMiPremium(){
			var m1premium=document.premium.mipremium1.value;
			if(m1premium!=null && m1premium!="")
				m1premium=m1premium.replace(new RegExp(',', 'g'),'');
			else
				m1premium=0;
			var m2premium=document.premium.mipremium2.value;
			if(m2premium!=null && m2premium!="")
				m2premium=m2premium.replace(new RegExp(',', 'g'),'');
			else
				m2premium=0;
			var m3premium=document.premium.mipremium3.value;
			if(m3premium!=null && m3premium!="")
				m3premium=m3premium.replace(new RegExp(',', 'g'),'');
			else
				m3premium=0;
			
			var A=parseFloat(m1premium)+parseFloat(m2premium)+parseFloat(m3premium);
			document.premium.mipremium.value=Comma(A.toFixed(2));
			
		}
		function misumit(){
			var mipremium=document.getElementById('mipremium').value;
			mipremium=mipremium.replace(new RegExp(',', 'g'),'');
			alert(mipremium);
			mipremium=parseFloat(mipremium);
			document.premium.premiumQuotaShare.value=Comma(mipremium.toFixed(2));
			getVATInfo(mipremium,'vattaxid');
			Calculation(mipremium);proposalNetDue();getpremiumDeposit('N');
			
		}
		function proposalNetDue()
		{
            var withHoldingTaxI = form.find('input[name="withHoldingTaxOC"]');
            var wHTax = withHoldingTaxI.val().replace(new RegExp(',', 'g'),'') || withHoldingTaxI.val("").val().length;
				var PremiumQshare=document.premium.premiumQuotaShare.value;
				if(PremiumQshare!=null && PremiumQshare!="")
					PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
				else 
					PremiumQshare=0;
				var PremiumSurplus=document.premium.premiumSurplus.value;
				if(PremiumSurplus!=null && PremiumSurplus!="")
					PremiumSurplus=PremiumSurplus.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumSurplus=0;
				
				var PremiumportifolioIn=document.premium.premiumportifolioIn.value;
				if(PremiumportifolioIn!=null && PremiumportifolioIn!="")
					PremiumportifolioIn=PremiumportifolioIn.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumportifolioIn=0;
				
				var CliamPortfolioin=document.premium.cliamPortfolioin.value;
				if(CliamPortfolioin!=null && CliamPortfolioin!="")
					CliamPortfolioin=CliamPortfolioin.replace(new RegExp(',', 'g'),'');
				else
				 	CliamPortfolioin=0;
				var Premium_Reserve_Released=document.premium.premium_Reserve_Released.value;
				if(Premium_Reserve_Released!=null && Premium_Reserve_Released!="")
					Premium_Reserve_Released=Premium_Reserve_Released.replace(new RegExp(',', 'g'),'');
				else
				 	Premium_Reserve_Released=0;
				var LossReserveReleased=document.premium.lossReserveReleased.value;
				if(LossReserveReleased!=null && LossReserveReleased!="")
					LossReserveReleased=LossReserveReleased.replace(new RegExp(',', 'g'),'');
				else
				 	LossReserveReleased=0;
				var Interest=document.premium.interest.value;
				if(Interest!=null && Interest!="")
					Interest=Interest.replace(new RegExp(',', 'g'),'');
				else
				 	Interest=0; 	
				var CashLoss_Credit=document.premium.cashLoss_Credit.value;
				if(CashLoss_Credit!=null && CashLoss_Credit!="")
					CashLoss_Credit=CashLoss_Credit.replace(new RegExp(',', 'g'),'');
				else
				 	CashLoss_Credit=0;
			
				
				var CommissionQshare=document.premium.commissionQuotaShare.value;
				if(CommissionQshare!=null && CommissionQshare!="")
					CommissionQshare=CommissionQshare.replace(new RegExp(',', 'g'),'');
				else
					CommissionQshare=0;
				
				var CommissionSurplus=document.premium.commissionSurplus.value;
				if(CommissionSurplus!=null && CommissionSurplus!="")
					CommissionSurplus=CommissionSurplus.replace(new RegExp(',', 'g'),'');
				else
				 	CommissionSurplus=0;
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
				var overrider=document.premium.overrider.value;
				if(overrider!=null && overrider!="")
					overrider=overrider.replace(new RegExp(',', 'g'),'');
				else
				 	overrider=0; 	
				
				var OtherCost=document.premium.otherCost.value;
				if(OtherCost!=null && OtherCost!="")
					OtherCost=OtherCost.replace(new RegExp(',', 'g'),'');
				else
				 	OtherCost=0;
				
				var Xl_Cost=document.premium.xl_Cost.value;
				if(Xl_Cost!=null && Xl_Cost!="")
					Xl_Cost=Xl_Cost.replace(new RegExp(',', 'g'),'');
				else
				 	Xl_Cost=0;
				var Premiumportifolioout=document.premium.premiumportifolioout.value;
				if(Premiumportifolioout!=null && Premiumportifolioout!="")
					Premiumportifolioout=Premiumportifolioout.replace(new RegExp(',', 'g'),'');
				else
				 	Premiumportifolioout=0;
				var Cliam_portfolio_out=document.premium.cliam_portfolio_out.value;
				if(Cliam_portfolio_out!=null && Cliam_portfolio_out!="")
					Cliam_portfolio_out=Cliam_portfolio_out.replace(new RegExp(',', 'g'),'');
				else
				 	Cliam_portfolio_out=0;
				
				var PremiumReserve_QuotaShare=document.premium.premiumReserve_QuotaShare.value;
				if(PremiumReserve_QuotaShare!=null && PremiumReserve_QuotaShare!="")
					PremiumReserve_QuotaShare=PremiumReserve_QuotaShare.replace(new RegExp(',', 'g'),'');
				else
				 	PremiumReserve_QuotaShare=0;
				var Loss_ReserveRetained=document.premium.loss_ReserveRetained.value;
				if(Loss_ReserveRetained!=null && Loss_ReserveRetained!="")
					Loss_ReserveRetained=Loss_ReserveRetained.replace(new RegExp(',', 'g'),'');
				else
				 	Loss_ReserveRetained=0;
				var Profit_Commission=document.premium.profit_Commission.value;
				if(Profit_Commission!=null && Profit_Commission!="")
					Profit_Commission=Profit_Commission.replace(new RegExp(',', 'g'),'');
				else
				 	Profit_Commission=0;
				
				var Cash_LossPaid=document.premium.cash_LossPaid.value;
				if(Cash_LossPaid!=null && Cash_LossPaid!="")
					Cash_LossPaid=Cash_LossPaid.replace(new RegExp(',', 'g'),'');
				else
				 	Cash_LossPaid=0;
				var Claims_paid=document.premium.claims_paid.value;
				if(Claims_paid!=null && Claims_paid!="")
					Claims_paid=Claims_paid.replace(new RegExp(',', 'g'),'');
				else
				 	Claims_paid=0;
				 	
				var taxDedectSource=document.premium.taxDedectSource.value;
				if(taxDedectSource!=null && taxDedectSource!="")
					taxDedectSource=taxDedectSource.replace(new RegExp(',', 'g'),'');
				else
				 	taxDedectSource=0;
				 	
				var vatPremium=document.premium.vatPremium.value;
				if(vatPremium!=null && vatPremium!="")
					vatPremium=vatPremium.replace(new RegExp(',', 'g'),'');
				else
				 	vatPremium=0;
				 	
				 var lossParticipation=document.premium.lossParticipation.value;
				if(lossParticipation!=null && lossParticipation!="")
					lossParticipation=lossParticipation.replace(new RegExp(',', 'g'),'');
				else
				 	lossParticipation=0;
				
				var brokerageVat=document.premium.brokerageVat.value;
				if(brokerageVat!=null && brokerageVat!="")
					brokerageVat=brokerageVat.replace(new RegExp(',', 'g'),'');
				else
					brokerageVat=0;
				 	
				 	
				var per = document.premium.account_Period.value;
				var slide = 0;

				//if("8"==per) {
                     slide = document.premium.slideScaleCom.value;
                    if (slide != null && slide != "") {
                        slide = slide.replace(new RegExp(',', 'g'), '');
                    }else{
                        slide=0;
					}

                //}
				var A=parseFloat(PremiumQshare)+parseFloat(PremiumSurplus)+parseFloat(PremiumportifolioIn)+parseFloat(CliamPortfolioin)+parseFloat(Premium_Reserve_Released)+parseFloat(LossReserveReleased)+parseFloat(Interest)+parseFloat(CashLoss_Credit)+ parseFloat(taxDedectSource)+ parseFloat(vatPremium) + parseFloat(lossParticipation);
				var B=parseFloat(CommissionQshare)+parseFloat(CommissionSurplus)+parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(overrider)+parseFloat(OtherCost)+parseFloat(Xl_Cost)+parseFloat(Premiumportifolioout)+parseFloat(Cliam_portfolio_out)+parseFloat(PremiumReserve_QuotaShare)+parseFloat(Loss_ReserveRetained)+parseFloat(Profit_Commission)+parseFloat(Cash_LossPaid)+parseFloat(Claims_paid) + parseFloat(wHTax)+ parseFloat(slide)+parseFloat(brokerageVat);
				document.premium.totalCredit.value=Comma(A.toFixed(2));
				document.premium.totalDebit.value=Comma(B.toFixed(2));
				var NetDue = A-B;
//                NetDue = (parseFloat(PremiumQshare) + parseFloat(PremiumSurplus) + parseFloat(PremiumportifolioIn) + parseFloat(CliamPortfolioin) + parseFloat(Premium_Reserve_Released) + parseFloat(LossReserveReleased) + parseFloat(Interest) + parseFloat(CashLoss_Credit)) - (parseFloat(CommissionQshare) + parseFloat(CommissionSurplus) + parseFloat(Brokerage) + parseFloat(Tax) + parseFloat(overrider) + parseFloat(OtherCost) + parseFloat(Xl_Cost) + parseFloat(Premiumportifolioout) + parseFloat(Cliam_portfolio_out) + parseFloat(PremiumReserve_QuotaShare) + parseFloat(Loss_ReserveRetained) + parseFloat(Profit_Commission) + parseFloat(Cash_LossPaid) + parseFloat(Claims_paid));
				document.premium.netDue.value=Comma(NetDue.toFixed(2));
		}
		function xolNetDue()
		{
            var withHoldingTaxI = form.find('input[name="withHoldingTaxOC"]');
            var wHTax = withHoldingTaxI.val().replace(new RegExp(',', 'g'),'') ||  withHoldingTaxI.val("").val().length;
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
				var A=NetDue=parseFloat(Premium);
				var B=parseFloat(Brokerage)+parseFloat(Tax)+parseFloat(OtherCost) + parseFloat(wHTax);
				document.premium.totalCredit.value=Comma(A.toFixed(2));
				document.premium.totalDebit.value=Comma(B.toFixed(2)); 
				var NetDue = A-B;
//                    NetDue = parseFloat(Premium) - parseFloat(Brokerage) - parseFloat(Tax) - parseFloat(OtherCost);
				document.premium.netDue.value=Comma(NetDue.toFixed(2));
				}
		}
		function chkPremiumQS(){
		if(document.premium.premiumQuotaShare.value==null || document.premium.premiumQuotaShare.value==""){
			document.premium.commissionQuotaShare.value="";
		}
		}
		
		function chkPremiumSurplus(){
			if(document.premium.premiumSurplus.value==null || document.premium.premiumSurplus.value==""){
				document.premium.commissionSurplus.value="";
			}
		}
		function getViewContractDetails(prodId,proposalNo,amendId) {
		var URL ='';
		
		if(prodId=='1'){
		
			URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId+'&product_id='+prodId;
		}
		else if(prodId=='2') {
			URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId+'&product_id='+prodId;
		}
		else if((prodId=='3')||(prodId=='5')) {
			URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId+'&product_id='+prodId;
		}
		else {
			URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId+'&product_id='+prodId;
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

function removeElementsWithValue(arr, val) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === val) {
            arr.splice(i, 1);
        }
    }
    return arr;
}

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

    // GetSlideDisplay()
	function GetSlideDisplay() {
			var per =document.premium.account_Period.value;
			var val ='<s:property value="profitCommYN"/>'
			if("8"==per){
			    document.getElementById("slide1").style.display='table-row';
			    document.premium.profit_Commission.value = '0.00';
			    document.getElementById("ProfitComm").style.display='none';
			}
			else if("7"==per){
				document.premium.slideScaleCom.value = '0.00';
				document.getElementById("slide1").style.display='none';
				document.getElementById("ProfitComm").style.display='block';
			}
			else if("9"==per){
				document.getElementById("lossPat").style.display='block';
				document.getElementById("slide1").style.display='none';
			}
			else{
                document.premium.slideScaleCom.value = '0.00';
                document.premium.profit_Commission.value = '0.00';
                document.getElementById("slide1").style.display='none';
                document.getElementById("lossPat").style.display='none';
                 if(val!=2){
                	document.getElementById("ProfitComm").style.display='none';
                }
               

			}
    }
    function GetOSBDisplay(){
	    var per =document.premium.account_Period.value;
	    if("7"==per || "8"==per){
	    	 document.getElementById("osbYNYes").disabled = true;
	    	 document.getElementById("osbYNNo").disabled = true;
	    	document.premium.osClaimsLossUpdateOC.disabled = true;
	    	document.premium.osbYN.value = 'No';
	    }else{
	     document.getElementById("osbYNYes").disabled = false;
	     document.getElementById("osbYNNo").disabled = false;
	   	 document.premium.osClaimsLossUpdateOC.disabled = false;
	    }
    }
    
    getoutStanding();
    function getoutStanding(){
    	val = document.premium.osbYN.value;
	    if(val=='Yes'){
	    	document.premium.osClaimsLossUpdateOC.disabled = false;
	    	document.getElementById("accountPrd").style.display = 'block';
	    	getLableValue();
	    	
	    }else{
		    document.premium.osClaimsLossUpdateOC.disabled = true;
		    document.getElementById("accountPrd").style.display = 'none';
		    document.premium.osClaimsLossUpdateOC.value = '';
	    }
    }
    function getLableValue(){
    	var accTypeval =document.premium.account_Period.value ;
    	var accYear = document.premium.account_Period_year.value;
    	var mode = document.premium.mode.value;
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
			document.getElementById('accountPrd').textContent =document.premium.transaction.value;
			if("edit"!=mode){
  			document.premium.osClaimsLossUpdateOC.value = document.premium.cliamPortfolioin.value ;
  			}
  			}
		else if(accTypeval == '6'){
			document.getElementById('accountPrd').textContent =document.premium.statementDate.value;
		}
		else{
		document.getElementById('accountPrd').textContent ='';
		}
    }
    
   function ViewOSB(){
   	var contNo=document.premium.contNo.value;
    var account_Period=document.premium.account_Period.value;
    var account_Period_year=document.premium.account_Period_year.value;
    var currency=document.premium.currency.value;
    var statementDate=document.premium.statementDate.value;
    var transaction=document.premium.transaction.value;
    var accountPeriodDate = document.premium.accountPeriodDate.value;
    if(transaction ==null || transaction ==''){
        alert("Please Select Transaction Date ");
		}
		if(currency == null || currency ==''){
        alert("Please Select Currency");
		}
		if(statementDate == null || statementDate ==''){
        alert("Please Select Statement Date");
		}
		if(account_Period == null || account_Period =='0' || account_Period_year == null || account_Period_year =='0'){
        alert("Please Select Accounting Period");
		}
    if(currency!=null && currency!='' && statementDate!=null && statementDate!='' && transaction!=null && transaction!=''){
    var URL='<%=request.getContextPath()%>/OBSAjaxListProportionPremium.action?contNo='+contNo+'&account_Period='+account_Period+'&account_Period_year='+account_Period_year+'&currency='+currency+'&statementDate='+statementDate+'&transaction='+transaction+'&accountPeriodDate='+accountPeriodDate+'&dropDown=obsList';
  	 postRequest(URL,'obsList');
  	 }
   }
  function GetOSBValue(){
   var per =document.premium.account_Period.value;
   var cliamPortfolioin =document.premium.cliamPortfolioin.value;
    if("5"==per){
    document.premium.osClaimsLossUpdateOC.value = cliamPortfolioin;
    }else{
    document.premium.osClaimsLossUpdateOC.value = '0.00';
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
      //getAccDate();
      function getAccDate(){
      var period =document.getElementById("account_Period").value;
      var year = document.getElementById("account_Period_year").value;
      var inception = document.getElementById("insDate").value;
      var date1=new Date("DD/MM/YYYY");
      var date ="";
      if(period!=''&&year!='0'){
      if("5"==(period)){
					date=document.premium.transaction.value;
				}
				else if("6"==(period) || "7"==(period) ||"8"==(period) ||"9"==(period) ){
					date=document.premium.statementDate.value;
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
var premiumReserve_QuotaShare = document.premium.premiumReserve_QuotaShare.value;
if(parseFloat(premiumReserve_QuotaShare)==0){
getpremiumDeposit('N');
}
function getpremiumDeposit(status){
var calc = document.getElementById("CalculateTax").value ;
var mode = document.getElementById("mode").value ;
	<s:if test="!hasActionErrors()">
		
	if("Completed"!=calc && "edit"!=mode){
	var resStatus = document.premium.premiumReserveUser.value;
	if('Y'!=resStatus && 'Y'!=status){
	var pemiumReserve = document.premium.premiumReserve.value;
	var PremiumQshare=document.premium.premiumQuotaShare.value;
	if(PremiumQshare!=null && PremiumQshare!="")
		PremiumQshare=PremiumQshare.replace(new RegExp(',', 'g'),'');
	else 
		PremiumQshare=0;
	var PremiumSurplus=document.premium.premiumSurplus.value;
	if(PremiumSurplus!=null && PremiumSurplus!="")
		PremiumSurplus=PremiumSurplus.replace(new RegExp(',', 'g'),'');
	else
	 	PremiumSurplus=0;
	 	
	 	var val = parseFloat(PremiumQshare)+parseFloat(PremiumSurplus);
	 	if(parseFloat(val)>0){
	 	var val1 = (val* parseFloat(pemiumReserve)/100 );
	 	var result = Comma(val1.toFixed(2));
	 	document.premium.premiumReserve_QuotaShare.value =result;
	 	}
	 	}
	 	if('Y'==status){
	 	document.premium.premiumReserveUser.value = status;
	 	document.getElementById("CalculateTax").value = "Completed";
	 	}
	 	}
	 	
	 	</s:if>
}
function getAjax(value,id) {
        $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=presubclass',
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

function AuthendicationBack(){
	document.premium.action="${pageContext.request.contextPath}/initAuthentication.do";
	document.premium.submit();
}
function showPopUp(value){
	var contrctNo = document.getElementById("contNo").value;
	var id="countStatus";
	var enteringMode=document.premium.enteringMode.value;
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
		var currencyid = document.getElementById("currId").value;
		var transDate=document.getElementById("transaction").value;
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

function showCashPopUp(){
		var contrctNo = document.getElementById("contNo").value;
		var enteringMode=document.premium.enteringMode.value;
		var val = document.getElementById("claimPaymentNo").value;
		var val1 = document.getElementById("creditAmountCLC").value;
		var val2 = document.getElementById("creditAmountCLD").value;
		var val3 = document.getElementById("CLCsettlementRate").value;
		var currencyid = document.getElementById("currId").value;
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
function getVATInfo(val,id){
	var proposal_No = document.getElementById("proposal_No").value;
  	$.ajax( {
		url : '${pageContext.request.contextPath}/getVatInfoXolPremium.action?proposal_No='+proposal_No+'&premiumAmount='+val+'&dropDown='+id,
		error : function() {
			$('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
		},
		success : function(data) {
			document.getElementById(id).innerHTML=data;
		},
		beforeSend : function() {
			//$('#loading').show();
			//$('.ajaxLoader').show();
		},
		complete : function() {
			proposalNetDue();
		},
		type : 'POST'
	});
}
getTrasView('<s:property value="documentType"/>');
function getTrasView(val){
	if(val=="R"){
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
function getTransactionData(){
	var val=document.getElementById("transDropDownVal").value;
	var documentType = document.premium.documentType.value;
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

	if(documentType==("R") ){
	document.premium.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do";
	document.premium.submit();
	}
	}
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
</script>		
</body>
</html>
