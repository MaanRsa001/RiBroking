<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
		<link
			href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
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
							<s:form name="cache" id="cache" action="" theme="simple">
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<div class="tablerow">
												RDS Cache
											</div>
										</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-xs-12">

														<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
															
															<thead>
																<tr>
																	<th style="text-align: center; vertical-align: middle;" class="no-sort" > <s:text name="SNo" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Proposal No" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Contract No" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Ceding Company" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Broker" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="UW Year" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Proposal Status" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Reason" /> </th>
																	<th style="text-align: center; vertical-align: middle;" > <s:text name="Fix" /> </th>
															</thead>
															<tbody>
																<s:iterator value="proposalList" var="list" status="stat">
																	<tr>
																		<td>
																			<s:property value="#stat.count" />
																		</td>
																		<td>${PROPOSAL_NO}</td>
																		<td>${CONTRACT_NO}</td>
																		<td>${Customer_name}</td>
																		<td>${Broker_name}</td>
																		<td>${UW_YEAR}</td>
																		<td>${PROPOSAL_STATUS}</td>
																		<td>${EDIT_MODE}</td>
																		<td>
																		<s:if test='#list.PROPOSAL_NO==(#list.EDIT_PROPOSAL)'>
																		<input type="button"  value="Fix"   class="btn btn-sm btn-info" id="mybutton" onclick="getFix('${PROPOSAL_NO}','${CONTRACT_NO}','${BASE_LAYER}','${EDIT_STATUS}')" />
																		
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
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
function getFix(proposal,contract,baseLayer,editMode){
document.cache.action='${pageContext.request.contextPath}/updateEditModeCeding.action?proposalNo='+proposal+'&contractNo='+contract+'&baseLayer='+baseLayer+'&editMode='+editMode; 
document.cache.submit();
}




</script>
</html>
