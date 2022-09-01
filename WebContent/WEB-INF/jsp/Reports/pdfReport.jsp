<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title><s:text name="application.title" /> - Access Denied</title>
	<link href="${pageContext.request.contextPath}/css/inward/bootstrap.min.css" rel="STYLESHEET" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	<style type="text/css">
	.bringCenter {
		position: absolute;
	    top: 25%;
	    left: 25%;
	    width: 50%;
	}
	</style>
</head>
<body>
<div class="table0">
	<div class="tablerow">
		<div class="panel panel-danger bringCenter">
			<div class="panel-heading">
				<s:text name="application.title" />
			</div>
			<div class="panel-body">
				<img alt="Error" src="${pageContext.request.contextPath}/images/inward/error.gif"/>
				&nbsp; <b>Sorry, Access Denied</b>
			</div>
		</div>
	</div>
</div>
</body>
</html>