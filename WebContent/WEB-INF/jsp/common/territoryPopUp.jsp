<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css" />	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script type="text/javascript">
    $(document).ready(function() {
        $('#territory').multiselect({
            buttonWidth: '300px'
        });
    });
</script>
<style type="text/css">
    .multiselect-container {
        width: 100% !important;
    }
</style>
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
	<div class="boxcontent">
		<div class="panel panel-primary">
			<div class="panel-heading">
				 &nbsp;&nbsp;&nbsp;
			</div>
			<div class="panel-body">
				<div class="table0" style="width: 100%; margin: 0 auto;">
				    <div class="tablerow">
				        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
				            <div class="tablerow">
				                <div style="padding:10px; background:#F8F8F8"><div class="table2">
				                    <div class="panel-body">
				                    	<div class="row" >
				 							<div class="row">
				 								<div class="col-xs-5" style="padding-right: 0px;">
											 		<div class="panel panel-primary">
											 			<div class="panel-heading">
											 				Territory / Region <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
											 			</div>
											 			<div class="panel-body" style="height: 160px;">
														<div >
											 				<s:select name="territory" id="territory" list="territoryRegionList" listKey="TERRITORY_ID" listValue="TERRITORY_NAME" label="Territory" multiple="true" required="true" size= "5" theme="simple" cssStyle="width: 150%;" onchange="getCountryValue('country','','','')" disabled='("2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer)) ?true:false'></s:select>
											 			</div>
											 			<!--<div  align="center">
											 				<input type="button" onclick="getCountryValue('country','')" value="Populate data" class="btn btn-sm btn-info"/>
											 			</div>
											 			--></div>
											 		</div>
				 								</div>
											 	<div id="country">
											 	<s:if test='territoryCountryList!=null && territoryCountryList.size()>0 '>
											 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
											 		<div class="panel panel-primary">
											 			<div class="panel-heading">
											 				Countries Included  <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
											 			</div>
											 			<div class="panel-body" style="height: 160px;">
											 				<s:select name="countryIncluded" id="countryIncluded" list="territoryCountryList" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='("2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer))?true:false'></s:select>
											 			</div>
											 		</div>
											 	</div>
											 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
											 		<div class="panel panel-primary">
											 			<div class="panel-heading">
											 			&nbsp;
											 			</div>
											 			<div class="panel-body" style="height: 160px;">
												 				
												 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('countryIncluded'),document.getElementById('countryExcluded'));"     >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('countryExcluded'),document.getElementById('countryIncluded'));"     >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('countryIncluded'),document.getElementById('countryExcluded'));" >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('countryExcluded'),document.getElementById('countryIncluded'));" >
												 			</div>
											 		</div>
											 	</div>
											 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
											 		<div class="panel panel-primary">
											 			<div class="panel-heading">
											 				Countries  Excluded <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
											 			</div>
											 			<div class="panel-body" style="height: 160px;">
											 				<s:select name="countryExcluded" id="countryExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) ?true:false'></s:select>
											 			</div>
											 		</div>
											 	</div>
											 	</s:if>
											 	<s:else>
												 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
												 		<div class="panel panel-primary">
												 			<div class="panel-heading">
												 				Countries Included <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
												 			</div>
												 			<div class="panel-body" style="height: 160px;">
												 				<s:select name="countryIncluded" id="countryIncluded" list="#{}" headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) ?true:false'></s:select>
												 			</div>
												 		</div>
												 	</div>
												 	<div class="col-xs-1" style="padding-left: 1px;padding-right: 1px;">
												 		<div class="panel panel-primary">
												 			<div class="panel-heading">
												 			&nbsp;
												 			</div>
												 			<div class="panel-body" style="height: 160px;">
												 				
												 				<input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('countryIncluded'),document.getElementById('countryExcluded'));"     >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.getElementById('countryExcluded'),document.getElementById('countryIncluded'));"     >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('countryIncluded'),document.getElementById('countryExcluded'));" >
																<br class="clear"/>
																<br class="clear"/>
																<input type="button" name="button2" value=" <<  "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.getElementById('countryExcluded'),document.getElementById('countryIncluded'));" >
												 			</div>
												 		</div>
												 	</div>
												 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
												 		<div class="panel panel-primary">
												 			<div class="panel-heading">
												 				Countries Excluded <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
												 			</div>
												 			<div class="panel-body" style="height: 160px;">
												 				<s:select name="countryExcluded" id="countryExcluded" list="#{}"  headerValue="--- Please Select ---" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) ?true:false'></s:select>
												 			</div>
												 		</div>
												 	</div>
											 	
											 	</s:else>
											 	<s:hidden name="previousSelectTerritory" id="previousSelectTerritory"/>
											 	
											 	
											 	
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

<script>
$(document).ready(function() {     
    $('#territory').multiselect({ 
      	includeSelectAllOption: true,
        enableFiltering:true,
         numberDisplayed: 0 
    });            
});
<s:if test='territory!=null && !"".equals(territory)'>
		var uwgrade='<s:property value="territory"/>';
	 var data=uwgrade.replace(/ /g,'');	
   	 var dataArray=data.split(",");   	 
   	$("#territory").val(dataArray);
</s:if>

<s:if test='territory!=null && !"".equals(territory)'>
getCountryValue('country','edit','<s:property value="countryExcludedList"/>','<s:property value="countryIncludedList"/>');
</s:if>	
function getCountryValue(div,mode,val,val1){
var elements=[];
	var selected = document.getElementById("previousSelectTerritory").value;
	var disableStatus1 = '<s:property value="disableStatus1"/>';
	var baseLayer ='<s:property value="baseLayer"/>'; 
	if(selected!="" && mode==""){
		answer = confirm("You are about to re-select the Territory / Region. This action will reset the countries included and countries excluded list. Do you wish to continue?");
		if(!answer){
			$('#territory').val(selected.split(','));
			$("#territory").multiselect("refresh");
		}
		}
		elements = $('#territory').val();
	var URL="${pageContext.request.contextPath}/territoryAjaxHome.action?dropDown=country&territory="+elements+"&previousSelectTerritory="+elements+"&countryExcludedList="+val+"&countryIncludedList="+val1+"&countryMode="+mode+"&disableStatus1="+disableStatus1+"&baseLayer="+baseLayer;
    postFormLoader(URL,div)
}
	</script>
</html>