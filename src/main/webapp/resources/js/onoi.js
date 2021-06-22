var sessionServerUrl = "https://sessionserver.onoi.kg";
var appName = "EsfApp";
var apiKey = "8AFFDEBA-986E-4A23-9885-2D9FEDA0A058";

function initSmartId() {
  window.SmartId.initialize(sessionServerUrl, appName, newQrCode => {
      document.querySelectorAll('[id="qrCode"]')[0].src = newQrCode;
    },

    authorizationData => {
      UpdateUi();
    }, () => {
      Exit(true);
    }, news => {
      if (news.newsType === "pushMessage") {
        alert("Новое сообщение: " + news.message);
      }

      if (news.newsType === "authorizationConfirmed") {
        alert("Авторизация подтверждена фотографией");
      }

    }, apiKey);
}

function UpdateUi() {
  if (window.SmartId.isAuthorized() && window.SmartId.getAuthorizationData()) {
	  var authorizationData = window.SmartId.getAuthorizationData();
	  if(authorizationData.inn != document.getElementById("target:inn").value
			  && document.getElementById("target:sessionId").value != authorizationData.sessionId
			  && document.getElementById("target:innOrganization").value != authorizationData.innOrganisation) {
		  document.getElementById("target:inn").value = authorizationData.inn;
		  document.getElementById("target:sessionId").value = authorizationData.sessionId;
		  document.getElementById("target:name").value = authorizationData.fullName;
		  document.getElementById("target:innOrganization").value = authorizationData.innOrganisation;
		  document.getElementById("target:submit").click();
	  }
  } else {
    $("#qrCodeBlock").show();
    $("#userInfo").hide();
  }
}

function Exit(closed) {
  if (!closed) {
    window.SmartId.closeSession();
  }
  UpdateUi();
}