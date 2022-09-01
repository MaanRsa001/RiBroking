<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
		<link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	 <style>
		.button {background-color: #4CAF50; /* Green */}
		
		.button2 {background-color: #008CBA;} /* Blue */
		.button3 {background-color: #f44336;} /* Red */ 
		.button4 {background-color: #e7e7e7; color: black;} /* Gray */ 
		.button5 {background-color: #555555;} /* Black */
</style>
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
               			<div class="boxcontent">
							<s:form id="reinstatement" name="reinstatement" theme="simple" action="" method="post" >
							<div class="panel panel-primary">
								<div class="panel-heading">
									Cedant Details
								</div>
								<div class="panel-body">
								<s:if test="cedantNoList.size()>0">
								<table class="footable"width="100%" cellspacing="0">
									<thead>
										<tr>
											<th style="text-align: center; vertical-align: middle;" class="no-sort" > <s:text name="SNo" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Claim No" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Claim Registration Date" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Insured Name" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Cedants Claim No" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Date of Loss" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Ceding Company Name" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Broker Name" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Currency" /> </th>
											<th style="text-align: center; vertical-align: middle;" > <s:text name="Gross Loss - FGU - OC" /> </th>
											<s:if test='"3".equals(productId)'>
													<th style="text-align: center; vertical-align: middle;" ><s:text name="NonClaim.LossEstimateOurshare-OrigCurr" /></th>
											</s:if>
											<s:if test='"1".equals(productId)'>
													<th style="text-align: center; vertical-align: middle;" ><s:text name="Claim.LossEstimateOurshare-OrigCurr" /></th>
											</s:if>
											<s:if test='"2".equals(productId)'>
													<th style="text-align: center; vertical-align: middle;" ><s:text name="proClaim.LossEstimateOurshare-OrigCurr" /></th>
											</s:if>
									</thead>
									<tbody>
										<s:iterator value="cedantNoList" var="list" status="stat">
											<tr>
												<td><s:property value="#stat.count" /></td>
												<td>${CLAIM_NO}</td>
												<td>${CREATED_DATE}</td>
												<td>${INSURED_NAME}</td>
												<td>${CEDENT_CLAIM_NO}</td>
												<td>${DATE_OF_LOSS}</td>
												<td>${Ceding_Company_Name}</td>	
												<td>${Broker_Name}</td>
												<td>${CURRENCY}</td>
												<td align="right"><s:property value="getText('number.format',{GROSSLOSS_FGU_OC})"/></td>
												<td align="right"><s:property value="getText('number.format',{LOSS_ESTIMATE_OS_OC})"/></td>
												
											</tr>
										</s:iterator>
									</tbody>
								</table>
								</s:if>
									<s:else>
									   <div class="panel panel-primary" >
		                                  <div class="panel-body">
		                                      No Data Available
		                                  </div>
		                              </div>
									</s:else>
									<br class="clear"/>
								<div class="boxcontent" align="center">
									<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
								</div>
								</div>
							</div>	
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">

</script>

</body>
</html>
