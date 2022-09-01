<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/dataTables/css/dataTables.tableTools.css">
<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.jqueryui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/dataTables.tableTools.js"></script>
<script type="text/javascript">
jQuery(function ($) {
		try {
			var data = $('#gridTableMake').dataTable( {
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"order": [[ 0, "asc" ]],
				"columnDefs": [ {
		          "targets": 'no-sort',
		          "orderable": false
			    } ],
			   // "stateSave": true,
				responsive: true
			});				 
		} catch(err){}
	} );	
</script>

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
<script type="text/javascript">
	 $(function() {
	    $( "#inceptionDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#lastRating" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	

	  </script>	
</head>
<body>
<s:form id="client" name="client" theme="simple" action="" method="post" enctype="multipart/form-data" >
<div class="row" style="display: table:width: 100%; margin: 0 auto;" >
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<span style="color:green"><s:actionmessage/></span>
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
      <div class="table1" style="width: 100%; margin: 0 auto;  ">
        <s:if test='"InsertClientMaster".equals(path)'>
        			<div class="tablerow">
							<div class="boxcontent">
									<div class="panel panel-primary">											
										<div class="panel-heading">
												
										</div>
										<div class="panel-body">
											<div class="boxcontent">
												<div class="textfield">
													<div class="text">
														<s:text name="label.clienttypes" /> &nbsp;<span style="color: red;">*</span>
													</div>
													<div class="tbox">
														<s:select list="clientList" name="clientType" id="clientType" cssClass="inputBoxS" headerKey="" listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerValue="---Select---" onchange="changename(this.value);" />
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.inceptionDate" /> &nbsp;<span style="color: red;">*</span>
													</div>
													<div class="tbox">
														<div class="inputAppend">
														<s:textfield name="inceptionDate" id="inceptionDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
														</div>
													</div>
												</div>
												
											</div>
										</div>
								</div>
						</div>
					</div>
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
													<s:textfield name="firstName" cssClass="inputBox" maxlength="200"/>													
												</div>
											</div>
											<div class="textfield"  id="BG" style="display: none">
												<div class="text" >
													<input type="checkbox" name="brokerGroup" id="brokerGroup" onchange="checkboxChange()"/>
													<s:text name="Broker_Group" />
												</div>
												<div class="tbox" id="broGroup" style="display: none">
													<s:select list="broGroupList" name="broGroup" id="broGroup" listKey="CUSTOMER_ID" listValue="FIRST_NAME" cssClass="inputBoxS" headerKey=""  headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address1" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="address1" cssClass="inputBox" maxlength="500"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.address2" /> &nbsp;
												</div>
												<div class="tbox">
													<s:textfield name="address2" cssClass="inputBox" maxlength="150"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.city" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="city" cssClass="inputBox" maxlength="200"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.country" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
												<s:select list="brokercountryList" name="country" id="country" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" cssClass="inputBoxS" headerKey=""  headerValue="---Select---" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.zipcode" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:textfield name="zipcode" cssClass="inputBox" maxlength="25"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.emailAddress" />
												</div>
												<div class="tbox">
													<s:textfield name="email" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.phone" />
												</div>
												<div class="tbox">
													<s:textfield name="telephone" cssClass="inputBox" maxlength="100"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.mobile" />
												</div>
												<div class="tbox">
														<s:textfield name="mobile" cssClass="inputBox"  onchange="checkNumbers(this.value)"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.faxNo" />
												</div>
												<div class="tbox">
													<s:textfield name="faxNo" cssClass="inputBox" maxlength="20"/>
												</div>
											</div>
												
											
											<div class="textfield">
												<div class="text">
													<s:text name="ceding.active" /> &nbsp;<span style="color: red;">*</span>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="active" value="active==null?'Y':active"/>
												</div>
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
		            <div class="tablerow">
						<div class="boxcontent">
							<div class="panel panel-primary">											
								<div class="panel-heading">
										<s:text name="label.bankdetails" />
								</div>
								<div class="panel-body">
									<div class="boxcontent">
			     					 <div class="panel-body">
										<table class="footable" width="100%" id="newgen">
											<thead>
												<tr>
													<th width="15.8%"> <s:text name="label.bankcurrency" /></th>
													<th width="15.8%"> <s:text name="label.bankaccountnumber" /></th>
													<th width="15.8%"> <s:text name="label.bankname" /></th>
													<th width="15.8%"> <s:text name="label.accountname" /></th>
													<th width="15.8%"> <s:text name="label.swiftcode" /> </th>
													<th width="15.8%"> <s:text name="label.ifsccode" /> </th>
													<th width="15.8%"> <s:text name="label.remarks" /> </th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="currencyList" var="list" status="stat">
													<tr><td>
															<s:select  list="bankCurrencyList"  name="bankCurrency[%{#stat.count-1}]" id="bankCurrency%{#stat.count-1}" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CURRENCY_ID"  listValue="CURRENCY_NAME"/>
														</td>
														<td>
															<s:textfield name="bankaccountnumber[%{#stat.count-1}]" id="bankaccountnumber[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
														</td>
														<td>
															<s:textfield name="bankname[%{#stat.count-1}]" id="bankname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
														</td>
														<td>
															<s:textfield name="accountname[%{#stat.count-1}]" id="accountname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
														</td>
														<td>
															<s:textfield name="swiftcode[%{#stat.count-1}]" id="swiftcode[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
														</td>
														<td>
															<s:textfield name="ifsccode[%{#stat.count-1}]" id="ifsccode[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
														</td>
														<td>
															<s:textfield name="bankRemarks[%{#stat.count-1}]" id="remarks[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})"/>
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
		            <div class="tablerow">							
						<div class="boxcontent">
							<div class="panel panel-primary">											
								<div class="panel-heading">
									<s:text name="label.contractdetails" />
								</div>
								<div class="panel-body">
									<div class="boxcontent">
										<div class="panel-body">
											<table class="footable" width="100%" id="newgen">
												<thead>
													<tr>
													<th width="2.8%"> <s:text name="label.serialnumber" /></th>
													<th width="15.8%"> <s:text name="label.departmentName" /></th>
													<th width="15.8%"> <s:text name="label.emailaddress" /></th>
													<th width="15.8%"> <s:text name="label.telephonenumber" /></th>
													<th width="15.8%"> <s:text name="label.faxnumber" /> </th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="contactList" var="list" status="stat">
														<tr>
															<td><s:property value="#stat.count"/></td>
															<td>
															<s:textfield name="departmentCD[%{#stat.count-1}]" id="departmentCD[%{#stat.count-1}]" cssClass="inputBox"/>
															</td>
															<td>
															<s:textfield name="emailaddress[%{#stat.count-1}]" id="emailaddress[%{#stat.count-1}]" cssClass="inputBox"/>
															</td>
															<td>
															<s:textfield name="telephonenumber[%{#stat.count-1}]" id="telephonenumber[%{#stat.count-1}]" cssClass="inputBox"/>
															</td>
															<td>
															<s:textfield name="faxnumber[%{#stat.count-1}]" id="faxnumber[%{#stat.count-1}]" cssClass="inputBox"/>
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
					<div class="tablerow">							
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
														<s:textfield name="panNo" cssClass="inputBox" maxlength="10"/>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isIndividual" />
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isIndividual" value="isIndividual==null?'N':active"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.isNonResident" />
													</div>
													<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="isNonResident" value="isNonResident==null?'N':active"></s:radio>
													</div>
												</div>
												<div class="textfield">
													<div class="text">
														<s:text name="ceding.specialRate" />
													</div>
													<div class="tbox">
														<s:textfield name="specialRate" cssClass="inputBox" maxlength="2" onkeyup="checkNumbers(this);" />
													</div>
												</div>

										</div>
										</div>					
						        </div>
						    </div>
						</div>
						</s:if>
						<s:else>
						<s:hidden name="panNo" id="panNo"/>
						<s:hidden name="isIndividual" id="isIndividual"/>
						<s:hidden name="isNonResident" id="isNonResident"/>
						<s:hidden name="specialRate" id="specialRate"/>
						</s:else>
						<div class="tablerow">							
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
														<s:textfield  name="establishmentYear" id="establishmentYear" cssClass="inputBox" onkeyup="checkNumbers(this);" maxlength="4"/>
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
														<s:textfield name="regNo" id="regNo" cssClass="inputBox"  />
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.cinNo" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield name="cinNo" id="cinNo" cssClass="inputBox"  />
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.rate" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield  name="rating" id="rating" cssClass="inputBox"  />
													</div>
											</div>
											<div class="textfield">
													<div class="text">
														<s:text name="label.rateagency" />&nbsp;
													</div>
													<div class="tbox">
														<s:textfield  name="ratingAgency" id="ratingAgency" cssClass="inputBox"  />
													</div>
											</div>
											
											<div class="textfield">
													<div class="text">
														<s:text name="label.lastrate" />&nbsp;
													</div>
													<div class="tbox">
													<div class="inputAppend">
													<s:textfield name="lastRating" id="lastRating"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  />
													</div>
													</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
												<s:text name="label.remarksdetails" />
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfieldA">
													<div class="text">
														<s:text name="country.Remarks" />&nbsp;
													</div>
													<div class="tbox">
														<s:textarea rows="1" name="clientRemarks" cssClass="inputBoxA" cssStyle="width: 200%;" />
													</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<s:if test='"Edit".equals(mode) || "update".equals(mode)'>
									<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="Back()" /> &nbsp;&nbsp;&nbsp;
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues('update','<s:property value="customerId"/>')"  />
								</s:if>
								<s:else>
									<input type="button"  value="Back"   class="btn btn-sm btn-danger" onClick="Back()" /> &nbsp;&nbsp;&nbsp;
									<input type="button" value="Submit" class="btn btn-sm btn-success" onClick="submitValues('insert','')"  />
								</s:else>
							</div>
						</div>
		</s:if>
        <s:if test='"List".equals(path)'>
          <div class="tablerow">							
			<div class="boxcontent">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="Client List" />
						<input type="button" value="Add New" class="btn btn-sm btn-success pull-right" onClick="Addnew()" />
					</div>
					<div class="panel-body">
						<table class="display responsive no-wrap" id="gridTableMake" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle;" class="no-sort"><s:text name="S.No"/></th>
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.tdstype" /></th>	
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.name" /></th>
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.expdate" /></th>
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.phone" /></th>
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.Edit" /></th>
									<th style="text-align: center; vertical-align: middle;"><s:text name="label.document" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="clientPersonalList" var="list" status="stat">
									<tr>
										<td style="text-align: center; vertical-align: middle;" width="2%"><s:property value="#stat.count" /></td>
										<td><s:property value="DETAIL_NAME" /></td>
										<td><s:property value="FIRST_NAME" /></td>
										<td><s:property value="EFFECTIVE_DATE" /></td>
										<td><s:property value="MOBILE" /></td>
										<td style="text-align: center; vertical-align: middle;">
										<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="funEditMode(${CUSTOMER_ID})" alt="Edit Contract " width="12" height="17">
										</td  ><td style="text-align: center; vertical-align: middle;">
										<img border='0' src="<%=request.getContextPath()%>/images/icon_view_schedule.gif" onClick="documentMode('${CUSTOMER_ID}','${FIRST_NAME}')" width="12" height="17">
									</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<s:hidden name="mode" id="mode"/>
					</div>
				</div>
			</div>
		</div>
	</s:if>
</div>
</div>
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
	if(val=='L'){
		document.getElementById('LN').style.display='inline';
		document.getElementById('BN').style.display='none';
		document.getElementById('cc').style.display='none';
		document.getElementById('BG').style.display='none';
		document.client.broGroup.value ='';
		document.client.brokerGroup.checked = false;
	}
}

function Addnew(){
	document.client.action='${pageContext.request.contextPath}/clientMaster1Admin.action?mode=new';
	document.client.submit();
}
function submitValues(mode,id){
    var type= document.getElementById("clientType").value;
	if(type=='B'){
        if(document.getElementById("brokerGroup").checked == true) {
			var val = document.client.broGroup.value;
			if(val == ''){
			  alert("Please Select Broker ");
			  }
			  else{
                  document.client.action='${pageContext.request.contextPath}/insertClientMasterAdmin.action?mode='+mode+'&customerId='+id;
                  document.client.submit();
              }
			}
			else{
            document.client.broGroup.value ='';
            document.client.action='${pageContext.request.contextPath}/insertClientMasterAdmin.action?mode='+mode+'&customerId='+id;
            document.client.submit();
        }
        }
	else{
        document.client.broGroup.value ='';
        document.client.action='${pageContext.request.contextPath}/insertClientMasterAdmin.action?mode='+mode+'&customerId='+id;
        document.client.submit();
    }

}
function funEditMode(val){
	document.client.mode.value ="Edit";
   	document.client.action='${pageContext.request.contextPath}/insertClientMasterAdmin.action?customerId='+val;
	document.client.submit();	       
}
function Back(){
	document.client.action='${pageContext.request.contextPath}/clientMasterAdmin.action?';
	document.client.submit();
}

function checkboxChange(){
	if(document.getElementById("brokerGroup").checked == true){
		document.getElementById('broGroup').style.display='inline';
	}else{
		document.getElementById('broGroup').style.display='none';
	}
}

function documentMode(id,name){
	document.client.action="${pageContext.request.contextPath}/docUploadAdmin.action?flag=client&customerId="+id+"&firstName="+name;
	document.client.submit();
      } 


checkbroGroup();
function checkbroGroup(){
    var val = document.client.broGroup.value;
    if(val != null && val !='') {
        document.getElementById('brokerGroup').checked = true;
        checkboxChange();
    }
}

function checkCurrency(id,count){
	var val = document.getElementById("bankCurrency"+count).value;
	var co = parseInt(count)+1;
	if(val == ''){
		alert("Please choose currency in row number "+co);
		document.getElementById(id).value ="";
	}
}
</script>
</body>
</html>