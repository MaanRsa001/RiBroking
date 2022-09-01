<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 

<!DOCTYPE HTML>
<html>
<head>
<sj:head jqueryui="true" jquerytheme="start"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
		<link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
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
					responsive: true
				});
			} catch(err){}
		} );
	 </script>
		
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">    
	<style>
		#tooltip{
			position:absolute;
			border:1px solid #333;
			background:#f7f5d1;
			padding:2px 5px;
			color:#333;
			display:none;
		}  
	</style>	
</head>  
<body>
<s:if test='"payment".equals(mode)'>
	<%-- --  <div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Allocation Details"/>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
								<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th class="no-sort"><s:text name="S.No"/></th>
										<th><s:text name="Allocation No." /></th>
										<th><s:text name="Allocation Date" /></th>
										<th><s:text name="Business Type" /></th>
										<th><s:text name="Type" /></th>
										<th><s:text name="Sign" /></th>
										<th><s:text name="Amount" /></th>
										<th><s:text name="Status" /></th>
										<th><s:text name="Receipt / Payment No" /></th>
										<th><s:text name="Settlement Type" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="allocationList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td>${SNO}</td>
												<td>${INCEPTION_DATE}</td>												
												<td>${PRODUCT_NAME}</td>
												<td>${TYPE}</td>
												<td>${SIGN}</td>
												<td>${PAID_AMOUNT}</td>
												<td>${STATUS}</td>
												<td>${RECEIPT_NO}</td>
												<td>${TRANS_TYPE}</td>
											</tr>
										</s:iterator>
									</tbody>
									</table>
											</div>			
								</div>
							</div>
							</div> 
							<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow" align="center">
									  <b> <s:text name="Allocation Details"/></b>
								</div>
							</div>
							</div>-->--%>
							
	<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">					
					<div class="table1">						
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body"><!--
									   <s:property value="currencyName"/>-->
										<display:table name="allocList" pagesize="10"	requestURI="reserveListClaim.do" class="footable" uid="row" id="record" export="true">
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="true" style="text-align:center;" title="Allocation No."	property="SNo" />	
										<display:column sortable="true" style="text-align:right;"  title="Allocation Date" property="date" />
										<display:column sortable="true" style="text-align:right;"  title="Business Type" property="productName" />
										<display:column sortable="true" style="text-align:right;" title="Type" property="type" />
										<display:column sortable="true" style="text-align:center;" title="Sign" property="sign" />
										<display:column sortable="true" style="text-align:left;" title="Amount" property="paid_Amount_Orig_curr" />
										<display:column sortable="true" style="text-align:left;" title="Status" property="status_of_claim" />
										<display:column sortable="true" style="text-align:left;" title="Receipt / Payment No" property="receiptNo" />
										<display:setProperty name="export.excel" value="true" />
										<display:setProperty name="export.excel.filename" value="ReserveList.xls"/>
										<display:column sortable="true" style="text-align:left;" title="Settlement Type" property="transactionType" />
										<display:setProperty name="export.csv" value="false" />
										<display:setProperty name="export.xml" value="false" />
										<display:setProperty name="export.pdf" value="false" />
									</display:table>	
										<s:if test='!"".equals(totalAmount)'>
										<div class="boxcontent" align="right">
											<div class="textfield33"></div>
											<div class="textfield33"></div>
											&nbsp;&nbsp;&nbsp;
											<div class="textfield33">
												<div class="text txtB">
													<s:text name="Total Allocated Amount" />
												</div>
												<div class="tbox">
													<s:textfield name="totalAmount" id="totalAmount" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>	
										</s:if>						
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">
							<div class="boxcontent" align="center">
								<input type="button" value="Close" class="btn btn-xs btn-danger" onclick="window.close()" />							
							</div>
						</div>										
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>
</s:if>
<s:else>
<%--<div class="table0" style="width: 100%; margin: 0 auto;">

	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">					
					<div class="table1">						
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">	
										
									<div class="panel-body"><!--
									   <s:property value="currencyName"/>-->
										<display:table name="ReserveList" pagesize="10"	requestURI="reserveListClaim.do" class="footable" uid="row" id="record" export="true">
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="true" style="text-align:center;" title="Reserve Id"	property="SNo" />	
										<display:column sortable="true" style="text-align:right;" format="{0,number,0,000.00}" title="Loss Estimate - Our Share - OC" property="loss_Estimate_Revised_Orig_Curr" />
										<display:column sortable="true" style="text-align:right;" property="loss_Estimate_Revised_USD" title='Loss Estimate - Our Share - ${requestScope.currencyName}' />
										<display:column sortable="true" style="text-align:right;" format="{0,number,0,000.00}" title="Paid Amount - Our Share - OC" property="paid_Amount_Orig_curr" />
										<display:column sortable="true" style="text-align:right;" title="Paid Amount - Our Share -${requestScope.currencyName}" property="paid_Amount_USD" />
										<display:column sortable="true" style="text-align:center;" title="Generated Date" property="cliam_update_Date" />
										<display:column sortable="true" style="text-align:left;" title="Update Reference" property="update_Reference" />
										<display:setProperty name="export.excel" value="true" />
										<display:setProperty name="export.excel.filename" value="ReserveList.xls"/>
										<display:setProperty name="export.csv" value="false" />
										<display:setProperty name="export.xml" value="false" />
										<display:setProperty name="export.pdf" value="false" />
									</display:table>						
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">
							<div class="boxcontent" align="center">
								<input type="button" value="Close" class="btn btn-xs btn-primary" onclick="window.close()" />							
							</div>
						</div>										
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>  --%>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				
							<div class="boxcontent">
								<div class="panel ">
									<div class="panel-body">
										<s:if test="ReserveList!=null && ReserveList.size()>0">
										<s:if test='!"3".equals(productId)'>
										<table class="footable" width="100%">
											<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserve ID" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"><s:text name="Outstanding Reserve Amount - Our Share – OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Surveyor / Adjustor Fee - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Other Professional Fee - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Total Outstanding Reserve - Our Share - OC" /></th>
												<%-- 	<s:if test='!"1".equals(productId)'>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement Premium - Our Share - OC" /></th>
													</s:if>--%>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Loss Reserve (IBNR) / Contigent Reserve - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserve Update Date" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Update Reference" /></th>
												</tr>
												<tr>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="ReserveList" status="record" >
													<tr>
														<td> <s:property value="SNo" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totala" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totalb" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totalc" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="Cibnr100Oc" /> </td>
														<td > <s:property value="cliam_update_Date" /> </td>
														<td > <s:property value="update_Reference" /> </td>													
													</tr>
												</s:iterator>
												<tr>
													<td><s:text name="Total "/></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalORpaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalSApaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalOPpaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalTORpaidAmount" /></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>											
												</tr>
											</tbody>
										</table>
										</s:if>
										<s:else>
										<table class="footable" width="100%">
											<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserve ID" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Outstanding Reserve Amount - Our Share – OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Surveyor / Adjustor Fee - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Other Professional Fee - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;" colspan="3"> <s:text name="Total Outstanding Reserve - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement Premium - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Loss Reserve (IBNR) - Our Share - OC" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserve Update Date" /></th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Update Reference" /></th>
												</tr>
												<tr>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Reserved Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Paid Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Amount" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
													<th style="text-align: center; vertical-align: middle;"> <s:text name="" /> </th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="ReserveList" status="record">
													<tr>
														<td> <s:property value="SNo" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="lero2c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="saf3c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4a" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4b" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="ofos4c" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totala" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totalb" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="totalc" /> </td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="reInspremiumOS" /></td>
														<td style="text-align: right; vertical-align: middle;"> <s:property value="Cibnr100Oc" /> </td>
														<td> <s:property value="cliam_update_Date" /> </td>
														<td> <s:property value="update_Reference" /> </td>												
													</tr>
												</s:iterator>
												<tr>
													<td><s:text name="Total "/></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalORpaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalSApaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalOPpaidAmount" /></td>
													<td></td>
													<td></td>
													<td style="text-align: right; vertical-align: middle;"><s:property value="totalTORpaidAmount" /></td>
													<td></td>
													<td></td>
													<td></td>	
													<td></td>	
													<td></td>											
												</tr>
											</tbody>
											<tfoot>
												
											</tfoot>
										</table>
										</s:else>
										</s:if>
										<s:else>
										Nothing found to display
										</s:else>
									
						<br class="clear"/>
						<div class="tablerow">
							<div class="boxcontent" align="center">
								<input type="button" value="Close" class="btn btn-sm btn-warning"  onclick="window.close()" />							
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:else>
</body>
</html>
