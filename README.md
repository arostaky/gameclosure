gameclosure
===========

game closure addons and plugins
-----------------------------------------------------
First Addon file: Admob Plugin for banners only (beta)

Usage:

Include the addon in your project manifest as follow:
----------------------
"addons": [
		"Admob"
	],
-----------------
Open your Application.js file and include the plugin as follow:
---------------------------------------------
import plugins.Admob.admob as admob;
-------------------------------------------

Note: in order to add your admob id and test device you need to modify the AdmobPlugin.java.

We will add more functions like : ShowAds or HideAds in the future.

If you want to colaborate with this project please do.

----------------------------------------------------------------------------------------------------------
Facebook Scoreboard:

It's an extension of facebook plugin by Game Closure.

Usage:

Include the addon in your project manifest as follow:

"addons": [
		"facebook"
	],
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"facebookAppID": "yourfaceappID",
		"facebookDisplayName": "yourfaceprojectname"
	},
	"ios": {
		"bundleID": "",
		"appleID": "12345678",
		"version": "1.0.0",
		"icons": {
			"57": "resources/icons/ios57.png",
			"72": "resources/icons/ios72.png",
			"114": "resources/icons/ios114.png",
			"144": "resources/icons/ios144.png",
			"renderGloss": true
		},
		"facebookAppID": "yourfaceappID",
		"facebookDisplayName": "yourfaceprojectname"
	},
	"browser": {
		"facebookAppID": "yourfaceappID",
		"facebookDisplayName": "yourfaceprojectname"
	},
------------------------------------------------------

In order to submit score do the following in your js file:

import plugins.facebook.facebook as fb;

fb.postScore("yourscore", showResponse);

In order to show scores do the following:

Android:

 var getFbScore = function () {
   var fieldsin = 'score, user.fields(first_name,picture)';
           if (device.isAndroid){
            fb.viewScores(fieldsin, function(result){
            var result_replace =  result.replace(/\\/g, "");
            var res_str = '{"data":'+result_replace+'}';
            
-------------------------------------------------------

Browser version:

var getFbScore = function () {
   var fieldsin = 'score, user.fields(first_name,picture)';
   var res_score_str = JSON.stringify(result,['result','data','score'],0);
   
-----------------------------------------------------------

You can retrieve users score, picture and name.

This plugin is also in beta.

