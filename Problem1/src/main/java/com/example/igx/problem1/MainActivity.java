package com.example.igx.problem1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener, SensorEventListener {

    SensorManager sensorM;
    Sensor acc;
    Sensor gra;
    Sensor lig;
    Sensor pro;
    float[] aX = new float[3];
    float[] grX = new float[3];
    float lX;
    float pX;
//    float[] gyX = new float[3];
//
    Location now;
    double latitude;
    double longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc = sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gra = sensorM.getDefaultSensor(Sensor.TYPE_GRAVITY);
        lig = sensorM.getDefaultSensor(Sensor.TYPE_LIGHT);
        pro = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);

//        latitude = now.getLatitude();
//        longtitude = now.getLongitude();

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.text_selectedType)).setText("LOCATION");
//                ((TextView) findViewById(R.id.text_selectedData)).setText("Latitude : "+latitude+"\nLongtitude : "+longtitude);
            }
        });
        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.text_selectedType)).setText("SENSORS");
                ((TextView) findViewById(R.id.text_selectedData)).setText("Acceletometer: ("+aX[0]+", "+aX[1]+", "+aX[2]+")"+
                        "\nGravity: ("+grX[0]+", "+grX[1]+", "+grX[2]+")\n"+
                        "Light: "+lX+" lux\n"+"Proximity: "+pX+"cm");

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String phoneNum = ((EditText) findViewById(R.id.edit_phoneNumber)).getText().toString();
            }
        });
    }
    protected void onResume(){
        super.onResume();
        sensorM.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        sensorM.unregisterListener(this);
    }
    //location
    @Override
    public void onLocationChanged(Location location) {
//        latitude = now.getLatitude();
//        longtitude = now.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
//sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        final float alpha = (float)0.8;

        grX[0] = alpha * grX[0] + (1 - alpha) * event.values[0];
        grX[1] = alpha * grX[1] + (1 - alpha) * event.values[1];
        grX[2] = alpha * grX[2] + (1 - alpha) * event.values[2];

        aX[0] = event.values[0] - grX[0];
        aX[1] = event.values[1] - grX[1];
        aX[2] = event.values[2] - grX[2];
        lX = event.values[0];
        pX = event.values[0];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
