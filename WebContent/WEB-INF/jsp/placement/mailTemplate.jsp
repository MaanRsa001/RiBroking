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
    	 <label for="cc" class="col-2 col-sm-1 col-form-label">Attachment:</label>
          <div class="col-5 col-sm-5 mb-2" >
          <s:file name="upload"  cssClass="inputBox" id="upload"/>
          </div>
          <div class="col-5 col-sm-5 mb-2" >
         <button type="submit" class="btn btn-sm btn-warning" onclick="uploadFile();">Upload File</button>
          </div>
      </div>
      <br/>
      <div class="row">
      <div class="boxcontent col-md-4 col-lg-4 col-xl-4" style="width:40%"  align="left">
	      
	      <ul class="list-group">
			      <s:iterator value="existingAttachList" var="list"  status="stat">
					  <li class="list-group-item">
					  		<div class="row">
					  				<div class="col-md-8">
					  					<s:property value="#list.ORG_FILE_NAME"/>
					  				</div>
					  				<div class="col-md-2">
					  					<button type="submit" class="btn btn-sm btn-primary" onclick="DownloadFile('<s:property value="#list.DOC_ID"/>','<s:property value="#list.ORG_FILE_NAME"/>')"><span class="glyphicon glyphicon-download"></span></button>
					  				</div>
					  				<div class="col-md-2">
					  					<button type="submit" class="btn btn-sm btn-danger" onclick="DeleteFile('<s:property value="#list.DOC_ID"/>','<s:property value="#list.ORG_FILE_NAME"/>')"><span class="glyphicon glyphicon-remove"></span></button>
					  				</div>
					  		</div>
					  
					  		
					  			
					  </li>
				</s:iterator>
			</ul>
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



