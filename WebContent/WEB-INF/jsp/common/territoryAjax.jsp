<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE html>
<html>
<body>
     <s:if test='"country".equalsIgnoreCase(dropDown)'>
       <s:if test='territoryCountryList!=null && territoryCountryList.size()>0 '>
		 	<div class="col-xs-3" style="padding-left: 1px;padding-right: 1px;">
		 		<div class="panel panel-primary">
		 			<div class="panel-heading">
		 				Countries Included <s:if test='"2".equals(#session.mfrid) '> &nbsp; <sup style="color:red;">#</sup></s:if>
		 			</div>
		 			<div class="panel-body" style="height: 160px;">
		 				<s:select name="countryIncluded" id="countryIncluded" list="territoryCountryList" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerValue="--- Please Select ---" headerKey="-1" label="Country" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='("2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) )?true:false'></s:select>
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
		 				<s:select name="countryExcluded" id="countryExcluded" list="excludedCountryList" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"  headerValue="--- Please Select ---" headerKey="-1" label="Country" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) ?true:false'></s:select>
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
		 				<s:select name="countryExcluded" id="countryExcluded" list="excludedCountryList"  headerValue="--- Please Select ---" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerKey="-1" label="Territory" multiple="true" required="true" size= "5"  theme="simple" cssStyle="width: 100%;" disabled='"2".equals(#session.mfrid) && baseLayer!=null  && !"".equalsIgnoreCase(baseLayer) ?true:false'></s:select>
		 			</div>
		 		</div>
		 	</div>
	 	</s:else>
		<s:hidden name="previousSelectTerritory" id="previousSelectTerritory"/>
     </s:if>
     </body>
     </html>