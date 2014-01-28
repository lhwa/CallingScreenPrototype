package com.prototype.contacts.CallingScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = IncomingCallBroadcastReceiver.class.toString();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d(TAG, "onReceive() called");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.d(TAG, state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING) ||
                    state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Log.d(TAG, "Phone is ringing");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(context, IncomingCallActivity.class);
                        i.putExtras(intent);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(i);
                    }
                }, 10);

            }
        }
    }
}
