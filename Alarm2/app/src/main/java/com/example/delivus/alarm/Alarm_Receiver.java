package com.example.delivus.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Iwolga on 12/11/2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the reciever", "Yay");
    }
}
