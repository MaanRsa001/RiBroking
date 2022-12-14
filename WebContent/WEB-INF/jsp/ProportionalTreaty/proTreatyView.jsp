<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<sj:head jqueryui="true" jquerytheme="start" />--%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript" type="text/javascript">
	function stopRKey(evt) { 
	  var evt = (evt) ? evt : ((event) ? event : null); 
	  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey; 
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">			
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">javascript:window.history.forward(1);</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
		
<style type="text/css">
.btn-group {
	width: 100%;
}

.btn-group .btn {
	width: 90%;
	padding: 3px 12px;
}
.subpannel .panel-body .panel-heading {
    color: #fff;
    background-color: #a8182b;
    border-color: #a8182b;
}
.reinsstyle{
display: flex;
gap:20px;
}
</style>
		<script type="text/javascript">
 $(function() {
    $( "#incepDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	$( "#expDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	$( "#accDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	$( "#periodDate" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd/mm/yy"
		//yearRange: "-100:+0"
	});
	
  });
  </script>
	</head>
	<body>
		<s:set var="elayerInfo" value='%{sectionInfo}'/>
		<s:set var="dislayer" value="%{'layer'.equals(layerMode)}"/>
		<s:set var="ebouquetExistingList" value='%{bouquetExistingList}'/>
		<div class="table0" style="width: 100%; margin: 0 auto;">
			<div class="tablerow">
			<s:set name="profitCommissionListVar" value="profitCommissionList"/>
					<s:set name="typeYearListVar" value="typeYearList"/>
				<div class="table1"
					style="width: 100%; margin: 0 auto; background-color: #E5E5E5;">
					<div class="tablerow">
						<div style="padding: 10px; background: #F8F8F8">
							<s:form name="proportional" id="proportional" theme="simple" action="" method="post" autocomplete="off">
								<div class="table2">
									<div class="tablerow">
										<span style="color: red;"><s:actionerror />
										</span>
										<STRONG> 
										<s:if test='!"".equals(contractGendration) && null!=contractGendration'>
										   <font color="blue"> <s:property value="status" /> </font>
										</s:if>
										</strong>
									</div>
									<div class="tablerow" align="center">
										<span class="pageHeading"> <s:text
												name="label.proportionalTreaty" /> </span>
									</div>
									<s:if test="contNo != '' && contNo != null && proposal_no != null && proposal_no != ''  && amend_Id_Mode != '' && amend_Id_Mode != null">
										<div class="tablerow">
											<div class="boxcontent">
												<div class="panel panel-primary">
													<div class="panel-body">
														<div class="boxcontent">
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementType" />
																</div>
																<div class="tbox txtB">
																	<s:select list="endosTypelist" name="endorsmenttype"
																		cssClass="inputBoxS" headerKey=""
																		headerValue="---Select---" listKey="TYPE"
																		listValue="DETAIL_NAME" />
																</div>
															</div>
															<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementNo" />
																</div>
																<div class="tbox txtB">
																	<s:property value="amendId" />
																</div>
															</div>
															<br class="clear" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
														&nbsp;&nbsp;&nbsp;
													</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield33">
															<div class="text">
																<s:text name="label.bouquetModeYN" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="bouquetModeYN" id="bouquetModeYN" value="%{bouquetModeYN==null?'N':bouquetModeYN}" onchange="getBouquest(this.value);" disabled="%{bouquetModeYN!=null}"></s:radio>													
															</div>
														</div>										
														<div class="textfield33">
															<div id="bouquetid">
																<div class="text">
																	<s:text name="label.bouquetMode" />
																</div>
																<div class="tbox">
																	<s:select list="bouquetList" listValue="Bouquet_NO" listKey="Bouquet_NO" name="bouquetNo" id="bouquetNo" cssClass="select1 inputBoxS" headerKey="" headerValue="---None---" disabled="%{bouquetModeYN!=null}"/>
																</div>
															</div>
														</div>
														<div class="textfield33" style="display:none">
															<div id="bouquetpds">
																<div class="boxcontent" align="center">
																<input type="button"  value="Proceed"   class="btn btn-sm btn-success"   onclick="procceed()" />
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div id="bouquestid" style="display:inline">
									<s:if test='"Y".equals(bouquetModeYN)'>
										<div class="boxcontent" >
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.bouquetinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
													
														
															<div>
																<table width="100%" class="table table-bordered" >
																	<thead>
																	<tr>
																		<th width="4%"> <s:text name="label.sno" /> </th>
																		<th width="15%"><s:text name="label.businessType" /></th>
																		<th width="10%"><s:text name="label.offerNo" /></th>
																		<th width="10%"><s:text name="label.baseproposal" /></th>
																		<th width="10%"><s:text name="label.proposalNo" /></th>
																		<th width="10%"><s:text name="label.sectionNoLayer" /></th>
																		<th width="15%"><s:text name="label.departmentClass" /></th>
																		<th width="15%"><s:text name="label.treatyType" /></th>
																		<th width="15%"><s:text name="label.treatyNameType" /></th>
																		<th width="10%"><s:text name="label.newRenew" /></th>
																		<%-- <th width="10%"><s:text name="label.existingshare" /></th> --%>
																	</tr>
																	</thead>
																	<tbody>	
																	<s:iterator value="#ebouquetExistingList" var="list"  status="stat">									
																	<tr>
																		<td>
																			<s:property value="%{#stat.count}"/>
																		</td>
																		<td>
																			<s:property value="#list.BUSINESS_TYPE"/>
																		</td>
																		<td>
																			<s:property value="#list.OFFER_NO"/>
																		</td>
																		<td>
																			<s:property value="#list.BASE_LAYER"/>
																		</td>
																		<td>
																			<s:property value="#list.PROPOSAL_NO"/>
																		</td>
																		<td>
																		<s:if test='2==(#list.PRODUCT_ID)'>
																			<s:property value="#list.SECTION_NO"/>
																		</s:if>
																		<s:else>
																		<s:property value="#list.LAYER_NO"/>
																		</s:else>
																		</td>
																		<td>
																			<s:property value="#list.TMAS_DEPARTMENT_NAME"/>
																		</td>
																		<td>
																			<s:property value="#list.TREATY_TYPE"/>
																		</td>
																		<td>
																			<s:property value="#list.RSK_TREATYID"/>
																		</td>
																		<td>
																			<s:property value="#list.POLICY_STATUS"/>
																		</td>
																		<%-- <td>
																			<s:property value="#list.EXISTING_SHARE"/>
																		</td> --%>
																	</tr>												
																	</s:iterator>
																	</tbody>
																</table>											
															</div> 
														
														
													</div>
												</div>
											</div>
											</div>
										</s:if>
									<div class="tablerow">
										<div class="boxcontent">
											<div class="panel panel-primary">
												<div class="panel-heading">
														<s:text name="label.riskinfo" />
													</div>
												<div class="panel-body">
													<div class="boxcontent">
													<s:if test="contNo != '' && contNo != null && proposal_no != null && proposal_no != ''  && amend_Id_Mode != '' && amend_Id_Mode != null">
														<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementNo" />
																</div>
																<div class="tbox txtB">
																	<s:property value="amendId" />
																</div>
														</div>
														<div class="textfield">
																<div class="text">
																	<s:text name="label.endorsementType" />
																</div>
																<div class="tbox txtB">
																	<s:select list="endosTypelist" name="endorsmenttype" headerValue="---Select---" listKey="TYPE"  istValue="DETAIL_NAME" />
																</div>
														</div>
														<s:hidden name="endorsementStatus" id="endorsementStatus"/>											
													<div class="textfield" >
															<div class="text">
																<s:if test='"Endorsement".equals(endorsmenttype)'>
																		<s:text name="label.endorsementDate" />
																	</s:if>
																	<s:if test='"GNPI".equals(endorsmenttype)'>
																		<s:text name="label.gnpiDate" />
																	</s:if>
																	<s:if test='"Rectification".equals(endorsmenttype)'>
																		<s:text name="label.rectificationDate" />
																	</s:if>
															</div>
															<div class="tbox">
																<s:textfield name="endorsementDate" id="endorsementDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="functionDate()" disabled="%{prclFlag==true?true:false}"  />
															</div>
														</div>
														</s:if>
														<div class="textfield">
															<div class="text" >
																<s:text name="label.proposalNo" />
															</div>
															<div class="tbox">
																<s:textfield name="proposal_no" id="proposal_no" cssClass="inputBox" disabled="true" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.offerNo" />
															</div>
															<div class="tbox">
																<s:textfield name="offerNo" cssClass="inputBox" disabled="true" />
															</div>
														</div>											
														<%-- <div class="textfield">
															<div class="text">
																<s:text name="label.contractno" />
															</div>
															<div class="tbox">
																<s:textfield name="contNo" cssClass="inputBox" disabled="true" />
															</div>
														</div> --%>
														
														
														<br class="clear"></br>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.cedentinfo" />
												</div>
												<div class="panel-body">
													<div class="textfield">
														<div class="text">
															<s:text name="label.cedingCompany" />&nbsp;<sup style="color: red;"></sup>
														</div>
														<div class="tbox">
																<s:if test="RenewalMode != null">
																	<s:select list="CeddingCompanylist"	 listKey="CUSTOMER_ID" listValue="NAME" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
																</s:if>
																<s:else>
																	<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onchange="getRetention();"/>
																</s:else>
														</div>
													</div>
													<div class="textfield">
															<div class="text">
																<s:text name="label.inceptionDate" /> &nbsp; <sup style="color: red;">  </sup>
															</div>
															<div class="tbox">
																<div class="">
																	<s:textfield name="incepDate" id="incepDate" cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate();" disabled="true" />
																</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.expiryDate" /> &nbsp; <sup style="color: red;">  </sup>
															</div>
															<div class="tbox">
																<s:if test="layerProposalNo == null || layerProposalNo == ''">
																	<div class="">
																		<s:textfield name="expDate" id="expDate" cssClass="inputBox" onkeyup="validateSpecialChars(this)" disabled="true" onchange="functionEDate();"/>
																	</div>
																</s:if>
																<s:else>
																	<div class="">
																		<s:textfield name="expDate" id="expDate" cssClass="inputBox" onkeyup="validateSpecialChars(this)" disabled="true" onchange="functionEDate();"/>
																	</div>
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.uwYearFrom" /> &nbsp; <sup style="color: red;">  </sup>
															</div>
															<div class="tbox" id="yearId">
																<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" id="uwYear" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" onblur="getRetention();"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.uwYearto" /> &nbsp; <sup style="color:red;"></sup>
															</div>
															<div class="tbox" id="yearIdto">
																<s:select  list="yearToList" listKey="YEAR" listValue="YEAR" name="uwYearTo" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
															</div>
														</div>
												</div>
											</div>
										</div>
										<s:iterator value="#elayerInfo" var="list"  status="stat">
											<div id="sectionid<s:property  value='%{#stat.count}'/>" ></div>
										</s:iterator>
							<div class="tablerow">
								<div class="boxcontent" align="center">
									<s:if test='!"PR".equals(Flag)'>
										<input type="button" id="back" value="Back" class="btn btn-sm btn-danger" onclick="destroyPopUps();goBack('<s:property value="#session.mfrid"/>','<s:property value="#session.DepartmentId"/>','<s:property value="flag"/>')" /> &nbsp;&nbsp;&nbsp;
										<input type="button" id="print" value="Print" class="btn btn-sm btn-info" onclick="getPrint()"/>
									</s:if>
									<s:else>
										<input type="button"  value="Close" class="btn btn-sm btn-danger" onclick="destroyPopUps();window.close()"/>
									</s:else>
								</div>
							</div>
									
							</div>
						</div>
						</div>
								<s:hidden name="layerMode" id="layerMode"/>
								<s:hidden name="proposalNo1" id="proposalNo1"/>
								<s:hidden name="flag" id="flag" />
								<s:hidden name="menuId" id="menuId" />
								<s:hidden name="CustomerId" value="cedingCo" />
								<s:hidden name="layerProposalNo" />
								<%--<s:hidden name="brokerage" />
								<s:hidden name="tax" />--%>
								<s:hidden name="contractno1" />
								<s:hidden name="renewal_contract_no" />
								<s:hidden name="renewalFlag" />
								<s:hidden name="lay1" />
								<s:hidden name="baseLayer" id="baseLayer"/>
								<s:hidden name="baseLoginID" />
								<s:hidden name="amendId" />
								<%--<s:hidden name="contractNo" value="%{contNo}" />
									<s:hidden name="contNo"/>--%>
								<s:hidden name="edit" />
								<s:hidden name="cedType" id="cedType" />
								<s:hidden name="commissionQ_SAmt" />
								<s:hidden name="commission_surpAmt" />
								<s:hidden name="endorsmentno" />
								<s:hidden name="proposalNo" id="proposalNo"
									value="%{proposal_no}" />
								<s:hidden name="mode" />
								<s:hidden name="countryExcludedList" id="countryExcludedList" />
								<s:hidden name="countryIncludedList" id="countryIncludedList" />
								<s:hidden name="renewalProposalNo" id="renewalProposalNo" />
								<s:hidden name="proposalReference" id="proposalReference" />
								<s:hidden name="preVal" id="preVal"/>
								<s:hidden name="prePerilVal" id="prePerilVal"/>
								<s:hidden name="reMode" id="reMode"/>
								<s:hidden name="renewalEditMode" id="renewalEditMode"/>
								<s:hidden name="slidePopUp" id="slidePopUp"/>
								<s:hidden name="lossPopUp" id="lossPopUp"/>
								<s:hidden name="profitPopUp" id="profitPopUp"/>
								 <s:hidden name="editMode" id="editMode"></s:hidden>
								 <s:hidden name="referenceNo" id="referenceNo"></s:hidden>
								 <s:hidden name="sectionMode" id="sectionMode"></s:hidden>
								 <s:hidden name="sectionNo" id="sectionNo"/>
								 <s:hidden name="proposal_No" id="proposal_No"/>
								 
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }

//toggleVesselBlock('<s:property value="departId"/>');
function toggleVesselBlock(value){
	if( value=="2" || value=="10")
	 {
	 	document.proportional.limitPerVesselOC.value=document.proportional.limitPerVesselOC.value;
	 	document.proportional.limitPerLocationOC.value=document.proportional.limitPerVesselOC.value;  
		document.getElementById('VesselBlock').style.display='block';
	 }   
	 else{ 
	 	document.proportional.limitPerVesselOC.value='';
	 	document.proportional.limitPerLocationOC.value='';  
	 	document.getElementById('VesselBlock').style.display='none';
	 }
	 
	 
}
 function getAjax(value,id) {
        $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=subclass',
        //data:'src_type_id='+val,
        success: function(data){
            $('#' + id).html(data);
            $('#subProfit_center').multiselect({ 
      			includeSelectAllOption: false,
        		enableFiltering:true,
        		numberDisplayed: 0,
        		enableCaseInsensitiveFiltering: true,
        		onChange: function(element, checked) {
          		  var val = $('#subProfit_center').val();
		          var val1 =document.getElementById("preVal").value;
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
		          $("#subProfit_center").multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#subProfit_center").val(val);
		           $("#subProfit_center").multiselect("refresh");
		           document.getElementById("preVal").value = '';
		          }
		          else if (val !=null && val[0]=='ALL' ) {
		          	$("#subProfit_center").multiselect('clearSelection');
		          	$("#subProfit_center").val('ALL');
		          	 $("#subProfit_center").multiselect("refresh");
		          	 document.getElementById("preVal").value = 'ALL';
		          }
		          else if(val== null || val[0]==''){
		          $("#subProfit_center").multiselect('clearSelection');
		          $("#subProfit_center").multiselect("refresh");
		          document.getElementById("preVal").value = '';
          }
          }                   
    });
        }
        });
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
<%--function getAjax(value,id)
{
	var URL='${pageContext.request.contextPath}/ajaxValueRiskDetails.action?departId='+value+'&dropDown=subclass';
	postRequest(URL,'subclass');
}--%>
function next(){
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		document.proportional.action="checkRiskDetails.action";
		document.proportional.submit();
}
function funsubmit(){
	dffDateAlert();
	if(chkAccAmt()){
	<s:if test="RenewalMode != null">	
		 var t1=document.getElementById("RenewalDate").value;
		 var t2=document.getElementById("incepDate").value;
		 var one_day = 1000 * 60 * 60 * 24;
		 var x=t1.split("-");     
		 var y=t2.split("-");
		 var date1=new Date(x[2],(x[1]-1),x[0]);  
		 var date2=new Date(y[2],(y[1]-1),y[0]);
		 var month1=x[1]-1;
		 var month2=y[1]-1;  
		 var Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
		 if(Diff<=0){
			 alert("Inception Date Invalid ..You are In Renewal Mode")
		 }
		 else{
		 	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		 	document.proportional.action="${pageContext.request.contextPath}/checkRiskDetails.action";
		 	document.proportional.submit();
		 }
	</s:if>
	<s:else>
			replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');		
			document.proportional.action="${pageContext.request.contextPath}/checkRiskDetails.action";
			document.proportional.submit();
	</s:else>
	}
}


function FunctionEdit(){
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.proportional.action="${pageContext.request.contextPath}/EditModeRiskDetails?multiuserMode=edit";
	document.proportional.submit();
}

function funEditSubmit(){

	document.proportional.editMode.value="S";
	
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.proportional.action="FirstPageSaveMethodRiskDetails.action";
	document.proportional.submit();
	
}

function FunctionSaveOption(){

		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
		document.proportional.action="FirstPageSaveMethodRiskDetails.action";
		document.proportional.submit();
}
function FunctionAddLayer()
{
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.getElementById("proposal_no").value="";
	document.getElementById("sectionNo").value="";
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.proportional.action="FirstPageSaveMethodRiskDetails.action";
	document.proportional.submit();
}
function FunctionUpdateOption()
{
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.getElementById("proposal_no").value=document.proportional.layerProposalNo.value;
	replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml,premiumQuotaShare,premiumSurplus,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.proportional.action="FirstPageSaveMethodRiskDetails.action";
	document.proportional.submit();
}
function FunctionView(){
	document.proportional.action="${pageContext.request.contextPath}/ViewMethodRiskDetails.action"
	document.proportional.submit();
}
function funAmendsubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus").value;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		else if("A" == elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
		document.getElementById("countryExcludedList").value=checkedItems;
		document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.proportional,'maxLimit_Product,limitPerVesselOC,limitPerLocationOC,limitOrigCur,treatyLimitsurplusOC,epi_origCur,epi,xlCost,limitOrigCurPml,treatyLimitsurplusOCPml,epipml');
		document.proportional.action="FirstPageUpdateModeRiskDetails.action";
		document.proportional.submit();
}
function FunctionAmendSaveOption(){
	document.proportional.action="${pageContext.request.contextPath}/AmednIDFirstPageSaveMathodRiskDetails.action";
	document.proportional.submit();
}

function FunctionEditCancel(){
	document.getElementById("bouquetNo").disabled=false;
	document.getElementById("flag").value='P';
	document.proportional.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}
function FunctionRejectCancel(){
	document.proportional.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}
function FunctionCancel(){
	var val = document.getElementById("proposalReference").value;
	document.getElementById("proposal_no").disabled=false;
	var no =document.getElementById("proposal_no").value;
	var old = document.getElementById("renewalProposalNo").value;
	if('Renewal'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("proposalNo").value = old;
		document.getElementById("renewalProposalNo").value=no;
			document.proportional.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.proportional.submit();
			}
			else {
			document.getElementById("proposal_no").disabled=true;
				 return false; 
			}
		}
		else{
			document.proportional.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.proportional.submit();
		}
	
}

function FunctionNotTakenCancel(){
	
	document.proportional.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.proportional.submit();
}

function AmendIdBack(){
	document.getElementById("mybutton").style.display = "none";
	var no = document.getElementById("proposal_no").value;
	
	//document.proportional.action = "${pageContext.request.contextPath}/InitPortfolio.do?proposalNo="+no+"&endtMode=endorsment&endorsementStatus=N";
	document.proportional.action = "${pageContext.request.contextPath}/InitPortfolio.do?endtMode=endorsment&endorsementStatus=N";
	document.proportional.submit();
}
function calculateSurplus(){
	var epi=document.proportional.epi.value;
	epi=epi.replace(new RegExp(',', 'g'),'');
	var quotashare=document.proportional.premiumQuotaShare.value;
	quotashare=quotashare.replace(new RegExp(',', 'g'),'');
	if(quotashare>epi){
		alert("Value should not exceed EPI 100% OC");
		document.proportional.premiumQuotaShare.value='';
		return false;
	}
	if(epi!=null && epi!='' && quotashare!=null && quotashare!=''){
		var finalvalue=(parseFloat(epi)-parseFloat(quotashare));
		document.proportional.premiumSurplus.value=Comma(finalvalue.toFixed(2));
	}
}
function calculation(type){
	var limit=document.proportional.limitOrigCur.value;
	limit=limit.replace(new RegExp(',', 'g'),'');
	var Epi100=document.proportional.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');
	var ourEsitamate=document.proportional.ourEstimate.value;
	if('our'==type){
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" ){
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.proportional.epi.value=Comma(finalvalue.toFixed(2));
	}else
		document.proportional.epi.value='0';
		}
	else if('epi1'==type){
			var Epi=document.proportional.epi.value;
			Epi=Epi.replace(new RegExp(',', 'g'),'');
		if(Epi100!=null && Epi100!="" && Epi!=null && Epi!="" ){
			var finalvalue=(parseFloat(Epi)*100)/parseFloat(Epi100);
			document.proportional.ourEstimate.value=Comma(finalvalue.toFixed(4));
		}
		}
}

function GetShareSigned(value){
		if(value=='A'){
		document.proportional.sharSign.disabled=false;
		}
		else  {
		document.proportional.sharSign.value='';
		document.proportional.sharSign.disabled=true;
		}
}
//getTreatyType('<s:property value="treatyType"/>');
function getTreatyType(val){
if(val=="1"){
		document.getElementById('treatyQS1').style.display="inline";
		document.getElementById('treatySurp1').style.display="none";
		document.getElementById('treatySurp2').style.display="none";
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
		//document.getElementById('treatySurp3').style.display="none";
		document.getElementById('limitOrigCur').value=document.proportional.limitOrigCur.value;
		//document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
		document.getElementById('treatynoofLine').value="";
		document.getElementById('treatyLimitsurplusOC').value="";
		//document.getElementById('treatyLimitsurplusOCPml').value="";
		document.getElementById('treatyQSper').style.display="inline";
		document.getElementById('premiumQuoteid').style.display="none";
		document.getElementById('premiumSurplusid').style.display="none";
		document.getElementById('commissionQSPid').style.display="inline";
		document.getElementById('commissionSurpPid').style.display="none";
		
		
	}
else if(val=="2"){
		document.getElementById('treatyQS1').style.display="inline";
		document.getElementById('treatySurp1').style.display="inline";
		//document.getElementById('treatyQS2').style.display="none";
		document.getElementById('treatySurp2').style.display="inline";
		//document.getElementById('treatySurp3').style.display="inline";
		document.getElementById('limitOrigCur').value=document.proportional.limitOrigCur.value;
		//document.getElementById('limitOrigCurPml').value="";
		document.getElementById('treatynoofLine').value=document.proportional.treatynoofLine.value;
		document.getElementById('treatyLimitsurplusOC').value=document.proportional.treatyLimitsurplusOC.value;
		//document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
		document.getElementById('treatyQSper').style.display="none";
		document.getElementById('premiumQuoteid').style.display="none";
		document.getElementById('premiumSurplusid').style.display="none";
		document.getElementById('commissionQSPid').style.display="none";
		document.getElementById('commissionSurpPid').style.display="inline";
	}
	else if(val=="3"){
		document.getElementById('treatyQS1').style.display="inline";
		document.getElementById('treatySurp1').style.display="inline";
		//document.getElementById('treatyQS2').style.display="inline";
		document.getElementById('treatySurp2').style.display="inline";
		//document.getElementById('treatySurp3').style.display="inline";
		document.getElementById('limitOrigCur').value=document.proportional.limitOrigCur.value;
		//document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
		document.getElementById('treatynoofLine').value=document.proportional.treatynoofLine.value;
		document.getElementById('treatyLimitsurplusOC').value=document.proportional.treatyLimitsurplusOC.value;
		//document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
		document.getElementById('factreatyQS1').style.display="none";
		document.getElementById('faclimitOrigCur').value="";
		document.getElementById('treatyQSper').style.display="inline";
		document.getElementById('premiumQuoteid').style.display="inline";
		document.getElementById('premiumSurplusid').style.display="inline";
		document.getElementById('commissionQSPid').style.display="inline";
		document.getElementById('commissionSurpPid').style.display="inline";
	}
	else if(val=="4" || val=="5" ){
		document.getElementById('treatyQS1').style.display="none";
		document.getElementById('treatySurp1').style.display="none";
		document.getElementById('treatySurp2').style.display="none";
		document.getElementById('limitOrigCur').value='';
		document.getElementById('treatynoofLine').value='';
		document.getElementById('treatyLimitsurplusOC').value='';
		document.getElementById('factreatyQS1').style.display="inline";
		document.getElementById('treatyQSper').style.display="none";
		document.getElementById('premiumQuoteid').style.display="none";
		document.getElementById('premiumSurplusid').style.display="none";
		document.getElementById('commissionQSPid').style.display="inline";
		document.getElementById('commissionSurpPid').style.display="none";
	}
}
function GetExchangeRate() {
		var incDate=document.forms['proportional'].incepDate.value;
		var accDate=document.forms['proportional'].accDate.value;
		var Currecny=document.proportional.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !="")){
	 		var URL='${pageContext.request.contextPath}/getExcRateRiskDetails.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else{
			document.proportional.exchRate.value='';
		}	
}

/*function CalculateEpi() {	
	var a=document.proportional.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.proportional.adjRate.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.proportional.epi.value=Comma(c.toFixed(2));
	}else {
		document.proportional.epi.value='';
	}
}*/

function functionview(id){
	var cedding="";
	if(id==1) {
		cedding=document.getElementById("CeddingId").value;
	}
	else {
		cedding=document.getElementById("BrokerId").value;
	}
	var URL ='<%=request.getContextPath()%>/ViewModeCeding?Mode=PopUp&ceddingcompany='+cedding;
	postRequest(URL, 'companyAjaxId');	 
}
		
function Commas(value) {
	if(value=="2") {
		document.proportional.epi_origCur.value=Comma(document.proportional.epi_origCur.value);
		document.proportional.maxLimit_Product.value=Comma(document.proportional.maxLimit_Product.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.treatyLimitsurplusOC.value=Comma(document.proportional.treatyLimitsurplusOC.value);
		//document.proportional.xlCost.value=Comma(document.proportional.xlCost.value);
		document.proportional.cedReten.value=Comma(document.proportional.cedReten.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		//document.proportional.limitOrigCurPml.value=Comma(document.proportional.limitOrigCurPml.value);
		//document.proportional.treatyLimitsurplusOCPml.value=Comma(document.proportional.treatyLimitsurplusOCPml.value);
		//document.proportional.epipml.value=Comma(document.proportional.epipml.value);
		if(document.proportional.limitPerVesselOC.value!=null && document.proportional.limitPerVesselOC.value!="") {
			document.proportional.limitPerVesselOC.value=Comma(document.proportional.limitPerVesselOC.value);
		}
		if(document.proportional.limitPerLocationOC.value!=null && document.proportional.limitPerLocationOC.value!="") {
			document.proportional.limitPerLocationOC.value=Comma(document.proportional.limitPerLocationOC.value);
		}
	} else if(value=="3") {
		document.proportional.deduc_hunPercent.value=Comma(document.proportional.deduc_hunPercent.value);
		document.proportional.maxLimit_Product.value=Comma(document.proportional.maxLimit_Product.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.subPremium.value=Comma(document.proportional.subPremium.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		document.proportional.m_dPremium.value=Comma(document.proportional.m_dPremium.value);
		if(document.proportional.limitPerVesselOC.value!=null && document.proportional.limitPerVesselOC.value!="") {
			document.proportional.limitPerVesselOC.value=Comma(document.proportional.limitPerVesselOC.value);
		}
		if(document.proportional.limitPerLocationOC.value!=null && document.proportional.limitPerLocationOC.value!="") {
			document.proportional.limitPerLocationOC.value=Comma(document.proportional.limitPerLocationOC.value);
		}
	} else if(value=="4") {
		document.proportional.epi_origCur.value=Comma(document.proportional.epi_origCur.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		//document.proportional.xlCost.value=Comma(document.proportional.xlCost.value);
	} else if(value=="5") {
		document.proportional.deduc_hunPercent.value=Comma(document.proportional.deduc_hunPercent.value);
		document.proportional.limitOrigCur.value=Comma(document.proportional.limitOrigCur.value);
		document.proportional.subPremium.value=Comma(document.proportional.subPremium.value);
		document.proportional.epi.value=Comma(document.proportional.epi.value);
		document.proportional.m_dPremium.value=Comma(document.proportional.m_dPremium.value);
	}
	document.proportional.premiumQuotaShare.value=Comma(document.proportional.premiumQuotaShare.value);
	document.proportional.premiumSurplus.value=Comma(document.proportional.premiumSurplus.value);
	
}



function functionDate(){
var	inceptionDate=document.proportional.incepDate.value;
	if(inceptionDate!=""){
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
     if(inceptionDate.match(dateformat)){
		var splitVal = inceptionDate.split('/');
		var status="";
		var dd = parseInt(splitVal[0]);
              var mm  = parseInt(splitVal[1]);
              var yy = parseInt(splitVal[2]);
              var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
              if (mm==1 || mm>2)
              {
                  if (dd>ListofDays[mm-1])
                  {
                    status='fail';
                  }
              }
              if (mm==2)
              {
                  var lyear = false;
                  if ( (!(yy % 4) && yy % 100) || !(yy % 400))
                  {
                      lyear = true;
                  }
                  if ((lyear==false) && (dd>=29))
                  {
                    status='fail'; 
                  }
                  if ((lyear==true) && (dd>29))
                  {
                    status='fail';  
                  }
              }
          }
          else
          {
             status='fail';  
          }
		if(status!='fail'){
		if(splitVal[0] .length<2){
		 splitVal[0]="0"+splitVal[0];
		 }if(splitVal[1].length<2){
		 splitVal[1]="0"+splitVal[1];
		 }
		inceptionDate = splitVal[0] + "/" + splitVal[1] + "/" + splitVal[2];
		document.proportional.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.proportional.uwYear.value=date.getFullYear();
		//var date1=new Date((date.getFullYear()+1)+"/"+date.getMonth()+"/"+(date.getDate()));
		
		date.setFullYear(date.getFullYear()+1);
		date.setDate(date.getDate()-1);
		var d;
		var m
		if(parseInt(date.getDate())<10) {
			d="0"+date.getDate();
		}else {
			d=date.getDate();
		}
		if(parseInt(date.getMonth()+1)==0) {
			m="12"
		}else if((parseInt(date.getMonth())+1)<10) {
			m="0"+(parseInt(date.getMonth())+1);
		}else {
			m=(parseInt(date.getMonth())+1);
		}
		var y=date.getFullYear();
		document.proportional.expDate.value=d+"/"+m+"/"+y;
		var expirydate=d+"/"+m+"/"+y;
	var URL='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&dropDown=yearId';
	postRequest(URL,'yearId');
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
	}
	}
}


function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
  // ex input "2010-01-18" 
	return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}

function dffDateAlert() {
	var inception=document.proportional.incepDate.value;
	var date=new Date(reformatDate(inception));
	date.setFullYear(date.getFullYear()+1);
	date.setDate(date.getDate());
	var d;
	var m
	if(parseInt(date.getDate())<10) {
		d="0"+date.getDate();
	}else {
		d=date.getDate();
	}
	if(parseInt(date.getMonth()+1)==0) {
		m="12"
	} else if((parseInt(date.getMonth())+1)<10) {
		m="0"+(parseInt(date.getMonth())+1);
	}else {
		m=(parseInt(date.getMonth())+1);
	}
	var y=date.getFullYear();					
	var expery=(document.proportional.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
	if(diffDays>0){
		alert('Expiry Date More than One Year');
	}
}

function chkAccAmt(){
	var limitOC=document.proportional.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.proportional.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.proportional.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.proportional.proStatus.value=="A"){	 
		cedPer=document.proportional.sharSign.value;
	}else{
		cedPer=document.proportional.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.proportional.sharSign.value;
	</s:if>	
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit){
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt){
				<s:if test='contNo == "" && contNo == null'>				
					document.proportional.proStatus.value='P';
					document.proportional.sharSign.value='';
				</s:if>
			}else{
				 return false; 
			}
		}
	}
	return true;
}

function setCedRetType(type){
	document.getElementById("cedType").value=type;
}

function toggleXLCostOC(retroType){
	if(retroType=="SR"){
		//document.proportional.xlCost.value="0";
		document.proportional.xlCost.readOnly=true;
		document.proportional.insuredName.readOnly=false; 
	}else{
		//document.proportional.xlCost.value="";
		//document.proportional.xlCost.readOnly=false;
		document.proportional.insuredName.value="";
		document.proportional.insuredName.readOnly=true; 
	}
}

<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
	//enableForm1(document.proportional,true,'<s:property value="%{fields}"/>');
	//document.getElementById("retDelete").disabled = true;
	//document.getElementById("retAdd").disabled = true;	
</s:if>

if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.proportional.underwriter.value);
}
function getUWLimit(value){
  // var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitRiskDetails.action?underwriter='+value+'&dropDown=uwc2';
	postRequest(URL,'uwc2');
}
//GetPML();
function GetPML(){
pml = document.proportional.pml.value;
var treatyType = document.proportional.treatyType.value;
	if(pml=="Y"){
	
     		document.getElementById('pmls').style.display = 'block';
     		//document.getElementById('epis').style.display = 'block';
     		document.getElementById('pmlPercent').value=document.proportional.pmlPercent.value;
     		//document.getElementById('epipml').value=document.proportional.epipml.value;
     		<%--if(treatyType =="1"){
     		document.getElementById('treatyQS2').style.display = 'block';
     		document.getElementById('treatySurp3').style.display = 'none';
     		document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
     		document.getElementById('treatyLimitsurplusOCPml').value="";
     		
     		}
     		else if(treatyType =="2"){
     		document.getElementById('treatySurp3').style.display = 'block';
     		document.getElementById('treatyQS2').style.display = 'none';
     		document.getElementById('limitOrigCurPml').value="";
     		document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
     		}
     		else if(treatyType =="3"){
     		document.getElementById('treatySurp3').style.display = 'block';
     		document.getElementById('treatyQS2').style.display = 'block';
     		document.getElementById('limitOrigCurPml').value=document.proportional.limitOrigCurPml.value;
     		document.getElementById('treatyLimitsurplusOCPml').value=document.proportional.treatyLimitsurplusOCPml.value;
     		
     		}--%>
	       	} 
	 else if(pml=="N"){
       	 	document.getElementById('pmls').style.display = 'none';
       	 	//document.getElementById('epis').style.display = 'none';
       	 	//document.getElementById('treatyQS2').style.display = 'none';
     		//document.getElementById('treatySurp3').style.display = 'none';
     		//document.getElementById('limitOrigCurPml').value="";
     		//document.getElementById('treatyLimitsurplusOCPml').value="";
     		document.getElementById('pmlPercent').value="";
     		//document.getElementById('epipml').value="";
	       	}
}

function calculateTreatySur() {	
	var a =document.proportional.limitOrigCur.value; 
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.proportional.treatynoofLine.value;
	if(a!=null && a!="" && b!=null && b!="") {
		
		var c=parseFloat(a)*parseFloat(b);
		document.proportional.treatyLimitsurplusOC.value=Comma(c.toFixed(2));
	}else {
		document.proportional.treatyLimitsurplusOC.value='';
	}
}
function CalculateEGNPIPML() {	
	<%--var a =document.proportional.epi.value; 
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.proportional.pmlPercent.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var pmlPer=parseFloat(b)/100;
		
		var c=parseFloat(a)*pmlPer;
		document.proportional.epipml.value=Comma(c.toFixed(2));
	}else {
		document.proportional.epipml.value='';
	}--%>
}

function CalculateTreatyQSPML(){
<%--	var type = document.proportional.treatyType.value; 
	var b=document.proportional.pmlPercent.value;
	var pml = document.proportional.pml.value;
	var a = 0;
	var c=0;
	if('Y'==pml){
	if(type=='1'||'3'==type){
     a =document.proportional.limitOrigCur.value; 
     a=a.replace(new RegExp(',', 'g'),'');
   
		if(a!=null && a!="" && b!=null && b!="") {
			var pmlPer=parseFloat(b)/100;
			
			var d=parseFloat(a)*pmlPer;
			document.proportional.limitOrigCurPml.value=Comma(d.toFixed(2));
		}else {
			document.proportional.limitOrigCurPml.value='0';
		}
	}
	if(type=='2'||'3'==type){
	 c =document.proportional.treatyLimitsurplusOC.value; 
     c=c.replace(new RegExp(',', 'g'),'');
   
		if(c!=null && c!="" && b!=null && b!="") {
			var pmlPer=parseFloat(b)/100;
			
			var e=parseFloat(c)*pmlPer;
			document.proportional.treatyLimitsurplusOCPml.value=Comma(e.toFixed(2));
		}else {
			document.proportional.treatyLimitsurplusOCPml.value='0';
		}
	}
	}--%>
}
<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
$(document).ready(function() {     
    $('#perilCovered').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
          var val = $('#perilCovered').val();
          var val1 =document.getElementById("prePerilVal").value;
          if(val1!= null && val1=='0' && val !=null && val[1]!=undefined ){
          $("#perilCovered").multiselect('clearSelection');
          val = removeElementsWithValue(val, '0');
          $("#perilCovered").val(val);
           $("#perilCovered").multiselect("refresh");
           document.getElementById("prePerilVal").value = '';
          }
          else if (val !=null && val[0]=='0' ) {
          	$("#perilCovered").multiselect('clearSelection');
          	$("#perilCovered").val('0');
          	 $("#perilCovered").multiselect("refresh");
          	 document.getElementById("prePerilVal").value = '0';
          }
          else if(val== null || val[0]==''){
          $("#perilCovered").multiselect('clearSelection');
          $("#perilCovered").multiselect("refresh");
          document.getElementById("prePerilVal").value = '';
          }
      	}                     
    }); 
    
     <s:if test='perilCovered!=null && !"".equals(perilCovered)'>	
 		var uwgrade='<s:property value="perilCovered"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#perilCovered").val(dataArray);
		 $("#perilCovered").multiselect("refresh");
	</s:if>    
           
});
</s:if>

  function hundredCheck(id,val){
	if(parseFloat(val)>100){
	alert("This field value is not exceed 100");
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
//getRateField('<s:property value="proposalType"/>');
function getRateField(val){
if( val=='R' || val=='H'){
document.getElementById('ratId').style.display='inline';

}else{

document.getElementById('runoffYear').value='0';
document.getElementById('ratId').style.display='none';
}
}
//getLocDeta('<s:property value="LOCIssued"/>');
function getLocDeta(id){
	if(id == 'Y'){
		document.getElementById('bnkId').style.display='inline';
		document.getElementById('crdPId').style.display='inline';
		document.getElementById('crdAId').style.display='inline';
		document.getElementById('benfId').style.display='inline';
	}else{
		document.getElementById('locBankName').value='';
		document.getElementById('locCreditPrd').value='';
		document.getElementById('locCreditAmt').value='';
		document.getElementById('locBeneficerName').value='';
		document.getElementById('bnkId').style.display='none';
		document.getElementById('crdPId').style.display='none';
		document.getElementById('crdAId').style.display='none';
		document.getElementById('benfId').style.display='none';
	}
}
 $(document).ready(function() {     
    $('#subProfit_center').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        enableCaseInsensitiveFiltering: true,
        onChange: function(element, checked) {
          var val = $('#subProfit_center').val();
          var val1 =document.getElementById("preVal").value;
          if(val1!= null && val1=='ALL' && val !=null && val[1]!=undefined ){
          $("#subProfit_center").multiselect('clearSelection');
          val = removeElementsWithValue(val, 'ALL');
          $("#subProfit_center").val(val);
           $("#subProfit_center").multiselect("refresh");
           document.getElementById("preVal").value = '';
          }
          else if (val !=null && val[0]=='ALL' ) {
          	$("#subProfit_center").multiselect('clearSelection');
          	$("#subProfit_center").val('ALL');
          	 $("#subProfit_center").multiselect("refresh");
          	 document.getElementById("preVal").value = 'ALL';
          }
          else if(val== null || val[0]==''){
          $("#subProfit_center").multiselect('clearSelection');
          $("#subProfit_center").multiselect("refresh");
          document.getElementById("preVal").value = '';
          }
      	}                     
    }); 
    
     <s:if test='subProfit_center!=null && !"".equals(subProfit_center)'>	
 		var uwgrade='<s:property value="subProfit_center"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");   	 
	   	$("#subProfit_center").val(dataArray);
		 $("#subProfit_center").multiselect("refresh");
	</s:if>    
           
});
function CedentRetention(){
	var contrctNo = document.getElementById("contNo").value;
	var proposalNO = document.getElementById("proposalNo").value;
	//var val = document.getElementById("claimPaymentNo").value;
	//var val1 = document.getElementById("creditAmountCLC").value;
	//var val2 = document.getElementById("creditAmountCLD").value;
	//var val3 = document.getElementById("CLCsettlementRate").value;
	
		//var currencyid = document.getElementById("baseCurrencyId").value;
		//var currencyid = document.getElementById("currId").value;
		var URL ='${pageContext.request.contextPath}/getCedentRetentionRiskDetails.action?contNo='+contrctNo+'&proposal_no='+proposalNo;
		var windowName = "Details";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 800;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=no';
		var strOpen = window.open (URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		strOpen.focus();
		return false;
}
//getRetentionDetails('<s:property value="retentionYN"/>');
function getRetentionDetails(val){
	if("Y"==val){
		document.getElementById("retentionid").style.display='inline';
	}
	else{
		document.getElementById("retentionid").style.display='none';
	}

}
function getRetention(){
var cedingNo=document.getElementById("CeddingId").value;
var uwYear=document.getElementById("uwYear").value;
if(cedingNo!=null && cedingNo!='' &&  uwYear!=null && uwYear!=''){
postFormRequest('${pageContext.request.contextPath}/GetRetentionDetailsRiskDetails.action?dropDown=retention', "retentionid", "proportional");
}
}
 function getViewContractDetails(proposalNo,amendId) {
		var URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		var windowName = "Contract Details";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 800;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=no';
			var strOpen = window.open (URL, windowName, features);
			mtbChildWin[mtbWinCound] = strOpen;
			mtbWinCound++;
			strOpen.focus();
			return false;
	}
	contractListValCheck();
	function contractListValCheck(){
	var val = '<s:property value="contractListVal"/>';
	if(val!="" && val !=null){
	document.getElementById("contract"+val).checked = true;
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

function middleMinusRestrictionNeg(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
        if(text[i]=='-') min++;
        if(min<1) { 
        	prevValue += text[i];
        }
        }
    txt.value= prevValue;
    return false;
}
function functionEDate()
{
	var	inceptionDate=document.proportional.incepDate.value;
	var	expirydate=document.proportional.expDate.value;
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
}
//getBouquest('<s:property value="bouquetModeYN"/>');
function getBouquest(val){
	if(val=="Y"){
    	document.getElementById('bouquetid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('bouquetid').style.display = 'none';
   	}
}
//getRiskInfo('<s:property value="riskdetailYN"/>');
function getRiskInfo(val){
	if(val=="Y"){
    	document.getElementById('riskid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('riskid').style.display = 'none';
   	}
}
//getBrokerInfo('<s:property value="brokerdetYN"/>');
function getBrokerInfo(val){
	if(val=="Y"){
    	document.getElementById('brokerid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('brokerid').style.display = 'none';
   	}
}
//getCoverInfo('<s:property value="coverdetYN"/>');
function getCoverInfo(val){
	if(val=="Y"){
    	document.getElementById('coverid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('coverid').style.display = 'none';
   	}
}
//getPremiumInfo('<s:property value="premiumdetailYN"/>');
function getPremiumInfo(val){
	if(val=="Y"){
    	document.getElementById('premiumid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('premiumid').style.display = 'none';
   	}
}
//getAcqInfo('<s:property value="acqdetailYN"/>');
function getAcqInfo(val){
	if(val=="Y"){
    	document.getElementById('aquid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('aquid').style.display = 'none';
   	}
}
//getCommInfo('<s:property value="commissiondetailYN"/>');
function getCommInfo(val){
	if(val=="Y"){
    	document.getElementById('commid').style.display = 'block';
   	} 
   	else{
   	 	document.getElementById('commid').style.display = 'none';
   	}
}
//getSlideInfo('<s:property value="slideScaleCommission"/>');
function  getSlideInfo(id){
	if(id=="Y"){
    		document.getElementById('ssc').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('ssc').style.display = 'none';
      	}
}
//getDepositInfo('<s:property value="depositdetailYN"/>');
function  getDepositInfo(id){
	if(id=="Y"){
    		document.getElementById('depositid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('depositid').style.display = 'none';
      	}
}
//getLossdetInfo('<s:property value="lossdetailYN"/>');
function  getLossdetInfo(id){
	if(id=="Y"){
    		document.getElementById('lossid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('lossid').style.display = 'none';
      	}
}
//getLossparInfo('<s:property value="lossParticipants"/>');
function  getLossparInfo(id){
	if(id=="Y"){
    		document.getElementById('lp').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('lp').style.display = 'none';
      	}
}

//getDocdetInfo('<s:property value="docdetailYN"/>');
function  getDocdetInfo(id){
	if(id=="Y"){
    		document.getElementById('documentid').style.display = 'block';
      	} 
      	else {
      	 	document.getElementById('documentid').style.display = 'none';
      	}
}
//ShareProfitCommission('<s:property value="share_Profit_Commission"/>');
function ShareProfitCommission(value) {
	if(value=='1'){
	document.getElementById('pc').style.display='block';
	}else if(value=='2'){
	document.getElementById('pc').style.display='none';
	}
}
//getCommissionField('<s:property value="commissionType"/>');
function  getCommissionField(id){
	if(id=="PR" || id=="LR"){
   		document.getElementById('comper').style.display = 'none';
   		document.getElementById('supcom').style.display = 'none';
   		document.getElementById('setup').style.display = 'block';
     	} 
  	else if(id=="PC"){
		document.getElementById('comper').style.display = 'block';
		document.getElementById('supcom').style.display = 'block';
		document.getElementById('setup').style.display = 'none';	       	}
	else{
		document.getElementById('comper').style.display = 'none';
		document.getElementById('supcom').style.display = 'none';
		document.getElementById('setup').style.display = 'none';	 
	}
}
//getpopupenable();
function getpopupenable(){
	var id = document.getElementById('commissionType').value;
	var slide ="";
	if(id=="PC"){
	 slide = document.proportional.superProfitCommission.value;
	}
	if(id=="PR"){
 		document.getElementById('ratioPopUp').style.display = 'inline';
   	} 
   	else if(id=="LR"){
		document.getElementById('ratioPopUp').style.display = 'inline';
 			       	}
   	else if(id=="PC" && slide =="Y"){
		document.getElementById('compoptype').style.display = 'inline';
 			}
	else{
	document.getElementById('compoptype').style.display = 'none';
	document.getElementById('ratioPopUp').style.display = 'none';
	}
}
//getLossYear('<s:property value="lossCarried"/>');
function getLossYear(id){
	if(id!="TE"){
     	document.getElementById('lossyear').style.display = 'block';
      } 
     else{
		document.getElementById('lossyear').style.display = 'none';
     }
}
//getProfitYear('<s:property value="profitCarried"/>');
function getProfitYear(id){
		if(id!="TE"){
     		document.getElementById('profityear').style.display = 'block';
       		} 
       	else{
			document.getElementById('profityear').style.display = 'none';
     		  }
}
//DocScript('<s:property value="docStatus"/>');
function DocScript(id){
    var amendId = document.proportional.amendId.value;
    if (amendId > 0) {
        document.proportional.docStatus.value = id;
        if ("Y" == id) {
            document.getElementById('docView').style.display = 'block';
        }
        else {
            document.getElementById('docView').style.display = 'none';
        }
    }
}
function getPaypartner(val,id){
	var cedingid=document.getElementById("CeddingId").value;
	var URL='${pageContext.request.contextPath}/paymentPartnerAjaxXol.action?dropDown='+id+'&cedingId='+cedingid+'&brokerId='+val;
 		postRequest(URL,id);
}
function getUnderwriterShare(val){
	var proStatus = document.getElementById("proStatus").value
	if("64"==val && proStatus=="A"){
	var share ='<s:property value="shareValue"/>' ;
	document.getElementById("leader_Underwriter_share").value = share;
	document.getElementById("leader_Underwriter_share").disabled=true;
	}
	else{
	document.getElementById("leader_Underwriter_share").value ="";
	document.getElementById("leader_Underwriter_share").disabled=false;
	}
}
function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo){
	 var type = document.getElementById("commissionType").value;
	 var referenceNo = document.getElementById("referenceNo").value;
	 var disableStatus1 = '<s:property value="disableStatus1"/>';
	 var treatyType = document.proportional.treatyType.value; 
	 if("Y"==disableStatus1 && ""==renewalProposalNo){
		 //renewalProposalNo =proposalNo;
	 }
	 if(renewalProposalNo!=null && renewalProposalNo!=''){
		 proposalNo=renewalProposalNo;
	 }
	 if("scale"==pageFor){
	  document.getElementById("slidePopUp").value = "Y";
	 }else if("lossparticipates"==pageFor){
	  document.getElementById("lossPopUp").value = "Y";
	 }else if("profitCommission"==pageFor){
	  document.getElementById("profitPopUp").value = "Y";
	 }
	 $.ajax({
	        type: "POST",
	        url: "${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType+"&referenceNo="+referenceNo,
	        //data:'src_type_id='+val,
	        success: function(data){
	            $('#companyAjaxId1').html(data);
	           
	    			$( "#periodDate" ).datepicker({
	    				changeMonth : true,
	    				changeYear : true,
	    				dateFormat : "dd/mm/yy"
	    				//yearRange: "-100:+0"
	    			});
	    		
	        }
	        });
	//var URL ="${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&commissionType="+type+"&treatyType="+treatyType+"&referenceNo="+referenceNo;
	//postRequest(URL, 'companyAjaxId1');	
	
}
function CalculateValue() {
	var ouracqCost =0;
	var commissionQ_S =  0;
	var commission_surp =  0;
	var premiumQS=0;
	var premiumSurplus=0;
	var epios = 0;
	var inwardType= document.getElementById("inwardType").value;
	var treatyType = document.proportional.treatyType.value; 
	if('2'==inwardType){
		 ouracqCost = document.proportional.ouracqCost.value;
		if(ouracqCost ==  null || ouracqCost ==""){
		ouracqCost =  0;
		}
		}
		if('1'==treatyType || '3'==treatyType ||'4'==treatyType){
		 commissionQ_S = document.proportional.commissionQ_S.value;
		if(commissionQ_S ==  null || commissionQ_S ==""){
		commissionQ_S =  0;
		}
		premiumQS = document.proportional.premiumQuotaShare.value;
		if(premiumQS ==  null || premiumQS ==""){
		premiumQS =  0;
		}
		}
		
		if('2'==treatyType){  
		 commission_surp = document.proportional.commission_surp.value;
		if(commission_surp ==  null || commission_surp ==""){
		commission_surp =  0;
		}
		premiumSurplus = document.proportional.premiumSurplus.value;
		if(premiumSurplus ==  null || premiumSurplus ==""){
		premiumSurplus =  0;
		}
		}
		
		if('3'==treatyType){ 
		 commissionQ_S = document.proportional.commissionQ_S.value;
		if(commissionQ_S ==  null || commissionQ_S ==""){
		commissionQ_S =  0;
		}
		 commission_surp = document.proportional.commission_surp.value;
		if(commission_surp ==  null || commission_surp ==""){
		commission_surp =  0;
		}
		premiumQS = document.proportional.premiumQuotaShare.value;
		if(premiumQS ==  null || premiumQS ==""){
		premiumQS =  0;
		}
		premiumSurplus = document.proportional.premiumSurplus.value;
		if(premiumSurplus ==  null || premiumSurplus ==""){
		premiumSurplus =  0;
		}
		}
		var overRidder = document.proportional.overRidder.value;
		if(overRidder ==  null || overRidder ==""){
		overRidder =  0;
		}
		var brokerage = document.proportional.brokerage.value;
		if(brokerage ==  null || brokerage ==""){
		brokerage =  0;
		}
		var tax = document.proportional.tax.value;
		if(tax ==  null || tax ==""){
		tax =  0;
		}
		var othercost = document.proportional.othercost.value;
		if(othercost ==  null || othercost ==""){
		othercost =  0;
		}
		if(commissionQ_S!=0){
		commissionQ_S=commissionQ_S.replace(new RegExp(',', 'g'),'');
		}if(commission_surp!=0){
		commission_surp=commission_surp.replace(new RegExp(',', 'g'),'');
		}if(overRidder!=0){
		overRidder=overRidder.replace(new RegExp(',', 'g'),'');
		}if(brokerage!=0){
		brokerage=brokerage.replace(new RegExp(',', 'g'),'');
		}if(tax!=0){
		tax=tax.replace(new RegExp(',', 'g'),'');
		}if(othercost!=0){
		othercost=othercost.replace(new RegExp(',', 'g'),'');
		}if(ouracqCost!=0){
		ouracqCost=ouracqCost.replace(new RegExp(',', 'g'),'');
		}//if(epiOSOEViewOC!=0){
		//epiOSOEViewOC=epiOSOEViewOC.replace(new RegExp(',', 'g'),'');
		//}
		if(premiumQS!=0){
		premiumQS=premiumQS.replace(new RegExp(',', 'g'),'');
		}
		if(premiumSurplus!=0){
		premiumSurplus=premiumSurplus.replace(new RegExp(',', 'g'),'');
		}
		if(commissionQ_S!=0){
		var QSAmt=(parseFloat(commissionQ_S)*parseFloat(premiumQS))/100;
		//document.proportional.commissionQ_SAmt.value=Comma(QSAmt.toFixed(2));
		}
		if(commission_surp!=0){
		var SurpAmt=(parseFloat(commission_surp)*parseFloat(premiumSurplus))/100;
		
		//document.proportional.commission_surpAmt.value=Comma(SurpAmt.toFixed(2));
		}
		<%--if(''!='<s:property value="epiAsPerShare"/>' && '0'!='<s:property value="epiAsPerShare"/>'){
		 epios = '<s:property value="epiAsPerShare"/>';
		}
		else --%>
		if(''!='<s:property value="epiOSViewOC"/>'){
		 epios = '<s:property value="epiOSViewOC"/>';
		 if(epios!=0){
			epios=epios.replace(new RegExp(',', 'g'),'');
		}
		}
		var share ='<s:property value="shareValue"/>' ;
		var com =0;
		var sur =0;
		var total =0;
		var val = 0;
		if('3'==treatyType){ 
		 com = parseFloat(premiumQS)*parseFloat(share)/100*parseFloat(commissionQ_S)/100;
		 sur = parseFloat(premiumSurplus)*parseFloat(share)/100*parseFloat(commission_surp)/100;
		 total = parseFloat(epios)*((parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		
		}
		if('1'==treatyType || '5'==treatyType ||'4'==treatyType){
		 total = parseFloat(epios)*((parseFloat(commissionQ_S)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		}
		else if('2'==treatyType){  
		 total = parseFloat(epios)*((parseFloat(commission_surp)+parseFloat(overRidder)+parseFloat(brokerage)+parseFloat(tax)+parseFloat(othercost))/100);
		}
		 val =  parseFloat(ouracqCost) +  com +sur+ total ;
		//var sum = parseFloat(premiumQS) + parseFloat(premiumSurplus);
		
		//var val= parseFloat(ouracqCost) + ((parseFloat(epiOSOEViewOC) *(parseFloat(tot)))/100)
		//document.proportional.acquisition_Cost.value=Comma(val.toFixed(2));
		//document.proportional.acqCostPer.value=Comma(total.toFixed(2));
		//document.getElementById("acquisition_Cost").value=Comma(val.toFixed(2));
	}
function funEditMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
   // document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='layer';
    document.proportional.action="EditSectionRiskDetails.action?departmentId="+deptId;
    
    document.proportional.submit();
}
function funNewMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId,secMode) {
	
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
   // document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='copy';
	document.getElementById("sectionMode").value=secMode;
    document.proportional.action="EditSectionRiskDetails.action?departmentId="+deptId;
    
    document.proportional.submit();
}
function funDeleteLayer(no){
	destroyPopUps();
	document.getElementById("proposalReference").value='Layer';
	document.getElementById("renewalProposalNo").value=no;
	document.proportional.action='${pageContext.request.contextPath}/CancelSectionRiskDetails.action';
	document.proportional.submit();
	
}
function funCopyMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
   // document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='copy';
    document.proportional.action="EditSectionRiskDetails.action?departmentId="+deptId;
    document.proportional.submit();
}
function fnSubmitScale(){
	postFormRequest('${pageContext.request.contextPath}/insBonusDetailsRiskDetails.action', "companyAjaxId1", "proportional");
}
function scaleremoveRow(val){
	var scale=document.getElementById("pageFor").value;
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowRiskDetails.action?mode=delete&deleteId="+val+"&dropDown="+scale, "tb1", "proportional");
			}
		}
}
function scalecalculate(){
	var scale=document.getElementById("pageFor").value;
postFormRequest("${pageContext.request.contextPath}/calculateSCRiskDetails.action?dropDown="+scale, "companyAjaxId1", "proportional");
			
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
			//cell4.setAttribute('style','background-color:#C6E2FF;');
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "scalemaxpartpercent["+(rowCount-1)+"]";
      		element5.id = "scalemaxpartpercent["+(rowCount-1)+"]";
			element5.className = "inputBox";
			element5.setAttribute("style", "text-align:right;");
			element5.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			cell5.appendChild(element5);
			
			var cell6 = row.insertCell(5);
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			//element5.setAttribute("onclick", "deleteRow('bonusTbl','"+(rowCount)+"')");
			element6.setAttribute("onclick", "scaleremoveRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-info"
			cell6.appendChild(element6);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}
function funViewMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	
	var URL ='<%=request.getContextPath()%>/sectionViewRiskDetails.action?proposalNo1='+proposalno;
	var windowName = "Details";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 800;
	var h = 400;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
	strOpen.focus();
	return false;
}
function getPremiumRes(val){
	if(val=='1'){
		document.proportional.premium_Reserve.value='';
		document.getElementById("premium_Reserve").readOnly=false;
	}
	else{
		document.getElementById("premium_Reserve").readOnly=true;
		document.proportional.premium_Reserve.value="0";
	}
}
function procceed(){
	
	document.proportional.action="InitRiskDetails.action";
	document.proportional.submit();
	
}
<s:if test='bouquetModeYN!=null && !"".equals(bouquetModeYN)'>	
document.getElementById('bouquestid').style.display = 'block';
document.getElementById('bouquetModeYNY').disabled=true;
document.getElementById('bouquetModeYNN').disabled=true;

document.getElementById('bouquetpds').style.display = 'none';
if ($("#bouquetModeYNY").prop("checked")) {
	document.getElementById('bouquetNo').disabled=true;
}
</s:if>

function getMethod(val){
	if (val=='MA') {
         document.getElementById('methodida').style.display = 'block';
         document.getElementById('methodidb').style.display = 'none';
         //document.getElementById('scalegrid').style.display = 'block';
         
     }
     else {
    	 document.getElementById('methodida').style.display = 'none';
         document.getElementById('methodidb').style.display = 'block';
         //document.getElementById('scalegrid').style.display = 'none';
     }
    
}

function getSubseqCal(val){
	if (val=='Y') {
         document.getElementById('subseqcalid').style.display = 'none';
     }
     else {
         document.getElementById('subseqcalid').style.display = 'block';
     }
    
}
function getScSubseqCal(val){
	if (val=='Y') {
         document.getElementById('scsubseqcalid').style.display = 'none';
     }
     else {
         document.getElementById('scsubseqcalid').style.display = 'block';
     }
    
}
function getscalePeriod(val){
	if (val=='P') {
         document.getElementById('scfiid').style.display = 'block';
         document.getElementById('periodid').style.display = 'none';
         document.getElementById('scseid').style.display = 'block';
     }
     else {
         document.getElementById('periodid').style.display = 'block';
         document.getElementById('scfiid').style.display = 'none';
         document.getElementById('scseid').style.display = 'none';
     }
    
}


function getPcscalePeriod(val){
	if (val=='P') {
         document.getElementById('pcscfiid').style.display = 'block';
         document.getElementById('pcperiodid').style.display = 'none';
         document.getElementById('pcscseid').style.display = 'block';
     }
     else {
         document.getElementById('pcperiodid').style.display = 'block';
         document.getElementById('pcscfiid').style.display = 'none';
         document.getElementById('pcscseid').style.display = 'none';
     }
    
}
function getLossRatio(val){
	var header=document.getElementById(val).value;
	$("#claimrfid").html(header);
	$("#claimrtid").html(header);
	$("#lossparid").html(header);
	$("#lossparmaxid").html(header);
//	$("#claimrfid").html(header);
	
    
}

function fnSubmitPC(){
	postFormRequest('${pageContext.request.contextPath}/insProfieCommissionRiskDetails.action', "companyAjaxId1", "proportional");
}
function commissionremoveRow(val){
	var scale=document.getElementById("pageFor").value;
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowRiskDetails.action?mode=delete&deleteId="+val+"&dropDown="+scale, "tb2", "proportional");
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
function getPrint() {
	$(".btn").hide();
	window.print();
	$(".btn").show(); 
	}
function goBack(productId, deptId, flag) {
	if (flag == 'P'||flag == '') {
		document.proportional.action ="commonListPortfolio?manufactureID="+productId+"&Department="+deptId;
	}else if(flag=='C') {
		document.proportional.action="InitPortfolio";
	}else if(flag=='H') {
		document.proportional.action="getHistoryPortfolio";
	}else if(flag=='R') {
		document.proportional.action="commonListPortfolio?manufactureID="+productId;
	}else if(flag=='N') {
		document.proportional.action="commonListPortfolio?manufactureID="+productId;
	}else if(flag=='RP') {
		document.proportional.action ="commonListPortfolio?manufactureID="+productId;
	}else if(flag=='RD') {
		document.proportional.action="InitPortfolio?";
	}else if(flag=='EC') {
		document.proportional.action="InitPortfolio?";
	}
	document.proportional.submit();
}
<s:if test="#elayerInfo!=null && #elayerInfo.size()>0">

	<s:iterator value="#elayerInfo" var="list"  status="stat">
	var id="sectionid<s:property  value='%{#stat.count}'/>";
	var proposalno=<s:property value='#list.PROPOSAL_NO'/>;
	var URL='${pageContext.request.contextPath}/sectionViewRiskDetails.action?proposalNo1='+proposalno+'&dropDown='+id;
		postRequest(URL,id);
	</s:iterator>

</s:if>
function postRequest(strURL, id)
{
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function() 
	{
		if (xmlHttp.readyState == 4) 
		{
			document.getElementById(id).innerHTML=xmlHttp.responseText;
		}
   }
	xmlHttp.send(null);
}
</script>
	</body>
</html>
