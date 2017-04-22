package com.bendworkin.androidalarms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmActivity extends AppCompatActivity {

    private Button setAlarmButton;
    private EditText dateTxt;
    private EditText timeTxt;
    private EditText msgTxt;
    private String date;
    private String time;
    private String alarmMsg;

    LocationManager locationManager;
    LocationListener locationListener;

    public void setAlarmNow(View view){

        date = dateTxt.getText().toString();
        time = timeTxt.getText().toString();
        alarmMsg = msgTxt.getText().toString();

        //Checking to see if we asked for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            //If we dont have permission we need to ask for it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            //We already have permission so we listen to the location
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            //location of user....
            //May be able to use to see timezone?
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        //Logic to set alarm to AlarmManager












    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {

            for (int i = 0; i < grantResults.length; i++) {

                if (requestCode == 1 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                    //Checking to see if we asked for location permission
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        //listening to the users location
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, locationListener);

                    }

                }

            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        setAlarmButton = (Button)findViewById(R.id.alarmButton);
        dateTxt = (EditText) findViewById(R.id.date);
        timeTxt = (EditText) findViewById(R.id.timeText);
        msgTxt = (EditText) findViewById(R.id.alarmMsg);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("Location", location.toString());

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
        };




    }
}
