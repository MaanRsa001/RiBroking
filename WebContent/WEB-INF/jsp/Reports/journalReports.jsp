<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
		.textfield25 {
			height: 60px;
		}
	</style>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	 $(function() {
		$( "#reportDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	  </script>														 




	
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="journalform" name="journalform" theme="simple" action="/ReportsAction.do"	method="post">										
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<s:if test='"init".equals(display)'>						
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalReports" />
										<span class="pullRight">
											<input type="button"  value="New Journal"   class="btn btn-sm btn-primary" onClick="callJournal(this.form,'newJournal');" /> 
										</span>
										<br class="clear"/>
									</div>
									<div class="panel-body" style="overflow: auto;">
										<div class="boxcontent" align="center">											
											<display:table name="ReportList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true" style="width:5%;" title="S No"> ${record_rowNum} </display:column>											
												<display:column sortable="true" style="width:19%; text-align: center;" title="Movement ID" property="MOVEMENT_ID" />
												<display:column sortable="true" style="width:19%; text-align: center;" title="UW Year" property="UW_YEAR" />												
												<display:column sortable="true" style="width:19%; text-align: center;" title="Report Date" property="REPORT_DATE" />
												<display:column sortable="true" style="width:19%; text-align: center;" title="Movement Type" property="MOVEMENT_TYPE" />
												<display:column sortable="true" title="Current" style="text-align:center; width:19%;">
														<s:if test='"15".equals(typeId)'>
															<a class="" title="Current" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="callPipelineMoveJvCrnt('<s:property value="%{#myrow.UW_YEAR}" />','<s:property value="%{#myrow.REPORT_DATE}" />','<s:property value="%{#myrow.MOVEMENT_ID}" />');"  alt="View Current" width="12" height="17"></a>															
														</s:if>														
														<s:if test='"16".equals(typeId)'>
															<a class="" title="Current" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="callBookedPremiumCrnt('<s:property value="%{#myrow.UW_YEAR}" />','<s:property value="%{#myrow.REPORT_DATE}" />','<s:property value="%{#myrow.MOVEMENT_ID}" />');"  alt="View Current" width="12" height="17"></a>
														</s:if>
														<s:if test='"17".equals(typeId)'>
															<a class="" title="Current" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="callBookedUPRCrnt('<s:property value="%{#myrow.UW_YEAR}" />','<s:property value="%{#myrow.REPORT_DATE}" />','<s:property value="%{#myrow.MOVEMENT_ID}" />');"  alt="View Current" width="12" height="17"></a>
														</s:if>
														<s:if test='"18".equals(typeId)'>															
															 <a class="" title="Current" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="callPipelineWritPreCrnt('<s:property value="%{#myrow.UW_YEAR}" />','<s:property value="%{#myrow.REPORT_DATE}" />','<s:property value="%{#myrow.MOVEMENT_ID}" />');"  alt="View Current" width="12" height="17"></a>
														</s:if>
												</display:column>												
											</display:table>							
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>											
						</div>
						</s:if>
						<s:elseif test='"new".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:if test='"15".equals(typeId)'>
										<s:text name="label.pipelineMovementJV" />
										</s:if>
										<s:if test='"16".equals(typeId)'>
										<s:text name="label.bookedPremium" />
										</s:if>
										<s:if test='"17".equals(typeId)'>
										<s:text name="label.bookedUPR" />
										</s:if>
										<s:if test='"18".equals(typeId)'>
										<s:text name="label.pipelinedWrittenPremium" />
										</s:if>								
									</div>
									<div class="panel-body" style="overflow: auto;">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Claim.ReportDate" /> &nbsp; <font color="red">*</font>
												</div>
												<div class="tbox">
													<!--  <div class="inputAppend">
														<sj:datepicker name="reportDate" id="reportDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />
													</div>	-->
													
											<s:textfield name="reportDate" id="reportDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)"/>																						
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.product" /> &nbsp; <font color="red">*</font>
												</div>
												<div class="tbox">
													<s:select list="#{'1':'Facultative','2':'Proportional Treaty','3':'Non-Proportional Treaty'}" cssClass="inputBoxS" name="product" id="product" headerKey="" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.Movement.Type" /> &nbsp; <font color="red">*</font>
												</div>
												<div class="tbox">
													<s:radio list="#{'I':'Intreim','F':'Final'}" name="movementType" value='%{movementType==null?"":movementType}' />													
												</div>
											</div>
											<br class="clear"/>
										</div>
										<s:if test='"15".equals(typeId)'>
										<s:hidden name="type" value="BookedUpr" />
										</s:if>
										<s:if test='"16".equals(typeId)'>
										<s:hidden name="type" value="BookedPremium" />
										</s:if>
										<s:if test='"17".equals(typeId)'>
										<s:hidden name="type" value="BookedUpr" />
										</s:if>
										<s:if test='"18".equals(typeId)'>
										<s:hidden name="type" value="" />
										</s:if>
										<div class="boxcontent" align="center">
											<s:if test='"15".equals(typeId)'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="fnCallProcedure(this.form,'callProPipelineMoveJv');" />
											</s:if>
											<s:if test='"16".equals(typeId)'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="fnCallProcedure(this.form,'callProBookedPremium');" />
											</s:if>
											<s:if test='"17".equals(typeId)'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="fnCallProcedure(this.form,'callProBookedUpr');" />
											</s:if>
											<s:if test='"18".equals(typeId)'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="fnCallProcedure(this.form,'callProPipelineWritten');" />
											</s:if>
											&nbsp;&nbsp;&nbsp;
											<s:if test='"15".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=15">Cancel</a>
											</s:if>
											<s:if test='"16".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=16">Cancel</a>
											</s:if>
											<s:if test='"17".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=17">Cancel</a>
											</s:if>
											<s:if test='"18".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=18">Cancel</a>
											</s:if>	
											
										</div>
										
									</div>
								</div>
							</div>							
						</div>
						</s:elseif>
						<s:elseif test='"crnt".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalReports" />										
									</div>
									<div class="panel-body" style="overflow: auto;">
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true"	title="S No"> ${record_rowNum} </display:column>											
												<display:column sortable="true" style="width:25%;" title="Sub Profit Center Name" property="TMAS_SPFC_NAME" />
												<display:column sortable="true" style="width:25%;text-align:center;" title="Product Name" property="TMAS_PRODUCT_NAME" />												
												<display:column sortable="true" style="width:25%;" title="Currency Name" property="CURRENCY_NAME" />
												<display:column sortable="true" style="width:25%;text-align:center;" title="View">
													<s:if test='"15".equals(typeId)'>
												  		<a class="" title="View" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="fnPipelineMovementJVDetails('<s:property value="%{#myrow.SPFCID}" />','<s:property value="%{#myrow.PRODUCT_ID}" />','<s:property value="%{#myrow.CURRENCY_ID}" />','<s:property value="%{#myrow.UW_YEAR}" />');"  alt="View Journal" width="12" height="17"></a>
												  	</s:if>
												  	<s:if test='"16".equals(typeId)'>
												  		<a class="" title="View" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="fnBookedPremiumDetails('<s:property value="%{#myrow.SPFCID}" />','<s:property value="%{#myrow.PRODUCT_ID}" />','<s:property value="%{#myrow.CURRENCY_ID}" />','<s:property value="%{#myrow.UW_YEAR}" />');"  alt="View Journal" width="12" height="17"></a>
												  	</s:if>
												  	<s:if test='"17".equals(typeId)'>
												  		<a class="" title="View" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="fnBookedUPRDetails('<s:property value="%{#myrow.SPFCID}" />','<s:property value="%{#myrow.PRODUCT_ID}" />','<s:property value="%{#myrow.CURRENCY_ID}" />','<s:property value="%{#myrow.UW_YEAR}" />');"  alt="View Journal" width="12" height="17"></a>
												  	</s:if>
												  	<s:if test='"18".equals(typeId)'>
												  		<a class="" title="View" style="cursor: pointer;">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="fnPipelinedWrtnDetails('<s:property value="%{#myrow.SPFCID}" />','<s:property value="%{#myrow.PRODUCT_ID}" />','<s:property value="%{#myrow.CURRENCY_ID}" />','<s:property value="%{#myrow.UW_YEAR}" />');"  alt="View Journal" width="12" height="17"></a>
												  	</s:if>
												</display:column>
											</display:table>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent" align="center">
											<s:if test='"15".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=15">Back</a>
											</s:if>
											<s:if test='"16".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=16">Back</a>
											</s:if>
											<s:if test='"17".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=17">Back</a>
											</s:if>
											<s:if test='"18".equals(typeId)'>
												<a class="btn btn-sm btn-danger"  href="<%=request.getContextPath()%>/getInitReports?typeId=18">Back</a>
											</s:if>											
										</div>										
									</div>
								</div>
							</div>							
						</div>
						</s:elseif>
						<s:elseif test='"bookedUpr".equals(display)'>
						<div class="tablerow">						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.bookedUpr" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:text name="label.date" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="accountDate"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.spc" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="spcName"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.uwYear" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="uwYear"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.currency" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="currencyname"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="Product" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="productName"/> </b>
											<br class="clear"/>
										</div>										
										<br class="clear"/>
										<div class="boxcontent">
											<s:text name="policyType.startDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="start_date"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="policyType.endDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="end_date"/> </b>
										</div>
										<br class="clear"/>
										<div class="boxcontent">											
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.debit" /> </th>
													<th width="25%"> <s:text name="label.credit" /> </th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td> <s:text name="label.bookedUPR" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(bkdUprD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.bookeddeferredAcquisitionCost" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(bkdDefAcqCostC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardBookedUPRBS" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdBkdUprBsC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardBookeddeferredAcquisitionCostBS" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdBkdDefAcqCostBsD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.pipelineUPR" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinUprC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.pipelineAcquisitionCost" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinAcqCostD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardPipelinedUPRBS" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinUprBSD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardPipelineddeferredAcquisitionCostBS" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinDefAcqCostBsC)})}"/> </td>
												</tr>
												<tr>
													<td align="right"> <s:text name="label.total" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{debitTotal})}"/> </td>
													<td align="right"> <s:property value="%{getText('number.format',{creditTotal})}"/> </td>
												</tr>
												</tbody>
											</table>
											<br class="clear"/>
										</div>
										<br/>.
										
										<div class="boxcontent" align="center">
											<a class="btn btn-sm btn-danger" type="button" title="Current" style="cursor: pointer;" onclick="callBookedUPRCrnt('<s:property value="uwYear" />','<s:property value="accountDate" />','<s:property value="movementId" />');">Back</a>
										</div>
									</div>									
								</div>
							</div>											
						</div>
						</s:elseif>
						<s:elseif test='"bookedPremium".equals(display)'>
						<div class="tablerow">						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.bookedPremium" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:text name="label.date" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="accountDate"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.spc" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="spcName"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.uwYear" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="uwYear"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.currency" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="currencyname"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="Product" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="productName"/> </b>	
											<br class="clear"/>
										</div>
										<br class="clear"/>
										<div class="boxcontent">
											<s:text name="policyType.startDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="start_date"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="policyType.endDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="end_date"/> </b>
										</div>
										<br class="clear"/>
										<div class="boxcontent">											
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.debit" /> </th>
													<th width="25%"> <s:text name="label.credit" /> </th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td> <s:text name="label.grossPremium" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(grsPremiumC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.grossPipelinePremium" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(grsPiplinPremiumD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.grossAcquisitionCost" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(grsAcqCostD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.grossPipelineAcquisitionCost" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(grsPiplinAcqCostC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.premiumReserveRetained" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(preResRetainD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.premiumReserveReleased" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(preResReleaseC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.lossReserveRetained" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(lossResRetainD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.lossReserveReleased" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(lossResReleaseC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.interest" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(bkdPreInterestC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.grossClaimsPaid" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(grsClaimsPaidD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.businessPartnerInwardNetAC" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(busiPartInwrdNetAcD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.pipelineNetAC" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinNetAcC)})}"/> </td>
												</tr>
												<tr>
													<td align="right"> <s:text name="label.total" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{debitTotal})}"/>  </td>
													<td align="right"> <s:property value="%{getText('number.format',{creditTotal})}"/> </td>
												</tr>
												</tbody>
											</table>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent" align="center">
											<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="callBookedPremiumCrnt('<s:property value="uwYear" />','<s:property value="accountDate" />','<s:property value="movementId" />');" />
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:elseif>
						<s:elseif test='"pipelineWritten".equals(display)'>
						<div class="tablerow">						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.piplelineWritten" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:text name="label.date" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="accountDate"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.spc" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="spcName"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.uwYear" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="uwYear"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.currency" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="currencyname"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="Product" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="productName"/> </b>	
											<br class="clear"/>
										</div>										
										<br class="clear"/>
										<div class="boxcontent">											
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.debit" /> </th>
													<th width="25%"> <s:text name="label.credit" /> </th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td> <s:text name="label.pipelinePremium" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinPremiumC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.pipelineAcquisitionCost" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinAcqCostD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.pipelineNetAC" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(piplinNetAcD)})}"/> </td>
													<td align="right"></td>
												</tr>												
												<tr>
													<td align="right"> <s:text name="label.total" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{debitTotal})}"/>  </td>
													<td align="right"> <s:property value="%{getText('number.format',{creditTotal})}"/> </td>
												</tr>
												</tbody>
											</table>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent" align="center">
											<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="callPipelineWritPreCrnt('<s:property value="uwYear" />','<s:property value="accountDate" />','<s:property value="movementId" />');" />
										</div>
									</div>									
								</div>
							</div>											
						</div>
						</s:elseif>
						<s:elseif test='"pipelineMovementJV".equals(display)'>
						<div class="tablerow">						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.pipelineMovementJV" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<s:text name="label.date" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="accountDate"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.spc" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="spcName"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.uwYear" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="uwYear"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="label.currency" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="currencyname"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="Product" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="productName"/> </b>	
											<br class="clear"/>
										</div>
										<br class="clear"/>
										<div class="boxcontent">
											<s:text name="policyType.startDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="start_date"/> </b>
											&nbsp;&nbsp;&nbsp;
											<s:text name="policyType.endDate" /> &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; <b> <s:property value="end_date"/> </b>
										</div>
										<br class="clear"/>
										<div class="boxcontent">											
											<table width="100%" class="table table-bordered">
												<thead>
												<tr>
													<th width="50%"></th>
													<th width="25%"> <s:text name="label.debit" /> </th>
													<th width="25%"> <s:text name="label.credit" /> </th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td> <s:text name="label.inwardPipelineUnearnedPremium" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinUnearnPremiumD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardDefferedAcquistionCost" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinDefAcqCostC)})}"/> </td>
												</tr>
												<tr>
													<td> <s:text name="label.inwardPipelineUPRBS" /> </td>
													<td align="right"></td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinUprBsC)})}"/> </td>
												</tr>																								
												<tr>
													<td> <s:text name="label.inwardPipelineDefferedAcquistionCostBS" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{@java.lang.Double@parseDouble(inwrdPiplinDefAcqCostBsD)})}"/> </td>
													<td align="right"></td>
												</tr>
												<tr>
													<td align="right"> <s:text name="label.total" /> </td>
													<td align="right"> <s:property value="%{getText('number.format',{debitTotal})}"/> </td>
													<td align="right"> <s:property value="%{getText('number.format',{creditTotal})}"/> </td>
												</tr>
												</tbody>
											</table>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent" align="center">
											<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="callPipelineMoveJvCrnt('<s:property value="uwYear" />','<s:property value="accountDate" />','<s:property value="movementId" />');" />
										</div>
									</div>									
								</div>
							</div>											
						</div>
						</s:elseif>
						
						
					</div>
					<s:hidden name="sNo" id="sNo" />
					<s:hidden name="reportType" id="reportType" />
					<s:hidden name="spc" id="spc" />
					<s:hidden name="uwYear" id="uwYear" />
					<s:hidden name="currencyId" id="currencyId" />
					<s:hidden name="accountDate" id="accountDate" />
					<s:hidden name="productId" id="productId" />
					<s:hidden name="typeId" id="typeId" />
					<s:hidden name="movementId" id="movementId" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" >    
	
	function fnPipelineMovementJVDetails(spc,productId,currencyId,uwYear) {		
		document.getElementById("spc").value=spc;
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("currencyId").value=currencyId;		
		document.getElementById("productId").value=productId;
		document.journalform.action="${pageContext.request.contextPath}/getPipelineMovementJvDetailsReports";
		document.journalform.submit();
	}
	
	function fnBookedPremiumDetails (spc,productId,currencyId,uwYear) {		
		document.getElementById("spc").value=spc;
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("currencyId").value=currencyId;		
		document.getElementById("productId").value=productId;
		document.journalform.action="${pageContext.request.contextPath}/getBookedPremiumDetailsReports";
		document.journalform.submit();
	}
	
	function fnBookedUPRDetails (spc,productId,currencyId,uwYear) {		
		document.getElementById("spc").value=spc;
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("currencyId").value=currencyId;		
		document.getElementById("productId").value=productId;
		document.journalform.action="${pageContext.request.contextPath}/getBookedUprDetailsReports";
		document.journalform.submit();
	}
	
	function fnPipelinedWrtnDetails (spc,productId,currencyId,uwYear) {		
		document.getElementById("spc").value=spc;
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("currencyId").value=currencyId;		
		document.getElementById("productId").value=productId;
		document.journalform.action="${pageContext.request.contextPath}/getPipelinedWrtnDetailsReports";
		document.journalform.submit();
	}	
	
	function viewMovDtls(accQtrDet,accPeriod,accDate,val,display,movType,movId) {
		if(val==1 || val==3) {
			document.getElementById("account_Period").value=accQtrDet;
			document.getElementById("accper").value=accPeriod;
			document.getElementById("accdate").value=accDate;
			document.getElementById("movId").value = movId;
			document.movementform.movementType.value=movType;
		}
		document.getElementById("display").value=display;
		document.movementform.action="${pageContext.request.contextPath}/viewMovDtlsReports";
		document.movementform.submit();
	}
	
	function viewJournelDtls(accQtrDet,accPeriod,accDate,val,display,movType,movId) {
		
		if(val==1 || val==3) {
			document.getElementById("account_Period").value=accQtrDet;
			document.getElementById("accper").value=accPeriod;
			document.getElementById("accdate").value=accDate;
			document.getElementById("movId").value = movId;
			document.movementform.movementType.value=movType;
		}
		document.getElementById("display").value=display;
		document.movementform.action="${pageContext.request.contextPath}/viewJournelDtlsReports";
		document.movementform.submit();
	}
	
	function viewCLMovDtls(accDate,val,display,movType,movId) {
		if(val==1 || val==3) {
			document.getElementById("accdate").value=accDate;
			document.getElementById("movId").value = movId;
			document.movementform.movementType.value=movType;
		}
		document.getElementById("display").value=display;
		document.movementform.action="${pageContext.request.contextPath}/viewCLMovDtlsReports";
		document.movementform.submit();
	}
	
	function viewJurnal(sNo) { 
		document.getElementById("sNo").value = sNo;
		document.movementform.action="${pageContext.request.contextPath}/viewJurnalReports";
		document.movementform.submit();
	}
	
	function viewJurnalReport(sNo) { 
		document.getElementById("sNo").value = sNo;
		document.movementform.action="${pageContext.request.contextPath}/viewJurnalReportReports";
		document.movementform.submit();
	}
	
	function getViewAllDownload(form) {
		document.movementform.action="${pageContext.request.contextPath}/viewAllReports.action";
		document.movementform.submit();	
	}
	
	function getViewAllJournelDownload(form) {
		document.movementform.action="${pageContext.request.contextPath}/viewAllJournelReports";
		document.movementform.submit();	
	}
	
	function getBack(form) {
		var a='10';
		form.action="${pageContext.request.contextPath}/getInitReports?typeId="+a;
		form.submit();
	}
	
	function getBack2(form) {
		var a='14';
		form.action="${pageContext.request.contextPath}/getInitReports?typeId="+a;
		form.submit();
	}
	
	function getCLBack(form) {
		var a='13';
		form.action="${pageContext.request.contextPath}/getInitReports?typeId="+a;
		form.submit();
	}
	
	function getBack1(form)	{
		var a='12';
		form.action="${pageContext.request.contextPath}/getInitReports?typeId="+a;
		form.submit();
	}
	
	function callMovment(form,method) {
		form.action="${pageContext.request.contextPath}/"+method+"Reports";
		form.submit();
	}
	
	function callJournal(form,method) {
		form.action="${pageContext.request.contextPath}/"+method+"Reports";
		form.submit();
	}
	
	function callPipelineMoveJvCrnt(uwYear,accountDate,movementId) {
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("accountDate").value=accountDate;		
		document.getElementById("movementId").value=movementId;
		document.journalform.action="${pageContext.request.contextPath}/viewPipelineMoveJvCrntReports";
		document.journalform.submit();
	}
	
	function callBookedPremiumCrnt(uwYear,accountDate,movementId) {
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("accountDate").value=accountDate;		
		document.getElementById("movementId").value=movementId;
		document.journalform.action="${pageContext.request.contextPath}/viewBookedPremiumCrntReports";
		document.journalform.submit();
	} 
	
	function callBookedUPRCrnt(uwYear,accountDate,movementId) {				
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("accountDate").value=accountDate;		
		document.getElementById("movementId").value=movementId;
		document.journalform.action="${pageContext.request.contextPath}/viewBookedUPRCrntReports";
		document.journalform.submit();				
	}
	
	function fnCallProcedure(form,method) {
		form.action="${pageContext.request.contextPath}/"+method+"Reports";
		form.submit();		
	}
	
	function callPipelineWritPreCrnt(uwYear,accountDate,movementId) {				
		document.getElementById("uwYear").value=uwYear;
		document.getElementById("accountDate").value=accountDate;		
		document.getElementById("movementId").value=movementId;
		document.journalform.action="${pageContext.request.contextPath}/viewPipelineWritPreCrntReports";
		document.journalform.submit();		
	}
	
	function callMovmentSummary(form) {
		document.getElementById('reportType').value='Html';
		form.action="${pageContext.request.contextPath}/getRlistReports";
		form.typeId.value="12";
		form.submit();
	}	
	
	function getReportsDownload(form) {
		document.getElementById('reportType').value='Excel';
		form.action="${pageContext.request.contextPath}/getRlistReports";
		form.submit();		
	}

</script>	
</body>
</html>
