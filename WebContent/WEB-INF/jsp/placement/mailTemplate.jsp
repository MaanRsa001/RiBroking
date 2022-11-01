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
                 <s:textfield name="mailTo" id="mailTo" cssClass="inputBox"/>
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
     <div class="row" style="padding-top:15px">
             <div class="text-center">
             		<button type="submit" class="btn btn-danger" onclick="cancelEmail();">Discard</button>
                    <button type="submit" class="btn btn-success" onclick="sendEmail();">Send</button>
                    
               </div>
       </div>
 <script>
      var editor =CKEDITOR.replace( 'editor' );
      CKEDITOR.config.height = '400px';
      
    </script>
</body>
</html>



