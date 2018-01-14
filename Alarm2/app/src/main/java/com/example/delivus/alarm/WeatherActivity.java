package com.example.delivus.alarm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by Delivus on 03.01.2018.
 */

public class WeatherActivity extends AppCompatActivity
{
    TextView city_field, details_field, current_temperature_field,
            humidity_field, pressure_field, weather_icon, updated_field;

    Typeface weatherFont;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.weather);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

        city_field = (TextView)findViewById(R.id.city_field);
        details_field = (TextView)findViewById(R.id.details_field);
        current_temperature_field = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        updated_field = (TextView)findViewById(R.id.updated_field);
        weather_icon = (TextView)findViewById(R.id.weather_icon);

        weather_icon.setTypeface(weatherFont);

        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            @Override
            public void processFinish(String city, String weather_description, String temperature, String humidity,
                                      String pressure, String weather_updatedOn, String iconText, String sun_rise)
            {
                city_field.setText(city);
                updated_field.setText(weather_updatedOn);
                details_field.setText(weather_description);
                current_temperature_field.setText(temperature);
                humidity_field.setText("Humidity: "+humidity);
                pressure_field.setText("Pressure: "+pressure);
                weather_icon.setText(Html.fromHtml(iconText));
            }
        });
        asyncTask.execute("58.015", "56.2467");
    }
}
