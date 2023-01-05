<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 

<!DOCTYPE HTML>
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
<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script type="text/javascript">
jQuery(function ($) {
		try {
		var table = $('#gridTableMake').dataTable( {
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"order": [[ 0, "desc" ]],
			"columnDefs": [ {
	          "targets": 'no-sort',
	          "orderable": false
		    } ],
			responsive: true
		});	
		} catch(err){}
	} );
jQuery(function ($) {
	try {
	var table = $('#gridTableMake1').dataTable( {
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		"order": [[ 0, "desc" ]],
		"columnDefs": [ {
          "targets": 'no-sort',
          "orderable": false
	    } ],
		responsive: true
	});	
	} catch(err){}
} );
</script>
	<style>
	/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
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
       
		function EditMode(contractno,transactionno,layerno,dept,sectionNo,tableType) {  
			var pro = document.getElementById("prod").value;
			if(pro==''){
			pro = document.getElementById("productId").value;
			}
        	if(pro==1)
        	{   
        	if("Temp"==tableType){
        		document.premium.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&size=list&tableType="+tableType;
        	}else{
				document.premium.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&size=list&tableType="+tableType;
			}
			}
			else if(pro==2)
			{
			if("Temp"==tableType){
			document.premium.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&size=list&departmentId="+dept+"&sectionNo="+sectionNo+"&tableType="+tableType;
			}else{
			document.premium.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&size=list&departmentId="+dept+"&sectionNo="+sectionNo+"&tableType="+tableType;
			}
			}
			else if(pro==3 || pro==5)
			{
			if("Temp"==tableType){
			document.premium.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=edit&contNo="+contractno+"&requestNo="+transactionno+"&layerno="+layerno+"&size=list&tableType="+tableType;
			}
			else{
			document.premium.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=edit&contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layerno+"&size=list&tableType="+tableType;
			}
			}
			document.premium.submit();       
		
}
		function newPremium(contNo,dept,sectionNo) {  
			//var contNo=document.getElementById("contNo").value; 
			var pro = document.getElementById("prod").value;
			if(pro==''){
			pro = document.getElementById("productId").value;
			}
			if(pro==1)
        	{       
			document.premium.action="${pageContext.request.contextPath}/editPremiumFaculPremium.do?mode=add&contNo="+contNo+"&size=list&tableType=Temp";
			}
			else if(pro==2)
			{
			document.premium.action="${pageContext.request.contextPath}/editPremiumProportionPremium.do?mode=add&contNo="+contNo+"&size=list&departmentId="+dept+"&sectionNo="+sectionNo+"&tableType=Temp";			
			}
			else if(pro==3 || pro==5)
			{
			document.premium.action="${pageContext.request.contextPath}/editPremiumXolPremium.do?mode=add&contNo="+contNo+"&layerno=<s:property value='layerno'/>&size=list&tableType=Temp";			
			}
			document.premium.submit();
		} 
		
		function ViewMode(contractno,transactionno,layerNo,dept,sectionNo,tableType) { 
		    var pro = document.getElementById("prod").value;
		    if(pro==''){
			pro = document.getElementById("productId").value;
			}
			if(pro==1)
        	{
        	if("Temp"==tableType){
				document.premium.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&tableType="+tableType;
			}else{
				document.premium.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&tableType="+tableType;
			}
			}
			else if(pro==2)
			{
			if("Temp"==tableType){
			document.premium.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&departmentId="+dept+"&sectionNo="+sectionNo+"&tableType="+tableType;
			}else{
			document.premium.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&departmentId="+dept+"&sectionNo="+sectionNo+"&tableType="+tableType;
	     	}
	     	}
	     	else if(pro==3 || pro==5)
			{
			if("Temp"==tableType){
			document.premium.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&requestNo="+transactionno+"&layerno=<s:property value='layerno'/>&tableType="+tableType;
			}else{
			document.premium.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno=<s:property value='layerno'/>&tableType="+tableType;
	     	}
	     	}
			document.premium.submit();       
		}     
		function DN(contractno,transactionno,layerNo,dept,sectionNo) {  
		    var pro = document.getElementById("prod").value;
		    if(pro==''){
			pro = document.getElementById("productId").value;
			}
			if(pro==1)
        	{
			document.premium.action="${pageContext.request.contextPath}/premiumViewFaculPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&type=DN&tableType=Main";
			}
			else if(pro==2)
			{
			document.premium.action="${pageContext.request.contextPath}/premiumViewProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&type=DN&tableType=Main&departmentId="+dept+"&sectionNo="+sectionNo;
	     	}
	     	else if(pro==3 || pro==5)
			{
			document.premium.action="${pageContext.request.contextPath}/premiumViewXolPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno=<s:property value='layerno'/>&type=DN&tableType=Main";
	     	}
			document.premium.submit();       
		}
		function deletedata(contractno,transactionno,layeNo,pid){
		if(pid==1){
			document.premium.action="${pageContext.request.contextPath}/deletePremiumFaculPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layeNo;
		}
		else if(pid==2){
			document.premium.action="${pageContext.request.contextPath}/deletePremiumProportionPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layeNo;
		}
		else if(pid==3 || pro==5){
			document.premium.action="${pageContext.request.contextPath}/deletePremiumXolPremium.do?contNo="+contractno+"&transactionNo="+transactionno+"&layerno="+layeNo;
		}
		document.premium.submit();  
		}
		function BackFunction() {
			 var menuId=document.getElementById("menuId").value;  
  			 document.location='<%=request.getContextPath()%>/InitPortfolio.action?flag=C&menuId='+menuId;
		}
		
		function Back() {
			 document.premium.action="${pageContext.request.contextPath}/premiumListPremiumMaster.action?type=premium";
      		 document.premium.submit();
		}
		function getDocList(proposalId,contNo,layerNo,tranNo,compName,brokeeName,type){			
			document.getElementById("proposalno").value=proposalId;
			document.getElementById("contarctno").value=contNo;
			//document.getElementById("layerNo").value=layerNo;
			document.getElementById("tranNo").value=tranNo;
			document.getElementById("companyName").value=compName;
			document.getElementById("brokerName").value=brokeeName;
			//document.premium.type.value=type;
			document.premium.action="${pageContext.request.contextPath}/documentUpload.action?type="+type+"&layerNo="+layerNo;
			document.premium.submit();
		}  
	</script>
</head>  
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<%--<s:form name="premium" action="/PremiumInit" theme="simple">--%>
					<s:form name="premium"  theme="simple">
					<div class="table1">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.PREMIUMDETAILS" />				
										<s:if test='#session.MenuRights.indexOf("PN")!=-1 && !"Y".equals(ceaseStatus)'>			
											<span class="pullRight">
											<input type="button"  value="New Premium"   class="btn btn-sm btn-warning" onClick="newPremium('<s:property value="contNo"/>','<s:property value="departmentId"/>','<s:property value="sectionNo"/>')" />
											</span>
										</s:if>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.ContractNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="contNo"/>
												</div>
											</div>
											<s:if test="layerno!=0 && layerno!=null ">
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.LayerNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="layerno"/>
												</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text txtB">
													<s:if test='"5".equals(productId)'>
														<s:text name="label.leadretro" />
													</s:if>
													<s:else>
													<s:text name="Heding.CedingCompany" />
													</s:else>
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="ceding_Company_Name"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:if test='"5".equals(productId)'>
														<s:text name="label.leadbroker" />
													</s:if>
													<s:else>
													<s:text name="broker.name" />
													</s:else>
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="brokername"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.DepartmentName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="department_Name"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Premium.UnderwritingYear" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="uwYear"/>
												</div>
											</div>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					<s:if test="preList.size()>0 && preTempList.size()>0">	
						<div class="panel panel-primary">
							<ul class="nav nav-tabs" style="color:blue">
								<s:if test="preList.size()>0">
									<li  class="active"><a data-toggle="tab" href="#menu1">Approved Transaction</a></li>
								</s:if>
								<s:if test="preTempList.size()>0">
								  	<li><a data-toggle="tab" href="#home">UnApproved Transaction</a></li>
								 </s:if>
							</ul>
						</s:if>
						<s:if test=" preTempList.size()>0 &&  preList.size()>0">
						<div class="panel-body">
						<div class="tab-content">
						</s:if>
						<s:if test=" preTempList.size()>0">
						<s:if test=" preList.size()>0">
						  <div id="home" class="tab-pane fade">
						  </s:if>
						  <s:else>
						  <div id="home" class="tab-pane fade in active">
						  </s:else>
						  
						    <div class="panel panel-primary">
								<div class="panel-heading">
									UnApproved Transaction
								</div>
								<div class="panel-body">
								<div class="tablerow">
								<div class="boxcontent">
									<div class="row">
										<div class="col-xs-12">
											<table class="display responsive no-wrap" id="gridTableMake1" width="100%" cellspacing="0">
												<thead>
													<tr>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Request No" /></th>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction Date" /></th>
														<s:if test='!"5".equals(#session.mfrid)'>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Statement Date" /></th>
														</s:if>
														<s:if test='"2".equals(#session.mfrid)'>
															<th style="text-align: center; vertical-align: middle;" > <s:text name="Account Period" /> </th>
															<th style="text-align: center; vertical-align: middle;" > <s:text name="Account Period Date" /> </th>
														</s:if>
														<s:else>
															<th style="text-align: center; vertical-align: middle;"><s:text name="Installment Date" /></th>	
														</s:else>
														<s:if test='#session.MenuRights.indexOf("PE")!=-1  '>		
														<th style="text-align: center; vertical-align: middle;" > <s:text name="Edit" /> </th>
														</s:if>	
														<s:if test='#session.MenuRights.indexOf("PV")!=-1'>		       
												       <th style="text-align: center; vertical-align: middle;" > <s:text name="View" /> </th>
												        </s:if>
														
												</thead>
												<tbody>
													<s:iterator value="preTempList" var="list" status="stat">
														<tr>
															
															<td><s:property value="requestNo"/></td>
															<td><s:property value="transDate"/></td>
															<s:if test='!"5".equals(#session.mfrid)'>
															<td>
																<s:property value="statementDate"/>
															</td>
															</s:if>	
															<s:if test='"2".equals(#session.mfrid)'>
															<td>
																<s:property value="account_Period" />
															</td>
															<td>
																<s:property value="accountPeriodDate" />
															</td>
															</s:if>
															<s:else>
															<td>
																<s:property value="account_Period" />
															</td>
															</s:else>
															<s:if test='#session.MenuRights.indexOf("PE")!=-1  '>		
															<td align="center">
																<input type="button"  value="Edit"   class="btn btn-xs btn-info"  style="cursor: pointer;" onclick="EditMode('<s:property value="contNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="departmentId" />','<s:property value="sectionNo" />','Temp')" />
															</td>
															</s:if>	
															<s:if test='#session.MenuRights.indexOf("PV")!=-1'>		       
														       <td align="center">
														       	<input type="button"  value="View"   class="btn btn-xs btn-info"  style="cursor: pointer;" onclick="ViewMode('<s:property value="contNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="departmentId" />','<s:property value="sectionNo" />','Temp')" />
														        </td>
													        </s:if>
														
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
								</div>
							</div>
						  </div>
						 </s:if>
						 <s:if test=" preList.size()>0">
						  <div id="menu1" class="tab-pane fade in active">
						    <div class="panel panel-primary">
									<div class="panel-heading">
										Approved Transaction
									</div>
									<div class="panel-body">
							<div class="tablerow">
								<div class="boxcontent">
									<div class="row">
										<div class="col-xs-12">
											<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
												<thead>
													<tr>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction No" /></th>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Transaction Date" /></th>
														<s:if test='!"5".equals(#session.mfrid)'>
														<th style="text-align: center; vertical-align: middle;"><s:text name="Statement Date" /></th>
														</s:if>
														<s:if test='"2".equals(#session.mfrid)'>
															<th style="text-align: center; vertical-align: middle;" > <s:text name="Account Period" /> </th>
															<th style="text-align: center; vertical-align: middle;" > <s:text name="Account Period Date" /> </th>
														</s:if>
														<s:else>
															<th style="text-align: center; vertical-align: middle;"><s:text name="Installment Date" /></th>	
														</s:else>
														<s:if test='#session.MenuRights.indexOf("PE")!=-1  '>		
															<th style="text-align: center; vertical-align: middle;" > <s:text name="Edit" /> </th>
														</s:if>	
														<s:if test='#session.MenuRights.indexOf("PV")!=-1'>		       
												       		<th style="text-align: center; vertical-align: middle;" > <s:text name="View" /> </th>
												        </s:if>
												         <s:if test='!"5".equals(productId)'>
												         <th style="text-align: center; vertical-align: middle;" > <s:text name="DN/CN" /> </th>
												         </s:if>
												         <th style="text-align: center; vertical-align: middle;" > <s:text name="Document" /> </th>
														
												</thead>
												<tbody>
													<s:iterator value="preList" var="list" status="stat">
														<tr>
															
															<td><s:property value="transactionNo"/></td>
															<td><s:property value="transDate"/></td>
															<s:if test='!"5".equals(#session.mfrid)'>
															<td>
																<s:property value="statementDate"/>
															</td>
															</s:if>	
															<s:if test='"2".equals(#session.mfrid)'>
															<td>
																<s:property value="account_Period" />
															</td>
															<td>
																<s:property value="accountPeriodDate" />
															</td>
															</s:if>
															<s:else>
															<td>
																<s:property value="account_Period" />
															</td>
															</s:else>
															<s:if test='#session.MenuRights.indexOf("PE")!=-1'>		
																<td align="center">
																<s:if test='!"N".equalsIgnoreCase(allocatedYN) && "Y".equalsIgnoreCase(transOpenperiodStatus) &&  !"Y".equals(ceaseStatus) && ("".equalsIgnoreCase(transDropDownVal))'>
																	<a title="Edit" style="cursor: pointer;" onClick="EditMode('<s:property value="contNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="departmentId"/>','<s:property value="sectionNo"/>','Main')">Edit </a>
																	</s:if>																				
																</td>
															</s:if>	
															<s:if test='#session.MenuRights.indexOf("PV")!=-1'>		       
														        <td align="center">
																	<a title="View" style="cursor: pointer;" onClick="ViewMode('<s:property value="contNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="departmentId"/>','<s:property value="sectionNo"/>','Main')">View</a>
														       </td>
													        </s:if>
													       <s:if test='!"5".equals(productId)'>
														        <td align="center">
																 	<a title="DN/CN" style="cursor: pointer;" onClick="DN('<s:property value="contNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="departmentId"/>','<s:property value="sectionNo"/>')">DN/CN</a>
														       </td>
													         </s:if>
													       <td align="center">
																 <a href="#" class="" title="Document" onclick="getDocList('<s:property value="proposal_No" />','<s:property value="contNo" />','<s:property value="transactionNo" />','<s:property value="layerno" />','<s:property value="ceding_Company_Name" />','<s:property value="broker" />','premium');"> 
																		 <img border='0' src="${pageContext.request.contextPath}/images/icon_view_schedule.gif" alt="Document Details" width="12" height="17">
																 </a>
															</td>
															<%-- <s:if test='#session.MenuRights.indexOf("PE")!=-1  '>		
															<td align="center">
																<input type="button"  value="Edit"   class="btn btn-xs btn-info"  style="cursor: pointer;" onclick="EditMode('<s:property value="contNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="departmentId" />')" />
															</td>
															</s:if>	
															<s:if test='#session.MenuRights.indexOf("PV")!=-1'>		       
														       <td align="center">
														       	<input type="button"  value="View"   class="btn btn-xs btn-info"  style="cursor: pointer;" onclick="ViewMode('<s:property value="contNo" />','<s:property value="requestNo" />','<s:property value="layerno" />','<s:property value="departmentId" />')" />
														        </td>
													        </s:if> --%>
														
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
										
										</div>
									</div>
								</div>
							</div>
						  </div>
						 </s:if>
						<s:if test=" preTempList.size()>0 &&  preList.size()>0">
						</div>
						</div>
						
						</div>
						</s:if>
						</div>
						<div class="tablerow">		

							<div class="boxcontent" align="center">
							<s:if test='"premiumDisplay".equals(premiumDisplay) || "premiumDisplay".equals(premiumMasterMode)'>
							<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="Back()"/>								
							</s:if>
							<s:else>
								<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="BackFunction()"/>								
							</s:else>
							</div>
						</div>
						<s:hidden  name="prod" id="prod" value="%{#session.mfrid}"/>
						<s:hidden name="contarctno" id="contarctno"/>
						<!--<s:hidden name="contNo" id="contNo"/>
						<s:hidden name="transactionNo" id="transactionNo"/>
						<s:hidden name="layerNo" id="layerNo"/>
						-->
						<s:hidden name="proposalno" id="proposalno"/>
						<s:hidden name="proposal_No" id="proposal_No"/>
						<s:hidden name="tranNo" id="tranNo"/>
						<s:hidden name="moduleType" value="PR"/>
						<s:hidden name="companyName" id="companyName"/>
						<s:hidden name="brokerName" id="brokerName"/>
						<s:hidden name="menuId" id="menuId"/>
						<s:hidden name="productId" id="productId"/>
						<s:hidden name="premiumMasterMode" id="premiumMasterMode"/>	
						<s:hidden name="multiuserError" id="multiuserError"/>
					</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}
function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}
	</script>	
</body>
</html>
