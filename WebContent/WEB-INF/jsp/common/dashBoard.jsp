<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>Test Page</title>
</head>
<body>	
	<s:form id="" name="" method="post" action="" theme="simple">
	<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							
							<div class="table2">				
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="center">
									<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
										<%-- <img alt="Arom Grips" src="<%=request.getContextPath()%>/images/Nsure_Logo.jpg" width="30%" height="30%" align="middle"> --%>
										<img alt="<s:property value='#session.COMPANY_NAME'/>" src="<%=request.getContextPath()%>/images/<s:property value='#session.MAIN_LOGO'/>" width="40%" height="40%">
									</s:if>
									<s:else>
										<img alt="<s:property value='#session.COMPANY_NAME'/>" src="<%=request.getContextPath()%>/images/<s:property value='#session.MAIN_LOGO'/>" width="40%" height="40%">
										<%-- <img alt="Uganda RE" src="<%=request.getContextPath()%>/images/Nsure_Logo.jpg" width="100%" height="100%"> --%>
									</s:else>
									</div>
								</div>					
							</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	</s:form>
  </body>
</html>
