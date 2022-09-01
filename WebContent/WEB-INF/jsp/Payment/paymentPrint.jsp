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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
		<style type="text/css">
	 @media print
			{    
			    .no-print, .no-print *
			    {
			        display: none !important;
			    }
			}
		.footable > tbody > tr > td {
	  padding: 1px;
	}
	@page 
        {
            size: legal;   /* auto is the current printer page size */
            margin:0cm;
        }
     @media print { html, body { height: 99%; } }   
}
	
	
	</style>
	</head>

	<body onload="divdis();">
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<s:form id="PaymentForm" name="PaymentForm" theme="simple" action="" method="post">
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
																	<!--<s:hidden name="serial_no" />
																--></div>
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
																		<s:text name="payment.diffAmount" />
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
															<display:table name="detailsList" pagesize="20"
																requestURI=""
																class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found"
																	value="" />
																<display:setProperty
																	name="paging.banner.one_items_found" value="" />
																<display:setProperty
																	name="paging.banner.all_items_found" value="" />
																<display:setProperty
																	name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement"
																	value="bottom" />
																<display:setProperty name="paging.banner.onepage"
																	value="" />

																<display:column sortable="false"
																	style="text-align:center;" title="S.No."
																	property="pay_res" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false"
																		style="text-align:center;" title="Payment No"
																		property="serial_no" />
																	<display:column sortable="false"
																		style="text-align:center;" title="Payment Currency"
																		property="currency" />
																</s:if>
																<s:else>
																	<display:column sortable="false"
																		style="text-align:center;" title="Receipt No"
																		property="serial_no" />
																	<display:column sortable="false"
																		style="text-align:center;" title="Received Currency"
																		property="currency" />
																</s:else>
																<display:column sortable="false"
																	style="text-align:right;" title="Amount"
																	property="payamount" />
																<display:column sortable="false"
																	style="text-align:right;" title="Settled Exchange Rate"
																	property="setExcRate" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false"
																		style="text-align:right;"
																		title="Converted Paid Amount" property="conRecCur" />
																</s:if>
																<s:else>
																	<display:column sortable="false"
																		style="text-align:right;"
																		title="Converted Receipt Amount" property="conRecCur" />
																</s:else>
																<display:column sortable="false"
																	style="text-align:right;" title="Exchange Rate"
																	property="exrate" />
																<display:column sortable="false"
																	style="text-align:right;" title="Total Amount"
																	property="totAmount" />
															</display:table>
														</s:if>
														<s:else>
															<display:table name="detailsList" pagesize="0"
																requestURI=""
																class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found"
																	value="" />
																<display:setProperty
																	name="paging.banner.one_items_found" value="" />
																<display:setProperty
																	name="paging.banner.all_items_found" value="" />
																<display:setProperty
																	name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement"
																	value="bottom" />
																<display:setProperty name="paging.banner.onepage"
																	value="" />

																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false"
																		style="text-align:center;" title="Receipt No"
																		property="pay_rec_no" />
																</s:if>
																<s:else>
																	<display:column sortable="false"
																		style="text-align:center;" title="Payment No"
																		property="pay_rec_no" />
																</s:else>
																<display:column sortable="false"
																	style="text-align:left;" title="Broker Name"
																	property="brokername" />
																<display:column sortable="false"
																	style="text-align:left;" title="Ceding Company"
																	property="cedingCo" />
																<display:column sortable="false"
																	style="text-align:left;" title="Currency"
																	property="currencyName" />
																<display:column sortable="false"
																	style="text-align:right;" title="Exchange Rate"
																	property="exrate" />
																<s:if test='"PT".equals(allocType)'>
																	<display:column sortable="false"
																		style="text-align:right;" title="Receipt Amount"
																		property="paymentamount" />
																</s:if>
																<s:else>
																	<display:column sortable="false"
																		style="text-align:right;" title="Payment Amount"
																		property="paymentamount" />
																</s:else>
															</display:table>
														</s:else>
													</div>
												</div>
											</div>
											<s:if test='"".equals(pay_rec_no) || pay_rec_no==null'>
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body" style="overflow: auto;">
													<display:table name="reversedetailsList" pagesize="20" requestURI="" class="footable" uid="row" id="record">
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found"	value="" />
													<display:setProperty name="paging.banner.placement" value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<s:set name="myrow" value="#attr.record"/>
													<display:column style="text-align:center;" title="Allocation No" property="serial_no" />
													<display:column style="text-align:center;" title="Contract No" property="contractno" />
													<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
													<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
													<display:column style="text-align:center;" title="Reversal Date" property="reversalDate" />
													<display:column style="text-align:left;" title="Currency" property="currency" />
													<display:column style="text-align:left;" title="Business Type" property="productname" />
													<display:column style="text-align:left;" title="Type" >	
														<s:if test='"P".equals(#myrow.type)'>
															<s:text name="PREMIUM"/>
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															<s:text name="CLAIM"/>
														</s:if>
													</display:column>
													<display:column style="text-align:right;" title="Sign" >
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
													<display:column style="text-align:right;" title="Reversed Amount" property="reversedAmount" />
													<display:column style="text-align:right;" title="Status" property="status" />
																		
												</display:table>
													</div>
												</div>
											</div>
											</s:if>
											<s:if test='!"".equals(pay_rec_no) && pay_rec_no!=null'>
											<div class="boxcontent">
											<div class="panel panel-primary">
											<!--<div class="panel-body" style="overflow: auto;">
													<display:table name="tresauryRecPaytailsList" pagesize="20" requestURI="" class="footable" uid="row" id="record">
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found"	value="" />
													<display:setProperty name="paging.banner.placement" value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<s:set name="myrow" value="#attr.record"/>
													<s:if test='"PT".equals(allocType)'>
													<display:column style="text-align:center;" title="ReceiptNo" property="serial_no" />
													</s:if>
													<s:else>
													<display:column style="text-align:center;" title="PaymentNo" property="serial_no" />
													</s:else>
													<display:column style="text-align:center;" title="Transaction Date" property="tr_date" />
													<display:column style="text-align:center;" title="Gross Amount" property="paymentamount" />
													<display:column style="text-align:center;" title="Bank Charges" property="bankCharges" />
													<display:column style="text-align:center;" title="With Holding Tax Recoverable" property="withHoldingTaxOC" />
													<display:column style="text-align:left;" title="Net Amount" property="netAmt" />
													<display:column style="text-align:left;" title="Difference Amount" property="diffAmount" />
													<display:column style="text-align:left;" title="Exchange Rate" property="exchangerate" />
													<display:column style="text-align:left;" title="Gross Amount(UGX)" property="totalexchaamount" />
												</display:table>
													</div>
											--><table width="100%" style="margin: 0 auto;" class="footable display" >
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
														<th width="12.5%"> <s:text name="Gross Amount" />(<s:property value="shortname"/>) </th>
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
												<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="back5()" />
												<input type="button" value="Print" id="print" class="btn btn-sm btn-primary"	onClick="printpage()"/>	
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:if>
					</div>
					<s:hidden name="allocType"/>
					<s:hidden name="type"/>	
					<s:hidden name="serial_no" />
				</s:form>
			</div>
		</div>
		<script type="text/javascript">
            var form = $('form[name="PaymentForm"]');
	function back5() {
		//document.PaymentForm.serial_no.value="";
		document.PaymentForm.action="${pageContext.request.contextPath}/initTreasury.action?flag=VIEW";
		document.PaymentForm.submit();	
	}
	function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}
</script>
	</body>
</html>
