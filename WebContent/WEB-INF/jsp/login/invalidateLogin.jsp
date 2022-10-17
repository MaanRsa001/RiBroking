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
	<script type="text/javascript">
	
		 function funinvalidate(id,val){
           	document.getElementById("loginId").value=id;
           	document.getElementById("sessionId").value=val;
           	document.invalidate.action="${pageContext.request.contextPath}/Logininvalidate.do";
              document.invalidate.submit();
     	}
     	function funBack(){
     		document.invalidate.action="${pageContext.request.contextPath}/Loginpage.do";
             document.invalidate.submit();
     	
     	}
	</script>
</head>

<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="invalidate" id="invalidate" theme="simple" action="" method="post">
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
																 Your previous session was not terminated properly.  Kindly invalidate your session and re-login to continue.
															</div>
															<s:if test="status==null">
																<div class="panel-body">
																	<div class="boxcontent">
																		<table width="100%" class="table table-bordered">
																			<thead>
																				<tr>
																					<th width="10%">
																						<s:text name="Login ID" />
																					</th>
																					<th width="33%">
																						<s:text name="Session ID" />
																					</th>
																					<th width="33%">
																						<s:text name="IP Address" />
																					</th>
																					<th width="33%">
																						<s:text name="Last Login Time" />
																					</th>
																					<th width="10%">
																						<s:text name="Invalidate" />
																					</th>
																				</tr>
																			</thead>
																			<tbody>
																				<s:iterator value="invalidLoginList" var="list" status="stat">
																				<tr>
																					<td style="text-align: center;">
																						<s:property value='#list.LOGIN_ID'/>
																					</td>
																					<td style="text-align: center;">
																						<s:property value='#list.SESSION_ID'/>
																					</td>
																					<td style="text-align: center;">
																						<s:property value='#list.IP_ADDRESS'/>
																					</td>
																					<td style="text-align: center;">
																						<s:property value='#list.LOGIN_DT'/>
																					</td>
																					<td style="text-align: center;">
																						<a href="#" onclick="funinvalidate('<s:property value='#list.LOGIN_ID'/>','<s:property value='#list.SESSION_ID'/>');" >Invalidate</a>
																					</td>
																				</tr>
																				</s:iterator>
																			</tbody>
																		</table>
																	<br class="clear" />
																</div>
															</div>
															 <div class="row" align="center">
															 <input type="button" value="Back" class="btn btn-sm btn-danger" onclick="funBack();"  />
															 </div>
														</s:if> 
														<s:else>
														<div class="row" align="center">
														Session is invalidate Successfully.Please Click on Back button and Relogin
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
</body>
</html>