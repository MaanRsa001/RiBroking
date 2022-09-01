depart<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="JavaScript" type="text/javascript">
			function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }
            document.onkeypress = stopRKey;
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
    <link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css"/>
    <script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
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
		$( "#accDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
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
	  });	
	  </script>	
	
</head>
<body onload="Commas(<s:property value="#session.mfrid" />),setCedRetType('<s:property value="cedRetenType" />')">
<div class="table0" style="width: 100%; margin: 0 auto;">
	<div class="tablerow">
		<div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
			<div class="tablerow">
				<div style="padding:10px; background:#F8F8F8">
					<s:form name="retro1" id="retro1" theme="simple" action="/FaculitivesDispatchAction.do" method="post" autocomplete="off">
					<div class="table2">
						<div class="tablerow">
							<span style="color:red;"><s:actionerror/></span>
						</div>						
						<div class="tablerow" align="center">
							<span class="pageHeading">							
							<s:text name="label.riskDetailSheetRetro" />
							</span>
						</div>
                        <s:if test="contNo != '' && contNo != null && proposal_no != null && proposal_no != ''">
                            <div class="tablerow">
                                <div class="boxcontent">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <div class="boxcontent">
                                                <%--  <div class="textfield">
												<div class="text">
													<s:text name="label.contractno" />
												</div>
												<div class="tbox txtB">
													<s:property value="contNo"/>
												</div>
											</div>--%>
                                                <div class="textfield">
                                                        <div class="text">
                                                            <s:text name="label.endorsementType" />
                                                        </div>
                                                        <div class="tbox txtB">
                                                            <s:select list="endosTypelist" name="endorsmenttype" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME"  />
                                                        </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text">
                                                        <s:text name="label.endorsementNo"/>
                                                    </div>
                                                    <div class="tbox txtB">
                                                        <s:property value="amendId"/>
                                                    </div>
                                                </div>
                                                <br class="clear"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </s:if>
                        <s:hidden name="nr" value="0"/>
                        <div class="boxcontent">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    &nbsp;&nbsp;&nbsp;
                                </div>
                                <div class="panel-body">
                                    <div class="boxcontent">
                                        <div class="textfield">
                                            <div class="text">
                                                <s:text name="label.proposalNo"/>
                                            </div>
                                            <div class="tbox">
                                                <s:textfield name="proposal_no" id="proposal_no"  cssClass="inputBox" disabled="true"
                                                             maxlength="30"/>
                                            </div>
                                        </div>
                                        <div class="textfield">
                                            <div class="text">
                                                <s:text name="label.contractno"/>
                                            </div>
                                            <div class="tbox txtB">
                                                <s:textfield name="contNo" id="contNo" cssClass="inputBox"
                                                             disabled="true" maxlength="30"/>
                                            </div>
                                        </div>
                                        <br class="clear"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tablerow">
                            <s:hidden name="nr" value="0"/>
                            <div class="boxcontent">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        &nbsp;&nbsp;&nbsp;
                                    </div>
                                    <div class="panel-body">
                                        <div class="boxcontent">
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.departmentClass"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:if test="RenewalMode != null">
                                                        <s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME"
                                                                  listKey="TMAS_DEPARTMENT_ID" name="departId"
                                                                  cssClass="inputBoxS" headerKey=""
                                                                  headerValue="---Select---" disabled="true"
                                                                  onchange="getAjax(this.value,'subclass')"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME"
                                                                  listKey="TMAS_DEPARTMENT_ID" name="departId"
                                                                  cssClass="inputBoxS" headerKey=""
                                                                  headerValue="---Select---"
                                                                  disabled="%{contNo==null?false:''.equals(contNo)?false:true}"
                                                                  onchange="getAjax(this.value,'subclass');"/>
                                                    </s:else>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.subClass"/>
                                                </div>
                                                <div class="tbox" id="subclass">
                                                
                                               <%--  <s:select list="subProfitList" listKey="TMAS_SPFC_ID"
                                                              listValue="TMAS_SPFC_NAME" name="subProfit_center"
                                                              cssClass="inputBoxS" headerKey=""
                                                              headerValue="---Select---"
                                                              disabled="%{RenewalMode!=null?true:false}"/>--%>
                                                              <s:select list="subProfitList" name="subProfit_center" cssClass="inputBoxS" id="subProfit_center" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="%{RenewalMode!=null?true:false}"/> 
                                                </div>
                                            </div>
											<div class="textfield">
												<div class="text">
                                                    <s:text name="label.profitCentre"/>
												</div>
												<div class="tbox">
                                                    <s:select list="profit_Centerlist" listKey="TMAS_PFC_ID"
                                                              listValue="TMAS_PFC_NAME" name="profit_Center"
                                                              cssClass="inputBoxS" headerKey=""
                                                              headerValue="---Select---"
                                                              disabled="%{RenewalMode!=null?true:false}"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.underwriter"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="UnderWritterlist" listKey="UWR_CODE"
                                                              listValue="UNDERWRITTER" name="underwriter"
                                                              cssClass="inputBoxS" headerKey=""
                                                              headerValue="---Select---"
                                                              disabled="%{RenewalMode!=null?true:false}"/>
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
													<s:text name="label.branchLocation" />
												</div>
												<div class="tbox">
													<s:select list="polBrlist" listKey="TMAS_POL_BRANCH_ID" listValue="TMAS_POL_BRANCH_NAME" name="polBr" cssClass="inputBoxS" disabled="%{RenewalMode!=null?true:false}" />
												</div>
											</div>
											<div class="textfield">
												<div class="text">
                                                        <s:text name="label.dummy" />
												</div>
												<div class="tbox">
                                                         <s:radio list="#{'D':'Yes','N':'No'}" name="dummyCon"
                                                             value="%{(dummyCon==null||dummyCon=='')?'N':dummyCon}" onchange="getDummyCon(this.value)" disabled="(contNo != '' && contNo != null)?true:false"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
                                                    <s:text name="label.leadRetrocessionaire"/>
												</div>
												<div class="tbox">
													<div class="inputAppend">
                                                        <s:if test="RenewalMode != null">
														<s:select list="CeddingCompanylist" listKey="CUSTOMER_ID" listValue="NAME" name="cedingCo" id="CeddingId" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" headerKey="" headerValue="---Select---" disabled="true" />															
														<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />																										
													</s:if>
													<s:else>
                                                        <s:select list="CeddingCompanylist" listKey="CUSTOMER_ID"
                                                                  listValue="NAME" name="cedingCo" id="CeddingId"
                                                                  cssClass="pullLeft"
                                                                  cssStyle="width: 85%; border:transparent;"
                                                                  headerKey="" headerValue="---Select---"
                                                                  disabled="%{contNo.replace(',','')==null?false:''.equals(contNo.replace(',',''))?false:true}"/>
														<input type="button"  size="2"  value="..." onclick="functionview(1)" class="pullRight" />
													</s:else>
													</div>
												</div>
											</div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.leadRetroBroker"/>
                                                </div>
                                                <div class="tbox">
                                                    <div class="inputAppend">
                                                        <s:if test="RenewalMode != null">
                                                            <s:select list="brokerlist" listKey="CUSTOMER_ID"
                                                                      listValue="NAME" name="broker" id="BrokerId"
                                                                      cssClass="pullLeft"
                                                                      cssStyle="width: 85%; border:transparent;"
                                                                      headerKey="" headerValue="---Select---"
                                                                      disabled="true"/>
                                                            <input type="button" size="2" value="..."
                                                                   onclick="functionview(2)" class="pullRight"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="brokerlist" listKey="CUSTOMER_ID"
                                                                      listValue="NAME" name="broker" id="BrokerId"
                                                                      cssClass="pullLeft"
                                                                      cssStyle="width: 85%; border:transparent;"
                                                                      headerKey="" headerValue="---Select---"
                                                                      disabled="%{contNo.replace(',','')==null?false:''.equals(contNo.replace(',',''))?false:true}"/>
                                                            <input type="button" size="2" value="..."
                                                                   onclick="functionview(2)" class="pullRight"/>
                                                        </s:else>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="textfield">
												<div class="text">
													<s:text name="label.retroType.retro" />
												</div>
												<div class="tbox">
													<s:select list="retroTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="retroType" id="retroType" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  disabled="%{(proposal_no!=null && !''.equals(proposal_no))?true:false}" onchange="TresuryScript(this.value);toggleXLCostOC(this.value);getFieldValue(this.value);getTreatyType();GetPML();" />
												</div>
											</div>
											<div class="textfield" id="insured" style="dislay:none">
												<div class="text">
													<s:text name="label.insuredName" />
												</div>
												<div class="tbox">
													<s:textfield name="insuredName" cssClass="inputBox" maxlength="100" />
												</div>
											</div>
											<div class="textfield" id="type" style="dislay:none">
                                                <div class="text">
                                                    <s:text name="label.retroTreatyType"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="treatyTypelist" listKey="TYPE"
                                                              listValue="DETAIL_NAME" name="treatyType" id="treatyType" 
                                                              cssClass="inputBoxS" headerKey="0"
                                                              headerValue="---Select---"
                                                              onchange="getTreatyType();GetPML();"/>
                                                </div>
                                            </div>
											<div class="textfield" id="name" style="dislay:none">
												<div class="text">
                                                    <s:text name="label.retroTreatyNameType"/>
												</div>
												<div class="tbox">
                                                    <s:textfield name="treatyName_type" cssClass="inputBox"
                                                                 maxlength="50"/>
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
													<s:text name="label.inceptionDate" />
												</div>
												<div class="tbox">
													<s:if test="RenewalMode != null && RenewalMode!=''">
														<s:if test="layerProposalNo == null || layerProposalNo == ''">

															<!--<sj:datepicker name="incepDate" id="incepDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />-->
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)"  />	

														</s:if>
														<s:else>
															<!--<sj:datepicker name="incepDate" id="incepDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />-->
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)"  />	
													
													</s:else>
													</s:if>
													<s:else>
														<s:if test="layerProposalNo == null || layerProposalNo == ''">
															<s:if test="prclFlag == true">
																<!--<sj:datepicker name="incepDate" id="incepDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate()" readonly="true" />-->
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()" readonly="true"/>	
															
															</s:if>
															<s:else>
																<!--<sj:datepicker name="incepDate" id="incepDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="functionDate()" />-->
																<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this)" onchange="functionDate();GetExchangeRate()" />	
																
															</s:else>
														</s:if>
														<s:else>

															<!--<sj:datepicker name="incepDate" id="incepDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" />-->
															<s:textfield name="incepDate" id="incepDate"  cssClass="inputBox"   onkeyup="validateSpecialChars(this);" onchange="GetExchangeRate()"/>	

														</s:else>
													</s:else>													
												</div>
											</div>
											<div class="textfield">
												<div class="text">
													<s:text name="label.expiryDate" />
												</div>
												<div class="tbox">
													<s:if test="layerProposalNo == null || layerProposalNo == ''">
														<!--<sj:datepicker name="expDate" id="expDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)"  />-->
															<s:textfield name="expDate" id="expDate"  cssClass="inputBox"  onkeyup="validateSpecialChars(this)" />	<%--disabled="true" --%>
														
													</s:if>
												</div>
											</div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.uwYear"/>
                                                </div>
                                                <div class="tbox" id="yearId">
                                                    <s:select list="yearList" listKey="YEAR" listValue="YEAR"
                                                              name="uwYear" cssClass="inputBoxS" headerKey=""
                                                              headerValue="---Select---"/>
                                                </div>
                                            </div>
											<div class="textfield">
												<div class="text">
                                                    <s:text name="label.acceptanceDate"/>
												</div>
												<div class="tbox">

                                                    <!--<sj:datepicker name="accDate" id="accDate" displayFormat="dd/mm/yy" cssClass="pullLeft" cssStyle="width: 85%; border:transparent;" onkeyup="validateSpecialChars(this)" onchange="GetExchangeRate()" />-->
                                                    <s:textfield name="accDate" id="accDate" cssClass="inputBox"
                                                                 onkeyup="validateSpecialChars(this)"
                                                                 onchange="GetExchangeRate()"/>
												</div>
											</div>
											<div class="textfield">
												<div class="text">
                                                    <s:text name="label.originalCurrency"/>
												</div>
												<div class="tbox">
                                                    <s:select list="orginalCurrencylist" listKey="CURRENCY_ID"
                                                              listValue="CURRENCY_NAME" name="orginalCurrency"
                                                              cssClass="inputBoxS" headerKey=""
                                                              headerValue="---Select---" onchange="GetExchangeRate()"/>
												</div>
											</div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.exchangeRate"/>
                                                </div>
                                                <div class="tbox" id="exRate">
                                                    <s:textfield name="exchRate" id="exchRate" cssStyle="text-align:right;"
                                                                 cssClass="inputBox" readonly="true"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                               <div class="text">
                                                    <s:text name="label.cessionRate"/>
                                                </div>
                                                <div class="tbox">
                                                         <s:select list="cessionList" listKey="TYPE"
                                                              listValue="DETAIL_NAME" name="cessionExgRate" id="cessionExgRate"
                                                               headerKey="0" cssClass="inputBoxS"
                                                              headerValue="---Select---" onchange="getFixRate(this.value)"  cssStyle="float:left;"/>
                                                              <div id="fixed" style="display:none;"><s:textfield name="fixedRate" id="fixedRate"  cssStyle="text-align:right;width: 45%; float:left;" onkeyup="allow8DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);"
                                                                 cssClass="inputBox" /> </div>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.noOfRetroCessionaries"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="noRetroCess" id="noRetroCess"  cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="3"/>
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
                                                    <s:text name="label.territoryScope"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="territoryscope" cssClass="inputBox"
                                                                 maxlength="500"/>
                                                </div>
                                            </div>

                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.cleancutrunoff"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="proposaltypelist" listKey="TYPE"
                                                              listValue="DETAIL_NAME" name="proposalType"
                                                              cssClass="inputBoxS" headerKey="0"
                                                              headerValue="---Select---" onchange="getRateField(this.value)"/>
                                                </div>
                                            </div>
											<div class="textfield" id="ratId" style="display:none;">
												<div class="text">
													<s:text name="label.rate.year" />
												</div>
												<div class="tbox">
													<s:textfield name="runoffYear" id="runoffYear" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="26" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);"/>
												</div>
											</div>			
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.portfolioCovered"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="riskCovered" cssClass="inputBox" maxlength="50"/>
                                                </div>
                                            </div>

                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.LOCIssued"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:radio list="#{'Y':'Yes','N':'No'}" name="LOCIssued"
                                                             value="%{(LOCIssued==null||LOCIssued=='')?'N':LOCIssued}" onchange="getLocDeta(this.value)"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="bnkId" style="display:none;">
												<div class="text">
														<s:text name="label.bankName" />
													</div>
													<div class="tbox">													
													<s:textfield name="locBankName" id="locBankName" cssClass="inputBox"  maxlength="100"/>
													</div>
												
											</div>
											<div class="textfield" id="crdPId" style="display:none;">
												<div class="text">
														<s:text name="label.creditPrd" />
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditPrd" id="locCreditPrd" cssClass="inputBox"   maxlength="26" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);"/>
													</div>
												
											</div>
											<div class="textfield" id="crdAId" style="display:none;">
												<div class="text">
														<s:text name="label.creditAmt" />
													</div>
													<div class="tbox">													
													<s:textfield name="locCreditAmt" id="locCreditAmt" cssClass="inputBox" cssStyle="text-align:right;"  onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value);negative(this.id,this.value);" maxlength="26"/>
													</div>
												
											</div>
											<div class="textfield" id="benfId" style="display:none;">
												<div class="text">
														<s:text name="label.benficerName" />
													</div>
													<div class="tbox">													
													<s:textfield name="locBeneficerName" id="locBeneficerName" cssClass="inputBox"   maxlength="100"/>
													</div>
												
											</div>
											<s:if test='"RI02".equals(#session.SOURCE_CODE)'>
                                           <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.perilCovered"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="perilCoveredlist" id="perilCovered"
                                                              listValue="DETAIL_NAME" multiple="true" size="1"
                                                              listKey="TYPE" name="perilCovered" cssClass="inputBoxS" headerKey="0" headerValue="None"/>
                                                </div>
                                            </div> 
                                            </s:if> 
                                            <s:else>
                                            	<s:hidden name="perilCovered" id="perilCovered"/>
                                            </s:else>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.pnocDays"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="PNOCDayslist" listValue="DETAIL_NAME" listKey="TYPE"
                                                              name="pnoc" cssClass="inputBoxS" headerKey="-1"
                                                              headerValue="---Select---"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.accountingPeriod"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:select list="AccontPeriodlist" listValue="DETAIL_NAME"
                                                              listKey="TYPE" name="accountingPeriod"
                                                              cssClass="inputBoxS" headerKey="-1"
                                                              headerValue="---Select---"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.statementDueDays"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="receiptofStatements" cssClass="inputBox"
                                                                 cssStyle="text-align:right;"
                                                                 onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="3"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.paymentDueDays"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="receiptofPayment" cssClass="inputBox"
                                                                 cssStyle="text-align:right;"
                                                                 onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="3"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <jsp:include page="/WEB-INF/jsp/common/territoryPopUp.jsp"/>
                            <div class="boxcontent">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        &nbsp;&nbsp;&nbsp;
                                    </div>
                                    <div class="panel-body">
                                        <div class="boxcontent">
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.ourRetentionType"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:radio  name="cedRetenType"
                                                             list="#{'A':'Amount','P':'Percentage'}"
                                                             onclick="setCedRetType(this.value)"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.ourRetention"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="cedReten" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="treatyQS1" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.retroTreatyLimitOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="limitOrigCur" id="limitOrigCur"
                                                                 cssClass="inputBox" cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
                                                                 onblur="calculateRetroTreatyQSPML();"
                                                                 maxlength="30"/>
                                                </div>
                                            </div>
                                             <div class="textfield" id="treatyQS3" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.facTreatyLimitOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="faclimitOrigCur" id="faclimitOrigCur"
                                                                 cssClass="inputBox" cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
                                                                 onblur="calculateRetroTreatyQSPML();"
                                                                 maxlength="30"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="treatySurp1" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.noofline"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="treatynoofLine" id="treatynoofLine"
                                                                 cssClass="inputBox" onkeyup="checkNumbers(this);"
                                                                 maxlength="30"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="treatySurp2" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.retroTreatyLimitSurplusOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="treatyLimitsurplusOC"
                                                                 id="treatyLimitsurplusOC" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30"
                                                                 onblur="calculateRetroTreatySurplusPML();"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.treatypml"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:radio list="#{'Y':'Yes','N':'No'}" name="pml" id="pml"
                                                             value="%{(pml==null ||pml=='') ?'N':pml}" onchange="GetPML();"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="pmls" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.treatypmlper"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="pmlPercent" id="pmlPercent"
                                                                 cssClass="inputBox" cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:checkDecimals(this)" maxlength="10"
                                                                 onblur="calculateRetroTreatyQSPML();calculateRetroTreatySurplusPML();"/>
                                                </div>
                                            </div>
                                           <%--  <div class="textfield" id="treatyQS2" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.retroTreatyLimitpmlOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="limitOrigCurPml" id="limitOrigCurPml"
                                                                 cssClass="inputBox" cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30" readonly="true"/>
                                                </div>
                                            </div>
                                             <div class="textfield" id="treatyQS4" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.facTreatyLimitpmlOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="faclimitOrigCurPml" id="faclimitOrigCurPml"
                                                                 cssClass="inputBox" cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30" readonly="true"/>
                                                </div>
                                            </div>
                                            <div class="textfield" id="treatySurp3" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.retroTreatyLimitsurplusOCpml"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="treatyLimitsurplusOCPml"
                                                                 id="treatyLimitsurplusOCPml" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30" readonly="true"/>
                                                </div>
                                            </div>--%>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.EPIOC100"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="epi" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)"
                                                                 />
                                                </div>
                                            </div>
                                        <%--    <div class="textfield" id="epis" style="display:none">
                                                <div class="text">
                                                    <s:text name="label.EPIOpml"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="epipml" id="epipml" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)"
                                                                 readonly="true"/>
                                                </div>
                                            </div>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.xlCostOC"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:textfield name="xlCost" cssClass="inputBox"
                                                                 cssStyle="text-align: right;"
                                                                 onkeyup="allow2DigitDecValues(this);javascript:this.value=Comma(this.value)"
                                                                 maxlength="30"/>
                                                </div>
                                            </div> --%>
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.proposalStatus"/>
                                                </div>
                                                <div class="tbox">
                                                    <s:if test="contractNo == '' || contractNo == null || !'renewal'.equals(flagTest)">
                                                        <s:select list="proposallist" name="proStatus"
                                                                  cssClass="inputBoxS"
                                                                  onchange="GetShareSigned(this.value)" headerKey=""
                                                                  headerValue="--Select--" listKey="TYPE"
                                                                  listValue="DETAIL_NAME"
                                                                  disabled='%{("Y".equals(prodisableStatus))?true:false}'/>
                                                        <s:hidden name="prodisableStatus"></s:hidden>
                                                    </s:if>
                                                    <s:else>
                                                        <s:select list="proposallist" name="proStatus"
                                                                  cssClass="inputBoxS"
                                                                  onchange="GetShareSigned(this.value)" headerKey=""
                                                                  headerValue="--Select--" listKey="TYPE"
                                                                  listValue="DETAIL_NAME" disabled="true"/>
                                                    </s:else>
                                                        <%-- <s:select name="proStatus" list="proposallist" listValue="DETAIL_NAME" listKey="TYPE" headerKey="" headerValue="---Select---" cssClass="inputBoxS" onchange="GetShareSigned(this.value)" disabled="%{amend_Id_Mode!=null?true:false}" />
                        --%></div>
                                            </div>
                                            <br class="clear"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <jsp:include page="/WEB-INF/jsp/common/remarks.jsp" />
                        </div>
                    </div>


                    <div class="tablerow">
                        <div class="boxcontent" align="center">
								<s:if test='amend_Id_Mode == null ||"".equals(amend_Id_Mode) '>
									<s:if test='proposal_no == null ||"".equals(proposal_no) '>
										<s:if test='layerProposalNo == null ||"".equals(layerProposalNo) '>
											<s:if test='"renewal".equals(flagTest)'>
												<input type="button"  value="Back"  class="btn btn-sm btn-danger" id="mybutton" onClick="destroyPopUps();AmendIdBack()" />
											</s:if>										
										</s:if>
										<s:hidden name="ceddingcompanyBack" value="%{cedingCo}" />
										<% request.setAttribute("LayerMode","Yes");  %>
										<s:if test='"renewal".equals(flagTest)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton" onClick="destroyPopUps();AmendIdBack()" />
										</s:if>
										<s:else>
											 <input type="button"  value="Cancel"  class="btn btn-sm btn-danger" onClick="destroyPopUps();FunctionEditCancel()" />
										</s:else>
										<input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');destroyPopUps();FunctionSaveOption()"  />
							 			<input type="button"  value="Next"    class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');destroyPopUps();next()" />
									</s:if>
									<s:else>
																			
										<s:if test='"R".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="destroyPopUps();FunctionRejectCancel()" />
										</s:if>
										<s:elseif test='"N".equals(proStatus)'>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="destroyPopUps();FunctionNotTakenCancel()" />
										</s:elseif>
										<s:elseif test='"renewal".equals(flagTest) || "Renewal".equals(proposalReference)'>
											<%-- <input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="FunctionRejectCancel()" />--%>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="destroyPopUps();return FunctionCancel();" />
											<s:hidden name="proposalNo1"/>
										</s:elseif>
										<s:else>
											<input type="button"  value="Cancel"  class="btn btn-sm btn-danger"  onClick="destroyPopUps();FunctionEditCancel()" />
										</s:else>	
										<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');destroyPopUps();FunctionSaveOption()" />
							 			<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');destroyPopUps();funEditSubmit()" />
									</s:else>
								</s:if>
								<s:else>
									<input type="button"  value="Cancel"  class="btn btn-sm btn-danger" id="mybutton"  onClick="destroyPopUps();AmendIdBack()"/>
									<%-- <input type="button"  value="Save"   class="btn btn-sm btn-success"  onclick="disableForm(this.form,false,'');FunctionSaveOption()"/>--%>
									<input type="button"  value="Next"   class="btn btn-sm btn-warning" onclick="disableForm(this.form,false,'');destroyPopUps();funAmendsubmit()"/>
								</s:else>								
							</div>
						</div>				
						<!--<div class="tablerow">							
							<div class="boxcontent" align="center">
								<input type="button" class="btn btn-sm btn-danger" onClick="FunctionEditCancel()" value="Cancel" />
								&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-sm btn-success" onClick="FunctionSaveOption()" value="Save" />
								&nbsp;&nbsp;&nbsp;
								 
								<input type="button" class="btn btn-sm btn-info" onClick="funsubmit()" value="Next" />
								
								<s:submit action="retro2Retro" cssClass="btn btn-sm btn-warning" value="Next" />
							</div>
						</div>
																			
					-->
					<s:hidden name="flag" id="flag"/>
					<s:hidden name="menuId" id="menuId"/>
					<s:hidden name="shareWritt" value="100"/>
					<s:hidden name="sharSign" value="100"/>	
					<s:hidden name="proposalno" />
					<s:hidden name="CustomerId" value="cedingCo" />
					<s:hidden name="layerProposalNo" />
					<s:hidden name="brokerage" />
					<s:hidden name="tax" />
					<s:hidden name="contractno1" />
					<s:hidden name="renewal_contract_no" />
					<s:hidden name="renewalFlag" />
					<s:hidden name="lay1" />
					<s:hidden name="baseLayer" />
					<s:hidden name="baseLoginID" />
					<s:hidden name="amendId" />
                    <%--<s:hidden name="contractNo" value="%{contNo}" />
                    <s:hidden name="contNo"/>--%>
					<s:hidden name="edit" />
					<s:hidden name="cedType" id="cedType" />
					<s:hidden name="proposalNo" id="proposalNo" value="%{proposal_no}"/>
					<s:hidden name="ourEstimate"/>
					<s:hidden name="endorsmentno"/>
					<s:hidden name="mode"/>
					<s:hidden name="countryExcludedList" id="countryExcludedList"/>
					<s:hidden name="countryIncludedList" id="countryIncludedList"/>
					<s:hidden name="renewalProposalNo" id="renewalProposalNo"/>
					<s:hidden name="proposalReference" id="proposalReference"/>
					<s:hidden name="preVal" id="preVal"/>
					<s:hidden name="prePerilVal" id="prePerilVal"/>
					<s:hidden name="reMode" id="reMode"/>
					<s:hidden name="renewalEditMode" id="renewalEditMode"/>
					<s:hidden name="RenewalMode" id="RenewalMode"/>
					
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
function next(){
    var checkedItems='';
    var checkedItems1='';
    try{
        var elements=document.getElementById("countryExcluded");
        var elements1=document.getElementById("countryIncluded");
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
    else
    {
        alert('Please Select Territory');
    }
    if(dffDateAlert()){
     exchangeAlert = CessionExchageRate();
		if(exchangeAlert){
		    document.getElementById("countryExcludedList").value=checkedItems;
			document.getElementById("countryIncludedList").value=checkedItems1;
		    replaceComma(document.retro1, 'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
			document.retro1.action="checkRetro.action";
			document.retro1.submit();
	}
	}
}

function funsubmit(){
	if(dffDateAlert()){
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
	             replaceComma(document.retro1, 'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
			 	document.retro1.action="${pageContext.request.contextPath}/checkRetro.action";
			 	document.retro1.submit();
			 }
		</s:if>
	        <s:else>
	       
		        	replaceComma(document.retro1, 'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
					document.retro1.action="${pageContext.request.contextPath}/checkRiskDetails.action";
					document.retro1.submit();
					
		</s:else>
		}
	}
}
function FunctionEdit(){	
	document.retro1.action="${pageContext.request.contextPath}/EditModeRetro.action";
	document.retro1.submit();
}

function funEditSubmit(){
    var checkedItems = '';
    var checkedItems1 = '';
    try {
        var elements = document.getElementById("countryExcluded");
        var elements1 = document.getElementById("countryIncluded");
        for (i = 1; i < elements.length; i++) {
            obj = elements[i];
            checkedItems += obj.value + ',';
        }
        for (i = 1; i < elements1.length; i++) {
            obj = elements1[i];
            checkedItems1 += obj.value + ',';
        }
    } catch (e) {
    }
    if (checkedItems.length > 1 || checkedItems1.length > 1) {
        checkedItems = checkedItems.substring(0, checkedItems.length - 1);
        checkedItems1 = checkedItems1.substring(0, checkedItems1.length - 1);
    }
    else {
        alert('Please Select Territory');
        return false;
    }
	if(dffDateAlert()){
		if(chkAccAmt()){
		exchangeAlert = CessionExchageRate();
			if(exchangeAlert){
			document.getElementById("countryExcludedList").value=checkedItems;
			document.getElementById("countryIncludedList").value=checkedItems1;
		    replaceComma(document.retro1, 'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
			document.retro1.action="FirstPageUpdateModeRetro.action";
			document.retro1.submit();
			}
		}
	}
}

function FunctionSaveOption(){
if(dffDateAlert()){
    var checkedItems = '';
    var checkedItems1 = '';
    try {
        var elements = document.getElementById("countryExcluded");
        var elements1 = document.getElementById("countryIncluded");
        for (i = 1; i < elements.length; i++) {
            obj = elements[i];
            checkedItems += obj.value + ',';
        }
        for (i = 1; i < elements1.length; i++) {
            obj = elements1[i];
            checkedItems1 += obj.value + ',';
        }
    } catch (e) {
    }
    if (checkedItems.length > 1 || checkedItems1.length > 1) {
        checkedItems = checkedItems.substring(0, checkedItems.length - 1);
        checkedItems1 = checkedItems1.substring(0, checkedItems1.length - 1);
    }
    else {
        alert('Please Select Territory');
    }
    document.getElementById("countryExcludedList").value=checkedItems;
	document.getElementById("countryIncludedList").value=checkedItems1;
    replaceComma(document.retro1,'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
	document.retro1.action="FirstPageSaveMethodRetro.action";
	document.retro1.submit();
	}
}

function FunctionView(){
	document.retro1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=ViewMethod"
	document.retro1.submit();
}
function funAmendsubmit(){
    var checkedItems='';
    var checkedItems1='';
    var exchangeAlert;
    try{
        var elements=document.getElementById("countryExcluded");
        var elements1=document.getElementById("countryIncluded");
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
    else
    {
        alert('Please Select Territory');
    }
	if(dffDateAlert()){
		exchangeAlert = CessionExchageRate();
		if(exchangeAlert){
			//document.retro1.action="AmednIdInsertRetro.action";
			//document.retro1.submit();
			document.getElementById("countryExcludedList").value=checkedItems;
			document.getElementById("countryIncludedList").value=checkedItems1;
			replaceComma(document.retro1,'limitOrigCur,cedReten,treatyLimitsurplusOC,limitOrigCurPml,treatyLimitsurplusOCPml,epi,epipml,xlCost,faclimitOrigCur,faclimitOrigCurPml');
			document.retro1.action="FirstPageUpdateModeRetro.action";
			document.retro1.submit();
		}
	}
}

function CessionExchageRate(){
	var val = document.getElementById("cessionExgRate").value;
	if('F'==val){
	var exchangesRate = document.getElementById("exchRate").value;
	var cessionVal = document.getElementById("fixedRate").value;
	if (""==cessionVal){
		cessionVal ='0';
	}
	else {
		cessionVal = cessionVal.replace(new RegExp(',', 'g'),'');
	}
	if (""==exchangesRate){
	exchangesRate ='0';
	}
	var per =((exchangesRate - cessionVal)/cessionVal)*100;
	if (per>5){
		return confirm("The Exchange Rate difference between the Current Rate and the Fixed Rate is more than 5 %.Do you wish to continue?");
	}
	else{
		return true;
	}
	}
	else{
	return true;
	}
}

function FunctionEditCancel(){
	document.retro1.action='${pageContext.request.contextPath}/commonListPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.retro1.submit();
}

function FunctionRejectCancel(){
	//document.location='<%=request.getContextPath()%>/menu.do?method=menu&manufactureID='+<%=session.getAttribute("mfrid")%>+'&flag=R';
	document.retro1.action='${pageContext.request.contextPath}/menuPortfolio.do?manufactureID=<s:property value="#session.mfrid"/>'
	document.retro1.submit();
}
function FunctionNotTakenCancel(){
	
	document.retro1.action='${pageContext.request.contextPath}/menuPortfolio.action?manufactureID=<s:property value="#session.mfrid"/>';
	document.retro1.submit();
}
function AmendIdBack(){
	document.getElementById("mybutton").style.display = "none";
	document.retro1.action ="${pageContext.request.contextPath}/InitPortfolio.do?endtMode=endorsment&endorsementStatus=N";
	document.retro1.submit();
}

function calculation(){
	var limit=document.retro1.limitOrigCur.value;
	limit=limit.replace(new RegExp(',', 'g'),'');


	var Epi100=document.retro1.epi_origCur.value;
	Epi100=Epi100.replace(new RegExp(',', 'g'),'');

	var ourEsitamate=document.retro1.ourEstimate.value;
	if(Epi100!=null && Epi100!="" && ourEsitamate!=null && ourEsitamate!="" )
	{
		var finalvalue=(parseFloat(Epi100)*parseFloat(ourEsitamate))/100;
		document.retro1.epi.value=Comma(finalvalue.toFixed(2));
	}else
	document.retro1.epi.value='0';
}

function GetShareSigned(value){
		if(value=='A')
		{
		document.retro1.sharSign.disabled=false;
		}
		else  if(value=='P')
		{
		document.retro1.sharSign.value='';
		document.retro1.sharSign.disabled=true;
		}
		else if(value=='R')
		{
		document.retro1.sharSign.value='';
		document.retro1.sharSign.disabled=true;
		}
}

function GetExchangeRate() {
		var accDate=document.retro1.accDate.value;
		var incDate=document.forms['retro1'].incepDate.value;
		var Currecny=document.retro1.orginalCurrency.value;
		if((incDate!=null && incDate !="") || (accDate!=null && accDate !=""))
		{
	 		var URL='${pageContext.request.contextPath}/getExcRateRetro.action?incepDate='+incDate+'&orginalCurrency='+Currecny+'&dropDown=exRate&accDate='+accDate;
  	 		postRequest(URL,'exRate');
        }else
		{
			document.retro1.exchRate.value='';
		}	
}


function FunctionLayerBackMode() {
	document.retro1.action="<%=request.getContextPath()%>/CedingCompanyAction.do?method=LayerMode";
	document.retro1.submit();
}

function Functionback() {
	document.retro1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=FirstPageBackMethod"
	document.retro1.submit();
}

function FunctionEditBackMode() {
	document.retro1.action="<%=request.getContextPath()%>/CedingCompanyAction.do?method=FlowEditMode";
	document.retro1.submit();
}

function FunctionAmendIdback() {
	document.retro1.action="<%=request.getContextPath()%>/RiskDetailsDispatchAction.do?method=AmednIdback"
	document.retro1.submit();
}

function CalculateEpi() {	
	var a=document.retro1.subPremium.value;
	a=a.replace(new RegExp(',', 'g'),'');
	var b=document.retro1.adjRate.value;
	if(a!=null && a!="" && b!=null && b!="") {
		var adjRt=parseFloat(b)/100;
		var c=parseFloat(a)*adjRt;
		document.retro1.epi.value=Comma(c.toFixed(2));
	}else {
		document.retro1.epi.value='';
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
	//var URL ='<%=request.getContextPath()%>/CedingCompanyAction.do?method=ViewMode&Mode=PopUp&ceddingcompany='+cedding;
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
	mtbChildWin[mtbWinCound] = strOpen;mtbWinCound++;
	strOpen.focus();
	return false;		 
}
		
function Commas(value) {
	if(value=="2") {
		document.retro1.epi_origCur.value=Comma(document.retro1.epi_origCur.value);
		document.retro1.maxLimit_Product.value=Comma(document.retro1.maxLimit_Product.value);
		document.retro1.limitOrigCur.value=Comma(document.retro1.limitOrigCur.value);
		document.retro1.xlCost.value=Comma(document.retro1.xlCost.value);
		document.retro1.cedReten.value=Comma(document.retro1.cedReten.value);
		document.retro1.epi.value=Comma(document.retro1.epi.value);
		if(document.retro1.limitPerVesselOC.value!=null && document.retro1.limitPerVesselOC.value!="") {
			document.retro1.limitPerVesselOC.value=Comma(document.retro1.limitPerVesselOC.value);
		}
		if(document.retro1.limitPerLocationOC.value!=null && document.retro1.limitPerLocationOC.value!="") {
			document.retro1.limitPerLocationOC.value=Comma(document.retro1.limitPerLocationOC.value);
		}
	} else if(value=="3") {
		document.retro1.deduc_hunPercent.value=Comma(document.retro1.deduc_hunPercent.value);
		document.retro1.maxLimit_Product.value=Comma(document.retro1.maxLimit_Product.value);
		document.retro1.limitOrigCur.value=Comma(document.retro1.limitOrigCur.value);
		document.retro1.subPremium.value=Comma(document.retro1.subPremium.value);
		document.retro1.epi.value=Comma(document.retro1.epi.value);
		document.retro1.m_dPremium.value=Comma(document.retro1.m_dPremium.value);
		if(document.retro1.limitPerVesselOC.value!=null && document.retro1.limitPerVesselOC.value!="") {
			document.retro1.limitPerVesselOC.value=Comma(document.retro1.limitPerVesselOC.value);
		}
		if(document.retro1.limitPerLocationOC.value!=null && document.retro1.limitPerLocationOC.value!="") {
			document.retro1.limitPerLocationOC.value=Comma(document.retro1.limitPerLocationOC.value);
		}
	} else if(value=="4") {
		document.retro1.epi_origCur.value=Comma(document.retro1.epi_origCur.value);
		document.retro1.limitOrigCur.value=Comma(document.retro1.limitOrigCur.value);
		document.retro1.xlCost.value=Comma(document.retro1.xlCost.value);
	} else if(value=="5") {
		document.retro1.deduc_hunPercent.value=Comma(document.retro1.deduc_hunPercent.value);
		document.retro1.limitOrigCur.value=Comma(document.retro1.limitOrigCur.value);
		document.retro1.subPremium.value=Comma(document.retro1.subPremium.value);
		document.retro1.epi.value=Comma(document.retro1.epi.value);
		document.retro1.m_dPremium.value=Comma(document.retro1.m_dPremium.value);
	}
		document.retro1.faclimitOrigCur.value=Comma(document.retro1.faclimitOrigCur.value);
		//document.retro1.faclimitOrigCurPml.value=Comma(document.retro1.faclimitOrigCurPml.value);
}

/*********************************/

function GetSp(id){	
	if(id=='Y')	{
		document.retro1.no_Insurer.value='';
		document.getElementById("NoOFInsur").readOnly=false;
	}
	if(id=='N')	{
		document.getElementById("NoOFInsur").readOnly=true;
		document.retro1.no_Insurer.value="0";
	}
}
<%--
<s:if test='amend_Id_Mode != "" && amend_Id_Mode != null'>
$(".disLink").click( function() {
	alert('Please Submit this Page');
 		return false;
});
var all = document.all;
for(var i=0; i<all.length; i++){
	if(all[i].type == 'button'){
		if(all[i].value!='Next')
			document.all[i].disabled = true;
	}
}
</s:if>
 --%>
function functionDate(){
var	inceptionDate=document.retro1.incepDate.value;
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
		document.retro1.incepDate.value = inceptionDate;
		var date=new Date(reformatDate(inceptionDate));
		//document.retro1.uwYear.value=date.getFullYear();
		
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
		document.retro1.expDate.value=d+"/"+m+"/"+y;
		
	var URL='${pageContext.request.contextPath}/ajaxValueFacultative.action?inceptionDate='+inceptionDate+'&dropDown=yearId';
	postRequest(URL,'yearId');
}
	}
}

function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
  // ex input "2010-01-18" 
	return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex out: "18/01/2010"
}

function dffDateAlert() {
	var inception=document.retro1.incepDate.value;
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
	var expery=(document.retro1.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery[2],expery[1]-1,expery[0]);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));	
	//alert(diffDays);
	if(diffDays>0){
	answer = confirm('Expiry Date is More than One Year.Do You Wish to Continue?');
	}else if(diffDays<-86400000){
	answer = confirm('Expiry Date is Less than One Year.Do You Wish to Continue?');
	}else{
	answer = true;
	}
	return  answer;
}


function chkAccAmt(){
	/*var limitOC=document.retro1.limitOrigCur.value;
	limitOC=limitOC.replace(new RegExp(',', 'g'),'');
	var maxLimit=document.retro1.maxLimit_Product.value;
	maxLimit=maxLimit.replace(new RegExp(',', 'g'),'');
	var exRate=document.retro1.exchRate.value;
	var cedPer=0;
	var amount=0;
	if(document.retro1.proStatus.value=="A"){	 
		cedPer=document.retro1.sharSign.value;
	}else{
		cedPer=document.retro1.shareWritt.value;
	}
	<s:if test='contNo != "" && contNo != null'>
		cedPer=document.retro1.sharSign.value;
    </s:if>
	if(limitOC!=null && limitOC!="" && maxLimit!=null && maxLimit!="" && cedPer!=null && cedPer!="" && exRate!=null &&exRate!=""){
		amount=((limitOC*(cedPer/100.0))/exRate);
		if(amount>maxLimit)
		{
			var doIt=confirm("Accepted Amount should be less than or equal to UW Capacity","Yes","No");
			if(doIt)
			{
				<s:if test='contNo == "" && contNo == null'>				
					document.retro1.proStatus.value='P';
					document.retro1.sharSign.value='';
				</s:if>
			}else
			{
				 return false; 
			}
		}
	}*/
	return true;
}

function setCedRetType(type){
	document.getElementById("cedType").value=type;
}

function toggleXLCostOC(retroType){
	<%--if(retroType=="SR"){
		document.retro1.xlCost.value="0";
		document.retro1.xlCost.readOnly=true;
		document.retro1.insuredName.readOnly=false; 
	}else{
		document.retro1.xlCost.readOnly=false;
		document.retro1.insuredName.value="";
		document.retro1.insuredName.readOnly=true; 
	}--%>
}
 function getAjax(value,id){
		 $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ajaxValueRetro.action?departId='+value+'&dropDown=subclass',
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
<%--function getAjax(value,id){
	var URL='<%=request.getContextPath()%>/ajaxValueRetro.action?departId='+value+'&dropDown=subclass';
	postRequest(URL,'subclass');
}--%>
function TresuryScript(value) {
	<%--if(value=="SR") {
		document.getElementById('insurename').style.display='Inline';
	} else if(value=="TR") {
		document.getElementById('insurename').style.display='none';	
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


getTreatyType();
function getTreatyType() {
	var factreaty =  document.getElementById('retroType').value;
	var val =document.getElementById('treatyType').value ;
	if (val !="0"){
	    if (val == "1") {
	        document.getElementById('treatyQS1').style.display = "inline";
	        document.getElementById('treatySurp1').style.display = "none";
	        document.getElementById('treatySurp2').style.display = "none";
	        document.getElementById('treatyQS3').style.display = "none";
	        document.getElementById('limitOrigCur').value = document.retro1.limitOrigCur.value;
	        document.getElementById('faclimitOrigCur').value ="";
	        document.getElementById('treatynoofLine').value = "";
	        document.getElementById('treatyLimitsurplusOC').value = "";
	    }
	    else if (val == "2") {
	        document.getElementById('treatyQS1').style.display = "none";
	        document.getElementById('treatySurp1').style.display = "inline";
	        document.getElementById('treatySurp2').style.display = "inline";
	        document.getElementById('limitOrigCur').value = "";
	         document.getElementById('treatyQS3').style.display = "none";
	        document.getElementById('treatynoofLine').value = document.retro1.treatynoofLine.value;
	        document.getElementById('treatyLimitsurplusOC').value = document.retro1.treatyLimitsurplusOC.value;
	          document.getElementById('faclimitOrigCur').value ="";
	    }
	    else if (val == "3") {
	        document.getElementById('treatyQS1').style.display = "inline";
	        document.getElementById('treatySurp1').style.display = "inline";
	        document.getElementById('treatySurp2').style.display = "inline";
	         document.getElementById('treatyQS3').style.display = "none";
	        document.getElementById('limitOrigCur').value = document.retro1.limitOrigCur.value;
	        document.getElementById('treatynoofLine').value = document.retro1.treatynoofLine.value;
	        document.getElementById('treatyLimitsurplusOC').value = document.retro1.treatyLimitsurplusOC.value;
	          document.getElementById('faclimitOrigCur').value ="";
	    }
    }
      else if (("SR" == factreaty || "FO" == factreaty || "TO" == factreaty) && "TR" != factreaty){
     		document.getElementById('treatyQS1').style.display = "none";
	        document.getElementById('treatySurp1').style.display = "none";
	        document.getElementById('treatySurp2').style.display = "none";
	        document.getElementById('treatyQS3').style.display = "inline";
	        document.getElementById('faclimitOrigCur').value = document.retro1.faclimitOrigCur.value;
	        document.getElementById('treatynoofLine').value = "";
	        document.getElementById('treatyLimitsurplusOC').value = "";
	          document.getElementById('limitOrigCur').value ="";
    }
    else{
    	document.getElementById('treatyQS1').style.display = "none";
	        document.getElementById('treatySurp1').style.display = "none";
	        document.getElementById('treatySurp2').style.display = "none";
	        document.getElementById('treatyQS3').style.display = "none";
	        document.getElementById('limitOrigCur').value = "";
	        document.getElementById('treatynoofLine').value = "";
	        document.getElementById('treatyLimitsurplusOC').value = "";
	        document.getElementById('faclimitOrigCur').value = "";
    }
}
GetPML();
function GetPML() {
    pml = document.retro1.pml.value;
    var treatyType = document.retro1.treatyType.value;
    var factreaty =  document.getElementById('retroType').value ;
    if (pml == "Y") {
        document.getElementById('pmls').style.display = 'block';
        document.getElementById('pmlPercent').value = document.retro1.pmlPercent.value;
         <%--if (treatyType != "0") {
	        if (treatyType == "1") {
	            document.getElementById('treatyQS2').style.display = 'block';
	            document.getElementById('treatyQS4').style.display = 'none';
	            document.getElementById('treatySurp3').style.display = 'none';
	            document.getElementById('limitOrigCurPml').value = document.retro1.limitOrigCurPml.value;
	            document.getElementById('treatyLimitsurplusOCPml').value = "";
	            document.getElementById('faclimitOrigCurPml').value = "";
	        }
	        else if (treatyType == "2") {
	            document.getElementById('treatySurp3').style.display = 'block';
	            document.getElementById('treatyQS2').style.display = 'none';
	            document.getElementById('treatyQS4').style.display = 'none';
	            document.getElementById('limitOrigCurPml').value = "";
	            document.getElementById('treatyLimitsurplusOCPml').value = document.retro1.treatyLimitsurplusOCPml.value;
	            document.getElementById('faclimitOrigCurPml').value = "";
	        }
	        else if (treatyType == "3") {
	            document.getElementById('treatySurp3').style.display = 'block';
	            document.getElementById('treatyQS2').style.display = 'block';
	            document.getElementById('treatyQS4').style.display = 'none';
	            document.getElementById('limitOrigCurPml').value = document.retro1.limitOrigCurPml.value;
	            document.getElementById('treatyLimitsurplusOCPml').value = document.retro1.treatyLimitsurplusOCPml.value;
	            document.getElementById('faclimitOrigCurPml').value = "";
	
	        }
        }
         else if (("SR" == factreaty || "FO" == factreaty || "TO" == factreaty) && "TR" != factreaty){
         		document.getElementById('treatyQS2').style.display = 'none';
         		document.getElementById('treatyQS4').style.display = 'block';
	            document.getElementById('treatySurp3').style.display = 'none';
	            document.getElementById('faclimitOrigCurPml').value = document.retro1.faclimitOrigCurPml.value;
	            document.getElementById('treatyLimitsurplusOCPml').value = "";
	            document.getElementById('limitOrigCurPml').value = "";
         
         }
         else{
          	document.getElementById('pmls').style.display = 'none';
          	document.getElementById('pmlPercent').value = "";
	       document.getElementById('treatyQS2').style.display = 'none';
	        document.getElementById('treatyQS4').style.display = 'none';
	        document.getElementById('treatySurp3').style.display = 'none';
	        document.getElementById('limitOrigCurPml').value = "";
	        document.getElementById('treatyLimitsurplusOCPml').value = "";
	        document.getElementById('faclimitOrigCurPml').value = "";
         }--%>
    }
    else if (pml == "N") {
        document.getElementById('pmls').style.display = 'none';
        document.getElementById('pmlPercent').value = "";
       <%-- document.getElementById('treatyQS2').style.display = 'none';
        document.getElementById('treatyQS4').style.display = 'none';
        document.getElementById('treatySurp3').style.display = 'none';
        document.getElementById('limitOrigCurPml').value = "";
        document.getElementById('treatyLimitsurplusOCPml').value = "";
        document.getElementById('faclimitOrigCurPml').value = "";--%>
    }
}

function calculateRetroTreatyQSPML() {
	<%--var tempLimitOrigCur =0;
	var tempPMLPercent  = document.retro1.pmlPercent.value;
	    tempPMLPercent = tempPMLPercent.replace(new RegExp(',', 'g'), '');
	var retroType = document.retro1.retroType.value;
	if("TR" == retroType){
	    tempLimitOrigCur = document.retro1.limitOrigCur.value;
	    tempLimitOrigCur = tempLimitOrigCur.replace(new RegExp(',', 'g'), '');
	}
	else{
		tempLimitOrigCur = document.retro1.faclimitOrigCur.value;
	    tempLimitOrigCur = tempLimitOrigCur.replace(new RegExp(',', 'g'), '');
	}
    var treatyType = document.retro1.treatyType.value;
    
    var tempLimitOrigCurPml = 0.00;
    if (tempLimitOrigCur != '' && tempLimitOrigCur != null && tempPMLPercent != '' && tempPMLPercent != null) {
        tempLimitOrigCurPml = (tempLimitOrigCur * tempPMLPercent) / 100;
    }
    if("TR" == retroType){
    	document.retro1.limitOrigCurPml.value = tempLimitOrigCurPml.toFixed(2);
    }
    else{
    	document.retro1.faclimitOrigCurPml.value  = tempLimitOrigCurPml.toFixed(2);
    }--%>
}

function calculateRetroTreatySurplusPML() {
   <%-- var tempTreatyLimitsurplusOC = document.retro1.treatyLimitsurplusOC.value;
    tempTreatyLimitsurplusOC = tempTreatyLimitsurplusOC.replace(new RegExp(',', 'g'), '');

    var tempPMLPercent = document.retro1.pmlPercent.value;
    tempPMLPercent = tempPMLPercent.replace(new RegExp(',', 'g'), '');

    var treatyType = document.retro1.treatyType.value;
    var tempTreatyLimitsurplusOCPml = 0.00;
    if (tempTreatyLimitsurplusOC != '' && tempTreatyLimitsurplusOC != null && tempPMLPercent != '' && tempPMLPercent != null) {
        tempTreatyLimitsurplusOCPml = (tempTreatyLimitsurplusOC * tempPMLPercent) / 100;
    }
    document.retro1.treatyLimitsurplusOCPml.value = tempTreatyLimitsurplusOCPml.toFixed(2);--%>
}

function calculateEPIPML() {
    var tempPMLPercent = document.retro1.pmlPercent.value;
    tempPMLPercent = tempPMLPercent.replace(new RegExp(',', 'g'), '');

    var tempEPIPML = 0.00;
    var tempEPI = document.getElementById("retro1_epi").value;
    tempEPI = tempEPI.replace(new RegExp(',', 'g'), '');

    if (tempPMLPercent != '' && tempPMLPercent != null && tempEPI != '' && tempEPI != null) {
        tempEPIPML = (tempPMLPercent * tempEPI) / 100;
    }
    document.retro1.epipml.value = tempEPIPML.toFixed(2);
}

function Commas(value) {
    if (value == "2") {
        document.retro1.limitOrigCur.value = Comma(document.retro1.limitOrigCur.value);
        document.retro1.cedReten.value = Comma(document.retro1.cedReten.value);
        document.retro1.treatyLimitsurplusOC.value = Comma(document.retro1.treatyLimitsurplusOC.value);
        //document.retro1.limitOrigCurPml.value = Comma(document.retro1.limitOrigCurPml.value);
        //document.retro1.treatyLimitsurplusOCPml.value = Comma(document.retro1.treatyLimitsurplusOCPml.value);
        document.retro1.epi.value = Comma(document.retro1.epi.value);
        //document.retro1.epipml.value = Comma(document.retro1.epipml.value);
        //document.retro1.xlCost.value = Comma(document.retro1.xlCost.value);
    }
    else if (value == "3") {
        document.retro1.limitOrigCur.value = Comma(document.retro1.limitOrigCur.value);
        document.retro1.cedReten.value = Comma(document.retro1.cedReten.value);
        document.retro1.treatyLimitsurplusOC.value = Comma(document.retro1.treatyLimitsurplusOC.value);
        //document.retro1.limitOrigCurPml.value = Comma(document.retro1.limitOrigCurPml.value);
        //document.retro1.treatyLimitsurplusOCPml.value = Comma(document.retro1.treatyLimitsurplusOCPml.value);
        document.retro1.epi.value = Comma(document.retro1.epi.value);
        //document.retro1.epipml.value = Comma(document.retro1.epipml.value);
        //document.retro1.xlCost.value = Comma(document.retro1.xlCost.value);
    } else if (value == "4") {
        document.retro1.limitOrigCur.value = Comma(document.retro1.limitOrigCur.value);
        document.retro1.cedReten.value = Comma(document.retro1.cedReten.value);
        document.retro1.treatyLimitsurplusOC.value = Comma(document.retro1.treatyLimitsurplusOC.value);
        //document.retro1.limitOrigCurPml.value = Comma(document.retro1.limitOrigCurPml.value);
        //document.retro1.treatyLimitsurplusOCPml.value = Comma(document.retro1.treatyLimitsurplusOCPml.value);
        document.retro1.epi.value = Comma(document.retro1.epi.value);
        //document.retro1.epipml.value = Comma(document.retro1.epipml.value);
        //document.retro1.xlCost.value = Comma(document.retro1.xlCost.value);
    } else if (value == "5") {
        document.retro1.limitOrigCur.value = Comma(document.retro1.limitOrigCur.value);
        document.retro1.cedReten.value = Comma(document.retro1.cedReten.value);
        document.retro1.treatyLimitsurplusOC.value = Comma(document.retro1.treatyLimitsurplusOC.value);
        //document.retro1.limitOrigCurPml.value = Comma(document.retro1.limitOrigCurPml.value);
        //document.retro1.treatyLimitsurplusOCPml.value = Comma(document.retro1.treatyLimitsurplusOCPml.value);
        document.retro1.epi.value = Comma(document.retro1.epi.value);
        //document.retro1.epipml.value = Comma(document.retro1.epipml.value);
        //document.retro1.xlCost.value = Comma(document.retro1.xlCost.value);
    }
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
			document.retro1.action='${pageContext.request.contextPath}/CancelProposalRiskDetails.action'; 
			document.retro1.submit();
			}
			else {
			document.getElementById("proposal_no").disabled=true;
				 return false; 
			}
		}
		else{
			document.retro1.action='${pageContext.request.contextPath}/menuPortfolio'; 
			document.retro1.submit();
		}
	
}
getRateField('<s:property value="proposalType"/>');
function getRateField(val){
if( val=='R' || val=='H'){
document.getElementById('ratId').style.display='inline';

}else{

document.getElementById('runoffYear').value='0';
document.getElementById('ratId').style.display='none';
}
}

getLocDeta('<s:property value="LOCIssued"/>');
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

getFixRate('<s:property value="cessionExgRate"/>');
function getFixRate(val){
	if ("F" ==val ){
		document.getElementById("fixed").style.display='inline';
		document.getElementById("cessionExgRate").style.width='150px';
		document.getElementById("cessionExgRate").style.float='left';
	}
	else{
		document.getElementById("fixed").style.display='none';
		document.getElementById("fixedRate").value='';
		document.getElementById("cessionExgRate").style.width='290px';
	} 
}
//getDummyCon('<s:property value="dummyCon"/>');
function getDummyCon(val){
	if ("D" ==val ){
		document.getElementById("CeddingId").value="64";
		document.getElementById("BrokerId").value="63";
		document.getElementById("retroType").value="TR";
		document.getElementById("noRetroCess").value="1";
		document.getElementById("noRetroCess").disabled=true;
		document.retro1.cedingCo.disabled=true;
		document.retro1.broker.disabled=true;
		document.retro1.retroType.disabled=true;
		getFieldValue("TR");
	}
	else{
		document.getElementById("CeddingId").value='';
		document.getElementById("BrokerId").value='';
		document.getElementById("retroType").value='';
		document.retro1.cedingCo.disabled=false;
		document.retro1.broker.disabled=false;
		document.retro1.retroType.disabled=false;
		getFieldValue("");
	} 
}
getFieldValue('<s:property value="retroType"/>');
function getFieldValue(val){
	if("SR"==val){
		document.getElementById("insured").style.display='inline';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='none';
		document.getElementById('treatyType').value='0';
	}
	else if("TR"==val){
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='inline';
		document.getElementById("name").style.display='inline';
	}
	else if("FO"==val || "TO"==val){
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='inline';
		document.getElementById('treatyType').value='0';
	}
	else{
		document.getElementById("insured").style.display='none';
		document.getElementById("type").style.display='none';
		document.getElementById("name").style.display='none';
		document.getElementById('treatyType').value='0';
	}
	GetPML();
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