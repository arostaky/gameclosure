package com.tealeaf.plugin.plugins;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tealeaf.TeaLeaf;
import com.tealeaf.logger;
import com.tealeaf.plugin.IPlugin;



public class AdmobPlugin extends Activity implements IPlugin {

  private Activity _activity;
  public static Context mContext;
  String admobUnitid = "";
  String testDeviceid = "";
  private static final String TAG = null;
  public AdView adView;
  static boolean runonce = false;
  public TeaLeaf tealeaf;
  public Handler handler;
  ComponentName tparen;
    public AdmobPlugin() {
    }

    public void onStart() {
      showAds();
    }

    public void onStop() {
    }


    public void onNewIntent(Intent intent) {

    }

    public void setInstallReferrer(String referrer) {

    }

    public void onActivityResult(Integer request, Integer result, Intent data) {

    }

    public boolean consumeOnBackPressed() {
        return true;
    }

    public void onBackPressed() {
    }
  public void onCreateApplication(Context applicationContext) {
    mContext = applicationContext;
  }

  public void onCreate(Activity activity, Bundle savedInstanceState) {
    _activity = activity;

         PackageManager manager = _activity.getPackageManager();
		try {
			Bundle meta = manager.getApplicationInfo(_activity.getPackageName(), PackageManager.GET_META_DATA).metaData;
			if (meta != null) {
				admobUnitid = meta.get("AD_UNIT_ID").toString();
				logger.log("{AdmobPlugin} admobID:", admobUnitid);
				testDeviceid = meta.get("TEST_DEVICE").toString();
			}
		} catch (Exception e) {
			logger.log("{AdmobPlugin} Exception on start:", e.getMessage());
		}
  }
  public void onWindowFocusChanged(){

  }

  public synchronized void showAds() {
    adView = new AdView(_activity);
         adView.setAdSize(AdSize.BANNER);
         adView.setAdUnitId(admobUnitid);
         RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                 RelativeLayout.LayoutParams.MATCH_PARENT,
                 RelativeLayout.LayoutParams.WRAP_CONTENT);
         lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
         lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
         adView.setLayoutParams(lp);
        RelativeLayout layout = new RelativeLayout(_activity);

         adView.loadAd(new AdRequest.Builder()
         .addTestDevice(testDeviceid)
         .build());
         adView.setBackgroundColor(android.graphics.Color.TRANSPARENT);
         _activity.addContentView(layout, lp);
         layout.addView(adView);
        // layout.setVisibility( View.GONE );
}
	public void hideAd(){
    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                adView.setVisibility( View.GONE );
                        }});
	}
	public void showAd(){
     ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                adView.setVisibility(View.VISIBLE);
                        }});
        }

       public AdListener adListener = new AdListener() {
        /** Called when an ad is clicked and about to return to the application. */

        @Override
        public void onAdClosed() {
          logger.log("onAdClosed");
         // Toast.makeText(flappyrocketActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
        }
        /** Called when an ad failed to load. */
        @Override
        public void onAdFailedToLoad(int error) {
       //   String message = "onAdFailedToLoad: " + getErrorReason(error);
       //   Log.d(LOG_TAG, message);
       //   Toast.makeText(flappyrocketActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onAdLeftApplication() {
          logger.log("onAdLeftApplication");
         // Toast.makeText(flappyrocketActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
        }

        /**
         * Called when an Activity is created in front of the app (e.g. an
         * interstitial is shown, or an ad is clicked and launches a new Activity).
         */
        @Override
        public void onAdOpened() {
          logger.log("onAdOpened");
        //  Toast.makeText(flappyrocketActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
        }

        /** Called when an ad is loaded. */
    @Override
        public void onAdLoaded() {
          logger.log("onAdLoaded");
          //Toast.makeText(mContext, "onAdLoaded", Toast.LENGTH_SHORT).show();
        }

  };

    @Override
    public void onResume() {
      super.onResume();
      if (adView != null) {
        adView.resume();
      }
    }

    @Override
    public void onPause() {
      if (adView != null) {
        adView.pause();
      }
      super.onPause();
    }

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
      if (adView != null) {
        // Destroy the AdView.
        adView.destroy();
      }
      super.onDestroy();
    }
  }

