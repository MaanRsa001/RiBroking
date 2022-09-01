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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
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
<s:form name="facultativeView" id="facultativeView" action="FaculitivesDispatchAction" method="post" theme="simple">
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
										<s:text name="Heading.RISKDETAILSHEET" /> <s:text name="Heading.facultative" />
									</div>
									<div class="panel-body">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.proposalNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposal_no" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.contractno" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="contractNo" id="contractNo" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.departmentClass" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="departmentName" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.profitCentre" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="profitCenterCode" cssClass="inputBox"  />
											</div>
										</div>
										<div class="textfield33">
											
										</div>
										<div class="textfieldA25">
											<s:text name="label.subClass" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="subProfitCenter" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
										</div>
										
										
										<div class="textfield33">
											<div class="text">
											<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
												<s:text name="label.inwardType" />
											</s:if>
											<s:else>
												<s:text name="label.inwardType02" />
											</s:else>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="inwardType" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.underwriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="underwriter" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.uwCapacityUGX" />(<s:property value="shortname" />)
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="maxiumlimit" cssStyle="text-align: right;" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.branchLocation" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="policyBranch" cssClass="inputBox" />
											</div>
										</div>	
										<div class="textfield33">
											<div class="text">
												<s:text name="label.typebusiness" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="type" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											
										</div>
										<div class="textfieldA25">
											<s:text name="label.cedingCompany" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="1" name="cedingCompany" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
										</div>	
										
										<div class="textfieldA25">
											<s:text name="label.broker" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="1" name="broker" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
										</div>	
										<s:if test='!"Fac".equals(type)'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.layerNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="xollayerNo" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.inceptionDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="inceptionDate" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.expiryDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="expiryDate" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.uwYear" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="year" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.acceptanceDate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="accountDate" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.originalCurrency" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="originalCurrency" id="originalCurrency" cssClass="inputBox" cssStyle="width:30%;float:left;" />
												<s:textfield readonly="true" name="usCurrencyRate" cssClass="inputBox" cssStyle="width:65%;float:left;text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.retro" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="spRetro" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.noofRetroContracts" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="no_Insurer" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>
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
												<s:textarea rows="3" name="countryIncludedName" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
											
											</div>
										<div class="textfieldA25">
												Countries  Excluded
											</div>
											<div class="textfieldA75">
													<s:textarea rows="3" name="countryExcludedName" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
											</div>
										
										<hr class="clear"/>									 
										<div class="textfield33">
											<div class="text">
												<s:text name="label.insuredName" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="insuredName" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.interestCovered" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="interest" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.city" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="city" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.location" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="location" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.latitude" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="latitude" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.longitude" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="longitude" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.LOCIssued" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locIssued" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Y" == locIssued'>
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
										<%--<div class="textfield33">
											<div class="text">
												<s:text name="label.sIPML" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="sipml" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.pMLP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pml" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div> --%>
										<s:if test='"Fac".equals(type)'>
										
										
										<hr class="clear"/>
												<table class="footable table-overflow" width="100%" id="newgen1" >
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.subClass" />  </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Type" />  </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlhundredper" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverareLimitOC" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlper" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleOC" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverageDays" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductableDays" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.premiumRate" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gWPIOC100" /> </th>
																<th class="8%" style="text-align: center; vertical-align: middle;"> Remarks </th>
																
															</tr>
														</thead>
														
														<tbody>
														<s:iterator value="coverdeductableList" var="list" status="stat">
															<tr>
																	<td>
												 					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
												 					</td>
																	<td>
																	
																	<s:if test='0==(#stat.count-1)'>
																	<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}" disabled="true"/>
																	<s:hidden name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})" />
																	</s:else>
																	</td>
																	
																	<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
																	<%--<s:if test='0==(#stat.count-1)'>
																	<s:select list="subProfit_center" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false"/>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"   disabled="true"/>
																	</s:else>--%>
                                                                    <s:textfield name="coversubdepartId[%{#stat.count-1}]" id="coversubdepartId%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"   maxlength="30"  disabled="true"  />
																	</td>
																	<td>
																	<s:select list="coverTypelist" listValue="DETAIL_NAME" listKey="TYPE" name="coverTypeId[%{#stat.count-1}]" id="coverTypeId%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="getPmlText()"  disabled="true"/>
																	</td>
																	
																	<td>
                                                                    <s:textfield name="pmlHundredPer[%{#stat.count-1}]" id="pmlHundredPer%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getdeductableCal();" maxlength="30"  disabled="true"  />
                                                                    
                                                                    </td>
																	 <td>
																	<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getdeductableCal();getCoverGWPI(%{#stat.count-1});getTotalValue();getTotalEGNPI();getEgnpiCal()" maxlength="30"   disabled="true"/>
																	
																	</td>
																	<td>
																			<s:textfield name="pmlPerList[%{#stat.count-1}]" id="pmlPerList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="30"   onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" disabled="true"/>
																	</td>
																	<td>
																	<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  disabled="true" />
																	</td>
																	<td>
																	<s:textfield name="coverageDays[%{#stat.count-1}]" id="coverageDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);" maxlength="30"  readonly="true"/>
																	</td>
																	<td><s:textfield name="deductableDays[%{#stat.count-1}]" id="deductableDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);" maxlength="30"  readonly="true"/></td>
																	<td><s:textfield name="premiumRateList[%{#stat.count-1}]" id="premiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);getCoverGWPI(%{#stat.count-1});getTotalEGNPI();" maxlength="30"  readonly="true"/></td>
																	<td><s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();getTotalEGNPI();" maxlength="30"  readonly="true"/></td>
																	<td>
																	<s:textarea name="coverRemark[%{#stat.count-1}]" id="coverRemark[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " readonly="true"/>
																	</td>
																	<s:hidden name="loopcount" id="loopcount" ></s:hidden>
																</tr>
																</s:iterator>
																<s:hidden name="totalCoverage" id="totalCoverage"></s:hidden>
																<s:hidden name="totalGWPI" id="totalGWPI"></s:hidden>
																<s:hidden name="totalDeductible" id="totalDeductible"></s:hidden>
																<%-- <tr id="gnpi2" style="display:none;" >
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td>Total</td>
																<td>Total</td>
																</tr>--%>
																<s:hidden name="loopcount" id="loopcount" ></s:hidden>
															</tbody>
															
															
															</table>
															</s:if>
															<s:else>
															<hr class="clear"/>
															<table class="footable" width="100%" id="xolnewgen">
														<thead>
															<tr>
																<th width="2%"> <s:text name="Serial No" />  </th>
																<th class="tableColWidth"> <s:text name="label.Class" />  </th>
																<th class="tableColWidth"> <s:text name="label.subClass" />  </th>
																<th class="tableColWidth"> <s:text name="label.coverLimitOC" /> </th>
																<th class="tableColWidth"> <s:text name="label.deductibleOC" /> </th>
																<th class="tableColWidth"> <s:text name="label.premiumRate" /> </th>
																<th class="tableColWidth"> <s:text name="label.gWPIOC100" /> </th>
																
															</tr>
														</thead>
														
														<tbody>
														<s:iterator value="xolCoverdeductableList" var="list" status="stat">
															<tr>
																	<td>
												 					<s:textfield name="xolcoverSNo[%{#stat.count-1}]" id="xolcoverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
												 					</td>
																	<td>
																	
																	<s:if test='0==(#stat.count-1)'>
																	<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}" />
																	<s:hidden name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjaxXOlCover(this.value,'xolcoverdepartid%{#stat.count-1}',%{#stat.count-1})" />
																	</s:else>
																	</td>
																	
																	<td id="xolcoverdepartid<s:property value='%{#stat.count-1}'/>">
																<%-- 	<s:if test='0==(#stat.count-1)'>
																	<s:select list="subProfit_center" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false"/>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" onchange="getAllValue(%{#stat.count-1})" />
																	</s:else>--%>
																	<s:textfield name="xolcoversubdepartId[%{#stat.count-1}]" id="xolcoversubdepartId%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:left;"   maxlength="30"  disabled="true"  />
																	</td>
																	
																	
																	
																	 <td>
																	<s:textfield name="xolcoverLimitOC[%{#stat.count-1}]" id="xolcoverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getxolCoverGWPI(%{#stat.count-1});getTotalxolValue();" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}' readonly="true" />
																	</td>
																	
																	<td>
																	<s:textfield name="xoldeductableLimitOC[%{#stat.count-1}]" id="xoldeductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalDeductable();" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' readonly="true"/>
																	</td>
																	
																	<td><s:textfield name="xolpremiumRateList[%{#stat.count-1}]" id="xolpremiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);allowOneDot(this);hundredCheck(this.id,this.value);Itnegative(this.id,this.value);getxolCoverGWPI(%{#stat.count-1});" maxlength="30"  readonly="true"/></td>
																	<td><s:textfield name="xolgwpiOC[%{#stat.count-1}]" id="xolgwpiOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  readonly="true"/></td>
																	
																	<s:hidden name="xolcoverpreVal[%{#stat.count-1}]" id="xolcoverpreVal%{#stat.count-1}"/>
																</tr>
																</s:iterator>
																
																<s:hidden name="xolLoopcount" id="xolLoopcount" ></s:hidden>
															</tbody>
															
															</table>
															
											</s:else>
															<hr class="clear"/>
										<s:if test='"4".equals(departmentId) '>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.modeOfTransport" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="modeOfTransport" cssClass="inputBox" />
											</div>
										</div>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="Vessel Name" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="vesselName" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.vesselAge" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="vesselAge" cssClass="inputBox" />
											</div>
										</div>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="label.vesseltonege" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="vessaletonnage" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="Cedant's Retention Type" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedRetenType" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="Cedant's Retention" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedantsRet" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="label.premiumRate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="premiumrate" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>--%>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.noofInstallments" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="noOfInst" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.paymentDueDays" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="receiptofPayment" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.shareWrittenP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="shWt" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.shareSignedP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="shSd" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="Mapped Contract No" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="contractListVal" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<s:if test='"None"!=contractListVal && ""!=contractListVal && "0"!=contractListVal && null != contractListVal'>
										<div class="textfield33">
										<input type="button" class="btn btn-sm btn-info" value="View" id="view" style="cursor: pointer;" onclick="getViewContractDetails('<s:property value="mappingProposal"/>','<s:property value="mapingAmendId"/>')" />
										</div>
										</s:if>
										<hr class="clear"/>
										<s:if test="contractListVal!=null && contractListVal!=''">
							
											<div class="textfield33" >
												<div class="text">
													<s:text name="Data Matching Contract Number" />
												</div>
												<div class="tbox">
													<s:textfield readonly="true" name="contractListVal" cssClass="inputBox" />
												</div>
											</div>
											<hr class="clear"/>
										</s:if>
										<table class="footable" width="100%">
											<thead>
											<tr>
												<th width="16.66%">100%</th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname" /></th>
												<th width="16.66%">Our Share</th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname" /></th>
											</tr>
											</thead>
											<tbody>
											<s:if test='"1".equals(departmentId)'>
											<s:if test='"Fac".equals(type)'>
											<tr>
												<td>
												<s:text name="label.si100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumInsured" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.si100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumInsuredOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumOrginalUsd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											
											</s:if>
											<s:else>
											<tr>
												<td>
													<s:text name="label.deductible" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductible" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.deductible" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="label.deductibleFacXolView" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXol" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXolDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.deductibleFacXolView" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverlimitOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverlimitOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											</s:else>
											<tr>
												<td>
													<s:text name="label.gwpi100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpi" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiUsd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.gwpi100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											</s:if>
											<s:else>
											<s:if test='"4".equals(departmentId) '>
											<tr>
												<td>
													<s:text name="label.limitpervessel" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselOC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td></td><td></td><td></td>
											</tr>
											<tr>
												
												<td>
													<s:text name="label.limitperlocation" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationOC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td></td><td></td><td></td>
											</tr>
											</s:if>
											<%--<tr>
												<td>
												<s:if test='"4".equals(DepartmentId) && !"Fac".equals(type)'>
													<s:text name="label.turnsIOC100%" />
												</s:if>
												<s:else>
												<s:text name="label.si100%" />
												</s:else>
												</td>
												<td>
													<s:textfield readonly="true" name="sumInsured" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
												<s:if test='"4".equals(DepartmentId) && !"Fac".equals(type)'>
													<s:text name="Facultative.TRSIOS" />
													</s:if>
													<s:else>
													<s:text name="Facultative.SIOS" />
													</s:else>
												</td>
												<td>
													<s:textfield readonly="true" name="sumInsuredOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="sumOrginalUsd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr> --%>
											<tr>
												<td>
													<s:text name="label.deductible" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductible" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.deductible" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											
											<tr>
												<td>
													<s:text name="label.deductibleFacXolView" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXol" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXolDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.deductibleFacXolView" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverlimitOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="coverlimitOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											
											<tr>
												<td>
													<s:text name="label.gwpi100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpi" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiUsd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.gwpi100View" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="gwpiOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											<s:if test='"RI01".equals(#session.SOURCE_CODE)' >
											<tr>
												<td>													
													<s:text name="label.tpl100%" />													
												</td>
												<td>
													<s:textfield readonly="true" name="tpl" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="tplusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="Facultative.TPLOS" />
												</td>
												<td>													
													<s:textfield readonly="true" name="tplOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>													
													<s:textfield readonly="true" name="tplOurshareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr> 
											</s:if>
											<s:if test='"RI02".equals(#session.SOURCE_CODE) && "4".equals(#session.DepartmentId) && "Fac".equals(type)'>
											<tr>
												<td>
													<s:text name="label.psl100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pslOC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pslusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.psl100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pslOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pslOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="label.pll100OC%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pllOC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pllusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.pll100OC%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pllOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pllOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											<tr>
												<td>
													<s:text name="label.pbl100OC%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pblOC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pblusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.pbl100OC%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pblOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pblOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											
											<tr>
												<td>
													<s:text name="label.pml100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="pmll" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pmlusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.pml100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="PmlOCOurShare" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="pmlOurShareusd" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr></s:if>
											</s:else>
											<!--<tr>
												<td>
													Deductible
												</td>
												<td>
													<s:textfield readonly="true" name="deductible" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:text name="label.coverlimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXol" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="deductibleFacXolDC" cssClass="inputBox" cssStyle="text-align:right;" />
												</td>
											</tr>
											-->
											</tbody>
										</table>
										<hr class="clear"/>
										<s:if test='!"0".equals(noOfInst)'>
										<s:set name="instalmnt" value="%{noOfInst == null?0:noOfInst}" />
										<table class="footable" width="100%">
											<thead>
											<tr>
												<th width="25%">
													<s:text name="label.installmentNo" />
												</th>
												<th width="25%">
													<s:text name="label.installmentDate" />
												</th>
												<th width="25%">
													<s:text name="label.installmentPremium" />
												</th>
												<th width="25%">
													<s:text name="label.paymentDueDays" />
												</th>
											</tr>
											</thead>
											<tbody>
											<s:iterator value="instalList" var="retroContract" status="stat">
											<tr>
												<td>
													<s:property value='%{#stat.count}'/>
												</td>
												<td>
													<s:textfield name="instalmentDateList[%{#stat.count-1}]" cssClass="inputBox" readonly="true" />
												</td>
												<td>
													<s:textfield name="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" />
												</td>
												<td>
													<s:textfield name="paymentDueDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" />
												</td>
											</tr>
											</s:iterator>
											</tbody>
										</table>
										<hr class="clear"/>
										</s:if>
										<s:if test='"7".equals(#session.DepartmentId)|| "9".equals(#session.DepartmentId)'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.riskGrade" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="riskGrade" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.occupationCode" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="occCode" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.fireProtection" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="fireProt" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.mbIndicator" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="mbind" cssClass="inputBox" />
											</div>
										</div>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="label.mLop" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="mlopYN" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.aLop" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="alopYN" cssClass="inputBox" />
											</div>
										</div>
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="label.categoryZone" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="categoryZone" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>--%>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.eqwsIndicator" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="eqwsInd" cssClass="inputBox" />
											</div>
										</div>
										<hr class="clear"/>
										</s:if>
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
													<a  href="#" onclick="CrestapopUp()" class="btn btn-info btn-rounded btn-xs" style="font-size: 8px;"><s:text name="label.viewDetails" /></a>
													</div>
												</div>
												
										<hr class="clear"/>
										<s:if test='!"Fac".equals(type)'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.commissionP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="commn" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.brokerageP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="brokerage" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.taxP" />
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
										<div class="textfield33">
											<div class="text">
												<s:text name="label.acqCostP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqCostPer" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="facultative.acqCostAmt" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqCost" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="Facultative.AcqCostUGX" /><s:property value="shortname" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="accusd" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>	
										<div class="textfield33">
											<div class="text">
												<s:text name="facultative.bonuspercentage" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqBonusName"  cssClass="inputBox" cssStyle="text-align:right;" />
												<s:hidden name="acqBonus" id="acqBonus"/>
											</div>
										</div>	
										
										<div class="textfield33" id="ncb" style="display:none">
											<div class="text">
												<s:text name="facultative.noclaimbonuspercentage" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqBonusPercentage" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>	
										<div class="textfield33" id="lcb" style="display:none">
												<div class="text">
													<a href="#" class="btn btn-info btn-rounded btn-xs" style="font-size: 8px;"  onclick="getPopUpDetails('<s:property value="contractNo"/>','<s:property value="endorsmentno"/>','<s:property value="proposalNo"/>')"><s:text name="label.viewDetails" /></a>
												</div>
										</div>
										<hr class="clear"/>
										
										<s:if test='!"0".equals(no_Insurer)'>
										<div class="panel panel-default">											
											<div class="panel-heading"> <s:text name="label.retroContracts" /> </div>
											<div class="panel-body">
												<div class="boxcontent">
										 <div class="textfield33">
											<div class="text">
												<s:text name="label.retentionPer" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="retper" cssClass="inputBox" cssStyle="text-align:right;" value="%{retper == null ? '':retper}" onkeyup="checkDecimals(this)" />
											</div>
										</div>
										<hr class="clear"/>
										<table class="footable" width="100%">
											<thead>
											<tr>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.InsuraerNo" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.retroType" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="RiskDetails.U/WYear" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.CeddingCompany" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.Retro" /> </th>
											</tr>
											</thead>
											<tbody>
											<!-- Iterator starts Here -->
											<s:iterator value="uwYearValList" status="stat">
												<tr>
													<td>
													<s:property value='%{#stat.count}'/>
													</td>
													<td>
														<s:radio list="#{'SR':'Specific','TR':'Treaty'}" name="retroTypeValList[%{#stat.count-1}]" id="eqThreat[%{#stat.count-1}]" onclick="setRetroType(this.value,'uwYearTd%{#stat.count-1}','%{#stat.count-1}'),setRetroContract('','','cedingCompany%{#stat.count-1}','%{#stat.count-1}')" disabled="true" />
													</td>
													<td id="uwYearTd<s:property value='%{#stat.count-1}'/>">
														<s:textfield name="uwYearValList[%{#stat.count-1}]" cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
													<td id="cedingCompany<s:property value='%{#stat.count-1}'/>">
														<s:textfield name="cedingCompanyValList[%{#stat.count-1}]" cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
													<td>
														<s:textfield name="retroPercentage[%{#stat.count-1}]" id="retroPercentage[%{#stat.count-1}]" onkeyup="checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox"  readonly="true"/>
													</td>
												</tr>
											</s:iterator>
											<!-- Iterator ends Here -->
											</tbody>
										</table>
										</div>
										</div>
										</div>
										
										<hr class="clear"/>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.capacityUtilizationP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cu" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.capacityUtilizationReason" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cuRsn" cssClass="inputBox" />
											</div>											
										</div>
										
										<hr class="clear"/>
										<div class="panel panel-default">											
										<div class="panel-heading"> Loss Record </div>
										<div class="panel-body">
											<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.lossRecord" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="lossRecord" cssClass="inputBox" />
											</div>											
										</div>
										<s:if test='"Y" == lossRecord'>
												<table class="footable table-overflow" width="100%" id="newgen1" >
																<thead>
																	<tr>
																		<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.year" />  </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossno" />  </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.insurName" />  </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.incDate" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.ExpDate" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.dateOdloss" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.causeofloss" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.insuredclaim" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.premium" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossratio" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.leader" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.itire" /> </th>
																	</tr>
																</thead>
																<tbody>
																<s:iterator value="lossRecordList" var="list" status="stat">
																	<tr>
																			<td>
																				<s:textfield name="lossSNoS[%{#stat.count-1}]" id="lossSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple" disabled="true"/> 
																			</td>
																			<td>
																			<s:textfield name="lossYear[%{#stat.count-1}]" id="lossYear%{#stat.count-1}"  cssClass="inputBox"  maxlength="30" />
																			</td>
																			<td>
																			<s:textfield name="lossNo[%{#stat.count-1}]" id="lossNo%{#stat.count-1}"  cssClass="inputBox"   maxlength="30" disabled="true"/>
																			</td>
																			<td>
																			<s:textfield name="lossinsuredName[%{#stat.count-1}]" id="lossinsuredName%{#stat.count-1}" cssClass="inputBox"   maxlength="100"  disabled="true" />
																			</td>
																			<td>
																			<s:textfield name="lossInceptionDate[%{#stat.count-1}]" id="lossInceptionDate%{#stat.count-1}"  cssClass="inputBox datepicker accumDate"   onkeyup="validateSpecialChars(this)" disabled="true"/>
																				
																			</td>
																			<td>
																			<s:textfield name="lossExpiryDate[%{#stat.count-1}]" id="lossExpiryDate%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)" disabled="true"/>
																			
																			</td>
																			<td>
																			<s:textfield name="lossDateOfLoss[%{#stat.count-1}]" id="lossDateOfLoss%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)" disabled="true"/>
																			</td>
																			<td >
																			<s:textfield name="lossCauseOfLoss[%{#stat.count-1}]" id="lossCauseOfLoss%{#stat.count-1}" cssClass="inputBox" maxlength="100"  disabled="true" />
																			</td>
																			<td >
																			<s:textfield name="lossInsuredClaim[%{#stat.count-1}]" id="lossInsuredClaim%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossPremium[%{#stat.count-1}]" id="lossPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossRatio[%{#stat.count-1}]" id="lossRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossLeader[%{#stat.count-1}]" id="lossLeader%{#stat.count-1}" cssClass="inputBox"  maxlength="30"   disabled="true"/>
																			</td>
																			<td >
																			<s:textfield name="lossITIReShare[%{#stat.count-1}]" id="lossITIReShare%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   disabled="true"/>
																			</td>
																			
																		</tr>
																		
																		</s:iterator>
																		<s:hidden name="lossCount" id="lossCount" ></s:hidden>
																	</tbody>
														</table>
														<div  id="calculateresult">
														</div>
												<br class="clear"/>
												
										</s:if>
										<%-- <div class="textfield33">
											<div class="text">
												<s:text name="label.refertoHeadOffice" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="reftoHO" cssClass="inputBox" />
											</div>											
										</div>--%>
										</div>
												</div>
												</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.leadUnderWriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.leadUnderwritterCountry" />
												</div>
												<div class="tbox">
													<s:textfield readonly="true" name="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align:right;" />
												</div>
											</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.leadUnderwritterShareP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<hr class="clear"/>										
										<div class="panel panel-default">											
										<div class="panel-heading">&nbsp;&nbsp;&nbsp;</div>
										<div class="panel-body">
											<div class="boxcontent">
										<div class="textfieldA25">
											<s:text name="Underwriter's Recommendations" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="uwRecommendation" cssClass="inputBoxA" cssStyle="width: 90%; resize: vertical; " readonly="true" />
										</div>
										<div class="textfieldA25">
															<s:text name="label.exclusion" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="exclusion" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										<div class="textfieldA25">
															<s:text name="label.otherAcceptance" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="othAccep" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										</div>
										</div>
										</div>
										<hr class="clear"/>
										<div class="panel panel-default">											
									<div class="panel-heading"> <s:text name="All Remarks" />  </div>
									<div class="panel-body">
										<div class="boxcontent">
										<table class="footable" width="100%">
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
										
										
										<s:if test='"7".equals(#session.DepartmentId)|| "9".equals(#session.DepartmentId)'>
										<div class="textfieldA25">
											<s:text name="label.riskDetail" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="riskDetail" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										<hr class="clear"/>
										<div class="textfieldA25">
											<s:text name="label.policyScope" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="scope" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>
										<hr class="clear"/>
										</s:if>
										
										<div class="textfield33">
											<div class="text">
												<s:text name="label.proposalStatus" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="status" cssClass="inputBox" />
											</div>											
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
							<div class="boxcontent" align="center">
								<s:if test='!"PR".equals(Flag)'>
									<input type="button" id="back" value="Back" class="btn btn-sm btn-danger" onclick="destroyPopUps();goBack('%{session.mfrid}','%{session.DepartmentId}','<s:property value="Flag"/>')" /> &nbsp;&nbsp;&nbsp;
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
<s:hidden name="proposalNo" id="proposalNo" value="%{proposal_no}" />
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
			document.facultativeView.action ="commonListPortfolio?manufactureID="+productId+"&Department="+deptId;
		}else if(flag=='C') {
			document.facultativeView.action="InitPortfolio?flag=C";
		}else if(flag=='H') {
			document.facultativeView.action="getHistoryPortfolio";
		}else if(flag=='R') {
			document.facultativeView.action="commonListPortfolio?manufactureID="+productId+"&flag=R";
		}else if(flag=='N') {
			document.facultativeView.action="commonListPortfolio?manufactureID="+productId+"&flag=N";
		}else if(flag=='RP') {
			document.facultativeView.action ="commonListPortfolio?manufactureID="+productId+"&flag=RP";
		}else if(flag=='RD') {
			document.facultativeView.action="InitPortfolio?flag=RD";
		}else if(flag=='EC') {
			document.facultativeView.action="InitPortfolio?flag=EC";
		}
		document.facultativeView.submit();
	}
	function getPrint()
	{
		$(".btn").hide();
		window.print();
		$(".btn").show();
	}
	
funView('<s:property value="acqBonus"/>');
function  funView(id){
			if(id=="LCB"){
	     		document.getElementById('lcb').style.display = 'block';
	     		document.getElementById('ncb').style.display = 'none';
	       	} 
	       	else if(id=="NCB"){
	       	 	document.getElementById('lcb').style.display = 'none';
	       	 	document.getElementById('ncb').style.display = 'block';
	       	}
	       	}
function getPopUpDetails(contractNo,endorsmentno,proposalNo){
		//var URL ="${pageContext.request.contextPath}/viewBonusPopUpFacultative.action?proposalNo="+proposalNo+"&endorsmentno="+endorsmentno+"&contractNo="+contractNo+"&mode=view";
		var acqBonus = document.facultativeView.acqBonus.value;
		var URL ="${pageContext.request.contextPath}/viewBonusPopUpXol.action?acqBonus="+acqBonus+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&layerNo=0&mode=view";		
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
toggleCrestapopUp('<s:property value="crestaStatus"/>');
function toggleCrestapopUp(value){
	if(value=='Y'){
	document.getElementById('cresta').style.display='block';
	}else if(value=='N'){
	document.getElementById('cresta').style.display='none';
	}
}

function CrestapopUp(){
	var contractNo = document.getElementById("contractNo").value;
	var proposalno = document.getElementById("proposalNo").value;
	var amendId = '<s:property value="endorsmentno"/>';
	var cur = document.getElementById("originalCurrency").value;
	
		var URL ='${pageContext.request.contextPath}/crestaPopUpRiskDetails.action?mode=view&contractNo='+contractNo+'&proposal_no='+proposalno+'&amendId='+amendId+'&orginalCurrency='+cur;
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
if('Y'=='<s:property value="lossRecord"/>'){
Calculate();
}
function Calculate(){
postFormRequest('${pageContext.request.contextPath}/calculateValueFacultative.action?dropDown=calculate', "calculateresult", "facultativeView");
}
function postFormRequest(strUrl, id, formId) {
    $.ajax({
		url : strUrl,
		type : "POST",
		data : $("#" + formId).serialize(),
		error : function() {
			$('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
		},
		success : function(data) {
			$('#' + id).html(data);
		},
		beforeSend : function() {
			$('#loading').show();
			$('.ajaxLoader').show();
		},
		complete : function() {
			$('#loading').hide();
			$('.ajaxLoader').hide();
		}
	});
}
var count=document.getElementById('loopcount').value;
<%--$(document).ready(function() {
 var count=document.getElementById('loopcount').value;
       $('#coversubdepartId').html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		onChange: function(element, checked) {
		          var val = $("#coversubdepartId"+count).val();
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!='' ){
		          $("#coversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#coversubdepartId"+count).val(val);
		           $("#coversubdepartId"+count).multiselect("refresh");
		           document.getElementById("coverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#coversubdepartId"+count).multiselect('clearSelection');
		          	$("#coversubdepartId"+count).val('ALL');
		          	 $("#coversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("coverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });         
  var cover ='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
  <s:if test='cover!=null && !"".equals(cover)'>
 		var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
	</s:if>  
	
});--%>
function getViewContractDetails(proposalNo,amendId) {
		var URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
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
			mtbChildWin[mtbWinCound] = strOpen;mtbWinCound++;
			strOpen.focus();
			return false;
	}
//alert('<s:property value="coversubdepartId[0]"/>'+'out');
 <%--<s:iterator value="coverdeductableList"  status="stat">   
  var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
</s:iterator>	--%>
</script>
</body>
</html>