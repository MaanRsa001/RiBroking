<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 

<!DOCTYPE HTML>
<html>
<head>
	<style>
		.cols25 {
			width: 25%;
			float: left;
		}
		
		.textfield {
			height: 25px;
		}
		  
	</style>
	<style type="text/css">
	 @media print
			{    
			    .no-print, .no-print *
			    {
			        display: none !important;
			        
			    }
			    .ui-datepicker-trigger { display:none; }
			}
		
	</style>
	<style type="text/css">
		body{
		  -webkit-print-color-adjust:exact;
		}
		@page 
        {
            size: legal;   /* auto is the current printer page size */
            margin:0cm;
        }
		@media print { html, body { height: 99%; } }        
	</style>	
</head>  
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="claims" action="" theme="simple">
					
					<div class="tablerow">				
				<div class="boxcontent">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<s:text name="heading.contractDetails" />
							<s:if test="claim_No != '' && claim_No != null">
							<span class="pullRight">								
								<s:text name="Claim.ClaimNo" />								
								:&nbsp;<s:property value="claim_No"/>								
							</span>
							</s:if>
						</div>
						<div class="panel-body">
							<div class="boxcontent">											
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.policyOrContractNo" />
									</div>
									<div class="tbox">
										<s:property value="policy_Contract_No"/>
									</div>
								</div>
								<div class="textfield" style="display:table;" align="right">
									<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('<s:property value="%{#session.mfrid}" />','<s:property value="proposal_No" />','<s:property value="amendId" />')" />
									<s:hidden name="amendId"/>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="Claim.class" />
									</div>
									<div class="tbox">
										<s:property value="departmentName"/>
									</div>
								</div>											
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.cedingCompanyName" />
									</div>
									<div class="tbox">
										<s:property value="ceding_company_Name"/>
									</div>
								</div>											
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.brokerName" />
									</div>
									<div class="tbox">
										<s:property value="broker_Name"/>
									</div>
								</div>								
								
								<%--<i class="glyphicon glyphicon-plus" id="detailsPlus" style="cursor: pointer;" onclick="detailsClick(true)"></i>
								<i class="glyphicon glyphicon-minus" id="detailsMinus" style="cursor: pointer; display: none;" onclick="detailsClick(false)"></i>
								<div id="detailsData" style="display: none;"> --%>
								<div>
								<s:if test='"1".equals(#session.mfrid)'>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.orginalInsured" />
									</div>
									<div class="tbox">
										<s:property value="insuredName"/>
									</div>
								</div>								
								</s:if>																						
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.from" />
									</div>
									<div class="tbox">
										<s:property value="from"/>
										<s:hidden name="from" value="%{from}" />													 
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.to" />
									</div>
									<div class="tbox">
										<s:property value="to"/>
										<s:hidden name="to" value="%{to}" />
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.retenstionPercentage" />
									</div>
									<div class="tbox">
										<s:property value="retention"/>
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.signedShare" />
									</div>
									<div class="tbox">
										<s:property value="signed_Share"/>
										<s:hidden name="signed_Share" value="%{signed_Share}" />
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.Suminsured" />
									</div>
									<div class="tbox">
										<s:property value="sumInsOSOC"/>
										<s:hidden name="sumInsOSOC" value="%{sumInsOSOC}" />
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:if test='"4".equals(#session.mfrid)'>
											<s:text name="claim.treatyLimitOSDC" /> <s:property value="shortname" />
										</s:if>
										<s:if test='"5".equals(#session.mfrid)'>
											<s:text name="claim.coverLimitOSDC" />
										</s:if>
									</div>
									<div class="tbox">
										<s:property value="sumInsOSDC"/>
										<s:hidden name="sumInsOSDC" />
									</div>
								</div>
								<s:if test='"4".equals(#session.mfrid)'>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.cashcallLimit-OurShare" />
									</div>
									<div class="tbox">
										<s:property value="cashLossOSOC"/>
										<s:hidden name="cashLossOSOC" />
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.cashcallLimit-OurShare-USD" /> <s:property value="shortname" />
									</div>
									<div class="tbox">
										<s:property value="cashLossOSDC"/>
										<s:hidden name="cashLossOSDC" />
									</div>
								</div>
								<div class="textfield" style="display:table;">
									<div class="text txtB">
										<s:text name="claim.SumofPaidAmountOC" />
									</div>
									<div class="tbox">
										<s:property value="sumOfPaidAmountOC"/>
									</div>
								</div>
								</s:if>
								<br class="clear"/>								
								</div>
							</div>
						</div>									
					</div>
				</div>
			</div>
					<div class="table1">
						<div class="tablerow">
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-body">
									<s:if test='!"Yes".equals(premiumclaimMode)'>
										<div class="cols25" align="center">
											<input type="button" value="Claim Registration" class="btn btn-xs btn-primary" onclick="ViewMode('Amode')" />
										</div>
										</s:if>
										<s:if test='!"Yes".equals(premiumclaimMode)'>
										<div class="cols25" align="center">
											<input type="button" value="Claim Payment" class="btn btn-xs btn-primary" onclick="ViewMode('Bmode')" />
										</div>
										</s:if>
										<s:if test='!"Yes".equals(premiumclaimMode)'>
										<div class="cols25" align="center">
											<input type="button" value="Claim Reserve Updation" class="btn btn-xs btn-primary" onclick="ViewMode('Cmode')" />
										</div>
										</s:if><%--
										<s:if test='!"Yes".equals(premiumclaimMode)'>
										<div class="cols25" align="center">
											<input type="button" value="Claim Review" class="btn btn-xs btn-primary" onclick="ViewMode('Dmode')" />
										</div>
										</s:if> --%>
										<br class="clear"/>
									</div>
								</div>
							</div>
						</div>						
						<s:if test='"Amode".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Reserve Position"></s:text>
									</div>			
									<div class="panel-body">
										<display:table name="RevisionList" pagesize="5" requestURI="" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
										
											<display:column   style="text-align:center;"  format="{0,number,0,000.00}" title="Reserve Id" property="SNo" />
											<display:column   style="text-align:center;" title="Date" property="cliam_update_Date" />
											<display:column sortable="true" style="text-align:center;" title="View">
													<a href="#" onclick="ViewReserve()" style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;"  title="View">View</a>
											</display:column>
										</display:table>										
									</div>
								</div>
							</div>
							<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Claim Payment"></s:text>
									</div>			
									<div class="panel-body">
										<display:table name="ClaimPayment" pagesize="10" requestURI="" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											
											<display:column   style="text-align:center;background-color:#FFF8C6" title="Reserve Id" property="SNo" />
															<display:column  
																style="text-align:center;background-color:#FFF8C6"
																title="Payment No." property="claimPaymentNo" />
															<display:setProperty name="paging.banner.onepage" value="" />
											<display:column   style="text-align:center;background-color:#FFF8C6" title="BKR / Ceding Co. Ref. No." property="payment_Request_No" />
											<display:column   style="text-align:center;background-color:#FFF8C6;" title="Payment Date" property="date" />		
											<display:column   style="text-align:right; background-color:#FFF8C6" title="Total Paid Amount - Our Share - OC" property="paid_Amount_Orig_curr" />
											<display:column sortable="true" style="text-align:center;background-color:#FFF8C6;" title="Settlement Status">
													<a href="#" onclick="ViewPayment('${record.claimPaymentNo}')" style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;"  title="View">View</a>
												</display:column>
										</display:table>										
									</div>
								</div>
							</div>
						</div>
						
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="heading.claim.claimDetails"></s:text>
									</div>			
									<div class="panel-body">
										<div class="boxcontent">
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.Statusofclaim" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="status_of_claim"/>
												</div>
											</div>
											
											
											<s:if test='"5".equals(productId)'>
												<div class="textfield">
													<div class="text txtB">
														<s:text name="label.department.class" />
													</div>
													<div class="tbox">
															<s:select name="departmentClass" id="departmentClass" list="departmentClassList" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" disabled="true" onchange="GetExchangeRate()" />
													</div>
												</div>
											</s:if>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.DateofLoss" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="date_of_Loss"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.ReportDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="report_Date"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.reservePositionDate" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="reservePositionDate"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
											<div class="text txtB ">
												<s:text name="claim.registrationDate" />
											</div>
											<div class="tbox">
												:&nbsp;&nbsp;&nbsp;<s:property value="created_Date"/>
											</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.LossDetails" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="loss_Details"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.CauseofLoss" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="cause_of_Loss"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.Location" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="location"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.cedentsNo" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="cedentClaimNo"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.currency" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="currencyName"/>
													<%--<s:select name="currecny" id="currecny" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" headerValue="---Select---" disabled="true" onchange="GetExchangeRate()"/>--%>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.ExcRate" />
												</div>
												<div class="tbox" style="display:table;">
													:&nbsp;&nbsp;&nbsp;<s:property value="exc_Rate"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.grossLossFGU" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="grossLossFGU"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.insuredName" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="insuredName"/>
												</div>
											</div>


											<div class="textfield" style="display:table;">
											<s:if test='"5".equals(productId)'>
												<div class="text txtB">
													<s:text name="NonClaim.LossEstimate100%-OrigCurr" />
												</div>
											</s:if>
											<s:if test='"4".equals(productId)'>
												<div class="text txtB">
													<s:text name="proClaim.LossEstimate100%-OrigCurr" />
												</div>
											</s:if>
											
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="loss_Estimate_Orig_Curr"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
											<s:if test='"5".equals(productId)'>
												<div class="text txtB">
													<s:text name="NonClaim.LossEstimateOurshare-OrigCurr" />
												</div>
												</s:if>
												
												<s:if test='"4".equals(productId)'>
												<div class="text txtB">
													<s:text name="proClaim.LossEstimateOurshare-OrigCurr" />
												</div>
												</s:if>
									
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="loss_Estimate_Our_share_Orig_Curr"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.recordFeess" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="recordFees"/>
												</div>
											</div>
											<div >
												<div class="textfield" id="si" style="display:none">
													<div class="text txtB">
														<s:text name="claim.surveyeradjuster" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="surveyorAdjesterPerOC"/>
													</div>
												</div>
												<div class="textfield" id="sio" style="display:none">
													<div class="text txtB">
														<s:text name="claim.surveyeradjusterouershare" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="surveyorAdjesterOurShareOC"/>
													</div>
												</div>
											</div>
											<div >
												<div class="textfield" id="so" style="display:none" >
													<div class="text txtB">
														<s:text name="claim.otherprofessional" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="otherProfessionalPerOc"/>
													</div>
												</div>
												<div class="textfield" id="otheroc" style="display:none">
													<div class="text txtB" >
														<s:text name="claim.otherprofessionaloc" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="professiona
														lOurShareOc"/>
														</div>
												</div>
												<s:if test='"RI02".equals(#session.SOURCE_CODE)'>	
													<div class="textfield" style="display:table;">
														<div class="text txtB">
															<s:text name="claim.recordibnros" />
														</div>
														<div class="tbox">
															:&nbsp;&nbsp;&nbsp;<s:property value="recordIbnr"/>
														</div>
													</div>
												</s:if>
												<div class="textfield" id="ib" style="display:none;">
													<div class="text txtB">
														<s:text name="claim.ibnroc" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="ibnrPerOc"/>
													</div>
												</div>
												<div class="textfield" id="ibos" style="display:none;">
													<div class="text txtB">
														<s:text name="claim.ibnrourShare" />
													</div>
													<div class="tbox">
														:&nbsp;&nbsp;&nbsp;<s:property value="ibnrOurShareOc"/>
													</div>
												</div>

											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.RIRecovery" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="ri_Recovery"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="Claim.Createdby" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="created_by"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.departmentClass" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="departmentName"/>
												</div>
											</div>
											<s:if test='"4".equals(productId)'>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="label.subClass" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="subProfitCenter"/>
												</div>
											</div>
											</s:if>
											
											<s:if test='advice_UW !=null && advice_UW=="Yes"'>
											<s:if test='advice_uw_email != null'>
											<div class="textfield" style="display:table;">
												<div class="textfieldA25">
													<s:text name="claim.adviceUWEmailID" />
												</div>
												<div class="textfieldA75">
													:&nbsp;&nbsp;&nbsp;<s:property value="advice_uw_email"/>
												</div>
											</div>
											</s:if>
										</s:if>
										<s:if test='advice_Mangement !=null && advice_Mangement=="Yes"'>
											<s:if test='advice_management_email != null'>
											<div class="textfield" style="display:table;">
												<div class="textfieldA25">
													<s:text name="claim.adviceMangementEmailID" />
												</div>
												<div class="textfieldA75">
													:&nbsp;&nbsp;&nbsp;<s:property value="advice_management_email"/>
												</div>
											</div>
											</s:if>
											</s:if>	
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
											<div class="textfield" style="display:table;"> 
												<div class="text txtB">
													<s:text name="claim.riskCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="risk_Code"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.aggregateCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="accumulation_Code"/>
												</div>
											</div>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.eventCode" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="event_Code"/>
												</div>
											</div>
												<div class="textfield" style="display:table;">
													<div class="text txtB">
													</div>
													<div class="tbox">
													</div>
												</div>
											</s:if>
											<div class="textfield" style="display:table;">
												<div class="text txtB">
													<s:text name="claim.remarks" />
												</div>
												<div class="tbox">
													:&nbsp;&nbsp;&nbsp;<s:property value="remarks"/>
												</div>
											</div>
												<br class="clear"/>
										</div>
									</div>
								</div>
							</div>							
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<s:if test='"claimDisplay".equals(claimDisplay)'>
								<input type="button"  value="Back"  class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:else>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="destroyPopUps();BackFunction()"/> &nbsp;&nbsp;&nbsp;
								</s:else>
								<input type="button"  value="Print" id="print"  class="btn btn-sm btn-info" onClick="printpage()" />								
							</div>
						</div>
						</s:if>
				
						
						<s:if test='"Bmode".equals(path)'>
						<div class="tablerow">							
						
						</div>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Claim Payment"></s:text>
									</div>			
									<div class="panel-body">
										<display:table name="ClaimPayment" pagesize="10" requestURI="" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											
											<display:column   style="text-align:center;background-color:#FFF8C6" title="Reserve Id" property="SNo" />
															<display:column  
																style="text-align:center;background-color:#FFF8C6"
																title="Payment No." property="claimPaymentNo" />
															<display:setProperty name="paging.banner.onepage" value="" />
											<display:column   style="text-align:center;background-color:#FFF8C6" title="BKR / Ceding Co. Ref. No." property="payment_Request_No" />
											<display:column   style="text-align:center;background-color:#FFF8C6;" title="Payment Date" property="date" />		
											<display:column   style="text-align:right; background-color:#FFF8C6" title="Paid Amount OC" property="paid_Amount_Orig_curr" />
											<display:column   style="text-align:right;background-color:#FFF8C6" title="Loss Estimate Revised OC" property="loss_Estimate_Revised_Orig_Curr" />
                                         	<display:column  style="text-align:center;background-color:#FFF8C6" sortable="true" title="Payment View">
												<a href="#" onclick="EditPayment('${record.claimPaymentNo}','paymentView')" style="color: blue; font-weight: bold; text-decoration: underline; cursor: pointer;" title="View">View</a>
											</display:column>
                                           <display:column sortable="true" style="text-align:center;background-color:#FFF8C6" title="Settlement Status">
													<a href="#" onclick="ViewPayment('${record.claimPaymentNo}')" style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;"  title="View">View</a>
											</display:column>
										</display:table>										
									</div>
								</div>
								<div id="paymentView">
								
								</div>
							</div>
						</div>
						<div class="tablerow">
						<div class="boxcontent" align="center">
							<s:if test='"Yes".equals(premiumclaimMode)'>
							<input type="button"  value="Close" class="btn btn-sm btn-danger" onclick="destroyPopUps();window.close()"/>
							</s:if>
							<s:else>
							<s:if test='"claimDisplay".equals(claimDisplay)'>
								<input type="button"  value="Back"  class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:else>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="destroyPopUps();BackFunction()"/> &nbsp;&nbsp;&nbsp;
								</s:else>
								<input type="button"  value="Print"  id="print" class="btn btn-sm btn-info"  onClick="printpage()"/>
							</s:else>							
							</div>
						</div>
						</s:if>
						
						<s:if test='"Cmode".equals(path)'>
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Reserve Position"></s:text>
									</div>			
									<div class="panel-body">
										<display:table name="RevisionList" pagesize="5" requestURI="" class="footable" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
											
												<display:column   style="text-align:center;"  format="{0,number,0,000.00}" title="Reserve Id" property="SNo" />
												<display:column   style="text-align:center;" title="Reserve Amount - Our Share - OC" property="loss_Estimate_Revised_Orig_Curr" />
												<display:column   style="text-align:right;" title="Paid Amount - Our Share - OC"  >
													<s:property value="revSumOfPaidAmt" />
												</display:column>		
												<display:column   style="text-align:right;" title="Outstanding Amount - Our Share - OC" property="osAmt"/>												
												<display:column   style="text-align:center;" title="Date" property="cliam_update_Date" />	
												<display:column sortable="true" style="text-align:center;" title="View">
													<a href="#" onclick="ViewReserve()" style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;"  title="View">View</a>
												</display:column>										 
										</display:table>
									</div>
								</div>
							</div>
						</div>
						
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<s:if test='"claimDisplay".equals(claimDisplay)'>
								<input type="button"  value="Back"  class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:else>
								<input type="button" value="Back" id="back"  class="btn btn-sm btn-danger" onclick="destroyPopUps();BackFunction()"/> &nbsp;&nbsp;&nbsp;
								</s:else>
								<input type="button"  value="Print" id="print"  class="btn btn-sm btn-info" onClick="printpage()" />								
							</div>
						</div>
						</s:if>
						
						<s:if test='"Dmode".equals(path)'>						
						<div class="tablerow">							
							<div class="boxcontent">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<s:text name="Heading.ClaimReview"></s:text>
									</div>			
									<div class="panel-body">
										<display:table name="claimReview" pagesize="5" requestURI="" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											
											<display:column   style="text-align:center;background-color:#FFF8C6" title="Sl. No." property="SNo" />
											<display:column   style="text-align:center;background-color:#FFF8C6" title="Review Date" property="review_Date" />
											<display:column   style="text-align:center; background-color:#FFF8C6" title="Review Done By" property="review_Done_By" />
											<display:column   style="text-align:left; background-color:#FFF8C6" title="Remarks" property="remarks" maxLength="75"/>
																 
										</display:table>										
									</div>
								</div>
							</div>
						</div>
						<div class="tablerow">							
							<div class="boxcontent" align="center">
							<div class="boxcontent" align="center">
							<s:if test='"claimDisplay".equals(claimDisplay)'>
								<input type="button"  value="Back"  class="btn btn-sm btn-warning" onClick="destroyPopUps();BackMethod()" /> &nbsp;&nbsp;&nbsp;
								</s:if>
								<s:else>
								<input type="button" value="Back" id="back" class="btn btn-sm btn-danger" onclick="destroyPopUps();BackFunction()"/> &nbsp;&nbsp;&nbsp;
								</s:else>
								<input type="button"  value="Print" id="print" class="btn btn-sm btn-info"  onClick="printpage()"/>								
							</div>								
							</div>
						</div>
						</s:if>												
						<s:hidden name="contarctno" id="contarctno" value="%{policy_Contract_No}" />
						<s:hidden name="proposal_No" id="proposal_No"/>
						<s:hidden name="layerNo" id="layerNo"/>
						<s:hidden name="claim_No" id="claim_No"/>
						<s:hidden name="menuId" id="menuId"/>	
						<s:hidden name="claimDisplay" />
						<s:hidden name="inception_Date" id="inception_Date"/>
						<s:hidden name="claimMasterMode" id="claimMasterMode"/>	
						<s:hidden name="paymentFlag" id="paymentFlag"/>
						<s:hidden name="departmentId" id="departmentId"/>
						<s:hidden name="productId" id="productId"/>
						<s:hidden name="reinstatementPremium" id="reinstatementPremium"/>
						<s:hidden name="currecny" id="currecny"/>
					</div>
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

	function BackMethod(){
	 document.claims.action="<%=request.getContextPath()%>/claimMasterListRetroclm.do?flag=claim";
	 document.claims.submit();
	 }

	function getViewContractDetails(prodId,proposalNo,amendId) {
		var URL ='';
		if(prodId=='1'){
			URL ='${pageContext.request.contextPath}/ViewMethodFacultative?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if(prodId=='2') {
			URL ='${pageContext.request.contextPath}/ViewMethodRiskDetails?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else if((prodId=='3')||(prodId=='5')) {
			URL ='${pageContext.request.contextPath}/ViewMethodXol?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		else {
			URL ='${pageContext.request.contextPath}/ViewMethodRetro?proposal_no='+proposalNo+'&flag=PR&amendId='+amendId;
		}
		
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
	function ViewMode(mode) {     
		document.claims.action="<%=request.getContextPath()%>/claimViewRetroclm.do?&mode="+mode;
		document.claims.submit();
	}
	function ViewReserve() {
	
		var URL ='<%=request.getContextPath()%>/reserveListRetroclm.do?mode=reserve&contractNo=<s:property value="policy_Contract_No"/>&claimNo=<s:property value="claim_No"/>';
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
		var strOpen = window.open(URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		strOpen.focus();
		return false;
	} 
	function ViewPayment(transactionNumber){
	//alert(transactionNumber);
		URL ='${pageContext.request.contextPath}/reserveListRetroclm.do?mode=payment&contractNo='+<s:property value="policy_Contract_No"/>+'&transactionNumber='+transactionNumber;
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
		var strOpen = window.open(URL, windowName, features);
		mtbChildWin[mtbWinCound] = strOpen;
		mtbWinCound++;
		strOpen.focus();
		return false;
		}
	function EditPayment(transactionNumber,id){
			var claim_No =document.getElementById("claim_No").value;
			var layerNo =document.getElementById("layerNo").value;
			var contarctno =document.getElementById("contarctno").value;
			var productId =document.getElementById("productId").value;
			var reinstatementPremium = document.getElementById("reinstatementPremium").value;
		var URL='${pageContext.request.contextPath}/getExcRateRetroclm.do?claimPaymentNo='+transactionNumber+"&dropDown=paymentView&claim_No="+claim_No+"&layerNo="+layerNo+"&contarctno="+contarctno+"&productId="+productId+"&reinstatementPremium="+reinstatementPremium;
		postRequest(URL,'paymentView');
	}
	
	function BackFunction() {
		document.claims.claim_No.value="";
		document.claims.action="<%=request.getContextPath()%>/claimInitRetroclm.do";
		document.claims.submit();
		
	}
	function printpage()
		{
			document.getElementById("back").style.display='none';
			document.getElementById("print").style.display='none';
			window.print();
			document.getElementById("back").style.display='inline';
			document.getElementById("print").style.display='inline';
		}

    funRecView('<s:property value="recordFees"/>');
    function  funRecView(id){
        if(id=="No"){
            document.getElementById('si').style.display = 'none';
            document.getElementById('sio').style.display = 'none';
            document.getElementById('so').style.display = 'none';
            document.getElementById('otheroc').style.display = 'none';
        }
        else if(id=="Yes"){
            document.getElementById('si').style.display = 'table';
            document.getElementById('sio').style.display = 'table';
            document.getElementById('so').style.display = 'table';
            document.getElementById('otheroc').style.display = 'table';
        }
    }
    funRecordView('<s:property value="recordIbnr"/>');
    function  funRecordView(id){
        if(id=="No"){
            document.getElementById('ib').style.display = 'none';
            document.getElementById('ibos').style.display = 'none';
        }
        else if(id=="Yes"){
            document.getElementById('ib').style.display = 'table';
            document.getElementById('ibos').style.display = 'table';

        }
    }
   
</script>	
</body>
</html>
