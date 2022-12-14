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
<s:form name="retroxolView" id="retroxolView" action="" method="post" theme="simple">
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
										<s:text name="heading.retroXOL" />
									</div>
									<div class="panel-body">
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ProposalNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposal_no" id="proposal_no" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ContNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="contNo" id="contNo"  cssClass="inputBox" cssStyle="text-align: right;" />
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
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.SubProfitCentre" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="subProfit_center" cssClass="inputBox" />
											</div>
										</div>	--%>										 
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
												<s:text name="label.businessType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="businessType" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.retroSessionLeader" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedingCo" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Broker" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="broker" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.retroTreatyNameType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatyName_type" cssClass="inputBox" />
											</div>
										</div>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.LayerNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="layerNo" cssClass="inputBox" />
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
												<s:textfield readonly="true" name="orginalCurrency" id="orginalCurrency" cssClass="inputBox" cssStyle="width:50%;float:left;" />
												<s:textfield readonly="true" name="exchRate" cssClass="inputBox" cssStyle="width:45%;float:left;text-align:right;" />
											</div>
										</div>
										<hr class="clear"/>
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
											<div class="textfieldA25">
													<s:text name="label.perilCovered"/>
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
												<s:text name="RiskDetails.TerritoryScope" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="territoryscope" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PortFolioCovered" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="portfoloCovered" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Basis" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="basis" cssClass="inputBox" />
											</div>
										</div>
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="label.perilCovered" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="perilCovered" cssClass="inputBox" />
											</div>
										</div>		--%>								
										
										<div class="textfield33">
												<div class="text">
													<s:text name="label.LOCIssued" />
												</div>
												<div class="tbox">													
													<s:textfield readonly="true" name="LOCIssued" cssClass="inputBox" />													
												</div>
											</div>
											<s:if test='"Yes" == LOCIssued'>
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
										<s:if test='"Combined Risk & Cat XOL" == businessType ||"Risk XOL" == businessType  '>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.umbrellaXL" />
												</div>
												<div class="tbox">													
													<s:textfield readonly="true" name="umbrellaXL" cssClass="inputBox" />													
												</div>
											</div>
										</s:if>
										<hr class="clear"/>
										<div class="boxcontent" id="stoplossno" style="display:none;">
								
										<div class="boxcontent">
										<div >
										<table class="table table-bordered" width="100%" id="newgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitPoc" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePoc" /> </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="CoverList" var="list" status="stat">
															<tr>
																	<td>
												 					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
												 					</td>
																	<td>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true"/>
																	<s:if test='0==(#stat.count-1)'>
																	<s:text name="(Main Class)" />
																	</s:if>
																	</td>
																	<td>
																	<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  disabled="true" />
																	
																	</td>
																	<td>
																	<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled="true"/>
																	</td>
																	<td>
																	<s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30" disabled="true"/>
																	</td>
																	<td >
																	<s:textfield name="gnpiAsPO[%{#stat.count-1}]" id="gnpiAsPO%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen');"  maxlength="30"  disabled="true"/>
																	</td>
																</tr>
																</s:iterator>
															</tbody>
															</table>
															<br class="clear"/>
														</div>
													</div>
												</div>
												<div class="boxcontent" id="stoploss" style="display:none;">
															<div class="boxcontent">
															<div >
															<table class="table table-bordered" width="100%" id="newgen1">
																			<thead>
																				<tr>
																					<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitAm" />  </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitper" />  </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleAm" /> </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePer" /> </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																					<th width="13%" style="text-align: center; vertical-align: middle;" > <s:text name="label.gnpiasperoffer" /> </th>
																				</tr>
																				<tr>
																					<th></th>
																					<th></th>
																					<th colspan="2">(Whichever is lower)</th>
																					<th colspan="2">(Whichever is higher)</th>
																					<th></th>	
																					<th ></th>
																				</tr>
																			</thead>
																			<tbody>
																			<s:iterator value="CoverList" var="list" status="stat">
																				<tr>
																				
																						<td>
																							<s:textfield name="coverSNoS[%{#stat.count-1}]" id="coverSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
																						</td>
																						<td>
																						<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="true"/>
																						<s:if test='0==(#stat.count-1)'>
																						<s:text name="(Main Class)" />
																						</s:if>
																						</td>
																						<td>
																						<s:textfield name="coverLimitAmount[%{#stat.count-1}]" id="coverLimitAmount[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
																						</td>
																						<td>
																						<s:textfield name="coverLimitPercent[%{#stat.count-1}]" id="coverLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled="true"/>
																						</td>
																						<td>
																						<s:textfield name="deductableLimitAmount[%{#stat.count-1}]" id="deductableLimitAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
																						</td>
																						<td>
																						<s:textfield name="deductableLimitPercent[%{#stat.count-1}]" id="deductableLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled="true"/>
																						</td>
																						<td>
																						<s:textfield name="egnpiAsPerOffSlide[%{#stat.count-1}]" id="egnpiAsPerOffSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled="true"/>
																						</td>
																						<td >
																						<s:textfield name="gnpiAsPOSlide[%{#stat.count-1}]" id="gnpiAsPOSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen1');" maxlength="30"   disabled="true"/>
																						</td>
																					</tr>
																					
																					</s:iterator>
																				</tbody>
																				</table>
																				<br class="clear"/>
																			
																</div>
															</div>
														</div>
								
										<hr class="clear"/>
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="label.ourassesment" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="ourAssessment" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>--%>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypml" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pml" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Yes" == pml'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypmlper" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pmlPercent" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.premiumbasis" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="premiumbasis" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AdjustmentRate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="adjRate" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.minimumRate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="minimumRate" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.maximumRate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="maximumRate" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.burningCostLF" />
											</div>
											<div class="tbox">
													<s:textfield readonly="true" name="burningCostLF" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.M&D_Installments" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="m_d_InstalmentNumber" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.paymentDueDays" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="paymentDuedays" cssClass="inputBox" />
											</div>
										</div>	
										<div class="textfield33">
												<div class="text">
													<s:text name="label.noOfRetroCessionaries" />
												</div>
												<div class="tbox">
													<s:textfield name="noRetroCess" cssClass="inputBox" cssStyle="text-align: right;" readonly="true" />													
												</div>
											</div>	
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ShareWritten" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="shareWritt" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ShareSigned" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="sharSign" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>	--%>									
										<hr class="clear"/>
										<table class="table table-bordered" width="100%">
											<thead>
											<tr>
												<th width="16.66%">100%</th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/> </th>
												
											</tr>
											</thead>
											<tbody>
											<s:if test="coverLimitXL !='0.00'">
											<tr>
												<td>
													<s:text name="label.coverLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXL" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXLDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												
											</tr>
											</s:if>
											<s:if test="deductLimitXL !='0.00'">
											<tr>
												<td>
													<s:text name="label.deductLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXL" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXLDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											</s:if>
											<tr>
												<td>
													<s:text name="RiskDetails.CoverLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOrig_CurDc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												
											</tr>
											<tr>
												<td>
													<s:text name="RiskDetails.Deductible" />
												</td>
												<td>
													<s:textfield readonly="true" name="deduc_hunPercent" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deduc_hunPercent_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												
											</tr>
											<tr>
												<td>
													<s:text name="EGNPI" />
												</td>
												<td>
													<s:textfield readonly="true" name="egnpiOffer" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="egnpiOfferDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<%-- <tr>
												<td>
													<s:text name="RiskDetails.SubjectPremium" />
												</td>
												<td>
													<s:textfield readonly="true" name="subPremium" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="subPremium_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td> <s:text name="RiskDetails.SubjectPremiumOurShare" /> </td>
												<td><s:textfield readonly="true" name="subPremiumOSOC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
												<td><s:textfield readonly="true" name="subPremiumOSDC" cssClass="inputBox" cssStyle="text-align: right;" /></td>
											</tr>--%>
											<tr>
												<td>
													<s:text name="RiskDetails.Epi" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<tr>
												<td>
													<s:text name="label.minPremium" />
												</td>
												<td>
													<s:textfield readonly="true" name="minPremium" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="minPremiumDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<tr>
												<td>
													<s:text name="RiskDetails.MDPremium" />
												</td>
												<td>
													<s:textfield readonly="true" name="m_dPremium" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="md_premium_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<tr>
											<td>
													<s:text name="RiskDetails.AnnuAgreDeduct" />
												</td>
												<td>
													<s:textfield readonly="true" name="anualAggregateDeduct" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="anualAggregateDeduct_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												</tr>
												
												<tr>
													<td>
														<s:text name="RiskDetails.AnnuAgreLaible" />
													</td>
													<td>
														<s:textfield readonly="true" name="anualAggregateLiability" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													<td>
														<s:textfield readonly="true" name="anualAggregateLiability_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													
													</tr>
													
													<s:if test="occ_limit!=null && occ_limit!='' && occ_limit!='0.00'">
													<tr>
													<td>
														<s:text name="label.occ.limit" />
													</td>
													<td>
														<s:textfield readonly="true" name="occ_limit" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													<td>
														<s:textfield readonly="true" name="occ_limitDc" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													
													</tr>
													</s:if>
											</tbody>
										</table>
										<%-- <table class="table table-bordered" width="100%">
											<thead>
											<tr>
												<th width="16.66%"></th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname" /></th>
												<th width="16.66%"></th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/></th>
											</tr>
											</thead>
											<tbody>
											<s:if test="coverLimitXL !='0.00'">
											<tr>
												<td>
													<s:text name="label.coverLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXL" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXLDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.coverLimitOS" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXLOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverLimitXLOurShareDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											</s:if>
											<s:if test="deductLimitXL !='0.00'">
											<tr>
												<td>
													<s:text name="label.deductLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXL" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXLDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.deductLimitOS" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXLOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductLimitXLOurShareDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>
											<tr>
												<td>
													<s:text name="Cover Limit 100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													
												</td>
												<td>
													<s:text name="Cover Limit Our Share" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="EPI 100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiAsPerOffer" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
												
												</td>
												<td>
													<s:text name="EPI Our Share" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiAsPerOffer" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiAsPerOffer_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>												
											</tr>
											<tr>
												<td>
													<s:text name="Deductible 100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="deduc_hunPercent" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
												
												</td>
												<td>
													<s:text name="M&D Premium Our Share" />
												</td>
												<td>
													<s:textfield readonly="true" name="md_premium_our_service" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="md_premium_our_service_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>												
											</tr>
											</tbody>
										</table>--%>
										<hr class="clear" />
										<s:hidden name="m_d_InstalmentNumber"/>
										<s:if test='!"0".equals(m_d_InstalmentNumber)'>
										<table width="100%" class="table table-bordered">
											<thead>
											<tr>
												<th> <s:text name="RiskDetails.InstallmentNo" /> </th>
												<th> <s:text name="RiskDetails.InstallmentDate" /> </th>
												<th> <s:text name="RiskDetails.M&DPremium" /> </th>
												<th> <s:text name="label.paymentDueDays" /> </th>
											</tr>
											</thead>
											<tbody>
											<tr>
												<s:iterator value="instalList" var="retroContract" status="stat">
											<tr>
												<td> <s:property value='%{#stat.count}'/> </td>
												<td> <s:textfield name="instalmentDateList[%{#stat.count-1}]" cssClass="inputBox" readonly="true" /> </td>
												<td> <s:textfield name="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" readonly="true" cssStyle="text-align: right;" /> </td>
												<td><s:textfield name="paymentDueDays[%{#stat.count-1}]" id="paymentDueDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"  readonly="true"/> </td>
											</tr>
											</s:iterator>
											</tr>
											</tbody>
										</table>
										</s:if>
										<hr class="clear"/>
										
										<s:if test='!"0".equals(noRetroCess)'>
										<table class="table table-bordered" width="100%">
											<thead>
											<tr>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.slNo" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.reinsurersName" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.broker" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.shareAcceptedPer" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.proposalStatus" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="riskDetails.shareSignedPer" /> </th>
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
											</tr>
											</s:iterator>
											</tbody>
										</table>
										</s:if>
										<hr class="clear" />
										<div class="panel panel-default">											
										<div class="panel-heading"> <s:text name="label.acquisitionCost" /> </div>
										<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.BroKerage" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="brokerage" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Tax" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="tax" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.othercost" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="othercost" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										
										</div>
										</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
												<div class="text">
													<s:text name="label.reinstatementpremium" />
												</div>
												<div class="tbox">													
													<s:radio name="reInstatementPremium" id="reInstatementPremium" list="#{'Y':'Yes','N':'No'}"  value="reInstatementPremium==null?'N':reInstatementPremium" disabled="true" />
																										
													
											</div>
											</div>
											<div class="textfield33" id="rsp" style="display:none">
												<a href="#" class="btn btn-info btn-rounded btn-xs"  style="font-size: 8px;"  onclick="getPopUpDetails('RSP','<s:property value="contractNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
											</div>
											<%-- <div class="textfield33">
												<div class="text">
													<s:text name="label.annualAggregateLiability" />
												</div>
												<div class="tbox">
													<s:textfield  cssClass="inputBox"   name="annualAggregateLiability" readonly="true"   />
												</div>											
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="RiskDetails.AnnualAggrDeduct" />
												</div>
												<div class="tbox">
													<s:textfield  cssClass="inputBox"   name="anualAggregateDeduct" readonly="true"   />
												</div>											
											</div>
											<s:if test="occ_limit!=null && occ_limit!='' && occ_limit!='0.00'">
											<div class="textfield33">
											<div class="text">
												</div>
												<div class="tbox">
												</div>	
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.occ.limit" />
												</div>
												<div class="tbox">
													<s:textfield readonly="true" name="occ_limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</div>											
											</div>
											</s:if>--%>
											
										<hr class="clear"/>
											<div class="textfield33">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'NO'}" name="crestaStatus" value="crestaStatus==null?'N':crestaStatus" disabled="true" ></s:radio>
												</div>
											</div>
											<div class="textfield33" id="cresta" style="display:none">
												<div class="text">
													<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="CrestapopUp()" style="font-size: 8px;"><s:text name="label.viewDetails" /></a>
													</div>
												</div>
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
										<div class="panel-heading"> &nbsp;&nbsp;&nbsp; </div>
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
										<div class="panel-heading"> <s:text name="All Remarks" /> </div>
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
										<s:if test="!endorsementDate.equals('') && !endorsementDate.equals(null)">
										<div class="textfield33">
											<div class="text">
												<s:if test='"Endorsement".equals(endorsmenttype)'>
													<s:text name="label.endorsementDate" />
												</s:if>
												<s:if test='"GNPI".equals(endorsmenttype)'>
													<s:text name="label.gnpiDate" />
												</s:if>
												<s:if test='"Rectification".equals(endorsmenttype)'>
													<s:text name="label.rectificationDate" />
												</s:if>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="endorsementDate" cssClass="inputBox" />
											</div>
										</div>
										</s:if>										
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
<s:hidden name="amendId" id="amendId"/>
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
			document.retroxolView.action ="commonListPortfolio?manufactureID="+productId+"&Department="+deptId;
		}else if(flag=='C') {
			document.retroxolView.action="InitPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}else if(flag=='H') {
			document.retroxolView.action="getHistoryPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}else if(flag=='R') {
			document.retroxolView.action="commonListPortfolio?manufactureID="+productId+"&flag=R";
		}else if(flag=='N') {
			document.retroxolView.action="commonListPortfolio?manufactureID="+productId+"&flag=N";
		}else if(flag=='RP') {
			document.retroxolView.action ="commonListPortfolio?manufactureID="+productId+"&flag=RP";
		}else if(flag=='RD') {
			document.retroxolView.action="InitPortfolio?flag="+flag+"&menuId=${session.menuId}";
		}
		document.retroxolView.submit();
	}
	
	function getPrint() {
		$(".btn").hide();
		window.print();
		$(".btn").show(); 
	}
	
	
	function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	var URL ="${pageContext.request.contextPath}/viewPopUpXol.action?mode=view&pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo;
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
funView('<s:property value="reInstatementPremium"/>');
function  funView(id){
			if(id=="Y"){
	     		document.getElementById('rsp').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('rsp').style.display = 'none';
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
function CrestapopUp(){
	var contractNo = document.getElementById("contNo").value;
	var proposalno = document.getElementById("proposal_no").value;
	var amendId = document.getElementById("amendId").value;
	var cur = document.getElementById("orginalCurrency").value;
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
GetStopLossType('<s:property value="businessType"/>');
function GetStopLossType(val){
if(val=='Stop Loss'){
	document.getElementById('stoploss').style.display='inline';
	document.getElementById('stoplossno').style.display='none';
}else if (val=='Cat XOL' ||val=='Risk XOL' ||val=='Combined Risk &amp; Cat XOL' ||val=='Aggregate XOL' || val =='Umbrella XL'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
 	
}else{
	document.getElementById('stoplossno').style.display='none';
	document.getElementById('stoploss').style.display='none';
}
}
</script>
</body>
</html>

