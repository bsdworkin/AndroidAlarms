package com.bendworkin.androidalarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {

    private EditText daysInput;
    private EditText minsInput;
    private EditText msgInput;
    private Button startTimerButton;

    private long daysLong;
    private long minsLong;
    private String msgEntered;

    private long daysToMS;
    private long minsToMS;
    private long daysAndMinsMS;

    public void setTimer(View view){

        daysLong = Long.parseLong(daysInput.getText().toString());
        minsLong = Long.parseLong(minsInput.getText().toString());
        msgEntered = msgInput.getText().toString();


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent )
            {
                Toast.makeText(getApplicationContext(), msgEntered, Toast.LENGTH_LONG).show();
                context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
            }
        };

        this.registerReceiver( receiver, new IntentFilter("com.blah.blah.somemessage") );

        PendingIntent pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );
        AlarmManager manager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        daysToMS = TimeUnit.DAYS.toMillis(daysLong);
        minsToMS = TimeUnit.MINUTES.toMillis(minsLong);
        daysAndMinsMS = daysToMS + minsToMS;



        // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
        manager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + daysAndMinsMS, pintent );


        Toast.makeText(getApplicationContext(), "ALARM IS SET", Toast.LENGTH_LONG).show();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        daysInput = (EditText) findViewById(R.id.daysText);
        minsInput = (EditText) findViewById(R.id.minsText);
        msgInput = (EditText) findViewById(R.id.timerMessage);
        startTimerButton = (Button) findViewById(R.id.startTimer);





    }
}
