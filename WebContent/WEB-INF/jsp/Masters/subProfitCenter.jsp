<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
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
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
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
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="subprofitcenter" theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/>
							</span>
							<span style="color:green"><s:actionmessage/></span>
							
						</div>
						<s:if test='"success".equals(path)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.Subprofit" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.Product" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="productId" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.SubProfitId" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="subProfitCenter_Id" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.SubProfitName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="subProfitCenter" />
												</div>
											</div>
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="Profit.CoreCompCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="coreCompanyCode" />
												</div>
											</div>-->
											<div class="textfield">
												<div class="text">
													<s:text name="Department.Active" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="active" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="Lists()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="AddNew" class="btn btn-sm btn-primary" onClick="Addnew()"/>
								<s:hidden value="subProfitCenter_Id" />
								<s:hidden value="productId" />					
							</div>						
						</div>
						</s:if>
						<s:if test='"InsertSubProfitCenter".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="Heading.Subprofit" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.Product" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:if test='"insert".equals(mode)'>
													<s:select list="productList" name="productId" cssClass="inputBoxS" headerKey="" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME"   headerValue="---Select---" />
												</s:if>
												<s:else>
												<s:select list="productList" name="productId" cssClass="inputBoxS" headerKey="" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME"   headerValue="---Select---" disabled="true"/>
												<s:hidden name="productId"/>
												</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.SubProfitId" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:if test='"insert".equals(mode)'>
												<s:textfield name="subProfitCenter_Id" cssClass="inputBox" />
												</s:if>
												<s:else>
												<s:textfield name="subProfitCenter_Id" cssClass="inputBox" readonly="true" />
												
												</s:else>
													<%--<s:if test='subProfitCenter_Id == null || subProfitCenter_Id == ""'>
														<s:textfield name="subProfitCenter_Id" cssClass="inputBox" />
														<s:hidden name="operation" value="insert" />
													</s:if>
													<s:else>
													<s:textfield name="subProfitCenter_Id" cssClass="inputBox" readonly="true" />
														<s:if test='"true".equals(edit)'>
															<s:textfield name="subProfitCenter_Id" cssClass="inputBox" readonly="true" />
															<s:hidden name="operation" value="readonly" />
														</s:if>
														<s:else>
															<s:textfield name="subProfitCenter_Id" cssClass="inputBox" />
															<s:hidden name="operation" value="insert" />
														</s:else>
													</s:else>--%>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="SubProfit.SubProfitName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="subProfitCenter" cssClass="inputBox" />
												</div>
											</div>
											<!--  <div class="textfield">
												<div class="text">
													<s:text name="Profit.CoreCompCode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="coreCompanyCode" cssClass="inputBox" />
												</div>
											</div>-->
											<div class="textfield">
												<div class="text">
													<s:text name="Department.Active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" value="active==null?'N':active"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button"  value="Back" class="btn btn-sm btn-danger" onClick="Lists()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues()" />
								<!--<s:hidden name="subProfitCenter_Id" />
								--><!--<s:hidden name="productId" />-->
						<s:hidden name="mode"/>
							</div>
						</div>				
						</s:if>
						
						<s:if test='"ListSubProfitCenter".equals(path)'>
						
						<%-- <div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="variant.searchBy" />
												</div>
												<div class="tbox">
													<s:select list="#{'SP':'SubProfitCenter'}" name="searchType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.enterDataForSearch" />
												</div>
												<div class="tbox">
													<s:textfield name="searchValue" cssClass="inputBox" />
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div><s:hidden name="mode" value="search"/>
							<div class="boxcontent" align="center">
								<input type="button"  value="Go"   class="btn btn-sm btn-success" onClick="Lists()" /> &nbsp;&nbsp;&nbsp;
								<input type="button" value="Add New" class="btn btn-sm btn-primary" onClick="Addnew()" />
							</div>
						</div>--%>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="SubProfit.SUBPROFITCENTERLIST" />
									</div>
									<div class="panel-body">
										<%--<display:table name="subprofitCenterList" pagesize="12" requestURI="" excludedParams="*" class="table table-bordered" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:center;" title="SubProfitId" property="TMAS_SPFC_ID" />
											<display:column sortable="true" style="text-align:left;" title="SubProfitCenter" property="TMAS_SPFC_NAME" />
											<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS" />
											<display:column sortable="true" style="text-align:center;" title=" Edit">
												<img border='0'	src="<%=request.getContextPath()--%/images/icon_view_schedule.gif" onClick="funEditMode('${record.TMAS_SPFC_ID}','${record.TMAS_PRODUCT_ID}')" alt="Edit Contract " width="12" height="17">
											</display:column>
										</display:table>--%>
										<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
											<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;"><s:text name="S.No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="SubProfitId" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="SubProfitCenter" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name=" Status " /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name=" Edit " /></th>
												</tr>
										</thead>
										<tbody>
											<s:iterator value="subprofitCenterList" var="list" status="stat">
												<tr>																			
													<td><s:property value="#stat.count"/></td>
													<td>${TMAS_SPFC_ID}</td>
													<td>${TMAS_SPFC_NAME}</td>
													<td>${STATUS}</td>
													<td><img border='0'	src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode('${TMAS_SPFC_ID}','${TMAS_PRODUCT_ID}')" alt="Edit Contract " width="12" height="17"></td>
													
												</tr>
											</s:iterator>
										</tbody>
									</table>
										<!--<s:hidden name="subProfitCenter_Id" id="subProfitCenter_Id" />
										<s:hidden name="productId" id="productId" />
									<s:hidden name="mode" id="mode" />-->
									</div>
								</div>
							</div>
						</div>
						
						</s:if>																		
					</div>
					<!--<s:hidden name="mode" id="mode" />					
					--></s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function submitValues() {
		document.subprofitcenter.action='${pageContext.request.contextPath}/insertsubprofitCenterAdmin.action';
		document.subprofitcenter.submit();
	}
	
	function Lists() {
		document.forms[0].action='${pageContext.request.contextPath}/subprofitCenterAdmin.action';
	document.forms[0].submit();
	}
	         
	//add by Najeeb
	function searchdetails() {
		document.subprofitcenter.action="<%=request.getContextPath()%>/SubProfitCenterDispatchAction.do?method=InsertSubProfitCenter&Business=listSerch";
		document.subprofitcenter.submit();
	}
	
	function funEditMode(val,val1) {
	    document.subprofitcenter.mode.value='Edit';
		//document.getElementById("subProfitCenter_Id").value=val;
		//document.getElementById("productId").value=val1;
		document.subprofitcenter.action='${pageContext.request.contextPath}/insertsubprofitCenterAdmin.action?subProfitCenter_Id='+val+'&productId='+val1;
		document.subprofitcenter.submit();	       
	}
	
	function back() {
		document.subprofitcenter.action="<%=request.getContextPath()%>/SubProfitCenterDispatchAction.do?method=Init";
		document.subprofitcenter.submit();
	}
	function Addnew(){
	document.forms[0].action='${pageContext.request.contextPath}/editSubprofitCenterAdmin.action';
	document.forms[0].submit();
	}
	
</script>		
</body>
</html>
