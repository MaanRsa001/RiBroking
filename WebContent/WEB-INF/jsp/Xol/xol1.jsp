<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
	</script >
	
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
		$( ".instalmentDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });
	
	  </script>

</head>
<body onload="Commas(<s:property value="#session.mfrid" />),setCedRetType('<s:property value="cedRetenType" />')">
<s:set var="elayerInfo" value='%{layerInfo}'/>
<s:set var="ebouquetExistingList" value='%{bouquetExistingList}'/>
<s:set var="dislayer" value="%{baseLayer!=null && !''.equals(baseLayer)}"/>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="xol1" id="xol1" theme="simple" action="" method="post" autocomplete="off">
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
												<div class="textfield33" >
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
						<div id="bouquestid" style="display:none">
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
													<s:text name="label.contractno" />
												</div>
												<div class="tbox">
													<s:textfield name="contNo" cssClass="inputBox" disabled="true" />
												</div>
											</div>
											
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
													<div class="input-group"> 
													<s:if test="RenewalMode != null">																												
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />															
														 <span class="input-group-addon">
														 	<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" onclick="functionview(1)">
											 			     	<span class="glyphicon glyphicon-list"></span>
											 			    </button>
					     								</span>
													</s:if>
													<s:else>
														<s:if test="'Layer'.equals(proposalReference)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
															 <span class="input-group-addon">
                            									<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" onclick="functionview(1)">
												 			     	<span class="glyphicon glyphicon-list"></span>
												 			    </button>
					     									 </span>
														</s:if>
														<s:elseif test="baseLayer==null || ''.equals(baseLayer)">
															<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled='%{(#dislayer) || ("Y".equals(disableStatus1))?true:false}'  />
															 <span class="input-group-addon">
                            									<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" onclick="functionview(1)">
												 			     	<span class="glyphicon glyphicon-list"></span>
												 			    </button>
					     									 </span>
														</s:elseif>
														<s:else>
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
															 <span class="input-group-addon">
                            									<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" onclick="functionview(1)">
												 			     	<span class="glyphicon glyphicon-list"></span>
												 			    </button>
					     									 </span>
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
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) || (#dislayer) ?true:false}' />
															</div>
														</s:if>
														<s:else>
															<div class="">
															<s:textfield name="incepDate" id="incepDate" cssClass="inputBox"  onkeyup="validateSpecialChars(this)" disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) || (#dislayer) ?true:false}' />
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
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) || (#dislayer) ?true:false}' />
																</div>																
															</s:else>
														</s:if>
														<s:else>
															<div class="">
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox" onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()"  disabled='%{(contNo != "" && contNo != null) ||proposalReference!=null  && !"".equalsIgnoreCase(proposalReference) || (#dislayer) ?true:false}' />
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
														<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  disabled='%{"Layer".equals(proposalReference)|| "Y".equals(disableStatus) || (#dislayer) ?true:false}' onchange="functionEDate();"/>
														</div>														
													</s:if>
													<s:else>
													<div class="">
													<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  disabled='%{"Layer".equals(proposalReference)|| "Y".equals(disableStatus) || (#dislayer) ?true:false}' onchange="functionEDate();"/>
													 </div>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYearFrom" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="yearId">
													<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled='%{"Layer".equals(proposalReference) || (#dislayer) ||(contNo != "" && contNo != null)?true:false}' />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.uwYearto" /> &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="yearIdto">
													<s:select list="yearToList" listKey="YEAR" listValue="YEAR" name="uwYearTo" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled='%{"Layer".equals(proposalReference) || (#dislayer) ||(contNo != "" && contNo != null)?true:false}' />
												</div>
											</div>
											
											<br class="clear"></br>
										</div>
									</div>
								</div>
							</div>
							<s:if test='!"layer".equals(layerMode) && (#elayerInfo!=null && #elayerInfo.size()>0)'>
								<div class="boxcontent" id="laydet">
									<div class="panel panel-primary">											
										<div class="panel-heading">
											<s:text name="label.layerinfo" />
										</div>
										<div class="panel-body">
											<div class="boxcontent" style="width:75%">
											
												<s:if test="#elayerInfo!=null && #elayerInfo.size()>0">
													<div>
														<table width="75%" class="table table-bordered" >
															<thead>
															<tr>
															
																<th width="10%"><s:text name="label.proposalNo" /></th>
																<th width="10%"><s:text name="label.layerNo" /></th>
																<th width="15%"><s:text name="label.departmentClass" /></th>
																<th width="15%"><s:text name="label.treatyType" /></th>
																<th width="15%"><s:text name="label.treatyNameType" /></th>
																<th width="10%"><s:text name="label.edit" /></th>
																<th width="10%"><s:text name="label.copy" /></th>
																<th width="10%" > <s:text name="Delete" /> </th>
															</tr>
															</thead>
															<tbody>	
															<s:iterator value="#elayerInfo" var="list"  status="stat">									
															<tr>
																<td>
																	<s:property value="#list.PROPOSAL_NO"/>
																</td>
																<td>
																	<s:property value="#list.LAYER_NO"/>
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
																<td align="center">
																	<button type="button"  class="btn btn-sm btn-primary"  onclick="funEditMode('<s:property value='#list.PROPOSAL_NO'/>','<s:property value='#list.CEDING_COMPANY_ID'/>','<s:property value='#list.PRODUCT_ID'/>','<s:property value='#list.BASE_LAYER'/>','<s:property value='#list.CONTRACT_NO'/>','<s:property value='#list.DEPT_ID'/>');" tabindex="1"> Edit </button>
																</td>
																<td align="center">
																	<button type="button"  class="btn btn-sm btn-warning"  onclick="funCopyMode('<s:property value='#list.PROPOSAL_NO'/>','<s:property value='#list.CEDING_COMPANY_ID'/>','<s:property value='#list.PRODUCT_ID'/>','<s:property value='#list.BASE_LAYER'/>','<s:property value='#list.CONTRACT_NO'/>','<s:property value='#list.DEPT_ID'/>');" tabindex="1"> Copy </button>
																</td>
																<td align="center">
																	<s:if test='(#list.PROPOSAL_NO!=#list.BASE_LAYER)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');funDeleteLayer('<s:property value='#list.PROPOSAL_NO'/>')" theme="simple"/>
																	</s:if>
																</td>
															</tr>												
															</s:iterator>
															</tbody>
														</table>											
													</div> 
												</s:if>
												
											</div>
										</div>
									</div>
									</div>
								</s:if>
							<div id="sectionid" >
							<div class="boxcontent">
								<div class="panel panel-primary subpannel">											
									<div class="panel-heading">
										<s:text name="label.layerdet" />
									</div>
									<div class="panel-body">
									
										<div class="boxcontent">
											<s:if test="#elayerInfo!=null && #elayerInfo.size()>0">
											<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<s:iterator value="#elayerInfo" var="list"  status="stat">
													<button type="button"  class="btn btn-sm btn-info"  onclick="funViewMode('<s:property value='#list.PROPOSAL_NO'/>','<s:property value='#list.CEDING_COMPANY_ID'/>','<s:property value='#list.PRODUCT_ID'/>','<s:property value='#list.BASE_LAYER'/>','<s:property value='#list.CONTRACT_NO'/>','<s:property value='#list.DEPT_ID'/>');" tabindex="1">Layer  <s:property value="#list.LAYER_NO"/> </button>
												</s:iterator>
											</div>
											</div>
											</s:if>
													<div class="textfield">
															<div class="text">
																<s:text name="label.layerNo" />
															</div>
															<div class="tbox">
															<s:if test='"Renewal".equals(proposalReference) || "Layer".equals(proposalReference)'>
															<%--<s:textfield name="layerNo" cssClass="inputBox" cssStyle="text-align: right;" readonly="%{(proposal_no!='' && proposal_no!=null)?true:false}" disabled='%{("Y".equals(disableStatus1))?true:false}' onkeyup="checkNumbers(this);"/>--%>
																<s:textfield name="layerNo" id="layerNo" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);hundredCheck(this.id,this.value);"/>
															</s:if>
															<s:else>
																<s:textfield name="layerNo" id="layerNo" cssClass="inputBox" cssStyle="text-align: right;"    onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);hundredCheck(this.id,this.value);"/>
															</s:else>
															
															</div>
														</div>
											
											<br class="clear"></br>
										</div>
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading ">
													<s:text name="label.riskdetails" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
													<div class="textfield">
															<div class="text">
																<s:text name="label.insdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="riskdetailYN" id="riskdetailYN" value="%{riskdetailYN==null?'N':riskdetailYN}" onchange="getRiskInfo(this.value)"></s:radio>													
															</div>
														</div>
														<div class="boxcontent" id="riskid">
														<div class="textfield">
															<div class="text">
																<s:text name="label.originalCurrency" />
															</div>
															<div class="tbox">
																<s:select list="orginalCurrencylist" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" name="orginalCurrency" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" onchange="GetExchangeRate()" disabled='%{"Y".equals(disableStatus1)?true:false}'  />
															</div>
															<span  id="exRate">
																<s:hidden name="exchRate" id="exchRate"/>
															</span>
															
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.treatyNameType" />
															</div>
															<div class="tbox">
																<s:textfield name="treatyName_type" cssClass="inputBox" maxlength="500"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.businessType" /> 
															</div>
															<div class="tbox" >
															<div id="typeBus">
															<s:if test="RenewalMode != null">	
																<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" onchange="GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();"  disabled='%{(baseLayer==null  || "".equalsIgnoreCase(baseLayer))?true:false}' />
															</s:if>
															<s:else>
																<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" onchange="GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</s:else>
															</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.departmentClass" />
															</div>
															<div class="tbox">
																<s:if test="RenewalMode != null ">
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="true" />
																</s:if>
																<s:else>
																	<s:if test="'Layer'.equals(proposalReference)">
																		<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId"  cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  onchange="getAjaxCoverClass();getAjax(this.value,'subclass');"/>
																	</s:if>
																	<s:else>
																		<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="departId" id="departId"  cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" onchange="getAjaxCoverClass();getAjax(this.value,'subclass');" /><!-- getTypeOfBusiness(this.value,'typeBus');getBasis(this.value,'rdsBasis');getAjaxCoverClass();getAjax(this.value,'subclass') -->
																	</s:else>
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.subClass" />
															</div>
															<div class="tbox"  id="subclass">
																<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" multiple="true" name="subProfit_center" id="subProfit_center"  cssClass="inputBoxS" disabled='%{(contNo != "" && contNo != null)?true:false}' headerKey="ALL" headerValue="ALL" />
																
																<s:hidden name="profit_Center" id="profit_Center" value="D"/>
																<%--<s:select list="subProfitList" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" id="subProfit_center" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  disabled='%{contNo != null && contNo != ""?true:RenewalMode!=null?false:"Y".equals(disableStatus)?true:false}'/>
																 <s:hidden name="subProfit_center" id="subProfit_center" value="D"/>--%>											
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																	<s:text name="label.basis" />
															</div>
															<div class="tbox">
																<div id="rdsBasis">
																	<s:select list="Basislist" listValue="DETAIL_NAME" listKey="TYPE" name="basis" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled='%{(contNo != "" && contNo != null)?true:false}'/>													
																</div>
															</div>
														</div>
														<br class="clear"></br>
														<div id="stoplossno" style="display:block;">
															<div id="newgenid">
																<table class="table table-bordered" width="100%" id="newgen">
																	<thead>
																		<tr>
																			<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																			<!--<th width="15.8%"> <s:text name="label.subClass" />  </th>
																			--><th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitPoc" />  </th>
																			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePoc" /> </th>
																			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Net Max Retention - 100% - OC" /> </th>
																			<th width="10.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
																		</tr>
																	</thead>
																	<tbody>
																		<s:iterator value="CoverList" var="list" status="stat">
																			<tr>
																				<td>
															 					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true"/> 
															 					</td>
																				<td >
																				<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  /><%--disabled="0==(#stat.count-1)?true:false" --%>
																				<%-- <s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})"/>--%>
																				<s:if test='0==(#stat.count-1)'>
																				
																				</s:if>
																				</td>
																				<%--<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
																				<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="select1 inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"  />
																				<s:if test='0==(#stat.count-1)'>
																				<s:text name="(Main Class)" />
																				</s:if>
																				</td>
																				 --%><td>
																				<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);"  maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  />
																				
																				</td>
																				<td>
																				<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																				</td>
																				<td>
																				<s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30"  />
																				</td>
																				<td>
																				<s:textfield name="netMaxRetentPer[%{#stat.count-1}]" id="netMaxRetentPer%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30"  />
																				</td>
																				<td align="center">
																				<s:if test='0!=(#stat.count-1)'>
																				<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" />
																				</s:if>
																				</td>
																				<s:hidden name="loopcount" id="loopcount" ></s:hidden>
																			</tr>
																		</s:iterator>
																	</tbody>
																</table>
																<s:if test=' !"Y".equals(disableStatus1)'>
																<div class="boxcontent" align="center">
																		<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
																</div>
																</s:if>
															</div>
															<div class="textfield" id="coverLimitID" style="display: none;">
																<div class="text">
																	<s:text name="label.coverLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="coverLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" maxlength="26" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</div>
															</div>
															<div class="textfield" id="deductLimitID" style="display: none;">
																<div class="text">
																	<s:text name="label.deductLimit" />
																</div>
																<div class="tbox">
																	<s:textfield name="deductLimitXL" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" maxlength="26" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</div>
															</div>
														</div>
														<div id="stoploss" style="display:none;">
															<div id="newgenid1">
																<table class="table table-bordered" width="100%" id="newgen1">
																	<thead>
																		<tr>
																			<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitAm" />  </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitper" />  </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleAm" /> </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductiblePer" /> </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.egnpiasperoffer" /> </th>
																			<th width="13%" style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
																			<th width="7%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
																		</tr>
																		<tr>
																			<th></th>
																			<th></th>
																			<th colspan="2">(Whichever is lower)</th>
																			<th colspan="2">(Whichever is higher)</th>
																			<th></th>	
																			<th ></th>
																			<th></th>
																		</tr>
																	</thead>
																	<tbody>
																	<s:iterator value="CoverList" var="list" status="stat">
																		<tr>
																		
																				<td>
																					<s:textfield name="coverSNoS[%{#stat.count-1}]" id="coverSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
																				</td>
																				<td >
																				<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" theme="simple" disabled="0==(#stat.count-1) && ('17'!=departId||'18'!=departId ||'19'!=departId)?true:false"/>
																				<%--<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" theme="simple" />--%>
																				<s:if test='0==(#stat.count-1)'>
																				<s:text name="(Main Class)" />
																				</s:if>
																				</td>
																				<td>
																				<s:textfield name="coverLimitAmount[%{#stat.count-1}]" id="coverLimitAmount[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																				</td>
																				<td>
																				<s:textfield name="coverLimitPercent[%{#stat.count-1}]" id="coverLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																				</td>
																				<td>
																				<s:textfield name="deductableLimitAmount[%{#stat.count-1}]" id="deductableLimitAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																				</td>
																				<td>
																				<s:textfield name="deductableLimitPercent[%{#stat.count-1}]" id="deductableLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);" maxlength="10" theme="simple" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																				</td>
																				<td>
																				<s:textfield name="egnpiAsPerOffSlide[%{#stat.count-1}]" id="egnpiAsPerOffSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" />
																				</td>
																				<td ><%--id="gnpislide[<s:property value="%{#stat.count-1}" />]" style="display:none" --%>
																				<s:textfield name="gnpiAsPOSlide[%{#stat.count-1}]" id="gnpiAsPOSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen1');" maxlength="30"   />
																				</td>
																				<td align="center">
																				<s:if test='0!=(#stat.count-1)'>
																				<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRowS('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
																				</s:if>
																				</td>
																			</tr>
																			</s:iterator>
																			<s:hidden name="count" id="count" ></s:hidden>
																		</tbody>
																		</table>
																		<br></br>
																		<s:if test=' !"Y".equals(disableStatus1)'>
																			<div class="boxcontent" align="center">
																				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');"  />
																			</div>
																	</s:if>
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
													<s:text name="label.brokerinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.insdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="brokerdetYN" id="brokerdetYN" value="%{brokerdetYN==null?'N':brokerdetYN}" onchange="getBrokerInfo(this.value)"></s:radio>													
															</div>
														</div>
														<div class="boxcontent" id="brokerid">													
														<div class="textfield">
															<div class="text">
																<s:text name="label.broker" />
															</div>
															<div class="tbox">
																<div class="input-group">
																	<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="broker" id="BrokerId" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" disabled='%{baseLayer==null  && "".equalsIgnoreCase(baseLayer) || "Y".equals(disableStatus1)?true:false}'  onchange="getPaypartner(this.value,'paypartid');"/>
																	<span class="input-group-addon">
																		<button type="button" name="companyBtn" id="companyBtn" data-toggle="modal" data-target="#companyModal" onclick="functionview(2)">
														 			     	<span class="glyphicon glyphicon-list"></span>
														 			    </button>
								     								</span>
																</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.paymentpartner" />
															</div>
															<div class="tbox" id="paypartid">
																<s:select list="paymentPartnerlist" listKey="CUSTOMER_ID" listValue="NAME" name="paymentPartner" id="paymentPartner" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.leadUnderWriter" /> 
															</div>
															<div class="tbox">
																	<s:select list="underwriterList" listKey="CUSTOMER_ID" cssClass="select1 inputBoxS"  listValue="NAME" name="leader_Underwriter" id="leader_Underwriter"   headerKey="" headerValue="---Select---"   /><!-- onchange="getUnderwriterShare(this.value);" -->
															</div>
														</div>
														<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
														<div class="textfield" >
															<div class="text">
																<s:text name="label.leadUnderwritterCountry" />
															</div>
															<div class="tbox" id="country">
																	<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  />													
															</div>
														</div>
														</s:if>
														<s:else>
														<div class="tbox" id="country">
															<s:hidden name="leader_Underwriter_country" id="leader_Underwriter_country" />
														</div>
														</s:else>
														<div class="textfield">
															<div class="text">
																<s:text name="label.leadUnderwritterShareP" /> 
															</div>
															<div class="tbox">
																<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);" maxlength="8" disabled="false"/>
															</div>
														</div>
														<br class="clear"></br>
													</div>
												</div>
											</div>
										</div>	
										</div>								
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.premiuminfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.insdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="premiumdetailYN" id="premiumdetailYN" value="%{premiumdetailYN==null?'N':premiumdetailYN}" onchange="getPremiumInfo(this.value)"></s:radio>													
															</div>
														</div>
													<div class="boxcontent" id="premiumid">
														<div class="textfield">
															<div class="text">
																<s:text name="label.subjectPremium" />
															</div>
															<div class="tbox">
																<s:textfield name="subPremium" id="subPremium" cssClass="inputBox"  cssStyle="text-align: right;"  onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);CalculateEpi();CalculateminimumrateEpi();" maxlength="26"/>													
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.premiumbasis" />
															</div>
															<div class="tbox">
																<s:select list="premiumBasicList" listValue="DETAIL_NAME" listKey="TYPE" name="premiumbasis" cssClass="select1 inputBoxS" headerKey="" headerValue="---None---" onchange="getPremiumBasis();CalculateEpi();CalculateminimumrateEpi();" />
															</div>
														</div>
														<div class="textfield" id="rate" style="display:none">
															<div class="text">
																<s:text name="label.premiumAdjustmentRateP" />
															</div>
															<div class="tbox">
																<s:textfield name="adjRate" id="adjRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateEpi()" onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);" maxlength="26"/>													
															</div>
														</div>
														<div class="textfield" id="rate1" style="display:none">
															<div class="text">
																<s:text name="label.minimumRate" />
															</div>
															<div class="tbox">
																<s:textfield name="minimumRate" id="minimumRate" cssClass="inputBox" cssStyle="text-align: right;" onblur="CalculateminimumrateEpi();CalculateEpi()"   onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
															</div>
														</div>
														<div class="textfield" id="rate2" style="display:none">
															<div class="text">
																<s:text name="label.maximumRate" />
															</div>
															<div class="tbox">
																<s:textfield name="maximumRate" id="maximumRate" cssClass="inputBox" cssStyle="text-align: right;"   onkeyup="checkDecimals(this); middleMinusRestrictionNeg(this);negative(this.id,this.value)" maxlength="26"/>													
															</div>
														</div>
														<div class="textfield" id="rate3" style="display:none">
															<div class="text">
																<s:text name="label.burningCostLF" />
															</div>
															<div class="tbox" >
																<div class="input-group">
																  <span class="input-group-addon" id="basic-addon1">100/</span>
																  <s:textfield name="burningCostLF" id="burningCostLF" cssClass="form-control inputBox" cssStyle="text-align: right; width: 88%;" onkeyup="negative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);allowOneDot(this);hundredCheck(this.id,this.value)" onblur="CalculateminimumrateEpi();CalculateEpi()"  maxlength="20"  />
																</div>																										
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.epiPoc1" />
															</div>
															<div class="tbox">
																<s:textfield name="epi" id="epi" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);getRateOnline(this.value);"/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.minPremium" />
															</div>
															<div class="tbox">
																<s:textfield name="minPremium" id="minPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);mdpremiumval();"  />													
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.MandDPremiumP" />
															</div>
															<div class="tbox">
																<s:textfield name="m_dPremium" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="allow2DigitDecValues(this); middleMinusRestrictionNeg(this);jajvascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>													
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.rateonline" />
															</div>
															<div class="tbox">
																<s:textfield name="rateOnLine" id="rateOnLine" cssClass="inputBox"  cssStyle="text-align: right;"   maxlength="100"/>													
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.MandDInstallments" />
															</div>
															<div class="tbox">
																<s:textfield name="m_d_InstalmentNumber" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" maxlength="3"/>
															</div>
														</div>
												</div>
											</div>
										</div>
										</div>
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.intallmentinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.insdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="installYN" id="installYN" value="%{installYN==null?'N':installYN}" onchange="getInstallInfo(this.value)"></s:radio>													
															</div>
														</div>
														<div class="boxcontent" id="insid">
														<table width="100%" class="table table-bordered" id="installid">
															<thead>
															<tr>
																<th width="7%"> <s:text name="label.installmentNo" /> </th>
																<th width="31.66%"> <s:text name="label.installmentDate" /> </th>
																<th width="31.66%"> <s:text name="RiskDetails.M&DPremium" /> </th>
																<th width="31.66%"> <s:text name="label.paymentDueDays" /> </th>
																<th width="15%" > <s:text name="Delete Row" /> </th>
															</tr>
															</thead>
															<tbody>	
															<s:iterator value="instalList" var="retroContract" status="stat">										
															<tr>
																<td>
																<s:textfield name="installsno[%{#stat.count-1}]" id="installsno[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/>
																  </td>
																<td>
																	<s:textfield name="instalmentDateList[%{#stat.count-1}]" id="instalmentDateList[%{#stat.count-1}]"  cssClass="inputBox datepicker instalmentDate"   onkeyup="validateSpecialChars(this);" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/>
																</td>
																<td>
																	<s:textfield name="installmentPremium[%{#stat.count-1}]" id="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);this.value=Comma(this.value);allow2DigitDecValues(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/>
																</td> 
																<td>
																	<s:textfield name="paymentDueDays[%{#stat.count-1}]" id="[%{#stat.count-1}]" cssClass="inputBox"   cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}'/> 
																</td> 
																<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');removeInst('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
																	</s:if>
																</td>
															</tr>												
															</s:iterator>
															</tbody>
														</table>											
														<div class="boxcontent" align="center">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="installRow('installid');" />
														</div>
													</div> 
													</div>
												</div>
											</div>
										</div>
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.acqinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.acqdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="acqdetailYN" id="acqdetailYN" value="%{acqdetailYN==null?'N':acqdetailYN}" onchange="getAcqInfo(this.value);"></s:radio>													
															</div>
														</div>
														<div class="boxcontent" id="aquid">
														<div class="textfield">
															<div class="text">
																<s:text name="label.brokerageP" /> 
															</div>
															<div class="tbox">
																<s:if test="(broker.toString().trim()).equalsIgnoreCase('DIRECT')">
																	<s:textfield name="brokerage" id="BrokeragerValue" cssClass="inputBox" readonly="true" value="0" cssStyle="text-align:right;" onkeyup="negative(this.id,this.value);middleMinusRestrictionNeg(this);checkDecimals10(this);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()" maxlength="14"/>
																</s:if>
																<s:else>
																	<s:textfield name="brokerage" id="BrokeragerValue" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals10(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()"  maxlength="14" disabled='%{"Y".equals(disableStatus1)?true:false}' />
																</s:else>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.taxP" /> 
															</div>
															<div class="tbox">
																<s:textfield name="tax" id="TaxValue" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" value='%{"D1".equals(profit_Center)?0.00:tax}' onchange="GetAcqCost()" maxlength="8" disabled='"Y".equals(disableStatus1) || "D1".equals(profit_Center)'/>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.otherCostP" /> 
															</div>
															<div class="tbox">
																<s:textfield name="othercost" id="CostOther" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" onchange="GetAcqCost()" maxlength="8" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>	
														
														<div class="textfield">
															<div class="text">
																<s:text name="label.acqBonus" />
															</div>
															<div class="tbox">
																<s:select list="bonusList" listKey="DETAIL_NAME" listValue="REMARKS" name="acqBonus" cssClass="select1 inputBoxS" headerKey=""	headerValue="---None---"   onchange="funViewLCB(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="ncb" style="display:none">
															<div class="text">
																<s:text name="label.acqBonusPer" />
															</div>
															<div class="tbox">
																	<s:textfield name="acqBonusPercentage" cssClass="inputBox"  cssStyle="text-align: right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="7" disabled='%{"Y".equals(disableStatus1)?true:false}' />
															</div>
														</div>
														<div class="textfield" id="lcb" style="display:none">
															<div class="text">
																<button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal2" onclick="getPopUpDetailsNCB('LCB','<s:property value="contractNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>','<s:property value="layerNo"/>')">
														 			     <s:text name="label.enterDetails" />
														 		</button>
															</div>
															
														</div>									
														<%-- <div class="textfield">
															<div class="text">
																<s:text name="label.acqCostAmount" />
															</div>
															<div class="tbox">
																<s:textfield name="acquisition_Cost" readonly="true" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="middleMinusRestriction(this);javascript:this.value=Comma(this.value)" />
															</div>
														</div>	 --%>
														<s:hidden name="acquisition_Cost" value="0" />
														<s:hidden name="share_Profit_Commission" value="0" />
														<br class="clear"></br>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="boxcontent">
											<div class="panel panel-primary">											
												<div class="panel-heading">
													<s:text name="label.reinstinfo" />
												</div>
												<div class="panel-body">
													<div class="boxcontent">
														<div class="textfield">
															<div class="text">
																<s:text name="label.reinstdetails" />
															</div>
															<div class="tbox">
																<s:radio list="#{'Y':'Yes','N':'No' }" name="reinstdetailYN" id="reinstdetailYN" value="%{reinstdetailYN==null?'N':reinstdetailYN}" onchange="getReinsInfo(this.value);"></s:radio>													
															</div>
														</div>
														<div class="boxcontent" id="reinsid">
														<div class="textfield">
															<div class="text">
																<s:text name="label.reinstatementpremium" />
															</div>
															<div class="tbox">													
																<div class="reinsstyle">
																<div><s:radio name="reInstatementPremium" id="reInstatementPremium" list="#{'Y':'Yes','N':'No'}"  value="(reInstatementPremium==null || reInstatementPremium=='')?'N':reInstatementPremium" onclick="funView(this.value)" disabled='%{"Y".equals(disableStatus1)?true:false}' /></div>
																<div id="rsp" style="display:none"><button type="button" name="companyBtn" id="companyBtn" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#companyModal1" onclick="getPopUpDetails('RSP','<s:property value="contractNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="layerProposalNo"/>','<s:property value="anualAggregateLiability"/>','<s:property value="layerNo"/>')">
														 			     <s:text name="label.enterDetails" />
														 		</button>
														 		</div>
																</div>
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="label.annualAggregateLiability" />
															</div>
															<div class="tbox">													
																<s:textfield name="anualAggregateLiability" id="anualAggregateLiability" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;" maxlength="16" />
															</div>
														</div>
														<div class="textfield">
															<div class="text">
																<s:text name="RiskDetails.AnnualAggrDeduct" />
															</div>
															<div class="tbox">													
																<s:textfield name="anualAggregateDeduct" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value)" cssClass="inputBox" cssStyle="text-align: right;"  maxlength="16"/>
															</div>
														</div>
														<div class="textfield" id="occID" style="display:none;">
															<div class="text">
																<s:text name="label.occ.limit" />
															</div>
															<div class="tbox">
																<s:textfield name="occ_limit" id="occ_limit" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value)" maxlength="26"/>
															</div>
														</div>
														<br class="clear"></br>
														</div>
													</div>
												</div>
											</div>
										</div>
										<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
										<div class="boxcontent" align="center">
										<s:if test='"layer".equals(layerMode) && "layer".equals(flag)'>
										<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="disableForm(this.form,false,'');destroyPopUps();FunctionEdit()" />
										<button class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');FunctionUpdateOption()">Update Layer</button>
										</s:if>
										<s:elseif test='"layer".equals(layerMode) && "copy".equals(flag)'>
										<input type="button" value="Cancel" class="btn btn-sm btn-danger" onClick="disableForm(this.form,false,'');destroyPopUps();FunctionEdit()" />
										<button class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');FunctionAddLayer()">Add Layer</button>
										</s:elseif>
										
											
										</div>
									</div>
								</div>
							</div>
							
							
														
						</div>
						<div id="companyModal" class="modal fade" role="dialog">
						  <div class="modal-dialog modal-lg">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						      </div>
						      <div class="modal-body" >
						        <div class="container-fluid" id="companyAjaxId">
						        </div>
						      </div>
						    </div>
						  </div>
						</div>	
						<div id="companyModal1" class="modal fade" role="dialog">
						  <div class="modal-dialog modal-lg">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						      </div>
						      <div class="modal-body" >
						        <div class="container-fluid" id="companyAjaxId1">
						        </div>
						      </div>
						    </div>
						  </div>
						</div>
						<div id="companyModal2" class="modal fade" role="dialog">
						  <div class="modal-dialog modal-lg">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						      </div>
						      <div class="modal-body" >
						        <div class="container-fluid" id="companyAjaxId2">
						        </div>
						      </div>
						    </div>
						  </div>
						</div>	
						<div id="layerModal" class="modal fade" role="dialog">
						  <div class="modal-dialog modal-lg">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						      </div>
						      <div class="modal-body" >
						        <div class="container-fluid" >
						        	<div class="boxcontent">
										<div class="textfield">
											<div class="text">
												<s:text name="label.newLayer" />
											</div>
											<div class="tbox">
												<s:textfield name="layerLayerNo" id="layerLayerNo" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="negative(this.id,this.value);hundredCheck(this.id,this.value);" maxlength="5"/>													
											</div>
										</div>
										<div class="textfield">
											<button type="button"  class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionAddLayer();"  tabindex="1"> Submit </button>
										</div>
									</div>
						        </div>
						      </div>
						    </div>
						  </div>
						</div>
						</div>					
						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<s:hidden name="amend_Id_Mode"/>
								<s:if test='amend_Id_Mode == null ||"".equals(amend_Id_Mode)  '>
									<s:if test='proposal_no == null ||"".equals(proposal_no) '>
										<s:if test='layerProposalNo == null ||"".equals(layerProposalNo) '>
											<s:if test='"renewal".equals(flagTest)'>
												<input type="button"  value="Back"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
											</s:if>										
										</s:if>
										
										<s:hidden name="ceddingcompanyBack" value="%{cedingCo}" />
										<% request.setAttribute("LayerMode","Yes");  %>
										<s:if test='"renewal".equals(flagTest)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
										</s:if>
										<s:else>
											 <input type="button"  value="Back"  class="btn btn-sm btn-danger" id="mybutton" onClick="FunctionEditCancel()" />
										</s:else>
										<s:if test='"Y".equals(bouquetModeYN)'>	
											<button class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionAddLayer()">Add to Bouquet</button>
										</s:if>
										<s:else>
											<button class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionAddLayer()">Save</button>
										</s:else>
										<input type="button"  value="Submit"   class="btn btn-sm btn-warning"   id="mybutton1" onclick="disableForm(this.form,false,'');funEditSubmit()" />
										<!-- <input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  /> -->
							 			<!-- <input type="button"  value="Next"    class="btn btn-sm btn-warning"  id="mybutton1"  onclick="disableForm(this.form,false,'');next()" /> -->
									</s:if>
									<s:else>
										<s:if test='"renewal".equals(flagTest) || "Renewal".equals(proposalReference) || "Layer".equals(proposalReference) '>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"   onClick="return FunctionCancel();" />
											
										</s:if>
										<s:elseif test='"R".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"  onClick="FunctionRejectCancel()" />
										</s:elseif>
										<s:elseif test='"N".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="FunctionNotTakenCancel()" />
										</s:elseif>
										<s:else>
											<input type="button"  value="Back"  class="btn btn-sm btn-danger" id="mybutton"   onClick="FunctionEditCancel()" />
										</s:else>	
										<%-- <s:if test='!"layer".equals(flag)  && !"copy".equals(flag)'>
										 <input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()" /> 
										  <input type="button"  value="Submit"   class="btn btn-sm btn-warning"   id="mybutton1" onclick="disableForm(this.form,false,'');funEditSubmit()" />
										</s:if> --%>
							 			
									</s:else>
								</s:if>
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="AmendIdBack()" />
									<!--<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"  />
									--><input type="button"  value="Next"   class="btn btn-sm btn-warning" id="mybutton1"  onclick="disableForm(this.form,false,'');destroyPopUps();funAmendsubmit()" />
								</s:else>								
							</div>
						</div>
																			
					</div>
					</div>
					<s:hidden name="proposalNo1" id="proposalNo1"/>
					<s:hidden name="manufactureID" id="manufactureID"/>
					<s:hidden name="flag" id="flag"/>
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
					<s:hidden name="mode" id="mode"/>
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
<s:if test='("layer".equals(layerMode) && (#elayerInfo!=null && #elayerInfo.size()>0)) || (proposal_no==null ||"".equals(proposal_no))'>
document.getElementById("sectionid").style.display = "inline";
</s:if>
<s:else>
document.getElementById("sectionid").style.display = "none";
</s:else>
$('.select1').select2({ });

var mtbChildWin = new Array();
var mtbWinCound = 0;
function destroyPopUps() 
{
for (mtbWinCound = 0; mtbWinCound < mtbChildWin.length; mtbWinCound++) {
 if (mtbChildWin[mtbWinCound] != null && !mtbChildWin[mtbWinCound].closed)
 mtbChildWin[mtbWinCound].close();
 }
 }

function getDepatmentCover()
{
  	var val1 =document.getElementById("departId").value;
  	
  	if(val1=="17" || val1=="18" || val1=="19" ){
  	document.getElementById("coverdepartId[0]").disabled=false;
  	document.getElementById("coverdepartIdS[0]").disabled=false;
  	}else{
	document.getElementById("coverdepartId[0]").value=val1;
	document.getElementById("coverdepartIdS[0]").value=val1;
	document.getElementById("coverdepartId[0]").disabled=true;
  	document.getElementById("coverdepartIdS[0]").disabled=true;
	}
}
function getSubDepatmentCover()
{
	 //subclass//var data=$('#subProfit_center').val();
	 //$("#coversubdepartId0").val(data);
	 //$("#coversubdepartId0").multiselect("refresh");
}
function getAjax1(value,id)
{
	var URL='${pageContext.request.contextPath}/ajaxValueXol.action?departId='+value+'&dropDown=subclass';
	postRequest(URL,'subclass');
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
function getAjaxCoverClass(){
var drop = "";
var id="";
var value =document.xol1.departId.value;
var proposal_no = document.xol1.proposal_no.value;
var business=document.xol1.businessType.value;
document.getElementById('departId').disabled = false;
if(business!='5'){ 
	 drop = "newgen";
}else if(business=='5'){
	drop = "newgen1";
} 
if(business!=''){ 
document.getElementById("mode").value = "class";
    	if("newgen"==drop){
    	
    	$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?dropDown=newgen', "newgenid", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //getEgnpiCal();
        //CalculateEGNPI();
        getDepatmentCover('our');
        if(proposal_no!=''){
      //  document.getElementById('departId').disabled = true;
        }
        }
			});
    	}else if("newgen1"==drop){
    	$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?dropDown=newgen1', "newgenid1", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //alert("1");
        //getEgnpiCalSlide();
        //CalculateEGNPI('our');
        getDepatmentCover();
        if(proposal_no!=''){
       // document.getElementById('departId').disabled = true;
        }
        }
			});
    	} 
    }
}

function next()
{
destroyPopUps();
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
var business=document.xol1.businessType.value;
if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium');
	//document.xol1.action="checkXol.action?countryExcluded="+checkedItems+"&countryIncluded="+checkedItems1;
	document.xol1.action="checkXol.action";
	document.xol1.submit();
}
function funsubmit()
{
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
		 if(Diff<=0) 
		 {
			 alert("Inception Date Invalid ..You are In Renewal Mode")
		 }
		 else
		 {
		 	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium');
		 	document.xol1.action="${pageContext.request.contextPath}/checkRiskDetails";
		 	document.xol1.submit();
		 }
	</s:if>
	<s:else>
			replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium');		
			document.xol1.action="${pageContext.request.contextPath}/checkRiskDetails";
			document.xol1.submit();
	</s:else>
	}
}

function FunctionEdit()
{	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.getElementById("multiuserMode").value='edit';
	document.xol1.action="${pageContext.request.contextPath}/EditModeXol.action";
	document.xol1.submit();
}

function funEditSubmit()
{
destroyPopUps();
	//if(chkAccAmt()){
	document.xol1.editMode.value="S";
	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium,anualAggregateLiability,anualAggregateDeduct');
	document.xol1.action="FirstPageSaveMethodXol.action";
	document.xol1.submit();
	//}
}
function FunctionSaveOption()
{
	destroyPopUps();
	var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium,anualAggregateLiability,anualAggregateDeduct');
	document.xol1.action="FirstPageSaveMethodXol.action";
	document.xol1.submit();
}
function FunctionAddLayer()
{
	destroyPopUps();
	var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("flag").value='';
	document.getElementById("proposal_no").value="";
	document.getElementById("layerMode").value='';
	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium,anualAggregateLiability,anualAggregateDeduct');
	document.xol1.action="FirstPageSaveMethodXol.action";
	document.xol1.submit();
}
function FunctionUpdateOption()
{
	destroyPopUps();
	var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	document.getElementById("flag").value='';
	document.getElementById("layerMode").value='';
	document.getElementById("proposal_no").value=document.xol1.layerProposalNo.value;
	replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium,anualAggregateLiability,anualAggregateDeduct');
	document.xol1.action="FirstPageSaveMethodXol.action";
	document.xol1.submit();
}

function FunctionView()
{
	document.xol1.action="${pageContext.request.contextPath}/ViewMethodRiskDetails.action"
	document.xol1.submit();
}

function funAmendsubmit()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("countryExcluded");
			var elements1=document.getElementById("countryIncluded");
			var elements2=document.getElementById("proStatus");
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
		else if("A"==elements2)
		{
		    alert('Please Select Territory');
		}
	dffDateAlert();
	document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
		replaceComma(document.xol1,'event_limit,coverLimitXL,deductLimitXL,subPremium,egnpipml,epi,minPremium,m_dPremium');
		document.xol1.action="FirstPageUpdateModeXol.action";
		document.xol1.submit();
}

function FunctionAmendSaveOption()
{
	document.xol1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIDFirstPageSaveMathod"
	document.xol1.submit();
}

function FunctionEditCancel()
{
	destroyPopUps();
	document.getElementById("flag").value='P';
	document.xol1.action ="${pageContext.request.contextPath}/commonListPortfolio.action";
	document.xol1.submit();
}


function FunctionRejectCancel()
{
	destroyPopUps();
	document.xol1.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.xol1.submit();
}
function FunctionNotTakenCancel(){
	destroyPopUps();
	document.xol1.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.xol1.submit();
}

function AmendIdBack()
{	
	destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	document.getElementById("proposalNo").disabled = false;
	document.xol1.action= "${pageContext.request.contextPath}/InitPortfolio.action?endtMode=endorsment&endorsementStatus=N";
	document.xol1.submit();
}

<%--function calculation()
{
	//var limit=document.xol1.limitOrigCur.value;
	//limit=limit.replace(new RegExp(',', 'g'),'');
	var Epi100=document.xol1.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');

	var ourEsitamate=document.xol1.ourEstimate.value;
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" )
	{
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.xol1.epi.value=Comma(finalvalue.toFixed(2));
	}else
	document.xol1.epi.value='0';
}--%>

function GetShareSigned(value)
{
		
		if(value=='A')
		{
		document.xol1.sharSign.disabled=false;
		}
		else  
		{
		document.xol1.sharSign.value='';
		document.xol1.sharSign.disabled=true;
		}
		
}

function GetExchangeRate() {
		var incDate=document.forms['xol1'].incepDate.value;
		var accDate='';
			//document.forms['xol1'].accDate.value;
		var Currecny=document.xol1.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !=""))
		{
	 		var URL='${pageContext.request.contextPath}/getExcRateXol.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else
		{
			document.xol1.exchRate.value='';
		}
}


function CalculateEpi() {	
var premiumbasis = document.xol1.premiumbasis.value;
var a = 0;
	var a=document.xol1.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	if('1'==premiumbasis){
	var b=document.xol1.adjRate.value;
	}
	else if('2'==premiumbasis){
	var b=document.xol1.minimumRate.value;
	}
	else if('3'==premiumbasis){
	document.getElementById("epi").readOnly=false;
	}
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.xol1.epi.value=Comma(c.toFixed(2));
	}else {
		document.xol1.epi.value='';
	}
	
}


function CalculateEGNPIPML() {	
	<%--var a=document.xol1.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.pmlPercent.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var pmlPer=parseFloat(b)/100;
		var c=parseFloat(a)*pmlPer;
		document.xol1.egnpipml.value=Comma(c.toFixed(2));
	}else {
		document.xol1.egnpipml.value='';
	}--%>
}

function mdpremiumval(){
document.xol1.m_dPremium.value = document.xol1.minPremium.value;
}
function CalculateminimumrateEpi() {	
	var egn = 0;
	 egn = document.xol1.subPremium.value;
	egn=egn.replace(new RegExp(',', 'g'),'');
	var b=document.xol1.minimumRate.value;
	var bas =document.xol1.premiumbasis.value;
	if(bas=="2"){
	if(egn!=null && egn!="" && b!=null && b!="") {
		var minRt=parseFloat(b)/100;
		var c=parseFloat(egn)*minRt;
		document.xol1.minPremium.value=Comma(c.toFixed(2));
		mdpremiumval();
	}
	else {
		document.xol1.minPremium.value='';
		document.xol1.m_dPremium.value ='';
	}
	}
	else {
		document.xol1.minPremium.value='';
		document.xol1.m_dPremium.value ='';
	}
}

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
		document.xol1.epi_origCur.value=Comma(document.xol1.epi_origCur.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.xlCost.value=Comma(document.xol1.xlCost.value);
		document.xol1.cedReten.value=Comma(document.xol1.cedReten.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		
	} else if(value=="3") {
		document.xol1.subPremium.value=Comma(document.xol1.subPremium.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		document.xol1.m_dPremium.value=Comma(document.xol1.m_dPremium.value);
		
		document.xol1.coverLimitXL.value=Comma(document.xol1.coverLimitXL.value);
		document.xol1.deductLimitXL.value=Comma(document.xol1.deductLimitXL.value);
		document.xol1.minPremium.value=Comma(document.xol1.minPremium.value);
	} else if(value=="4") {
		document.xol1.epi_origCur.value=Comma(document.xol1.epi_origCur.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.xlCost.value=Comma(document.xol1.xlCost.value);
	} else if(value=="5") {
		document.xol1.deduc_hunPercent.value=Comma(document.xol1.deduc_hunPercent.value);
		document.xol1.limitOrigCur.value=Comma(document.xol1.limitOrigCur.value);
		document.xol1.subPremium.value=Comma(document.xol1.subPremium.value);
		document.xol1.epi.value=Comma(document.xol1.epi.value);
		document.xol1.m_dPremium.value=Comma(document.xol1.m_dPremium.value);
	}
}

/*********************************/

function GetSp(id)
{	
	if(id=='Y')
	{
		document.xol1.no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N')
	{
		document.getElementById("NoOFInsur").readOnly=true;
		document.xol1.no_Insurer.value="0";
	}
}
getPremiumBasis();
function getPremiumBasis(){ 
	var type= document.xol1.businessType.value;
	var val= document.xol1.premiumbasis.value;
 if(val=='1'){
	document.getElementById('rate').style.display='inline';
	if(type=="5"){
		document.getElementById('rate1').style.display='inline';
	 	document.getElementById('rate2').style.display='inline';
	}
	else{
	 	document.getElementById('rate1').style.display='none';
	 	document.getElementById('rate2').style.display='none';
 	}
	document.getElementById('rate3').style.display='none';
	document.getElementById('burningCostLF').value ='';
	//document.getElementById("minPremium").readOnly=false;   
	 }   
	 
else if(val=='2'||val=='7' ){
	document.getElementById('rate').style.display='none';
 	document.getElementById('rate1').style.display='inline';
 	document.getElementById('rate2').style.display='inline';
	document.getElementById('rate3').style.display='inline';
	document.getElementById('adjRate').value ='';
}else{
	document.getElementById('rate').style.display='none';
 	document.getElementById('rate1').style.display='none';
 	document.getElementById('rate2').style.display='none';
	document.getElementById('rate3').style.display='none';
	document.getElementById('burningCostLF').value ='';
	document.getElementById('minimumRate').value ='';
	document.getElementById('maximumRate').value ='';
	document.getElementById('adjRate').value ='';
}
}

function GetStopLossType(val){
var rowCount = 0;
if(val=='5'){
	document.getElementById('stoploss').style.display='inline';
	document.getElementById('stoplossno').style.display='none';
	var table = document.getElementById("newgen1");
 	rowCount = table.rows.length -2;
 	document.getElementById('count').value = parseInt(rowCount-1);
 	
 	//getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6' ||val=='7' || val=='8'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
	var table = document.getElementById("newgen");
 	rowCount = table.rows.length-1;
 	document.getElementById('loopcount').value = parseInt(rowCount);
 	
 	//getEgnpiCal();
}else{
	document.getElementById('stoplossno').style.display='none';
	document.getElementById('stoploss').style.display='none';
}
}
GetStopLoss('<s:property value="businessType"/>');
function GetStopLoss(val){
var rowCount = 0;
if(val=='5'){
	document.getElementById('stoploss').style.display='inline';
	document.getElementById('stoplossno').style.display='none';
 	//getEgnpiCalSlide();
}else if (val=='1' ||val=='2' ||val=='3' ||val=='4' || val=='6' ||val=='7'||val=='8'){
	document.getElementById('stoplossno').style.display='inline';
	document.getElementById('stoploss').style.display='none';
 	//getEgnpiCal();
}else{
	document.getElementById('stoplossno').style.display='none';
	document.getElementById('stoploss').style.display='none';
}
}
if("6"=='<s:property value="businessType"/>'){
getUmbrellaVal();
}
else{
getUmbrellaXL('<s:property value="umbrellaXL"/>');
}
function getUmbrellaXL(val){
if(val=='Y'){
document.getElementById('coverLimitID').style.display='inline';
document.getElementById('deductLimitID').style.display='inline';
}
else{
document.getElementById('coverLimitID').style.display='none';
document.getElementById('deductLimitID').style.display='none';
}
}


function getUmbrellaVal(){
var val = document.getElementById("businessType").value;
if(val=='6'){
document.getElementById('coverLimitID').style.display='inline';
document.getElementById('deductLimitID').style.display='inline';
}
else{
document.getElementById('coverLimitID').style.display='none';
document.getElementById('deductLimitID').style.display='none';
}
}

function functionDate()
{
var	inceptionDate=document.xol1.incepDate.value;
	if(inceptionDate!="")
	{
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
		document.xol1.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.xol1.uwYear.value=date.getFullYear();
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
		var expirydate=d+"/"+m+"/"+y;
		document.xol1.expDate.value=d+"/"+m+"/"+y;	
		var URL='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&dropDown=yearId';
	postRequest(URL,'yearId');
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
	}
}
}
function functionEDate()
{
	var	inceptionDate=document.xol1.incepDate.value;
	var	expirydate=document.xol1.expDate.value;
	var URL1='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&expiryDate='+expirydate+'&dropDown=yearIdto';
	postRequest(URL1,'yearIdto');
}
function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
  	// ex input "2010-01-18" 
	return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}

function dffDateAlert() {
	var inception=document.xol1.incepDate.value;
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
	var expery=(document.xol1.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));		
	if(diffDays>0)
	{
		alert('Expiry Date More than One Year');
	}
}

function chkAccAmt(){
var business=document.xol1.businessType.value;
	if(business!='5'){
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitOC[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitOC[0]").value;
	}else{
	document.xol1.limitOrigCur.value=document.getElementById("coverLimitAmount[0]").value;
	document.xol1.deduc_hunPercent.value=document.getElementById("deductableLimitAmount[0]").value;
	}
	var limitOC=document.xol1.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.xol1.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.xol1.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.xol1.proStatus.value=="A")
	{	 
		cedPer=document.xol1.sharSign.value;
	}else
	{
		cedPer=document.xol1.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.xol1.sharSign.value;
	</s:if>	
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit)
		{
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt)
			{
				<s:if test='contNo == "" && contNo == null'>				
					document.xol1.proStatus.value='P';
					document.xol1.sharSign.value='';
				</s:if>
			}else
			{
				 return false; 
			}
		}
	}
	return true;
}

function setCedRetType(type)
{
	document.getElementById("cedType").value=type;
}

function toggleXLCostOC(retroType)
{
	if(retroType=="SR")
	{
		document.xol1.xlCost.value="0";
		document.xol1.xlCost.readOnly=true;
		document.xol1.insuredName.readOnly=false; 
	}else
	{
		//document.xol1.xlCost.value="";
		document.xol1.xlCost.readOnly=false;
		document.xol1.insuredName.value="";
		document.xol1.insuredName.readOnly=true; 
	}
	
}
if('<s:property value="endtMode"/>'=="endorsment"){
getUWLimit(document.xol1.underwriter.value);
}
function getUWLimit(value){
   //var x= (sel.options[sel.selectedIndex].text);
    var URL='${pageContext.request.contextPath}/UnderwritingLimitXol.action?underwriter='+value+'&dropDown=uwc2';
	postRequest(URL,'uwc2');
}
function insRow(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "coverSNo["+(rowCount-1)+"]";
       		element1.id = "coverSNo["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
       		
			var cell2 = row.insertCell(1);
			//cell2.id = "coverdepartIdtable"+(rowCount-1);
			createcoverdeptCell(cell2, rowCount)
		
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "coverLimitOC["+(rowCount-1)+"]";
      		element2.id = "coverLimitOC["+(rowCount-1)+"]";
      		element2.value=document.getElementById("coverLimitOC["+(rowCount-2)+"]").value;
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element2.setAttribute("maxlength",'30');
			element2.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell3.appendChild(element2);
			
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "deductableLimitOC["+(rowCount-1)+"]";
			element3.value=document.getElementById("deductableLimitOC["+(rowCount-2)+"]").value;
      		element3.id = "deductableLimitOC["+(rowCount-1)+"]";
			element3.className = "inputBox";
			element3.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)");
			element3.setAttribute("maxlength",'30');
			element3.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element3);
			
			
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "egnpiAsPerOff["+(rowCount-1)+"]";
      		element4.id = "egnpiAsPerOff"+(rowCount-1);
			element4.value=document.getElementById("egnpiAsPerOff"+(parseFloat(rowCount)-2)).value;
			element4.className = "inputBox";
			element4.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element4.setAttribute("maxlength",'30'); 
			element4.setAttribute("style", "text-align:right;");
			cell5.appendChild(element4); 
			
			
			var cell6 = row.insertCell(5);
			//cell6.id='gnpi['+(rowCount-1)+']';
			//cell6.style.display ='none';
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "gnpiAsPO["+(rowCount-1)+"]";
      		element5.id = "gnpiAsPO"+(rowCount-1);
			//element5.value=document.getElementById("gnpiAsPO"+(parseFloat(rowCount)-2)).value;
			element5.setAttribute("disabled",true);
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element5.setAttribute("maxlength",'30'); 
			element5.setAttribute("style", "text-align:right;");
			cell6.appendChild(element5); 
			
			
			var cell7 = row.insertCell(6);
			cell7.setAttribute("align","center");
			var element6 = document.createElement("input");
			element6.type = "button";
			element6.value="Delete";
			element6.setAttribute("onclick", "disableForm(this.form,false,'');deleteRow('"+(rowCount-1)+"')");
			element6.className="btn btn-sm btn-danger"
			cell7.appendChild(element6);
			//var val = document.getElementById("loopcount").value ;
			//if(val == ''){
			// document.getElementById("loopcount").value =document.getElementById("count").value;
			// }
			// else{
			// document.getElementById("loopcount").value =parseInt(val)+1;
			// }
			 document.getElementById("loopcount").value =parseInt(rowCount);			 
			// getEgnpiCal();
			 getAjaxCoverClass();
			 
}
function insRow1(tableID)
{
var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			
			var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "coverSNoS["+(rowCount-2)+"]";
       		element1.id = "coverSNoS["+(rowCount-2)+"]";
 			element1.value = rowCount-1;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
			
			var cell2 = row.insertCell(1);
			//cell2.id = "coverdepartIdtable1"+(rowCount-2);
			createcoverdeptCell1(cell2, rowCount)
		
			var cell3 = row.insertCell(2);
			var element2 = document.createElement("input");
			element2.type = "text";
			element2.name = "coverLimitAmount["+(rowCount-2)+"]";
      		element2.id = "coverLimitAmount["+(rowCount-2)+"]";
			element2.value=document.getElementById("coverLimitAmount["+(rowCount-3)+"]").value;
			element2.className = "inputBox";
			element2.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			element2.setAttribute("maxlength",'30'); 
			element2.setAttribute("style", "text-align:right;");
			//element2.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell3.appendChild(element2);
			
			var cell4 = row.insertCell(3);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "coverLimitPercent["+(rowCount-2)+"]";
      		element3.id = "coverLimitPercent["+(rowCount-2)+"]";
			element3.value=document.getElementById("coverLimitPercent["+(rowCount-3)+"]").value;
      		element3.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);");
			element3.className = "inputBox";
			element3.setAttribute("maxlength",'3'); 
			element3.setAttribute("style", "text-align:right;");
			//element3.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell4.appendChild(element3);
			
			var cell5 = row.insertCell(4);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "deductableLimitAmount["+(rowCount-2)+"]";
      		element4.id = "deductableLimitAmount["+(rowCount-2)+"]";
			element4.className = "inputBox";
			element4.value=document.getElementById("deductableLimitAmount["+(rowCount-3)+"]").value;
			element4.setAttribute("onkeyup", "allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)");
			element4.setAttribute("maxlength",'30'); 
			element4.setAttribute("style", "text-align:right;");
			//element4.setAttribute("disabled",'%{"Y".equals(disableStatus1)?true:false}');
			cell5.appendChild(element4);
			
			var cell6 = row.insertCell(5);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "deductableLimitPercent["+(rowCount-2)+"]";
      		element5.id = "deductableLimitPercent["+(rowCount-2)+"]";
			element5.value=document.getElementById("deductableLimitPercent["+(rowCount-3)+"]").value;
			element5.className = "inputBox";
			element5.setAttribute("onkeyup", "checkDecimals(this);middleMinusRestrictionNeg(this);Itnegative(this.id,this.value);allowOneDot(this);");
			element5.setAttribute("maxlength",'3'); 
			element5.setAttribute("style", "text-align:right;");
			//element5.setAttribute("disabled",'{"Y".equals(disableStatus1)?true:false}');
			cell6.appendChild(element5);
			
			var cell7 = row.insertCell(6);
			var element6 = document.createElement("input");
			element6.type = "text";
			element6.name = "egnpiAsPerOffSlide["+(rowCount-2)+"]";
      		element6.id = "egnpiAsPerOffSlide"+(parseInt(rowCount)-2);
			element6.value=document.getElementById("egnpiAsPerOffSlide"+(parseInt(rowCount)-3)).value;
			element6.className = "inputBox";
			element6.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element6.setAttribute("maxlength",'30'); 
			element6.setAttribute("style", "text-align:right;");
			cell7.appendChild(element6);
			
			var cell8 = row.insertCell(7);
			//cell8.id='gnpislide['+(rowCount-2)+']';
			//cell8.style.display ='none';
			var element7 = document.createElement("input");
			element7.type = "text";
			element7.name = "gnpiAsPOSlide["+(rowCount-2)+"]";
      		element7.id = "gnpiAsPOSlide"+(rowCount-2);
			//element7.value=document.getElementById("gnpiAsPO"+(parseFloat(rowCount)-2)).value;
			element7.setAttribute("disabled",true);
			element7.className = "inputBox";
			element7.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);");
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			cell8.appendChild(element7);
			
			
			var cell9 = row.insertCell(8);
			cell9.setAttribute("align","center"); 
			var element8 = document.createElement("input");
			element8.type = "button";
			element8.value="Delete";
			element8.setAttribute("onclick", "disableForm(this.form,false,'');deleteRowS('"+(rowCount-2)+"')");
			element8.className="btn btn-sm btn-danger"
			cell9.appendChild(element8);
			//var val = document.getElementById("loopcount").value ;
			//if(val == ''){
			 //document.getElementById("loopcount").value =document.getElementById("count").value;
			// }
			// else{
			// document.getElementById("loopcount").value =parseInt(val)+1;
			// }
			 document.getElementById("count").value =parseInt(rowCount-1);
			 //getEgnpiCalSlide();
			 getAjaxCoverClass();
}
function createcoverdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartId["+(rowCount-1)+"]";
      	element.id = "coverdepartId["+(rowCount-1)+"]";
       element.className = "select1 inputBoxS";
      	//element.setAttribute("onchange", "getAjaxCover(this.value,'coverdepartid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function createcoversubdeptCell(cell, rowCount){
	element = document.createElement("select");
         element.name = "coversubdepartId["+(rowCount-1)+"]";
      	element.id = "coversubdepartId["+(rowCount-1)+"]";
       element.className = "select1 inputBoxS coversubdepartId";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoversubdept(element);
          cell.appendChild(element);
}
function createcoverdeptCell1(cell, rowCount){
	element = document.createElement("select");
         element.name = "coverdepartIdS["+(rowCount-2)+"]";
      	element.id = "coverdepartIdS["+(rowCount-2)+"]";
       element.className = "inputBox";
      // element.setAttribute("onchange", "getCrestaIDAjax(this.value,'crestaid"+(rowCount-1)+"',"+(rowCount-1)+")");            
       populateCoverdept(element);
          cell.appendChild(element);
}
function populateCoverdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = '---Select---';
          objOption.value = '';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='departIdlist'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_DEPARTMENT_NAME' />".replace("&amp;", "&") ;
				objOption.value = "<s:property value='TMAS_DEPARTMENT_ID' />";
				if(document.all && !window.opera){
					element.add(objOption);
				}else{
					element.add(objOption, null);
				}
			</s:iterator>
 }
 function populateCoversubdept(objSelect){
	var objOption = document.createElement("option");
          objOption.text = 'ALL';
          objOption.value = 'ALL';
          if(document.all && !window.opera){
          	objSelect.add(objOption);
          }else{
          	objSelect.add(objOption, null);
          }
         	<s:iterator value='subProfitList'>
				var objOption = document.createElement("option");
				objOption.text = "<s:property value='TMAS_SPFC_NAME' />".replace("&amp;", "&");
				objOption.value = "<s:property value='TMAS_SPFC_ID' />";
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
		document.getElementById("coverdepartId[0]").disabled = false; 
		 document.getElementById("mode").value = "delete";
		$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitXol.action?deleteId='+val+'&dropDown=newgen', "newgenid", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
       // getEgnpiCal();
        //CalculateEGNPI();
        getDepatmentCover();
        }
			});
			}
		}
}
function deleteRowS(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
		document.getElementById("coverdepartIdS[0]").disabled = false;
		document.getElementById("mode").value = "delete";
			$.ajax({
        type: "POST",
        url: postFormRequest('${pageContext.request.contextPath}/removeClassLimitAmountXol.action?deleteId='+val+'&dropDown=newgen1', "newgenid1", "xol1"),
        //data:'src_type_id='+val,
        success: function(data){
        //alert("1");
        //getEgnpiCalSlide();
       // CalculateEGNPI();
        getDepatmentCover();
        }
			});
			}
		}
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

function removeElementsWithValue(arr, val) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === val) {
            arr.splice(i, 1);
        }
    }
    return arr;
}
function Itnegative(id,val){
if(parseInt(val)<0){
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}

function hundredCheck(id,val){
	if(parseFloat(val)>100){
	alert("This field value is not exceed 100");
	document.getElementById(id).value='';
	}
	else if(val=='-'){
		document.getElementById(id).value='';
	}
}
function FunctionCancel(){
	destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	document.getElementById("proposal_no").disabled =false;
	var no =document.getElementById("proposal_no").value;
	var old = document.getElementById("renewalProposalNo").value;
	if('Renewal'==val){
		answer = confirm("This action will remove the newly created proposal number "+no+" which cannot be reused.  Do you wish to continue?");
		if(answer){
		document.getElementById("proposalNo").value = old;
	document.getElementById("renewalProposalNo").value=no;
			document.xol1.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action';
			document.xol1.submit();
			}
			else {
			document.getElementById("proposal_no").disabled =true;
				 return false; 
			}
		}
		else{
			document.xol1.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.xol1.submit();
		}
	
}

function getEgnpiCal(){
	var texts = document.getElementById("loopcount").value;
	
	if(texts=="0"){
	texts = parseInt(texts)+1;
	}
    var sum = 0;
    for( var i = 0; i < texts; i ++ ) {
    var val = document.getElementById("egnpiAsPerOff"+i).value;
    if(val == ''){
   		val =0;
    }
    else{
    val = val.replace(new RegExp(',', 'g'),'');
    }
        sum = parseFloat(sum) + parseFloat(val);
    }
    document.getElementById("egnpiOffer").value =  Comma(parseFloat(sum).toFixed(2));
			
}

<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
	enableForm1(document.xol1,true,'<s:property value="%{fields}"/>');
</s:if>
<%--
$(document).ready(function() { 
     
    $('#subProfit_center').multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
          var val = $('#subProfit_center').val();
          if (val[0]=='ALL') {
          	$("#subProfit_center").multiselect('clearSelection');
          	$("#subProfit_center").val('ALL');
          	 $("#subProfit_center").multiselect("refresh");
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
 var val=document.getElementById('loopcount').value;
$(document).ready(function() {
    $("#coversubdepartId"+val).multiselect({ 
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
          var val1 = $("#coversubdepartId"+val).val();
          if (val1[0]=='ALL') {
          	$("#coversubdepartId"+val).multiselect('clearSelection');
          	$("#coversubdepartId"+val).val('ALL');
          	 $("#coversubdepartId"+val).multiselect("refresh");
          }
      	}                     
    }); 
  
 
 		
});

//alert('<s:property value="coversubdepartId[0]"/>'+'out');
 <s:iterator value="CoverList"  status="stat">   
  var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
</s:iterator>
--%>

getFieldDisable();
function getFieldDisable(){
var val=document.getElementById("endorsmenttype").value;
	var type = document.getElementById("businessType").value;
	var table='';
	if("GNPI"==val){
		var form = document.getElementById("xol1");
		var elements = form.elements;
		for (var i = 0, len = elements.length; i < len; ++i) {
		    elements[i].disabled = true;
		}
		document.getElementById("mybutton").disabled = false;
		document.getElementById("mybutton1").disabled = false;
		
		if(type=='5'){
			//document.getElementById("gnpiS").style.display = "table-cell";
			//document.getElementById("gnpiE").style.display = "table-cell";
			//document.getElementById("gnpi").style.display = false;
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-2;
			for(var c= 0;c<rowCount;c++){
			//document.getElementById("gnpislide["+c+"]").style.display = "table-cell";
			document.getElementById("gnpiAsPOSlide"+c).disabled = false;
			}
			insRowEmptySlide('newgen1');
		}
		else{
			//document.getElementById("gnpiS").style.display = false;
			//document.getElementById("gnpiE").style.display = false;
			//document.getElementById("gnpi").style.display = "table-cell";
			table = document.getElementById('newgen');
			
			insRowEmpty('newgen');
		}
	}	
	else{
	if(type=='5'){
			table = document.getElementById('newgen1');
			var rowCount = table.rows.length-2;
			for(var c= 0;c<rowCount;c++){
			document.getElementById("gnpiAsPOSlide"+c).disabled = true;
			}
		}
}
}
function insRowEmpty(tableID)
{
var table = document.getElementById(tableID);
var rowCount = table.rows.length;
var val=0;
var val1=0;
if('newgen1' == tableID){
	for(var c= 0;c<rowCount-2;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	 
	 var sum1 = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	}
}
else{
	 for(var c= 0;c<rowCount-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		 sum= sum.replace(new RegExp(',', 'g'),'');
	 	 val +=parseFloat(sum);		
	 	
	 	var sum1 = document.getElementById("gnpiAsPO"+c).value;
	 	sum1= sum1.replace(new RegExp(',', 'g'),'');
		val1 +=parseFloat(sum1);
	 	}
}
val =Comma(val.toFixed(2));
val1 =Comma(val1.toFixed(2));
var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.innerText = "Total" ;
            td.align="Left";
            tr.appendChild(td);
  			var td1 = document.createElement("td");
            td1.innerText = " " ;
            tr.appendChild(td1);
            var td2 = document.createElement("td");
            td2.innerText = " " ;
            tr.appendChild(td2);
            var td3 = document.createElement("td");
            td3.innerText = " " ;
            tr.appendChild(td3);
            var td4 = document.createElement("td");
            var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "egnpiTotaol";
      		element5.id = "egnpiTotaol";
      		element5.value=val;
			element5.className = "inputBox";
			element5.setAttribute("style", "text-align:right;");
			element5.setAttribute("readonly",'true');
            td4.appendChild(element5);
            tr.appendChild(td4);
            var td5 = document.createElement("td");
            var element7 = document.createElement("input");
            element7.type = "text";
			element7.name = "Total";
      		element7.id = "Total";
      		element7.value=val1;
			element7.className = "inputBox";
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("style", "text-align:right;");
			element7.setAttribute("readonly",'true');
			td5.append(element7);
            tr.appendChild(td5);
            var td6 = document.createElement("td");
            td6.innerText = " ";
            tr.appendChild(td6);
            table.children[1].appendChild(tr);
            
}

function insRowEmptySlide(tableID)
{
var table = document.getElementById(tableID);
var rowCount = table.rows.length;
var val=0;
var val1=0
if('newgen1' == tableID){
	for(var c= 0;c<parseInt(rowCount)-2;c++){
	 var sum = document.getElementById("egnpiAsPerOffSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 val +=parseFloat(sum);
	 
	  var sum1 = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	}
}
else{
	 for(var c= 0;c<parseInt(rowCount)-1;c++){
		 var sum = document.getElementById("egnpiAsPerOff"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
	 	val +=parseFloat(sum);	
	 	
	 	var sum1 = document.getElementById("gnpiAsPO"+c).value;
	 sum1= sum1.replace(new RegExp(',', 'g'),'');
	 val1 +=parseFloat(sum1);
	 	}
}
val =Comma(val.toFixed(2));
val1 =Comma(val1.toFixed(2));
var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.innerText = "Total" ;
            tr.appendChild(td);
  			var td1 = document.createElement("td");
            td1.innerText = " " ;
            tr.appendChild(td1);
            var td2 = document.createElement("td");
            td2.innerText = " " ;
            tr.appendChild(td2);
            var td3 = document.createElement("td");
            td3.innerText = " " ;
            tr.appendChild(td3);
            var td4 = document.createElement("td");
            td4.innerText = " " ;
            tr.appendChild(td4);
            var td5 = document.createElement("td");
            var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "egnpiTotaol";
      		element5.id = "egnpiTotaol";
      		element5.value=val;
			element5.className = "inputBox";
			element5.setAttribute("style", ";text-align:right;");
			element5.setAttribute("readonly",'true');
            td5.appendChild(element5);
            tr.appendChild(td5);
            var td6 = document.createElement("td");
            tr.appendChild(td6);
           	var td7 = document.createElement("td");
            var element7 = document.createElement("input");
            element7.type = "text";
			element7.name = "Total";
      		element7.id = "Total";
      		element7.value=val1;
			element7.className = "inputBox";
			element7.setAttribute("maxlength",'30'); 
			element7.setAttribute("readonly",'true'); 
			element7.setAttribute("style", ";text-align:right;");
			td7.append(element7);
            tr.appendChild(td7);
            var td8 = document.createElement("td");
            tr.appendChild(td8);
            table.children[1].appendChild(tr);
}

function getEgnpiVal(tableID){
var table = document.getElementById(tableID);
var val=0;
var rowCount = table.rows.length-2;

if('newgen1' == tableID){
	for(var c= 0;c<rowCount;c++){
	 var sum = document.getElementById("gnpiAsPOSlide"+c).value;
	 sum= sum.replace(new RegExp(',', 'g'),'');
	 if(sum==''){
	 sum =0;
	 }
	 val +=parseFloat(sum);
	}
	val =Comma(val.toFixed(2));
	document.getElementById("egnpiTotaol").value=val;
}
else{
	 for(var c= 0;c<rowCount;c++){
		 var sum = document.getElementById("gnpiAsPO"+c).value;
		sum= sum.replace(new RegExp(',', 'g'),'');
		if(sum==''){
		 sum =0;
		 }
	 	val +=parseFloat(sum);		
	 	}
	 	val =Comma(val.toFixed(2));
	document.getElementById("Total").value=val;
}

}

 function getViewContractDetails(proposalNo,amendId) {
		var URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
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
	
	function getTypeOfBusiness(val,id){
			var URL='${pageContext.request.contextPath}/getTypeOfBusinessXol.action?dropDown='+id+'&departId='+val;
  	 		postRequest(URL,id);
	}
	function getBasis(val,id){
			var URL='${pageContext.request.contextPath}/getTypeOfBusinessXol.action?dropDown='+id+'&departId='+val;
  	 		postRequest(URL,id);
	}
	function getPaypartner(val,id){
		var cedingid=document.getElementById("CeddingId").value;
		var URL='${pageContext.request.contextPath}/paymentPartnerAjaxXol.action?dropDown='+id+'&cedingId='+cedingid+'&brokerId='+val;
	 		postRequest(URL,id);
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
funViewLCB('<s:property value="acqBonus"/>');
function  funViewLCB(id){
		if(id=="LCB"){
     		document.getElementById('lcb').style.display = 'block';
     		document.getElementById('ncb').style.display = 'none';
       	} 
       	else if(id=="NCB"){
       	 	document.getElementById('lcb').style.display = 'none';
       	 	document.getElementById('ncb').style.display = 'block';
       	}
       	else{
       		document.getElementById('lcb').style.display = 'none';
       	 	document.getElementById('ncb').style.display = 'none';
       	}
}

function getPopUpDetailsNCB(acqBonus,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo,layerNo){
 document.getElementById("bonusPopUp").value = "Y";
 var referenceNo = document.getElementById("referenceNo").value;
	var URL ="${pageContext.request.contextPath}/viewBonusPopUpXol.action?acqBonus="+acqBonus+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&layerNo="+layerNo+"&referenceNo="+referenceNo;		
	postRequest(URL, 'companyAjaxId2');
	}
funView('<s:property value="reInstatementPremium"/>');
function  funView(id){
		if(id=="Y"){
     		document.getElementById('rsp').style.display = 'block';
       	} 
       	else if(id=="N"){
       	 	document.getElementById('rsp').style.display = 'none';
       	}
}
function getPopUpDetails(pageFor,contractNo,endorsmentno,proposalNo,flag,renewalProposalNo,anualAggregateLiability,layerNo){
var val ='<s:property value="disableStatus1"/>';
var mode='';

if('Y'==val){
mode='view';
}
var referenceNo = document.getElementById("referenceNo").value;
 document.getElementById("reinsPopUp").value = "Y";
var URL ="${pageContext.request.contextPath}/viewPopUpXol.action?pageFor="+pageFor+"&proposal_no="+proposalNo+"&amendId="+endorsmentno+"&contractNo="+contractNo+"&flag="+flag+"&layerProposalNo="+renewalProposalNo+"&anualAggregateLiabilityTemp="+anualAggregateLiability+"&layerNo="+layerNo+'&mode='+mode+"&referenceNo="+referenceNo;
postRequest(URL, 'companyAjaxId1');	
}
getRiskInfo('<s:property value="riskdetailYN"/>');
function getRiskInfo(val){
	if(val=="Y"){
    	document.getElementById('riskid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('riskid').style.display = 'none';
   	}
}
getBrokerInfo('<s:property value="brokerdetYN"/>');
function getBrokerInfo(val){
	if(val=="Y"){
    	document.getElementById('brokerid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('brokerid').style.display = 'none';
   	}
}
getPremiumInfo('<s:property value="premiumdetailYN"/>');
function getPremiumInfo(val){
	if(val=="Y"){
    	document.getElementById('premiumid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('premiumid').style.display = 'none';
   	}
}
getInstallInfo('<s:property value="installYN"/>');
function getInstallInfo(val){
	if(val=="Y"){
    	document.getElementById('insid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('insid').style.display = 'none';
   	}
}
getAcqInfo('<s:property value="acqdetailYN"/>');
function getAcqInfo(val){
	if(val=="Y"){
    	document.getElementById('aquid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('aquid').style.display = 'none';
   	}
}
getReinsInfo('<s:property value="acqdetailYN"/>');
function getReinsInfo(val){
	if(val=="Y"){
    	document.getElementById('reinsid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('reinsid').style.display = 'none';
   	}
}
getBouquest('<s:property value="bouquetModeYN"/>');
function getBouquest(val){
	if(val=="Y"){
    	document.getElementById('bouquetid').style.display = 'block';
    	$('.select1').select2({ });
   	} 
   	else{
   	 	document.getElementById('bouquetid').style.display = 'none';
   	}
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
function GetAcqCost() {

	var sourceId ='<s:property value="#session.SOURCE_CODE"/>';
	var epi=0;
		if(sourceId=="RI01"){
			epi=document.xol1.subPremium.value;
		}else{
		 epi=document.xol1.subPremium.value;
		}
		epi= epi.replace(new RegExp(',', 'g'),'');
		//var md=document.xol2.md_premium_our_service.value;
		var brok=document.getElementById("BrokeragerValue").value;
		var tax=document.getElementById("TaxValue").value;
		var CostOther=document.getElementById("CostOther").value;
		if(brok==""){
		brok="0";
		}
		if(tax==""){
		tax="0";
		}
		if(CostOther==""){
		CostOther="0";
		}
		//if( document.getElementById("BrokeragerValue").value!="" && document.getElementById("TaxValue").value !=""  && document.getElementById("CostOther").value != "") {
			var c=parseFloat(epi)*((parseFloat(brok)+parseFloat(tax)+parseFloat(CostOther))/100);
			document.xol1.acquisition_Cost.value=Comma(c.toFixed(2));
		//}
		//else {
		//	document.xol2.acquisition_Cost.value=''
		//}
	}
	
	
	//Reinspopup	
function fnSubmit(){
	//$('#companyModal1').modal('toggle');
	var anual=document.getElementById("anualAggregateLiabilityTemp").value;
	document.getElementById("anualAggregateLiability").value =anual;
	postFormRequest('${pageContext.request.contextPath}/insReinstatementDetailsXol.action', "companyAjaxId1", "xol1");
	
	//document.xol1.action="${pageContext.request.contextPath}/insReinstatementDetailsXol.action";
	//document.xol1.submit();
}
function removeRowReins(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowXol.action?mode=delete&deleteId="+val, "companyAjaxId1", "xol1");
			}
		}
}

	function getDet(){
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


	function ReinsRow(tableID)
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
				element6.setAttribute("onclick", "disableForm(this.form,false,'');removeRowReins('"+(rowCount-1)+"')");
				element6.className="btn btn-sm btn-info"
				cell6.appendChild(element6);
				</s:if>
				<s:else>
				
				var cell4 = row.insertCell(3);
				cell4.setAttribute("align","center");
				var element6 = document.createElement("input");
				element6.type = "button";
				element6.value="Delete";
				element6.setAttribute("onclick", "disableForm(this.form,false,'');removeRowReins('"+(rowCount-1)+"')");
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
			 document.getElementById("coverLimitOCRe["+(i)+"]").value=Comma(((parseInt(val))*(parseInt(noofrows))).toFixed(2));
			  total=parseInt(total)+parseInt(val);
			 </s:iterator>
			 document.getElementById("anualAggregateLiabilityTemp").value=Comma(((parseInt(total))*(parseInt(noofrows))).toFixed(2));
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
		document.getElementById("anualAggregateLiabilityTemp").value=Comma(total.toFixed(2));
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
	         	<s:iterator value='reinstatementTypeList'>
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
	 	document.getElementById("reinstatementMinAmount"+"["+id+"]").reafdOnly=true;
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
	
	//bonus
	function fnBSubmit(){

	postFormRequest('${pageContext.request.contextPath}/insBonusDetailsXol.action', "companyAjaxId2", "xol1");

	
}
function BonusremoveRow(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowBonusXol.action?mode=delete&deleteId="+val, "companyAjaxId2", "xol1");
			//postFormRequest("${pageContext.request.contextPath}/removeRowXol.action?mode=delete&deleteId="+val, "companyAjaxId1", "xol1");
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
function BonusinsRow(tableID)
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
			element5.setAttribute("onclick", "BonusremoveRow('"+(rowCount-1)+"')");
			element5.className="btn btn-sm btn-info"
			cell5.appendChild(element5);
			 document.getElementById("loopcount").value =parseInt(rowCount);
			// }
}

function deleteRow11(tableID,i) {
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
function installRow(tableID){
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;	
	//alert(rowCount);
	var row = table.insertRow(rowCount);
	var cell1 = row.insertCell(0)
			var element1 = document.createElement("input");
			element1.type = "text";
      	 	element1.name = "installsno["+(rowCount-1)+"]";
       		element1.id = "installsno["+(rowCount-1)+"]";
 			element1.value = rowCount;
 			element1.className = "inputBox";
 			//element1.innerHTML = rowCount;
			element1.setAttribute("readonly",true);
       		cell1.appendChild(element1);
	
	
	var cell2 = row.insertCell(1);
	var element2 = document.createElement("input");
	element2.type = "text";
	element2.name = "instalmentDateList["+(rowCount-1)+"]";
	element2.id = "instalmentDateList["+(rowCount-1)+"]";
	element2.className = "inputBox datepicker instalmentDate";
	element2.type = "text";
	element2.setAttribute("onkeyup", "validateSpecialChars(this);"); 
	cell2.appendChild(element2);
	 $(function() {
		 $( ".instalmentDate" ).datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd/mm/yy"
				//yearRange: "-100:+0"
			});
		});
	cell2.appendChild(element2);
	
	var cell3 = row.insertCell(2);
	var element3 = document.createElement("input");
	element3.type = "text";
	element3.name = "installmentPremium["+(rowCount-1)+"]";
	element3.id = "installmentPremium["+(rowCount-1)+"]";
	element3.className = "inputBox";
	element3.style = "text-align: right";
	element3.type = "text";
	element3.setAttribute("maxlength", "20");
	element3.setAttribute("onkeyup", "Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);this.value=Comma(this.value);allow2DigitDecValues(this);"); 	
	cell3.appendChild(element3);
	
	var cell4 = row.insertCell(3);
	var element4 = document.createElement("input");
	element4.type = "text";
	element4.name = "paymentDueDays["+(rowCount-1)+"]";
	element4.id = "paymentDueDays["+(rowCount-1)+"]";
	element4.className = "inputBox";
	element4.style = "text-align: right";
	element4.type = "text";
	element4.setAttribute("maxlength", "20");
	element4.setAttribute("onkeyup", "checkNumbers(this);middleMinusRestrictionNeg(this);"); 	
	cell4.appendChild(element4);
	
	var cell6 = row.insertCell(4);
	//cell9.setAttribute("align","center");
	var element9 = document.createElement("input");
	element9.type = "button";
	element9.value="Delete";
	element9.setAttribute("onclick", "removeInst('"+(rowCount-1)+"')");
	element9.className="btn btn-sm btn-info"
	cell6.appendChild(element9);
	
}

function removeInst(val){
	if(val==0){
		alert("First row can't be deleted");
	}
	else{
		var status=confirm("Do you want to delete specified row");
		if(status){
			postFormRequest("${pageContext.request.contextPath}/removeRowInstXol.action?dropDown=deleteInst&deleteId="+val, "insid", "xol1");
			//postFormRequest("${pageContext.request.contextPath}/removeRowXol.action?mode=delete&deleteId="+val, "companyAjaxId1", "xol1");
			}
		}
}
function funEditMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
    document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='layer';
    if(productId==2){
    		document.xol1.action="EditModeRiskDetails.action?departmentId="+deptId;
    }
    else if(productId==3 || productId==5) {
		if(baseLayer.length>0){
        document.getElementById("contractno1").value=baseContract;
        document.getElementById("lay1").value='layer';
      }
    	document.xol1.action="EditLayerXol.action"
    }else if(productId==4){
    	document.xol1.action="EditModeRetro.action"
    }else if(productId==1){
    	document.xol1.action="EditMethodFacultative.action";
    }
    document.xol1.submit();
}
function funDeleteLayer(no){
	destroyPopUps();
	document.getElementById("proposalReference").value='Layer';
	document.getElementById("renewalProposalNo").value=no;
	document.xol1.action='${pageContext.request.contextPath}/CancelProposalXol.action';
	document.xol1.submit();
	
}
function funViewMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	
		var URL ='<%=request.getContextPath()%>/layerViewXol?proposalNo1='+proposalno;
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
function funCopyMode1(ProposalNo){
	document.xol1.layerProposalNo.value=ProposalNo;
}
function funCopyMode(proposalno,ceddingcompanyid,productId,baseLayer,baseContract,deptId) {
	document.getElementById("laydet").style.display = "none";
    document.getElementById("proposalNo1").value=proposalno;
    document.getElementById("CustomerId").value=ceddingcompanyid;
    document.getElementById("baseLayer").value=baseLayer;
    document.getElementById("layerMode").value='layer';
	document.getElementById("flag").value='copy';
    if(productId==2){
    		document.xol1.action="EditModeRiskDetails.action?departmentId="+deptId;
    }
    else if(productId==3 || productId==5) {
		if(baseLayer.length>0){
        document.getElementById("contractno1").value=baseContract;
        document.getElementById("lay1").value='layer';
      }
    	document.xol1.action="EditLayerXol.action"
    }else if(productId==4){
    	document.xol1.action="EditModeRetro.action"
    }else if(productId==1){
    	document.xol1.action="EditMethodFacultative.action";
    }
    document.xol1.submit();
}
function procceed(){
	
	document.xol1.action="InitXol.action";
	document.xol1.submit();
	
}
<s:if test='bouquetModeYN!=null && !"".equals(bouquetModeYN)'>	
document.getElementById('bouquestid').style.display = 'block';
document.getElementById('bouquetModeYNY').disabled=true;
document.getElementById('bouquetModeYNN').disabled=true;

document.getElementById('bouquetpds').style.display = 'none';
if ($("#bouquetModeYNY").prop("checked")) {
	document.getElementById('bouquetNo').disabled=true;
}
$('.select1').select2({ });
</s:if>
function getRateOnline(val1){
	var total=0;
	<s:iterator value="CoverList"  var="list" status="stat">
	 var i = <s:property value="%{#stat.count-1}"/>;
	 var val= document.getElementById("coverLimitOC["+(i)+"]").value;
	 if(val==''){
	 val ="0";
	 }else{
	 val=val.replace(new RegExp(',', 'g'),'');
	 }
	 total=parseInt(total)+parseInt(val);
	 </s:iterator>
	 val1=val1.replace(new RegExp(',', 'g'),'');
	 var finalvalue=(parseFloat(total)/parseFloat(val1))*100;
	 document.getElementById("rateOnLine").value=Comma(finalvalue.toFixed(2));	 
	
}

</script>		
</body>
</html>
