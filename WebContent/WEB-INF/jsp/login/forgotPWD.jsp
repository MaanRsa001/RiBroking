<%-- 
    Author     : Raja.K
    Document   : Common Login Template
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="application.name"/></title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script type="text/javascript" src="javascript/tcal.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>       				
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>		    
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
	<script type="text/javascript">
		 function callSubmit(val){
           	if(val=='back'){
           		document.changepwd.action="Loginpage.action";
           		document.getElementById("status").value="";
           	}else if(val=='submit'){
           		document.changepwd.action="${pageContext.request.contextPath}/LoginpwdForgot.action";
           	}
              document.changepwd.submit();
     	}
	</script>
</head>

<body>

<div class="panel panel-primary">
		<div class="panel-heading">
			<s:text name="application.name"/>
		</div>
	<div class="panel-body" >
	<div class="logonBg">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<s:text name="forgot.password.reset" />
					</div>
					<div class="panel-body" >						
						<br/>
						<div style="color: red;">
							<s:actionerror/>
						</div>
						<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">							
							<div class="form-group">
								<div class="tableWidth" style="color: #000000;">
									<s:if test='"success"==status'>
										<div class="row">
											<div class="col-xs-12" align="center">
												<h5 class="label label-success"> <s:text name="forgot.pass.success"/> </h5>
											</div>
										</div>
									</s:if>
									<s:else>
										<div class="textfield">
											<div class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="user.name" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield name="loginId" id="userId" cssClass="inputBox" maxLength="60" placeholder="Login Name" cssStyle="border: 1px solid #c2c2c2;"/>
											</div>
										</div>
										
										<div class="textfield">
											<div class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="forgot.email" /> <font color="red">*</font>
											</div>
											<div class="tbox">
												<s:textfield name="mailId" id="mailId" cssClass="inputBox" maxLength="60" cssStyle="border: 1px solid #c2c2c2;" />
											</div>
										</div>
										
									</s:else>
									<s:hidden name="status" id="status"/>
									<br class="clear" />
									
								</div>
								<br class="clear"/>
							</div>
						</s:form>				
					</div>
				</div>
			</div>			
		</div>
	</div>
	</div>
	<s:if test='"success"==status'>
		<div class="boxcontent" align="center">
			  <s:hidden name="pwdMsg"/>
              <s:hidden name="home"/>
              <input type="button"  name="Submit1" style="cursor:pointer;" value=" Back " onclick="callSubmit('back')" class="btn btn-sm btn-danger" style="cursor:pointer;" />
          </div>
	</s:if>
	<s:else>
		<div class="boxcontent" align="center">
			<input type="button"  name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-danger" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button"  name="Submit2" style="cursor:pointer;" value=" Reset " class="btn btn-sm btn-warning" onclick="reSetValue();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<s:hidden name="home"/>
            <input type="button"  name="Submit1" style="cursor:pointer;" class="btn btn-sm btn-success" value=" Submit " onclick="callSubmit('submit')"/>
		</div>
	</s:else>
	<br class="clear"/>	
</div>

<%-- 
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td bgcolor="#FFFFFF"><h1><s:text name="application.name"/></h1></td>
              </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
                		<tr>
		                    <td class="heading"><s:text name="forgot.password"/></td>
		                </tr>
                		<s:if test='"success"==status'>
                			<tr><td height="100px;">&nbsp;</td></tr>
			                <tr>
			                   <td align="center" style="padding-right:20px;"> <s:text name="forgot.pass.success"/></td>
			                </tr>
			                <tr><td height="100px;">&nbsp;</td></tr>
                		</s:if>
                		<s:else>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="">
		                      	<table width="" border="0" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #808080; background-color: #F0F0F0;" >
		                      	  <tr><td colspan="2">&nbsp;</td></tr>
		                      	  <tr><td colspan="2" class="headingtext"><s:text name="forgot.password.reset" /></td></tr>
		                      	  <tr><td colspan="2">&nbsp;</td></tr>
			                      <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="user.name" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:textfield name="loginId" id="userId" cssClass="input"/></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="forgot.email" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:textfield name="mailId" id="mailId" cssClass="input" /></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                     </table>
			                   </td>
			                </tr>
			             </s:else>
			             <s:hidden name="status" id="status"/>
		              </table>
		            </td>
                   </tr>
                   <tr>
	                  <td>
                   		<table align="center">
	                   		<s:if test='"success"==status'>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="home"/>
	                              		<input type="button"  name="Submit1" style="cursor:pointer;" value=" Back " onclick="callSubmit('back')" class="btn" style="cursor:pointer;" />
	                            	</td>
	                       	 	</tr>
	                   		</s:if>
	                   		<s:else>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="home"/>
	                            		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;
	                              		<s:reset name="Submit2" cssStyle="cursor:pointer;" value=" Reset " cssClass="btn"/>&nbsp;&nbsp;&nbsp;
	                              		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn" value=" Submit " onclick="callSubmit('submit')"/>	                              		
	                            	</td>
	                       	 	</tr>
	                       	 </s:else>
                   		</table>
	                   	</td>
                    </tr>
        		 </table>
    		</s:form>
    	</td>
    	</tr>
    </table>
    </td></tr>
    </table>--%>
</body>
</html>