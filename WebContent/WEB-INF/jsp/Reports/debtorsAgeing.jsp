<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
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
	 $(function() {
		$( "#end_date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#start_date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	  </script>	
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="" name="debtorsform" theme="simple" action="/ReportsAction.do"	method="post">										
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
												
						<s:if test='"init".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.DebtorsReports" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Reports.Product" />
												</div>
												<div class="tbox">
													<s:select list="productList" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" cssClass="inputBoxS" name="productId" id="productId" onchange="getCedingCompany1();getch(this.value);"	headerKey="-1" headerValue="---ALL---" />
												</div>
											</div>
											<div class="textfield" id="pr2">
											</div>
											
											
											<div class="textfield">
												<div class="text txtB">
													<s:text name="Reports.Broker" />
												</div>
												<div class="tbox">
													<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId"	id="brokerId" onchange="getCedingCompany1()" headerKey="-1" headerValue="---ALL---" />
													<%--<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId"	id="brokerId" headerKey="-1" headerValue="---Select---"  onchange="getCedent(this.value);"/>--%>
												</div>
											</div>
											<div class="textfield" id="ceding" >
												<div class="text txtB">
													<s:text name="Reports.CedingCompany" />
												</div>
												<div class="tbox" id="reportCeding">
													<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" headerKey="-1"	headerValue="---ALL---" />
												</div>
											</div>
											 <div class="textfield">
												<div class="text txtB">
													<s:text name="Reports.PRCL" />
												</div>
												<div class="tbox">
													<s:checkboxlist list="#{'PR':'Premium','CL':'Claim','PT':'Payment','RT':'Receipt','ALL':'ALL'}" name="docType" value='docType==null?"ALL":docType' onclick="checkDE(this.value);"></s:checkboxlist>
												</div>
											</div>
											<br class="clear" />
											<table width="100%" class="table table-bordered">
													
											<tbody>												
												<tr >
												<td width="20%">
												<b><s:text name="Report.DebtorsReportDate" /></b>
												</td>
												<td width="40%">
												<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
												</td>
												
												<td width="40%">
												</td>
												
												
											</tr>
											
											<tr>
											<td width="20%">
												<b><s:text name="Ageing Group" /></b>
												</td>
												<td width="40%">
												
												</td>
												
												<td width="40%">
												</td>
											</tr>
											
											<tr align="center">
												<td width="20%">
													<b><s:text name="Group No" /></b>
												</td>
												<td width="40%">
												<b><s:text name="From" /></b>
												</td>
												
												<td width="40%">
												<b><s:text name="To" /></b>
												</td>
											</tr>
											<tr>
											<td width="20%">
												<s:text name="1" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom" id="debFrom" cssStyle="text-align: right;" />
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo" id="debTo" cssStyle="text-align: right;" onchange="getNext('debTo','debFrom1');"/>
												</td>
											</tr>
											<tr>
											<td width="20%">
												<s:text name="2" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom1" id="debFrom1" cssStyle="text-align: right;" readonly="true"/>
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo1" id="debTo1" cssStyle="text-align: right;" onchange="getNext('debTo1','debFrom2');"/>
												</td>
											</tr>
											<tr>
											<td width="20%">
												<s:text name="3" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom2" id="debFrom2" cssStyle="text-align: right;" readonly="true"/>
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo2" id="debTo2" cssStyle="text-align: right;" onchange="getNext('debTo2','debFrom3');"/>
												</td>
											</tr>
											<tr>
											<td width="20%">
												<s:text name="4" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom3" id="debFrom3" cssStyle="text-align: right;" readonly="true"/>
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo3" id="debTo3" cssStyle="text-align: right;" onchange="getNext('debTo3','debFrom4');"/>
												</td>
											</tr><tr>
											<td width="20%">
												<s:text name="5" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom4" id="debFrom4" cssStyle="text-align: right;" readonly="true"/>
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo4" id="debTo4" cssStyle="text-align: right;" onchange="getNext('debTo4','debFrom5');"/>
												</td>
												
											</tr>
											<tr><td width="20%">
												<s:text name="6" />
												</td>
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debFrom5" id="debFrom5" cssStyle="text-align: right;" readonly="true"/>
												</td>
												
												<td width="40%">
												<s:textfield cssClass="inputBox" name="debTo5" id="debTo5" cssStyle="text-align: right;" />
												</td>
												</tr>
											</tbody>
											</table>									
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<input type="button" value="Detail Report" class="btn btn-sm btn-success" onclick="getReports(this.form,'1','11');"/>
								<input type="button" value="Summary" class="btn btn-sm btn-success" onclick="getReports(this.form,'2','54');"/>
							</div>							
						</div>
						</s:if>
						
						<s:if test='"DebtorsAgeing".equals(display)'>
						<div class="tablerow">
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.DebtorsReports" />										
									</div>
									<div class="panel-body">
										<div class="boxcontent">											
											<display:table name="ReportsList" pagesize="20"	requestURI="" class="table table-bordered" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
													<%--List columnInfo=(List)request.getAttribute("ColumnInfo"); 
														try{
															for(int i=0;i<columnInfo.size();i++){
																Map columnMap=(Map)columnInfo.get(i);
																String Key=((String)columnMap.get("EXCEL_HEADER_NAME"));
																String value=((String)columnMap.get("FIELD_NAME"));
													--%>
												<display:column sortable="true" style="text-align:center;" title="<%--=Key--%>" property="<%--=value--%>" ></display:column>
												<%--}}catch(Exception e){e.printStackTrace();}--%>	
											</display:table>											
											<br class="clear"/>
										</div>
									</div>									
								</div>
							</div>
							
							<div class="boxcontent" align="center">
								<input type="button" value="Back" class="btn btn-sm btn-danger"	onclick="getBack(this.form);"/> &nbsp;&nbsp;&nbsp; 
								<input type="button" value="Save" class="btn btn-sm btn-success" onclick="getReportsDownload(this.form);"/>
							</div>							
						</div>
						</s:if>
						
					</div>		
					<!--<s:hidden name="productId" />										
					-->									
					<s:hidden name="reportType" id="reportType" />
					<s:hidden name="typeId" id="typeId"/>
					<s:hidden name="display" />
					<s:hidden name="type" id="type"/>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" >    
	
function getReports(form,val,val1){
	try{
		document.getElementById('type').value=val;
		document.getElementById('typeId').value=val1;
		document.getElementById('reportType').value='Html';
		form.action="${pageContext.request.contextPath}/getRlistReports";
		form.submit();
		}catch(err){alert(err)}
	}
function getBack(form)
{
	form.action='${pageContext.request.contextPath}/commonListPortfolio&manufactureID='+<%=session.getAttribute("mfrid")%>+'&Department='+<%=session.getAttribute("DepartmentId")%>;
    /*form.action="${pageContext.request.contextPath}/commonListPortfolio&manufactureID=<bean:write name="mfrid"/>&Department=<bean:write name="DepartmentId"/>";*/
	form.action="${pageContext.request.contextPath}/getInitReports";
	form.submit();
}
function getReportsDownload(form)
{
	document.getElementById('reportType').value='Excel';
	form.action="${pageContext.request.contextPath}/getRlistReports";
	form.submit();		
}
function getNext(to,from){

var val1=document.getElementById(to).value;
if(val1==null || val1==''){
document.getElementById(from).value='';
}else{
val1=val1.replace(new RegExp(',', 'g'),'');
document.getElementById(from).value=parseFloat(val1)+1;
}
}
function checkDE(id){
	if(id=='ALL'){
	 	document.getElementById("docType-1").checked=false;
	 	document.getElementById("docType-2").checked=false;
	 	document.getElementById("docType-3").checked=false;
	 	document.getElementById("docType-4").checked=false;
	}else {
		document.getElementById("docType-5").checked=false;
		}
	}
</script>	
</body>
</html>
