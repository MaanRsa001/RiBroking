function postRequest(strURL, id)
{
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function() 
	{
		if (xmlHttp.readyState == 4) 
		{
			document.getElementById(id).innerHTML=xmlHttp.responseText;
		}
   }
	xmlHttp.send(null);
}
function postFormRequest(strUrl, id, formId) {
    $.ajax({
		url : strUrl,
		type : "POST",
		data : $("#" + formId).serialize(),
		error : function() {
			$('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
		},
		success : function(data) {
			$('#' + id).html(data);
		},
		beforeSend : function() {
			$('#loading').show();
			$('.ajaxLoader').show();
		},
		complete : function() {
			$('#loading').hide();
			$('.ajaxLoader').hide();
		}
	});
}
function postFormLoader(strUrl, id) {
    $.ajax( {
        url : strUrl,
        error : function() {
            $('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
        },
        success : function(data) {
            $('#' + id).html(data);
        },
        beforeSend : function() {
            $('#loading').show();
            $('.ajaxLoader').show();
        },
        complete : function() {
			$('#loading').hide();
            $('.ajaxLoader').hide();
        },
        type : 'POST'
    });
}
function postRequestID(strUrl, id) {
	$.ajax( {
		url : strUrl,
		error : function() {
			$('#' + id).html('<p>An error has occurred in jquery Ajax</p>');
		},
		success : function(data) {
			$('#' + id).html(data);
		},
		beforeSend : function() {
			//$('#loading').show();
			//$('.ajaxLoader').show();
		},
		complete : function() {
		//	$('#loading').hide();
			//$('.ajaxLoader').hide();
		},
		type : 'POST'
	});
}
function Comma(Num)
{
	  Num += '';
      Num = Num.replace(/,/g, '');
      if(Num.length>20)
      {
     	 alert('Exceeding Your Limit');
     	 Num=Num.substring(0,20);
      }
      x = Num.split('.');
      x1 = x[0];
      x2 = x.length > 1 ? '.' + x[1] : '';
      var rgx = /(\d+)(\d{3})/;
      while (rgx.test(x1))
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
      return x1 + x2;
}
function checkDecimals10(obj)
{
	var validno="1234567890.-";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+10);
	}else
	{
		if(val.length>10)
			//obj.value=temp.substring(0,index+4);		
			obj.value=temp.substring(0,index+11);
			
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}
function checkDecimals(obj)
{
	var validno="1234567890.-";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+4);
	}else
	{
		if(val.length>4)
			//obj.value=temp.substring(0,index+4);		
			obj.value=temp.substring(0,index+5);
			
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}
function checkNumbers(obj)
{
	var validno="1234567890";
	var temp=obj.value.replace(new RegExp(',','g'),'');
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}

function validateSpecialChars(obj)
{
	
	var validno="1234567890/";
	var temp=obj.value;
	
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
	
}
function checkDecimalsValue(obj,count)
{
	var validno="1234567890.-,";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+19);
	}else
	{
		if(val.length>count)
			obj.value=temp.substring(0,parseInt(index)+parseInt(count)+parseInt('1'));		
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}
function checkDecimals15(obj)
{
	var validno="1234567890.-,";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+19);
	}else
	{
		if(val.length>4)
			obj.value=temp.substring(0,index+5);		
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}

function checkDecimalsValue(obj,count)
{
	var validno="1234567890.-,";
	var temp=obj.value;
	var finalValue="";
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+19);
	}else
	{
		if(val.length>count)
			obj.value=temp.substring(0,parseInt(index)+parseInt(count)+parseInt('1'));		
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}
function allow2DigitDecValues(obj)
{
	
	var validno="1234567890.-,";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index==-1)
	{
	if(val.length>1)
		temp=temp.substring(0,index+19);
	}else
	{
		if(val.length>2)
			temp=temp.substring(0,index+3);		
	}
	finalValue=temp;
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			finalValue=finalValue.replace(temp.charAt(i),"");
		}
	}
	obj.value=finalValue;
return false;
}
function allow8DigitDecValues(obj)
{
	
	var validno="1234567890.-,";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index==-1)
	{
	if(val.length>1)
		temp=temp.substring(0,index+19);
	}else
	{
		if(val.length>8)
			temp=temp.substring(0,index+9);		
	}
	finalValue=temp;
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			finalValue=finalValue.replace(temp.charAt(i),"");
		}
		
	}
	obj.value=finalValue;
return false;
}
function allowOneDot(txt) {
	var prevValue='';
    var dots = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
        if(text[i]=='.') dots++;
        if(dots<=1) { 
        	prevValue += text[i];
        }
    }
    txt.value= prevValue;
    return false;
}

function allowOneAndMiddleMinus12(txt) {
	var prevValue='';
    var min = 1;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=1; i<length; i++) {
    	
        if(text[i]=='-') min++;
        if(min<=1) { 
        	prevValue += text[i];
        }
    }
    txt.value= prevValue;
    return false;
}

function enableForm(theform, disable, include) {
	if (document.all || document.getElementById) {
	for (i = 0; i < theform.length; i++) {
	var formElement = theform.elements[i];
	if (include.indexOf(formElement.name)!=-1) {
	formElement.disabled = disable;
	}else if (include.indexOf('fragile')!=-1 && formElement.name.indexOf('fragile')!=-1) {
	formElement.disabled = disable;
	}
	}
	}
	}

function enableForm1(theform, disable, include) {
	if (document.all || document.getElementById) {
	for (i = 0; i < theform.length; i++) {
	var formElement = theform.elements[i];
	if (include.indexOf(formElement.name)!=-1 && formElement.name!="") {
	formElement.disabled = disable;
	}
	}
	}
	}
function disableForm(theform, disable, exclude) {
	if (document.all || document.getElementById) {
	for (i = 0; i < theform.length; i++) {
	var formElement = theform.elements[i];
	if (exclude.indexOf(formElement.name)==-1) {
	formElement.disabled = disable;
	}
	}
	}
	}
function replaceComma(inputFrom,names){
	if (document.all || document.getElementById) {
		for (i = 0; i < inputFrom.length; i++) {
			try{
				var formElement = inputFrom.elements[i];
				if (names.indexOf(formElement.name.toString().indexOf("[")!=-1?formElement.name.toString().substring(0,formElement.name.toString().indexOf("[")):(formElement.name.toString().indexOf("_")!=-1?formElement.name.toString().substring(0,formElement.name.toString().indexOf("_")):formElement.name.toString()))!=-1) {
					formElement.value=formElement.value.replace(new RegExp(',','g'),'');
					}else if (names.indexOf('fragile')!=-1 && formElement.name.indexOf('fragile')!=-1) {
					formElement.value=formElement.value.replace(new RegExp(',','g'),'');
				}
			}catch(err){}
		}
	}
}

function negative(id,val){
	if(parseFloat(val)<0){
		
	$('#' + id).val('');
	}
	else if(val=='-'){
		$('#' + id).val('');
	}
}


$(window).load(function() {
    $('#loading').hide();
});


function middleMinusRestriction(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
	    if(i!=0){
	        if(text[i]=='-') min++;
	        if(min<1) { 
	        	prevValue += text[i];
	        }
	        }else{
	        	prevValue += text[i];
	        }
        }
    txt.value= prevValue;
    return false;
}

function middleMinusRestrictionNeg(txt) {
	var prevValue='';
    var min = 0;
    var length = txt.value.length;
    var text = txt.value;
    for(var i=0; i<length; i++) {
        if(text[i]=='-') min++;
        if(min<1) { 
        	prevValue += text[i];
        }
        }
    txt.value= prevValue;
    return false;
}