<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
       	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">	
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" ></link>	
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css"></link>	
		<link rel="stylesheet" type="text/css" href="css/select2.css"/>
		<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>    
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="js/select2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
	
	<style type="text/css">
		.btn-group {
		width: 100%;
		}
		.btn-group .btn {
		width: 90%;
		padding: 3px 12px;
		}
		.subpannel .panel-body .panel-heading {
    color: #fff;
    background-color: #a8182b;
    border-color: #a8182b;
}
.reinsstyle{
display: flex;
gap:20px;
}
	</style>
	
</head>
	<body>
		<s:set var="elayerInfo" value='%{sectionInfo}'/>
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
			<s:set name="profitCommissionListVar" value="profitCommissionList"/>
					<s:set name="typeYearListVar" value="typeYearList"/>
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
									
									
									
									
									<div class="boxcontent">
											<div class="panel panel-primary subpannel">											
												<div class="panel-heading">
													<s:text name="label.sectiondetail" />
												</div>
												<div class="panel-body">
														<div class="boxcontent">
															<s:if test="#elayerInfo!=null && #elayerInfo.size()>0">
															<div class="row">
															<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																<s:iterator value="#elayerInfo" var="list"  status="stat">
																	<button type="button"  class="btn btn-sm btn-info"  onclick="funViewMode('<s:property value='#list.PROPOSAL_NO'/>','<s:property value='#list.CEDING_COMPANY_ID'/>','<s:property value='#list.PRODUCT_ID'/>','<s:property value='#list.BASE_LAYER'/>','<s:property value='#list.CONTRACT_NO'/>','<s:property value='#list.DEPT_ID'/>');" tabindex="1">Section <s:property value="#list.SECTION_NO"/> </button>
																</s:iterator>
															</div>
															</div>
															</s:if>
														<div class="textfield">
															<div class="text">
																<s:text name="label.sectionNo" />
															</div>
															<div class="tbox">
															<s:if test='"Renewal".equals(proposalReference) || "Layer".equals(proposalReference)'>
															<%--<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;" readonly="%{(proposal_no!='' && proposal_no!=null)?true:false}" disabled='%{("Y".equals(disableStatus1))?true:false}' onkeyup="checkNumbers(this);"/>--%>
																<s:textfield name="sectionNo" id="sectionNo" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" readonly="true"/>
															</s:if>
															<s:else>
																<s:textfield name="sectionNo" id="sectionNo" cssClass="inputBox" cssStyle="text-align: right;"    onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" readonly="true"/>
															</s:else>
															
															</div>
														</div>
											
														<br class="clear"></br>
														</div>
														<div class="boxcontent">
															<div class="panel panel-primary">											
																<div class="panel-heading ">
																	<s:text name="label.riskdetails" />
																</div>
																<div class="panel-body">
																	<div class="textfield">
																		<div class="text">
																			<s:text name="label.insdetails" />
																		</div>
																		<div class="tbox">
																			<s:radio list="#{'Y':'Yes','N':'No' }" name="riskdetailYN" id="riskdetailYN" value="%{riskdetailYN==null?'N':riskdetailYN}" onchange="getRiskInfo(this.value)" disabled="true"></s:radio>													
																		</div>
																	</div>
																	<s:if test='"Y".equals(riskdetailYN)'>
																	<div class="boxcontent" id="riskid">
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.originalCurrency" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																				<s:select list="orginalCurrencylist" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" name="orginalCurrency" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" onchange="GetExchangeRate()" disabled="true" />
																			</div>
																			<span  id="exRate">
																				<s:hidden name="exchRate" id="exchRate"/>
																			</span>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.treatyNameType" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="treatyName_type" cssClass="inputBox" maxlength="500" disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.treatyType" />&nbsp;<sup style="color: red;"></sup>
																			</div>
																			<div class="tbox">
																				<s:if test="RenewalMode != null">
																					<s:select list="treatyTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="treatyType" cssClass="select1 inputBoxS" headerKey="0" headerValue="---Select---" onchange="getTreatyType(this.value);" disabled="true" />
																				</s:if>
																				<s:else>
																					<s:select list="treatyTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="treatyType" cssClass="select1 inputBoxS" headerKey="0" headerValue="---Select---" onchange="getTreatyType(this.value);"  disabled="true"/> <%-- disabled='%{ (proposal_no != "" && proposal_no != null)?true:false}' --%>
																				</s:else>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.departmentClass" />
																			</div>
																			<div class="tbox">
																				<%--<s:if test="RenewalMode != null || (proposal_no!=null && proposal_no!='') ">--%>
																				<s:if test="RenewalMode != null ">
																					<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjax(this.value,'subclass')" />
																				</s:if>
																				<s:elseif test="(baseLayer!=null && baseLayer!='')">
																					<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjax(this.value,'subclass');" />
																				</s:elseif>
																				<s:else>
																					<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getAjax(this.value,'subclass');" /><%-- disabled='%{(proposal_no!=null && proposal_no!="") ?true:false}' --%>
																				</s:else>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.subClass" />
																			</div>
																			<div class="tbox" id="subclass">
																				<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" multiple="true" name="subProfit_center" id="subProfit_center"  cssClass="inputBoxS" disabled="true" headerKey="ALL" headerValue="ALL" />
																			</div>
																		</div>
																		<div class="textfield" id="factreatyQS1"
																			style="display: none">
																			<div class="text">
																				<s:text name="label.factreatyLimitOC" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="faclimitOrigCur" id="faclimitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="30" disabled="true" />
																			</div>
																		</div>
																		<div class="textfield" id="treatyQS1"
																			style="display: none">
																			<div class="text">
																				<s:text name="label.treatyLimitOC" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="limitOrigCur" id="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" onchange="calculateTreatySur()" maxlength="30" disabled="true" />
																			</div>
																		</div>
																		<div class="textfield" id="treatyQSper"
																			style="display: none">
																			<div class="text">
																				<s:text name="label.quotesharePercent" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="quotesharePercent" id="quotesharePercent" cssClass="inputBox" cssStyle="text-align: right;" maxlength="20" onkeyup="middleMinusRestrictionNeg(this);negative(this.id,this.value);checkDecimals(this);allowOneDot(this);hundredCheck(this.id,this.value)"  disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield" id="treatySurp1"
																			style="display: none">
																			<div class="text">
																				<s:text name="label.noofline" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="treatynoofLine" id="treatynoofLine" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);javascript:this.value=Comma(this.value)"  onchange="calculateTreatySur()" maxlength="30" disabled="true" />
																			</div>
																		</div>
																		<div class="textfield" id="treatySurp2"
																			style="display: none">
																			<div class="text">
																				<s:text name="label.treatyLimitsurplusOC" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="treatyLimitsurplusOC" id="treatyLimitsurplusOC" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" onchange="CalculateTreatyQSPML()" maxlength="30" disabled="true" />
																			</div>
																		</div>
																		<s:hidden  name="cedRetenType" ></s:hidden>
																		<s:hidden  name="cedReten" id="cedReten" ></s:hidden>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.cleancutrunoff" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																				<s:select list="proposaltypelist" listKey="TYPE" listValue="DETAIL_NAME" name="proposalType" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="getRateField(this.value)" disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield" id="ratId" style="display: none;">
																			<div class="text">
																				<s:text name="label.rate.year" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																				<s:textfield name="runoffYear" id="runoffYear" cssClass="inputBox" cssStyle="text-align:right;" maxlength="26" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" disabled="true"/>
																			</div>
																		</div>
																	</div>
																	</s:if>
																</div>
															</div>
														</div>
														<div class="boxcontent">
															<div class="panel panel-primary">											
																<div class="panel-heading">
																	<s:text name="label.brokerinfo" />
																</div>
																<div class="panel-body">
																	<div class="boxcontent">
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.insdetails" />
																			</div>
																			<div class="tbox">
																				<s:radio list="#{'Y':'Yes','N':'No' }" name="brokerdetYN" id="brokerdetYN" value="%{brokerdetYN==null?'N':brokerdetYN}" onchange="getBrokerInfo(this.value)" disabled="true"></s:radio>													
																			</div>
																		</div>
																		<s:if test='"Y".equals(brokerdetYN)'>
																		<div class="boxcontent" id="brokerid">
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.broker" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																					<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled="true" onchange="getPaypartner(this.value,'paypartid');"/>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.paymentpartner" />
																			</div>
																			<div class="tbox" id="paypartid">
																				<s:select list="paymentPartnerlist" listKey="CUSTOMER_ID" listValue="NAME" name="paymentPartner" id="paymentPartner" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.leadUnderWriter" />  &nbsp; <sup style="color:red;"></sup>
																			</div>
																			<div class="tbox">
																					<s:select list="underwriterList" listKey="CUSTOMER_ID" cssClass="select1 inputBoxS"  listValue="NAME" name="leader_Underwriter" id="leader_Underwriter"   headerKey="" headerValue="---Select---"  disabled="true"/> <!-- onchange="getUnderwriterShare(this.value);" -->
																			</div>
																		</div>
																		<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																		 <div class="textfield" >
																			<div class="text">
																				<s:text name="label.leadUnderwritterCountry" />  &nbsp; <sup style="color:red;"></sup>
																			</div>
																			<div class="tbox" id="country">
																				
																				<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  disabled="true"/>													
																			</div>
																		</div>
																		</s:if>
																		<s:else>
																		<div class="tbox" id="country">
																			<s:hidden  name="leader_Underwriter_country" id="leader_Underwriter_country" disabled="true"/>
																		</div>	
																		</s:else>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.leadUnderwritterShareP" />&nbsp; <sup style="color:red;"></sup>
																			</div>
																			<div class="tbox">
																				<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="true"/>
																			</div>
																		</div>
																		</div>
																		</s:if>
																	</div>
																</div>
															</div>
														</div>	
														<div class="boxcontent">
															<div class="panel panel-primary">											
																<div class="panel-heading">
																	<s:text name="label.coverinfo" />
																</div>
																<div class="panel-body">
																	<div class="boxcontent">
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.insdetails" />
																			</div>
																			<div class="tbox">
																				<s:radio list="#{'Y':'Yes','N':'No' }" name="coverdetYN" id="coverdetYN" value="%{coverdetYN==null?'N':coverdetYN}" onchange="getCoverInfo(this.value)" disabled="true"></s:radio>													
																			</div>
																		</div>
																		<s:if test='"Y".equals(coverdetYN)'>
																		<div class="boxcontent" id="coverid">
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.territoryScope" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																				<s:textfield name="territoryscope" cssClass="inputBox" maxlength="500" disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.portfolioCovered" />
																			</div>
																			<div class="tbox">
																				<s:textfield name="riskCovered" cssClass="inputBox" maxlength="50" disabled="true"/>
																			</div>
																		</div>
																		<div class="textfield">
																			<div class="text">
																				<s:text name="label.pnocDays" /> &nbsp; <sup style="color: red;">  </sup>
																			</div>
																			<div class="tbox">
																				<s:select list="PNOCDayslist" listValue="DETAIL_NAME" listKey="TYPE" name="pnoc" cssClass="select1 inputBoxS" headerKey="-1" headerValue="---Select---" disabled="true"/>
																			</div>
																		</div>
																		</div>
																		</s:if>
																	</div>
																</div>
															</div>
														</div>
														<div class="boxcontent">
															<div class="panel panel-primary">											
																<div class="panel-heading">
																	<s:text name="label.premiuminfo" />
																</div>
																<div class="panel-body">
																	<div class="boxcontent">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.insdetails" />
																				</div>
																				<div class="tbox">
																					<s:radio list="#{'Y':'Yes','N':'No' }" name="premiumdetailYN" id="premiumdetailYN" value="%{premiumdetailYN==null?'N':premiumdetailYN}" onchange="getPremiumInfo(this.value)" disabled="true"></s:radio>													
																				</div>
																			</div>
																			<s:if test='"Y".equals(premiumdetailYN)'>
																			<div class="boxcontent" id="premiumid">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.ourAssessedEPIOC100" />
																				</div>
																				<div class="tbox">
																					<s:textfield name="epi" id="epi" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);calculation('epi');calculateSurplus();" disabled="true"/>
																				</div>
																			</div>
																			<div class="textfield" id="premiumQuoteid">
																				<div class="text">
																					<s:text name="label.premiumQuotaShare" />
																				</div>
																				<div class="tbox">
																					<s:textfield cssClass="inputBox" name="premiumQuotaShare" id="premiumQuotaShare" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);calculateSurplus();" maxlength="30" disabled="true"/>
																				</div>
																			</div>
																			<div class="textfield" id="premiumSurplusid">
																				<div class="text">
																					<s:text name="label.premiumSurplus" />
																				</div>
																				<div class="tbox">
																					<s:textfield cssClass="inputBox" name="premiumSurplus" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="30"  readonly="true"/>
																				</div>
																			</div>
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.accountingPeriod" /> &nbsp; <sup style="color: red;">  </sup>
																				</div>
																				<div class="tbox">
																					<s:select list="AccontPeriodlist" listValue="DETAIL_NAME" listKey="TYPE" name="accountingPeriod" cssClass="select1 inputBoxS" headerKey="0" headerValue="---Select---" disabled="true" />
																				</div>
																			</div>
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.accountingPeriodNotes" />&nbsp; <sup style="color: red;">  </sup>
																				</div>
																				<div class="tbox">
																					<s:textfield name="accountingPeriodNotes" cssClass="inputBox"  maxlength="200"  disabled="true"/>
																				</div>
																			</div>
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.statementDueDays" />&nbsp; <sup style="color: red;">  </sup>
																				</div>
																				<div class="tbox">
																					<s:textfield name="receiptofStatements" cssClass="inputBox" cssStyle="text-align:right;" maxlength="3" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" disabled="true"/>
																				</div>
																			</div>
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.statementConfirm" />&nbsp; <sup style="color: red;">  </sup>
																				</div>
																				<div class="tbox">
																					<s:textfield name="statementConfirm" cssClass="inputBox"  maxlength="200"  disabled="true"/>
																				</div>
																			</div>
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.paymentDueDays" />&nbsp; <sup style="color: red;">  </sup>
																				</div>
																				<div class="tbox">
																					<s:textfield name="receiptofPayment" cssClass="inputBox" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);" cssStyle="text-align:right;" maxlength="3" disabled="true"/>
																				</div>
					
																			</div>
																	</div>
																	</s:if>
																</div>
															</div>	
														<s:hidden name="spRetro" value="%{(spRetro==null || spRetro=='')?'Y':spRetro}"/>
														<s:hidden name="no_Insurer" id="NoOFInsur"/>
														<s:hidden name="accDate" id="accDate"/>
														<s:hidden name="polBr" id="polBr"/>
														<s:hidden name="profit_Center" id="profit_Center" value="D"/>
														<s:hidden name="inwardType" id="inwardType"/>
														<s:hidden name="underwriter" id="underwriter"/>
														<s:hidden name="maxLimit_Product" id="maxLimit_Product"/>
														<s:hidden name="locCreditAmt" id="locCreditAmt"/>
														<s:hidden name="locCreditPrd" id="locCreditPrd"/>
														<s:hidden name="LOCIssued" id="LOCIssued" value="%{(LOCIssued==null || LOCIssued=='')?'N':LOCIssued}"/>
														<s:hidden name="locBankName" id="locBankName"/>
														<s:hidden name="locBeneficerName" id="locBeneficerName"/>
														<s:hidden name="perilCovered" id="perilCovered"/>
														<s:hidden name="ourEstimate" id="ourEstimate"/>
														<s:hidden name="proStatus" id="proStatus" value="A"/>
														<s:hidden name="shareWritt" id="shareWritt"/>
														<s:hidden name="sharSign" id="sharSign"/>
														<s:hidden name="retentionYN" ></s:hidden>
														<s:hidden name="epi_origCur" ></s:hidden>
														<s:hidden name="limitPerVesselOC" ></s:hidden>
														<s:hidden name="limitPerLocationOC" ></s:hidden>
															
														</div>
														<div class="boxcontent">
															<div class="panel panel-primary">											
																	<div class="panel-heading">
																		<s:text name="label.acqinfo" />
																	</div>
																	<div class="panel-body">
																		<div class="boxcontent">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.insdetails" />
																				</div>
																				<div class="tbox">
																					<s:radio list="#{'Y':'Yes','N':'No' }" name="acqdetailYN" id="acqdetailYN" value="%{acqdetailYN==null?'N':acqdetailYN}" onchange="getAcqInfo(this.value);" disabled="true"></s:radio>													
																				</div>
																			</div>
																			<s:if test='"Y".equals(acqdetailYN)'>
																			<div class="boxcontent" id="aquid">
																				<div class="textfield" id="commissionQSPid">
																					<div class="text">
																						<s:text name="label.commissionQSP" />
																					</div>
																					<div class="tbox">
																						<s:textfield name="commissionQ_S" id="commissionQ_S" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled="true"/>													
																					</div>
																				</div>
																				<div class="textfield" id="commissionSurpPid">
																					<div class="text">
																						<s:text name="label.commissionSurpP" />
																					</div>
																					<div class="tbox">
																						<s:textfield name="commission_surp" id="commission_surp" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled="true"/>													
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.overriderP" />
																					</div>
																					<div class="tbox">
																						<s:textfield name="overRidder" id="overRidder" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.brokerageP" />&nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:if test="(broker.toString().trim()).equalsIgnoreCase('DIRECT')">
																							<s:textfield name="brokerage" id="brokerageIDD" cssClass="inputBox" value="0" readonly="true" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="14" disabled="true"/>
																						</s:if>
																						<s:else>
																							<s:textfield name="brokerage" id="brokerageIDD" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="14" disabled="true"/>
																						</s:else>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.taxP" />&nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="tax" id="taxIDDD" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" value='%{"D".equals(profit_Center) ?0.00:tax}' onblur="CalculateValue()"  maxlength="8" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.otherCostP" />
																					</div>
																					<div class="tbox">
																						<s:textfield name="othercost" id="CostOther" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8"  disabled="true"/>
																					</div>
																				</div>
																				<s:hidden name="acquisition_Cost" id="acquisition_Cost"></s:hidden>
																				<%-- <div class="textfield">
																					<div class="text">
																						<s:text name="label.acqCostAmount" />
																					</div>
																					<div class="tbox">
																						<s:textfield name="acquisition_Cost" id="acquisition_Cost" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" />
																					</div>
																				</div> --%>
																			</div>
																			</s:if>
																		</div>
																	</div>
																</div>
															</div>
															<div class="boxcontent">
															<div class="panel panel-primary">											
																	<div class="panel-heading">
																		<s:text name="label.comminfo" />
																	</div>
																	<div class="panel-body">
																		<div class="boxcontent">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.insdetails" />
																				</div>
																				<div class="tbox">
																					<s:radio list="#{'Y':'Yes','N':'No' }" name="commissiondetailYN" id="premiumdetailYN" value="%{commissiondetailYN==null?'N':commissiondetailYN}" onchange="getCommInfo(this.value)" disabled="true"></s:radio>													
																				</div>
																			</div>
																			<s:if test='"Y".equals(commissiondetailYN)'>
																			<div class="boxcontent" id="commid">
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.scaleCommission" />
																					</div>
																					<div class="tbox">	
																						<div class="reinsstyle">
																							<div><s:radio name="slideScaleCommission" list="#{'Y':'Yes','N':'No'}"  value="slideScaleCommission==null || slideScaleCommission==''?'N':slideScaleCommission" onclick="getSlideInfo(this.value)" disabled="true"/></div>
																							<div id="ssc" style="display:none"><button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal1" onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>')">
																					 			     <s:text name="label.enterDetails" />
																					 		</button>
																					 		</div>
																						</div>												
																																			
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.lossporticipents" />
																					</div>
																					<div class="tbox">	
																						<div class="reinsstyle">												
																						<div><s:radio name="lossParticipants" list="#{'Y':'Yes','N':'No'}"  value="lossParticipants==null || lossParticipants==''?'N':lossParticipants" onclick="getLossparInfo(this.value)" disabled="true"/></div>
																						<div id="lp" style="display:none"><button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal1" onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>')">
																					 			     <s:text name="label.enterDetails" />
																					 		</button>
																					 		</div>	
																					 	</div>												
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.profitCommission" />
																					</div>
																					<div class="tbox">	
																						<div class="reinsstyle">												
																						<div><s:radio name="share_Profit_Commission" list="#{'1':'Yes','2':'No'}" value="share_Profit_Commission==null ||share_Profit_Commission==''?'2':share_Profit_Commission" onclick="ShareProfitCommission(this.value)" disabled="true"/></div>
																						<%-- <div id="pc" style="display:none"><button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal2" >
																					 			     <s:text name="label.enterDetails" />
																					 		</button>
																					 		</div>	 --%>
																					 	</div>												
																																			
																					</div>
																				</div>
																			</div>
																			</s:if>
																		</div>
																	</div>
																</div>
															</div>
															<s:if test='"q".equals(share_Profit_Commission)'>
															<div class="boxcontent" id="pc">
																			<div class="panel panel-primary">											
																				<div class="panel-heading">
																					<s:text name="Profit Commission"></s:text>
																				</div>
																				<div class="panel-body">
																					<div class="boxcontent">																						
																						<div class="textfield">
																							<div class="text">
																								<s:text name="label.manexp" />
																							</div>
																							<div class="tbox">												
																							<s:textfield name="managementExpenses" id="managementExpenses"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestriction(this);" maxlength="26" disabled="true"/>	<br/>											
																							</div>
																						</div>
																						<div class="textfield">
																							<div class="text">
																								<s:text name="label.comtype" /> 
																							</div>
																							<div class="tbox">												
																							<s:select  list="commissionTypeList"  name="commissionType" id="commissionType" cssClass="select1 inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getCommissionField(this.value);getpopupenable()"  disabled="true"/>
																							</div>
																						</div>
																						<div class="textfield" id="supcom" style="display:none;">
																							<div class="text">
																								<s:text name="label.supprocom" />
																							</div>
																							<div class="tbox">
																								<s:radio list="#{'Y':'Yes','N':'No'}" name="superProfitCommission" id="superProfitCommission" value="(superProfitCommission==null || superProfitCommission=='') ?'N':superProfitCommission"  onchange="getpopupenable()"  disabled="true"></s:radio>
																									<div  id="compoptype" style="display:none;">
																										<s:if test='"Y"==profitCommissionEnable || "Y".equals(disableStatus1)'>
																											&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
																										</s:if>
																										<s:else>
																										<button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal1" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>')">
																								 			     <s:text name="label.enterDetails" />
																								 		</button>
																										</s:else>
																									</div>		
																							</div>
																						</div>
																						
																						<div class="textfield" id="comper" style="display:none;">
																							<div class="text">
																								<s:text name="label.procomper" /> 
																							</div>
																							<div class="tbox">												
																								<s:textfield name="profitCommissionPer" id="profitCommissionPer"  cssClass="inputBox" onkeyup="checkDecimals(this);" cssStyle="text-align: right;" maxlength="26" disabled="true"/>		<br/>										
																						</div>
																						</div>
																						<div class="textfield" id="setup" style="display:none;">
																							<div class="text">
																								<s:text name="label.stepup" />
																							</div>
																							<div class="tbox">	
																								<div class="reinsstyle">												
																									<div><s:radio list="#{'Y':'Yes','N':'No'}" name="setup" id="setup" value="(setup==null || setup=='')?'Y':setup"  disabled="true"></s:radio></div>
																									<div id="ratioPopUp" style="display:none"><button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal1" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>')">
																								 			     <s:text name="label.enterDetails" />
																								 		</button>
																								 		</div>	
																								 	</div>												
																							</div>
																						</div>
																						<div class="textfield" >
																							<div class="text">
																								<s:text name="label.lossCarFordType" /> 
																							</div>
																							<div class="tbox">												
																							<s:select  list="typeYearListVar"  name="lossCarried" id="lossCarried" cssClass="select1 inputBoxS"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getLossYear(this.value)"  disabled="true"/><br/>
																							</div>
																						</div>
																						<div class="textfield" >
																							<div id="lossyear" style="display:none;">
																								<div class="text">
																									<s:text name="label.lossperidyear" /> 
																								</div>
																								<div class="tbox">
																									<s:textfield name="lossyear" id="lossyear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" disabled="true"/>		<br/>										
																								</div>
																							</div>
																						</div>
																						<div class="textfield">
																							<div class="text">
																								<s:text name="label.profitCarFordType" /> 
																							</div>
																							<div class="tbox">												
																							<s:select  list="typeYearListVar"  name="profitCarried" id="profitCarried" cssClass="select1 inputBoxS"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getProfitYear(this.value)"  disabled="true"/><br/>
																							</div>
																						</div>
																						<div class="textfield" >
																							<div id="profityear" style="display:none">
																								<div class="text">
																									<s:text name="label.profitCarFordyear" /> 
																								</div>
																								<div class="tbox">												
																										<s:textfield name="profitCarriedForYear" id="profitCarriedForYear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" disabled="true"/><br/>
																								</div>
																							</div>
																						</div>
																						
																						<div class="textfield">
																							<div class="text">
																								<s:text name="label.fpcType" />
																							</div>
																							<div class="tbox">
																									<s:select  list="slidingScalePeriodList"  name="pcfpcType" id="pcfpcType" cssClass="select1 inputBoxS"   headerKey="" headerValue="---Select---" listKey="TYPE"  listValue="DETAIL_NAME"   onchange="getPcscalePeriod(this.value);" disabled="true"/>
																							</div>
																						</div>
																						<div class="textfield" id="pcscfiid">
																							<div class="text">
																								<s:text name="label.firstprofitcom" />
																							</div>
																							<div class="tbox">
																									<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" disabled="true"/><br/>
																							</div>
																						</div>	
																						<div class="textfield" id="pcperiodid" style="display:none">
																							<div class="text">
																								<s:text name="label.fpcfixedDate" />
																							</div>
																							<div class="tbox">
																									<s:textfield name="pcfixedDate" id="periodDate"  cssClass="inputBox"  disabled="true"/>
																							</div>
																						</div>
																						
																						<div class="textfield" id="pcscseid">
																							<div class="text">
																								<s:text name="label.period" />
																							</div>
																							<div class="tbox">
																									<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="select1 inputBoxS"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   disabled="true"/><br/>
																							</div>
																						</div>	
																						<div class="textfield" >
																							<div class="text">
																								<s:text name="label.subseqcal" />
																							</div>
																							<div class="tbox">
																								<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   disabled="true" onchange="getSubseqCal(this.value)"></s:radio><br/>	
																							</div>
																						</div>
																						<div id="subseqcalid">
																						<div class="textfield" >
																							<div class="text">
																								<s:text name="label.subprofitcom" />
																							</div>
																							<div class="tbox">
																									<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" disabled="true"/><br/>
																							</div>
																						</div>
																						<div class="textfield" >
																							<div class="text">
																								<s:text name="label.period" />
																							</div>
																							<div class="tbox">
																									<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="select1 inputBoxS"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  disabled="true" /><br/>
																							</div>
																						</div>	
																						</div>	
																						<div class="textfield" >
																							<div class="text">
																							</div>
																							<div class="tbox">
																							</div>
																						</div>	
																						<div class="textfield" >
																							<div class="text">
																								<s:text name="label.notes" />
																							</div>
																							<div class="tbox">
																									<s:textarea rows="2" name="profitCommission" id="profitCommission" cssClass="inputBoxA" cssStyle="width: 90%;" disabled="true"/><br/>
																								<br/>
																							</div>
																						</div>
																						<br class="clear"/>
																						</div>	
																													
																							
																					</div>
																					
																				</div>
																			</div>
																		</s:if>
															<div id="companyModal2" class="modal fade" role="dialog">
																  <div class="modal-dialog modal-lg">
																    <!-- Modal content-->
																    <div class="modal-content">
																      <div class="modal-header">
																        <button type="button" class="close" data-dismiss="modal">&times;</button>
																      </div>
																      <div class="modal-body" >
																        <div class="container-fluid" id="companyAjaxId2">
																        	
																        </div>
																      </div>
																    </div>
																  </div>
															</div>	
															
														<div class="boxcontent">
															<div class="panel panel-primary">											
																	<div class="panel-heading">
																		<s:text name="label.depositdetails" />
																	</div>
																	<div class="panel-body">
																		<div class="boxcontent">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.insdetails" />
																				</div>
																				<div class="tbox">
																					<s:radio list="#{'Y':'Yes','N':'No' }" name="depositdetailYN" id="depositdetailYN" value="%{depositdetailYN==null?'N':depositdetailYN}" onchange="getDepositInfo(this.value)" disabled="true"></s:radio>													
																				</div>
																			</div>
																			<s:if test='"Y".equals(depositdetailYN)'>
																			<div class="boxcontent" id="depositid">													
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.premiumReserveP" /> &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<div class="input-group" style="display: flex;">
																							<s:select list="premiumReserveList" listKey="TYPE" listValue="DETAIL_NAME" name="premiumResType" id="premiumResType" cssClass="inputBoxS" cssStyle="width:50%;" headerKey="" headerValue="---Select---" disabled="true"/>
																							<%-- <span class="input-group-addon"> --%>
																								<s:textfield name="premium_Reserve" id="premium_Reserve" cssClass="inputBox"   cssStyle="width:40%;text-align:right;" onchange="GetPremiumReserveIntr()" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="true"/>
														     								<%-- </span> --%>
																						</div>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.lossReserveP" /> &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="loss_reserve" cssClass="inputBox" cssStyle="text-align:right;" onchange="GetPremiumReserveIntr()" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.interestP" /> &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="interest" id="interset" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.portfolioinoutLossP" /> &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="portfolio_inout_Loss" id="InoutLoss" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.portfolioinoutPremiumP" /> &nbsp; <sup style="color:red;"></sup>
																					</div>
																					
																					<div class="tbox">
																						<div class="input-group" style="display: flex;">
																						<s:select list="premiumReserveList" listKey="TYPE" listValue="DETAIL_NAME" name="portfolioType" id="portfolioType" cssClass="inputBoxS" cssStyle="width:50%;" headerKey="" headerValue="---Select---" disabled="true"/>
																						<%-- <span class="input-group-addon"> --%>
																							<s:textfield name="portfolio_inout_Premium" cssClass="inputBox" cssStyle="width:40%;text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled="true"/>
													     								<%-- </span> --%>
																					</div>
																					</div>
																				</div>
																				<br class="clear"/>
																			</div>
																			</s:if>
																		</div>
																	</div>
																</div>
															</div>
															<div class="boxcontent">
															<div class="panel panel-primary">											
																	<div class="panel-heading">
																		<s:text name="label.lossdetails" />
																	</div>
																	<div class="panel-body">
																		<div class="boxcontent">
																			<div class="textfield">
																				<div class="text">
																					<s:text name="label.insdetails" />
																				</div>
																				<div class="tbox">
																					<s:radio list="#{'Y':'Yes','N':'No' }" name="lossdetailYN" id="lossdetailYN" value="%{lossdetailYN==null?'N':lossdetailYN}" onchange="getLossdetInfo(this.value)" disabled="true"></s:radio>													
																				</div>
																			</div>
																			<s:if test='"Y".equals(lossdetailYN)'>
																			<div class="boxcontent" id="lossid">													
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.lossAdvicePOC" />  &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="loss_Advise" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.cashLossPOC" />  &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="cash_Loss_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.eventLimit" />  &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="event_limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.aggregateLimit" />  &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="aggregate_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
																					</div>
																				</div>
																				<div class="textfield">
																					<div class="text">
																						<s:text name="label.OccurrentLimit" />  &nbsp; <sup style="color:red;"></sup>
																					</div>
																					<div class="tbox">
																						<s:textfield name="occurrent_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
																					</div>
																				</div>
																				<br class="clear"/>
																			</div>
																			</s:if>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="boxcontent" >
															<div class="panel panel-primary">											
																<div class="panel-heading">
																	<s:text name="label.exclusionRemarks" /> 
																</div>
																<div class="panel-body">
																	<div class="boxcontent">
																		<div class="textfieldA25">
																			<s:text name="label.exclusion" />
																		</div>
																		<div class="textfieldA75">
																			<s:textarea id="exclusion" name="exclusion" rows="3" cssClass="inputBoxA" cssStyle="width: 100%; resize: vertical; " disabled="true"/>
																			<br/>
																			<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining"  /> </span>
																		</div>
																		<br class="clear"/>
																	</div>
																	<div class="boxcontent">
																	<div id="remarkid">
																	<table class="table table-bordered" width="100%" id="remark">
																					<thead>
																						<tr>
																							<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																							<th width="10%" style="text-align: center; vertical-align: middle;"> <s:text name="label.description" />  </th>
																							<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark1" />  </th>
																							<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark2" /> </th>
																							<th width="4%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
																						</tr>
																					</thead>
																					<tbody>
																					<s:iterator value="remarkList" var="list" status="stat">
																						<tr>
																								<td >
																			 					<s:textfield name="remarkSNo[%{#stat.count-1}]" id="remarkSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
																			 					</td>
																								<td>
																								<s:textfield name="description[%{#stat.count-1}]" id="description[%{#stat.count-1}]"  cssClass = "inputBox"  disabled="true"/>
																								
																								</td>
																								<td>
																								<s:textarea name="remark1[%{#stat.count-1}]" id="remark1[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; "  disabled="true"/>
																								</td>
																								<td>
																								<s:textarea name="remark2[%{#stat.count-1}]" id="remark2[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " disabled="true"/>
																								</td>
																								<td align="center">
																								<s:if test='0!=(#stat.count-1)'>
																								<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRemark('<s:property value="%{#stat.count-1}"/>')" disabled="true"/>
																								</s:if>
																								</td>
																								<s:hidden name="remarkCount" id="remarkCount" ></s:hidden>
																							</tr>
																							</s:iterator>
																						</tbody>
																						</table>
																					</div>
																				</div>
																		</div>
																</div>
														</div>
													</div>
												</div>
											</div>
							</div>
							<div id="companyModal" class="modal fade" role="dialog">
								  <div class="modal-dialog modal-lg">
								    <!-- Modal content-->
								    <div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal">&times;</button>
								      </div>
								      <div class="modal-body" >
								        <div class="container-fluid" id="companyAjaxId">
								        </div>
								      </div>
								    </div>
								  </div>
								</div>
								<div id="companyModal1" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-lg">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							      </div>
							      <div class="modal-body" >
							        <div class="container-fluid" id="companyAjaxId1">
							        </div>
							      </div>
							    </div>
							  </div>
						</div>	
						<s:if test='"".equals(dropDown) || dropDown==null'>		
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="window.close();" />							
							</div>
						</div>
						</s:if>
								</div>
								<s:hidden name="layerMode" id="layerMode"/>
								<s:hidden name="proposalNo1" id="proposalNo1"/>
					
								<s:hidden name="menuId" id="menuId" />
								<s:hidden name="CustomerId" value="cedingCo" />
								<s:hidden name="layerProposalNo" />
								<%--<s:hidden name="brokerage" />
								<s:hidden name="tax" />--%>
								<s:hidden name="contractno1" />
								<s:hidden name="renewal_contract_no" />
								<s:hidden name="renewalFlag" />
								<s:hidden name="lay1" />
								<s:hidden name="baseLayer" id="baseLayer"/>
								<s:hidden name="baseLoginID" />
								<s:hidden name="amendId" />
								<%--<s:hidden name="contractNo" value="%{contNo}" />
									<s:hidden name="contNo"/>--%>
								<s:hidden name="edit" />
								<s:hidden name="cedType" id="cedType" />
								<s:hidden name="commissionQ_SAmt" />
								<s:hidden name="commission_surpAmt" />
								<s:hidden name="endorsmentno" />
								
								<s:hidden name="countryExcludedList" id="countryExcludedList" />
								<s:hidden name="countryIncludedList" id="countryIncludedList" />
								<s:hidden name="renewalProposalNo" id="renewalProposalNo" />
								<s:hidden name="proposalReference" id="proposalReference" />
								<s:hidden name="preVal" id="preVal"/>
								<s:hidden name="prePerilVal" id="prePerilVal"/>
								<s:hidden name="reMode" id="reMode"/>
								<s:hidden name="renewalEditMode" id="renewalEditMode"/>
								<s:hidden name="slidePopUp" id="slidePopUp"/>
								<s:hidden name="lossPopUp" id="lossPopUp"/>
								<s:hidden name="profitPopUp" id="profitPopUp"/>
								 <s:hidden name="editMode" id="editMode"></s:hidden>
								 <s:hidden name="referenceNo" id="referenceNo"></s:hidden>
								 
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
$('.select1').select2({ });
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }

//toggleVesselBlock('<s:property value="departId"/>');
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

	document.proportional.editMode.value="S";
	
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.proportional.action="FirstPageSaveMethodRiskDetails.action";
	document.proportional.submit();
	
}

function FunctionSaveOption(){

		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
		document.proportional.action="FirstPageSaveMethodRiskDetails.action";
		document.proportional.submit();
}
function FunctionAddLayer()
{
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.getElementById("proposal_no").value="";
	document.getElementById("sectionNo").value="";
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.proportional.action="FirstPageSaveMethodRiskDetails.action";
	document.proportional.submit();
}
function FunctionUpdateOption()
{
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='layer';
	document.getElementById("proposal_no").value=document.proportional.layerProposalNo.value;
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
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
	if('Renewal'==val){
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
	else if('epi1'==type){
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
	var URL ='<%=request.getContextPath()%>/ViewModeCeding?Mode=PopUp&ceddingcompany='+cedding;
	postRequest(URL, 'companyAjaxId');	 
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
	document.proportional.premiumQuotaShare.value=Comma(document.proportional.premiumQuotaShare.value);
	document.proportional.premiumSurplus.value=Comma(document.proportional.premiumSurplus.value);
	
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
		var expirydate=d+"/"+m+"/"+y;
	var URL='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&dropDown=yearId';
	postRequest(URL,'yearId');
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
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
	//document.getElementById("retAdd").disabled = true;	
</s:if>

if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.proportional.underwriter.value);
}
function getUWLimit(value){
  // var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitRiskDetails.action?underwriter='+value+'&dropDown=uwc2';
	postRequest(URL,'uwc2');
}
//GetPML();
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
//getLocDeta('<s:property value="LOCIssued"/>');
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
//getRetentionDetails('<s:property value="retentionYN"/>');
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
function functionEDate()
{
	var	inceptionDate=document.proportional.incepDate.value;
	var	expirydate=document.proportional.expDate.value;
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
}
getBouquest('<s:property value="bouquetModeYN"/>');
function getBouquest(val){
	if(val=="Y"){
    	document.getElementById('bouquetid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('bouquetid').style.display = 'none';
   	}
}
getRiskInfo('<s:property value="riskdetailYN"/>');
function getRiskInfo(val){
	if(val=="Y"){
    	document.getElementById('riskid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('riskid').style.display = 'none';
   	}
}
getBrokerInfo('<s:property value="brokerdetYN"/>');
function getBrokerInfo(val){
	if(val=="Y"){
    	document.getElementById('brokerid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('brokerid').style.display = 'none';
   	}
}
getCoverInfo('<s:property value="coverdetYN"/>');
function getCoverInfo(val){
	if(val=="Y"){
    	document.getElementById('coverid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('coverid').style.display = 'none';
   	}
}
getPremiumInfo('<s:property value="premiumdetailYN"/>');
function getPremiumInfo(val){
	if(val=="Y"){
    	document.getElementById('premiumid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('premiumid').style.display = 'none';
   	}
}
getAcqInfo('<s:property value="acqdetailYN"/>');
function getAcqInfo(val){
	if(val=="Y"){
    	document.getElementById('aquid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('aquid').style.display = 'none';
   	}
}
getCommInfo('<s:property value="commissiondetailYN"/>');
function getCommInfo(val){
	if(val=="Y"){
    	document.getElementById('commid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('commid').style.display = 'none';
   	}
}
getSlideInfo('<s:property value="slideScaleCommission"/>');
function  getSlideInfo(id){
	if(id=="Y"){
    		document.getElementById('ssc').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('ssc').style.display = 'none';
      	}
}
getDepositInfo('<s:property value="depositdetailYN"/>');
function  getDepositInfo(id){
	if(id=="Y"){
    		document.getElementById('depositid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('depositid').style.display = 'none';
      	}
}
getLossdetInfo('<s:property value="lossdetailYN"/>');
function  getLossdetInfo(id){
	if(id=="Y"){
    		document.getElementById('lossid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('lossid').style.display = 'none';
      	}
}
getLossparInfo('<s:property value="lossParticipants"/>');
function  getLossparInfo(id){
	if(id=="Y"){
    		document.getElementById('lp').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('lp').style.display = 'none';
      	}
}

//getDocdetInfo('<s:property value="docdetailYN"/>');
function  getDocdetInfo(id){
	if(id=="Y"){
    		document.getElementById('documentid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('documentid').style.display = 'none';
      	}
}
ShareProfitCommission('<s:property value="share_Profit_Commission"/>');
function ShareProfitCommission(value) {
	if(value=='1'){
	document.getElementById('pc').style.display='block';
	}else if(value=='2'){
	document.getElementById('pc').style.display='none';
	}
}
getCommissionField('<s:property value="commissionType"/>');
function  getCommissionField(id){
	if(id=="PR" || id=="LR"){
   		document.getElementById('comper').style.display = 'none';
   		document.getElementById('supcom').style.display = 'none';
   		document.getElementById('setup').style.display = 'block';
     	} 
  	else if(id=="PC"){
		document.getElementById('comper').style.display = 'block';
		document.getElementById('supcom').style.display = 'block';
		document.getElementById('setup').style.display = 'none';	       	}
	else{
		document.getElementById('comper').style.display = 'none';
		document.getElementById('supcom').style.display = 'none';
		document.getElementById('setup').style.display = 'none';	 
	}
}
getpopupenable();
function getpopupenable(){
	var id = document.getElementById('commissionType').value;
	var slide ="";
	if(id=="PC"){
	 slide = document.proportional.superProfitCommission.value;
	}
	if(id=="PR"){
 		document.getElementById('ratioPopUp').style.display = 'inline';
   	} 
   	else if(id=="LR"){
		document.getElementById('ratioPopUp').style.display = 'inline';
 			       	}
   	else if(id=="PC" && slide =="Y"){
		document.getElementById('compoptype').style.display = 'inline';
 			}
	else{
	document.getElementById('compoptype').style.display = 'none';
	document.getElementById('ratioPopUp').style.display = 'none';
	}
}
getLossYear('<s:property value="lossCarried"/>');
function getLossYear(id){
	if(id!="TE"){
     	document.getElementById('lossyear').style.display = 'block';
      } 
     else{
		document.getElementById('lossyear').style.display = 'none';
     }
}
//DocScript('<s:property value="docStatus"/>');
function DocScript(id){
    var amendId = document.proportional.amendId.value;
    if (amendId > 0) {
        document.proportional.docStatus.value = id;
        if ("Y" == id) {
            document.getElementById('docView').style.display = 'block';
        }
        else {
            document.getElementById('docView').style.display = 'none';
        }
    }
}
function getPaypartner(val,id){
	var cedingid=document.getElementById("CeddingId").value;
	var URL='${pageContext.request.contextPath}/paymentPartnerAjaxXol.action?dropDown='+id+'&cedingId='+cedingid+'&brokerId='+val;
 		postRequest(URL,id);
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
function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	 var type = document.getElementById("commissionType").value;
	 var referenceNo = document.getElementById("referenceNo").value;
	 var disableStatus1 = '<s:property value="disableStatus1"/>';
	 var treatyType = document.proportional.treatyType.value; 
	 if("Y"==disableStatus1 && ""==renewalProposalNo){
		 //renewalProposalNo =proposalNo;
	 }
	 if(renewalProposalNo!=null && renewalProposalNo!=''){
		 proposalNo=renewalProposalNo;
	 }
	 if("scale"==pageFor){
	  document.getElementById("slidePopUp").value = "Y";
	 }else if("lossparticipates"==pageFor){
	  document.getElementById("lossPopUp").value = "Y";
	 }else if("profitCommission"==pageFor){
	  document.getElementById("profitPopUp").value = "Y";
	 }
	
	var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType+"&referenceNo="+referenceNo;
	postRequest(URL, 'companyAjaxId1');	
}
function CalculateValue() {
	var ouracqCost =0;
	var commissionQ_S =  0;
	var commission_surp =  0;
	var premiumQS=0;
	var premiumSurplus=0;
	var epios = 0;
	var inwardType= document.getElementById("inwardType").value;
	var treatyType = document.proportional.treatyType.value; 
	if('2'==inwardType){
		 ouracqCost = document.proportional.ouracqCost.value;
		if(ouracqCost ==  null || ouracqCost ==""){
		ouracqCost =  0;
		}
		}
		if('1'==treatyType || '3'==treatyType ||'4'==treatyType){
		 commissionQ_S = document.proportional.commissionQ_S.value;
		if(commissionQ_S ==  null || commissionQ_S ==""){
		commissionQ_S =  0;
		}
		premiumQS = document.proportional.premiumQuotaShare.value;
		if(premiumQS ==  null || premiumQS ==""){
		premiumQS =  0;
		}
		}
		
		if('2'==treatyType){  
		 commission_surp = document.proportional.commission_surp.value;
		if(commission_surp ==  null || commission_surp ==""){
		commission_surp =  0;
		}
		premiumSurplus = document.proportional.premiumSurplus.value;
		if(premiumSurplus ==  null || premiumSurplus ==""){
		premiumSurplus =  0;
		}
		}
		
		if('3'==treatyType){ 
		 commissionQ_S = document.proportional.commissionQ_S.value;
		if(commissionQ_S ==  null || commissionQ_S ==""){
		commissionQ_S =  0;
		}
		 commission_surp = document.proportional.commission_surp.value;
		if(commission_surp ==  null || commission_surp ==""){
		commission_surp =  0;
		}
		premiumQS = document.proportional.premiumQuotaShare.value;
		if(premiumQS ==  null || premiumQS ==""){
		premiumQS =  0;
		}
		premiumSurplus = document.proportional.premiumSurplus.value;
		if(premiumSurplus ==  null || premiumSurplus ==""){
		premiumSurplus =  0;
		}
		}
		var overRidder = document.proportional.overRidder.value;
		if(overRidder ==  null || overRidder ==""){
		overRidder =  0;
		}
		var brokerage = document.proportional.brokerage.value;
		if(brokerage ==  null || brokerage ==""){
		brokerage =  0;
		}
		var tax = document.proportional.tax.value;
		if(tax ==  null || tax ==""){
		tax =  0;
		}
		var othercost = document.proportional.othercost.value;
		if(othercost ==  null || othercost ==""){
		othercost =  0;
		}
		if(commissionQ_S!=0){
		commissionQ_S=commissionQ_S.replace(new RegExp(',', 'g'),'');
		}if(commission_surp!=0){
		commission_surp=commission_surp.replace(new RegExp(',', 'g'),'');
		}if(overRidder!=0){
		overRidder=overRidder.replace(new RegExp(',', 'g'),'');
		}if(brokerage!=0){
		brokerage=brokerage.replace(new RegExp(',', 'g'),'');
		}if(tax!=0){
		tax=tax.replace(new RegExp(',', 'g'),'');
		}if(othercost!=0){
		othercost=othercost.replace(new RegExp(',', 'g'),'');
		}if(ouracqCost!=0){
		ouracqCost=ouracqCost.replace(new RegExp(',', 'g'),'');
		}//if(epiOSOEViewOC!=0){
		//epiOSOEViewOC=epiOSOEViewOC.replace(new RegExp(',', 'g'),'');
		//}
		if(premiumQS!=0){
		premiumQS=premiumQS.replace(new RegExp(',', 'g'),'');
		}
		if(premiumSurplus!=0){
		premiumSurplus=premiumSurplus.replace(new RegExp(',', 'g'),'');
		}
		if(commissionQ_S!=0){
		var QSAmt=(parseFloat(commissionQ_S)*parseFloat(premiumQS))/100;
		//document.proportional.commissionQ_SAmt.value=Comma(QSAmt.toFixed(2));
		}
		if(commission_surp!=0){
		var SurpAmt=(parseFloat(commission_surp)*parseFloat(premiumSurplus))/100;
		
		//document.proportional.commission_surpAmt.value=Comma(SurpAmt.toFixed(2));
		}
		<%--if(''!='<s:property value="epiAsPerShare"/>' && '0'!='<s:property value="epiAsPerShare"/>'){
		 epios = '<s:property value="epiAsPerShare"/>';
		}
		else --%>
		if(''!='<s:property value="epiOSViewOC"/>'){
		 epios = '<s:property value="epiOSViewOC"/>';
		 if(epios!=0){
			epios=epios.replace(new RegExp(',', 'g'),'');
		}
		}
		var share ='<s:property value="shareValue"/>' ;
		var com =0;
		var sur =0;
		var total =0;
		var val = 0;
		if('3'==treatyType){ 
		 com = parseFloat(premiumQS)*parseFloat(share)/100*parseFloat(commissionQ_S)/100;
		 sur = parseFloat(premiumSurplus)*parseFloat(share)/100*parseFloat(commission_surp)/100;
		 total = parseFloat(epios)*((parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		
		}
		if('1'==treatyType || '5'==treatyType ||'4'==treatyType){
		 total = parseFloat(epios)*((parseFloat(commissionQ_S)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		}
		else if('2'==treatyType){  
		 total = parseFloat(epios)*((parseFloat(commission_surp)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		}
		 val =  parseFloat(ouracqCost) +  com +sur+ total ;
		//var sum = parseFloat(premiumQS) + parseFloat(premiumSurplus);
		
		//var val= parseFloat(ouracqCost) + ((parseFloat(epiOSOEViewOC) *(parseFloat(tot)))/100)
		document.proportional.acquisition_Cost.value=Comma(val.toFixed(2));
		//document.proportional.acqCostPer.value=Comma(total.toFixed(2));
		//document.getElementById("acquisition_Cost").value=Comma(val.toFixed(2));
	}
function funEditMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
   // document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='layer';
    document.proportional.action="EditSectionRiskDetails.action?departmentId="+deptId;
    
    document.proportional.submit();
}
function funDeleteLayer(no){
	destroyPopUps();
	document.getElementById("proposalReference").value='Layer';
	document.getElementById("renewalProposalNo").value=no;
	document.proportional.action='${pageContext.request.contextPath}/CancelSectionRiskDetails.action';
	document.proportional.submit();
	
}
function funCopyMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
   // document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='copy';
    document.proportional.action="EditSectionRiskDetails.action?departmentId="+deptId;
    document.proportional.submit();
}
function fnSubmitScale(){
	postFormRequest('${pageContext.request.contextPath}/insBonusDetailsRiskDetails.action', "companyAjaxId1", "proportional");
}
function scaleremoveRow(val){
	var scale=document.getElementById("pageFor").value;
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowRiskDetails.action?mode=delete&deleteId="+val+"&dropDown="+scale, "tb1", "proportional");
			}
		}
}
function scaleinsRow(tableID)
{
	var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "scaleSNo["+(rowCount-1)+"]";
       		element1.id = "scaleSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			
			var cell2 = row.insertCell(1);
			//cell2.setAttribute('style','background-color:#C6E2FF;');
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "scaleFrom["+(rowCount-1)+"]";
      		element2.id = "scaleFrom["+(rowCount-1)+"]";
			element2.className = "inputBox";
			element2.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "scaleTo["+(rowCount-1)+"]";
      		element3.id = "scaleTo["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("style", "text-align:right;");
			element3.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "scaleLowClaimBonus["+(rowCount-1)+"]";
      		element4.id = "scaleLowClaimBonus["+(rowCount-1)+"]";
			element4.className = "inputBox";
			element4.setAttribute("style", "text-align:right;");
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			//element5.setAttribute("onclick", "deleteRow('bonusTbl','"+(rowCount)+"')");
			element5.setAttribute("onclick", "scaleremoveRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-info"
			cell5.appendChild(element5);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}
</script>
	</body>
</html>
