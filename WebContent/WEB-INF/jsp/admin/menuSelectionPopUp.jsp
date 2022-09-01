<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
	</head>

	<body>
		<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="" name="" method="post" action="" theme="simple">
							<div class="table2">
								<div class="tablerow">
									<s:a action="" cssClass="imgLogo">       
							            <img alt="Royal Sundaram" src="<%=request.getContextPath()%>/images/rs-logo.png" width="230" height="71">
							        </s:a>
								</div>
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>					
								<div class="tablerow" style="margin-top: 10px;">
									<div class="heading">
										<s:text name="menu.menuSelection" />										
									</div>
								</div>
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent">
										<div class="borderC" style="padding: 5px;">
										<table width="50%" style="margin: 0 auto;">
											<tr>
												<td width="10px">
													<s:checkbox name="" />
												</td>
												<td>
													Receipt File Upload
												</td>
											</tr>
											<tr>
												<td>
													<s:checkbox name="" />
												</td>
												<td>
													CITI Bank Upload
												</td>
											</tr>
											<tr>
												<td>
													<s:checkbox name="" />
												</td>
												<td>
													HDFC Bank Upload
												</td>
											</tr>
											<tr>
												<td>
													<s:checkbox name="" />
												</td>
												<td>
													SCB Upload
												</td>
											</tr>
										</table>
										</div>
									</div>
								</div>
								<div class="tablerow" style="background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="center">
										<input type="button" class="btn2 rEdge" value="Close" onclick="window.close();" />										
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
</html>
