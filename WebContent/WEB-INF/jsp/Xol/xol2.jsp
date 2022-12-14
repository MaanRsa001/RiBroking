<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		$( ".instalmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });
	  </script>	
</head>

<body onload="Commas(<s:property value="#session.mfrid" />)">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="xol2" name="xol2" theme="simple" action=""	method="post" enctype="multipart/form-data" autocomplete="off">
					<s:set name="line" value="request.getAttribute('InstalMentNumber')==null||''.equalsIgnoreCase(request.getAttribute('InstalMentNumber').toString())?0:Integer.parseInt(request.getAttribute('InstalMentNumber').toString())" />
					<s:set name="NoRetroCess" value="request.getAttribute('NoRetroCess')==null||''.equalsIgnoreCase(request.getAttribute('NoRetroCess').toString())?0:Integer.parseInt(request.getAttribute('NoRetroCess').toString())" />
					<s:set name="noIns" value="request.getAttribute('NoInsurar')==null||''.equalsIgnoreCase(request.getAttribute('NoInsurar').toString())?0:Integer.parseInt(request.getAttribute('NoInsurar').toString())" />
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<s:if test="contNo != '' && contNo != null && proposal_no != null && proposal_no != '' && amend_Id_Mode != '' && amend_Id_Mode != null && (proposalReference=='' || proposalReference ==null)">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<s:if test="endorsmentno != '' &&  endorsmentno !='0'">
												<div class="textfield">
													<div class="text">
														<s:text name="label.endorsementType" />
													</div>
													<div class="tbox txtB">
														<s:property value="endorsmenttype"/>
														<s:hidden name="endttypename" id="endttypename"/>													</div>
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
											<table width="100%" class="table table-bordered">
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.proposalNo" />
												</td>
												<td width="16.66%">
													<s:property value="proposal_no"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.mainclass" />
												</td>
												<td width="16.66%">
													<s:property value="departClass"/>
												</td>
												 <td width="16.66%" class="txtB">
													<s:text name="label.cedingCompany" />
												</td>
												<td width="16.66%">
													<s:property value="cedingCo"/>
												</td>
											</tr>
											<tr>
												
												<td width="16.66%" class="txtB">
													<s:text name="label.broker" />
												</td>
												<td width="16.66%">
													<s:property value="broker"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.layerNumber" />
												</td>
												<td width="16.66%">
													<s:property value="layerNo"/>
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
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.ourCurrency" /> </th>
													<th width="25%"> <s:property value="shortname" /></th>
												</tr>
												</thead>
												<tbody>												
												<tr>
													<td> <s:text name="label.coverLimitOurShareMain" /> </td>
													<td align="right"> <s:property value="limitOSViewOC"/> <s:hidden name="limitOSViewOC" />  <s:hidden name="limitOurShare" /> </td>
													<td align="right"> <s:property value="limitOSViewDC"/> <s:hidden name="limitOSViewDC" /> </td>
												</tr>												
												<tr>
													<td> <s:text name="label.epiOurShare1" /> </td>
													<td align="right"> <s:property value="epiOSViewOC"/> <s:hidden name="epiOSViewOC" /> <s:hidden name="epiAsPerOffer" /> </td>
													<td align="right"> <s:property value="epiOSViewDC"/> <s:hidden name="epiOSViewDC" /> </td>
												</tr>
												<tr>
													<td> <s:text name="label.MDPremiumOurShare" /> </td>
													<td align="right"> <s:property value="mandDpreViewOC"/> <s:hidden name="mandDpreViewOC" /> <s:hidden name="md_premium_our_service" /> </td>
													<td align="right"> <s:property value="mandDpreViewDC"/> <s:hidden name="mandDpreViewDC" /> </td>
												</tr>
												<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
												<tr>
													<td> <s:text name="label.MinPremiumOurShare" /> </td>
													<td align="right"> <s:property value="minPremiumOSOC"/> <s:hidden name="minPremiumOSOC" /> <s:hidden name="minPremiumOurShare" /> </td>
													<td align="right"> <s:property value="minPremiumOSDC"/> <s:hidden name="minPremiumOSDC" /> </td>
												</tr>
												</s:if>										
												</tbody>
											</table>
											<s:hidden name="NoRetroCess" />
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<s:if test='!"0".equals(m_d_InstalmentNumber)'>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="5%"> <s:text name="label.installmentNo" /> </th>
													<th width="31.66%"> <s:text name="label.installmentDate" /> </th>
													<th width="31.66%"> <s:text name="RiskDetails.M&DPremium" /> </th>
													<th width="31.66%"> <s:text name="label.paymentDueDays" /> </th>
												</tr>
												</thead>
												<tbody>	
												<s:iterator value="instalList" var="retroContract" status="stat">										
												<tr>
													<td>
													<s:property value='%{#stat.count}'/>
													  </td>
													<td>
														<s:textfield name="instalmentDateList[%{#stat.count-1}]" id="instalmentDateList[%{#stat.count-1}]"  cssClass="inputBox datepicker instalmentDate"   onkeyup="validateSpecialChars(this);" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/>
													</td>
													<td>
														<s:textfield name="installmentPremium[%{#stat.count-1}]" id="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);this.value=Comma(this.value);allow2DigitDecValues(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/>
													</td> 
													<td>
														<s:textfield name="paymentDueDays[%{#stat.count-1}]" id="paymentDueDays[%{#stat.count-1}]" cssClass="inputBox"   cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/> 
													</td> 
												</tr>												
												</s:iterator>
												</tbody>
											</table>											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							</s:if>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="label.acquisitionCost" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.brokerageP" /> 
												</div>
												<div class="tbox">
													<s:if test="(broker.toString().trim()).equalsIgnoreCase('DIRECT')">
														<s:textfield name="brokerage" id="BrokeragerValue" cssClass="inputBox" readonly="true" value="0" cssStyle="text-align:right;" onkeyup="negative(this.id,this.value);middleMinusRestrictionNeg(this);checkDecimals10(this);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()" maxlength="14"/>
													</s:if>
													<s:else>
														<s:textfield name="brokerage" id="BrokeragerValue" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()"  maxlength="14" disabled='%{"Y".equals(disableStatus1)?true:false}' />
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.taxP" /> 
												</div>
												<div class="tbox">
													<s:textfield name="tax" id="TaxValue" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" value='%{"D".equals(profit_Center)?0.00:tax}' onchange="GetAcqCost()" maxlength="8" disabled='"Y".equals(disableStatus1) || "D".equals(profit_Center)'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.otherCostP" /> 
												</div>
												<div class="tbox">
													<s:textfield name="othercost" id="CostOther" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}' />
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.acqCostAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="acquisition_Cost" readonly="true" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="middleMinusRestriction(this);javascript:this.value=Comma(this.value)" />
												</div>
											</div>	
											<div class="textfield">
															<div class="text">
																<s:text name="label.acqBonus" />
															</div>
															<div class="tbox">
																<s:select list="bonusList" listKey="DETAIL_NAME" listValue="REMARKS" name="acqBonus" cssClass="inputBoxS" headerKey=""	headerValue="---None---"   onchange="funViewLCB(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="ncb" style="display:none">
															<div class="text">
																<s:text name="label.acqBonusPer" />
															</div>
															<div class="tbox">
																	<s:textfield name="acqBonusPercentage" cssClass="inputBox"  cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="7" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="lcb" style="display:none">
															<div class="text">
																<a href="#" onclick="getPopUpDetailsNCB('LCB','<s:property value="contractNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>','<s:property value="layerNo"/>')"><s:text name="label.enterDetails" /></a>
															
															</div>
															
														</div>									
											
											<s:hidden name="share_Profit_Commission" value="0" />
											<br class="clear"/>
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
													<s:textfield name="RetentionPercentage" cssClass="inputBox" cssStyle="text-align: right;" value="%{RetentionPercentage ==null?'':RetentionPercentage}" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"  maxlength="8"  /><%--disabled='%{"Y".equals(disableStatus1)?true:false}' --%>
												</div>
											</div>	
																														
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="5%"> <s:text name="label.sNo" /> </th>
													<th width="31.66%"> <s:text name="label.uwYear" /> </th>
													<th width="31.66%"> <s:text name="label.retroContract" /> </th>
													<th width="31.66%"> <s:text name="label.retroP" /> </th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="retroFinalList" var="retroContract" status="stat">
												<tr>
													<td>
													<s:property value='%{#stat.count}'/>
													</td>
													<td>
														<s:select  list="retroUwyear" listKey="CONTDET1" listValue="CONTDET2" name="retroYear[%{#stat.count-1}]" id="retroYear[%{#stat.count-1}]" cssClass="inputBoxS" onchange="getRetroContract(this.value,'retroYear%{#stat.count-1}','%{#stat.count-1}')" headerKey="" headerValue="---Select---" /><%-- || "Y".equals(disableStatus1)? --%>
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
													     <s:select list="%{retroFinalList[#stat.count-1]}" name="retroCeding[%{#stat.count-1}]" id="retroCeding[%{#stat.count-1}]" listKey="CONTDET1" listValue="CONTDET2"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" /> <%--||"Y".equals(disableStatus1) --%>
													     
													</td>
													<%--</s:else>--%>
													<td>
														<s:textfield name="percentRetro[%{#stat.count-1}]" id="percentRetro[%{#stat.count-1}]" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8"  /><%--||"Y".equals(disableStatus1) --%>
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
										<div class="boxcontent">
										<div class="textfield">
												<div class="text">
													<s:text name="label.reinstatementpremium" />
												</div>
												<div class="tbox">													
													<s:radio name="reInstatementPremium" id="reInstatementPremium" list="#{'Y':'Yes','N':'No'}"  value="(reInstatementPremium==null || reInstatementPremium=='')?'N':reInstatementPremium" onclick="funView(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}' />
												</div>
											</div>
											<div class="textfield" id="rsp" style="display:none">
															<div class="text">
																<a href="#" onclick="getPopUpDetails('RSP','<s:property value="contractNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>','<s:property value="anualAggregateLiability"/>','<s:property value="layerNo"/>')"><s:text name="label.enterDetails" /></a>
															</div>
											</div>														
											<div class="textfield">
												<div class="text">
													<s:text name="label.annualAggregateLiability" />
												</div>
												<div class="tbox">													
													<s:textfield name="anualAggregateLiability" id="anualAggregateLiability" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="RiskDetails.AnnualAggrDeduct" />
												</div>
												<div class="tbox">													
													<s:textfield name="anualAggregateDeduct" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;"  maxlength="16"/>
												</div>
											</div>
											<div class="textfield" id="occID" style="display:none;">
												<div class="text">
													<s:text name="label.occ.limit" />
												</div>
												<div class="tbox">
													<s:textfield name="occ_limit" id="occ_limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>
												</div>
											</div>
															
											<%-- <div class="textfield">
												<div class="text">
													<s:text name="label.noOfReinstatment" />
												</div>
												<div class="tbox">												
													<s:textfield name="reinstNo"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="javascript:this.value=Comma(this.value);allow2DigitDecValues(this);" maxlength="26"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.reinstAdditionalPremiumP" />
												</div>
												<div class="tbox">
													<s:textfield name="reinstAditionalPremium_percent" cssClass="inputBox" cssStyle="text-align: right;" onblur="GetReinstatmentPremium(this.value)" onkeyup="checkDecimals(this)" maxlength="8"/>													
												</div>
											</div>
											<div class="textfieldA">
												<div class="text">
													<s:text name="label.reinstAdditionalPremium" />
												</div>
												<div class="tbox">
													<s:textfield name="reinstAdditionalPremium" cssClass="inputBox" cssStyle="text-align: right;" readonly="true" onkeyup="javascript:this.value=Comma(this.value)" />
												</div>
											</div>		--%>						
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
											<div class="textfield">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus" value="(crestaStatus==null || crestaStatus=='')?'N':crestaStatus" onchange="toggleCrestapopUp(this.value);" disabled='%{"Y".equals(disableStatus1)?true:false}' ></s:radio>
												</div>
											</div>
											<div class="textfield" id="cresta" style="display:none">
											<div class="text">
												<a href="#" onclick="CrestapopUp()"><s:text name="label.enterDetails" /></a>
												<%-- <input id="cresta" type="button"  size="2"  value="Click Here" onclick="CrestapopUp()" />--%>
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
											<%-- <div class="textfield">
												<div class="text">
													<s:text name="label.burningCostP" />
												</div>
												<div class="tbox">
													<s:textfield name="burningCost" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this)" maxlength="8"/>
												</div>
											</div>
											<div class="textfield"></div>--%>
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderWriter" /> 
												</div>
												<div class="tbox">
													<%--<s:textfield name="leader_Underwriter" cssClass="inputBox" maxlength="500"/>--%>
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
												<s:hidden name="leader_Underwriter_country" id="leader_Underwriter_country" />
											</div>
											</s:else>
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderwritterShareP" /> 
												</div>
												<div class="tbox">
													<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);" maxlength="8" disabled="false"/>
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
												<s:textarea id="exclusion" name="exclusion" rows="3" cssClass="inputBoxA" cssStyle="width: 100%; resize: vertical; "/>
												<br/>
												<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
											</div>
											<br class="clear"/>
										</div>
										<!--<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="label.remarks" />
											</div>
											<div class="textfieldA75">
												<s:textarea name="remarks" id="remarks" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;" />
												<br/>
												<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
											</div>
											<br class="clear"/>
										</div>
										
									-->
									<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="label.underwritersRecommendations" /> 
											</div>
											<div class="textfieldA75">
												<s:textarea cssClass="inputBoxA" id="underwriter_Recommendations" name="underwriter_Recommendations" rows="3" cssStyle="width: 100%; resize: vertical; "/>
												<br/>
												<span class="textAreaRemaining"><label id="underwriter_Recommendations_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
											</div>
											<br class="clear"/>
										</div></div>
								</div>
							</div>
							
							
							<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
							<s:if test="amend_Id_Mode != '' && amend_Id_Mode != null && (proposalReference=='' || proposalReference ==null)">
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
												<s:if test='"Y".equals(endorsementStatus)'>		--%>		
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
													<s:textfield name="endorsementDate" id="endorsementDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="functionDate()" disabled="%{prclFlag==true?true:false}" />
												</div>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.docDetails" />
												</div>
												<div class="tbox">
														<s:radio name="docStatus" id="docStatus" list="#{'Y':'Yes','N':'No'}" value="(docStatus==null||docStatus=='')?'N':docStatus" onclick="DocScript(this.value)"/>
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
															<table class="table table-bordered" id="DocTable">
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
																		<s:select name="docTypeId[%{index}]" id="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																	</td>
																	</s:if>
																	<s:else>
																	<td class="formCon"  valign="top">
																		<s:select name="docTypeId[%{index}]" list="#{}" id="docTypeId[%{index}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																	</td>
																	</s:else>
																	<td class="formCon" style="text-align: center;">
																		<s:textarea name="docDesc[%{index}]"  id="docDesc[%{index}]" rows="2" cols="70" cssClass="inputBox" />												 
																	</td>													
																	<td class="formCon">
																		<s:file name="upload"  id="upload[%{index}]" cssClass="inputBox"  />
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
															<input type="button"  value="Add More" class="btn btn-sm btn-info" id="addmore" onClick="insRow('DocTable');" /> 
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
						</div>
													
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button" value="Previous" class="btn btn-sm btn-warning" id="mybutton1" onclick="disableForm(this.form,false,'');FunctionBackMode()">								
								<s:if test="amend_Id_Mode == '' || amend_Id_Mode == null && (proposalReference!='' || proposalReference !=null) ">
									<input type="button" class="btn btn-sm btn-danger" onclick="CancelPage('%{proStatus}')" value="Cancel">									
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');SavePage()" value="Save">
									<s:if test='"A".equals(proStatus)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');SubmitPage()">
									</s:if>
								</s:if>								
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
									<input type="button" value="Submit" class="btn btn-sm btn-success" id="mybutton2" onclick="disableForm(this.form,false,'');return oncheck();"/>
								</s:else>
							</div>
						</div>
						<s:hidden name="departId"/>
						<s:hidden name="proposalno" id="proposalno"  value="%{proposal_no}" />
						<s:hidden name="baseLayer" value="%{baseLayer}" />
						<s:hidden name="layerProposalNo" id="layerProposalNo" value="%{layerProposalNo}" />
						<s:hidden name="contarctno" value="%{contNo}" />
						<s:hidden name="contNo" id="contNo"/>
						<s:hidden name="proposalType" />
						<s:hidden name="broker" />
						<s:hidden name="gms_Approval" />
						<s:hidden name="layerNo" />
						<s:hidden name="incepDate" />
						<s:hidden name="expDate" />
						<s:hidden name="limitOrigCur" />
						<s:hidden name="proposal_no" id="proposal_no" />
						<s:hidden name="amend_Id_Mode" />
						<%--<s:hidden name="baseLayer" id="baseLayer"/>--%>
						<s:hidden name="renewal_contract_no" />
						<s:hidden name="renewalFlag" />
						<s:hidden name="layerLayerNo" id="layerLayerNo" />
						<s:hidden name="instlmentperod" />
						<s:hidden name="contractno" />
						<s:hidden name="lay" />
						<s:hidden name="baseLoginID" />
						<s:hidden name="amendId" id="amendId"/>
						<s:hidden name="no_Insurer" />
						<s:hidden name="m_d_InstalmentNumber" />
						<s:hidden name="retroType" />
						<s:hidden name="mode" />
				    	<s:hidden name="contractno1" />
  						<s:hidden name="lay1" />
						<s:hidden name="orginalCurrency" id="orginalCurrency"/>
						<s:hidden name="proposalReference" id="proposalReference" />	
						<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>	
						<s:hidden name="proposalNo" id="proposalNo"/>
						<s:hidden name="businessType" id="businessType"/>
						<s:hidden name="proStatus" id="proStatus"/>
						<s:hidden name="profit_Center" id="profit_Center"/>
						<s:hidden name="endorsmenttype" id="endorsmenttype"/>
						<s:hidden name="renewalEditMode" id="renewalEditMode"/>
						<s:hidden name="bonusPopUp" id="bonusPopUp"/>
                        <s:hidden name="crestaPopUp" id="crestaPopUp"/>
                        <s:hidden name="reinsPopUp" id="reinsPopUp"/>
                        <s:hidden name="flag" id="flag"/>
                        <s:hidden name="shareValue" id="shareValue"/>
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
function AmendIdBack()
{	
	destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	var no = document.getElementById("proposal_no").value;
 	//document.getElementById("endorsementStatusY").disabled = true;
	//document.getElementById("endorsementStatusN").disabled = true;
	document.getElementById("endorsementStatus").value='N';
	document.xol2.action= "${pageContext.request.contextPath}/InitPortfolio.action?proposalNo="+no+"&endtMode=endorsment&endorsementStatus=N";
	document.xol2.submit();
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
		var URL='<%=request.getContextPath()%>/getRetroContractDetailsXol.action?inceptionDate='+inceptionDate+'&productCode='+product+'&uwYear='+uwYear+'&dropDown=retroCeding&retroListName='+count+'&mode='+mode;
  	 	postRequest(URL,id);
	}
}

function SubmitPage() {
	destroyPopUps();
	replaceComma(document.xol2,'acquisition_Cost,anualAggregateLiability,anualAggregateDeduct,reinstAdditionalPremium');
	document.xol2.action="${pageContext.request.contextPath}/RiskSecondPageXol.action";
	document.xol2.submit();
}
        
function Editmode()
{
	document.xol2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=SecondPageUpdate';
	document.xol2.submit();
}

function SavePage() {
	destroyPopUps();
	replaceComma(document.xol2,'acquisition_Cost,anualAggregateLiability,anualAggregateDeduct,reinstAdditionalPremium');
	document.xol2.action="SecondPageSaveXol.action";
	document.xol2.submit();
}	  	    
	    
function AmednIdSubmitPage() {
	document.xol2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdInsertSecondPage';
	document.xol2.submit();
}
	    
function AmednIdSavePage() {
	document.xol2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdSavePage';
	document.xol2.submit();
}
function EndorsementScript(value) {
		if(value=="Y") {
			document.getElementById('endtlabel').style.display='block';		
			document.getElementById('doc1').style.display='block';
            document.xol2.docStatus.value= 'N';
		}
		else if(value=="N") {
			document.getElementById('endtlabel').style.display='none';
			document.getElementById('doc1').style.display='none';
            document.getElementById('docView').style.display='none';
        }
	}
function CalculateValue() {
	var commisson='';
	var	epiAsPerShare=document.xol2.epiAsPerShare.value;
	var coQs=document.xol2.commissionQ_S.value;
	if(coQs==null || coQs=="")
		coQs=0;
	var coSur=document.xol2.commission_surp.value;
	if(coSur==null || coSur=="")
		coSur=0;   	 	
	var tax=document.getElementById("taxIDDD").value;
    if(tax==null || tax=="")
		tax=0;
	var brokerage=document.getElementById("brokerageIDD").value;
    if(brokerage==null || brokerage=="")
		brokerage=0;
	var overidden=document.xol2.overRidder.value;
    if(overidden==null || overidden=="")
		overidden=0;
	var otherCost=document.xol2.othercost.value;
    if(otherCost==null || otherCost=="")
		otherCost=0;
	var PQS=document.xol2.premiumQuotaShare.value;
    if(PQS==null || PQS=="")
		PQS=0;
	else
		PQS=PQS.replace(new RegExp(',', 'g'),'');					
	var PSur=document.xol2.premiumSurplus.value;
    if(PSur==null || PSur=="")
		PSur=0;
	else
		PSur=PSur.replace(new RegExp(',', 'g'),'');
	var QSAmt=(parseFloat(coQs)*parseFloat(PQS))/100;
	var SurpAmt=(parseFloat(coSur)*parseFloat(PSur))/100;
	document.xol2.commissionQ_SAmt.value=Comma(QSAmt.toFixed(2));
	document.xol2.commission_surpAmt.value=Comma(SurpAmt.toFixed(2));		
	var value=(parseFloat(commisson) * parseFloat(epiAsPerShare));
	var per=(parseFloat(brokerage)+parseFloat(tax)+parseFloat(overidden)+parseFloat(otherCost))/100;
	var otheracqcostAmt=(per*parseFloat(epiAsPerShare));
	document.xol2.acqCostPer.value=Comma(otheracqcostAmt.toFixed(2));
	commisson=parseFloat(QSAmt)+parseFloat(SurpAmt)+parseFloat(otheracqcostAmt);
	document.xol2.acquisition_Cost.value=Comma(commisson.toFixed(2));
	alert("QR"+document.xol2.epiAsPerOffer.value);
     				 
}

function CancelPage(flag) {
	//var param='';
	//if(flag=="N")
	//{
	//	param='&flag=N'
	//}
	//var proposal_no = document.xol2.proposal_no.value
	//document.location='${pageContext.request.contextPath}/commonListPortfolio.do?method=menu&manufactureID=3'+param;
	//document.xol2.action = "${pageContext.request.contextPath}/commonListPortfolio.do?proposalNo="+proposal_no;
	//document.xol2.submit();
	destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	var no =document.getElementById("renewalProposalNo").value;
	var out =document.getElementById("proposalNo").value;

if('Renewal'==val || 'Layer'==val){
		answer = confirm("This action will remove the newly created proposal number "+out+" which cannot be reused.  Do you wish to continue?");
		if(answer){
			document.getElementById("renewalProposalNo").value =out;
			document.getElementById("proposalNo").value = no;
			document.xol2.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action';
			document.xol2.submit();
			}
			else {
				 return false; 
			}
		}
		else{
			document.xol2.action="${pageContext.request.contextPath}/menuPortfolio?proposalNo="+no; 
			document.xol2.submit();
		}	
}

function FunctionBackMode() {
destroyPopUps();
	//document.getElementById("proposal_no").disabled = true;
	//document.getElementById("endorsementStatusN").disabled = true;
	document.xol2.action="EditModeXol.action?multiuserMode=edit";
	document.xol2.submit();
}
function ShareProfitCommission(value,proId) {
	if(proId=='2') {
		document.xol2.share_Profit_Commission.value=value;
		if(value=="1") {
			document.getElementById("Mangment").readOnly=false;
			document.getElementById("loss").readOnly=false;
			document.xol2.profit_commission.readOnly=false;
   		}
    	else if(value=="2")
    	{
			document.getElementById("Mangment").readOnly=true;
			document.getElementById("loss").readOnly=true;
			document.xol2.profit_commission.readOnly=true;
			document.xol2.management_Expenses.value='';
			document.xol2.lossC_F.value='';
			document.xol2.profit_commission.value='';
    	}
	}
}

function cleanCutPopUp() {
	if(document.xol2.proposalType.value=="R")
	{
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Loss %?","Yes","No");
		if(doIt) {
			document.xol2.portfolio_inout_Loss.readOnly=false;
			document.xol2.portfolio_inout_Loss.focus();
		} else {
			document.xol2.portfolio_inout_Loss.value='0';
  			document.xol2.portfolio_inout_Loss.readOnly=true;
  			cleanCutPopUp1();
		}
	}
}

function cleanCutPopUp1() {
	if(document.xol2.proposalType.value=="R") { 
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Premium %?","Yes","No");
		if(doIt) {
			document.xol2.portfolio_inout_Premium.readOnly=false;
			document.xol2.portfolio_inout_Premium.focus();
		} else {
			document.xol2.portfolio_inout_Premium.value='0';
			document.xol2.portfolio_inout_Premium.readOnly=true;
			document.xol2.loss_Advise.focus();
		}
	}
}

function PremiumQuotaShare1() {
	var share=document.xol2.premiumQuotaShare.value;
	share=share.replace(new RegExp(',', 'g'),'');
	var Epi=document.xol2.epiAsPerShare.value;
	Epi=Epi.replace(new RegExp(',', 'g'),'');
	var val=Epi-share;
	if(share<0) {
		alert('Premium Quota Share Should not less than 0');
		document.xol2.premiumQuotaShare.value='';
		document.xol2.premiumSurplus.value='';
	}else if(val<0) {
	 	alert('Premium Surplus Should not less than 0');
	 	document.xol2.premiumQuotaShare.value='';
	 	document.xol2.premiumSurplus.value='';
 	}else{
 		document.xol2.premiumSurplus.value=Comma(Math.round(val*1000)/1000);
	}
	if(share=='0')
	{
		document.xol2.commissionQ_S.value='0';
		document.xol2.commissionQ_S.readOnly=true;
	}else
	{
		document.xol2.commissionQ_S.readOnly=false;
	}
	if(val==0)
	{
		document.xol2.commission_surp.value='0';
		document.xol2.commission_surp.readOnly=true;
	}else
	{
	 document.xol2.commission_surp.readOnly=false;
	}
	CalculateValue();
}

function PremiumQuotaShare() {
	var share=document.xol2.premiumQuotaShare.value;
	var surplus=document.xol2.premiumSurplus.value;
	var Epi=document.xol2.epiAsPerShare.value;
	if(Epi==share) {
		document.xol2.premiumSurplus.readOnly=true;
		document.xol2.commission_surp.readOnly=true;
		document.xol2.premiumSurplus.value='0'
		document.xol2.commission_surp.value='0'
		document.xol2.commissionQ_S.readOnly=false;
		document.xol2.premiumQuotaShare.readOnly=false;
	} else if(Epi==surplus) {
		document.xol2.premiumSurplus.readOnly=false;
		document.xol2.commission_surp.readOnly=false;
		document.xol2.commissionQ_S.value='0'
		document.xol2.premiumQuotaShare.value='0'
		document.xol2.commissionQ_S.readOnly=true;
//		document.xol2.premiumQuotaShare.readOnly=true;
	} else {
		document.xol2.premiumSurplus.readOnly=false;
		document.xol2.commission_surp.readOnly=false;
		document.xol2.commissionQ_S.readOnly=false;
		document.xol2.premiumQuotaShare.readOnly=false;
	}
	
	if(surplus!="" &&  share!="" ) {
		var c=parseFloat(share)+parseFloat(surplus);
		if(c > parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.xol2.premiumQuotaShare.value='';
			document.xol2.premiumSurplus.value='';
		}
		else if(c < parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.xol2.premiumQuotaShare.value='';
			document.xol2.premiumSurplus.value='';
		}
		else {
			document.getElementById("error").style.display='none';
		}
	}
}

function GetPremiumReserveIntr() {
	if(document.xol2.loss_reserve.value=='0'  && document.xol2.premium_Reserve.value=='0') {
		document.xol2.interest.value='0';
		document.getElementById("interset").readOnly=true;
	}
	else {
		document.xol2.interest.value="";
		document.getElementById("interset").readOnly=false;
	}
}

<%--function GetReinstatmentPremium(value) {
	var a=document.xol2.epiAsPerOffer.value;
	value=value.replace(new RegExp(',', 'g'),'');
	if(a!=null && a!="" && value!=null && value!="" && value!=0) {  
		var c=(a*value)/100;
		document.xol2.reinstAdditionalPremium.value=Comma(c.toFixed(2));
	}
	else {
		document.xol2.reinstAdditionalPremium.value='0.00'
	}
}--%>

function GetAcqCost() {

var sourceId ='<s:property value="#session.SOURCE_CODE"/>';
var epi=0;
	if(sourceId=="RI01"){
		epi=document.xol2.minPremiumOurShare.value;
	}else{
	 epi=document.xol2.epiAsPerOffer.value;
	}
	//var md=document.xol2.md_premium_our_service.value;
	var brok=document.getElementById("BrokeragerValue").value;
	var tax=document.getElementById("TaxValue").value;
	var CostOther=document.getElementById("CostOther").value;
	if(brok==""){
	brok="0";
	}
	if(tax==""){
	tax="0";
	}
	if(CostOther==""){
	CostOther="0";
	}
	//if( document.getElementById("BrokeragerValue").value!="" && document.getElementById("TaxValue").value !=""  && document.getElementById("CostOther").value != "") {
		var c=parseFloat(epi)*((parseFloat(brok)+parseFloat(tax)+parseFloat(CostOther))/100);
		document.xol2.acquisition_Cost.value=Comma(c.toFixed(2));
	//}
	//else {
	//	document.xol2.acquisition_Cost.value=''
	//}
}

function Commas(value) {
	if(value=="2") {     
		document.xol2.cash_Loss_Limit.value=Comma(document.xol2.cash_Loss_Limit.value);
		document.xol2.loss_Advise.value=Comma(document.xol2.loss_Advise.value);
		document.xol2.premiumQuotaShare.value=Comma(document.xol2.premiumQuotaShare.value);
		document.xol2.premiumSurplus.value=Comma(document.xol2.premiumSurplus.value);
		document.xol2.acquisition_Cost.value=Comma(document.xol2.acquisition_Cost.value);
		PremiumQuotaShare1();	
	}
	else if(value=="3") {
		//document.xol2.reinstAditionalPremium_percent.value=Comma(document.xol2.reinstAditionalPremium_percent.value);
		document.xol2.anualAggregateLiability.value=Comma(document.xol2.anualAggregateLiability.value);
		document.xol2.anualAggregateDeduct.value=Comma(document.xol2.anualAggregateDeduct.value);
		//document.xol2.reinstNo.value=Comma(document.xol2.reinstNo.value);
		//document.xol2.reinstAdditionalPremium.value=Comma(document.xol2.reinstAdditionalPremium.value);
		document.xol2.acquisition_Cost.value=Comma(document.xol2.acquisition_Cost.value);
		
		GetAcqCost();
		//GetReinstatmentPremium(document.xol2.reinstAditionalPremium_percent.value);
	}
}
function endorsementPage() {
	replaceComma(document.xol2,'acquisition_Cost,anualAggregateLiability,anualAggregateDeduct,reinstAdditionalPremium');
	document.xol2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementXol.action";
	document.xol2.submit();
}
function oncheck(){
     <%-- if(document.getElementById("endorsementStatusN").checked){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose ???Yes??? under the Endorsement finalised field.");
     	if(answer)
     	endorsementPage();
     	else
     	return false;
     	}
    else --%>
    destroyPopUps();
    document.getElementById("endorsementStatus").value="Y";
    endorsementPage();
}
$(function() {
		//set up text length counter
		$('#exclusion').keyup(function() {
	        update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
	    });
	    //and fire it on doc ready, too
	    update_chars_left(500, $('#exclusion')[0], $('#exclusion_left'));
	    
	    $('#underwriter_Recommendations').keyup(function() {
	        update_chars_left(500, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
	    });
	    update_chars_left(500, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
		    	
	   /// $('#remarks').keyup(function() {
	        //update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	   // });	    
	    //update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
	    
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
	funView('<s:property value="reInstatementPremium"/>');
	function  funView(id){
			if(id=="Y"){
	     		document.getElementById('rsp').style.display = 'block';
	       	} 
	       	else if(id=="N"){
	       	 	document.getElementById('rsp').style.display = 'none';
	       	}
	}
	function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo,anualAggregateLiability,layerNo){
	var val ='<s:property value="disableStatus1"/>';
	var mode='';
	if('Y'==val){
	mode='view';
	}
	 document.getElementById("reinsPopUp").value = "Y";
	var URL ="${pageContext.request.contextPath}/viewPopUpXol.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&anualAggregateLiability="+anualAggregateLiability+"&layerNo="+layerNo+'&mode='+mode;
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
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
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
	var contractNo = document.getElementById("contNo").value;
	var proposalno = document.getElementById("proposalno").value;
	var amendId = document.getElementById("amendId").value;
	var cur = document.getElementById("orginalCurrency").value;
	var mode='';
	var layerProposalNo = document.getElementById("layerProposalNo").value;
	var val ='<s:property value="disableStatus1"/>';
	if("Y"==val){
	mode='view';
	} document.getElementById("crestaPopUp").value = "Y";
		var URL ='${pageContext.request.contextPath}/crestaPopUpRiskDetails.action?contractNo='+contractNo+'&proposal_no='+proposalno+'&amendId='+amendId+'&orginalCurrency='+cur+'&layerProposalNo='+layerProposalNo+'&mode='+mode;
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
	var URL='${pageContext.request.contextPath}/ajaxValueXol.action?leader_Underwriter='+value+'&dropDown=country&leader_Underwriter_country='+leader_Underwriter_country;
	postRequest(URL,'country');
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
	funViewLCB('<s:property value="acqBonus"/>');
	function  funViewLCB(id){
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
	
	function getPopUpDetailsNCB(acqBonus,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo,layerNo){
	 document.getElementById("bonusPopUp").value = "Y";
		var URL ="${pageContext.request.contextPath}/viewBonusPopUpXol.action?acqBonus="+acqBonus+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&layerNo="+layerNo;		
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
																	
GetBusinessType('<s:property value="businessType"/>');
function GetBusinessType(val){
if( val=='2' ||val=='3' ||val=='7' || val=='8'){
document.getElementById('occID').style.display='inline';

}else{
document.getElementById('occID').style.display='none';
}

}
getFieldDisable('<s:property value="endorsmenttype"/>');
function getFieldDisable(val){
	var type = document.getElementById("businessType").value;
	var table='';
	if("GNPI"==val){
		var form = document.getElementById("xol2");
		var elements = form.elements;
		for (var i = 0, len = elements.length; i < len; ++i) {
		    elements[i].disabled = true;
		}
		document.getElementById("mybutton").disabled = false;
		//document.getElementById("mybutton1").disabled = false;
		document.getElementById("mybutton2").disabled = false;
		//document.getElementById("endorsementStatusY").disabled = false;
		//document.getElementById("endorsementStatusN").disabled = false;
		document.getElementById("endorsementDate").disabled = false;
		document.getElementById("docStatusY").disabled = false;
		document.getElementById("docStatusN").disabled = false;
}
}
	
DocScript('<s:property value="docStatus"/>');
function DocScript(id){
    var amendId = document.getElementById("amendId").value;
    if (amendId > 0) {
        document.xol2.docStatus.value = id;
        if ("Y" == id) {
            document.getElementById('docView').style.display = 'block';
            document.getElementById("addmore").disabled = false;
        }
        else {
            document.getElementById('docView').style.display = 'none';
        }
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

 <s:iterator value="docuList"  status="stat">   
  var val='<s:property value="%{#stat.count-1}"/>'; 
 document.getElementById("docTypeId["+val+"]").disabled = false;
 document.getElementById("docDesc["+val+"]").disabled = false;
 document.getElementById("upload["+val+"]").disabled = false;
</s:iterator>
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
 
 		
function deleteUpload(id){
if(confirm('This row will be deleted. Do You Want to continue?')){
	postFormRequest('${pageContext.request.contextPath}/newDocDeleteXol.action?mode=delete&deleteId='+id+'&dropDown=docView', "docView", "xol2");
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
