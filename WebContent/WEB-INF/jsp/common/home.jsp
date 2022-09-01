<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
		</script>
	</head>

	<body onload="getExpiryDate()">
		<div class="table0" style="width: 90%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
					<div class="tablerow">
						<div style="padding:10px; background:#F8F8F8">
							<s:form id="dForm" name="dForm" method="post" action="" theme="simple">
							<div class="table2">
								<div class="tablerow">
									<span style="color:red;"><s:actionerror/></span>
								</div>
								<s:if test="'department'.equals(mode)">
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="center">
										<div class="panel panel-primary panelWidth">
											<div class="panel-heading">
												<s:text name="label.departmentList" />
											</div>
											<div class="panel-body">
												<s:submit name="retro" action="retro1Retro" value="Retro" cssClass="productButton" /> <br/><br/>
												<s:submit name="retroxol" action="retroxol1Retro" value="Retro XOL" cssClass="productButton" /> <br/><br/>												
											</div>
										</div>
									</div>									
								</div>
								</s:if>
								<s:else>								
								<div class="tablerow" style="margin-top: 10px; background-color: #fff; padding: 10px;">
									<div class="boxcontent" align="center">
										<div class="panel panel-primary panelWidth">
											<s:if test='null==homeType || "product".equalsIgnoreCase(homeType)'>
												<div class="panel-heading">
													<s:text name="label.productList" />
												</div>
												<div class="panel-body">
												 <s:iterator value="productList" status="stat" var="product">
												    <%-- <a class="productButton" href="departmentHome.action?productId=<s:property value='%{#product.TMAS_PRODUCT_ID}' />&navName=<s:property value='%{#product.TMAS_PRODUCT_NAME}'/> "> <s:property value="#product.TMAS_PRODUCT_NAME"/> </a> <br/><br/> --%>
												    <s:a href="#" onclick="dnavigation('2','add','%{#product.TMAS_PRODUCT_NAME}','departmentHome.action?productId=%{#product.TMAS_PRODUCT_ID}')" cssClass="productButton"><s:property value="#product.TMAS_PRODUCT_NAME"/></s:a>
												    <br/><br	/> 
												 </s:iterator>
												 </div>
											</s:if>
											<s:if test='"department".equalsIgnoreCase(homeType)'>
												<div class="panel-heading">
													<s:text name="label.departmentList" />
												</div>
												<div class="panel-body">
												 <s:iterator value="departmentList" status="stat" var="department">
												  <%-- <a class="productButton" href="getProcessHome.action?departmentId=<s:property value='%{#department.TMAS_DEPARTMENT_ID}' />&navName=<s:property value='%{#department.TMAS_DEPARTMENT_NAME}'/>"> <s:property value="%{#department.TMAS_DEPARTMENT_NAME}"/> </a> <br/><br/>--%>
												  <s:a href="#" onclick="dnavigation('3','add','%{#department.TMAS_DEPARTMENT_NAME}','getProcessHome.action?departmentId=%{#department.TMAS_DEPARTMENT_ID}&navName=%{#department.TMAS_DEPARTMENT_NAME}')" value="%{#department.TMAS_DEPARTMENT_NAME}" cssClass="productButton"><s:property value="%{#department.TMAS_DEPARTMENT_NAME}"/></s:a>
												    <br/><br/> 
												 </s:iterator>
												</div>
											</s:if>
											<s:if test='"process".equalsIgnoreCase(homeType)'>
												<div class="panel-heading">
													<s:text name="label.processList" />
												</div>
												<div class="panel-body">
												 <s:iterator value="processList" status="stat" var="process">
												    <%-- <a class="productButton" href="getLaunchHome.action?processId=<s:property value='%{#process.PROCESS_ID}' />&navName=<s:property value='%{#process.PROCESS_NAME}'/>"> <s:property value="%{#process.PROCESS_NAME}"/> </a> <br/><br/>--%>
												    <s:a href="#" onclick="dnavigation('4','add','%{#process.PROCESS_NAME}','getLaunchHome.action?processId=%{#process.PROCESS_ID}&navName=%{#process.PROCESS_NAME}')" value="%{#process.PROCESS_NAME}" cssClass="productButton"><s:property value="%{#process.PROCESS_NAME}"/></s:a>
												    <br/><br/>
												 </s:iterator>
												</div>
											</s:if>
										</div>
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
	</body>
</html>
