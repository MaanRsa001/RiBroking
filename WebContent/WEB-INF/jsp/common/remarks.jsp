<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<!DOCTYPE html>
<html>
<head>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	
</head>
	<div class="boxcontent" >
		<div class="panel panel-primary">											
			<div class="panel-heading">
				<s:text name="label.exclusionRemarks" /> 
			</div>
			<div class="panel-body">
				<div class="boxcontent">
					<div class="textfieldA25">
						<s:text name="label.exclusion" />
					</div>
					<div class="textfieldA75">
						<s:textarea id="exclusion" name="exclusion" rows="3" cssClass="inputBoxA" cssStyle="width: 100%; resize: vertical; "/>
						<br/>
						<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text name="Characters Remaining" /> </span>
					</div>
					<br class="clear"/>
				</div>
				<div class="boxcontent">
				<div id="remarkid">
				<table class="table table-bordered" width="100%" id="remark">
								<thead>
									<tr>
										<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
										<th width="10%" style="text-align: center; vertical-align: middle;"> <s:text name="label.description" />  </th>
										<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark1" />  </th>
										<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark2" /> </th>
										<th width="4%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
									</tr>
								</thead>
								<tbody>
								<s:iterator value="remarkList" var="list" status="stat">
									<tr>
											<td >
						 					<s:textfield name="remarkSNo[%{#stat.count-1}]" id="remarkSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td>
											<s:textfield name="description[%{#stat.count-1}]" id="description[%{#stat.count-1}]"  cssClass = "inputBox"  />
											
											</td>
											<td>
											<s:textarea name="remark1[%{#stat.count-1}]" id="remark1[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; "  />
											</td>
											<td>
											<s:textarea name="remark2[%{#stat.count-1}]" id="remark2[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " />
											</td>
											<td align="center">
											<s:if test='0!=(#stat.count-1)'>
											<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRemark('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											</td>
											<s:hidden name="remarkCount" id="remarkCount" ></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
									</table>
									<br class="clear"/>
									<div class="boxcontent" align="center">
										<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRemarkRow('remark');" />
									</div>
								</div>
							</div>
					</div>
			</div>
	</div>

<script>
function deleteRemark(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
	var oForm = document.forms[3]
		var status=confirm("Do you want to delete specified row");
		if(status){
		postFormRequest('${pageContext.request.contextPath}/removeRemarksXol.action?mode=delete&deleteId='+val+'&dropDown=remark', "remarkid", oForm.name); 
		}
		}
}
function insRemarkRow(tableID)
{
var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "remarkSNo["+(rowCount-1)+"]";
       		element1.id = "remarkSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       	
			var cell2 = row.insertCell(1);
			var element2 = document.createElement("input");
			element2.type = "textarea";
			element2.name = "description["+(rowCount-1)+"]";
      		element2.id = "description["+(rowCount-1)+"]";
      		//element2.value=document.getElementById("description["+(rowCount-2)+"]").value;
      		element2.setAttribute("style","width: 100%;  ");
			element2.className = "inputBox";
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			var element3 = document.createElement("textarea");
			//element3.type = "textarea";
			element3.name = "remark1["+(rowCount-1)+"]";
			//element3.value=document.getElementById("remark1["+(rowCount-2)+"]").value;
      		element3.id = "remark1["+(rowCount-1)+"]";
      		element3.setAttribute("style","width: 100%;  resize: vertical; ");
			element3.className = "inputBox";
			cell3.appendChild(element3);
			
			
			var cell4 = row.insertCell(3);
			var element4 = document.createElement("textarea");
			//element4.type = "textarea";
			element4.name = "remark2["+(rowCount-1)+"]";
			//element4.value=document.getElementById("remark2["+(rowCount-2)+"]").value;
      		element4.id = "remark2["+(rowCount-1)+"]";
      		element4.setAttribute("style","width: 100%;  resize: vertical; ");
			element4.className = "inputBox";
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			cell5.setAttribute("align","center");
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			element5.setAttribute("onclick", "deleteRemark('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-danger"
			cell5.appendChild(element5);
			
			 document.getElementById("loopcount").value =parseInt(rowCount);			 
			 
}

	</script>
</html>