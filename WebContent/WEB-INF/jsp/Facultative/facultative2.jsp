<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    <link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	 $(function() {
	    $('.accumDate').datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$('.accumDate1').datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#endorsementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( ".instalmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>
<style type="text/css">
 .tableColWidth {
 	min-width: 200px;
 	max-width: 750px;
 	width: 200px;
 	white-space: normal;
 }
 
  .tableColWidth1 {
 	min-width: 50px;
 	max-width: 750px;
 	width: 450px;
 	white-space: normal;
 }
 
 .tableColNoWrap {
 	min-width: 100px;
 	max-width: 750px;
 	width: 100px;
 	white-space: nowrap;
 }
 .table-overflow{
    overflow: scrollX;
 }
 </style>													
</head>
	<body onunload="destroyPopUps();" onload="GetPmlValidation('<s:property value="sipml"/>'),calacccost(),callculateCU('<s:property value="sumusd" />','<s:property value="maxiumlimit" />');" >
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1"
					style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8" >
							<s:form id="facultative2" name="facultative2" theme="simple" action="" method="post" enctype="multipart/form-data" autocomplete="off">
								<%--<s:set name="line" 	value="request.getAttribute('NoInsurar')==null?0:Integer.parseInt(request.getAttribute('NoInsurar').toString())" /> --%>
								<s:set name="instalmnt" value="request.getAttribute('NoOfInstallments')==null?0:Integer.parseInt(request.getAttribute('NoOfInstallments').toString())" />
								<div class="table2">
									<div class="tablerow">
										<span style="color: red;"><s:actionerror />
										</span>
									</div>
									<div class="tablerow" align="center">
										<s:if test='"2".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeAviation" />
										</s:if>
										<s:elseif test='"4".equals(#session.DepartmentId)'>
											<s:text name="label.facMarine" />
										</s:elseif>
										<s:elseif test='"7".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeEngineering" />
										</s:elseif>
										<s:elseif test='"8".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeBonds" />
										</s:elseif>
										<s:elseif test='"9".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeFire" />
										</s:elseif>
										<s:elseif test='"10".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeMarineHull" />
										</s:elseif>
										<s:elseif test='"11".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeMisce" />
										</s:elseif>
										<s:elseif test='"12".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeMotor" />
										</s:elseif>
										<s:elseif test='"13".equals(#session.DepartmentId)'>
											<s:text name="label.facultativePolitical" />
										</s:elseif>
										<s:elseif test='"14".equals(#session.DepartmentId)'>
											<s:text name="label.facultativeLife" />
										</s:elseif>
									</div>
									<s:if test="contractNo != '' && contractNo != null">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body">
														<div class="boxcontent">
															<s:if test="endorsmentno != '' && contractNo != null && endorsmentno !='0'">
																
																
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementType" />
																</div>
																<div class="tbox txtB">
																	<%-- --<s:select list="endosTypelist" name="endorsmenttype" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME"  disabled="true"/>--%>
																<s:property value="endorsmenttype"/>
																<s:hidden name="endorsmenttype" id="endorsmenttype"/>
																</div>
															</div>
															</s:if>
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementNo" />
																</div>
																<div class="tbox txtB">
																<s:property value="endorsmentno"/>
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<div class="tablerow">
										<s:hidden name="nr" value="0" />
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-body">
													<div class="boxcontent">
														<table width="100%" class="footable">
															<tr>
																<td width="16.66%" class="txtB">
																	<s:text name="label.proposalNo" />
																</td>
																<td width="16.66%">
																	<s:property value="proposalNo" />
																</td>
																<td width="16.66%" class="txtB">
																	<s:text name="label.department" />
																</td>
																<td width="16.66%">
																	<s:property value="departClass"/>
																</td>
																<td width="16.66%" class="txtB">
																	<s:text name="label.subClass" />
																</td>
																<td width="16.66%">
																	<s:property value="subProfitCenter" />
																</td>
															</tr>
															<tr>
																<td width="16.66%" class="txtB">
																	<s:text name="label.cedingCompany" />
																</td>
																<td width="16.66%">
																	<s:property value="cedingCompany" />
																</td>
																<td width="16.66%" class="txtB">
																	<s:text name="label.broker" />
																</td>
																<td width="16.66%">
																	<s:property value="broker" />
																</td>
																<td width="16.66%" class="txtB">
																	<s:text name="label.uwYear" />
																</td>
																<td width="16.66%">
																	<s:property value="year" />
																	<s:hidden name="year" />
																</td>
															</tr>
															<tr>
																<td width="16.66%" class="txtB">
																	<s:text name="label.insuredName" />
																</td>
																<td width="16.66%">
																	<s:property value="insuredName" />
																</td>
																<td width="16.66%" class="txtB"></td>
																<td width="16.66%"></td>
																<td width="16.66%" class="txtB"></td>
																<td width="16.66%"></td>
															</tr>
														</table>
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>

										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<table width="100%" class="footable">
															<thead>
																<tr>
																	<th width="50%"></th>
																	<th width="25%" style="text-align: center; vertical-align: middle;">
																		<s:text name="label.ourCurrency" />
																	</th>
																	<th width="25%" style="text-align: center; vertical-align: middle;">
																	<s:property value="shortname" />
																	</th>
																</tr>
															</thead>
															<tbody>
															<s:if test="type == 2">
																<tr>
																	<td>
																		<s:text name="label.deductibleOurShare" />
																	</td>
																	<td align="right">
																		<s:property value="deductibleOSOC" />
																		<s:hidden name="deductibleOurShare" />
																		<s:hidden name="deductibleOSOC" />
																	</td>
																	<td align="right">
																		<s:property value="deductibleOSDC" />
																		<s:hidden name="deductibleOSDC" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<s:text name="label.coverlimitOurShare" />
																	</td>
																	<td align="right">
																		<s:property value="coverlimitOSOC" />
																		<s:hidden name="coverlimitOurShare" />
																		<s:hidden name="coverlimitOSOC" />
																	</td>
																	<td align="right">
																		<s:property value="coverlimitOSDC" />
																		<s:hidden name="coverlimitOSDC" />
																	</td>
																</tr>
																</s:if>
																<s:if
																	test="sumInsuredOurShare != '' && sumInsuredOurShare != null && type==1">
																	<tr>
																		<td>
																			<s:text name="label.siOurShare" />
																		</td>
																		<td align="right">
																			<s:property value="siOSViewOC" />
																			<s:hidden name="siOSViewOC" />
																		</td>
																		<td align="right">
																			<s:property value="siOSViewDC" />
																			<s:hidden name="siOSViewDC" />
																		</td>
																	</tr>
																</s:if>
																<s:hidden name="sumInsuredOurShare" id="sumInsuredOurShare"/>
																<%--<s:if test="pmlOurShare != '' && pmlOurShare != null">
																	<tr>
																		<td>
																			<s:text name="label.pmlOurShare" />
																		</td>
																		<td align="right">
																			<s:property value="pmlOSViewOC" />
																			<s:hidden name="pmlOSViewOC" />
																		</td>
																		<td align="right">
																			<s:property value="pmlOSViewDC" />
																			<s:hidden name="pmlOSViewDC" />
																		</td>
																	</tr>
																</s:if>
																<s:hidden name="pmlOurShare" id="pmlOurShare" />--%>
																<tr>
																	<td>
																		<s:text name="label.gwpiOurShare" />
																	</td>
																	<td align="right">
																		<s:property value="gwpiOSViewOC" />
																		<s:hidden name="gwpiOurShare" />
																		<s:hidden name="gwpiOSViewOC" />
																	</td>
																	<td align="right">
																		<s:property value="gwpiOSViewDC" />
																		<s:hidden name="gwpiOSViewDC" />
																	</td>
																</tr>
															<%-- 	<s:if test="DepartmentId != 7">
																	<tr>
																		<td>
																			<s:text name="label.tplOurShare" />
																		</td>
																		<td align="right">
																			<s:property value="tplOSViewOC" />
																			<s:hidden name="tplOSViewOC" />
																		</td>
																		<td align="right">
																			<s:property value="tplOSViewDC" />
																			<s:hidden name="tplOSViewDC" />
																		</td>
																	</tr>
																<%--</s:if>--%>
																
															</tbody>
														</table>
														<s:hidden name="xolOSViewOC" />
														<s:hidden name="xolOSViewDC" />
														<s:hidden name="tplOurShare" />
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>
										<s:if test='!"0".equals(noOfInst)'>
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<table width="100%" class="footable">
															<thead>
																<tr>
																	<th width="25%" style="text-align: center; vertical-align: middle;">
																		<s:text name="label.installmentNo" />
																	</th>
																	<th width="25%" style="text-align: center; vertical-align: middle;">
																		<s:text name="label.installmentDate" />
																	</th>
																	<th width="25%" style="text-align: center; vertical-align: middle;">
																		<s:text name="label.installmentPremium" />
																	</th>
																	<th width="25%" style="text-align: center; vertical-align: middle;"> <s:text name="label.paymentDueDays" /> </th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="instalList" var="retroContract" status="stat">
																<tr>
																	<td style="text-align: center;">
																		<s:property value='%{#stat.count}'/>
																	</td>
																	<td style="text-align: center;">
																		<!--  <div class="inputAppend">
																			<sj:datepicker name="instalmentDateList[%{#stat.count-1}]" id="instalmentDateList%{#stat.count-1}" displayFormat="dd/mm/yy"  cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this);" />
																		</div>-->
																		
																	<s:textfield name="instalmentDateList[%{#stat.count-1}]" id="instalmentDateList%{#stat.count-1}"  cssClass="inputBox datepicker instalmentDate"  onkeyup="validateSpecialChars(this)" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}' />
																	</td>
																	<td style="text-align: center;">
																		<s:textfield name="installmentPremium[%{#stat.count-1}]" id="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);Itnegative(this.id,this.value);allowOneDot(this);" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}' /> 
																	</td>
																	<td style="text-align: center;">
																		<s:textfield name="paymentDueDays[%{#stat.count-1}]" id="paymentDueDays[%{#stat.count-1}]" cssClass="inputBox"  cssStyle="text-align: right;" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/> 
																	</td> 
																</tr>
																</s:iterator>
															</tbody>
														</table>
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>
										</s:if>
										<s:if test='"7".equals(#session.DepartmentId)|| "9".equals(#session.DepartmentId)'>
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<s:text name="label.riskDetail" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.riskGrade" />
															</div>
															<div class="tbox">
																<s:select list="risklist1" listKey="GRADE_ID" listValue="GRADE_DESC" name="riskGrade" cssClass="inputBoxS" headerKey=""	headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.occupationCode" />
															</div>
															<div class="tbox">
																<s:textfield name="occCode" cssStyle="text-align:right;"
																	cssClass="inputBox" maxlength="500"/>
															</div>
														</div>
														<div class="textfieldA">
															<div class="text">
																<s:text name="label.riskDetail" />
															</div>
															<div class="tbox">
																<s:textarea rows="3" name="riskDetail" id="riskDetail"
																	cssClass="inputBoxA" cssStyle="width: 90%;" /> <br/>
																<span class="textAreaRemaining"><label id="riskDetail_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.fireProtection" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="fireProt" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.policyScope" />
															</div>
															<div class="tbox">
																<s:textfield name="scope" cssClass="inputBox" maxlength="30"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.mbIndicator" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="mbind" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.mLop" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="mlopYN" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.aLop" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="alopYN" />
															</div>
														</div>
														<%-- <div class="textfield">
															<div class="text">
																<s:text name="label.categoryZone" />
															</div>
															<div class="tbox">
																<s:select list="category" listKey="ZONE_ID" listValue="ZONE_DESC" cssClass="inputBoxS"	name="categoryZone" headerKey=""	headerValue="---Select---" />
															</div>
														</div>--%>
														<div class="textfield">
															<div class="text">
																<s:text name="label.eqwsIndicator" />
															</div>
															<div class="tbox">
																<s:select list="EQWSIndDrop" listKey="TYPE" listValue="DETAIL_NAME" cssClass="inputBoxS" name="eqwsInd" onchange="setEQWSInd(this.value)" headerKey="" headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield" style="display: none;">
															<div class="text">
																<s:text name="label.wsThreat" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="wsThreat"
																	id="wsThreat" />
															</div>
														</div>
														<div class="textfield" style="display: none;">
															<div class="text">
																<s:text name="label.eqThreat" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="eqThreat"
																	id="eqThreat" />
															</div>
														</div>
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<div class="boxcontent">
										<div class="panel panel-primary">											
											<div class="panel-heading">
												&nbsp;&nbsp;&nbsp;
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="textfield33">
														<div class="text">
															<s:text name="Do you want fill Cresta Zone Details?" />
														</div>
														<div class="tbox">
															<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus" value="(crestaStatus==null || crestaStatus=='') ?'N':crestaStatus" onchange="toggleCrestapopUp(this.value);" disabled='"Y".equals(disableStatus1)' ></s:radio>
														</div>
													</div>
													<div class="text" id="cresta" style="display:none">
															<s:if test='"Y".equals(disableStatus1)'>
																<a href="#" onclick="CrestapopUp('<s:property value="proposalNo"/>')"><s:text name="label.enterDetails"  /></a>
															</s:if>
															<s:else>
																<a href="#" onclick="CrestapopUp('')"><s:text name="label.enterDetails"/></a>
															</s:else>
														</div>
													
													<br class="clear">
												</div>
											</div>
										</div>
									</div>
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<s:if test="type == 2">
																<s:hidden name="commn" id="commn" value="0"/>
														</s:if>
														<s:else>
														<div class="textfield">
															<div class="text">
																<s:text name="label.commissionP" />
															</div>
															<div class="tbox"><s:textfield name="commn" cssClass="inputBox"
																	cssStyle="text-align: right;" onchange="calacccost()"
																	onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled='"Y".equals(disableStatus1)'/>
															</div>
														</div>
														</s:else>
														<s:set id="br" name="broker" value="broker" />

														<div class="textfield" id="pmllvalue">
															<div class="text">
																<s:text name="label.brokerageP" />
															</div>
															<div class="tbox">
																<s:if test="(broker.toString().trim()).equalsIgnoreCase('DIRECT')">
																	<s:textfield name="brokerage" readonly="true" value='0' cssClass="inputBox" cssStyle="text-align: right;"	onchange="calacccost()"  />
																</s:if>
																<s:else>
																	<s:textfield name="brokerage" cssClass="inputBox" cssStyle="text-align: right;"	onchange="calacccost()" onkeyup="checkDecimals10(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="14" disabled='"Y".equals(disableStatus1)'/>
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.taxP" />
															</div>
															<div class="tbox">
																<s:textfield name="tax" cssClass="inputBox"	cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="calacccost()" value='%{"D".equals(profitCenterCode)?0.00:tax}' readonly='"D".equals(profitCenterCode)'	maxlength="8" disabled='"Y".equals(disableStatus1)'/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.otherCostP" />
															</div>
															<div class="tbox">
																<s:textfield name="othercost" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="calacccost()" maxlength="8" disabled='"Y".equals(disableStatus1)'/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.acqCostP" />
															</div>
															<div class="tbox">
																<s:textfield name="acqCostPer" cssClass="inputBox"
																	cssStyle="text-align: right;" readonly="true" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.acqCostAmount" />
															</div>
															<div class="tbox">
																<s:textfield name="acqCost" cssClass="inputBox"
																	readonly="true" cssStyle="text-align: right;" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.acqBonus" />
															</div>
															<div class="tbox">
																<s:select list="bonusList" listKey="DETAIL_NAME" listValue="REMARKS" name="acqBonus" cssClass="inputBoxS" headerKey=""	headerValue="---None---"   onchange="funView(this.value)" disabled='"Y".equals(disableStatus1)'/> <%--disabled='%{("Y".equals(disableStatus1))?true:false}' --%>
															</div>
														</div>
														<div class="textfield" id="ncb" style="display:none">
															<div class="text">
																<s:text name="label.acqBonusPer" />
															</div>
															<div class="tbox">
																	<s:textfield name="acqBonusPercentage" cssClass="inputBox"  cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="7" disabled='"Y".equals(disableStatus1)'/>
															</div>
														</div>
														<div class="textfield" id="lcb" style="display:none">
															<div class="text">
																<a href="#" onclick="getPopUpDetails('LCB','<s:property value="contractNo"/>','<s:property value="endorsmentno"/>','<s:property value="proposalNo"/>','<s:property value="flag"/>','<s:property value="renewalProposalNo"/>','<s:property value="productId"/>')"><s:text name="label.enterDetails" /></a>
															</div>
															
														</div>
														
														
														<br class="clear">
													</div>
												</div>
											</div>
										</div>

										<s:if test='!"0".equals(no_Insurer)'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="label.retroContracts" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
															 <div class="text">
																	<s:text name="label.retentionPer" />
																</div>
																<div class="tbox">
																	<s:textfield name="retper"	cssStyle="text-align:right;" cssClass="inputBox" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" /><%--disabled='"Y".equals(disableStatus1)' --%>
																</div>
																
															</div>
															<table width="100%" class="footable">
																<thead>
																	<tr>
																		<th width="5%" style="text-align: center; vertical-align: middle;">
																			<s:text name="label.sNo" />
																		</th>
																		<th width="22.5%" style="text-align: center; vertical-align: middle;">
																			<s:text name="label.retroType" />
																		</th>
																		<th width="22.5%" style="text-align: center; vertical-align: middle;">
																			<s:text name="label.uwYear" />
																		</th>
																		<th width="22.5%" style="text-align: center; vertical-align: middle;">
																			<s:text name="label.retroContract" />
																		</th>
																		<th width="22.5%" style="text-align: center; vertical-align: middle;">
																			<s:text name="label.retroP" />
																		</th>
																	</tr>
																</thead>
																<tbody>
																	<!-- line iterator starts -->
																	<s:iterator value="uwYearValList" status="stat">
																	<tr>
																		<td>
																		<s:property value='%{#stat.count}'/>
																		</td>
																		
																		
																		<%--<s:if test='0==(#stat.count-1)'>
																			 <td>
																				<s:radio list="#{'SR':'Specific','TR':'Treaty'}" name="retroTypeValList[%{#stat.count-1}]" id="retroTypeValList[%{#stat.count-1}]" value="'TR'" onclick="setRetroType(this.value,'uwYearTd%{#stat.count-1}','%{#stat.count-1}'),setRetroContract('','','cedingCompany%{#stat.count-1}','%{#stat.count-1}')" disabled="true"/>
																				<s:hidden name="retroType1" id="retroType1" value="%{retroType1!=null?'':retroType1}" />
																			</td>
																			<td id="uwYearTd<s:property value='%{#stat.count-1}'/>">
																				<s:select list="%{uwlList[#stat.count-1]}" listKey="CONTDET1" listValue="CONTDET2" name="uwYearValList[%{#stat.count-1}]" id="uwYearValList[%{#stat.count-1}]" cssClass="inputBoxS" onchange="setRetroContract('TR',this.value,'cedingCompany%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" disabled="true"/>
																			</td>
																			<td id="cedingCompany<s:property value='%{#stat.count-1}'/>">
																				<s:select list="%{retroDupList[#stat.count-1]}" listKey="CONTDET1" listValue="CONTDET1" name="cedingCompanyValList[%{#stat.count-1}]" id="cedingCompanyValList[%{#stat.count-1}]" cssClass="inputBoxS"  disabled="true"/>
																			</td>
																			
																		</s:if>
																		<s:else>--%>
																			<td>
																				<s:radio list="#{'SR':'Specific','TR':'Treaty'}" name="retroTypeValList[%{#stat.count-1}]"  id="retroTypeValList[%{#stat.count-1}]"    onclick="setRetroType(this.value,'uwYearTd%{#stat.count-1}','%{#stat.count-1}'),setRetroContract('','','cedingCompany%{#stat.count-1}','%{#stat.count-1}')" /><%--disabled='"Y".equals(disableStatus1)' --%>
																			</td>
																			<td id="uwYearTd<s:property value='%{#stat.count-1}'/>">
																				<s:select list="%{uwlList[#stat.count-1]}" listKey="CONTDET1" listValue="CONTDET2" name="uwYearValList[%{#stat.count-1}]" id="uwYearValList[%{#stat.count-1}]" cssClass="inputBoxS" onchange="setRetroContract('eqThreat%{#stat.count-1}',this.value,'cedingCompany%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" /><%--disabled='"Y".equals(disableStatus1)' --%>
																			</td>
																			<td id="cedingCompany<s:property value='%{#stat.count-1}'/>">
																				<s:select list="%{retrolList[#stat.count-1]}" listKey="CONTDET1" listValue="CONTDET1" name="cedingCompanyValList[%{#stat.count-1}]" id="cedingCompanyValList[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" /><%--disabled='"Y".equals(disableStatus1)' --%>
																			</td>
																		<%--</s:else>--%>
																		<td>
																			<s:textfield name="retroPercentage[%{#stat.count-1}]" id="retroPercentage[%{#stat.count-1}]" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8" /><%-- disabled='"Y".equals(disableStatus1)'--%>
																		</td>
																		
																	</tr>
																	</s:iterator>
																	<!-- line iterator ends -->
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</s:if>

										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.capacityUtilizationP" />
															</div>
															<div class="tbox">
																<s:textfield name="cu" cssStyle="text-align:right;"	cssClass="inputBox" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="26" disabled='"Y".equals(disableStatus1)'/>
															</div>
														</div>
														<div class="textfieldA">
															<div class="text">
																<s:text name="label.capacityUtilizationReason" />
															</div>
															<div class="tbox">
																<s:textarea rows="3" id="cuRsn" name="cuRsn" cssClass="inputBoxA" cssStyle="width: 90%;" /> <br/>
																<span class="textAreaRemaining"><label id="cuRsn_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
															</div>
														</div>
														
														<%-- <div class="textfield">
															<div class="text">
																<s:text name="label.refertoHeadOffice" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="reftoHO" />
															</div>
														</div>--%>
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>
										<div class="boxcontent">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<s:text name="label.lossRecord" />
											</div>
											<div class="panel-body">
											<div class="textfield">
												<div class="text">
													<s:text name="label.lossRecord" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="lossRecord" onchange="getLossDetails(this.value);"/>
												</div>
											</div>
											<br class="clear"/>
											<div  id="loss" style="display:none;">
											
											<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
												<table class="footable table-overflow" width="100%" id="newgen1" >
																<thead>
																	<tr>
																		<th width="2%"> <s:text name="Serial No" />  </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.year" />  </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossno" />  </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.insurName" />  </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.incDate" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.ExpDate" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.dateOdloss" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.causeofloss" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.insuredclaim" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premium" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossratio" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.leader" /> </th>
																		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.itire" /> </th>
																		<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
																	</tr>
																</thead>
																<tbody>
																<s:iterator value="lossRecordList" var="list" status="stat">
																	<tr>
																			<td>
																				<s:textfield name="lossSNoS[%{#stat.count-1}]" id="lossSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
																			</td>
																			<td>
																			<s:textfield name="lossYear[%{#stat.count-1}]" id="lossYear%{#stat.count-1}"  cssClass="inputBox"  maxlength="30"  />
																			</td>
																			<td>
																			<s:textfield name="lossNo[%{#stat.count-1}]" id="lossNo%{#stat.count-1}"  cssClass="inputBox"   maxlength="30" onchange="getInsuredName('%{#stat.count-1}')" />
																			</td>
																			<td>
																			<s:textfield name="lossinsuredName[%{#stat.count-1}]" id="lossinsuredName%{#stat.count-1}" cssClass="inputBox"   maxlength="100"   />
																			</td>
																			<td>
																			<s:textfield name="lossInceptionDate[%{#stat.count-1}]" id="lossInceptionDate%{#stat.count-1}"  cssClass="inputBox datepicker accumDate"   onkeyup="validateSpecialChars(this)" onchange="functionDate('%{#stat.count-1}')"/>
																				
																			</td>
																			<td>
																			<s:textfield name="lossExpiryDate[%{#stat.count-1}]" id="lossExpiryDate%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)"  />
																			
																			</td>
																			<td>
																			<s:textfield name="lossDateOfLoss[%{#stat.count-1}]" id="lossDateOfLoss%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)" />
																			</td>
																			<td >
																			<s:textfield name="lossCauseOfLoss[%{#stat.count-1}]" id="lossCauseOfLoss%{#stat.count-1}" cssClass="inputBox" maxlength="100"   />
																			</td>
																			<td >
																			<s:textfield name="lossInsuredClaim[%{#stat.count-1}]" id="lossInsuredClaim%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossPremium[%{#stat.count-1}]" id="lossPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossRatio[%{#stat.count-1}]" id="lossRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossLeader[%{#stat.count-1}]" id="lossLeader%{#stat.count-1}" cssClass="inputBox"  maxlength="30"   />
																			</td>
																			<td >
																			<s:textfield name="lossITIReShare[%{#stat.count-1}]" id="lossITIReShare%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   />
																			</td>
																			<td align="center">
																			<s:if test='0!=(#stat.count-1)'>
																			<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" />
																			</s:if>
																			</td>
																		</tr>
																		
																		</s:iterator>
																		<s:hidden name="lossCount" id="lossCount" ></s:hidden>
																	</tbody>
														</table>
															<div class="boxcontent" align="right">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');" />
																<input type="button"  value="Total"  class="btn btn-sm btn-primary" onclick="Calculate();" />
															</div>
															
															<br class="clear"/>
														</div>
												
											</div>
											<div  id="calculateresult">
											</div>
											</div>
											</div>
											
										</div>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									 <div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderWriter" />
												</div>
												<div class="tbox">
														<s:select list="underwriterList" listKey="CUSTOMER_ID" cssClass="inputBoxS"  listValue="NAME" name="leader_Underwriter" id="leader_Underwriter"   headerKey="" headerValue="---Select---"   onchange="getCountry(this.value,'country','');getUnderwriterShare(this.value);"/>
												</div>
											</div>
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<div class="textfield" >
													<div class="text">
														<s:text name="label.leadUnderwritterCountry" />
													</div>
													<div class="tbox" id="country">
															<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  />													
													</div>
												</div>	
											</s:if>
											<s:else>
												<div class="tbox" id="country">
													<s:hidden name="leader_Underwriter_country" id="leader_Underwriter_country"/>
												</div>
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderwritterShareP" />
												</div>
												<div class="tbox">
													<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="false"/>
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
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfieldA25">
															<s:text name="Underwriter's Recommendations" />
														</div>
														<div class="textfieldA75">
															<s:textarea cssClass="inputBoxA" id="uwRecommendation" name="uwRecommendation"
																rows="3" cssStyle="width: 100%;" /><br/>
															<span class="textAreaRemaining"><label id="uwRecommendation_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
														</div>
														<div class="textfieldA25">
															<s:text name="label.exclusion" />
														</div>
														<div class="textfieldA75">
															<s:textarea name="exclusion" id="exclusion" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
															<br/>
															<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
														</div>
														<div class="textfieldA25">
															<s:text name="label.otherAcceptance" />
														</div>
														<div class="textfieldA75">
															<s:textarea cssClass="inputBoxA" id="othAccep" name="othAccep"
																cssStyle="width: 100%;" rows="3" /> <br/>
															<span class="textAreaRemaining"><label id="othAccep_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
														</div>
														<br class="clear" />
													</div>
												</div>
											</div>
										</div>
									<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
									<s:if test='"endorsment".equalsIgnoreCase(mode)'>
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
																		<div class="text">
																			<s:text name="label.ceaseStatus" />&nbsp; <sup style="color:red;">#</sup>
																		</div>
																		<div class="tbox">
																			<s:radio name="ceaseStatus" id="ceaseStatus" list="#{'Y':'Yes','N':'No'}" value="ceaseStatus==null?'N':ceaseStatus" />
																		</div>
														</div>
														<%-- <div class="textfield"> /*For Y145 Changes By Ramya*/
															<div class="text">
																<s:text name="label.endorsementStatus" />
															</div>
															<div class="tbox">
																<s:radio name="endorsementStatus" id="endorsementStatus" list="#{'Y':'Yes','N':'No'}" value="endorsementStatus==null?'N':endorsementStatus" onclick="EndorsementScript(this.value)"/>
															</div>
														</div>
														<s:if test='"Y".equals(endorsementStatus)'>	--%>	
														<s:hidden name="endorsementStatus" id="endorsementStatus"/>											
													<div class="textfield" >
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
														<!--  <div class="inputAppend">
														<sj:datepicker name="endorsementDate" id="endorsementDate" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate()" readonly="%{prclFlag==true?true:false}" />
														</div>-->  
														
														<s:textfield name="endorsementDate" id="endorsementDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"   disabled="%{prclFlag==true?true:false}" />	
													</div>
												</div>
												<div class="textfield" >
													<div class="text">
														<s:text name="label.docDetails" />
													</div>
													<div class="tbox">
															<s:radio name="docStatus" id="docStatus" list="#{'Y':'Yes','N':'No'}" value="docStatus==null?'N':docStatus" onclick="DocScript(this.value)"/>
													</div>
												</div>
											<br class="clear" />
											<div class="boxcontent" id="docView" style="display:none;">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="upload.heading.documentsupload" />
													</div>
													
													<div class="panel-body">
														<div class="boxcontent">
															<table class="footable" id="DocTable">
																<thead>
																<tr>
																	<th width="5%">
																		<s:text name="upload.docId" />
																	</th>
																	<th width="20%">
																		<s:text name="upload.docType" />
																	</th>
																	<th width="45%">
																		<s:text name="upload.docDesc" />
																	</th>
																	<th width="30%">
																		<s:text name="upload.selectdoc" />
																	</th>
																	<th width="30%">
																		<s:text name="Delete" />
																	</th>
																</tr>
																</thead>
																<tbody>
																<s:iterator value="docuList" id="index" status="stat">
																<tr>
																	<td class="formCon" style="text-align: center;">
																		<s:textfield name="docId[%{index}]" id="docId" cssClass="inputBox" value="%{#index+1}" readonly="true"/> 
																 	</td>
																 	<s:if test='!"null".equals(docType)'>
																	<td class="formCon"  valign="top">
																		<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																	</td>
																	</s:if>
																	<s:else>
																	<td class="formCon"  valign="top">
																		<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																	</td>
																	</s:else>
																	<td class="formCon" style="text-align: center;">
																		<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" cssClass="inputBox" />												 
																	</td>													
																	<td class="formCon">
																		<s:file name="upload"  cssClass="inputBox" id="upload"/>
																	</td>
																	<td class="formCon">
																		<s:if test='0!=(#stat.count-1)'>
																				<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteUpload('<s:property value="%{#stat.count-1}"/>')" />
																		</s:if>
																		
																	</td>
																</tr>
																</s:iterator>
																</tbody>
															</table>											
														</div>
														<div class="boxcontent" align="right">
															<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="insRow('DocTable');" /> 
														</div>
													</div>
												
												</div>
												<s:hidden name="startIndex" id="startIndex"/>
												<s:hidden name="endIndex" id="endIndex" />
											</div>
														<br class="clear"/>
													</div>
													
												</div>
											</div>
										</div>
									</s:if>
									<div class="tablerow">
										<div class="boxcontent" align="center">
											<input type="button" value="previous" class="btn btn-sm btn-warning" onclick="functionbackMode()" />
											<s:if test='"".equals(contractNo)  || contractNo == null'>
											
												<input type="submit" value="Cancel"	class="btn btn-sm btn-danger" onClick="return FunctionEditCancel('%{proStatus}')" />&nbsp;&nbsp;&nbsp;
											<s:if test='"0".equals(no_Insurer)'>	
												<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');FunctionSaveOption1()" />
												<s:if test='"A".equals(proStatus)'>
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');funsubmit1()" />
												</s:if>
											</s:if>
											<s:else>
												<input type="button" value="Save" class="btn btn-sm btn-info" onClick="disableForm(this.form,false,'');FunctionSaveOption()" />
												<s:if test='"A".equals(proStatus)'>
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');funsubmit()" />
												</s:if>
											</s:else>
											</s:if>
											<s:else>
												<input type="submit"  value="Cancel"   class="btn btn-sm btn-danger" id="mybutton" onclick="endorsementCancel()" />
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');return oncheck();" />
											</s:else>
											
										</div>
									</div>
									<s:hidden name="underwriterCode" />
									<s:hidden name="dgmsApproval" />
									<s:hidden name="proposalNo" id="proposalNo"/>
									<s:hidden name="contractNo" id="contractNo"/>
									<s:hidden name="endorsmentno" id="endorsmentno" />
									<s:hidden name="renewal_Contract_no" />
									<s:hidden name="renewalFlag" />
									<s:hidden name="renewalStatus" />
									<s:hidden name="premiurate" />
									<s:hidden name="conmode" />
									<s:hidden name="baseLoginID" />
									<s:hidden name="noOfInst" id="noOfInst"/>
									<s:hidden name="no_Insurer" id="no_Insurer"/>
									<s:hidden name="inceptionDate" />
									<s:hidden name="expiryDate" />
									<s:hidden name="Department" value="%{Department}" />
									<s:hidden name="sipml" />
									<s:hidden name="proposalno" value="%{proposalNo}" />
									<s:hidden name="mode" id="mode" />
									<s:hidden name="flag" id="flag"/>
									<s:hidden name="type"/>
									<s:hidden name="display" />
									<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>
									<s:hidden name="originalCurrency" id="originalCurrency"/>
									<s:hidden name="receiptofPayment" id="receiptofPayment"/>
									<s:hidden name="retroDupVal" id="retroDupVal" />
									<s:hidden name="reference" id="reference" />
									<s:hidden name="proposalReference" id="proposalReference" />
									<s:hidden name="proStatus" id="proStatus"/>
									<s:hidden name="profitCenterCode" id="profitCenterCode"/>
									<s:hidden name="shareValue" id="shareValue"/>
									<s:hidden name="insuredName" id="insuredName"/>
									<%--<s:hidden name="reMode" id="reMode"/>--%>
									<s:hidden name="renewalEditMode" id="renewalEditMode"/>
									<s:hidden name="bonusPopUp" id="bonusPopUp"/>
									<s:hidden name="crestaPopUp" id="crestaPopUp"/>
									<s:hidden name="retroDupType" id="retroDupType"/>
									<s:hidden name="retroDupContract" id="retroDupContract"/>
									<s:hidden name="retroDupYerar" id="retroDupYerar"/>
									<s:hidden name="sumusd" id="sumusd"/>
									<s:hidden name="maxiumlimit" id="maxiumlimit"/>
									<s:hidden name="usCurrencyRate" id="usCurrencyRate"/>
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
	
function endorsementCancel() {
destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	//document.getElementById("endorsementStatusY").disabled = true;
	//document.getElementById("endorsementStatusN").disabled = true;
	document.getElementById("endorsementStatus").value='N';
	document.facultative2.action = "${pageContext.request.contextPath}/InitPortfolio.do?endtMode=endorsment&endorsementStatus=N";
}
function setEQWSInd(value)
{
	if(value=="N")
	{
		document.getElementById("wsThreatN").checked=true;
		document.getElementById("eqThreatN").checked=true;
	}else if(value=="EQ")
	{
		document.getElementById("wsThreatN").checked=true;
		document.getElementById("eqThreatY").checked=true;
	}else if(value=="WS")
	{
		document.getElementById("wsThreatY").checked=true;
		document.getElementById("eqThreatN").checked=true;
	}else if(value=="EQWS")
	{
		document.getElementById("wsThreatY").checked=true;
		document.getElementById("eqThreatY").checked=true;
	
	}
}

function calacccost()
{
	if(document.facultative2.commn.value==null || document.facultative2.commn.value=="")
	{
		var comm=0;
	}else
	{
		var comm=document.facultative2.commn.value;
	}
	if(document.facultative2.brokerage.value==null || document.facultative2.brokerage.value=="")
	{
		var brokerage=0;
	}
	else
	{
		var brokerage=document.facultative2.brokerage.value;
	}
	if(document.facultative2.tax.value==null || document.facultative2.tax.value=="")
	{
		var tax=0;
	}else
	{
		var tax=document.facultative2.tax.value;  
	}
	if(document.facultative2.othercost.value==null || document.facultative2.othercost.value=="")
	{
		var othercost=0;
	}
	else
	{
		var othercost=document.facultative2.othercost.value;
	}
	if(document.facultative2.gwpiOurShare.value==null || document.facultative2.gwpiOurShare.value=="")
	{
		var premiumrate=0;
	}
	else
	{
		var premiumrate=document.facultative2.gwpiOurShare.value;
	}
	var add=(parseFloat(comm)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost));//+(parseFloat(tax).toFixed(4))+(parseFloat(othercost).toFixed(4))));
	document.facultative2.acqCostPer.value=add.toFixed(4);
	var cal=(parseFloat(add)*parseFloat(premiumrate))/100;
	document.facultative2.acqCost.value=Comma(cal.toFixed(2));
}

function getRetroContract(uwYear,id)
{
	var retroType=document.getElementById("retroType1"+id).value;
	var inceptionDate='<bean:write property="inceptionDate" name="FaculitivesActionForm"/>';
	if(uwYear=='' || uwYear==null)
	{
		if(retroType=='SR')
		{
			var opt =document.getElementById("uwYear"+id).options.length=0;
			var opt = document.createElement("option");
	 		opt.value ='0';
	 		opt.text = '--Select--';
	 		document.getElementById("uwYear"+id).options.add(opt);
	 		opt = document.createElement("option");
			opt.value ='<bean:write property="year" name="FaculitivesActionForm"/>';
			opt.text ='<bean:write property="year" name="FaculitivesActionForm"/>';
			document.getElementById("uwYear"+id).options.add(opt);
 			return false;
		}else if(retroType=='TR')
		{
    		//var broker=document.facultative2.broker.value;
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
				if (xmlhttp.readyState==4 && xmlhttp.status==200)
				{
		 	 		var a=xmlhttp.responseText.split("~");
			 		var j=0;
			 		var opt = document.getElementById("uwYear"+id).options.length=0;
					var opt = document.createElement("option");
			 		opt.value ='0';
			 		opt.text = '--Select--';
			 		document.getElementById("uwYear"+id).options.add(opt);
			 		while(j<a.length)
			 		{	
						opt = document.createElement("option");
						opt.value = a[j];
						j++;
						opt.text = a[j];
					    document.getElementById("uwYear"+id).options.add(opt);
				    	j++;
		   			}         
				}
			}
			xmlhttp.open("POST",'<%=request.getContextPath()%>/FaculitivesDispatchAction.do?method=getRetroContractDetails&inceptionDate='+inceptionDate+'&retroType='+retroType+'&productCode=4',true);
			xmlhttp.send();
		}
	}else if(uwYear!='0')
	{
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
				if (xmlhttp.readyState==4 && xmlhttp.status==200)
				{
		 	 		var a=xmlhttp.responseText.split("~");
			 		var j=0;
			 		var opt = document.getElementById("cedingCompany"+id).options.length=0;
					var opt = document.createElement("option");
			 		opt.value ='0';
			 		opt.text = '--Select--';
			 		document.getElementById("cedingCompany"+id).options.add(opt);
			 		while(j<a.length)
			 		{	
						opt = document.createElement("option");
						opt.value = a[j];
						j++;
						opt.text = a[j];
					    document.getElementById("cedingCompany"+id).options.add(opt);
				    	j++;
		   			}         
				}
			}
			xmlhttp.open("POST",'<%=request.getContextPath()%>/FaculitivesDispatchAction.do?method=getRetroContractDetails&inceptionDate='+inceptionDate+'&retroType='+retroType+'&productCode=4&uwYear='+uwYear,true);
			xmlhttp.send();
	}
}

function setRetroType(val,id,count) {
	var URL = "getRetroContractDetailsFacultative.action?dropDown=uwYear&listName="+count+"&inceptionDate=${inceptionDate}&retroType="+val;
	postRequest(URL,id);
}
function setRetroContract(retroval,val,id,count) {
	var mode=''
	//if('0'==count){
	//	 mode='Duplicate';
	//	}
	var URL = "getRetroContractDetailsFacultative.action?dropDown=cedingCompany&listName="+count+"&inceptionDate=${inceptionDate}&retroType="+retroval+"&year="+val+"&mode="+mode;
	postRequest(URL,id);
}

function callculateCU(siosdc,uwLimt)
{
 	if(siosdc!=null && siosdc!="" && uwLimt!=null && uwLimt!="0" && uwLimt!="")
 	{
 		var cu=(siosdc/uwLimt);
       	//document.facultative2.cu.value=Comma(cu.toFixed(2));
       	 	document.facultative2.cu.value=Comma(cu.toFixed(4));
	}
}

function funsubmit()
{
destroyPopUps();
	document.getElementById("reference").value = 'submit';
	document.facultative2.action="RetroDupCheckFacultative.action";
	document.facultative2.submit();
}
function funsubmit1()
{
document.getElementById("reference").value = '';
	replaceComma(document.facultative2,'acqCost');
	document.facultative2.action="InsertSecondPageFacultative.action";
	document.facultative2.submit();
}

function FunctionSaveOption()
{
destroyPopUps();
	document.getElementById("reference").value = 'save';
	document.facultative2.action="RetroDupCheckFacultative.action";
	document.facultative2.submit();
}
function FunctionSaveOption1()
{
document.getElementById("reference").value = '';
	replaceComma(document.facultative2,'acqCost');
	document.facultative2.action="SavePageSecondFacultative.action";
	document.facultative2.submit();
}
function EndorsementScript(value) {
		if(value=="Y") {
			document.getElementById('endtlabel').style.display='block';		
			document.getElementById('doc1').style.display='block';
            document.facultative2.docStatus.value= 'N';
		}
		else if(value=="N") {
			document.getElementById('endtlabel').style.display='none';
			document.getElementById('doc1').style.display='none';
            document.getElementById('docView').style.display='none';
        }
	}
function FunctionEditCancel(flag)
{
destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	var no =document.getElementById("proposalNo").value;
	var old = document.getElementById("renewalProposalNo").value ;
	if('Renewal'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("renewalProposalNo").value =no;
			document.facultative2.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action?Proposal_no='+old; 
			document.facultative2.submit();
			}
			else{
			return false;
			}
		}
		else{
			document.facultative2.action="${pageContext.request.contextPath}/commonListPortfolio.do?proposalNo="+no+"&layerNo=0";
			document.facultative2.submit();
		}
}

function ConCancelMode()
{
	document.facultative2.action='<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=Init';
	document.facultative2.submit(); 
}
	
function functionbackMode()
{
destroyPopUps();
	document.facultative2.action="EditMethodFacultative.action?multiuserMode=edit";
	document.facultative2.submit();
}

function GetPmlValidation(value)
{
   	if(value=="SI")
   	{ 
	 	//document.getElementById("pmlOurShare").readOnly=true;
	}
	else if(value=="PML") 
	{
	 	document.getElementById("sumInsuredOurShare").readOnly=true;
	}
}
function endorsementPage() {
disableChange();
document.getElementById("reference").value = 'endt';
	document.facultative2.action="RetroDupCheckFacultative.action";
	document.facultative2.submit();
}
function disableChange(){
$("#facultative2 input, #facultative2 select").attr('disabled',false);
$("#facultative2 input, #facultative2 select").attr('readonly',false);
}
function endorsement(){
document.getElementById("reference").value = '';
	replaceComma(document.facultative2,'acqCost');
	document.facultative2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementFacultative.action";
	document.facultative2.submit();
}
<s:if test="!hasActionErrors()">
retroDupCheck();
</s:if>
function retroDupCheck(){
var ans = true;
var result =true;

	var val = document.getElementById("retroDupVal").value;
	var reference = document.getElementById("reference").value;
	if('endt' == reference || 'submit' == reference || 'save' == reference){
	if('[]' != val){
	val = val.replace("[","");
	val = val.replace("]","");
	var val1 = val.split(",");
		for (word of val1){
		 ans = confirm(word);
		if(!ans){
		result = false;
		}
		}
		}
		if(ans && result){
		if('endt'==reference){
		disableChange();
		endorsement();
		}
		else if('submit'==reference){
		disableChange();
		funsubmit1();
		}
		else if('save'==reference){
		disableChange();
		FunctionSaveOption1();
		}
		}
		}
}
function oncheck(){
destroyPopUps();
var mode = document.getElementById("mode").value;
var val =document.getElementById("no_Insurer").value;
	if("endorsment"==mode){
	document.getElementById("endorsementStatus").value='Y';
	}
   <%--   if(document.getElementById("endorsementStatusN").checked){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
     	if(answer)
     	endorsementPage();
     	else
     	return false;
     	}
    else --%>
    if("0"==val){
    endorsement();
    }else{
    endorsementPage();
    }
}
$(function() {
		//set up text length counter
		$('#exclusion').keyup(function() {
	        update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
	    });
	    update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
		<s:if test="DepartmentId == 7 || DepartmentId == 9">
		$('#riskDetail').keyup(function() {
	        update_chars_left(500, $('#riskDetail')[0], $('#riskDetail_left'));
	    });
	     update_chars_left(500, $('#riskDetail')[0], $('#riskDetail_left'));
	    </s:if>
	    //and fire it on doc ready, too
	   
	
		$('#cuRsn').keyup(function() {
	        update_chars_left(500, $('#cuRsn')[0], $('#cuRsn_left'));
	    });
	    update_chars_left(500, $('#cuRsn')[0], $('#cuRsn_left'));
	    
	    $('#uwRecommendation').keyup(function() {
	        update_chars_left(500, $('#uwRecommendation')[0], $('#uwRecommendation_left'));
	    });
	    update_chars_left(500, $('#uwRecommendation')[0], $('#uwRecommendation_left'));
		    	
	   // $('#remarks').keyup(function() {
	       // update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	   // });	    
	    //update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    
	    $('#othAccep').keyup(function() {
	        update_chars_left(500, $('#othAccep')[0], $('#othAccep_left'));
	    });
	    update_chars_left(500, $('#othAccep')[0], $('#othAccep_left'));
	    
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
	       	else{
	       		document.getElementById('lcb').style.display = 'none';
	       	 	document.getElementById('ncb').style.display = 'none';
	       	}
	}
	
	function getPopUpDetails(acqBonus,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo,productId){
	//var URL ="${pageContext.request.contextPath}/viewBonusPopUpFacultative.action?acqBonus="+acqBonus+"&proposalNo="+proposalNo+"&endorsmentno="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&renewalProposalNo="+renewalProposalNo+"&productId="+productId;
		document.getElementById("bonusPopUp").value = "Y";
		var URL ="${pageContext.request.contextPath}/viewBonusPopUpXol.action?acqBonus="+acqBonus+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&layerNo=0";		
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
				
getCountry('<s:property value="leader_Underwriter"/>','country','<s:property value="leader_Underwriter_country"/>');
function getCountry(value,id,leader_Underwriter_country){
	var URL='${pageContext.request.contextPath}/ajaxValueRiskDetails.action?leader_Underwriter='+value+'&dropDown=country&leader_Underwriter_country='+leader_Underwriter_country;
	postRequest(URL,'country');
}




toggleCrestapopUp('<s:property value="crestaStatus"/>');
function toggleCrestapopUp(value){
if(value=='Y'){
document.getElementById('cresta').style.display='block';
}else if(value=='N'){
document.getElementById('cresta').style.display='none';
}
}



function CrestapopUp(layerProposalNo){
	var contractNo = document.getElementById("contractNo").value;
	var proposalno = document.getElementById("proposalNo").value;
	var amendId = document.getElementById("endorsmentno").value;
	var cur = document.getElementById("originalCurrency").value;
	document.getElementById("crestaPopUp").value = "Y";
		var URL ='${pageContext.request.contextPath}/crestaPopUpRiskDetails.action?contractNo='+contractNo+'&proposal_no='+proposalno+'&amendId='+amendId+'&orginalCurrency='+cur+'&layerProposalNo='+layerProposalNo;
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
DocScript('<s:property value="docStatus"/>');
function DocScript(id) {
    var amendId = document.getElementById("endorsmentno").value;
    if (amendId > 0) {
        document.facultative2.docStatus.value = id;
    if ("Y" == id) {
        document.getElementById('docView').style.display = 'block';
    }
    else {
        document.getElementById('docView').style.display = 'none';
    }
}
}
function addUpload(){
	document.facultative2.display.value='addnew';
	document.facultative2.method.value='list';
	document.facultative2.action = "${pageContext.request.contextPath}/documentUpload.do";
	document.facultative2.submit();
}

function insRow(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			var cell1 = row.insertCell(0)
			
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "docId["+(rowCount-1)+"]";
       		element1.id = "docId["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
			
			var cell2 = row.insertCell(1);
			createdoc(cell2, rowCount)
			
			
			
			var cell3 = row.insertCell(2);
			var element3 = document.createElement("textarea");
			//element3.type = "textarea";
			element3.name = "docDesc["+(rowCount-1)+"]";
      		element3.id = "docDesc["+(rowCount-1)+"]";
			element3.className = "inputBox";
			//element7.value="";
			element3.setAttribute("rows", "2");
			element3.setAttribute("cols", "70");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			var element4 = document.createElement("input");
			element4.type = "file";
			element4.name = "upload";
      		element4.id = "upload";
			element4.className = "inputBox";
			
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			element5.setAttribute("onclick", "deleteUpload('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-danger"
			cell5.appendChild(element5);
			
            document.getElementById("endIndex").value = (parseInt(rowCount));
			
}

function createdoc(cell, rowCount){
		element = document.createElement("select");
       element.name = "docTypeId["+(rowCount-1)+"]";
      element.id = "docTypeId["+(rowCount-1)+"]";
       element.className = "inputBox";
       populatedoc(element);
         cell.appendChild(element);
}


function populatedoc(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='docType'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DOC_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='DOC_TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 
 function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
function hundredCheck(id,val){
    if(parseFloat(val)>100){
        alert("This field value is not exceed 100");
        document.getElementById(id).value='';
    }
    else if(val=='-'){
        document.getElementById(id).value='';
    }
}
function insRow1(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "lossSNoS["+(rowCount-1)+"]";
       		element1.id = "lossSNoS"+(rowCount-1)+"";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1);
			var element1 = document.createElement("input");
			element1.type = "text";
			element1.name = "lossYear["+(rowCount-1)+"]";
      		element1.id = "lossYear"+(rowCount-1)+"";
			element1.className = "inputBox";
			element1.setAttribute("maxlength",'30');
			cell2.appendChild(element1);
			
			
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "lossNo["+(rowCount-1)+"]";
      		element2.id = "lossNo"+(rowCount-1)+"";
			element2.className = "inputBox";
			element2.setAttribute("maxlength",'30');
			element2.setAttribute("onkeyup",'checkNumbers(this);');
			element2.setAttribute("onchange","getInsuredName("+(rowCount-1)+")");
			cell3.appendChild(element2);
			
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			//element3.value = '<s:property value="insuredName"/>';
			element3.name = "lossinsuredName["+(rowCount-1)+"]";
      		element3.id = "lossinsuredName"+(rowCount-1)+"";
			element3.className = "inputBox";
			element3.setAttribute("maxlength",'100');
			//element3.setAttribute("disabled",true);
			cell4.appendChild(element3);
			
			
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "lossInceptionDate["+(rowCount-1)+"]";
      		element4.id = "lossInceptionDate"+(rowCount-1);
			element4.className = "inputBox  datepicker accumDate1";
			element4.setAttribute("onkeyup", "validateSpecialChars(this)");
			element4.setAttribute("onchange", "functionDate("+(rowCount-1)+")");
			
			  $(document).on("focus", "#lossInceptionDate"+(rowCount-1), function(){ 
			   $(this).datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : "dd/mm/yy"
					//yearRange: "-100:+0"
				});
			  });
			cell5.appendChild(element4); 
			
			
			var cell6 = row.insertCell(5);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "lossExpiryDate["+(rowCount-1)+"]";
      		element5.id = "lossExpiryDate"+(rowCount-1);
			element5.className = "inputBox datepicker accumDate1";
			element5.setAttribute("onkeyup", "validateSpecialChars(this)");
			  $(document).on("focus", "#lossExpiryDate"+(rowCount-1), function(){ 
			   $(this).datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : "dd/mm/yy"
					//yearRange: "-100:+0"
				});
			  });
			cell6.appendChild(element5); 
			
			
			var cell7 = row.insertCell(6);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "lossDateOfLoss["+(rowCount-1)+"]";
      		element6.id = "lossDateOfLoss"+(rowCount-1)+"";
			element6.className = "inputBox datepicker accumDate1";
			element6.setAttribute("onkeyup", "validateSpecialChars(this)");
			  $(document).on("focus", "#lossDateOfLoss"+(rowCount-1), function(){ 
			   $(this).datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : "dd/mm/yy"
					//yearRange: "-100:+0"
				});
			  });
			cell7.appendChild(element6);
			
			var cell8 = row.insertCell(7);
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "lossCauseOfLoss["+(rowCount-1)+"]";
      		element7.id = "lossCauseOfLoss"+(rowCount-1);
			element7.className = "inputBox";
			cell8.appendChild(element7); 
			
			var cell9 = row.insertCell(8);
			var element8 = document.createElement("input");
			element8.type = "text";
			element8.name = "lossInsuredClaim["+(rowCount-1)+"]";
      		element8.id = "lossInsuredClaim"+(rowCount-1);
			element8.className = "inputBox";
			element8.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio("+(rowCount-1)+");");
			element8.setAttribute("maxlength",'30'); 
			element8.setAttribute("style", "text-align:right;");
			cell9.appendChild(element8); 
			
			var cell10 = row.insertCell(9);
			var element9 = document.createElement("input");
			element9.type = "text";
			element9.name = "lossPremium["+(rowCount-1)+"]";
      		element9.id = "lossPremium"+(rowCount-1);
			element9.className = "inputBox";
			element9.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio("+(rowCount-1)+");");
			element9.setAttribute("maxlength",'30'); 
			element9.setAttribute("style", "text-align:right;");
			cell10.appendChild(element9); 
			
			
			var cell11 = row.insertCell(10);
			var element10 = document.createElement("input");
			element10.type = "text";
			element10.name = "lossRatio["+(rowCount-1)+"]";
      		element10.id = "lossRatio"+(rowCount-1);
			//element10.value=document.getElementById("lossLeader"+(parseFloat(rowCount)-2)).value;
			element10.className = "inputBox";
			element10.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element10.setAttribute("maxlength",'30'); 
			element10.setAttribute("style", "text-align:right;");
			cell11.appendChild(element10); 
			
			
			var cell12 = row.insertCell(11);
			var element11 = document.createElement("input");
			element11.type = "text";
			element11.name = "lossLeader["+(rowCount-1)+"]";
      		element11.id = "lossLeader"+(rowCount-1);
			element11.className = "inputBox";
			element11.setAttribute("maxlength",'100'); 
			cell12.appendChild(element11);
			
			
			var cell13 = row.insertCell(12);
			var element12 = document.createElement("input");
			element12.type = "text";
			element12.name = "lossITIReShare["+(rowCount-1)+"]";
      		element12.id = "lossITIReShare"+(rowCount-1);
			element12.className = "inputBox";
			element12.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element12.setAttribute("maxlength",'30'); 
			element12.setAttribute("style", "text-align:right;");
			cell13.appendChild(element12); 
			
			var cell14 = row.insertCell(13);
			var element13 = document.createElement("input");
			element13.type = "button";
			element13.value="Delete";
			element13.setAttribute("onclick", "disableForm(this.form,false,'');deleteRow('"+(rowCount-1)+"')");
			element13.className="btn btn-sm btn-danger"
			element13.setAttribute("style", "text-align:center;");
			cell14.appendChild(element13);
			
			document.getElementById("lossCount").value =parseInt(rowCount);
}

getLossDetails('<s:property value="lossRecord"/>');
function getLossDetails(val){
	//var URL='${pageContext.request.contextPath}/lossValueFacultative.action?dropDown=facLoss&lossRecord='+val;
	//postRequest(URL,'loss');
	if("Y"==val){
		document.getElementById("loss").style.display='inline';
	}
	else{
		document.getElementById("loss").style.display='none';
	}

}
 function deleteRow(val){
 		var status=confirm("Do you want to delete specified row");
		if(status){
		postFormRequest('${pageContext.request.contextPath}/removeLossFacultative.action?mode=delete&deleteId='+val+'&dropDown=lossnewgen', "loss", "facultative2"); 
			}
 }
function getRatio(id){
var claim = document.getElementById("lossInsuredClaim"+id).value;
var premium = document.getElementById("lossPremium"+id).value;
var ratio =0;
if (claim ==''){
claim =0;
}
else{
claim =claim.replace(new RegExp(',', 'g'),'');
}
if(premium ==''){
premium=0;
}
else{
premium = premium.replace(new RegExp(',', 'g'),'');
}
if(premium!='0'&&claim!='0'){
  ratio = (parseFloat(claim)/parseFloat(premium))*100 ;
 }
 document.getElementById("lossRatio"+id).value=Comma(ratio.toFixed(2));
}
function getUnderwriterShare(val){
var proStatus = document.getElementById("proStatus").value
if("64"==val && proStatus=="A"){
var share ='<s:property value="shareValue"/>' ;
document.getElementById("leader_Underwriter_share").value = share;
document.getElementById("leader_Underwriter_share").disabled=true;
}
else{
document.getElementById("leader_Underwriter_share").value ="";
document.getElementById("leader_Underwriter_share").disabled=false;
}
}
if('Y'=='<s:property value="lossRecord"/>'){
Calculate();
}
function Calculate(){
postFormRequest('${pageContext.request.contextPath}/calculateValueFacultative.action?dropDown=calculate', "calculateresult", "facultative2");
}

function getInsuredName(id){
var val = document.getElementById("lossNo"+id).value ;
if(val!=''){
document.getElementById("lossinsuredName"+id).value = "<s:property value="insuredName"/>";
}
}
function functionDate(id)
{
   var	inceptionDate=document.getElementById("lossInceptionDate"+id).value;
	if(inceptionDate!="")
	{
		var date=new Date(reformatDate(inceptionDate));
		date.setFullYear(date.getFullYear()+1);
		date.setDate(date.getDate()-1);
		var d;
		var m
		if(parseInt(date.getDate())<10)
		{
			d="0"+date.getDate();
		}else
		{
			d=date.getDate();
		}
		if(parseInt(date.getMonth()+1)==0)
		{
			m="12"
		}else
		if((parseInt(date.getMonth())+1)<10)
		{
			m="0"+(parseInt(date.getMonth())+1);
		}else
		{
			m=(parseInt(date.getMonth())+1);
		}
		var y=date.getFullYear();
		document.getElementById("lossExpiryDate"+id).value=d+"/"+m+"/"+y;
	}
	}	
function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
    // ex input "2010-01-18" 
    return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
}

function deleteUpload(id){
if(confirm('This row will be deleted. Do You Want to continue?')){
	postFormRequest('${pageContext.request.contextPath}/newDocDeleteFacultative.action?mode=delete&deleteId='+id+'&dropDown=docView', "docView", "facultative2");
}
else { 
		return false;
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

function middleMinusRestrictionNeg(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
        if(text[i]=='-') min++;
        if(min<1) { 
        	prevValue += text[i];
        }
        }
    txt.value= prevValue;
    return false;
}
</script>	
	</body>
</html>
