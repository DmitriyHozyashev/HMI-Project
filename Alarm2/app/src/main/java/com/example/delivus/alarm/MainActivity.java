package com.example.delivus.alarm;

import android.app.AlarmManager;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    TextView alarm_status;
    Context context;

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
        alarm_timepicker = (TimePicker) findViewById(R.id.alarm_timepicker);
        alarm_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());


            }
        });
        //initialize alarm_status
        alarm_status = (TextView) findViewById(R.id.alarm_status);



        //initialize buttons
        FloatingActionButton start_alarm = (FloatingActionButton) findViewById(R.id.start_alarm);
        //create an onClick listener to start the alarm
        start_alarm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //method that changes the status alarm
                set_alarm_text("Alarm on! " + calendar.toString());
                return false;
            }
        });

        FloatingActionButton stop_alarm = (FloatingActionButton) findViewById(R.id.stop_alarm);
        //create an onClick listener to stop the alarm or unset the alarm
        start_alarm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //method that changes the status alarm
                set_alarm_text("Alarm off!");
                return false;
            }
        });
    }

    private void set_alarm_text(String output) {
        alarm_status.setText(output);
    }
}
