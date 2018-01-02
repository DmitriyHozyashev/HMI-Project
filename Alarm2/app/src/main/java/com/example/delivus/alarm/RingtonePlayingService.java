package com.example.delivus.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Iwolga on 12/11/2017.
 */

public class RingtonePlayingService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Integer intent)
    {
        Log.e("In the service", "Start command");
        return START_NOT_STICKY;
    }
}
