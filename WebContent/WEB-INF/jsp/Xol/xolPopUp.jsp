<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		    
		<link rel="stylesheet" type="text/css" href="css/select2-3.4.1.css"/>
    	<link rel="stylesheet" type="text/css" href="css/select2-bootstrap.css"/>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap.min.css"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
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
		Reinstatement Premium Details
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
<s:form id="reinstatement" name="reinstatement" theme="simple" action="" method="post" >
  <s:set var="reinstatementTypeListVar" value="reinstatementTypeList"/>
 <s:if test="'view'.equals(mode)">
<div class="panel panel-primary">
	<div class="panel-heading">
		Section A - Type and Number of Reinstatements
	</div>
<div class="panel-body">
<div class="table2">
    <div class="panel-body">
         <div class="tablerow">
			<span style="color:red;"><s:actionerror/></span>
			<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
		</div>
       <div class="row">
				<div class="textfield">
						<div class="text">
							<s:text name="Reinstatements" />
				</div>
				<div class="tbox">
					<s:select  list="reinstatementOptionList"  name="reinstatementOption" id="reinstatementOption" cssClass="inputBoxS"   onchange="getDet(this.value)" listKey="DETAIL_NAME" listValue="REMARKS" disabled="true"/>
				</div>
				</div>
              </div>
		<div class="row" >
		<div >
			<table class="footable" id="bonusTbl" width="100%" >
				<thead>
						<tr>
							<th width="2%"  style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement No" />  </th>
						<th  width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement type"/> </th>
						<th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Amount % and/or Time %"/> </th>
						<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
						<th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Minimum Amount % (If Applicable)"/> </th>
						<th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Minimum Time %   (If Applicable)"/> </th>
						</s:if>
					</tr>
				</thead>
					<tbody>
							<s:iterator value="reInstatementDetailsList" var="list" status="stat">
								<tr>
										<td>
					 					<s:textfield name="reinstatementNo[%{#stat.count-1}]" id="reinstatementNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
					 					</td>
										<td>
										<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  disabled="true"/>
										</td>
										<td>
										<s:textfield name="reinstatementAmount[%{#stat.count-1}]" id="reinstatementAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true" />
										</td>
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
										<td>
										<s:textfield name="reinstatementMinAmount[%{#stat.count-1}]" id="reinstatementMinAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" disabled="true"/>
										</td>
										<td>
										<s:textfield name="reinstatementMinTime[%{#stat.count-1}]" id="reinstatementMinTime[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true"/>
										</td> 
										</s:if>
									</tr>
								</s:iterator>
						</tbody>
				</table>
			</div>
			<div class="textfield" >
				<div class="text">
					Total Number of Reinstatements 
				</div>
				<div class="tbox">
					<s:textfield name="totalNoOfRows" id="totalNoOfRows" cssClass="inputBox" cssStyle="text-align:right;"  readonly="true"/>
				</div>
			</div>
		</div>
		</div>
		</div>
		</div>
		</div>
<div class="panel panel-primary">
	<div class="panel-heading">
		Section B – Annual Aggregate Liability
	</div>
<div class="panel-body">
	<div class="textfield">
		<div class="text">
			<s:text name="label.annualAggregateLiability" />
		</div>
		<div class="tbox">													
			<s:textfield name="anualAggregateLiability" id="anualAggregateLiability" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16" readonly="true" />
		</div>
	</div>
			<div class="boxcontent">
			<div id="newgenid1">
			<table class="footable" width="100%" id="newgen1">
							<thead>
								<tr>
									<th width="15%"> <s:text name="label.Class" />  </th>
									<th width="15%"> <s:text name="label.annualAggregateLiability" />  </th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="CoverList" var="list" status="stat">
								<tr>
										<td>
										<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="true"/>
										<s:if test='0==(#stat.count-1)'>
										<s:text name="(Main Class)" />
										</s:if>
										</td>
										<td>
										<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='true' />
										<s:hidden name="hcoverLimitOC[%{#stat.count-1}]" id="hcoverLimitOC[%{#stat.count-1}]" ></s:hidden>
										</td>
										
									</tr>
									</s:iterator>
								</tbody>
								</table>
				</div>
			</div>
		</div>
		</div>
		
		<div class="boxcontent" align="center">
			<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');getPrint()"/>
			<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
		</div>

</s:if>
<s:else>
<div class="panel panel-primary">
	<div class="panel-heading">
		Section A - Type and Number of Reinstatements
	</div>
<div class="panel-body">
<div class="table2">
    <div class="panel-body">
         <div class="tablerow">
			<span style="color:red;"><s:actionerror/></span>
			<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
		</div>
       <div class="row">
           <div class="textfield">
				<div class="text">
					<s:text name="Reinstatements" />
				</div>
				<div class="tbox">
					<s:select  list="reinstatementOptionList"  name="reinstatementOption" id="reinstatementOption" cssClass="inputBoxS"   onchange="getDet(this.value);getAnnualAgg();" listKey="DETAIL_NAME" listValue="REMARKS" />
				</div>
			</div>
       </div>
		<div class="row" >
		<div >
			<table class="footable" id="bonusTbl" width="100%" >								
			<thead>
				<tr>
					<th width="2%"  style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement No" />  </th>
					<th  width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Reinstatement type"/> </th>
					<th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Amount % and/or Time %"/> </th>
					<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
					<th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Minimum Amount % (If Applicable)"/> </th>
					 <th width="16.5%"  style="text-align: center; vertical-align: middle;"> <s:text name="Minimum Time %   (If Applicable)"/> </th>
					 </s:if>
					<th width="10%"  style="text-align: center; vertical-align: middle;"> <s:text name="Delete"/> </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="reInstatementDetailsList"  var="list" status="stat">
					<tr>
							<td>
		 					<s:textfield name="reinstatementNo[%{#stat.count-1}]" id="reinstatementNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
		 					</td>
		 					<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
							<td >
							<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  onchange="getEditFields(this.value,%{#stat.count-1});"/>
							</td>
							</s:if>
							<s:else>
							<td >
							<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  />
							</td>
							</s:else>
							<td >
							<s:textfield name="reinstatementAmount[%{#stat.count-1}]" id="reinstatementAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
							</td>
							<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
							 <td >
							<s:textfield name="reinstatementMinAmount[%{#stat.count-1}]" id="reinstatementMinAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>
							</td>
							<td >
							<s:textfield name="reinstatementMinTime[%{#stat.count-1}]" id="reinstatementMinTime[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
							</td>
							</s:if>
							<td align="center">
							<s:if test='0!=(#stat.count-1)'>
							<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="disableForm(this.form,false,'');removeRow('<s:property value="%{#stat.count-1}"/>');return false;" />
							</s:if>
							</td>
							<s:if test='"RI01".equals(#session.SOURCE_CODE)'>
							<s:hidden  name="reinstatementMinTime[%{#stat.count-1}]" id="reinstatementMinTime[%{#stat.count-1}]"/>
							<s:hidden  name="reinstatementMinAmount[%{#stat.count-1}]" id="reinstatementMinAmount[%{#stat.count-1}]"/>
							</s:if>
						</tr>
					</s:iterator>
				</tbody>
				</table>
			</div>
			<div class="textfield" >
				<div class="text">
					Total Number of Reinstatements 
				</div>
				<div class="tbox">
							<s:textfield name="totalNoOfRows" id="totalNoOfRows" cssClass="inputBox" cssStyle="text-align:right;"  readonly="true"/>
				</div>
			</div>
			<div class="boxcontent" align="right">
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('bonusTbl');" />
			</div>
		</div>
		
   </div>
   </div>
   </div>
   </div>
   <div class="panel panel-primary">
	<div class="panel-heading">
		Section B – Annual Aggregate Liability
	</div>
<div class="panel-body">
	<div class="textfield">
		<div class="text">
			<s:text name="label.annualAggregateLiability" />
		</div>
		<div class="tbox">													
			<s:textfield name="anualAggregateLiability" id="anualAggregateLiability" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16"  />
			<s:hidden name="annualTemp" id="annualTemp" value="%{anualAggregateLiability}"></s:hidden>
		</div>
	</div>
			<div class="boxcontent">
			<div id="newgenid1">
			<table class="footable" width="100%" id="newgen1">
							<thead>
								<tr>
									<th width="15%"> <s:text name="label.Class" />  </th>
									<th width="15%"> <s:text name="label.annualAggregateLiability" />  </th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="CoverList" var="list" status="stat">
								<tr>
										<td>
										<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="true"/>
										<s:if test='0==(#stat.count-1)'>
										<s:text name="(Main Class)" />
										</s:if>
										</td>
										<td >
										<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
										<s:hidden name="hcoverLimitOC[%{#stat.count-1}]" id="hcoverLimitOC[%{#stat.count-1}]" ></s:hidden>
										</td>
										
									</tr>
									</s:iterator>
								</tbody>
								</table>
				</div>
			</div>
		</div>
		</div>


   <div class="boxcontent" align="center">
   			
			<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmit();return false;" class="btn btn-sm btn-success" />
			
			<input type="button" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
		</div>
</s:else>
  <s:hidden name="proposal_no" id="proposal_no"/>
  <s:hidden name="Typename" id="Typename"/>
  <s:hidden name="amendId" id="amendId"/>
  <s:hidden name="contractNo" id="contractNo"/>
  <s:hidden name="loopcount" id="loopcount"></s:hidden>
  <s:hidden name="layerProposalNo" id="layerProposalNo"/>
  <s:hidden name="flag" id="flag"/>
  <s:hidden name="pageFor" id="pageFor"/>
   <%--<s:hidden name="anualAggregateLiability" id="anualAggregateLiability"/>--%>
   <s:hidden name="layerNo" id="layerNo"/>
   
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
function fnSubmit(){
	var anual=document.getElementById("anualAggregateLiability").value;
	window.opener.document.getElementById("anualAggregateLiability").value =anual;
	document.reinstatement.action="${pageContext.request.contextPath}/insReinstatementDetailsXol.action";
	document.reinstatement.submit();
}
function removeRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			document.reinstatement.action="${pageContext.request.contextPath}/removeRowXol.action?mode=delete&deleteId="+val;
			document.reinstatement.submit();
			}
		}
}

if('<s:property value="errorList"/>'==''){
getDet('<s:property value="reinstatementOption"/>');
}
function getDet(val){
	val =document.getElementById("reinstatementOption").value;
	if(val=="S"){
	     	//document.getElementById('am').style.display = 'block';
			var table = document.getElementById('bonusTbl');
			var rowCount = table.rows.length;
			document.getElementById("totalNoOfRows").value = rowCount-1;
			
			       	} 
	       	else if(val=="U"){
	     		//document.getElementById('am').style.display = 'none';
	     		document.getElementById('totalNoOfRows').value = "NA";
	       	}
	       	getAnnualAgg();
}


function insRow(tableID)
{
	var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			var tot = document.getElementById("totalNoOfRows").value;
			if(tot !="NA"){
			document.getElementById("totalNoOfRows").value = rowCount;
			}
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "reinstatementNo["+(rowCount-1)+"]";
       		element1.id = "reinstatementNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			
			var cell2 = row.insertCell(1);
			//cell2.setAttribute('style','background-color:#C6E2FF;');
			createreinstatementCell(cell2, rowCount)
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "reinstatementAmount["+(rowCount-1)+"]";
      		element3.id = "reinstatementAmount["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.value=document.getElementById("reinstatementAmount["+(rowCount-2)+"]").value;  
			element3.setAttribute("style", "text-align:right;");
			element3.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "reinstatementMinAmount["+(rowCount-1)+"]";
      		element4.id = "reinstatementMinAmount["+(rowCount-1)+"]";
      		element4.value=document.getElementById("reinstatementMinAmount["+(rowCount-2)+"]").value; 
			element4.className = "inputBox";
			element4.setAttribute("style", "text-align:right;");
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			//cell5.setAttribute('style','background-color:#C6E2FF;');
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "reinstatementMinTime["+(rowCount-1)+"]";
      		element5.id = "reinstatementMinTime["+(rowCount-1)+"]";
      		element5.value=document.getElementById("reinstatementMinTime["+(rowCount-2)+"]").value;
			element5.className = "inputBox";
			element5.setAttribute("style", "text-align:right;");
			element5.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell5.appendChild(element5);
			
			var cell6 = row.insertCell(5);
			cell6.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');removeRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-info"
			cell6.appendChild(element6);
			</s:if>
			<s:else>
			
			var cell4 = row.insertCell(3);
			cell4.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');removeRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-info"
			var element7 = document.createElement("input");
			element7.type = "hidden";
			element7.name = "reinstatementMinTime["+(rowCount-1)+"]";
      		element7.id = "reinstatementMinTime"+(rowCount-1);
      		element7.value='';
      		var element8 = document.createElement("input");
      		element8.type = "hidden";
			element8.name = "reinstatementMinAmount["+(rowCount-1)+"]";
      		element8.id = "reinstatementMinAmount"+(rowCount-1);
      		element8.value='';
      		cell4.appendChild(element7);
      		cell4.appendChild(element8);
			cell4.appendChild(element6);
			</s:else>
			<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
			getEditFields(document.getElementById("reinstatementTypeId["+(rowCount-1)+"]").value,rowCount-1);
			</s:if>
			document.getElementById("loopcount").value =parseInt(rowCount);
		getAnnualAgg();	
			
}
function getAnnualAgg(){
var val =document.getElementById("reinstatementOption").value;
var noofrows = document.getElementById("totalNoOfRows").value;
noofrows = parseInt(noofrows)+1;
var total=0;
	if(val=="S"){
		<s:iterator value="CoverList"  var="list" status="stat">
		 var i = <s:property value="%{#stat.count-1}"/>;
		 var val= document.getElementById("hcoverLimitOC["+(i)+"]").value;
		 if(val==''){
		 val ="0";
		 }else{
		 val=val.replace(new RegExp(',', 'g'),'');
		 }
		 document.getElementById("coverLimitOC["+(i)+"]").value=Comma(((parseInt(val))*(parseInt(noofrows))).toFixed(2));
		  total=parseInt(total)+parseInt(val);
		 </s:iterator>
		 document.getElementById("anualAggregateLiability").value=Comma(((parseInt(total))*(parseInt(noofrows))).toFixed(2));
	}else{
		<s:iterator value="CoverList"  var="list" status="stat">
		 var i = <s:property value="%{#stat.count-1}"/>;
		 var val= document.getElementById("hcoverLimitOC["+(i)+"]").value;
		 if(val==''){
		 val ="0";
		 }else{
		 val=val.replace(new RegExp(',', 'g'),'');
		 }
		 document.getElementById("coverLimitOC["+(i)+"]").value=Comma(((parseInt(val))).toFixed(2));
		 total=parseInt(total)+parseInt(val);
		 </s:iterator>
	document.getElementById("anualAggregateLiability").value=Comma(total.toFixed(2));
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
	function createreinstatementCell(cell, rowCount){
	element = document.createElement("select");
    element.name = "reinstatementTypeId["+(rowCount-1)+"]";
  	element.id = "reinstatementTypeId["+(rowCount-1)+"]";
  	<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
    	element.setAttribute("onchange", "getEditFields(this.value,"+(rowCount-1)+")"); 
    </s:if>
    element.className = "inputBox";            
    populate(element);
     element.value=document.getElementById("reinstatementTypeId["+(rowCount-2)+"]").value; 
       //var mylist = document.getElementById("reinstatementTypeId["+(rowCount-2)+"]").value;
         //element.value= mylist;
          cell.appendChild(element);
}
function populate(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='reinstatementTypeListVar'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='REMARKS' />".replace("&amp;", "&");
				objOption.value = "<s:property value='DETAIL_NAME' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 <s:if test='"RI02".equals(#session.SOURCE_CODE)'>
<s:iterator value="reInstatementDetailsList"  var="list" status="stat">
 var i = <s:property value="%{#stat.count-1}"/>;
  getEditFields(document.getElementById("reinstatementTypeId["+(i)+"]").value,i);
 </s:iterator>
 function getEditFields(val,id){
 if(val=="PRA" ){
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").readOnly=false;
 	document.getElementById("reinstatementMinTime"+"["+id+"]").readOnly=true;
 	document.getElementById("reinstatementMinTime"+"["+id+"]").value="";
 }
 else if(val=="PRT" ){
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").readOnly=true;
 	document.getElementById("reinstatementMinTime"+"["+id+"]").readOnly=false;
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").value="";
 }
 else  if(val=="PRAT"){
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").readOnly=false;
 	document.getElementById("reinstatementMinTime"+"["+id+"]").readOnly=false;
 }
 else{
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").readOnly=true;
 	document.getElementById("reinstatementMinTime"+"["+id+"]").readOnly=true;
 	document.getElementById("reinstatementMinAmount"+"["+id+"]").value="";
 	document.getElementById("reinstatementMinTime"+"["+id+"]").value="";
 }
 }
 </s:if>
 
 
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
