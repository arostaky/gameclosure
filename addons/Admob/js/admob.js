var AdmobPlugin = Class(function () {
	this.showAd = function () {
		logger.log("{Admob} Showing banner");

		if (NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent) {
			NATIVE.plugins.sendEvent("AdmobPlugin", "showBanner",
				JSON.stringify({}));
		}
	};
	this.hideAd = function(){
		logger.log("{Admob} Hiding banner");

		if (NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent) {
			NATIVE.plugins.sendEvent("AdmobPlugin", "hideBanner",
				JSON.stringify({}));
		}
	};
});
exports = new AdmobPlugin();
