package com.example.delivus.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    EditText alarm_time;
    TextView alarm_status;
    PendingIntent pendingIntent;
    String oldAlert = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent1 = getIntent();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_time = (EditText) findViewById(R.id.Alarm_time);
        alarm_status = (TextView) findViewById(R.id.alarm_status);

        //create an intent to the Alarm Receiver class
        final Intent my_intent = new Intent(MainActivity.this, Alarm_Receiver.class);

        if (intent1.getExtras() != null){
            alarm_time.setText(intent1.getStringExtra("alarmFrom"));
            oldAlert = intent1.getStringExtra("alarmFrom");
        }

        //initialize buttons
        FloatingActionButton start_alarm = (FloatingActionButton) findViewById(R.id.start_alarm);
        //create an onClick listener to start the alarm
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkTime(alarm_time.getText().toString())) {
                    Intent intent = new Intent();
                    String[] test = alarm_time.getText().toString().split(":");
                    int hour = Integer.valueOf(test[0]);
                    int minute = Integer.valueOf(test[1]);

                    Calendar setedtime = Calendar.getInstance();
                    setedtime.set(Calendar.HOUR_OF_DAY, hour);
                    setedtime.set(Calendar.MINUTE, minute);
                    Calendar timenow = Calendar.getInstance();

                    if (setedtime.getTimeInMillis() < timenow.getTimeInMillis())
                        setedtime.add(Calendar.DAY_OF_MONTH, 1);

                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (minute < 10)
                        minute_string = '0' + minute_string;
                    //convert int values to string
                    set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);

                    my_intent.putExtra("extra", "alarm on");

                    //Create pending intent
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //SET the alarm manager
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setedtime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    intent.putExtra("alert",alarm_time.getText().toString());
                    intent.putExtra("oldalert",oldAlert);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else{
                    alarm_status.setText("Time input error");
                }
            }
        });

        FloatingActionButton stop_alarm = (FloatingActionButton) findViewById(R.id.stop_alarm);
        //create an onClick listener to stop the alarm or unset the alarm
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pendingIntent != null) {
                    set_alarm_text("Alarm off!");
                    alarmManager.cancel(pendingIntent);
                }
                my_intent.putExtra("extra", "alarm off");
                sendBroadcast(my_intent);
            }
        });
        FloatingActionButton delete_alarm = (FloatingActionButton) findViewById(R.id.delete_alarm);
        delete_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pendingIntent != null) {
                    set_alarm_text("Alarm off!");
                    alarmManager.cancel(pendingIntent);
                }
                Intent intent = new Intent();
                intent.putExtra("alert",alarm_time.getText().toString());
                setResult(78,intent);
                finish();
                //my_intent.putExtra("extra", "alarm off");
                //sendBroadcast(my_intent);
            }
        });
    }

    private void set_alarm_text(String output) {
        alarm_status.setText(output);
    }

    public static boolean checkTime(String time){
        Pattern p = Pattern.compile("^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$");
        Matcher m = p.matcher(time);
        return m.matches();
    }
}
