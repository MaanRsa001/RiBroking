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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	 $(function() {
		$( "#endorsementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	</script>
</head>

<body onload="Commas(<s:property value="#session.mfrid" />);PremiumQuotaShare1();ShareProfitCommission('<s:property value='share_Profit_Commission' />','<s:property value='#session.mfrid' />');">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="proportional2" name="proportional2" theme="simple" action="" method="post" enctype="multipart/form-data" autocomplete="off">
					<s:set name="line" value="request.getAttribute('InstalMentNumber')==null||''.equalsIgnoreCase(request.getAttribute('InstalMentNumber').toString())?0:Integer.parseInt(request.getAttribute('InstalMentNumber').toString())" />
					<s:set name="NoRetroCess" value="request.getAttribute('NoRetroCess')==null||''.equalsIgnoreCase(request.getAttribute('NoRetroCess').toString())?0:Integer.parseInt(request.getAttribute('NoRetroCess').toString())" />
					<s:set name="noIns" value="request.getAttribute('No_Insurer')==null||''.equalsIgnoreCase(request.getAttribute('No_Insurer').toString())?0:Integer.parseInt(request.getAttribute('No_Insurer').toString())" />
					<s:set name="profitCommissionListVar" value="profitCommissionList"/>
					<s:set name="typeYearListVar" value="typeYearList"/>
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<s:if test="contNo != '' && contNo != null && proposal_no != null && proposal_no != '' && amend_Id_Mode != '' && amend_Id_Mode != null">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<%--<div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox txtB">
													<s:property value="contNo"/>
												</div>
											</div> --%>
											<s:if test="amendId != '' &&  amendId !='0'">
												<div class="textfield">
													<div class="text">
														<s:text name="label.endorsementType" />
													</div>
													<div class="tbox txtB">
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
													<s:property value="proposal_no"/>
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
													<s:property value="subProfit_center"/>
													<s:hidden name="subProfit_center"></s:hidden>
												</td>
												
												
											</tr>
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.cedingCompany" />
												</td>
												<td width="16.66%">
													<s:property value="cedingCo"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.broker" />
												</td>
												<td width="16.66%">
													<s:property value="broker"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.uwYear" />
												</td>
												<td width="16.66%">
													<s:property value="uwYear"/>
													<s:hidden name="uwYear" />
												</td>
											</tr>
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.treatyNameType" />
												</td>
												<td width="16.66%">
													<s:property value="treatyName_type"/>
													<s:hidden name="treatyName_type" />
												</td>											
												<td width="16.66%" class="txtB"></td>
												<td width="16.66%"></td>
												<td width="16.66%" class="txtB"></td>
												<td width="16.66%"></td>
											</tr>
											</table>
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
											<table width="100%" class="footable">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.ourCurrency" /> </th>
													<th width="25%"> <s:property value="shortname" /><s:hidden name="shortname"/></th>
												</tr>
												</thead>
												<tbody>
												<s:if test="limitOSViewOC!='' && limitOSViewOC!=null ">												
												<tr>
												<s:if test=' "4".equals(treatyType) ||"5".equals(treatyType)'>
														<td width="50%"><s:text name="label.treatyLimitOurShare.Obj" /></td>
													</s:if>
													<s:else>
													<td> <s:text name="label.treatyLimitOurShare" /> </td>
													</s:else>
													<td align="right"> <s:property value="limitOSViewOC"/> <s:hidden name="limitOurShare" />  <s:hidden name="limitOSViewOC" /> </td>
													<td align="right"> <s:property value="limitOSViewDC"/> <s:hidden name="limitOSViewDC" /> </td>
												</tr>
												</s:if>
												<s:if test="treatyLimitsurplusOSViewOC!='' && treatyLimitsurplusOSViewOC!=null ">	
												<tr>
													<td> <s:text name="label.treatyLimitsurOurShare" /> </td>
													<td align="right"> <s:property value="treatyLimitsurplusOSViewOC"/> <s:hidden name="treatyLimitsurplusOurShare" />  <s:hidden name="treatyLimitsurplusOSViewOC" /> </td>
													<td align="right"> <s:property value="treatyLimitsurplusOSViewDC"/> <s:hidden name="treatyLimitsurplusOSViewDC" /> </td>
												</tr>
												</s:if>		
												<s:if test="epiOSOEViewOC!='' && epiOSOEViewOC!=null && epiOSOEViewOC!='0.00'  ">
												<tr>
													<td> <s:text name="label.epiOurAssessmentOurShareOE" /> </td>
													<td align="right"> <s:property value="epiOSOEViewOC"/> <s:hidden name="epiOSOEViewOC" /> <s:hidden name="epiAsPerShare" /> </td>
													<td align="right"> <s:property value="epiOSOEViewDC"/> <s:hidden name="epiOSOEViewDC" /> </td>
												</tr>	
												</s:if>
												<s:if test="epiOSViewOC!='' && epiOSViewOC!=null ">										
												<tr>
													<td> <s:text name="label.epiOurAssessmentOurShare" /> </td>
													<td align="right"> <s:property value="epiOSViewOC"/> <s:hidden name="epiOSViewOC" /> <s:hidden name="epiAsPerOffer" /> </td>
													<td align="right"> <s:property value="epiOSViewDC"/> <s:hidden name="epiOSViewDC" /> </td>
												</tr>
												</s:if>
												
												<%-- <tr>
													<td> <s:text name="label.xlCostOurShare" /> </td>
													<td align="right"> <s:property value="xlCostViewOC"/> <s:hidden name="xlcostOurShare" /> <s:hidden name="xlCostViewOC" /> </td>
													<td align="right"> <s:property value="xlCostViewDC"/> <s:hidden name="xlCostViewDC" /> </td>
												</tr>	--%>										
												</tbody>
											</table>
											<table width="100%" class="footable">
												<tbody>
												<tr>
												<s:if test='"1".equals(treatyType)  || "4".equals(treatyType) ||"5".equals(treatyType)'>
													<s:if test=' "4".equals(treatyType) ||"5".equals(treatyType)'>
														<td width="50%"><s:text name="label.premiumQuotaShare.obj" /></td>
													</s:if>
													<s:else>
														<td width="50%"><s:text name="label.premiumQuotaShare" /></td>
													</s:else>
													<td width="25%"> <s:textfield cssClass="inputBox" name="premiumQuotaShare" id="premiumQuotaShare" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="30" /> </td>
													<td width="25%"></td>
													
												</s:if>	
												<s:if test='"2".equals(treatyType) '>
													<td width="50%"><s:text name="label.premiumSurplus" /></td>
													<td width="25%"> <s:textfield cssClass="inputBox" name="premiumSurplus" cssStyle="text-align:right;" readonly="true" /> </td>
													<td width="25%"></td>
													
												</s:if>
												<s:if test='"3".equals(treatyType)  '>
													<td width="25%"><s:text name="label.premiumQuotaShare" /></td>
													<td width="25%"> <s:textfield cssClass="inputBox" name="premiumQuotaShare" cssStyle="text-align:right;" onblur="PremiumQuotaShare1()" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="30" /> </td>
													<td width="25%"><s:text name="label.premiumSurplus" /></td>
													<td width="25%"> <s:textfield cssClass="inputBox" name="premiumSurplus" cssStyle="text-align:right;" readonly="true" /> </td>
												</s:if>
												</tr>
												</tbody>
											</table>
											<s:hidden name="NoRetroCess" />
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="label.acquisitionCost" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<s:if test='"2".equals(inwardType)'>
										<div class="textfield">
												<div class="text">
													<s:text name="label.orginalacqcost" />
												</div>
												<div class="tbox">
													<%--<s:textfield name="orginalacqcost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this)"  maxlength="8" />--%>
													<s:radio list="#{'Y':'Yes','N':'No'}" name="orginalacqcost" value="orginalacqcost==null || orginalacqcost==''?'N':orginalacqcost" onchange="getOcqCostValue(this.value);CalculateValue();" ></s:radio>

												</div>
											</div>
											<div class="textfield" id="oac">
												<div class="text">
													<s:text name="label.ourassessmentorginalacqcost" />
												</div>
												<div class="tbox">
													<s:textfield name="ourassessmentorginalacqcost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="CalculationOurOcqCost();CalculateValue();" maxlength="8" />
												</div>
											</div>
											<div class="textfield" id="oc">
												<div class="text">
													<s:text name="label.oueassessmentorginalacqcost" />
												</div>
												<div class="tbox">
													<s:textfield name="ouracqCost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" onblur="CalculateValue()" maxlength="8" />
												</div>
											</div>
										
										</s:if>
										<div class="textfield">
										<div class="text">
											<s:text name="label.rate" />&nbsp; <sup style="color:red;">#</sup>
										</div>
										<div class="tbox">
											<s:select list="lOCIssuesedList" listKey="DETAIL_NAME" listValue="REMARKS" name="locRate" id="locRate" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
										</div>
										</div>
										<s:if test='"1".equals(treatyType) || "3".equals(treatyType) || "4".equals(treatyType) || "5".equals(treatyType)'>								
											<div class="textfield">
												<div class="text">
												<s:if test=' "4".equals(treatyType) ||"5".equals(treatyType)'>
													<s:text name="label.commissionQSP.obj" />
												</s:if>
												<s:else>
													<s:text name="label.commissionQSP" />
												</s:else>
												</div>
												<div class="tbox">
													<s:textfield name="commissionQ_S" id="commissionQ_S" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}' />
												</div>
											</div>
											</s:if>
											<s:if test='"2".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
												<div class="text">
													<s:text name="label.commissionSurpP" />
												</div>
												<div class="tbox">
													<s:textfield name="commission_surp" id="commission_surp" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											</s:if>
											<%--<s:if test='"1".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
												<div class="text">
													<s:text name="RiskDetails.CommisQ/SAmt" />
												</div>
												<div class="tbox">
													<s:textfield name="commissionQ_SAmt"  cssClass="inputBox" cssStyle="text-align:right;" tabindex="-1" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" readonly="true"/>
												</div>
											</div>
											</s:if>
											<s:if test='"2".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
												<div class="text">
													<s:text name="label.commissionSurpAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="commission_surpAmt"  cssClass="inputBox" cssStyle="text-align:right;" tabindex="-1" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" readonly="true"/>
												</div>
											</div></s:if>--%>
											<div class="textfield">
												<div class="text">
													<s:text name="label.overriderP" />
												</div>
												<div class="tbox">
													<s:textfield name="overRidder" id="overRidder" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.brokerageP" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="(broker.toString().trim()).equalsIgnoreCase('DIRECT')">
														<s:textfield name="brokerage" id="brokerageIDD" cssClass="inputBox" value="0" readonly="true" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="14" />
													</s:if>
													<s:else>
														<s:textfield name="brokerage" id="brokerageIDD" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="14" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.taxP" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="tax" id="taxIDDD" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" value='%{"D".equals(profit_Center) ?0.00:tax}' onblur="CalculateValue()" onchange="GetAcqCost()" maxlength="8" disabled='"Y".equals(disableStatus1) || "D".equals(profit_Center) '/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.otherCostP" />
												</div>
												<div class="tbox">
													<s:textfield name="othercost" id="CostOther" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<%--  <div class="textfield">
												<div class="text">
													<s:text name="label.otherAcqCostAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="acqCostPer" cssClass="inputBox" cssStyle="text-align:right;"  readonly="true"/>
												</div>
											</div>--%>
											<div class="textfield">
												<div class="text">
													<s:text name="label.acqCostAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="acquisition_Cost" id="acquisition_Cost" cssClass="inputBox" cssStyle="text-align:right;" readonly="true" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>	
							</div>									
							<s:if test='!"0".equals(no_Insurer)'> 
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading"> <s:text name="label.retroContracts" /> </div>
									<div class="panel-body">
										<div class="boxcontent">													
											<div class="textfield">
												 <div class="text"> <s:text name="label.retentionPer" /> &nbsp; <sup style="color:red;">#</sup></div> 
												<div class="tbox">
													<s:textfield name="RetentionPercentage" cssClass="inputBox" cssStyle="text-align: right;" value="%{RetentionPercentage ==null?'':RetentionPercentage}" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" /><%--disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) ?true:false}'|| "Y".equals(disableStatus1) --%>
												</div>
												
											</div>
																														
											<table width="100%" class="footable">
												<thead>
												<tr>
													<th width="5%"> <s:text name="label.sNo" /> &nbsp; <sup style="color:red;">#</sup></th> 
													<th width="31.66%"> <s:text name="label.uwYear" /> &nbsp; <sup style="color:red;">#</sup></th>
													<th width="31.66%"> <s:text name="label.retroContract" /> &nbsp; <sup style="color:red;">#</sup></th>
													<th width="31.66%"> <s:text name="label.retroP" /> &nbsp; <sup style="color:red;">#</sup></th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="retroFinalList" var="retroContract" status="stat">
												<tr>
													<td>
													<s:property value='%{#stat.count}'/>
													</td>
													<td>
														<s:select  list="retroUwyear" listKey="CONTDET1" listValue="CONTDET2" name="retroYear[%{#stat.count-1}]" id="retroYear[%{#stat.count-1}]" cssClass="inputBoxS" onchange="getRetroContract(this.value,'retroYear%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" /><%--disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) ?true:false}'|| "Y".equals(disableStatus1) --%>
													</td>
													<%--<td><s:hidden name="RetentionPercentage" value="0"/>		
													<s:if test='0==(#stat.count-1)'>
														<s:select  list="retroUwyear" listKey="CONTDET1" listValue="CONTDET2" name="retroYear[%{#stat.count-1}]" id="retroYear[%{#stat.count-1}]" cssClass="inputBoxS" onchange="getRetroContract(this.value,'retroYear%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" disabled='true'/>
													</s:if>
													<s:else>
														<s:select  list="retroUwyear" listKey="CONTDET1" listValue="CONTDET2" name="retroYear[%{#stat.count-1}]" id="retroYear[%{#stat.count-1}]" cssClass="inputBoxS" onchange="getRetroContract(this.value,'retroYear%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) || "Y".equals(disableStatus1)?true:false}'/>
													</s:else>
													</td>
													<s:if test='0==(#stat.count-1)'>
													<td id="retroYear<s:property value='%{#stat.count-1}'/>">
													     <s:select list="%{retroDupList[#stat.count-1]}" name="retroCeding[%{#stat.count-1}]" id="retroCeding[%{#stat.count-1}]" listKey="CONTDET1" listValue="CONTDET2"  cssClass="inputBoxS" disabled='true'/> 
													     
													</td>
													</s:if>
													<s:else>--%>
													<td id="retroYear<s:property value='%{#stat.count-1}'/>">
													     <s:select list="%{retroFinalList[#stat.count-1]}" name="retroCeding[%{#stat.count-1}]" id="retroCeding[%{#stat.count-1}]" listKey="CONTDET1" listValue="CONTDET2"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" /> <%--disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) ?true:false}'||"Y".equals(disableStatus1) --%>
													     
													</td>
													<%--</s:else>--%>
													<td>
														<s:textfield name="percentRetro[%{#stat.count-1}]" id="percentRetro[%{#stat.count-1}]" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8"  /><%--disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) ?true:false}'||"Y".equals(disableStatus1) --%>
													</td>
													</tr>
												</s:iterator>
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
										<div class="row">
											<div class="textfield33">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus" value="crestaStatus==null || crestaStatus==''?'N':crestaStatus" onchange="toggleCrestapopUp(this.value);" disabled='%{"Y".equals(disableStatus1)?true:false}'></s:radio>
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="cresta" style="display:none">
													<s:if test='"Y"==slideEnable || "Y".equals(disableStatus1)'>
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="CrestapopUp('<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
														<%-- <a href="#" onclick="CrestapopUp('<s:property value="proposal_no"/>')"><s:text name="label.enterDetails" /></a>--%>
													</s:if>
													<s:else>
														<a href="#" onclick="CrestapopUp('')"><s:text name="label.enterDetails" /></a>
													</s:else>
												</div>
											</div>
											<div class="textfield33"  >
											<s:if test="''.equals(baseLayer) ||null==baseLayer">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="crestacommissionSubClass" id="crestacommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="crestacommissionSubClass==null || crestacommissionSubClass==''?'1':crestacommissionSubClass" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
													</div>
											</s:if>
										</div>	
										<div class="textfield33">
												<div class="text">
													<s:text name="label.scaleCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="slideScaleCommission" list="#{'Y':'Yes','N':'No'}"  value="slideScaleCommission==null || slideScaleCommission==''?'N':slideScaleCommission" onclick="funView(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="ssc" style="display:none">
												<s:if test='"Y"==slideEnable || "Y".equals(disableStatus1)'>
													<%-- <a href="#" onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a>--%>
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
												</s:if>
												<s:else>
													<a href="#" onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a>
												</s:else>
												</div>
											</div>	
											<div class="textfield33"  >
											<s:if test="''.equals(baseLayer) ||null==baseLayer">
												<div class="text">
													<s:text name="label.commsubclass" />
												</div>
												<div class="tbox">													
													<s:radio name="slidecommissionSubClass" id="slidecommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="slidecommissionSubClass==null || slidecommissionSubClass==''?'1':slidecommissionSubClass" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</s:if>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.lossporticipents" />
												</div>
												<div class="tbox">													
													<s:radio name="lossParticipants" list="#{'Y':'Yes','N':'No'}"  value="lossParticipants==null || lossParticipants==''?'N':lossParticipants" onclick="funLPView(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>
											<div class="textfield33" >
												<div class="text" id="lp" style="display:none">
												<s:if test='"Y"==lossParEnable || "Y".equals(disableStatus1)'>
													<a href="#" class="btn btn-info btn-rounded btn-xs"  onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
													<%-- <a href="#" onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a>--%>
												</s:if>
												<s:else>
													<a href="#" onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a>
												</s:else>
												</div>
											</div>	
											<div class="textfield33"  >	
											<s:if test="''.equals(baseLayer) ||null==baseLayer">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="losscommissionSubClass" id="losscommissionSubClass" list="#{'1':'Yes','2':'No'}"   value="losscommissionSubClass==null || losscommissionSubClass==''?'1':losscommissionSubClass" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
													</div>
											</s:if>
											</div>	
											<div class="textfield33">
												<div class="text">
													<s:text name="label.profitCommission" />
												</div>
												<div class="tbox">													
													<s:radio name="share_Profit_Commission" list="#{'1':'Yes','2':'No'}" value="share_Profit_Commission==null ||share_Profit_Commission==''?'2':share_Profit_Commission" onclick="ShareProfitCommission(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
												</div>
											</div>
											<div class="textfield33"></div>
											<div class="textfield33"  >
											<s:if test="''.equals(baseLayer) ||null==baseLayer">
													<div class="text">
														<s:text name="label.commsubclass" />
													</div>
													<div class="tbox">													
														<s:radio name="commissionSubClass" id="commissionSubClass" list="#{'1':'Yes','2':'No'}"   value="commissionSubClass==null || commissionSubClass==''?'1':commissionSubClass" disabled='%{"Y".equals(disableStatus1)?true:false}'/>													
													</div>
											</s:if>
											</div>				
											<br class="clear">
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" id="pc" style="display:none">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">																						
											<div class="textfield">
												<div class="text">
													<s:text name="label.manexp" />
												</div>
												<div class="tbox">												
												<s:textfield name="managementExpenses" id="managementExpenses"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestriction(this);" maxlength="26" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>	<br/>											
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.comtype" /> 
												</div>
												<div class="tbox">												
												<s:select  list="commissionTypeList"  name="commissionType" id="commissionType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getCommissionField(this.value);getpopupenable()"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>
												</div>
											</div>
											<div class="textfield" id="supcom" style="display:none;">
												<div class="text">
													<s:text name="label.supprocom" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="superProfitCommission" id="superProfitCommission" value="(superProfitCommission==null || superProfitCommission=='') ?'N':superProfitCommission"  onchange="getpopupenable()"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio>
														<div  id="compoptype" style="display:none;">
															<s:if test='"Y"==profitCommissionEnable || "Y".equals(disableStatus1)'>
																&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
																<%-- <a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a>--%><br/>	
															</s:if>
															<s:else>
																&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a><br/>	
															</s:else>
														</div>		
												</div>
											</div>
											
											<div class="textfield" id="comper" style="display:none;">
												<div class="text">
													<s:text name="label.procomper" /> 
												</div>
												<div class="tbox">												
													<s:textfield name="profitCommissionPer" id="profitCommissionPer"  cssClass="inputBox" onkeyup="checkDecimals(this);" cssStyle="text-align: right;" maxlength="26" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>		<br/>										
											</div>
											</div>
											<div class="textfield" id="setup" style="display:none;">
												<div class="text">
													<s:text name="label.stepup" />
												</div>
												<div class="tbox">												
													<s:radio list="#{'Y':'Yes','N':'No'}" name="setup" id="setup" value="(setup==null || setup=='')?'Y':setup"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio>
															<br/>										
												</div>
											</div>
											<div class="textfield" id="ratioPopUp" style="display:none;">
												<s:if test='"Y"==profitCommissionEnable || "Y".equals(disableStatus1)'>
													<a href="#" class="btn btn-info btn-rounded btn-xs" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.viewDetails" /></a>
													<%-- <a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a><br/>	--%>
												</s:if>
												<s:else>
													<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a><br/>	
												</s:else>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.lossCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="lossCarried" id="lossCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getLossYear(this.value)"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div id="lossyear" style="display:none;">
													<div class="text">
														<s:text name="label.lossperidyear" /> 
													</div>
													<div class="tbox">
														<s:textfield name="lossyear" id="lossyear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>		<br/>										
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.profitCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="profitCarried" id="profitCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getProfitYear(this.value)"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div id="profityear" style="display:none">
													<div class="text">
														<s:text name="label.profitCarFordyear" /> 
													</div>
													<div class="tbox">												
															<s:textfield name="profitCarriedForYear" id="profitCarriedForYear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.firstprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.subprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false' /><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.subseqcal" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio><br/>	
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
														<s:textarea rows="3" name="profitCommission" id="profitCommission" cssClass="inputBoxA" cssStyle="width: 90%;" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
													<br/>
												</div>
											</div>
											<br class="clear"/>
											</div>	
																				
												<br class="clear"/>
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
													<s:text name="label.premiumReserveP" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="premium_Reserve" cssClass="inputBox" cssStyle="text-align:right;" onchange="GetPremiumReserveIntr()" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.lossReserveP" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="loss_reserve" cssClass="inputBox" cssStyle="text-align:right;" onchange="GetPremiumReserveIntr()" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.interestP" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="interest" id="interset" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.portfolioinoutLossP" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="portfolio_inout_Loss" id="InoutLoss" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.portfolioinoutPremiumP" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="portfolio_inout_Premium" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onblur="CalculateValue()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
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
													<s:text name="label.lossAdvicePOC" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="loss_Advise" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cashLossPOC" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="cash_Loss_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.eventLimit" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="event_limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.aggregateLimit" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="aggregate_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.OccurrentLimit" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="occurrent_Limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="30" />
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
													<s:text name="label.leadUnderWriter" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
														<s:select list="underwriterList" listKey="CUSTOMER_ID" cssClass="inputBoxS"  listValue="NAME" name="leader_Underwriter" id="leader_Underwriter"   headerKey="" headerValue="---Select---"   onchange="getCountry(this.value,'country','');getUnderwriterShare(this.value);"/>
												</div>
											</div>
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
											 <div class="textfield" >
												<div class="text">
													<s:text name="label.leadUnderwritterCountry" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="country">
													
													<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  />													
												</div>
											</div>
											</s:if>
											<s:else>
											<div class="tbox" id="country">
												<s:hidden  name="leader_Underwriter_country" id="leader_Underwriter_country"/>
											</div>	
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderwritterShareP" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="false"/>
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
												<s:text name="label.exclusion" />
											</div>
											<div class="textfieldA75">
												<s:textarea name="exclusion" id="exclusion" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
												<br/>
												<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
											</div>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="label.underwritersRecommendations" />&nbsp; <sup style="color:red;">#</sup>
											</div>
											<div class="textfieldA75">
												<s:textarea cssClass="inputBoxA" id="underwriter_Recommendations" name="underwriter_Recommendations" rows="3" cssStyle="width: 100%;" />
												<br/>
												<span class="textAreaRemaining"><label id="underwriter_Recommendations_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
											</div>
											<br class="clear"/>
										</div>
										<br/>
									</div>
								</div>
							</div>
							<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
							<s:if test="amend_Id_Mode != '' && amend_Id_Mode != null">
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
												
												<%-- <div class="textfield">
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
													<s:textfield name="endorsementDate" id="endorsementDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="functionDate()" disabled="%{prclFlag==true?true:false}"  />
												</div>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.docDetails" />
												</div>
												<div class="tbox">
														<s:radio name="docStatus" id="docStatus" list="#{'Y':'Yes','N':'No'}" value="(docStatus==null || docStatus=='')?'N':docStatus"  onclick="DocScript(this.value)"/>
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
																<s:iterator value="docuList" id="index">
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
																		<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" cssClass="inputBox"/>												 
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
								<input type="button" value="Previous" class="btn btn-sm btn-warning" onclick="FunctionBackMode()">
								<s:if test="amend_Id_Mode == '' || amend_Id_Mode == null">
									<input type="button" class="btn btn-sm btn-danger" onclick="return CancelPage('%{proStatus}')" value="Cancel">									
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');SavePage()" value="Save">
									<s:if test='"A".equals(proStatus)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');SubmitPage()">
									</s:if>
								</s:if>
								<s:else>
									<input type="button" class="btn btn-sm btn-danger" onclick="AmendIdBack()" value="Cancel">
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');return oncheck();">
								</s:else>
							</div>
						</div>
						<s:hidden name="underwriter"/>
						<s:hidden name="proposalno" value="%{proposal_no}" id="proposalno"/>
						<s:hidden name="baseLayer" value="%{baseLayer}" id="baseLayer"/>
						<s:hidden name="layerProposalNo" value="%{layerProposalNo}" id="layerProposalNo"/>
						<s:hidden name="contractNo" value="%{contNo}" />
						<s:hidden name="contNo" id="contNo"/>
						<s:hidden name="contractno" />						
						<s:hidden name="proposalType" />
						<s:hidden name="broker" />
						<s:hidden name="gms_Approval" />
						<s:hidden name="layerNo" />
						<s:hidden name="incepDate" />
						<s:hidden name="expDate" />
						<s:hidden name="limitOrigCur" />
						<s:hidden name="treatyLimitsurplusOC" />
						<s:hidden name="proposal_no" id="proposal_no"/>
						<s:hidden name="amend_Id_Mode" />
						<s:hidden name="renewal_contract_no" />
						<s:hidden name="renewalFlag" />
						<s:hidden name="treatyType" id="treatyType"/>
						<s:hidden name="inwardType" id="inwardType"/>
						<s:hidden name="product_id"/>
						<s:hidden name="productCode"/>
						<s:hidden name="instlmentperod" value="line" />
						<s:hidden name="lay" />
						<s:hidden name="baseLoginID" />
						<s:hidden name="amendId" id="amendId" />
						<s:hidden name="no_Insurer" />
						<s:hidden name="m_d_InstalmentNumber" />
						<s:hidden name="retroType" />
						<s:hidden name="mode" />
						<s:hidden name="accounts"/>
						<s:hidden name="orginalCurrency" id="orginalCurrency"/>
						<s:hidden name="pml" id="pml"/>
						<s:hidden name="shareValue" id="shareValue"/>
						<s:hidden name="proposalReference" id="proposalReference" />	
						<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>	
						<s:hidden name="proposalNo" id="proposalNo"/>
						<s:hidden name="proStatus" id="proStatus"/>
						<s:hidden name="profit_Center" id="profit_Center"/>
						<s:hidden name="renewalEditMode" id="renewalEditMode"/>
						<s:hidden name="crestaPopUp" id="crestaPopUp"/>
						<s:hidden name="slidePopUp" id="slidePopUp"/>
						<s:hidden name="lossPopUp" id="lossPopUp"/>
						<s:hidden name="profitPopUp" id="profitPopUp"/>
						<s:hidden name="flag" id="flag"/>
						<s:hidden name="retroDupContract" id="retroDupContract"/>
						<s:hidden name="retroDupYerar" id="retroDupYerar"/>
						<s:hidden name="exchRate" id="exchRate"/>
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

function getRetroContract(uwYear,id,count)
	{
	var inceptionDate='<s:property value="incepDate" />';
	var product='<s:property value="product_id"/>';
	var id1=id;
	var mode='';
	//if(0==count){
	//mode ='Dup';
	//}
	if(uwYear!='0')
	{
		var URL='<%=request.getContextPath()%>/getRetroContractDetailsRiskDetails.action?inceptionDate='+inceptionDate+'&productCode='+product+'&uwYear='+uwYear+'&dropDown=retroCeding&retroListName='+count+'&mode='+mode;
  	 	postRequest(URL,id);
	}
}

function SubmitPage()
{
destroyPopUps();
var base= document.getElementById("baseLayer").value;
if(base==null || base==""){
answer=confirm("All the changes made in # marked fields will be updated in all class linked to this base proposal.Do you wish to continue.");
if(answer){
	replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
	document.proportional2.action="RiskSecondPageRiskDetails.action";
	document.proportional2.submit();
	}else
     	return false;
	
}else{
	replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
	document.proportional2.action="RiskSecondPageRiskDetails.action";
	document.proportional2.submit();
}
	
}
        
function Editmode()
{
	document.proportional2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=SecondPageUpdate';
	document.proportional2.submit();
}
	
function EndorsementScript(value) {
		if(value=="Y") {
			document.getElementById('endtlabel').style.display='block';		
			document.getElementById('doc1').style.display='block';
            document.proportional2.docStatus.value= 'N';
		}
		else if(value=="N") {
			document.getElementById('endtlabel').style.display='none';
			document.getElementById('doc1').style.display='none';
            document.getElementById('docView').style.display='none';

        }
	}
function SavePage() {
destroyPopUps();
var base= document.getElementById("baseLayer").value;
if(base==null || base==""){
answer=confirm("All the changes made in # marked fields will be updated in all class linked to this base proposal.Do you wish to continue.");
if(answer){
	replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
	document.proportional2.action="SecondPageSaveRiskDetails.action";
	document.proportional2.submit();
	}else
     	return false;
	
}else{
replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
document.proportional2.action="SecondPageSaveRiskDetails.action";
document.proportional2.submit();
}
}	    
	    
function AmednIdSubmitPage() {
	document.proportional2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdInsertSecondPage';
	document.proportional2.submit();
}
	    
function AmednIdSavePage() {
	document.proportional2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdSavePage';
	document.proportional2.submit();
}
function CalculateValue() {
var ouracqCost =0;
var commissionQ_S =  0;
var commission_surp =  0;
var premiumQS=0;
var premiumSurplus=0;
var epios = 0;
if('2'=='<s:property value="inwardType"/>'){
	 ouracqCost = document.proportional2.ouracqCost.value;
	if(ouracqCost ==  null || ouracqCost ==""){
	ouracqCost =  0;
	}
	}
	if('1'=='<s:property value="treatyType"/>' || '3'=='<s:property value="treatyType"/>' ||'4'=='<s:property value="treatyType"/>'){
	 commissionQ_S = document.proportional2.commissionQ_S.value;
	if(commissionQ_S ==  null || commissionQ_S ==""){
	commissionQ_S =  0;
	}
	premiumQS = document.proportional2.premiumQuotaShare.value;
	if(premiumQS ==  null || premiumQS ==""){
	premiumQS =  0;
	}
	}
	
	if('2'=='<s:property value="treatyType"/>'){  
	 commission_surp = document.proportional2.commission_surp.value;
	if(commission_surp ==  null || commission_surp ==""){
	commission_surp =  0;
	}
	premiumSurplus = document.proportional2.premiumSurplus.value;
	if(premiumSurplus ==  null || premiumSurplus ==""){
	premiumSurplus =  0;
	}
	}
	
	if('3'=='<s:property value="treatyType"/>'){ 
	 commissionQ_S = document.proportional2.commissionQ_S.value;
	if(commissionQ_S ==  null || commissionQ_S ==""){
	commissionQ_S =  0;
	}
	 commission_surp = document.proportional2.commission_surp.value;
	if(commission_surp ==  null || commission_surp ==""){
	commission_surp =  0;
	}
	premiumQS = document.proportional2.premiumQuotaShare.value;
	if(premiumQS ==  null || premiumQS ==""){
	premiumQS =  0;
	}
	premiumSurplus = document.proportional2.premiumSurplus.value;
	if(premiumSurplus ==  null || premiumSurplus ==""){
	premiumSurplus =  0;
	}
	}
	var overRidder = document.proportional2.overRidder.value;
	if(overRidder ==  null || overRidder ==""){
	overRidder =  0;
	}
	var brokerage = document.proportional2.brokerage.value;
	if(brokerage ==  null || brokerage ==""){
	brokerage =  0;
	}
	var tax = document.proportional2.tax.value;
	if(tax ==  null || tax ==""){
	tax =  0;
	}
	var othercost = document.proportional2.othercost.value;
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
	//document.proportional2.commissionQ_SAmt.value=Comma(QSAmt.toFixed(2));
	}
	if(commission_surp!=0){
	var SurpAmt=(parseFloat(commission_surp)*parseFloat(premiumSurplus))/100;
	
	//document.proportional2.commission_surpAmt.value=Comma(SurpAmt.toFixed(2));
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
	if('3'=='<s:property value="treatyType"/>'){ 
	 com = parseFloat(premiumQS)*parseFloat(share)/100*parseFloat(commissionQ_S)/100;
	 sur = parseFloat(premiumSurplus)*parseFloat(share)/100*parseFloat(commission_surp)/100;
	 total = parseFloat(epios)*((parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
	
	}
	if('1'=='<s:property value="treatyType"/>' || '5'=='<s:property value="treatyType"/>' ||'4'=='<s:property value="treatyType"/>'){
	 total = parseFloat(epios)*((parseFloat(commissionQ_S)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
	}
	else if('2'=='<s:property value="treatyType"/>'){  
	 total = parseFloat(epios)*((parseFloat(commission_surp)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
	}
	 val =  parseFloat(ouracqCost) +  com +sur+ total ;
	//var sum = parseFloat(premiumQS) + parseFloat(premiumSurplus);
	
	//var val= parseFloat(ouracqCost) + ((parseFloat(epiOSOEViewOC) *(parseFloat(tot)))/100)
	document.proportional2.acquisition_Cost.value=Comma(val.toFixed(2));
	//document.proportional2.acqCostPer.value=Comma(total.toFixed(2));
	//document.getElementById("acquisition_Cost").value=Comma(val.toFixed(2));
}

function Commas(value) {
	if(value=="2") {  
		if('1'=='<s:property value="treatyType"/>' || '5'=='<s:property value="treatyType"/>' ||'4'=='<s:property value="treatyType"/>'){ 
			document.proportional2.premiumQuotaShare.value=Comma(document.proportional2.premiumQuotaShare.value);
			//document.proportional2.commissionQ_SAmt.value=Comma(document.proportional2.commissionQ_SAmt.value);
			
		}
		else if('2'=='<s:property value="treatyType"/>'){
			document.proportional2.premiumSurplus.value=Comma(document.proportional2.premiumSurplus.value);
			//document.proportional2.commission_surpAmt.value=Comma(document.proportional2.commission_surpAmt.value);
		}
		else if('3'=='<s:property value="treatyType"/>'){
			document.proportional2.premiumQuotaShare.value=Comma(document.proportional2.premiumQuotaShare.value);
			document.proportional2.premiumSurplus.value=Comma(document.proportional2.premiumSurplus.value);
			//document.proportional2.commissionQ_SAmt.value=Comma(document.proportional2.commissionQ_SAmt.value);
			//document.proportional2.commission_surpAmt.value=Comma(document.proportional2.commission_surpAmt.value);
		}
		
		
		//document.proportional2.acqCostPer.value=Comma(document.proportional2.acqCostPer.value);
		document.proportional2.acquisition_Cost.value=Comma(document.proportional2.acquisition_Cost.value);
		document.proportional2.loss_Advise.value=Comma(document.proportional2.loss_Advise.value);
		document.proportional2.cash_Loss_Limit.value=Comma(document.proportional2.cash_Loss_Limit.value);
		document.proportional2.event_limit.value=Comma(document.proportional2.event_limit.value);
		document.proportional2.aggregate_Limit.value=Comma(document.proportional2.aggregate_Limit.value);
		document.proportional2.occurrent_Limit.value=Comma(document.proportional2.occurrent_Limit.value);
	}
	}
function CancelPage(flag) {
destroyPopUps();
	//var param='';
	//if(flag=="N")
	//{
	//	param='&flag=N'
	//}
	//var proposal_no = document.proportional2.proposal_no.value
	//document.location='${pageContext.request.contextPath}/commonListPortfolio.action?proposalNo='+proposal_no+'&manufactureID=<%=session.getAttribute("mfrid")%>+param';
	var val = document.getElementById("proposalReference").value;
	var no =document.getElementById("renewalProposalNo").value;
	var out =document.getElementById("proposal_no").value;
	if('Renewal'==val || 'Layer'==val){
		answer = confirm("This action will remove the newly created proposal number "+out+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("renewalProposalNo").value =out;
		document.getElementById("proposalNo").value = no;
			document.proportional2.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.proportional2.submit();
			}
			else {
				 return false; 
			}
		}
		else{
			document.proportional2.action='${pageContext.request.contextPath}/menuPortfolio?proposalNo='+out;
			document.proportional2.submit();
		}	
}

function FunctionBackMode() {
	destroyPopUps();
	document.proportional2.action="EditModeRiskDetails?multiuserMode=edit";
	document.proportional2.submit();
}
ShareProfitCommission('<s:property value="share_Profit_Commission"/>');
function ShareProfitCommission(value) {
	if(value=='1'){
	document.getElementById('pc').style.display='block';
	}else if(value=='2'){
	document.getElementById('pc').style.display='none';
	}
}

function cleanCutPopUp() {
	if(document.proportional2.proposalType.value=="R")
	{
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Loss %?","Yes","No");
		if(doIt) {
			document.proportional2.portfolio_inout_Loss.readOnly=false;
			document.proportional2.portfolio_inout_Loss.focus();
		} else {
			document.proportional2.portfolio_inout_Loss.value='0';
  			document.proportional2.portfolio_inout_Loss.readOnly=true;
  			//cleanCutPopUp1();
		}
	}
}

function cleanCutPopUp1() {
	if(document.proportional2.proposalType.value=="R") { 
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Premium %?","Yes","No");
		if(doIt) {
			document.proportional2.portfolio_inout_Premium.readOnly=false;
			document.proportional2.portfolio_inout_Premium.focus();
		} else {
			document.proportional2.portfolio_inout_Premium.value='0';
			document.proportional2.portfolio_inout_Premium.readOnly=true;
			document.proportional2.loss_Advise.focus();
		}
	}
}
//var st='<s:property value="crestaStatus"/>';
//if(st!=null && st!='' ){
toggleCrestapopUp('<s:property value="crestaStatus"/>');
//}
function toggleCrestapopUp(value){
if(value=='Y'){
document.getElementById('cresta').style.display='block';
}else if(value=='N'){
document.getElementById('cresta').style.display='none';
}//else{
//document.getElementById('cresta').style.display='block';
//}

}

function CrestapopUp(layerProposalNo){
	var contractNo = document.getElementById("contNo").value;
	var proposalno = document.getElementById("proposalno").value;
	var amendId = document.getElementById("amendId").value;
	var disableStatus1 = '<s:property value="disableStatus1"/>';
	 if("Y"==disableStatus1 && ""==layerProposalNo){
		 layerProposalNo =proposalno;
	 }
	var cur = document.getElementById("orginalCurrency").value;
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

function PremiumQuotaShare1() {
	var share="";
	var Epi="";
	var val="";
	var pre=""
	if('1'=='<s:property value="treatyType"/>' || '5'=='<s:property value="treatyType"/>' ||'4'=='<s:property value="treatyType"/>'){
		Epi=document.proportional2.epiOSViewOC.value;
		document.proportional2.premiumQuotaShare.value=Epi;
	}
	else if('2'=='<s:property value="treatyType"/>'){
	 Epi=document.proportional2.epiOSViewOC.value;
	 document.proportional2.premiumSurplus.value=Epi;
	}
	else if('3'=='<s:property value="treatyType"/>'){
		Epi=document.proportional2.epiOSViewOC.value;
	 	pre = document.proportional2.premiumQuotaShare.value;
	 	Epi=Epi.replace(new RegExp(',', 'g'),'');
	 	pre=pre.replace(new RegExp(',', 'g'),'');
	 	if(pre == null || pre==""){
	 	pre = 0;
	 	}
	 	if(parseFloat(Epi)<parseFloat(pre)){
	 	alert("Please Enter Premium QS Our Share OC Less than EPI (Our Assessment) - Our Share");
	 	pre = '';
	 	}
	 	else{
	 	Epi = parseFloat(Epi) - parseFloat(pre);
	 	}
		document.proportional2.premiumSurplus.value=Comma(Epi.toFixed(2));
		document.proportional2.premiumQuotaShare.value = Comma(pre.toFixed(2));
	}
	CalculateValue();
}

function PremiumQuotaShare() {
	var share=document.proportional2.premiumQuotaShare.value;
	var surplus=document.proportional2.premiumSurplus.value;
	var Epi=document.proportional2.epiAsPerShare.value;
	if(Epi==share) {
		document.proportional2.premiumSurplus.readOnly=true;
		document.proportional2.commission_surp.readOnly=true;
		document.proportional2.premiumSurplus.value='0'
		document.proportional2.commission_surp.value='0'
		document.proportional2.commissionQ_S.readOnly=false;
		document.proportional2.premiumQuotaShare.readOnly=false;
	} else if(Epi==surplus) {
		document.proportional2.premiumSurplus.readOnly=false;
		document.proportional2.commission_surp.readOnly=false;
		document.proportional2.commissionQ_S.value='0'
		document.proportional2.premiumQuotaShare.value='0'
		document.proportional2.commissionQ_S.readOnly=true;
//		document.proportional2.premiumQuotaShare.readOnly=true;
	} else {
		document.proportional2.premiumSurplus.readOnly=false;
		document.proportional2.commission_surp.readOnly=false;
		document.proportional2.commissionQ_S.readOnly=false;
		document.proportional2.premiumQuotaShare.readOnly=false;
	}
	
	if(surplus!="" &&  share!="" ) {
		var c=parseFloat(share)+parseFloat(surplus);
		if(c > parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.proportional2.premiumQuotaShare.value='';
			document.proportional2.premiumSurplus.value='';
		}
		else if(c < parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.proportional2.premiumQuotaShare.value='';
			document.proportional2.premiumSurplus.value='';
		}
		else {
			document.getElementById("error").style.display='none';
			
		}
	}
}

function GetPremiumReserveIntr() {
	if(document.proportional2.loss_reserve.value=='0'  && document.proportional2.premium_Reserve.value=='0') {
		document.proportional2.interest.value='0';
		document.getElementById("interset").readOnly=true;
	}
	else {
		document.proportional2.interest.value="";
		document.getElementById("interset").readOnly=false;
	}
}

<%--function GetReinstatmentPremium(value) {
	var a=document.proportional2.epiAsPerOffer.value;
	value=value.replace(new RegExp(',', 'g'),'');
	if(a!=null && a!="" && value!=null && value!="" && value!=0) {  
		var c=(a*value)/100;
		document.proportional2.reinstAdditionalPremium.value=Comma(c.toFixed(2));
	}
	else {
		document.proportional2.reinstAdditionalPremium.value='0.00'
	}
}--%>

function GetAcqCost() {
	<%--var md=document.proportional2.md_premium_our_service.value;
	var brok=document.getElementById("brokerageIDD").value;
	var tax=document.getElementById("taxIDDD").value;
	var CostOther=document.getElementById("CostOther").value;
	if( document.getElementById("brokerageIDD").value!="" && document.getElementById("taxIDDD").value !=""  && document.getElementById("CostOther").value != "") {
		var c=parseFloat(md)*((parseFloat(brok)+parseFloat(tax)+parseFloat(CostOther))/100);
		document.proportional2.acquisition_Cost.value=Comma(c.toFixed(2));
	}
	else {
		document.proportional2.acquisition_Cost.value=''
	}--%>
}


function endorsementPage() {
if(document.getElementById("ceaseStatusY").checked  ){
//&& document.getElementById("endorsementStatusY").checked
        	answer=confirm("You have choosen cease account for this treaty.This action will restrict change Premium and Claim form this treaty.Do you wish to continue?");
     	if(answer){
     	replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
     	document.proportional2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementRiskDetails.action";
		document.proportional2.submit();
	}
     	else
     	return false;
     	}
    else {
    replaceComma(document.proportional2,'premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
    document.proportional2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementRiskDetails.action";
	document.proportional2.submit();
    }
}
function oncheck(){
     <%-- if(document.getElementById("endorsementStatusN").checked){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
     	if(answer)
     	endorsementPage();
     	else
     	return false;
     	}
    else--%> 
    destroyPopUps();
    document.getElementById("endorsementStatus").value='Y';
    endorsementPage();
}


	$(function() {
	
		$('#exclusion').keyup(function() {
	        update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
	    });
	    update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
	
    	//set up text length counter
	   // $('#remarks').keyup(function() {
	       // update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    //});
	    //and fire it on doc ready, too
	    //update_chars_left(500, $('#remarks')[0], $('#remarks_left'));exclusion
	    
	    $('#underwriter_Recommendations').keyup(function() {
	        update_chars_left(500, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
	    });
	    update_chars_left(500, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
	    
	   
	    
	    $('#profitCommission').keyup(function() {
	        update_chars_left(500, $('#profitCommission')[0], $('#profitCommission_left'));
	    });
	    update_chars_left(500, $('#profitCommission')[0], $('#profitCommission_left'));
	    
	   
	    
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
	
	
<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)' >
	enableForm1(document.proportional2,true,'<s:property value="%{fields}"/>');
</s:if>


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
	
	function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	 var type = document.getElementById("commissionType").value;
	 var disableStatus1 = '<s:property value="disableStatus1"/>';
	 var treatyType= <s:property value="treatyType"/>;
	 if("Y"==disableStatus1 && ""==renewalProposalNo){
		 renewalProposalNo =proposalNo;
	 }
	 if("scale"==pageFor){
	  document.getElementById("slidePopUp").value = "Y";
	 }else if("lossparticipates"==pageFor){
	  document.getElementById("lossPopUp").value = "Y";
	 }else if("profitCommission"==pageFor){
	  document.getElementById("profitPopUp").value = "Y";
	 }
	
	var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType;
	//var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&layerProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType;
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
			if(id!="TE"){
	     		document.getElementById('lossyear').style.display = 'block';
	       		} 
	       	else{
				document.getElementById('lossyear').style.display = 'none';
	     		  }
	}
	getProfitYear('<s:property value="profitCarried"/>');
	function getProfitYear(id){
			if(id!="TE"){
	     		document.getElementById('profityear').style.display = 'block';
	       		} 
	       	else{
				document.getElementById('profityear').style.display = 'none';
	     		  }
	}
	getpopupenable();
	function getpopupenable(){
		var id = document.getElementById('commissionType').value;
		var slide ="";
		if(id=="PC"){
		 slide = document.proportional2.superProfitCommission.value;
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
	
	getOcqCostValue('<s:property value="orginalacqcost"/>');
	function getOcqCostValue(id){
	    var type ='<s:property value="inwardType"/>';
	    if(type=='2'){
			if("Y"==id){
				document.getElementById('oac').style.display = 'block';
				document.getElementById('oc').style.display = 'block';
			}
			else {
				document.getElementById('oac').style.display = 'none';
				document.getElementById('oc').style.display = 'none';
				document.proportional2.ourassessmentorginalacqcost.value = '';
				document.proportional2.ouracqCost.value = '';
			}
		}
	}
	
		
DocScript('<s:property value="docStatus"/>');
function DocScript(id){
    var amendId = document.getElementById("amendId").value;
    if (amendId > 0) {
        document.proportional2.docStatus.value = id;
        if ("Y" == id) {
            document.getElementById('docView').style.display = 'block';
        }
        else {
            document.getElementById('docView').style.display = 'none';
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
 function CalculationOurOcqCost(){
 var epios =0;
 var ourass = proportional2.ourassessmentorginalacqcost.value;
 if(ourass==null||ourass==''){
 ourass =0;
 }
 <%--if(''!='<s:property value="epiAsPerShare"/>' && '0'!='<s:property value="epiAsPerShare"/>'){
	 epios = '<s:property value="epiAsPerShare"/>';
	}
	else--%> if(''!='<s:property value="epiAsPerOffer"/>'){
	 epios = '<s:property value="epiAsPerOffer"/>';
	}
	var val = parseInt(epios)*parseInt(ourass)/100;
	//var val = parseInt(epios)*parseInt(ourass);
	proportional2.ouracqCost.value = val;
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
function AmendIdBack(){
destroyPopUps();
	var no = document.getElementById("proposal_no").value;
	//document.getElementById("endorsementStatusY").disabled = true;
	//document.getElementById("endorsementStatusN").disabled = true;
	document.getElementById("endorsementStatus").value='N';
	document.proportional2.action ="${pageContext.request.contextPath}/InitPortfolio.do?proposalNo="+no+"&endtMode=endorsment&endorsementStatus=N";
	document.proportional2.submit();
}
			
function deleteUpload(id){
if(confirm('This row will be deleted. Do You Want to continue?')){
	postFormRequest('${pageContext.request.contextPath}/newDocDeleteRiskDetails.action?mode=delete&deleteId='+id+'&dropDown=docView', "docView", "proportional2");
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
