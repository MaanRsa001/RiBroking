<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
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
			
</head>
<body>
<s:set var="elayerInfo" value='%{layerInfo}'/>
<s:set var="ebouquetExistingList" value='%{bouquetExistingList}'/>
<s:set var="dislayer" value="%{baseLayer!=null && !''.equals(baseLayer)}"/>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="xolView" id="xolView" theme="simple" action="" method="post" autocomplete="off">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
							<strong> 
							<s:if test='!"".equals(contractGendration) && null!=contractGendration'>
							   <font color="blue"> <s:property value="status" /> </font>
							</s:if>
							</strong>
						</div>						
						<div class="tablerow" align="center">
							<span class="pageHeading">							
							<s:text name="label.riskDetailSheetXol" />
							</span>
						</div>
						<s:if test="contNo != null && contNo != '' && proposal_no != null && proposal_no != '' && amend_Id_Mode != '' && amend_Id_Mode != null ">
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
													<s:select list="endosTypelist" name="endorsmenttype" id="endorsmenttype" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" onchange="getFieldDisable()" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.endorsementNo" />
												</div>
												<div class="tbox txtB">
													<s:property value="amendId"/>
												</div>
											</div>													 
											<br class="clear"></br>
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<s:else>
						<s:hidden name="endorsmenttype"  id="endorsmenttype"/>
						</s:else>
						</div>
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
														<s:radio list="#{'Y':'Yes','N':'No' }" name="bouquetModeYN" id="bouquetModeYN" value="%{bouquetModeYN==null?'N':bouquetModeYN}" onchange="getBouquest(this.value);" disabled="true"></s:radio>													
													</div>
												</div>										
												<div class="textfield33">
													<div id="bouquetid">
														<div class="text">
															<s:text name="label.bouquetMode" />
														</div>
														<div class="tbox">
															<s:select list="bouquetList" listValue="Bouquet_NO" listKey="Bouquet_NO" name="bouquetNo" id="bouquetNo" cssClass="select1 inputBoxS" headerKey="" headerValue="---None---" disabled="true"/>
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
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										<s:text name="label.cedentinfo" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.cedingCompany" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="RenewalMode != null">																												
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />															
													</s:if>
													<s:else>
														<s:if test="'Layer'.equals(proposalReference)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
															
														</s:if>
														<s:elseif test="baseLayer==null || ''.equals(baseLayer)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true"  />
															 
														</s:elseif>
														<s:else>
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
														</s:else>
													</s:else>
													 </div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.inceptionDate" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="RenewalMode != null">
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<div class="">
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)"  disabled="true" />
															</div>
														</s:if>
														<s:else>
															<div class="">
															<s:textfield name="incepDate" id="incepDate" cssClass="inputBox"  onkeyup="validateSpecialChars(this)" disabled="true" />
															</div>
														</s:else>
													</s:if>
													<s:else>
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<s:if test="prclFlag == true">
																<div class="">
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"    readonly="true"/>
																</div>
															</s:if>
															<s:else>
																<div class="">
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled="true" />
																</div>																
															</s:else>
														</s:if>
														<s:else>
															<div class="">
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled="true" />
															</div>
														</s:else>
													</s:else>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.expiryDate" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:if test="layerProposalNo == null || layerProposalNo == ''">
														<div class="">
														<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  disabled="true" onchange="functionEDate();"/>
														</div>														
													</s:if>
													<s:else>
													<div class="">
													<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  disabled="true" onchange="functionEDate();"/>
													 </div>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYearFrom" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="yearId">
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYearto" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="yearIdto">
													<s:select list="yearToList" listKey="YEAR" listValue="YEAR" name="uwYearTo" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
												</div>
											</div>
											
											<br class="clear"></br>
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
					<s:hidden name="proposalNo1" id="proposalNo1"/>
					<s:hidden name="flag" id="flag"/>
					<s:hidden name="mode" id="mode"/>
					<s:hidden name="manufactureID" id="manufactureID"/>
					<s:hidden name="menuId" id="menuId"/>
					<s:hidden name="branchCode" />
					<s:hidden name="xlPremium" value="0" />			
					<s:hidden name="CustomerId" value="cedingCo" />
					<s:hidden name="layerProposalNo" />
					<!--<s:hidden name="brokerage" />
					<s:hidden name="tax" />-->
					<s:hidden name="contractno1" id="contractno1"/>
					<s:hidden name="renewal_contract_no" />
					<s:hidden name="renewalFlag" />
					<s:hidden name="lay1" id="lay1" />
					<%-- <s:hidden name="layerLayerNo" id="layerLayerNo" /> --%>
					<s:hidden name="baseLoginID" />
					<s:hidden name="amendId" />
					<s:hidden name="contractNo" value="%{contNo}"/>
					<!--<s:hidden name="contNo"/>
					--><s:hidden name="edit" id="edit"/>
					<s:hidden name="cedType" id="cedType" />
					<s:hidden name="endorsmentno"/>
					<s:hidden name="proposalNo" id="proposalNo" value="%{proposal_no}"/>
					<s:hidden name="limitOrigCur"/>
					<s:hidden name="deduc_hunPercent"/>
					<s:hidden name="layerMode" id="layerMode"/>
					<s:hidden name="countryExcludedList" id="countryExcludedList"/>
					<s:hidden name="countryIncludedList" id="countryIncludedList"/>
					<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>
					<s:hidden name="proposalReference" id="proposalReference"/>
					<s:hidden name="prePerilVal" id="prePerilVal"/>
					<s:hidden name="reMode" id="reMode"/>
					<s:hidden name="renewalEditMode" id="renewalEditMode"/>
					<s:hidden name="bonusPopUp" id="bonusPopUp"/>
					 <s:hidden name="reinsPopUp" id="reinsPopUp"/>
					 <s:hidden name="proStatus" id="proStatus" value="A"/>
					  <s:hidden name="pageFor" id="pageFor"/>
					   <s:hidden name="Typename" id="Typename"/>
					   <s:hidden name="CustomerId" id="CustomerId"/>
					   <s:hidden name="baseLayer" id="baseLayer"/>
					   <s:hidden name="multiuserMode" id="multiuserMode"/>
					   <s:hidden name="editMode" id="editMode"></s:hidden>
					   <s:hidden name="referenceNo" id="referenceNo"></s:hidden>
					   <s:hidden name="preVal" id="preVal"/>
					   <s:hidden name="coverlimitTot" id="coverlimitTot"/>
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
function goBack(productId, deptId, flag) {
	if (flag == 'P'||flag == '') {
		document.xolView.action ="commonListPortfolio.action?manufactureID="+productId+"&Department="+deptId;
	}else if(flag=='EC') {
		document.xolView.action="InitPortfolio.action?flag="+flag+"&menuId=${session.menuId}";
	}else if(flag=='H') {
		document.xolView.action="getHistoryPortfolio.action?flag="+flag+"&menuId=${session.menuId}";
	}else if(flag=='R') {
		document.xolView.action="commonListPortfolio.action?manufactureID="+productId+"&flag=R";
	}else if(flag=='N') {
		document.xolView.action="commonListPortfolio.action?manufactureID="+productId+"&flag=N";
	}else if(flag=='RP') {
		document.xolView.action ="commonListPortfolio.action?manufactureID="+productId+"&flag=RP";
	}else if(flag=='RD') {
		document.xolView.action="InitPortfolio.action?flag="+flag;
	}else if(flag=='C') {
		document.xolView.action="InitPortfolio?flag=C&menuId=${session.menuId}";
	}
	document.xolView.submit();
}

function getPrint() {
	$(".btn").hide();
	window.print();
	$(".btn").show(); 
}
<s:if test="#elayerInfo!=null && #elayerInfo.size()>0">

<s:iterator value="#elayerInfo" var="list"  status="stat">
var id="sectionid<s:property  value='%{#stat.count}'/>";
var proposalno=<s:property value='#list.PROPOSAL_NO'/>;
var URL='${pageContext.request.contextPath}/layerViewXol.action?proposalNo1='+proposalno+'&dropDown='+id;
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

