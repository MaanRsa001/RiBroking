<%-- 
    Author     : Raja.K
    Document   : Common Login Template
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css"/>
<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
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

<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
       	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>		    
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
    	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>

<script type="text/javascript">
$(function(){
	$("#selectall").click(function () {
		  //$('.case').attr('checked', this.checked);
		  $('.case').not(this).prop('checked', this.checked);
	});
	$(".case").click(function(){
		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}
	});
});
</script>
  
	<script type="text/javascript">
	
		
     	function funBack(){
     		document.userInv.action="${pageContext.request.contextPath}/welcomeHomeAdmin.do";
             document.userInv.submit();
     	
     	}
	</script>
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="userInv" id="userInv" theme="simple" action="" method="post">
					<div class="row" style="display: table:width: 100%; margin: 0 auto;" >
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<span style="color:green"><s:actionmessage/></span>
							<s:actionerror cssStyle="color: red;" />
						</div>
					</div>
						<div class="table2">
							<div class="tablerow">
								<div class="table0" style="width: 100%; margin: 0 auto;">
								    <div class="tablerow">
								        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
								            <div class="tablerow">
								                <div style="padding:10px; background:#F8F8F8">
													<div class="boxcontent">
														<div class="panel panel-primary">
															<div class="panel-heading">
															User Login List
															</div>
															<s:if test="status==null">
																<div class="panel-body">
																	<div class="boxcontent">
																		 <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																			<thead>
																				<tr>
																				<th style="text-align: center; vertical-align: middle;" class="no-sort" width="2%"><input type="checkbox" id="selectall" />Select All</th>
																				<th style="text-align: center; vertical-align: middle;" width="2%" class="no-sort">
																						<s:text name="S.No" />
																					</th>
																					<th style="text-align: center; vertical-align: middle;">
																						<s:text name="Login ID" />
																					</th>
																					<th style="text-align: center; vertical-align: middle;">
																						<s:text name="Login Name" />
																					</th>
																					<th style="text-align: center; vertical-align: middle;">
																						<s:text name="Last Login Time" />
																					</th>
																					
																				</tr>
																			</thead>
																			<tbody>
																				<s:iterator value="userLoginList" var="list" status="stat">
																				<tr>
																				<td><input type="checkbox"  value="${list.SESSION_ID}" id="${list.SESSION_ID}"   class="case" name="case" /></td>
																					<td >
																						<s:property value='#stat.count'/>
																					</td>
																					<td >
																						<s:property value='#list.LOGIN_ID'/>
																					</td>
																					<td >
																						<s:property value='#list.USERNAME'/>
																					</td>
																					
																					<td >
																						<s:property value='#list.LOGIN_DT'/>
																					</td>
																					
																				</tr>
																				</s:iterator>
																			</tbody>
																		</table>
																	<br class="clear" />
																</div>
															</div>
															 <div class="row" align="center">
															 <input type="button" value="Invalidate" class="btn btn-sm btn-success" onclick="funInvalid();"  />
															 <input type="button" value="Back" class="btn btn-sm btn-danger" onclick="funBack();"  />
															 </div>
														</s:if> 
														<s:else>
														<div class="row" align="center">
														  Login Invalidated successfully
														</div>
														<div class="row" align="center">
															 <input type="button" value="Back" class="btn btn-sm btn-danger" onclick="funBack();"  />
														</div>
														</s:else>			
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<s:hidden name="loginId" id="loginId"></s:hidden>
					<s:hidden name="sessionId" id="sessionId"></s:hidden>
				</s:form>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
function funInvalid(){
 var checkedVal ="";
$('#gridTableMake input[name=case]:checked').each(function(index,elem) {
checkedVal = checkedVal.concat($(elem).val()+',')
  });
if(checkedVal!=''){
 answer = confirm("Do You Want to invalidate this Login");
 if(answer){
 document.userInv.action="${pageContext.request.contextPath}/LoginuserInvalidate.do?checkedVal="+checkedVal;
 document.userInv.submit();
 }
}
else{
alert("Please Select Login For Invalidation");
}
}
</script>

</body>
</html>