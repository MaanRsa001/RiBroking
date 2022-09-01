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
			function cancelBack(){
		        if (event.keyCode == 37 && event.altKey || event.keyCode == 39 && event.altKey)
			        {
			            event.cancelBubble = true;
			            event.returnValue = false;
			        }
	   			 }