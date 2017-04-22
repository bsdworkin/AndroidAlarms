package com.bendworkin.androidalarms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void toSetAlarm(View view){
        Intent setAlarm = new Intent(getApplicationContext(), AlarmActivity.class);

        startActivity(setAlarm);
    }

    public void toSetTimer(View view){
        Intent setTimer = new Intent(getApplicationContext(), TimerActivity.class);

        startActivity(setTimer);
    }

    public void toLocationAlarm(View view){
        Intent setLocationAlarm = new Intent(getApplicationContext(), LocationAlarmActivity.class);

        startActivity(setLocationAlarm);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking to see if we asked for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //If we dont have permission we need to ask for it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            //We already have permission
            Log.i("Location Permission", "Permission Already Granted");
        }
    }
}
