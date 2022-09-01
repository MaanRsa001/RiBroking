<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- 	<sj:head jqueryui="true" jquerytheme="start" />--%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
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
<body onload="Commas(<s:property value="#session.mfrid" />),setCedRetType('<s:property value="cedRetenType" />')">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="xol1" id="xol1" theme="simple" action="" method="post" autocomplete="off">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow" align="center">
							<span class="pageHeading">							
							<s:text name="label.riskDetailSheetXol" />
							</span>
						</div>
						<s:if test="contNo != null && contNo != '' && proposal_no != null && proposal_no != '' && amend_Id_Mode != '' && amend_Id_Mode != null ">
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
													<s:select list="endosTypelist" name="endorsmenttype" id="endorsmenttype" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" onchange="getFieldDisable()" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.endorsementNo" />
												</div>
												<div class="tbox txtB">
													<s:property value="amendId"/>
												</div>
											</div>													 
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<s:else>
						<s:hidden name="endorsmenttype"  id="endorsmenttype"/>
						</s:else>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
												<div class="text" >
													<s:text name="label.proposalNo" />
												</div>
												<div class="tbox">
													<s:textfield name="proposal_no" id="proposal_no" cssClass="inputBox" disabled="true" />
												</div>
											</div>											
											<div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox">
													<s:textfield name="contNo" cssClass="inputBox" disabled="true" />
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
													<s:if test="RenewalMode != null ">
													<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
													</s:if>
													<s:else>
													<s:if test="'Layer'.equals(proposalReference)">
													<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId"  cssClass="inputBoxS" headerKey="" headerValue="---Select---"  onchange="toggleVesselBlock(this.value);getTypeOfBusiness(this.value,'typeBus');getBasis(this.value,'rdsBasis');getAjaxCoverClass();" />
													</s:if>
													<s:else>
													<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='%{  "Renewal".equals(proposalReference)||(proposal_no!=null && proposal_no!="")?true:false}' onchange="toggleVesselBlock(this.value);getTypeOfBusiness(this.value,'typeBus');getBasis(this.value,'rdsBasis');getAjaxCoverClass();" />
													</s:else>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<%--<s:text name="label.subClass" />--%>
												</div>
												<div class="tbox" >
													<s:hidden name="subProfit_center" id="subProfit_center" value="D"/>
													<%--<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" id="subProfit_center" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled='%{contNo != null && contNo != ""?true:RenewalMode!=null?false:"Y".equals(disableStatus)?true:false}'/>
													 <s:select list="subProfitList" name="subProfit_center" cssClass="inputBoxS" id="subProfit_center" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled='%{contNo != null && contNo != ""?true:RenewalMode!=null?false:"Y".equals(disableStatus)?true:false}' onclick="getSubDepatmentCover();" />	--%>											
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.profitCentre" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:select list="profit_Centerlist"  listKey="TMAS_PFC_ID" listValue="TMAS_PFC_NAME" name="profit_Center" id="profit_Center" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="%{'Layer'.equals(proposalReference) ||RenewalMode!=null?true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
														<s:text name="label.inwardType" /> &nbsp; <sup style="color:red;">#</sup>
													</s:if>
													<s:else>
														<s:text name="label.inwardType02" /> &nbsp; <sup style="color:red;">#</sup>
													</s:else>
												</div>
												<div class="tbox">
													<s:select list="inwardBusinessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="inwardType" id="inwardType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="%{'Layer'.equals(proposalReference)||RenewalMode!=null?true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.underwriter" /> 
												</div>
												<div class="tbox">
													<s:select list="UnderWritterlist"  listKey="UWR_CODE" listValue="UNDERWRITTER" name="underwriter" id="underwriter" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  onchange="getUWLimit(this.value)"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwCapacityUGX" />(<s:property value="shortname" />)
												</div>
												<div class="tbox" id="uwc2">
													<s:textfield name="maxLimit_Product" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
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
													<s:text name="label.branchLocation" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:select list="polBrlist" listKey="TMAS_POL_BRANCH_ID" listValue="TMAS_POL_BRANCH_NAME" name="polBr" cssClass="inputBoxS" disabled="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.businessType" /> 
												</div>
												<div class="tbox" >
												<div id="typeBus">
												<s:if test="RenewalMode != null">	
													<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetBusinessType(this.value);GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();"  disabled='%{(baseLayer==null  || "".equalsIgnoreCase(baseLayer))?true:false}' />
												</s:if>
												<s:else>
													<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetBusinessType(this.value);GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();" disabled='%{"Y".equals(disableStatus1)?true:false}' />
												</s:else>
												</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedingCompany" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:if test="RenewalMode != null">																												
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />															
														<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
													</s:if>
													<s:else>
														<s:if test="'Layer'.equals(proposalReference)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />
															<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
														</s:if>
														<s:elseif test="baseLayer==null || ''.equals(baseLayer)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled='%{"Y".equals(disableStatus1)?true:false}'  />
															<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
														</s:elseif>
														<s:else>
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />
															<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
														</s:else>
													</s:else>
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.broker" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<%--<s:if test="RenewalMode != null">																												
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />															
														<input type="button"  size="2"  value="..." onclick="functionview(2)" class="pullRight" />
													</s:if>
													<s:else>--%>
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled='%{baseLayer==null  && "".equalsIgnoreCase(baseLayer) || "Y".equals(disableStatus1)?true:false}'  />
														<input type="button"  size="2"  value="..." onclick="functionview(2)" class="pullRight" />
													<%--</s:else>--%>
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.treatyNameType" />
												</div>
												<div class="tbox">
													<s:textfield name="treatyName_type" cssClass="inputBox" maxlength="500"/>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
												<s:if test='"Renewal".equals(proposalReference) || "Layer".equals(proposalReference)'>
												<%--<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;" readonly="%{(proposal_no!='' && proposal_no!=null)?true:false}" disabled='%{("Y".equals(disableStatus1))?true:false}' onkeyup="checkNumbers(this);"/>--%>
													<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);"/>
												</s:if>
												<s:else>
													<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;"   disabled='proposal_no!=null  && ""!=proposal_no?true:false' onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);"/>
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
													<s:text name="label.inceptionDate" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="RenewalMode != null">
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<div class="inputAppend">
															<s:textfield name="incepDate" id="incepDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) ?true:false}' />
															</div>
														</s:if>
														<s:else>
															<div class="inputAppend">
															<s:textfield name="incepDate" id="incepDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) ?true:false}' />
															</div>
														</s:else>
													</s:if>
													<s:else>
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<s:if test="prclFlag == true">
																<div class="inputAppend">
																<s:textfield name="incepDate" id="incepDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"    readonly="true"/>
																</div>
															</s:if>
															<s:else>
																<div class="inputAppend">
																<s:textfield name="incepDate" id="incepDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) ?true:false}' />
																</div>																
															</s:else>
														</s:if>
														<s:else>
															<div class="inputAppend">
															<s:textfield name="incepDate" id="incepDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) ?true:false}' />
															</div>
														</s:else>
													</s:else>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.expiryDate" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="layerProposalNo == null || layerProposalNo == ''">
														<div class="inputAppend">
														<s:textfield name="expDate" id="expDate"  cssStyle="width: 100%; border:transparent;"  onkeyup="validateSpecialChars(this)"  disabled='%{"Layer".equals(proposalReference)|| "Y".equals(disableStatus)?true:false}'/>
														</div>														
													</s:if>
													<s:else>
													<div class="inputAppend">
													<s:textfield name="expDate" id="expDate"  cssStyle="width: 100%; border:transparent;"  onkeyup="validateSpecialChars(this)"  disabled='%{"Layer".equals(proposalReference)|| "Y".equals(disableStatus)?true:false}'/>
													 </div>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYear" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="yearId">
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='%{"Layer".equals(proposalReference)||(contNo != "" && contNo != null)?true:false}' />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.acceptanceDate" /> 
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:textfield name="accDate" id="accDate"  cssStyle="width: 100%; border:transparent;"  onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" disabled='%{"Y".equals(disableStatus1)?true:false}'  />
													</div>													
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.originalCurrency" />
												</div>
												<div class="tbox">
													<s:select list="orginalCurrencylist" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" name="orginalCurrency" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetExchangeRate()" disabled='%{"Y".equals(disableStatus1)?true:false}'  />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.exchangeRate" /> 
												</div>
												<div class="tbox">
												<%-- <div style="width: 40%; float:left;">
													<s:radio list="#{'A':'Auto','F':'Fixed'}" name="exchangeType" value="%{exchangeType==null?'A':exchangeType}" onclick="GetExchangeType(this.value)"  />
													</div> --%>
												<div  id="exRate">
													<s:textfield name="exchRate" id="exchRate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.Retro" /> 
												</div>
												<div class="tbox">													
													<s:radio list="#{'Y':'Yes','N':'No'}" name="spRetro" onclick="GetSp(this.value)" value="%{(spRetro==null || spRetro=='')?'Y':spRetro}" /><%--disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.noofRetroContracts" />
												</div>
												<div class="tbox">													
													<s:textfield cssClass="inputBox" name="no_Insurer" id="NoOFInsur" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" maxlength="3"/>			<%-- disabled='%{"Y".equals(disableStatus1)?true:false}'  --%>										
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>									
							<jsp:include page="/WEB-INF/jsp/common/territoryPopUp.jsp" />
							<div class="boxcontent" id="VesselBlock">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">													
											<div class="textfield">
												<div class="text">
													<s:text name="label.limitperVesselOC" />
												</div>
												<div class="tbox">
													<s:textfield name="limitPerVesselOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value);" maxlength="26"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.limitperLocationOC" />
												</div>
												<div class="tbox">
													<s:textfield name="limitPerLocationOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value);" maxlength="26"/>
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
											<!--<div class="textfield">
													<div class="text">
														<s:text name="label.territory" />
													</div>
													<div class="tbox">
														<s:select list="Territortylist" listKey="TERRITORY_CODE" listValue="TERRITORY_DESC" name="territory" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />													
													</div>
												</div>
												--><div class="textfield">
													<div class="text">
														<s:text name="label.territoryScope" /> 
													</div>
													<div class="tbox">
														<s:textfield name="territoryscope" cssClass="inputBox" maxlength="500"/>													
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="label.portfolioCovered" />
													</div>
													<div class="tbox">
														<s:textfield name="portfoloCovered" cssClass="inputBox" maxlength="50"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="label.basis" />
													</div>
													<div class="tbox">
													<div id="rdsBasis">
														<s:select list="Basislist" listValue="DETAIL_NAME" listKey="TYPE" name="basis" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='%{(contNo != "" && contNo != null)?true:false}'/>													
													</div>
													</div>
												</div>
												<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												 <div class="textfield">
													<div class="text">
														<s:text name="label.perilCovered" />
													</div>
													<div class="tbox">
														<s:select list="perilCoveredlist" id="perilCovered" listValue="DETAIL_NAME" multiple="true" size="1" listKey="TYPE" name="perilCovered" cssClass="inputBoxS" headerKey="0" headerValue="None" />													
													</div>
												</div>
												</s:if>
												<s:else>
													<s:hidden name="perilCovered" id="perilCovered"/>
												</s:else>
												
												<div class="textfield">
													<div class="text">
														<s:text name="label.LOCIssued" /> 
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="LOCIssued" value="%{LOCIssued==null?'N':LOCIssued}" onchange="getLocDeta(this.value)" />													
													</div>
												</div>
												<div class="textfield" id="bnkId" style="display:none;">
												<div class="text">
														<s:text name="label.bankName" /> 
													</div>
													<div class="tbox">													
													<s:textfield name="locBankName" id="locBankName" cssClass="inputBox"  maxlength="100" />
													</div>
												
											</div>
											<div class="textfield" id="crdPId" style="display:none;">
												<div class="text">
														<s:text name="label.creditPrd" /> 
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditPrd" id="locCreditPrd" cssClass="inputBox"   maxlength="26" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);" />
													</div>
												
											</div>
											<div class="textfield" id="crdAId" style="display:none;">
												<div class="text">
														<s:text name="label.creditAmt" /> 
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditAmt" id="locCreditAmt" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26" />
													</div>
												
											</div>
											<div class="textfield" id="benfId" style="display:none;">
												<div class="text">
														<s:text name="label.benficerName" /> 
													</div>
													<div class="tbox">													
													<s:textfield name="locBeneficerName" id="locBeneficerName" cssClass="inputBox"   maxlength="100" />
													</div>
												
											</div>
												<div class="textfield" id="umbrellaID" style="display:none;">
													<div class="text">
														<s:text name="label.umbrellaXL" />
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="umbrellaXL" value="%{umbrellaXL==null?'N':umbrellaXL}" onchange="getUmbrellaXL(this.value);" disabled='%{"Y".equals(disableStatus1)?true:false}' />
													</div>
												</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" id="stoplossno" style="display:none;">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div>
										<div id="newgenid">
										<table class="footable" width="100%" id="newgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<!--<th width="15.8%"> <s:text name="label.subClass" />  </th>
																--><th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitPoc" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePoc" /> </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
																<th width="10.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="CoverList" var="list" status="stat">
															<tr>
																	<td>
												 					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
												 					</td>
																	<td >
																	<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  /><%--disabled="0==(#stat.count-1)?true:false" --%>
																	<%-- <s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})"/>--%>
																	<s:if test='0==(#stat.count-1)'>
																	<s:text name="(Main Class)" />
																	</s:if>
																	</td>
																	<%--<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"  />
																	<s:if test='0==(#stat.count-1)'>
																	<s:text name="(Main Class)" />
																	</s:if>
																	</td>
																	 --%><td>
																	<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  />
																	
																	</td>
																	<td>
																	<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30"  />
																	</td>
																	<td ><%--id='gnpi[<s:property value="%{#stat.count-1}" />]' style="display:none" --%>
																	<s:textfield name="gnpiAsPO[%{#stat.count-1}]" id="gnpiAsPO%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen');"  maxlength="30"  />
																	</td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" />
																	</s:if>
																	</td>
																	<s:hidden name="loopcount" id="loopcount" ></s:hidden>
																</tr>
																</s:iterator>
																<%-- <tr id="gnpi2" style="display:none;" >
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td width="15.8%">Total</td>
																<td>Total</td>
																<td>Total</td>
																</tr>--%>
															</tbody>
															</table>
															<br class="clear"/>
															<s:if test=' !"Y".equals(disableStatus1)'>
															<div class="boxcontent" align="center">
																	<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
															</div>
															</s:if>
														</div>
															<%-- <br class="clear"/>
															<div class="textfield" id="eventID" style="display:none;">
																<div class="text">
																	<s:text name="label.eventLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="event_limit" id="event_limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>
																</div>
															</div>--%>
															
															<br class="clear"/>
															<div class="textfield" id="coverLimitID" style="display: none;">
																<div class="text">
																	<s:text name="label.coverLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="coverLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="26" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</div>
															</div>
															<div class="textfield" id="deductLimitID" style="display: none;">
																<div class="text">
																	<s:text name="label.deductLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="deductLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</div>
															</div>
													
														</div>
													</div>
											</div>
									</div>
							</div>
							<div class="boxcontent" id="stoploss" style="display:none;">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div id="newgenid1">
										<table class="footable" width="100%" id="newgen1">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitAm" />  </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitper" />  </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleAm" /> </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePer" /> </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
																<th width="7%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
															</tr>
															<tr>
																<th></th>
																<th></th>
																<th colspan="2">(Whichever is lower)</th>
																<th colspan="2">(Whichever is higher)</th>
																<th></th>	
																<th ></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="CoverList" var="list" status="stat">
															<tr>
															
																	<td>
																		<s:textfield name="coverSNoS[%{#stat.count-1}]" id="coverSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
																	</td>
																	<td >
																	<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="0==(#stat.count-1) && ('17'!=departId||'18'!=departId ||'19'!=departId)?true:false"/>
																	<%--<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" />--%>
																	<s:if test='0==(#stat.count-1)'>
																	<s:text name="(Main Class)" />
																	</s:if>
																	</td>
																	<td>
																	<s:textfield name="coverLimitAmount[%{#stat.count-1}]" id="coverLimitAmount[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="coverLimitPercent[%{#stat.count-1}]" id="coverLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="deductableLimitAmount[%{#stat.count-1}]" id="deductableLimitAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="deductableLimitPercent[%{#stat.count-1}]" id="deductableLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																	</td>
																	<td>
																	<s:textfield name="egnpiAsPerOffSlide[%{#stat.count-1}]" id="egnpiAsPerOffSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCalSlide()" maxlength="30" />
																	</td>
																	<td ><%--id="gnpislide[<s:property value="%{#stat.count-1}" />]" style="display:none" --%>
																	<s:textfield name="gnpiAsPOSlide[%{#stat.count-1}]" id="gnpiAsPOSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen1');" maxlength="30"   />
																	</td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRowS('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
																	</s:if>
																	</td>
																</tr>
																</s:iterator>
																<s:hidden name="count" id="count" ></s:hidden>
															</tbody>
															</table>
															<br class="clear"/>
															<s:if test=' !"Y".equals(disableStatus1)'>
																<div class="boxcontent" align="center">
																	<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');"  />
																</div>
														</s:if>
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
										<div class="textfield" id="egnpi">
												<div class="text">
													<s:text name="label.egnpiasperoffer" />
												</div>
												<div class="tbox" >
													<s:textfield name="egnpiOffer" id="egnpiOffer" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);CalculateEGNPI('our');CalculateEpi();" maxlength="26" />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.ourassesment" />
												</div>
												<div class="tbox">
													<s:textfield name="ourAssessment"  cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);CalculateEGNPI('our');CalculateEpi();hundredCheck(this.id,this.value);" />
												</div>
											</div>													
											<div class="textfield">
												<div class="text">
													<s:text name="label.subjectPremium" />
												</div>
												<div class="tbox">
													<s:textfield name="subPremium" id="subPremium" cssClass="inputBox"  cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);CalculateEpi();CalculateminimumrateEpi();CalculateEGNPI('epi');" maxlength="26"/>													
												</div>
											</div>
											
											<div class="textfield">
													<div class="text">
														<s:text name="label.pml" />
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="pml" value="%{pml==null?'N':pml}"  onchange="GetPML(this.value);CalculateminimumrateEpi();CalculateEpi();" disabled='%{"Y".equals(disableStatus1)?true:false}' />
													</div>
											</div>
											<div class="textfield" id="pmlp" style="display:none">
												<div class="text">
													<s:text name="label.pmlPercent" />
												</div>
												<div class="tbox">
													<s:textfield name="pmlPercent" id="pmlPercent" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);hundredCheck(this.id,this.value);negative(this.id,this.value);allowOneDot(this);" maxlength="10" onblur="CalculateEpi();CalculateEGNPIPML();CalculateminimumrateEpi();"/>
												</div>
											</div>
											<%-- <div class="textfield" id="pmlepi" style="display:none">
												<div class="text">
													<s:text name="label.egnpiasperpml" />
												</div>
												<div class="tbox">
													<s:textfield name="egnpipml" id="egnpipml" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  maxlength="26" readonly="true"/>													
												</div>
											</div>
										</div>--%>
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
													<s:text name="label.premiumbasis" />
												</div>
												<div class="tbox">
													<s:select list="premiumBasicList" listValue="DETAIL_NAME" listKey="TYPE" name="premiumbasis" cssClass="inputBoxS" headerKey="" headerValue="---None---" onchange="getPremiumBasis();GetPML(this.value);CalculateEpi();CalculateminimumrateEpi();" />
												</div>
											</div>
											<div class="textfield" id="rate" style="display:none">
												<div class="text">
													<s:text name="label.premiumAdjustmentRateP" />
												</div>
												<div class="tbox">
													<s:textfield name="adjRate" id="adjRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateEpi()" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate1" style="display:none">
												<div class="text">
													<s:text name="label.minimumRate" />
												</div>
												<div class="tbox">
													<s:textfield name="minimumRate" id="minimumRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateminimumrateEpi();CalculateEpi()"   onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate2" style="display:none">
												<div class="text">
													<s:text name="label.maximumRate" />
												</div>
												<div class="tbox">
													<s:textfield name="maximumRate" id="maximumRate" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate3" style="display:none">
												<div class="text">
													<s:text name="label.burningCostLF" />
												</div>
												<div class="tbox" >
													<div class="input-group">
													  <span class="input-group-addon" id="basic-addon1">100/</span>
													  <s:textfield name="burningCostLF" id="burningCostLF" cssClass="form-control inputBox" cssStyle="text-align: right; width: 88%;" onkeyup="negative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);allowOneDot(this);hundredCheck(this.id,this.value)" onblur="CalculateminimumrateEpi();CalculateEpi()"  maxlength="20"  />
													</div>																										
												</div>
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
											
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.epiPoc1" />
												</div>
												<div class="tbox">
													<s:textfield name="epi" id="epi" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.minPremium" />
												</div>
												<div class="tbox">
													<s:textfield name="minPremium" id="minPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);mdpremiumval();"  />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.MandDPremiumP" />
												</div>
												<div class="tbox">
													<s:textfield name="m_dPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);jajvascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
																							
											<div class="textfield">
												<div class="text">
													<s:text name="label.MandDInstallments" />
												</div>
												<div class="tbox">
													<s:textfield name="m_d_InstalmentNumber" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" maxlength="3"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.paymentDuedays" />
												</div>
												<div class="tbox">
													<s:textfield name="paymentDuedays" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.proposalStatus" />
												</div>
												<div class="tbox">
												<s:if test="contNo == '' || contNo == null || !'renewal'.equals(flagTest)">
														<s:select list="proposallist" name="proStatus"  id="proStatus" cssClass="inputBoxS" onchange="GetShareSigned(this.value)"  headerKey="" headerValue="--Select--" listKey="TYPE" listValue="DETAIL_NAME" disabled='%{("Y".equals(prodisableStatus))?true:false}'/>
														<s:hidden name="prodisableStatus"></s:hidden>
													</s:if>
													<s:else>
														<s:select list="proposallist" name="proStatus" id="proStatus" cssClass="inputBoxS" onchange="GetShareSigned(this.value)"  headerKey="" headerValue="--Select--" listKey="TYPE" listValue="DETAIL_NAME" disabled="true"/>
													</s:else>
													</div>
											</div>	 
											<div class="textfield">
												<div class="text">
													<s:text name="label.shareWrittenP" />
												</div>
												<div class="tbox">
													<s:textfield name="shareWritt" id="shareWritt" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);hundredCheck(this.id,this.value);allowOneDot(this);" maxlength="26"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.shareSignedP" />
												</div>
												<div class="tbox">
													<s:textfield name="sharSign" id="sharSign" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);hundredCheck(this.id,this.value);allowOneDot(this);" maxlength="26"  /><%--disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
												</div>
											</div>
											<br class="clear">
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
								<s:hidden name="amend_Id_Mode"/>
								<s:if test='amend_Id_Mode == null ||"".equals(amend_Id_Mode)  '>
									<s:if test='proposal_no == null ||"".equals(proposal_no) '>
										<s:if test='layerProposalNo == null ||"".equals(layerProposalNo) '>
											<s:if test='"renewal".equals(flagTest)'>
												<input type="button"  value="Back"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
											</s:if>										
										</s:if>
										
										<s:hidden name="ceddingcompanyBack" value="%{cedingCo}" />
										<% request.setAttribute("LayerMode","Yes");  %>
										<s:if test='"renewal".equals(flagTest)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
										</s:if>
										<s:else>
											 <input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="FunctionEditCancel()" />
										</s:else>
										<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  />
							 			<input type="button"  value="Next"    class="btn btn-sm btn-warning"  id="mybutton1"  onclick="disableForm(this.form,false,'');next()" />
									</s:if>
									<s:else>
										<s:if test='"renewal".equals(flagTest) || "Renewal".equals(proposalReference) || "Layer".equals(proposalReference) '>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"   onClick="return FunctionCancel();" />
											<s:hidden name="proposalNo1"/>
										</s:if>
										<s:elseif test='"R".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"  onClick="FunctionRejectCancel()" />
										</s:elseif>
										<s:elseif test='"N".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="FunctionNotTakenCancel()" />
										</s:elseif>
										<s:else>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"   onClick="FunctionEditCancel()" />
										</s:else>	
										
										<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
							 			<input type="button"  value="Next"   class="btn btn-sm btn-warning"   id="mybutton1" onclick="disableForm(this.form,false,'');funEditSubmit()" />
									</s:else>
								</s:if>
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
									<!--<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  />
									--><input type="button"  value="Next"   class="btn btn-sm btn-warning" id="mybutton1"  onclick="disableForm(this.form,false,'');destroyPopUps();funAmendsubmit()" />
								</s:else>								
							</div>
						</div>
																			
					</div>
					<s:hidden name="manufactureID" id="manufactureID"/>
					<s:hidden name="flag" id="flag"/>
					<s:hidden name="menuId" id="menuId"/>
					<s:hidden name="branchCode" />
					<s:hidden name="xlPremium" value="0" />			
					<s:hidden name="CustomerId" value="cedingCo" />
					<s:hidden name="layerProposalNo" />
					<!--<s:hidden name="brokerage" />
					<s:hidden name="tax" />-->
					<s:hidden name="contractno1" />
					<s:hidden name="renewal_contract_no" />
					<s:hidden name="renewalFlag" />
					<s:hidden name="lay1" />
					<s:hidden name="layerLayerNo" id="layerLayerNo" />
					<s:hidden name="baseLayer" />
					<s:hidden name="baseLoginID" />
					<s:hidden name="amendId" />
					<s:hidden name="contractNo" value="%{contNo}"/>
					<!--<s:hidden name="contNo"/>
					--><s:hidden name="edit" />
					<s:hidden name="cedType" id="cedType" />
					<s:hidden name="endorsmentno"/>
					<s:hidden name="proposalNo" id="proposalNo" value="%{proposal_no}"/>
					<s:hidden name="mode" id="mode"/>
					<s:hidden name="limitOrigCur"/>
					<s:hidden name="deduc_hunPercent"/>
					<s:hidden name="layerMode"/>
					<s:hidden name="countryExcludedList" id="countryExcludedList"/>
					<s:hidden name="countryIncludedList" id="countryIncludedList"/>
					<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>
					<s:hidden name="proposalReference" id="proposalReference"/>
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
	if(value=="4" || value=="2" || value=="10")
	 {
		document.getElementById('VesselBlock').style.display='block';
	 }   
	 else{   
	 	document.getElementById('VesselBlock').style.display='none';
	 }
}
function getDepatmentCover()
{
  	var val1 =document.getElementById("departId").value;
  	
  	if(val1=="17" || val1=="18" || val1=="19" ){
  	document.getElementById("coverdepartId[0]").disabled=false;
  	document.getElementById("coverdepartIdS[0]").disabled=false;
  	}else{
	document.getElementById("coverdepartId[0]").value=val1;
	document.getElementById("coverdepartIdS[0]").value=val1;
	document.getElementById("coverdepartId[0]").disabled=true;
  	document.getElementById("coverdepartIdS[0]").disabled=true;
	}
}
function getSubDepatmentCover()
{
	 //subclass//var data=$('#subProfit_center').val();
	 //$("#coversubdepartId0").val(data);
	 //$("#coversubdepartId0").multiselect("refresh");
}
function getAjax(value,id)
{
	var URL='${pageContext.request.contextPath}/ajaxValueXol.action?departId='+value+'&dropDown=subclass';
	postRequest(URL,'subclass');
}
<%-- function getAjax(value,id)
{
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueXol.action?departId='+value+'&dropDown=subclass',
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#subProfit_center').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		onChange: function(element, checked) {
          		var val = $('#subProfit_center').val();
          		if (val != null && val[0]=='ALL') {
          		$("#subProfit_center").multiselect('clearSelection');
          		$("#subProfit_center").val('ALL');
          	 	$("#subProfit_center").multiselect("refresh");
          }
      	}                     
    });
        }
        });
       // getAjaxCover(value,'coverdepartid0','0');
}
function getAjaxCover(value,id,count)
{
alert();
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueXol.action?departId='+value+'&dropDown=coversubclass&id='+count,
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		onChange: function(element, checked) {
          		var val = $('#coversubdepartId'+count).val();
          		if (val != null && val[0]=='ALL') {
          		$("#coversubdepartId"+count).multiselect('clearSelection');
          		$("#coversubdepartId"+count).val('ALL');
          	 	$("#coversubdepartId"+count).multiselect("refresh");
          }
      	}                     
    });
        }
        });
        
}
--%>
function getAjaxCoverClass(){
var drop = "";
var id="";
var value =document.xol1.departId.value;
var proposal_no = document.xol1.proposal_no.value;
var business=document.xol1.businessType.value;
document.getElementById('departId').disabled = false;
if(business!='5'){ 
	 drop = "newgen";
}else if(business=='5'){
	drop = "newgen1";
} 
if(business!=''){ 
document.getElementById("mode").value = "class";
    	if("newgen"==drop){
    	
    	$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?dropDown=newgen', "newgenid", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        getEgnpiCal();
        CalculateEGNPI();
        getDepatmentCover('our');
        if(proposal_no!=''){
        document.getElementById('departId').disabled = true;
        }
        }
			});
    	}else if("newgen1"==drop){
    	$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?dropDown=newgen1', "newgenid1", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //alert("1");
        getEgnpiCalSlide();
        CalculateEGNPI('our');
        getDepatmentCover();
        if(proposal_no!=''){
        document.getElementById('departId').disabled = true;
        }
        }
			});
    	} 
    }
}

function next()
{
destroyPopUps();
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
var business=document.xol1.businessType.value;
if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	//document.xol1.action="checkXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
	document.xol1.action="checkXol.action";
	document.xol1.submit();
}
function funsubmit()
{
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
		 if(Diff<=0) 
		 {
			 alert("Inception Date Invalid ..You are In Renewal Mode")
		 }
		 else
		 {
		 	replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
		 	document.xol1.action="${pageContext.request.contextPath}/checkRiskDetails";
		 	document.xol1.submit();
		 }
	</s:if>
	<s:else>
			replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');		
			document.xol1.action="${pageContext.request.contextPath}/checkRiskDetails";
			document.xol1.submit();
	</s:else>
	}
}

function FunctionEdit()
{	
	document.xol1.action="${pageContext.request.contextPath}/EditModeRiskDetails";
	document.xol1.submit();
}

function funEditSubmit()
{
destroyPopUps();
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
	if(chkAccAmt()){
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	document.xol1.action="FirstPageUpdateModeXol.action";
	document.xol1.submit();
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
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	document.xol1.action="FirstPageSaveMethodXol.action";
	document.xol1.submit();
}

function FunctionView()
{
	document.xol1.action="${pageContext.request.contextPath}/ViewMethodRiskDetails.action"
	document.xol1.submit();
}

function funAmendsubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.xol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
		document.xol1.action="FirstPageUpdateModeXol.action";
		document.xol1.submit();
}

function FunctionAmendSaveOption()
{
	document.xol1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIDFirstPageSaveMathod"
	document.xol1.submit();
}

function FunctionEditCancel()
{
	destroyPopUps();
	document.xol1.action ="${pageContext.request.contextPath}/commonListPortfolio.action";
	document.xol1.submit();
}


function FunctionRejectCancel()
{
	destroyPopUps();
	document.xol1.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.xol1.submit();
}
function FunctionNotTakenCancel(){
	destroyPopUps();
	document.xol1.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.xol1.submit();
}

function AmendIdBack()
{	
	destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	document.getElementById("proposalNo").disabled = false;
	document.xol1.action= "${pageContext.request.contextPath}/InitPortfolio.action?endtMode=endorsment&endorsementStatus=N";
	document.xol1.submit();
}

<%--function calculation()
{
	//var limit=document.xol1.limitOrigCur.value;
	//limit=limit.replace(new RegExp(',', 'g'),'');
	var Epi100=document.xol1.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');

	var ourEsitamate=document.xol1.ourEstimate.value;
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" )
	{
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.xol1.epi.value=Comma(finalvalue.toFixed(2));
	}else
	document.xol1.epi.value='0';
}--%>

function GetShareSigned(value)
{
		
		if(value=='A')
		{
		document.xol1.sharSign.disabled=false;
		}
		else  
		{
		document.xol1.sharSign.value='';
		document.xol1.sharSign.disabled=true;
		}
		
}

function GetExchangeRate() {
		var incDate=document.forms['xol1'].incepDate.value;
		var accDate=document.forms['xol1'].accDate.value;
		var Currecny=document.xol1.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !=""))
		{
	 		var URL='${pageContext.request.contextPath}/getExcRateXol.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else
		{
			document.xol1.exchRate.value='';
		}
}


function CalculateEpi() {	
var pml = document.xol1.pml.value;
var premiumbasis = document.xol1.premiumbasis.value;
var a = 0;
//if('Y'==pml){
	//var a=document.xol1.egnpipml.value;
	//}
	//else{
	var a=document.xol1.subPremium.value;
	//}
	a=a.replace(new RegExp(',', 'g'),'');
	if('1'==premiumbasis){
	var b=document.xol1.adjRate.value;
	}
	else if('2'==premiumbasis){
	var b=document.xol1.minimumRate.value;
	}
	else if('3'==premiumbasis){
	document.getElementById("epi").readOnly=false;
	}
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.xol1.epi.value=Comma(c.toFixed(2));
	}else {
		document.xol1.epi.value='';
	}
	
}

function CalculateEGNPI(type) {	
	var a=document.xol1.egnpiOffer.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.ourAssessment.value;
	if('our'==type){
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.xol1.subPremium.value=Comma(c.toFixed(2));
	}else {
		document.xol1.subPremium.value='';
	}
	}
	else if('epi'==type){
            var Epi=document.xol1.subPremium.value;
            Epi=Epi.replace(new RegExp(',', 'g'),'');
        if(a!=null && a!="" && Epi!=null && Epi!="" ){
            var finalvalue=(parseFloat(Epi)*100)/parseFloat(a);
            document.xol1.ourAssessment.value=Comma(finalvalue.toFixed(4));
        }
        }
}
function CalculateEGNPIPML() {	
	<%--var a=document.xol1.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.pmlPercent.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var pmlPer=parseFloat(b)/100;
		var c=parseFloat(a)*pmlPer;
		document.xol1.egnpipml.value=Comma(c.toFixed(2));
	}else {
		document.xol1.egnpipml.value='';
	}--%>
}

function mdpremiumval(){
document.xol1.m_dPremium.value = document.xol1.minPremium.value;
}
function CalculateminimumrateEpi() {	
	var type=document.xol1.pml.value;
	var egn = 0;
	//if('Y'==type){
	//  egn=document.xol1.egnpipml.value;
	//}
	//else if('N'==type){
	  egn = document.xol1.subPremium.value;
	//}
	egn=egn.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.minimumRate.value;
	var bas =document.xol1.premiumbasis.value;
	if(bas=="2"){
	if(egn!=null && egn!="" && b!=null && b!="") {
		var minRt=parseFloat(b)/100;
		var c=parseFloat(egn)*minRt;
		document.xol1.minPremium.value=Comma(c.toFixed(2));
		mdpremiumval();
	}
	else {
		document.xol1.minPremium.value='';
		document.xol1.m_dPremium.value ='';
	}
	}
	else {
		document.xol1.minPremium.value='';
		document.xol1.m_dPremium.value ='';
	}
}
<%--function CalculateminimumPremium() {	

//if('Y'==pml){
	//var a=document.xol1.egnpipml.value;
	//}
	//else{
	var a=document.xol1.subPremium.value;
	//}
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.minimumRate.value;
	
	if(a!=null && a!="" && b!=null && b!="") {
		var minRt=parseFloat(b)/100;
		var c=parseFloat(a)*minRt;
		document.xol1.epi.value=Comma(c.toFixed(0));
	}else {
		document.xol1.epi.value='';
	}
}--%>
GetExchangeType('<s:property value="exchangeType"/>');
function GetExchangeType(val){
if(val=='A'){
document.getElementById("exchRate").readOnly=true;
}else if(val=='F'){
document.getElementById("exchRate").readOnly=false;
}

}
function functionview(id){
	var cedding="";
	if(id==1) {
		cedding=document.getElementById("CeddingId").value;
	}
	else {
		cedding=document.getElementById("BrokerId").value;
	}
	var URL ='<%=request.getContextPath()%>/ViewModeCeding?Mode=PopUp&ceddingcompany='+cedding;
	//var URL ='<%=request.getContextPath()%>/CedingCompanyAction.do?method=ViewMode&Mode=PopUp&ceddingcompany='+cedding;
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
		document.xol1.epi_origCur.value=Comma(document.xol1.epi_origCur.value);
		document.xol1.maxLimit_Product.value=Comma(document.xol1.maxLimit_Product.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.xlCost.value=Comma(document.xol1.xlCost.value);
		document.xol1.cedReten.value=Comma(document.xol1.cedReten.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		if(document.xol1.limitPerVesselOC.value!=null && document.xol1.limitPerVesselOC.value!="") {
			document.xol1.limitPerVesselOC.value=Comma(document.xol1.limitPerVesselOC.value);
		}
		if(document.xol1.limitPerLocationOC.value!=null && document.xol1.limitPerLocationOC.value!="") {
			document.xol1.limitPerLocationOC.value=Comma(document.xol1.limitPerLocationOC.value);
		}
	} else if(value=="3") {
		//document.xol1.deduc_hunPercent.value=Comma(document.xol1.deduc_hunPercent.value);
		document.xol1.maxLimit_Product.value=Comma(document.xol1.maxLimit_Product.value);
		//document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.egnpiOffer.value=Comma(document.xol1.egnpiOffer.value);
		document.xol1.subPremium.value=Comma(document.xol1.subPremium.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		document.xol1.m_dPremium.value=Comma(document.xol1.m_dPremium.value);
		if(document.xol1.limitPerVesselOC.value!=null && document.xol1.limitPerVesselOC.value!="") {
			document.xol1.limitPerVesselOC.value=Comma(document.xol1.limitPerVesselOC.value);
		}
		if(document.xol1.limitPerLocationOC.value!=null && document.xol1.limitPerLocationOC.value!="") {
			document.xol1.limitPerLocationOC.value=Comma(document.xol1.limitPerLocationOC.value);
		}
		//document.xol1.event_limit.value=Comma(document.xol1.event_limit.value);
		document.xol1.coverLimitXL.value=Comma(document.xol1.coverLimitXL.value);
		document.xol1.deductLimitXL.value=Comma(document.xol1.deductLimitXL.value);
		//document.xol1.egnpipml.value=Comma(document.xol1.egnpipml.value);
		document.xol1.minPremium.value=Comma(document.xol1.minPremium.value);
	} else if(value=="4") {
		document.xol1.epi_origCur.value=Comma(document.xol1.epi_origCur.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.xlCost.value=Comma(document.xol1.xlCost.value);
	} else if(value=="5") {
		document.xol1.deduc_hunPercent.value=Comma(document.xol1.deduc_hunPercent.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.subPremium.value=Comma(document.xol1.subPremium.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		document.xol1.m_dPremium.value=Comma(document.xol1.m_dPremium.value);
	}
}

/*********************************/

function GetSp(id)
{	
	if(id=='Y')
	{
		document.xol1.no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N')
	{
		document.getElementById("NoOFInsur").readOnly=true;
		document.xol1.no_Insurer.value="0";
	}
}
var per='<s:property value="pml"/>';
if(per=='' || per==null){
GetPML('N');
}else{
GetPML(per);
}
function GetPML(id)
{	
	if(id=='Y')
	{
		//document.xol1.pmlPercent.value='';
		//document.getElementById("pmlPercent").readOnly=false;
		document.getElementById('pmlp').style.display='inline';
 		//document.getElementById('pmlepi').style.display='inline';
	}
	if(id=='N')
	{
		//document.getElementById("pmlPercent").readOnly=true;
		document.getElementById('pmlp').style.display='none';
	 	//document.getElementById('pmlepi').style.display='none';
	 	//document.getElementById('egnpipml').value='0';
	 	document.getElementById('pmlPercent').value='0';
	}
}
getPremiumBasis();
function getPremiumBasis(){ 
	var type= document.xol1.businessType.value;
	var val= document.xol1.premiumbasis.value;
 if(val=='1'){
	document.getElementById('rate').style.display='inline';
	if(type=="5"){
		document.getElementById('rate1').style.display='inline';
	 	document.getElementById('rate2').style.display='inline';
	}
	else{
	 	document.getElementById('rate1').style.display='none';
	 	document.getElementById('rate2').style.display='none';
 	}
	document.getElementById('rate3').style.display='none';
	document.getElementById('burningCostLF').value ='';
	//document.getElementById("minPremium").readOnly=false;   
	 }   
	 
else if(val=='2'||val=='7' ){
	document.getElementById('rate').style.display='none';
 	document.getElementById('rate1').style.display='inline';
 	document.getElementById('rate2').style.display='inline';
	document.getElementById('rate3').style.display='inline';
	document.getElementById('adjRate').value ='';
}else{
	document.getElementById('rate').style.display='none';
 	document.getElementById('rate1').style.display='none';
 	document.getElementById('rate2').style.display='none';
	document.getElementById('rate3').style.display='none';
	document.getElementById('burningCostLF').value ='';
	document.getElementById('minimumRate').value ='';
	document.getElementById('maximumRate').value ='';
	document.getElementById('adjRate').value ='';
}
}
GetBusinessType('<s:property value="businessType"/>');
function GetBusinessType(val){
if( val=='2' ||val=='3' ||val=='7' || val=='8'){
document.getElementById('umbrellaID').style.display='inline';

}else{
document.getElementById('umbrellaID').style.display='none';
}

}
function GetStopLossType(val){
var rowCount = 0;
if(val=='5'){
	document.getElementById('stoploss').style.display='inline';
	document.getElementById('stoplossno').style.display='none';
	var table = document.getElementById("newgen1");
 	rowCount = table.rows.length -2;
 	document.getElementById('count').value = parseInt(rowCount-1);
 	
 	getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6' ||val=='7' || val=='8'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
	var table = document.getElementById("newgen");
 	rowCount = table.rows.length-1;
 	document.getElementById('loopcount').value = parseInt(rowCount);
 	
 	getEgnpiCal();
}else{
	document.getElementById('stoplossno').style.display='none';
	document.getElementById('stoploss').style.display='none';
}
}
GetStopLoss('<s:property value="businessType"/>');
function GetStopLoss(val){
var rowCount = 0;
if(val=='5'){
	document.getElementById('stoploss').style.display='inline';
	document.getElementById('stoplossno').style.display='none';
 	//getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6' ||val=='7'||val=='8'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
 	//getEgnpiCal();
}else{
	document.getElementById('stoplossno').style.display='none';
	document.getElementById('stoploss').style.display='none';
}
}
if("6"=='<s:property value="businessType"/>'){
getUmbrellaVal();
}
else{
getUmbrellaXL('<s:property value="umbrellaXL"/>');
}
function getUmbrellaXL(val){
if(val=='Y'){
document.getElementById('coverLimitID').style.display='inline';
document.getElementById('deductLimitID').style.display='inline';
}
else{
document.getElementById('coverLimitID').style.display='none';
document.getElementById('deductLimitID').style.display='none';
}
}


function getUmbrellaVal(){
var val = document.getElementById("businessType").value;
if(val=='6'){
document.getElementById('coverLimitID').style.display='inline';
document.getElementById('deductLimitID').style.display='inline';
}
else{
document.getElementById('coverLimitID').style.display='none';
document.getElementById('deductLimitID').style.display='none';
}
}

function functionDate()
{
var	inceptionDate=document.xol1.incepDate.value;
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
		document.xol1.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.xol1.uwYear.value=date.getFullYear();
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
		document.xol1.expDate.value=d+"/"+m+"/"+y;	
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
	var inception=document.xol1.incepDate.value;
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
	var expery=(document.xol1.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
	if(diffDays>0)
	{
		alert('Expiry Date More than One Year');
	}
}

function chkAccAmt(){
var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	var limitOC=document.xol1.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.xol1.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.xol1.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.xol1.proStatus.value=="A")
	{	 
		cedPer=document.xol1.sharSign.value;
	}else
	{
		cedPer=document.xol1.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.xol1.sharSign.value;
	</s:if>	
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit)
		{
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt)
			{
				<s:if test='contNo == "" && contNo == null'>				
					document.xol1.proStatus.value='P';
					document.xol1.sharSign.value='';
				</s:if>
			}else
			{
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

function toggleXLCostOC(retroType)
{
	if(retroType=="SR")
	{
		document.xol1.xlCost.value="0";
		document.xol1.xlCost.readOnly=true;
		document.xol1.insuredName.readOnly=false; 
	}else
	{
		//document.xol1.xlCost.value="";
		document.xol1.xlCost.readOnly=false;
		document.xol1.insuredName.value="";
		document.xol1.insuredName.readOnly=true; 
	}
	
}
if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.xol1.underwriter.value);
}
function getUWLimit(value){
   //var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitXol.action?underwriter='+value+'&dropDown=uwc2';
	postRequest(URL,'uwc2');
}
<%-- function insRow(tableID)
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
			cell3.id="coverdepartid"+(rowCount-1);
			createcoversubdeptCell(cell3, rowCount)
		
		
			var cell4 = row.insertCell(3);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "coverLimitOC["+(rowCount-1)+"]";
      		element2.id = "coverLimitOC["+(rowCount-1)+"]";
      		element2.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element2.setAttribute("maxlength",'30');
			element2.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element2);
			
			var cell5 = row.insertCell(4);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "deductableLimitOC["+(rowCount-1)+"]";
			element3.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element3.id = "deductableLimitOC["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element3.setAttribute("maxlength",'30');
			element3.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell5.appendChild(element3);
			
			
			var cell6 = row.insertCell(5);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "egnpiAsPerOff["+(rowCount-1)+"]";
      		element4.id = "egnpiAsPerOff"+(rowCount-1);
			element4.value=document.getElementById("egnpiAsPerOff"+(parseFloat(rowCount)-2)).value;
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();");
			element4.setAttribute("maxlength",'30'); 
			element4.setAttribute("style", "text-align:right;");
			cell6.appendChild(element4); 
			
			var cell7 = row.insertCell(6);
			cell7.setAttribute("align","center");
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			element5.setAttribute("onclick", "deleteRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-danger"
			cell7.appendChild(element5);
			//var val = document.getElementById("loopcount").value ;
			//if(val == ''){
			// document.getElementById("loopcount").value =document.getElementById("count").value;
			// }
			// else{
			// document.getElementById("loopcount").value =parseInt(val)+1;
			// }
			 document.getElementById("loopcount").value =parseInt(rowCount);			 
			 getEgnpiCal();
			 
}
--%>
function insRow(tableID)
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
			//cell2.id = "coverdepartIdtable"+(rowCount-1);
			createcoverdeptCell(cell2, rowCount)
		
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "coverLimitOC["+(rowCount-1)+"]";
      		element2.id = "coverLimitOC["+(rowCount-1)+"]";
      		element2.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element2.setAttribute("maxlength",'30');
			element2.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell3.appendChild(element2);
			
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "deductableLimitOC["+(rowCount-1)+"]";
			element3.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element3.id = "deductableLimitOC["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element3.setAttribute("maxlength",'30');
			element3.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element3);
			
			
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "egnpiAsPerOff["+(rowCount-1)+"]";
      		element4.id = "egnpiAsPerOff"+(rowCount-1);
			element4.value=document.getElementById("egnpiAsPerOff"+(parseFloat(rowCount)-2)).value;
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();");
			element4.setAttribute("maxlength",'30'); 
			element4.setAttribute("style", "text-align:right;");
			cell5.appendChild(element4); 
			
			
			var cell6 = row.insertCell(5);
			//cell6.id='gnpi['+(rowCount-1)+']';
			//cell6.style.display ='none';
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "gnpiAsPO["+(rowCount-1)+"]";
      		element5.id = "gnpiAsPO"+(rowCount-1);
			//element5.value=document.getElementById("gnpiAsPO"+(parseFloat(rowCount)-2)).value;
			element5.setAttribute("disabled",true);
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element5.setAttribute("maxlength",'30'); 
			element5.setAttribute("style", "text-align:right;");
			cell6.appendChild(element5); 
			
			
			var cell7 = row.insertCell(6);
			cell7.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');deleteRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-danger"
			cell7.appendChild(element6);
			//var val = document.getElementById("loopcount").value ;
			//if(val == ''){
			// document.getElementById("loopcount").value =document.getElementById("count").value;
			// }
			// else{
			// document.getElementById("loopcount").value =parseInt(val)+1;
			// }
			 document.getElementById("loopcount").value =parseInt(rowCount);			 
			 getEgnpiCal();
			 getAjaxCoverClass();
			 
}
function insRow1(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "coverSNoS["+(rowCount-2)+"]";
       		element1.id = "coverSNoS["+(rowCount-2)+"]";
 			element1.value = rowCount-1;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
			
			var cell2 = row.insertCell(1);
			//cell2.id = "coverdepartIdtable1"+(rowCount-2);
			createcoverdeptCell1(cell2, rowCount)
		
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "coverLimitAmount["+(rowCount-2)+"]";
      		element2.id = "coverLimitAmount["+(rowCount-2)+"]";
			element2.value=document.getElementById("coverLimitAmount["+(rowCount-3)+"]").value;
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			element2.setAttribute("maxlength",'30'); 
			element2.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell3.appendChild(element2);
			
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "coverLimitPercent["+(rowCount-2)+"]";
      		element3.id = "coverLimitPercent["+(rowCount-2)+"]";
			element3.value=document.getElementById("coverLimitPercent["+(rowCount-3)+"]").value;
      		element3.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);");
			element3.className = "inputBox";
			element3.setAttribute("maxlength",'3'); 
			element3.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element3);
			
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "deductableLimitAmount["+(rowCount-2)+"]";
      		element4.id = "deductableLimitAmount["+(rowCount-2)+"]";
			element4.className = "inputBox";
			element4.value=document.getElementById("deductableLimitAmount["+(rowCount-3)+"]").value;
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			element4.setAttribute("maxlength",'30'); 
			element4.setAttribute("style", "text-align:right;");
			//element4.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell5.appendChild(element4);
			
			var cell6 = row.insertCell(5);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "deductableLimitPercent["+(rowCount-2)+"]";
      		element5.id = "deductableLimitPercent["+(rowCount-2)+"]";
			element5.value=document.getElementById("deductableLimitPercent["+(rowCount-3)+"]").value;
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);");
			element5.setAttribute("maxlength",'3'); 
			element5.setAttribute("style", "text-align:right;");
			//element5.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell6.appendChild(element5);
			
			var cell7 = row.insertCell(6);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "egnpiAsPerOffSlide["+(rowCount-2)+"]";
      		element6.id = "egnpiAsPerOffSlide"+(parseInt(rowCount)-2);
			element6.value=document.getElementById("egnpiAsPerOffSlide"+(parseInt(rowCount)-3)).value;
			element6.className = "inputBox";
			element6.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCalSlide();");
			element6.setAttribute("maxlength",'30'); 
			element6.setAttribute("style", "text-align:right;");
			cell7.appendChild(element6);
			
			var cell8 = row.insertCell(7);
			//cell8.id='gnpislide['+(rowCount-2)+']';
			//cell8.style.display ='none';
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "gnpiAsPOSlide["+(rowCount-2)+"]";
      		element7.id = "gnpiAsPOSlide"+(rowCount-2);
			//element7.value=document.getElementById("gnpiAsPO"+(parseFloat(rowCount)-2)).value;
			element7.setAttribute("disabled",true);
			element7.className = "inputBox";
			element7.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			cell8.appendChild(element7);
			
			
			var cell9 = row.insertCell(8);
			cell9.setAttribute("align","center"); 
			var element8 = document.createElement("input");
			element8.type = "button";
			element8.value="Delete";
			element8.setAttribute("onclick", "disableForm(this.form,false,'');deleteRowS('"+(rowCount-2)+"')");
			element8.className="btn btn-sm btn-danger"
			cell9.appendChild(element8);
			//var val = document.getElementById("loopcount").value ;
			//if(val == ''){
			 //document.getElementById("loopcount").value =document.getElementById("count").value;
			// }
			// else{
			// document.getElementById("loopcount").value =parseInt(val)+1;
			// }
			 document.getElementById("count").value =parseInt(rowCount-1);
			 getEgnpiCalSlide();
			 getAjaxCoverClass();
}
function createcoverdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartId["+(rowCount-1)+"]";
      	element.id = "coverdepartId["+(rowCount-1)+"]";
       element.className = "inputBox";
      	//element.setAttribute("onchange", "getAjaxCover(this.value,'coverdepartid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function createcoversubdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coversubdepartId["+(rowCount-1)+"]";
      	element.id = "coversubdepartId["+(rowCount-1)+"]";
       element.className = "inputBoxS coversubdepartId";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoversubdept(element);
          cell.appendChild(element);
}
function createcoverdeptCell1(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartIdS["+(rowCount-2)+"]";
      	element.id = "coverdepartIdS["+(rowCount-2)+"]";
       element.className = "inputBox";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
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
				objOption.text = "<s:property value='TMAS_DEPARTMENT_NAME' />".replace("&amp;", "&") ;
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
          objOption.text = 'ALL';
          objOption.value = 'ALL';
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
 function deleteRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartId[0]").disabled = false; 
		 document.getElementById("mode").value = "delete";
		$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?deleteId='+val+'&dropDown=newgen', "newgenid", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        getEgnpiCal();
        CalculateEGNPI();
        getDepatmentCover();
        }
			});
			}
		}
}
function deleteRowS(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartIdS[0]").disabled = false;
		document.getElementById("mode").value = "delete";
			$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?deleteId='+val+'&dropDown=newgen1', "newgenid1", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //alert("1");
        getEgnpiCalSlide();
        CalculateEGNPI();
        getDepatmentCover();
        }
			});
			}
		}
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

function removeElementsWithValue(arr, val) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === val) {
            arr.splice(i, 1);
        }
    }
    return arr;
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
function FunctionCancel(){
	destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	document.getElementById("proposal_no").disabled =false;
	var no =document.getElementById("proposal_no").value;
	var old = document.getElementById("renewalProposalNo").value;
	if('Renewal'==val || 'Layer'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("proposalNo").value = old;
	document.getElementById("renewalProposalNo").value=no;
			document.xol1.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action';
			document.xol1.submit();
			}
			else {
			document.getElementById("proposal_no").disabled =true;
				 return false; 
			}
		}
		else{
			document.xol1.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.xol1.submit();
		}
	
}
function getEgnpiCalSlide(){
	var texts = document.getElementById("count").value;
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("egnpiAsPerOffSlide"+i).value;
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("egnpiOffer").value = Comma( parseFloat(sum).toFixed(2));
			
}
function getEgnpiCal(){
	var texts = document.getElementById("loopcount").value;
	
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("egnpiAsPerOff"+i).value;
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("egnpiOffer").value =  Comma(parseFloat(sum).toFixed(2));
			
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

<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
	enableForm1(document.xol1,true,'<s:property value="%{fields}"/>');
</s:if>
<%--
$(document).ready(function() { 
     
    $('#subProfit_center').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
          var val = $('#subProfit_center').val();
          if (val[0]=='ALL') {
          	$("#subProfit_center").multiselect('clearSelection');
          	$("#subProfit_center").val('ALL');
          	 $("#subProfit_center").multiselect("refresh");
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
 var val=document.getElementById('loopcount').value;
$(document).ready(function() {
    $("#coversubdepartId"+val).multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
          var val1 = $("#coversubdepartId"+val).val();
          if (val1[0]=='ALL') {
          	$("#coversubdepartId"+val).multiselect('clearSelection');
          	$("#coversubdepartId"+val).val('ALL');
          	 $("#coversubdepartId"+val).multiselect("refresh");
          }
      	}                     
    }); 
  
 
 		
});

//alert('<s:property value="coversubdepartId[0]"/>'+'out');
 <s:iterator value="CoverList"  status="stat">   
  var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
</s:iterator>
--%>

getFieldDisable();
function getFieldDisable(){
var val=document.getElementById("endorsmenttype").value;
	var type = document.getElementById("businessType").value;
	var table='';
	if("GNPI"==val){
		var form = document.getElementById("xol1");
		var elements = form.elements;
		for (var i = 0, len = elements.length; i < len; ++i) {
		    elements[i].disabled = true;
		}
		document.getElementById("mybutton").disabled = false;
		document.getElementById("mybutton1").disabled = false;
		
		if(type=='5'){
			//document.getElementById("gnpiS").style.display = "table-cell";
			//document.getElementById("gnpiE").style.display = "table-cell";
			//document.getElementById("gnpi").style.display = false;
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-2;
			for(var c= 0;c<rowCount;c++){
			//document.getElementById("gnpislide["+c+"]").style.display = "table-cell";
			document.getElementById("gnpiAsPOSlide"+c).disabled = false;
			}
			insRowEmptySlide('newgen1');
		}
		else{
			//document.getElementById("gnpiS").style.display = false;
			//document.getElementById("gnpiE").style.display = false;
			//document.getElementById("gnpi").style.display = "table-cell";
			table = document.getElementById('newgen');
			var rowCount = table.rows.length-1;
			for(var c= 0;c<rowCount;c++){
			//document.getElementById("gnpi["+c+"]").style.display = "table-cell";
			document.getElementById("gnpiAsPO"+c).disabled = false;
			}
			insRowEmpty('newgen');
		}
	}	
	else{
	if(type=='5'){
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-2;
			for(var c= 0;c<rowCount;c++){
			document.getElementById("gnpiAsPOSlide"+c).disabled = true;
			}
		}
		else{
			table = document.getElementById('newgen');
			var rowCount = table.rows.length-1;
			for(var c= 0;c<rowCount;c++){
			document.getElementById("gnpiAsPO"+c).disabled = true;
			}
	}
}
}
function insRowEmpty(tableID)
{
var table = document.getElementById(tableID);
var rowCount = table.rows.length;
var val=0;
var val1=0;
if('newgen1' == tableID){
	for(var c= 0;c<rowCount-2;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	 
	 var sum1 = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	}
}
else{
	 for(var c= 0;c<rowCount-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		 sum= sum.replace(new RegExp(',', 'g'),'');
	 	 val +=parseFloat(sum);		
	 	
	 	var sum1 = document.getElementById("gnpiAsPO"+c).value;
	 	sum1= sum1.replace(new RegExp(',', 'g'),'');
		val1 +=parseFloat(sum1);
	 	}
}
val =Comma(val.toFixed(2));
val1 =Comma(val1.toFixed(2));
var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.innerText = "Total" ;
            td.align="Left";
            tr.appendChild(td);
  			var td1 = document.createElement("td");
            td1.innerText = " " ;
            tr.appendChild(td1);
            var td2 = document.createElement("td");
            td2.innerText = " " ;
            tr.appendChild(td2);
            var td3 = document.createElement("td");
            td3.innerText = " " ;
            tr.appendChild(td3);
            var td4 = document.createElement("td");
            var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "egnpiTotaol";
      		element5.id = "egnpiTotaol";
      		element5.value=val;
			element5.className = "inputBox";
			element5.setAttribute("style", "text-align:right;");
			element5.setAttribute("readonly",'true');
            td4.appendChild(element5);
            tr.appendChild(td4);
            var td5 = document.createElement("td");
            var element7 = document.createElement("input");
            element7.type = "text";
			element7.name = "Total";
      		element7.id = "Total";
      		element7.value=val1;
			element7.className = "inputBox";
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			element7.setAttribute("readonly",'true');
			td5.append(element7);
            tr.appendChild(td5);
            var td6 = document.createElement("td");
            td6.innerText = " ";
            tr.appendChild(td6);
            table.children[1].appendChild(tr);
            
}

function insRowEmptySlide(tableID)
{
var table = document.getElementById(tableID);
var rowCount = table.rows.length;
var val=0;
var val1=0
if('newgen1' == tableID){
	for(var c= 0;c<parseInt(rowCount)-2;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	 
	  var sum1 = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	}
}
else{
	 for(var c= 0;c<parseInt(rowCount)-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
	 	val +=parseFloat(sum);	
	 	
	 	var sum1 = document.getElementById("gnpiAsPO"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	 	}
}
val =Comma(val.toFixed(2));
val1 =Comma(val1.toFixed(2));
var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.innerText = "Total" ;
            tr.appendChild(td);
  			var td1 = document.createElement("td");
            td1.innerText = " " ;
            tr.appendChild(td1);
            var td2 = document.createElement("td");
            td2.innerText = " " ;
            tr.appendChild(td2);
            var td3 = document.createElement("td");
            td3.innerText = " " ;
            tr.appendChild(td3);
            var td4 = document.createElement("td");
            td4.innerText = " " ;
            tr.appendChild(td4);
            var td5 = document.createElement("td");
            var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "egnpiTotaol";
      		element5.id = "egnpiTotaol";
      		element5.value=val;
			element5.className = "inputBox";
			element5.setAttribute("style", ";text-align:right;");
			element5.setAttribute("readonly",'true');
            td5.appendChild(element5);
            tr.appendChild(td5);
            var td6 = document.createElement("td");
            tr.appendChild(td6);
           	var td7 = document.createElement("td");
            var element7 = document.createElement("input");
            element7.type = "text";
			element7.name = "Total";
      		element7.id = "Total";
      		element7.value=val1;
			element7.className = "inputBox";
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("readonly",'true'); 
			element7.setAttribute("style", ";text-align:right;");
			td7.append(element7);
            tr.appendChild(td7);
            var td8 = document.createElement("td");
            tr.appendChild(td8);
            table.children[1].appendChild(tr);
}

function getEgnpiVal(tableID){
var table = document.getElementById(tableID);
var val=0;
var rowCount = table.rows.length-2;

if('newgen1' == tableID){
	for(var c= 0;c<rowCount;c++){
	 var sum = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 if(sum==''){
	 sum =0;
	 }
	 val +=parseFloat(sum);
	}
	val =Comma(val.toFixed(2));
	document.getElementById("egnpiTotaol").value=val;
}
else{
	 for(var c= 0;c<rowCount;c++){
		 var sum = document.getElementById("gnpiAsPO"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
		if(sum==''){
		 sum =0;
		 }
	 	val +=parseFloat(sum);		
	 	}
	 	val =Comma(val.toFixed(2));
	document.getElementById("Total").value=val;
}

}

 function getViewContractDetails(proposalNo,amendId) {
		var URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
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
	
	function getTypeOfBusiness(val,id){
			var URL='${pageContext.request.contextPath}/getTypeOfBusinessXol.action?dropDown='+id+'&departId='+val;
  	 		postRequest(URL,id);
	}
	function getBasis(val,id){
			var URL='${pageContext.request.contextPath}/getTypeOfBusinessXol.action?dropDown='+id+'&departId='+val;
  	 		postRequest(URL,id);
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
