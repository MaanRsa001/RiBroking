<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
	</script >
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
	    $( "#inceptionDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#expiryDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#accountDate" ).datepicker({
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
 	min-width: 80px;
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
<body onload="Commas(),toggle('<s:property value="sipml" />'),setCedRetType('<s:property value="cedRetenType" />')">

<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="facultative" id="facultative" theme="simple" action="" method="post" autocomplete="off">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow" align="center">
							<s:if test="DepartmentId == 2">
								<s:text name="label.facultativeAviation" />
							</s:if>
							<s:elseif test="DepartmentId == 4">
								<s:text name="label.facMarine" />
							</s:elseif>
							<s:elseif test="DepartmentId == 7">
								<s:text name="label.facultativeEngineering" />
							</s:elseif>
							<s:elseif test="DepartmentId == 8">
								<s:text name="label.facultativeBonds" />
							</s:elseif>
							<s:elseif test="DepartmentId == 9">
								<s:text name="label.facultativeFire" />
							</s:elseif>
							<s:elseif test="DepartmentId == 10">
								<s:text name="label.facultativeMarineHull" />
							</s:elseif>
							<s:elseif test="DepartmentId == 11">
								<s:text name="label.facultativeMisce" />
							</s:elseif>
							<s:elseif test="DepartmentId == 12">
								<s:text name="label.facultativeMotor" />
							</s:elseif>
							<s:elseif test="DepartmentId == 13">
								<s:text name="label.facultativePolitical" />
							</s:elseif>
							<s:elseif test="DepartmentId == 14">
								<s:text name="label.facultativeLife" />
							</s:elseif>
						</div>
						<s:if test="contractNo != '' && contractNo != null">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<%-- <div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox txtB">
													<s:property value="contractNo"/>
												</div>
											</div>--%>
											<div class="textfield">
												<div class="text">
													<s:text name="label.endorsementType" />
												</div>
												<div class="tbox txtB">
													<s:select list="endosTypelist" name="endorsmenttype" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME"  />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.endorsementNo" />
												</div>
												<div class="tbox txtB">
													<s:property value="endorsmentno"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.proposalNo" />
												</div>
												<div class="tbox">
													<s:textfield name="proposalNo" id="proposalNo" cssClass="inputBox" readonly="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox">
													<s:textfield name="contractNo" cssClass="inputBox" readonly="true" />
												</div>
											</div>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
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
												
												<%-- <s:select list="subProfit_center" name="subProfitCenter" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled='%{(contractNo != "" && contractNo != null)?true:false}'/>--%>
												<s:select list="subProfit_center" name="subProfitCenter" cssClass="inputBoxS" id="subProfitCenter" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled='%{(contractNo != "" && contractNo != null)?true:false}' onchange="getSubDepatmentCover();"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.profitCentre" />
												</div>
												<div class="tbox">
													<s:select list="profit_Center" name="profitCenterCode" cssClass="inputBoxS" headerKey="" headerValue="---Select---" listKey="TMAS_PFC_ID" listValue="TMAS_PFC_NAME" disabled="%{'Renewal'.equals(proposalReference)?true:false}" />
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
												</div>
												<div class="tbox">
													<s:select list="inwardBusinessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="inwardType" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" disabled="%{'Renewal'.equals(proposalReference)?true:false}"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.underwriter" />
												</div>
												<div class="tbox">
													<s:select list="UnderWritter" name="underwriter" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" listKey="UWR_CODE" listValue="UNDERWRITTER"  onchange="getUWLimit(this.value)"/>
												</div>
											</div>
											
											
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwCapacityUGX"/>(<s:property value="shortname"/>)   
												</div>
												<div class="tbox" id="uwc" >
													<s:textfield name="maxiumlimit" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"  />
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
											<div class="textfield">
												<div class="text">
													<s:text name="label.branchLocation" />
												</div>
												<div class="tbox">
													<s:select list="polBr" name="policyBranch" cssClass="inputBoxS" listKey="TMAS_POL_BRANCH_ID" listValue="TMAS_POL_BRANCH_NAME" disabled='%{"Renewal".equals(proposalReference)||(contractNo != "" && contractNo != null)?true:false}' />
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.typebusiness" />
												</div>
												<div class="tbox">
													<s:select list="TypeDropDown" name="type" cssClass="inputBoxS" headerKey="" headerValue="---Select---" listKey="TYPE" listValue="DETAIL_NAME" onchange="toggle();PMlCalculation();getCoverDedutable(this.value);getPLLEnable();getSubDepatmentCover();" disabled='"Renewal".equals(proposalReference) ||"Y".equals(disableStatus1)?true:false'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedingCompany" />
												</div>
												<div class="tbox">
													<div class="inputAppend">	
													<s:select list="CeddingCompany" name="cedingCompany" id="CeddingId" cssClass="pullLeft" listKey="CUSTOMER_ID" listValue="NAME" cssStyle="width: 85%; border:transparent;" headerKey="-1" headerValue="---Select---"  disabled='"Renewal".equals(proposalReference)||"Y".equals(disableStatus1)?true:false' />
													<input type="button"  size="2"  value="..." onclick="ViewCeddingCompany(1)" class="pullRight" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.broker" />
												</div>
												<div class="tbox">
													<div class="inputAppend">															
													<s:select list="brokers" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" listKey="CUSTOMER_ID" listValue="NAME" disabled='"Y".equals(disableStatus1)?true:false'  /><%--RenewalMode!=null ?true: (contractNo!=null || !"".equals(contractNo) ) ?false: ("Y".equals(disableStatus1))?true:false --%>
													<input type="button"  size="2"  value="..." onclick="ViewCeddingCompany(2)" class="pullRight" />
													</div>
												</div>
											</div>
											<div class="textfield"  id="xollay" style="display:none">
												<div class="text">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
												<s:if test='"Renewal".equals(proposalReference) || "Layer".equals(proposalReference)'>
													<s:textfield name="xollayerNo" cssClass="inputBox" cssStyle="text-align: right;"   />
												</s:if>
												<s:else>
													<s:textfield name="xollayerNo" cssClass="inputBox" cssStyle="text-align: right;"   disabled='proposal_no!=null  && ""!=proposal_no?true:false'/>
												</s:else>
												
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
											
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.inceptionDate" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:textfield name="inceptionDate" id="inceptionDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()" readonly="%{prclFlag==true?true:false}" disabled='%{(contractNo != "" && contractNo != null) || "Renewal".equals(proposalReference) ?true:false}' />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.expiryDate" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:textfield name="expiryDate" id="expiryDate"  cssStyle="width: 100%; border:transparent;"  onkeyup="validateSpecialChars(this)"  />
													</div>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYear" />
												</div>
												<div class="tbox" id="yearId">
													<s:select list="yearList" name="year" cssClass="inputBoxS" headerKey="" listKey="YEAR" listValue="YEAR" headerValue="---Select---"  disabled='%{(contractNo != "" && contractNo != null) || "Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.acceptanceDate" />
												</div>
												<div class="tbox">
														<div class="inputAppend">
															<s:textfield name="accountDate" id="accountDate"  cssStyle="width: 100%; border:transparent;"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" disabled='"Y".equals(disableStatus1)?true:false' />	<%--%{("endorsment".equals(mode))?true:false}' --%>
														</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.originalCurrency" />
												</div>
												<div class="tbox" >
													<s:select list="orginalCurrency" name="originalCurrency" cssClass="inputBoxS" headerKey="" headerValue="---Select---" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" onchange="GetExchangeRate()" disabled='%{("Y".equals(disableStatus1))?true:false}' />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.exchangeRate" />
												</div>
												<div class="tbox" id="currencyRate">
													<s:textfield name="usCurrencyRate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.retro" />
												</div>
												<div class="tbox">
													<%--<s:radio list="#{'Y':'Yes','N':'No'}" name="spRetro" onclick="GetSp(this.value)"    disabled='%{("Y".equals(disableStatus1))?true:false}'/>
													<s:textfield name="no_Insurer" id="NoOFInsur" cssClass="inputBox" readonly='spRetro=="Y"?"false":"true"' onkeyup="checkNumbers(this);" maxlength="3"/>--%>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="spRetro" id="spRetro" onclick="GetSp(this.value)" value="%{(spRetro==null || spRetro=='')?'Y':spRetro}"  /><%--disabled='%{("Y".equals(disableStatus1))?true:false}' --%>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.noofRetroContracts" />
												</div>
												<div class="tbox">
													<s:textfield name="no_Insurer" id="NoOFInsur" cssClass="inputBox"  onkeyup="checkNumbers(this);" maxlength="3"/><%-- disabled='"Y".equals(disableStatus1)?true:false' --%>
												</div>
											</div>
											<br class="clear"/>
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
													<s:text name="label.insuredName" />
												</div>
												<div class="tbox">
													<s:textfield name="insuredName" cssClass="inputBox" maxlength="100" readonly='"Y".equals(disableStatus1)?true:false'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.interestCovered" />
												</div>
												<div class="tbox">
													<s:textfield name="interest" cssClass="inputBox" maxlength="500"/>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.city" />
												</div>
												<div class="tbox">
														<s:textfield name="city" cssClass="inputBox" maxlength="50"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.location" />
												</div>
												<div class="tbox">
													<s:textfield name="location" cssClass="inputBox"  maxlength="30"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.latitude" />
												</div>
												<div class="tbox">
													<s:textfield name="latitude" id="latitude" cssClass="inputBox" maxlength="30" onkeyup="allowOneDot(this);middleMinusRestriction(this);allow8DigitDecValues(this);checkLatitudeRange(this.id,this.value)"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.longitude" />
												</div>
												<div class="tbox">
													<s:textfield name="longitude" id="longitude" cssClass="inputBox" maxlength="30" onkeyup="allowOneDot(this);middleMinusRestriction(this);allow8DigitDecValues(this);checkLongitudeRange(this.id,this.value)"/>
												</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.LOCIssued" />
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="locIssued" value="%{(locIssued==null || locIssued=='')?'N':locIssued}"  onchange="getLocDeta(this.value)"/>													
													</div>
												</div>
											<div class="textfield" id="bnkId" style="display:none;">
												<div class="text">
														<s:text name="label.bankName" />
													</div>
													<div class="tbox">													
													<s:textfield name="locBankName" id="locBankName" cssClass="inputBox"  maxlength="100"/>
													</div>
												
											</div>
											<div class="textfield" id="crdPId" style="display:none;">
												<div class="text">
														<s:text name="label.creditPrd" />
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditPrd" id="locCreditPrd" cssClass="inputBox"   maxlength="26" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);"/>
													</div>
												
											</div>
											<div class="textfield" id="crdAId" style="display:none;">
												<div class="text">
														<s:text name="label.creditAmt" />
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditAmt" id="locCreditAmt" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
													</div>
												
											</div>
											<div class="textfield" id="benfId" style="display:none;">
												<div class="text">
														<s:text name="label.benficerName" />
													</div>
													<div class="tbox">													
													<s:textfield name="locBeneficerName" id="locBeneficerName" cssClass="inputBox"   maxlength="100"/>
													</div>
												
											</div>
											
												
											
										<%-- 	<div class="textfield">
												<div class="text">
													<s:text name="label.sIPML" />
												</div>
												<div class="tbox">
													<s:radio list="#{'SI':'SI','PML':'PML','LL':'Loss Limit'}" name="sipml" onclick="toggle()" />
												</div>
											</div>
											<div class="textfield" id="toggle">
												<div class="text">
													<s:text name="label.pMLP" />
												</div>
												<div class="tbox">
													<s:textfield name="pml" id="P2" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="PMlCalculation();SUMCalculation();" maxlength="26"/>
												</div>
											</div>--%>
											
										</div>
									</div>
								</div>
							</div>
							<s:if test='"4".equals(#session.DepartmentId)'>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">													
											
												<div class="textfield">
													<div class="text">
														<s:text name="label.modeOfTransport" />
													</div>
													<div class="tbox">
														<s:select list="modeOfTransports" name="modeOfTransport" cssClass="inputBoxS" headerKey="" headerValue="--Select--" listKey="MODE_TRANSPORT_ID" listValue="TRANSPORT_DESCRIPTION" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="label.vesselName" />
													</div>
													<div class="tbox">
														<s:textfield name="vesselName" cssClass="inputBox" maxlength="200"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="label.vesselAge" />
													</div>
													<div class="tbox">
														<s:textfield name="vesselAge" cssClass="inputBox" onkeyup="checkNumbers(this);" maxlength="3" />
													</div>
												</div>
											<s:if test=' "4".equals(#session.DepartmentId) '>
												<div class="textfield">
													<div class="text">
														<s:text name="label.vesseltonege" />
													</div>
													<div class="tbox">
														<s:textfield name="vessaletonnage" cssClass="inputBox"  onkeyup="checkNumbers(this);" maxlength="26"/>
													</div>
												</div>
											</s:if>
												 <div class="textfield">
													<div class="text">
														<s:text name="label.limitperVesselOC" />
													</div>
													<div class="tbox">
														<s:textfield name="limitPerVesselOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="label.limitperLocationOC" />
													</div>
													<div class="tbox">
														<s:textfield name="limitPerLocationOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
													</div>
												</div>
											
											<br class="clear"/>
											
										</div>
									</div>
								</div>
							</div>
							</s:if>
			<%-- <div class="boxcontent" id="coverdeduct">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										
											<div class="textfield" id="endtlabel1" style="display:none">
												<div class="text">
													<s:text name="label.coverLimitOC" />
												</div>
												<div class="tbox">
																<s:textfield name="deductibleFacXol"
																	id="deductibleFacXol" cssClass="inputBox"
																	cssStyle="text-align:right;"
																	onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value); fnSiOcEntry(this.value);GWPCalculation();SUMCalculation"
																	maxlength="26" 
																	readonly='"Y".equals(disableStatus1)?true:false'/>
															</div>
											</div>	
																							
											<div class="textfield" id="endtlabel2" style="display:none">
												<div class="text">
											<s:if test='"4".equals(#session.DepartmentId)'>
													<s:text name="label.turnsIOC100" />
											</s:if>
											<s:else>
												<s:text name="label.sIOC100" />
											</s:else>
												</div>
												<div class="tbox">
													<s:textfield name="sumInsured" id="sumInsured" cssClass="inputBox" cssStyle="text-align: right;" onblur="PMlCalculation();GWPCalculation();SUMCalculation();" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" disabled='"Y".equals(disableStatus1)?true:false'/>
												</div>
											</div>--%>	
											<s:if test='"RI02".equals(#session.SOURCE_CODE) && "4".equals(#session.DepartmentId)'>
												<div class="boxcontent" >
													<div class="panel panel-primary">											
														<div class="panel-heading">
															&nbsp;&nbsp;&nbsp;
														</div>
														<div class="panel-body">
															<div class="boxcontent">
													<div class="textfield" id="psl" style="display:none">
														<div class="text">
															<s:text name="label.psl100OC" />
														</div>
														<div class="tbox">
															<div id="pmllvalue">
															<s:textfield name="pslOC" id="pslOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" readonly='"Y".equals(disableStatus1)?true:false'/>
															</div>													
														</div>
													</div>
													<div class="textfield" id="pll" style="display:none">
													<div class="text">
														<s:text name="label.pll100OC" />
													</div>
													<div class="tbox">
														<div id="pmllvalue">
														<s:textfield name="pllOC" id="pllOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" readonly='"Y".equals(disableStatus1)?true:false'/>
														</div>													
													</div>
												</div>
												<div class="textfield" id="pbl" style="display:none">
													<div class="text">
														<s:text name="label.pbl100OC" />
													</div>
													<div class="tbox">
														<div id="pmllvalue">
														<s:textfield name="pblOC" id="pblOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" readonly='"Y".equals(disableStatus1)?true:false'/>
														</div>													
													</div>
												</div>
												<div class="textfield" id="pml" style="display:none">
													<div class="text">
														<s:text name="label.pML100OC" />
													</div>
													<div class="tbox">
														<div id="pmllvalue">
														<s:textfield name="pmll" id="P1" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" readonly='"Y".equals(disableStatus1)?true:false'/>
														</div>													
													</div>
												</div>
												</div>
												</div>
												</div>
												</div>
												</s:if>
											
											<%-- <div class="textfield" id="endtlabel" >
												<div class="text">
													<s:text name="label.deductibleOC" />
												</div>
												<div class="tbox">
													<s:textfield name="deductible" id="deductible" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value); " maxlength="26" readonly='"Y".equals(disableStatus1)?true:false'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.premiumRate" />
												</div>
												<div class="tbox">
													<s:textfield name="premiumrate" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);GWPCalculation()" maxlength="26"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.gWPIOC100" />
												</div>
												<div class="tbox">
													<s:textfield name="gwpi" id="gwpiFac" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"  maxlength="26"/>		<%--onblur="PMlCalculation();"										
												</div>
											</div>
											
											<br class="clear">
										</div>
									</div>
								</div>
							</div> --%>
							<div class="boxcontent" id="coverdeduct"  style="display:none">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div>
										<div id="xolnewgenid" class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
										<table class="footable" width="100%" id="xolnewgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.subClass" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premiumRate" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.gWPIOC100" /> </th>
																<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
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
																	<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}" disabled="0==(#stat.count-1)?true:false"/>
																	<s:hidden name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxXOlCover(this.value,'xolcoverdepartid%{#stat.count-1}',%{#stat.count-1})"/>
																	</s:else>
																	</td>
																	
																	<td id="xolcoverdepartid<s:property value='%{#stat.count-1}'/>">
																	<s:if test='0==(#stat.count-1)'>
																	<s:select list="subProfit_center" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false"/>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" onchange="getAllValue(%{#stat.count-1})" />
																	</s:else>
																	</td>
																	
																	
																	
																	 <td>
																	<s:textfield name="xolcoverLimitOC[%{#stat.count-1}]" id="xolcoverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getxolCoverGWPI(%{#stat.count-1});getTotalxolValue();" maxlength="30"    /><%--disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
																	</td>
																	
																	<td>
																	<s:textfield name="xoldeductableLimitOC[%{#stat.count-1}]" id="xoldeductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalDeductable();" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	
																	<td><s:textfield name="xolpremiumRateList[%{#stat.count-1}]" id="xolpremiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);allowOneDot(this); middleMinusRestrictionNeg(this);hundredCheck(this.id,this.value);Itnegative(this.id,this.value);getxolCoverGWPI(%{#stat.count-1});getTotalGWPI();" maxlength="30"  /></td>
																	<td><s:textfield name="xolgwpiOC[%{#stat.count-1}]" id="xolgwpiOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalGWPI();" maxlength="30"  /></td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteFacRow('<s:property value="%{#stat.count-1}"/>')" />
																	</s:if>
																	</td>
																	<s:hidden name="xolcoverpreVal[%{#stat.count-1}]" id="xolcoverpreVal%{#stat.count-1}"/>
																</tr>
																</s:iterator>
																<%-- <s:hidden name="totalCoverage" id="totalCoverage"></s:hidden>
																<s:hidden name="totalGWPI" id="totalGWPI"></s:hidden>
																<s:hidden name="totalDeductible" id="totalDeductible"></s:hidden>
																<tr id="gnpi2" style="display:none;" >
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td>Total</td>
																<td>Total</td>
																</tr>--%>
																<s:hidden name="xolLoopcount" id="xolLoopcount" ></s:hidden>
															</tbody>
															
															
															</table>
															
															<table class="footable" width="100%" id="xolnewgen">
															<tr>
																<td width="2%" > Total:&nbsp;  </td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth" ><s:textfield name="deductibleFacXol" id="deductibleFacXol"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth"><s:textfield name="deductible" id="deductible"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"><s:textfield name="xoltotalGWPI" id="xoltotalGWPI"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);"/> </td>
																<td class="tableColWidth1">&nbsp;&nbsp;&nbsp;</td>
															</tr></table>
															<br class="clear"/>
															<s:if test=' !"Y".equals(disableStatus1)'>
															<div class="boxcontent" align="right">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insXolCoverRow('xolnewgen');" />
															</div>
															</s:if>
														</div>
															<br class="clear"/>
															<s:hidden name="sumInsured" id="sumInsured" value="0"></s:hidden>
															<s:hidden name="premiumrate" id="premiumrate" value="0"></s:hidden>
															<s:hidden name="gwpi" id="gwpi" value="0"></s:hidden>
												</div>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" id="coverdeduct1"  style="display:none">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div>
										<div id="newgenid" class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
										<table class="footable" width="100%" id="newgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.subClass" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Type" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlhundredper" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverareLimitOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlper" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverageDays" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductableDays" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premiumRate" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.gWPIOC100" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Remarks" /> </th>
																<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
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
																	<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}" disabled="0==(#stat.count-1)?true:false"/>
																	<s:hidden name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})"/>
																	</s:else>
																	</td>
																	
																	<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
																	<s:if test='0==(#stat.count-1)'>
																	<s:select list="subProfit_center" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false"/>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" onchange="getAllValue(%{#stat.count-1})" />
																	</s:else>
																	</td>
																	<td>
																	<s:select list="coverTypelist" listValue="DETAIL_NAME" listKey="TYPE" name="coverTypeId[%{#stat.count-1}]" id="coverTypeId%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="getPmlText()"/>
																	</td>
																	
																	<td>
																	<s:textfield name="pmlHundredPer[%{#stat.count-1}]" id="pmlHundredPer%{#stat.count-1}"  cssClass ="inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);getPMLPercent(%{#stat.count-1});javascript:this.value=Comma(this.value);" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  />
																	
																	</td>
																	 <td>
																	<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getdeductableCal();getCoverGWPI(%{#stat.count-1});getPMLPercent(%{#stat.count-1});getEgnpiCal();getTotalValue();getTotalEGNPI();" maxlength="30"   /> <%-- disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
																	</td>
																	<td>
																			<s:textfield name="pmlPerList[%{#stat.count-1}]" id="pmlPerList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="30"   onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);hundredCheck(this.id,this.value);javascript:this.value=Comma(this.value);" disabled="true"/>
																	</td>
																	<td>
																	<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="coverageDays[%{#stat.count-1}]" id="coverageDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="30"  />
																	</td>
																	<td><s:textfield name="deductableDays[%{#stat.count-1}]" id="deductableDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="30"  /></td>
																	<td><s:textfield name="premiumRateList[%{#stat.count-1}]" id="premiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);allowOneDot(this);hundredCheck(this.id,this.value);Itnegative(this.id,this.value);getCoverGWPI(%{#stat.count-1});getTotalEGNPI();" maxlength="30"  /></td>
																	<td><s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();getTotalEGNPI();" maxlength="30"  /></td>
																	<td>
																	<s:textarea name="coverRemark[%{#stat.count-1}]" id="coverRemark[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " />
																	</td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteFacRow('<s:property value="%{#stat.count-1}"/>')" />
																	</s:if>
																	</td>
																	<s:hidden name="coverpreVal[%{#stat.count-1}]" id="coverpreVal%{#stat.count-1}"/>
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
															
															<table class="footable" width="100%" id="newgen">
															<tr>
																<td width="20%"> Total:&nbsp;  </td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"><s:textfield name="totalCoverageVal" id="totalCoverageVal"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth" ></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth" ><s:textfield name="totalGWPIVal" id="totalGWPIVal"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth1">&nbsp;&nbsp;&nbsp;</td>
															</tr></table>
															<br class="clear"/>
															<s:if test=' !"Y".equals(disableStatus1)'>
															<div class="boxcontent" align="right">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insFacCoverRow('newgen');" />
															</div>
															</s:if>
														</div>
															<br class="clear"/>
												</div>
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
										<div>
											<div class="textfield" id="cet">
												<div class="text">
													<s:text name="Cedant's Retention Type" />
												</div>
												<div class="tbox">
													<s:radio list="#{'A':'Amount','P':'Percentage'}" name="cedRetenType" onclick="setCedRetType(this.value)" /><%--disabled='"Y".equals(disableStatus1)?true:false' --%>
												</div>
											</div>
											<div class="textfield" id="cer">
												<div class="text">
													<s:text name="Cedant's Retention" />
												</div>
												<div class="tbox">
													<s:textfield name="cedantsRet" id="cedantsRet" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="18" /> <%-- readonly='"Y".equals(disableStatus1)?true:false'--%>
												</div>
											</div>
											<s:if test='"RI01".equals(#session.SOURCE_CODE)' >
												<div class="textfield">
													<div class="text">
														<s:text name="label.tPLOC100" />
													</div>
													<div class="tbox">
														<s:textfield name="tpl" cssClass="inputBox" cssStyle="text-align: right;" onkeyup=" middleMinusRestriction(this);javascript:this.value=Comma(this.value)" onblur="allow2DigitDecValues(this);" maxlength="26"/>													
													</div>
												</div>
											</s:if>
											<s:else>
												<s:hidden name="tpl" id="tpl" />
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.noofInstallments" />
												</div>
												<div class="tbox">
													<s:textfield name="noOfInst" cssClass="inputBox" maxlength="2" onkeyup="checkNumbers(this);" />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.paymentDueDays" />
												</div>
												<div class="tbox">
													<s:textfield name="receiptofPayment" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkNumbers(this);" maxlength="3" />													
												</div>
											
											</div>
										
											<div class="textfield">
												<div class="text">
													<s:text name="label.proposalStatus" />
												</div>
												<div class="tbox">
													<s:if test="contractNo == '' || contractNo == null">
														<s:select list="proposal" name="proStatus" id="proStatus" cssClass="inputBoxS" onchange="GetShareSigned(this.value)"  headerKey="" headerValue="--Select--" listKey="TYPE" listValue="DETAIL_NAME"/>
													</s:if>
													<s:else>
														<s:select list="proposal" name="proStatus" id="proStatus" cssClass="inputBoxS" onchange="GetShareSigned(this.value)"  headerKey="" headerValue="--Select--" listKey="TYPE" listValue="DETAIL_NAME" disabled="true"/>
													</s:else>											
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.shareWrittenP" />
												</div>
												<div class="tbox">
													<s:textfield name="shWt" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="26"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.shareSignedP" />
												</div>
												<div class="tbox">
													<s:textfield name="shSd" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="26" /><%--readonly='"Y".equals(disableStatus1)?true:false'--%>
												</div>
											</div>
											<s:hidden name="xolOC" />
													
														</div>
										</div>
									</div>
								</div>
							</div>
							<s:if test="contractTypelist.size()>0  ">
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
						<s:if test="conmode == '' || conmode == null">							
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<s:if test="proposalNo == '' || proposalNo == null">
								<input type="submit"  value="Cancel"   class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');FunctionEditCancel()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');dffDateAlert()" />
							</s:if>
							<s:else>
								<input type="submit"  value="Cancel"   class="btn btn-sm btn-danger" onclick="return FunctionRejectCancel()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');dffDateAlert()" />
								<s:hidden name="proposal_no" />
							</s:else>	
							</div>
						</div>
						</s:if>
						<s:elseif test='"endorsment".equalsIgnoreCase(mode)'>
							<div class="boxcontent" align="center">
								<input type="submit"  value="Cancel"   class="btn btn-sm btn-danger" id="mybutton" onclick="endorsementCancel()" />
								&nbsp;&nbsp;&nbsp;
								<!--
								<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
								&nbsp;&nbsp;&nbsp;
								--><input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');dffDateAlert()" />
								</div>
						</s:elseif>
						<s:else>
							<div class="boxcontent" align="center">
								<input type="submit"  value="Back"   class="btn btn-sm btn-danger" onclick="return FunctionRejectCancel()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
								&nbsp;&nbsp;&nbsp;
								<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');dffDateAlert()" />
								
								<!--<s:submit action="faculengg2Facultative" cssClass="btn btn-sm btn-warning" value="Next" />
							--></div>
						</s:else>													
					</div>
					<s:hidden name="menuId" id="menuId"/>
					<%--<s:hidden name="contractNo" />--%>
					<s:hidden name="renewal_Contract_no" />
					<s:hidden name="renewalFlag" />
					<s:hidden name="renewalStatus" />
					<s:hidden name="edit" />
					<s:hidden name="conmode" />
					<s:hidden name="baseLoginID" />
					<s:hidden name="Department" value="%{departmentId}" />
					<s:hidden name="id"  id="id" />
					<s:hidden name="cedType"  id="cedType" />
					<s:hidden name="endorsmentno"/>
					<s:hidden name="mode" id="mode"/>
					<s:hidden name="preVal" id="preVal"/>
					
					<s:hidden name="flag"/>
					<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>
					<s:hidden name="countryExcludedList" id="countryExcludedList"/>
					<s:hidden name="countryIncludedList" id="countryIncludedList"/>
					<s:hidden name="reference" id="reference"/>
					<s:hidden name="proposalReference" id="proposalReference"/>
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
function ViewCeddingCompany(id)
{
	var cedding="";
	if(id==1)
	{
	 cedding=document.getElementById("CeddingId").value;
	}
	else
	{
	 cedding=document.getElementById("BrokerId").value;
	}
		var URL ='<%=request.getContextPath()%>/ViewModeCeding?Mode=PopUp&ceddingcompany='+cedding;
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

function toggle() {

var val = document.forms['facultative'].type.value;
if(val=="2"){
document.getElementById('xollay').style.display="inline"

}else if(val=="1"){
document.getElementById('xollay').style.display="none"

}
	/*var values = document.forms['facultative'].sipml.value;
	var val = document.forms['facultative'].type.value;
	if(values=="SI")
	{
		//document.getElementById('toggleText').style.display="none"
		document.getElementById('toggle').style.display="none"
		//document.getElementById('pmlllable').style.display="none"
		//document.getElementById('pmllvalue').style.display="none"
		//document.getElementById('P1').value="";
		document.getElementById('P2').value="";
		
		//document.getElementById('cp').style.display="none"
		//document.getElementById('sp').style.display="none"
		//document.getElementById('gp').style.display="none"
		
	}
	else if(values=="PML")
	{
		//document.getElementById('toggleText').style.display="inline"
		document.getElementById('toggle').style.display="block"
		//document.getElementById('pmlllable').style.display="block"
		//document.getElementById('pmllvalue').style.display="block"
		if(val=="1"){
		//document.getElementById('cp').style.display="none"
		//document.getElementById('sp').style.display="block"
		//document.getElementById('gp').style.display="block"
		}
		else if(val=="2"){
		//document.getElementById('cp').style.display="block"
		//document.getElementById('sp').style.display="none"
		//document.getElementById('gp').style.display="block"
		}
	}*/
} 

function Commas()
{	 
	document.forms['facultative'].maxiumlimit.value=Comma(document.forms['facultative'].maxiumlimit.value);
	document.forms['facultative'].cedantsRet.value=Comma(document.forms['facultative'].cedantsRet.value);
	if("4"!=<s:property value="#session.DepartmentId"/>){
	//document.forms['facultative'].deductible.value=Comma(document.forms['facultative'].deductible.value);
	}else{
	//document.forms['facultative'].deductible.value=Comma(document.forms['facultative'].deductible.value);
	//document.forms['facultative'].pslOC.value=Comma(document.forms['facultative'].pslOC.value);
	//document.forms['facultative'].pblOC.value=Comma(document.forms['facultative'].pblOC.value);
	//document.forms['facultative'].pllOC.value=Comma(document.forms['facultative'].pllOC.value);
	//document.forms['facultative'].pmll.value=Comma(document.forms['facultative'].pmll.value);
	//document.forms['facultative'].gwpiPml.value=Comma(document.forms['facultative'].gwpiPml.value);
	
	}
	//document.forms['facultative'].deductibleFacXol.value=Comma(document.forms['facultative'].deductibleFacXol.value);
	if(document.forms['facultative'].limitPerVesselOC!=null && document.forms['facultative'].limitPerVesselOC.value!="")
	{
		document.forms['facultative'].limitPerVesselOC.value=Comma(document.forms['facultative'].limitPerVesselOC.value);
	}
	if(document.forms['facultative'].limitPerLocationOC!=null && document.forms['facultative'].limitPerLocationOC.value!="")
	{
		document.forms['facultative'].limitPerLocationOC.value=Comma(document.forms['facultative'].limitPerLocationOC.value);
	}
	//document.forms['facultative'].sumInsured.value=Comma(document.forms['facultative'].sumInsured.value);
	//document.forms['facultative'].sumInsuredPml.value=Comma(document.forms['facultative'].sumInsuredPml.value);
	//if(document.forms['facultative'].pmll.value!=null && document.forms['facultative'].pmll.value!="")
	//{
	//	document.forms['facultative'].pmll.value=Comma(document.forms['facultative'].pmll.value);
	//}
	//document.forms['facultative'].gwpi.value=Comma(document.forms['facultative'].gwpi.value);
	/*if(document.forms['facultative'].tpl.value==null || document.forms['facultative'].tpl.value==""){
		document.forms['facultative'].tpl.value="";
	}else{
		document.forms['facultative'].tpl.value=Comma(document.forms['facultative'].tpl.value);
	}*/
}

function GetExchangeRate()
{		
		var accDate=document.forms['facultative'].accountDate.value;
		var incDate=document.forms['facultative'].inceptionDate.value;
		var cur = document.forms['facultative'].usCurrencyRate.value;
		var Currecny=document.forms['facultative'].originalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !="")){
	 		var URL='${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=facexRate&accDate='+accDate;
  	 		postRequest(URL,'currencyRate');
        }else{
			document.forms['facultative'].usCurrencyRate.value='';
		}	
}

function setCedRetType(type)
{
	document.getElementById("cedType").value=type;
}

function GetSp(id)
{	
	if(id=='Y')
	{
		document.forms['facultative'].no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N')
	{
		document.getElementById("NoOFInsur").readOnly=true;
		document.forms['facultative'].no_Insurer.value="0";
	}
}

function PMlCalculation()
{
	/*var gwpi100=0;
	var pml=document.forms['facultative'].pml.value;
	 gwpi100 = document.forms['facultative'].gwpi.value;
	if(pml!=null && pml!="" && gwpi100!=null && gwpi100!="")
	{
		pml=pml.replace(new RegExp(',', 'g'),'');
		gwpi100=gwpi100.replace(new RegExp(',', 'g'),'');
		var percenpml=parseFloat(pml)/100;
		var calc=parseFloat(gwpi100)*percenpml;
		document.forms['facultative'].gwpiPml.value=Comma(calc.toFixed(2));
	}
	else{
	 document.forms['facultative'].gwpiPml.value='';
	}*/
}
function SUMCalculation()
{
 <%--var type=document.forms['facultative'].type.value
 
	var sum=0;
	var pml=document.forms['facultative'].pml.value;
	if("1"==type){
	 sum = document.forms['facultative'].sumInsured.value;
	 }
	 else if("2"==type){
	 sum = document.forms['facultative'].deductibleFacXol.value;
	 }
	if(pml!=null && pml!="" && sum!=null && sum!="")
	{
		pml=pml.replace(new RegExp(',', 'g'),'');
		sum=sum.replace(new RegExp(',', 'g'),'');
		var percenpml=parseFloat(pml)/100;
		var calc=parseFloat(sum)*percenpml;
		if("1"==type){
		document.forms['facultative'].sumInsuredPml.value=Comma(calc.toFixed(2));
		}
		else if("2"==type){
		document.forms['facultative'].deductibleFacXolPml.value=Comma(calc.toFixed(2));
		}
	}--%>
}

function GWPCalculation(){
	var sumInsured=0;
	var pmlrate=document.forms['facultative'].premiumrate.value;
	var type = document.forms['facultative'].type.value;
	if(type=="1"){
	 sumInsured=document.forms['facultative'].sumInsured.value;
	}
	else if(type=="2"){
	 sumInsured=document.forms['facultative'].deductibleFacXol.value;
	}
	sumInsured=sumInsured.replace(new RegExp(',', 'g'),'');
	if(pmlrate!=null && pmlrate!="" && sumInsured!=null && sumInsured!="")
	{
		var percenpml=parseFloat(pmlrate)/100;
		var calc=parseFloat(sumInsured)*percenpml;
		document.forms['facultative'].gwpi.value=Comma(calc.toFixed(2));
	}
	else{
	 	document.forms['facultative'].gwpi.value='';
	}
}
function getCoverGWPI(val){
	var sumInsured=0;
	var pmlrate=document.getElementById("premiumRateList"+val).value;
	 sumInsured=document.getElementById("coverLimitOC"+val).value;
	 pmlrate = pmlrate.replace(new RegExp(',', 'g'),'');
	sumInsured=sumInsured.replace(new RegExp(',', 'g'),'');
	if(pmlrate!=null && pmlrate!="" && sumInsured!=null && sumInsured!="")
	{
		var percenpml=parseFloat(pmlrate)/100;
		var calc=parseFloat(sumInsured)*percenpml;
		document.getElementById("egnpiAsPerOff"+val).value=Comma(calc.toFixed(2));
	}
	else{
	 	document.getElementById("egnpiAsPerOff"+val).value='';
	}
}
function getxolCoverGWPI(val){
	var sumInsured=0;
	var pmlrate=document.getElementById("xolpremiumRateList"+val).value;
	 sumInsured=document.getElementById("xolcoverLimitOC"+val).value;
	 pmlrate = pmlrate.replace(new RegExp(',', 'g'),'');
	sumInsured=sumInsured.replace(new RegExp(',', 'g'),'');
	if(pmlrate!=null && pmlrate!="" && sumInsured!=null && sumInsured!="")
	{
		var percenpml=parseFloat(pmlrate)/100;
		var calc=parseFloat(sumInsured)*percenpml;
		document.getElementById("xolgwpiOC"+val).value=Comma(calc.toFixed(2));
	}
	else{
	 	document.getElementById("xolgwpiOC"+val).value='';
	}
}
function getPMLPercent(val){
	var sumInsured=0;
	var pmlamount=document.getElementById("pmlHundredPer"+val).value;
	 sumInsured=document.getElementById("coverLimitOC"+val).value;
	 pmlamount = pmlamount.replace(new RegExp(',', 'g'),'');
	sumInsured=sumInsured.replace(new RegExp(',', 'g'),'');
	
	if(pmlamount!=null && pmlamount!="" && sumInsured!=null && sumInsured!="")
	{
		var percenpml=parseFloat(pmlamount)*100;
		var calc=percenpml/parseFloat(sumInsured);
		document.getElementById("pmlPerList"+val).value=Comma(calc.toFixed(2));
	}
	else{
	 	document.getElementById("pmlPerList"+val).value='';
	}
}
function functionDate()
{
   var	inceptionDate=document.forms['facultative'].inceptionDate.value;
	if(inceptionDate!="")
	{
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
		document.forms['facultative'].inceptionDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.forms['facultative'].year.value=date.getFullYear();
		//var date1=new Date((date.getFullYear()+1)+"/"+date.getMonth()+"/"+(date.getDate()));
		
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
		document.forms['facultative'].expiryDate.value=d+"/"+m+"/"+y;
		
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

function dffDateAlert(){
	destroyPopUps();
    var inception=document.forms['facultative'].inceptionDate.value;
	if(inception!="")
	{
		var date=new Date(reformatDate(inception));
		date.setFullYear(date.getFullYear()+1);
		date.setDate(date.getDate()-1);
		var d;
		var m;
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
		var expery=(document.forms['facultative'].expiryDate.value).split("/");					
		var oneDay = 24*60*60*1000;
		var firstDate=new Date(y,m-1,d)
		var secondDate=new Date(expery[2],expery[1]-1,expery[0]);
		//var firstDate = new Date(2008,01,12); 
		   //var secondDate = new Date(2008,01,22); 
		//var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
		var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
		if(diffDays>0) {
			alert('Expiry Date More than One Year');
		}
		funsubmit();
	}else{
	   funsubmit();
	}
}

function chkAccAmt(){
	var sumIns=document.forms['facultative'].sumInsured.value;
	sumIns=sumIns.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.forms['facultative'].maxiumlimit.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	//var pmll=document.forms['facultative'].pmll.value;
	//pmll=pmll.replace(new RegExp(',', 'g'),'');
	var exRate=document.forms['facultative'].usCurrencyRate.value;
	var cedPer=0;
	var amount=0;
	if(document.forms['facultative'].proStatus.value=="A") {	 
		cedPer=document.forms['facultative'].shSd.value;
	}else {
		cedPer=document.forms['facultative'].shWt.value;
	}
	var amt="0";
	/*if(document.forms['facultative'].sipml.value=="SI") {
		amt=sumIns;
	}
	else if(document.forms['facultative'].sipml.value=="PML") {
		//amt=pmll;
		amt=0;
	}*/
	if(amt!=null && amt!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!=""&&exRate!=null &&exRate!=""){
		amount=((amt*(cedPer/100.0))/exRate);
		if(amount>maxLimit) {
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt) {
				<s:if test='contractNo == "" && contractNo == null'>
					document.forms['facultative'].proStatus.value='P';
					document.forms['facultative'].shSd.value='';
				</s:if>				
			}else {
				 return false; 
			}
		}
	}
	return true; 
}

function setCedRetType(type)
{
	document.getElementById("cedType").value=type;
}

/***********************************/
function funsubmit()
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
		else if(elements2 == "A")
		{
		    alert('Please Select Territory');
		}
	if(chkAccAmt()) {
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.facultative,'maxiumlimit,cedantsRet,limitPerVesselOC,limitPerLocationOC,deductible,deductibleFacXol,sumInsured,gwpi,tpl,sumInsuredPml,pslOC,pllOC,pblOC,pmll,deductible,gwpiPml');
		document.forms['facultative'].action="${pageContext.request.contextPath}/InsertFirstPageFacultative.action";
		document.forms['facultative'].submit();
	}
}

function FunctionSaveOption()
{
destroyPopUps();
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
		else if(elements2 == "A")
		{
		    alert('Please Select Territory');
		}
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.facultative,'maxiumlimit,cedantsRet,limitPerVesselOC,limitPerLocationOC,deductible,deductibleFacXol,sumInsured,gwpi,tpl,sumInsuredPml,pslOC,pllOC,pblOC,pmll,deductible,gwpiPml');
	document.forms['facultative'].action="${pageContext.request.contextPath}/SavePageFacultative.action";
	document.forms['facultative'].submit();
}
		
		
function FirstPageBackMethod()
{
	document.forms['facultative'].action="<%=request.getContextPath()%>/FaculitivesDispatchAction.do?method=FirstPageBackMethod";
	document.forms['facultative'].submit();
}
		
function FunctionEditCancel()
{
		destroyPopUps();
		document.facultative.action='${pageContext.request.contextPath}/menuPortfolio.action'; 
		document.facultative.submit();
}

function FunctionRejectCancel()
{
	destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	document.getElementById("proposalNo").disabled=false;
	var no =document.getElementById("proposalNo").value;
	if('Renewal'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("renewalProposalNo").value =no;
			document.forms['facultative'].action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.forms['facultative'].submit();
			}
			else {
			document.getElementById("proposalNo").disabled=true;
				 return false; 
			}
		}
		else{
			document.forms['facultative'].action='${pageContext.request.contextPath}/menuPortfolio?layerNo=0'; 
			document.forms['facultative'].submit();
		}
}

function endorsementCancel() {
	destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	document.facultative.action = "${pageContext.request.contextPath}/InitPortfolio.do?endtMode=endorsment&endorsementStatus=N";
}

GetShareSigned('<s:property value="proStatus"/>');
function GetShareSigned(value)
{
		if(value=='A') {
			document.forms['facultative'].shSd.disabled=false;
		}
		else  {
			document.forms['facultative'].shSd.value='';
			document.forms['facultative'].shSd.disabled=true;
		}
}
if('<s:property value="type"/>'!=null){
//EndorsementScript('<s:property value="type"/>');
getCoverDedutable('<s:property value="type"/>');
}
function EndorsementScript(value) {
		if(value=='1') {
		
			if("4"!=<s:property value="#session.DepartmentId"/>){
			document.getElementById('endtlabel').style.display='block';	
			}
			document.getElementById('endtlabel1').style.display='none';	
			document.getElementById('endtlabel2').style.display='block';
			document.getElementById('cet').style.display='block';
			document.getElementById('cer').style.display='block';
			//document.getElementById('deductible').value='';
			document.getElementById('deductibleFacXol').value='';
		}
		else if(value=='2') {
		
		if("4"!=<s:property value="#session.DepartmentId"/>){
			document.getElementById('endtlabel').style.display='block';
			}
			document.getElementById('endtlabel1').style.display='block';
			document.getElementById('endtlabel2').style.display='none';
			document.getElementById('cedantsRet').value='';
			document.getElementById('cet').style.display='none';
			document.getElementById('cer').style.display='none';
			document.forms['facultative'].sumInsured.value='';
		}
	}
function getCoverDedutable(value){
//var val ='<s:property value="#session.DepartmentId"/>';
 if(value=="1"){
 document.getElementById('coverdeduct').style.display='none';
 document.getElementById('coverdeduct1').style.display='';
 getEgnpiCal();
 getdeductableCal();
 getDedcCal();
 }else if(value=="2") {
 document.getElementById('coverdeduct1').style.display='none';
 document.getElementById('coverdeduct').style.display='';
 }

}
function fnSiOcEntry(val) {
	document.forms['facultative'].sumInsured.value=val;
}
if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.facultative.underwriter.value);
}
function getUWLimit(value){
   //var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitFacultative.action?underwriter='+value+'&dropDown=uwc';
	postRequest(URL,'uwc');
}    

function territorySelection(){
		var URL ='${pageContext.request.contextPath}/territorySelectionFacultative.action';
		var windowName = "TerritoryDetails";
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
<s:if test='"RI02".equals(#session.SOURCE_CODE) && "4".equals(#session.DepartmentId)'>
	 getPLLEnable();
</s:if>
function getPLLEnable(){
 var type=document.forms['facultative'].type.value
 
 if('4'=='<s:property value="#session.DepartmentId"/>' && 'RI02'=='<s:property value="#session.SOURCE_CODE"/>' ){
 if('1'==type){
	 document.getElementById('pll').style.display='block';
	 document.getElementById('psl').style.display='block';
	 document.getElementById('pml').style.display='block';
	 document.getElementById('pbl').style.display='block';
 }
 else{
 	  document.getElementById('pll').style.display='none';
	 document.getElementById('psl').style.display='none';
	 document.getElementById('pml').style.display='none';
	 document.getElementById('pbl').style.display='none';
	 document.forms['facultative'].pslOC.value='';
	 document.forms['facultative'].pllOC.value='';
	 document.forms['facultative'].pblOC.value='';
	 document.forms['facultative'].pmll.value='';
	 
 }
 }
}

function checkLatitudeRange(id,val){
if(val>90 || val<-90){
	alert("Lattitude Rage should be -90 t0 +90");
	document.getElementById(id).value='';
	}
	
}

function checkLongitudeRange(id,val){
if(val>180 || val<-180){
	alert("Longitude Rage should be -180 t0 +180");
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
getLocDeta('<s:property value="locIssued"/>');
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
    $('#subProfitCenter').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
          var val = $('#subProfitCenter').val();
          var val1 =document.getElementById("preVal").value;
          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
          $("#subProfitCenter").multiselect('clearSelection');
          val = removeElementsWithValue(val, 'ALL');
          $("#subProfitCenter").val(val);
           $("#subProfitCenter").multiselect("refresh");
           document.getElementById("preVal").value = '';
          }
          else if (val !=null && val[0]=='ALL' ) {
          	$("#subProfitCenter").multiselect('clearSelection');
          	$("#subProfitCenter").val('ALL');
          	 $("#subProfitCenter").multiselect("refresh");
          	 document.getElementById("preVal").value = 'ALL';
          }
          else if(val== null || val[0]==''){
          $("#subProfitCenter").multiselect('clearSelection');
          $("#subProfitCenter").multiselect("refresh");
          document.getElementById("preVal").value = '';
          }
      	}                     
    }); 
    
     <s:if test='subProfitCenter!=null && !"".equals(subProfitCenter)'>	
 		var uwgrade='<s:property value="subProfitCenter"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#subProfitCenter").val(dataArray);
		 $("#subProfitCenter").multiselect("refresh");
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

 var count=document.getElementById('loopcount').value;
$(document).ready(function() {
 var count=document.getElementById('loopcount').value;
       $('#coversubdepartId').html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#coversubdepartId"+count).val();
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
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
 
});

 <s:iterator value="xolCoverdeductableList"  status="stat">   
 var count='<s:property value="%{#stat.count-1}"/>';
       $('#xolcoversubdepartId').html(data);
            $('#xolcoversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#xolcoversubdepartId"+count).val();
		           //var data=val.replace(new RegExp(',', 'g'),'');;	
	   	 			//var dataArray=data.split(",");
		          var val1 =document.getElementById("xolcoverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#xolcoversubdepartId"+count).val(val);
		           $("#xolcoversubdepartId"+count).multiselect("refresh");
		           document.getElementById("xolcoverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          	$("#xolcoversubdepartId"+count).val('ALL');
		          	 $("#xolcoversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("xolcoverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });  
    var val='<s:property value="%{#stat.count-1}"/>'; 
 	var uwgrade='<s:property value="%{xolcoversubdepartId[#stat.count-1]}"/>';
	var data=uwgrade.replace(/ /g,'');	
	var dataArray=data.split(",");
	$("#xolcoversubdepartId"+val).val(dataArray);
	$("#xolcoversubdepartId"+val).multiselect("refresh");       
 
</s:iterator>
var count=document.getElementById('xolLoopcount').value;
$(document).ready(function() {
 var count=document.getElementById('xolLoopcount').value;
       $('#xolcoversubdepartId').html(data);
            $('#xolcoversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#xolcoversubdepartId"+count).val();
		          var val1 =document.getElementById("xolcoverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#xolcoversubdepartId"+count).val(val);
		           $("#xolcoversubdepartId"+count).multiselect("refresh");
		           document.getElementById("xolcoverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          	$("#xolcoversubdepartId"+count).val('ALL');
		          	 $("#xolcoversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("xolcoverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });         
 
});
<s:iterator value="coverdeductableList"  status="stat">   
 var count='<s:property value="%{#stat.count-1}"/>';
       $('#coversubdepartId').html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#coversubdepartId"+count).val();
		           //var data=val.replace(new RegExp(',', 'g'),'');;	
	   	 			//var dataArray=data.split(",");
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
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
    var val='<s:property value="%{#stat.count-1}"/>'; 
 	var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
	var data=uwgrade.replace(/ /g,'');	
	var dataArray=data.split(",");
	$("#coversubdepartId"+val).val(dataArray);
	$("#coversubdepartId"+val).multiselect("refresh");       
 
</s:iterator>
 function getAjax1(value,id)
{
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueFacultative.action?departId='+value+'&dropDown=subclass',
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#subProfit_center').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
          		 var val = $('#subProfitCenter').val();
		          var val1 =document.getElementById("preVal").value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#subProfitCenter").multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#subProfitCenter").val(val);
		           $("#subProfitCenter").multiselect("refresh");
		           document.getElementById("preVal").value = '';
		          }
		          else if (val !=null && val[0]=='ALL' ) {
		          	$("#subProfitCenter").multiselect('clearSelection');
		          	$("#subProfitCenter").val('ALL');
		          	 $("#subProfitCenter").multiselect("refresh");
		          	 document.getElementById("preVal").value = 'ALL';
          }
          else{
          $("#subProfitCenter").multiselect('clearSelection');
          $("#subProfitCenter").multiselect("refresh");
          document.getElementById("preVal").value = '';
          }
      	}                     
    });
        }
        });
       // getAjaxCover(value,'coverdepartid0','0');
}
function getAjaxCover(value,id,count)
{
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueFacultative.action?coverdepart='+value+'&dropDown=coversubclass&id='+count,
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#coversubdepartId"+count).val();
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
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
        }
        });
        
}
function getAjaxXOlCover(value,id,count)
{
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueFacultative.action?coverdepart='+value+'&dropDown=xolcoversubclass&id='+count,
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#xolcoversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
		          var val = $("#xolcoversubdepartId"+count).val();
		          var val1 =document.getElementById("xolcoverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#xolcoversubdepartId"+count).val(val);
		           $("#xolcoversubdepartId"+count).multiselect("refresh");
		           document.getElementById("xolcoverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#xolcoversubdepartId"+count).multiselect('clearSelection');
		          	$("#xolcoversubdepartId"+count).val('ALL');
		          	 $("#xolcoversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("xolcoverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });
        }
        });
        
}

function getAllValue(count){
	var val = $("#coversubdepartId"+count).val();
    var val1 =document.getElementById("coverpreVal"+count).value;
    if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
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
function insFacCoverRow(tableID)
{
var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "coverSNo["+(rowCount-1)+"]";
       		element1.id = "coverSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1);
			createcoverdeptCell(cell2, rowCount)
			
			var cell3 = row.insertCell(2);
			cell3.id='coverdepartid'+(rowCount-1);
			createcoversubdeptCell(cell3, rowCount)
		
			var cell4 = row.insertCell(3);
			createcoverTypeCell(cell4, rowCount)
			
			
			
			var cell5 = row.insertCell(4);
			var element14 = document.createElement("input");
			element14.type = "text";
			element14.name = "pmlHundredPer["+(rowCount-1)+"]";
      		element14.id = "pmlHundredPer"+(rowCount-1)+"";
      		//element4.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element14.className = "inputBox";
			element14.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);getPMLPercent("+(rowCount-1)+");javascript:this.value=Comma(this.value);");
			element14.setAttribute("maxlength",'30');
			element14.setAttribute("disabled",'true');
			element14.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell5.appendChild(element14);
			
			var cell6 = row.insertCell(5);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "coverLimitOC["+(rowCount-1)+"]";
      		element4.id = "coverLimitOC"+(rowCount-1)+"";
      		//element4.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getdeductableCal();getCoverGWPI("+(rowCount-1)+");getPMLPercent("+(rowCount-1)+");getEgnpiCal();getTotalValue();getTotalEGNPI();");
			element4.setAttribute("maxlength",'30');
			element4.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell6.appendChild(element4);
			
			var cell7 = row.insertCell(6);
			var element13 = document.createElement("input");
			element13.type = "text";
			element13.name = "pmlPerList["+(rowCount-1)+"]";
      		element13.id = "pmlPerList"+(rowCount-1)+"";
      		//element4.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element13.className = "inputBox";
			element13.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element13.setAttribute("maxlength",'30');
			element13.setAttribute("disabled",'true');
			element13.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell7.appendChild(element13);
			
			var cell8 = row.insertCell(7);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "deductableLimitOC["+(rowCount-1)+"]";
			//element5.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element5.id = "deductableLimitOC"+(rowCount-1)+"";
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element5.setAttribute("maxlength",'30');
			element5.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell8.appendChild(element5);
			
			var cell9 = row.insertCell(8);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "coverageDays["+(rowCount-1)+"]";
			//element6.value=document.getElementById("coverageDays["+(rowCount-2)+"]").value;
      		element6.id = "coverageDays["+(rowCount-1)+"]";
			element6.className = "inputBox";
			element6.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);checkNumbers(this);");
			element6.setAttribute("maxlength",'30');
			element6.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell9.appendChild(element6);
			
			var cell10 = row.insertCell(9);
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "deductableDays["+(rowCount-1)+"]";
			//element7.value=document.getElementById("deductableDays["+(rowCount-2)+"]").value;
      		element7.id = "deductableDays["+(rowCount-1)+"]";
			element7.className = "inputBox";
			element7.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);checkNumbers(this);");
			element7.setAttribute("maxlength",'30');
			element7.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell10.appendChild(element7);
			
			var cell11 = row.insertCell(10);
			var element8 = document.createElement("input");
			element8.type = "text";
			element8.name = "premiumRateList["+(rowCount-1)+"]";
			//element8.value=document.getElementById("premiumRateList["+(rowCount-2)+"]").value;
      		element8.id = "premiumRateList"+(rowCount-1)+"";
			element8.className = "inputBox";
			element8.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);getCoverGWPI("+(rowCount-1)+");getTotalEGNPI();");
			element8.setAttribute("maxlength",'30');
			element8.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell11.appendChild(element8);
			
			var cell12 = row.insertCell(11);
			var element9 = document.createElement("input");
			element9.type = "text";
			element9.name = "egnpiAsPerOff["+(rowCount-1)+"]";
      		element9.id = "egnpiAsPerOff"+(rowCount-1);
			//element9.value=document.getElementById("egnpiAsPerOff"+(parseFloat(rowCount)-2)).value;
			element9.className = "inputBox";
			element9.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();getTotalEGNPI();");
			element9.setAttribute("maxlength",'30'); 
			element9.setAttribute("style", "text-align:right;");
			cell12.appendChild(element9); 
			
			var cell13 = row.insertCell(12);
			var element10 = document.createElement("textarea");
			//element3.type = "textarea";
			element10.name = "coverRemark["+(rowCount-1)+"]";
			//element3.value=document.getElementById("remark1["+(rowCount-2)+"]").value;
      		element10.id = "coverRemark["+(rowCount-1)+"]";
      		element10.setAttribute("style", "width: 100%; resize: vertical;");
			element10.className = "inputBox";
			element10.setAttribute("align", "right");
			var element12 = document.createElement("input");
			element12.type = "hidden";
			element12.name = "coverpreVal["+(rowCount-1)+"]";
      		element12.id = "coverpreVal"+(rowCount-1);
      		element12.value='';
			cell13.appendChild(element10);
			cell13.appendChild(element12);
			
			var cell14 = row.insertCell(13);
			cell13.setAttribute("align","center");
			var element11 = document.createElement("input");
			element11.type = "button";
			element11.value="Delete";
			element11.setAttribute("onclick", "disableForm(this.form,false,'');deleteFacRow('"+(rowCount-1)+"')");
			element11.className="btn btn-sm btn-danger"
			cell14.appendChild(element11);
			
			
			 document.getElementById("loopcount").value =parseInt(rowCount);
			 //getEgnpiCal();
			// getdeductableCal();
			 getDedcCal();
}
function createcoverdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartId["+(rowCount-1)+"]";
      	element.id = "coverdepartId["+(rowCount-1)+"]";
       element.className = "inputBox";
      	element.setAttribute("onchange", "getAjaxCover(this.value,'coverdepartid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function createcoversubdeptCell(cell, rowCount){

	element = document.createElement("select");
         element.name = "coversubdepartId["+(rowCount-1)+"]";
      	element.id = "coversubdepartId["+(rowCount-1)+"]";
       element.className = "inputBoxS";
       //element.setAttribute("onchange", "getAllValue("+(rowCount-1)+");            
       populateCoversubdept(element);
          cell.appendChild(element);
}
function createcoverTypeCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverTypeId["+(rowCount-1)+"]";
      	element.id = "coverTypeId"+(rowCount-1)+"";
       element.className = "inputBoxS";
       element.setAttribute("onchange", "getPmlText()");
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverType(element);
          cell.appendChild(element);
}

function populateCoverdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='departIdlist'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_DEPARTMENT_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_DEPARTMENT_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
					
					
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function populateCoversubdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = 'None';
          objOption.value = 'None';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='subProfitList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_SPFC_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_SPFC_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function populateCoverType(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='coverTypelist'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function insXolCoverRow(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "xolcoverSNo["+(rowCount-1)+"]";
       		element1.id = "xolcoverSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1);
			createxolcoverdeptCell(cell2, rowCount)
			
			var cell3 = row.insertCell(2);
			cell3.id='xolcoverdepartid'+(rowCount-1);
			createxolcoversubdeptCell(cell3, rowCount)
		
			
			var cell4 = row.insertCell(3);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "xolcoverLimitOC["+(rowCount-1)+"]";
      		element4.id = "xolcoverLimitOC"+(rowCount-1)+"";
      		//element4.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getxolCoverGWPI("+(rowCount-1)+");getTotalxolValue();getTotalGWPI();");
			element4.setAttribute("maxlength",'30');
			element4.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element4);
			
		
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "xoldeductableLimitOC["+(rowCount-1)+"]";
			//element5.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element5.id = "xoldeductableLimitOC"+(rowCount-1)+"";
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalDeductable();");
			element5.setAttribute("maxlength",'30');
			element5.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell5.appendChild(element5);
			
			
			
			var cell6 = row.insertCell(5);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "xolpremiumRateList["+(rowCount-1)+"]";
			//element8.value=document.getElementById("premiumRateList["+(rowCount-2)+"]").value;
      		element6.id = "xolpremiumRateList"+(rowCount-1)+"";
			element6.className = "inputBox";
			element6.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);getxolCoverGWPI("+(rowCount-1)+");");
			element6.setAttribute("maxlength",'30');
			element6.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell6.appendChild(element6);
			
			var cell7 = row.insertCell(6);
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "xolgwpiOC["+(rowCount-1)+"]";
      		element7.id = "xolgwpiOC"+(rowCount-1);
			//element9.value=document.getElementById("egnpiAsPerOff"+(parseFloat(rowCount)-2)).value;
			element7.className = "inputBox";
			element7.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalGWPI();");
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			cell7.appendChild(element7); 
			
			
			
			var cell8 = row.insertCell(7);
			cell8.setAttribute("align","center");
			var element8 = document.createElement("input");
			element8.type = "button";
			element8.value="Delete";
			element8.setAttribute("onclick", "disableForm(this.form,false,'');deleteXolRow('"+(rowCount-1)+"')");
			element8.className="btn btn-sm btn-danger"
			cell8.appendChild(element8);
			var element9 = document.createElement("input");
			element9.type = "hidden";
			element9.name = "xolcoverpreVal["+(rowCount-1)+"]";
      		element9.id = "xolcoverpreVal"+(rowCount-1);
      		element9.value='';
			cell8.appendChild(element9);
			
			 document.getElementById("xolLoopcount").value =parseInt(rowCount);
			 //getEgnpiCal();
			// getdeductableCal();
			 getDedcCal();
			 
}
function createxolcoverdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "xolcoverdepartId["+(rowCount-1)+"]";
      	element.id = "xolcoverdepartId["+(rowCount-1)+"]";
       element.className = "inputBox";
      	element.setAttribute("onchange", "getAjaxXOlCover(this.value,'xolcoverdepartid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function createxolcoversubdeptCell(cell, rowCount){

	element = document.createElement("select");
         element.name = "xolcoversubdepartId["+(rowCount-1)+"]";
      	element.id = "xolcoversubdepartId["+(rowCount-1)+"]";
       element.className = "inputBoxS";
       //element.setAttribute("onchange", "getAllValue("+(rowCount-1)+");            
       populateCoversubdept(element);
          cell.appendChild(element);
}
 function deleteFacRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartId[0]").disabled = false; 
		document.getElementById("coversubdepartId0").disabled = false; 
		postFormRequest('${pageContext.request.contextPath}/removeCoverDeductableFacultative.action?mode=delete&deleteId='+val+'&dropDown=coverdeductable', "newgenid", "facultative"); 
		}
		}
}
 function deleteXolRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartId[0]").disabled = false; 
		document.getElementById("coversubdepartId0").disabled = false; 
		postFormRequest('${pageContext.request.contextPath}/removeXolCoverDeductableFacultative.action?mode=delete&deleteId='+val+'&dropDown=xolcoverdeductable', "xolnewgenid", "facultative"); 
			}
		}
}
function getSubDepatmentCover()
{
  var data=$('#subProfitCenter').val();
	 $("#coversubdepartId0").multiselect('clearSelection');
	 $("#coversubdepartId0").val(data);
	 $("#coversubdepartId0").multiselect("refresh");
	 $("#xolcoversubdepartId0").multiselect('clearSelection');
	 $("#xolcoversubdepartId0").val(data);
	 $("#xolcoversubdepartId0").multiselect("refresh");
	
}


function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
function getdeductableCal(){
	var texts = document.getElementById("loopcount").value;
	
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("coverLimitOC"+i).value;
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("totalCoverage").value =  Comma(parseFloat(sum).toFixed(2));
			
}
function getEgnpiCal(){
	var texts = document.getElementById("loopcount").value;
	
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("egnpiAsPerOff"+i).value;
    //alert(val);
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("totalGWPI").value =  Comma(parseFloat(sum).toFixed(2));
			
}

function getDedcCal(){
var texts = document.getElementById("loopcount").value;
	
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("deductableLimitOC"+i).value;
    //alert(val);
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    if(sum != '' && sum !='0'){
    sum = sum.replace(new RegExp(',', 'g'),'');
    }
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("totalDeductible").value =  Comma(parseFloat(sum).toFixed(2));
			
}
onmouseover="ShowToolTip(this.id,'%{proposalNo}')" 
function ShowToolTip(txtbox,val)
{
	var ToolTip = document.getElementById(txtbox);
	ToolTip.title=val;
}
getTotalValue();
function getTotalValue(){
    var texts = document.getElementById("loopcount").value;
	var val=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
	for(var i=0;i<texts;i++){
		var cover = document.getElementById("coverLimitOC"+i).value;
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		}
		val +=parseFloat(cover);
	}
	document.getElementById("totalCoverageVal").value= Comma(parseFloat(val).toFixed(2));
}
getTotalGWPI();
function getTotalGWPI(){
    var texts = document.getElementById("xolLoopcount").value;
	var val=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
	for(var i=0;i<texts;i++){
		var cover = document.getElementById("xolgwpiOC"+i).value;
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		}
		val +=parseFloat(cover);
	}
	document.getElementById("xoltotalGWPI").value= Comma(parseFloat(val).toFixed(2));
}
getTotalxolValue();
function getTotalxolValue(){
    var texts = document.getElementById("xolLoopcount").value;
	var val=0;
	if(texts=="0" ){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
	for(var i=0;i<texts;i++){
		var cover = document.getElementById("xolcoverLimitOC"+i).value;
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		}
		val +=parseFloat(cover);
	}
	document.getElementById("deductibleFacXol").value= Comma(parseFloat(val).toFixed(2));
}
getTotalEGNPI();
function getTotalEGNPI(){
	var texts = document.getElementById("loopcount").value;
	var val=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
	for(var i=0;i<texts;i++){
		var cover = document.getElementById("egnpiAsPerOff"+i).value;
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		}
		val +=parseFloat(cover);
	}
	document.getElementById("totalGWPIVal").value= Comma(parseFloat(val).toFixed(2));
}
getTotalDeductable();
function getTotalDeductable(){
	var texts = document.getElementById("xolLoopcount").value;
	var val=0;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
	for(var i=0;i<texts;i++){
		var cover = document.getElementById("xoldeductableLimitOC"+i).value;
		if(cover ==''){
		cover=0;
		}
		else{
		cover = cover.replace(new RegExp(',', 'g'),'');
		}
		val +=parseFloat(cover);
	}
	document.getElementById("deductible").value= Comma(parseFloat(val).toFixed(2));
}
getPmlText();
function getPmlText(){
var texts = document.getElementById("loopcount").value;
if(texts=="0"){
	texts = parseInt(texts)+1;
	}else if(texts==""){
	texts = 1;
	}
for(var i=0;i<texts;i++){
var value = document.getElementById('coverTypeId'+i).value;
if( "2" == value){
	 document.getElementById('pmlPerList'+i).disabled=false;
	 document.getElementById('pmlHundredPer'+i).disabled=false;
	}
	else{
	document.getElementById('pmlPerList'+i).value='';
	 document.getElementById('pmlPerList'+i).disabled=true;
	 document.getElementById('pmlHundredPer'+i).value='';
	 document.getElementById('pmlHundredPer'+i).disabled=true;
	}
	}
}


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