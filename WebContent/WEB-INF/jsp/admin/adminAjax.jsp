<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='!"directCeding".equalsIgnoreCase(dropDown)'>
<style type="text/css">
 .tableColWidth {
 	min-width: 200px;
 	max-width: 750px;
 	width: 200px;
 	white-space: normal;
 }
 
  .tableColWidth1 {
 	min-width: 50px;
 	max-width: 750px;
 	width: 450px;
 	white-space: normal;
 }
 
 .tableColNoWrap {
 	min-width: 100px;
 	max-width: 750px;
 	width: 100px;
 	white-space: nowrap;
 }
 .table-overflow{
    overflow: scrollX;
 }
 </style>
 <script type="text/javascript">
	 $(function() {
	    $('.accumDate').datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
	  });
</SCRIPT>	
</s:if>	
<s:if test='"currencyRate".equalsIgnoreCase(dropDown)'>
  <s:textfield name="usCurrencyRate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
</s:if>
<s:elseif test='"DocList".equalsIgnoreCase(dropDown)'>
<td class="formCon"  valign="top" id="DocListDiv">
<div class="boxcontent">
											<table class="table table-bordered" id="DocTable">
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
												</tr>
												</thead>
												<tbody>
												
												<s:iterator value="docuList" id="index">
												<tr>
													<td class="formCon" style="text-align: center;">${index+1}
												 		<s:hidden name="docId[%{index}]" id="docId" value="%{#index+1}"/>
												 	</td>
													<td class="formCon"  valign="top" >
														<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" />
													</td>
													<td class="formCon" style="text-align: center;">
														<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" theme="simple" />												 
													</td>													
													<td class="formCon">
														<s:file name="upload"  cssClass="inputBox" theme="simple" />
													</td>
												</tr>
												
												</s:iterator>
												
												</tbody>
											</table>											
										</div>
	</s:elseif>
	<s:elseif test='"exRate".equalsIgnoreCase(dropDown)'>
     	<s:hidden name="exchRate" id="exchRate"/>
	</s:elseif>
	<s:elseif test='"section".equalsIgnoreCase(dropDown)'>
		<s:select list="sectionList" name="sectionName" id="sectionName"  listKey="SECTION_NO" listValue="SECTION_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />	</s:elseif>
	<s:elseif test='"reclass".equalsIgnoreCase(dropDown)'>
	<s:select list="renewalDepartIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="slidedepartId" id="slidedepartId" cssClass="inputBoxS" headerKey="" headerValue="---Select---" /></s:elseif>
	<s:elseif test='"section1".equalsIgnoreCase(dropDown)'>
		<s:textfield cssClass="inputBox" name="sectionName" cssStyle="width: 85%; float:left;" />
		<span class="cr" onclick="GetSection();" style="cursor: pointer" title="Click here"><i class="cr-icon glyphicon glyphicon-ok"></i></span>	</s:elseif>
	<s:elseif test='"error".equalsIgnoreCase(dropDown)'>
	<s:hidden name="sectionName"></s:hidden>
	<span style="color:red;"><s:actionerror/></span>
	</s:elseif>
	<s:elseif test='"facexRate".equalsIgnoreCase(dropDown)'>
	<s:textfield name="usCurrencyRate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" />
	</s:elseif>
	<s:elseif test='"adjPrem".equalsIgnoreCase(dropDown)'>
	<s:textfield name="adjustment_premium" onblur="CalculationXOL(this.value);middleMinusRestriction(this);xolNetDue();this.value=Comma(this.value);" onclick="this.select();" cssStyle="text-align:right;" cssClass="inputBox" maxlength="26" value="%{adjustment_premium==null?'0.00':adjustment_premium}"/>
	</s:elseif>
	<s:elseif test='"currency".equalsIgnoreCase(dropDown)'>
	<s:if test='bankCurrencyList!=null'>
		<s:select name="currency" id="currency"
			list="bankCurrencyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" 
			cssClass="inputBoxS"
			onblur="GetExchangeRate();getBaseCurrencyAmount();"
			/>
			</s:if>
			<s:else>
			<s:select list="#{}" name="currency" id="currency"
				cssClass="inputBoxS"
                  headerKey="0"
				headerValue="---Select---" />
				</s:else>
	</s:elseif>
     <s:elseif test='"paymentCurrency".equalsIgnoreCase(dropDown)'>
		<s:textfield name="exrate" id="exrate" cssStyle="text-align:right;" readonly="true" cssClass="inputBox" maxlength="30" onblur="getBaseCurrencyAmount();" />
	</s:elseif>

	<s:elseif test='"exc_Rate".equalsIgnoreCase(dropDown)'>
		<s:textfield name="exc_Rate"  cssClass="inputBox" cssStyle="text-align:right;" readonly="true"/>													
	</s:elseif>
	<s:elseif test='"exc_Rate1".equalsIgnoreCase(dropDown)'>
		<s:textfield name="exc_Rate" id="exc_Rate" cssClass="inputBox" cssStyle="text-align:right;" readonly="true"/>													
	</s:elseif>
	<s:elseif test='"retroCeding".equalsIgnoreCase(dropDown) || "Dup".equalsIgnoreCase(mode)'>
	    <s:select list="retroContractList" listKey="CONTDET1" listValue="CONTDET2" name="retroCeding[%{retroListName}]"  id="retroCeding%{retroListName}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" />
	</s:elseif>
	<s:elseif test='"subclass".equalsIgnoreCase(dropDown)'>
	<%--<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='contNo!=null || !"".equals(contNo)?true:false}'/>--%>
		<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfit_center" id="subProfit_center" cssClass="inputBoxS"  multiple="true"   headerKey="ALL" headerValue="ALL" disabled='%{contNo != null && contNo != ""?true:RenewalMode!=null?false:"Y".equals(disableStatus)?true:false}' theme="simple" />
	</s:elseif>
	<s:elseif test='"presubclass".equalsIgnoreCase(dropDown)'>
		<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="subProfitId" id="subProfitId"  cssClass="inputBoxS"  multiple="true"   headerKey="ALL" headerValue="ALL"  theme="simple" />
	</s:elseif>
	<s:elseif test='"coversubclass".equalsIgnoreCase(dropDown)'>
		<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="coversubdepartId[%{id}]" id="coversubdepartId%{id}" cssClass="inputBoxS"  multiple="true"   headerKey="ALL" headerValue="ALL"   theme="simple" />
	</s:elseif>
	<s:elseif test='"xolcoversubclass".equalsIgnoreCase(dropDown)'>
		<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="xolcoversubdepartId[%{id}]" id="xolcoversubdepartId%{id}" cssClass="inputBoxS"  multiple="true"   headerKey="ALL" headerValue="ALL"   theme="simple" />
	</s:elseif>
	<s:elseif test='"coverdedsubclass".equalsIgnoreCase(dropDown)'>
		<s:select list="subProfit_centerlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" name="coversubdepartId[%{id}]" id="coversubdepartId%{id}" cssClass="inputBoxS"    headerKey="ALL" headerValue="ALL"   theme="simple" />
	</s:elseif>
	
      <s:if test='"exchRate".equalsIgnoreCase(dropDown)'>
       													<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:left;text-align:right;" />	
     </s:if>
     
     <s:if test='"Premium".equalsIgnoreCase(dropDown)'>
	   <s:textfield cssClass="inputBox" name="premiumQuotaShare" id="premiumQuotaShare" onblur="Caculate(this.value);middleMinusRestriction(this);facNetDue();" cssStyle="text-align: right;" />
     </s:if>
     
     <s:if test='"mdpremium".equalsIgnoreCase(dropDown)'>
       <s:textfield name="md_premium" onblur="CalculationXOL(this.value);xolNetDue();middleMinusRestriction(this);this.value=Comma(this.value);" onclick="this.select();" cssClass="inputBox" cssStyle="text-align:right;"  />
     </s:if>
     
     <s:if test='"reportCeding".equalsIgnoreCase(dropDown)'>
       <s:select list="ceddingCompanyList" listKey="CUSTOMER_ID" listValue="NAME" cssClass="inputBoxS" name="cedingId" id="cedingId" headerKey="-1" headerValue="---ALL---" />
     </s:if>

     <s:if test='"exachange".equalsIgnoreCase(dropDown)'>
       <s:textfield name="exachangeValList[%{count}]" id="exachangeValList[%{count}]" cssClass="inputBox" cssStyle="text-align: right;" value="%{exchRate}" readonly="true"/>
     </s:if>
     
     
     <s:if test='"uwYear".equalsIgnoreCase(dropDown)'>
        <s:select name="uwYearValList[%{listName}]" id="uwYearValList[%{listName}]" list="uwYearList" listKey="CONTDET1" listValue="CONTDET2" cssClass="inputBoxS" onchange="setRetroContract('%{retroType}',this.value,'cedingCompany%{listName}','%{listName}')" headerKey="" headerValue="---Select---" />
     </s:if>
     <s:if test='"uwc".equalsIgnoreCase(dropDown)'>
     <s:textfield name="maxiumlimit" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals15(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);" maxlength="26"/>
     </s:if>
      <s:if test='"uwc2".equalsIgnoreCase(dropDown)'>
     <s:textfield name="maxLimit_Product" cssClass="inputBox" cssStyle="text-align: right;" onkeyup="checkDecimals15(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value);" maxlength="30" />
     </s:if>
    <s:elseif test='"crestaID".equalsIgnoreCase(dropDown)'>
    <s:select  list="crestaIDAjsxList"  name="crestaId[%{id}]" id="crestaId[%{id}]" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CRESTA_ID"  listValue="CRESTA_ID"   onchange="getCrestaNameAjax(this.value,'crestaname%{id}',%{id})"/>
	</s:elseif>
	<s:elseif test='"crestaName".equalsIgnoreCase(dropDown)'>
	<s:textfield name="crestaName[%{id}]" id="crestaName[%{id}]" cssClass="inputBox" value="%{crestaNameAjax}" readonly="true"></s:textfield>
    </s:elseif>
    
<s:elseif test='"calculate".equalsIgnoreCase(dropDown)'>
<br class="clear"/>
<br class="clear"/>

<div  class="col-xs-12 " >
<b>Total:</b>
<s:if test="lossSum.size()>0">
<table class="footable table-overflow" width="100%" id="newgen1" >
<thead>
	<tr>
		<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.year" />  </th>
		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.insuredclaim" /> </th>
		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premium" /> </th>
		<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossratio" /> </th>
	</tr>
</thead>
<tbody>
<s:iterator value="lossSum" var="list" status="stat">
<tr>
		<td>
		<s:property value="%{#stat.count}"/>
		</td>
		<td>
		<s:textfield name="yearSum[%{#stat.count-1}]" id="yearSum%{#stat.count-1}"  cssClass="inputBox"  disabled="true" theme="simple"/>
		</td>
		<td>
		<s:textfield name="lossSum[%{#stat.count-1}]" id="lossSum%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" disabled="true" theme="simple"  />
		</td>
		<td >
		<s:textfield name="premSum[%{#stat.count-1}]" id="premSum%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" disabled="true" theme="simple"  />
		</td>
		<td >
		<s:textfield name="ratioSum[%{#stat.count-1}]" id="ratioSum%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;"  disabled="true" theme="simple"  />
		</td>
	</tr>
	
</s:iterator>
 
</tbody>
</table>
</s:if>
<s:else>
    <div class="panel panel-primary" >
        <div class="panel-body">
            No Data Available
        </div>
    </div>
</s:else>
<br class="clear"/>
</div>

</s:elseif>
     <s:elseif test='"cedingCompany".equalsIgnoreCase(dropDown) || "Duplicate".equalsIgnoreCase(mode)'>
        <s:select list="cedingCompanyList" listKey="CONTDET1" listValue="CONTDET1" name="cedingCompanyValList[%{listName}]"  id="cedingCompanyValList[%{listName}]"  cssClass="inputBoxS" headerKey="" headerValue="--Select--" />
     </s:elseif>
     <s:elseif test='"departmentMenu".equalsIgnoreCase(dropDown)'>
     	<s:iterator value="departmentList" var="department" status="stat">
			<li>
				<s:a href="#" onclick="processMenuList(%{productId},'%{productName}','%{#department.TMAS_DEPARTMENT_ID}','%{#department.TMAS_DEPARTMENT_NAME}','department%{productId}%{#department.TMAS_DEPARTMENT_ID}')"><s:property value="#department.TMAS_DEPARTMENT_NAME"/> <i class="glyphicon glyphicon-hand-right pullRight"> </i> </s:a> 
				<ul id="department${productId}${TMAS_DEPARTMENT_ID}">
					
				</ul>	
			</li>
		</s:iterator>
     </s:elseif>
     <s:elseif test='"processMenu".equalsIgnoreCase(dropDown)'>
	     <s:iterator value="processList" var="process" status="stat">
			<li>
				<s:a href="#" onclick="finalMenuList('%{productId}','%{productName}','%{departmentId}','%{departmentName}','%{#process.PROCESS_ID}','%{#process.PROCESS_NAME}','Process%{#process.PROCESS_ID}')"><s:property value="#process.PROCESS_NAME"/> <i class="glyphicon glyphicon-hand-right pullRight"> </i> </s:a> 
				<ul id="Process${PROCESS_ID}">
					
				</ul>
			</li>
		</s:iterator>
	</s:elseif>
	<s:elseif test='"finalMenu".equalsIgnoreCase(dropDown)'>
		<s:iterator value="finalMenuList" var="finalMenu" status="stat">
			<li>
				<s:a href="#" onclick="dockNavigation('%{productId}','%{productName}', '%{departmentId}', '%{departmentName}', '%{processId}', '%{processName}', '%{#finalMenu.MENU_ID}', '%{#finalMenu.MENU_URL}','%{#finalMenu.MENU_NAME}');"><s:property value="#finalMenu.MENU_NAME"/></s:a>
			</li>
		</s:iterator>
	</s:elseif>
	<s:elseif test='"directCeding".equalsIgnoreCase(dropDown)'>
	    <s:property value="name" />
	</s:elseif>
	<s:elseif test='"product".equalsIgnoreCase(reqform)'>
	    <s:select list="productList" name="productId" id="pid" onchange="getProduct(this.value,'department')" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
	</s:elseif>
	<s:elseif test='"department".equalsIgnoreCase(reqform)'>
	<s:select list="departmentList" name="department" id="dpid" onchange="getProduct(this.value,'process')" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
	</s:elseif>
	<s:elseif test='"process".equalsIgnoreCase(reqform)'>
	<s:select list="processList" name="process" id="processid" onchange="getProduct(this.value,'menu')" listKey="PROCESS_ID" listValue="PROCESS_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
	</s:elseif>
	<s:elseif test='"menu".equalsIgnoreCase(reqform)'>
	<s:select list="menuList" name="menuId"  listKey="MENU_ID" listValue="MENU_NAME"  headerKey="-1" headerValue="---Select---" cssClass="inputBoxS" />
	</s:elseif>
	<s:elseif test='"departmentId".equalsIgnoreCase(reqform)'>
	    <s:select list="departmentList" name="departmentId" cssClass="inputBoxS"  headerKey="-1" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME"  headerValue="---Select---" />
	</s:elseif>
	<s:elseif test='"departmentId1".equalsIgnoreCase(reqform)'>
	    <s:select list="departmentList" name="departmentId" cssClass="inputBoxS"  headerKey="-1" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME"  headerValue="---Select---" disabled="mode=='update'?true:false"/>
	    <s:if test='"update".equals(mode)'>
			<s:hidden name="departmentId"></s:hidden>
		</s:if>
	</s:elseif>
	<s:elseif test='"accountno".equalsIgnoreCase(reqform)'>
	<s:if test='currencyName!=null'>
	<div class="boxcontent">
		<s:if test='accountList!=null'>
		<div class="textfieldA25">
			<s:text name="Currency Name" />
		</div>
		<div class="textfieldA75">
			<s:property value="currencyName"/>		
		</div>
		<div class="textfieldA25 no-print">
			<s:text name="dn.AccNo" />
		</div>
		<div class="textfieldA75">
			<s:select list="accountList" name="accountId" id="accountId" cssClass="no-print" listKey="BANK_AC_NO" listValue="BANK_AC_NO"  headerKey="" headerValue="--Select--" onchange='bankChange(this.value,"BranchAddressDiv");'/>
		</div>
		</s:if>
	</div>
	
	</s:if>
	</s:elseif>
	<s:elseif test='"branchAddress".equalsIgnoreCase(reqform)'>
		<div id = "BranchAddressDiv">
		<s:if test='bankAddress!=null'>
				<div class="boxcontent">
					<div class="textfieldA25">
						<s:text name="Account Number" />
					</div>
					<div class="textfieldA75">
						<s:property value="bancAccountNo"/>
					</div>
					<div class="textfieldA25">
						<s:text name="dn.address" />
					</div>
					<div class="textfieldA75">
						<s:property value="bankAddress"/>
					</div>
				</div>
		</s:if>
		</div>
		</s:elseif>
<s:elseif test='"newgen".equalsIgnoreCase(dropDown)'>
<table class="table table-bordered" width="100%" id="newgen">
	<thead>
		<tr>
			<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
			<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitPoc" />  </th>
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
					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
					</td>
				<td id="coverdepartIdtable<s:property value='%{#stat.count-1}'/>">
					<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---" theme="simple"  />
					<%--<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" /><%--disabled="0==(#stat.count-1)?true:false" --%>
					<s:if test='0==(#stat.count-1)'>
					<s:text name="(Main Class)" />
					</s:if>
				</td>
				<td>
				<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value)" maxlength="30"   theme="simple"/>
				
				</td>
				<td>
				<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); allow2DigitDecValues(this);middleMinusRestrictionNeg(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
				</td>
				<td>
					<s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  theme="simple"/>
				</td>
				<td>
				<s:textfield name="netMaxRetentPer[%{#stat.count-1}]" id="netMaxRetentPer%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value); middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal()" maxlength="30" theme="simple" />
				</td>
				<td align="center">
				<s:if test='0!=(#stat.count-1)'>
				<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
				</s:if>
				</td>
				
			</tr>
			</s:iterator>
			<s:hidden name="loopcount" id="loopcount" ></s:hidden>
		</tbody>
		</table>
		<br class="clear"/>
		<s:if test=' !"Y".equals(disableStatus1)'>
			<div class="boxcontent" align="center">
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
			</div>
		</s:if>
</s:elseif>
<s:elseif test='"newgen1".equalsIgnoreCase(dropDown)'>
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
		<th width="13%"  style="text-align: center; vertical-align: middle;"> <s:text name="label.gnpiasperoffer" /> </th>
		<th width="7" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
	</tr>
	<tr>
		<th></th>
		<th></th>
		<th colspan="2">(Whichever is lower)</th>
		<th colspan="2">(Whichever is higher)</th>
		<th></th>
		<th></th>
		<th></th>
	</tr>
</thead>
<tbody>
<s:iterator value="CoverList" var="list" status="stat">
	<tr>
			<td>
				<s:textfield name="coverSNoS[%{#stat.count-1}]" id="coverSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
			</td>
			<td id="coverdepartIdtable1<s:property value='%{#stat.count-1}'/>">
				<s:select list="coverDepartmentList" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  theme="simple"/>
				<%--<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartIdS[%{#stat.count-1}]" id="coverdepartIdS[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" /><%--disabled="0==(#stat.count-1)?true:false" --%>
				<s:if test='0==(#stat.count-1)'>
				<s:text name="(Main Class)" />
				</s:if>
			</td>
			<td>
			<s:textfield name="coverLimitAmount[%{#stat.count-1}]" id="coverLimitAmount[%{#stat.count-1}]"  cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
			</td>
			<td>
			<s:textfield name="coverLimitPercent[%{#stat.count-1}]" id="coverLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allowOneDot(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
			</td>
			<td>
			<s:textfield name="deductableLimitAmount[%{#stat.count-1}]" id="deductableLimitAmount[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
			</td>
			<td>
			<s:textfield name="deductableLimitPercent[%{#stat.count-1}]" id="deductableLimitPercent[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allowOneDot(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" />
			</td>
			<td>
				<s:textfield name="egnpiAsPerOffSlide[%{#stat.count-1}]" id="egnpiAsPerOffSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCalSlide()" maxlength="30"  theme="simple"/>
			</td>
			<td>
				<s:textfield name="gnpiAsPOSlide[%{#stat.count-1}]" id="gnpiAsPOSlide%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiVal('newgen1');" maxlength="30"    theme="simple" disabled="true"/>
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
	<br class="clear"/>
	<s:if test=' !"Y".equals(disableStatus1)'>
			<div class="boxcontent" align="center">
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');" />
			</div>
	</s:if>
</s:elseif>
<s:elseif test='"remark".equalsIgnoreCase(dropDown)'>
<table class="table table-bordered" width="100%" id="remark">
		<thead>
			<tr>
				<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
				<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.description" />  </th>
				<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark1" />  </th>
				<th width="15.8%" style="text-align: center; vertical-align: middle;"> <s:text name="label.Remark2" /> </th>
				<th width="10.8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="remarkList" var="list" status="stat">
		<tr>
				<td >
					<s:textfield name="remarkSNo[%{#stat.count-1}]" id="remarkSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
					</td>
				<td>
				<s:textfield name="description[%{#stat.count-1}]" id="description[%{#stat.count-1}]"  cssClass = "inputBox"  cssStyle="width: 100%; height: 100%;" theme="simple"/>
				
				</td>
				<td>
				<s:textarea name="remark1[%{#stat.count-1}]" id="remark1[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " theme="simple" />
				</td>
				<td>
				<s:textarea name="remark2[%{#stat.count-1}]" id="remark2[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; " theme="simple"/>
				</td>
				<td align="center">
				<s:if test='0!=(#stat.count-1)'>
				<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="deleteRemark('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
				</s:if>
				</td>
				<s:hidden name="remarkCount" id="remarkCount" ></s:hidden>
			</tr>
			</s:iterator>
			</tbody>
			</table>
			<br class="clear"/>
			<div class="boxcontent" align="center">
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRemarkRow('remark');" />
			</div>
</s:elseif>
<s:elseif test='"coverdeductable".equalsIgnoreCase(dropDown)'>
<table class="table table-bordered" width="100%" id="newgen">
	<thead>
		<tr>
			<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.subClass" />  </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Type" />  </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlhundredper" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitOC" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.pmlper" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleOC" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverageDays" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductableDays" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premiumRate" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.gWPIOC100" /> </th>
			<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Remarks" /> </th>
			<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="coverdeductableList" var="list" status="stat">
		<tr>
				<td>
					<s:textfield name="coverSNo[%{#stat.count-1}]" id="coverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
					</td>
				<td>
				
				<s:if test='0==(#stat.count-1)'>
				<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}" theme="simple"/>
				<s:hidden name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
				<s:text name="(Main Class)" />
				</s:if>
				<s:else>
				<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})" theme="simple"/>
				</s:else>
				</td>
				<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
				
				<s:if test='0==(#stat.count-1)'>
				<s:select list="subProfit_center" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false" theme="simple"/>
				<s:text name="(Main Class)" />
				</s:if>
				<s:else>
				<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" theme="simple" />
				</s:else>
				</td>
				<td>
				<s:select list="coverTypelist" listValue="DETAIL_NAME" listKey="TYPE" name="coverTypeId[%{#stat.count-1}]" id="coverTypeId%{#stat.count-1}" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple" onchange="getPmlText()"/>
				</td>
				<td>
					<s:textfield name="pmlHundredPer[%{#stat.count-1}]" id="pmlHundredPer%{#stat.count-1}"  cssClass ="inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  theme="simple"/>
				</td>
				 <td>
				<s:textfield name="coverLimitOC[%{#stat.count-1}]" id="coverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getdeductableCal();getCoverGWPI(%{#stat.count-1});getEgnpiCal();getTotalValue();getTotalEGNPI();" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  theme="simple"/>
				</td>
				<td>
					<s:textfield name="pmlPerList[%{#stat.count-1}]" id="pmlPerList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;"  maxlength="30"   onkeyup="allow2DigitDecValues(this);middleMinusRestrictionNeg(this);negative(this.id,this.value);javascript:this.value=Comma(this.value);" disabled="true" theme="simple"/>
				</td>
				<td>
				<s:textfield name="deductableLimitOC[%{#stat.count-1}]" id="deductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' theme="simple"/>
				</td>
				<td>
				<s:textfield name="coverageDays[%{#stat.count-1}]" id="coverageDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);" maxlength="30" theme="simple" />
				</td>
				<td><s:textfield name="deductableDays[%{#stat.count-1}]" id="deductableDays[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);" maxlength="30"  theme="simple"/></td>
				<td><s:textfield name="premiumRateList[%{#stat.count-1}]" id="premiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);getCoverGWPI(%{#stat.count-1});getTotalEGNPI();" maxlength="30"  theme="simple"/></td>
				<td><s:textfield name="egnpiAsPerOff[%{#stat.count-1}]" id="egnpiAsPerOff%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getEgnpiCal();getTotalEGNPI();" maxlength="30"  theme="simple"/></td>
				<td>
				<s:textarea name="coverRemark[%{#stat.count-1}]" id="coverRemark[%{#stat.count-1}]" cssClass="inputBox" cssStyle="width: 100%; resize: vertical; "  theme="simple"/>
				</td>
				<td align="center">
				<s:if test='0!=(#stat.count-1)'>
				<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteFacRow('<s:property value="%{#stat.count-1}"/>')" />
				</s:if>
				</td>
				<s:hidden name="loopcount" id="loopcount" ></s:hidden>
			</tr>
			</s:iterator>
			<s:hidden name="totalCoverage" id="totalCoverage"></s:hidden>
			<s:hidden name="totalGWPI" id="totalGWPI"></s:hidden>
			<s:hidden name="totalDeductible" id="totalDeductible"></s:hidden>
			<%-- <tr id="gnpi2" style="display:none;" >
			<td width="15.8%">Total</td>
			<td width="15.8%">Total</td>
			<td width="15.8%">Total</td>
			<td width="15.8%">Total</td>
			<td width="15.8%">Total</td>
			<td>Total</td>
			<td>Total</td>
			</tr>--%>
		</tbody>
		</table>
		
<br class="clear"/>
		<table class="table table-bordered" width="100%" id="newgen">
		<tr>
			<td width="20%"> Total:  </td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"><s:textfield name="totalCoverageVal" id="totalCoverageVal"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" onkeyup="allow2DigitDecValues(this);"/></td>
			<td class="tableColWidth" ></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth" ><s:textfield name="totalGWPIVal" id="totalGWPIVal"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" onkeyup="allow2DigitDecValues(this);"/></td>
			<td class="tableColWidth"></td>
			<td class="tableColWidth1"></td>
		</tr></table>
	<br class="clear"/>
	<s:if test=' !"Y".equals(disableStatus1)'>
	<div class="boxcontent" align="right">
		<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insFacCoverRow('newgen');" />
	</div>
	</s:if>
	<script type="text/javascript">
	<s:iterator value="coverdeductableList"  status="stat"> 
	 var val='<s:property value="%{#stat.count-1}"/>'; 
	$(document).ready(function() {
    $("#coversubdepartId"+val).multiselect({     
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
        		var val1 =document.getElementById("coverpreVal"+val).value;
          		 var val = $("#coversubdepartId"+val).val();
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!='' ){
		          $("#coversubdepartId"+val).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#coversubdepartId"+val).val(val);
		           $("#coversubdepartId"+val).multiselect("refresh");
		           document.getElementById("coverpreVal"+val).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#coversubdepartId"+val).multiselect('clearSelection');
		          	$("#coversubdepartId"+val).val('ALL');
		          	 $("#coversubdepartId"+val).multiselect("refresh");
		          	 document.getElementById("coverpreVal"+val).value = 'ALL';
		          }
      	}                     
    }); 
	
 // var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");

});
</s:iterator>
</script>
</s:elseif>
<s:elseif test='"xolcoverdeductable".equalsIgnoreCase(dropDown)'>
<table class="table table-bordered" width="100%" id="xolnewgen">
														<thead>
															<tr>
																<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.subClass" />  </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.coverLimitOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.deductibleOC" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premiumRate" /> </th>
																<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.gWPIOC100" /> </th>
																<th class="tableColWidth1" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
															</tr>
														</thead>
														
														<tbody>
														<s:iterator value="xolCoverdeductableList" var="list" status="stat">
															<tr>
																	<td>
												 					<s:textfield name="xolcoverSNo[%{#stat.count-1}]" id="xolcoverSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
												 					</td>
																	<td>
																	
																	<s:if test='0==(#stat.count-1)'>
																	<s:textfield name="departmentName1" cssClass="inputBox" value="%{departmentName}"  theme="simple"/>
																	<s:hidden name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" value="%{#session.DepartmentId}"></s:hidden>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="departIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="xolcoverdepartId[%{#stat.count-1}]" id="xolcoverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled="0==(#stat.count-1)?true:false" onchange="getAjaxXOlCover(this.value,'xolcoverdepartid%{#stat.count-1}',%{#stat.count-1})" theme="simple"/>
																	</s:else>
																	</td>
																	
																	<td id="xolcoverdepartid<s:property value='%{#stat.count-1}'/>">
																	<s:if test='0==(#stat.count-1)'>
																	<s:select list="subProfit_center" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" disabled="0==(#stat.count-1)?true:false" theme="simple"/>
																	<s:text name="(Main Class)" />
																	</s:if>
																	<s:else>
																	<s:select list="%{coversubDepartList[#stat.count-1]}" name="xolcoversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="xolcoversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL" multiple="true"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" onchange="getAllValue(%{#stat.count-1})" theme="simple"/>
																	</s:else>
																	</td>
																	
																	
																	
																	 <td>
																	<s:textfield name="xolcoverLimitOC[%{#stat.count-1}]" id="xolcoverLimitOC%{#stat.count-1}"  cssClass = "inputBox"  cssStyle="text-align:right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getxolCoverGWPI(%{#stat.count-1});getTotalxolValue();" maxlength="30"  disabled='%{"Y".equals(disableStatus1)?true:false}'  theme="simple"/>
																	</td>
																	
																	<td>
																	<s:textfield name="xoldeductableLimitOC[%{#stat.count-1}]" id="xoldeductableLimitOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getTotalDeductable();" maxlength="30" disabled='%{"Y".equals(disableStatus1)?true:false}' theme="simple"/>
																	</td>
																	
																	<td><s:textfield name="xolpremiumRateList[%{#stat.count-1}]" id="xolpremiumRateList%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="checkDecimals(this);middleMinusRestrictionNeg(this);allowOneDot(this);hundredCheck(this.id,this.value);Itnegative(this.id,this.value);getxolCoverGWPI(%{#stat.count-1});getTotalGWPI();" maxlength="30"  theme="simple"/></td>
																	<td><s:textfield name="xolgwpiOC[%{#stat.count-1}]" id="xolgwpiOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"   theme="simple"/></td>
																	<td align="center">
																	<s:if test='0!=(#stat.count-1)'>
																	<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteFacRow('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
																	</s:if>
																	</td>
																	<s:hidden name="xolcoverpreVal[%{#stat.count-1}]" id="xolcoverpreVal%{#stat.count-1}"/>
																</tr>
																</s:iterator>
																
																<s:hidden name="xolLoopcount" id="xolLoopcount" ></s:hidden>
															</tbody>
															
															
															</table>
															
															<table class="table table-bordered" width="100%" id="xolnewgen">
															<tr>
																<td width="2%"> Total:&nbsp;  </td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth" ><s:textfield name="deductibleFacXol" id="deductibleFacXol"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth"><s:textfield name="deductible" id="deductible"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" onkeyup="allow2DigitDecValues(this);"/></td>
																<td class="tableColWidth"></td>
																<td class="tableColWidth"><s:textfield name="xoltotalGWPI" id="xoltotalGWPI"  cssClass="inputBox" cssStyle="text-align:right;" theme="simple" onkeyup="allow2DigitDecValues(this);"/> </td>
																<td class="tableColWidth1">&nbsp;&nbsp;&nbsp;</td>
															</tr></table>
															<br class="clear"/>
															<s:if test=' !"Y".equals(disableStatus1)'>
															<div class="boxcontent" align="right">
																<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insXolCoverRow('xolnewgen');" />
															</div>
															</s:if>
															<script type="text/javascript">
	<s:iterator value="xolCoverdeductableList"  status="stat"> 
	 var val='<s:property value="%{#stat.count-1}"/>'; 
	$(document).ready(function() {
    $("#xolcoversubdepartId"+val).multiselect({     
      	includeSelectAllOption: false,
        enableFiltering:true,
        numberDisplayed: 0,
        onChange: function(element, checked) {
        		var val1 =document.getElementById("xolcoverpreVal"+val).value;
          		 var val = $("#xolcoversubdepartId"+val).val();
		          if(val1!= null && val1=='ALL' && val !=null && val[1]!='' ){
		          $("#xolcoversubdepartId"+val).multiselect('clearSelection');
		          val = removeElementsWithValue(val, 'ALL');
		          $("#xolcoversubdepartId"+val).val(val);
		           $("#xolcoversubdepartId"+val).multiselect("refresh");
		           document.getElementById("xolcoverpreVal"+val).value = '';
		          }
		           else if (val !=null && val[0]=='ALL' ) {
		          	$("#xolcoversubdepartId"+val).multiselect('clearSelection');
		          	$("#xolcoversubdepartId"+val).val('ALL');
		          	 $("#xolcoversubdepartId"+val).multiselect("refresh");
		          	 document.getElementById("xolcoverpreVal"+val).value = 'ALL';
		          }
      	}                     
    }); 
	
 // var val='<s:property value="%{#stat.count-1}"/>'; 
 var uwgrade='<s:property value="%{xolcoversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#xolcoversubdepartId"+val).val(dataArray);
		 $("#xolcoversubdepartId"+val).multiselect("refresh");

});
</s:iterator>
</script>
</s:elseif>
<s:elseif test='"country".equalsIgnoreCase(dropDown)'>
 <s:if test='"RI02".equals(#session.SOURCE_CODE)'>
	<s:textfield name="leader_Underwriter_country" id="leader_Underwriter_country" cssClass="inputBox" cssStyle="text-align: right;"  />	
</s:if>	
<s:else>											
	<s:hidden name="leader_Underwriter_country" id="leader_Underwriter_country"/>
</s:else>
</s:elseif>
<s:elseif test='"slideView".equalsIgnoreCase(dropDown)'>




					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="boxcontent">
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.contractno" />
									</div>
									<div class="tbox">
										<s:property value="contNo"/>
									</div>
								</div>

								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.premiumClass" />
									</div>
									<div class="tbox">
										<s:property value="subProfit_center"/>
									</div>
								</div>
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.transDate" />
									</div>
									<div class="tbox">
										<s:property value="transaction"/>
									</div>
								</div>
								
								<div class="textfield">
									<div class="text txtB">
										Currency
									</div>
									<div class="tbox">
										 <s:property value="currencyName"/>
									</div>
								</div>
							</div></div>
						<br class="clear"/>
					</div>
					<s:if test='"Y".equals(gridShow)'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								Converted Results in Currency - <s:property value="currencyName"/>
							</div>
							<div class="panel-body">
								<div class="boxcontent">
									<div class="table2">
										<div class="tablerow">
											<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
											<span style="color:green;"><s:actionmessage/></span>
										</div>
										<div class="panel-body">

											<div class="row" >
												<div >

													<table class="table table-bordered" id="bonusTb" width="100%" >
														<thead>
														<tr>
															<th width="15%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
															<th width="15%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td>
																<s:text name="label.premiumdet" />
															</td>
															<td align="right">
																<s:property value="conPremiumOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimpaid" />
															</td>
															<td align="right">
																<s:property value="conClaimPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimuotstanding" />
															</td>
															<td align="right">
																<s:property value="conClaimOutStandingOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimratio" />
															</td>
															<td align="right">
																<s:property value="conClaimRatioOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.slideScale" />
															</td>
															<td align="right">
																<s:property value="conSlideScaleCommOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.commpaid" />
															</td>
															<td align="right">
																<s:property value="conCommPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.slidescaleadj" />
															</td>
															<td align="right">
																<s:property value="conSlideScaleAdjOC"/>
															</td>

														</tr>

													</table>

												</div>
											</div>
											<br class="clear"/>

										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<div style="overflow-x:auto; ">
<s:if test="premiumFinallist2.size()>0">
						<table id="testTable" summary="Code page support in different versions of MS Windows." rules="groups" frame="hsides" border="2" class="table table-bordered">
							<thead>
							<tr>
								<th  style="text-align: center; vertical-align: middle;"><b>Currency </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Transaction Number</b></th>
								<th style="text-align: center; vertical-align: middle;"><b>Transaction Date</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Contract Number</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Class</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Claims Outstanding - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Commission Paid till date (Provisional + Adjustment) - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Exchange Rate </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claims Outstanding - <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Commission Paid till date (Provisional + Adjustment) - <s:property value="currencyName"/></b></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="premiumFinallist2" var="list" status="sta">
								<tr>
									<td>
											${SHORT_NAME}
									</td>
									<td align="right">
											${TRANSACTION_NO}
									</td>
									<td>
											${TRANSACTION_MONTH_YEAR}
									</td>
									<td align="right">
											${CONTRACT_NO}
									</td>
									<td>
											${TMAS_DEPARTMENT_NAME}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.PremiumOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{PremiumOC})"/>
										</s:else>
									</td>
									<td align="right">
										${CLAIM_PREMIUM_VAL}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.CLIAMOSOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{CliamOSOC})"/>
										</s:else>
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.COMMISSION_PAID_OC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{COMMISSION_PAID_OC})"/>
										</s:else>
									</td>
									<td align="right">
											${EXCHG_RATE}
									</td>
									<td align="right">
										${PREMIUM_DC}
									</td>
									<td align="right">
										${CLAIM_DC}
									</td>
									<td align="right">
											${CLAIMOUT_DC}
									</td>
									<td align="right">
											${CLAIMPAID_DC}
									</td>

								</tr>
							</s:iterator>
							</tbody>
						</table>
						</s:if>
						 <s:else>
                              <div class="panel panel-primary" >
                                  <div class="panel-body">
                                      No Data Available
                                  </div>
                              </div>
                          </s:else>
					</div>
					<div class="btnContainer" align="right">
						<input type="button" onclick="tableToExcel('testTable', 'Slide Scale Commission Report')" value="Export to Excel" class="btn btn-sm btn-info">
					</div>


</s:elseif>
<s:elseif test='"retention".equalsIgnoreCase(dropDown)'>
<div id="retentionDis" >
<table class="table table-bordered" id="newgen" width="100%" >				
									<thead>
										<tr>
											<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Sno" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.Class" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Sub Class" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Business" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Retension" />  </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Type of Basis" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="1st Retention" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"><s:text name="2nd Retention" /></th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(FST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Treaty Limit(SST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(FST)" /> </th>
											<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="Event Limit(SST)" /> </th>
											<th width="9%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="retList" var="list" status="stat">
										<tr>
											<td>
						 					<s:textfield name="retSNo[%{#stat.count-1}]" id="retSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
						 					</td>
											<td>
											<s:select list="retdepartIdlist" listValue="TMAS_DEPARTMENT_NAME" listKey="TMAS_DEPARTMENT_ID" name="coverdepartId[%{#stat.count-1}]" id="coverdepartId[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  onchange="getAjaxCover(this.value,'coverdepartid%{#stat.count-1}',%{#stat.count-1})" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td id="coverdepartid<s:property value='%{#stat.count-1}'/>">
											<s:select list="%{coversubDepartList[#stat.count-1]}" name="coversubdepartId[%{#stat.count-1}]" cssClass="inputBoxS" id="coversubdepartId%{#stat.count-1}" headerKey="ALL" headerValue="ALL"  listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME"  multiple="true" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td >
											<s:select list="retBusinessTypeList"  name="retBusinessType[%{#stat.count-1}]" id="retBusinessType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>											
											<td >
											<s:select list="retTypeList"  name="retType[%{#stat.count-1}]" id="retType[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"  listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:select list="basicTypeList"  name="retBasis[%{#stat.count-1}]" id="retBasis[%{#stat.count-1}]" cssClass="inputBoxS" headerKey="" headerValue="---Select---"   listKey="TYPE" listValue="DETAIL_NAME" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="firstretention[%{#stat.count-1}]" id="firstretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="secondretention[%{#stat.count-1}]" id="secondretention[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retTreatyFST[%{#stat.count-1}]" id="retTreatyFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retTreatySST[%{#stat.count-1}]" id="retTreatySST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retEventFST[%{#stat.count-1}]" id="retEventFST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:textfield name="retEventSST[%{#stat.count-1}]" id="retEventSST[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple" disabled='%{(baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false}'/>
											</td>
											<td>
											<s:if test='0!=(#stat.count-1)'>
											<s:if test='baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)'>
											<input type="button" value="Delete" id="retDelete" class="btn btn-sm btn-danger" disabled="disabled" onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:if>
											<s:else>
											<input type="button" value="Delete" id="retDelete" class="btn btn-sm btn-danger"  onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
											</s:else>
											</s:if>
											</td>
												<s:hidden name="count" value="%{#stat.count-1}"></s:hidden>
										</tr>
										</s:iterator>
									</tbody>
								</table>
								<div class="boxcontent" align="right">
									<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow('newgen');" />
									<s:hidden name="loopcount" id="loopcount"></s:hidden>
	</div>
	<script type="text/javascript">
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
	
	<s:iterator value="retList"  status="stat">   
  	var val='<s:property value="%{#stat.count-1}"/>'; 
 	var uwgrade='<s:property value="%{coversubdepartId[#stat.count-1]}"/>';
		 var data=uwgrade.replace(/ /g,'');	
	   	 var dataArray=data.split(",");
	   	$("#coversubdepartId"+val).val(dataArray);
		 $("#coversubdepartId"+val).multiselect("refresh");
</s:iterator>
</script>							</div>
</s:elseif>
<s:elseif test='"lossnewgen".equalsIgnoreCase(dropDown)'>
<div  id="loss" >
<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
<table class="footable table-overflow" width="100%" id="newgen1" >
				<thead>
					<tr>
						<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.year" />  </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossno" />  </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.insurName" />  </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.incDate" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.ExpDate" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.dateOdloss" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.causeofloss" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.insuredclaim" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.premium" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.lossratio" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.leader" /> </th>
						<th class="tableColWidth" style="text-align: center; vertical-align: middle;"> <s:text name="label.itire" /> </th>
						<th width="8%" style="text-align: center; vertical-align: middle;"> <s:text name="Delete Row" /> </th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="lossRecordList" var="list" status="stat">
					<tr>
							<td>
								<s:textfield name="lossSNoS[%{#stat.count-1}]" id="lossSNoS[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
							</td>
							<td>
							<s:textfield name="lossYear[%{#stat.count-1}]" id="lossYear%{#stat.count-1}"  cssClass="inputBox"  maxlength="30"  theme="simple"/>
							</td>
							<td>
							<s:textfield name="lossNo[%{#stat.count-1}]" id="lossNo%{#stat.count-1}"  cssClass="inputBox"   maxlength="30" onchange="getInsuredName('%{#stat.count-1}')" theme="simple"/>
							</td>
							<td>
							<s:textfield name="lossinsuredName[%{#stat.count-1}]" id="lossinsuredName%{#stat.count-1}" cssClass="inputBox"   maxlength="100"  theme="simple" />
							</td>
							<td>
							<s:textfield name="lossInceptionDate[%{#stat.count-1}]" id="lossInceptionDate%{#stat.count-1}"  cssClass="inputBox datepicker accumDate"   onkeyup="validateSpecialChars(this)" onchange="functionDate('%{#stat.count-1}')" theme="simple"/>
								
							</td>
							<td>
							<s:textfield name="lossExpiryDate[%{#stat.count-1}]" id="lossExpiryDate%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)"  theme="simple"/>
							
							</td>
							<td>
							<s:textfield name="lossDateOfLoss[%{#stat.count-1}]" id="lossDateOfLoss%{#stat.count-1}" cssClass="inputBox datepicker accumDate" onkeyup="validateSpecialChars(this)" theme="simple"/>
							</td>
							<td >
							<s:textfield name="lossCauseOfLoss[%{#stat.count-1}]" id="lossCauseOfLoss%{#stat.count-1}" cssClass="inputBox" maxlength="100"   theme="simple"/>
							</td>
							<td >
							<s:textfield name="lossInsuredClaim[%{#stat.count-1}]" id="lossInsuredClaim%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   theme="simple"/>
							</td>
							<td >
							<s:textfield name="lossPremium[%{#stat.count-1}]" id="lossPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);getRatio('%{#stat.count-1}');" maxlength="30"   theme="simple"/>
							</td>
							<td >
							<s:textfield name="lossRatio[%{#stat.count-1}]" id="lossRatio%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  theme="simple" />
							</td>
							<td >
							<s:textfield name="lossLeader[%{#stat.count-1}]" id="lossLeader%{#stat.count-1}" cssClass="inputBox"  maxlength="30"   theme="simple"/>
							</td>
							<td >
							<s:textfield name="lossITIReShare[%{#stat.count-1}]" id="lossITIReShare%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);javascript:this.value=Comma(this.value);" maxlength="30"  theme="simple" />
							</td>
							<td align="center">
							<s:if test='0!=(#stat.count-1)'>
							<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
							</s:if>
							</td>
						</tr>
						
						</s:iterator>
						<s:hidden name="lossCount" id="lossCount" ></s:hidden>
					</tbody>
		</table>
			<div class="boxcontent" align="right">
				<input type="button"  value="AddMore"  class="btn btn-sm btn-primary" onclick="insRow1('newgen1');" />
				<input type="button"  value="Total"  class="btn btn-sm btn-primary" onclick="Calculate();" />
			</div>
			
			<br class="clear"/>
		</div>
		</div>
</s:elseif>

<s:elseif test='"yearId".equalsIgnoreCase(dropDown)'>
<s:if test='"1".equals(#session.mfrid)'>
	<s:select list="yearList" name="year" cssClass="inputBoxS"  listKey="YEAR" listValue="YEAR"   disabled='%{(contractNo != "" && contractNo != null) || "Y".equals(disableStatus1)?true:false}'/>
</s:if>
<s:elseif test='"2".equals(#session.mfrid)'>
	<s:select list="yearList" listKey="YEAR"
	listValue="YEAR" name="uwYear" id="uwYear" cssClass="inputBoxS" onblur="getRetention();"
	
	disabled='%{(contNo != "" && contNo != null)?true:false}' />
</s:elseif>
<s:elseif test='"3".equals(#session.mfrid)'>
	<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="inputBoxS" disabled='%{"Layer".equals(proposalReference)||(contNo != "" && contNo != null)?true:false}' />
</s:elseif>
<s:elseif test='"4".equals(#session.mfrid)'>
	<s:select list="yearList" listKey="YEAR" listValue="YEAR"
          name="uwYear" cssClass="inputBoxS" />
</s:elseif>
<s:elseif test='"5".equals(#session.mfrid)'>
	<s:select list="yearList" listKey="YEAR" listValue="YEAR" name="uwYear" cssClass="inputBoxS"   />
</s:elseif>
</s:elseif>
<s:elseif test='"yearIdto".equalsIgnoreCase(dropDown)'>
<s:select list="yearToList" listKey="YEAR" listValue="YEAR" name="uwYearTo" cssClass="select1 inputBoxS"  disabled='%{"Layer".equals(proposalReference) || dislayer ||(contNo != "" && contNo != null)?true:false}' />
</s:elseif>
<s:elseif test='"paypartid".equalsIgnoreCase(dropDown)'>
<s:select list="paymentPartnerlist" listKey="CUSTOMER_ID" listValue="NAME" name="paymentPartner" id="paymentPartner" cssClass="select1 inputBoxS"  />
</s:elseif>
<s:elseif test='"obsList".equalsIgnoreCase(dropDown)'>
<s:if test="OSBList.size()>0">
<table class="table table-bordered" width="100%"  >
<thead>
	<tr>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Transaction No" />  </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Transaction Date" />  </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Statement Date" /> </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Accounting Period" />  </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Accounting Period Date"  />  </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Sub Class" /> </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Section" /> </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="label.currency" /> </th>
		<th style="text-align: center; vertical-align: middle;"> <s:text name="Outstanding Loss Reserve – OC" /> </th>
		<th width="8%"> <s:text name="View" /> </th>
	</tr>
</thead>
<tbody>
<s:iterator value="OSBList" var="list" status="stat">
<tr>
		<td align="center">
		<s:property value="%{#list.TRANSACTION_NO}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.TRANSACTION_DATE}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.STATEMENT_DATE}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.ACCOUNT_PERIOD_QTR}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.ACCOUNTING_PERIOD_DATE}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.SUB_CLASS}"/>
		</td>
		<td align="center">
		<s:property value="%{#list.SECTION_NAME}"/>
		</td>
		
		<td align="center">
		<s:property value="%{#list.CURRENCY_ID}"/>
		</td>
		<td align="right">
		<s:property value="getText('number.format',{OSCLAIM_LOSSUPDATE_OC})"/>
		</td>
		<td align="center">
		<a title="View" style="cursor: pointer;" onClick="ViewMode('<s:property value="%{#list.CONTRACT_NO}"/>','<s:property value="%{#list.TRANSACTION_NO}"/>','<s:property value="%{#list.LAYER_NO}"/>','<s:property value="%{#list.DEPTID}"/>')">View</a>
		</td>
	</tr>
</s:iterator>
</tbody>
</table>
</s:if>
<s:else>
    <div class="panel panel-primary" >
        <div class="panel-body">
            No Data Available
        </div>
    </div>
</s:else>
</s:elseif>
<s:elseif test='"profitView".equalsIgnoreCase(dropDown)'>
		<div class="panel panel-primary">
						<div class="panel-body">
							<div class="boxcontent">
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.contractno" />
									</div>
									<div class="tbox">
										<s:property value="contNo"/>
									</div>
								</div>

								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.premiumClass" />
									</div>
									<div class="tbox">
										<s:property value="subProfit_center"/>
									</div>
								</div>
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.transDate" />
									</div>
									<div class="tbox">
										<s:property value="transaction"/>
									</div>
								</div>
								
								<div class="textfield">
									<div class="text txtB">
										Currency
									</div>
									<div class="tbox">
										 <s:property value="currencyName"/>
									</div>
								</div>
							</div></div>
						<br class="clear"/>
					</div>
					<s:if test='"Y".equals(gridShow)'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								Converted Results in Currency - <s:property value="currencyName"/>
							</div>
							<div class="panel-body">
								<div class="boxcontent">
									<div class="table2">
										<div class="tablerow">
											<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
											<span style="color:green;"><s:actionmessage/></span>
										</div>
										<div class="panel-body">

											<div class="row" >
												<div >

													<table class="table table-bordered" id="bonusTb" width="100%" >
														<thead>
														<tr>
															<th width="15%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
															<th width="15%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td>
																<s:text name="label.premiumincludedet" />
															</td>
															<td align="right">
																<s:property value="conPortfolioPremium"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.accCost" />
															</td>
															<td align="right">
																<s:property value="conAccCostBrokerage"/>
															</td>

														</tr>
																							<tr>
															<td>
																<s:text name="label.claimpaid" />
															</td>
															<td align="right">
																<s:property value="conProClaimPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimuotstanding" />
															</td>
															<td align="right">
																<s:property value="conProClaimOutStandingOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.manExp" />
															</td>
															<td align="right">
																<s:property value="conManagExp"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.proloss" />
															</td>
															<td align="right">
																<s:property value="conProfitLoss"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.otr.adj" />
															</td>
															<td align="right">
																<s:property value="conOtherAdj"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.treatyadj" />
															</td>
															<td align="right">
																<s:property value="conTreatyAdj"/>
															</td>

														</tr>
														<s:if test='"PR"==ProfitCommType'>
														<tr>
															<td>
																<s:text name="label.prorat" />
															</td>
															<td align="right">
																<s:property value="conProfitRatio"/>
															</td>

														</tr>
														</s:if>
														<s:if test='"LR"==ProfitCommType'>
														<tr>
															<td>
																<s:text name="label.lossratio" />
															</td>
															<td align="right">
																<s:property value="conLossRatio"/>
															</td>

														</tr>
														</s:if>
														<tr>
															<td>
																<s:text name="label.profitcomm" />
															</td>
															<td align="right">
																<s:property value="conProfitCommission"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.supprocomm" />
															</td>
															<td align="right">
																<s:property value="conSuperProfitComm"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.paydate" />
															</td>
															<td align="right">
																<s:property value="conPayedTillDate"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.profit.adj" />
															</td>
															<td align="right">
																<s:property value="conProfitCommAdj"/>
															</td>

														</tr>
													</table>

												</div>
											</div>
											<br class="clear"/>

										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<div style="overflow-x:auto; ">
<s:if test="premiumFinallist2.size()>0">
						<table id="testTable" summary="Code page support in different versions of MS Windows." rules="groups" frame="hsides" border="2" class="table table-bordered">
							<thead>
							<tr>
								<th  style="text-align: center; vertical-align: middle;"><b>Currency </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Transaction Number</b></th>
								<th style="text-align: center; vertical-align: middle;"><b>Transaction Date</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Contract Number</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Class</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium (Including Portfolio Premiums) -  OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Acquisition Costs (Excluding Brokerage) - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Claims Paid - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Claims Outstanding - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Profit Commission - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Exchange Rate </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium (Including Portfolio Premiums) -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Acquisition Costs (Excluding Brokerage)-  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claims Paid - <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claims Outstanding - <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Profit Commission - <s:property value="currencyName"/></b></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="premiumFinallist2" var="list" status="sta">
								<tr>
									<td>
											${SHORT_NAME}
									</td>
									<td align="right">
											${TRANSACTION_NO}
									</td>
									<td>
											${TRANSACTION_MONTH_YEAR}
									</td>
									<td align="right">
											${CONTRACT_NO}
									</td>
									<td>
											${TMAS_DEPARTMENT_NAME}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.PremiumOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{PremiumOC})"/>
										</s:else>
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.ACQUI_COST)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{ACQUI_COST})"/>
										</s:else>
									</td>
									<td align="right">
										${CLAIMPAID_OC}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.CliamOSOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{CliamOSOC})"/>
										</s:else>
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.PROFIT_COMMISSION_OC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{PROFIT_COMMISSION_OC})"/>
										</s:else>
									</td>
									
									<td align="right">
											${EXCHG_RATE}
									</td>
									<td align="right">
										${PREMIUM_DC}
									</td>
									<td align="right">
										${ACQ_COST_DC}
									</td>
									<td align="right">
										${CLAIMPAID_DC}
									</td>
									<td align="right">
											${CLAIMOUT_DC}
									</td>
									<td align="right">
											${PROFIT_COMMISSION}
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
						</s:if>
						 <s:else>
                              <div class="panel panel-primary" >
                                  <div class="panel-body">
                                      No Data Available
                                  </div>
                              </div>
                          </s:else>
					</div>
					<div class="btnContainer" align="right">
						<input type="button" onclick="tableToExcel('testTable', 'Profit Commission Report')" value="Export to Excel" class="btn btn-sm btn-info">
					</div>

</s:elseif>
<s:elseif test='"Calc".equalsIgnoreCase(dropDown)'>
<div class="panel panel-primary">
<div class="panel-heading">
   	Reinstatement Premium Calculation
</div>
 <div class="panel-body">
 <div class="boxcontent" >
<div class="boxcontent" >
<div class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;">
<s:if test="hasActionErrors()">
<div class="tablerow">
	<span style="color: red;"><s:actionerror />
	</span>
</div>
</s:if>
<s:else>
<s:if test="reinstatementList.size()>0">
	<table class="table table-bordered" id="Paid" width="100%">
	<thead>
	<tr>
		<th class="no-sort" width="1%" style="text-align: center; vertical-align: middle;"><s:text name="Sr No" /></th>
		<th  width="2%" style="text-align: center; vertical-align: middle;"><s:text name="Reinstatement Order" /></th>
		<th  width="2%" style="text-align: center; vertical-align: middle;"><s:text name="Payment from Cover No" /></th>
		<th  width="2%" style="text-align: center; vertical-align: middle;"><s:text name="Reinstatement No" /></th>
		<th style="text-align: center; vertical-align: middle;">Claim No</th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Payment No" /></th>
		<th style="text-align: center; vertical-align: middle;">Class</th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Payment Date" /></th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Currency" /></th>
		<th style="text-align: center; vertical-align: middle;" ><s:text name="Treaty Currency (TC) " /></th>
		<th style="text-align: center; vertical-align: middle;" ><s:text name="Claim Paid - OC"/></th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Paid - " /><s:property value="shortname"/></th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Exchange Rate to TC" /></th>
		<th style="text-align: center; vertical-align: middle;"><s:text name="Claim Paid – TC" /></th>
	</tr>
</thead>
<tbody>
	<s:iterator value="reinstatementList" var="list" status="stat">
	<tr>
		<td  ><s:property value="paidSno[#stat.count-1]" />   </td>
		<td style="text-align: center; vertical-align: middle;" > <s:property value="reinsNum[#stat.count-1]" /></td>
		<td style="text-align: center; vertical-align: middle;"><s:property value="paymentCoverNum[#stat.count-1]" /></td>
		<td style="text-align: center; vertical-align: middle;"><s:property value="paymentCoverPlus[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="claimNoList[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="claimPaymentNoList[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="departmentNameList[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="inceptionDateList[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="currencyNameList[#stat.count-1]" /></td>
		<td class="tableColWidth1"><s:property value="rdsCurrencyNameList[#stat.count-1]" /></td>
		<td class="tableColWidth"><s:textfield name="claimPaidOC[%{#stat.count-1}]" id="claimPaidOC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPaidOC[#stat.count-1]==null?'0.00':claimPaidOC[#stat.count-1]}"  theme="simple" /></td>
		<td class="tableColWidth"><s:textfield name="claimPaidDC[%{#stat.count-1}]" id="claimPaidDC%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" readonly="true" value="%{claimPaidDC[#stat.count-1]==null?'0.00':claimPaidDC[#stat.count-1]}"  theme="simple" /></td>
		<td class="tableColWidth"> <s:textfield name="claimEcxhangeRate[%{#stat.count-1}]" id="claimEcxhangeRate%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow8DigitDecValues(this);getClaimPaid('%{#stat.count-1}');"   maxlength="30"  value="%{claimEcxhangeRate[#stat.count-1]==null?'0.00':claimEcxhangeRate[#stat.count-1]}" readonly="true" theme="simple" /></td>
		<td class="tableColWidth"> <s:textfield name="claimPaidPremium[%{#stat.count-1}]" id="claimPaidPremium%{#stat.count-1}" cssClass="inputBox" cssStyle="text-align: right;" onblur="this.value=Comma(this.value);allow2DigitDecValues(this);"   maxlength="30" value="%{claimPaidPremium[#stat.count-1]==null?'0.00':claimPaidPremium[#stat.count-1]}"  readonly="true" theme="simple" /></td>
	</tr>
	</s:iterator>
</tbody>
</table>
</s:if>
 <s:else>
    <div class="panel panel-primary" >
        <div class="panel-body">
            No Data Available
        </div>
    </div>
</s:else>
<br  class="clear"/>
<div class="boxcontent" >
<div class="textfield">
	<div class="text">
		<s:text name="Reinstatement Premium in " /><s:property value="currencyName"/>
	</div>
	<div class="tbox" >
		<s:textfield name="reinsCalVal" id="reinsCalVal"  cssClass="inputBox" cssStyle="text-align: right;"/>
	</div>
</div>
</div>
</s:else>
<s:hidden name="tbleorder" id="tbleorder" value="Y"/>
</div>
</div>
</div>
</div>  
</div>
</s:elseif>
<s:elseif test='"bonusView".equalsIgnoreCase(dropDown)'>
		<div class="panel panel-primary">
						<div class="panel-body">
							<div class="boxcontent">
								<div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.contractno" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="contNo"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="label.layerNo" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="layerno"/>
                                                    </div>
                                                </div>
                                                 <div class="textfield">
                                                    <div class="text txtB">
                                                        Class
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="subProfit_center"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        As On Date
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="transaction"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        Currency
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="currencyName"/>
                                                    </div>
                                                </div>
                                                <div class="textfield">
                                                    <div class="text txtB">
                                                        <s:text name="facultative.bonuspercentage" />
                                                    </div>
                                                    <div class="tbox">
                                                        <s:property value="acqBonusName"/>
                                                    </div>
                                                </div>
							</div></div>
						<br class="clear"/>
					</div>
					<s:if test='"Y".equals(gridShow)'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								Converted Results in Currency - <s:property value="currencyName"/>
							</div>
							<div class="panel-body">
								<div class="boxcontent">
									<div class="table2">
										<div class="tablerow">
											<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
											<span style="color:green;"><s:actionmessage/></span>
										</div>
										<div class="panel-body">

											<div class="row" >
												<div >

													<table class="table table-bordered" id="bonusTb" width="100%" >
														<thead>
														<tr>
															<th width="15%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
															<th width="15%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td>
																<s:text name="label.premiumdet" />
															</td>
															<td align="right">
																<s:property value="conPremiumOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimpaid" />
															</td>
															<td align="right">
																<s:property value="conClaimPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimuotstanding" />
															</td>
															<td align="right">
																<s:property value="conClaimOutStandingOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimratio" />
															</td>
															<td align="right">
																<s:property value="conClaimRatioOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.Bonus" />
															</td>
															<td align="right">
																<s:property value="conbonusOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.bonuspaid" />
															</td>
															<td align="right">
																<s:property value="conbonusPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.bonusadj" />
															</td>
															<td align="right">
																<s:property value="conbonusAdjOC"/>
															</td>

														</tr>

													</table>

												</div>
											</div>
											<br class="clear"/>

										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<div style="overflow-x:auto; ">
<s:if test="premiumFinallist2.size()>0">
						<table id="testTable" summary="Code page support in different versions of MS Windows." rules="groups" frame="hsides" border="2" class="table table-bordered">
							<thead>
							<tr>
								<th  style="text-align: center; vertical-align: middle;"><b>Currency </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Transaction Number</b></th>
								<th style="text-align: center; vertical-align: middle;"><b>Transaction Date</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Contract Number</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Class</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Claims Outstanding - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Bonus Paid till date (Provisional + Adjustment) - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Exchange Rate </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claims Outstanding - <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Bonus Paid till date (Provisional + Adjustment) - <s:property value="currencyName"/></b></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="premiumFinallist2" var="list" status="sta">
								<tr>
									<td>
											${SHORT_NAME}
									</td>
									<td align="right">
											${TRANSACTION_NO}
									</td>
									<td>
											${TRANSACTION_MONTH_YEAR}
									</td>
									<td align="right">
											${CONTRACT_NO}
									</td>
									<td>
											${TMAS_DEPARTMENT_NAME}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.PremiumOC) || null==#list.PremiumOC'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{PremiumOC})"/>
										</s:else>
									</td>
									<td align="right">
										${CLAIM_PREMIUM_VAL}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.OSLR) || null==#list.OSLR'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{OSLR})"/>
										</s:else>
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.BONUS_OC) || null==#list.BONUS_OC'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{BONUS_OC})"/>
										</s:else>
									</td>
									<td align="right">
											${EXCHG_RATE}
									</td>
									<td align="right">
										${PREMIUM_DC}
									</td>
									<td align="right">
										${CLAIM_DC}
									</td>
									<td align="right">
											${CLAIMOUT_DC}
									</td>
									<td align="right">
											${BONUS_DC}
									</td>

								</tr>
							</s:iterator>
							</tbody>
						</table>
						</s:if>
						 <s:else>
                              <div class="panel panel-primary" >
                                  <div class="panel-body">
                                      No Data Available
                                  </div>
                              </div>
                          </s:else>
					</div>
					<div class="btnContainer" align="right">
						<input type="button" onclick="tableToExcel('testTable', 'Bonus Report')" value="Export to Excel" class="btn btn-sm btn-info">
					</div>

</s:elseif>
<s:elseif test='"lossView".equalsIgnoreCase(dropDown)'>
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="boxcontent">
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.contractno" />
									</div>
									<div class="tbox">
										<s:property value="contNo"/>
									</div>
								</div>

								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.premiumClass" />
									</div>
									<div class="tbox">
										<s:property value="subProfit_center"/>
									</div>
								</div>
								<div class="textfield">
									<div class="text txtB">
										<s:text name="label.transDate" />
									</div>
									<div class="tbox">
										<s:property value="transaction"/>
									</div>
								</div>
								
								<div class="textfield">
									<div class="text txtB">
										Currency
									</div>
									<div class="tbox">
										 <s:property value="currencyName"/>
									</div>
								</div>
							</div></div>
						<br class="clear"/>
					</div>
					<s:if test='"Y".equals(gridShow)'>
						<div class="panel panel-primary">
							<div class="panel-heading">
								Converted Results in Currency - <s:property value="currencyName"/>
							</div>
							<div class="panel-body">
								<div class="boxcontent">
									<div class="table2">
										<div class="tablerow">
											<span style="color:red;"><s:iterator value="errorList" var="list" status="stat"><li><s:property /> </li></s:iterator></span></ol>
											<span style="color:green;"><s:actionmessage/></span>
										</div>
										<div class="panel-body">

											<div class="row" >
												<div >

													<table class="table table-bordered" id="bonusTb" width="100%" >
														<thead>
														<tr>
															<th width="15%" style="text-align: center; vertical-align: middle;"> <b><s:text name="label.particulers" /></b></th>
															<th width="15%" style="text-align: center; vertical-align: middle;"><b><s:property value="currencyName"/></b></th>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td>
																<s:text name="label.premiumdet" />
															</td>
															<td align="right">
																<s:property value="conlossPremiumOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimpaid" />
															</td>
															<td align="right">
																<s:property value="conlossClaimPaidOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimuotstanding" />
															</td>
															<td align="right">
																<s:property value="conlossClaimOutStandingOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.claimratio" />
															</td>
															<td align="right">
																<s:property value="conlossClaimRatioOC"/>
															</td>

														</tr>
														<tr>
                                                              <td>
                                                                  <s:text name="label.claimpaidratio" />
                                                              </td>
                                                              <td align="right">
                                                                  <s:property value="conClaimPaidRatioOC"/>
                                                              </td>

                                                          </tr>
														<tr>
															<td>
																<s:text name="label.lossPor" />
															</td>
															<td align="right">
																<s:property value="conlossOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.lossPartRef" />
															</td>
															<td align="right">
																<s:property value="conlossTillOC"/>
															</td>

														</tr>
														<tr>
															<td>
																<s:text name="label.lossPartRefadj" />
															</td>
															<td align="right">
																<s:property value="conlossAdjOC"/>
															</td>

														</tr>

													</table>

												</div>
											</div>
											<br class="clear"/>

										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<div style="overflow-x:auto; ">
<s:if test="premiumFinallist2.size()>0">
						<table id="testTable" summary="Code page support in different versions of MS Windows." rules="groups" frame="hsides" border="2" class="table table-bordered">
							<thead>
							<tr>
								<th  style="text-align: center; vertical-align: middle;"><b>Currency </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Transaction Number</b></th>
								<th style="text-align: center; vertical-align: middle;"><b>Transaction Date</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Contract Number</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Class</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Claims Outstanding - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b> Commission Paid till date (Provisional + Adjustment) - OC</b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Exchange Rate </b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Premium -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claim Paid -  <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Claims Outstanding - <s:property value="currencyName"/></b></th>
								<th  style="text-align: center; vertical-align: middle;"><b>Commission Paid till date (Provisional + Adjustment) - <s:property value="currencyName"/></b></th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="premiumFinallist2" var="list" status="sta">
								<tr>
									<td>
											${SHORT_NAME}
									</td>
									<td align="right">
											${TRANSACTION_NO}
									</td>
									<td>
											${TRANSACTION_MONTH_YEAR}
									</td>
									<td align="right">
											${CONTRACT_NO}
									</td>
									<td>
											${TMAS_DEPARTMENT_NAME}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.PremiumOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{PremiumOC})"/>
										</s:else>
									</td>
									<td align="right">
										${CLAIM_PREMIUM_VAL}
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.CliamOSOC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{CliamOSOC})"/>
										</s:else>
									</td>
									<td align="right">
										<s:if test='"0".equals(#list.COMMISSION_PAID_OC)'>
											0.00
										</s:if>
										<s:else>
											<s:property value="getText('number.format',{COMMISSION_PAID_OC})"/>
										</s:else>
									</td>
									<td align="right">
											${EXCHG_RATE}
									</td>
									<td align="right">
										${PREMIUM_DC}
									</td>
									<td align="right">
										${CLAIM_DC}
									</td>
									<td align="right">
											${CLAIMOUT_DC}
									</td>
									<td align="right">
											${CLAIMPAID_DC}
									</td>

								</tr>
							</s:iterator>
							</tbody>
						</table>
						</s:if>
						 <s:else>
                              <div class="panel panel-primary" >
                                  <div class="panel-body">
                                      No Data Available
                                  </div>
                              </div>
                          </s:else>
					</div>
					<div class="btnContainer" align="right">
						<input type="button" onclick="tableToExcel('testTable', 'Loss Participation  Report')" value="Export to Excel" class="btn btn-sm btn-info">
					</div>


</s:elseif>
<s:elseif test='"typeBus".equalsIgnoreCase(dropDown)'>
	<s:if test="RenewalMode != null">	
		<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();" disabled='%{(baseLayer==null  || "".equalsIgnoreCase(baseLayer))?true:false}' />
	</s:if>
	<s:else>
		<s:select list="businessTypelist" listKey="TYPE" listValue="DETAIL_NAME" name="businessType" id="businessType" cssClass="inputBoxS" headerKey="" headerValue="---Select---" onchange="GetStopLossType(this.value);getPremiumBasis();getUmbrellaVal();getFieldDisable();getAjaxCoverClass();" disabled='%{"Y".equals(disableStatus1)?true:false}' />
	</s:else>
</s:elseif>
<s:elseif test='"rdsBasis".equalsIgnoreCase(dropDown)'>
	<s:select list="Basislist" listValue="DETAIL_NAME" listKey="TYPE" name="basis" cssClass="inputBoxS" headerKey="" headerValue="---Select---" disabled='%{(contNo != "" && contNo != null)?true:false}'/>													
</s:elseif>
<s:elseif test='"contractList".equalsIgnoreCase(dropDown)'>
	<s:select list="retroContractList" name="contNo" id="contNo"  listKey="RSK_CONTRACT_NO" listValue="RSK_CONTRACT_NO" headerKey="" cssClass="inputBoxS"  headerValue="---Select---" onchange="getRetroDet(this.value,'retroDetails')"/>													

</s:elseif>
<s:elseif test='"retroDetails".equalsIgnoreCase(dropDown)'>
<s:if test='proposal_No!=null&&proposal_No!=""'>
<div class="textfield">
		&nbsp;&nbsp;&nbsp;<input class="btn btn-xs btn-primary" type="button" value="View Contract Details" onclick="getViewContractDetails('4','<s:property value="proposal_No" />','<s:property value="amendId" />','<s:property value="branchCode" />')" />
		<s:hidden name="proposal_No" id="proposal_No"/>
		<s:hidden name="amendId" id="amendId"/>
</div>
</s:if>
<div class="textfield">
	<div class="text">
		<s:text name="label.leadretrocessionary" />
	</div>
	<div class="tbox">
		<s:textfield name="leadRetro" id="leadRetro"  cssClass="inputBox"    readonly="true"/>																						
	</div>
</div>
<div class="textfield">
	<div class="text">
		<s:text name="label.leadbroker" />
	</div>
	<div class="tbox">
		<s:textfield name="leadBroker" id="leadBroker"  cssClass="inputBox"   readonly="true"/>																						
	</div>
</div>
<div class="textfield">
	<div class="text">
		<s:text name="label.treatyName" />
	</div>
	<div class="tbox">
		<s:textfield name="treatyName" id="treatyName"  cssClass="inputBox"   readonly="true"/>																						
	</div>
</div>
<div class="textfield">
	<div class="text">
		<s:text name="label.currency" />
	</div>
	<div class="tbox">
		<s:select name="currency" id="currId" list="orginalCurrency" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" cssClass="inputBoxS" cssStyle="width:30%;float:left;" headerValue="---Select---" onchange="GetExchangeRate()" disabled="%{disableStatus}"/>											
		<span id="exchRate">
		<s:textfield cssClass="inputBox" name="exchRate" readonly="true"   cssStyle="width:60%;float:left;text-align:right;" />
		</span>
	</div>
</div>
</s:elseif>
<s:elseif test='"paymentView".equalsIgnoreCase(dropDown)'>
<div class="boxcontent">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<s:text name="Claim Payment View"></s:text>
										<span class="pullRight">
										Payment No &nbsp;: &nbsp;<s:property value="claimPaymentNo"/>
										</span>
		</div>
		<div class="panel-body">
			<div class="boxcontent">
			<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.paymentDate" />
					</div>
					<div class="tbox">
					<s:property value="date"/>
					</div>
				</div>
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.paymenttype" />
					</div>
					<div class="tbox">
					<s:property value="paymentType"/>
					</div>
				</div>
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.bkrCedingCoRefNo" />
					</div>
					<div class="tbox">
					<s:property value="payment_Request_No"/>
					</div>
				</div> 
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.paymentReference" />
					</div>
					<div class="tbox">
					<s:property value="payment_Reference"/>
					</div>
				</div>
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.claimPaidOurShareOC" />
					</div>
					<div class="tbox">
					<s:property value="paid_claim_os"/>
					</div>
				</div>
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.surveyorFeeOurShareOC" />
					</div>
					<div class="tbox">
					<s:property value="surveyor_fee_os"/>
					</div>
				</div>
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.otherProfessionalFeeOurShareOC" />
					</div>
					<div class="tbox">
					<s:property value="other_prof_fee_os"/>
					</div>
				</div>
				<s:if test='"3".equals(productId)'>
					<div class="textfield">
						<div class="text txtB">
							<s:text name="claim.reinsttype" />
						</div>
						<div class="tbox">
						<s:property value="reinstType"/>
						
						</div>
					</div>
					<div class="textfield">
						<div class="text txtB">
							<s:text name="claim.reinstpremium" />
						</div>
						<div class="tbox">
						<s:property value="reinstPremiumOCOS"/>
							<%--<s:if test='"Y".equals(reinstatementPremium)'>
							<s:hidden name="reinstatementPremium" id="reinstatementPremium"/>
							&nbsp;<input type="button"  class="btn btn-sm btn-info" size="2"  value="..." onclick="reinstatementDetails();" cssStyle="text-align:right;width: 45%; float:left;" />
							</s:if>--%>
						</div>
					</div>
				</s:if>
				
				
				<div class="textfield">
					<div class="text txtB">
						<s:text name="claim.totalClaimsPaidOurShareOC" />
					</div>
					<div class="tbox">
					<s:property value="paid_Amount_Orig_curr"/>
					</div>
				</div>
				
				<br class="clear" />
			</div>
			<div class="boxcontent">
				<div class="textfieldA25">
					<s:text name="claim.remarks" />
				</div>
				<div class="textfieldA75">
				<s:property value="remarks"/>
					
				</div>
				<br class="clear" />
			</div>
		</div>
	</div>
</div>

</s:elseif>

<s:elseif test='"docView".equalsIgnoreCase(dropDown)'>
<div class="boxcontent" id="docView" style="display:inline;">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<s:text name="upload.heading.documentsupload" />
		</div>
		
		<div class="panel-body">
			<div class="boxcontent">
				<table class="table table-bordered" id="DocTable">
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
					<s:iterator value="docuList" id="index" status="stat">
					<tr>
						<td class="formCon" style="text-align: center;">
							<s:textfield name="docId[%{index}]" id="docId" cssClass="inputBox" value="%{#index+1}" readonly="true" theme="simple"/> 
					 	</td>
					 	<s:if test='!"null".equals(docType)'>
						<td class="formCon"  valign="top">
							<s:select name="docTypeId[%{index}]" list="docType" listKey="DOC_TYPE" listValue="DOC_NAME" cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple"/>
						</td>
						</s:if>
						<s:else>
						<td class="formCon"  valign="top">
							<s:select name="docTypeId[%{index}]" list="#{}"  cssClass="inputBoxS" headerKey="" headerValue="---Select---" theme="simple"/>
						</td>
						</s:else>
						<td class="formCon" style="text-align: center;">
							<s:textarea name="docDesc[%{index}]"  rows="2" cols="70" cssClass="inputBox" theme="simple"/>												 
						</td>													
						<td class="formCon">
							<s:file name="upload"  cssClass="inputBox" id="upload" theme="simple"/>
						</td>
						<td class="formCon">
							<s:if test='0!=(#stat.count-1)'>
									<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteUpload('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
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
</div>
<s:hidden name="startIndex" id="startIndex"/>
<s:hidden name="endIndex" id="endIndex" />
</s:elseif>	

<s:elseif test='"trans".equalsIgnoreCase(dropDown)'>
<div class="tbox" id="trans" style="width:30%;float:left">
	<s:radio list="#{'Yes':'Yes','No':'No'}" name="chooseTransaction" id="chooseTransaction" value="%{(chooseTransaction==null || chooseTransaction=='')?'Yes':chooseTransaction}" onchange="getTransactionDropDown(this.value,'trans');"  disabled='mode.equals("edit")?true:false'/>													
</div>
<span style="width:65%;float:left" >
<s:if test='"Yes"==(chooseTransaction)'>
<s:select list="transList" listKey="TRANSACTION_NO" listValue="TRANSACTION_NO" headerKey="" headerValue="---Select---" name="transDropDownVal" cssClass="inputBoxS" onchange="getTransactionData();" />
</s:if>
<s:else>
<s:hidden name="transDropDownVal" id="transDropDownVal"/>
</s:else>
</span>

</s:elseif>		
<s:elseif test='"countStatus".equalsIgnoreCase(dropDown)'>
<s:if test='"Y"==popupShowStatus'>
<script type="text/javascript">
	showPopUp('<s:property value="type"/>');
</script>
</s:if>
<s:else>
<script type="text/javascript">
alert("This action is not allowed because a previous transaction is pending for authorisation for this contract.");
</script>
</s:else>
</s:elseif>	
<s:elseif test='"cashcountStatus".equalsIgnoreCase(dropDown)'>
<s:if test='"Y"==popupShowStatus'>
<script type="text/javascript">
	showPopUp('<s:property value="type"/>');
</script>
</s:if>
<s:else>
<script type="text/javascript">
alert("This action is not allowed because a previous transaction is pending for authorisation for this contract.");
</script>
</s:else>
</s:elseif>		
<s:elseif test='"premiumSubmit".equalsIgnoreCase(dropDown)'>
<script type="text/javascript">
	insertPremiumDet('<s:property value="popupShowStatus"/>');
</script>
</s:elseif>
<s:elseif test='"deleteInst".equalsIgnoreCase(dropDown)'>
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
					<s:textfield name="instalmentDateList[%{#stat.count-1}]" id="instalmentDateList[%{#stat.count-1}]"  cssClass="inputBox datepicker instalmentDate"   onkeyup="validateSpecialChars(this);" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}' theme="simple"/>
				</td>
				<td>
					<s:textfield name="installmentPremium[%{#stat.count-1}]" id="installmentPremium[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"  onkeyup="Itnegative(this.id,this.value);middleMinusRestrictionNeg(this);allow2DigitDecValues(this);this.value=Comma(this.value);allow2DigitDecValues(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}' theme="simple"/>
				</td> 
				<td>
					<s:textfield name="paymentDueDays[%{#stat.count-1}]" id="[%{#stat.count-1}]" cssClass="inputBox"   cssStyle="text-align: right;" onkeyup="checkNumbers(this);middleMinusRestrictionNeg(this);" maxlength="26" disabled='%{("".equals(transactionList[#stat.count-1]))?false:true}' theme="simple"/> 
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
</s:elseif>
<s:elseif test='"scale".equalsIgnoreCase(dropDown)'>
	<table class="table table-bordered" id="bonusTbl" width="100%" >										
		<thead>
			<tr>
				<th width="2%" style="text-align: center; vertical-align: middle;"> <s:text name="Serial No" />  </th>
				<th width="30%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio From"/> </th>
				<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Loss Ratio To "/> </th>
				<th width="16.5%" style="text-align: center; vertical-align: middle;"> <s:text name="Sliding Scale Commission %" /> </th>
			</tr>
		</thead>
			<tbody>
			<s:iterator value="scaleCommissionList" var="list" status="stat">
				<tr>
					<td>
 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
 					</td>
					<td >
					<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
					</td>
					<td >
					<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
					</td >
					<td >
					<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
					</td>
					
				</tr>
			</s:iterator>
			</tbody>
	</table>
</s:elseif>
<s:elseif test='"lossparticipates".equalsIgnoreCase(dropDown)'>
	<table class="table table-bordered" width="100%" id="bonusTbl">
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
				<s:iterator value="scaleCommissionList" var="list" status="stat">
					<tr>
						<td>
	 					<s:textfield name="scaleSNo[%{#stat.count-1}]" id="scaleSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/> 
	 					</td>
						<td >
						<s:textfield name="scaleFrom[%{#stat.count-1}]" id="scaleFrom[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
						</td>
						<td >
						<s:textfield name="scaleTo[%{#stat.count-1}]" id="scaleTo[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="30" theme="simple"/>
						</td>
						<td >
						<s:textfield name="scaleLowClaimBonus[%{#stat.count-1}]" id="scaleLowClaimBonus[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align:right;" onkeyup="allow2DigitDecValues(this);middleMinusRestriction(this);javascript:this.value=Comma(this.value)" maxlength="26" theme="simple"/>
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
</s:elseif>
<s:elseif test='"contact".equalsIgnoreCase(dropDown)'>
<table class="table table-bordered" width="100%" id="newgen">
	<thead>
		<tr>
		<th width="2.8%"> <s:text name="label.serialnumber" /></th>
		<th width="15.8%"> <s:text name="label.departmentName" /></th>
		<th width="15.8%"> <s:text name="label.subdepartmentName" /></th>
		<th width="15.8%"> <s:text name="label.emailaddress" /></th>
		<th width="15.8%"> <s:text name="label.telephonenumber" /></th>
		<th width="15.8%"> <s:text name="label.faxnumber" /> </th>
		<th width="5.8%"> <s:text name="Delete" /> </th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="contactList" var="list" status="stat">
			<tr>
				<td><s:textfield name="contactSNo[%{#stat.count-1}]" id="contactSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/></td>
				<td>
				<s:select  list="departList"  name="departmentCD[%{#stat.count-1}]" id="departmentCD%{#stat.count-1}" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" theme="simple"/>
				</td>
				<td>
				<s:textfield name="subdepartmentCD[%{#stat.count-1}]" id="subdepartmentCD[%{#stat.count-1}]" cssClass="inputBox"  theme="simple"/>
				</td>
				<td>
				<s:textfield name="emailaddress[%{#stat.count-1}]" id="emailaddress[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
				</td>
				<td>
				<s:textfield name="telephonenumber[%{#stat.count-1}]" id="telephonenumber[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
				</td>
				<td>
				<s:textfield name="faxnumber[%{#stat.count-1}]" id="faxnumber[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
				</td>
				<td align="center">
					<s:if test='0!=(#stat.count-1)'>
					<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
					</s:if>
				</td>
				</tr>
			</s:iterator>
	</tbody>
</table>
</s:elseif>
<s:elseif test='"bankid".equalsIgnoreCase(dropDown)'>
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
			<th width="5.8%"> <s:text name="Delete" /> </th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="currencyList" var="list" status="stat">
			<tr>
				<td><s:textfield name="bankSNo[%{#stat.count-1}]" id="bankSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/></td>
			
				<td>
					<s:select  list="bankCurrencyList"  name="bankCurrency[%{#stat.count-1}]" id="bankCurrency%{#stat.count-1}" cssClass="inputBox"  headerKey="" headerValue="---Select---" listKey="CURRENCY_ID"  listValue="CURRENCY_NAME" theme="simple"/>
				</td>
				<td>
					<s:textfield name="bankaccountnumber[%{#stat.count-1}]" id="bankaccountnumber[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td>
					<s:textfield name="bankname[%{#stat.count-1}]" id="bankname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td>
					<s:textfield name="accountname[%{#stat.count-1}]" id="accountname[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td>
					<s:textfield name="swiftcode[%{#stat.count-1}]" id="swiftcode[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td>
					<s:textarea name="corespondentbank[%{#stat.count-1}]" id="corespondentbank[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td>
					<s:textarea name="bankRemarks[%{#stat.count-1}]" id="remarks[%{#stat.count-1}]" cssClass="inputBox" onchange="checkCurrency(this.id,%{#stat.count-1})" theme="simple"/>
				</td>
				<td align="center">
						<s:if test='0!=(#stat.count-1)'>
						<input type="button" value="Delete" class="btn btn-sm btn-danger" onclick="disableForm(this.form,false,'');deleteRow1('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
						</s:if>
					</td>
				</tr>
			</s:iterator>
	</tbody>
</table>
</s:elseif>
<s:elseif test='"reinsurerid".equalsIgnoreCase(dropDown)'>
<table width="100%" class="table table-bordered" id="reinsTbl">
	<thead>
	<tr>
		<th width="10%"> <s:text name="label.sno" /> </th>
		<th width="20%"><s:text name="label.reinsureName" /></th>
		<th width="20%"><s:text name="label.placingBroker" /></th>
		<th width="20%"><s:text name="label.shareOffer" /></th>
		<th width="20%" > <s:text name="Mail Status" /> </th>
		<th width="10%" > <s:text name="Delete Row" /> </th>
	</tr>
	</thead>
	<tbody>	
	<s:iterator value="reinsurerInfoList" var="list"  status="stat">									
	<tr>
		<td>
			<s:textfield name="reinsSNo[%{#stat.count-1}]" id="reinsSNo[%{#stat.count-1}]" cssClass="inputBox" value="%{#stat.count}" readonly="true" theme="simple"/>
		</td>
		<td>
			<s:select list="reinsurerList" listKey="CUSTOMER_ID" listValue="NAME" name="reinsureName[%{#stat.count-1}]" id="reinsureName[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  theme="simple"/>
		</td>
		<td>
			<s:select list="brokerList" listKey="CUSTOMER_ID" listValue="NAME" name="placingBroker[%{#stat.count-1}]" id="placingBroker[%{#stat.count-1}]" cssClass="select1 inputBoxS" headerKey="" headerValue="---Select---"  theme="simple"/>
		</td>
		<td>
			<s:textfield name="shareOffer[%{#stat.count-1}]" id="shareOffer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;"    onkeyup="checkNumbers(this); middleMinusRestrictionNeg(this);" theme="simple"/>
		</td>
		<td>
			<s:property value="#list.MAIL_STATUS"/>
		</td>
		<td align="center">
			<s:if test='!"Success".equals(#list.MAIL_STATUS)'>
			<input type="button" value="Delete" class="btn btn-sm btn-danger"   onclick="deleteRow('<s:property value="%{#stat.count-1}"/>')" />
			</s:if>
		</td>
	</tr>												
	</s:iterator>
	</tbody>
</table>
</s:elseif>	
<s:elseif test='"statusChange".equalsIgnoreCase(dropDown)'>
<style type="text/css">
 .tableColWidth {
 	min-width: 200px;
 	max-width: 750px;
 	width: 200px;
 	white-space: normal;
 }
 
  .tableColWidth1 {
 	min-width: 80px;
 	max-width: 750px;
 	width: 450px;
 	white-space: normal;
 }
 
 .tableColNoWrap {
 	min-width: 100px;
 	max-width: 750px;
 	width: 100px;
 	white-space: nowrap;
 }
 .table-overflow{
    overflow: scrollX;
 }
 </style>
<div  class="col-xs-12 form-horizontal form-label-left" style="overflow-x: scroll;overflow-y: visible;">
<table width="100%" class="table table-bordered" >
	<thead>
	<tr>
		<th width="3%"> <s:text name="label.sno" /> </th>
		<th class="tableColWidth"><s:text name="label.proposalNo" /></th>
		<th class="tableColWidth"><s:text name="label.cedingCompany" /></th>
		<th class="tableColWidth"><s:text name="label.reinsureName" /></th>
		<th class="tableColWidth"><s:text name="label.placingBroker" /></th>
		<th class="tableColWidth"><s:text name="label.shareOffer" /></th>
		<s:if test='"A".equals(newStatus) || "RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.written" /></th>
		</s:if>
		<s:if test='"A".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.writtenvaliditydate" /></th>
		<th class="tableColWidth"><s:text name="label.writtenvalidityRemarks" /></th>
		</s:if>
		<s:if test='"RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.proposedWL" /></th>
		</s:if>
		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.signedLine" /></th>
		</s:if>
		<s:if test='"SL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.signedLineValidity" /></th>
		<th class="tableColWidth"><s:text name="label.signedLineRemarks" /></th>
		</s:if>
		<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.proposedSL" /></th>
		</s:if>
		<s:if test='"RO".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.reoffer" /></th>
		</s:if>
		<s:if test='"PWL".equals(newStatus)  || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.tqrBrokerageAmt" /></th>
		</s:if>
		<s:if test='"A".equals(newStatus)  || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.brokerage" /></th>
		</s:if>
		<s:if test='"P".equals(newStatus) || "D".equals(newStatus) || "NPWL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
		<th class="tableColWidth"><s:text name="label.emailStatus" /></th>
		<th class="tableColWidth"><s:text name="label.previewsendmail" /></th>
		</s:if>
	</tr>
	</thead>
	<tbody>	
	<s:iterator value="placementeditInfo" var="list"  status="stat">									
		<tr>
			<td>
				<s:property value="%{#stat.count}"/>
			</td>
			<td>
				<s:property value="#list.PROPOSAL_NO"/>
				<s:hidden name="proposalNos[%{#stat.count-1}]"/>
				<s:hidden name="reinsurerIds[%{#stat.count-1}]"/>
				<s:hidden name="brokerIds[%{#stat.count-1}]"/>
			</td>
			<td>
				<s:property value="#list.CEDING_COMPANY_ID"/>
			</td>
			<td>
				<s:property value="#list.REINSURER_NAME"/>
			</td>
			<td>
				<s:property value="#list.BROKER_NAME"/>
			</td>
			<td>
				<s:textfield name="shareOffered[%{#stat.count-1}]" id="shareOffered[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			<s:if test='"A".equals(newStatus) || "RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="writtenLine[%{#stat.count-1}]" id="writtenLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="writtenLine[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"A".equals(newStatus)'>
			<td>
				<s:textfield name="writtenvaliditydate[%{#stat.count-1}]" id="writtenvaliditydate[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
			</td>
			<td>
				<s:textfield name="writtenvalidityRemarks[%{#stat.count-1}]" id="writtenvalidityRemarks[%{#stat.count-1}]" cssClass="inputBox" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="writtenvaliditydate[%{#stat.count-1}]"/>
			<s:hidden name="writtenvalidityRemarks[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"RO".equals(newStatus) || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="proposedWL[%{#stat.count-1}]" id="proposedWL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="proposedWL[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="signedLine[%{#stat.count-1}]" id="signedLine[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="signedLine[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"SL".equals(newStatus)'>
			<td>
				<s:textfield name="signedLineValidity[%{#stat.count-1}]" id="signedLineValidity[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			<td>
				<s:textfield name="signedLineRemarks[%{#stat.count-1}]" id="signedLineRemarks[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="signedLineValidity[%{#stat.count-1}]"/>
			<s:hidden name="signedLineRemarks[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"RO".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="proposedSL[%{#stat.count-1}]" id="proposedSL[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="proposedSL[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"RO".equals(newStatus)'>
			<td>
				<s:textfield name="reoffer[%{#stat.count-1}]" id="reoffer[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="reoffer[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"PWL".equals(newStatus)  || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="tqrBrokerageAmt[%{#stat.count-1}]" id="tqrBrokerageAmt[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="tqrBrokerageAmt[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"A".equals(newStatus)  || "PWL".equals(newStatus) || "NPWL".equals(newStatus) || "SL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:textfield name="brokerage[%{#stat.count-1}]" id="brokerage[%{#stat.count-1}]" cssClass="inputBox" cssStyle="text-align: right;" theme="simple"/>
			</td>
			</s:if>
			<s:else>
			<s:hidden name="brokerage[%{#stat.count-1}]"/>
			</s:else>
			<s:if test='"P".equals(newStatus) || "D".equals(newStatus) || "NPWL".equals(newStatus) || "PSL".equals(newStatus) || "CSL".equals(newStatus)'>
			<td>
				<s:property value="#list.MAIL_STATUS"/>
			</td>
			<td>
				<input type="button" value="Update" class="btn btn-sm btn-info"   onclick="FnUpdate('<s:property value="%{#stat.count-1}"/>')" theme="simple"/>
			</td>
			</s:if>
		</tr>												
		</s:iterator>
	</tbody>
</table>
</div>
</s:elseif>							