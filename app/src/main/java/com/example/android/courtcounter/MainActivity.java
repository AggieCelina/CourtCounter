package com.example.android.courtcounter;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import static com.example.android.courtcounter.R.id.chrono;

public class MainActivity extends AppCompatActivity {

    int timeMove = 0;
    int timeRest = 0;
    int timesRepeat = 0;
    int nbOfSets = 0;
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHolder = new ViewHolder();
        viewHolder.movePlusButton = (Button) findViewById(R.id.move_plus_button);
        viewHolder.moveSubButton = (Button) findViewById(R.id.move_sub_button);
        viewHolder.restPlusButton = (Button) findViewById(R.id.rest_plus_button);
        viewHolder.restSubButton = (Button) findViewById(R.id.rest_sub_button);
        viewHolder.setPlusButton = (Button) findViewById(R.id.set_plus_button);
        viewHolder.setSubButton = (Button) findViewById(R.id.set_sub_button);
        viewHolder.startButton = (Button) findViewById(R.id.start_button);
        viewHolder.chrono = (Chronometer) findViewById(chrono);
        viewHolder.timeMoveView = (TextView) findViewById(R.id.move_time);
        viewHolder.timeRestView = (TextView) findViewById(R.id.rest_time);
        viewHolder.timesRepeatView = (TextView) findViewById(R.id.repeat_time);
    }

    /*
    View Holder - hold views
     */

    public void addOneToMove(View view) {

        timeMove = timeMove + 1;
        displayForTimeMove(timeMove);
    }

/*
add and substract time for move
 */

    public void substractOneFromMove(View view) {

        if (timeMove > 0) {

            timeMove = timeMove - 1;
            displayForTimeMove(timeMove);
        }
    }

    public void addOneToRest(View view) {

        timeRest = timeRest + 1;
        displayForTimeRest(timeRest);
    }


    /*
    add and substract time for rest
    */

    public void substractOneFromRest(View view) {

        timeRest = timeRest - 1;
        displayForTimeRest(timeRest);
    }

    public void addOneToRepeat(View view) {

        timesRepeat = timesRepeat + 1;
        displayForTimesRepeat(timesRepeat);
    }

     /*
    add and substract time for repeat
    */

    public void substractOneFromRepeat(View view) {

        timesRepeat = timesRepeat - 1;
        displayForTimesRepeat(timesRepeat);
    }

    public void resetEverything(View view) {

        timeRest = 0;
        timeMove = 0;
        timesRepeat = 0;
        displayForTimeMove(timeMove);
        displayForTimeRest(timeRest);
        displayForTimesRepeat(timesRepeat);

        viewHolder.chrono.setBase(SystemClock.elapsedRealtime());
        viewHolder.chrono.stop();
        viewHolder.chrono.setTextSize(120);

        PlayBeep();

        //enable buttons
        viewHolder.startButton.setEnabled(true);
        viewHolder.movePlusButton.setEnabled(true);
        viewHolder.moveSubButton.setEnabled(true);
        viewHolder.restPlusButton.setEnabled(true);
        viewHolder.restSubButton.setEnabled(true);
        viewHolder.setPlusButton.setEnabled(true);
        viewHolder.setSubButton.setEnabled(true);


    }

    /*

    reset everything
     */

    public void displayForTimeMove(int time) {

        viewHolder.timeMoveView.setText(String.valueOf(time));
    }
    /*
    display methods
     */

    public void displayForTimeRest(int time) {

        viewHolder.timeRestView.setText(String.valueOf(time));
    }

    public void displayForTimesRepeat(int time) {

        viewHolder.timesRepeatView.setText(String.valueOf(time));
    }

    public void startTimerMove(View view) {


        viewHolder.chrono.setTextSize(120);
        viewHolder.chrono.setBase(SystemClock.elapsedRealtime());
        viewHolder.chrono.start();
        countTimerMove(viewHolder.chrono, timeMove + 1);
        PlayBeep();
//disable buttons
        viewHolder.startButton.setEnabled(false);
        viewHolder.movePlusButton.setEnabled(false);
        viewHolder.moveSubButton.setEnabled(false);
        viewHolder.restPlusButton.setEnabled(false);
        viewHolder.restSubButton.setEnabled(false);
        viewHolder.setPlusButton.setEnabled(false);
        viewHolder.setSubButton.setEnabled(false);


    }


    //starts chronometer

    public void startTimerRest(View view) {

        viewHolder.chrono.setBase(SystemClock.elapsedRealtime());
        viewHolder.chrono.start();
        countTimerRest(viewHolder.chrono, timeRest + 1);
        PlayBeep();


    }

    //count ticks and break when ticks reach the value set by user in timeMove
    public void countTimerMove(Chronometer chronometer, final int timeType) {


        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//the OnChronometerTickListener method gets called every time the chronometer has incremented.


            @Override
            public void onChronometerTick(Chronometer chronometer) {


                String timeTypeString = String.format("%02d:%02d", timeType / 60, timeType % 60);
                //The %02d means: "Format as a decimal number using at least 2 digits, and pad with 0 if less than 2 digits"
                //Note, that using the modulo 60 pTime % 60 gets the remainder of seconds when dividing by 60.
                // This is short hand for pTime - (ptime / 60) * 60
                String currentTime = chronometer.getText().toString();
                if (currentTime.equals(timeTypeString)) //put time according to you
                {
                    chronometer.stop();
                    chronometer.setText("00:00");
                    startTimerRest(chronometer);
                    PlayBeep();


                }

            }
        });


    }

    public void countTimerRest(Chronometer chronometer, final int timeType) {


        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//the OnChronometerTickListener method gets called every time the chronometer has incremented.


            @Override
            public void onChronometerTick(Chronometer chronometer) {


                String timeTypeString = String.format("%02d:%02d", timeType / 60, timeType % 60);
                //The %02d means: "Format as a decimal number using at least 2 digits, and pad with 0 if less than 2 digits"
                //Note, that using the modulo 60 pTime % 60 gets the remainder of seconds when dividing by 60.
                // This is short hand for pTime - (ptime / 60) * 60
                String currentTime = chronometer.getText().toString();
                if (currentTime.equals(timeTypeString)) //put time according to you
                {
                    chronometer.stop();
                    chronometer.setText("00:00");
                    nbOfSets = nbOfSets + 1;
                    if (nbOfSets < timesRepeat) {
                        startTimerMove(chronometer);
                    } else {
                        chronometer.setText("well done!");
                        chronometer.setTextSize(50);
                        PlayBeep();
                    }


                }

            }
        });


    }

    /*

    make a beep sound
     */
    public void PlayBeep() {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

    }

    private class ViewHolder {


        Button movePlusButton;
        Button moveSubButton;
        Button restPlusButton;
        Button restSubButton;
        Button setSubButton;
        Button setPlusButton;
        Button startButton;
        Chronometer chrono;
        TextView timeMoveView;
        TextView timeRestView;
        TextView timesRepeatView;
    }


}