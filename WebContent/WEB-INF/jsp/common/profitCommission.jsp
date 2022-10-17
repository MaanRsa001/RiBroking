<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <sj:head jqueryui="true" jquerytheme="start"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
		<!-- <link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/> -->
    	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
	 <style>
		.button {background-color: #4CAF50; /* Green */}
		
		.button2 {background-color: #008CBA;} /* Blue */
		.button3 {background-color: #f44336;} /* Red */ 
		.button4 {background-color: #e7e7e7; color: black;} /* Gray */ 
		.button5 {background-color: #555555;} /* Black */
</style>
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
<div class="tablerow">
<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
<div class="tablerow">
<div style="padding:10px; background:#F8F8F8">
<div class="boxcontent">
<div class="panel panel-primary">
	<div class="panel-heading">
		 Commission Type Details
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
</div>
                <s:form id="commissionpopup" name="commissionpopup" theme="simple" action="" method="post" >
                <s:set var="commissionTypeListVar" value="commissionTypeBasedList"/>
                   <s:if test="'view'.equals(mode) || !''.equals(layerProposalNo)">
                   <div class="table2">
                   <div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
							<span style="color:green;"><s:actionmessage/></span>
					</div>
                    <div class="panel-body">
                    
                    	<div class="row" >
							<div >
							<table class="table table-bordered" id="bonusTbl" width="100%" >									
							<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<s:if test='"PR".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Commission %" /> </th>
											</s:if>
											<s:elseif test='"LR".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Commission %" /> </th>
											</s:elseif>
											<s:elseif test='"PC".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Super Profit Commission %" /> </th>
											</s:elseif>
										</tr>
							</thead>
							<tbody>
									<s:iterator value="commissionTypeListVar" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="commissionSNo[%{#stat.count-1}]" id="commissionSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td>
											<s:textfield name="commissionFrom[%{#stat.count-1}]" id="commissionFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
											</td>
											<td>
											<s:textfield name="commissionTo[%{#stat.count-1}]" id="commissionTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
											</td>
											<td>
											<s:textfield name="commissionPer[%{#stat.count-1}]" id="commissionPer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true"/>
											</td>
										</tr>
									</s:iterator>
							</tbody>
								</table>
							</div>
						</div>
						<br class="clear"/>
						<div class="boxcontent" align="center">
							<s:if test="'view'.equals(mode) ">
							<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');getPrint()"/>
							</s:if>
							<s:else>
							<%--<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />--%>
							</s:else>							
							<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
					   </div>
					</div>
                    </div>
                   </s:if>
                   <s:else>
                   <div class="table2">
                   		<div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
						</div>
                    	<div class="panel-body">
								<div class="row" >
								<div >
						<table class="table table-bordered" id="bonusTbl" width="100%" >										
						<thead>
						<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<s:if test='"PR".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Commission %" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
											</s:if>
											<s:elseif test='"LR".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Commission %" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
											</s:elseif>
											<s:elseif test='"PC".equals(commissionType)'>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Profit Ratio % To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Super Profit Commission %" /> </th>
											<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
											</s:elseif>
										</tr>
										</thead>
											<tbody>
											<s:iterator value="commissionTypeListVar" var="list" status="stat">
												<tr>
													<td>
								 					<s:textfield name="commissionSNo[%{#stat.count-1}]" id="commissionSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
								 					</td>
													<td >
													<s:textfield name="commissionFrom[%{#stat.count-1}]" id="commissionFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" />
													</td>
													<td >
													<s:textfield name="commissionTo[%{#stat.count-1}]" id="commissionTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" />
													</td>
													<td >
													<s:textfield name="commissionPer[%{#stat.count-1}]" id="commissionPer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26"/>
													</td>
													<td>
													<s:if test='0!=(#stat.count-1)'>
													<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="commissionremoveRow('<s:property value="%{#stat.count-1}"/>')" />
													</s:if>
													</td>
												</tr>
											</s:iterator>
											</tbody>
									</table>
								</div>
								<div class="boxcontent" align="right">
									<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="commissioninsRow('bonusTbl');" />
								</div>
								</div>
								<div class="boxcontent" align="left">
								<b>Note :</b> Please enter the slabs in ascending order
								</div>
								<div class="boxcontent" align="center">
									<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
									<input type="button" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
								</div>
						</div>
                    </div>
                    </s:else>
                    <s:hidden name="pageFor" id="pageFor"/>
                   	<s:hidden name="Proposal_no" id="Proposal_no"/>
                   	<s:hidden name="amendId" id="amendId"/>
                   	<s:hidden name="contractNo" id="contractNo"/>
                   	<s:hidden name="layerProposalNo" id="layerProposalNo"/>
                   	<s:hidden name="loopcount" id="loopcount"></s:hidden>
                   	<s:hidden name="commissionType" id="commissionType"/>
                    </s:form>
                </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>	


<script type="text/javascript">

function commissionremoveRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			document.commissionpopup.action="${pageContext.request.contextPath}/removeRowRiskDetails.action?mode=delete&Flag=commission&deleteId="+val;
			document.commissionpopup.submit();
			}
		}
}


function commissioninsRow(tableID)
{
	var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "commissionSNo["+(rowCount-1)+"]";
       		element1.id = "commissionSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			
			var cell2 = row.insertCell(1);
			//cell2.setAttribute('style','background-color:#C6E2FF;');
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "commissionFrom["+(rowCount-1)+"]";
      		element2.id = "commissionFrom["+(rowCount-1)+"]";
			element2.className = "inputBox";
			element2.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "commissionTo["+(rowCount-1)+"]";
      		element3.id = "commissionTo["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("style", "text-align:right;");
			element3.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "commissionPer["+(rowCount-1)+"]";
      		element4.id = "commissionPer["+(rowCount-1)+"]";
			element4.className = "inputBox";
			element4.setAttribute("style", "text-align:right;");
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			//element5.setAttribute("onclick", "deleteRow('bonusTbl','"+(rowCount)+"')");
			element5.setAttribute("onclick", "commissionremoveRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-info"
			cell5.appendChild(element5);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}

function fnSubmitScale(){
	document.commissionpopup.action="${pageContext.request.contextPath}/insProfieCommissionRiskDetails.action";
	document.commissionpopup.submit();
}

function getPrint()
	{
		document.getElementById("close").style.display='none';
		document.getElementById("print").style.display='none';
		window.print();
		document.getElementById("close").style.display='inline';
		document.getElementById("print").style.display='inline';
	}
</script>

	</body>
</html>
