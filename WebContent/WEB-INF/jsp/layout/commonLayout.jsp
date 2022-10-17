<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
       	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">       				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css" />		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">	
		<link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" ></link>	
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-multiselect.css"></link>	    
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="js/select2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/motor.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script> 
	
	<script src="<%=request.getContextPath()%>/js/bootstrap-multiselect.js" type="text/javascript"></script>
    	<script type="text/javascript">
    	$(window).on('beforeunload', function () {
	         $("input[type=submit], input[type=button]").prop("disabled", "disabled");
	     });
    	</script>
    	<script type="text/javascript">
		if (window.XMLHttpRequest) {
			window.location.href += "#";
			setTimeout("changeHashAgain()", "50");
			var storedHash = window.location.hash;
			window.setInterval(function() {
				if (window.location.hash != storedHash) {
					window.location.hash = storedHash;
				}
			}, 50);
			
    	}else if (window.ActiveXObject) { // IE
			window.location.hash="no-back-button";
			window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
			window.onhashchange=function(){window.location.hash="no-back-button";}
   		}
			appPath = "${pageContext.request.contextPath}/";
			
			function changeHashAgain() {
				window.location.href += "1";
			}
			/*function changeHashOnLoad() {
				window.location.href += "#";
				setTimeout("changeHashAgain()", "50");
			}
		
			function changeHashAgain() {
				window.location.href += "1";
			}
		
			var storedHash = window.location.hash;
			window.setInterval(function() {
				if (window.location.hash != storedHash) {
					window.location.hash = storedHash;
				}
			}, 50);*/
		
			if (document.attachEvent)
				document.attachEvent("onkeydown", my_onkeydown_handler);
			function my_onkeydown_handler(){
				switch (event.keyCode) {
				case 116: // 'F5'
				
					event.returnValue = false;
					event.keyCode = 0;
					window.status = "Refresh Functionality Disabled";
					break;
				}
			}
			shortcut.add("f5", function() {
				window.status = "Refresh Functionality Disabled";
			});
			shortcut.add("Ctrl+f5", function() {
				window.status = "Refresh Functionality Disabled";
			});
			shortcut.add("Ctrl+r", function() {
				window.status = "Refresh Functionality Disabled";
			});
			shortcut.add("Ctrl+Shift+r", function() {
				window.status = "Refresh Functionality Disabled";
			});
			shortcut.add("Ctrl+n", function() {
				window.status = "New Window Functionality Disabled";
			});
			shortcut.add("Alt + Left Arrow", function() {
				window.status = "New Window Functionality Disabled";
			});
			shortcut.add("Alt + Right Arrow", function() {
				window.status = "New Window Functionality Disabled";
			});
		</script>
		<script type="text/javascript">
			function cancelBack(){
	        if (event.keyCode == 37 && event.altKey || event.keyCode == 39 && event.altKey)
		        {
		            event.cancelBubble = true;
		            event.returnValue = false;
		        }
   			 }
		</script>
    <script type="text/javascript">
        var _$ = $ = $.noConflict(true);
    </script>
		<style>
			#loading {
				width: 100%;
				height: 100%;
				top: 0px;
				left: 0px;
				position: fixed;
				display: block;
				opacity: 0.7;
				background-color: #fff;
				z-index: 99;
				text-align: center;
			}

			#loading-image {
				position: absolute;
				top: 30%;
				left: 45%;
				z-index: 100;
				width: 100px;
				height: 100px;
			}

		</style>


		<script type="text/javascript">
 var seconds = 1800;
function worker() {
 postRequestID("${pageContext.request.contextPath}/TimeOutHome.action?","");
}
</script>
        <title>
            <tiles:insertAttribute name="title" ignore="true" />             
        </title>
        <style type="text/css">
        	*{ margin:0;padding:0; }
			html,body{ height:100%;width:100%;}
			body{display:table;}
			.content {display: block;overflow:auto;height:100%;padding-bottom: 40px;box-sizing: border-box;}
			#footer{position: fixed;bottom: 0;left: 0;height: 25px;line-height: 25px;background-color: #c2c2c2;width: 100%;text-align: center;}
			.sidebar{float:left;background:green;height:100%;width:10%;}
			.contents{height:100%;width:100%;overflow:auto;}
        </style>        
    </head>
     <body onkeydown="cancelBack();">
	 <div id="loading" style="width:100%;" >
		 <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" />
	 </div>
     <div class="table0" style="width: 95%; margin: 0 auto;">
    		<div class="tablerow" >
    			<tiles:insertAttribute name="header" />
    		</div>
    		<div class="tablerow" style="margin-left: 2px;">    			
    			<tiles:insertAttribute name="navigation" />
    		</div>
    		<div class="tablerow" style="margin-top: 2px;">    			
    			<tiles:insertAttribute name="menu" />
    		</div>
    		<div class="tablerow contents" style="margin-top: 2px;">
    			<div class="boxcontent" style="overflow: auto; position: relative; height: 68vh; padding: 5px; top: 0; bottom: 25px;">
    			<tiles:insertAttribute name="body"  />
    			<br class="clear"/>
    			</div>
    		</div>
    		<div class="tablerow" style="margin-top: 2px;" id="footer">    			
    			<tiles:insertAttribute name="footer" />    			
    		</div>
    	</div>
    	<s:form name="OldNavigationForm" id="OldNavigationForm">
			<s:hidden name="listPosition"/>
			<s:hidden name="positionType"/>
			<s:hidden name="menuName"/>
			<s:hidden name="menuUrl"/>
		</s:form>
		<SCRIPT type="text/javascript">
        function dnavigation(listPosition,positionType,menuName,menuUrl) {
			document.NavigationForm.listPosition.value = listPosition;
			document.NavigationForm.positionType.value = positionType;
			document.NavigationForm.menuName.value = menuName;
			document.NavigationForm.menuUrl.value = menuUrl;
			document.NavigationForm.action = "${pageContext.request.contextPath}/dnavigationMenu.do";
			document.NavigationForm.submit();
		}
        </SCRIPT>
         <script>

        $("input[type=submit], input[type=button]").click( function()
           {
            postRequestID("${pageContext.request.contextPath}/insertSessionTrackingHome.action?","");
           }
        );
    </script>	
    <script>

        $("body").click( function()
           {
               postRequestID("${pageContext.request.contextPath}/LoginCheck.action?","");
           }
        );
    </script>
        <script>
            if (self == top) {
                var theBody = document.getElementsByTagName('body')[0];
                theBody.style.display = "block"
            } else {
                top.location = self.location
            }
        </script>

        <script type="text/javascript">
            var idleTime = 0;
            $(document).ready(function () {
                var idleInterval = setInterval("timerIncrement()", 60000); // 1 minute

                //Zero the idle timer on mouse movement.
                $(this).mousemove(function (e) {
                    //idleTime = 0;
                });
                $(this).keypress(function (e) {
                    //idleTime = 0;
                });
                $("input.calwithtime").focus(function(){
                    var calObj = new dhtmlXCalendarObject($(this).attr('id'));
                    calObj.setDateFormat("%d/%m/%Y %H:%M");
                });
                $("input.calwithouttime").focus(function(){
                    var calObj = new dhtmlXCalendarObject($(this).attr('id'));
                    calObj.setDateFormat("%d/%m/%Y");
                    if($(this).attr('id')=="toDate"){
                    	if(document.getElementById("fromDate").value==""){
                    		calObj.unload();
                    		alert("Select Start Date");                    		
                    	}else{
                    		calObj.setSensitiveRange(document.getElementById("fromDate").value, null);
                    	}
                    }
                });
                
            });
            function timerIncrement() {
                idleTime = idleTime + 1;
               if (idleTime >28) { 
               
              var  person = confirm("System is Idle for more than 30 minutes,Press Ok  for continue,Cancel For ReLogin");
                if(person)
                {
                worker();worker();timer('extend');
                idleInterval = setInterval("timerIncrement()", 60000);
                idleTime = 0;
                }
                else{
                    //alert("System is Idle for more than 30 minutes, Kindly relogin");
                    window.location.href = "${pageContext.request.contextPath}/Loginout.do";
                }
                }
            }
    _$(function () {//on ready
        (function ($) {//wrapper
            var parent = $('input.dp1').parent().addClass('datePickerParent');
            parent.on('DOMNodeInserted', 'button.ui-datepicker-trigger', function () {
                var $btn = $(this);
                if ($btn.find('i').length < 1)
                    $btn.html('<i class="glyphicon glyphicon-calendar"/>');
            });
        })(_$);
    });    
    

        </script>               
    </body>
</html>
