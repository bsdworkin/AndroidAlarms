package com.bendworkin.androidalarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private EditText daysInput;
    private EditText minsInput;
    private EditText msgInput;
    private Button startTimerButton;

    public void setTimer(View view){



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        daysInput = (EditText) findViewById(R.id.daysText);
        minsInput = (EditText) findViewById(R.id.minsText);
        msgInput = (EditText) findViewById(R.id.minsText);
        startTimerButton = (Button) findViewById(R.id.startTimer);





    }
}
