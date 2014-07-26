# Game Closure DevKit Plugin: Admob

Admob only supports Android build targets.

## Usage

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"Admob"
],
~~~

Under the Android section, you can configure the Admob plugin:

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"admobUnitid": "myadmobunitid",
		"testDeviceid":"mytestdeviceid"
	}
~~~
In your game Application.js you can configure the Admob plugin like this:
~~~
import plugins.Admob.admob as admob;

you cannot use yet the functions:

admob.showAd();

or

admob.hideAd();

(under development)
~~~


## Testing

To test for successful integration, build your game:

~~~
basil debug native-android --clean --open
~~~

Then watch logcat:

~~~
adb logcat | grep -i admob
~~~

Look out for warnings in the console that indicates a problem with your setup.