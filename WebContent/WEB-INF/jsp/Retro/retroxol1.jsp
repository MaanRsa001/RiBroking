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
		$( "#accDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
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
	  });	
	  </script>	
</head>
<body onload="Commas(<s:property value="#session.mfrid" />),setCedRetType('<s:property value="cedRetenType" />')">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="retroxol1" id="retroxol1" theme="simple" action="" method="post" autocomplete="off">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow" align="center">
							<span class="pageHeading">							
							<s:text name="label.riskDetailSheetRetroXol" />
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
													<s:select list="endosTypelist" name="endorsmenttype" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" onchange="getFieldDisable(this.value)"  />
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
													<s:if test="RenewalMode != null || (proposal_no!=null && proposal_no!='')">
													<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjax(this.value,'subclass');getAjaxCoverClass();getBasis(this.value,'rdsBasis');"/>													
													</s:if>
													<s:else>
													<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='%{(contNo==null || "".equals(contNo))?false:"Y".equals(disableStatus)?true:false}' onchange="getAjax(this.value,'subclass');getAjaxCoverClass();getBasis(this.value,'rdsBasis');" />
													</s:else>
												</div>
											</div>
											<div class="textfield">
											<s:hidden name="subProfit_center" id="subProfit_center" value="D"/></div>
											<!--<div class="textfield">
												<div class="text">
													<s:text name="label.subClass" />
												</div>
												<div class="tbox" id="subclass">
													<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled="%{RenewalMode!=null?true:false}" />
													<%--<s:select list="subProfitList" name="subProfit_center" cssClass="inputBoxS" id="subProfit_center" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="%{RenewalMode!=null?true:false}" /> --%>												</div>
											</div>
											--><div class="textfield">
												<div class="text">
													<s:text name="label.profitCentre" />
												</div>
												<div class="tbox">
													<s:select list="profit_Centerlist" listKey="TMAS_PFC_ID" listValue="TMAS_PFC_NAME" name="profit_Center" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="%{RenewalMode!=null?true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.underwriter" />
												</div>
												<div class="tbox">
													<s:select list="UnderWritterlist" listKey="UWR_CODE" listValue="UNDERWRITTER" name="underwriter" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="%{RenewalMode!=null?true:false}" />
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
													<s:select list="polBrlist" listKey="TMAS_POL_BRANCH_ID" listValue="TMAS_POL_BRANCH_NAME" name="polBr" cssClass="inputBoxS" disabled="%{RenewalMode!=null?true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.businessType" />
												</div>
												<div class="tbox">
													<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="getDepatmentCover();GetBusinessType(this.value);GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getAjaxCoverClass();"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.retroCessionLeader" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:if test="RenewalMode != null">																												
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />															
														<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />																										
													</s:if>
													<s:else>
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="%{contNo==null?false:''.equals(contNo)?false:true}"  />
														<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
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
													<s:if test="RenewalMode != null">
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />															
														<input type="button"  size="2"  value="..." onclick="functionview(2)" class="pullRight" />
													</s:if>
													<s:else>
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="%{contNo==null?false:''.equals(contNo)?false:true}"  />
														<input type="button"  size="2"  value="..." onclick="functionview(2)" class="pullRight" />														
													</s:else>
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.retroTreatyNameType" />
												</div>
												<div class="tbox">
													<s:textfield name="treatyName_type" cssClass="inputBox" maxlength="50" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
												<s:if test=' "Renewal".equals(proposalReference) || "Layer".equals(proposalReference)'>
													<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;"  maxlength="15" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);"/>
												</s:if>
												<s:else>
														<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;" readonly="%{(proposal_no!='' && proposal_no!=null)?true:false}" maxlength="15" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);"/>
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
													<s:if test="RenewalMode != null">
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this);" onchange="GetExchangeRate()"/>	
														</s:if>
														<s:else>
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this);" onchange="GetExchangeRate()"/>
														</s:else>
													</s:if>
													<s:else>
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<s:if test="prclFlag == true">
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()" disabled="true"/>
															</s:if>
															<s:else>
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()" />
															</s:else>
														</s:if>
														<s:else>
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this);" onchange="GetExchangeRate()" />
														</s:else>
													</s:else>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.expiryDate" />
												</div>
												<div class="tbox">
													<s:if test="layerProposalNo == null || layerProposalNo == ''">
														<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  />													
													</s:if>
													<s:else>
													 <s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" />		<%--disabled="true"  --%>											
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYear" />
												</div>
												<div class="tbox" id="yearId">
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  />
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.acceptanceDate" />
												</div>
												<div class="tbox">
													<s:textfield name="accDate" id="accDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  onchange="GetExchangeRate()"/>												
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.originalCurrency" />
												</div>
												<div class="tbox">
													<s:select list="orginalCurrencylist" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" name="orginalCurrency" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetExchangeRate()" />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.exchangeRate" />
												</div>
												<div class="tbox" id="exRate">
													<s:textfield name="exchRate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
										<%--	<div class="textfield">
												<div class="text">
													<s:text name="label.coverLimitPoc" />
												</div>
												<div class="tbox">
													<s:textfield name="limitOrigCur" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="26" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.deductiblePoc" />
												</div>
												<div class="tbox">
													<s:textfield name="deduc_hunPercent" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="26" />													
												</div>
												<s:hidden name="xlPremium" value="0"/>
											</div> --%>
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
													<div class="tbox" id="rdsBasis">
														<s:select list="Basislist" listValue="DETAIL_NAME" listKey="TYPE" name="basis" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />													
													</div>
												</div>
												<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<div class="textfield">
													<div class="text">
														<s:text name="label.perilCovered" />
													</div>
													<div class="tbox">
														<s:select list="perilCoveredlist" id="perilCovered" listValue="DETAIL_NAME" multiple="true" size="1" listKey="TYPE" headerKey="0" headerValue="None" name="perilCovered" cssClass="inputBoxS"  />													
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
														<s:radio list="#{'Y':'Yes','N':'No'}" name="LOCIssued" value="%{(LOCIssued==null || LOCIssued=='')?'N':LOCIssued}" onchange="getLocDeta(this.value)"/>
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
													<s:textfield name="locCreditPrd" id="locCreditPrd" cssClass="inputBox"   maxlength="26" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);negative(this.id,this.value);"/>
													</div>
												
											</div>
											<div class="textfield" id="crdAId" style="display:none;">
												<div class="text">
														<s:text name="label.creditAmt" />
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditAmt" id="locCreditAmt" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
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
												<div class="textfield" id="umbrellaID" style="display:none;">
													<div class="text">
														<s:text name="label.umbrellaXL" />
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="umbrellaXL" value="%{umbrellaXL==null?'N':umbrellaXL}" onchange="getUmbrellaXL(this.value);"/>													
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
										<div >
										<div id="newgenid">
										<table class="table table-bordered" width="100%" id="newgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitPoc" />  </th>
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
																	<td>
																	<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   />
																	
																	</td>
																	<td>
																	<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" />
																	</td>
																	<td>
																	<s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30" />
																	</td>
																	<td >
																	<s:textfield name="gnpiAsPO[%{#stat.count-1}]" id="gnpiAsPO%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen');"  maxlength="30"  />
																	</td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
																	</s:if>
																	</td>
																	<s:hidden name="loopcount" id="loopcount" ></s:hidden>
																</tr>
																</s:iterator>
															</tbody>
															</table>
															<br class="clear"/>
															<div class="boxcontent" align="center">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
															</div>
															</div>
															<br class="clear"/>
															<%-- <div class="textfield" id="eventID" style="display:none;">
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
																	<s:textfield name="coverLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>													
																</div>
															</div>
															<div class="textfield" id="deductLimitID" style="display: none;">
																<div class="text">
																	<s:text name="label.deductLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="deductLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>													
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
										<table class="table table-bordered" width="100%" id="newgen1">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitAm" />  </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitper" />  </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleAm" /> </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePer" /> </th>
																<th width="15%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
																<th width="10.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
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
																	<td>
																	<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="0==(#stat.count-1) && ('17'!=departId||'18'!=departId ||'19'!=departId)?true:false"/>
																	<%--<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" />--%>
																	<s:if test='0==(#stat.count-1)'>
																	<s:text name="(Main Class)" />
																	</s:if>
																	</td>
																	<td>
																	<s:textfield name="coverLimitAmount[%{#stat.count-1}]" id="coverLimitAmount[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
																	</td>
																	<td>
																	<s:textfield name="coverLimitPercent[%{#stat.count-1}]" id="coverLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" />
																	</td>
																	<td>
																	<s:textfield name="deductableLimitAmount[%{#stat.count-1}]" id="deductableLimitAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
																	</td>
																	<td>
																	<s:textfield name="deductableLimitPercent[%{#stat.count-1}]" id="deductableLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" />
																	</td>
																	<td>
																	<s:textfield name="egnpiAsPerOffSlide[%{#stat.count-1}]" id="egnpiAsPerOffSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCalSlide()" maxlength="30" />
																	</td>
																	<td >
																	<s:textfield name="gnpiAsPOSlide[%{#stat.count-1}]" id="gnpiAsPOSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen1');" maxlength="30"   />
																	</td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRowS('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
																	</s:if>
																	</td>
																	
																</tr>
																
																</s:iterator>
																<s:hidden name="count" id="count" ></s:hidden>
															</tbody>
															</table>
															<br class="clear"/>
														<div class="boxcontent" align="center">
															<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');" />
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
													<s:text name="label.egnpi" />
												</div>
												<div class="tbox">
													<s:textfield name="egnpiOffer" id="egnpiOffer" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateEpi();" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.pml" />
													</div>
													<div class="tbox">													
														<s:radio list="#{'Y':'Yes','N':'No'}" name="pml" value="%{(pml==null || pml=='')?'N':pml}"  onchange="GetPML(this.value);CalculateminimumrateEpi();CalculateEpi();"/>
													</div>
											</div>
											<div class="textfield" id="pmlp" style="display:none">
												<div class="text">
													<s:text name="label.pmlPercent" />
												</div>
												<div class="tbox">
													<s:textfield name="pmlPercent" id="pmlPercent" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.value,'pml');hundredCheck(this.id,this.value);" maxlength="10" onblur="CalculateEpi();CalculateEGNPIPML();CalculateminimumrateEpi();"/>													
												</div>
											</div>
											<%-- <div class="textfield" id="pmlepi" style="display:none">
												<div class="text">
													<s:text name="label.egnpipml" />
												</div>
												<div class="tbox">
													<s:textfield name="egnpipml" id="egnpipml" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  maxlength="26" readonly="true"/>													
												</div>
											</div>--%>
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
													<s:textfield name="adjRate" id="adjRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateEpi()" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate1" style="display:none">
												<div class="text">
													<s:text name="label.minimumRate" />
												</div>
												<div class="tbox">
													<s:textfield name="minimumRate" id="minimumRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateminimumrateEpi();CalculateEpi()"   onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate2" style="display:none">
												<div class="text">
													<s:text name="label.maximumRate" />
												</div>
												<div class="tbox">
													<s:textfield name="maximumRate" id="maximumRate" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield" id="rate3" style="display:none">
												<div class="text">
													<s:text name="label.burningCostLF" />
												</div>
												<div class="tbox" >
													<div class="input-group">
													  <span class="input-group-addon" id="basic-addon1">100/</span>
													  <s:textfield name="burningCostLF" id="burningCostLF" cssClass="form-control inputBox" cssStyle="text-align: right; width: 88%;" onkeyup="middleMinusRestrictionNeg(this);negative(this.id,this.value);checkDecimals(this);hundredCheck(this.id,this.value)" onblur="CalculateminimumrateEpi();CalculateEpi()"  maxlength="20"  />
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
													<s:textfield name="epi" id="epi" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"/>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.minPremium" />
												</div>
												<div class="tbox">
													<s:textfield name="minPremium" id="minPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);mdPremiumVal()"  />													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.MandDPremiumP" />
												</div>
												<div class="tbox">
													<s:textfield name="m_dPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);jajvascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>													
												</div>
											</div>
																							
											<div class="textfield">
												<div class="text">
													<s:text name="label.MandDInstallments" />
												</div>
												<div class="tbox">
													<s:textfield name="m_d_InstalmentNumber" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="3"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.paymentDuedays" />
												</div>
												<div class="tbox">
													<s:textfield name="paymentDuedays" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="26"/>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.proposalStatus" />
												</div>
												<div class="tbox">
													<s:select name="proStatus" list="proposallist" listValue="DETAIL_NAME" listKey="TYPE" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="GetShareSigned(this.value)" disabled="%{ (amend_Id_Mode!=null && !''.equals(amend_Id_Mode)) ? true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.noOfRetroCessionaries" />
												</div>
												<div class="tbox">
													<s:textfield name="noRetroCess" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="3" />													
												</div>
											</div>	 
											<br class="clear">
										</div>
									</div>
								</div>
							</div>
							<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								
								<s:if test='amend_Id_Mode == null ||"".equals(amend_Id_Mode) '>
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
											 <input type="button"  value="Cancel"  class="btn btn-sm btn-danger" onClick="FunctionEditCancel()" />
										</s:else>
										<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  />
							 			<input type="button"  value="Next"    class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');next()" />
									</s:if>
									<s:else>
																			
										<s:if test='"R".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="FunctionRejectCancel()" />
										</s:if>
										<s:elseif test='"N".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="FunctionNotTakenCancel()" />
										</s:elseif>
										<s:elseif test='"renewal".equals(flagTest) || "Renewal".equals(proposalReference) || "Layer".equals(proposalReference) '>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="return FunctionCancel();" />
											<s:hidden name="proposalNo1"/>
										</s:elseif>
										<s:else>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="FunctionEditCancel()" />
										</s:else>	
										<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
							 			<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');funEditSubmit()" />
									</s:else>
								</s:if>
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"  onClick="AmendIdBack()" />
									<%-- <input type="button"  value="Save"   class="btn btn-sm btn-success"  id="mybutton1" onclick="disableForm(this.form,false,'');FunctionSaveOption()"/>--%>
									<input type="button"  value="Next"   class="btn btn-sm btn-warning" id="mybutton2" onclick="disableForm(this.form,false,'');funAmendsubmit()" />
								</s:else>								
							</div>
						</div>												
					</div>
					<s:hidden name="flag" id="flag"/>
					<s:hidden name="menuId" id="menuId"/>
					<s:hidden name="shareWritt" value="100"/>
					<s:hidden name="sharSign" value="100"/>	
					<s:hidden name="proposalno" />
					<s:hidden name="CustomerId" value="cedingCo" />
					<s:hidden name="layerProposalNo" />
					<s:hidden name="brokerage" />
					<s:hidden name="tax" />
					<s:hidden name="contractno1" />
					<s:hidden name="renewal_contract_no" />
					<s:hidden name="renewalFlag" />
					<s:hidden name="lay1" />
					<s:hidden name="baseLayer" />
					<s:hidden name="baseLoginID" />
					<s:hidden name="amendId" />
					<s:hidden name="ContractNo" value="%{contNo}" />
					<!--<s:hidden name="contNo"/>
					--><s:hidden name="edit" />
					<s:hidden name="cedType" id="cedType" />
					<s:hidden name="endorsmentno"/>
					<s:hidden name="proposalNo" id="proposalNo" value="%{proposal_no}"/>
					<s:hidden name="mode" id="mode"/>
					<s:hidden name="limitOrigCur"/>
					<s:hidden name="deduc_hunPercent"/>
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
function getAjax(value,id)
{
	var URL='<%=request.getContextPath()%>/ajaxValueXol.action?departId='+value+'&dropDown=subclass';
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
}
--%>
function next()
{
if(dffDateAlert()){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
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
		else
		{
		    alert('Please Select Territory');
		}
var business=document.retroxol1.businessType.value;
if(business!='5'){
	document.retroxol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.retroxol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.retroxol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.retroxol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.retroxol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	document.retroxol1.action="checkXol.action";
	//document.retroxol1.action="checkXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
	document.retroxol1.submit();
	}
}

function funEditSubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
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
		else
		{
		    alert('Please Select Territory');
		}
	if(dffDateAlert()){
	if(chkAccAmt()){
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.retroxol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	//document.retroxol1.action="FirstPageUpdateModeXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
	document.retroxol1.action="FirstPageUpdateModeXol.action";
	document.retroxol1.submit();
	}
	}
}

function FunctionSaveOption()
{
if(dffDateAlert()){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
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
		else
		{
		    alert('Please Select Territory');
		}
var business=document.retroxol1.businessType.value;
if(business!='5'){
	document.retroxol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.retroxol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.retroxol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.retroxol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.retroxol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
	//document.retroxol1.action="FirstPageSaveMethodXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
	document.retroxol1.action="FirstPageSaveMethodXol.action";
	document.retroxol1.submit();
	}
}

function FunctionView()
{
	document.retroxol1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=ViewMethod"
	document.retroxol1.submit();
}

function funAmendsubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
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
		else
		{
		    alert('Please Select Territory');
		}
	if(dffDateAlert()){
	if(chkAccAmt()){
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.retroxol1,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,event_limit,coverLimitXL,deductLimitXL,egnpiOffer,subPremium,egnpipml,epi,minPremium,m_dPremium');
		//document.retroxol1.action="FirstPageUpdateModeXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
		document.retroxol1.action="FirstPageUpdateModeXol.action";
		document.retroxol1.submit();
	}
	}
}

function FunctionEditCancel()
{
	document.retroxol1.action ='${pageContext.request.contextPath}/InitPortfolio.action';
	document.retroxol1.submit();
	
}

function FunctionRejectCancel()
{
	//document.location='<%=request.getContextPath()%>/menu.do?method=menu&manufactureID='+<%=session.getAttribute("mfrid")%>+'&flag=R';
	document.retroxol1.action='${pageContext.request.contextPath}/menuPortfolio.do?manufactureID=<s:property value="#session.mfrid"/>'
	document.retroxol1.submit();
}
function FunctionNotTakenCancel(){
	
	document.retroxol1.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.retroxol1.submit();
}
function AmendIdBack()
{	document.getElementById("mybutton").style.display = "none";
document.getElementById("proposalNo").disabled = false;
	document.retroxol1.action ="${pageContext.request.contextPath}/InitPortfolio.action?endtMode=endorsment&endorsementStatus=N";
	document.retroxol1.submit();
}
getDepatmentCover();
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
		document.getElementById('pmlp').style.display='inline';
 		//document.getElementById('pmlepi').style.display='inline';
	}
	if(id=='N')
	{
		document.getElementById('pmlp').style.display='none';
	 	//document.getElementById('pmlepi').style.display='none';
	 	//document.getElementById('egnpipml').value='0';
	 	document.getElementById('pmlPercent').value='0';
	}
}
getPremiumBasis();
function getPremiumBasis(){
	var type= document.retroxol1.businessType.value;
	var val= document.retroxol1.premiumbasis.value;
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
else if(val=='2'){
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
if(val=='1' || val=='2' ||val=='3'){
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
 	rowCount = table.rows.length;
 	document.getElementById('count').value = parseInt(rowCount)-2;
 	getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
	var table = document.getElementById("newgen");
 	rowCount = table.rows.length;
 	document.getElementById('loopcount').value = parseInt(rowCount)-1;
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
 	getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
 	getEgnpiCal();
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
}else{
document.getElementById('coverLimitID').style.display='none';
document.getElementById('deductLimitID').style.display='none';
}
}

function funsubmit()
{
	if(dffDateAlert()){
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
		 	document.retroxol1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=check";
		 	document.retroxol1.submit();
		 }
	</s:if>
	<s:else>		
			document.retroxol1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=check";
			document.retroxol1.submit();
	</s:else>
	}
	}
}

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
			//cell6.id='gnpi['+(rowCount)+']';
			//cell6.style.display ='none';
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "gnpiAsPO["+(rowCount-1)+"]";
      		element5.id = "gnpiAsPO"+(rowCount-1);
			//element5.value=document.getElementById("gnpiAsPO"+(parseFloat(rowCount)-2)).value;
			element5.className = "inputBox";
			element5.setAttribute("disabled",true);
			element5.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element5.setAttribute("maxlength",'30'); 
			element5.setAttribute("style", "text-align:right;");
			cell6.appendChild(element5); 
			
			
			var cell7 = row.insertCell(6);
			cell7.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "deleteRow('"+(rowCount-1)+"')");
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
			element7.className = "inputBox";
			element7.setAttribute("disabled",true);
			element7.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			cell8.appendChild(element7);
			
			
			var cell9 = row.insertCell(8);
			cell9.setAttribute("align","center"); 
			var element8 = document.createElement("input");
			element8.type = "button";
			element8.value="Delete";
			element8.setAttribute("onclick", "deleteRowS('"+(rowCount-2)+"')");
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
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
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
				objOption.text = "<s:property value='TMAS_DEPARTMENT_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_DEPARTMENT_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function deleteRow(val){
 document.getElementById("mode").value = "delete";
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartId[0]").disabled = false; 
		postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?deleteId='+val+'&dropDown=newgen', "newgenid", "retroxol1"); 
			}
		}
}
function deleteRowS(val){
document.getElementById("mode").value = "delete";
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartIdS[0]").disabled = false;
		postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?deleteId='+val+'&dropDown=newgen1', "newgenid1", "retroxol1"); 
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

function calculation()
{
	var limit=document.retroxol1.limitOrigCur.value;
	limit=limit.replace(new RegExp(',', 'g'),'');

	var Epi100=document.retroxol1.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');

	var ourEsitamate=document.retroxol1.ourEstimate.value;
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" )
	{
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.retroxol1.epi.value=Comma(finalvalue.toFixed(2));
	}else
	document.retroxol1.epi.value='0';
}

function GetShareSigned(value)
{
		if(value=='A')
		{
		document.retroxol1.sharSign.disabled=false;
		}
		else  if(value=='P')
		{
		document.retroxol1.sharSign.value='';
		document.retroxol1.sharSign.disabled=true;
		}
		else if(value=='R')
		{
		document.retroxol1.sharSign.value='';
		document.retroxol1.sharSign.disabled=true;
		}
}

function GetExchangeRate() {
		var incDate=document.forms['retroxol1'].incepDate.value;
		var accDate=document.retroxol1.accDate.value;
		var Currecny=document.retroxol1.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !=""))
		{
	 		var URL='${pageContext.request.contextPath}/getExcRateXol.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else
		{
			document.retroxol1.exchRate.value='';
		}	
}

function CalculateEpi() {	
	var a=document.retroxol1.egnpiOffer.value;
	var premiumbasis = document.retroxol1.premiumbasis.value;
	a=a.replace(new RegExp(',', 'g'),'');
	if('1'==premiumbasis){
	var b=document.retroxol1.adjRate.value;
	}
	else if('2'==premiumbasis){
	var b=document.retroxol1.minimumRate.value;
	}
	else if('3'==premiumbasis){
	document.getElementById("epi").readOnly=false;
	}
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.retroxol1.epi.value=Comma(c.toFixed(2));
	}else {
		document.retroxol1.epi.value='';
	}
}
<%-- 
function CalculateEGNPI() {	
	var a=document.retroxol1.egnpiOffer.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.retroxol1.ourAssessment.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.retroxol1.subPremium.value=Comma(c.toFixed(2));
	}else {
		document.retroxol1.subPremium.value='';
	}
}
--%>
 
function CalculateEGNPIPML() {	
	<%--var a=document.retroxol1.egnpiOffer.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.retroxol1.pmlPercent.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var pmlPer=parseFloat(b)/100;
		var c=parseFloat(a)*pmlPer;
		document.retroxol1.egnpipml.value=Comma(c.toFixed(2));
	}else {
		document.retroxol1.egnpipml.value='';
	}--%>
}

function mdPremiumVal(){
document.retroxol1.m_dPremium.value=document.retroxol1.minPremium.value;
}

function CalculateminimumrateEpi() {	
	var type=document.retroxol1.pml.value;
	var egn = 0;
	//if('Y'==type){
	//  egn=document.retroxol1.egnpipml.value;
	//}
	//else if('N'==type){
	  egn = document.retroxol1.egnpiOffer.value;
	//}
	egn=egn.replace(new RegExp(',', 'g'),'');
	var b=document.retroxol1.minimumRate.value;
	var bas =document.retroxol1.premiumbasis.value;
	if(bas=="2"){
	if(egn!=null && egn!="" && b!=null && b!="") {
		var minRt=parseFloat(b)/100;
		var c=parseFloat(egn)*minRt;
		document.retroxol1.minPremium.value=Comma(c.toFixed(2));
		mdPremiumVal();
	}
	else {
		document.retroxol1.minPremium.value='';
		document.retroxol1.m_dPremium.value='';
	}
	}
	else {
		document.retroxol1.minPremium.value='';
		document.retroxol1.m_dPremium.value='';
	}
}
function CalculateminimumPremium() {	

//if('Y'==pml){
	//var a=document.retroxol1.egnpipml.value;
	//}
	//else{
	var a=document.retroxol1.egnpiOffer.value;
	//}
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.retroxol1.minimumRate.value;
	
	if(a!=null && a!="" && b!=null && b!="") {
		var minRt=parseFloat(b)/100;
		var c=parseFloat(a)*minRt;
		document.retroxol1.epi.value=Comma(c.toFixed(0));
	}else {
		document.retroxol1.epi.value='';
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
	strOpen.focus();
	return false;		 
}
		
function Commas(value) {
	if(value=="5") {
		//document.retroxol1.maxLimit_Product.value=Comma(document.retroxol1.maxLimit_Product.value);
		//document.retroxol1.limitOrigCur.value=Comma(document.retroxol1.limitOrigCur.value);
		document.retroxol1.egnpiOffer.value=Comma(document.retroxol1.egnpiOffer.value);
		//document.retroxol1.subPremium.value=Comma(document.retroxol1.subPremium.value);
		document.retroxol1.epi.value=Comma(document.retroxol1.epi.value);
		document.retroxol1.m_dPremium.value=Comma(document.retroxol1.m_dPremium.value);
		//if(document.retroxol1.limitPerVesselOC.value!=null && document.retroxol1.limitPerVesselOC.value!="") {
		//	document.retroxol1.limitPerVesselOC.value=Comma(document.retroxol1.limitPerVesselOC.value);
		//}
		//if(document.retroxol1.limitPerLocationOC.value!=null && document.retroxol1.limitPerLocationOC.value!="") {
			//document.retroxol1.limitPerLocationOC.value=Comma(document.retroxol1.limitPerLocationOC.value);
		//}
		//document.retroxol1.event_limit.value=Comma(document.retroxol1.event_limit.value);
		document.retroxol1.coverLimitXL.value=Comma(document.retroxol1.coverLimitXL.value);
		document.retroxol1.deductLimitXL.value=Comma(document.retroxol1.deductLimitXL.value);
		//document.retroxol1.egnpipml.value=Comma(document.retroxol1.egnpipml.value);
		document.retroxol1.minPremium.value=Comma(document.retroxol1.minPremium.value);
		//document.retroxol1.deduc_hunPercent.value=Comma(document.retroxol1.deduc_hunPercent.value);
		//document.retroxol1.limitOrigCur.value=Comma(document.retroxol1.limitOrigCur.value);
		//document.retroxol1.subPremium.value=Comma(document.retroxol1.subPremium.value);
		document.retroxol1.epi.value=Comma(document.retroxol1.epi.value);
		document.retroxol1.m_dPremium.value=Comma(document.retroxol1.m_dPremium.value);
	}
}

/*********************************/

function GetSp(id)
{	
	if(id=='Y')
	{
		document.retroxol1.no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N')
	{
		document.getElementById("NoOFInsur").readOnly=true;
		document.retroxol1.no_Insurer.value="0";
	}
}

function functionDate()
{
var	inceptionDate=document.retroxol1.incepDate.value;
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
		document.retroxol1.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.retroxol1.uwYear.value=date.getFullYear();
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
		document.retroxol1.expDate.value=d+"/"+m+"/"+y;
	
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
	var inception=document.retroxol1.incepDate.value;
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
	var expery=(document.retroxol1.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
	if(diffDays>0){
	answer = confirm('Expiry Date is More than One Year.Do You Wish to Continue?');
	}else if(diffDays<-86400000){
	answer = confirm('Expiry Date is Less than One Year.Do You Wish to Continue?');
	}else{
	answer = true;
	}
	return  answer;
}

function chkAccAmt(){
	/*Commented by Ganapathy(maxlimit_product is set in 
			old one when product is not equal to 4 and)
			
	var limitOC=document.retroxol1.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.retroxol1.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.retroxol1.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.retroxol1.proStatus.value=="A")
	{	 
		cedPer=document.retroxol1.sharSign.value;
	}else
	{
		cedPer=document.retroxol1.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.retroxol1.sharSign.value;
	</s:if>	
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit)
		{
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt)
			{
				<s:if test='contNo == "" && contNo == null'>				
					document.retroxol1.proStatus.value='P';
					document.retroxol1.sharSign.value='';
				</s:if>
			}else
			{
				 return false; 
			}
		}
	}*/
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
		document.retroxol1.xlCost.value="0";
		document.retroxol1.xlCost.readOnly=true;
		document.retroxol1.insuredName.readOnly=false; 
	}else
	{
		//document.retroxol1.xlCost.value="";
		document.retroxol1.xlCost.readOnly=false;
		document.retroxol1.insuredName.value="";
		document.retroxol1.insuredName.readOnly=true; 
	}
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
function toggleVesselBlock(value){
	 if(value=="4")
	 {
		document.getElementById('VesselBlock').style.display='block';
	 }   
	 else
	 {   
	 	document.getElementById('VesselBlock').style.display='none';
	 }
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
			document.retroxol1.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.retroxol1.submit();
			}
			else {
			document.getElementById("proposal_no").disabled=true;
				 return false; 
			}
		}
		else{
			document.retroxol1.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.retroxol1.submit();
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
           
});--%>

getFieldDisable('<s:property value="endorsmenttype"/>');

function getFieldDisable(val){
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
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-1;
			for(var c= 0;c<rowCount;c++){
			document.getElementById("gnpiAsPOSlide"+c).disabled = false;
			}
			insRowEmptySlide('newgen1');
		}
		else{
			table = document.getElementById('newgen');
			var rowCount = table.rows.length-1;
			for(var c= 0;c<rowCount;c++){
			document.getElementById("gnpiAsPO"+c).disabled = false;
			}
			insRowEmpty('newgen');
		}
	}	
	else{
	if(type=='5'){
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-1;
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
if('newgen1' == tableID){
	for(var c= 0;c<rowCount-1;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	}
}
else{
	 for(var c= 0;c<rowCount-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
	 	val +=parseFloat(sum);		
	 	}
}
val =Comma(val.toFixed(2));
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
if('newgen1' == tableID){
	for(var c= 0;c<parseInt(rowCount)-1;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	}
}
else{
	 for(var c= 0;c<parseInt(rowCount)-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
	 	val +=parseFloat(sum);		
	 	}
}
val =Comma(val.toFixed(2));
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
function getAjaxCoverClass(){
var drop = "";
var id="";
var value =document.retroxol1.departId.value;
var business=document.retroxol1.businessType.value;
document.getElementById('departId').disabled = false;
var proposal_no = document.retroxol1.proposal_no.value;
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
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?dropDown=newgen', "newgenid", "retroxol1"),
        //data:'src_type_id='+val,
        success: function(data){
        getEgnpiCal();
        getDepatmentCover();
        if(proposal_no!=''){
        document.getElementById('departId').disabled = true;
        }
        }
			});
    	}else if("newgen1"==drop){
    	$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?dropDown=newgen1', "newgenid1", "retroxol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //alert("1");
        getEgnpiCalSlide();
        getDepatmentCover();
         if(proposal_no!=''){
        document.getElementById('departId').disabled = true;
        }
        }
			});
    	} 
    }
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
