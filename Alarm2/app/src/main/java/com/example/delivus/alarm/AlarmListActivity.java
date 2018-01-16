package com.example.delivus.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Iwolga on 12/13/2017.
 */

public class AlarmListActivity extends AppCompatActivity{
    static final private int ADD = 0;
    static final private int EDIT = 1;
    static final private String FILENAME = "AlarmTime.txt";
    ListView alarm_list;
    ArrayList<String> alarmArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_activity);
        alarm_list = (ListView)findViewById(R.id.alarm_list);
        alarm_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AlarmListActivity.this, MainActivity.class);
                String s = (String)alarm_list.getItemAtPosition(position);
                intent.putExtra("alarmFrom",s);
                startActivityForResult(intent,EDIT);
            }
        });
        alarmArrayList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alarmArrayList);
        alarm_list.setAdapter(adapter);
        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            byte [] buffer = new byte[5];
            int available = fileInputStream.available()/5;
            for (int i = 0; i < available;i++){
                fileInputStream.read(buffer,0,5);
                String s = new String(buffer);
                alarmArrayList.add(s);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmListActivity.this, MainActivity.class);
                startActivityForResult(intent,ADD);
            }
        });

        FloatingActionButton w = (FloatingActionButton)findViewById(R.id.w);
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AlarmListActivity.this, WeatherActivity.class);
                startActivity(intent2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD) {
            if (resultCode == RESULT_OK) {
                alarmArrayList.add(data.getStringExtra("alert"));
            }else {

            }
        }
        if (requestCode == EDIT) {
            if (resultCode == RESULT_CANCELED) {
                alarmArrayList.remove(data.getStringExtra("alert"));
            }else {

            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME,MODE_WORLD_READABLE);
            for (String s : alarmArrayList){
                fileOutputStream.write(s.getBytes());
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
