package com.bendworkin.androidalarms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LocationAlarmActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_location_alarm);


    }
}
