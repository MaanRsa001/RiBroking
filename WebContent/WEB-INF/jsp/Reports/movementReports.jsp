<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="movementform" name="movementform" theme="simple" action="/ReportsAction.do"	method="post">										
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
												
						<s:if test='"initMovRep".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.movementReport" />
										<span class="pullRight">
											<input type="button"  value="New Movement"   class="btn btn-sm btn-primary" onClick="callMovment(this.form,'newMovReport');" /> 
										</span>
										<br class="clear"/>
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
												<display:column sortable="true" style="text-align:right;" title="Movment Id" property="MOVMENT_TRANID" />
												<display:column sortable="true" style="text-align:left;" title="Accounting Quarter" property="DETAIL_NAME" />
												<display:column sortable="true" style="text-align:center;" title="Accounting Year" property="ACCOUNT_PERIOD" />
												<display:column sortable="true" style="text-align:center;" title="Movement Type" >
													<s:if test='"F".equalsIgnoreCase(#myrow.REPORT_TYPE)'>
														Final
													</s:if>
													<s:else>
														Intreim
													</s:else>
												</display:column>		
												<display:column sortable="true" style="text-align:center;"  title="Current" >
													<a class="" title="View Current">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewMovDtls('<s:property value="%{#myrow.DETAIL_NAME}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD_QTR}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD}" />','1','MovRepPage','<s:property value="%{#myrow.REPORT_TYPE}" />','<s:property value="%{#myrow.MOVMENT_TRANID}" />');"  alt="View Current" width="12" height="17"></a>
												</display:column>
												<display:column sortable="true" style="text-align:center;"  title="Discrepancies " >
												<s:if test='"Y".equals(#myrow.CHANGEDYN)'>
													<a class="" title="View Discrepancies">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewMovDtls('<s:property value="%{#myrow.DETAIL_NAME}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD_QTR}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD}" />','3','MovDisPage','<s:property value="%{#myrow.REPORT_TYPE}" />','<s:property value="%{#myrow.MOVMENT_TRANID}" />');"  alt="View Discrepancies" width="12" height="17"></a>
												</s:if>
												</display:column>
											</display:table>
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>
							<s:hidden name="movId" id="movId" />
							<s:hidden name="account_Period" id="account_Period" />
							<s:hidden name="display" id="display" />
							<s:hidden name="accper" id="accper" />
							<s:hidden name="accountDate" id="accdate" />
							<s:hidden name="movementType" id="movementType"/>					
						</div>
						</s:if>
						
						<s:elseif test='"clmJurnelRep".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.claimJournelReport" />
										<span class="pullRight">
											<input type="button"  value="New Movement"   class="btn btn-sm btn-primary" onClick="callMovment(this.form,'journelReport');" /> 
										</span>
										<br class="clear"/>
									</div>
									<div class="panel-body" style="overflow: auto;">
										<div class="boxcontent">											
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement"	value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true" style="text-align:right;" title="Movment Id" property="MOVMENT_TRANID" />
												<display:column sortable="true" style="text-align:left;" title="Accounting Quarter" property="DETAIL_NAME" />
												<display:column sortable="true" style="text-align:center;" title="Accounting Year" property="ACCOUNT_PERIOD" />
												<display:column sortable="true" style="text-align:center;" title="Movement Type" >
													<s:if test='"F".equalsIgnoreCase(#myrow.REPORT_TYPE)'>
														Final
													</s:if>
													<s:else>
														Intreim
													</s:else>
												</display:column>		
												<display:column sortable="true" style="text-align:center;"  title="Current" >
													<a class="" title="View Current">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewJournelDtls('<s:property value="%{#myrow.DETAIL_NAME}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD_QTR}" />','<s:property value="%{#myrow.ACCOUNT_PERIOD}" />','1','clmJurnelRep','<s:property value="%{#myrow.REPORT_TYPE}" />','<s:property value="%{#myrow.MOVMENT_TRANID}"/>');"  alt="View Current" width="12" height="17"></a>
												</display:column>
											</display:table>
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>
							<s:hidden name="movId" id="movId" />
							<s:hidden name="account_Period" id="account_Period" />
							<s:hidden name="display" id="display" />
							<s:hidden name="accper" id="accper" />
							<s:hidden name="accountDate" id="accdate" />
							<s:hidden name="movementType"/>						
						</div>
						</s:elseif>
						
						<s:elseif test='"initMovementSummary".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.MovementSummary" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDateStartFrom" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="start_date" id="start_date" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.transactionDateEndto" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="end_date" id="end_date" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" />
													</div>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<input type="button"  value="Submit"   class="btn btn-sm btn-success" onClick="callMovmentSummary(this.form);" />
							</div>							
						</div>
						<s:hidden name="typeId" id="typeId"></s:hidden>
						</s:elseif>
						
						<s:elseif test='"MovRepPage".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalReport" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingQuarter" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="account_Period"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingYear" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="accountDate"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true"	style="text-align:right;" title="SNo" property="SNO" />
												<display:column sortable="true"	style="text-align:left;" title="UW Year" property="UW_YEAR" />
												<display:column sortable="true" style="text-align:left;" title="SPC Name" property="SPC_NAME" />
												<display:column sortable="true" style="text-align:left;" title="Currency Name" property="CURRENCY_NAME" />
												<display:column sortable="true"	style="text-align:right;" title="Sum Of Premium Original" format="{0,number,##.00}" property="SUMOFTOTPR" />
												<display:column sortable="true"	style="text-align:right;"  title="Sum Of Premium USD" format="{0,number,###,###,###,###,##0.00}" property="SUMOFTOTPR_DC" />
												<display:column sortable="true" style="text-align:center;"  title="View Journal" >
													<a class="" title="View Jurnal">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewJurnal('<s:property value="%{#myrow.SNO}" />');"  alt="View Jurnal" width="12" height="17"></a>
												</display:column>
											</display:table>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack(this.form);"/>&nbsp;&nbsp;&nbsp;
								<input type="button" value="View All Journal" class="btn btn-sm btn-warning" onclick="getViewAllDownload(this.form);"/>
							</div>							
						</div>
						<s:hidden name="account_Period" id="account_Period" />
						<s:hidden name="accper" id="accper" />
						<s:hidden name="accountDate" id="accdate" />
						<s:hidden name="movId" id="movId"/>
						<s:hidden name="movementType" id="movementType"/>
						</s:elseif>
						
						<s:elseif test='"MovjournelPage".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.claimJournelReport" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingQuarter" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="account_Period"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingYear" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="accountDate"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true" style="text-align:right;" title="SNo" property="SNO" />
												<display:column sortable="true" style="text-align:left;" title="UW Year" property="UW_YEAR" />
												<display:column sortable="true" style="text-align:left;" title="SPC Name" property="SPC_NAME" />
												<display:column sortable="true" style="text-align:left;" title="Currency Name" property="CURRENCY_NAME" />
												<display:column sortable="true"	style="text-align:right;"  title="Sum Of Premium Original" format="{0,number,##.00}" property="LOSS_ESTIMATE_REVISED_OC" />
												<display:column sortable="true"	style="text-align:right;"  title="Sum Of Premium USD" format="{0,number,###,###,###,###,##0.00}" property="LOSS_ESTIMATE_REVISED_DC" />
												<display:column sortable="true" style="text-align:center;"  title="View Journal" >
													<a class="" title="View Jurnal">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewJurnalReport('<s:property value="%{#myrow.SNO}" />');"  alt="View Jurnal" width="12" height="17"></a>
												</display:column>
											</display:table>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack2(this.form);"/>&nbsp;&nbsp;&nbsp;
								<input type="button" value="View All Journal" class="btn btn-sm btn-warning"	onclick="getViewAllJournelDownload(this.form);"/>
							</div>							
						</div>
						<s:hidden name="account_Period" id="account_Period" />
						<s:hidden name="accper" id="accper" />
						<s:hidden name="accountDate" id="accdate" />
						<s:hidden name="movId" id="movId" />
						<s:hidden name="movementType" id="movementType"/>
						</s:elseif>
						
						<s:elseif test='"MovDisPage".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.discrepanciesReport" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingQuarter" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="account_Period"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.accountingYear" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="accountDate"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												
												<display:column sortable="true"	style="text-align:right;" title="S.No." value="${record_rowNum}" />
												<display:column sortable="true"	style="text-align:center;" title="Accounting Quarter" property="accper" />
												<display:column sortable="true"	style="text-align:center;" title="Accounting Year" property="accountDate" />
												<display:column sortable="true"	style="text-align:left;" title="UW Year" property="uwYear" />													
												<display:column sortable="true"	style="text-align:left;" title="SPC Name" property="spc" />
												<display:column sortable="true"	style="text-align:left;" title="Currency Name" property="currencyname" />
												<display:column sortable="true"	style="text-align:right;" title="current Gross Pre" property="currentGrossPre" />	
												<display:column sortable="true" style="text-align:right;" title="pre Gross Pre" property="preGrossPre" />
												<display:column sortable="true"	style="text-align:right;" title="diff Gross Pre" property="diffGrossPre" />	
												<display:column sortable="true" style="text-align:right;" title="current Gross Acq"	property="currentGrossAcq" />
												<display:column sortable="true"	style="text-align:right;" title="previous Gross Acq" property="previousGrossAcq" />
												<display:column sortable="true" style="text-align:right;" title="diff Gross Acq" property="diffGrossAcq" />
												<display:column sortable="true"	style="text-align:right;" title="current Premium Deposit Ret" property="currentPremiumDepositRet" />
												<display:column sortable="true"	style="text-align:right;" title="previous Premium Deposit Ret" property="previousPremiumDepositRet" />
												<display:column sortable="true"	style="text-align:right;" title="diff Premium DepositRet" property="diffPremiumDepositRet" />
												<display:column sortable="true"	style="text-align:right;" title="current Premium DepositRel" property="currentPremiumDepositRel" />
												<display:column sortable="true"	style="text-align:right;" title="previous Premium Deposit Rel" property="previousPremiumDepositRel" />
												<display:column sortable="true"	style="text-align:right;" title="diff Premium Deposit Rel" property="diffPremiumDepositRel" />
												<display:column sortable="true"	style="text-align:right;" title="current Claim Deposit Ret"	property="currentClaimDepositRet" />
												<display:column sortable="true"	style="text-align:right;" title="previous Claim Deposit Ret" property="previousClaimDepositRet" />
												<display:column sortable="true"	style="text-align:right;" title="diff Claim Deposit Ret" property="diffClaimDepositRet" />
												<display:column sortable="true"	style="text-align:right;"  title="current Claim Deposit Rel" property="currentClaimDepositRel" />
												<display:column sortable="true" style="text-align:right;"  title="previous Claim Deposit Rel" property="previousClaimDepositRel" />
												<display:column sortable="true"	style="text-align:right;"  title="diff Claim Deposit Rel" property="diffClaimDepositRel" />
												<display:column sortable="true"	style="text-align:right;"  title="current Interest Deposit"	property="currentInterestDeposit" />
												<display:column sortable="true"	style="text-align:right;"  title="previous Interest Deposit" property="previousInterestDeposit" />
												<display:column sortable="true"	style="text-align:right;"  title="diff Interest Deposit" property="diffInterestDeposit" />
												<display:column sortable="true"	style="text-align:right;"  title="current Gross Claim Paid"	property="currentGrossClaimPaid" />
												<display:column sortable="true"	style="text-align:right;"  title="previous Gross Claim Paid" property="previousGrossClaimPaid" />
												<display:column sortable="true"	style="text-align:right;"  title="Diff Gross Claim Paid" property="diffGrossClaimPaid" />													
											</display:table>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack2(this.form);"/>&nbsp;&nbsp;&nbsp;
								<input type="button" value="View All Journal" class="btn btn-sm btn-warning"	onclick="getViewAllJournelDownload(this.form);"/>
							</div>							
						</div>
						<s:hidden name="account_Period" id="account_Period" />
						<s:hidden name="accper" id="accper" />
						<s:hidden name="accountDate" id="accdate" />
						</s:elseif>
						
						<s:elseif test='"viewAllReport".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalReport" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
										  	 <s:iterator value="viewJurnalAll" var="viewJurnalVar" status="stat">
											  	 <table class="table table-bordered" width="100%">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.sNo" /> </td>
											  	 		<td> <s:property value="sNo"/></td>
											  	 		<td class="txtB"> <s:text name="label.uwYear" /> </td>
											  	 		<td> <s:property value="uwYear"/></td>
											  	 	</tr>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.accountingQuarter" /> </td>
											  	 		<td> <s:property value="account_Period" /> </td>
											  	 		<td class="txtB"> <s:text name="label.accountingYear" /> </td>
											  	 		<td> <s:property value="accountDate" /> </td>
											  	 	</tr>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.sPC" /> </td>
											  	 		<td> <s:property value="spc" /> </td>
											  	 		<td class="txtB"> <s:text name="currency.currencyName" /> </td>
											  	 		<td> <s:property value="currencyname" /> </td>
											  	 	</tr>
											  	 </table>
											  	 <table width="100%" class="table table-bordered">
											  	 	<thead>
											  	 	<tr>
											  	 		<th width="20%"> <s:text name="label.description" /> </th>
											  	 		<th colspan="2" width="40%"> <s:text name="label.debit" /> </th>
											  	 		<th colspan="2" width="40%"> <s:text name="label.credit" /> </th>
											  	 	</tr>
											  	 	<tr>
											  	 		<th width="20%"></th>
											  	 		<th width="20%"> <s:text name="label.original" /> </th>
											  	 		<th width="20%"> <s:text name="label.usd" /> </th>
											  	 		<th width="20%"> <s:text name="label.original" /> </th>
											  	 		<th width="20%"> <s:text name="label.usd" /> </th>
											  	 	</tr>
											  	 	</thead>
											  	 	<tbody>
											  	 	<s:if test="gpOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossPremium" /> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 		<td align="right"><s:property value="grossPremiumOC"/> </td>
											  	 		<td align="right"><s:property value="grossPremiumDC"/> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossPremium" /> </td>
											  	 		<td align="right"> <s:property value="grossPremiumOC"/></td>
											  	 		<td align="right"> <s:property value="grossPremiumDC"/> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="gAOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossAcquistionCost" /> </td>
											  	 		<td align="right"><s:property value="grossAcqCostOC"/> </td>
											  	 		<td align="right"><s:property value="grossAcqCostDC"/> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossAcquistionCost" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"><s:property value="grossAcqCostOC"/>  </td>
											  	 		<td align="right"><s:property value="grossAcqCostDC"/>  </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="pdRetOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.premiumDepositRetained" /> </td>
											  	 		<td align="right"><s:property value="premiumDepositRetainedOC"/> </td>
											  	 		<td align="right"><s:property value="premiumDepositRetainedDC"/> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.premiumDepositRetained" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"><s:property value="premiumDepositRetainedOC"/>  </td>
											  	 		<td align="right"><s:property value="premiumDepositRetainedDC"/>  </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="pdRelOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.premiumDepositReleased" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> <s:property value="premiumDepositReleasedOC"/> </td>
											  	 		<td align="right"> <s:property value="premiumDepositReleasedDC"/> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.premiumDepositReleased" /> </td>
											  	 		<td align="right"> <s:property value="premiumDepositReleasedOC"/> </td>
											  	 		<td align="right"> <s:property value="premiumDepositReleasedDC"/> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="cdRetOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.claimDepositRetained" /> </td>
											  	 		<td align="right"> <s:property value="claimDepositRetainedOC"/> </td>
											  	 		<td align="right"> <s:property value="claimDepositRetainedDC"/> </td>
											  	 		<td align="right"></td>
											  	 		<td align="right"></td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.claimDepositRetained" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> <s:property value="claimDepositRetainedOC"/> </td>
											  	 		<td align="right"> <s:property value="claimDepositRetainedDC"/> </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="cdRelOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.claimDepositReleased" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> <s:property value="claimDepositReleasedOC"/></td>
											  	 		<td align="right"> <s:property value="claimDepositReleasedDC"/> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.claimDepositReleased" /> </td>
											  	 		<td align="right"> <s:property value="claimDepositReleasedOC"/></td>
											  	 		<td align="right"> <s:property value="claimDepositReleasedDC"/> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="intOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.interestOnDeposit" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> <s:property value="interestOnDepositOC"/> </td>
											  	 		<td align="right"> <s:property value="interestOnDepositDC"/> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.interestOnDeposit" /> </td>
											  	 		<td align="right"> <s:property value="interestOnDepositOC"/></td>
											  	 		<td align="right"> <s:property value="interestOnDepositDC"/></td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="gcpOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossClaimsPaid" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> <s:property value="grossClaimPaidOC"/> </td>
											  	 		<td align="right"> <s:property value="grossClaimPaidDC"/> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.grossClaimsPaid" /> </td>
											  	 		<td align="right"> <s:property value="grossClaimPaidOC"/> </td>
											  	 		<td align="right"> <s:property value="grossClaimPaidDC"/> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<s:if test="blOC>=0">
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.brokerLedgerControl" /> </td>
											  	 		<td align="right"><s:property value="brokerLedCtlOC"/></td>
											  	 		<td align="right"><s:property value="brokerLedCtlDC"/>  </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 	</tr>
											  	 	</s:if>
											  	 	<s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="label.brokerLedgerControl" /> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"> </td>
											  	 		<td align="right"><s:property value="brokerLedCtlOC"/>  </td>
											  	 		<td align="right"><s:property value="brokerLedCtlDC"/> <%--=DropDownControllor.formatter(String.valueOf(Math.abs(f.getBrokerLedCtlDC()))) --%> </td>
											  	 	</tr>
											  	 	</s:else>
											  	 	<tr>
											  	 		<td class="txtB"> <s:text name="premium.total" /> </td>
											  	 		<td align="right"> <s:property value="totalDROC"/> </td>
											  	 		<td align="right"> <s:property value="totalDRDC"/></td>
											  	 		<td align="right"> <s:property value="totalCROC"/></td>
											  	 		<td align="right"> <s:property value="totalCRDC"/></td>
											  	 	</tr>
											  	 	</tbody>
											  	 </table>
											  	 <hr style="border: 2px solid #4f6180;" />
											  	</s:iterator>
										</div>
									</div>									
								</div>
							</div>
							<s:hidden name="movId" id="movId" />
							<div class="boxcontent" align="center">								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="viewMovDtls('','','','2','MovRepPage','${record.movementType}','');"/>
							</div>							
						</div>
						<s:hidden name="account_Period" id="account_Period" />
						<s:hidden name="accper" id="accper" />
						<s:hidden name="accountDate" id="accdate" />
						<s:hidden name="display" id="display" />
						<s:hidden name="movementType"/>
						</s:elseif>
						
						<s:elseif test='"viewJurnelAllReport".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalReport" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">
									  	    <s:iterator value="viewJurnalAll" var="viewJurnalVar" status="stat">
									  	    <table class="table table-bordered" width="100%">
										  	 	<tr>
										  	 		<td width="25%" class="txtB"> <s:text name="label.sNo" /> </td>
										  	 		<td width="25%" class=""> <s:property value="sNo"/> </td>
										  	 		<td width="25%" class="txtB"> <s:text name="label.uwYear" /> </td>
										  	 		<td width="25%" class=""><s:property value="uwYear"/> </td>
										  	 	</tr>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.accountingQuarter" /> </td>
										  	 		<td> <s:property value="account_Period" /> </td>
										  	 		<td class="txtB"> <s:text name="label.accountingYear" /> </td>
										  	 		<td> <s:property value="accountDate" /> </td>
										  	 	</tr>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.sPC" /> </td>
										  	 		<td> <s:property value="spc" /> </td>
										  	 		<td class="txtB"> <s:text name="currency.currencyName" /> </td>
										  	 		<td> <s:property value="currencyname" /> </td>
										  	 	</tr>
										  	 </table>
										  	 <table width="100%" class="table table-bordered">
										  	 	<thead>
										  	 	<tr>
										  	 		<th width="20%"> <s:text name="label.description" /> </th>
										  	 		<th colspan="2" width="40%"> <s:text name="label.debit" /> </th>
										  	 		<th colspan="2" width="40%"> <s:text name="label.credit" /> </th>
										  	 	</tr>
										  	 	<tr>
										  	 		<th width="20%"></th>
										  	 		<th width="20%"> <s:text name="label.original" /> </th>
										  	 		<th width="20%"> <s:text name="label.usd" /> </th>
										  	 		<th width="20%"> <s:text name="label.original" /> </th>
										  	 		<th width="20%"> <s:text name="label.usd" /> </th>
										  	 	</tr>
										  	 	</thead>
										  	 	<tbody>
										  	 	<s:if test="osLM>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.osLossMovementPL" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"> <s:property value="osLossMovement"/></td>
										  	 		<td align="right"> <s:property value="osLossMovementUSD"/></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.osLossMovementPL" /> </td>
										  	 		<td align="right"> <s:property value="osLossMovement"/></td>
										  	 		<td align="right"> <s:property value="oLossMovementUSD"/></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:else>
										  	 	<s:if test="bsM>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.bsMovement" /> </td>
										  	 		<td align="right"> <s:property value="bsMovement"/></td>
										  	 		<td align="right"> <s:property value="bsMovementUSD"/></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.bsMovement" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"><s:property value="bsMovement"/></td>
										  	 		<td align="right"><s:property value="bsMovementUSD"/></td>
										  	 	</tr>
										  	 	</s:else>
										  	 	<s:if test="jCP>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="movement.claimPaid" /> </td>
										  	 		<td align="right"><s:property value="journelClaimPaid"/> </td>
										  	 		<td align="right"><s:property value="journelClaimPaidUSD"/> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>								  	 	
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="movement.claimPaid" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"><s:property value="journelClaimPaid"/></td>
										  	 		<td align="right"><s:property value="journelClaimPaidUSD"/> </td>
										  	 	</tr>
										  	 	</s:else>
										  	 	<s:if test="dC>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.debitorCreditorControlAC" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"><s:property value="debtorsCreditors"/> </td>
										  	 		<td align="right"><s:property value="debtorsCreditorsUSD"/></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.debitorCreditorControlAC" /> </td>
										  	 		<td align="right"> <s:property value="debtorsCreditors"/></td>
										  	 		<td align="right"> <s:property value="debtorsCreditorsUSD"/></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:else>
										  	 	<s:if test="osLMB>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.oSLossMovementBS" /> </td>
										  	 		<td align="right"><s:property value="osLossMovementBs"/> </td>
										  	 		<td align="right"><s:property value="osLossMovementBsUSD"/></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>								  	 	
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.oSLossMovementBS" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"> <s:property value="osLossMovementBs"/></td>
										  	 		<td align="right"> <s:property value="osLossMovementBsUSD"/></td>
										  	 	</tr>
										  	 	</s:else>
										  	 	<s:if test="osLP>=0">
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.oSLossPL" /> </td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"><s:property value="osLossPl"/> </td>
										  	 		<td align="right"><s:property value="osLossPlUSD"/></td>
										  	 	</tr>
										  	 	</s:if>
										  	 	<s:else>
										  	 	<tr>
										  	 		<td class="txtB"> <s:text name="label.oSLossPL" /> </td>
										  	 		<td align="right"><s:property value="osLossPl"/></td>
										  	 		<td align="right"><s:property value="osLossPlUSD"/></td>
										  	 		<td align="right"></td>
										  	 		<td align="right"></td>
										  	 	</tr>
										  	 	</s:else>
										  	  	</tbody>
										  	 </table>
										  	 <hr style="border: 2px solid #4f6180;" />
										  	 </s:iterator>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="viewJournelDtls('','','','2','clmJurnelRep','<s:property value="%{#myrow.REPORT_TYPE}" />','');"/>
							</div>							
						</div>
						<s:hidden name="movId" id="movId" />
						<s:hidden name="account_Period" id="account_Period" />
						<s:hidden name="accper" id="accper" />
						<s:hidden name="accountDate" id="accdate" />
						<s:hidden name="display" id="display" />
						<s:hidden name="movementType"/>			
						</s:elseif>
						
						<s:elseif test='"newMovReport".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.newMovement" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.AccountPeriod" />
												</div>
												<div class="tbox">
													<s:select list="premiumaccperiod" listKey="TYPE" listValue="DETAIL_NAME"  name="accper" headerKey="0" headerValue="---Select--" cssClass="inputBoxS" cssStyle="width: 45%; float: left;" />
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="accountDate" headerKey="0" headerValue="---Year--" cssClass="inputBoxS" cssStyle="width: 45%; float: left;" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.Movement.Type" />
												</div>
												<div class="tbox">
													<s:radio list="#{'':'Intreim','F':'Final'}" name="movementType" value='' />													
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<s:hidden name="display" id="display" />								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack(this.form);"/> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success"	onclick="callMovment(this.form,'insertMovement');"/>
							</div>
						</div>
						</s:elseif>
						
						<s:elseif test='"journelReport".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.journalNewMovement" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.AccountPeriod" />
												</div>
												<div class="tbox">
													<s:select list="premiumaccperiod" listKey="TYPE" listValue="DETAIL_NAME"  name="accper" headerKey="0" headerValue="---Select--" cssClass="inputBoxS" cssStyle="width: 45%; float: left;" />
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="accountDate" headerKey="0" headerValue="---Year--" cssClass="inputBoxS" cssStyle="width: 45%; float: left;" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.Movement.Type" />
												</div>
												<div class="tbox">
													<s:radio list="#{'':'Intreim','F':'Final'}" name="movementType" value=''/>													
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<s:hidden name="display" id="display" />								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack2(this.form);"/> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success"	onclick="callMovment(this.form,'insertJournelMovement');"/>
							</div>
						</div>
						</s:elseif>
						
						<s:elseif test='"movementSummary".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.PremiumMovementSummary" />		
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<display:table name="ReportsList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												
												<%--List columnInfo=(List)request.getAttribute("ColumnInfo"); 
												try{
													for(int i=0;i<columnInfo.size();i++){
														Map columnMap=(Map)columnInfo.get(i);
														String Key=((String)columnMap.get("EXCEL_HEADER_NAME"));
														String value=((String)columnMap.get("FIELD_NAME"));
												--%>
												<display:column sortable="true" style="text-align:left;" title="<%--=Key--%>" property="<%--=value--%>" ></display:column>
												<%--}}catch(Exception e){e.printStackTrace();}--%>	
											</display:table>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack1(this.form);"/> &nbsp;&nbsp;&nbsp; 
								<input type="button" value="Save" class="btn btn-sm btn-success"	onclick="getReportsDownload(this.form);"/>
							</div>
						</div>
						</s:elseif>
						
						<s:elseif test='"initCLMovRep".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.claimMovementReport" />
										<span class="pullRight">
											<input type="button"  value="New Movement" class="btn btn-sm btn-primary" onClick="callMovment(this.form,'newCLMovReport');" /> 
										</span>
										<br class="clear"/>										
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true" style="text-align:center;" title="Movment Id" property="MOVEMENT_ID" />
												<display:column sortable="true" style="text-align:center;" title="Movment as on" property="MOVEMENT_DT" />
												<display:column sortable="true" style="text-align:center;" title="Movement Type" >
													<s:if test='"F".equalsIgnoreCase(#myrow.REPORT_TYPE)'>
														Final
													</s:if>
													<s:else>
														Intreim
													</s:else>
												</display:column>		
												<display:column sortable="true" style="text-align:center;"  title="View" >
													<a class="" title="View Current">	<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onclick="viewCLMovDtls('<s:property value="%{#myrow.MOVEMENT_DT}" />','1','CLMovRepPage','<s:property value="%{#myrow.REPORT_TYPE}" />','<s:property value="%{#myrow.MOVEMENT_ID}" />');"  alt="View Current" width="12" height="17"></a>
												</display:column>
											</display:table>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<s:hidden name="movId" id="movId" />
							<s:hidden name="account_Period" id="account_Period" />
							<s:hidden name="display" id="display" />
							<s:hidden name="accper" id="accper" />
							<s:hidden name="accountDate" id="accdate" />
							<s:hidden name="movementType"/>
						</div>
						</s:elseif>
						
						<s:elseif test='"CLMovRepPage".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.claimMovementReport" />																				
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.movementId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="movId"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.movementAsOn" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="accountDate"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
										<div class="boxcontent">
											<display:table name="ReportList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column sortable="true" style="text-align:left;" title="UW Year" property="UWYEAR" />
												<display:column sortable="true" style="text-align:left;" title="SPC Name" property="SPC_NAME" />
												<display:column sortable="true" style="text-align:left;" title="Currency Name" property="CURRENCY_NAME" />
												<display:column sortable="true" style="text-align:left;" title="Product" property="PRODUCT" />													
												<display:column sortable="true" style="text-align:left;" title="Type" property="TYPE" />
												<display:column sortable="true" style="text-align:center;" title="Contract NO" property="CONTRACT_NO" />
												<display:column sortable="true" style="text-align:center;" title="Transaction No" property="TRAN_NO" />															
												<display:column sortable="true" style="text-align:right;"  title="OS Claim Original" property="OUTSTANDING_AMOUNT_OC" />
												<display:column sortable="true" style="text-align:right;"  title="OS Claim Original USD" property="OUTSTANDING_AMOUNT_DC" />											
											</display:table>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">	
								 <input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getCLBack(this.form);"/>
							</div>
							<s:hidden name="account_Period" id="account_Period" />
							<s:hidden name="accper" id="accper" />
							<s:hidden name="accountDate" id="accdate" />
						</div>
						</s:elseif>
						
						<s:elseif test='"newCLMovReport".equals(display)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.newClaimMovement" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.movementReportAsOn" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<sj:datepicker name="end_date" id="end_date" cssClass="pullLeft" changeYear="true" displayFormat="dd/mm/yy" cssStyle="width: 85%; border:transparent;" />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.Movement.Type" />
												</div>
												<div class="tbox">
													<s:radio list="#{'':'Intreim','F':'Final'}" name="movementType"  value=''/>													
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<s:hidden name="display" id="display" />								
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getCLBack(this.form);"/> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success"	onclick="callMovment(this.form,'insertCLMovement');"/>
							</div>							
						</div>
						</s:elseif>
					</div>
					<s:hidden name="sNo" id="sNo" />
					<s:hidden name="reportType" id="reportType" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" >    
	
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
