package com.example.delivus.alarm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Iwolga on 12/11/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("LongLogTag")
    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone extra is ", state);

        NotificationManager notify_manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);

        PendingIntent pendingIntentMain = PendingIntent.getActivity(this, 0, intent_main_activity, 0);

        Notification notify_popup = new Notification.Builder(this)
                .setContentTitle("Alarm is going off")
                .setContentText("Click this")
                .setContentIntent(pendingIntentMain)
                .setSmallIcon(R.drawable.ic_action_call)
                .setAutoCancel(true)
                .build();

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId == 1)
        {
            this.isRunning = true;
            this.startId = 0;

            notify_manager.notify(0,notify_popup);

            media_song = MediaPlayer.create(this, R.raw.uis_elvetie);
            media_song.start();
        }
        else if (this.isRunning && startId == 0)
        {
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;
        }
        else if (!this.isRunning && startId == 0)
        {
            this.isRunning = false;
            this.startId = 0;
        }
        else if (this.isRunning && startId == 1)
        {
            this.isRunning = true;
            this.startId = 1;
        }
        else
        {
            Log.e("Error", "Something wrong Oo");
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        Log.e("on Destroy called", "end");
        super.onDestroy();
        this.isRunning = false;
    }
}
