package com.novaagrisystems.agrimeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.novaagrisystems.agrimeter.Helpers.getDate;
import static com.novaagrisystems.agrimeter.Helpers.getTime;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    FirebaseDatabase database;

    @BindView(R.id.currentHumidityValue) TextView currentHumidityValue;
    @BindView(R.id.humidityCurrentDate) TextView humidityCurrentDate;
    @BindView(R.id.humidityCurrentTime) TextView humidityCurrentTime;
    private DatabaseReference humiditySensorRef;
    private ChildEventListener humidityEventListener;


    @BindView(R.id.currentTemperatureValue) TextView currentTemperatureValue;
    @BindView(R.id.temperatureCurrentDate) TextView temperatureCurrentDate;
    @BindView(R.id.temperatureCurrentTime) TextView temperatureCurrentTime;
    private DatabaseReference temperatureSensorRef;
    private ChildEventListener temperatureEventListener;

    @BindView(R.id.currentMoistureValue) TextView currentMoistureValue;
    @BindView(R.id.moistureCurrentDate) TextView moistureCurrentDate;
    @BindView(R.id.moistureCurrentTime) TextView moistureCurrentTime;
    private DatabaseReference moistureSensorRef;
    private ChildEventListener moistureEventListener;

    @BindView(R.id.currentLightValue) TextView currentLightValue;
    @BindView(R.id.lightCurrentDate) TextView lightCurrentDate;
    @BindView(R.id.lightCurrentTime) TextView lightCurrentTime;
    private DatabaseReference lightSensorRef;
    private ChildEventListener lightEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }

        humiditySensorRef =  database.getReference("sensorData").child("humidity");
        temperatureSensorRef =  database.getReference("sensorData").child("temperature");
        moistureSensorRef =  database.getReference("sensorData").child("soilMoisture");
        lightSensorRef =  database.getReference("sensorData").child("light");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("   Overview");

        setHumiditySensorListener();
        setTemperatureSensorListener();
        setMoistureSensorListener();
        setLightSensorListener();
    }


    private void setHumiditySensorListener() {
        humidityEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SensorEvent sensorEvent = dataSnapshot.getValue(SensorEvent.class);
                Log.d(TAG, "onChildAdded:" + sensorEvent.datetime + " : " + sensorEvent.value);

                currentHumidityValue.setText(sensorEvent.value.intValue() + getString(R.string.humidity_units));
                humidityCurrentDate.setText(getDate(sensorEvent.datetime));
                humidityCurrentTime.setText(getTime(sensorEvent.datetime));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        humiditySensorRef.addChildEventListener(humidityEventListener);
    }

    private void setTemperatureSensorListener() {
        temperatureEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SensorEvent sensorEvent = dataSnapshot.getValue(SensorEvent.class);
                Log.d(TAG, "onChildAdded:" + sensorEvent.datetime + " : " + sensorEvent.value);

                currentTemperatureValue.setText(sensorEvent.value.intValue() + getString(R.string.temperature_units));
                temperatureCurrentDate.setText(getDate(sensorEvent.datetime));
                temperatureCurrentTime.setText(getTime(sensorEvent.datetime));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        temperatureSensorRef.addChildEventListener(temperatureEventListener);
    }

    private void setMoistureSensorListener() {
        moistureEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SensorEvent sensorEvent = dataSnapshot.getValue(SensorEvent.class);
                Log.d(TAG, "onChildAdded:" + sensorEvent.datetime + " : " + sensorEvent.value);

                currentMoistureValue.setText(sensorEvent.value.intValue() + getString(R.string.moisture_units));
                moistureCurrentDate.setText(getDate(sensorEvent.datetime));
                moistureCurrentTime.setText(getTime(sensorEvent.datetime));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        moistureSensorRef.addChildEventListener(moistureEventListener);
    }

    private void setLightSensorListener() {
        lightEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SensorEvent sensorEvent = dataSnapshot.getValue(SensorEvent.class);
                Log.d(TAG, "onChildAdded:" + sensorEvent.datetime + " : " + sensorEvent.value);

                currentLightValue.setText(sensorEvent.value.intValue() + getString(R.string.light_units));
                lightCurrentDate.setText(getDate(sensorEvent.datetime));
                lightCurrentTime.setText(getTime(sensorEvent.datetime));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        lightSensorRef.addChildEventListener(lightEventListener);
    }



    @OnClick(R.id.humidityCard)
    public void openhumidityCard() {
        Intent intent = new Intent(MainActivity.this, HumidityDetails.class);
        startActivity(intent);
    }

    @OnClick(R.id.temperatureCard)
    public void openTemperatureCard() {
        Intent intent = new Intent(MainActivity.this, TemperatureDetails.class);
        startActivity(intent);
    }

    @OnClick(R.id.moistureCard)
    public void openMoistureCard() {
        Intent intent = new Intent(MainActivity.this, MoistureDetails.class);
        startActivity(intent);
    }

    @OnClick(R.id.lightCard)
    public void openLightCard() {
        Intent intent = new Intent(MainActivity.this, LightDetails.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (humidityEventListener != null) {
            humiditySensorRef.removeEventListener(humidityEventListener);
        }
        if (temperatureEventListener != null) {
            temperatureSensorRef.removeEventListener(temperatureEventListener);
        }
        if (moistureEventListener != null) {
            moistureSensorRef.removeEventListener(moistureEventListener);
        }
        if (lightEventListener != null) {
            lightSensorRef.removeEventListener(lightEventListener);
        }
    }
}
