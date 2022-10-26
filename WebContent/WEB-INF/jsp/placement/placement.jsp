<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style type="text/css">
		.btn-group {
		width: 100%;
		}
		.btn-group .btn {
		width: 90%;
		padding: 3px 12px;
		}
	</style>
<script type="text/javascript">
	 $(function() {
		$( "#transaction" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#amendmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#inception_Date" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		$( "#statementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });	
	  </script>		
</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
			<s:set var="ebouquetExistingList" value='%{bouquetExistingList}'/>
				<div style="padding:10px; background:#F8F8F8">
				<s:form id="placement" name="placement" theme="simple" action=""	method="post" autocomplete="off">					
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">
								<div class="panel-heading" align="center">
										Placement Process
									</div>
									<div class="panel-body">
										<div class="boxcontent" >
											
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
																		<th width="7%"> <s:text name="label.sno" /> </th>
																		<th width="20%"><s:text name="label.businessType" /></th>
																		<th width="10%"><s:text name="label.proposalNo" /></th>
																		<th width="10%"><s:text name="label.inceptionDate" /></th>
																		<th width="10%"><s:text name="label.expiryDate" /></th>
																		<th width="5%"><s:text name="label.underwritingYear" /></th>
																		<th width="5%"><s:text name="label.uwYearto" /></th>
																		<th width="10%"><s:text name="label.treatyType" /></th>
																		<th width="20%"><s:text name="label.treatyNameType" /></th>
																		<th width="10%"><s:text name="label.newRenew" /></th>
																		<th width="5%"><s:text name="label.existingshare" /></th>
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
																			<s:property value="#list.PROPOSAL_NO"/>
																		</td>
																		<td>
																			<s:property value="#list.INS_DATE"/>
																		</td>
																		<td>
																			<s:property value="#list.EXP_DATE"/>
																		</td>
																		<td>
																			<s:property value="#list.UW_YEAR"/>
																		</td>
																		<td>
																			<s:property value="#list.UW_YEAR_TO"/>
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
																		<td>
																			<s:property value="#list.EXISTING_SHARE"/>
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
									</s:if>
									<s:else>
										<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.offerinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														  <div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.cedingCompany" />
																</div>
																<div class="tbox">
																	<s:property value="cedingCompany"/>
																</div>
															</div>
															<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.treatyNameType" />
																</div>
																<div class="tbox">
																	<s:property value="treatyName"/>
																</div>
															</div>	
															<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.inceptionDate" />
																</div>
																<div class="tbox">
																	<s:property value="inceptionDate"/>
																	<s:hidden name="inceptionDate" />
																</div>
															</div>
															<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.expiryDate" />
																</div>
																<div class="tbox">
																	<s:property value="expiryDate"/>
																	<s:hidden name="expiryDate" />
																</div>
															</div>
															<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.underwritingYear" />
																</div>
																<div class="tbox">
																	<s:property value="uwYear"/>
																</div>
															</div>	
															<div class="textfield" style="display:table;">
																<div class="text txtB">
																	<s:text name="label.uwYearto" />
																</div>
																<div class="tbox">
																	<s:property value="uwYear"/>
																</div>
															</div>	
															<br class="clear"/>
													</div>
												</div>
										</div>
									</s:else>
									<s:if test='"placing".equals(mode)'>
									<div class="boxcontent" >
									<div class="panel panel-primary">											
										<div class="panel-heading">
											<s:text name="label.Reinsureinfo" />
										</div>
										<div class="panel-body">
											<div class="boxcontent">
													<div class="textfield">
														<div class="text txtB">
															<s:text name="label.placementMode" />
														</div>
														<div class="tbox">
															<s:radio list="#{'1':'Separate','2':'Combined / Bouquet'}" name="placementMode"  id="placementMode" value="placementMode==null || placementMode==''?'1':placementMode" disabled='%{!"Y".equals(bouquetModeYN)?true:false}'/>												
														</div>
													</div>
													<div id="reinsurerid">
														<table width="100%" class="table table-bordered" id="reinsTbl">
															<thead>
															<tr>
																<th width="7%"> <s:text name="label.sno" /> </th>
																<th width="20%"><s:text name="label.reinsureName" /></th>
																<th width="20%"><s:text name="label.placingBroker" /></th>
																<th width="20%"><s:text name="label.shareOffer" /></th>
																<th width="15%" > <s:text name="Delete Row" /> </th>
															</tr>
															</thead>
															<tbody>	
															<s:iterator value="reinsurerInfoList" var="list"  status="stat">									
															<tr>
																<td>
																	<s:textfield name="reinsSNo[%{#stat.count-1}]" id="reinsSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/>
																</td>
																<td>
																	<s:select list="reinsurerList" listKey="CUSTOMER_ID" listValue="NAME" name="reinsureName[%{#stat.count-1}]" id="reinsureName[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  />
																</td>
																<td>
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" name="placingBroker[%{#stat.count-1}]" id="placingBroker[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  />
																</td>
																<td>
																	<s:textfield name="shareOffer[%{#stat.count-1}]" id="shareOffer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"    onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" />
																</td>
																<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
																	</s:if>
																</td>
															</tr>												
															</s:iterator>
															</tbody>
														</table>
														</div>
														<div class="boxcontent" align="center">
															<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="reinsureRow('reinsTbl');" />
														</div>											
													</div> 
												
												
											</div>
										</div>
									</div>
									</s:if>
									<s:if test='"mail".equals(mode)'>
									<div class="boxcontent" >
									<div class="panel panel-primary">											
										<div class="panel-heading">
											<s:text name="label.Reinsureinfo" />
										</div>
										<div class="panel-body">
											<div class="boxcontent">
													<div id="reinsurerid">
														<table width="100%" class="table table-bordered" id="reinsTbl">
															<thead>
															<tr>
																<th width="7%"> <s:text name="label.sno" /> </th>
																<th width="20%"><s:text name="label.reinsureName" /></th>
																<th width="20%"><s:text name="label.placingBroker" /></th>
																<th width="20%"><s:text name="label.shareOffer" /></th>
																<th width="15%" > <s:text name="Mail Status" /> </th>
																<th width="15%" > <s:text name="Action" /> </th>
															</tr>
															</thead>
															<tbody>	
															<s:iterator value="reinsurerInfoList" var="list"  status="stat">									
															<tr>
																<td>
																	<s:textfield name="reinsSNo[%{#stat.count-1}]" id="reinsSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/>
																</td>
																<td>
																	<s:select list="reinsurerList" listKey="CUSTOMER_ID" listValue="NAME" name="reinsureName[%{#stat.count-1}]" id="reinsureName[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  />
																</td>
																<td>
																	<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" name="placingBroker[%{#stat.count-1}]" id="placingBroker[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  />
																</td>
																<td>
																	<s:property value="shareOffer[#stat.count-1]"/>
																</td>
																<td>
																<s:property value="mailStatus[#stat.count-1]"/>
																</td>
																<td align="center">
																	<input type="button" value="Send Mail" class="btn btn-sm btn-primary"   data-toggle="modal" data-target="#companyModal" />
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
									<div>
									<div id="companyModal" class="modal fade" role="dialog">
										  <div class="modal-dialog modal-lg">
										    <!-- Modal content-->
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										      </div>
										      <div class="modal-body" >
										        <div class="container-fluid" id="companyAjaxId2">
										        	<s:include value="mailTemplate.jsp"></s:include>
										        </div>
										      </div>
										    </div>
										  </div>
									</div>	
									
									</div>
									
									</s:if>
									</div>
									<br class="clear"/>									
									
									
								</div>
							</div>
							
								</div>
							
													
						</div>						
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							
							
								<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onclick="FnCancel()" />
								<input type="button"  value="Next"  class="btn btn-sm btn-success"  onclick="FnNext()" />	
								<input type="button"  value="Submit"  class="btn btn-sm btn-warning"  onclick="FnSumbit()" />											
							</div>
						</div>	
						
						<s:hidden name="size" />
						<s:hidden name="proposalNo" id="proposalNo"></s:hidden>
						
					</div>	
					<div id="premiumSubmit">
					</div>									
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('.select1').select2({ });
function reinsureRow(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "reinsSNo["+(rowCount-1)+"]";
       		element1.id = "reinsSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1)
			createreinsurerCell(cell2, rowCount)
       		
			var cell3 = row.insertCell(2)
			createbrokerCell(cell3, rowCount)
		
			var cell4 = row.insertCell(3);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "shareOffer["+(rowCount-1)+"]";
      		element2.id = "shareOffer["+(rowCount-1)+"]";
			element2.className = "inputBox";
			cell4.appendChild(element2);
			
			var cell5 = row.insertCell(4);
			cell5.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');deleteRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-danger"
			cell5.appendChild(element6);
			$('.select1').select2({ });	 
}
function createreinsurerCell(cell, rowCount){
	element = document.createElement("select");
    element.name = "reinsureName["+(rowCount-1)+"]";
    element.id = "reinsureName["+(rowCount-1)+"]";
    element.className = "select1 inputBoxS";
    populateReinsurer(element);
    cell.appendChild(element);
}
function populateReinsurer(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='reinsurerList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='NAME' />".replace("&amp;", "&") ;
				objOption.value = "<s:property value='CUSTOMER_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
function createbrokerCell(cell, rowCount){
	element = document.createElement("select");
    element.name = "placingBroker["+(rowCount-1)+"]";
    element.id = "placingBroker["+(rowCount-1)+"]";
    element.className = "select1 inputBoxS";
    populateBroker(element);
    cell.appendChild(element);
}
function populateBroker(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='brokerList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='NAME' />".replace("&amp;", "&") ;
				objOption.value = "<s:property value='CUSTOMER_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
function deleteRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowPlacement.action?mode=delete&deleteId="+val+"&dropDown=reinsurerid", "reinsurerid", "placement");
			}
		}
}
function FnCancel(){
	document.placement.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.placement.submit();
}
function FnNext(){
	document.placement.action='${pageContext.request.contextPath}/savePlacingPlacement.action'
	document.placement.submit();
}
function FnSumbit(){
	document.placement.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.placement.submit();
}
</script>		
</body>
</html>
