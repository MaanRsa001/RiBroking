<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript">
	 $(function() {
	    $( "#tr_date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#amend_date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>
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
	
	</head>
	<body onload="divdis();getConRecCurrency();GetExchangeRate1('<s:property value="cedingCompanyValList[0]"/>','1');calcu();">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<s:form id="PaymentForm" name="PaymentForm" theme="simple" action="" method="post">
					<div class="panel-group" id="accordion">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													aria-expanded="true" href="#collapseOne"> <i
													class="glyphicon glyphicon-plus" id="detailsPlus"
													style="cursor: pointer;" onclick="detailsClick(true)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i>
													<i class="glyphicon glyphicon-minus" id="detailsMinus"
													style="cursor: pointer; display: none;"
													onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i> </a>
											</h4>
										</div>

										<div id="detailsData" style="display: none;"
											class="panel-collapse collapse in">
											<div class="panel-body">
												<div class="textfield">
													<div class="text">
														<s:if test='"PT".equals(allocType)'>
															<s:text name="Payment No" />
														</s:if>
														<s:else>
															<s:text name="Receipt No" />
														</s:else>
													</div>
													<div class="tbox txtB">
														<s:textfield name="paymentNoSearch" id="paymentNoSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
														<div class="text">
															<s:text name="Broker Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="brokerNameSearch" id="brokerNameSearch" cssClass="inputBox" />
														</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Company Name" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="companyNameSearch" id="companyNameSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Remarks" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="remarksSearch" id="remarksSearch" cssClass="inputBox" />
													</div>
												</div>
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('')" />							
																					
														<s:if test='#session.MenuRights.indexOf("R")!=-1'>
															<input type="button" value="Show Full data" class="btn btn-sm btn-warning" onclick="showFullData();" /> 
														</s:if>
																			
												</div>
											</div>
										</div>
									</div>
								</div>
					<div class="table1"
						style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
						<div class="tablerow" align="center">
							<s:if test='"PT".equals(allocType)'>
								<s:text name="heading.payment" />
							</s:if>
							<s:else>
								<s:text name="heading.Receipt" />
							</s:else>
						</div>
						<s:set name="brokerid" value="%{brokerid==null?'':brokerid}" />
						<div class="tablerow">
							<span style="color: red;"><s:actionerror /> </span>
						</div>
						<s:if test='"listReceiptNo".equalsIgnoreCase(partToShow)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">

									<s:hidden name="brokerid" id="brokerid" />
									<div class="table2">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:if test='!"reversal".equals(type)'>
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
														</s:else>
														<span class="pullRight"> 
														<s:if test='#session.MenuRights.indexOf("PRN")!=-1'>
															<s:if test='"PT".equals(allocType)'>
																<input type="button" value="New Payment" class="btn btn-sm btn-success" onclick="AddNewReceipt('<s:property value="allocType" />')" />
															</s:if>
															<s:else>
																<input type="button" value="New Receipt" class="btn btn-sm btn-success" onclick="AddNewReceipt('<s:property value="allocType" />')" />
															</s:else>
															</s:if>
														</span>
													</div>
													<div class="panel-body" style="overflow: auto;">
														<div class="panel-body">
															<div class="row">
																<div class="col-xs-12">
								   									<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																		<thead>
																			<tr>
																				<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
																				<th style="text-align: center; vertical-align: middle;">
																					<s:if test='"PT".equals(allocType)'>
																						<s:text name="PAYMENT NO" />
																					</s:if>
																					<s:else>
																						<s:text name="RECEIPT NO" />
																					</s:else>
																				</th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="BROKER" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="CEDING COMPANY" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="AMOUNT" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="REMARKS" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="STATUS OF ALLOCATION" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="ALLOCATION MENU" /></th>
																				<s:if test='"reversal".equals(type)'>
																					<s:if test='"PT".equals(allocType)'>
																						<th style="text-align: center; vertical-align: middle;"><s:text name="REVERSED RECEIPT NO" /></th>
																					</s:if>
																					<s:else>
																						<th style="text-align: center; vertical-align: middle;"><s:text name="REVERSED PAYMENT NO" /></th>
																					</s:else>
																				</s:if>
																				<s:if test='#session.MenuRights.indexOf("PRE")!=-1'>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="Edit" /></th>
																				</s:if>
																				<s:if test='#session.MenuRights.indexOf("PRV")!=-1'>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="View" /></th>
																				</s:if>
																				<s:if test='!"reversal".equals(type)'>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="Document" /></th>
																				</s:if>
																			</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="DetailsList" var="list" status="stat">
																			<tr>
																				<td><s:property value="#stat.count" /></td>
																				<td><s:property value="pay_rec_no" /></td>
																				<td><s:property value="broker" /></td>
																				<td><s:property value="cedingCo" /></td>
																				<td align="right"><s:property value="payamount" /></td>
																				<td><s:property value="remarks" /></td>
																				<td><s:property value="allocationStatus" /></td>
																				<td><s:if test='"Transaction".equals(#list.transactionType)'> 
																					<a href="#" class="" title="allocationStatus" onclick="funMove('<s:property value="pay_rec_no" />','<s:property value="type" />')">
																						<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Edit" width="12" height="17"/> 
																					</a>
																					</s:if>
																				</td>
																				<s:if test='"reversal".equals(type)'>
																				<td><s:property value="serial_no" /></td>
																				</s:if>
																				<s:if test='#session.MenuRights.indexOf("PRE")!=-1'>
																				<td>
																					<s:if test='"Yes".equalsIgnoreCase(#list.editShowStatus) && "".equalsIgnoreCase(#list.serial_no) && !"Yes".equalsIgnoreCase(#list.recpayOpenYN) '>
																						<a href="#" class="" title="Edit" onclick="funEditMode('<s:property value="pay_rec_no" />')">
																							<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Edit" width="12" height="17"> 
																						</a>
																					</s:if>
																				</td>
																				</s:if>
																				<s:if test='#session.MenuRights.indexOf("PRV")!=-1'>
																				<td> 
																				<a href="#" class="" title="View" onclick="funNewModes('<s:property value="pay_rec_no" />','<s:property value="brokerid" />')">
																					<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="View" width="12" height="17"> 
																				</a>
																				</td>
																				</s:if>
																				<s:if test='!"reversal".equals(type)'>
																				<td>
																					<a href="#" class="" title="Document" onclick="getDocList('<s:property value="pay_rec_no" />','<s:property value="cedingCo" />','<s:property value="broker" />','treasury');"> 
																						<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Document Details" width="12" height="17"/>
																					</a>
																				</td>
																				</s:if>
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
										<div class="boxcontent" align="center">
											<s:hidden name="serial_no" />
											<s:hidden name="flag" />
											<!--<s:hidden name="broker" />-->
											<s:hidden name="cedingCo" />
											<s:hidden name="tranNo" id="tranNo" />
											<s:hidden name="moduleType" value="%{allocType}" />
											<s:hidden name="companyName" id="companyName" />
											<s:hidden name="brokerName" id="brokerName" />
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<s:elseif test='"reversalList".equals(partToShow)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">
									<div class="table2">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:if test='"PT".equals(allocType)'>
															<s:text name="Payment Reversal List" />
														</s:if>
														<s:else>
															<s:text name="Receipt Reversal List" />
														</s:else>
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="Premium.PaymentNo" />
																	</s:if>
																	<s:else>
																		<s:text name="Premium.ReceiptNo" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="serial_no" />
																	<s:hidden name="serial_no" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RiskDetails.Broker" />
																</div>
																<div class="tbox">
																	<s:property value="broker" />
																</div>
															</div>
															<s:if test='!""equalsIgnoreCase(cedingCo)'>
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="RiskDetails.CedingCompany" />
																	</div>
																	<div class="tbox">
																		<s:property value="cedingCo" />
																	</div>
																</div>
															</s:if>															
															<br class="clear" />
															<display:table name="detailsList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found"	value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set name="myrow" value="#attr.record"/>
																<display:column sortable="true" style="text-align:center;" title="Allocation No" property="serial_no" />
																<display:column sortable="true" style="text-align:center;" title="Contract No" property="contractno" />
																<display:column sortable="true" style="text-align:center;" title="Transaction No" property="transactionno" />
																<display:column sortable="true" style="text-align:center;" title="Allocation Date" property="allocateddate" />
																<display:column sortable="true" style="text-align:center;" title="Reversal Date" property="reversalDate" />
																<display:column sortable="true" style="text-align:left;" title="Currency" property="currency" />
																<display:column sortable="true" style="text-align:left;" title="Business Type" property="productname" />
																<display:column sortable="true" style="text-align:left;" title="Type" >	
																	<s:if test='"P".equals(#myrow.type)'>
																		<s:text name="PREMIUM"/>
																	</s:if>
																	<s:if test='"C".equals(#myrow.type)'>
																		<s:text name="CLAIM"/>
																	</s:if>
																</display:column>
																<display:column sortable="true" style="text-align:right;" title="Sign" >
																	<s:if test='"RT".equals(allocType)'>
																		<s:if test='"P".equals(#myrow.type)'>
																			+
																		</s:if>
																		<s:elseif test='"C".equals(#myrow.type)'>
																			-
																		</s:elseif>
																	</s:if>
																	<s:elseif test='"PT".equals(allocType)'>
																		<s:if test='"P".equals(#myrow.type)'>
																			-
																		</s:if>
																		<s:elseif test='"C".equals(#myrow.type)'>
																			+
																		</s:elseif>
																	</s:elseif>		
																</display:column>
																<display:column sortable="true" style="text-align:right;" title="Reversed Amount" property="reversedAmount" />
															</display:table>
															<div class="panel-body">
																<div class="row">
																	<div class="col-xs-12">
								   										<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																			<thead>
																				<tr>
																					<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Allocation No" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction No" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Allocation Date" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Reversal Date" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Currency" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="UW Year" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Business Type" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Type" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Sign" /></th>
																					<th style="text-align: center; vertical-align: middle;"><s:text name="Reversed Amount" /></th>
																				</tr>
																			</thead>
																			<tbody>
																				<s:iterator value="detailsList" var="list" status="stat">
																					<tr>
																						<td><s:property value="#stat.count" /></td>
																						<td><s:property value="serial_no" /></td>
																						<td><s:property value="contractno" /></td>
																						<td><s:property value="transactionno" /></td>
																						<td><s:property value="allocateddate" /></td>
																						<td><s:property value="reversalDate" /></td>
																						<td><s:property value="currency" /></td>
																						<td><s:property value="productname" /></td>
																						<td>
																							<s:if test='"P".equals(<s:property value="type" />)'>
																								<s:text name="PREMIUM"/>
																							</s:if>
																							<s:if test='"C".equals(<s:property value="type" />)'>
																								<s:text name="CLAIM"/>
																							</s:if>
																						</td>
																						<td><s:if test='"RT".equals(<s:property value="allocType" />)'>
																								<s:if test='"P".equals(<s:property value="type" />)'>
																										+
																								</s:if>
																								<s:elseif test='"C".equals(<s:property value="type" />)'>
																									-
																								</s:elseif>
																								</s:if>
																							<s:elseif test='"PT".equals(<s:property value="allocType" />)'>
																								<s:if test='"P".equals(<s:property value="type" />)'>
																									-
																								</s:if>
																								<s:elseif test='"C".equals(<s:property value="type" />)'>
																									+
																								</s:elseif>
																							</s:elseif>		
																						</td>
																						<td align="right"><s:property value="reversedAmount" /></td>
																					</tr>
																				</s:iterator>
																			</tbody>
																		</table>
																	</div>			
																</div>
															</div>
														<br class="clear"/>
														</div>
													<div class="boxcontent" align="center">												
														<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="back5()" />											</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>						
						</s:elseif>
						<s:if test='"viewReceiptNo".equals(partToShow)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">
									<div class="table2">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:if test='!"reversal".equals(type)'>
															<s:if test='"PT".equals(allocType)'>
																<s:text name="heading.payment" />
															</s:if>
															<s:else>
																<s:text name="heading.Receipt" />
															</s:else>
														</s:if>
														<s:else>
															<s:text name="Heading.ReceiptReversalDetails" />
														</s:else>
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="Premium.PaymentNo" />
																	</s:if>
																	<s:else>
																		<s:text name="Premium.ReceiptNo" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="serial_no" />
																	<s:hidden name="serial_no" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RiskDetails.Broker" />
																</div>
																<div class="tbox">
																	<s:property value="brokername" />
																</div>
															</div>
															<s:if test='!""equalsIgnoreCase(cedingCo)'>
																<div class="textfield">
																	<div class="text txtB">
																		<s:text name="RiskDetails.CedingCompany" />
																	</div>
																	<div class="tbox">
																		<s:property value="cedingCo" />
																	</div>
																</div>
															</s:if>
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.bank" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receiptBank" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="receiptBankName" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.paymentCurrency" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receivedCurrency" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="currency" />
																	&nbsp;
																	<s:property value="exchangerate" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Payment.TransactionDate" />
																</div>
																<div class="tbox">
																	<s:property value="tr_date" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.amount" />
																</div>
																<div class="tbox">
																	<s:property value="paymentamount" />
																	<s:hidden name="paymentamount" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.bankCharges" />
																</div>
																<div class="tbox">
																	<s:property value="bankCharges" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.paymentAmount" />(<s:property value="shortname"/>)
																	</s:if>
																	<s:else>
																		<s:text name="payment.receiptAmount" />(<s:property value="shortname"/>)
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="totalexchaamount" />
																</div>
															</div>
                                                            <div class="textfield">
                                                                <div class="text txtB">
                                                                    <s:text name="label.WithHoldingTax" />
                                                                </div>
                                                                <div class="tbox">
                                                                    <s:property value="withHoldingTaxOC" />
                                                                </div>
                                                            </div>
                                                            <div class="textfield">
                                                                <div class="text txtB">
                                                                    <s:text name="label.PremiumLavy" />
                                                                </div>
                                                                <div class="tbox">
                                                                    <s:property value="premiumLavy" />
                                                                </div>
                                                            </div>
                                                            <div class="textfield">
                                                                <div class="text txtB">
                                                                    <s:text name="payment.netAmt" />
                                                                </div>
                                                                <div class="tbox">
                                                                    <s:property value="netAmt" />
                                                                </div>
                                                            </div>
															<s:if test='!"reversal".equals(type)'>
															<div class="textfield">
																	<div class="text txtB">
																		<s:text name="Round off - OC" />
																	</div>
																	<div class="tbox">
																		<s:property value="convDiffAmount" />
																	</div>
																</div>
																<div class="textfield">
																	<div class="text txtB">
																	Difference amount (<s:property value="shortname"/>) - Forex & Rounding off 
																	</div>
																	<div class="tbox">
																		<s:property value="diffAmount" />
																	</div>
																	</div>
																	
															</s:if>
															<div class="textfield">
																	<div class="text txtB">
																	Remarks
																	</div>
																	<div class="tbox">
																		<s:property value="remarks" />
																	</div>
																	</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body" style="overflow: auto;">
														<s:if test='!"reversal".equals(type)'>
															<display:table name="detailsList" pagesize="20" requestURI="" class="table table-bordered" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<!--display:column sortable="false"
																	style="text-align:center;" title="S.No."
																	property="pay_res" -->
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false" style="text-align:center;" title="Payment No" property="serial_no" />
																	<display:column sortable="false" style="text-align:center;" title="Payment Currency" property="currency" />
																</s:if>
																<s:else>
																	<display:column sortable="false" style="text-align:center;" title="Receipt No" property="serial_no" />
																	<display:column sortable="false" style="text-align:center;" title="Received Currency" property="currency" />
																</s:else>
																<display:column sortable="false" style="text-align:right;" title="Amount" property="payamount" />
																<display:column sortable="false" style="text-align:right;" title="Settled Exchange Rate" property="setExcRate" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false" style="text-align:right;" title="Converted Paid Amount" property="conRecCur" />
																</s:if>
																<s:else>
																	<display:column sortable="false" style="text-align:right;" title="Converted Receipt Amount" property="conRecCur" />
																</s:else>
																<display:column sortable="false" style="text-align:right;" title="Exchange Rate" property="exrate" />
																<display:column sortable="false" style="text-align:right;" title="Total Amount" property="totAmount" />
															</display:table>
														</s:if>
														<s:else>
															<display:table name="detailsList" pagesize="0" requestURI="" class="table table-bordered" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false" style="text-align:center;" title="Receipt No" property="pay_rec_no" />
																</s:if>
																<s:else>
																	<display:column sortable="false" style="text-align:center;" title="Payment No" property="pay_rec_no" />
																</s:else>
																<display:column sortable="false" style="text-align:left;" title="Broker Name" property="brokername" />
																<display:column sortable="false" style="text-align:left;" title="Ceding Company" property="cedingCo" />
																<display:column sortable="false" style="text-align:left;" title="Currency" property="currencyName" />
																<display:column sortable="false" style="text-align:right;" title="Exchange Rate" property="exrate" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false" style="text-align:right;" title="Receipt Amount" property="paymentamount" />
																</s:if>
																<s:else>
																	<display:column sortable="false" style="text-align:right;" title="Payment Amount" property="paymentamount" />
																</s:else>
															</display:table>
														</s:else>
													</div>
												</div>
											</div>
											<s:if test='"VIEW".equals(flag)'>
											  <div class="boxcontent">
								         		<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="heading.alloccontracts" />
													</div>									
													<div class="panel-body">
														<div class="boxcontent" style="overflow: auto;">
															<display:table name="allocatedList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record2" export="true">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found"	value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set name="myrow" value="#attr.record2"/>
																<display:column style="text-align:center;" title="Allocation No." property="serial_no" />
																<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
																<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
																<display:column style="text-align:center;" title="Contract No" property="contractno" />
																<display:column style="text-align:center;" title="Currency" property="currencyName" />
																<display:column style="text-align:center;" title="Exchange Rate" property="exchangerate" />
																<display:column style="text-align:center;" title="Business Type" property="productname" />
																<display:column style="text-align:left;" title="TYPE">
																	<s:if test='"P".equals(#myrow.type)'>
																		<s:text name="PREMIUM" />
																	</s:if>
																	<s:if test='"C".equals(#myrow.type)'>
																		<s:text name="CLAIM" />
																	</s:if>
																	<s:if test='"PT".equals(#myrow.type)'>
																		<s:text name="PAYMENT" />
																	</s:if>
																	<s:if test='"RT".equals(#myrow.type)'>
																		<s:text name="RECEIPT" />
																	</s:if>
																</display:column>
																<display:column style="text-align:right;" title="SIGN">
																		<s:if test='"P".equals(#myrow.type)'>
																			+
																		</s:if>
																		<s:if test='"C".equals(#myrow.type)'>
																			-
																		</s:if>
																		<s:if test='"PT".equals(#myrow.type)'>
																			+
																		</s:if>
																		<s:if test='"RT".equals(#myrow.type)'>
																			-
																		</s:if>
																</display:column>		
																<display:column style="text-align:right;" title="Amount" >
																	<s:if test='"P".equals(#myrow.type)'>
																		<s:property value="#myrow.payamount"/>
																	</s:if>
																	<s:if test='"C".equals(#myrow.type)'>
																		<s:property value="#myrow.payamount"/>
																	</s:if>
																	<s:if test='"PT".equals(#myrow.type)'>
																		<s:property value="#myrow.payamount"/>
																	</s:if>
																	<s:if test='"RT".equals(#myrow.type)'>
																		<s:property value="#myrow.payamount"/>
																	</s:if>
																</display:column>
																<display:column style="text-align:left;" title="Status" property="status" /><%--adjustMentType --%>
																<display:setProperty name="export.excel" value="true" />
																<display:setProperty name="export.excel.filename" value="Allocated Contracts2.xls"/>
																<display:setProperty name="export.csv" value="false" />
																<display:setProperty name="export.xml" value="false" />
																<display:setProperty name="export.pdf" value="false" />
															</display:table>
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="heading.revcontracts" />
													</div>									
													<div class="panel-body">
														<div class="boxcontent" style="overflow: auto;">
															<display:table name="revertedList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record3" export="true">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found"	value="" />
																<display:setProperty name="paging.banner.placement" value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:set name="myrow" value="#attr.record3"/>
																<display:column style="text-align:center;" title="Allocation No." property="serial_no" />
																<display:column style="text-align:center;" title="Reversal Date" property="allocateddate" />
																<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
																<display:column style="text-align:center;" title="Contract No" property="contractno" />
																<display:column style="text-align:center;" title="Currency" property="currencyName" />
																<display:column style="text-align:center;" title="Exchange Rate" property="exchangerate" />
																<display:column style="text-align:center;" title="Business Type" property="productname" />
																<display:column style="text-align:left;" title="TYPE">
																	<s:if test='"P".equals(#myrow.type)'>
																		<s:text name="PREMIUM" />
																	</s:if>
																	<s:if test='"C".equals(#myrow.type)'>
																		<s:text name="CLAIM" />
																	</s:if>	
																	<s:if test='"PT".equals(#myrow.type)'>
																		<s:text name="PAYMENT" />
																	</s:if>	
																	<s:if test='"RT".equals(#myrow.type)'>
																		<s:text name="RECEIPT" />
																	</s:if>							
																</display:column>
																<display:column style="text-align:right;" title="SIGN">
																		<s:if test='"P".equals(#myrow.type)'>
																			+
																		</s:if>
																		<s:if test='"C".equals(#myrow.type)'>
																			-
																		</s:if>
																		<s:if test='"PT".equals(#myrow.type)'>
																			+
																		</s:if>
																		<s:if test='"RT".equals(#myrow.type)'>
																			-
																		</s:if>
																</display:column>		
																<display:column style="text-align:right;" title="Reversal Amount" >
																<s:property value="#myrow.payamount" />
																</display:column>
																<display:column style="text-align:left;" title="Status" property="status" /><%--adjustMentType --%>
																<display:setProperty name="export.excel" value="true"/>
																<display:setProperty name="export.excel.filename" value="Reverted Contracts3.xls"/>
																<display:setProperty name="export.csv" value="false" />
																<display:setProperty name="export.xml" value="false" />
																<display:setProperty name="export.pdf" value="false" />
															</display:table>
														</div>
													</div>
												</div>
											</div>
										</s:if>
										<s:if test='"".equals(pay_rec_no) || pay_rec_no==null'>
										</s:if>
										<s:if test='!"".equals(pay_rec_no) && pay_rec_no!=null'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<table width="100%" style="margin: 0 auto;" class="footable display" >
														<thead>						
															<tr>
																<s:if test='"PT".equals(allocType)'>
																	<th width="12.5%"> <s:text name="ReceiptNo" /> </th>
																</s:if>
																<s:else>
																	<th width="12.5%"> <s:text name="PaymentNo" /> </th>
																</s:else>
																	<th width="12.5%"> <s:text name="Transaction Date" /> </th>
																	<th width="12.5%"> <s:text name="Gross Amount" /> </th>
																	<th width="12.5%"> <s:text name="Bank Charges" /> </th>
																	<th width="12.5%"> <s:text name="With Holding Tax Recoverable" /> </th>
																	<th width="12.5%"> <s:text name="Net Amount" /> </th>
																	<th width="12.5%"> <s:text name="Difference Amount" /> </th>
																	<th width="12.5%"> <s:text name="Exchange Rate" /> </th>
																	<th width="12.5%"> <s:text name="Gross Amount" /><s:property value="shortname"/> </th>
																</tr>			
															</thead>
														<tbody>
														<s:iterator value="tresauryRecPaytailsList" var="record">
														<tr>
															<td><s:property value="serial_no"/></td>
															<td align="right"><s:property value="tr_date"/></td>
															<td align="right"><s:property value="paymentamount"/></td>
															<td align="right"><s:property value="bankCharges"/></td>
															<td align="right"><s:property value="withHoldingTaxOC"/></td>
															<td align="right"><s:property value="netAmt"/></td>
															<td align="right"><s:property value="diffAmount"/></td>
															<td align="right"><s:property value="exchangerate"/></td>
															<td align="right"><s:property value="totalexchaamount"/></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
								</s:if>
								<div class="boxcontent" align="center">
									<s:if test='"VIEW".equals(flag)'>
										<%-- <input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="back5()" />--%>
										<a href="#" class="btn btn-sm btn-danger"	onClick="back5()">Back</a>
									</s:if>
									<s:if test='"REVVIEW".equals(flag)'>
										<%-- <input type="button" value="Close" class="btn btn-sm btn-danger" onclick="javascript:self.close();" />--%>
										<a href="#" class="btn btn-sm btn-danger"	onClick="javascript:self.close();">Close</a>
									</s:if>
										<%-- <input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	--%>
										<a href="#" class="btn btn-sm btn-primary"	id="print" onClick="printpage()">Print</a>
										<%-- <input type="button" value="Issue Receipt" id="print" class="btn btn-sm btn-primary"	onClick="fnReceipt()"/>--%>
										<%--
										<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
										<s:if test='"PT".equals(allocType)'>
										 <a href="#" class="btn btn-sm btn-success"	onClick="fnReceipt()">Issue Payment</a>
										 </s:if><s:else>
										 <a href="#" class="btn btn-sm btn-success"	onClick="fnReceipt()">Issue Receipt</a>
										</s:else>
										</s:if>
										 --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:if>
						<s:if test='"receiptNo".equalsIgnoreCase(partToShow)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">
									<div class="table2">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:if test='"PT".equals(allocType)'>
															<s:text name="Heading.PaymentList" />
														</s:if>
														<s:else>
															<s:text name="Heading.ReceiptList" />
														</s:else>
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RiskDetails.Broker" />
																</div>
																<div class="tbox">
																	<s:select name="broker" id="BrokerId" list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" onchange="GetCedingCompany(this.value)" headerKey="" headerValue="---Select---" />
																</div>
															</div>
															<div class="textfield" id="enddiv">
																<div class="text txtB">
																	<s:text name="RiskDetails.CedingCompany" />
																</div>
																<div class="tbox">
																	<s:if test="flag == null || flag == ''">
																		<s:select list="#{}" name="cedingCo" id="CeddingId" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" />
																	</s:if>
																	<s:else>
																		<s:select name="cedingCo" id="CeddingId" list="CeddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME"  cssClass="inputBoxS" headerKey="0" headerValue="---Select---" />
																	</s:else>
																</div>
															</div>
															<div class="textfield" id="enddiv">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.bank" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receiptBank" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:select name="receiptBankId" id="" list="bankMasterList" listKey="BANK_ID" listValue="BANK_NAME" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="paymentCurrencyajax(this.value,'CurrencyDiv');getpayamount();" onblur="GetExchangeRate();"  />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Payment.TransactionDate" />
																</div>
																<s:if test='"EDIT".equals(flag)'>
																<div class="tbox">
																	<div class="inputAppend">
																		<s:textfield name="tr_date" id="tr_date" disabled="true" cssStyle="width: 100%; border:transparent;" />	
																		<s:hidden name="tr_date" id="tr_date"/>
																	</div>
																</div>
																</s:if>
																<s:else>
																	<div class="tbox">
																	<s:textfield name="tr_date" id="tr_date"  cssClass="inputBox"  onchange="GetExchangeRate()"/>
																</div>
																</s:else>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.paymentCurrency" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receivedCurrency" />
																	</s:else>
																</div>
																<div class="tbox">
																	<div style="width: 50%; float:left;" id="CurrencyDiv">
																	<s:if test='bankCurrencyList!=null&&bankCurrencyList!=""'>
																		<s:select name="currency" id="currency" list="bankCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME"  cssClass="inputBoxS" onblur="GetExchangeRate();getBaseCurrencyAmount();" headerKey="0" headerValue="---Select---" />
																	</s:if>
																	<s:else>
																	<s:select name="currency" id="currency" list="#{}"  cssClass="inputBoxS" onchange="GetExchangeRate();getBaseCurrencyAmount();" headerKey="0" headerValue="---Select---"/>
																	</s:else>
																	</div>
																	<div id="paymentCurrency" style="text-align: right; width: 40%; float:left;">
																		<s:textfield name="exrate" id="exrate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" maxlength="30" onblur="getBaseCurrencyAmount();"/>
																	</div>
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.amount" />
																</div>
																<div class="tbox">
																	<s:textfield name="paymentamount" cssClass="inputBox" cssStyle="text-align: right;" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);getBaseCurrencyAmount();getNetAmount();"  onchange="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getBaseCurrencyAmount();getNetAmount();" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.bankCharges" />
																</div>
																<div class="tbox">
																	<s:textfield name="bankCharges" cssClass="inputBox" cssStyle="text-align: right;" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getNetAmount();"  />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.baseCurrencyAmount" />
																</div>
																<div class="tbox">
																	<s:textfield name="baseCurrencyAmount" id="baseCurrencyAmount" maxlength="30" cssClass="inputBox" cssStyle="text-align: right;" readonly="true" />
																</div>
															</div>
															<s:if test='"PT".equals(allocType)'>
                                                                	<s:hidden name="withHoldingTaxOC" id="withHoldingTaxOC" value="0"/>
                                                             </s:if>
                                                             <s:else>
																	<div class="textfield">
																		<div class="text txtB">
																			<s:if test='"PT".equals(allocType)'>
																				<s:text name="label.WithHoldingTaxPT" />
																			</s:if>
																			<s:else>
																				<s:text name="label.WithHoldingTax" />
																			</s:else>
		                                                                </div>
		                                                                <div class="tbox">
		                                                                    <s:textfield name="withHoldingTaxOC" cssClass="inputBox" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);this.value=Comma(this.value);getNetAmount();" cssStyle="text-align: right;" /></div>
		                                                            </div>
                                                             </s:else>
                                                             <div class="textfield">
                                                                <div class="text txtB">
                                                                    <s:text name="label.PremiumLavy" />
                                                                </div>
                                                                <div class="tbox">
                                                                	<s:textfield name="premiumLavy" id="premiumLavy" cssClass="inputBox" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);this.value=Comma(this.value);getNetAmount();" cssStyle="text-align: right;" /></div>
                                                                </div>
                                                            </div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.netAmt" />
																</div>
																<div class="tbox">
																	<s:textfield name="netAmt" cssClass="inputBox" maxlength="30" cssStyle="text-align: right;" readonly="true" />
																</div>
															</div>
															<s:if test='"EDIT".equals(flag)'>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Payment.AmendmentDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="amend_date" id="amend_date"  cssClass="inputBox"  />
																</div>
															</div>
															</s:if>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.remarks" />
																</div>
																<div class="tbox">
																	<s:textarea name="remarks" cssClass="inputBox" cssStyle="width: 90%; resize: vertical; "  />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.transactionType" />
																</div>
																<div class="tbox">
																	<s:select name="transactionType" list="transactionTypeList" listKey="TYPE" listValue="DETAIL_NAME" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="receiptdeteails(this.value)"/>
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											  <div class="boxcontent" align="center">
												<s:submit type="button" value="Back" cssClass="btn btn-sm btn-danger" onclick="back6()" />
											<!--  	<s:submit type="button" value="Submit" cssClass="btn btn-sm btn-success" onclick="receiptSubmit()" />-->
											</div>
										</div>
									</div>
								</div>
							</div>
							<s:hidden name="flag" id="flag"/>
							<s:hidden name="serial_no" id="serial_no"/>
						</s:if>
						<s:if test='"receiptNoGen".equals(partToShow)'>
							<div class="tablerow">
								<div style="padding: 10px; background: #F8F8F8">
									<div class="table2">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													
													<div class="panel-body">
														<div class="boxcontent">
														<s:if test='"EDIT".equalsIgnoreCase(flag)'>
														<div class="textfield">
																<div class="text txtB">
																<s:if test='"PT".equals(allocType)'>
																<s:text name="Payment Number" />
																	</s:if>
																	<s:else>
																	<s:text name="Receipt Number" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:property value="pay_rec_no" />
																</div>
															</div>
															</s:if>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RiskDetails.Broker" />
																</div>
																<div class="tbox">
																	<s:select name="broker" id="BrokerId" list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" onchange="GetCedingCompany(this.value)" headerKey="" headerValue="---Select---" disabled="true"/>
																	<s:hidden name="broker"/>
																</div>
															</div>
															<div class="textfield" id="enddiv">
																<div class="text txtB">
																	<s:text name="RiskDetails.CedingCompany" />
																</div>
																<div class="tbox">
																	<s:select name="cedingCo" id="CeddingId" list="CeddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME"  cssClass="inputBoxS" headerKey="0" headerValue="---Select---" disabled="true"/>
																	<s:hidden name="cedingCo"/>
																</div>
															</div>
															<div class="textfield" id="enddiv">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.bank" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receiptBank" />
																	</s:else>
																</div>
																<div class="tbox">
																	<s:select name="receiptBankId" id="" list="bankMasterList" listKey="BANK_ID" listValue="BANK_NAME" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="paymentCurrencyajax(this.value,'CurrencyDiv');getpayamount();" onblur="GetExchangeRate();" disabled="true" />
																		<s:hidden name="receiptBankId"></s:hidden>
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Payment.TransactionDate" />
																</div>
																<s:if test='"EDIT".equals(flag)'>
																<div class="tbox">
																	<div class="inputAppend">
																		<s:textfield name="tr_date" id="tr_date" disabled="true" cssStyle="width: 100%; border:transparent;" />	
																	</div>
																</div>
																</s:if>
																<s:else>
																<div class="tbox">
																	<s:textfield name="tr_date" id="tr_date"  cssClass="inputBox"  onchange="GetExchangeRate()"/>
																</div>
																</s:else>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:if test='"PT".equals(allocType)'>
																		<s:text name="payment.paymentCurrency" />
																	</s:if>
																	<s:else>
																		<s:text name="payment.receivedCurrency" />
																	</s:else>
																</div>
																<div class="tbox">
																	<div style="width: 50%; float:left;" id="CurrencyDiv">
																	<s:if test='bankCurrencyList!=null'>
																		<s:select name="currency" id="currency" list="bankCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME"  cssClass="inputBoxS" onblur="GetExchangeRate();getBaseCurrencyAmount();" headerKey="0" headerValue="---Select---" />
																	</s:if>
																	<s:else>
																		<s:select name="currency" id="currency" list="#{}"  cssClass="inputBoxS" onchange="GetExchangeRate();getBaseCurrencyAmount();" headerKey="0" headerValue="---Select---"/>
																	</s:else>
																	</div>
																	<div id="paymentCurrency" style="text-align: right; width: 40%; float:left;">
																		<s:textfield name="exrate" id="exrate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" maxlength="30" onchange="getBaseCurrencyAmount();"/>
																	</div>
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.amount" />
																</div>
																<div class="tbox">
																	<s:textfield name="paymentamount" id="paymentamount" cssClass="inputBox" cssStyle="text-align: right;" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);getBaseCurrencyAmount();getNetAmount();"  onchange="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getBaseCurrencyAmount();getNetAmount();" readonly="true" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.bankCharges" />
																</div>
																<div class="tbox">
																	<s:textfield name="bankCharges" cssClass="inputBox" cssStyle="text-align: right;" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getNetAmount();" readonly="true" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.baseCurrencyAmount" />
																</div>
																<div class="tbox">
																	<s:textfield name="baseCurrencyAmount" id="baseCurrencyAmount" maxlength="30" cssClass="inputBox" cssStyle="text-align: right;" readonly="true" />
																</div>
															</div>
															<s:if test='"PT".equals(allocType)'>
                                                                	<s:hidden name="withHoldingTaxOC" id="withHoldingTaxOC" value="0"/>
                                                             </s:if>
                                                             <s:else>
																	<div class="textfield">
																		<div class="text txtB">
																			<s:if test='"PT".equals(allocType)'>
																				<s:text name="label.WithHoldingTaxPT" />
																			</s:if>
																			<s:else>
																				<s:text name="label.WithHoldingTax" />
																			</s:else>
		                                                                </div>
		                                                                <div class="tbox">
		                                                                    <s:textfield name="withHoldingTaxOC" cssClass="inputBox" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);this.value=Comma(this.value);getNetAmount();" cssStyle="text-align: right;" readonly="true"/></div>
		                                                            </div>
                                                             </s:else>
                                                             <div class="textfield">
                                                                <div class="text txtB">
                                                                    <s:text name="label.PremiumLavy" />
                                                                </div>
                                                                <div class="tbox">
                                                                	<s:textfield name="premiumLavy" id="premiumLavy" cssClass="inputBox" maxlength="30" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);this.value=Comma(this.value);getNetAmount();" onchange="allow2DigitDecValues(this);this.value=Comma(this.value);getNetAmount();" cssStyle="text-align: right;" /></div>
                                                                </div>
                                                            </div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.netAmt" />
																</div>
																<div class="tbox">
																	<s:textfield name="netAmt" cssClass="inputBox" maxlength="30" cssStyle="text-align: right;" readonly="true" />
																</div>
															</div>
															<s:if test='"EDIT".equals(flag)'>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Payment.AmendmentDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="amend_date" id="amend_date"  cssClass="inputBox"  />
																</div>
															</div>
															</s:if>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.transactionType" />
																</div>
																<div class="tbox">
																	<s:select name="transactionType" list="transactionTypeList" listKey="TYPE" listValue="DETAIL_NAME" cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="receiptdeteails(this.value)" disabled="true"/>
																	<s:hidden name="transactionType"/>
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="payment.remarks" />
																</div>
																<div class="tbox">
																	<s:textarea name="remarks" cssClass="inputBox" cssStyle="width: 90%; resize: vertical; " />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<s:hidden name="flag" id="flag"/>
							<s:hidden name="serial_no" id="serial_no"/>
							<div class="boxcontent">
								<div class="panel-body">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:if test='"PT".equals(allocType)'>
												<s:text name="PAYMENT DETAILS" />
											</s:if>
											<s:else>
												<s:text name="RECEIPT DETAILS" />
											</s:else>
										</div>
										<div class="panel-body">
											<br class="clear"/>
											<s:if test='"Transaction".equals(transactionType)'>
											<table class="table table-bordered" width="100%" id="gen">
												<thead>
													<tr>
														<th> S.No. </th>
														<th width="15.8%"> <s:text name="Payment.Currency" /> </th>
														<th width="15.8%"> <s:text name="payment.amountoc" /> </th>
														<th width="15.8%"> <s:text name="payment.setExcRate" /> </th>
														<th width="15.8%"> 
															<s:if test='"PT".equals(allocType)'>
																<s:text name="payment.conPaidCurrency" />
															</s:if>
															<s:else>
																<s:text name="payment.conRecCurrency" />
															</s:else>
														</th>
														<th width="15.8%"> <s:text name="payment.exchangeRate" /> </th>
														<th width="15.8%"> <s:text name="payment.totalAmount" /> </th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="paymentList" var="pmt" status="stat">
														<tr>
															<td>
																<s:hidden name="recNoValList[%{#stat.count-1}]"/>
																<s:property value='%{#stat.count}'/>
															</td>											
															<td>
																<s:select name="cedingCompanyValList[%{#stat.count-1}]" id="cedingCompanyValList[%{#stat.count-1}]" list="orginalCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME"  cssClass="inputBoxS" headerKey="0" headerValue="---Select---" onchange="GetExchangeRate1(this.value,'%{#stat.count}');" />
															</td>
															<td>
																<s:textfield name="payamountValList[%{#stat.count-1}]" id="payamountValList[%{#stat.count-1}]" cssStyle="text-align: right;" cssClass="inputBox" onchange="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);calcu();getConRecCurrency();" maxlength="40" />
															</td>
															<td>
																<s:textfield name="setExcRateValList[%{#stat.count-1}]" id="setExcRateValList[%{#stat.count-1}]" cssStyle="text-align: right;" cssClass="inputBox"  onchange="this.value=Comma(this.value);" onkeyup="allow8DigitDecValues(this);middleMinusRestriction(this);getConRecCurrency();checkDecimalsValue(this,8);Itnegative(this.id,this.value);" maxlength="40"/>
															</td>
															<td>
																<s:textfield name="conRecCurValList[%{#stat.count-1}]" id="conRecCurValList[%{#stat.count-1}]"  cssStyle="text-align: right;" cssClass="inputBox"  onchange="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);getConRecCurrency1();getExchange();" maxlength="30"/>
															</td>
																		
															<td id="exachange<s:property value='%{#stat.count}'/>">
																<s:textfield name="exachangeValList[%{#stat.count-1}]" id="exachangeValList[%{#stat.count-1}]" readonly="true" cssStyle="text-align: right;" cssClass="inputBox" maxlength="30"/>
															</td>
															<td>
																<s:textfield name="rowamountValList[%{#stat.count-1}]" id="rowamountValList[%{#stat.count-1}]" readonly="true" cssStyle="text-align: right;" cssClass="inputBox" maxlength="30"/>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										<div class="boxcontent" align="right">
											<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="addRow('gen');" />
										</div>
									<div>
									<b></b>
									<table>
										<tbody>
											<tr>
												<td width="38%"><s:text name="Total" /></td>
												<td width="12.5%"> </td>
												<td width="15%" class="txtB">
													<s:textfield name="totalConRecCur" id="totConRecCur" cssClass="inputBox"  maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/></td>
												<td width="15%"></td>
												<td width="12.5%" class="txtB">
													<s:textfield name="txtTotalAmt" id="txtTotalAmt" cssClass="inputBox" maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/>
												</td>
												<s:hidden name="hidTotalAmt" id="hidTotalAmt" value="%{txtTotalAmt}"/>
											</tr>
											<tr>
												<td width="30%"><s:text name="Rounding off/Exchange Difference" /></td>
													<td width="13%" class="txtB">
														<s:select name="difftype1" id="difftype1" list="#{'B':'Add','M':'Subract'}" cssClass="inputBoxS" headerKey="N" headerValue="---Select---" disabled="true"/>
													</td>
													<td width="15%" class="txtB">
														<s:textfield name="convertedDiffAmount" id="convertedDiffAmount" cssClass="inputBox"  maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/></td>
													<td width="13%" class="txtB">
														<s:select name="difftype" id="difftype" list="#{'B':'Add','M':'Subract'}" cssClass="inputBoxS" headerKey="N" headerValue="---Select---" disabled="true"/>
													</td>
													<td width="12.5%" class="txtB">
														<s:textfield name="txtDiffPer" id="txtDiffPer" cssClass="inputBox" maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/>
													</td>
												</tr>
												<tr>
													<td width="36%"><s:text name="Difference Percentage(%)" /></td>
													<td width="12.5%"></td>
													<td width="15%" class="txtB">
														<s:textfield name="percentage1" id="percentage1" cssClass="inputBox"  maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/></td>
													<td width="15%"></td>
													<td width="12.5%" class="txtB">
															<s:textfield name="percentage2" id="percentage2" cssClass="inputBox" maxlength="40" size="width: 400px;" cssStyle="text-align: right;" readonly="true"/>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<s:hidden name="hideRowCnt" id="hideRowCnt"/>
								<br class="clear"/>									
							</s:if>
							<s:if test='"Treasury".equals(transactionType)'>
							<s:hidden name="type" />
								<display:table name="ReversalInfo" pagesize="0" requestURI="/initTreasury.action&flag=VIEW" class="table table-bordered" uid="row" id="record">
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found"	value="" />
									<display:setProperty name="paging.banner.placement" value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="true" style="text-align:center;" title="Select">
									<input type="radio" name="pay_rec_no" id="pay_rec_no" value="${record.pay_rec_no}" />
										<!-- html:radio property="pay_rec_no" value="${record.pay_rec_no}"  -->
									</display:column>
									<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:center;" title="Receipt No" property="pay_rec_no" />
									</s:if>
									<s:else>
									<display:column sortable="true" style="text-align:center;" title="Payment No" property="pay_rec_no" />
									</s:else>
									<display:column sortable="true" style="text-align:left;" title="Broker Name" property="brokername" />
									<display:column sortable="true" style="text-align:left;" title="Ceding Company" property="cedingCo" />
									<display:column sortable="true" style="text-align:left;" title="Currency" property="currencyName" />
									<display:column sortable="true" style="text-align:right;" title="Exchange Rate" property="exrate" />
									<s:if test='"PT".equals(allocType)'>
									<display:column sortable="true" style="text-align:right;" title="Receipt Amount" property="paymentamount" />
									</s:if>
									<s:else>
									<display:column sortable="true" style="text-align:right;" title="Payment Amount" property="paymentamount" />
									</s:else>
									<display:column sortable="true" style="text-align:center;"  title="View" >
										<a class="tooltip" title="View"><img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif"  onclick="funNewModes('${record.pay_rec_no}','${record.brokerid}')"  alt="View" width="12" height="17"></a>
									</display:column>
								</display:table>
							</s:if>
						</div>							
					</div>
				</div>					
			</div>
			<s:if test='"Transaction".equals(transactionType)'>
			<div class="boxcontent" align="center">
				<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="receiptSubmit()" />
				<input type="button" value="Cancel" class="btn btn-sm btn-warning" onclick="back1()" />
			</div>
			</s:if>
			<s:elseif test='"Treasury".equals(transactionType)'>
			<div class="boxcontent" align="center">
				<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="receiptSubmit1()" />
				<input type="button" value="Cancel" class="btn btn-sm btn-warning" onclick="back1()" />
			</div>
			</s:elseif>
		</s:if>
	</div>
	<s:hidden name="allocType"  id="allocType"/>
	<s:hidden name="type"/>	
	<s:hidden name="transactionTypeTest"/>
	</s:form>
</div>
</div>
<script type="text/javascript">
   var form = $('form[name="PaymentForm"]');
         function funMove(receiptNo,type){
         	document.PaymentForm.action="${pageContext.request.contextPath}/allocationTreasury.action?pay_rec_no="+receiptNo+"&allocateType="+type;
			document.PaymentForm.submit();
         }
		function GetExchangeRate1(value,i) {
		var incDate=document.PaymentForm.tr_date.value;
			var Currecny=value;
			if(incDate!=null && incDate !="" && Currecny!=null && Currecny!="0" ){
			var id='exachange'+i;
		 		//var URL='${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exachange&count='+(i-1);
	  	 		//postRequest(URL,'exachange'+i);
	  	 		$.ajax({
			        type: "POST",
			        url:'${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exachange&count='+(i-1),
			        //data:'src_type_id='+val,
			        success: function(data){
			            $('#' + id).html(data);
			           calcu();
			        }
		        });
	        }else{
				document.getElementById("exachangeValList["+(i-1)+"]").value='0.00';
				document.getElementById("rowamountValList["+(i-1)+"]").value='0.00';
				
			}	
		}
		function GetExchangeRate() {
			var InceptionDate=document.PaymentForm.tr_date.value;
			var Currecny=document.PaymentForm.currency.value;
			postRequest("${pageContext.request.contextPath}/getExcRateTreasury?dropDown=paymentCurrency&incepDate="+InceptionDate+"&orginalCurrency="+Currecny, "paymentCurrency");
		}
	
		function GetCedingCompany(val)
		{		
			if(val!='63') {
				document.getElementById("enddiv").style.display = "none";
		 		return false;
			}
			else {
		 		document.getElementById("enddiv").style.display="inline";
		    	var broker=document.PaymentForm.broker.value;    	 
				 	if (window.XMLHttpRequest) {
						xmlhttp=new XMLHttpRequest();
				    } else {
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function() {
					if (xmlhttp.readyState==4 && xmlhttp.status==200) {
						var a=xmlhttp.responseText.trim().split("~");
						var j=0;
						var opt = document.PaymentForm.cedingCo.options.length=0;
						opt = document.createElement("option");
						opt.value ='0';
						opt.text = '--Select--';
						document.PaymentForm.cedingCo.options.add(opt);
						while(j<a.length) {	
							opt = document.createElement("option");
							opt.value = a[j];
							j++;
							opt.text = a[j];
					    	document.PaymentForm.cedingCo.options.add(opt);
				    		j++;
				    	}         
					}
				};
				xmlhttp.open("POST",'<%=request.getContextPath()%>/directCedingTreasury.do?broker='+broker,true);
				xmlhttp.send();
			}
		}
		function funNewMode() {
			document.PaymentForm.action="<%=request.getContextPath()%>/PaymentInit.do?method=newReceipt";
			document.PaymentForm.submit();	
		}
		function ref(val) {	
			document.getElementById("brokerid").value=val;
			document.PaymentForm.action='<%=request.getContextPath()%>/payment.jsp';
			document.PaymentForm.submit();	
		}
		function back5() {
			document.PaymentForm.serial_no.value="";
			document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
			document.PaymentForm.submit();	
		}
		function back1() {
		  	document.PaymentForm.flag.value='';
			document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action?cancelType=C";
			document.PaymentForm.submit();		
		}
		function back6() {
			document.PaymentForm.serial_no.value="";
			document.PaymentForm.flag.value="";
			document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
			document.PaymentForm.submit();	
		}
		function funCancel() {
			document.PaymentForm.flag.value="";
			document.PaymentForm.action='${pageContext.request.contextPath}/initTreasury.action';
			document.PaymentForm.submit();
		}		
		function insert(flag) {
			document.getElementById("flag").value=flag;
			document.PaymentForm.action="<%=request.getContextPath()%>/PaymentInit.do?method=Insert";
			document.PaymentForm.submit();
		}
		function receiptSubmit1() {
			enableForm(document.forms['PaymentForm'],false,'tr_date');
			document.PaymentForm.action="${pageContext.request.contextPath}/receiptSubmitTreasury.action";
			document.PaymentForm.submit();
		}
	function receiptSubmit() {
		var id=document.getElementById("hideRowCnt").value;
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		for(i=0;i<rowCount;i++) {
				if(document.getElementById('payamountValList['+i+']')!=null && document.getElementById('payamountValList['+i+']').value!="" &&document.getElementById('setExcRateValList['+i+']')!=null && document.getElementById('setExcRateValList['+i+']').value!="" && document.getElementById('conRecCurValList['+i+']')!=null && document.getElementById('conRecCurValList['+i+']').value!="" ){
				var payamoutlist=document.getElementById('payamountValList['+i+']').value;
				var exchageamoutlist=document.getElementById('setExcRateValList['+i+']').value;
				var conamoutlist=document.getElementById('conRecCurValList['+i+']').value;
				payamoutlist = payamoutlist.replace(new RegExp(',','g',),'');
				exchageamoutlist = exchageamoutlist.replace(new RegExp(',','g'),'');
				conamoutlist = conamoutlist.replace(new RegExp(',','g'),'');
				var dived=parseFloat(payamoutlist/exchageamoutlist).toFixed(2);
				var dived2=parseFloat(payamoutlist/conamoutlist).toFixed(8);
				if(dived!=conamoutlist && dived2!=exchageamoutlist){
				document.getElementById('conRecCurValList['+i+']').value=Math.abs(dived);	
					}
				}
			}
	var convertedDiffAmount=document.getElementById("convertedDiffAmount").value;
	convertedDiffAmount = convertedDiffAmount.replace(new RegExp(',','g'),'');
	var TotalPercentage=document.getElementById("percentage2").value;
	TotalPercentage = TotalPercentage.replace(new RegExp(',','g'),'');
	convertedDiffAmount=Math.abs(convertedDiffAmount);
	TotalPercentage=Math.abs(TotalPercentage);
	<%--if(convertedDiffAmount>10)
	{
	var amount=confirm("Rounding off amount is more than 10.Do you wish to continue?");
	if(amount){
	if(TotalPercentage>5){
	var amount1=confirm("Exchange difference is significantly high. Do you wish to continue?");
	if(amount1){
	enableForm(document.forms['PaymentForm'],false,'tr_date');
		document.PaymentForm.action="${pageContext.request.contextPath}/receiptSubmitTreasury.action";
		document.PaymentForm.submit();
	} 
	} 
	else{
	enableForm(document.forms['PaymentForm'],false,'tr_date');
		document.PaymentForm.action="${pageContext.request.contextPath}/receiptSubmitTreasury.action";
		document.PaymentForm.submit();
	}
	}
	}--%>
	 if(TotalPercentage>5){
	var amount1=confirm("Exchange difference is significantly high. Do you wish to continue?");
	if(amount1){
	enableForm(document.forms['PaymentForm'],false,'tr_date');
		document.PaymentForm.action="${pageContext.request.contextPath}/receiptSubmitTreasury.action";
		document.PaymentForm.submit();
	} 
	}
	else{
		enableForm(document.forms['PaymentForm'],false,'tr_date');
		document.PaymentForm.action="${pageContext.request.contextPath}/receiptSubmitTreasury.action";
		document.PaymentForm.submit();
		}
	}		 
			 
	function newPayment() {
		document.getElementById("flag").value="insert"; 
		document.PaymentForm.action="<%=request.getContextPath()%>/PaymentInit.do?method=Init&type=PT";
		document.PaymentForm.submit();
	}
		 
	function AddMode(pay_rec_no,serial_no) {
		document.getElementById("pay_rec_no").value=pay_rec_no;
		document.getElementById("serial_no").value=serial_no;
		document.getElementById("flag").value="add";
		document.PaymentForm.action="<%=request.getContextPath()%>/PaymentInit.do?method=List";
		document.PaymentForm.submit();
	}

	function getJurnal(value) {
		document.PaymentForm.serial_no.value=value;
	 	document.PaymentForm.flag.value="JOURNAL";
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
		document.PaymentForm.submit();		
	}
	
	function funReverseMode(recNo,broker,cedCo) {
	  	document.PaymentForm.serial_no.value=recNo;
	  	document.PaymentForm.broker.value=broker;
	  	document.PaymentForm.cedingCo.value=cedCo;
	   	document.PaymentForm.flag.value="REVERSE";
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
		document.PaymentForm.submit();
	}
	
	function AddNewReceipt(type) {
		document.PaymentForm.action="${pageContext.request.contextPath}/newReceiptTreasury.action?flag=new";
		document.PaymentForm.submit();
	}
	
	function funNewModes(value,value1) {
	   	document.PaymentForm.serial_no.value=value;
		document.PaymentForm.flag.value="VIEW";
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action?broker="+value1;
		document.PaymentForm.submit();
	}
	
	function allocate(pay_rec_no,serial_no,cedingid,brokerid) {
		document.getElementById("pay_rec_no").value=pay_rec_no;
		document.getElementById("serial_no").value=serial_no;
		document.getElementById("cedingid").value=cedingid;
		document.getElementById("broker").value=brokerid;
		document.getElementById("flag").value="view";
		document.PaymentForm.action="${pageContext.request.contextPath}/allocationTreasury";
		document.PaymentForm.submit();
	}
	if('S'=='<s:property value="searchType"/>'){
	detailsClick(true);
	}
	function detailsClick(val) {
		if(val){
	  		document.getElementById('detailsData').style.display='block';		
			document.getElementById('detailsMinus').style.display='block';
			document.getElementById('detailsPlus').style.display='none';
	    }else{
	    	document.getElementById('detailsData').style.display='none';
			document.getElementById('detailsMinus').style.display='none';
			document.getElementById('detailsPlus').style.display='block';
	    }
	}
	function funSearchMode(mode){
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action?searchType="+mode;
		document.PaymentForm.submit();
	}
	function showFullData(){
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action?fullSearch=Yes";
		document.PaymentForm.submit();
		
	}
	$('#paymentNoSearch').on( 'keyup', function () {
	$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
	} );
	$('#brokerNameSearch').on( 'keyup', function () {
	$('#gridTableMake').DataTable().columns(2).search(this.value).draw();
	} );
	$('#companyNameSearch').on( 'keyup', function () {
	$('#gridTableMake').DataTable().columns(3).search(this.value).draw();
	} );
	$('#remarksSearch').on( 'keyup', function () {
	$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
	} );
	function divdis() {
		var BrokerId=document.getElementById("BrokerId").value;
		if(BrokerId!='63') {
			if(document.getElementById("enddiv")!=null) {	
	 			document.getElementById("enddiv").style.display = "none";
	 		}
	 		return false;
		}
	}
	
	getBaseCurrencyAmount();
	
	function getBaseCurrencyAmount(){
		if(document.PaymentForm.exrate.value!=null && document.PaymentForm.exrate.value!="" && document.PaymentForm.exrate.value!=0 && document.PaymentForm.paymentamount.value!=null && document.PaymentForm.paymentamount.value!="") {
			var amt=document.PaymentForm.paymentamount.value;
			amt=amt.replace(new RegExp(',', 'g'),'');
			var rate=document.PaymentForm.exrate.value;
			var baseamt=(amt/rate);
			document.PaymentForm.baseCurrencyAmount.value=Comma(baseamt.toFixed(2));
		}else {
			 document.PaymentForm.baseCurrencyAmount.value=parseFloat('0').toFixed(2);
		}
	}
	
	getNetAmount();
	
	function getNetAmount(){
	
        var wHTaxOCi = form.find('input[name="withHoldingTaxOC"]');
//        console.log(wHTaxOCi);
        var wHTaxOC = wHTaxOCi.val().replace(new RegExp(',', 'g'),'') || "0";
		var allocType = document.PaymentForm.allocType.value;
		//alert(allocType);
		if(document.PaymentForm.bankCharges.value!=null && document.PaymentForm.bankCharges.value!="" && document.PaymentForm.paymentamount.value!=null && document.PaymentForm.paymentamount.value!="" && document.PaymentForm.premiumLavy.value!=null && document.PaymentForm.premiumLavy.value!="")
		{
			var amt=document.PaymentForm.paymentamount.value;
			amt=amt.replace(new RegExp(',', 'g'),'');
			var bankCharges=document.PaymentForm.bankCharges.value;
			bankCharges=bankCharges.replace(new RegExp(',', 'g'),'');
			var premiumLavy=document.PaymentForm.premiumLavy.value;
			premiumLavy=premiumLavy.replace(new RegExp(',', 'g'),'');
            var netAmount;
			if(allocType=="PT") {
                netAmount = (parseFloat(amt) + parseFloat(bankCharges))- parseFloat(wHTaxOC)-parseFloat(premiumLavy);
			}
			else {
				netAmount=(parseFloat(amt)-parseFloat(bankCharges))- parseFloat(wHTaxOC)-parseFloat(premiumLavy);
			}
            //netAmount = netAmount - parseFloat(wHTaxOC);
			//var netAmount=(parseFloat(amt)-parseFloat(bankCharges));
			document.PaymentForm.netAmt.value=Comma(netAmount.toFixed(2));
		}else
		{
			 document.PaymentForm.netAmt.value=parseFloat('0').toFixed(2);
		}
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
	function getDocList(tranNo,compName,brokeeName,type){
		document.getElementById("tranNo").value=tranNo;
		document.getElementById("companyName").value=compName;
		document.getElementById("brokerName").value=brokeeName;
		document.PaymentForm.type.value=type;
		document.PaymentForm.action="${pageContext.request.contextPath}/documentUpload.action";
	    document.PaymentForm.submit();
	}
	
	function funEditMode(value) {
		document.PaymentForm.serial_no.value=value;
		document.PaymentForm.flag.value="EDIT";
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
		document.PaymentForm.submit();
	} 
	function search(){
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action";
		document.PaymentForm.submit();	
	} 
	function printpage()
		{
		document.PaymentForm.action="${pageContext.request.contextPath}/printTreasury.action?flag=PRINT";
		document.PaymentForm.submit();
		}
	function fnReceipt()
		{
		var val = document.PaymentForm.serial_no.value;
		var allocType = document.PaymentForm.allocType.value;
		//document.PaymentForm.action="${pageContext.request.contextPath}/treansactionPdfReports.action?receiptNo="+val+"&typeId="+allocType;
		//document.PaymentForm.submit();
		var URL="${pageContext.request.contextPath}/treansactionPdfReports.action?receiptNo="+val+"&typeId="+allocType;
	  	postRequest(URL,'');
		}	
		
	function receiptdeteails(type){
	document.PaymentForm.transactionType.value=type;
	document.PaymentForm.action="${pageContext.request.contextPath}/receiptdeteailsTreasury.action";
	document.PaymentForm.submit();
	}
	
	function calcu() {
		var totamtval=0;
		var amount=0;
		var rate=0;
		var totamt=0;
		var diffPer=0;
		var roundconvert=0;
		var roundtotal=0;
		var percentage2=0;
		//var rateamt=0;
		id=document.getElementById("hideRowCnt").value;
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		//document.PaymentForm.receiptamt.value=parseFloat(rateamt).toFixed(2);
		//alert(rateamt);
		<%--
		<s:iterator value="paymentList" var="pmt" status="stat">
		 
			totamt=0;
			if(document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]')!=null && document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]').value!="" && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]')!=null && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!="") {
	 			amount=parseFloat(document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value);	
     			rate=parseFloat(document.getElementById('exachangeValList[<s:property value="%{#stat.count-1}"/>]').value)	
			}
			else {
				amount=0;
				rate=0;
			}	
		 	if(rate!='0') {
				totamt=amount/rate;
			}	 
			document.getElementById('rowamountValList[<s:property value="%{#stat.count-1}"/>]').value=parseFloat(totamt).toFixed(2);
			totamtval=totamtval+parseFloat(totamt);
		</s:iterator>
		--%>
		
		for(i=0;i<rowCount;i++) {
			totamt=0;
			
			if(document.getElementById('exachangeValList['+i+']')==null || document.getElementById('exachangeValList['+i+']').value=="" ){
			if(document.getElementById('exrate')!=null && document.getElementById('exrate').value!="" && document.getElementById('payamountValList['+i+']')!=null && document.getElementById('payamountValList['+i+']').value!="") {
	 			amount=document.getElementById('payamountValList['+i+']').value;	
     			rate=document.getElementById('exrate').value;
     			amount=parseFloat(amount.replace(new RegExp(',', 'g'),''));
     			rate=parseFloat(rate.replace(new RegExp(',', 'g'),''));
			}
			else {
				amount=0;
				rate=0;
			}	
			}
			else{
			if(document.getElementById('exachangeValList['+i+']')!=null && document.getElementById('exachangeValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']')!=null && document.getElementById('payamountValList['+i+']').value!="") {
	 			amount=document.getElementById('payamountValList['+i+']').value;	
     			rate=document.getElementById('exachangeValList['+i+']').value;
     			amount=parseFloat(amount.replace(new RegExp(',', 'g'),''));
     			rate=parseFloat(rate.replace(new RegExp(',', 'g'),''));
			}
			else {
				amount=0;
				rate=0;
			}	
			}
		 	if(rate!='0') {
				totamt=amount/rate;
			}	
			//alert(totamt); 
			document.getElementById('rowamountValList['+i+']').value=Comma(parseFloat(totamt).toFixed(2));
			totamtval=totamtval+parseFloat(totamt);
		}
		document.getElementById("txtTotalAmt").value=Comma(parseFloat(totamtval).toFixed(2));
		document.getElementById("hidTotalAmt").value=Comma(parseFloat(totamtval).toFixed(2));
		var amt1=document.getElementById("baseCurrencyAmount").value;
			amt1=amt1.replace(new RegExp(',', 'g'),'');
			roundtotal=amt1-totamtval;
			if(roundtotal<0){
			document.getElementById("difftype").value="M";
			}
			else if(roundtotal>0){
			document.getElementById("difftype").value="B";
			}
			percentage2=(roundtotal*100)/amt1;
			document.getElementById("percentage2").value=Comma(parseFloat(percentage2).toFixed(2));
			document.getElementById("txtDiffPer").value=Comma(parseFloat(roundtotal).toFixed(2));
		var amt=document.PaymentForm.paymentamount.value;
			amt=amt.replace(new RegExp(',', 'g'),'');
			var rate=document.PaymentForm.exrate.value;
			var baseamt=(amt/rate);
		var receiptAmount1=baseamt;
		var totalAmount=parseFloat(totamtval).toFixed(2);
		var diffAmount=parseFloat(receiptAmount1)-parseFloat(totalAmount);
	}
	function getExchange(){
	var totamtval=0;
     	var amount=0;
    	var setExcrate=0
     	var totamt=0;
     	var rountconvert=0;
     	var percentage1=0;
		var id=document.getElementById("hideRowCnt").value;
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		for(i=0;i<rowCount;i++) {
			totamt=0;
			if(document.getElementById('conRecCurValList['+i+']').value!=null && document.getElementById('conRecCurValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']').value!=null && document.getElementById('payamountValList['+i+']').value!="") {
				 amount=document.getElementById('payamountValList['+i+']').value;	
			     setExcrate=document.getElementById('conRecCurValList['+i+']').value;
			     amount=parseFloat(amount.replace(new RegExp(',', 'g'),''));
			     setExcrate=parseFloat(setExcrate.replace(new RegExp(',', 'g'),''));
			     
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
				 totamt=amount/setExcrate;
				 totamtval+=totamt;
			}	
			var tot=parseFloat(totamt).toFixed(8); 
			if(	tot!=0){	
			document.getElementById('setExcRateValList['+i+']').value=tot;
	}
	}
	}
	function getConRecCurrency() {	
		var totamtval=0;
     	var amount=0;
    	var setExcrate=0
     	var totamt=0;
     	var rountconvert=0;
     	var percentage1=0;
		var id=document.getElementById("hideRowCnt").value;
		<%-- 
		<s:iterator value="paymentList" var="pmt" status="stat">
			totamt=0;
			if(document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value!=null && document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value!="" && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!=null && document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value!="") {
				 amount=parseFloat(document.getElementById('payamountValList[<s:property value="%{#stat.count-1}"/>]').value);	
			     setExcrate=parseFloat(document.getElementById('setExcRateValList[<s:property value="%{#stat.count-1}"/>]').value)
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
				 totamt=amount/setExcrate;
				 totamtval+=totamt;
			}	 
			document.getElementById('conRecCurValList[<s:property value="%{#stat.count-1}"/>]').value=parseFloat(totamt).toFixed(2);
			document.getElementById("totConRecCur").value=parseFloat(totamtval).toFixed(2);
		</s:iterator>
		--%>
		
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		for(i=0;i<rowCount;i++) {
			totamt=0;
			if(document.getElementById('setExcRateValList['+i+']').value!=null && document.getElementById('setExcRateValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']').value!=null && document.getElementById('payamountValList['+i+']').value!="") {
				 amount=document.getElementById('payamountValList['+i+']').value;	
			     setExcrate=document.getElementById('setExcRateValList['+i+']').value;
			     amount=parseFloat(amount.replace(new RegExp(',', 'g'),''));
			     setExcrate =parseFloat( setExcrate.replace(new RegExp(',', 'g'),''));
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
			
				 totamt=amount/setExcrate;
				 totamtval+=totamt;
			}	 
			document.getElementById('conRecCurValList['+i+']').value=Comma(parseFloat(totamt).toFixed(2));
			document.getElementById("totConRecCur").value=Comma(parseFloat(totamtval).toFixed(2));
			var amt=document.getElementById("paymentamount").value;
			amt=amt.replace(new RegExp(',', 'g'),'');
			rountconvert=amt-totamtval;
			rountconvert = parseFloat(rountconvert).toFixed(2);
			if(rountconvert<0){
			document.getElementById("difftype1").value="M";
			}
			else if(rountconvert>0){
			document.getElementById("difftype1").value="B";
			}
			percentage1=(rountconvert*100)/amt;
			percentage1 = parseFloat(percentage1).toFixed(2);
			if(parseFloat(percentage1)==-0.00){
			percentage1="0.00";
			}
			if(parseFloat(rountconvert)==-0.00){
			rountconvert="0.00";
			}
			document.getElementById("percentage1").value=Comma(percentage1);
			document.getElementById("convertedDiffAmount").value=Comma(rountconvert);
		}
	}
	function getConRecCurrency1() {	
		var totamtval=0;
     	var amount=0;
    	var setExcrate=0
     	var totamt=0;
     	var rountconvert=0;
     	var percentage1=0;
		var id=document.getElementById("hideRowCnt").value;
		var rowCount = parseInt(document.getElementById("hideRowCnt").value);
		//alert(rowCount);
		for(i=0;i<rowCount;i++) {
			totamt=0;
			if(document.getElementById('setExcRateValList['+i+']').value!=null && document.getElementById('setExcRateValList['+i+']').value!="" && document.getElementById('payamountValList['+i+']').value!=null && document.getElementById('payamountValList['+i+']').value!="") {
				 amount=document.getElementById('conRecCurValList['+i+']').value;
				 amount=parseFloat(amount.replace(new RegExp(',', 'g'),''));
			     setExcrate=document.getElementById('setExcRateValList['+i+']').value;
			     setExcrate = parseFloat(setExcrate.replace(new RegExp(',', 'g'),''));
			}else {
				 amount=0;
				 setExcrate=0;
			}	
			if(setExcrate!='0') {
				 totamt=amount;
				 totamtval+=totamt;
				// alert(totamtval);
			}	 
			//document.getElementById('conRecCurValList['+i+']').value=parseFloat(totamt).toFixed(2);
			document.getElementById("totConRecCur").value=Comma(parseFloat(totamtval).toFixed(2));
			var amt=document.getElementById("paymentamount").value;
			amt=amt.replace(new RegExp(',', 'g'),'');
			rountconvert=amt-totamtval;
			rountconvert = parseFloat(rountconvert).toFixed(2);
			if(rountconvert<0){
			document.getElementById("difftype1").value="M";
			}
			else if(rountconvert>0){
			document.getElementById("difftype1").value="B";
			}
			percentage1=(rountconvert*100)/amt;
			percentage1 = parseFloat(percentage1).toFixed(2);
			if(parseFloat(percentage1)==-0.00){
			percentage1="0.00";
			}
			if(parseFloat(rountconvert)==-0.00){
			rountconvert="0.00";
			}
			document.getElementById("percentage1").value=Comma(percentage1);
			document.getElementById("convertedDiffAmount").value=Comma(rountconvert);
		}
	}
	var rowIdCNT=1;
	function addRow(tab) {		 	
	    var tabObj;
	    var len=0;
	    tabObj=document.getElementById(tab);
	    len=tabObj.rows.length;
	    document.getElementById("hideRowCnt").value=len;
	    //var len1=parseInt(len);
	    var len1=parseInt(len)-1;
	    var len2=parseInt(len);
	    var Chemtype="";
	    Chemtype+='<option value="0">--Select---';
	    
	    <s:iterator value="orginalCurrencyList" status="stat">
	    	Chemtype+='<option value="<s:property value="CURRENCY_ID"/>"><s:property value="CURRENCY_NAME"/>';
	    </s:iterator>
	     <%-- if(cedding!=null && cedding.size()>0){
			  for (int i=0;i<cedding.size();i++) {
				  	 Map  entry=(Map) cedding.get(i);%>						
	   				Chemtype+='<option value="<%=entry.get("CURRENCY_ID").toString()%>"><%=entry.get("CURRENCY_NAME").toString() %>';
	   	<%}}--%>
	   	
	   	var val=new Array();
	   	//val[0]='<span class="formtxtf"  style="width:4%;text-align:right;" ><input type="hidden" name="recNo'+len1+'" value="">'+(len1)+'&nbsp;&nbsp;&nbsp;</span>';
	    //val[1]='<span class="formtxtf"  style="width:16%;text-align:right;" ><select name="cedingCompany'+len1+'" id="cedingCompany'+len1+'" onchange="GetExchangeRate(this.value,'+len1+');calcu();" class="inputBoxS">'+Chemtype+'</select></span>';  
	    //val[2]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="payamount'+len1+'" id="payamount'+len1+'"  class="inputBox" onblur="calcu();getConRecCurrency();"  onkeyup="allow2DigitDecValues(this)"></span> ';
	    //val[3]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="setExcRate'+len1+'" id="setExcRate'+len1+'"  class="inputBox" onblur="getConRecCurrency();"    onkeyup="checkDecimals15(this)"></span> ';
	    //val[4]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="conRecCur'+len1+'" id="conRecCur'+len1+'" readonly="true" class="inputBox"   maxlength=10 ></span> ';
	    //val[5]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="exachange'+len1+'" id="exachange'+len1+'" readonly="true" class="inputBox"    maxlength=10 ></span>';
	    //val[6]='<span class="formtxtf"  style="width:16%;text-align:right;"><input type="text" style="text-align:right;" name="rowamount'+len1+'" id="rowamount'+len1+'" readonly="true" class="inputBox"   maxlength=10 ></span>';
		
		val[0]='<input type="hidden" name="recNoValList['+len1+']" value="">'+(len2)+'&nbsp;&nbsp;&nbsp;</span>';
	    val[1]='<select name="cedingCompanyValList['+len1+']" id="cedingCompanyValList['+len1+']" onchange="GetExchangeRate1(this.value,'+len2+');calcu();" class="inputBoxS">'+Chemtype+'</select>';  
	    val[2]='<input type="text" style="text-align:right;" name="payamountValList['+len1+']" id="payamountValList['+len1+']"  class="inputBox" onchange="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);calcu();getConRecCurrency();">';
	    val[3]='<input type="text" style="text-align:right;" name="setExcRateValList['+len1+']" id="setExcRateValList['+len1+']" class="inputBox" onchange="this.value=Comma(this.value);" onkeyup="allow8DigitDecValues(this);getConRecCurrency();checkDecimalsValue(this,8);">';
	    val[4]='<input type="text" style="text-align:right;" name="conRecCurValList['+len1+']" id="conRecCurValList['+len1+']" value="0.00"   onchange="this.value=Comma(this.value);" onkeyup="allow2DigitDecValues(this);getConRecCurrency1();getExchange();"  class="inputBox" maxlength=10 >';
	    val[5]='<input type="text" style="text-align:right;" name="exachangeValList['+len1+']" id="exachangeValList['+len1+']" readonly="true" class="inputBox" maxlength=10 >';
	    val[6]='<input type="text" style="text-align:right;" name="rowamountValList['+len1+']" id="rowamountValList['+len1+']" value="0.00" readonly="true" class="inputBox" maxlength=10 >';
		  
	    var temprowIdCNT=rowIdCNT;
	    row = tabObj.insertRow(len);
	    //row.id ='rowId'+temprowIdCNT;
	    //document.getElementById("hideRowCnt").value=len1;
	    document.getElementById("hideRowCnt").value=len2;
	    row.onclick=function(){
	
	    };
	
	    for(i=0;i<val.length;i++){
			cell = row.insertCell(i);
			if(i==5) {
	        	cell.id = "exachange" + len2;
	        } 
	        cell.innerHTML=val[i];
	    }
	    rowIdCNT++;
	}
	<s:if test='!"new".equals(flag)'>
	paymentCurrencyajax('<s:property value="receiptBankId"/>','CurrencyDiv');
	</s:if>
	function getpayamount(){
	document.PaymentForm.baseCurrencyAmount.value=parseFloat('0').toFixed(2);
	document.PaymentForm.paymentamount.value=parseFloat('0').toFixed(2);
	}
	function paymentCurrencyajax(currency,div){
	var URL="${pageContext.request.contextPath}/currencyajexTreasury.action?dropDown=currency&currencyId="+currency;
	postRequest(URL,div);
	}
	function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
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
</script>
	</body>
</html>
