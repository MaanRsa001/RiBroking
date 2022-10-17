<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<s:form name="proTreatyView" id="proTreatyView" action="" method="post" theme="simple">
<s:set name="profitCommissionListVar" value="profitCommissionList"/>
<s:set name="typeYearListVar" value="typeYearList"/>
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
										<s:text name="Heading.PROPORTIONALTREATY" />
									</div>
									<div class="panel-body">
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ProposalNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposal_no" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.ContNo" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="contNo" id="contNo" cssClass="inputBox" cssStyle="text-align: right;" />
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
											<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
												<s:text name="RiskDetails.InwardType" />
											</s:if>
											<s:else>
												<s:text name="RiskDetails.InwardType02" />
											</s:else>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="inwardType" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfieldA25">
												<s:text name="RiskDetails.SubProfitCentre" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" readonly="true" name="subProfit_center" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;"/>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Underwriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="underwriter" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.MaximumLimit" />(<s:property value="shortname"/>)
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="maxLimit_Product" cssClass="inputBox" cssStyle="text-align: right;" />
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
												<s:text name="RiskDetails.CedingCompany" />
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
												<s:text name="RiskDetails.TreatyType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatyName" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatyNameType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatyName_type" cssClass="inputBox" />
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
												<s:textfield readonly="true" name="orginalCurrency" id="orginalCurrency" cssClass="inputBox" cssStyle="float: left; width:50%;" />
												<s:textfield readonly="true" name="exchRate" cssClass="inputBox" cssStyle="float: left; width:45%;text-align: right;" />
												<br class="clear" />
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
												<s:text name="Facultative.No.OfInsurar" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="no_Insurer" cssClass="inputBox" />
											</div>
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
												<s:text name="label.cleancutrunoff" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="proposalType" cssClass="inputBox" />
											</div>
										</div>
										<s:if test="proposalType !=null && proposalType !='' && proposalType !='Clean-Cut'">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.rate.year" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="runoffYear" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioCovered" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="riskCovered" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.LOCIssued" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="lOCIssued" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Yes" == lOCIssued'>
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
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PNOC" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pnoc" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AccountingPeriod" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="accountingPeriod" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.StatementDue" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="receiptofStatements" cssClass="inputBox" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.PaymentDue" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="receiptofPayment" cssClass="inputBox" />
											</div>
										</div>	
												
										<hr class="clear"/>
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
										 <div class="textfieldA25">
												<s:text name="label.perilCovered" />
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
												<s:text name="riskDetails.cedRetenType" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedRetenType" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.CedantsRetention" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="cedReten" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<s:if test="treatynoofLine != 0">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.noofline" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="treatynoofLine" cssClass="inputBox" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypml" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pml" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>
										<s:if test='"Yes"==pml'>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.treatypmlper"/>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="pmlPercent" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div></s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.OurEstimateView" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="ourEstimate" cssStyle="text-align:right;" cssClass="inputBox" />
											</div>
										</div>										
										<div class="textfield33">
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
										</div>
										<s:if test='"Yes"==pml'>
										<div class="textfield33">
										</div>
										</s:if>
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
										<s:if test='"Yes"==retentionYN'>
										<div class="panel panel-default">											
											<div class="panel-heading"> Retention </div>
											<div class="panel-body">
												<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="label.retentionYN" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="retentionYN" cssClass="inputBox" />
											</div>											
										</div>
										<table class="table table-bordered" id="newgen" width="100%" >				
									<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Sno" />  </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Sub Class" />  </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Business" />  </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Retension" />  </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Basis" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="1st Retention" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"><s:text name="2nd Retention" /></th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(FST)" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(SST)" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(FST)" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(SST)" /> </th>
											
										</tr>
									</thead>
									<tbody>
									<s:iterator value="retList" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="retSNo[%{#stat.count-1}]" id="retSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
						 					</td>
											<td>
											<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})" disabled="true" theme="simple"/>
											</td>
											<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
											<%--<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"  multiple="true" theme="simple" disabled="true"/>--%>
											<s:textfield name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBox" id="coversubdepartId%{#stat.count-1}" disabled="true"/>
											</td>
											<td >
											<s:select list="retBusinessTypeList"  name="retBusinessType[%{#stat.count-1}]" id="retBusinessType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled="true"/>
											</td>											
											<td >
											<s:select list="retTypeList"  name="retType[%{#stat.count-1}]" id="retType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:select list="basicTypeList"  name="retBasis[%{#stat.count-1}]" id="retBasis[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"   listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="firstretention[%{#stat.count-1}]" id="firstretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="secondretention[%{#stat.count-1}]" id="secondretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="retTreatyFST[%{#stat.count-1}]" id="retTreatyFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="retTreatySST[%{#stat.count-1}]" id="retTreatySST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="retEventFST[%{#stat.count-1}]" id="retEventFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
											<td>
											<s:textfield name="retEventSST[%{#stat.count-1}]" id="retEventSST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled="true"/>
											</td>
												<s:hidden name="count" value="%{#stat.count-1}"></s:hidden>
												<s:hidden name="loopcount" id="loopcount"></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								</div>
								</div>
								</div>
								</s:if>
								<hr class="clear"/>
										<table class="table table-bordered">
											<thead>
											<tr>
												<th width="16.66%">100%</th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/></th>
												<th width="16.66%">Our Share</th>
												<th width="16.66%">OC</th>
												<th width="16.66%"><s:property value="shortname"/></th>
											</tr>
											</thead>
											<tbody>
											<s:if test='departClass !=null && departClass!=""&&departClass=="Aviation"'>
											 <tr>
												<td>
													<s:text name="label.limitpervessel" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.limitpervessel" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerVesselOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											
											
											<tr>
												<td>
													<s:text name="label.limitperlocation" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.limitperlocation" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitPerLocationOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>
											<s:if test="limitOrigCur !='0.00' ">
											<tr>
												<td>
													<s:text name="label.treatylimit100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOrig_CurDc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.treatylimit100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>
											<s:if test="faclimitOrigCur !='0.00'">
											<tr>
												<td>
													<s:text name="label.treatylimit100%.obj" />
												</td>
												<td>
													<s:textfield readonly="true" name="faclimitOrigCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="facLimitOrig_CurDc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.treatylimit100%.obj" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="limitOurShare_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>
												<%--<s:if test="limitOrigCurPml !='0.00'">
												<tr>
													<td>
														<s:text name="label.treatyLimitpmlOC" />
													</td>
													<td>
														<s:textfield readonly="true" name="limitOrigCurPml" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													<td>
														<s:textfield readonly="true" name="limitOrigCurPmlDC" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													<td>
														<s:text name="label.treatyLimitpmlourshare" />
													</td>
													<td>
														<s:textfield readonly="true" name="limitOrigCurPmlOS" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
													<td>
														<s:textfield readonly="true" name="limitOrigCurPmlOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
													</td>
												</tr>
												</s:if>--%>
											<s:if test="treatyLimitsurplusOC !='0.00'">
											<tr>
												<td>
													<s:text name="label.treatysurpluslimit100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.treatysurpluslimit100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOurShareDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>
											<%--<s:if test="treatyLimitsurplusOCPml !='0.00'">
											<tr>
												<td>
													<s:text name="label.treatyLimitsurplusOCpml" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOCPml" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOCPmlDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.treatyLimitsurplusOSpml" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOCPmlOS" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="treatyLimitsurplusOCPmlOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											</s:if>--%>
											<%-- <tr>
												<td>
													<s:text name="label.xlcost100%" />
												</td>
												<td>
													<s:textfield readonly="true" name="xlCost" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="xlCost_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.XLCostOurShare" />
												</td>
												<td>
													<s:textfield readonly="true" name="xlcostOurShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="xlcostOurShare_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>--%>
											<tr>
												<td>
													<s:text name="label.epi100asperofferView" />
												</td>
												<td>
													<s:textfield readonly="true" name="epi_origCur" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epi_origCur_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.epi100asperofferView" />
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
													<s:text name="label.epi100ourestimateView" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiEstmate_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													<s:text name="label.epi100ourestimateView" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiOurShareEs" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="epiOurShareEs_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
											<td>
													<s:text name="label.lossadvice" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_Advise" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_Advise_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.lossadvice" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_AdviseOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="loss_AdviseOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											<tr>
											<td>
													<s:text name="label.cashlosslimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_Limit_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="label.cashlosslimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="cash_Loss_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.EventLimit" /></td>
												<td>
													<s:textfield readonly="true" name="event_limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
												
													<s:text name="RiskDetails.EventLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="event_limitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.AggregateLimit" /></td>
												<td>
													<s:textfield readonly="true" name="aggregate_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_Limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.AggregateLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="aggregate_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.OccurrentLimit" /></td>
												<td>
													<s:textfield readonly="true" name="occurrent_Limit" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_Limit_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.OccurrentLimit" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_LimitOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="occurrent_LimitOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
												<td><s:text name="RiskDetails.AcquisitionCost" /></td>
												<td>
													<s:textfield readonly="true" name="acquisition_Cost" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="acquisition_Cost_Dc" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.AcquisitionCost" />
												</td>
												<td>
													<s:textfield readonly="true" name="acquisition_CostOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="acquisition_CostOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</tr>
											<tr>
											<s:if test='"4".equals(treatyType) ||"5".equals(treatyType)'>
											
												<td>
								
												</td>
												<td>
												</td>
												<td>
												</td>
												
												<td>
														<s:text name="Premium " />
													
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											</s:if>
											<s:else>
											<td>
												
													<s:text name="RiskDetails.PremiumQuotaShare" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShare_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													
													<s:text name="RiskDetails.PremiumQuotaShare" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShareOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumQuotaShareOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
											
											
											</s:else>
											
												
											</tr>
											<s:if test=' "2".equals(treatyType) ||"3".equals(treatyType)'>
											<tr>
											<td>
													<s:text name="RiskDetails.PremiumSurplus" />
													
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplus" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplus_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
												<td>
													<s:text name="RiskDetails.PremiumSurplus" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplusOSOC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="premiumSurplusOSDC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>
											</s:if>
											<%-- <tr>
											<td>
												<s:text name="RiskDetails.CommisQ/SAmt" />
												</td>
												<td>
													<s:textfield readonly="true" name="commissionQ_SAmt" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="commissionQ_SAmt_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:text name="RiskDetails.CommisSurpAmt" />
												</td>
												<td>
													<s:textfield readonly="true" name="commission_surpAmt" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												<td>
													<s:textfield readonly="true" name="commission_surpAmt_DC" cssClass="inputBox" cssStyle="text-align: right;" />
												</td>
												
											</tr>--%>
											
											</tbody>
										</table>
										<hr class="clear"/>
										<div class="panel panel-default">											
											<div class="panel-heading"> <s:text name="label.acquisitionCost" /> </div>
											<div class="panel-body">
												<div class="boxcontent">
										<s:if test='"Retro".equals(inwardType)'>
										<div class="textfield33">
												<div class="text">
													<s:text name="label.orginalacqcost" />
												</div>
												<div class="tbox">
													<%--<s:textfield name="orginalacqcost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this)"  maxlength="8" />--%>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="orginalacqcost" value="orginalacqcost==null || orginalacqcost==''?'N':orginalacqcost" onchange="getOcqCostValue(this.value);CalculateValue();" disabled="true"></s:radio>

												</div>
											</div>
											<div class="textfield33" id="oac">
												<div class="text">
													<s:text name="label.ourassessmentorginalacqcost" />
												</div>
												<div class="tbox">
													<s:textfield name="ourassessmentorginalacqcost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="CalculationOurOcqCost();CalculateValue();" maxlength="8" disabled="true"/>
												</div>
											</div>
											<div class="textfield33" id="oc">
												<div class="text">
													<s:text name="label.oueassessmentorginalacqcost" />
												</div>
												<div class="tbox">
													<s:textfield name="ouracqCost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" onblur="CalculateValue()" maxlength="8" disabled="true" />
												</div>
											</div>
										
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.rate" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="locRate" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:if test=' "4".equals(treatyType) ||"5".equals(treatyType)'>
													<s:text name="label.commissionQSP.obj" />
												</s:if>
												<s:else>
												<s:text name="RiskDetails.CommissionQuotaShare" />
												</s:else>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="commissionQ_S" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										<s:if test=' "2".equals(treatyType) || "3".equals(treatyType)'>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.CommissionSurplus" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="commission_surp" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>
										</div>
										</s:if>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.Overrider" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="overRidder" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
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
										<s:if test='!"0".equals(no_Insurer)'> 
											<%--<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AcqCostPer" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqCostPer" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.AcqCostPerDC" /><s:property value="shortname"/>
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="acqCostPerDC" cssClass="inputBox" cssStyle="text-align:right;" />
											</div>
										</div>--%>
										<div class="boxcontent">
										<div class="panel panel-default">											
											<div class="panel-heading"> <s:text name="label.retroContracts" /> </div>
											<div class="panel-body">
												<div class="boxcontent">
										<div class="textfield33">
											<div class="text">
												<s:text name="facultative.retentionPer" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="RetentionPercentage" cssClass="inputBox" cssStyle="text-align:right;" value="%{RetentionPercentage == null ? '':RetentionPercentage}" onkeyup="checkDecimals(this)" />
											</div>
										</div>
										<hr class="clear"/>
										
										<table class="table table-bordered" width="100%">
											<thead>
											<tr>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.InsuraerNo" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="RiskDetails.U/WYear" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.CeddingCompany" /> </th>
												<th style="text-align: center; vertical-align: middle;"> <s:text name="Facu.Retro" /> </th>
											</tr>
											</thead>
											<tbody>
											<!-- Iterator starts Here -->
											<s:iterator value="retroFinalList" var="retroContract" status="stat">
												<tr>
													<td>
													<s:property value='%{#stat.count}'/>
													</td>
													<td>
													    <s:textfield name="retroYear[%{#stat.count-1}]"  cssStyle="text-align:right;" readonly="true" cssClass="inputBox"  />
													</td>
													<td id="retroYear<s:property value='%{#stat.count-1}'/>">
													     <s:textfield name="retroCeding[%{#stat.count-1}]"  cssStyle="text-align:right;" readonly="true" cssClass="inputBox"  />
													</td>
													<td>
														<s:textfield name="percentRetro[%{#stat.count-1}]" id="percentRetro[%{#stat.count-1}]"  cssStyle="text-align:right;" cssClass="inputBox" readonly="true" />
													</td>
												</tr> 
												</s:iterator>
											<!-- Iterator ends Here -->
											</tbody>
										</table>
										</div>
										</div>
										</div>
										</div>
										</s:if>
										
										<div class="textfield33">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus" value="crestaStatus==null?'N':crestaStatus" onchange="toggleCrestapopUp(this.value);" disabled="true" ></s:radio>
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="cresta" style="display:none">
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="CrestapopUp('<s:property value="contNo"/>','<s:property value="proposal_no"/>','<s:property value="amendId"/>','<s:property value="orginalCurrency"/>')"><s:text name="label.viewDetails" /></a>
													
												</div>
											</div>
											<div class="textfield33"  >
												<s:if test="''.equals(baseLayer) ||null.equals(baseLayer)">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="crestacommissionSubClass" id="crestacommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="crestacommissionSubClass==null?'2':crestacommissionSubClass" disabled="true"/>													
													</div>
												</s:if>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.scaleCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="slideScaleCommission" list="#{'Y':'Yes','N':'No'}"  value="slideScaleCommission==null?'N':slideScaleCommission"  disabled="true"/>													
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="ssc" style="display:none">
													<div class="text">
														<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="renewalProposalNo"/>')"><s:text name="label.viewDetails" /></a>
													</div>
												</div>	
											</div>
											<div class="textfield33"  >
											<s:if test="''.equals(baseLayer) ||null.equals(baseLayer)">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="slidecommissionSubClass" id="slidecommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="slidecommissionSubClass==null?'2':slidecommissionSubClass" disabled="true"/>													
													</div>
											</s:if>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.lossporticipents" />
												</div>
												<div class="tbox">													
													<s:radio name="lossParticipants" list="#{'Y':'Yes','N':'No'}"  value="lossParticipants==null?'N':lossParticipants"  disabled="true"/>													
												</div>
											</div>
											<div class="textfield33">
												<div class="text"  id="lp" style="display:none">
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="renewalProposalNo"/>')"><s:text name="label.viewDetails" /></a>
												</div>
											</div>		
											<div class="textfield33"  >											
											<s:if test="''.equals(baseLayer) ||null.equals(baseLayer)">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="losscommissionSubClass" id="losscommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="commissionSubClass==null?'2':commissionSubClass" disabled="true"/>													
													</div>
											</s:if>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.profitCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="share_Profit_Commission" list="#{'1':'Yes','2':'No'}"  value="share_Profit_Commission==null?'N':share_Profit_Commission" disabled="true" />													
												</div>
											</div>
											<div class="textfield33">
											
											</div>
											<div class="textfield33">
											<s:if test="''.equals(baseLayer) ||null.equals(baseLayer)">
												<div class="text">
													<s:text name="label.commsubclass" />
												</div>
												<div class="tbox">													
													<s:radio name="commissionSubClass" id="commissionSubClass" list="#{'1':'Yes','2':'No'}" value="commissionSubClass==null?'N':commissionSubClass"  disabled="true"/>													
												</div>
											</s:if>
											</div>	
											<hr class="clear"/>			
										<div class="boxcontent" id="pc" style="display:none">													
											<div class="textfield33">
												<div class="text">
													<s:text name="label.manexp" /> 
												</div>
												<div class="tbox">												
												<s:textfield name="managementExpenses" id="managementExpenses"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this)" readonly="true"/>	<br/>											</div>
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.comtype" /> 
												</div>
												<div class="tbox">												
													<s:select  list="commissionTypeList"  name="commissionType" id="commissionType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getCommissionField(this.value);getpopupenable(this.value)" disabled="true"/><br/>
												</div>
											</div>
											<div class="textfield33" id="supcom" style="display:none">
												<div class="text">
													<s:text name="label.supprocom" /> 
												</div>
												<div class="tbox">
													<div class="row">
														<div class="col-xs-6">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="superProfitCommission" id="superProfitCommission" value="superProfitCommission==null?'N':superProfitCommission"  onchange="getpopupenable(this.value)" disabled="true"></s:radio>
														</div>
														<div class="col-xs-6" id="compoptype" style="display:none;">
														<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
														</div>
													</div>	
												</div>
												
											</div>	
											<div class="textfield33" id="comper" style="display:none">
												<div class="text">
													<s:text name="label.procomper" /> 
												</div>
												<div class="tbox">												
													<s:textfield name="profitCommissionPer" id="profitCommissionPer"  cssClass="inputBox" cssStyle="text-align: right;" readonly="true"/>		<br/>										
												</div>
											</div>
											<div class="textfield33" id="setup" style="display:none">
												<div class="text">
													<s:text name="label.setup" /> 
												</div>
												<div class="tbox">	
													<div class="row">
														<div class="col-xs-6">											
															<s:radio list="#{'Y':'Yes','N':'No'}" name="setup" value="setup==null?'N':setup"  disabled="true"></s:radio>
														</div>
														<div class="col-xs-6" id="ratioPopUp" style="display:none;">
															<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
														</div>
													</div>
												</div>
											</div>
											<div>
											<div class="textfield33" >
												<div class="text">
													<s:text name="label.lossCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="lossCarried" id="lossCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getLossYear(this.value)" disabled="true"/><br/>
											</div>
											</div>
											<div class="textfield33" id="lossyear" style="display:none">
												<div class="text">
													<s:text name="label.lossperidyear" /> 
												</div>
												<div class="tbox">
													<s:textfield name="lossyear" id="lossyear"  cssClass="inputBox" cssStyle="text-align: right;" readonly="true" />		<br/>										
													</div>
											</div>
											</div>
											<div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.profitCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="profitCarried" id="profitCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getProfitYear(this.value)" disabled="true"/><br/>
											</div>
											</div>
											<div class="textfield33" id="profityear" style="display:none">
												<div class="text">
													<s:text name="label.profitCarFordyear" /> 
												</div>
												<div class="tbox">												
														<s:textfield name="profitCarriedForYear" id="profitCarriedForYear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
											</div>
											</div>
											</div>
											<div class="textfield33">
												<div class="text">
													<s:text name="label.firstprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS" disabled="true" /><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.subprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);" readonly="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  disabled="true"/><br/>
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.subseqcal" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null?'N':subSeqCalculation"  disabled="true"></s:radio><br/>	
												</div>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.notes" />
												</div>
												<div class="tbox">
														<s:textarea rows="1" name="profitCommission" id="profitCommission" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" disabled="true"/><br/>
												</div>
											</div>	
											<hr class="clear"/>
											</div>
									
										<div class="textfield33">
											<div class="text">
												<s:text name="label.premiumReserveP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="premium_Reserve" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.lossReserveP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="loss_reserve" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.interestP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="interest" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioinoutLossP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="portfolio_inout_Loss" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<div class="textfield33">
											<div class="text">
												<s:text name="label.portfolioinoutPremiumP" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="portfolio_inout_Premium" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<hr class="clear"/>
										<div class="textfield33">
											<div class="text">
												<s:text name="RiskDetails.LeadUnderWriter" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter" cssClass="inputBox" />
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
												<s:text name="RiskDetails.LeadUnderwriterShare" />
											</div>
											<div class="tbox">
												<s:textfield readonly="true" name="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" />
											</div>											
										</div>
										<hr class="clear"/>	
										<div class="panel panel-default">											
										<div class="panel-heading">&nbsp;&nbsp;&nbsp; </div>
										<div class="panel-body">
											<div class="boxcontent">										
										<div class="textfieldA25" style="font-weight: normal;">
											<s:text name="RiskDetails.Exclusion" />
										</div>
										<div class="textfieldA75">
											<s:textarea rows="3" name="exclusion" cssClass="inputBoxA" cssStyle="width: 90%;resize: vertical;" readonly="true" />
										</div>											
										
										<hr class="clear"/>									
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
										<div class="panel-heading"> All Remarks </div>
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
<s:hidden name="flag" id="flag"/>		
<s:hidden name="menuId" id="menuId"/>
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
			document.proTreatyView.action ="commonListPortfolio?manufactureID="+productId+"&Department="+deptId;
		}else if(flag=='C') {
			document.proTreatyView.action="InitPortfolio";
		}else if(flag=='H') {
			document.proTreatyView.action="getHistoryPortfolio";
		}else if(flag=='R') {
			document.proTreatyView.action="commonListPortfolio?manufactureID="+productId;
		}else if(flag=='N') {
			document.proTreatyView.action="commonListPortfolio?manufactureID="+productId;
		}else if(flag=='RP') {
			document.proTreatyView.action ="commonListPortfolio?manufactureID="+productId;
		}else if(flag=='RD') {
			document.proTreatyView.action="InitPortfolio?";
		}else if(flag=='EC') {
			document.proTreatyView.action="InitPortfolio?";
		}
		document.proTreatyView.submit();
	}
	
	function getPrint() {
		$(".btn").hide();
		window.print();
		$(".btn").show(); 
		}
	function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	var type = document.getElementById("commissionType").value;
	var treatyType= '<s:property value="treatyType"/>';
	var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&mode=view&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&renewalProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType;
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
		funView('<s:property value="slideScaleCommission"/>');
	function  funView(id){
			if(id=="Y"){
	     		document.getElementById('ssc').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('ssc').style.display = 'none';
	       	}
	}
	funLPView('<s:property value="lossParticipants"/>');
	function  funLPView(id){
			if(id=="Y"){
	     		document.getElementById('lp').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('lp').style.display = 'none';
	       	}
	}
	funLProfitiew('<s:property value="share_Profit_Commission"/>');
	function  funLProfitiew(id){
			if(id=="1"){
	     		document.getElementById('pc').style.display = 'block';
	       	} 
	       	else if(id=="2"){
	       	 	document.getElementById('pc').style.display = 'none';
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
	
	getLossYear('<s:property value="lossCarried"/>');
	function getLossYear(id){
			if(id=="TE"){
	     		document.getElementById('lossyear').style.display = 'none';
	       		} 
	       	else{
				document.getElementById('lossyear').style.display = '';
	     		  }
	}
	getProfitYear('<s:property value="profitCarried"/>');
	function getProfitYear(id){
			if(id=="TE"){
	     		document.getElementById('profityear').style.display = 'none';
	       		} 
	       	else{
				document.getElementById('profityear').style.display = '';
	     		  }
	}
	getpopupenable('<s:property value="commissionType"/>');
	function getpopupenable(id){
		var id = document.getElementById('commissionType').value;
		var slide ="";
		if(id=="PC"){
		 slide = document.proTreatyView.superProfitCommission.value;
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
	
toggleCrestapopUp('<s:property value="crestaStatus"/>');
function toggleCrestapopUp(value){
if(value=='Y'){
document.getElementById('cresta').style.display='block';
}else if(value=='N'){
document.getElementById('cresta').style.display='none';
}

}
function CrestapopUp(contNo,proposalNo,amendId,orginalCurrency){
	var contractNo = document.getElementById("contNo").value;
	var proposalno = document.getElementById("proposalNo").value;
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
var val=document.getElementById('loopcount').value;
<%--$(document).ready(function() {
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
 <s:iterator value="retList"  status="stat">   
  var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
</s:iterator>--%>

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
			mtbChildWin[mtbWinCound] = strOpen;mtbWinCound++;
			strOpen.focus();
			return false;
	}
	getOcqCostValue('<s:property value="orginalacqcost"/>');
	function getOcqCostValue(id){
	    var type ='<s:property value="inwardType"/>';
	    if(type=='Retro'){
			if("Y"==id){
				document.getElementById('oac').style.display = 'block';
				document.getElementById('oc').style.display = 'block';
			}
			else {
				document.getElementById('oac').style.display = 'none';
				document.getElementById('oc').style.display = 'none';
			}
		}
	}
</script>
</body>
</html>

