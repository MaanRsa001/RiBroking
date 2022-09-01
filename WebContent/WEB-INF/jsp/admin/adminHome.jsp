<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<SCRIPT type="text/javascript">
	function getMenuResult(menuId, menuURL) {
		document.getElementById("menuId").value = menuId;
		document.getElementById("menuUrl").value = menuURL;
		document.menus.action = "userRightsMenu.do";
		document.menus.submit();
	}
</SCRIPT>
</head>
<s:form name="menus" theme="simple" action="">
	<body>
		<div class="table0">
			<div class="tablerow">
			<ul>
				<s:iterator value="%{#session.MenuList}" var="menuList" status="stat">
				<li>
				<a href="<s:property value="MENU_URL"/>" class="btn btn-primary" style="margin-top:10px;width:300px;"/><s:property value="MENU_NAME"/></a>
				</li>
				</s:iterator>
			 	</ul>
				
			</div>
		</div>
	</body>
	<s:hidden name="menuId" id="menuId" />
	<s:hidden name="menuUrl" id="menuUrl" />
</s:form>