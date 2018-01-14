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
        Log.e("We are in the reciever", "Yay");
        String extra_string = intent.getExtras().getString("extra");
        Log.e("What is the key?", extra_string);
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        service_intent.putExtra("extra", extra_string);
        context.startService(service_intent);
    }
}
