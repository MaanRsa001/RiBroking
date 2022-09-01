<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
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
					"order": [[ 1, "desc" ]],
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
	 <script type="text/javascript">
	 $(function() {
		$( "#effectiveDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>	
</head>

<body >
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="rate" theme="simple" action="" method="post">
					<div class="table2">
						<div class="tablerow">
							<%--<span style="color:red;"><s:actionerror/></span>
							<span style="color:red;"><s:property value="errorList"/></span>
							<span style="color:red;"><table><s:iterator value="error" var="list" status="stat"><tr><td>${ERROR}</td></tr></s:iterator></table> </span>
							--%><ol>
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
							<span style="color:green;"><s:actionmessage/></span>
						</div>
						<s:if test="'list'.equals(mode)">
						<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="rate.heading.list" />
										<span class="pullRight">
										<input type="button" value="Add More" class="btn btn-sm btn-info" onClick="AddNewForm('new')"/>
										</span>
									</div>
						<div class="panel-body">
															<div class="row">
																<div class="col-xs-12">
								   									<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
																		<thead>
																			<tr>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="TDS S.No" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name="Effective Date" /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name=" Entry Date " /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name=" Edit " /></th>
																				<th style="text-align: center; vertical-align: middle;"><s:text name=" View " /></th>
																			</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="rateMasterList" var="list" status="stat">
																			<tr>																			
																				<td>${AMEND_ID}</td>
																				<td>${EFFECTIVE_DATE}</td>
																				<td>${SYS_DATE}</td>
																				<s:if test='%{#list.MAX_AMENDID==#list.AMEND_ID}'>
																				<td><input type="button" value="Edit" class="btn btn-sm btn-info" onClick="funEditMode('edit','${AMEND_ID}')"/></td>
																				</s:if>
																				<s:else>
																				<td></td>
																				</s:else>
																				<td><input type="button" value="View" class="btn btn-sm btn-info" onClick="funEditMode('view','${AMEND_ID}')"/></td>
																			</tr>
																		</s:iterator>
																	</tbody>
																</table>
															</div>			
														</div>
													</div>
													</div>
						</s:if>
						<s:elseif test="'view'.equals(mode)">
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="rate.heading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										
										<div class="textfield">
												<div class="text">
													<s:text name="TDS Sno" />
												</div>
												<div class="tbox"><s:property value="tdsSno"/>
												</div>
										</div>
										<div class="textfield">
												<div class="text">
													<s:text name="Entry Date" />
												</div>
												<div class="tbox"><s:property value="entrydate"/>
												</div>
										</div>
										<div class="textfield">
												<div class="text">
													<s:text name="rate.date" />
												</div>
												<div class="tbox">
												<s:property value="effectiveDate"/>							
												</div>
										</div>
										</div>
										<div class="boxcontent">
										<br class="clear"/>
										<br class="clear"/>
										<table class="footable" width="100%" id="rateTbl">
														<thead>
															<tr>
																<th width="2%"> <s:text name="Serial No" />  </th>
																<th  width="8%" > <s:text name="rate.secno"/> </th>
																<th width="8%" > <s:text name="rate.anuelthrteshold"/> </th>
																<th width="10%" > <s:text name="rate.type"/> </th>
																<th width="12%" > <s:text name="rate.resistatus"/> </th>
																<th width="8%"> <s:text name="rate.tdsrate" /> </th>
																<th width="8%"> <s:text name="rate.cess1" /> </th>
																<th  width="8%" > <s:text name="rate.cess2"/> </th>
																<th width="8%" > <s:text name="rate.cess3"/> </th>
																<th width="8%" > <s:text name="rate.totaltds"/> </th>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="editAddRateMaster" var="list" status="stat">
															<tr>
															<td>
																		<s:textfield cssClass="inputBox" name="rateSno[%{#stat.count-1}]" id="rateSno[%{#stat.count-1}]" cssStyle="text-align:right;" value="%{#stat.count}" readonly="true"/>
															</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="sectionNo[%{#stat.count-1}]" id="sectionNo[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" readonly="true"/>
												 					</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="anualThresholdLimit[%{#stat.count-1}]" id="anualThresholdLimit[%{#stat.count-1}]" cssStyle="text-align:right;" readonly="true"/>
																	</td>
																	<td>
																	<s:select list="dropDownTypeList" name="typeId[%{#stat.count-1}]" id="typeId[%{#stat.count-1}]"  listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="true"/>
																	</td>
																	<td>
																	<s:select list="dropDownResidentalList" name="residentalStatus[%{#stat.count-1}]" id="residentalStatus[%{#stat.count-1}]"  listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" disabled="true"/>
																	</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="rateOfTds[%{#stat.count-1}]" id="rateOfTds[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  readonly="true"/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessOne[%{#stat.count-1}]" id="cessOne[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  readonly="true"/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessTwo[%{#stat.count-1}]" id="cessTwo[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  readonly="true"/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessThree[%{#stat.count-1}]" id="cessThree[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  readonly="true"/>
																	</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="totalTds[%{#stat.count-1}]" id="totalTds[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" readonly="true"/>
																	</td>
																</tr>
															</s:iterator>
															</tbody>
															</table>
												
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="tablerow">							
							<div class="boxcontent" align="center">								
								<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="Cancel()"  /> &nbsp;&nbsp;&nbsp;
							</div>
						</div>	
						</s:elseif>
						<s:else>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="rate.heading" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
										<div class="textfield">
												<div class="text">
													<s:text name="rate.date" />
												</div>
												<div class="tbox">
												<s:textfield name="effectiveDate" id="effectiveDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  onchange="functionDate()"  />	
												</div>
										</div>
										</div>	
										<br class="clear"/>
										<br class="clear"/>
										<table class="footable" width="100%" id="rateTbl">
														<thead>
															<tr>
																<th width="2%"> <s:text name="Serial No" />  </th>
																<th  width="8%" > <s:text name="rate.secno"/> </th>
																<th width="8%" > <s:text name="rate.anuelthrteshold"/> </th>
																<th width="10%" > <s:text name="rate.type"/> </th>
																<th width="12%" > <s:text name="rate.resistatus"/> </th>
																<th width="8%"> <s:text name="rate.tdsrate" /> </th>
																<th width="8%"> <s:text name="rate.cess1" /> </th>
																<th  width="8%" > <s:text name="rate.cess2"/> </th>
																<th width="8%" > <s:text name="rate.cess3"/> </th>
																<th width="8%" > <s:text name="rate.totaltds"/> </th>
																<th width="8%"> <s:text name="Delete" /> </th>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="editAddRateMaster" var="list" status="stat">
															<tr>
															<td>
																		<s:textfield cssClass="inputBox" name="rateSno[%{#stat.count-1}]" id="rateSno[%{#stat.count-1}]" value="%{#stat.count}" readonly="true" cssStyle="text-align:right;"/>
															</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="sectionNo[%{#stat.count-1}]" id="sectionNo[%{#stat.count-1}]" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"/>
												 					</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="anualThresholdLimit[%{#stat.count-1}]" id="anualThresholdLimit[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;"/>
																	</td>
																	<td>
																	<s:select list="dropDownTypeList" name="typeId[%{#stat.count-1}]" id="typeId[%{#stat.count-1}]"  listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" />
																	</td>
																	<td>
																	<s:select list="dropDownResidentalList" name="residentalStatus[%{#stat.count-1}]" id="residentalStatus[%{#stat.count-1}]"  listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerKey="" headerValue="---Select---" cssClass="inputBoxS" />
																	</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="rateOfTds[%{#stat.count-1}]" id="rateOfTds[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;" onblur='totalTdsCalculation(%{#stat.count-1});'/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessOne[%{#stat.count-1}]" id="cessOne[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;" onblur='totalTdsCalculation(%{#stat.count-1});'/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessTwo[%{#stat.count-1}]" id="cessTwo[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;" onblur='totalTdsCalculation(%{#stat.count-1});'/>
																	</td>
																	<td>
																	<s:textfield cssClass="inputBox" name="cessThree[%{#stat.count-1}]" id="cessThree[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;" onblur='totalTdsCalculation(%{#stat.count-1});'/>
																	</td>
																	<td>
																		<s:textfield cssClass="inputBox" name="totalTds[%{#stat.count-1}]" id="totalTds[%{#stat.count-1}]" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" cssStyle="text-align:right;" readonly="true"/>
																	</td>
																	
																	<td>
																	<input type="button" value="Delete" class="btn btn-sm btn-info"   onclick="removeRow('<s:property value="%{#stat.count-1}"/>','<s:property value="mode"/>')" />
																	</td>
																</tr>
															</s:iterator>
															</tbody>
															</table>
															
															<div class="boxcontent" align="right">
															<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('rateTbl');" />
														</div>
														
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">								
								<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="Cancel()"  /> &nbsp;&nbsp;&nbsp;
								<s:if test='"new".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('insert')"/>
								
								</s:if>
								<s:if test='"edit".equals(mode)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="SubmitForm('update')"/>
									<s:hidden name="tdsSno" id="tdsSno"/>
								</s:if>
							</div>
						</div>										
						
						</s:else>																			
					</div>				
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function removeRow(val,mode){

if(val==0){
alert("First row should not delete");
}
else{
var status=confirm("Do you want to delete specified row");
if(status){
	document.rate.action='${pageContext.request.contextPath}/removeRowAdmin.action?flag=delete&mode='+mode+'&deleteId='+val;
	document.rate.submit();
	}
	}
}
	
    	
function funEditMode(val,id) {       	
	document.rate.action='${pageContext.request.contextPath}/detailsRateMasterAdmin.action?mode='+val+'&tdsSno='+id;
	document.rate.submit();    
}
  
function AddNewForm(val) {
	document.rate.action='${pageContext.request.contextPath}/detailsRateMasterAdmin.action?mode='+val;
	document.rate.submit();
}

function SubmitForm(value) {
	document.rate.action='${pageContext.request.contextPath}/detailsRateMasterAdmin.action?mode='+value;
	document.rate.submit();
}

function Cancel() {
	document.rate.action='${pageContext.request.contextPath}/detailsRateMasterAdmin.action?mode=list';
	document.rate.submit();
}

function totalTdsCalculation(val)
{
	var rateOfTds=document.getElementById('rateOfTds['+val+']').value;
	var cessOne=document.getElementById('cessOne['+val+']').value ;
	var cessTwo=document.getElementById('cessTwo['+val+']').value ;
	var cessThree=document.getElementById('cessThree['+val+']').value ;
	var rate=0.00;
	var c1 =0.00;
	var c2=0.00;
	var c3 =0.00;
	rateOfTds=rateOfTds.replace(new RegExp(',', 'g'),'');
	cessOne=cessOne.replace(new RegExp(',', 'g'),'');
	cessTwo=cessTwo.replace(new RegExp(',', 'g'),'');
	cessThree=cessThree.replace(new RegExp(',', 'g'),'');
	if(rateOfTds!=null && rateOfTds!=""  ){
	 rate=parseFloat(rateOfTds);
	}
	if(cessOne!=null && cessOne!="" ){
	 c1 =parseFloat(cessOne);
	}
	if(cessTwo!=null && cessTwo!="" ){
	 c2=parseFloat(cessTwo);
	}
	if(cessThree!=null && cessThree!="" ){
	 c3 =parseFloat(cessThree);
	}
	var tot =rate+c1+c2+c3;
	document.getElementById('totalTds['+val+']').value =tot;
	
}

function insRow(tableID)
{

	var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "rateSno["+(rowCount-1)+"]";
       		element1.id = "rateSno["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("style", "text-align:right;");
       		cell1.appendChild(element1);
       		
			
			var cell2 = row.insertCell(1);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "sectionNo["+(rowCount-1)+"]";
      		element2.id = "sectionNo["+(rowCount-1)+"]";
			element2.className = "inputBox";
			element2.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "anualThresholdLimit["+(rowCount-1)+"]";
      		element3.id = "anualThresholdLimit["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			rateCrestaTypeIdCell(cell4, rowCount)
			
			var cell5 = row.insertCell(4);
			rateresidentalStatusCell(cell5, rowCount)
			
			
			var cell6 = row.insertCell(5);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "rateOfTds["+(rowCount-1)+"]";
      		element6.id = "rateOfTds["+(rowCount-1)+"]";
			element6.className = "inputBox";
			element6.setAttribute("style", "text-align:right;");
			//onblur='totalTdsCalculation(%{#stat.count-1});'
			element6.setAttribute("onblur","totalTdsCalculation("+(rowCount-1)+")");
			element6.setAttribute("onkeyup","allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell6.appendChild(element6);
			
			
       		var cell7 = row.insertCell(6);
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "cessOne["+(rowCount-1)+"]";
      		element7.id = "cessOne["+(rowCount-1)+"]";
			element7.className = "inputBox";
			element7.setAttribute("style", "text-align:right;");
			element7.setAttribute("onblur","totalTdsCalculation("+(rowCount-1)+")");
			element7.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell7.appendChild(element7);
			
			
			
			var cell8 = row.insertCell(7);
			var element8 = document.createElement("input");
			element8.type = "text";
			element8.name = "cessTwo["+(rowCount-1)+"]";
      		element8.id = "cessTwo["+(rowCount-1)+"]";
			element8.className = "inputBox";
			element8.setAttribute("style", "text-align:right;");
			element8.setAttribute("onblur","totalTdsCalculation("+(rowCount-1)+")");
			element8.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell8.appendChild(element8);
			
			
			
			
			var cell9 = row.insertCell(8);
			var element9 = document.createElement("input");
			element9.type = "text";
			element9.name = "cessThree["+(rowCount-1)+"]";
      		element9.id = "cessThree["+(rowCount-1)+"]";
			element9.className = "inputBox";
			element9.setAttribute("style", "text-align:right;");
			element9.setAttribute("onblur","totalTdsCalculation("+(rowCount-1)+")");
			element9.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell9.appendChild(element9);
			
			
			var cell10 = row.insertCell(9);
			var element10 = document.createElement("input");
			element10.type = "text";
			element10.name = "totalTds["+(rowCount-1)+"]";
      		element10.id = "totalTds["+(rowCount-1)+"]";
			element10.className = "inputBox";
			element10.setAttribute("style", "text-align:right;");
			element10.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell10.appendChild(element10);
			
			
			var cell11 = row.insertCell(10);
			var element11 = document.createElement("input");
			element11.type = "button";
			element11.value="Delete";
			//element11.setAttribute("onclick", "deleteRow('rateTbl','"+(rowCount)+"')");
			element11.setAttribute("onclick", "removeRow('"+(rowCount-1)+"','<s:property value="mode"/>')");
			
			element11.className="btn btn-sm btn-info"
			cell11.appendChild(element11);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}


function rateresidentalStatusCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "residentalStatus["+(rowCount-1)+"]";
      element.id = "residentalStatus["+(rowCount-1)+"]";
       element.className = "inputBox";            
       populateresidentalStatusCell(element);
          cell.appendChild(element);
}
function populateresidentalStatusCell(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='dropDownResidentalList'>
				var objOption = document.createElement("option");
				objOption.value = "<s:property value='CATEGORY_DETAIL_ID' />";
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function rateCrestaTypeIdCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "typeId["+(rowCount-1)+"]";
      element.id = "typeId["+(rowCount-1)+"]";
       element.className = "inputBox";            
       populateCrestaTypeIdCell(element);
          cell.appendChild(element);
}
function populateCrestaTypeIdCell(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='dropDownTypeList'>
				var objOption = document.createElement("option");
				objOption.value = "<s:property value='CATEGORY_DETAIL_ID' />";
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }

	
	
</script>		
</body>
</html>
