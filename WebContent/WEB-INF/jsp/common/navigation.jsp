<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menu-style.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dropdownPlain.js"></script>
	</head>
	<body>
		<s:form name="NavigationForm" id="NavigationForm" theme="simple" action="" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr bgcolor="#FFFFFF">
					<td width="100%" align="left">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>					
								<td align="left" valign="middle" style="background-color: #167ab3;">						
									<ul class="dropdown" style="font-family;Arial;font-size:12px;margin: 0 auto;">
										<s:iterator value="%{#session.ProductList}" var="productList" status="stat">
											<li>
											<s:a href="#" onclick="departmentMenuList('%{#productList.TMAS_PRODUCT_ID}','%{#productList.TMAS_PRODUCT_NAME}','product%{#productList.TMAS_PRODUCT_ID}')"><s:property value="#productList.TMAS_PRODUCT_NAME"/> <i class="glyphicon glyphicon-hand-down pullRight"></i> </s:a>
												<ul class="sub_menu" style="visibility: hidden;" id="product${TMAS_PRODUCT_ID}">
												
												</ul>
											</li>
										</s:iterator>
									</ul>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br class="clear"/>
			<s:hidden name="productId"/>
			<s:hidden name="departmentId" id="departmentId" />
			<s:hidden name="processId"/>
			<s:hidden name="menuId"/>
			<s:hidden name="productName"/>
			<s:hidden name="departmentName"/>
			<s:hidden name="processName"/>
			<s:hidden name="menuUrl"/>
			<s:hidden name="menuName"/>
			<s:hidden name="mode" id="menuMode"/>
			<s:hidden name="proposalNo" id="menuproposalNo"/>
			<s:hidden name="proposal_no" id="menuproposal_no"/>
		</s:form>
		<SCRIPT type="text/javascript">
		function dockNavigation(productId, productName, departmentId, departmentName, processId, processName, menuId, menuUrl,menuName) {
		var mode=document.getElementById("menuMode").value;
		var proposal1=document.getElementById("menuproposalNo").value;
		if(proposal1==null || proposal1=='' ){
		var proposal=document.getElementById("menuproposal_no").value;
		//alert(proposal+"1");
		}else{
		var proposal=document.getElementById("menuproposalNo").value;
		//alert(proposal+"0");
		}
		//alert(mode);
		//alert(proposal);
		if(mode=='endorsment' && menuId=='' && menuUrl=='welcomeHome.action' ){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
	     	if(answer){
	     		document.NavigationForm.productId.value = productId;
				document.NavigationForm.productName.value = productName;
				document.NavigationForm.departmentId.value = departmentId;
				document.NavigationForm.departmentName.value = departmentName;
				document.NavigationForm.processId.value = processId;
				document.NavigationForm.processName.value = processName;
				document.NavigationForm.menuId.value = menuId;
				document.NavigationForm.menuUrl.value = menuUrl;
				document.NavigationForm.mode.value = mode;
				document.NavigationForm.proposalNo.value =proposal;
				document.NavigationForm.menuName.value =menuName;
				document.NavigationForm.action = "${pageContext.request.contextPath}/dockNavigationMenu.do?menumodeStatus="+mode;
				document.NavigationForm.submit();
				}
	     	else{
     			return false;
     			}
     		}
    	else{
			document.NavigationForm.productId.value = productId;
			document.NavigationForm.productName.value = productName;
			document.NavigationForm.departmentId.value = departmentId;
			document.NavigationForm.departmentName.value = departmentName;
			document.NavigationForm.processId.value = processId;
			document.NavigationForm.processName.value = processName;
			document.NavigationForm.menuId.value = menuId;
			document.NavigationForm.menuUrl.value = menuUrl;
			document.NavigationForm.menuName.value =menuName;
			document.NavigationForm.action = "${pageContext.request.contextPath}/dockNavigationMenu.do";
			document.NavigationForm.submit();
			}
			
		}
		function departmentMenuList(productId,productName,id) {
		var mode=document.getElementById("menuMode").value;
		//alert(document.getElementById("menuproposalNo").value+" no");
		//alert(document.getElementById("menuproposal_no").value+" no1");
		var proposal1=document.getElementById("menuproposalNo").value;
		if(proposal1==null || proposal1=='' ){
		var proposal=document.getElementById("menuproposal_no").value;
		//alert(proposal+"1");
		}else{
		var proposal=document.getElementById("menuproposalNo").value;
		//alert(proposal+"0");
		}
		//alert(mode);
		//alert(proposal);
		if(mode=='endorsment'){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
	     	if(answer){
	     		var url = "${pageContext.request.contextPath}/departmentMenuAjaxHome.action?productId="+productId+"&productName="+productName+"&menumode="+mode+"&proposalNo="+proposal;
				postRequest(url, id);
				}
	     	else{
     			return false;
     			}
     		}
    	else{
			var url = "${pageContext.request.contextPath}/departmentMenuAjaxHome.action?productId="+productId+"&productName="+productName+"&proposalNo="+proposal;
			postRequest(url, id);
			}
		}
		function processMenuList(productId,productName,departmentId,departmentName,id) {
			var url = "${pageContext.request.contextPath}/processMenuAjaxHome.action?productId="+productId+"&productName="+productName+"&departmentId="+departmentId+"&departmentName="+departmentName;
			postRequest(url, id);
		}
		function finalMenuList(productId,productName,departmentId,departmentName,processId,processName,id) {
			var url = "${pageContext.request.contextPath}/finalMenuAjaxHome.action?productId="+productId+"&productName="+productName+"&departmentId="+departmentId+"&departmentName="+departmentName+"&processId="+processId+"&processName="+processName;
			postRequest(url, id);
		}
		</SCRIPT>
	</body>
</html>