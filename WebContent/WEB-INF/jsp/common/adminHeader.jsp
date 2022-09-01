<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false"%>
<%try{ %>

<div class="table0" style="padding: 5px 0px;">
	<div class="tablerow">
		<div class="pullLeft">
			<img alt="GRIPS" src="<%=request.getContextPath()%>/images/<s:property value='#session.HEADER_LOGO'/>" width="230" height="71"/>
		</div>
		<div class="pullRight">
			<font color="#AAAAAA" size=6  face="Courier New" style="font-weight: bolder;"> 
				<b style="text-transform: uppercase;"><s:text name="application.name" /></b>
			</font>
		</div>
		<br class="clear"/>
	</div>
	<div class="tablerow">
		<div class="pullRight">
		<s:a action="welcomeHomeAdmin" cssClass="loginMenu"><s:text name="label.home"/></s:a> |
			<s:a action="Loginout" cssClass="loginMenu"><s:text name="label.logout"/></s:a> |
			Welcome <s:property value="%{#session.UserName}" />
		</div>
		<br class="clear"/>
	</div>
</div>
<%}catch(Exception e){e.printStackTrace();}%>