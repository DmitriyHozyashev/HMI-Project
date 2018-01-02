package com.example.delivus.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    //TimePicker alarm_timepicker;
    EditText alarm_time;
    TextView alarm_status;
    Context context;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        //initialize alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //create an instance of calendar
        final Calendar calendar = Calendar.getInstance();

        //initialize timepicker
        //alarm_timepicker = (TimePicker) findViewById(R.id.alarm_timepicker);

        //initialize alarm_status
        alarm_status = (TextView) findViewById(R.id.alarm_status);


        //create an intent to the Alarm Receiver class
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);


        //initialize buttons
        FloatingActionButton start_alarm = (FloatingActionButton) findViewById(R.id.start_alarm);
        //create an onClick listener to start the alarm
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm_time = (EditText) findViewById(R.id.Alarm_time);
                if (checkTime(alarm_time.getText().toString())) {
                    String[] test = alarm_time.getText().toString().split(":");
                    int hour = Integer.valueOf(test[0]);
                    int minute = Integer.valueOf(test[1]);

                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);

                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);


                    //calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                    //calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                    //int hour = alarm_timepicker.getHour();
                    //int minute = alarm_timepicker.getMinute();

                    //String hour_string = String.valueOf(hour);
                    //String minute_string = String.valueOf(minute);

                    if (minute < 10)
                        minute_string = '0' + minute_string;
                    //convert int values to string
                    set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);
                    //Create pending intent
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //SET the alarm manager
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
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
                set_alarm_text("Alarm off!");
                alarmManager.cancel(pendingIntent);
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
