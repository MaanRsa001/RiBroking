<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	 $(function() {
		$( "#endorsementDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
     });
</script>


<body onload="Commas(<s:property value="#session.mfrid" />);loadPremiumOCDetails()">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form id="retro2" name="retro2" theme="simple" action=""	method="post" enctype="multipart/form-data" autocomplete="off">
					<s:set name="line" value="request.getAttribute('InstalMentNumber')==null||''.equalsIgnoreCase(request.getAttribute('InstalMentNumber').toString())?0:Integer.parseInt(request.getAttribute('InstalMentNumber').toString())" />
					<s:set name="NoRetroCess" value="request.getAttribute('NoRetroCess')==null||''.equalsIgnoreCase(request.getAttribute('NoRetroCess').toString())?0:Integer.parseInt(request.getAttribute('NoRetroCess').toString())" />
					<s:set name="noIns" value="request.getAttribute('NoInsurar')==null||''.equalsIgnoreCase(request.getAttribute('NoInsurar').toString())?0:Integer.parseInt(request.getAttribute('NoInsurar').toString())" />
						<s:set name="profitCommissionListVar" value="profitCommissionList"/>
						<s:set name="typeYearListVar" value="typeYearList"/>
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<s:if test="contNo != '' && contNo != null">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">											
											<div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox txtB">
													<s:property value="contNo"/>
												</div>
											</div>
											<s:if test="endorsmentno != '' &&  endorsmentno !='0'">
												<div class="textfield">
													<div class="text">
														<s:text name="label.endorsementType" />
													</div>
													<div class="tbox txtB">
														<s:property value="endorsmenttype"/>
														<s:hidden name="endorsmenttype" id="endorsmenttype"/>
													</div>
												</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="label.endorsementNo" />
												</div>
												<div class="tbox txtB">
													<s:property value="endorsmentno"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:if>
						<div class="tablerow">
							<s:hidden name="nr" value="0" />
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="footable">
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.proposalNo" />
												</td>
												<td width="16.66%">
													<s:property value="proposal_no"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.profitCentre" />
												</td>
												<td width="16.66%">
													<s:property value="profit_Center"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.subClass" />
												</td>
												<td width="16.66%">
													<s:property value="subProfit_center"/>
												</td>
											</tr>
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.cedingCompany" />
												</td>
												<td width="16.66%">
													<s:property value="cedingCo"/>
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.treatyNameType" />
												</td>
												<td width="16.66%">
													<s:property value="treatyName_type"/>
													<s:hidden name="treatyName_type" />
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.broker" />
												</td>
												<td width="16.66%">
													<s:property value="broker"/>
												</td>
											</tr>
											<tr>
												<td width="16.66%" class="txtB">
													<s:text name="label.uwYear" />
												</td>
												<td width="16.66%">
													<s:property value="uwYear"/>
													<s:hidden name="uwYear" />
												</td>
												<td width="16.66%" class="txtB">
													<s:text name="label.branchLocation" />
												</td>
												<td width="16.66%">
													<s:property value="polBr"/>
												</td>												
												<td width="16.66%" class="txtB"></td>
												<td width="16.66%"></td>
											</tr>
											</table>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="footable">
												<tbody>
												<tr>
													<s:if test='"1".equals(treatyType)  '>
														<td width="50%"><s:text name="label.premiumQuotaShare100OC"/></td>
														<td width="25%"><s:textfield cssClass="inputBox"
																					 name="premiumQuotaShare"
																					 cssStyle="text-align:right;"
																					 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this); javascript:this.value=Comma(this.value)"
																					 maxlength="30"/></td>
														<td width="25%"></td>

													</s:if>
													<s:elseif test='"2".equals(treatyType) '>
														<td width="50%"><s:text name="label.premiumSurplus100OC"/></td>
														<td width="25%"><s:textfield cssClass="inputBox"
																					 name="premiumSurplus"
																					 cssStyle="text-align:right;"
																					 readonly="true"/></td>
														<td width="25%"></td>

													</s:elseif>
													<s:elseif test='"3".equals(treatyType)  '>
														<td width="25%"><s:text name="label.premiumQuotaShare100OC"/></td>
														<td width="25%"><s:textfield cssClass="inputBox"
																					 name="premiumQuotaShare"
																					 cssStyle="text-align:right;"
																					 onblur="calculatePremium()"
																					 onkeyup="allow2DigitDecValues(this); middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																					 maxlength="30"/></td>
														<td width="25%"><s:text name="label.premiumSurplus100OC"/></td>
														<td width="25%"><s:textfield cssClass="inputBox"
																					 name="premiumSurplus"
																					 cssStyle="text-align:right;"
																					 readonly="true"/></td>
													</s:elseif>
													<s:else>
														<td width="25%"><s:text name="label.premium100OC"/></td>
														<td width="25%"><s:textfield cssClass="inputBox"
																					 name="premiumQuotaShare"
																					 cssStyle="text-align:right;"
																					 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this); javascript:this.value=Comma(this.value)"
																					 maxlength="30"/></td>
													</s:else>
												</tr>
												</tbody>
											</table>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							
							<s:hidden name="NoRetroCess" />
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="label.acquisitionCost"/>
									</div>
									<div class="panel-body">
										<div class="boxcontent">
										<div class="textfield">
										<div class="text">
											<s:text name="label.rate" />
										</div>
										<div class="tbox">
											<s:select list="lOCIssuesedList" listKey="DETAIL_NAME" listValue="REMARKS" name="locRate" id="locRate" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
										</div>
										</div>
										<div class="textfield">
										<div class="text">
											<s:text name="label.comType" />
										</div>
										<div class="tbox">
											<s:select list="CommissionList" listValue="DETAIL_NAME" listKey="TYPE" name="retroCommissionType" id="retroCommissionType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
										</div>
										</div>
										<s:if test='"1".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
													<div class="text">
														<s:text name="label.commissionQSP"/>
													</div>
													<div class="tbox">
														<s:textfield name="commissionQ_S" cssClass="inputBox"
																	 cssStyle="text-align:right;"
																	 onkeyup="middleMinusRestriction(this);checkDecimals(this)" onblur=""
																	 maxlength="8"/>
													</div>
											</div>
											</s:if>
											<s:if test='"1".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
												
													<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="commissionQ_SYN"
															 value="commissionQ_SYN==null?'Y':commissionQ_SYN"
															 ></s:radio>
													</div>
											</div>
											</s:if>
											<s:if test='"2".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
													<div class="text">
														<s:text name="label.commissionSurpP"/>
													</div>
													<div class="tbox">
														<s:textfield name="commission_surp" cssClass="inputBox"
																	 cssStyle="text-align:right;"
																	 onkeyup="middleMinusRestriction(this);checkDecimals(this)" onblur=""
																	 maxlength="8"/>
													</div>
											</div>
											</s:if>
											<s:if test='"2".equals(treatyType) || "3".equals(treatyType) '>
											<div class="textfield">
													<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="commission_surpYN"
															 value="commission_surpYN==null?'Y':commission_surpYN"
															 ></s:radio>
													</div>
											</div>
											</s:if>
											<div class="textfield">
												<div class="text">
													<s:text name="label.overriderP"/>
												</div>
												<div class="tbox">
													<s:textfield name="overRidder" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" onblur="" maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="overRidderYN"
															 value="overRidderYN==null||overRidderYN==''?'Y':overRidderYN"
															 ></s:radio>
													</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.brokerageP"/>
												</div>
												<div class="tbox">
													<s:if test="'DIRECT'.equalsIgnoreCase(br.toString().trim()))">
														<s:textfield name="brokerage" id="brokerageIDD"
																	 cssClass="inputBox" value="0"
																	 cssStyle="text-align:right;"
																	 onkeyup="middleMinusRestriction(this);checkDecimals10(this)" onblur=""
																	 maxlength="14"/>
													</s:if>
													<s:else>
														<s:textfield name="brokerage" id="brokerageIDD"
																	 cssClass="inputBox" cssStyle="text-align:right;"
																	 onkeyup="middleMinusRestriction(this);checkDecimals10(this)" onblur=""
																	 maxlength="14"/>
													</s:else>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="brokerageYN"
															 value="brokerageYN==null||brokerageYN==''?'Y':brokerageYN"
															 ></s:radio>
													</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.taxP"/>
												</div>
												<div class="tbox">
													<s:textfield name="tax" id="taxIDDD" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" value='%{"D".equals(profit_Center)?0.00:tax}' onblur="" maxlength="8" disabled='"D".equals(profit_Center)'/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="taxYN"
															 value="taxYN==null||taxYN==''?'Y':taxYN"
															 ></s:radio>
													</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.otherCostP"/>
												</div>
												<div class="tbox">
													<s:textfield name="othercost" id="CostOther" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" onblur="" maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
														<s:text name="label.includeOrg"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="othercostYN"
															 value="othercostYN==null||othercostYN==''?'Y':othercostYN"
															 ></s:radio>
													</div>
											</div>											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.premiumReserveP"/>
												</div>
												<div class="tbox">
													<s:textfield name="premium_Reserve" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onchange="GetPremiumReserveIntr()"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.lossReserveP"/>
												</div>
												<div class="tbox">
													<s:textfield name="loss_reserve" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onchange="GetPremiumReserveIntr()"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.interestP"/>
												</div>
												<div class="tbox">
													<s:textfield name="interest" id="interset" cssClass="inputBox"
																 cssStyle="text-align:right;" onblur="cleanCutPopUp()"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
														<s:text name="label.ceedODI"/>
													</div>
													<div class="tbox">
														<s:radio list="#{'Y':'Yes','N':'No'}" name="ceedODIYN"
															 value="ceedODIYN==null || ceedODIYN==''?'N':ceedODIYN"
															 ></s:radio>
													</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.portfolioinoutLossP"/>
												</div>
												<div class="tbox">
													<s:textfield name="portfolio_inout_Loss" id="InoutLoss"
																 cssClass="inputBox" cssStyle="text-align:right;"
																 onblur="cleanCutPopUp1()" onkeyup="middleMinusRestriction(this);checkDecimals(this)"
																 maxlength="8"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.portfolioinoutPremiumP"/>
												</div>
												<div class="tbox">
													<s:textfield name="portfolio_inout_Premium" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="middleMinusRestriction(this);checkDecimals(this)" onblur="" maxlength="8"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>

							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="Do you want fill Cresta Zone Details?"/>
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="crestaStatus"
															 value="crestaStatus==null||crestaStatus==''?'N':crestaStatus"
															 onchange="toggleCrestapopUp(this.value);"></s:radio>
												</div>
											</div>
											<div class="textfield">
												<div class="text" id="cresta" style="display:none">
													<a href="#" onclick="CrestapopUp()"><s:text
															name="label.enterDetails"/></a>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.scaleCommission"/>
												</div>
												<div class="tbox">
													<s:radio name="slideScaleCommission" list="#{'Y':'Yes','N':'No'}"
															 value="slideScaleCommission==null||slideScaleCommission==''?'N':slideScaleCommission"
															 onclick="funView(this.value)"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text" id="ssc" style="display:none">
													<s:if test='"Y"==slideEnable'>
														<a href="#" onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails"/></a>
													</s:if>
													<s:else>
														<a href="#" onclick="getPopUpDetails('scale','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails"/></a>
													</s:else>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.lossporticipents"/>
												</div>
												<div class="tbox">
													<s:radio name="lossParticipants" list="#{'Y':'Yes','N':'No'}"
															 value="lossParticipants==null||lossParticipants==''?'N':lossParticipants"
															 onclick="funLPView(this.value)"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text" id="lp" style="display:none">
													<s:if test='"Y"==lossParEnable'>
														<a href="#"
														   onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails"/></a>
													</s:if>
													<s:else>
														<a href="#"
														   onclick="getPopUpDetails('lossparticipates','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails"/></a>
													</s:else>
												</div>
											</div>
											
											<div class="textfield">
												<div class="text">
													<s:text name="label.profitCommission"/>
												</div>
												<div class="tbox">
													<s:radio name="share_Profit_Commission" list="#{'1':'Yes','2':'No'}"
															 value="share_Profit_Commission==null||share_Profit_Commission==''?'2':share_Profit_Commission"
															 onclick="ShareProfitCommission(this.value)"/>
												</div>
											</div>
											
											<br class="clear">
										</div>
									</div>
								</div>
							</div>
							<div class="boxcontent" id="pc" style="display:none">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">																						
											<div class="textfield">
												<div class="text">
													<s:text name="label.manexp" />
												</div>
												<div class="tbox">												
												<s:textfield name="managementExpenses" id="managementExpenses"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestriction(this);checkDecimals(this)" maxlength="26" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>	<br/>											
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.comtype" /> 
												</div>
												<div class="tbox">												
												<s:select  list="commissionTypeList"  name="commissionType" id="commissionType" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getCommissionField(this.value);getpopupenable()"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>
												</div>
											</div>
											<div class="textfield" id="supcom" style="display:none;">
												<div class="text">
													<s:text name="label.supprocom" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="superProfitCommission" id="superProfitCommission" value="(superProfitCommission==null || superProfitCommission=='') ?'N':superProfitCommission"  onchange="getpopupenable()"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio>
														<div  id="compoptype" style="display:none;">
															<s:if test='"Y"==profitCommissionEnable'>
																&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a><br/>	
															</s:if>
															<s:else>
																&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a><br/>	
															</s:else>
														</div>		
												</div>
											</div>
											
											<div class="textfield" id="comper" style="display:none;">
												<div class="text">
													<s:text name="label.procomper" /> 
												</div>
												<div class="tbox">												
													<s:textfield name="profitCommissionPer" id="profitCommissionPer"  onkeyup="checkDecimals(this);" cssClass="inputBox" cssStyle="text-align: right;" maxlength="26" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>		<br/>										
											</div>
											</div>
											<div class="textfield" id="setup" style="display:none;">
												<div class="text">
													<s:text name="label.stepup" />
												</div>
												<div class="tbox">												
													<s:radio list="#{'Y':'Yes','N':'No'}" name="setup" id="setup" value="(setup==null || setup=='')?'Y':setup"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio>
															<br/>										
												</div>
											</div>
											<div class="textfield" id="ratioPopUp" style="display:none;">
												<s:if test='"Y"==profitCommissionEnable'>
													<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','<s:property value="baseLayer"/>')"><s:text name="label.enterDetails" /></a><br/>	
												</s:if>
												<s:else>
													<a href="#" onclick="getPopUpDetails('profitCommission','<s:property value="contNo"/>','<s:property value="amendId"/>','<s:property value="proposal_no"/>','<s:property value="flag"/>','')"><s:text name="label.enterDetails" /></a><br/>	
												</s:else>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.lossCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="lossCarried" id="lossCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getLossYear(this.value)"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div id="lossyear" style="display:none;">
													<div class="text">
														<s:text name="label.lossperidyear" /> 
													</div>
													<div class="tbox">
														<s:textfield name="lossyear" id="lossyear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/>		<br/>										
													</div>
												</div>
											</div>
											<div class="textfield" ">
												<div class="text">
													<s:text name="label.profitCarFordType" /> 
												</div>
												<div class="tbox">												
												<s:select  list="typeYearListVar"  name="profitCarried" id="profitCarried" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  onchange="getProfitYear(this.value)"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div id="profityear" style="display:none">
													<div class="text">
														<s:text name="label.profitCarFordyear" /> 
													</div>
													<div class="tbox">												
															<s:textfield name="profitCarriedForYear" id="profitCarriedForYear"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
													</div>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.firstprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="fistpc" id="fistpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="profitMont" id="profitMont" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.subprofitcom" />
												</div>
												<div class="tbox">
														<s:textfield name="subpc" id="subpc"  cssClass="inputBox" cssStyle="text-align: right;" onkeyup="middleMinusRestrictionNeg(this);checkNumbers(this);" maxlength="10" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
												</div>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.period" />
												</div>
												<div class="tbox">
														<s:select  list="profitCommissionListVar"  name="subProfitMonth" id="subProfitMonth" cssClass="inputBox"   headerKey="" headerValue="---Select---" listKey="DETAIL_NAME"  listValue="REMARKS"  disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false' /><br/>
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.subseqcal" />
												</div>
												<div class="tbox">
													<s:radio list="#{'Y':'Yes','N':'No'}" name="subSeqCalculation" value="subSeqCalculation==null || subSeqCalculation==''?'N':subSeqCalculation"   disabled='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'></s:radio><br/>	
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
												</div>
												<div class="tbox">
												</div>
											</div>	
											<div class="textfield" >
												<div class="text">
													<s:text name="label.notes" />
												</div>
												<div class="tbox">
														<s:textarea rows="3" name="profitCommission" id="profitCommission" cssClass="inputBoxA" cssStyle="width: 90%;" readonly='"Y".equals(disableStatus1)||"Y"==profitCommissionEnable?true:false'/><br/>
													<br/>
												</div>
											</div>
											<br class="clear"/>
											</div>	
																				
												<br class="clear"/>
										</div>
									</div>
								</div>
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.lossAdvicePOC"/>
												</div>
												<div class="tbox">
													<s:textfield name="loss_Advise" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																 maxlength="30"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.cashLossPOC"/>
												</div>
												<div class="tbox">
													<s:textfield name="cash_Loss_Limit" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																 maxlength="30"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.eventLimit"/>
												</div>
												<div class="tbox">
													<s:textfield name="event_limit" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																 maxlength="30"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.aggregateLimit"/>
												</div>
												<div class="tbox">
													<s:textfield name="aggregate_Limit" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																 maxlength="30"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.OccurrentLimit"/>
												</div>
												<div class="tbox">
													<s:textfield name="occurrent_Limit" cssClass="inputBox"
																 cssStyle="text-align:right;"
																 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
																 maxlength="30"/>
												</div>
											</div>
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div>
							<s:if test='!"0".equals(noRetroCess)'>
							<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<table width="100%" class="footable">
												<thead>
												<tr>
													<th width="5%"> <s:text name="label.sNo" /> </th>
													<th width="15.83%"> <s:text name="label.retroName" /> </th>
													<th width="15.83%"> <s:text name="label.broker" /> </th>
													<th width="15.83%"> <s:text name="label.shareWrittenP" /> </th>
													<th width="15.83%"> <s:text name="label.proposalStatus" /> </th>
													<th width="15.83%"> <s:text name="label.shareSignedP" /> </th>
													<th width="15.83%"> <s:text name="label.overriderCommissionP" /> </th>

												</tr>
												</thead>
												<tbody>
												<s:iterator value="retroCessList" var="retroCess123" status="stat">
												<tr>
													<td style="text-align: center;">
														<s:property value='%{#stat.count}'/>
													</td>
													<td style="text-align: center;">
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCompany[%{#stat.count-1}]" id="cedingCompany[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													<td style="text-align: center;">
														<s:select list="brokerlist" listKey="CUSTOMER_ID" listValue="NAME" name="retroBroker[%{#stat.count-1}]" id="retroBroker[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
													</td>
													<td style="text-align: center;">
														<s:textfield name="shareAccepted[%{#stat.count-1}]" id="shareAccepted[%{#stat.count-1}]" onkeyup="middleMinusRestriction(this);checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8" />
													</td>
													<td style="text-align: center;">
														<s:select list="proposallist" listValue="DETAIL_NAME" listKey="TYPE" name="proposalStatus[%{#stat.count-1}]" id="proposalStatus[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="disableShareSigned('%{#stat.count-1}')"/>
													</td>
													<td style="text-align: center;">
														<s:textfield name="shareSigned[%{#stat.count-1}]" id="shareSigned[%{#stat.count-1}]" onkeyup="middleMinusRestriction(this);checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8" />
													</td>
													<td style="text-align: center;">
														<s:textfield name="commission[%{#stat.count-1}]" id="commission[%{#stat.count-1}]" onkeyup="middleMinusRestriction(this);checkDecimals(this)" cssStyle="text-align:right;" cssClass="inputBox" maxlength="8" />
													</td>

												</tr>
												</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							</s:if>
							<%--<div class="boxcontent">
								<div class="panel panel-primary">											
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderWriter" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
														<s:select list="underwriterList" listKey="CUSTOMER_ID" cssClass="inputBoxS"  listValue="NAME" name="leader_Underwriter" id="leader_Underwriter"   headerKey="" headerValue="---Select---"   onchange="getCountry(this.value,'country','');"/>
												</div>
											</div>
											<div class="textfield" >
												<div class="text">
													<s:text name="label.leadUnderwritterCountry" />  &nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox" id="country">
													
													<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  />													
												</div>
											</div>	
											<div class="textfield">
												<div class="text">
													<s:text name="label.leadUnderwritterShareP" />&nbsp; <sup style="color:red;">#</sup>
												</div>
												<div class="tbox">
													<s:textfield name="leader_Underwriter_share" id="leader_Underwriter_share" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals(this);negative(this.id,this.value);allowOneDot(this);hundredCheck(this.id,this.value);" maxlength="8" disabled="false"/>
												</div>
											</div>
											
											<br class="clear"/>
										</div>
									</div>
								</div>
							</div> --%>
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										&nbsp;&nbsp;&nbsp;
									</div>
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="label.exclusion"/>
											</div>
											<div class="textfieldA75">
												<s:textarea name="exclusion" id="exclusion" rows="3"
															cssClass="inputBoxA" cssStyle="width: 100%;"/>
												<br/>
												<span class="textAreaRemaining"><label id="exclusion_left"></label> &nbsp; <s:text
														name="Characters Remaining"/> </span>
											</div>
											<br class="clear"/>
										</div>
										<br/>
										<div class="boxcontent">
											<div class="textfieldA25">
												<s:text name="label.underwritersRecommendations"/>
											</div>
											<div class="textfieldA75">
												<s:textarea cssClass="inputBoxA" id="underwriter_Recommendations"
															name="underwriter_Recommendations" rows="3"
															cssStyle="width: 100%;"/>
												<br/>
												<span class="textAreaRemaining"><label
														id="underwriter_Recommendations_left"></label> &nbsp; <s:text
														name="Characters Remaining"/></span>
											</div>
											<br class="clear"/>
										</div>
										<br/>
									</div>
								</div>
							</div>
							<jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
							<s:if test="amend_Id_Mode != '' && amend_Id_Mode != null">
								<div class="boxcontent">
									<div class="panel panel-primary">
										<div class="panel-heading">
											&nbsp;&nbsp;&nbsp;
										</div>
										<div class="panel-body">
											<div class="boxcontent">
												<s:if test='!"N".equals(CeaseaccountStatus)'>
													<div class="textfield">
														<div class="text">
															<s:text name="label.ceaseStatus" />
														</div>
														<div class="tbox">
															<s:radio name="ceaseStatus" id="ceaseStatus" list="#{'Y':'Yes','N':'No'}" value="ceaseStatus==null ||ceaseStatus=''?'N':ceaseStatus" />
														</div>
													</div>
												</s:if>
												<s:else>
													<s:hidden name="ceaseStatus" id="ceaseStatusY"></s:hidden>
												</s:else>
												<%-- <div class="textfield">
													<div class="text">
														<s:text name="label.endorsementStatus" />
													</div>
													<div class="tbox">
														<s:radio name="endorsementStatus" id="endorsementStatus" list="#{'Y':'Yes','N':'No'}" value="endorsementStatus==null?'N':endorsementStatus" onclick="EndorsementScript(this.value)"/>
													</div>
												</div>

												<s:if test='"Y".equals(endorsementStatus)'>--%>
												<s:hidden name="endorsementStatus" id="endorsementStatus"/>
													<div class="textfield" id="endtlabel" style="display:inline;">
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
													<div class="textfield" id="doc1" style="display:inline;">
														<div class="text">
															<s:text name="label.docDetails" />
														</div>
														<div class="tbox">
															<s:radio name="docStatus" id="docStatus" list="#{'Y':'Yes','N':'No'}" value="docStatus==null || docStatus==''?'N':docStatus" onclick="DocScript(this.value)"/>
														</div>
													</div>
												
												<br class="clear"/>

												<br class="clear" />
												<div class="boxcontent" id="docView" style="display:none;">
													<div class="panel panel-primary">
														<div class="panel-heading">
															<s:text name="upload.heading.documentsupload" />
														</div>

														<div class="panel-body">
															<div class="boxcontent">
																<table class="footable" id="DocTable">
																	<thead>
																	<tr>
																		<th width="5%">
																			<s:text name="upload.docId" />
																		</th>
																		<th width="20%">
																			<s:text name="upload.docType" />
																		</th>
																		<th width="45%">
																			<s:text name="upload.docDesc" />
																		</th>
																		<th width="30%">
																			<s:text name="upload.selectdoc" />
																		</th>
																		<th width="30%">
																			<s:text name="Delete" />
																		</th>
																	</tr>
																	</thead>
																	<tbody>
																	<s:iterator value="docuList" id="index">
																		<tr>
																			<td class="formCon" style="text-align: center;">
																				<s:textfield name="docId[%{index}]" id="docId" cssClass="inputBox" value="%{#index+1}" readonly="true"/>
																			</td>
																			<s:if test='!"null".equals(docType)'>
																				<td class="formCon"  valign="top">
																					<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																				</td>
																			</s:if>
																			<s:else>
																				<td class="formCon"  valign="top">
																					<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
																				</td>
																			</s:else>
																			<td class="formCon" style="text-align: center;">
																				<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" cssClass="inputBox"/>
																			</td>
																			<td class="formCon">
																				<s:file name="upload"  cssClass="inputBox" id="upload"/>
																			</td>
																			<td class="formCon">
																				<s:if test='0!=(#stat.count-1)'>
																						<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteUpload('<s:property value="%{#stat.count-1}"/>')" />
																				</s:if>
																			</td>
																		</tr>
																	</s:iterator>
																	</tbody>
																</table>
															</div>
															<div class="boxcontent" align="right">
																<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="insRow('DocTable');" />
															</div>
														</div>
													</div>
												<s:hidden name="startIndex" id="startIndex"/>
												<s:hidden name="endIndex" id="endIndex" />
												</div>
												<br class="clear"/>
											</div>
										</div>
									</div>
								</div>
							</s:if>

						<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button" value="Previous" class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');FunctionBackMode()">							
								<s:if test="amend_Id_Mode == '' || amend_Id_Mode == null">
									<input type="button" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');CancelPage('%{proStatus}')" value="Cancel">									
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn btn-sm btn-info" onclick="disableForm(this.form,false,'');SavePage()" value="Save">
									<s:if test='"A".equals(proStatus)'>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');SubmitPage()">
									</s:if>
								</s:if>
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"  onClick="AmendIdBack()"/>
									<input type="button" value="Submit" class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');return oncheck()">
								</s:else>
							</div>
						</div>
						<s:hidden name="proStatus" id="proStatus"/>
						<s:hidden name="proposalno" id="proposalno" value="%{proposal_no}"/>
						<s:hidden name="baseLayer" value="%{baseLayer}" />
							<%--<s:hidden name="brokerage" value="%{brokerage}" />
                            <s:hidden name="tax" value="%{tax}" />--%>
						<s:hidden name="contarctno" value="%{contNo}" />
						<s:hidden name="contNo" id="contNo"/>
						<s:hidden name="treatyType" id="treatyType"/>
						<s:hidden name="proposalType" />
						<s:hidden name="broker" />
						<s:hidden name="gms_Approval" />
						<s:hidden name="layerNo" />
						<s:hidden name="incepDate" />
						<s:hidden name="expDate" />
						<s:hidden name="limitOrigCur" />
						<s:hidden name="proposal_no" id="proposal_no" />
						<s:hidden name="amend_Id_Mode" />
						<s:hidden name="baseLayer" id="baseLayer"/>
						<s:hidden name="renewal_contract_no" />
						<s:hidden name="renewalFlag" />
						<s:hidden name="orginalCurrency" id="orginalCurrency"/>
						<s:hidden name="layerProposalNo" id="layerProposalNo"/>

						<s:hidden name="instlmentperod" value="line" />
						<s:hidden name="contractno" />
						<s:hidden name="lay" />
						<s:hidden name="baseLoginID" />
						<s:hidden name="amendId" id="amendId"/>
						<s:hidden name="no_Insurer" />
						<s:hidden name="m_d_InstalmentNumber" />
						<s:hidden name="retroType" />
						<s:hidden name="mode" />
						<s:hidden name="proposalReference" id="proposalReference" />	
						<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>	
						<s:hidden name="proposalNo" id="proposalNo"/>
						<s:hidden name="profit_Center" id="profit_Center"/>
						<s:hidden name="renewalEditMode" id="renewalEditMode"/>
						 <s:hidden name="crestaPopUp" id="crestaPopUp"/>
                        <s:hidden name="slidePopUp" id="slidePopUp"/>
                        <s:hidden name="lossPopUp" id="lossPopUp"/>
                        <s:hidden name="profitPopUp" id="profitPopUp"/>
                         <s:hidden name="flag" id="flag"/>
                         <s:hidden name="RenewalMode" id="RenewalMode"/>
                         <s:hidden name="epi" id="epi"/>
                         <s:hidden name="exchRate" id="exchRate"/>
					</div>
										
					</s:form>
				</div>
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

<%-- 
<s:if test='amend_Id_Mode != "" && amend_Id_Mode != null'>
$(".disLink").click( function() {
	alert('Please Submit this Page');
 		return false;
});
var all = document.all;
for(var i=0; i<all.length; i++)
{
	if(all[i].type == 'button')
	{
		if(all[i].value!='Submit' && all[i].value!='Previous')
			document.all[i].disabled = true;
		
	}
}
</s:if>
--%>
function getRetroContract(uwYear,id) {
	var inceptionDate='<bean:write property="incepDate" name="RiskDetailsActionForm"/>';
	var product='<bean:write name="Product" />';
	if(uwYear!='0') {
		if (window.XMLHttpRequest) {
			xmlhttp=new XMLHttpRequest();
		} 	
		else {
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
	 	 		var a=xmlhttp.responseText.split("~");
		 		var j=0;
		 		var opt = document.getElementById("cedingCompany"+id).options.length=0;
				var opt = document.createElement("option");
		 		opt.value ='0';
		 		opt.text = '--Select--';
		 		document.getElementById("cedingCompany"+id).options.add(opt);
		 		while(j<a.length) {	
					opt = document.createElement("option");
					opt.value = a[j];
					j++;
					opt.text = a[j];
				    document.getElementById("cedingCompany"+id).options.add(opt);
			    	j++;
	   			}         
			}
		}
		xmlhttp.open("POST",'<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=getRetroContractDetails&inceptionDate='+inceptionDate+'&productCode='+product+'&uwYear='+uwYear,true);
		xmlhttp.send();
	}
}

function SubmitPage()
{
   /* var base = document.getElementById("baseLayer").value;
    if (base == null || base == "") {
        answer = confirm("All the changes made in # marked fields will be updated in all class linked to this base proposal.Do you wish to continue.");
        if (answer) {*/
        	destroyPopUps();
            replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
            document.retro2.action = "RiskSecondPageRetro.action";
            document.retro2.submit();
       /* } else
            return false;
    } else {
        replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,commissionQ_SAmt,commission_surpAmt,acqCostPer,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
        document.retro2.action = "RiskSecondPageRetro.action";
        document.retro2.submit();
    }*/
}
        
function Editmode()
{
    replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.retro2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=SecondPageUpdate';
	document.retro2.submit();
}

function SavePage() {
    /*var base = document.getElementById("baseLayer").value;
    if (base == null || base == "") {
        answer = confirm("All the changes made in # marked fields will be updated in all class linked to this base proposal.Do you wish to continue.");
        if (answer) {*/
        	destroyPopUps();
            replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
            document.retro2.action = "SecondPageSaveRetro.action";
            document.retro2.submit();
       /* } else
            return false;

    } else {
        replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,commissionQ_SAmt,commission_surpAmt,acqCostPer,acquisition_Cost,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit,orginalacqcost,ourassessmentorginalacqcost,ouracqCost');
        document.retro2.action = "SecondPageSaveRetro.action";
        document.retro2.submit();
    }*/
}
function AmednIdSubmitPage() {
    replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.retro2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdInsertSecondPage';
	document.retro2.submit();
}
	    
function AmednIdSavePage() {
    replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
	document.retro2.action='<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdSavePage';
	document.retro2.submit();
}

/*
function CalculateValue() {
	var commisson='';
	var	epiAsPerShare=document.retro2.epiAsPerShare.value;
	var coQs=document.retro2.commissionQ_S.value;
	if(coQs==null || coQs=="")
		coQs=0;
	var coSur=document.retro2.commission_surp.value;
	if(coSur==null || coSur=="")
		coSur=0;   	 	
	var tax=document.getElementById("taxIDDD").value;
    if(tax==null || tax=="")
		tax=0;
	var brokerage=document.getElementById("brokerageIDD").value;
    if(brokerage==null || brokerage=="")
		brokerage=0;
	var overidden=document.retro2.overRidder.value;
    if(overidden==null || overidden=="")
		overidden=0;
	var otherCost=document.retro2.othercost.value;
    if(otherCost==null || otherCost=="")
		otherCost=0;
	var PQS=document.retro2.premiumQuotaShare.value;
    if(PQS==null || PQS=="")
		PQS=0;
	else
		PQS=PQS.replace(new RegExp(',', 'g'),'');					
	var PSur=document.retro2.premiumSurplus.value;
    if(PSur==null || PSur=="")
		PSur=0;
	else
		PSur=PSur.replace(new RegExp(',', 'g'),'');
	var QSAmt=(parseFloat(coQs)*parseFloat(PQS))/100;
	var SurpAmt=(parseFloat(coSur)*parseFloat(PSur))/100;
	document.retro2.commissionQ_SAmt.value=Comma(QSAmt.toFixed(2));
	document.retro2.commission_surpAmt.value=Comma(SurpAmt.toFixed(2));		
	var value=(parseFloat(commisson) * parseFloat(epiAsPerShare));
	var per=(parseFloat(brokerage)+parseFloat(tax)+parseFloat(overidden)+parseFloat(otherCost))/100;
	var otheracqcostAmt=(per*parseFloat(epiAsPerShare));
	document.retro2.acqCostPer.value=Comma(otheracqcostAmt.toFixed(2));
	commisson=parseFloat(QSAmt)+parseFloat(SurpAmt)+parseFloat(otheracqcostAmt);
	document.retro2.acquisition_Cost.value=Comma(commisson.toFixed(2));
     				 
}
*/

function CancelPage(flag) {
destroyPopUps();
	var val = document.getElementById("proposalReference").value;
	var no =document.getElementById("renewalProposalNo").value;
	var out =document.getElementById("proposalNo").value;
	if('Renewal'==val){
		answer = confirm("This action will remove the newly created proposal number "+out+" which cannot be reused.  Do you wish to continue?");
		if(answer){
			document.getElementById("renewalProposalNo").value =out;
			document.getElementById("proposalNo").value = no;
			document.retro2.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.retro2.submit();
			}
			else {
				 return false; 
			}
		}
		else{
			var param='';
	if(flag=="N")
	{
		//param='&flag=N'
	}
	//document.location='${pageContext.request.contextPath}/menu.do?method=menu&manufactureID='+<%=session.getAttribute("mfrid")%>+'param';
	//document.location="${pageContext.request.contextPath}/menuPortfolio.do?proposalNo="+out+"&manufactureID="+<%=session.getAttribute("mfrid")%>+param;
	document.retro2.action="${pageContext.request.contextPath}/menuPortfolio?proposalNo="+out+"&manufactureID="+<%=session.getAttribute("mfrid")%>;
	document.retro2.submit();
	}	
}

function FunctionBackMode() {
destroyPopUps();
	document.retro2.action="EditModeRetro.action?multiuserMode=edit";
	document.retro2.submit();
}

function ShareProfitCommission(value,proId) {
	if(proId=='2') {
		document.retro2.share_Profit_Commission.value=value;
		if(value=="1") {
			document.getElementById("Mangment").readOnly=false;
			document.getElementById("loss").readOnly=false;
			document.retro2.profit_commission.readOnly=false;
   		}
    	else if(value=="2")
    	{
			document.getElementById("Mangment").readOnly=true;
			document.getElementById("loss").readOnly=true;
			document.retro2.profit_commission.readOnly=true;
			document.retro2.management_Expenses.value='';
			document.retro2.lossC_F.value='';
			document.retro2.profit_commission.value='';
    	}
	}
}

function cleanCutPopUp() {
	if(document.retro2.proposalType.value=="R")
	{
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Loss %?","Yes","No");
		if(doIt) {
			document.retro2.portfolio_inout_Loss.readOnly=false;
			document.retro2.portfolio_inout_Loss.focus();
		} else {
			document.retro2.portfolio_inout_Loss.value='0';
  			document.retro2.portfolio_inout_Loss.readOnly=true;
  			cleanCutPopUp1();
		}
	}
}

function cleanCutPopUp1() {
	if(document.retro2.proposalType.value=="R") { 
		doIt=confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Premium %?","Yes","No");
		if(doIt) {
			document.retro2.portfolio_inout_Premium.readOnly=false;
			document.retro2.portfolio_inout_Premium.focus();
		} else {
			document.retro2.portfolio_inout_Premium.value='0';
			document.retro2.portfolio_inout_Premium.readOnly=true;
			document.retro2.loss_Advise.focus();
		}
	}
}

/*function PremiumQuotaShare1() {
	var share=document.retro2.premiumQuotaShare.value;
	share=share.replace(new RegExp(',', 'g'),'');
	var Epi=document.retro2.epiAsPerShare.value;
	Epi=Epi.replace(new RegExp(',', 'g'),'');
	var val=Epi-share;
	if(share<0) {
		alert('Premium Quota Share Should not less than 0');
		document.retro2.premiumQuotaShare.value='';
		document.retro2.premiumSurplus.value='';
	}else if(val<0) {
	 	alert('Premium Surplus Should not less than 0');
	 	document.retro2.premiumQuotaShare.value='';
	 	document.retro2.premiumSurplus.value='';
 	}else{
 		document.retro2.premiumSurplus.value=Comma(Math.round(val*1000)/1000);
	}
	if(share=='0')
	{
		document.retro2.commissionQ_S.value='0';
		document.retro2.commissionQ_S.readOnly=true;
	}else
	{
		document.retro2.commissionQ_S.readOnly=false;
	}
	if(val==0)
	{
		document.retro2.commission_surp.value='0';
		document.retro2.commission_surp.readOnly=true;
	}else
	{
	 document.retro2.commission_surp.readOnly=false;
	}
	CalculateValue();
}

function PremiumQuotaShare() {
	var share=document.retro2.premiumQuotaShare.value;
	var surplus=document.retro2.premiumSurplus.value;
	var Epi=document.retro2.epiAsPerShare.value;
	if(Epi==share) {
		document.retro2.premiumSurplus.readOnly=true;
		document.retro2.commission_surp.readOnly=true;
		document.retro2.premiumSurplus.value='0'
		document.retro2.commission_surp.value='0'
		document.retro2.commissionQ_S.readOnly=false;
		document.retro2.premiumQuotaShare.readOnly=false;
	} else if(Epi==surplus) {
		document.retro2.premiumSurplus.readOnly=false;
		document.retro2.commission_surp.readOnly=false;
		document.retro2.commissionQ_S.value='0'
		document.retro2.premiumQuotaShare.value='0'
		document.retro2.commissionQ_S.readOnly=true;
		document.retro2.premiumQuotaShare.readOnly=true;
	} else {
		document.retro2.premiumSurplus.readOnly=false;
		document.retro2.commission_surp.readOnly=false;
		document.retro2.commissionQ_S.readOnly=false;
		document.retro2.premiumQuotaShare.readOnly=false;
	}
	
	if(surplus!="" &&  share!="" ) {
		var c=parseFloat(share)+parseFloat(surplus);
		if(c > parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.retro2.premiumQuotaShare.value='';
			document.retro2.premiumSurplus.value='';
		}
		else if(c < parseFloat(Epi)) {
			document.getElementById("error").style.display='inline';
			document.retro2.premiumQuotaShare.value='';
			document.retro2.premiumSurplus.value='';
		}
		else {
			document.getElementById("error").style.display='none';
		}
	}
}
*/
function GetPremiumReserveIntr() {
	if(document.retro2.loss_reserve.value=='0'  && document.retro2.premium_Reserve.value=='0') {
		document.retro2.interest.value='0';
		document.getElementById("interset").readOnly=true;
	}
	else {
		document.retro2.interest.value="";
		document.getElementById("interset").readOnly=false;
	}
}

function GetReinstatmentPremium(value) {
	var a=document.retro2.epiAsPerOffer.value;
	value=value.replace(new RegExp(',', 'g'),'');
	if(a!=null && a!="" && value!=null && value!="" && value!=0) {  
		var c=(a*value)/100;
		document.retro2.reinstAdditionalPremium.value=Comma(c.toFixed(2));
	}
	else {
		document.retro2.reinstAdditionalPremium.value='0.00'
	}
}

function GetAcqCost() {
	var md=document.retro2.md_premium_our_service.value;
	var brok=document.getElementById("BrokeragerValue").value;
	var tax=document.getElementById("TaxValue").value;
	var CostOther=document.getElementById("CostOther").value;
	if( document.getElementById("BrokeragerValue").value!="" && document.getElementById("TaxValue").value !=""  && document.getElementById("CostOther").value != "") {
		var c=parseFloat(md)*((parseFloat(brok)+parseFloat(tax)+parseFloat(CostOther))/100);
		document.retro2.acquisition_Cost.value=Comma(c.toFixed(2));
	}
	else {
		document.retro2.acquisition_Cost.value=''
	}
}
function EndorsementScript(value) {
		if(value=="Y") {
			document.getElementById('endtlabel').style.display='block';
            document.getElementById('doc1').style.display='block';
            document.retro2.docStatus.value= 'N';
        }
		else if(value=="N") {
			document.getElementById('endtlabel').style.display='none';
            document.getElementById('doc1').style.display='none';
            document.getElementById('docView').style.display='none';
        }
	}
function endorsementPage() {
    if (document.getElementById("ceaseStatusY").checked ) {
        answer = confirm("You have choosen cease account for this treaty.This action will restrict change Premium and Claim form this treaty.Do you wish to continue?");
        if (answer) {
            replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
            document.retro2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementRetro.action";
            document.retro2.submit();
        }
        else
            return false;
    }
    else {
        replaceComma(document.retro2, 'premiumQuotaShare,premiumSurplus,loss_Advise,cash_Loss_Limit,event_limit,aggregate_Limit,occurrent_Limit');
        document.retro2.action = "${pageContext.request.contextPath}/submitSecondPageEndorsementRetro.action";
        document.retro2.submit();
    }

}
function oncheck(){
      <%--if(document.getElementById("endorsementStatusN").checked){
        	answer=confirm("The changes have not been captured in the system.  Do you wish to exit without saving?  If you wish to update the record, kindly click on cancel and choose “Yes” under the Endorsement finalised field.");
     	if(answer)
     	endorsementPage();
     	else
     	return false;
     	}
    else --%>
    destroyPopUps();
    document.getElementById("endorsementStatus").value='Y';
    endorsementPage();  
}
<%--$(function() {
		   var max = 2000;
    $('#remarks').keypress(function(e) {
    
        if (e.which < 0x20) {
            return;     // Do nothing
        }
        if (this.value.length == max) {
            e.preventDefault();
            alert("l"+this.value.length);
        } else if (this.value.length > max) {
            // Maximum exceeded
            this.value = this.value.substring(0, max);
            alert(this.value);
        }
    });  	
	    $('#remarks').keyup(function() {
	        update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
	    });	    
	    update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
	    
	});--%>

	function update_chars_left(max_len, target_input, display_element) {
	   var text_len = target_input.value.length;
	   if (text_len >= max_len) {
	       target_input.value = target_input.value.substring(0, max_len); // truncate
	       display_element.html("0");
	   } else {
	       display_element.html(max_len - text_len);
	   }
	}
	toggleCrestapopUp('<s:property value="crestaStatus"/>');
function toggleCrestapopUp(value) {
    if (value == 'Y') {
        document.getElementById('cresta').style.display = 'block';
    } else if (value == 'N') {
        document.getElementById('cresta').style.display = 'none';
    }
}

String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

function CrestapopUp() {
    var contractNo = document.getElementById("contNo").value.replaceAll(',', '').trim();
    var proposalno = document.getElementById("proposalno").value.replaceAll(',', '').trim();
    var amendId = document.getElementById("amendId").value.replaceAll(',', '').trim();
    var cur = document.getElementById("orginalCurrency").value.replaceAll(',', '').trim();
    var layerProposalNo = document.getElementById("layerProposalNo").value.replaceAll(',', '').trim();
 	document.getElementById("crestaPopUp").value = "Y";
    var URL = '${pageContext.request.contextPath}/crestaPopUpRiskDetails.action?contractNo=' + contractNo + '&proposal_no=' + proposalno + '&amendId=' + amendId + '&orginalCurrency=' + cur + '&layerProposalNo=' + layerProposalNo;
    var windowName = "Details";
    var width = screen.availWidth;
    var height = screen.availHeight;
    var w = 800;
    var h = 400;
    var features =
        'width=' + w +
        ',height=' + h +
        ',left=' + ((width - w - 10) * .5) +
        ',top=' + ((height - h - 30) * .5) +
        ',directories=no' +
        ',location=no' +
        ',menubar=no' +
        ',scrollbars=yes' +
        ',status=no' +
        ',toolbar=no' +
        ',resizable=no';
        
    var strOpen = window.open(URL, windowName, features);
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
}

funView('<s:property value="slideScaleCommission"/>');
function funView(id) {
    if (id == "Y") {
        document.getElementById('ssc').style.display = 'block';
    }
    else if (id == "N") {
        document.getElementById('ssc').style.display = 'none';
    }
}
funLPView('<s:property value="lossParticipants"/>');
function funLPView(id) {
    if (id == "Y") {
        document.getElementById('lp').style.display = 'block';
    }
    else if (id == "N") {
        document.getElementById('lp').style.display = 'none';
    }
}

function getPopUpDetails(pageFor, contractNo, endorsmentno, proposalNo, flag, renewalProposalNo) {
    pageFor = pageFor.replaceAll(',', '').trim();
    contractNo = contractNo.replaceAll(',', '').trim();
    endorsmentno = endorsmentno.replaceAll(',', '').trim();
    proposalNo = proposalNo.replaceAll(',', '').trim();
    flag = flag.replaceAll(',', '').trim();
    renewalProposalNo = renewalProposalNo.replaceAll(',', '').trim();
 if("scale"==pageFor){
	  document.getElementById("slidePopUp").value = "Y";
	 }else if("lossparticipates"==pageFor){
	  document.getElementById("lossPopUp").value = "Y";
	 }else if("profitCommission"==pageFor){
	  document.getElementById("profitPopUp").value = "Y";
	 }
    var type = document.getElementById("commissionType").value;
    var URL = "${pageContext.request.contextPath}/viewScaleCommissionPopUpRiskDetails.action?pageFor=" + pageFor + "&proposal_no=" + proposalNo + "&amendId=" + endorsmentno + "&contractNo=" + contractNo + "&flag=" + flag + "&layerProposalNo=" + renewalProposalNo + "&commissionType=" + type;
    var windowName = "Details";
    var width = screen.availWidth;
    var height = screen.availHeight;
    var w = 800;
    var h = 400;
    var features =
        'width=' + w +
        ',height=' + h +
        ',left=' + ((width - w - 10) * .5) +
        ',top=' + ((height - h - 30) * .5) +
        ',directories=no' +
        ',location=no' +
        ',menubar=no' +
        ',scrollbars=yes' +
        ',status=no' +
        ',toolbar=no' +
        ',resizable=no';
    var strOpen = window.open(URL, windowName, features);
    mtbChildWin[mtbWinCound] = strOpen;
	mtbWinCound++;
    strOpen.focus();
    return false;
}
getLossYear('<s:property value="lossCarried"/>');
function getLossYear(id) {
    if (id!= "TE") {
        document.getElementById('lossyear').style.display = 'block';
    }
    else {
        document.getElementById('lossyear').style.display = 'none';
    }
}
getProfitYear('<s:property value="profitCarried"/>');
function getProfitYear(id) {
    if (id!= "TE") {
        document.getElementById('profityear').style.display = 'block';
    }
    else {
        document.getElementById('profityear').style.display = 'none';
    }
}

ShareProfitCommission('<s:property value="share_Profit_Commission"/>');
function ShareProfitCommission(value) {
    if (value == '1') {
        document.getElementById('pc').style.display = 'block';
    } else if (value == '2') {
        document.getElementById('pc').style.display = 'none';
    }
}

function cleanCutPopUp() {
    if (document.retro2.proposalType.value == "R") {
        doIt = confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Loss %?", "Yes", "No");
        if (doIt) {
            document.retro2.portfolio_inout_Loss.readOnly = false;
            document.retro2.portfolio_inout_Loss.focus();
        } else {
            document.retro2.portfolio_inout_Loss.value = '0';
            document.retro2.portfolio_inout_Loss.readOnly = true;
            cleanCutPopUp1();
        }
    }
}

function cleanCutPopUp1() {
    if (document.retro2.proposalType.value == "R") {
        doIt = confirm("Proposal Type Run Off Do you want to Enter Portfolio in/out Premium %?", "Yes", "No");
        if (doIt) {
            document.retro2.portfolio_inout_Premium.readOnly = false;
            document.retro2.portfolio_inout_Premium.focus();
        } else {
            document.retro2.portfolio_inout_Premium.value = '0';
            document.retro2.portfolio_inout_Premium.readOnly = true;
            document.retro2.loss_Advise.focus();
        }
    }
}
getCommissionField('<s:property value="commissionType"/>');
function getCommissionField(id) {
    if (id == "PR" || id == "LR") {
        document.getElementById('comper').style.display = 'none';
        document.getElementById('supcom').style.display = 'none';
        document.getElementById('setup').style.display = 'block';
    }

    else if (id == "PC") {
        document.getElementById('comper').style.display = 'block';
        document.getElementById('supcom').style.display = 'block';
        document.getElementById('setup').style.display = 'none';
    }
}

<%--getpopupenable();
function getpopupenable() {
    var id = document.getElementById('commissionType').value;
    var slide = "";
    if (id == "PC") {
        slide = document.retro2.superProfitCommission.value;
    }
    if (id == "PR") {
        document.getElementById('compoptype').style.display = 'inline';
    }
    else if (id == "LR") {
        document.getElementById('compoptype').style.display = 'inline';
    }
    else if (id == "PC" && slide == "Y") {
        document.getElementById('compoptype').style.display = 'inline';
    }
    else {
        document.getElementById('compoptype').style.display = 'none';
    }
}--%>

$(function () {

    $('#exclusion').keyup(function () {
        update_chars_left(2000, $('#exclusion')[0], $('#exclusion_left'));
    });
    update_chars_left(2000, $('#exclusion')[0], $('#exclusion_left'));

    //set up text length counter
    //$('#remarks').keyup(function () {
     //   update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
    //});
    //and fire it on doc ready, too
    //update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
    exclusion

    $('#underwriter_Recommendations').keyup(function () {
        update_chars_left(2000, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
    });
    update_chars_left(2000, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));


    $('#profitCommission').keyup(function () {
        update_chars_left(2000, $('#profitCommission')[0], $('#profitCommission_left'));
    });
    update_chars_left(2000, $('#profitCommission')[0], $('#profitCommission_left'));


});

$(function () {

    $('#exclusion').keyup(function () {
        update_chars_left(2000, $('#exclusion')[0], $('#exclusion_left'));
    });
    update_chars_left(2000, $('#exclusion')[0], $('#exclusion_left'));

    //set up text length counter
  <%--  $('#remarks').keyup(function () {
        update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
    });
    //and fire it on doc ready, too
    update_chars_left(2000, $('#remarks')[0], $('#remarks_left'));
    exclusion--%>

    $('#underwriter_Recommendations').keyup(function () {
        update_chars_left(2000, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));
    });
    update_chars_left(2000, $('#underwriter_Recommendations')[0], $('#underwriter_Recommendations_left'));


    $('#profitCommission').keyup(function () {
        update_chars_left(2000, $('#profitCommission')[0], $('#profitCommission_left'));
    });
    update_chars_left(2000, $('#profitCommission')[0], $('#profitCommission_left'));


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

function calculatePremium() {
var treatyType = document.getElementById("treatyType").value;
    if ('3' == treatyType) {
        document.retro2.premiumSurplus.value=0;
        var tempEPI = document.getElementById("epi").value;
        tempEPI=tempEPI.replaceAll("," , "");
        tempPremiumQuotaShare = document.retro2.premiumQuotaShare.value.replaceAll("," , "");
        if (tempEPI - tempPremiumQuotaShare > 0) {
        var val = 
            document.retro2.premiumSurplus.value = (tempEPI - tempPremiumQuotaShare);
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
        } else if (tempEPI - tempPremiumQuotaShare <= 0) {
            document.retro2.premiumSurplus.value = 0;
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
            //alert('Premium Surplus Should not less than 0 or Empty');
        }
        document.retro2.premiumSurplus.readOnly = true;
    }

  <%-- if ('3' == '<s:property value="treatyType"/>' && 'Y' == '<s:property value="pml"/>') {
        document.retro2.premiumSurplus.value=0;
        var tempEPIPML = '<s:property value="epi"/>'.replaceAll("," , "");
        tempPremiumQuotaShare = document.retro2.premiumQuotaShare.value.replaceAll("," , "");
        if (tempEPIPML - tempPremiumQuotaShare > 0) {
            document.retro2.premiumSurplus.value = (tempEPIPML - tempPremiumQuotaShare);
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
        } else {
            document.retro2.premiumSurplus.value = (tempEPIPML - tempPremiumQuotaShare);
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
            alert('Premium Surplus Should not less than 0 or Empty');
        }
        document.retro2.premiumSurplus.readOnly = true;
    }--%>
}
function loadPremiumOCDetails() {
   <%-- if(document.retro2.premiumSurplus.value==='' || document.retro2.premiumSurplus.value==null){
        document.retro2.premiumSurplus.value=0;
	}

    if(document.retro2.premiumQuotaShare.value==='' || document.retro2.premiumQuotaShare.value==null){
        document.retro2.premiumQuotaShare.value=0;
    }--%>
    var treatyType = document.getElementById("treatyType").value;
    var val ='';
    var premiumQuotaShare ='';
    if ('1' == treatyType || '3' == treatyType) {
	 premiumQuotaShare=document.retro2.premiumQuotaShare.value;
	}
	if(''==premiumQuotaShare){
    if ('1' == treatyType) {
        document.retro2.premiumQuotaShare.value = document.getElementById("epi").value;
        val = document.retro2.premiumQuotaShare.value;
        document.retro2.premiumQuotaShare.value=Comma(parseFloat(val).toFixed(2));
        document.retro2.premiumQuotaShare.readOnly = true;
    }
    else if ('2' == treatyType ) {
        document.retro2.premiumSurplus.value =document.getElementById("epi").value;
        val = document.retro2.premiumSurplus.value;
        document.retro2.premiumSurplus.value=Comma(parseFloat(val).toFixed(2));
        document.retro2.premiumSurplus.readOnly = true;
    }
    else if ( '3' == treatyType) {
         document.retro2.premiumQuotaShare.value =document.getElementById("epi").value;
    }
    else {
    	document.retro2.premiumQuotaShare.value = document.getElementById("epi").value;
    	val = document.retro2.premiumQuotaShare.value;
    	document.retro2.premiumQuotaShare.value=Comma(parseFloat(val).toFixed(2));
        document.retro2.premiumQuotaShare.readOnly = true;
    }
    
    calculatePremium();
    for (var i = 0; i <<s:property value="noRetroCess"/>; i++) {
        disableShareSigned(i)
    }
    }
}
function Commas(value) {

    if(value=="2"||value=="3"||value=="4") {
        if('1'=='<s:property value="treatyType"/>'){
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
        }
        else if('2'=='<s:property value="treatyType"/>'){
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
        }
        else if('3'=='<s:property value="treatyType"/>'){
            document.retro2.premiumQuotaShare.value=Comma(document.retro2.premiumQuotaShare.value);
            document.retro2.premiumSurplus.value=Comma(document.retro2.premiumSurplus.value);
        }
        document.retro2.loss_Advise.value=Comma(document.retro2.loss_Advise.value);
        document.retro2.cash_Loss_Limit.value=Comma(document.retro2.cash_Loss_Limit.value);
        document.retro2.event_limit.value=Comma(document.retro2.event_limit.value);
        document.retro2.aggregate_Limit.value=Comma(document.retro2.aggregate_Limit.value);
        document.retro2.occurrent_Limit.value=Comma(document.retro2.occurrent_Limit.value);
    }
}

function disableShareSigned(val) {
    if (document.getElementById('proposalStatus[' + val + ']').value !== 'A') {
        document.getElementById('shareSigned[' + val + ']').setAttribute("readonly", "true")
        document.getElementById('shareSigned[' + val + ']').value = '';
    }
    if (document.getElementById('proposalStatus[' + val + ']').value === 'A') {
        document.getElementById('shareSigned[' + val + ']').removeAttribute("readonly")
    }
}
DocScript('<s:property value="docStatus"/>');
function DocScript(id){
    var amendId = document.getElementById("amendId").value;
    if (amendId > 0) {
        document.retro2.docStatus.value = id;
        if ("Y" == id) {
            document.getElementById('docView').style.display = 'block';
        }
        else {
            document.getElementById('docView').style.display = 'none';
        }
    }
}


function insRow(tableID)
{
    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var cell1 = row.insertCell(0)

    var element1 = document.createElement("input");
    element1.type = "text";
    element1.name = "docId["+(rowCount-1)+"]";
    element1.id = "docId["+(rowCount-1)+"]";
    element1.value = rowCount;
    element1.className = "inputBox";
    //element1.innerHTML = rowCount;
    element1.setAttribute("readonly",true);
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    createdoc(cell2, rowCount)



    var cell3 = row.insertCell(2);
    var element3 = document.createElement("textarea");
    //element3.type = "textarea";
    element3.name = "docDesc["+(rowCount-1)+"]";
    element3.id = "docDesc["+(rowCount-1)+"]";
    element3.className = "inputBox";
    //element7.value="";
    element3.setAttribute("rows", "2");
    element3.setAttribute("cols", "70");
    cell3.appendChild(element3);

    var cell4 = row.insertCell(3);
    var element4 = document.createElement("input");
    element4.type = "file";
    element4.name = "upload";
    element4.id = "upload";
    element4.className = "inputBox";
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(4);
	var element5 = document.createElement("input");
	element5.type = "button";
	element5.value="Delete";
	element5.setAttribute("onclick", "deleteUpload('"+(rowCount-1)+"')");
	element5.className="btn btn-sm btn-danger"
	cell5.appendChild(element5);
			
    document.getElementById("endIndex").value = (parseInt(rowCount));

}

function createdoc(cell, rowCount){
    element = document.createElement("select");
    element.name = "docTypeId["+(rowCount-1)+"]";
    element.id = "docTypeId["+(rowCount-1)+"]";
    element.className = "inputBox";
    populatedoc(element);
    cell.appendChild(element);
}


function populatedoc(objSelect){
    var objOption = document.createElement("option");
    objOption.text = '---Select---';
    objOption.value = '';
    if(document.all && !window.opera){
        objSelect.add(objOption);
    }else{
        objSelect.add(objOption, null);
    }
    <s:iterator value='docType'>
    var objOption = document.createElement("option");
    objOption.text = "<s:property value='DOC_NAME' />".replace("&amp;", "&");
    objOption.value = "<s:property value='DOC_TYPE' />";
    if(document.all && !window.opera){
        element.add(objOption);
    }else{
        element.add(objOption, null);
    }
    </s:iterator>
}
function AmendIdBack(){
	destroyPopUps();
	document.getElementById("mybutton").style.display = "none";
	var no = document.getElementById("proposal_no").value;
	//document.getElementById("endorsementStatusY").disabled = true;
	//document.getElementById("endorsementStatusN").disabled = true;
	document.getElementById("endorsementStatus").value='N';
	document.retro2.action ="${pageContext.request.contextPath}/InitPortfolio.do?proposalNo="+no+"&endtMode=endorsment&endorsementStatus=N";
	document.retro2.submit();
}
//getCountry('<s:property value="leader_Underwriter"/>','country','<s:property value="leader_Underwriter_country"/>');
function getCountry(value,id,leader_Underwriter_country){
	var URL='${pageContext.request.contextPath}/ajaxValueRiskDetails.action?leader_Underwriter='+value+'&dropDown=country&leader_Underwriter_country='+leader_Underwriter_country;
	postRequest(URL,'country');
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
getpopupenable();
function getpopupenable(){
		var id = document.getElementById('commissionType').value;
		var slide ="";
		if(id=="PC"){
		 slide = document.retro2.superProfitCommission.value;
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
	
			
function deleteUpload(id){
if(confirm('This row will be deleted. Do You Want to continue?')){
	postFormRequest('${pageContext.request.contextPath}/newDocDeleteRetro.action?mode=delete&deleteId='+id+'&dropDown=docView', "docView", "retro2");
}
else { 
		return false;
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


</script>		
</body>
</html>
