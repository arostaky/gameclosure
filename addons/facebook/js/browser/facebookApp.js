/**
 * @license
 * This file is part of the Game Closure SDK.
 *
 * The Game Closure SDK is free software: you can redistribute it and/or modify
 * it under the terms of the Mozilla Public License v. 2.0 as published by Mozilla.

 * The Game Closure SDK is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Mozilla Public License v. 2.0 for more details.

 * You should have received a copy of the Mozilla Public License v. 2.0
 * along with the Game Closure SDK.  If not, see <http://mozilla.org/MPL/2.0/>.
 */

import lib.Callback;

var withFacebook = new lib.Callback();
var _appID;

// create a mock facebook object that lets us call the facebook JS SDK synchronously

var FB = {};
wrapFBCall( "api", "getLoginStatus", "getAuthResponse", "getAccessToken", "getUserID",
			"login", "logout", "share", "publish", "addFriend", "init", "ui","publish_actions");

function wrapFBCall() {
	for (var i = 0, n = arguments.length; i < n; ++i) {
		(function (method) {
			FB[method] = function () {
				var args = arguments;
				withFacebook.run(function () {
					GLOBAL.FB[method].apply(GLOBAL.FB, args);
				})
			}
		})(arguments[i]);
	}
}

// run any callback function after the FB JS SDK loads
exports.withFacebook = function () {
	withFacebook.forward(arguments);
}

// start the init process
var INIT_TIMEOUT = CONFIG.addons.facebook && CONFIG.addons.facebook.timeout || 20000;
exports.init = function (appID) {
	logger.log('Initializing Facebook canvas app with ID:', appID);
	_appID = appID;

	if (GLOBAL.FB) {
		_onload();
	} else {
		withFacebook.runOrTimeout(function () {
			logger.log('async init succeeded');
		}, function () {
			logger.log('async init timed out');
			exports.timedOut = true;
		}, INIT_TIMEOUT);

		// this will run when/if the facebook code loads
		window.fbAsyncInit = bind(this, _onload);
	}
};

function _onload() {
	GLOBAL.FB.init({
		appId  : _appID,
		status : true,
		cookie : true,
		xfbml  : false,
		frictionlessRequests: true,
		channelUrl: null,//window.location.protocol + "//" + window.location.hostname + "/facebook_channel.html",
		oauth : true,
		version : 'v2.0'
	});

	if (exports.timedOut) {
		exports.timedOut = false;
	}

	logger.log('facebook initialized');
	withFacebook.fire(GLOBAL.FB);
}

exports.getMe = function(cb) {
	FB.api('/me', cb);
};

exports.login = function(cb) {
	//FB.login(cb, {scope: 'public_profile, user_friends, publish_actions'});
	FB.login(cb);
	//logger.log('este es login:',cb);
	//FB.login(cb);
};

exports.logout = function(cb) {
	FB.logout(cb);
};

exports.isOpen = function(cb) {
	FB.getLoginStatus(cb);
};

exports.fql = function(params, cb) {
	var encodedQuery = encodeURIComponent(params.query);
	console.log(encodedQuery);
	FB.api('/fql?q=' + encodedQuery, cb);
};

exports.getFriends = function(cb) {
	FB.api('/me/friends', cb);
};

exports.postStory = function(params, cb) {
	params = merge({method: 'feed'}, params);
	FB.ui(params, cb);
};
exports.postScore = function(params, cb){
	//params = merge({method: 'score'}, params);
	//FB.api(params, cb);
    //FB.api('/me/scores/','post', {'score': params}, cb);
    FB.api('/me/scores/','post', params, cb);
    //logger.log('queda de postScore:',params);
};
exports.viewScores = function(params, cb){
	/*var encodedQuery = encodeURIComponent(params.query);
	console.log(encodedQuery);*/
	//FB.api('/'+_appID+'/scores/', {"fields": params}, cb);
   FB.api('/'+_appID+'/scores/', params, cb);
    //logger.log(params, cb);
};
/*export.viewScores = function(params, cb){
    FB.api('/1487908818107577/scores/', {"fields": params}, cb);
};*/

// export the FB API functions
exports.FB = FB;
