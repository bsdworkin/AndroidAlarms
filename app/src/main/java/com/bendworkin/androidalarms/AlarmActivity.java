package com.bendworkin.androidalarms;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
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
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity {

    private Button setAlarmButton;
    private EditText dateTxt;
    private EditText timeTxt;
    private EditText msgTxt;
    private String date;
    private String time;
    private String alarmMsg;

    private Date toDate;
    private Date toTime;

    LocationManager locationManager;
    LocationListener locationListener;

    public void setAlarmNow(View view){

        date = dateTxt.getText().toString();
        time = timeTxt.getText().toString();
        alarmMsg = msgTxt.getText().toString();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat tf = new SimpleDateFormat("hh:mm:ss a");

        //parse the date
        try {
            this.toDate = df.parse(date);
            this.toTime = tf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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


        //call function to set alarm
        setAlarm();













    }

    //sets the alarm
    public void setAlarm(){
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive( Context context, Intent intent )
            {
                Toast.makeText(getApplicationContext(), alarmMsg, Toast.LENGTH_LONG).show();
                context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
            }
        };

        this.registerReceiver( receiver, new IntentFilter("com.blah.blah.somemessage") );

        PendingIntent pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );
        AlarmManager manager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

            /*String[] dateSplit = date.split("/");
            String[] timeSplit = time.split(":");

            int month = Integer.parseInt(dateSplit[0]);
            int day = Integer.parseInt(dateSplit[1]);
            int year = Integer.parseInt(dateSplit[2]);

            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);
            int second = Integer.parseInt(timeSplit[2]);

            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.YEAR, year);

            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
            cal.set(Calendar.MILLISECOND, 0);



            Toast.makeText(getApplicationContext(), Long.toString(cal.getTimeInMillis()), Toast.LENGTH_LONG).show();
            */



        Date finalDate = new Date();
        finalDate.setTime(toDate.getTime() + toTime.getTime() - 21600000);

        long testLong = finalDate.getTime() - cal.getTimeInMillis();



        // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
        manager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + testLong, pintent );
        //Toast.makeText(getApplicationContext(), toDate.toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), toTime.toString(), Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(), Long.toString(testLong), Toast.LENGTH_LONG).show();



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

    public void setDate(String date){
        this.date = date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setAlarmMsg(String message){
        this.alarmMsg = message;
    }

    public String getDate(){ return this.date; }

    public String getTime(){ return this.time; }

    public String getAlarmMsg(){ return this.alarmMsg; }


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
