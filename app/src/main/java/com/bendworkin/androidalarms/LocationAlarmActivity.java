package com.bendworkin.androidalarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class LocationAlarmActivity extends AppCompatActivity {

    private SeekBar minSeekBar;
    private TextView minsTxt;
    private Button cancelButton;

    private int mins;

    public void startLocationAlarm(View view ){

        cancelButton.setVisibility(View.INVISIBLE);


    }

    public void toMM(View view){

        Intent cancelAlarm = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(cancelAlarm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);

        minSeekBar = (SeekBar) findViewById(R.id.minSeekBar);
        minsTxt = (TextView) findViewById(R.id.minTxtView);
        cancelButton = (Button) findViewById(R.id.button5);

        minSeekBar.setMax(60);
        minSeekBar.setProgress(2);

        minSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mins = progress;

                if (minSeekBar.getProgress() == 0){

                    minsTxt.setText("0:00:00");

                }
                else if(minSeekBar.getProgress() < 10 && minSeekBar.getProgress() > 0){

                    minsTxt.setText("0:0" + mins + ":00");

                }
                else if(minSeekBar.getProgress() == 60){

                    minsTxt.setText("1:00:00");

                }else {

                    minsTxt.setText("0:" + mins + ":00");

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
