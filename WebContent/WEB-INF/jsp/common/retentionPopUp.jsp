<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<style type="text/css">
 .tableColWidth {
 	min-width: 200px;
 	max-width: 750px;
 	width: 200px;
 	white-space: normal;
 }
 
  .tableColWidth1 {
 	min-width: 80px;
 	max-width: 750px;
 	width: 450px;
 	white-space: normal;
 }
 
 .tableColNoWrap {
 	min-width: 100px;
 	max-width: 750px;
 	width: 100px;
 	white-space: nowrap;
 }
 .table-overflow{
    overflow: scrollX;
 }
 </style>	
<!DOCTYPE html>
<html>
<head>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
</head>
				<div id="retentionid" class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;overflow-y: visible;">
								<table class="footable" id="newgen" width="100%" >				
									<thead>
										<tr>
											<th width="2%"> <s:text name="Sno" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Sub Class" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Business" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Retension" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Basis" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="1st Retention" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="2nd Retention" /></th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(FST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(SST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(FST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(SST)" /> </th>
											<th width="9%"> <s:text name="Delete Row" /> </th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="retList" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="retSNo[%{#stat.count-1}]" id="retSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
						 					</td>
											<td>
											<s:select list="retdepartIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
											<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"  multiple="true" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}' onchange="getAllValue(%{#stat.count-1})"/>
											</td>
											<td >
											<s:select list="retBusinessTypeList"  name="retBusinessType[%{#stat.count-1}]" id="retBusinessType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>											
											<td >
											<s:select list="retTypeList"  name="retType[%{#stat.count-1}]" id="retType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:select list="basicTypeList"  name="retBasis[%{#stat.count-1}]" id="retBasis[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"   listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="firstretention[%{#stat.count-1}]" id="firstretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="secondretention[%{#stat.count-1}]" id="secondretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retTreatyFST[%{#stat.count-1}]" id="retTreatyFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retTreatySST[%{#stat.count-1}]" id="retTreatySST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retEventFST[%{#stat.count-1}]" id="retEventFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retEventSST[%{#stat.count-1}]" id="retEventSST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:if test='0!=(#stat.count-1)'>
											<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
											<input type="button" value="Delete" id="retDelete" class="btn btn-sm btn-danger" disabled="disabled" onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											<s:else>
											<input type="button" value="Delete" id="retDelete" class="btn btn-sm btn-danger"  onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:else>
											</s:if>
										    <s:hidden name="coverpreVal[%{#stat.count-1}]" id="coverpreVal%{#stat.count-1}"/>
											</td>
												<s:hidden name="count" value="%{#stat.count-1}"></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<div class="boxcontent" align="right">
									<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" id="retAdd" onclick="insRow('newgen');" />
								</div>
							</div>
							<s:hidden name="loopcount" id="loopcount"></s:hidden>

<script type="text/javascript">

var val=document.getElementById('loopcount').value;
$(document).ready(function() {
    $("#coversubdepartId"+val).multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
           var val = $("#coversubdepartId"+val).val();
		          var val1 =document.getElementById("coverpreVal"+val).value;
		         
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#coversubdepartId"+val).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#coversubdepartId"+val).val(val);
		           $("#coversubdepartId"+val).multiselect("refresh");
		           document.getElementById("coverpreVal"+val).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#coversubdepartId"+val).multiselect('clearSelection');
		          	$("#coversubdepartId"+val).val('ALL');
		          	 $("#coversubdepartId"+val).multiselect("refresh");
		          	 document.getElementById("coverpreVal"+val).value = 'ALL';
		          }
      	}                     
    }); 
  
 
 		
});

 <s:iterator value="retList"  status="stat">   
 var count='<s:property value="%{#stat.count-1}"/>';
       $('#coversubdepartId').html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		onChange: function(element, checked) {
		          var val = $("#coversubdepartId"+count).val();
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#coversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#coversubdepartId"+count).val(val);
		           $("#coversubdepartId"+count).multiselect("refresh");
		           document.getElementById("coverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#coversubdepartId"+count).multiselect('clearSelection');
		          	$("#coversubdepartId"+count).val('ALL');
		          	 $("#coversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("coverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });  
    var val='<s:property value="%{#stat.count-1}"/>'; 
 	var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
	var data=uwgrade.replace(/ /g,'');	
	var dataArray=data.split(",");
	$("#coversubdepartId"+val).val(dataArray);
	$("#coversubdepartId"+val).multiselect("refresh");    
</s:iterator>
function getAjaxCover(value,id,count)
{
	 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueFacultative.action?coverdepart='+value+'&dropDown=coversubclass&id='+count,
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#coversubdepartId'+count).multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		onChange: function(element, checked) {
          		 var val = $("#coversubdepartId"+count).val();
		          var val1 =document.getElementById("coverpreVal"+count).value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#coversubdepartId"+count).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#coversubdepartId"+count).val(val);
		           $("#coversubdepartId"+count).multiselect("refresh");
		           document.getElementById("coverpreVal"+count).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#coversubdepartId"+count).multiselect('clearSelection');
		          	$("#coversubdepartId"+count).val('ALL');
		          	 $("#coversubdepartId"+count).multiselect("refresh");
		          	 document.getElementById("coverpreVal"+count).value = 'ALL';
		          }
      	}                     
    });
        }
        });
        
}

function fnSubmit(){
	document.retention.action="${pageContext.request.contextPath}/submitCedentRetentionRiskDetails.action";
	document.retention.submit();
}

function insRow(tableID)
{
var table = document.getElementById(tableID);
var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "retSNo["+(rowCount-1)+"]";
       		element1.id = "retSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1);
			createcoverdeptCell(cell2, rowCount)
			
			var cell3 = row.insertCell(2);
			cell3.id='coverdepartid'+(rowCount-1);
			createcoversubdeptCell(cell3, rowCount)
			
			var cell4 = row.insertCell(3);
			createRetBusinessTypeCell(cell4, rowCount)
			
			var cell5 = row.insertCell(4);
			createRetTypeCell(cell5, rowCount)
			
			var cell6 = row.insertCell(5);
			createBasisCell(cell6, rowCount)
			
			var cell7 = row.insertCell(6);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "firstretention["+(rowCount-1)+"]";
      		element6.id = "firstretention"+(rowCount-1)+"";
      		//element6.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element6.className = "inputBox";
			element6.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element6.setAttribute("maxlength",'30');
			element6.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell7.appendChild(element6);
			
			var cell8 = row.insertCell(7);
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "secondretention["+(rowCount-1)+"]";
			//element7.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element7.id = "secondretention["+(rowCount-1)+"]";
			element7.className = "inputBox";
			element7.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element7.setAttribute("maxlength",'30');
			element7.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell8.appendChild(element7);
			
			var cell9 = row.insertCell(8);
			var element8 = document.createElement("input");
			element8.type = "text";
			element8.name = "retTreatyFST["+(rowCount-1)+"]";
			//element8.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element8.id = "retTreatyFST["+(rowCount-1)+"]";
			element8.className = "inputBox";
			element8.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element8.setAttribute("maxlength",'30');
			element8.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell9.appendChild(element8);
			
			var cell10 = row.insertCell(9);
			var element9 = document.createElement("input");
			element9.type = "text";
			element9.name = "retTreatySST["+(rowCount-1)+"]";
			//element9.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element9.id = "retTreatySST["+(rowCount-1)+"]";
			element9.className = "inputBox";
			element9.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element9.setAttribute("maxlength",'30');
			element9.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell10.appendChild(element9);
			
			var cell11 = row.insertCell(10);
			var element10 = document.createElement("input");
			element10.type = "text";
			element10.name = "retEventFST["+(rowCount-1)+"]";
			//element10.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element10.id = "retEventFST["+(rowCount-1)+"]";
			element10.className = "inputBox";
			element10.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element10.setAttribute("maxlength",'30');
			element10.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell11.appendChild(element10);
			
			var cell12 = row.insertCell(11);
			var element11 = document.createElement("input");
			element11.type = "text";
			element11.name = "retEventSST["+(rowCount-1)+"]";
			//element11.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element11.id = "retEventSST["+(rowCount-1)+"]";
			element11.className = "inputBox";
			element11.setAttribute("onkeyup", "middleMinusRestriction(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element11.setAttribute("maxlength",'30');
			element11.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell12.appendChild(element11);
			
			var cell13 = row.insertCell(12);
			cell13.setAttribute("align","center");
			var element12 = document.createElement("input");
			element12.type = "button";
			element12.value="Delete";
			element12.setAttribute("onclick", "deleteRow('"+(rowCount-1)+"')");
			element12.className="btn btn-sm btn-danger"
			var element13 = document.createElement("input");
			element13.type = "hidden";
			element13.name = "coverpreVal["+(rowCount-1)+"]";
      		element13.id = "coverpreVal"+(rowCount-1);
      		element13.value='';
			cell13.appendChild(element12);
			cell13.appendChild(element13);
			 document.getElementById("loopcount").value =parseInt(rowCount);			 
			 
			 
}
function createcoverdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartId["+(rowCount-1)+"]";
      	element.id = "coverdepartId["+(rowCount-1)+"]";
       element.className = "inputBox";
      	element.setAttribute("onchange", "getAjaxCover(this.value,'coverdepartid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function createcoversubdeptCell(cell, rowCount){

	element = document.createElement("select");
         element.name = "coversubdepartId["+(rowCount-1)+"]";
      	element.id = "coversubdepartId["+(rowCount-1)+"]";
       element.className = "inputBoxS coversubdepartId";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoversubdept(element);
          cell.appendChild(element);
}
function createRetBusinessTypeCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "retBusinessType["+(rowCount-1)+"]";
      	element.id = "retBusinessType["+(rowCount-1)+"]";
       element.className = "inputBoxS";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateRetBusinessType(element);
          cell.appendChild(element);
}

function createRetTypeCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "retType["+(rowCount-1)+"]";
      	element.id = "retType["+(rowCount-1)+"]";
       element.className = "inputBoxS";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateRetType(element);
          cell.appendChild(element);
}
function createBasisCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "retBasis["+(rowCount-1)+"]";
      	element.id = "retBasis["+(rowCount-1)+"]";
       element.className = "inputBoxS";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateBasisType(element);
          cell.appendChild(element);
}

function populateCoverdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='retdepartIdlist'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_DEPARTMENT_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_DEPARTMENT_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function populateCoversubdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = 'None';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='subProfitList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_SPFC_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_SPFC_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 
 function populateRetBusinessType(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
          <s:iterator value='retBusinessTypeList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
         	
 }
 function populateRetType(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
          <s:iterator value='retTypeList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
         	
 }
 function populateBasisType(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
         
          
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
          <s:iterator value='basicTypeList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='DETAIL_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TYPE' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
         	
 }
 function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
 function deleteRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		postFormRequest('${pageContext.request.contextPath}/removeCedentRetentionRiskDetails.action?mode=delete&deleteId='+val+'&dropDown=retention', "retentionid", "proportional"); 
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
function getAllValue(count){
	var val = $("#coversubdepartId"+count).val();
    var val1 =document.getElementById("coverpreVal"+count).value;
    
    if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
    $("#coversubdepartId"+count).multiselect('clearSelection');
    val = removeElementsWithValue(val, 'ALL');
    $("#coversubdepartId"+count).val(val);
     $("#coversubdepartId"+count).multiselect("refresh");
     document.getElementById("coverpreVal"+count).value = '';
    }
     else if (val !=null && val[0]=='ALL' ) {
    	$("#coversubdepartId"+count).multiselect('clearSelection');
    	$("#coversubdepartId"+count).val('ALL');
    	 $("#coversubdepartId"+count).multiselect("refresh");
    	 document.getElementById("coverpreVal"+count).value = 'ALL';
    }
}	

function removeElementsWithValue(arr, val) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === val) {
            arr.splice(i, 1);
        }
    }
    return arr;
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

</body>
</html>
