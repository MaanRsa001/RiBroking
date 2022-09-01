<%-- 
    Author     : Raja.K
    Document   : Common Login Template
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title><s:text name="application.name" /></title>
        <link rel='shortcut icon' type='image/x-icon' href='${pageContext.request.contextPath}/images/title-logo.png' />
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
         <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
       <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"> --%>
        <script>javascript:window.history.forward(1); </script>
        <style>
            #image_style a{display:block;color:transparent;} 
            #image_style a:hover{background-position:left bottom;}
            body { display : none;} 
        </style>             
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
       	<script language="JavaScript">javascript:window.history.forward(1);</script>
        <script type="text/javascript">
      function callSubmit(val){
      URL="Loginpage";
      var windowName = "Home";
     // var oForm = document.forms[3];
      //alert(oForm.name);
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 1440;
		var h = 830;
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
			//var v=postFormRequest(URL,"",oForm.name);
			var w = window.open (URL, windowName, features);
			w.focus();
			return false;
      }
        </script>
    </head>
    <body style="margin: 0px auto;background-color: #999999;" >
        <s:form name="form1"  method="post" action="" theme="simple" >
            <s:hidden name="loginCount" id="loginCount" />
            <table width="834" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="#333333">
                        <table  bgcolor="#FFFFFF" width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr bgcolor="#00447A">
                                <td height="25" colspan="2">
                                    <div align="center">
                                        <p class="Logon"><br></p>
                                        <p class="Logon">&nbsp;</p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="left">
                                <img src="${pageContext.request.contextPath}/images/Grips Logo 5a.png" border="0" width="230" height="71">
                                </td>
                                <td class="textbold" colspan="2" align="center">
                                    <h2><font color = "#004094" style="font-family: Arial; margin: 0px; font-weight:bold; font-size: 50px;"><s:text name="application.name" /></font></h2>
                                </td>
                            </tr>
                            <tr>
                                <td width="455" height="377" bgcolor="#00447A">
                                    &nbsp;
                                </td>
                                <td width="379" valign="bottom" background="${pageContext.request.contextPath}/images/log_bg.jpg">
                                    <table border="0" align="center" cellpadding="4" cellspacing="0" class="table-border-login" style="font-family: Arial;">
                                        <s:if test="hasActionErrors()">
                                            <tr> 
                                                <td colspan="2" style="color:red">
                                                    <s:actionerror/>
                                                </td>
                                            </tr>
                                        </s:if>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                <center>
                                                	<input type="button"  value="Click here to Login" onclick="callSubmit('login');" class="btn btn-sm btn-success" >
                                                </center>
                                            </td>
                                        </tr>

                                        <tr> 
                                            
                                        </tr>
                                        <tr> 
                                           
                                        </tr>
                                        <tr> 
                                            <td height="9"></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                                
                                            </td>
                                        </tr>
                                         <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                               
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                               
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td colspan="2">
                                               
                                            </td>
                                        </tr>
                        			</table>
                    			</td>
               				 </tr>
           			 	</table>
           			</td>
           		</tr>
           </table>
        </s:form>
        <script>
            if (self == top) {
                var theBody = document.getElementsByTagName('body')[0]
                theBody.style.display = "block"
            } else {
                top.location = self.location
            }            
        </script>
    </body>
</html>