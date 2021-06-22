(function (w, d) {
    w.sf = {
        elements: {}
    };
    function getElements() {
        var els = w.sf.elements;

        els.sltTokenList = d.getElementById('sltTokenList');
        els.pwdPinCode = d.getElementById('pwdPinCode');
        els.btnEnter = d.getElementById('btnEnter');
        els.btnDetectDevices = d.getElementById('btnDetectDevices');
        els.divFailure = d.getElementById('divFailure');
        els.divStatus = d.getElementById('loadingBar_title');
    }
    function init() {
    	var els = w.sf.elements;
        getElements();
    	connectTumSocket1("AgGCMPhbvxh/LUsCS4yXCkcQ+BbPQI0fV05vrhMJVuQm1BJXf+V0xfhGibzOMozVMPEwh97zenmGkblnjHHG9rMrh54yMDI4MTIyMDIzNTk1OVoA+yJ5wnLaclSRvnGe9IT9CsU2tfZsCza4rfQQLCGkcgOPxgkE/bFqBO1yErbf0tqMywJOAw0AG6dvXrPykO331Q==", function (event) {
    		console.log(event.data);
		 },function (event) {
			els.divFailure.style.display = 'block';
	        els.divFailure.innerHTML = '<b>Ошибка!!! <br/>Убедитесь что у вас включен "ТУМАР-CSP" и "CryptoSocket". Установочный файл вы можете скачать по <a href="/fugi/resources/eID.exe" target="_blank" style="color:blue"> ссылке </a></b>';
		 });
    }
    var btnEnterClickHandlerFn = function () {
    	pkcs7_sign();
    };
	function pkcs7_sign(){
		 var els = w.sf.elements;
	  	 PF('statusDialogMain').show();
	     document.all("signed_data").innerHTML = "";
	     var dataToSign = document.all("data").value;    
	     var pass = document.all("pwdPinCode").value;   
	     var options;
	     options = {"plugin_name":"citiz-kg",	"pass":pass};
	     els.divStatus.innerHTML = 'Поиск подходящего ключа...';
	     LoadKeyFromTokens(options, function (event) {	 
	    	const data = JSON.parse(event.data);
                 if (data.result === 'true') {
                	 let isKeyFound = false;
                     for (cert in data) {
                         if (data.hasOwnProperty(cert) && cert.indexOf("certificate") >= 0) {
                             if (data[cert].keyOid === '1.3.6.1.4.1.6801.1.5.8') {
                            	
                                  keyProfile = data[cert].profile;
                                  keySerialNum = data[cert].serialNumber;
                                  options = {
                                     'profile': keyProfile,
                                     'pass': pass,
                                     'sn': keySerialNum,
                                     'detach': false,
                                     'isConvert': false,
                                     'isCert': true,
                                     'data': dataToSign,
                                     'hashType': 2
                                 };
                                 els.divStatus.innerHTML = 'Выполняется подписание данных...';
                                 Sign(options, function (event) {   
                                	 
                                     const data = JSON.parse(event.data);
                                     if (data.result === 'true') {
                                     	var sign = data.pkcs7;                                       	
                                     	document.getElementById('signed_data').value = sign;
             		                    $("#saveButton").click();
               		                    PF('statusDialogMain').hide();
                                     } else {
                                  	   PF('statusDialogMain').hide();
                                         cancelProcessing('SIGN_ERROR');
                                         console.error(data.code, data.error);
                                     }
                                 });
                                  isKeyFound = true;
                             }
                         }
                     	}
                     if(!isKeyFound){
                    	 PF('statusDialogMain').hide();
                    	 els.divFailure.style.display = 'block';
                       	 els.divFailure.innerHTML = '<b>Ошибка!!! <br/> Сертификат ключа не найден!!! </b>';
                     }
                 }else{
                	 PF('statusDialogMain').hide();
                	 els.divFailure.style.display = 'block';
                   	 els.divFailure.innerHTML = '<b>Ошибка!!! <br/> В процессе считывания ключей с устройства!!! </b>';
                	 
                 }
	     });	     
	  }
	var wndLoadHandlerFn = function () {
       var els = w.sf.elements;
       getElements();
       els.btnEnter.onclick = btnEnterClickHandlerFn;
       try {
       	init();
       }catch(err) {
    	    console.log(err);
    	    els.divFailure.style.display = 'block';
          	els.divFailure.innerHTML = '<b>Ошибка!!! <br/>Убедитесь что у вас включен "ТУМАР-CSP" и "CryptoSocket". Установочный файл вы можете скачать по <a href="/fugi/resources/eID.exe" target="_blank" style="color:blue"> ссылке </a></b>';
       }
	};

    w.onload = wndLoadHandlerFn;

})(window, document);
		    