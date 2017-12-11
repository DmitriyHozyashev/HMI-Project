package com.example.delivus.alarm;

import android.app.AlarmManager;
import android.content.Context;
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

        //initialize timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.alarm_timepicker);

        //initialize alarm_status
        alarm_status = (TextView) findViewById(R.id.alarm_status);

        //create an instance of calendar
        Calendar calendar = Calendar.getInstance();

        //initialize buttons
        Button start_alarm = (Button) findViewById(R.id.start_alarm);
        //create an onClick listener to start the alarm
        start_alarm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //method that changes the status alarm
                set_alarm_text("Alarm on!");
                return false;
            }
        });

        Button stop_alarm = (Button) findViewById(R.id.stop_alarm);
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
