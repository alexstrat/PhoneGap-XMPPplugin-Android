package eurecom.phonegap;

import com.phonegap.DroidGap;

import android.app.Activity;
import android.os.Bundle;

public class HangmanActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}