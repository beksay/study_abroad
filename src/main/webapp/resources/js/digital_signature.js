(function (w, d) {
    w.sf = {
        elements: {},
        pluginManager: {}
    };

    function getPluginManager() {
        w.sf.pluginManager = new PluginManager();
        return w.sf.pluginManager.getPlugin();
    }

    function getElements() {
        var els = w.sf.elements;

        els.sltTokenList = d.getElementById('sltTokenList');
        els.pwdPinCode = d.getElementById('pwdPinCode');
        els.btnEnter = d.getElementById('btnEnter');
        els.btnDetectDevices = d.getElementById('btnDetectDevices');
        els.divFailure = d.getElementById('divFailure');
    }

    var failureDefaultHandlerFn = function (error) {
        var pluginManager = w.sf.pluginManager;
        var overlays = document.getElementsByClassName("ui-widget-overlay");
        var els = w.sf.elements;
        var i;
        for (i = 0; i < overlays.length; i++) {
        	overlays[i].style.display = "none";
        }
        
        els.divFailure.innerHTML = error.message;
        
        els.divFailure.style.display = 'block';
    };

    var successAuth = (function () {
        return function (p7b) {
            
        	var els = w.sf.elements;
        	var pluginManager = w.sf.pluginManager;
        	var device = w.parseInt(els.sltTokenList.value);
            var pin = els.pwdPinCode.value;
            var data = d.getElementById('data').value;
            pluginManager.sign(device, data, pin)
                            .then(function(signed) {
                            	var dta = {
                            			data: data,
                            			sign: signed
                            	};
                            	d.getElementById('signed_data').value = dta.sign;
                            	$("#saveButton").click();
                            	PF('statusDialogMain').hide();
                            })
                            .catch(function (error) {
                                alert(error.message);
                                PF('statusDialogMain').hide();
                            });
        };
    })();
    
    
    

    function Login() {
    	 
    	PF('statusDialogMain').show();
        var els = w.sf.elements,
            pluginManager = w.sf.pluginManager,
            device = w.parseInt(els.sltTokenList.value),
            pin = els.pwdPinCode.value;

        els.divFailure.style.display = 'none';

        pluginManager.login(device, pin)
            .then(function (auth) {
                successAuth(auth);
            })
            .catch(function (error) {
            	PF('statusDialogMain').hide();
            	failureDefaultHandlerFn(error);
            });
    }

    var getDeviceTypeSuccessHandlerFn = function (device) {
        var name = device.name,
            deviceIndex = w.sf.deviceIndex,
            option = document.createElement('option');

        option.value = deviceIndex;
        option.innerHTML = '#' + (deviceIndex + 1) + ' - ' + name;

        sltTokenList.appendChild(option);

        w.sf.deviceIndex++;
    };

    var btnDetectDevicesClickHandlerFn = function () {
        var els = w.sf.elements,
            pluginManager = w.sf.pluginManager,
            dLen, o, option;

        els.pwdPinCode.value = '';
        els.divFailure.style.display = 'none';
        els.sltTokenList.style.display = 'none';
        els.pwdPinCode.style.display = 'none';
        els.btnEnter.style.display = 'none';

        pluginManager.getTokenList()
            .then(function (vals) {
                if (vals.length) {
                    while (option = els.sltTokenList.firstChild) {
                        els.sltTokenList.removeChild(option);
                    }

                    w.sf.deviceIndex = 0;
                    option = document.createElement('option');
                    option.value = '';
                    option.innerHTML = '-- выберите устройство --';

                    els.sltTokenList.appendChild(option);

                    for (o = 0; o < vals.length; o++) {
                        getDeviceTypeSuccessHandlerFn(vals[o]);
                    }
                    els.sltTokenList.style.display = 'initial';
                }
            })
            .catch(function (error) {
                failureDefaultHandlerFn(error);
            });
    };

    var sltTokenListChangeHandlerFn = function () {
        var els = w.sf.elements;

        els.pwdPinCode.style.display = this.value ? 'initial' : 'none';

        els.pwdPinCode.value = '';

        els.pwdPinCode.focus();
    };

    var pwdPinCodeKeyUpHandlerFn = function () {
    	var els = w.sf.elements;
    	els.btnEnter.style.display = this.value.length ? 'initial' : 'none';
    };

    var btnEnterClickHandlerFn = function () {
        Login();
    };

    var wndLoadHandlerFn = function () {
        var els = w.sf.elements;

        getElements();
        getPluginManager()
            .then(function () {
                var pluginManager = w.sf.pluginManager;

                els.btnDetectDevices.onclick = btnDetectDevicesClickHandlerFn;
                els.sltTokenList.onchange = sltTokenListChangeHandlerFn;
                els.pwdPinCode.onkeyup = pwdPinCodeKeyUpHandlerFn;
                els.btnEnter.onclick = btnEnterClickHandlerFn;
                try {
                    if (pluginManager.plugin.valid) {
	                    els.btnDetectDevices.style.display = 'initial';
	                    els.btnDetectDevices.onclick();
	                } else {
	                    els.divFailure.style.display = 'block';
	
	                    els.divFailure.innerHTML = 'Плагин не поддерживается';
	                }
	            }
                catch(err) {
                	els.divFailure.style.display = 'block';
                	els.divFailure.innerHTML = '<b>Плагин не поддерживается, чтобы скачать плагин перейдите по <a href="http://www.rutoken.ru/support/download/rutoken-plugin/" target="_blank" style="color:blue"> ссылке </a></b>';
                }
                
            });
    };

    w.onload = wndLoadHandlerFn;

})(window, document);
