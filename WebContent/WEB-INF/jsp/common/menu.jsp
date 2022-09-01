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
				<%--
				<s:iterator value="%{#session.navList}" var="nav" status="stat">
					<s:a cssClass="disLink" href="#" onclick="dnavigation('%{#stat.count}','delete','','%{#nav.LIST_VALUE}')"><font
					color="#0000B8" style="vertical-align: middle; line-height: 16px;"><s:property value="LIST_KEY"/></font> </s:a>
					<font color="#660033"><i class="glyphicon glyphicon-share-alt"></i></font> &nbsp;
				</s:iterator>
				<br/>	
				<s:iterator value="%{#session.menuList}" var="menuList" status="stat">
				   <s:submit onclick="getMenuResult('%{#menuList.MENU_ID}','%{#menuList.MENU_URL}')" value="%{#menuList.MENU_NAME}" cssClass="menuButton"/>
				</s:iterator>
			 	--%>
				<s:if test='!"".equalsIgnoreCase(#session.ProductName)'>
					<s:property value="#session.ProductName" />
					<%--<font color="#660033"><i--%>
						<%--class="glyphicon glyphicon-share-alt"></i>--%>
					<%--</font> &nbsp;--%>
                    &nbsp;
			 	</s:if>
				<s:if test='!"".equalsIgnoreCase(#session.DepartmentName)'>
					<font color="#660033"><i
						class="glyphicon glyphicon-share-alt"></i>
					</font> &nbsp;
					<s:property value="#session.DepartmentName" />
			 	</s:if>
				<s:if test='!"".equalsIgnoreCase(#session.ProcessName)'>
					<font color="#660033"><i
						class="glyphicon glyphicon-share-alt"></i>
					</font> &nbsp;
					<s:property value="#session.ProcessName" />
			 	</s:if>
			</div>
		</div>
	</body>
	<s:hidden name="menuId" id="menuId" />
	<s:hidden name="menuUrl" id="menuUrl" />
</s:form>