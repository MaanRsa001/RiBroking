<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
 
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript">
	jQuery(function ($) {
			try {
				var data = $('#gridTableMake').dataTable( {
					"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
					"order": [[ 0, "asc" ]],
					"columnDefs": [ {
			          "targets": 'no-sort',
			          "orderable": false
				    } ],
					responsive: true
				});				 
			} catch(err){}
		} );	
		
	 </script>	
<SCRIPT language="javascript">
$(function(){

	 $("#selectall").click(function () {
        if ($("#selectall").is(':checked')) {
            $(".checkItem").prop("checked", true);
        } else {
            $(".checkItem").prop("checked", false);
        }
    });
    
     $(".checkItem").click(function () {
            $("#selectall").prop("checked", false);
    });
    
});

</SCRIPT>
    <title>My JSP 'CedingView.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <style>
	  #tooltip{
    position:absolute;
    border:1px solid #333;
    background:#f7f5d1;
    padding:2px 5px;
    color:#333;
    display:none;
    }  
	 </style>	
<script type="text/javascript">
	 $(function() {
	    $( "#transactionDateSearch" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });
	  </script>	 
  </head>
<body>
  <div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
				<s:form name="authentication" id="authentication" action="" theme="simple" >
				<div class="tablerow">
							<span style="color: red;"><s:actionerror /> </span>
				</div>
				
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									   <s:text name="Heading.Authedication"/>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
								<div class="col-xs-12">
								   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
									<thead>
									<tr>
										<th   class="no-sort" width="3%" style="text-align: center; vertical-align: middle;"><s:text name="Select" /><input type="checkbox" id="selectall"/></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Request No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Contract No" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Instant Date / Statement Period" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Used Id" /></th>
										
										<th style="text-align: center; vertical-align: middle;"><s:text name="Edit" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="View" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Approve" /></th>
										<th style="text-align: center; vertical-align: middle;"><s:text name="Reject" /></th>
									</tr>
									</thead>
									<tbody>
										<s:iterator value="authenticationList" var="list" status="stat">
											<tr>
												<td style="text-align: center; vertical-align: middle;" > <input type="checkbox" value="<s:property value="requestNo"/>"  id="chkbox${stat.count-1}"  class="checkItem"  name="checkItem" "/>
														  </td>
												<td><s:property value="requestNo"/></td>
												<td><s:property value="contNo"/></td>
												<td><s:property value="account_Period"/></td>
												<td><s:property value="loginId"/></td>
												<td>
													<a title="Edit" style="cursor: pointer;" onClick="EditMode('<s:property value="contNo"/>','<s:property value="requestNo"/>','<s:property value="layerNo"/>','<s:property value="departmentId"/>','<s:property value="productId"/>') ">Edit</a>
												</td>
												<td>
													<a title="View" style="cursor: pointer;" onClick="ViewMode('<s:property value="contNo"/>','<s:property value="requestNo"/>','<s:property value="layerNo"/>','<s:property value="departmentId"/>','<s:property value="productId"/>')">View</a>
												</td>
												<td>
												<a title="Approve" style="cursor: pointer;" onClick="Approve('<s:property value="requestNo"/>','A','single')">Approve</a>
												</td>
												<td>
												<a title="Reject" style="cursor: pointer;" onClick="Approve('<s:property value="requestNo"/>','R','single')">Reject</a>
												</td>
											</tr>
										</s:iterator>
									</tbody>
									</table>
											</div>			
								</div>
							</div>
							
							</div>
							<div align="center">
								 <input type="submit"  value="Approv"   class="btn btn-sm btn-success" onclick="Approve('','A','multiple')"/>
								 <input type="submit"  value="Reject"   class="btn btn-sm btn-danger" onclick="Approve('','R','multiple')" />
							</div>
				</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
 <script>	
function EditMode(contractno,transactionno,layerno,dept,pro) {  
var tableType = "Temp";
       	if(pro==1)
       	{   
       		document.authentication.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&tableType="+tableType+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
		}
		else if(pro==2)
		{
		document.authentication.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&departmentId="+dept+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
		}
		else if(pro==3 || pro==5)
		{
		document.authentication.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&layerno="+layerno+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
		}
		document.authentication.submit();       
	}
function ViewMode(contractno,transactionno,layerNo,dept,pro) { 
    var tableType = "Temp";
	if(pro==1)
      	{
		document.authentication.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&tableType="+tableType+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
	}
	else if(pro==2)
	{
	document.authentication.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&departmentId="+dept+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
    	}
    	else if(pro==3 || pro==5)
	{
	document.authentication.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&layerno="+layerNo+"&productId="+pro+"&premiumDisplay=authenticationDisplay&tableType="+tableType;
    	}
	document.authentication.submit();       
}    


function checkAll()
{
	$("#selectall").prop("checked", true);
    $('.checkItem').prop("checked", true);
}
function uncheckAll()
{
	 $("#selectall").prop("checked", false);
	 $('.checkItem').prop("checked", false);
	 
}

function Approve(requestNo,status,uploadStatus){
	document.authentication.action="${pageContext.request.contextPath}/approvalChangeAuthentication.action?approveStatus="+status+"&requestNo="+requestNo+"&uploadStatus="+uploadStatus;
	document.authentication.submit();
}
	</script>
</html>
