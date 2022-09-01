<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<sj:head jqueryui="true" jquerytheme="start" />--%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript" type="text/javascript">
	function stopRKey(evt) { 
	  var evt = (evt) ? evt : ((event) ? event : null); 
	  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey; 
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<link
			href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />
		<script
			src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js"
			type="text/javascript"></script>
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
    $( "#incepDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	$( "#expDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	$( "#accDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
  });	
  </script>
	</head>
	<body
		onload="Commas(<s:property value="#session.mfrid" />),setCedRetType('<s:property value="cedRetenType" />')">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1"
					style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form name="proportional" id="proportional" theme="simple"
								action="" method="post" autocomplete="off">
								<div class="table2">
									<div class="tablerow">
										<span style="color: red;"><s:actionerror />
										</span>
									</div>
									<div class="tablerow" align="center">
										<span class="pageHeading"> <s:text
												name="label.proportionalTreaty" /> </span>
									</div>
									<s:if
										test="contNo != '' && contNo != null && proposal_no != null && proposal_no != ''  && amend_Id_Mode != '' && amend_Id_Mode != null">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementType" />
																</div>
																<div class="tbox txtB">
																	<s:select list="endosTypelist" name="endorsmenttype"
																		cssClass="inputBoxS" headerKey=""
																		headerValue="---Select---" listKey="TYPE"
																		listValue="DETAIL_NAME" />
																</div>
															</div>
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementNo" />
																</div>
																<div class="tbox txtB">
																	<s:property value="amendId" />
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
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.proposalNo" />
															</div>
															<div class="tbox">
																<s:textfield name="proposal_no" id="proposal_no"
																	cssClass="inputBox" disabled="true" maxlength="30" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.contractno" />
															</div>
															<div class="tbox txtB">
																<s:textfield name="contNo" id="contNo"
																	cssClass="inputBox" disabled="true" maxlength="30" />
															</div>
														</div>
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
														<div class="textfield">
															<div class="text">
																<s:text name="label.departmentClass" />
															</div>
															<div class="tbox">
																<%--<s:if test="RenewalMode != null || (proposal_no!=null && proposal_no!='') ">--%>
																<s:if test="RenewalMode != null ">
																	<s:select list="departIdlist"
																		listValue="TMAS_DEPARTMENT_NAME"
																		listKey="TMAS_DEPARTMENT_ID" name="departId"
																		cssClass="inputBoxS" headerKey=""
																		headerValue="---Select---" disabled="true"
																		onchange="getAjax(this.value,'subclass')" />
																</s:if>
																<s:elseif test="(baseLayer!=null && baseLayer!='')">
																	<s:select list="departIdlist"
																		listValue="TMAS_DEPARTMENT_NAME"
																		listKey="TMAS_DEPARTMENT_ID" name="departId"
																		cssClass="inputBoxS" headerKey=""
																		headerValue="---Select---"
																		onchange="getAjax(this.value,'subclass');toggleVesselBlock(this.value);" />
																</s:elseif>
																<s:else>
																	<s:select list="departIdlist"
																		listValue="TMAS_DEPARTMENT_NAME"
																		listKey="TMAS_DEPARTMENT_ID" name="departId"
																		cssClass="inputBoxS" headerKey=""
																		headerValue="---Select---"
																		disabled='%{(proposal_no!=null && proposal_no!="") ?true:false}'
																		onchange="getAjax(this.value,'subclass');toggleVesselBlock(this.value);" />
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.subClass" />
															</div>
															<div class="tbox" id="subclass">
																<%--<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled='%{(contNo != "" && contNo != null)?true:false}' />--%>
																<s:select list="subProfitList" listKey="TMAS_SPFC_ID"
																	listValue="TMAS_SPFC_NAME" multiple="true"
																	name="subProfit_center" id="subProfit_center"
																	cssClass="inputBoxS"
																	disabled='%{(contNo != "" && contNo != null)?true:false}'
																	headerKey="ALL" headerValue="ALL" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.profitCentre" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="profit_Centerlist" listKey="TMAS_PFC_ID"
																	listValue="TMAS_PFC_NAME" name="profit_Center"
																	cssClass="inputBoxS" headerKey=""
																	headerValue="---Select---"
																	disabled="%{RenewalMode!=null?true:false}" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
															<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
																<s:text name="label.inwardType" />
															</s:if>
															<s:else>
																<s:text name="label.inwardType02" />
															</s:else>
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="inwardBusinessTypelist" listKey="TYPE"
																	listValue="DETAIL_NAME" name="inwardType"
																	cssClass="inputBoxS" headerKey="0"
																	headerValue="---Select---"
																	disabled="%{RenewalMode!=null?true:false}" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.underwriter" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="UnderWritterlist" listKey="UWR_CODE"
																	listValue="UNDERWRITTER" name="underwriter"
																	cssClass="inputBoxS" headerKey="0"
																	headerValue="---Select---"
																	onchange="getUWLimit(this.value)" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.uwCapacityUGX" />
																(
																<s:property value="shortname" />
																)
															</div>
															<div class="tbox" id="uwc2">
																<s:textfield name="maxLimit_Product" cssClass="inputBox"
																	cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"
																	maxlength="30" />
															</div>
														</div>
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
														<div class="textfield">
															<div class="text">
																<s:text name="label.branchLocation" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="polBrlist" listKey="TMAS_POL_BRANCH_ID"
																	listValue="TMAS_POL_BRANCH_NAME" name="polBr"
																	cssClass="inputBoxS"
																	disabled='%{(proposal_no!=null && proposal_no!="") ?true:false}' />
															</div>
														</div>
														<div class="textfield">

														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.cedingCompany" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<div class="inputAppend">
																	<s:if test="RenewalMode != null">
																		<s:select list="CeddingCompanylist"
																			listKey="CUSTOMER_ID" listValue="NAME"
																			name="cedingCo" id="CeddingId" cssClass="pullLeft"
																			cssStyle="width: 85%; border:transparent;"
																			headerKey="" headerValue="---Select---"
																			disabled="true" />

																		<input type="button" size="2" value="..."
																			onclick="functionview(1)" class="pullRight" />
																	</s:if>
																	<s:else>
																		<s:select list="CeddingCompanylist"
																			listKey="CUSTOMER_ID" listValue="NAME"
																			name="cedingCo" id="CeddingId" cssClass="pullLeft"
																			cssStyle="width: 85%; border:transparent;"
																			headerKey="" headerValue="---Select---"
																			disabled='%{"Y".equals(disableStatus1)?true:false}' onchange="getRetention();"/>
																		<input type="button" size="2" value="..."
																			onclick="functionview(1)" class="pullRight" />
																	</s:else>
																</div>
															</div>
														</div>

														<div class="textfield">
															<div class="text">
																<s:text name="label.broker" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<div class="inputAppend">
																	<%--<s:if test="RenewalMode != null">																												
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />																
														<input type="button"  size="2"  value="..." onclick="functionview(2)" class="pullRight" />																										
													</s:if>
													<s:else>--%>
																	<s:select list="brokerlist" listKey="CUSTOMER_ID"
																		listValue="NAME" name="broker" id="BrokerId"
																		cssClass="pullLeft"
																		cssStyle="width: 85%; border:transparent;"
																		headerKey="" headerValue="---Select---"
																		disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	<input type="button" size="2" value="..."
																		onclick="functionview(2)" class="pullRight" />

																</div>
															</div>
														</div>


														<div class="textfield">
															<div class="text">
																<s:text name="label.treatyType" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:if test="RenewalMode != null">
																	<s:select list="treatyTypelist" listKey="TYPE"
																		listValue="DETAIL_NAME" name="treatyType"
																		cssClass="inputBoxS" headerKey="0"
																		headerValue="---Select---"
																		onchange="getTreatyType(this.value);GetPML();"
																		disabled='%{baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)?true:false}' />
																</s:if>
																<s:else>
																	<s:select list="treatyTypelist" listKey="TYPE"
																		listValue="DETAIL_NAME" name="treatyType"
																		cssClass="inputBoxS" headerKey="0"
																		headerValue="---Select---"
																		onchange="getTreatyType(this.value);GetPML();"
																		disabled='%{ (proposal_no != "" && proposal_no != null)?true:false}' />
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.treatyNameType" />
															</div>
															<div class="tbox">
																<s:textfield name="treatyName_type" cssClass="inputBox"
																	maxlength="500" />
															</div>
														</div>
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
														<div class="textfield">
															<div class="text">
																<s:text name="label.inceptionDate" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<div class="inputAppend">
																	<s:textfield name="incepDate" id="incepDate"
																		cssStyle="width: 100%; border:transparent;"
																		onkeyup="validateSpecialChars(this)"
																		onchange="functionDate();GetExchangeRate();"
																		disabled='%{"Renewal".equals(proposalReference) || (contNo != "" && contNo != null)?true:false}' />
																</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.expiryDate" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:if
																	test="layerProposalNo == null || layerProposalNo == ''">
																	<div class="inputAppend">
																		<s:textfield name="expDate" id="expDate"
																			cssStyle="width: 100%; border:transparent;"
																			onkeyup="validateSpecialChars(this)"
																			readonly="%{layerProposalNo!=null?true:false}" />
																	</div>
																</s:if>
																<s:else>
																	<div class="inputAppend">
																		<s:textfield name="expDate" id="expDate"
																			cssStyle="width: 100%; border:transparent;"
																			onkeyup="validateSpecialChars(this)"
																			readonly="%{layerProposalNo!=null?true:false}" />
																	</div>
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.uwYear" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox" id="yearId">
																<s:select list="yearList" listKey="YEAR"
																	listValue="YEAR" name="uwYear" id="uwYear" cssClass="inputBoxS"
																	headerKey="" headerValue="---Select---"
																	disabled='%{(contNo != "" && contNo != null)?true:false}' onblur="getRetention();"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.acceptanceDate" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<div class="inputAppend">
																	<s:textfield name="accDate" id="accDate"
																		cssStyle="width: 100%; border:transparent;"
																		onkeyup="validateSpecialChars(this)"
																		onchange="GetExchangeRate()"
																		disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.originalCurrency" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="orginalCurrencylist"
																	listKey="CURRENCY_ID" listValue="CURRENCY_NAME"
																	name="orginalCurrency" cssClass="inputBoxS"
																	headerKey="" headerValue="---Select---"
																	onchange="GetExchangeRate()"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.exchangeRate" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox" id="exRate">
																<s:textfield name="exchRate"
																	cssStyle="text-align:right;" readonly="true"
																	cssClass="inputBox" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.Retro" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="spRetro"
																	onclick="GetSp(this.value)"
																	value="%{(spRetro==null || spRetro=='')?'Y':spRetro}"
																	/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.noofRetroContracts" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield cssClass="inputBox" name="no_Insurer"
																	id="NoOFInsur" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);"
																	maxlength="3"
																	 /><%--disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
															</div>
														</div>

														<div id="VesselBlock">
															<div class="textfield">
																<div class="text">
																	<s:text name="Facultative.LimitperVesselOC" />
																</div>
																<div class="tbox">
																	<s:textfield cssClass="inputBox"
																		name="limitPerVesselOC" id=""
																		onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);"
																		cssStyle="text-align: right;" maxlength="30" />
																</div>
															</div>
															<div class="textfield">
																<div class="text">
																	<s:text name="Facultative.LimitperLocationOC" />
																</div>
																<div class="tbox">
																	<s:textfield cssClass="inputBox"
																		name="limitPerLocationOC" id=""
																		onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);"
																		cssStyle="text-align: right;" maxlength="30" />
																</div>
															</div>
														</div>
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
														<%-- <div class="textfield">
												<div class="text">
													<s:text name="label.territory" />
												</div>
												<div class="tbox">
													<s:select list="Territortylist" listKey="TERRITORY_CODE" listValue="TERRITORY_DESC" name="territory" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />													
												</div>
											</div>	--%>

														<div class="textfield">
															<div class="text">
																<s:text name="label.territoryScope" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="territoryscope" cssClass="inputBox"
																	maxlength="500" />
															</div>
														</div>

														<div class="textfield">
															<div class="text">
																<s:text name="label.cleancutrunoff" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="proposaltypelist" listKey="TYPE"
																	listValue="DETAIL_NAME" name="proposalType"
																	cssClass="inputBoxS" headerKey="0"
																	headerValue="---Select---"
																	onchange="getRateField(this.value)" />
															</div>
														</div>
														<div class="textfield" id="ratId" style="display: none;">
															<div class="text">
																<s:text name="label.rate.year" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="runoffYear" id="runoffYear"
																	cssClass="inputBox" cssStyle="text-align:right;"
																	maxlength="26"
																	onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" />
															</div>
														</div>

														<div class="textfield">
															<div class="text">
																<s:text name="label.portfolioCovered" />
															</div>
															<div class="tbox">
																<s:textfield name="riskCovered" cssClass="inputBox"
																	maxlength="50" />
															</div>
														</div>

														<div class="textfield">
															<div class="text">
																<s:text name="label.LOCIssued" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="LOCIssued"
																	value="%{(LOCIssued==null || LOCIssued=='')?'N':LOCIssued}"
																	onchange="getLocDeta(this.value)" />
															</div>
														</div>
														<div class="textfield" id="bnkId" style="display: none;">
															<div class="text">
																<s:text name="label.bankName" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="locBankName" id="locBankName"
																	cssClass="inputBox" maxlength="100" />
															</div>

														</div>
														<div class="textfield" id="crdPId" style="display: none;">
															<div class="text">
																<s:text name="label.creditPrd" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="locCreditPrd" id="locCreditPrd"
																	cssClass="inputBox" maxlength="26"
																	onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" />
															</div>

														</div>
														<div class="textfield" id="crdAId" style="display: none;">
															<div class="text">
																<s:text name="label.creditAmt" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="locCreditAmt" id="locCreditAmt"
																	cssClass="inputBox" cssStyle="text-align:right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"
																	maxlength="26" />
															</div>

														</div>
														<div class="textfield" id="benfId" style="display: none;">
															<div class="text">
																<s:text name="label.benficerName" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="locBeneficerName"
																	id="locBeneficerName" cssClass="inputBox"
																	maxlength="100" />
															</div>

														</div>
														<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
														<div class="textfield">
															<div class="text">
																<s:text name="label.perilCovered" />
															</div>
															<div class="tbox">
																<s:select list="perilCoveredlist" id="perilCovered"
																	listValue="DETAIL_NAME" multiple="true" size="1"
																	listKey="TYPE" name="perilCovered" cssClass="inputBoxS"
																	headerKey="0" headerValue="None" />
															</div>
														</div> 
														</s:if>
														<s:else>
														<s:hidden name="perilCovered" id="perilCovered"/>
														</s:else>
														<div class="textfield">
															<div class="text">
																<s:text name="label.pnocDays" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="PNOCDayslist" listValue="DETAIL_NAME"
																	listKey="TYPE" name="pnoc" cssClass="inputBoxS"
																	headerKey="-1" headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.accountingPeriod" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:select list="AccontPeriodlist"
																	listValue="DETAIL_NAME" listKey="TYPE"
																	name="accountingPeriod" cssClass="inputBoxS"
																	headerKey="0" headerValue="---Select---"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.statementDueDays" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="receiptofStatements"
																	cssClass="inputBox" cssStyle="text-align:right;"
																	maxlength="3"
																	onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.paymentDueDays" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="receiptofPayment" cssClass="inputBox"
																	onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);"
																	cssStyle="text-align:right;" maxlength="3" />
															</div>

														</div>
													</div>
												</div>
											</div>
										</div>
										<jsp:include page="/WEB-INF/jsp/common/territoryPopUp.jsp" />
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
													&nbsp;&nbsp;&nbsp;
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.cedantsRetentionType" />
															</div>
															<div class="tbox">
																<s:radio name="cedRetenType"
																	list="#{'A':'Amount','P':'Percentage'}"
																	onclick="setCedRetType(this.value)"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.cedantsRetention" />
															</div>
															<div class="tbox">
																<s:textfield name="cedReten" id="cedReten"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"
																	maxlength="30"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="treatyQS1"
															style="display: none">
															<div class="text">
																<s:text name="label.treatyLimitOC" />
															</div>
															<div class="tbox">
																<s:textfield name="limitOrigCur" id="limitOrigCur"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"
																	onchange="CalculateTreatyQSPML()" maxlength="30"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="factreatyQS1"
															style="display: none">
															<div class="text">
																<s:text name="label.factreatyLimitOC" />
															</div>
															<div class="tbox">
																<s:textfield name="faclimitOrigCur" id="faclimitOrigCur"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"
																	maxlength="30"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="treatySurp1"
															style="display: none">
															<div class="text">
																<s:text name="label.noofline" />
															</div>
															<div class="tbox">
																<s:textfield name="treatynoofLine" id="treatynoofLine"
																	cssClass="inputBox"
																	onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);javascript:this.value=Comma(this.value)"
																	maxlength="30"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="treatySurp2"
															style="display: none">
															<div class="text">
																<s:text name="label.treatyLimitsurplusOC" />
															</div>
															<div class="tbox">
																<s:textfield name="treatyLimitsurplusOC"
																	id="treatyLimitsurplusOC" cssClass="inputBox"
																	cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"
																	onchange="CalculateTreatyQSPML()" maxlength="30"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.treatypml" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No'}" name="pml"
																	id="pml" value="%{pml==null || pml==''?'N':pml}"
																	onchange="GetPML();"
																	disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="pmls" style="display: none">
															<div class="text">
																<s:text name="label.treatypmlper" />
															</div>
															<div class="tbox">
																<s:textfield name="pmlPercent" id="pmlPercent"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);hundredCheck(this.id,this.value);negative(this.id,this.value);allowOneDot(this);"
																	maxlength="10" onblur="CalculateTreatyQSPML(); " />
															</div>
														</div>
														<%-- <div class="textfield" id="treatyQS2" style="display:none">
												<div class="text">
													<s:text name="label.cedantsRetention" />
												</div>
												<div class="tbox">
													<s:textfield name="cedReten" id="cedReten" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>														
											<div class="textfield" id="treatyQS1" style="display:none">
												<div class="text">
													<s:text name="label.treatyLimitOC" />
												</div>
												<div class="tbox">
													<s:textfield name="limitOrigCur" id="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" onchange="CalculateTreatyQSPML()" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>
											<div class="textfield" id="factreatyQS1" style="display:none">
												<div class="text">
													<s:text name="label.factreatyLimitOC" />
												</div>
												<div class="tbox">
													<s:textfield name="faclimitOrigCur" id="faclimitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"  maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>
											<div class="textfield" id="treatySurp1" style="display:none">
												<div class="text">
													<s:text name="label.noofline" />
												</div>
												<div class="tbox">
													<s:textfield name="treatynoofLine" id="treatynoofLine" cssClass="inputBox" onkeyup="allow8DigitDecValues(this);negative(this.id,this.value);allowOneDot(this);javascript:this.value=Comma(this.value)" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield" id="treatySurp2" style="display:none">
												<div class="text">
													<s:text name="label.treatyLimitsurplusOC" />
												</div>
												<div class="tbox">
													<s:textfield name="treatyLimitsurplusOC" id="treatyLimitsurplusOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)"  onchange="CalculateTreatyQSPML()" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.treatypml" />
												</div>
												<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="pml" id="pml" value="%{pml==null || pml==''?'N':pml}" onchange="GetPML();" disabled='%{"Y".equals(disableStatus1)?true:false}' />													
												</div>
											</div>	
											<div class="textfield" id="pmls" style="display:none">
												<div class="text">
													<s:text name="label.treatypmlper" />
												</div>
												<div class="tbox">
													<s:textfield name="pmlPercent" id="pmlPercent" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="checkDecimals(this);hundredCheck(this.id,this.value);negative(this.id,this.value);allowOneDot(this);" maxlength="10" onblur="CalculateTreatyQSPML(); "/>
												</div>
											</div>	
											<%-- <div class="textfield" id="treatyQS2" style="display:none">
												<div class="text">
													<s:text name="label.treatyLimitpmlOC" />
												</div>
												<div class="tbox">
													<s:textfield name="limitOrigCurPml" id="limitOrigCurPml" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield" id="treatySurp3" style="display:none">
												<div class="text">
													<s:text name="label.treatyLimitsurplusOCpml" />
												</div>
												<div class="tbox">
													<s:textfield name="treatyLimitsurplusOCPml" id="treatyLimitsurplusOCPml" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value); negative(this.id,this.value)" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>	--%>

														<div class="textfield">
															<div class="text">
																<s:text name="label.epiOCasperoffer" />
															</div>
															<div class="tbox">
																<s:textfield name="epi_origCur" id="epi_origCur"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value); negative(this.id,this.value);calculation('our');"
																	maxlength="30" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.ourAssessment" />
															</div>
															<div class="tbox">
																<s:textfield name="ourEstimate" cssClass="inputBox"
																	cssStyle="text-align: right;" 
																	onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);calculation('our');hundredCheck(this.id,this.value)" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.ourAssessedEPIOC100" />
															</div>
															<div class="tbox">
																<s:textfield name="epi" id="epi" cssClass="inputBox"
																	cssStyle="text-align: right;"
																	onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);calculation('epi');"
																	/>
																<%--onblur="CalculateEGNPIPML();" --%>
															</div>
														</div>
														<%--<div class="textfield" id="epis" style="display:none">
												<div class="text">
													<s:text name="label.ourAssessedEPIOpml" />
												</div>
												<div class="tbox">
													<s:textfield name="epipml" id="epipml" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" readonly="true" />													
												</div>
											</div>
											 <div class="textfield">
												<div class="text">
													<s:text name="label.xlCostOC" />
												</div>
												<div class="tbox">
													<s:textfield name="xlCost" id="xlCost" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="30" />													
												</div>
											</div>--%>

														<div class="textfield">
															<div class="text">
																<s:text name="label.proposalStatus" />
															</div>
															<div class="tbox">
																<s:if
																	test="contNo == '' || contNo == null || !'renewal'.equals(flagTest)">
																	<s:select list="proposallist" name="proStatus"
																		id="proStatus" cssClass="inputBoxS"
																		onchange="GetShareSigned(this.value)" headerKey=""
																		headerValue="--Select--" listKey="TYPE"
																		listValue="DETAIL_NAME"
																		disabled='%{("Y".equals(prodisableStatus))?true:false}' />
																	<s:hidden name="prodisableStatus"></s:hidden>
																</s:if>
																<s:else>
																	<s:select list="proposallist" name="proStatus"
																		id="proStatus" cssClass="inputBoxS"
																		onchange="GetShareSigned(this.value)" headerKey=""
																		headerValue="--Select--" listKey="TYPE"
																		listValue="DETAIL_NAME" disabled="true" />
																</s:else>
																<%-- <s:select name="proStatus" list="proposallist" listValue="DETAIL_NAME" listKey="TYPE" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="GetShareSigned(this.value)" disabled="%{amend_Id_Mode!=null?true:false}" />
												--%>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.shareWrittenP" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="shareWritt" id="shareWritt"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);hundredCheck(this.id,this.value);allowOneDot(this);"
																	maxlength="8" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.shareSignedP" />
																&nbsp;
																<sup style="color: red;">
																	#
																</sup>
															</div>
															<div class="tbox">
																<s:textfield name="sharSign" id="sharSign"
																	cssClass="inputBox" cssStyle="text-align: right;"
																	onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);hundredCheck(this.id,this.value);allowOneDot(this);"
																	maxlength="8"
																	/><%--disabled='%{"Y".equals(disableStatus1)?true:false}'  --%>
															</div>
														</div>
														<br class="clear">
													</div>
												</div>
											</div>
										</div>
										<div class="boxcontent" >
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Retention" /> 
										&nbsp;
										<sup style="color: red;">
											#
										</sup>
									</div>
									<div class="panel-body">
									<div class="textfield">
										<div class="text">
											<s:text name="label.retentionYN" />
										</div>
										<div class="tbox">
											<s:radio list="#{'Y':'Yes','N':'No'}" name="retentionYN" onchange="getRetentionDetails(this.value);" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
										</div>
									</div>
									<br class="clear"/>
									<div class="boxcontent">
									<jsp:include page="/WEB-INF/jsp/common/retentionPopUp.jsp" />
									</div>
									</div>
								</div>
							</div>
							<s:if test="contractTypelist.size()>0">
							<div class="boxcontent" >
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
										Contract Details
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div>
									<table class="footable" id="Contract" width="100%">
									<thead>
									<tr>
										<th style="text-align: center; vertical-align: middle;" class="no-sort" width="3%"><s:text name="Select" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Proposal No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Broker Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Insured Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="UnderWriter" /></th>
										<th style="text-align: center; vertical-align: middle;" width="3%"><s:text name="View" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="contractTypelist" var="list" status="stat">
											<tr>
												<td style="text-align: center; vertical-align: middle;" ><input type="radio" name="contractListVal" id="contract${RSK_CONTRACT_NO}" title="" value='${RSK_CONTRACT_NO}' />   </td>
												<td  >${RSK_CONTRACT_NO}</td>
												<td  >${RSK_PROPOSAL_NUMBER}</td>
												<td >${RSK_BROKERID}</td>
												<td  >${RSK_INSURED_NAME}</td>
												<td  >${RSK_UNDERWRITTER}</td>
												<td  >
												<input type="button" class="btn btn-sm btn-info" value="V" style="cursor: pointer;" onclick="getViewContractDetails('${RSK_PROPOSAL_NUMBER}','${RSK_ENDORSEMENT_NO}')" />
												</td>
											</tr>
										</s:iterator>
										<tr>
											<td style="text-align: center; vertical-align: middle;" ><input type="radio" name="contractListVal" id="contractNone" title="" value='None' />   </td>
											<td  >None</td>
											<td  ></td>
											<td  ></td>
											<td  ></td>
											<td  ></td>
											<td  ></td>
										</tr>
									</tbody>
									</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							</s:if>
								<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
							</div>
					
									<div class="tablerow">
										<div class="boxcontent" align="center">
											<s:if test='amend_Id_Mode ==null ||"".equals(amend_Id_Mode) '>
												<s:if test='proposal_no == null ||"".equals(proposal_no) '>
													<s:if
														test='layerProposalNo == null ||"".equals(layerProposalNo) '>
														<s:if test='"renewal".equals(flagTest)'>
															<input type="button" value="Back"
																class="btn btn-sm btn-danger" id="mybutton"
																onClick="destroyPopUps();AmendIdBack()" />
														</s:if>
													</s:if>

													<s:hidden name="ceddingcompanyBack" value="%{cedingCo}" />
													<%-- request.setAttribute("LayerMode","Yes");  --%>
													<s:if test='"renewal".equals(flagTest)'>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger" id="mybutton"
															onClick="destroyPopUps();AmendIdBack()" />
													</s:if>
													<s:else>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger"
															onClick="destroyPopUps();FunctionEditCancel()" />
													</s:else>
													<input type="button" value="Save"
														class="btn btn-sm btn-success"
														onclick="disableForm(this.form,false,'');destroyPopUps();FunctionSaveOption()" />
													<input type="button" value="Next"
														class="btn btn-sm btn-warning"
														onclick="disableForm(this.form,false,'');destroyPopUps();next()" />
												</s:if>
												<s:else>
													<s:if
														test='"renewal".equals(flagTest) || "Renewal".equals(proposalReference) || "Layer".equals(proposalReference) '>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger"
															onClick="destroyPopUps();return FunctionCancel();" />
														<s:hidden name="proposalNo1" id="proposalNo1" />
													</s:if>
													<s:elseif test='"R".equals(proStatus)'>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger"
															onClick="destroyPopUps();FunctionRejectCancel()" />
													</s:elseif>
													<s:elseif test='"N".equals(proStatus)'>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger"
															onClick="destroyPopUps();FunctionNotTakenCancel()" />
													</s:elseif>
													<s:else>
														<input type="button" value="Cancel"
															class="btn btn-sm btn-danger"
															onClick="destroyPopUps();FunctionEditCancel()" />
													</s:else>
													<%--<s:if test='"renewal".equals(flagTest)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="FunctionRejectCancel()" />
											<s:hidden name="proposalNo1" id="proposalNo1"/>
										</s:if>--%>
													<input type="button" value="Save"
														class="btn btn-sm btn-success"
														onclick="disableForm(this.form,false,'');destroyPopUps();FunctionSaveOption()" />
													<input type="button" value="Next"
														class="btn btn-sm btn-warning"
														onclick="disableForm(this.form,false,'');destroyPopUps();funEditSubmit()" />
												</s:else>
											</s:if>
											<s:else>
												<input type="button" value="Cancel"
													class="btn btn-sm btn-danger" id="mybutton"
													onClick="AmendIdBack()" />
												<!--<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  />
									-->
												<input type="button" value="Next"
													class="btn btn-sm btn-warning"
													onclick="disableForm(this.form,false,'');destroyPopUps();funAmendsubmit()" />
											</s:else>
										</div>
									</div>
								</div>
								<s:hidden name="flag" id="flag" />
								<s:hidden name="menuId" id="menuId" />
								<s:hidden name="proposalno" />
								<s:hidden name="CustomerId" value="cedingCo" />
								<s:hidden name="layerProposalNo" />
								<%--<s:hidden name="brokerage" />
								<s:hidden name="tax" />--%>
								<s:hidden name="contractno1" />
								<s:hidden name="renewal_contract_no" />
								<s:hidden name="renewalFlag" />
								<s:hidden name="lay1" />
								<s:hidden name="baseLayer" />
								<s:hidden name="baseLoginID" />
								<s:hidden name="amendId" />
								<%--<s:hidden name="contractNo" value="%{contNo}" />
									<s:hidden name="contNo"/>--%>
								<s:hidden name="edit" />
								<s:hidden name="cedType" id="cedType" />
								<s:hidden name="commissionQ_SAmt" />
								<s:hidden name="commission_surpAmt" />
								<s:hidden name="endorsmentno" />
								<s:hidden name="proposalNo" id="proposalNo"
									value="%{proposal_no}" />
								<s:hidden name="mode" />
								<s:hidden name="countryExcludedList" id="countryExcludedList" />
								<s:hidden name="countryIncludedList" id="countryIncludedList" />
								<s:hidden name="renewalProposalNo" id="renewalProposalNo" />
								<s:hidden name="proposalReference" id="proposalReference" />
								<s:hidden name="preVal" id="preVal"/>
								<s:hidden name="prePerilVal" id="prePerilVal"/>
								<s:hidden name="reMode" id="reMode"/>
								<s:hidden name="renewalEditMode" id="renewalEditMode"/>
								
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

toggleVesselBlock('<s:property value="departId"/>');
function toggleVesselBlock(value){
	if( value=="2" || value=="10")
	 {
	 	document.proportional.limitPerVesselOC.value=document.proportional.limitPerVesselOC.value;
	 	document.proportional.limitPerLocationOC.value=document.proportional.limitPerVesselOC.value;  
		document.getElementById('VesselBlock').style.display='block';
	 }   
	 else{ 
	 	document.proportional.limitPerVesselOC.value='';
	 	document.proportional.limitPerLocationOC.value='';  
	 	document.getElementById('VesselBlock').style.display='none';
	 }
	 
	 
}
 function getAjax(value,id) {
        $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=subclass',
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#subProfit_center').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
          		  var val = $('#subProfit_center').val();
		          var val1 =document.getElementById("preVal").value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#subProfit_center").multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#subProfit_center").val(val);
		           $("#subProfit_center").multiselect("refresh");
		           document.getElementById("preVal").value = '';
		          }
		          else if (val !=null && val[0]=='ALL' ) {
		          	$("#subProfit_center").multiselect('clearSelection');
		          	$("#subProfit_center").val('ALL');
		          	 $("#subProfit_center").multiselect("refresh");
		          	 document.getElementById("preVal").value = 'ALL';
		          }
		          else if(val== null || val[0]==''){
		          $("#subProfit_center").multiselect('clearSelection');
		          $("#subProfit_center").multiselect("refresh");
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
<%--function getAjax(value,id)
{
	var URL='${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=subclass';
	postRequest(URL,'subclass');
}--%>
function next(){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		document.proportional.action="checkRiskDetails.action";
		document.proportional.submit();
}
function funsubmit(){
	dffDateAlert();
	if(chkAccAmt()){
	<s:if test="RenewalMode != null">	
		 var t1=document.getElementById("RenewalDate").value;
		 var t2=document.getElementById("incepDate").value;
		 var one_day = 1000 * 60 * 60 * 24;
		 var x=t1.split("-");     
		 var y=t2.split("-");
		 var date1=new Date(x[2],(x[1]-1),x[0]);  
		 var date2=new Date(y[2],(y[1]-1),y[0]);
		 var month1=x[1]-1;
		 var month2=y[1]-1;  
		 var Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
		 if(Diff<=0){
			 alert("Inception Date Invalid ..You are In Renewal Mode")
		 }
		 else{
		 	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		 	document.proportional.action="${pageContext.request.contextPath}/checkRiskDetails.action";
		 	document.proportional.submit();
		 }
	</s:if>
	<s:else>
			replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');		
			document.proportional.action="${pageContext.request.contextPath}/checkRiskDetails.action";
			document.proportional.submit();
	</s:else>
	}
}


function FunctionEdit(){	
	document.proportional.action="${pageContext.request.contextPath}/EditModeRiskDetails";
	document.proportional.submit();
}

function funEditSubmit(){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
	if(chkAccAmt()){
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
	document.proportional.action="FirstPageUpdateModeRiskDetails.action";
	document.proportional.submit();
	}
}

function FunctionSaveOption(){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		document.proportional.action="FirstPageSaveMethodRiskDetails.action";
		document.proportional.submit();
}

function FunctionView(){
	document.proportional.action="${pageContext.request.contextPath}/ViewMethodRiskDetails.action"
	document.proportional.submit();
}
function funAmendsubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		document.proportional.action="FirstPageUpdateModeRiskDetails.action";
		document.proportional.submit();
}
function FunctionAmendSaveOption(){
	document.proportional.action="${pageContext.request.contextPath}/AmednIDFirstPageSaveMathodRiskDetails.action";
	document.proportional.submit();
}

function FunctionEditCancel(){
	document.proportional.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}
function FunctionRejectCancel(){
	document.proportional.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}
function FunctionCancel(){
	var val = document.getElementById("proposalReference").value;
	document.getElementById("proposal_no").disabled=false;
	var no =document.getElementById("proposal_no").value;
	var old = document.getElementById("renewalProposalNo").value;
	if('Renewal'==val || 'Layer'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("proposalNo").value = old;
		document.getElementById("renewalProposalNo").value=no;
			document.proportional.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.proportional.submit();
			}
			else {
			document.getElementById("proposal_no").disabled=true;
				 return false; 
			}
		}
		else{
			document.proportional.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.proportional.submit();
		}
	
}

function FunctionNotTakenCancel(){
	
	document.proportional.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}

function AmendIdBack(){
	document.getElementById("mybutton").style.display = "none";
	var no = document.getElementById("proposal_no").value;
	
	//document.proportional.action = "${pageContext.request.contextPath}/InitPortfolio.do?proposalNo="+no+"&endtMode=endorsment&endorsementStatus=N";
	document.proportional.action = "${pageContext.request.contextPath}/InitPortfolio.do?endtMode=endorsment&endorsementStatus=N";
	document.proportional.submit();
}

function calculation(type){
	var limit=document.proportional.limitOrigCur.value;
	limit=limit.replace(new RegExp(',', 'g'),'');
	var Epi100=document.proportional.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');
	var ourEsitamate=document.proportional.ourEstimate.value;
	if('our'==type){
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" ){
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.proportional.epi.value=Comma(finalvalue.toFixed(2));
	}else
		document.proportional.epi.value='0';
		}
	else if('epi'==type){
			var Epi=document.proportional.epi.value;
			Epi=Epi.replace(new RegExp(',', 'g'),'');
		if(Epi100!=null && Epi100!="" && Epi!=null && Epi!="" ){
			var finalvalue=(parseFloat(Epi)*100)/parseFloat(Epi100);
			document.proportional.ourEstimate.value=Comma(finalvalue.toFixed(4));
		}
		}
}

function GetShareSigned(value){
		if(value=='A'){
		document.proportional.sharSign.disabled=false;
		}
		else  {
		document.proportional.sharSign.value='';
		document.proportional.sharSign.disabled=true;
		}
}
getTreatyType('<s:property value="treatyType"/>');
function getTreatyType(val){
if(val=="1"){
		document.getElementById('treatyQS1').style.display="inline";
		document.getElementById('treatySurp1').style.display="none";
		document.getElementById('treatySurp2').style.display="none";
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
		//document.getElementById('treatySurp3').style.display="none";
		document.getElementById('limitOrigCur').value=document.proportional.limitOrigCur.value;
		//document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
		document.getElementById('treatynoofLine').value="";
		document.getElementById('treatyLimitsurplusOC').value="";
		//document.getElementById('treatyLimitsurplusOCPml').value="";
	}
else if(val=="2"){
		document.getElementById('treatyQS1').style.display="none";
		document.getElementById('treatySurp1').style.display="inline";
		//document.getElementById('treatyQS2').style.display="none";
		document.getElementById('treatySurp2').style.display="inline";
		//document.getElementById('treatySurp3').style.display="inline";
		document.getElementById('limitOrigCur').value="";
		//document.getElementById('limitOrigCurPml').value="";
		document.getElementById('treatynoofLine').value=document.proportional.treatynoofLine.value;
		document.getElementById('treatyLimitsurplusOC').value=document.proportional.treatyLimitsurplusOC.value;
		//document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
	}
	else if(val=="3"){
		document.getElementById('treatyQS1').style.display="inline";
		document.getElementById('treatySurp1').style.display="inline";
		//document.getElementById('treatyQS2').style.display="inline";
		document.getElementById('treatySurp2').style.display="inline";
		//document.getElementById('treatySurp3').style.display="inline";
		document.getElementById('limitOrigCur').value=document.proportional.limitOrigCur.value;
		//document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
		document.getElementById('treatynoofLine').value=document.proportional.treatynoofLine.value;
		document.getElementById('treatyLimitsurplusOC').value=document.proportional.treatyLimitsurplusOC.value;
		//document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
	}
	else if(val=="4" || val=="5" ){
		document.getElementById('treatyQS1').style.display="none";
		document.getElementById('treatySurp1').style.display="none";
		document.getElementById('treatySurp2').style.display="none";
		document.getElementById('limitOrigCur').value='';
		document.getElementById('treatynoofLine').value='';
		document.getElementById('treatyLimitsurplusOC').value='';
		document.getElementById('factreatyQS1').style.display="inline";
	}
}
function GetExchangeRate() {
		var incDate=document.forms['proportional'].incepDate.value;
		var accDate=document.forms['proportional'].accDate.value;
		var Currecny=document.proportional.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !="")){
	 		var URL='${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else{
			document.proportional.exchRate.value='';
		}	
}

/*function CalculateEpi() {	
	var a=document.proportional.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.proportional.adjRate.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.proportional.epi.value=Comma(c.toFixed(2));
	}else {
		document.proportional.epi.value='';
	}
}*/

function functionview(id){
	var cedding="";
	if(id==1) {
		cedding=document.getElementById("CeddingId").value;
	}
	else {
		cedding=document.getElementById("BrokerId").value;
	}
	var URL ='${pageContext.request.contextPath}/ViewModeCeding?Mode=PopUp&ceddingcompany='+cedding;
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
		
function Commas(value) {
	if(value=="2") {
		document.proportional.epi_origCur.value=Comma(document.proportional.epi_origCur.value);
		document.proportional.maxLimit_Product.value=Comma(document.proportional.maxLimit_Product.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.treatyLimitsurplusOC.value=Comma(document.proportional.treatyLimitsurplusOC.value);
		//document.proportional.xlCost.value=Comma(document.proportional.xlCost.value);
		document.proportional.cedReten.value=Comma(document.proportional.cedReten.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		//document.proportional.limitOrigCurPml.value=Comma(document.proportional.limitOrigCurPml.value);
		//document.proportional.treatyLimitsurplusOCPml.value=Comma(document.proportional.treatyLimitsurplusOCPml.value);
		//document.proportional.epipml.value=Comma(document.proportional.epipml.value);
		if(document.proportional.limitPerVesselOC.value!=null && document.proportional.limitPerVesselOC.value!="") {
			document.proportional.limitPerVesselOC.value=Comma(document.proportional.limitPerVesselOC.value);
		}
		if(document.proportional.limitPerLocationOC.value!=null && document.proportional.limitPerLocationOC.value!="") {
			document.proportional.limitPerLocationOC.value=Comma(document.proportional.limitPerLocationOC.value);
		}
	} else if(value=="3") {
		document.proportional.deduc_hunPercent.value=Comma(document.proportional.deduc_hunPercent.value);
		document.proportional.maxLimit_Product.value=Comma(document.proportional.maxLimit_Product.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.subPremium.value=Comma(document.proportional.subPremium.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		document.proportional.m_dPremium.value=Comma(document.proportional.m_dPremium.value);
		if(document.proportional.limitPerVesselOC.value!=null && document.proportional.limitPerVesselOC.value!="") {
			document.proportional.limitPerVesselOC.value=Comma(document.proportional.limitPerVesselOC.value);
		}
		if(document.proportional.limitPerLocationOC.value!=null && document.proportional.limitPerLocationOC.value!="") {
			document.proportional.limitPerLocationOC.value=Comma(document.proportional.limitPerLocationOC.value);
		}
	} else if(value=="4") {
		document.proportional.epi_origCur.value=Comma(document.proportional.epi_origCur.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		//document.proportional.xlCost.value=Comma(document.proportional.xlCost.value);
	} else if(value=="5") {
		document.proportional.deduc_hunPercent.value=Comma(document.proportional.deduc_hunPercent.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.subPremium.value=Comma(document.proportional.subPremium.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		document.proportional.m_dPremium.value=Comma(document.proportional.m_dPremium.value);
	}
}

/*********************************/
var type='<s:property value="spRetro"/>';
if(type=='N'){
GetSp('<s:property value="spRetro"/>');
}
function GetSp(id){	
	if(id=='Y'){
		document.proportional.no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N'){
		document.getElementById("NoOFInsur").readOnly=true;
		document.proportional.no_Insurer.value="0";
	}
}

function functionDate(){
var	inceptionDate=document.proportional.incepDate.value;
	if(inceptionDate!=""){
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(inceptionDate.match(dateformat)){
		var splitVal = inceptionDate.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
                    status='fail';  
                  }
              }
          }
          else
          {
             status='fail';  
          }
		if(status!='fail'){
		if(splitVal[0] .length<2){
		 splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		inceptionDate = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		document.proportional.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.proportional.uwYear.value=date.getFullYear();
		//var date1=new Date((date.getFullYear()+1)+"/"+date.getMonth()+"/"+(date.getDate()));
		
		date.setFullYear(date.getFullYear()+1);
		date.setDate(date.getDate()-1);
		var d;
		var m
		if(parseInt(date.getDate())<10) {
			d="0"+date.getDate();
		}else {
			d=date.getDate();
		}
		if(parseInt(date.getMonth()+1)==0) {
			m="12"
		}else if((parseInt(date.getMonth())+1)<10) {
			m="0"+(parseInt(date.getMonth())+1);
		}else {
			m=(parseInt(date.getMonth())+1);
		}
		var y=date.getFullYear();
		document.proportional.expDate.value=d+"/"+m+"/"+y;
		
	var URL='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&dropDown=yearId';
	postRequest(URL,'yearId');
	}
	}
}


function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
  // ex input "2010-01-18" 
	return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}

function dffDateAlert() {
	var inception=document.proportional.incepDate.value;
	var date=new Date(reformatDate(inception));
	date.setFullYear(date.getFullYear()+1);
	date.setDate(date.getDate());
	var d;
	var m
	if(parseInt(date.getDate())<10) {
		d="0"+date.getDate();
	}else {
		d=date.getDate();
	}
	if(parseInt(date.getMonth()+1)==0) {
		m="12"
	} else if((parseInt(date.getMonth())+1)<10) {
		m="0"+(parseInt(date.getMonth())+1);
	}else {
		m=(parseInt(date.getMonth())+1);
	}
	var y=date.getFullYear();					
	var expery=(document.proportional.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
	if(diffDays>0){
		alert('Expiry Date More than One Year');
	}
}

function chkAccAmt(){
	var limitOC=document.proportional.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.proportional.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.proportional.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.proportional.proStatus.value=="A"){	 
		cedPer=document.proportional.sharSign.value;
	}else{
		cedPer=document.proportional.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.proportional.sharSign.value;
	</s:if>	
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit){
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt){
				<s:if test='contNo == "" && contNo == null'>				
					document.proportional.proStatus.value='P';
					document.proportional.sharSign.value='';
				</s:if>
			}else{
				 return false; 
			}
		}
	}
	return true;
}

function setCedRetType(type){
	document.getElementById("cedType").value=type;
}

function toggleXLCostOC(retroType){
	if(retroType=="SR"){
		//document.proportional.xlCost.value="0";
		document.proportional.xlCost.readOnly=true;
		document.proportional.insuredName.readOnly=false; 
	}else{
		//document.proportional.xlCost.value="";
		//document.proportional.xlCost.readOnly=false;
		document.proportional.insuredName.value="";
		document.proportional.insuredName.readOnly=true; 
	}
}

<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
	enableForm1(document.proportional,true,'<s:property value="%{fields}"/>');
	//document.getElementById("retDelete").disabled = true;
	document.getElementById("retAdd").disabled = true;	
</s:if>

if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.proportional.underwriter.value);
}
function getUWLimit(value){
  // var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitRiskDetails.action?underwriter='+value+'&dropDown=uwc2';
	postRequest(URL,'uwc2');
}
GetPML();
function GetPML(){
pml = document.proportional.pml.value;
var treatyType = document.proportional.treatyType.value;
	if(pml=="Y"){
	
     		document.getElementById('pmls').style.display = 'block';
     		//document.getElementById('epis').style.display = 'block';
     		document.getElementById('pmlPercent').value=document.proportional.pmlPercent.value;
     		//document.getElementById('epipml').value=document.proportional.epipml.value;
     		<%--if(treatyType =="1"){
     		document.getElementById('treatyQS2').style.display = 'block';
     		document.getElementById('treatySurp3').style.display = 'none';
     		document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
     		document.getElementById('treatyLimitsurplusOCPml').value="";
     		
     		}
     		else if(treatyType =="2"){
     		document.getElementById('treatySurp3').style.display = 'block';
     		document.getElementById('treatyQS2').style.display = 'none';
     		document.getElementById('limitOrigCurPml').value="";
     		document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
     		}
     		else if(treatyType =="3"){
     		document.getElementById('treatySurp3').style.display = 'block';
     		document.getElementById('treatyQS2').style.display = 'block';
     		document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
     		document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
     		
     		}--%>
	       	} 
	 else if(pml=="N"){
       	 	document.getElementById('pmls').style.display = 'none';
       	 	//document.getElementById('epis').style.display = 'none';
       	 	//document.getElementById('treatyQS2').style.display = 'none';
     		//document.getElementById('treatySurp3').style.display = 'none';
     		//document.getElementById('limitOrigCurPml').value="";
     		//document.getElementById('treatyLimitsurplusOCPml').value="";
     		document.getElementById('pmlPercent').value="";
     		//document.getElementById('epipml').value="";
	       	}
}

function CalculateEGNPIPML() {	
	<%--var a =document.proportional.epi.value; 
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.proportional.pmlPercent.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var pmlPer=parseFloat(b)/100;
		
		var c=parseFloat(a)*pmlPer;
		document.proportional.epipml.value=Comma(c.toFixed(2));
	}else {
		document.proportional.epipml.value='';
	}--%>
}

function CalculateTreatyQSPML(){
<%--	var type = document.proportional.treatyType.value; 
	var b=document.proportional.pmlPercent.value;
	var pml = document.proportional.pml.value;
	var a = 0;
	var c=0;
	if('Y'==pml){
	if(type=='1'||'3'==type){
     a =document.proportional.limitOrigCur.value; 
     a=a.replace(new RegExp(',', 'g'),'');
   
		if(a!=null && a!="" && b!=null && b!="") {
			var pmlPer=parseFloat(b)/100;
			
			var d=parseFloat(a)*pmlPer;
			document.proportional.limitOrigCurPml.value=Comma(d.toFixed(2));
		}else {
			document.proportional.limitOrigCurPml.value='0';
		}
	}
	if(type=='2'||'3'==type){
	 c =document.proportional.treatyLimitsurplusOC.value; 
     c=c.replace(new RegExp(',', 'g'),'');
   
		if(c!=null && c!="" && b!=null && b!="") {
			var pmlPer=parseFloat(b)/100;
			
			var e=parseFloat(c)*pmlPer;
			document.proportional.treatyLimitsurplusOCPml.value=Comma(e.toFixed(2));
		}else {
			document.proportional.treatyLimitsurplusOCPml.value='0';
		}
	}
	}--%>
}
<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
$(document).ready(function() {     
    $('#perilCovered').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
          var val = $('#perilCovered').val();
          var val1 =document.getElementById("prePerilVal").value;
          if(val1!= null && val1=='0' && val !=null && val[1]!=undefined ){
          $("#perilCovered").multiselect('clearSelection');
          val = removeElementsWithValue(val, '0');
          $("#perilCovered").val(val);
           $("#perilCovered").multiselect("refresh");
           document.getElementById("prePerilVal").value = '';
          }
          else if (val !=null && val[0]=='0' ) {
          	$("#perilCovered").multiselect('clearSelection');
          	$("#perilCovered").val('0');
          	 $("#perilCovered").multiselect("refresh");
          	 document.getElementById("prePerilVal").value = '0';
          }
          else if(val== null || val[0]==''){
          $("#perilCovered").multiselect('clearSelection');
          $("#perilCovered").multiselect("refresh");
          document.getElementById("prePerilVal").value = '';
          }
      	}                     
    }); 
    
     <s:if test='perilCovered!=null && !"".equals(perilCovered)'>	
 		var uwgrade='<s:property value="perilCovered"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#perilCovered").val(dataArray);
		 $("#perilCovered").multiselect("refresh");
	</s:if>    
           
});
</s:if>

  function hundredCheck(id,val){
	if(parseFloat(val)>100){
	alert("This field value is not exceed 100");
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
getRateField('<s:property value="proposalType"/>');
function getRateField(val){
if( val=='R' || val=='H'){
document.getElementById('ratId').style.display='inline';

}else{

document.getElementById('runoffYear').value='0';
document.getElementById('ratId').style.display='none';
}
}
getLocDeta('<s:property value="LOCIssued"/>');
function getLocDeta(id){
	if(id == 'Y'){
		document.getElementById('bnkId').style.display='inline';
		document.getElementById('crdPId').style.display='inline';
		document.getElementById('crdAId').style.display='inline';
		document.getElementById('benfId').style.display='inline';
	}else{
		document.getElementById('locBankName').value='';
		document.getElementById('locCreditPrd').value='';
		document.getElementById('locCreditAmt').value='';
		document.getElementById('locBeneficerName').value='';
		document.getElementById('bnkId').style.display='none';
		document.getElementById('crdPId').style.display='none';
		document.getElementById('crdAId').style.display='none';
		document.getElementById('benfId').style.display='none';
	}
}
 $(document).ready(function() {     
    $('#subProfit_center').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
          var val = $('#subProfit_center').val();
          var val1 =document.getElementById("preVal").value;
          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
          $("#subProfit_center").multiselect('clearSelection');
          val = removeElementsWithValue(val, 'ALL');
          $("#subProfit_center").val(val);
           $("#subProfit_center").multiselect("refresh");
           document.getElementById("preVal").value = '';
          }
          else if (val !=null && val[0]=='ALL' ) {
          	$("#subProfit_center").multiselect('clearSelection');
          	$("#subProfit_center").val('ALL');
          	 $("#subProfit_center").multiselect("refresh");
          	 document.getElementById("preVal").value = 'ALL';
          }
          else if(val== null || val[0]==''){
          $("#subProfit_center").multiselect('clearSelection');
          $("#subProfit_center").multiselect("refresh");
          document.getElementById("preVal").value = '';
          }
      	}                     
    }); 
    
     <s:if test='subProfit_center!=null && !"".equals(subProfit_center)'>	
 		var uwgrade='<s:property value="subProfit_center"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#subProfit_center").val(dataArray);
		 $("#subProfit_center").multiselect("refresh");
	</s:if>    
           
});
function CedentRetention(){
	var contrctNo = document.getElementById("contNo").value;
	var proposalNO = document.getElementById("proposalNo").value;
	//var val = document.getElementById("claimPaymentNo").value;
	//var val1 = document.getElementById("creditAmountCLC").value;
	//var val2 = document.getElementById("creditAmountCLD").value;
	//var val3 = document.getElementById("CLCsettlementRate").value;
	
		//var currencyid = document.getElementById("baseCurrencyId").value;
		//var currencyid = document.getElementById("currId").value;
		var URL ='${pageContext.request.contextPath}/getCedentRetentionRiskDetails.action?contNo='+contrctNo+'&proposal_no='+proposalNo;
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
getRetentionDetails('<s:property value="retentionYN"/>');
function getRetentionDetails(val){
	if("Y"==val){
		document.getElementById("retentionid").style.display='inline';
	}
	else{
		document.getElementById("retentionid").style.display='none';
	}

}
function getRetention(){
var cedingNo=document.getElementById("CeddingId").value;
var uwYear=document.getElementById("uwYear").value;
if(cedingNo!=null && cedingNo!='' &&  uwYear!=null && uwYear!=''){
postFormRequest('${pageContext.request.contextPath}/GetRetentionDetailsRiskDetails.action?dropDown=retention', "retentionid", "proportional");
}
}
 function getViewContractDetails(proposalNo,amendId) {
		var URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
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
	contractListValCheck();
	function contractListValCheck(){
	var val = '<s:property value="contractListVal"/>';
	if(val!="" && val !=null){
	document.getElementById("contract"+val).checked = true;
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
