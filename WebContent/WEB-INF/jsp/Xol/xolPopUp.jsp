<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
   

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
<%-- <s:form id="reinstatement" name="reinstatement" theme="simple" action="" method="post" > --%>
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
			<table class="table table-bordered" id="bonusTbl" width="100%" >
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
					 					<s:textfield name="reinstatementNo[%{#stat.count-1}]" id="reinstatementNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
					 					</td>
										<td>
										<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  disabled="true" theme="simple"/>
										</td>
										<td>
										<s:textfield name="reinstatementAmount[%{#stat.count-1}]" id="reinstatementAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true" theme="simple" />
										</td>
										<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
										<td>
										<s:textfield name="reinstatementMinAmount[%{#stat.count-1}]" id="reinstatementMinAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" disabled="true" theme="simple"/>
										</td>
										<td>
										<s:textfield name="reinstatementMinTime[%{#stat.count-1}]" id="reinstatementMinTime[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" disabled="true" theme="simple"/>
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
			<s:textfield name="anualAggregateLiabilityTemp" id="anualAggregateLiabilityTemp" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16" readonly="true" />
		</div>
	</div>
			<div class="boxcontent">
			<div id="newgenid1">
			<table class="table table-bordered" width="100%" id="newgen1">
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
										<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdRe[%{#stat.count-1}]" id="coverdepartIdRe[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="true"/>
										<s:if test='0==(#stat.count-1)'>
										<s:text name="(Main Class)" />
										</s:if>
										</td>
										<td>
										<s:textfield name="coverLimitOCRe[%{#stat.count-1}]" id="coverLimitOCRe[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='true'/>
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
				<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
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
			<table class="table table-bordered" id="bonusTbl" width="100%" >								
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
		 					<s:textfield name="reinstatementNo[%{#stat.count-1}]" id="reinstatementNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
		 					</td>
		 					<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
							<td >
							<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  onchange="getEditFields(this.value,%{#stat.count-1});" theme="simple"/>
							</td>
							</s:if>
							<s:else>
							<td >
							<s:select  list="reinstatementTypeListVar"  name="reinstatementTypeId[%{#stat.count-1}]" id="reinstatementTypeId[%{#stat.count-1}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="DETAIL_NAME" listValue="REMARKS"  theme="simple"/>
							</td>
							</s:else>
							<td >
							<s:textfield name="reinstatementAmount[%{#stat.count-1}]" id="reinstatementAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
							</td>
							<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
							 <td >
							<s:textfield name="reinstatementMinAmount[%{#stat.count-1}]" id="reinstatementMinAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
							</td>
							<td >
							<s:textfield name="reinstatementMinTime[%{#stat.count-1}]" id="reinstatementMinTime[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
							</td>
							</s:if>
							<td align="center">
							<s:if test='0!=(#stat.count-1)'>
							<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="disableForm(this.form,false,'');removeRowReins('<s:property value="%{#stat.count-1}"/>');return false;" />
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
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="ReinsRow('bonusTbl');" />
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
			<s:textfield name="anualAggregateLiabilityTemp" id="anualAggregateLiabilityTemp" onkeyup="allow2DigitDecValues(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16"  />
			<s:hidden name="annualTemp" id="annualTemp" value="%{anualAggregateLiability}"></s:hidden>
		</div>
	</div>
			<div class="boxcontent">
			<div id="newgenid1">
			<table class="table table-bordered" width="100%" id="newgen1">
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
										<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdRe[%{#stat.count-1}]" id="coverdepartIdRe[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="true"/>
										<s:if test='0==(#stat.count-1)'>
										<s:text name="(Main Class)" />
										</s:if>
										</td>
										<td >
										<s:textfield name="coverLimitOCRe[%{#stat.count-1}]" id="coverLimitOCRe[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}'/>
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
			
			<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
		</div>
</s:else>
 
   
  <%--  </s:form> --%>
   </div>
   </div>
   </div>
   </div>
</div>
</div>
</div>
</div>	

<script type="text/javascript">



</script>

	</body>
</html>
