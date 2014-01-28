package com.prototype.contacts.CallingScreen;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class IncomingCallActivity extends Activity {

    private static final String TAG = IncomingCallActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        setContentView(R.layout.main);

        String phoneNumber = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (phoneNumber == null) {
            phoneNumber = "Cannot retrieve phone number";
        }

        TextView text = (TextView) findViewById(R.id.display_text);
        text.setText("Incoming call from " + phoneNumber);
    }

    @Override
    public void onAttachedToWindow() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                + WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}
