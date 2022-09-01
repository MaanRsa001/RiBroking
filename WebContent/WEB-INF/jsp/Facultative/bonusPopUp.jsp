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

                <s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" >
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
							<table class="footable" id="bonusTbl" width="100%" >									
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
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td>
											<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
											</td>
											<td>
											<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
											</td>
											<td>
											<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true"/>
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
											<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);"   readonly="true"/>
									<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
									</div>
								</div>	
								
								<div class="textfield" >
									<div class="text">
										<s:text name="label.subpsliderofitcom" />
									</div>
									<div class="tbox">
											<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);" maxlength="10"   readonly="true"/>
											<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"  disabled="true"/>
									</div>
								</div>
								<div class="textfield" >
									<div class="text">
										<s:text name="label.subseqcal" />
									</div>
									<div class="tbox">
										<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   disabled='true'></s:radio><br/>	
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
							<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
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
							<table class="footable" id="bonusTbl" width="100%" >										
								<thead>
									<tr>
										<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
										<th width="30%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
										<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
										<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Sliding Scale Commission %" /> </th>
										<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
									</tr>
								</thead>
									<tbody>
									<s:iterator value="scaleCommissionListVar" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
						 					</td>
											<td >
											<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
											</td>
											<td >
											<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
											</td >
											<td >
											<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>
											</td>
											<td>
											<s:if test='0!=(#stat.count-1)'>
											<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="scaleremoveRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											</td>
										</tr>
									</s:iterator>
									</tbody>
							</table>
					
					<div class="boxcontent" align="right">
						<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="scaleinsRow('bonusTbl');" />
					</div>
					
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
					<div class="panel panel-primary">
					<div class="panel-body">
					
						<div class="textfield">
							<div class="text">
								<s:text name="label.firstslideprofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);"  />
							<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>	
						<div class="textfield" >
							<div class="text">
								<s:text name="label.subpsliderofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);" maxlength="10"  />
									<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>
						<div class="textfield" >
							<div class="text">
								<s:text name="label.subseqcal" />
							</div>
							<div class="tbox">
								<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"  ></s:radio><br/>	
							</div>
						</div>	
						</div>
					</div>
					<div class="boxcontent" align="center">
						<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
						<input type="button" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
					</div>
					</div>
                    </s:else>
                    <s:hidden name="pageFor" id="pageFor"/>
                   	<s:hidden name="Proposal_no" id="Proposal_no"/>
                   	<s:hidden name="amendId" id="amendId"/>
                   	<s:hidden name="contractNo" id="contractNo"/>
                   	<s:hidden name="layerProposalNo" id="layerProposalNo"/>
                   	<s:hidden name="loopcount" id="loopcount"></s:hidden>
                   	<s:hidden name="productId" id="productId"></s:hidden>
                   	<s:hidden name="treatyType" id="treatyType"></s:hidden>
                   	
                    </s:form>
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
                <s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" >
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
									<s:text name="Type" />
								</div>
								<div class="tbox" style="width: 80%">
									<s:radio name="bonusTypeId" id="bonusTypeId" list="#{'LC':'Loss Corridor','LP':'Loss Participation '}"  disabled="true" />													
								</div>
							</div>
	                    </div>
                    </div>
					  <div class="panel panel-primary">
	                    <div class="panel-body">
								<table class="footable" width="100%" id="bonusTbl">
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
							 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
							 					</td>
												<td>
												<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
												</td>
												<td>
												<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
												</td>
												<td>
												<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true"/>
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
												<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);"  disabled="true"/>
										<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
										</div>
									</div>	
									
									<div class="textfield" >
										<div class="text">
											<s:text name="label.sublossprofitcom" />
										</div>
										<div class="tbox">
												<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="checkNumbers(this);" maxlength="10"   disabled="true"/>
												<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;" disabled="true"/>
										</div>
									</div>
									<div class="textfield" >
										<div class="text">
											<s:text name="label.subseqcal" />
										</div>
										<div class="tbox">
											<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   disabled='true'></s:radio><br/>	
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
								<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
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
									<s:text name="Type" />
								</div>
								<div class="tbox" style="width: 80%">
									<s:radio name="bonusTypeId" id="bonusTypeId" list="#{'LC':'Loss Corridor','LP':'Loss Participation'}" />
								</div>
							</div>
                    	</div>
                    	</div>
                    	<div class="panel panel-primary">
                    		<div class="panel-body">
                    	
								<div >
									<table class="footable" width="100%" id="bonusTbl">
										<thead>
											<tr>
												<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
												<th width="30%" style="text-align: center; vertical-align: middle;"> <s:text name="Claims Ratio From"/> </th>
												<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Claims Ratio To "/> </th>
												<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Participation of the Cedant %" /> </th>
												<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
											</tr>
										</thead>
											<tbody>
												<s:iterator value="scaleCommissionListVar" var="list" status="stat">
													<tr>
														<td>
									 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
									 					</td>
														<td >
														<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
														</td>
														<td >
														<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
														</td>
														<td >
														<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>
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
					
						<div class="textfield">
							<div class="text">
								<s:text name="label.firstlossprofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);"  />
							<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>	
						
						<div class="textfield" >
							<div class="text">
								<s:text name="label.sublossprofitcom" />
							</div>
							<div class="tbox">
									<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;width: 45%; float:left;" onkeyup="middleMinusRestriction(this);checkNumbers(this);" maxlength="10"  />
									<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   cssStyle="width: 45%; float:left;"/>
							</div>
						</div>
						<div class="textfield" >
							<div class="text">
								<s:text name="label.subseqcal" />
							</div>
							<div class="tbox">
								<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   ></s:radio><br/>	
							</div>
						</div>	
						
						
						</div>
					</div>
						<div class="boxcontent" align="center">
								<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmitScale();return false;" class="btn btn-sm btn-success" />
								<input type="button" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
						</div>
					</div>
                   
                    </s:else>
                    <s:hidden name="pageFor" id="pageFor"/>
                   <s:hidden name="Proposal_no" id="Proposal_no"/>
                   <s:hidden name="amendId" id="amendId"/>
                   <s:hidden name="contractNo" id="contractNo"/>
                   <s:hidden name="layerProposalNo" id="layerProposalNo"/>
                   <s:hidden name="loopcount" id="loopcount"></s:hidden>
                    </s:form>
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
											
                			<s:form id="bonuspopup" name="bonuspopup" theme="simple" action="" method="post" >
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
														<table class="footable" width="100%" id="bonusTbl">
															<thead>
																<tr>
																	<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																	<th  width="16.5%" id="lossamount1" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Amount From"/> </th>
																	<th width="16.5%" id="lossamount2" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Amount To "/> </th>
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
																	<s:textfield name="bonusFrom[%{#stat.count-1}]" id="bonusFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
																	</td>
																	<td>
																	<s:textfield name="bonusTo[%{#stat.count-1}]" id="bonusTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" readonly="true"/>
																	</td>
																	<td>
																	<s:textfield name="bonusLowClaimBonus[%{#stat.count-1}]" id="bonusLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="26" readonly="true"/>
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
												<input type="button" id="close" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
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
												<table class="footable" width="100%" id="bonusTbl">
													<thead>
														<tr>
															<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
															<th  width="16.5%" id="lossamount1" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Amount From"/> </th>
															<th width="16.5%" id="lossamount2" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Amount To "/> </th>
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
										 					<s:textfield name="bonusSNo[%{#stat.count-1}]" id="bonusSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
										 					</td>
															<td >
															<s:textfield name="bonusFrom[%{#stat.count-1}]" id="bonusFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
															</td>
															<td >
															<s:textfield name="bonusTo[%{#stat.count-1}]" id="bonusTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" />
															</td>
															<td >
															<s:textfield name="bonusLowClaimBonus[%{#stat.count-1}]" id="bonusLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26"/>
															</td>
															<td>
															<s:if test='0!=(#stat.count-1)'>
															<INPUT type="button" value="Delete" class="btn btn-sm btn-info"   onclick="removeRow('<s:property value="%{#stat.count-1}"/>')" />
															</s:if>
															</td>
															</tr>
													</s:iterator>
													</tbody>
												</table>
											</div>
											<div class="boxcontent" align="right">
												<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('bonusTbl');" />
											</div>
										</div>
										<div class="boxcontent" align="left">
											<b>Note :</b> Please enter the slabs in ascending order
										</div>
										<div class="boxcontent" align="center">
											<input type="button" value="Submit"  onclick="disableForm(this.form,false,'');fnSubmit();return false;" class="btn btn-sm btn-success" />
											<input type="button" value="Close"  onclick="window.close()" class="btn btn-sm btn-danger"/>
										</div>
									</div>
                    			</div>
                    	</s:else>
		                   <s:hidden name="proposalNo" id="proposalNo"/>
		                   <s:hidden name="Typename" id="Typename"/>
		                   <s:hidden name="endorsmentno" id="endorsmentno"/>
		                   <s:hidden name="contractNo" id="contractNo"/>
		                   <s:hidden name="acqBonus" id="acqBonus"/>
		                   <s:hidden name="loopcount" id="loopcount"></s:hidden>
		                    <s:hidden name="proposal_no" id="proposal_no"/>
		                    <s:hidden name="productId" id="productId"/>
		                    <s:hidden name="product_id" id="product_id"/>
		                     <s:hidden name="layerNo" id="layerNo"/>
		                     <s:hidden name="amendId" id="amendId"/>
		                     <s:hidden name="pageFor" id="pageFor"/>
		                     
                    </s:form>
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
function fnSubmit(){
//var productId = document.getElementById("productId").value;
//if(productId==''){
//productId = document.getElementById("product_id").value;
//}
//if(productId =='1'){
//	document.bonuspopup.action="${pageContext.request.contextPath}/insBonusDetailsFacultative.action";
//	}
//	else if(productId =='3'){
	document.bonuspopup.action="${pageContext.request.contextPath}/insBonusDetailsXol.action";
//	}
	
	document.bonuspopup.submit();
}
function removeRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			document.bonuspopup.action="${pageContext.request.contextPath}/removeRowFacultative.action?mode=delete&deleteId="+val;
			document.bonuspopup.submit();
			}
		}
}
function fnSubmitScale(){
	document.bonuspopup.action="${pageContext.request.contextPath}/insBonusDetailsRiskDetails.action";
	document.bonuspopup.submit();
}
function scaleremoveRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			document.bonuspopup.action="${pageContext.request.contextPath}/removeRowRiskDetails.action?mode=delete&deleteId="+val;
			document.bonuspopup.submit();
			}
		}
}
if(""=='<s:property value="bonusTypeId"/>'){
	getDet('LA');
}else{
	getDet('<s:property value="bonusTypeId"/>');
}
function getDet(val){
var pageFor =document.getElementById("pageFor").value;
if(""==pageFor){
	val =document.getElementById("bonusTypeId").value;
	document.getElementById("Typename").value=val;
	if(val=="LA"){
	    document.getElementById('lossamount1').style.display = 'table-cell';
    		document.getElementById('lossration1').style.display = 'none';
    		document.getElementById('lossamount2').style.display = 'table-cell';
    		document.getElementById('lossration2').style.display = 'none';
      	} 
      	else if(val=="LR"){
      	 	document.getElementById('lossamount1').style.display = 'none';
      	 	document.getElementById('lossration1').style.display = 'table-cell';
      	 	document.getElementById('lossamount2').style.display = 'none';
      	 	document.getElementById('lossration2').style.display = 'table-cell';
      	}
  }
}

function scaleinsRow(tableID)
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
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "scaleFrom["+(rowCount-1)+"]";
      		element2.id = "scaleFrom["+(rowCount-1)+"]";
			element2.className = "inputBox";
			element2.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "scaleTo["+(rowCount-1)+"]";
      		element3.id = "scaleTo["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("style", "text-align:right;");
			element3.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "scaleLowClaimBonus["+(rowCount-1)+"]";
      		element4.id = "scaleLowClaimBonus["+(rowCount-1)+"]";
			element4.className = "inputBox";
			element4.setAttribute("style", "text-align:right;");
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			//element5.setAttribute("onclick", "deleteRow('bonusTbl','"+(rowCount)+"')");
			element5.setAttribute("onclick", "scaleremoveRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-info"
			cell5.appendChild(element5);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}
function insRow(tableID)
{
//for(var j=0;j<5;j++){
	var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "bonusSNo["+(rowCount-1)+"]";
       		element1.id = "bonusSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			
			var cell2 = row.insertCell(1);
			//cell2.setAttribute('style','background-color:#C6E2FF;');
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "bonusFrom["+(rowCount-1)+"]";
      		element2.id = "bonusFrom["+(rowCount-1)+"]";
			element2.className = "inputBox";
			element2.setAttribute("style", "text-align:right;");
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell2.appendChild(element2);
			
			var cell3 = row.insertCell(2);
			//cell3.setAttribute('style','background-color:#C6E2FF;');
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "bonusTo["+(rowCount-1)+"]";
      		element3.id = "bonusTo["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("style", "text-align:right;");
			element3.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell3.appendChild(element3);
			
			var cell4 = row.insertCell(3);
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "bonusLowClaimBonus["+(rowCount-1)+"]";
      		element4.id = "bonusLowClaimBonus["+(rowCount-1)+"]";
			element4.className = "inputBox";
			element4.setAttribute("style", "text-align:right;");
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "button";
			element5.value="Delete";
			//element5.setAttribute("onclick", "deleteRow('bonusTbl','"+(rowCount)+"')");
			element5.setAttribute("onclick", "removeRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-info"
			cell5.appendChild(element5);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}

function deleteRow(tableID,i) {
			try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			table.deleteRow(i);
			rowCount--;
			for(var j=i;j<rowCount;j++){
			 document.getElementById("bonusSNo["+j+"]").value = j;
			  document.getElementById("scaleSNo["+j+"]").value = j;
			}
			}catch(e) {
				alert(e);
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
$(function() {
	$('#bonusremarks').keyup(function() {
        update_chars_left(500, $('#bonusremarks')[0], $('#bonus_left'));
    });
    update_chars_left(500, $('#bonusremarks')[0], $('#bonus_left'));
});
function update_chars_left(max_len, target_input, display_element) {
   var text_len = target_input.value.length;
   if (text_len >= max_len) {
       target_input.value = target_input.value.substring(0, max_len); // truncate
       display_element.html("0");
   } else {
       display_element.html(max_len - text_len);
   }
}

<s:if test="'scale'.equals(pageFor)">
getType();
</s:if>
function getType(){
type = '<s:property value="treatyType"/>';
if(type=='1'){
document.getElementById("quotaShare").display='inline';
document.getElementById("quotaShare").value='QS';
document.getElementById("quotaShare").disabled=true;
}
else if(type=='2'){
document.getElementById("quotaShare").display='inline';
document.getElementById("quotaShare").value='S';
document.getElementById("quotaShare").disabled=true;
}
else if(type=='3'){
document.getElementById("quotaShare").display='inline';
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

	</body>
</html>
