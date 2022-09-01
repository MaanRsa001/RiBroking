<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	
	<script type="text/javascript">
	jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
				   // "stateSave": true,
					responsive: true
				});				 
			} catch(err){}
		} );	
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript">
	 $(function() {
	    $( "#reverseDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		});
	  </script>	
	</head>
	<body onload="divdis();">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<s:form id="adjustForm" name="adjustForm" theme="simple" action="" method="post">
					<div class="table1"
						style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
						<div class="tablerow" align="center">
						<%-- 	<s:if test='"PT".equals(allocType)'>
								<s:text name="heading.payment" />
							</s:if>
							<s:else>
								<s:text name="heading.Receipt" />
							</s:else>--%>
						</div>
						<s:set name="brokerid" value="%{brokerid==null?'':brokerid}" />
						<div class="tablerow">
							<span style="color: red;"><s:actionerror /> </span>
						</div>
						<s:if test='"adjustList".equalsIgnoreCase(mode)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">

									<s:hidden name="brokerid" id="brokerid" />
									<div class="table2">
										<div class="tablerow">
											<!--  <div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield33">
														<div class="text">
															<s:text name="make.searchBy"></s:text>
														</div>
															<div class="tbox">
																<s:select list="#{'1':'Adjustment Number','2':'Ceding Company','3':'Broker','4':'Transaction Type'}" headerKey="" headerValue="---Select---" name="searchBy" cssClass="inputBoxS" />
															</div>
														</div>
														<div class="textfield33">
															<div class="text">
																<s:text name="make.searchValue"></s:text>
															</div>
															<div class="tbox">
																<s:textfield name="searchValue" cssClass="inputBox"/>
															</div>
														</div>
														<br class="clear"/>
														<br/>
														<div align="center">
															<input type="button" value="Search" class="btn btn-sm btn-warning" onclick="search()" />
														</div>											
													</div>
												</div>
											</div>
										</div>-->
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
													<div class="tablerow">
													<s:text name="ADJUSTMENT DETAILS" />
													<%-- 	<s:if test='!"reversal".equals(type)'>
															<s:if test='"PT".equals(allocType)'>
																<s:text name="Heading.PaymentList" />
															</s:if>
															<s:else>
																<s:text name="Heading.ReceiptList" />
															</s:else>
														</s:if>
														<s:else>
															<s:if test='"PT".equals(allocType)'>
																<s:text name="Heading.ReceiptReversalList" />
															</s:if>
															<s:else>
																
																<s:text name="Heading.PaymentReversalList" />
															</s:else>
															<!--<s:text name="Heading.ReceiptReversalList" />*
														--></s:else>--%>
														<span class="pullRight"> 
																	<input type="button" value="New Adjustment"
																		class="btn btn-sm btn-success"
																		onclick="AddNewAdjustment()" />
																
															</span>
														</div>
													</div>
													<br class="clear">
													<!--  <div class="panel-body" style="overflow: auto;">
														<display:table name="allocatedAdjustmentList" pagesize="10"
															requestURI="" class="footable" uid="row" id="record">
															<s:set name="myrow" value="#attr.record" />
															<display:setProperty name="paging.banner.one_item_found"
																value="" />
															<display:setProperty name="paging.banner.one_items_found"
																value="" />
															<display:setProperty name="paging.banner.all_items_found"
																value="" />
															<display:setProperty
																name="paging.banner.some_items_found" value="" />
															<display:setProperty name="paging.banner.placement"
																value="bottom" />
															<display:setProperty name="paging.banner.onepage"
																value="" />
															
																<display:column sortable="false"
																	style="text-align:center;" title="Adjustment No"
																	property="serialNo" />
															<display:column sortable="false" style="text-align:left;"
																title="Broker Name" property="brokerName" />
															<display:column sortable="false" style="text-align:left;"
																title="Ceding Company" property="cedingName" />
																<display:column sortable="false" style="text-align:left;"
																title="Transaction Type" property="type" />
																<display:column sortable="false" style="text-align:left;"
																title="Transaction Currency" property="currencyName" />
																<display:column sortable="false" style="text-align:left;"
																title="Adjustment Type" property="adjustType" />
															<display:column sortable="false"
																style="text-align:right;" title="Amount Adjusted" 
																property="payamount" />
														<%-- 	<s:if test='"reversal".equals(type)'>
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false"
																		style="text-align:center;" title="REVERSED RECEIPT NO"
																		property="serial_no" />
																</s:if>
																<s:else>
																	<display:column sortable="false"
																		style="text-align:center;" title="REVERSED PAYMENT NO"
																		property="serial_no" />
																</s:else>
															</s:if>
															<s:if test='#session.MenuRights.indexOf("PRE")!=-1'>
																<display:column sortable="false"
																	style="text-align:center;" title="Edit">

																	<s:if
																		test='"Yes".equalsIgnoreCase(#myrow.editShowStatus) && "".equalsIgnoreCase(#myrow.serial_no) && !"Yes".equalsIgnoreCase(#myrow.recpayOpenYN)  '>
																		<a href="#" class="" title="Edit" onclick="funEditMode('${record.pay_rec_no}')">
																			<img border='0'
																				src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																				alt="Edit" width="12" height="17"> 
																		</a>
																	</s:if>
																</display:column>
															</s:if>--%>
																<display:column sortable="false"
																	style="text-align:center;" title="View" >
																	<a href="#" class="" title="View" onclick="funNewModes('${record.serialNo}','${record.brokerName}','${record.cedingName}','${record.status}')">
																		<img border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			alt="View" width="12" height="17"> 
																	</a>
																</display:column>
																<display:column sortable="false"
																	style="text-align:center;" title="Reverse" >
																	<s:if test='!"R".equals(#myrow.status)'>
																	<a href="#" class="" title="Reverse" onclick="funNewModes1('${record.serialNo}','${record.brokerName}','${record.cedingName}')">
																		<img border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			alt="View" width="12" height="17"> 
																	</a>
																	</s:if>
																</display:column>
																<display:column sortable="false"
																	style="text-align:center;" title="Document">
																	<a href="#" class="" title="Document" onclick="getDocList('${record.serialNo}','${record.cedingName}','${record.brokerName}','adjustList');"> 
																	<img
																			border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			
																			alt="Document Details" width="12" height="17">
																	</a>
																</display:column>
														</display:table>
													</div>-->
									<div class="panel-body">
									<div class="row">
									<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th style="text-align: center; vertical-align: middle;"><s:text name="SNo" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Adjustment No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Broker Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Ceding Company" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction Type" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction Currency" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Adjustment Type" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Amount Adjusted" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="View" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Reverse" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Document" /></th>								
										
										</tr>
										</thead>
										<tbody>
										<s:iterator value="allocatedAdjustmentList" var="list" status="stat">
										<tr>
										<td><s:property value="#stat.count"/></td>
										<td><s:property value="serialNo"/></td>
										<td><s:property value="brokerName"/></td>
										<td><s:property value="cedingName"/></td>
										<td><s:property value="type"/></td>	
										<td><s:property value="currencyName"/></td>	
										<td><s:property value="adjustType"/></td>	
										<td align="right"><s:property value="payamount"/></td>
										<td>
										<a href="#" class="" title="View" onclick="funNewModes('${list.serialNo}','${list.brokerName}','${list.cedingName}','${list.status}')">
																		<img border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			alt="View" width="12" height="17"> 
										</a>
										</td>
										<td>
										<s:if test='!"R".equals(#list.status)'>
										<a href="#" class="" title="Reverse" onclick="funNewModes1('${list.serialNo}','${list.brokerName}','${list.cedingName}')">
																		<img border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			alt="View" width="12" height="17"> 
											</a>
										</s:if>
										</td>	
										<td>
										<a href="#" class="" title="Document" onclick="getDocList('${list.serialNo}','${list.cedingName}','${list.brokerName}','adjustList');"> 
										<img
																			border='0'
																			src="${pageContext.request.contextPath}/images/icon_view_schedule.gif"
																			
																			alt="Document Details" width="12" height="17">
										</a>
										</td>			
										</tr>
										</s:iterator>
										</tbody>
										</table>
										</div>
										</div>
										</div>
													
												</div>
											</div>
											<div class="boxcontent" align="center">
												<s:hidden name="serialNo" />
												<s:hidden name="mode" />
												<s:hidden name="broker" />
												<s:hidden name="cedingCo" />
												<s:hidden name="moduleType" value="ADMT" />
												<!--<s:hidden name="tranNo" id="tranNo" />
												<s:hidden name="cedingName" id="cedingName" />
												<s:hidden name="brokerName" id="brokerName" />-->
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:if>
						<s:elseif test='"view".equals(mode)'>
						<s:if test='"R".equals(status)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="Adjustment No" />
												</div>
												<div class="tbox txtB">
													<s:property value="serialNo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Ceding Company Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="cedingName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Broker Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="brokerName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Reversed Date" />
												</div>
												<div class="tbox txtB">
													<s:property value="allocateddate"/>
												</div>
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
										<s:text name="Reversed Adjustment Details" />
									</div>
																
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="10" requestURI="" class="footable" uid="row" id="record2" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record2"/>
												<display:column style="text-align:center;" title="Adjustment No" property="serialNo" />
												<display:column style="text-align:center;" title="Reversed Date" property="allocateddate" />
												<s:if test='"P".equals(#myrow.type) || "C".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="transactionNo" />
												</s:if>
												<s:elseif test='"PT".equals(#myrow.type) || "RT".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="receiptNo" />
												</s:elseif>
												<display:column style="text-align:center;" title="Contract No" property="contractNo" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Business Type" property="productName" />
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
													<s:if test='"RT".equals(#myrow.type)'>
														<s:text name="RECEIPT" />
													</s:if>
													<s:if test='"PT".equals(#myrow.type)'>
														<s:text name="PAYMENT" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="Reversed Amount" >
														<s:property value="#myrow.payamount"/>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="adjustType" />
												<display:setProperty name="export.excel" value="false" />
												<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
									<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="Total Reversed Adjustment Amount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
								</div>
							</div>
                      		<div class="btnContainer" style="text-align: center;">
                            	<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="back(this.form);" />
                            	<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
                            	<s:hidden name="serialNo" />
                            	<s:hidden name="mode"/>
                            </div>
						</s:if>
						<s:else>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="Adjustment No" />
												</div>
												<div class="tbox txtB">
													<s:property value="serialNo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Ceding Company Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="cedingName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Broker Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="brokerName"/>
												</div>
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
										<s:text name="Adjustment Details" />
									</div>
																
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="10" requestURI="" class="footable" uid="row" id="record2" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record2"/>
												<display:column style="text-align:center;" title="Adjustment No" property="serialNo" />
												<display:column style="text-align:center;" title="Adjustment Date" property="allocateddate" />
												<s:if test='"P".equals(#myrow.type) || "C".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="transactionNo" />
												</s:if>
												<s:elseif test='"PT".equals(#myrow.type) || "RT".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="receiptNo" />
												</s:elseif>
												<display:column style="text-align:center;" title="Contract No" property="contractNo" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Business Type" property="productName" />
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
													<s:if test='"RT".equals(#myrow.type)'>
														<s:text name="RECEIPT" />
													</s:if>
													<s:if test='"PT".equals(#myrow.type)'>
														<s:text name="PAYMENT" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="Amount" >
														<s:property value="#myrow.payamount"/>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="adjustType" />
												<display:setProperty name="export.excel" value="false" />
												<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
									<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="adjust.unUtilizedAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
								</div>
							</div>
                      		<div class="btnContainer" style="text-align: center;">
                            	<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="back(this.form);" />
                            	<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
                            	<s:hidden name="serialNo" />
                            	<s:hidden name="mode"/>
                            </div>
                            </s:else>
                      </s:elseif>
                      <s:elseif test='"Reverse".equals(mode)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="Adjustment No" />
												</div>
												<div class="tbox txtB">
													<s:property value="serialNo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Ceding Company Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="cedingName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Broker Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="brokerName"/>
												</div>
											</div>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="adjust.reversaldate" />
												</div>
												<!--<div class="text txtB">
																	  <div class="inputAppend">
																		<sj:datepicker name="reverseDate" id="reverseDate"
																			cssClass="pullLeft" changeMonth="true" changeYear="true"
																			displayFormat="dd/mm/yy"
																			cssStyle="width: 85%; border:transparent;"/>
													</div> -->
													<div class="tbox">
														<s:textfield name="reverseDate" id="reverseDate"  cssClass="inputBox"  />
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
										<s:text name="Adjustment Details" />
									</div>
																
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="10" requestURI="" class="footable" uid="row" id="record2" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record2"/>
												<display:column style="text-align:center;" title="Adjustment No" property="serialNo" />
												<display:column style="text-align:center;" title="Adjustment Date" property="allocateddate" />
												<s:if test='"P".equals(#myrow.type) || "C".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="transactionNo" />
												</s:if>
												<s:elseif test='"PT".equals(#myrow.type) || "RT".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="receiptNo" />
												</s:elseif>
												<display:column style="text-align:center;" title="Contract No" property="contractNo" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Business Type" property="productName" />
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
													<s:if test='"RT".equals(#myrow.type)'>
														<s:text name="RECEIPT" />
													</s:if>
													<s:if test='"PT".equals(#myrow.type)'>
														<s:text name="PAYMENT" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="Amount" >
														<s:property value="#myrow.payamount"/>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="adjustType" />
												<display:setProperty name="export.excel" value="false" />
												<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
									
								</div>
							</div>
							</div>
                      		<div class="btnContainer" style="text-align: center;">
                            	<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="back(this.form);" />
                            	<input type="button" value="Reverse"  class="btn btn-sm btn-primary"	onClick="Reversal()"/>	
                            	<s:hidden name="serialNo" />
                            	<s:hidden name="cedingName" />
                            	<s:hidden name="brokerName" />
                            	<s:hidden name="mode"/>
                            </div>
                      </s:elseif>
                      <s:elseif test='"ReverseInsert".equals(mode)'>
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="Adjustment No" />
												</div>
												<div class="tbox txtB">
													<s:property value="serialNo"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Ceding Company Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="cedingName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Broker Name" />
												</div>
												<div class="tbox txtB">
													<s:property value="brokerName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="Reversed Date" />
												</div>
												<div class="tbox txtB">
													<s:property value="reverseDate"/>
												</div>
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
										<s:text name="Reversed Adjustment Details" />
									</div>
																
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedList" pagesize="10" requestURI="" class="footable" uid="row" id="record2" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record2"/>
												<display:column style="text-align:center;" title="Adjustment No" property="serialNo" />
												<display:column style="text-align:center;" title="Reversal Date" property="allocateddate" />
												<s:if test='"P".equals(#myrow.type) || "C".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="transactionNo" />
												</s:if>
												<s:elseif test='"PT".equals(#myrow.type) || "RT".equals(#myrow.type)'>
												<display:column style="text-align:center;" title="Transaction No" property="receiptNo" />
												</s:elseif>
												<display:column style="text-align:center;" title="Contract No" property="contractNo" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Business Type" property="productName" />
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
													<s:if test='"RT".equals(#myrow.type)'>
														<s:text name="RECEIPT" />
													</s:if>
													<s:if test='"PT".equals(#myrow.type)'>
														<s:text name="PAYMENT" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="Reversal Amount" >
														<s:property value="#myrow.payamount"/>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="adjustType" />
												<display:setProperty name="export.excel" value="false" />
												<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
									<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="Total Reversed Adjustment Amount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
								</div>
							</div>
                      		<div class="btnContainer" style="text-align: center;">
                            	<input type="button" value="Back"	class="btn btn-sm btn-danger" onclick="back(this.form);" />
                            	<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
                            	<s:hidden name="serialNo" />
                            	<s:hidden name="mode"/>
                            </div>
                      </s:elseif>
					</div>
				</s:form>
			</div>
		</div>
		<script type="text/javascript">
            var form = $('form[name="adjustForm"]');
	function AddNewAdjustment() {
		document.adjustForm.action="${pageContext.request.contextPath}/adjustmentHomeAdjustment.action";
		document.adjustForm.submit();
	}
	
	function funNewModes(value,brokerName,cedingName,status) {
		document.adjustForm.serialNo.value=value;
		document.adjustForm.mode.value="view";
		document.adjustForm.action="${pageContext.request.contextPath}/adjustmentViewAdjustment.action?cedingName="+cedingName+"&brokerName="+brokerName+"&status="+status;
		document.adjustForm.submit();
	}
	function Reversal(){
	var p=confirm("Are you sure to reverse the adjustments?")
	if(p){
		document.adjustForm.mode.value="ReverseInsert";
		document.adjustForm.action="${pageContext.request.contextPath}/reverseInsertAdjustment.action";
		document.adjustForm.submit();
		}
	}
	function funNewModes1(value,brokerName,cedingName) {
		document.adjustForm.serialNo.value=value;
		document.adjustForm.mode.value="Reverse";
		document.adjustForm.action="${pageContext.request.contextPath}/adjustmentViewAdjustment.action?cedingName="+cedingName+"&brokerName="+brokerName;
		document.adjustForm.submit();
	}
	function back() {	
		document.adjustForm.action="${pageContext.request.contextPath}/initAdjustment.action";
		document.adjustForm.submit();
	}
	function printpage()
		{
		document.adjustForm.mode.value="print";
		document.adjustForm.action="${pageContext.request.contextPath}/adjustmentViewAdjustment.action";
		document.adjustForm.submit();
		}
		function getDocList(tranNo,compName,brokeeName,type){
		document.adjustForm.action="${pageContext.request.contextPath}/documentUpload.action?tranNo="+tranNo+"&brokerName="+brokeeName+"&companyName="+compName+"&type="+type;
	    document.adjustForm.submit();
	}
	function funViewMode(recNo,brokId) {
		var URL ='<%=request.getContextPath()%>/PaymentInit.do?method=List&flag=REVVIEW&serial_no='+recNo+'&broker='+brokId;
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
	//function getDocList(tranNo,compName,brokeeName){
	//	document.getElementById("tranNo").value=tranNo;
	//	document.getElementById("companyName").value=compName;
	//	document.getElementById("brokerName").value=brokeeName;
	//	document.adjustForm.action="${pageContext.request.contextPath}/documentUpload.action?mode=Adjusment";
	//    document.adjustForm.submit();
	//}
	
	function funEditMode(value) {
		document.adjustForm.serial_no.value=value;
		document.adjustForm.flag.value="EDIT";
		document.adjustForm.action="${pageContext.request.contextPath}/initTreasury.action";
		document.adjustForm.submit();
	} 
	function search(){
		document.adjustForm.action="${pageContext.request.contextPath}/initAdjustment.action";
		document.adjustForm.submit();	
	} 
	
</script>
	</body>
</html>
