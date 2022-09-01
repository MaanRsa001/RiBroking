<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%--<sj:head jqueryui="true" jquerytheme="start" />--%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	
 
</head>
  <body>
  <div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
                    <div class="boxcontent">
                        <s:form id="copyProposa" name="copyProposa" theme="simple" action="" method="post" >
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                               Copy Details
							    </div>
							    <div class="panel-body">
							    <div class="boxcontent">
							       A copy of this contract has been created vide proposal number <s:property value="proposalNo"/>.  You can amend the proposal from the Pending Offers Screen.
							    </div>
							    </div>
							    </div>
							    </s:form>
							    </div>
							    </div>
							    </div>
							    </div>
							    </div>
							    </div>
    
    
    <div class="boxcontent" align="center">
        <input type="submit"  value="Go to Pending Offers"   class="btn btn-sm btn-info" onclick=" FunctionRejectCancel()" />
         <input type="submit"  value="Back"   class="btn btn-sm btn-danger" onclick=" endorsementCancel()" />
    </div>
    
<script type="text/javascript">

function endorsementCancel() {
	document.copyProposa.action = "${pageContext.request.contextPath}/InitPortfolio.do";
	document.copyProposa.submit();
}

function FunctionRejectCancel()
{
	
			document.copyProposa.action='${pageContext.request.contextPath}/menuPortfolio?layerNo=0'; 
			document.copyProposa.submit();
		
}
</script>
 </body>
</html>
