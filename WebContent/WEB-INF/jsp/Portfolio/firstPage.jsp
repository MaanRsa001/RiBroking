<%@ page language="java"   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'CedingView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
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
				   // "stateSave": true,
					responsive: true
				});				 
			} catch(err){}
		} );		
	</script>
<%--<style>
	  #tooltip{
    position:absolute;
    border:1px solid #333;
    background:#f7f5d1;
    padding:2px 5px;
    color:#333;
    display:none;    
    }  
	 </style>	--%>
  </head>
  
<body><br>
<s:form name="FirstPageForm" id="FirstPageForm" action="" theme="simple">

<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
				<div class = "panel-group" id = "accordion">
				   <div class = "panel panel-primary">
				      <div class = "panel-heading">
				         <h4 class = "panel-title">
				            <a data-toggle = "collapse" data-parent = "#accordion" aria-expanded="true" href = "#collapseOne">
				               <i class="glyphicon glyphicon-plus" id="detailsPlus" style="cursor: pointer;" onclick="detailsClick(true)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i>
								<i class="glyphicon glyphicon-minus" id="detailsMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)">&nbsp;<font face="Arial" ><b>Multiple Search</b></font></i> 
				            </a>
				         </h4>
				      </div>
				      
				      <div id="detailsData" style="display: none;" class = "panel-collapse collapse in">
				         <div class = "panel-body">
				         		<div class="textfield">
									<div class="text">
									<s:text name="Offer No" />
									</div>
									<div class="tbox txtB">
									 <s:textfield name="offerNoSearch" id="offerNoSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="Bouquet No" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="bouquetNoSearch" id="bouquetNoSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="Proposal No" />
									</div>
									<div class="tbox txtB">
									 <s:textfield name="proposalNoSearch" id="proposalNoSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="Company Name" />
										
									</div>
									<div class="tbox txtB">
									<s:textfield name="companyNameSearch" id="companyNameSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="Broker Name" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="brokerNameSearch" id="brokerNameSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="label.class" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="departmentNameSearch" id="departmentNameSearch" cssClass="inputBox" />
									</div>
								</div>
								<div class="textfield">
									<div class="text">
									<s:text name="label.subClass" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="subclassSearch" id="subclassSearch" cssClass="inputBox" />
									</div>
								</div>
								 <s:if test='"1".equals(#session.mfrid) '>
								<div class="textfield">
									<div class="text">
									<s:text name="Insured Name" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="insuredNameSearch" id="insuredNameSearch" cssClass="inputBox" />
									</div>
								</div>
								</s:if>
								 <s:if test='"1".equals(#session.mfrid)'>
								 <div class="textfield">
									<div class="text">
									<s:text name="UW Year" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="uwYearSearch" id="uwYearSearch" cssClass="inputBox" />
									</div>
								</div>
								</s:if>
								<s:else>
								 <div class="textfield">
									<div class="text">
									<s:text name="UW Year" />
										
									</div>
									<div class="tbox txtB">
									 <s:textfield name="uwYearSearch1" id="uwYearSearch1" cssClass="inputBox" />
									</div>
								</div>
								</s:else>
								<br class="clear"/>
								<div  align="center">
										<input type="button" class="btn btn-sm btn-info" value="Search" style="cursor: pointer;" onclick="funSearchMode('S')" />
										<input type="button" class="btn btn-sm btn-success" value="Clear Search" style="cursor: pointer;"	onclick="funSearchMode('')" />	 					
								</div>
				         </div>
				      </div>
				      </div>
				   </div>
					<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="tablerow">
									<s:if test='!"".equals(title) || null!=title '>
									       <s:if test='"REJE".equalsIgnoreCase(title)'>
									          <span class="heading"><b>  <s:text name="heading.RejectedList"/></b></span>
										      
										  </s:if>
										   <s:elseif test='"QUO".equalsIgnoreCase(title)'>
										      <span class="heading"><b><s:text name="heading.PendingList"/></b></span>
										      
										  </s:elseif>
										   <s:elseif test='"NTU".equalsIgnoreCase(title)'>
										      <span class="heading"><b><s:text name="heading.NotTakenUpList"/></b></span>
										      
										  </s:elseif>
										   <s:elseif test='"RP".equalsIgnoreCase(title)'>
										      <span class="heading"><b><s:text name="heading.renewalPendingList"/></b></span>
										      
										  </s:elseif>
										  <span class="pullRight"> <input type="button"
															value="New Offer" class="btn btn-sm btn-success"
															onclick="AddNewOffer();" /> </span>
										</s:if>
								</div>
							</div>
							  <s:if test='"3".equals(#session.mfrid) || "5".equals(#session.mfrid)'>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12">
											   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
												<thead>
												<tr>
													<th style="text-align: center; vertical-align: middle;"><s:text name="S.No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Offer No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Bouquet No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Base" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Proposal No" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Company Name" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Broker Name" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="label.class" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="label.subClass" /></th>
													<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="Generated Date" /></th> --%>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Inception Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Expiry Date" /></th>
													<th style="text-align: center; vertical-align: middle;"><s:text name="UW Year" /></th>
													<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="UnderWriter" /></th> --%>
													<s:if test='"RP".equalsIgnoreCase(title)'>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Old Contract No" /></th>
													</s:if>
													<th style="text-align: center; vertical-align: middle;" width="150px">
														<s:text name="Select Option" />
													</th>
													</tr>
													</thead>
													<tbody>
													<s:iterator value="portfolioList" var="list" status="stat">
													<tr>
													<td><s:property value="#stat.count"/></td>
													<td><s:property value="offerNo"/></td>
													<td><s:property value="bouquetNo"/></td>
													<s:if test='!"5".equals(#session.mfrid) '>
													<s:if test='#session.MenuRights.indexOf("EN")!=-1'>
													<td>
													<s:property value="baseLayer"/>
													</td>	
													</s:if>
													</s:if>
													<td><s:property value="proposalNo"/></td>
													<td><s:property value="ceding_Company_Name"/></td>	
													<td><s:property value="brokerName"/></td>	
													<td><s:property value="department_Name"/></td>	
													<td><s:property value="subClass"/></td>
													<%-- <td><s:property value="quote_Gendrated_date"/></td>	 --%>
													<td><s:property value="inception_Date"/></td>	
													<td><s:property value="expiry_Date"/></td>	
													<td><s:property value="uwYear"/></td>
													<%-- <td><s:property value="underwritter"/></td>	 --%>
													<s:if test='"RP".equalsIgnoreCase(#list.title)'>
													<td><s:property value="old_Contract"/></td>
													</s:if>
														
													<td style="text-align: center; vertical-align: middle;">
														<div><s:select list="buttonSelectionList"  id="buttonVal%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="--Select--"  listKey="TYPE" listValue="DETAIL_NAME" cssStyle="width:70%;float:left;"/>
														 <span class="pull-right"><input type="button"  value="Go"   class="btn btn-xs btn-info"  style="cursor: pointer;float:left;" onclick="ButtonAction('<s:property value="proposalNo" />','<s:property value="cedding_company_id" />','<s:property value="#session.mfrid" />','<s:property value="baseLayer" />','<s:property value="departmentId" />','<s:property value="flag" />','<s:property value="amendId" />','<s:property value="layerNo" />','<s:property value="inception_Date" />','<s:property value="ceding_Company_Name" />','<s:property value="brokerName" />','<s:property value="flag" />','<s:property value="#stat.count-1" />')" /></span></div>
													</td>
													
																	
													</tr>
													</s:iterator>
													</tbody>
													</table>
												</div>
											</div>
											</div>
						   </s:if>	
							   <s:else>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
										   <table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
											<thead>
											<tr>
												<th style="text-align: center; vertical-align: middle;"><s:text name="S.No" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Offer No" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Bouquet No" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Base" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Proposal No" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Company Name" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Broker Name" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="label.class" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="label.subClass" /></th>
												<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="Generated Date" /></th> --%>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Inception Date" /></th>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Expiry Date" /></th>
												<s:if test=' "1".equals(#session.mfrid) '>
													<th style="text-align: center; vertical-align: middle;"><s:text name="Insured Name" /></th>
												</s:if>
												<th style="text-align: center; vertical-align: middle;"><s:text name="UW Year" /></th>
												<%-- <th style="text-align: center; vertical-align: middle;"><s:text name="UnderWriter" /></th> --%>
												<s:if test='"RP".equalsIgnoreCase(title)'>
												<th style="text-align: center; vertical-align: middle;"><s:text name="Old Contract No" /></th>
												</s:if>
												
													<th style="text-align: center; vertical-align: middle;" width="150px">
														<s:text name="Select Option" />
													</th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="portfolioList" var="list" status="stat">
												<tr>
												<td><s:property value="#stat.count"/></td>
												<td><s:property value="offerNo"/></td>
												<td><s:property value="bouquetNo"/></td>
												<s:if test='"2".equals(#session.mfrid)'>
													<td><s:property value="baseLayer" /></td>
													<%-- <td>
													<s:if test='"".equals(#list.baseLayer) || null==#list.baseLayer'>
													<input type="button" class="btn btn-sm btn-info" value="CS" style="cursor: pointer;" onclick="funClassMode('<s:property value="proposalNo" />','<s:property value="cedding_company_id" />','<s:property value="proposalNo" />')" />
													</s:if>
													</td> --%>
												</s:if>
												<td><s:property value="proposalNo"/></td>
												<td><s:property value="ceding_Company_Name"/></td>	
												<td><s:property value="brokerName"/></td>	
												<td><s:property value="department_Name"/></td>	
												<td><s:property value="subClass"/></td>	
												<%-- <td><s:property value="quote_Gendrated_date"/></td>	 --%>
												<td><s:property value="inception_Date"/></td>	
												<td><s:property value="expiry_Date"/></td>
												<s:if test=' "1".equals(#session.mfrid) '>
												<td><s:property value="insuredName" /></td>
												</s:if>	
												<td><s:property value="uwYear"/></td>
												<%-- <td><s:property value="underwritter"/></td>	 --%>
												<s:if test='"RP".equalsIgnoreCase(#list.title)'>
												<td><s:property value="old_Contract"/></td>
												</s:if>
												
												
												<td style="text-align: center; vertical-align: middle;">
														<div><s:select list="buttonSelectionList"  id="buttonVal%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="--Select--"  listKey="TYPE" listValue="DETAIL_NAME" cssStyle="width:70%;float:left;"/>
														 <span class="pull-right"><input type="button"  value="Go"   class="btn btn-xs btn-info"  style="cursor: pointer;float:left;" onclick="ButtonAction('<s:property value="proposalNo" />','<s:property value="cedding_company_id" />','<s:property value="#session.mfrid" />','<s:property value="baseLayer" />','<s:property value="departmentId" />','<s:property value="flag" />','<s:property value="amendId" />','<s:property value="layerNo" />','<s:property value="inception_Date" />','<s:property value="ceding_Company_Name" />','<s:property value="brokerName" />','<s:property value="flag" />','<s:property value="#stat.count-1" />')" /></span></div>
													</td>
												</tr>
												</s:iterator>
												</tbody>
												</table>
											</div>
										</div>
										</div>
								
									</s:else>
							</div>
					<s:hidden name="proposal_no" id="proposal_no"/>
					<s:hidden name="proposalNo" id="proposalNo"/>
					<s:hidden name="CustomerId" id="CustomerId"/>
					<s:hidden name="baseLayer" id="baseLayer"/>
					<s:hidden name="mode" id="mode"/>
					<s:hidden name="flag" id="flag"/>
					<s:hidden name="menuId" id="menuId"/>
					<s:hidden name="manufactureID" id="manufactureID"/>
					<s:hidden name="amendId" id="amendId"/>
					<s:hidden name="contractno1" id="contractno1"/>
					<s:hidden name="lay1" id="lay1"/>
					<s:hidden name="multiuserError" id="multiuserError"/>
					<s:hidden name="pro" id="pro" value="%{#session.mfrid}" />
</div>
</div>
</div>
</div>
</div>
</s:form>			 
					 
  </body>
   <script>
     function searchdetails()
     {
      	document.FirstPageForm.action="<%=request.getContextPath()%>/PortfolioDispatchIntAction.do?method=SearchView";
      	document.FirstPageForm.submit();
     }
     function funEditMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	      //document.getElementById("proposal_no").value=proposalno;
	      document.getElementById("proposal_no").value=baseLayer;
	      document.getElementById("CustomerId").value=ceddingcompanyid;
	      document.getElementById("baseLayer").value=baseLayer;
	      document.getElementById("mode").value='edit';
	      if(productId==2){
	      		document.FirstPageForm.action="EditModeRiskDetails.action?departmentId="+deptId;
	      }
	      else if(productId==3 || productId==5) {
			if(baseLayer.length>0){
              document.getElementById("contractno1").value=baseContract;
              document.getElementById("lay1").value='layer';
            }
	      	document.FirstPageForm.action="EditModeXol.action"
	      }else if(productId==4){
	      	document.FirstPageForm.action="EditModeRetro.action"
	      }else if(productId==1){
	      	document.FirstPageForm.action="EditMethodFacultative.action";
	      }
	      document.FirstPageForm.submit();
      }
     
     function funViewMode(proposalno,mfrid,flag,amendId)
     {
     	document.getElementById("amendId").value=amendId;
	    document.getElementById("flag").value=flag;
	    document.getElementById("proposal_no").value=proposalno;
       if(mfrid==2) 
        {
            document.FirstPageForm.action="ViewMethodRiskDetails.action"
	        document.FirstPageForm.submit();
        }
        else if(mfrid==3 ||mfrid==5)
        {
        	document.FirstPageForm.action="ViewMethodXol.action"
	        document.FirstPageForm.submit();
        }
        else if(mfrid==4 )
        {
        	document.FirstPageForm.action="ViewMethodRetro.action"
	        document.FirstPageForm.submit();
        }
        else
        {
	        document.FirstPageForm.action="ViewMethodFacultative.action"
	        document.FirstPageForm.submit();
       
        }
     }
      
     function funLayerMode(ProposalNo,ceddingcompanyId,layerno)
     {
	     // alert(layerno) 
	      document.getElementById("proposal_no").value=ProposalNo;
	      document.getElementById("CustomerId").value=ceddingcompanyId;
	     // document.getElementById("layerNo").value=layerno;
	      document.FirstPageForm.action="LayerEditModeXol.action"
	      document.FirstPageForm.submit();
     }
     function funClassMode(ProposalNo,ceddingcompanyId,ContractNo)
      {
      //document.getElementById("renewal_contract_no").value=ContractNo;
      document.getElementById("proposal_no").value=ProposalNo;
      //document.getElementById("proposalNo").value=proposalno;  
      document.getElementById("CustomerId").value=ceddingcompanyId;
     // document.getElementById("layerNo").value="layer";
      document.FirstPageForm.action="ClassEditModeRiskDetails.action"
      document.FirstPageForm.submit();
      }
    function getSearchBy()
    {
    	document.getElementById("searchValue").value='';
    }
    function getSearch()
	{ 
		var searchType=document.getElementById("searchType").value;
		var searchValue=document.getElementById("searchValue").value;
		document.FirstPageForm.action="commonListPortfolio.action?manufactureID=2&menuId=47";
		document.FirstPageForm.submit();
		
	}
	
	function getAjaxContentPost(strURL, id, params){
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4){
			var response = xmlHttp.responseText;
			if(response.indexOf("Script-Execute", 0)!=-1){
				response = response.replace("Script-Execute", "");
                var ss = document.createElement('script');
                var scr = response;
                ss.text = scr;
                var hh = document.getElementsByTagName('head')[0];
                hh.appendChild(ss);
            }else{
                document.getElementById(id).innerHTML = response;            
            }
			if(id == "CauseOfLossDiv"){
				LoadFromCOL("0");
			}else if(id == "CauseOfLossDetailsDiv"){
				OthersDiv();
			}
		}
   }
	xmlHttp.send(params+"&RandomId="+Math.random());
}
editMode();
	function editMode(){
	var val =document.getElementById("multiuserError").value;
		if('error'==val){
		document.getElementById("multiuserError").value ='';
		alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
		}
	}
if('S'=='<s:property value="searchType"/>'){
detailsClick(true);
}
function detailsClick(val) {
	if(val){
  		document.getElementById('detailsData').style.display='block';		
		document.getElementById('detailsMinus').style.display='block';
		document.getElementById('detailsPlus').style.display='none';
		//document.getElementById('claimOverflow').style.height='45vh';						    
    }else{
    	document.getElementById('detailsData').style.display='none';
		document.getElementById('detailsMinus').style.display='none';
		document.getElementById('detailsPlus').style.display='block';
		//document.getElementById('claimOverflow').style.height='59vh';		    
    }
}
$('#offerNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(1).search(this.value).draw();
} );
$('#bouquetNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(2).search(this.value).draw();
} );
$('#proposalNoSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(4).search(this.value).draw();
} );
$('#companyNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(5).search(this.value).draw();
} );
$('#brokerNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(6).search(this.value).draw();
} );
$('#departmentNameSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(7).search(this.value).draw();
} );
$('#subclassSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(8).search(this.value).draw();
} );
$('#uwYearSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
$('#underwriterSearch').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(10).search(this.value).draw();
} );
$('#underwriterSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );

$('#uwYearSearch1').on( 'keyup', function () {
$('#gridTableMake').DataTable().columns(9).search(this.value).draw();
} );
function funSearchMode(mode){
var flag = document.getElementById("flag").value;
	if(flag!="C"&&flag!="RD"){
	  document.FirstPageForm.action="${pageContext.request.contextPath}/commonListPortfolio.action?SearchType="+mode;
	  document.FirstPageForm.submit();
	  }
	  else{
	  document.FirstPageForm.action="${pageContext.request.contextPath}/InitPortfolio.action?SearchType="+mode;
	  document.FirstPageForm.submit();
	 }
}
function ButtonAction(proposalno,ceddingcompanyid,productId,baseLayer,deptId,flag,amendId,layerNo,inception_Date,ceding_Company_Name,brokerName,flag,row){	
	var sel = document.getElementById("buttonVal"+row).value;
	if("E"==sel){
		funEditMode(proposalno,ceddingcompanyid,productId,baseLayer,'',deptId);
	}
	else if("V"==sel){
		funViewMode(proposalno,productId,flag,amendId);
	}
	else if("PL"==sel){
		funplacingMode(proposalno,amendId,flag,baseLayer);
	}else if("RI"==sel){
		funReinsurerMode(proposalno,amendId,flag,baseLayer);
	}
	
	

}
function funplacingMode(proposalno,mfrid,flag,amendId)
{
	document.getElementById("amendId").value=amendId;
    document.getElementById("flag").value=flag;
    document.getElementById("proposalNo").value=proposalno;
 
       document.FirstPageForm.action="summaryPlacement.action"
       document.FirstPageForm.submit();
  
   
}
function funReinsurerMode(proposalno,mfrid,flag,amendId)
{
	document.getElementById("amendId").value=amendId;
    document.getElementById("flag").value=flag;
    document.getElementById("proposalNo").value=proposalno;
 
       document.FirstPageForm.action="initPlacement.action"
       document.FirstPageForm.submit();
  
   
}
function AddNewOffer() {
	var pro = document.getElementById("pro").value;
		if(pro==1)
        {
        document.FirstPageForm.action="${pageContext.request.contextPath}/InitMethodFacultative.action";
        }
        if(pro==2)
        {
        document.FirstPageForm.action="${pageContext.request.contextPath}/InitRiskDetails.action";
        }
        if(pro==3 || pro==5)
        {
        document.FirstPageForm.action="${pageContext.request.contextPath}/InitXol.action";
        }
        if(pro==4)
        {
        document.FirstPageForm.action="${pageContext.request.contextPath}/InitRetro.action";
        }
		document.FirstPageForm.submit();
	}
   </script>
</html>
