<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
				var data = $('#gridTableMake').DataTable( {
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
	    $( "#accountDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#reversalDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>
	<script type="text/javascript">
	function pagination(val,pageval,lpage) {
		if(pageval>lpage) {
			pageval=lpage;
		}
		document.allocationForm.pagecount.value=pageval;
		document.allocationForm.expg.value="pagination";
		document.allocationForm.action = "contractAllocateTreasury.action";
		document.allocationForm.submit();
	}
	function callEnterPage(val,lpage) {
			if(event.keyCode==13) {
				try {
					if(!isNaN(val)) {
						val=Math.round(val);
						if(val>0)
							newcallpage('pageInput',val,lpage);
					}
				}
				catch(e){
				}
			}
		}
	</script>
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:if test='"PT".equals(allocType)'>
					<s:text name="heading.allocationPayment" />
				</s:if>
				<s:else>
					<s:text name="heading.allocationReceipt" />
				</s:else>
			</div>
	<div class="panel-body">
			<s:if test='"receiptAllocation".equalsIgnoreCase(partToShow)'>
				<div class="tablerow">
					<div style="padding:10px; background:#F8F8F8">
						<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >
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
												<div class="textfield">
													<div class="text">
														<s:text name="Currency" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="currencySearch" id="currencySearch" cssClass="inputBox" />
													</div>
												</div>
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('')" />							
														<input type="button" value="Show Full data" class="btn btn-sm btn-warning" onclick="showFullData();" /> 
																			
												</div>
											</div>
										</div>
									</div>
								</div>
						<s:hidden name="hidSelCurrencey" id="hidSelCurrencey" value="%{hidSelCurrencey==null?'':hidSelCurrencey}"/>
						<s:hidden name="hideprocessType" id="hideprocessType" />
						<s:hidden name="currencyid" />
						<s:hidden name="policyno" />
						<div class="table2">
							<div class="tablerow">
								<span style="color:red;"><s:actionerror/></span>
							</div>						
							<div class="tablerow">	
								<div class="boxcontent">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<s:if test='"PT".equals(allocType)'>
												<s:if test='"PA".equals(type)'>
													<s:text name="heading.PartiallyAllocatedPayments" />
												</s:if>
												<s:if test='"FA".equals(type)'>
													<s:text name="heading.fullyAllocatedPayments" />
												</s:if>								
											</s:if>
											<s:else>
												<s:if test='"PA".equals(type)'>
													<s:text name="heading.partiallyAllocatedReceipts" />
												</s:if>
												<s:if test='"FA".equals(type)'>
													<s:text name="heading.fullyAllocatedReceipts" />
												</s:if>	
											</s:else>										
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12">
								   					<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
														<thead>
															<tr>
																<th style="text-align: center; vertical-align: middle;"><s:text name="Click" /></th>
																	<s:if test='"PT".equals(allocType)'>
																		<th style="text-align: center; vertical-align: middle;"><s:text name="PAYMENT NO" /></th>
																	</s:if>
																	<s:else>
																		<th style="text-align: center; vertical-align: middle;"><s:text name="RECEIPT NO" /></th>
																	</s:else>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="BROKER" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="CEDING COMPANY" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="AMOUNT" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="REMARKS" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="CURRENCY" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="CURRENCY" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="TYPE" /></th>
																	<s:if test='"PA".equalsIgnoreCase(type)'>
																	<s:if test='#session.MenuRights.indexOf("PAD")!=-1'>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="ALLOCATION SUMMARY" /></th>
																	</s:if>
																	<s:if test='#session.MenuRights.indexOf("PAV")!=-1'>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="DETAILS" /></th>
																	</s:if>
																	</s:if>
																	<s:else>
																	<s:if test='#session.MenuRights.indexOf("FAD")!=-1'>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="ALLOCATION SUMMARY" /></th>	
																	</s:if>
																	<s:if test='#session.MenuRights.indexOf("FAV")!=-1'>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="DETAILS" /></th>
																	</s:if>
																	</s:else>									
																</tr>
															</thead>
														<tbody>
															<s:iterator value="detailsList" var="list" status="stat">
																<tr>
																	<td>													
																	<input type="radio" name="selects" id='selects<s:property value="#stat.count-1" />' onclick="viewPayment('${list.brokerid}','${list.cedingid}','${list.serial_no}',this.title,'selects<s:property value="#stat.count-1" />')" title="" value="0" />
																	</td>
																	<td><s:property value="serial_no"/></td>
																	<td><s:property value="broker"/></td>
																	<td><s:property value="cedingCo"/></td>
																	<td align="right"><s:property value="paymentamount"/></td>	
																	<td><s:property value="remarks"/></td>	
																	<td><s:property value="currency"/></td>	
																	<td>
																	<s:select name="selcurrency" list="#list.allocateCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="selvalue('selects%{#stat.count-1}',this.value);viewPayment('%{#list.brokerid}','%{#list.cedingid}','%{#list.serial_no}',this.value,'selects%{#stat.count-1}')" />
																	</td>
																	<td>
																	<s:select name="processType" list="#{'I':'Inward','R':'Retro'}"   cssClass="inputBoxS"  onchange="setProcess(this.value)"/>
																	</td>
																	<td>
																	<a href="#" class="" title="Allocation Summery" onclick="viewMode('${list.serial_no}','${list.broker}','${list.cedingCo}','allocateDetails','','View','','')"> 
																	<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Details" width="12" height="17">
																	</a>
																	</td>	
																	<td>
																	<a href="#" class="" title="Details" onclick="viewMode('${list.serial_no}','${list.broker}','${list.cedingCo}','allocateView','','FullView','','')">
																	<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="View" width="12" height="17">
																	</a>
																	</td>						
																	</tr>
																	</s:iterator>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											<div class="boxcontent" align="center">
												<s:hidden name="brokerid" />
												<s:hidden name="cedingid" />
												<s:hidden name="serial_no" />
												<s:hidden name="pay_rec_no" />
												<s:hidden name="cedingCo" />
												<s:hidden name="brokername" />
												<s:hidden name="flag" />
												<s:hidden name="searchBusinessType" value="" />
												<s:hidden name="searchType" value="" />
												<s:hidden name="searchContractNo" value="" />
												<s:hidden name="allocType" />
												<s:hidden name="type" />
												<s:hidden name="retroType" />
												<s:if test='"PA".equals(type)'>
													<s:if test='#session.MenuRights.indexOf("PAS")!=-1'>
														<input type="button"  value="Submit"   class="btn btn-sm btn-success" onclick="allocateSubmit()" />
													</s:if>
													</s:if>
											</div>
										</div>
									</div>
								</div>
							</div>
							<s:hidden name="alloccurrencyid" />				
						</s:form>
					</div>
				</div>
			</s:if>
			<s:if test='"CONTRACT_ALLOCATION".equals(CONTRACT_ALLOCATION)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >
					<s:hidden name="policyno" />
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.allocatedContractNo" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="label.currency" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyName" cssClass="inputBox" readonly="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="payment.amount" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyValue" cssClass="inputBox" readonly="true" />
												</div>
											</div>
											<br class="clear"/>
											<div style="overflow: auto;">
												<display:table name="RECEIPT_CONTRACT" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found"	value="" />
													<display:setProperty name="paging.banner.placement" value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<display:column style="text-align:center;" title="Click">
													<input type="checkbox" name="checkboxs" id="checkboxs" value="${record.contractno}" onclick="checkBox('${record.contractno}')"/>
													</display:column>	
													<display:column style="text-align:center;" title="CONTRACT NO" property="contractno" />
													<display:column style="text-align:center;" title="CEDING COMPANY" property="cedingCo" />
													<display:column style="text-align:center;" title="INCEPTION DATE" property="inceptiobdate" />
													<display:column style="text-align:center;" title="EXPIRY DATE" property="expirtdate" />
													<display:column style="text-align:center;" title="TYPE" property="productname" />
												</display:table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />
								<s:hidden name="cedingid" />
								<s:hidden name="brokerid" />								
								<s:hidden name="serial_no" />
								<s:hidden name="type" />
								<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="contractAllocate()" /> &nbsp;&nbsp;&nbsp;
	        					<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />
							</div>
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			<s:if test='"ALLOCATION_TRANSACTION".equals(ALLOCATION_TRANSACTION)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >
					<s:hidden name="policyno" />
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent" align="center">
											<s:text name="allocation.sucessMsg" />
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center"> 
								<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" onclick="backTran()" />
							</div>
						</div>
					</div>
					<s:hidden name="type" />
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			<s:if test='"transactionAllocation".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >
					<s:hidden name="policyno" />
					<s:hidden name="currencyid" id="currencyid" value="currencyid" />
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">	
							<div class="boxcontent">
								<div class="panel panel-primary">									
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
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
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
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record1" export="true">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:column style="text-align:center;" title="Settlement Date" property="paymentDate" />
												<display:column style="text-align:left;" title="Ceding Company" property="cedingCompanyName" />
												<display:column style="text-align:left;" title="Bank" property="bank" />
												<display:column style="text-align:left;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated" property="allocated" />
												<display:column style="text-align:right;" title="Utilized" property="utilized" />
												<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
												<display:column style="text-align:left;" title="Status" property="status" />
												<display:setProperty name="export.excel" value="true"/>
												<display:setProperty name="export.excel.filename" value="Contracts1.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />								
											</display:table>
										</div>
									</div>
								</div>
							</div>
							<%-- <div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.allocTrans" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.businessType" />
												</div>
												<div class="tbox">
													<s:select list="#{'1':'Facltative','2':'Proportional Treaty','3':'XOL'}" headerKey="" headerValue="All" name="searchBusinessType" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.type" />
												</div>
												<div class="tbox">
													<s:select list="#{'P':'Premium','C':'Claim'}" headerKey="" headerValue="All" name="searchType" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="RiskDetails.ContNo" />
												</div>
												<div class="tbox">
													<s:textfield name="searchContractNo" cssClass="inputBox"/>
												</div>
											</div>
											<br class="clear"/>
											<div align="center">
												<s:submit type="button" value="Search" cssClass="btn btn-sm btn-warning" onclick="callEnterPage('1','%{lpage}')" />
											</div>											
										</div>
									</div>
								</div>
							</div>--%>
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.currency" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyName" readonly="true" cssClass="inputBox"/>
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.Amount" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyValue" readonly="true" cssClass="inputBox"/>
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.Date" />
												</div>
												<div class="tbox">
												<s:textfield name="accountDate" id="accountDate"  cssClass="inputBox"  />
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
										<div class="boxcontent" style="overflow: auto;">
										  <%--<display:table name="transactionContractList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column style="text-align:center;" title="Click">
												<s:if test='!"".equals(#myrow.netdue)'>
													<s:checkbox name="chkbox[%{#myrow.count}]" id="chkbox[%{#attr.record_rowNum-1}]" fieldValue="true" onchange="setAmount(this.id,'receivenetdue[%{#attr.record_rowNum-1}]','%{#myrow.count}','%{#myrow.netdue}','previousValue[%{#myrow.count}]','%{#myrow.checkPC}');"/>
												</s:if>
												<s:if test='!"".equals(#myrow.payamount)'>
													<s:checkbox name="chkbox[%{#myrow.count}]" id="chkbox[%{#attr.record_rowNum-1}]" fieldValue="true" onchange="setAmount(this.id,'receivepayamount[%{#attr.record_rowNum-1}]','%{#myrow.count}','%{#myrow.payamount}','previousValue[%{#myrow.count}]','%{#myrow.checkPC}');"/>
												</s:if>
												</display:column>
												<display:column style="text-align:center;" title="CONTRACT NO" >
													<s:hidden name="contractno[%{#attr.record_rowNum-1}]" id="contractno[%{#attr.record_rowNum-1}]" value="%{#myrow.contractno}" /><s:property value="#myrow.contractno"/>		
												</display:column>
												<display:column style="text-align:center;" title="BUSINESS TYPE"  >
													<s:hidden name="productname[%{#attr.record_rowNum-1}]" id="productname[%{#attr.record_rowNum-1}]" value="%{#myrow.productname}" /><s:property value="#myrow.productname"/>
												</display:column>
												<display:column style="text-align:center;" title="TRANSACTION NO" >
													<s:hidden name="transactionNos[%{#myrow.count}]" id="transactionno[%{#attr.record_rowNum-1}]" value="%{#myrow.transactionno}" /><s:property value="#myrow.transactionno"/>	
												</display:column>
												<display:column style="text-align:center;" title="CEDING COMPANY NAME" >
													<s:hidden name="cedingCompanyName[%{#attr.record_rowNum-1}]" id="cedingCompanyName[%{#attr.record_rowNum-1}]" value="%{#myrow.cedingCompanyName}" /><s:property value="#myrow.cedingCompanyName"/>	
												</display:column>
												
												<display:column style="text-align:center;" title="DATE">
													<s:hidden name="inceptiobdate[%{#attr.record_rowNum-1}]" id="inceptiobdate[%{#attr.record_rowNum-1}]" value="%{#myrow.inceptiobdate}" /><s:property value="#myrow.inceptiobdate"/>
												</display:column>
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.checkPC)'>
														<s:text name="PREMIUM"/>
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														<s:text name="CLAIM"/>
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
												<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
												</s:if>	
												</display:column>
												<display:column style="text-align:center;" title="AMOUNT">
													<s:if test='"".equals(#myrow.netdue)'>
														<s:hidden name="netdue[%{#attr.record_rowNum-1}]" value="0" id="netdue[%{#attr.record_rowNum-1}]" />			
													</s:if>
													<s:else>
														<s:textfield name="netdue[%{#attr.record_rowNum-1}]" id="netdue[%{#attr.record_rowNum}]" value="%{#myrow.netdue}" readonly="true" cssClass="inputBox" cssStyle="text-align:right;"/>			
														<s:hidden name="netdue[%{#attr.record_rowNum-1}]" value="%{#myrow.netdue}" id="netdue[%{#attr.record_rowNum-1}]" />
													</s:else>
													<s:if test='"".equals(#myrow.payamount)'>
														<s:hidden name="payment[%{#attr.record_rowNum-1}]" value="0" id="payment[%{#attr.record_rowNum-1}]" />
													</s:if>
													<s:else>
														<s:textfield name="payment[%{#attr.record_rowNum-1}]" id="payment[%{#attr.record_rowNum-1}]" value="%{#myrow.payamount}" readonly="true" cssClass="inputBox" cssStyle="text-align:right;"/>
														<s:hidden name="payment[%{#attr.record_rowNum-1}]" value="%{#myrow.payamount}" id="payment[%{#attr.record_rowNum-1}]" />
													</s:else>
												</display:column>
												<display:column style="text-align:center;" title="ACCEPTED AMOUNT" >
													<s:if test='!"".equals(#myrow.netdue)'>
														<%-- 
														<s:textfield name="receivenetdue[%{#attr.record_rowNum-1}]" id="receivenetdue[%{#attr.record_rowNum-1}]" value="%{#myrow.accPremium}" onchange="updateUtilizedAmount('chkbox[%{#myrow.count}]','previousValue[%{#myrow.count}]',this,'%{#myrow.checkPC}')" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;"/>
														--%
														<s:textfield name="receivePayAmounts[%{#attr.record_rowNum-1}]" id="receivenetdue[%{#attr.record_rowNum-1}]" onchange="updateUtilizedAmount('chkbox[%{#myrow.count}]','previousValue[%{#myrow.count}]',this,'%{#myrow.checkPC}')" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30"  disabled="%{(receivePayAmounts[%{#myrow.count}]!='' && receivePayAmounts[%{#myrow.count}]!=null)?true:false}"/>
													</s:if>
													<s:if test='!"".equals(#myrow.payamount)'>
														<%--  <s:textfield name="receivepayamount[%{#attr.record_rowNum-1}]" value="%{#myrow.accClaim}" id="receivepayamount[%{#attr.record_rowNum-1}]" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;"/>--%
														<s:textfield name="receivePayAmounts[%{#myrow.count}]" id="receivepayamount[%{#attr.record_rowNum-1}]" onchange="updateUtilizedAmount('chkbox[%{#myrow.count}]','previousValue[%{#myrow.count}]',this,'%{#myrow.checkPC}')" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30"  disabled="%{(receivePayAmounts[%{#myrow.count}]!='' && receivePayAmounts[%{#myrow.count}]!=null)?true:false}"/>
													</s:if>
													<s:hidden name="previousValue[%{#myrow.count}]" id="previousValue[%{#myrow.count}]"/>
												</display:column>							
											</display:table>--%>
											
											 <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
														<thead>
															<tr>
																<th style="text-align: center; vertical-align: middle;"><s:text name="Click" /></th>
																	
																	<th style="text-align: center; vertical-align: middle;"><s:text name="CONTRACT NO" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="BUSINESS TYPE" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="TRANSACTION NO" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="CEDING COMPANY NAME" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="DATE" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="TYPE" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="SIGN" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="AMOUNT" /></th>
																	<th style="text-align: center; vertical-align: middle;"><s:text name="ACCEPTED AMOUNT" /></th>
																								
																</tr>
															</thead>
														<tbody>
															<s:iterator value="transactionContractList" var="myrow" status="stat">
																<tr>
																	<td><s:if test='!"".equals(#myrow.netdue)'>
																	 <s:checkbox fieldValue="%{#myrow.transactionno}"  id="chkbox[%{#myrow.transactionno}]"  name="checkItem" onchange="setAmount(this.id,'receivePayAmounts[%{#myrow.transactionno}]','%{#stat.count-1}','%{#myrow.netdue}','previousValue[%{#myrow.transactionno}]','%{#myrow.checkPC}');"/>
																		<%--	<s:checkbox name="chkbox[%{#stat.count-1}]" id="chkbox[%{#stat.count-1}]" value="" fieldValue="true" onchange="setAmount(this.id,'receivenetdue[%{#stat.count-1}]','%{#stat.count-1}','%{#myrow.netdue}','previousValue[%{#stat.count-1}]','%{#myrow.checkPC}');" />--%>
																		</s:if>
																		<s:if test='!"".equals(#myrow.payamount)'>
																			<s:checkbox fieldValue="%{#myrow.transactionno}"  id="chkbox[%{#myrow.transactionno}]"  name="checkItem" onchange="setAmount(this.id,'receivePayAmounts[%{#myrow.transactionno}]','%{#stat.count-1}','%{#myrow.payamount}','previousValue[%{#myrow.transactionno}]','%{#myrow.checkPC}');"/>
																		</s:if>												
																	</td>
																	<td><s:hidden name="contractno[%{#stat.count-1}]" id="contractno[%{#stat.count-1}]" value="%{#myrow.contractno}" /><s:property value="#myrow.contractno"/></td>
																	<td><s:hidden name="productname[%{#stat.count-1}]" id="productname[%{#stat.count-1}]" value="%{#myrow.productname}" /><s:property value="#myrow.productname"/></td>	
																	<td><s:hidden name="transactionNos[%{#stat.count-1}]" id="transactionno[%{#stat.count-1}]" value="%{#myrow.transactionno}" /><s:property value="#myrow.transactionno"/></td>
																	<td><s:hidden name="cedingCompanyName[%{#attr.record_rowNum-1}]" id="cedingCompanyName[%{#attr.record_rowNum-1}]" value="%{#myrow.cedingCompanyName}" /><s:property value="#myrow.cedingCompanyName"/></td>	
																	<td><s:hidden name="inceptiobdate[%{#stat.count-1}]" id="inceptiobdate[%{#stat.count-1}]" value="%{#myrow.inceptiobdate}" /><s:property value="#myrow.inceptiobdate"/></td>	
																	<td><s:if test='"P".equals(#myrow.checkPC)'>
																			<s:text name="PREMIUM"/>
																		</s:if>
																		<s:if test='"C".equals(#myrow.checkPC)'>
																			<s:text name="CLAIM"/>
																		</s:if>
																	</td>
																	<td><s:if test='"RT".equals(allocType)'>
																			<s:if test='"P".equals(#myrow.checkPC)'>
																				+
																			</s:if>
																			<s:if test='"C".equals(#myrow.checkPC)'>
																				-
																			</s:if>
																		</s:if>
																		<s:if test='"PT".equals(allocType)'>
																			<s:if test='"P".equals(#myrow.checkPC)'>
																				-
																			</s:if>
																			<s:if test='"C".equals(#myrow.checkPC)'>
																				+
																			</s:if>
																		</s:if>	</td>
																		<td><s:if test='"".equals(#myrow.netdue)'>
																			<s:hidden name="netdue[%{#stat.count-1}]" value="0" id="netdue[%{#stat.count-1}]" />			
																		</s:if>
																		<s:else>
																			<s:textfield name="netdue[%{#stat.count-1}]" id="netdue[%{#stat.count-1}]" value="%{#myrow.netdue}" readonly="true" cssClass="inputBox" cssStyle="text-align:right;"/>			
																			<s:hidden name="netdue[%{#stat.count-1}]" value="%{#myrow.netdue}" id="netdue[%{#stat.count-1}]" />
																		</s:else>
																		<s:if test='"".equals(#myrow.payamount)'>
																			<s:hidden name="payment[%{#stat.count-1}]" value="0" id="payment[%{#stat.count-1}]" />
																		</s:if>
																		<s:else>
																			<s:textfield name="payment[%{#stat.count-1}]" id="payment[%{#stat.count-1}]" value="%{#myrow.payamount}" readonly="true" cssClass="inputBox" cssStyle="text-align:right;"/>
																			<s:hidden name="payment[%{#stat.count-1}]" value="%{#myrow.payamount}" id="payment[%{#stat.count-1}]" />
																		</s:else></td>
																	<td><s:if test='!"".equals(#myrow.netdue)'>
																
																	<s:textfield name="receivePayAmounts" id="receivePayAmounts[%{#myrow.transactionno}]" onchange="updateUtilizedAmount('chkbox[%{#myrow.transactionno}]','previousValue[%{#myrow.transactionno}]',this,'%{#myrow.checkPC}');" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30"  disabled="true" />
																</s:if>
																<s:if test='!"".equals(#myrow.payamount)'>
																	<s:textfield name="receivePayAmounts" id="receivePayAmounts[%{#myrow.transactionno}]" onchange="updateUtilizedAmount('chkbox[%{#myrow.transactionno}]','previousValue[%{#myrow.transactionno}]',this,'%{#myrow.checkPC}');" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30"  disabled="true"/>
																</s:if>
																<s:hidden name="previousValue[%{#stat.count-1}]" id="previousValue[%{#myrow.transactionno}]"/>
																</td>
																	</tr>
																	</s:iterator>
																</tbody>
															</table>
											<br class="clear"/>
										<%--	<s:if test="transactionContractList!=null && !transactionContractList.isEmpty()">
 												<div class="tablerow">
													<table width="100%">								
												    	<tr align="center" >
													   		<td height="12" align="center" colspan="9" class="td-no-right-border">
																<table border="0" width="100%">
																	<tr align="center">
																		<td width="65%" align="right">											
																			<table border="0">
																				<tr>
																					<td>
																						<s:if test='!"1".equalsIgnoreCase(cpage)'>
																							<a href="#" onclick="pagination(this,1,<s:property value='lpage'/>)" ><font  color="blue"><img border="0" src="${pageContext.request.contextPath}/images/pagefirst.png" ></font></a>																
																							<a href ="#" onclick="pagination(this,<s:property value="%{@java.lang.Integer@parseInt(cpage)-1}" />,<s:property value='lpage'/>)"><font  color="blue"><img border="0" src="${pageContext.request.contextPath}/images/pageleft.png" ></font></a>
																						</s:if>
																						<s:else>
																							<a href ="#" onclick="javascript:return false;" ><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)" src="${pageContext.request.contextPath}/images/pagefirst.png" ></font></a>																
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pageleft.png" ></font></a>
																						</s:else>
																					</td>
																					<td>
																						&nbsp;
																						Page 
																						<input id="pagetext" type="text" align="top" value="<s:property value='cpage'/>" style="height:20px;width:23px" onkeydown="callEnterPage(this.value,<s:property value='lpage'/>);" /> of <s:property value='lpage'/>
																						&nbsp;
																					</td>
																					<td>
																						<s:if test="%{@java.lang.Integer@parseInt(cpage) < @java.lang.Integer@parseInt(totalpages)}">
																							<a href="#" onclick="pagination(this,<s:property value="%{@java.lang.Integer@parseInt(cpage)+1}" />,<s:property value="lpage"/> )"><font  color="blue"><img border="0"  src="${pageContext.request.contextPath}/images/pageright.png" ></font></a>
																							<a href ="#" onclick="pagination(this,<s:property value='lpage'/>,<s:property value='lpage'/>)"><font  color="blue"><img border="0"  src="${pageContext.request.contextPath}/images/pagelast.png" ></font></a>
																						</s:if>
																						<s:else>
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pageright.png" ></font></a>
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pagelast.png" ></font></a>
																						</s:else>
																	  	 			</td>
																				</tr>
																			</table>
																		</td>
																		<td width="35%" align="right">
																	 		View&nbsp;<s:property value="startrownum" />&nbsp;-&nbsp;<s:if test="%{@java.lang.Integer@parseInt(endrownum) < @java.lang.Integer@parseInt(totalreccount)}"><s:property value="endrownum" /></s:if><s:else><s:property value="totalreccount"/></s:else>&nbsp;of&nbsp;<s:property value="totalreccount"/>
																	 	</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
												<s:hidden name="hideprocessType" id="hideprocessType" />
											</s:if>--%>
											<br class="clear"/>
										</div>
										<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.unUtilizedAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');TransAllocate()" /> &nbsp;&nbsp;&nbsp;
	        					<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />
							</div>
						</div>
					</div>
					<s:hidden name="selectTransactionNo" id="selectTransactionNo" />
					<s:hidden name="selreceivePayAmount" id="selreceivePayAmount" />
					<s:hidden name="checkCount" id="checkCount" />
					<s:hidden name="trans" id="trans" value="0" />
					<s:hidden name="selects" id="selects" value="0" />
					<s:hidden name="brokerid" />
					<s:hidden name="cedingid" />
					<s:hidden name="serial_no" />
					<s:hidden name="selects" />
					<s:hidden name="hidSelCurrencey" />
					<s:hidden name="contractId" id="contractId" value="%{contractId}" />
					<s:hidden name="receiptNo" id="receiptNo" value="%{RECEIPT_NO}" />
					<s:hidden name="currency" />
					<s:hidden name="allocType"/>
					<s:hidden name="type" />
					<s:hidden name="checks"/>
					<s:hidden name="pagecount" />
					<s:hidden name="previouspageno"/>
					<s:hidden name="currentpageno"/>
					<s:hidden name="totalreccount" id="totalreccount"/>
					<s:hidden name="pageLength"/>
					<s:hidden name="oneTime"/>
					<s:hidden name="currentcheckeditems"/>
					<s:hidden name="cpage"/>
					<s:hidden name="expg" value=""/>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			<s:if test='"retrotransactionAllocation".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >
					<s:hidden name="policyno" />
					<s:hidden name="currencyid" id="currencyid" value="currencyid" />
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">	
							<div class="boxcontent">
								<div class="panel panel-primary">									
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
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
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
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record1" export="true">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												
												<display:column style="text-align:center;" title="Settlement Date" property="paymentDate" />
												<display:column style="text-align:left;" title="Ceding Company" property="cedingCompanyName" />
												<display:column style="text-align:left;" title="Bank" property="bank" />
												
												<display:column style="text-align:left;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated" property="allocated" />
												<display:column style="text-align:right;" title="Utilized" property="utilized" />
												<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
												<display:column style="text-align:left;" title="Status" property="status" />
												<display:setProperty name="export.excel" value="true"/>
												
												<display:setProperty name="export.excel.filename" value="Contracts1.xls"/>
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
										<s:text name="heading.allocTrans" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.businessType" />
												</div>
												<div class="tbox">
													<s:select list="#{'1':'Facltative','2':'Proportional Treaty','3':'XOL'}" headerKey="" headerValue="All" name="searchBusinessType" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.type" />
												</div>
												<div class="tbox">
													<s:select list="#{'P':'Premium','C':'Claim'}" headerKey="" headerValue="All" name="searchType" cssClass="inputBoxS" />
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="RiskDetails.ContNo" />
												</div>
												<div class="tbox">
													<s:textfield name="searchContractNo" cssClass="inputBox"/>
												</div>
											</div>
											<br class="clear"/>
											<div align="center">
												<s:submit type="button" value="Search" cssClass="btn btn-sm btn-warning" onclick="callEnterPage('1','%{lpage}')" />
											</div>											
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.currency" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyName" readonly="true" cssClass="inputBox"/>
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.Amount" />
												</div>
												<div class="tbox">
													<s:textfield name="currencyValue" readonly="true" cssClass="inputBox"/>
												</div>
											</div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="allocation.Date" />
												</div>
												<div class="tbox">
												<s:textfield name="accountDate" id="accountDate"  cssClass="inputBox"  />
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
										<div class="boxcontent" style="overflow: auto;">
										 <display:table name="transactionContractList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column style="text-align:center;" title="Click">
												<s:if test='!"".equals(#myrow.netdue)'>
													<s:checkbox name="chkbox[%{#myrow.count}]" id="chkbox[%{#attr.record_rowNum-1}]" fieldValue="true" onchange="setAmount1(this.id,'receivenetdue[%{#attr.record_rowNum-1}]','%{#myrow.count}','%{#myrow.netdue}','previousValue[%{#myrow.count}]','%{#myrow.checkPC}');"/>
												</s:if>
												</display:column>
												<display:column style="text-align:center;" title="CONTRACT NO" >
													<s:hidden name="contractno[%{#attr.record_rowNum-1}]" id="contractno[%{#attr.record_rowNum-1}]" value="%{#myrow.contractno}" /><s:property value="#myrow.contractno"/>		
												</display:column>
												<display:column style="text-align:center;" title="TRANSACTION NO" >
													<s:hidden name="transactionNos[%{#myrow.count}]" id="transactionno[%{#attr.record_rowNum-1}]" value="%{#myrow.transactionno}" /><s:property value="#myrow.transactionno"/>	
												</display:column>
												<display:column style="text-align:center;" title="RETROCESSIONAIRE" >
													<s:hidden name="cedingCompanyName[%{#attr.record_rowNum-1}]" id="cedingCompanyName[%{#attr.record_rowNum-1}]" value="%{#myrow.cedingCompanyName}" /><s:property value="#myrow.cedingCompanyName"/>	
												</display:column>
												
												<display:column style="text-align:center;" title="DATE">
													<s:hidden name="inceptiobdate[%{#attr.record_rowNum-1}]" id="inceptiobdate[%{#attr.record_rowNum-1}]" value="%{#myrow.inceptiobdate}" /><s:property value="#myrow.inceptiobdate"/>
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
												<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
														<s:hidden name="sign" value="+"/>
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
														<s:hidden name="sign" value="-"/>
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
														<s:hidden name="sign" value="+"/>
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
													<s:hidden name="sign" value="-"/>
														-
													</s:if>
												</s:if>	
												</display:column>
												<display:column style="text-align:center;" title="AMOUNT">
													<s:if test='"".equals(#myrow.netdue)'>
														<s:hidden name="netdue[%{#attr.record_rowNum-1}]" value="0" id="netdue[%{#attr.record_rowNum-1}]" />			
													</s:if>
													<s:else>
														<s:textfield name="netdue[%{#attr.record_rowNum-1}]" id="netdue[%{#attr.record_rowNum}]" value="%{#myrow.netdue}" readonly="true" cssClass="inputBox" cssStyle="text-align:right;"/>			
														<s:hidden name="netdue[%{#attr.record_rowNum-1}]" value="%{#myrow.netdue}" id="netdue[%{#attr.record_rowNum-1}]" />
													</s:else>
												</display:column>
												<display:column style="text-align:center;" title="ACCEPTED AMOUNT" >
													<s:if test='!"".equals(#myrow.netdue)'>
														<s:textfield name="receivePayAmounts[%{#attr.record_rowNum-1}]" id="receivenetdue[%{#attr.record_rowNum-1}]" onchange="updateUtilizedAmount('chkbox[%{#myrow.count}]','previousValue[%{#myrow.count}]',this,'%{#myrow.checkPC}')" onkeyup="allow2DigitDecValues(this)" cssClass="inputBox" cssStyle="text-align:right;" maxlength="30" disabled="%{(receivePayAmounts[%{#myrow.count}]!='' && receivePayAmounts[%{#myrow.count}]!=null)?true:false}"/>
													</s:if>
													<s:hidden name="previousValue[%{#myrow.count}]" id="previousValue[%{#myrow.count}]"/>
												</display:column>							
											</display:table>
											<br class="clear"/>
											<s:if test="transactionContractList!=null && !transactionContractList.isEmpty()">
 												<div class="tablerow">
													<table width="100%">								
												    	<tr align="center" >
													   		<td height="12" align="center" colspan="9" class="td-no-right-border">
																<table border="0" width="100%">
																	<tr align="center">
																		<td width="65%" align="right">											
																			<table border="0">
																				<tr>
																					<td>
																						<s:if test='!"1".equalsIgnoreCase(cpage)'>
																							<a href="#" onclick="pagination(this,1,<s:property value='lpage'/>)" ><font  color="blue"><img border="0" src="${pageContext.request.contextPath}/images/pagefirst.png" ></font></a>																
																							<a href ="#" onclick="pagination(this,<s:property value="%{@java.lang.Integer@parseInt(cpage)-1}" />,<s:property value='lpage'/>)"><font  color="blue"><img border="0" src="${pageContext.request.contextPath}/images/pageleft.png" ></font></a>
																						</s:if>
																						<s:else>
																							<a href ="#" onclick="javascript:return false;" ><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)" src="${pageContext.request.contextPath}/images/pagefirst.png" ></font></a>																
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pageleft.png" ></font></a>
																						</s:else>
																					</td>
																					<td>
																						&nbsp;
																						Page 
																						<input id="pagetext" type="text" align="top" value="<s:property value='cpage'/>" style="height:20px;width:23px" onkeydown="callEnterPage(this.value,<s:property value='lpage'/>);" /> of <s:property value='lpage'/>
																						&nbsp;
																					</td>
																					<td>
																						<s:if test="%{@java.lang.Integer@parseInt(cpage) < @java.lang.Integer@parseInt(totalpages)}">
																							<a href="#" onclick="pagination(this,<s:property value="%{@java.lang.Integer@parseInt(cpage)+1}" />,<s:property value="lpage"/> )"><font  color="blue"><img border="0"  src="${pageContext.request.contextPath}/images/pageright.png" ></font></a>
																							<a href ="#" onclick="pagination(this,<s:property value='lpage'/>,<s:property value='lpage'/>)"><font  color="blue"><img border="0"  src="${pageContext.request.contextPath}/images/pagelast.png" ></font></a>
																						</s:if>
																						<s:else>
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pageright.png" ></font></a>
																							<a href ="#" onclick="javascript:return false;"><font  color="blue"><img border="0" style="opacity:0.7;filter:alpha(opacity=40)"  src="${pageContext.request.contextPath}/images/pagelast.png" ></font></a>
																						</s:else>
																	  	 			</td>
																				</tr>
																			</table>
																		</td>
																		<td width="35%" align="right">
																	 		View&nbsp;<s:property value="startrownum" />&nbsp;-&nbsp;<s:if test="%{@java.lang.Integer@parseInt(endrownum) < @java.lang.Integer@parseInt(totalreccount)}"><s:property value="endrownum" /></s:if><s:else><s:property value="totalreccount"/></s:else>&nbsp;of&nbsp;<s:property value="totalreccount"/>
																	 	</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
												<s:hidden name="hideprocessType" id="hideprocessType" />
											</s:if>
											<br class="clear"/>
										</div>
										<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.unUtilizedAmount" />
												</div>
												<div class="tbox">
													<s:textfield name="unUtilizedAmt" id="unUtilizedAmt" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="TransRetroAllocate()" /> &nbsp;&nbsp;&nbsp;
	        					<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />
							</div>
						</div>
					</div>
					<s:hidden name="checkCount" id="checkCount" />
					<s:hidden name="trans" id="trans" value="0" />
					<s:hidden name="selects" id="selects" value="0" />
					<s:hidden name="brokerid" />
					<s:hidden name="cedingid" />
					<s:hidden name="serial_no" />
					<s:hidden name="selects" />
					<s:hidden name="hidSelCurrencey" />
					<s:hidden name="contractId" id="contractId" value="%{contractId}" />
					<s:hidden name="receiptNo" id="receiptNo" value="%{RECEIPT_NO}" />
					<s:hidden name="currency" />
					<s:hidden name="allocType"/>
					<s:hidden name="type" />
					
					<s:hidden name="checks"/>
					<s:hidden name="pagecount" />
					<s:hidden name="previouspageno"/>
					<s:hidden name="currentpageno"/>
					<s:hidden name="totalreccount" id="totalreccount"/>
					<s:hidden name="pageLength"/>
					<s:hidden name="oneTime"/>
					<s:hidden name="currentcheckeditems"/>
					<s:hidden name="cpage"/>
					<s:hidden name="expg" value=""/>
					
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			
			<s:if test='"allocateView".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">									
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
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
												</div>
											</div>
											<s:if test='cedingCo != "" && cedingCo != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.CedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											</s:if>											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record1" export="true">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												
												<display:column style="text-align:left;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated" property="allocated" />
												<display:column style="text-align:right;" title="Utilized" property="utilized" />
												<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
												<display:column style="text-align:left;" title="Status" property="status" />
												<display:setProperty name="export.excel" value="true"/>
												
												<display:setProperty name="export.excel.filename" value="Contracts1.xls"/>
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
												<s:if test='"RE".equals(#myrow.type)'>
												<display:column style="text-align:left;" title="TYPE">
														<s:text name="RE" />
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													
														<s:property value="#myrow.payamount"/>
													
												</display:column>
												<display:column style="text-align:left;" title="Status" property="status" /><%--adjustMentType --%>
												</s:if>
												<s:else>
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
													<s:if test='"RT".equals(allocType)'>
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
													</s:if>
													<s:if test='"PT".equals(allocType)'>
														<s:if test='"P".equals(#myrow.type)'>
															-
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															+
														</s:if>
														<s:if test='"PT".equals(#myrow.type)'>
															-
														</s:if>
														<s:if test='"RT".equals(#myrow.type)'>
															+
														</s:if>
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
												</s:else>
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
							<s:if test='!"View".equals(flag)'>
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
												<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
												<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
												<display:column style="text-align:center;" title="Contract No" property="contractno" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Exchange Rate" property="exchangerate" />
												<display:column style="text-align:center;" title="Business Type" property="productname" />
												<s:if test='"RE".equals(#myrow.type)'>
												<display:column style="text-align:left;" title="TYPE">
														<s:text name="RE" />
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													
														<s:property value="#myrow.payamount"/>
													
												</display:column>
												<display:column style="text-align:left;" title="Status" property="status" /><%--adjustMentType --%>
												</s:if>
												<s:else>
												
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>								
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
														<s:if test='"P".equals(#myrow.type)'>
															+
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															-
														</s:if>
													</s:if>
													<s:if test='"PT".equals(allocType)'>
														<s:if test='"P".equals(#myrow.type)'>
															-
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															+
														</s:if>
													</s:if>
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													<s:if test='"P".equals(#myrow.type)'>
														<s:property value="#myrow.payamount" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:property value="#myrow.payamount" />
													</s:if>
												</display:column>
												<display:column style="text-align:left;" title="Status" property="status" /><%--adjustMentType --%>
												</s:else>
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
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />																
								<s:hidden name="serial_no" />
								<s:hidden name="brokerid" />
								<s:hidden name="cedingid" />
								<s:hidden name="pay_rec_no" />
								<s:hidden name="allocType" />
								<s:hidden name="type" />
								<s:hidden name="flag" />
								<s:hidden name="status" />
								<s:if test='"FullView".equals(flag)'>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />
								</s:if>
								<s:else>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backDetailsPage()" />
								</s:else>								
							</div>
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			<s:if test='"RetroallocateView".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">									
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
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
												</div>
											</div>
											<s:if test='cedingCo != "" && cedingCo != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.CedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											</s:if>											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record1" export="true">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												
												<display:column style="text-align:left;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated" property="allocated" />
												<display:column style="text-align:right;" title="Utilized" property="utilized" />
												<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
												<display:column style="text-align:left;" title="Status" property="status" />
												<display:setProperty name="export.excel" value="true"/>
												
												<display:setProperty name="export.excel.filename" value="Contracts1.xls"/>
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
													
														<s:text name="RE" />
													
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
														<s:property value="#myrow.payamount"/>
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
							<s:if test='!"View".equals(flag)'>
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
												<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
												<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
												<display:column style="text-align:center;" title="Contract No" property="contractno" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Exchange Rate" property="exchangerate" />
												<display:column style="text-align:center;" title="Business Type" property="productname" />
												<display:column style="text-align:left;" title="TYPE">
														<s:text name="RE" />
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													
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
							
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />																
								<s:hidden name="serial_no" />
								<s:hidden name="brokerid" />
								<s:hidden name="cedingid" />
								<s:hidden name="pay_rec_no" />
								<s:hidden name="allocType" />
								<s:hidden name="type" />
								<s:hidden name="flag" />
								<s:hidden name="status" />
								<s:if test='"FullView".equals(flag)'>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />
								</s:if>
								<s:else>
									<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backDetailsPage()" />
								</s:else>								
							</div>
							
							
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			<s:if test='"alloDet".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">									
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
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
												</div>
											</div>
											<s:if test='cedingCo != "" && cedingCo != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.CedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											</s:if>
											<s:if test='allTillDate != "" && allTillDate != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="label.amount" />
												</div>
												<div class="tbox">
													<s:property value="allTillDate"/>
												</div>
											</div>
											</s:if>	
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
											
												<display:column style="text-align:left;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated" property="allocated" />
												<display:column style="text-align:right;" title="Utilized" property="utilized" />
												<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
												<display:column style="text-align:left;" title="Status" property="status" />
											
												<display:setProperty name="export.excel" value="true"/>
												<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
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
										<s:text name="heading.alloccontracts" />
									</div>									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocateDetailsList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column style="text-align:center;" title="Allocation No." property="serial_no" />
												<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
												<display:column style="text-align:center;" title="Currency" property="currency" />
												<display:column style="text-align:right;" title="Allocated Amount" property="allTillDate" />
												<display:column style="text-align:center;"  title="Reverse" >
												<s:if test='"".equals(#myrow.adjustMentType)'>
													<a href="#" class="" title="Reverse" onclick="viewMode('${pay_rec_no}','${record.broker}','${record.cedingCo}','allocateView','${record.serial_no}','Rev','${record.currencyValue}','${record.retroType}')">
														<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Reverse" width="12" height="17">
													</a>
													</s:if>
												</display:column>
												<display:column style="text-align:center;"  title="View" >
													<a href="#" class="" title="View" onclick="viewMode('${pay_rec_no}','${record.broker}','${record.cedingCo}','allocateView','${record.serial_no}','View','${record.currencyValue}','${record.retroType}')">
														<img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="View" width="12" height="17">
													</a>
												</display:column>
												<display:setProperty name="export.excel" value="true"/>
												<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />
											</display:table>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />																
								<s:hidden name="pay_rec_no" />
								<s:hidden name="brokerid" />
								<s:hidden name="cedingid" />
								<s:hidden name="serial_no" />
								<s:hidden name="cedingCo" />
								<s:hidden name="brokername" />
								<s:hidden name="flag" />
								<s:hidden name="allocType" />
								<s:hidden name="type" />
								<s:hidden name="retroType" />								
								<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backTran()" />																
							</div>
							
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>			
			</s:if>
			<s:if test='"allocateRev".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.reverseAllocation" />
									</div>									
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:if test='"PT".equals(type)'>
														<s:text name="Premium.PaymentNo" />
													</s:if>
													<s:else>
														<s:text name="Premium.ReceiptNo" />
													</s:else>
												</div>
												<div class="tbox">
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
												</div>
											</div>
											<s:if test='cedingCo != "" && cedingCo != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.CedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											</s:if>											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body" style="overflow: auto;">
										<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found"	value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column style="text-align:left;" title="Currency" property="currency" />
											<display:column style="text-align:right;" title="Allocated" property="allocated" />
											<display:column style="text-align:right;" title="Utilized" property="utilized" />
											<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
											<display:column style="text-align:left;" title="Status" property="status" />
											
											<display:setProperty name="export.excel" value="true"/>
											<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
											<display:setProperty name="export.csv" value="false" />
											<display:setProperty name="export.xml" value="false" />
											<display:setProperty name="export.pdf" value="false" />
										</display:table>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body">
										<div class="boxcontent" style="overflow: auto;">
											<display:table name="allocateViewList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found"	value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<s:set name="myrow" value="#attr.record"/>
												<display:column style="text-align:center;" title="Allocation No." property="serial_no" />
												<display:column style="text-align:center;" title="Allocation Date" property="allocateddate" />
												<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
												<display:column style="text-align:center;" title="Contract No" property="contractno" />
												<display:column style="text-align:center;" title="Currency" property="currencyName" />
												<display:column style="text-align:center;" title="Exchange Rate" property="exchangerate" />
												<display:column style="text-align:center;" title="Business Type" property="productname" />
												<s:if test='"RE".equals(#myrow.type)'>
												<display:column style="text-align:left;" title="TYPE">
														<s:text name="RE" />
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													
														<s:property value="#myrow.payamount"/>
													
												</display:column>
												</s:if>
												<s:else>
												
												<display:column style="text-align:left;" title="TYPE">
													<s:if test='"P".equals(#myrow.type)'>
														<s:text name="PREMIUM" />
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:text name="CLAIM" />
													</s:if>
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
														<s:if test='"P".equals(#myrow.type)'>
															+
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															-
														</s:if>
													</s:if>
													<s:if test='"PT".equals(allocType)'>
														<s:if test='"P".equals(#myrow.type)'>
															-
														</s:if>
														<s:if test='"C".equals(#myrow.type)'>
															+
														</s:if>
													</s:if>
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													<s:if test='"P".equals(#myrow.type)'>
														<s:property value="#myrow.payamount"/>
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														<s:property value="#myrow.payamount"/>
													</s:if>
												</display:column>
											</s:else>
												<display:setProperty name="export.excel" value="true"/>
												<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf" value="false" />										
											</display:table>
										</div>
										<br class="clear"/>
										<div class="boxcontent">
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="payment.reverseDate" />
												</div>
												<div class="tbox">
													<s:textfield name="reversalDate" id="reversalDate"  cssClass="inputBox"  />
													</div>
											</div>
											<br class="clear"/>
										</div>
										
									</div>
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />																
								<s:hidden name="serial_no" />
								<s:hidden name="brokerid" />
								<s:hidden name="cedingid" />
								<s:hidden name="allocateddate" />
								<s:hidden name="pay_rec_no" />
								<s:hidden name="brokername" />
								<s:hidden name="cedingCo" />
								<s:hidden name="allocType"/>
								<s:hidden name="type"/>
								<s:hidden name="retroType"/>
								<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backDetailsPage()" />
								<input type="button"  value="Submit"  class="btn btn-sm btn-success" onclick="allSubmit()" />							
							</div>
							
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>
			</s:if>
			
			<s:if test='"revSuccess".equalsIgnoreCase(partToShow)'>
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="allocationForm" name="allocationForm" theme="simple" action="" method="post" >					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">																		
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:if test='"PT".equals(type)'>
														<s:text name="Premium.PaymentNo" />
													</s:if>
													<s:else>
														<s:text name="Premium.ReceiptNo" />
													</s:else>
												</div>
												<div class="tbox">
													<s:property value="pay_rec_no"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.Broker" />
												</div>
												<div class="tbox">
													<s:property value="brokername"/>
												</div>
											</div>
											<s:if test='cedingCo != "" || cedingCo != null'>
											<div class="textfield">
												<div class="text txtB">													
													<s:text name="RiskDetails.CedingCompany" />
												</div>
												<div class="tbox">
													<s:property value="cedingCo"/>
												</div>
											</div>
											</s:if>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">									
									<div class="panel-body" style="overflow: auto;">
										<display:table name="allocatedStatusList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found"	value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column style="text-align:left;" title="Currency" property="currency" />
											<display:column style="text-align:right;" title="Allocated" property="allocated" />
											<display:column style="text-align:right;" title="Utilized" property="utilized" />
											<display:column style="text-align:right;" title="Not Utilized" property="notUtilized" />
											<display:column style="text-align:left;" title="Status" property="status" />
										
											<display:setProperty name="export.excel" value="true"/>
											<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
											<display:setProperty name="export.csv" value="false" />
											<display:setProperty name="export.xml" value="false" />
											<display:setProperty name="export.pdf" value="false" />
										</display:table>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.reversedContracts" />
									</div>									
									<div class="panel-body" style="overflow: auto;">
										<display:table name="allocateDetailsList" pagesize="10" requestURI="" class="table table-bordered" uid="row" id="record" export="false">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found"	value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<s:set name="myrow" value="#attr.record"/>
											<display:column style="text-align:center;" title="Allocation No." property="serial_no" />
											<display:column style="text-align:center;" title="Transaction No" property="transactionno" />
											<display:column style="text-align:center;" title="Contract No" property="contractno" />
											<display:column style="text-align:center;" title="Business Type" property="productname" />
											<s:if test='"RE".equals(#myrow.type)'>
												<display:column style="text-align:left;" title="TYPE">
														<s:text name="RE" />
												</display:column>
												<display:column style="text-align:right;" title="SIGN">
													<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"C".equals(#myrow.checkPC)'>
														+
													</s:if>
													<s:if test='"P".equals(#myrow.checkPC)'>
														-
													</s:if>
												</s:if>	
												</display:column>		
												<display:column style="text-align:right;" title="Amount" >
													
														<s:property value="#myrow.netdue"/>
													
												</display:column>
												</s:if>
											<s:else>
											<display:column style="text-align:left;" title="TYPE">
												<s:if test='"P".equals(#myrow.type)'>
													<s:text name="PREMIUM" />
												</s:if>
												<s:if test='"C".equals(#myrow.type)'>
													<s:text name="CLAIM" />
												</s:if>
											</display:column>
											<display:column style="text-align:right;" title="SIGN">
												<s:if test='"RT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.type)'>
														+
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														-
													</s:if>
												</s:if>
												<s:if test='"PT".equals(allocType)'>
													<s:if test='"P".equals(#myrow.type)'>
														-
													</s:if>
													<s:if test='"C".equals(#myrow.type)'>
														+
													</s:if>
												</s:if>
											</display:column>		
											<display:column style="text-align:right;" title="Amount" >
												<s:if test='"P".equals(#myrow.type)'>
													<s:property value="#myrow.netdue" />
												</s:if>
												<s:if test='"C".equals(#myrow.type)'>
													<s:property value="#myrow.payamount" />
												</s:if>
											</display:column>
											</s:else>
											<display:setProperty name="export.excel" value="true"/>
											<display:setProperty name="export.excel.filename" value="Allocated Contracts.xls"/>
											<display:setProperty name="export.csv" value="false" />
											<display:setProperty name="export.xml" value="false" />
											<display:setProperty name="export.pdf" value="false" />
										</display:table>
									</div>
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<s:hidden name="contractId" id="contractId" />																
								<s:hidden name="pay_rec_no" />
								<s:hidden name="brokerid" />
								<s:hidden name="cedingid" />
								<s:hidden name="serial_no" />
								<s:hidden name="cedingCo" />
								<s:hidden name="brokername" />
								<s:hidden name="flag" />
								<s:hidden name="allocType" />
								<s:hidden name="type" />
								<input type="button"  value="Back"  class="btn btn-sm btn-danger" onclick="backDetailsPage()" />
								<input type="button"  value="Cancel"  class="btn btn-sm btn-warning" onclick="backTran()" />							
							</div>
							
						</div>
					</div>
					<s:hidden name="alloccurrencyid" />
					</s:form>
				</div>
			</div>						
			</s:if>
		</div>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript" >
<s:if test='"transactionAllocation".equalsIgnoreCase(partToShow)'>
selectedcode();
</s:if>
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
	document.allocationForm.searchType.value=mode;
		document.allocationForm.action="${pageContext.request.contextPath}/allocationTreasury.do";
		document.allocationForm.submit();
	}
	function showFullData(){
		document.allocationForm.action="${pageContext.request.contextPath}/allocationTreasury.do?fullSearch=Yes";
		document.allocationForm.submit();
		
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
	$('#currencySearch').on( 'keyup', function () {
	$('#gridTableMake').DataTable().columns(6).search(this.value).draw();
	} );
	function selectedcode(){
	var count = "";
		var attandenceAgentCode=document.getElementById("selectTransactionNo").value;
		attandenceAgentCode = attandenceAgentCode.substring(0, attandenceAgentCode.length-1);
		var amoumt=document.getElementById("selreceivePayAmount").value;
		amoumt = amoumt.substring(0, attandenceAgentCode.length-1);
		if(amoumt!=""){
		var val6=amoumt.split(',');
		}
		if(attandenceAgentCode!=""){
		var val5=attandenceAgentCode.split(',');
		for(var j=0;j<val5.length;++j){
		var k=document.getElementById("chkbox["+val5[j]+"]".trim());
		document.getElementById("receivePayAmounts["+val5[j]+"]").value=val6[j];
		document.getElementById("previousValue["+val5[j]+"]").value=val6[j];
		document.getElementById("receivePayAmounts["+val5[j]+"]").disabled=false;
		 k.checked=true;
		}
	}
	}

	var reversalDate = new calendar(document.forms[0].elements["reversalDate"]);
	reversalDate.year_scroll = true;
	reversalDate.time_comp = false;
	var accountDate = new calendar(document.forms[0].elements["accountDate"]);
	accountDate.year_scroll = true;
	accountDate.time_comp = false;

	function viewMode(recNo,brokerName,cedingName,method,slNo,flag,curId,type) {
		document.allocationForm.pay_rec_no.value=recNo;
		document.allocationForm.serial_no.value=slNo;
		document.allocationForm.brokername.value=brokerName;
		document.allocationForm.cedingCo.value=cedingName;
		document.allocationForm.flag.value=flag;
		document.allocationForm.alloccurrencyid.value=curId;
		document.allocationForm.retroType.value=type;
		document.allocationForm.action="${pageContext.request.contextPath}/"+method+"Treasury.action";
		document.allocationForm.submit();
	}

	function allSubmit() {
		doIt=confirm("Do you want to Reverse?","Yes","No");
		if(doIt) {
			document.allocationForm.action="${pageContext.request.contextPath}/reverseInsertTreasury.action";
			document.allocationForm.submit();
		}
	}

	function allocateSubmit() {
		document.allocationForm.action="${pageContext.request.contextPath}/contractAllocateTreasury.action";
		document.allocationForm.submit();
	} 

	function viewPayment(val1,val2,val3,title,selectId) {
		if(document.getElementById(selectId).checked==true) {
			document.allocationForm.cedingid.value=val2;
			document.allocationForm.brokerid.value=val1;
			document.allocationForm.serial_no.value=val3;
			document.allocationForm.hidSelCurrencey.value = title;
		}
	}
 	
 	var check="";
	function checkBox() {
	
		//layerno
		//document.allocationForm.layerno.value=val2;
		
		var chks=document.getElementsByName("checkboxs");
		var checkCount = 0;
		var text = new Array();
		var strtext = ""; 
		//alert("valued"+valued);
 
		for (var i = 0; i < chks.length; i++) { 
			if (chks[i].checked) {
				var arlength = text.length;   
				text[arlength] = chks[i].value;
			}
		} strtext = text.join(",");
		document.getElementById("contractId").value = strtext ;
	}

	function contractAllocate() {
		document.allocationForm.brokerid.value=document.allocationForm.brokerid.value;
		document.allocationForm.cedingid.value=document.allocationForm.cedingid.value;
 		//document.allocationForm.action="${pageContext.request.contextPath}/PaymentInit.do?method=transAllocate";
 		document.allocationForm.action = "${pageContext.request.contextPath}/contractAllocateTreasury.action"
 		document.allocationForm.submit();
	}
	
	function selvalue(id,val) {
		//if(val==0) {
			//document.getElementById("hidSelCurrencey").value='';
		//} else {
			//document.getElementById("hidSelCurrencey").value=val;
		//}
		document.getElementById(id).title = val;
	}
	function setProcess(val) {
		document.allocationForm.hideprocessType.value=val;
	}
	function backTran() {
		document.allocationForm.action="${pageContext.request.contextPath}/allocationTreasury.do";
		document.allocationForm.submit();
	}

	function backDetailsPage() {
		document.allocationForm.cedingid.value='';
		document.allocationForm.action="${pageContext.request.contextPath}/allocateDetailsTreasury.action";
		document.allocationForm.submit();
	}

	function TransAllocate() {
		//document.allocationForm.action="${pageContext.request.contextPath}/PaymentInit.do?method=allocateTransaction";
		var oTable = $('#gridTableMake').DataTable();
		var rowcollection =  oTable.$("input[name=checkItem]:checked", {"page": "all"});
		var checkedVal ="";
		var checkedVal1 ="";
		var colAchange ="";
		var count="";
		var colAchange ="" ;
		rowcollection.each(function(index,elem){
			checkedVal = checkedVal.concat($(elem).val()+',')
		});
		var totralcount = document.getElementById("totalreccount").value;
		var rowcollection1 =  oTable.$("input[name=receivePayAmounts]", {"page": "all"});
		rowcollection1.each(function(index,elem){
			checkedVal1 = checkedVal1.concat($(elem).val()+',')
		});
		var getVal = checkedVal1.split(",");
		var selectVal="";
		for(var i=0;i<getVal.length;i++){
		if(''!=getVal[i]){
		selectVal = selectVal.concat(getVal[i]+',');
		}
		}
		document.getElementById("selectTransactionNo").value =checkedVal;
		document.getElementById("selreceivePayAmount").value =selectVal;
		document.allocationForm.action="${pageContext.request.contextPath}/submitAllocationTreasury.action";
		document.allocationForm.submit();
	}
	
	function TransRetroAllocate() {
		//document.allocationForm.action="${pageContext.request.contextPath}/PaymentInit.do?method=allocateTransaction";
		document.allocationForm.action="${pageContext.request.contextPath}/submitRetroAllocationTreasury.action";
		document.allocationForm.submit();
	}
function get(tableId) {
// returns an array for the values of all input elements in the last row of given table
    var t = document.getElementById(tableId);
    var r = t.rows[t.rows.length-1];
    var inputs = r.getElementsByTagName("input");
    var result = new Array(inputs.length);
    for (var i=0; i<inputs.length; i++)
        result[i] = inputs[i].value; // not innerHTML or something
    return result;
}
	ajaxCheck('','','S','','');

	function ajaxCheck(contNo,transNo,checkPC,id,mode) {
		var amount;
		var payRecNo='<bean:write name="PaymentActionForm" property="serial_no"/>';
		var cedId='<bean:write name="PaymentActionForm" property="cedingid"/>';
		var brokId='<bean:write name="PaymentActionForm" property="brokerid"/>';
		var curId='<bean:write name="PaymentActionForm" property="alloccurrencyid"/>';
		
		if(mode=='CH'){	
			if(checkPC=="P") {	
				if(document.getElementById("chkbox"+id).checked)
					document.getElementById("receivenetdue"+id).value=document.getElementById("netdue"+id).value;
				else
					document.getElementById("receivenetdue"+id).value="";							
			}
			if(checkPC=="C") {
				if(document.getElementById("chkbox"+id).checked)
					document.getElementById("receivepayamount"+id).value=document.getElementById("payment"+id).value;
				else
					document.getElementById("receivepayamount"+id).value="";
			}
		}
		
		if(checkPC=='P') {
		  amount=document.getElementById("receivenetdue"+id).value;
		} else if(checkPC=='C') {
			amount=document.getElementById("receivepayamount"+id).value;
		}	
		// alert(amount);
	
		if (window.XMLHttpRequest) {
			xmlhttp=new XMLHttpRequest();
		} else {
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				document.allocationForm.unUtilizedAmt.value=xmlhttp.responseText;
			}
		}
		xmlhttp.open("POST",'${pageContext.request.contextPath}/PaymentInit.do?method=ajaxCheck&contNo='+contNo+'&amount='+amount+'&transNo='+transNo+'&checkPC='+checkPC+'&mode='+mode+'&payRecNo='+payRecNo+'&cedId='+cedId+'&brokId='+brokId+'&curId='+curId,true);
		xmlhttp.send();
	}
	function setAmount(chkboxId,amountId,rownum,amountVal,previousVal,checkPC) {
		var unUtiliziedAmt = document.getElementById('unUtilizedAmt').value
		var unUtil = unUtiliziedAmt.replace(new RegExp(',', 'g'),'');
		var allocType = '<s:property value="allocType"/>';
		var result = 0;
		if(document.getElementById(chkboxId).checked==true) {
			document.getElementById(amountId).value = amountVal;
			document.getElementById(amountId).disabled=false;
			if((allocType=="RT" && checkPC=="P") || (allocType=="PT" && checkPC=="C")) {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			else if((allocType=="RT" && checkPC=="C") || (allocType=="PT" && checkPC=="P")) {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			
			document.getElementById('unUtilizedAmt').value =  result.toFixed(2);
			document.getElementById(previousVal).value = amountVal;
		}
		else {
			var prevVal = document.getElementById(previousVal).value;
			if((allocType=="RT" && checkPC=="P") || (allocType=="PT" && checkPC=="C")) {
				result = parseFloat(unUtil) + parseFloat(prevVal);
			}
			else if((allocType=="RT" && checkPC=="C") || (allocType=="PT" && checkPC=="P")) {
				result = parseFloat(unUtil) - parseFloat(prevVal);
			}
			//var result = parseFloat(unUtil) - parseFloat(amountVal);
			//var prevVal = document.getElementById(previousVal).value;
			//var result = parseFloat(unUtil) - parseFloat(prevVal);
			document.getElementById(amountId).value = "";
			document.getElementById(amountId).disabled=true;
			document.getElementById('unUtilizedAmt').value = result.toFixed(2);
		}
	}
	function setAmount1(chkboxId,amountId,rownum,amountVal,previousVal,checkPC) {
		var unUtiliziedAmt = document.getElementById('unUtilizedAmt').value
		var unUtil = unUtiliziedAmt.replace(new RegExp(',', 'g'),'');
		var allocType = '<s:property value="allocType"/>';
		var result = 0;
		if(document.getElementById(chkboxId).checked==true) {
		document.getElementById(amountId).value = amountVal;
		document.getElementById(amountId).disabled=false;
			if((allocType=="RT" && checkPC=="P") || (allocType=="PT" && checkPC=="C")) {
				result = parseFloat(unUtil) - parseFloat(amountVal);
			}
			else if((allocType=="RT" && checkPC=="C") || (allocType=="PT" && checkPC=="P")) {
				result = parseFloat(unUtil) + parseFloat(amountVal);
			}
			
			document.getElementById('unUtilizedAmt').value =  result.toFixed(2);
			document.getElementById(previousVal).value = amountVal;
		}
		else {
			var prevVal = document.getElementById(previousVal).value;
			if((allocType=="RT" && checkPC=="P") || (allocType=="PT" && checkPC=="C")) {
				result = parseFloat(unUtil) + parseFloat(prevVal);
			}
			else if((allocType=="RT" && checkPC=="C") || (allocType=="PT" && checkPC=="P")) {
				result = parseFloat(unUtil) - parseFloat(prevVal);
			}
			
			//var result = parseFloat(unUtil) - parseFloat(amountVal);
			//var prevVal = document.getElementById(previousVal).value;
			//var result = parseFloat(unUtil) - parseFloat(prevVal);
			//document.getElementById(amountId).value = "";
			//alert(result);
			document.getElementById(amountId).value = "";
			document.getElementById(amountId).disabled=true;
			document.getElementById('unUtilizedAmt').value = result.toFixed(2);
		}
	}
	function setUtilizedAmt(amountVal) {
		document.getElementById('unUtilizedAmt').value = document.getElementById('unUtilizedAmt').value - amountVal;
	}
	
	function updateUtilizedAmount(chkbox, previousValue, obj, checkPC) {
		if(document.getElementById(chkbox).checked==true) {
			var allocType = '<s:property value="allocType"/>';
			var prevVal = 0;
			var objVal = 0;
			if(document.getElementById(previousValue).value!="") {
				prevVal = parseFloat(document.getElementById(previousValue).value);
			}
			if(obj.value !="") {
				objVal = obj.value;
			}
			var unUtil = parseFloat(document.getElementById('unUtilizedAmt').value);
			//var result = parseFloat(document.getElementById('unUtilizedAmt').value) - parseFloat(document.getElementById(previousValue).value) + parseFloat(obj.value);
			if((allocType=="RT" && checkPC=="P") || (allocType=="PT" && checkPC=="C")) {
				result = unUtil + prevVal - parseFloat(objVal);
			}
			else if((allocType=="RT" && checkPC=="C") || (allocType=="PT" && checkPC=="P")) {
				result = unUtil - prevVal + parseFloat(objVal);
			} 
			document.getElementById('unUtilizedAmt').value = result.toFixed(2);
			document.getElementById(previousValue).value = objVal;
		}
	}
	
	
	function search(){
		document.allocationForm.action="${pageContext.request.contextPath}/allocationTreasury.action";
		document.allocationForm.submit();	
	}
	function getJurnal(value,allocationNo) {
		document.allocationForm.serial_no.value=value;
	 	document.allocationForm.flag.value="JOURNAL";
		document.allocationForm.action="${pageContext.request.contextPath}/allocateDetailsTreasury.action?allocationNo="+allocationNo;
		document.allocationForm.submit();		
	}
	function getJurnal1(value,value1) {
		document.allocationForm.serial_no.value=value;
		document.allocationForm.status.value=value1;
	 	document.allocationForm.flag.value="JOURNAL";
		document.allocationForm.action="${pageContext.request.contextPath}/allocateViewTreasury.action";
		document.allocationForm.submit();		
	}
	
	function back5() {
	document.allocationForm.serial_no.value="";
	document.allocationForm.flag.value="";
		document.allocationForm.action="${pageContext.request.contextPath}/allocateDetailsTreasury.action";
		document.allocationForm.submit();	
	}
	function back6() {
	document.allocationForm.serial_no.value="";
	document.allocationForm.flag.value="FullView";
		document.allocationForm.action="${pageContext.request.contextPath}/allocateViewTreasury.action";
		document.allocationForm.submit();	
	}
	
	function getAmoungtValue(checkbox,id){
	if(document.getElementById(checkbox).checked==true) {
	document.getElementById("previousAmount").value = document.getElementById(id).value+",";
	}
	}

</script>	
</body>
</html>
