<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
		$( "#report_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#date_of_Loss" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#reservePositionDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#created_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#review_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#cliam_update_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#reopen_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#claim_closed_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#reputed_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
 </script>
	</head>

	<body onload="addComma('<s:property value="path" />')">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form id="" name="claims" theme="simple" action="" method="post" autocomplete="off">
							<%!double b=0; double a=0; %>
								<div class="table2">
									<div class="tablerow">
										<span style="color: red;"><s:actionerror />
										</span>
									</div>
									<div class="tablerow">
										<s:if test="!'InitMode'.equals(cliamMode)">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body">
														<div class="boxcontent" align="center">
															<s:if test="#session.IntialMode.equals('IntialMode')">
																<input type="button" value="Claim Registration" style="cursor: hand" class="btn btn-xs btn-primary" onclick="AmodeInit()" />
															</s:if>
															<s:else>
																&nbsp;&nbsp;&nbsp; <input type="button" value="Claim Registered" style="cursor: hand" class="btn btn-xs btn-primary" onclick="Amode()" />
																<s:if test="loss_Estimate_Revised_Orig_Curr == '' || loss_Estimate_Revised_Orig_Curr == null">
																	&nbsp;&nbsp;&nbsp; <input type="button" value="Claim Payment" style="cursor: hand; color: gray;" class="btn btn-xs btn-primary" />
																</s:if>
																<s:else>
																	&nbsp;&nbsp;&nbsp; <input type="button" value="Claim Payment" style="cursor: hand" class="btn btn-xs btn-primary" onclick="Bmode()" />
																</s:else>
																	&nbsp;&nbsp;&nbsp; <input type="button" value="Claim Reserve Updation" style="cursor: hand" class="btn btn-xs btn-primary" onclick="Cmode()" />
																&nbsp;&nbsp;&nbsp; <%-- <input type="button"  value="Claim Review" style="cursor: hand"   class="btn btn-xs btn-primary"  onclick="Dmode()" />--%>
															</s:else>
														</div>
													</div>
												</div>
											</div>
										</s:if>
										<s:if test='"Amode".equals(path)'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="label.reservePosition" />
													</div>
													</div>
												<div class="panel-body">
														<div class="boxcontent">
															<display:table name="RevisionList" pagesize="5"
																requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set var="myrow" value="#attr.record" />
																<display:column sortable="true" style="text-align:center;" format="{0,number,0,000.00}" title="Reserve Id" property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Date" property="cliam_update_Date" />
																<display:column sortable="true" style="text-align:center;" title="Modify Reserve">
																	<a href="#" onclick="ModifyReserve()" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="Reserve Updation">Modify Reserve Updation</a>
																</display:column>
																<display:column sortable="true" style="text-align:center;" title="View">
																	<a href="#" onclick="ViewReserve()" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
															</display:table>
															<br class="clear" />
														</div>
													</div>
											</div>
											<div class="boxcontent">
												<div class="panel panel-primary">
												<div class="panel-heading">
														<s:text name="Heading.ClaimPayment" />
														</div>
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<display:table name="ClaimPayment" pagesize="5" requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<display:column sortable="true" style="text-align:center;" title="Reserve Id" property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Payment No." property="claimPaymentNo" />
																<display:column sortable="true" style="text-align:center;" title="Client Ref No. " property="payment_Request_No" />
																<display:column sortable="true" style="text-align:center;" title="Payment Date" property="date" />
																<%-- <s:if test='"3".equals(productId)'>
																	<display:column sortable="true" style="text-align:right;" title="Net Claim Amount OC" property="paid_Amount_Orig_curr" />
																</s:if>
																<s:else>--%>
																	<display:column sortable="true" style="text-align:right;" title="Total Paid Amount - Our Share - OC" property="paid_Amount_Orig_curr" />
																<%-- </s:else>--%>
																<display:column sortable="true" style="text-align:center;" title="Settlement Status">
																	<a href="#" onclick="ViewPayment('${record.claimPaymentNo}')" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
															</display:table>
															<br class="clear" />
														</div>
												</div>
												</div>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="heading.claim.claimDetails" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.statusOfClaim" />
																</div>
																<div class="tbox">
																	<s:if test="status_of_claim == '' || status_of_claim == null">
																		<s:radio list="#{'Open':'Open','Closed':'Close'}" name="status_of_claim" />
																	</s:if>
																	<s:elseif test='"Open".equals(status_of_claim)  '>
																		<s:if test="#session.Cliam_cliam_no==null">
																			<s:radio list="#{'Open':'Open','Repudiate':'Repudiate'}" name="status_of_claim" disabled="true"/>
																			<s:hidden name="status_of_claim" id="status_of_claim"/>
																		</s:if>
																		<s:else>
																		<s:radio list="#{'Open':'Open','Repudiate':'Repudiate'}" name="status_of_claim" onclick="funReopenDate(this.value)"/>
																		</s:else>
																	</s:elseif>
																	<s:elseif test='"Repudiate".equals(status_of_claim) '>
																		<s:if test="#session.Cliam_cliam_no==null">
																			<s:radio list="#{'Repudiate':'Repudiate','Reopened':'Reopen'}" name="status_of_claim" disabled="true"/>
																			<s:hidden name="status_of_claim" id="status_of_claim"/>
																		</s:if>
																		<s:else>
																		<s:radio list="#{'Repudiate':'Repudiate','Reopened':'Reopen'}" name="status_of_claim" onclick="funReopenDate(this.value)"/>
																		</s:else>
																	</s:elseif>
																	<s:elseif test='"Closed".equals(status_of_claim) '>
																		<s:if test="#session.Cliam_cliam_no==null">
																			<s:radio list="#{'Closed':'Close','Reopened':'Reopen'}" name="status_of_claim" disabled="true"/>
																			<s:hidden name="status_of_claim" id="status_of_claim"/>
																		</s:if>
																		<s:else>
																		<s:radio list="#{'Closed':'Close','Reopened':'Reopen'}" name="status_of_claim" onclick="funReopenDate(this.value)"/>
																		</s:else>
																	</s:elseif>
																	<s:elseif test='"Reopened".equals(status_of_claim) '>
																		<s:if test="#session.Cliam_cliam_no==null">
																			<s:radio list="#{'Reopened':'Reopen','Repudiate':'Repudiate'}" name="status_of_claim" disabled="true" />
																			<s:hidden name="status_of_claim" id="status_of_claim"/>
																		</s:if>
																		<s:else>
																		<s:radio list="#{'Reopened':'Reopen','Repudiate':'Repudiate'}" name="status_of_claim" onclick="funReopenDate(this.value)"/>
																		</s:else>
																	</s:elseif>
																	<s:elseif test="status_of_claim != '' || status_of_claim != null">
																		<s:if test='!"Open".equals(status_of_claim)'>
																			<s:radio list="#{'Closed':'Close','Reopened':'Reopen'}" name="status_of_claim" onclick="disableAll(this.value);funReopenDate(this.value)" />
																		</s:if>
																	</s:elseif>
																</div>
															</div>
															<s:if test='"3".equals(productId)'>
															<div class="textfield">
																<div class="text ">
																	<s:text name="label.department.class" />
																</div>
																<div class="tbox">
																		<s:select name="departmentClass" id="departmentClass" list="departmentClassList" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  />
																</div>
															</div>
															</s:if>
															<s:else>
															<div class="textfield">
																<s:hidden name="departmentClass" id="departmentClass"/>
															</div>
															</s:else>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.dateofLoss" />
																</div>
																<div class="tbox">
																	<s:textfield name="date_of_Loss" id="date_of_Loss" cssClass="inputBox" onchange="getReserveDate(this.value);"/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.reportDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="report_Date" id="report_Date" cssClass="inputBox" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.reservePositionDate" />
																</div>
																<div class="tbox">
																<s:if test="claim_No==null || claim_No=='' ">
																	<s:textfield name="reservePositionDate" id="reservePositionDate" cssClass="inputBox" />
																</s:if>
																<s:else>
																<s:textfield name="reservePositionDate" id="reservePositionDate" cssClass="inputBox" disabled="true"/>
																<s:hidden name="reservePositionDate"></s:hidden>
																</s:else>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.registrationDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="created_Date" id="created_Date" cssClass="inputBox" onchange="GetExchangeRate()" />
																	<s:if test='"Yes".equals(ri_Recovery)'>
																		<s:select list="#{'india':'India','USA':'USA'}" name="recovery_from" cssClass="inputBoxS" cssStyle="display:none;" headerKey="removed" headerValue="---Select---" />
																	</s:if>
																	<s:else>
																		<s:select list="#{'india':'India','USA':'USA'}" name="recovery_from" cssClass="inputBoxS" cssStyle="display:none;" headerKey="removed" headerValue="---Select---" />
																	</s:else>
																</div>
															</div>
															<div class="textfieldA">
																<div class="text ">
																	<s:text name="claim.lossDetails" />
																</div>
																<div class="tbox">
																	<s:textarea rows="3" id="loss_Details" name="loss_Details" cssClass="inputBoxA" cssStyle="width: 90%;" />
																	<br />
																	<span class="textAreaRemaining"><label id="loss_Details_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
																</div>
															</div>
															<div class="textfieldA">
																<div class="text ">
																	<s:text name="claim.causeofLoss" />
																</div>
																<div class="tbox">
																	<s:textarea rows="3" id="cause_of_Loss" name="cause_of_Loss" cssClass="inputBoxA" cssStyle="width: 90%;" />
																	<br />
																	<span class="textAreaRemaining"><label id="cause_of_Loss_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
																</div>
															</div>
															
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.location" />
																</div>
																<div class="tbox">
																	<s:textfield name="location" cssClass="inputBox"  maxlength="30" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.cedentsNo" />
																</div>
																<div class="tbox">
																	<s:textfield name="cedentClaimNo" id="cedentClaimNo"  cssClass="inputBox"  maxlength="30" cssStyle="float:left;"/>
																	<span><input type="button"  size="2"  value="..." onclick="cedentNumberDetails();" class="pullRight" cssStyle="text-align:right;width: 45%; float:left;"/></span>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.currency" />
																</div>
																<div class="tbox">
																	<s:if test="claim_No==null || claim_No=='' ">
																		<s:select name="currecny" id="currecny" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" onchange="GetExchangeRate()" />
																	</s:if>
																	<s:else>
																		<s:select name="currecny" id="currecny" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" disabled="true" onchange="GetExchangeRate()" />
																	</s:else>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.excRate" />
																</div>
																<div class="tbox" id="exc_Rate1">
																	<s:textfield name="exc_Rate" id="exc_Rate" cssClass="inputBox" cssStyle="text-align:right;"  readonly="true"/>
																	
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.grossLossFGU" />
																</div>
																<div class="tbox">
																	<s:textfield name="grossLossFGU"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="FindOurShare()" maxlength="27" />
																</div>
															</div>
															<div class="textfield">
																<div class="text">
																	<s:text name="claim.insuredName" />
																</div>
																<div class="tbox">
																	<s:textfield name="insuredName" cssClass="inputBox" maxlength="100" />
																</div>
															</div>
															<div class="textfield">
																<s:if test='"3".equals(productId)'>
																	<div class="text ">
																		<s:text name="NonClaim.LossEstimate100%-OrigCurr" />
																	</div>
																</s:if>
																<s:if test='"1".equals(productId)'>
																	<div class="text ">
																		<s:text name="Claim.LossEstimate100%-OrigCurr" />
																	</div>
																</s:if>
																<s:if test='"2".equals(productId)'>
																	<div class="text ">
																		<s:text name="proClaim.LossEstimate100%-OrigCurr" />
																	</div>
																</s:if>
																<div class="tbox">
																	<s:if test="claim_No==null || claim_No==''">
																		<s:textfield name="loss_Estimate_Orig_Curr"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="FindOurShare()" maxlength="27" />
																	</s:if>
																	<s:else>
																		<s:textfield name="loss_Estimate_Orig_Curr"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="FindOurShare()" readonly="true" />
																	</s:else>
																</div>
															</div>
															<div class="textfield">
																<s:if test='"3".equals(productId)'>
																	<div class="text ">
																		<s:text name="NonClaim.LossEstimateOurshare-OrigCurr" />
																	</div>
																</s:if>
																<s:if test='"1".equals(productId)'>
																	<div class="text ">
																		<s:text name="Claim.LossEstimateOurshare-OrigCurr" />
																	</div>
																</s:if>
																<s:if test='"2".equals(productId)'>
																	<div class="text ">
																		<s:text name="proClaim.LossEstimateOurshare-OrigCurr" />
																	</div>
																</s:if>
																<div class="tbox">
																	<s:textfield name="loss_Estimate_Our_share_Orig_Curr" id="loss_Estimate_Our_share_Orig_Curr" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" />
																</div>
															</div>
															 <div class="textfield">
																<div class="text ">
																	<s:text name="claim.recordFeess" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="recordFees" id="recordFees" value="recordFees==null?'No':recordFees" onclick="funRecView(this.value);"/>
																</div>
															</div>
															 <div class="textfield">
															 </div>
															<div class="textfield" id="si" style="display:none">
																<div class="text ">
																	<s:text name="claim.surveyeradjuster" />
																</div>
																<div class="tbox">
																	<s:textfield name="surveyorAdjesterPerOC" id="surveyorAdjesterPerOC" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="getsurveyorOurShare()"/>
																</div>
															</div>
															<div class="textfield" id="sio" style="display:none">
																<div class="text ">
																	<s:text name="claim.surveyeradjusterouershare" />
																</div>
																<div class="tbox">
																	<s:textfield name="surveyorAdjesterOurShareOC" id="surveyorAdjesterOurShareOC" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssStyle="text-align:right;"  />
																</div>
															</div>
															<div class="textfield" id="so" style="display:none">
																<div class="text ">
																	<s:text name="claim.otherprofessional" />
																</div>
																<div class="tbox">
																	<s:textfield name="otherProfessionalPerOc" id="otherProfessionalPerOc" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="getotherProfessional()" />
																</div>
															</div>
															<div class="textfield" id="otheroc" style="display:none">
																<div class="text " >
																	<s:text name="claim.otherprofessionaloc" />	
																</div>
																<div class="tbox">
																	<s:textfield name="professionalOurShareOc" id="professionalOurShareOc" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssStyle="text-align:right;"  />
																</div>
															</div>
															<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
																 <div class="textfield">
																	<div class="text ">
																		<s:text name="claim.recordibnros" />
																	</div>
																	<div class="tbox">
																		<s:radio list="#{'Yes':'Yes','No':'No'}" name="recordIbnr"  id="recordIbnr"  onclick="funRecordView(this.value)" value="recordIbnr==null?'No':recordIbnr" />
																	</div>
																</div>
																<div class="textfield">
																</div>
															</s:if>
															<s:else>
															<s:hidden name="recordIbnr"  id="recordIbnr" value="No"/>
															</s:else>
															<div class="textfield" id="ib" style="display:none">
																<div class="text ">
																	<s:text name="claim.ibnroc" />
																</div>
																<div class="tbox">
																	<s:textfield name="ibnrPerOc" id="ibnrPerOc" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="getIbnrValue()" />
																</div>
															</div>
															<div class="textfield" id="ibos" style="display:none">
																<div class="text ">
																	<s:text name="claim.ibnrourShare" />
																</div>
																<div class="tbox">
																	<s:textfield name="ibnrOurShareOc" id="ibnrOurShareOc" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionClaim(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssStyle="text-align:right;"  />
																</div>
															</div>
															
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.riRecovery" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="ri_Recovery" id="ri_cession" value="%{ri_Recovery==null?'Yes':ri_Recovery}"  onclick="RecoveryScript(this.value)"  />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.createdby" />
																</div>
																<div class="tbox">
																	<s:textfield name="created_by" value="%{#session.UserId}" readonly="true" cssClass="inputBox" />
																</div>
															</div>
															<br class="clear" />
														</div>
														<s:if test='!"3".equals(productId)'>
														<s:if test='"1".equals(productId)'>
														<div class="textfield">
															<div class="text">
																<s:text name="label.departmentClass" />
															</div>
															<div class="tbox">
																<s:textfield name="departmentName" id="departmentName" cssClass="inputBox" readonly="true"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.subClass" />
															</div>
															<div class="tbox">
																<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" multiple="true" name="subProfitId" id="subProfitId" cssClass="inputBoxS"   disabled='%{(contNo != "" && contNo != null)?true:false}' headerKey="ALL" headerValue="ALL" />
															</div>
														</div> 
														</s:if>
														<s:if test='"2".equals(productId)'>
														<div class="textfield">
															<div class="text">
																<s:text name="label.departmentClass" />
															</div>
															<div class="tbox">
															<s:select name="claimdepartId" id="claimdepartId" list="predepartIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  onchange="getAjax(this.value,'presubclass');"/>
															
																<%--<s:textfield name="departmentName" id="departmentName" cssClass="inputBox" readonly="true"/>--%>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.subClass" />
															</div>
															<div class="tbox" id="presubclass">
																<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" multiple="true" name="subProfitId" id="subProfitId" cssClass="inputBoxS"    headerKey="ALL" headerValue="ALL" />
															</div>
														</div> 
														</s:if>
														</s:if>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="claim.remarks" />
															</div>
															<div class="textfieldA75">
																<s:textarea cssClass="inputBoxA" id="remarks" name="remarks" cssStyle="width: 100%;" rows="3" />
																<br />
																<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
															</div>
															<br class="clear" />
														</div>
														<div class="textfield"  id="reopen_DateId" style="display:none">
																<div class="text ">
																	<s:text name="claim.reopenDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="reopen_Date" id="reopen_Date" cssClass="inputBox" />
																</div>
															</div>
															<div class="textfield"  id="reputed_DateId" style="display:none">
																<div class="text ">
																	<s:text name="claim.repudetDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="reputed_Date" id="reputed_Date" cssClass="inputBox" />
																</div>
															</div>
															
													</div>
												</div>
											</div>
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
												<div class="boxcontent">
													<div class="panel panel-primary">
														<div class="panel-heading">
															<s:text name="Claim Accumulation" />
														</div>
														<div class="panel-body">
															<div class="boxcontent">
																<div class="textfield">
																<div class="text ">
																	<s:text name="claim.riskCode" />
																</div>
																<div class="tbox">
																	<s:textfield name="risk_Code" cssClass="inputBox" cssStyle="text-align:left;" maxlength="30" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.eventCode" />
																</div>
																<div class="tbox">
																	<s:textfield name="event_Code" cssClass="inputBox" cssStyle="text-align:left;" maxlength="30" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.aggregateCode" />
																</div>
																<div class="tbox">
																	<s:textfield name="accumulation_Code" cssClass="inputBox" cssStyle="text-align:left;" maxlength="30" />
																</div>
															</div>
															</div>
														</div>
													</div>
												
												</div>
											</s:if>
											<s:else>
												<s:hidden name="risk_Code"/>
												<s:hidden name="event_Code"/>
												<s:hidden name="accumulation_Code"/>
											</s:else>
											<div class="boxcontent" align="center">
												<s:if test='"claimDisplay".equals(claimDisplay) ||"CD".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
												</s:if>
												<s:elseif test='"claimDisplaypayment".equals(claimDisplay) ||"CP".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
												</s:elseif>
												<s:else>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
												</s:else>
												<s:if test='"2".equals(productId)'>
													<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="destroyPopUps();oncheck()" /> &nbsp;&nbsp;&nbsp;
												</s:if>
												<s:else>
													<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="destroyPopUps();oncheck1()" /> &nbsp;&nbsp;&nbsp;
												</s:else>
												<s:if test='!"claimDisplay".equals(claimDisplay) && !"claimDisplaypayment".equals(claimDisplay)'>
													<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="destroyPopUps();CancelFunction()" />
												</s:if>
											</div>
											
											<s:hidden name="loss_Estimate_Revised_Orig_Curr" />
											<s:hidden name="paid_Amount_Orig_curr" />
											<br class="clear" />
											<br class="clear" />
											<br class="clear" />
										</s:if>
										<s:if test='!"Amode".equals(path)'>
											<s:if test='!"Dmode".equals(path)'>
												<div class="boxcontent" align="center">
													<s:if test="#session.IntialMode.equals('IntialMode')">
														<s:hidden name="loss_Estimate_Our_share_Orig_Curr" />
														<s:hidden name="loss_Estimate_Orig_Curr" />
														<s:hidden name="loss_Estimate_Revised_Orig_Curr" />
														<s:hidden name="paid_Amount_Orig_curr" />
														<s:if test='"claimDisplay".equals(claimDisplay) ||"CD".equals(claimMasterMode)'>
															<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
														</s:if>
														<s:elseif test='"claimDisplaypayment".equals(claimDisplay) ||"CP".equals(claimMasterMode)'>
															<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
														</s:elseif>
														<s:else>
														<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="BackFunction()" />
														</s:else>
													</s:if>
												</div>
											</s:if>
										</s:if>
										<s:if test='"Bmode".equals(path)'>
										<s:if test='"list".equals(paymentFlag) '>
										<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="label.reservePosition" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<display:table name="RevisionList" pagesize="5" requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set var="myrow" value="#attr.record" />
																<display:column sortable="true" style="text-align:right;" format="{0,number,0,000.00}" title="Reserve Id" property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Date" property="cliam_update_Date" />
																<display:column sortable="true"
																	style="text-align:center;" title="View">
																	<a href="#" onclick="ViewReserve()" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
															</display:table>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<br />
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.ClaimPayment" />
														<span class="pullRight">
															<input type="button"  value="New Payment"   class="btn btn-sm btn-warning" onClick="EditPayment('','new')" />
														</span>
													</div>
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<display:table name="ClaimPayment" pagesize="5" requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set name="myrow" value="#attr.record"/>
																<display:column sortable="true" style="text-align:center;" title="Reserve Id" property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Payment No." property="claimPaymentNo" />
																<display:column sortable="true" style="text-align:center;" title="Client Ref No. " property="payment_Request_No" />
																<display:column sortable="true" style="text-align:center;" title="Payment Date" property="date" />
																<%-- <s:if test='"3".equals(productId)'>
																	<display:column sortable="true" style="text-align:right;" title="Net Claim Amount OC" property="paid_Amount_Orig_curr" />
																</s:if>
																<s:else>--%>
																	<display:column sortable="true" style="text-align:right;" title="Total Paid Amount - Our Share - OC" property="paid_Amount_Orig_curr" />
																<%--</s:else> --%>
																<display:column sortable="true"
																	style="text-align:center;" title="Edit">
																	<s:if test='!"N".equalsIgnoreCase(#myrow.allocatedYN) && "Y".equalsIgnoreCase(#myrow.transOpenperiodStatus) &&( "Open".equalsIgnoreCase(#myrow.status_of_claim) || "Reopened".equalsIgnoreCase(#myrow.status_of_claim))'>
																		<a href="#" onclick="EditPayment('${record.claimPaymentNo}','edit')" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">Edit</a>
																	</s:if>
																</display:column>
																<display:column sortable="true"
																	style="text-align:center;" title="Payment View">
																	<a href="#" onclick="EditPayment('${record.claimPaymentNo}','view')" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
																<display:column sortable="true"
																	style="text-align:center;" title="Settlement Status">
																	<a href="#" onclick="ViewPayment('${record.claimPaymentNo}')" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
																<display:column sortable="false" style="text-align:center;" title="Claim Auth. Form">
																	 <a href="#" onclick="ViewClaimAuthForm('<s:property value="proposal_No"/>','${record.claimPaymentNo}','<s:property value="ceding_company_Name"/>','<s:property value="broker_Name"/>','claimPayment');" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">
																		<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Document Details" width="12" height="17">
																	</a>
																</display:column> 
																<display:column sortable="false" style="text-align:center;" title="Document">
																	 <a href="#" class="" title="Document" onclick="getDocList('<s:property value="proposal_No"/>','${record.claimPaymentNo}','<s:property value="ceding_company_Name"/>','<s:property value="broker_Name"/>','claimPayment');">
																		<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Document Details" width="12" height="17">
																	</a>
																</display:column> 
															</display:table>
															<br class="clear" />
														</div>
												</div>
											</div>
											</s:if>
											<s:if test='"new".equals(paymentFlag) || "edit".equals(paymentFlag) || "view".equals(paymentFlag)'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.ClaimPayment" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
														<div class="textfield">
																<div class="text ">
																	<s:text name="claim.paymentDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="date" id="date" cssClass="inputBox" disabled='%{("Y".equals(disabledFields) ||"view".equals(paymentFlag)) ?true:false}'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.paymenttype" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Interim':'Interim','Final':'Final'}" name="paymentType" id="paymentType" disabled='%{("view".equals(paymentFlag) || "edit".equals(paymentFlag)) ?true:false}'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.bkrCedingCoRefNo" />
																</div>
																<div class="tbox">
																	<s:textfield name="payment_Request_No" cssClass="inputBox" maxlength="100" disabled='%{"view".equals(paymentFlag) ?true:false}'/>
																</div>
															</div> 
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.paymentReference" />
																</div>
																<div class="tbox">
																	<s:textfield name="payment_Reference" cssClass="inputBox" maxlength="100" disabled='%{"view".equals(paymentFlag) ?true:false}'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.claimPaidOurShareOC" />
																</div>
																<div class="tbox">
																	<s:textfield name="paid_claim_os" id="paid_claim_os" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssClass="inputBox" maxlength="30" onchange="calculateTotalPaid()" value='%{paid_claim_os==null?"0":paid_claim_os}'  cssStyle="text-align:right;" disabled='%{("Y".equals(disabledFields) ||"view".equals(paymentFlag)) ?true:false}'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.surveyorFeeOurShareOC" />
																</div>
																<div class="tbox">
																	<s:textfield name="surveyor_fee_os" id="surveyor_fee_os" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  cssClass="inputBox" maxlength="100" onchange="calculateTotalPaid()" value='%{surveyor_fee_os==null?"0":surveyor_fee_os}'  cssStyle="text-align:right;" disabled='%{("Y".equals(disabledFields) ||"view".equals(paymentFlag)) ?true:false}'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.otherProfessionalFeeOurShareOC" />
																</div>
																<div class="tbox">
																	<s:textfield name="other_prof_fee_os" id="other_prof_fee_os" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  cssClass="inputBox" maxlength="100" onchange="calculateTotalPaid()" value='%{other_prof_fee_os==null?"0":other_prof_fee_os}' cssStyle="text-align:right;" disabled='%{("Y".equals(disabledFields) ||"view".equals(paymentFlag)) ?true:false}'/>
																</div>
															</div>
															<s:if test='"3".equals(productId)'>
															<s:hidden name="reinstatementPremium" id="reinstatementPremium"/>
																<div class="textfield">
																	<div class="text ">
																		<s:text name="claim.reinsttype" />
																	</div>
																	<div class="tbox">
																		<s:select list="#{'MDP':'On M&D Premium','ADP':'On Adjusted Premium','FDP':'On Final Adjusted Premium','NA':'Not Applicable'}" name="reinstType" id="reinstType" cssClass="inputBoxS" headerKey="removed" headerValue="---Select---" disabled='%{("Y".equals(disabledFields) ||"view".equals(paymentFlag)) ?true:false}' onchange="getReinsValue();"/>
																	
																	</div>
																</div>
																<div class="textfield" >
																	<div class="text ">
																		<s:text name="claim.reinstpremium" />
																	</div>
																	<div class="tbox">
																		<s:textfield name="reinstPremiumOCOS" id="reinstPremiumOCOS" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align:right;float:left;width:250px"  maxlength="27" onchange="calculateTotalPaid()"  value='%{reinstPremiumOCOS==null?"0":reinstPremiumOCOS}' disabled='%{"view".equals(paymentFlag) ?true:false}'/>
																		<span id="reinsDisable" style="display:none">
																		&nbsp;<input type="button"  class="btn btn-sm btn-info" size="2"  value="..." onclick="reinstatementDetails('<s:property value="paymentFlag"/>');" cssStyle="text-align:right;width: 45%; float:left;" />
																		</span>
																	</div>
																</div>
															</s:if>
															
															
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.totalClaimsPaidOurShareOC" />
																</div>
																<div class="tbox">
																	<s:textfield name="paid_Amount_Orig_curr" onkeyup="javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="27" readonly="true" disabled='%{("view".equals(paymentFlag)) ?true:false}' />
																</div>
															</div>
															
															<br class="clear" />
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="claim.remarks" />
															</div>
															<div class="textfieldA75">
																<s:textarea cssClass="inputBoxA" id="remarks" name="remarks" cssStyle="width: 100%;" rows="3" disabled='%{("view".equals(paymentFlag)) ?true:false}'/>
																<br />
																<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											</s:if>
											<div class="boxcontent" align="center">
												
												<s:if test='"new".equals(paymentFlag) || "edit".equals(paymentFlag) || "view".equals(paymentFlag)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();Bmode()" /> &nbsp;&nbsp;&nbsp;
												</s:if>
												<s:else>
													<s:if test='"claimDisplay".equals(claimDisplay)||"CD".equals(claimMasterMode)'>
														<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
													</s:if>
													<s:elseif test='"claimDisplaypayment".equals(claimDisplay)||"CP".equals(claimMasterMode)'>
														<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
													</s:elseif>
													<s:else>
														<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
													</s:else>
												</s:else>
												
												
												
												
												<s:if test='"new".equals(paymentFlag) || "edit".equals(paymentFlag)'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="destroyPopUps();disableForm(this.form,false,'');oncheck3()" />
												
												<s:hidden name="claimPaymentNo" id="claimPaymentNo"/>
												</s:if>
												&nbsp;&nbsp;&nbsp;
												<s:if test='!"claimDisplay".equals(claimDisplay) && !"claimDisplaypayment".equals(claimDisplay)'>
													<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="destroyPopUps();CancelFunction()" />
												</s:if>
												<s:hidden name="loss_Estimate_Orig_Curr" />
												<s:hidden name="loss_Estimate_Revised_Orig_Curr" />
												<s:hidden name="currecny" id="currecny" />
												<%--<s:hidden name="currencyName" id="currencyName" />--%>
												<s:hidden name="sample" value="%{ClaimList.get(0).getDate()}" />
												
												<s:hidden name="departmentName" id="departmentName" />
												<s:hidden name="disabledFields" id="disabledFields" />
											</div>
											<br class="clear" />
											<br class="clear" />
											<br class="clear" />
										</s:if>
										<s:if test='"Cmode".equals(path)'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="label.reservePosition" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<display:table name="RevisionList" pagesize="5" requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set var="myrow" value="#attr.record" />
																<display:column sortable="true" style="text-align:right;" format="{0,number,0,000.00}" title="Reserve Id" property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Date" property="cliam_update_Date" />
																<display:column sortable="true"
																	style="text-align:center;" title="View">
																	<a href="#" onclick="ViewReserve()" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
																</display:column>
															</display:table>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<br />
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														&nbsp;&nbsp;&nbsp;
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="claim.reverseYN" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="reverseClaimYN" onclick="toggleReverseClaimYN(this.value)" id="reverse" />
																</div>
															</div>
															<div class="textfield">
															</div>
															<div id="closedId" style="display: none;">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="claim.closeYN" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="closeClaimYN" id="closeClaimYN" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.closedDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="claim_closed_Date" id="claim_closed_Date" cssClass="inputBox" />
																</div>
															</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" id="reserveId" style="display: none;">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="label.claimsReserveUpdation" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.updateReference" />
																</div>
																<div class="tbox">
																	<s:textfield cssClass="inputBox" name="update_Reference"  maxlength="100" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																</div>
																<div class="tbox">
																</div>
															</div>
															<br class="clear" />
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.lossEstimatoueShare" />
																</div>
																<div class="tbox">
																	<s:textfield cssClass="inputBox" name="update_Rivised_percentage" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="27" onchange="getCalculation('update');getTotalReserved();"/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.lossEstimateRevisedOC" />
																</div>
																<div class="tbox">
																	<s:textfield cssClass="inputBox" name="update_Rivised_original_Cur" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="27" onchange="getTotalReserved();"/>
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.updaterecordFeess" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="updaterecordFees"  id="updaterecordFees"  value="updaterecordFees==null?'No':updaterecordFees" onclick="funView(this.value)" />
																</div>
															</div>
															<div class="textfield">
															</div>
															<div class="textfield" id="is" style="display:none">
																<div class="text ">
																	<s:text name="claim.surveyeradjuster" />
																</div>
																<div class="tbox">
																	<s:textfield name="updatesurveyorAdjesterPerOC" id="updatesurveyorAdjesterPerOC" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" onchange="getCalculation('surveyor');getTotalReserved();"/>
																</div>
															</div>
															<div class="textfield" id="ios" style="display:none">
																<div class="text ">
																	<s:text name="claim.surveyeradjusterouershare" />
																</div>
																<div class="tbox">
																	<s:textfield name="updatesurveyorAdjesterOurShareOC" id="updatesurveyorAdjesterOurShareOC" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssClass="inputBox" cssStyle="text-align:right;"  onchange="getTotalReserved();"/>
																</div>
															</div>
															<div class="textfield" id="os" style="display:none">
																<div class="text ">
																	<s:text name="claim.otherprofessional" />
																</div>
																<div class="tbox">
																	<s:textfield name="updateotherProfessionalPerOc" id="updateotherProfessionalPerOc" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssStyle="text-align:right;"  onchange="getCalculation('other');getTotalReserved();"/>
																</div>
															</div>
															<div class="textfield" id="oos" style="display:none">
																<div class="text " >
																	Other Professional Fee - Our Share- OC		
																</div>
																<div class="tbox">
																	<s:textfield name="updateprofessionalOurShareOc" id="updateprofessionalOurShareOc" cssClass="inputBox" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssStyle="text-align:right;"  onchange="getTotalReserved();"/>
																</div>
															</div>
															<s:if test='"RI02".equals(#session.SOURCE_CODE)'>														
															 <div class="textfield">
																<div class="text ">
																	<s:text name="claim.updaterecordibnros" />
																</div>
																<div class="tbox">
																	<s:radio list="#{'Yes':'Yes','No':'No'}" name="updaterecordIbnr"  id="updaterecordIbnr"  value="updaterecordIbnr==null?'No':updaterecordIbnr" onkeyup="javascript:this.value=Comma(this.value)" onclick="funUpdateRecordView(this.value)" />
																</div>
															</div>
															<div class="textfield">
															</div>
															</s:if>
															<s:else>
															<s:hidden name="updaterecordIbnr"  id="updaterecordIbnr" value="No"/>
															</s:else>
															<div class="textfield" id="uib" style="display:none">
																<div class="text ">
																	<s:text name="claim.ibnroc" />
																</div>
																<div class="tbox">
																	<s:textfield name="updateibnrPerOc" id="updateibnrPerOc" cssClass="inputBox" cssStyle="text-align:right;" onchange="getCalculation('ibnr');" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"/>
																</div>
															</div>
															<div class="textfield" id="uibos" style="display:none">
																<div class="text ">
																	<s:text name="claim.ibnrourShare" />
																</div>
																<div class="tbox">
																	<s:textfield name="updateibnrOurShareOc" id="updateibnrOurShareOc" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssClass="inputBox" cssStyle="text-align:right;"  />
																</div>
															</div>

															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.totalReserveOSOC" />
																</div>
																<div class="tbox">
																	<s:textfield name="totalReserveOSOC" id="totalReserveOSOC" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" cssClass="inputBox" cssStyle="text-align:right;"  readonly="true"/>
																</div>
															</div>
															<div class="textfield">
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.reservePositionDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="reservePositionDate" id="reservePositionDate" cssClass="inputBox" />
																</div>
															</div>
															<div class="textfield">
																<div class="text ">
																	<s:text name="claim.reserveUpdationDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="cliam_update_Date" id="cliam_update_Date" cssClass="inputBox" />
																</div>
															</div>
															<br class="clear" />
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="claim.remarks" />
															</div>
															<div class="textfieldA75">
																<s:textarea cssClass="inputBoxA" id="remarks" name="remarks" cssStyle="width: 100%;" rows="3" />
																<br />
																<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
															</div>
															<br class="clear" />
														</div>
														</div>
													</div>
												</div>
											
											<div class="boxcontent" align="center">
												<s:if test='"claimDisplay".equals(claimDisplay) ||"CD".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
												</s:if>
												<s:elseif test='"claimDisplaypayment".equals(claimDisplay) ||"CP".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
												</s:elseif>
												<s:else>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
												</s:else>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="destroyPopUps();reserveCheck()" />
												&nbsp;&nbsp;&nbsp;
												<s:if test='!"claimDisplay".equals(claimDisplay) && !"claimDisplaypayment".equals(claimDisplay)'>
													<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="destroyPopUps();CancelFunction()" />
												</s:if>
												<s:hidden name="loss_Estimate_Our_share_Orig_Curr" />
												<s:hidden name="loss_Estimate_Orig_Curr" />
												<s:hidden name="loss_Estimate_Revised_Orig_Curr" />
												<s:hidden name="paid_Amount_Orig_curr" />
												<s:hidden name="osAmt" />
												<br class="clear" />
												<br class="clear" />
												<br class="clear" />
												<s:hidden name="sample" value="%{ClaimList.get(0).getDate()}" />
											</div>
										</s:if>
										<s:if test='"Dmode".equals(path)'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.ClaimReview" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<display:table name="claimReview" pagesize="5" requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<display:column sortable="true" style="text-align:center;" title="Sl. No." property="SNo" />
																<display:column sortable="true" style="text-align:center;" title="Review Date" property="review_Date" />
																<display:column sortable="true" style="text-align:center;" title="Review Done By" property="review_Done_By" />
																<display:column sortable="true" style="text-align:left;" title="Remarks" property="remarks" maxLength="75" />
															</display:table>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.ClaimReview" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="claim.reviewDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="review_Date" id="review_Date" cssClass="inputBox" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="claim.reviewDoneBy" />
																</div>
																<div class="tbox">
																	<s:textfield name="review_Done_By" cssClass="inputBox" maxlength="100" />
																</div>
															</div>
															<br class="clear" />
														</div>
														<div class="boxcontent">
															<div class="textfieldA25">
																<s:text name="claim.remarks" />
															</div>
															<div class="textfieldA75">
																<s:textarea cssClass="inputBoxA" id="remarks" name="remarks" cssStyle="width: 100%;" rows="3" />
																<br />
																<span class="textAreaRemaining"><label id="remarks_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<s:if test='"claimDisplay".equals(claimDisplay) ||"CD".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethodclaim()" /> &nbsp;&nbsp;&nbsp;
												</s:if>
												<s:elseif test='"claimDisplaypayment".equals(claimDisplay) ||"CP".equals(claimMasterMode)'>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod1()" /> &nbsp;&nbsp;&nbsp;
												</s:elseif>
												<s:else>
													<input type="button" value="Back" class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
												</s:else>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="destroyPopUps();ClaimReviewInsert()" /> &nbsp;&nbsp;&nbsp;
												<s:if test='!"claimDisplay".equals(claimDisplay) && !"claimDisplaypayment".equals(claimDisplay)'>
													<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="destroyPopUps();CancelFunction()" />
												</s:if>
												<s:hidden name="loss_Estimate_Our_share_Orig_Curr" />
												<s:hidden name="loss_Estimate_Orig_Curr" />
												<s:hidden name="loss_Estimate_Revised_Orig_Curr" />
												<s:hidden name="paid_Amount_Orig_curr" />
											</div>
											<br class="clear" />
											<br class="clear" />
											<br class="clear" />
										</s:if>
									
									<s:hidden name="contarctno" id="contarctno" value="%{policy_Contract_No}" />
									<s:hidden name="proposal_No" id="proposal_No" />
									<s:hidden name="layerNo" id="layerNo" />
									<s:if test='claim_No!=null'>
										<s:hidden name="claim_No" id="claim_No"/>
									</s:if>
									<s:hidden name="menuId" id="menuId" />
									<!--<s:hidden name="osAmt"/>
						-->
									<s:hidden name="amendId" />
									<s:hidden name="from" value="%{from}" id="from"/>
									<s:hidden name="to" value="%{to}" id="to"/>
									<s:hidden name="signed_Share" value="%{signed_Share}" />
									<s:hidden name="sumInsOSOC" value="%{sumInsOSOC}" />
									<s:hidden name="sumInsOSDC" id="sumInsOSDC"/>
									<s:hidden name="cashLossOSOC" id="cashLossOSOC"/>
									<s:hidden name="cashLossOSDC" id="cashLossOSDC"/>
									<s:hidden name="revSumOfPaidAmt" id="revSumOfPaidAmt"/>
									<s:hidden name="inception_Date" id="inception_Date"/>
									<s:hidden name="claimDisplay" id="claimDisplay"/>
									<s:hidden name="oldClaimNumber" id="oldClaimNumber"/>
									<s:hidden name="reserveCount" id="reserveCount"/>
									<s:hidden name="proposalType" id="proposalType" />
								<%--	<s:hidden name="consubProfitId" id="consubProfitId"/> --%>
									<s:hidden name="coverLimitAmount" id="coverLimitAmount"/>
									<s:hidden name="signshare" id="signshare" value="%{signed_Share}"/>
									<s:hidden name="basis" id="basis"/>
									<s:hidden name="paymentFlag" id="paymentFlag"/>
									<s:hidden name="claimMasterMode" id="claimMasterMode"/>	
									<s:hidden name="preVal" id="preVal"/>	
									<s:hidden name="multiuserError" id="multiuserError"/>
									<s:hidden name="companyName" id="companyName"/>
									<s:hidden name="brokerName" id="brokerName"/>
									<s:hidden name="Ceding_Company_Code" id="Ceding_Company_Code"/>
									<s:hidden name="acceptenceDate" id="acceptenceDate"/>
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
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }
	function AmodeInit() {
		document.claims.action="<%=request.getContextPath()%>/cliamPageModesClaim.do?BusinessMode=Amode";
		document.claims.submit();
	}
	function Amode() {
		document.claims.action="<%=request.getContextPath()%>/cliamPageModesClaim.do?BusinessMode=Amode";
		document.claims.submit();
	}
	function Bmode() {
		
		//var currecnyName = document.getElementById("currenyName").value;
		document.getElementById("paymentFlag").value = "list";
		document.claims.action='${pageContext.request.contextPath}/cliamPageModesClaim.do?BusinessMode=Bmode';
		//document.claims.action='${pageContext.request.contextPath}/cliamPageModesClaim.do?BusinessMode=Bmode';
		document.claims.submit(); 
		
	}
	function ModifyReserve() {
		document.claims.action="<%=request.getContextPath()%>/cliamPageModesClaim.do?BusinessMode=Cmode";
		document.claims.submit();
	}
	function Cmode() {
		
		document.claims.action='${pageContext.request.contextPath}/cliamPageModesClaim.do?BusinessMode=Cmode';
		document.claims.submit();   
	}
	function Dmode() {
		document.claims.action="<%=request.getContextPath()%>/cliamPageModesClaim.do?BusinessMode=Dmode";
		document.claims.submit(); 
	}
	function BackMethod() {	
		document.claims.action="<%=request.getContextPath()%>/claimInitClaim.do";
		document.claims.submit();
	}
	function BackMethod1() {	
		document.claims.action="<%=request.getContextPath()%>/claimPaymentListClaim.do?flag=claim";
		document.claims.submit();
	}
	 function BackMethodclaim(){
	 document.claims.action="<%=request.getContextPath()%>/claimMasterListClaim.do?flag=claim";
	 document.claims.submit();
	 }
	 
	 function oncheck(){
	 disableAll('Reopened');
	replaceComma(document.claims,'grossLossFGU,loss_Estimate_Orig_Curr,loss_Estimate_Our_share_Orig_Curr,surveyorAdjesterPerOC,surveyorAdjesterOurShareOC,otherProfessionalPerOc,professionalOurShareOc,ibnrPerOc,ibnrOurShareOc,loss_Estimate_Our_share_Orig_Curr,loss_Estimate_Orig_Curr,loss_Estimate_Revised_Orig_Curr,paid_Amount_Orig_curr,paid_claim_os,surveyor_fee_os,other_prof_fee_os,paid_Amount_Orig_curr,reinstPremiumOCOS,netclaimAmountOCOS,osAmt,update_Rivised_percentage,update_Rivised_original_Cur,updatesurveyorAdjesterPerOC,updatesurveyorAdjesterOurShareOC,updateotherProfessionalPerOc,updateprofessionalOurShareOc,updateibnrPerOc,updateibnrOurShareOc,totalReserveOSOC,sumInsOSOC,sumInsOSDC,cashLossOSOC,cashLossOSDC,revSumOfPaidAmt');
      if(document.getElementById("ri_cessionNo").checked){
        	answer=confirm("You have chosen to not cede this transaction to retro contracts.  Do you wish to continue?");
     	if(answer)
     	ClaimDetailsSubmit();
     	else
     	return false;
     	}
    else ClaimDetailsSubmit();
		}
	

	 function oncheck1(){ 
	 disableAll('Reopened');
	 var inception = document.getElementById("from").value; 
	 var expiry = document.getElementById("to").value; 
	 var report = document.getElementById("report_Date").value;
	 var loss = document.getElementById("date_of_Loss").value; 
	 var basis = document.getElementById("basis").value;
	 var check =true;
	 var date1=true,date2=true,date3=true,date4=true;
	 replaceComma(document.claims,'grossLossFGU,loss_Estimate_Orig_Curr,loss_Estimate_Our_share_Orig_Curr,surveyorAdjesterPerOC,surveyorAdjesterOurShareOC,otherProfessionalPerOc,professionalOurShareOc,ibnrPerOc,ibnrOurShareOc,loss_Estimate_Our_share_Orig_Curr,loss_Estimate_Orig_Curr,loss_Estimate_Revised_Orig_Curr,paid_Amount_Orig_curr,paid_claim_os,surveyor_fee_os,other_prof_fee_os,paid_Amount_Orig_curr,reinstPremiumOCOS,netclaimAmountOCOS,osAmt,update_Rivised_percentage,update_Rivised_original_Cur,updatesurveyorAdjesterPerOC,updatesurveyorAdjesterOurShareOC,updateotherProfessionalPerOc,updateprofessionalOurShareOc,updateibnrPerOc,updateibnrOurShareOc,totalReserveOSOC,sumInsOSOC,sumInsOSDC,cashLossOSOC,cashLossOSDC,revSumOfPaidAmt');
      if(document.getElementById("ri_cessionNo").checked){ 
        	answer=confirm("You have chosen to not cede this transaction to retro contracts.  Do you wish to continue?"); 
     	if(answer){
     	check = answer;
     	//ClaimDetailsSubmit1(); 
     	}
     	else 
     	return false; 
     	}
     	if("3"==basis || "1"==basis){
     	if ($.datepicker.parseDate('dd/mm/yy', inception) > $.datepicker.parseDate('dd/mm/yy', loss)) {
     	answer=confirm("Date of Loss is not within the Insured Period.  Do you wish to continue?"); 
     	if(answer){
     	date1 = answer;
     	}else 
     	return false; 
     	}
     	if ($.datepicker.parseDate('dd/mm/yy', loss) > $.datepicker.parseDate('dd/mm/yy', expiry)) {
     	answer=confirm("Date of Loss is not within the Insured Period.  Do you wish to continue?"); 
     	if(answer){
     	date2 = answer;
     	}else 
     	return false; 
     	}
     	if("3"==basis ){
     	if ($.datepicker.parseDate('dd/mm/yy', inception) > $.datepicker.parseDate('dd/mm/yy', report)) {
     	answer=confirm("Report is not within the Insured Period.  Do you wish to continue?"); 
     	if(answer){
     	date3 = answer;
     	}else 
     	return false; 
     	}
     	if ($.datepicker.parseDate('dd/mm/yy', report) > $.datepicker.parseDate('dd/mm/yy', expiry)) {
     	answer=confirm("Report is not within the Insured Period.  Do you wish to continue?"); 
     	if(answer){
     	date4 = answer;
     	}else 
     	return false; 
     	}
     	}
     	}
     	if(check && date1&& date2 && date3 &&date4){
     	ClaimDetailsSubmit1();
     	}
    	else 
     	return false; 
	} 
	 
	function ClaimDetailsSubmit() { 
		var date = document.getElementById("from").value; 
	 	var x = document.getElementById("date_of_Loss").value; 
	 	var orgcurrency = document.getElementById("loss_Estimate_Our_share_Orig_Curr").value; 
	 	var excrate = document.getElementById("exc_Rate").value; 
	 	var suminsured = document.getElementById("cashLossOSDC").value; 
	 	if(x==""){ 
	      confirm("Please Enter Date of Loss."); 
	 	} 
	 	else if(orgcurrency==""){ 
	      confirm("Please Enter Cash Loss-100%-OC."); 
	 	} 
	 	else if(excrate==""){ 
	 		var currecny=document.getElementById("exc_Rate").value; 
	   		confirm("Please select currency and Enter Claim Registration Date"); 	 
	 	} 
	 else{ 
		var t=(orgcurrency/excrate); 
		var s=parseFloat(suminsured.replace(/,/g, '')) 
		var amount=(t<=s); 
		if ($.datepicker.parseDate('dd/mm/yy', date) > $.datepicker.parseDate('dd/mm/yy', x)) {
			var propType = document.getElementById("proposalType").value; 
	 		var test=true;
			if(propType=="R")
      		test= confirm("You are registering a claim for a loss which occurred prior to inception date. Do you wish to continue?");
      	if(test){ 
			if(amount){ 
				var test1= confirm("The loss amount is less than the cash loss limit.  Do you wish to continue?"); 
					if(test1){ 
						ClaimDetailsSubmit1()
						}
		}
		else{
		document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Amode";
		document.claims.submit();
		}
		}
		}
		else{
			if(amount){
				var test1= confirm("The loss amount is less than the cash loss limit.  Do you wish to continue?");
				if(test1){
						document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Amode";
						document.claims.submit();
		}
		}
		else{
		ClaimDetailsSubmit1()
		}
		}
		}
		}
		
	function ClaimDetailsSubmit1() {
		var sur = 0;
		var other =0;
		var ibnr =0;
		var total=0;
		var record =document.claims.recordFees.value;
		var recordibnr=document.claims.recordIbnr.value;
		var loss = document.claims.loss_Estimate_Orig_Curr.value;
		loss =loss.replace(new RegExp(',', 'g'),'');
		if("Yes"==record){
		 sur = document.claims.surveyorAdjesterPerOC.value;
		 other = document.claims.otherProfessionalPerOc.value;
		 sur =sur.replace(new RegExp(',', 'g'),'');
		 other =other.replace(new RegExp(',', 'g'),'');
		}
		if("Yes"==recordibnr){
		 ibnr = document.claims.ibnrPerOc.value;
		 ibnr =ibnr.replace(new RegExp(',', 'g'),'');
		}
		var exchange = document.claims.exc_Rate.value;
		//var sum = document.claims.sumInsOSOC.value;
		var sum = document.claims.coverLimitAmount.value;
		var signshare = document.claims.signshare.value;
		
		sum =sum.replace(new RegExp(',', 'g'),'');
		signshare =signshare.replace(new RegExp(',', 'g'),'');
		sum=(sum*signshare)/100;
		
		exchange =exchange.replace(new RegExp(',', 'g'),'');
		if("Yes"==record && "Yes"==recordibnr){
		if(exchange !="" && loss!="" &&sur!="" &&other!="" && ibnr!="")  {
		loss=((loss/exchange)*100)/100;
		sur=((sur/exchange)*100)/100;
		other=((other/exchange)*100)/100;
		ibnr=((ibnr/exchange)*100)/100;
		}
		}
		else if( "Yes"==recordibnr){
		if(exchange !="" && loss!="" && ibnr!="")  {
		loss=((loss/exchange)*100)/100;
		ibnr=((ibnr/exchange)*100)/100;
		}
		}
		else if("Yes"==record ){
		if(exchange !="" && loss!="" &&sur!="" &&other!="" )  {
		loss=((loss/exchange)*100)/100;
		sur=((sur/exchange)*100)/100;
		other=((other/exchange)*100)/100;
		}
		}
		else if(exchange !="" && loss!="")  {
		loss=((loss/exchange)*100)/100;
		}
		total=parseFloat(loss)+parseFloat(sur)+parseFloat(other)+parseFloat(ibnr);
		 if(total>sum){
		answer=confirm("The loss is being created with a reserve greater than the cover limit for the current class.  Do you wish to continue?");
		if(answer){
		if(""==document.claims.cedentClaimNo.value){
		alert("Please Enter Cedants Claim No");
		}
		else{
			document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Amode";
			document.claims.submit();
			}
			
		}
			}
	else{
		if(""==document.claims.cedentClaimNo.value){
			alert("Please Enter Cedants Claim No");
			}
			else{
			document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Amode";
			document.claims.submit();
			}
		}
	}
	
	function ClaimReserveInsert() {
		disableAll('Reopened');
		replaceComma(document.claims,'paid_Amount_Orig_curr');
		document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Bmode";
		document.claims.submit();
	}
	
	function ClaimUpdationInsert() {
		disableAll('Reopened');
		document.claims.reserveCount.value="";
		replaceComma(document.claims,'grossLossFGU,loss_Estimate_Orig_Curr,loss_Estimate_Our_share_Orig_Curr,surveyorAdjesterPerOC,surveyorAdjesterOurShareOC,otherProfessionalPerOc,professionalOurShareOc,ibnrPerOc,ibnrOurShareOc,loss_Estimate_Our_share_Orig_Curr,loss_Estimate_Orig_Curr,loss_Estimate_Revised_Orig_Curr,paid_Amount_Orig_curr,paid_claim_os,surveyor_fee_os,other_prof_fee_os,paid_Amount_Orig_curr,reinstPremiumOCOS,netclaimAmountOCOS,osAmt,update_Rivised_percentage,update_Rivised_original_Cur,updatesurveyorAdjesterPerOC,updatesurveyorAdjesterOurShareOC,updateotherProfessionalPerOc,updateprofessionalOurShareOc,updateibnrPerOc,updateibnrOurShareOc,totalReserveOSOC,sumInsOSOC,sumInsOSDC,cashLossOSOC,cashLossOSDC,revSumOfPaidAmt');
		document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Cmode";
		document.claims.submit();
	}
	   
	function ClaimReviewInsert() {	
		disableAll('Reopened');
		document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Dmode";
		document.claims.submit();
	}
	function GetExchangeRate() {
    	var ClaimRegDate=document.getElementById("created_Date").value;
    	var Currency=document.getElementById("currecny").value;		
		var URL='<%=request.getContextPath()%>/getExcRateClaim.do?created_Date='+ClaimRegDate+'&Currecny='+Currency+'&dropDown=exc_Rate1';
    	postRequest(URL,'exc_Rate1');		
	}
	function getReserveDate(val){
	if(val!=null && val!=''){
	document.claims.reservePositionDate.value=val;
	}
	}
	function FindOurShare() {
		var sharevalue=document.claims.signed_Share.value;
		var estimatecurrvalue=document.claims.loss_Estimate_Orig_Curr.value;
		estimatecurrvalue=estimatecurrvalue.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*estimatecurrvalue)/100;
		document.claims.loss_Estimate_Our_share_Orig_Curr.value=Comma(signedShare.toFixed(2));
	}

<%--	function OutstandingAmount() {
		//var paidamt=document.claims.paid_Amount_Orig_curr.value;
		//var osamount=<s:property value="osAmt"/>;
		//paidamt=paidamt.replace(new RegExp(',', 'g'),'');
		//osamount=osamount.replace(new RegExp(',', 'g'),'');
		//var outstandAmt=(osamount-paidamt);
		//document.claims.osAmt.value=Comma(outstandAmt.toFixed(2));
	}--%>
	
	function RecoveryScript(value) {
		if(value=="Yes") {
			document.claims.recovery_from.style.display='none';
			document.claims.recovery_from.value='0';
		}else if(value=="No") {
			document.claims.recovery_from.style.display='none';
			document.claims.recovery_from.value='0';
		}
	} 
	
	function TresuryScript(value) {
		if(value=="Yes") {
			document.getElementById('tresEmail').style.display='Inline';
			document.claims.adviceTreasuryEmail.style.display='Inline';
		} else if(value=="No") {
			document.getElementById('tresEmail').style.display='none';	
			document.claims.adviceTreasuryEmail.style.display='none';
		}
	} 
	
	function AdviceUWScript(value) {
		if(value=="Yes") {
			document.getElementById('uwlabel').style.display='block';			
		}
		else if(value=="No") {
			document.getElementById('uwlabel').style.display='none';
		}
	} 
	
	function AdviceMangementScript(value) {
		if(value=="Yes") {
			document.getElementById('malabel').style.display='block';			
		} else if(value=="No") {
			document.getElementById('malabel').style.display='none';			
		}
	} 
		
	function LoadRecoveryScript() {
		document.claims.recovery_from.style.display='none';
		document.claims.ri_Recovery.value='No';
		document.claims.advice_uw_email.style.display='none';
		document.claims.advice_management_email.style.display='none';
	}	
	
	function loadhidevalues() {	 
		document.claims.recovery_from.style.display='none';
		document.claims.ri_Recovery.value='No';
		document.claims.advice_uw_email.style.display='none';
		document.claims.advice_management_email.style.display='none';			
	}
	
	function BackFunction() {
		var menuId=document.getElementById("menuId").value;  
  		document.location='${pageContext.request.contextPath}/InitPortfolio.action?flag=C&menuId='+menuId;
	}
	
	function ViewPayment(transactionNumber){
	 var URL ='${pageContext.request.contextPath}/reserveListClaim.do?mode=payment&contractNo='+<s:property value="policy_Contract_No"/>+'&transactionNumber='+transactionNumber;
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
		var strOpen = window.open(URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		strOpen.focus();
		return false;
		}
	
	
	function EditPayment(transactionNumber,flag){
	document.getElementById("paymentFlag").value = flag;
		document.claims.action='${pageContext.request.contextPath}/cliamPageModesClaim.do?BusinessMode=Bmode&claimPaymentNo='+transactionNumber;
		document.claims.submit(); 
	
	}
	function ViewReserve() {
		var URL ='<%=request.getContextPath()%>/reserveListClaim.do?mode=reserve&contractNo=<s:property value="policy_Contract_No"/>&claimNo=<s:property value="claim_No"/>';
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
		var strOpen = window.open(URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		strOpen.focus();
		return false;
	} 
	
	function commas() {
		if(document.claims.loss_Estimate_Our_share_Orig_Curr.value !=null && document.claims.loss_Estimate_Our_share_Orig_Curr.value !="")
		{
			document.claims.loss_Estimate_Our_share_Orig_Curr.value=Comma(document.claims.loss_Estimate_Our_share_Orig_Curr.value);
		}
		if(document.claims.loss_Estimate_Orig_Curr.value != null && document.claims.loss_Estimate_Orig_Curr.value != "")
		{
			document.claims.loss_Estimate_Orig_Curr.value=Comma(document.claims.loss_Estimate_Orig_Curr.value);
		}
		if(document.claims.loss_Estimate_Revised_Orig_Curr.value != null && document.claims.loss_Estimate_Revised_Orig_Curr.value != "")
		{
			document.claims.loss_Estimate_Revised_Orig_Curr.value=Comma(document.claims.loss_Estimate_Revised_Orig_Curr.value);
			
		}
		if(document.claims.paid_Amount_Orig_curr.value !=null && document.claims.paid_Amount_Orig_curr.value !="")
		{
			document.claims.paid_Amount_Orig_curr.value=Comma(document.claims.paid_Amount_Orig_curr.value);
		}
		if(document.claims.grossLossFGU.value !=null && document.claims.grossLossFGU.value !="")
		{
			document.claims.grossLossFGU.value=Comma(document.claims.grossLossFGU.value);
		}
	}	
	
	<s:if test='"Cmode".equals(path)'>
		<s:if test='"Yes".equals(reverseClaimYN)'>
			toggleReverseClaimYN("Yes");
		</s:if>
		<s:elseif test='"Yes".equals(closeClaimYN)'>
			toggleReverseClaimYN("No");
		</s:elseif>
	</s:if>
	
	function toggleReverseClaimYN(value) {
		if(value=="Yes")
		{	document.claims.closeClaimYN.value="No";
			document.getElementById('reserveId').style.display='block';
			document.getElementById('closedId').style.display='none';
			
		}else if(value=="No")
		{	document.claims.closeClaimYN.value="Yes";
			document.getElementById('reserveId').style.display='none';
			document.getElementById('closedId').style.display='block';
		}
	}
	
	function toggleCloseClaimYN(value) {
	if(document.getElementById("close2").checked)
		{
			document.getElementById("reverse1").checked=true;
			document.getElementById('reserveId').style.display='Inline';
		}else if(document.getElementById("close1").checked)
		{
			document.getElementById("reverse2").checked=true;
			document.getElementById('reserveId').style.display='none';
		}
	}

	function CancelFunction() {
		   var menuId=document.getElementById("menuId").value;  
  		   document.location='${pageContext.request.contextPath}/InitPortfolio.action?flag=C&menuId='+menuId;
 			
	}

	function getViewContractDetails(prodId,proposalNo,amendId) {
		var URL ='';
		if(prodId=='1'){
			URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if(prodId=='2') {
			URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if((prodId=='3')||(prodId=='5')) {
			URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else {
			URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		
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
	

	function NetClaimPremium() {
	<s:if test='"3".equals(productId)'>
		var paidamt=document.claims.paid_Amount_Orig_curr.value;
		var reinspremium=document.claims.reinstPremiumOCOS.value;
		paidamt=paidamt.replace(new RegExp(',', 'g'),'');
		reinspremium=reinspremium.replace(new RegExp(',', 'g'),'');
		var netclaim=(paidamt-reinspremium);
		document.claims.netclaimAmountOCOS.value=Comma(netclaim.toFixed(2));
		</s:if>
	}
		function calculateTotalPaid() {
		//alert();
			var reinsprem='0';
			var paidamt=document.claims.paid_claim_os.value;
			var survfee=document.claims.surveyor_fee_os.value;
			var otherfee=document.claims.other_prof_fee_os.value;
			<s:if test='"3".equals(productId)'>
			 reinsprem=document.claims.reinstPremiumOCOS.value;
			</s:if>
			paidamt=paidamt.replace(new RegExp(',', 'g'),'');
			survfee=survfee.replace(new RegExp(',', 'g'),'');
			otherfee=otherfee.replace(new RegExp(',', 'g'),'');
			reinsprem=reinsprem.replace(new RegExp(',', 'g'),'');
			var netamt=(parseFloat(paidamt)+parseFloat(survfee)+parseFloat(otherfee)-parseFloat(reinsprem));
			document.claims.paid_Amount_Orig_curr.value=Comma(netamt.toFixed(2));
		}
		<s:if test="status_of_claim == '' || status_of_claim == null || #session.Cliam_cliam_no==null">
			disableAll('Reopened');
		</s:if> 
		<s:else>
			disableAll('Open');
		</s:else> 
		function disableAll(status) {
		<s:if test="path=='Amode'">
		//alert(status);
			if(status=="Reopened"){
				document.getElementById('currecny').disabled = false;
				document.getElementById('exc_Rate').disabled = false;
				var radios = document.claims.recordFees;
				for (var i=0, iLen=radios.length; i<iLen; i++) {
				  radios[i].disabled = false;
				} 
				document.getElementById('surveyorAdjesterPerOC').disabled = false;
				document.getElementById('surveyorAdjesterOurShareOC').disabled = false;
				document.getElementById('otherProfessionalPerOc').disabled = false;
				document.getElementById('professionalOurShareOc').disabled = false;
				var radios1 = document.claims.recordIbnr;
				for (var i=0, iLen1=radios1.length; i<iLen1; i++) {
				  radios1[i].disabled = false;
				} 
				document.getElementById('ibnrPerOc').disabled = false;
				document.getElementById('ibnrOurShareOc').disabled = false;
				var radios2 = document.claims.ri_Recovery;
				for (var i=0, iLen2=radios2.length; i<iLen2; i++) {
				  radios2[i].disabled = false;
				} 			
			} else {
				document.getElementById('currecny').disabled = true;
				document.getElementById('exc_Rate').disabled = true;
				var radios = document.claims.recordFees;
				for (var i=0, iLen=radios.length; i<iLen; i++) {
				  radios[i].disabled = true;
				} 
				//document.getElementById("surveyorAdjesterPerOC").setAttribute("disabled", true);
				document.getElementById('surveyorAdjesterPerOC').disabled = true;
				document.getElementById('surveyorAdjesterOurShareOC').disabled = true;
				document.getElementById('otherProfessionalPerOc').disabled = true;
				document.getElementById('professionalOurShareOc').disabled = true;
				var radios1 = document.claims.recordIbnr;
				for (var i=0, iLen1=radios1.length; i<iLen1; i++) {
				  radios1[i].disabled = true;
				} 
				document.getElementById('ibnrPerOc').disabled = true;
				document.getElementById('ibnrOurShareOc').disabled = true;
				var radios2 = document.claims.ri_Recovery;
				for (var i=0, iLen2=radios2.length; i<iLen2; i++) {
				  radios2[i].disabled = true;
				} 
			}
			</s:if>
		}
		function oncheck3(){
		disableAll('Reopened');
		replaceComma(document.claims,'grossLossFGU,loss_Estimate_Orig_Curr,loss_Estimate_Our_share_Orig_Curr,surveyorAdjesterPerOC,surveyorAdjesterOurShareOC,otherProfessionalPerOc,professionalOurShareOc,ibnrPerOc,ibnrOurShareOc,loss_Estimate_Our_share_Orig_Curr,loss_Estimate_Orig_Curr,loss_Estimate_Revised_Orig_Curr,paid_Amount_Orig_curr,paid_claim_os,surveyor_fee_os,other_prof_fee_os,paid_Amount_Orig_curr,reinstPremiumOCOS,netclaimAmountOCOS,osAmt,update_Rivised_percentage,update_Rivised_original_Cur,updatesurveyorAdjesterPerOC,updatesurveyorAdjesterOurShareOC,updateotherProfessionalPerOc,updateprofessionalOurShareOc,updateibnrPerOc,updateibnrOurShareOc,totalReserveOSOC,sumInsOSOC,sumInsOSDC,cashLossOSOC,cashLossOSDC,revSumOfPaidAmt');
	      if(document.getElementById("paymentTypeFinal").checked){
	        	answer=confirm("Payment Type is chosen as Final.  This action will zeroise the reserve and close the claim.  Do you wish to continue?");
	     	if(answer)
	     	ClaimReserveInsert();
	     	else
	     	return false;
	     	}
	    else  ClaimReserveInsert();
	}
	function getvalidation(val){
			var sharevalue=document.claims.signed_Share.value;
			var estimatecurrvalue=document.claims.loss_Estimate_Orig_Curr.value;
			estimatecurrvalue=estimatecurrvalue.replace(new RegExp(',', 'g'),'');
			var signedShare=(estimatecurrvalue*(sharevalue/100));
			if(val<=signedShare){
			alert("Our Share of Gross Loss shall be within the signed share");
			}
	}
	<s:if test='"Amode".equals(path)'>
	funRecView('<s:property value="recordFees"/>');
	</s:if>
	function  funRecView(id){
			if(id=="No" || id==""){
	     		document.getElementById('si').style.display = 'none';
	     		document.getElementById('sio').style.display = 'none';
	     		document.getElementById('so').style.display = 'none';
	     		document.getElementById('otheroc').style.display = 'none';
	       	} 
	       	else if(id=="Yes"){
	       	 	document.getElementById('si').style.display = 'block';
	     		document.getElementById('sio').style.display = 'block';
	     		document.getElementById('so').style.display = 'block';
	     		document.getElementById('otheroc').style.display = 'block';
	       	}
	}
	if("Amode"==('<s:property value="path"/>')){
	funReopenDate('<s:property value="status_of_claim"/>');
	}
	function  funReopenDate(id){
			if(id=="Reopened"){
	     		document.getElementById('reopen_DateId').style.display = 'block';
	     		document.getElementById('reputed_DateId').style.display = 'none';
	       	} else if(id=="Repudiate"){
	     		document.getElementById('reopen_DateId').style.display = 'none';
	     		document.getElementById('reputed_DateId').style.display = 'block';
	       	} 
	       	else {
	       	 	document.getElementById('reopen_DateId').style.display = 'none';
	       	 	document.getElementById('reputed_DateId').style.display = 'none';
	       	}
	}
	<s:if test='"Cmode".equals(path)'>
	funView('<s:property value="updaterecordFees"/>');
	</s:if>
	function  funView(id){
			if(id=="No" ||id=="" ){
	     		document.getElementById('is').style.display = 'none';
	     		document.getElementById('ios').style.display = 'none';
	     		document.getElementById('os').style.display = 'none';
	     		document.getElementById('oos').style.display = 'none';
	       	} 
	       	else if(id=="Yes"){
	       	 	document.getElementById('is').style.display = 'block';
	     		document.getElementById('ios').style.display = 'block';
	     		document.getElementById('os').style.display = 'block';
	     		document.getElementById('oos').style.display = 'block';
	       	}
	}
	$(document).ready(function() {     
	    $('#subProfitId').multiselect({ 
	      	includeSelectAllOption: false,
	        enableFiltering:true,
	        numberDisplayed: 0,
	        enableCaseInsensitiveFiltering: true,
	        onChange: function(element, checked) {
	          var val = $('#subProfitId').val();
	          var val1 =document.getElementById("preVal").value;
	          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
	          $("#subProfitId").multiselect('clearSelection');
	          val = removeElementsWithValue(val, 'ALL');
	          $("#subProfitId").val(val);
	           $("#subProfitId").multiselect("refresh");
	           document.getElementById("preVal").value = '';
	          }
	          else if (val !=null && val[0]=='ALL' ) {
	          	$("#subProfitId").multiselect('clearSelection');
	          	$("#subProfitId").val('ALL');
	          	 $("#subProfitId").multiselect("refresh");
	          	 document.getElementById("preVal").value = 'ALL';
	          }
	          else if(val== null || val[0]==''){
	          $("#subProfitId").multiselect('clearSelection');
	          $("#subProfitId").multiselect("refresh");
	          document.getElementById("preVal").value = '';
	          }
	          /* $("#subProfitId").multiselect('clearSelection');
	          $("#subProfitId").val('ALL');
	          $("#subProfitId").multiselect("refresh"); */
	      	}                     
	    }); 
	    
	     <s:if test='subProfitId!=null && !"".equals(subProfitId)'>	
	 		var uwgrade='<s:property value="subProfitId"/>';
			 var data=uwgrade.replace(/ /g,'');	
		   	 var dataArray=data.split(",");   	 
		   	$("#subProfitId").val(dataArray);
			 $("#subProfitId").multiselect("refresh");
		</s:if>  
		<s:else>
		$("#subProfitId").multiselect('clearSelection');
	          	$("#subProfitId").val('ALL');
	          	 $("#subProfitId").multiselect("refresh");
		</s:else>
	           
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
	<s:if test='"RI02".equals(#session.SOURCE_CODE)'>	
	funUpdateRecordView('<s:property value="updaterecordIbnr"/>');
	function  funUpdateRecordView(id){
	    if(id=="No"){
	     		document.getElementById('uib').style.display = 'none';
	     		document.getElementById('uibos').style.display = 'none';
	       	} 
	       	else if(id=="Yes"){
	       	 	document.getElementById('uib').style.display = 'block';
	       	 	document.getElementById('uibos').style.display = 'block';
	       	 	
	       	}
	}
	funRecordView('<s:property value="recordIbnr"/>');
	function  funRecordView(id){
			if(id=="No"){
	     		document.getElementById('ib').style.display = 'none';
	     		document.getElementById('ibos').style.display = 'none';
	       	} 
	       	else if(id=="Yes"){
	       	 	document.getElementById('ib').style.display = 'block';
	       	 	document.getElementById('ibos').style.display = 'block';
	       	 	
	       	}
	}
	</s:if>
	function getsurveyorOurShare(){
		var sharevalue=document.claims.signed_Share.value;
		var surveyorAdjesterPerOC=document.claims.surveyorAdjesterPerOC.value;
		surveyorAdjesterPerOC=surveyorAdjesterPerOC.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*surveyorAdjesterPerOC)/100;
		document.claims.surveyorAdjesterOurShareOC.value=Comma(signedShare.toFixed(2));
	}
	
	function getsurveyorValidation(val){
		var sharevalue=document.claims.signed_Share.value;
		var surveyorAdjesterPerOC=document.claims.surveyorAdjesterPerOC.value;
		surveyorAdjesterPerOC=surveyorAdjesterPerOC.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*surveyorAdjesterPerOC)/100;
		if(val<=signedShare){
			alert("Our Share of Other Professional Fee shall be within the signed share");
			}
	}
	
	function getotherProfessional(){
		var sharevalue=document.claims.signed_Share.value;
		var otherProfessionalPerOc=document.claims.otherProfessionalPerOc.value;
		otherProfessionalPerOc=otherProfessionalPerOc.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*otherProfessionalPerOc)/100;
		document.claims.professionalOurShareOc.value=Comma(signedShare.toFixed(2));
	}
	
	function getOtherProfessionalValidation(val){
		var sharevalue=document.claims.signed_Share.value;
		var otherProfessionalPerOc=document.claims.otherProfessionalPerOc.value;
		otherProfessionalPerOc=otherProfessionalPerOc.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*otherProfessionalPerOc)/100;
		if(val<=signedShare){
			alert("Our Share of Other Professional Fee shall be within the signed share");
			}
	}
	
	function getIbnrValue(){
		var sharevalue=document.claims.signed_Share.value;
		var ibnrPerOc=document.claims.ibnrPerOc.value;
		ibnrPerOc=ibnrPerOc.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*ibnrPerOc)/100;
		document.claims.ibnrOurShareOc.value=Comma(signedShare.toFixed(2));
	}
	
	function getibnrValidation(val){
		var sharevalue=document.claims.signed_Share.value;
		var ibnrPerOc=document.claims.ibnrPerOc.value;
		ibnrPerOc=ibnrPerOc.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*ibnrPerOc)/100;
		if(val<=signedShare){
			alert("Our Share of Outstanding Loss Reserve (IBNR) shall be within the signed share");
			}
	}
	function getCalculation(id){
	if(id=="update"){
		var sharevalue=document.claims.signed_Share.value;
		var data=document.claims.update_Rivised_percentage.value;
		data=data.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*data)/100;
		document.claims.update_Rivised_original_Cur.value=Comma(signedShare.toFixed(2));
		}
		else if(id=="surveyor"){
		var sharevalue=document.claims.signed_Share.value;
		var data=document.claims.updatesurveyorAdjesterPerOC.value;
		data=data.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*data)/100;
		document.claims.updatesurveyorAdjesterOurShareOC.value=Comma(signedShare.toFixed(2));
		}
		else if(id=="other"){
		var sharevalue=document.claims.signed_Share.value;
		var data=document.claims.updateotherProfessionalPerOc.value;
		data=data.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*data)/100;
		document.claims.updateprofessionalOurShareOc.value=Comma(signedShare.toFixed(2));
		}
		else if(id=="ibnr"){
		var sharevalue=document.claims.signed_Share.value;
		var data=document.claims.updateibnrPerOc.value;
		data=data.replace(new RegExp(',', 'g'),'');
		var signedShare=(sharevalue*data)/100;
		document.claims.updateibnrOurShareOc.value=Comma(signedShare.toFixed(2));
		}
	}
	<%--
	<s:if test='null !=(oldClaimNumber) && "0" !=(oldClaimNumber) &&"" !=(oldClaimNumber) '>
	cedentAlert();
	</s:if>
	<s:elseif test='"0"==(oldClaimNumber)' >
	claimInsert();
	</s:elseif>
	
	
	function cedentAlert(){
	  if(null !=document.claims.oldClaimNumber.value  && "0" !=document.claims.oldClaimNumber.value && "" !=document.claims.oldClaimNumber.value){
	  	answer=confirm("The cedant’s claim entered is already registered under our claim number "+'<s:property value="oldClaimNumber"/>'+".  Do you wish to continue creating a new claim with the same cedant’s claim reference?");
		if(answer){
			claimInsert();
			}
	  }
	}
	
	function claimInsert(){
			disableAll('Reopened');
			document.claims.oldClaimNumber.value="";
			document.claims.action="<%=request.getContextPath()%>/claimInsertActionClaim.do?BusinessMode=Amode";
			document.claims.submit();
	}--%>
	function reserveCheck(){
	document.claims.action="<%=request.getContextPath()%>/reserveCheckClaim.do?BusinessMode=Cmode";
	document.claims.submit();
	}
	if(null !=document.claims.reserveCount.value  && "0" !=document.claims.reserveCount.value && "" !=document.claims.reserveCount.value){
	reserveAlert();
	}else if( "0" ==document.claims.reserveCount.value){
	ClaimUpdationInsert();
	}
	
	
	function reserveAlert(){
	  if(null !=document.claims.reserveCount.value  && "0" !=document.claims.reserveCount.value && "" !=document.claims.reserveCount.value){
	  	answer=confirm("The oustanding loss Reserve for current claim is not equal to Zero.This action will Zeroise the reserve and close the claim.Do you wish to continue.");
		if(answer){
			ClaimUpdationInsert();
			}
	  }
	}
	function addComma(id){
	var paymentFlag='<s:property value="paymentFlag"/>';
	if("Amode"==id){
		document.claims.loss_Estimate_Orig_Curr.value = Comma(document.claims.loss_Estimate_Orig_Curr.value);
		document.claims.loss_Estimate_Our_share_Orig_Curr.value = Comma(document.claims.loss_Estimate_Our_share_Orig_Curr.value);
		document.claims.grossLossFGU.value = Comma(document.claims.grossLossFGU.value);
		if("Yes"==document.claims.recordFees.value){
		document.claims.surveyorAdjesterPerOC.value = Comma(document.claims.surveyorAdjesterPerOC.value);
		document.claims.surveyorAdjesterOurShareOC.value = Comma(document.claims.surveyorAdjesterOurShareOC.value);
		document.claims.otherProfessionalPerOc.value = Comma(document.claims.otherProfessionalPerOc.value);
		document.claims.professionalOurShareOc.value = Comma(document.claims.professionalOurShareOc.value);
		}
		if("Yes"==document.claims.recordIbnr.value){
		document.claims.ibnrPerOc.value = Comma(document.claims.ibnrPerOc.value);
		document.claims.ibnrOurShareOc.value = Comma(document.claims.ibnrOurShareOc.value);
		}
	}
	if("Cmode"==id){
		document.claims.update_Rivised_percentage.value = Comma(document.claims.update_Rivised_percentage.value);
		document.claims.update_Rivised_original_Cur.value = Comma(document.claims.update_Rivised_original_Cur.value);
		if("Yes"==document.claims.updaterecordFees.value){
		document.claims.updatesurveyorAdjesterPerOC.value = Comma(document.claims.updatesurveyorAdjesterPerOC.value);
		document.claims.updatesurveyorAdjesterOurShareOC.value = Comma(document.claims.updatesurveyorAdjesterOurShareOC.value);
		document.claims.updateotherProfessionalPerOc.value = Comma(document.claims.updateotherProfessionalPerOc.value);
		document.claims.updateprofessionalOurShareOc.value = Comma(document.claims.updateprofessionalOurShareOc.value);
		}
		if("Yes"==document.claims.updaterecordIbnr.value){
		document.claims.updateibnrPerOc.value = Comma(document.claims.updateibnrPerOc.value);
		document.claims.updateibnrOurShareOc.value = Comma(document.claims.updateibnrOurShareOc.value);
		}
		document.claims.totalReserveOSOC.value = Comma(document.claims.totalReserveOSOC.value);
		
	}
	if("Bmode"==id && "list"!=paymentFlag){
		document.claims.paid_Amount_Orig_curr.value = Comma(document.claims.paid_Amount_Orig_curr.value);
		document.claims.paid_claim_os.value = Comma(document.claims.paid_claim_os.value);
		document.claims.surveyor_fee_os.value = Comma(document.claims.surveyor_fee_os.value);
		document.claims.other_prof_fee_os.value = Comma(document.claims.other_prof_fee_os.value);
		var val = '<s:property value="productId"/>';
		if(val == '3'){
		document.claims.reinstPremiumOCOS.value = Comma(document.claims.reinstPremiumOCOS.value);
		}
	}
	}
	
	function getTotalReserved(){
	var oustandOSOC=document.claims.update_Rivised_original_Cur.value;
	var surveyorOSOC=document.claims.updatesurveyorAdjesterOurShareOC.value;
	var otherproOSOC=document.claims.updateprofessionalOurShareOc.value;
	//var oustandIBNOSOC=document.claims.updateibnrOurShareOc.value;
	if(oustandOSOC==null || oustandOSOC==''){
	oustandOSOC=0;
	}else{
	oustandOSOC=oustandOSOC.replace(new RegExp(',', 'g'),'');
	}
	if(surveyorOSOC==null ||surveyorOSOC==''){
	surveyorOSOC=0;
	}else{
	surveyorOSOC=surveyorOSOC.replace(new RegExp(',', 'g'),'');
	}
	if(otherproOSOC==null || otherproOSOC==''){
	otherproOSOC=0;
	}else{
	otherproOSOC=otherproOSOC.replace(new RegExp(',', 'g'),'');
	}
	<%--if(oustandIBNOSOC==null || oustandIBNOSOC==''){
	oustandIBNOSOC=0;
	}else{
	oustandIBNOSOC=oustandIBNOSOC.replace(new RegExp(',', 'g'),'');
	}--%>
	var toatal=parseFloat(oustandOSOC)+parseFloat(surveyorOSOC)+parseFloat(otherproOSOC);
	document.claims.totalReserveOSOC.value=Comma(toatal);
	}
	$(function() {
			    	
		    $('#remarks').keyup(function() {
		        update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
		    });	    
		    update_chars_left(500, $('#remarks')[0], $('#remarks_left'));
		    
		    $('#loss_Details').keyup(function() {
		        update_chars_left(500, $('#loss_Details')[0], $('#loss_Details_left'));
		    });	
		        
		    update_chars_left(500, $('#loss_Details')[0], $('#loss_Details_left'));
		    
		    $('#cause_of_Loss').keyup(function() {
		        update_chars_left(500, $('#cause_of_Loss')[0], $('#cause_of_Loss_left'));
		    });	    
		    update_chars_left(500, $('#cause_of_Loss')[0], $('#cause_of_Loss_left'));
		    
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
		
	function reinstatementDetails(paymentFlag){
	var proposal_No = document.getElementById("proposal_No").value;
	var Currency=document.getElementById("currecny").value;
	var layerNo=document.getElementById("layerNo").value;
	var claim_No='<s:property value="claim_No"/>';	
	var val = '<s:property value="productId"/>';
	//var CurrencyName = document.getElementById("currencyName").value;
	var departmentId = document.getElementById("departmentId").value;
	var date =document.getElementById("date").value; 
	var claimPaymentNo = document.getElementById("claimPaymentNo").value; 
			var paid_claim_os = document.getElementById("paid_claim_os").value;
			var surveyor_fee_os = document.getElementById("surveyor_fee_os").value;
			var other_prof_fee_os =  document.getElementById("other_prof_fee_os").value;
			var reinstType = document.getElementById("reinstType").value;
			var departmentName = '<s:property value="departmentName"/>';
			if(date ==''){
			alert("Please Enter Payment Date");
			}
			if(paid_claim_os==''){
			alert("Please Enter Claims Paid - Our Share - OC");
			}
			if(surveyor_fee_os==''){
			alert("Please Enter Surveyor / Adjustor Fee - Our Share - OC");
			}
			if(other_prof_fee_os==''){
			alert("Please Enter Other Professional Fee - Our Share - OC");
			}
			if(reinstType=="removed"){
			alert("Please Select Reinstatement Type");
			}
			if(date !=''&&paid_claim_os!=''&&surveyor_fee_os!=''&&other_prof_fee_os!=''&&reinstType!='removed'){
			document.getElementById("date").disabled=true;
			document.getElementById("paid_claim_os").disabled=true;
			document.getElementById("surveyor_fee_os").disabled=true;
			document.getElementById("other_prof_fee_os").disabled=true;
			document.getElementById("reinstType").disabled=true;
			var URL ='${pageContext.request.contextPath}/reInsPopUpClaim.do?productId='+val+'&proposal_No='+proposal_No+'&currecny='+Currency+'&claim_No='+claim_No+'&layerNo='+layerNo+'&date='+date+'&paid_claim_os='+paid_claim_os+'&surveyor_fee_os='+surveyor_fee_os+'&other_prof_fee_os='+other_prof_fee_os+'&reinstType='+reinstType+"&departmentName="+departmentName+'&departmentId='+departmentId+'&paymentFlag='+paymentFlag+'&claimPaymentNo='+claimPaymentNo;
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
		
		}
	function getDocList(proposalId,tranNo,compName,brokerName,type) {		
			document.getElementById("companyName").value=compName;
			document.getElementById("brokerName").value=brokerName;
			document.claims.action="${pageContext.request.contextPath}/documentUpload.action?proposalNo="+proposalId+"&type="+type+"&tranNo="+tranNo;
		    document.claims.submit();
		} 	
		function getAjax(value,id) {
		var departmentId = document.getElementById("departmentId").value;
		if(value!=""&&departmentId!=value){
	        $.ajax({
	        type: "POST",
	        url: '${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=presubclass',
	        //data:'src_type_id='+val,
	        success: function(data){
	            $('#' + id).html(data);
	            $('#subProfitId').multiselect({ 
	      			includeSelectAllOption: false,
	        		enableFiltering:true,
	        		numberDisplayed: 0,
	        		enableCaseInsensitiveFiltering: true,
	        		onChange: function(element, checked) {
	          		  var val = $('#subProfitId').val();
			          var val1 =document.getElementById("preVal").value;
			          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
			          $("#subProfitId").multiselect('clearSelection');
			          val = removeElementsWithValue(val, 'ALL');
			          $("#subProfitId").val(val);
			           $("#subProfitId").multiselect("refresh");
			           document.getElementById("preVal").value = '';
			          }
			          else if (val !=null && val[0]=='ALL' ) {
			          	$("#subProfitId").multiselect('clearSelection');
			          	$("#subProfitId").val('ALL');
			          	 $("#subProfitId").multiselect("refresh");
			          	 document.getElementById("preVal").value = 'ALL';
			          }
			          else if(val== null || val[0]==''){
			          $("#subProfitId").multiselect('clearSelection');
			          $("#subProfitId").multiselect("refresh");
			          document.getElementById("preVal").value = '';
	          }
	          }                   
	    });
	        }
	        });
	        }
	    }
	editModeStatus();
	function editModeStatus(){
		var val =document.getElementById("multiuserError").value;
		if('error'==val){
		document.getElementById("multiuserError").value ='';
		alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
		}
	}
	<s:if test='"Bmode".equals(path) && "3".equals(productId)  &&("new".equals(paymentFlag) || "edit".equals(paymentFlag)|| "view".equals(paymentFlag))'>
	getReinsValue();
	</s:if>
	function getReinsValue(){
var val = document.getElementById("reinstatementPremium").value;
var reinstType =  document.getElementById("reinstType").value;
if(val=='Y' && reinstType !='NA' &&  reinstType !='removed'){
document.getElementById("reinsDisable").style.display='block';
document.getElementById("reinstPremiumOCOS").disabled=false;
}else if((val=='N' || val=='') && reinstType !='NA' &&  reinstType !='removed'){
document.getElementById("reinsDisable").style.display='none';
document.getElementById("reinstPremiumOCOS").disabled=false;
}
else {
document.getElementById("reinsDisable").style.display='none';
document.getElementById("reinstPremiumOCOS").value="0.00";
document.getElementById("reinstPremiumOCOS").disabled=true;
calculateTotalPaid();
}
}
	<s:if test='"Amode".equals(path)'>
	ridisabled();
	</s:if>
	function ridisabled(){
	document.getElementById("ri_cessionNo").disabled=true;
	document.getElementById("ri_cessionYes").disabled=true;
	}
	
	function cedentNumberDetails(){
	var no = document.getElementById("cedentClaimNo").value;
	var dateofLoss = document.getElementById("date_of_Loss").value;
	var cedingCode = document.getElementById("Ceding_Company_Code").value;
	var claim_No =  '<s:property value="claim_No"/>';	
	var productId = '<s:property value="productId"/>';
	var status = true;
	if(no ==''){
	alert("Please Enter Cedants Claim No");
	status = false;
	}
	if(dateofLoss ==''){
	alert("Please Enter Date of Loss");
	status = false;
	}
	if(status){
	var URL ='${pageContext.request.contextPath}/cedentDetailsClaim.do?cedentClaimNo='+no+'&date_of_Loss='+dateofLoss+'&Ceding_Company_Code='+cedingCode+'&productId='+productId+'&claim_No='+claim_No;
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
	    }
	    return false;
	}
	

function middleMinusRestrictionClaim(txt) {
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


function ViewClaimAuthForm(proposalId,tranNo,compName,brokerName,type){
try{
		var contractNo = document.getElementById('contarctno').value;
		var proposal = document.getElementById('proposal_No').value;
		var layerNo = document.getElementById('layerNo').value;
		var currency = document.getElementById('currecny').value;
	}catch(err){console.log(err)}
		var URL ='${pageContext.request.contextPath}/viewAuthClaim.action?contarctno='+contractNo+'&proposal_No='+proposal+'&layerNo='+layerNo+'&currecny='+currency;
		var windowName = "Claim Auth";
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

	</script>
		</body>
	</html>
