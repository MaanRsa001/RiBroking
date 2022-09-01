
function getHTTPObj() {
	var http;
	var browser = navigator.appName;
	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			http = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return http;
}
function addOption(theSel, theText, theValue) {
	var newOpt = new Option(theText, theValue);
	var selLength = theSel.length;
	theSel.options[selLength] = newOpt;
}
function deletePrevious(theSel) {
	var selLength = theSel.length;
	var i;
	for (i = selLength - 1; i > 0; i--) {
		if (theSel.length > 0) {
			theSel.options[i] = null;
		}
	}
}

