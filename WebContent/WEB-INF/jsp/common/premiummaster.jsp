<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
 
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
				var data = $('#gridTableMake1').dataTable( {
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
		//$(document).ready(function() {
		//var oTable =$('#gridTableMake').dataTable();
		//var data = $('div.dataTables_filter input').val();
		//alert (data);
		////});
		//$("input:eq(6)").val("myDefaultVal").trigger('keyup');
	 </script>	
    <title>My JSP 'CedingView.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
<script type="text/javascript">
	 $(function() {
	    $( "#transactionDateSearch" ).datepicker({
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
				<s:form name="premiummaster" id="premiummaster" action="" theme="simple" >
				<div class="tablerow">
							<span style="color: red;"><s:actionerror /> </span>
				</div>
					<s:if test='flag.equals("new")'>
					<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Heading.PREMIUMDETAILS"/>
									  <span class="pullRight"> 
										<input type="button" value="Contract Identifier"
										class="btn btn-sm btn-success"
										onclick="contractSearch();" />
									</span> 
									<!--<span class="pullRight"> 
										<input type="button" value="New Premium"
										class="btn btn-sm btn-success"
										onclick="newPremium();" />
									</span>-->
								</div>
							</div>
							<div class="panel-body">
								<div class="textfield">
										<div class="text">
											<s:text name="label.productId" />
										</div>
										<div class="tbox" >
											<s:select name="transactionType" id="transactionType" list="productIdList" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---"  onchange="getDepart(this.value);"/>												
										</div>
									</div>	
									<div class="textfield" id="conId" style="display: none;">
										<div class="text">
											<s:text name="label.contractNo" />
										</div>
										<div class="tbox">
										
											<s:textfield cssClass="inputBox" name="contractNo"  id="contractNo" maxlength="50" />												
										</div>
									</div>											
									 <div class="textfield" id="layer" style="display: none;">
										<div class="text">
											<s:text name="label.layerNo" />
										</div>
										<div class="tbox" >
											<s:textfield cssClass="inputBox" name="layerno" id="layerno"  />												
										</div>
									</div>											
									
									<div class="textfield" id="deptID" style="display: none;">
										<div class="text">
											<s:text name="Premium.SubClass" />
										</div>
										<div class="tbox" id="exchRate">
										<s:select name="dept" id="dept" list="predepartIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="N" cssClass="inputBoxS" headerValue="---Select---" />												
										</div>
									</div>
								</div>
							</div>
						<div align="center">
						  <input type="button"  value="Back"   class="btn btn-sm btn-danger" onclick="getBack()" />
						  <input type="button"  value="Submit"   class="btn btn-sm btn-success" onclick="getSubmitPremium()" />
						</div>
					</s:if>
					<s:elseif test='flag.equals("search")'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Contract Identifier"/>
								</div>
							</div>
								<div class="panel-body">
									<div class="textfield">
										<div class="text">
											<s:text name="label.productId" />
										</div>
										<div class="tbox" >
											<s:select name="transactionType" id="transactionType" list="productIdList" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" />												
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.cedingcompany" />
										</div>
										<div class="tbox">
										<s:select name="ceding_Company_Name" id="ceding_Company_Name" list="cedingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>											
									<div class="textfield">
										<div class="text">
											<s:text name="label.broker" />
										</div>
										<div class="tbox" >
										<s:select name="broker" id="broker" list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>											
									<div class="textfield">
										<div class="text">
											<s:text name="label.underwritingyear" />
										</div>
										<div class="tbox" >
										<s:select name="underwritingYear" id="underwritingYear" list="yearList" listKey="YEAR" listValue="YEAR" headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>	
									<div class="textfield">
										<div class="text">
											<s:text name="label.dept" />
										</div>
										<div class="tbox" id="exchRate">
										<s:select name="dept" id="dept" list="departIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>	
								</div>	
							</div>
						<div align="center">
						  <input type="button"  value="Back"   class="btn btn-sm btn-danger" onclick="getBack()" />
						  <input type="button"  value="Submit"   class="btn btn-sm btn-success" onclick="getSubmitContractident()" />
						</div>
						
					</s:elseif>
					<s:elseif test='"contractidentifier".equals(flag)'>
					<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Contract Identifier"/>
								</div>
							</div>
							<div class="panel-body">
								<div class="textfield">
									<div class="text">
										<s:text name="label.productId" />
									</div>
									<div class="tbox" >
										<s:select name="transactionType" id="transactionType" list="productIdList" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" />												
									</div>
								</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.cedingcompany" />
										</div>
										<div class="tbox">
										<s:select name="ceding_Company_Name" id="ceding_Company_Name" list="cedingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>											
									<div class="textfield">
										<div class="text">
											<s:text name="label.broker" />
										</div>
										<div class="tbox" >
										<s:select name="broker" id="broker" list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>											
									<div class="textfield">
										<div class="text">
											<s:text name="label.underwritingyear" />
										</div>
										<div class="tbox" >
										<s:select name="underwritingYear" id="underwritingYear" list="yearList" listKey="YEAR" listValue="YEAR" headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />												
										</div>
									</div>	
									<div class="textfield">
										<div class="text">
											<s:text name="label.dept" />
										</div>
										<div class="tbox" id="exchRate">
										<s:select name="dept" id="dept" list="departIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />												
									</div>
								</div>
							</div>	
						</div>
					<div align="center">
					  <input type="button"  value="Back"   class="btn btn-sm btn-danger" onclick="getBack()" />
					  <input type="button"  value="Submit"   class="btn btn-sm btn-success" onclick="getSubmitContractident()" />
					</div>
					<br class="clear"/>
					<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Contract Identifier Details"/>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
								<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Proposal No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Company Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Broker Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Dept Name" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Inception Date" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="ExpiryDate" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="UW Year" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="UnderWriter" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Old Contract No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Premium" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="contractIdentifierList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td><s:property value="proposal_No" /></td>
												<td><s:property value="contractNo" /></td>
												<td><s:property value="ceding_Company_Name" /></td>
												<td><s:property value="broker" /></td>
												<td><s:property value="dept" /></td>
												<td><s:property value="inception_Date" /></td>
												<td><s:property value="expDate" /></td>
												<td><s:property value="underwritingYear" /></td>
												<td><s:property value="underwriter" /></td>
												<td><s:property value="old_Contract" /></td>
												<td>
												<input type="button" class="btn btn-sm btn-info" value="P" style="cursor: pointer;" onclick="funPremiumMode('<s:property value="transactionType" />','<s:property value="contractNo" />','<s:property value="layerno" />','<s:property value="proposal_No" />','<s:property value="departmentId"/>')" />
												</td>
											</tr>
										</s:iterator>
									</tbody>
									</table>
											</div>			
								</div>
							</div>
							<div align="center">
								<!--  <input type="submit"  value="Back"   class="btn btn-sm btn-danger" onclick="getBack()" />-->
							</div>
						</div>
					</s:elseif>
					<s:else>
					
								
						<s:if test="premiumTempList.size()>0 && premiumList.size()>0">	
							<div class="panel panel-primary">
								<ul class="nav nav-tabs" style="color:blue">
								<s:if test="premiumList.size()>0">
									<li  class="active"><a data-toggle="tab" href="#menu1">Approved Transaction</a></li>
								</s:if>
								<s:if test="premiumTempList.size()>0">
							  		<li><a data-toggle="tab" href="#home">UnApproved Transaction</a></li>
							  	</s:if>
								</ul>
						</s:if>
						
						<s:if test=" premiumTempList.size()>0 &&  premiumList.size()>0">
						<div class="panel-body">
						<div class="tab-content">
						</s:if>
						<s:if test="premiumTempList.size()>0">
							<s:if test="premiumList.size()>0">
							  <div id="home" class="tab-pane fade">
							</s:if>
							<s:else>
							  <div id="home" class="tab-pane fade in active">
							</s:else>
						  
						  
						   <div class="panel-group" id="accordion">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													aria-expanded="true" href="#collapseOne"> <i
													class="glyphicon glyphicon-plus" id="detailsPlusTemp"
													style="cursor: pointer;" onclick="detailsClickTemp(true)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i>
													<i class="glyphicon glyphicon-minus" id="detailsMinusTemp"
													style="cursor: pointer; display: none;"
													onclick="detailsClickTemp(false)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i> </a>
											</h4>
										</div>

										<div id="detailsDataTemp" style="display: none;"
											class="panel-collapse collapse in">
											<div class="panel-body">
												<div class="textfield">
													<div class="text">
															<s:text name="Ceding Company Name" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="companyNameSearchTemp" id="companyNameSearchTemp" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Broker Name" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="brokerNameSearchTemp" id="brokerNameSearchTemp" cssClass="inputBox" />
													</div>
												</div>
												
												<div class="textfield">
													<div class="text">
														<s:text name="Contract No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="contractNoSearchTemp" id="contractNoSearchTemp" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Request No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="transactionNoSearchTemp" id="transactionNoSearchTemp" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Transaction Date" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="transactionDateSearchTemp" id="transactionDateSearchTemp" cssClass="inputBox" />
													</div>
												</div>
												
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('ST','Temp')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('','Temp')" />							
														
												</div>
											</div>
										</div>
									</div>
								</div>
						    <div class="panel panel-primary">
								<div class="panel-heading">
									UnApproved Transaction
								</div>
								<div class="panel-body">
											<div class="row">
											<div class="col-xs-12">
											   <table class="display responsive no-wrap" id="gridTableMake1" width="100%" cellspacing="0">
												<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Request No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Layer No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="label.type" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Inception Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Expiry Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Ceding Company" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Broker" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="TransactionDate" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Edit" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="View" /></th>
													
												</tr>
												</thead>
												<tbody>
													<s:iterator value="premiumTempList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="requestNo" /></td>
															<td><s:property value="contractNo" /></td>
															<td><s:property value="layerno" /></td>
															<td><s:property value="transactionTypeName" /></td>
															<td><s:property value="inception_Date" /></td>
															<td><s:property value="expDate" /></td>
															<td><s:property value="ceding_Company_Name" /></td>
															<td><s:property value="broker" /></td>
															<td>&nbsp;<s:property value="transactionDate" /></td>
															<td>
																<s:if test='#session.MenuRights.indexOf("PE")!=-1'>		
																	<a title="Edit" style="cursor: pointer;" onClick="EditMode('<s:property value="contractNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="transactionType" />','<s:property value="InsDate" />','<s:property value="dept" />','<s:property value="proposal_No" />','Temp') ">Edit</a>
																</s:if>
															</td>
															<td>
																<s:if test='#session.MenuRights.indexOf("PV")!=-1'>	
																  <a title="View" style="cursor: pointer;" onClick="ViewMode('<s:property value="contractNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="transactionType" />','<s:property value="dept" />','<s:property value="proposal_No" />','Temp')">View</a>
																</s:if>
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
						 </s:if>
						 <s:if test=" premiumList.size()>0">
						  <div id="menu1" class="tab-pane fade in active">
						  
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
															<s:text name="Ceding Company Name" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="companyNameSearch" id="companyNameSearch" cssClass="inputBox" />
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
														<s:text name="Contract No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="contractNoSearch" id="contractNoSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Transaction No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="transactionNoSearch" id="transactionNoSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Transaction Date" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="transactionDateSearch" id="transactionDateSearch" cssClass="inputBox" />
													</div>
												</div>
												
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S','Main')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('','Main')" />							
														<input type="button" value="Show Full Data" class="btn btn-sm btn-warning" style="cursor: pointer;" onclick="newPremiumList();" />	
														
												</div>
											</div>
										</div>
									</div>
								</div>
						    <div class="panel panel-primary">
									<div class="panel-heading">
										Approved Transaction
										  <span class="pullRight"> 
											<input type="button" value="Contract Identifier" class="btn btn-sm btn-warning" onclick="contractSearch();" />
											&nbsp;<input type="button" value="New Premium" class="btn btn-sm btn-success" onclick="newPremium();" />
										</span>
									</div>
									<div class="panel-body">
											<div class="row">
											<div class="col-xs-12">
											   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
												<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Layer No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="label.type" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Inception Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Expiry Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Ceding Company" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Broker" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="TransactionDate" /></th>
													<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Edit" /></th>
													</s:if>
													<th style="text-align: center; vertical-align: middle;"><s:text name="View" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="DN/CN" /></th>
													<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="Delete" /></th>--%>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Document" /></th>
												</tr>
												</thead>
												<tbody>
													<s:iterator value="premiumList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count" /></td>
															<td><s:property value="transactionNo" /></td>
															<td><s:property value="contractNo" /></td>
															<td><s:property value="layerno" /></td>
															<td><s:property value="transactionTypeName" /></td>
															<td><s:property value="inception_Date" /></td>
															<td><s:property value="expDate" /></td>
															<td><s:property value="ceding_Company_Name" /></td>
															<td><s:property value="broker" /></td>
															<td>&nbsp;<s:property value="transactionDate" /></td>
															<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
															<td>
																<s:if test='#session.MenuRights.indexOf("PE")!=-1'>		
																<s:if test='!"N".equalsIgnoreCase(#list.allocatedYN) && "Y".equalsIgnoreCase(#list.transOpenperiodStatus) &&  !"Y".equals(ceaseStatus)  && "".equalsIgnoreCase(#list.transDropDownVal)'>																					    
																	<a title="Edit" style="cursor: pointer;" onClick="EditMode('<s:property value="contractNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="transactionType" />','<s:property value="InsDate" />','<s:property value="dept" />','<s:property value="proposal_No" />','Main') ">Edit</a>
																</s:if>
																</s:if>
															</td>
															</s:if>
															<td>
																<s:if test='#session.MenuRights.indexOf("PV")!=-1'>	
																  <a title="View" style="cursor: pointer;" onClick="ViewMode('<s:property value="contractNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="transactionType" />','<s:property value="dept" />','<s:property value="proposal_No" />','Main' )">View</a>
																</s:if>
															</td>
															<td>
															  <%-- <a title="DN/CN" style="cursor: pointer;" onClick="DN('<s:property value="contractNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="transactionType" />','<s:property value="dept" />')">DN/CN</a>--%>
															</td>
															<%-- <td>
															<s:if test='"Y".equalsIgnoreCase(#list.deleteStatus)' >
														 	<center ><a title="Delete" style="cursor: pointer;" onClick="deletedata('<s:property value="contractNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="transactionType" />')">Delete</a></center>
												       	 </s:if>
															</td>--%>
															<td>
															  <a href="#" class="" title="Document" onclick="getDocList('<s:property value="proposal_No" />','<s:property value="contractNo" />','<s:property value="layerno" />','<s:property value="transactionNo" />','<s:property value="ceding_Company_Name" />','<s:property value="broker" />','premium','<s:property value="transactionType"/>');"> 
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
						 </s:if>
						</div>
						</div>
						
						</div>
						</div>
						
							</s:else>
							<s:hidden name="InsDate"/>
							<s:hidden name="premiumDisplay" value="premiumDisplay"/>
							<s:hidden name="multiuserError" id="multiuserError"/>
				</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
 <script>	
if('S'=='<s:property value="searchType"/>'){
	detailsClick(true);
	}
if('ST'=='<s:property value="searchType"/>'){
	detailsClickTemp(true);
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
function detailsClickTemp(val) {
	if(val){
  		document.getElementById('detailsDataTemp').style.display='block';		
		document.getElementById('detailsMinusTemp').style.display='block';
		document.getElementById('detailsPlusTemp').style.display='none';
    }else{
    	document.getElementById('detailsDataTemp').style.display='none';
		document.getElementById('detailsMinusTemp').style.display='none';
		document.getElementById('detailsPlusTemp').style.display='block';
    }
}
$('#companyNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(7).search(this.value).draw();
} );
$('#brokerNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8 ).search(this.value).draw();
} );
$('#contractNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(2).search(this.value).draw();
} );
$('#transactionNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
} );
$('#transactionDateSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );


$('#companyNameSearchTemp').on( 'keyup', function () {
$('#gridTableMake1').DataTable().columns(7).search(this.value).draw();
} );
$('#brokerNameSearchTemp').on( 'keyup', function () {
$('#gridTableMake1').DataTable().columns(8 ).search(this.value).draw();
} );
$('#contractNoSearchTemp').on( 'keyup', function () {
$('#gridTableMake1').DataTable().columns(2).search(this.value).draw();
} );
$('#transactionNoSearchTemp').on( 'keyup', function () {
$('#gridTableMake1').DataTable().columns(1).search(this.value).draw();
} );
$('#transactionDateSearchTemp').on( 'keyup', function () {
$('#gridTableMake1').DataTable().columns(9).search(this.value).draw();
} );
editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}
 function funSearchMode(mode,tableType)
     {
      document.premiummaster.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium&searchType="+mode+"&tableType="+tableType;
      document.premiummaster.submit();
     }
     function getBack()
     {
      document.premiummaster.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      document.premiummaster.submit();
     }
     function getSubmitContractident(){
      document.premiummaster.action="${pageContext.request.contextPath}/contractIdetifierPremiumMaster.action?flag=contractidentifier";
      document.premiummaster.submit();
     }
     function EditMode(contractno,transactionno,layerno,pro,InsDate,dept,proposal_No,tableType) {  
        	if(pro==1)
        	{  
	        	if("Temp"==tableType){
	        		document.premiummaster.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&proposal_No="+proposal_No+'&tableType='+tableType;
	        	}else{ 
					document.premiummaster.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&proposal_No="+proposal_No+'&tableType='+tableType;
				}
			}
			else if(pro==2)
			{
				if("Temp"==tableType){
		        		document.premiummaster.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&departmentId="+dept+"&proposal_No="+proposal_No+'&tableType='+tableType;
		        	}else{
					document.premiummaster.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&departmentId="+dept+"&proposal_No="+proposal_No+'&tableType='+tableType;
				}
			}
			else if(pro==3 || pro==5)
			{
					if("Temp"==tableType && pro==3){
		        		document.premiummaster.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&layerno="+layerno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&proposal_No="+proposal_No+'&tableType='+tableType;
		        	}else{
						document.premiummaster.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layerno+"&InsDate="+InsDate+"&productId="+pro+"&size=list&proposal_No="+proposal_No+'&tableType='+tableType;
					}
			}
			document.premiummaster.submit();   
		}

		function newPremium() {  
			document.premiummaster.action="${pageContext.request.contextPath}/newPremiumPremiumMaster.action?flag=new";
			document.premiummaster.submit();	
		} 
		function getSubmitPremium() {  
			var contNo=document.getElementById("contractNo").value; 
			var pro = document.getElementById("transactionType").value;
			var layer=document.getElementById("layerno").value;
			var dept=document.getElementById("dept").value;
			if(pro==""){
			confirm("Please Select Product Id.");
			}else{
			if(contNo==""){
			confirm("Please Enter Contract No.");
			}
			}
			if(pro==3 || pro==5){
			if(layer==""){
			confirm("Please Enter Layer No.");
			}
			}
			else if(pro==2){
			if(dept=="N"){
			confirm("Please Select Class");
			}
			}
			if(contNo!="" && pro!="" ){
			if(pro==1)
        	{       
			document.premiummaster.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=add&contNo="+contNo+"&productId="+pro+"&size=list";
			}
			else if(pro==2 && dept!="")
			{
			document.premiummaster.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=add&contNo="+contNo+"&productId="+pro+"&size=list&departmentId="+dept;			
			}
			else if((pro==3 || pro==5) && layer!="")
			{
			//document.premiummaster.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=add&contNo="+contNo+"&layerno="+layer+"&productId="+pro+"&size=list";			
			document.premiummaster.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=add&contNo="+contNo+"&productId="+pro+"&size=list";			
			
			}
			document.premiummaster.submit();
			}
		} 
		function ViewMode(contractno,transactionno,layerno,pro,dept,proposal_No,tableType) {  
			if(pro==1)
        	{
        	if("Temp"==tableType){
        		document.premiummaster.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&productId="+pro+"&proposal_No="+proposal_No+'&tableType='+tableType;
        	}else{
				document.premiummaster.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&productId="+pro+"&proposal_No="+proposal_No+'&tableType='+tableType;
			}
			
			}
			else if(pro==2)
			{
			if("Temp"==tableType){
        		document.premiummaster.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&productId="+pro+"&departmentId="+dept+"&proposal_No="+proposal_No+'&tableType='+tableType;
        	}else{
				document.premiummaster.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&productId="+pro+"&departmentId="+dept+"&proposal_No="+proposal_No+'&tableType='+tableType;
	     	}
	     	}
	     	else if(pro==3 || pro==5)
			{
			if("Temp"==tableType && pro==3){
        		document.premiummaster.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&layerno="+layerno+"&productId="+pro+"&proposal_No="+proposal_No+'&tableType='+tableType;
        	}else{
				document.premiummaster.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layerno+"&productId="+pro+"&proposal_No="+proposal_No+'&tableType='+tableType;
	     	}
	     	}
			document.premiummaster.submit();       
		}     
		function DN(contractno,transactionno,layerno,pro,dept) {  
			if(pro==1)
        	{
			document.premiummaster.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&productId="+pro+"&type=DN";
			}
			else if(pro==2)
			{
			document.premiummaster.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&productId="+pro+"&type=DN&departmentId="+dept;
	     	}
	     	else if(pro==3 || pro==5)
			{
			document.premiummaster.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno=<s:property value='layerno'/>&productId="+pro+"&type=DN";
	     	}
			document.premiummaster.submit();       
		}
		
		function deletedata(contractno,transactionno,layeNo,pro){
		var check =  confirm("Do you want to delete this record");
		if(check){
			document.premiummaster.action="${pageContext.request.contextPath}/deletePremiumPremiumMaster.action?contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layeNo;
			document.premiummaster.submit();  
			}
		}
		
		function BackFunction() {
			 var menuId=document.getElementById("menuId").value;  
  			 document.location='<%=request.getContextPath()%>/InitPortfolio.action?flag=C&menuId='+menuId;
		}
		
		function getDocList(proposalId,contNo,layerNo,tranNo,compName,brokeeName,type,proId){		
			document.premiummaster.action="${pageContext.request.contextPath}/documentUpload.action?type="+type+"&proposalno="+proposalId+"&contarctno="+contNo+"&layerNo="+layerNo+"&tranNo="+tranNo+"&companyName="+compName+"&brokerName="+brokeeName+"&moduleType=RDS&productId="+proId;
			document.premiummaster.submit();
		}  
		function newPremiumList(){
			document.premiummaster.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=fullpremium";
			document.premiummaster.submit();
		}
		function contractSearch(){
		document.premiummaster.action="${pageContext.request.contextPath}/contractSearchPremiumMaster.action?flag=search";
		document.premiummaster.submit();
		}
		function funPremiumMode(pro,contractno,layerno,proposal_No,dept)
      	{  
        if(pro==1)
        {
        document.premiummaster.action="${pageContext.request.contextPath}/premiumListFaculPremium.do?mode=premiumMaster&contNo="+contractno+"&productId="+pro+"&proposal_No="+proposal_No+"&departmentId="+dept;
        }
        else if(pro==2)
        {
        document.premiummaster.action="${pageContext.request.contextPath}/premiumListProportionPremium.do?mode=premiumMaster&contNo="+contractno+"&productId="+pro+"&proposal_No="+proposal_No+"&layerno="+layerno+"&departmentId="+dept;        
        }
        else if(pro==3 || pro==5)
        {
        document.premiummaster.action="${pageContext.request.contextPath}/premiumListXolPremium.do?mode=premiumMaster&contNo="+contractno+"&layerno="+layerno+"&productId="+pro+"&proposal_No="+proposal_No+"&departmentId="+dept;       
        }

        document.premiummaster.submit();       
      }  
      
getDepart('<s:property value="transactionType"/>');
     function getDepart(val){
     if(val=='2'){
     document.getElementById('deptID').style.display='inline';
     }else{
     document.getElementById('deptID').style.display='none';
     }
     if(val!='' &&val!=null ){
     document.getElementById('conId').style.display='inline';
     }
     else{
      document.getElementById('conId').style.display='none';
     }
      if(val=='3' || val=='5'){
      document.getElementById('layer').style.display='inline';
      }
      else{
      document.getElementById('layer').style.display='none';
      }
      }  

      
	</script>
</html>
