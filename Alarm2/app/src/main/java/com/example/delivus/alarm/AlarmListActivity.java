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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Iwolga on 12/13/2017.
 */

public class AlarmListActivity extends AppCompatActivity{
    static final private int ADD = 0;
    static final private int EDIT = 1;
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
        FileInputStream fileInputStream = null;
        String s;
        StringBuilder sb = new StringBuilder();
        /*try{
            fileInputStream = openFileInput("AlertTime");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                while ((s = reader.readLine()) != null){
                    sb.append(s);
                    Log.w("1",sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileInputStream != null){
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
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
            if (resultCode == RESULT_OK) {
                alarmArrayList.remove(data.getStringExtra("alert"));
            }else {

            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
