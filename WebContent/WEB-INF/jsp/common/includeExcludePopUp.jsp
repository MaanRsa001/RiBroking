<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dataTables/js/jquery-1.11.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<style type="text/css">
    .multiselect-container {
        width: 100% !important;
    }
</style>
<script type="text/javascript">
	 $(function() {
	    $( "#effectiveDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	  </script>
<script language="javascript">
	NS = document.layers? 1:0;

	function moveAll(input,out) {
		for(k=0; k < out.options.length; k++) { 
			out.options[k] = new Option(out.options[k].text,out.options[k].value); 
		} 
		j = k;
		for(i=1; i < input.options.length; i++) { 
			out.options[k] = new Option(input.options[i].text,input.options[i].value);
			k++;
		} 
		out.length =k; 
		input.length = 1;
		if (NS) {  
		   history.go(0);  
		}
	}


	function add(input,out) { 
		//alert("SKRTEST"+"----------Input"+input+"------------Out"+out);

      	var selLen = out.options.length; 
        var availLen = 0;

        for(k=0; k < out.options.length; k++) { 
            out.options[k] = new Option(out.options[k].text,out.options[k].value); 
        } 
        j = k;
		input.options[availLen++] = new Option(input.options[0].text,input.options[0].value); 
			
        for(i=1; i < input.options.length; i++) { 
			if(input.options[i].selected) { 
				out.options[j++] = new Option(input.options[i].text,input.options[i].value); 
				selLen++;
			} 
			else { 
				input.options[availLen++] = new Option(input.options[i].text,input.options[i].value); 
			}
        }
        input.length = availLen; 
        out.length = selLen; 
        if (NS) {  
			history.go(0);  
        }
	}
</script>
</head>
<s:form name="iemodule" theme="simple">
<div class="row" style="display: table:width: 100%; margin: 0 auto;" >
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<span style="color:green"><s:actionmessage/></span>
		<s:actionerror cssStyle="color: red;" />
	</div>
</div>
	<div class="boxcontent">
		
		<s:if test='"list".equals(mode)'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				IE Module List
			</div>
			<div class="panel-body">
				<div class="boxcontent">
				<div >
					<table class="table table-bordered" width="100%" id="newgen">
						<thead>
							<tr>
								<th width="15.8%"> <s:text name="Proposal No" />  </th>
								<th width="15.8%"> <s:text name="Contract No" />  </th>
								<th width="15.8%"> <s:text name="Layer No" />  </th>
								<th width="15.8%"> <s:text name="Transaction No" />  </th>
								<th width="15.8%"> <s:text name="Effective Date" />  </th>
								<th width="15.8%"> <s:text name="Download" /> </th>
								<th width="15.8%"> <s:text name="Edit" /> </th>
								<th width="10.8%"> <s:text name="View" /> </th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="inclusionExlist" var="list" status="stat">
							<tr>
									<td align="center">
				 					<s:property value="#list.PROPOSAL_NO"/>
				 					</td>
				 					<td align="center">
				 					<s:property value="#list.CONTRACT_NO"/>
				 					</td>
				 					<td align="center">
				 					<s:property value="#list.LAYER_NO"/>
				 					</td>
									<td align="center">
									<s:property value="#list.TRANSACTION_NO"/>
									</td>
									<td align="center">
									<s:property value="#list.EEFECTIVE_DATE"/>
									</td>
									<td align="center">
									<input type="button" value="Download" class="btn btn-sm btn-warning" onclick="funDownload('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.CONTRACT_NO"/>','<s:property value="#list.LAYER_NO"/>','44')" />
									</td>
									<td align="center">
									<input type="button" value="Edit" class="btn btn-sm btn-primary" onclick="funEdit('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.TRANSACTION_NO"/>','<s:property value="#list.EEFECTIVE_DATE"/>','<s:property value="#list.CONTRACT_NO"/>','<s:property value="#list.LAYER_NO"/>')" />
									</td>
									<td align="center">
									<input type="button" value="View" class="btn btn-sm btn-info" onclick="funView('<s:property value="#list.PROPOSAL_NO"/>','<s:property value="#list.TRANSACTION_NO"/>','<s:property value="#list.EEFECTIVE_DATE"/>','<s:property value="#list.CONTRACT_NO"/>','<s:property value="#list.LAYER_NO"/>')" />
									</td>
								</tr>
								</s:iterator>
							</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="boxcontent" align="center">
				<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="Cancel()" />
				<br>
				</div>
			</div>
		</s:if>
		<s:elseif test='"new".equals(mode) || "edit".equals(mode)|| "view".equals(mode)'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				 Item Description				 
			</div>
			
			<div class="panel-body">
				<div class="textfield">
					<div class="text">
						<s:text name="Effective Date" />&nbsp; <sup style="color:red;">#</sup>
					</div>
					<div class="tbox">
						<div class="inputAppend">
							<s:textfield name="effectiveDate" id="effectiveDate"  cssStyle="width: 100%; border:transparent;" onkeyup="validateSpecialChars(this)"  disabled='"view".equals(mode)?true:false'/>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="row">
					<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-heading">
				 				Item Description
				 			</div>
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Business Type
				 			</div>
				 			</div>
				 		</div>
					</div>
					 <s:if test='productlist!=null && productlist.size()>0 '>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-heading">
					 				Inclusion 
					 			</div>
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="productIncluded" id="productIncluded" list="productlist" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"view".equals(mode)?true:false'></s:select>
					 			</div>
					 		</div>
					 	</div>
					 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-heading">
					 			&nbsp;
					 			</div>
					 			<div class="panel-body" style="height: 160px;">
						 				<s:if test="!'view'.equals(mode)">
						 				<input type="button" name="button3"  <s:property value='mode=="view"?"disabled=disabled":""' /> value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('productIncluded'),document.getElementById('productExcluded'));"     >
										<br class="clear"/>
										<br class="clear"/>
										<input type="button" name="button4" <s:property value='mode=="view"?"disabled=disabled":""' /> value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('productExcluded'),document.getElementById('productIncluded'));"     >
										<br class="clear"/>
										<br class="clear"/>
										<input type="button" name="button1" <s:property value='mode=="view"?"disabled=disabled":""' /> value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('productIncluded'),document.getElementById('productExcluded'));" >
										<br class="clear"/>
										<br class="clear"/>
										<input type="button" name="button2" <s:property value='mode=="view"?"disabled=disabled":""' /> value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('productExcluded'),document.getElementById('productIncluded'));" >
										</s:if>
						 			</div>
					 		</div>
					 	</div>
					 	<s:if test='productExlist!=null && productExlist.size()>0 '>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 		<div class="panel-heading">
					 				Exclusion 
					 			</div>
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="productExcluded" id="productExcluded" list="productExlist" listKey="TMAS_PRODUCT_ID" listValue="TMAS_PRODUCT_NAME"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
					 	</s:if>
					 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 		<div class="panel-heading">
					 				Exclusion 
					 			</div>
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="productExcluded" id="productExcluded" list="#{}"   headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
					 	
					 	</s:else>
				 	</s:if>
				</div>
				<div class="row">
						<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Inward Business Type
				 			</div>
				 			</div>
				 		</div>
						</div>
				 	<s:if test='inwardBusinessTypelist!=null && inwardBusinessTypelist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="inwardIncluded" id="inwardIncluded" list="inwardBusinessTypelist" listKey="TYPE" listValue="DETAIL_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				<s:if test="!'view'.equals(mode)">
					 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('inwardIncluded'),document.getElementById('inwardExcluded'));"    <s:property value='mode=="view"?"disabled=disabled":""' /> >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('inwardExcluded'),document.getElementById('inwardIncluded'));"    <s:property value='mode=="view"?"disabled=disabled":""' /> >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('inwardIncluded'),document.getElementById('inwardExcluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('inwardExcluded'),document.getElementById('inwardIncluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									</s:if>
					 			</div>
				 		</div>
				 	</div>
				 	<s:if test='inwardBusinessTypeExlist!=null && inwardBusinessTypeExlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="inwardExcluded" id="inwardExcluded" list="inwardBusinessTypeExlist" listKey="TYPE" listValue="DETAIL_NAME"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="inwardExcluded" id="inwardExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
				 	</s:else>
				 	</s:if>
				 </div>
				 <div class="row">
						<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Profit Center
				 			</div>
				 			</div>
				 		</div>
						</div>
				 	<s:if test='profit_Centerlist!=null && profit_Centerlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="profitIncluded" id="profitIncluded" list="profit_Centerlist" listKey="TMAS_PFC_ID" listValue="TMAS_PFC_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				<s:if test="!'view'.equals(mode)">
					 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('profitIncluded'),document.getElementById('profitExcluded'));"    <s:property value='mode=="view"?"disabled=disabled":""' /> >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('profitExcluded'),document.getElementById('profitIncluded'));"     <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('profitIncluded'),document.getElementById('profitExcluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('profitExcluded'),document.getElementById('profitIncluded'));" <s:property value='mode=="view"?"disabled=disabled":""' /> >
									</s:if>
					 			</div>
				 		</div>
				 	</div>
				 	<s:if test='profit_CenterExlist!=null && profit_CenterExlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="profitExcluded" id="profitExcluded" list="profit_CenterExlist" listKey="TMAS_PFC_ID" listValue="TMAS_PFC_NAME"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="profitExcluded" id="profitExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
				 	
				 	</s:else>
				 	</s:if>
				 </div>
				 <div class="row">
						<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Class of Business
				 			</div>
				 			</div>
				 		</div>
						</div>
				 	<s:if test='departIdlist!=null && departIdlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="deprtIncluded" id="deprtIncluded" list="departIdlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				<s:if test="!'view'.equals(mode)">
					 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('deprtIncluded'),document.getElementById('deprtExcluded'));"    <s:property value='mode=="view"?"disabled=disabled":""' /> >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('deprtExcluded'),document.getElementById('deprtIncluded'));"     <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('deprtIncluded'),document.getElementById('deprtExcluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('deprtExcluded'),document.getElementById('deprtIncluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									</s:if>
					 			</div>
				 		</div>
				 	</div>
				 	<s:if test='departIdExlist!=null && departIdExlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="deprtExcluded" id="deprtExcluded" list="departIdExlist" listKey="TMAS_DEPARTMENT_ID" listValue="TMAS_DEPARTMENT_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="deprtExcluded" id="deprtExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
				 	</s:else>
				 	</s:if>
				</div>
				<%-- <div class="row">
						<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Sub Class of Business
				 			</div>
				 			</div>
				 		</div>
						</div>
				 	<s:if test='subClasslist!=null && subClasslist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="subClassIncluded" id="subClassIncluded" list="subClasslist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				<s:if test="!'view'.equals(mode)">
					 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('subClassIncluded'),document.getElementById('subClassExcluded'));"     <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('subClassExcluded'),document.getElementById('subClassIncluded'));"     <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('subClassIncluded'),document.getElementById('subClassExcluded'));"  <s:property value='mode=="view"?"disabled=disabled":""' />  >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('subClassExcluded'),document.getElementById('subClassIncluded'));"  <s:property value='mode=="view"?"disabled=disabled":""' />>
									</s:if>
					 			</div>
				 		</div>
				 	</div>
				 	<s:if test='subClassExlist!=null && subClassExlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="subClassExcluded" id="subClassExcluded" list="subClassExlist" listKey="TMAS_SPFC_ID" listValue="TMAS_SPFC_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="subClassExcluded" id="subClassExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
				 	</s:else>
				 	</s:if>
				</div> --%>
				<div class="row">
					<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Type of Treaty
				 			</div>
				 			</div>
				 		</div>
					</div>
				 	<s:if test='treatyTypelist!=null && treatyTypelist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="treatyIncluded" id="treatyIncluded" list="treatyTypelist" listKey="TYPE" listValue="DETAIL_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				<s:if test="!'view'.equals(mode)">
					 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('treatyIncluded'),document.getElementById('treatyExcluded'));"  <s:property value='mode=="view"?"disabled=disabled":""' />    >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('treatyExcluded'),document.getElementById('treatyIncluded'));"   <s:property value='mode=="view"?"disabled=disabled":""' />  >
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('treatyIncluded'),document.getElementById('treatyExcluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									<br class="clear"/>
									<br class="clear"/>
									<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('treatyExcluded'),document.getElementById('treatyIncluded'));" <s:property value='mode=="view"?"disabled=disabled":""' />>
									</s:if>
					 			</div>
				 		</div>
				 	</div>
				 	<s:if test='treatyTypeExlist!=null && treatyTypeExlist.size()>0 '>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:select name="treatyExcluded" id="treatyExcluded" list="treatyTypeExlist"  listKey="TYPE" listValue="DETAIL_NAME"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
				 			</div>
				 		</div>
				 	</div>
				 	</s:if>
				 	<s:else>
					 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
					 		<div class="panel panel-primary">
					 			<div class="panel-body" style="height: 160px;">
					 				<s:select name="treatyExcluded" id="treatyExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled="'view'.equals(mode)?true:false"></s:select>
					 			</div>
					 		</div>
					 	</div>
				 	</s:else>
				 	</s:if>
				</div>
				<div class="row">
					<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
							<div >
				 				Inward Proposal No
				 			</div>
				 			</div>
				 		</div>
					</div>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 			</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
					 				
					 				
					 		</div>
				 		</div>
				 	</div>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<div class="panel-body" style="height: 160px;">
				 				<s:textarea name="excludeProposalNo" id="excludeProposalNo" cssClass="inputBox" rows="10" cssStyle="width: 100%; height:100%; resize: none; "   disabled="'view'.equals(mode)?true:false"/>
				 			</div>
				 		</div>
				 	</div>
				 	
				</div>
				<!--<div class="row">
					<div class="col-xs-5" style="padding-right: 0px;">
				 		<div class="panel panel-primary">
							<div >
				 				Proposal No
				 			</div>
				 		</div>
					</div>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			
				 		</div>
				 	</div>
				 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			
				 		</div>
				 	</div>
				 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
				 		<div class="panel panel-primary">
				 			<s:textfield  name="proposalExcluded"  id="proposalExcluded"></s:textfield>
				 		</div>
				 	</div>
				 	
				</div>
			--></div>
			<div class="boxcontent" align="center">
				<s:if test="!'new'.equals(mode)">
				<input type="button"  value="Back"  class="btn btn-sm btn-danger"  onclick="Back()" />
				</s:if>
				<s:if test="!'view'.equals(mode)">
				&nbsp;&nbsp;&nbsp;<input type="button"  value="Save"   class="btn btn-sm btn-success" onclick="disableForm(this.form,false,'');FunctionSaveOption()" />
				</s:if>
		</div>
		<br>
		</div>
		  			
	<s:hidden name="includedList" id="includedList" />
<s:hidden name="excludedList" id="excludedList" />
<s:hidden name="proposalNo" id="proposalNo" />
<s:hidden name="contarctno" id="contarctno" />
<s:hidden name="layerNo" id="layerNo" />
<s:hidden name="transactionNo" id="transactionNo" />
	</s:elseif>
</div>
</s:form>
<script>
function FunctionSaveOption()
{
var checkedItems='';
		var checkedItems1='';
		try{
			var elements=document.getElementById("productIncluded");
			var elements1=document.getElementById("productExcluded");
			var elements2=document.getElementById("inwardIncluded");
			var elements3=document.getElementById("inwardExcluded");
			var elements4=document.getElementById("profitIncluded");
			var elements5=document.getElementById("profitExcluded");
			var elements6=document.getElementById("deprtIncluded");
			var elements7=document.getElementById("deprtExcluded");
			//var elements8=document.getElementById("subClassIncluded");
			//var elements9=document.getElementById("subClassExcluded");
			var elements10=document.getElementById("treatyIncluded");
			var elements11=document.getElementById("treatyExcluded");
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements1.length; i++){
				obj=elements1[i];
				checkedItems1+=obj.value+',';
			}
			for(i=1; i<elements2.length; i++){
				obj=elements2[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements3.length; i++){
				obj=elements3[i];
				checkedItems1+=obj.value+',';
			}
			for(i=1; i<elements4.length; i++){
				obj=elements4[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements5.length; i++){
				obj=elements5[i];
				checkedItems1+=obj.value+',';
			}
			for(i=1; i<elements6.length; i++){
				obj=elements6[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements7.length; i++){
				obj=elements7[i];
				checkedItems1+=obj.value+',';
			}
			//for(i=1; i<elements8.length; i++){
				//obj=elements8[i];
				//checkedItems+=obj.value+',';
			//}
			//for(i=1; i<elements9.length; i++){
				//obj=elements9[i];
				//checkedItems1+=obj.value+',';
			//}
			for(i=1; i<elements10.length; i++){
				obj=elements10[i];
				checkedItems+=obj.value+',';
			}
			for(i=1; i<elements11.length; i++){
				obj=elements11[i];
				checkedItems1+=obj.value+',';
			}
		}catch(e){}
		if(checkedItems.length>1 || checkedItems1.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			checkedItems1=checkedItems1.substring(0, checkedItems1.length-1);
		}
		document.getElementById("includedList").value=checkedItems;
		document.getElementById("excludedList").value=checkedItems1;
	document.iemodule.action="${pageContext.request.contextPath}/insertieModuleXol.action";
	document.iemodule.submit();
}
function funEdit(val,val1,val2,val3,val4){
document.iemodule.action="${pageContext.request.contextPath}/editieModuleXol.action?proposalNo="+val+"&transactionNo="+val1+"&effectiveDate="+val2+"&contarctno="+val3+"&layerNo="+val4+"&mode=edit";
document.iemodule.submit();
}
function funDownload(proposalNo,val,val1,val2){
var startDate=prompt("Transaction Start Date : ");
var endDate=prompt("Transaction End Date : ") ;
document.iemodule.action="${pageContext.request.contextPath}/getRlistReports?contractNo="+val+"&layerNo="+val1+"&typeId="+val2+"&start_date="+startDate+"&end_date="+endDate+"&proposalNo="+proposalNo+"&reportType=Excel&productId=6";
document.iemodule.submit();
}

function funView(val,val1,val2,val3,val4){
document.iemodule.action="${pageContext.request.contextPath}/editieModuleXol.action?proposalNo="+val+"&transactionNo="+val1+"&effectiveDate="+val2+"&contarctno="+val3+"&layerNo="+val4+"&mode=view";
document.iemodule.submit();
}
function Cancel()
{
	document.iemodule.action ="${pageContext.request.contextPath}/InitPortfolio.do";
	document.iemodule.submit();
}
function Back()
{
	document.iemodule.action="${pageContext.request.contextPath}/ieModuleXol.action";
	document.iemodule.submit();
}

	</script>
</html>