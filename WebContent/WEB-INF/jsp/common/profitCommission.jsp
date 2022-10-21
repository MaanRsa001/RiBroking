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
                <%-- <s:form id="commissionpopup" name="commissionpopup" theme="simple" action="" method="post" > --%>
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
							<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
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
								<div id="tb2">
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
								 					<s:textfield name="commissionSNo[%{#stat.count-1}]" id="commissionSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
								 					</td>
													<td >
													<s:textfield name="commissionFrom[%{#stat.count-1}]" id="commissionFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
													</td>
													<td >
													<s:textfield name="commissionTo[%{#stat.count-1}]" id="commissionTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
													</td>
													<td >
													<s:textfield name="commissionPer[%{#stat.count-1}]" id="commissionPer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
													</td>
													<td>
													<s:if test='0!=(#stat.count-1)'>
													<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="commissionremoveRow('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
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
									<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitPC();return false;" class="btn btn-sm btn-success" />
									<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
								</div>
						</div>
                    </div>
                    </s:else>
                    <s:hidden name="pageFor" id="pageFor"/>
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
