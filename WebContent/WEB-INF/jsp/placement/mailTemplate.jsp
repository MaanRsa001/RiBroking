<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
</head>
<body>
			<div class="row">
                <label for="bcc" class="col-2 col-sm-1 col-form-label">Subject:</label>
                <div class="col-10 col-sm-10 mb-2" style="padding-bottom: 10px;">
                <s:textfield name="mailSubject" id="mailSubject" cssClass="inputBox"/>
                </div>
            </div>
            <div class="row">
                <label for="to" class="col-2 col-sm-1 col-form-label">To:</label>
                <div class="col-10 col-sm-10 mb-2" style="padding-bottom: 10px;">
                <s:select list="mailCCList" listKey="CODE" listValue="CODEDESC" name="mailTo" id="mailTo"  multiple="true" cssClass="inputBoxS"   />
                 <%-- <s:textfield name="mailTo" id="mailTo" cssClass="inputBox"/> --%>
                </div>
            </div>
            
            <div class="row">
                <label for="cc" class="col-2 col-sm-1 col-form-label">CC:</label>
                <div class="col-10 col-sm-10 mb-2" style="padding-bottom: 10px;">
                	<s:textfield name="mailCC" id="mailCC" cssClass="inputBox"/>
                </div>
            </div>
    <div id="editor" >
        <s:text name="mailBody" />
    </div>
    <div class="row">
    	 <label for="cc" class="col-2 col-sm-1 col-form-label">Remarks:</label>
          <div class="col-10 col-sm-10 mb-2" style="padding-bottom: 10px;">
          	<s:textarea name="mailRemarks" id="mailRemarks" rows="3" cssClass="inputBoxA" cssStyle="width: 100%;"/>
          </div>
      </div>
      <div class="row">
      <div class="boxcontent col-md-4 col-lg-4 col-xl-4" style="width:40%"  align="left">
      	<div class="boxcontent">
			<table class="table table-bordered" id="lcDoctable">
				<thead>
				<tr>
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
					<td class="formCon">
						<s:hidden name="docTypeId[%{index}]" value="MA"></s:hidden>
						<s:hidden name="docDesc[%{index}]" value="Mail Attachement"></s:hidden>
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
		<div class="boxcontent" align="center">
			<input type="button"  value="Add More" class="btn btn-sm btn-info" onClick="addMorelc();" />
		</div>
	</div>
	</div>
     <div class="row" style="padding-top:15px">
             <div class="text-center">
             		<button type="submit" class="btn btn-sm btn-danger" onclick="cancelEmail();">Discard</button>
                    <button type="submit" class="btn btn-sm btn-success" onclick="sendEmail();">Send</button>
                    
               </div>
       </div>
 <script>
      var editor =CKEDITOR.replace( 'editor' );
      CKEDITOR.config.height = '400px';
      CKEDITOR.config.readOnly = true;
      
    </script>
</body>
</html>



