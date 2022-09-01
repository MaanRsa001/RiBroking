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
#tooltip {
	position: absolute;
	border: 1px solid #333;
	background: #f7f5d1;
	padding: 2px 5px;
	color: #333;
	display: none;
}
</style>
<script type="text/javascript">
	 $(function() {
	    $( "#dateOfLossSearch" ).datepicker({
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
				<div class="table1"
					style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form name="taclclaim" id="taclclaim" action="" theme="simple">
								<div class="tablerow">
									<span style="color: red;"><s:actionerror /> </span>
								</div>
								
								<s:if test='BusinessMode.equals("new")'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:text name="CLAIM DETAILS" />
												<span class="pullRight"> <input type="button"
														value="Contract Identifier" class="btn btn-sm btn-success"
														onclick="contractSearch();" /> </span>
											</div>
										</div>
										<div class="panel-body">
											<div class="textfield">
												<div class="text">
													<s:text name="label.productId" />
												</div>
												<div class="tbox">
													<s:select name="productId" id="productId"
														list="productIdList" listKey="TMAS_PRODUCT_ID"
														listValue="TMAS_PRODUCT_NAME" headerKey=""
														cssClass="inputBoxS" headerValue="---Select---"
														onchange="getDepart(this.value);" />
												</div>
											</div>
											<%--<div class="textfield" id="pro" style="display: none;">
									<div class="text">
										<s:text name="Proposal No" />
									</div>
									<div class="tbox" >
										<s:textfield cssClass="inputBox" name="proposal_No" id="proposal_No"  />												
									</div>
								</div> --%>
											<div class="textfield" id="con" style="display: none;">
												<div class="text">
													<s:text name="label.contractNo" />
												</div>
												<div class="tbox">

													<s:textfield cssClass="inputBox" name="contractNo"
														id="contractNo" maxlength="50" />
												</div>
											</div>
											<div class="textfield" id="layer" style="display: none;">
												<div class="text">
													<s:text name="label.layerNo" />
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="layerNo"
														id="layerNo" />
												</div>
											</div>

											<div class="textfield" id="deptID" style="display: none;">
												<div class="text">
													<s:text name="Premium.SubClass" />
												</div>
												<div class="tbox" id="exchRate">
													<s:select name="departmentId" id="departmentId"
														list="predepartIdlist" listKey="TMAS_DEPARTMENT_ID"
														listValue="TMAS_DEPARTMENT_NAME" headerKey="N"
														cssClass="inputBoxS" headerValue="---Select---" />
												</div>
											</div>

										</div>
									</div>
									<div align="center">
										<input type="button" value="Back"
											class="btn btn-sm btn-danger" onclick="getBack()" />
										<input type="button" value="Submit"
											class="btn btn-sm btn-success" onclick="NewCliam()" />
									</div>
								</s:if>
								<s:elseif test='flag.equals("search")'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:text name="Contract Identifier" />
											</div>
										</div>
										<div class="panel-body">
											<div class="textfield">
												<div class="text">
													<s:text name="label.productId" />
												</div>
												<div class="tbox">
													<s:select name="productId" id="productId"
														list="productIdList" listKey="TMAS_PRODUCT_ID"
														listValue="TMAS_PRODUCT_NAME" headerKey=""
														cssClass="inputBoxS" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedingcompany" />
												</div>
												<div class="tbox">
													<s:select name="ceding_Company_Code"
														id="ceding_Company_Code" list="cedingCompanyList"
														listKey="CUSTOMER_ID" listValue="NAME" headerKey=""
														cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.broker" />
												</div>
												<div class="tbox">
													<s:select name="broker_code" id="broker_code"
														list="brokerList" listKey="CUSTOMER_ID" listValue="NAME"
														headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.underwritingyear" />
												</div>
												<div class="tbox">
													<s:select name="underwritingYear" id="underwritingYear"
														list="yearList" listKey="YEAR" listValue="YEAR"
														headerKey="" cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.dept" />
												</div>
												<div class="tbox" id="exchRate">
													<s:select name="deptId" id="deptId" list="departIdlist"
														listKey="TMAS_DEPARTMENT_ID"
														listValue="TMAS_DEPARTMENT_NAME" headerKey=""
														cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
										</div>
									</div>
									<div align="center">
										<input type="button" value="Back"
											class="btn btn-sm btn-danger" onclick="getBack()" />
										<input type="button" value="Submit"
											class="btn btn-sm btn-success"
											onclick="getSubmitContractident()" />
									</div>
								</s:elseif>
								<s:elseif test='"contractidentifier".equals(flag)'>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:text name="Contract Identifier" />
											</div>
										</div>
										<div class="panel-body">
											<div class="textfield">
												<div class="text">
													<s:text name="label.productId" />
												</div>
												<div class="tbox">
													<s:select name="productId" id="productId"
														list="productIdList" listKey="TMAS_PRODUCT_ID"
														listValue="TMAS_PRODUCT_NAME" headerKey=""
														cssClass="inputBoxS" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedingcompany" />
												</div>
												<div class="tbox">
													<s:select name="ceding_Company_Code"
														id="ceding_Company_Code" list="cedingCompanyList"
														listKey="CUSTOMER_ID" listValue="NAME" headerKey="N"
														cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.broker" />
												</div>
												<div class="tbox">
													<s:select name="broker_code" id="broker_code"
														list="brokerList" listKey="CUSTOMER_ID" listValue="NAME"
														headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.underwritingyear" />
												</div>
												<div class="tbox">
													<s:select name="underwritingYear" id="underwritingYear"
														list="yearList" listKey="YEAR" listValue="YEAR"
														headerKey="N" cssClass="inputBoxS" headerValue="---ALL---" />

												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.dept" />
												</div>
												<div class="tbox" id="exchRate">
													<s:select name="deptId" id="deptId" list="departIdlist"
														listKey="TMAS_DEPARTMENT_ID"
														listValue="TMAS_DEPARTMENT_NAME" headerKey="N"
														cssClass="inputBoxS" headerValue="---ALL---" />
												</div>
											</div>
										</div>
									</div>
									<div align="center">
										<input type="button" value="Back"
											class="btn btn-sm btn-danger" onclick="getBack()" />
										<input type="button" value="Submit"
											class="btn btn-sm btn-success"
											onclick="getSubmitContractident()" />
									</div>
									<br class="clear" />
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:text name="Contract Identifier Details" />
											</div>
										</div>
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
																	<s:text name="Company Name" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Broker Name" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Dept Name" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Inception Date" />
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
																	<s:text name="Claim" />
																</th>
															</tr>
														</thead>
														<tbody>
															<s:iterator value="contractIdentifierList" var="list"
																status="stat">
																<tr>
																	<td>
																		<s:property value="#stat.count" />
																	</td>
																	<td>
																		<s:property value="proposal_No" />
																	</td>
																	<td>
																		<s:property value="contractNo" />
																	</td>
																	<td>
																		<s:property value="layerNo" />
																	</td>
																	<td>
																		<s:property value="ceding_company_Name" />
																	</td>
																	<td>
																		<s:property value="broker_Name" />
																	</td>
																	<td>
																		<s:property value="deptId" />
																	</td>
																	<td>
																		<s:property value="inception_Date" />
																	</td>
																	<td>
																		<s:property value="expiryDate" />
																	</td>
																	<td>
																		<s:property value="underwritingYear" />
																	</td>
																	<td>
																		<s:property value="underwriter" />
																	</td>
																	<td>
																		<s:property value="old_Contract" />
																	</td>
																	<td>
																		<input type="button" class="btn btn-sm btn-info"
																			value="C" style="cursor: pointer;"
																			onclick="funClaimMode('<s:property value="contractNo" />','<s:property value="proposal_No" />','<s:property value="layerNo" />','<s:property value="inception_Date" />','<s:property value="productId" />')" />
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
														<s:text name="Claim No" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="claimNoSearch" id="claimNoSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Date Of Loss" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="dateOfLossSearch" id="dateOfLossSearch" cssClass="inputBox" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="Status Of Caim" />
													</div>
													<div class="tbox txtB">
														<s:textfield name="claimStatusSearch" id="claimStatusSearch" cssClass="inputBox" />
													</div>
												</div>
												<br class="clear"/>
												<div  align="center">
														<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
														<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;" onclick="funSearchMode('')" />							
														<input type="button" value="Show Full Data" class="btn btn-sm btn-warning" style="cursor: pointer;" onclick="newClaimList();" />	
														
												</div>
											</div>
										</div>
									</div>
								</div>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												<s:text name="CLAIM DETAILS" />
												<span class="pullRight"> 
												         <input type="button" value="Contract Identifier" class="btn btn-sm btn-warning" onclick="contractSearch();" />
														 &nbsp; <input type="button" value="New Claim" class="btn btn-sm btn-success" onclick="NewCliamSearch();" />
												</span>
											</div>
										</div>

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
																	<s:text name="Contract No" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Layer No" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Claim No" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Type" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Inception Date" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Expiry Date" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Date Of Loss" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Claim Registered Date" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Ceding Company" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Broker" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Status Of Claim" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Edit" />
																</th>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="View" />
																</th>
																<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="Delete" /></th>--%>
																<th style="text-align: center; vertical-align: middle;">
																	<s:text name="Document" />
																</th>
															</tr>
														</thead>
														<tbody>
															<s:iterator value="Claimlist" var="list" status="stat">
																<tr>
																	<td>
																		<s:property value="#stat.count" />
																	</td>
																	<td>
																		<s:property value="policy_Contract_No" />
																	</td>
																	<td>
																		<s:property value="layerNo" />
																	</td>
																	<td>
																		<s:property value="claim_No" />
																	</td>
																	<td>
																		<s:property value="productName" />
																	</td>
																	<td>
																		<s:property value="inception_Date" />
																	</td>
																	<td>
																		<s:property value="expiryDate" />
																	</td>
																	<td>
																		<s:property value="date_of_Loss" />
																	</td>
																	<td>
																		<s:property value="created_Date" />
																	</td>
																	<td>
																		<s:property value="ceding_company_Name" />
																	</td>
																	<td>
																		<s:property value="broker_Name" />
																	</td>
																	<td>
																		<s:property value="status_of_claim" />
																	</td>
																	<td>
																		<s:if
																			test='#session.MenuRights.indexOf("CE")!=-1'>
																			<a title="Edit" style="cursor: pointer;"
																				onClick="EditMode('<s:property value="policy_Contract_No" />','<s:property value="claim_No" />','<s:property value="proposal_No" />','<s:property value="layerNo" />','<s:property value="productId" />','<s:property value="departmentId" />')">Edit
																			</a>
																		</s:if>
																	</td>
																	<td>
																		<s:if test='#session.MenuRights.indexOf("CV")!=-1'>
																			<a title="View" style="cursor: pointer;"
																				onclick="ViewMode('<s:property value="policy_Contract_No" />','<s:property value="claim_No" />','<s:property value="proposal_No" />','<s:property value="layerNo" />','<s:property value="productId" />','<s:property value="departmentId" />')">View</a>
																		</s:if>
																	</td>
																	<%-- 	<td>
											<s:if test='"Y".equalsIgnoreCase(#list.deleteStatus)' >
											 		<center ><a title="Delete" style="cursor: pointer;" onClick="deletedata('<s:property value="policy_Contract_No" />','<s:property value="claim_No" />')">Delete</a></center>
									       	 	</s:if>
												</td>--%>
																	<td>
																		<a href="#" class="" title="Document"
																			onclick="getDocList('<s:property value="proposal_No"/>','<s:property value="policy_Contract_No" />','<s:property value="layerNo"/>','','<s:property value="ceding_company_Name"/>','<s:property value="broker_Name"/>','<s:property value="productId" />','claim')">
																			<img border='0'
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
										<div align="center">
											<!--  <input type="submit"  value="Back"   class="btn btn-sm btn-danger" onclick="getBack()" />-->
										</div>
									</div>
								</s:else>
								<s:hidden name="InsDate" />
								<s:hidden name="claimDisplay" id="claimDisplay" />
								<s:hidden name="multiuserError" id="multiuserError" />
								<s:hidden name="companyName" id="companyName" />
								<s:hidden name="brokerName" id="brokerName" />
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
$('#companyNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#brokerNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(10).search(this.value).draw();
} );
$('#contractNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
} );
$('#claimNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(3).search(this.value).draw();
} );
$('#dateOfLossSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(7).search(this.value).draw();
} );
$('#claimStatusSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(11).search(this.value).draw();
} );
editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}
     function getSubmitContractident(){
     document.taclclaim.action="${pageContext.request.contextPath}/idetifierListClaim.do?flag=contractidentifier";
     document.taclclaim.submit();
     }
   
	function contractSearch(){
	//var c=document.getElementById("claimDisplay").value;
		document.taclclaim.action="${pageContext.request.contextPath}/contractSearchClaim.do?flag=search";
		document.taclclaim.submit();
		}
      
    function ViewMode(policy_Contract_No,claim_No,proNo,layer,productId,departmentId) { 
    	//var c=document.getElementById("claimDisplay").value;
		document.taclclaim.action="${pageContext.request.contextPath}/claimViewClaim.do?mode=Amode&claim_No="+claim_No+"&contarctno="+policy_Contract_No+"&proposal_No="+proNo+"&layerNo="+layer+"&productId="+productId+"&departmentId="+departmentId;
		document.taclclaim.submit();
	}	
	function EditMode(policy_Contract_No,claim_No,proposal_No,layerNo,productId,departmentId) {
		document.taclclaim.action="${pageContext.request.contextPath}/claimInitClaim.do?edit=edit&claim_No="+claim_No+"&contarctno="+policy_Contract_No+"&proposal_No="+proposal_No+"&layerNo="+layerNo+"&productId="+productId+"&departmentId="+departmentId;
		document.taclclaim.submit();
	}	 
	function NewCliam() {
		var contNo = document.getElementById("contractNo").value;
		//var proNo = document.getElementById("proposal_No").value;
		var layer = document.getElementById("layerNo").value;
		var id = document.getElementById("productId").value;
		var dept=document.getElementById("departmentId").value;
			if(id==""){
			confirm("Please Select Product Id.");
			}
			else{
			//if(proNo==""){
			//confirm("Please Enter Proposal No.");
			//}
			if(contNo==""){
			confirm("Please Enter Contract No.");
			}
			if(id == "3"  &&layer==""){
			confirm("Please Enter Layer No.");
			}
			else{
			layer ="0";
			}
			}
			if(id=="2" && dept=="N"){
			confirm("Please Select Class");
			}
		if( contNo!="" &&id!=""){
		//var c=document.getElementById("claimDisplay").value;
		document.taclclaim.action="${pageContext.request.contextPath}/claimInitClaim.do?BusinessMode=NewCliam&contarctno="+contNo+"&claimMasterMode=CD";
		document.taclclaim.submit();
		}
	}	 
	
	function getDocList(proposalId,contNo,layerNo,tranNo,compName,brokeeName,productId,type){
	 	//var c=document.getElementById("claimDisplay").value;
	 	document.getElementById("companyName").value=compName;
		document.getElementById("brokerName").value=brokeeName;
		document.taclclaim.action="${pageContext.request.contextPath}/documentUpload.action?type="+type+"&proposalno="+proposalId+"&contarctno="+contNo+"&layerNo="+layerNo+"&tranNo="+tranNo+"&moduleType=RDS&productId="+productId;
		document.taclclaim.submit();
		}     
	function NewCliamSearch(){
		document.taclclaim.action="${pageContext.request.contextPath}/claimnewClaim.do?BusinessMode=new";
	    document.taclclaim.submit();
	}
	function funClaimMode(contractNo,proposalNo,layerNo,InceptionDate,productId){
		//var c=document.getElementById("claimDisplay").value;
		document.taclclaim.productId.value=productId;	
		document.taclclaim.action="${pageContext.request.contextPath}/claimInitClaim.do?BusinessMode=NewCliam&contarctno="+contractNo+"&proposal_No="+proposalNo+"&layerNo="+layerNo+"&inception_Date="+InceptionDate+"&flag=contractidentifier";
	    document.taclclaim.submit();
	}
	function getBack(){
	 document.taclclaim.action="${pageContext.request.contextPath}/claimMasterListClaim.do?flag=claim";
	 document.taclclaim.submit();
	 }
	function newClaimList(){
			document.taclclaim.action="${pageContext.request.contextPath}/claimMasterListClaim.do?flag=fullclaim";
			document.taclclaim.submit();
	}
	function deletedata(contNo,claimno){
	var check =  confirm("Do you want to delete this record");
	if(check){
		document.taclclaim.action="${pageContext.request.contextPath}/deleteclaimDetailsClaim.action?contarctno="+contNo+"&claim_No="+claimno+"&flag=claim";
		document.taclclaim.submit();  	
		}
	}
	function funSearchMode(mode){
	     document.taclclaim.action="${pageContext.request.contextPath}/claimMasterListClaim.do?flag=claim&searchType="+mode;
		 document.taclclaim.submit();
    }
	getDepart('<s:property value="productId"/>');
	function getDepart(val){
     if(val=='2'){
     document.getElementById('deptID').style.display='inline';
     //document.getElementById('pro').style.display='inline';
     document.getElementById('con').style.display='inline';
     document.getElementById('layer').style.display='none';
     }
     else if(val=='3'){
     document.getElementById('deptID').style.display='none';
     //document.getElementById('pro').style.display='inline';
     document.getElementById('con').style.display='inline';
     document.getElementById('layer').style.display='inline';
     }
      else if(val=='1'){
     document.getElementById('deptID').style.display='none';
     //document.getElementById('pro').style.display='inline';
     document.getElementById('con').style.display='inline';
     document.getElementById('layer').style.display='none';
     }
     else{
     document.getElementById('deptID').style.display='none';
     //document.getElementById('pro').style.display='none';
     document.getElementById('con').style.display='none';
     document.getElementById('layer').style.display='none';
     }
      }  
 
    
</script>
</html>
