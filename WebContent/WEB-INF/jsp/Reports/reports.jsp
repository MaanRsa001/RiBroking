<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <style type="text/css">
            .DECIMAL, .NUMBER{
                text-align:right;
            }
            .JCLRgrips>.JCLRgrip{
                z-index: auto;
            }
        </style>
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


      
	</head>
	<body>
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
				<div class="table1"	style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form id="" name="claims" theme="simple"	action="/ReportsAction.do" method="post">
								<div class="table2" >
									<div class="tablerow" id="err" style="display:inline">
										<span style="color: red;"><s:actionerror />
										</span>
									</div>
									<s:if test='"init".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.Product" />
																</div>
																<div class="tbox">
																	<s:select list="productList" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" cssClass="inputBoxS" name="productId" id="productId" onchange="getCedingCompany1();getch(this.value);"	headerKey="-1" headerValue="---Select---" />
																</div>
															</div>
															<div class="textfield" id="pr2">
															</div>
															<div class="textfield" style="display: none;" id="pr1">
																<div class="text txtB">
																	<s:text name="report.dept" />
																</div>
																<div class="tbox">
																	<s:select list="deptName" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" cssClass="inputBoxS" name="dept" id="dept" onchange="getCedingCompany()" headerKey="-1" headerValue="---Select---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.LoginId" />
																</div>
																<div class="tbox">
																	<s:select list="UserList" listKey="LOGIN_ID" listValue="LOGIN_NAME" cssClass="inputBoxS" name="loginId" id="loginId" headerKey="-1"	headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.Broker" />
																</div>
																<div class="tbox">
																	<%--<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId"	id="brokerId" onchange="getCedingCompany1()" headerKey="-1" headerValue="---ALL---" />--%>
																	<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId"	id="brokerId" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.CedingCompany" />
																</div>
																<div class="tbox" id="reportCeding">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" headerKey="-1"	headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.UWYear" />
																</div>
																<div class="tbox">
																	<s:select list="yearList" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" name="uwYear" id="uwYear" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<s:if test='"4".equals(typeId)'>
															<%-- <div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.StartDateClaim" />
																</div>
																<div class="tbox">																		
																	<s:hidden name="start_date"/>
																	<s:textfield cssClass="inputBox" name="start_date" disabled="true"></s:textfield>
																</div>
															</div>--%>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.EndDateClaim" />
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />	
																</div>
															</div>
															</s:if>
															<s:else>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.StartDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.EndDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															</s:else>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
									</s:if>
									<s:if test='"initTrans".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.transaction" />
														<s:text name="Heading.Reports" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.CedingCompany" />
																</div>
																<div class="tbox" id="reportCeding">
																<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" headerKey="-1"	headerValue="---ALL---" /></div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="BrokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId"	id="brokerId" onchange="getCedingCompany1()" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.UWYear" />
																</div>
																<div class="tbox">
																	<s:select list="yearList" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" name="uwYear" id="uwYear" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.Contract" />
																</div>
																<div class="tbox">
																<s:radio name="test" id="test" list="#{'ALL':'ALL','S':'Selected'}" value="test==null?'ALL':test"   onclick="contractScript(this.value)"/>
																</div>
															</div>
															<div class="textfield" id="endtlabel" style="display:inline;">
																<div class="text txtB">
																	<s:text name="Reports.ContractNO" />
																</div>
																	<div class="tbox">
																	<s:textfield name="contractNo" cssClass="inputBoxS"/>
																</div>
																</div>								
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Premium.Transaction" />
																	<s:text name="Reports.StartDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Premium.Transaction" />
																	<s:text name="Reports.EndDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<br class="clear">
															<div class="textfield" style="width: 100%">
																<div class="text txtB" style="width: 25%">
																	<s:text name="Reports.DocumentType" />
																</div>
																<div class="tbox">
																<s:checkboxlist list="#{'PR':'Premium','CL':'Claim','PT':'Payment','RT':'Receipt','ALL':'ALL'}" name="docType" value='docType==null?"ALL":docType' onclick="checkDE(this.value);"></s:checkboxlist>
																</div>
															</div>
															<br class="clear">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Show All Fields" />
																</div>
																<div class="tbox">
																<s:radio name="showAllFields" id="test" list="#{'Y':'Yes','N':'No'}" value='showAllFields==null?"Y":showAllFields'/>
																</div>
															</div>
															<br class="clear"/>
														</div>
													</div>
												</div>								
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="getReports(this.form);"/>
											</div>
										</div>
										<s:hidden name="productId"/>
									</s:if>
									<s:if test='"pendingOffer".equals(display)'>
										<div class="tablerow">
										<%--	<div class="boxcontent">
												 <div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="proMes" />
													</div>
													  <div class="panel-body" >
														<div class="boxcontent"> --%>
															<display:table name="ReportsList"  pagesize="10" requestURI="" style="" class="footable" uid="row" id="record" export="false">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement"	value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<s:iterator value="ColumnInfo" var="column"	status="stat">
																	<display:column  class='${column.DATA_TYPE}' title='${column.EXCEL_HEADER_NAME}' property="${column.FIELD_NAME}" style="width: 10%;text-align: left;word-wrap:break-word;"/>
																</s:iterator>
																<display:setProperty name="export.excel" value="true" />
																<display:setProperty name="export.excel.filename" value="Reports.xls" />
																<display:setProperty name="export.csv" value="false" />
																<display:setProperty name="export.xml" value="false" />
																<display:setProperty name="export.pdf" value="false" />
															</display:table>
													<%-- 	</div>
													</div>
												</div>
											</div>--%>
											<br class="clear"/>
											<div class="boxcontent" align="center">
											<a  class="btn btn-sm btn-danger" onclick="getBack(this.form);"  >Back</a>
												<!-- <input type="button" value="Back" class="btn btn-sm btn-danger" onclick="getBack(this.form);" /> -->
												
												<s:if test='"2".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:if>
												<s:elseif test='"3".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"4".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"9".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"11".equals(typeId)  || "54".equals(typeId) || "36".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"19".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"21".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"23".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"24".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test='"45".equals(typeId)'>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Save</a>
												</s:elseif>
												<s:elseif test=' "25".equals(typeId)'><%--"24".equals(typeId) || --%>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getReportsDownload(this.form);" >Save</a>
												</s:elseif>
												
												<s:else>
												&nbsp;&nbsp;&nbsp;
												<a  class="btn btn-sm btn-success" onclick="getReportsDownload(this.form);" >Save</a>
												</s:else>
											</div>
										</div>
										<s:hidden name="productId"/>
										<s:hidden name="loginId"/>
										<s:hidden name="brokerId"/>
										<s:hidden name="cedingId"/>
										<s:hidden name="uwYear"/>
										<s:hidden name="start_date"/>
										<s:hidden name="end_date"/>
										<s:hidden name="dept"/>
										<s:hidden name="settlementType" />
										<s:hidden name="allocateStatus" />
										<s:hidden name="test"/>			
										<s:hidden name="docType"/>
										<s:hidden name="contractNo"/>
										<s:hidden name="showAllFields"/>
										<s:hidden name="payrecType"/>
										<s:hidden name="transactionType"/>
										<s:hidden name="treatyMainClass"/>
										<s:hidden name="treatyType"/>
										<s:hidden name="debFrom"/>
										<s:hidden name="debTo"/>
										<s:hidden name="debFrom1"/>
										<s:hidden name="debTo1"/>
										<s:hidden name="debFrom2"/>
										<s:hidden name="debTo2"/>
										<s:hidden name="debFrom3"/>
										<s:hidden name="debTo3"/>
										<s:hidden name="debFrom4"/>
										<s:hidden name="debTo4"/>
										<s:hidden name="debFrom5"/>
										<s:hidden name="debTo5"/>
										<s:hidden name="type"/>	
										<s:hidden name="allocationYN"/>			
									</s:if>
									<s:if
										test='"inwardRetro".equals(display) || "retroInward".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:if test='"1".equals(productId)'>
															<s:text name="Heading.facultative" />
														</s:if>
														<s:if test='"2".equals(productId)'>
															<s:text name="Heading.treaty" />
														</s:if>
														<s:if test='"3".equals(productId)'>
															<s:text name="Heading.xol" />
														</s:if>
														<s:if test='"4".equals(productId)'>
															<s:text name="Heading.reports.retro" />
														</s:if>
														<s:if test='"5".equals(productId)'>
															<s:text name="Heading.reports.retroxol" />
														</s:if>
														<s:text name="Heading.retroPolicyDetails" />
														<s:text name="Heading.Reports" />
													</div>
													<div class="panel-body" style="overflow: auto;">
														<div class="boxcontent">
															<display:table name="ReportsList" pagesize="20"	requestURI="" class="footable" uid="row" id="record">
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
																<display:column sortable="true" style="text-align:center;" title="<%--=Key--%>" property="<%--=value--%>" />
																<%--}}catch(Exception e){e.printStackTrace();}--%>
															</display:table>
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent">
												<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="getBack(this.form);" />
												&nbsp;&nbsp;&nbsp;
												<input type="button" value="Save" class="btn btn-sm btn-success" onclick="getReportsDownload(this.form);" />
											</div>
										</div>
									</s:if>

									<s:if
										test='"transMaster".equals(display) || "retroInward".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:text name="Heading.transaction" />
														<s:text name="Heading.Reports" />
													</div>
													<div class="panel-body" style="overflow: auto;">
														<div class="boxcontent">
															<display:table name="ReportsList" pagesize="20"	requestURI="" class="footable" uid="row" id="record">
																<display:setProperty name="paging.banner.one_item_found" value="" />
																<display:setProperty name="paging.banner.one_items_found" value="" />
																<display:setProperty name="paging.banner.all_items_found" value="" />
																<display:setProperty name="paging.banner.some_items_found" value="" />
																<display:setProperty name="paging.banner.placement"	value="bottom" />
																<display:setProperty name="paging.banner.onepage" value="" />
																<%--List columnInfo=(List)request.getAttribute("ColumnInfo"); 
			  										try{
														for(int i=0;i<columnInfo.size();i++){
															Map columnMap=(Map)columnInfo.get(i);
															String Key=((String)columnMap.get("EXCEL_HEADER_NAME"));
															String value=((String)columnMap.get("FIELD_NAME"));
												--%>
																<display:column sortable="true" style="text-align:center;" title="<%--=Key--%>" property="<%--=value--%>" />
																<%--}}catch(Exception e){e.printStackTrace();}--%>
															</display:table>
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent">
												<input type="button" value="Back"
													class="btn btn-sm btn-danger" onclick="getBack(this.form);" />
												&nbsp;&nbsp;&nbsp;
												<input type="button" value="Save" class="btn btn-sm btn-success" onclick="getReportsDownload(this.form);" />
											</div>
										</div>
									</s:if>
									<s:if test='"allocationReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.StartDate" /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />																					
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.EndDate" /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																																								
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.SettlementType" />
																</div>
																<div class="tbox">
																	<s:select list="#{'PT':'Payment','RT':'Receipt'}" cssClass="inputBoxS" name="settlementType" id="settlementType" headerKey="ALL" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.CedingCompany" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="allocationReport.Status" />
																</div>
																<div class="tbox" id="reportCeding">
																	<s:select list="#{'Y':'Allocated','R':'Reverted'}" cssClass="inputBoxS" name="allocateStatus" id="allocateStatus" headerKey="ALL"	headerValue="---ALL---" />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"receiptpaymentReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
														<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.CedingCompany" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.StartDate" /> &nbsp; 
																</div>
																<div class="tbox">						
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />														
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.EndDate" /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.PayRecType" />
																</div>
																<div class="tbox">
																	<s:select list="#{'PT':'Payment','RT':'Receipt'}" cssClass="inputBoxS" name="payrecType" id="payrecType" headerKey="ALL" headerValue="---ALL---" />
																</div>
															</div>
															
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.Status" />
																</div>
																<div class="tbox" id="reportCeding">
																	<s:select list="#{'Transaction':'Transaction','Treasury':'Treasury'}" cssClass="inputBoxS" name="transactionType" id="transactionType" headerKey="ALL"	headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.fields" />
																</div>
																<div class="tbox" id="reportCeding">
																	<s:radio name="showAllFields" id="test" list="#{'Y':'Yes','N':'No'}" value='showAllFields==null?"N":showAllFields'/>
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"retroRDSReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="label.retroCessionLeader" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.UWYear" />
																</div>
																<div class="tbox">
																	<s:select list="yearList" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" name="uwYear" id="uwYear" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div> 
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.StartDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.EndDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"retroReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="label.retroCessionLeader" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.UWYear" />
																</div>
																<div class="tbox">
																	<s:select list="yearList" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" name="uwYear" id="uwYear" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div> 
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.StartDate" /> &nbsp; 
																</div>
																<div class="tbox">						
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />														
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.EndDate" /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"TRwithdrawReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="label.retroCessionLeader" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.UWYear" />
																</div>
																<div class="tbox">
																	<s:select list="yearList" listKey="YEAR" listValue="YEAR" cssClass="inputBoxS" name="uwYear" id="uwYear" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div> 
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Treaty Main Class" /> &nbsp; 
																</div>
																<div class="tbox">
																<s:select list="departIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" cssClass="inputBoxS" name="treatyMainClass" id="treatyMainClass" headerKey="-1" headerValue="---Select---" />	
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Type of Treaty" /> &nbsp; 
																</div>
																<div class="tbox">
																<s:select list="proposaltypelist" listKey="TYPE" listValue="DETAIL_NAME" cssClass="inputBoxS" name="treatyType" id="treatyType" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Portfolio Withdrawal as on " /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"installmentdueReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
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
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.CedingCompany" />
																</div>
																<div class="tbox">
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReport.Broker" />
																</div>
																<div class="tbox">
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="brokerId" id="brokerId" onchange="" headerKey="-1" headerValue="---ALL---" />
																</div>
															</div>
															
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="As on Date " /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="RecPayReportReport.itemYN" />
																</div>
																<div class="tbox">
																	<s:radio name="allocationYN" id="test" list="#{'Y':'Yes','N':'No'}" value='allocationYN==null?"N":allocationYN'/>
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"TbReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
													</div>
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" /><s:text name="As on Date " /> &nbsp; 
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<input type="button" value="Submit"	class="btn btn-sm btn-success" onclick="getReports(this.form);" />
											</div>
										</div>
										</s:if>
										<s:if test='"soaReport".equals(display)'>
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<s:property value="headMes" />
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
																	<s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" headerKey=""	headerValue="---ALL---" />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.StartDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="start_date" id="start_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:property value="dateMes" />
																	&nbsp;
																	<s:text name="Reports.EndDate" />
																</div>
																<div class="tbox">
																	<s:textfield name="end_date" id="end_date"  cssClass="inputBox"   />
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.setledItem" />
																</div>
																<div class="tbox">
																	<s:radio name="settledItems" id="test" list="#{'Y':'Yes','N':'No'}" value='settledItems==null?"N":settledItems'/>
																</div>
															</div>
															<div class="textfield">
																<div class="text txtB">
																	<s:text name="Reports.PRCL" />
																</div>
																<div class="tbox">
																	<s:checkboxlist list="#{'P':'Premium','C':'Claim','N':'ALL'}" name="saperatePRCLYN" value='saperatePRCLYN==null?"N":saperatePRCLYN' onclick="checkDE1(this.value);"></s:checkboxlist>
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
											<div class="boxcontent" align="center">
												<a  class="btn btn-sm btn-success" onclick="getReportsPDF(this.form);" >Download</a>
												<a  class="btn btn-sm btn-success" onclick="getPayRecReportsDownload(this.form);" >Excel Download</a>
											</div>
										</div>
									</s:if>
								</div>
								<s:hidden name="manufactureID" value="%{#session.mfrid}" />
								<s:hidden name="reportType" id="reportType" />
								<s:hidden name="display" />
								<s:hidden name="typeId" />
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">    
       <%--(function ($) {
            $(function () {
                var recordTable = $("table#record");
                var width = recordTable.width() + 100;
                recordTable.css({'width':'auto','min-width':'100%'});
                recordTable.colResizable({
                    fixed:false,
                    liveDrag: true,
                    minWidth: 100,
                    onDrag: function (e) {
                        var x = $(e.target);
                    }
                }).mousemove(function (evt) {
//                console.log(_$(evt.target).width());
                });
            });
        })(_$);--%>
	<s:if test='"init".equals(display)'>
		getCedingCompany();
		getch('<s:property value = "productId"/>');
	</s:if>
	<s:if test='!"soaReport".equals(display)'>
	var test=document.claims.test.value;
	if (test=="ALL"){
	contractScript("ALL");
	}
	</s:if>
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
	function checkDE1(id){
	if(id=='N'){
	 	document.getElementById("saperatePRCLYN-1").checked=false;
	 	document.getElementById("saperatePRCLYN-2").checked=false;
	}else if(id=='P'){
		document.getElementById("saperatePRCLYN-2").checked=false;
	 	document.getElementById("saperatePRCLYN-3").checked=false;
	}else if(id=='C'){
		document.getElementById("saperatePRCLYN-1").checked=false;
	 	document.getElementById("saperatePRCLYN-3").checked=false;
	}
	}
	function getHomeBack(form)
	{
		form.action="${pageContext.request.contextPath}/commonListPortfolio";
		form.submit();
	}
  	function getBack(form)
	{
  		document.claims.action="${pageContext.request.contextPath}/getInitReports";
		document.claims.submit();
	    //form.action='${pageContext.request.contextPath}/commonListPortfolio?manufactureID=<bean:write name="mfrid"/>&Department=<bean:write name="DepartmentId"/>';
		//form.action="${pageContext.request.contextPath}/getInitReports";
		//form.submit();
	}
	function getReports(form){
	try{
		document.getElementById('reportType').value='Html';
		document.claims.action="${pageContext.request.contextPath}/getRlistReports";
		document.claims.submit();
		}catch(err){alert(err)}
	}
	function getReportsDownload(form)
	{
		document.getElementById('reportType').value='Excel';
		document.claims.action="${pageContext.request.contextPath}/getRlistReports";
		document.claims.submit();
		//form.reportType.value='Excel';
	    //form.action="${pageContext.request.contextPath}/getRlistReports.action";
		//form.submit();		
	}
	function getPayRecReportsDownload(form)
	{
		document.getElementById('reportType').value='xml';
		document.claims.action="${pageContext.request.contextPath}/getRlistReports";
		document.claims.submit();
		//alert(form);
		//form.reportType.value='xml';
	    //form.action="${pageContext.request.contextPath}/getRlistReports.action";
		//form.submit();
		//window.location.reload();
	}
	
	
	function getReportsPDF(form)
	{
		document.getElementById('reportType').value='pdf';
		//$("err").empty();
		document.getElementById("err").style.display="none";
		document.claims.action="${pageContext.request.contextPath}/getRlistReports";
		//document.claims.actionError.value="";
		document.claims.submit();
	}
	function getch(val) {
		if(val=='4'){
			document.getElementById("pr1").style.display='inline';
			document.getElementById("pr2").style.display='none';
		}else
		{
			document.getElementById("pr1").style.display='none';
			document.getElementById("pr2").style.display='inline'
		}	
	}
	function contractScript(value) {
		if(value=="S") {
			document.getElementById('endtlabel').style.display='block';			
		}
		else if(value=="ALL") {
			document.getElementById('endtlabel').style.display='none';
		}
	}
	function getCedent(value) {
		if(value=="63") {
			document.getElementById('ceding').style.display='block';
			document.getElementById('ced').style.display='none';
			document.getElementById("cedingId").value='ALL';			
		}
		else {
		document.getElementById('ceding').style.display='none';
			document.getElementById('ced').style.display='block';
			document.getElementById("cedingId").value='';
		}
	}
	
	function getCedingCompany1()
	{
	var brokerId=document.getElementById("brokerId").value;
	var productId=document.getElementById("productId").value;
	//if(brokerId!=null && brokerId!="" && brokerId!=-1 && productId!=null && productId!="" && productId!=-1)
	if(productId!=null && productId!="" && productId!=-1)
	{
		var URL='${pageContext.request.contextPath}/getCedingCompanyReports?productId='+productId+'&brokerId='+brokerId+'&dropDown='+"reportCeding";
  	 	postRequest(URL,'reportCeding');
	}
}

	function getCedingCompany()
	{
		var brokerId=document.getElementById("brokerId").value;
		var productId=document.getElementById("productId").value;
		//if(productId!=null && productId!="" && productId!=-1)
		//if(brokerId!=null && brokerId!="" && brokerId!=-1 && productId!=null && productId!="" && productId!=-1)
		{
			if (window.XMLHttpRequest)
			    {
			   		xmlhttp=new XMLHttpRequest();
			    } 	
				else
				{
					xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function()
				{
					if (xmlhttp.readyState==4 && xmlhttp.status==200)
					{
			 	 		var a=xmlhttp.responseText.split("~");
				 		var j=0;
				 		var opt = document.getElementById("CeddingId").options.length=0;
						var opt = document.createElement("option");
				 		opt.value ='-1';
				 		opt.text = '--All--';
				 		document.getElementById("CeddingId").options.add(opt);
				 		while(j<a.length)
				 		{	
							opt = document.createElement("option");
							opt.value = a[j];
							j++;
							opt.text = a[j];
						    document.getElementById("CeddingId").options.add(opt);
					    	j++;
			   			}         
					}
				}
				xmlhttp.open("POST",'${pageContext.request.contextPath}/getCedingCompanyReports?productId='+productId+'&brokerId='+brokerId,true);
				xmlhttp.send();
		}				
	}

</script>
	</body>
</html>