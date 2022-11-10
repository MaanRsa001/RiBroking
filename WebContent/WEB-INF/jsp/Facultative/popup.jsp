<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/reset.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/style.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/motor.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/test.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/disableF5.js"></script>
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head>
	<body>
<s:form name="cusPopup" id="cusPopup" action="" method="post" theme="simple">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<div class="boxcontent">
							<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
											<s:text name="label.nameaddressdetails" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield" >
												<div class="text" id="cc" >
													<s:text name="cedingcompany.firstName" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="text" id="BN" style="display:none">
													<s:text name="broker.firstname" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="text" id="LN" style="display:none">
													<s:text name="leader.firstname" />&nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">													
													<s:textfield name="firstName" cssClass="inputBox" maxlength="200" readonly="true"/>													
												</div>
											</div>
											<div class="textfield"  id="BG" style="display: none">
												<div class="text" >
													<input type="checkbox" name="brokerGroup" id="brokerGroup" onchange="checkboxChange()" />
													<s:text name="Broker_Group" />
												</div>
												<div class="tbox" id="broGroup" style="display: none">
													<s:select list="broGroupList" name="broGroup" id="broGroup" listKey="CUSTOMER_ID" listValue="FIRST_NAME" cssClass="inputBoxS" headerKey=""  headerValue="---Select---" disabled="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address1" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="address1" cssClass="inputBox" maxlength="500" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address2" /> &nbsp;
												</div>
												<div class="tbox">
													<s:textfield name="address2" cssClass="inputBox" maxlength="150" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.city" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="city" cssClass="inputBox" maxlength="200" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.country" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:select list="brokercountryList" name="country" id="country" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="inputBoxS" headerKey=""  headerValue="---Select---" disabled="true" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.zipcode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="zipcode" cssClass="inputBox" maxlength="25" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.emailAddress" />
												</div>
												<div class="tbox">
													<s:textfield name="email" cssClass="inputBox" maxlength="100" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.phone" />
												</div>
												<div class="tbox">
													<s:textfield name="telephone" cssClass="inputBox" maxlength="100" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.mobile" />
												</div>
												<div class="tbox">
														<s:textfield name="mobile" cssClass="inputBox"  onchange="checkNumbers(this.value)" readonly="true"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.faxNo" />
												</div>
												<div class="tbox">
													<s:textfield name="faxNo" cssClass="inputBox" maxlength="20" readonly="true"/>
												</div>
											</div>
												
											
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" value="active==null?'Y':active" disabled="true"/>
												</div>
											</div>
										</div>
										</div>
										
						              
										
								</div>
							</div>
							<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
														<s:text name="label.bankdetails" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
							     					 <div class="panel-body">
							     					 <div id="bankid">
														<table class="table table-bordered" width="100%" id="newgen1">
															<thead>
																<tr>
																	<th width="2.8%"> <s:text name="label.serialnumber" /></th>
																	<th width="15.8%"> <s:text name="label.bankcurrency" /></th>
																	<th width="15.8%"> <s:text name="label.bankaccountnumber" /></th>
																	<th width="15.8%"> <s:text name="label.bankname" /></th>
																	<th width="15.8%"> <s:text name="label.accountname" /></th>
																	<th width="15.8%"> <s:text name="label.swiftcode" /> </th>
																	<th width="15.8%"> <s:text name="label.coresondentbank" /> </th>
																	<th width="15.8%"> <s:text name="label.remarks" /> </th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="currencyList" var="list" status="stat">
																	<tr>
																		<td><s:textfield name="bankSNo[%{#stat.count-1}]" id="bankSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/></td>
																	
																		<td>
																			<s:select  list="bankCurrencyList"  name="bankCurrency[%{#stat.count-1}]" id="bankCurrency%{#stat.count-1}" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CURRENCY_ID"  listValue="CURRENCY_NAME" disabled="true"/>
																		</td>
																		<td>
																			<s:textfield name="bankaccountnumber[%{#stat.count-1}]" id="bankaccountnumber[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		<td>
																			<s:textfield name="bankname[%{#stat.count-1}]" id="bankname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		<td>
																			<s:textfield name="accountname[%{#stat.count-1}]" id="accountname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		<td>
																			<s:textfield name="swiftcode[%{#stat.count-1}]" id="swiftcode[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		<td>
																			<s:textarea name="corespondentbank[%{#stat.count-1}]" id="corespondentbank[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		<td>
																			<s:textarea name="bankRemarks[%{#stat.count-1}]" id="remarks[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" readonly="true"/>
																		</td>
																		
																		</tr>
																	</s:iterator>
															</tbody>
														</table>
														</div>
													  </div>
													 
												</div>
							                  </div>
							               </div>
						              </div>
						              <div class="boxcontent">
										<div class="panel panel-primary">											
											<div class="panel-heading">
												<s:text name="label.contractdetails" />
											</div>
											<div class="panel-body">
												<div class="boxcontent">
													<div class="panel-body">
														<div id="contact">
														<table class="table table-bordered" width="100%" id="newgen">
															<thead>
																<tr>
																<th width="2.8%"> <s:text name="label.serialnumber" /></th>
																<th width="15.8%"> <s:text name="label.departmentName" /></th>
																<th width="15.8%"> <s:text name="label.subdepartmentName" /></th>
																<th width="15.8%"> <s:text name="label.emailaddress" /></th>
																<th width="15.8%"> <s:text name="label.telephonenumber" /></th>
																<th width="15.8%"> <s:text name="label.faxnumber" /> </th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="contactList" var="list" status="stat">
																	<tr>
																		<td><s:textfield name="contactSNo[%{#stat.count-1}]" id="contactSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/></td>
																		<td>
																		<s:select  list="departList"  name="departmentCD[%{#stat.count-1}]" id="departmentCD%{#stat.count-1}" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" disabled="true"/>
																		</td>
																		<td>
																		<s:textfield name="subdepartmentCD[%{#stat.count-1}]" id="subdepartmentCD[%{#stat.count-1}]" cssClass="inputBox" readonly="true"/>
																		</td>
																		<td>
																		<s:textfield name="emailaddress[%{#stat.count-1}]" id="emailaddress[%{#stat.count-1}]" cssClass="inputBox" readonly="true"/>
																		</td>
																		<td>
																		<s:textfield name="telephonenumber[%{#stat.count-1}]" id="telephonenumber[%{#stat.count-1}]" cssClass="inputBox" readonly="true"/>
																		</td>
																		<td>
																		<s:textfield name="faxnumber[%{#stat.count-1}]" id="faxnumber[%{#stat.count-1}]" cssClass="inputBox" readonly="true"/>
																		</td>
																		
																		</tr>
																	</s:iterator>
															</tbody>
														</table>
														</div>
													</div>
													
												</div>
											</div>
										 </div>
									</div>
								<s:if test='"RI02".equals(#session.SOURCE_CODE)'>	
									<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
											<s:text name="label.tdsdetails" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
													<div class="text">
														<s:text name="ceding.panNo" />
													</div>
													<div class="tbox">
														<s:textfield name="panNo" cssClass="inputBox" maxlength="10" readonly="true"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isIndividual" />
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isIndividual" value="isIndividual==null?'N':active" disabled="true"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isNonResident" />
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isNonResident" value="isNonResident==null?'N':active" disabled="true"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.specialRate" />
													</div>
													<div class="tbox">
														<s:textfield name="specialRate" cssClass="inputBox" maxlength="2" onkeyup="checkNumbers(this);"  readonly="true"/>
													</div>
												</div>

										</div>
										</div>					
						        </div>
						    </div>
						    </s:if>
						    <div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
											<s:text name="label.otherdetails" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
													<div class="text">
														<s:text name="label.estabilishment.year" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield  name="establishmentYear" id="establishmentYear" cssClass="inputBox" onkeyup="checkNumbers(this);" maxlength="4" readonly="true"/>
													</div>
											</div>
											<div class="textfield">
													<div class="text">
													<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
														<s:text name="label.regNo" />&nbsp;
														</s:if>
														<s:else>
														<s:text name="label.regNoira" />&nbsp;
														</s:else>
													</div>
													<div class="tbox">
														<s:textfield name="regNo" id="regNo" cssClass="inputBox"  readonly="true"/>
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.cinNo" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield name="cinNo" id="cinNo" cssClass="inputBox"  readonly="true"/>
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.rate" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield  name="rating" id="rating" cssClass="inputBox"  readonly="true"/>
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.rateagency" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield  name="ratingAgency" id="ratingAgency" cssClass="inputBox"  readonly="true"/>
													</div>
											</div>
											
											<div class="textfield">
													<div class="text">
														<s:text name="label.lastrate" />&nbsp;
													</div>
													<div class="tbox">
													<div class="inputAppend">
													<s:textfield name="lastRating" id="lastRating"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  readonly="true"/>
													</div>
													</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
												<s:text name="label.remarksdetails" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfieldA">
													<div class="text">
														<s:text name="country.Remarks" />
													</div>
													<div class="tbox">
														<s:textarea rows="1" name="clientRemarks" cssClass="inputBoxA" cssStyle="width: 200%;" readonly="true"/>
													</div>
											</div>
										</div>
									</div>
									</div>
						</div>
						</div>
					</div>
							</div>
						</div>
						</div>
					
						</div>
						</div>
					
					<div class="boxcontent" align="center">
					<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
					</div>
				
		</s:form>
	
<script type="text/javascript">
changename('<s:property value="clientType"/>');
function changename(val){
	if(val=='C'){
		document.getElementById('cc').style.display='inline';
		document.getElementById('BN').style.display='none';
		document.getElementById('LN').style.display='none';
		document.getElementById('BG').style.display='none';
		document.client.broGroup.value ='';
		document.client.brokerGroup.checked = false;
	}
	if(val=='B'){
		document.getElementById('BN').style.display='inline';
		document.getElementById('cc').style.display='none';
		document.getElementById('LN').style.display='none';
		document.getElementById('BG').style.display='inline';
		checkboxChange();
	}
	
}

function checkboxChange(){
	if(document.getElementById("brokerGroup").checked == true){
		document.getElementById('broGroup').style.display='inline';
	}else{
		document.getElementById('broGroup').style.display='none';
	}
}
checkbroGroup();
function checkbroGroup(){
    var val = document.cusPopup.broGroup.value;
    if(val != null && val !='') {
        document.getElementById('brokerGroup').checked = true;
        checkboxChange();
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