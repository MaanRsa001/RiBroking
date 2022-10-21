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
<s:if test="'scale'.equals(pageFor)">
<s:set name="profitCommissionListVar" value="profitCommissionList"/>
<div class="table0" style="width: 100%; margin: 0 auto;">
<div class="tablerow">
<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
<div class="tablerow">
<div style="padding:10px; background:#F8F8F8">
<div class="boxcontent">
<div class="panel panel-primary">
	<div class="panel-heading">
		  Sliding Scale Commission Details
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

               <%--  <s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" > --%>
                <s:set var="scaleCommissionListVar" value="scaleCommissionList"/>
                   <s:if test="'view'.equals(mode) || !''.equals(layerProposalNo)">
                   <div class="table2">
                   <div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
							<span style="color:green;"><s:actionmessage/></span>
					</div>
					<s:if test='"1".equals(treatyType) || "2".equals(treatyType)||"3".equals(treatyType)'>
					<div class="panel panel-primary" >
						<div class="panel-body">
			 					<div class="textfield">
										<div class="text">
											<s:text name="label.quotaShare" />
										</div>
										<div class="tbox">
											<s:select  list="quotaShareList"  name="quotaShare" id="quotaShare" cssClass="inputBox"   headerKey="" headerValue="---Select---" listValue="DETAIL_NAME"  listKey="REMARKS"  disabled="true"/>
										</div>
								</div>	  
						</div>
					</div>
					</s:if>
					<div class="panel panel-primary">
                    	<div class="panel-body">
							<table class="table table-bordered" id="bonusTbl" width="100%" >									
							<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Sliding Scale Commission %" /> </th>
										</tr>
							</thead>
							<tbody>
									<s:iterator value="scaleCommissionListVar" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
						 					</td>
											<td>
											<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
											</td>
											<td>
											<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
											</td>
											<td>
											<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true" theme="simple"/>
											</td>
										</tr>
									</s:iterator>
							</tbody>
								</table>
								<br class="clear"/>
								<div >
									<div class="text" style="width: 20%">
										<s:text name="Remarks" />
									</div>
									<div class="tbox" style="width: 80%">
											<s:textarea rows="3" name="bonusremarks" id="bonusremarks" cssClass="inputBoxA"  cssStyle="width: 100%;" readonly="true"/><br/>
										<br/>
									</div>
								</div>
								
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-body">
							
								<div class="textfield">
									<div class="text">
										<s:text name="label.firstslideprofitcom" />
									</div>
									<div class="tbox">
											<s:textfield name="scfistpc" id="scfistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);"   readonly="true"/>
									<s:select  list="profitCommissionListVar"  name="scprofitMont" id="scprofitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
									</div>
								</div>	
								
								<div class="textfield" >
									<div class="text">
										<s:text name="label.subpsliderofitcom" />
									</div>
									<div class="tbox">
											<s:textfield name="scsubpc" id="scsubpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);" maxlength="10"   readonly="true"/>
											<s:select  list="profitCommissionListVar"  name="scsubProfitMonth" id="scsubProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"  disabled="true"/>
									</div>
								</div>
								<div class="textfield" >
									<div class="text">
										<s:text name="label.subseqcal" />
									</div>
									<div class="tbox">
										<s:radio list="#{'Y':'Yes','N':'No'}" name="scsubSeqCalculation" value="scsubSeqCalculation==null || scsubSeqCalculation==''?'N':scsubSeqCalculation"   disabled='true'></s:radio><br/>	
									</div>
								</div>	
								</div>
						</div>
						<br class="clear"/>
						<div class="boxcontent" align="center">
							<s:if test="'view'.equals(mode) ">
							<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');getPrint()"/>
							</s:if>
							<%--<s:else>
							<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
							</s:else>	--%>						
							<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
					   </div>
					</div>
                   </s:if>
                   <s:else>
                   <div class="table2">
                   		<div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
						</div>
						<s:if test='"1".equals(treatyType) || "2".equals(treatyType)||"3".equals(treatyType)'>
						<div class="panel panel-primary">
	                    	<div class="panel-body">
								<div class="textfield">
									<div class="text">
										<s:text name="label.quotaShare" />
									</div>
									<div class="tbox">
										<s:select  list="quotaShareList"  name="quotaShare" id="quotaShare" cssClass="inputBox"   headerKey="" headerValue="---Select---" listValue="DETAIL_NAME"  listKey="REMARKS"  />
									</div>
								</div>	                    	
							</div>
	                    </div>
	                    </s:if>
	                    <div class="panel panel-primary">
	                    	<div class="panel-body">
								<div class="textfield">
									<div class="text">
										<s:text name="label.provisionCom" />
									</div>
									<div class="tbox">
										<s:textfield name="provisionCom" id="provisionCom" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
									</div>
								</div>
								<div class="textfield">
									<div class="text">
										<s:text name="label.scalementhod" />
									</div>
									<div class="tbox">
										<s:select  list="slidingScaleMethodList"  name="scalementhod" id="scalementhod" cssClass="inputBox"   headerKey="" headerValue="---Select---" listValue="DETAIL_NAME"  listKey="TYPE"  onchange="getMethod(this.value);"/>
									</div>
								</div>
								</div>
								</div>
								<div class="panel panel-primary">
	                    		<div class="panel-body">	
								<div id="methodida">
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalemin" />
										</div>
										<div class="tbox">
											<s:textfield name="scaleminRatio" id="scaleminRatio" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalemax" />
										</div>
										<div class="tbox">
											<s:textfield name="scalemaxRatio" id="scalemaxRatio" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalecombine" />
										</div>
										<div class="tbox">
											<s:textfield name="scalecombine" id="scalecombine" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalebanding" />
										</div>
										<div class="tbox">
											<s:textfield name="scalebanding" id="scalebanding" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scaledigit" />
										</div>
										<div class="tbox">
											<s:textfield name="scaledigit" id="scaledigit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>	
								</div> 
								<div id="methodidb" style="display:none">
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalelossratioFrom" />
										</div>
										<div class="tbox">
											<s:textfield name="scalelossratioFrom" id="scalelossratioFrom" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scalelossratioTo" />
										</div>
										<div class="tbox">
											<s:textfield name="scalelossratioTo" id="scalelossratioTo" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scaledeltalossratio" />
										</div>
										<div class="tbox">
											<s:textfield name="scaledeltalossratio" id="scaledeltalossratio" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
									<div class="textfield">
										<div class="text">
											<s:text name="label.scaledeltacommission" />
										</div>
										<div class="tbox">
											<s:textfield name="scaledeltacommission" id="scaledeltacommission" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);"/>
										</div>
									</div>
								</div>                    	
							</div>
							<div class="boxcontent" align="center">
								<input type="button"  value="Calculate"  class="btn btn-sm btn-primary" onclick="scalecalculate();" /><s:property value="scaleCommissionListVar.size()"/>
							</div>
							<br/>
	                    </div>
	                    <s:if test="scaleCommissionList!=null">
						<div class="panel panel-primary"  id="scalegrid">
	                    	<div class="panel-body">
	                    	<div id="tb1" >
							<table class="table table-bordered" id="bonusTbl" width="100%" >										
								<thead>
									<tr>
										<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
										<th width="30%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
										<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
										<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Sliding Scale Commission %" /> </th>
										<%-- <th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th> --%>
									</tr>
								</thead>
									<tbody>
									<s:iterator value="scaleCommissionList" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
						 					</td>
											<td >
											<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
											</td>
											<td >
											<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
											</td >
											<td >
											<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
											</td>
											<%-- <td>
											<s:if test='0!=(#stat.count-1)'>
											<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="scaleremoveRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											</td> --%>
										</tr>
									</s:iterator>
									</tbody>
							</table>
							</div>
					
					<!-- <div class="boxcontent" align="right">
						<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="scaleinsRow('bonusTbl');" />
					</div> -->
					
					<div class="boxcontent" align="left">
					<b>Note :</b> Please enter the slabs in ascending order
					</div>
					<br/>
					<div >
						<div class="text" style="width: 20%">
							<s:text name="Remarks" />
						</div>
						<div class="tbox" style="width: 80%">
								<s:textarea rows="3" name="bonusremarks" id="bonusremarks" cssClass="inputBoxA"  cssStyle="width: 100%;"/><br/>
								<span class="textAreaRemaining"><label id="bonus_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
							<br/>
						</div>
						
					</div>
					</div>
					</div>
					</s:if>
					<div class="panel panel-primary">
					<div class="panel-body">
					
						<div class=textfieldA100>
							<div class="text" style="float: left;width: 25%;">
								<s:text name="label.firstslideprofitcom" />
							</div>
							<div style="float: left;width: 75%;">
							<s:select  list="slidingScalePeriodList"  name="fpcType" id="fpcType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="TYPE"  listValue="DETAIL_NAME"   cssStyle="width: 33.3%; float:left;" onchange="getscalePeriod(this.value);"/>
							<div id="scfiid" >
							<s:textfield name="scfistpc" id="scfistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 33.3%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);"  />
							</div>
							<div id="periodid" style="display:none"> 
							<s:textfield name="fpcfixedDate" id="periodDate"  cssClass="inputBox" cssStyle="width: 33.3%; float:left;"   />
							</div>
							<div id="scseid" >
							<s:select  list="profitCommissionListVar"  name="scprofitMont" id="scprofitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 33.3%; float:right;"/>
							</div>
							</div>
						</div>
						<br/>	
						<div class="textfield" >
							<div class="text">
								<s:text name="label.subseqcal" />
							</div>
							<div class="tbox">
								<s:radio list="#{'Y':'Yes','N':'No'}" name="scsubSeqCalculation" value="scsubSeqCalculation==null || scsubSeqCalculation==''?'N':scsubSeqCalculation" onchange="getScSubseqCal(this.value);" ></s:radio><br/>	
							</div>
						</div>
						<div class="textfield"  id="scsubseqcalid">
							<div class="text">
								<s:text name="label.subpsliderofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="scsubpc" id="scsubpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);" maxlength="10"  />
									<s:select  list="profitCommissionListVar"  name="scsubProfitMonth" id="scsubProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>
							
						</div>
					</div>
					<div class="boxcontent" align="center">
						<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
						<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
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

</s:if>
<s:elseif test="'lossparticipates'.equals(pageFor)">
<s:set name="profitCommissionListVar" value="profitCommissionList"/>
<div class="table0" style="width: 100%; margin: 0 auto;">
<div class="tablerow">
<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
<div class="tablerow">
<div style="padding:10px; background:#F8F8F8">
<div class="boxcontent">
<div class="boxcontent">
<div class="panel panel-primary">
	<div class="panel-heading">
		 Loss Participation / Corridor Details
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
                <%-- <s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" > --%>
                <s:set var="scaleCommissionListVar" value="scaleCommissionList"/>
                   <s:if test="'view'.equals(mode) || !''.equals(layerProposalNo) ">
                   <div class="table2">
	                  <div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span>
							<span style="color:green;"><s:actionmessage/></span>
					  </div>
					  <div class="panel panel-primary">
	                    <div class="panel-body">
		                   <div>
								<div class="text" style="width: 20%">
									<s:text name="Ratio Type" />
								</div>
								<div class="tbox" style="width: 80%">
									<s:radio name="bonusTypeId" id="bonusTypeId" list="#{'LC':'Loss Corridor','LP':'Loss Participation '}"  disabled="true" />													
								</div>
							</div>
	                    </div>
                    </div>
					  <div class="panel panel-primary">
	                    <div class="panel-body">
								<table class="table table-bordered" width="100%" id="bonusTbl">
									<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Claims Ratio From"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Claims Ratio To"/> </th>
											<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Participation of the Cedant %" /> </th>
										</tr>
									</thead>
										<tbody>
										<s:iterator value="scaleCommissionListVar" var="list" status="stat">
											<tr>
												<td>
							 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
							 					</td>
												<td>
												<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
												</td>
												<td>
												<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
												</td>
												<td>
												<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true" theme="simple"/>
												</td>
											</tr>
										</s:iterator>
										</tbody>
									</table>
									<br class="clear"/>
									<div >
										<div class="text" style="width: 20%">
											<s:text name="Remarks" />
										</div>
										<div class="tbox" style="width: 80%">
												<s:textarea rows="3" name="bonusremarks" id="bonusremarks" cssClass="inputBoxA"  cssStyle="width: 100%;" disabled="true"/><br/>
											<br/>
										</div>
									</div>
										
								</div>
							</div>
							<div class="panel panel-primary">
								<div class="panel-body">
								
									<div class="textfield">
										<div class="text">
											<s:text name="label.firstlossprofitcom" />
										</div>
										<div class="tbox">
												<s:textfield name="scfistpc" id="scfistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);"  disabled="true"/>
										<s:select  list="profitCommissionListVar"  name="scprofitMont" id="scprofitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
										</div>
									</div>	
									
									<div class="textfield" >
										<div class="text">
											<s:text name="label.sublossprofitcom" />
										</div>
										<div class="tbox">
												<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);" maxlength="10"   disabled="true"/>
												<s:select  list="profitCommissionListVar"  name="scsubProfitMonth" id="scsubProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
										</div>
									</div>
									<div class="textfield" >
										<div class="text">
											<s:text name="label.subseqcal" />
										</div>
										<div class="tbox">
											<s:radio list="#{'Y':'Yes','N':'No'}" name="scsubSeqCalculation" value="scsubSeqCalculation==null || scsubSeqCalculation==''?'N':scsubSeqCalculation"   disabled='true'></s:radio><br/>	
										</div>
									</div>
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
                   </s:if>
                   <s:else>
                    <div class="table2">
                   		<div class="tablerow">
							<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
						</div>
					<div class="panel panel-primary">
                   	 <div class="panel-body">
		                    <div>
								<div class="text" style="width: 20%">
									<s:text name="Ratio Type" />
								</div>
								<div class="tbox" style="width: 80%">
									<s:select  name="bonusTypeId" id="bonusTypeId" list="lossRationList" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="TYPE"  listValue="DETAIL_NAME"   cssStyle="width: 33.3%; float:left;" onchange="getLossRatio(this.value);"/>
									<s:iterator value="lossRationList" var="lossDetail">
										<s:hidden name="%{#lossDetail.TYPE}" id="%{#lossDetail.TYPE}" value="%{#lossDetail.DETAIL_NAME}"/>
									</s:iterator>
								</div>
							</div>
                    	</div>
                    	</div>
                    	<div class="panel panel-primary">
                    		<div class="panel-body">
                    	
								<div id="tb1">
									<table class="table table-bordered" width="100%" id="bonusTbl">
										<thead>
											<tr>
												<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
												<th width="30%" style="text-align: center; vertical-align: middle;"><span id="claimrfid"></span> <s:text name="From"/> </th>
												<th width="16.5%" style="text-align: center; vertical-align: middle;"><span id="claimrtid"></span> <s:text name="To "/> </th>
												<th width="16.5%" style="text-align: center; vertical-align: middle;"><span id="lossparid"></span> <s:text name="Loss Participation of the Cedant %" /> </th>
												<th width="16.5%" style="text-align: center; vertical-align: middle;"><span id="lossparmaxid"></span> <s:text name="Maximum Participation of Cedant%" /> </th>
												<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
											</tr>
										</thead>
											<tbody>
												<s:iterator value="scaleCommissionListVar" var="list" status="stat">
													<tr>
														<td>
									 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
									 					</td>
														<td >
														<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
														</td>
														<td >
														<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
														</td>
														<td >
														<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
														</td>
														<td >
														<s:textfield name="scalemaxpartpercent[%{#stat.count-1}]" id="scalemaxpartpercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
														</td>
														<td >
														<s:if test='0!=(#stat.count-1)'>
														<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="scaleremoveRow('<s:property value="%{#stat.count-1}"/>')" />
														</s:if>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
									<div class="boxcontent" align="right">
										<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="scaleinsRow('bonusTbl');" />
									</div>
								
								<div class="boxcontent" align="left">
									<b>Note :</b> Please enter the slabs in ascending order
								</div>
								<div >
						<div class="text" style="width: 20%">
							<s:text name="Remarks" />
						</div>
						<div class="tbox" style="width: 80%">
								<s:textarea rows="3" name="bonusremarks" id="bonusremarks" cssClass="inputBoxA"  cssStyle="width: 100%;"/><br/>
								<span class="textAreaRemaining"><label id="bonus_left"></label> &nbsp; <s:text name="Characters Remaining" /></span>
							<br/>
						</div>
						
						</div>
					</div>
					</div>
					<div class="panel panel-primary">
					<div class="panel-body">
					
						<div class=textfieldA100>
							<div class="text" style="float: left;width: 25%;">
								<s:text name="label.firstslideprofitcom" />
							</div>
							<div style="float: left;width: 75%;">
							<s:select  list="slidingScalePeriodList"  name="fpcType" id="fpcType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="TYPE"  listValue="DETAIL_NAME"   cssStyle="width: 33.3%; float:left;" onchange="getscalePeriod(this.value);"/>
							<div id="scfiid" >
							<s:textfield name="scfistpc" id="scfistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 33.3%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);"  />
							</div>
							<div id="periodid" style="display:none">
							<s:textfield name="fpcfixedDate" id="periodDate"  cssClass="inputBox" cssStyle="width: 33.3%; float:left;"   />
							</div>
							<div id="scseid" >
							<s:select  list="profitCommissionListVar"  name="scprofitMont" id="scprofitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 33.3%; float:right;"/>
							</div>
							</div>
						</div>
						<br/>	
						<div class="textfield" >
							<div class="text">
								<s:text name="label.subseqcal" />
							</div>
							<div class="tbox">
								<s:radio list="#{'Y':'Yes','N':'No'}" name="scsubSeqCalculation" value="scsubSeqCalculation==null || scsubSeqCalculation==''?'N':scsubSeqCalculation"    onchange="getScSubseqCal(this.value);"></s:radio><br/>	
							</div>
						</div>	
						<div class="textfield" id="scsubseqcalid">
							<div class="text">
								<s:text name="label.sublossprofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="scsubpc" id="scsubpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);" maxlength="10"  />
									<s:select  list="profitCommissionListVar"  name="scsubProfitMonth" id="scsubProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>
						
						
						
						</div>
					</div>
						<div class="boxcontent" align="center">
								<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
								<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
						</div>
					</div>
                   
                    </s:else>
                    <s:hidden name="pageFor" id="pageFor"/>
                    <%-- </s:form> --%>
                </div>
            </div>
            </div>
       </div>
     </div>
   </div>
 </div>
  </div>
</div>	

</s:elseif>
<s:else>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">
               			<div class="boxcontent">
							<div class="panel panel-primary">
                				<div class="panel-heading">
									Low Bonus Details
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
								<div class="tablerow">
										<span style="color:red;"><s:actionerror/></span>
								</div>
											
                			<%-- <s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" > --%>
                				<s:set var="lowClaimBonusListVar" value="lowClaimBonusList"/>
                   					<s:if test="'view'.equals(mode)">
                   						<div class="table2">
                   							
                    						<div class="panel-body">
									                   <div class="textfield">
															<div class="text">
																<s:text name="Type" />
															</div>
															<div class="tbox">
																<%--<s:select  list="#{'LA':'Loss Amount','LR':'Loss Ratio'}"  name="bonusTypeId" id="bonusTypeId" cssClass="inputBoxS"   onchange="getDet(this.value)" disabled="true"/>--%>
																<s:select  list="#{'LR':'Loss Ratio'}"  name="bonusTypeId" id="bonusTypeId" cssClass="inputBoxS"   onchange="getDet(this.value)" disabled="true"/>
																
															</div>
														</div>
                    							<br class="clear" />
												<div>
													<div >
														<table class="table table-bordered" width="100%" id="bonusTbl">
															<thead>
																<tr>
																	<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																	<th width="16.5%" id="lossration1" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
																	<th width="16.5%" id="lossration2" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
																	<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Low Claim Bonus(%)" /> </th>
																</tr>
															</thead>
															<tbody>
															<s:iterator value="lowClaimBonusListVar" var="list" status="stat">
																<tr>
																	<td>
												 					<s:textfield name="bonusSNo[%{#stat.count-1}]" id="bonusSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
												 					</td>
																	<td>
																	<s:textfield name="bonusFrom[%{#stat.count-1}]" id="bonusFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
																	</td>
																	<td>
																	<s:textfield name="bonusTo[%{#stat.count-1}]" id="bonusTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true" theme="simple"/>
																	</td>
																	<td>
																	<s:textfield name="bonusLowClaimBonus[%{#stat.count-1}]" id="bonusLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true" theme="simple"/>
																	</td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
												</div>
											</div>
											<br class="clear"/>
											<div class="boxcontent" align="center">
												<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');getPrint()"/>
												<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
											</div>
										</div>
                    				</div>
                   				</s:if>
                   				<s:else>
                    			<div class="table2">
                    				<div class="panel-body">
                    					<div class="tablerow">
						                    <div class="textfield">
													<div class="text">
														<s:text name="Type" />
													</div>
													<div class="tbox">
														<s:select  list="#{'LR':'Loss Ratio'}"  name="bonusTypeId" id="bonusTypeId" cssClass="inputBoxS"   onchange="getDet(this.value)"/>
													</div>
											</div>
                    					</div>
                    					<br class="clear" />
										<div>
											<div >
												<table class="table table-bordered" width="100%" id="bonusTbl">
													<thead>
														<tr>
															<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
															<th width="16.5%" id="lossration1" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
															<th width="16.5%" id="lossration2" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
															<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Low Claim Bonus(%)" /> </th>
															<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
														</tr>
													</thead>
													<tbody>
													<s:iterator value="lowClaimBonusListVar" var="list" status="stat">
														<tr>
															<td>
										 					<s:textfield name="bonusSNo[%{#stat.count-1}]" id="bonusSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
										 					</td>
															<td >
															<s:textfield name="bonusFrom[%{#stat.count-1}]" id="bonusFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30"  theme="simple"/>
															</td>
															<td >
															<s:textfield name="bonusTo[%{#stat.count-1}]" id="bonusTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
															</td>
															<td >
															<s:textfield name="bonusLowClaimBonus[%{#stat.count-1}]" id="bonusLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
															</td>
															<td>
															<s:if test='0!=(#stat.count-1)'>
															<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="BonusremoveRow('<s:property value="%{#stat.count-1}"/>')" />
															</s:if>
															</td>
															</tr>
													</s:iterator>
													</tbody>
												</table>
											</div>
											<div class="boxcontent" align="right">
												<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="BonusinsRow('bonusTbl');" />
											</div>
										</div>
										<div class="boxcontent" align="left">
											<b>Note :</b> Please enter the slabs in ascending order
										</div>
										<div class="boxcontent" align="center">
											<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnBSubmit();return false;" class="btn btn-sm btn-success" />
											<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
										</div>
									</div>
                    			</div>
                    	</s:else>
		                  
		                  
                    <%-- </s:form> --%>
                </div>
            </div>
        </div>
     </div>
   </div>
 </div>
 </div>
</div>	
</s:else>
<script type="text/javascript">

<s:if test='fpcType!=null && !"".equals(fpcType)'>
getscalePeriod('<s:property value="fpcType"/>')
</s:if>
<s:if test='scsubSeqCalculation!=null && !"".equals(scsubSeqCalculation)'>
getScSubseqCal('<s:property value="scsubSeqCalculation"/>')
</s:if>
<s:if test='bonusTypeId!=null && !"".equals(bonusTypeId)'>
getLossRatio('<s:property value="bonusTypeId"/>')
</s:if>
<s:if test='scalementhod!=null && !"".equals(scalementhod)'>
getMethod('<s:property value="scalementhod"/>')
</s:if>
</script>

	</body>
</html>
