package com.example.delivus.alarm;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Iwolga on 12/11/2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        //Intent intent1 = new Intent(Alarm_Receiver.this, AlarmActivity.class);
       // startActivity(intent1);
        Log.e("We are in the reciever", "Yay");
    }
}
