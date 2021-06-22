getJCWebClient({
    onLoad: function() {
        console.log('JC-WebClient установлен');
        JCWebClient2.initialize();
        
        checkJCWebClientVersion(function() {
            console.log('Версия JC-WebClient корректна. Продолжаем работу...');
            document.getElementById('btnDetectDevices').style.display = 'block';
            document.getElementById('btnDetectDevices').addEventListener("click", function(){
            	var select = document.getElementById('sltTokenList');
            	select.style.display = 'block';
            	var slots = JCWebClient2.getAllSlots();
            	
            	console.log(slots.length);
            	
            	for (i = 0; i < slots.length; i++) {
            		var slot = slots[i];
                   	var option = document.createElement("option");
                   	option.text = slot;
                   	select.add(option); 
            	}
            }); 
        });

    },
    onError: function(error) {
        this.onError = undefined;
        
        document.getElementById('divFailure').innerHTML = '<b>Плагин не поддерживается, Необходимо скачать версии 4.0 или вышее, чтобы скачать плагин перейдите по <a href="https://www.aladdin-rd.ru/support/downloads/jc-webclient" target="_blank" style="color:blue"> ссылке </a></b>';
        document.getElementById('divFailure').style.display = 'block;';
        console.log('Установите JC-WebClient !!!');
    }
});

function checkJCWebClientVersion(done) {
    var failureCount = 0;
    
    checkVersion();

    function checkVersion() {
        JCWebClient2.getJCWebClientVersion({
            async: true,
            onResult: function(version, error) {
                if(typeof(error) !== 'undefined') {
                    console.log('Ошибка получения версии JC-WebClient: ' + error);
                }

                if(version < 4) {
                    ++failureCount;

                    if(failureCount === 1) {
                    	 document.getElementById('divFailure').innerHTML = '<b>Версии плагина не поддерживается, Необходимо скачать версии 4.0 или вышее, чтобы скачать плагин перейдите по <a href="https://www.aladdin-rd.ru/support/downloads/jc-webclient" target="_blank" style="color:blue"> ссылке </a></b>';
                         document.getElementById('divFailure').style.display = 'block;';
                         console.log('Обновите JC-WebClient !!!');
                    }

                    setTimeout(function() {
                        checkVersion();
                    }, 2000);
                    return;
                }

                if(failureCount !== 0) {
                    window.location.reload();
                    return;
                }

                done();
            }
        });
    }
}

function getJCWebClient(params) {
    if (typeof(JCWebClient2) !== 'undefined') {
        params.onLoad();
        return;
    }

    getScript(
        'https://localhost:24738/JCWebClient.js',
        params.onLoad,
        function(error) {
            if(typeof(params.onError) === 'function') {
                params.onError(error);
            }

            setTimeout(function() {
                getJCWebClient(params);
            }, 2000);
        }
    );
}

function getScript(src, done, fail) {
    var parent = document.getElementsByTagName('body')[0];

    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = src;

    if (script.readyState) {  // IE
        script.onreadystatechange = function () {
            if (script.readyState === "loaded" || script.readyState === "complete") {
                script.onreadystatechange = null;
                if (typeof (JCWebClient2) === 'undefined') {
                    onFail("JCWebClient is invalid");
                }
                else {
                    done();
                }
            }
            else if (script.readyState !== "loading") {
                onFail("JCWebClient hasn't been loaded");
            }
        }
    }
    else {  // Others
        script.onload = done;
        script.onerror = function() {
            onFail("JCWebClient hasn't been loaded");
        };
    }

    parent.appendChild(script);

    function onFail(errorMsg) {
        parent.removeChild(script);
        fail(errorMsg);
    }
}