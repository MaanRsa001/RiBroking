<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <sj:head jqueryui="true" jquerytheme="start"/>
    <link rel="stylesheet" type="text/css" href="css/chosen.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
     	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	 
	 <style>
		.button {background-color: #4CAF50; /* Green */}

		.button2 {background-color: #008CBA;} /* Blue */
		.button3 {background-color: #f44336;} /* Red */
		.button4 {background-color: #e7e7e7; color: black;} /* Gray */
		.button5 {background-color: #555555;} /* Black */
	 </style>
	<style>
		#loading {
			width: 100%;
			height: 100%;
			top: 0px;
			left: 0px;
			position: fixed;
			display: block;
			opacity: 0.7;
			background-color: #fff;
			z-index: 99;
			text-align: center;
		}

		#loading-image {
			position: absolute;
			top: 30%;
			left: 45%;
			z-index: 100;
			width: 100px;
			height: 100px;
		}

	</style>
</head>
<body>
<div id="loading" style="width:100%;" >
	<img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" />
</div>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
               <div class="boxcontent">
				<div class="panel panel-primary">
                <div class="panel-heading">
										 Cresta Details
				</div>
				 <div class="panel-body">
				<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-12 col-md-6">
												<s:text name="Proposal No" /> &nbsp;:&nbsp; <s:property value="proposal_no"/>
											</div>
											<div class="col-xs-12 col-md-6">
												<s:text name="Contract No" /> &nbsp;:&nbsp; <s:property value="contractNo"/>
											</div>
										</div>
									</div>
								</div>
					</div></div>
				<s:if test="'view'.equals(mode) || !''.equals(layerProposalNo)">
				<s:form id="crestaZone" name="crestaZone" theme="simple" action="" method="post" enctype="multipart/form-data" >
                    <div class="table2">
	                   <div class="tablerow">
	                   		<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
							<span style="color:red;"><s:actionerror/></span>
						</div>
                    <div class="panel-body">
                    	<div >
                    	</div>
							<div >
							<table class="footable" id="newgen" width="100%" >
									<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Territory Code" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Cresta Id" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Cresta Name" /> </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Currency" /> </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"><s:text name="Accumulation Date" /></th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Accumulation of Risk " /> </th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="crestaList1" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td>
											<s:select  list="territoryCodelist"  name="territoryCode[%{#stat.count-1}]" id="territoryCode[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="COUNTRY_ID"  listValue="COUNTRY_NAME"  onchange="getCrestaIDAjax(this.value,'crestaid%{#stat.count-1}',%{#stat.count-1})" disabled="true"/>
											</td>
											<td id="crestaid<s:property value='%{#stat.count-1}'/>">
											<s:select  list="%{crestaIDList[#stat.count-1]}"  name="crestaId[%{#stat.count-1}]" id="crestaId[%{#stat.count-1}]" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="CRESTA_ID"  listValue="CRESTA_ID"  onchange="getCrestaNameAjax(this.value,'crestaname%{#stat.count-1}',%{#stat.count-1})" disabled="true"/>
											</td>											
											<td id="crestaname<s:property value='%{#stat.count-1}'/>">
											<s:textfield name="crestaName[%{#stat.count-1}]" id="crestaName[%{#stat.count-1}]" cssClass="inputBox" readonly="true"></s:textfield>
											</td>
											<td>
											<s:select  list="currencyShortList"  name="currencyId[%{#stat.count-1}]" id="currencyId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CURRENCY_ID"  listValue="SHORT_NAME" disabled="true"/>
											</td>
											<td>
											<s:textfield name="accumulationDate[%{#stat.count-1}]" id="accumulationDate[%{#stat.count-1}]"  cssClass = "inputBox"  onkeyup="validateSpecialChars(this)"  readonly="true"/>
											</td>
											<td>
											<s:textfield name="accRisk[%{#stat.count-1}]" id="accRisk[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30"  readonly="true"/>
											</td>
												<s:hidden name="count" value="%{#stat.count-1}"></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<s:hidden name="contractNo"/>
								<s:hidden name="proposal_no"/>
								<s:hidden name="amendId"/>
								<s:hidden name="importflag" id="importflag"/>
							</div>
							<br class="clear"/>
							<div class="boxcontent" align="center">
							<s:if test="'view'.equals(mode) ">
							<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');getPrint()"/>
							</s:if>
							<s:else>
							<%-- <input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmit();return false;" class="btn btn-sm btn-success" />--%>
							</s:else>
							<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
							</div>
						</div>
					</div>
                    </s:form>
				</s:if>
				<s:else>
                <s:form id="crestaZone" name="crestaZone" theme="simple" action="" method="post" enctype="multipart/form-data" >
                    <div class="table2">
	                   <div class="tablerow">
	                   		<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
							<span style="color:red;"><s:actionerror/></span>
						</div>
                    <div class="panel-body">
                    	<div >
                    	</div>
							<div >
								<table class="footable" id="newgen" width="100%" >				
									<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Territory Code" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Cresta Id" />  </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Cresta Name" /> </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Currency" /> </th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"><s:text name="Accumulation Date" /></th>
											<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Accumulation of Risk " /> </th>
											<th width="10.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="crestaList1" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td >
											<s:select  list="territoryCodelist"  name="territoryCode[%{#stat.count-1}]" id="territoryCode[%{#stat.count-1}]" cssClass="chosen-select " cssStyle="width:150px"  headerKey="" headerValue="---Select---" listKey="COUNTRY_ID"  listValue="COUNTRY_NAME"  onchange="getCrestaIDAjax(this.value,'crestaid%{#stat.count-1}',%{#stat.count-1})"/>
											</td>
											<td id="crestaid<s:property value='%{#stat.count-1}'/>" >
											<s:select  list="%{crestaIDList[#stat.count-1]}"  name="crestaId[%{#stat.count-1}]" id="crestaId[%{#stat.count-1}]" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="CRESTA_ID"  listValue="CRESTA_ID"  onchange="getCrestaNameAjax(this.value,'crestaname%{#stat.count-1}',%{#stat.count-1})"/>
											</td>											
											<td id="crestaname<s:property value='%{#stat.count-1}'/>" >
											<s:textfield name="crestaName[%{#stat.count-1}]" id="crestaName[%{#stat.count-1}]" cssClass="inputBox" readonly="true"></s:textfield>
											</td>
											<td >
											<s:select  list="currencyShortList"  name="currencyId[%{#stat.count-1}]" id="currencyId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CURRENCY_ID"  listValue="SHORT_NAME" />
											</td>
											<td >
											<s:textfield name="accumulationDate[%{#stat.count-1}]" id="accumulationDate[%{#stat.count-1}]"  cssClass = "inputBox datepicker accumDate"  onkeyup="validateSpecialChars(this)"  />
											</td >
											<td >
											<s:textfield name="accRisk[%{#stat.count-1}]" id="accRisk[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
											</td>
											<td >
											<s:if test='0!=(#stat.count-1)'>
											<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											</td>
												<s:hidden name="count" value="%{#stat.count-1}"></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<div class="boxcontent" align="right">
									<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
								</div>
								<s:hidden name="contractNo"/>
								<s:hidden name="proposal_no"/>
								<s:hidden name="amendId"/>
								<s:hidden name="importflag" id="importflag"/>
							</div>
							<div id="display" style="display:none">
								<div class="boxcontent" >
									<div class="panel panel-primary">
										<div class="panel-heading">
											  File Import
										</div>
										<div class="panel-body">
											 <div class="textfield">
												<div class="text">
													<s:text name="File" />
												</div>
												<div class="tbox">
													<div class="inputAppend">
													<s:file name="crestaupload" id="crestaupload"   />
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<input type="button" value="File Import" class="btn btn-sm btn-success"  onclick="insImport();" />&nbsp;&nbsp;
												</div>
											</div>
											<br class="clear"/>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<br class="clear"/>
							<div class="boxcontent" align="center">
							<input type="button" value="Get Template" class="btn btn-sm btn-primary"  onclick="fnTemplate();" />&nbsp;&nbsp;
							<input type="button" value="Import" class="btn btn-sm btn-info"  onclick="fileImport();" />&nbsp;&nbsp;
							<input type="button" value="Submit" class="btn btn-sm btn-success"  onclick="fnSubmit();" />&nbsp;&nbsp;
							<s:hidden name="loopcount" id="loopcount"></s:hidden>
						<input type="button" value="Close" class="btn btn-sm btn-danger"  onclick="window.close()" />
					</div>
						</div>
                    </div>
                    	<s:hidden name="layerProposalNo" id="layerProposalNo"/>
                    </s:form>
                    </s:else>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function fnSubmit(){
	document.crestaZone.action="${pageContext.request.contextPath}/submitCrestaDetailsRiskDetails.action";
	document.crestaZone.submit();
}

function insRow(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "scaleSNo["+(rowCount-1)+"]";
       		element1.id = "scaleSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
			
			var cell2 = row.insertCell(1);
			//cell2.setAttribute('style','background-color:#C6E2FF;');
			createTerritoryCell(cell2, rowCount)
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			cell3.id="crestaid"+(rowCount-1);
			createCrestaIDCell(cell3, rowCount)
			
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			cell4.id="crestaname"+(rowCount-1);
			createCrestaNameCell(cell4, rowCount)
			
			var cell5 = row.insertCell(4);
			//cell5.setAttribute('style','background-color:#C6E2FF;');
			createCurrencyCell(cell5, rowCount)
			
			var cell6 = row.insertCell(5);
			//cell6.setAttribute('style','background-color:#C6E2FF;');
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "accumulationDate["+(rowCount-1)+"]";
      		element6.id = "accumulationDate"+(rowCount-1)+"";
			element6.className = "inputBox datepicker accumDate1";
			element6.setAttribute("onkeyup", "validateSpecialChars(this)");
			  $(document).on("focus", "#accumulationDate"+(rowCount-1), function(){ 
			   $(this).datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : "dd/mm/yy"
					//yearRange: "-100:+0"
				});
			  });
			cell6.appendChild(element6);
			
			var cell7 = row.insertCell(6);
			//cell7.setAttribute('style','background-color:#C6E2FF;');
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "accRisk["+(rowCount-1)+"]";
      		element7.id = "accRisk["+(rowCount-1)+"]";
			element7.className = "inputBox";
			//element7.value="";
			element7.setAttribute("style", "text-align:right;");
			element7.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell7.appendChild(element7);
			//document.getElementById("accRisk["+(rowCount-1)+"]").value ="0"; //for validation purpose
			
			var cell8 = row.insertCell(7);
			var element8 = document.createElement("input");
			element8.type = "button";
			element8.value="Delete";
			//element8.onclick = function () {deleteRow(this.id)};
			element8.setAttribute("onclick", "deleteRow('"+(rowCount-1)+"')");
			element8.className="btn btn-sm btn-danger"
			cell8.appendChild(element8);
			 document.getElementById("loopcount").value =parseInt(rowCount);
}
function createTerritoryCell(cell, rowCount){
 $(".chosen-select").chosen('destroy');
	element = document.createElement("select");
         element.name = "territoryCode["+(rowCount-1)+"]";
      	element.id = "territoryCode["+(rowCount-1)+"]";
       element.setAttribute("class", "chosen-select");
        element.setAttribute("style", "width:150px;");
       element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateTerritoty(element);
          cell.appendChild(element);
           $(".chosen-select").chosen();
}
function createCrestaIDCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "crestaId["+(rowCount-1)+"]";
      	element.id = "crestaId["+(rowCount-1)+"]";
       element.className = "inputBox";
       element.setAttribute("onchange", "getCrestaNameAjax(this.value,'crestaname"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCrestaID(element);
          cell.appendChild(element);
}
function populateTerritoty(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='territoryCodelist'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='COUNTRY_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='COUNTRY_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
function populateCrestaID(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='crestaIDAjsxList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='CRESTA_ID' />";
				objOption.value = "<s:property value='CRESTA_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function createCrestaNameCell(cell, rowCount){
	element = document.createElement("input");
         element.name = "crestaName["+(rowCount-1)+"]";
      element.id = "crestaName["+(rowCount-1)+"]";
       element.className = "inputBox";            
      // populateCrestaName(element);
          cell.appendChild(element);
}
function populateCrestaName(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='crestaNameAjsxList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='CRESTA_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='CRESTA_NAME' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
function createCurrencyCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "currencyId["+(rowCount-1)+"]";
      element.id = "currencyId["+(rowCount-1)+"]";
       element.className = "inputBox";            
       populateCurrency(element);
       var mylist = document.getElementById("currencyId["+(rowCount-2)+"]").value;
         element.value= mylist;
          cell.appendChild(element);
}
function populateCurrency(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='currencyShortList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='SHORT_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='CURRENCY_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function deleteRow1(tableID,i) {
			try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			table.deleteRow(i);
			rowCount--;
			}catch(e) {
				alert(e);
			}
		}

function getCrestaIDAjax(value,id,count) {
	if(value!=null && value !=""){
	 	var URL='${pageContext.request.contextPath}/getCrestaAjaxRiskDetails.action?crestaValue='+value+'&dropDown=crestaID&id='+count;
        postFormLoader(URL,id);
    }	
}
function fnTemplate(mode){
	document.crestaZone.action="${pageContext.request.contextPath}/documentDownloadRiskDetails.action";
	document.crestaZone.submit();
}
function insImport(){
var doc = document.getElementById('crestaupload').value;
if(doc==""){
alert("Plese Seelect File");
}
else{
var last=doc.substring(doc.lastIndexOf(".") + 1);
if(last !="xlsx" && last !="xls"){
alert("Invalid Import File Format.Import File need to be xls or xlsx");
}
else{
	var msg = window.prompt("Excel Import Start Row Number : 2 ,Enter End Row Number : ", "");
	if(msg>=2){
	document.crestaZone.action="${pageContext.request.contextPath}/uploadDocumentRiskDetails.action?maxNum="+msg;
	document.crestaZone.submit();
	}
	else{
	alert("Invalid End Row Number");
	}
}
}
}
<s:if test='"Y".equals(importflag)'>
fileImport();
</s:if>

function fileImport(){
document.getElementById('display').style.display='block';
document.getElementById('importflag').value="Y";
}


function getCrestaNameAjax(value,id,count) {
	if(value!=null && value !=""){
	 	var URL='${pageContext.request.contextPath}/getCrestaAjaxRiskDetails.action?crestaValue='+value+'&dropDown=crestaName&id='+count;
        postFormLoader(URL,id);
    }	
}
function getPrint()
	{
		document.getElementById("close").style.display='none';
		document.getElementById("print").style.display='none';
		window.print();
		document.getElementById("close").style.display='inline';
		document.getElementById("print").style.display='inline';
	}
function deleteRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			document.crestaZone.action="${pageContext.request.contextPath}/removeCrestaRiskDetails.action?mode=delete&deleteId="+val;
			document.crestaZone.submit();
			}
		}
}
function deleteRow3(id, el) {
			var decision = confirm("Row will be deleted. Do You Want to continue? ","");{
				if (decision==true){
					while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
						el = el.parentNode;
					}
					if (el.parentNode && el.parentNode.rows.length > 1) {
						el.parentNode.removeChild(el);
					}
				} else {
					//document.getElementById(id).checked=false;
				}	   
			}
		} 
		
    function middleMinusRestriction(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
	    if(i!=0){
	        if(text[i]=='-') min++;
	        if(min<1) { 
	        	prevValue += text[i];
	        }
	        }else{
	        	prevValue += text[i];
	        }
        }
    txt.value= prevValue;
    return false;
}	
</script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
   <script type="text/javascript">
	 $(function() {
	    $('.accumDate').datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$('.accumDate1').datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>
	  <script type="text/javascript" src="js/chosen.jquery.js"></script>
<script type="text/javascript">
 $(function() {
$(".chosen-select").chosen({});
});
</script>	
</body>
</html>
