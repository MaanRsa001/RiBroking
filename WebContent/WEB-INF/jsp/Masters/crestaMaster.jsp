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

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="cresta" theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						<s:if test="'list'.equals(mode)">
						<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="cresta.heading.list" />
										<div align="right">
										<input type="button" value="Add New" class="btn btn-sm btn-info" onClick="AddNewForm('new')"/>
										</div>
									</div>
						<div class="panel-body">
															<div class="row">
																<div class="col-xs-12">
								   									<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																		<thead>
																			<tr>
																				<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="TERRITORY CODE" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="CRESTA ID" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="CRESTA NAME" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="REMARKS" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="STATUS" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="ENTRY DATE" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="EDIT" /></th>
																			</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="crestaMasterList" var="list" status="stat">
																			<tr>
																				<td><s:property value="#stat.count" /></td>
																				<td>${TERITORY_NAME}</td>
																				<td>${CRESTA_ID}</td>
																				<td>${CRESTA_NAME}</td>
																				<td>${REMARKS}</td>
																				<td>${STATUS }</td>
																				<td>${ENTRY_DATE}</td>
																				<td><input type="button" value="Edit" class="btn btn-sm btn-info" onClick="funEditMode('edit','${CRESTA_SNO}')"/></td>
																			</tr>
																		</s:iterator>
																	</tbody>
																</table>
															</div>			
														</div>
													</div>
													</div>
						</s:if>
						<s:else>
						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="cresta.heading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">	
										<div class="textfield">
												<div class="text">
													<s:text name="cresta.territory" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox" id="product">
													<s:select list="dropDownTerritoryList" name="territoryId" id="territoryId"  listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" />
												</div>
											</div>
																					
											<div class="textfield">
												<div class="text">
													<s:text name="cresta.cresid" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="crestaId" id="crestaId" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="cresta.cresName" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield cssClass="inputBox" name="crestaName" id="crestaName"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="cresta.remarks" />
												</div>
												<div class="tbox">
													<s:textarea rows="1" name="remarks" cssClass="inputBoxA" cssStyle="width: 90%;" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="cresta.status" /> &nbsp;<span style="color: red;">*</span>
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
								<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="Cancel()"  /> &nbsp;&nbsp;&nbsp;
								<s:if test='"new".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('insert')"/>
								
								</s:if>
								<s:if test='"edit".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('update')"/>
									<s:hidden name="crestaSNo" id="crestaSNo"/>
								</s:if>
							</div>
						</div>										
						
						</s:else>																			
					</div>				
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

    	
function funEditMode(val,id) {       	
	document.cresta.action='${pageContext.request.contextPath}/detailsCrestaAdmin.action?mode='+val+'&crestaSNo='+id;
	document.cresta.submit();       
}
  
function AddNewForm(val) {
	document.cresta.action='${pageContext.request.contextPath}/detailsCrestaAdmin.action?mode='+val;
	document.cresta.submit();
}

function SubmitForm(value) {
	document.cresta.action='${pageContext.request.contextPath}/detailsCrestaAdmin.action?mode='+value;
	document.cresta.submit();
}

function Cancel() {
	document.cresta.action='${pageContext.request.contextPath}/detailsCrestaAdmin.action?mode=list';
	document.cresta.submit();
}

function home() {
	document.menus.action='${pageContext.request.contextPath}/menuMasterAdmin.action';
	document.menus.submit();
}

function Edit() {
	document.menus.action='${pageContext.request.contextPath}/insertMenuMasterAdmin.action?mode=Edit';
	document.menus.submit();
}


</script>		
</body>
</html>
