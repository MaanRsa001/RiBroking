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
		<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		<script type="text/javascript">
		jQuery(function ($) {
				try {
				var table = $('#gridTableMake').dataTable( {
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
		<title>My JSP 'CedingView.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%--  <style>
	  #tooltip{
    position:absolute;
    border:1px solid #333;
    background:#f7f5d1;
    padding:2px 5px;
    color:#333;
    display:none;
    }  
	 </style>--%>
	</head>
	<body>
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1"
					style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form name="portfolioview" id="portfolioview" action=""
								theme="simple">
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
														<s:text name="Proposal No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="proposalNoSearch" id="proposalNoSearch" cssClass="inputBox" />
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
												<s:if test='"3".equals(#session.mfrid) || "5".equals(#session.mfrid)'>
													
													<div class="textfield">
														<div class="text">
															<s:if test=' !"5".equals(#session.mfrid)'>
																<s:text name="Company Name" />
															</s:if>
															<s:else>
																<s:text name="Lead Retrocessionaire" />
															</s:else>
														</div>
														<div class="tbox txtB">
															<s:textfield name="companyNameSearch1" id="companyNameSearch1" cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Broker Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="brokerNameSearch1" id="brokerNameSearch1"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="label.class" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="departmentNameSearch1" id="departmentNameSearch1"
																cssClass="inputBox" />
														</div>
													</div>
												</s:if>
												<s:else>
													<div class="textfield">
														<div class="text">
															<s:if test=' !"4".equals(#session.mfrid)'>
																<s:text name="Company Name" />
															</s:if>
															<s:else>
																<s:text name="Lead Retrocessionaire" />
															</s:else>
														</div>
														<div class="tbox txtB">
															<s:textfield name="companyNameSearch" id="companyNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Broker Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="brokerNameSearch" id="brokerNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="label.class" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="departmentNameSearch" id="departmentNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
												</s:else>
												<s:if test='"1".equals(#session.mfrid) '>
													<div class="textfield">
														<div class="text">
															<s:text name="Insured Name" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="insuredNameSearch" id="insuredNameSearch"
																cssClass="inputBox" />
														</div>
													</div>
												</s:if>
												<s:if test='"1".equals(#session.mfrid) || "3".equals(#session.mfrid) || "5".equals(#session.mfrid)'>
													<div class="textfield">
														<div class="text">
															<s:text name="UW Year" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="uwYearSearch2" id="uwYearSearch2"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Underwriter" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="underwriterSearch" id="underwriterSearch"
																cssClass="inputBox" />
														</div>
													</div>

												</s:if>
												<s:else>
													<div class="textfield">
														<div class="text">
															<s:text name="UW Year" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="uwYearSearch3" id="uwYearSearch3"
																cssClass="inputBox" />
														</div>
													</div>
													<div class="textfield">
														<div class="text">
															<s:text name="Underwriter" />
														</div>
														<div class="tbox txtB">
															<s:textfield name="underwriterSearch1" id="underwriterSearch1"
																cssClass="inputBox" />
														</div>
													</div>
												</s:else>
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

								<s:if test='"".equals(display) || null==display'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:if test='"RD".equals(flag)'>
													<s:text name="Heading.RENEWALDUELIST" />
												</s:if>

												<s:else>
													<s:text name="Heading.CONTRACTSLIST" />
												</s:else>
												<s:if test='#session.MenuRights.indexOf("R")!=-1'>
													<span class="pullRight"> <input type="button"
															value="New Offer" class="btn btn-sm btn-success"
															onclick="AddNewOffer();" /> </span>
												</s:if>

											</div>
										</div>
										<s:if test='"2".equals(#session.mfrid) || "1".equals(#session.mfrid) || "4".equals(#session.mfrid)'>
											
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12">

														<table class="display responsive no-wrap"
															id="gridTableMake" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="text-align: center; vertical-align: middle;" class="no-sort" > <s:text name="SNo" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Proposal No" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Contract No" /> </th>
																	<s:if test='"4".equals(#session.mfrid)'>
																		<th style="text-align: center; vertical-align: middle;" > <s:text name="Lead Retrocessionaire" /> </th>
																	</s:if>
																	<s:else>
																		<th style="text-align: center; vertical-align: middle;" > <s:text name="Company Name" /> </th>
																	</s:else>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="Broker Name" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="label.class" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="Inception Date" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="Expiry Date" />
																	</th>
																	<s:if test=' "1".equals(#session.mfrid) ' >
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Insured Name" />
																		</th>
																	</s:if>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="UW Year" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="UnderWriter" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" >
																		<s:text name="Old Contract No" />
																	</th>
																	<s:if test='"2".equals(#session.mfrid)'>
																		<th style="text-align: center; vertical-align: middle;"><s:text name="Base" /></th>
																	</s:if>
																	<th style="text-align: center; vertical-align: middle;" width="140px">
																		<s:text name="Select Option" />
																	</th>
																	
															</thead>
															<tbody>
																<s:iterator value="portfolioList" var="list" status="stat">
																	<tr>
																		<td>
																			<s:property value="#stat.count" />
																		</td>
																		<td>
																			<s:property value="proposalId" />
																		</td>
																		<td>
																			<s:property value="proposalNo" />
																		</td>
																		<td>
																			<s:property value="ceding_Company_Name" />
																		</td>
																		<td>
																			<s:property value="brokerName" />
																		</td>
																		<td>
																			<s:property value="department_Name" />
																		</td>
																		<td>
																			<s:property value="inception_Date" />
																		</td>
																		<td>
																			<s:property value="expiry_Date" />
																		</td>
																		<s:if test=' "1".equals(#session.mfrid) '>
																			<td>
																				<s:property value="insuredName" />
																			</td>
																		</s:if>
																		<td>
																			<s:property value="uwYear" />
																		</td>
																		<td>
																			<s:property value="underwritter" />
																		</td>
																		<td>
																			<s:property value="old_Contract" />
																		</td>
																		<s:if test='"2".equals(#session.mfrid)'>
																		<td><s:property value="baseLayer" /></td>
																		</s:if>
																		<td style="text-align: center; vertical-align: middle;">
																			<div><s:select list="buttonSelectionList" name="buttonVal[%{#stat.count-1}]" id="buttonVal%{#stat.count-1}" cssClass=" inputBoxS" headerKey="" headerValue="--Select--"  listKey="TYPE" listValue="DETAIL_NAME" cssStyle="width:65%;float:left;" />
																			 <span class="pull-right"><input type="button"  value="Go"  size="2" class="btn btn-xs btn-info"  style="cursor: pointer;float:left;" onclick="ButtonAction('<s:property value="proposalNo" />','<s:property value="cedding_company_id" />','<s:property value="proposalId" />','<s:property value="baseLayer" />','<s:property value="departmentId" />','<s:property value="flag" />','<s:property value="amendId" />','<s:property value="layerNo" />','<s:property value="inception_Date" />','<s:property value="ceding_Company_Name" />','<s:property value="brokerName" />','<s:property value="flag" />','<s:property value="#stat.count-1" />')" /></span></div>
																		</td>
																	
																	</tr>
																</s:iterator>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</s:if>
										<s:else>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12">
														<table class="display responsive no-wrap"
															id="gridTableMake" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="text-align: center; vertical-align: middle;"
																		class="no-sort">
																		<s:text name="S.No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Proposal No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Contract No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Layer No" />
																	</th>
																	<s:if test='"5".equals(#session.mfrid)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Lead Retrocessionaire" />
																		</th>
																	</s:if>
																	<s:else>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Company Name" />
																		</th>
																	</s:else>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Broker Name" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="label.class" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="InceptionDate" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="ExpiryDate" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UW Year" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UnderWriter" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Old Contract No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Base" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;" width="150px">
																		<s:text name="Select Option" />
																	</th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="portfolioList" var="list"
																	status="stat">
																	<tr>
																		<td>
																			<s:property value="#stat.count" />
																		</td>
																		<td>
																			<s:property value="proposalId" />
																		</td>
																		<td>
																			<s:property value="proposalNo" />
																		</td>
																		<td>
																			<s:property value="layerNo" />
																		</td>
																		<td>
																			<s:property value="ceding_Company_Name" />
																		</td>
																		<td>
																			<s:property value="brokerName" />
																		</td>
																		<td>
																			<s:property value="department_Name" />
																		</td>
																		<td>
																			<s:property value="inception_Date" />
																		</td>
																		<td>
																			<s:property value="expiry_Date" />
																		</td>
																		<td>
																			<s:property value="uwYear" />
																		</td>
																		<td>
																			<s:property value="underwritter" />
																		</td>
																		<td>
																			<s:property value="old_Contract" />
																		</td>
																		<td>
																			<s:property value="baseLayer" />
																		</td>
																		<td style="text-align: center; vertical-align: middle;">
																			<div><s:select list="buttonSelectionList" name="buttonVal[%{#stat.count-1}]" id="buttonVal%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="--Select--"  listKey="TYPE" listValue="DETAIL_NAME" cssStyle="width:70%;float:left;"/>
																			 <span class="pull-right"><input type="button"  value="Go"   class="btn btn-xs btn-info"  style="cursor: pointer;float:left;" onclick="ButtonAction('<s:property value="proposalNo" />','<s:property value="cedding_company_id" />','<s:property value="proposalId" />','<s:property value="baseLayer" />','<s:property value="departmentId" />','<s:property value="flag" />','<s:property value="amendId" />','<s:property value="layerNo" />','<s:property value="inception_Date" />','<s:property value="ceding_Company_Name" />','<s:property value="brokerName" />','<s:property value="flag" />','<s:property value="#stat.count-1" />')" /></span></div>
																		</td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</s:else>
									</div>
								</s:if>
								<s:else>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:if test='"History".equals(display)'>
													<s:text name="Heading.HISLIST" />
												</s:if>
												<s:else>
													<s:text name="Heading.CONTRACTSLIST" />
												</s:else>
											</div>
										</div>
										<s:if test='"2".equals(#session.mfrid) || "1".equals(#session.mfrid) || "4".equals(#session.mfrid)'>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12">
														<table class="display responsive no-wrap"
															id="gridTableMake" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="text-align: center; vertical-align: middle;"
																		class="no-sort">
																		<s:text name="S.No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Proposal No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Contract No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Endt No" />
																	</th>
																	<s:if test='"History".equals(display)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Endt Type" />
																		</th>
																	</s:if>
																	<s:if test='"4".equals(#session.mfrid)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Lead Retrocessionaire" />
																		</th>
																	</s:if>
																	<s:else>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Company Name" />
																		</th>
																	</s:else>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Broker Name" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="label.class" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="InceptionDate" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="ExpiryDate" />
																	</th>
																	<s:if test=' "1".equals(#session.mfrid) '>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Insured Name" />
																		</th>
																	</s:if>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UW Year" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UnderWriter" />
																	</th>
																	<s:if test='!"History".equals(display)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Old Contract No" />
																		</th>
																	</s:if>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="View" />
																	</th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="portfolioList" var="list"
																	status="stat">
																	<tr>
																		<td>
																			<s:property value="#stat.count" />
																		</td>
																		<td>
																			<s:property value="proposalId" />
																		</td>
																		<td>
																			<s:property value="proposalNo" />
																		</td>
																		<td>
																			<s:property value="amendId" />
																		</td>
																		<s:if test='"History".equals(display)'>
																			<td>
																				<s:property value="endorsmentTypeName" />
																			</td>
																		</s:if>
																		<td>
																			<s:property value="ceding_Company_Name" />
																		</td>
																		<td>
																			<s:property value="brokerName" />
																		</td>
																		<td>
																			<s:property value="department_Name" />
																		</td>
																		<td>
																			<s:property value="inception_Date" />
																		</td>
																		<td>
																			<s:property value="expiry_Date" />
																		</td>
																		<s:if test=' "1".equals(#session.mfrid) '>
																			<td>
																				<s:property value="insuredName" />
																			</td>
																		</s:if>
																		<td>
																			<s:property value="uwYear" />
																		</td>
																		<td>
																			<s:property value="underwritter" />
																		</td>
																		<s:if test='!"History".equals(display)'>
																			<td>
																				<s:property value="old_Contract" />
																			</td>
																		</s:if>
																		<td>
																			<s:if test='#session.MenuRights.indexOf("V")!=-1'>
																				<input type="button" class="btn btn-sm btn-info"
																					value="View" style="cursor: pointer;"
																					onclick="funViewMode('<s:property value="proposalId" />','<s:property value="amendId" />','<s:property value="flag" />','<s:property value="baseLayer" />')" />
																			</s:if>
																		</td>
																		

																	</tr>
																</s:iterator>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</s:if>
										<s:else>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12">
														<table class="display responsive no-wrap"
															id="gridTableMake" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="text-align: center; vertical-align: middle;"
																		class="no-sort">
																		<s:text name="S.No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Proposal No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Contract No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Layer No" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Endt No" />
																	</th>
																	<s:if test='"History".equals(display)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Endt Type" />
																		</th>
																	</s:if>
																	<s:if test='"5".equals(#session.mfrid)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Lead Retrocessionaire" />
																		</th>
																	</s:if>
																	<s:else>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Company Name" />
																		</th>
																	</s:else>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="Broker Name" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="label.class" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="InceptionDate" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="ExpiryDate" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UW Year" />
																	</th>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="UnderWriter" />
																	</th>
																	<s:if test='!"History".equals(display)'>
																		<th
																			style="text-align: center; vertical-align: middle;">
																			<s:text name="Old Contract No" />
																		</th>
																	</s:if>
																	<th style="text-align: center; vertical-align: middle;">
																		<s:text name="View" />
																	</th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="portfolioList" var="list"
																	status="stat">
																	<tr>
																		<td>
																			<s:property value="#stat.count" />
																		</td>
																		<td>
																			<s:property value="proposalId" />
																		</td>
																		<td>
																			<s:property value="proposalNo" />
																		</td>
																		<td>
																			<s:property value="layerNo" />
																		</td>
																		<td>
																			<s:property value="amendId" />
																		</td>
																		<s:if test='"History".equals(display)'>
																			<td>
																				<s:property value="endorsmentTypeName" />
																			</td>
																		</s:if>
																		<td>
																			<s:property value="ceding_Company_Name" />
																		</td>
																		<td>
																			<s:property value="brokerName" />
																		</td>
																		<td>
																			<s:property value="department_Name" />
																		</td>
																		<td>
																			<s:property value="inception_Date" />
																		</td>
																		<td>
																			<s:property value="expiry_Date" />
																		</td>
																		<td>
																			<s:property value="uwYear" />
																		</td>
																		<td>
																			<s:property value="underwritter" />
																		</td>
																		<s:if test='!"History".equals(display)'>
																			<td>
																				<s:property value="old_Contract" />
																			</td>
																		</s:if>
																		<td>
																			<s:if test='#session.MenuRights.indexOf("V")!=-1'>
																				<input type="button" class="btn btn-sm btn-info"
																					value="View" style="cursor: pointer;"
																					onclick="funViewMode('<s:property value="proposalId" />','<s:property value="amendId" />','<s:property value="flag" />','<s:property value="baseLayer" />')" />
																			</s:if>
																		</td>
																		
																	</tr>
																</s:iterator>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</s:else>
										<div align="center">
											<input type="submit" value="Back"
												class="btn btn-sm btn-danger" onclick="getBack()" />
										</div>
									</div>
								</s:else>
								<s:hidden name="pro" id="pro" value="%{#session.mfrid}" />
								<s:hidden name="contractno" id="contractno" />
								<s:hidden name="CustomerId" id="CustomerId" />
								<s:hidden name="proposal_no" id="proposal_no" />
								<s:hidden name="proposal_No" id="proposal_No" />
								<s:hidden name="proposalNo" id="proposalNo" />
								<s:hidden name="baseLayer" id="baseLayer" />
								<s:hidden name="layerNo" id="layerNo" />
								<s:hidden name="layerLayerNo" id="layerLayerNo" />
								<s:hidden name="renewal_contract_no" id="renewal_contract_no" />
								<s:hidden name="layer" id="layer" />
								<s:hidden name="mode" id="mode" />
								<s:hidden name="flag" id="flag" />
								<s:hidden name="menuId" id="menuId" />
								<s:hidden name="manufactureID" id="manufactureID" />
								<s:hidden name="moduleType" value="RDS" />
								<s:hidden name="companyName" id="companyName" />
								<s:hidden name="brokerName" id="brokerName" />
								<s:hidden name="display" id="display" />
								<s:hidden name="amendId" id="amendId" />
								<s:hidden name="proposalno" id="proposalno" />
								<s:hidden name="contarctno" id="contarctno" />
								<s:hidden name="expiry_Date" id="expiry_Date" />
								<s:hidden name="type" id="type" />
								<s:hidden name="reMode" id="reMode" />
								<s:hidden name="renewalEditMode" id="renewalEditMode" />
								
								<s:hidden name="multiuserError" id="multiuserError" />
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
function myFunction(id){
	var $rows = $('#gridTableMake tr');
	//var $rows = $('#gridTableMake').DataTable();
	//var data = table.rows().data();
$('#input'+id).keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

    $rows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});
}
 function searchdetails()
 {
  document.portfolioview.action="${pageContext.request.contextPath}/PortfolioDispatchIntAction.do?method=SearchView";
  document.portfolioview.submit();
 }
 
     function funEditMode(contractno,ceddingcompanyid,proposalno,baseLayer,deptId)
     {
		//document.getElementById("searchType").value=searchType;
     	//document.getElementById("searchValue").value=searchValue;
       document.getElementById("contractno").value=contractno;
	   document.getElementById("CustomerId").value=ceddingcompanyid;
	   document.getElementById("proposal_no").value=proposalno;
	   document.getElementById("proposalNo").value=proposalno;
	   document.getElementById("baseLayer").value=baseLayer;
	   document.getElementById("mode").value='endorsment';
	   if(document.portfolioview.pro.value==2)
       {
	    document.portfolioview.action="EditModeRiskDetails.action?endtMode=endorsment&departmentId="+deptId;
	   }
	   else if(document.portfolioview.pro.value==4)
	   {
	   document.portfolioview.action="EditModeRetro.action?endtMode=endorsment";
	   }
	   else if(document.portfolioview.pro.value==3 ||document.portfolioview.pro.value==5)
	   {
	   	document.portfolioview.action="EditModeXol.action?endtMode=endorsment";
	   }
	   else if(document.portfolioview.pro.value==1)
	   {
	    document.portfolioview.action="${pageContext.request.contextPath}/EditMethodFacultative.action?endtMode=endorsment";
	   }
       
	    document.portfolioview.submit();
     }
      
       function funViewMode(proposalno,amendId,flag,baseLayer)
      {
      		document.getElementById("amendId").value=amendId;
	        document.getElementById("proposal_no").value=proposalno;
	        document.getElementById("proposalNo").value=proposalno;
	        document.getElementById("flag").value=flag;
	        document.getElementById("baseLayer").value=baseLayer;
    	if(document.portfolioview.pro.value==2 )
	      {	      	
	        document.portfolioview.action="ViewMethodRiskDetails";
	        document.portfolioview.submit();
	     }
	     else if(document.portfolioview.pro.value==3 ||document.portfolioview.pro.value==5)
	     {
	     	document.portfolioview.action="ViewMethodXol";
	        document.portfolioview.submit();
	    }
	    else if(document.portfolioview.pro.value==4)
	    {
	    	document.portfolioview.action="ViewMethodRetro";
	        document.portfolioview.submit();
	    }
	     else
	     {
	        document.portfolioview.action="ViewMethodFacultative.action";
	        document.portfolioview.submit();
	   	}
      }
      function getHistory(proposalno,flag)
      {
      		document.getElementById("display").value='History';
	        document.getElementById("proposalNo").value=proposalno;
	        document.getElementById("flag").value=flag;
	        document.portfolioview.action="${pageContext.request.contextPath}/getHistoryPortfolio.action";
	        document.portfolioview.submit();
      }
     function getBack()
     {
      document.getElementById("display").value='';
      document.portfolioview.action="${pageContext.request.contextPath}/InitPortfolio.action";
      document.portfolioview.submit();
     }
      function funPremiumMode(contractno,layerno,deptId,proposalno)
      {  
     // alert(contractno+"contno");
     
        var pro = document.getElementById("pro").value;
        if(pro==1)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/premiumListFaculPremium.do?contNo="+contractno+"&departmentId="+deptId;
        }
        else if(pro==2)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/premiumListProportionPremium.do?contNo="+contractno+"&departmentId="+deptId;        
        }
        else if(pro==3 || pro==5 )
        {
        document.portfolioview.action="${pageContext.request.contextPath}/premiumListXolPremium.do?contNo="+contractno+"&layerno="+layerno+"&departmentId="+deptId;        
        }
		document.getElementById('proposal_No').value = proposalno;
        document.portfolioview.submit();       
      }      
      function funCliamMode(contractno,layerno,proposalno,inception_Date ,deptId)
      {
     // alert(inception_Date)
      document.getElementById("layerNo").value=layerno;
      document.getElementById('contarctno').value = contractno;
      document.getElementById('proposal_No').value = proposalno;
      document.getElementById("proposalNo").value=proposalno;
      var pro = document.getElementById("pro").value;
      if(pro==1 || pro==2||pro==3){
      document.portfolioview.action="${pageContext.request.contextPath}/claimInitClaim.do?inception_Date="+inception_Date+"&departmentId="+deptId;
      document.portfolioview.submit();
      }
      else if(pro==4||pro==5){
      document.portfolioview.action="${pageContext.request.contextPath}/claimInitRetroclm.do?inception_Date="+inception_Date+"&departmentId="+deptId;
      document.portfolioview.submit();
      }
      }
       function funIEMode(contractno,layerno,proposalno,inception_Date ,deptId)
      {
     // alert(inception_Date)
      document.getElementById("layerNo").value=layerno;
      document.getElementById('contarctno').value = contractno;
      document.getElementById('proposal_No').value = proposalno;
      document.getElementById("proposalNo").value=proposalno;
     // document.getElementById("inception_Date").value=inception_Date;
      document.portfolioview.action="${pageContext.request.contextPath}/ieModuleXol.do?inception_Date="+inception_Date+"&departmentId="+deptId;
      document.portfolioview.submit();
      }
            
      function funAddLayer(contarctno,ceddingcompanyid,proposalno)
      {
      document.getElementById("contarctno").value=contarctno;
      document.getElementById("CustomerId").value=ceddingcompanyid;
      document.getElementById("proposalno").value=proposalno;
      document.portfolioview.action="${pageContext.request.contextPath}/CedingCompanyAction.do?method=FlowEditContarctMode";
      document.portfolioview.submit();
      }      
      function funLayerMode(ProposalNo,ceddingcompanyId,ContractNo,layerNo)
      {
      document.getElementById("renewal_contract_no").value=ContractNo;
      document.getElementById("proposal_no").value=ProposalNo;
      document.getElementById("proposalNo").value=proposalno;  
      document.getElementById("CustomerId").value=ceddingcompanyId;
      document.getElementById("layerNo").value="layer";
      document.getElementById("layerLayerNo").value=layerNo;
      document.portfolioview.action="LayerEditModeXol.action"
      document.portfolioview.submit();
      }
      function funClassMode(ProposalNo,ceddingcompanyId,ContractNo)
      {
      document.getElementById("renewal_contract_no").value=ContractNo;
      document.getElementById("proposal_no").value=ProposalNo;
      document.getElementById("proposalNo").value=proposalno;  
      document.getElementById("CustomerId").value=ceddingcompanyId;
      document.getElementById("layerNo").value="layer";
      document.portfolioview.action="ClassEditModeRiskDetails.action"
      document.portfolioview.submit();
      }
      
      function funStatisticMode(proposalNo,ContractNo,departmentId,layerNo,inception_Date){
      document.getElementById("proposalNo").value=proposalNo;
       document.getElementById("proposal_No").value=proposalNo;
       document.getElementById("layerNo").value=layerNo;
      document.portfolioview.action="statisticsDownStatistics.action?contractNo="+ContractNo+"&departmentId="+departmentId+"&inception_Date="+inception_Date;
      document.portfolioview.submit();
      }
      function funRenewalMode(proposalNo,ContractNo)
      {
       document.getElementById("proposal_no").value=proposalNo;
       document.getElementById("proposalNo").value=proposalno;
       document.getElementById("renewal_contract_no").value=ContractNo;
       document.getElementById("expiry_Date").value=expiry_Date;
       //document.getElementById("mode").value=
     	if(document.portfolioview.pro.value==2)
      	{
	    document.portfolioview.action=" ${pageContext.request.contextPath}/EditModeRiskDetails";
	    document.getElementById("mode").value="Renewal";
	    document.getElementById("reMode").value="Renewal";
	    document.getElementById("renewalEditMode").value="Renewal";
	    
	    }
	    else if(document.portfolioview.pro.value==3 ||document.portfolioview.pro.value==5){
	    document.portfolioview.action=" ${pageContext.request.contextPath}/EditModeXol";
	    document.getElementById("mode").value="Renewal";
	    document.getElementById("reMode").value="Renewal";
	    document.getElementById("renewalEditMode").value="Renewal";
	    }
	    else if(document.portfolioview.pro.value==4){
	    document.portfolioview.action=" ${pageContext.request.contextPath}/EditModeRetro";
	    document.getElementById("mode").value="Renewal";
	    document.getElementById("reMode").value="Renewal";
	    document.getElementById("renewalEditMode").value="Renewal";
	    }
	    else if(document.portfolioview.pro.value==1)
	    {
	    document.portfolioview.action="${pageContext.request.contextPath}/EditMethodFacultative";
	    document.getElementById("mode").value="Renewal";
	    document.getElementById("reMode").value="Renewal";
	    document.getElementById("renewalEditMode").value="Renewal";
	    }
	    document.portfolioview.submit();
      }
    function getSearchBy()
    {
    	document.getElementById("searchValue").value='';
    }  
    
    function getSearch1(){
   		var flag=document.getElementById("flag").value;
		var menuId=document.getElementById("menuId").value;
		//document.portfolioview.action="InitPortfolio.action?flag="+flag+"&menuId="+menuId;
		document.portfolioview.action="InitPortfolio.action?menuId="+menuId;
		//document.portfolioview.action="InitPortfolio.action;
		document.portfolioview.submit();
    }
    function getSearch()
	{ 
		var searchType=document.getElementById("searchType").value;
		var searchValue=document.getElementById("searchValue").value;
		var flag="<bean:write property='flag' name='PortfolioForm'/>";
			if(searchType=="0")
			{
				document.getElementById("searchValue").value="";
			}
			var xmlHttp;
		    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
				var xmlHttp = new XMLHttpRequest();
		    }else if (window.ActiveXObject) { // IE
				var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		   	}
		    xmlHttp.open('POST', "${pageContext.request.contextPath}/InitPortfolio.action?searchType="+searchType+"&searchValue="+searchValue+"&flag="+flag+"&reqFrom=ajax", true);
		    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xmlHttp.onreadystatechange = function() 
			{
				if (xmlHttp.readyState == 4) 
				{
						document.getElementById("displayTag").innerHTML=xmlHttp.responseText;
				}
		   }
			xmlHttp.send(null);
	}
	function getDocList(proposalId,proposalNo,layerNo,compName,brokeeName,type){
		document.getElementById("proposalno").value=proposalId;
		document.getElementById("contarctno").value=proposalNo;
		document.getElementById("layerNo").value=layerNo;
		document.getElementById("companyName").value=compName;
		document.getElementById("brokerName").value=brokeeName;
		document.portfolioview.type.value=type;
  	    document.portfolioview.action="${pageContext.request.contextPath}/documentUpload.action";
	    document.portfolioview.submit();
	}
	function AddNewOffer() {
	var pro = document.getElementById("pro").value;
		if(pro==1)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/InitMethodFacultative.action";
        }
        if(pro==2)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/InitRiskDetails.action";
        }
        if(pro==3 || pro==5)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/InitXol.action";
        }
        if(pro==4)
        {
        document.portfolioview.action="${pageContext.request.contextPath}/InitRetro.action";
        }
		document.portfolioview.submit();
	}
	function showFullData(){
		var menuId=document.getElementById("menuId").value;
		document.portfolioview.action="${pageContext.request.contextPath}/InitPortfolio.action?flag=C&mode=first&type=fullData&menuId="+menuId;
		document.portfolioview.submit();
	}
	editMode();
	function editMode(){
	var val =document.getElementById("multiuserError").value;
		if('error'==val){
		document.getElementById("multiuserError").value ='';
		alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
		}
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
$('#proposalNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
} );
$('#contractNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(2).search(this.value).draw();
} );
$('#companyNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(3).search(this.value).draw();
} );
$('#brokerNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(4).search(this.value).draw();
} );
$('#departmentNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
} );
$('#insuredNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8).search(this.value).draw();
} );
$('#underwriterSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(10).search(this.value).draw();
} );
$('#underwriterSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#companyNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(4).search(this.value).draw();
} );
$('#brokerNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
} );
$('#departmentNameSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(6).search(this.value).draw();
} );
$('#uwYearSearch2').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#uwYearSearch3').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8).search(this.value).draw();
} );

function ButtonAction(contractno,ceddingcompanyid,proposalno,baseLayer,deptId,flag,amendId,layerNo,inception_Date,ceding_Company_Name,brokerName,flag,row){
var sel = document.getElementById("buttonVal"+row).value;
if("E"==sel){
funEditMode(contractno,ceddingcompanyid,proposalno,baseLayer,deptId);
}
else if("V"==sel){
funViewMode(proposalno,amendId,flag,baseLayer);
}
else if("H"==sel){
getHistory(proposalno,flag);
}
else if("P"==sel){
funPremiumMode(contractno,layerNo,deptId,proposalno);
}
else if("C"==sel){
funCliamMode(contractno,layerNo,proposalno,inception_Date,deptId);
}
else if("S"==sel){
funStatisticMode(proposalno,contractno,deptId,layerNo,inception_Date);
}
else if("CS"==sel){
funClassMode(proposalno,ceddingcompanyid,contractno);
}
else if("R"==sel){
funRenewalMode(proposalno,contractno);
}
else if("IE"==sel){
funIEMode(contractno,layerNo,proposalno,inception_Date,deptId);
}
else if("L"==sel){
funLayerMode(proposalno,ceddingcompanyid,contractno,layerNo);
}
else if("CP"==sel){
funDupliacteCopyMode(proposalno);
}
else if("D"==sel){
var val = "contract";
if("C"!=flag){
val="RD";
}
getDocList(proposalno,contractno,layerNo,ceding_Company_Name,brokerName,val);
}

}
function funDupliacteCopyMode(proposalno){
	  document.getElementById("proposal_no").value=proposalno;
      document.getElementById("proposalNo").value=proposalno;  
	  document.portfolioview.action="${pageContext.request.contextPath}/DuplicateCopyPortfolio.action";
	  document.portfolioview.submit();
}

function funSearchMode(mode){
var flag = document.getElementById("flag").value;
	if(flag!="C"&&flag!="RD"){
	  document.portfolioview.action="${pageContext.request.contextPath}/commonListPortfolio.action?SearchType="+mode;
	  document.portfolioview.submit();
	  }
	  else{
	  document.portfolioview.action="${pageContext.request.contextPath}/InitPortfolio.action?SearchType="+mode;
	  document.portfolioview.submit();
	 }
}
</script>
</html>
